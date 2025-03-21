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
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.doc.ocl2025.tests.CallChainImpl.CallChainSingletonScope;
import org.eclipse.ocl.pivot.CallExp;
import org.eclipse.ocl.pivot.Constraint;
import org.eclipse.ocl.pivot.IterableType;
import org.eclipse.ocl.pivot.LoopExp;
import org.eclipse.ocl.pivot.OCLExpression;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.OperationCallExp;
import org.eclipse.ocl.pivot.Package;
import org.eclipse.ocl.pivot.ids.IdManager;
import org.eclipse.ocl.pivot.ids.SingletonScope;
import org.eclipse.ocl.pivot.ids.IdManager.AbstractSingletonScopeFactory;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.xtext.util.Arrays;

/**
 * ResolveVisitor converts references to shared specializations
 * to references to local copies.
 */
public class Statistics
{
	/*	public static class CallChain implements Comparable<@NonNull CallChain>
	{
		protected final @NonNull List<@NonNull Operation> operations;
		protected final @NonNull List<@NonNull CallExp> callExps = new ArrayList<>();

		public CallChain(@NonNull List<@NonNull Operation> operations) {
			this.operations = operations;
		}

		@Override
		public int compareTo(@NonNull CallChain o) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public @NonNull String toString() {
			StringBuilder s = new StringBuilder();
			s.append(callExps.size());
			for (@NonNull Operation asOperation : operations) {
				s.append(" ");
				s.append(asOperation.getName());
			}
			return s.toString();
		}

		public void addCallExp(@NonNull CallExp asCallExp) {
			callExps.add(asCallExp);
		}

		public void printOut() {
			System.out.println(toString());
			for (@NonNull CallExp asCallExp : callExps) {
				System.out.println(PivotUtil.getContainingExpressionInOCL(asCallExp));
			}
		}

	} */

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

	/**
	 * Map from the ParametersId hashCode to the parametersId with the same hash.
	 */
	private static final @NonNull CallChainSingletonScope callChains = (CallChainSingletonScope)AbstractSingletonScopeFactory.createSingletonScope(new CallChainSingletonScopeFactory());

	public static class CallChainSingletonScopeFactory extends AbstractSingletonScopeFactory
	{
		@Override
		protected @NonNull SingletonScope<?,?> createSingletonScope(@NonNull IdManager idManager) {
			return new CallChainSingletonScope(idManager);
		}
	}


	protected final @NonNull String[] includedPackageNames;
	protected final @NonNull String[] excludedPackageNames;
	protected final @NonNull Map<org.eclipse.ocl.pivot.@NonNull Class, @NonNull ClassStatistics> class2classStatistics = new HashMap<>();
	protected final @NonNull Map<@NonNull Constraint, @NonNull ConstraintStatistics> constraint2constraintStatistics = new HashMap<>();
	protected final @NonNull Map<org.eclipse.ocl.pivot.@NonNull Package, @NonNull PackageStatistics> package2packageStatistics = new HashMap<>();
	//	protected final @NonNull Map<@NonNull CallExp, @NonNull CallChain> callExp2callChain = new HashMap<>();
	protected final @NonNull Map<@NonNull Operation, @NonNull CallChain> operation2callChain = new HashMap<>();
	protected final @NonNull Map<@NonNull List<@NonNull Operation>, @NonNull CallChain> operations2callChain = new HashMap<>();

	public Statistics(@NonNull String[] includedPackageNames, @NonNull String[] excludedPackageNames) {
		this.includedPackageNames = includedPackageNames;
		this.excludedPackageNames = excludedPackageNames;
	}

	public void addCallExp(@NonNull CallExp asCallExp, @NonNull Operation asOperation) {
		//	CallChain callChain = operation2callChain.get(asOperation);
		//	if (callChain == null) {
		if (!(asOperation.getType() instanceof IterableType) && !(asOperation.eContainer() instanceof IterableType)) {
			return;
		}
		List<@NonNull Operation> operations = new ArrayList<>();
		operations.add(asOperation);
		for (OCLExpression asExp = asCallExp.getOwnedSource(); asExp != null; asExp = ((CallExp)asExp).getOwnedSource()) {
			if (asExp instanceof OperationCallExp) {
				operations.add(0, PivotUtil.getReferredOperation((OperationCallExp)asExp));
			}
			else if (asExp instanceof LoopExp) {
				operations.add(0, PivotUtil.getReferredIteration((LoopExp)asExp));
			}
			else {
				break;
			}
		}
		CallChain callChain = operations2callChain.get(operations);
		if (callChain == null) {
			callChain = callChains.getSingleton(operations);
			operations2callChain.put(operations, callChain);
		}
		operation2callChain.put(asOperation, callChain);
		callChain.addCallExp(asCallExp);

		//	}
	}

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

	public @Nullable PackageStatistics addPackage(@NonNull Package asPackage) {
		String asName = PivotUtil.getName(asPackage);
		if (Arrays.contains(excludedPackageNames, asName)) {
			return null;
		}
		if (!Arrays.contains(includedPackageNames, asName)) {
			throw new IllegalStateException("Unexpected package name '" + asName + "'");
		}
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
		for (@NonNull CallChain callChain : operations2callChain.values()) {
			callChain.printOut();
		}
	}
}
