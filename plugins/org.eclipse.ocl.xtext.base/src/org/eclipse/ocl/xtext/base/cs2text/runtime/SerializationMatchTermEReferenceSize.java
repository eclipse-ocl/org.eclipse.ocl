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

import org.eclipse.emf.ecore.EReference;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.xtext.base.cs2text.solutions.RuleMatch;
import org.eclipse.ocl.xtext.base.cs2text.solutions.StaticRuleMatch;

/**
 * An EAttributeSizeCardinalitySolution contributes the actual (constant) size of a, possibly enumerated, slot to an
 * expression determining the cardinality of a SerializationRule term.
 */
public class SerializationMatchTermEReferenceSize extends SerializationMatchTermAbstract
{
	protected final @NonNull EReference eReference;
	protected final @NonNull ParserRuleValue parserRuleValue;

	public SerializationMatchTermEReferenceSize(@NonNull EReference eReference, @NonNull ParserRuleValue parserRuleValue) {
		this.eReference = eReference;
		this.parserRuleValue = parserRuleValue;
	}

	@Override
	public @Nullable Integer basicGetIntegerSolution(@NonNull RuleMatch ruleMatch) {
		return ruleMatch.getSize(eReference, parserRuleValue);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof SerializationMatchTermEReferenceSize)) {
			return false;
		}
		SerializationMatchTermEReferenceSize that = (SerializationMatchTermEReferenceSize) obj;
		if (this.eReference != that.eReference) return false;
		if (!this.parserRuleValue.equals(that.parserRuleValue)) return false;
		return true;
	}

	public @NonNull EReference getEReference() {
		return eReference;
	}

	public @NonNull ParserRuleValue getParserRuleValue() {
		return parserRuleValue;
	}

	@Override
	public int hashCode() {
		return getClass().hashCode() + eReference.hashCode() + parserRuleValue.hashCode() * 7;
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
		s.append(eReference.getEContainingClass().getName());
		s.append("::");
		s.append(eReference.getName());
		s.append(".'");
		s.append(parserRuleValue.getRuleName());
		s.append("'|");
	}
}