/*******************************************************************************
 * Copyright (c) 2010, 2025 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   C.Damus, K.Hussey, E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.internal.delegate;

import java.util.Map;

import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EValidator;
import org.eclipse.emf.ecore.impl.ValidationDelegateRegistryImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.QueryDelegate;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.common.delegate.DelegateResourceSetAdapter;
import org.eclipse.ocl.common.delegate.VirtualDelegateMapping;
import org.eclipse.ocl.common.internal.options.CommonOptions;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.utilities.EnvironmentFactory;
import org.eclipse.ocl.pivot.utilities.LabelUtil;
import org.eclipse.ocl.pivot.utilities.ParserException;
import org.eclipse.ocl.pivot.utilities.PivotConstants;
import org.eclipse.ocl.pivot.utilities.PivotUtil;

/**
 * An implementation of a delegate domain for an OCL enhanced package. The domain
 * maintains an OCL facade to be shared by all delegates within the package.
 */
public class OCLDelegateDomain implements DelegateDomain
{
	public static class FactoryFactory
	{
		public static final @NonNull FactoryFactory INSTANCE = new FactoryFactory();

		public @NonNull OCLDelegateDomainFactory createDelegateDomainFactory() {
			return new OCLDelegateDomainFactory();
		}

		public @NonNull OCLInvocationDelegateFactory createInvocationDelegateFactory(@NonNull String oclDelegateURI) {
			return new OCLInvocationDelegateFactory(oclDelegateURI, false);
		}

		public @NonNull OCLQueryDelegateFactory createQueryDelegateFactory(@NonNull String oclDelegateURI) {
			return new OCLQueryDelegateFactory(oclDelegateURI, false);
		}

		public @NonNull OCLSettingDelegateFactory createSettingDelegateFactory(@NonNull String oclDelegateURI) {
			return new OCLSettingDelegateFactory(oclDelegateURI, false);
		}

		public @NonNull OCLValidationDelegateFactory createValidationDelegateFactory(@NonNull String oclDelegateURI) {
			return new OCLValidationDelegateFactory(oclDelegateURI, false);
		}
	}

	protected static class PivotOnlyRegistry extends ValidationDelegateRegistryImpl
	{
		private static final long serialVersionUID = 1L;

		public static final @NonNull PivotOnlyRegistry INSTANCE = new PivotOnlyRegistry();
		private static final @NonNull OCLValidationDelegateFactory OCLValidationDelegateFactory_INSTANCE = new OCLValidationDelegateFactory(PivotConstants.OCL_DELEGATE_URI_PIVOT, true);

		@Override
		public ValidationDelegate getValidationDelegate(String uri) {
			assert !PivotConstants.OCL_DELEGATE_URI_PIVOT_DYNAMIC.equals(uri);				// XXX
			return OCLValidationDelegateFactory_INSTANCE;
		}
	}

	protected static class PivotOnlyVirtualDelegateMapping extends VirtualDelegateMapping
	{
		public static final @NonNull PivotOnlyVirtualDelegateMapping INSTANCE = new PivotOnlyVirtualDelegateMapping();

		protected PivotOnlyVirtualDelegateMapping() {
			super("x", "y", "z");
		}

		@Override
		public @Nullable String getDefaultValue() {
			return PivotConstants.OCL_DELEGATE_URI_PIVOT;
		}

		@Override
		public @Nullable String getPreferredValue() {
			return PivotConstants.OCL_DELEGATE_URI_PIVOT;
		}
	}

	private static @NonNull FactoryFactory getDelegateFactoryFactory(@Nullable FactoryFactory delegateFactoryFactory) {
		return delegateFactoryFactory != null ? delegateFactoryFactory : FactoryFactory.INSTANCE;
	}

	// FIXME workaround BUG 485093 giving a false non-null analysis
	@SuppressWarnings("null")
	private static @NonNull DelegateResourceSetAdapter getDelegateResourceSetAdapter(@NonNull ResourceSet resourceSet) {
		return DelegateResourceSetAdapter.getAdapter(resourceSet);
	}

	/**
	 * @since 1.1
	 */
	// FIXME workaround BUG 485093 giving a false non-null analysis
	public static <T> T getDelegateResourceSetRegistry(EModelElement modelElement, @NonNull Class<@NonNull T> registryClass, T defaultRegistry) {
		Resource resource = modelElement.eResource();
		if (resource == null) {
			return defaultRegistry;
		}
		ResourceSet resourceSet = resource.getResourceSet();
		if (resourceSet == null) {
			return defaultRegistry;
		}
		DelegateResourceSetAdapter adapter = getDelegateResourceSetAdapter(resourceSet);
		@Nullable T registry = getDelegateResourceSetAdapterRegistry(adapter, registryClass);
		return registry != null ? registry : defaultRegistry;
	}

