/*******************************************************************************
 * Copyright (c) 2011, 2021 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/

package org.eclipse.ocl.examples.pivot.tests;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.genmodel.OCLGenModelUtil;
import org.eclipse.ocl.examples.xtext.tests.TestUtil;
import org.eclipse.ocl.examples.xtext.tests.company.CompanyPackage;
import org.eclipse.ocl.pivot.ids.IdResolver;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.messages.PivotMessagesInternal;
import org.eclipse.ocl.pivot.internal.values.BagImpl;
import org.eclipse.ocl.pivot.messages.PivotMessages;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.OCL;
import org.eclipse.ocl.pivot.utilities.ThreadLocalExecutor;
import org.eclipse.ocl.pivot.values.OrderedSetValue;
import org.eclipse.ocl.pivot.values.Value;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/**
 * Tests for model access operations.
 */
@RunWith(value = Parameterized.class)
public class EvaluateModelOperationsTest4 extends PivotTestSuite
{
	@Parameters
	public static Collection<Object[]> data() {
		Object[][] data = new Object[][]{{false}, {true}};
		return Arrays.asList(data);
	}

	public EvaluateModelOperationsTest4(boolean useCodeGen) {
		super(useCodeGen);
	}

	protected @NonNull TestOCL createOCLWithProjectMap() {
		return new TestOCL(getTestFileSystem(), getTestPackageName(), getName(), getProjectMap(), null);
	}

	@Override
	protected @NonNull String getTestPackageName() {
		return "EvaluateModelOperations";
	}

	@BeforeClass public static void resetCounter() throws Exception {
		PivotTestSuite.resetCounter();
	}

	@Override
	@Before public void setUp() throws Exception {
		TestUtil.doOCLinEcoreSetup();
		super.setUp();
	}

	@Override
	@After public void tearDown() throws Exception {
		super.tearDown();
	}

	@SuppressWarnings("unchecked")
	public void eAdd(@NonNull EObject eObject, @NonNull String featureName, @Nullable Object value) {
		EStructuralFeature eStructuralFeature = eObject.eClass().getEStructuralFeature(featureName);
		assert eStructuralFeature.isMany();
		if (eStructuralFeature instanceof EReference) {
			assert value instanceof EObject;
		}
		else {
			assert !(value instanceof EObject);
		}
		((List<Object>)eObject.eGet(eStructuralFeature)).add(value);
	}

	public @NonNull EObject eCreate(EClass cClass) {
		return ClassUtil.nonNullEMF(cClass.getEPackage().getEFactoryInstance().create(cClass));
	}

	public void eSet(@NonNull EObject eObject, @NonNull String featureName, @Nullable Object value) {
		EStructuralFeature eStructuralFeature = eObject.eClass().getEStructuralFeature(featureName);
		assert !eStructuralFeature.isMany();
		if (eStructuralFeature instanceof EReference) {
			assert value instanceof EObject;
		}
		else {
			assert !(value instanceof EObject);
		}
		eObject.eSet(eStructuralFeature, value);
	}

