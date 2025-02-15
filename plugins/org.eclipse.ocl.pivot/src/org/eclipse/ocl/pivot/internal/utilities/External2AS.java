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
package org.eclipse.ocl.pivot.internal.utilities;

import java.util.Map;

import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.Model;
import org.eclipse.ocl.pivot.internal.ecore.es2as.Ecore2AS;
import org.eclipse.ocl.pivot.utilities.ParserException;

/**
 * External2AS defines the common behaviour of an external (e.g. Ecore or UML) system to AS conversion.
 */
public interface External2AS
{
	/**
	 * @since 1.23
	 */
	public static @Nullable External2AS findAdapter(@NonNull Resource resource, @NonNull EnvironmentFactoryInternal environmentFactory) {		// XXX not an Adapter
		return environmentFactory.getMetamodelManager().getES2AS(resource);
	}

	/**
	 * @since 1.23
	 */
	public static @NonNull External2AS getAdapter(@NonNull Resource resource, @NonNull EnvironmentFactoryInternal environmentFactory) {
		External2AS es2as = External2AS.findAdapter(resource, environmentFactory);
		if (es2as == null) {
			es2as = Ecore2AS.getAdapter(resource, environmentFactory);
		}
		return es2as;
	}

	void dispose();

	/**
	 * Return the AS model that results from this conversion.
	 *
	 * FIXME Only the asResource is a actually needed, and only by UML support.
	 */
	@NonNull Model getASModel() throws ParserException;

	/**
	 * Return the AS element of type requiredClass corresponding to the external eOBJect, or null if none available.
	 */
	@Nullable <T extends Element> T getCreated(@NonNull Class<T> requiredClass, @NonNull EObject eObject);

	/**
	 * Return the map of all external objects to all AS elements.
	 */
	@Nullable Map<@NonNull Notifier, @NonNull Element> getCreatedMap();

	/**
	 * Return the external resource.
	 */
	@NonNull Resource getResource();

	/**
	 * Return the URI of the external resource.
	 */
	@NonNull URI getURI();

	/**
	 * @since 1.23
	 */
	default void setEcoreURI(@NonNull URI uri) {}
}