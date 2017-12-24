/*******************************************************************************
 * Copyright (c) 2011, 2015 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.test.xtext;

import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfigurationType;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.examples.debug.launching.OCLLaunchConstants;
import org.eclipse.ocl.examples.xtext.tests.TestUIUtil;
import org.eclipse.ocl.examples.xtext.tests.XtextTestCase;
import org.eclipse.ocl.pivot.Constraint;
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal.EnvironmentFactoryInternalExtension;
import org.eclipse.ocl.pivot.internal.utilities.OCLInternal;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.NameUtil;

/**
 * Tests that load a model and verify that there are no unresolved proxies as a result.
 */
public class DebuggerTests extends XtextTestCase
{
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

	public void testDebugger_Launch() throws Exception {
		TestUIUtil.closeIntro();
		TestUIUtil.enableSwitchToDebugPerspectivePreference();
		//
		IProject iProject = TestUIUtil.createIProject("DebuggerTests");
		IFile xmiFile = TestUIUtil.copyIFile(iProject.getFile("RoyalAndLoyal.xmi"), getProjectFileURI("RoyalAndLoyal.xmi"), null);
		@SuppressWarnings("unused")IFile ecoreFile = TestUIUtil.copyIFile(iProject.getFile("RoyalAndLoyal.ecore"), getProjectFileURI("RoyalAndLoyal.ecore"), null);
		IFile oclFile = TestUIUtil.copyIFile(iProject.getFile("RoyalAndLoyal.ocl"), getProjectFileURI("RoyalAndLoyal.ocl"), "UTF-8");
		URI xmiURI = URI.createPlatformResourceURI(xmiFile.getFullPath().toString(), true);
		URI oclURI = URI.createPlatformResourceURI(oclFile.getFullPath().toString(), true);
		//
		OCLInternal ocl = OCLInternal.newInstance(getProjectMap(), null);
		Resource xmiResource = ocl.getResourceSet().getResource(xmiURI, true);
		EObject xmiRoot = ClassUtil.nonNullState(xmiResource.getContents().get(0));
		assertNoResourceErrors("Load failed", xmiResource);
		assertNoUnresolvedProxies("Unresolved proxies", xmiResource);
		assertNoValidationErrors("Validation errors", xmiRoot);
		Resource oclResource = ClassUtil.nonNullState(ocl.getResourceSet().getResource(oclURI, true));
		assertNoResourceErrors("Load failed", oclResource);
		assertNoUnresolvedProxies("Unresolved proxies", oclResource);
		assertNoValidationErrors("Validation errors", ClassUtil.nonNullState(oclResource.getContents().get(0)));

		EStructuralFeature ref_RandL_Customer = xmiRoot.eClass().getEStructuralFeature("ref_RandL_Customer");
		@SuppressWarnings("unchecked")List<EObject> customers = (List<EObject>) xmiRoot.eGet(ref_RandL_Customer);
		EObject eObject = customers.get(0);

		EnvironmentFactoryInternalExtension environmentFactory = (EnvironmentFactoryInternalExtension)ocl.getEnvironmentFactory();
		org.eclipse.ocl.pivot.Class customerClass = ClassUtil.nonNullState(environmentFactory.getASOf(org.eclipse.ocl.pivot.Class.class, eObject.eClass()));
		Iterable<Constraint> customerInvariants = environmentFactory.getMetamodelManager().getAllInvariants(customerClass);
		Constraint constraint = ClassUtil.nonNullState(NameUtil.getNameable(customerInvariants, "invariant_sizesAgree"));

		ILaunchConfigurationWorkingCopy launchConfiguration = createLaunchConfiguration(iProject, constraint, eObject);
		launchConfiguration.doSave();
		TestUIUtil.flushEvents();
		ILaunch launch = launchConfiguration.launch(ILaunchManager.DEBUG_MODE, null);
		assert launch != null;
		TestUIUtil.waitForLaunchToTerminate(launch);
		ocl.dispose();
	}
}
