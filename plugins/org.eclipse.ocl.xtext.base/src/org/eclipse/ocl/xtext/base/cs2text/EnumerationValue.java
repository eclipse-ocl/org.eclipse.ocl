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
package org.eclipse.ocl.xtext.base.cs2text;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.utilities.Nameable;

/**
 * An EnumerationValue defines zero/one/many values that enumerate a String EAttribute.
 */
public interface EnumerationValue extends Nameable
{
	@Override
	@NonNull String getName();
	boolean isElement(@NonNull String string);
	boolean isNull();
}