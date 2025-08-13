/*******************************************************************************
 * Copyright (c) 2014, 2019 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.internal.complete;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.Model;
import org.eclipse.ocl.pivot.Package;
import org.eclipse.ocl.pivot.PivotPackage;
import org.eclipse.ocl.pivot.internal.CompleteModelImpl;
import org.eclipse.ocl.pivot.internal.ModelImpl;
import org.eclipse.ocl.pivot.util.PivotPlugin;
import org.eclipse.ocl.pivot.utilities.TracingOption;

import com.google.common.base.Function;
import com.google.common.collect.Iterables;

public class PartialModels extends EObjectResolvingEList<Model> implements ModelListeners.IModelListener, Adapter
{
	public static final @NonNull TracingOption PARTIAL_MODELS = new TracingOption(PivotPlugin.PLUGIN_ID, "partialModels");
//	static { PARTIAL_ROOTS.setState(true); }
	private static final long serialVersionUID = 1L;

	private static class Model2RootOwnedPackages implements Function<Model, Iterable<org.eclipse.ocl.pivot.Package>>
	{
		@Override
		public Iterable<Package> apply(Model partialModel) {
			return partialModel.getOwnedPackages();
		}
	}
	private static final @NonNull Model2RootOwnedPackages model2RootOwnedPackages = new Model2RootOwnedPackages();

	public PartialModels(@NonNull CompleteModelImpl owner) {
		super(Model.class, owner, PivotPackage.Literals.COMPLETE_MODEL__PARTIAL_MODELS.getFeatureID());
		ResourceSet asResourceSet = owner.getEnvironmentFactory().getASResourceSet();
		assert asResourceSet.getResources().isEmpty();
		asResourceSet.eAdapters().add(this);
	}

	@Override
	public void addUnique(Model partialModel) {
		assert partialModel != null;
		super.addUnique(partialModel);
		didAdd(partialModel);
	}

	@Override
	public void addUnique(int index, Model partialModel) {
		assert partialModel != null;
		super.addUnique(index, partialModel);
		didAdd(partialModel);
	}

	protected void didAdd(@NonNull Model partialModel) {
		if (PARTIAL_MODELS.isActive()) {
			PARTIAL_MODELS.println("Do-didAdd Model" + this + " " + partialModel);
		}
		CompleteModelImpl completeModel = getCompleteModel();
		completeModel.didAddPartialModel(partialModel);
		((ModelImpl)partialModel).addRootListener(this);
	}

	@Override
	public void didAddPackage(org.eclipse.ocl.pivot.@NonNull Package partialPackage) {
		getCompleteModel().didAddPackage(partialPackage);
	}

	private void didAddResource(@NonNull Resource resource) {
		for (EObject eObject : resource.getContents()) {
			if (eObject instanceof Model) {
				getCompleteModel().getPartialModels().add((Model)eObject);
			}
		}
		resource.eAdapters().add(this);
	}

	@Override
	protected void didRemove(int index, Model partialModel) {
		assert partialModel != null;
		if (PARTIAL_MODELS.isActive()) {
			PARTIAL_MODELS.println("Do-didRemove Model " + this + " " + partialModel);
		}
		didRemove(partialModel);
	}

	protected void didRemove(@NonNull Model partialModel) {
		((ModelImpl)partialModel).removeRootListener(this);
		CompleteModelInternal completeModel = getCompleteModel();
		for (org.eclipse.ocl.pivot.Package pivotPackage : partialModel.getOwnedPackages()) {
			if (pivotPackage != null) {
				completeModel.didRemoveNestedPackage(pivotPackage);
			}
		}
		completeModel.didRemovePartialModel(partialModel);
	}

	@Override
	public void didRemovePackage(org.eclipse.ocl.pivot.@NonNull Package partialPackage) {
		getCompleteModel().didRemoveNestedPackage(partialPackage);
	}

	private void didRemoveResource(@NonNull Resource resource) {
		resource.eAdapters().remove(this);
	}

	/**
	 * @since 7.0
	 */
	protected @NonNull CompleteModelImpl getCompleteModel() {
		assert owner != null;
		return (CompleteModelImpl)owner;
	}

	protected @NonNull Iterable<org.eclipse.ocl.pivot.@NonNull Package> getNestedPartialPackages() {
		PartialModels partialModels = getCompleteModel().getPartialModels();
		Iterable<Iterable<org.eclipse.ocl.pivot.@NonNull Package>> roots_packages = Iterables.transform(partialModels, model2RootOwnedPackages);
		@SuppressWarnings("null")
		@NonNull Iterable<org.eclipse.ocl.pivot.@NonNull Package> allPackages = Iterables.concat(roots_packages);
		return allPackages;
	}

	@Override
	public Notifier getTarget() {
		return getCompleteModel().getEnvironmentFactory().getASResourceSet();
	}

	@Override
	public boolean isAdapterForType(Object type) {
		return type == PartialModels.class;
	}

	@Override
	public void notifyChanged(Notification notification) {
		assert notification != null;
		Object notifier = notification.getNotifier();
		int eventType = notification.getEventType();
		if (notifier instanceof ResourceSet) {
			int featureID = notification.getFeatureID(ResourceSet.class);
			if (featureID == ResourceSet.RESOURCE_SET__RESOURCES)
			{
				if (eventType == Notification.ADD)
				{
					Object newValue = notification.getNewValue();
					if (newValue instanceof Resource) {
						didAddResource((Resource)newValue);
					}
				}
				else if (eventType == Notification.ADD_MANY)
				{
					Object newValues =  notification.getNewValue();
					if (newValues instanceof Iterable<?>) {
						for (Object newValue : (Iterable<?>)newValues){
							if (newValue instanceof Resource) {
								didAddResource((Resource)newValue);
							}
						}
					}
				}
				if (eventType == Notification.REMOVE)
				{
					Object newValue = notification.getNewValue();
					if (newValue instanceof Resource) {
						didRemoveResource((Resource)newValue);
					}
				}
				else if (eventType == Notification.REMOVE_MANY)
				{
					Object newValues =  notification.getNewValue();
					if (newValues instanceof Iterable<?>) {
						for (Object newValue : (Iterable<?>)newValues){
							if (newValue instanceof Resource) {
								didRemoveResource((Resource)newValue);
							}
						}
					}
				}
			}
		}
		else if (notifier instanceof Resource) {
			int featureID = notification.getFeatureID(Resource.class);
			if (featureID == Resource.RESOURCE__CONTENTS)
			{
				if (eventType == Notification.ADD)
				{
					Object newValue = notification.getNewValue();
					if (newValue instanceof Model) {
						add((Model)newValue);
					}
				}
				else if (eventType == Notification.ADD_MANY)
				{
					Object newValues =  notification.getNewValue();
					if (newValues instanceof Iterable<?>) {
						for (Object newValue : (Iterable<?>)newValues){
							if (newValue instanceof Model) {
								add((Model)newValue);
							}
						}
					}
				}
				if (eventType == Notification.REMOVE)
				{
					Object newValue = notification.getNewValue();
					remove(newValue);
				}
				else if (eventType == Notification.REMOVE_MANY)
				{
					Object newValues =  notification.getNewValue();
					if (newValues instanceof Iterable<?>) {
						for (Object newValue : (Iterable<?>)newValues){
							remove(newValue);
						}
					}
				}
			}
		}
	}

	@Override
	public void setTarget(Notifier newTarget) {}

//	@Override
//	public void unsetTarget(Notifier oldTarget) {
//		assert owner == oldTarget;
//	}
}