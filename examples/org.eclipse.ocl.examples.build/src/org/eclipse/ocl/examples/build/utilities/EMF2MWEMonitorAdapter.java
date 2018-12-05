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

import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.Monitor;
import org.eclipse.emf.mwe.core.monitor.ProgressMonitor;

/**
 * Enable Acceleo's EMF Monitor to interact with MWE's ProgressMonitor.
 */
public class EMF2MWEMonitorAdapter implements Monitor
{
	private ProgressMonitor monitor;
	
	public EMF2MWEMonitorAdapter (ProgressMonitor monitor) {
		this.monitor = monitor;
	}
	
	@Override
	public void beginTask(String name, int totalWork) {
		monitor.beginTask(name, totalWork);
	}

	@Override
	public void clearBlocked() {
	}

	@Override
	public void done() {
		monitor.done();
	}

	public void finished(Object element, Object context){		
		monitor.finished(element, context);
	}

	@Override
	public void internalWorked(double work) {
		monitor.internalWorked(work);
	}

	@Override
	public boolean isCanceled() {
		return monitor.isCanceled();
	}

	public void postTask(Object element, Object context) {
		monitor.postTask(element, context);
	}

	public void preTask(Object element, Object context) {
		monitor.preTask(element, context);
	}

	@Override
	public void setBlocked(Diagnostic diagnostic) {
	}

	@Override
	public void setCanceled(boolean value) {
		monitor.setCanceled(value);
	}

	@Override
	public void setTaskName(String name) {
		monitor.setTaskName(name);
	}

	public void started(Object element, Object context){
		monitor.started(element, context);
	}

	@Override
	public void subTask(String name) {
		monitor.subTask(name);
	}

	@Override
	public void worked(int work) {
		monitor.worked(work);
	}
}
