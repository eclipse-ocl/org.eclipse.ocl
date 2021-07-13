/*******************************************************************************
 * Copyright (c) 2021 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/

package org.eclipse.ocl.pivot.internal.evaluation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.IfExp;
import org.eclipse.ocl.pivot.LoopExp;
import org.eclipse.ocl.pivot.NavigationCallExp;
import org.eclipse.ocl.pivot.OCLExpression;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.OperationCallExp;
import org.eclipse.ocl.pivot.Parameter;
import org.eclipse.ocl.pivot.TypedElement;
import org.eclipse.ocl.pivot.VariableDeclaration;
import org.eclipse.ocl.pivot.VariableExp;
import org.eclipse.ocl.pivot.internal.cse.CSEElement;
import org.eclipse.ocl.pivot.internal.symbolic.AbstractSymbolicRefinedValue;
import org.eclipse.ocl.pivot.internal.symbolic.SymbolicStatus;
import org.eclipse.ocl.pivot.internal.utilities.PivotUtilInternal;
import org.eclipse.ocl.pivot.library.LibraryFeature;
import org.eclipse.ocl.pivot.library.logical.BooleanAndOperation;
import org.eclipse.ocl.pivot.library.logical.BooleanAndOperation2;
import org.eclipse.ocl.pivot.library.logical.BooleanImpliesOperation;
import org.eclipse.ocl.pivot.library.logical.BooleanImpliesOperation2;
import org.eclipse.ocl.pivot.library.logical.BooleanOrOperation;
import org.eclipse.ocl.pivot.library.logical.BooleanOrOperation2;
import org.eclipse.ocl.pivot.library.oclany.OclAnyEqualOperation;
import org.eclipse.ocl.pivot.library.oclany.OclAnyNotEqualOperation;
import org.eclipse.ocl.pivot.library.oclany.OclAnyOclAsTypeOperation;
import org.eclipse.ocl.pivot.library.oclany.OclAnyOclIsInStateOperation;
import org.eclipse.ocl.pivot.library.oclany.OclAnyOclIsInvalidOperation;
import org.eclipse.ocl.pivot.library.oclany.OclAnyOclIsUndefinedOperation;
import org.eclipse.ocl.pivot.library.oclany.OclAnyOclLogOperation;
import org.eclipse.ocl.pivot.library.oclany.OclAnyOclTypeOperation;
import org.eclipse.ocl.pivot.library.oclany.OclAnyToStringOperation;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.utilities.StringUtil;
import org.eclipse.ocl.pivot.utilities.UniqueList;
import org.eclipse.ocl.pivot.utilities.ValueUtil;
import org.eclipse.ocl.pivot.values.InvalidValueException;
import org.eclipse.ocl.pivot.values.SymbolicValue;

/**
 * A HypothesizedSymbolicEvaluationEnvironment supports the symbolic re-evaluation subject to some hypothesis
 * which defines a value for some expression and imposes executability of a particular control path. All CSEs
 * have the same refined value for the poyrposes of the hypothesis evaluation. Once the hypothesis has been
 * contradicted a refined value vfor the hypothesized expression can be return ed to the baseSymbolicEvaluationEnvironment.
 *
 * @since 1.16
 */
public class HypothesizedSymbolicEvaluationEnvironment extends AbstractSymbolicEvaluationEnvironment
{
	/**
	 * The overall evaluation, initially hyothesis-free but updated by contradicted hypothesis.
	 */
	protected final @NonNull BaseSymbolicEvaluationEnvironment baseSymbolicEvaluationEnvironment;

	/**
	 * The Hypothesis explored by this HypothesizedSymbolicEvaluationEnvironment.
	 */
	protected final @NonNull Hypothesis hypothesis;

	/**
	 * The hypothesis-specific (symbolic) value of each common expression element, null if not yet computed.
	 * Note that this is the 'read' value. A re-evaluation may compute a rival value which,
	 *  if incompatible constitutes a contradiction.
	 */
	private @NonNull Map<@NonNull CSEElement, @NonNull SymbolicValue> cseElement2symbolicValue = new HashMap<>();

	/**
	 * Variables whose initializer is affected and so whose symbolic value must be re-evaluated..
	 */
	private final @NonNull UniqueList<@NonNull VariableDeclaration> affectedVariables = new UniqueList<>();

