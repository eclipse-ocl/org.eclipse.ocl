/**
 * Copyright (c) 2020, 2021 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 */
package org.eclipse.ocl.pivot.internal.symbolic;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.TypedElement;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.prettyprint.PrettyPrinter;
import org.eclipse.ocl.pivot.internal.symbolic.SymbolicContent.SymbolicCollectionContent;
import org.eclipse.ocl.pivot.internal.symbolic.SymbolicContent.SymbolicMapContent;
import org.eclipse.ocl.pivot.utilities.ValueUtil;
import org.eclipse.ocl.pivot.values.InvalidValue;
import org.eclipse.ocl.pivot.values.SymbolicRefinedValue;
import org.eclipse.ocl.pivot.values.SymbolicValue;

/**
 * @since 1.17
 */
public abstract class AbstractSymbolicRefinedValue extends AbstractSymbolicValue implements SymbolicRefinedValue
{
	/**
	 * AbstractSymbolicFinalValue wraps the evluated SymbolicValue in the final contextual verdict that its
	 * usage is either Dead (not used) or Incompatible (conflicting with the usr's needs).
	 */
	private static abstract class AbstractSymbolicFinalValue extends AbstractSymbolicRefinedValue
	{
		protected AbstractSymbolicFinalValue(@NonNull SymbolicValue value) {
			super(value);
		}

		@Override
		public @Nullable SymbolicSimpleStatus basicGetBooleanStatus() {
			return null; //getDeadStatus();
		}

		@Override
		public @NonNull SymbolicSimpleStatus basicGetInvalidStatus() {
			return SymbolicSimpleStatus.UNDECIDED;
		}

		@Override
		public @NonNull SymbolicSimpleStatus basicGetNullStatus() {
			return SymbolicSimpleStatus.UNDECIDED;
		}

		@Override
		public @Nullable SymbolicNumericValue basicGetNumericValue() {
			return null; //SymbolicStatus.UNDECIDED;
		}

		@Override
		public boolean isNullFree() {
			return false;
		}

		@Override
		public @Nullable SymbolicReason mayBeInvalidReason() {
			return null;
		}

		@Override
		public @Nullable SymbolicReason mayBeNullReason() {
			return null;
		}

		@Override
		public boolean mayBeZero() {
			return false;
		}
	}

	/**
	 * SymbolicDeadValue refines a SymbolicValue to define a dead.(unreachable) evaluation
	 */
	private static class SymbolicDeadValue extends AbstractSymbolicFinalValue
	{
		public SymbolicDeadValue(@NonNull SymbolicValue value) {
			super(value);
			assert !(value instanceof SymbolicRefinedValue);
		}

		@Override
		public void appendName(@NonNull StringBuilder s) {
			s.append("%Dead(");
			value.appendName(s);
			s.append(")");
		}

		@Override
		public @NonNull SymbolicSimpleStatus getDeadStatus() {
			return SymbolicSimpleStatus.SATISFIED;
		}
	}

	/**
	 * SymbolicExceptValue refines a SymbolicValue to exclude a possible value.
	 */
	private static class SymbolicExceptValue extends AbstractSymbolicRefinedValue
	{
		protected final @NonNull Object exceptValue;

		public SymbolicExceptValue(@NonNull SymbolicValue value, @Nullable Object exceptValue) {
			super(value);
			assert ValueUtil.isBoxed(exceptValue);
			this.exceptValue = exceptValue != null ? exceptValue : ValueUtil.NULL_VALUE;
			assert !(ValueUtil.isInvalidValue(exceptValue) && (value.mayBeInvalidReason() == null));
			assert !(ValueUtil.isNullValue(exceptValue) && (value.mayBeNullReason() == null));
			assert !((exceptValue == ValueUtil.ZERO_VALUE) && !value.mayBeZero());



		//	assert !ValueUtil.isNullValue(exceptValue) || !value.isNull();
		}

		@Override
		public void appendName(@NonNull StringBuilder s) {
			s.append("%Except(");
			value.appendName(s);
			s.append(", ");
			ValueUtil.toString(exceptValue, s, 100);
			s.append(")");
		}

