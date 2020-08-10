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
package org.eclipse.ocl.xtext.base.cs2text.xtext;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.utilities.StringUtil;
import org.eclipse.ocl.xtext.base.cs2text.elements.SerializationRule;

/**
 * The (static) SerializationRules identify the alternative rules that may be used to serialize a given EClass.
 * Once the actual EObject instance of EClass is known a DynamicSerializationRules identifies the subset of the
 * rules with compatiible containment ancestry.
 */
public class RTSerializationRules
{
	protected final @NonNull EClass eClass;
	protected final @NonNull Iterable<@NonNull SerializationRule> serializationRules;

	public RTSerializationRules(/*@NonNull*/ EClass eClass, /*@NonNull*/ Iterable<@NonNull SerializationRule> serializationRules) {
		assert eClass != null;
		this.eClass = eClass;
		assert serializationRules != null;
		this.serializationRules = serializationRules;
	}

	public @NonNull EClass getEClass() {
		return eClass;
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

	public void toString(@NonNull StringBuilder s, int i) {
		s.append(eClass.getEPackage().getName());
		s.append("::");
		s.append(eClass.getName());
		for (@NonNull SerializationRule serializationRule : serializationRules) {
			StringUtil.appendIndentation(s, i);
			s.append("|&  ");
			serializationRule.toString(s, -1);
		}
	}
}