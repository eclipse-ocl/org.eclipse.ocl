/*******************************************************************************
 * Copyright (c) 2017 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.library;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.CallExp;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.internal.manager.TemplateParameterSubstitutionVisitor;
import org.eclipse.ocl.pivot.utilities.EnvironmentFactory;

/**
 * @since 1.12
 */
public interface LibraryIterationOrOperation extends LibraryFeature
{
	boolean resolveReturnNullity(@NonNull EnvironmentFactory context, @NonNull CallExp asCallExp, boolean returnIsRequired);
	@Nullable Type resolveReturnType(@NonNull EnvironmentFactory environmentFactory, @NonNull CallExp callExp, @Nullable Type returnType);
	void resolveUnmodeledTemplateParameterSubstitutions(@NonNull TemplateParameterSubstitutionVisitor templateParameterSubstitutions, @NonNull CallExp callExp);
}
