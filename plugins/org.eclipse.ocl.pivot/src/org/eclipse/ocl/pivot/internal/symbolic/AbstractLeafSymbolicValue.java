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

import java.util.List;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.CollectionType;
import org.eclipse.ocl.pivot.NavigationCallExp;
import org.eclipse.ocl.pivot.OCLExpression;
import org.eclipse.ocl.pivot.OperationCallExp;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.TypedElement;
import org.eclipse.ocl.pivot.ids.CollectionTypeId;
import org.eclipse.ocl.pivot.ids.MapTypeId;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.symbolic.SymbolicContent.SymbolicCollectionContent;
import org.eclipse.ocl.pivot.internal.symbolic.SymbolicContent.SymbolicMapContent;
import org.eclipse.ocl.pivot.library.LibraryOperation;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.values.SymbolicValue;

/**
 * @since 1.17
 */
public abstract class AbstractLeafSymbolicValue extends AbstractSymbolicValue
{
	public static class SymbolicCollectionValue extends AbstractLeafSymbolicValue
	{
		public SymbolicCollectionValue(@NonNull String name, @NonNull OCLExpression expression, boolean mayBeNull, boolean mayBeInvalid) {
			super(name, expression, mayBeNull, mayBeInvalid, null);
			assert expression.getType() instanceof CollectionType;
		}

		@Override
		public boolean isCollection() {
			return true;
		}

	/*	@Override
		public void toString(@NonNull StringBuilder s, int lengthLimit) {
			s.append(operation.getClass().getSimpleName());
			s.append("(");
			s.append(sourceValue);
			s.append(",");
			s.append(argumentValue);
			s.append(")");
			s.append(":");
			super.toString(s, lengthLimit);
		} */
	}

	public static class SymbolicNavigationCallValue extends AbstractLeafSymbolicValue
	{
		protected final @NonNull SymbolicValue sourceValue;
		protected final @NonNull Property property;

		public SymbolicNavigationCallValue(@NonNull String name, @NonNull NavigationCallExp navigationCallExp, boolean mayBeNull, boolean mayBeInvalid, @NonNull SymbolicValue sourceValue) {
			super(name, navigationCallExp, mayBeNull, mayBeInvalid, null);
			this.sourceValue = sourceValue;
			this.property = PivotUtil.getReferredProperty(navigationCallExp);
		}

		@Override
		public void appendName(@NonNull StringBuilder s) {
			((AbstractSymbolicValue)sourceValue).appendName(s);
			s.append(".");
			s.append(property.getName());
		}

		public @Nullable Object getSourceValue() {
			return sourceValue;
		}
	}

	public static class SymbolicOperationCallValue extends AbstractLeafSymbolicValue
	{
		protected final @NonNull LibraryOperation operation;
		protected final @NonNull List<@NonNull SymbolicValue> boxedSourceAndArgumentValues;

		public SymbolicOperationCallValue(@NonNull String name, @NonNull OperationCallExp operationCallExp, boolean mayBeNull, boolean mayBeInvalid,
				@NonNull LibraryOperation operation, @NonNull List<@NonNull SymbolicValue> boxedSourceAndArgumentValues) {
			super(name, operationCallExp, mayBeNull, mayBeInvalid, null);
			this.operation = operation;
			this.boxedSourceAndArgumentValues = boxedSourceAndArgumentValues;
		//	assert operation == operationCallExp.getReferredOperation().getImplementation();		// XXX lazy init may not have happened
			assert boxedSourceAndArgumentValues.size() == operationCallExp.getReferredOperation().getOwnedParameters().size()+1;
		}

//		public @NonNull List<@Nullable Object> getBoxedSourceAndArgumentValues() {
		//	return boxedSourceAndArgumentValues;
//			throw new UnsupportedOperationException();		// XXX
//		}

		@Override
		public void appendName(@NonNull StringBuilder s) {
			boxedSourceAndArgumentValues.get(0).appendName(s);
			s.append(".");
			s.append(operation.getClass().getSimpleName());
			s.append("(");
			for (int i = 1; i < boxedSourceAndArgumentValues.size(); i++) {
				SymbolicValue argumentValue = boxedSourceAndArgumentValues.get(i);
				if (i > 1) {
					s.append(",");
				}
				argumentValue.appendName(s);
			}
			s.append(")");
		}
	}

	public AbstractLeafSymbolicValue() {
		this.name = TypeId.OCL_INVALID_NAME;
		this.typeId = TypeId.OCL_INVALID;
		this.mayBeNull = false;
		this.mayBeInvalid = false;
	}

	protected final @NonNull String name;
	protected final @NonNull TypeId typeId;
	protected final boolean mayBeNull;
	protected final boolean mayBeInvalid;
	private @Nullable SymbolicContent content = null;