	// FIXME workaround BUG 485093 giving a false non-null analysis
	private static <T> @Nullable T getDelegateResourceSetAdapterRegistry(@NonNull DelegateResourceSetAdapter adapter, @NonNull Class<T> registryClass) {
		return adapter.getRegistry(registryClass);
	}

	/**
	 * Initialize the resourceSet registries, if non-null, or the global registries, if null,
	 * to support usage of the Pivot OCL Delegate Evaluator for the Pivot OCL Delegate URI.
	 */
	public static void initialize(@Nullable ResourceSet resourceSet) {
		initialize(resourceSet, PivotConstants.OCL_DELEGATE_URI_PIVOT);
	}

	/**
	 * Initialize the resourceSet registries, if non-null, or the global registries, if null,
	 * to support usage of the Pivot OCL Delegate Evaluator for the oclDelegateURI.
	 */
	public static void initialize(@Nullable ResourceSet resourceSet, @NonNull String oclDelegateURI) {
		if (resourceSet == null) {
			lazyInitializeGlobals(oclDelegateURI, true);
		}
		else {
			lazyInitializeLocals(resourceSet, oclDelegateURI, true, null);
		}
	}

	public static void initializePivotOnlyDiagnosticianContext(@NonNull Map<Object, Object> context) {
		context.put(org.eclipse.emf.ecore.EValidator.ValidationDelegate.Registry.class, PivotOnlyRegistry.INSTANCE);
		context.put(EValidator.SubstitutionLabelProvider.class, LabelUtil.SUBSTITUTION_LABEL_PROVIDER);
	}

	public static void initializePivotOnlyDiagnosticianResourceSet(@NonNull ResourceSet resourceSet) {
		DelegateResourceSetAdapter adapter = getDelegateResourceSetAdapter(resourceSet);
		putDelegateResourceSetRegistry(adapter, org.eclipse.ocl.pivot.internal.delegate.ValidationDelegate.Registry.class, PivotOnlyRegistry.INSTANCE);
		putDelegateResourceSetRegistry(adapter, VirtualDelegateMapping.class, PivotOnlyVirtualDelegateMapping.INSTANCE);
	}

	/**
	 * @since 7.0
	 */
	public static void lazyInitializeGlobalValidationRegistry(@NonNull String oclDelegateURI, boolean forceInitialization) {
		if (!EMFPlugin.IS_ECLIPSE_RUNNING) {		// Install the 'plugin' registrations
			EValidator.ValidationDelegate.Registry validationRegistry = EValidator.ValidationDelegate.Registry.INSTANCE;
			if (forceInitialization || !validationRegistry.containsKey(oclDelegateURI)) {
				Object validationDelegateFactory = PivotConstants.OCL_DELEGATE_URI_PIVOT_DYNAMIC.equals(oclDelegateURI) ? new OCLValidationDelegateFactory.Dynamic(oclDelegateURI, true) : new OCLValidationDelegateFactory(oclDelegateURI, true);
				validationRegistry.put(oclDelegateURI, validationDelegateFactory);
			}
		}
	}

	/**
	 * Initialize any currently uninitialized global delegate registries to support the oclDelegateURI.
	 * @since 1.23
	 */
	public static void lazyInitializeGlobals(@NonNull String oclDelegateURI, boolean forceInitialization) {
		if (!EMFPlugin.IS_ECLIPSE_RUNNING) {		// Install the 'plugin' registrations
			EOperation.Internal.InvocationDelegate.Factory.Registry invocationRegistry = EOperation.Internal.InvocationDelegate.Factory.Registry.INSTANCE;
			if (forceInitialization || !invocationRegistry.containsKey(oclDelegateURI)) {
				OCLInvocationDelegateFactory invocationDelegateFactory = new OCLInvocationDelegateFactory(oclDelegateURI, true);
				invocationRegistry.put(oclDelegateURI, invocationDelegateFactory);
			}
			EStructuralFeature.Internal.SettingDelegate.Factory.Registry settingRegistry = EStructuralFeature.Internal.SettingDelegate.Factory.Registry.INSTANCE;
			if (forceInitialization || !settingRegistry.containsKey(oclDelegateURI)) {
				OCLSettingDelegateFactory settingDelegateFactory = new OCLSettingDelegateFactory(oclDelegateURI, true);
				settingRegistry.put(oclDelegateURI, settingDelegateFactory);
			}
			lazyInitializeGlobalValidationRegistry(oclDelegateURI, forceInitialization);
			QueryDelegate.Factory.Registry queryRegistry = QueryDelegate.Factory.Registry.INSTANCE;
			if (forceInitialization || !queryRegistry.containsKey(oclDelegateURI)) {
				OCLQueryDelegateFactory queryDelegateFactory = new OCLQueryDelegateFactory(oclDelegateURI, true);
				queryRegistry.put(oclDelegateURI, queryDelegateFactory);
			}
		}
	}

