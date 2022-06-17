/*******************************************************************************
 * Copyright (c) 2011, 2021 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   L.Goubet, E.D.Willink - Initial API and implementation
 *     E.D.Willink (CEA LIST) - Bug 388493, 399378
 *******************************************************************************/

package org.eclipse.ocl.examples.pivot.tests;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.CollectionItem;
import org.eclipse.ocl.pivot.CollectionLiteralExp;
import org.eclipse.ocl.pivot.Constraint;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.Model;
import org.eclipse.ocl.pivot.StandardLibrary;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.ids.IdResolver;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.manager.MetamodelManagerInternal;
import org.eclipse.ocl.pivot.internal.messages.PivotMessagesInternal;
import org.eclipse.ocl.pivot.messages.PivotMessages;
import org.eclipse.ocl.pivot.options.PivotValidationOptions;
import org.eclipse.ocl.pivot.uml.UMLStandaloneSetup;
import org.eclipse.ocl.pivot.uml.internal.es2as.UML2AS;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.MetamodelManager;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.OCL;
import org.eclipse.ocl.pivot.utilities.ParserException;
import org.eclipse.ocl.pivot.utilities.StringUtil;
import org.eclipse.ocl.pivot.values.InvalidValueException;
import org.eclipse.ocl.xtext.essentialocl.cs2as.EssentialOCLCS2ASMessages;
import org.eclipse.ocl.xtext.oclinecore.OCLinEcoreStandaloneSetup;
import org.eclipse.osgi.util.NLS;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/**
 * Tests for Name access.
 */
@SuppressWarnings("null")
@RunWith(value = Parameterized.class)
public class EvaluateNameVisibilityTest4 extends PivotFruitTestSuite
{
	@Parameters
	public static Collection<Object[]> data() {
		Object[][] data = new Object[][]{{false}, {true}};
		return Arrays.asList(data);
	}

	public EvaluateNameVisibilityTest4(boolean useCodeGen) {
		super(useCodeGen);
	}

	@Override
	protected @NonNull TestOCL createOCL() {
		return new TestOCL(getTestFileSystem(), getTestPackageName(), getName(), useCodeGen ? getProjectMap() : OCL.NO_PROJECTS, null);
	}

	protected @NonNull TestOCL createOCLWithProjectMap() {
		return new TestOCL(getTestFileSystem(), getTestPackageName(), getName(), getProjectMap(), null);
	}

	@Override
	protected @NonNull String getTestPackageName() {
		return "EvaluateNameVisibility";
	}

	@BeforeClass public static void resetCounter() throws Exception {
		PivotTestSuite.resetCounter();
	}

	@Override
	@Before public void setUp() throws Exception {
		UMLStandaloneSetup.init();
		super.setUp();
	}

	@Override
	@After public void tearDown() throws Exception {
		super.tearDown();
	}

	/**
	 * Tests the basic name accesses
	 */
	@Test public void test_bad_navigation() throws InvocationTargetException {
		TestOCL ocl = createOCL();
		StandardLibrary standardLibrary = ocl.getStandardLibrary();
		ocl.assertQueryEquals(standardLibrary.getPackage(), "Boolean", "let types = self.ownedClasses?->select(name = 'Boolean') in let type = if types->notEmpty() then types->any(true) else null endif in type?.name");
		ocl.assertQueryNull(standardLibrary.getPackage(), "let types = self.ownedClasses?->select(name = 'notAclass') in let type = if types->notEmpty() then types->any(true) else null endif in type?.name");
		ocl.assertSemanticErrorQuery(null, "let a : Type = null in a.Package", PivotMessagesInternal.UnresolvedProperty_ERROR_, "Type", "Package");
		ocl.assertQueryNull(null, "let a : Type = null in a?.isClass()");
		ocl.assertSemanticErrorQuery(null, "let a : Type = null in a.Package()", PivotMessagesInternal.UnresolvedOperation_ERROR_, "Type", "Package");
		ocl.assertSemanticErrorQuery(null, "let a : Set(Type) = null in a.Package", PivotMessagesInternal.UnresolvedProperty_ERROR_, "Type", "Package");
		ocl.assertSemanticErrorQuery(null, "let a : Set(Type) = null in a.Package()", PivotMessagesInternal.UnresolvedOperation_ERROR_, "Type", "Package");
		ocl.assertSemanticErrorQuery(null, "Type.Package", PivotMessagesInternal.UnresolvedProperty_ERROR_, "Type", "Package");
		ocl.assertSemanticErrorQuery(null, "Type.Package()", PivotMessagesInternal.UnresolvedOperationCall_ERROR_, "Type", "Package", "");
		ocl.assertSemanticErrorQuery(null, "Set(Type).Package", PivotMessagesInternal.UnresolvedProperty_ERROR_, "Set(Type)", "Package");
		ocl.assertSemanticErrorQuery(null, "Set(Type).Package()", PivotMessagesInternal.UnresolvedOperationCall_ERROR_, "Set(Type)", "Package", "");
		ocl.assertSemanticErrorQuery(null, "let a : Type = null in a->Package", PivotMessagesInternal.UnresolvedProperty_ERROR_, "Set(Type)", "Package");
		ocl.assertSemanticErrorQuery(null, "let a : Type = null in a->Package()", PivotMessagesInternal.UnresolvedOperation_ERROR_, "Set(Type)", "Package");
		ocl.assertSemanticErrorQuery(null, "let a : Set(Type) = null in a->Package", PivotMessagesInternal.UnresolvedProperty_ERROR_, "Set(Type)", "Package");
		ocl.assertSemanticErrorQuery(null, "let a : Set(Type) = null in a->Package()", PivotMessagesInternal.UnresolvedOperation_ERROR_, "Set(Type)", "Package");
		ocl.assertSemanticErrorQuery(null, "Type->Package", PivotMessagesInternal.UnresolvedProperty_ERROR_, "Set(Class)", "Package");
		ocl.assertSemanticErrorQuery(null, "Type->Package()", PivotMessagesInternal.UnresolvedOperation_ERROR_, "Set(Class)", "Package");
		ocl.assertSemanticErrorQuery(null, "Set(Type)->Package", PivotMessagesInternal.UnresolvedProperty_ERROR_, "Set(SetType)", "Package");
		ocl.assertSemanticErrorQuery(null, "Set(Type)->Package()", PivotMessagesInternal.UnresolvedOperation_ERROR_, "Set(SetType)", "Package");
		ocl.assertSemanticErrorQuery(null, "let a : Type = null in a.if", "no viable alternative following input ''if''");
		ocl.assertSemanticErrorQuery(null, "let a : Type = null in a->if", "no viable alternative following input ''if''");
		// oclAsSet()
		ocl.assertQueryEquals(standardLibrary.getPackage(), 0, "let types = self.ownedClasses?->select(name = 'notAclass') in let type = if types->notEmpty() then types->any(true) else null endif in type->size()");
		ocl.assertQueryEquals(standardLibrary.getPackage(), 0, "let types = self.ownedClasses?->select(name = 'notAclass') in let type = if types->notEmpty() then types->any(true) else null endif in type?->size()");
		ocl.assertQueryEquals(standardLibrary.getPackage(), 1, "let types = self.ownedClasses?->select(name = 'Boolean') in let type = if types->notEmpty() then types->any(true) else null endif in type->size()");
		ocl.assertQueryEquals(standardLibrary.getPackage(), 1, "let types = self.ownedClasses?->select(name = 'Boolean') in let type = if types->notEmpty() then types->any(true) else null endif in type?->size()");
		ocl.assertQueryEquals(standardLibrary.getPackage(), 1, "let types = self.ownedClasses?->select(name = 'Boolean') in let type = if types->notEmpty() then types->any(true) else null endif in type?->size()?->size()");
		ocl.dispose();
	}

