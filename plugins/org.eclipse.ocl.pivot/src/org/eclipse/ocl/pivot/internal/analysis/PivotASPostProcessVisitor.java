/*******************************************************************************
 * Copyright (c) 2017, 2020 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.internal.analysis;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.utilities.EnvironmentFactory;

/**
 * @since 1.12
 * @noextend This class is not intended to be subclassed by clients. It is part of the hierarchy for auto-generated visitors.
 */
public class PivotASPostProcessVisitor extends ASSymbolicEvaluationVisitor {

	public PivotASPostProcessVisitor(@NonNull EnvironmentFactory context) {
		super(context);
	}
}
