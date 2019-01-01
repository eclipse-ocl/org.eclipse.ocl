/*******************************************************************************
 * Copyright (c) 2013, 2019 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.codegen.java;

/**
 * Manage the mapping from long fully qualified class names to the short class names that may be used once an import has been provided.
 *
 * (This operates on names rather than classes since classes are not always available when using genModel.)
 */
public abstract class AbstractImportNameManager implements ImportNameManager
{
}
