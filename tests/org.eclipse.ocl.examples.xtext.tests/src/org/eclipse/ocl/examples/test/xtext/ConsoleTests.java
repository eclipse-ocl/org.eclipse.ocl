/*******************************************************************************
 * Copyright (c) 2011, 2018 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.test.xtext;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.model.IStackFrame;
import org.eclipse.debug.core.model.IThread;
import org.eclipse.debug.core.model.IVariable;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.utilities.CGUtil;
import org.eclipse.ocl.examples.debug.core.OCLDebugTarget;
import org.eclipse.ocl.examples.debug.launching.OCLLaunchConstants;
import org.eclipse.ocl.examples.debug.vm.VMVirtualMachine;
import org.eclipse.ocl.examples.debug.vm.core.VMVariable;
import org.eclipse.ocl.examples.xtext.tests.TestUIUtil;
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.NullLiteralExp;
import org.eclipse.ocl.pivot.OperationCallExp;
import org.eclipse.ocl.pivot.PropertyCallExp;
import org.eclipse.ocl.pivot.VariableExp;
import org.eclipse.ui.internal.Workbench;

/**
 * Tests that exercise the Xtext OCL Console.
 */
@SuppressWarnings("restriction")
public class ConsoleTests extends AbstractConsoleTests
{
	public void testConsole_oclLog() throws Exception {
		assertConsoleResult(consolePage, null, "7", "7\n");
		assertConsoleResult(consolePage, null, "7.oclLog('seven = ')", "seven = 7\n7\n");
	}

	public void testConsole_debugger() throws Exception {
		if (CGUtil.isTychoSurefire()) {				// FIXME BUG 526252
			System.err.println(getName() + " has been disabled -see Bug 526252");
			return;
		}
		ILaunch launch = null;
		try {
			//		VMVirtualMachine.LOCATION.setState(true);
			//		VMVirtualMachine.PRE_VISIT.setState(true);
			//		VMVirtualMachine.POST_VISIT.setState(true);
			//		VMVirtualMachine.VISITOR_STACK.setState(true);
			//		VMVirtualMachine.VM_EVENT.setState(true);
			//		VMVirtualMachine.VM_REQUEST.setState(true);
			//		VMVirtualMachine.VM_RESPONSE.setState(true);
			TestUIUtil.enableSwitchToDebugPerspectivePreference();
			assertConsoleResult(consolePage, EcorePackage.Literals.ECLASS, "self.name <> null", "true\n");
			launch = consolePage.launchDebugger();
			assertNotNull(launch);
			//
			Map<String, Object> attributes = launch.getLaunchConfiguration().getAttributes();
			ExpressionInOCL asExpressionInOCL = (ExpressionInOCL) attributes.get(OCLLaunchConstants.EXPRESSION_OBJECT);
			assert asExpressionInOCL != null;
			OperationCallExp asOperationCallExp = (OperationCallExp) asExpressionInOCL.getOwnedBody();
			PropertyCallExp asPropertyCallExpCallExp = (PropertyCallExp) asOperationCallExp.getOwnedSource();
			VariableExp asVariableExp = (VariableExp) asPropertyCallExpCallExp.getOwnedSource();
			NullLiteralExp asNullLiteralExp = (NullLiteralExp) asOperationCallExp.getOwnedArguments().get(0);
			//
			OCLDebugTarget debugTarget = (OCLDebugTarget) launch.getDebugTarget();
			IThread vmThread = debugTarget.getThreads()[0];
			assert vmThread != null;
			TestUIUtil.waitForSuspended(vmThread);
			//
			checkPosition(vmThread, 5, 112, 116);
			checkVariables(vmThread, VMVirtualMachine.PC_NAME, "self");
			checkVariable(vmThread, VMVirtualMachine.PC_NAME, asVariableExp);
			checkVariable(vmThread, "self", EcorePackage.Literals.ECLASS);
			//
			vmThread.stepInto();
			TestUIUtil.waitForSuspended(vmThread);
			//
			checkPosition(vmThread, 5, 117, 121);
			checkVariables(vmThread, VMVirtualMachine.PC_NAME, "self", "$ownedSource");
			checkVariable(vmThread, VMVirtualMachine.PC_NAME, asPropertyCallExpCallExp);
			checkVariable(vmThread, "self", EcorePackage.Literals.ECLASS);
			checkVariable(vmThread, "$ownedSource", EcorePackage.Literals.ECLASS);
			//
			vmThread.stepInto();
			TestUIUtil.waitForSuspended(vmThread);
			//
			checkPosition(vmThread, 5, 125, 129);
			checkVariables(vmThread, VMVirtualMachine.PC_NAME, "self");
			checkVariable(vmThread, VMVirtualMachine.PC_NAME, asNullLiteralExp);
			checkVariable(vmThread, "self", EcorePackage.Literals.ECLASS);
			//
			vmThread.stepInto();
			TestUIUtil.waitForSuspended(vmThread);
			//
			checkPosition(vmThread, 5, 122, 124);
			checkVariables(vmThread, VMVirtualMachine.PC_NAME, "self", "$ownedSource", "$ownedArguments[0]");
			checkVariable(vmThread, VMVirtualMachine.PC_NAME, asOperationCallExp);
			checkVariable(vmThread, "self", EcorePackage.Literals.ECLASS);
			checkVariable(vmThread, "$ownedSource", "EClass");
			checkVariable(vmThread, "$ownedArguments[0]", null);
			//
			vmThread.stepInto();
			TestUIUtil.waitForSuspended(vmThread);
			//
			checkPosition(vmThread, 5, 112, 129);
			checkVariables(vmThread, VMVirtualMachine.PC_NAME, "self", "$ownedBody");
			checkVariable(vmThread, VMVirtualMachine.PC_NAME, asExpressionInOCL);
			checkVariable(vmThread, "self", EcorePackage.Literals.ECLASS);
			checkVariable(vmThread, "$ownedBody", true);
			//
			vmThread.stepInto();
			TestUIUtil.waitForTerminated(vmThread);
		}
		catch (Throwable e) {
			if (launch != null) {
				launch.terminate();
				TestUIUtil.wait(1000);
			}
			throw e;
		}
		finally {
			//
			ILaunch[] launches = DebugPlugin.getDefault().getLaunchManager().getLaunches();
			TestUIUtil.removeTerminatedLaunches(launches);
			//		SourceLookupFacility.shutdown();		// BUG 468902 this doesn't work
			Workbench.getInstance().getActiveWorkbenchWindow().getActivePage().closeAllEditors(false);
			TestUIUtil.wait(1000);
		}
	}

