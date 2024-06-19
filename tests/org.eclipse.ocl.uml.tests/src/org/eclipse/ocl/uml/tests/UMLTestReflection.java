/*******************************************************************************
 * Copyright (c) 2009, 2018 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *   E.D.Willink - Bug 254919, 296409
 *******************************************************************************/
package org.eclipse.ocl.uml.tests;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.ocl.Environment;
import org.eclipse.ocl.expressions.OCLExpression;
import org.eclipse.ocl.tests.GenericTestSuite;
import org.eclipse.ocl.tests.TestReflection;
import org.eclipse.ocl.uml.ExpressionInOCL;
import org.eclipse.ocl.uml.OCL;
import org.eclipse.ocl.uml.UMLEnvironment;
import org.eclipse.ocl.uml.UMLEnvironmentFactory;
import org.eclipse.ocl.uml.internal.OCLStandardLibraryImpl;
import org.eclipse.ocl.uml.internal.UMLReflectionImpl;
import org.eclipse.uml2.uml.CallOperationAction;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Comment;
import org.eclipse.uml2.uml.Constraint;
import org.eclipse.uml2.uml.DataType;
import org.eclipse.uml2.uml.Enumeration;
import org.eclipse.uml2.uml.EnumerationLiteral;
import org.eclipse.uml2.uml.LiteralUnlimitedNatural;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.PrimitiveType;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.SendSignalAction;
import org.eclipse.uml2.uml.State;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.resource.UMLResource;
import org.eclipse.uml2.uml.util.UMLUtil;

