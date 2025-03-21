/*******************************************************************************
 * Copyright (c) 2016, 2018 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.doc.ocl2025.tests.manual;

import com.google.common.collect.Sets;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.doc.ocl2025.tests.AbstractOCL2025CGTests;
import org.eclipse.ocl.doc.ocl2025.tests.PrintAndLog;
import org.eclipse.ocl.doc.ocl2025.tests.RandomObjectListGenerator;
import org.eclipse.ocl.doc.ocl2025.tests.RandomObjectListGenerator.Feature;
import org.eclipse.ocl.doc.ocl2025.tests.RandomObjectListGenerator.Klass;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Source code for OCL 2025, Collection optimization paper.
 */
public class OCL2025_Cascade_Tests extends AbstractOCL2025CGTests
{
	@Override
	@Before
	public void setUp() throws Exception {
		super.setUp();
	}

	@Override
	@After
	public void tearDown() throws Exception {
		super.tearDown();
	}

	@Test
	public void testManual_JavaSet_Intersection() throws Exception {
		RandomObjectListGenerator randomObjectListGenerator = new RandomObjectListGenerator();
		PrintAndLog logger = new PrintAndLog(getName());
		logger.printf("%s\n", getName());
		try {
			int[] tests = PrintAndLog.getTestSizes();
			for (int testSize : tests) {
				@NonNull Feature anotherFeature = new Feature(-1);
				@NonNull Feature @NonNull [] featuresArray = randomObjectListGenerator.createFeatures(testSize);
				@NonNull Klass @NonNull [] klassesArray = randomObjectListGenerator.createKlasses(testSize, featuresArray);
				garbageCollect();
				//		logger.printf("%9d: ", testSize);
				long startTime = System.nanoTime();
				Set<@NonNull Klass> allKlasses = Sets.newHashSet(klassesArray);
				long endTime1 = System.nanoTime();
				List<@NonNull Klass> allParents = new ArrayList<>();
				for (@NonNull Klass klass : allKlasses) {
					allParents.addAll(klass.getParents());
				}
				long endTime2 = System.nanoTime();
				Set<@NonNull Klass> allParentsSet = new HashSet<>(allParents);
				long endTime3 = System.nanoTime();
				List<@NonNull Feature> allFeatures = new ArrayList<>();
				for (@NonNull Klass klass : allParentsSet) {
					allFeatures.addAll(klass.getFeatures());
				}
				long endTime4 = System.nanoTime();
				Set<@NonNull Feature> allFeaturesSet = new HashSet<>(allFeatures);
				long endTime5 = System.nanoTime();
				assert !allFeaturesSet.contains(anotherFeature);
				long endTime6 = System.nanoTime();
				double scale = testSize;
				logger.printf("%9.3f + %9.3f + %9.3f + %9.3f + %9.3f + %9.3f = %9.3fns * %9d = %9.6fs\n", (endTime1 - startTime) / scale, (endTime2 - endTime1) / scale, (endTime3 - endTime2) / scale, (endTime4 - endTime3) / scale, (endTime5 - endTime4) / scale, (endTime6 - endTime5) / scale, (endTime6 - startTime) / scale, testSize, (endTime6 - startTime) / 10e9);
				//	randomIntegerListGenerator.checkResult(newList, testSize);
			}
		}
		finally {
			logger.dispose();
		}
	}

	@Test
	public void testManual_FastSet_Intersection() throws Exception {
		RandomObjectListGenerator randomObjectListGenerator = new RandomObjectListGenerator();
		PrintAndLog logger = new PrintAndLog(getName());
		logger.printf("%s\n", getName());
		try {
			int[] tests = PrintAndLog.getTestSizes();
			for (int testSize : tests) {
				@NonNull Feature anotherFeature = new Feature(-1);
				@NonNull Feature @NonNull [] featuresArray = randomObjectListGenerator.createFeatures(testSize);
				@NonNull Klass @NonNull [] klassesArray = randomObjectListGenerator.createKlasses(testSize, featuresArray);
				garbageCollect();
				//		logger.printf("%9d: ", testSize);
				long startTime = System.nanoTime();
				boolean gotIt = false;
				//	HashSet<@NonNull Klass> parentsSet = new HashSet<>();
				BinaryInsertionList parentsSet = new BinaryInsertionList();
				for (@NonNull Klass klass : klassesArray) {
					for (@NonNull Klass parent : klass.getParents()) {
						//	System.identityHashCode(parent);
						if (parentsSet.add(parent)) {
							for (@NonNull Feature feature : parent.getFeatures()) {
								if (feature == anotherFeature) {
									gotIt = true;
									break;
								}
							}
						}
					}
				}
				assert !gotIt;
				long endTime = System.nanoTime();
				double scale = testSize;
				logger.printf("%9.3f * %9d = %9.6fs\n", (endTime - startTime) / scale, testSize, (endTime - startTime) / 10e9);
			}
		}
		finally {
			logger.dispose();
		}
	}

