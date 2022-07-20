/*******************************************************************************
 * Copyright (c) 2010, 2021 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 *   E.D.Willink - Bug 296409, 297541
 *******************************************************************************/
package org.eclipse.ocl.examples.pivot.tests;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.examples.xtext.tests.TestFileSystem;
import org.eclipse.ocl.pivot.CompleteEnvironment;
import org.eclipse.ocl.pivot.Model;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.PivotTables;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.ids.CollectionTypeId;
import org.eclipse.ocl.pivot.ids.IdResolver;
import org.eclipse.ocl.pivot.ids.MapTypeId;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.complete.StandardLibraryInternal;
import org.eclipse.ocl.pivot.internal.manager.MetamodelManagerInternal;
import org.eclipse.ocl.pivot.internal.messages.PivotMessagesInternal;
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal.EnvironmentFactoryInternalExtension;
import org.eclipse.ocl.pivot.library.LibraryConstants;
import org.eclipse.ocl.pivot.messages.PivotMessages;
import org.eclipse.ocl.pivot.messages.StatusCodes;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.OCL;
import org.eclipse.ocl.pivot.utilities.PivotConstants;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.utilities.SemanticException;
import org.eclipse.ocl.pivot.utilities.StringUtil;
import org.eclipse.ocl.pivot.utilities.ValueUtil;
import org.eclipse.ocl.pivot.values.BagValue;
import org.eclipse.ocl.pivot.values.CollectionValue;
import org.eclipse.ocl.pivot.values.InvalidValueException;
import org.eclipse.ocl.pivot.values.MapValue;
import org.eclipse.ocl.pivot.values.OrderedSetValue;
import org.eclipse.ocl.pivot.values.SequenceValue;
import org.eclipse.ocl.pivot.values.SetValue;
import org.eclipse.ocl.pivot.values.Value;
import org.eclipse.ocl.xtext.essentialocl.cs2as.EssentialOCLCS2ASMessages;
import org.eclipse.ocl.xtext.oclinecore.OCLinEcoreStandaloneSetup;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/**
 * Tests for iterator expressions.
 */
@RunWith(value = Parameterized.class)
public class IteratorsTest4 extends PivotTestSuite
{
	public static final @NonNull String VIOLATED_TEMPLATE = "The ''{0}'' constraint is violated for ''{1}''";	// _UI_GenericConstraint_diagnostic = The ''{0}'' constraint is violated on ''{1}''

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

