/**
 * Copyright (c) 2020 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 */
package org.eclipse.ocl.xtext.base.cs2text.idioms;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.xtext.base.cs2text.idioms.impl.IdiomsPackageImpl;

public abstract class AbstractIdiomsProvider implements IdiomsProvider
{
	private static @NonNull IdiomModel IDIOM_MODEL = getIdiomModel(URI.createPlatformResourceURI("org.eclipse.ocl.xtext.base/model/BaseIdioms.xmi", true));

	private static @NonNull IdiomModel getIdiomModel(@NonNull URI idiomURI) {
		ResourceSet resourceSet = new ResourceSetImpl();
		IdiomsPackageImpl.eINSTANCE.getClass();
		Resource resource = resourceSet.getResource(idiomURI, true);
		return (IdiomModel) resource.getContents().get(0);
	}

	@Override
	public @NonNull Iterable<@NonNull Idiom> getIdioms() {
		return IDIOM_MODEL.getOwnedIdioms();
	}

}
