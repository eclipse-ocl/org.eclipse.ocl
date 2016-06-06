/*******************************************************************************
 * Copyright (c) 2016 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.project.stringlib;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.ids.CollectionTypeId;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.library.AbstractSimpleBinaryOperation;
import org.eclipse.ocl.pivot.values.SequenceValue;

/**
 * StringSplitOperation realises the String split() library operation.
 */
public class StringSplitOperation extends AbstractSimpleBinaryOperation
{
	public static final @NonNull StringSplitOperation INSTANCE = new StringSplitOperation();
	public static final @NonNull CollectionTypeId SEQ_STRING = TypeId.SEQUENCE.getSpecializedId(TypeId.STRING);

	@Override
	public @NonNull SequenceValue evaluate(@Nullable Object sourceVal, @Nullable Object argVal) {
		String string = asString(sourceVal); 
		String pattern = asString(argVal); 
		String[] split = string.split(pattern);
		return createSequenceOfEach(SEQ_STRING, (Object[])split);
	}
}
