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
import java.util.Map;
import java.util.WeakHashMap;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
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
	/**
	 * A DelegatingEValidator instance displaces a global EValidator.Registry.INSTANCE to redirect validations for
	 * EPackages associated with ResourceSets with local ValidationRegistryAdapters to use the local adapter.
	 *
	 * @since 1.23
	 */
	protected static class DelegatingEValidator implements EValidator
	{
		public static void addEntry(@NonNull EPackage ePackage, @NonNull ResourceSet resourceSet, @NonNull EValidator eValidator) {
			EValidator globalEValidator = EValidator.Registry.INSTANCE.getEValidator(ePackage);
			DelegatingEValidator delegatingEValidator;
			if (globalEValidator instanceof DelegatingEValidator) {				// XXX Composed/Composite/...
				delegatingEValidator = (DelegatingEValidator)globalEValidator;
			}
			else {
				delegatingEValidator = new DelegatingEValidator(globalEValidator);
				EValidator.Registry.INSTANCE.put(ePackage, delegatingEValidator);
			}
			delegatingEValidator.resourceSet2delegateEvalidator.put(resourceSet, eValidator);
		}

		public static void removeEntry(@NonNull EPackage ePackage, @NonNull ResourceSet resourceSet) {
			EValidator globalEValidator = EValidator.Registry.INSTANCE.getEValidator(ePackage);
			if (globalEValidator instanceof DelegatingEValidator) {				// XXX Composed/Composite/...
				DelegatingEValidator delegatingEValidator = (DelegatingEValidator)globalEValidator;
				WeakHashMap<@Nullable ResourceSet, @Nullable EValidator> resourceSet2delegateEvalidator = delegatingEValidator.resourceSet2delegateEvalidator;
				resourceSet2delegateEvalidator.remove(resourceSet);
				if (resourceSet2delegateEvalidator.size() == 1) {
					Object oldEValidator = resourceSet2delegateEvalidator.get(ORIGINAL_GLOBAL_EVALIDATOR_KEY);
					EValidator.Registry.INSTANCE.put(ePackage, oldEValidator);
				}
			}
			else {
				assert false;
			}
		}

		private final static @Nullable ResourceSet ORIGINAL_GLOBAL_EVALIDATOR_KEY = null;

		/**
		 * Map from ResourceSet with a local ValidationRegistryAdapter to its local validator.
		 * null key maps all other ResourceSets to the displaced validator.
		 * WeakHashMap should ensure that stale ResourceSets vanish.
		 */
		private final @NonNull WeakHashMap<@Nullable ResourceSet, @Nullable EValidator> resourceSet2delegateEvalidator = new WeakHashMap<>();

		private DelegatingEValidator(@Nullable EValidator oldEValidator) {
			resourceSet2delegateEvalidator.put(ORIGINAL_GLOBAL_EVALIDATOR_KEY, oldEValidator);
		}

		protected @Nullable EValidator getEValidator(EObject eObject) {
			if (eObject == null) {
				return null;
			}
			Resource resource = eObject.eResource();
			if (resource == null) {
				return null;
			}
			ResourceSet resourceSet = resource.getResourceSet();
			if (resourceSet == null) {
				return null;
			}
			return resourceSet2delegateEvalidator.get(resourceSet);
		}

		@Override
		public boolean validate(EObject eObject, DiagnosticChain diagnostics, Map<Object, Object> context) {
			EValidator eValidator = getEValidator(eObject);
			return eValidator != null ? eValidator.validate(eObject, diagnostics, context) : diagnostics != null;
		}

		@Override
		public boolean validate(EClass eClass, EObject eObject, DiagnosticChain diagnostics, Map<Object, Object> context) {
			EValidator eValidator = getEValidator(eObject);
			return eValidator != null ? eValidator.validate(eClass, eObject, diagnostics, context) : diagnostics != null;
		}

		@Override
		public boolean validate(EDataType eDataType, Object value, DiagnosticChain diagnostics, Map<Object, Object> context) {
			EValidator eValidator = getEValidator(eDataType);
			return eValidator != null ? eValidator.validate(eDataType, value, diagnostics, context) : diagnostics != null;
		}
	}

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

	/**
	 * Register a non-null eValidator as the validator for ePackage in the global validation registry, but only for use
	 * with the ResourceSet that this ValidationRegistryAdapter adapts. For other ResourceSets delegation
	 * redirects to any displaced eValiadtor.
	 *
	 * For a null eValidator, retract the previous registration.
	 *
	 * @since 1.23
	 */
	public void putWithGlobalDelegation(@NonNull EPackage ePackage, @Nullable EValidator eValidator) {
		ResourceSet resourceSet = getTarget();
		assert resourceSet != null;
		if (eValidator != null) {
			put(ePackage, eValidator);
			DelegatingEValidator.addEntry(ePackage, resourceSet, eValidator);
		}
		else {
			remove(ePackage);
			DelegatingEValidator.removeEntry(ePackage, resourceSet);
		}
	}

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