	@Test public void test_implicit_source() {
		TestOCL ocl = createOCL();
		StandardLibrary standardLibrary = ocl.getStandardLibrary();
		if (!useCodeGen) {			// FIXME CG consistent boxing
			ocl.assertQueryTrue(standardLibrary.getPackage(), "ownedClasses?->select(name = 'Integer') = Set{Integer}");
			ocl.assertQueryTrue(standardLibrary.getPackage(), "let name : String = 'String' in ownedClasses?->select(name = 'Integer') = Set{Integer}");
			ocl.assertQueryTrue(-1, "let type : Class = oclType() in type.owningPackage?.ownedClasses?->select(name = type.name) = Set{Integer}");
		}
		ocl.assertQueryTrue(standardLibrary.getPackage(), "ownedPackages->select(oclIsKindOf(Integer))->isEmpty()");
		ocl.assertQueryTrue(standardLibrary.getPackage(), "ownedPackages->select(oclIsKindOf(Package))->isEmpty()");	// Fails unless implicit Package disambiguated away by argument type expectation
		ocl.dispose();
	}

	@Test public void test_safe_aggregate_navigation() {
		TestOCL ocl = createOCL();
		StandardLibrary standardLibrary = ocl.getStandardLibrary();
		ocl.assertQueryInvalid(standardLibrary.getPackage(), "ownedClasses->including(null)->select(name = 'Integer')", StringUtil.bind(PivotMessages.NullNavigation, "source", "NamedElement::name"), InvalidValueException.class);
		ocl.assertQueryResults(standardLibrary.getPackage(), "Set{Integer}", "ownedClasses?->select(name = 'Integer')");
		if (!useCodeGen) {		// FIXME boxing
			ocl.assertQueryTrue(standardLibrary.getPackage(), "ownedClasses?->select(name = 'Integer') = Set{Integer}");
		}
		ocl.assertQueryInvalid(standardLibrary.getPackage(), "ownedClasses->including(null)->select(name = 'Integer') = Set{Integer}", StringUtil.bind(PivotMessages.NullNavigation, "source", "NamedElement::name"), InvalidValueException.class);
		ocl.assertQueryResults(standardLibrary.getPackage(), "Set{Integer}", "ownedClasses->including(null)?->select(name = 'Integer')");
		ocl.assertQueryTrue(standardLibrary.getPackage(), "ownedClasses->including(null)?->select(name = 'Integer')?.name = Bag{'Integer'}");
		ocl.assertQueryResults(standardLibrary.getPackage(), "Bag{'Integer', null}", "ownedClasses?->select(name = 'Integer')->including(null)->collect(c | c?.name)");
		ocl.assertQueryInvalid(standardLibrary.getPackage(), "ownedClasses->including(null)->select(name = 'Integer').name = Bag{'Integer'}", StringUtil.bind(PivotMessages.NullNavigation, "source", "NamedElement::name"), InvalidValueException.class);
		ocl.dispose();
	}

	@Test public void test_safe_object_navigation() {
		TestOCL ocl = createOCL();
		StandardLibrary standardLibrary = ocl.getStandardLibrary();
		ocl.assertValidationErrorQuery(ocl.getContextType(standardLibrary.getPackage()), "let parent : OclElement[1] = oclContainer()?.oclAsType(OclElement) in parent", PivotMessages.ValidationConstraintIsNotSatisfied_ERROR_, "LetVariable::CompatibleNullityForInitializer", "parent : OclElement[1] = self.oclContainer()?.oclAsType(OclElement)");
		ocl.assertQueryEquals(standardLibrary.getPackage(), standardLibrary.getPackage().eContainer(), "let parent : OclElement[?] = oclContainer()?.oclAsType(OclElement) in parent");
		ocl.assertQueryNull(standardLibrary.getPackage(), "let grandparent : OclElement[?] = oclContainer()?.oclContainer()?.oclAsType(OclElement) in grandparent");
		ocl.dispose();
	}

	@Test public void test_iterator_scope() {
		TestOCL ocl = createOCL();
		ocl.assertQueryEquals(null, 6, "Set{1, 2, 3 }->iterate(i : Integer; sum : Integer = 0 | sum + i)");
		ocl.assertQueryInvalid(null, "let s : Set(String) = invalid in Set{'a'}->union(s)");
		ocl.dispose();
	}

	@Test public void test_compatibility_names() {
		TestOCL ocl = createOCL();
		ocl.assertQueryEquals(null, 6, "Set{1, 2, 3 }->_iterate(i : Integer; sum : Integer = 0 | _sum + _i)");
		ocl.assertQueryInvalid(null, "let s : Set(String) = invalid in Set{'a'}->_union(_s)");
		ocl.dispose();
	}

	@Test public void test_self_scope() {
		TestOCL ocl = createOCL();
		ExpressionInOCL query = ocl.assertQuery(null, "Sequence{1}");
		CollectionLiteralExp coll = (CollectionLiteralExp) query.getOwnedBody();
		CollectionItem item = (CollectionItem) coll.getOwnedParts().get(0);
		ocl.assertQueryTrue(item, "type = ownedItem.type");
		//		ocl.assertQueryInvalid(null, "type = item.type");		// A2.2 def'n of invalid = invalid
		ocl.assertQueryInvalid(null, "let item : CollectionItem = null in item.type = item");		// A2.2 def'n of invalid = invalid
		ocl.assertQueryInvalid(null, "let item : CollectionItem = invalid in item.type = item");		// A2.2 def'n of invalid = invalid
		ocl.dispose();
	}

	@Test public void test_caught_and_uncaught() {
		TestOCL ocl = createOCL();
		initFruitPackage(ocl);
		EObject context = fruitEFactory.create(tree);
		ocl.assertQueryTrue(context, "let myName : String = name in myName.oclIsKindOf(String) and myName = null");
		ocl.dispose();
	}

	/**
	 * Tests nested exists iterators.
	 */
	@Test public void test_double_exists_407817() {
		TestOCL ocl = createOCL();
		String textQuery =
				"Set{'a','1'}->exists(s | Set{1,2}->exists(i | i.toString() = s.toUpper()))";
		ocl.assertQueryTrue(null, textQuery);
		ocl.dispose();
	}

