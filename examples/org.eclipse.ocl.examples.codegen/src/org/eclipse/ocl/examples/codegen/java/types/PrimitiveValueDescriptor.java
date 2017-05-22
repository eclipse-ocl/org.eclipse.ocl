/*******************************************************************************
 * Copyright (c) 2015, 2017 Willink Transformations and others.
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
import org.eclipse.ocl.pivot.ids.DataTypeId;
import org.eclipse.ocl.pivot.values.Value;

/**
 * A PrimitiveValueDescriptor describes primitive value that has one type when boxed and another as Ecore.
 */
public class PrimitiveValueDescriptor extends BoxedValueDescriptor
{
	private @NonNull Class<?> primitiveClass;

	public PrimitiveValueDescriptor(@NonNull DataTypeId typeId, @NonNull Class<? extends Value> boxedClass,
			@NonNull Class<?> primitiveClass) {
		super(typeId, boxedClass);
		this.primitiveClass = primitiveClass;
	}

	@Override
	protected @NonNull EcoreDescriptor createEcoreDescriptor() {
		EcoreDescriptor ecoreDescriptor = createPrimitiveObjectEcoreDescriptor(primitiveClass);
		if (ecoreDescriptor != null) {
			return ecoreDescriptor;
		}
		ecoreDescriptor = createPrimitiveTypeEcoreDescriptor(primitiveClass);
		if (ecoreDescriptor != null) {
			return ecoreDescriptor;
		}
		return new UnboxedValueDescriptor(elementId, primitiveClass);
	}

	@Override
	protected @NonNull UnboxedDescriptor createUnboxedDescriptor() {
		UnboxedDescriptor unboxedDescriptor = createPrimitiveTypeUnboxedDescriptor(primitiveClass);
		if (unboxedDescriptor != null) {
			return unboxedDescriptor;
		}
		return new UnboxedValueDescriptor(elementId, primitiveClass);
	}
}