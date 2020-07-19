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
package org.eclipse.ocl.xtext.base.cs2text.solutions;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.xtext.base.cs2text.user.UserSlotsAnalysis;

public interface CardinalitySolution
{
	@NonNull CardinalitySolution addSolution(@NonNull CardinalitySolution solution);
	@Nullable Integer getIntegerSolution(@NonNull UserSlotsAnalysis slotsAnalysis);
	boolean isRuntime();
//	@NonNull Integer solve(@NonNull Map<@NonNull EStructuralFeature, @NonNull Object> eFeature2contentAnalysis);
	void toString(@NonNull StringBuilder s, int depth);
}