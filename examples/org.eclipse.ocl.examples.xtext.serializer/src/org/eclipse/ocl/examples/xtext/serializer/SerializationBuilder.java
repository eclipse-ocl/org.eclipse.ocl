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
package org.eclipse.ocl.examples.xtext.serializer;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.xtext.serializer.ToDebugString.ToDebugStringable;

/**
 * SerializationBuilder builds the intermediate serialization as an interleaving of concrete strings and virtual
 * characters such as soft-space. The append merthods do the building.
 *
 * Finally the toString returns a simple string with the virtual characters converted to concrete equivalents where
 * appropriate.
 */
public class SerializationBuilder implements ToDebugStringable
{
	/**
	 * The virtual character/string to start a new line, but pairs of half new lines generate one
	 * rather than two new lines.
	 */
	public static final @NonNull String HALF_NEW_LINE = new String("«½\\n»");

	/**
	 * The virtual character/string to start a new line.
	 */
	public static final @NonNull String NEW_LINE = new String("«\\n»");

	/**
	 * The virtual character/string to suppress soft space separation.
	 */
	public static final @NonNull String NO_SPACE = new String("«! »");

	/**
	 * The virtual character/string to pop the most recent indenttatio push.
	 */
	public static final @NonNull String POP = new String("«-»");

	/**
	 * The virtual character/string to push a standard indentation on the stack.
	 */
	public static final @NonNull String PUSH = new String("«+»");

	/**
	 * The virtual character/string to push the next string as indentation on the stack.
	 */
	public static final @NonNull String PUSH_NEXT = new String("«+?»");

	/**
	 * The virtual character/string to start a new line,  but avoid multiple new lines.
	 */
	public static final @NonNull String SOFT_NEW_LINE = new String("«?\\n»");

	/**
	 * The virtual character/string to ensure space separation but avoid redundant spacing.
	 */
	public static final @NonNull String SOFT_SPACE = new String("«? »");

	/**
	 * The virtual character/string to mark the beginning of a wrap region within which either
	 * all or no wrap-here preferred wraappimg points are exploited,
	 */
	public static final @NonNull String WRAP_BEGIN_ALL = new String("«+w*»");

	/**
	 * The virtual character/string to mark the beginning of a wrap region within which any
	 * all or no wrap-here preferred wraapping points are exploited,
	 */
	public static final @NonNull String WRAP_BEGIN_SOME = new String("«+w?»");

	/**
	 * The virtual character/string to mark the end of a wrap region.
	 */
	public static final @NonNull String WRAP_END = new String("«-w»");

	/**
	 * The virtual character/string to mark a preferred wrapping pont.
	 */
	public static final @NonNull String WRAP_HERE = new String("«w»");

	/**
	 * The virtual character/string to mark the indentation anchor/reference for subsequent wraps.
	 */
	public static final @NonNull String WRAP_ANCHOR = new String("«wa»");

	/**
	 * An AbstractContext identifies (the left edge of) a significant character location in the
	 * indented but not yet wrapped output text.
	 */
	protected static abstract class AbstractContext
	{
		protected final int line;
		protected final int column;
		protected final int offset;

		protected AbstractContext(@NonNull WrappingStringBuilder s) {
			this.line = s.getLine();
			this.column = s.getColumn();
			this.offset = s.getOffset();
		}

		@Override
		public @NonNull String toString() {
			return line + ":" + column + ":" + offset;
		}
	}

	/**
	 * An AnchorContext identifies (the left edge of) text that provides a column number to be
	 * matched in subsequent wrapped text.
	 */
	protected static class AnchorContext extends AbstractContext
	{
		public AnchorContext(@NonNull WrappingStringBuilder s) {
			super(s);
		}

		@Override
		public @NonNull String toString() {
			return "Anchor " + super.toString();
		}
	}

	/**
	 * An EndContext identifies (the left edge of) the first character that follows a RegionContext.
	 */
	protected static class EndContext extends AbstractContext
	{
		public EndContext(@NonNull WrappingStringBuilder s) {
			super(s);
		}

		@Override
		public @NonNull String toString() {
			return "End " + super.toString();
		}
	}

