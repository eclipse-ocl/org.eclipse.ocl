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
import org.eclipse.jdt.annotation.Nullable;

/**
 * @noimplement This interface is not intended to be implemented by clients.
 */
public interface CGModelVisitor<R>
{
	/**
	 * Returns an object which is an instance of the given class
	 * associated with this object. Returns <code>null</code> if
	 * no such object can be found.
	 *
	 * @param adapter the adapter class to look up
	 * @return an object of the given class,
	 *    or <code>null</code> if this object does not
	 *    have an adapter for the given class
	 */
	@Nullable <A> A getAdapter(@NonNull Class<A> adapter);

	/**
	 * Return the result of visiting a visitable for which no more specific pivot type method
	 * is available.
	 */
	R visiting(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGElement visitable);

	R visitCGAccumulator(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGAccumulator object);
	R visitCGAssertNonNullExp(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGAssertNonNullExp object);
	R visitCGBoolean(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGBoolean object);
	R visitCGBoxExp(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGBoxExp object);
	R visitCGBuiltInIterationCallExp(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGBuiltInIterationCallExp object);
	R visitCGCachedOperation(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGCachedOperation object);
	R visitCGCachedOperationCallExp(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGCachedOperationCallExp object);
	R visitCGCallExp(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGCallExp object);
	R visitCGCallable(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGCallable object);
	R visitCGCastExp(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGCastExp object);
	R visitCGCatchExp(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGCatchExp object);
	R visitCGClass(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGClass object);
	R visitCGCollectionExp(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGCollectionExp object);
	R visitCGCollectionPart(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGCollectionPart object);
	R visitCGConstant(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGConstant object);
	R visitCGConstantExp(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGConstantExp object);
	R visitCGConstraint(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGConstraint object);
	R visitCGEcoreClassShadowExp(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGEcoreClassShadowExp object);
	R visitCGEcoreDataTypeShadowExp(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGEcoreDataTypeShadowExp object);
	R visitCGEcoreExp(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGEcoreExp object);
	R visitCGEcoreOperation(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGEcoreOperation object);
	R visitCGEcoreOperationCallExp(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGEcoreOperationCallExp object);
	R visitCGEcoreOppositePropertyCallExp(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGEcoreOppositePropertyCallExp object);
	R visitCGEcorePropertyCallExp(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGEcorePropertyCallExp object);
	R visitCGElement(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGElement object);
	R visitCGElementId(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGElementId object);
	R visitCGExecutorCompositionProperty(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGExecutorCompositionProperty object);
	R visitCGExecutorNavigationProperty(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGExecutorNavigationProperty object);
	R visitCGExecutorOperation(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGExecutorOperation object);
	R visitCGExecutorOperationCallExp(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGExecutorOperationCallExp object);
	R visitCGExecutorOppositeProperty(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGExecutorOppositeProperty object);
	R visitCGExecutorOppositePropertyCallExp(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGExecutorOppositePropertyCallExp object);
	R visitCGExecutorProperty(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGExecutorProperty object);
	R visitCGExecutorPropertyCallExp(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGExecutorPropertyCallExp object);
	R visitCGExecutorShadowPart(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGExecutorShadowPart object);
	R visitCGExecutorType(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGExecutorType object);
	R visitCGFinalVariable(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGFinalVariable object);
	R visitCGGuardExp(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGGuardExp object);
	R visitCGIfExp(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGIfExp object);
	R visitCGInteger(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGInteger object);
	R visitCGInvalid(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGInvalid object);
	R visitCGIsEqual2Exp(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGIsEqual2Exp object);
	R visitCGIsEqualExp(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGIsEqualExp object);
	R visitCGIsInvalidExp(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGIsInvalidExp object);
	R visitCGIsKindOfExp(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGIsKindOfExp object);
	R visitCGIsUndefinedExp(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGIsUndefinedExp object);
	R visitCGIterationCallExp(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGIterationCallExp object);
	R visitCGIterator(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGIterator object);
	R visitCGLetExp(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGLetExp object);
	R visitCGLibraryIterateCallExp(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGLibraryIterateCallExp object);
	R visitCGLibraryIterationCallExp(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGLibraryIterationCallExp object);
	R visitCGLibraryOperation(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGLibraryOperation object);
	R visitCGLibraryOperationCallExp(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGLibraryOperationCallExp object);
	R visitCGLibraryPropertyCallExp(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGLibraryPropertyCallExp object);
	R visitCGLocalVariable(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGLocalVariable object);
	R visitCGMapExp(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGMapExp object);
	R visitCGMapPart(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGMapPart object);
	R visitCGModel(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGModel object);
	R visitCGNamedElement(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGNamedElement object);
	R visitCGNativeOperation(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGNativeOperation object);
	R visitCGNativeOperationCallExp(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGNativeOperationCallExp object);
	R visitCGNativeProperty(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGNativeProperty object);
	R visitCGNativePropertyCallExp(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGNativePropertyCallExp object);
	R visitCGNavigationCallExp(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGNavigationCallExp object);
	R visitCGNull(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGNull object);
	R visitCGNumber(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGNumber object);
	R visitCGOperation(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGOperation object);
	R visitCGOperationCallExp(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGOperationCallExp object);
	R visitCGOppositePropertyCallExp(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGOppositePropertyCallExp object);
	R visitCGPackage(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGPackage object);
	R visitCGParameter(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGParameter object);
	R visitCGProperty(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGProperty object);
	R visitCGPropertyCallExp(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGPropertyCallExp object);
	R visitCGReal(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGReal object);
	R visitCGSettableVariable(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGSettableVariable object);
	R visitCGShadowExp(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGShadowExp object);
	R visitCGShadowPart(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGShadowPart object);
	R visitCGString(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGString object);
	R visitCGTemplateParameterExp(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGTemplateParameterExp object);
	R visitCGText(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGText object);
	R visitCGThrowExp(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGThrowExp object);
	R visitCGTupleExp(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGTupleExp object);
	R visitCGTuplePart(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGTuplePart object);
	R visitCGTuplePartCallExp(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGTuplePartCallExp object);
	R visitCGTypeExp(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGTypeExp object);
	R visitCGTypeId(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGTypeId object);
	R visitCGTypedElement(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGTypedElement object);
	R visitCGUnboxExp(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGUnboxExp object);
	R visitCGUnlimited(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGUnlimited object);
	R visitCGValuedElement(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGValuedElement object);
	R visitCGVariable(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGVariable object);
	R visitCGVariableExp(org.eclipse.ocl.examples.codegen.cgmodel.@NonNull CGVariableExp object);
}