		@Override
		public @Nullable SymbolicSimpleStatus basicGetBooleanStatus() {
			SymbolicSimpleStatus booleanStatus = super.basicGetBooleanStatus();
			if (exceptValue.equals(ValueUtil.TRUE_VALUE)) {
				booleanStatus = SymbolicSimpleStatus.UNSATISFIED;
			}
			else if (exceptValue.equals(ValueUtil.FALSE_VALUE)) {
				booleanStatus = SymbolicSimpleStatus.SATISFIED;
			}
			return booleanStatus;
		}

		@Override
		public @Nullable SymbolicSimpleStatus basicGetInvalidStatus() {
			SymbolicSimpleStatus invalidStatus = super.basicGetInvalidStatus();
			if (exceptValue instanceof InvalidValue) {
				invalidStatus = SymbolicSimpleStatus.UNSATISFIED;
			}
			return invalidStatus;
		}

		@Override
		public @Nullable SymbolicSimpleStatus basicGetNullStatus() {
			SymbolicSimpleStatus nullStatus = super.basicGetNullStatus();
			if (exceptValue == ValueUtil.NULL_VALUE) {
				nullStatus = SymbolicSimpleStatus.UNSATISFIED;
			}
			return nullStatus;
		}

		@Override
		public @Nullable SymbolicNumericValue basicGetNumericValue() {
			SymbolicNumericValue zeroStatus = super.basicGetNumericValue();
			if (exceptValue.equals(ValueUtil.ZERO_VALUE)) {
				zeroStatus = SymbolicNumericValue.getNotZero();		// FIXME keep refined wrapper
			}
			return zeroStatus;
		}

	/*	@Override
		public / *final* / @NonNull String toString() {
			StringBuilder s = new StringBuilder();
			toString(s);
			appendContent(s);
			return s.toString();
		} */
	}

	/**
	 * SymboliciIncompatibility refines a SymbolicValue to define an incompatible usage; the wrapped value
	 * conflicts with a requirement such as non-null of the usage..
	 */
	private static class SymboliciIncompatibility extends AbstractSymbolicFinalValue
	{
		protected final @NonNull String incompatibility;
		protected final @NonNull TypedElement typedElement;

		public SymboliciIncompatibility(@NonNull SymbolicValue value, @NonNull String incompatibility, @NonNull TypedElement typedElement) {
			super(value);
			this.incompatibility = incompatibility;
			this.typedElement = typedElement;
			assert !(value instanceof SymboliciIncompatibility);
		}

		@Override
		public void appendName(@NonNull StringBuilder s) {
			s.append("%Incompatibility(");
			value.appendName(s);
			s.append(", ");
			s.append(incompatibility);
			s.append(" at \"");
			s.append(PrettyPrinter.print(typedElement));
			s.append("\")");
		}

		@Override
		public @Nullable String asIncompatibility() {
			return incompatibility;
		}

		@Override
		public @NonNull SymbolicSimpleStatus basicGetInvalidStatus() {
			return SymbolicSimpleStatus.SATISFIED;
		}

		@Override
		public @Nullable SymbolicReason mayBeInvalidReason() {
			SymbolicReason mayBeInvalidReason = value.mayBeInvalidReason();
			if (mayBeInvalidReason != null) {
				return mayBeInvalidReason;
			}
			else {
				return value.mayBeNullReason();
			}
		}

		@Override
		public @Nullable SymbolicReason mayBeNullReason() {
			return null;
		}
	}

	/**
	 * IsZero converts a ZeroStatus value to a BooleanStatus
	 */
	private static class SymbolicIsZeroValue extends AbstractSymbolicRefinedValue
	{
		public SymbolicIsZeroValue(@NonNull SymbolicValue value) {
			super(value);
		}

		@Override
		public void appendName(@NonNull StringBuilder s) {
			s.append("%IsZero(");
			value.appendName(s);
			s.append(")");
		}

		@Override
		public @NonNull SymbolicSimpleStatus basicGetBooleanStatus() {
			SymbolicNumericValue zeroStatus = value.getNumericValue();
			if (zeroStatus.isZero()) {
				return SymbolicSimpleStatus.SATISFIED;
			}
			else if (zeroStatus.isNotZero()) {
				return SymbolicSimpleStatus.UNSATISFIED;
			}
			else {
				return SymbolicSimpleStatus.UNDECIDED;
			}
		}

		@Override
		public @Nullable SymbolicSimpleStatus basicGetInvalidStatus() {
			return null;
		}

		@Override
		public @Nullable SymbolicSimpleStatus basicGetNullStatus() {
			return null;
		}

