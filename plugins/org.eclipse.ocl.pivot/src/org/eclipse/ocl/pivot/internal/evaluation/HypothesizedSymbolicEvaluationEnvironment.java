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
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.CompleteClass;
import org.eclipse.ocl.pivot.CompleteModel;
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.IfExp;
import org.eclipse.ocl.pivot.LoopExp;
import org.eclipse.ocl.pivot.NavigationCallExp;
import org.eclipse.ocl.pivot.OCLExpression;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.OperationCallExp;
import org.eclipse.ocl.pivot.Parameter;
import org.eclipse.ocl.pivot.StandardLibrary;
import org.eclipse.ocl.pivot.TypedElement;
import org.eclipse.ocl.pivot.VariableDeclaration;
import org.eclipse.ocl.pivot.VariableExp;
import org.eclipse.ocl.pivot.internal.cse.CSEElement;
import org.eclipse.ocl.pivot.internal.symbolic.AbstractSymbolicRefinedValue;
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
	protected final @NonNull TypedElement hypothesizedTypedElement;

	/**
	 * The hypothesis-specific (symbolic) value of each common expression element, null if not yet computed.
	 *
	 * This value is lazily initialized from the corresponding overall symbolic value from the baseSymbolicEvaluationEnvironment,
	 * refined by the refined symbolic values of each active typed elemnts for the CSE element.
	 *
	 * A re-evaluation of all refined values propagates to give the composite hypothesized state.
	 *
	 * At any point computation of a refined value for the CSE may fail and so identify a contradiction.
	 */
	private final @NonNull Map<@NonNull CSEElement, @NonNull SymbolicValue> cseElement2symbolicValue;

	/**
	 * Expressions, Variables and ExpressionInOCL whose symbolic value may depends on the value of the hypothesizedTypedElement.
	 * For most expressions the container necessarily depends on the child, but not vice-versa. For variables a change is tantamount
	 * to a re=evaluation since for instance a let variable affects parts of the in-child.
	 */
	private final @NonNull UniqueList<@NonNull TypedElement> activeTypedElements = new UniqueList<>();

	/**
	 * CSEElements whose symbolic value are accessed during a symbolic evaluation using the hypothesized typed element.
	 */
//	private final @NonNull UniqueList<@NonNull CSEElement> activeCSEElements = new UniqueList<>();

	/**
	 * CSEElements for which the hypothesizedTypedElement or its control ancestry consequences provides a refined value.
	 */
	private final @NonNull UniqueList<@NonNull CSEElement> refinedCSEElements = new UniqueList<>();

	public HypothesizedSymbolicEvaluationEnvironment(@NonNull BaseSymbolicEvaluationEnvironment baseSymbolicEvaluationEnvironment, @NonNull Hypothesis hypothesis, @NonNull TypedElement hypothesizedTypedElement) {
		super(baseSymbolicEvaluationEnvironment.getSymbolicAnalysis());
		this.baseSymbolicEvaluationEnvironment = baseSymbolicEvaluationEnvironment;
		this.hypothesis = hypothesis;
		this.hypothesizedTypedElement = hypothesizedTypedElement;
		this.cseElement2symbolicValue = new HashMap<>(baseSymbolicEvaluationEnvironment.getCSEElement2SymbolicValue());
	}



