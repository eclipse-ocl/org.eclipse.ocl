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
import org.eclipse.ocl.examples.codegen.cgmodel.CGElement;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.cgmodel.CGVariableExp;
import org.eclipse.ocl.pivot.utilities.ClassUtil;

/**
 * A NameResolution represents a future name for the value of an expression and for all unmodified clients of that value.
 *
 * The need for a neme is first declared and an actual name, hierarchically unique within the NameManager unique,
 * is assigned just before the CG2Java generation.
 *  </p>
 *  Declarations occur at various timres.
 *  </br>Java reserved words are excluded.
 *  </br>However certain reserved words such as "this" my be assigned for precisely their Java purpose.
 *  </br>Global names may be declared in the GlobalNameManager.
 *  </br>Local names may be declared in the appropriate NestedNameManager.
 *  </p>
 *  Reserved globals are typically declared and assigned during CodeGenerator/GlobalContext construction.
 *  </br>Incidental globals are typically declared during AS2CG and possibly CG3JavaPre
 *  </br>Locals may be pre-emptively declared during AS2CG or CG3JavaPre often to support additional variants.
 *  </br>Residual locals are declared at the start of CodeGenerator resolveNames.
 *  </br>Unassigned names are resolved at the end of CodeGenerator resolveNames.
 *  </br>All names are resolved when needed by CG2Java.
 */
public class NameResolution
{
	/**
	 * Place holder for nameVariant2resolvedName's value until the name is resolved.
	 */
	private static final @NonNull String NOT_YET_RESOLVED = "«not-yet-resolved»".intern();	// Safe since no guilemets in valid Java identofoers.

	/**
	 * The namespace at and below whch this resolved name and all its variiants must be unique.
	 */
	protected final @NonNull NameManager nameManager;

	/**
	 * The CGElement that computes the value to be accessed by the resolved name. May be null for uncomputed globals.
	 */
	protected final @Nullable CGValuedElement primaryElement;

	/**
	 * A hint as to what could make a readable resolved name.
	 */
	protected final @NonNull String nameHint;

	/**
	 * Additional CGElements that propgate the unchanged value to be accessed by the resolved name.
	 */
	private @Nullable List<@NonNull CGElement> secondaryElements = null;

	/**
	 * The resolved name based on nameHint after ensuring that it is unique at and below the nameManager. Non-null once resolved.
	 */
	private @Nullable String resolvedName = null;

	/**
	 * Additional variants of resolvedName for which further unique names are required.
	 */
	private @Nullable Map<@NonNull NameVariant, @NonNull String> nameVariant2resolvedName = null;

	protected NameResolution(@NonNull NameManager nameManager, @Nullable CGValuedElement primaryElement, @NonNull String nameHint) {
		this.nameManager = nameManager;
		this.primaryElement = primaryElement;
		this.nameHint = nameHint;
		if ("PROPid_size".equals(nameHint)) {
			getClass();		// XXX
		}
		assert !(primaryElement instanceof CGVariableExp) : "Should have redirected to getNamedValue()";
		if (primaryElement != null) {
			primaryElement.setNameResolution(this);
			nameManager.addNameResolution(primaryElement);
		}
		nameManager.addNameResolution(this);
	}

	public void addSecondaryElement(@NonNull CGValuedElement cgElement) {
		if (cgElement != primaryElement) {
			List<@NonNull CGElement> secondaryElements2 = secondaryElements;
			if (secondaryElements2 == null) {
				secondaryElements = secondaryElements2 = new ArrayList<>();
			}
			else {
				assert !secondaryElements2.contains(cgElement);
			}
			secondaryElements2.add(cgElement);
			cgElement.setNameResolution(this);
			nameManager.addNameResolution(cgElement);
		}
	}

	public void addNameVariant(@NonNull NameVariant nameVariant) {
		assert resolvedName == null : "Cannot addNameVariant after name is resolved";
		Map<@NonNull NameVariant, @NonNull String> nameVariant2resolvedName2 = nameVariant2resolvedName;
		if (nameVariant2resolvedName2 == null) {
			nameVariant2resolvedName = nameVariant2resolvedName2 = new HashMap<>();
		}
		String old = nameVariant2resolvedName2.put(nameVariant, NOT_YET_RESOLVED);
		assert (old == null) || (old == NOT_YET_RESOLVED) : "Duplicate " + nameVariant;		// variant may be declared/used multple times
	}

	public @Nullable CGValuedElement basicGetPrimaryElement() {
		return primaryElement;
	}

	public @Nullable String basicGetResolvedName() {
		return resolvedName;
	}

	public @NonNull NameManager getNameManager() {
		return nameManager;
	}

	public @NonNull CGValuedElement getPrimaryElement() {
		return ClassUtil.nonNullState(primaryElement);
	}

	public @NonNull String getResolvedName() {
		return ClassUtil.nonNullState(resolvedName);
	}

	public @NonNull String getVariantResolvedName(@NonNull NameVariant nameVariant) {
		assert nameVariant2resolvedName != null;
		String resolvedName = nameVariant2resolvedName.get(nameVariant);
		assert resolvedName != NOT_YET_RESOLVED;
		return ClassUtil.nonNullState(resolvedName);
	}

	public @Nullable Iterable<@NonNull CGElement> getSecondaryElements() {
		return secondaryElements;
	}

	public void resolveIn(@NonNull Context context) {
		if (resolvedName == null) {
			Object cgElement = primaryElement != null ? primaryElement : NameManager.NOT_AN_OBJECT;
			String resolvedName = context.allocateUniqueName(nameHint, cgElement);
			setResolvedName(resolvedName);
			Map<@NonNull NameVariant, @NonNull String> nameVariant2resolvedName2 = nameVariant2resolvedName;
			if (nameVariant2resolvedName2 != null) {
				for (@NonNull NameVariant nameVariant : nameVariant2resolvedName2.keySet()) {
					String variantNameHint = nameVariant.getName(nameHint);
					String resolvedVariantName = context.allocateUniqueName(variantNameHint, cgElement);
					nameVariant2resolvedName2.put(nameVariant, resolvedVariantName);
				}
			}
		}
	}

	protected void setResolvedName(@NonNull String resolvedName) {
		this.resolvedName = resolvedName;
	}

	@Override
	public @NonNull String toString() {
		return nameManager + ":" + nameHint + " => " + (resolvedName != null ? resolvedName : "???");
	}
}