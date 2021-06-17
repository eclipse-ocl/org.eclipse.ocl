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

import java.util.Map;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.TypedElement;
import org.eclipse.ocl.pivot.utilities.ClassUtil;

/**
 * @since 1.16
 */
public class CSEMappedElement extends AbstractCSEElement<@NonNull Element>
{
	private final @NonNull Map<@NonNull TypedElement, @NonNull CSEElement> key2element;

	public CSEMappedElement(@NonNull CommonSubExpressionAnalysis cseAnalysis, @NonNull Element element, @NonNull Map<@NonNull TypedElement, @NonNull CSEElement> key2element) {
		super(cseAnalysis, element, computeHeight(key2element.values()));
		this.key2element = key2element;
		for (@NonNull CSEElement inputCSE : key2element.values()) {
			addInput(inputCSE);
		}
	}

	public @NonNull CSEElement getElement(@NonNull TypedElement key) {
		return ClassUtil.nonNullState(key2element.get(key));
	}
}
