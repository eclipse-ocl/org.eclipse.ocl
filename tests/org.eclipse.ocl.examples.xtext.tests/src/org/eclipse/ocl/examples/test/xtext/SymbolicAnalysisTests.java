/*******************************************************************************
 * Copyright (c) 2020, 2021 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.test.xtext;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.pivot.tests.PivotTestCase;
import org.eclipse.ocl.examples.pivot.tests.TestOCL;
import org.eclipse.ocl.examples.xtext.tests.XtextTestCase;
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.IfExp;
import org.eclipse.ocl.pivot.IntegerLiteralExp;
import org.eclipse.ocl.pivot.InvalidLiteralExp;
import org.eclipse.ocl.pivot.IteratorExp;
import org.eclipse.ocl.pivot.IteratorVariable;
import org.eclipse.ocl.pivot.LetExp;
import org.eclipse.ocl.pivot.LetVariable;
import org.eclipse.ocl.pivot.LiteralExp;
import org.eclipse.ocl.pivot.Model;
import org.eclipse.ocl.pivot.NullLiteralExp;
import org.eclipse.ocl.pivot.NumericLiteralExp;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.OperationCallExp;
import org.eclipse.ocl.pivot.OppositePropertyCallExp;
import org.eclipse.ocl.pivot.PrimitiveType;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.PropertyCallExp;
import org.eclipse.ocl.pivot.StringLiteralExp;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.TypedElement;
import org.eclipse.ocl.pivot.VariableDeclaration;
import org.eclipse.ocl.pivot.VariableExp;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.cse.CSEElement;
import org.eclipse.ocl.pivot.internal.evaluation.BaseSymbolicEvaluationEnvironment;
import org.eclipse.ocl.pivot.internal.evaluation.SymbolicAnalysis;
import org.eclipse.ocl.pivot.internal.manager.MetamodelManagerInternal;
import org.eclipse.ocl.pivot.internal.symbolic.SymbolicSimpleReason;
import org.eclipse.ocl.pivot.internal.symbolic.SymbolicUnknownValue;
import org.eclipse.ocl.pivot.internal.symbolic.SymbolicUtil;
import org.eclipse.ocl.pivot.internal.symbolic.SymbolicVariableValue;
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal.EnvironmentFactoryInternalExtension;
import org.eclipse.ocl.pivot.internal.utilities.PivotUtilInternal;
import org.eclipse.ocl.pivot.messages.StatusCodes;
import org.eclipse.ocl.pivot.options.PivotValidationOptions;
import org.eclipse.ocl.pivot.resource.ASResource;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.OCL;
import org.eclipse.ocl.pivot.utilities.ParserException;
import org.eclipse.ocl.pivot.utilities.PivotConstants;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.utilities.TreeIterable;
import org.eclipse.ocl.pivot.utilities.UniqueList;
import org.eclipse.ocl.pivot.utilities.ValueUtil;
import org.eclipse.ocl.pivot.utilities.XMIUtil;
import org.eclipse.ocl.pivot.values.SequenceValue;
import org.eclipse.ocl.pivot.values.SetValue;
import org.eclipse.ocl.pivot.values.SymbolicValue;
import org.eclipse.ocl.xtext.base.cs2as.CS2AS;
import org.eclipse.ocl.xtext.base.utilities.BaseCSResource;
import org.eclipse.ocl.xtext.oclstdlib.scoping.JavaClassScope;

/**
 * Tests the isNull/isNotNull/isNotKnown results of the FlowAnalysis.
 */
@SuppressWarnings("null")
public class SymbolicAnalysisTests extends XtextTestCase
{
	@SuppressWarnings("serial")
	private static class IsDeadList extends ArrayList<@Nullable EObject> {}
	@SuppressWarnings("serial")
	private static class IsInvalidList extends ArrayList<@Nullable EObject> {}
	@SuppressWarnings("serial")
	private static class MayBeInvalidList extends ArrayList<@Nullable EObject> {}
	@SuppressWarnings("serial")
	private static class MayBeNullList extends ArrayList<@Nullable EObject> {}

	public class MyOCL extends TestOCL
	{
		private @NonNull Resource asResource;
		private @NonNull Operation asOperation;
		private org.eclipse.ocl.pivot.@NonNull Package deductionsPackage;
		private org.eclipse.ocl.pivot.@NonNull Class deductionsClass;
		private org.eclipse.ocl.pivot.@NonNull Class dummyClass;
		private @NonNull Property dummyClass_dummy;
		private @NonNull Property deductionsClass_mayBeNullDummy;

		public MyOCL(@NonNull String queryName, @NonNull String querySignature, @NonNull String queryBody) throws IOException {
			super(getTestFileSystem(), "SymbolicAnalysisTests", getName(), OCL.NO_PROJECTS, null);
			String testContext =
					"package deductions : ded = 'http://" + getTestName() + "/deductions'\n" +
							"{\n" +
							"  class Deductions\n" +
							"  {\n" +
							"    property mayBeNullDummy : Dummy[?];\n" +
							"    property nonNullDummy : Dummy[1];\n" +
							"    property x : Integer[?];\n" +
							"    operation " + querySignature + " {\nbody: " + queryBody + ";\n}\n" +
							"  }\n" +
							"  class Dummy\n" +
							"  {\n" +
							"    property mayBeNullDeductions : Deductions[?];\n" +
							"    property dummy : Dummy[?];\n" +
							"    operation func(i : Integer, j : Integer) : Dummy[?];\n" +
							"  }\n" +
							"}";
			String fileName = "SymbolicAnalysisTests_" + queryName;
			createFile(fileName + ".oclinecore", testContext);
			PivotUtilInternal.getEnvironmentFactory(null).setOption(PivotValidationOptions.PotentialInvalidResult, StatusCodes.Severity.IGNORE);
			this.asResource = doLoad_Concrete(fileName, "oclinecore");
			Model model = PivotUtil.getModel(asResource);
			this.deductionsPackage = ClassUtil.nonNullState(NameUtil.getNameable(model.getOwnedPackages(), "deductions"));
			this.deductionsClass = ClassUtil.nonNullState(NameUtil.getNameable(deductionsPackage.getOwnedClasses(), "Deductions"));
			this.dummyClass = ClassUtil.nonNullState(NameUtil.getNameable(deductionsPackage.getOwnedClasses(), "Dummy"));
			this.asOperation = ClassUtil.nonNullState(NameUtil.getNameable(deductionsClass.getOwnedOperations(), queryName));
			this.dummyClass_dummy = ClassUtil.nonNullState(NameUtil.getNameable(dummyClass.getOwnedProperties(), "dummy"));
			this.deductionsClass_mayBeNullDummy = ClassUtil.nonNullState(NameUtil.getNameable(deductionsClass.getOwnedProperties(), "mayBeNullDummy"));

			URI ecoreURI = getTestFileURI(fileName + ".ecore");
			@SuppressWarnings("unused")
			Resource ecoreResource = PivotTestCase.as2ecore(this, asResource, ecoreURI, NO_MESSAGES);
		}

		public @NonNull EObject createDeductionsInstance() {
			EClass eClass = (EClass) deductionsClass.getESObject();
			EFactory eFactoryInstance = eClass.getEPackage().getEFactoryInstance();
			return eFactoryInstance.create(eClass);
		}

		public @NonNull EObject createDummyInstance() {
			EClass eClass = (EClass) dummyClass.getESObject();
			EFactory eFactoryInstance = eClass.getEPackage().getEFactoryInstance();
			return eFactoryInstance.create(eClass);
		}

		public Resource doLoad_Concrete(@NonNull String stem, @NonNull String extension) throws IOException {
			String inputName = stem + "." + extension;
			String pivotName = inputName + PivotConstants.DOT_OCL_AS_FILE_EXTENSION;
			URI inputURI = getTestFileURI(inputName);
			URI pivotURI = getTestFileURI(pivotName);
			BaseCSResource xtextResource = (BaseCSResource) getResourceSet().createResource(inputURI);
			JavaClassScope.getAdapter(xtextResource,  getClass().getClassLoader());
			getEnvironmentFactory().adapt(xtextResource);
			xtextResource.load(null);
			assertNoResourceErrors("Load failed", xtextResource);
			CS2AS cs2as = xtextResource.findCS2AS();
			if (cs2as != null) {
				ASResource asResource = cs2as.getASResource();
				assertNoValidationErrors("Loaded pivot", asResource);
			}
			XMLResource asResource = xtextResource.getASResource();
			assertNoUnresolvedProxies("Unresolved proxies", xtextResource);
			assertNoResourceErrors("Save failed", xtextResource);
			asResource.setURI(pivotURI);
			assertNoValidationErrors("Pivot validation errors", asResource.getContents().get(0));
			asResource.save(XMIUtil.createSaveOptions(asResource));
			return asResource;
		}

		public @NonNull ExpressionInOCL getExpressionInOCL() throws ParserException {
			return ((EnvironmentFactoryInternalExtension)environmentFactory).parseSpecification(asOperation.getBodyExpression());
		}

		protected @NonNull SymbolicAnalysis getSymbolicAnalysis(@NonNull ExpressionInOCL asExpressionInOCL, @Nullable Object selfObject, @Nullable Object resultObject, @Nullable Object @Nullable [] parameters) {
			MetamodelManagerInternal metamodelManager = getMetamodelManager();
			return ((MetamodelManagerInternal.MetamodelManagerInternalExtension2)metamodelManager).getSymbolicAnalysis(asExpressionInOCL, selfObject, resultObject, parameters);
		}
	}

	public void checkContents(@NonNull SymbolicAnalysis symbolicAnalysis, @NonNull ExpressionInOCL asExpressionInOCL,
			@Nullable IsDeadList expectedDeads, @Nullable MayBeNullList expectedMayBeNulls,
			@Nullable MayBeInvalidList expectedMayBeInvalids, @Nullable IsInvalidList expectedIsInvalids) {
		assertNull(symbolicAnalysis.getAnalysisIncompatibility());
	//	Map<@NonNull Element, @NonNull SymbolicValue> element2symbolicValue = symbolicAnalysis.getElement2SymbolicValue();
		BaseSymbolicEvaluationEnvironment evaluationEnvironment = symbolicAnalysis.getBaseSymbolicEvaluationEnvironment();
		Set<@NonNull TypedElement> elements = new HashSet<>(); //>evaluationEnvironment.getElements();
		Set<@NonNull CSEElement> cseElements = evaluationEnvironment.getCSEElements();
		Set<@Nullable EObject> expectedDeadSet = expectedDeads != null ? new UniqueList<@Nullable EObject>(expectedDeads) : Collections.emptySet();
		int expectedContentCount = 0;
		Set<@NonNull CSEElement> cseElements2 = new HashSet<>();
		for (EObject eObject : new TreeIterable(asExpressionInOCL, true)) {
			TypedElement element = (TypedElement)eObject;
			if (cseElements2.add(symbolicAnalysis.getCSEElement(element))) {
				expectedContentCount++;
			}
			elements.add(element);
		//	assertTrue("Expected dead for " + debugText(eObject), evaluationEnvironment.basicGetSymbolicValue(element) != null);
		}
		assertEquals("Checking contents size for " + debugText(asExpressionInOCL), cseElements.size(), expectedContentCount); //allContents.size();
		Set<@Nullable EObject> expectedMayBeNullSet = expectedMayBeNulls != null ? new UniqueList<@Nullable EObject>(expectedMayBeNulls) : Collections.emptySet();
		Set<@Nullable EObject> expectedMayBeInvalidSet = expectedMayBeInvalids != null ? new UniqueList<@Nullable EObject>(expectedMayBeInvalids) : Collections.emptySet();
		Set<@Nullable EObject> expectedIsInvalidSet = expectedIsInvalids != null ? new UniqueList<@Nullable EObject>(expectedIsInvalids) : Collections.emptySet();
		StringBuilder s = new StringBuilder();
		for (@NonNull TypedElement element : elements) {
			SymbolicValue actualValue = evaluationEnvironment.getSymbolicValue(element);
			assert actualValue != null;
			boolean expectedIsDead = expectedDeadSet.contains(element);
			boolean expectedIsInvalid = expectedIsInvalidSet.contains(element);
			boolean expectedMayBeNull = expectedMayBeNullSet.contains(element);
			boolean expectedMayBeInvalid = expectedIsInvalid || expectedMayBeInvalidSet.contains(element);
			boolean okIsDead = expectedIsDead == actualValue.isDead();
			boolean okIsInvalid = expectedIsInvalid == actualValue.isInvalid();
			boolean okMayBeInvalid = expectedMayBeInvalid == (actualValue.mayBeInvalidReason() != null);
			boolean okMayBeNull = expectedMayBeNull == (actualValue.mayBeNullReason() != null);
			if (!okIsDead || !okIsInvalid || !okMayBeInvalid || !okMayBeNull ) {
				s.append("\n\t" + element.eClass().getName() + ": " + SymbolicUtil.printPath(element, true));
				if (!okIsDead) {
					s.append("\n\t\t expected " + (expectedIsDead ? "not " : "") + "is-dead" );
				}
				if (!okIsInvalid) {
					s.append("\n\t\t expected " + (expectedIsInvalid ? "not " : "") + "is-invalid");
				}
				if (!okMayBeInvalid) {
					s.append("\n\t\t expected " + (expectedMayBeInvalid ? "not " : "") + "may-be-invalid");
				}
				if (!okMayBeNull) {
					s.append("\n\t\t expected " + (expectedMayBeNull ? "not " : "") + "may-be-null");
				}
			}
		}
		if (s.length() > 0) {
			fail(s.toString());
		}
	}

	private @NonNull String debugText(@NonNull EObject eObject) {
		return eObject.eClass().getName() + ":\"" + eObject + "\"";
	}

	protected @Nullable IsDeadList isDeads(@Nullable EObject @Nullable ... elements) {
		if (elements == null) {
			return null;
		}
		IsDeadList results = new IsDeadList();
		for (@Nullable EObject element : elements) {
			results.add(element);
		}
		return results;
	}

	protected @Nullable IsInvalidList isInvalids(@Nullable EObject @Nullable ... elements) {
		if (elements == null) {
			return null;
		}
		IsInvalidList results = new IsInvalidList();
		for (@Nullable EObject element : elements) {
			results.add(element);
		}
		return results;
	}

/*	protected @Nullable EObject @Nullable [] mayBeDeads(@Nullable EObject @Nullable ... elements) {
		if (elements == null) {
			return null;
		}
		int iMax = elements.length;
		@Nullable EObject [] results = new @Nullable EObject[iMax];
		for (int i = 0; i < iMax; i++) {
			results[i] = elements[i];
		}
		return results;
	} */

	protected @Nullable MayBeInvalidList mayBeInvalids(@Nullable EObject @Nullable ... elements) {
		if (elements == null) {
			return null;
		}
		MayBeInvalidList results = new MayBeInvalidList();
		for (@Nullable EObject element : elements) {
			results.add(element);
		}
		return results;
	}

	protected @Nullable MayBeNullList mayBeNulls(@Nullable EObject @Nullable ... elements) {
		if (elements == null) {
			return null;
		}
		MayBeNullList results = new MayBeNullList();
		for (@Nullable EObject element : elements) {
			results.add(element);
		}
		return results;
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		SymbolicAnalysis.HYPOTHESIS.setState(true);
	}

