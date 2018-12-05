/*******************************************************************************
 * Copyright (c) 2011, 2018 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.xtext.console;

import org.eclipse.core.resources.IProject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.xtext.resource.XtextResourceSet;
import org.eclipse.xtext.ui.resource.XtextResourceSetProvider;

import com.google.inject.Inject;
import com.google.inject.Provider;


public class XtextConsoleResourceSetProvider extends XtextResourceSetProvider
{
	@Inject
	private Provider<XtextResourceSet> resourceSetProvider;

	private XtextResourceSet nullProjectResourceSet = null;

	@Override
	public ResourceSet get(IProject project) {			// FIXME Workaround for Bug 339640
		if (project != null) {
			return super.get(project);
		}
		else {
			if (nullProjectResourceSet == null) {
				nullProjectResourceSet = resourceSetProvider.get();
			}
			return nullProjectResourceSet;
		}
	}
}
