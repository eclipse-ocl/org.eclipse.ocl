/*******************************************************************************
 * Copyright (c) 2013, 2019 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.codegen.java;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;

/**
 * Manage the mapping from long fully qualified class names to the short class names that may be used once an import has been provided.
 * Fully qualifued names are compressed to a form suitable for use as Java source text.
 */
public class JavaImportNameManager extends AbstractImportNameManager
{
	/**
	 * The short class name associated with a given long class name, null if the shortened class name is already used by some other class.
	 */
	protected final @NonNull Map<@NonNull String, @Nullable String> long2short = new HashMap<>();

	/**
	 * The long class name associated with a given short class name.
	 */
	protected final Map<@NonNull String, @NonNull String> short2longName = new HashMap<>();

	public JavaImportNameManager() {
		reserveImportNames();
	}

	/**
	 * Register the new fully qualified name of a class (optionally including a $suffix) and return the non-null
	 * text by which an optionally NonNUll/Nullable qualified class may be referenced within Java source.
	 */
	@Override
	public @NonNull String addImport(@Nullable Boolean isRequired, @NonNull String longName) {
		if (longName.indexOf('$') >= 0) {
			longName = longName.replace('$', '.');
		}
		if (isRequired == null) {
			String shortName = addImport(longName, true);
			return shortName != null ? shortName : longName;
		}
		String shortName = addImport(longName, false);
		String annotationName = addImport((isRequired ? NonNull.class : Nullable.class).getName(), true);
		StringBuilder s = new StringBuilder();
		if (shortName != null) {
			s.append("@");
			s.append(annotationName);
			s.append(" ");
			s.append(shortName);
		}
		else {
			int index = longName.lastIndexOf(".");
			if (index >= 0) {
				s.append(longName.substring(0, index+1));
				s.append("@");
			}
			s.append(annotationName);
			s.append(" ");
			if (index >= 0) {
				s.append(longName.substring(index+1));
			}
			else {
				s.append(longName);
			}
		}
		return s.toString();
	}

	protected @Nullable String addImport(@NonNull String newLongName, boolean keepConflicts) {
		int index = newLongName.lastIndexOf(".");
		String shortName = index >= 0 ? newLongName.substring(index+1) : newLongName;
		String oldLongName = short2longName.get(shortName);
		if (oldLongName == null) {							// New conflict-free class => allocate shortName
			long2short.put(newLongName, shortName);
			short2longName.put(shortName, newLongName);
			return shortName;
		}
		else {
			if (newLongName.equals(oldLongName)) {			// Long-name re-use => re-use shortName
				long2short.put(newLongName, shortName);		// -- ensure reserved name is known to be used
				return shortName;
			}
			else {											// New conflicting class => allocate 'null'
				if (keepConflicts) {
					long2short.put(newLongName, null);
				}
				return null;
			}
		}
	}

	@Override
	public @NonNull Map<@NonNull String, @Nullable String> getLong2ShortImportNames() {
		return long2short;
	}

	protected void reserveImportName(@NonNull Class<?> reservedClass) {
		@NonNull String shortName = reservedClass.getSimpleName();
		@NonNull String longName = reservedClass.getName();
		short2longName.put(shortName, longName);
	}

	/**
	 * Prepopulate the shortNames with some that are at best confusing if re-used for user-defined classes.
	 */
	protected void reserveImportNames() {
		reserveImportName(Class.class);
		reserveImportName(Iterable.class);
		reserveImportName(Iterator.class);
		reserveImportName(List.class);
		reserveImportName(Map.class);
		reserveImportName(NonNull.class);
		reserveImportName(Nullable.class);
		reserveImportName(Object.class);
		reserveImportName(Package.class);
		reserveImportName(Set.class);
	}
}
