/*******************************************************************************
 * Copyright (c) 2012, 2022 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.internal.ids;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.TemplateParameter;
import org.eclipse.ocl.pivot.ids.AbstractSingletonScope;
import org.eclipse.ocl.pivot.ids.BindingsId;
import org.eclipse.ocl.pivot.ids.ElementId;
import org.eclipse.ocl.pivot.ids.IdHash;
import org.eclipse.ocl.pivot.ids.IdVisitor;
import org.eclipse.ocl.pivot.ids.OperationId;
import org.eclipse.ocl.pivot.ids.ParametersId;
import org.eclipse.ocl.pivot.ids.PropertyId;
import org.eclipse.ocl.pivot.ids.SingletonScope.AbstractKeyAndValue;
import org.eclipse.ocl.pivot.ids.TemplateParameterId;
import org.eclipse.ocl.pivot.ids.TemplateableId;
import org.eclipse.ocl.pivot.ids.TypeId;

public class TemplateParameterIdImpl extends AbstractElementId implements TemplateParameterId
{
	private static class TemplateParameterIdValue extends AbstractKeyAndValue<@NonNull TemplateParameterId>
	{
		private final @NonNull TemplateableId templateableId;
		private final int index;
		private final @NonNull TypeId @NonNull [] constrainingTypeIds;

		private TemplateParameterIdValue(@NonNull TemplateableId templateableId, int index, @NonNull TypeId @NonNull [] constrainingTypeIds) {
			super(IdHash.createConstrainedHash(templateableId, index, constrainingTypeIds));
			this.templateableId = templateableId;
			this.index = index;
			this.constrainingTypeIds = constrainingTypeIds;
		}

		@Override
		public @NonNull TemplateParameterId createSingleton() {
			return new TemplateParameterIdImpl(hashCode, templateableId, index, constrainingTypeIds);
		}

		@Override
		public boolean equals(@Nullable Object object) {
			if (!(object instanceof TemplateParameterIdImpl)) {
				return false;
			}
			TemplateParameterIdImpl that = (TemplateParameterIdImpl)object;
			return that.equals(templateableId, index, constrainingTypeIds);
		}
	}

	/**
	 * @since 1.18
	 */
	public static class TemplateParameterIdSingletonScope extends AbstractSingletonScope<@NonNull TemplateParameterId, @NonNull TemplateParameter>
	{
		/**
		 * @since 7.0
		 */
		public @NonNull TemplateParameterId getSingleton(@NonNull TemplateableId templateableId, int index, @NonNull TypeId @NonNull [] constrainingTypeIds) {
			return getSingletonFor(new TemplateParameterIdValue(templateableId, index, constrainingTypeIds));
		}
	}

	/**
	 * List of template parameters; 0 index at least index ... up to most nested
	 */
	private static /*@LazyNonNull*/ List<@NonNull TemplateParameterIdImpl> templateParameterNormalizedIds = null;

	/**
	 * @since 7.0
	 */
	public static synchronized @NonNull TemplateParameterId getNormalizedTemplateParameterId(int index, @NonNull TypeId @NonNull [] constrainingTypeIds) {
		assert index >= 0;
		if (templateParameterNormalizedIds == null) {
			templateParameterNormalizedIds = new ArrayList<>(10);
		}
		int hashCode = IdHash.createConstrainedHash(null, index, constrainingTypeIds);
		for (@NonNull TemplateParameterIdImpl templateParameterId : templateParameterNormalizedIds) {
			if ((hashCode == templateParameterId.hashCode()) && templateParameterId.equals(null, index, constrainingTypeIds)) {
				return templateParameterId;
			}
		}
		TemplateParameterIdImpl templateParameterId = new TemplateParameterIdImpl(hashCode, null, index, constrainingTypeIds);
		templateParameterNormalizedIds.add(templateParameterId);
		return templateParameterId;
	}

	private final int hashCode;
	private final @Nullable TemplateableId templateableId;
	private final int index;
	private  @NonNull TypeId @NonNull [] constrainingTypeIds;
	private final @NonNull String name;

	/**
	 * @since 1.18
	 */
	private TemplateParameterIdImpl(int hashCode, @Nullable TemplateableId templateableId, int index, @NonNull TypeId @NonNull [] constrainingTypeIds) {
		//		System.out.println("create " + ClassUtil.debugFullName(this));
		this.hashCode = hashCode;
		this.templateableId = templateableId;
		this.index = index;
		this.constrainingTypeIds = constrainingTypeIds;
		StringBuilder s = new StringBuilder();
		s.append("$" + Integer.toString(index));
		for (@NonNull TypeId constrainingTypeId : constrainingTypeIds) {
			s.append("_");
			s.append(constrainingTypeId);
		}
		this.name = s.toString();
	}

	@Override
	public <R> R accept(@NonNull IdVisitor<R> visitor) {
		return visitor.visitTemplateParameterId(this);
	}

	/**
	 * @since 7.0
	 */
	public boolean equals(@Nullable TemplateableId thatTemplateableId, int thatIndex, @NonNull TypeId @NonNull [] thoseConstrainingTypeIds) {
		if (this.index != thatIndex) {
			return false;
		}
		if (this.templateableId != thatTemplateableId) {
			return false;
		}
		int iMax = this.constrainingTypeIds.length;
		if (iMax != thoseConstrainingTypeIds.length) {
			return false;
		}
		for (int i = 0; i < iMax; i++) {
			if (this.constrainingTypeIds[i] != thoseConstrainingTypeIds[i]) {
				return false;
			}
		}
		return true;
	}

	@Override
	public @NonNull TypeId @NonNull [] getConstrainingTypeIds() {
		return constrainingTypeIds;
	}

	@Override
	public @NonNull String getDisplayName() {
		return name;
	}

	/**
	 * @since 1.18
	 */
	@Override
	public @Nullable TemplateableId getTemplateableId() {
		return templateableId;
	}

	@Override
	public int getIndex() {
		return index;
	}

	@Override
	public @Nullable String getLiteralName() {
		if (this == TypeId.T_1) {
			return "T_1";
		}
		else if (this == TypeId.T_2) {
			return "T_2";
		}
		else if (this == TypeId.T_3) {
			return "T_3";
		}
		else {
			return null;
		}
	}

	@Override
	public @NonNull String getName() {
		return name;
	}

//	@Override
//	public @NonNull OperationId getOperationId(int templateParameters, @NonNull String name, @NonNull ParametersId parametersId) {
//		throw new UnsupportedOperationException();
//	}

	/**
	 * @since 7.0
	 */
	@Override
	public @NonNull OperationId getOperationId(@NonNull ExtraParameters extraParameters, @NonNull String name, @NonNull ParametersId parametersId) {
		throw new UnsupportedOperationException();
	}

	@Override
	public @NonNull PropertyId getPropertyId(@NonNull String name) {
		throw new UnsupportedOperationException();
	}

	@Override
	public int getTemplateParameters() {
		throw new UnsupportedOperationException();
	}

	@Override
	public final int hashCode() {
		return hashCode;
	}

	/**
	 * @since 1.7
	 */
	@Override
	public boolean isTemplated() {
		return true;
	}

	@Override
	public @NonNull ElementId specialize(@NonNull BindingsId templateBindings) {
		ElementId elementId = templateBindings.getElementId(index);
		assert elementId != null;
		return elementId;
	}

	@Override
	public @NonNull String toString() {
	//	if (templateableId != null) {
	//		return templateableId.getDisplayName() + "::" + getDisplayName();
	//	}
	//	else {
			return getDisplayName();
	//	}
	}
}