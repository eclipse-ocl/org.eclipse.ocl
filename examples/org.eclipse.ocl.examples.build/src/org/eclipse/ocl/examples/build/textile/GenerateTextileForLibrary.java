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
package org.eclipse.ocl.examples.build.textile;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.mwe.core.WorkflowContext;
import org.eclipse.emf.mwe.core.issues.Issues;
import org.eclipse.emf.mwe.core.lib.AbstractWorkflowComponent;
import org.eclipse.emf.mwe.core.monitor.ProgressMonitor;
import org.eclipse.emf.mwe.utils.StandaloneSetup;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.Library;
import org.eclipse.ocl.pivot.Model;
import org.eclipse.ocl.pivot.Precedence;
import org.eclipse.ocl.pivot.resource.ASResource;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.xtext.base.utilities.BaseCSResource;
import org.eclipse.ocl.xtext.oclstdlib.OCLstdlibStandaloneSetup;

public abstract class GenerateTextileForLibrary extends AbstractWorkflowComponent
{
	protected Logger log = Logger.getLogger(getClass());	
	protected ResourceSet resourceSet = null;	
	protected String textileFolder;
	protected String textileFileName;
	protected String projectName;
	protected String modelFile;

	protected String sourceFile;

	protected GenerateTextileForLibrary() {
		OCLstdlibStandaloneSetup.doSetup();
	}	

	@Override
	public void checkConfiguration(Issues issues) {
		if (textileFolder == null) {
			issues.addError(this, "textileFolder not specified.");
		}
		if (textileFileName == null) {
			issues.addError(this, "textileFileName not specified.");
		}
		if (projectName == null) {
			issues.addError(this, "projectName not specified.");
		}
		if (modelFile == null) {
			issues.addError(this, "modelFile not specified.");
		}
	}

	protected abstract /*@NonNull*/ String generateTextile(/*@NonNull*/ Model pivotModel);

	protected @Nullable Library getLibrary(@NonNull Model root) {
		TreeIterator<EObject> tit = root.eAllContents();
		while (tit.hasNext()) {
			EObject eObject = tit.next();
			if (eObject instanceof Library) {
				return (Library) eObject;
			}
		}
		return null;
	}
	
	protected Iterable<Precedence> getPrecedences(@NonNull Model asModel) {
		List<Precedence> precedences = new ArrayList<Precedence>();
		for (org.eclipse.ocl.pivot.Package asPackage : asModel.getOwnedPackages()) {
			if (asPackage instanceof Library) {
				precedences.addAll(((Library)asPackage).getOwnedPrecedences());
			}
		}
		return precedences;
	}

	protected ResourceSet getResourceSet() {
		if (resourceSet == null) {
			resourceSet = new ResourceSetImpl();
		}
		return resourceSet;
	}
	
	@Override
	protected void invokeInternal(WorkflowContext ctx, ProgressMonitor monitor, Issues issues) {
		String rootPath = StandaloneSetup.getPlatformRootPath();
		File folder = new File(rootPath + textileFolder);
		try {
			sourceFile = "/" + projectName + "/" + modelFile;
			URI fileURI = URI.createPlatformResourceURI(sourceFile, true);
			log.info("Loading OCL library '" + fileURI);
			ResourceSet resourceSet = getResourceSet();
			BaseCSResource xtextResource = (BaseCSResource)resourceSet.getResource(fileURI, true);
			String message = PivotUtil.formatResourceDiagnostics(ClassUtil.nonNullEMF(xtextResource.getErrors()), "OCLstdlib parse failure", "\n");
			if (message != null) {
				issues.addError(this, message, null, null, null);
				return;
			}
			ASResource asResource = xtextResource.getASResource();
//			if (asResource == null) {
//				return;
//			}
			EObject pivotModel = ClassUtil.nonNullState(asResource.getContents().get(0));
//			ASSaver saver = new ASSaver(asResource);
//			saver.localizeSpecializations();
			String fileName = folder + "/" + textileFileName + ".textile";
			log.info("Generating '" + fileName + "'");
			String textileContent = generateTextile((Model)pivotModel);
			FileWriter fw = new FileWriter(fileName);
			fw.append(textileContent);
			fw.close();
		} catch (RuntimeException e) {
			throw e;
		} catch (Exception e) {
			throw new RuntimeException("Problems running " + getClass().getSimpleName(), e);
		}
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
	 * The platform relative path to the generated doc folder (e.g. "../doc/org.eclipse.ocl.doc")
	 */
	public void setTextileFileName(String textileFileName) {
		this.textileFileName = textileFileName;
	}

	/**
	 * The platform relative path to the generated doc folder (e.g. "../doc/org.eclipse.ocl.doc")
	 */
	public void setTextileFolder(String textileFolder) {
		this.textileFolder = textileFolder;
	}
}