	/**
	 * Install a local DelegateDomain.Factory and a local ValidationDelegate.Factory
	 *
	 * @since 7.0
	 */
	public static void lazyInitializeLocalValidationRegistry(@NonNull ResourceSet resourceSet, @NonNull String oclDelegateURI, boolean forceInitialization,
			@Nullable FactoryFactory delegateFactoryFactory) {
		delegateFactoryFactory = getDelegateFactoryFactory(delegateFactoryFactory);

		@SuppressWarnings("null")@NonNull DelegateResourceSetAdapter adapter = DelegateResourceSetAdapter.getAdapter(resourceSet);

		// Install a local DelegateDomain.Factory
		DelegateDomain.Factory.Registry.Impl delegateDomainFactory = new DelegateDomain.Factory.Registry.Impl();
		if (forceInitialization || !delegateDomainFactory.containsKey(oclDelegateURI)) {
			delegateDomainFactory.put(oclDelegateURI, delegateFactoryFactory.createDelegateDomainFactory());
		}
		if (forceInitialization || (getDelegateResourceSetAdapterRegistry(adapter, DelegateDomain.Factory.Registry.class) == null)) {
			putDelegateResourceSetRegistry(adapter, DelegateDomain.Factory.Registry.class, delegateDomainFactory);
		}

		// Install a local ValidationDelegate.Factory
		ValidationDelegate.Factory.Registry validationDelegateFactoryRegistry = new ValidationDelegate.Factory.Registry.Impl();
		if (forceInitialization || !validationDelegateFactoryRegistry.containsKey(oclDelegateURI)) {
			OCLValidationDelegateFactory validationDelegateFactory = delegateFactoryFactory.createValidationDelegateFactory(oclDelegateURI);
			validationDelegateFactoryRegistry.put(oclDelegateURI, validationDelegateFactory);
		}
		if (forceInitialization || (getDelegateResourceSetAdapterRegistry(adapter, ValidationDelegate.Factory.Registry.class) == null)) {
			putDelegateResourceSetRegistry(adapter, ValidationDelegate.Factory.Registry.class, validationDelegateFactoryRegistry);
		}
	}

