/*******************************************************************************
 * Copyright (c) 2014, 2025 CEA LIST and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink (CEA LIST) - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.internal.utilities;

import java.util.Map;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EValidator;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.eclipse.emf.ecore.util.EObjectValidator;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.internal.delegate.OCLDelegateDomain;
import org.eclipse.ocl.pivot.internal.resource.ASResourceFactoryRegistry;
import org.eclipse.ocl.pivot.util.DerivedConstants;
import org.eclipse.ocl.pivot.validation.ValidationRegistryAdapter;

public abstract class PivotDiagnostician extends Diagnostician
{
	private static Boolean diagnosticianHasDoValidate = null; // Use 2.9/2.8 Diagnostician

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
	 * BasicDiagnosticWithRemove supports the migration of child diagnostics to grandchildren in the more
	 * efficient validateFeatureDetail override.
	 *
	 * The enhancement is facilitated by a remove. The actual requirement is to split off all children after a
	 * given index for additional to another BasicDiagnostic. Perhaps a truncate(index) or split(index) might
	 * be better; many low level alternatives possible. Maybe just a getRawChildren().
	 *
	 * @since 1.4
	 */
	public static class BasicDiagnosticWithRemove extends BasicDiagnostic
	{
		/**
		 * This validate is a convenience method demonstrating how the BasicDiagnosticWithRemove enhancement
		 * can be exploited.
		 */
		public static BasicDiagnostic validate(EObject eObject, Map<Object, Object> validationContext) {
			Object object = validationContext.get(EValidator.class);
			Diagnostician diagnostician;
			if (object instanceof Diagnostician) {
				diagnostician = (Diagnostician)object;
			}
			else {
				diagnostician = ValidationRegistryAdapter.getDiagnostician(eObject);
			}
			BasicDiagnostic diagnostics = new BasicDiagnosticWithRemove(EObjectValidator.DIAGNOSTIC_SOURCE, 0, EcorePlugin.INSTANCE.getString("_UI_DiagnosticRoot_diagnostic", new Object[] { diagnostician.getObjectLabel(eObject) }), new Object [] { eObject });
			diagnostician.validate(eObject, diagnostics, validationContext);
			return diagnostics;
		}

		public BasicDiagnosticWithRemove(String source, int code, String message, Object[] data) {
			super(source, code, message, data);
		}

		public Diagnostic remove(int index) {
			return children.remove(index);
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