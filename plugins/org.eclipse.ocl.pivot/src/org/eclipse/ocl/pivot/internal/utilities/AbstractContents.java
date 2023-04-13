/*******************************************************************************
 * Copyright (c) 2012, 2022 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package	org.eclipse.ocl.pivot.internal.utilities;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.AnyType;
import org.eclipse.ocl.pivot.BagType;
import org.eclipse.ocl.pivot.BooleanType;
import org.eclipse.ocl.pivot.CollectionType;
import org.eclipse.ocl.pivot.Comment;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.Import;
import org.eclipse.ocl.pivot.InvalidType;
import org.eclipse.ocl.pivot.Iteration;
import org.eclipse.ocl.pivot.LambdaType;
import org.eclipse.ocl.pivot.Library;
import org.eclipse.ocl.pivot.MapType;
import org.eclipse.ocl.pivot.Model;
import org.eclipse.ocl.pivot.Namespace;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.OrderedSetType;
import org.eclipse.ocl.pivot.ParameterVariable;
import org.eclipse.ocl.pivot.PivotFactory;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.SelfType;
import org.eclipse.ocl.pivot.SequenceType;
import org.eclipse.ocl.pivot.SetType;
import org.eclipse.ocl.pivot.TemplateBinding;
import org.eclipse.ocl.pivot.TemplateParameter;
import org.eclipse.ocl.pivot.TemplateParameterSubstitution;
import org.eclipse.ocl.pivot.TemplateSignature;
import org.eclipse.ocl.pivot.TemplateableElement;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.VoidType;
import org.eclipse.ocl.pivot.ids.PackageId;
import org.eclipse.ocl.pivot.internal.LibraryImpl;
import org.eclipse.ocl.pivot.internal.PackageImpl;
import org.eclipse.ocl.pivot.internal.library.StandardLibraryContribution;
import org.eclipse.ocl.pivot.library.LibraryFeature;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.PivotConstants;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.utilities.StringUtil;
import org.eclipse.ocl.pivot.utilities.ValueUtil;
import org.eclipse.ocl.pivot.values.Unlimited;

public abstract class AbstractContents extends PivotUtil
{
	/**
	 * @since 1.4
	 */
	@Deprecated
	protected static void addBinding(@NonNull TemplateableElement specializedType, @NonNull Type actualType) {
		TemplateSignature templateSignature1 = specializedType.getOwnedSignature();
		assert templateSignature1 == null;
		TemplateableElement unspecializedType = specializedType.getUnspecializedElement();
		List<TemplateBinding> templateBindings = specializedType.getOwnedBindings();
		TemplateBinding templateBinding ;
		if (templateBindings.size() > 0) {
			templateBinding = templateBindings.get(0);
		}
		else {
			templateBinding = PivotFactory.eINSTANCE.createTemplateBinding();
			templateBindings.add(templateBinding);
		}
		List<TemplateParameterSubstitution> parameterSubstitutions = templateBinding.getOwnedSubstitutions();
		TemplateSignature templateSignature2 = unspecializedType.getOwnedSignature();
		assert templateSignature2 != null;
		List<@NonNull TemplateParameter> templateParameters = PivotUtilInternal.getOwnedParametersList(templateSignature2);
		TemplateParameter templateParameter = templateParameters.get(parameterSubstitutions.size());
		assert templateParameter != null;
		TemplateParameterSubstitution templateParameterSubstitution = createTemplateParameterSubstitution(templateParameter, actualType);
		parameterSubstitutions.add(templateParameterSubstitution);
	}

	protected static void addSuperClass(org.eclipse.ocl.pivot./*@NonNull*/ Class asClass, org.eclipse.ocl.pivot./*@NonNull*/ Class asSuperClass) {
		asClass.getSuperClasses().add(asSuperClass);
	}

	/* @deprecated no longer used. Replaced by EClass literal and TemplateParameter */
	@Deprecated
	protected @NonNull BagType createBagType(@NonNull String name, @Nullable String lower, @Nullable String upper, @NonNull TemplateParameter templateParameter) {
		return createCollectionType(PivotFactory.eINSTANCE.createBagType(), name, lower, upper, templateParameter);
	}

	/**
	 * @since 1.17
	 */
	protected @NonNull BagType createBagType(/*@NonNull*/ EClass eClass, @NonNull TemplateParameter templateParameter, boolean isNullFree, int lower, int upper) {
		BagType asType = PivotFactory.eINSTANCE.createBagType();
		asType.setName(eClass.getName());
		initTemplateParameters(asType, templateParameter);
		((PivotObjectImpl)asType).setESObject(eClass);
		asType.setIsNullFree(isNullFree);
		asType.setLowerValue(ValueUtil.integerValueOf(lower));
		asType.setUpperValue(upper >= 0 ? ValueUtil.unlimitedNaturalValueOf(upper) : ValueUtil.UNLIMITED_VALUE);
		return asType;
	}

	/**
	 * @since 1.4
	 */
	protected @NonNull BagType createBagType(@NonNull BagType unspecializedType) {
		return createCollectionType(PivotFactory.eINSTANCE.createBagType(), unspecializedType);
	}

	/**
	 * @since 1.16
	 */
	protected @NonNull ExpressionInOCL createBodyExpression(@NonNull Operation operation, /*@NonNull*/ Type selfType, @NonNull String exprString, /*@NonNull*/ Type resultType) {
		ExpressionInOCL asExpression = PivotFactory.eINSTANCE.createExpressionInOCL();
		asExpression.setType(resultType);
		asExpression.setBody(exprString);
		ParameterVariable contextVariable = PivotFactory.eINSTANCE.createParameterVariable();
		contextVariable.setName(PivotConstants.SELF_NAME);
		contextVariable.setType(selfType);
		contextVariable.setIsRequired(!operation.isIsValidating());
		asExpression.setOwnedContext(contextVariable);
		operation.setBodyExpression(asExpression);
		return asExpression;
	}

	/**
	 * @since 1.17
	 */
	protected @NonNull CollectionType createCollectionType(/*@NonNull*/ EClass eClass, @NonNull TemplateParameter templateParameter, boolean isNullFree, int lower, int upper) {
		CollectionType asType = PivotFactory.eINSTANCE.createCollectionType();
		asType.setName(eClass.getName());
		initTemplateParameters(asType, templateParameter);
		((PivotObjectImpl)asType).setESObject(eClass);
		asType.setIsNullFree(isNullFree);
		asType.setLowerValue(ValueUtil.integerValueOf(lower));
		asType.setUpperValue(upper >= 0 ? ValueUtil.unlimitedNaturalValueOf(upper) : ValueUtil.UNLIMITED_VALUE);
		return asType;
	}

	protected @NonNull <@NonNull T extends CollectionType> T createCollectionType(@NonNull T asType, @NonNull String name, @Nullable  String lower, @Nullable String upper, @NonNull TemplateParameter templateParameter) {
		asType.setName(name);
		asType.setLower(lower != null ? StringUtil.createNumberFromString(lower) : Integer.valueOf(0));
		asType.setUpper(upper != null ? StringUtil.createNumberFromString(upper) : Unlimited.INSTANCE);
		initTemplateParameter(asType, templateParameter);
		return asType;
	}

	/* @deprecated no longer used. Replaced by EClass literal and TemplateParameter */
	@Deprecated
	protected @NonNull CollectionType createCollectionType(@NonNull String name, @Nullable String lower, @Nullable String upper, @NonNull TemplateParameter templateParameter) {
		return createCollectionType(PivotFactory.eINSTANCE.createCollectionType(), name, lower, upper, templateParameter);
	}

	/**
	 * @since 1.4
	 */
	protected @NonNull CollectionType createCollectionType(@NonNull CollectionType unspecializedType) {
		return createCollectionType(PivotFactory.eINSTANCE.createCollectionType(), unspecializedType);
	}

	/**
	 * @since 1.4
	 */
	protected @NonNull <@NonNull T extends CollectionType> T createCollectionType(/*@NonNull*/ T specializedType, @NonNull T unspecializedType) {
		specializedType.setName(unspecializedType.getName());
		specializedType.setLower(unspecializedType.getLower());
		specializedType.setUpper(unspecializedType.getUpper());
		specializedType.setUnspecializedElement(unspecializedType);
		return specializedType;
	}

	@Deprecated /* @deprecated add selfType/resultType */
	protected @NonNull ExpressionInOCL createExpressionInOCL(@NonNull Type type, @NonNull String exprString) {
		ExpressionInOCL asExpression = PivotFactory.eINSTANCE.createExpressionInOCL();
		asExpression.setType(type);
		asExpression.setBody(exprString);
//		ParameterVariable contextVariable = PivotFactory.eINSTANCE.createParameterVariable();
//		contextVariable.setName(PivotConstants.SELF_NAME);
//		contextVariable.setType(type);
//		pivotExpression.setOwnedContext(contextVariable);
		return asExpression;
	}

	/**
	 * @since 1.15
	 */
	@Deprecated /* use createBodyExpression */
	protected @NonNull ExpressionInOCL createExpressionInOCL(@NonNull Type selfType, @NonNull String exprString, @NonNull Type resultType) {
		ExpressionInOCL asExpression = PivotFactory.eINSTANCE.createExpressionInOCL();
		asExpression.setType(resultType);
		asExpression.setBody(exprString);
		ParameterVariable contextVariable = PivotFactory.eINSTANCE.createParameterVariable();
		contextVariable.setName(PivotConstants.SELF_NAME);
		contextVariable.setType(selfType);
		contextVariable.setIsRequired(true);
		asExpression.setOwnedContext(contextVariable);
		return asExpression;
	}

	protected @NonNull Import createImport(@Nullable String name, @NonNull Namespace namespace) {
		Import asImport = PivotFactory.eINSTANCE.createImport();
		asImport.setName(name);
		asImport.setImportedNamespace(namespace);
		return asImport;
	}

	protected @NonNull LambdaType createLambdaType(@NonNull String name, @NonNull TemplateParameter contextType, @NonNull Type resultType, @NonNull TemplateParameter... parameterTypes) {
		assert contextType != null;
		assert resultType != null;
		LambdaType type = createLambdaType(name);
		type.setContextType(contextType);
		// XXX parameterTypes
		type.setResultType(resultType);
		return type;
	}

	protected @NonNull Iteration createIteration(@NonNull String name, @NonNull Type type, @Nullable String implementationClass, @NonNull LibraryFeature implementation, TemplateParameter... templateParameters) {
		Iteration asIteration = createIteration(name, type, implementationClass, implementation);
		initTemplateParameters(asIteration, templateParameters);
		return asIteration;
	}

	@Deprecated /* @deprecated add ePackage */
	protected @NonNull Library createLibrary(@NonNull String name, @NonNull String nsPrefix, @NonNull String nsURI, @Nullable PackageId packageId) {
		Library asLibrary = PivotFactory.eINSTANCE.createLibrary();
		asLibrary.setName(name);
		asLibrary.setNsPrefix(nsPrefix);
		if (packageId != null) {
			((LibraryImpl)asLibrary).setPackageId(packageId);  // FIXME Add to API
		}
		asLibrary.setURI(nsURI);
		return asLibrary;
	}

	/**
	 * @since 1.17
	 */
	protected @NonNull Library createLibrary(@NonNull String name, @NonNull String nsPrefix, @NonNull String nsURI, @Nullable PackageId packageId, @Nullable EPackage ePackage) {
		Library asLibrary = PivotFactory.eINSTANCE.createLibrary();
		asLibrary.setName(name);
		asLibrary.setNsPrefix(nsPrefix);
		if (packageId != null) {
			((LibraryImpl)asLibrary).setPackageId(packageId);  // FIXME Add to API
		}
		asLibrary.setURI(nsURI);
		((PivotObjectImpl)asLibrary).setESObject(ePackage);
		return asLibrary;
	}

	@Deprecated /* @deprecated no longer used */
	protected @NonNull MapType createMapType(/*@NonNull*/ MapType asType, @NonNull String name, @NonNull TemplateParameter keyParameter, @NonNull TemplateParameter valueParameter) {
		asType.setName(name);
		initTemplateParameters(asType, keyParameter, valueParameter);
		return asType;
	}

	/**
	 * @since 1.17
	 */
	@Deprecated
	protected @NonNull MapType createMapType(/*@NonNull*/ EClass eClass, @NonNull TemplateParameter keyParameter, @NonNull TemplateParameter valueParameter) {
		MapType mapType = PivotFactory.eINSTANCE.createMapType();
		mapType.setName(eClass.getName());
		initTemplateParameters(mapType, keyParameter, valueParameter);
		((PivotObjectImpl)mapType).setESObject(eClass);
		return mapType;
	}
	protected @NonNull MapType createMapType(/*@NonNull*/ EClass eClass, @NonNull TemplateParameter keyParameter, boolean keysAreNullFree, @NonNull TemplateParameter valueParameter, boolean valuesAreNullFree) {
		MapType mapType = PivotFactory.eINSTANCE.createMapType();
		mapType.setName(eClass.getName());
		initTemplateParameters(mapType, keyParameter, valueParameter);
		((PivotObjectImpl)mapType).setESObject(eClass);
		mapType.setKeysAreNullFree(keysAreNullFree);
		mapType.setValuesAreNullFree(valuesAreNullFree);
		return mapType;
	}

	/* @deprecated no longer used. Replaced by EClass literal and TemplateParameter */
	@Deprecated
	protected @NonNull MapType createMapType(@NonNull String name, @NonNull TemplateParameter keyParameter, @NonNull TemplateParameter valueParameter) {
		MapType mapType = PivotFactory.eINSTANCE.createMapType();
		mapType.setName(name);
		initTemplateParameters(mapType, keyParameter, valueParameter);
		return mapType;
	}

	/**
	 * @since 1.4
	 */
	protected @NonNull MapType createMapType(@NonNull MapType unspecializedType) {
		MapType specializedType = PivotFactory.eINSTANCE.createMapType();
		specializedType.setName(unspecializedType.getName());
		specializedType.setUnspecializedElement(unspecializedType);
		return specializedType;
	}

	/**
	 * @since 1.17
	 */
	protected @NonNull Model createModel(@NonNull String asURI, @NonNull EPackage ePackage) {
		Model asModel = PivotUtil.createModel(asURI);
		((PivotObjectImpl)asModel).setESObject(ePackage);
		return asModel;
	}

	protected @NonNull Operation createOperation(@NonNull String name, @NonNull Type type, @Nullable String implementationClass, @Nullable LibraryFeature implementation, TemplateParameter... templateParameters) {
		Operation asOperation = createOperation(name, type, implementationClass, implementation);
		initTemplateParameters(asOperation, templateParameters);
		return asOperation;
	}

	/**
	 * @since 1.17
	 */
	protected @NonNull OrderedSetType createOrderedSetType(/*@NonNull*/ EClass eClass, @NonNull TemplateParameter templateParameter, boolean isNullFree, int lower, int upper) {
		OrderedSetType asType = PivotFactory.eINSTANCE.createOrderedSetType();
		asType.setName(eClass.getName());
		initTemplateParameters(asType, templateParameter);
		((PivotObjectImpl)asType).setESObject(eClass);
		asType.setIsNullFree(isNullFree);
		asType.setLowerValue(ValueUtil.integerValueOf(lower));
		asType.setUpperValue(upper >= 0 ? ValueUtil.unlimitedNaturalValueOf(upper) : ValueUtil.UNLIMITED_VALUE);
		return asType;
	}

	/* @deprecated no longer used. Replaced by EClass literal and TemplateParameter */
	@Deprecated
	protected @NonNull OrderedSetType createOrderedSetType(@NonNull String name, @Nullable String lower, @Nullable String upper, @NonNull TemplateParameter templateParameter) {
		return createCollectionType(PivotFactory.eINSTANCE.createOrderedSetType(), name, lower, upper, templateParameter);
	}

	/**
	 * @since 1.4
	 */
	protected @NonNull OrderedSetType createOrderedSetType(@NonNull OrderedSetType unspecializedType) {
		return createCollectionType(PivotFactory.eINSTANCE.createOrderedSetType(), unspecializedType);
	}

	/**
	 * @since 1.17
	 */
	protected org.eclipse.ocl.pivot.@NonNull Package createPackage(@NonNull String name, @Nullable String nsPrefix, @NonNull String nsURI, @Nullable PackageId packageId, @Nullable EPackage ePackage) {
		org.eclipse.ocl.pivot.Package asPackage = PivotFactory.eINSTANCE.createPackage();
		asPackage.setName(name);
		asPackage.setNsPrefix(nsPrefix);
		if (packageId != null) {
			((PackageImpl)asPackage).setPackageId(packageId);  // FIXME Add to API
		}
		asPackage.setURI(nsURI);
		((PivotObjectImpl)asPackage).setESObject(ePackage);
		return asPackage;
	}

	/**
	 * @since 1.17
	 */
	protected @NonNull SequenceType createSequenceType(/*@NonNull*/ EClass eClass, @NonNull TemplateParameter templateParameter, boolean isNullFree, int lower, int upper) {
		SequenceType asType = PivotFactory.eINSTANCE.createSequenceType();
		asType.setName(eClass.getName());
		initTemplateParameters(asType, templateParameter);
		((PivotObjectImpl)asType).setESObject(eClass);
		asType.setIsNullFree(isNullFree);
		asType.setLowerValue(ValueUtil.integerValueOf(lower));
		asType.setUpperValue(upper >= 0 ? ValueUtil.unlimitedNaturalValueOf(upper) : ValueUtil.UNLIMITED_VALUE);
		return asType;
	}

	/* @deprecated no longer used. Replaced by EClass literal and TemplateParameter */
	@Deprecated
	protected @NonNull SequenceType createSequenceType(@NonNull String name, @Nullable String lower, @Nullable String upper, @NonNull TemplateParameter templateParameter) {
		return createCollectionType(PivotFactory.eINSTANCE.createSequenceType(), name, lower, upper, templateParameter);
	}

	/**
	 * @since 1.4
	 */
	protected @NonNull SequenceType createSequenceType(@NonNull SequenceType unspecializedType) {
		return createCollectionType(PivotFactory.eINSTANCE.createSequenceType(), unspecializedType);
	}

	/**
	 * @since 1.17
	 */
	protected @NonNull SetType createSetType(/*@NonNull*/ EClass eClass, @NonNull TemplateParameter templateParameter, boolean isNullFree, int lower, int upper) {
		SetType asType = PivotFactory.eINSTANCE.createSetType();
		asType.setName(eClass.getName());
		initTemplateParameters(asType, templateParameter);
		((PivotObjectImpl)asType).setESObject(eClass);
		asType.setIsNullFree(isNullFree);
		asType.setLowerValue(ValueUtil.integerValueOf(lower));
		asType.setUpperValue(upper >= 0 ? ValueUtil.unlimitedNaturalValueOf(upper) : ValueUtil.UNLIMITED_VALUE);
		return asType;
	}

	/* @deprecated no longer used. Replaced by EClass literal and TemplateParameter */
	@Deprecated
	protected @NonNull SetType createSetType(@NonNull String name, @Nullable String lower, @Nullable String upper, @NonNull TemplateParameter templateParameter) {
		return createCollectionType(PivotFactory.eINSTANCE.createSetType(), name, lower, upper, templateParameter);
	}

	/**
	 * @since 1.4
	 */
	protected @NonNull SetType createSetType(@NonNull SetType unspecializedType) {
		return createCollectionType(PivotFactory.eINSTANCE.createSetType(), unspecializedType);
	}

	protected @NonNull AnyType getAnyType(org.eclipse.ocl.pivot.@NonNull Package asPackage, @NonNull String name) {
		return (AnyType) ClassUtil.nonNullState(asPackage.getOwnedClass(name));
	}

	protected @NonNull BagType getBagType(org.eclipse.ocl.pivot.@NonNull Package asPackage, @NonNull String name) {
		return (BagType) ClassUtil.nonNullState(asPackage.getOwnedClass(name));
	}

	@Deprecated
	protected @NonNull BagType getBagType(@NonNull BagType unspecializedType, @NonNull Type elementType) {
		BagType type = createBagType(unspecializedType);
		addBinding(type, elementType);
		return type;
	}

	protected @NonNull BagType getBagType(@NonNull BagType unspecializedType, /*@NonNull*/ Type elementType, boolean isNullFree, int lower, int upper) {
		assert elementType != null;
		BagType type = createBagType(unspecializedType);
		type.setIsNullFree(isNullFree);
		type.setLowerValue(ValueUtil.integerValueOf(lower));
		type.setUpperValue(upper >= 0 ? ValueUtil.unlimitedNaturalValueOf(upper) : ValueUtil.UNLIMITED_VALUE);
		addBinding(type, elementType);
		return type;
	}

	/**
	 * @since 1.18
	 */
	protected @NonNull BooleanType getBooleanType(org.eclipse.ocl.pivot.@NonNull Package asPackage, @NonNull String name) {
		return (BooleanType) ClassUtil.nonNullState(asPackage.getOwnedClass(name));
	}

	protected org.eclipse.ocl.pivot.@NonNull Class getClass(org.eclipse.ocl.pivot.@NonNull Package asPackage, @NonNull String name) {
		return ClassUtil.nonNullState(asPackage.getOwnedClass(name));
	}

	protected @NonNull CollectionType getCollectionType(org.eclipse.ocl.pivot.@NonNull Package asPackage, @NonNull String name) {
		return (CollectionType) ClassUtil.nonNullState(asPackage.getOwnedClass(name));
	}

	@Deprecated
	protected @NonNull CollectionType getCollectionType(@NonNull CollectionType unspecializedType, @NonNull Type elementType) {
		CollectionType type = createCollectionType(unspecializedType);
		addBinding(type, elementType);
		return type;
	}

	protected @NonNull CollectionType getCollectionType(@NonNull CollectionType unspecializedType, /*@NonNull*/ Type elementType, boolean isNullFree, int lower, int upper) {
		assert elementType != null;
		CollectionType type = createCollectionType(unspecializedType);
		type.setIsNullFree(isNullFree);
		type.setLowerValue(ValueUtil.integerValueOf(lower));
		type.setUpperValue(upper >= 0 ? ValueUtil.unlimitedNaturalValueOf(upper) : ValueUtil.UNLIMITED_VALUE);
		addBinding(type, elementType);
		return type;
	}

	protected @NonNull InvalidType getInvalidType(org.eclipse.ocl.pivot.@NonNull Package asPackage, @NonNull String name) {
		return (InvalidType) ClassUtil.nonNullState(asPackage.getOwnedClass(name));
	}

	protected @NonNull Library getLibrary(@NonNull Model asModel, @NonNull String name) {
		return (Library) ClassUtil.nonNullState(NameUtil.getNameable(asModel.getOwnedPackages(), name));
	}

	@Deprecated
	protected @NonNull MapType getMapType(@NonNull MapType unspecializedType, @NonNull Type keyType, @NonNull Type valueType) {
		MapType type = createMapType(unspecializedType);
		addBinding(type, keyType);
		addBinding(type, valueType);
		return type;
	}

	protected @NonNull MapType getMapType(@NonNull MapType unspecializedType, @NonNull Type keyType, boolean keysAreNullFree, @NonNull Type valueType, boolean valuesAreNullFree) {
		assert keyType != null;
		assert valueType != null;
		MapType type = createMapType(unspecializedType);
		addBinding(type, keyType);
		addBinding(type, valueType);
		type.setKeysAreNullFree(keysAreNullFree);
		type.setValuesAreNullFree(valuesAreNullFree);
		return type;
	}

	protected @NonNull Model getModel(@NonNull String modelURI) {
		StandardLibraryContribution standardLibraryContribution = ClassUtil.nonNullState(StandardLibraryContribution.REGISTRY.get(modelURI));
		Resource resource = standardLibraryContribution.getResource();
		return ClassUtil.nonNullState((Model) resource.getContents().get(0));
	}

	protected @NonNull OrderedSetType getOrderedSetType(org.eclipse.ocl.pivot.@NonNull Package asPackage, @NonNull String name) {
		return (OrderedSetType) ClassUtil.nonNullState(asPackage.getOwnedClass(name));
	}

	@Deprecated
	protected @NonNull OrderedSetType getOrderedSetType(@NonNull OrderedSetType unspecializedType, @NonNull Type elementType) {
		OrderedSetType type = createOrderedSetType(unspecializedType);
		addBinding(type, elementType);
		return type;
	}

	protected @NonNull OrderedSetType getOrderedSetType(@NonNull OrderedSetType unspecializedType, /*@NonNull*/ Type elementType, boolean isNullFree, int lower, int upper) {
		assert elementType != null;
		OrderedSetType type = createOrderedSetType(unspecializedType);
		type.setIsNullFree(isNullFree);
		type.setLowerValue(ValueUtil.integerValueOf(lower));
		type.setUpperValue(upper >= 0 ? ValueUtil.unlimitedNaturalValueOf(upper) : ValueUtil.UNLIMITED_VALUE);
		addBinding(type, elementType);
		return type;
	}

	protected org.eclipse.ocl.pivot.@NonNull Package getPackage(@NonNull Model asModel, @NonNull String name) {
		return ClassUtil.nonNullState(NameUtil.getNameable(asModel.getOwnedPackages(), name));
	}

	/**
	 * @since 1.18
	 */
	protected org.eclipse.ocl.pivot.@NonNull Class getPrimitiveType(org.eclipse.ocl.pivot.@NonNull Package asPackage, @NonNull String name) {
		return ClassUtil.nonNullState(asPackage.getOwnedClass(name));
	}

	protected @NonNull Property getProperty(org.eclipse.ocl.pivot.@NonNull Class asClass, @NonNull String name) {
		return ClassUtil.nonNullState(NameUtil.getNameable(asClass.getOwnedProperties(), name));
	}

	/**
	 * @since 1.3
	 */
	protected @NonNull SelfType getSelfType(org.eclipse.ocl.pivot.@NonNull Package asPackage, @NonNull String name) {
		return (SelfType) ClassUtil.nonNullState(asPackage.getOwnedClass(name));
	}

	protected @NonNull SequenceType getSequenceType(org.eclipse.ocl.pivot.@NonNull Package asPackage, @NonNull String name) {
		return (SequenceType) ClassUtil.nonNullState(asPackage.getOwnedClass(name));
	}

	@Deprecated
	protected @NonNull SequenceType getSequenceType(@NonNull SequenceType unspecializedType, @NonNull Type elementType) {
		SequenceType type = createSequenceType(unspecializedType);
		addBinding(type, elementType);
		return type;
	}

	protected @NonNull SequenceType getSequenceType(@NonNull SequenceType unspecializedType, /*@NonNull*/ Type elementType, boolean isNullFree, int lower, int upper) {
		assert elementType != null;
		SequenceType type = createSequenceType(unspecializedType);
		type.setIsNullFree(isNullFree);
		type.setLowerValue(ValueUtil.integerValueOf(lower));
		type.setUpperValue(upper >= 0 ? ValueUtil.unlimitedNaturalValueOf(upper) : ValueUtil.UNLIMITED_VALUE);
		addBinding(type, elementType);
		return type;
	}

	protected @NonNull SetType getSetType(org.eclipse.ocl.pivot.@NonNull Package asPackage, @NonNull String name) {
		return (SetType) ClassUtil.nonNullState(asPackage.getOwnedClass(name));
	}

	@Deprecated
	protected @NonNull SetType getSetType(@NonNull SetType unspecializedType, @NonNull Type elementType) {
		SetType type = createSetType(unspecializedType);
		addBinding(type, elementType);
		return type;
	}

	protected @NonNull SetType getSetType(@NonNull SetType unspecializedType, /*@NonNull*/ Type elementType, boolean isNullFree, int lower, int upper) {
		assert elementType != null;
		SetType type = createSetType(unspecializedType);
		type.setIsNullFree(isNullFree);
		type.setLowerValue(ValueUtil.integerValueOf(lower));
		type.setUpperValue(upper >= 0 ? ValueUtil.unlimitedNaturalValueOf(upper) : ValueUtil.UNLIMITED_VALUE);
		addBinding(type, elementType);
		return type;
	}

	protected @NonNull TemplateParameter getTemplateParameter(@NonNull TemplateableElement templateableElement, int index) {
		return ClassUtil.nonNullState(templateableElement.getOwnedSignature().getOwnedParameters().get(index));
	}

	protected @NonNull VoidType getVoidType(org.eclipse.ocl.pivot.@NonNull Package asPackage, @NonNull String name) {
		return (VoidType) ClassUtil.nonNullState(asPackage.getOwnedClass(name));
	}

	protected <T extends CollectionType> void initTemplateParameter(@NonNull TemplateableElement asType, @NonNull TemplateParameter templateParameter) {
		TemplateSignature templateSignature = PivotFactory.eINSTANCE.createTemplateSignature();
		templateSignature.getOwnedParameters().add(templateParameter);
		asType.setOwnedSignature(templateSignature);
	}

	protected <T extends CollectionType> void initTemplateParameters(@NonNull TemplateableElement asType, TemplateParameter... templateParameters) {
		if ((templateParameters != null) && (templateParameters.length > 0)) {
			TemplateSignature templateSignature = PivotFactory.eINSTANCE.createTemplateSignature();
			for (TemplateParameter templateParameter : templateParameters) {
				templateSignature.getOwnedParameters().add(templateParameter);
			}
			asType.setOwnedSignature(templateSignature);
		}
	}

	protected void installComment(Element element, @NonNull String body) {
		Comment asComment = PivotFactory.eINSTANCE.createComment();
		asComment.setBody(body);
		element.getOwnedComments().add(asComment);
	}

	protected void setOpposites(@NonNull Property firstProperty, @NonNull Property secondProperty) {
		firstProperty.setOpposite(secondProperty);
		secondProperty.setOpposite(firstProperty);
	}
}
