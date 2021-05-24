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

import java.util.List;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.Element;

/**
 * @since 1.15
 */
public class CSEAggregateElement extends AbstractCSEElement<@NonNull Element>
{
	private static int computeHeight(@NonNull List<@NonNull CSEElement> elements) {
		int maxHeight = 0;
		for (@NonNull CSEElement element : elements) {
			int height = element.getHeight();
			if (height > maxHeight) {
				maxHeight = height;
			}
		}
		return maxHeight + 1;
	}

	public CSEAggregateElement(@NonNull CommonSubExpressionAnalysis cseAnalysis, @NonNull Element element, @NonNull List<@NonNull CSEElement> elements) {
		super(cseAnalysis, element, computeHeight(elements));
	}
}
