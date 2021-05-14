/**
 * Copyright (c) 2021 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 */
package org.eclipse.ocl.pivot.internal.cse;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.OCLExpression;

/**
 * @since 1.15
 */
public class CSEExpressionElement extends AbstractCSEElement<@NonNull OCLExpression>
{
	public CSEExpressionElement(@NonNull AbstractCSEElement<?> parent, @NonNull OCLExpression expression, int height) {
		super(parent, expression, height);
	}
}