	protected AbstractLeafSymbolicValue(@NonNull String name, @NonNull TypedElement typedElement, boolean mayBeNull, boolean mayBeInvalid,@Nullable SymbolicContent content) {
		// FIXME getBehavioralType needed by test_umlValidation_Bug467192
		this(name, ClassUtil.nonNullState(PivotUtil.getBehavioralType(typedElement)).getTypeId(), mayBeNull, mayBeInvalid, content);
	}

	protected AbstractLeafSymbolicValue(@NonNull String name, @NonNull TypeId typeId, boolean mayBeNull, boolean mayBeInvalid, @Nullable SymbolicContent content) {
		this.name = name;
		this.typeId = typeId;
		this.mayBeNull = mayBeNull;
		this.mayBeInvalid = mayBeInvalid;
		this.content = content;
	}

	@Override
	public void appendName(@NonNull StringBuilder s) {
		s.append(name);
	}

	protected void appendType(@NonNull StringBuilder s) {
		s.append(" : ");
		s.append(typeId);
		s.append("[");
		s.append(mayBeNull ? "?" : "1");
		if (mayBeInvalid) {
			s.append("!");
		}
		s.append("]");
	}

	@Override
	public @Nullable SymbolicSimpleStatus basicGetBooleanStatus() {
		if (typeId == TypeId.BOOLEAN) {
			return SymbolicSimpleStatus.UNDECIDED;
		}
		return null;
	//	throw new IllegalStateException("No Boolean source configured");
	}

	@Override
	public @Nullable SymbolicContent basicGetContent() {
		return content;
	}

	@Override
	public @NonNull SymbolicSimpleStatus basicGetInvalidStatus() {
		return mayBeInvalid ? SymbolicSimpleStatus.UNDECIDED : SymbolicSimpleStatus.UNSATISFIED;
	}

	@Override
	public @NonNull SymbolicSimpleStatus basicGetNullStatus() {
		return mayBeNull ? SymbolicSimpleStatus.UNDECIDED : SymbolicSimpleStatus.UNSATISFIED;
	}

	@Override
	public @Nullable SymbolicNumericValue basicGetNumericValue() {
		if (isNumeric()) {	// FIXME Behavioral
			return SymbolicNumericValue.getZeroOrNotZero();
		}
		return null;
	}

	protected @NonNull SymbolicCollectionContent createCollectionContent() {
		return new SymbolicCollectionContent("c#" + name + "%", (CollectionTypeId)typeId, null);
	}

	protected @NonNull SymbolicMapContent createMapContent() {
		return new SymbolicMapContent("m#" + name + "%", (MapTypeId)typeId, null);
	}

	@Override
	public @NonNull SymbolicValue getBaseValue() {
		return this;
	}

	@Override
	public @NonNull SymbolicCollectionContent getCollectionContent() {
		assert typeId instanceof CollectionTypeId;
		SymbolicContent content2 = content;
		if (content2 == null) {
			content = content2 = createCollectionContent();
		}
		return (SymbolicCollectionContent)content2;
	}

//	@Override
//	public @NonNull CollectionTypeId getCollectionTypeId() {
//		throw new InvalidValueException(PivotMessages.ConvertibleValueRequired, "Invalid");
//	}

	@Override
	public @NonNull SymbolicContent getContent() {
		if (typeId instanceof CollectionTypeId) {
			return getCollectionContent();
		}
		if (typeId instanceof MapTypeId) {
			return getMapContent();
		}
		throw new IllegalStateException();
	}

	@Override
	public @NonNull SymbolicSimpleStatus getDeadStatus() {
		return SymbolicSimpleStatus.UNSATISFIED;
	}

	@Override
	public @Nullable Object getKnownValue() {
		throw new IllegalStateException();		// Should be guarded by isKnown().
	}

	@Override
	public @NonNull SymbolicMapContent getMapContent() {
		assert typeId instanceof MapTypeId;
		SymbolicContent content2 = content;
		if (content2 == null) {
			content = content2 = createMapContent();
		}
		return (SymbolicMapContent)content2;
	}

	@Override
	public @NonNull TypeId getTypeId() {
		return typeId;
	}

	@Override
	public boolean isCollection() {
		return typeId instanceof CollectionTypeId;
	}

	@Override
	public boolean isKnown() {
		return false;
	}

	@Override
	public boolean isMap() {
		return typeId instanceof MapTypeId;
	}

	@Override
	public boolean isNullFree() {
		return false;			// XXX
	}

	protected boolean isNumeric() {
		return (typeId == TypeId.REAL) || (typeId == TypeId.INTEGER) || (typeId == TypeId.UNLIMITED_NATURAL);
	}

	@Override
	public void toString(@NonNull StringBuilder s) {
		appendName(s);
		appendType(s);
		appendContent(s);
	}
}
