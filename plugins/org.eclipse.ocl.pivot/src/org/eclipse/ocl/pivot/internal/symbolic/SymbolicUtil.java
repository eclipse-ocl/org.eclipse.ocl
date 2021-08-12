/**
 * Copyright (c) 2020, 2021 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 */
package org.eclipse.ocl.pivot.internal.symbolic;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.CollectionItem;
import org.eclipse.ocl.pivot.CollectionRange;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.IfExp;
import org.eclipse.ocl.pivot.LetExp;
import org.eclipse.ocl.pivot.LiteralExp;
import org.eclipse.ocl.pivot.LoopExp;
import org.eclipse.ocl.pivot.NamedElement;
import org.eclipse.ocl.pivot.NavigationCallExp;
import org.eclipse.ocl.pivot.OperationCallExp;
import org.eclipse.ocl.pivot.PivotPackage;
import org.eclipse.ocl.pivot.TypedElement;
import org.eclipse.ocl.pivot.VariableExp;
import org.eclipse.ocl.pivot.internal.cse.CSEElement;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.values.SymbolicValue;

/**
 * @since 1.16
 */
public class SymbolicUtil
{
	/**
	 * TypedElementHeightComparator sort ExpressionInOCL/OCLExpression to determine a (re-)evaluation order.
	 * Simplest (fewest shallowest children) CSE first, then fewest delegations to the CSE then earliest position
	 * in Ecore Tree..
	 */
	public static class TypedElementHeightComparator implements Comparator<@NonNull TypedElement>
	{
		private final @NonNull Map<@NonNull ? extends Element, @NonNull CSEElement> element2cse;
		private @Nullable Map<@NonNull Element, @NonNull Iterable<@NonNull Integer>> element2position = null;

		public TypedElementHeightComparator(@NonNull Map<@NonNull ? extends Element, @NonNull CSEElement> element2cse) {
			this.element2cse = element2cse;
		}

		@Override
		public int compare(@NonNull TypedElement o1, @NonNull TypedElement o2) {
			assert o1 != o2;
			CSEElement cse1 = element2cse.get(o1);
			CSEElement cse2 = element2cse.get(o2);
			assert (cse1 != null) && (cse2 != null);
			int h1 = cse1.getHeight();
			int h2 = cse2.getHeight();
			int diff = h1 - h2;
			if (diff != 0) {
				return diff;
			}
			int d1 = getDelegations(o1);
			int d2 = getDelegations(o2);
			diff = d1 - d2;
			if (diff != 0) {
				return diff;
			}
			Iterator<@NonNull Integer> i1 = getPosition(o1);
			Iterator<@NonNull Integer> i2 = getPosition(o2);
			while (i1.hasNext() && i2.hasNext()) {
				int x1 = i1.next();
				int x2 = i2.next();
				diff = x1 - x2;
				if (diff != 0) {
					return diff;
				}
			}
			if (!i1.hasNext()) {
				return -1;
			}
			if (!i2.hasNext()) {
				return 1;
			}
			assert false;
			return 0;
		}

		protected int getDelegations(@NonNull Element typedElement) {
			Element delegate = getDelegate(typedElement);
			if (delegate != null) {
				final CSEElement cse1 = element2cse.get(typedElement);
				final CSEElement cse2 = element2cse.get(delegate);
				assert cse1 == cse2;		// XXX delete me
				return 1 + getDelegations(delegate);
			}
			return 0;
		}

		protected @NonNull Iterator<@NonNull Integer> getPosition(@NonNull TypedElement typedElement) {
			Map<@NonNull Element, @NonNull Iterable<@NonNull Integer>> element2position2 = element2position;
			if (element2position2 == null) {
				element2position = element2position2 = new HashMap<>();
			}
			Iterable<@NonNull Integer> position = element2position2.get(typedElement);
			if (position == null) {
				List<@NonNull Integer> position2 = new ArrayList<>();
				position = getPosition(position2, typedElement);
			//	assert !element2position2.values().contains(position);
				element2position2.put(typedElement, position);
			//	assert element2position2.values().contains(position);
			}
			return position.iterator();
		}

		private @NonNull Iterable<@NonNull Integer> getPosition(@NonNull List<@NonNull Integer> position, @NonNull EObject eObject) {
			EReference eContainmentFeature = eObject.eContainmentFeature();
			if (eContainmentFeature != null) {
				EObject eContainer = eObject.eContainer();
				assert eContainer != null;
				getPosition(position, eContainer);
				position.add(eContainer.eContents().indexOf(eObject));
			}
			return position;
		}
	}

