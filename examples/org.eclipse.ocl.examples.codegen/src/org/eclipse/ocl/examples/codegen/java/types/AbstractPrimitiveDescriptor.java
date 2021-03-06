/*******************************************************************************
 * Copyright (c) 2015, 2021 Willink Transformations and others.
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
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.generator.TypeDescriptor;
import org.eclipse.ocl.examples.codegen.java.JavaStream;
import org.eclipse.ocl.examples.codegen.java.JavaStream.SubStream;
import org.eclipse.ocl.pivot.ids.ElementId;

/**
 * An AbstractPrimitiveObjectDescriptor describes a built-in type and any associated irregular code generation patterns.
 */
public abstract class AbstractPrimitiveDescriptor extends SimpleValueDescriptor implements SimpleDescriptor
{
	protected final @NonNull Class<?> nonPrimitiveJavaClass;

	protected AbstractPrimitiveDescriptor(@NonNull ElementId elementId, @NonNull Class<?> primitiveJavaClass, @NonNull Class<?> nonPrimitiveJavaClass) {
		super(elementId, primitiveJavaClass);
		this.nonPrimitiveJavaClass = nonPrimitiveJavaClass;
	}

	@Override
	public void append(@NonNull JavaStream js, @Nullable Boolean isRequired) {
		js.append(javaClass.getName());			// Override avoids registration of int as an import
	}

	protected void appendCast(@NonNull JavaStream js, @Nullable Class<?> actualJavaClass, @NonNull SubStream subStream) {
		if (actualJavaClass == nonPrimitiveJavaClass) {
			subStream.append();
		}
		else {
			js.append("((");
			js.appendClassReference(true, nonPrimitiveJavaClass);
			js.append(")");
			subStream.append();
			js.append(")");
		}
		// caller must append the derived variant of e.g. ".intValue()"
	}

	@Override
	public void appendNotEqualsTerm(@NonNull JavaStream js, @NonNull CGValuedElement thisValue, @NonNull TypeDescriptor thatTypeDescriptor, @NonNull String thatName) {
		if (thatTypeDescriptor instanceof AbstractPrimitiveDescriptor) {
			js.appendValueName(thisValue);
			js.append(" != ");
			js.append(thatName);
		}
		else {
			js.append("(");
			js.append(thatName);
			js.append(" == null) || (");
			js.appendValueName(thisValue);
			js.append(" != ");
			js.append(thatName);
			js.append(")");
		}
	}

	@Override
	public void appendEqualsValue(@NonNull JavaStream js, @NonNull CGValuedElement thisValue,
			@NonNull CGValuedElement thatValue, boolean notEquals) {
		js.appendValueName(thisValue);
		js.append(notEquals ? " != " : " == ");
		js.appendValueName(thatValue);
		//		super.appendEqualsValue(js, thisValue, thatValue, notEquals);
	}

	@Override
	public @NonNull Class<?> getNonPrimitiveJavaClass() {
		return nonPrimitiveJavaClass;
	}
}