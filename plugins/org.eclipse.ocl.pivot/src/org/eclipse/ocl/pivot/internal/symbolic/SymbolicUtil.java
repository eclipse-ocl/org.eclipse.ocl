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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.CallExp;
import org.eclipse.ocl.pivot.CollectionItem;
import org.eclipse.ocl.pivot.CollectionRange;
import org.eclipse.ocl.pivot.Constraint;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.IfExp;
import org.eclipse.ocl.pivot.IteratorVariable;
import org.eclipse.ocl.pivot.LetExp;
import org.eclipse.ocl.pivot.LetVariable;
import org.eclipse.ocl.pivot.LiteralExp;
import org.eclipse.ocl.pivot.LoopExp;
import org.eclipse.ocl.pivot.NamedElement;
import org.eclipse.ocl.pivot.NavigationCallExp;
import org.eclipse.ocl.pivot.OperationCallExp;
import org.eclipse.ocl.pivot.ParameterVariable;
import org.eclipse.ocl.pivot.PivotPackage;
import org.eclipse.ocl.pivot.ResultVariable;
import org.eclipse.ocl.pivot.TypedElement;
import org.eclipse.ocl.pivot.VariableDeclaration;
import org.eclipse.ocl.pivot.VariableExp;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.values.SymbolicValue;
import org.eclipse.ocl.pivot.values.Value;

/**
 * @since 1.17
 */
public class SymbolicUtil
{
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

	public static @Nullable SymbolicReason isRequiredReason(@NonNull TypedElement typedElement) {
		if (typedElement.isIsRequired()) {
			return null;
		}
		StringBuilder s = new StringBuilder();
		printName(s, typedElement);
		s.append(" may be null");
		return new SymbolicSimpleReason(s.toString());
	}

	@Deprecated		/* @deprecated propagate true reason */
	public static @Nullable SymbolicReason mayBeInvalidReason(boolean mayBeInvalid) {
		if (mayBeInvalid) {
			return SymbolicSimpleReason.MAY_BE_INVALID_REASON;
		}
		else {
			return null;
		}
	}

	public static @Nullable SymbolicReason mayBeInvalidReason(@Nullable Object value) {
		assert !(value instanceof SymbolicValue) : "SymbolValue is no longer a Value";
		if (value == null) {
			return null;
		}
		else if ((value instanceof Value) && ((Value)value).mayBeInvalid()) {
			return SymbolicSimpleReason.MAY_BE_INVALID_VALUE;
		}
		return null;
	}

	public static @Nullable SymbolicReason mayBeInvalidReason(@NonNull SymbolicValue nestedValue, @NonNull String outerReason) {
		SymbolicReason nestedMayBeInvalidReason = nestedValue.mayBeInvalidReason();
		if (nestedMayBeInvalidReason == null) {
			return null;
		}
		// return nestedMayBeInvalidReason + " => " + outerReason + " may be invalid";
		return new SymbolicNestedReason(nestedMayBeInvalidReason, null, new SymbolicSimpleReason(outerReason + " may be invalid"));
	}

	public static @NonNull SymbolicReason mayBeInvalidReason(@NonNull SymbolicReason nestedMayBeInvalidReason, @NonNull CallExp callExp) {
		return new SymbolicNestedReason(nestedMayBeInvalidReason, null, new SymbolicSimpleReason("\"" + callExp + "\" may be invalid"));
	}

	@Deprecated		/* @deprecated propagate true reason */
	public static @Nullable SymbolicReason mayBeNullReason(boolean mayBeNull) {
		if (mayBeNull) {
			return SymbolicSimpleReason.MAY_BE_NULL_REASON;
		}
		else {
			return null;
		}
	}

	public static @Nullable SymbolicReason mayBeNullReason(@NonNull SymbolicValue nestedValue, @NonNull String outerReason) {
		SymbolicReason nestedMayBeNullReason = nestedValue.mayBeNullReason();
		if (nestedMayBeNullReason == null) {
			return null;
		}
		// return nestedMayBeNullReason + " => " + outerReason + " may be null";
		return new SymbolicNestedReason(nestedMayBeNullReason, null, new SymbolicSimpleReason(outerReason + " may be null"));
	}

	public static @Nullable SymbolicReason mayBeNullReason(@Nullable Object value) {
		assert !(value instanceof SymbolicValue) : "SymbolValue is no longer a Value";
		if (value == null) {
			return SymbolicSimpleReason.NULL_VALUE;
		}
		else if ((value instanceof Value) && ((Value)value).mayBeNull()) {
			return SymbolicSimpleReason.MAY_BE_NULL_VALUE;
		}
		return null;
	}

	public static @NonNull SymbolicReason mayBeNullReason(@NonNull SymbolicReason nestedMayBeNullReason, @NonNull CallExp callExp) {
		return new SymbolicNestedReason(nestedMayBeNullReason, null, new SymbolicSimpleReason("\"" + callExp + "\" may be null"));
	}

	/**
	 * Return a summary of namedElement to clarify its use in adiagnostic.
	 */
	public static void printName(@NonNull StringBuilder s, @NonNull NamedElement namedElement) {
		if (namedElement instanceof IteratorVariable) {
			IteratorVariable iteratorVariable = (IteratorVariable)namedElement;
			if (iteratorVariable.isIsImplicit()) {
				s.append("implicit ");
			}
			s.append("iterator");
		}
		else if (namedElement instanceof LetVariable) {
			s.append("let variable");
		}
		else if (namedElement instanceof ParameterVariable) {
			s.append("parameter");
		}
		else if (namedElement instanceof ResultVariable) {
			s.append("iteration result");
		}
		else if (namedElement instanceof VariableDeclaration) {
			s.append("variable");
		}
		else {
			s.append(namedElement.eClass().getName());
		}
		s.append(" \"");
		s.append(namedElement.getName());
		s.append("\"");
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
			EObject eContainer = namedElement.eContainer();
			if (eContainer instanceof Constraint) {
				s.append(((NamedElement)eContainer.eContainer()).getName());
				s.append("::");
				s.append(((NamedElement)eContainer).getName());
				return;
			}
			else if (eContainer instanceof NamedElement) {
				s.append(((NamedElement)eContainer).getName());
				return;
			}
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
			LoopExp callExp = (LoopExp)namedElement;
			if (callExp.isIsSafe()) {
				s.append("?~");
			}
			s.append(PivotUtil.getName(PivotUtil.getReferredIteration(callExp)));
			s.append("()");
		}
		else if (namedElement instanceof NavigationCallExp) {
			NavigationCallExp callExp = (NavigationCallExp)namedElement;
			if (callExp.isIsSafe()) {
				s.append("?~");
			}
			s.append(PivotUtil.getName(PivotUtil.getReferredProperty(callExp)));
		}
		else if (namedElement instanceof OperationCallExp) {
			OperationCallExp callExp = (OperationCallExp)namedElement;
			if (callExp.isIsSafe()) {
				s.append("?~");
			}
			s.append(PivotUtil.getName(PivotUtil.getReferredOperation(callExp)));
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
