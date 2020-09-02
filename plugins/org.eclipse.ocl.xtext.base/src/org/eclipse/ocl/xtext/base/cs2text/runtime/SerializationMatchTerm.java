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

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.xtext.base.cs2text.xtext.StaticRuleMatch;

/**
 * A CardinalitySolution defines the behaviour of nodes in an expression tree that provides the
 * limited capability to compute the cardinalities of SerilaizationRule terms from the actual
 * feture slot sizes of an actual element to be serialized.
 */
public abstract class SerializationMatchTerm
{
	public abstract static class SerializationMatchTermAbstractBinary extends SerializationMatchTerm
	{
		protected final @NonNull SerializationMatchTerm left;
		protected final @NonNull SerializationMatchTerm right;
		private @Nullable Set<@NonNull SerializationMatchTerm> childClosure = null;

		public SerializationMatchTermAbstractBinary(@NonNull SerializationMatchTerm left, @NonNull SerializationMatchTerm right) {
			this.left = left;
			this.right = right;
		}

		@Override
		public @NonNull Set<@NonNull SerializationMatchTerm> getChildClosure() {
			Set<@NonNull SerializationMatchTerm> childClosure2 = childClosure;
			if (childClosure2 == null) {
				childClosure = childClosure2 = new HashSet<>();
				childClosure2.add(this);
				childClosure2.addAll(left.getChildClosure());
				childClosure2.addAll(right.getChildClosure());
			}
			return childClosure2;
		}

		public @NonNull SerializationMatchTerm getLeft() {
			return left;
		}

		public @NonNull SerializationMatchTerm getRight() {
			return right;
		}

		@Override
		public int hashCode() {
			return getClass().hashCode() + left.hashCode() + right.hashCode() * 7;
		}

		@Override
		public boolean isConstant(@NonNull StaticRuleMatch ruleMatch) {
			return left.isConstant(ruleMatch) && right.isConstant(ruleMatch);
		}

		@Override
		public boolean isKnown(@NonNull StaticRuleMatch ruleMatch) {
			return left.isKnown(ruleMatch) && right.isKnown(ruleMatch);
		}
	}

	public static class SerializationMatchTermAdd extends SerializationMatchTermAbstractBinary
	{
		public SerializationMatchTermAdd(@NonNull SerializationMatchTerm left, @NonNull SerializationMatchTerm right) {
			super(left, right);
		}

		@Override
		public @Nullable Integer basicGetIntegerSolution(@NonNull RuleMatch ruleMatch) {
			Integer intLeft = left.basicGetIntegerSolution(ruleMatch);
			if (intLeft == null) {
				return null;
			}
			Integer intRight = right.basicGetIntegerSolution(ruleMatch);
			if (intRight == null) {
				return null;
			}
			return intLeft + intRight;
		}

		@Override
		public boolean equals(Object obj) {
			if (obj == this) {
				return true;
			}
			if (!(obj instanceof SerializationMatchTermAdd)) {
				return false;
			}
			SerializationMatchTermAdd that = (SerializationMatchTermAdd) obj;
			if (!this.left.equals(that.left)) return false;
			if (!this.right.equals(that.right)) return false;
			return true;
		}

		@Override
		public void toString(@NonNull StringBuilder s, int depth) {
			s.append("(");
			left.toString(s, depth);
			s.append(" + ");
			right.toString(s, depth);
			s.append(")");
		}
	}

	public static class SerializationMatchTermDivide extends SerializationMatchTermAbstractBinary
	{
		public SerializationMatchTermDivide(@NonNull SerializationMatchTerm left, @NonNull SerializationMatchTerm right) {
			super(left, right);
		}

