/*******************************************************************************
 * Copyright (c) 2014, 2018 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     R.Dvorak and others - QVTo debugger framework
 *     E.D.Willink - revised API for OCL debugger framework
 *******************************************************************************/
package org.eclipse.ocl.examples.debug.ui.launching;

import org.apache.log4j.Logger;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.ui.AbstractLaunchConfigurationTab;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal;
import org.eclipse.ocl.pivot.internal.utilities.OCLInternal;
import org.eclipse.ocl.pivot.utilities.OCL;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.FileEditorInput;

public abstract class AbstractMainTab extends AbstractLaunchConfigurationTab
{
	private static final Logger logger = Logger.getLogger(AbstractMainTab.class);

	protected @Nullable OCLInternal ocl;		// FIXME Add a dispose() when not visible for a long time
	
	/**
	 * Internal flag to suppress redundant recursive updates while initializing controls.
	 */
	protected boolean initializing = false;
	
	@Override
	public void dispose() {
		OCL ocl2 = ocl;
		if (ocl2 != null) {
			ocl2.dispose();
			ocl = null;
		}
		super.dispose();
	}

	protected @NonNull EnvironmentFactoryInternal getEnvironmentFactory() {
		OCLInternal ocl2 = ocl;
		if (ocl2 == null) {
			ocl = ocl2 = OCLInternal.newInstance();
		}
		return ocl2.getEnvironmentFactory();
	}

	public @NonNull String getName() {
		return "Main";
	}

	protected boolean launchConfigurationExists(@NonNull String name) {
		ILaunchConfiguration[] cs = new ILaunchConfiguration[]{};
		try {
			cs = getLaunchManager().getLaunchConfigurations();
		}
		catch (CoreException ex) {
			logger.error("Failed to access ILaunchConfiguration", ex);
		}
		for (int i = 0; i < cs.length; i++) {
			if (name.equals(cs[i].getName())){
				return true;
			}
		}
		return false;
	}

	// Return a new launch configuration name that does not
	// already exists
	protected String newLaunchConfigurationName(@NonNull String fileName) {
		if (!launchConfigurationExists(fileName)) {
			return fileName;
		}
		for (int i = 1; true; i++) {
			String configurationName = fileName + " (" + i + ")";
			if (!launchConfigurationExists(configurationName)) {
				return configurationName;
			}
		}
	}

	public void setDefaults(ILaunchConfigurationWorkingCopy configuration) {
		IWorkbench workbench = PlatformUI.getWorkbench();
		if (workbench != null) {
			IWorkbenchWindow workbenchWindow = workbench.getActiveWorkbenchWindow();
			if (workbenchWindow != null) {
				IWorkbenchPage activePage = workbenchWindow.getActivePage();
				if (activePage != null) {
					IFile iFile = null;
					ISelection selection = activePage.getSelection();
					if (selection instanceof IStructuredSelection) {
						Object selectedObject = ((IStructuredSelection)selection).getFirstElement();
						if (selectedObject instanceof IAdaptable) {
							@Nullable Object adaptedObject = ((IAdaptable) selectedObject).getAdapter(IFile.class);
							if (adaptedObject instanceof IFile) {
								iFile = (IFile) adaptedObject;
							}
						}
					}
					if (iFile == null) {
						IEditorPart activeEditor = activePage.getActiveEditor();
						if (activeEditor != null) {
							IEditorInput editorInput = activeEditor.getEditorInput();
							if (editorInput instanceof FileEditorInput) {
								iFile = ((FileEditorInput)editorInput).getFile();
							}
						}
					}
					if (iFile != null) {
						String fileName = iFile.getName();
						if (fileName.length() > 0){
							configuration.rename(newLaunchConfigurationName(fileName));
							setDefaults(configuration, iFile);
						}
					}
				}
			}
		}
	}

	protected abstract void setDefaults(@NonNull ILaunchConfigurationWorkingCopy configuration, @NonNull IFile iFile);

	@Override
	public void updateLaunchConfigurationDialog() {
		if (!initializing) {
			super.updateLaunchConfigurationDialog();
		}
	}
}