	/**
	 * Test that Ecore Data Types can be used. Inspired by Bug 358713.
	 */
	@Test public void test_ecoreDataTypes() throws Exception {
		TestOCL ocl = createOCL();
		String metamodelText =
				"import ecore : 'http://www.eclipse.org/emf/2002/Ecore#/';\n" +
						"package pkg : pkg = 'pkg' {\n" +
						"  class A {\n" +
						"    property anEBigDecimal : ecore::EBigDecimal;\n" +
						"    property anEBigInteger : ecore::EBigInteger;\n" +
						"    property anEBoolean : ecore::EBoolean;\n" +
						"    property anEBooleanObject : ecore::EBooleanObject;\n" +
						"    property anEByte : ecore::EByte;\n" +
						"    property anEByteObject : ecore::EByteObject;\n" +
						"    property anEChar : ecore::EChar;\n" +
						"    property anECharacterObject : ecore::ECharacterObject;\n" +
						"    property anEDouble : ecore::EDouble;\n" +
						"    property anEDoubleObject : ecore::EDoubleObject;\n" +
						"    property anEFloat : ecore::EFloat;\n" +
						"    property anEFloatObject : ecore::EFloatObject;\n" +
						"    property anEInt : ecore::EInt;\n" +
						"    property anEIntegerObject : ecore::EIntegerObject;\n" +
						"    property anELong : ecore::ELong;\n" +
						"    property anELongObject : ecore::ELongObject;\n" +
						"    property anEShort : ecore::EShort;\n" +
						"    property anEShortObject : ecore::EShortObject;\n" +
						"    property anEString : ecore::EString;\n" +
						"    property anEDate : ecore::EDate;\n" +
						"  }\n" +
						"}\n";
		Resource metamodel = cs2ecore(ocl.getEnvironmentFactory(), metamodelText, getTestFileURI("test.ecore"));
		EPackage ePackage = (EPackage) metamodel.getContents().get(0);
		EClass eClass = ClassUtil.nonNullState((EClass) ePackage.getEClassifiers().get(0));
		//        helper.setContext(metamodelManager.getIdResolver().getType(eClass));
		EObject eObject = eCreate(eClass);
		//
		Date nowDate = new Date();
		eSet(eObject, "anEDate", nowDate);
		ocl.assertQueryTrue(eObject, "anEDate = anEDate");
		ocl.assertQueryFalse(eObject, "anEDate = ecore::EDate{value='2345-12-23'}");
		//
		eSet(eObject, "anEBigDecimal", BigDecimal.valueOf(0));
		ocl.assertQueryEquals(eObject, 0, "anEBigDecimal");
		ocl.assertQueryEquals(eObject, 1, "anEBigDecimal + 1");
		ocl.assertQueryEquals(eObject, 0, "self.anEBigDecimal");
		ocl.assertQueryEquals(eObject, 1, "self.anEBigDecimal + 1");
		//
		eSet(eObject, "anEBigInteger", BigInteger.valueOf(0));
		ocl.assertQueryEquals(eObject, 0, "anEBigInteger");
		ocl.assertQueryEquals(eObject, 1, "anEBigInteger + 1");
		ocl.assertQueryEquals(eObject, 0, "self.anEBigInteger");
		ocl.assertQueryEquals(eObject, 1, "self.anEBigInteger + 1");
		//
		ocl.assertQueryEquals(eObject, false, "anEBoolean");
		ocl.assertQueryEquals(eObject, true, "anEBoolean or true");
		ocl.assertQueryEquals(eObject, false, "self.anEBoolean");
		ocl.assertQueryEquals(eObject, true, "self.anEBoolean or true");
		//
		eSet(eObject, "anEBooleanObject", false);
		ocl.assertQueryEquals(eObject, false, "anEBooleanObject");
		ocl.assertQueryEquals(eObject, true, "anEBooleanObject or true");
		ocl.assertQueryEquals(eObject, false, "self.anEBooleanObject");
		ocl.assertQueryEquals(eObject, true, "self.anEBooleanObject or true");
		//
		ocl.assertQueryEquals(eObject, 0, "anEByte");
		ocl.assertQueryEquals(eObject, 1, "anEByte + 1");
		ocl.assertQueryEquals(eObject, 0, "self.anEByte");
		ocl.assertQueryEquals(eObject, 1, "self.anEByte + 1");
		//
		eSet(eObject, "anEByteObject", (byte)0);
		ocl.assertQueryEquals(eObject, 0, "anEByteObject");
		ocl.assertQueryEquals(eObject, 1, "anEByteObject + 1");
		ocl.assertQueryEquals(eObject, 0, "self.anEByteObject");
		ocl.assertQueryEquals(eObject, 1, "self.anEByteObject + 1");
		//
		ocl.assertQueryEquals(eObject, 0, "anEChar");
		ocl.assertQueryEquals(eObject, 1, "anEChar + 1");
		ocl.assertQueryEquals(eObject, 0, "self.anEChar");
		ocl.assertQueryEquals(eObject, 1, "self.anEChar + 1");
		//
		eSet(eObject, "anECharacterObject", (char)0);
		ocl.assertQueryEquals(eObject, 0, "anECharacterObject");
		ocl.assertQueryEquals(eObject, 1, "anECharacterObject + 1");
		ocl.assertQueryEquals(eObject, 0, "self.anECharacterObject");
		ocl.assertQueryEquals(eObject, 1, "self.anECharacterObject + 1");
		//
		ocl.assertQueryEquals(eObject, 0, "anEDouble");
		ocl.assertQueryEquals(eObject, 1, "anEDouble + 1");
		ocl.assertQueryEquals(eObject, 0, "self.anEDouble");
		ocl.assertQueryEquals(eObject, 1, "self.anEDouble + 1");
		//
		eSet(eObject, "anEDoubleObject", (double)0);
		ocl.assertQueryEquals(eObject, 0, "anEDoubleObject");
		ocl.assertQueryEquals(eObject, 1, "anEDoubleObject + 1");
		ocl.assertQueryEquals(eObject, 0, "self.anEDoubleObject");
		ocl.assertQueryEquals(eObject, 1, "self.anEDoubleObject + 1");
		//
		ocl.assertQueryEquals(eObject, 0, "anEFloat");
		ocl.assertQueryEquals(eObject, 1, "anEFloat + 1");
		ocl.assertQueryEquals(eObject, 0, "self.anEFloat");
		ocl.assertQueryEquals(eObject, 1, "self.anEFloat + 1");
		//
		eSet(eObject, "anEFloatObject", (float)0);
		ocl.assertQueryEquals(eObject, 0, "anEFloatObject");
		ocl.assertQueryEquals(eObject, 1, "anEFloatObject + 1");
		ocl.assertQueryEquals(eObject, 0, "self.anEFloatObject");
		ocl.assertQueryEquals(eObject, 1, "self.anEFloatObject + 1");
		//
		ocl.assertQueryEquals(eObject, 0, "anEInt");
		ocl.assertQueryEquals(eObject, 1, "anEInt + 1");
		ocl.assertQueryEquals(eObject, 0, "self.anEInt");
		ocl.assertQueryEquals(eObject, 1, "self.anEInt + 1");
		//
		eSet(eObject, "anEIntegerObject", 0);
		ocl.assertQueryEquals(eObject, 0, "anEIntegerObject");
		ocl.assertQueryEquals(eObject, 1, "anEIntegerObject + 1");
		ocl.assertQueryEquals(eObject, 0, "self.anEIntegerObject");
		ocl.assertQueryEquals(eObject, 1, "self.anEIntegerObject + 1");
		//
		ocl.assertQueryEquals(eObject, 0, "anELong");
		ocl.assertQueryEquals(eObject, 1, "anELong + 1");
		ocl.assertQueryEquals(eObject, 0, "self.anELong");
		ocl.assertQueryEquals(eObject, 1, "self.anELong + 1");
		//
		eSet(eObject, "anELongObject", (long)0);
		ocl.assertQueryEquals(eObject, 0, "anELongObject");
		ocl.assertQueryEquals(eObject, 1, "anELongObject + 1");
		ocl.assertQueryEquals(eObject, 0, "self.anELongObject");
		ocl.assertQueryEquals(eObject, 1, "self.anELongObject + 1");
		//
		ocl.assertQueryEquals(eObject, 0, "anEShort");
		ocl.assertQueryEquals(eObject, 1, "anEShort + 1");
		ocl.assertQueryEquals(eObject, 0, "self.anEShort");
		ocl.assertQueryEquals(eObject, 1, "self.anEShort + 1");
		//
		eSet(eObject, "anEShortObject", (short)0);
		ocl.assertQueryEquals(eObject, 0, "anEShortObject");
		ocl.assertQueryEquals(eObject, 1, "anEShortObject + 1");
		ocl.assertQueryEquals(eObject, 0, "self.anEShortObject");
		ocl.assertQueryEquals(eObject, 1, "self.anEShortObject + 1");
		//
		eSet(eObject, "anEString", "");
		ocl.assertQueryEquals(eObject, "", "anEString");
		ocl.assertQueryEquals(eObject, "1", "anEString + '1'");
		ocl.assertQueryEquals(eObject, "", "self.anEString");
		ocl.assertQueryEquals(eObject, "1", "self.anEString + '1'");
		ocl.dispose();
	}

