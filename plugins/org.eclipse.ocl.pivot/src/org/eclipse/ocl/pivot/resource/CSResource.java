/*******************************************************************************
 * Copyright (c) 2012, 2025 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.resource;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.internal.resource.ASResourceFactory;
import org.eclipse.ocl.pivot.internal.resource.ICS2AS;
import org.eclipse.ocl.pivot.internal.utilities.PivotUtilInternal;
import org.eclipse.ocl.pivot.utilities.EnvironmentFactory;
import org.eclipse.ocl.pivot.utilities.ParserContext;

/**
 * CSResource defines the Xtext-independent extended interface for a Concrete Syntax resource.
 * The derived BaseCSResource provides a richer interface with Xtext parsing dependencies.
 */
public interface CSResource extends Resource
{
	/**
	 * Dispose of this CSResource and its conversion facilities. This frees up resources after conversion to AS but loses the
	 * required source visibility for debugging.
	 *
	 * @since 7.0
	 */
	default void dispose() {}

	/**
	 * Return the ASResourceFactory corresponding to this CS Resource.
	 */
	@NonNull ASResourceFactory getASResourceFactory();

	/**
	 * Return the CS to AS conversion manager for use with environmentFactory.
	 *
	 * @since 7.0
	 */
	default @NonNull ICS2AS getCS2AS(@NonNull EnvironmentFactory environmentFactory) {
		throw new UnsupportedOperationException();
	}

	/**
	 * @since 1.15
	 */
	default @NonNull EnvironmentFactory getEnvironmentFactory() {
		return PivotUtilInternal.getEnvironmentFactory(getResourceSet());
	}

	@Deprecated /* @deprecated only for BaseCSResource */
	@NonNull ParserContext getParserContext();

	/**
	 * Return true if this CSResource is derived from an ASResource.
	 * @since 7.0
	 */
	@Deprecated /* @deprecated only for BaseCSResource */
	default boolean isDerived() {
		return false;
	}

	/**
	 * @since 7.0
	 */
	default ASResource reloadIn(@NonNull EnvironmentFactory environmentFactory) {			// XXX
		throw new UnsupportedOperationException();
	}

	/**
	 * Set whether this CSResource is derived from an ASResource.
	 * @since 7.0
	 */
	@Deprecated /* @deprecated only for BaseCSResource */
	default void setDerived(boolean isDerived) {}

	@Deprecated /* @deprecated only for BaseCSResource */
	void setParserContext(@Nullable ParserContext parserContext);

	@Deprecated /* @deprecated only for BaseCSResource */
	void update(int index, int length, String newString);

	void updateFrom(@NonNull ASResource asResource, @NonNull EnvironmentFactory environmentFactory);
}