		@Override
		public @Nullable Integer basicGetIntegerSolution(@NonNull RuleMatch ruleMatch) {
			Integer intRight = right.basicGetIntegerSolution(ruleMatch);
			if ((intRight == null) || (intRight == 0)) {
				return null;
			}
			Integer intLeft = left.basicGetIntegerSolution(ruleMatch);
			if (intLeft == null) {
				return null;
			}
			int result = Math.floorDiv(intLeft, intRight);
			if (result * intRight != intLeft) {
				return null;
			}
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (obj == this) {
				return true;
			}
			if (!(obj instanceof SerializationMatchTermDivide)) {
				return false;
			}
			SerializationMatchTermDivide that = (SerializationMatchTermDivide) obj;
			if (!this.left.equals(that.left)) return false;
			if (!this.right.equals(that.right)) return false;
			return true;
		}
		@Override
		public void toString(@NonNull StringBuilder s, int depth) {
			s.append("(");
			left.toString(s, depth);
			s.append(" / ");
			right.toString(s, depth);
			s.append(")");
		}
	}

	/**
	 * An EAttributeSizeCardinalitySolution contributes the actual (constant) size of a, possibly enumerated, slot to an
	 * expression determining the cardinality of a SerializationRule term.
	 */
	public static class SerializationMatchTermEAttributeSize extends SerializationMatchTerm
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

	/**
	 * An EAttributeSizeCardinalitySolution contributes the actual (constant) size of a, possibly enumerated, slot to an
	 * expression determining the cardinality of a SerializationRule term.
	 */
	public static class SerializationMatchTermEReferenceSize extends SerializationMatchTerm
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

	/**
	 * An EAttributeSizeCardinalitySolution contributes the actual (constant) size of a, possibly enumerated, slot to an
	 * expression determining the cardinality of a SerializationRule term.
	 */
	public static class SerializationMatchTermEStructuralFeatureSize extends SerializationMatchTerm
	{
		protected final @NonNull EStructuralFeature eStructuralFeature;

		public SerializationMatchTermEStructuralFeatureSize(/*@NonNull*/ EStructuralFeature eStructuralFeature) {
			assert eStructuralFeature != null;
			this.eStructuralFeature = eStructuralFeature;
		}

		@Override
		public @Nullable Integer basicGetIntegerSolution(@NonNull RuleMatch ruleMatch) {
			return ruleMatch.getSize(eStructuralFeature);
		}

		@Override
		public boolean equals(Object obj) {
			if (obj == this) {
				return true;
			}
			if (!(obj instanceof SerializationMatchTermEStructuralFeatureSize)) {
				return false;
			}
			SerializationMatchTermEStructuralFeatureSize that = (SerializationMatchTermEStructuralFeatureSize)obj;
			if (this.eStructuralFeature != that.eStructuralFeature) return false;
			return true;
		}

		public @NonNull EStructuralFeature getEStructuralFeature() {
			return eStructuralFeature;
		}

		@Override
		public int hashCode() {
			return getClass().hashCode() + eStructuralFeature.hashCode();
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
			s.append(eStructuralFeature.getEContainingClass().getName());
			s.append("::");
			s.append(eStructuralFeature.getName());
			s.append("|");
		}
	}

	public static class SerializationMatchTermGreaterThan extends SerializationMatchTermAbstractBinary
	{
		public SerializationMatchTermGreaterThan(@NonNull SerializationMatchTerm left, @NonNull SerializationMatchTerm right) {
			super(left, right);
		}

		@Override
		public @Nullable Integer basicGetIntegerSolution(@NonNull RuleMatch ruleMatch) {
			Integer intLeft = left.basicGetIntegerSolution(ruleMatch);
			if (intLeft == null) {
				return null;
			}
			Integer intRight = right.basicGetIntegerSolution(ruleMatch);
			if (intRight == null) {
				return null;
			}
			return intLeft > intRight ? 1 : 0;
		}

		@Override
		public boolean equals(Object obj) {
			if (obj == this) {
				return true;
			}
			if (!(obj instanceof SerializationMatchTermGreaterThan)) {
				return false;
			}
			SerializationMatchTermGreaterThan that = (SerializationMatchTermGreaterThan) obj;
			if (!this.left.equals(that.left)) return false;
			if (!this.right.equals(that.right)) return false;
			return true;
		}

