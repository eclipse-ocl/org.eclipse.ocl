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
package org.eclipse.ocl.xtext.base.cs2text;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;

/**
 * A RootUserElementAnalysis provides the working context to assist in the determination of the Xtext grammar rule
 * that can produce and assign a user model element that has no container.
 */
public class UserRootElementAnalysis extends UserAbstractElementAnalysis
{
	public UserRootElementAnalysis(@NonNull UserModelAnalysis modelAnalysis, @NonNull EObject element) {
		super(modelAnalysis, element);
		assert element.eContainer() == null;
	}

	@Override
	public @Nullable UserAbstractElementAnalysis getContainingElementAnalysis() {
		return null;
	}

	@Override
	protected boolean hasCompatibleContainmentHierarchy() {
		return true;
	}
}