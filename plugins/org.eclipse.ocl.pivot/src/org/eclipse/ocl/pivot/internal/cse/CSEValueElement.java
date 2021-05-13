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
import org.eclipse.ocl.pivot.LiteralExp;
import org.eclipse.ocl.pivot.OCLExpression;

/**
 * @since 1.15
 */
public class CSEValueElement extends AbstractCSEElement<@NonNull LiteralExp, @NonNull OCLExpression>
{
	protected final @NonNull Object value;

	public CSEValueElement(@NonNull CommonSubExpressionAnalysis cseAnalysis, @NonNull LiteralExp exemplar, @NonNull Object value) {
		super(cseAnalysis, exemplar);
		this.value = value;
	}

	public void addLiteralExp(@NonNull LiteralExp literalExp) {
		addClient(literalExp);
	}
}
