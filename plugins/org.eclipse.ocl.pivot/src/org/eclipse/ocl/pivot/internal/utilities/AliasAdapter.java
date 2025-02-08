/*******************************************************************************
 * Copyright (c) 2010, 2024 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.internal.utilities;

import org.eclipse.ocl.pivot.internal.ecore.Ecore2Moniker;

/**
 * An AliasAdapter extends a Resource to provide a set of aliases for elements,
 * typically packages, contained within that Resource. Use of an alias as the moniker
 * for a package simplifies the moniker and avoids prefix variation for monikers
 * computed for alternate domains.
 */
@Deprecated /* @deprecated localized for monikers in Ecore2Moniker */
public class AliasAdapter extends Ecore2Moniker.MonikerAliasAdapter
{
}