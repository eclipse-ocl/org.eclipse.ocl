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

public class SubIdiom
{
	public static @NonNull SubIdiom CLOSE_BRACE = new SubIdiom(new KeywordLocator("}"), StringSegment.POP, StringSegment.SOFT_SPACE, ValueSegment.INSTANCE, StringSegment.NEW_LINE);
	public static @NonNull SubIdiom CLOSE_SQUARE = new SubIdiom(new KeywordLocator("]"), ValueSegment.INSTANCE);
	public static @NonNull SubIdiom COMMA = new SubIdiom(new KeywordLocator(","), ValueSegment.INSTANCE, StringSegment.SOFT_SPACE);
	public static @NonNull SubIdiom DEFAULT = new SubIdiom(new DefaultLocator(), StringSegment.SOFT_SPACE, ValueSegment.INSTANCE, StringSegment.SOFT_SPACE);
	public static @NonNull SubIdiom DOUBLE_COLON = new SubIdiom(new KeywordLocator("::"), ValueSegment.INSTANCE);
	public static @NonNull SubIdiom DOT_DOT = new SubIdiom(new KeywordLocator(".."), ValueSegment.INSTANCE);
	public static @NonNull SubIdiom OPEN_BRACE = new SubIdiom(new KeywordLocator("{"), StringSegment.SOFT_SPACE, ValueSegment.INSTANCE, StringSegment.PUSH, StringSegment.NEW_LINE);
	public static @NonNull SubIdiom OPEN_SQUARE = new SubIdiom(new KeywordLocator("["), ValueSegment.INSTANCE);
	public static @NonNull SubIdiom SEMI_COLON = new SubIdiom(new KeywordLocator(";"), ValueSegment.INSTANCE, StringSegment.NEW_LINE);

	protected final @NonNull Locator locator;
	protected final @NonNull Segment @NonNull [] segments;

	public SubIdiom(@NonNull Locator locator, @NonNull Segment @NonNull ... segments) {
		this.locator = locator;
		this.segments = segments;
	}

	public boolean matches(@NonNull SerializationNode serializationNode) {
		return locator.matches(serializationNode);
	}

	public void serialize(@NonNull String value, @NonNull SerializationBuilder serializationBuilder) {
		for (@NonNull Segment segment : segments) {
			segment.serialize(value, serializationBuilder);
		}
	}

	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append(locator.toString());
		s.append(":");
		boolean isFirst = true;
		for (@NonNull Segment segment : segments) {
			if (!isFirst) {
				s.append("+");
			}
			s.append(segment.toString());
			isFirst = false;
		}
		return s.toString();
	}
}
