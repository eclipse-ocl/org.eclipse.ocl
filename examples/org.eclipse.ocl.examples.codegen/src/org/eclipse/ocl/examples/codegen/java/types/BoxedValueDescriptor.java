/*******************************************************************************
 * Copyright (c) 2013, 2019 CEA LIST and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink(CEA LIST) - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.codegen.java.types;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.generator.CodeGenerator;
import org.eclipse.ocl.examples.codegen.generator.TypeDescriptor;
import org.eclipse.ocl.pivot.ids.ElementId;

/**
 * A BoxedValueDescriptor describes a type whose boxed representation differs from its unboxed representation. It has a pivot ElementId and a Java class.
 * <p>
 * Thus an IntegerValue is a TypeId.INTEGER and an org.eclipse.ocl.domain.values.IntegerValue.
 */
public abstract class BoxedValueDescriptor extends AbstractValueDescriptor implements BoxedDescriptor
{
	private /*@LazyNonNull*/ EcoreDescriptor ecoreDescriptor;
	private /*@LazyNonNull*/ UnboxedDescriptor unboxedDescriptor;

	public BoxedValueDescriptor(@NonNull ElementId elementId, @NonNull Class<?> javaClass) {
		super(elementId, javaClass);
	}

	protected abstract @NonNull EcoreDescriptor createEcoreDescriptor();

	protected @Nullable UnboxedValueDescriptor createPrimitiveObjectEcoreDescriptor(@NonNull Class<?> instanceClass) {
		if (BigDecimal.class == instanceClass) {
			return new UnboxedValueDescriptor(elementId, BigDecimal.class);
		}
		else if (BigInteger.class == instanceClass) {
			return new UnboxedValueDescriptor(elementId, BigInteger.class);
		}
		else if (Byte.class == instanceClass) {
			return new UnboxedValueDescriptor(elementId, Byte.class);
		}
		else if (Character.class == instanceClass) {
			return new UnboxedValueDescriptor(elementId, Character.class);
		}
		else if (Double.class == instanceClass) {
			return new UnboxedValueDescriptor(elementId, Double.class);
		}
		else if (Float.class == instanceClass) {
			return new UnboxedValueDescriptor(elementId, Float.class);
		}
		else if (Integer.class == instanceClass) {
			return new UnboxedValueDescriptor(elementId, Integer.class);
		}
		else if (Long.class == instanceClass) {
			return new UnboxedValueDescriptor(elementId, Long.class);
		}
		else if (Short.class == instanceClass) {
			return new UnboxedValueDescriptor(elementId, Short.class);
		}
		else {
			return null;
		}
	}

	protected @Nullable EcoreDescriptor createPrimitiveTypeEcoreDescriptor(@NonNull Class<?> instanceClass) {
		if (boolean.class == instanceClass) {
			return new BooleanPrimitiveDescriptor(elementId);
		}
		else if (byte.class == instanceClass) {
			return new BytePrimitiveDescriptor(elementId);
		}
		else if (char.class == instanceClass) {
			return new CharPrimitiveDescriptor(elementId);
		}
		else if (double.class == instanceClass) {
			return new DoublePrimitiveDescriptor(elementId);
		}
		else if (float.class == instanceClass) {
			return new FloatPrimitiveDescriptor(elementId);
		}
		else if (int.class == instanceClass) {
			return new IntPrimitiveDescriptor(elementId);
		}
		else if (long.class == instanceClass) {
			return new LongPrimitiveDescriptor(elementId);
		}
		else if (short.class == instanceClass) {
			return new ShortPrimitiveDescriptor(elementId);
		}
		else {
			return null;
		}
	}

	protected @Nullable UnboxedDescriptor createPrimitiveTypeUnboxedDescriptor(@NonNull Class<?> instanceClass) {
		if (boolean.class == instanceClass) {
			return new BooleanPrimitiveDescriptor(elementId);
		}
		else if (byte.class == instanceClass) {
			return new BytePrimitiveDescriptor(elementId);
		}
		else if (char.class == instanceClass) {
			return new CharPrimitiveDescriptor(elementId);
		}
		else if (double.class == instanceClass) {
			return new DoublePrimitiveDescriptor(elementId);
		}
		else if (float.class == instanceClass) {
			return new FloatPrimitiveDescriptor(elementId);
		}
		else if (int.class == instanceClass) {
			return new IntPrimitiveDescriptor(elementId);
		}
		else if (long.class == instanceClass) {
			return new LongPrimitiveDescriptor(elementId);
		}
		else if (short.class == instanceClass) {
			return new ShortPrimitiveDescriptor(elementId);
		}
		else {
			return null;
		}
	}

	protected abstract @NonNull UnboxedDescriptor createUnboxedDescriptor();

	@Override
	public @NonNull EcoreDescriptor getEcoreDescriptor(@NonNull CodeGenerator codeGenerator, @Nullable Class<?> instanceClass) {
		if (instanceClass != null) {
			EcoreDescriptor ecoreDescriptor = createPrimitiveObjectEcoreDescriptor(instanceClass);
			if (ecoreDescriptor != null) {
				return ecoreDescriptor;
			}
			ecoreDescriptor = createPrimitiveTypeEcoreDescriptor(instanceClass);
			if (ecoreDescriptor != null) {
				return ecoreDescriptor;
			}
			//			else {
			//				return new SimpleDataTypeDescriptor(id, instanceClassName);
			//			}
		}
		EcoreDescriptor ecoreDescriptor2 = ecoreDescriptor;
		if (ecoreDescriptor2 == null) {
			ecoreDescriptor = ecoreDescriptor2 = createEcoreDescriptor();
		}
		return ecoreDescriptor2;
	}

	@Override
	public @NonNull UnboxedDescriptor getUnboxedDescriptor(@NonNull CodeGenerator codeGenerator) {
		UnboxedDescriptor unboxedDescriptor2 = unboxedDescriptor;
		if (unboxedDescriptor2 == null) {
			unboxedDescriptor = unboxedDescriptor2 = createUnboxedDescriptor();
		}
		return unboxedDescriptor2;
	}

	@Override
	public final boolean isAssignableFrom(@NonNull TypeDescriptor typeDescriptor) {
		if (typeDescriptor == this) {
			return true;
		}
		if (!(typeDescriptor instanceof BoxedDescriptor)) {
			return false;
		}
		return javaClass.isAssignableFrom(typeDescriptor.getJavaClass());
	}
}