		@Override
		public void toString(@NonNull StringBuilder s, int depth) {
			s.append("(");
			left.toString(s, depth);
			s.append(" > ");
			right.toString(s, depth);
			s.append(")");
		}
	}

	public static class SerializationMatchTermInteger extends SerializationMatchTerm
	{
		protected final int value;

		public SerializationMatchTermInteger(int value) {
			this.value = value;
		}

		@Override
		public boolean equals(Object obj) {
			if (obj == this) {
				return true;
			}
			if (!(obj instanceof SerializationMatchTermInteger)) {
				return false;
			}
			SerializationMatchTermInteger that = (SerializationMatchTermInteger) obj;
			if (this.value != that.value) return false;
			return true;
		}

		@Override
		public @NonNull Integer basicGetIntegerSolution(@NonNull RuleMatch ruleMatch) {
			return value;
		}

		public int getValue() {
			return value;
		}

		@Override
		public int hashCode() {
			return getClass().hashCode() + value;
		}

		@Override
		public boolean isConstant(@NonNull StaticRuleMatch ruleMatch) {
			return true;
		}

		@Override
		public boolean isKnown(@NonNull StaticRuleMatch ruleMatch) {
			return true;
		}

		@Override
		public void toString(@NonNull StringBuilder s, int depth) {
			s.append(Integer.toString(value));
		}
	}

	public static class SerializationMatchTermMultiply extends SerializationMatchTermAbstractBinary
	{
		public SerializationMatchTermMultiply(@NonNull SerializationMatchTerm left, @NonNull SerializationMatchTerm right) {
			super(left, right);
		}

		@Override
		public @Nullable Integer basicGetIntegerSolution(@NonNull RuleMatch ruleMatch) {
			Integer intRight = right.basicGetIntegerSolution(ruleMatch);
			if ((intRight == null) || (intRight == 0)) {
				return null;
			}
			Integer intLeft = left.basicGetIntegerSolution(ruleMatch);
			if (intLeft == null) {
				return null;
			}
			return intLeft * intRight;
		}

		@Override
		public boolean equals(Object obj) {
			if (obj == this) {
				return true;
			}
			if (!(obj instanceof SerializationMatchTermMultiply)) {
				return false;
			}
			SerializationMatchTermMultiply that = (SerializationMatchTermMultiply) obj;
			if (!this.left.equals(that.left)) return false;
			if (!this.right.equals(that.right)) return false;
			return true;
		}
		@Override
		public void toString(@NonNull StringBuilder s, int depth) {
			s.append("(");
			left.toString(s, depth);
			s.append(" * ");
			right.toString(s, depth);
			s.append(")");
		}
	}

	public static class SerializationMatchTermSubtract extends SerializationMatchTermAbstractBinary
	{
		public SerializationMatchTermSubtract(@NonNull SerializationMatchTerm left, @NonNull SerializationMatchTerm right) {
			super(left, right);
		}

		@Override
		public @Nullable Integer basicGetIntegerSolution(@NonNull RuleMatch ruleMatch) {
			Integer intLeft = left.basicGetIntegerSolution(ruleMatch);
			if (intLeft == null) {
				return null;
			}
			Integer intRight = right.basicGetIntegerSolution(ruleMatch);
			if (intRight == null) {
				return null;
			}
			return intLeft - intRight;
		}

		@Override
		public boolean equals(Object obj) {
			if (obj == this) {
				return true;
			}
			if (!(obj instanceof SerializationMatchTermSubtract)) {
				return false;
			}
			SerializationMatchTermSubtract that = (SerializationMatchTermSubtract) obj;
			if (!this.left.equals(that.left)) return false;
			if (!this.right.equals(that.right)) return false;
			return true;
		}

