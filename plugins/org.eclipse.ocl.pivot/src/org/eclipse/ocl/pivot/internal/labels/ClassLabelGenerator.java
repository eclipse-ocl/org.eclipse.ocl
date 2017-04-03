/*******************************************************************************
 * Copyright (c) 2017 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.internal.labels;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.labels.AbstractLabelGenerator;

public final class ClassLabelGenerator extends AbstractLabelGenerator<Class<?>>
{
	public static void initialize(@NonNull Registry registry) {
		registry.install(Class.class, new ClassLabelGenerator());
	}

	@SuppressWarnings("unchecked")
	public ClassLabelGenerator() {
		super((Class<? extends Class<?>>) Class.class);
	}

	@Override
	public void buildLabelFor(@NonNull Builder labelBuilder, @NonNull Class<?> object) {
		labelBuilder.appendObject(object.getName());
	}
}