	public void testSymbolicAnalysis_AndGuard() throws Exception {
		MyOCL ocl = new MyOCL("AndGuard", "AndGuard(x : Integer[?]) : Boolean",
				"x <> null and x > 0");
		ExpressionInOCL asExpressionInOCL = ocl.getExpressionInOCL();
		VariableDeclaration x_Parameter = PivotUtil.getOwnedParameter(asExpressionInOCL, 0);
		OperationCallExp and_Exp = (OperationCallExp) asExpressionInOCL.getOwnedBody();
		OperationCallExp ne_and_Exp = (OperationCallExp) PivotUtil.getOwnedSource(and_Exp);
		OperationCallExp gt_and_Exp = (OperationCallExp) PivotUtil.getOwnedArgument(and_Exp, 0);
		VariableExp x_ne_and_Exp = (VariableExp) PivotUtil.getOwnedSource(ne_and_Exp);
		NullLiteralExp null_ne_and_Exp = (NullLiteralExp) PivotUtil.getOwnedArgument(ne_and_Exp, 0);
		VariableExp x_gt_and_Exp = (VariableExp) PivotUtil.getOwnedSource(gt_and_Exp);
		NumericLiteralExp zero_gt_and_Exp = (NumericLiteralExp) PivotUtil.getOwnedArgument(gt_and_Exp, 0);

		PrimitiveType integerType = ocl.getStandardLibrary().getIntegerType();
		SymbolicVariableValue self_Value = new SymbolicVariableValue(asExpressionInOCL.getOwnedContext(), null, null);

		// non-null known ok x
		SymbolicAnalysis symbolicAnalysis1a = ocl.getSymbolicAnalysis(asExpressionInOCL, self_Value, null, new Object[]{5});
		checkContents(symbolicAnalysis1a, asExpressionInOCL, null, mayBeNulls(null_ne_and_Exp), null, null);
		BaseSymbolicEvaluationEnvironment symbolicEvaluationEnvironment1a = symbolicAnalysis1a.getBaseSymbolicEvaluationEnvironment();
		assertTrue(symbolicEvaluationEnvironment1a.isTrue(gt_and_Exp));

		// non-null known bad x
		SymbolicAnalysis symbolicAnalysis1b = ocl.getSymbolicAnalysis(asExpressionInOCL, self_Value, null, new Object[]{-5});
		checkContents(symbolicAnalysis1b, asExpressionInOCL, isDeads(ne_and_Exp, x_ne_and_Exp, null_ne_and_Exp), null, null, null);
		BaseSymbolicEvaluationEnvironment symbolicEvaluationEnvironment1b = symbolicAnalysis1b.getBaseSymbolicEvaluationEnvironment();
		assertTrue(symbolicEvaluationEnvironment1b.isFalse(gt_and_Exp));

		// null x
		SymbolicAnalysis symbolicAnalysis2 = ocl.getSymbolicAnalysis(asExpressionInOCL, self_Value, null, new Object[]{null});
		checkContents(symbolicAnalysis2, asExpressionInOCL, isDeads(gt_and_Exp, x_gt_and_Exp, zero_gt_and_Exp), mayBeNulls(x_Parameter, x_ne_and_Exp, null_ne_and_Exp), null, null);
		BaseSymbolicEvaluationEnvironment symbolicEvaluationEnvironment2 = symbolicAnalysis2.getBaseSymbolicEvaluationEnvironment();
		assertTrue(symbolicEvaluationEnvironment2.isFalse(ne_and_Exp));
		assertTrue(symbolicEvaluationEnvironment2.isFalse(asExpressionInOCL));

		// may-be-null x
		SymbolicVariableValue symbolicVariable = new SymbolicVariableValue(asExpressionInOCL.getOwnedParameters().get(0), SymbolicSimpleReason.MAY_BE_NULL_REASON, null);
		SymbolicAnalysis symbolicAnalysis3 = ocl.getSymbolicAnalysis(asExpressionInOCL, self_Value, null, new Object[]{symbolicVariable});
		checkContents(symbolicAnalysis3, asExpressionInOCL, null, mayBeNulls(x_Parameter, x_ne_and_Exp, null_ne_and_Exp), null, null);

		// non-null unknown x
		SymbolicAnalysis symbolicAnalysis4 = ocl.getSymbolicAnalysis(asExpressionInOCL, self_Value, null, new Object[]{new SymbolicUnknownValue("p0", integerType, null, null)});
		checkContents(symbolicAnalysis4, asExpressionInOCL, null, mayBeNulls(null_ne_and_Exp), null, null);
		BaseSymbolicEvaluationEnvironment symbolicEvaluationEnvironment4 = symbolicAnalysis4.getBaseSymbolicEvaluationEnvironment();
		assertFalse(symbolicEvaluationEnvironment4.mayBeInvalidOrNull(gt_and_Exp));
		assertFalse(symbolicEvaluationEnvironment4.mayBeInvalidOrNull(asExpressionInOCL));

		ocl.dispose();
	}

	public void testSymbolicAnalysis_And2Guard() throws Exception {
		MyOCL ocl = new MyOCL("And2Guard", "And2Guard(x : Integer[?]) : Boolean",
				"x <> null and2 x > 0");
		ExpressionInOCL asExpressionInOCL = ocl.getExpressionInOCL();
		VariableDeclaration x_Parameter = PivotUtil.getOwnedParameter(asExpressionInOCL, 0);
		OperationCallExp and2_Exp = (OperationCallExp) asExpressionInOCL.getOwnedBody();
		OperationCallExp ne_and2_Exp = (OperationCallExp) PivotUtil.getOwnedSource(and2_Exp);
		OperationCallExp gt_and2_Exp = (OperationCallExp) PivotUtil.getOwnedArgument(and2_Exp, 0);
		VariableExp x_ne_and2_Exp = (VariableExp) PivotUtil.getOwnedSource(ne_and2_Exp);
		NullLiteralExp null_ne_and2_Exp = (NullLiteralExp) PivotUtil.getOwnedArgument(ne_and2_Exp, 0);
		VariableExp x_gt_and2_Exp = (VariableExp) PivotUtil.getOwnedSource(gt_and2_Exp);
		NumericLiteralExp zero_gt_and2_Exp = (NumericLiteralExp) PivotUtil.getOwnedArgument(gt_and2_Exp, 0);

		PrimitiveType integerType = ocl.getStandardLibrary().getIntegerType();
		SymbolicVariableValue self_Value = new SymbolicVariableValue(asExpressionInOCL.getOwnedContext(), null, null);

		// non-null known ok x
		SymbolicAnalysis symbolicAnalysis1a = ocl.getSymbolicAnalysis(asExpressionInOCL, self_Value, null, new Object[]{5});
		checkContents(symbolicAnalysis1a, asExpressionInOCL, null, mayBeNulls(null_ne_and2_Exp), null, null);
		BaseSymbolicEvaluationEnvironment symbolicEvaluationEnvironment1a = symbolicAnalysis1a.getBaseSymbolicEvaluationEnvironment();
		assertTrue(symbolicEvaluationEnvironment1a.isTrue(gt_and2_Exp));

		// non-null known bad x
		SymbolicAnalysis symbolicAnalysis1b = ocl.getSymbolicAnalysis(asExpressionInOCL, self_Value, null, new Object[]{-5});
		checkContents(symbolicAnalysis1b, asExpressionInOCL, isDeads(ne_and2_Exp, x_ne_and2_Exp, null_ne_and2_Exp), null, null, null);
		BaseSymbolicEvaluationEnvironment symbolicEvaluationEnvironment1b = symbolicAnalysis1b.getBaseSymbolicEvaluationEnvironment();
		assertTrue(symbolicEvaluationEnvironment1b.isFalse(gt_and2_Exp));

		// null x
		SymbolicAnalysis symbolicAnalysis2 = ocl.getSymbolicAnalysis(asExpressionInOCL, self_Value, null, new Object[]{null});
		checkContents(symbolicAnalysis2, asExpressionInOCL, isDeads(gt_and2_Exp, x_gt_and2_Exp, zero_gt_and2_Exp), mayBeNulls(x_Parameter, x_ne_and2_Exp, null_ne_and2_Exp), null, null);
		BaseSymbolicEvaluationEnvironment symbolicEvaluationEnvironment2 = symbolicAnalysis2.getBaseSymbolicEvaluationEnvironment();
		assertTrue(symbolicEvaluationEnvironment2.isFalse(ne_and2_Exp));
		assertTrue(symbolicEvaluationEnvironment2.isFalse(asExpressionInOCL));

		// may-be-null x
		SymbolicVariableValue symbolicVariable = new SymbolicVariableValue(asExpressionInOCL.getOwnedParameters().get(0), SymbolicSimpleReason.MAY_BE_NULL_REASON, null);
		SymbolicAnalysis symbolicAnalysis3 = ocl.getSymbolicAnalysis(asExpressionInOCL, self_Value, null, new Object[]{symbolicVariable});
		checkContents(symbolicAnalysis3, asExpressionInOCL, null, mayBeNulls(x_Parameter, x_ne_and2_Exp, null_ne_and2_Exp), null, null);

		// non-null unknown x
		SymbolicAnalysis symbolicAnalysis4 = ocl.getSymbolicAnalysis(asExpressionInOCL, self_Value, null, new Object[]{new SymbolicUnknownValue("p0", integerType, null, null)});
		checkContents(symbolicAnalysis4, asExpressionInOCL, null, mayBeNulls(null_ne_and2_Exp), null, null);
		BaseSymbolicEvaluationEnvironment symbolicEvaluationEnvironment4 = symbolicAnalysis4.getBaseSymbolicEvaluationEnvironment();
		assertFalse(symbolicEvaluationEnvironment4.mayBeInvalidOrNull(gt_and2_Exp));
		assertFalse(symbolicEvaluationEnvironment4.mayBeInvalidOrNull(asExpressionInOCL));

		ocl.dispose();
	}

	public void testSymbolicAnalysis_BadSequenceIndex() throws Exception {
		MyOCL ocl = new MyOCL("BadSequenceIndex",
			"BadSequenceIndex(s : Sequence(Dummy)[?]) : Dummy", "s->select(false)->at(4)");
		ExpressionInOCL asExpressionInOCL = ocl.getExpressionInOCL();
	//	TypeId deductionsType = asExpressionInOCL.getOwnedContext().getTypeId();
		Type seqType = asExpressionInOCL.getOwnedParameters().get(0).getType();
		OperationCallExp at_Exp = (OperationCallExp) asExpressionInOCL.getOwnedBody();
		IteratorExp select_at_Exp = (IteratorExp) at_Exp.getOwnedSource();
		IteratorVariable select_at_Iterator = (IteratorVariable) select_at_Exp.getOwnedIterators().get(0);
	//	LiteralExp selectBodyExp = (LiteralExp) select_at_Exp.getOwnedBody();
	//	VariableExp asSourceExp = (VariableExp) PivotUtil.getOwnedSource(asPropertyCallExp);

		SymbolicVariableValue self_Value = new SymbolicVariableValue(asExpressionInOCL.getOwnedContext(), null, null);

		// safe non-null navigation
		SymbolicAnalysis symbolicAnalysis1 = ocl.getSymbolicAnalysis(asExpressionInOCL, self_Value, null, new Object[]{new SymbolicUnknownValue("p0", seqType, null, null)});
		checkContents(symbolicAnalysis1, asExpressionInOCL, null, null, null, null);

/*		// safe null navigation
		SymbolicEvaluationVisitor symbolicAnalysis2 = ocl.getSymbolicAnalysis(asExpressionInOCL, null, null, new Object[]{null});
		checkContents(symbolicAnalysis2, asExpressionInOCL, null, mayBeNulls(asSourceExp), null, null);

		// safe maybe-null navigation
		SymbolicEvaluationVisitor symbolicAnalysis3 = ocl.getSymbolicAnalysis(asExpressionInOCL, null, null, new Object[]{new SymbolicUnknownValueImpl(deductionsType, SymbolicSimpleReason.MAY_BE_NULL_REASON, null), 0.0});
		checkContents(symbolicAnalysis3, asExpressionInOCL, null, mayBeNulls(asSourceExp), mayBeInvalids(asExpressionInOCL, asPropertyCallExp), null);

		// safe maybe-invalid navigation
		SymbolicEvaluationVisitor symbolicAnalysis4 = ocl.getSymbolicAnalysis(asExpressionInOCL, null, null, new Object[]{new SymbolicUnknownValueImpl(deductionsType, null, SymbolicUtil.mayBeInvalidReason()), 0.0});
		checkContents(symbolicAnalysis4, asExpressionInOCL, null, null, mayBeInvalids(asExpressionInOCL, asPropertyCallExp, asSourceExp), null);
*/
		ocl.dispose();
	}

	public void testSymbolicAnalysis_BadDivide() throws Exception {
		MyOCL ocl = new MyOCL("BadDivide",
			"BadDivide(num : Real[?], den : Real[?]) : Real", "num / den");
		ExpressionInOCL asExpressionInOCL = ocl.getExpressionInOCL();
		OperationCallExp div_Exp = (OperationCallExp) asExpressionInOCL.getOwnedBody();
	//	VariableExp num_Exp = (VariableExp) PivotUtil.getOwnedSource(div_Exp);
	//	VariableExp den_Exp = (VariableExp) PivotUtil.getOwnedArgument(div_Exp, 0);

		PrimitiveType realType = ocl.getStandardLibrary().getRealType();
		SymbolicVariableValue self_Value = new SymbolicVariableValue(asExpressionInOCL.getOwnedContext(), null, null);

		// not-null / maybe-zero
		SymbolicAnalysis symbolicAnalysis5a = ocl.getSymbolicAnalysis(asExpressionInOCL, self_Value, null, new Object[]{new SymbolicUnknownValue("p0", realType, null, null), new SymbolicUnknownValue("p1", realType, null, null)});
		checkContents(symbolicAnalysis5a, asExpressionInOCL, null, null, mayBeInvalids(asExpressionInOCL, div_Exp), null);

		// 5.0 / 0.0
		SymbolicAnalysis symbolicAnalysis1 = ocl.getSymbolicAnalysis(asExpressionInOCL, self_Value, null, new Object[]{5.0, 0.0});
		checkContents(symbolicAnalysis1, asExpressionInOCL, null, null, null, isInvalids(asExpressionInOCL, div_Exp));

		// 5.0 / 1.0
		SymbolicAnalysis symbolicAnalysis2 = ocl.getSymbolicAnalysis(asExpressionInOCL, self_Value, null, new Object[]{5.0, 1.0});
		checkContents(symbolicAnalysis2, asExpressionInOCL, null, null, null, null);

		// not-null / 0.0
		SymbolicAnalysis symbolicAnalysis3 = ocl.getSymbolicAnalysis(asExpressionInOCL, self_Value, null, new Object[]{new SymbolicUnknownValue("p0", realType, null, null), 0.0});
		checkContents(symbolicAnalysis3, asExpressionInOCL, null, null, null, isInvalids(asExpressionInOCL, div_Exp));

		// not-null / 1.0
		SymbolicAnalysis symbolicAnalysis4 = ocl.getSymbolicAnalysis(asExpressionInOCL, self_Value, null, new Object[]{new SymbolicUnknownValue("p0", realType, null, null), 1.0});
		checkContents(symbolicAnalysis4, asExpressionInOCL, null, null, null, null);

		// not-null / maybe-zero
		SymbolicAnalysis symbolicAnalysis5 = ocl.getSymbolicAnalysis(asExpressionInOCL, self_Value, null, new Object[]{new SymbolicUnknownValue("p0", realType, null, null), new SymbolicUnknownValue("p1", realType, null, null)});
		checkContents(symbolicAnalysis5, asExpressionInOCL, null, null, mayBeInvalids(asExpressionInOCL, div_Exp), null);

		ocl.dispose();
	}

