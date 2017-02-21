/*******************************************************************************
 * Copyright (c) 2014, 2016 CEA LIST and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   E.D.Willink (CEA LIST) - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.internal.utilities;

import java.lang.ref.WeakReference;
import java.lang.reflect.Method;
import java.util.Map;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EValidator;
import org.eclipse.emf.ecore.impl.EValidatorRegistryImpl;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.internal.delegate.OCLDelegateDomain;
import org.eclipse.ocl.pivot.internal.resource.ASResourceFactory;
import org.eclipse.ocl.pivot.internal.resource.ASResourceFactoryRegistry;
import org.eclipse.ocl.pivot.util.DerivedConstants;
import org.eclipse.ocl.pivot.utilities.OCL;

public abstract class PivotDiagnostician extends Diagnostician
{
	private static Boolean diagnosticianHasDoValidate = null; // Use 2.9/2.8 Diagnostician

	public static @NonNull Diagnostician createDiagnostician(@NonNull ResourceSet resourceSet,
			AdapterFactory adapterFactory, @Nullable IProgressMonitor progressMonitor) {
		EValidatorRegistryImpl registry = new EValidatorRegistryImpl();
		for (ASResourceFactory asResourceFactory : ASResourceFactoryRegistry.INSTANCE.getExternalResourceFactories()) {
			asResourceFactory.initializeEValidatorRegistry(registry);
		}
		if (diagnosticianHasDoValidate == null) {
			diagnosticianHasDoValidate = false;
			for (Method method : Diagnostician.class.getDeclaredMethods()) {
				if ("doValidate".equals(method.getName())) {
					diagnosticianHasDoValidate = true;
				}
			}
		}
		if (diagnosticianHasDoValidate) {
			return new Diagnostician_2_9(registry, resourceSet, adapterFactory,
				progressMonitor != null ? progressMonitor : new NullProgressMonitor());
		}
		else {
			return new Diagnostician_2_8(registry, resourceSet, adapterFactory);
		}
	}

	/**
	 * Return the OCL context for the validation, caching the created value in the validation context for re-use by
	 * further validations. The cached reference is weak to ensure that the OCL context is disposed once no longer in use.
	 */
	public static @NonNull OCL getOCL(@NonNull Map<Object, Object> context) {
		OCL ocl = null;
		Object oclRef = context.get(WeakOCLReference.class);
		if (oclRef instanceof WeakOCLReference) {
			ocl = ((WeakOCLReference)oclRef).get();
		}
		if (ocl == null) {
			ocl = OCL.newInstance();
			context.put(WeakOCLReference.class, new WeakOCLReference(ocl));
		}
		return ocl;
	}

	public static void setOCL(@NonNull Map<Object, Object> context, @NonNull OCL ocl) {
		context.put(WeakOCLReference.class, new WeakOCLReference(ocl));
	}

	protected final static class Diagnostician_2_8 extends PivotDiagnostician
	{
		protected Diagnostician_2_8(EValidator.@NonNull Registry eValidatorRegistry,
				@NonNull ResourceSet resourceSet, AdapterFactory adapterFactory) {
			super(eValidatorRegistry, resourceSet, adapterFactory);
		}

		@Override
		public String getObjectLabel(EObject eObject) {
			if (adapterFactory != null && !eObject.eIsProxy()) {
				IItemLabelProvider itemLabelProvider = (IItemLabelProvider) adapterFactory.adapt(eObject, IItemLabelProvider.class);
				if (itemLabelProvider != null) {
					return itemLabelProvider.getText(eObject);
				}
			}
			return super.getObjectLabel(eObject);
		}
	}

	protected final static class Diagnostician_2_9 extends PivotDiagnostician
	{
		private final @NonNull ResourceSet resourceSet;
		private final @NonNull IProgressMonitor progressMonitor;

		protected Diagnostician_2_9(EValidator.@NonNull Registry eValidatorRegistry, @NonNull ResourceSet resourceSet,
				AdapterFactory adapterFactory, @NonNull IProgressMonitor progressMonitor) {
			super(eValidatorRegistry, resourceSet, adapterFactory);
			this.resourceSet = resourceSet;
			this.progressMonitor = progressMonitor;
		}

		@Override
		protected boolean doValidate(EValidator eValidator, EClass eClass, EObject eObject,
				DiagnosticChain diagnostics, Map<Object, Object> context) {
			progressMonitor.worked(1);
			synchronized (resourceSet) {
				return super.doValidate(eValidator, eClass, eObject, diagnostics, context);
			}
		}

		@Override
		public String getObjectLabel(EObject eObject) {
			if (adapterFactory != null && !eObject.eIsProxy()) {
				IItemLabelProvider itemLabelProvider = (IItemLabelProvider) adapterFactory.adapt(eObject, IItemLabelProvider.class);
				if (itemLabelProvider != null) {
					return itemLabelProvider.getText(eObject);
				}
			}
			return super.getObjectLabel(eObject);
		}
	}

	/**
	 * WeakOCLReference maintains the reference to the OCL context within the Diagnostician context and
	 * disposes of it once the Diagnostician is done.
	 */
	public static final class WeakOCLReference extends WeakReference<OCL>
	{
		protected final @NonNull OCL ocl;

		protected WeakOCLReference(@NonNull OCL ocl) {
			super(ocl);
			this.ocl = ocl;
		}

		@Override
		public void finalize() {
			new Thread("OCL-Finalizer")
			{
				@Override
				public void run() {
					ocl.dispose();
				}
			}.start();
		}
	}

	protected final AdapterFactory adapterFactory;
	protected final @NonNull Technology technology;

	protected PivotDiagnostician(EValidator.@NonNull Registry eValidatorRegistry, @NonNull ResourceSet resourceSet, AdapterFactory adapterFactory) {
		super(eValidatorRegistry);
		this.adapterFactory = adapterFactory;
		this.technology = ASResourceFactoryRegistry.INSTANCE.getTechnology();
		OCLDelegateDomain.initializePivotOnlyDiagnosticianResourceSet(resourceSet);
	}

	@Override
	public Map<Object, Object> createDefaultContext() {
		Map<Object, Object> context = super.createDefaultContext();
		if (context != null) {
			OCLDelegateDomain.initializePivotOnlyDiagnosticianContext(context);
		}
		return context;
	}

	@Override
	public boolean validate(EClass eClass, EObject eObject, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if ((eClass != null) && !technology.isValidatable(eClass)) {
			return true;
		}
		if ((eObject instanceof EAnnotation) && DerivedConstants.UML2_UML_PACKAGE_2_0_NS_URI.equals(((EAnnotation)eObject).getSource())) {	// FIXME move stereotype discard to technology
			return true;
		}
		return super.validate(eClass, eObject, diagnostics, context);
	}
}