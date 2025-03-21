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
import java.util.List;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.CallExp;
import org.eclipse.ocl.pivot.Constraint;
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.ids.AbstractSingletonScope;
import org.eclipse.ocl.pivot.ids.IdHash;
import org.eclipse.ocl.pivot.ids.IdManager;
import org.eclipse.ocl.pivot.ids.SingletonScope;
import org.eclipse.ocl.pivot.ids.SingletonScope.AbstractKeyAndValue;
import org.eclipse.ocl.pivot.ids.OperationId;
import org.eclipse.ocl.pivot.internal.ids.WeakHashMapOfListOfWeakReference2;
import org.eclipse.ocl.pivot.internal.prettyprint.PrettyPrintOptions;
import org.eclipse.ocl.pivot.internal.prettyprint.PrettyPrinter;
import org.eclipse.ocl.pivot.utilities.PivotUtil;

/**
 * ParametersId provides a hashed list of operationIds suitable for characterizing an operation signature.
 * parameter ids suitable for use when indexing operation overloads.
 */
public class CallChainImpl implements CallChain, WeakHashMapOfListOfWeakReference2.MatchableId<@NonNull OperationId @NonNull []>
{
	protected class Iterator implements java.util.Iterator<@NonNull OperationId>
	{
		private int index = 0;

		@Override
		public boolean hasNext() {
			return index < operationIds.length;
		}

		@Override
		public @NonNull OperationId next() {
			return operationIds[index++];
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}

	private static class CallChainValue extends AbstractKeyAndValue<@NonNull CallChain>
	{
		private final @NonNull IdManager idManager;
		private final @NonNull OperationId @NonNull [] value;

		private CallChainValue(@NonNull IdManager idManager, @NonNull OperationId @NonNull [] value) {
			super(computeHashCode(value));
			this.idManager = idManager;
			this.value = value;
		}

		@Override
		public @NonNull CallChain createSingleton() {
			return new CallChainImpl(idManager, value);
		}

		@Override
		public boolean equals(@Nullable Object that) {
			if (that instanceof CallChainImpl) {
				CallChainImpl singleton = (CallChainImpl)that;
				return computeEquals(singleton.operationIds, value);
			}
			else {
				return false;
			}
		}
	}

	/**
	 * @since 1.18
	 */
	public static class CallChainSingletonScope extends AbstractSingletonScope<@NonNull CallChain, @NonNull OperationId @NonNull []>
	{
		private final @NonNull IdManager idManager;

		public CallChainSingletonScope(@NonNull IdManager idManager) {
			this.idManager = idManager;
		}

		//		public @NonNull CallChain getSingleton(@NonNull IdManager idManager, @NonNull OperationId @NonNull [] value) {
		//			return getSingletonFor(new CallChainValue(idManager, value));
		//		}

		public @NonNull CallChain getSingleton(@NonNull OperationId @NonNull [] values) {
			return getSingletonFor(new CallChainValue(idManager, values));
		}

		//	public @NonNull CallChain getSingleton(@NonNull List<@NonNull OperationId> values) {
		//		return getSingletonFor(new CallChainValue(idManager, values.toArray(new @NonNull OperationId[values.size()])));
		//	}

		public @NonNull CallChain getSingleton(@NonNull List<@NonNull Operation> asOperations) {
			@NonNull OperationId @NonNull [] values = new @NonNull OperationId[asOperations.size()];
			int i = 0;
			for (@NonNull Operation asOperation : asOperations) {
				values[i++] = asOperation.getOperationId();
			}
			return getSingletonFor(new CallChainValue(idManager, values));
		}

		//	@Override
		//	public @NonNull SingletonScope getSingletonScope(@NonNull IdManager idManager) {
		// TODO Auto-generated method stub
		//		return null;
		//	}
	}

	private static boolean computeEquals(@NonNull OperationId @NonNull [] theseOperationIds, @NonNull OperationId @NonNull [] thoseOperationIds) {
		if (theseOperationIds.length != thoseOperationIds.length) {
			return false;
		}
		for (int i = 0; i < theseOperationIds.length; i++) {
			if (theseOperationIds[i] != thoseOperationIds[i]) {
				return false;
			}
		}
		return true;
	}

