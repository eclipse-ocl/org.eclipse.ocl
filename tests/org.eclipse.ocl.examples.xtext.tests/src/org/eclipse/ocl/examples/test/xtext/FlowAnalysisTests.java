/*******************************************************************************
 * Copyright (c) 2010, 2021 Willink Transformations and others.
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

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.examples.pivot.tests.TestOCL;
import org.eclipse.ocl.examples.xtext.tests.XtextTestCase;
import org.eclipse.ocl.pivot.Constraint;
import org.eclipse.ocl.pivot.IfExp;
import org.eclipse.ocl.pivot.IteratorExp;
import org.eclipse.ocl.pivot.LetExp;
import org.eclipse.ocl.pivot.Model;
import org.eclipse.ocl.pivot.NullLiteralExp;
import org.eclipse.ocl.pivot.OCLExpression;
import org.eclipse.ocl.pivot.OperationCallExp;
import org.eclipse.ocl.pivot.PropertyCallExp;
import org.eclipse.ocl.pivot.VariableExp;
import org.eclipse.ocl.pivot.internal.manager.FlowAnalysis;
import org.eclipse.ocl.pivot.internal.manager.MetamodelManagerInternal;
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal.EnvironmentFactoryInternalExtension;
import org.eclipse.ocl.pivot.resource.ASResource;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.OCL;
import org.eclipse.ocl.pivot.utilities.ParserException;
import org.eclipse.ocl.pivot.utilities.PivotConstants;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.utilities.XMIUtil;
import org.eclipse.ocl.xtext.base.cs2as.CS2AS;
import org.eclipse.ocl.xtext.base.utilities.BaseCSResource;
import org.eclipse.ocl.xtext.oclstdlib.scoping.JavaClassScope;

/**
 * Tests the isNull/isNotNull/isNotKnown results of the FlowAnalysis.
 */
@SuppressWarnings("null")
public class FlowAnalysisTests extends XtextTestCase
{
	public class MyOCL extends TestOCL
	{
		public MyOCL() {
			super(getTestFileSystem(), "FlowAnalysisTests", getName(), OCL.NO_PROJECTS, null);
		}

		public void assertIsNonNull(@NonNull OCLExpression asExpression) {
			FlowAnalysis flowAnalysis = getFlowAnalysis(asExpression);
			assertFalse("Expected not-isNull for '" + asExpression + "'",
				flowAnalysis.isNull(asExpression));
			assertTrue("Expected isNonNull for '" + asExpression + "'",
				flowAnalysis.isNonNull(asExpression));
		}

		public void assertIsNotKnown(@NonNull OCLExpression asExpression) {
			FlowAnalysis flowAnalysis = getFlowAnalysis(asExpression);
			assertFalse("Expected not-isNull for '" + asExpression + "'",
				flowAnalysis.isNull(asExpression));
			assertFalse("Expected not-isNonNull for '" + asExpression + "'",
				flowAnalysis.isNonNull(asExpression));
		}

		public void assertIsNull(@NonNull OCLExpression asExpression) {
			FlowAnalysis flowAnalysis = getFlowAnalysis(asExpression);
			assertTrue("Expected isNull for '" + asExpression + "'",
				flowAnalysis.isNull(asExpression));
			assertFalse("Expected not-isNonNull for '" + asExpression + "'",
				flowAnalysis.isNonNull(asExpression));
		}