	public static @Nullable Element getDelegate(@NonNull Element typedElement) {
		Element delegate = null;
		if (typedElement instanceof CollectionItem) {
			delegate = PivotUtil.getOwnedItem((CollectionItem)typedElement);
		}
	//	else if (typedElement instanceof ExpressionInOCL) {
	//		delegate = ((ExpressionInOCL)typedElement).getOwnedBody();
	//	}
	//	else if (typedElement instanceof LetExp) {
	//		delegate = PivotUtil.getOwnedIn((LetExp)typedElement);
	//	}
	//	else if (typedElement instanceof ShadowPart) {
	//		delegate = PivotUtil.getReferredProperty((ShadowPart)typedElement);
	//	}
	//	else if (typedElement instanceof Variable) {
	//		delegate = ((Variable)typedElement).getOwnedInit();
	//	}
		else if (typedElement instanceof VariableExp) {
			delegate = PivotUtil.getReferredVariable((VariableExp)typedElement);
		}
		return delegate;
	}

	/**
	 * Return a reverse hierarchical summary of typedElement to clarify its use in a whole ancestry diagnostic.
	 */
	public static @NonNull String printPath(@NonNull NamedElement namedElement, boolean fullHierarchy) {
		StringBuilder s = new StringBuilder();
		printPath(s, namedElement, null, fullHierarchy);
		return s.toString();
	}

	/**
	 * Accumulate a reverse hierarchical summary of typedElement to clarify its use in a whole ancestry diagnostic.
	 */
	protected static void printPath(@NonNull StringBuilder s, @NonNull NamedElement namedElement, @Nullable EReference childContainmentReference, boolean fullHierarchy) {
		if (namedElement instanceof ExpressionInOCL) {
			s.append("«body»");
			if (!fullHierarchy) {
				return;
			}
		}
		else if (namedElement instanceof CollectionRange) {
			s.append("«range»");
			if (childContainmentReference == PivotPackage.Literals.COLLECTION_RANGE__OWNED_FIRST) {
				s.append(".first");
			}
			else if (childContainmentReference == PivotPackage.Literals.COLLECTION_RANGE__OWNED_LAST) {
				s.append(".last");
			}
		}
		else if (namedElement instanceof IfExp) {
			s.append("«if»");
			if (childContainmentReference == PivotPackage.Literals.IF_EXP__OWNED_CONDITION) {
				s.append(".cond");
			}
			else if (childContainmentReference == PivotPackage.Literals.IF_EXP__OWNED_THEN) {
				s.append(".then");
			}
			else if (childContainmentReference == PivotPackage.Literals.IF_EXP__OWNED_ELSE) {
				s.append(".else");
			}
		}
		else if (namedElement instanceof LetExp) {
			s.append("«let»");
			if (childContainmentReference == PivotPackage.Literals.LET_EXP__OWNED_VARIABLE) {
				s.append(".var");
			}
			else if (childContainmentReference == PivotPackage.Literals.LET_EXP__OWNED_IN) {
				s.append(".in");
			}
		}
		else if (namedElement instanceof LoopExp) {
			s.append(PivotUtil.getName(PivotUtil.getReferredIteration((LoopExp)namedElement)));
			s.append("()");
		}
		else if (namedElement instanceof NavigationCallExp) {
			s.append(PivotUtil.getName(PivotUtil.getReferredProperty((NavigationCallExp)namedElement)));
		}
		else if (namedElement instanceof OperationCallExp) {
			s.append(PivotUtil.getName(PivotUtil.getReferredOperation((OperationCallExp)namedElement)));
			s.append("()");
		}
		else if (namedElement instanceof LiteralExp) {
			s.append(namedElement.toString());
		}
		else {
			s.append(namedElement.getName());
		}
		EObject eContainer = namedElement.eContainer();
		if (eContainer instanceof ExpressionInOCL) {
			eContainer = namedElement.eContainer();
		}
		if (eContainer instanceof NamedElement) {
			s.append("~");
			printPath(s, (NamedElement)eContainer, namedElement.eContainmentFeature(), fullHierarchy);
		}
	}

	public static @NonNull String printValue(@NonNull SymbolicValue symbolicValue) {
		if (symbolicValue instanceof SymbolicKnownValue) {
			@SuppressWarnings("unused") Object value = ((SymbolicKnownValue)symbolicValue).getKnownValue();
			return symbolicValue.toString();
		}
		else{
			return symbolicValue.toString();
		}
	}

}
