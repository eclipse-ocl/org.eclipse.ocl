/*******************************************************************************
 * Copyright (c) 2016, 2022 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.utilities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.BooleanLiteralExp;
import org.eclipse.ocl.pivot.CallExp;
import org.eclipse.ocl.pivot.CollectionRange;
import org.eclipse.ocl.pivot.CollectionType;
import org.eclipse.ocl.pivot.CompleteClass;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.IfExp;
import org.eclipse.ocl.pivot.IntegerLiteralExp;
import org.eclipse.ocl.pivot.InvalidLiteralExp;
import org.eclipse.ocl.pivot.IterateExp;
import org.eclipse.ocl.pivot.Iteration;
import org.eclipse.ocl.pivot.IteratorExp;
import org.eclipse.ocl.pivot.LetExp;
import org.eclipse.ocl.pivot.LetVariable;
import org.eclipse.ocl.pivot.NavigationCallExp;
import org.eclipse.ocl.pivot.NullLiteralExp;
import org.eclipse.ocl.pivot.OCLExpression;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.OperationCallExp;
import org.eclipse.ocl.pivot.OppositePropertyCallExp;
import org.eclipse.ocl.pivot.ParameterVariable;
import org.eclipse.ocl.pivot.PivotFactory;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.PropertyCallExp;
import org.eclipse.ocl.pivot.RealLiteralExp;
import org.eclipse.ocl.pivot.SelfType;
import org.eclipse.ocl.pivot.StandardLibrary;
import org.eclipse.ocl.pivot.StringLiteralExp;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.TypeExp;
import org.eclipse.ocl.pivot.TypedElement;
import org.eclipse.ocl.pivot.UnlimitedNaturalLiteralExp;
import org.eclipse.ocl.pivot.Variable;
import org.eclipse.ocl.pivot.VariableDeclaration;
import org.eclipse.ocl.pivot.WildcardType;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.complete.StandardLibraryInternal;
import org.eclipse.ocl.pivot.internal.manager.Orphanage;
import org.eclipse.ocl.pivot.internal.manager.TemplateParameterSubstitutionVisitor;
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal;
import org.eclipse.ocl.pivot.library.LibraryIterationOrOperation;
import org.eclipse.ocl.pivot.values.TemplateParameterSubstitutions;

import com.google.common.collect.Lists;

/**
 * PivotHelper provides helper routines to assist creation of Pivot model elements with respect to
 * the prevailing EnvironmentFactory.
 */
public class PivotHelper extends PivotUtil
{
	protected final @NonNull EnvironmentFactoryInternal environmentFactory;
	protected final @NonNull StandardLibraryInternal standardLibrary;
	@Deprecated /* change to lazy */
	private final @NonNull MetamodelManager metamodelManager;

	public PivotHelper(@NonNull EnvironmentFactory environmentFactory) {
		this.environmentFactory = (EnvironmentFactoryInternal) environmentFactory;
		this.standardLibrary = this.environmentFactory.getStandardLibrary();
		this.metamodelManager = environmentFactory.getMetamodelManager();			// FIXME avoid this cast;
	}

	public @NonNull BooleanLiteralExp createBooleanLiteralExp(boolean booleanSymbol) {
		BooleanLiteralExp asBoolean = PivotFactory.eINSTANCE.createBooleanLiteralExp();
		asBoolean.setBooleanSymbol(booleanSymbol);
		asBoolean.setType(standardLibrary.getBooleanType());
		asBoolean.setIsRequired(true);
		return asBoolean;
	}

	/**
	 * @since 1.4
	 */
	public @NonNull OCLExpression createCoercionCallExp(@NonNull OCLExpression asExpression, @NonNull Operation coercion) {
		if (asExpression instanceof IntegerLiteralExp) {
			IntegerLiteralExp asIntegerLiteralExp = (IntegerLiteralExp)asExpression;
			Number integerSymbol = asIntegerLiteralExp.getIntegerSymbol();
			if (integerSymbol.longValue() >= 0) {
				org.eclipse.ocl.pivot.Class integerType = standardLibrary.getIntegerType();
				Operation asCoercion = NameUtil.getNameable(integerType.getOwnedOperations(), "toUnlimitedNatural");
				if (coercion == asCoercion) {
					return createUnlimitedNaturalLiteralExp(integerSymbol);
				}
			}
		}
		OperationCallExp asCoercionCallExp = PivotFactory.eINSTANCE.createOperationCallExp();
		asCoercionCallExp.setOwnedSource(asExpression);
		asCoercionCallExp.setReferredOperation(coercion);
		asCoercionCallExp.setType(coercion.getType());
		asCoercionCallExp.setIsRequired(coercion.isIsRequired());
		return asCoercionCallExp;
	}

