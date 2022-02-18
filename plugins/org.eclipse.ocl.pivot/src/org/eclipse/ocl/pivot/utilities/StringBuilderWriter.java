/*******************************************************************************
 * Copyright (c) 2022 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.utilities;

import java.io.IOException;
import java.io.Writer;

import org.eclipse.jdt.annotation.NonNull;

/**
 * A character stream that delegates its output to a StringBuilder.
 *
 * @since 1.18
 */
public class StringBuilderWriter extends Writer
{
	private @NonNull StringBuilder s;

	public StringBuilderWriter(@NonNull StringBuilder s) {
		this.s = s;
		lock = s;
	}

	@Override
	public @NonNull StringBuilderWriter append(CharSequence csq) {
		s.append(csq);
		return this;
	}

	@Override
	public @NonNull StringBuilderWriter append(CharSequence csq, int start, int end) {
		s.append(csq, start, end);
		return this;
	}

	@Override
	public @NonNull StringBuilderWriter append(char c) {
		write(c);
		return this;
	}

	@Override
	public void close()	throws IOException {
		//	s.close();
	}

	@Override
	public void flush() {
		// s.flush();
	}

	/**
	 * Return the underlying StringBuilder.
	 */
	public @NonNull StringBuilder getStringBuilder() {
		return s;
	}

	/**
	 * Return the buffer's current value as a string.
	 */
	@Override
	public @NonNull String toString() {
		return s.toString();
	}

	@Override
	public void write(int c) {
		s.append((char) c);
	}

	@Override
	public void write(char cbuf[], int off, int len) {
		s.append(cbuf, off, len);
	}

	@Override
	public void write(String str) {
		s.append(str);
	}

	@Override
	public void write(String str, int off, int len) {
		s.append(str, off, len);
	}
}
