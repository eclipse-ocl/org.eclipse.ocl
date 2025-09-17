/*******************************************************************************
 * Copyright (c) 2011, 2021 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.build.utilities;

import org.apache.log4j.Logger;
import org.eclipse.emf.mwe.core.WorkflowContext;
import org.eclipse.emf.mwe.core.issues.Issues;
import org.eclipse.emf.mwe.core.lib.AbstractWorkflowComponent;
import org.eclipse.emf.mwe.core.monitor.ProgressMonitor;
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.ThreadLocalExecutor;

/**
 * Suspends an OCL instance created by OCLInstanceSetup to make way for a distinct OCL instance
 * pending an OCLInstanceResume and eventual OCLInstanceDispose.
 */
public class OCLInstanceSuspend extends AbstractWorkflowComponent
{
	private Logger log = Logger.getLogger(getClass());
	private OCLInstanceSetup oclInstanceSetup = null;

	public OCLInstanceSuspend() {}

	@Override
	public void checkConfiguration(Issues issues) {
		if (oclInstanceSetup == null) {
			issues.addError(this, "oclInstanceSetup not specified.");
		}
	}

	@Override
	protected void invokeInternal(WorkflowContext ctx, ProgressMonitor monitor, Issues issues) {
		EnvironmentFactoryInternal environmentFactory = oclInstanceSetup.getOCL().getEnvironmentFactory();
		log.info("Suspending OCL Instance - " + NameUtil.debugSimpleName(environmentFactory));
		ThreadLocalExecutor.detachEnvironmentFactory(environmentFactory);
	}

	/**
	 * Define an OCLInstanceSetup to suspend the OCL state.
	 */
	public void setOclInstanceSetup(OCLInstanceSetup oclInstanceSetup) {
		this.oclInstanceSetup = oclInstanceSetup;
	}
}