//	private void addRefinedOutputCSEElements(@NonNull CSEElement cseElement) {
//		for (@NonNull CSEElement cseOutput : cseElement.getOutputs()) {
//			if (activeCSEElements.add(cseOutput)) {
//				addRefinedOutputCSEElements(cseOutput);
//			}
//		}
//	}

	public @Nullable String analyze() {
		@NonNull SymbolicValue hypothesizedValue = hypothesis.getHypothesizedValue();
		installActiveTypedElementAncestry(hypothesizedTypedElement, hypothesizedValue, Boolean.TRUE);
		UniqueList<@NonNull CSEElement> reevaluatedCSEElements = new UniqueList<>();
		for (@NonNull TypedElement activeTypedElement : activeTypedElements) {
			CSEElement activeCSEElement = cseAnalysis.getCSEElement(activeTypedElement);
			if (reevaluatedCSEElements.add(activeCSEElement)) {
				String incompatibility = symbolicReEvaluate(activeTypedElement);
				if (incompatibility != null) {
					return incompatibility;
				}
			}
		}
	/*	Set<@NonNull TypedElement> typedElementsSet = new HashSet<>();
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
			String incompatibility = symbolicReEvaluate(typedElement2);
			if (incompatibility != null) {
				return incompatibility;
			}
		} */
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

/*	private @NonNull List<@NonNull TypedElement> computeAffectedTypedElements() {
		UniqueList<@NonNull TypedElement> typedElements = new UniqueList<>();
		for (@NonNull CSEElement cseElement : activeCSEElements) {
			for (@NonNull TypedElement element : cseElement.getElements()) {
				if (!(element instanceof VariableDeclaration)) {
					typedElements.add(element);
				}
			}
		}
		return typedElements;
	} */

/*	private @NonNull Iterable<@NonNull VariableDeclaration> computeAffectedVariables() {
		List<@NonNull VariableDeclaration> variables = new UniqueList<>();
		for (@NonNull CSEElement cseElement : activeCSEElements) {
			for (@NonNull TypedElement element : cseElement.getElements()) {
				if (element instanceof VariableDeclaration) {
					variables.add((VariableDeclaration)element);
				}
			}
		}
		return variables;
	} */

	@Override
	protected @NonNull Iterable<@NonNull TypedElement> getAffectedTypedElements(@NonNull TypedElement typedElement) {
		return Collections.singletonList(typedElement);
	}

	@Override
	public @NonNull BaseSymbolicEvaluationEnvironment getBaseSymbolicEvaluationEnvironment() {
		return baseSymbolicEvaluationEnvironment;
	}

	/**
	 * Given that activeTypedElement executes, register any consequent refinements for
	 * containing and sibling TypedElements that ensure this execution.
	 */
	private @Nullable String installActiveTypedElementAncestry(@NonNull TypedElement activeTypedElement, @NonNull SymbolicValue activeSymbolicValue, @Nullable Boolean isControlPath) {
		if (activeTypedElement instanceof IfExp) {
			getClass();		// XXX
		}
		if (!activeTypedElements.add(activeTypedElement)) {			// The ancestor of VariableExp wil eventually rejoin the hierarchy
			SymbolicValue symbolicValue = getSymbolicValue(activeTypedElement);
			return symbolicValue.asIncompatibility();
		}
		else {
			SymbolicValue refinedSymbolicValue = setSymbolicValue(activeTypedElement, activeSymbolicValue, isControlPath == Boolean.TRUE ? "hypo" : isControlPath == Boolean.TRUE ? "ctrl" : "copy");
			String incompatibility = refinedSymbolicValue.asIncompatibility();
			if (incompatibility != null) {
				return incompatibility;
			}
			EObject eContainer = activeTypedElement.eContainer();
			if (eContainer instanceof ExpressionInOCL) {
				ExpressionInOCL containingExpressionInOCL = (ExpressionInOCL)eContainer;
				activeTypedElements.add(containingExpressionInOCL);
				if (activeTypedElements.size() > 1) {
					Collections.sort(activeTypedElements, cseAnalysis.getTypedElementHeightComparator());
				}
			}
			else if (eContainer instanceof TypedElement) {
				TypedElement containingTypedElement = (TypedElement)eContainer;
				SymbolicValue containingSymbolicValue = baseSymbolicEvaluationEnvironment.getSymbolicValue(containingTypedElement);
				incompatibility = installActiveTypedElementAncestry(containingTypedElement, containingSymbolicValue, isControlPath != null ? Boolean.FALSE : null);
				if (incompatibility != null) {
					return incompatibility;
				}
/*				if (eContainer instanceof VariableDeclaration) {
					CSEElement cseElement = cseAnalysis.getCSEElement(containingTypedElement);
					for (@NonNull TypedElement referencingTypedElement : cseElement.getElements()) {
						SymbolicValue referencingSymbolicValue = baseSymbolicEvaluationEnvironment.getSymbolicValue(containingTypedElement);
						incompatibility = installActiveTypedElementAncestry(referencingTypedElement, referencingSymbolicValue);
						if (incompatibility != null) {
							return incompatibility;
						}
					}
				} */
			}
			else {
				throw new IllegalStateException("Unexpected " + activeTypedElement.eClass().getName());
			}
			if (isControlPath != null) {
				incompatibility = installActiveTypedElementDescendants(activeTypedElement);
				if (incompatibility != null) {
					return incompatibility;
				}
			}
		}
		return null;
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
	private @Nullable String installActiveTypedElementDescendants(@NonNull TypedElement activeTypedElement) {
		VariableDeclaration activeVariable = null;
		if (activeTypedElement instanceof VariableExp) {
			activeVariable = PivotUtil.getReferredVariable((VariableExp)activeTypedElement);
		}
		else if (activeTypedElement instanceof VariableDeclaration) {
			activeVariable = (VariableDeclaration)activeTypedElement;
		}
		if (activeVariable != null) {
			CSEElement cseElement = cseAnalysis.getCSEElement(activeVariable);
			SymbolicValue symbolicValue = baseSymbolicEvaluationEnvironment.getSymbolicValue(cseElement);
			for (@NonNull TypedElement referencingTypedElement : cseElement.getElements()) {
				String incompatibility = installActiveTypedElementAncestry(referencingTypedElement, symbolicValue, null);
				if (incompatibility != null) {
					return incompatibility;
				}
			}
		}




		EObject containingTypedElement = activeTypedElement.eContainer();
		OCLExpression refinedExpression = null;						// Source/condition expression that can be refined
		Boolean refinedBooleanValue = null;							//  by a simple Boolean	value
		boolean mayBeInvalid = false;								//   or a strictness prohibition on
		boolean mayBeNull = false;									//    null or invalid sources
		if (containingTypedElement instanceof IfExp) {
			IfExp ifExp = (IfExp)containingTypedElement;
			refinedExpression = PivotUtil.getOwnedCondition(ifExp);
			if (activeTypedElement == ifExp.getOwnedThen()) {
				refinedBooleanValue = Boolean.TRUE;
			}
			else if (activeTypedElement == ifExp.getOwnedElse()) {
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

			if (activeTypedElement == operationCallExp.getOwnedSource()) {
				CompleteModel completeModel = environmentFactory.getCompleteModel();
				StandardLibrary standardLibrary = environmentFactory.getStandardLibrary();
				CompleteClass oclInvalidClass = completeModel.getCompleteClass(standardLibrary.getOclInvalidType());
				Operation oclInvalidOperation = oclInvalidClass.getOperation(operation);
				if (oclInvalidOperation != null) {
					mayBeInvalid = true;
				}
				CompleteClass oclVoidClass = completeModel.getCompleteClass(standardLibrary.getOclVoidType());
				Operation oclVoidOperation = oclVoidClass.getOperation(operation);
				if (oclVoidOperation != null) {
					mayBeNull = true;
				}
			}

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
				if (activeTypedElement == argument) {
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
					SymbolicValue baseSymbolicValue = baseSymbolicEvaluationEnvironment.getSymbolicValue(argument);
					SymbolicValue refinedSymbolicValue = baseSymbolicValue;
					if (!isValidating && baseSymbolicValue.mayBeInvalid()) {
						refinedSymbolicValue = AbstractSymbolicRefinedValue.createExceptValue(refinedSymbolicValue, ValueUtil.INVALID_VALUE);
					}
					if (baseSymbolicValue.mayBeNull()) {
						refinedSymbolicValue = AbstractSymbolicRefinedValue.createExceptValue(refinedSymbolicValue, null);
					}
					if (refinedSymbolicValue != baseSymbolicValue) {
						CSEElement cseElement = cseAnalysis.getCSEElement(argument);
						setSymbolicValue(argument, refinedSymbolicValue, "child");
						refinedCSEElements.add(cseElement);
					}
				}
				i++;
			}
		}
		else if (containingTypedElement instanceof NavigationCallExp) {		// Source is not a descendant
		//	NavigationCallExp navigationCallExp = (NavigationCallExp)containingTypedElement;
		//	if (navigationCallExp.isIsSafe()) {
		//		mayBeNull = true;
		//	}
		//	refinedExpression = PivotUtil.getOwnedSource(navigationCallExp);
		}
		else if (containingTypedElement instanceof LoopExp) {
			LoopExp loopExp = (LoopExp)containingTypedElement;
			if (loopExp.isIsSafe()) {
				mayBeNull = true;
			}
			if (activeTypedElement == loopExp.getOwnedBody()) {
			//	constrainedExpression = PivotUtil.getOwnedSource(loopExp);
			//	symbolicPathValue = evaluationEnvironment.getSymbolicValue2(constrainedExpression);
			// XXX	symbolicKnownValue = SIZE_NOT_EMPTY;
				// XXX isSafe
			}
			refinedExpression = PivotUtil.getOwnedSource(loopExp);
		}
		String incompatibility = null;
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
				refinedCSEElements.add(cseElement);
				SymbolicValue appliedSymbolicValue = setSymbolicValue(cseElement, refinedSymbolicValue);
				if (SymbolicAnalysis.HYPOTHESIS.isActive()) {
					SymbolicAnalysis.HYPOTHESIS.println("    set-path: " + SymbolicUtil.printPath(refinedExpression, false) + " as: " + SymbolicUtil.printValue(appliedSymbolicValue));
				}
				incompatibility = appliedSymbolicValue.asIncompatibility();
			}
		}
		return incompatibility;
	}

