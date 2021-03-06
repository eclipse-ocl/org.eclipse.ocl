/*******************************************************************************
 * Copyright (c) 2011, 2020 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.build.utilities;

import java.io.File;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.emf.mwe.utils.StandaloneSetup;

/**
 * Initializes OCL support.
 */
public class FilterStandaloneSetup extends StandaloneSetup
{
	private Pattern pattern = null;


	private Pattern getPattern() {
		if (pattern == null) {
			pattern = Pattern.compile("\\.*org\\.eclipse\\.emf\\.ecore");
		}
		return pattern;
	}

	@Override
	protected void registerBundle(File file) {
		String filePath = file.toString();
		System.out.println("1: " + filePath);
		if (filePath.contains("org.eclipse.emf.ecore")) {
			getClass();
		}
		Matcher m = getPattern().matcher(filePath);
		if (!m.matches()) {
			super.registerBundle(file);
		}
		else {
			super.registerBundle(file);
		}
	}

	@Override
	protected boolean scanFolder(File f, Set<String> visitedPathes) {
		String filePath = f.toString();
//		System.out.println("2: " + filePath);
		if (filePath.endsWith("org.eclipse.emf.ecore")) {
			getClass();
		}
		Matcher m = getPattern().matcher(filePath);
		if (!m.matches()) {
			return super.scanFolder(f, visitedPathes);
		}
		else {
			return super.scanFolder(f, visitedPathes);
		}
	}
}
