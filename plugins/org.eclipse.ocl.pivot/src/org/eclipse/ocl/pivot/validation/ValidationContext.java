/*******************************************************************************
 * Copyright (c) 2023, 2025 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.validation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EValidator;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.internal.resource.ASResourceFactoryRegistry;
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal;
import org.eclipse.ocl.pivot.resource.BasicProjectManager;
import org.eclipse.ocl.pivot.utilities.EnvironmentFactory;
import org.eclipse.ocl.pivot.utilities.LabelUtil;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.utilities.ThreadLocalExecutor;
import org.eclipse.ocl.pivot.utilities.ThreadLocalExecutor.InitWrapperCallBack;

/**
 * ValidationContext reifies the Map<Object,Object> context argument for the EValidator API.
 *
 * @since 1.20
 */
public class ValidationContext extends HashMap<Object,Object>
{
	private static final long serialVersionUID = 1L;

	/**
	 * Return the current EnvironmentFactory preferably from the cached validationContext entry, else falling back on PivotUtil.basicGetEnvironmentFactory(Notifier).
	 * Returns null if no EnvironmentFactory available.
	 *
	 * @since 7.0
	 */
	public static @Nullable EnvironmentFactoryInternal basicGetEnvironmentFactory(Map<Object, Object> validationContext, @Nullable Object object) {
		if (validationContext != null) {
			Object environmentFactory = validationContext.get(EnvironmentFactory.class);
			if (environmentFactory != null) {
				return (EnvironmentFactoryInternal)environmentFactory;
			}
		}
		EnvironmentFactoryInternal environmentFactory = PivotUtil.basicGetEnvironmentFactory(object);
		if ((environmentFactory != null) && (validationContext != null)) {
			validationContext.put(EnvironmentFactory.class, environmentFactory);
		}
		return environmentFactory;
	}

	/**
	 * Return the current EnvironmentFactory preferably from the cached validationContext entry,
	 * else falling back on a cached/created AdapterFactoryEditingDomainResourceSet's ThreadLOcalExecutor cache
	 * else falling back on PivotUtil.getEnvironmentFactory(Notifier)
	 * and caching the result for subsequent use.
	 *
	 * @since 7.0
	 */
	public static @NonNull EnvironmentFactoryInternal getEnvironmentFactory(Map<Object, Object> validationContext, @Nullable Object object) {
		//
		//	If already cached by validationContext, re-use.
		//
		if (validationContext != null) {
			Object environmentFactory = validationContext.get(EnvironmentFactory.class);
			if (environmentFactory != null) {
				return (EnvironmentFactoryInternal)environmentFactory;
			}
		}
		//
		//	If already cached by the ThreadLocalExecutor, re-use.
		//
		EnvironmentFactoryInternal environmentFactory = PivotUtil.basicGetEnvironmentFactory(object);
		if (environmentFactory != null) {
			return environmentFactory;
		}
		//
		//	If validating probably as part of a GenModelEditor's AdapterFactoryEditingDomainResourceSet,
		//	re-use/create the per-EditingDomain EnvironmentFactoryInternal cached by the ThreadLocalExecutor.
		//
		if (object instanceof EObject) {
			EObject eObject = (EObject)object;
			Resource eResource = eObject.eResource();
			if (eResource != null) {
				final ResourceSet resourceSet = eResource.getResourceSet();
				if (resourceSet instanceof IEditingDomainProvider) {			// AdapterFactoryEditingDomainResourceSet
					EditingDomain editingDomain = ((IEditingDomainProvider)resourceSet).getEditingDomain();
					assert editingDomain != null;
					InitWrapperCallBack<@NonNull EnvironmentFactoryInternal, @Nullable Object> callBack = new InitWrapperCallBack<@NonNull EnvironmentFactoryInternal, @Nullable Object>()
					{
						protected @Nullable EnvironmentFactoryInternal environmentFactoryInternal = null;

						@Override
						public @NonNull EnvironmentFactoryInternal getResult() {
							assert environmentFactoryInternal != null;
							return environmentFactoryInternal;
						}

						@Override
						public void run() {
							ResourceSet resourceSet2 = editingDomain.getResourceSet();
							assert resourceSet2 == resourceSet;		// XXX debugging
							environmentFactoryInternal = ASResourceFactoryRegistry.INSTANCE.createEnvironmentFactory(BasicProjectManager.createDefaultProjectManager(), resourceSet2, null);
						}
					};
					ThreadLocalExecutor.init(editingDomain, callBack);
					environmentFactory = callBack.getResult();
					ThreadLocalExecutor.setUsesFinalizer();			// XXX not on a worker thread ?? part threads may also use fiunalizer ??
				}
			}
		}
		//
		//	Create an EnvironmentFactoryInternal cached by the ThreadLocalExecutor.
		//
		if (environmentFactory == null) {
			if (object instanceof Notifier) {
				environmentFactory = PivotUtil.getEnvironmentFactory((Notifier)object);
			}
			else {
				// In the unlikely (? impossible) event that an EDataType validation occurs before an EClass validation caches an appropriate
				//  ResourceSet-specific EnvironmentFactory, use but do not cache a global EnvironmentFactory.
				System.out.println("EDataType validation without prior eClass validation");
				environmentFactory = PivotUtil.getEnvironmentFactory(null);
			}
		}
		//
		//	Cache it in the ValidationContext
		//
		if (validationContext != null) {
			validationContext.put(EnvironmentFactory.class, environmentFactory);
		}
		return environmentFactory;
	}

	/**
	 * @since 7.0
	 */
	public ValidationContext() {}

	public ValidationContext(EValidator.@NonNull Registry validationRegistry) {
		Diagnostician diagnostician = createDiagnostician(validationRegistry);
	    put(EValidator.class, diagnostician);
	    put(EValidator.Registry.class, validationRegistry);
	//	put(EValidator.SubstitutionLabelProvider.class, diagnostician);
		put(EValidator.SubstitutionLabelProvider.class, LabelUtil.SUBSTITUTION_LABEL_PROVIDER);
	}

	protected Diagnostician createDiagnostician(EValidator.@NonNull Registry validationRegistry) {
		return new Diagnostician(validationRegistry);
	}

	public @NonNull List<@NonNull Diagnostic> createDiagnostics() {
		return new ArrayList<>();
	}

	@SuppressWarnings("null")
	public @NonNull Diagnostician getDiagnostician() {
		return (Diagnostician)get(EValidator.class);
	}

	@SuppressWarnings("null")
	public EValidator.@NonNull Registry getValidationRegistry() {
		return (EValidator.@NonNull Registry)get(EValidator.Registry.class);
	}
}