		@Override
		public @Nullable SymbolicNumericValue basicGetNumericValue() {
			return null; //value.getNumericValue();
		}

		@Override
		public @Nullable Object getKnownValue() {
			SymbolicSimpleStatus booleanStatus = getBooleanStatus();
			return /*booleanStatus == null ? null :*/ booleanStatus.isSatisfied() ? Boolean.TRUE : Boolean.FALSE;
		}
	}

	private static class SymbolicNotValue extends AbstractSymbolicRefinedValue
	{		// FIXME only negate Boolean / correct TypeId
		public SymbolicNotValue(@NonNull SymbolicValue value) {
			super(value);
		}

		@Override
	public void appendName(@NonNull StringBuilder s) {
		s.append("%Not(");
		value.appendName(s);
		s.append(")");
	}

		@Override
		public @NonNull SymbolicSimpleStatus basicGetBooleanStatus() {
			return value.getBooleanStatus().not();		// not super to avoid recursion
		}

		@Override
		public @Nullable SymbolicSimpleStatus basicGetInvalidStatus() {
			SymbolicSimpleStatus invalidStatus = value.basicGetInvalidStatus();
			return invalidStatus != null ? invalidStatus.not() : null;
		}

		@Override
		public @Nullable SymbolicSimpleStatus basicGetNullStatus() {
			SymbolicSimpleStatus nullStatus = value.basicGetNullStatus();
			return nullStatus != null ? nullStatus.not() : null;
		}

		@Override
		public @Nullable SymbolicNumericValue basicGetNumericValue() {
		//	SymbolicStatus zeroStatus = value.basicGetNumericValue();
		//	return zeroStatus != null ? zeroStatus.not() : null;
			return null;
		}
	}

/*	private static class NotSmallerThanSymbolicValue extends AbstractRefinedSymbolicValue
	{
		protected final @NonNull SymbolicValue minSizeValue;

		public NotSmallerThanSymbolicValue(@NonNull SymbolicValue value, @NonNull SymbolicValue minSizeValue) {
			super(value);
			this.minSizeValue = minSizeValue;
		}

		@Override
		public boolean isSmallerThan(@NonNull SymbolicValue minSizeValue) {
			// TODO Auto-generated method stub
		//	return super.isSmallerThan(minSizeValue);
			throw new UnsupportedOperationException();
		}

		@Override
		public boolean mayBeSmallerThan(@NonNull SymbolicValue minSizeValue) {
			// TODO Auto-generated method stub
		//	return super.mayBeSmallerThan(minSizeValue);
			throw new UnsupportedOperationException();
		}

		@Override
		public @NonNull String toString() {
			return "%NotSmallerThan(" + value.toString() + "," + minSizeValue.toString() + ")";
		}
	} */

	private static class SymbolicNullFreeValue extends AbstractSymbolicRefinedValue
	{
		public SymbolicNullFreeValue(@NonNull SymbolicValue value) {
			super(value);
		}

		@Override
		public void appendName(@NonNull StringBuilder s) {
			s.append("%NullFree(");
			value.appendName(s);
			s.append(")");
		}

		@Override
		public boolean isNullFree() {
			return true;
		}
	}

	public static class SymbolicRefinedContentValue extends AbstractSymbolicRefinedValue
	{
		private final @NonNull SymbolicContent content;

		public SymbolicRefinedContentValue(@NonNull SymbolicValue value) {
			super(value);
			this.content = value.getContent().shallowClone();
		}

		@Override
		public void appendName(@NonNull StringBuilder s) {
			s.append("%Refined(");
			value.appendName(s);
			s.append(")");
		}

		@Override
		public @NonNull SymbolicContent basicGetContent() {
			return content;
		}

		@Override
		public @Nullable SymbolicNumericValue basicGetNumericValue() {
			return null;//content.getSize().getNumericValue();
		}

		@Override
		public @NonNull SymbolicCollectionContent getCollectionContent() {
			return (SymbolicCollectionContent) content;
		}

		@Override
		public @NonNull SymbolicContent getContent() {
			return content;
		}

		@Override
		public @NonNull SymbolicMapContent getMapContent() {
			return (SymbolicMapContent) content;
		}

		public @NonNull SymbolicValue getSize() {
			return content.getSize();
		}

