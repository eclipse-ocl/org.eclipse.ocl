/*
 * Copyright (c) 2015 Christian W. Damus and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Christian W. Damus - Initial API and implementation
 *
 */

package org.eclipse.ocl.examples.pivot.tests;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.ids.CollectionTypeId;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.iterators.IncludingIterator;
import org.eclipse.ocl.pivot.library.collection.CollectionIncludingOperation;
import org.eclipse.ocl.pivot.library.collection.CollectionMutableIncludingOperation;
import org.eclipse.ocl.pivot.utilities.ValueUtil;
import org.eclipse.ocl.pivot.values.CollectionValue;
import org.eclipse.ocl.pivot.values.IntegerValue;
import org.eclipse.ocl.pivot.values.SequenceValue;
import org.eclipse.ocl.pivot.values.SetValue;

import junit.framework.TestCase;

/**
 * These tests contribute to the OCL 2017 Deterministic Lazy OCL Collections paper.
 */
public class SpeedTests extends PivotTestCase
{
	public static class PrintAndLog
	{
		public static int[] getTestSizes() {
			return getTestSizes(500000, 38);
		}

		public static int[] getTestSizes(int warmUp, int max) {
			int[] testSizes = new int[max];
			int j = 0;
			testSizes[j++] = warmUp;
			for (int i = testSizes.length-1; i >= 1; i--) {
				testSizes[j++] = (int)Math.round(Math.pow(10.0, (i+5)/6.0));
			}
			return testSizes;
		}

		private PrintWriter writer;

		public PrintAndLog(String testName) throws IOException {
			String testSuiteName = System.getProperty("testSuiteName", null);
			//		assert testSuiteName != null;
			if (testSuiteName != null) {
				File file = new File("results/" + testSuiteName + ".csv");
				file.getParentFile().mkdirs();
				writer = new PrintWriter(new FileWriter(file));
			}
		}

		public void dispose() throws IOException {
			if (writer != null) {
				writer.close();
			}
		}

		public PrintStream printf(String format, Object ... args) {
			if (writer != null) {
				writer.printf(format, args);
				writer.flush();
			}
			return System.out.printf(format, args);
		}
	}

	public static void garbageCollect() throws InterruptedException {
		for (int y = 0; y < 5; y++) {
			System.gc();
			Thread.sleep(100);
		}
	}

	public void testCreateAndIterateCollection() throws Exception {		// Old (master) and New (ewillink/509670)
		PrintAndLog logger = new PrintAndLog(getName());
		logger.printf("%s\n", getName());
		TypeId elementTypeId = TypeId.INTEGER;
		CollectionTypeId collectionTypedId = TypeId.SET.getSpecializedId(elementTypeId);
		int[] tests = PrintAndLog.getTestSizes();
		for (int testSize : tests) {
			garbageCollect();
			logger.printf("%9d, ", testSize);
			Object[] values = new IntegerValue[testSize];
			long startTime0 = System.nanoTime();
			int hashIn = 0;
			for (int i = 0; i < testSize; i++) {
				IntegerValue integerValue = ValueUtil.integerValueOf(i);
				values[i] = integerValue;
				hashIn += integerValue.hashCode();
			}
			long endTime0 = System.nanoTime();
			logger.printf("%9.6f, ", (endTime0 - startTime0) / 1.0e9);
			long startTime1 = System.nanoTime();
			SetValue setValue = ValueUtil.createSetOfEach(collectionTypedId, values);
			long endTime1 = System.nanoTime();
			logger.printf("%9.6f, ", (endTime1 - startTime1) / 1.0e9);
			long startTime2 = System.nanoTime();
			Iterable setValue2 = setValue.iterable();
			long endTime2 = System.nanoTime();
			logger.printf("%9.6f, ", (endTime2 - startTime2) / 1.0e9);
			long startTime3 = System.nanoTime();
			int hashOut = 0;
			for (Object o : setValue2) {
				hashOut += o.hashCode();
			}
			long endTime3 = System.nanoTime();
			logger.printf("%9.6f\n", (endTime3 - startTime3) / 1.0e9);
			TestCase.assertEquals(hashOut, hashIn);
			garbageCollect();
		}
	}

