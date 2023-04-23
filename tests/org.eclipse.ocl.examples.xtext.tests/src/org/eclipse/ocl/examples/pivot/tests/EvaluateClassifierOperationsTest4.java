/*******************************************************************************
 * Copyright (c) 2010, 2021 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   L.Goubet, E.D.Willink - Initial API and implementation
 *   E.D.Willink (CEA LIST) - Bug 388529
 *******************************************************************************/

package org.eclipse.ocl.examples.pivot.tests;

import java.util.Arrays;
import java.util.Collection;

import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.xtext.tests.TestFileSystem;
import org.eclipse.ocl.pivot.Model;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.ids.CollectionTypeId;
import org.eclipse.ocl.pivot.ids.IdResolver;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.manager.MetamodelManagerInternal;
import org.eclipse.ocl.pivot.internal.messages.PivotMessagesInternal;
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal.EnvironmentFactoryInternalExtension;
import org.eclipse.ocl.pivot.resource.ProjectManager;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.MetamodelManager;
import org.eclipse.ocl.pivot.utilities.OCL;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/**
 * Tests for OclAny operations.
 */
@RunWith(value = Parameterized.class)
public class EvaluateClassifierOperationsTest4 extends PivotTestSuite
{
	@Parameters
	public static Collection<Object[]> data() {
		Object[][] data = new Object[][]{{false}, {true}};
		return Arrays.asList(data);
	}

	public static class MyOCL extends TestOCL
	{
		// need a metamodel that has a reflexive EReference.
		// Ecore will do nicely. Create the following structure:
		// pkg1
		// pkg1::pkg2
		// pkg1::pkg2::jim
		// pkg1::bob
		// pkg1::pkg3
		// pkg1::pkg3::pkg4
		// pkg1::pkg3::pkg5
		// pkg1::pkg3::pkg5::george
		@NonNull Model root = PivotUtil.createModel(null);
		org.eclipse.ocl.pivot.@NonNull Package pkg1 = PivotUtil.createOwnedPackage(root, "pkg1");
		org.eclipse.ocl.pivot.@NonNull Package pkg2 = PivotUtil.createOwnedPackage(pkg1, "pkg2");
		org.eclipse.ocl.pivot.@NonNull Package jim = PivotUtil.createOwnedPackage(pkg2, "jim");
		org.eclipse.ocl.pivot.@NonNull Package bob = PivotUtil.createOwnedPackage(pkg1, "bob");
		org.eclipse.ocl.pivot.@NonNull Package pkg3 = PivotUtil.createOwnedPackage(pkg1, "pkg3");
		org.eclipse.ocl.pivot.@NonNull Package pkg4 = PivotUtil.createOwnedPackage(pkg3, "pkg4");
		org.eclipse.ocl.pivot.@NonNull Package pkg5 = PivotUtil.createOwnedPackage(pkg3, "pkg5");
		org.eclipse.ocl.pivot.@NonNull Package george = PivotUtil.createOwnedPackage(pkg5, "george");

		public MyOCL(@NonNull TestFileSystem testFileSystem, @NonNull String testPackageName, @NonNull String name, @NonNull ProjectManager projectManager, @Nullable ResourceSet externalResourceSet) {
			super(testFileSystem, testPackageName, name, projectManager, externalResourceSet);
			MetamodelManagerInternal metamodelManager = getMetamodelManager();
			//			metamodelManager.addGlobalNamespace(PivotConstants.OCL_NAME, ClassUtil.nonNullState(metamodelManager.getASmetamodel()));

			metamodelManager.installRoot(ClassUtil.nonNullState(root));
			//	        helper.setContext(ClassUtil.nonNullState(metamodelManager.getPivotType("Package")));
		}
	}

	public EvaluateClassifierOperationsTest4(boolean useCodeGen) {
		super(useCodeGen);
	}

	//	@Override
	protected @NonNull MyOCL createOCL(@NonNull ProjectManager classPath, @Nullable ResourceSet externalResourceSet) {
		return new MyOCL(getTestFileSystem(), getTestPackageName(), getName(), classPath, externalResourceSet);
	}

