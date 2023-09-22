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
import org.eclipse.ocl.pivot.utilities.UniqueList;

/**
 * A SuperClassHelper maintains a lazy cache of super classes.
 */
public class SuperClassHelper
{
	private static @NonNull Set<org.eclipse.ocl.pivot.@NonNull Class> CYCLIC_GUARD = new HashSet<>();

	private @NonNull Map<org.eclipse.ocl.pivot.@NonNull Class, @NonNull Set<org.eclipse.ocl.pivot.@NonNull Class>> class2superClasses = new HashMap<>();

	public SuperClassHelper() {}

	/**
	 * Return the most-derived EClass common to all elements.
	 *
	public @NonNull EClass getCommonEClass(@NonNull Iterable<@NonNull ? extends Element> elements) {
		EClass smallestEClass = null;
	//	Integer bestDepth = null;
		Set<@NonNull EClass> smallestESuperClasses = null;
		for (@NonNull Element element : elements) {
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
	} */

	/**
	 * Return the most-derived EClass that is a subtype of all elements.
	 *
	public @NonNull EClass getCompatibleEClass(@NonNull Iterable<@NonNull ? extends Element> elements) {
		EClass compatibleEClass = null;
		Set<@NonNull EClass> compatibleESuperClasses = null;
		for (@NonNull Element element : elements) {
			EClass eClass = element.eClass();
			assert eClass != null;
		//	Set<@NonNull EClass> eSuperClasses = getESuperClasses(eClass);
			if (compatibleEClass == null) {
				compatibleEClass = eClass;
				compatibleESuperClasses = getESuperClasses(eClass);
			}
			else {
				assert compatibleESuperClasses != null;
				if ((compatibleEClass == eClass) || compatibleESuperClasses.contains(eClass)){
					;
				}
				else {
					Set<@NonNull EClass> eSuperClasses = getESuperClasses(eClass);
					if ((compatibleEClass == eClass) || eSuperClasses.contains(compatibleEClass)){
						compatibleEClass = eClass;
						compatibleESuperClasses = eSuperClasses;
					}
					else {
						throw new IllegalStateException("No common EClass for " + compatibleEClass.getName() + " and " + eClass.getName());
					}
				}
			}
		}
		assert compatibleEClass != null;
		return compatibleEClass;
	) */

	private @NonNull Set<org.eclipse.ocl.pivot.@NonNull Class> getSuperClasses(org.eclipse.ocl.pivot.@NonNull Class asClass) {
		Set<org.eclipse.ocl.pivot.@NonNull Class> asSuperClasses = class2superClasses.get(asClass);
		if (asSuperClasses == null) {
			class2superClasses.put(asClass, CYCLIC_GUARD);
			asSuperClasses = new UniqueList<>();
		//	int maxSuperDepth = -1;
			for (org.eclipse.ocl.pivot.@NonNull Class asSuperClass : asClass.getSuperClasses()) {
				assert asSuperClass != null;
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

	/**
	 * Return true if subclass and its superClasses includes superClass.
	 */
	public boolean isSubClass(org.eclipse.ocl.pivot.@NonNull Class subClass, org.eclipse.ocl.pivot.@NonNull Class superClass) {
		if (subClass == superClass) {
			return true;
		}
		return getSuperClasses(subClass).contains(superClass);
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
