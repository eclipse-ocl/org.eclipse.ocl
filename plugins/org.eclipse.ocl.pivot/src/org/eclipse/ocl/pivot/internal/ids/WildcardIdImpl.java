/*******************************************************************************
 * Copyright (c) 2022 Willink Transformations and others.
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
import org.eclipse.ocl.pivot.ids.IdHash;
import org.eclipse.ocl.pivot.ids.IdManager;
import org.eclipse.ocl.pivot.ids.IdVisitor;
import org.eclipse.ocl.pivot.ids.SingletonScope.AbstractKeyAndValue;
import org.eclipse.ocl.pivot.ids.TemplateParameterId;
import org.eclipse.ocl.pivot.ids.WildcardId;
import org.eclipse.ocl.pivot.utilities.PivotConstants;

/**
 * @since 1.18
 */
public class WildcardIdImpl extends AbstractTypeId/*UnscopedId*/ implements WildcardId
{

	private static class WildcardIdValue extends AbstractKeyAndValue<@NonNull WildcardId>
	{
		private final @NonNull IdManager idManager;
		private final @NonNull TemplateParameterId templateParameterId;

		private WildcardIdValue(@NonNull IdManager idManager, @NonNull TemplateParameterId templateParameterId) {
			super(computeHashCode(templateParameterId));
			this.idManager = idManager;
			this.templateParameterId = templateParameterId;
		}

		@Override
		public @NonNull WildcardId createSingleton() {
			return new WildcardIdImpl(idManager, templateParameterId);
		}

		@Override
		public boolean equals(@Nullable Object that) {
			if (that instanceof WildcardIdImpl) {
				WildcardIdImpl singleton = (WildcardIdImpl)that;
				return singleton.templateParameterId == templateParameterId;
			}
			else {
				return false;
			}
		}
	}

	/**
	 * @since 1.18
	 */
	public static class WildcardIdSingletonScope extends AbstractSingletonScope<@NonNull WildcardId, @NonNull TemplateParameterId>
	{
		public @NonNull WildcardId getSingleton(@NonNull IdManager idManager, @NonNull TemplateParameterId value) {
			return getSingletonFor(new WildcardIdValue(idManager, value));
		}
	}

	private static int computeHashCode(@NonNull TemplateParameterId templateParameterId) {
		return IdHash.createChildHash(templateParameterId, PivotConstants.WILDCARD_NAME);
	}

	private final int hashCode;
	private final @NonNull TemplateParameterId templateParameterId;

	public WildcardIdImpl(@NonNull IdManager idManager, @NonNull TemplateParameterId templateParameterId) {
		this.hashCode = computeHashCode(templateParameterId);
		this.templateParameterId = templateParameterId;
	}

	@Override
	public <R> R accept(@NonNull IdVisitor<R> visitor) {
		return visitor.visitWildcardId(this);
	}

	@Override
	public @NonNull String getDisplayName() {
		return templateParameterId.getDisplayName() + "::" +  PivotConstants.WILDCARD_NAME;//name;
	}

	@Override
	public @NonNull TemplateParameterId getTemplateParameterId() {
		return templateParameterId;
	}

	@Override
	public int hashCode() {
		return hashCode;
	}
}