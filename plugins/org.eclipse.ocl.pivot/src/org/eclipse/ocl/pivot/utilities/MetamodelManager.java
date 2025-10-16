/*******************************************************************************
 * Copyright (c) 2010, 2022 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *	E.D.Willink - initial API and implementation
 *	E.D.Willink (CEA LIST) - Bug 399252
 *******************************************************************************/
package org.eclipse.ocl.pivot.utilities;

import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.CompleteModel;
import org.eclipse.ocl.pivot.CompleteStandardLibrary;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.ElementExtension;
import org.eclipse.ocl.pivot.Model;
import org.eclipse.ocl.pivot.Stereotype;
import org.eclipse.ocl.pivot.internal.manager.Orphanage;
import org.eclipse.ocl.pivot.internal.utilities.External2AS;
import org.eclipse.ocl.pivot.resource.ASResource;

/**
 * @since 7.0
 */
public interface MetamodelManager
{
	/**
	 * @since 7.0
	 */
	void addExternal2AS(@NonNull External2AS external2as);

	/**
	 * @since 7.0
	 */
	void addGenModel(@NonNull GenModel genModel);

	/**
	 * @since 7.0
	 */
	void addGenPackage(@NonNull GenPackage genPackage);

	/**
	 * @since 7.0
	 */
	@NonNull Orphanage createOrphanage();

	/**
	 * @since 7.0
	 */
	void dispose();

	@Nullable <T extends Element> T getASOfEcore(@NonNull Class<T> pivotClass, @Nullable EObject eObject);

	@NonNull ResourceSet getASResourceSet();

	/**
	 * @since 7.0
	 */
	@NonNull CompleteModel getCompleteModel();

	/**
	 * @since 7.0
	 */
	@Nullable External2AS getES2AS(@NonNull Resource esResource);

	@Nullable <T extends EObject> T getEcoreOfPivot(@NonNull Class<T> ecoreClass, @NonNull Element element);

	/**
	 * @since 7.0
	 */
	@NonNull ElementExtension getElementExtension(@NonNull Element asStereotypedElement, @NonNull Stereotype asStereotype);

	/**
	 * @since 7.0
	 */
	@NonNull EnvironmentFactory getEnvironmentFactory();

	/**
	 * @since 7.0
	 */
	@Nullable GenPackage getGenPackage(@NonNull String nsURI);

	/**
	 * @since 7.0
	 */
	@NonNull ASResource getResource(@NonNull URI uri, @Nullable String contentType);

	/**
	 * @since 7.0
	 */
	@NonNull CompleteStandardLibrary getStandardLibrary();

	/**
	 * @since 7.0
	 */
	void installResource(@NonNull Resource asResource);

	/**
	 * @since 7.0
	 */
	void installRoot(@NonNull Model pivotModel);

	/**
	 * @since 7.0
	 */
	@Nullable Element loadResource(@NonNull URI uri, String alias, @Nullable ResourceSet resourceSet) throws ParserException;

	/**
	 * @since 7.0
	 */
	void removeASResource(@NonNull ASResource asResource);

	/**
	 * @since 7.0
	 */
	void removeExternalResource(@NonNull External2AS external2as);

	/**
	 * @since 7.0
	 */
	void removeExternalResource(@NonNull Resource esResource);
}
