/*******************************************************************************
 * Copyright (c) 2015, 2021 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *	E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.validity.locator;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.Monitor;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.emf.validation.validity.LeafConstrainingNode;
import org.eclipse.ocl.examples.emf.validation.validity.Result;
import org.eclipse.ocl.examples.emf.validation.validity.Severity;
import org.eclipse.ocl.examples.emf.validation.validity.locator.ConstraintLocator;
import org.eclipse.ocl.examples.emf.validation.validity.manager.ValidityManager;
import org.eclipse.ocl.examples.emf.validation.validity.manager.ValidityModel;
import org.eclipse.ocl.pivot.Constraint;
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal;
import org.eclipse.ocl.pivot.internal.validation.PivotEObjectValidator;
import org.eclipse.ocl.pivot.resource.CSResource;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.utilities.ThreadLocalExecutor;
import org.eclipse.ocl.xtext.base.utilities.ElementUtil;
import org.eclipse.ocl.xtext.basecs.ConstraintCS;
import org.eclipse.ocl.xtext.basecs.ModelElementCS;

public class CompleteOCLCSConstraintLocator extends PivotConstraintLocator
{
	public static @NonNull CompleteOCLCSConstraintLocator INSTANCE = new CompleteOCLCSConstraintLocator();

	@Override
	public @Nullable Map<@NonNull EObject, @NonNull List<@NonNull LeafConstrainingNode>> getConstraints(@NonNull ValidityModel validityModel,
		@NonNull EPackage ePackage, @NonNull Set<@NonNull Resource> resources, @NonNull Monitor monitor) {
		Map<@NonNull EObject, @NonNull List<@NonNull LeafConstrainingNode>> map = null;
		for (@NonNull Resource resource : resources) {
			if (monitor.isCanceled()) {
				return null;
			}
			if (resource instanceof CSResource) {
				EnvironmentFactoryInternal environmentFactory = ThreadLocalExecutor.basicGetEnvironmentFactory();
				if (environmentFactory != null) {
					for (TreeIterator<EObject> tit = resource.getAllContents(); tit.hasNext(); ) {
						if (monitor.isCanceled()) {
							return null;
						}
						EObject eObject = tit.next();
						if (eObject instanceof ConstraintCS) {
							ConstraintCS csConstraint = (ConstraintCS)eObject;
							Constraint asConstraint = PivotUtil.getPivot(Constraint.class, csConstraint);
							if (asConstraint != null) {
								EObject esObject = getConstrainedESObject(environmentFactory, asConstraint);
								if (esObject != null) {
									@NonNull String label = String.valueOf(asConstraint.getName());
									map = createLeafConstrainingNode(map, validityModel, esObject, csConstraint, label);
								}
							}
						}
					}
				}
			}
		}
		return map;
	}

//	@Override
//	public Object getImage() {
//		return OCLValidityPlugin.INSTANCE.getImage("OCLModelFile.gif");
//	}

	@Override
	public @NonNull EObject getConstrainingType(@NonNull EObject constrainedType, @NonNull Object constrainingObject) {
		EObject eContainer = ((EObject)constrainingObject).eContainer();
		return eContainer != null ? eContainer : constrainedType;
	}

	@Override
	public @NonNull ConstraintLocator getInstance() {
		return INSTANCE;
	}

	@Override
	public @NonNull String getName() {
		return "Complete OCL CS constraints";
	}

	@Override
	public @Nullable String getSourceExpression(@NonNull LeafConstrainingNode node) {
		Object constrainingObject = node.getConstrainingObject();
		if (!(constrainingObject instanceof ConstraintCS)) {
			return null;
		}
		ModelElementCS csElement = (ConstraintCS)constrainingObject;
		return ElementUtil.getText(csElement);
	}

	@Override
	public @Nullable Resource getSourceResource(@NonNull LeafConstrainingNode node) {
		Object constrainingObject = node.getConstrainingObject();
		if (!(constrainingObject instanceof ConstraintCS)) {
			return null;
		}
		ModelElementCS csElement = (ConstraintCS)constrainingObject;
		return csElement.eResource();
	}

	@Override
	public void validate(@NonNull Result result, @NonNull ValidityManager validityManager, @Nullable Monitor monitor) {
		Severity severity = Severity.UNKNOWN;
		try {
			ConstraintCS csConstraint = (ConstraintCS) result.getLeafConstrainingNode().getConstrainingObject();
			Constraint constraint = (Constraint) csConstraint.getPivot();
			if (constraint != null){
				EObject eObject = result.getValidatableNode().getConstrainedObject();
				try {
					ResourceSet resourceSet = eObject.eResource().getResourceSet();
					if (resourceSet != null) {
						Map<Object, Object> context = validityManager.createDefaultContext();
						context.put(Monitor.class,  monitor);
						Diagnostic diagnostic = PivotEObjectValidator.INSTANCE.validate(constraint, eObject, context);
						result.setDiagnostic(diagnostic);
						severity = diagnostic != null ? getSeverity(diagnostic) : Severity.OK;
					}
				} catch (Throwable e) {
					result.setException(e);
					severity = Severity.FATAL;
				}
			}
		} finally {
			result.setSeverity(severity);
		}
	}
}