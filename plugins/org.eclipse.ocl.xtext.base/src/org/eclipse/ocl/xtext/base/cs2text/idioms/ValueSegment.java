/*******************************************************************************
 * Copyright (c) 2020 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.xtext.base.cs2text.idioms;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.xtext.base.cs2text.SerializationBuilder;
import org.eclipse.ocl.xtext.base.cs2text.elements.SerializationNode;
import org.eclipse.ocl.xtext.base.cs2text.user.UserElementSerializer;

public class ValueSegment implements Segment
{
	public static final @NonNull ValueSegment INSTANCE = new ValueSegment();

	private ValueSegment() {}

	@Override
	public void serialize(@NonNull SerializationNode serializationNode, @NonNull UserElementSerializer serializer, @NonNull SerializationBuilder serializationBuilder) {
		serializationNode.serialize(serializer, serializationBuilder);
	}

	@Override
	public String toString() {
		return "«value»";
	}
}
