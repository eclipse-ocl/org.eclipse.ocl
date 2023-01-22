/*******************************************************************************
 * Copyright (c) 2022 Willink Transformation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.codegen.calling;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.Nameable;

/**
 *  AbstractCallingConvention defines the common calling convention functionality.
 */
public abstract class AbstractCallingConvention implements CallingConvention, Nameable
{
	private static @NonNull Set<@NonNull AbstractCallingConvention> callingConventions = new HashSet<>();

	public static void printAllUsages() {
		List<@NonNull AbstractCallingConvention> callingConventions = new ArrayList<>(AbstractCallingConvention.callingConventions);
		Collections.sort(callingConventions, NameUtil.NAMEABLE_COMPARATOR);
		for (@NonNull AbstractCallingConvention callingConvention : callingConventions) {
			System.out.println(callingConvention);
			callingConvention.printUsages();
		}
	}

	public static void resetAllUsages() {
		for (@NonNull AbstractCallingConvention callingConvention : callingConventions) {
			callingConvention.resetUsages();
		}
		callingConventions.clear();
	}

	private @NonNull Map<@NonNull String, @NonNull List<@NonNull String>> testName2usages = new HashMap<>();

	private void addInstance(@Nullable String testName, @NonNull String usage) {
		callingConventions.add(this);
		if (testName == null) {
			testName = "";
		}
		List<@NonNull String> usages = testName2usages.get(testName);
		if (usages == null) {
			usages = new ArrayList<>();
			testName2usages.put(testName, usages);
		}
		usages.add(usage);
//		NameUtil.errPrintln(this + " for " + usage);
	}

	@Override
	public @NonNull String getName() {
		return getClass().getSimpleName();
	}

	protected void logInstance(@Nullable Object usage) {
		addInstance(NameUtil.contextText, String.valueOf(usage));
	}

	protected void logInstance(@NonNull Operation asOperation, boolean maybeVirtual) {
		addInstance(NameUtil.contextText, String.valueOf(asOperation));
	}

	private void printUsages() {
		List<@NonNull String> testNames = new ArrayList<>(testName2usages.keySet());
		Collections.sort(testNames);
		for (@NonNull String testName : testNames) {
			System.out.println("\t" + testName);
			List<@NonNull String> usages = new ArrayList<>(testName2usages.get(testName));
			Map<@NonNull String, @NonNull Integer> distinctUsages = new HashMap<>();
			for (@NonNull String usage : usages) {
				Integer count = distinctUsages.get(usage);
				distinctUsages.put(usage, count != null ? count+1 : 1);
			}
			List<@NonNull String> sortedUsages = new ArrayList<>(distinctUsages.keySet());
			Collections.sort(sortedUsages);
			for (@NonNull Object usage : sortedUsages) {
				System.out.println("\t\t" + distinctUsages.get(usage) + " * " + usage);
			}
		}
	}

	private void resetUsages() {
		testName2usages.clear();
	}

	@Override
	public @NonNull String toString() {
		return getClass().getSimpleName();
	}
}
