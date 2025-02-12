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
import java.io.PrintStream;
import java.io.PrintWriter;

public class PrintAndLog
{
	public static int[] getTestSizes() {
		int warmup = 100;
		int tests = 37;
		int[] testSizes = new int[warmup+tests];
		for (int i = 0; i < warmup; i++) {
			testSizes[i] = 5000;
		}
		for (int i = 1; i <= testSizes.length-warmup; i++) {
			testSizes[i+warmup-1] = (int)Math.round(Math.pow(10.0, (i+5)/6.0));
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