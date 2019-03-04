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
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.generator.TypeDescriptor;
import org.eclipse.ocl.examples.codegen.java.JavaStream;
import org.eclipse.ocl.examples.codegen.java.JavaStream.SubStream;
import org.eclipse.ocl.pivot.ids.ElementId;

/**
 * An EcoreDoubleObjectDescriptor describes the EDoubleObject type and any associated irregular code generation patterns.
 */
public class EcoreDoubleObjectDescriptor extends SimpleValueDescriptor implements SimpleDescriptor
{
	protected final @NonNull DoublePrimitiveDescriptor primitiveTypeDescriptor;

	public EcoreDoubleObjectDescriptor(@NonNull ElementId elementId) {
		super(elementId, Double.class);
		primitiveTypeDescriptor = new DoublePrimitiveDescriptor(elementId);
	}

	@Override
	public void appendCast(@NonNull JavaStream js, @Nullable Boolean isRequired, @Nullable Class<?> actualJavaClass, @Nullable SubStream subStream) {
		if (actualJavaClass != boolean.class) {
			assert (actualJavaClass == null) || (actualJavaClass == Object.class);
			js.append("(");
			append(js, isRequired);
			js.append(")");
		}
		if (subStream != null) {
			subStream.append();
		}
	}

	@Override
	public void appendNotEqualsTerm(@NonNull JavaStream js, @NonNull CGValuedElement thisValue, @NonNull TypeDescriptor thatTypeDescriptor, @NonNull String thatName) {
		if (thatTypeDescriptor instanceof DoublePrimitiveDescriptor) {
			js.append("(");
			js.appendValueName(thisValue);
			js.append(" == null) || (");
			js.appendValueName(thisValue);
			js.append(" != ");
			js.append(thatName);
			js.append(")");
		}
		else {
			js.appendValueName(thisValue);
			js.append(" != ");
			js.append(thatName);
		}
	}

	@Override
	public void appendEqualsValue(@NonNull JavaStream js, @NonNull CGValuedElement thisValue,
			@NonNull CGValuedElement thatValue, boolean notEquals) {
		super.appendEqualsValue(js, thisValue, thatValue, notEquals);
	}

	@Override
	public @NonNull TypeDescriptor getPrimitiveDescriptor() {
		return primitiveTypeDescriptor;
	}
}