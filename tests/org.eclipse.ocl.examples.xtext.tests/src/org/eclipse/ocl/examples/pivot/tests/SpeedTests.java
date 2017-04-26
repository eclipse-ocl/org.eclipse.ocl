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

import org.eclipse.ocl.pivot.ids.CollectionTypeId;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.utilities.ValueUtil;
import org.eclipse.ocl.pivot.values.SetValue;

/**
 * A test case that demonstrates a memory leak in the OCL Validation infrastructure.
 */
public class SpeedTests extends PivotTestCase
{
	public static class PrintAndLog
	{
		public static int[] getTestSizes() {
			int[] testSizes = new int[38];
			testSizes[0] = 5000;
			for (int i = 1; i < testSizes.length; i++) {
				testSizes[i] = (int)Math.round(Math.pow(10.0, (i+5)/6.0));
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

	public void testNewCollection() throws Exception {
		//		OCL ocl = OCL.newInstance(OCL.NO_PROJECTS);
		PrintAndLog logger = new PrintAndLog(getName());
		logger.printf("%s\n", getName());
		TypeId elementTypeId = TypeId.INTEGER;
		CollectionTypeId collectionTypedId = TypeId.SET.getSpecializedId(elementTypeId);
		int[] tests = PrintAndLog.getTestSizes();
		for (int testSize : tests) {
			garbageCollect();
			logger.printf("%9d, ", testSize);
			Object[] values = new Integer[testSize];
			long startTime0 = System.nanoTime();
			int hashIn = 0;
			for (Integer i = 0; i < testSize; i++) {
				values[i] = i;
				hashIn += i.hashCode();
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
			assert hashOut == hashIn;
			garbageCollect();
		}
		//		ocl.dispose();
	}
}
