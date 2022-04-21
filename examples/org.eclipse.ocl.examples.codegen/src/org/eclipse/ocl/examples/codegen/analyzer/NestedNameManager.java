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
import java.util.List;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.cgmodel.CGForeignProperty;
import org.eclipse.ocl.examples.codegen.cgmodel.CGNamedElement;
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
	private @Nullable List<@NonNull BaseNameResolution> reservedNameResolutions = null;

	/**
	 * The value name assignments.
	 */
	private @Nullable Context context = null;		// Non-null once value name allocation is permitted.

	public NestedNameManager(@NonNull NameManager parent, @NonNull CGNamedElement cgScope) {
		super(parent, parent.helper);
		this.parent = parent;
		this.cgScope = cgScope;
		assert !(parent instanceof NestedNameManager) || (((NestedNameManager)parent).cgScope != cgScope);		// XXX
		parent.addChild(this);
	}

	public void assignNames() {
		Context context2 = context;
		assert context2 == null;
		this.context = context2 = new Context(this);
		if (reservedNameResolutions != null) {
			for (@NonNull BaseNameResolution nameResolution : reservedNameResolutions) {
				String resolvedName = nameResolution.getResolvedName();
				CGValuedElement primaryElement = nameResolution.getPrimaryElement();
				context2.reserveName(resolvedName, primaryElement);
			}
		}
		assignNames(context2);
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
		return new BaseNameResolution(this, cgElement, nameHint);
	}

	@Override
	public @NonNull BaseNameResolution declareReservedName(@NonNull CGValuedElement cgElement, @NonNull String nameHint) {
		assert !cgElement.isGlobal();
		assert cgElement.getNamedValue() == cgElement;
		NameResolution nameResolution2 = cgElement.basicGetNameResolution();
		assert nameResolution2 == null;
		BaseNameResolution baseNameResolution = new BaseNameResolution(this, cgElement, nameHint);
		baseNameResolution.setResolvedName(nameHint);
		List<@NonNull BaseNameResolution> reservedNameResolutions2 = reservedNameResolutions;
		if (reservedNameResolutions2 == null) {
			reservedNameResolutions = reservedNameResolutions2 = new ArrayList<>();
		}
		reservedNameResolutions2.add(baseNameResolution);
		return baseNameResolution;
	}

	@Override
	protected @NonNull Context getContext() {
		return ClassUtil.nonNullState(context);
	}

	@Override
	protected @Nullable String getLazyNameHint(@NonNull CGValuedElement cgNamedValue) {
		if (cgNamedValue instanceof CGForeignProperty) {
			// FIXME if we make CGForeignProperty we ave worse problems with dependences for non-trivial initializers
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

	@Override
	public boolean isGlobal() {
		return false;
	}

	public boolean isReserved(@NonNull NameResolution nameResolution) {
		return (reservedNameResolutions != null) && reservedNameResolutions.contains(nameResolution);
	}

	@Override
	public @NonNull String toString() {
		return "locals-" + cgScope.eClass().getName() + "-" + CGUtil.getAST(cgScope).getName();
	}
}
