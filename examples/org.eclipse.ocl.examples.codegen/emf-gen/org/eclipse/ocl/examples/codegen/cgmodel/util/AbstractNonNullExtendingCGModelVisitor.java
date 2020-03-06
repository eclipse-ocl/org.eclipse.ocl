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
 * An AbstractExtendingNonNullCGModelVisitor provides a default implementation for each
 * visitXxx method that delegates to the visitYyy method of the first
 * super class, (or transitively its first super class first super class
 * until a non-interface super-class is found). In the absence of any
 * suitable first super class, the method delegates to visiting().
 * The return is annotated as @NonNull.
 *
 * @deprecated Explicit 'NonNull' functionality is obsolete with Java 8 @NonNull annotations.
 */
 @Deprecated
public abstract class AbstractNonNullExtendingCGModelVisitor<R, C>
	extends AbstractCGModelVisitor<R, C>
	implements CGModelVisitor<R>
{
	/**
	 * Initializes me with an initial value for my result.
	 *
	 * @param context my initial result value
	 */
	protected AbstractNonNullExtendingCGModelVisitor(C context) {
		super(context);
	}

	/**
	 * Perform a visit to the specified visitable.
	 *
	 * @param visitable a visitable
	 * @return the non-null result of visiting it
	 */
	@Override
	public @NonNull R visit(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGElement visitable) {
		R result = visitable.accept(this);
		if (result == null) {
			throw new IllegalStateException("null return from non-null " + getClass().getName());
		}
		return result;
	}

	@Override
	public @NonNull R visitCGAccumulator(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGAccumulator object) {
		return visitCGIterator(object);
	}

	@Override
	public @NonNull R visitCGAssertNonNullExp(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGAssertNonNullExp object) {
		return visitCGCallExp(object);
	}

	@Override
	public @NonNull R visitCGBoolean(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGBoolean object) {
		return visitCGConstant(object);
	}

	@Override
	public @NonNull R visitCGBoxExp(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGBoxExp object) {
		return visitCGCallExp(object);
	}

	@Override
	public @NonNull R visitCGBuiltInIterationCallExp(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGBuiltInIterationCallExp object) {
		return visitCGIterationCallExp(object);
	}

	@Override
	public @NonNull R visitCGCachedOperation(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGCachedOperation object) {
		return visitCGOperation(object);
	}

	@Override
	public @NonNull R visitCGCachedOperationCallExp(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGCachedOperationCallExp object) {
		return visitCGOperationCallExp(object);
	}

	@Override
	public @NonNull R visitCGCallExp(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGCallExp object) {
		return visitCGValuedElement(object);
	}

	@Override
	public @NonNull R visitCGCallable(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGCallable object) {
		return visitCGValuedElement(object);
	}

	@Override
	public @NonNull R visitCGCastExp(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGCastExp object) {
		return visitCGCallExp(object);
	}

	@Override
	public @NonNull R visitCGCatchExp(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGCatchExp object) {
		return visitCGCallExp(object);
	}

	@Override
	public @NonNull R visitCGClass(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGClass object) {
		return visitCGNamedElement(object);
	}

	@Override
	public @NonNull R visitCGCollectionExp(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGCollectionExp object) {
		return visitCGValuedElement(object);
	}

	@Override
	public @NonNull R visitCGCollectionPart(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGCollectionPart object) {
		return visitCGValuedElement(object);
	}

	@Override
	public @NonNull R visitCGConstant(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGConstant object) {
		return visitCGValuedElement(object);
	}

	@Override
	public @NonNull R visitCGConstantExp(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGConstantExp object) {
		return visitCGValuedElement(object);
	}

	@Override
	public @NonNull R visitCGConstraint(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGConstraint object) {
		return visitCGCallable(object);
	}

	@Override
	public @NonNull R visitCGEcoreClassShadowExp(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGEcoreClassShadowExp object) {
		return visitCGShadowExp(object);
	}

	@Override
	public @NonNull R visitCGEcoreDataTypeShadowExp(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGEcoreDataTypeShadowExp object) {
		return visitCGShadowExp(object);
	}

	@Override
	public @NonNull R visitCGEcoreExp(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGEcoreExp object) {
		return visitCGCallExp(object);
	}

	@Override
	public @NonNull R visitCGEcoreOperation(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGEcoreOperation object) {
		return visitCGOperation(object);
	}

	@Override
	public @NonNull R visitCGEcoreOperationCallExp(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGEcoreOperationCallExp object) {
		return visitCGOperationCallExp(object);
	}

	@Override
	public @NonNull R visitCGEcoreOppositePropertyCallExp(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGEcoreOppositePropertyCallExp object) {
		return visitCGOppositePropertyCallExp(object);
	}

	@Override
	public @NonNull R visitCGEcorePropertyCallExp(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGEcorePropertyCallExp object) {
		return visitCGPropertyCallExp(object);
	}

	@Override
	public @NonNull R visitCGElement(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGElement object) {
		return visiting(object);
	}

	@Override
	public @NonNull R visitCGElementId(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGElementId object) {
		return visitCGConstant(object);
	}

	@Override
	public @NonNull R visitCGExecutorCompositionProperty(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGExecutorCompositionProperty object) {
		return visitCGExecutorProperty(object);
	}

	@Override
	public @NonNull R visitCGExecutorNavigationProperty(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGExecutorNavigationProperty object) {
		return visitCGExecutorProperty(object);
	}

	@Override
	public @NonNull R visitCGExecutorOperation(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGExecutorOperation object) {
		return visitCGValuedElement(object);
	}

	@Override
	public @NonNull R visitCGExecutorOperationCallExp(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGExecutorOperationCallExp object) {
		return visitCGOperationCallExp(object);
	}

	@Override
	public @NonNull R visitCGExecutorOppositeProperty(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGExecutorOppositeProperty object) {
		return visitCGExecutorProperty(object);
	}

	@Override
	public @NonNull R visitCGExecutorOppositePropertyCallExp(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGExecutorOppositePropertyCallExp object) {
		return visitCGOppositePropertyCallExp(object);
	}

	@Override
	public @NonNull R visitCGExecutorProperty(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGExecutorProperty object) {
		return visitCGValuedElement(object);
	}

	@Override
	public @NonNull R visitCGExecutorPropertyCallExp(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGExecutorPropertyCallExp object) {
		return visitCGPropertyCallExp(object);
	}

	@Override
	public @NonNull R visitCGExecutorShadowPart(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGExecutorShadowPart object) {
		return visitCGExecutorProperty(object);
	}

	@Override
	public @NonNull R visitCGExecutorType(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGExecutorType object) {
		return visitCGValuedElement(object);
	}

	@Override
	public @NonNull R visitCGFinalVariable(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGFinalVariable object) {
		return visitCGVariable(object);
	}

	@Override
	public @NonNull R visitCGGuardExp(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGGuardExp object) {
		return visitCGCallExp(object);
	}

	@Override
	public @NonNull R visitCGIfExp(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGIfExp object) {
		return visitCGValuedElement(object);
	}

	@Override
	public @NonNull R visitCGInteger(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGInteger object) {
		return visitCGNumber(object);
	}

	@Override
	public @NonNull R visitCGInvalid(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGInvalid object) {
		return visitCGConstant(object);
	}

	@Override
	public @NonNull R visitCGIsEqual2Exp(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGIsEqual2Exp object) {
		return visitCGCallExp(object);
	}

	@Override
	public @NonNull R visitCGIsEqualExp(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGIsEqualExp object) {
		return visitCGCallExp(object);
	}

	@Override
	public @NonNull R visitCGIsInvalidExp(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGIsInvalidExp object) {
		return visitCGCallExp(object);
	}

	@Override
	public @NonNull R visitCGIsKindOfExp(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGIsKindOfExp object) {
		return visitCGCallExp(object);
	}

	@Override
	public @NonNull R visitCGIsUndefinedExp(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGIsUndefinedExp object) {
		return visitCGCallExp(object);
	}

	@Override
	public @NonNull R visitCGIterationCallExp(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGIterationCallExp object) {
		return visitCGCallExp(object);
	}

	@Override
	public @NonNull R visitCGIterator(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGIterator object) {
		return visitCGParameter(object);
	}

	@Override
	public @NonNull R visitCGLetExp(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGLetExp object) {
		return visitCGValuedElement(object);
	}

	@Override
	public @NonNull R visitCGLibraryIterateCallExp(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGLibraryIterateCallExp object) {
		return visitCGLibraryIterationCallExp(object);
	}

	@Override
	public @NonNull R visitCGLibraryIterationCallExp(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGLibraryIterationCallExp object) {
		return visitCGIterationCallExp(object);
	}

	@Override
	public @NonNull R visitCGLibraryOperation(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGLibraryOperation object) {
		return visitCGOperation(object);
	}

	@Override
	public @NonNull R visitCGLibraryOperationCallExp(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGLibraryOperationCallExp object) {
		return visitCGOperationCallExp(object);
	}

	@Override
	public @NonNull R visitCGLibraryPropertyCallExp(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGLibraryPropertyCallExp object) {
		return visitCGPropertyCallExp(object);
	}

	@Override
	public @NonNull R visitCGLocalVariable(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGLocalVariable object) {
		return visitCGVariable(object);
	}

	@Override
	public @NonNull R visitCGMapExp(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGMapExp object) {
		return visitCGValuedElement(object);
	}

	@Override
	public @NonNull R visitCGMapPart(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGMapPart object) {
		return visitCGValuedElement(object);
	}

	@Override
	public @NonNull R visitCGModel(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGModel object) {
		return visitCGNamedElement(object);
	}

	@Override
	public @NonNull R visitCGNamedElement(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGNamedElement object) {
		return visitCGElement(object);
	}

	@Override
	public @NonNull R visitCGNativeOperation(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGNativeOperation object) {
		return visitCGOperation(object);
	}

	@Override
	public @NonNull R visitCGNativeOperationCallExp(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGNativeOperationCallExp object) {
		return visitCGOperationCallExp(object);
	}

	@Override
	public @NonNull R visitCGNativeProperty(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGNativeProperty object) {
		return visitCGProperty(object);
	}

	@Override
	public @NonNull R visitCGNativePropertyCallExp(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGNativePropertyCallExp object) {
		return visitCGPropertyCallExp(object);
	}

	@Override
	public @NonNull R visitCGNavigationCallExp(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGNavigationCallExp object) {
		return visitCGCallExp(object);
	}

	@Override
	public @NonNull R visitCGNull(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGNull object) {
		return visitCGConstant(object);
	}

	@Override
	public @NonNull R visitCGNumber(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGNumber object) {
		return visitCGConstant(object);
	}

	@Override
	public @NonNull R visitCGOperation(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGOperation object) {
		return visitCGCallable(object);
	}

	@Override
	public @NonNull R visitCGOperationCallExp(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGOperationCallExp object) {
		return visitCGCallExp(object);
	}

	@Override
	public @NonNull R visitCGOppositePropertyCallExp(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGOppositePropertyCallExp object) {
		return visitCGNavigationCallExp(object);
	}

	@Override
	public @NonNull R visitCGPackage(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGPackage object) {
		return visitCGNamedElement(object);
	}

	@Override
	public @NonNull R visitCGParameter(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGParameter object) {
		return visitCGVariable(object);
	}

	@Override
	public @NonNull R visitCGProperty(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGProperty object) {
		return visitCGValuedElement(object);
	}

	@Override
	public @NonNull R visitCGPropertyCallExp(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGPropertyCallExp object) {
		return visitCGNavigationCallExp(object);
	}

	@Override
	public @NonNull R visitCGReal(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGReal object) {
		return visitCGNumber(object);
	}

	@Override
	public @NonNull R visitCGSettableVariable(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGSettableVariable object) {
		return visitCGVariable(object);
	}

	@Override
	public @NonNull R visitCGShadowExp(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGShadowExp object) {
		return visitCGValuedElement(object);
	}

	@Override
	public @NonNull R visitCGShadowPart(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGShadowPart object) {
		return visitCGValuedElement(object);
	}

	@Override
	public @NonNull R visitCGString(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGString object) {
		return visitCGConstant(object);
	}

	@Override
	public @NonNull R visitCGTemplateParameterExp(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGTemplateParameterExp object) {
		return visitCGValuedElement(object);
	}

	@Override
	public @NonNull R visitCGText(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGText object) {
		return visitCGConstant(object);
	}

	@Override
	public @NonNull R visitCGThrowExp(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGThrowExp object) {
		return visitCGCallExp(object);
	}

	@Override
	public @NonNull R visitCGTupleExp(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGTupleExp object) {
		return visitCGValuedElement(object);
	}

	@Override
	public @NonNull R visitCGTuplePart(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGTuplePart object) {
		return visitCGValuedElement(object);
	}

	@Override
	public @NonNull R visitCGTuplePartCallExp(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGTuplePartCallExp object) {
		return visitCGPropertyCallExp(object);
	}

	@Override
	public @NonNull R visitCGTypeExp(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGTypeExp object) {
		return visitCGValuedElement(object);
	}

	@Override
	public @NonNull R visitCGTypeId(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGTypeId object) {
		return visitCGElementId(object);
	}

	@Override
	public @NonNull R visitCGTypedElement(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGTypedElement object) {
		return visitCGNamedElement(object);
	}

	@Override
	public @NonNull R visitCGUnboxExp(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGUnboxExp object) {
		return visitCGCallExp(object);
	}

	@Override
	public @NonNull R visitCGUnlimited(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGUnlimited object) {
		return visitCGConstant(object);
	}

	@Override
	public @NonNull R visitCGValuedElement(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGValuedElement object) {
		return visitCGTypedElement(object);
	}

	@Override
	public @NonNull R visitCGVariable(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGVariable object) {
		return visitCGValuedElement(object);
	}

	@Override
	public @NonNull R visitCGVariableExp(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGVariableExp object) {
		return visitCGValuedElement(object);
	}

	/**
	 * Return the result of visiting a visitable for which no more specific pivot type method
	 * is available.
	 */
	@Override
	public abstract @NonNull R visiting(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGElement visitable);
}
