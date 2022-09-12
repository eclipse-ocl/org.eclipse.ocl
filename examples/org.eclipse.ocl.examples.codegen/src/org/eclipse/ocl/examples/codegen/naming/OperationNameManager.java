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
import org.eclipse.ocl.examples.codegen.cgmodel.CGOperation;
import org.eclipse.ocl.pivot.Operation;

/**
 * An OperationNameManager supervises the parameter and variable names allocated within the scope of an Operation.
 */
public class OperationNameManager extends FeatureNameManager
{
	public OperationNameManager(@NonNull ClassNameManager classNameManager, @NonNull CGOperation cgOperation) {
		super(classNameManager, classNameManager, cgOperation);
	}

	public @NonNull Operation getASOperation() {
		return (Operation)asScope;
	}

	public @NonNull CGOperation getCGOperation() {
		return (CGOperation)cgScope;
	}
}
