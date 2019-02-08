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
package org.eclipse.ocl.examples.codegen.oclinecore;

import java.lang.annotation.Annotation;
import java.util.Collections;
import java.util.Map;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.genmodel.OCLGenModelUtil;
import org.eclipse.ocl.examples.codegen.java.AbstractImportNameManager;
import org.eclipse.ocl.examples.codegen.java.ImportUtils;

/**
 * Manage the mapping from long fully qualified class names to the short class names that may be used once an import has been provided.
 * Fully qualifued names are compressed to a form suitable for use as Java source text embedded with GenModel source text.
 */
public class OCLinEcoreImportNameManager extends AbstractImportNameManager
{
	/**
	 * Register the new fully qualified name of a class (optionally including a $suffix) and return the non-null
	 * text by which an optionally NonNull/Nullable qualified class may be referenced within a genmodel.
	 */
	@Override
	public @NonNull String addImport(@Nullable Boolean isRequired, @NonNull String className) {
		if (className.indexOf('$') >= 0) {
			className = className.replace('$', '.');
		}
		StringBuilder s = new StringBuilder();
		s.append(ImportUtils.IMPORTS_PREFIX);
		if (isRequired == null) {
			s.append(className);
		}
		else {
			if (Object.class.getName().equals(className)) {
				className = "Object";			// Is this still needed ?
			}
			Class<? extends Annotation> annotationClass = isRequired ? NonNull.class : Nullable.class;
			String annotationClassName = annotationClass.getName();
			assert annotationClassName != null;
			int index = className.lastIndexOf(".");
			if (index >= 0) {
				s.append(className.substring(0, index+1));
			}
			if (OCLGenModelUtil.INSTANCE.useNestedImports()) {
				s.append(ImportUtils.IMPORTS_PREFIX);
				s.append("@");
				s.append(ImportUtils.IMPORTS_PREFIX);
				s.append(annotationClass.getName());
				s.append(ImportUtils.IMPORTS_SUFFIX);
				s.append(" ");
				s.append(ImportUtils.IMPORTS_SUFFIX);
			}
			else {
				s.append("@");
				s.append(annotationClass.getName());
				s.append(" ");
			}
			if (index < 0) {
				s.append(className);
			}
			else {
				s.append(className.substring(index+1));
			}
		}
		s.append(ImportUtils.IMPORTS_SUFFIX);
		return s.toString();
	}

	@Override
	public @NonNull Map<@NonNull String, @Nullable String> getLong2ShortImportNames() {
		return Collections.emptyMap();
	}
}
