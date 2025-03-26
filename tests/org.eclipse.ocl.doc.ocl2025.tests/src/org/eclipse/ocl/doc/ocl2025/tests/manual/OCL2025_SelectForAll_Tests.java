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

import junit.framework.TestCase;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
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
public class OCL2025_SelectForAll_Tests extends TestCase
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
	public void testManual_JavaSet_SelectForAll() throws Exception {
		RandomObjectListGenerator randomObjectListGenerator = new RandomObjectListGenerator();
		PrintAndLog logger = new PrintAndLog(getName());
		logger.printf("%s\n", getName());
		try {
			@NonNull Feature @NonNull [] featuresArray = randomObjectListGenerator.createFeatures(1);
			int[] tests = PrintAndLog.getTestSizes();
			for (int testSize : tests) {
				@NonNull Klass @NonNull [] klassesArray = randomObjectListGenerator.createKlasses(testSize, featuresArray);
				PrintAndLog.garbageCollect();
				//		logger.printf("%9d: ", testSize);
				long startTime = System.nanoTime();
				Set<Klass> allKlasses = Sets.newHashSet(klassesArray);
				long endTime1 = System.nanoTime();
				Set<Klass> halfClasses = new HashSet<>();
				int i = 0;
				for (Klass klass : allKlasses) {
					if ((i++ & 1) != 0) {
						halfClasses.add(klass);
					}
				}
				long endTime2 = System.nanoTime();
				Set<Klass> allParentsSet = new HashSet<>();
				boolean allOk = true;
				for(Klass klass : halfClasses) {
					if (klass == null) {
						allOk = false;
					}
				}
				assert allOk;
				long endTime3 = System.nanoTime();
				double scale = testSize;
				logger.printf("%9.3f + %9.3f + %9.3f = %9.3fns * %9d = %9.6fs\n", (endTime1 - startTime) / scale, (endTime2 - endTime1) / scale, (endTime3 - endTime2) / scale, (endTime3 - startTime) / scale, testSize, (endTime3 - startTime) / 1.0e9);
				//	randomIntegerListGenerator.checkResult(newList, testSize);
			}
		}
		finally {
			logger.dispose();
		}
	}

	static PrintAndLog logger = null;
	static long startTime;
	static int misses = 0;
	static int hits = 0;

	@Test
	public void testManual_FastSet_SelectForAll() throws Exception {
		RandomObjectListGenerator randomObjectListGenerator = new RandomObjectListGenerator();
		PrintAndLog logger = new PrintAndLog(getName());
		logger.printf("%s\n", getName());
		try {
			@NonNull Feature @NonNull [] featuresArray = randomObjectListGenerator.createFeatures(1);
			int[] tests = PrintAndLog.getTestSizes();
			for (int testSize : tests) {
				Klass @NonNull [] klassesArray = randomObjectListGenerator.createKlasses(testSize, featuresArray);
				PrintAndLog.garbageCollect();
				//		logger.printf("%9d: ", testSize);
				long startTime = System.nanoTime();
				long endTime1 = System.nanoTime();
				boolean allOk = true;
				int i = 0;
				for (Klass klass : klassesArray) {
					if ((i++ & 1) != 0) {
						if (klass == null) {
							allOk = false;
						}
					}
				}
				assert allOk;
				long endTime3 = System.nanoTime();
				double scale = testSize;
				logger.printf("%9.3f + %9.3f = %9.3fns * %9d = %9.6fs\n", (endTime1 - startTime) / scale, (endTime3 - endTime1) / scale, (endTime3 - startTime) / scale, testSize, (endTime3 - startTime) / 1.0e9);
				//	randomIntegerListGenerator.checkResult(newList, testSize);
			}
		}
		finally {
			logger.dispose();
		}
	}

	public static class BinaryInsertionList
	{
		private static final int GAP_FACTOR = 1 << 4;
		private static final int GAP_BASE_OFFSET = 0;
		private static final int GAP_RUN_LENGTH_OFFSET = 1;
		private static final int GAP_RUN_LENGTH_START_OFFSET = 2;
		private static final int MAX_RUN_LENGTH = GAP_FACTOR - GAP_RUN_LENGTH_START_OFFSET;

		private int @Nullable [] contents = null;
		private int size = 0;

		boolean add(Object object) {
			int newValue = System.identityHashCode(object);
			int[] oldContents = contents;
			if (size < GAP_FACTOR) {
				// Direct assignment of first value
				if (size == 0) {
					assert contents == null;
					contents = oldContents = new int[GAP_FACTOR];
					oldContents[0+GAP_BASE_OFFSET] = newValue;
					oldContents[0+GAP_RUN_LENGTH_OFFSET] = 0;
				}
				else {
					// Insertion sort for single GAP
					Boolean sorted = insertionSort(0, newValue);
					if (sorted != null) {
						return sorted;
					}
					contents = rebalance(newValue);
				}
				misses++;
				size++;
				return true;
			}
			// Gapped insertion sort when larger
			assert oldContents != null;
			//
			//	Binary search to locate the insertion point
			//
			int low = 0;
			int high = oldContents.length - GAP_FACTOR;
			while (low <= high) {
				int mid = ((low + high) >>> 1) & ~(GAP_FACTOR-1);
				long midVal = oldContents[mid];
				if (midVal < newValue) {
					low = mid + GAP_FACTOR;
				}
				else if (midVal > newValue) {
					high = mid - GAP_FACTOR;
				}
				else {
					hits++;
					return false;//mid; 			// key found, no addition
				}
			}
			int lowIndex = (low > 0) ? (low-GAP_FACTOR) : 0;
			Boolean sorted = insertionSort(lowIndex, newValue);
			if (sorted != null) {
				return sorted;
			}
			long baseValue = oldContents[lowIndex];
			int iMax = GAP_FACTOR;
			int iLt = GAP_FACTOR;
			int i = 1;
			for (; i < GAP_FACTOR; i++) {
				long contentValue = oldContents[lowIndex+1];
				if (contentValue == newValue) {
					hits++;
					return false;					// key found, no addition
				}
				if (newValue < contentValue) {
					iLt = i;
					break;
				}
				if (contentValue == baseValue) {
					iMax = i;
					break;
				}
			}
			for (; i < GAP_FACTOR; i++) {
				long contentValue = oldContents[lowIndex+1];
				if (contentValue == baseValue) {
					iMax = i;
				}
			}
			if (iMax < GAP_FACTOR) {
				for (int j = iMax; j > iLt; --j) {
					oldContents[lowIndex+j+1] = oldContents[lowIndex+j];
				}
				oldContents[lowIndex+iLt] = newValue;
				misses++;
				size++;
				return true;
			}
			//
			//	Rebalance and insert
			//
			contents = rebalance(newValue);
			misses++;
			size++;
			return true;
		}

		protected void zsizePlusPlus() {
			size++;
			if ((size % 10000) == 0) {
				logger.printf("%9.6fs : size = %9d hits = %9d misses = %9d\n", (System.nanoTime() - startTime) / 1.0e9, size, hits, misses);
			}
		}

		/*
		 * Insert newValue within the potentially GAP_FACTOR run starting at iBase.
		 * Returns True if key was found
		 * Returns False if insertion succeeded
		 * Returns null if rebalancing required to make way for newValue.
		 */
		private @Nullable Boolean insertionSort(int iBase, int newValue) {
			int[] oldContents = contents;
			assert oldContents != null;
			int baseValue = oldContents[iBase];
			assert (iBase == 0) || (newValue > baseValue);
			assert (iBase == oldContents.length-GAP_FACTOR) || (newValue < oldContents[iBase+GAP_FACTOR]);
			int runLength = oldContents[iBase+1];
			//	int iMax = GAP_FACTOR;
			int iLt = -1;
			;
			if (baseValue == newValue) {
				hits++;
				return false;					// key found, no addition
			}
			for (int i = 0; i < runLength; i++) {			// XXX binary search
				int contentValue = oldContents[iBase+GAP_RUN_LENGTH_START_OFFSET+i];
				if (contentValue == newValue) {
					hits++;
					return false;					// key found, no addition
				}
				if (contentValue < newValue) {
					iLt = i;
				}
				else {
					break;
				}
			}
			if (runLength < MAX_RUN_LENGTH) {							// If room for an insertion
				assert newValue != baseValue;
				if (newValue < baseValue) {								// If insertion lowers initial boundary
					assert iLt == -1;
					for (int j = runLength; j > 0; j--) {
						oldContents[iBase+GAP_RUN_LENGTH_START_OFFSET+j] = oldContents[iBase+GAP_RUN_LENGTH_START_OFFSET+j-1];
					}
					oldContents[iBase+GAP_RUN_LENGTH_START_OFFSET] = baseValue;
					oldContents[iBase+GAP_BASE_OFFSET] = newValue;
				}
				else {													// If insertion at threshold
					//	assert iLt > 0;
					for (int j = runLength-1; j > iLt; j--) {
						oldContents[iBase+GAP_RUN_LENGTH_START_OFFSET+j+1] = oldContents[iBase+GAP_RUN_LENGTH_START_OFFSET+j];
					}
					oldContents[iBase+GAP_RUN_LENGTH_START_OFFSET+iLt+1] = newValue;
				}
				oldContents[iBase+GAP_RUN_LENGTH_OFFSET] = runLength+1;
				misses++;
				size++;
				return true;
			}
			return null;
		}

		public int[] rebalance(int newValue) {
			//			if (346224929 == newValue) {
			//				getClass();		// XXX
			//			}
			long startTime2 = System.nanoTime();
			//	assert checkContents();
			//
			//	Rebalance and insert
			//
			int[] oldContents = contents;
			assert oldContents != null;
			int[] newContents = new int[GAP_FACTOR*(size+1)];
			int newIndex = 0;

			boolean hasNewValue = true;
			//	List<Long> debugArray = new ArrayList<>();
			//	for (long l : oldContents) {
			//		debugArray.add(l);
			//	}
			//	Collections.sort
			//	assert oldContents.length == debugArray.size();
			//	Set<Object> debugSet = Sets.newHashSet(debugArray);				// XXX
			//	assert size == debugSet.size();
			for (int oldIndex = 0; oldIndex < oldContents.length; oldIndex += GAP_FACTOR) {
				int baseValue = oldContents[oldIndex+GAP_BASE_OFFSET];
				assert baseValue != 0;		// XXX
				int runLength = oldContents[oldIndex+GAP_RUN_LENGTH_OFFSET];
				if (hasNewValue && (newValue < baseValue)) {
					newContents[newIndex+GAP_BASE_OFFSET] = newValue;
					newContents[newIndex+GAP_RUN_LENGTH_OFFSET] = 0;
					newIndex += GAP_FACTOR;
					hasNewValue = false;
				}
				newContents[newIndex+GAP_BASE_OFFSET] = baseValue;
				newContents[newIndex+GAP_RUN_LENGTH_OFFSET] = 0;
				newIndex += GAP_FACTOR;
				for (int i = 0; i < runLength; i++) {
					int nextValue = oldContents[oldIndex+GAP_RUN_LENGTH_START_OFFSET+i];
					if (hasNewValue && (newValue < nextValue)) {
						newContents[newIndex+GAP_BASE_OFFSET] = newValue;
						newContents[newIndex+GAP_RUN_LENGTH_OFFSET] = 0;
						newIndex += GAP_FACTOR;
						hasNewValue = false;
					}
					newContents[newIndex+GAP_BASE_OFFSET] = nextValue;
					newContents[newIndex+GAP_RUN_LENGTH_OFFSET] = 0;
					newIndex += GAP_FACTOR;
				}
				/*	int nextOldIndex = oldIndex + GAP_FACTOR;
				oldIndex++;
				while (oldIndex < nextOldIndex) {
					int nextValue = oldContents[oldIndex++];
					if (nextValue == baseValue) {
						break;
					}
					newContents[newIndex+GAP_BASE_OFFSET] = nextValue;
					newContents[newIndex+GAP_RUN_LENGTH_OFFSET] = 0;
					newIndex += GAP_FACTOR;
				}
				oldIndex = nextOldIndex; */
			}
			if (hasNewValue) {
				newContents[newIndex+GAP_BASE_OFFSET] = newValue;
				newContents[newIndex+GAP_RUN_LENGTH_OFFSET] = 0;
				newIndex += GAP_FACTOR;
				hasNewValue = false;
			}
			assert newIndex == newContents.length;
			//	assert checkContents();
			//	long endTime = System.nanoTime();
			//	logger.printf("%9.6fs : %9d => %9d : %9.6fs\n", (endTime - startTime) / 1.0e9, size, newContents.length, (endTime - startTime2) / 1.0e9);
			return newContents;
		}

		private boolean zcheckContents() {
			int checkSize = 0;
			int[] contents2 = contents;
			assert contents2 != null;
			long runningValue = -1;
			for (int baseIndex = 0; baseIndex < contents2.length; baseIndex += GAP_FACTOR) {
				int baseValue = contents2[baseIndex+GAP_BASE_OFFSET];
				assert baseValue > runningValue;
				runningValue = baseValue;
				checkSize++;
				int runLength = contents2[baseIndex+GAP_RUN_LENGTH_OFFSET];
				assert (0 <= runLength) && (runLength <= MAX_RUN_LENGTH);
				for (int i = 0; i < runLength; i++) {
					long runValue = contents2[baseIndex+GAP_RUN_LENGTH_START_OFFSET+i];
					assert runValue > runningValue;
					runningValue = runValue;
					checkSize++;
				}
			}
			assert checkSize == size;
			return true;
		}
	}
}
