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

import java.util.HashSet;
import java.util.Set;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.doc.ocl2025.tests.PrintAndLog;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

/**
 * Source code for OCL 2025, Collection optimization paper.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class OCL2025_SelectReject_Tests2 //extends AbstractOCL2025CGTests
{
	static PrintAndLog logger = null;

	public static class PseudoNamedElement
	{
		private final int id;

		public PseudoNamedElement() {
			this.id = System.identityHashCode(this);
		}

		@Override
		public int hashCode() {
			return id;
		}

		@Override
		public boolean equals(Object obj) {
			return this == obj;
		}

		public @Nullable String getName() {
			return "xyzzy";
		}
	}

	private static @NonNull PseudoNamedElement [] elementsArray = null;
	private static ComplexLatexSummary summary = null;
	private static int[] testSizes = null;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		//	System.out.println("setUpBeforeClass");
		testSizes = PrintAndLog.getTestSizes(1000, 5, 1, 10);
		summary = new ComplexLatexSummary(OCL2025_SelectReject_Tests2.class.getSimpleName());
		elementsArray = new @NonNull PseudoNamedElement [PrintAndLog.MAX_TEST_SIZE];
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
		testSizes = null;
	}

	@Test
	public void testSelectReject_1_SetSetSize() throws Exception {
		String testName = "Set,Set,Size"; //getName();
		PrintAndLog logger = new PrintAndLog(testName);
		logger.printf("%s\n", testName);
		summary.printf("%s", testName);
		try {
			for (int iTest = 0; iTest < testSizes.length; iTest++) {
				int testSize = testSizes[iTest];
				//				garbageCollect();
				//		logger.printf("%9d: ", testSize);
				long startTime = System.nanoTime();
				long endTime1 = System.nanoTime();
				Set<@NonNull PseudoNamedElement> selectedSet = new HashSet<>();
				for (int i = 0; i < testSize; i++) {
					@NonNull PseudoNamedElement element = elementsArray[i];
					if (element.getName() != null) {
						selectedSet.add(element);
					}
				}
				long endTime2 = System.nanoTime();
				Set<PseudoNamedElement> selectedRejectedSet = new HashSet<>();
				for (PseudoNamedElement element : selectedSet) {
					String name = element.getName();
					assert name != null;
					if (!name.equals("")) {
						selectedRejectedSet.add(element);
					}
				}
				long endTime3 = System.nanoTime();
				if (selectedRejectedSet.size() <= 0) {
					assert false;
				}
				long endTime4 = System.nanoTime();
				double scale = testSize;
				logger.printf("%9d : %9.3f + %9.3f + %9.3f + %9.3f = %9.3fns/e : %9.6fs\n", testSize, (endTime1 - startTime) / scale, (endTime2 - endTime1) / scale, (endTime3 - endTime2) / scale, (endTime4 - endTime3) / scale, (endTime4 - startTime) / scale, (endTime4 - startTime) / 1.0e9);
				//	randomIntegerListGenerator.checkResult(newList, testSize);
				if (testSize != PrintAndLog.WARMUP_TEST_SIZE) {
					summary.printf(" & %9.6f", ((endTime4 - startTime) / 1.0e9));
				}
				if (testSize != PrintAndLog.WARMUP_TEST_SIZE) {
					summary.keyedprintf(testSize, "size", "%9d", testSize);
					summary.keyedprintf(testSize, "null", "%9.3f", ((endTime1 - startTime) / scale));
					summary.keyedprintf(testSize, "select", "%9.3f", ((endTime2 - endTime1) / scale));
					summary.keyedprintf(testSize, "reject", "%9.3f", ((endTime3 - endTime2) / scale));
					summary.keyedprintf(testSize, "result", "%9.3f", ((endTime4 - endTime3) / scale));
					summary.keyedprintf(testSize, "total ns/e", "%9.3f", ((endTime4 - startTime) / scale));
					summary.keyedprintf(testSize, "total s", "%9.6f", ((endTime4 - startTime) / 1.0e9));
				}
				if ((testSize != PrintAndLog.WARMUP_TEST_SIZE) && (((iTest + 1) >= testSizes.length) || (testSize != testSizes[iTest+1]))) {
					logger.printf("%s", summary.average(testSize));
				}
			}
			summary.flush(testName);
		}
		finally {
			logger.dispose();
		}
	}

	@Test
	public void testSelectReject_2_SetSet() throws Exception {
		String testName = "Set,Set";//getName();
		PrintAndLog logger = new PrintAndLog(testName);
		logger.printf("%s\n", testName);
		summary.printf("%s", testName);
		try {
			for (int iTest = 0; iTest < testSizes.length; iTest++) {
				int testSize = testSizes[iTest];
				//				garbageCollect();
				//		logger.printf("%9d: ", testSize);
				long startTime = System.nanoTime();
				long endTime1 = System.nanoTime();
				Set<@NonNull PseudoNamedElement> selectedSet = new HashSet<>();
				for (int i = 0; i < testSize; i++) {
					@NonNull PseudoNamedElement element = elementsArray[i];
					if (element.getName() != null) {
						selectedSet.add(element);
					}
				}
				long endTime2 = System.nanoTime();
				Set<PseudoNamedElement> selectedRejectedSet = new HashSet<>();
				for(PseudoNamedElement element : selectedSet) {
					String name = element.getName();
					assert name != null;
					if (!name.equals("")) {
						selectedRejectedSet.add(element);
					}
				}
				long endTime3 = System.nanoTime();
				//	selectedRejectedSet.getClass();
				long endTime4 = System.nanoTime();
				double scale = testSize;
				logger.printf("%9d : %9.3f + %9.3f + %9.3f + %9.3f = %9.3fns/e : %9.6fs\n", testSize, (endTime1 - startTime) / scale, (endTime2 - endTime1) / scale, (endTime3 - endTime2) / scale, (endTime4 - endTime3) / scale, (endTime4 - startTime) / scale, (endTime4 - startTime) / 1.0e9);
				//	randomIntegerListGenerator.checkResult(newList, testSize);
				if (testSize != PrintAndLog.WARMUP_TEST_SIZE) {
					summary.printf(" & %9.6f", ((endTime4 - startTime) / 1.0e9));
				}
				if (testSize != PrintAndLog.WARMUP_TEST_SIZE) {
					summary.keyedprintf(testSize, "size", "%9d", testSize);
					summary.keyedprintf(testSize, "null", "%9.3f", ((endTime1 - startTime) / scale));
					summary.keyedprintf(testSize, "select", "%9.3f", ((endTime2 - endTime1) / scale));
					summary.keyedprintf(testSize, "reject", "%9.3f", ((endTime3 - endTime2) / scale));
					summary.keyedprintf(testSize, "result", "%9.3f", ((endTime4 - endTime3) / scale));
					summary.keyedprintf(testSize, "total ns/e", "%9.3f", ((endTime4 - startTime) / scale));
					summary.keyedprintf(testSize, "total s", "%9.6f", ((endTime4 - startTime) / 1.0e9));
				}
				if ((testSize != PrintAndLog.WARMUP_TEST_SIZE) && (((iTest + 1) >= testSizes.length) || (testSize != testSizes[iTest+1]))) {
					logger.printf("%s", summary.average(testSize));
				}
			}
			summary.flush(testName);
		}
		finally {
			logger.dispose();
		}
	}

	@Test
	public void testSelectReject_3_SetSize() throws Exception {
		String testName = "Set,Size"; //getName();
		PrintAndLog logger = new PrintAndLog(testName);
		logger.printf("%s\n", testName);
		summary.printf("%s", testName);
		try {
			for (int iTest = 0; iTest < testSizes.length; iTest++) {
				int testSize = testSizes[iTest];
				//				garbageCollect();
				//		logger.printf("%9d: ", testSize);
				long startTime = System.nanoTime();
				long endTime1 = System.nanoTime();
				long endTime2 = System.nanoTime();
				Set<@NonNull PseudoNamedElement> selectedSet = new HashSet<>();
				for (int i = 0; i < testSize; i++) {
					@NonNull PseudoNamedElement element = elementsArray[i];
					String name = element.getName();
					if ((name != null) && !name.equals("")) {
						selectedSet.add(element);
					}
				}
				long endTime3 = System.nanoTime();
				if (selectedSet.size() <= 0) {
					assert false;
				}
				long endTime4 = System.nanoTime();
				double scale = testSize;
				logger.printf("%9d : %9.3f + %9.3f + %9.3f + %9.3f = %9.3fns/e : %9.6fs\n", testSize, (endTime1 - startTime) / scale, (endTime2 - endTime1) / scale, (endTime3 - endTime2) / scale, (endTime4 - endTime3) / scale, (endTime4 - startTime) / scale, (endTime4 - startTime) / 1.0e9);
				//	randomIntegerListGenerator.checkResult(newList, testSize);
				if (testSize != PrintAndLog.WARMUP_TEST_SIZE) {
					summary.printf(" & %9.6f", ((endTime4 - startTime) / 1.0e9));
				}
				if (testSize != PrintAndLog.WARMUP_TEST_SIZE) {
					summary.keyedprintf(testSize, "size", "%9d", testSize);
					summary.keyedprintf(testSize, "null", "%9.3f", ((endTime1 - startTime) / scale));
					summary.keyedprintf(testSize, "select", "%9.3f", ((endTime2 - endTime1) / scale));
					summary.keyedprintf(testSize, "reject", "%9.3f", ((endTime3 - endTime2) / scale));
					summary.keyedprintf(testSize, "result", "%9.3f", ((endTime4 - endTime3) / scale));
					summary.keyedprintf(testSize, "total ns/e", "%9.3f", ((endTime4 - startTime) / scale));
					summary.keyedprintf(testSize, "total s", "%9.6f", ((endTime4 - startTime) / 1.0e9));
				}
				if ((testSize != PrintAndLog.WARMUP_TEST_SIZE) && (((iTest + 1) >= testSizes.length) || (testSize != testSizes[iTest+1]))) {
					logger.printf("%s", summary.average(testSize));
				}
			}
			summary.flush(testName);
		}
		finally {
			logger.dispose();
		}
	}

	@Test
	public void testSelectReject_4_Count() throws Exception {
		String testName = "Count";//getName();
		PrintAndLog logger = new PrintAndLog(testName);
		logger.printf("%s\n", testName);
		summary.printf("%s", testName);
		try {
			for (int iTest = 0; iTest < testSizes.length; iTest++) {
				int testSize = testSizes[iTest];
				//				garbageCollect();
				//		logger.printf("%9d: ", testSize);
				long startTime = System.nanoTime();
				long endTime1 = System.nanoTime();
				long endTime2 = System.nanoTime();
				int size = 0;
				for (int i = 0; i < testSize; i++) {
					@NonNull PseudoNamedElement element = elementsArray[i];
					String name = element.getName();
					if ((name != null) && !name.equals("")) {
						size++;
					}
				}
				long endTime3 = System.nanoTime();
				if (size <= 0) {
					assert false;
				}
				long endTime4 = System.nanoTime();
				double scale = testSize;
				logger.printf("%9d : %9.3f + %9.3f + %9.3f + %9.3f = %9.3fns/e : %9.6fs\n", testSize, (endTime1 - startTime) / scale, (endTime2 - endTime1) / scale, (endTime3 - endTime2) / scale, (endTime4 - endTime3) / scale, (endTime4 - startTime) / scale, (endTime4 - startTime) / 1.0e9);
				//	randomIntegerListGenerator.checkResult(newList, testSize);
				if (testSize != PrintAndLog.WARMUP_TEST_SIZE) {
					summary.printf(" & %9.6f", ((endTime4 - startTime) / 1.0e9));
				}
				if (testSize != PrintAndLog.WARMUP_TEST_SIZE) {
					summary.keyedprintf(testSize, "size", "%9d", testSize);
					summary.keyedprintf(testSize, "null", "%9.3f", ((endTime1 - startTime) / scale));
					summary.keyedprintf(testSize, "select", "%9.3f", ((endTime2 - endTime1) / scale));
					summary.keyedprintf(testSize, "reject", "%9.3f", ((endTime3 - endTime2) / scale));
					summary.keyedprintf(testSize, "result", "%9.3f", ((endTime4 - endTime3) / scale));
					summary.keyedprintf(testSize, "total ns/e", "%9.3f", ((endTime4 - startTime) / scale));
					summary.keyedprintf(testSize, "total s", "%9.6f", ((endTime4 - startTime) / 1.0e9));
				}
				if ((testSize != PrintAndLog.WARMUP_TEST_SIZE) && (((iTest + 1) >= testSizes.length) || (testSize != testSizes[iTest+1]))) {
					logger.printf("%s", summary.average(testSize));
				}
			}
			summary.flush(testName);
		}
		finally {
			logger.dispose();
		}
	}

	@Test
	public void testSelectReject_5_Exists() throws Exception {
		String testName = "Exists";//getName();
		PrintAndLog logger = new PrintAndLog(testName);
		logger.printf("%s\n", testName);
		summary.printf("%s", testName);
		try {
			for (int iTest = 0; iTest < testSizes.length; iTest++) {
				int testSize = testSizes[iTest];
				//				garbageCollect();
				//		logger.printf("%9d: ", testSize);
				long startTime = System.nanoTime();
				long endTime1 = System.nanoTime();
				long endTime2 = System.nanoTime();
				boolean notEmpty = false;
				for (int i = 0; i < testSize; i++) {
					@NonNull PseudoNamedElement element = elementsArray[i];
					String name = element.getName();
					if ((name != null) && !name.equals("")) {
						notEmpty = true;
						break;
					}
				}
				long endTime3 = System.nanoTime();
				if (!notEmpty) {
					assert false;
				}
				long endTime4 = System.nanoTime();
				double scale = testSize;
				logger.printf("%9d : %9.3f + %9.3f + %9.3f + %9.3f = %9.3fns/e : %9.6fs\n", testSize, (endTime1 - startTime) / scale, (endTime2 - endTime1) / scale, (endTime3 - endTime2) / scale, (endTime4 - endTime3) / scale, (endTime4 - startTime) / scale, (endTime4 - startTime) / 1.0e9);
				//	randomIntegerListGenerator.checkResult(newList, testSize);
				if (testSize != PrintAndLog.WARMUP_TEST_SIZE) {
					summary.printf(" & %9.6f", ((endTime4 - startTime) / 1.0e9));
				}
				if (testSize != PrintAndLog.WARMUP_TEST_SIZE) {
					summary.keyedprintf(testSize, "size", "%9d", testSize);
					summary.keyedprintf(testSize, "null", "%9.3f", ((endTime1 - startTime) / scale));
					summary.keyedprintf(testSize, "select", "%9.3f", ((endTime2 - endTime1) / scale));
					summary.keyedprintf(testSize, "reject", "%9.3f", ((endTime3 - endTime2) / scale));
					summary.keyedprintf(testSize, "result", "%9.3f", ((endTime4 - endTime3) / scale));
					summary.keyedprintf(testSize, "total ns/e", "%9.3f", ((endTime4 - startTime) / scale));
					summary.keyedprintf(testSize, "total s", "%9.6f", ((endTime4 - startTime) / 1.0e9));
				}
				if ((testSize != PrintAndLog.WARMUP_TEST_SIZE) && (((iTest + 1) >= testSizes.length) || (testSize != testSizes[iTest+1]))) {
					logger.printf("%s", summary.average(testSize));
				}
			}
			summary.flush(testName);
		}
		finally {
			logger.dispose();
		}
	}
}