	public @NonNull CollectionRange createCollectionRange(@NonNull OCLExpression asFirst, @NonNull OCLExpression asLast) {
		CollectionRange collectionRange = PivotFactory.eINSTANCE.createCollectionRange();
		collectionRange.setOwnedFirst(asFirst);
		collectionRange.setOwnedLast(asLast);
		collectionRange.setType(standardLibrary.getIntegerType());
		collectionRange.setIsRequired(true);
		return collectionRange;
	}

	public @NonNull IfExp createIfExp(@NonNull OCLExpression asCondition, @NonNull OCLExpression asThen, @NonNull OCLExpression asElse) {
		Type commonType = metamodelManager.getCommonType(getType(asThen), TemplateParameterSubstitutions.EMPTY,
			getType(asElse), TemplateParameterSubstitutions.EMPTY);
		IfExp asIf = PivotFactory.eINSTANCE.createIfExp();
		asIf.setOwnedCondition(asCondition);
		asIf.setOwnedThen(asThen);
		asIf.setOwnedElse(asElse);
		asIf.setType(commonType);
		asIf.setIsRequired(asThen.isIsRequired() && asElse.isIsRequired());
		return asIf;
	}

	public @NonNull IntegerLiteralExp createIntegerLiteralExp(@NonNull Number integerSymbol) {
		IntegerLiteralExp asInteger = PivotFactory.eINSTANCE.createIntegerLiteralExp();
		asInteger.setIntegerSymbol(integerSymbol);
		asInteger.setType(standardLibrary.getIntegerType());
		asInteger.setIsRequired(true);
		return asInteger;
	}

	public @NonNull InvalidLiteralExp createInvalidExpression(/*Object object, String boundMessage, Throwable e*/) {
		InvalidLiteralExp invalidLiteralExp = PivotFactory.eINSTANCE.createInvalidLiteralExp();
		invalidLiteralExp.setType(standardLibrary.getOclInvalidType());
		//		invalidLiteralExp.setObject(object);
		//		invalidLiteralExp.setReason(boundMessage);
		//		invalidLiteralExp.setThrowable(e);
		return invalidLiteralExp;
	}

	public @NonNull IterateExp createIterateExp(@NonNull OCLExpression asSource, @NonNull Iteration asIteration, @NonNull List<@NonNull ? extends Variable> asIterators, @NonNull Variable asResult, @NonNull OCLExpression asBody) {
		IterateExp asCallExp = PivotFactory.eINSTANCE.createIterateExp();
		asCallExp.setReferredIteration(asIteration);
		asCallExp.setName(asIteration.getName());
		asCallExp.setOwnedSource(asSource);
		asCallExp.getOwnedIterators().addAll(asIterators);
		asCallExp.setOwnedResult(asResult);
		asCallExp.getOwnedBodies().add(asBody);
		setOperationReturnType(asCallExp, asIteration);
		return asCallExp;
	}

	public @NonNull IteratorExp createIteratorExp(@NonNull OCLExpression asSource, @NonNull Iteration asIteration, @NonNull List<@NonNull ? extends Variable> asIterators, @NonNull OCLExpression asBody) {
		IteratorExp asCallExp = PivotFactory.eINSTANCE.createIteratorExp();
		asCallExp.setReferredIteration(asIteration);
		asCallExp.setName(asIteration.getName());
		asCallExp.setOwnedSource(asSource);
		asCallExp.getOwnedIterators().addAll(asIterators);
		asCallExp.setOwnedBody(asBody);
		setOperationReturnType(asCallExp, asIteration);
		return asCallExp;
	}

