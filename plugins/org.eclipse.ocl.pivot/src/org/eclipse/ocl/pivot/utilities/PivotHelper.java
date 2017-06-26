/*******************************************************************************
 * Copyright (c) 2016, 2017 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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
import org.eclipse.ocl.pivot.CollectionItem;
import org.eclipse.ocl.pivot.CollectionLiteralExp;
import org.eclipse.ocl.pivot.CollectionLiteralPart;
import org.eclipse.ocl.pivot.CollectionRange;
import org.eclipse.ocl.pivot.CollectionType;
import org.eclipse.ocl.pivot.Comment;
import org.eclipse.ocl.pivot.CompleteClass;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.IfExp;
import org.eclipse.ocl.pivot.Import;
import org.eclipse.ocl.pivot.IntegerLiteralExp;
import org.eclipse.ocl.pivot.InvalidLiteralExp;
import org.eclipse.ocl.pivot.IterateExp;
import org.eclipse.ocl.pivot.Iteration;
import org.eclipse.ocl.pivot.IteratorExp;
import org.eclipse.ocl.pivot.IteratorVariable;
import org.eclipse.ocl.pivot.LetExp;
import org.eclipse.ocl.pivot.LetVariable;
import org.eclipse.ocl.pivot.MapLiteralExp;
import org.eclipse.ocl.pivot.MapLiteralPart;
import org.eclipse.ocl.pivot.MapType;
import org.eclipse.ocl.pivot.Namespace;
import org.eclipse.ocl.pivot.NavigationCallExp;
import org.eclipse.ocl.pivot.NullLiteralExp;
import org.eclipse.ocl.pivot.OCLExpression;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.OperationCallExp;
import org.eclipse.ocl.pivot.Package;
import org.eclipse.ocl.pivot.Parameter;
import org.eclipse.ocl.pivot.ParameterVariable;
import org.eclipse.ocl.pivot.PivotFactory;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.RealLiteralExp;
import org.eclipse.ocl.pivot.ResultVariable;
import org.eclipse.ocl.pivot.SelfType;
import org.eclipse.ocl.pivot.ShadowExp;
import org.eclipse.ocl.pivot.ShadowPart;
import org.eclipse.ocl.pivot.StandardLibrary;
import org.eclipse.ocl.pivot.StringLiteralExp;
import org.eclipse.ocl.pivot.TupleLiteralExp;
import org.eclipse.ocl.pivot.TupleLiteralPart;
import org.eclipse.ocl.pivot.TupleType;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.TypeExp;
import org.eclipse.ocl.pivot.TypedElement;
import org.eclipse.ocl.pivot.UnlimitedNaturalLiteralExp;
import org.eclipse.ocl.pivot.Variable;
import org.eclipse.ocl.pivot.VariableDeclaration;
import org.eclipse.ocl.pivot.VariableExp;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.manager.PivotMetamodelManager;
import org.eclipse.ocl.pivot.internal.manager.TemplateParameterSubstitutionHelper;
import org.eclipse.ocl.pivot.internal.utilities.PivotUtilInternal;
import org.eclipse.ocl.pivot.library.LibraryFeature;
import org.eclipse.ocl.pivot.values.TemplateParameterSubstitutions;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

/**
 * PivotHelper provides helper routines to assist creation of Pivot model elements.
 * @since 1.3
 */
public class PivotHelper
{
	protected final @NonNull EnvironmentFactory environmentFactory;
	protected final @NonNull StandardLibrary standardLibrary;

	public PivotHelper(@NonNull EnvironmentFactory environmentFactory) {
		this.environmentFactory = environmentFactory;
		this.standardLibrary = environmentFactory.getStandardLibrary();
	}

	public @NonNull BooleanLiteralExp createBooleanLiteralExp(boolean booleanSymbol) {
		BooleanLiteralExp asBoolean = PivotFactory.eINSTANCE.createBooleanLiteralExp();
		asBoolean.setBooleanSymbol(booleanSymbol);
		asBoolean.setType(standardLibrary.getBooleanType());
		asBoolean.setIsRequired(true);
		return asBoolean;
	}

	public @NonNull CollectionItem createCollectionItem(@NonNull OCLExpression asItem) {
		CollectionItem collectionItem = PivotFactory.eINSTANCE.createCollectionItem();
		collectionItem.setOwnedItem(asItem);
		collectionItem.setType(asItem.getType());
		collectionItem.setIsRequired(asItem.isIsRequired());
		return collectionItem;
	}

