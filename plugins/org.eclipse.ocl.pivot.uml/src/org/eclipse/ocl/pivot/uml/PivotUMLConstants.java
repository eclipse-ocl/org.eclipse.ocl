/*******************************************************************************
 * Copyright (c) 2025 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.uml;

import org.eclipse.emf.common.util.URI;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.ids.IdManager;
import org.eclipse.ocl.pivot.ids.RootPackageId;
import org.eclipse.ocl.pivot.utilities.PivotConstants;

/**
 * @since 7.0
 */
public interface PivotUMLConstants extends PivotConstants
{
	/**
	 * The Package name of the shared types metamodel.
	 */
	static final @NonNull String TYPES_METAMODEL_NAME = "$types$";	// FIXME Use extension point

	/**
	 * The Package name of the shared uml metamodel.
	 */
	static final @NonNull String UML_METAMODEL_NAME = "$uml$";	// FIXME Use extension point

	static final @NonNull RootPackageId UML_METAMODEL = IdManager.getRootPackageId(PivotUMLConstants.UML_METAMODEL_NAME);
	static final @NonNull RootPackageId TYPES_METAMODEL = IdManager.getRootPackageId(PivotUMLConstants.TYPES_METAMODEL_NAME);

	/**
	 * URI used to identify that a Package contributes to the OCL AS.
	 * Fragments may be appended tio identify a particular aspect of the AS.
	 * @since 7.0
	 */
	static final @NonNull URI UML_SEMANTICS = URI.createURI(UML_METAMODEL_NAME/*"http://www.eclipse.org/OCL/AS"*/);			// XXX

	/**
	 * Sub URI used to identify that a Package contributes to the OCL AS Standard Library.
	 * @since 7.0
	 */
	static final @NonNull URI UML_TYPES_SEMANTICS = UML_SEMANTICS.appendFragment("Types");			// XXX

	/**
	 * Sub URI used to identify that a Package contributes to the OCL AS Metamodel.
	 * @since 7.0
	 */
	static final @NonNull URI UML_METAMODEL_SEMANTICS = UML_SEMANTICS.appendFragment("Metamodel");			// XXX
}