	public void testSymbolicAnalysis_BadSafeNavigation() throws Exception {
		MyOCL ocl = new MyOCL("BadSafeNavigation",
			"BadSafeNavigation(x : Deductions[?]) : Dummy", "x?.nonNullDummy");
		ExpressionInOCL asExpressionInOCL = ocl.getExpressionInOCL();
		VariableDeclaration x_Parameter = PivotUtil.getOwnedParameter(asExpressionInOCL, 0);
		Type deductionsType = asExpressionInOCL.getOwnedContext().getType();
		PropertyCallExp nonNullDummy_Exp = (PropertyCallExp) asExpressionInOCL.getOwnedBody();
		VariableExp x_nonNullDummy_Exp = (VariableExp) PivotUtil.getOwnedSource(nonNullDummy_Exp);

		SymbolicVariableValue self_Value = new SymbolicVariableValue(asExpressionInOCL.getOwnedContext(), null, null);

		// safe non-null navigation
		SymbolicAnalysis symbolicAnalysis1 = ocl.getSymbolicAnalysis(asExpressionInOCL, self_Value, null, new Object[]{new SymbolicUnknownValue("p0", deductionsType, null, null)});
		checkContents(symbolicAnalysis1, asExpressionInOCL, null, null, null, null);

		// safe null navigation
		SymbolicAnalysis symbolicAnalysis2 = ocl.getSymbolicAnalysis(asExpressionInOCL, self_Value, null, new Object[]{null});
		checkContents(symbolicAnalysis2, asExpressionInOCL, null, mayBeNulls(asExpressionInOCL, x_Parameter, nonNullDummy_Exp, x_nonNullDummy_Exp), null, null);

		// safe maybe-null navigation
		SymbolicAnalysis symbolicAnalysis3 = ocl.getSymbolicAnalysis(asExpressionInOCL, self_Value, null, new Object[]{new SymbolicUnknownValue("p0", deductionsType, SymbolicSimpleReason.MAY_BE_NULL_REASON, null)});
		checkContents(symbolicAnalysis3, asExpressionInOCL, null, mayBeNulls(asExpressionInOCL, x_Parameter,
			nonNullDummy_Exp, x_nonNullDummy_Exp), null, null);

		// safe maybe-invalid navigation
		SymbolicAnalysis symbolicAnalysis4 = ocl.getSymbolicAnalysis(asExpressionInOCL, self_Value, null, new Object[]{new SymbolicUnknownValue("p0", deductionsType, null, SymbolicSimpleReason.MAY_BE_INVALID_REASON)});
		assertNotNull(symbolicAnalysis4.getAnalysisIncompatibility());

		ocl.dispose();
	}

	public void testSymbolicAnalysis_BadSafeOppositeNavigation() throws Exception {
		MyOCL ocl = new MyOCL("BadSafeNavigation",
			"BadSafeNavigation(x : Deductions[?]) : Bag(Dummy)", "x?.Dummy[Dummy::mayBeNullDeductions]");		// Dummy is a non-null multi-valued opposite
		ExpressionInOCL asExpressionInOCL = ocl.getExpressionInOCL();
		VariableDeclaration x_Parameter = PivotUtil.getOwnedParameter(asExpressionInOCL, 0);
		Type deductionsType = asExpressionInOCL.getOwnedContext().getType();
		OppositePropertyCallExp Dummy_Exp = (OppositePropertyCallExp) asExpressionInOCL.getOwnedBody();
		VariableExp x_Dummy_Exp = (VariableExp) PivotUtil.getOwnedSource(Dummy_Exp);

		SymbolicVariableValue self_Value = new SymbolicVariableValue(asExpressionInOCL.getOwnedContext(), null, null);

		// safe non-null navigation
		SymbolicAnalysis symbolicAnalysis1 = ocl.getSymbolicAnalysis(asExpressionInOCL, self_Value, null, new Object[]{new SymbolicUnknownValue("p0", deductionsType, null, null)});
		checkContents(symbolicAnalysis1, asExpressionInOCL, null, null, null, null);

		// safe null navigation
		SymbolicAnalysis symbolicAnalysis2 = ocl.getSymbolicAnalysis(asExpressionInOCL, self_Value, null, new Object[]{null});
		checkContents(symbolicAnalysis2, asExpressionInOCL, null, mayBeNulls(asExpressionInOCL, x_Parameter, Dummy_Exp, x_Dummy_Exp), null, null);

		// safe maybe-null navigation
		SymbolicAnalysis symbolicAnalysis3 = ocl.getSymbolicAnalysis(asExpressionInOCL, self_Value, null, new Object[]{new SymbolicUnknownValue("p0", deductionsType, SymbolicSimpleReason.MAY_BE_NULL_REASON, null)});
		checkContents(symbolicAnalysis3, asExpressionInOCL, null, mayBeNulls(x_Parameter, x_Dummy_Exp), null, null);

		// safe maybe-invalid navigation
		SymbolicAnalysis symbolicAnalysis4 = ocl.getSymbolicAnalysis(asExpressionInOCL, self_Value, null, new Object[]{new SymbolicUnknownValue("p0", deductionsType, null, SymbolicSimpleReason.MAY_BE_INVALID_REASON)});
		assertNotNull(symbolicAnalysis4.getAnalysisIncompatibility());

		ocl.dispose();
	}

	public void testSymbolicAnalysis_BadUnsafeRequiredNavigation() throws Exception {
		MyOCL ocl = new MyOCL("BadUnsafeRequiredNavigation",
			"BadUnsafeRequiredNavigation(x : Deductions[?]) : Dummy", "x.nonNullDummy");
		ExpressionInOCL asExpressionInOCL = ocl.getExpressionInOCL();
		VariableDeclaration x_Parameter = PivotUtil.getOwnedParameter(asExpressionInOCL, 0);
		Type deductionsType = asExpressionInOCL.getOwnedContext().getType();
		PropertyCallExp nonNullDummy_Exp = (PropertyCallExp) asExpressionInOCL.getOwnedBody();
		VariableExp x_nonNullDummy_Exp = (VariableExp) PivotUtil.getOwnedSource(nonNullDummy_Exp);

		SymbolicVariableValue self_Value = new SymbolicVariableValue(asExpressionInOCL.getOwnedContext(), null, null);

		// unsafe non-null navigation
		SymbolicAnalysis symbolicAnalysis1 = ocl.getSymbolicAnalysis(asExpressionInOCL, self_Value, null, new Object[]{new SymbolicUnknownValue("p0", deductionsType, null, null)});
		checkContents(symbolicAnalysis1, asExpressionInOCL, null, null, null, null);

		// unsafe null navigation
		SymbolicAnalysis symbolicAnalysis2 = ocl.getSymbolicAnalysis(asExpressionInOCL, self_Value, null, new Object[]{null});
		checkContents(symbolicAnalysis2, asExpressionInOCL, null, mayBeNulls(x_Parameter, x_nonNullDummy_Exp), null, isInvalids(asExpressionInOCL, nonNullDummy_Exp));

		// unsafe maybe-null navigation
		SymbolicAnalysis symbolicAnalysis3 = ocl.getSymbolicAnalysis(asExpressionInOCL, self_Value, null, new Object[]{new SymbolicUnknownValue("p0", deductionsType, SymbolicSimpleReason.MAY_BE_NULL_REASON, null)});
		checkContents(symbolicAnalysis3, asExpressionInOCL, null, mayBeNulls(x_Parameter, x_nonNullDummy_Exp), mayBeInvalids(asExpressionInOCL, nonNullDummy_Exp), null);

		// unsafe maybe-invalid navigation
		SymbolicAnalysis symbolicAnalysis4 = ocl.getSymbolicAnalysis(asExpressionInOCL, self_Value, null, new Object[]{new SymbolicUnknownValue("p0", deductionsType, null, SymbolicSimpleReason.MAY_BE_INVALID_REASON)});
		assertNotNull(symbolicAnalysis4.getAnalysisIncompatibility());

		ocl.dispose();
	}

	public void testSymbolicAnalysis_BadUnsafeOptionalNavigation() throws Exception {
		MyOCL ocl = new MyOCL("BadUnsafeOptionalNavigation",
			"BadUnsafeOptionalNavigation(x : Deductions[?]) : Dummy", "x.mayBeNullDummy");
		ExpressionInOCL asExpressionInOCL = ocl.getExpressionInOCL();
		VariableDeclaration x_Parameter = PivotUtil.getOwnedParameter(asExpressionInOCL, 0);
		Type deductionsType = asExpressionInOCL.getOwnedContext().getType();
		PropertyCallExp mayBeNullDummy_Exp = (PropertyCallExp) asExpressionInOCL.getOwnedBody();
		VariableExp x_mayBeNullDummy_Exp = (VariableExp) PivotUtil.getOwnedSource(mayBeNullDummy_Exp);

		SymbolicVariableValue self_Value = new SymbolicVariableValue(asExpressionInOCL.getOwnedContext(), null, null);

		// unsafe non-null navigation
		SymbolicAnalysis symbolicAnalysis1 = ocl.getSymbolicAnalysis(asExpressionInOCL, self_Value, null, new Object[]{new SymbolicUnknownValue("p0", deductionsType, null, null)});
		checkContents(symbolicAnalysis1, asExpressionInOCL, null, mayBeNulls(asExpressionInOCL, mayBeNullDummy_Exp), null, null);

		// unsafe null navigation
		SymbolicAnalysis symbolicAnalysis2 = ocl.getSymbolicAnalysis(asExpressionInOCL, self_Value, null, new Object[]{null});
		checkContents(symbolicAnalysis2, asExpressionInOCL, null, mayBeNulls(x_Parameter, x_mayBeNullDummy_Exp), null, isInvalids(asExpressionInOCL, mayBeNullDummy_Exp));

		// unsafe maybe-null navigation
		SymbolicAnalysis symbolicAnalysis3 = ocl.getSymbolicAnalysis(asExpressionInOCL, self_Value, null, new Object[]{new SymbolicUnknownValue("p0", deductionsType, SymbolicSimpleReason.MAY_BE_NULL_REASON, null)});
		checkContents(symbolicAnalysis3, asExpressionInOCL, null, mayBeNulls(asExpressionInOCL, x_Parameter, x_mayBeNullDummy_Exp, mayBeNullDummy_Exp), mayBeInvalids(asExpressionInOCL, mayBeNullDummy_Exp), null);

		// unsafe maybe-invalid navigation
		SymbolicAnalysis symbolicAnalysis4 = ocl.getSymbolicAnalysis(asExpressionInOCL, self_Value, null, new Object[]{new SymbolicUnknownValue("p0", deductionsType, null, SymbolicSimpleReason.MAY_BE_INVALID_REASON)});
		assertNotNull(symbolicAnalysis4.getAnalysisIncompatibility());

		ocl.dispose();
	}

	public void testSymbolicAnalysis_CommutatedAndGuard() throws Exception {
		MyOCL ocl = new MyOCL("CommutatedAndGuard", "CommutatedAndGuard(x : Integer[?]) : Boolean",
				"x > 0 and x <> null");
		ExpressionInOCL asExpressionInOCL = ocl.getExpressionInOCL();
		VariableDeclaration x_Parameter = PivotUtil.getOwnedParameter(asExpressionInOCL, 0);
		OperationCallExp and_Exp = (OperationCallExp) asExpressionInOCL.getOwnedBody();
		OperationCallExp gt_and_Exp = (OperationCallExp) PivotUtil.getOwnedSource(and_Exp);
		OperationCallExp ne_and_Exp = (OperationCallExp) PivotUtil.getOwnedArgument(and_Exp, 0);
		VariableExp x_ne_and_Exp = (VariableExp) PivotUtil.getOwnedSource(ne_and_Exp);
		NullLiteralExp null_ne_and_Exp = (NullLiteralExp) PivotUtil.getOwnedArgument(ne_and_Exp, 0);
		VariableExp x_gt_and_Exp = (VariableExp) PivotUtil.getOwnedSource(gt_and_Exp);
		NumericLiteralExp zero_gt_and_Exp = (NumericLiteralExp) PivotUtil.getOwnedArgument(gt_and_Exp, 0);

		PrimitiveType integerType = ocl.getStandardLibrary().getIntegerType();
		SymbolicVariableValue self_Value = new SymbolicVariableValue(asExpressionInOCL.getOwnedContext(), null, null);

		// non-null known ok x
		SymbolicAnalysis symbolicAnalysis1a = ocl.getSymbolicAnalysis(asExpressionInOCL, self_Value, null, new Object[]{5});
		checkContents(symbolicAnalysis1a, asExpressionInOCL, null, mayBeNulls(null_ne_and_Exp), null, null);
		BaseSymbolicEvaluationEnvironment symbolicEvaluationEnvironment1a = symbolicAnalysis1a.getBaseSymbolicEvaluationEnvironment();
		assertTrue(symbolicEvaluationEnvironment1a.isTrue(gt_and_Exp));

		// non-null known bad x
		SymbolicAnalysis symbolicAnalysis1b = ocl.getSymbolicAnalysis(asExpressionInOCL, self_Value, null, new Object[]{-5});
		checkContents(symbolicAnalysis1b, asExpressionInOCL, isDeads(ne_and_Exp, x_ne_and_Exp, null_ne_and_Exp), null, null, null);
		BaseSymbolicEvaluationEnvironment symbolicEvaluationEnvironment1b = symbolicAnalysis1b.getBaseSymbolicEvaluationEnvironment();
		assertTrue(symbolicEvaluationEnvironment1b.isFalse(gt_and_Exp));

		// null x
		SymbolicAnalysis symbolicAnalysis2 = ocl.getSymbolicAnalysis(asExpressionInOCL, self_Value, null, new Object[]{null});
		checkContents(symbolicAnalysis2, asExpressionInOCL, isDeads(gt_and_Exp, x_gt_and_Exp, zero_gt_and_Exp), mayBeNulls(x_Parameter, x_ne_and_Exp, null_ne_and_Exp), null, null);
		BaseSymbolicEvaluationEnvironment symbolicEvaluationEnvironment2 = symbolicAnalysis2.getBaseSymbolicEvaluationEnvironment();
		assertTrue(symbolicEvaluationEnvironment2.isFalse(ne_and_Exp));
	//	assertTrue(symbolicEvaluationEnvironment2.isTrue(asExpressionInOCL));

		// may-be-null x
		SymbolicVariableValue symbolicVariable = new SymbolicVariableValue(asExpressionInOCL.getOwnedParameters().get(0), SymbolicSimpleReason.MAY_BE_NULL_REASON, null);
		SymbolicAnalysis symbolicAnalysis3 = ocl.getSymbolicAnalysis(asExpressionInOCL, self_Value, null, new Object[]{symbolicVariable});
		checkContents(symbolicAnalysis3, asExpressionInOCL, null, mayBeNulls(x_Parameter, x_ne_and_Exp, null_ne_and_Exp), null, null);

		// non-null unknown x
		SymbolicAnalysis symbolicAnalysis4 = ocl.getSymbolicAnalysis(asExpressionInOCL, self_Value, null, new Object[]{new SymbolicUnknownValue("p0", integerType, null, null)});
		checkContents(symbolicAnalysis4, asExpressionInOCL, null, mayBeNulls(null_ne_and_Exp), null, null);
		BaseSymbolicEvaluationEnvironment symbolicEvaluationEnvironment4 = symbolicAnalysis4.getBaseSymbolicEvaluationEnvironment();
		assertFalse(symbolicEvaluationEnvironment4.mayBeInvalidOrNull(gt_and_Exp));
		assertFalse(symbolicEvaluationEnvironment4.mayBeInvalidOrNull(asExpressionInOCL));

		ocl.dispose();
	}

