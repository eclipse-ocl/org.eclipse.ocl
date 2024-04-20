/*******************************************************************************
 * Copyright (c) 2023, 2024 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.validation;

import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EValidator;
import org.eclipse.emf.ecore.impl.EValidatorRegistryImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.eclipse.emf.ecore.util.EObjectValidator;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.internal.resource.ASResourceImpl.ImmutableResource;

/**
 * The ValidationRegistryAdapter supports a local ResourceSet ValidationRegistry that delegates to the global ValidationRegistry
 * in the same way as a local ResourceSet PackageRegistry that delegates to the global PackageRegistry.
 * Unfortunately this is a retrofit so the standard Diagnostician API as used by the Sample Ecore Editor is unaffected.
 * To use the local ValidationRegistry, it is necessary to pass the local ValidationRegistry to the Diagnostician copnstructor
 * which the getDiagnostician() convenien ce does.
 * @since 1.20
 */
public class ValidationRegistryAdapter extends EValidatorRegistryImpl implements Adapter.Internal
{
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(ValidationRegistryAdapter.class);
	/**
	 * The ValidationRegistryAdapter used when a Resource / EObject has no ResourceSet. Typically for an installed EPackage.
	 */
	private static @Nullable ValidationRegistryAdapter ORPHAN_INSTANCE = null;

	/**
	 * Return any ValidationRegistryAdapter already adapting this notifier, which may be an EObject or REsource or ResourceSet.
	 */
	public static @Nullable ValidationRegistryAdapter basicGetAdapter(@NonNull Notifier notifier) {
		Resource resource = notifier instanceof EObject ? ((EObject)notifier).eResource() : notifier instanceof Resource ? (Resource)notifier : null;
		ResourceSet resourceSet = resource != null ? resource.getResourceSet() : (ResourceSet)notifier;
		assert resourceSet != null;
		return basicGetAdapter(resourceSet);
	}

	/**
	 * Return any ValidationRegistryAdapter already adapting this resourceSet.
	 */
	public static @Nullable ValidationRegistryAdapter basicGetAdapter(@NonNull ResourceSet resourceSet) {
		synchronized (resourceSet) {
			List<Adapter> eAdapters = resourceSet.eAdapters();
			for (Adapter adapter : eAdapters) {
				if (adapter instanceof ValidationRegistryAdapter) {
					return (ValidationRegistryAdapter)adapter;
				}
			}
			return null;
		}
	}

	/**
	 * Return and if necessary create a ValidationRegistryAdapter adapting this notifier, which may be an EObject or Resource or ResourceSet.
	 */
	public static @NonNull ValidationRegistryAdapter getAdapter(@NonNull Notifier notifier) {
		Resource resource = notifier instanceof EObject ? ((EObject)notifier).eResource() : notifier instanceof Resource ? (Resource)notifier : null;
		ResourceSet resourceSet = resource != null ? resource.getResourceSet() : (ResourceSet)notifier;
		if (resourceSet == null) {
			if (!(resource instanceof ImmutableResource)) {
				logger.error("No ResourceSet available for ValidationRegistryAdapter.getAdapter()");
			}
			ValidationRegistryAdapter ORPHAN_INSTANCE2 = ORPHAN_INSTANCE;
			if (ORPHAN_INSTANCE2 == null) {
				ORPHAN_INSTANCE2 = ORPHAN_INSTANCE = new ValidationRegistryAdapter();
			}
			return ORPHAN_INSTANCE2;
		}
		return getAdapter(resourceSet);
	}

	public static EValidator.@NonNull Registry getFallbackGlobalValidationRegistry() {
		logger.error("Falling back on global EValidator.Registry - no ResourceSet available");
		return EValidator.Registry.INSTANCE;
	}

	/**
	 * Return and if necessary create a ValidationRegistryAdapter for this resourceSet.
	 */
	public static @NonNull ValidationRegistryAdapter getAdapter(@NonNull ResourceSet resourceSet) {
		synchronized (resourceSet) {
			List<Adapter> eAdapters = resourceSet.eAdapters();
			for (Adapter adapter : eAdapters) {
				if (adapter instanceof ValidationRegistryAdapter) {
					return (ValidationRegistryAdapter)adapter;
				}
			}
			return new ValidationRegistryAdapter(resourceSet);
		}
	}

	/**
	 * Return a Diagnostician suitable for object ensuring that any local ValidationRegistry adapting its ResourceSet is exploited.
	 */
	public static @NonNull Diagnostician getDiagnostician(@NonNull Notifier notifier) {
		Resource resource = notifier instanceof EObject ? ((EObject)notifier).eResource() : notifier instanceof Resource ? (Resource)notifier : null;
		ResourceSet resourceSet = resource != null ? resource.getResourceSet() : (ResourceSet)notifier ;;
		assert resourceSet != null;
		ValidationRegistryAdapter validationRegistry = basicGetAdapter(resourceSet);
		@SuppressWarnings("null")
		@NonNull Diagnostician diagnostician = validationRegistry != null ? new Diagnostician(validationRegistry) : Diagnostician.INSTANCE;
		return diagnostician;
	}

	protected /*final @NonNull*/ ResourceSet resourceSet;

	private ValidationRegistryAdapter() {
		super(EValidator.Registry.INSTANCE);
	}

	public ValidationRegistryAdapter(@NonNull ResourceSet resourceSet) {
		this();
		this.resourceSet = resourceSet;
		resourceSet.eAdapters().add(this);
	}

	public void add(@NonNull EPackage ePackage, @NonNull EValidator extraEValidator) {
		EValidator oldEValidator = getEValidator(ePackage);
		if (oldEValidator == extraEValidator) {
		}
		else {
			ComposedEValidator composedEValidator;
			if (oldEValidator instanceof ComposedEValidator) {
				composedEValidator = (ComposedEValidator)oldEValidator;
			}
			else if (oldEValidator == null) {
				composedEValidator = new ComposedEValidator(EObjectValidator.INSTANCE);
				put(ePackage, composedEValidator);
			}
			else {
				composedEValidator = new ComposedEValidator(oldEValidator);
				put(ePackage, composedEValidator);
			}
			composedEValidator.addChild(extraEValidator);
		}
	}

	/**
	 * Return a Diagnostician suitable for object ensuring that any local ValidationRegistry adapting its ResourceSet is exploited.
	 */
	public @NonNull Diagnostician getDiagnostician() {
		return new Diagnostician(this);
	}

	@Override
	public ResourceSet getTarget() {
		return resourceSet;
	}

	@Override
	public boolean isAdapterForType(Object type) {
		return type == ValidationRegistryAdapter.class;
	}

	@Override
	public void notifyChanged(Notification notification) {}

	@Override
	public void setTarget(Notifier newTarget) {
		assert (newTarget == resourceSet);// || (newTarget == null);
	}

	@Override
	public String toString() {
		return getClass().getName() + "@" + Integer.toHexString(hashCode()) + super.toString();
	}

	@Override
	public void unsetTarget(Notifier oldTarget) {
		assert oldTarget == resourceSet;
		if (oldTarget != null) {
			resourceSet.eAdapters().remove(this);
		}
		resourceSet = null;
		clear();
	}
}