/*******************************************************************************
 * Copyright (c) 2013, 2018 CEA LIST and others.
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
	 * A GlobalName wraps the globally unique actual spelling for a requested spelling.
	 *
	public static class GlobalName
	{
		protected final @NonNull String name;

		private GlobalName(@NonNull String name) {
			this.name = name;
		}

		public @NonNull String getName() {
			return name;
		}

		@Override
		public @NonNull String toString() {
			return name;
		}
	} */

	/**
	 * A NameVariant specifies an algorithm for creating a variant of some name.
	 */
	public static interface NameVariant
	{
		@NonNull String getName(@NonNull String name);

		@Override
		@NonNull String toString();
	}

	/**
	 * A NameVariantPrefix specifies an algorithm for creating a variant that applirs a prefix to some name.
	 */
	private static class NameVariantPrefix implements NameVariant
	{
		protected final @NonNull String prefix;

		private NameVariantPrefix(@NonNull String prefix) {
			this.prefix = prefix;
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
		public @NonNull String toString() {
			return prefix + "...";
		}
	}

	/**
	 * The value name assignments.
	 */
	private final @NonNull Context context;

	/**
	 * The distinct value name spaces indexed by their lexical prefix.
	 */
//	private final @NonNull Map<@NonNull String, @NonNull PrefixedValueNames> prefix2prefixedValueNames = new HashMap<>();
	private final @NonNull List<@NonNull NameVariant> nameVariants = new UniqueList<>();

	/**
	 * The actual unique global name for each reserved name.
	 */
	private final @NonNull Map<@NonNull String, @NonNull NameResolution> name2reservedNameResolutions = new HashMap<>();

	public GlobalNameManager(@NonNull NameManagerHelper helper) {
		super(null, helper);
		this.context = new Context(this);			// Global can allocate names straightawy
	}

/*	public void assignValueName(@NonNull Object object, @NonNull PrefixedValueNames prefixedValueNames, @NonNull String valueName) {
		if (object instanceof CGLocalVariable) {
			getClass();		// XXX
		}
		if ("collect".equals(valueName)) {
			getClass();		// XXX
		}
		assert !(object instanceof CGValuedElement) || ((CGValuedElement)object).getName() != null;
		prefixedValueNames.putValueName(object, valueName);
	} */

	public @NonNull NameVariant addNameVariantPrefix(@NonNull String prefix) {
		NameVariant nameVariant = new NameVariantPrefix(prefix);
		assert nameVariants.add(nameVariant);
		return nameVariant;
	}

/*	public void assignNameManager(@NonNull Object anObject, @NonNull NameManager nameManager) {
		NameManager old = object2nameManager.put(anObject, nameManager);
		assert (old == null) || (old == nameManager) : "Duplicate NameManager for " + anObject;
	} */

	public void assignNames() {
		assignNames(context);
	}

	public @NonNull NameResolution declareGlobalName(@Nullable CGValuedElement object, @NonNull String name) {
		NameResolution nameResolution = new NameResolution(this, object, name);
		nameResolution.resolveIn(context);
		return nameResolution;
	}

	/**
	 * Assign the resolved spelling of the shared global name for use by cgValuedElement.
	 *
	 * The caller is responsible for ensuring that the multiple shared usages do not occur in conflicting code contexts.
	 * For instance the same name may be used for many Operation Parameters.
	 *
	public void declareGlobalValue(@NonNull CGValuedElement cgValuedElement, @NonNull GlobalName globalName) {
		NameResolution nameResolution = new GlobalNameResolution(this, cgValuedElement, globalName.getName());
		addNameResolution(nameResolution);
	} */

	public @NonNull NameResolution declareReservedName(@Nullable CGValuedElement object, @NonNull String name) {
		NameResolution nameResolution = new NameResolution(this, object, name);
		assert reservedJavaNames.contains(name);
		nameResolution.setResolvedName(name);
		return nameResolution;
	}

	@Override
	protected @NonNull Context getContext() {
		return context;
	}

/*	public @NonNull GlobalName getGlobalName(@NonNull String name) {
		GlobalName globalName = name2globalNames.get(name);
		String uniqueName;
		if (globalName == null) {
			assert !context.hasKey(name);
			uniqueName = name;
		}
		else {
			uniqueName = context.allocateUniqueName(name, NOT_AN_OBJECT);
		}
		globalName = new GlobalName(uniqueName);
		name2globalNames.put(name, globalName);
		return globalName;
	} */

	public @NonNull String getReservedName(@NonNull String name) {
		return ClassUtil.nonNullState(name2reservedNameResolutions.get(name)).getResolvedName();
	}

	/**
	 * GlobalNameManager override reserves names immediately rather than queuing for assignValueNames().
	 *
	@Override
	public @NonNull String queueValueName(@Nullable String nameHint, @Nullable PrefixedValueNames prefixedValueNames, @NonNull Object anObject) {
		assert !(anObject instanceof String);					// XXX
		if (prefixedValueNames == null) {
			prefixedValueNames = defaultPrefixedValueNames;;
		}
		if (nameHint == null) {
			nameHint = (String) helper.getNameHint(anObject);
			if (nameHint == null) {
				nameHint = (String) helper.getNameHint(anObject);		// XXX debugging
				nameHint = "XXX";				// XXX
			}
		}
		assert nameHint != null;
		reserveName(nameHint, anObject, prefixedValueNames);
	/*	if (anObject instanceof CGValuedElement) {
			CGValuedElement cgValuedElement = (CGValuedElement)anObject;
			assert (cgValuedElement.getName() == null) || (cgValuedElement.getName().equals(nameHint));
			nameHint = prefixedValueNames.prefix + nameHint;
			cgValuedElement.setName(nameHint);
		}
		prefixedValueNames.putValueName(anObject, zznameHint); * /
		return nameHint;
	} */

	@Override
	public @NonNull String toString() {
		return "globals";
	}
}
