/*******************************************************************************
 * Copyright (c) 2010, 2020 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.build.utilities;


import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.eclipse.emf.codegen.ecore.generator.Generator;
import org.eclipse.emf.codegen.ecore.genmodel.GenJDKLevel;
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.codegen.ecore.genmodel.generator.GenBaseGeneratorAdapter;
import org.eclipse.emf.codegen.ecore.genmodel.util.GenModelUtil;
import org.eclipse.emf.common.util.BasicMonitor;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.Monitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.mwe.core.ConfigurationException;
import org.eclipse.emf.mwe.core.WorkflowContext;
import org.eclipse.emf.mwe.core.issues.Issues;
import org.eclipse.emf.mwe.core.lib.AbstractWorkflowComponent;
import org.eclipse.emf.mwe.core.monitor.ProgressMonitor;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.examples.codegen.genmodel.OCLGenModelUtil;
import org.eclipse.ocl.pivot.internal.utilities.OCLInternal;
import org.eclipse.ocl.pivot.utilities.OCL;
import org.eclipse.ocl.pivot.utilities.XMIUtil;

/**
 * Performs a Generate Model on the designated <tt>genmodel</tt> genModel.
 */
public class GenerateModel extends AbstractWorkflowComponent {
	private Logger log = Logger.getLogger(getClass());
	private ResourceSet resourceSet = null; // Optional ResourceSet to re-use
	private OCLInstanceSetup oclInstanceSetup = null;
	protected String genModel; 				// URI of the genmodel
	protected boolean showProgress = false; // Set true to show genmodel new tasks
	private boolean clearResourceSet = true;// Set to false to preserve the resource set.

	public GenerateModel() {
		super();
//		BaseLinkingService.DEBUG_RETRY.setState(true);
	}

	@Override
	public void checkConfiguration(Issues issues) {
		if (genModel == null) {
			issues.addError(this, "uri not specified.");
		}
		if ((resourceSet != null) && (oclInstanceSetup != null)) {
			issues.addError(this, "only one of oclInstanceSetup and resourceSet may be specified.");
		}
		else if ((resourceSet == null) && (oclInstanceSetup == null)) {
			issues.addError(this, "one of oclInstanceSetup and resourceSet must be specified.");
		}
	}

	private void gatherUsedGenPackages(@NonNull Set<GenPackage> allUsedGenPackages, GenPackage anotherUsedGenPackage) {
		if (allUsedGenPackages.add(anotherUsedGenPackage)) {
			GenModel anotherGenModel = anotherUsedGenPackage.getGenModel();
			if (anotherGenModel != null) {		// FIXME diagnose null or is it always downstream of an IOE
				for (GenPackage usedGenPackage : anotherGenModel.getUsedGenPackages()) {
					gatherUsedGenPackages(allUsedGenPackages, usedGenPackage);
				}
			}
		}
	}

	public String getGenModel() {
		return genModel;
	}

	private @NonNull OCLInternal getOCL() {
		if (oclInstanceSetup != null) {
			return oclInstanceSetup.getOCL();
		}
		else {
			return OCLInternal.newInstance(getResourceSet());
		}
	}

	public ResourceSet getResourceSet() {
		ResourceSet resourceSet2 = resourceSet;
		if (resourceSet2 == null) {
			resourceSet = resourceSet2 = oclInstanceSetup != null ? oclInstanceSetup.getResourceSet() : new ResourceSetImpl();
		}
		return resourceSet2;
	}

