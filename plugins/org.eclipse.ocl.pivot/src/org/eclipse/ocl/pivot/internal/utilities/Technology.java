/*******************************************************************************
 * Copyright (c) 2015, 2019 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.internal.utilities;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.CompleteModel;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.ids.IdResolver;
import org.eclipse.ocl.pivot.ids.PackageId;
import org.eclipse.ocl.pivot.library.LibraryProperty;
import org.eclipse.ocl.pivot.utilities.EnvironmentFactory;
import org.eclipse.ocl.pivot.utilities.ParserException;

/**
 * Technology instances encapsulate behaviour that varies according to the metamodel technologies in use.
 * At present this means just-Ecore supported by an EcoreTechnology or Ecore-and-UML supported
 * by a UMLEcoreTechnology.
 */
public interface Technology
{
	/**
	 * @since 7.0
	 */
	@NonNull LibraryProperty createBasePropertyImplementation(@NonNull EnvironmentFactory environmentFactory, @NonNull Property property);

	// See Bug 458394 for the need for the asNavigationExp argument.
	/**
	 * @since 7.0
	 */
	@NonNull LibraryProperty createExplicitNavigationPropertyImplementation(@NonNull EnvironmentFactory environmentFactory,
			@Nullable Element asNavigationExp, @Nullable Object sourceValue, @NonNull Property property);

	/**
	 * @since 7.0
	 */
	@NonNull LibraryProperty createExtensionPropertyImplementation(@NonNull EnvironmentFactory environmentFactory, @NonNull Property property);

	/**
	 * @since 7.0
	 */
	@NonNull IdResolver createIdResolver(@NonNull EnvironmentFactory environmentFactory);

	/**
	 * @since 7.0
	 */
	@NonNull LibraryProperty createStereotypePropertyImplementation(@NonNull EnvironmentFactory environmentFactory, @NonNull Property property);

	String getExtensionName(@NonNull Element asStereotypedElement);

	/**
	 * @since 7.0
	 */
	@Deprecated /* ?? not used */
	PackageId getMetamodelId(@NonNull EnvironmentFactory environmentFactory, @NonNull EPackage eObject2);

	/**
	 * @since 7.0
	 */
	@NonNull PackageId getMetapackageId(@NonNull EnvironmentFactory environmentFactory, org.eclipse.ocl.pivot.@NonNull Package asPackage);

	@Nullable String getOriginalName(@NonNull ENamedElement eNamedElement);

	/**
	 * @since 7.0
	 */
	@Nullable Element getParseableElement(@NonNull EnvironmentFactory environmentFactory, @NonNull EObject eObject) throws ParserException;

	/**
	 * @since 7.0
	 */
	boolean isStereotype(@NonNull EnvironmentFactory environmentFactory, @NonNull EClass eClass);

	boolean isValidatable(@NonNull EClass eClass);

	/**
	 * @since 7.0
	 */
	void registerMetaPackages(@NonNull CompleteModel completeModel);
}
