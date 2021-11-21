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
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.IfExp;
import org.eclipse.ocl.pivot.IterateExp;
import org.eclipse.ocl.pivot.LetExp;
import org.eclipse.ocl.pivot.LoopExp;
import org.eclipse.ocl.pivot.NavigationCallExp;
import org.eclipse.ocl.pivot.OCLExpression;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.OperationCallExp;
import org.eclipse.ocl.pivot.TypedElement;
import org.eclipse.ocl.pivot.VariableDeclaration;
import org.eclipse.ocl.pivot.VariableExp;
import org.eclipse.ocl.pivot.internal.cse.CSEElement;
import org.eclipse.ocl.pivot.internal.manager.PivotMetamodelManager;
import org.eclipse.ocl.pivot.internal.symbolic.AbstractSymbolicRefinedValue;
import org.eclipse.ocl.pivot.internal.symbolic.SymbolicUtil;
import org.eclipse.ocl.pivot.library.LibraryOperation;
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
 * contradicted a refined value vfor the hypothesized expression can be returned to the baseSymbolicEvaluationEnvironment.
 *
 * @since 1.17
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
		Collections.sort(activeTypedElements, cseAnalysis.getTypedElementHeightComparator());
		UniqueList<@NonNull CSEElement> reevaluatedCSEElements = new UniqueList<>();
		for (@NonNull TypedElement activeTypedElement : activeTypedElements) {
			CSEElement activeCSEElement = cseAnalysis.getCSEElement(activeTypedElement);
			if (reevaluatedCSEElements.add(activeCSEElement)) {
				symbolicReEvaluate(activeTypedElement);
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
		//	ExpressionInOCL expressionInOCL = symbolicAnalysis.getExpressionInOCL();
		//	SymbolicValue symbolicValue = getSymbolicValue(expressionInOCL);
		//	return symbolicValue.asIncompatibility();
			return symbolicAnalysis.getIncompatibility(this, hypothesizedTypedElement);
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
		if (!activeTypedElements.add(activeTypedElement)) {			// The ancestor of VariableExp will eventually rejoin the hierarchy
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
				if (isControlPath == Boolean.TRUE) {
					CSEElement cseElement = cseAnalysis.getCSEElement(activeTypedElement);
					if (cseElement.isSafe()) {
						if (hypothesis.cannotBeSafe()) {
							SymbolicValue baseSymbolicValue2 = baseSymbolicEvaluationEnvironment.getSymbolicValue(activeTypedElement);
						//	SymbolicValue refinedSymbolicValue2 = AbstractSymbolicRefinedValue.createExceptValue(baseSymbolicValue2, ValueUtil.INVALID_VALUE);
						//	symbolicAnalysis.getMayBeInvalidValue(baseSymbolicValue2.getType, null, null)
							SymbolicValue refinedSymbolicValue2 = AbstractSymbolicRefinedValue.createIncompatibility(baseSymbolicValue2, "cannotBeSafe", activeTypedElement);
							String incompatibility2 = installRefinement(activeTypedElement, refinedSymbolicValue2);
							if (incompatibility2 != null) {
								return incompatibility2;
							}
						//	return "cannotBeSafe";
						}
					/*	Iterable<@NonNull CSEElement> inputs = cseElement.getInputs();
						assert inputs != null;
						for (@NonNull CSEElement unsafeCSEElement : inputs) {			// All one input
							for (@NonNull Element unsafeElement : unsafeCSEElement.getElements()) {
								if (unsafeElement instanceof TypedElement) {
									SymbolicValue baseSymbolicValue2 = baseSymbolicEvaluationEnvironment.getSymbolicValue((TypedElement)unsafeElement);
									SymbolicValue refinedSymbolicValue2 = AbstractSymbolicRefinedValue.createExceptValue(baseSymbolicValue2, ValueUtil.INVALID_VALUE);
									refinedSymbolicValue2 = AbstractSymbolicRefinedValue.createExceptValue(baseSymbolicValue2, null);
									String incompatibility2 = installRefinement((TypedElement)unsafeElement, refinedSymbolicValue);
									if (incompatibility2 != null) {
										return incompatibility2;
									}
								}
							}
						} */
					}
					for (@NonNull CSEElement dependentCSEElement : cseElement.getOutputs()) {
						SymbolicValue dependentSymbolicValue = baseSymbolicEvaluationEnvironment.basicGetSymbolicValue(dependentCSEElement);
						if (dependentSymbolicValue == null) {
							assert dependentCSEElement.getSafeCSE() != null;
						}
						else {
							for (@NonNull TypedElement dependentTypedElement : dependentCSEElement.getElements()) {
								String incompatibility2 = installActiveTypedElementAncestry(dependentTypedElement, dependentSymbolicValue, null);
								if (incompatibility2 != null) {
									return incompatibility2;
								}
							}
						}
					}
					if (activeTypedElements.size() > 1) {
						Collections.sort(activeTypedElements, cseAnalysis.getTypedElementHeightComparator());
					}
				}
			}
		}
		return null;
	}

	/**
	 * Given that executedExpression executes, register any consequent refinements for
	 * any refinedTypedElements or affectedVariables that ensure this execution.
	 *
	 * </br>'and' term executes implies other term true
	 * </br>'or' term executes implies other term false
	 * </br>'implies' guard executes implies other term true
	 * </br>IfExp.then executes implies condition is precisely true
	 * </br>IfExp.else executes implies condition is precisely false
	 * </br>navigation sources not-invalid
	 * </br>unsafe navigation sources not-null
	 * </br>LoopExp executes implies source not-invalid, not empty
	 * </br>IterateExp executes implies result inirializer not invalid
	 * </br>loop sources not-invalid
	 * </br>unsafe loop sources not-null
	 *
	 * FIXME and/and2 etc distinctions, noting tht the symbolic analysis occurs before mandatory commutation.
	 *
	 * FIXME Is some of this functionality duplicating checkPreconditions() ?
	 * No here we can impose constraints rather than discover them.
	 */
	private @Nullable String installActiveTypedElementDescendants(@NonNull TypedElement activeTypedElement) {
		VariableDeclaration activeVariable = null;
		if (activeTypedElement instanceof VariableExp) {
			activeVariable = PivotUtil.getReferredVariable((VariableExp)activeTypedElement);
		}
		else if (activeTypedElement instanceof VariableDeclaration) {
			activeVariable = (VariableDeclaration)activeTypedElement;
		}
		if (activeVariable != null) {				// ?? is this now redundant
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
		if (containingTypedElement instanceof IfExp) {
			return installIfExpPathConstraints(activeTypedElement, (IfExp)containingTypedElement);
		}
		else if (containingTypedElement instanceof LetExp) {
			return installLetExpPathConstraints(activeTypedElement, (LetExp)containingTypedElement);
		}
		else if (containingTypedElement instanceof LoopExp) {			// Before OperationCallExp
			return installLoopExpPathConstraints(activeTypedElement, (LoopExp)containingTypedElement);
		}
		else if (containingTypedElement instanceof OperationCallExp) {
			OperationCallExp operationCallExp = (OperationCallExp)containingTypedElement;
			Operation operation = PivotUtil.getReferredOperation(operationCallExp);
			LibraryOperation implementation = (LibraryOperation)operation.getImplementation();
			if (implementation == null) {
				implementation = (LibraryOperation)((PivotMetamodelManager)environmentFactory.getMetamodelManager()).getImplementation(operation);
			}
			return implementation.installPathConstraints(this, activeTypedElement, operationCallExp);
		}
		else if (containingTypedElement instanceof NavigationCallExp) {
			return installNavigationCallExpPathConstraints(activeTypedElement, (NavigationCallExp)containingTypedElement);
		}
		else {
			return null;
		}
	}

	private @Nullable String installIfExpPathConstraints(@NonNull TypedElement activeTypedElement, @NonNull IfExp ifExp) {
		OCLExpression ownedCondition = PivotUtil.getOwnedCondition(ifExp);
		if (activeTypedElement == ifExp.getOwnedThen()) {
			SymbolicValue refinedSymbolicValue = getKnownValue(Boolean.TRUE);
			return installRefinement(ownedCondition, refinedSymbolicValue);
		}
		else if (activeTypedElement == ifExp.getOwnedElse()) {
			SymbolicValue refinedSymbolicValue = getKnownValue(Boolean.FALSE);
			return installRefinement(ownedCondition, refinedSymbolicValue);
		}
		else {
			assert activeTypedElement == ownedCondition;
			return null;
		}
	}

	private @Nullable String installLetExpPathConstraints(@NonNull TypedElement activeTypedElement, @NonNull LetExp letExp) {
//		Variable ownedVariable = letExp.getOwnedVariable();
//		if (activeTypedElement == letExp.getOwnedIn()) {
//			SymbolicValue baseSymbolicValue = baseSymbolicEvaluationEnvironment.getSymbolicValue(ownedVariable);
//			return installRefinement(ownedVariable, refinedSymbolicValue);
//		}
//		else {
//			assert activeTypedElement == ownedVariable;
			return null;		// A let-variable may be invalid
//		}
	}

	private @Nullable String installLoopExpPathConstraints(@NonNull TypedElement activeTypedElement, @NonNull LoopExp loopExp) {
	//	Iteration iteration = PivotUtil.getReferredIteration(loopExp);
	//	assert !iteration.isIsValidating();		// XXX FIXME forAll isValidating
		@Nullable String incompatibility = null;
		@NonNull OCLExpression ownedSource = PivotUtil.getOwnedSource(loopExp);
		@NonNull OCLExpression ownedBody = PivotUtil.getOwnedBody(loopExp);
		if (activeTypedElement != ownedSource) {
			SymbolicValue baseSymbolicValue = baseSymbolicEvaluationEnvironment.getSymbolicValue(ownedSource);
			SymbolicValue refinedSymbolicValue = AbstractSymbolicRefinedValue.createExceptValue(baseSymbolicValue, ValueUtil.INVALID_VALUE);
			if (!loopExp.isIsSafe()) {
				refinedSymbolicValue = AbstractSymbolicRefinedValue.createExceptValue(baseSymbolicValue, null);
			}
			if (activeTypedElement == ownedBody) {
				refinedSymbolicValue = AbstractSymbolicRefinedValue.createNotEmpty(refinedSymbolicValue);
			}
			incompatibility = installRefinement(ownedSource, refinedSymbolicValue);
		}
		if ((incompatibility == null) && (loopExp instanceof IterateExp)) {
			IterateExp iterateExp = (IterateExp)loopExp;
			VariableDeclaration ownedResult = PivotUtil.getOwnedResult(iterateExp);
			if (activeTypedElement != ownedResult) {
				SymbolicValue baseSymbolicValue = baseSymbolicEvaluationEnvironment.getSymbolicValue(ownedResult);
				SymbolicValue refinedSymbolicValue = AbstractSymbolicRefinedValue.createExceptValue(baseSymbolicValue, ValueUtil.INVALID_VALUE);
				incompatibility = installRefinement(ownedResult, refinedSymbolicValue);
			}
		}
		if ((incompatibility == null) && (activeTypedElement != ownedBody)) {
		//	SymbolicValue baseSymbolicValue = baseSymbolicEvaluationEnvironment.getSymbolicValue(ownedBody);
		//	incompatibility = installRefinement(ownedBody, refinedSymbolicValue);
		}
		return incompatibility;
	}

	private @Nullable String installNavigationCallExpPathConstraints(@NonNull TypedElement activeTypedElement, @NonNull NavigationCallExp navigationCallExp) {
		assert activeTypedElement == PivotUtil.getOwnedSource(navigationCallExp);	// No extra corrolaries of ownedSource execution
		return null;
	}

//	private @Nullable String installOperationCallExpPathConstraints(@NonNull TypedElement activeTypedElement, @NonNull OperationCallExp operationCallExp) {
	//	OCLExpression refinedExpression = null;						// Source/condition expression that can be refined
	//	Boolean refinedBooleanValue1 = null;							//  by a simple Boolean	value
	//	boolean mayBeInvalid = false;								//   or a strictness prohibition on
	//	boolean mayBeNull = false;									//    null or invalid sources
	//	if (operationCallExp.isIsSafe()) {
	//		mayBeNull = true;
	//	}
	//	OCLExpression ownedSource = PivotUtil.getOwnedSource(operationCallExp);
	//	refinedExpression = ownedSource;
//		Operation operation = PivotUtil.getReferredOperation(operationCallExp);
//		LibraryOperation implementation = (LibraryOperation)operation.getImplementation();
//		return implementation.installPathConstraints(this, activeTypedElement, operationCallExp);
/*		if (activeTypedElement == operationCallExp.getOwnedSource()) {
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
		/ *if (argumentsSize == 0) {
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
		else* / if (argumentsSize == 1) {
			OCLExpression argument = ownedArguments.get(0);
			if (activeTypedElement == argument) {
				if ((implementation instanceof BooleanAndOperation) || (implementation instanceof BooleanAndOperation2)) {
			//		refinedBooleanValue1 = Boolean.TRUE;
				}
				else if ((implementation instanceof BooleanImpliesOperation) || (implementation instanceof BooleanImpliesOperation2)) {
			//		refinedBooleanValue1 = Boolean.TRUE;
				}
				else if ((implementation instanceof BooleanOrOperation) || (implementation instanceof BooleanOrOperation2)) {
			//		refinedBooleanValue1 = Boolean.FALSE;
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



		String incompatibility = null;
	//	if (ownedSource != activeTypedElement) {
			SymbolicValue refinedSymbolicValue1 = null;
			if (refinedBooleanValue1 != null) {
				refinedSymbolicValue1 = getKnownValue(refinedBooleanValue1);
			}
			else if (!mayBeInvalid || !mayBeNull) {
				SymbolicValue baseSymbolicValue = baseSymbolicEvaluationEnvironment.getSymbolicValue(refinedExpression);
				refinedSymbolicValue1 = baseSymbolicValue;
				if (!mayBeInvalid) {
					refinedSymbolicValue1 = AbstractSymbolicRefinedValue.createExceptValue(refinedSymbolicValue1, ValueUtil.INVALID_VALUE);
				}
				if (!mayBeNull) {
					refinedSymbolicValue1 = AbstractSymbolicRefinedValue.createExceptValue(refinedSymbolicValue1, null);
				}
				if (refinedSymbolicValue1 == baseSymbolicValue) {
					refinedSymbolicValue1 = null;
				}
			}
			if (refinedSymbolicValue1 != null) {
				incompatibility = installRefinement(refinedExpression, refinedSymbolicValue1);
			}
	//	}

			if (ownedSource != activeTypedElement) {
				SymbolicValue baseSymbolicValue = baseSymbolicEvaluationEnvironment.getSymbolicValue(ownedSource);
				boolean specialCased = true;
				if (argumentsSize == 1) {	// source of binary op execution; refine to avoid argument invalidity
					OCLExpression argument = ownedArguments.get(0);
					LibraryFeature implementation = operation.getImplementation();
					if ((implementation instanceof BooleanAndOperation) || (implementation instanceof BooleanAndOperation2)) {
						SymbolicValue refinedSymbolicValue = getKnownValue(Boolean.TRUE);
						incompatibility = installRefinement(argument, refinedSymbolicValue);
					}
					else if ((implementation instanceof BooleanImpliesOperation) || (implementation instanceof BooleanImpliesOperation2)) {
						SymbolicValue refinedSymbolicValue = getKnownValue(Boolean.FALSE);
						incompatibility = installRefinement(argument, refinedSymbolicValue);
					}
					else if ((implementation instanceof BooleanOrOperation) || (implementation instanceof BooleanOrOperation2)) {
						SymbolicValue refinedSymbolicValue = getKnownValue(Boolean.FALSE);
						incompatibility = installRefinement(argument, refinedSymbolicValue);
					}
					else if (implementation instanceof OclAnyOclAsTypeOperation) {
						SymbolicValue refinedSymbolicValue = AbstractSymbolicRefinedValue.createExceptValue(baseSymbolicValue, ValueUtil.INVALID_VALUE);
						refinedSymbolicValue = AbstractSymbolicRefinedValue.createExceptValue(refinedSymbolicValue, null);
						incompatibility = installRefinement(ownedSource, refinedSymbolicValue);
					}
					else if (implementation instanceof OclAnyOclIsInStateOperation) {
						SymbolicValue refinedSymbolicValue = AbstractSymbolicRefinedValue.createExceptValue(baseSymbolicValue, ValueUtil.INVALID_VALUE);
						refinedSymbolicValue = AbstractSymbolicRefinedValue.createExceptValue(refinedSymbolicValue, null);
						incompatibility = installRefinement(ownedSource, refinedSymbolicValue);
					}
					else if (implementation instanceof OclAnyEqualOperation) {
						SymbolicValue refinedSymbolicValue = AbstractSymbolicRefinedValue.createExceptValue(baseSymbolicValue, ValueUtil.INVALID_VALUE);
						incompatibility = installRefinement(ownedSource, refinedSymbolicValue);
					}
					else if (implementation instanceof OclAnyNotEqualOperation) {
						SymbolicValue refinedSymbolicValue = AbstractSymbolicRefinedValue.createExceptValue(baseSymbolicValue, ValueUtil.INVALID_VALUE);
						incompatibility = installRefinement(ownedSource, refinedSymbolicValue);
					}
					else {
						specialCased = false;
					}
				}
				if (!specialCased && parameter.isIsRequired()) {
					SymbolicValue refinedSymbolicValue2 = baseSymbolicValue;
					if (!isValidating && baseSymbolicValue.mayBeInvalid()) {
						refinedSymbolicValue2 = AbstractSymbolicRefinedValue.createExceptValue(refinedSymbolicValue2, ValueUtil.INVALID_VALUE);
					}
					if (baseSymbolicValue.mayBeNull()) {
						refinedSymbolicValue2 = AbstractSymbolicRefinedValue.createExceptValue(refinedSymbolicValue2, null);
					}
					if (refinedSymbolicValue2 != baseSymbolicValue) {
						incompatibility = installRefinement(argument, refinedSymbolicValue2);
						if (incompatibility != null) {
							break;
						}
					}
				}







				SymbolicValue refinedSymbolicValue1 = null;
				if (refinedBooleanValue1 != null) {
					refinedSymbolicValue1 = getKnownValue(refinedBooleanValue1);
				}
				else if (!mayBeInvalid || !mayBeNull) {
					SymbolicValue baseSymbolicValue = baseSymbolicEvaluationEnvironment.getSymbolicValue(refinedExpression);
					refinedSymbolicValue1 = baseSymbolicValue;
					if (!mayBeInvalid) {
						refinedSymbolicValue1 = AbstractSymbolicRefinedValue.createExceptValue(refinedSymbolicValue1, ValueUtil.INVALID_VALUE);
					}
					if (!mayBeNull) {
						refinedSymbolicValue1 = AbstractSymbolicRefinedValue.createExceptValue(refinedSymbolicValue1, null);
					}
					if (refinedSymbolicValue1 == baseSymbolicValue) {
						refinedSymbolicValue1 = null;
					}
				}
				if (refinedSymbolicValue1 != null) {
					incompatibility = installRefinement(refinedExpression, refinedSymbolicValue1);
				}
			}




		if (incompatibility == null) {
			int i = 0;
			for (@NonNull Parameter parameter : PivotUtil.getOwnedParameters(operation)) {
				OCLExpression argument = ownedArguments.get(i);
				if (argument != activeTypedElement) {
					SymbolicValue baseSymbolicValue = baseSymbolicEvaluationEnvironment.getSymbolicValue(argument);
					boolean specialCased = true;
					if (argumentsSize == 1) {	// argument of binary op executes; refine source to avoid source invalidity
						LibraryFeature implementation = operation.getImplementation();
						if ((implementation instanceof BooleanAndOperation) || (implementation instanceof BooleanAndOperation2)) {
							SymbolicValue refinedSymbolicValue = getKnownValue(Boolean.TRUE);
							incompatibility = installRefinement(ownedSource, refinedSymbolicValue);
						}
						else if ((implementation instanceof BooleanImpliesOperation) || (implementation instanceof BooleanImpliesOperation2)) {
							SymbolicValue refinedSymbolicValue = getKnownValue(Boolean.TRUE);
							incompatibility = installRefinement(ownedSource, refinedSymbolicValue);
						}
						else if ((implementation instanceof BooleanOrOperation) || (implementation instanceof BooleanOrOperation2)) {
							SymbolicValue refinedSymbolicValue = getKnownValue(Boolean.FALSE);
							incompatibility = installRefinement(ownedSource, refinedSymbolicValue);
						}
						else if (implementation instanceof OclAnyOclAsTypeOperation) {
							SymbolicValue refinedSymbolicValue = AbstractSymbolicRefinedValue.createExceptValue(baseSymbolicValue, ValueUtil.INVALID_VALUE);
							incompatibility = installRefinement(ownedSource, refinedSymbolicValue);
						}
						else if (implementation instanceof OclAnyOclIsInStateOperation) {
							SymbolicValue refinedSymbolicValue = AbstractSymbolicRefinedValue.createExceptValue(baseSymbolicValue, ValueUtil.INVALID_VALUE);
							incompatibility = installRefinement(ownedSource, refinedSymbolicValue);
						}
						else if (implementation instanceof OclAnyEqualOperation) {
							SymbolicValue refinedSymbolicValue = AbstractSymbolicRefinedValue.createExceptValue(baseSymbolicValue, ValueUtil.INVALID_VALUE);
							incompatibility = installRefinement(ownedSource, refinedSymbolicValue);
						}
						else if (implementation instanceof OclAnyNotEqualOperation) {
							SymbolicValue refinedSymbolicValue = AbstractSymbolicRefinedValue.createExceptValue(baseSymbolicValue, ValueUtil.INVALID_VALUE);
							incompatibility = installRefinement(ownedSource, refinedSymbolicValue);
						}
						else {
							specialCased = false;
						}
					}
					if (!specialCased && parameter.isIsRequired()) {
						SymbolicValue refinedSymbolicValue2 = baseSymbolicValue;
						if (!isValidating && baseSymbolicValue.mayBeInvalid()) {
							refinedSymbolicValue2 = AbstractSymbolicRefinedValue.createExceptValue(refinedSymbolicValue2, ValueUtil.INVALID_VALUE);
						}
						if (baseSymbolicValue.mayBeNull()) {
							refinedSymbolicValue2 = AbstractSymbolicRefinedValue.createExceptValue(refinedSymbolicValue2, null);
						}
						if (refinedSymbolicValue2 != baseSymbolicValue) {
							incompatibility = installRefinement(argument, refinedSymbolicValue2);
							if (incompatibility != null) {
								break;
							}
						}
					}
				}
				i++;
			}
		}
		return incompatibility; */
//	}

	public @Nullable String installRefinement(@NonNull TypedElement refinedExpression, @NonNull SymbolicValue refinedSymbolicValue) {
		CSEElement cseElement = cseAnalysis.getCSEElement(refinedExpression);
		refinedCSEElements.add(cseElement);
		SymbolicValue appliedSymbolicValue = setSymbolicValue(refinedExpression, cseElement, refinedSymbolicValue);
		if (SymbolicAnalysis.HYPOTHESIS.isActive()) {
			SymbolicAnalysis.HYPOTHESIS.println("    set-path: " + SymbolicUtil.printPath(refinedExpression, false) + " as: " + SymbolicUtil.printValue(appliedSymbolicValue));
		}
		return appliedSymbolicValue.asIncompatibility();
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

	private @NonNull SymbolicValue setSymbolicValue(@NonNull TypedElement typedElement, @NonNull CSEElement cseElement, @NonNull SymbolicValue refinedSymbolicValue) {
		SymbolicValue oldSymbolicValue = cseElement2symbolicValue.get(cseElement);
		assert oldSymbolicValue != null;
		SymbolicValue newSymbolicValue = refinedSymbolicValue;
		if ((newSymbolicValue != oldSymbolicValue) && !newSymbolicValue.equals(oldSymbolicValue)) {
			newSymbolicValue = refinedSymbolicValue.asRefinementOf(oldSymbolicValue, typedElement);
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
		SymbolicValue resultValue = setSymbolicValue(typedElement, cseElement, symbolicValue);
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
		SymbolicValue writeValue;
		try {
			writeValue = symbolicEvaluationVisitor.symbolicEvaluate(typedElement);
		}
		catch (InvalidValueException e) {
			Object boxedValue = idResolver.boxedValueOf(e);
			writeValue = getKnownValue(boxedValue);
		}
		SymbolicValue newSymbolicValue = setSymbolicValue(typedElement, writeValue, "eval");
		return newSymbolicValue.asIncompatibility();
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
