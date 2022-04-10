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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.cgmodel.CGElement;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.PivotPackage;
import org.eclipse.ocl.pivot.VariableDeclaration;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.PivotUtil;

/**
 * A NameManager provides suggestions for names and maintains caches of used names so that model elements are consistently
 * named without collisions.
 */
public class NestedNameManager extends NameManager
{
	protected final @NonNull NameManager parent;
	protected final @NonNull CGElement cgScope;
	private @Nullable List<@NonNull BaseNameResolution> reservedNameResolutions = null;;

	/**
	 * The value name assignments.
	 */
	private @Nullable Context context = null;		// Non-null once value name allocation is permitted.

	/**
	 * The name declaration for each AS variable. These are allocated early to enable initializer expressions
	 * to use the name of the initialized variable long before the containement reee is complete.
	 */
	private final @NonNull Map<@NonNull VariableDeclaration, @NonNull NameResolution> asVariable2nameResolution = new HashMap<>();

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
			for (@NonNull BaseNameResolution nameResolution : reservedNameResolutions) {
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
		NameResolution nameResolution2 = cgNamedValue.basicGetNameResolution();
		assert nameResolution2 == null;
		BaseNameResolution baseNameResolution = new BaseNameResolution(this, cgNamedValue, nameHint);
		baseNameResolution.setResolvedName(nameHint);
		List<@NonNull BaseNameResolution> reservedNameResolutions2 = reservedNameResolutions;
		if (reservedNameResolutions2 == null) {
			reservedNameResolutions = reservedNameResolutions2 = new ArrayList<>();
		}
		reservedNameResolutions2.add(baseNameResolution);
		return baseNameResolution;
	}

	@Override
	public @NonNull NameResolution declareStandardName(@NonNull CGValuedElement cgElement) {
		NameResolution nameResolution = cgElement.basicGetNameResolution();
		if (nameResolution != null) {
			return nameResolution;
		}
		else {
			Element asElement = cgElement.getAst();
			if (asElement != null) {
				EObject eContainer = asElement;
				EStructuralFeature eContainingFeature;
				while ((eContainingFeature = eContainer.eContainingFeature()) == PivotPackage.Literals.LET_EXP__OWNED_IN) {
					eContainer = eContainer.eContainer();
				}
				if (eContainingFeature == PivotPackage.Literals.VARIABLE__OWNED_INIT) {
					eContainer = eContainer.eContainer();
					assert eContainer != null;
					nameResolution = getNameResolution((VariableDeclaration)eContainer);
					nameResolution.addCGElement(cgElement);
					return nameResolution;
				}
			}
			String nameHint = helper.getNameHint(cgElement);
			return declareStandardName(cgElement, nameHint);
		}
	}

	@Override
	protected @NonNull Context getContext() {
		return ClassUtil.nonNullState(context);
	}

	public @NonNull NameResolution getNameResolution(@NonNull VariableDeclaration asVariable) {
		NameResolution nameResolution = asVariable2nameResolution.get(asVariable);
		if (nameResolution == null) {
			nameResolution = new BaseNameResolution(this, null, PivotUtil.getName(asVariable));
			asVariable2nameResolution.put(asVariable, nameResolution);
		}
		return nameResolution;
	}

	public boolean isReserved(@NonNull NameResolution nameResolution) {
		return (reservedNameResolutions != null) && reservedNameResolutions.contains(nameResolution);
	}

	@Override
	public @NonNull String toString() {
		return "locals " + cgScope.eClass().getName() + " : " + cgScope.toString();
	}
}
