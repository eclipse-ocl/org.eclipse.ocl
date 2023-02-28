/*******************************************************************************
 * Copyright (c) 2010, 2021 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   L.Goubet, E.D.Willink - Initial API and implementation
 *   E.D.Willink - Bug 400448
 *******************************************************************************/

package org.eclipse.ocl.examples.pivot.tests;

import java.util.Arrays;
import java.util.Collection;

import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.xtext.tests.TestFileSystem;
import org.eclipse.ocl.pivot.CompleteEnvironment;
import org.eclipse.ocl.pivot.Model;
import org.eclipse.ocl.pivot.StandardLibrary;
import org.eclipse.ocl.pivot.TupleType;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.ids.CollectionTypeId;
import org.eclipse.ocl.pivot.ids.IdManager;
import org.eclipse.ocl.pivot.ids.TuplePartId;
import org.eclipse.ocl.pivot.ids.TupleTypeId;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.manager.MetamodelManagerInternal;
import org.eclipse.ocl.pivot.internal.messages.PivotMessagesInternal;
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal.EnvironmentFactoryInternalExtension;
import org.eclipse.ocl.pivot.messages.PivotMessages;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.OCL;
import org.eclipse.ocl.pivot.utilities.PivotConstants;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.utilities.ValueUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/**
 * Tests for OclAny operations.
 */
@RunWith(value = Parameterized.class)
public class EvaluateOclAnyOperationsTest4 extends PivotTestSuite
{
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

