/*******************************************************************************
 * Copyright (c) 2018 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.standalone.pivot.tests;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.ocl.examples.pivot.tests.PivotTestSuite;

public class StandaloneClassPathTests extends PivotTestSuite
{
	public void testClassPath() {
		String fileSeparator = System.getProperty("file.separator");
		String classPath = System.getProperty("java.class.path");
		String javaClassVersion = System.getProperty("java.class.version");
		String javaVersion = System.getProperty("java.version");
		String javaVmSpecificationVersion = System.getProperty("java.vm.specification.version");
		String javaVmVersion = System.getProperty("java.vm.version");
		String javaSpecificationVersion = System.getProperty("java.specification.version");
		String pathSeparator = System.getProperty("path.separator");
		System.out.println("java.version: " + javaVersion);
		System.out.println("java.class.version: " + javaClassVersion);
		System.out.println("java.specification.version: " + javaSpecificationVersion);
		System.out.println("java.vm.specification.version: " + javaVmSpecificationVersion);
		System.out.println("java.vm.version: " + javaVmVersion);
		String[] classPathEntries = classPath.split(pathSeparator);
		List<String> sortedEntries = new ArrayList<String>();
		String bundleSeparator = fileSeparator + "bundle" + fileSeparator;
		for (String entry : classPathEntries) {
			int index = entry.indexOf(bundleSeparator);
			if (index >= 0) {
				sortedEntries.add(entry.substring(index + 8));
			}
			else {
				sortedEntries.add(entry);
			}
		}
		Collections.sort(sortedEntries);
		System.out.println("ClassPath:");
		for (String entry : sortedEntries) {
			System.out.println("\t" + entry);
		}
	}
}
