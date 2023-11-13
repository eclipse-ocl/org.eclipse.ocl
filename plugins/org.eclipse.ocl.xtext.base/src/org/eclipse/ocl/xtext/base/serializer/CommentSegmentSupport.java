/**
 * Copyright (c) 2020, 2023 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 */
package org.eclipse.ocl.xtext.base.serializer;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;

/**
 * CommentSegmentSupport defines the interface that a user-defined class must implement
 * to contribute comments to the overall output.
 */
public interface CommentSegmentSupport extends CustomSegmentSupport
{
	/**
	 * Append the body of a comment, wrapping the body in appropriate prefix/suffix and multi-line framing.
	 */
	void appendBody(@NonNull SerializationBuilder serializationBuilder, @Nullable String body);
}
