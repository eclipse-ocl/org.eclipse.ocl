/**
 * Copyright (c) 2010, 2022 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 */
package org.eclipse.ocl.pivot;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.internal.complete.PartialPackages;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Orphanage</b></em>'.
 * <!-- end-user-doc -->
 *
 *
 * @see org.eclipse.ocl.pivot.PivotPackage#getOrphanage()
 * @generated
 */
public interface Orphanage extends org.eclipse.ocl.pivot.Package
{
	void addOrphanClass(org.eclipse.ocl.pivot.@NonNull Class orphanClass);
	void addPackageListener(@NonNull PartialPackages partialPackages);
	void dispose();
	void removePackageListener(@NonNull PartialPackages partialPackages);
} // Orphanage
