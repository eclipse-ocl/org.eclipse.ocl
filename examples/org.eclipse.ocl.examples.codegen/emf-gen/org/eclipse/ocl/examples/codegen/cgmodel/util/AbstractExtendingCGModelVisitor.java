/*******************************************************************************
 * Copyright (c) 2013, 2019 CEA LIST and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * 
 * Contributors:
 *   E.D.Willink(CEA LIST) - Initial API and implementation
 * 
 * </copyright>
 *
 * This code is auto-generated
 * from: org.eclipse.ocl.examples.codegen/model/cgmodel.genmodel
 *
 * Only the copyright statement is editable.
 *******************************************************************************/
package	org.eclipse.ocl.examples.codegen.cgmodel.util;

import org.eclipse.jdt.annotation.NonNull;

/**
 * An AbstractExtendingCGModelVisitor provides a default implementation for each
 * visitXxx method that delegates to the visitYyy method of the first
 * super class, (or transitively its first super class' first super class
 * until a non-interface super-class is found). In the absence of any
 * suitable first super class, the method delegates to visiting().
 */
public abstract class AbstractExtendingCGModelVisitor<R, C>
	extends AbstractCGModelVisitor<R, C>
	implements CGModelVisitor<R>
{
	/**
	 * Initializes me with an initial value for my result.
	 *
	 * @param context my initial result value
	 */
	protected AbstractExtendingCGModelVisitor(C context) {
		super(context);
	}

	@Override
	public R visitCGAccumulator(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGAccumulator object) {
		return visitCGIterator(object);
	}

	@Override
	public R visitCGAssertNonNullExp(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGAssertNonNullExp object) {
		return visitCGCallExp(object);
	}

	@Override
	public R visitCGBoolean(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGBoolean object) {
		return visitCGConstant(object);
	}

	@Override
	public R visitCGBoxExp(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGBoxExp object) {
		return visitCGCallExp(object);
	}

	@Override
	public R visitCGBuiltInIterationCallExp(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGBuiltInIterationCallExp object) {
		return visitCGIterationCallExp(object);
	}

	@Override
	public R visitCGCachedOperation(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGCachedOperation object) {
		return visitCGOperation(object);
	}

	@Override
	public R visitCGCachedOperationCallExp(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGCachedOperationCallExp object) {
		return visitCGOperationCallExp(object);
	}

	@Override
	public R visitCGCallExp(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGCallExp object) {
		return visitCGValuedElement(object);
	}

	@Override
	public R visitCGCallable(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGCallable object) {
		return visitCGValuedElement(object);
	}

	@Override
	public R visitCGCastExp(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGCastExp object) {
		return visitCGCallExp(object);
	}

	@Override
	public R visitCGCatchExp(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGCatchExp object) {
		return visitCGCallExp(object);
	}

	@Override
	public R visitCGClass(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGClass object) {
		return visitCGNamedElement(object);
	}

	@Override
	public R visitCGCollectionExp(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGCollectionExp object) {
		return visitCGValuedElement(object);
	}

	@Override
	public R visitCGCollectionPart(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGCollectionPart object) {
		return visitCGValuedElement(object);
	}

	@Override
	public R visitCGConstant(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGConstant object) {
		return visitCGValuedElement(object);
	}

	@Override
	public R visitCGConstantExp(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGConstantExp object) {
		return visitCGValuedElement(object);
	}

	@Override
	public R visitCGConstraint(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGConstraint object) {
		return visitCGCallable(object);
	}

	@Override
	public R visitCGEcoreClassShadowExp(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGEcoreClassShadowExp object) {
		return visitCGShadowExp(object);
	}

	@Override
	public R visitCGEcoreDataTypeShadowExp(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGEcoreDataTypeShadowExp object) {
		return visitCGShadowExp(object);
	}

	@Override
	public R visitCGEcoreExp(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGEcoreExp object) {
		return visitCGCallExp(object);
	}

	@Override
	public R visitCGEcoreOperation(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGEcoreOperation object) {
		return visitCGOperation(object);
	}

	@Override
	public R visitCGEcoreOperationCallExp(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGEcoreOperationCallExp object) {
		return visitCGOperationCallExp(object);
	}

	@Override
	public R visitCGEcoreOppositePropertyCallExp(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGEcoreOppositePropertyCallExp object) {
		return visitCGOppositePropertyCallExp(object);
	}

	@Override
	public R visitCGEcorePropertyCallExp(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGEcorePropertyCallExp object) {
		return visitCGPropertyCallExp(object);
	}

	@Override
	public R visitCGElement(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGElement object) {
		return visiting(object);
	}

	@Override
	public R visitCGElementId(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGElementId object) {
		return visitCGConstant(object);
	}

	@Override
	public R visitCGExecutorCompositionProperty(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGExecutorCompositionProperty object) {
		return visitCGExecutorProperty(object);
	}

	@Override
	public R visitCGExecutorNavigationProperty(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGExecutorNavigationProperty object) {
		return visitCGExecutorProperty(object);
	}

	@Override
	public R visitCGExecutorOperation(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGExecutorOperation object) {
		return visitCGValuedElement(object);
	}

	@Override
	public R visitCGExecutorOperationCallExp(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGExecutorOperationCallExp object) {
		return visitCGOperationCallExp(object);
	}

	@Override
	public R visitCGExecutorOppositeProperty(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGExecutorOppositeProperty object) {
		return visitCGExecutorProperty(object);
	}

	@Override
	public R visitCGExecutorOppositePropertyCallExp(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGExecutorOppositePropertyCallExp object) {
		return visitCGOppositePropertyCallExp(object);
	}

	@Override
	public R visitCGExecutorProperty(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGExecutorProperty object) {
		return visitCGValuedElement(object);
	}

	@Override
	public R visitCGExecutorPropertyCallExp(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGExecutorPropertyCallExp object) {
		return visitCGPropertyCallExp(object);
	}

	@Override
	public R visitCGExecutorShadowPart(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGExecutorShadowPart object) {
		return visitCGExecutorProperty(object);
	}

	@Override
	public R visitCGExecutorType(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGExecutorType object) {
		return visitCGValuedElement(object);
	}

	@Override
	public R visitCGFinalVariable(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGFinalVariable object) {
		return visitCGVariable(object);
	}

	@Override
	public R visitCGGuardExp(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGGuardExp object) {
		return visitCGCallExp(object);
	}

	@Override
	public R visitCGIfExp(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGIfExp object) {
		return visitCGValuedElement(object);
	}

	@Override
	public R visitCGInteger(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGInteger object) {
		return visitCGNumber(object);
	}

	@Override
	public R visitCGInvalid(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGInvalid object) {
		return visitCGConstant(object);
	}

	@Override
	public R visitCGIsEqual2Exp(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGIsEqual2Exp object) {
		return visitCGCallExp(object);
	}

	@Override
	public R visitCGIsEqualExp(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGIsEqualExp object) {
		return visitCGCallExp(object);
	}

	@Override
	public R visitCGIsInvalidExp(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGIsInvalidExp object) {
		return visitCGCallExp(object);
	}

	@Override
	public R visitCGIsKindOfExp(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGIsKindOfExp object) {
		return visitCGCallExp(object);
	}

	@Override
	public R visitCGIsUndefinedExp(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGIsUndefinedExp object) {
		return visitCGCallExp(object);
	}

	@Override
	public R visitCGIterationCallExp(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGIterationCallExp object) {
		return visitCGCallExp(object);
	}

	@Override
	public R visitCGIterator(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGIterator object) {
		return visitCGParameter(object);
	}

	@Override
	public R visitCGLetExp(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGLetExp object) {
		return visitCGValuedElement(object);
	}

	@Override
	public R visitCGLibraryIterateCallExp(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGLibraryIterateCallExp object) {
		return visitCGLibraryIterationCallExp(object);
	}

	@Override
	public R visitCGLibraryIterationCallExp(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGLibraryIterationCallExp object) {
		return visitCGIterationCallExp(object);
	}

	@Override
	public R visitCGLibraryOperation(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGLibraryOperation object) {
		return visitCGOperation(object);
	}

	@Override
	public R visitCGLibraryOperationCallExp(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGLibraryOperationCallExp object) {
		return visitCGOperationCallExp(object);
	}

	@Override
	public R visitCGLibraryPropertyCallExp(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGLibraryPropertyCallExp object) {
		return visitCGPropertyCallExp(object);
	}

	@Override
	public R visitCGLocalVariable(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGLocalVariable object) {
		return visitCGVariable(object);
	}

	@Override
	public R visitCGMapExp(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGMapExp object) {
		return visitCGValuedElement(object);
	}

	@Override
	public R visitCGMapPart(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGMapPart object) {
		return visitCGValuedElement(object);
	}

	@Override
	public R visitCGModel(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGModel object) {
		return visitCGNamedElement(object);
	}

	@Override
	public R visitCGNamedElement(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGNamedElement object) {
		return visitCGElement(object);
	}

	@Override
	public R visitCGNativeOperation(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGNativeOperation object) {
		return visitCGOperation(object);
	}

	@Override
	public R visitCGNativeOperationCallExp(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGNativeOperationCallExp object) {
		return visitCGOperationCallExp(object);
	}

	@Override
	public R visitCGNativeProperty(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGNativeProperty object) {
		return visitCGProperty(object);
	}

	@Override
	public R visitCGNativePropertyCallExp(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGNativePropertyCallExp object) {
		return visitCGPropertyCallExp(object);
	}

	@Override
	public R visitCGNavigationCallExp(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGNavigationCallExp object) {
		return visitCGCallExp(object);
	}

	@Override
	public R visitCGNull(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGNull object) {
		return visitCGConstant(object);
	}

	@Override
	public R visitCGNumber(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGNumber object) {
		return visitCGConstant(object);
	}

	@Override
	public R visitCGOperation(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGOperation object) {
		return visitCGCallable(object);
	}

	@Override
	public R visitCGOperationCallExp(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGOperationCallExp object) {
		return visitCGCallExp(object);
	}

	@Override
	public R visitCGOppositePropertyCallExp(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGOppositePropertyCallExp object) {
		return visitCGNavigationCallExp(object);
	}

	@Override
	public R visitCGPackage(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGPackage object) {
		return visitCGNamedElement(object);
	}

	@Override
	public R visitCGParameter(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGParameter object) {
		return visitCGVariable(object);
	}

	@Override
	public R visitCGProperty(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGProperty object) {
		return visitCGValuedElement(object);
	}

	@Override
	public R visitCGPropertyCallExp(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGPropertyCallExp object) {
		return visitCGNavigationCallExp(object);
	}

	@Override
	public R visitCGReal(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGReal object) {
		return visitCGNumber(object);
	}

	@Override
	public R visitCGSettableVariable(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGSettableVariable object) {
		return visitCGVariable(object);
	}

	@Override
	public R visitCGShadowExp(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGShadowExp object) {
		return visitCGValuedElement(object);
	}

	@Override
	public R visitCGShadowPart(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGShadowPart object) {
		return visitCGValuedElement(object);
	}

	@Override
	public R visitCGString(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGString object) {
		return visitCGConstant(object);
	}

	@Override
	public R visitCGTemplateParameterExp(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGTemplateParameterExp object) {
		return visitCGValuedElement(object);
	}

	@Override
	public R visitCGText(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGText object) {
		return visitCGConstant(object);
	}

	@Override
	public R visitCGThrowExp(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGThrowExp object) {
		return visitCGCallExp(object);
	}

	@Override
	public R visitCGTupleExp(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGTupleExp object) {
		return visitCGValuedElement(object);
	}

	@Override
	public R visitCGTuplePart(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGTuplePart object) {
		return visitCGValuedElement(object);
	}

	@Override
	public R visitCGTuplePartCallExp(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGTuplePartCallExp object) {
		return visitCGPropertyCallExp(object);
	}

	@Override
	public R visitCGTypeExp(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGTypeExp object) {
		return visitCGValuedElement(object);
	}

	@Override
	public R visitCGTypeId(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGTypeId object) {
		return visitCGElementId(object);
	}

	@Override
	public R visitCGTypedElement(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGTypedElement object) {
		return visitCGNamedElement(object);
	}

	@Override
	public R visitCGUnboxExp(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGUnboxExp object) {
		return visitCGCallExp(object);
	}

	@Override
	public R visitCGUnlimited(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGUnlimited object) {
		return visitCGConstant(object);
	}

	@Override
	public R visitCGValuedElement(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGValuedElement object) {
		return visitCGTypedElement(object);
	}

	@Override
	public R visitCGVariable(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGVariable object) {
		return visitCGValuedElement(object);
	}

	@Override
	public R visitCGVariableExp(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGVariableExp object) {
		return visitCGValuedElement(object);
	}
}
