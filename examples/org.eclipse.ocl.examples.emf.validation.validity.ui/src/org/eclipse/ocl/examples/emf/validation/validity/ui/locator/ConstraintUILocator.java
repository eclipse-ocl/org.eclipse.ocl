/*******************************************************************************
 * Copyright (c) 2014 E.D.Willink and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *	E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.emf.validation.validity.ui.locator;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.examples.emf.validation.validity.LeafConstrainingNode;
import org.eclipse.ocl.examples.emf.validation.validity.ResultConstrainingNode;
import org.eclipse.ocl.examples.emf.validation.validity.locator.ConstraintLocator;
import org.eclipse.ocl.examples.emf.validation.validity.ui.view.IDEValidityManager;
import org.eclipse.ocl.examples.emf.validation.validity.ui.view.ValidityView;

/**
 * A ConstraintLocator supports location of a particular category of Constraint and dispatch of those constraints for validation.
 * <p>
 * The org.eclipse.ocl.examples.emf.validation.validity.constraint_locator extension point is used to register ConstraintLocators.
 */
public interface ConstraintUILocator extends ConstraintLocator
{
	/**
	 * Launch a debugger for the resultConstrainingNode, returning false if debugging is not supported.
	 * @throws CoreException 
	 */
	boolean debug(@NonNull ResultConstrainingNode resultConstrainingNode, @NonNull ValidityView validityView, @NonNull IProgressMonitor monitor) throws CoreException;

	/**
	 * Open an editor for the resultConstrainingNode, returning false if debugging is not supported.
	 * @throws CoreException 
	 */
	boolean openEditor(@NonNull LeafConstrainingNode leafConstrainingNode, @NonNull IDEValidityManager validityManager, @NonNull IProgressMonitor monitor) throws CoreException;
}