	// XXX specialize return type
	public @NonNull NavigationCallExp createNavigationCallExp(@NonNull OCLExpression asSource, @NonNull Property asProperty) {
		NavigationCallExp asNavigationCallExp;
		if (asProperty.isIsImplicit()) {
			OppositePropertyCallExp asCallExp = PivotFactory.eINSTANCE.createOppositePropertyCallExp();
			asCallExp.setReferredProperty(asProperty.getOpposite());
			asNavigationCallExp = asCallExp;
		}
		else {
			PropertyCallExp asCallExp = PivotFactory.eINSTANCE.createPropertyCallExp();
			asCallExp.setReferredProperty(asProperty);
			asNavigationCallExp = asCallExp;
		}
		asNavigationCallExp.setName(asProperty.getName());
		asNavigationCallExp.setOwnedSource(asSource);
		asNavigationCallExp.setType(asProperty.getType());
		asNavigationCallExp.setIsRequired(asProperty.isIsRequired());
		return asNavigationCallExp;
	}

	public @NonNull NullLiteralExp createNullLiteralExp() {
		NullLiteralExp asNull = PivotFactory.eINSTANCE.createNullLiteralExp();
		asNull.setType(standardLibrary.getOclVoidType());
		asNull.setIsRequired(false);
		return asNull;
	}

	public @NonNull OperationCallExp createOperationCallExp(@NonNull OCLExpression asSourceExpression, @NonNull String opName, @NonNull OCLExpression... asArguments) {
		Type asType = ClassUtil.requireNonNull(asSourceExpression.getType());
		CompleteClass completeClass = environmentFactory.getCompleteModel().getCompleteClass(asType);
		int argumentCount = asArguments != null ? asArguments.length : 0;
		int bestMatches = -1;
		Operation bestOperation = null;
		for (@NonNull Operation asOperation : completeClass.getOperations(FeatureFilter.SELECT_NON_STATIC, opName)) {
			List<@NonNull ? extends TypedElement> asParameters = ClassUtil.nullFree(asOperation.getOwnedParameters());
			if ((asParameters.size() == argumentCount) && (asArguments != null)) {
				int exactMatches = 0;
				boolean gotOne = true;
				for (int i = 0; i < argumentCount; i++) {
					Type asParameterType = ClassUtil.requireNonNull(asParameters.get(i).getType());
					OCLExpression asArgument = asArguments[i];
					Type asArgumentType = asArgument.getType();
					if (asParameterType instanceof SelfType) {
						if (asArgumentType.conformsTo(standardLibrary, asType) && asType.conformsTo(standardLibrary, asArgumentType)) {
							exactMatches++;
						}
					}
					else {
						if (!asArgumentType.conformsTo(standardLibrary, asParameterType)) {
							gotOne = false;
							break;
						}
						if (asParameterType.conformsTo(standardLibrary, asArgumentType)) {
							exactMatches++;
						}
					}
				}
				if (gotOne) {
					if (exactMatches > bestMatches) {
						bestMatches = exactMatches;
						bestOperation = asOperation;
					}
					else if (exactMatches > bestMatches) {
						bestOperation = null;
					}
				}
			}
		}
		if (bestMatches < 0) {
			throw new IllegalStateException("No match found for " + opName);
		}
		if (bestOperation == null) {
			throw new IllegalStateException("Ambiguous match found for " + opName);
		}
		return createOperationCallExp(asSourceExpression, bestOperation, asArguments != null ? Lists.newArrayList(asArguments) : null);
	}

	public @NonNull OperationCallExp createOperationCallExp(@Nullable OCLExpression asSourceExpression, @NonNull Operation asOperation, @Nullable List<@NonNull OCLExpression> asArguments) {
		OperationCallExp asOperationCallExp = PivotFactory.eINSTANCE.createOperationCallExp();
		asOperationCallExp.setOwnedSource(asSourceExpression);
		asOperationCallExp.setReferredOperation(asOperation);
		asOperationCallExp.setName(asOperation.getName());
		if (asArguments != null) {
			asOperationCallExp.getOwnedArguments().addAll(asArguments);
		}
		setOperationReturnType(asOperationCallExp, asOperation);
		return asOperationCallExp;
	}

