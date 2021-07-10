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
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.CallExp;
import org.eclipse.ocl.pivot.CollectionItem;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.LetExp;
import org.eclipse.ocl.pivot.LoopExp;
import org.eclipse.ocl.pivot.NavigationCallExp;
import org.eclipse.ocl.pivot.OperationCallExp;
import org.eclipse.ocl.pivot.TypedElement;
import org.eclipse.ocl.pivot.VariableExp;
import org.eclipse.ocl.pivot.internal.cse.CSEElement;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.utilities.TreeIterable;

/**
 * @since 1.16
 */
public class SymbolicUtil extends AbstractLeafSymbolicValue
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
			return System.identityHashCode(o1) - System.identityHashCode(o2);
		}

		protected int getDelegations(@NonNull TypedElement typedElement) {
			TypedElement delegate = getDelegate(typedElement);
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
		Map<@NonNull CSEElement, @NonNull List<@NonNull TypedElement>> cse2elements = new HashMap<>();
		for (EObject eObject : new TreeIterable(expressionInOCL,true)) {
			if (eObject instanceof TypedElement) {		// MapLiteralPart
				TypedElement element = (TypedElement)eObject;
				CSEElement cseElement2 = element2cse.get(element);
				assert cseElement2 != null : "Missing CSE for " + element.eClass().getName() + ": " + element;
				List<@NonNull TypedElement> elements = cse2elements.get(cseElement2);
				if (elements == null) {
					elements = new ArrayList<>();
					cse2elements.put(cseElement2, elements);
				}
				elements.add(element);
			}
		}
		for (@NonNull List<@NonNull TypedElement> elements : cse2elements.values()) {
			for (@NonNull TypedElement element : elements) {
				for (TypedElement aDelegate = element; (aDelegate = SymbolicUtil.getDelegate(aDelegate)) != null; ) {
					assert elements.contains(aDelegate) : "Inconsistent CSE delegation for " + element.eClass().getName() + ": " + element;
				}
			}
		}
		return true;
	}

	public static @Nullable TypedElement getDelegate(@NonNull TypedElement typedElement) {
		TypedElement delegate = null;
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
	 * Return a short summary of typedElement to clarify its use in a whole ancestry diagnostic.
	 */
	public static @NonNull String getSummary(@NonNull TypedElement typedElement) {
		StringBuilder s = new StringBuilder();
		if (typedElement instanceof CallExp) {
			CallExp callExp = (CallExp)typedElement;
			s.append("«expr»");
			if (callExp.isIsSafe()) {
				s.append("?");
			}
			if (callExp.getOwnedSource().isIsMany()) {
				s.append("->");
			}
			else {
				s.append(".");
			}
			if (typedElement instanceof NavigationCallExp) {
				s.append(PivotUtil.getName(PivotUtil.getReferredProperty((NavigationCallExp)typedElement)));
			}
			else if (typedElement instanceof OperationCallExp) {
				s.append(PivotUtil.getName(PivotUtil.getReferredOperation((OperationCallExp)typedElement)));
				s.append("(...)");
			}
			else if (typedElement instanceof LoopExp) {
				s.append(PivotUtil.getName(PivotUtil.getReferredIteration((LoopExp)typedElement)));
				s.append("(...)");
			}
			else if (typedElement instanceof LoopExp) {
				s.append("a ");
				s.append(typedElement.eClass().getName());
			}
		}
		else {
			s.append("a ");
			s.append(typedElement.eClass().getName());
		}
		return s.toString();
	}

}
