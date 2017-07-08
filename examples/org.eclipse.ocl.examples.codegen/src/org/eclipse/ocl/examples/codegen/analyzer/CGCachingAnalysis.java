/*******************************************************************************
 * Copyright (c) 2017 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.codegen.analyzer;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.cgmodel.CGConstraint;
import org.eclipse.ocl.examples.codegen.cgmodel.CGElement;
import org.eclipse.ocl.examples.codegen.cgmodel.CGIterationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGPackage;
import org.eclipse.ocl.examples.codegen.cgmodel.CGVariable;
import org.eclipse.ocl.examples.codegen.cgmodel.CGVariableExp;
import org.eclipse.ocl.examples.codegen.utilities.CGUtil;
import org.eclipse.ocl.pivot.ids.CollectionTypeId;
import org.eclipse.ocl.pivot.utilities.TreeIterable;

/**
 * The CGCachingAnalysis sets CGVariable.cacheNeeded for colletion variables that are accessed more than once.
 */
public class CGCachingAnalysis
{
	protected final @NonNull CGPackage cgPackage;

	public CGCachingAnalysis(@NonNull CGPackage cgPackage) {
		//		super(null);
		this.cgPackage = cgPackage;
	}

	protected void addUser(@NonNull Map<@NonNull CGVariable, @NonNull Set<@NonNull CGVariableExp>> cgVariable2users, @NonNull CGVariable cgVariable, @NonNull CGVariableExp cgVariableExp) {
		Set<@NonNull CGVariableExp> users = cgVariable2users.get(cgVariable);
		if (users == null) {
			users = new HashSet<>();
			cgVariable2users.put(cgVariable, users);
		}
		users.add(cgVariableExp);
	}

	public void analyze() {
		for (@NonNull Object object : new TreeIterable(cgPackage, true)) {
			if (object instanceof CGConstraint) {
				analyzeConstraint((CGConstraint)object);
			}
		}
	}

	protected void analyzeConstraint(@NonNull CGConstraint cgConstraint) {
		Map<@NonNull CGVariable, @NonNull Set<@NonNull CGVariableExp>> cgVariable2users = new HashMap<>();
		Map<@NonNull CGElement, @Nullable Boolean> cgElement2multiple = new HashMap<>();
		for (@NonNull Object object : new TreeIterable(cgConstraint, true)) {
			analyzeUsage(cgVariable2users, object);
		}
		for (@NonNull CGVariable cgVariable : cgVariable2users.keySet()) {
			assignCaching(cgVariable2users, cgElement2multiple, cgVariable);
		}
	}

	protected void analyzeUsage(@NonNull Map<@NonNull CGVariable, @NonNull Set<@NonNull CGVariableExp>> cgVariable2users, @NonNull Object object) {
		if (object instanceof CGVariableExp) {
			CGVariableExp cgVariableExp = (CGVariableExp)object;
			CGVariable cgVariable = CGUtil.getReferredVariable(cgVariableExp);
			addUser(cgVariable2users, cgVariable, cgVariableExp);
		}
	}

	protected void assignCaching(@NonNull Map<@NonNull CGVariable, @NonNull Set<@NonNull CGVariableExp>> cgVariable2users,
			@NonNull Map<@NonNull CGElement, @Nullable Boolean> cgElement2multiple, @NonNull CGVariable cgVariable) {
		if (cgVariable.getASTypeId() instanceof CollectionTypeId) {
			boolean isMultiple = false;
			Set<@NonNull CGVariableExp> users = cgVariable2users.get(cgVariable);
			assert users != null;
			if (users.size() > 1) {
				isMultiple = true;
			}
			else {
				for (@NonNull CGVariableExp cgUsingElement : users) {
					if (isMultiple(cgElement2multiple, cgUsingElement)) {
						isMultiple = true;
						break;
					}
				}
			}
			if (isMultiple) {
				cgVariable.setCacheNeeded(true);
			}
		}
	}

	protected boolean isMultiple(@NonNull Map<@NonNull CGElement, @Nullable Boolean> cgElement2multiple, @NonNull CGElement cgElement) {
		Boolean isMultiple = cgElement2multiple.get(cgElement);
		if (isMultiple == null) {
			EObject eContainer = cgElement.eContainer();
			if ((eContainer instanceof CGIterationCallExp) && (cgElement == ((CGIterationCallExp)eContainer).getBody())) {
				isMultiple = true;
			}
			else if (eContainer instanceof CGElement) {
				isMultiple = isMultiple(cgElement2multiple, (CGElement)eContainer);
			}
			else {
				isMultiple = false;
			}
			cgElement2multiple.put(cgElement, isMultiple);
		}
		return isMultiple;
	}
}