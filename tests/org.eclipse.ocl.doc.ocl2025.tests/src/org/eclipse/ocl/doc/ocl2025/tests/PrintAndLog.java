/*******************************************************************************
 * Copyright (c) 2017, 2018 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.doc.ocl2025.tests;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class PrintAndLog
{
	public static final int WARMUP_TEST_SIZE = 5000;
	public static final int MAX_TEST_SIZE = 1000000;

	public static void garbageCollect() throws InterruptedException {
		for (int y = 0; y < 5; y++) {
			System.gc();
			Thread.sleep(100);
		}
	}

	public static int[] getTestSizes() {
		return getTestSizes(20, 5, 6, 1);
	}

	public static int[] getTestSizes(int warmup, int decades, int testsPerDecade, int repeats) {
		int tests = decades * testsPerDecade + 1;
		int[] testSizes = new int[warmup+(tests+1)*repeats];
		for (int i = 0; i < warmup; i++) {
			testSizes[i] = WARMUP_TEST_SIZE;
		}
		for (int i = 1; i <= tests; i++) {
			for (int j = 0; j < repeats; j++) {
				//		testSizes[(i-1)*repeats+1+j+warmup-1] = (int)Math.round(Math.pow(10.0, (i+testsPerDecade-1)/(double)testsPerDecade));
				testSizes[(i-1)*repeats+1+j+warmup-1] = i == 0 ? 0 : (int)Math.round(Math.pow(10.0, (i-1)/(double)testsPerDecade));
			}
		}
		//	testSizes[warmup+tests] = MAX_TEST_SIZE;
		return testSizes;
	}

	private PrintWriter writer;

	public PrintAndLog(String testName) throws IOException {
		//	String testSuiteName = System.getProperty("testSuiteName", null);
		//		assert testSuiteName != null;
		//	if (testSuiteName != null) {
		File file = new File("results/" + testName + ".csv");
		file.getParentFile().mkdirs();
		writer = new PrintWriter(new FileWriter(file));
		//	}
	}

	public void dispose() throws IOException {
		if (writer != null) {
			writer.close();
		}
	}

	public void printf(String format, Object ... args) {
		if (writer != null) {
			writer.printf(format, args);
			//		writer.flush();
		}
		//	return System.out.printf(format, args);
	}
}