		protected @NonNull OCLExpression createTestModel(@NonNull String invariantName, @NonNull String invariantBody) throws IOException, ParserException {
			String testContext =
					"package deductions : ded = 'http://deductions'\n" +
							"{\n" +
							"  class Deductions\n" +
							"  {\n" +
							"    property dummy : Dummy[?];\n" +
							"    property x : Integer[?];\n" +
							"    invariant " + invariantName + ": " + invariantBody + ";\n" +
							"  }\n" +
							"  class Dummy\n" +
							"  {\n" +
							"    property dummy : Dummy[?];\n" +
							"    operation func(i : Integer, j : Integer) : Dummy[?];\n" +
							"  }\n" +
							"}";
			String fileName = "FlowAnalysis_" + invariantName;
			createOCLinEcoreFile(fileName + ".oclinecore", testContext);
			Resource asResource = doLoad_Concrete(fileName, "oclinecore");
			Model model = PivotUtil.getModel(asResource);
			org.eclipse.ocl.pivot.Package deductionsPackage = ClassUtil.nonNullState(NameUtil.getNameable(model.getOwnedPackages(), "deductions"));
			org.eclipse.ocl.pivot.Class deductionsClass = ClassUtil.nonNullState(NameUtil.getNameable(deductionsPackage.getOwnedClasses(), "Deductions"));
			Constraint asInvariant = ClassUtil.nonNullState(NameUtil.getNameable(deductionsClass.getOwnedInvariants(), invariantName));
			return ((EnvironmentFactoryInternalExtension)environmentFactory).parseSpecification(asInvariant.getOwnedSpecification()).getOwnedBody();
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
			Resource asResource = xtextResource.getASResource();
			assertNoUnresolvedProxies("Unresolved proxies", xtextResource);
			assertNoResourceErrors("Save failed", xtextResource);
			asResource.setURI(pivotURI);
			assertNoValidationErrors("Pivot validation errors", asResource.getContents().get(0));
			asResource.save(XMIUtil.createSaveOptions());
			return asResource;
		}

		protected FlowAnalysis getFlowAnalysis(@NonNull OCLExpression asExpression) {
			MetamodelManagerInternal metamodelManager = getMetamodelManager();
			return ((MetamodelManagerInternal.MetamodelManagerInternalExtension2)metamodelManager).getFlowAnalysis(asExpression);
		}
	}

	public void testFlowAnalysis_SimpleProperty() throws Exception {
		MyOCL ocl = new MyOCL();
		OperationCallExp asBody = (OperationCallExp) ocl.createTestModel("SimpleProperty",
				"x <> null");
		PropertyCallExp asPropertyCallExp = (PropertyCallExp) PivotUtil.getOwnedSource(asBody);
		NullLiteralExp asNullLiteralExp = (NullLiteralExp) PivotUtil.getOwnedArgument(asBody, 0);
		ocl.assertIsNotKnown(asPropertyCallExp);
		ocl.assertIsNull(asNullLiteralExp);
		ocl.dispose();
	}

	public void testFlowAnalysis_SimpleNonNullVariable() throws Exception {
		MyOCL ocl = new MyOCL();
		LetExp asLetExp = (LetExp) ocl.createTestModel("SimpleNonNullVariable",
				"let v : Integer[1] = 1 in v <> null");
		OperationCallExp asIn = (OperationCallExp) PivotUtil.getOwnedIn(asLetExp);
		VariableExp asVariableExp = (VariableExp) PivotUtil.getOwnedSource(asIn);
		NullLiteralExp asNullLiteralExp = (NullLiteralExp) PivotUtil.getOwnedArgument(asIn, 0);
		ocl.assertIsNonNull(asVariableExp);
		ocl.assertIsNull(asNullLiteralExp);
		ocl.dispose();
	}

	public void testFlowAnalysis_SimpleNullVariable() throws Exception {
		MyOCL ocl = new MyOCL();
		LetExp asLetExp = (LetExp) ocl.createTestModel("SimpleNullVariable",
				"let v : Integer[?] = null in v <> null");
		OperationCallExp asIn = (OperationCallExp) PivotUtil.getOwnedIn(asLetExp);
		VariableExp asVariableExp = (VariableExp) PivotUtil.getOwnedSource(asIn);
		NullLiteralExp asNullLiteralExp = (NullLiteralExp) PivotUtil.getOwnedArgument(asIn, 0);
		ocl.assertIsNull(asVariableExp);
		ocl.assertIsNull(asNullLiteralExp);
		ocl.dispose();
	}

