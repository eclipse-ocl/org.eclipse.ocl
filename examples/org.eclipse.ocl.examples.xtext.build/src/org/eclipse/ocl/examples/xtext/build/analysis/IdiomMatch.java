/*******************************************************************************
 * Copyright (c) 2020 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.xtext.build.analysis;

/**
 * An IdiomMatch is created after a first successful match of an idiom. It accumulates
 * subsequent matches for idioms with multiple subidioms.
 *
 * An Idiom such as {...} may be nested or cascaded to support {..{..]..} or {..}..{..}
 */
public interface IdiomMatch
{
}