	public void testDoubleIncludingNew() throws Exception {	// New (ewillink/509670)
		PrintAndLog logger = new PrintAndLog(getName());
		logger.printf("%s\n", getName());
		TypeId elementTypeId = TypeId.INTEGER;
		CollectionTypeId collectionTypedId = TypeId.SEQUENCE.getSpecializedId(elementTypeId);
		int[] tests = PrintAndLog.getTestSizes();
		for (int testSize : tests) {
			garbageCollect();
			logger.printf("%9d, ", testSize);
			Object[] values = new IntegerValue[testSize-2];
			long startTime0 = System.nanoTime();
			int hashIn = 0;
			int b = testSize / 3;
			int c = (2*testSize) / 3;
			for (int i = 0, j = 0; i < testSize; i++) {
				IntegerValue integerValue = ValueUtil.integerValueOf(i);
				if ((i != b) && (i != c)) {
					values[j++] = integerValue;
				}
				hashIn += integerValue.hashCode();
				//				System.out.println(integerValue + " " + hashIn);
			}
			long endTime0 = System.nanoTime();
			logger.printf("%9.6f, ", (endTime0 - startTime0) / 1.0e9);
			long startTime1 = System.nanoTime();
			SequenceValue seqValue = ValueUtil.createSequenceOfEach(collectionTypedId, values);
			long endTime1 = System.nanoTime();
			logger.printf("%9.6f, ", (endTime1 - startTime1) / 1.0e9);
			long startTime2 = System.nanoTime();
			CollectionValue includingB = IncludingIterator.including(collectionTypedId, seqValue, ValueUtil.integerValueOf(b));
			CollectionValue includingC = IncludingIterator.including(collectionTypedId, includingB, ValueUtil.integerValueOf(c));
			long endTime2 = System.nanoTime();
			logger.printf("%9.6f, ", (endTime2 - startTime2) / 1.0e9);
			long startTime3 = System.nanoTime();
			int hashOut = 0;
			for (Object o : includingC) {
				hashOut += o.hashCode();
				//				System.out.println(o + " " + hashOut);
			}
			long endTime3 = System.nanoTime();
			logger.printf("%9.6f\n", (endTime3 - startTime3) / 1.0e9);
			TestCase.assertEquals(hashOut, hashIn);
			garbageCollect();
		}
	}

	public void testDoubleIncludingOld() throws Exception {	// Old (master)
		PrintAndLog logger = new PrintAndLog(getName());
		logger.printf("%s\n", getName());
		TypeId elementTypeId = TypeId.INTEGER;
		CollectionTypeId collectionTypedId = TypeId.SEQUENCE.getSpecializedId(elementTypeId);
		int[] tests = PrintAndLog.getTestSizes();
		//		for (int t = tests.length; --t >= 0; ) {
		//			int testSize = tests[t];
		for (int testSize : tests) {
			garbageCollect();
			logger.printf("%9d, ", testSize);
			Object[] values = new IntegerValue[testSize-2];
			long startTime0 = System.nanoTime();
			int hashIn = 0;
			int b = testSize / 3;
			int c = (2*testSize) / 3;
			for (int i = 0, j = 0; i < testSize; i++) {
				IntegerValue integerValue = ValueUtil.integerValueOf(i);
				if ((i != b) && (i != c)) {
					values[j++] = integerValue;
				}
				hashIn += integerValue.hashCode();
				//				System.out.println(integerValue + " " + hashIn);
			}
			long endTime0 = System.nanoTime();
			logger.printf("%9.6f, ", (endTime0 - startTime0) / 1.0e9);
			long startTime1 = System.nanoTime();
			SequenceValue seqValue = ValueUtil.createSequenceOfEach(collectionTypedId, values);
			long endTime1 = System.nanoTime();
			logger.printf("%9.6f, ", (endTime1 - startTime1) / 1.0e9);
			long startTime2 = System.nanoTime();
			CollectionValue includingB = CollectionIncludingOperation.INSTANCE.evaluate(seqValue, ValueUtil.integerValueOf(b));
			CollectionValue includingC = CollectionIncludingOperation.INSTANCE.evaluate(includingB, ValueUtil.integerValueOf(c));
			long endTime2 = System.nanoTime();
			logger.printf("%9.6f, ", (endTime2 - startTime2) / 1.0e9);
			long startTime3 = System.nanoTime();
			int hashOut = 0;
			for (Object o : includingC) {
				hashOut += o.hashCode();
				//				System.out.println(o + " " + hashOut);
			}
			long endTime3 = System.nanoTime();
			logger.printf("%9.6f\n", (endTime3 - startTime3) / 1.0e9);
			TestCase.assertEquals(hashOut, hashIn);
			garbageCollect();
		}
	}