	/**
	 * Expressions (and ExpressionInOCL) whose symbolic value must be re-evaluated..
	 */
	private final @NonNull Set<@NonNull TypedElement> affectedTypedElements = new HashSet<>();

	/**
	 * TypedElements for which the hypothesized value or its control path consequences provides a better value.
	 */
	private final @NonNull Map<@NonNull TypedElement, @NonNull SymbolicValue> refinedTypedElements2symbolicValue = new HashMap<>();

	public HypothesizedSymbolicEvaluationEnvironment(@NonNull BaseSymbolicEvaluationEnvironment baseSymbolicEvaluationEnvironment, @NonNull Hypothesis hypothesis, @NonNull TypedElement typedElement) {
		super(baseSymbolicEvaluationEnvironment.getSymbolicAnalysis(), typedElement);
		this.baseSymbolicEvaluationEnvironment = baseSymbolicEvaluationEnvironment;
		this.hypothesis = hypothesis;
		installHypothesis(typedElement);
	}

	/**
	 * Given that executedExpression executes, register any consequent refinements for
	 * any refinedTypedElements or affectedVariables that ensure this execution.
	 *
	 * </br>'and'/'implies' guards are set true
	 * </br>'or' guards false
	 * </br>'if' conditions true/false as appropriate.
	 * </br>navigation sources not-invalid
	 * </br>unsafe navigation sources not-null
	 * </br>loop sources not-invalid
	 * </br>unsafe loop sources not-null
	 */
	private void addRefinedChildExpressions(@NonNull OCLExpression executedExpression) {
		EObject containingTypedElement = executedExpression.eContainer();
		OCLExpression refinedExpression = null;						// Source/condition expression that can be refined
		Boolean refinedBooleanValue = null;							//  by a simple Boolean	value
		boolean mayBeInvalid = false;								//   or a strictness prohibition on
		boolean mayBeNull = false;									//    null or invalid sources
		if (containingTypedElement instanceof IfExp) {
			IfExp ifExp = (IfExp)containingTypedElement;
			refinedExpression = PivotUtil.getOwnedCondition(ifExp);
			if (executedExpression == ifExp.getOwnedThen()) {
				refinedBooleanValue = Boolean.TRUE;
			}
			else if (executedExpression == ifExp.getOwnedElse()) {
				refinedBooleanValue = Boolean.FALSE;
			}
			// else  if (executedExpression == ifExp.getOwnedCondition()) imposes no path limitations
		}
		else if (containingTypedElement instanceof OperationCallExp) {
			OperationCallExp operationCallExp = (OperationCallExp)containingTypedElement;
			if (operationCallExp.isIsSafe()) {
				mayBeNull = true;
			}
			refinedExpression = PivotUtil.getOwnedSource(operationCallExp);
			Operation operation = PivotUtil.getReferredOperation(operationCallExp);
			List<@NonNull OCLExpression> ownedArguments = PivotUtilInternal.getOwnedArgumentsList(operationCallExp);
			int argumentsSize = ownedArguments.size();
			if (argumentsSize == 0) {
				LibraryFeature implementation = operation.getImplementation();
				if (implementation instanceof OclAnyOclIsInvalidOperation) {
					mayBeInvalid = true;
					mayBeNull = true;
				}
			//	else if (implementation instanceof OclAnyOclIsNewOperation) {
			//		mayBeNull = true;
			//	}
				else if (implementation instanceof OclAnyOclIsUndefinedOperation) {
					mayBeInvalid = true;
					mayBeNull = true;
				}
				else if (implementation instanceof OclAnyOclLogOperation) {
					mayBeInvalid = true;
					mayBeNull = true;
				}
				else if (implementation instanceof OclAnyOclTypeOperation) {
					mayBeInvalid = true;
					mayBeNull = true;
				}
				else if (implementation instanceof OclAnyToStringOperation) {
					mayBeInvalid = true;
					mayBeNull = true;
				}
			}
			else if (argumentsSize == 1) {
				OCLExpression argument = ownedArguments.get(0);
				if (executedExpression == argument) {
					LibraryFeature implementation = operation.getImplementation();
					if ((implementation instanceof BooleanAndOperation) || (implementation instanceof BooleanAndOperation2)) {
						refinedBooleanValue = Boolean.TRUE;
					}
					else if ((implementation instanceof BooleanImpliesOperation) || (implementation instanceof BooleanImpliesOperation2)) {
						refinedBooleanValue = Boolean.TRUE;
					}
					else if ((implementation instanceof BooleanOrOperation) || (implementation instanceof BooleanOrOperation2)) {
						refinedBooleanValue = Boolean.FALSE;
					}
					else if (implementation instanceof OclAnyOclAsTypeOperation) {
						mayBeNull = true;
					}
					else if (implementation instanceof OclAnyOclIsInStateOperation) {
						mayBeNull = true;
					}
					else if (implementation instanceof OclAnyEqualOperation) {
						mayBeNull = true;
					}
					else if (implementation instanceof OclAnyNotEqualOperation) {
						mayBeNull = true;
					}
				}
			}
			boolean isValidating = operation.isIsValidating();
			int i = 0;
			for (@NonNull Parameter parameter : PivotUtil.getOwnedParameters(operation)) {
				if (parameter.isIsRequired()) {
					OCLExpression argument = ownedArguments.get(i);
					SymbolicValue baseSymbolicValue = baseSymbolicEvaluationEnvironment.getSymbolicValue(refinedExpression);
					SymbolicValue refinedSymbolicValue = baseSymbolicValue;
					if (!isValidating && baseSymbolicValue.mayBeInvalid()) {
						refinedSymbolicValue = AbstractSymbolicRefinedValue.createExceptValue(refinedSymbolicValue, ValueUtil.INVALID_VALUE);
					}
					if (baseSymbolicValue.mayBeNull()) {
						refinedSymbolicValue = AbstractSymbolicRefinedValue.createExceptValue(refinedSymbolicValue, null);
					}
					if (refinedSymbolicValue != baseSymbolicValue) {
						SymbolicValue old = refinedTypedElements2symbolicValue.put(argument, refinedSymbolicValue);
						assert old == null;
					}
				}
				i++;
			}
		}
		else if (containingTypedElement instanceof NavigationCallExp) {
			NavigationCallExp navigationCallExp = (NavigationCallExp)containingTypedElement;
			if (navigationCallExp.isIsSafe()) {
				mayBeNull = true;
			}
			refinedExpression = PivotUtil.getOwnedSource(navigationCallExp);
		}
		else if (containingTypedElement instanceof LoopExp) {
			LoopExp loopExp = (LoopExp)containingTypedElement;
			if (loopExp.isIsSafe()) {
				mayBeNull = true;
			}
			if (executedExpression == loopExp.getOwnedBody()) {
			//	constrainedExpression = PivotUtil.getOwnedSource(loopExp);
			//	symbolicPathValue = evaluationEnvironment.getSymbolicValue2(constrainedExpression);
			// XXX	symbolicKnownValue = SIZE_NOT_EMPTY;
				// XXX isSafe
			}
			refinedExpression = PivotUtil.getOwnedSource(loopExp);
		}
		if (refinedExpression != null) {

			SymbolicValue refinedSymbolicValue = null;
			if (refinedBooleanValue != null) {
				refinedSymbolicValue = getKnownValue(refinedBooleanValue);
			}
			else if (!mayBeInvalid || !mayBeNull) {
				SymbolicValue baseSymbolicValue = baseSymbolicEvaluationEnvironment.getSymbolicValue(refinedExpression);
				refinedSymbolicValue = baseSymbolicValue;
				if (!mayBeInvalid) {
					refinedSymbolicValue = AbstractSymbolicRefinedValue.createExceptValue(refinedSymbolicValue, ValueUtil.INVALID_VALUE);
				}
				if (!mayBeNull) {
					refinedSymbolicValue = AbstractSymbolicRefinedValue.createExceptValue(refinedSymbolicValue, null);
				}
			}
			if (refinedSymbolicValue != null) {
				SymbolicValue old = refinedTypedElements2symbolicValue.put(refinedExpression, refinedSymbolicValue);
				assert old == null;
			}
			if (refinedExpression instanceof VariableExp) {
				affectedVariables.add(PivotUtil.getReferredVariable((VariableExp)refinedExpression));
			}
		}
	}

