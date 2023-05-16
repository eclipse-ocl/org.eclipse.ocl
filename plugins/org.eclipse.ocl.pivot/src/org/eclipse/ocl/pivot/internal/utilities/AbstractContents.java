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
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.CollectionType;
import org.eclipse.ocl.pivot.Comment;
import org.eclipse.ocl.pivot.CompleteStandardLibrary;
import org.eclipse.ocl.pivot.DataType;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.Import;
import org.eclipse.ocl.pivot.Iteration;
import org.eclipse.ocl.pivot.LambdaType;
import org.eclipse.ocl.pivot.Library;
import org.eclipse.ocl.pivot.MapType;
import org.eclipse.ocl.pivot.Model;
import org.eclipse.ocl.pivot.Namespace;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.Parameter;
import org.eclipse.ocl.pivot.ParameterVariable;
import org.eclipse.ocl.pivot.PivotFactory;
import org.eclipse.ocl.pivot.PrimitiveType;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.TemplateParameter;
import org.eclipse.ocl.pivot.TemplateSignature;
import org.eclipse.ocl.pivot.TemplateableElement;
import org.eclipse.ocl.pivot.TupleType;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.ids.PackageId;
import org.eclipse.ocl.pivot.internal.LibraryImpl;
import org.eclipse.ocl.pivot.internal.PackageImpl;
import org.eclipse.ocl.pivot.internal.library.StandardLibraryContribution;
import org.eclipse.ocl.pivot.library.LibraryFeature;
import org.eclipse.ocl.pivot.oclstdlib.OCLstdlibPackage;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.PivotConstants;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.utilities.TuplePart;
import org.eclipse.ocl.pivot.utilities.ValueUtil;

import com.google.common.collect.Lists;

public abstract class AbstractContents extends PivotUtil
{
	protected final org.eclipse.ocl.pivot.@NonNull Package orphanPackage;
	protected final @NonNull Orphanage orphanage;

	protected AbstractContents() {
		CompleteStandardLibrary standardLibrary = PivotUtilInternal.getEnvironmentFactory(null).getStandardLibrary();
		this.orphanPackage = Orphanage.createOrphanagePackage();
		this.orphanage = new Orphanage(orphanPackage, standardLibrary);
	}

	protected void addSuperClass(org.eclipse.ocl.pivot./*@NonNull*/ Class asClass, org.eclipse.ocl.pivot./*@NonNull*/ Class asSuperClass) {
		asClass.getSuperClasses().add(asSuperClass);
	}

	protected @NonNull Parameter createAccumulator(@NonNull Iteration asIteration, @NonNull String name, /*@NonNull*/ Type asType, boolean isRequired) {
		Parameter asParameter = createParameter(name, asType, isRequired);
		asIteration.getOwnedAccumulators().add(asParameter);
		return asParameter;
	}

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

	protected org.eclipse.ocl.pivot.@NonNull Class createClass(org.eclipse.ocl.pivot.@NonNull Package asPackage, @NonNull String name) {
		assert asPackage != orphanPackage;
		org.eclipse.ocl.pivot.Class asType = createClass(name);
		asPackage.getOwnedClasses().add(asType);
		return asType;
	}

	protected org.eclipse.ocl.pivot.@NonNull Class createClass(org.eclipse.ocl.pivot.@NonNull Package asPackage, /*@NonNull*/ EClass eClass) {
		assert asPackage != orphanPackage;
		org.eclipse.ocl.pivot.Class asType;
		if (eClass == OCLstdlibPackage.Literals.OCL_ANY) {
			asType = PivotFactory.eINSTANCE.createAnyType();
		}
		else if (eClass == OCLstdlibPackage.Literals.OCL_INVALID) {
			asType = PivotFactory.eINSTANCE.createInvalidType();
		}
		else if (eClass == OCLstdlibPackage.Literals.OCL_SELF) {
			asType = PivotFactory.eINSTANCE.createSelfType();
		}
		else if (eClass == OCLstdlibPackage.Literals.OCL_VOID) {
			asType = PivotFactory.eINSTANCE.createVoidType();
		}
		else {
			asType = PivotFactory.eINSTANCE.createClass();
		}
		asType.setName(eClass.getName());
		((PivotObjectImpl)asType).setESObject(eClass);
		asPackage.getOwnedClasses().add(asType);
		return asType;
	}

