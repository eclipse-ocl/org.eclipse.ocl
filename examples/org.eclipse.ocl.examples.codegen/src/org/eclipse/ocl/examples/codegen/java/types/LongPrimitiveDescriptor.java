/*******************************************************************************
 * Copyright (c) 2015 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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
 * A LongPrimitiveDescriptor describes the long type and any associated irregular code generation patterns.
 */
public class LongPrimitiveDescriptor extends AbstractPrimitiveDescriptor
{
	public LongPrimitiveDescriptor(@NonNull ElementId elementId) {
		super(elementId, long.class);
	}

	@Override
	public void appendCast(@NonNull JavaStream js, @Nullable Class<?> actualJavaClass, @Nullable SubStream subStream) {
		if ((subStream != null) && (actualJavaClass == Long.class)) {
			subStream.append();
			js.append(".longValue()");
		}
		else {
			js.append("(");
			js.appendClassReference(Long.class);
			js.append(")");
			if (subStream != null) {
				subStream.append();
			}
		}
	}
}