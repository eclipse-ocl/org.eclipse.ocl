/*******************************************************************************
 * Copyright (c) 2017, 2018 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.internal.labels;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.CompleteClass;
import org.eclipse.ocl.pivot.labels.AbstractLabelGenerator;

/**
 * @since 7.0
 */
public final class CompleteClassLabelGenerator extends AbstractLabelGenerator<CompleteClass>
{
	public static void initialize(@NonNull Registry registry) {
		registry.install(CompleteClass.class, new CompleteClassLabelGenerator());
	}

	public CompleteClassLabelGenerator() {
		super(CompleteClass.class);
	}

	@Override
	public void buildLabelFor(@NonNull Builder labelBuilder, @NonNull CompleteClass object) {
		labelBuilder.appendObject(object.getName());
	}
}