	/**
	 * Tests same names on both if branches. This gave CG problems.
	 */
	@Test public void test_double_get() {
		TestOCL ocl = createOCL();
		initFruitPackage(ocl);
		EObject context = fruitEFactory.create(tree);
		ocl.assertQueryEquals(context, null, "if true then name else name endif");
		ocl.dispose();
	}

	/**
	 * Tests a guarded let if in operator. This gave CG problems.
	 */
	@Test public void test_cg_let_implies() {
		TestOCL ocl = createOCL();
		StandardLibrary standardLibrary = ocl.getStandardLibrary();
		String textQuery =
				"let bodyConstraint : Constraint = null\n" +
						"in bodyConstraint <> null implies\n" +
						"bodyConstraint?.ownedSpecification = null";
		org.eclipse.ocl.pivot.Class testType = standardLibrary.getIntegerType();
		assert testType.getOwnedInvariants().isEmpty();
		ocl.assertQueryTrue(testType, textQuery);
		//		ocl.assertQueryTrue(ValuesUtil.createTypeValue(metamodelManager.getMetaclass(testType)), textQuery);
		ocl.dispose();
	}

	@Test public void test_let_implies_let_implies() {
		TestOCL ocl = createOCL();
		StandardLibrary standardLibrary = ocl.getStandardLibrary();
		String textQuery =
				"let bodyConstraint : Constraint = oclType().ownedInvariants?->any(name = 'body')\n" +
						"in bodyConstraint <> null implies\n" +
						"let bodySpecification : ValueSpecification = bodyConstraint?.ownedSpecification\n" +
						"in bodySpecification <> null and\n" +
						"bodySpecification?.oclIsKindOf(ExpressionInOCL) implies\n" +
						"true";
		//	    "CompatibleBody(bodySpecification)";
		org.eclipse.ocl.pivot.Class testType = standardLibrary.getIntegerType();
		assert testType.getOwnedInvariants().isEmpty();
		ocl.assertQueryTrue(-1, textQuery);
		ocl.dispose();
	}

	@Test public void test_no_self() throws ParserException {
		TestOCL ocl = createOCL();
		// This test relies on a fudge in assertSemanticErrorQuery or rather in assertBadQuery that adjusts the 'self' spelling.
		ocl.assertSemanticErrorQuery(null, "self->any(true)", PivotMessages.UnspecifiedSelfContext);
		ocl.dispose();
	}

	@Test public void test_cg_implies_calls() throws ParserException {
		TestOCL ocl = createOCL();
		StandardLibrary standardLibrary = ocl.getStandardLibrary();
		ExpressionInOCL query = ocl.createQuery(standardLibrary.getOclVoidType(), "self->any(true)");
		String textQuery =
				"name = 'closure' implies\n" +
						"type.oclAsType(CollectionType).elementType = null";
		ocl.assertQueryTrue(query.getOwnedBody(), textQuery);
		ocl.dispose();
	}

	@Test public void test_cg_caught_if() throws ParserException {
		TestOCL ocl = createOCL();
		StandardLibrary standardLibrary = ocl.getStandardLibrary();
		ExpressionInOCL query = ocl.createQuery(standardLibrary.getOclVoidType(), "self->any(true)");
		String textQuery =
				"name = 'closure' implies\n" +
						"if self.ownedSource?.type.oclIsKindOf(SequenceType) or self.ownedSource?.type.oclIsKindOf(OrderedSetType)"
						+ "then self.type.oclIsKindOf(OrderedSetType) else self.type.oclIsKindOf(SetType) endif";
		ocl.assertQueryTrue(query.getOwnedBody(), textQuery);
		ocl.dispose();
	}

	@Test public void test_cg_equals_528829() throws Exception {
		TestOCL ocl = createOCL();
		if (!EMFPlugin.IS_ECLIPSE_RUNNING) {
			OCLinEcoreStandaloneSetup.doSetup();
		}
		String metamodelText =
				"import ecore : 'http://www.eclipse.org/emf/2002/Ecore#/';\n" +
						"package pkg : pkg = 'pkg' {\n" +
						"  class A {\n" +
						"    property kind : Kind;\n" +
						"  }\n" +
						"  enum Kind {\n" +
						"    literal X;\n" +
						"  }\n" +
						"}\n";
		Resource metamodel = cs2as(ocl, metamodelText);
		Model pivotModel = (Model) metamodel.getContents().get(0);
		org.eclipse.ocl.pivot.Package pivotPackage = pivotModel.getOwnedPackages().get(0);
		org.eclipse.ocl.pivot.Class pivotType = pivotPackage.getOwnedClasses().get(0);
		EClass eClass = ClassUtil.nonNullState(ocl.getMetamodelManager().getEcoreOfPivot(EClass.class, pivotType));
		Object testObject = eClass.getEPackage().getEFactoryInstance().create(eClass);
		ocl.assertQueryFalse(testObject, "kind = 'kind'");
		ocl.assertQueryFalse(testObject, "'kind' = kind");
		ocl.dispose();
	}

	@Test public void test_cg_loop_source_self_or() throws ParserException, IOException {
		TestOCL ocl = createOCL();
		if (!EMFPlugin.IS_ECLIPSE_RUNNING) {
			OCLinEcoreStandaloneSetup.doSetup();
		}
		String metamodelText =
				"import ecore : 'http://www.eclipse.org/emf/2002/Ecore#/';\n" +
						"package pkg : pkg = 'pkg' {\n" +
						"  class A {\n" +
						"    invariant True : true;\n" +
						"  }\n" +
						"}\n";
		Resource metamodel = cs2as(ocl, metamodelText);
		Model pivotModel = (Model) metamodel.getContents().get(0);
		org.eclipse.ocl.pivot.Package pivotPackage = pivotModel.getOwnedPackages().get(0);
		org.eclipse.ocl.pivot.Class pivotType = pivotPackage.getOwnedClasses().get(0);
		Constraint pivotConstraint = pivotType.getOwnedInvariants().get(0);
		String textQuery = "context.oclAsType(Class).ownedInvariants->excluding(self)?->forAll(name <> self.name or isCallable <> self.isCallable)";
		ocl.assertQueryTrue(pivotConstraint, textQuery);
		ocl.dispose();
	}

	/*	@Test public void test_cg_derived_operation() throws ParserException, IOException {
		if (!EMFPlugin.IS_ECLIPSE_RUNNING) {
			OCLinEcoreStandaloneSetup.doSetup();
			OCLDelegateDomain.initialize(null);
		}
		String metamodelText =
				"import ecore : 'http://www.eclipse.org/emf/2002/Ecore#/';\n" +
				"package pkg : pkg = 'pkg' {\n" +
				"  class A {\n" +
				"    operation derivedOperation(p : Integer) : Integer { body: p * 99; }\n" +
				"    operation derivedDerivedOperation(p : Integer) : Integer { body: 2 * derivedOperation(p);}\n" +
				"  }\n" +
				"}\n";
		Resource metamodel = cs2as(ocl, metamodelText);
		Root pivotModel = (Root) metamodel.getContents().get(0);
		org.eclipse.ocl.pivot.Package pivotPackage = pivotModel.getNestedPackage().get(0);
		Type pivotType = pivotPackage.getOwnedType().get(0);
		EClass eClass = metamodelManager.getEcoreOfPivot(EClass.class, pivotType);
		Object testObject = eClass.getEPackage().getEFactoryInstance().create(eClass);
		String textQuery = "self.derivedDerivedOperation(3)";
		assertQueryEquals(testObject, 594, textQuery);
	} */

