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

import java.io.File;
import java.io.FileWriter;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.mwe.core.WorkflowContext;
import org.eclipse.emf.mwe.core.issues.Issues;
import org.eclipse.emf.mwe.core.monitor.ProgressMonitor;
import org.eclipse.emf.mwe.utils.StandaloneSetup;
import org.eclipse.ocl.pivot.Library;
import org.eclipse.ocl.pivot.Model;
import org.eclipse.ocl.pivot.resource.ASResource;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.xtext.base.utilities.BaseCSResource;
import org.eclipse.xtext.XtextStandaloneSetup;

public abstract class GenerateLaTeXForLibrary extends GenerateLaTeXUtils
{

	protected abstract /*@NonNull*/ String generateLaTeX(/*@NonNull*/ Library asLibrary);

	@Override
	protected void invokeInternal(WorkflowContext ctx, ProgressMonitor monitor, Issues issues) {
		String rootPath = StandaloneSetup.getPlatformRootPath();
		XtextStandaloneSetup.doSetup();
		File folder = new File(rootPath + latexFolder);
		folder.mkdirs();
		try {
			String sourceFile = "/" + projectName + "/" + modelFile;
			URI fileURI = URI.createPlatformResourceURI(sourceFile, true);
			log.info("Loading OCL library '" + fileURI);
			ResourceSet resourceSet = getResourceSet();
			Resource xtextResource = resourceSet.getResource(fileURI, true);
			String message = PivotUtil.formatResourceDiagnostics(ClassUtil.nonNullEMF(xtextResource.getErrors()), "OCLstdlib parse failure", "\n");
			if (message != null) {
				issues.addError(this, message, null, null, null);
				return;
			}
			ASResource asResource = ((BaseCSResource)xtextResource).getASResource();
//			if (asResource == null) {
//				return;
//			}
			EObject pivotModel = ClassUtil.nonNullState(asResource.getContents().get(0));
//			ASSaver saver = new ASSaver(asResource);
//			saver.localizeSpecializations();
			String fileName = folder + "/" + latexFileName + ".tex";
			log.info("Generating '" + fileName + "'");
			String latexContent = generateLaTeX(ClassUtil.nonNullState((Library) ((Model)pivotModel).getOwnedPackages().get(0)));
			String encodedContent = encodeForLaTeX(latexContent);
			FileWriter fw = new FileWriter(fileName);
			fw.append(encodedContent);
			fw.close();
		} catch (RuntimeException e) {
			throw e;
		} catch (Exception e) {
			throw new RuntimeException("Problems running " + getClass().getSimpleName(), e);
		}
	}
}