	public void testSymbolicAnalysis_CommutatedAnd2Guard() throws Exception {
		MyOCL ocl = new MyOCL("CommutatedAnd2Guard", "CommutatedAnd2Guard(x : Integer[?]) : Boolean",
				"x > 0 and2 x <> null");
		ExpressionInOCL asExpressionInOCL = ocl.getExpressionInOCL();
		VariableDeclaration x_Parameter = PivotUtil.getOwnedParameter(asExpressionInOCL, 0);
		OperationCallExp and2_Exp = (OperationCallExp) asExpressionInOCL.getOwnedBody();
		OperationCallExp gt_and2_Exp = (OperationCallExp) PivotUtil.getOwnedSource(and2_Exp);
		OperationCallExp ne_and2_Exp = (OperationCallExp) PivotUtil.getOwnedArgument(and2_Exp, 0);
		VariableExp x_ne_and2_Exp = (VariableExp) PivotUtil.getOwnedSource(ne_and2_Exp);
		NullLiteralExp null_ne_and2_Exp = (NullLiteralExp) PivotUtil.getOwnedArgument(ne_and2_Exp, 0);
		VariableExp x_gt_and2_Exp = (VariableExp) PivotUtil.getOwnedSource(gt_and2_Exp);
	//	NumericLiteralExp zero_gt_and2_Exp = (NumericLiteralExp) PivotUtil.getOwnedArgument(gt_and2_Exp, 0);

		PrimitiveType integerType = ocl.getStandardLibrary().getIntegerType();
		SymbolicVariableValue self_Value = new SymbolicVariableValue(asExpressionInOCL.getOwnedContext(), null, null);

		// non-null known ok x
		SymbolicAnalysis symbolicAnalysis1a = ocl.getSymbolicAnalysis(asExpressionInOCL, self_Value, null, new Object[]{5});
		checkContents(symbolicAnalysis1a, asExpressionInOCL, null, mayBeNulls(null_ne_and2_Exp), null, null);
		BaseSymbolicEvaluationEnvironment symbolicEvaluationEnvironment1a = symbolicAnalysis1a.getBaseSymbolicEvaluationEnvironment();
		assertTrue(symbolicEvaluationEnvironment1a.isTrue(gt_and2_Exp));

		// non-null known bad x
		SymbolicAnalysis symbolicAnalysis1b = ocl.getSymbolicAnalysis(asExpressionInOCL, self_Value, null, new Object[]{-5});
		checkContents(symbolicAnalysis1b, asExpressionInOCL, isDeads(ne_and2_Exp, x_ne_and2_Exp, null_ne_and2_Exp), null, null, null);
		BaseSymbolicEvaluationEnvironment symbolicEvaluationEnvironment1b = symbolicAnalysis1b.getBaseSymbolicEvaluationEnvironment();
		assertTrue(symbolicEvaluationEnvironment1b.isFalse(gt_and2_Exp));

		// null x
		SymbolicAnalysis symbolicAnalysis2 = ocl.getSymbolicAnalysis(asExpressionInOCL, self_Value, null, new Object[]{null});
		checkContents(symbolicAnalysis2, asExpressionInOCL, null, mayBeNulls(x_Parameter, x_gt_and2_Exp, x_ne_and2_Exp, null_ne_and2_Exp), null, isInvalids(asExpressionInOCL, and2_Exp, gt_and2_Exp));
		BaseSymbolicEvaluationEnvironment symbolicEvaluationEnvironment2 = symbolicAnalysis2.getBaseSymbolicEvaluationEnvironment();
		assertTrue(symbolicEvaluationEnvironment2.isFalse(ne_and2_Exp));
	//	assertTrue(symbolicEvaluationEnvironment2.isTrue(asExpressionInOCL));

		// may-be-null x
		SymbolicVariableValue symbolicVariable = new SymbolicVariableValue(asExpressionInOCL.getOwnedParameters().get(0), SymbolicSimpleReason.MAY_BE_NULL_REASON, null);
		SymbolicAnalysis symbolicAnalysis3 = ocl.getSymbolicAnalysis(asExpressionInOCL, self_Value, null, new Object[]{symbolicVariable});
		checkContents(symbolicAnalysis3, asExpressionInOCL, null, mayBeNulls(x_Parameter, null_ne_and2_Exp), null, null);

		// non-null unknown x
		SymbolicAnalysis symbolicAnalysis4 = ocl.getSymbolicAnalysis(asExpressionInOCL, self_Value, null, new Object[]{new SymbolicUnknownValue("p0", integerType, null, null)});
		checkContents(symbolicAnalysis4, asExpressionInOCL, null, mayBeNulls(null_ne_and2_Exp), null, null);
		BaseSymbolicEvaluationEnvironment symbolicEvaluationEnvironment4 = symbolicAnalysis4.getBaseSymbolicEvaluationEnvironment();
		assertFalse(symbolicEvaluationEnvironment4.mayBeInvalidOrNull(gt_and2_Exp));
		assertFalse(symbolicEvaluationEnvironment4.mayBeInvalidOrNull(asExpressionInOCL));

		ocl.dispose();
	}

	public void testSymbolicAnalysis_CommutatedOrGuard() throws Exception {
		MyOCL ocl = new MyOCL("CommutatedOrGuard", "CommutatedOrGuard(x : Integer[?]) : Boolean",
				"x > 0 or x = null");
		ExpressionInOCL asExpressionInOCL = ocl.getExpressionInOCL();
		VariableDeclaration x_Parameter = PivotUtil.getOwnedParameter(asExpressionInOCL, 0);
		OperationCallExp orExp = (OperationCallExp) asExpressionInOCL.getOwnedBody();
		OperationCallExp gt_or_Exp = (OperationCallExp) PivotUtil.getOwnedSource(orExp);
		OperationCallExp eq_or_Exp = (OperationCallExp) PivotUtil.getOwnedArgument(orExp, 0);
		VariableExp x_eq_or_Exp = (VariableExp) PivotUtil.getOwnedSource(eq_or_Exp);
		NullLiteralExp null_eq_or_Exp = (NullLiteralExp) PivotUtil.getOwnedArgument(eq_or_Exp, 0);
		VariableExp x_gt_or_Exp = (VariableExp) PivotUtil.getOwnedSource(gt_or_Exp);
		NumericLiteralExp zero_gt_or_Exp = (NumericLiteralExp) PivotUtil.getOwnedArgument(gt_or_Exp, 0);

		PrimitiveType integerType = ocl.getStandardLibrary().getIntegerType();
		SymbolicVariableValue self_Value = new SymbolicVariableValue(asExpressionInOCL.getOwnedContext(), null, null);

		// non-null known ok x
		SymbolicAnalysis symbolicAnalysis1a = ocl.getSymbolicAnalysis(asExpressionInOCL, self_Value, null, new Object[]{5});
		checkContents(symbolicAnalysis1a, asExpressionInOCL, isDeads(eq_or_Exp, x_eq_or_Exp, null_eq_or_Exp), null, null, null);
		BaseSymbolicEvaluationEnvironment symbolicEvaluationEnvironment1a = symbolicAnalysis1a.getBaseSymbolicEvaluationEnvironment();
		assertTrue(symbolicEvaluationEnvironment1a.isTrue(gt_or_Exp));

		// non-null known bad x
		SymbolicAnalysis symbolicAnalysis1b = ocl.getSymbolicAnalysis(asExpressionInOCL, self_Value, null, new Object[]{-5});
		checkContents(symbolicAnalysis1b, asExpressionInOCL, null, mayBeNulls(null_eq_or_Exp), null, null);
		BaseSymbolicEvaluationEnvironment symbolicEvaluationEnvironment1b = symbolicAnalysis1b.getBaseSymbolicEvaluationEnvironment();
		assertTrue(symbolicEvaluationEnvironment1b.isFalse(gt_or_Exp));

		// null x
		SymbolicAnalysis symbolicAnalysis2 = ocl.getSymbolicAnalysis(asExpressionInOCL, self_Value, null, new Object[]{null});
		checkContents(symbolicAnalysis2, asExpressionInOCL, isDeads(gt_or_Exp, x_gt_or_Exp, zero_gt_or_Exp), mayBeNulls(x_Parameter, x_eq_or_Exp, null_eq_or_Exp), null, null);
		BaseSymbolicEvaluationEnvironment symbolicEvaluationEnvironment2 = symbolicAnalysis2.getBaseSymbolicEvaluationEnvironment();
		assertTrue(symbolicEvaluationEnvironment2.isTrue(eq_or_Exp));
		assertTrue(symbolicEvaluationEnvironment2.isTrue(asExpressionInOCL));

		// may-be-null x
		SymbolicVariableValue symbolicVariable = new SymbolicVariableValue(asExpressionInOCL.getOwnedParameters().get(0), SymbolicSimpleReason.MAY_BE_NULL_REASON, null);
		SymbolicAnalysis symbolicAnalysis3 = ocl.getSymbolicAnalysis(asExpressionInOCL, self_Value, null, new Object[]{symbolicVariable});
		checkContents(symbolicAnalysis3, asExpressionInOCL, null, mayBeNulls(x_Parameter, x_eq_or_Exp, null_eq_or_Exp), null, null);

		// non-null unknown x
		SymbolicAnalysis symbolicAnalysis4 = ocl.getSymbolicAnalysis(asExpressionInOCL, self_Value, null, new Object[]{new SymbolicUnknownValue("p0", integerType, null, null)});
		checkContents(symbolicAnalysis4, asExpressionInOCL, null, mayBeNulls(null_eq_or_Exp), null, null);
		BaseSymbolicEvaluationEnvironment symbolicEvaluationEnvironment4 = symbolicAnalysis4.getBaseSymbolicEvaluationEnvironment();
		assertFalse(symbolicEvaluationEnvironment4.mayBeInvalidOrNull(gt_or_Exp));
		assertFalse(symbolicEvaluationEnvironment4.mayBeInvalidOrNull(asExpressionInOCL));

		ocl.dispose();
	}

	public void testSymbolicAnalysis_CommutatedOr2Guard() throws Exception {
		MyOCL ocl = new MyOCL("CommutatedOr2Guard", "CommutatedOr2Guard(x : Integer[?]) : Boolean",
				"x > 0 or2 x = null");
		ExpressionInOCL asExpressionInOCL = ocl.getExpressionInOCL();
		VariableDeclaration x_Parameter = PivotUtil.getOwnedParameter(asExpressionInOCL, 0);
		OperationCallExp orExp = (OperationCallExp) asExpressionInOCL.getOwnedBody();
		OperationCallExp gt_or2_Exp = (OperationCallExp) PivotUtil.getOwnedSource(orExp);
		OperationCallExp eq_or2_Exp = (OperationCallExp) PivotUtil.getOwnedArgument(orExp, 0);
		VariableExp x_eq_or2_Exp = (VariableExp) PivotUtil.getOwnedSource(eq_or2_Exp);
		NullLiteralExp null_eq_or2_Exp = (NullLiteralExp) PivotUtil.getOwnedArgument(eq_or2_Exp, 0);
		VariableExp x_gt_or2_Exp = (VariableExp) PivotUtil.getOwnedSource(gt_or2_Exp);
	//	NumericLiteralExp zero_gt_or2_Exp = (NumericLiteralExp) PivotUtil.getOwnedArgument(gt_or2_Exp, 0);

		PrimitiveType integerType = ocl.getStandardLibrary().getIntegerType();
		SymbolicVariableValue self_Value = new SymbolicVariableValue(asExpressionInOCL.getOwnedContext(), null, null);

		// non-null known ok x
		SymbolicAnalysis symbolicAnalysis1a = ocl.getSymbolicAnalysis(asExpressionInOCL, self_Value, null, new Object[]{5});
		checkContents(symbolicAnalysis1a, asExpressionInOCL, isDeads(eq_or2_Exp, x_eq_or2_Exp, null_eq_or2_Exp), null, null, null);
		BaseSymbolicEvaluationEnvironment symbolicEvaluationEnvironment1a = symbolicAnalysis1a.getBaseSymbolicEvaluationEnvironment();
		assertTrue(symbolicEvaluationEnvironment1a.isTrue(gt_or2_Exp));

		// non-null known bad x
		SymbolicAnalysis symbolicAnalysis1b = ocl.getSymbolicAnalysis(asExpressionInOCL, self_Value, null, new Object[]{-5});
		checkContents(symbolicAnalysis1b, asExpressionInOCL, null, mayBeNulls(null_eq_or2_Exp), null, null);
		BaseSymbolicEvaluationEnvironment symbolicEvaluationEnvironment1b = symbolicAnalysis1b.getBaseSymbolicEvaluationEnvironment();
		assertTrue(symbolicEvaluationEnvironment1b.isFalse(gt_or2_Exp));

		// null x
		SymbolicAnalysis symbolicAnalysis2 = ocl.getSymbolicAnalysis(asExpressionInOCL, self_Value, null, new Object[]{null});
		checkContents(symbolicAnalysis2, asExpressionInOCL, null, mayBeNulls(x_Parameter, x_gt_or2_Exp, x_eq_or2_Exp, null_eq_or2_Exp), null, isInvalids(asExpressionInOCL, orExp, gt_or2_Exp));
		BaseSymbolicEvaluationEnvironment symbolicEvaluationEnvironment2 = symbolicAnalysis2.getBaseSymbolicEvaluationEnvironment();
		assertTrue(symbolicEvaluationEnvironment2.isTrue(eq_or2_Exp));
	//	assertTrue(symbolicEvaluationEnvironment2.isTrue(asExpressionInOCL));

		// may-be-null x
		SymbolicVariableValue symbolicVariable = new SymbolicVariableValue(asExpressionInOCL.getOwnedParameters().get(0), SymbolicSimpleReason.MAY_BE_NULL_REASON, null);
		SymbolicAnalysis symbolicAnalysis3 = ocl.getSymbolicAnalysis(asExpressionInOCL, self_Value, null, new Object[]{symbolicVariable});
		checkContents(symbolicAnalysis3, asExpressionInOCL, null, mayBeNulls(x_Parameter, null_eq_or2_Exp), null, null);

		// non-null unknown x
		SymbolicAnalysis symbolicAnalysis4 = ocl.getSymbolicAnalysis(asExpressionInOCL, self_Value, null, new Object[]{new SymbolicUnknownValue("p0", integerType, null, null)});
		checkContents(symbolicAnalysis4, asExpressionInOCL, null, mayBeNulls(null_eq_or2_Exp), null, null);
		BaseSymbolicEvaluationEnvironment symbolicEvaluationEnvironment4 = symbolicAnalysis4.getBaseSymbolicEvaluationEnvironment();
		assertFalse(symbolicEvaluationEnvironment4.mayBeInvalidOrNull(gt_or2_Exp));
		assertFalse(symbolicEvaluationEnvironment4.mayBeInvalidOrNull(asExpressionInOCL));

		ocl.dispose();
	}

	public void testSymbolicAnalysis_First() throws Exception {
		MyOCL ocl = new MyOCL("First", "First(x : Sequence(Integer)[?]) : Integer",
				"x->first()");
		ExpressionInOCL asExpressionInOCL = ocl.getExpressionInOCL();
		VariableDeclaration x_Parameter = PivotUtil.getOwnedParameter(asExpressionInOCL, 0);
		OperationCallExp first_Exp = (OperationCallExp) asExpressionInOCL.getOwnedBody();
		VariableExp x_first_Exp = (VariableExp) PivotUtil.getOwnedSource(first_Exp);

		SymbolicVariableValue self_Value = new SymbolicVariableValue(asExpressionInOCL.getOwnedContext(), null, null);

		// non-null empty x
		SymbolicAnalysis symbolicAnalysis1a = ocl.getSymbolicAnalysis(asExpressionInOCL, self_Value, null, new Object[]{ValueUtil.EMPTY_SET});
		assertEquals("incompatible type \"Set(OclInvalid[0|1])\" for parameter \"x\"", symbolicAnalysis1a.getAnalysisIncompatibility());

		// non-null empty x
		SymbolicAnalysis symbolicAnalysis1 = ocl.getSymbolicAnalysis(asExpressionInOCL, self_Value, null, new Object[]{ValueUtil.EMPTY_SEQUENCE});
		checkContents(symbolicAnalysis1, asExpressionInOCL, null, null, null, isInvalids(asExpressionInOCL, first_Exp));

		// non-null not-empty x
		SetValue set2a = ValueUtil.createSetOfEach(TypeId.SET.getSpecializedId(TypeId.INTEGER), ValueUtil.integerValueOf(99));
		SymbolicAnalysis symbolicAnalysis2a = ocl.getSymbolicAnalysis(asExpressionInOCL, self_Value, null, new Object[]{set2a});
		assertEquals("incompatible type \"Set(Integer[1|1])\" for parameter \"x\"", symbolicAnalysis2a.getAnalysisIncompatibility());

		// non-null not-empty x
		SequenceValue seq = ValueUtil.createSequenceOfEach(TypeId.SEQUENCE.getSpecializedId(TypeId.INTEGER), ValueUtil.integerValueOf(99));
		SymbolicAnalysis symbolicAnalysis2 = ocl.getSymbolicAnalysis(asExpressionInOCL, self_Value, null, new Object[]{seq});
		checkContents(symbolicAnalysis2, asExpressionInOCL, null, null, null, null);

		// non-null unknown x
		SymbolicVariableValue symbolicVariable = new SymbolicVariableValue(asExpressionInOCL.getOwnedParameters().get(0), null, null);
		SymbolicAnalysis symbolicAnalysis3 = ocl.getSymbolicAnalysis(asExpressionInOCL, self_Value, null, new Object[]{symbolicVariable});
		checkContents(symbolicAnalysis3, asExpressionInOCL, null, null, mayBeInvalids(asExpressionInOCL, first_Exp), null);

		// null collection x (does not behave as empty set)
		SymbolicAnalysis symbolicAnalysis4 = ocl.getSymbolicAnalysis(asExpressionInOCL, self_Value, null, new Object[]{null});
		checkContents(symbolicAnalysis4, asExpressionInOCL, null, mayBeNulls(x_Parameter, x_first_Exp), null, isInvalids(asExpressionInOCL, first_Exp));

		ocl.dispose();
	}