		public MyOCL(@NonNull TestFileSystem testFileSystem, @NonNull String testPackageName, @NonNull String name, @Nullable ResourceSet externalResourceSet) {
			super(testFileSystem, testPackageName, name, useCodeGen ? getProjectMap() : OCL.NO_PROJECTS, externalResourceSet);
			MetamodelManagerInternal metamodelManager = getMetamodelManager();
			//			metamodelManager.addGlobalNamespace(PivotConstants.OCL_NAME, ClassUtil.nonNullState(metamodelManager.getASmetamodel()));

			metamodelManager.installRoot(ClassUtil.nonNullState(root));
			//	        helper.setContext(ClassUtil.nonNullState(metamodelManager.getPivotType("Package")));
		}
	}

	@Parameters
	public static Collection<Object[]> data() {
		Object[][] data = new Object[][]{{false}, {true}};
		return Arrays.asList(data);
	}

	public EvaluateOclAnyOperationsTest4(boolean useCodeGen) {
		super(useCodeGen);
	}

	@Override
	protected @NonNull MyOCL createOCL() {
		return new MyOCL(getTestFileSystem(), getTestPackageName(), getName(), null);
	}

	@Override
	protected @NonNull String getTestPackageName() {
		return "EvaluateOclAnyOperations";
	}

	@BeforeClass public static void resetCounter() throws Exception {
		PivotTestSuite.resetCounter();
	}

	@Override
	@Before public void setUp() throws Exception {
		super.setUp();
	}

	@Override
	@After public void tearDown() throws Exception {
		super.tearDown();
	}

	@Test public void testEqual() {
		MyOCL ocl = createOCL();
		ocl.assertQueryTrue(null, "Boolean = Boolean");
		ocl.assertQueryFalse(null, "Boolean = Integer");
		ocl.assertQueryTrue(null, "OclVoid = OclVoid");
		ocl.assertQueryTrue(null, "OclInvalid = OclInvalid");
		ocl.assertQueryFalse(null, "OclInvalid = OclVoid");
		ocl.assertQueryTrue(null, "Set(String) = Set(String)");
		ocl.assertQueryFalse(null, "Set(String) = Set(Integer)");
		ocl.assertQueryFalse(null, "Set(String) = Sequence(String)");
		//
		ocl.assertQueryTrue(null, "ocl::CollectionKind::_'Collection' = ocl::CollectionKind::_'Collection'");
		ocl.assertQueryFalse(null, "ocl::CollectionKind::_'Collection' = ocl::CollectionKind::_'Set'");
		//
		ocl.loadEPackage("ecore", EcorePackage.eINSTANCE);
		ocl.assertQueryFalse(null, "ecore::EDate{value='2000-01-25'} = ecore::EDate{value='2000-01-24'}");
		ocl.assertQueryTrue(null, "ecore::EDate{value='2000-01-24'} = ecore::EDate{value='2000-01-24'}");
		ocl.assertQueryFalse(null, "ecore::EDate{value='2000-01-23'} = ecore::EDate{value='2000-01-24'}");
		//
		// invalid
		//
		ocl.assertQueryInvalid(null, "invalid = 3");
		ocl.assertQueryInvalid(null, "3 = invalid");
		ocl.assertQueryInvalid(null, "invalid = 3.0");
		ocl.assertQueryInvalid(null, "3.0 = invalid");

		ocl.assertQueryInvalid(null, "invalid = 'test'");
		ocl.assertQueryInvalid(null, "'test' = invalid");
		ocl.assertQueryInvalid(null, "invalid = true");
		ocl.assertQueryInvalid(null, "false = invalid");
		ocl.assertQueryInvalid(null, "invalid = Sequence{}");
		ocl.assertQueryInvalid(null, "Sequence{} = invalid");

		ocl.assertQueryInvalid(null, "invalid = invalid");
		//
		// null
		//
		ocl.assertQueryFalse(null, "null = 3");
		ocl.assertQueryFalse(null, "3 = null");
		ocl.assertQueryFalse(null, "null = 3.0");
		ocl.assertQueryFalse(null, "3.0 = null");

		ocl.assertQueryFalse(null, "null = 'test'");
		ocl.assertQueryFalse(null, "'test' = null");
		ocl.assertQueryFalse(null, "null = true");
		ocl.assertQueryFalse(null, "false = null");
		ocl.assertQueryFalse(null, "null = Sequence{}");
		ocl.assertQueryFalse(null, "Sequence{} = null");

		ocl.assertQueryTrue(null, "null = null");
		ocl.dispose();
	}

	@Test public void testGreaterThan() {
		MyOCL ocl = createOCL();
		StandardLibrary standardLibrary = ocl.getStandardLibrary();
		ocl.loadEPackage("ecore", EcorePackage.eINSTANCE);
		ocl.assertQueryTrue(null, "ecore::EDate{value='2000-01-25'} > ecore::EDate{value='2000-01-24'}");
		ocl.assertQueryFalse(null, "ecore::EDate{value='2000-01-24'} > ecore::EDate{value='2000-01-24'}");
		ocl.assertQueryFalse(null, "ecore::EDate{value='2000-01-23'} > ecore::EDate{value='2000-01-24'}");
		//
		// invalid
		//
		// FIXME Analyzer-extraOperation OclAny::< should not be defined
		ocl.assertSemanticErrorQuery(null, "invalid > 0", PivotMessagesInternal.UnresolvedOperationCall_ERROR_, standardLibrary.getOclInvalidType(), PivotConstants.GREATER_THAN_OPERATOR, standardLibrary.getIntegerType());
		//		ocl.assertQueryInvalid(null, "invalid > 0");
		//		assertSemanticErrorQuery2(null, "0 > invalid", OCLMessages.OperationCallNotFound_ERROR_, PivotConstants.GREATER_THAN_OPERATOR);
		ocl.assertQueryInvalid(null, "0 > invalid");
		ocl.assertSemanticErrorQuery(null, "invalid > invalid", PivotMessagesInternal.UnresolvedOperationCall_ERROR_, standardLibrary.getOclInvalidType(), PivotConstants.GREATER_THAN_OPERATOR, standardLibrary.getOclInvalidType());
		//
		// null
		//
		// FIXME Analyzer-extraOperation OclAny::< should not be defined
		ocl.assertSemanticErrorQuery(null, "null > 0", PivotMessagesInternal.UnresolvedOperationCall_ERROR_, standardLibrary.getOclVoidType(), PivotConstants.GREATER_THAN_OPERATOR, standardLibrary.getIntegerType());
		//		ocl.assertQueryInvalid(null, "null > 0");
		//		assertSemanticErrorQuery2(null, "0 > null", OCLMessages.OperationCallNotFound_ERROR_, PivotConstants.GREATER_THAN_OPERATOR);
		ocl.assertQueryInvalid(null, "0 > null");
		ocl.assertSemanticErrorQuery(null, "null > null", PivotMessagesInternal.UnresolvedOperationCall_ERROR_, standardLibrary.getOclVoidType(), PivotConstants.GREATER_THAN_OPERATOR, standardLibrary.getOclVoidType());
		ocl.dispose();
	}

	@Test public void testGreaterThanOrEqual() {
		MyOCL ocl = createOCL();
		StandardLibrary standardLibrary = ocl.getStandardLibrary();
		ocl.loadEPackage("ecore", EcorePackage.eINSTANCE);
		ocl.assertQueryTrue(null, "ecore::EDate{value='2000-01-25'} >= ecore::EDate{value='2000-01-24'}");
		ocl.assertQueryTrue(null, "ecore::EDate{value='2000-01-24'} >= ecore::EDate{value='2000-01-24'}");
		ocl.assertQueryFalse(null, "ecore::EDate{value='2000-01-23'} >= ecore::EDate{value='2000-01-24'}");
		//
		// invalid
		//
		// FIXME Analyzer-extraOperation OclAny::< should not be defined
		ocl.assertSemanticErrorQuery(null, "invalid >= 0", PivotMessagesInternal.UnresolvedOperationCall_ERROR_, standardLibrary.getOclInvalidType(), PivotConstants.GREATER_THAN_OR_EQUAL_OPERATOR, standardLibrary.getIntegerType());
		//		ocl.assertQueryInvalid(null, "invalid >= 0");
		//		assertSemanticErrorQuery2(null, "0 >= invalid", OCLMessages.OperationCallNotFound_ERROR_, PivotConstants.GREATER_THAN_OR_EQUAL_OPERATOR);
		ocl.assertQueryInvalid(null, "0 >= invalid");
		ocl.assertSemanticErrorQuery(null, "invalid >= invalid", PivotMessagesInternal.UnresolvedOperationCall_ERROR_, standardLibrary.getOclInvalidType(), PivotConstants.GREATER_THAN_OR_EQUAL_OPERATOR, standardLibrary.getOclInvalidType());
		//
		// null
		//
		// FIXME Analyzer-extraOperation OclAny::< should not be defined
		ocl.assertSemanticErrorQuery(null, "null >= 0", PivotMessagesInternal.UnresolvedOperationCall_ERROR_, standardLibrary.getOclVoidType(), PivotConstants.GREATER_THAN_OR_EQUAL_OPERATOR, standardLibrary.getIntegerType());
		//		ocl.assertQueryInvalid(null, "null >= 0");
		//		assertSemanticErrorQuery2(null, "0 >= null", OCLMessages.OperationCallNotFound_ERROR_, PivotConstants.GREATER_THAN_OR_EQUAL_OPERATOR);
		ocl.assertQueryInvalid(null, "0 >= null");
		ocl.assertSemanticErrorQuery(null, "null >= null", PivotMessagesInternal.UnresolvedOperationCall_ERROR_, standardLibrary.getOclVoidType(), PivotConstants.GREATER_THAN_OR_EQUAL_OPERATOR, standardLibrary.getOclVoidType());
		ocl.dispose();
	}

	@Test public void testLessThan() {
		MyOCL ocl = createOCL();
		StandardLibrary standardLibrary = ocl.getStandardLibrary();
		ocl.loadEPackage("ecore", EcorePackage.eINSTANCE);
		ocl.assertQueryFalse(null, "ecore::EDate{value='2000-01-25'} < ecore::EDate{value='2000-01-24'}");
		ocl.assertQueryFalse(null, "ecore::EDate{value='2000-01-24'} < ecore::EDate{value='2000-01-24'}");
		ocl.assertQueryTrue(null, "ecore::EDate{value='2000-01-23'} < ecore::EDate{value='2000-01-24'}");
		//
		// invalid
		//
		// FIXME Analyzer-extraOperation OclAny::< should not be defined
		ocl.assertSemanticErrorQuery(null, "invalid < 0", PivotMessagesInternal.UnresolvedOperationCall_ERROR_, standardLibrary.getOclInvalidType(), PivotConstants.LESS_THAN_OPERATOR, standardLibrary.getIntegerType());
		//		ocl.assertQueryInvalid(null, "invalid < 0");
		//		assertSemanticErrorQuery2(null, "0 < invalid", OCLMessages.OperationCallNotFound_ERROR_, PivotConstants.LESS_THAN_OPERATOR);
		ocl.assertQueryInvalid(null, "0 < invalid");
		ocl.assertSemanticErrorQuery(null, "invalid < invalid", PivotMessagesInternal.UnresolvedOperationCall_ERROR_, standardLibrary.getOclInvalidType(), PivotConstants.LESS_THAN_OPERATOR, standardLibrary.getOclInvalidType());
		//
		// null
		//
		// FIXME Analyzer-extraOperation OclAny::< should not be defined
		ocl.assertSemanticErrorQuery(null, "null < 0", PivotMessagesInternal.UnresolvedOperationCall_ERROR_, standardLibrary.getOclVoidType(), PivotConstants.LESS_THAN_OPERATOR, standardLibrary.getIntegerType());
		//		ocl.assertQueryInvalid(null, "null < 0");
		//		assertSemanticErrorQuery2(null, "0 < null", OCLMessages.OperationCallNotFound_ERROR_, PivotConstants.LESS_THAN_OPERATOR);
		ocl.assertQueryInvalid(null, "0 < null");
		ocl.assertSemanticErrorQuery(null, "null < null", PivotMessagesInternal.UnresolvedOperationCall_ERROR_, standardLibrary.getOclVoidType(), PivotConstants.LESS_THAN_OPERATOR, standardLibrary.getOclVoidType());
		ocl.dispose();
	}

	@Test public void testLessThanOrEqual() {
		MyOCL ocl = createOCL();
		StandardLibrary standardLibrary = ocl.getStandardLibrary();
		ocl.loadEPackage("ecore", EcorePackage.eINSTANCE);
		ocl.assertQueryFalse(null, "ecore::EDate{'2000-01-25'} <= ecore::EDate{'2000-01-24'}");
		ocl.assertQueryTrue(null, "ecore::EDate{'2000-01-24'} <= ecore::EDate{value='2000-01-24'}");
		ocl.assertQueryTrue(null, "ecore::EDate{value='2000-01-23'} <= ecore::EDate{value='2000-01-24'}");
		//
		// invalid
		//
		// FIXME Analyzer-extraOperation OclAny::< should not be defined
		ocl.assertSemanticErrorQuery(null, "invalid <= 0", PivotMessagesInternal.UnresolvedOperationCall_ERROR_, standardLibrary.getOclInvalidType(), PivotConstants.LESS_THAN_OR_EQUAL_OPERATOR, standardLibrary.getIntegerType());
		//		ocl.assertQueryInvalid(null, "invalid <= 0");
		//		ocl.assertSemanticErrorQuery2(null, "0 <= invalid", OCLMessages.OperationCallNotFound_ERROR_, PivotConstants.LESS_THAN_OR_EQUAL_OPERATOR);
		ocl.assertQueryInvalid(null, "0 <= invalid");
		ocl.assertSemanticErrorQuery(null, "invalid <= invalid", PivotMessagesInternal.UnresolvedOperationCall_ERROR_, standardLibrary.getOclInvalidType(), PivotConstants.LESS_THAN_OR_EQUAL_OPERATOR, standardLibrary.getOclInvalidType());
		//
		// null
		//
		// FIXME Analyzer-extraOperation OclAny::< should not be defined
		ocl.assertSemanticErrorQuery(null, "null <= 0", PivotMessagesInternal.UnresolvedOperationCall_ERROR_, standardLibrary.getOclVoidType(), PivotConstants.LESS_THAN_OR_EQUAL_OPERATOR, standardLibrary.getIntegerType());
		//		ocl.assertQueryInvalid(null, "null <= 0");
		//		ocl.assertSemanticErrorQuery2(null, "0 <= null", OCLMessages.OperationCallNotFound_ERROR_, PivotConstants.LESS_THAN_OR_EQUAL_OPERATOR);
		ocl.assertQueryInvalid(null, "0 <= null");
		ocl.assertSemanticErrorQuery(null, "null <= null", PivotMessagesInternal.UnresolvedOperationCall_ERROR_, standardLibrary.getOclVoidType(), PivotConstants.LESS_THAN_OR_EQUAL_OPERATOR, standardLibrary.getOclVoidType());
		ocl.dispose();
	}

	@Test public void testNotEqual() {
//		BaseLinkingService.DEBUG_RETRY.setState(true);
		MyOCL ocl = createOCL();
		ocl.assertQueryFalse(null, "Boolean <> Boolean");
		ocl.assertQueryTrue(null, "Boolean <> Integer");
		ocl.assertQueryFalse(null, "OclVoid <> OclVoid");
		ocl.assertQueryFalse(null, "OclInvalid <> OclInvalid");
		ocl.assertQueryTrue(null, "OclInvalid <> OclVoid");
		ocl.assertQueryFalse(null, "Set(String) <> Set(String)");
		ocl.assertQueryTrue(null, "Set(String) <> Set(Integer)");
		ocl.assertQueryTrue(null, "Set(String) <> Sequence(String)");
		//
		ocl.assertQueryFalse(null, "ocl::CollectionKind::_'Collection' <> ocl::CollectionKind::_'Collection'");
		ocl.assertQueryTrue(null, "ocl::CollectionKind::_'Collection' <> ocl::CollectionKind::_'Set'");
		//
		ocl.loadEPackage("ecore", EcorePackage.eINSTANCE);
		ocl.assertQueryTrue(null, "ecore::EDate{value='2000-01-25'} <> ecore::EDate{value='2000-01-24'}");
		ocl.assertQueryFalse(null, "ecore::EDate{value='2000-01-24'} <> ecore::EDate{'2000-01-24'}");
		ocl.assertQueryTrue(null, "ecore::EDate{'2000-01-23'} <> ecore::EDate{'2000-01-24'}");
		//
		// invalid
		//
		ocl.assertQueryInvalid(null, "invalid <> 3");
		ocl.assertQueryInvalid(null, "3 <> invalid");
		ocl.assertQueryInvalid(null, "invalid <> 3.0");
		ocl.assertQueryInvalid(null, "3.0 <> invalid");

		ocl.assertQueryInvalid(null, "invalid <> 'test'");
		ocl.assertQueryInvalid(null, "'test' <> invalid");
		ocl.assertQueryInvalid(null, "invalid <> true");
		ocl.assertQueryInvalid(null, "false <> invalid");
		ocl.assertQueryInvalid(null, "invalid <> Sequence{}");
		ocl.assertQueryInvalid(null, "Sequence{} <> invalid");

		ocl.assertQueryInvalid(null, "invalid <> invalid");
		//
		// null
		//
		ocl.assertQueryTrue(null, "null <> 3");
		ocl.assertQueryTrue(null, "3 <> null");
		ocl.assertQueryTrue(null, "null <> 3.0");
		ocl.assertQueryTrue(null, "3.0 <> null");

		ocl.assertQueryTrue(null, "null <> 'test'");
		ocl.assertQueryTrue(null, "'test' <> null");
		ocl.assertQueryTrue(null, "null <> true");
		ocl.assertQueryTrue(null, "false <> null");
		ocl.assertQueryTrue(null, "null <> Sequence{}");
		ocl.assertQueryTrue(null, "Sequence{} <> null");

		ocl.assertQueryFalse(null, "null <> null");
		ocl.dispose();
	}

	/**
	 * Tests the oclAsModelType() operator.
	 */
	@Test public void test_oclAsModelType() {
		MyOCL ocl = createOCL();
		ocl.assertSemanticErrorQuery(null, "invalid.oclAsModelType(OclAny)", PivotMessagesInternal.UnresolvedOperationCall_ERROR_, "OclInvalid", "oclAsModelType", "OclAny");
		ocl.assertQueryInvalid(ocl.pkg1, "self.oclAsModelType(OclAny)");
		// See test_umlValidation_Bug458394 for some real usage
		ocl.dispose();
	}

	/**
	 * Tests the explicit oclAsSet() operator.
	 */
	@Test public void test_oclAsSet_explicit() {
		MyOCL ocl = createOCL();
		ocl.assertQueryResults(null, "Set{true}", "true.oclAsSet()");
		ocl.assertQueryResults(null, "Set{}", "null.oclAsSet()");
		ocl.assertQueryInvalid(null, "invalid.oclAsSet()");
		ocl.assertQueryResults(null, "Set{Set{1..4}}", "Set{1..4}->oclAsSet()");
		ocl.dispose();
	}

	/**
	 * Tests the implicit oclAsSet() operator.
	 */
	@Test public void test_oclAsSet_implicit() {
		MyOCL ocl = createOCL();
		StandardLibrary standardLibrary = ocl.getStandardLibrary();
		ocl.assertQueryResults(null, "Set{true}", "true->select(true)");
		ocl.assertQueryResults(null, "Set{true}", "Set{true}->select(true)");
		ocl.assertQueryResults(null, "Set{}", "null->select(true)");
		ocl.assertQueryResults(null, "Set{}", "Set{}->select(true)");
		ocl.assertQueryResults(null, "Set{null}", "Set{null}->select(true)");
		ocl.assertQueryInvalid(null, "invalid->select(true)");
		//
		ocl.assertQueryResults(null, "false", "true.oclIsUndefined()");
		ocl.assertQueryResults(null, "false", "true->oclIsUndefined()");	// Set{true}
		ocl.assertQueryResults(null, "true", "null.oclIsUndefined()");
		ocl.assertQueryResults(null, "false", "null->oclIsUndefined()");	// Set{}
		ocl.assertQueryResults(null, "true", "invalid.oclIsUndefined()");
		ocl.assertQueryResults(null, "true", "invalid->oclIsUndefined()");	// invalid
		//
		ocl.assertQueryEquals(null, 4, "'1234'.size()");
		ocl.assertQueryEquals(null, 1, "'1234'->size()");
		//
		org.eclipse.ocl.pivot.Class booleanType = standardLibrary.getBooleanType();
		ocl.assertQueryEquals(null, booleanType, "true.oclType()");
		Type collectionType = ocl.getCompleteEnvironment().getSetType(booleanType, false, ValueUtil.ONE_VALUE, ValueUtil.UNLIMITED_ONE_VALUE);
		ocl.assertQueryEquals(null, collectionType, "true->oclType()");		// Set{true}
		ocl.dispose();
	}

	/**
	 * Tests the oclAsType() operator.
	 */
	@Test public void test_oclAsType() {
		MyOCL ocl = createOCL();
		ocl.assertQueryInvalid(null, "invalid.oclAsType(String)");
		ocl.assertQueryInvalid(null, "invalid.oclAsType(Integer)");
		ocl.assertQueryInvalid(null, "invalid.oclAsType(Class)");
		ocl.assertQueryInvalid(null, "invalid.oclAsType(OclVoid)");
		ocl.assertQueryInvalid(null, "invalid.oclAsType(OclInvalid)");
		ocl.assertQueryInvalid(null, "invalid.oclAsType(Set(String))");
		ocl.assertQueryInvalid(null, "invalid.oclAsType(Tuple(a:String))");
		ocl.assertQueryInvalid(null, "invalid.oclAsType(ocl::Package)");
		//
		ocl.assertQueryNull(null, "let s : String = null.oclAsType(String) in s");
		ocl.assertQueryNull(null, "null.oclAsType(Integer)");
		ocl.assertQueryNull(null, "null.oclAsType(Class)");
		ocl.assertQueryNull(null, "null.oclAsType(OclVoid)");
		ocl.assertValidationErrorQuery(null, "null.oclAsType(OclInvalid)",
			PivotMessages.ValidationConstraintIsNotSatisfied_ERROR_, "CallExp::TypeIsNotInvalid", "null.oclAsType(OclInvalid)");
		ocl.assertQueryNull(null, "null.oclAsType(Set(String))");
		ocl.assertQueryNull(null, "null.oclAsType(Tuple(a:String))");
		ocl.assertQueryNull(null, "null.oclAsType(ocl::Package)");
		//
		ocl.assertQueryInvalid(null, "true.oclAsType(Integer)");
		ocl.assertQueryInvalid(null, "true.oclAsType(String)");
		ocl.assertQueryTrue(null, "true.oclAsType(Boolean)");
		ocl.assertQueryTrue(null, "true.oclAsType(OclAny)");
		ocl.assertQueryInvalid(null, "true.oclAsType(OclVoid)");
		ocl.assertQueryInvalid(null, "true.oclAsType(OclInvalid)");
		//
		ocl.assertQueryEquals(null, 3, "3.oclAsType(Integer)");
		ocl.assertQueryEquals(null, 3.0, "3.oclAsType(Real)");
		ocl.assertQueryInvalid(null, "3.0.oclAsType(Integer)");		// Cannot downcast
		ocl.assertQueryEquals(null, 3.0, "3.0.oclAsType(Real)");
		ocl.assertQueryInvalid(null, "3.oclAsType(String)");
		ocl.assertQueryEquals(null, 3, "3.oclAsType(OclAny)");
		ocl.assertQueryInvalid(null, "3.oclAsType(OclVoid)");
		ocl.assertQueryInvalid(null, "3.oclAsType(OclInvalid)");
		//
		ocl.assertQueryInvalid(null, "3.14.oclAsType(Integer)");
		ocl.assertQueryEquals(null, 3.14, "3.14.oclAsType(Real)");
		ocl.assertQueryInvalid(null, "3.14.oclAsType(String)");
		ocl.assertQueryEquals(null, 3.14, "3.14.oclAsType(OclAny)");
		ocl.assertQueryInvalid(null, "3.14.oclAsType(OclVoid)");
		ocl.assertQueryInvalid(null, "3.14.oclAsType(OclInvalid)");
		//
		ocl.assertQueryInvalid(null, "*.oclAsType(Integer)");
		ocl.assertQueryInvalid(null, "*.oclAsType(Real)");
		ocl.assertQueryUnlimited(null, "*.oclAsType(UnlimitedNatural)");
		ocl.assertQueryInvalid(null, "*.oclAsType(String)");
		ocl.assertQueryUnlimited(null, "*.oclAsType(OclAny)");
		ocl.assertQueryInvalid(null, "*.oclAsType(OclVoid)");
		ocl.assertQueryInvalid(null, "*.oclAsType(OclInvalid)");
		//
		ocl.assertQueryInvalid(null, "Set{1,2}->oclAsType(Set(UnlimitedNatural))");
		ocl.assertQueryResults(null, "Set{1,2}", "Set{1,2}->oclAsType(Set(Integer))");
		ocl.assertQueryResults(null, "Set{1,2}", "Set{1,2}->oclAsType(Collection(Real))");
		ocl.assertQueryInvalid(null, "Set{1,2}->oclAsType(Collection(UnlimitedNatural))");
		ocl.assertQueryInvalid(null, "Set{1.0,2}->oclAsType(Collection(UnlimitedNatural))");
		ocl.assertQueryInvalid(null, "Set{1,2}->oclAsType(Sequence(UnlimitedNatural))");
		if (!useCodeGen) {				// FIXME CG UOE on getDepth()
			ocl.assertQueryInvalid(null, "Set{1,2}.oclAsType(Set(UnlimitedNatural))");		// Cannot cast non-collection (elements) to collection
		}
		ocl.assertQueryResults(null, "Bag{1,2}", "Set{1,2}.oclAsType(Integer)");
		if (!useCodeGen) {				// FIXME CG UOE on getDepth()
			ocl.assertQueryInvalid(null, "Set{1,2}.oclAsType(Set(Integer))");				// Cannot cast non-collection (elements) to collection
		}
		ocl.assertQueryResults(null, "Bag{1,2}", "Set{1,2}.oclAsType(Integer)");
		ocl.assertQueryResults(null, "Set{Set{1,2},Set{3,4}}", "Set{Set{1,2},Set{3,4}}->oclAsType(Set(Set(Integer)))");
		ocl.assertQueryResults(null, "Set{Set{1,2},Set{3,4}}", "Set{Set{1,2},Set{3,4}}->oclAsType(Set(Collection(Integer)))");
		ocl.assertQueryResults(null, "Set{Set{1,2},Set{3,4}}", "Set{Set{1,2},Set{3,4}}->oclAsType(Collection(Set(Integer)))");
		ocl.assertQueryResults(null, "Set{Set{1,2},Set{3,4}}", "Set{Set{1,2},Set{3,4}}->oclAsType(Set(Set(Real)))");
		ocl.assertQueryInvalid(null, "Set{Set{1,2},Set{3,4}}->oclAsType(Set(Sequence(Integer)))");
		ocl.assertQueryInvalid(null, "Set{Set{1,2},Set{3,4}}->oclAsType(Sequence(Set(Integer)))");
		//
		ocl.assertSemanticErrorQuery(null, "3.oclAsType(OclAny).abs()", PivotMessagesInternal.UnresolvedOperation_ERROR_, "OclAny", "abs");
		ocl.assertSemanticErrorQuery(null, "let v : OclAny = 3 in v.abs()", PivotMessagesInternal.UnresolvedOperation_ERROR_, "OclAny", "abs");
		ocl.assertQueryEquals(null, 3, "let v : OclAny = 3 in v.oclAsType(Integer).abs()");
		ocl.assertQueryInvalid(null, "Integer.oclAsType(Real)");
		ocl.dispose();
	}

	/**
	 * Tests the oclIsInvalid() operator.
	 */
	@Test public void test_oclIsInvalid() {
		MyOCL ocl = createOCL();
		ocl.assertQueryTrue(null, "invalid.oclIsInvalid()");
		ocl.assertQueryFalse(null, "null.oclIsInvalid()");
		ocl.assertQueryFalse(null, "true.oclIsInvalid()");
		ocl.assertQueryFalse(null, "false.oclIsInvalid()");
		ocl.assertQueryFalse(null, "3.14.oclIsInvalid()");
		ocl.assertQueryFalse(null, "1.oclIsInvalid()");
		ocl.assertQueryFalse(null, "*.oclIsInvalid()");
		ocl.assertQueryFalse(null, "'invalid'.oclIsInvalid()");
		ocl.assertQueryFalse(ocl.pkg1, "self.oclIsInvalid()");
		ocl.assertQueryTrue(null, "('123a'.toInteger()).oclIsUndefined()");	// Bug 342561 for old evaluator
		ocl.assertQueryTrue(null, "let a:Integer='123a'.toInteger() in a.oclIsUndefined()");	// Bug 342561 for old evaluator
		ocl.dispose();
	}

	/**
	 * Tests the oclIsKindOf() operator.
	 */
	@Test public void test_oclIsKindOf() {
		MyOCL ocl = createOCL();
		ocl.assertQueryInvalid(null, "invalid.oclIsKindOf(OclInvalid)");
		ocl.assertQueryInvalid(null, "invalid.oclIsKindOf(OclVoid)");
		ocl.assertQueryInvalid(null, "invalid.oclIsKindOf(OclAny)");
		ocl.assertQueryInvalid(null, "invalid.oclIsKindOf(String)");
		ocl.assertQueryInvalid(null, "invalid.oclIsKindOf(Integer)");
		ocl.assertQueryInvalid(null, "invalid.oclIsKindOf(Class)");
		ocl.assertQueryInvalid(null, "invalid.oclIsKindOf(Bag(Boolean))");
		ocl.assertQueryInvalid(null, "invalid.oclIsKindOf(Tuple(a:Integer))");
		ocl.assertQueryInvalid(null, "invalid.oclIsKindOf(ocl::Package)");
		//
		ocl.assertQueryFalse(null, "null.oclIsKindOf(OclInvalid)");
		ocl.assertQueryTrue(null, "null.oclIsKindOf(OclVoid)");
		ocl.assertQueryTrue(null, "null.oclIsKindOf(OclAny)");
		ocl.assertQueryTrue(null, "null.oclIsKindOf(String)");
		ocl.assertQueryTrue(null, "null.oclIsKindOf(Integer)");
		ocl.assertQueryTrue(null, "null.oclIsKindOf(Class)");
		ocl.assertQueryTrue(null, "null.oclIsKindOf(Bag(Boolean))");
		ocl.assertQueryTrue(null, "null.oclIsKindOf(Tuple(a:Integer))");
		ocl.assertQueryTrue(null, "null.oclIsKindOf(ocl::Package)");
		//
		ocl.assertQueryFalse(null, "true.oclIsKindOf(OclInvalid)");
		ocl.assertQueryFalse(null, "true.oclIsKindOf(OclVoid)");
		ocl.assertQueryTrue(null, "true.oclIsKindOf(Boolean)");
		ocl.assertQueryFalse(null, "true.oclIsKindOf(Integer)");
		ocl.assertQueryFalse(null, "true.oclIsKindOf(String)");
		ocl.assertQueryTrue(null, "true.oclIsKindOf(OclAny)");
		ocl.assertQueryFalse(null, "true.oclIsKindOf(ocl::Package)");
		//
		ocl.assertQueryFalse(null, "3.14.oclIsKindOf(OclInvalid)");
		ocl.assertQueryFalse(null, "3.14.oclIsKindOf(OclVoid)");
		ocl.assertQueryFalse(null, "3.14.oclIsKindOf(Boolean)");
		ocl.assertQueryTrue(null, "3.14.oclIsKindOf(Real)");
		ocl.assertQueryFalse(null, "3.14.oclIsKindOf(Integer)");
		ocl.assertQueryFalse(null, "3.14.oclIsKindOf(String)");
		ocl.assertQueryTrue(null, "3.14.oclIsKindOf(OclAny)");
		ocl.assertQueryFalse(null, "3.14.oclIsKindOf(ocl::Package)");
		//
		ocl.assertQueryFalse(null, "1.oclIsKindOf(OclInvalid)");
		ocl.assertQueryFalse(null, "1.oclIsKindOf(OclVoid)");
		ocl.assertQueryFalse(null, "1.oclIsKindOf(Boolean)");
		ocl.assertQueryTrue(null, "1.oclIsKindOf(Real)");
		ocl.assertQueryTrue(null, "1.oclIsKindOf(Integer)");
		ocl.assertQueryTrue(null, "(-1).oclIsKindOf(Integer)");
		ocl.assertQueryTrue(null, "1.oclIsKindOf(Integer)");
		ocl.assertQueryFalse(null, "1.oclIsKindOf(String)");
		ocl.assertQueryTrue(null, "1.oclIsKindOf(OclAny)");
		ocl.assertQueryFalse(null, "1.oclIsKindOf(ocl::Package)");
		//
		ocl.assertQueryFalse(null, "*.oclIsKindOf(OclInvalid)");
		ocl.assertQueryFalse(null, "*.oclIsKindOf(OclVoid)");
		ocl.assertQueryFalse(null, "*.oclIsKindOf(Boolean)");
		ocl.assertQueryFalse(null, "*.oclIsKindOf(Real)");
		ocl.assertQueryFalse(null, "*.oclIsKindOf(Integer)");
		ocl.assertQueryTrue(null, "*.oclIsKindOf(UnlimitedNatural)");
		ocl.assertQueryFalse(null, "*.oclIsKindOf(String)");
		ocl.assertQueryTrue(null, "*.oclIsKindOf(OclAny)");
		ocl.assertQueryFalse(null, "*.oclIsKindOf(ocl::Package)");
		//
		ocl.assertQueryFalse(null, "'invalid'.oclIsKindOf(OclInvalid)");
		ocl.assertQueryFalse(null, "'null'.oclIsKindOf(OclVoid)");
		ocl.assertQueryFalse(null, "'true'.oclIsKindOf(Boolean)");
		ocl.assertQueryFalse(null, "'3.14'.oclIsKindOf(Real)");
		ocl.assertQueryFalse(null, "'1'.oclIsKindOf(Integer)");
		ocl.assertQueryFalse(null, "'*'.oclIsKindOf(UnlimitedNatural)");
		ocl.assertQueryTrue(null, "'string'.oclIsKindOf(String)");
		ocl.assertQueryTrue(null, "'any'.oclIsKindOf(OclAny)");
		ocl.assertQueryFalse(ocl.pkg1, "'self'.oclIsKindOf(ocl::Package)");
		//
		ocl.assertQueryFalse(ocl.pkg1, "self.oclIsKindOf(OclInvalid)");
		ocl.assertQueryFalse(ocl.pkg1, "self.oclIsKindOf(OclVoid)");
		ocl.assertQueryFalse(ocl.pkg1, "self.oclIsKindOf(Boolean)");
		ocl.assertQueryFalse(ocl.pkg1, "self.oclIsKindOf(Real)");
		ocl.assertQueryFalse(ocl.pkg1, "self.oclIsKindOf(Integer)");
		ocl.assertQueryFalse(ocl.pkg1, "self.oclIsKindOf(UnlimitedNatural)");
		ocl.assertQueryFalse(ocl.pkg1, "self.oclIsKindOf(String)");
		ocl.assertQueryTrue(ocl.pkg1, "self.oclIsKindOf(OclAny)");
		ocl.assertQueryTrue(ocl.pkg1, "self.oclIsKindOf(ocl::Package)");
		ocl.dispose();
	}

	/**
	 * Tests the oclIsKindOf() operator.
	 */
	@Test public void test_oclIsKindOf_580139() {
		MyOCL ocl = createOCL();
		//
		ocl.assertQueryFalse(null, "let t : ocl::OclType = Integer in 4.0.oclIsKindOf(t)");
		ocl.assertQueryTrue(null, "let t : ocl::OclType = Integer in 4.oclIsKindOf(t)");
		ocl.assertQueryTrue(null, "let t = Real in 4.oclIsKindOf(t)");
		ocl.assertQueryFalse(null, "let t = if self <> null then Real else Integer endif in true.oclIsKindOf(t)");
		ocl.assertQueryFalse(null, "let t = Real in true.oclIsKindOf(t)");
		ocl.assertQueryTrue(null, "let t = UnlimitedNatural in *.oclIsKindOf(t)");
		ocl.dispose();
	}

	/**
	 * Tests the oclIsModelKindOf() operator.
	 */
	@Test public void test_oclIsModelKindOf() {
		MyOCL ocl = createOCL();
		ocl.assertSemanticErrorQuery(null, "invalid.oclIsModelKindOf(OclAny)", PivotMessagesInternal.UnresolvedOperationCall_ERROR_, "OclInvalid", "oclIsModelKindOf", "OclAny");
		ocl.assertQueryInvalid(ocl.pkg1, "self.oclIsModelKindOf(OclAny)");
		// See test_umlValidation_Bug458394 for some real usage
		ocl.dispose();
	}

	/**
	 * Tests the oclIsTypeOf() operator.
	 */
	@Test public void test_oclIsTypeOf() {
		MyOCL ocl = createOCL();
		ocl.assertQueryInvalid(null, "invalid.oclIsTypeOf(OclInvalid)");
		ocl.assertQueryInvalid(null, "invalid.oclIsTypeOf(OclVoid)");
		ocl.assertQueryInvalid(null, "invalid.oclIsTypeOf(OclAny)");
		ocl.assertQueryInvalid(null, "invalid.oclIsTypeOf(Integer)");
		ocl.assertQueryInvalid(null, "invalid.oclIsTypeOf(String)");
		ocl.assertQueryInvalid(null, "invalid.oclIsTypeOf(Class)");
		ocl.assertQueryInvalid(null, "invalid.oclIsTypeOf(Set(String))");
		ocl.assertQueryInvalid(null, "invalid.oclIsTypeOf(ocl::Package)");
		//
		ocl.assertQueryFalse(null, "null.oclIsTypeOf(OclInvalid)");
		ocl.assertQueryTrue(null, "null.oclIsTypeOf(OclVoid)");
		ocl.assertQueryFalse(null, "null.oclIsTypeOf(OclAny)");
		ocl.assertQueryFalse(null, "null.oclIsTypeOf(Integer)");
		ocl.assertQueryFalse(null, "null.oclIsTypeOf(String)");
		ocl.assertQueryFalse(null, "null.oclIsTypeOf(Class)");
		ocl.assertQueryFalse(null, "null.oclIsTypeOf(Set(String))");
		ocl.assertQueryFalse(null, "null.oclIsTypeOf(ocl::Package)");
		//
		ocl.assertQueryFalse(null, "true.oclIsTypeOf(OclInvalid)");
		ocl.assertQueryFalse(null, "true.oclIsTypeOf(OclVoid)");
		ocl.assertQueryTrue(null, "true.oclIsTypeOf(Boolean)");
		ocl.assertQueryFalse(null, "true.oclIsTypeOf(String)");
		ocl.assertQueryFalse(null, "true.oclIsTypeOf(OclAny)");
		ocl.assertQueryFalse(null, "true.oclIsTypeOf(ocl::Package)");
		//
		ocl.assertQueryFalse(null, "3.14.oclIsTypeOf(OclInvalid)");
		ocl.assertQueryFalse(null, "3.14.oclIsTypeOf(OclVoid)");
		ocl.assertQueryFalse(null, "3.14.oclIsTypeOf(Boolean)");
		ocl.assertQueryTrue(null, "3.14.oclIsTypeOf(Real)");
		ocl.assertQueryFalse(null, "3.14.oclIsTypeOf(Integer)");
		ocl.assertQueryFalse(null, "3.14.oclIsTypeOf(String)");
		ocl.assertQueryFalse(null, "3.14.oclIsTypeOf(OclAny)");
		ocl.assertQueryFalse(null, "3.14.oclIsTypeOf(ocl::Package)");
		//
		ocl.assertQueryFalse(null, "1.oclIsTypeOf(OclInvalid)");
		ocl.assertQueryFalse(null, "1.oclIsTypeOf(OclVoid)");
		ocl.assertQueryFalse(null, "1.oclIsTypeOf(Boolean)");
		ocl.assertQueryFalse(null, "1.oclIsTypeOf(Real)");
		ocl.assertQueryTrue(null, "1.oclIsTypeOf(Integer)");
		ocl.assertQueryTrue(null, "(-1).oclIsTypeOf(Integer)");
		ocl.assertQueryFalse(null, "1.oclIsTypeOf(UnlimitedNatural)");
		ocl.assertQueryFalse(null, "1.oclIsTypeOf(String)");
		ocl.assertQueryFalse(null, "1.oclIsTypeOf(OclAny)");
		ocl.assertQueryFalse(null, "1.oclIsTypeOf(ocl::Package)");
		//
		ocl.assertQueryFalse(null, "*.oclIsTypeOf(OclInvalid)");
		ocl.assertQueryFalse(null, "*.oclIsTypeOf(OclVoid)");
		ocl.assertQueryFalse(null, "*.oclIsTypeOf(Boolean)");
		ocl.assertQueryFalse(null, "*.oclIsTypeOf(Real)");
		ocl.assertQueryFalse(null, "*.oclIsTypeOf(Integer)");
		ocl.assertQueryTrue(null, "*.oclIsTypeOf(UnlimitedNatural)");
		ocl.assertQueryFalse(null, "*.oclIsTypeOf(String)");
		ocl.assertQueryFalse(null, "*.oclIsTypeOf(OclAny)");
		ocl.assertQueryFalse(null, "*.oclIsTypeOf(ocl::Package)");
		//
		ocl.assertQueryFalse(null, "'invalid'.oclIsTypeOf(OclInvalid)");
		ocl.assertQueryFalse(null, "'null'.oclIsTypeOf(OclVoid)");
		ocl.assertQueryFalse(null, "'true'.oclIsTypeOf(Boolean)");
		ocl.assertQueryFalse(null, "'3.14'.oclIsTypeOf(Real)");
		ocl.assertQueryFalse(null, "'1'.oclIsTypeOf(Integer)");
		ocl.assertQueryFalse(null, "'*'.oclIsTypeOf(UnlimitedNatural)");
		ocl.assertQueryTrue(null, "'string'.oclIsTypeOf(String)");
		ocl.assertQueryFalse(null, "'any'.oclIsTypeOf(OclAny)");
		ocl.assertQueryFalse(ocl.pkg1, "'self'.oclIsTypeOf(ocl::Package)");
		//
		ocl.assertQueryFalse(ocl.pkg1, "self.oclIsTypeOf(OclInvalid)");
		ocl.assertQueryFalse(ocl.pkg1, "self.oclIsTypeOf(OclVoid)");
		ocl.assertQueryFalse(ocl.pkg1, "self.oclIsTypeOf(Boolean)");
		ocl.assertQueryFalse(ocl.pkg1, "self.oclIsTypeOf(Real)");
		ocl.assertQueryFalse(ocl.pkg1, "self.oclIsTypeOf(Integer)");
		ocl.assertQueryFalse(ocl.pkg1, "self.oclIsTypeOf(UnlimitedNatural)");
		ocl.assertQueryFalse(ocl.pkg1, "self.oclIsTypeOf(String)");
		ocl.assertQueryFalse(ocl.pkg1, "self.oclIsTypeOf(OclAny)");
		ocl.assertQueryTrue(ocl.pkg1, "self.oclIsTypeOf(ocl::Package)");
		ocl.dispose();
	}

	/**
	 * Tests the oclIsUndefined() operator.
	 */
	@Test public void test_oclIsUndefined() {
		MyOCL ocl = createOCL();
		ocl.assertQueryTrue(null, "invalid.oclIsUndefined()");
		ocl.assertQueryTrue(null, "null.oclIsUndefined()");
		ocl.assertQueryFalse(null, "true.oclIsUndefined()");
		ocl.assertQueryFalse(null, "false.oclIsUndefined()");
		ocl.assertQueryFalse(null, "3.14.oclIsUndefined()");
		ocl.assertQueryFalse(null, "1.oclIsUndefined()");
		ocl.assertQueryFalse(null, "*.oclIsUndefined()");
		ocl.assertQueryFalse(null, "'null'.oclIsUndefined()");
		ocl.assertQueryFalse(ocl.pkg1, "self.oclIsUndefined()");
		ocl.dispose();
	}

	/**
	 * Tests the oclModelType() operator.
	 */
	@Test public void test_oclModelType() {
		MyOCL ocl = createOCL();
		ocl.assertSemanticErrorQuery(null, "invalid.oclModelType()", PivotMessagesInternal.UnresolvedOperation_ERROR_, "OclInvalid", "oclModelType");
		ocl.assertQueryInvalid(ocl.pkg1, "self.oclModelType()");
		// See test_umlValidation_Bug458394 for some real usage
		ocl.dispose();
	}

	/**
	 * Tests the oclModelTypes() operator.
	 */
	@Test public void test_oclModelTypes() {
		MyOCL ocl = createOCL();
		ocl.assertSemanticErrorQuery(null, "invalid.oclModelTypes()", PivotMessagesInternal.UnresolvedOperation_ERROR_, "OclInvalid", "oclModelTypes");
		ocl.assertQueryInvalid(ocl.pkg1, "self.oclModelTypes()");
		// See test_umlValidation_Bug458394 for some real usage
		ocl.dispose();
	}

	/**
	 * Tests the oclType() operator for Booleans.
	 */
	@Test public void test_oclType_Boolean() {
		MyOCL ocl = createOCL();
		EnvironmentFactoryInternalExtension environmentFactory = (EnvironmentFactoryInternalExtension) ocl.getEnvironmentFactory();
		StandardLibrary standardLibrary = environmentFactory.getStandardLibrary();
		org.eclipse.ocl.pivot.Class booleanType = standardLibrary.getBooleanType();
		org.eclipse.ocl.pivot.Class classType = standardLibrary.getClassType();
		org.eclipse.ocl.pivot.Class primitiveType = environmentFactory.getASClass("PrimitiveType");
		org.eclipse.ocl.pivot.Class booleanMetaType = environmentFactory.getASClass("BooleanType");
		assert primitiveType != null;
		assert booleanMetaType != null;
		ocl.assertQueryEquals(null, booleanType, "true.oclType()");
		ocl.assertQueryEquals(null, "Boolean", "true.oclType().name");
		ocl.assertQueryEquals(null, booleanType, "Boolean");
		ocl.assertQueryEquals(null, "Boolean", "Boolean.name");
		ocl.assertQueryEquals(null, booleanMetaType, "true.oclType().oclType()");
		ocl.assertQueryEquals(null, booleanMetaType.getName(), "true.oclType().oclType().name");
		ocl.assertQueryEquals(null, booleanMetaType, "Boolean.oclType()");
		ocl.assertQueryEquals(null, booleanMetaType.getName(), "Boolean.oclType().name");
		ocl.assertQueryEquals(null, classType, "true.oclType().oclType().oclType()");
		ocl.assertQueryEquals(null, "Class", "true.oclType().oclType().oclType().name");
		ocl.assertQueryResults(null, "Set{false,true}", "Boolean.allInstances()");
		ocl.assertQueryResults(null, "Set{false,true}", "true.oclType().allInstances()");
		ocl.assertQueryResults(null, "Set{}", "Boolean.oclType().allInstances()");
		ocl.assertQueryEquals(null, 1, "true.oclType().ownedOperations?->select(name = 'xor')->any(true)?.ownedParameters->size()");
		ocl.dispose();
	}

	/**
	 * Tests the oclType() operator for Classifiers.
	 */
	@Test public void test_oclType_Classifier() {
		MyOCL ocl = createOCL();
		EnvironmentFactoryInternalExtension environmentFactory = (EnvironmentFactoryInternalExtension) ocl.getEnvironmentFactory();
		StandardLibrary standardLibrary = environmentFactory.getStandardLibrary();
		@NonNull Type packageType = ClassUtil.nonNullState(environmentFactory.getASClass("Package"));
		ocl.assertQueryEquals(ocl.pkg1, packageType, "self.oclType()");
		ocl.assertQueryEquals(ocl.pkg1, "Package", "self.oclType().name");
		ocl.assertQueryEquals(null, packageType, "Package");
		ocl.assertQueryEquals(null, "Package", "Package.name");
		ocl.assertQueryEquals(null, standardLibrary.getClassType(), "Package.oclType()");
	//	ocl.assertQueryTrue(null, "Package.allInstances()->size() > 1"); //.name->includes('pivot')");
		ocl.assertQueryResults(null, "Set{}", "Package.allInstances()"); // new empty ModelManager
		ocl.assertQueryEquals(ocl.pkg1, 8, "Package.allInstances()->size()");
		ocl.assertQueryResults(ocl.pkg1, "self.oclAsType(Package)->closure(ownedPackages)->including(self)", "Package.allInstances()");
		ocl.assertQueryEquals(ocl.pkg1, 8, "self.oclType().allInstances()->size()");
		ocl.assertQueryEquals(ocl.pkg1, 0, "Package.oclType().allInstances()->size()");
		ocl.dispose();
	}

	/**
	 * Tests the oclType() operator for Collections.
	 */
	@Test public void test_oclType_Collection() {
		MyOCL ocl = createOCL();
		CompleteEnvironment completeEnvironment = ocl.getCompleteEnvironment();
		StandardLibrary standardLibrary = ocl.getStandardLibrary();
		ocl.assertQueryEquals(null, 1, "Set{1}->oclType().ownedOperations?->select(name = 'flatten')->size()");
		ocl.assertQueryEquals(null, completeEnvironment.getSetType(standardLibrary.getOclVoidType(), false, ValueUtil.ZERO_VALUE, ValueUtil.UNLIMITED_ZERO_VALUE), "Set{}->oclType()");
		ocl.assertQueryEquals(null, completeEnvironment.getSetType(standardLibrary.getIntegerType(), false, ValueUtil.ONE_VALUE, ValueUtil.UNLIMITED_ONE_VALUE), "Set{1}->oclType()");
		ocl.assertQueryResults(null, "Bag{'Integer'}", "Set{1}.oclType().name");
		ocl.assertQueryEquals(null, "Set", "Set{1}->oclType().name");
		ocl.assertSemanticErrorQuery(null, "Set{1}.allInstances()", PivotMessagesInternal.UnresolvedOperation_ERROR_, "Integer", "allInstances");
		ocl.assertSemanticErrorQuery(null, "Set{1}->allInstances()", PivotMessagesInternal.UnresolvedOperation_ERROR_, "Set(Integer)", "allInstances");
	//	ocl.assertSemanticErrorQuery(null, "Set{1}.oclType().allInstances()", PivotMessagesInternal.UnresolvedOperation_ERROR_, "Bag(Class)", "allInstances");
		ocl.assertQueryResults(null, "Bag{}", "Set{1}.oclType().allInstances()");
	//	ocl.assertSemanticErrorQuery(null, "Set{1}->oclType().allInstances()", PivotMessagesInternal.UnresolvedStaticOperationCall_ERROR_, "Set(Integer)", "allInstances", "");
		ocl.assertQueryResults(null, "Set{}", "Set{1}->oclType().allInstances()");
	//	ocl.assertQueryResults(null, "Set{}", "Set.oclType().allInstances()");
	//	ocl.assertQueryTrue(null, "Set.oclType().allInstances()->size() > 30");		// currently 33 distinct Set specializatons.
		ocl.assertQueryResults(null, "Set{}", "Set.oclType().allInstances()");
		ocl.assertQueryEquals(null, standardLibrary.getIntegerType(), "Set{1}->oclType().elementType");
		ocl.dispose();
	}

	/**
	 * Tests the oclType() operator for Enumerations.
	 */
	@Test public void test_oclType_Enumeration() {
		MyOCL ocl = createOCL();
		EnvironmentFactoryInternalExtension environmentFactory = (EnvironmentFactoryInternalExtension) ocl.getEnvironmentFactory();
		StandardLibrary standardLibrary = environmentFactory.getStandardLibrary();
		@NonNull Type collectionKindType = ClassUtil.nonNullState(environmentFactory.getASClass("CollectionKind"));
		org.eclipse.ocl.pivot.Class enumerationType = standardLibrary.getEnumerationType();
		//    	ocl.assertQueryEquals(null, metamodelManager.getPivotType("EnumerationLiteral"), "CollectionKind::Set.oclType()");
		// NB this is not EnumerationLiteral: cf. 4.oclType() is Integer not IntegerLiteral.
		ocl.assertQueryEquals(null, environmentFactory.getASClass("CollectionKind"), "CollectionKind::Set.oclType()");
		ocl.assertQueryEquals(null, "CollectionKind", "CollectionKind::Set.oclType().name");
		ocl.assertQueryEquals(null, collectionKindType, "CollectionKind");
		ocl.assertQueryEquals(null, "CollectionKind", "CollectionKind.name");
		ocl.assertQueryEquals(null, enumerationType, "CollectionKind.oclType()");
		ocl.assertQueryEquals(null, 5, "CollectionKind.allInstances()->size()");
		ocl.assertSemanticErrorQuery(null, "CollectionKind.oclType().ownedLiteral", PivotMessagesInternal.UnresolvedProperty_ERROR_, "Enumeration", "ownedLiteral");
		ocl.assertQueryResults(null, "Set{CollectionKind::Bag,CollectionKind::Collection,CollectionKind::_'OrderedSet',CollectionKind::_'Sequence',CollectionKind::_'Set'}", "CollectionKind.allInstances()");
		ocl.assertQueryResults(null, "Set{CollectionKind::Bag,CollectionKind::Collection,CollectionKind::OrderedSet,CollectionKind::Sequence,CollectionKind::Set}", "CollectionKind::Set.oclType().allInstances()");
	//	ocl.assertQueryResults(null, "Set{AssociativityKind,CollectionKind,PseudostateKind,TransitionKind}", "CollectionKind.oclType().allInstances()");
		ocl.assertQueryResults(null, "Set{}", "CollectionKind.oclType().allInstances()");
		ocl.dispose();
	}

	/**
	 * Tests the oclType() operator for Numerics.
	 */
	@Test public void test_oclType_Numeric() {
		MyOCL ocl = createOCL();
		EnvironmentFactoryInternalExtension environmentFactory = (EnvironmentFactoryInternalExtension) ocl.getEnvironmentFactory();
		StandardLibrary standardLibrary = environmentFactory.getStandardLibrary();
		org.eclipse.ocl.pivot.Class primitiveType = environmentFactory.getASClass("PrimitiveType");
		org.eclipse.ocl.pivot.Class integerType = standardLibrary.getIntegerType();
		ocl.assertQueryEquals(null, integerType, "Integer");
//
		ocl.assertQueryEquals(null, standardLibrary.getIntegerType(), "3.oclType()");
		ocl.assertQueryEquals(null, standardLibrary.getRealType(), "3.0.oclType()");
		ocl.assertQueryEquals(null, standardLibrary.getUnlimitedNaturalType(), "*.oclType()");
		ocl.assertQueryEquals(null, primitiveType, "Integer.oclType()");
		ocl.assertQueryEquals(null, integerType, "Integer");
	//	ocl.assertSemanticErrorQuery(null, "Integer.allInstances()", PivotMessagesInternal.UnresolvedStaticOperationCall_ERROR_, "Integer", "allInstances", "");
		ocl.assertQueryResults(null, "Set{}", "Integer.allInstances()");
	//	ocl.assertSemanticErrorQuery(null, "3.oclType().allInstances()", PivotMessagesInternal.UnresolvedStaticOperationCall_ERROR_, "Integer", "allInstances", "");
		ocl.assertQueryResults(null, "Set{}", "3.oclType().allInstances()");
		ocl.assertQueryResults(null, "Set{}", "Integer.oclType().allInstances()");
		ocl.assertQueryEquals(null, "Integer", "4.oclType().name");
		ocl.dispose();
	}

	/**
	 * Tests the oclType() operator for OclAny.
	 */
	@Test public void test_oclType_OclAny() {
		MyOCL ocl = createOCL();
		EnvironmentFactoryInternalExtension environmentFactory = (EnvironmentFactoryInternalExtension) ocl.getEnvironmentFactory();
		StandardLibrary standardLibrary = environmentFactory.getStandardLibrary();
		org.eclipse.ocl.pivot.Class anyType = standardLibrary.getOclAnyType();
		org.eclipse.ocl.pivot.Class anyTypeClass = environmentFactory.getASClass("AnyType");
		ocl.assertQueryEquals(null, standardLibrary.getOclVoidType(), "null.oclType()");
		//    	ocl.assertQueryEquals(null, standardLibrary.getOclVoidType(), "null.oclAsType(OclAny).oclType()");		// Cast does not change the dynamic type
		//    	ocl.assertQueryEquals(null, "OclAny", "null.oclAsType(OclAny).name");
		ocl.assertQueryEquals(null, anyType, "OclAny");
		ocl.assertQueryEquals(null, "OclAny", "OclAny.name");
		ocl.assertQueryEquals(null, anyTypeClass, "OclAny.oclType()");
	//	ocl.assertSemanticErrorQuery(null, "OclAny.allInstances()", PivotMessagesInternal.UnresolvedStaticOperationCall_ERROR_, "OclAny", "allInstances", "");
		ocl.assertQueryResults(null, "Set{}", "OclAny.allInstances()");
	//	ocl.assertSemanticErrorQuery(null, "null.oclAsType(OclAny).oclType().allInstances()", PivotMessagesInternal.UnresolvedStaticOperationCall_ERROR_, "OclAny", "allInstances", "");
		ocl.assertQueryResults(null, "Set{null}", "null.oclAsType(OclAny).oclType().allInstances()");
		ocl.assertQueryResults(null, "Set{}", "OclAny.oclType().allInstances()");
		ocl.dispose();
	}

	/**
	 * Tests the oclType() operator for OclInvalid.
	 */
	@Test public void test_oclType_OclInvalid() {
		MyOCL ocl = createOCL();
		EnvironmentFactoryInternalExtension environmentFactory = (EnvironmentFactoryInternalExtension) ocl.getEnvironmentFactory();
		StandardLibrary standardLibrary = environmentFactory.getStandardLibrary();
		org.eclipse.ocl.pivot.Class invalidType = standardLibrary.getOclInvalidType();
		org.eclipse.ocl.pivot.Class invalidTypeClass = environmentFactory.getASClass("InvalidType");
		ocl.assertQueryInvalid(null, "invalid.oclType()");
		ocl.assertQueryInvalid(null, "invalid.oclType().name");
		ocl.assertQueryEquals(null, invalidType, "OclInvalid");
		ocl.assertQueryEquals(null, "OclInvalid", "OclInvalid.name");
		ocl.assertQueryEquals(null, invalidTypeClass, "OclInvalid.oclType()");
		ocl.assertQueryInvalid(null, "OclInvalid.allInstances()");
		ocl.assertQueryInvalid(null, "invalid.oclType().allInstances()");
		ocl.assertQueryResults(null, "Set{}", "OclInvalid.oclType().allInstances()");
		ocl.assertQueryInvalid(null, "invalid.oclType().ownedOperations->select(name = '=')->any(true).ownedParameters->size()");
		ocl.dispose();
	}

	/**
	 * Tests the oclType() operator for OclVoid.
	 */
	@Test public void test_oclType_OclVoid() {
		MyOCL ocl = createOCL();
		EnvironmentFactoryInternalExtension environmentFactory = (EnvironmentFactoryInternalExtension) ocl.getEnvironmentFactory();
		StandardLibrary standardLibrary = environmentFactory.getStandardLibrary();
		org.eclipse.ocl.pivot.Class voidTypeClass = environmentFactory.getASClass("VoidType");
		org.eclipse.ocl.pivot.Class nullType = standardLibrary.getOclVoidType();
		ocl.assertQueryEquals(null, nullType, "null.oclType()");
		ocl.assertQueryEquals(null, "OclVoid", "null.oclType().name");
		ocl.assertQueryEquals(null, nullType, "OclVoid");
		ocl.assertQueryEquals(null, "OclVoid", "OclVoid.name");
		ocl.assertQueryEquals(null, voidTypeClass, "OclVoid.oclType()");
		ocl.assertQueryResults(null, "Set{null}", "OclVoid.allInstances()");
		ocl.assertQueryResults(null, "Set{null}", "null.oclType().allInstances()");
		ocl.assertQueryResults(null, "Set{}", "OclVoid.oclType().allInstances()");
		ocl.assertQueryEquals(null, 1, "null.oclType().ownedOperations?->select(name = '=')->any(true)?.ownedParameters->size()");
		ocl.dispose();
	}

	/**
	 * Tests the oclType() operator for Tuples.
	 */
	@Test public void test_oclType_Tuple() {
		MyOCL ocl = createOCL();
		EnvironmentFactoryInternalExtension environmentFactory = (EnvironmentFactoryInternalExtension) ocl.getEnvironmentFactory();
		StandardLibrary standardLibrary = environmentFactory.getStandardLibrary();
		org.eclipse.ocl.pivot.Class tupleTypeClass = environmentFactory.getASClass("TupleType");
		TuplePartId partId = IdManager.getTuplePartId(0, "a", TypeId.INTEGER);
		TupleTypeId tupleId = IdManager.getTupleTypeId("Tuple", partId);
		TupleType tupleType = ocl.getIdResolver().getTupleType(tupleId);
		ocl.assertQueryEquals(null, tupleType, "Tuple{a:Integer=3}.oclType()");
		ocl.assertQueryEquals(null, tupleType, "Tuple(a:Integer)");
		ocl.assertQueryEquals(null, tupleTypeClass, "Tuple(a:Integer).oclType()");
	//	ocl.assertSemanticErrorQuery(null, "Tuple(a:Integer).allInstances()", PivotMessagesInternal.UnresolvedStaticOperationCall_ERROR_, "Tuple(a:Integer[1])", "allInstances", "");
		ocl.assertQueryResults(null, "Set{}", "Tuple(a:Integer).allInstances()");
	//	ocl.assertSemanticErrorQuery(null, "Tuple{a:Integer=3}.oclType().allInstances()", PivotMessagesInternal.UnresolvedStaticOperationCall_ERROR_, "Tuple(a:Integer[1])", "allInstances", "");	// FIXME
		ocl.assertQueryResults(null, "Set{}", "Tuple{a:Integer=3}.oclType().allInstances()");	// FIXME
		ocl.assertQueryResults(null, "Set{}", "Tuple(a:Integer).oclType().allInstances()");
		ocl.assertQueryEquals(null, "Tuple", "Tuple{a:Integer=3}.oclType().name");
		ocl.dispose();
	}

	/**
	 * Tests the oclType() operator.
	 */
	@Test public void test_oclType() {
		MyOCL ocl = createOCL();
		EnvironmentFactoryInternalExtension environmentFactory = (EnvironmentFactoryInternalExtension) ocl.getEnvironmentFactory();
		StandardLibrary standardLibrary = environmentFactory.getStandardLibrary();
		org.eclipse.ocl.pivot.Class classType = standardLibrary.getClassType();
		org.eclipse.ocl.pivot.Class primitiveType = environmentFactory.getASClass("PrimitiveType");
		org.eclipse.ocl.pivot.Class booleanMetaType = environmentFactory.getASClass("BooleanType");
		assert primitiveType != null;
		assert booleanMetaType != null;
		ocl.assertQueryEquals(null, standardLibrary.getStringType(), "'string'.oclType()");
		ocl.assertQueryEquals(null, standardLibrary.getOclVoidType(), "self.oclType()");
		ocl.assertQueryEquals(null, primitiveType, "3.oclType().oclType()");
		ocl.assertQueryEquals(null, classType, "3.oclType().oclType().oclType()");
		ocl.assertQueryEquals(null, booleanMetaType, "Boolean.oclType()");
		ocl.assertQueryEquals(null, booleanMetaType.getName(), "Boolean.oclType().name");
		ocl.assertSemanticErrorQuery(null, "3.oclType(OclAny)", PivotMessagesInternal.UnresolvedOperationCall_ERROR_, "Integer", "oclType", "OclAny");
		ocl.dispose();
	}

	/**
	 * Tests the oclTypes() operator.
	 */
	@Test public void test_oclTypes() {
		MyOCL ocl = createOCL();
		EnvironmentFactoryInternalExtension environmentFactory = (EnvironmentFactoryInternalExtension) ocl.getEnvironmentFactory();
		StandardLibrary standardLibrary = environmentFactory.getStandardLibrary();
		org.eclipse.ocl.pivot.Class primitiveType = environmentFactory.getASClass("PrimitiveType");
		org.eclipse.ocl.pivot.Class classType = environmentFactory.getASClass("Class");
		org.eclipse.ocl.pivot.Class booleanMetaType = environmentFactory.getASClass("BooleanType");
		assert primitiveType != null;
		CollectionTypeId bagTypeId = TypeId.BAG.getSpecializedId(TypeId.OCL_ANY);
		CollectionTypeId setTypeId = TypeId.SET.getSpecializedId(TypeId.OCL_ANY);
		if (!useCodeGen) {				// FIXME Bad CG Set equals
			ocl.assertQueryEquals(null, ValueUtil.createSetOfEach(setTypeId, standardLibrary.getStringType()), "'string'.oclTypes()");
			ocl.assertQueryEquals(null, ValueUtil.createSetOfEach(setTypeId, standardLibrary.getOclVoidType()), "self.oclTypes()");
			ocl.assertQueryEquals(null, ValueUtil.createBagOfEach(bagTypeId, primitiveType), "3.oclTypes().oclType()");
			ocl.assertQueryEquals(null, ValueUtil.createBagOfEach(bagTypeId, classType), "3.oclTypes().oclType().oclType()");
			ocl.assertQueryEquals(null, ValueUtil.createSetOfEach(setTypeId, booleanMetaType), "Boolean.oclTypes()");
			//    	ocl.assertQueryEquals(null, ValueUtil.createSetOfEach(setTypeId, primitiveType.getName()), "Boolean.oclTypes().name");
		}
		ocl.assertSemanticErrorQuery(null, "3.oclTypes(OclAny)", PivotMessagesInternal.UnresolvedOperationCall_ERROR_, "Integer", "oclTypes", "OclAny");
		ocl.dispose();
	}

	//	@Test public void testMetaclassInstanceType() {
	//		ocl.assertQueryEquals(null, metamodelManager.getSequenceType(metamodelManager.getIntegerType()), "Sequence(Integer).oclType().instanceType");
	//	}

	@Test public void testToString() {
		MyOCL ocl = createOCL();
		ocl.loadEPackage("ecore", EcorePackage.eINSTANCE);
		@SuppressWarnings("null") @NonNull String emfString = EcoreFactory.eINSTANCE.createFromString(EcorePackage.Literals.EDATE, "2000-01-24").toString();
		ocl.assertQueryEquals(null, emfString, "ecore::EDate{value='2000-01-24'}.toString()");
		ocl.dispose();
	}
}
