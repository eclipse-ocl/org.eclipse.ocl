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
package org.eclipse.ocl.xtext.base.utilities;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.internal.context.AbstractParserContext;
import org.eclipse.ocl.pivot.internal.scoping.EnvironmentView;
import org.eclipse.ocl.pivot.internal.scoping.ScopeView;
import org.eclipse.ocl.pivot.resource.ASResource;
import org.eclipse.ocl.pivot.utilities.EnvironmentFactory;
import org.eclipse.ocl.xtext.base.cs2as.CS2AS;

/**
 * AbstractExtendedParserContext provides the default implementation of the ExtendedParserContext API that all clients
 * should extend.
 *
 * @since 1.3
 */
public abstract class AbstractExtendedParserContext extends AbstractParserContext implements ExtendedParserContext
{
	protected AbstractExtendedParserContext(@NonNull EnvironmentFactory environmentFactory, @Nullable URI uri) {
		super(environmentFactory, uri);
	}

	@Override
	public @Nullable ScopeView computeLookup(@NonNull EObject target, @NonNull EnvironmentView environmentView, @NonNull ScopeView scopeView) {
		return null;
	}

	@Override
	public @Nullable CS2AS createCS2AS(@NonNull BaseCSResource csResource, @NonNull ASResource asResource) {
		return null;
	}
}