	/**
	 * Test implicit collect and oclAsSet() therein. Inspired by Bug 351512.
	 */
	@Test public void test_oclAsSet_351512() throws Exception {
		TestOCL ocl = createOCL();
		IdResolver idResolver = ocl.getIdResolver();
		String metamodelText =
				"import ecore : 'http://www.eclipse.org/emf/2002/Ecore#/';\n" +
						"package pkg : pkg = 'pkg' {\n" +
						"  class A {\n" +
						"    property bs : B[*] {ordered,unique};\n" +
						"    attribute name : String;\n" +
						"  }\n" +
						"  class B {\n" +
						"    property c : C;\n" +
						"  }\n" +
						"  class C {\n" +
						"    attribute name : String;\n" +
						"  }\n" +
						"}\n";
		Resource metamodel = cs2ecore(ocl.getEnvironmentFactory(), metamodelText, getTestFileURI("test.ecore"));
		EPackage ePackage = (EPackage) metamodel.getContents().get(0);
		EClass aClass = ClassUtil.nonNullState((EClass) ePackage.getEClassifier("A"));
		EClass bClass = (EClass) ePackage.getEClassifier("B");
		EClass cClass = (EClass) ePackage.getEClassifier("C");
		EObject c1 = eCreate(cClass);
		eSet(c1, "name", "c1");
		EObject c2 = eCreate(cClass);
		eSet(c2, "name", "c2");
		EObject b1 = eCreate(bClass);
		eSet(b1, "c", c1);
		EObject b2 = eCreate(bClass);
		eSet(b2, "c", c2);
		EObject a = eCreate(aClass);
		eAdd(a, "bs", b1);
		eAdd(a, "bs", b2);
		//
		Object b1_value = idResolver.boxedValueOf(b1);
		Object b2_value = idResolver.boxedValueOf(b2);
		Object c1_value = idResolver.boxedValueOf(c1);
		Object c2_value = idResolver.boxedValueOf(c2);
		Value orderedSet_b1_b2 = idResolver.createOrderedSetOfEach(TypeId.ORDERED_SET.getSpecializedId(TypeId.OCL_ANY), b1_value, b2_value);
		Value sequence_c1_c2 = idResolver.createSequenceOfEach(TypeId.SEQUENCE.getSpecializedId(TypeId.OCL_ANY), c1_value, c2_value);
		//
		ocl.assertQueryEquals(a, orderedSet_b1_b2, "bs");
		ocl.assertQueryEquals(a, sequence_c1_c2, "bs?.c");
		ocl.assertQueryEquals(a, sequence_c1_c2, "bs?.c.oclAsSet()");
		ocl.assertQueryResults(a, "Sequence{'c1','c2'}", "bs?.c?.name");
		ocl.assertQueryResults(a, "Sequence{'c1','c2'}", "self.bs?.c?.name");
		ocl.assertQueryResults(a, "Sequence{'c1','c2'}", "bs?.c?.oclAsSet().name");
		ocl.assertQueryResults(a, "Sequence{'c1','c2'}", "bs?.c?.oclAsSet()->collect(name)");	// Test for Bug 351512
		ocl.assertQueryResults(a, "Sequence{'c1','c2'}", "bs?.c?->collect(oclAsSet()).name");
		ocl.assertQueryResults(a, "Sequence{'c1','c2'}", "bs?.c?->collect(j : C | j.oclAsSet()).name");
		ocl.assertQueryResults(a, "Sequence{'c1','c2'}", "bs?->collect(i : B | i.c)?->collect(j : C | j.oclAsSet())->collect(k : C | k.name)");
		ocl.dispose();
	}