		public MyOCL(@NonNull TestFileSystem testFileSystem, @NonNull String testPackageName, @NonNull String name) {
			super(testFileSystem, testPackageName, name, useCodeGen ? getProjectMap() : OCL.NO_PROJECTS, null);
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

	public IteratorsTest4(boolean useCodeGen) {
		super(useCodeGen);
	}

	@Override
	protected @NonNull String getTestPackageName() {
		return "Iterators";
	}

	@BeforeClass public static void resetCounter() throws Exception {
		PivotTestSuite.resetCounter();
	}

	@Override
	protected @NonNull MyOCL createOCL() {
		return new MyOCL(getTestFileSystem(), getTestPackageName(), getName());
	}

	@Override
	@Before public void setUp() throws Exception {
		super.setUp();
		PivotTables.LIBRARY.getClass();
	}

	@Override
	@After public void tearDown() throws Exception {
		super.tearDown();
	}

	/**
	 * Tests the any() iterator.
	 */
	@Test public void test_any() {
		MyOCL ocl = createOCL();
		EnvironmentFactoryInternalExtension environmentFactory = (EnvironmentFactoryInternalExtension) ocl.getEnvironmentFactory();
		ocl.getEnvironmentFactory().setSafeNavigationValidationSeverity(StatusCodes.Severity.WARNING);
		org.eclipse.ocl.pivot.Class pkg1Type = environmentFactory.getASClass("Package");
		// complete form
		ocl.assertQueryEquals(ocl.pkg1, ocl.bob, "let op : Set(Package[*|?]) = ownedPackages in op->any(p : ocl::Package[?] | p?.name = 'bob')");
		ocl.assertQueryEquals(ocl.pkg1, ocl.bob, "let op : Set(Package[*|?]) = ownedPackages in op?->any(p : ocl::Package | p.name = 'bob')");
		ocl.assertQueryEquals(ocl.pkg1, ocl.bob, "let op : Set(Package[*|?]) = ownedPackages in op?->any(p : ocl::Package[1] | p.name = 'bob')");
		ocl.assertValidationErrorQuery(pkg1Type, "let op : Set(Package[*|?]) = ownedPackages in op->any(p : ocl::Package[1] | p.name = 'bob')",
			PivotMessages.ValidationConstraintIsNotSatisfied_ERROR_, "IteratorExp::UnsafeSourceCanNotBeNull", "op->any(p : Package[1] | p.name.=('bob'))");
		ocl.assertValidationErrorQuery(pkg1Type, "let op : Set(Package[*|?]) = ownedPackages in op->any(p : ocl::Package[?] | p.name = 'bob')",
			PivotMessages.ValidationConstraintIsNotSatisfied_ERROR_, "PropertyCallExp::UnsafeSourceCanNotBeNull", "p.name");

		// shorter form
		ocl.assertQueryEquals(ocl.pkg1, ocl.bob, "let op : Set(Package[*|1]) = ownedPackages in op->any(p | p.name = 'bob')");
		ocl.assertQueryEquals(ocl.pkg1, ocl.bob, "let op : Set(Package[*|?]) = ownedPackages in op?->any(p | p.name = 'bob')");
		//		ocl.assertValidationErrorQuery(pkg1Type, "ownedPackages->any(p | p.name = 'bob')",
		//			PivotMessages.ValidationConstraintIsNotSatisfied_ERROR_, "PropertyCallExp", "UnsafeSourceMustBeNotNull", "p.name");
		//		ocl.assertValidationErrorQuery(pkg1Type, "ownedPackages?->any(p | p?.name = 'bob')",
		//			PivotMessages.ValidationConstraintIsNotSatisfied_ERROR_, "PropertyCallExp", "SafeSourceCannotBeNull", "p?.name");

		// shortest form
		//    	ocl.assertValidationErrorQuery(pkg1Type, "ownedPackages->any(name = 'bob')",
		//    		PivotMessages.ValidationConstraintIsNotSatisfied_ERROR_, "PropertyCallExp", "UnsafeSourceMustBeNotNull", "1_.name");
		ocl.assertQueryEquals(ocl.pkg1, ocl.bob, "let op : Set(Package[*|?]) = ownedPackages in op?->any(name = 'bob')");

		// negative
		ocl.assertQueryNotSame(ocl.pkg1, ocl.bob, "let op : Set(Package[*|?]) = ownedPackages in op?->any(name = 'pkg2')");
		//        ocl.assertValidationErrorQuery(pkg1Type, "ownedPackages->any(name = 'pkg2')",
		//			PivotMessages.ValidationConstraintIsNotSatisfied_ERROR_, "PropertyCallExp", "UnsafeSourceMustBeNotNull", "1_.name");

		ocl.assertQueryInvalid(null, "Sequence{}->any(s | s = false)");		// OMG Issue 18504
		ocl.assertQueryFalse(null, "Sequence{false}->any(s | s = false)");
		ocl.assertQueryFalse(null, "Sequence{false, false}->any(s | s = false)");

		ocl.assertQueryInvalid(null, "Sequence{}->any(s | s = null)");		// OMG Issue 18504
		// FIXME BUG 507628 comment 11 - check all test chnages wrt 2021-06
		ocl.assertValidationErrorQuery(pkg1Type, "Sequence{null}->any(s | s = null)",
			PivotMessages.ValidationConstraintIsNotSatisfied_ERROR_, "IteratorExp::UnsafeSourceCanNotBeNull", "Sequence{null}->any(s : OclVoid[1] | s.=(null))");
		ocl.assertValidationErrorQuery(pkg1Type, "Sequence{null, null}->any(s | s = null)",
			PivotMessages.ValidationConstraintIsNotSatisfied_ERROR_, "IteratorExp::UnsafeSourceCanNotBeNull", "Sequence{null, null}->any(s : OclVoid[1] | s.=(null))");
	//	ocl.assertQueryNull(null, "Sequence{null, null}->any(s | s = null)");

		ocl.assertQueryDefined(ocl.pkg1, "let op : Set(Package[*|1]) = ownedPackages in op->any(true)");
		ocl.assertQueryInvalid(ocl.pkg1, "let op : Set(Package[*|1]) = ownedPackages in op->any(false)");			// OMG Issue 18504
		ocl.assertQueryDefined(ocl.pkg1, "let op : Set(Package[*|?]) = ownedPackages in op?->any(true)");

		ocl.assertQueryEquals(null, 2, "Map{2 with 1, 1 with 2}->any(key with value | key > value)");
		ocl.assertQueryInvalid(null, "Map{2 with 1, 1 with 2}->any(key with value | key = value)");
		ocl.dispose();
	}

	/**
	 * Tests that when the body of an iterator results in invalid, the entire
	 * iterator expression's value is invalid.
	 */
	@Test public void test_any_invalidBody_142518() {
		MyOCL ocl = createOCL();
		ocl.assertQueryInvalid(null, "Bag{1, 2, 3}->any('true')");		// Bug 415669
		ocl.assertQueryInvalid(null, "Bag{1, 2, 3}->any(2)");			// Bug 415669

		ocl.assertQueryInvalid(EcorePackage.eINSTANCE,
				"let b:Boolean = null in Bag{1, 2, 3}->any(b and b)");

		// same deal for a null value (in the any case)
		ocl.assertQueryInvalid(EcorePackage.eINSTANCE,
				"Bag{1, 2, 3}->any(null.oclAsType(Boolean))");
		ocl.dispose();
	}

	/**
	 * Tests the closure() iterator.
	 */
	// pkg1
	// pkg1::pkg2
	// pkg1::pkg2::jim
	// pkg1::bob
	// pkg1::pkg3
	// pkg1::pkg3::pkg4
	// pkg1::pkg3::pkg5
	// pkg1::pkg3::pkg5::george
	@Test public void test_closure() {
		MyOCL ocl = createOCL();
		EnvironmentFactoryInternalExtension environmentFactory = (EnvironmentFactoryInternalExtension) ocl.getEnvironmentFactory();
		IdResolver idResolver = ocl.getIdResolver();
		@NonNull Type packageType = ClassUtil.nonNullState(environmentFactory.getASClass("Package"));
		CollectionTypeId typeId = TypeId.SET.getSpecializedId(packageType.getTypeId());
		CollectionValue expected1 = idResolver.createSetOfEach(typeId, ocl.pkg1, ocl.pkg3, ocl.pkg5, ocl.george); // closure does include sources (george)
		ocl.assertQueryEquals(ocl.george, expected1, "self.oclAsType(Package)->closure(owningPackage)");

		CollectionValue expected2 = idResolver.createSetOfEach(typeId, ocl.pkg1, ocl.pkg2, ocl.jim, ocl.bob, ocl.pkg3, ocl.pkg4, ocl.pkg5, ocl.george);
		//        CollectionValue expected2a = metamodelManager.createOrderedSetValue(null, pkg2, jim, bob, pkg3, pkg4, pkg5, george);
		ocl.assertQueryEquals(ocl.pkg1, expected2, "self.oclAsType(Package)->closure(ownedPackages)");
		// FIXME not a valid test for UML's unordered nested packages
		//        ocl.assertQueryEquals(ocl.pkg1, expected2a, "self->asSequence()->closure(ownedPackages)");
		ocl.assertQueryEquals(ocl.pkg1, expected2, "self.oclAsType(Package)->closure(ownedPackages->asSequence())");
		SetValue expected3 = idResolver.createSetOfEach(typeId, ocl.pkg1, ocl.pkg2, ocl.jim, ocl.bob, ocl.pkg3, ocl.pkg4, ocl.pkg5, ocl.george);
		ocl.assertQueryEquals(ocl.pkg1, expected3, "self.oclAsType(Package)->asBag()->closure(ownedPackages)");
		ocl.assertQueryEquals(ocl.pkg1, expected3, "self.oclAsType(Package)->closure(ownedPackages->asBag())");

		// empty closure
		CollectionTypeId collectedId = expected1.getTypeId();
		//        @SuppressWarnings("unused") DomainType elementType = collectionType.getElementType();
		ocl.assertQueryEquals(ocl.pkg1, idResolver.createSetOfEach(collectedId, ocl.pkg1), "self.oclAsType(Package)->closure(owningPackage)");
		//WIP        ocl.assertQueryNotEquals(ocl.pkg1, getEmptySetValue(), "self->closure(owningPackage)");
		// empty closure
		collectedId = TypeId.ORDERED_SET.getSpecializedId(packageType.getTypeId());
		ocl.assertQueryEquals(ocl.pkg1, idResolver.createOrderedSetOfEach(collectedId, ocl.pkg1), "self.oclAsType(Package)->asSequence()->closure(owningPackage)");
		//WIP 		ocl.assertQueryNotEquals(ocl.pkg1, metamodelManager.createOrderedSetValue(metamodelManager.getOrderedSetType(elementType)), "self->asSequence()->closure(owningPackage)");
		ocl.dispose();
	}

	/**
	 * Tests that the closure() iterator handles cycles.
	 */
	@Test public void test_closure_cycles() {
		MyOCL ocl = createOCL();
		EnvironmentFactoryInternalExtension environmentFactory = (EnvironmentFactoryInternalExtension) ocl.getEnvironmentFactory();
		IdResolver idResolver = ocl.getIdResolver();
		org.eclipse.ocl.pivot.@NonNull Class packageMetaclass = ClassUtil.nonNullState(environmentFactory.getASClass("Package"));
		CollectionTypeId typeId = TypeId.SET.getSpecializedId(packageMetaclass.getTypeId());
		Object ownedPackages = getAttribute(packageMetaclass, "ownedPackages", packageMetaclass);
		Object owningPackage = getAttribute(packageMetaclass, "owningPackage", packageMetaclass);
		assert (owningPackage != null) && (ownedPackages != null);
		SetValue expected = idResolver.createSetOfEach(typeId, ownedPackages, owningPackage); // cyclic closure *does* include self
		ocl.assertQueryEquals(owningPackage, expected, "self->closure(opposite)");
		ocl.assertQueryEquals(ownedPackages, expected, "self->closure(opposite)");
		ocl.dispose();
	}

	/**
	 * Tests parsing the closure of operation calls.
	 */
	@Test public void test_closure_operations() {
		MyOCL ocl = createOCL();
		Resource fakeResource = new XMIResourceFactoryImpl().createResource(URI.createURI("fake"));
		Model fakeRoot = PivotUtil.createModel(null);
		org.eclipse.ocl.pivot.Package fakePkg = PivotUtil.createOwnedPackage(fakeRoot, "fake");
		fakeResource.getContents().add(fakePkg);
		org.eclipse.ocl.pivot.Class fake = ocl.createOwnedClass(fakePkg, "Fake", false);
		ocl.createGeneralization(fake, ocl.getStandardLibrary().getOclAnyType());
		Operation getFakes = ocl.createOwnedOperation(fake, "getFakes", null, null, fake, true);
		getFakes.setType(ocl.getCompleteEnvironment().getSetType(fake, false, null, null));

		ocl.assertQuery(fake, "self->closure(getFakes())");
		ocl.dispose();
	}

	/**
	 * Tests the validation of the closure() iterator.
	 *
	@Test public void test_closureValidation() {
		MyOCL ocl = createMyOCL();
	    // non-recursive reference
	    ocl.assertQueryInvalid(ocl.pkg1, "self->closure(xyzzy)");
	    ocl.assertBadQuery(SemanticException.class, Diagnostic.ERROR,
	    	"self->closure(ownedType)",
	    	OCLMessages.NonStd_Iterator_, "closure");
		ocl.dispose();
	} */

	/**
	 * Tests the validation of the closure() iterator for conformance of the
	 * body type with the iterator variable (source element) type.
	 */
	@Test public void test_closureValidation_typeConformance_154695() {
		MyOCL ocl = createOCL();
		StandardLibraryInternal standardLibrary = ocl.getStandardLibrary();
		CompleteEnvironment completeEnvironment = ocl.getCompleteEnvironment();
		Resource fakeResource = new XMIResourceFactoryImpl().createResource(URI.createURI("fake"));
		Model fakeRoot = PivotUtil.createModel(null);
		org.eclipse.ocl.pivot.Package fakePkg = PivotUtil.createOwnedPackage(fakeRoot, "fake");
		fakeResource.getContents().add(fakePkg);
		org.eclipse.ocl.pivot.Class fake = ocl.createOwnedClass(fakePkg, "Fake", false);
		@SuppressWarnings("unused")
		Operation getFakes = ocl.createOwnedOperation(fake, "getFakes", null, null, completeEnvironment.getSetType(fake, false, null, null), true);

		// subclass the Fake class
		org.eclipse.ocl.pivot.Class subFake = ocl.createOwnedClass(fakePkg, "Subfake", false);
		ocl.createGeneralization(subFake, fake);
		ocl.createGeneralization(fake, standardLibrary.getOclAnyType());

		// get sub-fakes from a fake
		@SuppressWarnings("unused")
		Operation getSubFakes = ocl.createOwnedOperation(fake, "getSubFakes", null, null, completeEnvironment.getSetType(subFake, false, null, null), true);

		//        helper.setContext(subFake);

		// this should not parse because the result of the closure
		// expression
		// is more general than the iterator variable, so cannot be
		// assigned recursively
		ocl.assertValidationErrorQuery(subFake, "self->closure(getFakes())",
			VIOLATED_TEMPLATE, "IteratorExp::ClosureBodyElementTypeIsIteratorType", "self.oclAsSet()->closure(1_ : fake::Subfake[1] | 1_.getFakes())");

		// this should parse OK because the result of the closure expression
		// is more specific than the iterator variable, so it can be
		// assigned recursively
		ocl.assertQuery(fake, "self->closure(getSubFakes())");
		ocl.dispose();
	}

	/**
	 * Tests that the closure() body is not necessarily compatible.
	 */
	@Test public void test_closure_body_393509() {
		MyOCL ocl = createOCL();
		EnvironmentFactoryInternalExtension environmentFactory = (EnvironmentFactoryInternalExtension) ocl.getEnvironmentFactory();
		IdResolver idResolver = ocl.getIdResolver();
		org.eclipse.ocl.pivot.@NonNull Class packageMetaclass = ClassUtil.nonNullState(environmentFactory.getASClass("Package"));
		org.eclipse.ocl.pivot.@NonNull Class propertyMetaclass = ClassUtil.nonNullState(environmentFactory.getASClass("Property"));
		CollectionTypeId typeId = TypeId.SET.getSpecializedId(packageMetaclass.getTypeId());
		Property owningPackage = getAttribute(packageMetaclass, "owningPackage", packageMetaclass);
		SetValue expected = idResolver.createSetOfEach(typeId, owningPackage, packageMetaclass, packageMetaclass.eContainer(), packageMetaclass.eContainer().eContainer());
		ocl.assertQueryEquals(owningPackage, expected, "self->closure(i : OclElement | i.oclContainer())");
		ocl.assertValidationErrorQuery(propertyMetaclass, "self->closure(oclContainer())", VIOLATED_TEMPLATE, "IteratorExp::ClosureBodyElementTypeIsIteratorType", "self.oclAsSet()->closure(1_ : Property[1] | 1_.oclContainer())");
		ocl.dispose();
	}

	/**
	 * Tests that when the body of an iterator results in invalid, the entire
	 * iterator expression's value is invalid.
	 */
	@Test public void test_closure_invalidBody_142518() {
		MyOCL ocl = createOCL();
		ocl.assertQueryInvalid(ocl.getUMLMetamodel(),
			"let c : ocl::Type = invalid in ownedClasses->closure(c)", PivotMessages.InvalidLiteral, InvalidValueException.class);

		// in the case of a null value, null is allowed in a collection, so
		// it does not result in invalid
		ocl.assertQueryResults(null, "Set{5, null}",
				"let c : Set(Integer) = Set{null} in 5->closure(c)");

		//        Set<Object> expected = Collections.singleton(getNull());
		//        ocl.assertQueryEquals(EcorePackage.eINSTANCE, expected,
		//        	"let c : Set(ocl::Type) = Set{null} in ownedType->closure(c)");
		ocl.dispose();
	}

	@SuppressWarnings("unchecked")
	@Test public void test_closure_recursions_401302() throws IOException {
		MyOCL ocl = createOCL();
		if (!EcorePlugin.IS_ECLIPSE_RUNNING) {
			OCLinEcoreStandaloneSetup.doSetup();
		}
		String nodeModel =
				"package nodes : nodes = 'http://nodes'{\n" +
						//			"    class Root {\n" +
						//			"    	property nodes : Node[*] {composes};\n" +
						//			"	 }\n" +
						"    class Node {\n" +
						"    	property nodes : Node[*] {ordered,!unique};\n" +
						"    	property name : String;\n" +
						"	 }\n" +
						"}\n";
		URI uri = createEcoreFile(ocl, "NodeModel", nodeModel);
		Resource ecoreResource = ocl.getResourceSet().getResource(uri, true);
		EPackage nodesEPackage = (EPackage) ecoreResource.getContents().get(0);
		//		EClass rootEClass = (EClass) nodesEPackage.getEClassifier("Root");
		EClass nodeEClass = (EClass) nodesEPackage.getEClassifier("Node");
		EAttribute nameEAttribute = (EAttribute) nodeEClass.getEStructuralFeature("name");
		EReference nodesEReference = (EReference) nodeEClass.getEStructuralFeature("nodes");
		EFactory nodesEFactory = nodesEPackage.getEFactoryInstance();
		//		EObject root = nodesEFactory.create(rootEClass);
		EObject node1 = nodesEFactory.create(nodeEClass);
		EObject node2 = nodesEFactory.create(nodeEClass);
		EObject node3 = nodesEFactory.create(nodeEClass);
		EObject node4 = nodesEFactory.create(nodeEClass);
		EObject node5 = nodesEFactory.create(nodeEClass);
		node1.eSet(nameEAttribute, "node1");
		node2.eSet(nameEAttribute, "node2");
		node3.eSet(nameEAttribute, "node3");
		node4.eSet(nameEAttribute, "node4");
		node5.eSet(nameEAttribute, "node5");
		//
		((List<EObject>)node1.eGet(nodesEReference)).add(node2);
		//
		((List<EObject>)node2.eGet(nodesEReference)).add(node1);
		((List<EObject>)node2.eGet(nodesEReference)).add(node1);			// This repetition terminated recursion erroneously
		((List<EObject>)node2.eGet(nodesEReference)).add(node2);
		((List<EObject>)node2.eGet(nodesEReference)).add(node3);
		((List<EObject>)node2.eGet(nodesEReference)).add(node4);
		((List<EObject>)node2.eGet(nodesEReference)).add(node5);
		((List<EObject>)node2.eGet(nodesEReference)).add(node2);
		((List<EObject>)node2.eGet(nodesEReference)).add(node1);
		((List<EObject>)node2.eGet(nodesEReference)).add(node1);
		ocl.assertQueryEquals(node1, 5, "self->closure(nodes)->size()");
		ocl.dispose();
	}

	/**
	 * Tests the collect() iterator.
	 */
	@Test public void test_collect() {
		MyOCL ocl = createOCL();
		EnvironmentFactoryInternalExtension environmentFactory = (EnvironmentFactoryInternalExtension) ocl.getEnvironmentFactory();
		IdResolver idResolver = ocl.getIdResolver();
		//    	Abstract2Moniker.TRACE_MONIKERS.setState(true);
		@NonNull Type packageType = ClassUtil.nonNullState(environmentFactory.getASClass("Package"));
		CollectionTypeId typeId = TypeId.BAG.getSpecializedId(packageType.getTypeId());
		CollectionValue expected1 = idResolver.createBagOfEach(typeId, "pkg2", "bob", "pkg3");
		// complete form
		ocl.assertQueryEquals(ocl.pkg1, expected1, "ownedPackages?->collect(p : ocl::Package | p.name)");

		// shorter form
		ocl.assertQueryEquals(ocl.pkg1, expected1, "ownedPackages?->collect(p | p.name)");

		// yet shorter form
		ocl.assertQueryEquals(ocl.pkg1, expected1, "ownedPackages?->collect(name)");

		// shortest form
		ocl.assertQueryEquals(ocl.pkg1, expected1, "ownedPackages?.name");

		// flattening of nested collections
		CollectionValue expected2 = idResolver.createBagOfEach(typeId, ocl.jim, ocl.pkg4, ocl.pkg5);
		// ownedPackages is Set<Package>
		// ownedPackages->collectNested(ownedPackages) is Bag<Set<Package>>
		// ownedPackages->collectNested(ownedPackages)->flatten() is Bag<Package>
		ocl.assertQueryEquals(ocl.pkg1, expected2, "ownedPackages?.ownedPackages");
		ocl.assertQueryResults(ocl.pkg1, "Sequence{1,2}", "let s:Sequence(OclAny) = Sequence{'a','bb'} in s->collect(oclAsType(String)).size()");

		ocl.assertQueryResults(ocl.pkg1, "Sequence{1,4,9}", "Sequence{1..3}->collect(k | k*k)");
		ocl.assertQueryResults(ocl.pkg1, "Sequence{null, null, null}", "Sequence{1..3}->collect(k | null)");
		ocl.assertQueryResults(null, "Sequence{11,15,21,29,39,51}", "Sequence{10..15}->collect(v with i | v+i*i)");

		ocl.dispose();
	}

	/**
	 * Tests that the collect() iterator correctly deals with empty collections.
	 */
	@Test public void test_collect_empty_217461() {
		MyOCL ocl = createOCL();
		String self = "foo";
		List<String> expected = Collections.emptyList();

		ocl.assertQueryEquals(self, expected, "let c : Sequence(OrderedSet(String)) = Sequence{} in c->collect(s : OrderedSet(String) | s.toUpperCase())");
		ocl.dispose();
	}

	/**
	 * Tests that the collect() iterator correctly flattens its result.
	 */
	@Test public void test_collect_flattens_217461() {
		MyOCL ocl = createOCL();
		IdResolver idResolver = ocl.getIdResolver();
		String self = "foo";
		CollectionTypeId typeId = TypeId.SEQUENCE.getSpecializedId(TypeId.STRING);
		SequenceValue expected = idResolver.createSequenceOfEach(typeId, "THIS AND", "THAT", "THE OTHER");

		ocl.assertQueryEquals(self, expected, "Sequence{Sequence{'this and', 'that'}, Sequence{'the other'}}->collect(s : Sequence(String) | s.toUpperCase())");
		ocl.dispose();
	}

	/**
	 * Tests that parsing fails, in the case of an unknown property in a
	 * collection navigation, with an appropriate parse failure, not a
	 * <code>ClassCastException</code>.
	 */
	@Test public void test_collect_implicit_unknownAttribute_232669() {
		MyOCL ocl = createOCL();
		EnvironmentFactoryInternalExtension environmentFactory = (EnvironmentFactoryInternalExtension) ocl.getEnvironmentFactory();
		ocl.assertBadInvariant(SemanticException.class, Diagnostic.ERROR,
			environmentFactory.getASClass("Package"), "ownedPackages.unknownAttribute",
			PivotMessagesInternal.UnresolvedProperty_ERROR_, "Package", "unknownAttribute");
		ocl.dispose();
	}

	/**
	 * Tests that parsing fails, in the case of an unknown operation in a
	 * collection navigation, with an appropriate parse failure, not a
	 * <code>ClassCastException</code>.
	 */
	@Test public void test_collect_implicit_unknownOperation_232669() {
		MyOCL ocl = createOCL();
		EnvironmentFactoryInternalExtension environmentFactory = (EnvironmentFactoryInternalExtension) ocl.getEnvironmentFactory();
		ocl.assertBadInvariant(SemanticException.class, Diagnostic.ERROR,
			environmentFactory.getASClass("Package"), "ownedPackages.unknownOperation(self)",
			PivotMessagesInternal.UnresolvedOperationCall_ERROR_, "Package", "unknownOperation", PivotConstants.SELF_NAME);
		ocl.dispose();
	}

	/**
	 * Tests that when the body of an iterator results in invalid, the entire
	 * iterator expression's value is invalid.
	 */
	@Test public void test_collect_invalidBody_142518() {
		MyOCL ocl = createOCL();
		IdResolver idResolver = ocl.getIdResolver();
		ocl.assertQueryInvalid(EcorePackage.eINSTANCE,
				"Bag{1, 2, 3}->collect(Sequence{}->first())");

		// in the case of a null value, null is allowed in a collection, so
		// it does not result in invalid
		CollectionTypeId typeId = TypeId.BAG.getSpecializedId(TypeId.OCL_ANY);
		BagValue expected = idResolver.createBagOfEach(typeId, null, null, null);
		ocl.assertQueryEquals(EcorePackage.eINSTANCE, expected,
				"let b:Boolean = null in Bag{1, 2, 3}->collect(null)");
		ocl.dispose();
	}

	/**
	 * Tests the collectBy() iterator.
	 */
	@Test public void test_collectBy() {
		TestOCL ocl = createOCL();
		IdResolver idResolver = ocl.getIdResolver();
		CollectionTypeId typeId = TypeId.BAG.getSpecializedId(TypeId.INTEGER);
		Map<Object,Object> map = new HashMap<>();
		for (int i = 1; i <= 5; i++) {
			map.put(i,  i*i);
		}
		MapTypeId mapTypeId = TypeId.MAP.getSpecializedId(TypeId.INTEGER, TypeId.INTEGER, false, false);
		MapValue expected1 = idResolver.createMapOfAll(mapTypeId, map);
		CollectionValue expected1k = idResolver.createSetOfAll(typeId, map.keySet());
		CollectionValue expected1v = idResolver.createBagOfAll(typeId, map.values());

		// complete form
		ocl.assertQueryEquals(null, expected1, "Sequence{1..5}->collectBy(i : Integer | i*i)");
		ocl.assertQueryEquals(null, expected1k, "Sequence{1..5}->collectBy(i : Integer | i*i)->keys()");
		ocl.assertQueryEquals(null, expected1v, "Sequence{1..5}->collectBy(i : Integer | i*i)->values()");

		// shorter form
		ocl.assertQueryEquals(null, expected1, "Sequence{1..5}->collectBy(i | i*i)");

		// yet shorter form
		ocl.assertQueryResults(null, "Map{9 with Sequence{1, 4, 9, 16, 25, 16, 16}, 10 with Sequence{1, 4, 9, 16, 25, 16, 16}}", "Sequence{9,10,9}->collectBy(Sequence{1, 4, 9, 16, 25, 16, 16})");

		// shortest form
		ocl.assertQueryResults(null, "Map{1 with '1', 2 with '2', 3 with '3', 4 with '4', 5 with '5', 99 with '99' }", "Sequence{1..5,99}->collectBy(toString())");

		ocl.assertQueryResults(null, "Sequence{1..3}->collectBy(k | k*k*k)", "Sequence{1..3}->collectBy(i | i*i)->collectBy(k with v | k*v)");
		ocl.dispose();
	}

	/**
	 * Tests the collectNested() iterator.
	 */
	@Test public void test_collectNested() {
		MyOCL ocl = createOCL();
		EnvironmentFactoryInternalExtension environmentFactory = (EnvironmentFactoryInternalExtension) ocl.getEnvironmentFactory();
		IdResolver idResolver = ocl.getIdResolver();
		@NonNull Type packageType = ClassUtil.nonNullState(environmentFactory.getASClass("Package"));
		CollectionTypeId typeId = TypeId.BAG.getSpecializedId(packageType.getTypeId());
		CollectionValue expected1 = idResolver.createBagOfEach(typeId, "pkg2", "bob", "pkg3");

		// complete form
		ocl.assertQueryEquals(ocl.pkg1, expected1, "ownedPackages?->collectNested(p : ocl::Package | p.name)");

		// shorter form
		ocl.assertQueryEquals(ocl.pkg1, expected1, "ownedPackages?->collectNested(p | p.name)");

		// shortest form
		ocl.assertQueryEquals(ocl.pkg1, expected1, "ownedPackages?->collectNested(name)");

		// nested collections not flattened
		Set<org.eclipse.ocl.pivot.Package> e1 = Collections.singleton(ocl.jim);
		Set<?> e2 = Collections.EMPTY_SET;
		HashSet<Object> e3 = new HashSet<Object>(Arrays.asList(new Object[] {ocl.pkg4, ocl.pkg5}));
		CollectionValue expected2 = idResolver.createBagOfEach(typeId, e1, e2, e3);

		ocl.assertQueryEquals(ocl.pkg1, expected2, "ownedPackages?->collectNested(ownedPackages)");
		// Bug 423489 - ensure return is collection of body type not source type
		ocl.assertQueryResults(ocl.pkg1, "Sequence{1,2}", "let s:Sequence(OclAny) = Sequence{'a','bb'} in s->collectNested(oclAsType(String)).size()");
		ocl.assertQueryResults(ocl.pkg1, "Sequence{Sequence{1,2},Sequence{3,4}}", "let s:Sequence(Sequence(OclAny)) = Sequence{Sequence{'a','bb'},Sequence{'ccc','dddd'}} in s->collectNested(oclAsType(Sequence(String)))->collectNested(s | s.size())");
		// Bug 423490 - ensure nested iteration uses iterator as implicit source
		ocl.assertQueryResults(ocl.pkg1, "Sequence{2,1}", "let s:Sequence(Sequence(OclAny)) = Sequence{Sequence{'a','bb'},Sequence{'ccc'}} in s->collectNested(size())");
		ocl.dispose();
	}

	/**
	 * Tests that when the body of an iterator results in invalid, the entire
	 * iterator expression's value is invalid.
	 */
	@Test public void test_collectNested_invalidBody_142518() {
		MyOCL ocl = createOCL();
		IdResolver idResolver = ocl.getIdResolver();
		ocl.assertQueryInvalid(EcorePackage.eINSTANCE,
				"Bag{1, 2, 3}->collectNested(Sequence{}->first())");

		// in the case of a null value, null is allowed in a collection, so
		// it does not result in invalid
		Set<BigInteger> e1 = Collections.singleton(BigInteger.valueOf(1));
		Object e2 = null;
		Set<BigInteger> e3 = Collections.singleton(BigInteger.valueOf(3));
		CollectionTypeId typeId = TypeId.BAG.getSpecializedId(TypeId.INTEGER);
		BagValue expected = idResolver.createBagOfEach(typeId, e1, e2, e3);
		ocl.assertQueryEquals(EcorePackage.eINSTANCE, expected,
				"let b:Boolean = null in Bag{1, 2, 3}->collectNested(e | if e = 2 then null else Set{e} endif)");
		ocl.dispose();
	}

	/**
	 * Tests the exists() iterator.
	 */
	@Test public void test_exists() {
		MyOCL ocl = createOCL();
		ocl.assertQueryFalse(null, "Sequence{Sequence{false}, Sequence{false}, Sequence{false}, Sequence{false}}->exists(e | e->first())");
		ocl.assertQueryTrue(null, "Sequence{Sequence{false}, Sequence{true}, Sequence{false}, Sequence{false}}->exists(e | e->first())");
		ocl.assertQueryTrue(null, "Sequence{Sequence{false}, Sequence{true}, Sequence{null}, Sequence{true}}->exists(e | e->first())");
		ocl.assertQueryNull(null, "Sequence{Sequence{false}, Sequence{false}, Sequence{null}, Sequence{false}}->exists(e | e->first())");
		ocl.assertQueryTrue(null, "Sequence{Sequence{false}, Sequence{true}, Sequence{null}, Sequence{}}->exists(e | e->first())");
		ocl.assertQueryInvalid(null, "Sequence{Sequence{false}, Sequence{false}, Sequence{null}, Sequence{}}->exists(e | e->first())");
		ocl.assertQueryInvalid(null, "Sequence{Sequence{false}, Sequence{false}, Sequence{}, Sequence{null}}->exists(e | e->first())");
		ocl.assertQueryInvalid(null, "Sequence{Sequence{false}, Sequence{false}, Sequence{false}, Sequence{}}->exists(e | e->first())");

		ocl.assertQueryTrue(ocl.pkg1, "Sequence{'a', 'b', 'c', 'd', 'e'}->exists(e | e = 'c')");

		ocl.assertQueryTrue(ocl.pkg1, "Sequence{'a', 'b', 'c', 'c', 'e'}->exists(e | e = 'c')");

		ocl.assertQueryFalse(ocl.pkg1, "Sequence{'a', 'b', 'd', 'e'}->exists(e | e = 'c')");

		// when there are no values, they the desired result implictly
		// does not occur
		ocl.assertQueryFalse(ocl.pkg1, "Sequence{}->exists(e | e = 'c')");

		ocl.assertQueryTrue(ocl.pkg1, "ownedPackages->exists(true)");

		ocl.assertQueryTrue(null, "Map{1 with '1', true with 'TRUE', false with 'FALSE'}->exists(key with value | key <> value)");
		ocl.assertQueryTrue(null, "Map{1 with '1', true with 'TRUE', false with 'FALSE'}->exists(key | key <> null)");
		ocl.assertQueryTrue(null, "Map{1 with '1', true with 'TRUE', null with null}->exists(key | key <> null)");
		ocl.assertQueryTrue(null, "Map{1 with '1', true with 'TRUE', null with null}->exists(key  with value| key = value)");
		ocl.assertQueryFalse(null, "Map{1 with '1', true with 'TRUE', null with 'null'}->exists(key  with value| key = value)");
		ocl.assertQueryFalse(null, "Map{1 with '1', true with 'TRUE', 'null' with null}->exists(key  with value| key = value)");
		ocl.assertQueryInvalid(null, "Map{1 with '1', true with 'TRUE', invalid with null}->exists(key  with value| key = value)");
		ocl.assertQueryInvalid(null, "Map{1 with '1', true with 'TRUE', null with invalid}->exists(key  with value| key = value)");
		ocl.assertQueryInvalid(null, "Map{1 with '1', true with 'TRUE', 'null' with null}->exists(key  with value| key = invalid)");
		ocl.assertQueryTrue(null, "Map{1 with '1', true with 'TRUE', false with 'FALSE'}->exists(key : OclAny | key <> null)");
		ocl.assertQueryTrue(null, "Map{1 with '1', true with 'TRUE', false with 'FALSE'}->exists(key1, key2 with value2 | key1 <> value2)");
		ocl.assertQueryTrue(null, "Map{1 with '1', true with 'TRUE', false with 'FALSE'}->exists(key1 : OclAny, key2 : OclAny with value2 : String | key1 <> value2)");
		ocl.assertQueryTrue(null, "Map{1 with '1', true with 'TRUE', false with 'FALSE'}->exists(key1 with value1, key2 | key2 <> value1)");
		ocl.assertQueryTrue(null, "Map{1 with '1', true with 'TRUE', false with 'FALSE'}->exists(key with value | key.toString().toUpper() = value)");
		ocl.assertQueryTrue(null, "Map{1 with '1', true with 'TRUE', false with 'FALSE'}->exists(key : OclAny with value : String | key.toString().toUpper() = value)");

		ocl.assertQueryFalse(ocl.pkg1, "Sequence{2,3,4,6,12}->exists(e1, e2, e3 | e1 * e2 * e3 = 73)");
		ocl.assertQueryTrue(ocl.pkg1, "Sequence{2,3,4,6,12}->exists(e1, e2, e3 | e1 * e2 * e3 = 72)");

		ocl.assertQueryTrue(null, "Map{2 with 1, 3 with 2,1 with 3}->exists(k1 with v1, k2 with v2, k3 with v3 | k1 = v2 and k2 = v3 and k3 = v1)");
		ocl.assertQueryFalse(null, "Map{2 with 1, 4 with 2,1 with 3}->exists(k1 with v1, k2 with v2, k3 with v3 | k1 = v2 and k2 = v3 and k3 = v1)");
		ocl.dispose();
	}

	/**
	 * Tests that when the body of an iterator results in invalid, the entire
	 * iterator expression's value is invalid.
	 */
	@Test public void test_exists_invalidBody_142518() {
		MyOCL ocl = createOCL();
		ocl.assertQueryInvalid(null, "Bag{1, 2, 3}->exists('true')");		// Bug 415669
		ocl.assertQueryInvalid(null, "Bag{1, 2, 3}->exists(2)");			// Bug 415669

		ocl.assertQueryNull(EcorePackage.eINSTANCE,
				"let b:Boolean = null in Bag{1, 2, 3}->exists(b and b)");

		// same deal for a null value (in the exists case)
		ocl.assertQueryNull(EcorePackage.eINSTANCE,
				"Bag{1, 2, 3}->exists(null)");
		ocl.assertQueryInvalid(EcorePackage.eINSTANCE,
				"Bag{1, 2, 3}->exists(Sequence{}->first())");
		ocl.dispose();
	}

	/**
	 * Tests the exists iterator with multiple iterator variables.
	 */
	@Test public void test_exists_multipleIteratorVariables() {
		MyOCL ocl = createOCL();
		ocl.assertInvariantTrue(ocl.pkg1, "Sequence{1, 2, 3, 4}->exists(e1, e2 | e1 = e2)");
		ocl.assertInvariantTrue(ocl.pkg1, "Sequence{1, 2, 3, 4}->exists(e1, e2 | (e1 + e2) = 7)");
		ocl.assertInvariantFalse(ocl.pkg1, "Sequence{1, 2, 3, 4}->exists(e1, e2 | (e1 + e2) = 0)");
		ocl.assertInvariantTrue(ocl.pkg1, "Sequence{1, 2, 3, 4}->exists(e1, e2, e3 | (e1 + e2 + e3) > 2)");

		// when there are no values, the the desired result implictly
		// does not occur
		ocl.assertInvariantFalse(ocl.pkg1, "Sequence{}->exists(e1, e2 | e1 = e2)");

		ocl.assertQueryTrue(null, "Sequence{'1','2','3','4'}->exists(v1 with i1, v2 with i2 | v1 = i1.toString() and v2.toInteger() = i2)");
		ocl.assertQueryTrue(null, "OrderedSet{'1','2','3','4'}->exists(v1 : String[1] with i1 : Integer[1], v2 : String[1] with i2 : Integer[1] | v1 = i1.toString() and v2.toInteger() = i2)");
		ocl.dispose();
	}

	/**
	 * Tests that the exists() iterator return invalid when the source
	 * collection is null or invalid.
	 */
	@Test public void test_existsWithNullSource_143996() {
		MyOCL ocl = createOCL();
		ocl.assertQueryInvalid(ocl.pkg1,
			"let e : Collection(ocl::Package) = null in e->exists(" +
					"p : ocl::Package | p.name = 'bob')", StringUtil.bind(PivotMessages.TypedValueRequired, TypeId.ITERABLE_NAME, ValueUtil.getTypeName(null)), InvalidValueException.class);

		ocl.assertQueryInvalid(ocl.pkg1,
			"let e : Collection(ocl::Package) = invalid in e->exists(" +
					"p : ocl::Package | p.name = 'bob')", PivotMessages.InvalidLiteral, InvalidValueException.class);
		ocl.dispose();
	}

	/**
	 * Tests the forAll() iterator.
	 */
	@Test public void test_forAll() {
		MyOCL ocl = createOCL();
		ocl.assertQueryTrue(null, "Sequence{Sequence{true}, Sequence{true}, Sequence{true}, Sequence{true}}->forAll(e | e->first())");
		ocl.assertQueryFalse(null, "Sequence{Sequence{true}, Sequence{false}, Sequence{true}, Sequence{true}}->forAll(e | e->first())");
		ocl.assertQueryFalse(null, "Sequence{Sequence{true}, Sequence{false}, Sequence{null}, Sequence{false}}->forAll(e | e->first())");
		ocl.assertQueryNull(null, "Sequence{Sequence{true}, Sequence{true}, Sequence{null}, Sequence{true}}->forAll(e | e->first())");
		ocl.assertQueryFalse(null, "Sequence{Sequence{true}, Sequence{false}, Sequence{null}, Sequence{}}->forAll(e | e->first())");
		ocl.assertQueryInvalid(null, "Sequence{Sequence{true}, Sequence{true}, Sequence{null}, Sequence{}}->forAll(e | e->first())");
		ocl.assertQueryInvalid(null, "Sequence{Sequence{true}, Sequence{true}, Sequence{}, Sequence{null}}->forAll(e | e->first())");
		ocl.assertQueryInvalid(null, "Sequence{Sequence{true}, Sequence{true}, Sequence{true}, Sequence{}}->forAll(e | e->first())");

		ocl.assertQueryFalse(ocl.pkg1, "Sequence{'a', 'b', 'c', 'd', 'e'}->forAll(e | e = 'c')");

		ocl.assertQueryFalse(ocl.pkg1, "Sequence{'a', 'b', 'd', 'e'}->forAll(e | e = 'c')");

		ocl.assertQueryTrue(ocl.pkg1, "Sequence{'c', 'c', 'c', 'c'}->forAll(e | e = 'c')");

		ocl.assertQueryTrue(ocl.pkg1, "Sequence{'c'}->forAll(e | e = 'c')");

		// when there are no values, they implicitly all evaluate to the
		// desired result
		ocl.assertQueryTrue(ocl.pkg1, "Sequence{}->forAll(e | e = 'c')");

		ocl.assertQueryTrue(ocl.pkg1, "ownedPackages->forAll(true)");
		//
		ocl.assertQueryTrue(ocl.pkg1, "Sequence{1..0}->forAll(false)");
		ocl.assertQueryFalse(ocl.pkg1, "Sequence{1..1}->forAll(false)");

		ocl.assertQueryTrue(null, "Map{1 with '1', true with 'TRUE', false with 'FALSE'}->forAll(key with value | key <> value)");
		ocl.assertQueryTrue(null, "Map{1 with '1', true with 'TRUE', false with 'FALSE'}->forAll(key | key <> null)");
		ocl.assertQueryTrue(null, "Map{1 with '1', true with 'TRUE', false with 'FALSE'}->forAll(key : OclAny | key <> null)");
		ocl.assertQueryTrue(null, "Map{1 with '1', true with 'TRUE', false with 'FALSE'}->forAll(key1, key2 with value2 | key1 <> value2)");
		ocl.assertQueryTrue(null, "Map{1 with '1', true with 'TRUE', false with 'FALSE'}->forAll(key1 : OclAny, key2 : OclAny with value2 : String | key1 <> value2)");
		ocl.assertQueryTrue(null, "Map{1 with '1', true with 'TRUE', false with 'FALSE'}->forAll(key1 with value1, key2 | key2 <> value1)");
		ocl.assertQueryTrue(null, "Map{1 with '1', true with 'TRUE', false with 'FALSE'}->forAll(key with value | key.toString().toUpper() = value)");
		ocl.assertQueryTrue(null, "Map{1 with '1', true with 'TRUE', false with 'FALSE'}->forAll(key : OclAny with value : String | key.toString().toUpper() = value)");

		ocl.assertQueryTrue(ocl.pkg1, "Sequence{2,3,4,6,12}->forAll(e1, e2, e3 | e1 * e2 * e3 <> 73)");
		ocl.assertQueryFalse(ocl.pkg1, "Sequence{2,3,4,6,12}->forAll(e1, e2, e3 | e1 * e2 * e3 <> 72)");

		ocl.assertQueryTrue(null, "Sequence{11..15}->forAll(i1 with x1, i2 with x2 | i1+x2-11 = i2+x1-11)");
		ocl.assertQueryTrue(null, "Sequence{11..15}->forAll(i1, i2, i3 | 1+i1+i2+i3 = i1+i2+i3+1)");
		ocl.assertQueryTrue(null, "OrderedSet{11..15}->forAll(i1, i2 with x2, i3 | i1+x2+i3 = i1+i2+i3-10)");
		ocl.assertQueryTrue(null, "OrderedSet{11..15}->forAll(i1 with x1, i2 with x2, i3 with x3 | i1+x2+i3 = x1+i2+x3+10)");
		ocl.assertSemanticErrorQuery(null, "Set{11..15}->forAll(i1, i2 with x2, i3 | i1+x2+i3 = i1+i2+i3-10)", PivotMessages.IllegalCoIterator, "Set(Integer)");
		ocl.assertSemanticErrorQuery(null, "Bag{11..15}->forAll(i1 with x1 | i1 = x1)", PivotMessages.IllegalCoIterator, "Bag(Integer)");

		ocl.assertQueryFalse(null, "Map{2 with 1, 3 with 2,1 with 3}->forAll(k1 with v1, k2 with v2, k3 with v3 | not (k1 = v2 and k2 = v3 and k3 = v1))");
		ocl.assertQueryTrue(null, "Map{2 with 1, 4 with 2,1 with 3}->forAll(k1 with v1, k2 with v2, k3 with v3 | not (k1 = v2 and k2 = v3 and k3 = v1))");
		ocl.dispose();
	}

	/**
	 * Tests that when the body of an iterator results in invalid, the entire
	 * iterator expression's value is invalid.
	 */
	@Test public void test_forAll_invalidBody_142518() {
		MyOCL ocl = createOCL();
		ocl.assertQueryInvalid(null, "Bag{1, 2, 3}->forAll('true')");		// Bug 415669
		ocl.assertQueryInvalid(null, "Bag{1, 2, 3}->forAll(2)");			// Bug 415669

		ocl.assertQueryNull(EcorePackage.eINSTANCE,
				"let b:Boolean = null in Bag{1, 2, 3}->forAll(b and b)");

		// check that the "check" API interprets invalid as a constraint
		// violation
		ocl.assertInvariantFalse(EcorePackage.eINSTANCE,
				"let b:Boolean = null in Bag{1}->forAll(b and b)");

		// same deal for a null value (in the forAll case)
		ocl.assertQueryNull(EcorePackage.eINSTANCE,
				"Bag{1, 2, 3}->forAll(null)");
		ocl.assertQueryInvalid(EcorePackage.eINSTANCE,
				"Bag{1, 2, 3}->forAll(Sequence{}->first())");
		ocl.dispose();
	}

	/**
	 * Tests the forAll iterator with multiple iterator variables.
	 */
	@Test public void test_forAll_multipleIteratorVariables() {
		MyOCL ocl = createOCL();
		ocl.assertInvariantFalse(ocl.pkg1, "Sequence{1, 2, 3, 4}->forAll(e1, e2 | e1 = e2)");
		ocl.assertInvariantTrue(ocl.pkg1, "Sequence{1, 2, 3, 4}->forAll(e1, e2 | (e1 + e2) > e1)");
		ocl.assertInvariantTrue(ocl.pkg1, "Sequence{1, 2, 3, 4}->forAll(e1, e2, e3 | (e1 + e2 + e3) > e1)");

		// when there are no values, the the desired result implictly occurs
		ocl.assertInvariantTrue(ocl.pkg1, "Sequence{}->forAll(e1, e2 | e1 = e2)");

		ocl.assertQueryTrue(null, "Sequence{'1','2','3','4'}->forAll(v1 with i1, v2 with i2 | v1 = i1.toString() and v2.toInteger() = i2)");
		ocl.assertQueryTrue(null, "OrderedSet{'1','2','3','4'}->forAll(v1 : String[1] with i1 : Integer[1], v2 : String[1] with i2 : Integer[1] | v1 = i1.toString() and v2.toInteger() = i2)");
		ocl.dispose();
	}

	/**
	 * Tests that when the body of an iterator results in invalid, the
	 * isUnique iterator expression treats it like any other value.
	 */
	@Test public void test_isUnique_invalidBody_142518() {
		MyOCL ocl = createOCL();
		ocl.assertQueryInvalid(EcorePackage.eINSTANCE,
				"Bag{1, 2, 3}->isUnique(Sequence{}->first())");

		ocl.assertQueryFalse(EcorePackage.eINSTANCE,
				"let b:Boolean = null in Bag{1, 2, 3}->isUnique(null)");
		ocl.dispose();
	}

	/**
	 * Tests the isUnique() iterator.
	 */
	@Test public void test_isUnique_126861() {
		MyOCL ocl = createOCL();
		//    	Abstract2Moniker.TRACE_MONIKERS.setState(true);
		ocl.assertQueryTrue(ocl.pkg1, "Sequence{'a', 'b', 'c', 'd', 'e'}->isUnique(e | e)");

		ocl.assertQueryFalse(ocl.pkg1, "Sequence{'a', 'b', 'c', 'c', 'e'}->isUnique(e | e)");

		// when there are no values, they implicitly all evaluate to a
		// different result
		ocl.assertQueryTrue(ocl.pkg1, "Sequence{}->isUnique(e | e)");
		ocl.assertQueryTrue(ocl.pkg1, "Sequence{null}->isUnique(e | e)");
		ocl.assertQueryTrue(ocl.pkg1, "Sequence{null,1}->isUnique(e | e)");
		ocl.assertQueryFalse(ocl.pkg1, "Sequence{null,null}->isUnique(e | e)");

		ocl.assertQueryTrue(ocl.pkg1, "ownedPackages?->isUnique(name)");

		ocl.assertQueryFalse(null, "Map{2 with 1, 1 with 2}->isUnique(key with value | key * value)");
		ocl.assertQueryTrue(null, "Map{2 with 2, 1 with 2}->isUnique(key with value | key * value)");
		ocl.dispose();
	}

	/**
	 * Tests the generic iterate() iterator.
	 */
	@Test public void test_iterate() {
		MyOCL ocl = createOCL();
		IdResolver idResolver = ocl.getIdResolver();
		CollectionTypeId typeId = TypeId.SET.getSpecializedId(TypeId.STRING);
		SetValue expected = idResolver.createSetOfEach(typeId, "pkg2", "bob", "pkg3");

		// complete form
		ocl.assertQueryEquals(ocl.pkg1, expected, "ownedPackages?->iterate(p; s : Set(String) = Set{} | s->including(p.name))");

		// shorter forms
		ocl.assertQueryEquals(ocl.pkg1, expected, "ownedPackages?->iterate(p; s : Set(String) = Set{} | s->including(p.name))");
		ocl.assertQueryEquals(ocl.pkg1, expected, "ownedPackages?->iterate(p; s = Set(String){} | s->including(p.name))");

		// shortest forms
		ocl.assertQueryEquals(ocl.pkg1, expected, "ownedPackages?->iterate(s : Set(String) = Set{} | s->including(name))");
		ocl.assertQueryEquals(ocl.pkg1, expected, "ownedPackages?->iterate(s = Set(String){} | s->including(name))");

		ocl.assertQueryEquals(ocl.pkg1, "pfx_a_b_c", "Sequence{'a','b','c'}->iterate(e : String; s : String = 'pfx' | s + '_' + e)");

		ocl.assertQueryResults(null, "Sequence{1..3}->collectBy(k | k*k)", "Sequence{1..3}->iterate(j; acc = Map(Integer,Integer){} | acc->including(j, j*j))");
		ocl.assertQueryResults(null, "Sequence{1..3}->collectBy(k | k*k)", "Sequence{1..3}->collectBy(i | i*i)->iterate(j with v; acc = Map(Integer,Integer){} | acc->including(j, v))");
		ocl.dispose();
	}

	/**
	 * Tests some bad separators in iterate() expressions.
	 */
	@Test public void test_iterate_534626() {
		MyOCL ocl = createOCL();
		ocl.assertSemanticErrorQuery(null, "Sequence{'a','b','c'}->iterate(ch, acc:String ='' , acc+ch)",
			EssentialOCLCS2ASMessages.IterateExp_TooFewAccumulators, "iterate");
		ocl.assertSemanticErrorQuery(null, "Sequence{'a','b','c'}->iterate(ch | acc1+ch)",
			EssentialOCLCS2ASMessages.IterateExp_TooFewAccumulators, "iterate");
		ocl.assertSemanticErrorQuery(null, "Sequence{'a','b','c'}->iterate(ch; acc1:String ='', acc2:String ='' | acc1+ch)",
			EssentialOCLCS2ASMessages.IterateExp_TooManyAccumulators, "iterate");
		ocl.assertSemanticErrorQuery(null, "Sequence{'a','b','c'}->iterate(ch; acc1:String =''; acc2:String ='' | acc1+ch)",
			EssentialOCLCS2ASMessages.IterateExp_TooManyAccumulators, "iterate");
		ocl.assertSemanticWarningQuery(null, "Sequence{'a','b','c'}->iterate(ch, acc:String ='' | acc+ch)",
			EssentialOCLCS2ASMessages.IterateExp_BadAccumulatorSeparator, ",");
		ocl.assertQueryEquals(null, "abc", "Sequence{'a','b','c'}->iterate(ch; acc:String ='' | acc+ch)");
		ocl.assertQueryEquals(null, "abc", "Sequence{'a','b','c'}->iterate(ch; acc ='' | acc+ch)");
		ocl.dispose();
	}

	/**
	 * Tests that the generic iterate() iterator returns invalid when the
	 * source collection is null or invalid.
	 */
	@Test public void test_iterateWithNullSource_143996() {
		MyOCL ocl = createOCL();
		ocl.assertQueryInvalid(ocl.pkg1,
			"let e : Collection(ocl::Package) = null in e->iterate(" +
					"p : ocl::Package; s : String = '' | s.concat(p.name))", StringUtil.bind(PivotMessages.TypedValueRequired, TypeId.ITERABLE_NAME, ValueUtil.getTypeName(null)), InvalidValueException.class);

		ocl.assertQueryInvalid(ocl.pkg1,
			"let e : Collection(ocl::Package) = invalid in e->iterate(" +
					"p : ocl::Package; s : String = '' | s.concat(p.name))", PivotMessages.InvalidLiteral, InvalidValueException.class);
		ocl.dispose();
	}

	/**
	 * Tests the one() iterator.
	 */
	@Test public void test_one() {
		MyOCL ocl = createOCL();
		ocl.assertQueryTrue(ocl.pkg1, "Sequence{'a', 'b', 'c', 'd', 'e'}->one(e | e = 'c')");

		ocl.assertQueryFalse(ocl.pkg1, "Sequence{'a', 'b', 'c', 'c', 'e'}->one(e | e = 'c')");

		ocl.assertQueryFalse(ocl.pkg1, "Sequence{'a', 'b', 'd', 'e'}->one(e | e = 'c')");

		ocl.assertQueryTrue(ocl.pkg1, "Sequence{'a'}->one(true)");

		ocl.assertQueryFalse(ocl.pkg1, "Map{}->one(k with v | k = v)");
		ocl.assertQueryTrue(ocl.pkg1, "Map{'a' with 'a', 'b' with 'c' }->one(k with v | k = v)");
		ocl.assertQueryTrue(ocl.pkg1, "Map{'a' with 'a', 'b' with 'c' }->one(k with v | k <> v)");
		ocl.assertQueryFalse(ocl.pkg1, "Map{'a' with 'a', 'b' with 'b' }->one(k with v | k <> v)");
		ocl.dispose();
	}

	/**
	 * Tests that when the body of an iterator results in invalid, the entire
	 * iterator expression's value is invalid.
	 */
	@Test public void test_one_invalidBody_142518() {
		MyOCL ocl = createOCL();
		ocl.assertQueryInvalid(null, "Bag{1, 2, 3}->one('true')");		// Bug 415669
		ocl.assertQueryInvalid(null, "Bag{1, 2, 3}->one(2)");			// Bug 415669

		ocl.assertQueryInvalid(EcorePackage.eINSTANCE,
				"let b:Boolean = null in Bag{1, 2, 3}->one(b and b)");

		// same deal for a null value (in the one case)
		ocl.assertQueryInvalid(EcorePackage.eINSTANCE,
				"Bag{1, 2, 3}->one(null.oclAsType(Boolean))");
		ocl.dispose();
	}

	/**
	 * Tests the reject() iterator.
	 */
	@Test public void test_reject() {
		MyOCL ocl = createOCL();
		EnvironmentFactoryInternalExtension environmentFactory = (EnvironmentFactoryInternalExtension) ocl.getEnvironmentFactory();
		IdResolver idResolver = ocl.getIdResolver();
		@NonNull Type packageType = ClassUtil.nonNullState(environmentFactory.getASClass("Package"));
		CollectionTypeId typeId = TypeId.SET.getSpecializedId(packageType.getTypeId());
		CollectionValue expected = idResolver.createSetOfEach(typeId, ocl.pkg2, ocl.pkg3);

		// complete form
		ocl.assertQueryEquals(ocl.pkg1, expected, "ownedPackages?->reject(p : ocl::Package | p.name = 'bob')");

		// shorter form
		ocl.assertQueryEquals(ocl.pkg1, expected, "ownedPackages?->reject(p | p.name = 'bob')");

		// shortest form
		ocl.assertQueryEquals(ocl.pkg1, expected, "ownedPackages?->reject(name = 'bob')");

		expected = idResolver.createSetOfEach(typeId);
		ocl.assertQueryEquals(ocl.pkg1, expected, "ownedPackages?->reject(true)");

		ocl.assertQueryResults(null, "Map{1 with 1, 3 with 9}", "Sequence{1..3}->collectBy(k | k*k)->reject(k with v | k = 2)");
		ocl.assertQueryResults(null, "Map{1 with 1, 3 with 9}", "Set{1..3}->collectBy(k | k*k)->reject(k with v | v = 4)");
		ocl.dispose();
	}

	/**
	 * Tests that when the body of an iterator results in invalid, the entire
	 * iterator expression's value is invalid.
	 */
	@Test public void test_reject_invalidBody_142518() {
		MyOCL ocl = createOCL();
		ocl.assertQueryInvalid(null, "Bag{1, 2, 3}->reject('true')");		// Bug 415669
		ocl.assertQueryInvalid(null, "Bag{1, 2, 3}->reject(2)");			// Bug 415669

		ocl.assertQueryInvalid(EcorePackage.eINSTANCE,
				"let b:Boolean = null in Bag{1, 2, 3}->reject(b and b)");

		// same deal for a null value (in the exists case)
		ocl.assertQueryInvalid(EcorePackage.eINSTANCE,
				"Bag{1, 2, 3}->reject(null.oclAsType(Boolean))");
		ocl.dispose();
	}

	/**
	 * Tests the select() iterator.
	 */
	@Test public void test_select() {
		MyOCL ocl = createOCL();
		EnvironmentFactoryInternalExtension environmentFactory = (EnvironmentFactoryInternalExtension) ocl.getEnvironmentFactory();
		IdResolver idResolver = ocl.getIdResolver();
		@NonNull Type packageType = ClassUtil.nonNullState(environmentFactory.getASClass("Package"));
		CollectionTypeId typeId = TypeId.SET.getSpecializedId(packageType.getTypeId());
		CollectionValue expected = idResolver.createSetOfEach(typeId, ocl.pkg2, ocl.pkg3);

		// complete form
		ocl.assertQueryEquals(ocl.pkg1, expected, "ownedPackages?->select(p : ocl::Package | p.name <> 'bob')");

		// shorter form
		ocl.assertQueryEquals(ocl.pkg1, expected, "ownedPackages?->select(p | p.name <> 'bob')");

		// shortest form
		ocl.assertQueryEquals(ocl.pkg1, expected, "ownedPackages?->select(name <> 'bob')");

		Value expected2 = idResolver.createSetOfEach(typeId, ocl.bob, ocl.pkg2, ocl.pkg3);
		ocl.assertQueryEquals(ocl.pkg1, expected2, "ownedPackages?->select(true)");

		ocl.assertQueryResults(null, "Map{2 with 4}", "Sequence{1..3}->collectBy(k | k*k)->select(k with v | k = 2)");
		ocl.assertQueryResults(null, "Map{2 with 4}", "Sequence{1..3}->collectBy(k | k*k)->select(k with v | v = 4)");


		ocl.dispose();
	}

	/**
	 * Tests that when the body of an iterator results in invalid, the entire
	 * iterator expression's value is invalid.
	 */
	@Test public void test_select_invalidBody_142518() {
		MyOCL ocl = createOCL();
		ocl.assertQueryInvalid(null, "Bag{1, 2, 3}->select('true')");		// Bug 415669
		ocl.assertQueryInvalid(null, "Bag{1, 2, 3}->select(2)");			// Bug 415669

		ocl.assertQueryInvalid(EcorePackage.eINSTANCE,
				"let b:Boolean = null in Bag{1, 2, 3}->select(b and b)");

		// same deal for a null value (in the exists case)
		ocl.assertQueryInvalid(EcorePackage.eINSTANCE,
				"Bag{1, 2, 3}->select(null.oclAsType(Boolean))");
		ocl.dispose();
	}

	/**
	 * Tests the sortedBy() iterator.
	 */
	@Test public void test_sortedBy() {
		MyOCL ocl = createOCL();
		EnvironmentFactoryInternalExtension environmentFactory = (EnvironmentFactoryInternalExtension) ocl.getEnvironmentFactory();
		IdResolver idResolver = ocl.getIdResolver();
		@NonNull Type packageType = ClassUtil.nonNullState(environmentFactory.getASClass("Package"));
		CollectionTypeId typeId = TypeId.ORDERED_SET.getSpecializedId(packageType.getTypeId());
		OrderedSetValue expectedSet = idResolver.createOrderedSetOfEach(typeId, ocl.bob, ocl.pkg2, ocl.pkg3);

		// complete form
		ocl.assertQueryEquals(ocl.pkg1, expectedSet, "ownedPackages?->sortedBy(p : ocl::Package | p.name)");

		// shorter form
		ocl.assertQueryEquals(ocl.pkg1, expectedSet, "ownedPackages?->sortedBy(p | p.name)");

		// shortest form
		ocl.assertQueryEquals(ocl.pkg1, expectedSet, "ownedPackages?->sortedBy(name)");

		CollectionTypeId stringsTypeId = TypeId.SEQUENCE.getSpecializedId(TypeId.STRING);
		SequenceValue expected = idResolver.createSequenceOfEach(stringsTypeId, "a", "b", "c", "d", "e");
		ocl.assertQueryEquals(ocl.pkg1, expected, "Bag{'d', 'b', 'e', 'a', 'c'}->sortedBy(e | e)");
		ocl.assertQueryResults(null, "Sequence{'x', 'aa', 'zzz', 'zzz', 'zzz', 'yyyy', 'yyyy'}", "Bag{'x', 'yyyy', 'zzz', 'aa', 'zzz', 'yyyy', 'zzz'}->sortedBy(size())");
		ocl.dispose();
	}

	/**
	 * Tests that when the body of an iterator results in invalid, the entire
	 * iterator expression's value is invalid.
	 */
	@Test public void test_sortedBy_invalidBody_142518() {
		MyOCL ocl = createOCL();
		ocl.assertQueryInvalid(EcorePackage.eINSTANCE,
			"let s : String = null in Bag{1, 2, 3}->sortedBy(s.size())", StringUtil.bind(PivotMessages.TypedValueRequired, TypeId.STRING_NAME, ValueUtil.getTypeName(null)), InvalidValueException.class);

		// same deal for null values
		ocl.assertQueryInvalid(EcorePackage.eINSTANCE,
			"Bag{1, 2, 3}->sortedBy(null)", StringUtil.bind(PivotMessages.UndefinedBody, "sortedBy"), InvalidValueException.class);
		ocl.dispose();
	}

	/**
	 * Test to check the validation of the <tt>sortedBy</tt> iterator, that
	 * the body expression type has a <tt>&lt;</tt> operation.
	 */
	@Test public void test_sortedByRequiresComparability_192729() {
		MyOCL ocl = createOCL();
		EnvironmentFactoryInternalExtension environmentFactory = (EnvironmentFactoryInternalExtension) ocl.getEnvironmentFactory();
		org.eclipse.ocl.pivot.Class context = environmentFactory.getASClass("Package");
		org.eclipse.ocl.pivot.Class type = environmentFactory.getASClass("Class");
		ocl.assertValidationErrorQuery(context, "ownedClasses->sortedBy(e | e)",
			PivotMessagesInternal.UnresolvedOperation_ERROR_, type + "", LibraryConstants.COMPARE_TO);

		ocl.assertQuery(context, "ownedClasses->sortedBy(e | e.name)");
		ocl.loadEPackage("ecore", EcorePackage.eINSTANCE);

		// EDate defines an OclComparable::compareTo by having a java.lang.Comparable instance class
		ocl.assertQuery(context, "let dates : Sequence(ecore::EDate) = Sequence{} in dates->sortedBy(e | e)");
		// EInt defines OclComparable::compareTo by having a behavioral mapping to the Integer type
		ocl.assertQueryResults(context, "Sequence{1,7,9}", "let values : Sequence(ecore::EInt) = Sequence{1,9,7} in values->sortedBy(e | e)");
		ocl.dispose();
	}

	/**
	 * Tests the validation the number of iterator variables for iterators that
	 * do not support multiple variables.
	 */
	@Test public void test_invalidMultipleIteratorVariables() {
		MyOCL ocl = createOCL();
		ocl.assertBadQuery(SemanticException.class, Diagnostic.ERROR,		// FIXME Bug 296990
			null, "Sequence{'a', 'b', 'c'}->exists(e1, e2, e3, e4 | e1 = e2)",
			PivotMessagesInternal.UnresolvedIterationCall_ERROR_, "Sequence(String)", "exists", "e1, e2, e3, e4| e1 = e2");

		ocl.assertBadQuery(SemanticException.class, Diagnostic.ERROR,		// FIXME Bug 296990
			null, "Sequence{'a', 'b', 'c'}->forAll(e1, e2, e3, e4 | e1 = e2)",
			PivotMessagesInternal.UnresolvedIterationCall_ERROR_, "Sequence(String)", "forAll", "e1, e2, e3, e4| e1 = e2");

		ocl.assertBadQuery(SemanticException.class, Diagnostic.ERROR,
			null, "Sequence{'a', 'b', 'c'}->collect(e1, e2 | Tuple{a : String = e1, b : String = e2})",
			PivotMessagesInternal.UnresolvedIterationCall_ERROR_, "Sequence(String)", "collect", "e1, e2| Tuple{a : String = e1, b : String = e2}");

		ocl.assertBadQuery(SemanticException.class, Diagnostic.ERROR,
			null, "Sequence{'a', 'b', 'c'}->any(e1, e2 | e1 = e2)",
			PivotMessagesInternal.UnresolvedIterationCall_ERROR_, "Sequence(String)", "any", "e1, e2| e1 = e2");

		ocl.assertBadQuery(SemanticException.class, Diagnostic.ERROR,
			null, "Sequence{'a', 'b', 'c'}->one(e1, e2 | e1 = e2)",
			PivotMessagesInternal.UnresolvedIterationCall_ERROR_, "Sequence(String)", "one", "e1, e2| e1 = e2");

		ocl.assertBadQuery(SemanticException.class, Diagnostic.ERROR,
			null, "Sequence{'a', 'b', 'c'}->select(e1, e2 | e1 = e2)",
			PivotMessagesInternal.UnresolvedIterationCall_ERROR_, "Sequence(String)", "select", "e1, e2| e1 = e2");

		ocl.assertBadQuery(SemanticException.class, Diagnostic.ERROR,
			null, "Sequence{'a', 'b', 'c'}->reject(e1, e2 | e1 = e2)",
			PivotMessagesInternal.UnresolvedIterationCall_ERROR_, "Sequence(String)", "reject", "e1, e2| e1 = e2");

		ocl.assertBadQuery(SemanticException.class, Diagnostic.ERROR,
			null, "Sequence{'a', 'b', 'c'}->isUnique(e1, e2 | e1 = e2)",
			PivotMessagesInternal.UnresolvedIterationCall_ERROR_, "Sequence(String)", "isUnique", "e1, e2| e1 = e2");
		ocl.dispose();
	}
}