	public void testSymbolicAnalysis_GuardedBadDivide() throws Exception {
		MyOCL ocl = new MyOCL("GuardedBadDivide",
			"GuardedBadDivide(num : Real[?], den : Real[?]) : Real", "if den <> 0 then num / den else 999.999 endif");
		ExpressionInOCL asExpressionInOCL = ocl.getExpressionInOCL();
		IfExp if_Exp = (IfExp) asExpressionInOCL.getOwnedBody();
		OperationCallExp div_if_Exp = (OperationCallExp) PivotUtil.getOwnedThen(if_Exp);
		VariableExp num_Exp = (VariableExp) PivotUtil.getOwnedSource(div_if_Exp);
		VariableExp den_Exp = (VariableExp) PivotUtil.getOwnedArgument(div_if_Exp, 0);
		LiteralExp literal_Exp = (LiteralExp) PivotUtil.getOwnedElse(if_Exp);

		PrimitiveType integerType = ocl.getStandardLibrary().getIntegerType();
		PrimitiveType realType = ocl.getStandardLibrary().getRealType();
		SymbolicVariableValue self_Value = new SymbolicVariableValue(asExpressionInOCL.getOwnedContext(), null, null);

		// 5.0 / 0.0
		SymbolicAnalysis symbolicAnalysis1 = ocl.getSymbolicAnalysis(asExpressionInOCL, self_Value, null, new Object[]{5.0, 0.0});
		checkContents(symbolicAnalysis1, asExpressionInOCL, isDeads(div_if_Exp, num_Exp, den_Exp), null, null, null);

		// 5.0 / 1.0
		SymbolicAnalysis symbolicAnalysis2 = ocl.getSymbolicAnalysis(asExpressionInOCL, self_Value, null, new Object[]{5.0, 1.0});
		checkContents(symbolicAnalysis2, asExpressionInOCL, isDeads(literal_Exp), null, null, null);

		// not-null / 0.0
		SymbolicAnalysis symbolicAnalysis3 = ocl.getSymbolicAnalysis(asExpressionInOCL, self_Value, null, new Object[]{new SymbolicUnknownValue("p0", realType, null, null), 0.0});
		checkContents(symbolicAnalysis3, asExpressionInOCL, isDeads(div_if_Exp, num_Exp, den_Exp), null, null, null);

		// not-null / 1.0
		SymbolicAnalysis symbolicAnalysis4 = ocl.getSymbolicAnalysis(asExpressionInOCL, self_Value, null, new Object[]{new SymbolicUnknownValue("p0", realType, null, null), 1.0});
		checkContents(symbolicAnalysis4, asExpressionInOCL, isDeads(literal_Exp), null, null, null);

		// 5.0 / not-null-maybe-zero
		SymbolicAnalysis symbolicAnalysis5 = ocl.getSymbolicAnalysis(asExpressionInOCL, self_Value, null, new Object[]{5.0, new SymbolicUnknownValue("p0", realType, null, null)});
		checkContents(symbolicAnalysis5, asExpressionInOCL, null, null, null, null);

		// 5 / not-null-maybe-zero
		SymbolicAnalysis symbolicAnalysis6 = ocl.getSymbolicAnalysis(asExpressionInOCL, self_Value, null, new Object[]{5, new SymbolicUnknownValue("p0", integerType, null, null)});
		checkContents(symbolicAnalysis6, asExpressionInOCL, null, null, null, null);

		ocl.dispose();
	}

	public void testSymbolicAnalysis_IfNullNameGuard() throws Exception {
		MyOCL ocl = new MyOCL("IfNullNameGuard", "IfNullNameGuard(x : Integer[?]) : Boolean",
				"(let v : String[?] = x?.toString() in if v = null then 'null' else v endif) <> null");
		ExpressionInOCL asExpressionInOCL = ocl.getExpressionInOCL();
		VariableDeclaration x_Parameter = PivotUtil.getOwnedParameter(asExpressionInOCL, 0);
		OperationCallExp ne_Exp = (OperationCallExp) asExpressionInOCL.getOwnedBody();
		NullLiteralExp null_ne_Exp = (NullLiteralExp) PivotUtil.getOwnedArgument(ne_Exp, 0);
		LetExp let_ne_Exp = (LetExp) PivotUtil.getOwnedSource(ne_Exp);
		OperationCallExp toString_let_ne_Exp = (OperationCallExp) PivotUtil.getOwnedInit(PivotUtil.getOwnedVariable(let_ne_Exp));
		VariableExp x_toString_let_ne_Exp = (VariableExp) PivotUtil.getOwnedSource(toString_let_ne_Exp);
		LetVariable let_ne_Variable = (LetVariable) PivotUtil.getOwnedVariable(let_ne_Exp);
		IfExp if_let_ne_Exp = (IfExp) PivotUtil.getOwnedIn(let_ne_Exp);
		OperationCallExp eq_if_let_ne_Exp = (OperationCallExp) PivotUtil.getOwnedCondition(if_let_ne_Exp);
		StringLiteralExp null_if_let_ne_Exp = (StringLiteralExp) PivotUtil.getOwnedThen(if_let_ne_Exp);
		VariableExp v_if_let_ne_Exp = (VariableExp) PivotUtil.getOwnedElse(if_let_ne_Exp);
		VariableExp v_eq_if_let_ne_Exp = (VariableExp) PivotUtil.getOwnedSource(eq_if_let_ne_Exp);
		NullLiteralExp null_eq_if_let_ne_Exp = (NullLiteralExp) PivotUtil.getOwnedArgument(eq_if_let_ne_Exp, 0);

		SymbolicVariableValue self_Value = new SymbolicVariableValue(asExpressionInOCL.getOwnedContext(), null, null);

		// null x
		SymbolicAnalysis symbolicAnalysis1 = ocl.getSymbolicAnalysis(asExpressionInOCL, self_Value, null, new Object[] {null});
		checkContents(symbolicAnalysis1, asExpressionInOCL, isDeads(v_if_let_ne_Exp), mayBeNulls(x_Parameter, x_toString_let_ne_Exp, toString_let_ne_Exp, let_ne_Variable, v_eq_if_let_ne_Exp, null_eq_if_let_ne_Exp, null_ne_Exp), null, null);
		assertTrue("Result",  ocl.getIdResolver().oclEquals(symbolicAnalysis1.getBaseSymbolicEvaluationEnvironment().getSymbolicValue(if_let_ne_Exp).getKnownValue(), "null"));

		// non-null known x
		SymbolicAnalysis symbolicAnalysis2 = ocl.getSymbolicAnalysis(asExpressionInOCL, self_Value, null, new Object[] {99});
		checkContents(symbolicAnalysis2, asExpressionInOCL, isDeads(null_if_let_ne_Exp), mayBeNulls(null_eq_if_let_ne_Exp, null_ne_Exp), null, null);

		// non-null unknown x
		SymbolicVariableValue symbolicVariable3 = new SymbolicVariableValue(asExpressionInOCL.getOwnedParameters().get(0), null, null);
		SymbolicAnalysis symbolicAnalysis3 = ocl.getSymbolicAnalysis(asExpressionInOCL, self_Value, null, new Object[] {symbolicVariable3});
		checkContents(symbolicAnalysis3, asExpressionInOCL, isDeads(null_if_let_ne_Exp), mayBeNulls(null_eq_if_let_ne_Exp, null_ne_Exp), null, null);

		// maybe-null unknown x
		SymbolicVariableValue symbolicVariable4 = new SymbolicVariableValue(asExpressionInOCL.getOwnedParameters().get(0), SymbolicSimpleReason.MAY_BE_NULL_REASON, null);
		SymbolicAnalysis symbolicAnalysis4 = ocl.getSymbolicAnalysis(asExpressionInOCL, self_Value, null, new Object[] {symbolicVariable4});
		checkContents(symbolicAnalysis4, asExpressionInOCL, null, mayBeNulls(x_Parameter, let_ne_Exp, x_toString_let_ne_Exp, toString_let_ne_Exp, let_ne_Variable, if_let_ne_Exp, v_if_let_ne_Exp, v_eq_if_let_ne_Exp, null_eq_if_let_ne_Exp, null_ne_Exp), null, null);

		ocl.dispose();
	}

	public void testSymbolicAnalysis_IfOclIsUnDefinedNameGuard() throws Exception {
		MyOCL ocl = new MyOCL("IfOclIsUnDefinedNameGuard", "IfOclIsUnDefinedNameGuard(x : Deductions[?]) : Dummy[?]",
				"if x.mayBeNullDummy.oclIsUndefined() then null else x.mayBeNullDummy.dummy endif");
		ExpressionInOCL asExpressionInOCL = ocl.getExpressionInOCL();
		VariableDeclaration x_Parameter = PivotUtil.getOwnedParameter(asExpressionInOCL, 0);
		IfExp if_Exp = (IfExp) asExpressionInOCL.getOwnedBody();
		OperationCallExp oclIsUndefined_if_Exp = (OperationCallExp) PivotUtil.getOwnedCondition(if_Exp);
		PropertyCallExp mayBeNullDummy_oclIsUndefined_if_Exp = (PropertyCallExp) PivotUtil.getOwnedSource(oclIsUndefined_if_Exp);
		VariableExp x_mayBeNullDummy_oclIsUndefined_if_Exp = (VariableExp) PivotUtil.getOwnedSource(mayBeNullDummy_oclIsUndefined_if_Exp);
		NullLiteralExp null_if_Exp = (NullLiteralExp) PivotUtil.getOwnedThen(if_Exp);
		PropertyCallExp dummy_if_Exp = (PropertyCallExp) PivotUtil.getOwnedElse(if_Exp);
		PropertyCallExp mayBeNullDummy_dummy_if_Exp = (PropertyCallExp) PivotUtil.getOwnedSource(dummy_if_Exp);
		VariableExp x_mayBeNullDummy_dummy_if_Exp = (VariableExp) PivotUtil.getOwnedSource(mayBeNullDummy_dummy_if_Exp);

		SymbolicVariableValue self_Value = new SymbolicVariableValue(asExpressionInOCL.getOwnedContext(), null, null);

		// null x
		SymbolicAnalysis symbolicAnalysis1 = ocl.getSymbolicAnalysis(asExpressionInOCL, self_Value, null, new Object[] {null});
		checkContents(symbolicAnalysis1, asExpressionInOCL, isDeads(dummy_if_Exp, mayBeNullDummy_dummy_if_Exp, x_mayBeNullDummy_dummy_if_Exp), mayBeNulls(x_Parameter, x_mayBeNullDummy_oclIsUndefined_if_Exp, asExpressionInOCL, if_Exp, null_if_Exp), null, isInvalids(mayBeNullDummy_oclIsUndefined_if_Exp));

		// unknown may-be-null x
		SymbolicVariableValue symbolicVariable2 = new SymbolicVariableValue(asExpressionInOCL.getOwnedParameters().get(0), SymbolicSimpleReason.MAY_BE_NULL_REASON, null);
		SymbolicAnalysis symbolicAnalysis2 = ocl.getSymbolicAnalysis(asExpressionInOCL, self_Value, null, new Object[] {symbolicVariable2});
		checkContents(symbolicAnalysis2, asExpressionInOCL, null, mayBeNulls(x_Parameter, asExpressionInOCL, if_Exp, x_mayBeNullDummy_oclIsUndefined_if_Exp, null_if_Exp, dummy_if_Exp), mayBeInvalids(mayBeNullDummy_oclIsUndefined_if_Exp), null);

		// non-null unknown x
		SymbolicVariableValue symbolicVariable3 = new SymbolicVariableValue(asExpressionInOCL.getOwnedParameters().get(0), null, null);
		SymbolicAnalysis symbolicAnalysis3 = ocl.getSymbolicAnalysis(asExpressionInOCL, self_Value, null, new Object[] {symbolicVariable3});
		checkContents(symbolicAnalysis3, asExpressionInOCL, null, mayBeNulls(asExpressionInOCL, if_Exp, mayBeNullDummy_oclIsUndefined_if_Exp, null_if_Exp, dummy_if_Exp), null, null);

		// non-null known x null x.mayBeNullDummy
		EObject deductionsInstance4 = ocl.createDeductionsInstance();
		SymbolicAnalysis symbolicAnalysis4 = ocl.getSymbolicAnalysis(asExpressionInOCL, self_Value, null, new Object[] {deductionsInstance4});
		checkContents(symbolicAnalysis4, asExpressionInOCL, isDeads(dummy_if_Exp, mayBeNullDummy_dummy_if_Exp, x_mayBeNullDummy_dummy_if_Exp), mayBeNulls(asExpressionInOCL, if_Exp, mayBeNullDummy_oclIsUndefined_if_Exp, null_if_Exp), null, null);

		// non-null known x non-null x.mayBeNullDummy
		EObject dummyInstance5 = ocl.createDummyInstance();
		EObject deductionsInstance5 = ocl.createDeductionsInstance();
		deductionsInstance5.eSet((EStructuralFeature) ocl.deductionsClass_mayBeNullDummy.getESObject(), dummyInstance5);
		SymbolicAnalysis symbolicAnalysis5 = ocl.getSymbolicAnalysis(asExpressionInOCL, self_Value, null, new Object[] {deductionsInstance5});
		checkContents(symbolicAnalysis5, asExpressionInOCL, isDeads(null_if_Exp), mayBeNulls(asExpressionInOCL, if_Exp, dummy_if_Exp), null, null);

		ocl.dispose();
	}

