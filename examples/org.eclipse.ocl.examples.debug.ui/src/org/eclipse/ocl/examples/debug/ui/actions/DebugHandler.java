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
package org.eclipse.ocl.examples.debug.ui.actions;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IStorage;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationType;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ocl.examples.debug.launching.OCLLaunchConstants;
import org.eclipse.ocl.examples.debug.ui.messages.DebugUIMessages;
import org.eclipse.ocl.pivot.Constraint;
import org.eclipse.ocl.xtext.base.ui.utilities.BaseUIUtil;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.ISources;
import org.eclipse.ui.IStorageEditorInput;
import org.eclipse.ui.IWorkbenchSite;
import org.eclipse.ui.handlers.HandlerUtil;

/**
 * A DebugHandler supports the OCL->Debug command.
 */
public class DebugHandler extends AbstractHandler
{
	public @Nullable Object execute(ExecutionEvent event) throws ExecutionException {
		Object applicationContext = event.getApplicationContext();
		@SuppressWarnings("null")@NonNull IWorkbenchSite site = HandlerUtil.getActiveSiteChecked(event);
		IFile file = getFileFromEditorInput(HandlerUtil.getActiveEditorInputChecked(event));
		if (file == null) {
			return null;
		}
		@SuppressWarnings("null")@NonNull ISelection currentSelection = HandlerUtil.getCurrentSelectionChecked(event);
		Object selectedObject = BaseUIUtil.getSelectedObject(currentSelection, site);
		if (!(selectedObject instanceof EObject)) {
			return null;
		}
		Object shell = HandlerUtil.getVariable(applicationContext, ISources.ACTIVE_SHELL_NAME);
		if (!(shell instanceof Shell)) {
			return null;
		}
		DebugDialog dialog = new DebugDialog((Shell)shell, (EObject)selectedObject);
//		dialog.getShell().setText("Debug Dialog");
		int open = dialog.open();
		if (open != DebugDialog.OK) {
			return null;
		}
		EObject context = dialog.getContext();
		Constraint constraint = dialog.getConstraint();
		
		try {
			ILaunchManager launchManager = DebugPlugin.getDefault().getLaunchManager();
			ILaunchConfigurationType launchConfigurationType = launchManager.getLaunchConfigurationType(OCLLaunchConstants.LAUNCH_CONFIGURATION_TYPE_ID);
			String fileName = file.getFullPath().lastSegment() + ".launch"; //$NON-NLS-1$
			ILaunchConfiguration launchConfiguration = launchConfigurationType.newInstance(file.getProject(), fileName);
			ILaunchConfigurationWorkingCopy workingCopy = launchConfiguration.getWorkingCopy();
			Map<String, Object> attributes = new HashMap<String, Object>();
			attributes.put(OCLLaunchConstants.CONTEXT_OBJECT, context);
			attributes.put(OCLLaunchConstants.EXPRESSION_OBJECT, constraint != null ? constraint.getOwnedSpecification() : null);		
			workingCopy.setAttributes(attributes);		
			workingCopy.launch(ILaunchManager.DEBUG_MODE, new NullProgressMonitor());
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@Nullable
	private IFile getFileFromEditorInput(IEditorInput editorInput) throws ExecutionException {
		IFile file = null;
		if (editorInput instanceof IFileEditorInput){
			file = ((IFileEditorInput)editorInput).getFile();
		} else if (editorInput instanceof IStorageEditorInput) {
			try {
				IStorage storage = ((IStorageEditorInput)editorInput).getStorage();
				IPath path = storage.getFullPath();
				if (path != null) {
					file = ResourcesPlugin.getWorkspace().getRoot().getFile(path);
				}
			} catch (CoreException e) {
				throw new ExecutionException(DebugUIMessages.DebugHandler_0, e); //$NON-NLS-1$
			}
		}
		return file;
	}
}
