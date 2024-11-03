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

import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.ecore.EValidator;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.eclipse.jdt.annotation.NonNull;
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