	public @NonNull RealLiteralExp createRealLiteralExp(@NonNull Number realSymbol) {
		RealLiteralExp asReal = PivotFactory.eINSTANCE.createRealLiteralExp();
		asReal.setRealSymbol(realSymbol);
		asReal.setType(standardLibrary.getRealType());
		asReal.setIsRequired(true);
		return asReal;
	}

	public @NonNull StringLiteralExp createStringLiteralExp(@NonNull String stringSymbol) {
		StringLiteralExp asString = PivotFactory.eINSTANCE.createStringLiteralExp();
		asString.setStringSymbol(stringSymbol);
		asString.setType(standardLibrary.getStringType());
		asString.setIsRequired(true);
		return asString;
	}

	public @NonNull TypeExp createTypeExp(@NonNull Type type) {		// FIXME Class
		assert type instanceof org.eclipse.ocl.pivot.Class;		// Not TemplateParameter
		TypeExp asTypeExp = PivotFactory.eINSTANCE.createTypeExp();
		asTypeExp.setIsRequired(true);
		asTypeExp.setReferredType(type);
		asTypeExp.setName(type.getName());
		Type metaType = environmentFactory.getMetaclass(type);
		asTypeExp.setType(metaType);
		asTypeExp.setTypeValue(type);
		return asTypeExp;
	}

	public @NonNull UnlimitedNaturalLiteralExp createUnlimitedNaturalLiteralExp(@NonNull Number unlimitedNaturalSymbol) {
		UnlimitedNaturalLiteralExp asUnlimitedNatural = PivotFactory.eINSTANCE.createUnlimitedNaturalLiteralExp();
		asUnlimitedNatural.setUnlimitedNaturalSymbol(unlimitedNaturalSymbol);
		asUnlimitedNatural.setType(standardLibrary.getUnlimitedNaturalType());
		asUnlimitedNatural.setIsRequired(true);
		return asUnlimitedNatural;
	}

	/**
	 * @since 7.0
	 */
	public @NonNull WildcardType createWildcardType(org.eclipse.ocl.pivot.@Nullable Class lowerBound, org.eclipse.ocl.pivot.@Nullable Class upperBound) {		// FIXME move to PivotHelper
		WildcardType wildcardType = Orphanage.getOrphanWildcardType(environmentFactory.getOrphanage());// PivotFactory.eINSTANCE.createWildcardType();
	//	wildcardType.setName("?");			// Name is not significant
	//	wildcardType.setLowerBound(lowerBound != null ? lowerBound : standardLibrary.getOclAnyType());
	//	wildcardType.setUpperBound(upperBound != null ? upperBound : standardLibrary.getOclVoidType());
		return wildcardType;
	}

	public org.eclipse.ocl.pivot.@NonNull Class getDataTypeClass() {
		return ClassUtil.requireNonNull(metamodelManager.getASClass(TypeId.DATA_TYPE_NAME));
	}

	public @NonNull Property getDataTypeValueProperty() {
		return ClassUtil.requireNonNull(NameUtil.getNameable(getDataTypeClass().getOwnedProperties(), PivotConstants.DATA_TYPE_VALUE_NAME));
	}

	public @NonNull EnvironmentFactory getEnvironmentFactory() {
		return environmentFactory;
	}

	/**
	 * @since 7.0
	 */
	protected @NonNull MetamodelManager getMetamodelManager() {
		return metamodelManager;
	}

	public @NonNull StandardLibrary getStandardLibrary() {
		return standardLibrary;
	}

