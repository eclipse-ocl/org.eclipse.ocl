/*******************************************************************************
 * Copyright (c) 2023 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.flat;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.ids.TemplateParameterId;

/**
 * A PartialFlatClass identifies a Pivot Class as the client for which caches are provided.
 * <br>
 * This calls is not yet used by itself since current usage always provides a EClassifier
 * for the more refined EcoreFlatClass.
 */
public class GenericPartialFlatClass extends PartialFlatClass
{
	protected final @NonNull TemplateParameterId @Nullable [] templateParameterIds;

	protected GenericPartialFlatClass(@NonNull FlatModel flatModel, org.eclipse.ocl.pivot.@NonNull Class asClass) {
		super(flatModel, asClass);
		this.templateParameterIds = computeTemplateParameterIds(asClass);
		assert (asClass.getOwnedSignature() != null) && (asClass.getOwnedSignature().getOwnedParameters().size() > 0) && (asClass.getGeneric() != null) && (asClass.getOwnedBindings().size() == 0);
	}

	@Override
	public @NonNull TemplateParameterId @Nullable [] getTemplateParameterIds() {
		return templateParameterIds;
	}
}
