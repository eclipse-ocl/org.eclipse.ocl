/*******************************************************************************
 * Copyright (c) 2014, 2018 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/

package org.eclipse.ocl.examples.consumers.tests;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.examples.test.ecore.AbstractProjectMapTest;
import org.eclipse.ocl.pivot.internal.resource.StandaloneProjectMap;
import org.eclipse.papyrus.sysml.SysmlPackage;
import org.eclipse.papyrus.sysml.portandflows.PortandflowsPackage;

/**
 */
public class ConsumerProjectMapTest extends AbstractProjectMapTest
{	
	@Override
	protected void setUp() throws Exception {
		StandaloneProjectMap.PROJECT_MAP_ADD_EPACKAGE.setState(true);
		StandaloneProjectMap.PROJECT_MAP_ADD_GEN_MODEL.setState(true);
		StandaloneProjectMap.PROJECT_MAP_ADD_GENERATED_PACKAGE.setState(true);
		StandaloneProjectMap.PROJECT_MAP_ADD_URI_MAP.setState(true);
		StandaloneProjectMap.PROJECT_MAP_CONFIGURE.setState(true);
		StandaloneProjectMap.PROJECT_MAP_INSTALL.setState(true);
		StandaloneProjectMap.PROJECT_MAP_RESOLVE.setState(true);
		super.setUp();
	}

	public void testProjectMap_SysML_LoadBoth() {					// Almost certainly a workspace project; always a project on Hudson
		@SuppressWarnings("null")@NonNull String project = SysmlPackage.class.getPackage().getName();
		String modelPath = project + "/model/sysml.ecore";
		doTestProjectMap_LoadBoth(SysmlPackage.eINSTANCE, project, modelPath, "/");
	}
	
	public void testProjectMap_SysML_LoadDefault() {					// Almost certainly a workspace project; always a project on Hudson
		@SuppressWarnings("null")@NonNull String project = SysmlPackage.class.getPackage().getName();
		String modelPath = project + "/model/sysml.ecore";
		doTestProjectMap_LoadDefault(SysmlPackage.eINSTANCE, project, modelPath, "/", false);
	}
	
	public void testProjectMap_SysML_LoadEPackage() {					// Almost certainly a workspace project; always a project on Hudson
		@SuppressWarnings("null")@NonNull String project = SysmlPackage.class.getPackage().getName();
		String modelPath = project + "/model/sysml.ecore";
		doTestProjectMap_LoadEPackage(SysmlPackage.eINSTANCE, project, modelPath, "/");
	}
	
	public void testProjectMap_SysML_LoadFirst() {					// Almost certainly a workspace project; always a project on Hudson
		@SuppressWarnings("null")@NonNull String project = SysmlPackage.class.getPackage().getName();
		String modelPath = project + "/model/sysml.ecore";
		doTestProjectMap_LoadFirst(SysmlPackage.eINSTANCE, project, modelPath, "/", false);
	}
	
	public void testProjectMap_SysML_LoadModel() {					// Almost certainly a workspace project; always a project on Hudson
		@SuppressWarnings("null")@NonNull String project = SysmlPackage.class.getPackage().getName();
		String modelPath = project + "/model/sysml.ecore";
		doTestProjectMap_LoadModel(SysmlPackage.eINSTANCE, project, modelPath, "/", false);
	}

	public void testProjectMap_SysML_PortAndFlows_LoadBoth() {					// Almost certainly a workspace project; always a project on Hudson
		@SuppressWarnings("null")@NonNull String project = SysmlPackage.class.getPackage().getName();
		String modelPath = project + "/model/sysml.ecore";
		doTestProjectMap_LoadBoth(PortandflowsPackage.eINSTANCE, project, modelPath, "//portandflows");
	}
	
	public void testProjectMap_SysML_PortAndFlows_LoadDefault() {					// Almost certainly a workspace project; always a project on Hudson
		@SuppressWarnings("null")@NonNull String project = SysmlPackage.class.getPackage().getName();
		String modelPath = project + "/model/sysml.ecore";
		doTestProjectMap_LoadDefault(PortandflowsPackage.eINSTANCE, project, modelPath, "//portandflows", false);
	}
	
	public void testProjectMap_SysML_PortAndFlows_LoadEPackage() {					// Almost certainly a workspace project; always a project on Hudson
		@SuppressWarnings("null")@NonNull String project = SysmlPackage.class.getPackage().getName();
		String modelPath = project + "/model/sysml.ecore";
		doTestProjectMap_LoadEPackage(PortandflowsPackage.eINSTANCE, project, modelPath, "//portandflows");
	}
	
	public void testProjectMap_SysML_PortAndFlows_LoadFirst() {					// Almost certainly a workspace project; always a project on Hudson
		@SuppressWarnings("null")@NonNull String project = SysmlPackage.class.getPackage().getName();
		String modelPath = project + "/model/sysml.ecore";
		doTestProjectMap_LoadFirst(PortandflowsPackage.eINSTANCE, project, modelPath, "//portandflows", false);
	}
	
	public void testProjectMap_SysML_PortAndFlows_LoadModel() {					// Almost certainly a workspace project; always a project on Hudson
		@SuppressWarnings("null")@NonNull String project = SysmlPackage.class.getPackage().getName();
		String modelPath = project + "/model/sysml.ecore";
		doTestProjectMap_LoadModel(PortandflowsPackage.eINSTANCE, project, modelPath, "//portandflows", false);
	}
}