	/**
	 * Rewrite asTree and all its descendants to replace all "?." and "?->" navigations by their safe counterparts.
	 * @since 1.3
	 */
	public void rewriteSafeNavigations(@NonNull Element asTree) {
		//
		//	Locate all unsafe calls first to avoid CME from concurrent locate/rewrite.
		//
		List<@NonNull CallExp> unsafeCallExps = null;
		if (asTree instanceof CallExp) {
			unsafeCallExps = rewriteUnsafeCallExp_Gather(unsafeCallExps, (CallExp)asTree);
		}
		for (TreeIterator<EObject> tit = asTree.eAllContents(); tit.hasNext(); ) {
			EObject eObject = tit.next();
			if (eObject instanceof CallExp) {
				unsafeCallExps = rewriteUnsafeCallExp_Gather(unsafeCallExps, (CallExp)eObject);
			}
		}
		//
		//	Rewrite the unsafe calls
		//
		if (unsafeCallExps != null) {
			org.eclipse.ocl.pivot.Class oclAnyType = standardLibrary.getOclAnyType();
			Operation oclEqualsOperation = NameUtil.getNameable(oclAnyType.getOwnedOperations(), "=");
			assert oclEqualsOperation != null;
			org.eclipse.ocl.pivot.Class collectionType = standardLibrary.getCollectionType();
			Operation excludingOperation = NameUtil.getNameable(collectionType.getOwnedOperations(), "excluding");
			assert excludingOperation != null;
			for (CallExp unsafeCallExp : unsafeCallExps) {
				OCLExpression source = unsafeCallExp.getOwnedSource();
				assert source != null;
				if (source.getType() instanceof CollectionType) {
					rewriteUnsafeCollectionCallExp(metamodelManager, excludingOperation, unsafeCallExp);
				}
				else {
					rewriteUnsafeObjectCallExp(metamodelManager, oclEqualsOperation, unsafeCallExp);
				}
			}
		}
	}

	private @Nullable List<@NonNull CallExp> rewriteUnsafeCallExp_Gather(@Nullable List<@NonNull CallExp> unsafeCallExps, @NonNull CallExp callExp) {
		OCLExpression source = callExp.getOwnedSource();
		if ((source != null) && callExp.isIsSafe()) {
			if (unsafeCallExps == null) {
				unsafeCallExps = new ArrayList<@NonNull CallExp>();
			}
			unsafeCallExps.add(callExp);
		}
		return unsafeCallExps;
	}

	private void rewriteUnsafeCollectionCallExp(@NonNull MetamodelManager metamodelManager, @NonNull Operation excludingOperation, @NonNull CallExp unsafeCollectionCallExp) {
		unsafeCollectionCallExp.setIsSafe(false);
		EObject eContainer = unsafeCollectionCallExp.eContainer();
		EReference eContainmentFeature = unsafeCollectionCallExp.eContainmentFeature();
		resetContainer(unsafeCollectionCallExp);
		//
		OCLExpression nullExpression = createNullLiteralExp();
		OCLExpression safeCollectionCallExp = createOperationCallExp(unsafeCollectionCallExp, excludingOperation, Collections.singletonList(nullExpression));
		//
		eContainer.eSet(eContainmentFeature, safeCollectionCallExp);
	}

	/**
	 * @since 1.4
	 */
	private  void rewriteUnsafeObjectCallExp(@NonNull MetamodelManager metamodelManager, @NonNull Operation oclEqualsOperation, @NonNull CallExp unsafeObjectCallExp) {
		unsafeObjectCallExp.setIsSafe(false);
		EObject eContainer = unsafeObjectCallExp.eContainer();
		EReference eContainmentFeature = unsafeObjectCallExp.eContainmentFeature();
		resetContainer(unsafeObjectCallExp);
		OCLExpression oldSourceExpression = unsafeObjectCallExp.getOwnedSource();
		assert oldSourceExpression != null;
		//
		LetVariable unsafeSourceVariable = createLetVariable("unsafe", oldSourceExpression);
		OCLExpression unsafeSourceExpression1 = createVariableExp(unsafeSourceVariable);
		unsafeObjectCallExp.setOwnedSource(unsafeSourceExpression1);
		//
		OCLExpression unsafeSourceExpression2 = createVariableExp(unsafeSourceVariable);
		OCLExpression nullExpression = createNullLiteralExp();
		OCLExpression isUnsafeExpression = createOperationCallExp(unsafeSourceExpression2, oclEqualsOperation, Collections.singletonList(nullExpression));
		//
		OCLExpression thenExpression = createNullLiteralExp();
		OCLExpression safeObjectCallExp = createIfExp(isUnsafeExpression, thenExpression, unsafeObjectCallExp);
		//
		LetExp safeExp = createLetExp(unsafeSourceVariable, safeObjectCallExp);
		//
		eContainer.eSet(eContainmentFeature, safeExp);
	}