	/**
	 * Initialize the local resourceSet delegate registries to support the oclDelegateURI.
	 * A non-null delegateFactoryFactory may be specified for test purposes to intercept factory creation.
	 * @since 1.23
	 */
	public static void lazyInitializeLocals(@NonNull ResourceSet resourceSet, @NonNull String oclDelegateURI, boolean forceInitialization,
			@Nullable FactoryFactory delegateFactoryFactory) {
		delegateFactoryFactory = getDelegateFactoryFactory(delegateFactoryFactory);

		// Install a DelegateResourceSetAdapter to supervise local registries and resource post-loading
		@SuppressWarnings("null")@NonNull DelegateResourceSetAdapter adapter = DelegateResourceSetAdapter.getAdapter(resourceSet);
		VirtualDelegateMapping delegationMode = CommonOptions.DEFAULT_DELEGATION_MODE;
		if (forceInitialization || (getDelegateResourceSetAdapterRegistry(adapter, VirtualDelegateMapping.class) == null)) {
			putDelegateResourceSetRegistry(adapter, VirtualDelegateMapping.class, new VirtualDelegateMapping(delegationMode.getPluginId(), delegationMode.getKey(), delegationMode.getPreferredValue()));
		}

		// Install a local DelegateDomain.Factory and a local ValidationDelegate.Factory
		lazyInitializeLocalValidationRegistry(resourceSet, oclDelegateURI, forceInitialization, delegateFactoryFactory);

		// Install a local SettingDelegate.Factory
		EStructuralFeature.Internal.SettingDelegate.Factory.Registry settingDelegateFactoryRegistry = new EStructuralFeature.Internal.SettingDelegate.Factory.Registry.Impl();
		if (forceInitialization || !settingDelegateFactoryRegistry.containsKey(oclDelegateURI)) {
			OCLSettingDelegateFactory settingDelegateFactory = delegateFactoryFactory.createSettingDelegateFactory(oclDelegateURI);
			settingDelegateFactoryRegistry.put(oclDelegateURI, settingDelegateFactory);
		}
		if (forceInitialization || (getDelegateResourceSetAdapterRegistry(adapter, EStructuralFeature.Internal.SettingDelegate.Factory.Registry.class) == null)) {
			putDelegateResourceSetRegistry(adapter, EStructuralFeature.Internal.SettingDelegate.Factory.Registry.class, settingDelegateFactoryRegistry);
		}

		// Install a local InvocationDelegate.Factory
		EOperation.Internal.InvocationDelegate.Factory.Registry invocationDelegateFactoryRegistry = new EOperation.Internal.InvocationDelegate.Factory.Registry.Impl();
		if (forceInitialization || !invocationDelegateFactoryRegistry.containsKey(oclDelegateURI)) {
			OCLInvocationDelegateFactory invocationDelegateFactory = delegateFactoryFactory.createInvocationDelegateFactory(oclDelegateURI);
			invocationDelegateFactoryRegistry.put(oclDelegateURI, invocationDelegateFactory);
		}
		if (forceInitialization || (getDelegateResourceSetAdapterRegistry(adapter, EOperation.Internal.InvocationDelegate.Factory.Registry.class) == null)) {
			putDelegateResourceSetRegistry(adapter, EOperation.Internal.InvocationDelegate.Factory.Registry.class, invocationDelegateFactoryRegistry);
		}

		// Install a local QueryDelegate.Factory
		QueryDelegate.Factory.Registry queryDelegateFactoryRegistry = new QueryDelegate.Factory.Registry.Impl();
		if (forceInitialization || !queryDelegateFactoryRegistry.containsKey(oclDelegateURI)) {
			OCLQueryDelegateFactory queryDelegateFactory = delegateFactoryFactory.createQueryDelegateFactory(oclDelegateURI);
			queryDelegateFactoryRegistry.put(oclDelegateURI, queryDelegateFactory);
		}
		if (forceInitialization || (getDelegateResourceSetAdapterRegistry(adapter, QueryDelegate.Factory.Registry.class) == null)) {
			putDelegateResourceSetRegistry(adapter, QueryDelegate.Factory.Registry.class, queryDelegateFactoryRegistry);
		}
	}

	// FIXME workaround BUG 485093 giving a false non-null analysis
	private static @Nullable <T> T putDelegateResourceSetRegistry(@NonNull DelegateResourceSetAdapter adapter, @NonNull Class<T> registryClass, @NonNull T newRegistry) {
		return adapter.putRegistry(registryClass, newRegistry);
	}

	protected final @NonNull String uri;
	protected final @NonNull EPackage ePackage;
	// FIXME Introduce a lightweight function (? a lambda function) to avoid the need for a CompleteEnvironment for queries
	//	private Map<CompletePackage, org.eclipse.ocl.pivot.Package> queryPackages = null;
	//	private Map<CompleteType, org.eclipse.ocl.pivot.Class> queryTypes = null;

	/**
	 * Initializes me with my delegate URI and package.
	 *
	 * @param delegateURI
	 *            the delegate namespace I handle
	 * @param ePackage
	 *            the package that I handle
	 *
	 * @throws ParserException
	 *             if the operation's OCL body expression is invalid
	 */
	public OCLDelegateDomain(@NonNull String delegateURI, @NonNull EPackage ePackage) {
		this.uri = delegateURI;
		this.ePackage = ePackage;
	}

	public <T extends Element> @Nullable T getPivot(@NonNull Class<T> requiredClass, @NonNull EObject eObject) {
		EnvironmentFactory eEnvironmentFactory = PivotUtil.getEnvironmentFactory(eObject);
		try {
			return eEnvironmentFactory.getASOf(requiredClass, eObject);
		} catch (ParserException e) {
			return null;
		}
	}

	@Override
	public final @NonNull String getURI() {
		return uri;
	}

	@Override
	public synchronized void reset() {}

	@Override
	public String toString() {
		return ePackage.getName() + " : " + getURI(); //$NON-NLS-1$
	}
}
