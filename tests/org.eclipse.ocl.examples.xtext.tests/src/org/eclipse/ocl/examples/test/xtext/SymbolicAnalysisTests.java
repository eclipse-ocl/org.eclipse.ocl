/*******************************************************************************
 * Copyright (c) 2010, 2019 Willink Transformations and others.
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
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.pivot.tests.TestOCL;
import org.eclipse.ocl.examples.xtext.tests.XtextTestCase;
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.IfExp;
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
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal.EnvironmentFactoryInternalExtension;
import org.eclipse.ocl.pivot.internal.values.SymbolicUnknownValueImpl;
import org.eclipse.ocl.pivot.internal.values.SymbolicVariableValueImpl;
import org.eclipse.ocl.pivot.resource.ASResource;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.OCL;
import org.eclipse.ocl.pivot.utilities.ParserException;
import org.eclipse.ocl.pivot.utilities.PivotConstants;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.utilities.TreeIterable;
import org.eclipse.ocl.pivot.utilities.UniqueList;
import org.eclipse.ocl.pivot.utilities.XMIUtil;
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
	public class MyOCL extends TestOCL
	{
		public MyOCL() {
			super(getTestFileSystem(), "FlowAnalysisTests", getName(), OCL.NO_PROJECTS, null);
		}

		protected @NonNull ExpressionInOCL createQueryTestModel(@NonNull String queryName, @NonNull String querySignature, @NonNull String queryBody) throws IOException, ParserException {
			String testContext =
					"package deductions : ded = 'http://deductions'\n" +
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
			createOCLinEcoreFile(fileName + ".oclinecore", testContext);
			Resource asResource = doLoad_Concrete(fileName, "oclinecore");
			Model model = PivotUtil.getModel(asResource);
			org.eclipse.ocl.pivot.Package deductionsPackage = ClassUtil.nonNullState(NameUtil.getNameable(model.getOwnedPackages(), "deductions"));
			org.eclipse.ocl.pivot.Class deductionsClass = ClassUtil.nonNullState(NameUtil.getNameable(deductionsPackage.getOwnedClasses(), "Deductions"));
			Operation asOperation = ClassUtil.nonNullState(NameUtil.getNameable(deductionsClass.getOwnedOperations(), queryName));
			return ((EnvironmentFactoryInternalExtension)environmentFactory).parseSpecification(asOperation.getBodyExpression());
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

		protected @NonNull SymbolicAnalysis getSymbolicAnalysis(@NonNull ExpressionInOCL asExpressionInOCL, @Nullable Object context, @Nullable Object @Nullable [] parameters) {
			MetamodelManagerInternal metamodelManager = getMetamodelManager();
			return ((MetamodelManagerInternal.MetamodelManagerInternalExtension2)metamodelManager).getSymbolicAnalysis(asExpressionInOCL, context, parameters);
		}
	}

	public void checkContents(@NonNull SymbolicAnalysis symbolicAnalysis, @NonNull ExpressionInOCL asExpressionInOCL,
			@Nullable EObject @Nullable[] expectedDeads, @Nullable EObject @Nullable[] expectedMayBeNulls,
			@Nullable EObject @Nullable[] expectedMayBeInvalids, @Nullable EObject @Nullable[] expectedIsInvalids) {
	//	Map<@NonNull Element, @NonNull SymbolicValue> element2symbolicValue = symbolicAnalysis.getElement2SymbolicValue();
		BaseSymbolicEvaluationEnvironment evaluationEnvironment = symbolicAnalysis.getBaseSymbolicEvaluationEnvironment();
		Set<@NonNull TypedElement> elements = new HashSet<>(); //>evaluationEnvironment.getElements();
		Set<@NonNull CSEElement> cseElements = evaluationEnvironment.getCSEElements();
		Set<@Nullable EObject> expectedDeadSet = expectedDeads != null ? new UniqueList<@Nullable EObject>(expectedDeads) : Collections.emptySet();
		int expectedContentCount = 0;
	//	List<@NonNull EObject> allContents = new ArrayList<>();
		Set<@NonNull CSEElement> cseElements2 = new HashSet<>();
		for (EObject eObject : new TreeIterable(asExpressionInOCL, true)) {
			if (expectedDeadSet.contains(eObject) || !(eObject instanceof TypedElement)) {
				continue;
			}
			TypedElement element = (TypedElement)eObject;
			if (cseElements2.add(symbolicAnalysis.getCSEElement(element))) {
				expectedContentCount++;
			}
			elements.add(element);
			assertTrue("Expected dead for " + debugText(eObject), evaluationEnvironment.basicGetSymbolicValue(element) != null);
		}
		assertEquals("Checking contents size for " + debugText(asExpressionInOCL), cseElements.size(), expectedContentCount); //allContents.size();
		Set<@Nullable EObject> expectedMayBeNullSet = expectedMayBeNulls != null ? new UniqueList<@Nullable EObject>(expectedMayBeNulls) : Collections.emptySet();
		Set<@Nullable EObject> expectedMayBeInvalidSet = expectedMayBeInvalids != null ? new UniqueList<@Nullable EObject>(expectedMayBeInvalids) : Collections.emptySet();
		Set<@Nullable EObject> expectedIsInvalidSet = expectedIsInvalids != null ? new UniqueList<@Nullable EObject>(expectedIsInvalids) : Collections.emptySet();
		StringBuilder s = new StringBuilder();
		for (@NonNull TypedElement element : elements) {
			SymbolicValue expectedValue = evaluationEnvironment.getSymbolicValue(element);
			assert expectedValue != null;
			boolean expectedMayBeNull = expectedMayBeNullSet.contains(element);
			boolean expectedIsInvalid = expectedIsInvalidSet.contains(element);
			boolean expectedMayBeInvalid = expectedIsInvalid || expectedMayBeInvalidSet.contains(element);
			boolean okMayBeInvalid = expectedMayBeInvalid == expectedValue.mayBeInvalid();
			boolean okMayBeNull = expectedMayBeNull == expectedValue.mayBeNull();
			boolean okIsInvalid = expectedIsInvalid == expectedValue.isInvalid();
			if (!okMayBeInvalid || !okMayBeNull || !okIsInvalid) {
				s.append("\n\t" + debugText(element));
				if (!okMayBeInvalid) {
					s.append("\n\t\t expected may-be-invalid = " + expectedMayBeInvalid);
				}
				if (!okMayBeNull) {
					s.append("\n\t\t expected may-be-null = " + expectedMayBeNull);
				}
				if (!okIsInvalid) {
					s.append("\n\t\t expected is-invalid = " + expectedIsInvalid);
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

	protected @Nullable EObject @Nullable [] isDeads(@Nullable EObject @Nullable ... elements) {
		if (elements == null) {
			return null;
		}
		int iMax = elements.length;
		@Nullable EObject [] results = new @Nullable EObject[iMax];
		for (int i = 0; i < iMax; i++) {
			results[i] = elements[i];
		}
		return results;
	}

	protected @Nullable EObject @Nullable [] isInvalids(@Nullable EObject @Nullable ... elements) {
		if (elements == null) {
			return null;
		}
		int iMax = elements.length;
		@Nullable EObject [] results = new @Nullable EObject[iMax];
		for (int i = 0; i < iMax; i++) {
			results[i] = elements[i];
		}
		return results;
	}

	protected @Nullable EObject @Nullable [] mayBeDeads(@Nullable EObject @Nullable ... elements) {
		if (elements == null) {
			return null;
		}
		int iMax = elements.length;
		@Nullable EObject [] results = new @Nullable EObject[iMax];
		for (int i = 0; i < iMax; i++) {
			results[i] = elements[i];
		}
		return results;
	}

	protected @Nullable EObject @Nullable [] mayBeInvalids(@Nullable EObject @Nullable ... elements) {
		if (elements == null) {
			return null;
		}
		int iMax = elements.length;
		@Nullable EObject [] results = new @Nullable EObject[iMax];
		for (int i = 0; i < iMax; i++) {
			results[i] = elements[i];
		}
		return results;
	}

	protected @Nullable EObject @Nullable [] mayBeNulls(@Nullable EObject @Nullable ... elements) {
		if (elements == null) {
			return null;
		}
		int iMax = elements.length;
		@Nullable EObject [] results = new @Nullable EObject[iMax];
		for (int i = 0; i < iMax; i++) {
			results[i] = elements[i];
		}
		return results;
	}

	public void testSymbolicAnalysis_BadSequenceIndex() throws Exception {
		MyOCL ocl = new MyOCL();
		ExpressionInOCL asExpressionInOCL = ocl.createQueryTestModel("BadSequenceIndex",
			"BadSequenceIndex(s : Sequence(Dummy)) : Dummy", "s->select(false)->at(4)");
	//	TypeId deductionsTypeId = asExpressionInOCL.getOwnedContext().getTypeId();
		TypeId seqTypeId = asExpressionInOCL.getOwnedParameters().get(0).getTypeId();
		VariableDeclaration contextVariable = PivotUtil.getOwnedContext(asExpressionInOCL);
		OperationCallExp atCallExp = (OperationCallExp) asExpressionInOCL.getOwnedBody();
		IteratorExp selectCallExp = (IteratorExp) atCallExp.getOwnedSource();
		IteratorVariable selectIterator = (IteratorVariable) selectCallExp.getOwnedIterators().get(0);
	//	LiteralExp selectBodyExp = (LiteralExp) selectCallExp.getOwnedBody();
	//	VariableExp asSourceExp = (VariableExp) PivotUtil.getOwnedSource(asPropertyCallExp);

		// safe non-null navigation
		SymbolicAnalysis symbolicAnalysis1 = ocl.getSymbolicAnalysis(asExpressionInOCL, null, new Object[]{new SymbolicUnknownValueImpl(seqTypeId, false, false)});
		checkContents(symbolicAnalysis1, asExpressionInOCL, null, mayBeNulls(contextVariable), null, null);

/*		// safe null navigation
		SymbolicEvaluationVisitor symbolicAnalysis2 = ocl.getSymbolicAnalysis(asExpressionInOCL, null, new Object[]{null});
		checkContents(symbolicAnalysis2, asExpressionInOCL, null, mayBeNulls(asSourceExp), null, null);

		// safe maybe-null navigation
		SymbolicEvaluationVisitor symbolicAnalysis3 = ocl.getSymbolicAnalysis(asExpressionInOCL, null, new Object[]{new SymbolicUnknownValueImpl(deductionsTypeId, true, false), 0.0});
		checkContents(symbolicAnalysis3, asExpressionInOCL, null, mayBeNulls(asSourceExp), mayBeInvalids(asExpressionInOCL, asPropertyCallExp), null);

		// safe maybe-invalid navigation
		SymbolicEvaluationVisitor symbolicAnalysis4 = ocl.getSymbolicAnalysis(asExpressionInOCL, null, new Object[]{new SymbolicUnknownValueImpl(deductionsTypeId, false, true), 0.0});
		checkContents(symbolicAnalysis4, asExpressionInOCL, null, null, mayBeInvalids(asExpressionInOCL, asPropertyCallExp, asSourceExp), null);
*/
		ocl.dispose();
	}

	public void testSymbolicAnalysis_BadDivide() throws Exception {
		MyOCL ocl = new MyOCL();
		ExpressionInOCL asExpressionInOCL = ocl.createQueryTestModel("BadDivide",
			"BadDivide(num : Real, den : Real) : Real", "num / den");
		OperationCallExp asOperationCallExp = (OperationCallExp) asExpressionInOCL.getOwnedBody();
		VariableDeclaration contextVariable = PivotUtil.getOwnedContext(asExpressionInOCL);
	//	VariableExp asNumExp = (VariableExp) PivotUtil.getOwnedSource(asOperationCallExp);
	//	VariableExp asDenExp = (VariableExp) PivotUtil.getOwnedArgument(asOperationCallExp, 0);

		// 5.0 / 0.0
		SymbolicAnalysis symbolicAnalysis1 = ocl.getSymbolicAnalysis(asExpressionInOCL, null, new Object[]{5.0, 0.0});
		checkContents(symbolicAnalysis1, asExpressionInOCL, null, mayBeNulls(contextVariable), null, isInvalids(asExpressionInOCL, asOperationCallExp));

		// 5.0 / 1.0
		SymbolicAnalysis symbolicAnalysis2 = ocl.getSymbolicAnalysis(asExpressionInOCL, null, new Object[]{5.0, 1.0});
		checkContents(symbolicAnalysis2, asExpressionInOCL, null, mayBeNulls(contextVariable), null, null);

		// not-null / 0.0
		SymbolicAnalysis symbolicAnalysis3 = ocl.getSymbolicAnalysis(asExpressionInOCL, null, new Object[]{new SymbolicUnknownValueImpl(TypeId.REAL, false, false), 0.0});
		checkContents(symbolicAnalysis3, asExpressionInOCL, null, mayBeNulls(contextVariable), null, isInvalids(asExpressionInOCL, asOperationCallExp));

		// not-null / 1.0
		SymbolicAnalysis symbolicAnalysis4 = ocl.getSymbolicAnalysis(asExpressionInOCL, null, new Object[]{new SymbolicUnknownValueImpl(TypeId.REAL, false, false), 1.0});
		checkContents(symbolicAnalysis4, asExpressionInOCL, null, mayBeNulls(contextVariable), null, null);

		// not-null / maybe-zero
		SymbolicAnalysis symbolicAnalysis5 = ocl.getSymbolicAnalysis(asExpressionInOCL, null, new Object[]{new SymbolicUnknownValueImpl(TypeId.REAL, false, false), new SymbolicUnknownValueImpl(TypeId.REAL, false, false)});
		checkContents(symbolicAnalysis5, asExpressionInOCL, null, mayBeNulls(contextVariable), mayBeInvalids(asExpressionInOCL, asOperationCallExp), null);

		ocl.dispose();
	}

	public void testSymbolicAnalysis_GuardedBadDivide() throws Exception {
		MyOCL ocl = new MyOCL();
		ExpressionInOCL asExpressionInOCL = ocl.createQueryTestModel("GuardedBadDivide",
			"GuardedBadDivide(num : Real, den : Real) : Real", "if den <> 0 then num / den else 999.999 endif");
		IfExp asIfExp = (IfExp) asExpressionInOCL.getOwnedBody();
		OperationCallExp asOperationCallExp = (OperationCallExp) PivotUtil.getOwnedThen(asIfExp);
		VariableDeclaration contextVariable = PivotUtil.getOwnedContext(asExpressionInOCL);
		VariableExp asNumExp = (VariableExp) PivotUtil.getOwnedSource(asOperationCallExp);
		VariableExp asDenExp = (VariableExp) PivotUtil.getOwnedArgument(asOperationCallExp, 0);
		LiteralExp asLiteralExp = (LiteralExp) PivotUtil.getOwnedElse(asIfExp);

		// 5.0 / not-null-maybe-zero
		SymbolicAnalysis symbolicAnalysis5a = ocl.getSymbolicAnalysis(asExpressionInOCL, null, new Object[]{5.0, new SymbolicUnknownValueImpl(TypeId.REAL, false, false)});
		checkContents(symbolicAnalysis5a, asExpressionInOCL, null, mayBeNulls(contextVariable), null, null);

		// 5.0 / 0.0
		SymbolicAnalysis symbolicAnalysis1 = ocl.getSymbolicAnalysis(asExpressionInOCL, null, new Object[]{5.0, 0.0});
		checkContents(symbolicAnalysis1, asExpressionInOCL, mayBeDeads(asOperationCallExp, asNumExp, asDenExp), mayBeNulls(contextVariable), null, null);

		// 5.0 / 1.0
		SymbolicAnalysis symbolicAnalysis2 = ocl.getSymbolicAnalysis(asExpressionInOCL, null, new Object[]{5.0, 1.0});
		checkContents(symbolicAnalysis2, asExpressionInOCL, mayBeDeads(asLiteralExp), mayBeNulls(contextVariable), null, null);

		// not-null / 0.0
		SymbolicAnalysis symbolicAnalysis3 = ocl.getSymbolicAnalysis(asExpressionInOCL, null, new Object[]{new SymbolicUnknownValueImpl(TypeId.REAL, false, false), 0.0});
		checkContents(symbolicAnalysis3, asExpressionInOCL, mayBeDeads(asOperationCallExp, asNumExp, asDenExp), mayBeNulls(contextVariable), null, null);

		// not-null / 1.0
		SymbolicAnalysis symbolicAnalysis4 = ocl.getSymbolicAnalysis(asExpressionInOCL, null, new Object[]{new SymbolicUnknownValueImpl(TypeId.REAL, false, false), 1.0});
		checkContents(symbolicAnalysis4, asExpressionInOCL, mayBeDeads(asLiteralExp), mayBeNulls(contextVariable), null, null);

		// 5.0 / not-null-maybe-zero
		SymbolicAnalysis symbolicAnalysis5 = ocl.getSymbolicAnalysis(asExpressionInOCL, null, new Object[]{5.0, new SymbolicUnknownValueImpl(TypeId.REAL, false, false)});
		checkContents(symbolicAnalysis5, asExpressionInOCL, null, mayBeNulls(contextVariable), mayBeInvalids(asExpressionInOCL, asIfExp, asOperationCallExp), null);

		// 5 / not-null-maybe-zero
		SymbolicAnalysis symbolicAnalysis6 = ocl.getSymbolicAnalysis(asExpressionInOCL, null, new Object[]{5, new SymbolicUnknownValueImpl(TypeId.INTEGER, false, false)});
		checkContents(symbolicAnalysis6, asExpressionInOCL, null, mayBeNulls(contextVariable), mayBeInvalids(asExpressionInOCL, asIfExp, asOperationCallExp), null);

		ocl.dispose();
	}

	public void testSymbolicAnalysis_BadSafeNavigation() throws Exception {
		MyOCL ocl = new MyOCL();
		ExpressionInOCL asExpressionInOCL = ocl.createQueryTestModel("BadSafeNavigation",
			"BadSafeNavigation(x : Deductions[?]) : Dummy", "x?.nonNullDummy");
		VariableDeclaration contextVariable = PivotUtil.getOwnedContext(asExpressionInOCL);
		VariableDeclaration firstParameterVariable = PivotUtil.getOwnedParameter(asExpressionInOCL, 0);
		TypeId deductionsTypeId = asExpressionInOCL.getOwnedContext().getTypeId();
		PropertyCallExp asPropertyCallExp = (PropertyCallExp) asExpressionInOCL.getOwnedBody();
		VariableExp asSourceExp = (VariableExp) PivotUtil.getOwnedSource(asPropertyCallExp);

		// safe non-null navigation
		SymbolicAnalysis symbolicAnalysis1 = ocl.getSymbolicAnalysis(asExpressionInOCL, null, new Object[]{new SymbolicUnknownValueImpl(deductionsTypeId, false, false)});
		checkContents(symbolicAnalysis1, asExpressionInOCL, null, new EObject[]{contextVariable}, null, null);

		// safe null navigation
		SymbolicAnalysis symbolicAnalysis2 = ocl.getSymbolicAnalysis(asExpressionInOCL, null, new Object[]{null});
		checkContents(symbolicAnalysis2, asExpressionInOCL, null, mayBeNulls(asExpressionInOCL, contextVariable, firstParameterVariable, asPropertyCallExp, asSourceExp), null, null);

		// safe maybe-null navigation
		SymbolicAnalysis symbolicAnalysis3 = ocl.getSymbolicAnalysis(asExpressionInOCL, null, new Object[]{new SymbolicUnknownValueImpl(deductionsTypeId, true, false)});
		checkContents(symbolicAnalysis3, asExpressionInOCL, null, mayBeNulls(asExpressionInOCL, contextVariable, firstParameterVariable,
			asPropertyCallExp, asSourceExp), null, null);

		// safe maybe-invalid navigation
		SymbolicAnalysis symbolicAnalysis4 = ocl.getSymbolicAnalysis(asExpressionInOCL, null, new Object[]{new SymbolicUnknownValueImpl(deductionsTypeId, false, true)});
		checkContents(symbolicAnalysis4, asExpressionInOCL, null, mayBeNulls(contextVariable), mayBeInvalids(asExpressionInOCL, firstParameterVariable, asPropertyCallExp, asSourceExp), null);

		ocl.dispose();
	}

	public void testSymbolicAnalysis_BadSafeOppositeNavigation() throws Exception {
		MyOCL ocl = new MyOCL();
		ExpressionInOCL asExpressionInOCL = ocl.createQueryTestModel("BadSafeNavigation",
			"BadSafeNavigation(x : Deductions[?]) : Bag(Dummy)", "x?.Dummy[Dummy::mayBeNullDeductions]");		// Dummy is a non-null multi-valued opposite
		VariableDeclaration contextVariable = PivotUtil.getOwnedContext(asExpressionInOCL);
		VariableDeclaration firstParameterVariable = PivotUtil.getOwnedParameter(asExpressionInOCL, 0);
		TypeId deductionsTypeId = asExpressionInOCL.getOwnedContext().getTypeId();
		OppositePropertyCallExp asOppositePropertyCallExp = (OppositePropertyCallExp) asExpressionInOCL.getOwnedBody();
		VariableExp asSourceExp = (VariableExp) PivotUtil.getOwnedSource(asOppositePropertyCallExp);

		// safe non-null navigation
		SymbolicAnalysis symbolicAnalysis1 = ocl.getSymbolicAnalysis(asExpressionInOCL, null, new Object[]{new SymbolicUnknownValueImpl(deductionsTypeId, false, false)});
		checkContents(symbolicAnalysis1, asExpressionInOCL, null, mayBeNulls(contextVariable), null, null);

		// safe null navigation
		SymbolicAnalysis symbolicAnalysis2 = ocl.getSymbolicAnalysis(asExpressionInOCL, null, new Object[]{null});
		checkContents(symbolicAnalysis2, asExpressionInOCL, null, mayBeNulls(asExpressionInOCL, contextVariable, firstParameterVariable, asOppositePropertyCallExp, asSourceExp), null, null);

		// safe maybe-null navigation
		SymbolicAnalysis symbolicAnalysis3 = ocl.getSymbolicAnalysis(asExpressionInOCL, null, new Object[]{new SymbolicUnknownValueImpl(deductionsTypeId, true, false)});
		checkContents(symbolicAnalysis3, asExpressionInOCL, null, mayBeNulls(contextVariable, firstParameterVariable, asSourceExp), null, null);

		// safe maybe-invalid navigation
		SymbolicAnalysis symbolicAnalysis4 = ocl.getSymbolicAnalysis(asExpressionInOCL, null, new Object[]{new SymbolicUnknownValueImpl(deductionsTypeId, false, true)});
		checkContents(symbolicAnalysis4, asExpressionInOCL, null, mayBeNulls(contextVariable), mayBeInvalids(asExpressionInOCL, firstParameterVariable, asSourceExp, asOppositePropertyCallExp), null);

		ocl.dispose();
	}

	public void testSymbolicAnalysis_BadUnsafeRequiredNavigation() throws Exception {
		MyOCL ocl = new MyOCL();
		ExpressionInOCL asExpressionInOCL = ocl.createQueryTestModel("BadUnsafeRequiredNavigation",
			"BadUnsafeRequiredNavigation(x : Deductions[?]) : Dummy", "x.nonNullDummy");
		VariableDeclaration contextVariable = PivotUtil.getOwnedContext(asExpressionInOCL);
		VariableDeclaration firstParameterVariable = PivotUtil.getOwnedParameter(asExpressionInOCL, 0);
		TypeId deductionsTypeId = asExpressionInOCL.getOwnedContext().getTypeId();
		PropertyCallExp asPropertyCallExp = (PropertyCallExp) asExpressionInOCL.getOwnedBody();
		VariableExp asSourceExp = (VariableExp) PivotUtil.getOwnedSource(asPropertyCallExp);

		// unsafe non-null navigation
		SymbolicAnalysis symbolicAnalysis1 = ocl.getSymbolicAnalysis(asExpressionInOCL, null, new Object[]{new SymbolicUnknownValueImpl(deductionsTypeId, false, false)});
		checkContents(symbolicAnalysis1, asExpressionInOCL, null, mayBeNulls(contextVariable), null, null);

		// unsafe null navigation
		SymbolicAnalysis symbolicAnalysis2 = ocl.getSymbolicAnalysis(asExpressionInOCL, null, new Object[]{null});
		checkContents(symbolicAnalysis2, asExpressionInOCL, null, mayBeNulls(contextVariable, firstParameterVariable, asSourceExp), null, isInvalids(asExpressionInOCL, asPropertyCallExp));

		// unsafe maybe-null navigation
		SymbolicAnalysis symbolicAnalysis3 = ocl.getSymbolicAnalysis(asExpressionInOCL, null, new Object[]{new SymbolicUnknownValueImpl(deductionsTypeId, true, false)});
		checkContents(symbolicAnalysis3, asExpressionInOCL, null, mayBeNulls(contextVariable, firstParameterVariable, asSourceExp), mayBeInvalids(asExpressionInOCL, asPropertyCallExp), null);

		// unsafe maybe-invalid navigation
		SymbolicAnalysis symbolicAnalysis4 = ocl.getSymbolicAnalysis(asExpressionInOCL, null, new Object[]{new SymbolicUnknownValueImpl(deductionsTypeId, false, true)});
		checkContents(symbolicAnalysis4, asExpressionInOCL, null, mayBeNulls(contextVariable), mayBeInvalids(asExpressionInOCL, firstParameterVariable, asPropertyCallExp, asSourceExp), null);

		ocl.dispose();
	}

	public void testSymbolicAnalysis_BadUnsafeOptionalNavigation() throws Exception {
		MyOCL ocl = new MyOCL();
		ExpressionInOCL asExpressionInOCL = ocl.createQueryTestModel("BadUnsafeOptionalNavigation",
			"BadUnsafeOptionalNavigation(x : Deductions[?]) : Dummy", "x.mayBeNullDummy");
		VariableDeclaration contextVariable = PivotUtil.getOwnedContext(asExpressionInOCL);
		VariableDeclaration firstParameterVariable = PivotUtil.getOwnedParameter(asExpressionInOCL, 0);
		TypeId deductionsTypeId = asExpressionInOCL.getOwnedContext().getTypeId();
		PropertyCallExp asPropertyCallExp = (PropertyCallExp) asExpressionInOCL.getOwnedBody();
		VariableExp asSourceExp = (VariableExp) PivotUtil.getOwnedSource(asPropertyCallExp);

		// unsafe non-null navigation
		SymbolicAnalysis symbolicAnalysis1 = ocl.getSymbolicAnalysis(asExpressionInOCL, null, new Object[]{new SymbolicUnknownValueImpl(deductionsTypeId, false, false)});
		checkContents(symbolicAnalysis1, asExpressionInOCL, null, mayBeNulls(asExpressionInOCL, contextVariable, asPropertyCallExp), null, null);

		// unsafe null navigation
		SymbolicAnalysis symbolicAnalysis2 = ocl.getSymbolicAnalysis(asExpressionInOCL, null, new Object[]{null});
		checkContents(symbolicAnalysis2, asExpressionInOCL, null, mayBeNulls(contextVariable, firstParameterVariable, asSourceExp), null, isInvalids(asExpressionInOCL, asPropertyCallExp));

		// unsafe maybe-null navigation
		SymbolicAnalysis symbolicAnalysis3 = ocl.getSymbolicAnalysis(asExpressionInOCL, null, new Object[]{new SymbolicUnknownValueImpl(deductionsTypeId, true, false)});
		checkContents(symbolicAnalysis3, asExpressionInOCL, null, mayBeNulls(asExpressionInOCL, contextVariable, firstParameterVariable, asPropertyCallExp, asSourceExp), mayBeInvalids(asExpressionInOCL, asPropertyCallExp), null);

		// unsafe maybe-invalid navigation
		SymbolicAnalysis symbolicAnalysis4 = ocl.getSymbolicAnalysis(asExpressionInOCL, null, new Object[]{new SymbolicUnknownValueImpl(deductionsTypeId, false, true)});
		checkContents(symbolicAnalysis4, asExpressionInOCL, null, mayBeNulls(asExpressionInOCL, contextVariable, asPropertyCallExp), mayBeInvalids(asExpressionInOCL, firstParameterVariable, asPropertyCallExp, asSourceExp), null);

		ocl.dispose();
	}

	public void testSymbolicAnalysis_ImpliesGuard() throws Exception {
		MyOCL ocl = new MyOCL();
		ExpressionInOCL asExpressionInOCL = ocl.createQueryTestModel("ImpliesGuard", "ImpliesGuard(x : Integer) : Boolean",
				"x <> null implies2 x > 0");
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
		SymbolicVariableValueImpl symbolicVariable = new SymbolicVariableValueImpl(asExpressionInOCL.getOwnedParameters().get(0), true, false);
		SymbolicAnalysis symbolicAnalysis3 = ocl.getSymbolicAnalysis(asExpressionInOCL, null, new Object[]{symbolicVariable});
		checkContents(symbolicAnalysis3, asExpressionInOCL, null, mayBeNulls(contextVariable, firstParameterVariable, neSourceExp, nullExp), null, null);

		// non-null unknown x
		SymbolicAnalysis symbolicAnalysis1a = ocl.getSymbolicAnalysis(asExpressionInOCL, null, new Object[]{new SymbolicUnknownValueImpl(TypeId.INTEGER, false, false)});
		checkContents(symbolicAnalysis1a, asExpressionInOCL, null, mayBeNulls(contextVariable, nullExp), null, null);
		BaseSymbolicEvaluationEnvironment symbolicEvaluationEnvironment1a = symbolicAnalysis1a.getBaseSymbolicEvaluationEnvironment();
		assertTrue(symbolicEvaluationEnvironment1a.isTrue(notEqExp));
		assertFalse(symbolicEvaluationEnvironment1a.mayBeInvalidOrNull(gtExp));
//		assertFalse(symbolicEvaluationEnvironment1a.mayBeInvalidOrNull(asExpressionInOCL));

		// non-null known x
		SymbolicAnalysis symbolicAnalysis1 = ocl.getSymbolicAnalysis(asExpressionInOCL, null, new Object[]{5});
		checkContents(symbolicAnalysis1, asExpressionInOCL, null, mayBeNulls(contextVariable, nullExp), null, null);
		BaseSymbolicEvaluationEnvironment symbolicEvaluationEnvironment1 = symbolicAnalysis1.getBaseSymbolicEvaluationEnvironment();
		assertTrue(symbolicEvaluationEnvironment1.isTrue(notEqExp));
		assertTrue(symbolicEvaluationEnvironment1.isTrue(gtExp));
//		assertTrue(symbolicEvaluationEnvironment1.isTrue(asExpressionInOCL));

		// null x
		SymbolicAnalysis symbolicAnalysis2 = ocl.getSymbolicAnalysis(asExpressionInOCL, null, new Object[]{null});
		checkContents(symbolicAnalysis2, asExpressionInOCL, isDeads(gtExp, gtSourceExp, zeroExp), mayBeNulls(contextVariable, firstParameterVariable, neSourceExp, nullExp), null, null);
		BaseSymbolicEvaluationEnvironment symbolicEvaluationEnvironment2 = symbolicAnalysis2.getBaseSymbolicEvaluationEnvironment();
		assertTrue(symbolicEvaluationEnvironment2.isFalse(notEqExp));
//		assertTrue(symbolicEvaluationEnvironment2.isTrue(asExpressionInOCL));

		ocl.dispose();
	}

	public void testSymbolicAnalysis_IndirectAndGuard() throws Exception {
		MyOCL ocl = new MyOCL();
		ExpressionInOCL asExpressionInOCL = ocl.createQueryTestModel("IndirectAndGuard", "IndirectAndGuard(x : Deductions[1]) : Boolean",
				"x?.mayBeNullDummy <> null and x?.mayBeNullDummy.dummy = null");
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

		// non-null self
		SymbolicAnalysis symbolicAnalysis1 = ocl.getSymbolicAnalysis(asExpressionInOCL, null, new Object[]{null});
		checkContents(symbolicAnalysis1, asExpressionInOCL, isDeads(eqExp, eqPropCallExp, eqPropCallPropCallExp, eqSourceExp, eqNullExp), mayBeNulls(contextVariable, firstParameterVariable, neNullExp, notEqSourceExp, eqSourceExp, eqNullExp, eqPropCallExp, notEqPropCallExp), null, null);
	//	assertTrue(symbolicAnalysis1.isTrue(notEqExp));
	//	assertTrue(symbolicAnalysis1.isTrue(eqExp));
	//	assertTrue(symbolicAnalysis1.isTrue(asExpressionInOCL));

	/*	// null self
		SymbolicEvaluationVisitor symbolicAnalysis2 = ocl.getSymbolicAnalysis(asExpressionInOCL, null, new Object[]{null});
		checkContents(symbolicAnalysis2, asExpressionInOCL, isDeads(gtExp, gtSourceExp, zeroExp), mayBeNulls(neSourceExp, nullExp}, null, null);
		assertTrue(symbolicAnalysis2.isFalse(notEqExp));
		assertTrue(symbolicAnalysis2.isTrue(asExpressionInOCL));

		// may-be-null self
		SymbolicVariableValueImpl symbolicVariable = new SymbolicVariableValueImpl(asExpressionInOCL.getOwnedParameters().get(0), true, false);
		SymbolicEvaluationVisitor symbolicAnalysis3 = ocl.getSymbolicAnalysis(asExpressionInOCL, null, new Object[]{symbolicVariable});
		checkContents(symbolicAnalysis3, asExpressionInOCL, null, mayBeNulls(neSourceExp, nullExp}, null, null);
*/
		ocl.dispose();
	}

	public void testSymbolicAnalysis_SimpleIfNameGuard() throws Exception {
		MyOCL ocl = new MyOCL();
		ExpressionInOCL asExpressionInOCL = ocl.createQueryTestModel("SimpleIfNameGuard", "SimpleIfNameGuard() : Boolean",
				"(let v : String[?] = self?.toString() in if v = null then 'null' else v endif) <> null");
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

		SymbolicAnalysis symbolicAnalysis1 = ocl.getSymbolicAnalysis(asExpressionInOCL, null, new Object[0]);
		checkContents(symbolicAnalysis1, asExpressionInOCL, isDeads(asElseVariableExp), mayBeNulls(contextVariable, selfExp, asLetVariableInit, asLetVariable, asConditionVariableExp, neNullExp, asConditionNullExp), null, null); //new EObject[] {asExpressionInOCL, neOperationCallExp});
		assertTrue("Result",  ocl.getIdResolver().oclEquals(symbolicAnalysis1.getBaseSymbolicEvaluationEnvironment().getSymbolicValue2(asIf), "null"));

		SymbolicAnalysis symbolicAnalysis2 = ocl.getSymbolicAnalysis(asExpressionInOCL, new SymbolicUnknownValueImpl(TypeId.STRING, false, false), new Object[0]);
		checkContents(symbolicAnalysis2, asExpressionInOCL, isDeads(asThenLiteralExp), mayBeNulls(neNullExp, asConditionNullExp), null, null);

		ocl.dispose();
	}
}
