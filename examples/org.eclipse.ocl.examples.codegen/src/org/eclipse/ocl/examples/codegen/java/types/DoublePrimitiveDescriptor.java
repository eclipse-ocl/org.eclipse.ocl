/*******************************************************************************
 * Copyright (c) 2015, 2019 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.codegen.java.types;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.java.JavaStream;
import org.eclipse.ocl.examples.codegen.java.JavaStream.SubStream;
import org.eclipse.ocl.pivot.ids.ElementId;

/**
 * A DoublePrimitiveDescriptor describes the double type and any associated irregular code generation patterns.
 */
public class DoublePrimitiveDescriptor extends AbstractPrimitiveDescriptor
{
	public DoublePrimitiveDescriptor(@NonNull ElementId elementId) {
		super(elementId, double.class, Double.class);
	}

	@Override
	public void appendCast(@NonNull JavaStream js, @Nullable Boolean isRequired, @Nullable Class<?> actualJavaClass, @NonNull SubStream subStream) {
		appendCast(js, actualJavaClass, subStream);
		js.append(".doubleValue()");
	}
}