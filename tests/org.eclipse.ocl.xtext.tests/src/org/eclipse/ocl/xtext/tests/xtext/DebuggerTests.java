/*******************************************************************************
 * Copyright (c) 2011, 2025 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.xtext.tests.xtext;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfigurationType;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.debug.core.model.IStackFrame;
import org.eclipse.debug.core.model.IThread;
import org.eclipse.debug.core.model.IVariable;
import org.eclipse.debug.internal.ui.sourcelookup.SourceLookupFacility;
import org.eclipse.debug.internal.ui.viewers.model.TreeModelContentProvider;
import org.eclipse.debug.internal.ui.views.variables.VariablesView;
import org.eclipse.debug.ui.AbstractDebugView;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.ocl.debug.core.OCLDebugTarget;
import org.eclipse.ocl.debug.evaluator.OCLVMRootEvaluationEnvironment;
import org.eclipse.ocl.debug.evaluator.OCLVMVirtualMachine;
import org.eclipse.ocl.debug.launching.OCLLaunchConstants;
import org.eclipse.ocl.debug.vm.VMVirtualMachine;
import org.eclipse.ocl.debug.vm.core.VMVariable;
import org.eclipse.ocl.pivot.Constraint;
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.PivotPackage;
import org.eclipse.ocl.pivot.VariableDeclaration;
import org.eclipse.ocl.pivot.internal.ecore.annotations.EAnnotationConverter;
import org.eclipse.ocl.pivot.internal.resource.ProjectMap;
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal;
import org.eclipse.ocl.pivot.internal.utilities.OCLInternal;
import org.eclipse.ocl.pivot.utilities.AbstractEnvironmentFactory;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.PivotConstants;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.utilities.ThreadLocalExecutor;
import org.eclipse.ocl.xtext.base.ui.model.BaseEditorCallback;
import org.eclipse.ocl.xtext.completeocl.ui.internal.CompleteOCLActivator;
import org.eclipse.ocl.xtext.tests.TestFile;
import org.eclipse.ocl.xtext.tests.TestProject;
import org.eclipse.ocl.xtext.tests.TestUIUtil;
import org.eclipse.ocl.xtext.tests.XtextTestCase;
import org.eclipse.ui.IPartService;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IViewReference;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

import com.google.inject.Injector;

/**
 * Tests that load a model and verify that there are no unresolved proxies as a result.
 */
public class DebuggerTests extends XtextTestCase
{

	private void checkEquals(String string, Object left, Object right) {
		if (!left.equals(right)) {
			System.out.println(string + " mismatch for " + left + " " + right);
		}
	}

	private void checkPosition(@NonNull IThread vmThread, int lineNumber, int charStart, int charEnd) throws DebugException {
		IStackFrame topStackFrame = vmThread.getTopStackFrame();
		checkEquals("lineNumber", lineNumber, topStackFrame.getLineNumber());
		checkEquals("charStart", charStart, topStackFrame.getCharStart());
		checkEquals("charEnd", charEnd, topStackFrame.getCharEnd());
	}

	private void checkVariable(@NonNull IThread vmThread, @NonNull String name, @Nullable Object expectedValue) throws DebugException {
		IStackFrame topStackFrame = vmThread.getTopStackFrame();
		IVariable[] variables = topStackFrame.getVariables();
		if (variables != null){
			for (IVariable variable : variables) {
				if (name.equals(variable.getName()) && (variable instanceof VMVariable)) {
					Object valueObject = ((VMVariable)variable).getVmVar().valueObject;
					checkEquals(name, expectedValue, valueObject);
					return;
				}
			}
		}
		//		fail("Unknown variable '" + name + "'");
		System.out.println("Unknown variable '" + name + "'");
	}

	private void checkVariableEClass(@NonNull IThread vmThread, @NonNull String name, /*@NonNull*/ EClass expectedEClass) throws DebugException {
		IStackFrame topStackFrame = vmThread.getTopStackFrame();
		IVariable[] variables = topStackFrame.getVariables();
		if (variables != null){
			for (IVariable variable : variables) {
				if (name.equals(variable.getName()) && (variable instanceof VMVariable)) {
					Object valueObject = ((VMVariable)variable).getVmVar().valueObject;
					EClass valueEClass = ((EObject)valueObject).eClass();
					checkEquals(name, expectedEClass, valueEClass);
					return;
				}
			}
		}
		//		fail("Unknown variable '" + name + "'");
		System.out.println("Unknown variable '" + name + "'");
	}