/*	private void installHypothesis() {
		//
		//	Install the directly hypothesized expression.
		//
	//	@NonNull SymbolicValue hypothesizedValue = hypothesis.getHypothesizedValue();
	//	CSEElement hypothesisCSE = hypothesis.getCSEElement();
	//	setSymbolicValue(hypothesisCSE, hypothesizedValue);		// Install the known 'read' value.
	//	activeCSEElements.add(hypothesisCSE);
		//
		//	Ensure that all parents of the hypothesized expressions are re-evaluated.
		//
		//	Side-effect: refinedTypedElements and affectedVariables updated.
		//
	//	addRefinedOutputCSEElements(hypothesisCSE);
		List<@NonNull TypedElement> computedAffectedTypedElements1 = computeAffectedTypedElements();
		installActiveTypedElementAncestry(hypothesizedTypedElement);

		List<@NonNull TypedElement> affectedTypedElements1 = new ArrayList<>(activeTypedElements);
		//
		//	Ensure that all children of control path expressions are refined to enforce the control path executability.
		//
		//	Side-effect: refinedTypedElements and affectedVariables updated.
		//
	//	for (@NonNull TypedElement affectedTypedElement : computedAffectedTypedElements1) {
	//		if (affectedTypedElement instanceof OCLExpression) {
	//			addRefinedChildExpressions((OCLExpression)affectedTypedElement);
	//		}
	//	}

		List<@NonNull TypedElement> affectedTypedElements2 = new ArrayList<>(activeTypedElements);

		List<@NonNull TypedElement> computedAffectedTypedElements2 = computeAffectedTypedElements();
		assert new HashSet<>(computedAffectedTypedElements1).equals(new HashSet<>(computedAffectedTypedElements2));
		//
		// Copy all inactive CSEs.
		//
		BaseSymbolicEvaluationEnvironment baseSymbolicEvaluationEnvironment = getBaseSymbolicEvaluationEnvironment();
		for (@NonNull CSEElement cseElement : baseSymbolicEvaluationEnvironment.getCSEElements()) {
			if (!activeCSEElements.contains(cseElement) && !refinedCSEElements.contains(cseElement)) {
				SymbolicValue symbolicValue = baseSymbolicEvaluationEnvironment.getSymbolicValue(cseElement);
				SymbolicValue old = cseElement2symbolicValue.put(cseElement, symbolicValue);
				assert old == null;
			}
		}
	} */

	private @NonNull SymbolicValue setSymbolicValue(@NonNull CSEElement cseElement, @NonNull SymbolicValue refinedSymbolicValue) {
		SymbolicValue oldSymbolicValue = cseElement2symbolicValue.get(cseElement);
		assert oldSymbolicValue != null;
		SymbolicValue newSymbolicValue = refinedSymbolicValue;
		if ((newSymbolicValue != oldSymbolicValue) && !newSymbolicValue.equals(oldSymbolicValue)) {
			newSymbolicValue = refinedSymbolicValue.asRefinementOf(oldSymbolicValue);
			if ((newSymbolicValue != oldSymbolicValue) && !newSymbolicValue.equals(oldSymbolicValue)) {
				cseElement2symbolicValue.put(cseElement, newSymbolicValue);
				refinedCSEElements.add(cseElement);
			//	SymbolicAnalysis.HYPOTHESIS.println("    refined: " + SymbolicUtil.printPath(typedElement) + " to: " + + newSymbolicValue);
			}
		}
		return newSymbolicValue;
	}

	@Override
	public @NonNull SymbolicValue setSymbolicValue(@NonNull TypedElement typedElement, @NonNull SymbolicValue symbolicValue, @NonNull String purpose) {
		CSEElement cseElement = cseAnalysis.getCSEElement(typedElement);
		if ("self.name".equals(cseElement.toString())) {
			getClass();		// XXX
		}
		SymbolicValue resultValue = setSymbolicValue(cseElement, symbolicValue);
		if (SymbolicAnalysis.HYPOTHESIS.isActive()) {
			SymbolicAnalysis.HYPOTHESIS.println("    set-" + purpose + ": " + SymbolicUtil.printPath(typedElement, false) + " as: " + SymbolicUtil.printValue(resultValue));
		}
		return resultValue;
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
	//	if (SymbolicAnalysis.HYPOTHESIS.isActive()) {
	//		SymbolicAnalysis.HYPOTHESIS.println("    re-evaluated: " + SymbolicUtil.printPath(typedElement, false) + " as: " + writeValue);
	//	}
		SymbolicValue newSymbolicValue = setSymbolicValue(typedElement, writeValue, "eval");
		return newSymbolicValue.asIncompatibility();
/*		// Record re-evaluated value
		SymbolicValue readValue = basicGetSymbolicValue(typedElement);		// Get the 'read' value
		if (readValue == null) {											// If a new evaluation
			CSEElement cseElement = cseAnalysis.getCSEElement(typedElement);
			setSymbolicValue(cseElement, writeValue);					// Record re-evaluated value
			return null;
		}
		if (writeValue == readValue) {
			return null;		// XXX re-use seSymbolValue
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
	//	} */
	}

	@Override
	public void toString(@NonNull StringBuilder s, int depth) {
		s.append(hypothesis.getKind() + " hypothesis for " + SymbolicUtil.printPath(hypothesizedTypedElement, true));
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
		s.append("active");
	/*	for (@NonNull VariableDeclaration affectedVariable : computeAffectedVariables()) {
			StringUtil.appendIndentation(s, depth+2);
			s.append(affectedVariable.eClass().getName());
			s.append(" : \"");
			s.append(affectedVariable);
			s.append("\"");
		} */
		for (@NonNull TypedElement activeTypedElement : activeTypedElements) {
			StringUtil.appendIndentation(s, depth+2);
			s.append(cseAnalysis.getCSEElement(activeTypedElement).getHeight());
			s.append(" - ");
			s.append(activeTypedElement.eClass().getName());
			s.append(" : ");
			s.append(SymbolicUtil.printPath(activeTypedElement, false));
		}
		StringUtil.appendIndentation(s, depth+1);
		s.append("refined");
		for (@NonNull CSEElement refinedCSEElement : refinedCSEElements) {
			for (@NonNull TypedElement refinedTypedElement : refinedCSEElement.getElements()) {
				StringUtil.appendIndentation(s, depth+2);
				s.append(refinedTypedElement.eClass().getName());
				s.append(" : \"");
				s.append(refinedTypedElement);
				s.append("\" => ");
				SymbolicValue constrainingValue = cseElement2symbolicValue.get(refinedCSEElement); //  refinedTypedElements2symbolicValue.get(redefinedTypedElement);
				assert constrainingValue != null;
			//	StringUtil.appendIndentation(s, depth+2);
				s.append(constrainingValue);
			}
		}
	}
}