	protected org.eclipse.ocl.pivot.@NonNull Class createClass(org.eclipse.ocl.pivot.@NonNull Package asPackage, /*@NonNull*/ EDataType eDataType) {
		DataType asType = PivotFactory.eINSTANCE.createDataType();
		asType.setName(eDataType.getName());
		Class<?> instanceClass = eDataType.getInstanceClass();
		if (instanceClass != null) {
			asType.setInstanceClassName(eDataType.getInstanceClassName());
//			org.eclipse.ocl.pivot.Class behavioralClass = PivotUtil.getBehavioralClass(orphanage.getStandardLibrary(), instanceClass);
//			if (behavioralClass != null) {
//				asType.setBehavioralClass(behavioralClass);
//			}
		}
		((PivotObjectImpl)asType).setESObject(eDataType);
		asPackage.getOwnedClasses().add(asType);
		return asType;
	}

	protected @NonNull CollectionType createCollectionType(org.eclipse.ocl.pivot.@NonNull Package asPackage, /*@NonNull*/ EClass eClass, /*@NonNull*/ TemplateParameter templateParameter, boolean isNullFree, int lower, int upper) {
		assert asPackage != orphanPackage;
		assert templateParameter != null;
		CollectionType asType; // = (CollectionType)eClass.getEPackage().getEFactoryInstance().create(eClass);		-- XXX uses non-functional OCLstdlibFactory
		if (eClass == OCLstdlibPackage.Literals.BAG) {
			asType = PivotFactory.eINSTANCE.createBagType();
		}
		else if (eClass == OCLstdlibPackage.Literals.ORDERED_SET) {
			asType = PivotFactory.eINSTANCE.createOrderedSetType();
		}
		else if (eClass == OCLstdlibPackage.Literals.SEQUENCE) {
			asType = PivotFactory.eINSTANCE.createSequenceType();
		}
		else if (eClass == OCLstdlibPackage.Literals.SET) {
			asType = PivotFactory.eINSTANCE.createSetType();
		}
		else {
			asType = PivotFactory.eINSTANCE.createCollectionType();
		}
		asType.setName(eClass.getName());
		initTemplateParameters(asType, templateParameter);
		((PivotObjectImpl)asType).setESObject(eClass);
		asType.setIsNullFree(isNullFree);
		asType.setLower(ValueUtil.integerValueOf(lower));
		asType.setUpper(upper >= 0 ? ValueUtil.unlimitedNaturalValueOf(upper) : ValueUtil.UNLIMITED_VALUE);
		asPackage.getOwnedClasses().add(asType);
		return asType;
	}

	protected @NonNull DataType createDataType(org.eclipse.ocl.pivot.@NonNull Package asPackage, /*@NonNull*/ EDataType eDataType) {
		assert asPackage != orphanPackage;
		DataType asType = createDataType(eDataType);
		asPackage.getOwnedClasses().add(asType);
		return asType;
	}

	protected @NonNull Import createImport(@Nullable String name, @NonNull Namespace namespace) {
		Import asImport = PivotFactory.eINSTANCE.createImport();
		asImport.setName(name);
		asImport.setImportedNamespace(namespace);
		return asImport;
	}

	protected @NonNull Iteration createIteration(org.eclipse.ocl.pivot./*@NonNull*/ Class asClass, @NonNull String name, @Nullable String implementationClass, @NonNull LibraryFeature implementation, TemplateParameter... templateParameters) {
		Iteration asIteration = PivotFactory.eINSTANCE.createIteration();
		asIteration.setName(name);
		initTemplateParameters(asIteration, templateParameters);
		asIteration.setImplementationClass(implementationClass);
		asIteration.setImplementation(implementation);
		asClass.getOwnedOperations().add(asIteration);
		return asIteration;
	}

