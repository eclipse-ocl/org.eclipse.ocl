/*******************************************************************************
 * Copyright (c) 2013, 2018 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.build.xtend;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.mwe.core.issues.Issues;
import org.eclipse.emf.mwe.core.lib.AbstractWorkflowComponent;
import org.eclipse.ocl.xtext.oclstdlib.OCLstdlibStandaloneSetup;

public abstract class GenerateMetamodelWorkflowComponent extends AbstractWorkflowComponent
{
	protected Logger log = Logger.getLogger(getClass());	
	protected ResourceSet resourceSet = null;	
	protected String uri;
	protected String javaClassName;
	protected String javaFolder;
	protected String javaPackageName;
	protected String projectName;
	protected String modelFile;

	protected String sourceFile;

	protected GenerateMetamodelWorkflowComponent() {
		OCLstdlibStandaloneSetup.doSetup();
	}	

	@Override
	public void checkConfiguration(Issues issues) {
		if (uri == null) {
			issues.addError(this, "uri not specified.");
		}
		if (javaClassName == null) {
			issues.addError(this, "javaClassName not specified.");
		}
		if (javaFolder == null) {
			issues.addError(this, "javaFolder not specified.");
		}
		if (javaPackageName == null) {
			issues.addError(this, "javaPackageName not specified.");
		}
		if (projectName == null) {
			issues.addError(this, "projectName not specified.");
		}
		if (modelFile == null) {
			issues.addError(this, "modelFile not specified.");
		}
	}

	/**
	 * The Class Name of the generated metamodel.
	 */
	public void setJavaClassName(String javaClassName) {
		this.javaClassName = javaClassName;
	}

	/**
	 * The Java package path for the metamodel. (e.g. "org.eclipse.ocl.pivot.path")
	 */
	public void setJavaPackageName(String javaPackageName) {
		this.javaPackageName = javaPackageName;
	}

	/**
	 * The projectName relative path to the metamodel definition. (e.g. "model/Pivot.ecore")
	 */
	public void setModelFile(String modelFile) {
		this.modelFile = modelFile;
	}

	/**
	 * The project name hosting the Metamodel. (e.g. "org.eclipse.ocl.pivot")
	 */
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	
	/**
	 * An optional ResourceSet that MWE components may share to reduce model loading. 
	 */
	public void setResourceSet(ResourceSet resourceSet) {
		this.resourceSet = resourceSet;
	}
	
	/**
	 * The nsURI for use in the generated metamodel. (e.g. "http://www.eclipse.org/ocl/2015/Pivot").
	 */
	public void setUri(String uri) {
		this.uri = uri;
	}
}