	public void testFlowAnalysis_SimpleNotKnownVariable() throws Exception {
		MyOCL ocl = new MyOCL();
		LetExp asLetExp = (LetExp) ocl.createTestModel("SimpleNotKnownVariable",
				"let v : Integer[?] = x in v <> null");
		OperationCallExp asIn = (OperationCallExp) PivotUtil.getOwnedIn(asLetExp);
		VariableExp asVariableExp = (VariableExp) PivotUtil.getOwnedSource(asIn);
		NullLiteralExp asNullLiteralExp = (NullLiteralExp) PivotUtil.getOwnedArgument(asIn, 0);
		ocl.assertIsNotKnown(asVariableExp);
		ocl.assertIsNull(asNullLiteralExp);
		ocl.dispose();
	}

	public void testFlowAnalysis_SimpleIfGuard() throws Exception {
		MyOCL ocl = new MyOCL();
		LetExp asLetExp = (LetExp) ocl.createTestModel("SimpleIfGuard",
				"let v : Integer[?] = x in if v <> null then v.toString() else v.toString() endif <> null");
		OperationCallExp asIn = (OperationCallExp) PivotUtil.getOwnedIn(asLetExp);
		IfExp asExpression = (IfExp) PivotUtil.getOwnedSource(asIn);
		OperationCallExp asCondition = (OperationCallExp) PivotUtil.getOwnedCondition(asExpression);
		OperationCallExp asThen = (OperationCallExp) PivotUtil.getOwnedThen(asExpression);
		OperationCallExp asElse = (OperationCallExp) PivotUtil.getOwnedElse(asExpression);
		VariableExp asConditionVariableExp = (VariableExp) PivotUtil.getOwnedSource(asCondition);
		VariableExp asThenVariableExp = (VariableExp) PivotUtil.getOwnedSource(asThen);
		VariableExp asElseVariableExp = (VariableExp) PivotUtil.getOwnedSource(asElse);
		ocl.assertIsNotKnown(asConditionVariableExp);
		ocl.assertIsNonNull(asThenVariableExp);
		ocl.assertIsNull(asElseVariableExp);
		ocl.dispose();
	}

	public void testFlowAnalysis_ImpliesPropertyGuard() throws Exception {
		MyOCL ocl = new MyOCL();
		OperationCallExp asImplies = (OperationCallExp) ocl.createTestModel("ImpliesVariableGuard",
				"x <> null implies x.toString() <> null");
		OperationCallExp asSource = (OperationCallExp) PivotUtil.getOwnedSource(asImplies);
		OperationCallExp asArgument1 = (OperationCallExp) PivotUtil.getOwnedArgument(asImplies, 0);
		OperationCallExp asArgument2 = (OperationCallExp) PivotUtil.getOwnedSource(asArgument1);
		PropertyCallExp asSourcePropertyCallExp = (PropertyCallExp) PivotUtil.getOwnedSource(asSource);
		PropertyCallExp asArgumentPropertyCallExp = (PropertyCallExp) PivotUtil.getOwnedSource(asArgument2);
		ocl.assertIsNotKnown(asSourcePropertyCallExp);
		ocl.assertIsNonNull(asArgumentPropertyCallExp);
		ocl.dispose();
	}

	public void testFlowAnalysis_ImpliesVariableGuard() throws Exception {
		MyOCL ocl = new MyOCL();
		LetExp asLetExp = (LetExp) ocl.createTestModel("ImpliesGuard",
				"let v : Integer[?] = x in v <> null implies v.toString() <> null");
		OperationCallExp asImplies = (OperationCallExp) PivotUtil.getOwnedIn(asLetExp);
		OperationCallExp asSource = (OperationCallExp) PivotUtil.getOwnedSource(asImplies);
		OperationCallExp asArgument1 = (OperationCallExp) PivotUtil.getOwnedArgument(asImplies, 0);
		OperationCallExp asArgument2 = (OperationCallExp) PivotUtil.getOwnedSource(asArgument1);
		VariableExp asSourceVariableExp = (VariableExp) PivotUtil.getOwnedSource(asSource);
		VariableExp asArgumentVariableExp = (VariableExp) PivotUtil.getOwnedSource(asArgument2);
		ocl.assertIsNotKnown(asSourceVariableExp);
		ocl.assertIsNonNull(asArgumentVariableExp);
		ocl.dispose();
	}

