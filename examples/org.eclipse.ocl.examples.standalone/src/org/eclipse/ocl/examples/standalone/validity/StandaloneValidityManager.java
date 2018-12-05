/*******************************************************************************
 * Copyright (c) 2014, 2018 Obeo and others.
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

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.examples.emf.validation.validity.ConstrainingNode;
import org.eclipse.ocl.examples.emf.validation.validity.LeafConstrainingNode;
import org.eclipse.ocl.examples.emf.validation.validity.Result;
import org.eclipse.ocl.examples.emf.validation.validity.ResultSet;
import org.eclipse.ocl.examples.emf.validation.validity.ResultValidatableNode;
import org.eclipse.ocl.examples.emf.validation.validity.Severity;
import org.eclipse.ocl.examples.emf.validation.validity.ValidatableNode;
import org.eclipse.ocl.examples.emf.validation.validity.locator.ConstraintLocator;
import org.eclipse.ocl.examples.emf.validation.validity.locator.EClassConstraintLocator;
import org.eclipse.ocl.examples.emf.validation.validity.locator.EClassifierConstraintLocator;
import org.eclipse.ocl.examples.emf.validation.validity.locator.EValidatorConstraintLocator;
import org.eclipse.ocl.examples.emf.validation.validity.manager.ValidityManager;
import org.eclipse.ocl.examples.validity.locator.CompleteOCLCSConstraintLocator;
import org.eclipse.ocl.examples.validity.locator.PivotConstraintLocator;
import org.eclipse.ocl.examples.validity.locator.UMLConstraintLocator;

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

	public @NonNull Iterable<ConstraintLocator> getActiveConstraintLocators(@NonNull String nsURI) {
		return Iterables.filter(super.getActiveConstraintLocators(nsURI), isActivePredicate);
	}

	public void runValidation() {
		final ResultSet resultSet = createResultSet(new NullProgressMonitor());
		List<Result> results = installResultSet(resultSet, new NullProgressMonitor());
		for (Result result : results) {
			try {
				ValidatableNode validatable = result.getValidatableNode();
				ValidatableNode validatableParent = validatable.getParent();
				LeafConstrainingNode constraint = result
						.getLeafConstrainingNode();

				if (constraint != null) {
					List<ConstrainingNode> constrainingAncestors = getConstrainingNodeAncestors(constraint);

					boolean isConstrainingNodeEnabled = true;
					for (ConstrainingNode constrainingAncestor : constrainingAncestors) {
						if (!constrainingAncestor.isEnabled()) {
							isConstrainingNodeEnabled = false;
							break;
						}
					}

					boolean isEnabledForValidation = false;
					if (isConstrainingNodeEnabled) {
						if (validatable instanceof ResultValidatableNode) {
							if (validatableParent != null
									&& validatableParent.isEnabled()) {
								isEnabledForValidation = true;
							}
						} else {
							isEnabledForValidation = true;
						}
					}

					if (isEnabledForValidation) {
						ConstraintLocator constraintLocator = constraint
								.getConstraintLocator();
						constraintLocator.validate(result,
								StandaloneValidityManager.this, null);
					} else {
						result.setSeverity(Severity.UNKNOWN);
					}
				} else {
					result.setSeverity(Severity.UNKNOWN);
				}
			} catch (Exception e) {
				result.setException(e);
				result.setSeverity(Severity.FATAL);
			}
		}
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
