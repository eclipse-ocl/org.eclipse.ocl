/*******************************************************************************
 * Copyright (c) 2023 Willink Transformations and others.
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
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.StandardLibrary;
import org.eclipse.ocl.pivot.State;
import org.eclipse.ocl.pivot.ids.OperationId;
import org.eclipse.ocl.pivot.ids.TemplateParameterId;
import org.eclipse.ocl.pivot.library.LibraryFeature;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.FeatureFilter;
import org.eclipse.ocl.pivot.utilities.IndexableIterable;
import org.eclipse.ocl.pivot.utilities.Nameable;

/**
 * A FlatClass caches the diverse vertical (supertype) and, for CompleteFlatClass, horizontal (partial) classes
 * to provide a single fast lookup for features.
 */
public interface FlatClass extends Nameable
{
	public static class FragmentIterable implements IndexableIterable<@NonNull FlatFragment>
	{
		protected class Iterator implements java.util.Iterator<@NonNull FlatFragment>
		{
			private int index = firstIndex;

			@Override
			public boolean hasNext() {
				return index < lastIndex;
			}

			@Override
			public @NonNull FlatFragment next() {
				return array[index++];
			}

			@Override
			public void remove() {
				throw new UnsupportedOperationException();
			}
		}

		private final @NonNull FlatFragment @NonNull [] array;
		private final int firstIndex;
		private final int lastIndex;

		public FragmentIterable(@NonNull FlatFragment @NonNull [] array) {
			this.array = array;
			this.firstIndex = 0;
			this.lastIndex = array.length;
		}

		public FragmentIterable(@NonNull FlatFragment @NonNull [] array, int firstIndex, int lastIndex) {
			this.array = array;
			this.firstIndex = firstIndex;
			this.lastIndex = lastIndex;
		}

		@Override
		public @NonNull FlatFragment get(int index) {
			return ClassUtil.nonNullState(array[firstIndex + index]);
		}

		@Override
		public java.util.@NonNull Iterator<@NonNull FlatFragment> iterator() {
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

	@Nullable Operation basicGetOperation(@NonNull Operation operationId);
	@Nullable Operation basicGetOperation(@NonNull OperationId operationId);
	@Nullable Iterable<@NonNull Operation> basicGetOperationOverloads(@NonNull Operation pivotOperation);
	@Nullable Property basicGetProperty(@NonNull String name);
	org.eclipse.ocl.pivot.@NonNull Class getASClass();
	/**
	 * Return a depth ordered, OclAny-first, OclSelf-last, Iterable of all the super-adapters excluding this one.
	 */
	@NonNull FragmentIterable getAllProperSuperFragments();

	/**
	 * Return a depth ordered, OclAny-first, OclSelf-last, Iterable of all the super-adapters including this one.
	 */
	@NonNull FragmentIterable getAllSuperFragments();
	@Nullable Operation getBestOverload(@NonNull FlatClass derivedFlatClass, @NonNull Operation apparentOperation);
	@NonNull FlatClass getCommonFlatClass(@NonNull FlatClass that, boolean ignoreTemplateArguments);
	@Deprecated // XXX eliminate unsound inheritance
	@NonNull CompleteClass getCompleteClass();
	int getDepth();
	@NonNull FlatModel getFlatModel();
	@NonNull FlatClass getGenericFlatClass();
	@Override
	@NonNull String getName();
	@NonNull Iterable<@NonNull Operation> getOperationOverloads(@Nullable FeatureFilter featureFilter, @Nullable String name);
	@NonNull Iterable<@NonNull Operation> getOperations();
	@NonNull Iterable<@NonNull Operation> getOperations(@Nullable FeatureFilter featureFilter);
	@NonNull Iterable<@NonNull Property> getProperties(@Nullable FeatureFilter featureFilter, @Nullable String name);
	@NonNull FlatFragment getSelfFragment();
	@NonNull Operation @NonNull [] getSelfOperations();
	@NonNull Property @NonNull [] getSelfProperties();
	@NonNull StandardLibrary getStandardLibrary();
	@NonNull Iterable<@NonNull State> getStates();
	@NonNull Iterable<@NonNull State> getStates(@Nullable String name);
	@NonNull FragmentIterable getSuperFragments(int depth);
	@NonNull TemplateParameterId @Nullable [] getTemplateParameterIds();

	/**
	 * Initialize the super-fragment hierarchy from the compile-time analysis.
	 */
	void initFragments(@NonNull FlatFragment @NonNull [] fragments, int @NonNull [] depthCounts);
//	@NonNull Map<@NonNull String, @NonNull State> initStates();
	boolean isAbstract();
	boolean isInvalid();
	boolean isOrdered();
	boolean isSubFlatClassOf(@NonNull FlatClass that, boolean ignoreTemplateArguments);
	boolean isSuperFlatClassOf(@NonNull FlatClass that, boolean ignoreTemplateArguments);
	boolean isUnique();
	boolean isUndefined();
	@Nullable Operation lookupLocalOperation(@NonNull StandardLibrary standardLibrary, @NonNull String operationName, @NonNull FlatClass... argumentTypes);
	@NonNull LibraryFeature lookupImplementation(@NonNull Operation apparentOperation);
	@NonNull Operation lookupActualOperation(@NonNull StandardLibrary standardLibrary, @NonNull Operation apparentOperation);

	/**
	 * Reset the sub-fragment hierarchy following a class mutation.
	 */
	void resetFragments();
	void resetOperations();
	void resetProperties();
}
