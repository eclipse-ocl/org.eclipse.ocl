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
import org.eclipse.ocl.examples.codegen.cgmodel.CGEcoreExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.java.JavaStream;
import org.eclipse.ocl.examples.codegen.java.JavaStream.SubStream;
import org.eclipse.ocl.examples.codegen.naming.ExecutableNameManager;
import org.eclipse.ocl.pivot.ids.ElementId;
import org.eclipse.ocl.pivot.utilities.ValueUtil;

/**
 * A CharPrimitiveDescriptor describes the char type and any associated irregular code generation patterns.
 */
public class CharPrimitiveDescriptor extends AbstractPrimitiveDescriptor
{
	public CharPrimitiveDescriptor(@NonNull ElementId elementId) {
		super(elementId, char.class, Character.class);
	}

	@Override
	public void appendCast(@NonNull JavaStream js, @Nullable Boolean isRequired, @Nullable Class<?> actualJavaClass, @NonNull SubStream subStream) {
		appendCast(js, actualJavaClass, subStream);
		js.append(".charValue()");
	}

	@Override
	public @NonNull Boolean appendEcore(@NonNull JavaStream js, @NonNull ExecutableNameManager localNameManager, @NonNull CGEcoreExp cgEcoreExp, @NonNull CGValuedElement unboxedValue) {
		js.appendDeclaration(cgEcoreExp);
		js.append(" = ");
		assert unboxedValue.isNonNull();
		js.appendClassReference(null, ValueUtil.class);
		js.append(".charValueOf(");
		js.appendReferenceTo(unboxedValue);
		js.append(");\n");
		return true;
	}
}