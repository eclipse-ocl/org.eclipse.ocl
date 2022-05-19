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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.analyzer.GlobalNameManager.NameVariant;
import org.eclipse.ocl.examples.codegen.cgmodel.CGClass;
import org.eclipse.ocl.examples.codegen.cgmodel.CGForeignProperty;
import org.eclipse.ocl.examples.codegen.cgmodel.CGNamedElement;
import org.eclipse.ocl.examples.codegen.cgmodel.CGProperty;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.utilities.CGUtil;
import org.eclipse.ocl.pivot.utilities.ClassUtil;

/**
 * A NameManager provides suggestions for names and maintains caches of used names so that model elements are consistently
 * named without collisions.
 */
public class NestedNameManager extends NameManager
{
	protected final @NonNull NameManager parent;
	protected final @NonNull CGNamedElement cgScope;
	private @Nullable List<@NonNull NameResolution> reservedNameResolutions = null;

	/**
	 * The value name assignments.
	 */
	private @Nullable Context context = null;		// Non-null once value name allocation is permitted.

	/**
	 * Additional variants of resolvedName for which further unique names are required.
	 */
	private @NonNull Map<@NonNull CGNamedElement, @Nullable Map<@NonNull NameVariant, @Nullable String>> element2nameVariant2name = new HashMap<>();

	public NestedNameManager(@NonNull NameManager parent, @NonNull CGNamedElement cgScope) {
		super(parent, parent.helper);
		this.parent = parent;
		this.cgScope = cgScope;
		assert !(parent instanceof NestedNameManager) || (((NestedNameManager)parent).cgScope != cgScope);		// XXX
		parent.addChild(this);
	}

	public void addNameVariant(@NonNull CGNamedElement cgElement, @NonNull NameVariant nameVariant) {
	//	String resolvedName = getNameResolution().getResolvedName();
	//	assert resolvedName == null;
	//	assert (resolvedName == null) || ((NestedNameManager)getNameManager()).isReserved(this) : "Cannot addNameVariant after name is resolved";
		Map<@NonNull NameVariant, @Nullable String> nameVariant2name = element2nameVariant2name.get(cgElement);
		if (nameVariant2name == null) {
			nameVariant2name = new HashMap<>();
			element2nameVariant2name.put(cgElement, nameVariant2name);
		}
		String old = nameVariant2name.put(nameVariant, null);
		assert old == null;
	}

	public void assignExtraNames(@NonNull Context context) {
		for (Entry<@NonNull CGNamedElement, @Nullable Map<@NonNull NameVariant, @Nullable String>> entry1 : element2nameVariant2name.entrySet()) {
			Map<@NonNull NameVariant, @Nullable String> nameVariant2name = entry1.getValue();
			if (nameVariant2name != null) {
				CGNamedElement cgElement = entry1.getKey();
				assert cgElement.eContainer() != null;		// Not eliminated by CSE		-- ?? obsolete
				String resolvedName;
				if (cgElement instanceof CGValuedElement) {
					CGValuedElement cgValuedElement = (CGValuedElement)cgElement;
					NameResolution nameResolution = cgValuedElement.basicGetNameResolution();
					if (nameResolution == null) {
						nameResolution = declareLazyName(cgValuedElement);
						nameResolution.resolveNameHint();;
					}
					nameResolution.resolveIn(context, cgValuedElement);
					resolvedName = nameResolution.getResolvedName();
				}
				else {
					resolvedName = cgElement.getName();
				}
				for (Entry<@NonNull NameVariant, @Nullable String> entry2 : nameVariant2name.entrySet()) {
					NameVariant nameVariant = entry2.getKey();
					String name = entry2.getValue();
					assert name == null;
					String variantNameHint = nameVariant.getName(resolvedName);
					String variantName = context.allocateUniqueName(variantNameHint, cgElement);
					nameVariant2name.put(nameVariant, variantName);
				}
			}
		}
	}

	public void assignNames(@NonNull Map<@NonNull NameManager, @NonNull List<@NonNull CGValuedElement>> nameManager2namedElements) {
		Context context2 = context;
		assert context2 == null;
		this.context = context2 = new Context(this);
		assignReservedNames(context2);
		assignLocalNames(context2, nameManager2namedElements);
		assignExtraNames(context2);
		assignNestedNames(nameManager2namedElements);
	}

	protected void assignReservedNames(@NonNull Context context) {
		if (reservedNameResolutions != null) {
			for (@NonNull NameResolution nameResolution : reservedNameResolutions) {
				String resolvedName = nameResolution.getResolvedName();
				CGValuedElement primaryElement = nameResolution.getPrimaryElement();
				context.reserveName(resolvedName, primaryElement);
			}
		}
	}

