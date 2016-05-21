/*******************************************************************************
 * Copyright (c) 2015, 2016 Willink Transformations and others.
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
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.generator.TypeDescriptor;
import org.eclipse.ocl.examples.codegen.java.JavaStream;
import org.eclipse.ocl.pivot.ids.ElementId;

/**
 * An AbstractPrimitiveObjectDescriptor describes a built-in type and any associated irregular code generation patterns.
 */
public abstract class AbstractPrimitiveDescriptor extends SimpleValueDescriptor implements SimpleDescriptor
{
	public AbstractPrimitiveDescriptor(@NonNull ElementId elementId, @NonNull Class<?> javaClass) {
		super(elementId, javaClass);
	}

	@Override
	public void append(@NonNull JavaStream js, @Nullable Boolean isRequired) {
		js.append(javaClass.getName());			// Override avoids registration of int as an import
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
}