	/**
	 * Given that typedElement executes, register any consequent refinements for
	 * containing TypedElements that ensure this execution.
	 */
	private void addRefinedParentTypedElements(@NonNull TypedElement typedElement) {
		EObject eContainer = typedElement.eContainer();
		if (eContainer instanceof VariableDeclaration) {
			VariableDeclaration containingVariable = (VariableDeclaration)eContainer;
			assert containingVariable != null;
			boolean added = affectedVariables.add(containingVariable);
			assert added;
			addRefinedParentTypedElements(containingVariable);
		}
		else if (eContainer instanceof OCLExpression) {
			OCLExpression containingExpression = (OCLExpression)eContainer;
			assert containingExpression != null;
			affectedTypedElements.add(containingExpression);
			addRefinedParentTypedElements(containingExpression);
		}
		else if (eContainer instanceof ExpressionInOCL) {
			ExpressionInOCL containingExpressionInOCL = (ExpressionInOCL)eContainer;
			assert containingExpressionInOCL != null;
			affectedTypedElements.add(containingExpressionInOCL);
		}
	}

	@Override
	public @Nullable SymbolicValue basicGetSymbolicValue(@NonNull CSEElement cseElement) {
		return cseElement2symbolicValue.get(cseElement);
	}

	@Override
	protected @NonNull Iterable<@NonNull TypedElement> getAffectedTypedElements(@NonNull TypedElement typedElement) {
		return Collections.singletonList(typedElement);
	}