	public void testConsole_printCollections_474933() {
		// plain collections
		assertConsoleResult(consolePage, null, "Set{}", "");
		assertConsoleResult(consolePage, null, "Set{1}", "1\n");
		assertConsoleResult(consolePage, null, "OrderedSet{1, 2, 3}", "1\n2\n3\n");
		// nested collections
		assertConsoleResult(consolePage, null, "Set{Set{}}", "Set{}\n");
		assertConsoleResult(consolePage, null, "Sequence{OrderedSet{42, 314}, OrderedSet{271}}",
				"OrderedSet{42,314}\nOrderedSet{271}\n");
		assertConsoleResult(consolePage, null,
			"OrderedSet{Sequence{Bag{2, 2}, Bag{3}, Bag{4, 4, 4}}, Sequence{Bag{5}}, Sequence{Bag{}, Bag{5, 5}}, Sequence{Bag{4}}}",
				"Sequence{Bag{2,2},Bag{3},Bag{4,4,4}}\nSequence{Bag{5}}\nSequence{Bag{},Bag{5,5}}\nSequence{Bag{4}}\n");
	}

	private void checkPosition(@NonNull IThread vmThread, int lineNumber, int charStart, int charEnd) throws DebugException {
		IStackFrame topStackFrame = vmThread.getTopStackFrame();
		assertEquals("lineNumber", lineNumber, topStackFrame.getLineNumber());
		assertEquals("charStart", charStart, topStackFrame.getCharStart());
		assertEquals("charEnd", charEnd, topStackFrame.getCharEnd());
	}

	/*	private void checkVariable(@NonNull IThread vmThread, @NonNull String name, @NonNull Class<?> expectedClass) throws DebugException {
		IStackFrame topStackFrame = vmThread.getTopStackFrame();
		IVariable[] variables = topStackFrame.getVariables();
		if (variables != null){
			for (IVariable variable : variables) {
				if (name.equals(variable.getName()) && (variable instanceof VMVariable)) {
					Object valueObject = ((VMVariable)variable).getVmVar().valueObject;
					assertEquals(expectedClass, valueObject != null ? valueObject.getClass() : null);
					return;
				}
			}
		}
		fail("Unknown variable '" + name + "'");
	} */

	private void checkVariable(@NonNull IThread vmThread, @NonNull String name, @Nullable Object expectedValue) throws DebugException {
		IStackFrame topStackFrame = vmThread.getTopStackFrame();
		IVariable[] variables = topStackFrame.getVariables();
		if (variables != null){
			for (IVariable variable : variables) {
				if (name.equals(variable.getName()) && (variable instanceof VMVariable)) {
					Object valueObject = ((VMVariable)variable).getVmVar().valueObject;
					assertEquals(expectedValue, valueObject);
					return;
				}
			}
		}
		fail("Unknown variable '" + name + "'");
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
		assertEquals(expectedNames, actualNames);
	}
}
