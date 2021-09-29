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

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.URIHandler;
import org.eclipse.emf.mwe.core.WorkflowContext;
import org.eclipse.emf.mwe.core.issues.Issues;
import org.eclipse.emf.mwe.core.monitor.ProgressMonitor;
import org.eclipse.emf.mwe.utils.StandaloneSetup;
import org.eclipse.ocl.examples.build.utilities.ClasspathURIHandler;
import org.eclipse.ocl.pivot.Model;
import org.eclipse.ocl.pivot.internal.ecore.es2as.Ecore2AS;
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.MetamodelManager;
import org.eclipse.ocl.pivot.utilities.OCL;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.xtext.completeocl.CompleteOCLStandaloneSetup;
import org.eclipse.xtext.Grammar;
import org.eclipse.xtext.XtextStandaloneSetup;

public abstract class GenerateLaTeXForCSModel extends GenerateLaTeXUtils
{
	protected String cs2asFile;
	protected String cs2csFile;

	protected abstract /*@NonNull*/ String generateLaTeX(org.eclipse.ocl.pivot./*@NonNull*/ Package asPackage,
			/*@NonNull*/ Grammar grammar, org.eclipse.ocl.pivot./*@Nullable*/ Package cs2asPackage,
			org.eclipse.ocl.pivot./*@Nullable*/ Package cs2csPackage);

	@Override
	protected void invokeInternal(WorkflowContext ctx, ProgressMonitor monitor, Issues issues) {

		String rootPath = StandaloneSetup.getPlatformRootPath();
		XtextStandaloneSetup.doSetup();
		CompleteOCLStandaloneSetup.doSetup();
		File folder = new File(rootPath + latexFolder);
		folder.mkdirs();
		OCL ocl = OCL.newInstance();
		MetamodelManager metamodelManager = ocl.getMetamodelManager();
		ResourceSet resourceSet = ocl.getResourceSet();
		EList<URIHandler> uriHandlers = resourceSet.getURIConverter().getURIHandlers();
		uriHandlers.add(0, new ClasspathURIHandler());
		try {
			org.eclipse.ocl.pivot.Package asPackage = null;
			org.eclipse.ocl.pivot.Package cs2asPackage = null;
			org.eclipse.ocl.pivot.Package cs2csPackage = null;
			if ((cs2asFile != null) && (cs2asFile.length() > 0)) {
				String cs2asSourceFile = "/" + projectName + "/" + cs2asFile;
				URI cs2asURI = ClassUtil.nonNullState(URI.createPlatformResourceURI(cs2asSourceFile, true));
				log.info("Loading Model '" + cs2asURI);
				Resource oclResource = ocl.getCSResource(cs2asURI);
				cs2asPackage = getSecondaryPackage(metamodelManager, oclResource);
			}
			if ((cs2csFile != null) && (cs2csFile.length() > 0)) {
				String cs2csSourceFile = "/" + projectName + "/" + cs2csFile;
				URI cs2csURI = ClassUtil.nonNullState(URI.createPlatformResourceURI(cs2csSourceFile, true));
				log.info("Loading Model '" + cs2csURI);
				Resource oclResource = ocl.getCSResource(cs2csURI);
				cs2csPackage = getSecondaryPackage(metamodelManager, oclResource);
			}
			if (cs2asPackage != null) {
				asPackage = metamodelManager.getPrimaryPackage(cs2asPackage);
			}
			else if (cs2csPackage != null) {
				asPackage = metamodelManager.getPrimaryPackage(cs2csPackage);
			}
			else {
				String sourceFile = "/" + projectName + "/" + modelFile;
				URI fileURI = URI.createPlatformResourceURI(sourceFile, true);
				log.info("Loading Model '" + fileURI);
				Resource eResource = resourceSet.getResource(fileURI, true);
				if (eResource == null) {
					issues.addError(this, "No eResource for + ;" + fileURI + "'", null, null, null);
					return;
				}
				Ecore2AS adapter = Ecore2AS.getAdapter(eResource, (EnvironmentFactoryInternal) metamodelManager.getEnvironmentFactory());
				Model asModel = adapter.getASModel();
				asPackage = asModel.getOwnedPackages().get(0);
			}

			String xtextFile = "/" + projectName + "/" + grammarFile;
			URI fileURI = URI.createPlatformResourceURI(xtextFile, true);
			log.info("Loading Grammar '" + fileURI);
//			ResourceSet resourceSet = getResourceSet();
			Resource xtextResource = resourceSet.getResource(fileURI, true);
			String message = PivotUtil.formatResourceDiagnostics(ClassUtil.nonNullEMF(xtextResource.getErrors()), "Grammar parse failure", "\n");
			if (message != null) {
				issues.addError(this, message, null, null, null);
				return;
			}
			EObject xtextModel = ClassUtil.nonNullState(xtextResource.getContents().get(0));



			EObject eObject = asPackage.getESObject();
			Resource eResource = eObject.eResource();
			if (eResource != null) {
				String message2 = PivotUtil.formatResourceDiagnostics(ClassUtil.nonNullEMF(eResource.getErrors()), "OCLstdlib parse failure", "\n");
				if (message2 != null) {
					issues.addError(this, message, null, null, null);
					return;
				}
//				ASSaver saver = new ASSaver(asResource);
//				saver.localizeSpecializations();
				String fileName = folder + "/" + latexFileName + ".tex";
				log.info("Generating '" + fileName + "'");
				String latexContent = generateLaTeX(asPackage, (Grammar)xtextModel, cs2asPackage, cs2csPackage);
				String encodedContent = encodeForLaTeX(latexContent);
				FileWriter fw = new FileWriter(fileName);
				fw.append(encodedContent);
				fw.close();
			}
		} catch (RuntimeException e) {
			throw e;
		} catch (Exception e) {
			throw new RuntimeException("Problems running " + getClass().getSimpleName(), e);
		} finally {
			ocl.dispose(true);
		}
	}

	/**
	 * The projectName relative path to the CS2AS rules. (e.g. "model/BaseCS2AS.ocl")
	 */
	public void setCs2asFile(String cs2asFile) {
		this.cs2asFile = cs2asFile;
	}

	/**
	 * The projectName relative path to the CS2CS rules. (e.g. "model/BaseCS2CS.ocl")
	 */
	public void setCs2csFile(String cs2csFile) {
		this.cs2csFile = cs2csFile;
	}
}