	private void checkVariables(@NonNull IThread vmThread, String... names) throws DebugException {
		List<String> expectedNames = new ArrayList<String>();
		if (names != null){
			for (String name : names) {
				expectedNames.add(name);
			}
		}
		Collections.sort(expectedNames);
		IStackFrame topStackFrame = vmThread.getTopStackFrame();
		IVariable[] variables = topStackFrame.getVariables();
		List<String> actualNames = new ArrayList<String>();
		if (variables != null){
			for (IVariable variable : variables) {
				actualNames.add(variable.getName());
			}
		}
		Collections.sort(actualNames);
		checkEquals("variables", expectedNames, actualNames);
	}

	public static @NonNull TestFile copyFile(@NonNull TestProject testProject, @NonNull URIConverter uriConverter, @NonNull URI sourceURI) throws IOException {
		InputStream inputStream = uriConverter.createInputStream(sourceURI);
		String lastSegment = sourceURI.lastSegment();
		assert lastSegment != null;
		return testProject.getOutputFile(lastSegment, inputStream);
	}

	protected ILaunchConfigurationWorkingCopy createLaunchConfiguration(@NonNull IProject iProject,
			@NonNull Constraint constraint, @NonNull EObject eObject) throws CoreException {
		URI contextURI = EcoreUtil.getURI(eObject);
		URI constraintURI = EcoreUtil.getURI(constraint);
		ILaunchManager launchManager = DebugPlugin.getDefault().getLaunchManager();
		ILaunchConfigurationType launchConfigurationType = launchManager.getLaunchConfigurationType(OCLLaunchConstants.LAUNCH_CONFIGURATION_TYPE_ID);
		ILaunchConfigurationWorkingCopy launchConfiguration = launchConfigurationType.newInstance(iProject, constraint.getName());
		launchConfiguration.setAttribute(OCLLaunchConstants.CONSTRAINT_URI, constraintURI.toString());
		launchConfiguration.setAttribute(OCLLaunchConstants.CONTEXT_URI, contextURI.toString());
		return launchConfiguration;
	}

	/**
	 * Return the URI of a file in the test harness models folder.
	 */
	protected @NonNull URI getModelsURI(@NonNull String filePath) {
		return URI.createPlatformResourceURI(getTestBundleName() + "/models/" + filePath, true);
	}

	@Override
	protected void tearDown() throws Exception {
		TestUIUtil.cancelAndWaitForValidationJob();
		gc(ThreadLocalExecutor.getBracketedThreadName() + " pre-tearDown " + NameUtil.debugSimpleName(this));
		super.tearDown();
	//	TestUIUtil.wait(1000);
		gc(ThreadLocalExecutor.getBracketedThreadName() + " post-tearDown " + NameUtil.debugSimpleName(this));
		AbstractEnvironmentFactory.diagnoseLiveEnvironmentFactories();
	}

