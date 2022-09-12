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

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.examples.codegen.cgmodel.CGConstraint;
import org.eclipse.ocl.pivot.Constraint;

/**
 * A ConstraintNameManager supervises the variable names allocated within the scope of a Constraint.
 */
public class ConstraintNameManager extends FeatureNameManager
{
	public ConstraintNameManager(@NonNull ClassNameManager classNameManager, @NonNull CGConstraint cgConstraint) {
		super(classNameManager, classNameManager, cgConstraint);
	}

	public @NonNull Constraint getASConstraint() {
		return (Constraint)asScope;
	}

	public @NonNull CGConstraint getCGConstraint() {
		return (CGConstraint)cgScope;
	}
}
