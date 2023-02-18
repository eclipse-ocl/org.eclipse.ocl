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

import java.math.BigDecimal;
import java.math.BigInteger;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.examples.codegen.cgmodel.CGUnboxExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.java.JavaStream;
import org.eclipse.ocl.examples.codegen.naming.ExecutableNameManager;
import org.eclipse.ocl.pivot.ids.DataTypeId;

/**
 * A PrimitiveValueDescriptor describes primitive value that has one type when boxed and another as Ecore.
 */
public class PrimitiveValueDescriptor extends BoxedValueDescriptor
{
	private @NonNull Class<?> primitiveClass;

	public PrimitiveValueDescriptor(@NonNull DataTypeId typeId, @NonNull Class<?> boxedClass, @NonNull Class<?> primitiveClass) {
		super(typeId, boxedClass);
		this.primitiveClass = primitiveClass;
	}

	@Override
	public @NonNull Boolean appendUnboxStatements(@NonNull JavaStream js, @NonNull ExecutableNameManager localNameManager,
			@NonNull CGUnboxExp cgUnboxExp, @NonNull CGValuedElement boxedValue) {
		js.appendDeclaration(cgUnboxExp);
		js.append(" = ");
		if (primitiveClass == Character.class) {
			js.append("Character.valueOf(");
			js.appendValueName(boxedValue);
			js.append(".asInteger());\n");
		}
		else {
			js.appendValueName(boxedValue);
			if (primitiveClass == BigInteger.class) {
				js.append(".bigDecimalValue();\n");
			}
			else if (primitiveClass == BigDecimal.class) {
				js.append(".bigIntegerValue();\n");
			}
			else if (primitiveClass == boolean.class) {
				js.append(".asInteger() != 0;\n");
			}
			else if ((primitiveClass == double.class) || (primitiveClass == Double.class) || (primitiveClass == float.class) || (primitiveClass == Float.class)) {
				js.append(".asDouble();\n");
			}
			else if ((primitiveClass == byte.class) || (primitiveClass == char.class) || (primitiveClass == int.class) || (primitiveClass == Integer.class) || (primitiveClass == long.class) || (primitiveClass == Long.class) || (primitiveClass == short.class) || (primitiveClass == Short.class)) {
				js.append(".asInteger();\n");
			}
			else {
				throw new UnsupportedOperationException("PrimitiveValueDescriptor.appendUnboxStatements for " + primitiveClass);
			}
		}
		return true;
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