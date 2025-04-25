/*******************************************************************************
 * Copyright (c) 2012, 2025 Willink Transformations and others.
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
import java.util.Objects;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.AnyType;
import org.eclipse.ocl.pivot.BagType;
import org.eclipse.ocl.pivot.BooleanType;
import org.eclipse.ocl.pivot.Class;
import org.eclipse.ocl.pivot.CollectionType;
import org.eclipse.ocl.pivot.Comment;
import org.eclipse.ocl.pivot.Constraint;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.Import;
import org.eclipse.ocl.pivot.InvalidType;
import org.eclipse.ocl.pivot.Iteration;
import org.eclipse.ocl.pivot.Library;
import org.eclipse.ocl.pivot.MapType;
import org.eclipse.ocl.pivot.Model;
import org.eclipse.ocl.pivot.Namespace;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.OrderedSetType;
import org.eclipse.ocl.pivot.Package;
import org.eclipse.ocl.pivot.ParameterVariable;
import org.eclipse.ocl.pivot.PivotFactory;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.SelfType;
import org.eclipse.ocl.pivot.SequenceType;
import org.eclipse.ocl.pivot.SetType;
import org.eclipse.ocl.pivot.StringLiteralExp;
import org.eclipse.ocl.pivot.TemplateBinding;
import org.eclipse.ocl.pivot.TemplateParameter;
import org.eclipse.ocl.pivot.TemplateParameterSubstitution;
import org.eclipse.ocl.pivot.TemplateSignature;
import org.eclipse.ocl.pivot.TemplateableElement;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.VoidType;
import org.eclipse.ocl.pivot.ids.PackageId;
import org.eclipse.ocl.pivot.internal.ConstraintImpl;
import org.eclipse.ocl.pivot.internal.LibraryImpl;
import org.eclipse.ocl.pivot.internal.PackageImpl;
import org.eclipse.ocl.pivot.internal.library.StandardLibraryContribution;
import org.eclipse.ocl.pivot.library.LibraryFeature;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.PivotConstants;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.utilities.StringUtil;
import org.eclipse.ocl.pivot.values.Unlimited;

public abstract class AbstractContents extends PivotUtil
{
	/**
	 * @since 1.23
	 */
	public static abstract class AbstractMetamodelContents extends AbstractContents
	{
		protected final @NonNull Package standardLibrary;
		private final @NonNull Class booleanType;
		private final @NonNull Class stringType;

		protected AbstractMetamodelContents(@NonNull Package standardLibrary) {
			this.standardLibrary = standardLibrary;
			this.booleanType = Objects.requireNonNull(standardLibrary.getOwnedClass("Boolean"));
			this.stringType = Objects.requireNonNull(standardLibrary.getOwnedClass("String"));
		}

		protected @NonNull Constraint createInvariant(/*@NonNull*/ EOperation esObject, @NonNull String name, @NonNull String body) {
			assert esObject != null;
			ConstraintImpl constraint = (ConstraintImpl)PivotFactory.eINSTANCE.createConstraint();
			ExpressionInOCL expressionInOCL = PivotFactory.eINSTANCE.createExpressionInOCL();
			StringLiteralExp stringLiteral = PivotFactory.eINSTANCE.createStringLiteralExp();
			stringLiteral.setStringSymbol(body);
			stringLiteral.setType(stringType);
			expressionInOCL.setOwnedBody(stringLiteral);
			expressionInOCL.setType(booleanType);
			constraint.setName(name);
			constraint.setOwnedSpecification(expressionInOCL);
			constraint.setESObject(esObject);
			return constraint;
		}
	}

	/**
	 * @since 1.4
	 */
	protected static void addBinding(@NonNull TemplateableElement specializedType, @NonNull Type actualType) {
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
		TemplateSignature templateSignature = unspecializedType.getOwnedSignature();
		assert templateSignature != null;
		List<@NonNull TemplateParameter> templateParameters = PivotUtilInternal.getOwnedParametersList(templateSignature);
		TemplateParameter templateParameter = templateParameters.get(parameterSubstitutions.size());
		assert templateParameter != null;
		TemplateParameterSubstitution templateParameterSubstitution = createTemplateParameterSubstitution(templateParameter, actualType);
		parameterSubstitutions.add(templateParameterSubstitution);
	}

	/* @deprecated no longer used. Replaced by EClass literal and TemplateParameter */
	@Deprecated
	protected @NonNull BagType createBagType(@NonNull String name, @Nullable String lower, @Nullable String upper, @NonNull TemplateParameter templateParameter) {
		return createCollectionType(PivotFactory.eINSTANCE.createBagType(), name, lower, upper, templateParameter);
	}

	/**
	 * @since 1.17
	 */
	protected @NonNull BagType createBagType(/*@NonNull*/ EClass eClass, @NonNull TemplateParameter templateParameter) {
		BagType pivotType = PivotFactory.eINSTANCE.createBagType();
		pivotType.setName(eClass.getName());
		initTemplateParameters(pivotType, templateParameter);
		((PivotObjectImpl)pivotType).setESObject(eClass);
		return pivotType;
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
	protected @NonNull ExpressionInOCL createBodyExpression(@NonNull Operation operation, @NonNull Type selfType, @NonNull String exprString, @NonNull Type resultType) {
		ExpressionInOCL pivotExpression = PivotFactory.eINSTANCE.createExpressionInOCL();
		pivotExpression.setType(resultType);
		pivotExpression.setBody(exprString);
		ParameterVariable contextVariable = PivotFactory.eINSTANCE.createParameterVariable();
		contextVariable.setName(PivotConstants.SELF_NAME);
		contextVariable.setType(selfType);
		contextVariable.setIsRequired(!operation.isIsValidating());
		pivotExpression.setOwnedContext(contextVariable);
		operation.setBodyExpression(pivotExpression);
		return pivotExpression;
	}

	/**
	 * @since 1.17
	 */
	protected @NonNull CollectionType createCollectionType(/*@NonNull*/ EClass eClass, @NonNull TemplateParameter templateParameter) {
		CollectionType pivotType = PivotFactory.eINSTANCE.createCollectionType();
		pivotType.setName(eClass.getName());
		initTemplateParameters(pivotType, templateParameter);
		((PivotObjectImpl)pivotType).setESObject(eClass);
		return pivotType;
	}

	protected @NonNull <@NonNull T extends CollectionType> T createCollectionType(@NonNull T pivotType, @NonNull String name, @Nullable  String lower, @Nullable String upper, @NonNull TemplateParameter templateParameter) {
		pivotType.setName(name);
		pivotType.setLower(lower != null ? StringUtil.createNumberFromString(lower) : Integer.valueOf(0));
		pivotType.setUpper(upper != null ? StringUtil.createNumberFromString(upper) : Unlimited.INSTANCE);
		initTemplateParameter(pivotType, templateParameter);
		return pivotType;
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
		ExpressionInOCL pivotExpression = PivotFactory.eINSTANCE.createExpressionInOCL();
		pivotExpression.setType(type);
		pivotExpression.setBody(exprString);
//		ParameterVariable contextVariable = PivotFactory.eINSTANCE.createParameterVariable();
//		contextVariable.setName(PivotConstants.SELF_NAME);
//		contextVariable.setType(type);
//		pivotExpression.setOwnedContext(contextVariable);
		return pivotExpression;
	}

	/**
	 * @since 1.15
	 */
	@Deprecated /* use createBodyExpression */
	protected @NonNull ExpressionInOCL createExpressionInOCL(@NonNull Type selfType, @NonNull String exprString, @NonNull Type resultType) {
		ExpressionInOCL pivotExpression = PivotFactory.eINSTANCE.createExpressionInOCL();
		pivotExpression.setType(resultType);
		pivotExpression.setBody(exprString);
		ParameterVariable contextVariable = PivotFactory.eINSTANCE.createParameterVariable();
		contextVariable.setName(PivotConstants.SELF_NAME);
		contextVariable.setType(selfType);
		contextVariable.setIsRequired(true);
		pivotExpression.setOwnedContext(contextVariable);
		return pivotExpression;
	}

	protected @NonNull Import createImport(@Nullable String name, @NonNull Namespace namespace) {
		Import asImport = PivotFactory.eINSTANCE.createImport();
		asImport.setName(name);
		asImport.setImportedNamespace(namespace);
		return asImport;
	}

	protected @NonNull Iteration createIteration(@NonNull String name, @NonNull Type type, @Nullable String implementationClass, @NonNull LibraryFeature implementation, TemplateParameter... templateParameters) {
		Iteration pivotIteration = createIteration(name, type, implementationClass, implementation);
		initTemplateParameters(pivotIteration, templateParameters);
		return pivotIteration;
	}

	@Deprecated /* @deprecated add ePackage */
	protected @NonNull Library createLibrary(@NonNull String name, @NonNull String nsPrefix, @NonNull String nsURI, @Nullable PackageId packageId) {
		Library pivotLibrary = PivotFactory.eINSTANCE.createLibrary();
		pivotLibrary.setName(name);
		pivotLibrary.setNsPrefix(nsPrefix);
		if (packageId != null) {
			((LibraryImpl)pivotLibrary).setPackageId(packageId);  // FIXME Add to API
		}
		pivotLibrary.setURI(nsURI);
		return pivotLibrary;
	}

	/**
	 * @since 1.17
	 */
	protected @NonNull Library createLibrary(@NonNull String name, @NonNull String nsPrefix, @NonNull String nsURI, @Nullable PackageId packageId, @Nullable EPackage ePackage) {
		Library pivotLibrary = PivotFactory.eINSTANCE.createLibrary();
		pivotLibrary.setName(name);
		pivotLibrary.setNsPrefix(nsPrefix);
		if (packageId != null) {
			((LibraryImpl)pivotLibrary).setPackageId(packageId);  // FIXME Add to API
		}
		pivotLibrary.setURI(nsURI);
		if (ePackage != null) {
			((PivotObjectImpl)pivotLibrary).setESObject(ePackage);
		}
		return pivotLibrary;
	}

	@Deprecated /* @deprecated no longer used */
	protected @NonNull MapType createMapType(/*@NonNull*/ MapType pivotType, @NonNull String name, @NonNull TemplateParameter keyParameter, @NonNull TemplateParameter valueParameter) {
		pivotType.setName(name);
		initTemplateParameters(pivotType, keyParameter, valueParameter);
		return pivotType;
	}

	/**
	 * @since 1.17
	 */
	protected @NonNull MapType createMapType(/*@NonNull*/ EClass eClass, @NonNull TemplateParameter keyParameter, @NonNull TemplateParameter valueParameter) {
		MapType mapType = PivotFactory.eINSTANCE.createMapType();
		mapType.setName(eClass.getName());
		initTemplateParameters(mapType, keyParameter, valueParameter);
		((PivotObjectImpl)mapType).setESObject(eClass);
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
		Model pivotModel = PivotUtil.createModel(asURI);
		((PivotObjectImpl)pivotModel).setESObject(ePackage);
		return pivotModel;
	}

	protected @NonNull Operation createOperation(@NonNull String name, @NonNull Type type, @Nullable String implementationClass, @Nullable LibraryFeature implementation, TemplateParameter... templateParameters) {
		Operation pivotOperation = createOperation(name, type, implementationClass, implementation);
		initTemplateParameters(pivotOperation, templateParameters);
		return pivotOperation;
	}

	/**
	 * @since 1.17
	 */
	protected @NonNull OrderedSetType createOrderedSetType(/*@NonNull*/ EClass eClass, @NonNull TemplateParameter templateParameter) {
		OrderedSetType pivotType = PivotFactory.eINSTANCE.createOrderedSetType();
		pivotType.setName(eClass.getName());
		initTemplateParameters(pivotType, templateParameter);
		((PivotObjectImpl)pivotType).setESObject(eClass);
		return pivotType;
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
		org.eclipse.ocl.pivot.Package pivotPackage = PivotFactory.eINSTANCE.createPackage();
		pivotPackage.setName(name);
		pivotPackage.setNsPrefix(nsPrefix);
		if (packageId != null) {
			((PackageImpl)pivotPackage).setPackageId(packageId);  // FIXME Add to API
		}
		pivotPackage.setURI(nsURI);
		if (ePackage != null) {
			((PivotObjectImpl)pivotPackage).setESObject(ePackage);
		}
		return pivotPackage;
	}

	/**
	 * @since 1.17
	 */
	protected @NonNull SequenceType createSequenceType(/*@NonNull*/ EClass eClass, @NonNull TemplateParameter templateParameter) {
		SequenceType pivotType = PivotFactory.eINSTANCE.createSequenceType();
		pivotType.setName(eClass.getName());
		initTemplateParameters(pivotType, templateParameter);
		((PivotObjectImpl)pivotType).setESObject(eClass);
		return pivotType;
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
	protected @NonNull SetType createSetType(/*@NonNull*/ EClass eClass, @NonNull TemplateParameter templateParameter) {
		SetType pivotType = PivotFactory.eINSTANCE.createSetType();
		pivotType.setName(eClass.getName());
		initTemplateParameters(pivotType, templateParameter);
		((PivotObjectImpl)pivotType).setESObject(eClass);
		return pivotType;
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
		return (AnyType) Objects.requireNonNull(asPackage.getOwnedClass(name));
	}

	protected @NonNull BagType getBagType(org.eclipse.ocl.pivot.@NonNull Package asPackage, @NonNull String name) {
		return (BagType) Objects.requireNonNull(asPackage.getOwnedClass(name));
	}

	/**
	 * @since 1.18
	 */
	protected @NonNull BooleanType getBooleanType(org.eclipse.ocl.pivot.@NonNull Package asPackage, @NonNull String name) {
		return (BooleanType) Objects.requireNonNull(asPackage.getOwnedClass(name));
	}

	protected org.eclipse.ocl.pivot.@NonNull Class getClass(org.eclipse.ocl.pivot.@NonNull Package asPackage, @NonNull String name) {
		return Objects.requireNonNull(asPackage.getOwnedClass(name));
	}

	protected @NonNull CollectionType getCollectionType(org.eclipse.ocl.pivot.@NonNull Package asPackage, @NonNull String name) {
		return (CollectionType) Objects.requireNonNull(asPackage.getOwnedClass(name));
	}

	protected @NonNull InvalidType getInvalidType(org.eclipse.ocl.pivot.@NonNull Package asPackage, @NonNull String name) {
		return (InvalidType) Objects.requireNonNull(asPackage.getOwnedClass(name));
	}

	protected @NonNull Library getLibrary(@NonNull Model asModel, @NonNull String name) {
		return (Library) Objects.requireNonNull(NameUtil.getNameable(asModel.getOwnedPackages(), name));
	}

	protected @NonNull Model getModel(@NonNull String modelURI) {
		StandardLibraryContribution standardLibraryContribution = Objects.requireNonNull(StandardLibraryContribution.REGISTRY.get(modelURI));
		Resource resource = standardLibraryContribution.getResource();
		return Objects.requireNonNull((Model) resource.getContents().get(0));
	}

	protected @NonNull OrderedSetType getOrderedSetType(org.eclipse.ocl.pivot.@NonNull Package asPackage, @NonNull String name) {
		return (OrderedSetType) Objects.requireNonNull(asPackage.getOwnedClass(name));
	}

	protected org.eclipse.ocl.pivot.@NonNull Package getPackage(@NonNull Model asModel, @NonNull String name) {
		return Objects.requireNonNull(NameUtil.getNameable(asModel.getOwnedPackages(), name));
	}

	/**
	 * @since 1.18
	 */
	protected org.eclipse.ocl.pivot.@NonNull Class getPrimitiveType(org.eclipse.ocl.pivot.@NonNull Package asPackage, @NonNull String name) {
		return Objects.requireNonNull(asPackage.getOwnedClass(name));
	}

	protected @NonNull Property getProperty(org.eclipse.ocl.pivot.@NonNull Class asClass, @NonNull String name) {
		return Objects.requireNonNull(NameUtil.getNameable(asClass.getOwnedProperties(), name));
	}

	/**
	 * @since 1.3
	 */
	protected @NonNull SelfType getSelfType(org.eclipse.ocl.pivot.@NonNull Package asPackage, @NonNull String name) {
		return (SelfType) Objects.requireNonNull(asPackage.getOwnedClass(name));
	}

	protected @NonNull SequenceType getSequenceType(org.eclipse.ocl.pivot.@NonNull Package asPackage, @NonNull String name) {
		return (SequenceType) Objects.requireNonNull(asPackage.getOwnedClass(name));
	}

	protected @NonNull SetType getSetType(org.eclipse.ocl.pivot.@NonNull Package asPackage, @NonNull String name) {
		return (SetType) Objects.requireNonNull(asPackage.getOwnedClass(name));
	}

	protected @NonNull TemplateParameter getTemplateParameter(@NonNull TemplateableElement templateableElement, int index) {
		return Objects.requireNonNull(templateableElement.getOwnedSignature().getOwnedParameters().get(index));
	}

	protected @NonNull VoidType getVoidType(org.eclipse.ocl.pivot.@NonNull Package asPackage, @NonNull String name) {
		return (VoidType) Objects.requireNonNull(asPackage.getOwnedClass(name));
	}

	protected <T extends CollectionType> void initTemplateParameter(@NonNull TemplateableElement pivotType, @NonNull TemplateParameter templateParameter) {
		TemplateSignature templateSignature = PivotFactory.eINSTANCE.createTemplateSignature();
		templateSignature.getOwnedParameters().add(templateParameter);
		pivotType.setOwnedSignature(templateSignature);
	}

	protected <T extends CollectionType> void initTemplateParameters(@NonNull TemplateableElement pivotType, TemplateParameter... templateParameters) {
		if ((templateParameters != null) && (templateParameters.length > 0)) {
			TemplateSignature templateSignature = PivotFactory.eINSTANCE.createTemplateSignature();
			for (TemplateParameter templateParameter : templateParameters) {
				templateSignature.getOwnedParameters().add(templateParameter);
			}
			pivotType.setOwnedSignature(templateSignature);
		}
	}

	protected void installComment(Element element, @NonNull String body) {
		Comment pivotComment = PivotFactory.eINSTANCE.createComment();
		pivotComment.setBody(body);
		element.getOwnedComments().add(pivotComment);
	}
}