	/**
	 * @since 1.4
	 */
	public void setContextVariable(@NonNull ExpressionInOCL pivotSpecification, @NonNull String selfVariableName, @Nullable Type contextType, @Nullable Type contextInstance) {
		Variable contextVariable = pivotSpecification.getOwnedContext();
		if (contextVariable == null) {
			@NonNull ParameterVariable nonNullContextVariable = PivotFactory.eINSTANCE.createParameterVariable();
			contextVariable = nonNullContextVariable;
			pivotSpecification.setOwnedContext(contextVariable);
			if (contextType == null) {
				contextType = standardLibrary.getOclVoidType();
			}
		}
		refreshName(contextVariable, selfVariableName);
		setType(contextVariable, contextType, contextVariable.isIsRequired(), contextInstance);
	}

	/**
	 * Set the operation/iteration return type and nullity in the asCallExp. This may involve creating a specialization
	 * of the operation/iteration return type and may require the use of a TemplateParameterSubstitutionHelper to
	 * compute inadequately modelled unique/ordered/size/nullity.
	 *
	 * @since 1.4
	 */
	public void setOperationReturnType(@NonNull CallExp asCallExp, @NonNull Operation asOperation) {
		OCLExpression asSourceExpression = asCallExp.getOwnedSource();
		Type sourceType;
		if (asSourceExpression != null) {
			sourceType = asSourceExpression.getType();
		}
		else {
			sourceType = null;
		}
		Type returnType = null;
		Type formalType = asOperation.getType();
		boolean returnIsRequired = asOperation.isIsRequired();
		Object returnValue = null;			// Currently always a Type - see Bug 577902
		if ((formalType != null) && (sourceType != null)) {
			returnType = TemplateParameterSubstitutionVisitor.specializeType(formalType, asCallExp, environmentFactory, sourceType, null);
		}
		//
		//	The flattening of collect() and consequently implicit-collect is not modelled accurately.
		//	Other library operations have subtle non-null/size computations.
		//	Therefore allow an operation-specific overrides to adjust the regular functionality above.
		//
		LibraryIterationOrOperation implementation = (LibraryIterationOrOperation)asOperation.getImplementation();
		if (implementation != null) {		// Library classes have implementations, Complete OCL classes may be recursive
			returnType = implementation.resolveReturnType(environmentFactory, asCallExp, returnType);
			returnIsRequired = implementation.resolveReturnNullity(environmentFactory, asCallExp, returnIsRequired);
			returnValue = implementation.resolveReturnValue(environmentFactory, asCallExp);
		//	assert (returnValue == null) || ((returnType != null) && returnType.getName().equals(((EObject)returnValue).eClass().getName()));	// Not valid once AnyType/VoidType get involved
		}
		else {
			assert !asOperation.isIsTypeof();			// typeof return declaration must now be realized by an operation override
		}
		setType(asCallExp, returnType, returnIsRequired, (Type)returnValue);
	}

	public void setType(@NonNull OCLExpression asExpression, Type type, boolean isRequired, @Nullable Type typeValue) {
		setType(asExpression, type, isRequired);
		Type primaryTypeValue = typeValue != null ? metamodelManager.getPrimaryType(typeValue) : null;
		if (primaryTypeValue != asExpression.getTypeValue()) {
			asExpression.setTypeValue(primaryTypeValue);
		}
	}

	/**
	 * @since 1.4
	 */
	public void setType(@NonNull VariableDeclaration asVariable, Type type, boolean isRequired, @Nullable Type typeValue) {
		setType(asVariable, type, isRequired);
		Type primaryTypeValue = typeValue != null ? metamodelManager.getPrimaryType(typeValue) : null;
		if (primaryTypeValue != asVariable.getTypeValue()) {
			asVariable.setTypeValue(primaryTypeValue);
		}
	}

	public void setType(@NonNull TypedElement asTypedElement, Type type, boolean isRequired) {
		Type primaryType = type != null ? metamodelManager.getPrimaryType(type) : null;
		if (primaryType != asTypedElement.getType()) {
			asTypedElement.setType(primaryType);
		}
		boolean wasRequired = asTypedElement.isIsRequired();
		if (wasRequired != isRequired) {
			asTypedElement.setIsRequired(isRequired);
		}
		if (primaryType != null) {
			debugWellContainedness(primaryType);
		}
	}
}