	protected @NonNull Parameter createIterator(@NonNull Iteration asIteration, @NonNull String name, /*@NonNull*/ Type asType, boolean isRequired) {
		Parameter asParameter = createParameter(name, asType, isRequired);
		asIteration.getOwnedIterators().add(asParameter);
		return asParameter;
	}

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

	protected @NonNull MapType createMapType(org.eclipse.ocl.pivot.@NonNull Package asPackage, /*@NonNull*/ EClass eClass, /*@NonNull*/ TemplateParameter keyParameter, boolean keysAreNullFree, /*@NonNull*/ TemplateParameter valueParameter, boolean valuesAreNullFree) {
		assert asPackage != orphanPackage;
		assert keyParameter != null;
		assert valueParameter != null;
		MapType asType = PivotFactory.eINSTANCE.createMapType();
		asType.setName(eClass.getName());
		initTemplateParameters(asType, keyParameter, valueParameter);
		((PivotObjectImpl)asType).setESObject(eClass);
		asType.setKeysAreNullFree(keysAreNullFree);
		asType.setValuesAreNullFree(valuesAreNullFree);
		asPackage.getOwnedClasses().add(asType);
		return asType;
	}

	protected @NonNull Model createModel(@NonNull String asURI, @NonNull EPackage ePackage) {
		Model asModel = PivotUtil.createModel(asURI);
		((PivotObjectImpl)asModel).setESObject(ePackage);
		return asModel;
	}

	protected @NonNull Operation createOperation(org.eclipse.ocl.pivot./*@NonNull*/ Class asClass, @NonNull String name, @Nullable String implementationClass, @Nullable LibraryFeature implementation, TemplateParameter... templateParameters) {
		Operation asOperation = PivotFactory.eINSTANCE.createOperation();
		asOperation.setName(name);
		initTemplateParameters(asOperation, templateParameters);
		asOperation.setImplementationClass(implementationClass);
		asOperation.setImplementation(implementation);
		asClass.getOwnedOperations().add(asOperation);
		return asOperation;
	}

	protected @NonNull Operation createOperation(org.eclipse.ocl.pivot./*@NonNull*/ Class asClass, /*@NonNull*/ EOperation eOperation, @Nullable String implementationClass, @Nullable LibraryFeature implementation) {
		Operation asOperation = PivotFactory.eINSTANCE.createOperation();
		asOperation.setName(eOperation.getName());
		asOperation.setImplementationClass(implementationClass);
		asOperation.setImplementation(implementation);
		((PivotObjectImpl)asOperation).setESObject(eOperation);
		asClass.getOwnedOperations().add(asOperation);
		return asOperation;
	}

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

	protected @NonNull Parameter createParameter(@NonNull Operation asOperation, @NonNull String name, /*@NonNull*/ Type asType, boolean isRequired) {
		Parameter asParameter = createParameter(name, asType, isRequired);
		asOperation.getOwnedParameters().add(asParameter);
		return asParameter;
	}

	protected @NonNull PrimitiveType createPrimitiveType(org.eclipse.ocl.pivot.@NonNull Package asPackage, /*@NonNull*/ EDataType eDataType) {
		assert asPackage != orphanPackage;
		PrimitiveType asType = createPrimitiveType(eDataType);
		asPackage.getOwnedClasses().add(asType);
		return asType;
	}

	protected @NonNull Property createProperty(org.eclipse.ocl.pivot.@NonNull Class asClass, @NonNull String name, /*@NonNull*/ Type type) {
		Property asProperty = createProperty(name, type);
		asClass.getOwnedProperties().add(asProperty);
		return asProperty;
	}

	public @NonNull TuplePart createTuplePart(@NonNull String name, @NonNull Type type) {
		return new TuplePart.TuplePartImpl(name, type);
	}