		@Override
		public @Nullable SymbolicReason mayBeInvalidReason() {
			return value.mayBeInvalidReason();
		}

		@Override
		public @Nullable SymbolicReason mayBeNullReason() {
			return value.mayBeNullReason();
		}

		public void setSize(@NonNull SymbolicValue refinedSize) {
			content.setSize(refinedSize);
		}
	}

	/**
	 * SymbolicSizeValue refines to select the size of a values content.
	 */
	private static class SymbolicSizeValue extends AbstractSymbolicRefinedValue
	{
		public SymbolicSizeValue(@NonNull SymbolicValue value) {
			super(value);
		}

		@Override
		public void appendName(@NonNull StringBuilder s) {
			s.append("%Size(");
			value.appendName(s);
			s.append(")");
		}

		@Override
		public @Nullable SymbolicSimpleStatus basicGetInvalidStatus() {
			return null;
		}

		@Override
		public @Nullable SymbolicSimpleStatus basicGetNullStatus() {
			return null;
		}

		@Override
		public @NonNull SymbolicNumericValue basicGetNumericValue() {
			return value.getContent().getSize().getNumericValue();
		}
	}

/*	public static @NonNull SymbolicValue createBeKnownValue(@NonNull SymbolicValue symbolicValue, @NonNull Object knownValue) {
		if (!symbolicValue.isZero()) {
			return new BeKnownSymbolicValue(symbolicValue, knownValue);
		}
		else {
			return symbolicValue;
		}
	} */

	public static @NonNull SymbolicValue createDeadValue(SymbolicValue symbolicValue) {
		if (!symbolicValue.isDead()) {
			return new SymbolicDeadValue(symbolicValue.getBaseValue());
		}
		else {
			return symbolicValue;
		}
	}

	public static @NonNull SymbolicValue createExceptValue(@NonNull SymbolicValue symbolicValue, @Nullable Object exceptValue) {
		assert ValueUtil.isBoxed(exceptValue);
		if (((exceptValue == null) && (symbolicValue.mayBeNullReason() == null))
		 || ((exceptValue instanceof InvalidValue) && (symbolicValue.mayBeInvalidReason() == null))
		 || (ValueUtil.ZERO_VALUE.equals(exceptValue) && !symbolicValue.mayBeZero())
		 || (ValueUtil.FALSE_VALUE.equals(exceptValue) && !symbolicValue.mayBeFalse())
		 || (ValueUtil.TRUE_VALUE.equals(exceptValue) && !symbolicValue.mayBeTrue())) {
			return symbolicValue;
		}
		else {
			return new SymbolicExceptValue(symbolicValue, exceptValue);
		}
	}

	/*	public static @NonNull SymbolicValue createBeKnownValue(@NonNull SymbolicValue symbolicValue, @NonNull Object knownValue) {
		if (!symbolicValue.isZero()) {
			return new BeKnownSymbolicValue(symbolicValue, knownValue);
		}
		else {
			return symbolicValue;
		}
	} */

	public static @NonNull SymbolicValue createIncompatibility(@NonNull SymbolicValue symbolicValue, @NonNull String incompatibility, @NonNull TypedElement typedElement) {
		if (symbolicValue.asIncompatibility() != null) {
			return symbolicValue;
		}
	//	if (!symbolicValue.isDead()) {
			return new SymboliciIncompatibility(symbolicValue, incompatibility, typedElement);
	//	}
	//	else {
	//		return symbolicValue;
	//	}
	}

	public static @NonNull SymbolicValue createIsZeroValue(@NonNull SymbolicValue symbolicValue) {
		if (!symbolicValue.isZero()) {
			return new SymbolicIsZeroValue(symbolicValue);
		}
		else {
			return symbolicValue;
		}
	}

	public static @NonNull SymbolicValue createNotEmpty(@NonNull SymbolicValue symbolicValue) {
		SymbolicContent content = symbolicValue.getContent();
		SymbolicValue size = content.getSize();
		if (size.mayBeZero()) {
			symbolicValue = createRefinedContent(symbolicValue);
			content = symbolicValue.getContent();
			content.setSize(createExceptValue(size, ValueUtil.integerValueOf(0)));
		}
		return symbolicValue;
	}

	public static @NonNull SymbolicValue createNotValue(@NonNull SymbolicValue symbolicValue) {
		return new SymbolicNotValue(symbolicValue);
	}