	public void testDebugger_Launch() throws Exception {
		EAnnotationConverter.addDefaultEAnnotationConverter("http://www.eclipse.org/OCL/1.0.0/body");
		EAnnotationConverter.addDefaultEAnnotationConverter("http://www.eclipse.org/OCL/1.0.0/derive");
		EAnnotationConverter.addDefaultEAnnotationConverter("http://www.eclipse.org/OCL/1.0.0/init");
		EAnnotationConverter.addDefaultEAnnotationConverter("http://www.eclipse.org/OCL/1.0.0/post");
		EAnnotationConverter.addDefaultEAnnotationConverter("http://www.eclipse.org/OCL/1.0.0/pre");
		TestUIUtil.closeIntro();
		TestUIUtil.enableSwitchToDebugPerspectivePreference();
		//
		IWorkbench workbench = PlatformUI.getWorkbench();
		IWorkbenchWindow initialWorkbenchWindow = workbench.getActiveWorkbenchWindow();
		assert initialWorkbenchWindow != null;
		IWorkbenchPage initialPage = initialWorkbenchWindow.getActivePage();
		assert initialPage != null;
		IPartService initialPartService = initialWorkbenchWindow.getPartService();
		assert initialPartService != null;
		IWorkbenchPart initialPart = initialPartService.getActivePart();
		assert initialPart != null;
		//
		Injector injector = CompleteOCLActivator.getInstance().getInjector(CompleteOCLActivator.ORG_ECLIPSE_OCL_XTEXT_COMPLETEOCL_COMPLETEOCL);
		injector.getInstance(BaseEditorCallback.class).setDontAskForNatureAgain();
		//
		TestProject testProject = getTestProject();
		OCLInternal ocl = OCLInternal.newInstance(new ProjectMap(false), null);
		URIConverter uriConverter = ocl.getResourceSet().getURIConverter();
		TestFile xmiFile = copyFile(testProject, uriConverter, getTestModelURI("models/ecore/RoyalAndLoyal.xmi"));
		@SuppressWarnings("unused")TestFile ecoreFile = copyFile(testProject, uriConverter, getTestModelURI("models/ecore/RoyalAndLoyal.ecore"));
		TestFile oclFile = copyFile(testProject, uriConverter, getTestModelURI("models/ecore/RoyalAndLoyal.ocl"));
		//
		Resource xmiResource = ocl.getResourceSet().getResource(xmiFile.getURI(), true);
		EObject xmiRoot = ClassUtil.requireNonNull(xmiResource.getContents().get(0));
		assertNoResourceErrors("Load failed", xmiResource);
		assertNoUnresolvedProxies("Unresolved proxies", xmiResource);
		assertNoValidationErrors("Validation errors", xmiRoot);
		Resource oclResource = ClassUtil.requireNonNull(ocl.getResourceSet().getResource(oclFile.getURI(), true));
		assertNoResourceErrors("Load failed", oclResource);
		assertNoUnresolvedProxies("Unresolved proxies", oclResource);
		assertNoValidationErrors("Validation errors", ClassUtil.requireNonNull(oclResource.getContents().get(0)));

		EStructuralFeature ref_RandL_Customer = xmiRoot.eClass().getEStructuralFeature("ref_RandL_Customer");
		@SuppressWarnings("unchecked")List<EObject> customers = (List<EObject>) xmiRoot.eGet(ref_RandL_Customer);
		EObject eObject = customers.get(0);

		EnvironmentFactoryInternal environmentFactory = ocl.getEnvironmentFactory();
		org.eclipse.ocl.pivot.Class customerClass = ClassUtil.requireNonNull(environmentFactory.getASOf(org.eclipse.ocl.pivot.Class.class, eObject.eClass()));
		Iterable<Constraint> customerInvariants = environmentFactory.getMetamodelManager().getAllInvariants(customerClass);
		Constraint constraint = ClassUtil.requireNonNull(NameUtil.getNameable(customerInvariants, "invariant_sizesAgree"));

		ThreadLocalExecutor.resetEnvironmentFactory();

		IProject iProject = testProject.getIProject();
		ILaunchConfigurationWorkingCopy launchConfiguration = createLaunchConfiguration(iProject, constraint, eObject);
		launchConfiguration.doSave();
		TestUIUtil.flushEvents();
		ILaunch launch = launchConfiguration.launch(ILaunchManager.DEBUG_MODE, null);
		assert launch != null;

		OCLDebugTarget debugTarget = (OCLDebugTarget) launch.getDebugTarget();
		try {
			OCLVMVirtualMachine vm = (OCLVMVirtualMachine) debugTarget.getVM();
			OCLVMRootEvaluationEnvironment vmRootEvaluationEnvironment = (OCLVMRootEvaluationEnvironment) vm.getEvaluationEnv();
			assert vmRootEvaluationEnvironment != null;
			ExpressionInOCL asExpression = (ExpressionInOCL) vmRootEvaluationEnvironment.getDebuggableElement();
			VariableDeclaration selfVariable = PivotUtil.getOwnedContext(asExpression);

			IThread vmThread = debugTarget.getThreads()[0];
			assert vmThread != null;
			TestUIUtil.waitForSuspended(vmThread);
			TestUIUtil.waitForNotStepping(vmThread);
			//
			checkPosition(vmThread, 263, 11364, 11368);
			checkVariables(vmThread, VMVirtualMachine.PC_NAME, PivotConstants.SELF_NAME);
			checkVariableEClass(vmThread, VMVirtualMachine.PC_NAME, PivotPackage.Literals.VARIABLE_EXP);
			checkVariable(vmThread, PivotConstants.SELF_NAME, vmRootEvaluationEnvironment.getValueOf(selfVariable));
			//
			vmThread.stepInto();
			TestUIUtil.waitForSuspended(vmThread);
			checkPosition(vmThread, 263, 11369, 11377);
			checkVariables(vmThread, VMVirtualMachine.PC_NAME, PivotConstants.SELF_NAME, "$ownedSource");
			checkVariableEClass(vmThread, VMVirtualMachine.PC_NAME, PivotPackage.Literals.PROPERTY_CALL_EXP);
			checkVariable(vmThread, PivotConstants.SELF_NAME, vmRootEvaluationEnvironment.getValueOf(selfVariable));
			//
			vmThread.stepInto();
			TestUIUtil.waitForSuspended(vmThread);
			checkPosition(vmThread, 263, 11379, 11385);
			checkVariables(vmThread, VMVirtualMachine.PC_NAME, PivotConstants.SELF_NAME, "$ownedSource");
			checkVariableEClass(vmThread, VMVirtualMachine.PC_NAME, PivotPackage.Literals.OPERATION_CALL_EXP);
			checkVariable(vmThread, PivotConstants.SELF_NAME, vmRootEvaluationEnvironment.getValueOf(selfVariable));
			//
			vmThread.stepInto();
			TestUIUtil.waitForSuspended(vmThread);
			checkPosition(vmThread, 264, 11390, 11394);
			checkVariables(vmThread, VMVirtualMachine.PC_NAME, PivotConstants.SELF_NAME);
			checkVariableEClass(vmThread, VMVirtualMachine.PC_NAME, PivotPackage.Literals.VARIABLE_EXP);
			checkVariable(vmThread, PivotConstants.SELF_NAME, vmRootEvaluationEnvironment.getValueOf(selfVariable));
			//
			vmThread.stepInto();
			TestUIUtil.waitForSuspended(vmThread);
			checkPosition(vmThread, 264, 11395, 11400);
			checkVariables(vmThread, VMVirtualMachine.PC_NAME, PivotConstants.SELF_NAME, "$ownedSource");
			checkVariableEClass(vmThread, VMVirtualMachine.PC_NAME, PivotPackage.Literals.PROPERTY_CALL_EXP);
			checkVariable(vmThread, PivotConstants.SELF_NAME, vmRootEvaluationEnvironment.getValueOf(selfVariable));
			//
			vmThread.stepInto();
			TestUIUtil.waitForSuspended(vmThread);
			checkPosition(vmThread, 266, 11449, 11463);
			checkVariables(vmThread, VMVirtualMachine.PC_NAME, PivotConstants.SELF_NAME);
			checkVariableEClass(vmThread, VMVirtualMachine.PC_NAME, PivotPackage.Literals.VARIABLE_EXP);
			checkVariable(vmThread, PivotConstants.SELF_NAME, vmRootEvaluationEnvironment.getValueOf(selfVariable));
			//
			vmThread.stepInto();
			TestUIUtil.waitForSuspended(vmThread);
			checkPosition(vmThread, 266, 11464, 11469);
			checkVariables(vmThread, VMVirtualMachine.PC_NAME, "$ownedSource", "i_CustomerCard");
			checkVariableEClass(vmThread, VMVirtualMachine.PC_NAME, PivotPackage.Literals.PROPERTY_CALL_EXP);
			//		checkVariable(vmThread, PivotConstants.SELF_NAME, vmRootEvaluationEnvironment.getValueOf(selfVariable));
			//
			vmThread.stepInto();
			TestUIUtil.waitForSuspended(vmThread);
			checkPosition(vmThread, 266, 11472, 11476);
			checkVariables(vmThread, VMVirtualMachine.PC_NAME, "i_CustomerCard");
			checkVariableEClass(vmThread, VMVirtualMachine.PC_NAME, PivotPackage.Literals.BOOLEAN_LITERAL_EXP);
			//		checkVariable(vmThread, PivotConstants.SELF_NAME, vmRootEvaluationEnvironment.getValueOf(selfVariable));
			//
			vmThread.stepInto();
			TestUIUtil.waitForSuspended(vmThread);
			checkPosition(vmThread, 266, 11470, 11471);
			checkVariables(vmThread, VMVirtualMachine.PC_NAME, "$ownedSource", "$ownedArguments[0]", "i_CustomerCard");
			checkVariableEClass(vmThread, VMVirtualMachine.PC_NAME, PivotPackage.Literals.OPERATION_CALL_EXP);
			//		checkVariable(vmThread, PivotConstants.SELF_NAME, vmRootEvaluationEnvironment.getValueOf(selfVariable));
			//
			vmThread.stepInto();
			TestUIUtil.waitForSuspended(vmThread);
			checkPosition(vmThread, 268, 11483, 11485);
			checkVariables(vmThread, VMVirtualMachine.PC_NAME, PivotConstants.SELF_NAME, "$ownedSource");
			checkVariableEClass(vmThread, VMVirtualMachine.PC_NAME, PivotPackage.Literals.OPERATION_CALL_EXP);
			checkVariable(vmThread, PivotConstants.SELF_NAME, vmRootEvaluationEnvironment.getValueOf(selfVariable));
			//
			vmThread.stepInto();
			TestUIUtil.waitForSuspended(vmThread);
			checkPosition(vmThread, 264, 11388, 11389);
			checkVariables(vmThread, VMVirtualMachine.PC_NAME, PivotConstants.SELF_NAME, "$ownedSource", "$ownedArguments[0]");
			checkVariableEClass(vmThread, VMVirtualMachine.PC_NAME, PivotPackage.Literals.OPERATION_CALL_EXP);
			checkVariable(vmThread, PivotConstants.SELF_NAME, vmRootEvaluationEnvironment.getValueOf(selfVariable));
			//
			vmThread.stepInto();
			TestUIUtil.waitForSuspended(vmThread);
			checkPosition(vmThread, 263, 11364, 11491);
			checkVariables(vmThread, VMVirtualMachine.PC_NAME, PivotConstants.SELF_NAME, "$ownedBody");
			checkVariableEClass(vmThread, VMVirtualMachine.PC_NAME, PivotPackage.Literals.EXPRESSION_IN_OCL);
			checkVariable(vmThread, PivotConstants.SELF_NAME, vmRootEvaluationEnvironment.getValueOf(selfVariable));
			//
			vmThread.stepInto();
			TestUIUtil.waitForTerminated(vmThread);
		}
		finally {
			try {
				launch.terminate();
				TestUIUtil.waitForLaunchToTerminate(launch, 10000);
			}
			finally {
				ILaunch[] launches = DebugPlugin.getDefault().getLaunchManager().getLaunches();
				TestUIUtil.removeTerminatedLaunches(launches);
				SourceLookupFacility.shutdown();
				initialPage.activate(initialPart);
				initialPage.closeAllEditors(false);
			//	TestUIUtil.wait(1000);
			//	gc("After closeAllEditors");
				IViewReference[] viewReferences = initialPage.getViewReferences();
				for (IViewReference viewReference : viewReferences) {
					IViewPart viewPart = viewReference.getView(false);
					if (viewPart instanceof VariablesView) {
						AbstractDebugView variablesView = (AbstractDebugView)viewPart;
						variablesView.getViewer().setInput(null);
						TreeViewer treeModelViewer = (TreeViewer)variablesView.getViewer();
						treeModelViewer.setContentProvider(new TreeModelContentProvider());
					}
				}
			//	gc("After close LaunchView");
			}
		}
	}
}
