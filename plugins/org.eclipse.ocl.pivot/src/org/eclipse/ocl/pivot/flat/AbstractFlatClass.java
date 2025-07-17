/*******************************************************************************
 * Copyright (c) 2011, 2018 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.flat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.ids.OperationId;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.complete.CompleteClassInternal;
import org.eclipse.ocl.pivot.internal.executor.PivotReflectiveFragment;
import org.eclipse.ocl.pivot.internal.library.executor.ReflectiveInheritance;
import org.eclipse.ocl.pivot.types.TemplateParameters;
import org.eclipse.ocl.pivot.utilities.ClassUtil;

import com.google.common.base.Function;

/**
 * An AbstractTypeServer provides the co-ordinated operation, property and superclass lookup caches for one or more merged types.
 * @since 7.0
 */
public class AbstractFlatClass extends ReflectiveInheritance implements FlatClass
{
	public static final @NonNull List<org.eclipse.ocl.pivot.flat.AbstractFlatClass> EMPTY_LIST = Collections.<org.eclipse.ocl.pivot.flat.AbstractFlatClass>emptyList();

	public static final class BestOperation implements Function<List<Operation>, Operation> {

		@Override
		public Operation apply(List<Operation> operations) {
			return operations.get(0);
		}
	}

	public static final @NonNull BestOperation bestOperation = new BestOperation();

	protected final @NonNull CompleteClassInternal completeClass;

	public AbstractFlatClass(@NonNull CompleteClassInternal completeClass) {
		super(ClassUtil.requireNonNull(completeClass.getName()), computeFlags(completeClass.getPrimaryClass()));
		this.completeClass = completeClass;
//		org.eclipse.ocl.pivot.Class pivotClass = completeClass.getPrimaryClass();
//		assert !(pivotClass instanceof DataType) || (((DataType)pivotClass).getBehavioralClass() == null);	// DataTypes must use the inheritance of their behavioral class
	}

	@Override
	public @Nullable Operation basicGetOperation(@NonNull OperationId operationId) {
		return completeClass.getOperation(operationId);
	}

	@Override
	public @Nullable Property basicGetProperty(@NonNull String propertyName) {
		return completeClass.getProperty(propertyName);
	}

	@Override
	protected @NonNull FlatFragment createFragment(@NonNull FlatClass baseFlatClass) {
		return new PivotReflectiveFragment(this, baseFlatClass);
	}

	public @NonNull CompleteClassInternal getCompleteClass() {
		return completeClass;
	}

	@Override
	public @NonNull Iterable<@NonNull ? extends FlatClass> getInitialSuperInheritances() {
		return isOclAny() ? EMPTY_LIST : completeClass.getPartialClasses().getInitialSuperInheritances();
	}

	public @NonNull List<? extends Operation> getLocalOperations() {
		return ClassUtil.requireNonNull(completeClass.getPrimaryClass().getOwnedOperations());			// FIXME Use local cache
	}

	public @NonNull List<? extends Property> getLocalProperties() {
		return ClassUtil.requireNonNull(completeClass.getPrimaryClass().getOwnedProperties());			// FIXME Use local cache
	}

	/**
	 * @since 7.0
	 */
	@Override
	public @NonNull String getMetaclassName() {
		return completeClass.getPrimaryClass().getMetaclassName();
	}

	@Override
	public @NonNull List<Property> getOwnedProperties() {
		return ClassUtil.requireNonNull(completeClass.getPrimaryClass().getOwnedProperties());			// FIXME Use local cache
	}

	@Override
	public @NonNull List<Operation> getOwnedOperations() {
		return ClassUtil.requireNonNull(completeClass.getPrimaryClass().getOwnedOperations());			// FIXME Use local cache
	}

	@Override
	public org.eclipse.ocl.pivot.@NonNull Class getPivotClass() {
		return getCompleteClass().getPrimaryClass();
	}

	@Override
	public @NonNull List<org.eclipse.ocl.pivot.Class> getSuperClasses() {
		List<org.eclipse.ocl.pivot.Class> superClasses = new ArrayList<org.eclipse.ocl.pivot.Class>();
		for (org.eclipse.ocl.pivot.Class superClass : completeClass.getProperSuperClasses()) {
			superClasses.add(superClass);
		}
		return superClasses;
	}

	@Override
	public final @NonNull TypeId getTypeId() {
		return completeClass.getPrimaryClass().getTypeId();
	}

	/**
	 * @since 7.0
	 */
	@Override
	public @NonNull TemplateParameters getTemplateParameters() {
		return TemplateParameters.EMPTY_LIST;
	}

	@Override
	public String toString() {
		return completeClass.getPrimaryClass().toString();
	}

	@Override
	public void uninstall() {
//		System.out.println("uninstall " + toString());
		completeClass.uninstall();
		super.uninstall();
	}
}
