/*******************************************************************************
 * Copyright (c) 2017 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation based on org.eclipse.xtext.builder.nature.ToggleXtextNatureCommand
 *******************************************************************************/
package org.eclipse.ocl.xtext.base.ui.commands;

import java.util.Iterator;

import org.apache.log4j.Logger;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ocl.xtext.base.ui.OCLProjectHelper;
import org.eclipse.ocl.xtext.base.ui.messages.BaseUIMessages;
import org.eclipse.osgi.util.NLS;
import org.eclipse.ui.handlers.HandlerUtil;

/**
 */
public class ToggleOCLNatureCommand extends AbstractHandler implements ToggleNatureCommand {

	private static final Logger log = Logger.getLogger(ToggleOCLNatureCommand.class);

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		ISelection selection = HandlerUtil.getCurrentSelection(event);
		if (selection instanceof IStructuredSelection) {
			for (Iterator<?> it = ((IStructuredSelection) selection).iterator(); it.hasNext();) {
				Object element = it.next();
				IProject project = null;
				if (element instanceof IAdaptable) {
					project = ((IAdaptable) element).getAdapter(IProject.class);
				}
				if (project != null) {
					toggleNature(project);
				}
			}
		}
		return null;
	}

	@Override
	public String getAddNatureDialogText(String projectName) {
		return NLS.bind(BaseUIMessages.OCLNatureAddingEditorCallback_MessageDialog_Message, projectName);
	}

	@Override
	public String getAddNatureDialogTitle() {
		return BaseUIMessages.OCLNatureAddingEditorCallback_MessageDialog_Title;
	}

	@Override
	public @NonNull String getAddNatureKey() {
		return "add_ocl_nature";
	}

	@Override
	public boolean hasNature(@NonNull IProject project) {
		return OCLProjectHelper.hasNature(project);
	}

	@Override
	public void toggleNature(@NonNull IProject project) {
		try {
			IProjectDescription description = project.getDescription();
			String[] natures = description.getNatureIds();

			for (int i = 0; i < natures.length; ++i) {
				if (OCLProjectHelper.NATURE_ID.equals(natures[i])) {
					// Remove the nature
					String[] newNatures = new String[natures.length - 1];
					System.arraycopy(natures, 0, newNatures, 0, i);
					System.arraycopy(natures, i + 1, newNatures, i, natures.length - i - 1);
					description.setNatureIds(newNatures);
					project.setDescription(description, null);
					return;
				}
			}

			// Add the nature
			String[] newNatures = new String[natures.length + 1];
			System.arraycopy(natures, 0, newNatures, 0, natures.length);
			newNatures[natures.length] = OCLProjectHelper.NATURE_ID;
			description.setNatureIds(newNatures);
			project.setDescription(description, null);
		} catch (CoreException e) {
			log.error("Error toggling OCL nature", e);
		}
	}
}