	public void testSymbolicAnalysis_ImpliesGuard() throws Exception {
			MyOCL ocl = new MyOCL("ImpliesGuard", "ImpliesGuard(x : Integer[?]) : Boolean",
					"x <> null implies x > 0");
			ExpressionInOCL asExpressionInOCL = ocl.getExpressionInOCL();
			VariableDeclaration x_Parameter = PivotUtil.getOwnedParameter(asExpressionInOCL, 0);
			OperationCallExp implies_Exp = (OperationCallExp) asExpressionInOCL.getOwnedBody();
			OperationCallExp ne_implies_Exp = (OperationCallExp) PivotUtil.getOwnedSource(implies_Exp);
			OperationCallExp gt_implies_Exp = (OperationCallExp) PivotUtil.getOwnedArgument(implies_Exp, 0);
			VariableExp x_ne_implies_Exp = (VariableExp) PivotUtil.getOwnedSource(ne_implies_Exp);
			NullLiteralExp null_ne_implies_Exp = (NullLiteralExp) PivotUtil.getOwnedArgument(ne_implies_Exp, 0);
			VariableExp x_gt_implies_Exp = (VariableExp) PivotUtil.getOwnedSource(gt_implies_Exp);
			NumericLiteralExp zero_gt_implies_Exp = (NumericLiteralExp) PivotUtil.getOwnedArgument(gt_implies_Exp, 0);

			PrimitiveType integerType = ocl.getStandardLibrary().getIntegerType();
			SymbolicVariableValue self_Value = new SymbolicVariableValue(asExpressionInOCL.getOwnedContext(), null, null);

			// may-be-null x
			SymbolicVariableValue symbolicVariable = new SymbolicVariableValue(asExpressionInOCL.getOwnedParameters().get(0), SymbolicSimpleReason.MAY_BE_NULL_REASON, null);
			SymbolicAnalysis symbolicAnalysis3 = ocl.getSymbolicAnalysis(asExpressionInOCL, self_Value, null, new Object[]{symbolicVariable});
			checkContents(symbolicAnalysis3, asExpressionInOCL, null, mayBeNulls(x_Parameter, x_ne_implies_Exp, null_ne_implies_Exp), null, null);

			// non-null known x
			SymbolicAnalysis symbolicAnalysis1 = ocl.getSymbolicAnalysis(asExpressionInOCL, self_Value, null, new Object[]{5});
			checkContents(symbolicAnalysis1, asExpressionInOCL, isDeads(ne_implies_Exp, x_ne_implies_Exp, null_ne_implies_Exp), null, null, null);
			BaseSymbolicEvaluationEnvironment symbolicEvaluationEnvironment1 = symbolicAnalysis1.getBaseSymbolicEvaluationEnvironment();
			assertTrue(symbolicEvaluationEnvironment1.isTrue(gt_implies_Exp));
			assertTrue(symbolicEvaluationEnvironment1.isTrue(asExpressionInOCL));

			// null x
			SymbolicAnalysis symbolicAnalysis2 = ocl.getSymbolicAnalysis(asExpressionInOCL, self_Value, null, new Object[]{null});
			checkContents(symbolicAnalysis2, asExpressionInOCL, isDeads(gt_implies_Exp, x_gt_implies_Exp, zero_gt_implies_Exp), mayBeNulls(x_Parameter, x_ne_implies_Exp, null_ne_implies_Exp), null, null);
			BaseSymbolicEvaluationEnvironment symbolicEvaluationEnvironment2 = symbolicAnalysis2.getBaseSymbolicEvaluationEnvironment();
			assertTrue(symbolicEvaluationEnvironment2.isFalse(ne_implies_Exp));
			assertTrue(symbolicEvaluationEnvironment2.isTrue(asExpressionInOCL));

			// non-null unknown x
			SymbolicAnalysis symbolicAnalysis4 = ocl.getSymbolicAnalysis(asExpressionInOCL, self_Value, null, new Object[]{new SymbolicUnknownValue("p0", integerType, null, null)});
			checkContents(symbolicAnalysis4, asExpressionInOCL, null, mayBeNulls(null_ne_implies_Exp), null, null);
			BaseSymbolicEvaluationEnvironment symbolicEvaluationEnvironment4 = symbolicAnalysis4.getBaseSymbolicEvaluationEnvironment();
			assertFalse(symbolicEvaluationEnvironment4.mayBeInvalidOrNull(gt_implies_Exp));
			assertFalse(symbolicEvaluationEnvironment4.mayBeInvalidOrNull(asExpressionInOCL));

			ocl.dispose();
		}

	public void testSymbolicAnalysis_Implies2Guard() throws Exception {
			MyOCL ocl = new MyOCL("Implies2Guard", "Implies2Guard(x : Integer[?]) : Boolean",
					"x <> null implies2 x > 0");
			ExpressionInOCL asExpressionInOCL = ocl.getExpressionInOCL();
			VariableDeclaration x_Parameter = PivotUtil.getOwnedParameter(asExpressionInOCL, 0);
			OperationCallExp implies2_Exp = (OperationCallExp) asExpressionInOCL.getOwnedBody();
			OperationCallExp ne_implies2_Exp = (OperationCallExp) PivotUtil.getOwnedSource(implies2_Exp);
			OperationCallExp gt_implies2_Exp = (OperationCallExp) PivotUtil.getOwnedArgument(implies2_Exp, 0);
			VariableExp x_ne_implies2_Exp = (VariableExp) PivotUtil.getOwnedSource(ne_implies2_Exp);
			NullLiteralExp null_ne_implies2_Exp = (NullLiteralExp) PivotUtil.getOwnedArgument(ne_implies2_Exp, 0);
			VariableExp x_gt_implies2_Exp = (VariableExp) PivotUtil.getOwnedSource(gt_implies2_Exp);
			NumericLiteralExp zero_gt_implies2_Exp = (NumericLiteralExp) PivotUtil.getOwnedArgument(gt_implies2_Exp, 0);

			PrimitiveType integerType = ocl.getStandardLibrary().getIntegerType();
			SymbolicVariableValue self_Value = new SymbolicVariableValue(asExpressionInOCL.getOwnedContext(), null, null);

			// non-null known x
			SymbolicAnalysis symbolicAnalysis1 = ocl.getSymbolicAnalysis(asExpressionInOCL, self_Value, null, new Object[]{5});
			checkContents(symbolicAnalysis1, asExpressionInOCL, isDeads(ne_implies2_Exp, x_ne_implies2_Exp, null_ne_implies2_Exp), null, null, null);
			BaseSymbolicEvaluationEnvironment symbolicEvaluationEnvironment1 = symbolicAnalysis1.getBaseSymbolicEvaluationEnvironment();
			assertTrue(symbolicEvaluationEnvironment1.isTrue(gt_implies2_Exp));
			assertTrue(symbolicEvaluationEnvironment1.isTrue(asExpressionInOCL));

			// null x
			SymbolicAnalysis symbolicAnalysis2 = ocl.getSymbolicAnalysis(asExpressionInOCL, self_Value, null, new Object[]{null});
			checkContents(symbolicAnalysis2, asExpressionInOCL, isDeads(gt_implies2_Exp, x_gt_implies2_Exp, zero_gt_implies2_Exp), mayBeNulls(x_Parameter, x_ne_implies2_Exp, null_ne_implies2_Exp), null, null);
			BaseSymbolicEvaluationEnvironment symbolicEvaluationEnvironment2 = symbolicAnalysis2.getBaseSymbolicEvaluationEnvironment();
			assertTrue(symbolicEvaluationEnvironment2.isFalse(ne_implies2_Exp));
			assertTrue(symbolicEvaluationEnvironment2.isTrue(asExpressionInOCL));

			// may-be-null x
			SymbolicVariableValue symbolicVariable = new SymbolicVariableValue(asExpressionInOCL.getOwnedParameters().get(0), SymbolicSimpleReason.MAY_BE_NULL_REASON, null);
			SymbolicAnalysis symbolicAnalysis3 = ocl.getSymbolicAnalysis(asExpressionInOCL, self_Value, null, new Object[]{symbolicVariable});
			checkContents(symbolicAnalysis3, asExpressionInOCL, null, mayBeNulls(x_Parameter, x_ne_implies2_Exp, null_ne_implies2_Exp), null, null);

			// non-null unknown x
			SymbolicAnalysis symbolicAnalysis4 = ocl.getSymbolicAnalysis(asExpressionInOCL, self_Value, null, new Object[]{new SymbolicUnknownValue("p0", integerType, null, null)});
			checkContents(symbolicAnalysis4, asExpressionInOCL, null, mayBeNulls(null_ne_implies2_Exp), null, null);
			BaseSymbolicEvaluationEnvironment symbolicEvaluationEnvironment4 = symbolicAnalysis4.getBaseSymbolicEvaluationEnvironment();
			assertFalse(symbolicEvaluationEnvironment4.mayBeInvalidOrNull(gt_implies2_Exp));
			assertFalse(symbolicEvaluationEnvironment4.mayBeInvalidOrNull(asExpressionInOCL));

			ocl.dispose();
		}

	public void testSymbolicAnalysis_IndirectAndGuard() throws Exception {
			MyOCL ocl = new MyOCL("IndirectAndGuard", "IndirectAndGuard(x : Deductions[?]) : Boolean",
					"x?.mayBeNullDummy <> null and x?.mayBeNullDummy.dummy = null");
			ExpressionInOCL asExpressionInOCL = ocl.getExpressionInOCL();
			VariableDeclaration x_Parameter = PivotUtil.getOwnedParameter(asExpressionInOCL, 0);
			OperationCallExp and_Exp = (OperationCallExp) asExpressionInOCL.getOwnedBody();
			OperationCallExp ne_and_Exp = (OperationCallExp) PivotUtil.getOwnedSource(and_Exp);
			PropertyCallExp mayBeNullDummy_ne_and_Exp = (PropertyCallExp) PivotUtil.getOwnedSource(ne_and_Exp);
			VariableExp x_mayBeNullDummy_ne_and_Exp = (VariableExp) PivotUtil.getOwnedSource(mayBeNullDummy_ne_and_Exp);
			OperationCallExp eq_and_Exp = (OperationCallExp) PivotUtil.getOwnedArgument(and_Exp, 0);
			PropertyCallExp dummy_eq_and_Exp = (PropertyCallExp) PivotUtil.getOwnedSource(eq_and_Exp);
			PropertyCallExp mayBeNullDummy_dummy_eq_and_Exp = (PropertyCallExp) PivotUtil.getOwnedSource(dummy_eq_and_Exp);
			VariableExp x_mayBeNullDummy_dummy_eq_and_Exp = (VariableExp) PivotUtil.getOwnedSource(mayBeNullDummy_dummy_eq_and_Exp);
			NullLiteralExp null_ne_and_Exp = (NullLiteralExp) PivotUtil.getOwnedArgument(ne_and_Exp, 0);
		//	VariableExp gtSourceExp = (VariableExp) PivotUtil.getOwnedSource(eq_and_Exp);
			NullLiteralExp null_eq_and_Exp = (NullLiteralExp) PivotUtil.getOwnedArgument(eq_and_Exp, 0);

			SymbolicVariableValue self_Value = new SymbolicVariableValue(asExpressionInOCL.getOwnedContext(), null, null);

			// null x
			SymbolicAnalysis symbolicAnalysis1 = ocl.getSymbolicAnalysis(asExpressionInOCL, self_Value, null, new Object[]{null});
			checkContents(symbolicAnalysis1, asExpressionInOCL, isDeads(eq_and_Exp, dummy_eq_and_Exp, mayBeNullDummy_dummy_eq_and_Exp, x_mayBeNullDummy_dummy_eq_and_Exp, null_eq_and_Exp), mayBeNulls(x_Parameter, mayBeNullDummy_ne_and_Exp, x_mayBeNullDummy_ne_and_Exp, null_ne_and_Exp), null, null);
			assertTrue(symbolicAnalysis1.getBaseSymbolicEvaluationEnvironment().isFalse(ne_and_Exp));
			assertTrue(symbolicAnalysis1.getBaseSymbolicEvaluationEnvironment().isFalse(and_Exp));
			assertTrue(symbolicAnalysis1.getBaseSymbolicEvaluationEnvironment().isFalse(asExpressionInOCL));

		/*	// null self
			SymbolicEvaluationVisitor symbolicAnalysis2 = ocl.getSymbolicAnalysis(asExpressionInOCL, null, null, new Object[]{null});
			checkContents(symbolicAnalysis2, asExpressionInOCL, isDeads(gt_implies2_Exp, gtSourceExp, zeroExp), mayBeNulls(x_ne_implies2_Exp, null_ne_implies2_Exp}, null, null);
			assertTrue(symbolicAnalysis2.isFalse(ne_and_Exp));
			assertTrue(symbolicAnalysis2.isTrue(asExpressionInOCL));

			// may-be-null self
			SymbolicVariableValueImpl symbolicVariable = new SymbolicVariableValueImpl(asExpressionInOCL.getOwnedParameters().get(0), SymbolicSimpleReason.MAY_BE_NULL_REASON, null);
			SymbolicEvaluationVisitor symbolicAnalysis3 = ocl.getSymbolicAnalysis(asExpressionInOCL, null, null, new Object[]{symbolicVariable});
			checkContents(symbolicAnalysis3, asExpressionInOCL, null, mayBeNulls(x_ne_implies2_Exp, null_ne_implies2_Exp}, null, null);
	*/
			ocl.dispose();
		}

	public void testSymbolicAnalysis_IndirectAndGuard_Operation() throws Exception {
			MyOCL ocl = new MyOCL("IndirectAndGuard_Operation", "IndirectAndGuard_Operation(x : Deductions[?]) : Boolean",
					"x?.mayBeNullDummy <> null and x?.mayBeNullDummy.func(1,2) = null");
			ExpressionInOCL asExpressionInOCL = ocl.getExpressionInOCL();
		//	VariableDeclaration self_Parameter = PivotUtil.getOwnedContext(asExpressionInOCL);
			VariableDeclaration x_Parameter = PivotUtil.getOwnedParameter(asExpressionInOCL, 0);
			OperationCallExp and_Exp = (OperationCallExp) asExpressionInOCL.getOwnedBody();
			OperationCallExp ne_and_Exp = (OperationCallExp) PivotUtil.getOwnedSource(and_Exp);
			PropertyCallExp mayBeNullDummy_ne_and_Exp = (PropertyCallExp) PivotUtil.getOwnedSource(ne_and_Exp);
			VariableExp x_mayBeNullDummy_ne_and_Exp = (VariableExp) PivotUtil.getOwnedSource(mayBeNullDummy_ne_and_Exp);
			OperationCallExp eq_and_Exp = (OperationCallExp) PivotUtil.getOwnedArgument(and_Exp, 0);
			OperationCallExp func_eq_and_Exp = (OperationCallExp) PivotUtil.getOwnedSource(eq_and_Exp);
			PropertyCallExp mayBeNullDummy_func_eq_and_Exp = (PropertyCallExp) PivotUtil.getOwnedSource(func_eq_and_Exp);
			VariableExp x_mayBeNullDummy_func_eq_and_Exp = (VariableExp) PivotUtil.getOwnedSource(mayBeNullDummy_func_eq_and_Exp);
			NullLiteralExp null_ne_and_Exp = (NullLiteralExp) PivotUtil.getOwnedArgument(ne_and_Exp, 0);
		//	VariableExp gtSourceExp = (VariableExp) PivotUtil.getOwnedSource(eq_and_Exp);
			NullLiteralExp null_eq_and_Exp = (NullLiteralExp) PivotUtil.getOwnedArgument(eq_and_Exp, 0);
			IntegerLiteralExp a0_func_eq_and_Exp = (IntegerLiteralExp) PivotUtil.getOwnedArgument(func_eq_and_Exp, 0);
			IntegerLiteralExp a1_func_eq_and_Exp = (IntegerLiteralExp) PivotUtil.getOwnedArgument(func_eq_and_Exp, 1);

			SymbolicVariableValue self_Value = new SymbolicVariableValue(asExpressionInOCL.getOwnedContext(), null, null);

			// null x
			SymbolicAnalysis symbolicAnalysis1 = ocl.getSymbolicAnalysis(asExpressionInOCL, self_Value, null, new Object[]{null});
			checkContents(symbolicAnalysis1, asExpressionInOCL, isDeads(eq_and_Exp, func_eq_and_Exp, mayBeNullDummy_func_eq_and_Exp, x_mayBeNullDummy_func_eq_and_Exp, null_eq_and_Exp, a0_func_eq_and_Exp, a1_func_eq_and_Exp), mayBeNulls(x_Parameter, mayBeNullDummy_ne_and_Exp, x_mayBeNullDummy_ne_and_Exp, null_ne_and_Exp), null, null);
			assertTrue(symbolicAnalysis1.getBaseSymbolicEvaluationEnvironment().isFalse(ne_and_Exp));
			assertTrue(symbolicAnalysis1.getBaseSymbolicEvaluationEnvironment().isFalse(and_Exp));
			assertTrue(symbolicAnalysis1.getBaseSymbolicEvaluationEnvironment().isFalse(asExpressionInOCL));

		/*	// null self
			SymbolicEvaluationVisitor symbolicAnalysis2 = ocl.getSymbolicAnalysis(asExpressionInOCL, null, null, new Object[]{null});
			checkContents(symbolicAnalysis2, asExpressionInOCL, isDeads(gt_implies2_Exp, gtSourceExp, zeroExp), mayBeNulls(x_ne_implies2_Exp, null_ne_implies2_Exp}, null, null);
			assertTrue(symbolicAnalysis2.isFalse(ne_and_Exp));
			assertTrue(symbolicAnalysis2.isTrue(asExpressionInOCL));

			// may-be-null self
			SymbolicVariableValueImpl symbolicVariable = new SymbolicVariableValueImpl(asExpressionInOCL.getOwnedParameters().get(0), SymbolicSimpleReason.MAY_BE_NULL_REASON, null);
			SymbolicEvaluationVisitor symbolicAnalysis3 = ocl.getSymbolicAnalysis(asExpressionInOCL, null, null, new Object[]{symbolicVariable});
			checkContents(symbolicAnalysis3, asExpressionInOCL, null, mayBeNulls(x_ne_implies2_Exp, null_ne_implies2_Exp}, null, null);
	*/
			ocl.dispose();
		}

