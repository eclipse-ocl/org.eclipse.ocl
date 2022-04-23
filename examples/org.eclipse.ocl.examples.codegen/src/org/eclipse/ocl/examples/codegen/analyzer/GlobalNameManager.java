/*******************************************************************************
 * Copyright (c) 2013, 2022 CEA LIST and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink(CEA LIST) - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.codegen.analyzer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.UniqueList;

/**
 * A NameManager provides suggestions for names and maintains caches of used names so that model elements are consistently
 * named without collisions.
 */
public class GlobalNameManager extends NameManager
{
	/**
	 * A NameVariant specifies an algorithm for creating a variant of some name.
	 */
	public static interface NameVariant
	{
		@NonNull String getName(@NonNull String name);

		/**
		 * Return true if there is guaranteed to be only one use of the variant per context; typically one of many
		 * variables for an iteration or function.
		 *
		 * Return false if CSE may fail to avoid duplicates; typically SAFE guards.
		 */
		boolean isSingleton();

		@Override
		@NonNull String toString();
	}

	/**
	 * A NameVariantPreferred specifies apreferred (local) name spelling that is likely to be acceptable but may
	 * get renamed if necessary.
	 */
	private static class NameVariantPreferred implements NameVariant
	{
		protected final @NonNull String name;
		protected final boolean isSingleton;


		private NameVariantPreferred(@NonNull String name, boolean isSingleton) {
			this.name = name;
			this.isSingleton = isSingleton;
		}

		@Override
		public boolean equals(Object that) {
			return (that instanceof NameVariantPreferred) && this.name.equals(((NameVariantPreferred)that).name);
		}

		@Override
		public int hashCode() {
			return name.hashCode();
		}

		@Override
		public @NonNull String getName(@NonNull String name) {
			return this.name;
		}

		@Override
		public boolean isSingleton() {
			return isSingleton;
		}

		@Override
		public @NonNull String toString() {
			return name;
		}
	}

	/**
	 * A NameVariantPrefix specifies an algorithm for creating a variant that applirs a prefix to some name.
	 */
	private static class NameVariantPrefix implements NameVariant
	{
		protected final @NonNull String prefix;
		protected final boolean isSingleton;

		private NameVariantPrefix(@NonNull String prefix, boolean isSingleton) {
			this.prefix = prefix;
			this.isSingleton = isSingleton;
		}

		@Override
		public boolean equals(Object that) {
			return (that instanceof NameVariantPrefix) && this.prefix.equals(((NameVariantPrefix)that).prefix);
		}

		@Override
		public int hashCode() {
			return prefix.hashCode();
		}

		@Override
		public @NonNull String getName(@NonNull String name) {
			return prefix + name;
		}

		@Override
		public boolean isSingleton() {
			return isSingleton;
		}

		@Override
		public @NonNull String toString() {
			return prefix + "...";
		}
	}

	/**
	 * The value name assignments.
	 */
	private final @NonNull Context context;

	/**
	 * The distinct value name spaces.
	 */
	private final @NonNull List<@NonNull NameVariant> nameVariants = new UniqueList<>();

	/**
	 * The actual unique global name for each reserved name.
	 */
	private final @NonNull Map<@NonNull String, @NonNull NameResolution> name2reservedNameResolutions = new HashMap<>();

	public GlobalNameManager(@NonNull NameManagerHelper helper) {
		super(null, helper);
		this.context = new Context(this);			// Global can allocate names straightawy
	}

	public @NonNull NameVariant addNameVariantPreferred(@NonNull String name, boolean isSingleton) {
		NameVariant nameVariant = new NameVariantPreferred(name, isSingleton);
		assert nameVariants.add(nameVariant);
		return nameVariant;
	}

	public @NonNull NameVariant addNameVariantPrefix(@NonNull String prefix, boolean isSingleton) {
		NameVariant nameVariant = new NameVariantPrefix(prefix, isSingleton);
		assert nameVariants.add(nameVariant);
		return nameVariant;
	}

	public void assignNames() {
		assignNames(context);
	}

	/**
	 * Declare that cgElement has a name which should immediately default to nameHint.
	 * This is typically used to provide an eager name reservation for globl particularly Ecore names.
	 */
	public @NonNull BaseNameResolution declareGlobalName(@Nullable CGValuedElement cgElement, @NonNull String nameHint) {
		BaseNameResolution baseNameResolution = new BaseNameResolution(this, cgElement, nameHint);
		baseNameResolution.resolveIn(context);
		return baseNameResolution;
	}

	/**
	 * Declare that cgElement has a name which should immediately equate to nameHint.
	 * This is typically used to ensure that reserved Java names are used only for their Java purpose.
	 */
	@Override
	public @NonNull BaseNameResolution declareReservedName(@Nullable CGValuedElement cgElement, @NonNull String nameHint) {
		BaseNameResolution baseNameResolution = new BaseNameResolution(this, cgElement, nameHint);
		assert reservedJavaNames.contains(nameHint);
		baseNameResolution.setResolvedName(nameHint);
		return baseNameResolution;
	}

	@Override
	protected @NonNull Context getContext() {
		return context;
	}

	@Override
	protected @NonNull String getLazyNameHint(@NonNull CGValuedElement cgNamedValue) {
		return helper.getNameHint(cgNamedValue);
	}

	public @NonNull String getReservedName(@NonNull String name) {
		return ClassUtil.nonNullState(name2reservedNameResolutions.get(name)).getResolvedName();
	}

	@Override
	public boolean isGlobal() {
		return true;
	}

	@Override
	public @NonNull String toString() {
		return "globals";
	}
}
