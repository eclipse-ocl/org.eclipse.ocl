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

import java.util.Set;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.utilities.Nameable;
import org.eclipse.ocl.xtext.base.cs2text.enumerations.EnumerationValue;

import com.google.common.collect.Sets;

public class EAttributeData implements Nameable
{
	protected final @NonNull EAttribute eAttribute;
	protected final @NonNull Set<@NonNull EnumerationValue> enumerationValues;

	public EAttributeData(/*@NonNull*/ EAttribute eAttribute, @NonNull Set<@NonNull EnumerationValue> enumerationValues) {
		assert eAttribute != null;
		this.eAttribute = eAttribute;
		this.enumerationValues = enumerationValues;
	}

	public EAttributeData(/*@NonNull*/ EAttribute eAttribute, @NonNull EnumerationValue @NonNull ... enumerationValues) {
		assert eAttribute != null;
		this.eAttribute = eAttribute;
		this.enumerationValues = Sets.newHashSet(enumerationValues);		// XXX Prefer array
	}

	public @NonNull EAttribute getEAttribute() {
		return eAttribute;
	}

	public @NonNull Set<@NonNull EnumerationValue> getEnumerationValues() {
		return enumerationValues;
	}

	@Override
	public @NonNull String getName() {
		return XtextGrammarUtil.getName(eAttribute);
	}


	@Override
	public @NonNull String toString() {
		return eAttribute.getEContainingClass().getName() + "::" + eAttribute.getName() + " " + enumerationValues;
	}
}