	/**
	 * A HereContext identifies (the left edge of) text that may be preceded by a wrapping new line
	 * and compensating indentation..
	 */
	protected static class HereContext extends AbstractContext
	{
		public HereContext(@NonNull WrappingStringBuilder s) {
			super(s);
		}

		@Override
		public @NonNull String toString() {
			return "Here " + super.toString();
		}
	}

	/**
	 * An IndentEndContext identifies (the left edge of) text that follows the new line indentation.
	 */
	protected static class IndentedContext extends AbstractContext
	{
		public IndentedContext(@NonNull WrappingStringBuilder s) {
			super(s);
		}

		@Override
		public @NonNull String toString() {
			return "Indented " + super.toString();
		}
	}

	/**
	 * An IndentStartContext identifies (the left edge of) a hard new line.
	 */
	protected static class NewLineContext extends AbstractContext
	{
		public NewLineContext(@NonNull WrappingStringBuilder s) {
			super(s);
		}

		@Override
		public @NonNull String toString() {
			return "NewLine " + super.toString();
		}
	}

	/**
	 * A RegionContext identifies (the left edge of) a wrapping region within which wrap-here
	 * markers contol mapping with respect to wrpa-anchor contexts.
	 */
	protected static abstract class RegionContext extends AbstractContext
	{
		private @NonNull List<@NonNull AbstractContext> childContexts = new ArrayList<>();

		protected RegionContext(@NonNull WrappingStringBuilder s) {
			super(s);
		}

		public void addContext(@NonNull AbstractContext childContext) {
			childContexts.add(childContext);
		}

		public abstract @NonNull RegionContext getParentContext();

		/**
		 * Return the maximum number of columns to render this region and all its child regions
		 * using only the the as-is unwrapped indented output.
		 */
		public int getMaximumRequiredColumns() {
			int mostRequiredColumns = 0;
			for (@NonNull AbstractContext childContext : childContexts) {
				if (childContext instanceof EndContext) {
					EndContext endContext = ((EndContext)childContext);
					int requiredColumns = endContext.column;
					if (requiredColumns > mostRequiredColumns) {
						mostRequiredColumns = requiredColumns;
					}
				}
				else if (childContext instanceof NewLineContext) {
					NewLineContext newLineContext = ((NewLineContext)childContext);
					int requiredColumns = newLineContext.column;
					if (requiredColumns > mostRequiredColumns) {
						mostRequiredColumns = requiredColumns;
					}
				}
				else if (childContext instanceof RegionContext) {
					RegionContext regionContext = ((RegionContext)childContext);
					int requiredColumns = regionContext.getMaximumRequiredColumns();
					if (requiredColumns > mostRequiredColumns) {
						mostRequiredColumns = requiredColumns;
					}
				}
			}
			return mostRequiredColumns;
		}

