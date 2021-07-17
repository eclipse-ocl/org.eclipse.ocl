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
import org.eclipse.ocl.pivot.PropertyCallExp;
import org.eclipse.ocl.pivot.StringLiteralExp;
import org.eclipse.ocl.pivot.TypedElement;
import org.eclipse.ocl.pivot.VariableDeclaration;
import org.eclipse.ocl.pivot.VariableExp;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.cse.CSEElement;
import org.eclipse.ocl.pivot.internal.evaluation.BaseSymbolicEvaluationEnvironment;
import org.eclipse.ocl.pivot.internal.evaluation.SymbolicAnalysis;
import org.eclipse.ocl.pivot.internal.manager.MetamodelManagerInternal;
import org.eclipse.ocl.pivot.internal.symbolic.SymbolicUnknownValue;
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

	private static @NonNull String DEDUCTIONS_PACKAGE_URI = "http://deductions";

	public class MyOCL extends TestOCL
	{
		private @NonNull Resource asResource;
		private @NonNull Operation asOperation;
		private org.eclipse.ocl.pivot.@NonNull Package deductionsPackage;
		private org.eclipse.ocl.pivot.@NonNull Class deductionsClass;
		private org.eclipse.ocl.pivot.@NonNull Class dummyClass;

		public MyOCL(@NonNull String queryName, @NonNull String querySignature, @NonNull String queryBody) throws IOException {
			super(getTestFileSystem(), "FlowAnalysisTests", getName(), OCL.NO_PROJECTS, null);
			String testContext =
					"package deductions : ded = '" + DEDUCTIONS_PACKAGE_URI + "'\n" +
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
			String fileName = "FlowAnalysis_" + queryName;
			createFile(fileName + ".oclinecore", testContext);
			PivotUtilInternal.getEnvironmentFactory(null).setOption(PivotValidationOptions.PotentialInvalidResult, StatusCodes.Severity.IGNORE);
			this.asResource = doLoad_Concrete(fileName, "oclinecore");
			Model model = PivotUtil.getModel(asResource);
			this.deductionsPackage = ClassUtil.nonNullState(NameUtil.getNameable(model.getOwnedPackages(), "deductions"));
			this.deductionsClass = ClassUtil.nonNullState(NameUtil.getNameable(deductionsPackage.getOwnedClasses(), "Deductions"));
			this.dummyClass = ClassUtil.nonNullState(NameUtil.getNameable(deductionsPackage.getOwnedClasses(), "Dummy"));
			this.asOperation = ClassUtil.nonNullState(NameUtil.getNameable(deductionsClass.getOwnedOperations(), queryName));

			URI ecoreURI = getTestFileURI(fileName + ".ecore");
			@SuppressWarnings("unused")
			Resource ecoreResource = PivotTestCase.as2ecore(this, asResource, ecoreURI, NO_MESSAGES);
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
			boolean okMayBeInvalid = expectedMayBeInvalid == actualValue.mayBeInvalid();
			boolean okMayBeNull = expectedMayBeNull == actualValue.mayBeNull();
			if (!okIsDead || !okIsInvalid || !okMayBeInvalid || !okMayBeNull ) {
				s.append("\n\t" + debugText(element));
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
//		SymbolicAnalysis.HYPOTHESIS.setState(true);
	}

	public void testSymbolicAnalysis_AndGuard() throws Exception {
		MyOCL ocl = new MyOCL("AndGuard", "AndGuard(x : Integer) : Boolean",
				"x <> null and x > 0");
		ExpressionInOCL asExpressionInOCL = ocl.getExpressionInOCL();
		VariableDeclaration contextVariable = PivotUtil.getOwnedContext(asExpressionInOCL);
		VariableDeclaration firstParameterVariable = PivotUtil.getOwnedParameter(asExpressionInOCL, 0);
		OperationCallExp andExp = (OperationCallExp) asExpressionInOCL.getOwnedBody();
		OperationCallExp notEqExp = (OperationCallExp) PivotUtil.getOwnedSource(andExp);
		OperationCallExp gtExp = (OperationCallExp) PivotUtil.getOwnedArgument(andExp, 0);
		VariableExp neSourceExp = (VariableExp) PivotUtil.getOwnedSource(notEqExp);
		NullLiteralExp nullExp = (NullLiteralExp) PivotUtil.getOwnedArgument(notEqExp, 0);
		VariableExp gtSourceExp = (VariableExp) PivotUtil.getOwnedSource(gtExp);
		NumericLiteralExp zeroExp = (NumericLiteralExp) PivotUtil.getOwnedArgument(gtExp, 0);

		// non-null known ok x
		SymbolicAnalysis symbolicAnalysis1a = ocl.getSymbolicAnalysis(asExpressionInOCL, null, null, new Object[]{5});
		checkContents(symbolicAnalysis1a, asExpressionInOCL, null, mayBeNulls(contextVariable, nullExp), null, null);
		BaseSymbolicEvaluationEnvironment symbolicEvaluationEnvironment1a = symbolicAnalysis1a.getBaseSymbolicEvaluationEnvironment();
		assertTrue(symbolicEvaluationEnvironment1a.isTrue(gtExp));

		// non-null known bad x
		SymbolicAnalysis symbolicAnalysis1b = ocl.getSymbolicAnalysis(asExpressionInOCL, null, null, new Object[]{-5});
		checkContents(symbolicAnalysis1b, asExpressionInOCL, isDeads(notEqExp, neSourceExp, nullExp), mayBeNulls(contextVariable), null, null);
		BaseSymbolicEvaluationEnvironment symbolicEvaluationEnvironment1b = symbolicAnalysis1b.getBaseSymbolicEvaluationEnvironment();
		assertTrue(symbolicEvaluationEnvironment1b.isFalse(gtExp));

		// null x
		SymbolicAnalysis symbolicAnalysis2 = ocl.getSymbolicAnalysis(asExpressionInOCL, null, null, new Object[]{null});
		checkContents(symbolicAnalysis2, asExpressionInOCL, isDeads(gtExp, gtSourceExp, zeroExp), mayBeNulls(contextVariable, firstParameterVariable, neSourceExp, nullExp), null, null);
		BaseSymbolicEvaluationEnvironment symbolicEvaluationEnvironment2 = symbolicAnalysis2.getBaseSymbolicEvaluationEnvironment();
		assertTrue(symbolicEvaluationEnvironment2.isFalse(notEqExp));
//		assertTrue(symbolicEvaluationEnvironment2.isTrue(asExpressionInOCL));

		// may-be-null x
		SymbolicVariableValue symbolicVariable = new SymbolicVariableValue(asExpressionInOCL.getOwnedParameters().get(0), true, false);
		SymbolicAnalysis symbolicAnalysis3 = ocl.getSymbolicAnalysis(asExpressionInOCL, null, null, new Object[]{symbolicVariable});
		checkContents(symbolicAnalysis3, asExpressionInOCL, null, mayBeNulls(contextVariable, firstParameterVariable, neSourceExp, nullExp), null, null);

		// non-null unknown x
		SymbolicAnalysis symbolicAnalysis4 = ocl.getSymbolicAnalysis(asExpressionInOCL, null, null, new Object[]{new SymbolicUnknownValue("p0", TypeId.INTEGER, false, false)});
		checkContents(symbolicAnalysis4, asExpressionInOCL, null, mayBeNulls(contextVariable, nullExp), null, null);
		BaseSymbolicEvaluationEnvironment symbolicEvaluationEnvironment4 = symbolicAnalysis4.getBaseSymbolicEvaluationEnvironment();
		assertFalse(symbolicEvaluationEnvironment4.mayBeInvalidOrNull(gtExp));
//		assertFalse(symbolicEvaluationEnvironment4.mayBeInvalidOrNull(asExpressionInOCL));

		ocl.dispose();
	}

	public void testSymbolicAnalysis_And2Guard() throws Exception {
		MyOCL ocl = new MyOCL("And2Guard", "And2Guard(x : Integer) : Boolean",
				"x <> null and2 x > 0");
		ExpressionInOCL asExpressionInOCL = ocl.getExpressionInOCL();
		VariableDeclaration contextVariable = PivotUtil.getOwnedContext(asExpressionInOCL);
		VariableDeclaration firstParameterVariable = PivotUtil.getOwnedParameter(asExpressionInOCL, 0);
		OperationCallExp andExp = (OperationCallExp) asExpressionInOCL.getOwnedBody();
		OperationCallExp notEqExp = (OperationCallExp) PivotUtil.getOwnedSource(andExp);
		OperationCallExp gtExp = (OperationCallExp) PivotUtil.getOwnedArgument(andExp, 0);
		VariableExp neSourceExp = (VariableExp) PivotUtil.getOwnedSource(notEqExp);
		NullLiteralExp nullExp = (NullLiteralExp) PivotUtil.getOwnedArgument(notEqExp, 0);
		VariableExp gtSourceExp = (VariableExp) PivotUtil.getOwnedSource(gtExp);
		NumericLiteralExp zeroExp = (NumericLiteralExp) PivotUtil.getOwnedArgument(gtExp, 0);

		// non-null known ok x
		SymbolicAnalysis symbolicAnalysis1a = ocl.getSymbolicAnalysis(asExpressionInOCL, null, null, new Object[]{5});
		checkContents(symbolicAnalysis1a, asExpressionInOCL, null, mayBeNulls(contextVariable, nullExp), null, null);
		BaseSymbolicEvaluationEnvironment symbolicEvaluationEnvironment1a = symbolicAnalysis1a.getBaseSymbolicEvaluationEnvironment();
		assertTrue(symbolicEvaluationEnvironment1a.isTrue(gtExp));

		// non-null known bad x
		SymbolicAnalysis symbolicAnalysis1b = ocl.getSymbolicAnalysis(asExpressionInOCL, null, null, new Object[]{-5});
		checkContents(symbolicAnalysis1b, asExpressionInOCL, isDeads(notEqExp, neSourceExp, nullExp), mayBeNulls(contextVariable), null, null);
		BaseSymbolicEvaluationEnvironment symbolicEvaluationEnvironment1b = symbolicAnalysis1b.getBaseSymbolicEvaluationEnvironment();
		assertTrue(symbolicEvaluationEnvironment1b.isFalse(gtExp));

		// null x
		SymbolicAnalysis symbolicAnalysis2 = ocl.getSymbolicAnalysis(asExpressionInOCL, null, null, new Object[]{null});
		checkContents(symbolicAnalysis2, asExpressionInOCL, isDeads(gtExp, gtSourceExp, zeroExp), mayBeNulls(contextVariable, firstParameterVariable, neSourceExp, nullExp), null, null);
		BaseSymbolicEvaluationEnvironment symbolicEvaluationEnvironment2 = symbolicAnalysis2.getBaseSymbolicEvaluationEnvironment();
		assertTrue(symbolicEvaluationEnvironment2.isFalse(notEqExp));
//		assertTrue(symbolicEvaluationEnvironment2.isTrue(asExpressionInOCL));

		// may-be-null x
		SymbolicVariableValue symbolicVariable = new SymbolicVariableValue(asExpressionInOCL.getOwnedParameters().get(0), true, false);
		SymbolicAnalysis symbolicAnalysis3 = ocl.getSymbolicAnalysis(asExpressionInOCL, null, null, new Object[]{symbolicVariable});
		checkContents(symbolicAnalysis3, asExpressionInOCL, null, mayBeNulls(contextVariable, firstParameterVariable, neSourceExp, nullExp), null, null);

		// non-null unknown x
		SymbolicAnalysis symbolicAnalysis4 = ocl.getSymbolicAnalysis(asExpressionInOCL, null, null, new Object[]{new SymbolicUnknownValue("p0", TypeId.INTEGER, false, false)});
		checkContents(symbolicAnalysis4, asExpressionInOCL, null, mayBeNulls(contextVariable, nullExp), null, null);
		BaseSymbolicEvaluationEnvironment symbolicEvaluationEnvironment4 = symbolicAnalysis4.getBaseSymbolicEvaluationEnvironment();
		assertFalse(symbolicEvaluationEnvironment4.mayBeInvalidOrNull(gtExp));
//		assertFalse(symbolicEvaluationEnvironment4.mayBeInvalidOrNull(asExpressionInOCL));

		ocl.dispose();
	}

	public void testSymbolicAnalysis_BadSequenceIndex() throws Exception {
		MyOCL ocl = new MyOCL("BadSequenceIndex",
			"BadSequenceIndex(s : Sequence(Dummy)) : Dummy", "s->select(false)->at(4)");
		ExpressionInOCL asExpressionInOCL = ocl.getExpressionInOCL();
	//	TypeId deductionsTypeId = asExpressionInOCL.getOwnedContext().getTypeId();
		TypeId seqTypeId = asExpressionInOCL.getOwnedParameters().get(0).getTypeId();
		VariableDeclaration contextVariable = PivotUtil.getOwnedContext(asExpressionInOCL);
		OperationCallExp atCallExp = (OperationCallExp) asExpressionInOCL.getOwnedBody();
		IteratorExp selectCallExp = (IteratorExp) atCallExp.getOwnedSource();
		IteratorVariable selectIterator = (IteratorVariable) selectCallExp.getOwnedIterators().get(0);
	//	LiteralExp selectBodyExp = (LiteralExp) selectCallExp.getOwnedBody();
	//	VariableExp asSourceExp = (VariableExp) PivotUtil.getOwnedSource(asPropertyCallExp);

		// safe non-null navigation
		SymbolicAnalysis symbolicAnalysis1 = ocl.getSymbolicAnalysis(asExpressionInOCL, null, null, new Object[]{new SymbolicUnknownValue("p0", seqTypeId, false, false)});
		checkContents(symbolicAnalysis1, asExpressionInOCL, null, mayBeNulls(contextVariable), null, null);

/*		// safe null navigation
		SymbolicEvaluationVisitor symbolicAnalysis2 = ocl.getSymbolicAnalysis(asExpressionInOCL, null, null, new Object[]{null});
		checkContents(symbolicAnalysis2, asExpressionInOCL, null, mayBeNulls(asSourceExp), null, null);

		// safe maybe-null navigation
		SymbolicEvaluationVisitor symbolicAnalysis3 = ocl.getSymbolicAnalysis(asExpressionInOCL, null, null, new Object[]{new SymbolicUnknownValueImpl(deductionsTypeId, true, false), 0.0});
		checkContents(symbolicAnalysis3, asExpressionInOCL, null, mayBeNulls(asSourceExp), mayBeInvalids(asExpressionInOCL, asPropertyCallExp), null);

		// safe maybe-invalid navigation
		SymbolicEvaluationVisitor symbolicAnalysis4 = ocl.getSymbolicAnalysis(asExpressionInOCL, null, null, new Object[]{new SymbolicUnknownValueImpl(deductionsTypeId, false, true), 0.0});
		checkContents(symbolicAnalysis4, asExpressionInOCL, null, null, mayBeInvalids(asExpressionInOCL, asPropertyCallExp, asSourceExp), null);
*/
		ocl.dispose();
	}

	public void testSymbolicAnalysis_BadDivide() throws Exception {
		MyOCL ocl = new MyOCL("BadDivide",
			"BadDivide(num : Real, den : Real) : Real", "num / den");
		ExpressionInOCL asExpressionInOCL = ocl.getExpressionInOCL();
		OperationCallExp asOperationCallExp = (OperationCallExp) asExpressionInOCL.getOwnedBody();
		VariableDeclaration contextVariable = PivotUtil.getOwnedContext(asExpressionInOCL);
	//	VariableExp asNumExp = (VariableExp) PivotUtil.getOwnedSource(asOperationCallExp);
	//	VariableExp asDenExp = (VariableExp) PivotUtil.getOwnedArgument(asOperationCallExp, 0);

		// not-null / maybe-zero
		SymbolicAnalysis symbolicAnalysis5a = ocl.getSymbolicAnalysis(asExpressionInOCL, null, null, new Object[]{new SymbolicUnknownValue("p0", TypeId.REAL, false, false), new SymbolicUnknownValue("p1", TypeId.REAL, false, false)});
		checkContents(symbolicAnalysis5a, asExpressionInOCL, null, mayBeNulls(contextVariable), mayBeInvalids(asExpressionInOCL, asOperationCallExp), null);

		// 5.0 / 0.0
		SymbolicAnalysis symbolicAnalysis1 = ocl.getSymbolicAnalysis(asExpressionInOCL, null, null, new Object[]{5.0, 0.0});
		checkContents(symbolicAnalysis1, asExpressionInOCL, null, mayBeNulls(contextVariable), null, isInvalids(asExpressionInOCL, asOperationCallExp));

		// 5.0 / 1.0
		SymbolicAnalysis symbolicAnalysis2 = ocl.getSymbolicAnalysis(asExpressionInOCL, null, null, new Object[]{5.0, 1.0});
		checkContents(symbolicAnalysis2, asExpressionInOCL, null, mayBeNulls(contextVariable), null, null);

		// not-null / 0.0
		SymbolicAnalysis symbolicAnalysis3 = ocl.getSymbolicAnalysis(asExpressionInOCL, null, null, new Object[]{new SymbolicUnknownValue("p0", TypeId.REAL, false, false), 0.0});
		checkContents(symbolicAnalysis3, asExpressionInOCL, null, mayBeNulls(contextVariable), null, isInvalids(asExpressionInOCL, asOperationCallExp));

		// not-null / 1.0
		SymbolicAnalysis symbolicAnalysis4 = ocl.getSymbolicAnalysis(asExpressionInOCL, null, null, new Object[]{new SymbolicUnknownValue("p0", TypeId.REAL, false, false), 1.0});
		checkContents(symbolicAnalysis4, asExpressionInOCL, null, mayBeNulls(contextVariable), null, null);

		// not-null / maybe-zero
		SymbolicAnalysis symbolicAnalysis5 = ocl.getSymbolicAnalysis(asExpressionInOCL, null, null, new Object[]{new SymbolicUnknownValue("p0", TypeId.REAL, false, false), new SymbolicUnknownValue("p1", TypeId.REAL, false, false)});
		checkContents(symbolicAnalysis5, asExpressionInOCL, null, mayBeNulls(contextVariable), mayBeInvalids(asExpressionInOCL, asOperationCallExp), null);

		ocl.dispose();
	}

	public void testSymbolicAnalysis_BadSafeNavigation() throws Exception {
		MyOCL ocl = new MyOCL("BadSafeNavigation",
			"BadSafeNavigation(x : Deductions[?]) : Dummy", "x?.nonNullDummy");
		ExpressionInOCL asExpressionInOCL = ocl.getExpressionInOCL();
		VariableDeclaration contextVariable = PivotUtil.getOwnedContext(asExpressionInOCL);
		VariableDeclaration firstParameterVariable = PivotUtil.getOwnedParameter(asExpressionInOCL, 0);
		TypeId deductionsTypeId = asExpressionInOCL.getOwnedContext().getTypeId();
		PropertyCallExp asPropertyCallExp = (PropertyCallExp) asExpressionInOCL.getOwnedBody();
		VariableExp asSourceExp = (VariableExp) PivotUtil.getOwnedSource(asPropertyCallExp);

		// safe non-null navigation
		SymbolicAnalysis symbolicAnalysis1 = ocl.getSymbolicAnalysis(asExpressionInOCL, null, null, new Object[]{new SymbolicUnknownValue("p0", deductionsTypeId, false, false)});
		checkContents(symbolicAnalysis1, asExpressionInOCL, null, mayBeNulls(contextVariable), null, null);

		// safe null navigation
		SymbolicAnalysis symbolicAnalysis2 = ocl.getSymbolicAnalysis(asExpressionInOCL, null, null, new Object[]{null});
		checkContents(symbolicAnalysis2, asExpressionInOCL, null, mayBeNulls(asExpressionInOCL, contextVariable, firstParameterVariable, asPropertyCallExp, asSourceExp), null, null);

		// safe maybe-null navigation
		SymbolicAnalysis symbolicAnalysis3 = ocl.getSymbolicAnalysis(asExpressionInOCL, null, null, new Object[]{new SymbolicUnknownValue("p0", deductionsTypeId, true, false)});
		checkContents(symbolicAnalysis3, asExpressionInOCL, null, mayBeNulls(asExpressionInOCL, contextVariable, firstParameterVariable,
			asPropertyCallExp, asSourceExp), null, null);

		// safe maybe-invalid navigation
		SymbolicAnalysis symbolicAnalysis4 = ocl.getSymbolicAnalysis(asExpressionInOCL, null, null, new Object[]{new SymbolicUnknownValue("p0", deductionsTypeId, false, true)});
		checkContents(symbolicAnalysis4, asExpressionInOCL, null, mayBeNulls(contextVariable), mayBeInvalids(asExpressionInOCL, firstParameterVariable, asPropertyCallExp, asSourceExp), null);

		ocl.dispose();
	}

	public void testSymbolicAnalysis_BadSafeOppositeNavigation() throws Exception {
		MyOCL ocl = new MyOCL("BadSafeNavigation",
			"BadSafeNavigation(x : Deductions[?]) : Bag(Dummy)", "x?.Dummy[Dummy::mayBeNullDeductions]");		// Dummy is a non-null multi-valued opposite
		ExpressionInOCL asExpressionInOCL = ocl.getExpressionInOCL();
		VariableDeclaration contextVariable = PivotUtil.getOwnedContext(asExpressionInOCL);
		VariableDeclaration firstParameterVariable = PivotUtil.getOwnedParameter(asExpressionInOCL, 0);
		TypeId deductionsTypeId = asExpressionInOCL.getOwnedContext().getTypeId();
		OppositePropertyCallExp asOppositePropertyCallExp = (OppositePropertyCallExp) asExpressionInOCL.getOwnedBody();
		VariableExp asSourceExp = (VariableExp) PivotUtil.getOwnedSource(asOppositePropertyCallExp);

		// safe non-null navigation
		SymbolicAnalysis symbolicAnalysis1 = ocl.getSymbolicAnalysis(asExpressionInOCL, null, null, new Object[]{new SymbolicUnknownValue("p0", deductionsTypeId, false, false)});
		checkContents(symbolicAnalysis1, asExpressionInOCL, null, mayBeNulls(contextVariable), null, null);

		// safe null navigation
		SymbolicAnalysis symbolicAnalysis2 = ocl.getSymbolicAnalysis(asExpressionInOCL, null, null, new Object[]{null});
		checkContents(symbolicAnalysis2, asExpressionInOCL, null, mayBeNulls(asExpressionInOCL, contextVariable, firstParameterVariable, asOppositePropertyCallExp, asSourceExp), null, null);

		// safe maybe-null navigation
		SymbolicAnalysis symbolicAnalysis3 = ocl.getSymbolicAnalysis(asExpressionInOCL, null, null, new Object[]{new SymbolicUnknownValue("p0", deductionsTypeId, true, false)});
		checkContents(symbolicAnalysis3, asExpressionInOCL, null, mayBeNulls(contextVariable, firstParameterVariable, asSourceExp), null, null);

		// safe maybe-invalid navigation
		SymbolicAnalysis symbolicAnalysis4 = ocl.getSymbolicAnalysis(asExpressionInOCL, null, null, new Object[]{new SymbolicUnknownValue("p0", deductionsTypeId, false, true)});
		checkContents(symbolicAnalysis4, asExpressionInOCL, null, mayBeNulls(contextVariable), mayBeInvalids(asExpressionInOCL, firstParameterVariable, asSourceExp, asOppositePropertyCallExp), null);

		ocl.dispose();
	}

	public void testSymbolicAnalysis_BadUnsafeRequiredNavigation() throws Exception {
		MyOCL ocl = new MyOCL("BadUnsafeRequiredNavigation",
			"BadUnsafeRequiredNavigation(x : Deductions[?]) : Dummy", "x.nonNullDummy");
		ExpressionInOCL asExpressionInOCL = ocl.getExpressionInOCL();
		VariableDeclaration contextVariable = PivotUtil.getOwnedContext(asExpressionInOCL);
		VariableDeclaration firstParameterVariable = PivotUtil.getOwnedParameter(asExpressionInOCL, 0);
		TypeId deductionsTypeId = asExpressionInOCL.getOwnedContext().getTypeId();
		PropertyCallExp asPropertyCallExp = (PropertyCallExp) asExpressionInOCL.getOwnedBody();
		VariableExp asSourceExp = (VariableExp) PivotUtil.getOwnedSource(asPropertyCallExp);

		// unsafe non-null navigation
		SymbolicAnalysis symbolicAnalysis1 = ocl.getSymbolicAnalysis(asExpressionInOCL, null, null, new Object[]{new SymbolicUnknownValue("p0", deductionsTypeId, false, false)});
		checkContents(symbolicAnalysis1, asExpressionInOCL, null, mayBeNulls(contextVariable), null, null);

		// unsafe null navigation
		SymbolicAnalysis symbolicAnalysis2 = ocl.getSymbolicAnalysis(asExpressionInOCL, null, null, new Object[]{null});
		checkContents(symbolicAnalysis2, asExpressionInOCL, null, mayBeNulls(contextVariable, firstParameterVariable, asSourceExp), null, isInvalids(asExpressionInOCL, asPropertyCallExp));

		// unsafe maybe-null navigation
		SymbolicAnalysis symbolicAnalysis3 = ocl.getSymbolicAnalysis(asExpressionInOCL, null, null, new Object[]{new SymbolicUnknownValue("p0", deductionsTypeId, true, false)});
		checkContents(symbolicAnalysis3, asExpressionInOCL, null, mayBeNulls(contextVariable, firstParameterVariable, asSourceExp), mayBeInvalids(asExpressionInOCL, asPropertyCallExp), null);

		// unsafe maybe-invalid navigation
		SymbolicAnalysis symbolicAnalysis4 = ocl.getSymbolicAnalysis(asExpressionInOCL, null, null, new Object[]{new SymbolicUnknownValue("p0", deductionsTypeId, false, true)});
		checkContents(symbolicAnalysis4, asExpressionInOCL, null, mayBeNulls(contextVariable), mayBeInvalids(asExpressionInOCL, firstParameterVariable, asPropertyCallExp, asSourceExp), null);

		ocl.dispose();
	}

	public void testSymbolicAnalysis_BadUnsafeOptionalNavigation() throws Exception {
		MyOCL ocl = new MyOCL("BadUnsafeOptionalNavigation",
			"BadUnsafeOptionalNavigation(x : Deductions[?]) : Dummy", "x.mayBeNullDummy");
		ExpressionInOCL asExpressionInOCL = ocl.getExpressionInOCL();
		VariableDeclaration contextVariable = PivotUtil.getOwnedContext(asExpressionInOCL);
		VariableDeclaration firstParameterVariable = PivotUtil.getOwnedParameter(asExpressionInOCL, 0);
		TypeId deductionsTypeId = asExpressionInOCL.getOwnedContext().getTypeId();
		PropertyCallExp asPropertyCallExp = (PropertyCallExp) asExpressionInOCL.getOwnedBody();
		VariableExp asSourceExp = (VariableExp) PivotUtil.getOwnedSource(asPropertyCallExp);

		// unsafe non-null navigation
		SymbolicAnalysis symbolicAnalysis1 = ocl.getSymbolicAnalysis(asExpressionInOCL, null, null, new Object[]{new SymbolicUnknownValue("p0", deductionsTypeId, false, false)});
		checkContents(symbolicAnalysis1, asExpressionInOCL, null, mayBeNulls(asExpressionInOCL, contextVariable, asPropertyCallExp), null, null);

		// unsafe null navigation
		SymbolicAnalysis symbolicAnalysis2 = ocl.getSymbolicAnalysis(asExpressionInOCL, null, null, new Object[]{null});
		checkContents(symbolicAnalysis2, asExpressionInOCL, null, mayBeNulls(contextVariable, firstParameterVariable, asSourceExp), null, isInvalids(asExpressionInOCL, asPropertyCallExp));

		// unsafe maybe-null navigation
		SymbolicAnalysis symbolicAnalysis3 = ocl.getSymbolicAnalysis(asExpressionInOCL, null, null, new Object[]{new SymbolicUnknownValue("p0", deductionsTypeId, true, false)});
		checkContents(symbolicAnalysis3, asExpressionInOCL, null, mayBeNulls(contextVariable, firstParameterVariable, asSourceExp), mayBeInvalids(asExpressionInOCL, asPropertyCallExp), null);

		// unsafe maybe-invalid navigation
		SymbolicAnalysis symbolicAnalysis4 = ocl.getSymbolicAnalysis(asExpressionInOCL, null, null, new Object[]{new SymbolicUnknownValue("p0", deductionsTypeId, false, true)});
		checkContents(symbolicAnalysis4, asExpressionInOCL, null, mayBeNulls(contextVariable), mayBeInvalids(asExpressionInOCL, firstParameterVariable, asPropertyCallExp, asSourceExp), null);

		ocl.dispose();
	}

	public void testSymbolicAnalysis_CommutatedAndGuard() throws Exception {
		MyOCL ocl = new MyOCL("CommutatedAndGuard", "CommutatedAndGuard(x : Integer) : Boolean",
				"x > 0 and x <> null");
		ExpressionInOCL asExpressionInOCL = ocl.getExpressionInOCL();
		VariableDeclaration contextVariable = PivotUtil.getOwnedContext(asExpressionInOCL);
		VariableDeclaration firstParameterVariable = PivotUtil.getOwnedParameter(asExpressionInOCL, 0);
		OperationCallExp andExp = (OperationCallExp) asExpressionInOCL.getOwnedBody();
		OperationCallExp gtExp = (OperationCallExp) PivotUtil.getOwnedSource(andExp);
		OperationCallExp notEqExp = (OperationCallExp) PivotUtil.getOwnedArgument(andExp, 0);
		VariableExp neSourceExp = (VariableExp) PivotUtil.getOwnedSource(notEqExp);
		NullLiteralExp nullExp = (NullLiteralExp) PivotUtil.getOwnedArgument(notEqExp, 0);
		VariableExp gtSourceExp = (VariableExp) PivotUtil.getOwnedSource(gtExp);
		NumericLiteralExp zeroExp = (NumericLiteralExp) PivotUtil.getOwnedArgument(gtExp, 0);

		// non-null known ok x
		SymbolicAnalysis symbolicAnalysis1a = ocl.getSymbolicAnalysis(asExpressionInOCL, null, null, new Object[]{5});
		checkContents(symbolicAnalysis1a, asExpressionInOCL, null, mayBeNulls(contextVariable, nullExp), null, null);
		BaseSymbolicEvaluationEnvironment symbolicEvaluationEnvironment1a = symbolicAnalysis1a.getBaseSymbolicEvaluationEnvironment();
		assertTrue(symbolicEvaluationEnvironment1a.isTrue(gtExp));

		// non-null known bad x
		SymbolicAnalysis symbolicAnalysis1b = ocl.getSymbolicAnalysis(asExpressionInOCL, null, null, new Object[]{-5});
		checkContents(symbolicAnalysis1b, asExpressionInOCL, isDeads(notEqExp, neSourceExp, nullExp), mayBeNulls(contextVariable), null, null);
		BaseSymbolicEvaluationEnvironment symbolicEvaluationEnvironment1b = symbolicAnalysis1b.getBaseSymbolicEvaluationEnvironment();
		assertTrue(symbolicEvaluationEnvironment1b.isFalse(gtExp));

		// null x
		SymbolicAnalysis symbolicAnalysis2 = ocl.getSymbolicAnalysis(asExpressionInOCL, null, null, new Object[]{null});
		checkContents(symbolicAnalysis2, asExpressionInOCL, isDeads(gtExp, gtSourceExp, zeroExp), mayBeNulls(contextVariable, firstParameterVariable, neSourceExp, nullExp), null, null);
		BaseSymbolicEvaluationEnvironment symbolicEvaluationEnvironment2 = symbolicAnalysis2.getBaseSymbolicEvaluationEnvironment();
		assertTrue(symbolicEvaluationEnvironment2.isFalse(notEqExp));
//		assertTrue(symbolicEvaluationEnvironment2.isTrue(asExpressionInOCL));

		// may-be-null x
		SymbolicVariableValue symbolicVariable = new SymbolicVariableValue(asExpressionInOCL.getOwnedParameters().get(0), true, false);
		SymbolicAnalysis symbolicAnalysis3 = ocl.getSymbolicAnalysis(asExpressionInOCL, null, null, new Object[]{symbolicVariable});
		checkContents(symbolicAnalysis3, asExpressionInOCL, null, mayBeNulls(contextVariable, firstParameterVariable, nullExp), null, null);

		// non-null unknown x
		SymbolicAnalysis symbolicAnalysis4 = ocl.getSymbolicAnalysis(asExpressionInOCL, null, null, new Object[]{new SymbolicUnknownValue("p0", TypeId.INTEGER, false, false)});
		checkContents(symbolicAnalysis4, asExpressionInOCL, null, mayBeNulls(contextVariable, nullExp), null, null);
		BaseSymbolicEvaluationEnvironment symbolicEvaluationEnvironment4 = symbolicAnalysis4.getBaseSymbolicEvaluationEnvironment();
		assertFalse(symbolicEvaluationEnvironment4.mayBeInvalidOrNull(gtExp));
//		assertFalse(symbolicEvaluationEnvironment4.mayBeInvalidOrNull(asExpressionInOCL));

		ocl.dispose();
	}

	public void testSymbolicAnalysis_CommutatedAnd2Guard() throws Exception {
		MyOCL ocl = new MyOCL("CommutatedAnd2Guard", "CommutatedAnd2Guard(x : Integer) : Boolean",
				"x > 0 and2 x <> null");
		ExpressionInOCL asExpressionInOCL = ocl.getExpressionInOCL();
		VariableDeclaration contextVariable = PivotUtil.getOwnedContext(asExpressionInOCL);
		VariableDeclaration firstParameterVariable = PivotUtil.getOwnedParameter(asExpressionInOCL, 0);
		OperationCallExp andExp = (OperationCallExp) asExpressionInOCL.getOwnedBody();
		OperationCallExp gtExp = (OperationCallExp) PivotUtil.getOwnedSource(andExp);
		OperationCallExp notEqExp = (OperationCallExp) PivotUtil.getOwnedArgument(andExp, 0);
		VariableExp neSourceExp = (VariableExp) PivotUtil.getOwnedSource(notEqExp);
		NullLiteralExp nullExp = (NullLiteralExp) PivotUtil.getOwnedArgument(notEqExp, 0);
		VariableExp gtSourceExp = (VariableExp) PivotUtil.getOwnedSource(gtExp);
	//	NumericLiteralExp zeroExp = (NumericLiteralExp) PivotUtil.getOwnedArgument(gtExp, 0);

		// non-null known ok x
		SymbolicAnalysis symbolicAnalysis1a = ocl.getSymbolicAnalysis(asExpressionInOCL, null, null, new Object[]{5});
		checkContents(symbolicAnalysis1a, asExpressionInOCL, null, mayBeNulls(contextVariable, nullExp), null, null);
		BaseSymbolicEvaluationEnvironment symbolicEvaluationEnvironment1a = symbolicAnalysis1a.getBaseSymbolicEvaluationEnvironment();
		assertTrue(symbolicEvaluationEnvironment1a.isTrue(gtExp));

		// non-null known bad x
		SymbolicAnalysis symbolicAnalysis1b = ocl.getSymbolicAnalysis(asExpressionInOCL, null, null, new Object[]{-5});
		checkContents(symbolicAnalysis1b, asExpressionInOCL, isDeads(notEqExp, neSourceExp, nullExp), mayBeNulls(contextVariable), null, null);
		BaseSymbolicEvaluationEnvironment symbolicEvaluationEnvironment1b = symbolicAnalysis1b.getBaseSymbolicEvaluationEnvironment();
		assertTrue(symbolicEvaluationEnvironment1b.isFalse(gtExp));

		// null x
		SymbolicAnalysis symbolicAnalysis2 = ocl.getSymbolicAnalysis(asExpressionInOCL, null, null, new Object[]{null});
		checkContents(symbolicAnalysis2, asExpressionInOCL, null, mayBeNulls(contextVariable, firstParameterVariable, gtSourceExp, neSourceExp, nullExp), null, isInvalids(asExpressionInOCL, andExp, gtExp));
		BaseSymbolicEvaluationEnvironment symbolicEvaluationEnvironment2 = symbolicAnalysis2.getBaseSymbolicEvaluationEnvironment();
		assertTrue(symbolicEvaluationEnvironment2.isFalse(notEqExp));
//		assertTrue(symbolicEvaluationEnvironment2.isTrue(asExpressionInOCL));

		// may-be-null x
		SymbolicVariableValue symbolicVariable = new SymbolicVariableValue(asExpressionInOCL.getOwnedParameters().get(0), true, false);
		SymbolicAnalysis symbolicAnalysis3 = ocl.getSymbolicAnalysis(asExpressionInOCL, null, null, new Object[]{symbolicVariable});
		checkContents(symbolicAnalysis3, asExpressionInOCL, null, mayBeNulls(contextVariable, firstParameterVariable, nullExp), null, null);

		// non-null unknown x
		SymbolicAnalysis symbolicAnalysis4 = ocl.getSymbolicAnalysis(asExpressionInOCL, null, null, new Object[]{new SymbolicUnknownValue("p0", TypeId.INTEGER, false, false)});
		checkContents(symbolicAnalysis4, asExpressionInOCL, null, mayBeNulls(contextVariable, nullExp), null, null);
		BaseSymbolicEvaluationEnvironment symbolicEvaluationEnvironment4 = symbolicAnalysis4.getBaseSymbolicEvaluationEnvironment();
		assertFalse(symbolicEvaluationEnvironment4.mayBeInvalidOrNull(gtExp));
//		assertFalse(symbolicEvaluationEnvironment4.mayBeInvalidOrNull(asExpressionInOCL));

		ocl.dispose();
	}

	public void testSymbolicAnalysis_CommutatedOrGuard() throws Exception {
		MyOCL ocl = new MyOCL("CommutatedOrGuard", "CommutatedOrGuard(x : Integer) : Boolean",
				"x > 0 or x = null");
		ExpressionInOCL asExpressionInOCL = ocl.getExpressionInOCL();
		VariableDeclaration contextVariable = PivotUtil.getOwnedContext(asExpressionInOCL);
		VariableDeclaration firstParameterVariable = PivotUtil.getOwnedParameter(asExpressionInOCL, 0);
		OperationCallExp orExp = (OperationCallExp) asExpressionInOCL.getOwnedBody();
		OperationCallExp gtExp = (OperationCallExp) PivotUtil.getOwnedSource(orExp);
		OperationCallExp eqExp = (OperationCallExp) PivotUtil.getOwnedArgument(orExp, 0);
		VariableExp eqSourceExp = (VariableExp) PivotUtil.getOwnedSource(eqExp);
		NullLiteralExp nullExp = (NullLiteralExp) PivotUtil.getOwnedArgument(eqExp, 0);
		VariableExp gtSourceExp = (VariableExp) PivotUtil.getOwnedSource(gtExp);
		NumericLiteralExp zeroExp = (NumericLiteralExp) PivotUtil.getOwnedArgument(gtExp, 0);

		// non-null known ok x
		SymbolicAnalysis symbolicAnalysis1a = ocl.getSymbolicAnalysis(asExpressionInOCL, null, null, new Object[]{5});
		checkContents(symbolicAnalysis1a, asExpressionInOCL, isDeads(eqExp, eqSourceExp, nullExp), mayBeNulls(contextVariable), null, null);
		BaseSymbolicEvaluationEnvironment symbolicEvaluationEnvironment1a = symbolicAnalysis1a.getBaseSymbolicEvaluationEnvironment();
		assertTrue(symbolicEvaluationEnvironment1a.isTrue(gtExp));

		// non-null known bad x
		SymbolicAnalysis symbolicAnalysis1b = ocl.getSymbolicAnalysis(asExpressionInOCL, null, null, new Object[]{-5});
		checkContents(symbolicAnalysis1b, asExpressionInOCL, null, mayBeNulls(contextVariable, nullExp), null, null);
		BaseSymbolicEvaluationEnvironment symbolicEvaluationEnvironment1b = symbolicAnalysis1b.getBaseSymbolicEvaluationEnvironment();
		assertTrue(symbolicEvaluationEnvironment1b.isFalse(gtExp));

		// null x
		SymbolicAnalysis symbolicAnalysis2 = ocl.getSymbolicAnalysis(asExpressionInOCL, null, null, new Object[]{null});
		checkContents(symbolicAnalysis2, asExpressionInOCL, isDeads(gtExp, gtSourceExp, zeroExp), mayBeNulls(contextVariable, firstParameterVariable, eqSourceExp, nullExp), null, null);
		BaseSymbolicEvaluationEnvironment symbolicEvaluationEnvironment2 = symbolicAnalysis2.getBaseSymbolicEvaluationEnvironment();
		assertTrue(symbolicEvaluationEnvironment2.isTrue(eqExp));
//		assertTrue(symbolicEvaluationEnvironment2.isTrue(asExpressionInOCL));

		// may-be-null x
		SymbolicVariableValue symbolicVariable = new SymbolicVariableValue(asExpressionInOCL.getOwnedParameters().get(0), true, false);
		SymbolicAnalysis symbolicAnalysis3 = ocl.getSymbolicAnalysis(asExpressionInOCL, null, null, new Object[]{symbolicVariable});
		checkContents(symbolicAnalysis3, asExpressionInOCL, null, mayBeNulls(contextVariable, firstParameterVariable, nullExp), null, null);

		// non-null unknown x
		SymbolicAnalysis symbolicAnalysis4 = ocl.getSymbolicAnalysis(asExpressionInOCL, null, null, new Object[]{new SymbolicUnknownValue("p0", TypeId.INTEGER, false, false)});
		checkContents(symbolicAnalysis4, asExpressionInOCL, null, mayBeNulls(contextVariable, nullExp), null, null);
		BaseSymbolicEvaluationEnvironment symbolicEvaluationEnvironment4 = symbolicAnalysis4.getBaseSymbolicEvaluationEnvironment();
		assertFalse(symbolicEvaluationEnvironment4.mayBeInvalidOrNull(gtExp));
//		assertFalse(symbolicEvaluationEnvironment4.mayBeInvalidOrNull(asExpressionInOCL));

		ocl.dispose();
	}

	public void testSymbolicAnalysis_CommutatedOr2Guard() throws Exception {
		MyOCL ocl = new MyOCL("CommutatedOr2Guard", "CommutatedOr2Guard(x : Integer) : Boolean",
				"x > 0 or2 x = null");
		ExpressionInOCL asExpressionInOCL = ocl.getExpressionInOCL();
		VariableDeclaration contextVariable = PivotUtil.getOwnedContext(asExpressionInOCL);
		VariableDeclaration firstParameterVariable = PivotUtil.getOwnedParameter(asExpressionInOCL, 0);
		OperationCallExp orExp = (OperationCallExp) asExpressionInOCL.getOwnedBody();
		OperationCallExp gtExp = (OperationCallExp) PivotUtil.getOwnedSource(orExp);
		OperationCallExp eqExp = (OperationCallExp) PivotUtil.getOwnedArgument(orExp, 0);
		VariableExp eqSourceExp = (VariableExp) PivotUtil.getOwnedSource(eqExp);
		NullLiteralExp nullExp = (NullLiteralExp) PivotUtil.getOwnedArgument(eqExp, 0);
		VariableExp gtSourceExp = (VariableExp) PivotUtil.getOwnedSource(gtExp);
	//	NumericLiteralExp zeroExp = (NumericLiteralExp) PivotUtil.getOwnedArgument(gtExp, 0);

		// non-null known ok x
		SymbolicAnalysis symbolicAnalysis1a = ocl.getSymbolicAnalysis(asExpressionInOCL, null, null, new Object[]{5});
		checkContents(symbolicAnalysis1a, asExpressionInOCL, isDeads(eqExp, eqSourceExp, nullExp), mayBeNulls(contextVariable), null, null);
		BaseSymbolicEvaluationEnvironment symbolicEvaluationEnvironment1a = symbolicAnalysis1a.getBaseSymbolicEvaluationEnvironment();
		assertTrue(symbolicEvaluationEnvironment1a.isTrue(gtExp));

		// non-null known bad x
		SymbolicAnalysis symbolicAnalysis1b = ocl.getSymbolicAnalysis(asExpressionInOCL, null, null, new Object[]{-5});
		checkContents(symbolicAnalysis1b, asExpressionInOCL, null, mayBeNulls(contextVariable, nullExp), null, null);
		BaseSymbolicEvaluationEnvironment symbolicEvaluationEnvironment1b = symbolicAnalysis1b.getBaseSymbolicEvaluationEnvironment();
		assertTrue(symbolicEvaluationEnvironment1b.isFalse(gtExp));

		// null x
		SymbolicAnalysis symbolicAnalysis2 = ocl.getSymbolicAnalysis(asExpressionInOCL, null, null, new Object[]{null});
		checkContents(symbolicAnalysis2, asExpressionInOCL, null, mayBeNulls(contextVariable, firstParameterVariable, gtSourceExp, eqSourceExp, nullExp), null, isInvalids(asExpressionInOCL, orExp, gtExp));
		BaseSymbolicEvaluationEnvironment symbolicEvaluationEnvironment2 = symbolicAnalysis2.getBaseSymbolicEvaluationEnvironment();
		assertTrue(symbolicEvaluationEnvironment2.isTrue(eqExp));
//		assertTrue(symbolicEvaluationEnvironment2.isTrue(asExpressionInOCL));

		// may-be-null x
		SymbolicVariableValue symbolicVariable = new SymbolicVariableValue(asExpressionInOCL.getOwnedParameters().get(0), true, false);
		SymbolicAnalysis symbolicAnalysis3 = ocl.getSymbolicAnalysis(asExpressionInOCL, null, null, new Object[]{symbolicVariable});
		checkContents(symbolicAnalysis3, asExpressionInOCL, null, mayBeNulls(contextVariable, firstParameterVariable, nullExp), null, null);

		// non-null unknown x
		SymbolicAnalysis symbolicAnalysis4 = ocl.getSymbolicAnalysis(asExpressionInOCL, null, null, new Object[]{new SymbolicUnknownValue("p0", TypeId.INTEGER, false, false)});
		checkContents(symbolicAnalysis4, asExpressionInOCL, null, mayBeNulls(contextVariable, nullExp), null, null);
		BaseSymbolicEvaluationEnvironment symbolicEvaluationEnvironment4 = symbolicAnalysis4.getBaseSymbolicEvaluationEnvironment();
		assertFalse(symbolicEvaluationEnvironment4.mayBeInvalidOrNull(gtExp));
//		assertFalse(symbolicEvaluationEnvironment4.mayBeInvalidOrNull(asExpressionInOCL));

		ocl.dispose();
	}

	public void testSymbolicAnalysis_First() throws Exception {
		MyOCL ocl = new MyOCL("First", "First(x : Sequence(Integer)) : Integer",
				"x->first()");
		ExpressionInOCL asExpressionInOCL = ocl.getExpressionInOCL();
		VariableDeclaration contextVariable = PivotUtil.getOwnedContext(asExpressionInOCL);
		VariableDeclaration firstParameterVariable = PivotUtil.getOwnedParameter(asExpressionInOCL, 0);
		OperationCallExp firstExp = (OperationCallExp) asExpressionInOCL.getOwnedBody();
		VariableExp sourcefirstExp = (VariableExp) PivotUtil.getOwnedSource(firstExp);

		// non-null empty x
		SymbolicAnalysis symbolicAnalysis1 = ocl.getSymbolicAnalysis(asExpressionInOCL, null, null, new Object[]{ValueUtil.EMPTY_SET});
		checkContents(symbolicAnalysis1, asExpressionInOCL, null, mayBeNulls(contextVariable), null, isInvalids(asExpressionInOCL, firstExp));

		// non-null not-empty x
		SetValue set2 = ValueUtil.createSetOfEach(TypeId.SET.getSpecializedId(TypeId.INTEGER), ValueUtil.integerValueOf(99));
		SymbolicAnalysis symbolicAnalysis2 = ocl.getSymbolicAnalysis(asExpressionInOCL, null, null, new Object[]{set2});
		checkContents(symbolicAnalysis2, asExpressionInOCL, null, mayBeNulls(contextVariable), null, null);

		// non-null unknown x
		SymbolicVariableValue symbolicVariable = new SymbolicVariableValue(asExpressionInOCL.getOwnedParameters().get(0), false, false);
		SymbolicAnalysis symbolicAnalysis3 = ocl.getSymbolicAnalysis(asExpressionInOCL, null, null, new Object[]{symbolicVariable});
		checkContents(symbolicAnalysis3, asExpressionInOCL, null, mayBeNulls(contextVariable), mayBeInvalids(asExpressionInOCL, firstExp), null);

		// null collection x (does not behave as empty set)
		SymbolicAnalysis symbolicAnalysis4 = ocl.getSymbolicAnalysis(asExpressionInOCL, null, null, new Object[]{null});
		checkContents(symbolicAnalysis4, asExpressionInOCL, null, mayBeNulls(contextVariable, firstParameterVariable, sourcefirstExp), null, isInvalids(asExpressionInOCL, firstExp));

		ocl.dispose();
	}

	public void testSymbolicAnalysis_GuardedBadDivide() throws Exception {
		MyOCL ocl = new MyOCL("GuardedBadDivide",
			"GuardedBadDivide(num : Real, den : Real) : Real", "if den <> 0 then num / den else 999.999 endif");
		ExpressionInOCL asExpressionInOCL = ocl.getExpressionInOCL();
		IfExp asIfExp = (IfExp) asExpressionInOCL.getOwnedBody();
		OperationCallExp asOperationCallExp = (OperationCallExp) PivotUtil.getOwnedThen(asIfExp);
		VariableDeclaration contextVariable = PivotUtil.getOwnedContext(asExpressionInOCL);
		VariableExp asNumExp = (VariableExp) PivotUtil.getOwnedSource(asOperationCallExp);
		VariableExp asDenExp = (VariableExp) PivotUtil.getOwnedArgument(asOperationCallExp, 0);
		LiteralExp asLiteralExp = (LiteralExp) PivotUtil.getOwnedElse(asIfExp);

		// 5.0 / not-null-maybe-zero
		SymbolicAnalysis symbolicAnalysis5a = ocl.getSymbolicAnalysis(asExpressionInOCL, null, null, new Object[]{5.0, new SymbolicUnknownValue("p0", TypeId.REAL, false, false)});
		checkContents(symbolicAnalysis5a, asExpressionInOCL, null, mayBeNulls(contextVariable), null, null);

		// 5.0 / 0.0
		SymbolicAnalysis symbolicAnalysis1 = ocl.getSymbolicAnalysis(asExpressionInOCL, null, null, new Object[]{5.0, 0.0});
		checkContents(symbolicAnalysis1, asExpressionInOCL, isDeads(asOperationCallExp, asNumExp, asDenExp), mayBeNulls(contextVariable), null, null);

		// 5.0 / 1.0
		SymbolicAnalysis symbolicAnalysis2 = ocl.getSymbolicAnalysis(asExpressionInOCL, null, null, new Object[]{5.0, 1.0});
		checkContents(symbolicAnalysis2, asExpressionInOCL, isDeads(asLiteralExp), mayBeNulls(contextVariable), null, null);

		// not-null / 0.0
		SymbolicAnalysis symbolicAnalysis3 = ocl.getSymbolicAnalysis(asExpressionInOCL, null, null, new Object[]{new SymbolicUnknownValue("p0", TypeId.REAL, false, false), 0.0});
		checkContents(symbolicAnalysis3, asExpressionInOCL, isDeads(asOperationCallExp, asNumExp, asDenExp), mayBeNulls(contextVariable), null, null);

		// not-null / 1.0
		SymbolicAnalysis symbolicAnalysis4 = ocl.getSymbolicAnalysis(asExpressionInOCL, null, null, new Object[]{new SymbolicUnknownValue("p0", TypeId.REAL, false, false), 1.0});
		checkContents(symbolicAnalysis4, asExpressionInOCL, isDeads(asLiteralExp), mayBeNulls(contextVariable), null, null);

		// 5.0 / not-null-maybe-zero
		SymbolicAnalysis symbolicAnalysis5 = ocl.getSymbolicAnalysis(asExpressionInOCL, null, null, new Object[]{5.0, new SymbolicUnknownValue("p0", TypeId.REAL, false, false)});
		checkContents(symbolicAnalysis5, asExpressionInOCL, null, mayBeNulls(contextVariable), null, null);

		// 5 / not-null-maybe-zero
		SymbolicAnalysis symbolicAnalysis6 = ocl.getSymbolicAnalysis(asExpressionInOCL, null, null, new Object[]{5, new SymbolicUnknownValue("p0", TypeId.INTEGER, false, false)});
		checkContents(symbolicAnalysis6, asExpressionInOCL, null, mayBeNulls(contextVariable), null, null);

		ocl.dispose();
	}

	public void testSymbolicAnalysis_IfNullNameGuard() throws Exception {
		MyOCL ocl = new MyOCL("IfNullNameGuard", "IfNullNameGuard() : Boolean",
				"(let v : String[?] = self?.toString() in if v = null then 'null' else v endif) <> null");
		ExpressionInOCL asExpressionInOCL = ocl.getExpressionInOCL();
		VariableDeclaration contextVariable = PivotUtil.getOwnedContext(asExpressionInOCL);
		OperationCallExp neOperationCallExp = (OperationCallExp) asExpressionInOCL.getOwnedBody();
		NullLiteralExp neNullExp = (NullLiteralExp) PivotUtil.getOwnedArgument(neOperationCallExp, 0);
		LetExp asLetExp = (LetExp) PivotUtil.getOwnedSource(neOperationCallExp);
		OperationCallExp asLetVariableInit = (OperationCallExp) PivotUtil.getOwnedInit(PivotUtil.getOwnedVariable(asLetExp));
		VariableExp selfExp = (VariableExp) PivotUtil.getOwnedSource(asLetVariableInit);
		LetVariable asLetVariable = (LetVariable) PivotUtil.getOwnedVariable(asLetExp);
		IfExp asIf = (IfExp) PivotUtil.getOwnedIn(asLetExp);
		OperationCallExp asCondition = (OperationCallExp) PivotUtil.getOwnedCondition(asIf);
		StringLiteralExp asThenLiteralExp = (StringLiteralExp) PivotUtil.getOwnedThen(asIf);
		VariableExp asElseVariableExp = (VariableExp) PivotUtil.getOwnedElse(asIf);
		VariableExp asConditionVariableExp = (VariableExp) PivotUtil.getOwnedSource(asCondition);
		NullLiteralExp asConditionNullExp = (NullLiteralExp) PivotUtil.getOwnedArgument(asCondition, 0);

		// null self
		SymbolicAnalysis symbolicAnalysis1 = ocl.getSymbolicAnalysis(asExpressionInOCL, null, null, new Object[0]);
		checkContents(symbolicAnalysis1, asExpressionInOCL, isDeads(asElseVariableExp), mayBeNulls(contextVariable, selfExp, asLetVariableInit, asLetVariable, asConditionVariableExp, asConditionNullExp, neNullExp), null, null);
		assertTrue("Result",  ocl.getIdResolver().oclEquals(symbolicAnalysis1.getBaseSymbolicEvaluationEnvironment().getSymbolicValue(asIf).getKnownValue(), "null"));

		// non-null unknown self
		SymbolicAnalysis symbolicAnalysis2 = ocl.getSymbolicAnalysis(asExpressionInOCL, new SymbolicUnknownValue("p0", TypeId.STRING, false, false), null, new Object[0]);
		checkContents(symbolicAnalysis2, asExpressionInOCL, isDeads(asThenLiteralExp), mayBeNulls(asConditionNullExp, neNullExp), null, null);

		ocl.dispose();
	}

	public void testSymbolicAnalysis_IfOclIsUnDefinedNameGuard() throws Exception {
		MyOCL ocl = new MyOCL("IfOclIsUnDefinedNameGuard", "IfOclIsUnDefinedNameGuard(deductions : Deductions) : Dummy[?]",
				"if deductions.mayBeNullDummy.oclIsUndefined() then null else deductions.mayBeNullDummy.dummy endif");
		ExpressionInOCL asExpressionInOCL = ocl.getExpressionInOCL();
		VariableDeclaration contextVariable = PivotUtil.getOwnedContext(asExpressionInOCL);
		VariableDeclaration firstParameterVariable = PivotUtil.getOwnedParameter(asExpressionInOCL, 0);
		IfExp asIf = (IfExp) asExpressionInOCL.getOwnedBody();
		OperationCallExp asCondition = (OperationCallExp) PivotUtil.getOwnedCondition(asIf);
		PropertyCallExp asConditionSrc = (PropertyCallExp) PivotUtil.getOwnedSource(asCondition);
		VariableExp asConditionVariableExp = (VariableExp) PivotUtil.getOwnedSource(asConditionSrc);
		NullLiteralExp nullLiteralExp = (NullLiteralExp) PivotUtil.getOwnedThen(asIf);
		PropertyCallExp asElse = (PropertyCallExp) PivotUtil.getOwnedElse(asIf);
		PropertyCallExp asElseSrc = (PropertyCallExp) PivotUtil.getOwnedSource(asElse);
		VariableExp asElseVariableExp = (VariableExp) PivotUtil.getOwnedSource(asElseSrc);

		EObject dummyInstance = ocl.createDummyInstance();
		SymbolicUnknownValue selfSymbolicVariable = new SymbolicUnknownValue("p0", TypeId.STRING, false, false);

		// null deduction
		SymbolicAnalysis symbolicAnalysis1 = ocl.getSymbolicAnalysis(asExpressionInOCL, null, null, new Object[] {null});
		checkContents(symbolicAnalysis1, asExpressionInOCL, isDeads(asElse, asElseSrc, asElseVariableExp), mayBeNulls(contextVariable, firstParameterVariable, asConditionVariableExp, asExpressionInOCL, asIf, nullLiteralExp), null, isInvalids(asConditionSrc));

		// unknown may-be-null deduction
		SymbolicVariableValue symbolicVariable2 = new SymbolicVariableValue(asExpressionInOCL.getOwnedParameters().get(0), true, false);
		SymbolicAnalysis symbolicAnalysis2 = ocl.getSymbolicAnalysis(asExpressionInOCL, selfSymbolicVariable, null, new Object[] {symbolicVariable2});
		checkContents(symbolicAnalysis2, asExpressionInOCL, null, mayBeNulls(firstParameterVariable, asExpressionInOCL, asIf, asConditionVariableExp, nullLiteralExp), mayBeInvalids(asConditionSrc), null);

		// non-null unknown deduction
		SymbolicVariableValue symbolicVariable3 = new SymbolicVariableValue(asExpressionInOCL.getOwnedParameters().get(0), false, false);
		SymbolicAnalysis symbolicAnalysis3 = ocl.getSymbolicAnalysis(asExpressionInOCL, selfSymbolicVariable, null, new Object[] {symbolicVariable3});
		checkContents(symbolicAnalysis3, asExpressionInOCL, null, mayBeNulls(asExpressionInOCL, asIf, asConditionSrc, nullLiteralExp), null, null);

		// non-null known deduction
		SymbolicAnalysis symbolicAnalysis4 = ocl.getSymbolicAnalysis(asExpressionInOCL, null, null, new Object[] {dummyInstance});
		checkContents(symbolicAnalysis4, asExpressionInOCL, isDeads(asElse, asElseSrc, asElseVariableExp), mayBeNulls(contextVariable, asExpressionInOCL, asIf, nullLiteralExp), null, isInvalids(asConditionSrc));

		ocl.dispose();
	}

	public void testSymbolicAnalysis_ImpliesGuard() throws Exception {
			MyOCL ocl = new MyOCL("ImpliesGuard", "ImpliesGuard(x : Integer) : Boolean",
					"x <> null implies x > 0");
			ExpressionInOCL asExpressionInOCL = ocl.getExpressionInOCL();
			VariableDeclaration contextVariable = PivotUtil.getOwnedContext(asExpressionInOCL);
			VariableDeclaration firstParameterVariable = PivotUtil.getOwnedParameter(asExpressionInOCL, 0);
			OperationCallExp impliesExp = (OperationCallExp) asExpressionInOCL.getOwnedBody();
			OperationCallExp notEqExp = (OperationCallExp) PivotUtil.getOwnedSource(impliesExp);
			OperationCallExp gtExp = (OperationCallExp) PivotUtil.getOwnedArgument(impliesExp, 0);
			VariableExp neSourceExp = (VariableExp) PivotUtil.getOwnedSource(notEqExp);
			NullLiteralExp nullExp = (NullLiteralExp) PivotUtil.getOwnedArgument(notEqExp, 0);
			VariableExp gtSourceExp = (VariableExp) PivotUtil.getOwnedSource(gtExp);
			NumericLiteralExp zeroExp = (NumericLiteralExp) PivotUtil.getOwnedArgument(gtExp, 0);

			// may-be-null x
			SymbolicVariableValue symbolicVariable = new SymbolicVariableValue(asExpressionInOCL.getOwnedParameters().get(0), true, false);
			SymbolicAnalysis symbolicAnalysis3 = ocl.getSymbolicAnalysis(asExpressionInOCL, null, null, new Object[]{symbolicVariable});
			checkContents(symbolicAnalysis3, asExpressionInOCL, null, mayBeNulls(contextVariable, firstParameterVariable, neSourceExp, nullExp), null, null);

			// non-null known x
			SymbolicAnalysis symbolicAnalysis1 = ocl.getSymbolicAnalysis(asExpressionInOCL, null, null, new Object[]{5});
			checkContents(symbolicAnalysis1, asExpressionInOCL, isDeads(notEqExp, neSourceExp, nullExp), mayBeNulls(contextVariable), null, null);
			BaseSymbolicEvaluationEnvironment symbolicEvaluationEnvironment1 = symbolicAnalysis1.getBaseSymbolicEvaluationEnvironment();
			assertTrue(symbolicEvaluationEnvironment1.isTrue(gtExp));
	//		assertTrue(symbolicEvaluationEnvironment1.isTrue(asExpressionInOCL));

			// null x
			SymbolicAnalysis symbolicAnalysis2 = ocl.getSymbolicAnalysis(asExpressionInOCL, null, null, new Object[]{null});
			checkContents(symbolicAnalysis2, asExpressionInOCL, isDeads(gtExp, gtSourceExp, zeroExp), mayBeNulls(contextVariable, firstParameterVariable, neSourceExp, nullExp), null, null);
			BaseSymbolicEvaluationEnvironment symbolicEvaluationEnvironment2 = symbolicAnalysis2.getBaseSymbolicEvaluationEnvironment();
			assertTrue(symbolicEvaluationEnvironment2.isFalse(notEqExp));
	//		assertTrue(symbolicEvaluationEnvironment2.isTrue(asExpressionInOCL));

			// non-null unknown x
			SymbolicAnalysis symbolicAnalysis4 = ocl.getSymbolicAnalysis(asExpressionInOCL, null, null, new Object[]{new SymbolicUnknownValue("p0", TypeId.INTEGER, false, false)});
			checkContents(symbolicAnalysis4, asExpressionInOCL, null, mayBeNulls(contextVariable, nullExp), null, null);
			BaseSymbolicEvaluationEnvironment symbolicEvaluationEnvironment4 = symbolicAnalysis4.getBaseSymbolicEvaluationEnvironment();
			assertFalse(symbolicEvaluationEnvironment4.mayBeInvalidOrNull(gtExp));
	//		assertFalse(symbolicEvaluationEnvironment4.mayBeInvalidOrNull(asExpressionInOCL));

			ocl.dispose();
		}

	public void testSymbolicAnalysis_Implies2Guard() throws Exception {
			MyOCL ocl = new MyOCL("Implies2Guard", "Implies2Guard(x : Integer) : Boolean",
					"x <> null implies2 x > 0");
			ExpressionInOCL asExpressionInOCL = ocl.getExpressionInOCL();
			VariableDeclaration contextVariable = PivotUtil.getOwnedContext(asExpressionInOCL);
			VariableDeclaration firstParameterVariable = PivotUtil.getOwnedParameter(asExpressionInOCL, 0);
			OperationCallExp impliesExp = (OperationCallExp) asExpressionInOCL.getOwnedBody();
			OperationCallExp notEqExp = (OperationCallExp) PivotUtil.getOwnedSource(impliesExp);
			OperationCallExp gtExp = (OperationCallExp) PivotUtil.getOwnedArgument(impliesExp, 0);
			VariableExp neSourceExp = (VariableExp) PivotUtil.getOwnedSource(notEqExp);
			NullLiteralExp nullExp = (NullLiteralExp) PivotUtil.getOwnedArgument(notEqExp, 0);
			VariableExp gtSourceExp = (VariableExp) PivotUtil.getOwnedSource(gtExp);
			NumericLiteralExp zeroExp = (NumericLiteralExp) PivotUtil.getOwnedArgument(gtExp, 0);

			// non-null known x
			SymbolicAnalysis symbolicAnalysis1 = ocl.getSymbolicAnalysis(asExpressionInOCL, null, null, new Object[]{5});
			checkContents(symbolicAnalysis1, asExpressionInOCL, isDeads(notEqExp, neSourceExp, nullExp), mayBeNulls(contextVariable), null, null);
			BaseSymbolicEvaluationEnvironment symbolicEvaluationEnvironment1 = symbolicAnalysis1.getBaseSymbolicEvaluationEnvironment();
			assertTrue(symbolicEvaluationEnvironment1.isTrue(gtExp));
	//		assertTrue(symbolicEvaluationEnvironment1.isTrue(asExpressionInOCL));

			// null x
			SymbolicAnalysis symbolicAnalysis2 = ocl.getSymbolicAnalysis(asExpressionInOCL, null, null, new Object[]{null});
			checkContents(symbolicAnalysis2, asExpressionInOCL, isDeads(gtExp, gtSourceExp, zeroExp), mayBeNulls(contextVariable, firstParameterVariable, neSourceExp, nullExp), null, null);
			BaseSymbolicEvaluationEnvironment symbolicEvaluationEnvironment2 = symbolicAnalysis2.getBaseSymbolicEvaluationEnvironment();
			assertTrue(symbolicEvaluationEnvironment2.isFalse(notEqExp));
	//		assertTrue(symbolicEvaluationEnvironment2.isTrue(asExpressionInOCL));

			// may-be-null x
			SymbolicVariableValue symbolicVariable = new SymbolicVariableValue(asExpressionInOCL.getOwnedParameters().get(0), true, false);
			SymbolicAnalysis symbolicAnalysis3 = ocl.getSymbolicAnalysis(asExpressionInOCL, null, null, new Object[]{symbolicVariable});
			checkContents(symbolicAnalysis3, asExpressionInOCL, null, mayBeNulls(contextVariable, firstParameterVariable, neSourceExp, nullExp), null, null);

			// non-null unknown x
			SymbolicAnalysis symbolicAnalysis4 = ocl.getSymbolicAnalysis(asExpressionInOCL, null, null, new Object[]{new SymbolicUnknownValue("p0", TypeId.INTEGER, false, false)});
			checkContents(symbolicAnalysis4, asExpressionInOCL, null, mayBeNulls(contextVariable, nullExp), null, null);
			BaseSymbolicEvaluationEnvironment symbolicEvaluationEnvironment4 = symbolicAnalysis4.getBaseSymbolicEvaluationEnvironment();
			assertFalse(symbolicEvaluationEnvironment4.mayBeInvalidOrNull(gtExp));
	//		assertFalse(symbolicEvaluationEnvironment4.mayBeInvalidOrNull(asExpressionInOCL));

			ocl.dispose();
		}

	public void testSymbolicAnalysis_IndirectAndGuard() throws Exception {
			MyOCL ocl = new MyOCL("IndirectAndGuard", "IndirectAndGuard(x : Deductions[1]) : Boolean",
					"x?.mayBeNullDummy <> null and x?.mayBeNullDummy.dummy = null");
			ExpressionInOCL asExpressionInOCL = ocl.getExpressionInOCL();
			VariableDeclaration contextVariable = PivotUtil.getOwnedContext(asExpressionInOCL);
			VariableDeclaration firstParameterVariable = PivotUtil.getOwnedParameter(asExpressionInOCL, 0);
			OperationCallExp andExp = (OperationCallExp) asExpressionInOCL.getOwnedBody();
			OperationCallExp notEqExp = (OperationCallExp) PivotUtil.getOwnedSource(andExp);
			PropertyCallExp notEqPropCallExp = (PropertyCallExp) PivotUtil.getOwnedSource(notEqExp);
			VariableExp notEqSourceExp = (VariableExp) PivotUtil.getOwnedSource(notEqPropCallExp);
			OperationCallExp eqExp = (OperationCallExp) PivotUtil.getOwnedArgument(andExp, 0);
			PropertyCallExp eqPropCallExp = (PropertyCallExp) PivotUtil.getOwnedSource(eqExp);
			PropertyCallExp eqPropCallPropCallExp = (PropertyCallExp) PivotUtil.getOwnedSource(eqPropCallExp);
			VariableExp eqSourceExp = (VariableExp) PivotUtil.getOwnedSource(eqPropCallPropCallExp);
			NullLiteralExp neNullExp = (NullLiteralExp) PivotUtil.getOwnedArgument(notEqExp, 0);
		//	VariableExp gtSourceExp = (VariableExp) PivotUtil.getOwnedSource(eqExp);
			NullLiteralExp eqNullExp = (NullLiteralExp) PivotUtil.getOwnedArgument(eqExp, 0);

			// null x
			SymbolicAnalysis symbolicAnalysis1 = ocl.getSymbolicAnalysis(asExpressionInOCL, null, null, new Object[]{null});
			checkContents(symbolicAnalysis1, asExpressionInOCL, isDeads(eqExp, eqPropCallExp, eqPropCallPropCallExp, eqSourceExp, eqNullExp), mayBeNulls(contextVariable, firstParameterVariable, notEqPropCallExp, notEqSourceExp, neNullExp), null, null);
			assertTrue(symbolicAnalysis1.getBaseSymbolicEvaluationEnvironment().isFalse(notEqExp));
			assertTrue(symbolicAnalysis1.getBaseSymbolicEvaluationEnvironment().isFalse(andExp));
			assertTrue(symbolicAnalysis1.getBaseSymbolicEvaluationEnvironment().isFalse(asExpressionInOCL));

		/*	// null self
			SymbolicEvaluationVisitor symbolicAnalysis2 = ocl.getSymbolicAnalysis(asExpressionInOCL, null, null, new Object[]{null});
			checkContents(symbolicAnalysis2, asExpressionInOCL, isDeads(gtExp, gtSourceExp, zeroExp), mayBeNulls(neSourceExp, nullExp}, null, null);
			assertTrue(symbolicAnalysis2.isFalse(notEqExp));
			assertTrue(symbolicAnalysis2.isTrue(asExpressionInOCL));

			// may-be-null self
			SymbolicVariableValueImpl symbolicVariable = new SymbolicVariableValueImpl(asExpressionInOCL.getOwnedParameters().get(0), true, false);
			SymbolicEvaluationVisitor symbolicAnalysis3 = ocl.getSymbolicAnalysis(asExpressionInOCL, null, null, new Object[]{symbolicVariable});
			checkContents(symbolicAnalysis3, asExpressionInOCL, null, mayBeNulls(neSourceExp, nullExp}, null, null);
	*/
			ocl.dispose();
		}

	public void testSymbolicAnalysis_IndirectAndGuard_Operation() throws Exception {
			MyOCL ocl = new MyOCL("IndirectAndGuard_Operation", "IndirectAndGuard_Operation(x : Deductions[1]) : Boolean",
					"x?.mayBeNullDummy <> null and x?.mayBeNullDummy.func(1,2) = null");
			ExpressionInOCL asExpressionInOCL = ocl.getExpressionInOCL();
			VariableDeclaration contextVariable = PivotUtil.getOwnedContext(asExpressionInOCL);
			VariableDeclaration firstParameterVariable = PivotUtil.getOwnedParameter(asExpressionInOCL, 0);
			OperationCallExp andExp = (OperationCallExp) asExpressionInOCL.getOwnedBody();
			OperationCallExp notEqExp = (OperationCallExp) PivotUtil.getOwnedSource(andExp);
			PropertyCallExp notEqPropCallExp = (PropertyCallExp) PivotUtil.getOwnedSource(notEqExp);
			VariableExp notEqSourceExp = (VariableExp) PivotUtil.getOwnedSource(notEqPropCallExp);
			OperationCallExp eqExp = (OperationCallExp) PivotUtil.getOwnedArgument(andExp, 0);
			OperationCallExp eqOpCallExp = (OperationCallExp) PivotUtil.getOwnedSource(eqExp);
			PropertyCallExp eqOpCallPropCallExp = (PropertyCallExp) PivotUtil.getOwnedSource(eqOpCallExp);
			VariableExp eqSourceExp = (VariableExp) PivotUtil.getOwnedSource(eqOpCallPropCallExp);
			NullLiteralExp neNullExp = (NullLiteralExp) PivotUtil.getOwnedArgument(notEqExp, 0);
		//	VariableExp gtSourceExp = (VariableExp) PivotUtil.getOwnedSource(eqExp);
			NullLiteralExp eqNullExp = (NullLiteralExp) PivotUtil.getOwnedArgument(eqExp, 0);
			IntegerLiteralExp eq1Exp = (IntegerLiteralExp) PivotUtil.getOwnedArgument(eqOpCallExp, 0);
			IntegerLiteralExp eq2Exp = (IntegerLiteralExp) PivotUtil.getOwnedArgument(eqOpCallExp, 1);

			// null x
			SymbolicAnalysis symbolicAnalysis1 = ocl.getSymbolicAnalysis(asExpressionInOCL, null, null, new Object[]{null});
			checkContents(symbolicAnalysis1, asExpressionInOCL, isDeads(eqExp, eqOpCallExp, eqOpCallPropCallExp, eqSourceExp, eqNullExp, eq1Exp, eq2Exp), mayBeNulls(contextVariable, firstParameterVariable, notEqPropCallExp, notEqSourceExp, neNullExp), null, null);
			assertTrue(symbolicAnalysis1.getBaseSymbolicEvaluationEnvironment().isFalse(notEqExp));
			assertTrue(symbolicAnalysis1.getBaseSymbolicEvaluationEnvironment().isFalse(andExp));
			assertTrue(symbolicAnalysis1.getBaseSymbolicEvaluationEnvironment().isFalse(asExpressionInOCL));

		/*	// null self
			SymbolicEvaluationVisitor symbolicAnalysis2 = ocl.getSymbolicAnalysis(asExpressionInOCL, null, null, new Object[]{null});
			checkContents(symbolicAnalysis2, asExpressionInOCL, isDeads(gtExp, gtSourceExp, zeroExp), mayBeNulls(neSourceExp, nullExp}, null, null);
			assertTrue(symbolicAnalysis2.isFalse(notEqExp));
			assertTrue(symbolicAnalysis2.isTrue(asExpressionInOCL));

			// may-be-null self
			SymbolicVariableValueImpl symbolicVariable = new SymbolicVariableValueImpl(asExpressionInOCL.getOwnedParameters().get(0), true, false);
			SymbolicEvaluationVisitor symbolicAnalysis3 = ocl.getSymbolicAnalysis(asExpressionInOCL, null, null, new Object[]{symbolicVariable});
			checkContents(symbolicAnalysis3, asExpressionInOCL, null, mayBeNulls(neSourceExp, nullExp}, null, null);
	*/
			ocl.dispose();
		}

	public void testSymbolicAnalysis_NotEmptyGuardedFirst() throws Exception {
		MyOCL ocl = new MyOCL("GuardedFirst", "GuardedFirst(x : Sequence(Integer)) : Integer",
				"if x->notEmpty() then x->first() else -1 endif");
		ExpressionInOCL asExpressionInOCL = ocl.getExpressionInOCL();
		VariableDeclaration contextVariable = PivotUtil.getOwnedContext(asExpressionInOCL);
		VariableDeclaration firstParameterVariable = PivotUtil.getOwnedParameter(asExpressionInOCL, 0);
		IfExp ifExp = (IfExp) asExpressionInOCL.getOwnedBody();
		OperationCallExp notEmptyExp = (OperationCallExp) ifExp.getOwnedCondition();
		VariableExp sourceNotEmptyExp = (VariableExp) PivotUtil.getOwnedSource(notEmptyExp);
		OperationCallExp firstExp = (OperationCallExp) ifExp.getOwnedThen();
		VariableExp sourcefirstExp = (VariableExp) PivotUtil.getOwnedSource(firstExp);
		OperationCallExp negExp = (OperationCallExp) ifExp.getOwnedElse();
		IntegerLiteralExp literalExp = (IntegerLiteralExp) negExp.getOwnedSource();

		// non-null empty x
		SymbolicAnalysis symbolicAnalysis1 = ocl.getSymbolicAnalysis(asExpressionInOCL, null, null, new Object[]{ValueUtil.EMPTY_SET});
		checkContents(symbolicAnalysis1, asExpressionInOCL, isDeads(firstExp, sourcefirstExp), mayBeNulls(contextVariable), null, null);
		BaseSymbolicEvaluationEnvironment symbolicEvaluationEnvironment1 = symbolicAnalysis1.getBaseSymbolicEvaluationEnvironment();
		assertTrue(symbolicEvaluationEnvironment1.isFalse(notEmptyExp));

		// non-null not-empty x
		SetValue set2 = ValueUtil.createSetOfEach(TypeId.SET.getSpecializedId(TypeId.INTEGER), ValueUtil.integerValueOf(99));
		SymbolicAnalysis symbolicAnalysis2 = ocl.getSymbolicAnalysis(asExpressionInOCL, null, null, new Object[]{set2});
		checkContents(symbolicAnalysis2, asExpressionInOCL, isDeads(negExp, literalExp), mayBeNulls(contextVariable), null, null);
		BaseSymbolicEvaluationEnvironment symbolicEvaluationEnvironment2 = symbolicAnalysis2.getBaseSymbolicEvaluationEnvironment();
		assertTrue(symbolicEvaluationEnvironment2.isTrue(notEmptyExp));

		// non-null unknown x
		SymbolicVariableValue symbolicVariable = new SymbolicVariableValue(asExpressionInOCL.getOwnedParameters().get(0), false, false);
		SymbolicAnalysis symbolicAnalysis3 = ocl.getSymbolicAnalysis(asExpressionInOCL, null, null, new Object[]{symbolicVariable});
		checkContents(symbolicAnalysis3, asExpressionInOCL, null, mayBeNulls(contextVariable), null, null);

		// null collection x (does not behave as empty set)
		SymbolicAnalysis symbolicAnalysis4 = ocl.getSymbolicAnalysis(asExpressionInOCL, null, null, new Object[]{null});
		checkContents(symbolicAnalysis4, asExpressionInOCL, isDeads(firstExp, sourcefirstExp, negExp, literalExp), mayBeNulls(contextVariable, firstParameterVariable, sourceNotEmptyExp), null, isInvalids(asExpressionInOCL, ifExp, notEmptyExp));

		ocl.dispose();
	}

	public void testSymbolicAnalysis_NotIsEmptyGuardedFirst() throws Exception {		// XXX tweaked to fail
		MyOCL ocl = new MyOCL("GuardedFirst", "GuardedFirst(x : Sequence(Integer)) : Integer",
				"if not x->isEmpty() then x->first() else -1 endif");
		ExpressionInOCL asExpressionInOCL = ocl.getExpressionInOCL();
		VariableDeclaration contextVariable = PivotUtil.getOwnedContext(asExpressionInOCL);
		VariableDeclaration firstParameterVariable = PivotUtil.getOwnedParameter(asExpressionInOCL, 0);
		IfExp ifExp = (IfExp) asExpressionInOCL.getOwnedBody();
		OperationCallExp notExp = (OperationCallExp) ifExp.getOwnedCondition();
		OperationCallExp isEmptyExp = (OperationCallExp) PivotUtil.getOwnedSource(notExp);
		VariableExp sourceIsEmptyExp = (VariableExp) PivotUtil.getOwnedSource(isEmptyExp);
		OperationCallExp firstExp = (OperationCallExp) ifExp.getOwnedThen();
		VariableExp sourcefirstExp = (VariableExp) PivotUtil.getOwnedSource(firstExp);
		OperationCallExp negExp = (OperationCallExp) ifExp.getOwnedElse();
		IntegerLiteralExp literalExp = (IntegerLiteralExp) negExp.getOwnedSource();

		// non-null empty x
		SymbolicAnalysis symbolicAnalysis1 = ocl.getSymbolicAnalysis(asExpressionInOCL, null, null, new Object[]{ValueUtil.EMPTY_SET});
		checkContents(symbolicAnalysis1, asExpressionInOCL, isDeads(firstExp, sourcefirstExp), mayBeNulls(contextVariable), null, null);
		BaseSymbolicEvaluationEnvironment symbolicEvaluationEnvironment1 = symbolicAnalysis1.getBaseSymbolicEvaluationEnvironment();
		assertTrue(symbolicEvaluationEnvironment1.isFalse(notExp));

		// non-null not-empty x
		SetValue set2 = ValueUtil.createSetOfEach(TypeId.SET.getSpecializedId(TypeId.INTEGER), ValueUtil.integerValueOf(99));
		SymbolicAnalysis symbolicAnalysis2 = ocl.getSymbolicAnalysis(asExpressionInOCL, null, null, new Object[]{set2});
		checkContents(symbolicAnalysis2, asExpressionInOCL, isDeads(negExp, literalExp), mayBeNulls(contextVariable), null, null);
		BaseSymbolicEvaluationEnvironment symbolicEvaluationEnvironment2 = symbolicAnalysis2.getBaseSymbolicEvaluationEnvironment();
		assertTrue(symbolicEvaluationEnvironment2.isTrue(notExp));

		// non-null unknown x
		SymbolicVariableValue symbolicVariable = new SymbolicVariableValue(asExpressionInOCL.getOwnedParameters().get(0), false, false);
		SymbolicAnalysis symbolicAnalysis3 = ocl.getSymbolicAnalysis(asExpressionInOCL, null, null, new Object[]{symbolicVariable});
		checkContents(symbolicAnalysis3, asExpressionInOCL, null, mayBeNulls(contextVariable), null, null);

		// null collection x (does not behave as empty set)
		SymbolicAnalysis symbolicAnalysis4 = ocl.getSymbolicAnalysis(asExpressionInOCL, null, null, new Object[]{null});
		checkContents(symbolicAnalysis4, asExpressionInOCL, isDeads(firstExp, sourcefirstExp, negExp, literalExp), mayBeNulls(contextVariable, firstParameterVariable, sourceIsEmptyExp), null, isInvalids(asExpressionInOCL, ifExp, notExp, isEmptyExp));

		ocl.dispose();
	}

	public void testSymbolicAnalysis_SizeGtGuardedFirst() throws Exception {
		MyOCL ocl = new MyOCL("GuardedFirst", "GuardedFirst(x : Sequence(Integer)) : Integer",
				"if x->size() > 0 then x->first() else -1 endif");
		ExpressionInOCL asExpressionInOCL = ocl.getExpressionInOCL();
		VariableDeclaration contextVariable = PivotUtil.getOwnedContext(asExpressionInOCL);
		VariableDeclaration firstParameterVariable = PivotUtil.getOwnedParameter(asExpressionInOCL, 0);
		IfExp ifExp = (IfExp) asExpressionInOCL.getOwnedBody();
		OperationCallExp gtExp = (OperationCallExp) ifExp.getOwnedCondition();
		OperationCallExp sizeExp = (OperationCallExp) PivotUtil.getOwnedSource(gtExp);
		VariableExp sourceSizeExp = (VariableExp) PivotUtil.getOwnedSource(sizeExp);
		OperationCallExp firstExp = (OperationCallExp) ifExp.getOwnedThen();
		VariableExp sourcefirstExp = (VariableExp) PivotUtil.getOwnedSource(firstExp);
		OperationCallExp negExp = (OperationCallExp) ifExp.getOwnedElse();
		IntegerLiteralExp literalExp = (IntegerLiteralExp) negExp.getOwnedSource();

		// non-null empty x
		SymbolicAnalysis symbolicAnalysis1 = ocl.getSymbolicAnalysis(asExpressionInOCL, null, null, new Object[]{ValueUtil.EMPTY_SET});
		checkContents(symbolicAnalysis1, asExpressionInOCL, isDeads(firstExp, sourcefirstExp), mayBeNulls(contextVariable), null, null);
		BaseSymbolicEvaluationEnvironment symbolicEvaluationEnvironment1 = symbolicAnalysis1.getBaseSymbolicEvaluationEnvironment();
		assertTrue(symbolicEvaluationEnvironment1.isFalse(gtExp));

		// non-null not-empty x
		SetValue set2 = ValueUtil.createSetOfEach(TypeId.SET.getSpecializedId(TypeId.INTEGER), ValueUtil.integerValueOf(99));
		SymbolicAnalysis symbolicAnalysis2 = ocl.getSymbolicAnalysis(asExpressionInOCL, null, null, new Object[]{set2});
		checkContents(symbolicAnalysis2, asExpressionInOCL, isDeads(negExp, literalExp), mayBeNulls(contextVariable), null, null);
		BaseSymbolicEvaluationEnvironment symbolicEvaluationEnvironment2 = symbolicAnalysis2.getBaseSymbolicEvaluationEnvironment();
		assertTrue(symbolicEvaluationEnvironment2.isTrue(gtExp));

		// non-null unknown x
		SymbolicVariableValue symbolicVariable = new SymbolicVariableValue(asExpressionInOCL.getOwnedParameters().get(0), false, false);
		SymbolicAnalysis symbolicAnalysis3 = ocl.getSymbolicAnalysis(asExpressionInOCL, null, null, new Object[]{symbolicVariable});
		checkContents(symbolicAnalysis3, asExpressionInOCL, null, mayBeNulls(contextVariable), null, null);

		// null collection x (does not behave as empty set)
		SymbolicAnalysis symbolicAnalysis4 = ocl.getSymbolicAnalysis(asExpressionInOCL, null, null, new Object[]{null});
		checkContents(symbolicAnalysis4, asExpressionInOCL, isDeads(firstExp, sourcefirstExp, negExp, literalExp), mayBeNulls(contextVariable, firstParameterVariable, sourceSizeExp), null, isInvalids(asExpressionInOCL, ifExp, gtExp, sizeExp));

		ocl.dispose();
	}

	public void testSymbolicAnalysis_SizeNeGuardedFirst() throws Exception {
		MyOCL ocl = new MyOCL("GuardedFirst", "GuardedFirst(x : Sequence(Integer)) : Integer",
				"if x->size() <> 0 then x->first() else -1 endif");
		ExpressionInOCL asExpressionInOCL = ocl.getExpressionInOCL();
		VariableDeclaration contextVariable = PivotUtil.getOwnedContext(asExpressionInOCL);
		VariableDeclaration firstParameterVariable = PivotUtil.getOwnedParameter(asExpressionInOCL, 0);
		IfExp ifExp = (IfExp) asExpressionInOCL.getOwnedBody();
		OperationCallExp neExp = (OperationCallExp) ifExp.getOwnedCondition();
		OperationCallExp sizeExp = (OperationCallExp) PivotUtil.getOwnedSource(neExp);
		VariableExp sourceSizeExp = (VariableExp) PivotUtil.getOwnedSource(sizeExp);
		OperationCallExp firstExp = (OperationCallExp) ifExp.getOwnedThen();
		VariableExp sourcefirstExp = (VariableExp) PivotUtil.getOwnedSource(firstExp);
		OperationCallExp negExp = (OperationCallExp) ifExp.getOwnedElse();
		IntegerLiteralExp literalExp = (IntegerLiteralExp) negExp.getOwnedSource();

		// non-null empty x
		SymbolicAnalysis symbolicAnalysis1 = ocl.getSymbolicAnalysis(asExpressionInOCL, null, null, new Object[]{ValueUtil.EMPTY_SET});
		checkContents(symbolicAnalysis1, asExpressionInOCL, isDeads(firstExp, sourcefirstExp), mayBeNulls(contextVariable), null, null);
		BaseSymbolicEvaluationEnvironment symbolicEvaluationEnvironment1 = symbolicAnalysis1.getBaseSymbolicEvaluationEnvironment();
		assertTrue(symbolicEvaluationEnvironment1.isFalse(neExp));

		// non-null not-empty x
		SetValue set2 = ValueUtil.createSetOfEach(TypeId.SET.getSpecializedId(TypeId.INTEGER), ValueUtil.integerValueOf(99));
		SymbolicAnalysis symbolicAnalysis2 = ocl.getSymbolicAnalysis(asExpressionInOCL, null, null, new Object[]{set2});
		checkContents(symbolicAnalysis2, asExpressionInOCL, isDeads(negExp, literalExp), mayBeNulls(contextVariable), null, null);
		BaseSymbolicEvaluationEnvironment symbolicEvaluationEnvironment2 = symbolicAnalysis2.getBaseSymbolicEvaluationEnvironment();
		assertTrue(symbolicEvaluationEnvironment2.isTrue(neExp));

		// non-null unknown x
		SymbolicVariableValue symbolicVariable = new SymbolicVariableValue(asExpressionInOCL.getOwnedParameters().get(0), false, false);
		SymbolicAnalysis symbolicAnalysis3 = ocl.getSymbolicAnalysis(asExpressionInOCL, null, null, new Object[]{symbolicVariable});
		checkContents(symbolicAnalysis3, asExpressionInOCL, null, mayBeNulls(contextVariable), null, null);

		// null collection x (does not behave as empty set)
		SymbolicAnalysis symbolicAnalysis4 = ocl.getSymbolicAnalysis(asExpressionInOCL, null, null, new Object[]{null});
		checkContents(symbolicAnalysis4, asExpressionInOCL, isDeads(firstExp, sourcefirstExp, negExp, literalExp), mayBeNulls(contextVariable, firstParameterVariable, sourceSizeExp), null, isInvalids(asExpressionInOCL, ifExp, neExp, sizeExp));

		ocl.dispose();
	}

	public void testSymbolicAnalysis_OrGuard() throws Exception {
		MyOCL ocl = new MyOCL("OrGuard", "OrGuard(x : Integer) : Boolean",
				"x = null or x > 0");
		ExpressionInOCL asExpressionInOCL = ocl.getExpressionInOCL();
		VariableDeclaration contextVariable = PivotUtil.getOwnedContext(asExpressionInOCL);
		VariableDeclaration firstParameterVariable = PivotUtil.getOwnedParameter(asExpressionInOCL, 0);
		OperationCallExp orExp = (OperationCallExp) asExpressionInOCL.getOwnedBody();
		OperationCallExp eqExp = (OperationCallExp) PivotUtil.getOwnedSource(orExp);
		OperationCallExp gtExp = (OperationCallExp) PivotUtil.getOwnedArgument(orExp, 0);
		VariableExp eqSourceExp = (VariableExp) PivotUtil.getOwnedSource(eqExp);
		NullLiteralExp nullExp = (NullLiteralExp) PivotUtil.getOwnedArgument(eqExp, 0);
		VariableExp gtSourceExp = (VariableExp) PivotUtil.getOwnedSource(gtExp);
		NumericLiteralExp zeroExp = (NumericLiteralExp) PivotUtil.getOwnedArgument(gtExp, 0);

		// non-null known ok x
		SymbolicAnalysis symbolicAnalysis1a = ocl.getSymbolicAnalysis(asExpressionInOCL, null, null, new Object[]{5});
		checkContents(symbolicAnalysis1a, asExpressionInOCL, isDeads(eqExp, eqSourceExp, nullExp), mayBeNulls(contextVariable), null, null);
		BaseSymbolicEvaluationEnvironment symbolicEvaluationEnvironment1a = symbolicAnalysis1a.getBaseSymbolicEvaluationEnvironment();
		assertTrue(symbolicEvaluationEnvironment1a.isTrue(gtExp));

		// non-null known bad x
		SymbolicAnalysis symbolicAnalysis1b = ocl.getSymbolicAnalysis(asExpressionInOCL, null, null, new Object[]{-5});
		checkContents(symbolicAnalysis1b, asExpressionInOCL, null, mayBeNulls(contextVariable, nullExp), null, null);
		BaseSymbolicEvaluationEnvironment symbolicEvaluationEnvironment1b = symbolicAnalysis1b.getBaseSymbolicEvaluationEnvironment();
		assertTrue(symbolicEvaluationEnvironment1b.isFalse(gtExp));

		// null x
		SymbolicAnalysis symbolicAnalysis2 = ocl.getSymbolicAnalysis(asExpressionInOCL, null, null, new Object[]{null});
		checkContents(symbolicAnalysis2, asExpressionInOCL, isDeads(gtExp, gtSourceExp, zeroExp), mayBeNulls(contextVariable, firstParameterVariable, eqSourceExp, nullExp), null, null);
		BaseSymbolicEvaluationEnvironment symbolicEvaluationEnvironment2 = symbolicAnalysis2.getBaseSymbolicEvaluationEnvironment();
		assertTrue(symbolicEvaluationEnvironment2.isTrue(eqExp));
//		assertTrue(symbolicEvaluationEnvironment2.isTrue(asExpressionInOCL));

		// may-be-null x
		SymbolicVariableValue symbolicVariable = new SymbolicVariableValue(asExpressionInOCL.getOwnedParameters().get(0), true, false);
		SymbolicAnalysis symbolicAnalysis3 = ocl.getSymbolicAnalysis(asExpressionInOCL, null, null, new Object[]{symbolicVariable});
		checkContents(symbolicAnalysis3, asExpressionInOCL, null, mayBeNulls(contextVariable, firstParameterVariable, eqSourceExp, nullExp), null, null);

		// non-null unknown x
		SymbolicAnalysis symbolicAnalysis4 = ocl.getSymbolicAnalysis(asExpressionInOCL, null, null, new Object[]{new SymbolicUnknownValue("p0", TypeId.INTEGER, false, false)});
		checkContents(symbolicAnalysis4, asExpressionInOCL, null, mayBeNulls(contextVariable, nullExp), null, null);
		BaseSymbolicEvaluationEnvironment symbolicEvaluationEnvironment4 = symbolicAnalysis4.getBaseSymbolicEvaluationEnvironment();
		assertFalse(symbolicEvaluationEnvironment4.mayBeInvalidOrNull(gtExp));
//		assertFalse(symbolicEvaluationEnvironment4.mayBeInvalidOrNull(asExpressionInOCL));

		ocl.dispose();
	}

	public void testSymbolicAnalysis_Or2Guard() throws Exception {
		MyOCL ocl = new MyOCL("Or2Guard", "Or2Guard(x : Integer) : Boolean",
				"x = null or2 x > 0");
		ExpressionInOCL asExpressionInOCL = ocl.getExpressionInOCL();
		VariableDeclaration contextVariable = PivotUtil.getOwnedContext(asExpressionInOCL);
		VariableDeclaration firstParameterVariable = PivotUtil.getOwnedParameter(asExpressionInOCL, 0);
		OperationCallExp orExp = (OperationCallExp) asExpressionInOCL.getOwnedBody();
		OperationCallExp eqExp = (OperationCallExp) PivotUtil.getOwnedSource(orExp);
		OperationCallExp gtExp = (OperationCallExp) PivotUtil.getOwnedArgument(orExp, 0);
		VariableExp eqSourceExp = (VariableExp) PivotUtil.getOwnedSource(eqExp);
		NullLiteralExp nullExp = (NullLiteralExp) PivotUtil.getOwnedArgument(eqExp, 0);
		VariableExp gtSourceExp = (VariableExp) PivotUtil.getOwnedSource(gtExp);
		NumericLiteralExp zeroExp = (NumericLiteralExp) PivotUtil.getOwnedArgument(gtExp, 0);

		// non-null known ok x
		SymbolicAnalysis symbolicAnalysis1a = ocl.getSymbolicAnalysis(asExpressionInOCL, null, null, new Object[]{5});
		checkContents(symbolicAnalysis1a, asExpressionInOCL, isDeads(eqExp, eqSourceExp, nullExp), mayBeNulls(contextVariable), null, null);
		BaseSymbolicEvaluationEnvironment symbolicEvaluationEnvironment1a = symbolicAnalysis1a.getBaseSymbolicEvaluationEnvironment();
		assertTrue(symbolicEvaluationEnvironment1a.isTrue(gtExp));

		// non-null known bad x
		SymbolicAnalysis symbolicAnalysis1b = ocl.getSymbolicAnalysis(asExpressionInOCL, null, null, new Object[]{-5});
		checkContents(symbolicAnalysis1b, asExpressionInOCL, null, mayBeNulls(contextVariable, nullExp), null, null);
		BaseSymbolicEvaluationEnvironment symbolicEvaluationEnvironment1b = symbolicAnalysis1b.getBaseSymbolicEvaluationEnvironment();
		assertTrue(symbolicEvaluationEnvironment1b.isFalse(gtExp));

		// null x
		SymbolicAnalysis symbolicAnalysis2 = ocl.getSymbolicAnalysis(asExpressionInOCL, null, null, new Object[]{null});
		checkContents(symbolicAnalysis2, asExpressionInOCL, isDeads(gtExp, gtSourceExp, zeroExp), mayBeNulls(contextVariable, firstParameterVariable, eqSourceExp, nullExp), null, null);
		BaseSymbolicEvaluationEnvironment symbolicEvaluationEnvironment2 = symbolicAnalysis2.getBaseSymbolicEvaluationEnvironment();
		assertTrue(symbolicEvaluationEnvironment2.isTrue(eqExp));
//		assertTrue(symbolicEvaluationEnvironment2.isTrue(asExpressionInOCL));

		// may-be-null x
		SymbolicVariableValue symbolicVariable = new SymbolicVariableValue(asExpressionInOCL.getOwnedParameters().get(0), true, false);
		SymbolicAnalysis symbolicAnalysis3 = ocl.getSymbolicAnalysis(asExpressionInOCL, null, null, new Object[]{symbolicVariable});
		checkContents(symbolicAnalysis3, asExpressionInOCL, null, mayBeNulls(contextVariable, firstParameterVariable, eqSourceExp, nullExp), null, null);

		// non-null unknown x
		SymbolicAnalysis symbolicAnalysis4 = ocl.getSymbolicAnalysis(asExpressionInOCL, null, null, new Object[]{new SymbolicUnknownValue("p0", TypeId.INTEGER, false, false)});
		checkContents(symbolicAnalysis4, asExpressionInOCL, null, mayBeNulls(contextVariable, nullExp), null, null);
		BaseSymbolicEvaluationEnvironment symbolicEvaluationEnvironment4 = symbolicAnalysis4.getBaseSymbolicEvaluationEnvironment();
		assertFalse(symbolicEvaluationEnvironment4.mayBeInvalidOrNull(gtExp));
//		assertFalse(symbolicEvaluationEnvironment4.mayBeInvalidOrNull(asExpressionInOCL));

		ocl.dispose();
	}
}
