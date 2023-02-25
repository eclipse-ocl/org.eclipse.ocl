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
package org.eclipse.ocl.pivot.types;

import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.CompleteClass;
import org.eclipse.ocl.pivot.CompleteInheritance;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.StandardLibrary;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.flat.CompleteFlatClass;
import org.eclipse.ocl.pivot.flat.EcoreFlatClass;
import org.eclipse.ocl.pivot.flat.FlatClass;
import org.eclipse.ocl.pivot.flat.PartialFlatClass;
import org.eclipse.ocl.pivot.internal.elements.AbstractExecutorNamedElement;
import org.eclipse.ocl.pivot.library.LibraryFeature;
import org.eclipse.ocl.pivot.utilities.NameUtil;

public abstract class AbstractInheritance extends AbstractExecutorNamedElement implements CompleteInheritance
{
	public static final int ORDERED = FlatClass.ORDERED;
	public static final int UNIQUE = FlatClass.UNIQUE;
	public static final int OCL_ANY = FlatClass.OCL_ANY;
	public static final int OCL_VOID = FlatClass.OCL_VOID;
	public static final int OCL_INVALID = FlatClass.OCL_INVALID;
	/**
	 * @since 1.1
	 */
	public static final int ABSTRACT = FlatClass.ABSTRACT;

	/**
	 * A simple public static method that may be used to force class initialization.
	 */
	public static void initStatics() {}

	protected final @NonNull FlatClass flatClass;

	public AbstractInheritance(@NonNull String name, int flags) {
		super(name);
		this.flatClass = new PartialFlatClass((Type)this, flags);
	}

	protected AbstractInheritance(@NonNull CompleteClass completeClass, int flags) {
		super(NameUtil.getName(completeClass));
		this.flatClass = new CompleteFlatClass(completeClass, flags);
	}

	protected AbstractInheritance(@NonNull EClassifier eClassifier, int flags) {
		super(NameUtil.getName(eClassifier));
		this.flatClass = new EcoreFlatClass(eClassifier, flags);
	}

//	@Override
//	public @NonNull CompleteInheritance getCommonInheritance(@NonNull CompleteInheritance thatInheritance) {
//		return flatClass.getCommonInheritance(thatInheritance);
//	}

//	@Override
//	@Deprecated
//	public final int getDepth() {
//		return flatClass.getDepth();
//	}

	@Override
	public @NonNull FlatClass getFlatClass() {
		return flatClass;
	}

	public @NonNull FlatClass getFlatClass(@NonNull StandardLibrary standardLibrary) {
		return flatClass;
	}

/*	@Override
	public @Nullable InheritanceFragment getFragment(@NonNull CompleteInheritance thatInheritance) {
		int staticDepth = thatInheritance.getDepth();
		if (staticDepth <= getDepth()) {
			int iMax = getIndex(staticDepth+1);
			for (int i = getIndex(staticDepth); i < iMax; i++) {
				InheritanceFragment fragment = getFragment(i);
				if (fragment.getBaseFlatClass() == thatInheritance) {
					return fragment;
				}
			}
		}
		return null;
	} */

/*	@Override
	public final @NonNull InheritanceFragment getFragment(int fragmentNumber) {
		return flatClass.getFragment(fragmentNumber);
	}

	@Override
	public final @NonNull Iterable<@NonNull InheritanceFragment> getFragments() {
		return flatClass.getFragments();
	}

	@Override
	public final int getIndex(int fragmentNumber) {
		return flatClass.getIndex(fragmentNumber);
	}

	@Override
	public final int getIndexes(){
		return flatClass.getIndexes();
	} */

//	@Override
//	public final @NonNull InheritanceFragment getSelfFragment() {
//		return flatClass.getSelfFragment();
//	}

//	@Override
//	public final @NonNull FragmentIterable getSuperFragments(int depth) {
//		return flatClass.getSuperFragments(depth);
//	}

//	@Override
//	public org.eclipse.ocl.pivot.@NonNull Class getType() {
//		return getPivotClass();
//	}

//	public final boolean isInvalid() {
//		return flatClass.isInvalid();
//	}

//	@Override
//	public final boolean isOclAny() {
//		return flatClass.isOclAny();
//	}

//	@Override
//	public /*final*/ boolean isOrdered() {
//		return flatClass.isOrdered();
//	}

//	@Override
//	public boolean isSubInheritanceOf(@NonNull CompleteInheritance thatInheritance) {
//		return flatClass.isSubFlatClassOf(thatInheritance.getFlatClass());
//	}

//	@Override
//	public boolean isSuperInheritanceOf(@NonNull CompleteInheritance thatInheritance) {
//		return flatClass.isSuperFlatClassOf(thatInheritance.getFlatClass());
//	}

//	@Override
//	public final boolean isUndefined() {
//		return flatClass.isUndefined();
//	}

//	@Override
//	public /*final*/ boolean isUnique() {
//		return flatClass.isUnique();
//	}

//	@Override
	public @NonNull Operation lookupActualOperation(@NonNull StandardLibrary standardLibrary, @NonNull Operation apparentOperation) {
		return flatClass.lookupActualOperation(standardLibrary, apparentOperation);
	}

//	@Override
	public @NonNull LibraryFeature lookupImplementation(@NonNull StandardLibrary standardLibrary, @NonNull Operation apparentOperation) {
		return flatClass.lookupImplementation(standardLibrary, apparentOperation);
	}

//	@Override
//	public @Nullable Operation lookupLocalOperation(@NonNull StandardLibrary standardLibrary, @NonNull String operationName, CompleteInheritance... argumentTypes) {
//		return flatClass.lookupLocalOperation(standardLibrary, operationName, argumentTypes);
//	}
}