	/**
	 * Test container/containment navigation.
	 */
	@Test public void test_containment_navigation() throws Exception {
		String metamodelText =
				"package containment : pfx = 'http://containment'\n" +
						"{\n" +
						"	class Parent\n" +
						"	{\n" +
						"		property child1 : Child1[?] { ordered composes };\n" +
						"		property child2#parent : Child2[?] { ordered composes };\n" +
						"		property children1 : Children1[*] { ordered composes };\n" +
						"		property children2#parent : Children2[*] { ordered composes };\n" +
						"	}\n" +
						"	class Child1\n" +
						"	{\n" +
						"	}\n" +
						"	class Child2\n" +
						"	{\n" +
						"		property parent#child2 : Parent[?] { ordered };\n" +
						"	}\n" +
						"	class Children1\n" +
						"	{\n" +
						"	}\n" +
						"	class Children2\n" +
						"	{\n" +
						"		property parent#children2 : Parent[?] { ordered };\n" +
						"	}\n" +
						"}\n";
		OCL ocl1 = createOCL();
		Resource metamodel = cs2ecore(ocl1.getEnvironmentFactory(), metamodelText, getTestFileURI("test.ecore"));
		EPackage ePackage = (EPackage) metamodel.getContents().get(0);
		EClass parentClass = ClassUtil.nonNullState((EClass) ePackage.getEClassifier("Parent"));
		EClass child1Class = ClassUtil.nonNullState((EClass) ePackage.getEClassifier("Child1"));
		EClass child2Class = ClassUtil.nonNullState((EClass) ePackage.getEClassifier("Child2"));
		EClass children1Class = ClassUtil.nonNullState((EClass) ePackage.getEClassifier("Children1"));
		EClass children2Class = ClassUtil.nonNullState((EClass) ePackage.getEClassifier("Children2"));
		EObject parent = eCreate(parentClass);
		EObject child1 = eCreate(child1Class);
		EObject child2 = eCreate(child2Class);
		EObject children1 = eCreate(children1Class);
		EObject children2 = eCreate(children2Class);
		eSet(parent, "child1", child1);
		eSet(parent, "child2", child2);
		eAdd(parent, "children1", children1);
		eAdd(parent, "children2", children2);
		ThreadLocalExecutor.resetEnvironmentFactory();

		TestOCL ocl = createOCL();
		IdResolver idResolver = ocl.getIdResolver();

		org.eclipse.ocl.pivot.Class parentType = idResolver.getType(parentClass);
		org.eclipse.ocl.pivot.Class child1Type = idResolver.getType(child1Class);
		org.eclipse.ocl.pivot.Class child2Type = idResolver.getType(child2Class);
		org.eclipse.ocl.pivot.Class children1Type = idResolver.getType(children1Class);
		org.eclipse.ocl.pivot.Class children2Type = idResolver.getType(children2Class);
		//
		OrderedSetValue kids1 = idResolver.createOrderedSetOfEach(TypeId.ORDERED_SET.getSpecializedId(children1Type.getTypeId()), children1);
		OrderedSetValue kids2 = idResolver.createOrderedSetOfEach(TypeId.ORDERED_SET.getSpecializedId(children2Type.getTypeId()), children2);
		//
		ocl.assertSemanticErrorQuery(parentType, "parent", PivotMessagesInternal.UnresolvedElement_ERROR_, "", "parent");
		ocl.assertSemanticErrorQuery(parentType, "self.parent", PivotMessagesInternal.UnresolvedProperty_ERROR_, parentType, "parent");
		ocl.assertQueryEquals(parent, parentType, "Parent");
		ocl.assertSemanticErrorQuery(parentType, "self.Parent", PivotMessagesInternal.UnresolvedProperty_ERROR_, parentType, "Parent");
		ocl.assertQueryEquals(parent, child1, "child1");
		ocl.assertQueryEquals(parent, child1, "self.child1");
		ocl.assertQueryEquals(parent, child1Type, "Child1");
		ocl.assertSemanticErrorQuery(parentType, "self.Child1", PivotMessagesInternal.UnresolvedProperty_ERROR_, parentType, "Child1");
		ocl.assertQueryEquals(parent, child2, "child2");
		ocl.assertQueryEquals(parent, child2, "self.child2");
		ocl.assertQueryEquals(parent, child2Type, "Child2");
		ocl.assertSemanticErrorQuery(parentType, "self.Child2", PivotMessagesInternal.UnresolvedProperty_ERROR_, parentType, "Child2");
		ocl.assertQueryEquals(parent, kids1, "children1");
		ocl.assertQueryEquals(parent, kids1, "self.children1");
		ocl.assertQueryEquals(parent, children1Type, "Children1");
		ocl.assertSemanticErrorQuery(parentType, "self.Children1", PivotMessagesInternal.UnresolvedProperty_ERROR_, parentType, "Children1");
		ocl.assertQueryEquals(parent, kids2, "children2");
		ocl.assertQueryEquals(parent, kids2, "self.children2");
		ocl.assertQueryEquals(parent, children2Type, "Children2");
		ocl.assertSemanticErrorQuery(parentType, "self.Children2", PivotMessagesInternal.UnresolvedProperty_ERROR_, parentType, "Children2");
		//
		ocl.assertSemanticErrorQuery(child1Type, "parent", PivotMessagesInternal.UnresolvedElement_ERROR_, "", "parent");
		ocl.assertQueryEquals(child2, parentType, "Parent");
		ocl.assertSemanticErrorQuery(child1Type, "self.parent", PivotMessagesInternal.UnresolvedProperty_ERROR_, child1Type, "parent");
		ocl.assertQueryEquals(child1, parent, "self.Parent");
		//
		ocl.assertQueryEquals(child2, parent, "parent");
		ocl.assertQueryEquals(child2, parentType, "Parent");
		ocl.assertQueryEquals(child2, parent, "self.parent");
		ocl.assertSemanticErrorQuery(child2Type, "self.Parent", PivotMessagesInternal.UnresolvedProperty_ERROR_, child2Type, "Parent");
		//
		ocl.assertSemanticErrorQuery(children1Type, "parent", PivotMessagesInternal.UnresolvedElement_ERROR_, "", "parent");
		ocl.assertQueryEquals(children1, parentType, "Parent");
		ocl.assertSemanticErrorQuery(children1Type, "self.parent", PivotMessagesInternal.UnresolvedProperty_ERROR_, children1Type, "parent");
		ocl.assertQueryEquals(children1, parent, "self.Parent");
		//
		ocl.assertQueryEquals(children2, parent, "parent");
		ocl.assertQueryEquals(children2, parentType, "Parent");
		ocl.assertQueryEquals(children2, parent, "self.parent");
		ocl.assertSemanticErrorQuery(children2Type, "self.Parent", PivotMessagesInternal.UnresolvedProperty_ERROR_, children2Type, "Parent");
		//
		ocl.assertQueryTrue(parent, "child1 = child1");
		ocl1.dispose();
		ocl.dispose();
	}

