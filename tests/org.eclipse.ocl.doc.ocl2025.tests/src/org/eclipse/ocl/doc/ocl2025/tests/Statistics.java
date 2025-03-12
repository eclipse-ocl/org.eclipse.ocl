/*******************************************************************************
 * Copyright (c) 2025 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.doc.ocl2025.tests;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.Constraint;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.Package;
import org.eclipse.ocl.pivot.utilities.NameUtil;

/**
 * ResolveVisitor converts references to shared specializations
 * to references to local copies.
 */
public class Statistics
{
	public static class ClassStatistics implements Comparable<@NonNull ClassStatistics>
	{
		protected final @NonNull Statistics statistics;
		protected final org.eclipse.ocl.pivot.@NonNull Class asClass;
		protected final @NonNull List<@NonNull ConstraintStatistics> constraintStatistics = new ArrayList<>();

		public ClassStatistics(@NonNull Statistics statistics, org.eclipse.ocl.pivot.@NonNull Class asClass) {
			this.statistics = statistics;
			this.asClass = asClass;
		}

		public void add(@NonNull ConstraintStatistics asConstraintStatistics) {
			constraintStatistics.add(asConstraintStatistics);
		}

		@Override
		public int compareTo(@NonNull ClassStatistics that) {
			return this.asClass.getName().compareTo(that.asClass.getName());
		}

		public void printOut() {
			if (constraintStatistics.size() > 0) {
				System.out.println("\t" + asClass.getName());
				Collections.sort(constraintStatistics);
				for (@NonNull ConstraintStatistics constraintStatistic : constraintStatistics) {
					constraintStatistic.printOut();
				}
			}
		}

		@Override
		public @NonNull String toString() {
			return asClass.getName();
		}
	}

	public static class ConstraintStatistics implements Comparable<@NonNull ConstraintStatistics>
	{
		protected final @NonNull Statistics statistics;
		protected final @NonNull Constraint asConstraint;
		private int depth = 0;
		private int ops = 0;
		protected final @NonNull Map<@NonNull Operation, @NonNull Integer> operation2count = new HashMap<>();

		public ConstraintStatistics(@NonNull Statistics statistics, @NonNull Constraint asConstraint) {
			this.statistics = statistics;
			this.asConstraint = asConstraint;
		}

		public void addOperation(@NonNull Operation asOperation) {
			Integer count = operation2count.get(asOperation);
			operation2count.put(asOperation, count == null ? 1 : count.intValue()+1);
			ops++;
		}

		@Override
		public int compareTo(@NonNull ConstraintStatistics that) {
			return this.asConstraint.getName().compareTo(that.asConstraint.getName());
		}

		public void printOut() {
			StringBuilder s = new StringBuilder();
			s.append("\t\t" + asConstraint.getName() + "=" + depth + " " + ops);
			List<@NonNull Operation> operations = new ArrayList<>(operation2count.keySet());
			Collections.sort(operations, NameUtil.NAMEABLE_COMPARATOR);
			for (@NonNull Operation operation : operations) {
				s.append(" " + operation2count.get(operation) + "*" + operation.getName());
			}
			System.out.println(s.toString());
		}

		public void setDepth(int depth) {
			this.depth  = depth;
		}

		@Override
		public @NonNull String toString() {
			return asConstraint.getName();
		}
	}

	public static class PackageStatistics implements Comparable<@NonNull PackageStatistics>
	{
		protected final @NonNull Statistics statistics;
		protected final org.eclipse.ocl.pivot.@NonNull Package asPackage;
		protected final @NonNull List<@NonNull ClassStatistics> classStatistics = new ArrayList<>();

		public PackageStatistics(@NonNull Statistics statistics, org.eclipse.ocl.pivot.@NonNull Package asPackage) {
			this.statistics = statistics;
			this.asPackage = asPackage;
		}

		public void add(@NonNull ClassStatistics asClassStatistics) {
			classStatistics.add(asClassStatistics);
		}

		@Override
		public int compareTo(@NonNull PackageStatistics that) {
			return this.asPackage.getName().compareTo(that.asPackage.getName());
		}

		public void printOut() {
			System.out.println(asPackage.getName());
			Collections.sort(classStatistics);
			for (@NonNull ClassStatistics classStatistic : classStatistics) {
				classStatistic.printOut();
			}
		}

		@Override
		public @NonNull String toString() {
			return asPackage.getName();
		}
	}

	protected final @NonNull Map<org.eclipse.ocl.pivot.@NonNull Class, @NonNull ClassStatistics> class2classStatistics = new HashMap<>();
	protected final @NonNull Map<@NonNull Constraint, @NonNull ConstraintStatistics> constraint2constraintStatistics = new HashMap<>();
	protected final @NonNull Map<org.eclipse.ocl.pivot.@NonNull Package, @NonNull PackageStatistics> package2packageStatistics = new HashMap<>();

	public Statistics() {}

	public @NonNull ClassStatistics addClass(@NonNull PackageStatistics packageStatistics, org.eclipse.ocl.pivot.@NonNull Class asClass) {
		ClassStatistics classStatistics = getClassStatistics(asClass);
		packageStatistics.add(classStatistics);
		return classStatistics;
	}

	public @NonNull ConstraintStatistics addConstraint(@NonNull ClassStatistics classStatistics, @NonNull Constraint asConstraint) {
		ConstraintStatistics constraintStatistics = getConstraintStatistics(asConstraint);
		classStatistics.add(constraintStatistics);
		return constraintStatistics;
	}

	public @NonNull PackageStatistics addPackage(@NonNull Package asPackage) {
		PackageStatistics packageStatistics = getPackageStatistics(asPackage);
		return packageStatistics;
	}

	public @NonNull ClassStatistics getClassStatistics(org.eclipse.ocl.pivot.@NonNull Class asClass) {
		ClassStatistics classStatistics = class2classStatistics.get(asClass);
		if (classStatistics == null) {
			classStatistics = new ClassStatistics(this, asClass);
			class2classStatistics.put(asClass, classStatistics);
		}
		return classStatistics;
	}

	public @NonNull ConstraintStatistics getConstraintStatistics(@NonNull Constraint asConstraint) {
		ConstraintStatistics constraintStatistics = constraint2constraintStatistics.get(asConstraint);
		if (constraintStatistics == null) {
			constraintStatistics = new ConstraintStatistics(this, asConstraint);
			constraint2constraintStatistics.put(asConstraint, constraintStatistics);
		}
		return constraintStatistics;
	}

	public @NonNull PackageStatistics getPackageStatistics(org.eclipse.ocl.pivot.@NonNull Package asPackage) {
		PackageStatistics packageStatistics = package2packageStatistics.get(asPackage);
		if (packageStatistics == null) {
			packageStatistics = new PackageStatistics(this, asPackage);
			package2packageStatistics.put(asPackage, packageStatistics);
		}
		return packageStatistics;
	}

	public void printOut() {
		@NonNull List<org.eclipse.ocl.pivot.@NonNull Package> asPackages = new ArrayList<>(package2packageStatistics.keySet());
		Collections.sort(asPackages, NameUtil.NAMEABLE_COMPARATOR);
		for (org.eclipse.ocl.pivot.@NonNull Package asPackage : asPackages) {
			PackageStatistics packageStatistics = getPackageStatistics(asPackage);
			packageStatistics.printOut();
		}
	}
}