	public @NonNull CollectionLiteralExp createCollectionLiteralExp(@NonNull CollectionType asType, @NonNull Iterable<CollectionLiteralPart> asParts) {
		CollectionLiteralExp collectionLiteralExp = PivotFactory.eINSTANCE.createCollectionLiteralExp();
		Iterables.addAll(collectionLiteralExp.getOwnedParts(), asParts);
		collectionLiteralExp.setType(asType);
		collectionLiteralExp.setKind(TypeUtil.getCollectionKind(asType));
		collectionLiteralExp.setIsRequired(true);
		return collectionLiteralExp;
	}

	public @NonNull CollectionRange createCollectionRange(@NonNull OCLExpression asFirst, @NonNull OCLExpression asLast) {
		CollectionRange collectionRange = PivotFactory.eINSTANCE.createCollectionRange();
		collectionRange.setOwnedFirst(asFirst);
		collectionRange.setOwnedLast(asLast);
		collectionRange.setType(standardLibrary.getIntegerType());
		collectionRange.setIsRequired(true);
		return collectionRange;
	}

	public @NonNull Comment createComment(@NonNull String comment) {
		Comment asComment = PivotFactory.eINSTANCE.createComment();
		asComment.setBody(comment);
		return asComment;
	}

	public @NonNull IfExp createIfExp(@NonNull OCLExpression asCondition, @NonNull OCLExpression asThen, @NonNull OCLExpression asElse) {
		Type commonType = getMetamodelManager().getCommonType(ClassUtil.nonNullState(asThen.getType()), TemplateParameterSubstitutions.EMPTY,
			ClassUtil.nonNullState(asElse.getType()), TemplateParameterSubstitutions.EMPTY);
		IfExp asIf = PivotFactory.eINSTANCE.createIfExp();
		asIf.setOwnedCondition(asCondition);
		asIf.setOwnedThen(asThen);
		asIf.setOwnedElse(asElse);
		asIf.setType(commonType);
		asIf.setIsRequired(asThen.isIsRequired() && asElse.isIsRequired());
		return asIf;
	}

	public  @NonNull Import createImport(@Nullable String name, @NonNull Namespace namespace) {
		Import asImport = PivotFactory.eINSTANCE.createImport();
		asImport.setName(name);
		asImport.setImportedNamespace(namespace);
		return asImport;
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
		asCallExp.setOwnedSource(asSource);
		asCallExp.getOwnedIterators().addAll(asIterators);
		asCallExp.setOwnedResult(asResult);
		asCallExp.setOwnedBody(asBody);
		Type formalType = asIteration.getType();
		assert formalType != null;
		asCallExp.setType(formalType);
		asCallExp.setIsRequired(asIteration.isIsRequired());
		Type actualType = asSource.getType();
		assert actualType != null;
		Type returnType = getMetamodelManager().specializeType(formalType, asCallExp, actualType, asSource.getTypeValue());
		asCallExp.setType(returnType);
		return asCallExp;
	}

	public @NonNull IteratorExp createIteratorExp(@NonNull OCLExpression asSource, @NonNull Iteration asIteration, @NonNull List<@NonNull ? extends Variable> asIterators, @NonNull OCLExpression asBody) {
		IteratorExp asCallExp = PivotFactory.eINSTANCE.createIteratorExp();
		asCallExp.setReferredIteration(asIteration);
		asCallExp.setOwnedSource(asSource);
		asCallExp.getOwnedIterators().addAll(asIterators);
		asCallExp.setOwnedBody(asBody);
		Type formalType = asIteration.getType();
		assert formalType != null;
		asCallExp.setType(formalType);
		asCallExp.setIsRequired(asIteration.isIsRequired());
		Type actualType = asSource.getType();
		assert actualType != null;
		Type returnType = getMetamodelManager().specializeType(formalType, asCallExp, actualType, asSource.getTypeValue());
		asCallExp.setType(returnType);
		return asCallExp;
	}

	public @NonNull IteratorVariable createIteratorVariable(@NonNull String name, @NonNull Type asType, boolean isRequired) {
		IteratorVariable asVariable = PivotFactory.eINSTANCE.createIteratorVariable();
		asVariable.setName(name);
		asVariable.setType(asType);
		asVariable.setIsRequired(isRequired);
		return asVariable;
	}

