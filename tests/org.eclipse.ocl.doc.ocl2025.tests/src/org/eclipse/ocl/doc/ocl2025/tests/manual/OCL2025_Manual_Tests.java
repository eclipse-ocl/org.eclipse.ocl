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

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Set;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.doc.ocl2025.tests.AbstractOCL2025CGTests;
import org.eclipse.ocl.doc.ocl2025.tests.PrintAndLog;
import org.eclipse.ocl.doc.ocl2025.tests.RandomIntegerListGenerator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Source code for OCL 2025, Collection optimization paper.
 */
public class OCL2025_Manual_Tests extends AbstractOCL2025CGTests
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
		RandomIntegerListGenerator randomIntegerListGenerator = new RandomIntegerListGenerator();
		PrintAndLog logger = new PrintAndLog(getName());
		logger.printf("%s\n", getName());
		try {
			int[] tests = PrintAndLog.getTestSizes();
			for (int testSize : tests) {
				int commonSize = testSize/4;
				Collection<@NonNull Integer> randoms1 = randomIntegerListGenerator.createRandoms(testSize);
				Collection<@NonNull Integer> randoms2 = randomIntegerListGenerator.createRandoms(testSize, randoms1, commonSize);
				garbageCollect();
				//		logger.printf("%9d: ", testSize);
				long startTime = System.nanoTime();
				Set<@NonNull Integer> firstSet1 = Sets.newHashSet(randoms1);
				long endTime1 = System.nanoTime();
				Set<@NonNull Integer> secondSet = Sets.newHashSet(randoms2);
				long endTime2 = System.nanoTime();
				Set<@NonNull Integer> firstSet2 = Sets.newHashSet(firstSet1);
				long endTime3 = System.nanoTime();
				firstSet2.retainAll(secondSet);
				assert firstSet2.size() == commonSize;
				long endTime4 = System.nanoTime();
				double scale = testSize;
				logger.printf("%9.3f + %9.3f + %9.3f + %9.3f = %9.3fns * %9d = %9.6fs\n", (endTime1 - startTime) / scale, (endTime2 - endTime1) / scale, (endTime3 - endTime2) / scale, (endTime4 - endTime3) / scale, (endTime4 - startTime) / scale, testSize, (endTime4 - startTime) / 10e9);
				//	randomIntegerListGenerator.checkResult(newList, testSize);
			}
		}
		finally {
			logger.dispose();
		}
	}

	@Test
	public void testManual_FastSet_Intersection() throws Exception {
		RandomIntegerListGenerator randomIntegerListGenerator = new RandomIntegerListGenerator();
		PrintAndLog logger = new PrintAndLog(getName());
		logger.printf("%s\n", getName());
		try {
			int[] tests = /*new int[] { 10 };//*/ PrintAndLog.getTestSizes();
			for (int testSize : tests) {
				int commonSize = testSize/4;
				Collection<@NonNull Integer> randoms1 = randomIntegerListGenerator.createRandoms(testSize);
				Collection randoms2 = randomIntegerListGenerator.createRandoms(testSize, randoms1, commonSize);
				garbageCollect();
				//	logger.printf("%9d, ", testSize);
				//	long startTime = System.nanoTime();
				FastSet intersection = FastSet.intersection(logger, randoms1, randoms2);
				assert intersection.size() == commonSize;
				//	long endTime = System.nanoTime();
				//	logger.printf("%9.6f\n", (endTime - startTime) / 1.0e9);
				//	randomIntegerListGenerator.checkResult(newList, testSize);
			}
		}
		finally {
			logger.dispose();
		}
	}

	public static class FastSet<E> implements Set<E>
	{
		protected static final class HashComparator implements Comparator<Integer>
		{
			private int @NonNull [] hashes;

			public HashComparator(int @NonNull [] hashes) {
				this.hashes = hashes;
			}

			@Override
			public int compare(Integer iIndex, Integer jIndex) {
				int iHash = hashes[iIndex];
				int jHash = hashes[jIndex];
				if (iHash > jHash) {
					return +1;
				}
				else if (iHash < jHash) {
					return -1;
				}
				else {
					return iIndex - jIndex;		// Stable sort order for duplicates
				}
			}
		}

		protected class Iterator implements java.util.Iterator<E>
		{
			private int index = 0;

			@Override
			public boolean hasNext() {
				return index < elements.length;
			}

			@Override
			public E next() {
				return elements[index++];
			}

			@Override
			public void remove() {
				throw new UnsupportedOperationException();
			}
		}

		private Class<E> jClass;
		private E [] elements;
		/**
		 * The sorted hash codes of each element (bottom 32 bits) and indexes of each element (top 32 bits)
		 */
		private long [] sortedHashesAndIndexes = null;
		/**
		 * The indexes of each element sorted by its hashcode.
		 */
		//	private int [] hashIndex2elementIndex = null;

		public FastSet(Class<E> jClass, @NonNull Collection<@NonNull E> elements) {
			this.jClass = jClass;
			this.elements = (E[]) Array.newInstance(jClass, elements.size());
			int i = 0;
			for (E element : elements) {
				this.elements[i++] = element;
			}
		}

		public FastSet(Class<E> jClass, E[] elements, long[] sortedHashesAndIndexes, int size) {
			this.jClass = jClass;
			this.elements = (E[]) Array.newInstance(jClass, size);
			this.sortedHashesAndIndexes = sortedHashesAndIndexes;
			for (int i = 0; i < size; i++) {
				this.elements[i] = elements[i];
			}
		}

		private void computeHashesAndIndexes() {
			int iMax = elements.length;
			this.sortedHashesAndIndexes = new long[iMax];
			for (int i = 0; i < iMax; i++) {
				this.sortedHashesAndIndexes[i] = ((long)i << 32) | (elements[i].hashCode() & 0xFFFFFFFFL);
			}
			//	Arrays.sort(indexes1, new HashComparator(sortedHashes));
			sort2(sortedHashesAndIndexes, 0, iMax-1);
			//	int j = 0;
			//	for (Integer index : indexes1) {
			//		this.sortedHashes[j] = elements[index].hashCode();
			//		this.hashIndex2elementIndex[j] = index;
			//		j++;
			//	}
			//	for (int k = 0; k < elements.length; k++) {
			//		assert sortedHashes[k] == sortedHashes2[k];
			//		assert indexes1[k].intValue() == indexes2[k];
			//	}
			//		for (E element : elements) {
			//			int hash = element.hashCode();
			//			int index = Arrays.binarySearch(sortedHashes, hash);
			//			//	assert index == sortedIndexes[k++];
			//		}
			//	getClass();
		}
		/* The main function that implements QuickSort()
	      a[] --> Array to be sorted,
	      l  --> Starting index,
	      h  --> Ending index */
		static	void sort2(long[] sortedHashesAndIndexes, int l, int h)
		{
			if (l < h)
			{
				int pi = partition2(sortedHashesAndIndexes, l, h);

				// Recursively sort elements before
				// partition and after partition
				sort2(sortedHashesAndIndexes, l, pi-1);
				sort2(sortedHashesAndIndexes, pi+1, h);
			}
		}
		static	int partition2(long[] sortedHashesAndIndexes, int low, int high)
		{
			long pivotHashAndIndex = sortedHashesAndIndexes[high];
			int pivot = (int)(pivotHashAndIndex & 0xFFFFFFFFL);
			int i = (low-1);
			for (int j=low; j<high; j++)
			{

				// If current element is smaller than or
				// equal to pivot
				long jHashAndIndex = sortedHashesAndIndexes[j];
				int jHash = (int)(jHashAndIndex & 0xFFFFFFFFL);
				if (jHash <= pivot)
				{
					i++;
					int left = i;
					int right = j;
					long temp = sortedHashesAndIndexes[left];
					sortedHashesAndIndexes[left] = sortedHashesAndIndexes[right];
					sortedHashesAndIndexes[right] = temp;
				}
			}

			int left = i+1;
			int right = high;
			long temp = sortedHashesAndIndexes[left];
			sortedHashesAndIndexes[left] = sortedHashesAndIndexes[right];
			sortedHashesAndIndexes[right] = temp;

			return i+1;
		}

		public static <E> FastSet<E> intersection(PrintAndLog logger, Collection<@NonNull Integer> randoms1, Collection randoms2) {
			long startTime = System.nanoTime();
			FastSet<E> firstSet = new FastSet(Integer.class, randoms1);
			long endTime1 = System.nanoTime();
			FastSet<E> secondSet = new FastSet(Integer.class, randoms2);
			long endTime2 = System.nanoTime();
			firstSet.computeHashesAndIndexes();
			long endTime3 = System.nanoTime();
			secondSet.computeHashesAndIndexes();
			long endTime4 = System.nanoTime();
			int i = 0;
			int j = 0;
			int k = 0;
			int iMax = firstSet.size();
			int jMax = secondSet.size();
			long [] hashIntersections = new long[iMax];
			int [] elementIndexes = new int[iMax];
			while (i < iMax) {
				int iHash = (int)(firstSet.sortedHashesAndIndexes[i] & 0xFFFFFFFFL);
				while (j < jMax) {
					int jHash = (int)(secondSet.sortedHashesAndIndexes[j] & 0xFFFFFFFFL);
					if (jHash > iHash) {
						break;
					}
					if (jHash == iHash) {
						hashIntersections[k] = iHash;
						elementIndexes[k] = (int)(firstSet.sortedHashesAndIndexes[i] >> 32);
						k++;
					}
					j++;
				}
				i++;
			}
			long endTime5 = System.nanoTime();
			Arrays.sort(elementIndexes, 0, k);
			long endTime6 = System.nanoTime();
			E [] valueIntersections = (E[]) Array.newInstance(firstSet.jClass, iMax);
			int q = 0;
			for (int elementIndex : elementIndexes) {
				valueIntersections[q++] = firstSet.elements[elementIndex];
				if (q >= k) {
					break;
				}
			}
			long endTime7 = System.nanoTime();
			FastSet result = new FastSet(firstSet.jClass, valueIntersections, hashIntersections, k);
			long endTime8 = System.nanoTime();
			//	logger.printf("%9.6f %9.6f %9.6f %9.6f %9.6f %9.6f ", (endTime1 - startTime) / 1.0e9, (endTime2 - endTime1) / 1.0e9,
			//		(endTime3 - endTime2) / 1.0e9, (endTime4 - endTime3) / 1.0e9, (endTime5 - endTime4) / 1.0e9, (endTime6 - endTime5) / 1.0e9);
			double scale = iMax;
			logger.printf("%9.3f + %9.3f + %9.3f + %9.3f + %9.3f + %9.3f + %9.3f + %9.3f = %9.3fns * %9d = %9.6fs\n", (endTime1 - startTime) / scale, (endTime2 - endTime1) / scale,
				(endTime3 - endTime2) / scale, (endTime4 - endTime3) / scale, (endTime5 - endTime4) / scale, (endTime6 - endTime5) / scale, (endTime7 - endTime6) / scale, (endTime8 - endTime7) / scale,
				(endTime8 - startTime) / scale, iMax, (endTime8 - startTime) / 10e9);
			return result;
		}
		@Override
		public int size() {
			return elements.length;
		}

		@Override
		public boolean isEmpty() {
			return elements.length == 0;
		}

		@Override
		public boolean contains(Object o) {
			throw new UnsupportedOperationException();
		}

		@Override
		public @NonNull Iterator iterator() {
			return new Iterator();
		}

		@Override
		public Object[] toArray() {
			throw new UnsupportedOperationException();
		}

		@Override
		public <T> T @NonNull [] toArray(T @NonNull [] a) {
			throw new UnsupportedOperationException();
		}
		@Override
		public boolean add(E e) {
			throw new UnsupportedOperationException();
		}
		@Override
		public boolean remove(Object o) {
			throw new UnsupportedOperationException();
		}
		@Override
		public boolean containsAll(Collection<?> c) {
			throw new UnsupportedOperationException();
		}
		@Override
		public boolean addAll(Collection<? extends E> c) {
			throw new UnsupportedOperationException();
		}
		@Override
		public boolean retainAll(Collection<?> c) {
			throw new UnsupportedOperationException();
		}
		@Override
		public boolean removeAll(Collection<?> c) {
			throw new UnsupportedOperationException();
		}
		@Override
		public void clear() {
			throw new UnsupportedOperationException();
		}
	}
}