	@Test public void test_cg_derived_property() throws ParserException, IOException {
		TestOCL ocl = createOCL();
		if (!EMFPlugin.IS_ECLIPSE_RUNNING) {
			OCLinEcoreStandaloneSetup.doSetup();
			//			OCLDelegateDomain.initialize(null);
		}
		MetamodelManager metamodelManager = ocl.getMetamodelManager();
		String metamodelText =
				"import ecore : 'http://www.eclipse.org/emf/2002/Ecore#/';\n" +
						"package pkg : pkg = 'pkg' {\n" +
						"  class A {\n" +
						"    property derivedInteger : Integer { derivation: 99; }\n" +
						"    property derivedDerivedInteger : Integer { derivation: 2 * derivedInteger;}\n" +
						"  }\n" +
						"}\n";
		Resource metamodel = cs2as(ocl, metamodelText);
		Model pivotModel = (Model) metamodel.getContents().get(0);
		org.eclipse.ocl.pivot.Package pivotPackage = pivotModel.getOwnedPackages().get(0);
		org.eclipse.ocl.pivot.Class pivotType = pivotPackage.getOwnedClasses().get(0);
		EClass eClass = ClassUtil.nonNullState(metamodelManager.getEcoreOfPivot(EClass.class, pivotType));
		Object testObject = eClass.getEPackage().getEFactoryInstance().create(eClass);
		String textQuery = "self.derivedDerivedInteger";
		ocl.assertQueryEquals(testObject, 198, textQuery);
		ocl.dispose();
	}

	@Test public void test_cg_name_occlusion_401692() throws ParserException, IOException {
		if (useCodeGen) {
			return;					// FIXME 506647 regression disabled
		}
		TestOCL ocl = createOCL();
		if (!EMFPlugin.IS_ECLIPSE_RUNNING) {
			OCLinEcoreStandaloneSetup.doSetup();
			//			OCLDelegateDomain.initialize(null);
		}
		MetamodelManager metamodelManager = ocl.getMetamodelManager();
		String metamodelText =
				"package scope = 'abc'\n" +
						"{\n" +
						"	class A\n" +
						"	{\n" +
						"		attribute d : String;\n" +
						"		attribute e : String;\n" +
						"	}\n" +
						"	class B\n" +
						"	{\n" +
						"		operation findA(e : String) : A[?]\n" +
						"		{\n" +
						"			body:\n" +
						"				let found : OrderedSet(A) = as?->select(a : A | a.d = e) in if found->size() > 0 then found->first() else null endif;\n" +
						"		}\n" +
						"		property as : A[*] { ordered composes };\n" +
						"	}\n" +
						"}\n";
		Resource metamodel = cs2as(ocl, metamodelText);
		Model pivotModel = (Model) metamodel.getContents().get(0);
		org.eclipse.ocl.pivot.Package pivotPackage = pivotModel.getOwnedPackages().get(0);
		org.eclipse.ocl.pivot.Class pivotTypeA = ClassUtil.nonNullState(NameUtil.getNameable(pivotPackage.getOwnedClasses(), "A"));
		org.eclipse.ocl.pivot.Class pivotTypeB = ClassUtil.nonNullState(NameUtil.getNameable(pivotPackage.getOwnedClasses(), "B"));
		EPackage ePackage = ClassUtil.nonNullState(metamodelManager.getEcoreOfPivot(EPackage.class, pivotPackage));
		EClass eClassA = metamodelManager.getEcoreOfPivot(EClass.class, pivotTypeA);
		EClass eClassB = metamodelManager.getEcoreOfPivot(EClass.class, pivotTypeB);
		EAttribute eAttributeAd = metamodelManager.getEcoreOfPivot(EAttribute.class, ClassUtil.nonNullState(NameUtil.getNameable(pivotTypeA.getOwnedProperties(), "d")));
		EAttribute eAttributeAe = metamodelManager.getEcoreOfPivot(EAttribute.class, ClassUtil.nonNullState(NameUtil.getNameable(pivotTypeA.getOwnedProperties(), "e")));
		EReference eReferenceBas = metamodelManager.getEcoreOfPivot(EReference.class, ClassUtil.nonNullState(NameUtil.getNameable(pivotTypeB.getOwnedProperties(), "as")));
		EFactory eFactory = ePackage.getEFactoryInstance();
		Resource resource = new ResourceImpl();
		EObject testObjectA1 = eFactory.create(eClassA);
		testObjectA1.eSet(eAttributeAd, "d1");
		testObjectA1.eSet(eAttributeAe, "e1");
		EObject testObjectA2 = eFactory.create(eClassA);
		testObjectA2.eSet(eAttributeAd, "d2");
		testObjectA2.eSet(eAttributeAe, "e2");
		EObject testObjectB = eFactory.create(eClassB);
		resource.getContents().add(testObjectB);
		@SuppressWarnings("unchecked")List<EObject> as = (List<EObject>) testObjectB.eGet(eReferenceBas);
		as.add(testObjectA1);
		as.add(testObjectA2);
		//
		ocl.assertQueryEquals(testObjectB, testObjectA1, "self.findA('d1')");
		ocl.assertQueryEquals(testObjectB, null, "self.findA('e2')");
		ocl.dispose();
	}

	@Test public void test_cg_tuple_access() throws ParserException, IOException {
		TestOCL ocl = createOCL();
		//		getHelper().setContext(metamodelManager.getOclVoidType());
		String textQuery =
				"let\n" +
						"  table : Set(Tuple(range : Sequence(Integer), size : String)) = Set{\n" +
						"    Tuple{range = Sequence{0..49}, size = 'small'},\n" +
						"    Tuple{range = Sequence{50..999}, size = 'medium'},\n" +
						"    Tuple{range = Sequence{1000..1000000}, size = 'large'}\n" +
						"  }\n" +
						"in\n" +
						"  table?->any(range->includes(200000))?.size";
		ocl.assertQueryEquals(null, "large", textQuery);
		ocl.dispose();
	}