	public @NonNull LetExp createLetExp(@NonNull Variable asVariable, @NonNull OCLExpression asInExpression) {
		Type commonType = getMetamodelManager().getCommonType(ClassUtil.nonNullState(asVariable.getType()), TemplateParameterSubstitutions.EMPTY,
			ClassUtil.nonNullState(asInExpression.getType()), TemplateParameterSubstitutions.EMPTY);
		LetExp asLetExp = PivotFactory.eINSTANCE.createLetExp();
		asLetExp.setOwnedVariable(asVariable);
		asLetExp.setOwnedIn(asInExpression);;
		asLetExp.setType(commonType);
		asLetExp.setIsRequired(asInExpression.isIsRequired());
		asLetExp.setOwnedVariable(asVariable);
		return asLetExp;
	}

	public @NonNull LetVariable createLetVariable(@NonNull String name, @NonNull OCLExpression asInitExpression) {
		LetVariable asVariable = PivotFactory.eINSTANCE.createLetVariable();
		asVariable.setName(name);
		asVariable.setType(asInitExpression.getType());
		asVariable.setIsRequired(asInitExpression.isIsRequired());
		asVariable.setOwnedInit(asInitExpression);
		return asVariable;
	}

	public @NonNull LetVariable createLetVariable(@NonNull String name, @NonNull Type asType, boolean isRequired) {
		LetVariable asVariable = PivotFactory.eINSTANCE.createLetVariable();
		asVariable.setName(name);
		asVariable.setType(asType);
		asVariable.setIsRequired(isRequired);
		return asVariable;
	}

	public @NonNull LetVariable createLetVariable(@NonNull String name, @NonNull Type asType, boolean isRequired, @NonNull OCLExpression asInitExpression) {
		LetVariable asVariable = PivotFactory.eINSTANCE.createLetVariable();
		asVariable.setName(name);
		asVariable.setType(asType);
		asVariable.setIsRequired(isRequired);
		asVariable.setOwnedInit(asInitExpression);
		return asVariable;
	}

	public @NonNull OCLExpression createMapLiteralExp(@NonNull MapType asType, @NonNull Iterable<MapLiteralPart> asParts) {
		MapLiteralExp mapLiteralExp = PivotFactory.eINSTANCE.createMapLiteralExp();
		Iterables.addAll(mapLiteralExp.getOwnedParts(), asParts);
		mapLiteralExp.setType(asType);
		mapLiteralExp.setIsRequired(true);
		return mapLiteralExp;
	}

	public @NonNull MapLiteralPart createMapLiteralPart(@NonNull OCLExpression asKey, @NonNull OCLExpression asValue) {
		MapLiteralPart mapLiteralPart = PivotFactory.eINSTANCE.createMapLiteralPart();
		mapLiteralPart.setOwnedKey(asKey);
		mapLiteralPart.setOwnedValue(asValue);
		//		mapLiteralPart.setType(asItem.getType());
		//		mapLiteralPart.setIsRequired(true);
		return mapLiteralPart;
	}

	@SuppressWarnings("deprecation")
	public @NonNull NavigationCallExp createNavigationCallExp(@NonNull OCLExpression asSourceExpression, @NonNull Property asProperty) {
		return PivotUtil.createNavigationCallExp(asSourceExpression, asProperty);
	}

	public @NonNull NullLiteralExp createNullLiteralExp() {
		NullLiteralExp asNull = PivotFactory.eINSTANCE.createNullLiteralExp();
		asNull.setType(standardLibrary.getOclVoidType());
		asNull.setIsRequired(false);
		return asNull;
	}

