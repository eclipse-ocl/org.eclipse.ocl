/*******************************************************************************
 * Copyright (c) 2025 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *	 E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.ids;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.utilities.Nameable;

/**
 * A CompletePackageId provides a unique flat semantic identifier for a complete package.
 * <p>
 * A CompletePackageId is functionally identical to a String. It avoids API confusion between APIs looking up
 * CompletePackages by CompletePackageId and Packages within CompletePackages by (package) name.
 * <p>
 * Logically merged packages may have different NsURIs but the same PackageId.
 *
 * @see NestedPackageId
 * @see NsURIPackageId
 * @see RootPackageId
 *
 * @since 7.0
 */
public interface CompletePackageId extends ElementId, Nameable
{
	@Override
	@NonNull String getName();
}
