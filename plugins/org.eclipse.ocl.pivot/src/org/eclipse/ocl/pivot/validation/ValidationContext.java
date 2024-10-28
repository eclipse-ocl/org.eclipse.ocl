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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.ecore.EValidator;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal;
import org.eclipse.ocl.pivot.internal.utilities.PivotUtilInternal;
import org.eclipse.ocl.pivot.utilities.EnvironmentFactory;
import org.eclipse.ocl.pivot.utilities.LabelUtil;

/**
 * ValidationContext reifies the Map<Object,Object> context argument for the EValidator API.
 *
 * @since 1.20
 */
public class ValidationContext extends HashMap<Object,Object>
{
	private static final long serialVersionUID = 1L;

	/**
	 * Return the current EnvironmentFactory preferably from the cached validationContext entry, else falling back on PivotUtilInternal.getEnvironmentFactory(Notifier)
	 * and caching the result for subsequent use.
	 *
	 * @since 1.23
	 */
	public static @NonNull EnvironmentFactoryInternal getEnvironmentFactory(Map<Object, Object> validationContext, @Nullable Object object) {
		if (validationContext != null) {
			Object environmentFactory = validationContext.get(EnvironmentFactory.class);
			if (environmentFactory != null) {
				return (EnvironmentFactoryInternal)environmentFactory;
			}
		}
		EnvironmentFactoryInternal environmentFactory;
		if (object instanceof Notifier) {
			environmentFactory = PivotUtilInternal.getEnvironmentFactory((Notifier)object);
			if (validationContext != null) {
				validationContext.put(EnvironmentFactory.class, environmentFactory);
			}
		}
		else {
			// In the unlikely (? impossible) event that an EDataType validation occurs before an EClass validation caches an appropriate
			//  ResourceSet-specific EnvironmentFactory, use but do not cache a global EnvironmentFactory.
			System.out.println("EDataType validation without prior eClass validation");
			environmentFactory = PivotUtilInternal.getEnvironmentFactory((Notifier)null);
		}
		return environmentFactory;
	}

	/**
	 * @since 1.23
	 */
	public ValidationContext() {}

	public ValidationContext(EValidator.@NonNull Registry validationRegistry) {
		Diagnostician diagnostician = createDiagnostician(validationRegistry);
	    put(EValidator.class, diagnostician);
	    put(EValidator.Registry.class, validationRegistry);
	//	put(EValidator.SubstitutionLabelProvider.class, diagnostician);
		put(EValidator.SubstitutionLabelProvider.class, LabelUtil.SUBSTITUTION_LABEL_PROVIDER);
	}

	/**
	 * Return the current EnvironmentFactory preferably from the cached validationContext entry, else falling back on PivotUtilInternal.basicGetEnvironmentFactory(Notifier).
	 * Returns null if no EnvironmentFactory available.
	 *
	 * @since 1.23
	 */
	public static @Nullable EnvironmentFactoryInternal basicGetEnvironmentFactory(Map<Object, Object> validationContext, @Nullable Notifier notifier) {
		if (validationContext != null) {
			Object environmentFactory = validationContext.get(EnvironmentFactory.class);
			if (environmentFactory != null) {
				return (EnvironmentFactoryInternal)environmentFactory;
			}
		}
		return PivotUtilInternal.basicGetEnvironmentFactory(notifier);
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