	public @NonNull OperationCallExp createOperationCallExp(@NonNull OCLExpression asSourceExpression, @NonNull String opName, @NonNull OCLExpression... asArguments) {
		Type asType = ClassUtil.nonNullState(asSourceExpression.getType());
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
					Type asParameterType = ClassUtil.nonNullState(asParameters.get(i).getType());
					if (asParameterType instanceof SelfType) {
						Type asArgumentType = asArguments[i].getType();
						if (asArgumentType.conformsTo(standardLibrary, asType) && asType.conformsTo(standardLibrary, asArgumentType)) {
							exactMatches++;
						}
					}
					else {
						Type asArgumentType = asArguments[i].getType();
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

	//	public @NonNull OperationCallExp createOperationCallExp(@Nullable OCLExpression asSourceExpression, @NonNull Operation asOperation, @NonNull OCLExpression... asArguments) {
	//		return createOperationCallExp(asSourceExpression, asOperation, asArguments != null ? Lists.newArrayList(asArguments) : null);
	//	}

	public @NonNull OperationCallExp createOperationCallExp(@Nullable OCLExpression asSourceExpression, @NonNull Operation asOperation, @Nullable List<@NonNull OCLExpression> asArguments) {
		OperationCallExp asOperationCallExp = PivotFactory.eINSTANCE.createOperationCallExp();
		asOperationCallExp.setOwnedSource(asSourceExpression);
		asOperationCallExp.setReferredOperation(asOperation);
		if (asArguments != null) {
			asOperationCallExp.getOwnedArguments().addAll(asArguments);
		}
		asOperationCallExp.setIsRequired(asOperation.isIsRequired());
		Type formalType = asOperation.getType();
		Type sourceType = null;
		Type sourceTypeValue = null;
		if (asSourceExpression != null) {
			sourceType = asSourceExpression.getRawType();
			sourceTypeValue = asSourceExpression.getTypeValue();
		}
		Type returnType = null;
		if ((formalType != null) && (sourceType != null)) {
			PivotMetamodelManager metamodelManager = getMetamodelManager();
			if (asOperation.isIsTypeof()) {
				returnType = metamodelManager.specializeType(formalType, asOperationCallExp, sourceType, null);
			}
			else {
				returnType = metamodelManager.specializeType(formalType, asOperationCallExp, sourceType, sourceTypeValue);
			}

			boolean returnIsRequired = asOperation.isIsRequired();
			LibraryFeature implementationClass = asOperation.getImplementation();
			if (implementationClass != null) {
				Class<? extends LibraryFeature> className = implementationClass.getClass();
				TemplateParameterSubstitutionHelper helper = TemplateParameterSubstitutionHelper.getHelper(className);
				if (helper != null) {
					returnType = helper.resolveReturnType(metamodelManager, asOperationCallExp, returnType);
					returnIsRequired = helper.resolveReturnNullity(metamodelManager, asOperationCallExp, returnIsRequired);
				}
			}
			if (asOperation.isIsTypeof()) {
				setType(asOperationCallExp, standardLibrary.getClassType(), returnIsRequired, returnType);
			}
			else {
				setType(asOperationCallExp, returnType, returnIsRequired, null);
			}
		}
		return asOperationCallExp;
	}

	public org.eclipse.ocl.pivot.@NonNull Package createPackage(@NonNull String name, @Nullable String nsPrefix, @Nullable String nsURI) {
		Package asPackage = PivotFactory.eINSTANCE.createPackage();
		asPackage.setName(name);
		if (nsPrefix != null) {
			asPackage.setNsPrefix(nsPrefix);
		}
		if (nsURI != null) {
			asPackage.setURI(nsURI);
		}
		return asPackage;
	}

	public @NonNull Parameter createParameter(@NonNull TypedElement typedElement) {
		String name = ClassUtil.nonNullState(typedElement.getName());
		Type type = ClassUtil.nonNullState(typedElement.getType());
		Parameter asParameter = PivotUtil.createParameter(name, type, typedElement.isIsRequired());
		return asParameter;
	}

	public @NonNull ParameterVariable createParameterVariable(@NonNull String name, @NonNull Type asType, boolean isRequired) {
		ParameterVariable asVariable = PivotFactory.eINSTANCE.createParameterVariable();
		asVariable.setName(name);
		asVariable.setType(asType);
		asVariable.setIsRequired(isRequired);
		return asVariable;
	}

	public @NonNull RealLiteralExp createRealLiteralExp(@NonNull Number realSymbol) {
		RealLiteralExp asReal = PivotFactory.eINSTANCE.createRealLiteralExp();
		asReal.setRealSymbol(realSymbol);
		asReal.setType(standardLibrary.getRealType());
		asReal.setIsRequired(true);
		return asReal;
	}

	public @NonNull ResultVariable createResultVariable(@NonNull String name, @NonNull Type asType, boolean isRequired, @NonNull OCLExpression asInitExpression) {
		ResultVariable asVariable = PivotFactory.eINSTANCE.createResultVariable();
		asVariable.setName(name);
		asVariable.setType(asType);
		asVariable.setIsRequired(isRequired);
		asVariable.setOwnedInit(asInitExpression);
		return asVariable;
	}

	public @NonNull OCLExpression createShadowExp(org.eclipse.ocl.pivot.@NonNull Class asClass, @NonNull Iterable<ShadowPart> asParts) {
		ShadowExp shadowExp = PivotFactory.eINSTANCE.createShadowExp();
		Iterables.addAll(shadowExp.getOwnedParts(), asParts);
		shadowExp.setType(asClass);
		shadowExp.setIsRequired(true);
		return shadowExp;
	}

	public @NonNull ShadowPart createShadowPart(@NonNull Property asProperty, @NonNull OCLExpression asValue) {
		ShadowPart shadowPart = PivotFactory.eINSTANCE.createShadowPart();
		shadowPart.setReferredProperty(asProperty);
		shadowPart.setType(asProperty.getType());
		shadowPart.setIsRequired(asProperty.isIsRequired());
		shadowPart.setOwnedInit(asValue);
		return shadowPart;
	}

	public @NonNull StringLiteralExp createStringLiteralExp(@NonNull String stringSymbol) {
		StringLiteralExp asString = PivotFactory.eINSTANCE.createStringLiteralExp();
		asString.setStringSymbol(stringSymbol);
		asString.setType(standardLibrary.getStringType());
		asString.setIsRequired(true);
		return asString;
	}

	public @NonNull TupleLiteralExp createTupleLiteralExp(@NonNull TupleType asType, @NonNull Iterable<TupleLiteralPart> asParts) {
		TupleLiteralExp tupleLiteralExp = PivotFactory.eINSTANCE.createTupleLiteralExp();
		Iterables.addAll(tupleLiteralExp.getOwnedParts(), asParts);
		tupleLiteralExp.setType(asType);
		tupleLiteralExp.setIsRequired(true);
		return tupleLiteralExp;
	}

	public @NonNull TupleLiteralPart createTupleLiteralPart(@NonNull String name, @NonNull Type asType, boolean isRequired, @NonNull OCLExpression asValue) {
		TupleLiteralPart tupleLiteralPart = PivotFactory.eINSTANCE.createTupleLiteralPart();
		tupleLiteralPart.setName(name);
		tupleLiteralPart.setType(asType);
		tupleLiteralPart.setIsRequired(isRequired);
		tupleLiteralPart.setOwnedInit(asValue);
		return tupleLiteralPart;
	}

	public @NonNull TypeExp createTypeExp(@NonNull Type type) {
		TypeExp asTypeExp = PivotFactory.eINSTANCE.createTypeExp();
		asTypeExp.setIsRequired(true);
		asTypeExp.setReferredType(type);
		asTypeExp.setType(standardLibrary.getClassType());
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

	/** @deprecated Use appropriate derived Variable */
	@Deprecated
	public @NonNull Variable createVariable(@NonNull String name, @NonNull OCLExpression asInitExpression) {
		Variable asVariable = PivotUtil.createVariable(name, asInitExpression);
		return asVariable;
	}

	/** @deprecated Use appropriate derived Variable */
	@Deprecated
	public @NonNull Variable createVariable(@NonNull String name, @NonNull Type asType, boolean isRequired, @Nullable OCLExpression asInitExpression) {
		Variable asVariable = PivotUtil.createVariable(name, asType, isRequired, asInitExpression);
		return asVariable;
	}

	/** @deprecated Use appropriate derived Variable */
	@Deprecated
	public @NonNull Variable createVariable(@NonNull TypedElement typedElement) {
		String name = ClassUtil.nonNullState(typedElement.getName());
		Type type = ClassUtil.nonNullState(typedElement.getType());
		Variable asVariable = PivotUtil.createVariable(name, type, typedElement.isIsRequired(), null);
		return asVariable;
	}

	public @NonNull VariableExp createVariableExp(@NonNull VariableDeclaration asVariable) {
		VariableExp asVariableExp = PivotUtil.createVariableExp(asVariable);
		return asVariableExp;
	}

	public org.eclipse.ocl.pivot.@NonNull Class getDataTypeClass() {
		return ClassUtil.nonNullState(environmentFactory.getMetamodelManager().getASClass(TypeId.DATA_TYPE_NAME));
	}

	public @NonNull Property getDataTypeValueProperty() {
		return ClassUtil.nonNullState(NameUtil.getNameable(getDataTypeClass().getOwnedProperties(), PivotConstants.DATA_TYPE_VALUE_NAME));
	}

	public @NonNull EnvironmentFactory getEnvironmentFactory() {
		return environmentFactory;
	}

	protected @NonNull PivotMetamodelManager getMetamodelManager() {
		return (PivotMetamodelManager) environmentFactory.getMetamodelManager();			// FIXME avoid this cast
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
			PivotMetamodelManager metamodelManager = (PivotMetamodelManager) environmentFactory.getMetamodelManager();
			org.eclipse.ocl.pivot.Class oclAnyType = environmentFactory.getStandardLibrary().getOclAnyType();
			Operation oclEqualsOperation = NameUtil.getNameable(oclAnyType.getOwnedOperations(), "=");
			assert oclEqualsOperation != null;
			org.eclipse.ocl.pivot.Class collectionType = environmentFactory.getStandardLibrary().getCollectionType();
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

	private void rewriteUnsafeCollectionCallExp(@NonNull PivotMetamodelManager metamodelManager, @NonNull Operation excludingOperation, @NonNull CallExp unsafeCollectionCallExp) {
		unsafeCollectionCallExp.setIsSafe(false);
		EObject eContainer = unsafeCollectionCallExp.eContainer();
		EReference eContainmentFeature = unsafeCollectionCallExp.eContainmentFeature();
		PivotUtilInternal.resetContainer(unsafeCollectionCallExp);
		//
		OCLExpression nullExpression = metamodelManager.createNullLiteralExp();
		OCLExpression safeCollectionCallExp = createOperationCallExp(unsafeCollectionCallExp, excludingOperation, Collections.singletonList(nullExpression));
		//
		eContainer.eSet(eContainmentFeature, safeCollectionCallExp);
	}

	private  void rewriteUnsafeObjectCallExp(@NonNull PivotMetamodelManager metamodelManager, @NonNull Operation oclEqualsOperation, @NonNull CallExp unsafeObjectCallExp) {
		unsafeObjectCallExp.setIsSafe(false);
		EObject eContainer = unsafeObjectCallExp.eContainer();
		EReference eContainmentFeature = unsafeObjectCallExp.eContainmentFeature();
		PivotUtilInternal.resetContainer(unsafeObjectCallExp);
		OCLExpression oldSourceExpression = unsafeObjectCallExp.getOwnedSource();
		assert oldSourceExpression != null;
		//
		LetVariable unsafeSourceVariable = createLetVariable("unsafe", oldSourceExpression);
		OCLExpression unsafeSourceExpression1 = createVariableExp(unsafeSourceVariable);
		unsafeObjectCallExp.setOwnedSource(unsafeSourceExpression1);
		//
		OCLExpression unsafeSourceExpression2 = createVariableExp(unsafeSourceVariable);
		OCLExpression nullExpression = metamodelManager.createNullLiteralExp();
		OCLExpression isUnsafeExpression = createOperationCallExp(unsafeSourceExpression2, oclEqualsOperation, Collections.singletonList(nullExpression));
		//
		OCLExpression thenExpression = metamodelManager.createNullLiteralExp();
		OCLExpression safeObjectCallExp = metamodelManager.createIfExp(isUnsafeExpression, thenExpression, unsafeObjectCallExp);
		//
		LetExp safeExp = createLetExp(unsafeSourceVariable, safeObjectCallExp);
		//
		eContainer.eSet(eContainmentFeature, safeExp);
	}

	public void setType(@NonNull OCLExpression asExpression, Type type, boolean isRequired, @Nullable Type typeValue) {
		setType(asExpression, type, isRequired);
		Type primaryTypeValue = typeValue != null ? getMetamodelManager().getPrimaryType(typeValue) : null;
		if (primaryTypeValue != asExpression.getTypeValue()) {
			asExpression.setTypeValue(primaryTypeValue);
		}
	}

	/*	private void setType(@NonNull VariableDeclaration pivotElement, Type type, boolean isRequired, @Nullable Type typeValue) {
		setType(pivotElement, type, isRequired);
		PivotMetamodelManager metamodelManager = getMetamodelManager();
		Type primaryTypeValue = typeValue != null ? metamodelManager.getPrimaryType(typeValue) : null;
		if (primaryTypeValue != pivotElement.getTypeValue()) {
			pivotElement.setTypeValue(primaryTypeValue);
		}
	} */

	public void setType(@NonNull TypedElement asTypedElement, Type type, boolean isRequired) {
		Type primaryType = type != null ? getMetamodelManager().getPrimaryType(type) : null;
		if (primaryType != asTypedElement.getType()) {
			asTypedElement.setType(primaryType);
		}
		boolean wasRequired = asTypedElement.isIsRequired();
		if (wasRequired != isRequired) {
			asTypedElement.setIsRequired(isRequired);
		}
		if (primaryType != null) {
			PivotUtil.debugWellContainedness(primaryType);
		}
	}
}