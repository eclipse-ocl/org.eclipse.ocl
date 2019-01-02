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
	 * text by which an optionally NonNull/Nullable qualified class may be referenced within Java source.
	 */
	@Override
	public @NonNull String addImport(@Nullable Boolean isRequired, @NonNull String fullyQualifiedClassName) {
		String dollarPrefix = fullyQualifiedClassName;
		String dollarSuffix = "";
		int dollarIndex = fullyQualifiedClassName.indexOf('$');
		if (dollarIndex >= 0) {
			dollarPrefix = fullyQualifiedClassName.substring(0, dollarIndex);
			dollarSuffix = fullyQualifiedClassName.substring(dollarIndex+1, fullyQualifiedClassName.length()).replace('$',  '.');
		}
		String shortName = addImport(dollarPrefix);
		String importName = (shortName != null ? shortName : dollarPrefix) + (dollarSuffix .length() > 0 ? "." + dollarSuffix : "");
		if (isRequired == null) {
			return importName;
		}
		String annotationName = addImport((isRequired ? NonNull.class : Nullable.class).getName());
		String dotPrefix = "";
		String dotSuffix = importName;
		int dotIndex = importName.lastIndexOf('.');
		if (dotIndex >= 0) {
			dotPrefix = importName.substring(0, dotIndex);
			dotSuffix = importName.substring(dotIndex+1, importName.length());
		}
		StringBuilder s = new StringBuilder();
		if (dotPrefix.length() > 0) {
			s.append(dotPrefix);
			s.append(".");
		}
		s.append("@");
		s.append(annotationName);
		s.append(" ");
		s.append(dotSuffix);
		return s.toString();
	}

	/**
	 * Reserve and return the short class name for the oot-separate newLongName.
	 * Returns null if no short name can be allocated - reserved for a primitive/important class or another user class.
	 */
	private @Nullable String addImport(@NonNull String newLongName) {
		int index = newLongName.lastIndexOf(".");
		String shortName = index >= 0 ? newLongName.substring(index+1) : newLongName;
		String oldLongName = short2longName.get(shortName);
		if (oldLongName == null) {							// New conflict-free class => allocate shortName
			long2short.put(newLongName, shortName);
			short2longName.put(shortName, newLongName);
			return shortName;
		}
		else if (newLongName.equals(shortName) && (long2short.get(newLongName) == null)) {	// Matching primitive/reserved name
			return shortName;								//  avoid a long2short key that would lead to a real import
		}
		else if (newLongName.equals(oldLongName)) {			// Long-name re-use => re-use shortName
			long2short.put(newLongName, shortName);			// -- ensure reserved name is known to be used
			return shortName;
		}
		else {												// New conflicting class => just return null
			return null;
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
	 * Prepopulate the shortNames with some that are confusing or worse if re-used for user-defined classes.
	 */
	protected void reserveImportNames() {
		reserveImportName(Byte.class);
		reserveImportName(Character.class);
		reserveImportName(Class.class);
		reserveImportName(Double.class);
		reserveImportName(Enum.class);
		reserveImportName(Error.class);
		reserveImportName(Exception.class);
		reserveImportName(Float.class);
		reserveImportName(Integer.class);
		reserveImportName(Iterable.class);
		reserveImportName(Iterator.class);
		reserveImportName(List.class);
		reserveImportName(Long.class);
		reserveImportName(Map.class);
		reserveImportName(Math.class);
		reserveImportName(NonNull.class);
		reserveImportName(Nullable.class);
		reserveImportName(Object.class);
		reserveImportName(Package.class);
		reserveImportName(Process.class);
		reserveImportName(Set.class);
		reserveImportName(Short.class);
		reserveImportName(String.class);
		reserveImportName(byte.class);
		reserveImportName(char.class);
		reserveImportName(double.class);
		reserveImportName(float.class);
		reserveImportName(int.class);
		reserveImportName(long.class);
		reserveImportName(short.class);
	}
}
