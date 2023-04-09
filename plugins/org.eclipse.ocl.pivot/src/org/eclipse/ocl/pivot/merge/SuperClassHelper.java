/*******************************************************************************
 * Copyright (c) 2023 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - initial API and implementation
*******************************************************************************/
package org.eclipse.ocl.pivot.merge;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.utilities.UniqueList;

/**
 * A SuperClassHelper maintains a lazy cache of super classes.
 */
@Deprecated
public class SuperClassHelper
{
	private static @NonNull Set<org.eclipse.ocl.pivot.@NonNull Class> CYCLIC_GUARD = new HashSet<>();

	private @NonNull Map<org.eclipse.ocl.pivot.@NonNull Class, @NonNull Set<org.eclipse.ocl.pivot.@NonNull Class>> class2superClasses = new HashMap<>();

	public SuperClassHelper() {}

/*	public <E extends Element> org.eclipse.ocl.pivot.@NonNull Class getCommonClass(@NonNull Iterable<@NonNull E> elements) {
		org.eclipse.ocl.pivot.Class smallestClass = null;
	//	Integer bestDepth = null;
		Set<org.eclipse.ocl.pivot.@NonNull Class> smallestSuperClasses = null;
		for (@NonNull E element : elements) {
			org.eclipse.ocl.pivot.@NonNull Class asClass = element.eClass();
			assert asClass != null;
			Set<org.eclipse.ocl.pivot.@NonNull Class> asSuperClasses = getSuperClasses(asClass);
			if (smallestSuperClasses == null) {
				smallestClass = asClass;
				smallestSuperClasses = asSuperClasses;
			}
			else {
				Set<org.eclipse.ocl.pivot.@NonNull Class> commonSuperClasses = new HashSet<>(smallestSuperClasses);
				commonSuperClasses.retainAll(asSuperClasses);
				if (commonSuperClasses.size() < smallestSuperClasses.size()) {
					smallestSuperClasses = commonSuperClasses;
					if (asSuperClasses.size() == commonSuperClasses.size()) {
						smallestClass = asClass;
					}
					else {
						smallestClass = null;		// Two non-overlapping super-inheritances
					}
				}
			}
		}
		assert smallestSuperClasses != null;
		org.eclipse.ocl.pivot.Class largestClass = null;
		if (smallestClass == null) {
			Set<org.eclipse.ocl.pivot.@NonNull Class> largestSuperClasses = null;
			for (org.eclipse.ocl.pivot.@NonNull Class asClass : smallestSuperClasses) {
				Set<org.eclipse.ocl.pivot.@NonNull Class> eSuperClasses = getSuperClasses(asClass);
				if (largestSuperClasses == null) {
					largestClass = asClass;
					largestSuperClasses = eSuperClasses;
				}
				else {
					Set<org.eclipse.ocl.pivot.@NonNull Class> commonSuperClasses = new HashSet<>(largestSuperClasses);
					commonSuperClasses.retainAll(eSuperClasses);
					if (commonSuperClasses.size() > largestSuperClasses.size()) {
						largestSuperClasses = commonSuperClasses;
						largestClass = asClass;
					}
				}
			}
			assert largestClass != null;
		}
		else {
			largestClass = smallestClass;
		}
		return largestClass;
	} */

	private @NonNull Set<org.eclipse.ocl.pivot.@NonNull Class> getSuperClasses(org.eclipse.ocl.pivot.@NonNull Class asClass) {
		Set<org.eclipse.ocl.pivot.@NonNull Class> asSuperClasses = class2superClasses.get(asClass);
		if (asSuperClasses == null) {
			class2superClasses.put(asClass, CYCLIC_GUARD);
			asSuperClasses = new UniqueList<>();
		//	int maxSuperDepth = -1;
			for (org.eclipse.ocl.pivot.@NonNull Class asSuperClass : PivotUtil.getSuperClasses(asClass)) {
				if (asSuperClasses.add(asSuperClass)) {
					asSuperClasses.addAll(getSuperClasses(asSuperClass));
				}
			//	int depth = eClass2depth.get(eClass);
			//	if (depth > maxSuperDepth) {
			//		maxSuperDepth = depth;
			//	}
			}
		//	eClass2depth.put(eClass, maxSuperDepth+1);
			class2superClasses.put(asClass, asSuperClasses);
		}
		else if (asSuperClasses == CYCLIC_GUARD) {
			throw new IllegalStateException("Cyclic superClasses for " + asClass);
		}
		return asSuperClasses;
	}

	@Override
	public @NonNull String toString() {
		StringBuilder s = new StringBuilder();
		boolean isFirstOuter = true;
		List<org.eclipse.ocl.pivot.@NonNull Class> asClasses = new ArrayList<>(class2superClasses.keySet());
		Collections.sort(asClasses, NameUtil.NAMEABLE_COMPARATOR);
		for (org.eclipse.ocl.pivot.@NonNull Class asClass : asClasses) {
			Set<org.eclipse.ocl.pivot.@NonNull Class> asSuperClasses = class2superClasses.get(asClass);
			assert asSuperClasses != null;
			if (!isFirstOuter) {
				s.append("\n");
			}
			s.append(asClass.getName());
			s.append(" : ");
			boolean isFirstInner = true;
			for (org.eclipse.ocl.pivot.@NonNull Class asSuperClass : asSuperClasses) {
				if (!isFirstInner) {
					s.append(",");
				}
				s.append(asSuperClass.getName());
				isFirstInner = false;
			}
			isFirstOuter = false;
		}
		return s.toString();
	}
}