	public void testImmutableSequenceIncludingOld() throws Exception {	// Old (master)
		PrintAndLog logger = new PrintAndLog(getName());
		logger.printf("%s\n", getName());
		TypeId elementTypeId = TypeId.INTEGER;
		CollectionTypeId collectionTypedId = TypeId.SEQUENCE.getSpecializedId(elementTypeId);
		int[] tests = PrintAndLog.getTestSizes(50000, 30);
		//		for (int t = tests.length; --t >= 0; ) {
		//			int testSize = tests[t];
		@NonNull Object @NonNull [] noValues = new @NonNull IntegerValue[] {};
		for (int testSize : tests) {
			garbageCollect();
			logger.printf("%9d, ", testSize);
			Object[] values = new IntegerValue[testSize];
			long startTime0 = System.nanoTime();
			int hashIn = 0;
			for (int i = 0; i < testSize; i++) {
				IntegerValue integerValue = ValueUtil.integerValueOf(i);
				values[i] = integerValue;
				hashIn += integerValue.hashCode();
				//				System.out.println(integerValue + " " + hashIn);
			}
			long endTime0 = System.nanoTime();
			logger.printf("%9.6f, ", (endTime0 - startTime0) / 1.0e9);
			long startTime1 = System.nanoTime();
			CollectionValue seqValue = ValueUtil.createSequenceOfEach(collectionTypedId, noValues);
			long endTime1 = System.nanoTime();
			logger.printf("%9.6f, ", (endTime1 - startTime1) / 1.0e9);
			long startTime2 = System.nanoTime();
			for (int i = 0, j = 0; i < testSize; i++) {
				seqValue = CollectionIncludingOperation.INSTANCE.evaluate(seqValue, values[i]);
				//				System.out.println(integerValue + " " + hashIn);
			}
			long endTime2 = System.nanoTime();
			logger.printf("%9.6f, ", (endTime2 - startTime2) / 1.0e9);
			long startTime3 = System.nanoTime();
			int hashOut = 0;
			for (Object o : seqValue) {
				hashOut += o.hashCode();
				//				System.out.println(o + " " + hashOut);
			}
			long endTime3 = System.nanoTime();
			logger.printf("%9.6f\n", (endTime3 - startTime3) / 1.0e9);
			TestCase.assertEquals(hashOut, hashIn);
			garbageCollect();
		}
	}

	public void testImmutableSetIncludingOld() throws Exception {	// Old (master)
		PrintAndLog logger = new PrintAndLog(getName());
		logger.printf("%s\n", getName());
		TypeId elementTypeId = TypeId.INTEGER;
		CollectionTypeId collectionTypedId = TypeId.SET.getSpecializedId(elementTypeId);
		int[] tests = PrintAndLog.getTestSizes(50000, 25);
		//		for (int t = tests.length; --t >= 0; ) {
		//			int testSize = tests[t];
		@NonNull Object @NonNull [] noValues = new @NonNull IntegerValue[] {};
		for (int testSize : tests) {
			garbageCollect();
			logger.printf("%9d, ", testSize);
			Object[] values = new IntegerValue[testSize];
			long startTime0 = System.nanoTime();
			int hashIn = 0;
			for (int i = 0; i < testSize; i++) {
				IntegerValue integerValue = ValueUtil.integerValueOf(i);
				values[i] = integerValue;
				hashIn += integerValue.hashCode();
				//				System.out.println(integerValue + " " + hashIn);
			}
			long endTime0 = System.nanoTime();
			logger.printf("%9.6f, ", (endTime0 - startTime0) / 1.0e9);
			long startTime1 = System.nanoTime();
			CollectionValue setValue = ValueUtil.createSetOfEach(collectionTypedId, noValues);
			long endTime1 = System.nanoTime();
			logger.printf("%9.6f, ", (endTime1 - startTime1) / 1.0e9);
			long startTime2 = System.nanoTime();
			for (int i = 0, j = 0; i < testSize; i++) {
				setValue = CollectionIncludingOperation.INSTANCE.evaluate(setValue, values[i]);
				//				System.out.println(integerValue + " " + hashIn);
			}
			long endTime2 = System.nanoTime();
			logger.printf("%9.6f, ", (endTime2 - startTime2) / 1.0e9);
			long startTime3 = System.nanoTime();
			int hashOut = 0;
			for (Object o : setValue) {
				hashOut += o.hashCode();
				//				System.out.println(o + " " + hashOut);
			}
			long endTime3 = System.nanoTime();
			logger.printf("%9.6f\n", (endTime3 - startTime3) / 1.0e9);
			TestCase.assertEquals(hashOut, hashIn);
			garbageCollect();
		}
	}

