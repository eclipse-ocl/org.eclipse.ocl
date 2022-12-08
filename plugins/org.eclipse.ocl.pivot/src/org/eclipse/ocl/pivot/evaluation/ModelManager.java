/*******************************************************************************
 * Copyright (c) 2010, 2022 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.evaluation;

import java.util.Collections;
import java.util.Set;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.OCLExpression;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.ids.PropertyId;
import org.eclipse.ocl.pivot.ids.TypeId;

/**
 * ModelManager provides the models to be used during evaluation. In particular
 * the managed models constitute the extent from which Classifier.allInstances
 * returns are made.
 */
public interface ModelManager
{
	/**
	 * @since 1.1
	 */
	public interface ModelManagerExtension extends ModelManager
	{
		void dispose();
		@NonNull TreeIterator<? extends Object> eAllContents(@NonNull Object object);
		@NonNull EClass eClass(@NonNull Object object);
		@Nullable Object eContainer(@NonNull Object object);
		@Nullable Object eGet(@NonNull Object object, @NonNull EStructuralFeature eFeature);
	}

	/**
	 * @since 1.7
	 */
	public interface ModelManagerExtension2 extends ModelManagerExtension
	{
		@Deprecated /* @deprecated Use EcoreModelManager.getOpposites */
		@NonNull Iterable<@NonNull Object> getOpposite(@NonNull Property target2sourceProperty, @NonNull Object sourceObject);
	}

	/**
	 * @since 1.14
	 */
	public interface EcoreModelManager extends ModelManagerExtension2
	{
		/**
		 * Register allInstancesEClass as an EClass for which allInstances may be invoked.
		 * This invokes resetAnalysis to force a lazy reanlysis.
		 */
		void addAllInstancesEClass(@NonNull EClass allInstancesEClass);

		/**
		 * Register implicitOppositeEReference as an EReference for which implicit opposite navigation may be invoked.
		 * This invokes resetAnalysis to force a lazy reanlysis.
		 */
		void addImplicitOppositeEReference(@NonNull EReference implicitOppositeEReference);

		/**
		 *  Eagerly perform the totla ResourceSet travesal to discover allInstances/implicitOpposites.
		 */
		void analyze();

		/**
		 * Return the instances of eClass and its subtypes, returning null for none.
		 * A lazy analyze() is triggered.
		 */
		@Nullable Iterable<@NonNull EObject> getInstances(@NonNull EClass eClass);

		/**
		 * Return the instances of the class whose id is typeId and its subtypes, returning null for none.
		 * A lazy analyze() is triggered.
		 */
		@Nullable Iterable<@NonNull EObject> getInstances(@NonNull TypeId typeId);

		/**
		 * Return the source EObjects for which the opposite of eReference navigates to eTarget, returning null if none.
		 * A lazy analyze() is triggered.
		 */
		@Nullable Iterable<@NonNull EObject> getOpposites(@NonNull EReference eReference, @NonNull EObject eTarget);

		/**
		 * Reset the analysis forcing a re-analysis of the model. This may be necessary after a late discovery of
		 * an allInstances/implicitOpposites from uncompiled OCL.
		 */
		void resetAnalysis();
	}

	@Deprecated /* @deprecated use a NullModelManger to support static properties */
	@NonNull ModelManager NULL = new ModelManager()
	{
		@Override
		public @NonNull Set<@NonNull ? extends Object> get(org.eclipse.ocl.pivot.@NonNull Class type) {
			return Collections.<@NonNull Object>emptySet();
		}
	};

	/**
	 * Return the boxed value of the foreign propertyId of object, which should be null for a static properyId.
	 * Returns null if no such property yet known. Returns ValueUtil.NULL_VALUE for a null value.
	 *
	 * @since 1.18
	 */
	default /*@NoThrow*/ @Nullable Object basicGetForeignPropertyValue(@Nullable Object object, @NonNull PropertyId propertyId)
			throws VirtualMachineError {	// Just VirtualMachineError explicitly means @NoThrow CG-wise
		return null;
	}

	/**
	 * @since 1.18
	 */
	@Deprecated // XXX
	default @Nullable Object basicGetStaticPropertyValue(@NonNull PropertyId propertyId) {
		return basicGetForeignPropertyValue(null, propertyId);
	}

	/**
	 * Return the boxed value of the foreign propertyId of object, which should be null for a static properyId.
	 * initExpression or defaultValue is used to initialize on first access.
	 * Returns null if no such property known. Returns ValueUtil.NULL_VALUE for a null value.
	 * @param defaultValue
	 *
	 * @since 1.18
	 */
	default @Nullable Object getForeignPropertyValue(@Nullable Object object, @NonNull PropertyId propertyId, @Nullable Object defaultValue) {
		return defaultValue;
	}
	// XXX move initExpression eval to caller
	/**
	 * @since 1.18
	 */
	default /*@NoThrow*/ @Nullable Object getForeignPropertyValue(@Nullable Object object, @NonNull PropertyId propertyId, @Nullable OCLExpression initExpression, @Nullable Object defaultValue)
			throws VirtualMachineError {	// Just VirtualMachineError explicitly means @NoThrow CG-wise
		return defaultValue;
	}

	@Deprecated /* @deprecated Use getInstances() to avoid compulsory Set */
	@NonNull Set<@NonNull ? extends Object> get(org.eclipse.ocl.pivot.@NonNull Class type);

	/**
	 * Return the instances of the (Pivot) type and its subtypes, returning null for none.
	 * A lazy analyze() is triggered.
	 *
	 * @since 1.18
	 */
	default @Nullable Iterable<@NonNull ? extends Object> getInstances(org.eclipse.ocl.pivot.@NonNull Class type) {
		return get(type);
	}

	/**
	 * @since 1.18
	 */
	@Deprecated // XXX
	default @Nullable Object getStaticPropertyValue(@NonNull PropertyId propertyId, @Nullable OCLExpression initExpression, @Nullable Object defaultValue) {
		return getForeignPropertyValue(null, propertyId, initExpression, defaultValue);
	}

	/**
	 * Specify the boxed value of the not-read-only foreign propertyId of object, which should be null for a static propertyId.
	 * Returns the previous value. Returns Invalid if read-only.
	 *
	 * @since 1.18
	 */
	default /*@NoThrow*/ @Nullable Object setForeignPropertyValue(@Nullable Object object, @NonNull PropertyId propertyId, @NonNull Object value)
			throws VirtualMachineError {	// Just VirtualMachineError explicitly means @NoThrow CG-wise
		return null;
	}

	/**
	 * @since 1.18
	 */
	@Deprecated // XXX
	default @Nullable Object setStaticPropertyValue(@NonNull PropertyId propertyId, @NonNull Object value) {
		return setForeignPropertyValue(null, propertyId, value);
	}
}
