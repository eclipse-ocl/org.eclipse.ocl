/*******************************************************************************
 * Copyright (c) 2016 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.utilities;

import java.util.List;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.BooleanLiteralExp;
import org.eclipse.ocl.pivot.CollectionItem;
import org.eclipse.ocl.pivot.CollectionLiteralExp;
import org.eclipse.ocl.pivot.CollectionLiteralPart;
import org.eclipse.ocl.pivot.CollectionRange;
import org.eclipse.ocl.pivot.CollectionType;
import org.eclipse.ocl.pivot.CompleteClass;
import org.eclipse.ocl.pivot.IfExp;
import org.eclipse.ocl.pivot.Import;
import org.eclipse.ocl.pivot.IntegerLiteralExp;
import org.eclipse.ocl.pivot.InvalidLiteralExp;
import org.eclipse.ocl.pivot.IterateExp;
import org.eclipse.ocl.pivot.Iteration;
import org.eclipse.ocl.pivot.IteratorExp;
import org.eclipse.ocl.pivot.LetExp;
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
import org.eclipse.ocl.pivot.PivotFactory;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.RealLiteralExp;
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
import org.eclipse.ocl.pivot.internal.manager.PivotMetamodelManager;
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
			sourceType = asSourceExpression.getType();
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
		}
		asOperationCallExp.setType(returnType);
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

	public @NonNull RealLiteralExp createRealLiteralExp(@NonNull Number realSymbol) {
		RealLiteralExp asReal = PivotFactory.eINSTANCE.createRealLiteralExp();
		asReal.setRealSymbol(realSymbol);
		asReal.setType(standardLibrary.getRealType());
		asReal.setIsRequired(true);
		return asReal;
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

	public @NonNull OCLExpression createTupleLiteralExp(@NonNull TupleType asType, @NonNull Iterable<TupleLiteralPart> asParts) {
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

	public @NonNull Variable createVariable(@NonNull String name, @NonNull OCLExpression asInitExpression) {
		Variable asVariable = PivotUtil.createVariable(name, asInitExpression);
		return asVariable;
	}

	public @NonNull Variable createVariable(@NonNull String name, @NonNull Type asType, boolean isRequired, @Nullable OCLExpression asInitExpression) {
		Variable asVariable = PivotUtil.createVariable(name, asType, isRequired, asInitExpression);
		return asVariable;
	}

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

	protected @NonNull PivotMetamodelManager getMetamodelManager() {
		return (PivotMetamodelManager) environmentFactory.getMetamodelManager();			// FIXME avoid this cast
	}
}