	public void testFlowAnalysis_DoubleIfGuard() throws Exception {
		MyOCL ocl = new MyOCL();
		LetExp asLetExp = (LetExp) ocl.createTestModel("DoubleIfGuard",
				"let v : Integer[?] = x in if v <> null and x <> null then v.toString() else v.toString() endif <> null");
		OperationCallExp asIn = (OperationCallExp) PivotUtil.getOwnedIn(asLetExp);
		IfExp asExpression = (IfExp) PivotUtil.getOwnedSource(asIn);
		OperationCallExp asCondition1 = (OperationCallExp) PivotUtil.getOwnedCondition(asExpression);
		OperationCallExp asCondition1left = (OperationCallExp) PivotUtil.getOwnedSource(asCondition1);
		OperationCallExp asThen = (OperationCallExp) PivotUtil.getOwnedThen(asExpression);
		OperationCallExp asElse = (OperationCallExp) PivotUtil.getOwnedElse(asExpression);
		VariableExp asConditionVariableExp = (VariableExp) PivotUtil.getOwnedSource(asCondition1left);
		VariableExp asThenVariableExp = (VariableExp) PivotUtil.getOwnedSource(asThen);
		VariableExp asElseVariableExp = (VariableExp) PivotUtil.getOwnedSource(asElse);
		ocl.assertIsNotKnown(asConditionVariableExp);
		ocl.assertIsNonNull(asThenVariableExp);
		ocl.assertIsNotKnown(asElseVariableExp);
		ocl.dispose();
	}

	public void testFlowAnalysis_DoubleImpliesPropertyGuard() throws Exception {
		MyOCL ocl = new MyOCL();
		OperationCallExp asImplies = (OperationCallExp) ocl.createTestModel("DoubleImpliesVariableGuard",
				"dummy <> null and dummy.dummy <> null implies dummy <> null xor dummy.dummy <> null xor dummy.dummy.dummy <> null");
		OperationCallExp asAnd12 = (OperationCallExp) PivotUtil.getOwnedSource(asImplies);
		OperationCallExp asGuard1 = (OperationCallExp) PivotUtil.getOwnedSource(asAnd12);
		PropertyCallExp asRef1 = (PropertyCallExp) PivotUtil.getOwnedSource(asGuard1);
		OperationCallExp asGuard2 = (OperationCallExp) PivotUtil.getOwnedArgument(asAnd12, 0);
		PropertyCallExp asRef2 = (PropertyCallExp) PivotUtil.getOwnedSource(asGuard2);
		OperationCallExp asOr123 = (OperationCallExp) PivotUtil.getOwnedArgument(asImplies, 0);
		OperationCallExp asOr12 = (OperationCallExp) PivotUtil.getOwnedSource(asOr123);
		OperationCallExp asOr3 = (OperationCallExp) PivotUtil.getOwnedArgument(asOr123, 0);
		OperationCallExp asOr1 = (OperationCallExp) PivotUtil.getOwnedSource(asOr12);
		OperationCallExp asOr2 = (OperationCallExp) PivotUtil.getOwnedArgument(asOr12, 0);
		PropertyCallExp asUse1 = (PropertyCallExp) PivotUtil.getOwnedSource(asOr1);
		PropertyCallExp asUse2 = (PropertyCallExp) PivotUtil.getOwnedSource(asOr2);
		PropertyCallExp asUse3 = (PropertyCallExp) PivotUtil.getOwnedSource(asOr3);
		ocl.assertIsNotKnown(asRef1);
		ocl.assertIsNotKnown(asRef2);
		ocl.assertIsNonNull(asUse1);
		ocl.assertIsNonNull(asUse2);
		ocl.assertIsNotKnown(asUse3);
		ocl.dispose();
	}

