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

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.analyzer.GlobalNameManager.NameVariant;
import org.eclipse.ocl.examples.codegen.analyzer.NameManager.Context;
import org.eclipse.ocl.pivot.utilities.ClassUtil;

/**
 * A VariantNameResolution represents a future name that whose preferred resolution is algorithmically derived from
 * a BaseNameResolution using the algorithm defined by a NameVariant.
 *
 * All variants of a BaseNameResolution are resolved before any are used to ensure that a later declaration doesn'y clash
 * and so invalidate the uniqueness of names.
 */
public class VariantNameResolution extends AbstractNameResolution
{
	protected final @NonNull NameResolution baserNameResolution;
	protected final @NonNull NameVariant nameVariant;

	/**
	 * The resolved name based on nameHint after ensuring that it is unique at and below the nameManager. Non-null once resolved.
	 */
	private @Nullable String resolvedVariantName = null;

	protected VariantNameResolution(@NonNull NameResolution baserNameResolution, @NonNull NameVariant nameVariant) {
		this.baserNameResolution = baserNameResolution;
		this.nameVariant = nameVariant;
	}

	@Override
	public @Nullable String basicGetResolvedName() {
		return resolvedVariantName;
	}

	@Override
	public @NonNull BaseNameResolution getBaseNameResolution() {
		return baserNameResolution.getBaseNameResolution();
	}

	@Override
	public @NonNull String getNameHint() {
		return getBaseNameResolution().getNameHint();		// Undesirable but better to have disambiguating suffix that cascaded prefixes
	}

	@Override
	public @NonNull NameManager getNameManager() {
		return baserNameResolution.getNameManager();
	}

	public @NonNull NameVariant getNameVariant() {
		return nameVariant;
	}

	@Override
	public @NonNull String getResolvedName() {
		return ClassUtil.nonNullState(resolvedVariantName);
	}

	protected void resolveVariant(@NonNull Context context, @NonNull Object cgElement, @NonNull String nameHint) {
		String variantNameHint = nameVariant.getName(nameHint);
		String resolvedVariantName = context.allocateUniqueName(variantNameHint, cgElement);
		if ("getSeverity".equals(resolvedVariantName)) {
			getClass();		// XXX
		}
		this.resolvedVariantName = resolvedVariantName;
		resolveVariants(context, cgElement);
	}

	@Override
	public @NonNull String toString() {
		return getNameManager() + ":" + getNameHint() + " => " + (resolvedVariantName != null ? resolvedVariantName : "???");
	}
}