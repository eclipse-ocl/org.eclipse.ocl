/**
 * <copyright>
 * 
 * Copyright (c) 2013 CEA LIST and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *   E.D.Willink(CEA LIST) - Initial API and implementation
 * 
 * </copyright>
 */
package org.eclipse.ocl.examples.codegen.java.types;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.generator.TypeDescriptor;
import org.eclipse.ocl.examples.codegen.java.JavaStream;
import org.eclipse.ocl.examples.domain.ids.ElementId;

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
	public void appendEqualsValue(@NonNull JavaStream js, @NonNull CGValuedElement thisValue, @NonNull CGValuedElement thatValue, boolean notEquals) {
		js.appendValueName(thisValue);
		js.append(notEquals ? " != " :  " == ");
		js.appendValueName(thatValue);
	}

	@Override
	public @NonNull TypeDescriptor getPrimitiveDescriptor() {
		return primitiveTypeDescriptor;
	}
}