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

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.generator.TypeDescriptor;
import org.eclipse.ocl.examples.codegen.java.JavaStream;
import org.eclipse.ocl.examples.codegen.java.JavaStream.SubStream;
import org.eclipse.ocl.pivot.ids.ElementId;

/**
 * A BooleanObjectDescriptor describes the Boolean type and any associated irregular code generation patterns.
 */
public class BooleanObjectDescriptor extends SimpleValueDescriptor implements SimpleDescriptor
{
	protected final @NonNull BooleanPrimitiveDescriptor primitiveTypeDescriptor;

	public BooleanObjectDescriptor(@NonNull ElementId elementId) {
		super(elementId, Boolean.class);
		primitiveTypeDescriptor = new BooleanPrimitiveDescriptor(elementId);
	}

	@Override
	public void appendCast(@NonNull JavaStream js, @Nullable Boolean isRequired, @Nullable Class<?> actualJavaClass, @Nullable SubStream subStream) {
		if (actualJavaClass != boolean.class) {
			assert (actualJavaClass == null) || (actualJavaClass == Object.class);
			js.append("(");
			append(js, null);
			js.append(")");
		}
		if (subStream != null) {
			subStream.append();
		}
	}

	@Override
	public void appendNotEqualsTerm(@NonNull JavaStream js, @NonNull CGValuedElement thisValue, @NonNull TypeDescriptor thatTypeDescriptor, @NonNull String thatName) {
		if (thatTypeDescriptor instanceof BooleanPrimitiveDescriptor) {
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
		boolean thisIsNonNull = thisValue.isNonNull();
		boolean thatIsNonNull = thatValue.isNonNull();
		if (thisIsNonNull || !thatIsNonNull) {
			js.appendValueName(thisValue);
		}
		else {
			js.append("(");
			js.appendValueName(thisValue);
			js.append(" == Boolean.TRUE)");
		}
		js.append(notEquals ? " != " : " == ");
		if (thatIsNonNull || !thisIsNonNull) {
			js.appendValueName(thatValue);
		}
		else {
			js.append("(");
			js.appendValueName(thatValue);
			js.append(" == Boolean.TRUE)");
		}
	}

	@Override
	public @NonNull TypeDescriptor getPrimitiveDescriptor() {
		return primitiveTypeDescriptor;
	}
}