	/**
	 * Tests the basic name accesses
	 */
	@Test public void test_container_navigation() throws InvocationTargetException {
		TestOCL ocl = createOCLWithProjectMap();
		initFruitPackage(ocl);
		MetamodelManagerInternal metamodelManager = ocl.getMetamodelManager();
		IdResolver idResolver = ocl.getIdResolver();
		metamodelManager.addGlobalNamespace("fruit", fruitPackage);
		//
		//	Simple model: aTree contains redApple
		//
		EObject redApple = fruitEFactory.create(apple);
		redApple.eSet(fruit_color, color_red);
		//		EObject greenApple = fruitEFactory.create(apple);
		//		greenApple.eSet(fruit_color, color_green);
		EObject aTree = fruitEFactory.create(tree);
		@SuppressWarnings("unchecked")
		List<Object> treeFruits = (List<Object>) aTree.eGet(tree_fruits);
		treeFruits.add(redApple);
		//
		Type pivotTree = metamodelManager.getASOfEcore(Type.class, tree);
		//
		ocl.assertQueryEquals(redApple, color_red, "let aFruit : fruit::Fruit = self in aFruit.color");
		ocl.assertQueryEquals(aTree, idResolver.createOrderedSetOfEach(TypeId.SET, redApple), "let aTree : fruit::Tree = self in aTree.fruits");
		ocl.assertQueryEquals(aTree, idResolver.createOrderedSetOfEach(TypeId.SET, redApple), "self.fruits");
		ocl.assertQueryEquals(aTree, idResolver.createOrderedSetOfEach(TypeId.SET, redApple), "fruits");
		ocl.assertQueryEquals(redApple, aTree, "self.oclContainer()");
		ocl.assertQueryEquals(redApple, aTree, "self.Tree");
		//
		//	type/property ambiguity is resolved to type.
		//
		ocl.assertQueryEquals(redApple, pivotTree, "Tree");
		//
		//	type/property ambiguity is resolved to type.
		//
		ocl.assertQueryInvalid(redApple, "self.oclAsType(Tree)");
		//		ocl.assertQueryEquals(aTree, ValuesUtil.createOrderedSetValue(null, redApple), "self.oclAsType(Tree).fruits");
		ocl.dispose();
	}

	/**
	 * Tests the nested name accesses
	 */
	@Test public void test_nested_names() throws InvocationTargetException {
		TestOCL ocl = createOCLWithProjectMap();
		initFruitPackage(ocl);
		MetamodelManagerInternal metamodelManager = ocl.getMetamodelManager();
		IdResolver idResolver = ocl.getIdResolver();
		org.eclipse.ocl.pivot.Class appleType = metamodelManager.getASOfEcore(org.eclipse.ocl.pivot.Class.class, apple);
		//
		//	Simple model: appleTree contains redApple
		//
		EObject redApple = fruitEFactory.create(apple);
		redApple.eSet(fruit_color, color_red);
		redApple.eSet(fruit_name, "RedApple");
		EObject appleTree = fruitEFactory.create(tree);
		appleTree.eSet(tree_name, "AppleTree");
		@SuppressWarnings("unchecked")
		List<Object> treeFruits = (List<Object>) appleTree.eGet(tree_fruits);
		treeFruits.add(redApple);
		//
		ocl.assertQueryEquals(redApple, "RedApple", "self.name");
		ocl.assertQueryEquals(redApple, "RedApple", "self.Fruit::name");
		ocl.assertQueryEquals(redApple, "RedApple", "self.Apple::name");
//		ocl.assertValidationErrorQuery(appleType, "self.Tree::name",
//			PivotMessages.ValidationConstraintIsNotSatisfied_ERROR_, "PropertyCallExp::NonStaticSourceTypeIsConformant", "self.name");
		ocl.assertSemanticErrorQuery(appleType, "self.Tree::name",
			EssentialOCLCS2ASMessages.PropertyCallExp_IncompatibleProperty, "fruit::Tree::name");
		//
		ocl.assertQueryFalse(redApple, "self.color = Color::green");
		ocl.assertQueryTrue(redApple, "self.color = Color::red");
		ocl.assertQueryFalse(redApple, "self.color = 'red'");
		ocl.assertQueryEquals(redApple, redApple, "self.oclAsType(Apple)");
		ocl.assertQueryEquals(redApple, redApple, "self.oclAsType(fruit::Apple)");
		ocl.assertQueryEquals(redApple, idResolver.createSetOfEach(TypeId.SET, redApple), "self->oclAsType(Set(Fruit))");
		ocl.assertQueryEquals(redApple, idResolver.createSetOfEach(TypeId.SET, redApple), "self->oclAsType(Set(fruit::Apple))");
		ocl.assertSemanticErrorQuery(appleType, "self.oclAsType(fruit::fruit::Apple)", PivotMessagesInternal.UnresolvedNamespace_ERROR_, "fruit", "fruit");	// Demonstrates Bug 353985
		ocl.assertSemanticErrorQuery(appleType, "self->oclAsType(Set(fruit::apple::BadApple))", PivotMessagesInternal.UnresolvedType_ERROR_, "", "BadApple");
		ocl.assertSemanticErrorQuery(appleType, "self->oclAsType(Set(fruit::apple::BadApple))", PivotMessagesInternal.UnresolvedType_ERROR_, "", "BadApple");
		ocl.assertSemanticErrorQuery(appleType, "self->oclAsType(Set(fruit::badapple::BadApple))", PivotMessagesInternal.UnresolvedNamespace_ERROR_, "fruit", "badapple");
		ocl.assertSemanticErrorQuery(appleType, "self->oclAsType(Set(badfruit::badapple::BadApple))", PivotMessagesInternal.UnresolvedNamespace_ERROR_, "", "badfruit");
		ocl.assertQueryInvalid(redApple, "self->oclAsType(Set(fruit::apple::EatingApple))");
		ocl.assertQueryInvalid(redApple, "self->oclAsType(Set(fruit::Tree))");
		//
		ocl.assertQueryEquals(redApple, idResolver.createSetOfEach(TypeId.SET, appleTree), "Tree.allInstances()");
		ocl.assertQueryEquals(redApple, idResolver.createSetOfEach(TypeId.SET, appleTree), "fruit::Tree.allInstances()");
		EObject orphanFruit = fruitEFactory.create(apple);	// FIXME Bug 548225 comment 4 null has no metamodel and so gives UOE
		ocl.assertQueryEquals(orphanFruit, ocl.getEmptySetValue(), "fruit::Tree.allInstances()");
		//
		metamodelManager.addGlobalNamespace("zz", fruitPackage);
		ocl.assertQueryEquals(redApple, idResolver.createSetOfEach(TypeId.SET, appleTree), "zz::Tree.allInstances()");
		//
		ocl.assertQueryEquals(redApple, idResolver.createBagOfEach(TypeId.BAG, redApple), "Fruit.allInstances().oclAsType(Apple)");
		ocl.assertQueryEquals(redApple, idResolver.createSetOfEach(TypeId.SET, redApple), "Fruit.allInstances()->oclAsType(Set(Apple))");
		ocl.dispose();
	}

	@Test public void test_reservedNames() {
		TestOCL ocl = createOCL();
		ocl.assertQueryTrue(null, "Boolean.allInstances()->forAll(_'if' | _'if' <> null)");
		ocl.assertQueryTrue(null, "let _'if' = true in _'if'");
		ocl.dispose();
	}