	@Override
	public void invokeInternal(WorkflowContext ctx, ProgressMonitor arg1, Issues issues) {
		OCLGenModelUtil.initializeGeneratorAdapterFactoryRegistry();
		URI fileURI = URI.createPlatformResourceURI(genModel, true);
		log.info("Generating Ecore Model using '" + fileURI + "'");
		ResourceSet resourceSet = getResourceSet();
		if (resourceSet instanceof ResourceSetImpl) {
			ResourceSetImpl resourceSetImpl = (ResourceSetImpl) resourceSet;
			Map<URI, Resource> uriResourceMap = resourceSetImpl.getURIResourceMap();
			if (uriResourceMap != null) {
				uriResourceMap.clear();
			}
		}

		if (isClearResourceSet() && !resourceSet.getResources().isEmpty()) {
			throw new IllegalStateException("clearResourceSet is not supported see Bug 529484");
			//			resourceSet.getResources().clear();
		}

		Resource resource = resourceSet.getResource(fileURI, true);
		// EcoreUtil.resolveAll(resourceSet); -- genModel can fail if
		// proxies resolved here
		// problem arises if genmodel has an obsolete feature for a feature
		// moved up the inheritance hierarchy
		// since the proxy seems to be successfully resolved giving a double
		// feature
		ResourceUtils.checkResourceSet(resourceSet);
		//		MetamodelManager metamodelManager = ElementUtil.findMetamodelManager(resourceSet);
		//		metamodelManager.setAutoLoadPivotMetamodel(false);
		EObject eObject = resource.getContents().get(0);
		if (!(eObject instanceof GenModel)) {
			throw new ConfigurationException("No GenModel found in '" + resource.getURI() + "'");
		}
		GenModel genModel = (GenModel) eObject;
		List<GenPackage> usedGenPackages = genModel.getUsedGenPackages();
		Set<GenPackage> allOldUsedGenPackages = new HashSet<>();
		for (GenPackage usedGenPackage : usedGenPackages) {
			gatherUsedGenPackages(allOldUsedGenPackages, usedGenPackage);
		}
		allOldUsedGenPackages.removeAll(usedGenPackages);		// Indirectly usedGenPackages
		genModel.reconcile();
		allOldUsedGenPackages.retainAll(usedGenPackages);		// Promoted indirect to direct usedGenPackages
		try {
			usedGenPackages.removeAll(allOldUsedGenPackages);
			log.info("Saving reconciled '" + fileURI + "'");
			Map<Object, Object> saveOptions = XMIUtil.createSaveOptions((XMLResource)resource);
			saveOptions.put(XMLResource.OPTION_ENCODING, "UTF-8");
			resource.save(saveOptions);
			usedGenPackages.addAll(allOldUsedGenPackages);
		} catch (IOException e) {
			throw new ConfigurationException("Failed to save '" + fileURI + "'", e);
		}
		ResourceUtils.checkResourceSet(resourceSet);
		// genModel.setCanGenerate(true);
		// validate();



		genModel.setValidateModel(true); // The more checks the better
		//		genModel.setCodeFormatting(true); // Normalize layout
		genModel.setForceOverwrite(false); // Don't overwrite read-only
		// files
		genModel.setCanGenerate(true);
		// genModel.setFacadeHelperClass(null); // Non-null gives JDT
		// default NPEs
		//		genModel.setFacadeHelperClass(ASTFacadeHelper.class.getName()); // Bug 308069
		// genModel.setValidateModel(true);
		genModel.setBundleManifest(false); // New manifests should be
		// generated manually
		genModel.setUpdateClasspath(false); // New class-paths should be
		// generated manually
		if (genModel.getComplianceLevel().compareTo(GenJDKLevel.JDK50_LITERAL) < 0) {
			genModel.setComplianceLevel(GenJDKLevel.JDK50_LITERAL);
		}
		// genModel.setRootExtendsClass("org.eclipse.emf.ecore.impl.MinimalEObjectImpl$Container");
		OCL ocl = getOCL();
		Diagnostic diagnostic = genModel.diagnose();
		reportDiagnostics(issues, diagnostic);
		if (this.oclInstanceSetup == null) {
			ocl.dispose(true);
		}
		ocl = null;

		/*
		 * JavaModelManager.getJavaModelManager().initializePreferences();
		 * new
		 * JavaCorePreferenceInitializer().initializeDefaultPreferences();
		 *
		 * GenJDKLevel genSDKcomplianceLevel =
		 * genModel.getComplianceLevel(); String complianceLevel =
		 * JavaCore.VERSION_1_5; switch (genSDKcomplianceLevel) { case
		 * JDK60_LITERAL: complianceLevel = JavaCore.VERSION_1_6; case
		 * JDK14_LITERAL: complianceLevel = JavaCore.VERSION_1_4; default:
		 * complianceLevel = JavaCore.VERSION_1_5; } // Hashtable<?,?>
		 * defaultOptions = JavaCore.getDefaultOptions(); //
		 * JavaCore.setComplianceOptions(complianceLevel, defaultOptions);
		 * // JavaCore.setOptions(defaultOptions);
		 */

		//		Generator generator = new Generator();
		//		generator.setInput(genModel);
		Generator generator = GenModelUtil.createGenerator(genModel);
		Monitor monitor = showProgress ? new LoggerMonitor(log)
			: new BasicMonitor();
		diagnostic = generator.generate(genModel,
			GenBaseGeneratorAdapter.MODEL_PROJECT_TYPE, monitor);
		reportDiagnostics(issues, diagnostic);
	}

