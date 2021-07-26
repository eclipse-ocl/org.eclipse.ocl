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

import java.util.Comparator;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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
import org.eclipse.ocl.pivot.utilities.TreeIterable;
import org.eclipse.ocl.pivot.values.SymbolicValue;

import com.google.common.collect.Iterables;

/**
 * @since 1.16
 */
public class SymbolicUtil
{
	/**
	 * TypedElementHeightComparator sort ExpressionInOCL/OCLExpression to determine a (re-)evaluation order.
	 * Simplest (fewsest shallowest children) CSE first, then fewest delegations to the CSE then system hash code.
	 */
	public static class TypedElementHeightComparator implements Comparator<@NonNull TypedElement>
	{
		private final @NonNull Map<@NonNull ? extends Element, @NonNull CSEElement> element2cse;

		public TypedElementHeightComparator(@NonNull Map<@NonNull ? extends Element, @NonNull CSEElement> element2cse) {
			this.element2cse = element2cse;
		}

		@Override
		public int compare(@NonNull TypedElement o1, @NonNull TypedElement o2) {
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
			return System.identityHashCode(o1) - System.identityHashCode(o2);		// FIXME depth-first search order
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
	}

	//
	//	Confirm that the element2cse has a consistently delegated entry for the expressionInOCL tree.
	//
	public static boolean debugCheckCSEs(@NonNull ExpressionInOCL expressionInOCL, @NonNull Map<@NonNull Element, @NonNull CSEElement> element2cse) {
		//	Map<@NonNull CSEElement, @NonNull Set<@NonNull Element>> cse2elements = new HashMap<>();
		Set<@NonNull CSEElement> cseElements = new HashSet<>();
		for (EObject eObject : new TreeIterable(expressionInOCL,true)) {
			if (eObject instanceof Element) {		// MapLiteralPart
				Element element = (Element)eObject;
				CSEElement cseElement = element2cse.get(element);
				assert cseElement != null : "Missing CSE for " + element.eClass().getName() + ": " + element;
	//			Set<@NonNull Element> elements = cse2elements.get(cseElement);
	//			if (elements == null) {
	//				elements = new HashSet<>();
	//				cse2elements.put(cseElement, elements);
	//			}
	//			elements.add(element);
				cseElements.add(cseElement);
			}
		}
		for (@NonNull CSEElement cseElement : cseElements) {
			Iterable<@NonNull TypedElement> elements = cseElement.getElements();
			for (@NonNull Element element : elements) {
				for (Element aDelegate = element; (aDelegate = SymbolicUtil.getDelegate(aDelegate)) != null; ) {
					assert Iterables.contains(elements, aDelegate) : "Inconsistent CSE delegation for " + element.eClass().getName() + ": " + element;
				}
			}
		}
	//	for (Map.Entry<@NonNull CSEElement, @NonNull Set<@NonNull Element>> entry : cse2elements.entrySet()) {
	//		Set<@NonNull Element> localElements = entry.getValue();
	//		Set<@NonNull Element> cachedElements = Sets.newHashSet(entry.getKey().getElements());
	//		assert localElements.equals(cachedElements);
	//	}
		return true;
	}

	public static @Nullable Element getDelegate(@NonNull Element typedElement) {
		Element delegate = null;
		if (typedElement instanceof CollectionItem) {
			delegate = PivotUtil.getOwnedItem((CollectionItem)typedElement);
		}
		else if (typedElement instanceof ExpressionInOCL) {
			delegate = ((ExpressionInOCL)typedElement).getOwnedBody();
		}
		else if (typedElement instanceof LetExp) {
			delegate = PivotUtil.getOwnedIn((LetExp)typedElement);
		}
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
