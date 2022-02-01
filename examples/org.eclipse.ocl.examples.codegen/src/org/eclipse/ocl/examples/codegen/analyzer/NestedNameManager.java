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

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.cgmodel.CGElement;
import org.eclipse.ocl.pivot.utilities.ClassUtil;

/**
 * A NameManager provides suggestions for names and maintains caches of used names so that model elements are consistently
 * named without collisions.
 */
public class NestedNameManager extends NameManager
{
	protected final @NonNull NameManager parent;
	protected final @NonNull CGElement cgScope;

	/**
	 * The value name assignments.
	 */
	private @Nullable Context context = null;		// Non-null once value name allocation is permitted.

	/**
	 * Queue of elements whose unique valueName is pending. Each element may have an optional list of prefixes for additional
	 * special purpose declarations.
	 */
//	private @NonNull Map<@NonNull Object, @NonNull List<@NonNull PrefixedValueNames>> object2prefixedValueNames = new HashMap<>();

//	private @NonNull Map<@NonNull Object, @NonNull List<@NonNull PrefixedValueNames>> object2nameResolution = new HashMap<>();

	public NestedNameManager(@NonNull NameManager parent, @NonNull CGElement cgScope) {
		super(parent, parent.helper);
		this.parent = parent;
		this.cgScope = cgScope;
		parent.addChild(this);
	}

	public void assignNames() {
		Context context2 = context;
		assert context2 == null;
		this.context = context2 = new Context(this);
		assignNames(context2);
	}

	@Override
	protected @NonNull Context getContext() {
		return ClassUtil.nonNullState(context);
	}

/*	@Override
	public @NonNull String queueValueName(@Nullable String nameHint, @Nullable PrefixedValueNames prefixedValueNames, @NonNull Object anObject) {
		if (anObject instanceof CGBuiltInIterationCallExp) {
			getClass();		// XXX
		}
		assert !(anObject instanceof String);			// XXX
		PrefixedValueNames defaultValueNames = globalNameManager.getDefaultValueNames();
		if (prefixedValueNames == null) {
			prefixedValueNames = defaultValueNames;
		}


		if (nameHint == null) {
			nameHint = (String) helper.getNameHint(anObject);
			if (nameHint == null) {
				nameHint = (String) helper.getNameHint(anObject);		// XXX debugging
				nameHint = "XXX";				// XXX
			}
		}
	//	if (cgValuedElement.getName() != null) {
	//		assert
	//	}
		assert nameHint != null;
		List<@NonNull PrefixedValueNames> allPrefixedValueNames = object2prefixedValueNames.get(anObject);
		if (allPrefixedValueNames != null) {
			if (allPrefixedValueNames.contains(prefixedValueNames)) {
				return nameHint;
			}
		}

		if (anObject instanceof CGValuedElement) {
			CGValuedElement cgValuedElement = (CGValuedElement)anObject;
			if (prefixedValueNames == defaultValueNames) {
				assert (cgValuedElement.getName() == null) || (cgValuedElement.getName().equals(nameHint));
				nameHint = prefixedValueNames.prefix + nameHint;
				cgValuedElement.setName(nameHint);
			}
			else {
				assert cgValuedElement.getName() != null : "Assign defaultPrefixedValueNames first";
			}
		}
		if (allPrefixedValueNames == null) {
			allPrefixedValueNames = new UniqueList<>();
			object2prefixedValueNames.put(anObject, allPrefixedValueNames);
			if (prefixedValueNames != defaultValueNames) {
				allPrefixedValueNames.add(defaultValueNames);
			}
		}
		allPrefixedValueNames.add(prefixedValueNames);
		globalNameManager.assignNameManager(anObject, this);
		return nameHint;
	} */
}
