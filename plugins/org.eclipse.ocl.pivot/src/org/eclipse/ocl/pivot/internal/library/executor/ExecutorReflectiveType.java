/*******************************************************************************
 * Copyright (c) 2011, 2023 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.internal.library.executor;

import java.util.Collections;
import java.util.List;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.Constraint;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.StandardLibrary;
import org.eclipse.ocl.pivot.flat.FlatClass;
import org.eclipse.ocl.pivot.ids.OperationId;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.types.AbstractFragment;
import org.eclipse.ocl.pivot.types.TemplateParameters;
import org.eclipse.ocl.pivot.utilities.ClassUtil;

import com.google.common.base.Function;
import com.google.common.collect.Iterables;

/**
 * @since 7.0
 */
public class ExecutorReflectiveType extends AbstractReflectiveInheritanceType
{
	protected final @NonNull ExecutorReflectivePackage evaluationPackage;
	protected final org.eclipse.ocl.pivot.@NonNull Class domainClass;
	private /*@LazyNonNull*/ ExecutorProperties allProperties;

	public ExecutorReflectiveType(@NonNull ExecutorReflectivePackage evaluationPackage, org.eclipse.ocl.pivot.@NonNull Class domainClass) {
		super(ClassUtil.requireNonNull(domainClass.getName()), computeFlags(domainClass));
		this.evaluationPackage = evaluationPackage;
		this.domainClass = domainClass;
	}

	@Override
	protected @NonNull AbstractFragment createFragment(@NonNull FlatClass baseInheritance) {
		return new ExecutorReflectiveFragment(this, baseInheritance);
	}

	@Override
	public @NonNull Iterable<@NonNull ? extends FlatClass> getInitialSuperInheritances() {
		Iterable<? extends org.eclipse.ocl.pivot.@NonNull Class> superClasses = ClassUtil.nullFree(domainClass.getSuperClasses());
		StandardLibrary standardLibrary = evaluationPackage.getStandardLibrary();
		if (Iterables.isEmpty(superClasses)) {
			return Collections.singletonList(standardLibrary.getOclAnyType().getFlatClass(standardLibrary));
		}
		return Iterables.transform(superClasses, new Function<org.eclipse.ocl.pivot.@NonNull Class, @NonNull FlatClass>()
		{
			@Override
			public @NonNull FlatClass apply(org.eclipse.ocl.pivot.@NonNull Class asClass) {
				return asClass.getFlatClass(standardLibrary);
			}
		});
	}

	@Override
	public @NonNull List<org.eclipse.ocl.pivot.Class> getSuperClasses() {
		return domainClass.getSuperClasses();
	}

	@Override
	public @Nullable Operation getMemberOperation(@NonNull OperationId operationId) {
		throw new UnsupportedOperationException();					// FIXME
	}

	@Override
	public @Nullable Property getMemberProperty(@NonNull String name) {
		ExecutorProperties allProperties2 = allProperties;
		if (allProperties2 == null) {
			allProperties = allProperties2 = new ExecutorProperties(this);
		}
		return allProperties2.getMemberProperty(name);
	}

	@Override
	public @NonNull String getMetaclassName() {
		return domainClass.getMetaclassName();
	}

	@Override
	public @NonNull List<Constraint> getOwnedInvariants() {
		return domainClass.getOwnedInvariants();
	}

	@Override
	public @NonNull List<Operation> getOwnedOperations() {
		return domainClass.getOwnedOperations();
	}

	@Override
	public @NonNull List<Property> getOwnedProperties() {
		return domainClass.getOwnedProperties();
	}

	@Override
	public @NonNull List<Constraint> getOwnedConstraints() {
		throw new UnsupportedOperationException();			// FIXME
	}

	@Override
	public org.eclipse.ocl.pivot.@NonNull Package getOwningPackage() {
		return evaluationPackage;
	}

	@Override
	public org.eclipse.ocl.pivot.@NonNull Class getPivotClass() {
		return domainClass;
	}

	@Override
	public @NonNull TypeId getTypeId() {
		return domainClass.getTypeId();
	}

	/**
	 * @since 7.0
	 */
	@Override
	public @NonNull TemplateParameters getTemplateParameters() {
		return domainClass.getTemplateParameters();
	}

	@Override
	public boolean isOrdered() {
		return domainClass.isOrdered();
	}

	@Override
	public boolean isUnique() {
		return domainClass.isUnique();
	}

	@Override
	public String toString() {
		return String.valueOf(evaluationPackage) + "::" + String.valueOf(name); //$NON-NLS-1$
	}
}