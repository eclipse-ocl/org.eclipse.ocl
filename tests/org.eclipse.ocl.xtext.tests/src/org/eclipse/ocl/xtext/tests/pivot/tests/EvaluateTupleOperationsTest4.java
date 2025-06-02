/*******************************************************************************
 * Copyright (c) 2014, 2022 Eclipse Modeling Project and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/

package org.eclipse.ocl.xtext.tests.pivot.tests;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.ids.CollectionTypeId;
import org.eclipse.ocl.pivot.ids.IdManager;
import org.eclipse.ocl.pivot.ids.PartId;
import org.eclipse.ocl.pivot.ids.TupleTypeId;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.messages.PivotMessagesInternal;
import org.eclipse.ocl.pivot.messages.PivotMessages;
import org.eclipse.ocl.pivot.utilities.OCL;
import org.eclipse.ocl.pivot.utilities.ValueUtil;
import org.eclipse.ocl.pivot.values.SetValue;
import org.eclipse.ocl.pivot.values.TupleValue;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/**
 * Tests for Tuple operations.
 */
@RunWith(value = Parameterized.class)
public class EvaluateTupleOperationsTest4 extends PivotTestSuite
{
	@Parameters
	public static Collection<Object[]> data() {
		Object[][] data = new Object[][]{{false}, {true}};
		return Arrays.asList(data);
	}

	public EvaluateTupleOperationsTest4(boolean useCodeGen) {
		super(useCodeGen);
	}

	@Override
	protected @NonNull TestOCL createOCL() {
		return new TestOCL(getTestFileSystem(), getTestPackageName(), getName(), useCodeGen ? getProjectMap() : OCL.NO_PROJECTS, null);
	}