		/**
		 * Return the minimum number of columns to render this region and all its child regions
		 * using only the declared wrapping capabilities; i.e. without using force majeur to
		 * break at arbitrary spaces.
		 */
		public int getMinimumRequiredColumns() {
			int nextLine = line;
			int nextColumn = column;
			int anchorColumns = 0;
			int mostRequiredColumns = 0;
			for (@NonNull AbstractContext childContext : childContexts) {
				if (childContext instanceof AnchorContext) {
					AnchorContext anchorContext = ((AnchorContext)childContext);
					assert anchorContext.line == nextLine;
					anchorColumns = anchorContext.column - nextColumn;
					assert anchorColumns >= 0;
					nextLine = anchorContext.line;
					nextColumn = anchorContext.column;
				}
				else if (childContext instanceof EndContext) {
					EndContext endContext = ((EndContext)childContext);
					assert endContext.line == nextLine;
					int wrappedColumns = endContext.column - nextColumn;
					assert wrappedColumns >= 0;
					int requiredColumns = anchorColumns + wrappedColumns;
					if (requiredColumns > mostRequiredColumns) {
						mostRequiredColumns = requiredColumns;
					}
				}
				else if (childContext instanceof HereContext) {
					HereContext hereContext = ((HereContext)childContext);
					assert hereContext.line == nextLine;
					nextLine = hereContext.line;
					nextColumn = hereContext.column;
				}
				else if (childContext instanceof IndentedContext) {
					IndentedContext indentedContext = ((IndentedContext)childContext);
					assert indentedContext.line == nextLine;
					nextLine = indentedContext.line;
					nextColumn = indentedContext.column;
				}
				else if (childContext instanceof NewLineContext) {
					NewLineContext newLineContext = ((NewLineContext)childContext);
					if (newLineContext.column == 0) {					// Blank line may omit IndentedContext
						assert newLineContext.line == nextLine;
						nextLine = newLineContext.line + 1;
						nextColumn = 0;
					}
					else {
						assert newLineContext.line == nextLine;
						nextLine = newLineContext.line + 1;
						int wrappedColumns = newLineContext.column - nextColumn;
						assert wrappedColumns >= 0;
						int requiredColumns = anchorColumns + wrappedColumns;
						if (requiredColumns > mostRequiredColumns) {
							mostRequiredColumns = requiredColumns;
						}
					}
				}
				else if (childContext instanceof RegionContext) {
					RegionContext regionContext = ((RegionContext)childContext);
					assert regionContext.line == nextLine;
					int wrappedColumns = regionContext.column + regionContext.getMinimumRequiredColumns() - nextColumn;
					assert wrappedColumns >= 0;
					int requiredColumns = anchorColumns + wrappedColumns;
					if (requiredColumns > mostRequiredColumns) {
						mostRequiredColumns = requiredColumns;
					}
				}
				else {
					throw new UnsupportedOperationException();
				}
			}
			return mostRequiredColumns;
		}
	}

	/**
	 * The RootContext identifies (the left edge of) the root/orphan wrapping region starting at
	 * line 0, column 0, offset 0 and ending at the end of the output.
	 */
	protected static class RootContext extends RegionContext
	{
		public RootContext(@NonNull WrappingStringBuilder s) {
			super(s);
		}

		@Override
		public @NonNull RegionContext getParentContext() {
			throw new IllegalStateException();
		}

		@Override
		public @NonNull String toString() {
			return "Root " + super.toString();
		}
	}

	/**
	 * A WrappingContext identifies (the left edge of) a nested wrapping region.
	 */
	protected static class WrappingContext extends RegionContext
	{
		protected final boolean isAllOrNone;
		private final @NonNull RegionContext parentContext;

		protected WrappingContext(@NonNull RegionContext parentContext, @NonNull WrappingStringBuilder s, boolean isAllOrNone) {
			super(s);
			this.isAllOrNone = isAllOrNone;
			this.parentContext = parentContext;
		}

		@Override
		public @NonNull RegionContext getParentContext() {
			return parentContext;
		}

		@Override
		public @NonNull String toString() {
			return "Wrapped " + super.toString();
		}
	}

	protected static class WrappingStringBuilder
	{
		private final @NonNull StringBuilder s;

		/**
		 * The maximum number of characters (excluding new line characters) per line. Less than 1 for no limit.
		 */
		protected final int lineLength;

		/**
		 * The number of spaces between tab columns; typically 8..
		 */
		protected final int tabWidth;

		/**
		 * The line number for the next character - line 0 is first.
		 */
		private int nextLine = 0;

		/**
		 * The column number for the next character - column 0 is first.
		 */
		private int nextColumn = 0;

		private final @NonNull RootContext rootContext;
		private @NonNull RegionContext currentContext;

		public WrappingStringBuilder(@NonNull StringBuilder s, int lineLength, int tabWidth) {
			this.s = s;
			this.lineLength = lineLength;
			this.tabWidth = tabWidth;
			this.rootContext = new RootContext(this);
			this.currentContext = rootContext;
		}