	/**
	 * Tests that boxed Enumerations are navigable
	 */
	@Test public void test_enumeration_navigation() throws Exception {
		TestOCL ocl = createOCL();
		if (!useCodeGen) {			// FIXME BUG 458359
			ocl.assertQueryResults(CompanyPackage.Literals.COMPANY_SIZE_KIND, "Sequence{'small','medium','large'}", "self.eLiterals.name");
			// FIXME the following needs the full UML model to vbe loaded otherwise $uml$ is not a defined root package id.
			//		UML2AS.initialize(resourceSet);
			//		ocl.assertQueryResults(UMLPackage.Literals.AGGREGATION_KIND, "Sequence{'none','composite'}", "self.eLiterals.name");
		}
		ocl.dispose();
	}

	/**
	 * Test that Ecore Maps are loaded for MapValue evaluation.
	 */
	@Test public void test_maps() throws Exception {
		TestOCL ocl = createOCLWithProjectMap();
		URI uri = URI.createPlatformResourceURI("org.eclipse.emf.ecore/model/Ecore.ecore", true);	// Java bariant has precedence
		EObject ePackage = ocl.getResourceSet().getEObject(uri.appendFragment("/"), true);
		//
		String expectedResultExpression = OCLGenModelUtil.INSTANCE.hasConstraintEAnnotations() ? "Set{'baseType','constraints','name'}" :  "Set{'baseType','name'}";
		ocl.assertQueryResults(ePackage, expectedResultExpression,
				"self.eClassifiers->collect(c | c.eAnnotations)->collect(a | a.details)->collect(m | m->collect(k with v | k))->asSet()");
		//	ocl.assertQueryResults(ePackage, "Set{'baseType','constraints','name','suppressedIsSetVisibility','suppressedUnsetVisibility'}",
		//	"ecore::EAnnotation.allInstances().details->collect(m | m->collect(k with v | k))->asSet()");
		ocl.dispose();
	}