	protected void reportDiagnostics(Issues issues, Diagnostic diagnostic) {
		int severity = diagnostic.getSeverity();
		if (severity != Diagnostic.OK) {
			List<Diagnostic> children = diagnostic.getChildren();
			if (children.size() > 0) {
				for (Diagnostic child : children) {
					severity = child.getSeverity();
					@SuppressWarnings("unchecked") List<Object> data = (List<Object>) child.getData();
					Throwable throwable = null;
					String message;
					if ((data.size() == 1) && (data.get(0) instanceof Throwable)) {
						throwable = (Throwable) data.get(0);
						data = null;
						message = child.getMessage();
					}
					else {
						message = child.toString();
					}
					if (severity == Diagnostic.ERROR) {
						issues.addError(this, message, null, null, throwable, data);
					}
					else if (severity == Diagnostic.WARNING) {
						issues.addWarning(this, message, null, null, throwable, data);
					}
				}
			}
			else {
				if (severity == Diagnostic.ERROR) {
					issues.addError(this, diagnostic.toString());
				}
				else if (severity == Diagnostic.WARNING) {
					issues.addWarning(this, diagnostic.toString());
				}
			}
		}
	}

	public boolean isShowProgress() {
		return showProgress;
	}

	/**
	 * Define the genModel from which to generate a Java model.
	 *
	 * @param genModel URI of the genmodel
	 */
	public void setGenModel(String genModel) {
		this.genModel = genModel;
	}

	/**
	 * Define an OCLInstanceSetup to supervise the OCL state. If omitted a local OCL is
	 * created using the resourceSet. Is specified, an OCL may be shared by multiple workflow
	 * components. The OCLInstanceSetup workflow creas, the OCLInstanceDispose workflow disposes.
	 */
	public void setOclInstanceSetup(OCLInstanceSetup oclInstanceSetup) {
		this.oclInstanceSetup = oclInstanceSetup;
	}

	/**
	 * Define a ResourceSet to be used for for required resources. This allows the same ResourceSet to
	 * be shared for multiple modeling purposes.
	 */
	public void setResourceSet(ResourceSet resourceSet) {
		this.resourceSet = resourceSet;
	}

	/**
	 * Whether to display GenModel newTask activity.
	 *
	 * @param showProgress
	 */
	public void setShowProgress(boolean showProgress) {
		this.showProgress = showProgress;
	}


	public boolean isClearResourceSet() {
		return clearResourceSet;
	}


	/**
	 * Set to <code>false</code> to preserve the resource set. <code>true</code> by default
	 * @param clearResourceSet
	 */
	public void setClearResourceSet(boolean clearResourceSet) {
		this.clearResourceSet = clearResourceSet;
	}
}
