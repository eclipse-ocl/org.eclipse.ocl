/*******************************************************************************
 * Copyright (c) 2012, 2019 Willink Transformations and others.
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.URIHandlerImpl;
import org.eclipse.emf.mwe.core.WorkflowContext;
import org.eclipse.emf.mwe.core.issues.Issues;
import org.eclipse.emf.mwe.core.lib.AbstractWorkflowComponent;
import org.eclipse.emf.mwe.core.monitor.ProgressMonitor;
import org.eclipse.emf.mwe.utils.Mapping;
import org.eclipse.jdt.annotation.NonNull;
//import org.eclipse.m2m.internal.qvt.oml.TransformationExecutorBlackboxRegistry;		-- not yet released
import org.eclipse.m2m.qvt.oml.BasicModelExtent;
import org.eclipse.m2m.qvt.oml.ExecutionContextImpl;
import org.eclipse.m2m.qvt.oml.ExecutionDiagnostic;
import org.eclipse.m2m.qvt.oml.ModelExtent;
import org.eclipse.m2m.qvt.oml.TransformationExecutor;
import org.eclipse.m2m.qvt.oml.util.StringBufferLog;
import org.eclipse.ocl.pivot.internal.utilities.OCLInternal;
import org.eclipse.ocl.pivot.internal.utilities.PivotUtilInternal;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.LabelUtil;
import org.eclipse.ocl.pivot.utilities.XMIUtil;

public class QVToTransformationExecutor extends AbstractWorkflowComponent
{
	private static final Logger logger = Logger.getLogger(QVToTransformationExecutor.class);

	private ResourceSet resourceSet = null;
	private String uri = null;
	private List<String> blackboxes = new ArrayList<String>();
	private List<String> ins = new ArrayList<String>();
	private Map<String, Object> configs = new HashMap<String, Object>();
	private String out = null;
	private String trace = null;
	private String encoding = "UTF-8"; //$NON-NLS-1$
	private boolean validate = true;

	/**
	 * Define a mapping from a source UML/CMOF file to a UML file with resolved assignments.
	 */
	public void addConfig(final Mapping uriMap) {
		final String key = uriMap.getFrom();
		final Object value = uriMap.getTo();
		configs.put(key, value);
	}

	public void addBlackbox(String className) {
		blackboxes.add(className);
	}

	public void addIn(String fileName) {
		ins.add(fileName);
	}

	@Override
	public void checkConfiguration(Issues issues) {
		if (getUri() == null) {
			issues.addError(this, "uri not specified.");
		}
	}

	public String getEncoding() {
		return encoding;
	}

	public String getOut() {
		return out;
	}

	public ResourceSet getResourceSet() {
		if (resourceSet == null) {
			resourceSet = new ResourceSetImpl();
		}
		return resourceSet;
	}

	public String getTrace() {
		return trace;
	}

	public String getUri() {
		return uri;
	}

	public boolean getValidate() {
		return validate;
	}

	/**
	 * Clients may override to do any configuration
	 * properties initialization
	 *
	 * @return creates a context to be used by the transformation
	 */
	protected void initializeConfigurationProperties(ExecutionContextImpl context) {
		for (String key : configs.keySet()) {
			Object value = configs.get(key);
			context.setConfigProperty(key, value);
		}
	}

	@Override
	protected void invokeInternal(WorkflowContext ctx, ProgressMonitor monitor, Issues issues) {
		String uri = getUri();
		URI txURI = URI.createURI(uri, true);
		logger.info("Loading '" + txURI + "'");
		for (String className : blackboxes) {
			@SuppressWarnings("unused") Class<?> blackbox;
			try {
				blackbox = Class.forName(className);
			} catch (ClassNotFoundException e) {
				issues.addError(this, "Failed to load blackbox '" + className + "'", className, e, null);
				return;
			}
			//			TransformationExecutorBlackboxRegistry.INSTANCE.registerModules(blackbox);
		}
		TransformationExecutor transformationExecutor = new TransformationExecutor(txURI);
		Diagnostic diagnostic = transformationExecutor.loadTransformation();
		if (diagnostic.getSeverity() != Diagnostic.OK) {
			StringBuilder s = new StringBuilder();
			s.append("Failed to load ");
			s.append(txURI);
			for (Diagnostic child : diagnostic.getChildren()) {
				s.append("\n  " + child.getMessage());
			}
			issues.addError(this, s.toString(), txURI, null, null);
			return;
		}

		ResourceSet resourceSet = getResourceSet();
		List<@NonNull ModelExtent> modelExtents = new ArrayList<@NonNull ModelExtent>();
		for (String in : ins) {
			URI inURI = URI.createURI(in, true);
			logger.info("Loading '" + inURI + "'");
			Resource inResource = resourceSet.getResource(inURI, true);
			if (inResource.getErrors().size() > 0) {
				issues.addError(this, "Failed to load", inURI, null, null);
				return;
			}
			modelExtents.add(new BasicModelExtent(inResource.getContents()));
		}

		if (out != null) {
			modelExtents.add(new BasicModelExtent());
		}

		//		String traceUri = trace != null ? URI.createPlatformResourceURI(trace, true).toString() : null;


		StringBufferLog qvtoLog = new StringBufferLog();
		try {
			logger.info("Executing transformation '" + uri + "'");
			ExecutionContextImpl executionContext = new ExecutionContextImpl();
			executionContext.setLog(qvtoLog);
			initializeConfigurationProperties(executionContext);
			//			executionContext.setMonitor();
			ExecutionDiagnostic executionDiagnostic = transformationExecutor.execute(executionContext, modelExtents.toArray(new ModelExtent[modelExtents.size()]));
			if (executionDiagnostic.getSeverity() != Diagnostic.OK) {
				StringBuilder s = new StringBuilder();
				s.append("Failed to execute ");
				s.append(txURI);
				s.append(": ");
				s.append(executionDiagnostic.getMessage());
				for (Diagnostic child : diagnostic.getChildren()) {
					s.append("\n  " + child.getMessage());
				}
				issues.addError(this, s.toString(), txURI, null, null);
				return;
			}
		} catch (Exception e) {
			issues.addError(this, "Failed to launch transformation", txURI, e, null);
			return;
		}

		if (out != null) {
			URI outURI = URI.createURI(out, true);
			try {
				String qvtoLogContents = qvtoLog.getContents().trim();
				if (qvtoLogContents.length() > 0) {
					logger.info("Creating output:  '" + outURI + "'\n" + qvtoLogContents);
				}
				else {
					logger.info("Creating output:  '" + outURI);
				}
				XMLResource outResource = (XMLResource) resourceSet.createResource(outURI, null);
				outResource.getContents().addAll(ClassUtil.nullFree(modelExtents.get(modelExtents.size()-1).getContents()));
				outResource.setEncoding(getEncoding());
				Map<Object, Object> options = XMIUtil.createSaveOptions();
				options.put(XMLResource.OPTION_USE_ENCODED_ATTRIBUTE_STYLE, Boolean.TRUE);
				options.put(XMLResource.OPTION_URI_HANDLER, new URIHandlerImpl.PlatformSchemeAware());
				options.put(XMLResource.OPTION_SCHEMA_LOCATION, Boolean.TRUE);
				XMIUtil.retainLineWidth(options, outResource);
				outResource.save(options);
				if (validate) {
					OCLInternal.adapt(resourceSet);
					validate(outResource);
				}
			} catch (IOException e) {
				issues.addError(this, "Failed to save ", outURI, e, null);
				return;
			}
		}
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	/**
	 * @param uri the QVTo output URI
	 */
	public void setOut(String out) {
		this.out = out;
	}

	/**
	 * @param resourceSet the ResourceSet to re-use
	 */
	public void setResourceSet(ResourceSet resourceSet) {
		this.resourceSet = resourceSet;
	}

	/**
	 * @param uri the QVTo trace URI
	 */
	public void setTrace(String trace) {
		this.trace = trace;
	}

	/**
	 * @param uri the QVTo Transformations URI
	 */
	public void setUri(String uri) {
		this.uri = uri;
	}

	/**
	 * @param validate True to validate the output model
	 */
	public void setValidate(boolean validate) {
		this.validate = validate;
	}

	public static void validate(@NonNull Resource resource) throws IOException {
		for (EObject eObject : resource.getContents()) {
			Map<Object, Object> validationContext = LabelUtil.createDefaultContext(Diagnostician.INSTANCE);
			Resource eResource = ClassUtil.nonNullState(eObject.eResource());
			PivotUtilInternal.getEnvironmentFactory(eResource);	// FIXME oclIsKindOf fails because ExecutableStandardLibrary.getMetaclass is bad
			Diagnostic diagnostic = Diagnostician.INSTANCE.validate(eObject, validationContext);
			List<Diagnostic> children = diagnostic.getChildren();
			if (children.size() <= 0) {
				return;
			}
			StringBuilder s = new StringBuilder();
			s.append(children.size() + " validation errors");
			for (Diagnostic child : children){
				s.append("\n\t");
				s.append(child.getMessage());
			}
			throw new IOException(s.toString());
		}
	}

}
