/*******************************************************************************
 * Copyright (c) 2010, 2020 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 * E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.utilities;

import java.util.Comparator;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.internal.utilities.PivotConstantsInternal;
import org.eclipse.ocl.pivot.util.DerivedConstants;

public class AnnotationUtil
{
	public static final class EAnnotationComparator implements Comparator<EAnnotation>
	{
		public static final @NonNull EAnnotationComparator INSTANCE = new EAnnotationComparator();

		@Override
		public int compare(EAnnotation o1, EAnnotation o2) {
			String n1 = o1.getSource();
			String n2 = o2.getSource();
			return ClassUtil.safeCompareTo(n1, n2);
		}
	}

/*	public static boolean hasDocumentationKey(@Nullable String source, @NonNull EMap<String, String> details) {
		return (details.size() == 1) && PivotConstantsInternal.DOCUMENTATION_ANNOTATION_SOURCE.equals(source)
				&& details.containsKey(PivotConstantsInternal.DOCUMENTATION_ANNOTATION_KEY);
	} */

	public static boolean isDocumentationKey(@Nullable String source, @Nullable String key) {
		return PivotConstantsInternal.DOCUMENTATION_ANNOTATION_SOURCE.equals(source)
				&& PivotConstantsInternal.DOCUMENTATION_ANNOTATION_KEY.equals(key);
	}

	public static boolean isGenModelSource(@Nullable String source) {
		return PivotConstantsInternal.DOCUMENTATION_ANNOTATION_SOURCE.equals(source);
	}

//	public static boolean isOCLImportSource(@Nullable String source) {
//		return PivotConstants.IMPORT_ANNOTATION_SOURCE.equals(source);
//	}

	public static boolean isOriginalNameKey(@Nullable String source, @Nullable String key) {
		return DerivedConstants.UML2_UML_PACKAGE_2_0_NS_URI.equals(source)
				&& DerivedConstants.ANNOTATION_DETAIL__ORIGINAL_NAME.equals(key);
	}

}