		public void append(@Nullable String string) {
			if (string != null) {
				if (WRAP_ANCHOR.equals(string)) {
					currentContext.addContext(new AnchorContext(this));
				}
				else if (WRAP_BEGIN_ALL.equals(string)) {
					WrappingContext childContext = new WrappingContext(currentContext, this, true);
					currentContext.addContext(childContext);
					currentContext = childContext;
				}
				else if (WRAP_BEGIN_SOME.equals(string)) {
					WrappingContext childContext = new WrappingContext(currentContext, this, false);
					currentContext.addContext(childContext);
					currentContext = childContext;
				}
				else if (WRAP_END.equals(string)) {
					currentContext.addContext(new EndContext(this));
					currentContext = currentContext.getParentContext();
				}
				else if (WRAP_HERE.equals(string)) {
					currentContext.addContext(new HereContext(this));
				}
				else {
					for (int i = 0; i < string.length(); i++) {
						char c = string.charAt(i);
						s.append(c);
						if (c == '\n') {
							nextColumn = 0;
							nextLine++;
						}
						else if (c == '\r') {
							// ignore
						}
						else if (c == '\t') {
							nextColumn = ((nextColumn % tabWidth) + 1) * tabWidth;
						}
						else {
							nextColumn++;
						}
					}
				}
			}
		}

		public void appendIndent(@NonNull Stack<@NonNull String> indents) {
			int size = indents.size();
			assert size > 0;
			for (int i = 0; i < size; i++) {
				append(indents.get(i));
			}
			currentContext.addContext(new IndentedContext(this));
		}

		public void appendNewLine() {
			currentContext.addContext(new NewLineContext(this));
		}

		public void close() {
			int maximumRequiredColumns = rootContext.getMaximumRequiredColumns();
			int minimumRequiredColumns = rootContext.getMinimumRequiredColumns();
			if (maximumRequiredColumns > lineLength) {
				getClass();		// XXX
			}
		}

		public int getColumn() {
			return nextColumn;
		}

		public int getLine() {
			return nextLine;
		}

		public int getOffset() {
			return s.length();
		}

		@Override
		public @NonNull String toString() {
			@SuppressWarnings("null")
			@NonNull String castString = (@NonNull String)String.valueOf(s);
			return castString;
		}
	}

	protected static class IndentingStringBuilder
	{

		/**
		 * The string for a new-line; typically \n unless \r\n or \n\r really wanted.
		 */
		protected final @NonNull String newLineString;

		/**
		 * The string for an additional level of indentation; typically four spaces.
		 */
		protected final @NonNull String indentString;

		/**
		 * The strings to be concatenated as indentation following a new line.
		 */
		private @NonNull Stack<@NonNull String> indents = new Stack<>();

		/**
		 * True if a new-line has been output and not followed by anything else.
		 */
		private boolean atStartOfLine = true;

		private final @NonNull WrappingStringBuilder s;

		public IndentingStringBuilder(@NonNull WrappingStringBuilder s, @NonNull String newLineString, @NonNull String indentString) {
			this.s = s;
			this.newLineString = newLineString;
			this.indentString = indentString;
		}

		public void append(@NonNull String string) {
			s.append(string);
			if (atStartOfLine && !string.isEmpty()) {
				atStartOfLine = false;
			}
		}

		protected String appendNewLine() {
			s.appendNewLine();
			append(newLineString);
			atStartOfLine = true;
			return NO_SPACE;
		}

		protected String appendString(@NonNull String string) {
			if (atStartOfLine && !string.isEmpty() && !indents.isEmpty()) {
				s.appendIndent(indents);
			}
			append(string);
			return null;
		}

		public void close() {
			if (!atStartOfLine) {
				appendNewLine();
			}
			s.close();
		}

		public void pop() {
			indents.pop();
		}

		public void push(@NonNull String indentString) {
			indents.push(indentString);
		}

		@Override
		public @NonNull String toString() {
			@SuppressWarnings("null")
			@NonNull String castString = (@NonNull String)String.valueOf(s);
			return castString;
		}
	}

	/**
	 * The string for a new-line; typically \n unless \r\n or \n\r really wanted.
	 */
	protected final @NonNull String newLineString;

	/**
	 * The string for an additional level of indentation; typically four spaces.
	 */
	protected final @NonNull String indentString;

	/**
	 * The maximum number of characters (excluding new line characters) per line. Less than 1 for no limit.
	 */
	protected final int lineLength;