	/**
	 * Test multi-container navigation inspired by Bug 394152.
	 */
	@Test public void test_multi_container_394152() throws Exception {
		TestOCL ocl = createOCL();
		String metamodelText =
				"package bug394152 : pfx = 'http://bug394152'\n" +
						"{\n" +
						"	class Parent\n" +
						"	{\n" +
						"		property left#left : Child[+] { ordered composes };\n" +
						"		property right#right : Child[+] { ordered composes };\n" +
						"	}\n" +
						"	class Child\n" +
						"	{\n" +
						"		property left#left : Parent[?] { ordered };\n" +
						"		property right#right : Parent[?] { ordered };\n" +
						"	}\n" +
						"}\n";
		Resource metamodel = cs2ecore(ocl.getEnvironmentFactory(), metamodelText, getTestFileURI("test.ecore"));
		EPackage ePackage = (EPackage) metamodel.getContents().get(0);
		EClass parentClass = (EClass) ePackage.getEClassifier("Parent");
		EClass childClass = ClassUtil.nonNullState((EClass) ePackage.getEClassifier("Child"));
		EObject parent = eCreate(parentClass);
		EObject leftChild = eCreate(childClass);
		EObject rightChild = eCreate(childClass);
		eAdd(parent, "left", leftChild);
		eAdd(parent, "right", rightChild);
		//
		ocl.assertQueryEquals(leftChild, parent, "left");
		ocl.assertQueryEquals(leftChild, null, "right");
		ocl.assertQueryEquals(rightChild, null, "left");
		ocl.assertQueryEquals(rightChild, parent, "right");
		ocl.dispose();
	}

