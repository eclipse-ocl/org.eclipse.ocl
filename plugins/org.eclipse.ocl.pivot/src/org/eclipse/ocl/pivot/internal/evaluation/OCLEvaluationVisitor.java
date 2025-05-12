/*******************************************************************************
 * Copyright (c) 2010, 2018 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/

package org.eclipse.ocl.pivot.internal.evaluation;

import org.eclipse.jdt.annotation.NonNull;

/**
 * An evaluation visitor implementation for OCL expressions.
 */
public class OCLEvaluationVisitor extends BasicEvaluationVisitor
{
	/**
	 * @since 1.1
	 */
	public OCLEvaluationVisitor(@NonNull ExecutorInternal executor) {
		super(executor);
	}
}
