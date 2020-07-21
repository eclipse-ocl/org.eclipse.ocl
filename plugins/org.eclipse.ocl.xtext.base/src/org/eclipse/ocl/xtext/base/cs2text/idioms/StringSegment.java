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

public class StringSegment implements Segment
{
	public static final @NonNull StringSegment NEW_LINE = new StringSegment("\n");
	public static final @NonNull StringSegment POP = new StringSegment(SerializationBuilder.POP);
	public static final @NonNull StringSegment PUSH = new StringSegment(SerializationBuilder.PUSH);
	public static final @NonNull StringSegment SOFT_SPACE = new StringSegment(SerializationBuilder.SOFT_SPACE);

	protected final @NonNull String string;

	public StringSegment(@NonNull String string) {
		this.string = string;
	}

	@Override
	public void serialize(@NonNull String value, @NonNull SerializationBuilder serializationBuilder) {
		serializationBuilder.append(string);
	}

	@Override
	public String toString() {
		return string;
	}
}