	public void testFlowAnalysis_DoubleBiImpliesPropertyGuard() throws Exception {
		MyOCL ocl = new MyOCL();
		OperationCallExp asImplies = (OperationCallExp) ocl.createTestModel("DoubleBiImpliesVariableGuard",
				"dummy <> null and dummy.dummy <> null implies dummy <> null or dummy.dummy <> null or dummy.dummy.dummy <> null");
		OperationCallExp asAnd12 = (OperationCallExp) PivotUtil.getOwnedSource(asImplies);
		OperationCallExp asGuard1 = (OperationCallExp) PivotUtil.getOwnedSource(asAnd12);
		PropertyCallExp asRef1 = (PropertyCallExp) PivotUtil.getOwnedSource(asGuard1);
		OperationCallExp asGuard2 = (OperationCallExp) PivotUtil.getOwnedArgument(asAnd12, 0);
		PropertyCallExp asRef2 = (PropertyCallExp) PivotUtil.getOwnedSource(asGuard2);
		OperationCallExp asOr123 = (OperationCallExp) PivotUtil.getOwnedArgument(asImplies, 0);
		OperationCallExp asOr12 = (OperationCallExp) PivotUtil.getOwnedSource(asOr123);
		OperationCallExp asOr3 = (OperationCallExp) PivotUtil.getOwnedArgument(asOr123, 0);
		OperationCallExp asOr1 = (OperationCallExp) PivotUtil.getOwnedSource(asOr12);
		OperationCallExp asOr2 = (OperationCallExp) PivotUtil.getOwnedArgument(asOr12, 0);
		PropertyCallExp asUse1 = (PropertyCallExp) PivotUtil.getOwnedSource(asOr1);
		PropertyCallExp asUse2 = (PropertyCallExp) PivotUtil.getOwnedSource(asOr2);
		PropertyCallExp asUse3 = (PropertyCallExp) PivotUtil.getOwnedSource(asOr3);
		ocl.assertIsNull(asRef1);
		ocl.assertIsNull(asRef2);
		ocl.assertIsNonNull(asUse1);
		ocl.assertIsNonNull(asUse2);
		ocl.assertIsNotKnown(asUse3);
		ocl.dispose();
	}

	public void testFlowAnalysis_IterationExpressionGuard() throws Exception {
		MyOCL ocl = new MyOCL();
		OperationCallExp asImplies = (OperationCallExp) ocl.createTestModel("IterationExpressionGuard",
				"dummy->select(s | s <> null) <> null implies dummy->select(s | s <> null) = null");
		OperationCallExp asStartsWith = (OperationCallExp) PivotUtil.getOwnedArgument(asImplies, 0);
		IteratorExp asCompare = (IteratorExp) PivotUtil.getOwnedSource(asStartsWith);
		OperationCallExp asSource = (OperationCallExp) PivotUtil.getOwnedSource(asCompare);
		ocl.assertIsNonNull(asSource);
		ocl.dispose();
	}

	public void testFlowAnalysis_LetExpressionGuard() throws Exception {
		MyOCL ocl = new MyOCL();
		OperationCallExp asImplies = (OperationCallExp) ocl.createTestModel("LetExpressionGuard",
				"dummy.func(4, let q : Integer = 7 in q) <> null implies dummy.func(4, let y : Integer = 7 in y) = null");
		OperationCallExp asStartsWith = (OperationCallExp) PivotUtil.getOwnedArgument(asImplies, 0);
		OperationCallExp asCompare = (OperationCallExp) PivotUtil.getOwnedSource(asStartsWith);
		ocl.assertIsNonNull(asCompare);
		ocl.dispose();
	}

	public void testFlowAnalysis_OperationExpressionGuard() throws Exception {
		MyOCL ocl = new MyOCL();
		OperationCallExp asImplies = (OperationCallExp) ocl.createTestModel("OperationExpressionGuard",
				"dummy.func(2,5) <> null implies dummy.func(2,5).func(1,5) = null");
		OperationCallExp asStartsWith = (OperationCallExp) PivotUtil.getOwnedArgument(asImplies, 0);
		OperationCallExp asCompare = (OperationCallExp) PivotUtil.getOwnedSource(asStartsWith);
		OperationCallExp asSource = (OperationCallExp) PivotUtil.getOwnedSource(asCompare);
		ocl.assertIsNonNull(asSource);
		ocl.dispose();
	}
}