	protected org.eclipse.ocl.pivot.@NonNull Class getClass(org.eclipse.ocl.pivot.@NonNull Package asPackage, @NonNull String name) {
		return ClassUtil.nonNullState(asPackage.getOwnedClass(name));
	}

	protected @NonNull CollectionType getCollectionType(org.eclipse.ocl.pivot.@NonNull Package asPackage, @NonNull String name) {
		return (CollectionType) ClassUtil.nonNullState(asPackage.getOwnedClass(name));
	}

	protected @NonNull CollectionType getCollectionType(/*@NonNull*/ CollectionType genericType, /*@NonNull*/ Type elementType, boolean isNullFree, int lower, int upper) {
		assert genericType != null;
		assert elementType != null;
		return orphanage.getCollectionType(genericType, elementType, isNullFree, ValueUtil.integerValueOf(lower), upper >= 0 ? ValueUtil.unlimitedNaturalValueOf(upper) : ValueUtil.UNLIMITED_VALUE);
	}

	protected @NonNull DataType getDataType(org.eclipse.ocl.pivot.@NonNull Package asPackage, @NonNull String name) {
		return (DataType)ClassUtil.nonNullState(asPackage.getOwnedClass(name));
	}

	protected @NonNull LambdaType getLambdaType(org.eclipse.ocl.pivot./*@NonNull*/ Class oclLambdaType, /*@NonNull*/ TemplateParameter contextType, /*@NonNull*/ Type resultType, @NonNull TemplateParameter... parameterTypes) {
		assert oclLambdaType != null;
		assert contextType != null;
		assert resultType != null;
		List<@NonNull TemplateParameter> newArrayList = Lists.newArrayList(parameterTypes);
		assert newArrayList != null;
		return orphanage.getLambdaType(oclLambdaType, contextType, newArrayList, resultType);
	}

	protected @NonNull Library getLibrary(@NonNull Model asModel, @NonNull String name) {
		return (Library) ClassUtil.nonNullState(NameUtil.getNameable(asModel.getOwnedPackages(), name));
	}

	protected @NonNull MapType getMapType(/*@NonNull*/ MapType genericType, /*@NonNull*/ Type keyType, boolean keysAreNullFree, /*@NonNull*/ Type valueType, boolean valuesAreNullFree) {
		assert genericType != null;
		assert keyType != null;
		assert valueType != null;
		return orphanage.getMapType(genericType, keyType, keysAreNullFree, valueType, valuesAreNullFree);
	}

	protected @NonNull Model getModel(@NonNull String modelURI) {
		StandardLibraryContribution standardLibraryContribution = ClassUtil.nonNullState(StandardLibraryContribution.REGISTRY.get(modelURI));
		Resource resource = standardLibraryContribution.getResource();
		return ClassUtil.nonNullState((Model) resource.getContents().get(0));
	}

	protected org.eclipse.ocl.pivot.@NonNull Package getPackage(@NonNull Model asModel, @NonNull String name) {
		return ClassUtil.nonNullState(NameUtil.getNameable(asModel.getOwnedPackages(), name));
	}

	protected @NonNull Property getProperty(org.eclipse.ocl.pivot.@NonNull Class asClass, @NonNull String name) {
		return ClassUtil.nonNullState(NameUtil.getNameable(asClass.getOwnedProperties(), name));
	}

	protected @NonNull TemplateParameter getTemplateParameter(@NonNull TemplateableElement templateableElement, int index) {
		return ClassUtil.nonNullState(templateableElement.getOwnedSignature().getOwnedParameters().get(index));
	}

	protected @NonNull TupleType getTupleType(org.eclipse.ocl.pivot./*@NonNull*/ Class oclTupleType, @NonNull TuplePart @NonNull ... properties) {
		assert oclTupleType != null;
		return orphanage.getTupleType(oclTupleType, properties);
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

	protected void setOpposites(/*@NonNull*/ Property firstProperty, /*@NonNull*/ Property secondProperty) {
		firstProperty.setOpposite(secondProperty);
		secondProperty.setOpposite(firstProperty);
	}
}
