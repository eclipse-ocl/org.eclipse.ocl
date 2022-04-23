/*******************************************************************************
 * Copyright (c) 2022 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.codegen.analyzer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.analyzer.GlobalNameManager.NameVariant;
import org.eclipse.ocl.examples.codegen.analyzer.NameManager.Context;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.cgmodel.CGVariableExp;

public abstract class AbstractNameResolution implements NameResolution
{
	/**
	 * Additional CGElements that propgate the unchanged value to be accessed by the resolved name.
	 */
	private @Nullable List<@NonNull CGValuedElement> cgElements = null;

	/**
	 * Additional variants of resolvedName for which further unique names are required.
	 */
	private @Nullable Map<@NonNull NameVariant, @NonNull VariantNameResolution> nameVariant2variantNameResolution = null;

	protected AbstractNameResolution() {
		super();
	}

	@Override
	public void addCGElement(@NonNull CGValuedElement cgElement) {
	//	if ("gt".equals(nameHint) ) {
	//		getClass();		// XXX
	//	}
		List<@NonNull CGValuedElement> cgElements2 = cgElements;
		if (cgElements2 == null) {
			cgElements = cgElements2 = new ArrayList<>();
		}
		else {
			assert !cgElements2.contains(cgElement);
		}
		cgElements2.add(cgElement);
		cgElement.setNameResolution(this);
		getNameManager().addNameResolution(cgElement);
	//	System.out.println("addCGElement '" + this + "' : " + cgElement.eClass().getName() + ":" + cgElement);
	}

	@Override
	public @NonNull VariantNameResolution addNameVariant(@NonNull NameVariant nameVariant) {
		assert (basicGetResolvedName() == null) || ((NestedNameManager)getNameManager()).isReserved(this) : "Cannot addNameVariant after name is resolved";
		Map<@NonNull NameVariant, @NonNull VariantNameResolution> nameVariant2variantNameResolution2 = nameVariant2variantNameResolution;
		if (nameVariant2variantNameResolution2 == null) {
			nameVariant2variantNameResolution = nameVariant2variantNameResolution2 = new HashMap<>();
		}
		else {
		//	assert !nameVariant2variantNameResolution2.containsKey(nameVariant);
			VariantNameResolution variantNameResolution = nameVariant2variantNameResolution2.get(nameVariant);
			if (variantNameResolution != null) {
				return variantNameResolution;
			}
		}
		VariantNameResolution variantNameResolution = new VariantNameResolution(this, nameVariant);
		nameVariant2variantNameResolution2.put(nameVariant, variantNameResolution);
		return variantNameResolution;
	}

	@Override
	public @Nullable Iterable<@NonNull CGValuedElement> getCGElements() {
		return cgElements;
	}

/*	@Override
	public @NonNull VariantNameResolution getNameVariant(@NonNull NameVariant nameVariant) {
		Map<@NonNull NameVariant, @NonNull VariantNameResolution> nameVariant2variantNameResolution2 = nameVariant2variantNameResolution;
		if (nameVariant2variantNameResolution2 == null) {
			return addNameVariant(nameVariant);
		}
		VariantNameResolution variantNameResolution = nameVariant2variantNameResolution2.get(nameVariant);
		assert variantNameResolution != null;
		return variantNameResolution;
	} */

	@Override
	public @NonNull String getVariantResolvedName(@NonNull NameVariant nameVariant) {
		assert nameVariant2variantNameResolution != null;
		VariantNameResolution variantNameResolution = nameVariant2variantNameResolution.get(nameVariant);
		assert variantNameResolution != null;
		return variantNameResolution.getResolvedName();
	}

	@Override
	public boolean hasVariants() {
		return (nameVariant2variantNameResolution != null) && (nameVariant2variantNameResolution.size() > 0);
	}

	@Override
	public void removeCGElement(@NonNull CGVariableExp cgElement) {
	//	if ("gt".equals(nameHint) ) {
	//		getClass();		// XXX
	//	}
		List<@NonNull CGValuedElement> cgElements2 = cgElements;
		assert cgElements2 != null;
		assert cgElements2.contains(cgElement);
		cgElements2.remove(cgElement);
		getNameManager().removeNameResolution(cgElement);
		cgElement.resetNameResolution();
	//	cgElement.setNameResolution(this);
	//	System.out.println("addCGElement '" + this + "' : " + cgElement.eClass().getName() + ":" + cgElement);
	}

	protected void resolveVariants(@NonNull Context context, @NonNull Object cgElement) {
		Map<@NonNull NameVariant, @NonNull VariantNameResolution> nameVariant2variantNameResolution2 = nameVariant2variantNameResolution;
		if (nameVariant2variantNameResolution2 != null) {
			for (@NonNull VariantNameResolution variantNameResolution : nameVariant2variantNameResolution2.values()) {
				variantNameResolution.resolveVariant(context, cgElement, getResolvedName());
			}
		}
	}
}