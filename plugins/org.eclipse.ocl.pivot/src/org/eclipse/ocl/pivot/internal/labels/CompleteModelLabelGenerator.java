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
import org.eclipse.ocl.pivot.CompleteModel;
import org.eclipse.ocl.pivot.labels.AbstractLabelGenerator;

public final class CompleteModelLabelGenerator extends AbstractLabelGenerator<CompleteModel>
{
	public static void initialize(@NonNull Registry registry) {
		registry.install(CompleteModel.class, new CompleteModelLabelGenerator());
	}

	public CompleteModelLabelGenerator() {
		super(CompleteModel.class);
	}

	@Override
	public void buildLabelFor(@NonNull Builder labelBuilder, @NonNull CompleteModel object) {
		String name = object.getName();
		if (name != null) {
			labelBuilder.appendObject(name);
		}
	}
}