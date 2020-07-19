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
import org.eclipse.ocl.xtext.base.cs2text.SerializationBuilder;

public class Idiom
{
	public static @NonNull Idiom CLOSE_BRACE = new Idiom(new @NonNull String[]{SerializationBuilder.POP,SerializationBuilder.SOFT_SPACE}, new @NonNull String[]{"\n"});
	public static @NonNull Idiom CLOSE_SQUARE = new Idiom((String[])null, (String[])null);
	public static @NonNull Idiom COMMA = new Idiom(null, SerializationBuilder.SOFT_SPACE);
	public static @NonNull Idiom DEFAULT = new Idiom(SerializationBuilder.SOFT_SPACE, SerializationBuilder.SOFT_SPACE);
	public static @NonNull Idiom DOUBLE_COLON = new Idiom((String[])null, (String[])null);
	public static @NonNull Idiom DOT_DOT = new Idiom((String[])null, (String[])null);
	public static @NonNull Idiom OPEN_BRACE = new Idiom(new @NonNull String[]{SerializationBuilder.SOFT_SPACE}, new @NonNull String[]{SerializationBuilder.PUSH,"\n"});
	public static @NonNull Idiom OPEN_SQUARE = new Idiom((String[])null, (String[])null);
	public static @NonNull Idiom SEMI_COLON = new Idiom(null, "\n");

	protected final @NonNull String @Nullable [] prefixes;
	protected final @NonNull String @Nullable [] suffixes;

	public Idiom(@Nullable String prefix, @Nullable String suffix) {
		this.prefixes = prefix != null ? new @NonNull String[] {prefix} : null;
		this.suffixes = suffix != null ? new @NonNull String[] {suffix} : null;
	}

	public Idiom(@NonNull String @Nullable [] prefixes, @NonNull String @Nullable [] suffixes) {
		this.prefixes = prefixes;
		this.suffixes = suffixes;
	}

	public void serialize(@NonNull String value, @NonNull SerializationBuilder serializationBuilder) {
		if (prefixes != null) {
			for (@NonNull String prefix : prefixes) {
				serializationBuilder.append(prefix);
			}
		}
		serializationBuilder.append(value);
		if (suffixes != null) {
			for (@NonNull String suffix : suffixes) {
				serializationBuilder.append(suffix);
			}
		}
	}

}