	/**
	 * Tests construction of a type instance with property values
	 */
	@Test public void test_type_construction() throws InvocationTargetException {
		TestOCL ocl = createOCLWithProjectMap();
		initFruitPackage(ocl);
		EObject context = fruitEFactory.create(tree);
		ocl.assertValidationErrorQuery(ocl.getContextType(context), "Apple{stem=null}.label", "Missing initializers: color");
		ocl.assertValidationErrorQuery(ocl.getContextType(context), "Apple{name=null}.label", "Unexpected initializers: name");
		ocl.assertQueryEquals(context, null, "Apple{color=null,label=null,stem=null}.label");
		ocl.assertQueryEquals(context, "RedApple", "Apple{color=Color::red,label='RedApple',stem=null}.label");
		ocl.assertQueryEquals(context, color_red, "Apple{color=Color::red,label='RedApple',stem=null}.color");
		ocl.assertQueryTrue(context, "Apple{color=Color::red,label='RedApple',stem=null} = Apple{color=Color::red,label='RedApple',stem=null}");
		ocl.assertQueryTrue(context, "let thisApple = Apple{stem=null,label='AnApple',color=Color::red}, thatApple = Apple{label='AnApple',color=Color::red,stem=null} in thisApple = thatApple");
		ocl.assertQueryTrue(context, "let thisApple = Apple{stem=null,label='AnApple',color=Color::red}, thatApple = Apple{stem=null,color=Color::red,label='AnApple'} in thisApple = thatApple");
		ocl.assertQueryTrue(context, "let thisApple = Apple{stem=null,label='AnApple',color=Color::red}, thatApple = Apple{stem=null,label='AnApple',color=Color::red} in thisApple.label = thatApple.label");
		ocl.assertQueryTrue(context, "let thisApple = Apple{stem=null,label='AnApple',color=Color::red}, thatApple = Apple{stem=null,label='AnApple',color=Color::red} in thisApple.color = thatApple.color");
		ocl.assertQueryTrue(context, "let thisApple = Apple{stem=null,label='AnApple',color=Color::red}, thatApple = Apple{stem=null,label='AnApple',color=Color::red} in thisApple.label = thatApple.label and thisApple.color = thatApple.color");
		ocl.assertQueryFalse(context, "let thisApple = Apple{stem=null,label='ThisApple',color=Color::red}, thatApple = Apple{stem=null,label='ThatApple',color=Color::red} in thisApple.label = thatApple.label and thisApple.color = thatApple.color");
		ocl.assertQueryFalse(context, "let thisApple = Apple{stem=null,label='AnApple',color=Color::red}, thatApple = Apple{stem=null,label='AnApple',color=Color::black} in thisApple.label = thatApple.label and thisApple.color = thatApple.color");
		ocl.dispose();
	}

	/**
	 * Tests construction of a type instance with property values
	 * @throws ParserException
	 */
	@Test public void test_uml_primitives_399378() throws ParserException {
		//		TestCaseAppender.INSTANCE.uninstall();
		TestOCL ocl = createOCL();
		ResourceSet resourceSet = ocl.getResourceSet();
		UML2AS.initialize(resourceSet);
		MetamodelManagerInternal metamodelManager = ocl.getMetamodelManager();
		URI uri = getTestModelURI("models/uml/Fruit.uml");
		Element element = ClassUtil.nonNullState(metamodelManager.loadResource(uri, null, resourceSet));
		org.eclipse.ocl.pivot.Package fruitPackage = ((Model)element).getOwnedPackages().get(0);
		org.eclipse.ocl.pivot.Class treeClass = NameUtil.getNameable(fruitPackage.getOwnedClasses(), "Tree");
		ExpressionInOCL query = ocl.createQuery(treeClass, "self.height>20");
		assertNotNull(query);
		ocl.dispose();
	}

