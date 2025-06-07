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
package org.eclipse.ocl.pivot.manager;

import java.util.List;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.LambdaType;
import org.eclipse.ocl.pivot.PivotFactory;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.TypedElement;
import org.eclipse.ocl.pivot.values.TemplateParameterSubstitutions;

/**
 * LambdaTypeManager encapsulates the knowledge about known lambda types.
 * @since 7.0
 */
public interface LambdaTypeManager
{
	/**
	 * Return a concrete TypedElement instance suitable for parameterizing a shared LambdaType access/creation.
	 */
	public static @NonNull TypedElement createCandidateLambdaParameter(@NonNull String name, @NonNull Type type, boolean isRequired) {
		TypedElement typedElement = PivotFactory.eINSTANCE.createParameter();
		typedElement.setName(name);
		typedElement.setType(type);
		typedElement.setIsRequired(isRequired);
		return typedElement;
	}

	boolean conformsToLambdaType(@NonNull LambdaType actualType, @Nullable TemplateParameterSubstitutions actualSubstitutions,
			@NonNull LambdaType requiredType, @Nullable TemplateParameterSubstitutions requiredSubstitutions, boolean enforceNullity);

	void dispose();

	@NonNull LambdaType getLambdaType(@NonNull TypedElement context, @NonNull List<@NonNull ? extends TypedElement> parameters, @NonNull TypedElement result, @Nullable TemplateParameterSubstitutions bindings);
}