	public void testMutableSequenceIncludingNew() throws Exception {	// New (ewillink/509670)
		PrintAndLog logger = new PrintAndLog(getName());
		logger.printf("%s\n", getName());
		TypeId elementTypeId = TypeId.INTEGER;
		CollectionTypeId collectionTypedId = TypeId.SEQUENCE.getSpecializedId(elementTypeId);
		int[] tests = PrintAndLog.getTestSizes();
		//		for (int t = tests.length; --t >= 0; ) {
		//			int testSize = tests[t];
		@NonNull Object @NonNull [] noValues = new @NonNull IntegerValue[] {};
		for (int testSize : tests) {
			garbageCollect();
			logger.printf("%9d, ", testSize);
			Object[] values = new IntegerValue[testSize];
			long startTime0 = System.nanoTime();
			int hashIn = 0;
			for (int i = 0; i < testSize; i++) {
				IntegerValue integerValue = ValueUtil.integerValueOf(i);
				values[i] = integerValue;
				hashIn += integerValue.hashCode();
				//				System.out.println(integerValue + " " + hashIn);
			}
			long endTime0 = System.nanoTime();
			logger.printf("%9.6f, ", (endTime0 - startTime0) / 1.0e9);
			long startTime1 = System.nanoTime();
			CollectionValue seqValue = ValueUtil.createSequenceOfEach(collectionTypedId, noValues);
			long endTime1 = System.nanoTime();
			logger.printf("%9.6f, ", (endTime1 - startTime1) / 1.0e9);
			long startTime2 = System.nanoTime();
			for (int i = 0, j = 0; i < testSize; i++) {
				seqValue = CollectionMutableIncludingOperation.INSTANCE.evaluate(seqValue, values[i]);
				//				System.out.println(integerValue + " " + hashIn);
			}
			long endTime2 = System.nanoTime();
			logger.printf("%9.6f, ", (endTime2 - startTime2) / 1.0e9);
			long startTime3 = System.nanoTime();
			int hashOut = 0;
			for (Object o : seqValue) {
				hashOut += o.hashCode();
				//				System.out.println(o + " " + hashOut);
			}
			long endTime3 = System.nanoTime();
			logger.printf("%9.6f\n", (endTime3 - startTime3) / 1.0e9);
			TestCase.assertEquals(hashOut, hashIn);
			garbageCollect();
		}
	}

	public void testMutableSetIncludingNew() throws Exception {	// New (ewillink/509670)
		PrintAndLog logger = new PrintAndLog(getName());
		logger.printf("%s\n", getName());
		TypeId elementTypeId = TypeId.INTEGER;
		CollectionTypeId collectionTypedId = TypeId.SET.getSpecializedId(elementTypeId);
		int[] tests = PrintAndLog.getTestSizes();
		//		for (int t = tests.length; --t >= 0; ) {
		//			int testSize = tests[t];
		@NonNull Object @NonNull [] noValues = new @NonNull IntegerValue[] {};
		for (int testSize : tests) {
			garbageCollect();
			logger.printf("%9d, ", testSize);
			Object[] values = new IntegerValue[testSize];
			long startTime0 = System.nanoTime();
			int hashIn = 0;
			for (int i = 0; i < testSize; i++) {
				IntegerValue integerValue = ValueUtil.integerValueOf(i);
				values[i] = integerValue;
				hashIn += integerValue.hashCode();
				//				System.out.println(integerValue + " " + hashIn);
			}
			long endTime0 = System.nanoTime();
			logger.printf("%9.6f, ", (endTime0 - startTime0) / 1.0e9);
			long startTime1 = System.nanoTime();
			CollectionValue setValue = ValueUtil.createSetOfEach(collectionTypedId, noValues);
			long endTime1 = System.nanoTime();
			logger.printf("%9.6f, ", (endTime1 - startTime1) / 1.0e9);
			long startTime2 = System.nanoTime();
			for (int i = 0, j = 0; i < testSize; i++) {
				setValue = CollectionMutableIncludingOperation.INSTANCE.evaluate(setValue, values[i]);
				//				System.out.println(integerValue + " " + hashIn);
			}
			long endTime2 = System.nanoTime();
			logger.printf("%9.6f, ", (endTime2 - startTime2) / 1.0e9);
			long startTime3 = System.nanoTime();
			int hashOut = 0;
			for (Object o : setValue) {
				hashOut += o.hashCode();
				//				System.out.println(o + " " + hashOut);
			}
			long endTime3 = System.nanoTime();
			logger.printf("%9.6f\n", (endTime3 - startTime3) / 1.0e9);
			TestCase.assertEquals(hashOut, hashIn);
			garbageCollect();
		}
	}
}