@SuppressWarnings("nls")
public class UMLTestReflection
extends UMLReflectionImpl
implements TestReflection<EObject, Package,
Type, Classifier, Class, DataType, PrimitiveType, Enumeration,
Operation, Parameter, Property, Property, Property,
EnumerationLiteral, State, CallOperationAction, SendSignalAction, Constraint>
{
	public static class Static implements TestReflection.Static<EObject, Package,
	Type, Classifier, Class, DataType, PrimitiveType, Enumeration,
	Operation, Parameter, Property, Property, Property,
	EnumerationLiteral, State, CallOperationAction, SendSignalAction, Constraint>
	{
		public static final Static INSTANCE = new Static();

		public Static() {
			org.eclipse.uml2.uml.UMLPackage.eINSTANCE.getClass();
			org.eclipse.ocl.uml.UMLPackage.eINSTANCE.getClass();
		}

		@Override
		public OCL createOCL(ResourceSet resourceSet) {
			return OCL.newInstance(resourceSet);
		}

		@Override
		public TestReflection<EObject, Package, Type, Classifier, Class, DataType, PrimitiveType, Enumeration, Operation, Parameter, Property, Property, Property, EnumerationLiteral, State, CallOperationAction, SendSignalAction, Constraint> createReflection(
				Environment<Package, Classifier, Operation, Property, EnumerationLiteral, Parameter, State, CallOperationAction, SendSignalAction, Constraint, Class, EObject> environment) {
			return new UMLTestReflection((UMLEnvironment) environment);
		}

		@Override
		public ResourceSet createResourceSet() {
			Environment.Registry.INSTANCE.registerEnvironment(
				new UMLEnvironmentFactory().createEnvironment());
			ResourceSet resourceSet = new ResourceSetImpl();
			OCL.initialize(resourceSet);
			// Make sure that the UML metamodel and primitive types
			//   libraries are loaded
			umlMetamodel = (Package) resourceSet.getResource(
				URI.createURI(UMLResource.UML_METAMODEL_URI),
				true).getContents().get(0);
			umlPrimitiveTypes = (Package) resourceSet.getResource(
				URI.createURI(UMLResource.UML_PRIMITIVE_TYPES_LIBRARY_URI),
				true).getContents().get(0);
			ecorePrimitiveTypes = (Package) resourceSet.getResource(
				URI.createURI(UMLResource.ECORE_PRIMITIVE_TYPES_LIBRARY_URI),
				true).getContents().get(0);
			GenericTestSuite.initializeTestResourceSet(resourceSet,
				"org.eclipse.emf.ecore",
				"org.eclipse.ocl",
				"org.eclipse.ocl.uml",
				"org.eclipse.ocl.uml.tests",
				"org.eclipse.uml2.uml"
					);
			return resourceSet;
		}

		@Override
		public String getTestPlugInId() {
			return PLUGIN_ID;
		}
	}

	public static final String PLUGIN_ID = "org.eclipse.ocl.uml.tests";

	public static OCL createOCL(ResourceSet resourceSet) {
		OCL newInstance = OCL.newInstance(resourceSet);
		String repairs = System.getProperty(PLUGIN_ID + ".repairs");
		if (repairs != null)
			newInstance.setParserRepairCount(Integer.parseInt(repairs));
		return newInstance;
	}

	/**
	 * Map of %Key to value for denormalizing OCL test code.
	 */
	private Map<String, String> normalizers = null;

	public UMLTestReflection(UMLEnvironment env) {
		super(env);
	}

	protected static Package umlMetamodel;
	protected static Package umlPrimitiveTypes;
	protected static Package ecorePrimitiveTypes;
	protected static Package oclMetamodel;

	@Override
	public void addSupertype(Class aClass, Class superType) {
		aClass.getSuperClasses().add(superType);
	}

	@Override
	public Class createClass() {
		return UMLFactory.eINSTANCE.createClass();
	}

	@Override
	public Comment createComment() {
		return UMLFactory.eINSTANCE.createComment();
	}

	@Override
	public void createGeneralization(Classifier special, Classifier general) {
		special.createGeneralization(general);
	}

	@Override
	public Package createNestedPackage(Package aPackage, String name) {
		return aPackage.createNestedPackage(name);
	}

	@Override
	public Property createOwnedAttribute(Class aClass, String name, Classifier type) {
		return aClass.createOwnedAttribute(name, type);
	}

	@Override
	public Class createOwnedClass(Package aPackage, String name, boolean isAbstract) {
		return aPackage.createOwnedClass(name, isAbstract);
	}

	@Override
	public Enumeration createOwnedEnumeration(Package aPackage, String name) {
		return aPackage.createOwnedEnumeration(name);
	}

	@Override
	public EnumerationLiteral createOwnedLiteral(Enumeration anEnumeration, String name) {
		return anEnumeration.createOwnedLiteral(name);
	}

	@Override
	public Operation createOwnedOperation(Class aClass, String name, EList<String> paramNames, EList<Type> paramTypes, Type type, boolean isQuery) {
		Operation operation = aClass.createOwnedOperation(name, paramNames, paramTypes, type);
		if (isQuery) {
			operation.setIsQuery(true);
		}
		return operation;
	}

	@Override
	public Operation createOwnedPrimitiveOperation(PrimitiveType aPrimitiveType, String name, EList<String> paramNames, EList<Type> paramTypes, Type type, boolean isQuery) {
		Operation operation = aPrimitiveType.createOwnedOperation(name, paramNames, paramTypes, type);
		if (isQuery) {
			operation.setIsQuery(true);
		}
		return operation;
	}

	@Override
	public PrimitiveType createOwnedPrimitiveType(Package aPackage, String name) {
		return aPackage.createOwnedPrimitiveType(name);
	}

	@Override
	public Property createOwnedReference(Class aClass, String name, Class type) {
		return aClass.createOwnedAttribute(name, type);
	}

	@Override
	public Package createPackage(String name) {
		Package uPackage = UMLFactory.eINSTANCE.createPackage();
		uPackage.setName(name);
		return uPackage;
	}

	/*	public void disposeResourceSet(ResourceSet resourceSet) {
        if (resourceSet != null) {
            if (DISPOSE_UML_METAMODEL) {
                for (Resource res : resourceSet.getResources()) {
                    res.unload();
                    res.eAdapters().clear();
                }
                resourceSet.getResources().clear();
                resourceSet.eAdapters().clear();
                resourceSet = null;

                umlMetamodel = null;
                umlPrimitiveTypes = null;
                ecorePrimitiveTypes = null;
                oclMetamodel = null;
            } else {
                // unload and remove all resources but the UML Metamodel.
                // Don't clear the resource set's adapter-list

                Set<Resource> toPreserve = new java.util.HashSet<Resource>();
                toPreserve.add(umlMetamodel.eResource());
                toPreserve.add(umlPrimitiveTypes.eResource());
                toPreserve.add(ecorePrimitiveTypes.eResource());
//                toPreserve.add(oclExpressionTypes.eResource());

                for (Resource res : resourceSet.getResources()) {
                    if (!toPreserve.contains(res)) {
                        res.unload();
                        res.eAdapters().clear();
                    }
                }

                resourceSet.getResources().retainAll(toPreserve);
            }
        }

//        fruitPackage = null;
	} */

	@Override
	public String denormalize(String key) {
		if (normalizers == null) {
			normalizers = new HashMap<String, String>();
			normalizers.put("Reference", "Property");
			normalizers.put("String", "\"String\"");
			normalizers.put("uml", "UML");
		}
		return normalizers.get(key);
	}

	@Override
	public Property getAttribute(Classifier classifier, String name, Type type) {
		return classifier.getAttribute(name, type);
	}

	@Override
	public Classifier getBigDecimal() {
		return (PrimitiveType) ecorePrimitiveTypes.getOwnedType("EBigDecimal");
	}

	@Override
	public Classifier getBigInteger() {
		return (PrimitiveType) ecorePrimitiveTypes.getOwnedType("EBigInteger");
	}

	@Override
	public OCLExpression<Classifier> getBodyExpression(Constraint constraint) {
		return ((ExpressionInOCL) constraint.getSpecification()).getBodyExpression();
	}

	@Override
	public Classifier getClassTypeContext() {
		return (Classifier) umlMetamodel.getOwnedType("Class");
	}

	@Override
	public Classifier getClassifierTypeContext() {
		return (Classifier) umlMetamodel.getOwnedType("Classifier");
	}

	@Override
	public Classifier getCollectionKindTypeContext() {
		Package expressionsPackage = oclMetamodel.getNestedPackage("expressions");
		return (Classifier) expressionsPackage.getOwnedType("CollectionKind");
	}

	@Override
	public Classifier getCommentTypeContext() {
		return (Classifier) umlMetamodel.getOwnedType("Comment");
	}

	@Override
	public java.lang.Class<Constraint> getConstraintClass() {
		return Constraint.class;
	}

	//	public Classifier getDefaultSetType(Environment<Package, Classifier, Operation, Property, EnumerationLiteral, Parameter, State, CallOperationAction, SendSignalAction, Constraint, Class, EObject> environment) {
	//		return environment.getOCLStandardLibrary().getInvalid();
	//	}

	@Override
	public EPackage getEPackage(Package pkg) {
		return UMLUtil.convertToEcore(pkg, null).iterator().next();
	}

	@Override
	public PrimitiveType getEcoreBigDecimal() {
		return (PrimitiveType) ecorePrimitiveTypes.getOwnedType("EBigDecimal");
	}

	@Override
	public PrimitiveType getEcoreBigInteger() {
		return (PrimitiveType) ecorePrimitiveTypes.getOwnedType("EBigInteger");
	}

	@Override
	public PrimitiveType getEcoreLong() {
		return (PrimitiveType) ecorePrimitiveTypes.getOwnedType("ELong");
	}

	@Override
	public Package getEcorePrimitiveTypes() {
		return ecorePrimitiveTypes;
	}

	@Override
	public String getFruitModelPath() {
		return "/model/OCLTest.uml";
	}

	@Override
	public Classifier getMetaclass(String name) {
		return (Classifier) umlMetamodel.getOwnedType(name);
	}

	@Override
	public Classifier getMetametaclass(String name) {
		return (Classifier) umlMetamodel.getOwnedType(name);
	}

	public Package getOCLMetaModel() {
		return oclMetamodel;
	}

	@Override
	public String getNsURI(Package aPackage) {	// FIXME UOE
		throw new UnsupportedOperationException(getClass().getName() + ".getNsURI");
	}

	@Override
	public Type getOwnedType(Package aPackage, String name) {
		return aPackage.getOwnedType(name);
	}

	@Override
	public Classifier getOwner(Operation context) {
		return (Classifier) context.getOwner();
	}

	@Override
	public Package getResourcePackage(ResourceSet resourceSet, URI uri) {
		Resource res = resourceSet.getResource(uri, true);
		return (Package) res.getContents().get(0);
	}

	@Override
	public Classifier getStringTypeContext() {
		return OCLStandardLibraryImpl.INSTANCE.getString();
	}

	@Override
	public PrimitiveType getUMLBoolean() {
		return (PrimitiveType) umlPrimitiveTypes.getOwnedType("Boolean");
	}

	@Override
	public PrimitiveType getUMLInteger() {
		return (PrimitiveType) umlPrimitiveTypes.getOwnedType("Integer");
	}

	@Override
	public PrimitiveType getUMLLong() {
		return (PrimitiveType) ecorePrimitiveTypes.getOwnedType("ELong");
	}

	@Override
	public Package getUMLMetamodel() {
		return umlMetamodel;
	}

	@Override
	public Package getUMLPrimitiveTypes() {
		return umlPrimitiveTypes;
	}

	@Override
	public PrimitiveType getUMLString() {
		return (PrimitiveType) umlPrimitiveTypes.getOwnedType("String");
	}

	@Override
	public PrimitiveType getUMLUnlimitedNatural() {
		return (PrimitiveType) umlPrimitiveTypes.getOwnedType("UnlimitedNatural");
	}

	@Override
	public int getUnlimitedValue() {
		return LiteralUnlimitedNatural.UNLIMITED;
	}

	/*	public Map<URI, URI> initRegistries() {
		if (uriMap != null)
			return uriMap;
		uriMap = URIMappingRegistryImpl.INSTANCE.map();
		URI oclStandardLibraryURI = URI.createURI(UMLEnvironment.OCL_STANDARD_LIBRARY_NS_URI);
		if (uriMap.get(oclStandardLibraryURI) == null) {			// If no mapping registered then must set up standalone context
			Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put(
					UMLResource.FILE_EXTENSION, UMLResource.Factory.INSTANCE);
			Environment.Registry.INSTANCE.registerEnvironment(
					new UMLEnvironmentFactory().createEnvironment());
			String oclLocation = System.getProperty("org.eclipse.ocl.uml");
			if (oclLocation == null)
				AbstractTestSuite.fail("'org.eclipse.ocl.uml' property not defined; use the launch configuration to define it");
			oclLocation = "file:/" + oclLocation;
			uriMap.put(URI.createURI(UMLEnvironment.OCL_STANDARD_LIBRARY_NS_URI), URI.createURI(oclLocation + "/model/oclstdlib.uml"));
			String resourcesLocation = System.getProperty("org.eclipse.uml2.uml.resources");
			if (resourcesLocation == null)
				AbstractTestSuite.fail("'org.eclipse.uml2.uml.resources' property not defined; use the launch configuration to define it");
			resourcesLocation = "file:/" + resourcesLocation;
			uriMap.put(URI.createURI(UMLResource.STANDARD_PROFILE_URI), URI.createURI(resourcesLocation + "/profiles/Standard.profile.uml"));
			uriMap.put(URI.createURI(UMLResource.ECORE_PROFILE_URI), URI.createURI(resourcesLocation + "/profiles/Ecore.profile.uml"));
			uriMap.put(URI.createURI(UMLResource.UML_METAMODEL_URI), URI.createURI(resourcesLocation + "/metamodels/UML.metamodel.uml"));
			uriMap.put(URI.createURI(UMLResource.UML_PRIMITIVE_TYPES_LIBRARY_URI), URI.createURI(resourcesLocation + "/libraries/UMLPrimitiveTypes.library.uml"));
			uriMap.put(URI.createURI(UMLResource.ECORE_PRIMITIVE_TYPES_LIBRARY_URI), URI.createURI(resourcesLocation + "/libraries/EcorePrimitiveTypes.library.uml"));
			resourcesLocation = System.getProperty("org.eclipse.ocl");
			if (resourcesLocation == null)
				AbstractTestSuite.fail("'org.eclipse.ocl' property not defined; use the launch configuration to define it");
			resourcesLocation = "file:/" + resourcesLocation;
			uriMap.put(URI.createURI(OCL.OCL_METAMODEL_URI), URI.createURI(resourcesLocation + "/model/OCL.uml"));
		}
		OCLStandardLibraryImpl.INSTANCE.getClass();		// Ensure OCLStandardLibrary loaded before use
		return uriMap;
	} */

	/*	public void initResourceSet(ResourceSet resourceSet) {
		// also make sure that the UML metamodel and primitive types
		//   libraries are loaded
		if (umlMetamodel == null) {
			umlMetamodel = (Package) resourceSet.getResource(
				URI.createURI(UMLResource.UML_METAMODEL_URI),
				true).getContents().get(0);
		}
		if (umlPrimitiveTypes == null) {
			umlPrimitiveTypes = (Package) resourceSet.getResource(
				URI.createURI(UMLResource.UML_PRIMITIVE_TYPES_LIBRARY_URI),
				true).getContents().get(0);
		}
		if (ecorePrimitiveTypes == null) {
			ecorePrimitiveTypes = (Package) resourceSet.getResource(
				URI.createURI(UMLResource.ECORE_PRIMITIVE_TYPES_LIBRARY_URI),
				true).getContents().get(0);
		}
		if (oclMetamodel == null) {
			oclMetamodel = (Package) resourceSet.getResource(
				URI.createURI(OCL.OCL_METAMODEL_URI),
				true).getContents().get(0);
		}
    } */

	@Override
	public boolean isOrdered(String key) {
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
	public void setAbstract(Class aClass, boolean isAbstract) {
		aClass.setIsAbstract(isAbstract);
	}

	@Override
	public void setIsOrdered(Property aProperty, boolean isOrdered) {
		aProperty.setIsOrdered(isOrdered);
	}

	@Override
	public void setIsQuery(Operation anOperation, boolean isQuery) {
		anOperation.setIsQuery(isQuery);
	}

	@Override
	public void setIsUnique(Property aProperty, boolean isUnique) {
		aProperty.setIsUnique(isUnique);
	}

	@Override
	public void setName(Class aClass, String name) {
		aClass.setName(name);
	}

	@Override
	public void setNsPrefix(Package aPackage, String name) {
		//		aPackage.setNsPrefix(name);
	}

	@Override
	public void setNsURI(Package aPackage, String name) {
		//		aPackage.setNsPrefix(name);
	}

	@Override
	public void setOperationUpper(Operation anOperation, int value) {
		anOperation.setUpper(value);
	}

	@Override
	public void setUpper(Property aProperty, int value) {
		aProperty.setUpper(value);
	}

	@Override
	public boolean usesCompareTo() {
		return false;
	}
}