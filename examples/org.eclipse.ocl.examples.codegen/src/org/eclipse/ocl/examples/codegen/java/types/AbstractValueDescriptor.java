/*******************************************************************************
 * Copyright (c) 2013, 2021 CEA LIST and others.
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
import org.eclipse.ocl.examples.codegen.cgmodel.CGEcoreExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.java.JavaLocalContext;
import org.eclipse.ocl.examples.codegen.java.JavaStream;
import org.eclipse.ocl.pivot.ids.ElementId;
import org.eclipse.ocl.pivot.utilities.ValueUtil;

/**
 * An AbstractValueDescriptor extends an AbstractDescriptor to describe a type for which a Java Class as well as a pivot ElementId is available.
 */
public abstract class AbstractValueDescriptor extends AbstractDescriptor
{
	protected final @NonNull Class<?> javaClass;

	public AbstractValueDescriptor(@NonNull ElementId elementId, @NonNull Class<?> javaClass) {
		super(elementId);
		assert javaClass != Object.class;
		this.javaClass = javaClass;
	}

	public @NonNull Boolean appendEcore(@NonNull JavaStream js, @NonNull JavaLocalContext<@NonNull ?> localContext, @NonNull CGEcoreExp cgEcoreExp, @NonNull CGValuedElement unboxedValue) {
		String functionName = null;
		if (javaClass == BigDecimal.class) {
			functionName = "bigDecimalValueOf";
		}
		else if (javaClass == BigInteger.class) {
			functionName = "bigIntegerValueOf";
		}
		else if ((javaClass == Byte.class)) {// || (javaClass == byte.class)) {
			functionName = "byteValueOf";
		}
		else if ((javaClass == Character.class)) {// || (javaClass == char.class)) {
			functionName = "characterValueOf";
		}
		else if ((javaClass == Double.class)) {// || (javaClass == double.class)) {
			functionName = "doubleValueOf";
		}
		else if ((javaClass == Float.class)) {// || (javaClass == float.class)) {
			functionName = "floatValueOf";
		}
		else if ((javaClass == Integer.class)) {// || (javaClass == int.class)) {
			functionName = "intValueOf";
		}
		else if ((javaClass == Long.class)) {// || (javaClass == long.class)) {
			functionName = "longValueOf";
		}
		else if ((javaClass == Short.class)) {// || (javaClass == short.class)) {
			functionName = "shortValueOf";
		}
		if (functionName != null) {
			js.append("final ");
			js.appendClassReference(null, javaClass);
			js.append(" ");
			js.appendValueName(cgEcoreExp);
			js.append(" = ");
			js.appendClassReference(null, ValueUtil.class);
			js.append(".");
			js.append(functionName);
			js.append("(");
			js.appendValueName(cgEcoreExp.getSource());
			js.append(");\n");
			return true;
		}
		throw new UnsupportedOperationException();
//		return appendEcoreLegacy(js, localContext, cgEcoreExp, unboxedValue);
	}

	@Override
	public void append(@NonNull JavaStream js, @Nullable Boolean isRequired) {
		js.appendClassReference(isRequired, javaClass);
	}

	@Override
	public @NonNull String getClassName() {
		return javaClass.getName();
	}

	@Override
	public @NonNull Class<?> getJavaClass() {
		return javaClass;
	}

	@Override
	public @Nullable Class<?> hasJavaClass() {
		return javaClass;
	}

	@Override
	public boolean isAssignableTo(@NonNull Class<?> javaClass) {
		return javaClass.isAssignableFrom(this.javaClass);
	}

	@Override
	public @NonNull String toString() {
		return elementId + " => " + getClassName();
	}
}