	@Test public void test_unified_types_411441() throws Exception {
		TestOCL ocl = createOCL();
		ocl.assertQueryTrue(null, "let x : Collection(Type) = Set{Integer,Real} in x?->forAll(x : Type | x.name.indexOf('e') > 0)");
		ocl.assertQueryTrue(null, "let x : Type[*] = Bag{Integer,Real} in x?->forAll(x : Type | x.name.indexOf('e') > 0)");
		ocl.assertValidationErrorQuery(null, "let x : Type[*] = Set{Integer,Real} in x?->forAll(x : Type | x.name.indexOf('e') > 0)",
			PivotMessages.ValidationConstraintIsNotSatisfied_ERROR_, "LetVariable::CompatibleTypeForInitializer", "x : Bag(Type[*|?]) = Set{Integer, Real}");
		ocl.assertQueryTrue(null, "let x : Collection(Collection(Type[*])) = Set{Bag{Integer,Real},Bag{Boolean}} in x?->forAll(x : Collection(Type[*]) | x->size() > 0)");
		ocl.assertValidationErrorQuery(null, "let x : Collection(Collection(Type[*])) = Set{Bag{Integer,Real},Bag{Boolean}} in x?->forAll(x : Type | x->size() > 0)",
			PivotMessages.ValidationConstraintIsNotSatisfied_ERROR_, "IteratorExp::IteratorTypeIsSourceElementType", "x?->forAll(x : Type[1] | x.oclAsSet()->size().>(0))");
		ocl.assertValidationErrorQuery(null, "let x : Collection(Type) = Set{Integer,Real} in x?->forAll(x : Type[*] | x->size() > 0)",
			PivotMessages.ValidationConstraintIsNotSatisfied_ERROR_, "IteratorExp::IteratorTypeIsSourceElementType", "x?->forAll(x : Bag(Type[*|?]) | x->size().>(0))");
		ocl.dispose();
	}

	@Test
	public void test_ecore_collection_equality() throws Exception {
		TestOCL ocl = createOCL();
		//
		EList<EStructuralFeature> eStructuralFeatures = EcorePackage.Literals.ECLASS.getEStructuralFeatures();
		ocl.assertQueryOCLEquals(EcorePackage.Literals.ECLASS, eStructuralFeatures, "self.eStructuralFeatures");
		//
		ocl.assertQueryOCLNotEquals(EcorePackage.Literals.ECLASS, eStructuralFeatures, "self.eStructuralFeatures->asBag()");
		ocl.assertQueryOCLEquals(EcorePackage.Literals.ECLASS, eStructuralFeatures, "self.eStructuralFeatures->asOrderedSet()");
		ocl.assertQueryOCLNotEquals(EcorePackage.Literals.ECLASS, eStructuralFeatures, "self.eStructuralFeatures->asSequence()");
		ocl.assertQueryOCLNotEquals(EcorePackage.Literals.ECLASS, eStructuralFeatures, "self.eStructuralFeatures->asSet()");
		//
		ocl.assertQueryOCLNotEquals(EcorePackage.Literals.ECLASS, new HashSet<>(eStructuralFeatures), "self.eStructuralFeatures->asBag()");
		ocl.assertQueryOCLNotEquals(EcorePackage.Literals.ECLASS, new HashSet<>(eStructuralFeatures), "self.eStructuralFeatures->asOrderedSet()");
		ocl.assertQueryOCLNotEquals(EcorePackage.Literals.ECLASS, new HashSet<>(eStructuralFeatures), "self.eStructuralFeatures->asSequence()");
		ocl.assertQueryOCLEquals(EcorePackage.Literals.ECLASS, new HashSet<>(eStructuralFeatures), "self.eStructuralFeatures->asSet()");
		//
		ocl.assertQueryOCLNotEquals(EcorePackage.Literals.ECLASS, new ArrayList<>(eStructuralFeatures), "self.eStructuralFeatures->asBag()");
		ocl.assertQueryOCLNotEquals(EcorePackage.Literals.ECLASS, new ArrayList<>(eStructuralFeatures), "self.eStructuralFeatures->asOrderedSet()");
		ocl.assertQueryOCLEquals(EcorePackage.Literals.ECLASS, new ArrayList<>(eStructuralFeatures), "self.eStructuralFeatures->asSequence()");
		ocl.assertQueryOCLNotEquals(EcorePackage.Literals.ECLASS, new ArrayList<>(eStructuralFeatures), "self.eStructuralFeatures->asSequence()->reverse()");
		ocl.assertQueryOCLNotEquals(EcorePackage.Literals.ECLASS, new ArrayList<>(eStructuralFeatures), "self.eStructuralFeatures->asSet()");
		//
		ocl.assertQueryOCLNotEquals(EcorePackage.Literals.ECLASS, new LinkedHashSet<>(eStructuralFeatures), "self.eStructuralFeatures->asBag()");
		ocl.assertQueryOCLEquals(EcorePackage.Literals.ECLASS, new LinkedHashSet<>(eStructuralFeatures), "self.eStructuralFeatures->asOrderedSet()");
		ocl.assertQueryOCLNotEquals(EcorePackage.Literals.ECLASS, new LinkedHashSet<>(eStructuralFeatures), "self.eStructuralFeatures->asOrderedSet()->reverse()");
		ocl.assertQueryOCLNotEquals(EcorePackage.Literals.ECLASS, new LinkedHashSet<>(eStructuralFeatures), "self.eStructuralFeatures->asSequence()");
		ocl.assertQueryOCLNotEquals(EcorePackage.Literals.ECLASS, new LinkedHashSet<>(eStructuralFeatures), "self.eStructuralFeatures->asSet()");
		//
		Iterable<@NonNull EStructuralFeature> eStructuralFeatures2 = ClassUtil.nullFree(eStructuralFeatures);
		ocl.assertQueryOCLEquals(EcorePackage.Literals.ECLASS, new BagImpl<>(eStructuralFeatures2), "self.eStructuralFeatures->asBag()");
		ocl.assertQueryOCLNotEquals(EcorePackage.Literals.ECLASS, new BagImpl<>(eStructuralFeatures2), "self.eStructuralFeatures->asOrderedSet()");
		ocl.assertQueryOCLNotEquals(EcorePackage.Literals.ECLASS, new BagImpl<>(eStructuralFeatures2), "self.eStructuralFeatures->asSequence()");
		ocl.assertQueryOCLNotEquals(EcorePackage.Literals.ECLASS, new BagImpl<>(eStructuralFeatures2), "self.eStructuralFeatures->asSet()");
		//
		ocl.assertQueryOCLEquals(EcorePackage.Literals.ECLASS, org.eclipse.ocl.util.CollectionUtil.createNewBag(eStructuralFeatures), "self.eStructuralFeatures->asBag()");
		ocl.assertQueryOCLNotEquals(EcorePackage.Literals.ECLASS, org.eclipse.ocl.util.CollectionUtil.createNewBag(eStructuralFeatures), "self.eStructuralFeatures->asOrderedSet()");
		ocl.assertQueryOCLNotEquals(EcorePackage.Literals.ECLASS, org.eclipse.ocl.util.CollectionUtil.createNewBag(eStructuralFeatures), "self.eStructuralFeatures->asSequence()");
		ocl.assertQueryOCLNotEquals(EcorePackage.Literals.ECLASS, org.eclipse.ocl.util.CollectionUtil.createNewBag(eStructuralFeatures), "self.eStructuralFeatures->asSet()");
		//
		ocl.dispose();
	}

