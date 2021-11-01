/*******************************************************************************
 * Copyright (c) 2010, 2019 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/

package org.eclipse.ocl.examples.test.ecore;

import org.eclipse.emf.codegen.ecore.genmodel.GenRuntimeVersion;
import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.examples.codegen.utilities.CGUtil;
import org.eclipse.ocl.examples.xtext.tests.TestCaseAppender;
import org.eclipse.ocl.expressions.ExpressionsPackage;
import org.eclipse.ocl.pivot.PivotPackage;
import org.eclipse.ocl.pivot.internal.resource.StandaloneProjectMap;

/**
 */
public class ProjectMapTest extends AbstractProjectMapTest
{
	@Override
	protected void setUp() throws Exception {
//		StandaloneProjectMap.PROJECT_MAP_ADD_EPACKAGE.setState(true);
//		StandaloneProjectMap.PROJECT_MAP_ADD_GEN_MODEL.setState(true);
//		StandaloneProjectMap.PROJECT_MAP_ADD_GENERATED_PACKAGE.setState(true);
//		StandaloneProjectMap.PROJECT_MAP_ADD_URI_MAP.setState(true);
//		StandaloneProjectMap.PROJECT_MAP_CONFIGURE.setState(true);
//		StandaloneProjectMap.PROJECT_MAP_GET.setState(true);
//		StandaloneProjectMap.PROJECT_MAP_INSTALL.setState(true);
//		StandaloneProjectMap.PROJECT_MAP_RESOLVE.setState(true);
		super.setUp();
    	TestCaseAppender.INSTANCE.install();
    	StandaloneProjectMap.TEST_MAY_INITIALIZE_GLOBAL_FACILITIES = true;
		String targetRelease = System.getProperty("targetRelease");
		if (targetRelease != null) {
			boolean isTycho = CGUtil.isTychoSurefire();
			if (isTycho) {				// FIXME BUG 526252
				if (GenRuntimeVersion.get("2.14") == null) {			// less than Photon
					System.err.println(getTestName() + " - use of target platform suppressed for " + targetRelease + " - see Bug 576986");
				}
			}
		}
	}

	@Override
	protected void tearDown() throws Exception {
    	StandaloneProjectMap.TEST_MAY_INITIALIZE_GLOBAL_FACILITIES = !EMFPlugin.IS_ECLIPSE_RUNNING;
		super.tearDown();
	}

	public void testProjectMap_Ecore_LoadBoth() {					// Almost certainly a workspace project; always a project on Hudson
		@SuppressWarnings("null")@NonNull String project = EcorePackage.class.getPackage().getName();
		String modelPath = project + "/model/Ecore.ecore";
		doTestProjectMap_LoadBoth(EcorePackage.eINSTANCE, project, modelPath, "/");
	}

	public void testProjectMap_Ecore_LoadDefault() {					// Almost certainly a workspace project; always a project on Hudson
		@SuppressWarnings("null")@NonNull String project = EcorePackage.class.getPackage().getName();
		String modelPath = project + "/model/Ecore.ecore";
		doTestProjectMap_LoadDefault(EcorePackage.eINSTANCE, project, modelPath, "/", true);
	}

	public void testProjectMap_Ecore_LoadEPackage() {					// Almost certainly a workspace project; always a project on Hudson
		@SuppressWarnings("null")@NonNull String project = EcorePackage.class.getPackage().getName();
		String modelPath = project + "/model/Ecore.ecore";
		doTestProjectMap_LoadEPackage(EcorePackage.eINSTANCE, project, modelPath, "/");
	}

	public void testProjectMap_Ecore_LoadFirst() {					// Almost certainly a workspace project; always a project on Hudson
		@SuppressWarnings("null")@NonNull String project = EcorePackage.class.getPackage().getName();
		String modelPath = project + "/model/Ecore.ecore";
		doTestProjectMap_LoadFirst(EcorePackage.eINSTANCE, project, modelPath, "/", true);
	}

	public void testProjectMap_Ecore_LoadModel() {					// Almost certainly a workspace project; always a project on Hudson
		@SuppressWarnings("null")@NonNull String project = EcorePackage.class.getPackage().getName();
		String modelPath = project + "/model/Ecore.ecore";
		doTestProjectMap_LoadModel(EcorePackage.eINSTANCE, project, modelPath, "/", true);
	}

	public void testProjectMap_JavaVMTypes_LoadBoth() {					// Almost certainly a workspace project; always a project on Hudson
		EPackage.Registry.INSTANCE.put(org.eclipse.xtext.common.types.TypesPackage.eNS_URI, org.eclipse.xtext.common.types.TypesPackage.eINSTANCE);
		String project = "org.eclipse.xtext.common.types";
		String modelPath = project + "/model/JavaVMTypes.ecore";
		doTestProjectMap_LoadBoth(org.eclipse.xtext.common.types.TypesPackage.eINSTANCE, project, modelPath, "/");
	}

	public void testProjectMap_JavaVMTypes_LoadDefault() {					// Almost certainly a workspace project; always a project on Hudson
		EPackage.Registry.INSTANCE.put(org.eclipse.xtext.common.types.TypesPackage.eNS_URI, org.eclipse.xtext.common.types.TypesPackage.eINSTANCE);
		String project = "org.eclipse.xtext.common.types";
		String modelPath = project + "/model/JavaVMTypes.ecore";
		doTestProjectMap_LoadDefault(org.eclipse.xtext.common.types.TypesPackage.eINSTANCE, project, modelPath, "/", false);
	}

