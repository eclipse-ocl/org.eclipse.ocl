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

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.CompleteClass;
import org.eclipse.ocl.pivot.InheritanceFragment;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.StandardLibrary;
import org.eclipse.ocl.pivot.internal.library.executor.ExecutorFragment;
import org.eclipse.ocl.pivot.library.LibraryFeature;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.IndexableIterable;
import org.eclipse.ocl.pivot.utilities.Nameable;

public interface FlatClass extends Nameable
{
	public static class FragmentIterable implements IndexableIterable<@NonNull InheritanceFragment>
	{
		protected class Iterator implements java.util.Iterator<@NonNull InheritanceFragment>
		{
			private int index = firstIndex;

			@Override
			public boolean hasNext() {
				return index < lastIndex;
			}

			@Override
			public @NonNull InheritanceFragment next() {
				return array[index++];
			}

			@Override
			public void remove() {
				throw new UnsupportedOperationException();
			}
		}

		private final @NonNull InheritanceFragment @NonNull [] array;
		private final int firstIndex;
		private final int lastIndex;

		public FragmentIterable(@NonNull InheritanceFragment @NonNull [] array) {
			this.array = array;
			this.firstIndex = 0;
			this.lastIndex = array.length;
		}

		public FragmentIterable(@NonNull InheritanceFragment @NonNull [] array, int firstIndex, int lastIndex) {
			this.array = array;
			this.firstIndex = firstIndex;
			this.lastIndex = lastIndex;
		}

		@Override
		public @NonNull InheritanceFragment get(int index) {
			return ClassUtil.nonNullState(array[firstIndex + index]);
		}

		@Override
		public java.util.@NonNull Iterator<@NonNull InheritanceFragment> iterator() {
			return new Iterator();
		}

		@Override
		public int size() {
			return lastIndex - firstIndex;
		}

		@Override
		public String toString() {
			StringBuilder s = null;
			for (int i = firstIndex; i < lastIndex; i++) {
				if (s == null) {
					s = new StringBuilder();
					s.append("[");
				}
				else {
					s.append(", ");
				}
				s.append(array[i]);
			}
			if (s == null) {
				return "";
			}
			s.append("]");
			return s.toString();
		}
	}

	public static final int ORDERED = 1 << 0;
	public static final int UNIQUE = 1 << 1;
	public static final int OCL_ANY = 1 << 2;
	public static final int OCL_VOID = 1 << 3;
	public static final int OCL_INVALID = 1 << 4;			// NB. OCL_INVALID assumed greater than OCL_VOID by isSuper/SubInheritanceOf
	/**
	 * @since 1.1
	 */
	public static final int ABSTRACT = 1 << 5;

	/**
	 * Return a depth ordered, OclAny-first, OclSelf-last, Iterable of all the super-adapters excluding this one.
	 */
	@NonNull FragmentIterable getAllProperSuperFragments();
	/**
	 * Return a depth ordered, OclAny-first, OclSelf-last, Iterable of all the super-adapters including this one.
	 */
	@NonNull FragmentIterable getAllSuperFragments();
	@NonNull FlatClass getCommonFlatClass(@NonNull FlatClass that);
	CompleteClass getCompleteClass();
	int getDepth();
	@Nullable InheritanceFragment getFragment(@NonNull FlatClass that);
	@NonNull InheritanceFragment getFragment(int fragmentNumber);
	int getIndex(int fragmentNumber);
	int getIndexes();
	org.eclipse.ocl.pivot.@NonNull Class getPivotClass();
	@NonNull InheritanceFragment getSelfFragment();
	@NonNull FragmentIterable getSuperFragments(int depth);
	void initFragments(@NonNull ExecutorFragment @NonNull [] fragments, int[] depthCounts);
	boolean isAbstract();
	boolean isInvalid();
	boolean isOrdered();
	boolean isSubFlatClassOf(@NonNull FlatClass that);
	boolean isSuperFlatClassOf(@NonNull FlatClass that);
	boolean isUnique();
	boolean isUndefined();
	@Nullable Operation lookupLocalOperation(@NonNull StandardLibrary standardLibrary, @NonNull String operationName, @NonNull FlatClass... argumentTypes);
	@NonNull LibraryFeature lookupImplementation(@NonNull StandardLibrary standardLibrary, @NonNull Operation apparentOperation);
	@NonNull Operation lookupActualOperation(@NonNull StandardLibrary standardLibrary, @NonNull Operation apparentOperation);
	void uninstall();
}
