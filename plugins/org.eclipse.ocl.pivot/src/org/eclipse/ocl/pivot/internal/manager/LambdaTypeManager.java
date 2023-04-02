/*******************************************************************************
 * Copyright (c) 2011, 2018 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.internal.manager;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.LambdaType;
import org.eclipse.ocl.pivot.StandardLibrary;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.internal.complete.CompleteEnvironmentInternal;
import org.eclipse.ocl.pivot.values.TemplateParameterSubstitutions;

/**
 * LambdaTypeManager encapsulates the knowledge about known lambda types.
 */
public class LambdaTypeManager extends AbstractLambdaTypeManager
{
	protected final @NonNull CompleteEnvironmentInternal completeEnvironment;

	public LambdaTypeManager(@NonNull CompleteEnvironmentInternal completeEnvironment) {
		super(false);
		this.completeEnvironment = completeEnvironment;
	}

	public @NonNull LambdaType getLambdaType(@NonNull String typeName, @NonNull Type contextType, @NonNull List<@NonNull ? extends Type> parameterTypes, @NonNull Type resultType,
			@Nullable TemplateParameterSubstitutions bindings) {
		if (bindings == null) {
			return getLambdaType(typeName, contextType, parameterTypes, resultType);
		}
		else {
			Type specializedContextType = completeEnvironment.getSpecializedType(contextType, bindings);
			List<@NonNull Type> specializedParameterTypes = new ArrayList<>();
			for (@NonNull Type parameterType : parameterTypes) {
				specializedParameterTypes.add(completeEnvironment.getSpecializedType(parameterType, bindings));
			}
			Type specializedResultType = completeEnvironment.getSpecializedType(resultType, bindings);
			return getLambdaType(typeName, specializedContextType, specializedParameterTypes, specializedResultType);
		}
	}

	@Override
	protected @NonNull StandardLibrary getStandardLibrary() {
		return completeEnvironment.getOwnedStandardLibrary();
	}
}