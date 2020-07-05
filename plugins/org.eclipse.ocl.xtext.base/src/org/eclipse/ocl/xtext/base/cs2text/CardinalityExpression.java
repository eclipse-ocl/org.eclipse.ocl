/*******************************************************************************
 * Copyright (c) 2020 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.xtext.base.cs2text;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.utilities.Nameable;

/**
 * A CardinalityExpression represents the cardinality of a grammar term as a sum of scaled variables.
 */
public class CardinalityExpression implements Nameable
{
	protected final @NonNull String name;
	protected final @NonNull EStructuralFeature eStructuralFeature;
	private final List<@NonNull List<@NonNull CardinalityVariable>> sumOfProducts = new ArrayList<>();

	public CardinalityExpression(@NonNull String name, @NonNull EStructuralFeature eStructuralFeature) {
		this.name = name;
		this.eStructuralFeature = eStructuralFeature;
	}

	public void addMultiplicityProduct(@NonNull List<@NonNull CardinalityVariable> variables) {
		sumOfProducts.add(variables);
	}

	@Override
	public @NonNull String getName() {
		return name;
	}

	@Override
	public @NonNull String toString() {
		StringBuilder s = new StringBuilder();
		toString(s, 0);
		return String.valueOf(s);
	}

	public void toString(@NonNull StringBuilder s, int depth) {
		boolean isFirst1 = true;
		s.append(name);
		s.append(": |");
		s.append(eStructuralFeature.getName());
		s.append("| = ");
		for (@NonNull List<@NonNull CardinalityVariable> products : sumOfProducts) {
			if (!isFirst1) {
				s.append(" + ");
			}
			boolean gotOne = false;
			boolean isFirst2 = true;
			for (@NonNull CardinalityVariable variable : products) {
				if (!variable.isOne()) {
					if (!isFirst2) {
						s.append(" * ");
					}
					if (variable.isOne()) {
						s.append("1");
					}
					else {
						s.append(variable);
						gotOne = true;
					}
					isFirst2 = false;
				}
			}
			if (!gotOne) {
				s.append("1");
			}
			isFirst1 = false;
		}
	}
}