	public void testSymbolicAnalysis_InvalidLiteral() throws Exception {	// extracted from OCLtest.ocl Apple::stem
		MyOCL ocl = new MyOCL("InvalidLiteral", "InvalidLiteral(x : Dummy[?]) : Dummy[?]",
				"if x.dummy.oclIsUndefined() then null else invalid endif");
		ExpressionInOCL asExpressionInOCL = ocl.getExpressionInOCL();
		VariableDeclaration x_Parameter = PivotUtil.getOwnedParameter(asExpressionInOCL, 0);
		IfExp if_Exp = (IfExp) asExpressionInOCL.getOwnedBody();
		OperationCallExp oclIsUndefined_if_Exp = (OperationCallExp) PivotUtil.getOwnedCondition(if_Exp);
		PropertyCallExp dummy_oclIsUndefined_if_Exp = (PropertyCallExp) PivotUtil.getOwnedSource(oclIsUndefined_if_Exp);
		VariableExp x_dummy_oclIsUndefined_if_Exp = (VariableExp) PivotUtil.getOwnedSource(dummy_oclIsUndefined_if_Exp);
		NullLiteralExp null_if_Exp = (NullLiteralExp) PivotUtil.getOwnedThen(if_Exp);
		InvalidLiteralExp invalid_if_Exp = (InvalidLiteralExp) PivotUtil.getOwnedElse(if_Exp);

		SymbolicVariableValue self_Value = new SymbolicVariableValue(asExpressionInOCL.getOwnedContext(), null, null);

		// null x
		SymbolicAnalysis symbolicAnalysis1 = ocl.getSymbolicAnalysis(asExpressionInOCL, self_Value, null, new Object[] {null});
		checkContents(symbolicAnalysis1, asExpressionInOCL, isDeads(invalid_if_Exp), mayBeNulls(x_Parameter, x_dummy_oclIsUndefined_if_Exp, asExpressionInOCL, if_Exp, null_if_Exp), null, isInvalids(dummy_oclIsUndefined_if_Exp));

		// unknown may-be-null x
		SymbolicVariableValue symbolicVariable2 = new SymbolicVariableValue(asExpressionInOCL.getOwnedParameters().get(0), SymbolicSimpleReason.MAY_BE_NULL_REASON, null);
		SymbolicAnalysis symbolicAnalysis2 = ocl.getSymbolicAnalysis(asExpressionInOCL, self_Value, null, new Object[] {symbolicVariable2});
		checkContents(symbolicAnalysis2, asExpressionInOCL, null, mayBeNulls(x_Parameter, asExpressionInOCL, if_Exp, x_dummy_oclIsUndefined_if_Exp, null_if_Exp, dummy_oclIsUndefined_if_Exp), mayBeInvalids(asExpressionInOCL, if_Exp, dummy_oclIsUndefined_if_Exp), isInvalids(invalid_if_Exp));

		// non-null unknown x
		SymbolicVariableValue symbolicVariable3 = new SymbolicVariableValue(asExpressionInOCL.getOwnedParameters().get(0), null, null);
		SymbolicAnalysis symbolicAnalysis3 = ocl.getSymbolicAnalysis(asExpressionInOCL, self_Value, null, new Object[] {symbolicVariable3});
		checkContents(symbolicAnalysis3, asExpressionInOCL, null, mayBeNulls(asExpressionInOCL, if_Exp, dummy_oclIsUndefined_if_Exp, null_if_Exp), mayBeInvalids(asExpressionInOCL, if_Exp), isInvalids(invalid_if_Exp));

		// non-null known x null x.dummy
		EObject dummyInstance4 = ocl.createDummyInstance();
		SymbolicAnalysis symbolicAnalysis4 = ocl.getSymbolicAnalysis(asExpressionInOCL, self_Value, null, new Object[] {dummyInstance4});
		checkContents(symbolicAnalysis4, asExpressionInOCL, isDeads(invalid_if_Exp), mayBeNulls(asExpressionInOCL, if_Exp, dummy_oclIsUndefined_if_Exp, null_if_Exp), null, null);

		// non-null known x non-null x.dummy
		EObject dummyInstance5 = ocl.createDummyInstance();
		dummyInstance5.eSet((EStructuralFeature) ocl.dummyClass_dummy.getESObject(), dummyInstance5);
		SymbolicAnalysis symbolicAnalysis5 = ocl.getSymbolicAnalysis(asExpressionInOCL, self_Value, null, new Object[] {dummyInstance5});
		checkContents(symbolicAnalysis5, asExpressionInOCL, isDeads(null_if_Exp), null, null, isInvalids(asExpressionInOCL, if_Exp, invalid_if_Exp));

		ocl.dispose();
	}

	public void testSymbolicAnalysis_NotEmptyGuardedFirst() throws Exception {
		MyOCL ocl = new MyOCL("GuardedFirst", "GuardedFirst(x : Sequence(Integer)[?]) : Integer",
				"if x->notEmpty() then x->first() else -1 endif");
		ExpressionInOCL asExpressionInOCL = ocl.getExpressionInOCL();
		VariableDeclaration x_Parameter = PivotUtil.getOwnedParameter(asExpressionInOCL, 0);
		IfExp if_Exp = (IfExp) asExpressionInOCL.getOwnedBody();
		OperationCallExp notEmpty_if_Exp = (OperationCallExp) if_Exp.getOwnedCondition();
		VariableExp x_notEmpty_if_Exp = (VariableExp) PivotUtil.getOwnedSource(notEmpty_if_Exp);
		OperationCallExp first_if_Exp = (OperationCallExp) if_Exp.getOwnedThen();
		VariableExp x_first_if_Exp = (VariableExp) PivotUtil.getOwnedSource(first_if_Exp);
		OperationCallExp neg_if_Exp = (OperationCallExp) if_Exp.getOwnedElse();
		IntegerLiteralExp one_neg_if_Exp = (IntegerLiteralExp) neg_if_Exp.getOwnedSource();

		SymbolicVariableValue self_Value = new SymbolicVariableValue(asExpressionInOCL.getOwnedContext(), null, null);

		// non-null empty x
		SymbolicAnalysis symbolicAnalysis1a = ocl.getSymbolicAnalysis(asExpressionInOCL, self_Value, null, new Object[]{ValueUtil.EMPTY_SET});
		assertEquals("incompatible type \"Set(OclInvalid[0|1])\" for parameter \"x\"", symbolicAnalysis1a.getAnalysisIncompatibility());

		// non-null empty x
		SymbolicAnalysis symbolicAnalysis1 = ocl.getSymbolicAnalysis(asExpressionInOCL, self_Value, null, new Object[]{ValueUtil.EMPTY_SEQUENCE});
		checkContents(symbolicAnalysis1, asExpressionInOCL, isDeads(first_if_Exp, x_first_if_Exp), null, null, null);
		BaseSymbolicEvaluationEnvironment symbolicEvaluationEnvironment1 = symbolicAnalysis1.getBaseSymbolicEvaluationEnvironment();
		assertTrue(symbolicEvaluationEnvironment1.isFalse(notEmpty_if_Exp));

		// non-null not-empty x
		SequenceValue seq2 = ValueUtil.createSequenceOfEach(TypeId.SEQUENCE.getSpecializedId(TypeId.INTEGER), ValueUtil.integerValueOf(99));
		SymbolicAnalysis symbolicAnalysis2 = ocl.getSymbolicAnalysis(asExpressionInOCL, self_Value, null, new Object[]{seq2});
		checkContents(symbolicAnalysis2, asExpressionInOCL, isDeads(neg_if_Exp, one_neg_if_Exp), null, null, null);
		BaseSymbolicEvaluationEnvironment symbolicEvaluationEnvironment2 = symbolicAnalysis2.getBaseSymbolicEvaluationEnvironment();
		assertTrue(symbolicEvaluationEnvironment2.isTrue(notEmpty_if_Exp));

		// non-null unknown x
		SymbolicVariableValue symbolicVariable = new SymbolicVariableValue(asExpressionInOCL.getOwnedParameters().get(0), null, null);
		SymbolicAnalysis symbolicAnalysis3 = ocl.getSymbolicAnalysis(asExpressionInOCL, self_Value, null, new Object[]{symbolicVariable});
		checkContents(symbolicAnalysis3, asExpressionInOCL, null, null, null, null);

		// null collection x (does not behave as empty set)
		SymbolicAnalysis symbolicAnalysis4 = ocl.getSymbolicAnalysis(asExpressionInOCL, self_Value, null, new Object[]{null});
		checkContents(symbolicAnalysis4, asExpressionInOCL, isDeads(first_if_Exp, x_first_if_Exp, neg_if_Exp, one_neg_if_Exp), mayBeNulls(x_Parameter, x_notEmpty_if_Exp), null, isInvalids(asExpressionInOCL, if_Exp, notEmpty_if_Exp));

		ocl.dispose();
	}

	public void testSymbolicAnalysis_NotIsEmptyGuardedFirst() throws Exception {		// XXX tweaked to fail
		MyOCL ocl = new MyOCL("GuardedFirst", "GuardedFirst(x : Sequence(Integer)[?]) : Integer",
				"if not x->isEmpty() then x->first() else -1 endif");
		ExpressionInOCL asExpressionInOCL = ocl.getExpressionInOCL();
		VariableDeclaration x_Parameter = PivotUtil.getOwnedParameter(asExpressionInOCL, 0);
		IfExp if_Exp = (IfExp) asExpressionInOCL.getOwnedBody();
		OperationCallExp not_if_Exp = (OperationCallExp) if_Exp.getOwnedCondition();
		OperationCallExp empty_not_if_Exp = (OperationCallExp) PivotUtil.getOwnedSource(not_if_Exp);
		VariableExp x_empty_not_if_Exp = (VariableExp) PivotUtil.getOwnedSource(empty_not_if_Exp);
		OperationCallExp firstExp = (OperationCallExp) if_Exp.getOwnedThen();
		VariableExp first_if_Exp = (VariableExp) PivotUtil.getOwnedSource(firstExp);
		OperationCallExp neg_if_Exp = (OperationCallExp) if_Exp.getOwnedElse();
		IntegerLiteralExp one_neg_if_Exp = (IntegerLiteralExp) neg_if_Exp.getOwnedSource();

		SymbolicVariableValue self_Value = new SymbolicVariableValue(asExpressionInOCL.getOwnedContext(), null, null);

		// non-null unknown x
		SymbolicVariableValue symbolicVariable3a = new SymbolicVariableValue(asExpressionInOCL.getOwnedParameters().get(0), null, null);
		SymbolicAnalysis symbolicAnalysis3a = ocl.getSymbolicAnalysis(asExpressionInOCL, self_Value, null, new Object[]{symbolicVariable3a});
		checkContents(symbolicAnalysis3a, asExpressionInOCL, null, null, null, null);


		// non-null empty x
		SymbolicAnalysis symbolicAnalysis1a = ocl.getSymbolicAnalysis(asExpressionInOCL, self_Value, null, new Object[]{ValueUtil.EMPTY_SET});
		assertEquals("incompatible type \"Set(OclInvalid[0|1])\" for parameter \"x\"", symbolicAnalysis1a.getAnalysisIncompatibility());

		// non-null empty x
		SymbolicAnalysis symbolicAnalysis1 = ocl.getSymbolicAnalysis(asExpressionInOCL, self_Value, null, new Object[]{ValueUtil.EMPTY_SEQUENCE});
		checkContents(symbolicAnalysis1, asExpressionInOCL, isDeads(firstExp, first_if_Exp), null, null, null);
		BaseSymbolicEvaluationEnvironment symbolicEvaluationEnvironment1 = symbolicAnalysis1.getBaseSymbolicEvaluationEnvironment();
		assertTrue(symbolicEvaluationEnvironment1.isFalse(not_if_Exp));

		// non-null not-empty x
		SequenceValue seq2 = ValueUtil.createSequenceOfEach(TypeId.SEQUENCE.getSpecializedId(TypeId.INTEGER), ValueUtil.integerValueOf(99));
		SymbolicAnalysis symbolicAnalysis2 = ocl.getSymbolicAnalysis(asExpressionInOCL, self_Value, null, new Object[]{seq2});
		checkContents(symbolicAnalysis2, asExpressionInOCL, isDeads(neg_if_Exp, one_neg_if_Exp), null, null, null);
		BaseSymbolicEvaluationEnvironment symbolicEvaluationEnvironment2 = symbolicAnalysis2.getBaseSymbolicEvaluationEnvironment();
		assertTrue(symbolicEvaluationEnvironment2.isTrue(not_if_Exp));

		// non-null unknown x
		SymbolicVariableValue symbolicVariable = new SymbolicVariableValue(asExpressionInOCL.getOwnedParameters().get(0), null, null);
		SymbolicAnalysis symbolicAnalysis3 = ocl.getSymbolicAnalysis(asExpressionInOCL, self_Value, null, new Object[]{symbolicVariable});
		checkContents(symbolicAnalysis3, asExpressionInOCL, null, null, null, null);

		// null collection x (does not behave as empty set)
		SymbolicAnalysis symbolicAnalysis4 = ocl.getSymbolicAnalysis(asExpressionInOCL, self_Value, null, new Object[]{null});
		checkContents(symbolicAnalysis4, asExpressionInOCL, isDeads(firstExp, first_if_Exp, neg_if_Exp, one_neg_if_Exp), mayBeNulls(x_Parameter, x_empty_not_if_Exp), null, isInvalids(asExpressionInOCL, if_Exp, not_if_Exp, empty_not_if_Exp));

		ocl.dispose();
	}

	public void testSymbolicAnalysis_SizeGtGuardedFirst() throws Exception {
		MyOCL ocl = new MyOCL("GuardedFirst", "GuardedFirst(x : Sequence(Integer)[?]) : Integer",
				"if x->size() > 0 then x->first() else -1 endif");
		ExpressionInOCL asExpressionInOCL = ocl.getExpressionInOCL();
		VariableDeclaration x_Parameter = PivotUtil.getOwnedParameter(asExpressionInOCL, 0);
		IfExp if_Exp = (IfExp) asExpressionInOCL.getOwnedBody();
		OperationCallExp gt_if_Exp = (OperationCallExp) if_Exp.getOwnedCondition();
		OperationCallExp size_gt_if_Exp = (OperationCallExp) PivotUtil.getOwnedSource(gt_if_Exp);
		VariableExp x_size_gt_if_Exp = (VariableExp) PivotUtil.getOwnedSource(size_gt_if_Exp);
		OperationCallExp first_ifExp = (OperationCallExp) if_Exp.getOwnedThen();
		VariableExp x_first_ifExp = (VariableExp) PivotUtil.getOwnedSource(first_ifExp);
		OperationCallExp neg_if_Exp = (OperationCallExp) if_Exp.getOwnedElse();
		IntegerLiteralExp one_neg_if_Exp = (IntegerLiteralExp) neg_if_Exp.getOwnedSource();

		SymbolicVariableValue self_Value = new SymbolicVariableValue(asExpressionInOCL.getOwnedContext(), null, null);

		// non-null empty x
		SymbolicAnalysis symbolicAnalysis1a = ocl.getSymbolicAnalysis(asExpressionInOCL, self_Value, null, new Object[]{ValueUtil.EMPTY_SET});
		assertEquals("incompatible type \"Set(OclInvalid[0|1])\" for parameter \"x\"", symbolicAnalysis1a.getAnalysisIncompatibility());

		// non-null empty x
		SymbolicAnalysis symbolicAnalysis1 = ocl.getSymbolicAnalysis(asExpressionInOCL, self_Value, null, new Object[]{ValueUtil.EMPTY_SEQUENCE});
		checkContents(symbolicAnalysis1, asExpressionInOCL, isDeads(first_ifExp, x_first_ifExp), null, null, null);
		BaseSymbolicEvaluationEnvironment symbolicEvaluationEnvironment1 = symbolicAnalysis1.getBaseSymbolicEvaluationEnvironment();
		assertTrue(symbolicEvaluationEnvironment1.isFalse(gt_if_Exp));

		// non-null not-empty x
		SequenceValue seq2 = ValueUtil.createSequenceOfEach(TypeId.SEQUENCE.getSpecializedId(TypeId.INTEGER), ValueUtil.integerValueOf(99));
		SymbolicAnalysis symbolicAnalysis2 = ocl.getSymbolicAnalysis(asExpressionInOCL, self_Value, null, new Object[]{seq2});
		checkContents(symbolicAnalysis2, asExpressionInOCL, isDeads(neg_if_Exp, one_neg_if_Exp), null, null, null);
		BaseSymbolicEvaluationEnvironment symbolicEvaluationEnvironment2 = symbolicAnalysis2.getBaseSymbolicEvaluationEnvironment();
		assertTrue(symbolicEvaluationEnvironment2.isTrue(gt_if_Exp));

		// non-null unknown x
		SymbolicVariableValue symbolicVariable = new SymbolicVariableValue(asExpressionInOCL.getOwnedParameters().get(0), null, null);
		SymbolicAnalysis symbolicAnalysis3 = ocl.getSymbolicAnalysis(asExpressionInOCL, self_Value, null, new Object[]{symbolicVariable});
		checkContents(symbolicAnalysis3, asExpressionInOCL, null, null, null, null);

		// null collection x (does not behave as empty set)
		SymbolicAnalysis symbolicAnalysis4 = ocl.getSymbolicAnalysis(asExpressionInOCL, self_Value, null, new Object[]{null});
		checkContents(symbolicAnalysis4, asExpressionInOCL, isDeads(first_ifExp, x_first_ifExp, neg_if_Exp, one_neg_if_Exp), mayBeNulls(x_Parameter, x_size_gt_if_Exp), null, isInvalids(asExpressionInOCL, if_Exp, gt_if_Exp, size_gt_if_Exp));

		ocl.dispose();
	}

