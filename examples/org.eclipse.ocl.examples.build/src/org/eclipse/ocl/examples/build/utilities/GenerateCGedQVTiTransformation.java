/*******************************************************************************
 * Copyright (c) 2015, 2019 Willink Transformations Ltd., University of York and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     Adolfo Sanchez-Barbudo Herrera (University of York) - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.build.utilities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.mwe.core.WorkflowContext;
import org.eclipse.emf.mwe.core.issues.Issues;
import org.eclipse.emf.mwe.core.lib.AbstractWorkflowComponent;
import org.eclipse.emf.mwe.core.monitor.ProgressMonitor;
import org.eclipse.emf.mwe.utils.Mapping;
import org.eclipse.emf.mwe2.runtime.Mandatory;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.utilities.XMIUtil;
//import org.eclipse.qvtd.runtime.invocation.TransformationTechnology;
//import org.eclipse.qvtd.runtime.invocation.TransformationTechnology.TransformationException;

public  class GenerateCGedQVTiTransformation extends AbstractWorkflowComponent
{
	private final static @NonNull String BACKSLASH = "/";
	private static final Log LOG = LogFactory.getLog(GenerateCGedQVTiTransformation.class);

	protected String projectName;
	protected String oclFileURI;
	protected List<String> extendedOclFileURIs = new ArrayList<String>();
	protected ResourceSet resourceSet;
	protected String javaFolder = "src-gen/";
	protected String javaPackage = "";
	protected String lookupSolverClassName;
	protected String lookupResultItfName;
	protected String traceabilityPropName = "ast";
	protected Map<?, ?> savingOptions;
	private final @NonNull Map<String, String> packageRenameMap = new HashMap<String, String>();
	protected boolean debug = false;

	/**
	 * Defines a package rename only from some package to another package.
	 */
	public void addPackageRename(Mapping mapping) {
		packageRenameMap.put(mapping.getFrom(), mapping.getTo());
	}

	@Override
	public void checkConfiguration(final Issues issues) {
		// No additional checking configuration
	}

	@Override
	protected void invokeInternal(WorkflowContext ctx, ProgressMonitor monitor, Issues issues) {
		try {
			//			Class<?> txClass = Class.forName("org.eclipse.qvtd.cs2as.compiler.OCL2QVTiTransformationTechnology");
			//			Field txField = txClass.getField("INSTANCE");
			//			TransformationTechnology tx = (TransformationTechnology) txField.get(null);
			/*
			 * Cannot use this until we can guarantee that whatever OCL we build will use a QVTd
			 * that already provides OCL2QVTiTransformationTechnology.
			 */
			// FIXME	TransformationTechnology tx = OCL2QVTiTransformationTechnology.INSTANCE;
			//			Map<@NonNull String, Object> modelMap = new HashMap<@NonNull String, Object>();
			Map<@NonNull String, Object> parametersMap = new HashMap<@NonNull String, Object>();
			parametersMap.put("lookupSolverClassName", lookupSolverClassName);
			parametersMap.put("lookupResultItfName", lookupResultItfName);
			parametersMap.put("javaFolder", javaFolder);
			parametersMap.put("javaPackage", javaPackage);
			//
			parametersMap.put("oclFileURI", oclFileURI);
			parametersMap.put("extendedOclFileURIs", extendedOclFileURIs);
			parametersMap.put("traceabilityPropName", traceabilityPropName);
			parametersMap.put("packageRenames", packageRenameMap);
			parametersMap.put("log", LOG);
			parametersMap.put("debug", debug);
			//
			LOG.info("Transforming " + oclFileURI + " to " + javaFolder + javaPackage);
			throw new UnsupportedOperationException("Not yet available from OCL; use the QVTd version");
			//			tx.execute(ClassUtil.nonNullState(resourceSet), modelMap, parametersMap);
			//		} catch (TransformationException e) {
			//			issues.addError(this, e.getMessage(), null, e.getCause(), null);
		} catch (Exception e) {
			issues.addError(this, "Error while executing " + GenerateCGedQVTiTransformation.class.getName(), null, e, null);
		}
	}

	/**
	 * (Optional) The folder within the project that forms the root of EMF
	 * generated sources. (default is "src-gen/")
	 */
	public void setJavaFolder(final String javaFolder) {
		this.javaFolder = javaFolder.endsWith(BACKSLASH) ? javaFolder : javaFolder.concat(BACKSLASH);
	}

	/**
	 * (Optional) The folder within the project that forms the root of EMF
	 * generated sources. (default is "")
	 */
	public void setJavaPackage(final String javaPackage) {
		this.javaPackage = javaPackage;
	}

	/**
	 * (Mandatory) The OCL document URI corresponding to the CS2AS description
	 */
	@Mandatory
	public void setOclDocURI(final String oclDocURI) {
		this.oclFileURI = oclDocURI;
	}

	/**
	 * (Optional) The OCL document URI/s corresponding to the CS2AS decription
	 * that the mandatory OCLDocURI extends. (default is an empty list)
	 */
	public void addExtendedOclDocURIs(final String extendedOclDocURI) {
		this.extendedOclFileURIs.add(extendedOclDocURI);
	}
	/**
	 * (Mandatory) The fully qualified class name of the Lookup Solver java class
	 */
	@Mandatory
	public void setLookupSolverClassName(final String visitorClassName) {
		this.lookupSolverClassName = visitorClassName;
	}

	/**
	 * (Mandatory) The fully qualified interface name of the Lookup Result java class
	 */
	@Mandatory
	public void setLookupResultItfName(final String namedElementItfName) {
		this.lookupResultItfName = namedElementItfName;
	}

	/**
	 * A mandatory ResourceSet
	 */
	@Mandatory
	public void setResourceSet(final ResourceSet resourceSet) {
		this.resourceSet = resourceSet;
	}

	/**
	 * An optional saving options used when serialising EMF resources. (default is {@link XMIUtil#createSaveOptions()})
	 */
	public void setSavingOptions(final Map<?, ?> savingOptions) {
		this.savingOptions = savingOptions;
	}


	/**
	 * An optional CS2AS traceability property name (default is "ast")
	 */
	public void setTracePropertyName(final String tracePropName) {
		this.traceabilityPropName = tracePropName;
	}

	/**
	 * An optional flag to activate debugging (default is false)
	 */
	public void setDebug(boolean debug) {
		this.debug = debug;
	}


}
