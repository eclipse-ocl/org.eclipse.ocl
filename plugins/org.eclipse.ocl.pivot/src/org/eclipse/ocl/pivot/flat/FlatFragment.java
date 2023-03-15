/*******************************************************************************
 * Copyright (c) 2011, 2018 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.flat;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.ids.ParametersId;
import org.eclipse.ocl.pivot.internal.utilities.PivotUtilInternal;
import org.eclipse.ocl.pivot.library.LibraryFeature;
import org.eclipse.ocl.pivot.library.oclany.OclAnyUnsupportedOperation;
import org.eclipse.ocl.pivot.types.AbstractFragment;
import org.eclipse.ocl.pivot.utilities.ClassUtil;

import com.google.common.collect.Lists;

/**
 * A FlatFragment provides the description of the properties and operations defined by some class when accessed by the same
 * or another class. The descriptions are normally built by direct static construction from auto-generated code, with instnaces defined
 * in isolation during construction then cross-references defined later by calls to init().
 */
public class FlatFragment extends AbstractFragment
{
	private @NonNull Operation @Nullable [] operations = null;

	/**
	 * The properties defined by the derivedFlatClass of this fragment. Initially null,
	 * non null once initialized.
	 * FIXME legacy static initialization has some super properties too.
	 */
	private @NonNull Property @Nullable [] properties = null;

	protected @Nullable Map<@NonNull Operation, @NonNull LibraryFeature> operationMap = null;
	protected @Nullable Map<@NonNull Operation, @NonNull Operation> apparentOperation2actualOperation = null;

	public FlatFragment(@NonNull FlatClass derivedFlatClass, @NonNull FlatClass baseFlatClass) {
		super(derivedFlatClass, baseFlatClass);
	}

	public @NonNull Property @Nullable [] basicGetProperties() {
		return properties;
	}

	@Override
	public @NonNull LibraryFeature getImplementation(@NonNull Operation apparentOperation) {
		int index = apparentOperation.getIndex();
		if (index >= 0) {
			assert operations != null;
			return ClassUtil.nonNullState(operations[index].getImplementation());
		}
		else {
			Map<@NonNull Operation, @NonNull LibraryFeature> operationMap2 = operationMap;
			if (operationMap2 == null) {
				synchronized (this) {
					operationMap2 = operationMap;
					if (operationMap2 == null) {
						operationMap = operationMap2 = new HashMap<>();		// Optimize to reuse single super map if no local ops
					}
				}
			}
			LibraryFeature libraryFeature = operationMap2.get(apparentOperation);
			if (libraryFeature != null) {
				return libraryFeature;
			}
			synchronized (operationMap2) {
				libraryFeature = operationMap2.get(apparentOperation);
				if (libraryFeature != null) {
					return libraryFeature;
				}
				Operation localOperation = getLocalOperation(apparentOperation);
				if (localOperation == null) {
					if (derivedFlatClass == baseFlatClass) {
						localOperation = apparentOperation;
					}
				}
				if (localOperation != null) {				// Trivial case, there is a local operation
					libraryFeature = PivotUtilInternal.getImplementation(localOperation);
				}
				else {										// Non-trivial, search up the inheritance tree for an inherited operation
					Operation bestOverload = baseFlatClass.getBestOverload(derivedFlatClass, apparentOperation);
					if (bestOverload != null) {
						libraryFeature = PivotUtilInternal.getImplementation(bestOverload);
					}
					else {
						libraryFeature = OclAnyUnsupportedOperation.AMBIGUOUS;
					}
				}
				if (libraryFeature == null) {
					libraryFeature = OclAnyUnsupportedOperation.INSTANCE;
				}
				operationMap2.put(apparentOperation, libraryFeature);
				return libraryFeature;
			}
		}
	}

	@Override
	public final @Nullable Operation getLocalOperation(@NonNull Operation baseOperation) {
		if (derivedFlatClass instanceof CompleteFlatClass) {		// XXX move to FlatClass
			CompleteFlatClass completeFlatClass = (CompleteFlatClass)derivedFlatClass;
			String baseOperationName = baseOperation.getName();
			ParametersId baseParametersId = baseOperation.getParametersId();
			Operation bestOperation = null;
			for (org.eclipse.ocl.pivot.Class partialClass : completeFlatClass.getCompleteClass().getPartialClasses()) {
				for (Operation localOperation : partialClass.getOwnedOperations()) {
					if (localOperation.getName().equals(baseOperationName) && (localOperation.getParametersId() == baseParametersId)) {
						if (localOperation.getESObject() != null) {
							return localOperation;
						}
						if (bestOperation == null) {
							bestOperation = localOperation;
						}
						else if ((localOperation.getBodyExpression() != null) && (bestOperation.getBodyExpression() == null)) {
							bestOperation = localOperation;
						}
					}
				}
			}
			return bestOperation;					// null if not known locally, caller must try superfragments.
		}
		else {
			int index = baseOperation.getIndex();
			if (index >= 0) {
				assert operations != null;
				return operations[index];
			}
			else {
				return null;
			}
		}
	}

	@Override
	public final @NonNull Iterable<@NonNull Operation> getLocalOperations() {
		if (operations != null) {
			return Lists.<@NonNull Operation>newArrayList(operations);
		}
		else {
			return operationMap != null ? operationMap.keySet() : Collections.<@NonNull Operation>emptyList();
		}
	}

	@Override
	@Deprecated /* @deprecated no longer used */
	public final @NonNull Property @NonNull [] getLocalProperties() {
		assert properties != null;
		return properties;
	}

	@Override
	public @NonNull Iterable<org.eclipse.ocl.pivot.@NonNull Class> getSuperClasses() {
		throw new UnsupportedOperationException();		// WIP
	}

	public void initOperations(@NonNull Operation @NonNull [] operations) {
		assert this.operations == null;
		this.operations = operations;
	}

	public void initProperties(@NonNull Property @NonNull [] properties) {
		assert this.properties == null;
		this.properties = properties;
	}
}