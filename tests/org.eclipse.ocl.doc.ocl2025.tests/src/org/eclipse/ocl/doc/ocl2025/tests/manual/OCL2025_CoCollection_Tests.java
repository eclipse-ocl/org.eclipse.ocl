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

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Set;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.doc.ocl2025.tests.PrintAndLog;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.google.common.collect.Sets;

/**
 * Source code for OCL 2025, Collection optimization paper.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class OCL2025_CoCollection_Tests
{
	public static class PseudoNamedElement implements Comparable<PseudoNamedElement>
	{
		public static boolean HASH_IS_UNIQUE = false;

		private static int count = 0;
		private final int id;

		public PseudoNamedElement() {
			this.id = ++count; //System.identityHashCode(this);
		}

		@Override
		public int compareTo(PseudoNamedElement o) {
			return id - o.id;
		}

		@Override
		public boolean equals(Object that) {
			return this == that;
		}

		public long getId() {
			return id;
		}

		public @NonNull String getName() {
			return "xyzzy";
		}

		@Override
		public int hashCode() {
			return HASH_IS_UNIQUE ? id : System.identityHashCode(this);
		}

		@Override
		public String toString() {
			return Long.toString(id);
		}
	}

	private static @NonNull PseudoNamedElement [] elementsArray = null;
	private static ComplexLatexSummary summary = null;
	//	private static int[] testSizes = null;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		//	System.out.println("setUpBeforeClass");
		//	testSizes = PrintAndLog.getTestSizes(10, 5, 6, 10);
		summary = new ComplexLatexSummary(OCL2025_CoCollection_Tests.class.getSimpleName())
		{
			@Override
			protected Object separator(String key) {
				if (key == keys.get(0)) {
					return " * ";
				}
				if (key == keys.get(1)) {
					return " : ";
				}
				if (key == keys.get(keys.size()-3)) {
					return " = ";
				}
				if (key == keys.get(keys.size()-2)) {
					return "ns/e : ";
				}
				if (key == keys.get(keys.size()-1)) {
					return "s";
				}
				return " + ";
			}
		};
		elementsArray = new @NonNull PseudoNamedElement [2*PrintAndLog.MAX_TEST_SIZE];
		for (int i = 0; i < elementsArray.length; i++) {
			elementsArray[i] = new PseudoNamedElement();
		}
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		//	System.out.println("tearDownAfterClass");
		elementsArray = null;
		summary.dispose();
		summary = null;
		//	testSizes = null;
	}

	protected @NonNull CoCollection<PseudoNamedElement> createCoCollection(Class<PseudoNamedElement> jClass, PseudoNamedElement @NonNull [] elements) {
		return CoCollection.create(jClass, elements);
	}

	protected void doTest(String testName, int warmUps, int testWarmUp, int testMin, int testMax) throws IOException, InterruptedException {
		System.out.printf("%s\n", testName);
		PrintAndLog logger = new PrintAndLog(testName);
		logger.printf("%s\n", testName);
		PrintAndLog.garbageCollect();
		try {
			Integer[] testSizes = testSizes(testMin, testMax, 6, 6);
			for (int iTest = 0; iTest < warmUps; iTest++) {
				doTestInner(true, testWarmUp);
			}
			for (int iTest = 0; iTest < testSizes.length; iTest++) {
				int testSize = testSizes[iTest];
				for (int iRepeat = 0; iRepeat < 1000; iRepeat++) {
					doTestInner(false, testSize);
				}
				if ((((iTest + 1) >= testSizes.length) || (testSize != testSizes[iTest+1]))) {
					System.out.printf("%s", summary.average(testSize));
					logger.printf("%s", summary.statistic(testSize, 0));
				}
			}
			summary.flush(testName);
		}
		finally {
			logger.dispose();
			PseudoNamedElement.HASH_IS_UNIQUE = false;
		}
	}

	protected void doTestInner(boolean isWarmup, int testSize)
			throws InterruptedException {
		int commonSize = testSize/4;
		int iRepeats = testSize >= 1 ? Math.max(1000 / testSize, 1) : 1000;
		List<@NonNull List<@NonNull PseudoNamedElement>> firstLists = new ArrayList<>();
		List<@NonNull List<@NonNull PseudoNamedElement>> secondLists = new ArrayList<>();
		//	List<@NonNull List<@NonNull PseudoNamedElement>> commonLists = new ArrayList<>();
		List<@NonNull CoCollection<PseudoNamedElement>> firstSets = new ArrayList<>();
		List<@NonNull CoCollection<PseudoNamedElement>> secondSets = new ArrayList<>();
		for (int iRepeat = 0; iRepeat < iRepeats; iRepeat++) {
			List<@NonNull PseudoNamedElement> firstList = new ArrayList<>();
			List<@NonNull PseudoNamedElement> secondList = new ArrayList<>();
			//	List<@NonNull PseudoNamedElement> commonList = new ArrayList<>();
			for (int i = 0; i < testSize-commonSize; i++) {
				@NonNull PseudoNamedElement element = elementsArray[i];
				firstList.add(element);
			}
			for (int i = testSize-commonSize; i < testSize; i++) {
				@NonNull PseudoNamedElement element = elementsArray[i];
				firstList.add(element);
				secondList.add(element);
				//		commonList.add(element);
			}
			//	assert commonList.size() == commonSize;
			for (int i = testSize; i < 2*testSize-commonSize; i++) {
				@NonNull PseudoNamedElement element = elementsArray[i];
				secondList.add(element);
			}
			Collections.shuffle(firstList);
			Collections.shuffle(secondList);
			firstLists.add(firstList);
			secondLists.add(secondList);
			//	commonLists.add(commonList);
		}
		//	PrintAndLog.garbageCollect();
		//		logger.printf("%9d: ", testSize);
		Class<PseudoNamedElement> jClass = PseudoNamedElement.class;
		long startTime = System.nanoTime();
		for (int iRepeat = 0; iRepeat < iRepeats; iRepeat++) {
			PseudoNamedElement[] firstArray = firstLists.get(iRepeat).toArray(new PseudoNamedElement[testSize]);
			CoCollection<PseudoNamedElement> firstSet = createCoCollection(jClass, firstArray);
			firstSet.setSettable();
			firstSets.add(firstSet);
		}
		long endTime1 = System.nanoTime();
		for (int iRepeat = 0; iRepeat < iRepeats; iRepeat++) {
			PseudoNamedElement[] secondArray = secondLists.get(iRepeat).toArray(new PseudoNamedElement[testSize]);
			CoCollection<PseudoNamedElement> secondSet = createCoCollection(jClass, secondArray);
			secondSet.setSettable();
			secondSets.add(secondSet);
		}
		long endTime2 = System.nanoTime();
		//	for (int iRepeat = 0; iRepeat < iRepeats; iRepeat++) {
		//		firstSets.get(iRepeat).setSettable();
		//	}
		long endTime3 = System.nanoTime();
		//	for (int iRepeat = 0; iRepeat < iRepeats; iRepeat++) {
		//		secondSets.get(iRepeat).setSettable();
		//	}
		long endTime4 = System.nanoTime();
		for (int iRepeat = 0; iRepeat < iRepeats; iRepeat++) {
			CoCollection<PseudoNamedElement> firstSet = firstSets.get(iRepeat);
			CoCollection<PseudoNamedElement> secondSet = secondSets.get(iRepeat);
			CoCollection<PseudoNamedElement> intersection = firstSet.intersection(secondSet);
			int intersectionSize = intersection.size();
			if (intersectionSize != commonSize) {
				/*	intersectionSize = intersection.size();
				CoCollection<PseudoNamedElement> intersection2 = firstSet.intersection(secondSet);
				int intersectionSize2 = intersection2.size();
				List<@NonNull PseudoNamedElement> commonList = commonLists.get(iRepeat);
				Collections.sort(commonList);
				List<@NonNull PseudoNamedElement> intersectionList = Lists.newArrayList(intersection.iterator());
				Collections.sort(intersectionList);
				HashSet<?> deltaSet1 = new HashSet<>(commonList);
				deltaSet1.removeAll(intersection);
				HashSet<?> deltaSet2 = new HashSet<>(intersection);
				deltaSet2.removeAll(commonList); */
				assert false;
			}
		}
		long endTime5 = System.nanoTime();
		double scale = Math.max(1, testSize) * iRepeats;
		System.out.printf("%4d*%9d : %9.3f + %9.3f + %9.3f + %9.3f + %9.3f = %9.3fns/e : %9.6fs\n", iRepeats, testSize, (endTime1 - startTime) / scale, (endTime2 - endTime1) / scale, (endTime3 - endTime2) / scale, (endTime4 - endTime3) / scale, (endTime5 - endTime4) / scale, (endTime5 - startTime) / scale, (endTime5 - startTime) / (iRepeats * 1.0e9));
		//	logger.printf("%4d,%9d,%9.3f,%9.3f,%9.3f,%9.3f,%9.3f,%9.3f,%9.6f\n", iRepeats, testSize, (endTime1 - startTime) / scale, (endTime2 - endTime1) / scale, (endTime3 - endTime2) / scale, (endTime4 - endTime3) / scale, (endTime5 - endTime4) / scale, (endTime5 - startTime) / scale, (endTime5 - startTime) / (iRepeats * 1.0e9));
		//	randomIntegerListGenerator.checkResult(newList, testSize);
		if (!isWarmup) {
			summary.keyedprintf(testSize, "repeats", "%9d", iRepeats);
			summary.keyedprintf(testSize, "size", "%9d", testSize);
			summary.keyedprintf(testSize, "ctor 1", "%9.3f", ((endTime1 - startTime) / scale));
			summary.keyedprintf(testSize, "ctor 2", "%9.3f", ((endTime2 - endTime1) / scale));
			summary.keyedprintf(testSize, "settable 1", "%9.3f", ((endTime3 - endTime2) / scale));
			summary.keyedprintf(testSize, "settable 2", "%9.3f", ((endTime4 - endTime3) / scale));
			summary.keyedprintf(testSize, "intersect", "%9.3f", ((endTime5 - endTime4) / scale));
			summary.keyedprintf(testSize, "total ns/e", "%9.3f", ((endTime5 - startTime) / scale));
			summary.keyedprintf(testSize, "total s", "%9.6f", ((endTime5 - startTime) / (1.0e9 * iRepeats)));
		}
	}

	@Test
	public void testSetIntersection_00_Default() throws Exception {
		String testName = "DefaultCoCollection";//getName();
		doTest(testName, 1000, 10, 0, 1000000);
	}

	@Test
	public void testSetIntersection_01_Empty() throws Exception {
		String testName = "EmptyCoCollection";//getName();
		OCL2025_CoCollection_Tests testInstance = new OCL2025_CoCollection_Tests()
		{
			@Override
			protected @NonNull CoCollection<PseudoNamedElement> createCoCollection(Class<PseudoNamedElement> jClass, PseudoNamedElement @NonNull [] elements) {
				return new EmptyCoCollection<>(jClass);
			}
		};
		testInstance.doTest(testName, 10000, 0, 0, 0);
	}

	@Test
	public void testSetIntersection_02_Single() throws Exception {
		String testName = "SingleCoCollection";//getName();
		OCL2025_CoCollection_Tests testInstance = new OCL2025_CoCollection_Tests()
		{
			@Override
			protected @NonNull CoCollection<PseudoNamedElement> createCoCollection(Class<PseudoNamedElement> jClass, PseudoNamedElement @NonNull [] elements) {
				return new SingleCoCollection<>(jClass, elements[0]);
			}
		};
		testInstance.doTest(testName, 10000, 1, 1, 1);
	}

	@Test
	public void testSetIntersection_03_Double() throws Exception {
		String testName = "DoubleCoCollection";//getName();
		OCL2025_CoCollection_Tests testInstance = new OCL2025_CoCollection_Tests()
		{
			@Override
			protected @NonNull CoCollection<PseudoNamedElement> createCoCollection(Class<PseudoNamedElement> jClass, PseudoNamedElement @NonNull [] elements) {
				return new DoubleCoCollection<>(jClass, elements[0], elements[1]);
			}
		};
		testInstance.doTest(testName, 10000, 2, 2, 2);
	}

	@Test
	public void testSetIntersection_04_Triple() throws Exception {
		String testName = "TripleCoCollection";//getName();
		OCL2025_CoCollection_Tests testInstance = new OCL2025_CoCollection_Tests()
		{
			@Override
			protected @NonNull CoCollection<PseudoNamedElement> createCoCollection(Class<PseudoNamedElement> jClass, PseudoNamedElement @NonNull [] elements) {
				return new TripleCoCollection<>(jClass, elements[0], elements[1], elements[2]);
			}
		};
		testInstance.doTest(testName, 10000, 3, 3, 3);
	}

	@Test
	public void testSetIntersection_05_Quadruple() throws Exception {
		String testName = "QuadrupleCoCollection";//getName();
		OCL2025_CoCollection_Tests testInstance = new OCL2025_CoCollection_Tests()
		{
			@Override
			protected @NonNull CoCollection<PseudoNamedElement> createCoCollection(Class<PseudoNamedElement> jClass, PseudoNamedElement @NonNull [] elements) {
				return new QuadrupleCoCollection<>(jClass, elements[0], elements[1], elements[2], elements[3]);
			}
		};
		testInstance.doTest(testName, 10000, 4, 4, 4);
	}

	@Test
	public void testSetIntersection_06_Tiny() throws Exception {
		String testName = "TinyCoCollection";//getName();
		OCL2025_CoCollection_Tests testInstance = new OCL2025_CoCollection_Tests()
		{
			@Override
			protected @NonNull CoCollection<PseudoNamedElement> createCoCollection(Class<PseudoNamedElement> jClass, PseudoNamedElement @NonNull [] elements) {
				return new TinyCoCollection<>(jClass, elements);
			}
		};
		testInstance.doTest(testName, 1000, 10, 0, 10000);
	}

	@Test
	public void testSetIntersection_07_Small() throws Exception {
		String testName = "SmallCoCollection";//getName();
		OCL2025_CoCollection_Tests testInstance = new OCL2025_CoCollection_Tests()
		{
			@Override
			protected @NonNull CoCollection<PseudoNamedElement> createCoCollection(Class<PseudoNamedElement> jClass, PseudoNamedElement @NonNull [] elements) {
				return new SmallCoCollection<>(jClass, elements);
			}
		};
		testInstance.doTest(testName, 1000, 10, 0, 1000000);
	}

	@Test
	public void testSetIntersection_08_SmallWellHashed() throws Exception {
		String testName = "SmallWellHashedCoCollection";//getName();
		OCL2025_CoCollection_Tests testInstance = new OCL2025_CoCollection_Tests()
		{
			@Override
			protected @NonNull CoCollection<PseudoNamedElement> createCoCollection(Class<PseudoNamedElement> jClass, PseudoNamedElement @NonNull [] elements) {
				return new SmallWellHashedCoCollection(jClass, elements);
			}
		};
		PseudoNamedElement.HASH_IS_UNIQUE = true;
		testInstance.doTest(testName, 1000, 10, 0, 1000000);
	}

	@Test
	public void testSetIntersection_09_Large() throws Exception {
		String testName = "LargeCoCollection";//getName();
		OCL2025_CoCollection_Tests testInstance = new OCL2025_CoCollection_Tests()
		{
			@Override
			protected @NonNull CoCollection<PseudoNamedElement> createCoCollection(Class<PseudoNamedElement> jClass, PseudoNamedElement @NonNull [] elements) {
				return new LargeCoCollection<>(jClass, elements);
			}
		};
		testInstance.doTest(testName, 1000, 10, 0, 1000000);
	}

	@Test
	public void testSetIntersection_10_LargeWellHashed() throws Exception {
		String testName = "LargeWellHashedCoCollection";//getName();
		OCL2025_CoCollection_Tests testInstance = new OCL2025_CoCollection_Tests()
		{
			@Override
			protected @NonNull CoCollection<PseudoNamedElement> createCoCollection(Class<PseudoNamedElement> jClass, PseudoNamedElement @NonNull [] elements) {
				return new LargeCoCollection<>(jClass, elements);
			}
		};
		PseudoNamedElement.HASH_IS_UNIQUE = true;
		testInstance.doTest(testName, 1000, 10, 0, 1000000);
	}

	@Test
	public void testSetIntersection_11_NonDeterminateJavaSet() throws Exception {
		String testName = "NonDeterminateJavaSet";//getName();
		OCL2025_CoCollection_Tests testInstance = new OCL2025_CoCollection_Tests()
		{
			@Override
			protected @NonNull CoCollection<PseudoNamedElement> createCoCollection(Class<PseudoNamedElement> jClass, PseudoNamedElement @NonNull [] elements) {
				return new NonDeterminateJavaSet<>(jClass, elements);
			}
		};
		testInstance.doTest(testName, 1000, 10, 0, 1000000);
	}

	@Test
	public void testSetIntersection_12_NonDeterminateWellHashedJavaSet() throws Exception {
		String testName = "NonDeterminateWellHashedJavaSet";//getName();
		OCL2025_CoCollection_Tests testInstance = new OCL2025_CoCollection_Tests()
		{
			@Override
			protected @NonNull CoCollection<PseudoNamedElement> createCoCollection(Class<PseudoNamedElement> jClass, PseudoNamedElement @NonNull [] elements) {
				return new NonDeterminateJavaSet<>(jClass, elements);
			}
		};
		PseudoNamedElement.HASH_IS_UNIQUE = true;
		testInstance.doTest(testName, 1000, 10, 0, 1000000);
	}



	/*	@Test
	public void testSetIntersection_2_Flat() throws Exception {
		String testName = "Flat Set";//getName();
		PrintAndLog logger = new PrintAndLog(testName);
		logger.printf("%s\n", testName);
		//	summary.printf("%s", testName);
		RandomIntegerListGenerator randomIntegerListGenerator = new RandomIntegerListGenerator();
		try {
			for (int i = 0; i < testSizes.length; i++) {
				int testSize = testSizes[i];
				int commonSize = testSize/4;
				Collection<@NonNull Integer> randoms1 = randomIntegerListGenerator.createRandoms(testSize);
				Collection<@NonNull Integer> randoms2 = randomIntegerListGenerator.createRandoms(testSize, randoms1, commonSize);
				PrintAndLog.garbageCollect();
				//	logger.printf("%9d, ", testSize);
				//	long startTime = System.nanoTime();
				FlatSet<?> intersection = FlatSet.intersection(logger, randoms1, randoms2);
				assert intersection.size() == commonSize;
				//	long endTime = System.nanoTime();
				//	logger.printf("%9.6f\n", (endTime - startTime) / 1.0e9);
				//	randomIntegerListGenerator.checkResult(newList, testSize);
				if ((testSize != PrintAndLog.WARMUP_TEST_SIZE) && (((i + 1) >= testSizes.length) || (testSize != testSizes[i+1]))) {
					logger.printf("%s", summary.average(testSize));
				}
			}
			summary.flush(testName);
		}
		finally {
			logger.dispose();
		}
	} */

	private Integer[] testSizes(int testMin, int testMax, int decades, int testsPerDecade) {
		List<Integer> testSizes = new ArrayList<>();
		if (testMin == testMax) {
			testSizes.add(testMin);
		}
		else {
			if (testMin == 0) {
				testSizes.add(0);
			}
			for (int i = 0; i <= decades; i++) {
				for (int j = 0; j < testsPerDecade; j++) {
					//			for (int j = 0; j < repeats; j++) {
					//		testSizes[(i-1)*repeats+1+j+warmup-1] = (int)Math.round(Math.pow(10.0, (i+testsPerDecade-1)/(double)testsPerDecade));
					int testSize = (int)Math.round(Math.pow(10.0, i + j/(double)testsPerDecade));
					if ((testMin <= testSize) && (testSize <= testMax)) {
						if (testSizes.isEmpty() || (testSize != testSizes.get(testSizes.size()-1).intValue())) {
							testSizes.add(testSize);
						}
					}
					if (i == decades) {
						break;
					}
				}
			}
			//	testSizes[warmup+tests] = MAX_TEST_SIZE;
		}
		return testSizes.toArray(new Integer[testSizes.size()]);
	}



	public static abstract class CoCollection<E> implements Collection<E>
	{
		public static <E> @NonNull CoCollection<E> create(Class<E> jClass, E [] elements) {
			int length = elements.length;
			if (length == 0) {
				return new EmptyCoCollection<>(jClass);
			}
			else if (length == 1) {
				return new SingleCoCollection<>(jClass, elements[0]);
			}
			else if (length == 2) {
				return new DoubleCoCollection<>(jClass, elements[0], elements[1]);
			}
			else if (length == 3) {
				return new TripleCoCollection<>(jClass, elements[0], elements[1], elements[2]);
			}
			else if (length == 4) {
				return new QuadrupleCoCollection<>(jClass, elements[0], elements[1], elements[2], elements[3]);
			}
			//	else if (length < 5) {
			//		return new TinyCoCollection<>(jClass, elements);
			//	}
			else { //if (length < 256) {
				return new SmallCoCollection<>(jClass, elements);
			}
			//	else {
			//		return new LargeCoCollection<>(jClass, elements);
			//	}
		}

		/*	public static <E> @NonNull CoCollection<E> create(@NonNull Class<E> jClass, @NonNull List<E> elements) {
			int length = elements.size();
			if (length == 0) {
				return new EmptyCoCollection<>(jClass);
			}
			else if (length == 1) {
				Iterator<E> iterator = elements.iterator();
				return new SingleCoCollection<>(jClass, iterator.next());
			}
			else if (length == 2) {
				Iterator<E> iterator = elements.iterator();
				return new DoubleCoCollection<>(jClass, iterator.next(), iterator.next());
			}
		//	else if (length < 5) {
		//		return new TinyCoCollection<>(jClass, elements);
		//	}
			else {//if (length < 256) {
				return new SmallCoCollection<>(jClass, elements);
			}
		//	else {
		//		return new LargeCoCollection<>(jClass, elements);
		//	}
		} */

		/*	public static <E> @NonNull CoCollection<E> create(@NonNull Class<E> jClass, E [] elements, long[] hash2index) {
			int length = elements.length;
			assert hash2index.length == length;
			if (length == 0) {
				return new EmptyCoCollection<>(jClass);
			}
			else if (length == 1) {
				return new SingleCoCollection<>(jClass, elements[0]);
			}
			else if (length == 2) {
				return new DoubleCoCollection<>(jClass, elements[0], elements[1]);
			}
			else if (length < 5) {
				return new TinyCoCollection<>(jClass, elements, hash2index);
			}
			else if (length < 256) {
				return new SmallCoCollection<>(jClass, elements, hash2index);
			}
			else {
				return new LargeCoCollection<>(jClass, elements, hash2index);
			}
		} */

		protected final Class<E> jClass;

		protected CoCollection(Class<E> jClass) {
			this.jClass = jClass;
		}

		@Override
		public final boolean add(E e) {
			throw new IllegalStateException();
		}

		@Override
		public final boolean addAll(Collection<? extends E> c) {
			throw new IllegalStateException();
		}

		@Override
		public final void clear() {
			throw new IllegalStateException();
		}

		public abstract @NonNull CoCollection<E> intersection(@NonNull CoCollection<E> coCollection);

		@Override
		public final boolean remove(Object o) {
			throw new IllegalStateException();
		}

		@Override
		public final boolean removeAll(Collection<?> c) {
			throw new IllegalStateException();
		}

		@Override
		public final boolean retainAll(Collection<?> c) {
			throw new IllegalStateException();
		}

		public abstract void setSettable();
	}

	public static class EmptyCoCollection<E> extends CoCollection<E>
	{
		public static class NullIterator implements Iterator<Object>
		{
			public static @NonNull NullIterator INSTANCE = new NullIterator();

			public NullIterator() {}

			@Override
			public boolean hasNext() {
				return false;
			}

			@Override
			public Object next() {
				throw new NoSuchElementException();
			}

			@Override
			public void remove() {
				throw new IllegalStateException();
			}
		}

		public EmptyCoCollection(Class<E> jClass) {
			super(jClass);
		}

		@Override
		public boolean contains(Object o) {
			return false;
		}

		@Override
		public boolean containsAll(Collection<?> c) {
			return false;
		}

		@Override
		public @NonNull CoCollection<E> intersection(@NonNull CoCollection<E> coCollection) {
			return this;
		}

		@Override
		public boolean isEmpty() {
			return true;
		}

		@SuppressWarnings("unchecked")
		@Override
		public java.util.@NonNull Iterator<E> iterator() {
			return (java.util.Iterator<E>)NullIterator.INSTANCE;
		}

		@Override
		public void setSettable() {}

		@Override
		public int size() {
			return 0;
		}

		@Override
		public Object[] toArray() {
			return new Object[] {};
		}

		@Override
		public <T> T @NonNull [] toArray(T @NonNull [] a) {
			return a;
		}
	}


	public static class SingleCoCollection<E> extends CoCollection<E>
	{
		public static class SingleIterator<E> implements Iterator<E>
		{
			private final E element;
			private int cursor = 0;

			public SingleIterator(E element) {
				this.element = element;
			}

			@Override
			public boolean hasNext() {
				return cursor == 0;
			}

			@Override
			public E next() {
				switch (cursor) {
					case 0: cursor++; return element;
					default: throw new NoSuchElementException();
				}
			}

			@Override
			public void remove() {
				throw new IllegalStateException();
			}
		}

		private final E element;

		public SingleCoCollection(Class<E> jClass, E element) {
			super(jClass);
			this.element = element;
		}

		@Override
		public boolean contains(Object o) {
			if (o == null) {
				return (element == null);
			}
			else {
				return o.equals(element);
			}
		}

		@Override
		public boolean containsAll(Collection<?> c) {
			return c.contains(element);
		}

		@Override
		public @NonNull CoCollection<E> intersection(@NonNull CoCollection<E> coCollection) {
			if (coCollection.contains(element)) {
				return this;
			}
			return new EmptyCoCollection<>(jClass);
		}

		@Override
		public boolean isEmpty() {
			return false;
		}

		@Override
		public java.util.@NonNull Iterator<E> iterator() {
			return new SingleIterator<>(element);
		}

		@Override
		public void setSettable() {}

		@Override
		public int size() {
			return 1;
		}

		@Override
		public Object[] toArray() {
			return new Object[] {element};
		}

		@SuppressWarnings("unchecked")
		@Override
		public <T> T @NonNull [] toArray(T @NonNull [] a) {
			if (a.length < 1) {
				return (T[]) new Object[] {element};
			}
			a[0] = (T)element;
			return a;
		}
	}

	public static class DoubleCoCollection<E> extends CoCollection<E>
	{
		public static class DoubleIterator<E> implements Iterator<E>
		{
			private final E element0;
			private final E element1;
			private int cursor = 0;;

			public DoubleIterator(E element0, E element1) {
				this.element0 = element0;
				this.element1 = element1;
			}

			@Override
			public boolean hasNext() {
				return cursor < 2;
			}

			@Override
			public E next() {
				switch (cursor) {
					case 0: cursor++; return element0;
					case 1: cursor++; return element1;
					default: throw new NoSuchElementException();
				}
			}

			@Override
			public void remove() {
				throw new IllegalStateException();
			}
		}

		private final E element0;
		private final E element1;

		public DoubleCoCollection(Class<E> jClass, E element0, E element1) {
			super(jClass);
			this.element0 = element0;
			this.element1 = element1;
		}

		@Override
		public boolean contains(Object o) {
			if (o == null) {
				return (element0 == null) || (element1 == null);
			}
			else {
				return o.equals(element0) || o.equals(element1);
			}
		}

		@Override
		public boolean containsAll(Collection<?> c) {
			return c.contains(element0) && c.contains(element1);
		}

		@Override
		public @NonNull CoCollection<E> intersection(@NonNull CoCollection<E> coCollection) {
			int flags = 0;
			if (coCollection.contains(element0)) flags |= 0x1;
			if (coCollection.contains(element1)) flags |= 0x2;
			switch (flags) {
				case 0x0: return new EmptyCoCollection<>(jClass);
				case 0x1: return new SingleCoCollection<>(jClass, element0);
				case 0x2: return new SingleCoCollection<>(jClass, element1);
				case 0x3: return this;
				default: return this;		// never happens
			}
		}

		@Override
		public boolean isEmpty() {
			return false;
		}

		@Override
		public java.util.@NonNull Iterator<E> iterator() {
			return new DoubleIterator<E>(element0, element1);
		}

		@Override
		public void setSettable() {}

		@Override
		public int size() {
			return 2;
		}

		@Override
		public Object[] toArray() {
			return new Object[] {element0, element1};
		}

		@SuppressWarnings("unchecked")
		@Override
		public <T> T @NonNull [] toArray(T @NonNull [] a) {
			if (a.length < 2) {
				return (T[]) new Object[] {element0, element1};
			}
			a[0] = (T)element0;
			a[1] = (T)element1;
			return a;
		}
	}

	public static class TripleCoCollection<E> extends CoCollection<E>
	{
		public static class TripleIterator<E> implements Iterator<E>
		{
			private final E element0;
			private final E element1;
			private final E element2;
			private int cursor = 0;;

			public TripleIterator(E element0, E element1, E element2) {
				this.element0 = element0;
				this.element1 = element1;
				this.element2 = element2;
			}

			@Override
			public boolean hasNext() {
				return cursor < 3;
			}

			@Override
			public E next() {
				switch (cursor) {
					case 0: cursor++; return element0;
					case 1: cursor++; return element1;
					case 2: cursor++; return element2;
					default: throw new NoSuchElementException();
				}
			}

			@Override
			public void remove() {
				throw new IllegalStateException();
			}
		}

		private final E element0;
		private final E element1;
		private final E element2;

		public TripleCoCollection(Class<E> jClass, E element0, E element1, E element2) {
			super(jClass);
			this.element0 = element0;
			this.element1 = element1;
			this.element2 = element2;
		}

		@Override
		public boolean contains(Object o) {
			if (o == null) {
				return (element0 == null) || (element1 == null) || (element2 == null);
			}
			else {
				return o.equals(element0) || o.equals(element1) || o.equals(element2);
			}
		}

		@Override
		public boolean containsAll(Collection<?> c) {
			return c.contains(element0) && c.contains(element1) && c.contains(element2);
		}

		@Override
		public @NonNull CoCollection<E> intersection(@NonNull CoCollection<E> coCollection) {
			int flags = 0;
			if (coCollection.contains(element0)) flags |= 0x1;
			if (coCollection.contains(element1)) flags |= 0x2;
			if (coCollection.contains(element2)) flags |= 0x4;
			switch (flags) {
				case 0x0: return new EmptyCoCollection<>(jClass);
				case 0x1: return new SingleCoCollection<>(jClass, element0);
				case 0x2: return new SingleCoCollection<>(jClass, element1);
				case 0x3: return new DoubleCoCollection<>(jClass, element0, element1);
				case 0x4: return new SingleCoCollection<>(jClass, element2);
				case 0x5: return new DoubleCoCollection<>(jClass, element0, element2);
				case 0x6: return new DoubleCoCollection<>(jClass, element1, element2);
				case 0x7: return this;
				default: return this;		// never happens
			}
		}

		@Override
		public boolean isEmpty() {
			return false;
		}

		@Override
		public java.util.@NonNull Iterator<E> iterator() {
			return new TripleIterator<E>(element0, element1, element2);
		}

		@Override
		public void setSettable() {}

		@Override
		public int size() {
			return 3;
		}

		@Override
		public Object[] toArray() {
			return new Object[] {element0, element1, element2};
		}

		@SuppressWarnings("unchecked")
		@Override
		public <T> T @NonNull [] toArray(T @NonNull [] a) {
			if (a.length < 2) {
				return (T[]) new Object[] {element0, element1, element2};
			}
			a[0] = (T)element0;
			a[1] = (T)element1;
			a[2] = (T)element2;
			return a;
		}
	}

	public static class QuadrupleCoCollection<E> extends CoCollection<E>
	{
		public static class QuadrupleIterator<E> implements Iterator<E>
		{
			private final E element0;
			private final E element1;
			private final E element2;
			private final E element3;
			private int cursor = 0;;

			public QuadrupleIterator(E element0, E element1, E element2, E element3) {
				this.element0 = element0;
				this.element1 = element1;
				this.element2 = element2;
				this.element3 = element3;
			}

			@Override
			public boolean hasNext() {
				return cursor < 4;
			}

			@Override
			public E next() {
				switch (cursor) {
					case 0: cursor++; return element0;
					case 1: cursor++; return element1;
					case 2: cursor++; return element2;
					case 3: cursor++; return element3;
					default: throw new NoSuchElementException();
				}
			}

			@Override
			public void remove() {
				throw new IllegalStateException();
			}
		}

		private final E element0;
		private final E element1;
		private final E element2;
		private final E element3;

		public QuadrupleCoCollection(Class<E> jClass, E element0, E element1, E element2, E element3) {
			super(jClass);
			this.element0 = element0;
			this.element1 = element1;
			this.element2 = element2;
			this.element3 = element3;
		}

		@Override
		public boolean contains(Object o) {
			if (o == null) {
				return (element0 == null) || (element1 == null) || (element2 == null) || (element3 == null);
			}
			else {
				return o.equals(element0) || o.equals(element1) || o.equals(element2) || o.equals(element3);
			}
		}

		@Override
		public boolean containsAll(Collection<?> c) {
			return c.contains(element0) && c.contains(element1) && c.contains(element2) && c.contains(element3);
		}

		@Override
		public @NonNull CoCollection<E> intersection(@NonNull CoCollection<E> coCollection) {
			int flags = 0;
			if (coCollection.contains(element0)) flags |= 0x1;
			if (coCollection.contains(element1)) flags |= 0x2;
			if (coCollection.contains(element2)) flags |= 0x4;
			if (coCollection.contains(element3)) flags |= 0x8;
			switch (flags) {
				case 0x0: return new EmptyCoCollection<>(jClass);
				case 0x1: return new SingleCoCollection<>(jClass, element0);
				case 0x2: return new SingleCoCollection<>(jClass, element1);
				case 0x3: return new DoubleCoCollection<>(jClass, element0, element1);
				case 0x4: return new SingleCoCollection<>(jClass, element2);
				case 0x5: return new DoubleCoCollection<>(jClass, element0, element2);
				case 0x6: return new DoubleCoCollection<>(jClass, element1, element2);
				case 0x7: return new TripleCoCollection<>(jClass, element0, element1, element2);
				case 0x8: return new SingleCoCollection<>(jClass, element3);
				case 0x9: return new DoubleCoCollection<>(jClass, element0, element3);
				case 0xa: return new DoubleCoCollection<>(jClass, element1, element3);
				case 0xb: return new TripleCoCollection<>(jClass, element0, element1, element3);
				case 0xc: return new DoubleCoCollection<>(jClass, element2, element3);
				case 0xd: return new TripleCoCollection<>(jClass, element0, element2, element3);
				case 0xe: return new TripleCoCollection<>(jClass, element1, element2, element3);
				case 0xf: return this;
				default: return this;		// never happens
			}
		}

		@Override
		public boolean isEmpty() {
			return false;
		}

		@Override
		public java.util.@NonNull Iterator<E> iterator() {
			return new QuadrupleIterator<E>(element0, element1, element2, element3);
		}

		@Override
		public void setSettable() {}

		@Override
		public int size() {
			return 4;
		}

		@Override
		public Object[] toArray() {
			return new Object[] {element0, element1, element2, element3};
		}

		@SuppressWarnings("unchecked")
		@Override
		public <T> T @NonNull [] toArray(T @NonNull [] a) {
			if (a.length < 2) {
				return (T[]) new Object[] {element0, element1, element2, element2};
			}
			a[0] = (T)element0;
			a[1] = (T)element1;
			a[2] = (T)element2;
			a[3] = (T)element3;
			return a;
		}
	}

	public static abstract class HashedCoCollection<E> extends CoCollection<E>
	{
		public static class HashedIterator<E> implements Iterator<E>
		{
			private final E @NonNull [] elements;
			private int cursor = 0;

			public HashedIterator(E @NonNull [] elements) {
				this.elements = elements;
			}

			@Override
			public boolean hasNext() {
				return cursor < elements.length;
			}

			@Override
			public E next() {
				if (cursor < elements.length) {
					return elements[cursor++];
				}
				throw new NoSuchElementException();
			}

			@Override
			public void remove() {
				throw new IllegalStateException();
			}
		}

		private int binarySearch2(Object object)
		{
			int low = 0;
			int high = hash2index.length;
			@SuppressWarnings("unchecked")
			long objectHash = getHashCode((E)object);
			while (low <= high) {
				int mid = (low + high) >>> 1;
				long midHash = hash2index[mid] & 0xFFFFFFFFL;
				if (midHash < objectHash)
					low = mid + 1;
				else if (midHash > objectHash)
					high = mid - 1;
				else {
					for (int matchingMid = mid; matchingMid < high; matchingMid++) {
						int midIndex = (int)((hash2index[matchingMid] >> 32) & 0xFFFFFFFFL);
						E element = elements[midIndex];
						if (element == object) {
							return matchingMid;	// key found and matching element
						}
					}
					break;						// key found but no matching element
				}
			}
			return -(low + 1);  				// key not found.
		}

		protected long getHashCode(E object) {
			return System.identityHashCode(object) & 0xFFFFFFFFL;
		}

		private void sort2(int l, int h)
		{
			if (l < h)
			{
				int pi = partition2(hash2index, l, h);

				// Recursively sort elements before
				// partition and after partition
				sort2(l, pi-1);
				sort2(pi+1, h);
			}
		}

		private static int partition2(long[] hash2index, int low, int high)
		{
			long pivotHashAndIndex = hash2index[high];
			long pivot = pivotHashAndIndex & 0xFFFFFFFFL;
			int i = (low-1);
			for (int j=low; j<high; j++)
			{

				// If current element is smaller than or
				// equal to pivot
				long jHashAndIndex = hash2index[j];
				long jHash = jHashAndIndex & 0xFFFFFFFFL;
				if (jHash <= pivot)
				{
					i++;
					int left = i;
					int right = j;
					long temp = hash2index[left];
					hash2index[left] = hash2index[right];
					hash2index[right] = temp;
				}
			}

			int left = i+1;
			int right = high;
			long temp = hash2index[left];
			hash2index[left] = hash2index[right];
			hash2index[right] = temp;

			return i+1;
		}

		protected final E @NonNull [] elements;
		/**
		 * The sorted hash codes of each element (bottom 32 bits) and indexes of each element (top 32 bits)
		 */
		protected long [] hash2index = null;

		public HashedCoCollection(Class<E> jClass, E @NonNull [] elements) {
			super(jClass);
			this.elements = elements;
		}

		@SuppressWarnings("unchecked")
		public HashedCoCollection(@NonNull Class<E> jClass, @NonNull List<E> elements) {
			super(jClass);
			this.elements = elements.toArray((E @NonNull [])Array.newInstance(jClass, elements.size()));
		}

		//	public HashedCoCollection(@NonNull Class<E> jClass, E @NonNull [] elements, long @NonNull [] hash2index) {
		//		super(jClass);
		//		this.elements = elements;
		//		this.hash2index = hash2index;
		//	}

		private void computeHashesAndIndexes() {
			int iMax = elements.length;
			this.hash2index = new long[iMax];
			for (int i = 0; i < iMax; i++) {
				E element = elements[i];
				long hash = getHashCode(element);
				this.hash2index[i] = ((long)i << 32) | hash;
			}
			//	Arrays.sort(indexes1, new HashComparator(sortedHashes));
			sort2(0, iMax-1);
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

		@Override
		public boolean contains(Object o) {
			if (hash2index == null) {
				for (E element : elements) {
					if (!Objects.equals(element, o)) {
						return true;
					}
				}
				return false;
			}
			else {
				return binarySearch2(o) >= 0;
			}
		}

		@Override
		public boolean containsAll(Collection<?> c) {
			if (hash2index == null) {
				for (E thisElement : elements) {
					boolean gotIt = true;
					for (Object thatElement : c) {
						if (Objects.equals(thisElement, thatElement)) {
							gotIt = true;
							break;
						}
					}
					if (!gotIt) {
						return false;
					}
				}
			}
			else {
				for (Object thatElement : c) {
					if (binarySearch2(thatElement) < 0) {
						return false;
					}
				}
			}
			return true;
		}

		@Override
		public @NonNull CoCollection<E> intersection(@NonNull CoCollection<E> coCollection) {
			if (coCollection instanceof HashedCoCollection) {
				return intersection((HashedCoCollection<E>)coCollection);
			}
			return coCollection.intersection(this);			// XXX order
		}

		public @NonNull CoCollection<E> intersection(@NonNull HashedCoCollection<E> that) {
			int iThis = 0;
			int jThat = 0;
			int k = 0;
			int iMax = this.size();
			int jMax = that.size();
			int [] indexIntersections = new int[iMax];
			//	Set<Integer> debugIndexIntersections = new HashSet<>(iMax);
			while (iThis < iMax) {
				long thisHashLine = this.hash2index[iThis];
				long iHash = thisHashLine & 0xFFFFFFFFL;
				while (jThat < jMax) {
					long thatHashLine = that.hash2index[jThat];
					long jHash = thatHashLine & 0xFFFFFFFFL;
					if (jHash > iHash) {
						break;
					}
					if (jHash == iHash) {
						//	int thisIndex = (int)(thisHashLine >> 32);
						//	int thatIndex = (int)(thatHashLine >> 32);
						//	E thisElement = this.elements[thisIndex];
						//	E thatElement = that.elements[thatIndex];
						//	if (thisElement == thatElement) {
						//		indexIntersections[k++] = thisIndex;
						//		assert debugIndexIntersections.add(thisIndex);
						//	}
						//	else {
						int k0 = k;
						for (int iNextThis = iThis; iNextThis < iMax; iNextThis++) {
							long nextThisHashLine = this.hash2index[iNextThis];
							if (iHash != (nextThisHashLine & 0xFFFFFFFFL)) {
								break;
							}
							int nextThisIndex = (int)(nextThisHashLine >> 32);
							E nextThisElement = this.elements[nextThisIndex];
							for (int jNextThat = jThat; jNextThat < jMax; jNextThat++) {
								long nextThatHashLine = that.hash2index[jNextThat];
								if (jHash != (nextThatHashLine & 0xFFFFFFFFL)) {
									break;
								}
								int nextThatIndex = (int)(nextThatHashLine >> 32);
								E nextThatElement = that.elements[nextThatIndex];
								if (nextThisElement == nextThatElement) {
									boolean gotIt = false;
									for (int k2 = k0; k2 < k; k2++) {
										if (indexIntersections[k2] == nextThisIndex) {
											gotIt = true;
											break;
										}
									}
									if (!gotIt) {
										indexIntersections[k++] = nextThisIndex;
										//	assert debugIndexIntersections.add(nextThisIndex);
									}
								}
							}
						}
						//	}
					}
					jThat++;
					while (jThat < jMax) {
						long thatHashLine2 = that.hash2index[jThat];
						long jHash2 = thatHashLine2 & 0xFFFFFFFFL;
						if (jHash2 != jHash) {
							break;
						}
						jThat++;
					}
				}
				iThis++;
				while (iThis < iMax) {
					long thisHashLine2 = this.hash2index[iThis];
					long iHash2 = thisHashLine2 & 0xFFFFFFFFL;
					if (iHash2 != iHash) {
						break;
					}
					iThis++;
				}
			}
			if (k > 1) {
				Arrays.sort(indexIntersections, 0, k);
			}
			@SuppressWarnings("unchecked")
			E [] elementIntersections = (E @NonNull []) Array.newInstance(this.jClass, k);
			int q = 0;
			for (int p = 0; p < k; p++) {
				int elementIndex = indexIntersections[p];
				elementIntersections[q++] = this.elements[elementIndex];
				if (q >= k) {
					break;
				}
			}
			return CoCollection.create(this.jClass, elementIntersections);
		}

		@Override
		public boolean isEmpty() {
			return false;
		}

		@Override
		public java.util.@NonNull Iterator<E> iterator() {
			return new HashedIterator<E>(elements);
		}

		@Override
		public void setSettable() {
			if (hash2index == null) {
				computeHashesAndIndexes();
			}
		}

		@Override
		public int size() {
			return elements.length;
		}

		@Override
		public Object[] toArray() {
			return new Object[] {elements};
		}

		@SuppressWarnings("unchecked")
		@Override
		public <T> T @NonNull [] toArray(T @NonNull [] a) {
			if (a.length < elements.length) {
				return (T @NonNull []) new Object[] {elements};
			}
			System.arraycopy(elements, 0, a, 0, elements.length);
			return a;
		}
	}

	public static class TinyCoCollection<E> extends HashedCoCollection<E>
	{
		public TinyCoCollection(Class<E> jClass, E @NonNull [] elements) {
			super(jClass, elements);
		}

		//	public TinyCoCollection(@NonNull Class<E> jClass, @NonNull List<E> elements) {
		//		super(jClass, elements);
		//	}

		//	public TinyCoCollection(@NonNull Class<E> jClass, E @NonNull [] elements, long @NonNull [] hash2index) {
		//		super(jClass, elements, hash2index);
		//	}

		@Override
		public boolean contains(Object o) {
			for (E element : elements) {
				if (!Objects.equals(element, o)) {
					return true;
				}
			}
			return false;
		}

		@Override
		public boolean containsAll(Collection<?> c) {
			for (E thisElement : elements) {
				boolean gotIt = true;
				for (Object thatElement : c) {
					if (Objects.equals(thisElement, thatElement)) {
						gotIt = true;
						break;
					}
				}
				if (!gotIt) {
					return false;
				}
			}
			return true;
		}

		@Override
		public void setSettable() {
			if (hash2index == null) {
				hash2index = new long[elements.length];
				int iElement = 0;
				int jLast = 0;
				for (E element : elements) {
					long iHash = getHashCode(element);
					long iHashLine = ((long)iElement << 32) | iHash;
					iElement++;
					int j = 0;
					for (; j < jLast; j++) {
						long jHashLine = hash2index[j];
						long jHash = jHashLine & 0xFFFFFFFFL;
						if (iHash < jHash) {
							for (int jj = jLast; jj > j; jj--) {
								hash2index[jj] = hash2index[jj-1];
							}
							break;
						}
					}
					hash2index[j] = iHashLine;
					jLast++;
				}
			}
		}
	}

	public static class SmallCoCollection<E> extends HashedCoCollection<E>
	{
		public SmallCoCollection(Class<E> jClass, E @NonNull [] elements) {
			super(jClass, elements);
		}

		//	public SmallCoCollection(Class<E> jClass, @NonNull List<E> elements) {
		//		super(jClass, elements);
		//	}
	}

	public static class SmallWellHashedCoCollection extends SmallCoCollection<@NonNull PseudoNamedElement>
	{
		public SmallWellHashedCoCollection(Class<@NonNull PseudoNamedElement> jClass, @NonNull PseudoNamedElement @NonNull [] elements) {
			super(jClass, elements);
		}

		//	public SmallWellHashedCoCollection(Class<PseudoNamedElement> jClass, @NonNull List<PseudoNamedElement> elements) {
		//		super(jClass, elements);
		//	}

		@Override
		protected long getHashCode(@NonNull PseudoNamedElement object) {
			return object.getId();
		}

		@Override
		public @NonNull CoCollection<@NonNull PseudoNamedElement> intersection(@NonNull HashedCoCollection<@NonNull PseudoNamedElement> that) {
			int iThis = 0;
			int jThat = 0;
			int k = 0;
			int iMax = this.size();
			int jMax = that.size();
			int [] indexIntersections = new int[iMax];
			//	Set<Integer> debugIndexIntersections = new HashSet<>(iMax);
			while (iThis < iMax) {
				long thisHashLine = this.hash2index[iThis];
				long iHash = thisHashLine & 0xFFFFFFFFL;
				while (jThat < jMax) {
					long thatHashLine = that.hash2index[jThat];
					long jHash = thatHashLine & 0xFFFFFFFFL;
					if (jHash > iHash) {
						break;
					}
					if (jHash == iHash) {
						int thisIndex = (int)(thisHashLine >> 32);
						indexIntersections[k++] = thisIndex;
					}
					jThat++;
				}
				iThis++;
			}
			if (k > 1) {
				Arrays.sort(indexIntersections, 0, k);
			}
			PseudoNamedElement [] elementIntersections = (PseudoNamedElement[]) Array.newInstance(this.jClass, k);
			int q = 0;
			for (int p = 0; p < k; p++) {
				int elementIndex = indexIntersections[p];
				elementIntersections[q++] = this.elements[elementIndex];
				if (q >= k) {
					break;
				}
			}
			return CoCollection.create(this.jClass, elementIntersections);
		}
	}

	public static class LargeCoCollection<E> extends HashedCoCollection<E>
	{
		protected final @NonNull Map<E, @NonNull Integer> map;

		public LargeCoCollection(Class<E> jClass, E @NonNull [] elements) {
			super(jClass, elements);
			int size = elements.length;
			this.map = new HashMap<>(size);
			for (int i = 0; i < size; i++) {
				@SuppressWarnings("unused")
				Integer old = map.put(elements[i], i);
				//	assert old == null;			// XXX debugging
			}
		}

		//	public LargeCoCollection(@NonNull Class<E> jClass, @NonNull List<E> elements) {
		//		super(jClass, elements);
		//		int size = elements.size();
		//		this.map = new HashMap<>(size);
		//		for (int i = 0; i < size; i++) {
		//			map.put(elements.get(i), i);
		//		}
		//	}

		@Override
		public boolean contains(Object o) {
			return map.containsKey(o);
		}

		@Override
		public boolean containsAll(Collection<?> c) {
			for (Object element : c) {
				if (!map.containsKey(element)) {
					return false;
				}
			}
			return true;
		}

		@Override
		public @NonNull CoCollection<E> intersection(@NonNull CoCollection<E> that) {
			Set<E> resultSet = new HashSet<>(map.keySet());
			resultSet.retainAll(that);
			int size = resultSet.size();
			int [] resultIndexes = new int[size];
			int i = 0;
			for (E element : resultSet) {
				Integer index = map.get(element);
				assert index != null;
				resultIndexes[i++] = index;
			}
			Arrays.sort(resultIndexes);
			@SuppressWarnings("unchecked")
			E [] resultArray = (E []) Array.newInstance(jClass, size);
			int j = 0;
			for (int index : resultIndexes) {
				resultArray[j++] = elements[index];
			}
			return CoCollection.create(jClass, resultArray);
		}

		@Override
		public @NonNull Iterator<E> iterator() {
			return map.keySet().iterator();				// XXX order
		}

		@Override
		public void setSettable() {}
	}


	public static class NonDeterminateJavaSet<E> extends CoCollection<E>
	{
		protected final @NonNull Set<E> set;

		@SuppressWarnings("null")
		public NonDeterminateJavaSet(Class<E> jClass, E @NonNull [] elements) {
			super(jClass);
			this.set = Sets.newHashSet(elements);
		}

		@SuppressWarnings("null")
		public NonDeterminateJavaSet(Class<E> jClass, @NonNull Collection<E> elements) {
			super(jClass);
			this.set = Sets.newHashSet(elements);
		}

		public NonDeterminateJavaSet(Class<E> jClass, @NonNull Set<E> elements) {
			super(jClass);
			this.set = elements;
		}

		@Override
		public boolean contains(Object o) {
			return set.contains(o);
		}

		@Override
		public boolean containsAll(Collection<?> c) {
			return set.containsAll(c);
		}

		@Override
		public @NonNull CoCollection<E> intersection(@NonNull CoCollection<E> that) {
			Set<E> resultSet = new HashSet<>(set);
			resultSet.retainAll(that);
			return new NonDeterminateJavaSet(jClass, resultSet);
		}

		@Override
		public boolean isEmpty() {
			return set.isEmpty();
		}

		@Override
		public @NonNull Iterator<E> iterator() {
			return set.iterator();				// XXX order
		}

		@Override
		public void setSettable() {}

		@Override
		public int size() {
			return set.size();
		}

		@Override
		public Object[] toArray() {
			return set.toArray();
		}

		@Override
		public <T> T @NonNull [] toArray(T @NonNull [] a) {
			return set.toArray(a);
		}
	}
}
