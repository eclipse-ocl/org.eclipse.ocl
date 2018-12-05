/*******************************************************************************
 * Copyright (c) 2014, 2018 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * 
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.build.latex;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.mwe.core.issues.Issues;
import org.eclipse.emf.mwe.core.lib.AbstractWorkflowComponent;
import org.eclipse.ocl.xtext.oclstdlib.OCLstdlibStandaloneSetup;

public abstract class GenerateLaTeX extends AbstractWorkflowComponent
{
	protected Logger log = Logger.getLogger(getClass());	
	protected ResourceSet resourceSet = null;	
	protected String latexFolder;
	protected String latexFileName;
	protected String projectName;
	protected String modelFile;
	protected String grammarFile;
	protected String labelPrefix = "";

	protected GenerateLaTeX() {
		OCLstdlibStandaloneSetup.doSetup();
	}	

	@Override
	public void checkConfiguration(Issues issues) {
		if (latexFolder == null) {
			issues.addError(this, "latexFolder not specified.");
		}
		if (latexFileName == null) {
			issues.addError(this, "latexFileName not specified.");
		}
		if (projectName == null) {
			issues.addError(this, "projectName not specified.");
		}
		if (modelFile == null) {
			issues.addError(this, "modelFile not specified.");
		}
	}

	protected ResourceSet getResourceSet() {
		if (resourceSet == null) {
			resourceSet = new ResourceSetImpl();
		}
		return resourceSet;
	}

	/**
	 * The projectName relative path to the grammar definition. (e.g. "org.eclipse.ocl.examples.base/Base.xtext")
	 */
	public void setGrammarFile(String grammarFile) {
		this.grammarFile = grammarFile;
	}

	/**
	 * The prefix to apply to all Latex labels (e.g. "ocl:EssentialOCL:")
	 */
	public void setLabelPrefix(String labelPrefix) {
		this.labelPrefix = labelPrefix;
	}

	/**
	 * The platform relative path to the generated doc folder (e.g. "../doc/org.eclipse.ocl.doc")
	 */
	public void setLatexFileName(String latexFileName) {
		this.latexFileName = latexFileName;
	}

	/**
	 * The platform relative path to the generated doc folder (e.g. "../doc/org.eclipse.ocl.doc")
	 */
	public void setLatexFolder(String latexFolder) {
		this.latexFolder = latexFolder;
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
}