	@Test
	public void test_ecore_number_equality() throws Exception {
		TestOCL ocl = createOCL();
		//
		ocl.assertQueryOCLEquals(null, Byte.valueOf((byte)255), "-1");
		ocl.assertQueryOCLEquals(null, Character.valueOf((char)255), "255");
		ocl.assertQueryOCLEquals(null, Short.valueOf((short)255), "255");
		ocl.assertQueryOCLEquals(null, Integer.valueOf(255), "255");
		ocl.assertQueryOCLEquals(null, Long.valueOf(255), "255");
		ocl.assertQueryOCLEquals(null, BigInteger.valueOf(255), "255");
		ocl.assertQueryOCLEquals(null, Float.valueOf(255), "255");
		ocl.assertQueryOCLEquals(null, Double.valueOf(255), "255");
		ocl.assertQueryOCLEquals(null, BigDecimal.valueOf(255), "255");
		//
		ocl.assertQueryOCLNotEquals(null, Byte.valueOf((byte)255), "254");
		ocl.assertQueryOCLNotEquals(null, Character.valueOf((char)255), "254");
		ocl.assertQueryOCLNotEquals(null, Short.valueOf((short)255), "254");
		ocl.assertQueryOCLNotEquals(null, Integer.valueOf(255), "254");
		ocl.assertQueryOCLNotEquals(null, Long.valueOf(255), "254");
		ocl.assertQueryOCLNotEquals(null, BigInteger.valueOf(255), "254");
		ocl.assertQueryOCLNotEquals(null, Float.valueOf(255), "255.1");
		ocl.assertQueryOCLNotEquals(null, Double.valueOf(255), "255.1");
		ocl.assertQueryOCLNotEquals(null, BigDecimal.valueOf(255), "255.1");
		//
		ocl.dispose();
	}
}
