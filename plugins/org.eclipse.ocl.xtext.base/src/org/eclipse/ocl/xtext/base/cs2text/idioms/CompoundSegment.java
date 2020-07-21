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

public class CompoundSegment implements Segment
{
	protected final @NonNull Segment @NonNull [] segments;

	public CompoundSegment(@NonNull Segment @NonNull ... segments) {
		this.segments = segments;
	}

	@Override
	public void serialize(@NonNull String value, @NonNull SerializationBuilder serializationBuilder) {
		for (@NonNull Segment segment : segments) {
			segment.serialize(value, serializationBuilder);
		}
	}

	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		boolean isFirst = true;
		for (@NonNull Segment segment : segments) {
			if (!isFirst) {
				s.append("+");
			}
			s.append(segment.toString());
			isFirst = true;
		}
		return s.toString();
	}
}
