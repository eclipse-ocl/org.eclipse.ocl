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
import org.eclipse.ocl.examples.codegen.cgmodel.CGElement;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.pivot.utilities.ClassUtil;

/**
 * A NameManager provides suggestions for names and maintains caches of used names so that model elements are consistently
 * named without collisions.
 */
public class NestedNameManager extends NameManager
{
	protected final @NonNull NameManager parent;
	protected final @NonNull CGElement cgScope;
	private @Nullable List<@NonNull NameResolution> reservedNameResolutions = null;;

	/**
	 * The value name assignments.
	 */
	private @Nullable Context context = null;		// Non-null once value name allocation is permitted.

	public NestedNameManager(@NonNull NameManager parent, @NonNull CGElement cgScope) {
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
			for (@NonNull NameResolution nameResolution : reservedNameResolutions) {
				String resolvedName = nameResolution.getResolvedName();
				CGValuedElement primaryElement = nameResolution.getPrimaryElement();
				context2.reserveName(resolvedName, primaryElement);
			}
		}
		assignNames(context2);
	}

	public @NonNull NameResolution declareReservedName(@NonNull CGValuedElement cgElement, @NonNull String nameHint) {
		assert !cgElement.isGlobal();
		CGValuedElement cgNamedValue = cgElement.getNamedValue();
		assert cgElement == cgNamedValue;
		NameResolution nameResolution = cgNamedValue.basicGetNameResolution();
		assert nameResolution == null;
		nameResolution = new NameResolution(this, cgNamedValue, nameHint);
		nameResolution.setResolvedName(nameHint);
		List<@NonNull NameResolution> reservedNameResolutions2 = reservedNameResolutions;
		if (reservedNameResolutions2 == null) {
			reservedNameResolutions = reservedNameResolutions2 = new ArrayList<>();
		}
		reservedNameResolutions2.add(nameResolution);
		return nameResolution;
	}

	@Override
	protected @NonNull Context getContext() {
		return ClassUtil.nonNullState(context);
	}
}