	@Override
	@NonNull
	public BaseSymbolicEvaluationEnvironment getBaseSymbolicEvaluationEnvironment() {
		return baseSymbolicEvaluationEnvironment;
	}

	private void installHypothesis(@NonNull TypedElement typedElement) {
		//
		//	Install the directly hypothesized expression.
		//
	//	@NonNull TypedElement typedElement = executableObject;
		@SuppressWarnings("unused") @NonNull SymbolicValue originalValue = hypothesis.getOriginalValue();
		@NonNull SymbolicValue hypothesizedValue = hypothesis.getHypothesizedValue();
		CSEElement hypothesisCSE = hypothesis.getCSEElement();
		cseElement2symbolicValue.put(hypothesisCSE, hypothesizedValue);		// Install the known 'read' value.
		//
		//	Ensure that all parents of the hypothesized expressions are re-evaluated.
		//
		//	Side-effect: refinedTypedElements and affectedVariables updated.
		//
		if (typedElement instanceof VariableExp) {
			VariableDeclaration variable = PivotUtil.getReferredVariable((VariableExp)typedElement);
			affectedVariables.add(variable);
		}
		else if (typedElement instanceof VariableDeclaration) {
			affectedVariables.add((VariableDeclaration)typedElement);
		}
		addRefinedParentTypedElements(typedElement);
		//
		//	Ensure that all children of control path expressions are refined to enforce the control path executability.
		//
		//	Side-effect: refinedTypedElements and affectedVariables updated.
		//
		for (@NonNull TypedElement affectedTypedElement : affectedTypedElements) {
			if (affectedTypedElement instanceof OCLExpression) {
				addRefinedChildExpressions((OCLExpression)affectedTypedElement);
			}
		}
		//
		//	Ensure that all usage of all VariableExps for all refined Variables are re-evaluated.
		//
		for (@NonNull VariableDeclaration variable : affectedVariables) {		// FIXME should manage a single traversal
			CSEElement variableCSE = symbolicAnalysis.getCSEElement(variable);
			for (@NonNull Element output : variableCSE.getElements()) {
				if ((output instanceof VariableExp) && (output != typedElement)) {	// FIXME ?? surely (output != typedElement) is redundant ??
					VariableExp variableExp = (VariableExp)output;
					if (affectedTypedElements.add(variableExp)) {
						addRefinedParentTypedElements(variableExp);
					}
				}
			}
		}
		//
		//	Compute re-evaluate CSEs.
		//
		Set<@NonNull CSEElement> reEvaluateCSEs = new HashSet<>();
		for (@NonNull TypedElement refinedTypedElement : refinedTypedElements2symbolicValue.keySet()) {
			CSEElement cseElement = symbolicAnalysis.getCSEElement(refinedTypedElement);
			SymbolicValue refinedValue = refinedTypedElements2symbolicValue.get(refinedTypedElement);
			assert refinedValue != null;
			SymbolicValue old = cseElement2symbolicValue.put(cseElement, refinedValue);	// Install the new 'read' value.
			assert old == null;
			reEvaluateCSEs.add(cseElement);
		}
		for (@NonNull TypedElement affectedTypedElement : affectedTypedElements) {
			CSEElement cseElement = symbolicAnalysis.getCSEElement(affectedTypedElement);
			reEvaluateCSEs.add(cseElement);
		}
		//
		// Copy all unaffected CSEs.
		//
		BaseSymbolicEvaluationEnvironment baseSymbolicEvaluationEnvironment = getBaseSymbolicEvaluationEnvironment();
		for (@NonNull CSEElement cseElement : baseSymbolicEvaluationEnvironment.getCSEElements()) {
			if ((cseElement != hypothesisCSE) && !reEvaluateCSEs.contains(cseElement)) {
				SymbolicValue symbolicValue = baseSymbolicEvaluationEnvironment.getSymbolicValue(cseElement);
				SymbolicValue old = cseElement2symbolicValue.put(cseElement, symbolicValue);
				assert old == null;
			}
		}
	}