	public @Nullable String basicGetVariantResolvedName(@NonNull CGNamedElement cgElement, @NonNull NameVariant nameVariant) {
		Map<@NonNull NameVariant, @Nullable String> nameVariant2name = element2nameVariant2name.get(cgElement);
		return nameVariant2name != null ? nameVariant2name.get(nameVariant) : null;
	}

	@Override
	public @NonNull NameResolution declareLazyName(@NonNull CGValuedElement cgElement) {
		if (cgElement.isGlobal()) {
			return globalNameManager.declareLazyName(cgElement);
		}
		return super.declareLazyName(cgElement);
	}

	/**
	 * Declare that cgElement has a name which can eventually default to its preferred value.
	 * This is typically used to provide an eager name resolution for a variable without reserving the name.
	 */
	public @NonNull NameResolution declarePreferredName(@NonNull CGValuedElement cgElement) {
	//	assert cgElement.getNamedValue() == cgElement;
		NameResolution nameResolution = cgElement.basicGetNameResolution();
		if (nameResolution != null) {
			return nameResolution;
		}
		String nameHint = helper.getNameHint(cgElement);
		return new NameResolution(this, cgElement, nameHint);
	}

	@Override
	public @NonNull NameResolution declareReservedName(@NonNull CGValuedElement cgElement, @NonNull String nameHint) {
		assert !cgElement.isGlobal();
		assert cgElement.getNamedValue() == cgElement;
		NameResolution nameResolution2 = cgElement.basicGetNameResolution();
		assert nameResolution2 == null;
		NameResolution baseNameResolution = new NameResolution(this, cgElement, nameHint);
		baseNameResolution.setResolvedName(nameHint);
		List<@NonNull NameResolution> reservedNameResolutions2 = reservedNameResolutions;
		if (reservedNameResolutions2 == null) {
			reservedNameResolutions = reservedNameResolutions2 = new ArrayList<>();
		}
		reservedNameResolutions2.add(baseNameResolution);
		return baseNameResolution;
	}

	public @Nullable CGClass findCGScope() {
		for (NestedNameManager nameManager = this; nameManager != null; nameManager = nameManager.parent instanceof NestedNameManager ? (NestedNameManager)nameManager.parent : null) {
			CGNamedElement cgScope = nameManager.findCGScope();
			if (cgScope instanceof CGClass) {
				return (CGClass) cgScope;
			}
		}
		return null;
	}

	@Override
	protected @NonNull Context getContext() {
		return ClassUtil.nonNullState(context);
	}

	@Override
	protected @Nullable String getLazyNameHint(@NonNull CGValuedElement cgNamedValue) {
		if (cgNamedValue instanceof CGForeignProperty) {
			// FIXME if we make CGForeignProperty we have worse problems with dependences for non-trivial initializers
			return getNameHint(cgNamedValue);
		}
		if (cgNamedValue instanceof CGProperty) {
			return getNameHint(cgNamedValue);
		}
		return null;
	}

	public @NonNull NameResolution getNameResolution(@NonNull CGValuedElement cgElement) {
		NameResolution unsafeNameResolution = cgElement.basicGetNameResolution();
		if (unsafeNameResolution == null) {
			unsafeNameResolution = declareLazyName(cgElement);
		}
		return unsafeNameResolution;
	}

/*	public @NonNull String getVariantResolvedName(@NonNull CGValuedElement cgElement, @NonNull NameVariant nameVariant) {
		Map<@NonNull NameVariant, @Nullable String> nameVariant2name = element2nameVariant2name.get(cgElement);
		assert nameVariant2name != null;
		String name = nameVariant2name.get(nameVariant);
		assert name != null;
		return name;
	} */

	@Override
	public boolean isGlobal() {
		return false;
	}

	public boolean isReserved(@NonNull NameResolution nameResolution) {
		return (reservedNameResolutions != null) && reservedNameResolutions.contains(nameResolution);
	}

	public void setNameVariant(@NonNull CGValuedElement cgElement, @NonNull NameVariant nameVariant, @NonNull String variantName) {
		Map<@NonNull NameVariant, @Nullable String> nameVariant2name = element2nameVariant2name.get(cgElement);
		assert nameVariant2name != null;
		String old = nameVariant2name.put(nameVariant, variantName);
		assert old == null;
	}

	@Override
	public @NonNull String toString() {
		return "locals-" + cgScope.eClass().getName() + "-" + CGUtil.getAST(cgScope).getName();
	}
}