		@Override
		public void toString(@NonNull StringBuilder s, int depth) {
			s.append("(");
			left.toString(s, depth);
			s.append(" - ");
			right.toString(s, depth);
			s.append(")");
		}
	}

	public static class SerializationMatchTermUnsupported extends SerializationMatchTerm
	{
		public SerializationMatchTermUnsupported() {}

		@Override
		public boolean equals(Object obj) {
			if (obj == this) {
				return true;
			}
			if (!(obj instanceof SerializationMatchTermUnsupported)) {
				return false;
			}
			return true;
		}

		@Override
		public int hashCode() {
			return getClass().hashCode();
		}

		@Override
		public boolean isConstant(@NonNull StaticRuleMatch ruleMatch) {
			return false;
		}

		@Override
		public boolean isKnown(@NonNull StaticRuleMatch ruleMatch) {
			return false;
		}

		@Override
		public void toString(@NonNull StringBuilder s, int depth) {
			s.append("?");
		}
	}

	/**
	 * A VariableCardinalitySolution contributes the already computed value of a cardinality variable to an
	 * expression determining the cardinality of a SerializationRule term.
	 */
	public static class SerializationMatchTermVariable extends SerializationMatchTerm
	{
		protected final int cardinalityVariableIndex;

		public SerializationMatchTermVariable(int cardinalityVariableIndex) {
			this.cardinalityVariableIndex = cardinalityVariableIndex;
		}

		@Override
		public @Nullable Integer basicGetIntegerSolution(@NonNull RuleMatch ruleMatch) {
			return ruleMatch.basicGetIntegerSolution(cardinalityVariableIndex);
		}

		@Override
		public boolean equals(Object obj) {
			if (obj == this) {
				return true;
			}
			if (!(obj instanceof SerializationMatchTermVariable)) {
				return false;
			}
			SerializationMatchTermVariable that = (SerializationMatchTermVariable) obj;
			return this.cardinalityVariableIndex == that.cardinalityVariableIndex;
		}

		public int getVariableIndex() {
			return cardinalityVariableIndex;
		}

		@Override
		public int hashCode() {
			return getClass().hashCode() + cardinalityVariableIndex;
		}

		@Override
		public boolean isConstant(@NonNull StaticRuleMatch ruleMatch) {
			return false;
		}

		@Override
		public boolean isKnown(@NonNull StaticRuleMatch ruleMatch) {
			return false;
		}

//		@Override
//		public boolean isOptional() {
//			return cardinalityVariable.mayBeNone() && !cardinalityVariable.mayBeMany();
//		}

		@Override
		public void toString(@NonNull StringBuilder s, int depth) {
			s.append("V" + cardinalityVariableIndex);
		}
	}

	/**
	 * Return the value of the expression value using the actual characteristic of the user element slots if available.
	 * Returns null if evaluation fails.
	 */
	public @Nullable Integer basicGetIntegerSolution(@NonNull RuleMatch ruleMatch) {
		return null;
	}

	@Override
	public abstract boolean equals(Object obj);

	/**
	 * Return the closure of this and all child solutions.
	 */
	public @NonNull Set<@NonNull SerializationMatchTerm> getChildClosure() {
		return Collections.singleton(this);
	}

	@Override
	public abstract int hashCode();

	/**
	 * Return true if this is a foldable constant value at compile time. i.e an expression involving integer literals.
	 */
	public abstract boolean isConstant(@NonNull StaticRuleMatch ruleMatch);

	/**
	 * Return true if this will be a known constant value at run time. i.e. an expression involving actual feature slot counts.
	 */
	public abstract boolean isKnown(@NonNull StaticRuleMatch ruleMatch);

//	@Override
//	public boolean isOptional() {
//		return false;
//	}

	public boolean isRuntime() {
		return false;
	}

	@Override
	public @NonNull String toString() {
		StringBuilder s = new StringBuilder();
		toString(s, 0);
		return s.toString();
	}

	public abstract void toString(@NonNull StringBuilder s, int depth);
}