	public @Nullable String isContradiction(@NonNull TypedElement typedElement) {
		List<@NonNull TypedElement> affectedTypedElementsList = new ArrayList<>(affectedTypedElements);
		if (affectedTypedElementsList.size() > 1) {
			Collections.sort(affectedTypedElementsList, symbolicAnalysis.getTypedElementHeightComparator());
		}
		for (@NonNull TypedElement affectedTypedElement : affectedTypedElementsList) {
			String inCompatibility = symbolicReEvaluate(affectedTypedElement);
			if (inCompatibility != null) {
				return inCompatibility;
			}
		}
		List<@NonNull TypedElement> typedElementsList = new ArrayList<>(refinedTypedElements2symbolicValue.keySet());
		if (typedElementsList.size() > 1) {
			Collections.sort(typedElementsList, symbolicAnalysis.getTypedElementHeightComparator());
		}
		for (@NonNull TypedElement typedElement2 : typedElementsList) {
			String inCompatibility = symbolicReEvaluate(typedElement2);
			if (inCompatibility != null) {
				return inCompatibility;
			}
		}
		return null;
	}

	@Override
	public @NonNull SymbolicValue symbolicEvaluate(@NonNull TypedElement typedElement) {
		SymbolicValue symbolicValue = getSymbolicValue(typedElement);
	//	if (SymbolicAnalysis.HYPOTHESIS.isActive()) {
	//		SymbolicAnalysis.HYPOTHESIS.println("    evaluated: \"" + typedElement + "\" as: " + symbolicValue);
	//	}
		return symbolicValue;									// Use the 'read' value
	}