	@Override
	protected @NonNull String getTestPackageName() {
		return "EvaluateClassifierOperations";
	}

	@Override
	@Before public void setUp() throws Exception {
//		BaseLinkingService.DEBUG_RETRY.setState(true);
		super.setUp();
	}

	@Override
	@After public void tearDown() throws Exception {
		super.tearDown();
	}

	/**
	 * Tests the allInstances() operator.
	 */
	@Test public void test_allInstances() {
		MyOCL ocl = createOCL(OCL.CLASS_PATH, null);
		MetamodelManager metamodelManager = ocl.getMetamodelManager();
		try {
			org.eclipse.ocl.pivot.Class classType = metamodelManager.getStandardLibrary().getClassType();
			//
			ocl.assertQueryResults(null, "Set{true,false}", "Boolean.allInstances()");
			ocl.assertQueryResults(null, "Set{true,false}", "true.oclType().allInstances()");
			ocl.assertSemanticErrorQuery(null, "true.allInstances()", PivotMessagesInternal.UnresolvedOperationCall_ERROR_, "Boolean", "allInstances", "");
			//
			ocl.assertQueryResults(null, "Set{}", "Integer.allInstances()");
			ocl.assertQueryResults(null, "Set{}", "4.oclType().allInstances()");
			ocl.assertSemanticErrorQuery(null, "4.allInstances()", PivotMessagesInternal.UnresolvedOperationCall_ERROR_, "Integer", "allInstances", "");
			//
			ocl.assertQueryResults(null, "Set{}", "String.allInstances()");
			ocl.assertQueryResults(null, "Set{}", "''.oclType().allInstances()");
			ocl.assertSemanticErrorQuery(null, "''.allInstances()", PivotMessagesInternal.UnresolvedOperationCall_ERROR_, "String", "allInstances", "");
			//
			ocl.assertQueryResults(null, "Set{}", "OclAny.allInstances()");
			//
			ocl.assertQueryResults(null, "Set{null}", "OclVoid.allInstances()");
			ocl.assertQueryResults(null, "Set{null}", "null.oclType().allInstances()");
			ocl.assertSemanticErrorQuery(null, "null.allInstances()", PivotMessagesInternal.UnresolvedOperationCall_ERROR_, "OclVoid", "allInstances", "");
			//
			ocl.assertQueryInvalid(null, "OclInvalid.allInstances()");
			ocl.assertQueryInvalid(null, "invalid.oclType().allInstances()");
			ocl.assertSemanticErrorQuery(null, "invalid.allInstances()", PivotMessagesInternal.UnresolvedOperationCall_ERROR_, "OclInvalid", "allInstances", "");
			//
			ocl.assertQueryResults(null, "Set{CollectionKind::Bag,CollectionKind::Collection,CollectionKind::_'OrderedSet',CollectionKind::_'Sequence',CollectionKind::_'Set'}", "CollectionKind.allInstances()");
			ocl.assertQueryResults(null, "Set{CollectionKind::Bag,CollectionKind::Collection,CollectionKind::_'OrderedSet',CollectionKind::_'Sequence',CollectionKind::_'Set'}", "CollectionKind::_'Set'.oclType().allInstances()");
			ocl.assertSemanticErrorQuery(null, "CollectionKind::_'Set'.allInstances()", PivotMessagesInternal.UnresolvedOperationCall_ERROR_, "CollectionKind", "allInstances", "");
			//
			ocl.assertQueryResults(null, "Set{}", "ocl::Package.allInstances()");
			ocl.assertQueryEquals(ocl.pkg1, 8, "Package.allInstances()->size()");
			//
			ocl.assertQueryResults(null, "Set{}", "Set(Integer).allInstances()");
			ocl.assertQueryResults(null, "Set{}", "Set{1}->oclType().allInstances()");
			ocl.assertQueryResults(null, "Bag{}", "Set{1}.oclType().allInstances()");
			ocl.assertSemanticErrorQuery(classType, "Set{1}->allInstances()", PivotMessagesInternal.UnresolvedOperationCall_ERROR_, "Set(Integer)", "allInstances", "");
			ocl.assertSemanticErrorQuery(classType, "Set{1}.allInstances()", PivotMessagesInternal.UnresolvedOperationCall_ERROR_, "Integer", "allInstances", "");
			//
			ocl.assertQueryResults(null, "Set{}", "Map(Integer,String).allInstances()");
			ocl.assertQueryResults(null, "Set{}", "Map{1 with '1'}->oclType().allInstances()");
			ocl.assertQueryResults(null, "Bag{}", "Map{1 with '1'}.oclType().allInstances()");
			ocl.assertSemanticErrorQuery(classType, "Map{1 with '1'}->allInstances()", PivotMessagesInternal.UnresolvedOperationCall_ERROR_, "Map(Integer,String)", "allInstances", "");
			ocl.assertSemanticErrorQuery(classType, "Map{1 with '1'}.allInstances()", PivotMessagesInternal.UnresolvedOperationCall_ERROR_, "Integer", "allInstances", "");
			//
			ocl.assertQueryResults(null, "Set{}", "Tuple(a:Integer).allInstances()");
			ocl.assertQueryResults(null, "Set{}", "Tuple(a:Integer).oclType().allInstances()");
			ocl.assertSemanticErrorQuery(classType, "Tuple{a:Integer=1}.allInstances()", PivotMessagesInternal.UnresolvedOperationCall_ERROR_, "Tuple(a:Integer[1])", "allInstances", "");
		} finally {
			ocl.dispose();
		}
	}