	public void testProjectMap_JavaVMTypes_LoadEPackage() {					// Almost certainly a workspace project; always a project on Hudson
		EPackage.Registry.INSTANCE.put(org.eclipse.xtext.common.types.TypesPackage.eNS_URI, org.eclipse.xtext.common.types.TypesPackage.eINSTANCE);
		String project = "org.eclipse.xtext.common.types";
		String modelPath = project + "/model/JavaVMTypes.ecore";
		doTestProjectMap_LoadEPackage(org.eclipse.xtext.common.types.TypesPackage.eINSTANCE, project, modelPath, "/");
	}

	public void testProjectMap_JavaVMTypes_LoadFirst() {					// Almost certainly a workspace project; always a project on Hudson
		EPackage.Registry.INSTANCE.put(org.eclipse.xtext.common.types.TypesPackage.eNS_URI, org.eclipse.xtext.common.types.TypesPackage.eINSTANCE);
		String project = "org.eclipse.xtext.common.types";
		String modelPath = project + "/model/JavaVMTypes.ecore";
		doTestProjectMap_LoadFirst(org.eclipse.xtext.common.types.TypesPackage.eINSTANCE, project, modelPath, "/", false);
	}

	public void testProjectMap_JavaVMTypes_LoadModel() {					// Almost certainly a workspace project; always a project on Hudson
		EPackage.Registry.INSTANCE.put(org.eclipse.xtext.common.types.TypesPackage.eNS_URI, org.eclipse.xtext.common.types.TypesPackage.eINSTANCE);
		String project = "org.eclipse.xtext.common.types";
		String modelPath = project + "/model/JavaVMTypes.ecore";
		doTestProjectMap_LoadModel(org.eclipse.xtext.common.types.TypesPackage.eINSTANCE, project, modelPath, "/", false);
	}

	public void testProjectMap_Pivot_LoadBoth() {					// Almost certainly a workspace project; always a project on Hudson
		String project = "org.eclipse.ocl.pivot";
		String modelPath = project + "/model/Pivot.ecore";
		doTestProjectMap_LoadBoth(PivotPackage.eINSTANCE, project, modelPath, "/");
	}

	public void testProjectMap_Pivot_LoadDefault() {					// Almost certainly a workspace project; always a project on Hudson
		String project = "org.eclipse.ocl.pivot";
		String modelPath = project + "/model/Pivot.ecore";
		doTestProjectMap_LoadDefault(PivotPackage.eINSTANCE, project, modelPath, "/", false);
	}

	public void testProjectMap_Pivot_LoadEPackage() {					// Almost certainly a workspace project; always a project on Hudson
		String project = "org.eclipse.ocl.pivot";
		String modelPath = project + "/model/Pivot.ecore";
		doTestProjectMap_LoadEPackage(PivotPackage.eINSTANCE, project, modelPath, "/");
	}

	public void testProjectMap_Pivot_LoadFirst() {					// Almost certainly a workspace project; always a project on Hudson
		String project = "org.eclipse.ocl.pivot";
		String modelPath = project + "/model/Pivot.ecore";
		doTestProjectMap_LoadFirst(PivotPackage.eINSTANCE, project, modelPath, "/", false);
	}

	public void testProjectMap_Pivot_LoadModel() {					// Almost certainly a workspace project; always a project on Hudson
		String project = "org.eclipse.ocl.pivot";
		String modelPath = project + "/model/Pivot.ecore";
		doTestProjectMap_LoadModel(PivotPackage.eINSTANCE, project, modelPath, "/", false);
	}

	public void testProjectMap_OCL_LoadBoth() {					// Almost certainly a workspace project; always a project on Hudson
		@SuppressWarnings("null")@NonNull String project = org.eclipse.ocl.OCL.class.getPackage().getName();
		String modelPath = project + "/model/OCL.ecore";
		doTestProjectMap_LoadBoth(ExpressionsPackage.eINSTANCE, project, modelPath, "//expressions");
	}

	public void testProjectMap_OCL_LoadDefault() {					// Almost certainly a workspace project; always a project on Hudson
		@SuppressWarnings("null")@NonNull String project = org.eclipse.ocl.OCL.class.getPackage().getName();
		String modelPath = project + "/model/OCL.ecore";
		doTestProjectMap_LoadDefault(ExpressionsPackage.eINSTANCE, project, modelPath, "//expressions", false);
	}

	public void testProjectMap_OCL_LoadEPackage() {					// Almost certainly a workspace project; always a project on Hudson
		@SuppressWarnings("null")@NonNull String project = org.eclipse.ocl.OCL.class.getPackage().getName();
		String modelPath = project + "/model/OCL.ecore";
		doTestProjectMap_LoadEPackage(ExpressionsPackage.eINSTANCE, project, modelPath, "//expressions");
	}

	public void testProjectMap_OCL_LoadFirst() {					// Almost certainly a workspace project; always a project on Hudson
		@SuppressWarnings("null")@NonNull String project = org.eclipse.ocl.OCL.class.getPackage().getName();
		String modelPath = project + "/model/OCL.ecore";
		doTestProjectMap_LoadFirst(ExpressionsPackage.eINSTANCE, project, modelPath, "//expressions", false);
	}

	public void testProjectMap_OCL_LoadModel() {					// Almost certainly a workspace project; always a project on Hudson
		@SuppressWarnings("null")@NonNull String project = org.eclipse.ocl.OCL.class.getPackage().getName();
		String modelPath = project + "/model/OCL.ecore";
		doTestProjectMap_LoadModel(ExpressionsPackage.eINSTANCE, project, modelPath, "//expressions", false);
	}
}
