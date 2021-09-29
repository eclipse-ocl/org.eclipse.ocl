/*******************************************************************************
 * Copyright (c) 2011, 2018 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.build.utilities;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.mwe.core.WorkflowContext;
import org.eclipse.emf.mwe.core.issues.Issues;
import org.eclipse.emf.mwe.core.lib.AbstractWorkflowComponent;
import org.eclipse.emf.mwe.core.monitor.ProgressMonitor;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.ecore.EcoreEnvironment;
import org.eclipse.ocl.pivot.internal.utilities.OCLInternal;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.ThreadLocalExecutor;

/**
 * Initializes an OCL instance.
 */
public class OCLInstanceSetup extends AbstractWorkflowComponent
{
	private Logger log = Logger.getLogger(getClass());
	private ResourceSet resourceSet = null;
	private OCLInternal ocl = null;

	public OCLInstanceSetup() {
	//	StandardLibraryContribution.STANDARD_LIBRARY_CONTRIBUTION.setState(true);
	//	AbstractEnvironmentFactory.ENVIRONMENT_FACTORY_ATTACH.setState(true);
	//	ThreadLocalExecutor.THREAD_LOCAL_ENVIRONMENT_FACTORY.setState(true);
	//	log.info("Registering OCL Resources");
		//		OCLstdlib.install();
		//		PivotPackage.eINSTANCE.getClass();
		//		Resource.Factory.Registry.INSTANCE.getProtocolToFactoryMap().put("pivot", PivotResourceFactoryImpl.INSTANCE);
		URIConverter.URI_MAP.put(URI.createURI(EcoreEnvironment.OCL_STANDARD_LIBRARY_NS_URI), null);
	}

	@Override
	public void checkConfiguration(Issues issues) {
		if (resourceSet == null) {
			issues.addError(this, "resourceSet not specified.");
		}
	}

	public @NonNull OCLInternal getOCL() {
		return ClassUtil.nonNullState(ocl);
	}

	public @NonNull ResourceSet getResourceSet() {
		ResourceSet resourceSet2 = resourceSet;
		if (resourceSet2 == null) {
			resourceSet = resourceSet2 = new ResourceSetImpl();
		}
		return resourceSet2;
	}

	@Override
	protected void invokeInternal(WorkflowContext ctx, ProgressMonitor monitor, Issues issues) {
	//	log.info("Creating OCL Instance");
		assert ThreadLocalExecutor.basicGetEnvironmentFactory() == null;
		ocl = OCLInternal.newInstance(getResourceSet());
		log.info("Created OCL Instance - " + NameUtil.debugSimpleName(getOCL().getEnvironmentFactory()));
	}

	public void setResourceSet(ResourceSet resourceSet) {
		this.resourceSet = resourceSet;
	}

	public void dispose() {
		if (ocl != null) {
			ocl.dispose(true);
		}
	}
}