	public static class BinaryInsertionList
	{
		private long @Nullable [] contents = null;
		private int size = 0;


		boolean add(Object object) {
			int newValue = System.identityHashCode(object);
			long[] oldContents = contents;
			if (size <= 8) {
				// Direct assignment of first value
				if (oldContents == null) {
					assert size == 0;
					contents = oldContents = new long[9];
					oldContents[size++] = newValue;
					return true;
				}
				// Insertion sort for second to eighth values
				int i = 0;
				for (; i < size; i++) {
					long contentValue = oldContents[i];
					if (contentValue == newValue) {
						return false;
					}
					if (contentValue > newValue) {
						break;
					}
				}
				for (int j = size; j > i; --j) {
					long contentValue = oldContents[j-1];
					oldContents[j] = contentValue;
				}
				oldContents[i] = newValue;
				size++;
				return true;
			}
			// Gapped insertion sort when larger
			assert oldContents != null;
			return binarySearchAdd(0, oldContents.length, newValue);
		}

		private boolean binarySearchAdd(int fromIndex, int toIndex, long newValue) {
			long[] oldContents = contents;
			assert oldContents != null;
			//
			//	Binary search to locate the insertion point
			//
			int low = fromIndex;
			int high = toIndex - 1;
			while (low <= high) {
				int mid = (low + high) >>> 1;
				long midVal = oldContents[mid];
				if (midVal < newValue) {
					low = mid + 1;
				}
				else if (midVal > newValue) {
					high = mid - 1;
				}
				else {
					return false;//mid; 			// key found, no addition
				}
			}
			if (low < oldContents.length) {
				//
				//	Insert within the up to four repeats
				//
				long base = oldContents[low];
				assert newValue < base;
				if ((low+1 < oldContents.length) && (oldContents[low+1] == base)) {			// If inserting at a 'gap'
					oldContents[low] = newValue;
					if ((low+3 < oldContents.length) && (oldContents[low+3] == base)) {		// If inserting at a big 'gap'
						oldContents[low+1] = newValue;
						if ((low+5 < oldContents.length) && (oldContents[low+5] == base)) {		// If inserting at a big 'gap'
							oldContents[low+2] = newValue;
							if ((low+7 < oldContents.length) && (oldContents[low+7] == base)) {		// If inserting at a big 'gap'
								oldContents[low+3] = newValue;
							}
						}
					}
					size++;
					return true;
				}
			}
			//
			//	Rebalance and insert
			//
			int GAP_FACTOR = 8;
			long[] newContents = contents = new long[GAP_FACTOR*(size+1)];
			int newIndex = 0;
			int oldIndex = 0;
			boolean hasNewValue = true;
			//	List<Long> debugArray = new ArrayList<>();
			//	for (long l : oldContents) {
			//		debugArray.add(l);
			//	}
			//	Collections.sort
			//	assert oldContents.length == debugArray.size();
			//	Set<Object> debugSet = Sets.newHashSet(debugArray);				// XXX
			//	assert size == debugSet.size();
			while (oldIndex < oldContents.length) {
				long contentValue = oldContents[oldIndex++];
				if (hasNewValue && (newValue < contentValue)) {
					for (int j = 0; j < GAP_FACTOR; j++) {
						newContents[newIndex++] = newValue;
					}
					hasNewValue = false;
				}
				for (int j = 0; j < GAP_FACTOR; j++) {
					newContents[newIndex++] = contentValue;
				}
				while ((oldIndex < oldContents.length) && (oldContents[oldIndex] == contentValue)) {
					oldIndex++;
				}
			}
			if (hasNewValue) {
				for (int j = 0; j < GAP_FACTOR; j++) {
					newContents[newIndex++] = newValue;
				}
				hasNewValue = false;
			}
			assert newIndex == newContents.length;
			assert newContents[newContents.length-1] != 0;
			size++;
			//		System.out.println("Rebalance " + size + "/" + newContents.length);
			return true;
		}
	}
}
