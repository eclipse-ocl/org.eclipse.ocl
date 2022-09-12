/*******************************************************************************
 * Copyright (c) 2022 Willink Transformation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.codegen.naming;

/**
 * A NestedNameManager provides suggestions for names and maintains caches of used names so that model elements are consistently
 * named without collisions at some node in the name nesting hierarchy..
 */
public interface ClassableNameManager extends NameManager
{
}
