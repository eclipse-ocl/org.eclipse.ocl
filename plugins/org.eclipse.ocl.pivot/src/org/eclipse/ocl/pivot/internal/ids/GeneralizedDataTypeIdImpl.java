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

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.ids.AbstractSingletonScope;
import org.eclipse.ocl.pivot.ids.BindingsId;
import org.eclipse.ocl.pivot.ids.DataTypeId;
import org.eclipse.ocl.pivot.ids.ElementId;
import org.eclipse.ocl.pivot.ids.IdHash;
import org.eclipse.ocl.pivot.ids.IdVisitor;
import org.eclipse.ocl.pivot.ids.PackageId;
import org.eclipse.ocl.pivot.ids.SingletonScope.AbstractKeyAndValue;
import org.eclipse.ocl.pivot.ids.TemplateableTypeId;
import org.eclipse.ocl.pivot.ids.TypeId;

public class GeneralizedDataTypeIdImpl extends GeneralizedNestedTypeIdImpl implements DataTypeId
{
	private static class DataTypeIdValue extends AbstractKeyAndValue<@NonNull DataTypeId>
	{
		private @NonNull PackageId packageId;
		private @NonNull String name;
		private @NonNull ExtraParameters extraParameters;

		private DataTypeIdValue(@NonNull PackageId packageId, @NonNull String name, @NonNull ExtraParameters extraParameters) {
			super(computeHashCode(packageId, extraParameters, name));
			this.packageId = packageId;
			this.name = name;
			this.extraParameters = extraParameters;
		}

		@Override
		public @NonNull DataTypeId createSingleton() {
			return new GeneralizedDataTypeIdImpl(packageId, extraParameters, name);
		}

		@Override
		public boolean equals(@Nullable Object that) {
			if (that instanceof GeneralizedDataTypeIdImpl) {
				GeneralizedDataTypeIdImpl singleton = (GeneralizedDataTypeIdImpl)that;
				return name.equals(singleton.getName()) && (extraParameters == singleton.getExtraParameters());
			}
			else {
				return false;
			}
		}
	}

	/**
	 * @since 1.18
	 */
	public static class DataTypeIdSingletonScope extends AbstractSingletonScope<@NonNull DataTypeId, @NonNull DataTypeIdValue>
	{
		/**
		 * @since 7.0
		 */
		public @NonNull DataTypeId getSingleton(@NonNull PackageId packageId, @NonNull String name, @NonNull ExtraParameters extraParameters) {
			return getSingletonFor(new DataTypeIdValue(packageId, name, extraParameters));
		}
	}

	/**
	 * @since 1.18
	 */
	private static int computeHashCode(@NonNull ElementId parentId, @NonNull ExtraParameters extraParameters, @NonNull String name) {
		return IdHash.createChildHash(parentId, name) + 11 * extraParameters.getValue();
	}

	/**
	 * @since 7.0
	 */
	public GeneralizedDataTypeIdImpl(@NonNull PackageId parentId, @NonNull ExtraParameters extraParameters, @NonNull String name) {
		super(computeHashCode(parentId, extraParameters, name), parentId, extraParameters, name);
	}

	@Override
	public <R> R accept(@NonNull IdVisitor<R> visitor) {
		return visitor.visitDataTypeId(this);
	}

	@Override
	protected @NonNull TemplateableTypeId createSpecializedId(@NonNull BindingsId templateBindings) {
		return new SpecializedDataTypeIdImpl(this, templateBindings);
	}

	/**
	 * @since 7.0
	 */
	@Override
	public @NonNull String getMetaclassName() {
		return TypeId.DATA_TYPE_NAME;
	}
}