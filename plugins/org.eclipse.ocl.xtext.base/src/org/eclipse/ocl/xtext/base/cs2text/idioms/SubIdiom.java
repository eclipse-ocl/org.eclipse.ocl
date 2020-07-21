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
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.xtext.base.cs2text.BasicSerializationRule;
import org.eclipse.ocl.xtext.base.cs2text.SerializationBuilder;
import org.eclipse.ocl.xtext.base.cs2text.Serializer;
import org.eclipse.ocl.xtext.base.cs2text.elements.SerializationNode;
import org.eclipse.ocl.xtext.basecs.BaseCSPackage;

public class SubIdiom
{
	public static @NonNull CommentSegment COMMENT = new CommentSegment(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS);

	public static @NonNull SubIdiom CLOSE_BRACE = new SubIdiom(new KeywordLocator("}"), StringSegment.POP, StringSegment.SOFT_SPACE, ValueSegment.INSTANCE, StringSegment.NEW_LINE);
	public static @NonNull SubIdiom CLOSE_SQUARE = new SubIdiom(new KeywordLocator("]"), StringSegment.NO_SPACE, ValueSegment.INSTANCE);
	public static @NonNull SubIdiom COMMA = new SubIdiom(new KeywordLocator(","), StringSegment.NO_SPACE, ValueSegment.INSTANCE, StringSegment.SOFT_SPACE);
	public static @NonNull SubIdiom DEFAULT = new SubIdiom(DefaultLocator.INSTANCE, StringSegment.SOFT_SPACE, ValueSegment.INSTANCE, StringSegment.SOFT_SPACE);
	public static @NonNull SubIdiom DOUBLE_COLON = new SubIdiom(new KeywordLocator("::"), StringSegment.NO_SPACE, ValueSegment.INSTANCE, StringSegment.NO_SPACE);
	public static @NonNull SubIdiom DOT_DOT = new SubIdiom(new KeywordLocator(".."), StringSegment.NO_SPACE, ValueSegment.INSTANCE, StringSegment.NO_SPACE);
	public static @NonNull SubIdiom OPEN_BRACE = new SubIdiom(new KeywordLocator("{"), StringSegment.SOFT_SPACE, ValueSegment.INSTANCE, StringSegment.PUSH, StringSegment.NEW_LINE);
	public static @NonNull SubIdiom OPEN_SQUARE = new SubIdiom(new KeywordLocator("["), StringSegment.NO_SPACE, ValueSegment.INSTANCE, StringSegment.NO_SPACE);
	public static @NonNull SubIdiom SEMI_COLON = new SubIdiom(new KeywordLocator(";"), StringSegment.NO_SPACE, ValueSegment.INSTANCE, StringSegment.NEW_LINE);
	public static @NonNull SubIdiom VALUE = new SubIdiom(null, ValueSegment.INSTANCE);

	public static @NonNull SubIdiom COMMENTED_PACKAGE = new SubIdiom(new KeywordLocator("enum"), COMMENT, ValueSegment.INSTANCE);


	public static @NonNull SubIdiom PackagesCS_ownedClasses = new SubIdiom(new AssignmentLocator(BaseCSPackage.Literals.PACKAGE_CS__OWNED_CLASSES), StringSegment.HALF_NEW_LINE, ValueSegment.INSTANCE, StringSegment.HALF_NEW_LINE);

	protected final @Nullable Locator locator;
	protected final @NonNull Segment @NonNull [] segments;

	public SubIdiom(@Nullable Locator locator, @NonNull Segment @NonNull ... segments) {
		this.locator = locator;
		this.segments = segments;
	}

	public boolean matches(@NonNull SerializationNode serializationNode, @NonNull BasicSerializationRule serializationRule) {
		return (locator != null) && locator.matches(serializationNode, serializationRule);
	}

	public void serialize(@NonNull SerializationNode serializationNode, @NonNull Serializer serializer, @NonNull SerializationBuilder serializationBuilder) {
		for (@NonNull Segment segment : segments) {
			segment.serialize(serializationNode, serializer, serializationBuilder);
		}
	}

	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append("@");
		s.append(locator != null ? locator.toString() : "«null»");
		s.append("|");
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
