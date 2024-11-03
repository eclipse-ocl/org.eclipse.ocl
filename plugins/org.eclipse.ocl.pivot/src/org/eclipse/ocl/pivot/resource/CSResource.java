/*******************************************************************************
 * Copyright (c) 2012, 2021 Willink Transformations and others.
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
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal;
import org.eclipse.ocl.pivot.utilities.EnvironmentFactory;
import org.eclipse.ocl.pivot.utilities.ParserContext;
import org.eclipse.ocl.pivot.utilities.SemanticException;

/**
 * CSResource defines the Xtext-independent extended interface for a Concrete Syntax resource.
 * The derived BaseCSResource provides a richer interface with Xtext parsing dependencies.
 */
public interface CSResource extends Resource
{
	/**
	 * @since 1.1
	 */
	@Deprecated /* @deprecated obsolete - folded into super interface */
	public interface CSResourceExtension extends CSResource {}

	/**
	 * @since 1.3
	 */
	@Deprecated /* @deprecated obsolete - folded into super interface */
	public interface CSResourceExtension2 extends CSResourceExtension {}

	/**
	 * Create the CS2AS converter for the asResource conversions using environmentFactory.
	 * @since 1.23
	 */
	default @NonNull ICS2AS createCS2AS(@NonNull EnvironmentFactoryInternal environmentFactory, @NonNull ASResource asResource) {
		throw new UnsupportedOperationException();		// XXX
	}

	/**
	 * Dispose of this CSResource and its conversion facilities. This frees up resources after conversion to AS but loses the
	 * required source visibility for debugging.
	 * @since 1.23
	 */
	default void dispose() {}

	/**
	 * Return the Abstract Syntax representation of this Concrete Syntax resource.
	 */
	@Deprecated /* @deprecated use getCS2AS(getEnvironmentFactory()).getASResource() since EnvironmentFactory usually known */
	@NonNull ASResource getASResource();

	/**
	 * Return the ASResourceFactory corresponding to this CS Resource.
	 */
	@NonNull ASResourceFactory getASResourceFactory();

	/**
	 * Return the CS to AS conversion manager for use with environmentFactory.
	 *
	 * @since 1.23
	 */
	default @NonNull ICS2AS getCS2AS(@NonNull EnvironmentFactory environmentFactory) {
		throw new UnsupportedOperationException();		// XXX
	}

	/**
	 * @since 1.15
	 */
	@Deprecated /* @deprecated use PivotUtilInternal.getEnvironmentFactory */
	default @NonNull EnvironmentFactory getEnvironmentFactory() {
		throw new UnsupportedOperationException();
	}

	@Deprecated /* @deprecated only for BaseCSResource */
	@NonNull ParserContext getParserContext();

	/**
	 * Return the map of known projects.
	 */
	@Deprecated /* @deprecated no longer used - use getEnvironmentFactory().getProjectManager() */
	@NonNull ProjectManager getProjectManager();

	@Deprecated /* @deprecated only for BaseCSResource */
	void setParserContext(@Nullable ParserContext parserContext);

	/**
	 * Set the map of known projects.
	 */
	@Deprecated /* @deprecated ProjectManager is inferred from implicit/explicit setParserContext() */
	void setProjectManager(@Nullable ProjectManager projectManager);

	@Deprecated /* @deprecated only for BaseCSResource */
	void update(int index, int length, String newString);

	void updateFrom(@NonNull ASResource asResource, @NonNull EnvironmentFactory environmentFactory);

	/**
	 * @since 1.23
	 */
	default ASResource reloadIn(@NonNull EnvironmentFactory environmentFactory) throws SemanticException {			// XXX
		throw new UnsupportedOperationException();
	}
}
