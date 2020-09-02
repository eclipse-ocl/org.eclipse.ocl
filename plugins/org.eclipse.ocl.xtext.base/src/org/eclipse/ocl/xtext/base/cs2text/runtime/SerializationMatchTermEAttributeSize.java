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
package org.eclipse.ocl.xtext.base.cs2text.runtime;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.xtext.base.cs2text.solutions.StaticRuleMatch;

/**
 * An EAttributeSizeCardinalitySolution contributes the actual (constant) size of a, possibly enumerated, slot to an
 * expression determining the cardinality of a SerializationRule term.
 */
public class SerializationMatchTermEAttributeSize extends SerializationMatchTermAbstract
{
	protected final @NonNull EAttribute eAttribute;
	protected final @NonNull EnumerationValue enumerationValue;

	public SerializationMatchTermEAttributeSize(/*@NonNull*/ EAttribute eAttribute, @NonNull EnumerationValue enumerationValue) {
		assert eAttribute != null;
		this.eAttribute = eAttribute;
		this.enumerationValue = enumerationValue;
	}

	@Override
	public @Nullable Integer basicGetIntegerSolution(@NonNull RuleMatch ruleMatch) {
		return ruleMatch.getSize(eAttribute, enumerationValue);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof SerializationMatchTermEAttributeSize)) {
			return false;
		}
		SerializationMatchTermEAttributeSize that = (SerializationMatchTermEAttributeSize) obj;
		if (this.eAttribute != that.eAttribute) return false;
		if (!this.enumerationValue.equals(that.enumerationValue)) return false;
		return true;
	}

	public @NonNull EAttribute getEAttribute() {
		return eAttribute;
	}

	public @NonNull EnumerationValue getEnumerationValue() {
		return enumerationValue;
	}

	@Override
	public int hashCode() {
		return getClass().hashCode() + eAttribute.hashCode() + enumerationValue.hashCode() * 7;
	}

	@Override
	public boolean isConstant(@NonNull StaticRuleMatch ruleMatch) {
		return false;
	}

	@Override
	public boolean isKnown(@NonNull StaticRuleMatch ruleMatch) {
		return true;
	}

	@Override
	public void toString(@NonNull StringBuilder s, int depth) {
		s.append("|");
		s.append(eAttribute.getEContainingClass().getName());
		s.append("::");
		s.append(eAttribute.getName());
		s.append(".'");
		s.append(enumerationValue.getName());
		s.append("'|");
	}
}