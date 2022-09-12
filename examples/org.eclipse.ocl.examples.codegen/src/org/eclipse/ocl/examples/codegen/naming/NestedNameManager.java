/*******************************************************************************
 * Copyright (c) 2022 Willink Transformation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.codegen.naming;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.analyzer.CodeGenAnalyzer;
import org.eclipse.ocl.examples.codegen.cgmodel.CGLibraryOperation;
import org.eclipse.ocl.examples.codegen.cgmodel.CGNamedElement;
import org.eclipse.ocl.examples.codegen.cgmodel.CGParameter;
import org.eclipse.ocl.examples.codegen.cgmodel.CGProperty;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.java.JavaCodeGenerator;
import org.eclipse.ocl.examples.codegen.naming.GlobalNameManager.NameVariant;
import org.eclipse.ocl.examples.codegen.utilities.CGUtil;
import org.eclipse.ocl.pivot.NamedElement;
import org.eclipse.ocl.pivot.utilities.ClassUtil;

/**
 * A NestedNameManager provides suggestions for names and maintains caches of used names so that model elements are consistently
 * named without collisions at some node in the name nesting hierarchy..
 */
public abstract class NestedNameManager extends AbstractNameManager
{
	protected final @NonNull JavaCodeGenerator codeGenerator;
	protected final @NonNull CodeGenAnalyzer analyzer;
	protected final @NonNull AbstractNameManager parent;

	/**
	 * Names that must be used within a nested namespace. Typically these are Ecore assigned property/operation/parameter
	 * names whose spelling is not adjustable.
	 */
	private @Nullable List<NameResolution.@NonNull EagerNested> eagerNameResolutions = null;

	/**
	 * The value name assignments.
	 */
	private @Nullable Context context = null;		// Non-null once value name allocation is permitted.

	/**
	 * Additional variants of an element's resolvedName for which further unique names are required.
	 */
	private @NonNull Map<@NonNull CGNamedElement, @Nullable Map<@NonNull NameVariant, @Nullable String>> element2nameVariant2name = new HashMap<>();

