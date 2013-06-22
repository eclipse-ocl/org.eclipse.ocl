/**
 * <copyright>
 *
 * Copyright (c) 2013 E.D.Willink and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *
 * </copyright>
 */
package org.eclipse.ocl.examples.build.xtend

import org.apache.log4j.Logger
import org.eclipse.emf.ecore.resource.ResourceSet
import org.eclipse.emf.mwe.core.issues.Issues
import org.eclipse.emf.mwe.core.lib.AbstractWorkflowComponent
import org.eclipse.ocl.examples.xtext.oclstdlib.OCLstdlibStandaloneSetup

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

	protected new() {
		OCLstdlibStandaloneSetup.doSetup();
	}	

	override checkConfiguration(Issues issues) {
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
	public def void setJavaClassName(String javaClassName) {
		this.javaClassName = javaClassName;
	}

	/**
	 * The Java package path for the metamodel. (e.g. "org.eclipse.ocl.examples.pivot.path")
	 */
	public def void setJavaPackageName(String javaPackageName) {
		this.javaPackageName = javaPackageName;
	}

	/**
	 * The projectName relative path to the metamodel definition. (e.g. "model/Pivot.ecore")
	 */
	public def void setModelFile(String modelFile) {
		this.modelFile = modelFile;
	}

	/**
	 * The project name hosting the Metamodel. (e.g. "org.eclipse.ocl.examples.pivot")
	 */
	public def void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	
	/**
	 * An optional ResourceSet that MWE components may share to reduce model loading. 
	 */
	public def void setResourceSet(ResourceSet resourceSet) {
		this.resourceSet = resourceSet;
	}
	
	/**
	 * The nsURI for use in the generated metamodel. (e.g. "http://www.eclipse.org/ocl/3.1.0/Pivot").
	 */
	public def void setUri(String uri) {
		this.uri = uri;
	}
}