	public static @NonNull SymbolicValue createNullFreeValue(@NonNull SymbolicValue symbolicValue) {
		if (!symbolicValue.isNullFree()) {
			return new SymbolicNullFreeValue(symbolicValue);
		}
		else {
			return symbolicValue;
		}
	}

	public static @NonNull SymbolicRefinedContentValue createRefinedContent(@NonNull SymbolicValue symbolicValue) {
		return new SymbolicRefinedContentValue(symbolicValue);
	}

	public static @NonNull SymbolicValue createSizeValue(@NonNull SymbolicValue symbolicValue) {
		return new SymbolicSizeValue(symbolicValue);
	}

	public static @NonNull SymbolicValue createSmallerThanValue(@NonNull SymbolicValue symbolicValue, @NonNull SymbolicValue minSizeValue) {
		throw new UnsupportedOperationException();
	}

	protected final @NonNull SymbolicValue value;

	protected AbstractSymbolicRefinedValue(@NonNull SymbolicValue value) {
		this.value = value;
		assert !value.isDead();
	}

//	@Override
//	public void appendName(@NonNull StringBuilder s) {
//		throw new UnsupportedOperationException();
//	}

	@Override
	public @Nullable SymbolicSimpleStatus basicGetBooleanStatus() {
		return value.basicGetBooleanStatus();
	}

	@Override
	public @Nullable SymbolicContent basicGetContent() {
		return value.basicGetContent();
	}

	@Override
	public @Nullable SymbolicSimpleStatus basicGetInvalidStatus() {
		return value.basicGetInvalidStatus();
	}

	@Override
	public @Nullable SymbolicSimpleStatus basicGetNullStatus() {
		return value.basicGetNullStatus();
	}

	@Override
	public @Nullable SymbolicNumericValue basicGetNumericValue() {
		return value.basicGetNumericValue();
	}

	@Override
	public @NonNull SymbolicValue getBaseValue() {
		return value.getBaseValue();
	}

	@Override
	public @NonNull SymbolicCollectionContent getCollectionContent() {
		return value.getCollectionContent();
	}

	@Override
	public @NonNull SymbolicContent getContent() {
		return value.getContent();
	}

	@Override
	public @NonNull SymbolicSimpleStatus getDeadStatus() {
		return value.getDeadStatus();
	}

	@Override
	public @NonNull SymbolicMapContent getMapContent() {
		return value.getMapContent();
	}

	@Override
	public @NonNull Type getType() {
		return value.getType();
	}

//	@Override
//	public @NonNull Type getType(@NonNull StandardLibrary standardLibrary) {
//		return value.getType(standardLibrary);
//	}

	@Override
	public @NonNull TypeId getTypeId() {
		return value.getTypeId();
	}

	@Override
	public @Nullable Object getKnownValue() {
		return value.getKnownValue();
	}

	@Override
	public boolean isCollection() {
		return value.isCollection();
	}

	@Override
	public boolean isKnown() {
		return value.isKnown();
	}

	@Override
	public boolean isMap() {
		return value.isMap();
	}

	@Override
	public boolean isNullFree() {
		return value.isNullFree();
	}

	@Override
	public @Nullable SymbolicReason mayBeInvalidReason() {
		boolean mayBeInvalid = !getInvalidStatus().isUnsatisfied();
//		return SymbolicUtil.mayBeInvalidReason(mayBeInvalid);
		return mayBeInvalid ? value.mayBeInvalidReason() : null;
	}

	@Override
	public @Nullable SymbolicReason mayBeNullReason() {
		boolean mayBeNull = !getNullStatus().isUnsatisfied();
//		return SymbolicUtil.mayBeNullReason(mayBeNull);
		return mayBeNull ? value.mayBeNullReason() : null;
	}

	@Override
	public void toString(@NonNull StringBuilder s) {
//		ValueUtil.toString(value, s, 100);
		appendName(s);
		appendContent(s);
		s.append(" «");
		SymbolicReason mayBeNullReason = mayBeNullReason();
		if (mayBeNullReason != null) {
			mayBeNullReason.toString(s);
		}
		s.append("» «");
		SymbolicReason mayBeInvalidReason = mayBeInvalidReason();
		if (mayBeInvalidReason != null) {
			mayBeInvalidReason.toString(s);
		}
		s.append("»");
	}
}