	protected NestedNameManager(@NonNull JavaCodeGenerator codeGenerator, @NonNull AbstractNameManager parent, @NonNull CGNamedElement cgScope) {
		super(parent, parent.helper);
		this.codeGenerator = codeGenerator;
		this.analyzer = codeGenerator.getAnalyzer();
		this.parent = parent;
		// XXX	assert at most one ancestral class
		parent.addChild(this);
		globalNameManager.addChildNameManager(cgScope, this);
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

	/**
	 * Assign all the reserved names (first).
	 */
	protected void assignEagerNames(@NonNull Context context) {
		if (eagerNameResolutions != null) {
			for (@NonNull NameResolution nameResolution : eagerNameResolutions) {
				String resolvedName = nameResolution.resolveIn(context, null);
				CGNamedElement primaryElement = nameResolution.getPrimaryElement();
				context.reserveName(resolvedName, primaryElement);
			}
		}
	}

	/**
	 * Assign all the secondary names that prefix a primary name.
	 */
	protected void assignExtraNames(@NonNull Context context) {
		for (Entry<@NonNull CGNamedElement, @Nullable Map<@NonNull NameVariant, @Nullable String>> entry1 : element2nameVariant2name.entrySet()) {
			Map<@NonNull NameVariant, @Nullable String> nameVariant2name = entry1.getValue();
			if (nameVariant2name != null) {
				CGNamedElement cgElement = entry1.getKey();
				assert cgElement.eContainer() != null;		// Not eliminated by CSE		-- ?? obsolete
				String resolvedName;
				if (cgElement instanceof CGValuedElement) {
					CGValuedElement cgValuedElement = (CGValuedElement)cgElement;
					NameResolution nameResolution = getNameResolution(cgValuedElement);
					nameResolution.resolveNameHint();;
					nameResolution.resolveIn(context, cgValuedElement);
					resolvedName = nameResolution.getResolvedName();
				}
				else {
					resolvedName = CGUtil.getName(cgElement);
				}
				for (Entry<@NonNull NameVariant, @Nullable String> entry2 : nameVariant2name.entrySet()) {
					NameVariant nameVariant = entry2.getKey();
					String name = entry2.getValue();
					assert name == null;
					String variantNameHint = nameVariant.getName(resolvedName);
					String variantName = context.allocateLazyName(variantNameHint, cgElement);
					nameVariant2name.put(nameVariant, variantName);
				}
			}
		}
	}

	public void assignNames(@NonNull Map<@NonNull NameManager, @NonNull List<@NonNull CGValuedElement>> nameManager2namedElements) {
		Context context2 = context;
		assert context2 == null;
		this.context = context2 = new Context(this);
		assignEagerNames(context2);
		assignLocalNames(context2, nameManager2namedElements);
		assignExtraNames(context2);
		assignNestedNames(nameManager2namedElements);
	}

	public @Nullable String basicGetVariantResolvedName(@NonNull CGNamedElement cgElement, @NonNull NameVariant nameVariant) {
		Map<@NonNull NameVariant, @Nullable String> nameVariant2name = element2nameVariant2name.get(cgElement);
		return nameVariant2name != null ? nameVariant2name.get(nameVariant) : null;
	}

	/**
	 * Declare that cgElement has a name which should be distinct in this NameManager.
	 * This is typically used to ensure that a local name is available for re-use.
	 * <br>
	 * This should be used sparingly. Most names will be lazily declared adequately.
	 * <br>
	 * Valid usages are to force usage of the Ecore genmodel allocated name for a Property or Parameter.
	 */
	public @NonNull NameResolution declareEagerName(@NonNull CGNamedElement cgElement) {
		assert (cgElement instanceof CGLibraryOperation) || (cgElement instanceof CGProperty) || (cgElement instanceof CGParameter);
		boolean savedInhibitNameResolution = NameResolution.inhibitNameResolution;
		NameResolution.inhibitNameResolution = false;			// XXX do we still need this debug design enforcement
		String eagerName = getNameHint(cgElement);
		NameResolution.EagerNested nameResolution = new NameResolution.EagerNested(this, cgElement, eagerName);
	//	nameResolution.resolveIn(getContext());
		List<NameResolution.@NonNull EagerNested> eagerNameResolutions2 = eagerNameResolutions;
		if (eagerNameResolutions2 == null) {
			eagerNameResolutions = eagerNameResolutions2 = new ArrayList<>();
		}
		eagerNameResolutions2.add(nameResolution);
		NameResolution.inhibitNameResolution = savedInhibitNameResolution;
		return nameResolution;
	}

	public @NonNull CodeGenAnalyzer getAnalyzer() {
		return codeGenerator.getAnalyzer();
	}

	public abstract @NonNull NamedElement getASScope();

	public abstract @NonNull CGNamedElement getCGScope();

	/**
	 * Return the NestedNameManager that can be the parent of another CGClass. Returns null for global.
	 */
	public abstract @Nullable NestedNameManager getClassParentNameManager();

	public @NonNull JavaCodeGenerator getCodeGenerator() {
		return codeGenerator;
	}

	@Override
	protected @NonNull Context getContext() {
		return ClassUtil.nonNullState(context);
	}

	@Override
	public @NonNull NameResolution getNameResolution(@NonNull CGValuedElement cgElement) {
		NameResolution nameResolution = cgElement.basicGetNameResolution();
		if (nameResolution == null) {
			CGValuedElement cgNamedValue = cgElement.getNamedValue();
			nameResolution = cgNamedValue.basicGetNameResolution();
			if (nameResolution == null) {
				nameResolution = new NameResolution.Lazy(this, cgNamedValue, null);
			}
			if (cgElement != cgNamedValue) {
				nameResolution.addCGElement(cgElement);
			}
		}
		return nameResolution;
	}

	public @NonNull NameManager getParent() {
		return parent;
	}

	@Override
	public boolean isGlobal() {
		return false;
	}

	public boolean isReserved(@NonNull NameResolution nameResolution) {
		return (eagerNameResolutions != null) && eagerNameResolutions.contains(nameResolution);
	}

	public void setNameVariant(@NonNull CGValuedElement cgElement, @NonNull NameVariant nameVariant, @NonNull String variantName) {
		Map<@NonNull NameVariant, @Nullable String> nameVariant2name = element2nameVariant2name.get(cgElement);
		assert nameVariant2name != null;
		String old = nameVariant2name.put(nameVariant, variantName);
		assert old == null;
	}

	@Override
	public @NonNull String toString() {
		NamedElement asScope = getASScope();
		return asScope.eClass().getName() + "-" + asScope;
	}
}
