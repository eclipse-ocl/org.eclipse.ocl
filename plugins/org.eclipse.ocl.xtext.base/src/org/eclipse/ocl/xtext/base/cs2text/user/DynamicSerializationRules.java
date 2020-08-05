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
package org.eclipse.ocl.xtext.base.cs2text.user;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.utilities.StringUtil;
import org.eclipse.ocl.xtext.base.cs2text.elements.SerializationRule;

/**
 * The DynamicSerializationRules identify the subset of the static SerializationRules that are compatiible with
 * the containment ancestry of an actual EObject instance of EClassfoor the static rules.
 */
public class DynamicSerializationRules
{
	protected final @NonNull EClass eClass;
	protected final @NonNull Iterable<@NonNull SerializationRule> serializationRules;

	public DynamicSerializationRules(@NonNull EClass eClass, @NonNull Iterable<@NonNull SerializationRule> serializationRules) {
		this.eClass = eClass;
		this.serializationRules = serializationRules;
	}

	public @NonNull Iterable<@NonNull SerializationRule> getSerializationRules() {
		return serializationRules;
	}

	@Override
	public @NonNull String toString() {
		StringBuilder s = new StringBuilder();
		toString(s, 0);
		return s.toString();
	}

	public void toString(StringBuilder s, int i) {
		for (@NonNull SerializationRule serializationRule : serializationRules) {
			s.append(" ");;
			StringUtil.appendIndentation(s, i);
			s.append("|&  ");
			serializationRule.toString(s, -1);
		}
	}
}