	private static int computeHashCode(@NonNull OperationId @NonNull [] operationIds) {
		long hash = 0;
		for (OperationId operationId : operationIds) {
			hash = 3 * hash + IdHash.longValueOf(operationId.hashCode());
		}
		return IdHash.createParametersHash(CallChainImpl.class, operationIds, null);
	}

	private final int hashCode;
	private final @NonNull OperationId @NonNull [] operationIds;
	protected final @NonNull List<@NonNull CallExp> callExps = new ArrayList<>();

	public CallChainImpl(@NonNull IdManager idManager, @NonNull OperationId @NonNull [] operationIds) {
		this.hashCode = computeHashCode(operationIds);
		this.operationIds = operationIds;
	}

	@Override
	public void addCallExp(@NonNull CallExp asCallExp) {
		callExps.add(asCallExp);				// ?? Reverse order to first call first not last.
	}

	@Override
	public final boolean equals(Object that) {
		if (that instanceof SingletonScope.KeyAndValue) {			// A SingletonScope.Key may be used to lookup a ParametersId
			return that.equals(this);
		}
		else {												// But normally ParametersId instances are singletons
			return this == that;
		}
	}

	@Override
	public @NonNull OperationId get(int index) {
		return operationIds[index];
	}

	public @NonNull OperationId @NonNull [] get() {
		return operationIds;
	}

	@Override
	public int hashCode() {
		return hashCode;
	}

	@Override
	public java.util.@NonNull Iterator<@NonNull OperationId> iterator() {
		return new Iterator();
	}

	@Override
	public boolean matches(@NonNull OperationId @NonNull [] thoseOperationIds) {
		return computeEquals(operationIds, thoseOperationIds);
	}

	@Override
	public void printOut() {
		System.out.println(callExps.size() + "*" + operationIds.length + ":" + toString());
		for (@NonNull CallExp asCallExp : callExps) {
			ExpressionInOCL asExpressionInOCL = PivotUtil.getContainingExpressionInOCL(asCallExp);
			Constraint asConstraint = PivotUtil.getContainingConstraint(asExpressionInOCL);
			Type asType = PivotUtil.getContainingType(asConstraint);
			org.eclipse.ocl.pivot.Package asPackage = PivotUtil.getContainingPackage(asConstraint);
			assert asConstraint != null;
			assert asType != null;
			assert asPackage != null;
			if (operationIds.length > 1) {
				System.out.print("\t" + PivotUtil.getName(asPackage) + "::" + PivotUtil.getName(asType) + "::" + PivotUtil.getName(asConstraint));
				PrettyPrintOptions prettyPrintOptions = new PrettyPrintOptions.Global(null);
				prettyPrintOptions.setLinelength(80);
				System.out.println("\t\t" + PrettyPrinter.print(asExpressionInOCL, prettyPrintOptions).replace("\n", "\n\t\t"));
				//	System.out.println("\t\t" + asExpressionInOCL);
			}
			else {
				System.out.println("\t" + PivotUtil.getName(asPackage) + "::" + PivotUtil.getName(asType) + "::" + PivotUtil.getName(asConstraint));
			}
		}
	}

	@Override
	public int size() {
		return operationIds.length;
	}

	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append('(');
		for (int i = 0; i < operationIds.length; i++) {
			if (i > 0) {
				s.append(',');
			}
			OperationId operationId = operationIds[i];
			@SuppressWarnings("null")boolean isNonNull = operationId != null;			// Never happens NE guard
			s.append(isNonNull ? operationId.toString() : "null");
		}
		s.append(')');
		return s.toString();
	}

	/*	@Override
	public @NonNull String toString() {
		StringBuilder s = new StringBuilder();
		s.append(callExps.size());
		for (@NonNull Operation asOperation : operations) {
			s.append(" ");
			s.append(asOperation.getName());
		}
		return s.toString();
	} */
}