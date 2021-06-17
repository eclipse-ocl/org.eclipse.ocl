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
import org.eclipse.ocl.pivot.Element;

/**
 * @since 1.16
 */
public class CSEAggregateElement extends AbstractCSEElement<@NonNull Element>
{
	public CSEAggregateElement(@NonNull CommonSubExpressionAnalysis cseAnalysis, @NonNull Element element, @NonNull Iterable<@NonNull CSEElement> elements) {
		super(cseAnalysis, element, computeHeight(elements));
		for (@NonNull CSEElement anElement : elements) {
			addInput(anElement);
		}
	}
}