	/**
	 * Tests the conformsTo() operator.
	 */
	@Test public void test_conformsTo() {
		MyOCL ocl = createOCL(OCL.NO_PROJECTS, null);
		try {
			//		ocl.assertQueryTrue(null, "true.conformsTo(Boolean)");
			ocl.assertQueryTrue(null, "Boolean.conformsTo(Boolean)");
			ocl.assertQueryFalse(null, "String.conformsTo(Boolean)");
			ocl.assertQueryFalse(null, "Boolean.conformsTo(String)");
			ocl.assertQueryTrue(null, "Integer.conformsTo(Real)");
			ocl.assertQueryFalse(null, "UnlimitedNatural.conformsTo(Integer)");
			ocl.assertQueryFalse(null, "UnlimitedNatural.conformsTo(Real)");
			ocl.assertQueryFalse(null, "Real.conformsTo(Integer)");
			ocl.assertQueryFalse(null, "Real.conformsTo(UnlimitedNatural)");
			ocl.assertQueryFalse(null, "Integer.conformsTo(UnlimitedNatural)");
			//FIXME much more
		} finally {
			ocl.dispose();
		}
	}

	/**
	 * Tests the oclContainer() operator.
	 */
	@Test public void test_oclContainer() {
		MyOCL ocl = createOCL(OCL.CLASS_PATH, null);
		MetamodelManager metamodelManager = ocl.getMetamodelManager();
		try {
			org.eclipse.ocl.pivot.Class classType = metamodelManager.getStandardLibrary().getClassType();
			ocl.assertSemanticErrorQuery(classType, "invalid.oclContainer()", PivotMessagesInternal.UnresolvedOperation_ERROR_, "OclInvalid", "oclContainer");
			ocl.assertQueryInvalid(ocl.pkg2, "let s : OclElement = invalid in s.oclContainer()");
			ocl.assertSemanticErrorQuery(classType, "null.oclContainer()", PivotMessagesInternal.UnresolvedOperation_ERROR_, "OclVoid", "oclContainer");
			ocl.assertQueryInvalid(ocl.pkg2, "let s : OclElement = null in s.oclContainer()");
			ocl.assertQueryResults(ocl.root, "null", "oclContainer()");
			ocl.assertQueryEquals(ocl.pkg2, ocl.pkg1, "oclContainer()");
			ocl.assertSemanticErrorQuery(classType, "1.oclContainer()", PivotMessagesInternal.UnresolvedOperation_ERROR_, "Integer", "oclContainer");
			//
			ocl.assertSemanticErrorQuery(classType, "invalid.oclContainer", PivotMessagesInternal.UnresolvedProperty_ERROR_, "OclInvalid", "oclContainer");
			ocl.assertQueryInvalid(ocl.pkg2, "let s : OclElement = invalid in s.oclContainer");
			ocl.assertSemanticErrorQuery(classType, "null.oclContainer", PivotMessagesInternal.UnresolvedProperty_ERROR_, "OclVoid", "oclContainer");
			ocl.assertQueryInvalid(ocl.pkg2, "let s : OclElement = null in s.oclContainer");
			ocl.assertQueryResults(ocl.root, "null", "oclContainer");
			ocl.assertQueryEquals(ocl.pkg2, ocl.pkg1, "oclContainer");
			ocl.assertSemanticErrorQuery(classType, "1.oclContainer", PivotMessagesInternal.UnresolvedProperty_ERROR_, "Integer", "oclContainer");
		} finally {
			ocl.dispose();
		}
	}