	/**
	 * Re-evaluate typedElement and return null if the new result is compatible with the old value.
	 * Conversely return a String describing the incompatibility and consequently a contradiction.
	 */
	public @Nullable String symbolicReEvaluate(@NonNull TypedElement typedElement) {
		Object result = null;
		try {
			result = symbolicEvaluationVisitor.symbolicEvaluate(typedElement);
		}
		catch (InvalidValueException e) {
			result = e;
		}
		SymbolicValue writeValue;
		if (result instanceof SymbolicValue) {
			writeValue = (SymbolicValue)result;
		}
		else {
			Object boxedValue = environmentFactory.getIdResolver().boxedValueOf(result);
			writeValue = getKnownValue(boxedValue);
		}
		if (SymbolicAnalysis.HYPOTHESIS.isActive()) {
			SymbolicAnalysis.HYPOTHESIS.println("    re-evaluated: \"" + typedElement + "\" as: " + writeValue);
		}
		SymbolicValue readValue = basicGetSymbolicValue(typedElement);		// Get the 'read' value
		if (readValue == null) {											// If a new evaluation
			CSEElement cseElement = symbolicAnalysis.getCSEElement(typedElement);
			traceSymbolicValue(cseElement, writeValue);					// Record re-evaluated value
			return null;
		}
		if (writeValue == readValue) {
			return null;
		}
		SymbolicStatus booleanWriteStatus = writeValue.basicGetBooleanStatus();
		if (booleanWriteStatus != null) {
			boolean mayBeFalse = !booleanWriteStatus.isSatisfied();
			boolean mayBeTrue = !booleanWriteStatus.isUnsatisfied();
			boolean mustBeFalse = readValue.isFalse();
			boolean mustBeTrue = readValue.isTrue();
			if (mustBeFalse && !mayBeFalse) {
				return "mustBeFalse is incompatible with !mayBeFalse";
			}
			if (mustBeTrue && !mayBeTrue) {
				return "mustBeTrue is incompatible with !mayBeTrue";
			}
		}

		if (writeValue.isInvalid() && !readValue.mayBeInvalid()) {
			return "isInvalid is incompatible with !mayBeInvalid";
		}
		if (writeValue.mayBeInvalid() && !readValue.mayBeInvalid()) {
			return "mayBeInvalid is incompatible with !mayBeInvalid";
		}
		if (writeValue.isNull() && !readValue.mayBeNull()) {
			return "isNull is incompatible with !mayBeNull";
		}
		if (writeValue.mayBeNull() && !readValue.mayBeNull()) {
			return "mayBeNull is incompatible with !mayBeNull";
		}
		if (writeValue.basicGetZeroStatus() != null) {
			if (writeValue.isZero() && !readValue.mayBeZero()) {
				return "isZero is incompatible with !mayBeZero";
			}
			if (writeValue.mayBeZero() && !readValue.mayBeZero()) {
				return "mayBeZero is incompatible with !mayBeZero";
			}
		}
	//	if (writeValue.equals(readValue)) { / / XXX isCompatible
			return null;
	//	}
	//	else {
	//		return false;
	//	}
	}

	@Override
	public void toString(@NonNull StringBuilder s, int depth) {
		s.append(hypothesis.getKind() + " hypothesis for '" + executableObject + "' in '" + executableObject.eContainer() + "'");
		StringUtil.appendIndentation(s, depth+1);
		List<@NonNull CSEElement> keys = new ArrayList<>(cseElement2symbolicValue.keySet());
		if (keys.size() > 1) {
			Collections.sort(keys, NameUtil.TO_STRING_COMPARATOR);
		}
		s.append(keys.size() + " cses");
		for (@NonNull CSEElement key : keys) {
			StringUtil.appendIndentation(s, depth+2);
			Object value = cseElement2symbolicValue.get(key);
			s.append(key + " => " + value);
		}
		StringUtil.appendIndentation(s, depth+1);
		s.append("refined");
		for (@NonNull TypedElement redefinedTypedElement : refinedTypedElements2symbolicValue.keySet()) {
			StringUtil.appendIndentation(s, depth+2);
			s.append(redefinedTypedElement.eClass().getName());
			s.append(" : \"");
			s.append(redefinedTypedElement);
			s.append("\" => ");
			SymbolicValue constrainingValue = refinedTypedElements2symbolicValue.get(redefinedTypedElement);
			assert constrainingValue != null;
		//	StringUtil.appendIndentation(s, depth+2);
			s.append(constrainingValue);
		}
		StringUtil.appendIndentation(s, depth+1);
		s.append("re-evaluate");
		for (@NonNull VariableDeclaration affectedVariable : affectedVariables) {
			StringUtil.appendIndentation(s, depth+2);
			s.append(affectedVariable.eClass().getName());
			s.append(" : \"");
			s.append(affectedVariable);
			s.append("\"");
		}
		for (@NonNull TypedElement affectedTypedElement : affectedTypedElements) {
			StringUtil.appendIndentation(s, depth+2);
			s.append(affectedTypedElement.eClass().getName());
			s.append(" : \"");
			s.append(affectedTypedElement);
			s.append("\"");
		}
	}

	@Override
	public @NonNull SymbolicValue traceSymbolicValue(@NonNull CSEElement cseElement, @NonNull SymbolicValue symbolicValue) {
		if ("self.name".equals(cseElement.toString())) {
			getClass();		// XXX
		}
		SymbolicValue old = cseElement2symbolicValue.put(cseElement, symbolicValue);	// Install the new 'read' value.
		assert (old == null) || (old == symbolicValue); //old.equals(symbolicValue);
		return symbolicValue;
	}
}
