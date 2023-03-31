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

import org.eclipse.emf.ecore.EClass;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.UniqueList;

/**
 * An ESuperClassHelper maintains a lazy cache of super classes.
 */
public class ESuperClassHelper
{
	private static @NonNull Set<@NonNull EClass> CYCLIC_GUARD = new HashSet<>();

	private @NonNull Map<@NonNull EClass, @NonNull Set<@NonNull EClass>> eClass2eSuperClasses = new HashMap<>();

	public ESuperClassHelper() {}

	public <E extends Element> @NonNull EClass getCommonEClass(@NonNull Iterable<@NonNull E> elements) {
		EClass smallestEClass = null;
	//	Integer bestDepth = null;
		Set<@NonNull EClass> smallestESuperClasses = null;
		for (@NonNull E element : elements) {
			EClass eClass = element.eClass();
			assert eClass != null;
			Set<@NonNull EClass> eSuperClasses = getESuperClasses(eClass);
			if (smallestESuperClasses == null) {
				smallestEClass = eClass;
				smallestESuperClasses = eSuperClasses;
			}
			else {
				Set<@NonNull EClass> commonESuperClasses = new HashSet<>(smallestESuperClasses);
				commonESuperClasses.retainAll(eSuperClasses);
				if (commonESuperClasses.size() < smallestESuperClasses.size()) {
					smallestESuperClasses = commonESuperClasses;
					if (eSuperClasses.size() == commonESuperClasses.size()) {
						smallestEClass = eClass;
					}
					else {
						smallestEClass = null;		// Two non-overlapping super-inheritances
					}
				}
			}
		}
		assert smallestESuperClasses != null;
		EClass largestEClass = null;
		if (smallestEClass == null) {
			Set<@NonNull EClass> largestESuperClasses = null;
			for (@NonNull EClass eClass : smallestESuperClasses) {
				Set<@NonNull EClass> eSuperClasses = getESuperClasses(eClass);
				if (largestESuperClasses == null) {
					largestEClass = eClass;
					largestESuperClasses = eSuperClasses;
				}
				else {
					Set<@NonNull EClass> commonESuperClasses = new HashSet<>(largestESuperClasses);
					commonESuperClasses.retainAll(eSuperClasses);
					if (commonESuperClasses.size() > largestESuperClasses.size()) {
						largestESuperClasses = commonESuperClasses;
						largestEClass = eClass;
					}
				}
			}
			assert largestEClass != null;
		}
		else {
			largestEClass = smallestEClass;
		}
		return largestEClass;
	}

	private @NonNull Set<@NonNull EClass> getESuperClasses(@NonNull EClass eClass) {
		Set<@NonNull EClass> eSuperClasses = eClass2eSuperClasses.get(eClass);
		if (eSuperClasses == null) {
			eClass2eSuperClasses.put(eClass, CYCLIC_GUARD);
			eSuperClasses = new UniqueList<>();
		//	int maxSuperDepth = -1;
			for (EClass eSuperClass : eClass.getESuperTypes()) {
				assert eSuperClass != null;
				if (eSuperClasses.add(eSuperClass)) {
					eSuperClasses.addAll(getESuperClasses(eSuperClass));
				}
			//	int depth = eClass2depth.get(eClass);
			//	if (depth > maxSuperDepth) {
			//		maxSuperDepth = depth;
			//	}
			}
		//	eClass2depth.put(eClass, maxSuperDepth+1);
			eClass2eSuperClasses.put(eClass, eSuperClasses);
		}
		else if (eSuperClasses == CYCLIC_GUARD) {
			throw new IllegalStateException("Cyclic superEClasses for " + eClass);
		}
		return eSuperClasses;
	}

	@Override
	public @NonNull String toString() {
		StringBuilder s = new StringBuilder();
		boolean isFirstOuter = true;
		List<@NonNull EClass> eClasses = new ArrayList<>(eClass2eSuperClasses.keySet());
		Collections.sort(eClasses, NameUtil.ENAMED_ELEMENT_COMPARATOR);
		for (@NonNull EClass eClass : eClasses) {
			Set<@NonNull EClass> eSuperClasses = eClass2eSuperClasses.get(eClass);
			assert eSuperClasses != null;
			if (!isFirstOuter) {
				s.append("\n");
			}
			s.append(eClass.getName());
			s.append(" : ");
			boolean isFirstInner = true;
			for (@NonNull EClass eSuperClass : eSuperClasses) {
				if (!isFirstInner) {
					s.append(",");
				}
				s.append(eSuperClass.getName());
				isFirstInner = false;
			}
			isFirstOuter = false;
		}
		return s.toString();
	}
}
