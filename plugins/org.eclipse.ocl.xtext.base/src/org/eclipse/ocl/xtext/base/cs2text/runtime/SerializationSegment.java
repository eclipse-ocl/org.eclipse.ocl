/**
 * Copyright (c) 2020 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 */
package org.eclipse.ocl.xtext.base.cs2text.runtime;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport;

public interface SerializationSegment
{

	public static final @NonNull SerializationSegment HALF_NEW_LINE = null;//createStringSegment(IDIOM_MODEL, SerializationBuilder.HALF_NEW_LINE);
	public static final @NonNull SerializationSegment NO_SPACE = null;//createStringSegment(IDIOM_MODEL, SerializationBuilder.NO_SPACE);
	public static final @NonNull SerializationSegment POP = null;//createStringSegment(IDIOM_MODEL, SerializationBuilder.POP);
	public static final @NonNull SerializationSegment PUSH = null;//createStringSegment(IDIOM_MODEL, SerializationBuilder.PUSH);
	public static final @NonNull SerializationSegment SOFT_NEW_LINE = null;//createStringSegment(IDIOM_MODEL, SerializationBuilder.SOFT_NEW_LINE);
	public static final @NonNull SerializationSegment SOFT_SPACE = null;//createStringSegment(IDIOM_MODEL, SerializationBuilder.SOFT_SPACE);
	public static final @NonNull SerializationSegment VALUE = null;//createValueSegment(IDIOM_MODEL);

	public static @NonNull SerializationSegment createCustomSegment(Object object, Class<@NonNull BaseCommentSegmentSupport> class1) {
		// TODO Auto-generated method stub
		return null;
	}

	//	void serialize(@NonNull SerializationStep serializationStep, @NonNull UserElementSerializer serializer, @NonNull SerializationBuilder serializationBuilder);
}
