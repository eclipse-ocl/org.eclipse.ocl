/*******************************************************************************
 * Copyright (c) 2017 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.xtext.base.ui.commands;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ocl.pivot.Class;
import org.eclipse.ocl.pivot.Constraint;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.LanguageExpression;
import org.eclipse.ocl.pivot.Model;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.internal.utilities.PivotUtilInternal;
import org.eclipse.ocl.pivot.util.AbstractExtendingVisitor;
import org.eclipse.ocl.pivot.util.Visitable;
import org.eclipse.ocl.pivot.utilities.EnvironmentFactory;
import org.eclipse.ocl.pivot.utilities.LabelUtil;
import org.eclipse.ocl.pivot.utilities.MetamodelManager;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.utilities.Pivotable;
import org.eclipse.ocl.xtext.base.utilities.BaseCSResource;
import org.eclipse.ocl.xtext.base.utilities.ElementUtil;
import org.eclipse.ocl.xtext.basecs.ElementCS;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.handlers.HandlerUtil;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

public class MergeHandler extends AbstractHandler
{
	public static class AbstractMergeVisitor extends AbstractExtendingVisitor<Object, @NonNull EnvironmentFactory>
	{
		protected final @NonNull MetamodelManager metamodelManager;
		protected final @NonNull Set<org.eclipse.ocl.pivot.@NonNull Class> mergedClasses = new HashSet<>();
		protected final @NonNull Set<org.eclipse.ocl.pivot.@NonNull Operation> mergedOperations = new HashSet<>();
		protected final @NonNull Set<org.eclipse.ocl.pivot.@NonNull Package> mergedPackages = new HashSet<>();
		protected final @NonNull Set<org.eclipse.ocl.pivot.@NonNull Property> mergedProperties = new HashSet<>();

		protected AbstractMergeVisitor(@NonNull EnvironmentFactory environmentFactory) {
			super(environmentFactory);
			this.metamodelManager = environmentFactory.getMetamodelManager();
		}

		private void mergeClass(org.eclipse.ocl.pivot.@NonNull Class primaryClass, org.eclipse.ocl.pivot.@NonNull Class mergeClass) {
			Iterable<@NonNull Constraint> mergeInvariants = PivotUtil.getOwnedInvariants(mergeClass);
			if (!Iterables.isEmpty(mergeInvariants)) {
				List<@NonNull Constraint> primaryInvariants = PivotUtilInternal.getOwnedInvariantsList(primaryClass);
				for (@NonNull Constraint mergeInvariant : Lists.newArrayList(mergeInvariants)) {
					mergeInvariant.setIsCallable(true);
					PivotUtilInternal.resetContainer(mergeInvariant);
					primaryInvariants.add(mergeInvariant);
				}
			}
			Iterable<@NonNull Operation> mergeOperations = PivotUtil.getOwnedOperations(mergeClass);
			if (!Iterables.isEmpty(mergeOperations)) {
				List<@NonNull Operation> primaryOperations = PivotUtilInternal.getOwnedOperationsList(primaryClass);
				for (org.eclipse.ocl.pivot.@NonNull Operation mergeOperation : Lists.newArrayList(mergeOperations)) {
					mergeOperation(primaryOperations, mergeOperation);
				}
			}
			Iterable<@NonNull Property> mergeProperties = PivotUtil.getOwnedProperties(mergeClass);
			if (!Iterables.isEmpty(mergeOperations)) {
				List<@NonNull Property> primaryProperties = PivotUtilInternal.getOwnedPropertiesList(primaryClass);
				for (org.eclipse.ocl.pivot.@NonNull Property mergeProperty : Lists.newArrayList(mergeProperties)) {
					mergeProperty(primaryProperties, mergeProperty);
				}
			}
			mergedClasses.add(mergeClass);
		}

		private void mergeOperation(@NonNull List<@NonNull Operation> primaryOperations, org.eclipse.ocl.pivot.@NonNull Operation mergeOperation) {
			Operation primaryOperation = metamodelManager.getPrimaryOperation(mergeOperation);
			if (primaryOperation != mergeOperation) {		// If merge needed
				LanguageExpression pivotBodyExpression = mergeOperation.getBodyExpression();
				LanguageExpression primaryBodyExpression = primaryOperation.getBodyExpression();
				if ((primaryBodyExpression == null) && (pivotBodyExpression != null)) {
					PivotUtilInternal.resetContainer(pivotBodyExpression);
					primaryOperation.setBodyExpression(pivotBodyExpression);
				}
			}
			else											// Else simple promotion
			{
				PivotUtilInternal.resetContainer(mergeOperation);
				primaryOperations.add(mergeOperation);
			}
			mergedOperations.add(mergeOperation);
		}

		private void mergePackage(org.eclipse.ocl.pivot.@NonNull Package mergePackage) {
			Iterable<@NonNull Class> mergePackages = PivotUtil.getOwnedClasses(mergePackage);
			if (!Iterables.isEmpty(mergePackages)) {
				for (org.eclipse.ocl.pivot.@NonNull Class mergeClass : Lists.newArrayList(mergePackages)) {
					org.eclipse.ocl.pivot.@NonNull Class primaryClass = metamodelManager.getPrimaryClass(mergeClass);
					mergeClass(primaryClass, mergeClass);
				}
			}
			mergedPackages.add(mergePackage);
		}

		private void mergeProperty(@NonNull List<@NonNull Property> primaryProperties, org.eclipse.ocl.pivot.@NonNull Property mergeProperty) {
			Property primaryProperty = metamodelManager.getPrimaryProperty(mergeProperty);
			if (primaryProperty != mergeProperty) {			// If merge needed
				LanguageExpression pivotDefaultExpression = mergeProperty.getOwnedExpression();
				LanguageExpression primaryDefaultExpression = primaryProperty.getOwnedExpression();
				if ((primaryDefaultExpression == null) && (pivotDefaultExpression != null)) {
					primaryProperty.setOwnedExpression(pivotDefaultExpression);
				}
			}
			else											// Else simple promotion
			{
				//					boolean b1 = primaryProperty.isIsImplicit();
				//					boolean b2 = mergeProperty.isIsImplicit();
				PivotUtilInternal.resetContainer(mergeProperty);
				primaryProperties.add(mergeProperty);
			}
			mergedProperties.add(mergeProperty);
		}

		@Override
		public Object visiting(@NonNull Visitable visitable) {
			throw new IllegalArgumentException("Unsupported " + visitable.eClass().getName() + " for " + getClass().getSimpleName());
		}

		@Override
		public Object visitClass(org.eclipse.ocl.pivot.@NonNull Class mergeClass) {
			org.eclipse.ocl.pivot.@NonNull Class primaryClass = metamodelManager.getPrimaryClass(mergeClass);
			mergeClass(primaryClass, mergeClass);
			return null;
		}

		@Override
		public Object visitModel(@NonNull Model object) {
			for (org.eclipse.ocl.pivot.@NonNull Package asPackage : PivotUtil.getOwnedPackages(object)) {
				mergePackage(asPackage);
			}
			return null;
		}

		@Override
		public Object visitOperation(@NonNull Operation mergeOperation) {
			org.eclipse.ocl.pivot.@NonNull Class primaryClass = metamodelManager.getPrimaryClass(PivotUtil.getOwningClass(mergeOperation));
			List<@NonNull Operation> primaryOperations = PivotUtilInternal.getOwnedOperationsList(primaryClass);
			mergeOperation(primaryOperations, mergeOperation);
			return null;
		}

		@Override
		public Object visitPackage(org.eclipse.ocl.pivot.@NonNull Package object) {
			mergePackage(object);
			for (org.eclipse.ocl.pivot.@NonNull Package asPackage : PivotUtil.getOwnedPackages(object)) {
				mergePackage(asPackage);
			}
			return null;
		}

		@Override
		public Object visitProperty(@NonNull Property mergeProperty) {
			org.eclipse.ocl.pivot.@NonNull Class primaryClass = metamodelManager.getPrimaryClass(PivotUtil.getOwningClass(mergeProperty));
			List<@NonNull Property> primaryProperties = PivotUtilInternal.getOwnedPropertiesList(primaryClass);
			mergeProperty(primaryProperties, mergeProperty);
			return null;
		}
	}

	public static class MergeVisitor extends AbstractMergeVisitor
	{
		protected MergeVisitor(@NonNull EnvironmentFactory environmentFactory) {
			super(environmentFactory);
		}

		public void mergeSelections(@NonNull List<@NonNull Element> selectedElements) {
			for (@NonNull Element element : selectedElements) {
				element.accept(this);
			}
		}

		public void pruneMergedElements() {
			for (@NonNull Operation mergedOperation : mergedOperations) {
				ElementCS csElement = ElementUtil.getCsElement(mergedOperation);
				if (csElement != null) {
					EcoreUtil.delete(csElement);
				}
			}
			for (@NonNull Property mergedProperty : mergedProperties) {
				ElementCS csElement = ElementUtil.getCsElement(mergedProperty);
				if (csElement != null) {
					EcoreUtil.delete(csElement);
				}
			}
			for (org.eclipse.ocl.pivot.@NonNull Class mergedClass : mergedClasses) {
				ElementCS csElement = ElementUtil.getCsElement(mergedClass);
				if (csElement != null) {
					EcoreUtil.delete(csElement);
				}
			}
			for (org.eclipse.ocl.pivot.@NonNull Package mergedPackage : mergedPackages) {
				ElementCS csElement = ElementUtil.getCsElement(mergedPackage);
				if (csElement != null) {
					EcoreUtil.delete(csElement);
				}
			}
		}
	}

	public MergeHandler() {}

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IStructuredSelection selection = HandlerUtil.getCurrentStructuredSelection(event);
		if (selection == null) {
			return null;
		}
		Shell shell = HandlerUtil.getActiveShell(event);
		if (shell == null) {
			return null;
		}
		//		if (selection.size() < 2) {
		//			MessageDialog.openError(shell, "OCL Merge", "At least two selections required for a merge");
		//			return null;
		//		}
		List<@NonNull Element> selectedElements = new ArrayList<>();
		EnvironmentFactory environmentFactory = getSelections(shell, selection, selectedElements);
		if (environmentFactory == null) {
			return null;
		}
		MergeVisitor mergeVisitor = new MergeVisitor(environmentFactory);
		mergeVisitor.mergeSelections(selectedElements);
		mergeVisitor.pruneMergedElements();
		return null;
	}

	protected @Nullable EnvironmentFactory getSelections(@NonNull Shell shell, @NonNull IStructuredSelection selection, @NonNull List<@NonNull Element> selectedElements) {
		EnvironmentFactory environmentFactory = null;
		for (Iterator<?> objects = selection.iterator(); objects.hasNext();) {
			Object object = AdapterFactoryEditingDomain.unwrap(objects.next());
			if (object instanceof IAdaptable) {
				object = ((IAdaptable) object).getAdapter(EObject.class);
			}
			if (!(object instanceof ElementCS) || ! (object instanceof Pivotable)) {
				MessageDialog.openError(shell, "OCL Merge", "Selected object is not a CS addition: " + LabelUtil.getLabel(object));
				return null;
			}
			Element element = ((Pivotable)object).getPivot();
			if (element == null) {
				MessageDialog.openError(shell, "OCL Merge", "Selected CS object has no AS counterpart: " + LabelUtil.getLabel(object));
				return null;
			}
			if (environmentFactory == null) {
				Resource eResource = ((ElementCS)object).eResource();
				if (eResource instanceof BaseCSResource) {
					environmentFactory = ((BaseCSResource)eResource).getEnvironmentFactory();
				}
			}
			if ((element instanceof org.eclipse.ocl.pivot.Model)
					|| (element instanceof org.eclipse.ocl.pivot.Package)
					|| (element instanceof org.eclipse.ocl.pivot.Class)
					|| (element instanceof org.eclipse.ocl.pivot.Property)
					|| (element instanceof org.eclipse.ocl.pivot.Operation)) {
				selectedElements.add(element);
			}
			else {
				MessageDialog.openError(shell, "OCL Merge", "Selected CS object is not mergeable: " + LabelUtil.getLabel(object));
				return null;
			}
		}
		if (environmentFactory == null) {
			MessageDialog.openError(shell, "OCL Merge", "No EnvironmentFactory found for any selected object");
			return null;
		}
		return environmentFactory;
	}
}
