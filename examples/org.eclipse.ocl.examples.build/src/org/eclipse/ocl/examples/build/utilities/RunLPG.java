/*******************************************************************************
 * Copyright (c) 2012, 2018 Willink Transformations and others.
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
import java.io.IOException;

import org.apache.log4j.Logger;
import org.eclipse.emf.mwe.core.WorkflowContext;
import org.eclipse.emf.mwe.core.issues.Issues;
import org.eclipse.emf.mwe.core.lib.AbstractWorkflowComponent;
import org.eclipse.emf.mwe.core.monitor.ProgressMonitor;
import org.eclipse.m2m.qvt.oml.ExecutionContextImpl;

public class RunLPG extends AbstractWorkflowComponent
{
	private static final Logger logger = Logger.getLogger(RunLPG.class);
	
	private String in = null;	
	private String lpgExe = null;	
	private String lpgIncs = null;	
	private String lpgOpts = "";	

	@Override
	public void checkConfiguration(Issues issues) {
		if (getIn() == null) {
			issues.addError(this, "in not specified.");
		}
		if (lpgExe == null) {
			issues.addError(this, "lpgExe not specified.");
		}
		if (lpgIncs == null) {
			issues.addError(this, "lpgIncs not specified.");
		}
	}

	public String getIn() {
		return in;
	}

	/**
	 * Clients may override to do any configuration 
	 * properties initialization
	 * 
	 * @return creates a context to be used by the transformation
	 */
	protected void initializeConfigurationProperties(ExecutionContextImpl context) {
		// do nothing
	}

	@Override
	protected void invokeInternal(WorkflowContext ctx, ProgressMonitor monitor, Issues issues) {
		File inFile = new File(in);
		String command = "\"" + lpgExe + "\" " + lpgOpts + " -include-directory=\"" + lpgIncs + "\" \"" + inFile.getName() + "\"";
		logger.info("Running '" + command + "' in '" + inFile.getParentFile() + "'");
		try {
			Runtime.getRuntime().exec(command, null, inFile.getParentFile());
		} catch (IOException e) {
			issues.addError(this, "Failed to run LPG", in, e, null);
			return;
		}
	}
	
	public void setIn(String fileName) {
		this.in = fileName;
	}
	
	public void setLpgExe(String lpgExe) {
		this.lpgExe = lpgExe;
	}
	
	public void setLpgIncs(String lpgIncs) {
		this.lpgIncs = lpgIncs;
	}
	
	public void setLpgOpts(String lpgOpts) {
		this.lpgOpts = lpgOpts;
	}
}
