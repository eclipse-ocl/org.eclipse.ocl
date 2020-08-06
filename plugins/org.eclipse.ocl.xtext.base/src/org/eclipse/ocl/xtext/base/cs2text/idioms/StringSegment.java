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
import org.eclipse.xtext.util.Strings;

public class StringSegment implements Segment
{
	public static final @NonNull StringSegment HALF_NEW_LINE = new StringSegment(SerializationBuilder.HALF_NEW_LINE, true);
	public static final @NonNull StringSegment NEW_LINE = new StringSegment(SerializationBuilder.NEW_LINE, true);
	public static final @NonNull StringSegment NO_SPACE = new StringSegment(SerializationBuilder.NO_SPACE, true);
	public static final @NonNull StringSegment POP = new StringSegment(SerializationBuilder.POP, true);
	public static final @NonNull StringSegment PUSH = new StringSegment(SerializationBuilder.PUSH, true);
	public static final @NonNull StringSegment SOFT_NEW_LINE = new StringSegment(SerializationBuilder.SOFT_NEW_LINE, true);
	public static final @NonNull StringSegment SOFT_SPACE = new StringSegment(SerializationBuilder.SOFT_SPACE, true);

	protected final @NonNull String string;
	protected final boolean isPrintable;

	public StringSegment(@NonNull String string, boolean isPrintable) {
		this.string = string;
		this.isPrintable = isPrintable;
	}

	@Override
	public void serialize(@NonNull SerializationNode serializationNode, @NonNull UserElementSerializer serializer, @NonNull SerializationBuilder serializationBuilder) {
		serializationBuilder.append(string);
	}

	@Override
	public String toString() {
		return isPrintable ? string : Strings.convertToJavaString(string);
	}
}
