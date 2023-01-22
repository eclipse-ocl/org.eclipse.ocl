/*******************************************************************************
 * Copyright (c) 2013, 2022 CEA LIST and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink(CEA LIST) - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.codegen.java;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.codegen.util.ImportManager;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;

public class ImportUtils
{
	public static final String IMPORTS_MARKER = "<%**imports**%>";
	public static final String IMPORTS_PREFIX = "<%";
	public static final String IMPORTS_SUFFIX = "%>";
	public static final String IMPORTS_NESTED_ANNOTATION_PREFIX = IMPORTS_PREFIX + "@" + IMPORTS_PREFIX;
	public static final String IMPORTS_NESTED_ANNOTATION_SUFFIX = IMPORTS_SUFFIX + " " + IMPORTS_SUFFIX;

	public static @NonNull String getAffixedName(@NonNull Class<?> javaClass) {
		return IMPORTS_PREFIX + javaClass.getName() + IMPORTS_SUFFIX;
	}

	public static @NonNull String getAffixedName(@NonNull String className) {
		assert !className.contains("@");
		return IMPORTS_PREFIX + className + IMPORTS_SUFFIX;
	}

	// FIXME This is a very heavyweight solution for the simple single insert Java case
	public static @NonNull String resolveImports(@NonNull String source, @NonNull Map<@NonNull String, @Nullable String> long2short, boolean skipStartMarker)
	{
		StringBuilder s = new StringBuilder();
		int iStart = 0;
		int iMax = source.length();
		if (!skipStartMarker) {
			iStart = source.indexOf(IMPORTS_MARKER);
			if (iStart < 0) {
				return source;
			}
			s.append(source, 0, iStart);
			iStart += IMPORTS_MARKER.length();
			List<String> sortedImports = new ArrayList<String>(long2short.keySet());
			Collections.sort(sortedImports);
			for (String anImport : sortedImports) {
				String shortname = long2short.get(anImport);
				if ((shortname != null) && (!anImport.startsWith("java.lang.") || !anImport.equals("java.lang." + shortname))) {
					s.append("import " + anImport +";\n");
				}
			}
		}
		while (true) {
			int iPrefix = source.indexOf(IMPORTS_PREFIX, iStart);
			if (iPrefix < 0) {
				break;
			}
			int iSuffix = source.indexOf(IMPORTS_SUFFIX, iPrefix);
			if (iSuffix < 0) {
				break;
			}
			s.append(source, iStart, iPrefix);
			String longName = source.substring(iPrefix+IMPORTS_PREFIX.length(), iSuffix);


			String annotatedName = longName;
			//			@Nullable String longAnnotationName = null;
			//			@Nullable String longTypeName = annotatedName;
			int startIndex = annotatedName.indexOf("@");
			int endIndex = annotatedName.indexOf(" ");
			if ((0 <= startIndex) && (startIndex < endIndex)) {
				//				longTypeName = annotatedName.substring(0, startIndex) + annotatedName.substring(endIndex).trim();
				//				longAnnotationName = annotatedName.substring(startIndex+1, endIndex).trim();
				//				if (importManager != null) {
				//					importManager.addImport(longAnnotationName);
				//				}
			}
			//			if (importManager != null) {
			//				importManager.addImport(longTypeName);
			///			}
			/*			String shortTypeName = importManager != null ? importManager.getImportedName(longTypeName) : null;
			String shortAnnotationName = (longAnnotationName != null) && (importManager != null) ? importManager.getImportedName(longAnnotationName) : null;
			if (longAnnotationName == null) {
				s.append(IMPORTS_PREFIX);
				s.append(shortTypeName != null ? shortTypeName : longTypeName);
				s.append(IMPORTS_SUFFIX);
			}
			else if ((shortTypeName != null) && !shortTypeName.equals(longTypeName)) {
				s.append("@");
				s.append(IMPORTS_PREFIX);
				s.append(longAnnotationName);
				s.append(IMPORTS_SUFFIX);
				s.append(" ");
				s.append(shortTypeName);
			}
			else {
				s.append(annotatedName.substring(0, startIndex));
				s.append("@");
				s.append(IMPORTS_PREFIX);
				s.append(longAnnotationName);
				s.append(IMPORTS_SUFFIX);
				s.append(" ");
				s.append(annotatedName.substring(endIndex).trim());
			} */





			String shortname = long2short.get(longName);
			s.append(shortname != null ? shortname : longName);
			iStart = iSuffix + IMPORTS_SUFFIX.length();
		}
		s.append(source, iStart, iMax);
		return s.toString();
	}

	/**
	 * Rewrite double imports to suit the EMF generators. If importManager is null, as is the case
	 * since it is not obvious how to re-use the ImportManager between the OCL pre-generate and the Ecore generate
	 * sessions, an import such as <%x.y.@p.q z%> is changed to x.y.@<%p.q%> z so that the @p.q gets handled by
	 * the Ecore ImportManager. If importManager is non-null both imports are shortened.
	 */
	public static @NonNull String rewriteManagedImports(@NonNull String source, @Nullable ImportManager importManager)
	{
		int iMax = source.length();
		int iStart = 0;
		StringBuilder s = new StringBuilder();
		while (true) {
			int iPrefix = source.indexOf(IMPORTS_PREFIX, iStart);
			if (iPrefix < 0) {
				break;
			}
			int iSuffix = source.indexOf(IMPORTS_SUFFIX, iPrefix);
			if (iSuffix < 0) {
				break;
			}
			s.append(source, iStart, iPrefix);
			String annotatedName = source.substring(iPrefix+IMPORTS_PREFIX.length(), iSuffix);
			String longAnnotationName = null;
			String longTypeName = annotatedName;
			int startIndex = annotatedName.indexOf("@");
			int endIndex = annotatedName.indexOf(" ");
			if ((0 <= startIndex) && (startIndex < endIndex)) {
				longTypeName = annotatedName.substring(0, startIndex) + annotatedName.substring(endIndex).trim();
				longAnnotationName = annotatedName.substring(startIndex+1, endIndex).trim();
				if (importManager != null) {
					importManager.addImport(longAnnotationName);
				}
			}
			if (importManager != null) {
				importManager.addImport(longTypeName);
			}
			String shortTypeName = importManager != null ? importManager.getImportedName(longTypeName) : null;
			//			String shortAnnotationName = (longAnnotationName != null) && (importManager != null) ? importManager.getImportedName(longAnnotationName) : null;
			if (longAnnotationName == null) {
				s.append(IMPORTS_PREFIX);
				s.append(shortTypeName != null ? shortTypeName : longTypeName);
				s.append(IMPORTS_SUFFIX);
			}
			else if ((shortTypeName != null) && !shortTypeName.equals(longTypeName)) {
				s.append("@");
				s.append(IMPORTS_PREFIX);
				s.append(longAnnotationName);
				s.append(IMPORTS_SUFFIX);
				s.append(" ");
				s.append(shortTypeName);
			}
			else {
				s.append(annotatedName.substring(0, startIndex));
				s.append("@");
				s.append(IMPORTS_PREFIX);
				s.append(longAnnotationName);
				s.append(IMPORTS_SUFFIX);
				s.append(" ");
				s.append(annotatedName.substring(endIndex).trim());
			}
			iStart = iSuffix + IMPORTS_SUFFIX.length();
		}
		s.append(source, iStart, iMax);
		return s.toString();
	}
}
