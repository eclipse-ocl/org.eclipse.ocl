/*******************************************************************************
 * Copyright (c) 2010, 2018 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.build.utilities;

import java.io.File;
import java.io.FileWriter;
import java.net.URL;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage;
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.Resource.Diagnostic;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.mwe.core.WorkflowContext;
import org.eclipse.emf.mwe.core.issues.Issues;
import org.eclipse.emf.mwe.core.lib.AbstractWorkflowComponent;
import org.eclipse.emf.mwe.core.monitor.ProgressMonitor;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.examples.codegen.oclinecore.OCLinEcoreTables;
import org.eclipse.ocl.pivot.internal.library.StandardLibraryContribution;
import org.eclipse.ocl.pivot.internal.manager.PivotMetamodelManager;
import org.eclipse.ocl.pivot.internal.resource.StandaloneProjectMap;
import org.eclipse.ocl.pivot.internal.utilities.OCLInternal;
import org.eclipse.ocl.pivot.model.OCLstdlib;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.PivotUtil;

/**
 * Generates the javaFolder/'javaPackageName'Tables.java file defining
 * the static dispatch tables for the OCL executor.
 */
public class Model2tablesGenerator extends AbstractWorkflowComponent
{
	private Logger log = Logger.getLogger(getClass());
	private ResourceSet resourceSet = null;
	private OCLInstanceSetup oclInstanceSetup = null;
	private OCLInternal ocl = null;
	private boolean genOCLstdlib = false;
	protected String genModelFile;

	@Override
	public void checkConfiguration(Issues issues) {
		if (genModelFile == null) {
			issues.addError(this, "genModel not specified.");
		}
		if ((resourceSet != null) && (oclInstanceSetup != null)) {
			issues.addError(this, "only one of oclInstanceSetup and resourceSet may be specified.");
		}
		else if ((resourceSet == null) && (oclInstanceSetup == null)) {
			issues.addError(this, "one of oclInstanceSetup and resourceSet must be specified.");
		}
	}

	private @NonNull OCLInternal getOCL() {
		if (oclInstanceSetup != null) {
			ocl = oclInstanceSetup.getOCL();
		}
		else {
			ocl = OCLInternal.newInstance(getResourceSet());
		}
		return ocl;
	}

	public @NonNull ResourceSet getResourceSet() {
		ResourceSet resourceSet2 = resourceSet;
		if (resourceSet2 == null) {
			resourceSet = resourceSet2 = ocl != null ? ocl.getResourceSet() : new ResourceSetImpl();
		}
		return resourceSet2;
	}

	@Override
	public void invokeInternal(WorkflowContext ctx, ProgressMonitor arg1, Issues issues) {
		URI genModelURI = URI.createPlatformResourceURI(genModelFile, true);
		OCLInternal ocl = getOCL();
		ResourceSet resourceSet = ocl.getResourceSet();
		PivotMetamodelManager metamodelManager = (PivotMetamodelManager)ocl.getMetamodelManager();
		if (genOCLstdlib) {
		//	final EnvironmentFactoryAdapter adapter = EnvironmentFactoryAdapter.find(resourceSet);
		//	if (adapter != null) {
			metamodelManager.setLibraryLoadInProgress(true);
		//	}
		}
		log.info("Loading Gen Model '" + genModelURI);
		StandardLibraryContribution savedContribution = null;
		if (genOCLstdlib) {
			savedContribution = StandardLibraryContribution.REGISTRY.put(OCLstdlib.STDLIB_URI, new StandardLibraryContribution()
			{

				@Override
				public @NonNull StandardLibraryContribution getContribution() {
					return this;
				}

				@Override
				public @NonNull Resource getResource() {
					return new ResourceImpl(URI.createURI("dummy-library"));
				}
			});
		}

		try {
			StandaloneProjectMap projectMap = (StandaloneProjectMap) ocl.getProjectManager(); // new StandaloneProjectMap(false);
		//	projectMap.initializeResourceSet(resourceSet);
			resourceSet.getPackageRegistry().put(GenModelPackage.eNS_URI, GenModelPackage.eINSTANCE);
			Resource genModelResource = resourceSet.getResource(genModelURI, true);
			List<Diagnostic> genModelErrors = ClassUtil.nonNullEMF(genModelResource.getErrors());
			String errorsString = PivotUtil.formatResourceDiagnostics(genModelErrors, "Loading " + genModelURI, "\n");
			if (errorsString != null) {
				issues.addError(this, errorsString, null, null, null);
				return;
			}
			GenModel genModel = (GenModel) genModelResource.getContents().get(0);
			String modelDirectory = genModel.getModelDirectory();
			String modelProjectDirectory = genModel.getModelProjectDirectory();
			@NonNull String modelProject = modelProjectDirectory.substring(1);
			String folderPath = modelDirectory.substring(modelProjectDirectory.length());
			URI locationURI = projectMap.getLocation(modelProject);
			if (locationURI == null) {
				issues.addError(this, "No location URI for " + modelProjectDirectory, null, null, null);
				return;
			}
			URL url = new URL(locationURI.toString());
			java.net.URI uri = url.toURI();
			File targetFolder = new File(uri.getRawPath() + folderPath);
			log.info("Generating to '" + targetFolder + "'");
			List<GenPackage> genPackages = genModel.getAllGenPackagesWithClassifiers();
			for (@SuppressWarnings("null")@NonNull GenPackage genPackage : genPackages) {
				OCLinEcoreTables generateTables = new OCLinEcoreTables(genPackage);
				String tablesClass = generateTables.getTablesClassName();
				String dir = genPackage.getQualifiedPackageName().replace(".", "/");
				generateTables.generateTablesClass(null);
				String str = generateTables.toString();
				FileWriter testFile = new FileWriter(new File(targetFolder, dir + "/" + tablesClass + ".java"));
				testFile.append(str);
				testFile.close();
			}
		} catch (Exception e) {
			throw new RuntimeException("Problems running " + getClass().getSimpleName(), e);
		} finally {
			if (this.oclInstanceSetup == null) {
				ocl.dispose(true);
			}
			ocl = null;
			if (savedContribution != null) {
				StandardLibraryContribution.REGISTRY.put(OCLstdlib.STDLIB_URI, savedContribution);
			}
		}
	}

	public void setGenModelFile(String genModelFile) {
		this.genModelFile = genModelFile;
	}

	public void setGenOCLstdlib(boolean genOCLstdlib) {
		this.genOCLstdlib = genOCLstdlib;
	}

	/**
	 * Define an OCLInstanceSetup to supervise the OCL state. If omitted a local OCL is
	 * created using the resourceSet. Is specified, an OCL may be shared by multiple workflow
	 * components. The OCLInstanceSetup workflow creas, the OCLInstanceDispose workflow disposes.
	 */
	public void setOclInstanceSetup(OCLInstanceSetup oclInstanceSetup) {
		this.oclInstanceSetup = oclInstanceSetup;
	}

	public void setResourceSet(ResourceSet resourceSet) {
		this.resourceSet = resourceSet;
	}
}
