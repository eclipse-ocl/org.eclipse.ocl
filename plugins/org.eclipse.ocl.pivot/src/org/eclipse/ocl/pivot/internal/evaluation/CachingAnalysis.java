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
package org.eclipse.ocl.pivot.internal.evaluation;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.LoopExp;
import org.eclipse.ocl.pivot.VariableDeclaration;
import org.eclipse.ocl.pivot.VariableExp;
import org.eclipse.ocl.pivot.ids.CollectionTypeId;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.utilities.TreeIterable;

/**
 * The CachingAnalysis sets VariableDeclaration.cacheNeeded for colletion variables that are accessed more than once.
 */
public class CachingAnalysis
{
	public static void analyze(@NonNull ExpressionInOCL expressionInOCL) {
		CachingAnalysis cachingAnalysis = new CachingAnalysis(expressionInOCL);
		cachingAnalysis.analyze();
	}

	protected final @NonNull ExpressionInOCL expressionInOCL;
	private final @NonNull Map<@NonNull VariableDeclaration, @NonNull Set<@NonNull VariableExp>> variable2users = new HashMap<>();
	private final @NonNull Map<@NonNull Element, @Nullable Boolean> element2multiple = new HashMap<>();

	protected CachingAnalysis(@NonNull ExpressionInOCL expressionInOCL) {
		this.expressionInOCL = expressionInOCL;
	}

	protected void addUser(@NonNull VariableDeclaration asVariable, @NonNull VariableExp asVariableExp) {
		Set<@NonNull VariableExp> users = variable2users.get(asVariable);
		if (users == null) {
			users = new HashSet<>();
			variable2users.put(asVariable, users);
		}
		users.add(asVariableExp);
	}

	protected void analyze() {
		for (@NonNull Object object : new TreeIterable(expressionInOCL, true)) {
			analyzeUsage(object);
		}
		for (@NonNull VariableDeclaration asVariable : variable2users.keySet()) {
			assignCaching(asVariable);
		}
	}

	protected void analyzeUsage(@NonNull Object object) {
		if (object instanceof VariableExp) {
			VariableExp asVariableExp = (VariableExp)object;
			VariableDeclaration asVariable = PivotUtil.getReferredVariable(asVariableExp);
			addUser(asVariable, asVariableExp);
		}
	}

	protected void assignCaching(@NonNull VariableDeclaration asVariable) {
		if (asVariable.getTypeId() instanceof CollectionTypeId) {
			boolean isMultiple = false;
			Set<@NonNull VariableExp> users = variable2users.get(asVariable);
			assert users != null;
			if (users.size() > 1) {
				isMultiple = true;
			}
			else {
				for (@NonNull VariableExp asVariableExp : users) {
					if (isMultiple(asVariableExp)) {
						isMultiple = true;
						break;
					}
				}
			}
			if (isMultiple) {
				asVariable.setCacheNeeded(true);
			}
		}
	}

	protected boolean isMultiple(@NonNull Element asElement) {
		Boolean isMultiple = element2multiple.get(asElement);
		if (isMultiple == null) {
			EObject eContainer = asElement.eContainer();
			if ((eContainer instanceof LoopExp) && (asElement == ((LoopExp)eContainer).getOwnedBody())) {
				isMultiple = true;
			}
			else if (eContainer instanceof Element) {
				isMultiple = isMultiple((Element)eContainer);
			}
			else {
				isMultiple = false;
			}
			element2multiple.put(asElement, isMultiple);
		}
		return isMultiple;
	}
}