	@Test public void test_dynamic_dispatch_411154() throws ParserException, IOException {
		if (useCodeGen) return;
		TestOCL ocl = createOCL();
		if (!EMFPlugin.IS_ECLIPSE_RUNNING) {
			OCLinEcoreStandaloneSetup.doSetup();
			//			OCLDelegateDomain.initialize(null);
		}
		ocl.getEnvironmentFactory().setOption(PivotValidationOptions.OptionalDefaultMultiplicity, Boolean.TRUE);
		MetamodelManager metamodelManager = ocl.getMetamodelManager();
		String metamodelText =
				"package Bug411154 : pfx = 'Bug411154.ecore'\n" +
						"{\n" +
						"	class Domain {\n" +
						"		property types : T1[*] { ordered composes };\n" +
						"		property t1_2a : T1;\n" +
						"		property t1_3a : T1;\n" +
						"		property t1_3b : T1;\n" +
						"		property t1_4 : T1;\n" +
						"		property t2a_2a : T2a;\n" +
						"		property t2a_3a : T2a;\n" +
						"		property t2b_2b : T2b;\n" +
						"		property t3a : T3a;\n" +
						"	}\n" +
						"	abstract class T1 {\n" +
						"		operation op1() : String { body: 'T1::op1'; }\n" +
						"		operation op4() : String { body: 'T1::op4'; }\n" +
						"		operation op5() : String { body: 'T1::op5'; }\n" +
						"		operation op6() : String { body: 'T1::op6'; }\n" +
						"	}\n" +
						"	class T2a extends T1 {\n" +
						"		operation op1() : String { body: 'T2a::op1'; }\n" +
						"		operation op2() : String { body: 'T2a::op2'; }\n" +
						"		operation op4() : String { body: 'T2a::op4'; }\n" +
						"		operation op6() : String { body: 'T2a::op6'; }\n" +
						"		operation op7() : String { body: 'T2a::op7'; }\n" +
						"		operation op9() : String { body: 'T2a::op9'; }\n" +
						"	}\n" +
						"	class T2b extends T1 {\n" +
						"		operation op6() : String { body: 'T2b::op6'; }\n" +
						"		operation op7() : String { body: 'T2b::op7'; }\n" +
						"		operation op9() : String { body: 'T2b::op9'; }\n" +
						"	}\n" +
						"	class T3a extends T2a,T2b {\n" +
						"		operation op1() : String { body: 'T3a::op1'; }\n" +
						"		operation op2() : String { body: 'T3a::op2'; }\n" +
						"		operation op3() : String { body: 'T3a::op3'; }\n" +
						"		operation op6() : String { body: 'T3a::op6'; }\n" +
						"		operation op7() : String { body: 'T3a::op7'; }\n" +
						"	}\n" +
						"	class T3b extends T2a,T2b {\n" +
						"	}\n" +
						"	class T4 extends T3a,T3b {\n" +
						"		operation op6() : String { body: 'T4::op6'; }\n" +
						"		operation op7() : String { body: 'T4::op7'; }\n" +
						"	}\n" +
						"}\n";
		Resource metamodel = cs2as(ocl, metamodelText);
		Model pivotModel = (Model) metamodel.getContents().get(0);
		org.eclipse.ocl.pivot.Package pivotPackage = pivotModel.getOwnedPackages().get(0);
		org.eclipse.ocl.pivot.Class pivotTypeDomain = ClassUtil.nonNullState(NameUtil.getNameable(pivotPackage.getOwnedClasses(), "Domain"));
		//		org.eclipse.ocl.pivot.Class pivotTypeT1 = ClassUtil.getNamedElement(pivotPackage.getOwnedType(), "T1");
		org.eclipse.ocl.pivot.Class pivotTypeT2a = NameUtil.getNameable(pivotPackage.getOwnedClasses(), "T2a");
		org.eclipse.ocl.pivot.Class pivotTypeT2b = NameUtil.getNameable(pivotPackage.getOwnedClasses(), "T2b");
		org.eclipse.ocl.pivot.Class pivotTypeT3a = NameUtil.getNameable(pivotPackage.getOwnedClasses(), "T3a");
		org.eclipse.ocl.pivot.Class pivotTypeT3b = NameUtil.getNameable(pivotPackage.getOwnedClasses(), "T3b");
		org.eclipse.ocl.pivot.Class pivotTypeT4 = NameUtil.getNameable(pivotPackage.getOwnedClasses(), "T4");
		EPackage ePackage = ClassUtil.nonNullState(metamodelManager.getEcoreOfPivot(EPackage.class, pivotPackage));
		EClass eClassDomain = metamodelManager.getEcoreOfPivot(EClass.class, pivotTypeDomain);
		//		EClass eClassT1 = metamodelManager.getEcoreOfPivot(EClass.class, pivotTypeT1);
		EClass eClassT2a = metamodelManager.getEcoreOfPivot(EClass.class, pivotTypeT2a);
		EClass eClassT2b = metamodelManager.getEcoreOfPivot(EClass.class, pivotTypeT2b);
		EClass eClassT3a = metamodelManager.getEcoreOfPivot(EClass.class, pivotTypeT3a);
		EClass eClassT3b = metamodelManager.getEcoreOfPivot(EClass.class, pivotTypeT3b);
		EClass eClassT4 = metamodelManager.getEcoreOfPivot(EClass.class, pivotTypeT4);
		EReference eReferenceDomain_types = metamodelManager.getEcoreOfPivot(EReference.class, ClassUtil.nonNullState(NameUtil.getNameable(pivotTypeDomain.getOwnedProperties(), "types")));
		EReference eReferenceDomain_t1_2a = metamodelManager.getEcoreOfPivot(EReference.class, ClassUtil.nonNullState(NameUtil.getNameable(pivotTypeDomain.getOwnedProperties(), "t1_2a")));
		EReference eReferenceDomain_t1_3a = metamodelManager.getEcoreOfPivot(EReference.class, ClassUtil.nonNullState(NameUtil.getNameable(pivotTypeDomain.getOwnedProperties(), "t1_3a")));
		EReference eReferenceDomain_t1_3b = metamodelManager.getEcoreOfPivot(EReference.class, ClassUtil.nonNullState(NameUtil.getNameable(pivotTypeDomain.getOwnedProperties(), "t1_3b")));
		EReference eReferenceDomain_t1_4 = metamodelManager.getEcoreOfPivot(EReference.class, ClassUtil.nonNullState(NameUtil.getNameable(pivotTypeDomain.getOwnedProperties(), "t1_4")));
		EReference eReferenceDomain_t2a_2a = metamodelManager.getEcoreOfPivot(EReference.class, ClassUtil.nonNullState(NameUtil.getNameable(pivotTypeDomain.getOwnedProperties(), "t2a_2a")));
		EReference eReferenceDomain_t2a_3a = metamodelManager.getEcoreOfPivot(EReference.class, ClassUtil.nonNullState(NameUtil.getNameable(pivotTypeDomain.getOwnedProperties(), "t2a_3a")));
		EReference eReferenceDomain_t2b_2b = metamodelManager.getEcoreOfPivot(EReference.class, ClassUtil.nonNullState(NameUtil.getNameable(pivotTypeDomain.getOwnedProperties(), "t2b_2b")));
		EReference eReferenceDomain_t3a = metamodelManager.getEcoreOfPivot(EReference.class, ClassUtil.nonNullState(NameUtil.getNameable(pivotTypeDomain.getOwnedProperties(), "t3a")));
		EFactory eFactory = ePackage.getEFactoryInstance();
		Resource resource = new ResourceImpl();
		EObject testObjectDomain = eFactory.create(eClassDomain);
		resource.getContents().add(testObjectDomain);
		EObject testObjectT2a = eFactory.create(eClassT2a);
		EObject testObjectT2b = eFactory.create(eClassT2b);
		EObject testObjectT3a = eFactory.create(eClassT3a);
		EObject testObjectT3b = eFactory.create(eClassT3b);
		EObject testObjectT4 = eFactory.create(eClassT4);
		@SuppressWarnings("unchecked")
		List<EObject> list = (List<EObject>)testObjectDomain.eGet(eReferenceDomain_types);
		list.add(testObjectT2a);
		list.add(testObjectT2b);
		list.add(testObjectT3a);
		list.add(testObjectT3b);
		list.add(testObjectT4);
		testObjectDomain.eSet(eReferenceDomain_t1_2a, testObjectT2a);
		testObjectDomain.eSet(eReferenceDomain_t1_3a, testObjectT3a);
		testObjectDomain.eSet(eReferenceDomain_t1_3b, testObjectT3b);
		testObjectDomain.eSet(eReferenceDomain_t1_4, testObjectT4);
		testObjectDomain.eSet(eReferenceDomain_t2a_2a, testObjectT2a);
		testObjectDomain.eSet(eReferenceDomain_t2a_3a, testObjectT3a);
		testObjectDomain.eSet(eReferenceDomain_t2b_2b, testObjectT2b);
		testObjectDomain.eSet(eReferenceDomain_t3a, testObjectT3a);
		//
		ocl.assertQueryEquals(testObjectT2a, "T2a::op1", "self.op1()");
		ocl.assertQueryEquals(testObjectT2a, "T2a::op2", "self.op2()");
		ocl.assertSemanticErrorQuery(pivotTypeT2a, "self.op3()", PivotMessagesInternal.UnresolvedOperation_ERROR_, "Bug411154", "T2a::op3");
		ocl.assertQueryEquals(testObjectT2a, "T2a::op4", "self.op4()");
		ocl.assertQueryEquals(testObjectT2a, "T1::op5", "self.op5()");
		ocl.assertQueryEquals(testObjectT2a, "T2a::op6", "self.op6()");
		ocl.assertQueryEquals(testObjectT2a, "T2a::op7", "self.op7()");
		ocl.assertSemanticErrorQuery(pivotTypeT2a, "self.op8()", PivotMessagesInternal.UnresolvedOperation_ERROR_, "Bug411154", "T2a::op8");
		ocl.assertQueryEquals(testObjectT2a, "T2a::op9", "self.op9()");
		//
		ocl.assertQueryEquals(testObjectT3a, "T3a::op1", "self.op1()");
		ocl.assertQueryEquals(testObjectT3a, "T3a::op2", "self.op2()");
		ocl.assertQueryEquals(testObjectT3a, "T3a::op3", "self.op3()");
		ocl.assertQueryEquals(testObjectT3a, "T2a::op4", "self.op4()");
		ocl.assertQueryEquals(testObjectT3a, "T1::op5", "self.op5()");
		ocl.assertQueryEquals(testObjectT3a, "T3a::op6", "self.op6()");
		ocl.assertQueryEquals(testObjectT3a, "T3a::op7", "self.op7()");
		ocl.assertSemanticErrorQuery(pivotTypeT3a, "self.op8()", PivotMessagesInternal.UnresolvedOperation_ERROR_, "Bug411154", "T3a::op8");
		ocl.assertSemanticErrorQuery(pivotTypeT3a, "self.op9()", "Ambiguous resolution:\n" +
				"\tOperation : Bug411154::T2a::op9() : String[?]\n" +
				"\tOperation : Bug411154::T2b::op9() : String[?]");
		//
		ocl.assertQueryEquals(testObjectDomain, "T2a::op1", "t1_2a.op1()");
		ocl.assertSemanticErrorQuery(pivotTypeDomain, "t1_2a.op2()", PivotMessagesInternal.UnresolvedOperation_ERROR_, "Bug411154", "T1::op2");
		ocl.assertSemanticErrorQuery(pivotTypeDomain, "t1_2a.op3()", PivotMessagesInternal.UnresolvedOperation_ERROR_, "Bug411154", "T1::op3");
		ocl.assertQueryEquals(testObjectDomain, "T2a::op4", "t1_2a.op4()");
		ocl.assertQueryEquals(testObjectDomain, "T1::op5", "t1_2a.op5()");
		//
		ocl.assertQueryEquals(testObjectDomain, "T3a::op1", "t1_3a.op1()");
		ocl.assertSemanticErrorQuery(pivotTypeDomain, "t1_3a.op2()", PivotMessagesInternal.UnresolvedOperation_ERROR_, "Bug411154", "T1::op2");
		ocl.assertSemanticErrorQuery(pivotTypeDomain, "t1_3a.op3()", PivotMessagesInternal.UnresolvedOperation_ERROR_, "Bug411154", "T1::op3");
		ocl.assertQueryEquals(testObjectDomain, "T2a::op4", "t1_3a.op4()");
		ocl.assertQueryEquals(testObjectDomain, "T1::op5", "t1_3a.op5()");
		//
		ocl.assertQueryEquals(testObjectDomain, "T2a::op1", "t1_3b.op1()");
		ocl.assertSemanticErrorQuery(pivotTypeDomain, "t1_3b.op2()", PivotMessagesInternal.UnresolvedOperation_ERROR_, "Bug411154", "T1::op2");
		ocl.assertSemanticErrorQuery(pivotTypeDomain, "t1_3b.op3()", PivotMessagesInternal.UnresolvedOperation_ERROR_, "Bug411154", "T1::op3");
		ocl.assertQueryEquals(testObjectDomain, "T2a::op4", "t1_3b.op4()");
		ocl.assertQueryEquals(testObjectDomain, "T1::op5", "t1_3b.op5()");
		ocl.assertQueryInvalid(testObjectDomain, "t1_3b.op6()", NLS.bind(PivotMessages.AmbiguousOperation, "Bug411154::T1::op6() : String[?]", "Bug411154::T3b"), InvalidValueException.class);
		ocl.assertSemanticErrorQuery(pivotTypeDomain, "t1_3b.op7()", PivotMessagesInternal.UnresolvedOperation_ERROR_, "Bug411154", "T1::op7");
		//
		ocl.assertQueryEquals(testObjectDomain, "T3a::op1", "t1_4.op1()");
		ocl.assertSemanticErrorQuery(pivotTypeDomain, "t1_4.op2()", PivotMessagesInternal.UnresolvedOperation_ERROR_, "Bug411154", "T1::op2");
		ocl.assertSemanticErrorQuery(pivotTypeDomain, "t1_4.op3()", PivotMessagesInternal.UnresolvedOperation_ERROR_, "Bug411154", "T1::op3");
		ocl.assertQueryEquals(testObjectDomain, "T2a::op4", "t1_4.op4()");
		ocl.assertQueryEquals(testObjectDomain, "T1::op5", "t1_4.op5()");
		ocl.assertQueryEquals(testObjectDomain, "T4::op6", "t1_4.op6()");
		ocl.assertSemanticErrorQuery(pivotTypeDomain, "t1_4.op7()", PivotMessagesInternal.UnresolvedOperation_ERROR_, "Bug411154", "T1::op7");
		//
		ocl.assertQueryEquals(testObjectDomain, "T2a::op1", "t2a_2a.op1()");
		ocl.assertQueryEquals(testObjectDomain, "T2a::op2", "t2a_2a.op2()");
		ocl.assertSemanticErrorQuery(pivotTypeDomain, "t2a_2a.op3()", PivotMessagesInternal.UnresolvedOperation_ERROR_, "Bug411154", "T2a::op3");
		ocl.assertQueryEquals(testObjectDomain, "T2a::op4", "t2a_2a.op4()");
		ocl.assertQueryEquals(testObjectDomain, "T1::op5", "t2a_2a.op5()");
		ocl.assertQueryEquals(testObjectDomain, "T2a::op6", "t2a_2a.op6()");
		ocl.assertQueryEquals(testObjectDomain, "T2a::op7", "t2a_2a.op7()");
		//
		ocl.assertQueryEquals(testObjectDomain, "T2b::op6", "t2b_2b.op6()");
		//
		ocl.assertQueryEquals(testObjectDomain, "T3a::op1", "t2a_3a.op1()");
		ocl.assertQueryEquals(testObjectDomain, "T3a::op2", "t2a_3a.op2()");
		ocl.assertSemanticErrorQuery(pivotTypeDomain, "t2a_3a.op3()", PivotMessagesInternal.UnresolvedOperation_ERROR_, "Bug411154", "T2a::op3");
		ocl.assertQueryEquals(testObjectDomain, "T2a::op4", "t2a_3a.op4()");
		ocl.assertQueryEquals(testObjectDomain, "T1::op5", "t2a_3a.op5()");
		//
		ocl.assertQueryEquals(testObjectDomain, "T3a::op1", "t3a.op1()");
		ocl.assertQueryEquals(testObjectDomain, "T3a::op2", "t3a.op2()");
		ocl.assertQueryEquals(testObjectDomain, "T3a::op3", "t3a.op3()");
		ocl.assertQueryEquals(testObjectDomain, "T2a::op4", "t3a.op4()");
		ocl.assertQueryEquals(testObjectDomain, "T1::op5", "t3a.op5()");
		ocl.dispose();
	}
}
