/*******************************************************************************
 * Copyright (c) 2010, 2022 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.internal.resource;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.Element;

/**
 * ASSaver ensures that all references to synthesized types are terminated by local copies of the synthesized types.
 * This rewrite of the original ASSaver uses variant PartialStandardLibraryImpl guaranteeing correct operation for all
 * references without requiring derivations with accurate overloading for all references.
 *
 * @since 7.0
 */
public interface ASSaver
{
	/**
	 * Return the localized variant of target. If target is an orphan, localize() should have created
	 * a local copy that is returned here. Else returns target.
	 */
	@NonNull Element getLocal(@NonNull Element target);

	@NonNull Resource getResource();

	/**
	 * Prepare a pivot resource for save by redirecting all type references to shared orphans to local copies of the orphans.
	 */
	void localize();

	void normalizeContents();
}