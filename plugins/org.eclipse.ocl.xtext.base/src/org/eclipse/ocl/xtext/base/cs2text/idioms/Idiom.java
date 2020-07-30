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

import java.util.Map;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.xtext.base.cs2text.elements.BasicSerializationRule;
import org.eclipse.ocl.xtext.base.cs2text.elements.SerializationNode;

public class Idiom
{
	public static class DebugIdiom extends Idiom
	{
		public DebugIdiom(@NonNull SubIdiom @NonNull... subIdioms) {
			super(subIdioms);
		}

		@Override
		protected @NonNull IdiomMatch createIdiomMatch(@NonNull SerializationNode serializationNode) {
			return new IdiomMatch(this, serializationNode) {

				@Override
				public boolean installIn(@NonNull Map<@NonNull SerializationNode, @NonNull SubIdiom> serializationNode2subIdiom) {
					return super.installIn(serializationNode2subIdiom);
				}

				@Override
				public boolean nextMatch(@NonNull SerializationNode serializationNode, @NonNull BasicSerializationRule serializationRule) {
					return super.nextMatch(serializationNode, serializationRule);
				}

			};
		}

		@Override
		public @Nullable IdiomMatch firstMatch(@NonNull SerializationNode serializationNode, @NonNull BasicSerializationRule serializationRule) {
			IdiomMatch firstMatch = super.firstMatch(serializationNode, serializationRule);
			if (firstMatch != null) {
				getClass(); 		// XXX debugging
			}
			return firstMatch;
		}

	}

	public static final @NonNull Idiom BRACES = new Idiom(SubIdiom.OPEN_BRACE, SubIdiom.CLOSE_BRACE);
	public static final @NonNull Idiom COMMA = new Idiom(SubIdiom.COMMA);
	public static final @NonNull Idiom DEFAULT = new Idiom(SubIdiom.DEFAULT);
	public static final @NonNull Idiom DOUBLE_COLON = new Idiom(SubIdiom.DOUBLE_COLON);
	public static final @NonNull Idiom DOT_DOT = new Idiom(SubIdiom.DOT_DOT);
	public static final @NonNull Idiom PARENTHESES = new Idiom(SubIdiom.OPEN_PARENTHESIS, SubIdiom.CLOSE_PARENTHESIS);
	public static final @NonNull Idiom SEMI_COLON = new Idiom(SubIdiom.SEMI_COLON);
	public static final @NonNull Idiom SQUARES = new Idiom(SubIdiom.OPEN_SQUARE, SubIdiom.CLOSE_SQUARE);

	public static final @NonNull Idiom COMMENTED_RULE = new Idiom(SubIdiom.COMMENTED_RULE);

	public static final @NonNull Idiom INTER_CLASSSES = new DebugIdiom(SubIdiom.PackagesCS_ownedClasses);//, SubIdiom.PackagesCS_ownedClasses);

	public static final @NonNull Idiom @NonNull [] IDIOMS = new @NonNull Idiom[] { COMMENTED_RULE, BRACES, PARENTHESES, SQUARES, COMMA, DOUBLE_COLON, DOT_DOT, SEMI_COLON, INTER_CLASSSES, DEFAULT};

	protected final @NonNull SubIdiom @NonNull [] subIdioms;

	public Idiom(@NonNull SubIdiom @NonNull ... subIdioms) {
		this.subIdioms = subIdioms;
		assert subIdioms.length >= 1;
	}

	protected @NonNull IdiomMatch createIdiomMatch(@NonNull SerializationNode serializationNode) {
		return new IdiomMatch(this, serializationNode);
	}

	public @NonNull SubIdiom getSubidiom(int subIdiomIndex) {
		return subIdioms[subIdiomIndex];
	}

	public @NonNull SubIdiom @NonNull [] getSubIdioms() {
		return subIdioms;
	}

	public @Nullable IdiomMatch firstMatch(@NonNull SerializationNode serializationNode, @NonNull BasicSerializationRule serializationRule) {
		if (!subIdioms[0].matches(serializationNode, serializationRule)) {
			return null;
		}
		IdiomMatch idiomMatch = createIdiomMatch(serializationNode);
	//	idiomMatch.nextMatch(serializationNode, serializationRule);		// Opportunity for X ... X formatting
		return idiomMatch;
	}

	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append("{");
		boolean isFirst = true;
		for (@NonNull SubIdiom subIdiom: subIdioms) {
			if (!isFirst) {
				s.append(",");
			}
			s.append(subIdiom.toString());
			isFirst = false;
		}
		s.append("}");
		return s.toString();
	}
}
