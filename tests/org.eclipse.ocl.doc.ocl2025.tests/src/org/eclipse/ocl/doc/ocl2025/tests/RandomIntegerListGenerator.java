/*******************************************************************************
 * Copyright (c) 2025 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.doc.ocl2025.tests;

import java.util.Random;

import org.eclipse.jdt.annotation.NonNull;

public class RandomIntegerListGenerator extends AbstractRandomListGenerator<@NonNull Integer>
{
	@Override
	protected @NonNull Integer createRandomElement(@NonNull Random random) {
		return random.nextInt();
	}
}
