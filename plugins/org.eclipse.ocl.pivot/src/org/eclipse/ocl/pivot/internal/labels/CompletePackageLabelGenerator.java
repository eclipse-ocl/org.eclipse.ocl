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
import org.eclipse.ocl.pivot.CompletePackage;
import org.eclipse.ocl.pivot.labels.AbstractLabelGenerator;
import org.eclipse.ocl.pivot.utilities.PivotConstants;

public final class CompletePackageLabelGenerator extends AbstractLabelGenerator<CompletePackage>
{
	public static void initialize(@NonNull Registry registry) {
		registry.install(CompletePackage.class, new CompletePackageLabelGenerator());
	}

	public CompletePackageLabelGenerator() {
		super(CompletePackage.class);
	}

	@Override
	public void buildLabelFor(@NonNull Builder labelBuilder, @NonNull CompletePackage object) {
		if (PivotConstants.PRIMITIVES_URI != object.getURI()) {
			String name = object.getName();
			labelBuilder.appendObject(name);
		}
	}
}