	/**
	 * The number of spaces between tab columns; typically 8..
	 */
	protected final int tabWidth;

	/**
	 * The strings to be rendered as output after resolving virtual character strings such as SOFT_SPACE.
	 */
	protected final @NonNull List<@NonNull String> strings = new ArrayList<>(1000);

	/**
	 * Errors embedded in the output and also here.
	 */
	private @Nullable List<@NonNull String> errors = null;

	@SuppressWarnings("unused")		// Used to obtain a raw debug representation
	private @NonNull ToDebugString toDebugString = new ToDebugString(this);

	public SerializationBuilder() {
		this("\n", "    ");
	}

	public SerializationBuilder(@NonNull String newLineString, @NonNull String indentString) {
		this(newLineString, indentString, 80, 8);
	}

	public SerializationBuilder(@NonNull String newLineString, @NonNull String indentString, int lineLength, int tabWidth) {
		this.newLineString = newLineString;
		this.indentString = indentString;
		this.lineLength = lineLength;
		this.tabWidth = tabWidth;
	}

	public void append(@Nullable String string) {
		if (string != null) {
			assert string.indexOf('\n') < 0;
			strings.add(string);
		}
	}

	public void appendError(@NonNull String string) {
		List<@NonNull String> errors2 = errors;
		if (errors2 == null) {
			errors = errors2 = new ArrayList<>();
		}
		errors2.add(string);
		append(string);
	}

	public @Nullable Iterable<@NonNull String> getErrors() {
		return errors;
	}

	@Override
	public void toDebugString(@NonNull StringBuilder s, int depth) {
		for (@NonNull String string : strings) {
			s.append(string);
			if ((string == NEW_LINE) || (string == HALF_NEW_LINE) || (string == SOFT_NEW_LINE)) {
				s.append("\n");
			}
		}
	}

	@Override
	public @NonNull String toString() {
		WrappingStringBuilder sw = new WrappingStringBuilder(new StringBuilder(), lineLength, tabWidth);
		IndentingStringBuilder s = new IndentingStringBuilder(sw, newLineString, indentString);
		@Nullable String pendingString = NO_SPACE;
		final int indexMax = strings.size();
		for (int index = 0; index < indexMax; ) {
			@NonNull String nextString = strings.get(index++);
			if (nextString == PUSH) {
				s.push(indentString);
			}
			else if (nextString == PUSH_NEXT) {
				if (index < indexMax) {
					s.push(strings.get(index++));
				}
			}
			else if (nextString == POP) {
				s.pop();
			}
			else if (nextString == NEW_LINE) {
				pendingString = s.appendNewLine();
			}
			else if (nextString == HALF_NEW_LINE) {
				if ((pendingString == null) || (pendingString == NO_SPACE)) {
					pendingString = HALF_NEW_LINE;
				}
				else if ((pendingString == SOFT_NEW_LINE) || (pendingString == HALF_NEW_LINE)) {
					s.appendNewLine();
					pendingString = NO_SPACE;
				}
			}
			else if (nextString == SOFT_NEW_LINE) {
				if ((pendingString == null) || (pendingString == NO_SPACE) || (pendingString == SOFT_SPACE)) {
					pendingString = SOFT_NEW_LINE;
				}
			}
			else if (nextString == SOFT_SPACE) {
				if (pendingString == null) {
					pendingString = SOFT_SPACE;
				}
				else if (pendingString == SOFT_NEW_LINE) {
					pendingString = s.appendNewLine();
				}
			}
			else if (nextString == NO_SPACE) {
				if ((pendingString == null) || (pendingString == SOFT_SPACE)) {
					pendingString = NO_SPACE;
				}
				else  if (pendingString == SOFT_NEW_LINE) {
					pendingString = s.appendNewLine();
				}
			}
			else {	// nextString - hard text
				if (pendingString == SOFT_NEW_LINE) {
					pendingString = s.appendNewLine();
				}
				else if (pendingString == SOFT_SPACE) {
					s.appendString(" ");
				}
				pendingString = s.appendString(nextString);
			}
		}
		s.close();
		return s.toString();
	}
}