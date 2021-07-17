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
import org.eclipse.ocl.pivot.internal.symbolic.SymbolicUtil;
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
	 * The particular TypedElement for which the Hypothesis is explored by this HypothesizedSymbolicEvaluationEnvironment.
	 */
	protected final @NonNull TypedElement typedElement;

	/**
	 * The hypothesis-specific (symbolic) value of each common expression element, null if not yet computed.
	 * Note that this is the 'read' value. A re-evaluation may compute a rival value which,
	 *  if incompatible constitutes a contradiction.
	 */
	private @NonNull Map<@NonNull CSEElement, @NonNull SymbolicValue> cseElement2symbolicValue = new HashMap<>();

	/**
	 * Expressions (and ExpressionInOCL) whose symbolic value must be re-evaluated..
	 */
	private final @NonNull Set<@NonNull TypedElement> affectedTypedElements = new HashSet<>();

	/**
	 * CSEElements whose symbolic value must be re-evaluated.
	 */
	private final @NonNull Set<@NonNull CSEElement> affectedCSEElements = new HashSet<>();

	/**
	 * TypedElements for which the hypothesized value or its control path consequences provides a better value.
	 */
	private final @NonNull Set<@NonNull CSEElement> refinedCSEElements = new HashSet<>();

	public HypothesizedSymbolicEvaluationEnvironment(@NonNull BaseSymbolicEvaluationEnvironment baseSymbolicEvaluationEnvironment, @NonNull Hypothesis hypothesis, @NonNull TypedElement typedElement) {
		super(baseSymbolicEvaluationEnvironment.getSymbolicAnalysis());
		this.baseSymbolicEvaluationEnvironment = baseSymbolicEvaluationEnvironment;
		this.hypothesis = hypothesis;
		this.typedElement = typedElement;
		installHypothesis();
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
						CSEElement cseElement = cseAnalysis.getCSEElement(argument);
						setSymbolicValue(cseElement, refinedSymbolicValue);
						refinedCSEElements.add(cseElement);
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
				if (refinedSymbolicValue == baseSymbolicValue) {
					refinedSymbolicValue = null;
				}
			}
			if (refinedSymbolicValue != null) {
				CSEElement cseElement = cseAnalysis.getCSEElement(refinedExpression);
				setSymbolicValue(cseElement, refinedSymbolicValue);
				refinedCSEElements.add(cseElement);
			}
		}
	}

	private void addRefinedOutputCSEElements(@NonNull CSEElement cseElement) {
		for (@NonNull CSEElement cseOutput : cseElement.getOutputs()) {
			if (affectedCSEElements.add(cseOutput)) {
				addRefinedOutputCSEElements(cseOutput);
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

	public @Nullable String analyze() {
		List<@NonNull TypedElement> affectedTypedElementsList = new ArrayList<>(affectedTypedElements);
		if (affectedTypedElementsList.size() > 1) {
			Collections.sort(affectedTypedElementsList, cseAnalysis.getTypedElementHeightComparator());
		}
		for (@NonNull TypedElement affectedTypedElement : affectedTypedElementsList) {
			String inCompatibility = symbolicReEvaluate(affectedTypedElement);
			if (inCompatibility != null) {
				return inCompatibility;
			}
		}
		Set<@NonNull TypedElement> typedElementsSet = new HashSet<>();
		for (@NonNull CSEElement cseElement : refinedCSEElements) {
			for (@NonNull TypedElement typedElement2 : cseElement.getElements()) {
				typedElementsSet.add(typedElement2);
			}
		}
		List<@NonNull TypedElement> typedElementsList = new ArrayList<>(typedElementsSet);
		if (typedElementsList.size() > 1) {
			Collections.sort(typedElementsList, cseAnalysis.getTypedElementHeightComparator());
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
	public final @Nullable SymbolicValue basicGetSymbolicValue(@NonNull TypedElement element) {
		CSEElement cseElement = cseAnalysis.getCSEElement(element);
		return basicGetSymbolicValue(cseElement);
	}

	@Override
	public @Nullable SymbolicValue basicGetSymbolicValue(@NonNull CSEElement cseElement) {
		return cseElement2symbolicValue.get(cseElement);
	}

	private @NonNull List<@NonNull VariableDeclaration> computeAffectedVariables() {
		UniqueList<@NonNull VariableDeclaration> variables = new UniqueList<>();
		for (@NonNull CSEElement cseElement : affectedCSEElements) {
			for (@NonNull TypedElement element : cseElement.getElements()) {
				if (element instanceof VariableDeclaration) {	// FIXME ?? surely (output != typedElement) is redundant ??
					variables.add((VariableDeclaration)element);
				}
			}
		}
		return variables;
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

	private void installHypothesis() {
		//
		//	Install the directly hypothesized expression.
		//
		@NonNull SymbolicValue hypothesizedValue = hypothesis.getHypothesizedValue();
		CSEElement hypothesisCSE = hypothesis.getCSEElement();
		setSymbolicValue(hypothesisCSE, hypothesizedValue);		// Install the known 'read' value.
		affectedCSEElements.add(hypothesisCSE);
		//
		//	Ensure that all parents of the hypothesized expressions are re-evaluated.
		//
		//	Side-effect: refinedTypedElements and affectedVariables updated.
		//
		addRefinedParentTypedElements(typedElement);
		addRefinedOutputCSEElements(hypothesisCSE);

	//	for (@NonNull CSEElement cseElement9 : affectedCSEElements) {
	//		for (@NonNull TypedElement typedElement9 : cseElement9.getElements()) {
	//			assert affectedTypedElements.contains(typedElement9);
	//		}
	//	}
		for (@NonNull TypedElement typedElement9 : affectedTypedElements) {
			assert affectedCSEElements.contains(cseAnalysis.getCSEElement(typedElement9));
		}




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


		for (@NonNull TypedElement typedElement9 : affectedTypedElements) {
			assert affectedCSEElements.contains(cseAnalysis.getCSEElement(typedElement9));
		}

		List<@NonNull VariableDeclaration> computedAffectedVariables = computeAffectedVariables();
		//
		//	Ensure that all usage of all VariableExps for all refined Variables are re-evaluated.
		//
		for (@NonNull VariableDeclaration variable : computedAffectedVariables) {		// FIXME should manage a single traversal
			CSEElement variableCSE = cseAnalysis.getCSEElement(variable);
			assert affectedCSEElements.contains(variableCSE);
			for (@NonNull Element output : variableCSE.getElements()) {
				if ((output instanceof VariableExp) && (output != typedElement)) {	// FIXME ?? surely (output != typedElement) is redundant ??
					VariableExp variableExp = (VariableExp)output;
					if (affectedTypedElements.add(variableExp)) {
						addRefinedParentTypedElements(variableExp);
					}
				}
			}
		}
		for (@NonNull TypedElement typedElement9 : affectedTypedElements) {
			CSEElement cseElement = cseAnalysis.getCSEElement(typedElement9);
			assert affectedCSEElements.contains(cseElement);
		}
	//	for (@NonNull TypedElement typedElement9 : computedAffectedVariables) {
	//		CSEElement cseElement = cseAnalysis.getCSEElement(typedElement9);
	//		assert affectedCSEElements.contains(cseElement);
	//	}
		//
		//	Compute re-evaluate CSEs.
		//
		Set<@NonNull CSEElement> reEvaluateCSEs = new HashSet<>();
	//	for (@NonNull TypedElement refinedTypedElement : refinedTypedElements) {
	//		CSEElement cseElement = cseAnalysis.getCSEElement(refinedTypedElement);
		for (@NonNull CSEElement cseElement : refinedCSEElements) {
			SymbolicValue refinedValue = cseElement2symbolicValue.get(cseElement); // refinedTypedElements2symbolicValue.get(refinedTypedElement);
			assert refinedValue != null;
			SymbolicValue old = cseElement2symbolicValue.put(cseElement, refinedValue);	// Install the new 'read' value.
		//	assert old == null;
			reEvaluateCSEs.add(cseElement);
		}
		for (@NonNull TypedElement affectedTypedElement : affectedTypedElements) {
			CSEElement cseElement = cseAnalysis.getCSEElement(affectedTypedElement);
			reEvaluateCSEs.add(cseElement);
		}
		//
		// Copy all unaffected CSEs.
		//
		BaseSymbolicEvaluationEnvironment baseSymbolicEvaluationEnvironment = getBaseSymbolicEvaluationEnvironment();
		for (@NonNull CSEElement cseElement : baseSymbolicEvaluationEnvironment.getCSEElements()) {
			if ((cseElement != hypothesisCSE) && !reEvaluateCSEs.contains(cseElement)/* && !affectedCSEElements.contains(cseElement)*/ && !refinedCSEElements.contains(cseElement)) {
				SymbolicValue symbolicValue = baseSymbolicEvaluationEnvironment.getSymbolicValue(cseElement);
				SymbolicValue old = cseElement2symbolicValue.put(cseElement, symbolicValue);
				assert old == null;
			}
		}
	}

	public @NonNull SymbolicValue setSymbolicValue(@NonNull CSEElement cseElement, @NonNull SymbolicValue symbolicValue) {
		SymbolicValue old = cseElement2symbolicValue.put(cseElement, symbolicValue);	// Install the new 'read' value.
		if (old != null) {
			symbolicValue = symbolicValue.asRefinementOf(old);
			if (symbolicValue != old) {
				cseElement2symbolicValue.put(cseElement, symbolicValue);
			}
		}
	//	assert (old == null) || (old == symbolicValue); //old.equals(symbolicValue);
		return symbolicValue;
	}

	@Override
	public @NonNull SymbolicValue setSymbolicValue(@NonNull TypedElement typedElement, @NonNull SymbolicValue symbolicValue) {
		CSEElement cseElement = cseAnalysis.getCSEElement(typedElement);
		if ("self.name".equals(cseElement.toString())) {
			getClass();		// XXX
		}
		return setSymbolicValue(cseElement, symbolicValue);
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
		SymbolicValue writeValue ;
		try {
			writeValue = symbolicEvaluationVisitor.symbolicEvaluate(typedElement);
		}
		catch (InvalidValueException e) {
			Object boxedValue = environmentFactory.getIdResolver().boxedValueOf(e);
			writeValue = getKnownValue(boxedValue);
		}
		if (SymbolicAnalysis.HYPOTHESIS.isActive()) {
			SymbolicAnalysis.HYPOTHESIS.println("    re-evaluated: " + SymbolicUtil.printPath(typedElement) + " as: " + writeValue);
		}
		SymbolicValue readValue = basicGetSymbolicValue(typedElement);		// Get the 'read' value
		if (readValue == null) {											// If a new evaluation
			CSEElement cseElement = cseAnalysis.getCSEElement(typedElement);
			setSymbolicValue(cseElement, writeValue);					// Record re-evaluated value
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
		s.append(hypothesis.getKind() + " hypothesis for " + SymbolicUtil.printPath(typedElement));
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
		for (@NonNull CSEElement redefinedCSEElement : refinedCSEElements) {
			for (@NonNull TypedElement redefinedTypedElement : redefinedCSEElement.getElements()) {
				StringUtil.appendIndentation(s, depth+2);
				s.append(redefinedTypedElement.eClass().getName());
				s.append(" : \"");
				s.append(redefinedTypedElement);
				s.append("\" => ");
				SymbolicValue constrainingValue = cseElement2symbolicValue.get(cseAnalysis.getCSEElement(redefinedTypedElement)); //  refinedTypedElements2symbolicValue.get(redefinedTypedElement);
				assert constrainingValue != null;
			//	StringUtil.appendIndentation(s, depth+2);
				s.append(constrainingValue);
			}
		}
		StringUtil.appendIndentation(s, depth+1);
		s.append("re-evaluate");
		for (@NonNull VariableDeclaration affectedVariable : computeAffectedVariables()) {
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
}