	public void testSymbolicAnalysis_SizeNeGuardedFirst() throws Exception {
		MyOCL ocl = new MyOCL("GuardedFirst", "GuardedFirst(x : Sequence(Integer)[?]) : Integer",
				"if x->size() <> 0 then x->first() else -1 endif");
		ExpressionInOCL asExpressionInOCL = ocl.getExpressionInOCL();
		VariableDeclaration x_Parameter = PivotUtil.getOwnedParameter(asExpressionInOCL, 0);
		IfExp if_Exp = (IfExp) asExpressionInOCL.getOwnedBody();
		OperationCallExp ne_if_Exp = (OperationCallExp) if_Exp.getOwnedCondition();
		OperationCallExp size_ne_if_Exp = (OperationCallExp) PivotUtil.getOwnedSource(ne_if_Exp);
		VariableExp x_size_ne_if_Exp = (VariableExp) PivotUtil.getOwnedSource(size_ne_if_Exp);
		OperationCallExp first_if_Exp = (OperationCallExp) if_Exp.getOwnedThen();
		VariableExp x_first_if_Exp = (VariableExp) PivotUtil.getOwnedSource(first_if_Exp);
		OperationCallExp neg_if_Exp = (OperationCallExp) if_Exp.getOwnedElse();
		IntegerLiteralExp one_neg_if_Exp = (IntegerLiteralExp) neg_if_Exp.getOwnedSource();

		SymbolicVariableValue self_Value = new SymbolicVariableValue(asExpressionInOCL.getOwnedContext(), null, null);

		// non-null empty x
		SymbolicAnalysis symbolicAnalysis1a = ocl.getSymbolicAnalysis(asExpressionInOCL, self_Value, null, new Object[]{ValueUtil.EMPTY_SET});
		assertEquals("incompatible type \"Set(OclInvalid[0|1])\" for parameter \"x\"", symbolicAnalysis1a.getAnalysisIncompatibility());

		// non-null empty x
		SymbolicAnalysis symbolicAnalysis1 = ocl.getSymbolicAnalysis(asExpressionInOCL, self_Value, null, new Object[]{ValueUtil.EMPTY_SEQUENCE});
		checkContents(symbolicAnalysis1, asExpressionInOCL, isDeads(first_if_Exp, x_first_if_Exp), null, null, null);
		BaseSymbolicEvaluationEnvironment symbolicEvaluationEnvironment1 = symbolicAnalysis1.getBaseSymbolicEvaluationEnvironment();
		assertTrue(symbolicEvaluationEnvironment1.isFalse(ne_if_Exp));

		// non-null not-empty x
		SequenceValue seq2 = ValueUtil.createSequenceOfEach(TypeId.SEQUENCE.getSpecializedId(TypeId.INTEGER), ValueUtil.integerValueOf(99));
		SymbolicAnalysis symbolicAnalysis2 = ocl.getSymbolicAnalysis(asExpressionInOCL, self_Value, null, new Object[]{seq2});
		checkContents(symbolicAnalysis2, asExpressionInOCL, isDeads(neg_if_Exp, one_neg_if_Exp), null, null, null);
		BaseSymbolicEvaluationEnvironment symbolicEvaluationEnvironment2 = symbolicAnalysis2.getBaseSymbolicEvaluationEnvironment();
		assertTrue(symbolicEvaluationEnvironment2.isTrue(ne_if_Exp));

		// non-null unknown x
		SymbolicVariableValue symbolicVariable = new SymbolicVariableValue(asExpressionInOCL.getOwnedParameters().get(0), null, null);
		SymbolicAnalysis symbolicAnalysis3 = ocl.getSymbolicAnalysis(asExpressionInOCL, self_Value, null, new Object[]{symbolicVariable});
		checkContents(symbolicAnalysis3, asExpressionInOCL, null, null, null, null);

		// null collection x (does not behave as empty set)
		SymbolicAnalysis symbolicAnalysis4 = ocl.getSymbolicAnalysis(asExpressionInOCL, self_Value, null, new Object[]{null});
		checkContents(symbolicAnalysis4, asExpressionInOCL, isDeads(first_if_Exp, x_first_if_Exp, neg_if_Exp, one_neg_if_Exp), mayBeNulls(x_Parameter, x_size_ne_if_Exp), null, isInvalids(asExpressionInOCL, if_Exp, ne_if_Exp, size_ne_if_Exp));

		ocl.dispose();
	}

	public void testSymbolicAnalysis_OrGuard() throws Exception {
		MyOCL ocl = new MyOCL("OrGuard", "OrGuard(x : Integer[?]) : Boolean",
				"x = null or x > 0");
		ExpressionInOCL asExpressionInOCL = ocl.getExpressionInOCL();
		VariableDeclaration x_Parameter = PivotUtil.getOwnedParameter(asExpressionInOCL, 0);
		OperationCallExp orExp = (OperationCallExp) asExpressionInOCL.getOwnedBody();
		OperationCallExp eq_or_Exp = (OperationCallExp) PivotUtil.getOwnedSource(orExp);
		OperationCallExp gt_or_Exp = (OperationCallExp) PivotUtil.getOwnedArgument(orExp, 0);
		VariableExp x_eq_or_Exp = (VariableExp) PivotUtil.getOwnedSource(eq_or_Exp);
		NullLiteralExp null_eq_or_Exp = (NullLiteralExp) PivotUtil.getOwnedArgument(eq_or_Exp, 0);
		VariableExp x_gt_or_Exp = (VariableExp) PivotUtil.getOwnedSource(gt_or_Exp);
		NumericLiteralExp zero_gt_or_Exp = (NumericLiteralExp) PivotUtil.getOwnedArgument(gt_or_Exp, 0);

		PrimitiveType integerType = ocl.getStandardLibrary().getIntegerType();
		SymbolicVariableValue self_Value = new SymbolicVariableValue(asExpressionInOCL.getOwnedContext(), null, null);

		// non-null known ok x
		SymbolicAnalysis symbolicAnalysis1a = ocl.getSymbolicAnalysis(asExpressionInOCL, self_Value, null, new Object[]{5});
		checkContents(symbolicAnalysis1a, asExpressionInOCL, isDeads(eq_or_Exp, x_eq_or_Exp, null_eq_or_Exp), null, null, null);
		BaseSymbolicEvaluationEnvironment symbolicEvaluationEnvironment1a = symbolicAnalysis1a.getBaseSymbolicEvaluationEnvironment();
		assertTrue(symbolicEvaluationEnvironment1a.isTrue(gt_or_Exp));

		// non-null known bad x
		SymbolicAnalysis symbolicAnalysis1b = ocl.getSymbolicAnalysis(asExpressionInOCL, self_Value, null, new Object[]{-5});
		checkContents(symbolicAnalysis1b, asExpressionInOCL, null, mayBeNulls(null_eq_or_Exp), null, null);
		BaseSymbolicEvaluationEnvironment symbolicEvaluationEnvironment1b = symbolicAnalysis1b.getBaseSymbolicEvaluationEnvironment();
		assertTrue(symbolicEvaluationEnvironment1b.isFalse(gt_or_Exp));

		// null x
		SymbolicAnalysis symbolicAnalysis2 = ocl.getSymbolicAnalysis(asExpressionInOCL, self_Value, null, new Object[]{null});
		checkContents(symbolicAnalysis2, asExpressionInOCL, isDeads(gt_or_Exp, x_gt_or_Exp, zero_gt_or_Exp), mayBeNulls(x_Parameter, x_eq_or_Exp, null_eq_or_Exp), null, null);
		BaseSymbolicEvaluationEnvironment symbolicEvaluationEnvironment2 = symbolicAnalysis2.getBaseSymbolicEvaluationEnvironment();
		assertTrue(symbolicEvaluationEnvironment2.isTrue(eq_or_Exp));
		assertTrue(symbolicEvaluationEnvironment2.isTrue(asExpressionInOCL));

		// may-be-null x
		SymbolicVariableValue symbolicVariable = new SymbolicVariableValue(asExpressionInOCL.getOwnedParameters().get(0), SymbolicSimpleReason.MAY_BE_NULL_REASON, null);
		SymbolicAnalysis symbolicAnalysis3 = ocl.getSymbolicAnalysis(asExpressionInOCL, self_Value, null, new Object[]{symbolicVariable});
		checkContents(symbolicAnalysis3, asExpressionInOCL, null, mayBeNulls(x_Parameter, x_eq_or_Exp, null_eq_or_Exp), null, null);

		// non-null unknown x
		SymbolicAnalysis symbolicAnalysis4 = ocl.getSymbolicAnalysis(asExpressionInOCL, self_Value, null, new Object[]{new SymbolicUnknownValue("p0", integerType, null, null)});
		checkContents(symbolicAnalysis4, asExpressionInOCL, null, mayBeNulls(null_eq_or_Exp), null, null);
		BaseSymbolicEvaluationEnvironment symbolicEvaluationEnvironment4 = symbolicAnalysis4.getBaseSymbolicEvaluationEnvironment();
		assertFalse(symbolicEvaluationEnvironment4.mayBeInvalidOrNull(gt_or_Exp));
		assertFalse(symbolicEvaluationEnvironment4.mayBeInvalidOrNull(asExpressionInOCL));

		ocl.dispose();
	}

	public void testSymbolicAnalysis_Or2Guard() throws Exception {
		MyOCL ocl = new MyOCL("Or2Guard", "Or2Guard(x : Integer[?]) : Boolean",
				"x = null or2 x > 0");
		ExpressionInOCL asExpressionInOCL = ocl.getExpressionInOCL();
		VariableDeclaration self_Parameter = PivotUtil.getOwnedContext(asExpressionInOCL);
		VariableDeclaration x_Parameter = PivotUtil.getOwnedParameter(asExpressionInOCL, 0);
		OperationCallExp orExp = (OperationCallExp) asExpressionInOCL.getOwnedBody();
		OperationCallExp eq_or2_Exp = (OperationCallExp) PivotUtil.getOwnedSource(orExp);
		OperationCallExp gt_or2_Exp = (OperationCallExp) PivotUtil.getOwnedArgument(orExp, 0);
		VariableExp x_eq_or2_Exp = (VariableExp) PivotUtil.getOwnedSource(eq_or2_Exp);
		NullLiteralExp null_eq_or2_Exp = (NullLiteralExp) PivotUtil.getOwnedArgument(eq_or2_Exp, 0);
		VariableExp x_gt_or2_Exp = (VariableExp) PivotUtil.getOwnedSource(gt_or2_Exp);
		NumericLiteralExp zero_gt_or2_Exp = (NumericLiteralExp) PivotUtil.getOwnedArgument(gt_or2_Exp, 0);

		PrimitiveType integerType = ocl.getStandardLibrary().getIntegerType();
		SymbolicVariableValue self_Value = new SymbolicVariableValue(asExpressionInOCL.getOwnedContext(), null, null);

		// non-null known ok x
		SymbolicAnalysis symbolicAnalysis1a = ocl.getSymbolicAnalysis(asExpressionInOCL, self_Value, null, new Object[]{5});
		checkContents(symbolicAnalysis1a, asExpressionInOCL, isDeads(eq_or2_Exp, x_eq_or2_Exp, null_eq_or2_Exp), null, null, null);
		BaseSymbolicEvaluationEnvironment symbolicEvaluationEnvironment1a = symbolicAnalysis1a.getBaseSymbolicEvaluationEnvironment();
		assertTrue(symbolicEvaluationEnvironment1a.isTrue(gt_or2_Exp));

		// non-null known bad x
		SymbolicAnalysis symbolicAnalysis1b = ocl.getSymbolicAnalysis(asExpressionInOCL, self_Value, null, new Object[]{-5});
		checkContents(symbolicAnalysis1b, asExpressionInOCL, null, mayBeNulls(null_eq_or2_Exp), null, null);
		BaseSymbolicEvaluationEnvironment symbolicEvaluationEnvironment1b = symbolicAnalysis1b.getBaseSymbolicEvaluationEnvironment();
		assertTrue(symbolicEvaluationEnvironment1b.isFalse(gt_or2_Exp));

		// null x
		SymbolicAnalysis symbolicAnalysis2 = ocl.getSymbolicAnalysis(asExpressionInOCL, self_Value, null, new Object[]{null});
		checkContents(symbolicAnalysis2, asExpressionInOCL, isDeads(gt_or2_Exp, x_gt_or2_Exp, zero_gt_or2_Exp), mayBeNulls(x_Parameter, x_eq_or2_Exp, null_eq_or2_Exp), null, null);
		BaseSymbolicEvaluationEnvironment symbolicEvaluationEnvironment2 = symbolicAnalysis2.getBaseSymbolicEvaluationEnvironment();
		assertTrue(symbolicEvaluationEnvironment2.isTrue(eq_or2_Exp));
		assertTrue(symbolicEvaluationEnvironment2.isTrue(asExpressionInOCL));

		// may-be-null x
		SymbolicVariableValue symbolicVariable = new SymbolicVariableValue(asExpressionInOCL.getOwnedParameters().get(0), SymbolicSimpleReason.MAY_BE_NULL_REASON, null);
		SymbolicAnalysis symbolicAnalysis3 = ocl.getSymbolicAnalysis(asExpressionInOCL, self_Value, null, new Object[]{symbolicVariable});
		checkContents(symbolicAnalysis3, asExpressionInOCL, null, mayBeNulls(x_Parameter, x_eq_or2_Exp, null_eq_or2_Exp), null, null);

		// non-null unknown x
		SymbolicAnalysis symbolicAnalysis4 = ocl.getSymbolicAnalysis(asExpressionInOCL, self_Value, null, new Object[]{new SymbolicUnknownValue("p0", integerType, null, null)});
		checkContents(symbolicAnalysis4, asExpressionInOCL, null, mayBeNulls(null_eq_or2_Exp), null, null);
		BaseSymbolicEvaluationEnvironment symbolicEvaluationEnvironment4 = symbolicAnalysis4.getBaseSymbolicEvaluationEnvironment();
		assertFalse(symbolicEvaluationEnvironment4.mayBeInvalidOrNull(gt_or2_Exp));
		assertFalse(symbolicEvaluationEnvironment4.mayBeInvalidOrNull(asExpressionInOCL));

		ocl.dispose();
	}
}
