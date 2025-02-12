/*******************************************************************************
 * Copyright (c) 2025 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.doc.ocl2025.tests;

import java.util.Collection;
import java.util.HashSet;
import java.util.Random;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.utilities.UniqueList;

public abstract class AbstractRandomListGenerator<E>
{
	/*	public void checkModel(L list, int testSize) {
		List<E> elements = getOwnedElements(list);
		assert elements.size() == testSize-1;
		int i = 0;
		for (@Nullable E element = getHeadElement(list); element != null; element = getTarget(element)) {
			assert getElementName(element).equals("e-" + (i > 0 ? testSize-i : 1));
			if ((i > 0) && (element == getHeadElement(list))) {
				break;
			}
			i++;
		}
		assert i == testSize-1;
		i = 0;
		for (@Nullable E element = getHeadElement(list); element != null; element = getSource(element)) {
			if ((i > 0) && (element == getHeadElement(list))) {
				break;
			}
			assert getElementName(element).equals("e-" + (i+1));
			i++;
		}
		assert i == testSize-1;
	} */

	private @NonNull Random random = new Random();

	protected abstract E createRandomElement(@NonNull Random random);

	/**
	 * Return a List with n random elements all different.
	 */
	public @NonNull Collection<E> createRandoms(int nSize) {
		UniqueList<E> elements = new UniqueList<>();
		while (elements.size() < nSize) {
			E element = createRandomElement(random);
			elements.add(element);
		}
		return elements;
	}

	/**
	 * Return a List with n random elements all different.
	 */
	public @NonNull Collection<E> createRandoms(int nSize, @NonNull Collection<E> oldElements, int nCommon) {
		UniqueList<E> elements = new UniqueList<>(oldElements);
		int nJoint = oldElements.size() - nCommon + nSize;
		while (elements.size() < nJoint) {
			E element = createRandomElement(random);
			elements.add(element);
		}
		return new HashSet<>(elements.subList(nJoint - nSize, nJoint));			// Distribute common randomly
	}
}
