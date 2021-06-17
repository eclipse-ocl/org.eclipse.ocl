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

/**
 * @since 1.16
 */
public class CSEValueElement extends AbstractCSEElement<@NonNull LiteralExp>
{
	protected final @NonNull Object value;

	public CSEValueElement(@NonNull CommonSubExpressionAnalysis cseAnalysis, @NonNull LiteralExp exemplar, @NonNull Object value) {
		super(cseAnalysis, exemplar, 0);
		this.value = value;
	}

	public void addLiteralExp(@NonNull LiteralExp literalExp) {
		addOutput(literalExp);
	}
}
