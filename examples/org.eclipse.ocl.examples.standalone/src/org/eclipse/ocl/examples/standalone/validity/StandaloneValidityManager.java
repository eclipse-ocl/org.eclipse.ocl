/*******************************************************************************
 * Copyright (c) 2014, 2024 Obeo and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.standalone.validity;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.emf.validation.validity.ConstrainingNode;
import org.eclipse.ocl.emf.validation.validity.locator.ConstraintLocator;
import org.eclipse.ocl.emf.validation.validity.locator.EClassConstraintLocator;
import org.eclipse.ocl.emf.validation.validity.locator.EClassifierConstraintLocator;
import org.eclipse.ocl.emf.validation.validity.locator.EValidatorConstraintLocator;
import org.eclipse.ocl.emf.validation.validity.manager.ValidityManager;
import org.eclipse.ocl.validity.locator.CompleteOCLCSConstraintLocator;
import org.eclipse.ocl.validity.locator.PivotConstraintLocator;
import org.eclipse.ocl.validity.locator.UMLConstraintLocator;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;

public class StandaloneValidityManager extends ValidityManager
{
	private static class IsActivePredicate implements Predicate<ConstraintLocator>
	{
		private boolean runOCLConstraints = false;
		private boolean runJavaConstraints = false;
		private boolean runUMLConstraints = false;

		@Override
		public boolean apply(ConstraintLocator constraintLocator) {
			if (runOCLConstraints) {
				if (constraintLocator instanceof CompleteOCLCSConstraintLocator) {
					return true;
				}
				if (constraintLocator instanceof PivotConstraintLocator) {
					return true;
				}
//				if (constraintLocator instanceof DelegateConstraintLocator) {
//					return true;
//				}
			}
			if (runJavaConstraints) {
				if (constraintLocator instanceof EClassConstraintLocator) {
					return true;
				}
				if (constraintLocator instanceof EClassifierConstraintLocator) {
					return true;
				}
				if (constraintLocator instanceof EValidatorConstraintLocator) {
					return true;
				}
			}
			if (runUMLConstraints) {
				if (constraintLocator instanceof UMLConstraintLocator) {
					return true;
				}
			}
			return false;
		}
	}

	private static @NonNull List<ConstrainingNode> getConstrainingNodeAncestors(@NonNull ConstrainingNode constraining) {
		ConstrainingNode ancestor = constraining.getParent();
		List<ConstrainingNode> ancestors = new ArrayList<ConstrainingNode>();
		while (ancestor != null) {
			ancestors.add(ancestor);
			ancestor = ancestor.getParent();
		}
		return ancestors;
	}

	private @NonNull IsActivePredicate isActivePredicate = new IsActivePredicate();

	public StandaloneValidityManager() {}

	@Override
	public @NonNull Iterable<ConstraintLocator> getActiveConstraintLocators(@NonNull String nsURI) {
		return Iterables.filter(super.getActiveConstraintLocators(nsURI), isActivePredicate);
	}

	public void setRunJavaConstraints(boolean runJavaConstraints) {
		isActivePredicate.runJavaConstraints = runJavaConstraints;
	}

	public void setRunOCLConstraints(boolean runOCLConstraints) {
		isActivePredicate.runOCLConstraints = runOCLConstraints;
	}

	public void setRunUMLConstraints(boolean runUMLConstraints) {
		isActivePredicate.runUMLConstraints = runUMLConstraints;
	}
}
