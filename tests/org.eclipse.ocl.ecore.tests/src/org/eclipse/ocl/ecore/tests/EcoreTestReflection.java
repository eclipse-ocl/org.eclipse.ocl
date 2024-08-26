/*******************************************************************************
 * Copyright (c) 2009, 2024 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *   E.D.Willink - Bug 254919, 296409
 *******************************************************************************/
package org.eclipse.ocl.ecore.tests;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EPackage.Registry;
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;
import org.eclipse.ocl.Environment;
import org.eclipse.ocl.ecore.CallOperationAction;
import org.eclipse.ocl.ecore.Constraint;
import org.eclipse.ocl.ecore.EcoreEnvironment;
import org.eclipse.ocl.ecore.OCL;
import org.eclipse.ocl.ecore.SendSignalAction;
import org.eclipse.ocl.ecore.internal.UMLReflectionImpl;
import org.eclipse.ocl.ecore.opposites.EcoreEnvironmentFactoryWithHiddenOpposites;
import org.eclipse.ocl.expressions.ExpressionsPackage;
import org.eclipse.ocl.expressions.OCLExpression;
import org.eclipse.ocl.tests.GenericTestSuite;
import org.eclipse.ocl.tests.TestReflection;

@SuppressWarnings("nls")
public class EcoreTestReflection extends UMLReflectionImpl implements TestReflection<
EObject, EPackage,
EClassifier, EClassifier, EClass, EDataType, EClassifier, EEnum,
EOperation, EParameter, EStructuralFeature, EAttribute, EReference,
EEnumLiteral, EObject, CallOperationAction, SendSignalAction, Constraint>,
TestReflection.Static<
EObject, EPackage,
EClassifier, EClassifier, EClass, EDataType, EClassifier, EEnum,
EOperation, EParameter, EStructuralFeature, EAttribute, EReference,
EEnumLiteral, EObject, CallOperationAction, SendSignalAction, Constraint>
{
	public static final EcoreTestReflection INSTANCE = new EcoreTestReflection();
	public static final String PLUGIN_ID = "org.eclipse.ocl.ecore.tests";

	public static OCL createOCL() {
		OCL newInstance = OCL.newInstance();
		String repairs = System.getProperty(PLUGIN_ID + ".repairs");
		if (repairs != null)
			newInstance.setParserRepairCount(Integer.parseInt(repairs));
		return newInstance;
	}

	/**
	 * Map of %Key to value for denormalizing OCL test code.
	 */
	private Map<String, String> normalizers = null;

	@Override
	public void addSupertype(EClass aClass, EClass superClass) {
		aClass.getESuperTypes().add(superClass);
	}

	@Override
	public EClass createClass() {
		return EcoreFactory.eINSTANCE.createEClass();
	}

	@Override
	public EAnnotation createComment() {
		return EcoreFactory.eINSTANCE.createEAnnotation();
	}

	@Override
	public void createGeneralization(EClassifier special, EClassifier general) {
		if ((special instanceof EClass) && (general instanceof EClass))
			((EClass)special).getESuperTypes().add((EClass)general);
	}

	@Override
	public EPackage createNestedPackage(EPackage aPackage, String name) {
		EPackage nestedPackage = EcoreFactory.eINSTANCE.createEPackage();
		nestedPackage.setName(name);
		aPackage.getESubpackages().add(nestedPackage);
		return nestedPackage;
	}

	@Override
	public OCL createOCL(ResourceSet resourceSet) {
		Registry packageRegistry = resourceSet.getPackageRegistry();
		EcoreEnvironment.checkRegistry(packageRegistry);			// Not needed; just providing code coverage
		return OCL.newInstance(new EcoreEnvironmentFactoryWithHiddenOpposites(packageRegistry));
	}

	@Override
	public EAttribute createOwnedAttribute(EClass aClass, String name, EClassifier type) {
		EAttribute eAttribute = EcoreFactory.eINSTANCE.createEAttribute();
		eAttribute.setName(name);
		eAttribute.setEType(type);
		aClass.getEStructuralFeatures().add(eAttribute);
		return eAttribute;
	}

	@Override
	public EClass createOwnedClass(EPackage aPackage, String name, boolean isAbstract) {
		EClass eClass = EcoreFactory.eINSTANCE.createEClass();
		eClass.setName(name);
		eClass.setAbstract(isAbstract);
		aPackage.getEClassifiers().add(eClass);
		return eClass;
	}

	@Override
	public EEnum createOwnedEnumeration(EPackage aPackage, String name) {
		EEnum eEnum = EcoreFactory.eINSTANCE.createEEnum();
		eEnum.setName(name);
		aPackage.getEClassifiers().add(eEnum);
		return eEnum;
	}

	@Override
	public EEnumLiteral createOwnedLiteral(EEnum anEnumeration, String name) {
		EEnumLiteral eLiteral = EcoreFactory.eINSTANCE.createEEnumLiteral();
		eLiteral.setName(name);
		anEnumeration.getELiterals().add(eLiteral);
		return eLiteral;
	}

	@Override
	public EOperation createOwnedOperation(EClass aClass, String name, EList<String> paramNames, EList<EClassifier> paramTypes, EClassifier type, boolean isQuery) {
		EOperation eOperation = EcoreFactory.eINSTANCE.createEOperation();
		eOperation.setName(name);
		eOperation.setEType(type);
		if (paramNames != null) {
			for (int i = 0; i < paramNames.size(); i++) {
				createOwnedParameter(eOperation, paramNames.get(i), paramTypes.get(i));
			}
		}
		aClass.getEOperations().add(eOperation);
		return eOperation;
	}

	public EParameter createOwnedParameter(EOperation eOperation, String name, EClassifier type) {
		EParameter eParameter = EcoreFactory.eINSTANCE.createEParameter();
		eParameter.setName(name);
		eParameter.setEType(type);
		eOperation.getEParameters().add(eParameter);
		return eParameter;
	}

	@Override
	public EOperation createOwnedPrimitiveOperation(EClassifier aPrimitiveType, String name, EList<String> paramNames, EList<EClassifier> paramTypes, EClassifier type, boolean isQuery) {
		return createOwnedOperation((EClass) aPrimitiveType, name, paramNames, paramTypes, type, isQuery);
	}

	@Override
	public EClass createOwnedPrimitiveType(EPackage aPackage, String name) {
		EClass eClass = EcoreFactory.eINSTANCE.createEClass();
		eClass.setName(name);
		aPackage.getEClassifiers().add(eClass);
		return eClass;
	}

	@Override
	public EReference createOwnedReference(EClass aClass, String name, EClass type) {
		EReference eReference = EcoreFactory.eINSTANCE.createEReference();
		eReference.setName(name);
		eReference.setEType(type);
		aClass.getEStructuralFeatures().add(eReference);
		return eReference;
	}

	@Override
	public EPackage createPackage(String name) {
		EPackage ePackage = EcoreFactory.eINSTANCE.createEPackage();
		ePackage.setName(name);
		return ePackage;
	}

	@Override
	public TestReflection<EObject, EPackage, EClassifier, EClassifier, EClass, EDataType, EClassifier, EEnum, EOperation, EParameter, EStructuralFeature, EAttribute, EReference, EEnumLiteral, EObject, CallOperationAction, SendSignalAction, Constraint> createReflection(
			Environment<EPackage, EClassifier, EOperation, EStructuralFeature, EEnumLiteral, EParameter, EObject, CallOperationAction, SendSignalAction, Constraint, EClass, EObject> environment) {
		return this;
	}

	@Override
	public String denormalize(String key) {
		if (normalizers == null) {
			normalizers = new HashMap<String, String>();
			normalizers.put("Date", "EDate");
			normalizers.put("Package", "EPackage");
			normalizers.put("Reference", "EReference");
			normalizers.put("String", "EString");
			normalizers.put("Type", "EClassifier");
			normalizers.put("container", "eContainer");
			normalizers.put("nestedPackage", "eSubpackages");
			normalizers.put("nestingPackage", "eSuperPackage");
			normalizers.put("opposite", "eOpposite");
			normalizers.put("ownedType", "eClassifiers");
			normalizers.put("uml", "ecore");
		}
		return normalizers.get(key);
	}

	public void disposeResourceSet(ResourceSet resourceSet) {
		if (resourceSet != null) {

			// unload and remove all resources but the UML Metamodel.
			// Don't clear the resource set's adapter-list

			for (Resource res : resourceSet.getResources()) {
				res.unload();
				res.eAdapters().clear();
			}
		}

		//        fruitPackage = null;
	}

	@Override
	public EStructuralFeature getAttribute(EClassifier classifier, String name, EClassifier type) {
		if (!(classifier instanceof EClass))
			return null;
		EStructuralFeature feature = ((EClass)classifier).getEStructuralFeature(name);
		if (feature == null)
			return null;
		// check type
		return feature;
	}

	@Override
	public EClassifier getBigDecimal() {
		return EcorePackage.Literals.EBIG_DECIMAL;
	}

	@Override
	public EClassifier getBigInteger() {
		return EcorePackage.Literals.EBIG_INTEGER;
	}

	@Override
	public OCLExpression<EClassifier> getBodyExpression(Constraint constraint) {
		return constraint.getSpecification().getBodyExpression();
	}

	@Override
	public EClassifier getClassTypeContext() {
		return EcorePackage.Literals.ECLASS;
	}

	@Override
	public EClassifier getClassifierTypeContext() {
		return EcorePackage.Literals.ECLASSIFIER;
	}

	@Override
	public EClassifier getCollectionKindTypeContext() {
		return ExpressionsPackage.Literals.COLLECTION_KIND;
	}

	@Override
	public EClassifier getCommentTypeContext() {
		return EcorePackage.Literals.EANNOTATION;
	}

	@Override
	public java.lang.Class<Constraint> getConstraintClass() {
		return Constraint.class;
	}

	public EClassifier getDefaultSetType(Environment<EPackage, EClassifier, EOperation, EStructuralFeature,
			EEnumLiteral, EParameter, EObject, CallOperationAction, SendSignalAction, Constraint,
			EClass, EObject> environment) {
		return EcorePackage.Literals.ERESOURCE;
	}

	@Override
	public EPackage getEPackage(EPackage pkg) {
		return pkg;
	}

	@Override
	public EClassifier getEcoreBigDecimal() {
		return EcorePackage.Literals.EBIG_DECIMAL;
	}

	@Override
	public EClassifier getEcoreBigInteger() {
		return EcorePackage.Literals.EBIG_INTEGER;
	}

	@Override
	public EClassifier getEcoreLong() {
		return EcorePackage.Literals.ELONG;
	}

	@Override
	public EPackage getEcorePrimitiveTypes() {	// FIXME UOE
		throw new UnsupportedOperationException(getClass().getName() + ".getEcorePrimitiveTypes");
	}

	@Override
	public String getFruitModelPath() {
		return "/model/OCLTest.ecore";
	}

	@Override
	public EClassifier getMetaclass(String name) {
		return EcorePackage.eINSTANCE.getEClassifier(name);
	}

	@Override
	public EClassifier getMetametaclass(String name) {
		return EcorePackage.eINSTANCE.getEClassifier(name);
	}

	@Override
	public String getNsURI(EPackage aPackage) {
		return aPackage.getNsURI();
	}

	public EPackage getOCLExpressionTypes() {	// FIXME UOE
		throw new UnsupportedOperationException(getClass().getName() + ".getOCLExpressionTypes");
	}

	@Override
	public EClassifier getOwnedType(EPackage aPackage, String name) {
		return aPackage.getEClassifier(name);
	}

	@Override
	public EClassifier getOwner(EOperation context) {
		return context.getEContainingClass();
	}

	@Override
	public EPackage getResourcePackage(ResourceSet resourceSet, URI uri) {
		Resource res = resourceSet.getResource(uri, true);
		return (EPackage) res.getContents().get(0);
	}

	@Override
	public EClassifier getStringTypeContext() {
		return EcorePackage.Literals.ESTRING;
	}

	@Override
	public String getTestPlugInId() {
		return PLUGIN_ID;
	}

	@Override
	public EDataType getUMLBoolean() {
		return EcorePackage.Literals.EBOOLEAN;
	}

	@Override
	public EClassifier getUMLInteger() {
		return EcorePackage.Literals.EINT;
	}

	@Override
	public EClassifier getUMLLong() {
		return EcorePackage.Literals.ELONG;
	}

	@Override
	public EPackage getUMLMetamodel() {	// FIXME UOE
		return EcorePackage.eINSTANCE;
	}

	@Override
	public EPackage getUMLPrimitiveTypes() {	// FIXME UOE
		throw new UnsupportedOperationException(getClass().getName() + ".getUMLPrimitiveTypes");
	}

	@Override
	public EDataType getUMLString() {
		return EcorePackage.Literals.ESTRING;
	}

	@Override
	public EDataType getUMLUnlimitedNatural() {	// FIXME UOE
		throw new UnsupportedOperationException(getClass().getName() + ".getUnlimitedNatural");
	}

	@Override
	public int getUnlimitedValue() {
		return -1;			// FIXME find symbolic value
	}

	/*	public Map<URI, URI> initRegistries() {
		if (uriMap != null)
			return uriMap;
		uriMap = URIMappingRegistryImpl.INSTANCE.map();
		URI oclStandardLibraryURI = URI.createURI(EcoreEnvironment.OCL_STANDARD_LIBRARY_NS_URI);
		if (uriMap.get(oclStandardLibraryURI) == null) {			// If no mapping registered then must set up standalone context
			Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put(
					"ecore", new EcoreResourceFactoryImpl());
			Environment.Registry.INSTANCE.registerEnvironment(
					new EcoreEnvironmentFactory().createEnvironment());
			String oclLocation = System.getProperty("org.eclipse.ocl.ecore");
			if (oclLocation == null)
				AbstractTestSuite.fail("'org.eclipse.ocl.ecore' property not defined; use the launch configuration to define it");
			oclLocation = "file:/" + oclLocation;
			uriMap.put(URI.createURI(EcoreEnvironment.OCL_STANDARD_LIBRARY_NS_URI), URI.createURI(oclLocation + "/model/oclstdlib.ecore"));
		}
		return uriMap;
	} */

	@Override
	public ResourceSet createResourceSet() {
		ResourceSet resourceSet = new ResourceSetImpl();
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(
			"ecore", new EcoreResourceFactoryImpl());
		resourceSet.getPackageRegistry().put(EcorePackage.eINSTANCE.getNsURI(), EcorePackage.eINSTANCE);
		GenericTestSuite.initializeTestResourceSet(resourceSet,
			"org.eclipse.emf.ecore",
			"org.eclipse.ocl",
			"org.eclipse.ocl.ecore",
			"org.eclipse.ocl.ecore.tests"
				);
		return resourceSet;
	}

	@Override
	public boolean isOrdered(String key) {
		if ("nestedPackage".equals(key)) {
			return true;
		}
		return false;
	}

	@Override
	public boolean isUnique(String key) {
		if ("nestedPackage".equals(key)) {
			return true;
		}
		return false;
	}

	@Override
	public void setAbstract(EClass aClass, boolean isAbstract) {
		aClass.setAbstract(isAbstract);
	}

	@Override
	public void setIsOrdered(EStructuralFeature aProperty, boolean isOrdered) {
		aProperty.setOrdered(isOrdered);
	}

	@Override
	public void setIsQuery(EOperation anOperation, boolean isQuery) {
		//		anOperation.setIsQuery(isQuery);
	}

	@Override
	public void setIsUnique(EStructuralFeature aProperty, boolean isUnique) {
		aProperty.setUnique(isUnique);
	}

	@Override
	public void setName(EClass aClass, String name) {
		aClass.setName(name);
	}

	@Override
	public void setNsPrefix(EPackage aPackage, String name) {
		aPackage.setNsPrefix(name);
	}

	@Override
	public void setNsURI(EPackage aPackage, String name) {
		aPackage.setNsPrefix(name);
	}

	@Override
	public void setOperationUpper(EOperation anOperation, int value) {
		anOperation.setUpperBound(value);
	}

	@Override
	public void setUpper(EStructuralFeature aProperty, int value) {
		aProperty.setUpperBound(value);
	}

	@Override
	public boolean usesCompareTo() {
		return true;
	}
}