	@Override
	protected @NonNull String getTestPackageName() {
		return "EvaluateTupleOperations";
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

	@Test public void testTupleType_Collections() {
		TestOCL ocl = createOCL();
		PartId aPartId = IdManager.getPartId(0, "a", TypeId.INTEGER, true);
		@SuppressWarnings("null") TupleTypeId aTupleTypeId = IdManager.getTupleTypeId("Tuple", Collections.singletonList(aPartId));
		PartId aPartIdOpt = IdManager.getPartId(0, "a", TypeId.INTEGER, false);
		@SuppressWarnings("null") TupleTypeId aTupleTypeIdOpt = IdManager.getTupleTypeId("Tuple", Collections.singletonList(aPartIdOpt));
		PartId aPartIdVoid = IdManager.getPartId(0, "a", TypeId.OCL_VOID, false);
		@SuppressWarnings("null") TupleTypeId aTupleTypeIdVoid = IdManager.getTupleTypeId("Tuple", Collections.singletonList(aPartIdVoid));
		PartId bPartId = IdManager.getPartId(0, "b", TypeId.INTEGER, true);
		@SuppressWarnings("null") TupleTypeId bTupleTypeId = IdManager.getTupleTypeId("Tuple", Collections.singletonList(bPartId));
		//
		Map<@NonNull PartId, @Nullable Object> aValues_3 = new HashMap<>();
		aValues_3.put(aPartId, ValueUtil.integerValueOf(3));
		TupleValue aValue_3 = ValueUtil.createTupleValue(aTupleTypeId, aValues_3);
		//
		Map<@NonNull PartId, @Nullable Object> aValues_4 = new HashMap<>();
		aValues_4.put(aPartId, ValueUtil.integerValueOf(4));
		TupleValue aValue_4 = ValueUtil.createTupleValue(aTupleTypeId, aValues_4);
		//
		Map<@NonNull PartId, @Nullable Object> aValues_null = new HashMap<>();
		aValues_null.put(aPartId, null);
		TupleValue aValue_null = ValueUtil.createTupleValue(aTupleTypeIdOpt, aValues_null);
		TupleValue aValuev_null = ValueUtil.createTupleValue(aTupleTypeIdVoid, aValues_null);
		//
		Map<@NonNull PartId, @Nullable Object> bValues_4 = new HashMap<>();
		bValues_4.put(bPartId, ValueUtil.integerValueOf(4));
		TupleValue bValue_4 = ValueUtil.createTupleValue(bTupleTypeId, bValues_4);
		//
		CollectionTypeId collectionTypeId = TypeId.SET.getSpecializedId(TypeId.OCL_ANY);
		SetValue setValue_a3a4 = ValueUtil.createSetOfEach(collectionTypeId, aValue_3, aValue_4);
		SetValue setValue_a3a_null = ValueUtil.createSetOfEach(collectionTypeId, aValue_3, aValue_null);
		SetValue setValue_a3av_null = ValueUtil.createSetOfEach(collectionTypeId, aValue_3, aValuev_null);
		SetValue setValue_a3b4 = ValueUtil.createSetOfEach(collectionTypeId, aValue_3, bValue_4);
	//	SetValue setValue2 = ValueUtil.createSetOfEach(collectionTypeId);
		ocl.assertValidationErrorQuery(null, "let s : Set(Tuple(a:Integer[1])) = Set{Tuple{a = 3}, Tuple{a:Integer[?] = null}} in s",
			PivotMessages.ValidationConstraintIsNotSatisfied_ERROR_, "LetVariable::CompatibleTypeForInitializer",
				"s : Set(Tuple(a:Integer[1])) = Set{Tuple{a:Integer[1] = 3}, Tuple{a:Integer[?] = null}}");
		ocl.assertQueryEquals(null, setValue_a3a_null, "let s : Set(Tuple(a:Integer[?])) = Set{Tuple{a = 3}, Tuple{a:Integer = null}} in s");
		ocl.assertValidationErrorQuery(null, "let s : Set(Tuple(a:Integer[1])) = Set{Tuple{a = 3}, Tuple{a:Integer[1] = null}} in s",
			PivotMessages.ValidationConstraintIsNotSatisfied_ERROR_, "TupleLiteralPart::CompatibleNullityForInitializer",
				"a:Integer[1] = null");
		ocl.assertValidationErrorQuery(null, "let s : Set(Tuple(a:Integer[1])) = Set{Tuple{a = 3}, Tuple{a:Integer[?] = null}} in s",
			PivotMessages.ValidationConstraintIsNotSatisfied_ERROR_, "TupleLiteralPart::CompatibleNullityForInitializer",
				"a:Integer[1] = null");
		ocl.assertQueryEquals(null, setValue_a3av_null, "let s : Set(Tuple(a:Integer[?])) = Set{Tuple{a = 3}, Tuple{a = null}} in s");
		ocl.assertQueryEquals(null, setValue_a3av_null, "let s : Set(Tuple(a:Integer[1])) = Set{Tuple{a = 3}, Tuple{a = null}} in s");


		ocl.assertQueryEquals(null, setValue_a3b4, "let s : Set(OclAny) = Set{Tuple{a = 3}, Tuple{b = 4}} in s");
		ocl.assertQueryEquals(null, setValue_a3a4, "let s : Set(Tuple(a:Integer)) = Set{Tuple{a = 3}, Tuple{a = 4}} in s");
		ocl.assertQueryEquals(null, setValue_a3a4, "let s : Set(Tuple(a:Integer[?])) = Set{Tuple{a = 3}, Tuple{a = 4}} in s");
		ocl.assertQueryEquals(null, setValue_a3a4, "let s : Set(Tuple(a:Integer[1])) = Set{Tuple{a = 3}, Tuple{a = 4}} in s");
//		ocl.assertQueryEquals(null, setValue_a3a_null, "let s : Set(Tuple(a:Integer[1])) = Set{Tuple{a = 3}, Tuple{a = null}} in s");
// XXX
		ocl.assertQueryEquals(null, setValue_a3b4, "Set{Tuple{a = 3}, Tuple{b = 4}, Tuple{a = 3}}");						// BUG 4404404
		ocl.assertQueryEquals(null, setValue_a3b4, "Set{Tuple{a = 3}, Tuple{b = 4}, Tuple{a = 3}}");						// BUG 4404404
		ocl.assertQueryEquals(null, setValue_a3b4, "let s : Set(Tuple(a:Integer[1])) = Set{Tuple{a = 3}, Tuple{b = 4}} in s");
		ocl.assertQueryEquals(null, setValue_a3b4, "let s : Set(Tuple(a:Integer[?])) = Set{Tuple{a = 3}, Tuple{b = 4}} in s");
		ocl.assertQueryEquals(null, setValue_a3b4, "let s : Set(Tuple(a:Integer)) = Set{Tuple{a = 3}, Tuple{b = 4}} in s");
		ocl.assertValidationErrorQuery(null, "let s : Set(Tuple(a:Integer)) = Set{Tuple{a = 3}, Tuple{b = 4}} in s",
			PivotMessages.ValidationConstraintIsNotSatisfied_ERROR_, "LetVariable::CompatibleTypeForInitializer",
				"s : Set(Tuple(a:Integer[?])) = Set{Tuple{a : Integer[1] = 3}, Tuple{b : Integer[1] = 4}}");
		ocl.assertQueryEquals(null, setValue_a3b4, "let s : Set(OclAny) = Set{Tuple{a = 3}, Tuple{b = 4}} in s");
		ocl.dispose();
	}

	@Test public void testTupleType_Conformance() {
		TestOCL ocl = createOCL();
		ocl.assertSemanticErrorQuery(null, "let s : Sequence(OclAny) = Sequence{Tuple{a = 3}, Tuple{b = 4}} in s->first().a", PivotMessagesInternal.UnresolvedProperty_ERROR_, "OclAny", "a");
		ocl.assertQueryEquals(null, 3, "let s : Sequence(OclAny) = Sequence{Tuple{a = 3}, Tuple{b = 4}} in s->first().oclAsType(Tuple(a:Integer)).a");
		// BUG 440453		ocl.assertQueryEquals(null, 3, "let s : Sequence(OclAny) = Sequence{Tuple{a = 3}, Tuple{b = 4}} in s->first().oclAsType(Tuple(b:UnlimitedNatural)).b");
		//
		ocl.dispose();
	}

	@Test public void testTupleType_Iterations() {
		TestOCL ocl = createOCL();
		ocl.assertQueryTrue(null, "let s = Set{1..100} in let t = s->collect(i|Tuple{x=i}) in s->collect(i | t->select(x = i)).x->asSet() = s");
		//
		ocl.dispose();
	}

	@Test public void testTupleType_Equals() {
		TestOCL ocl = createOCL();
		ocl.assertQueryTrue(null, "Tuple{a = 3, b = Tuple{a = '3', b = Tuple{a = 3.1}}}.b = Tuple{b = 3, a = Tuple{a = '3', b = Tuple{a = 3.1}}}.a");
		//
		ocl.assertQueryTrue(null, "Tuple{a = 3, b = '4'} = Tuple{a = 3, b = '4'}");
		ocl.assertQueryTrue(null, "Tuple{a = 3, b = Tuple{a = '3', b = Tuple{a = 3.1}}} = Tuple{a = 3, b = Tuple{a = '3', b = Tuple{a = 3.1}}}");
		ocl.assertQueryTrue(null, "Tuple{a = 3, b = Tuple{a = '3', b = Tuple{a = 3.1}}}.b = Tuple{b = 3, a = Tuple{a = '3', b = Tuple{a = 3.1}}}.a");
		ocl.assertQueryTrue(null, "Tuple{a = 3, b = Tuple{a = '3', b = Tuple{a = 3.1}}}.b.b = Tuple{b = 3, a = Tuple{b = '3', a = Tuple{a = 3.1}}}.a.a");
		ocl.assertQueryTrue(null, "Tuple{a = 3, b = Tuple{a = '3', b = Tuple{a = 3.1}}}.b.b.a = Tuple{b = 3, a = Tuple{b = '3', a = Tuple{a = 3.1}}}.a.a.a");
		//
		ocl.assertQueryFalse(null, "Tuple{a = 3, b = '4'} = Tuple{b = 3, a = '4'}");
		ocl.assertQueryFalse(null, "Tuple{a = 3, b = Tuple{a = '3', b = Tuple{a = 3.1}}} = Tuple{a = 3, b = Tuple{a = '3', b = Tuple{b = 3.1}}}");
		ocl.assertQueryFalse(null, "Tuple{a = 3, b = Tuple{a = '3', b = Tuple{a = 3.1}}} = Tuple{a = 3, b = Tuple{a = '3', b = Tuple{b = 3.2}}}");
		ocl.assertQueryFalse(null, "Tuple{a = 3, b = Tuple{a = '3', b = Tuple{a = 3.1}}} = Tuple{a = 3, b = Tuple{a = '3', b = Tuple{b = '3.1'}}}");
		ocl.dispose();
	}

	@Test public void testTupleType_NotEquals() {
		TestOCL ocl = createOCL();
		ocl.assertQueryFalse(null, "Tuple{a = 3, b = '4'} <> Tuple{a = 3, b = '4'}");
		ocl.assertQueryFalse(null, "Tuple{a = 3, b = Tuple{a = '3', b = Tuple{a = 3.1}}} <> Tuple{a = 3, b = Tuple{a = '3', b = Tuple{a = 3.1}}}");
		ocl.assertQueryFalse(null, "Tuple{a = 3, b = Tuple{a = '3', b = Tuple{a = 3.1}}}.b <> Tuple{b = 3, a = Tuple{a = '3', b = Tuple{a = 3.1}}}.a");
		ocl.assertQueryFalse(null, "Tuple{a = 3, b = Tuple{a = '3', b = Tuple{a = 3.1}}}.b.b <> Tuple{b = 3, a = Tuple{b = '3', a = Tuple{a = 3.1}}}.a.a");
		ocl.assertQueryFalse(null, "Tuple{a = 3, b = Tuple{a = '3', b = Tuple{a = 3.1}}}.b.b.a <> Tuple{b = 3, a = Tuple{b = '3', a = Tuple{a = 3.1}}}.a.a.a");
		//
		ocl.assertQueryTrue(null, "Tuple{a = 3, b = '4'} <> Tuple{b = 3, a = '4'}");
		ocl.assertQueryTrue(null, "Tuple{a = 3, b = Tuple{a = '3', b = Tuple{a = 3.1}}} <> Tuple{a = 3, b = Tuple{a = '3', b = Tuple{b = 3.1}}}");
		ocl.assertQueryTrue(null, "Tuple{a = 3, b = Tuple{a = '3', b = Tuple{a = 3.1}}} <> Tuple{a = 3, b = Tuple{a = '3', b = Tuple{b = 3.2}}}");
		ocl.assertQueryTrue(null, "Tuple{a = 3, b = Tuple{a = '3', b = Tuple{a = 3.1}}} <> Tuple{a = 3, b = Tuple{a = '3', b = Tuple{b = '3.1'}}}");
		ocl.dispose();
	}

	@Test public void testTupleType_Parts() {
		TestOCL ocl = createOCL();
		//		ocl.assertSemanticErrorQuery2(null, "let s : Sequence(OclAny) = Sequence{Tuple{a = 3}, Tuple{b = 4}} in s->first().a", OCLMessages.UnresolvedProperty_ERROR_, "a", "OclAny");
		ocl.assertQueryEquals(null, 3, "Tuple{a = 3, b = '4'}.a");
		ocl.assertQueryEquals(null, 3, "Tuple{a = 3, b = Tuple{a = '3', b = Tuple{a = 3.1}}}.a");
		ocl.assertQueryEquals(null, "3", "Tuple{a = 3, b = Tuple{a = '3', b = Tuple{a = 3.1}}}.b.a");
		ocl.assertQueryEquals(null, 3.1, "Tuple{a = 3, b = Tuple{a = '3', b = Tuple{a = 3.1}}}.b.b.a");
		ocl.assertSemanticErrorQuery(null, "Tuple{}.a", "missing EOF at ''{''");
		ocl.assertSemanticErrorQuery(null, "Tuple{a = 3, b = '4'}.c", PivotMessagesInternal.UnresolvedProperty_ERROR_, "Tuple(a:Integer[1],b:String[1])", "c");
		// FIXME Duplicate parts warning		ocl.assertQueryEquals(null, 3, "Tuple{a = 1, a = 1}.a");
		ocl.dispose();
	}
}