	/**
	 * Tests the oclContents() operator.
	 */
	@Test public void test_oclContents() {
		MyOCL ocl = createOCL(OCL.NO_PROJECTS, null);
		EnvironmentFactoryInternalExtension environmentFactory = (EnvironmentFactoryInternalExtension) ocl.getEnvironmentFactory();
		try {
			IdResolver idResolver = environmentFactory.getIdResolver();
			org.eclipse.ocl.pivot.Class classType = environmentFactory.getStandardLibrary().getClassType();
			@NonNull Type packageType = ClassUtil.nonNullState(environmentFactory.getASClass("Package"));
			CollectionTypeId typeId = TypeId.SET.getSpecializedId(packageType.getTypeId());
			ocl.assertSemanticErrorQuery(classType, "invalid.oclContents()", PivotMessagesInternal.UnresolvedOperation_ERROR_, "OclInvalid", "oclContents");
			ocl.assertQueryInvalid(ocl.pkg2, "let s : OclElement = invalid in s.oclContents()");
			ocl.assertSemanticErrorQuery(classType, "null.oclContents()", PivotMessagesInternal.UnresolvedOperation_ERROR_, "OclVoid", "oclContents");
			ocl.assertQueryInvalid(ocl.pkg2, "let s : OclElement = null in s.oclContents()");
			ocl.assertQueryEquals(ocl.pkg1, idResolver.createSetOfEach(typeId, ocl.bob, ocl.pkg2, ocl.pkg3), "oclContents()");
			ocl.assertQueryEquals(ocl.pkg2, idResolver.createSetOfEach(typeId, ocl.jim), "oclContents()");
			ocl.assertQueryEquals(ocl.george, idResolver.createSetOfEach(typeId), "oclContents()");
			ocl.assertSemanticErrorQuery(classType, "1.oclContents()", PivotMessagesInternal.UnresolvedOperation_ERROR_, "Integer", "oclContents");
			//
			ocl.assertSemanticErrorQuery(classType, "invalid.oclContents", PivotMessagesInternal.UnresolvedProperty_ERROR_, "OclInvalid", "oclContents");
			ocl.assertQueryInvalid(ocl.pkg2, "let s : OclElement = invalid in s.oclContents");
			ocl.assertSemanticErrorQuery(classType, "null.oclContents", PivotMessagesInternal.UnresolvedProperty_ERROR_, "OclVoid", "oclContents");
			ocl.assertQueryInvalid(ocl.pkg2, "let s : OclElement = null in s.oclContents");
			ocl.assertQueryEquals(ocl.pkg1, idResolver.createSetOfEach(typeId, ocl.bob, ocl.pkg2, ocl.pkg3), "oclContents");
			ocl.assertQueryEquals(ocl.pkg2, idResolver.createSetOfEach(typeId, ocl.jim), "oclContents");
			ocl.assertQueryEquals(ocl.george, idResolver.createSetOfEach(typeId), "oclContents");
			ocl.assertSemanticErrorQuery(classType, "1.oclContents", PivotMessagesInternal.UnresolvedProperty_ERROR_, "Integer", "oclContents");
		} finally {
			ocl.dispose();
		}
	}
}
