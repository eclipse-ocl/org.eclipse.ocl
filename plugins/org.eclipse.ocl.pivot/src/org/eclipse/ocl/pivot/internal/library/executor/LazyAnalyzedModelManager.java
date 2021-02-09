/*******************************************************************************
 * Copyright (c) 2010, 2019 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.internal.library.executor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.evaluation.AbstractModelManager;
import org.eclipse.ocl.pivot.evaluation.ModelManager;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

/**
 * A self-populating map that lazily creates the extent of a class when asked
 * for it.
 * <p>
 * <b>Note</b> that this implementation violates the contract of the
 * {@link Map} API as follows:
 * <ul>
 *   <li>the {@link Map#entrySet()} method does not return entries
 *       for any keys that have not already been queried via the
 *       {@link Map#get(java.lang.Object)} method</li>
 *   <li>concomitant to the above item, the {@link Map#keySet()}
 *       and {@link Map#values()} methods also are not complete</li>
 * </ul>
 * In practice, this does not matter because this map is only used for providing
 * class extents to the OCL engine, and it only does look-up by key.
 * Moreover, this isn't strictly a violation of any contract because there is
 * no way to distinguish this behaviour from concurrent updates.
 * </p>
 * @since 1.14
 */
public class LazyAnalyzedModelManager extends AbstractModelManager implements ModelManager.EcoreModelManager
{
	private static abstract class EClassAnalysis
	{
		protected final @NonNull LazyAnalyzedModelManager modelManager;
		protected final @NonNull EClass eClass;
		protected @NonNull EReferenceAnalysis @Nullable [] eOppositeAnalyses;

		protected EClassAnalysis(@NonNull LazyAnalyzedModelManager modelManager, @NonNull EClass eClass) {
			this.modelManager = modelManager;
			this.eClass = eClass;
			this.eOppositeAnalyses = analyzeEClass();
		}

		private @NonNull EReferenceAnalysis @Nullable [] analyzeEClass() {
			List<@NonNull EReferenceAnalysis> eOppositeAnalyses = null;
			for (EStructuralFeature eStructuralFeature : eClass.getEAllStructuralFeatures()) {
				if (eStructuralFeature instanceof EReference) {
					EReference eReference = (EReference)eStructuralFeature;
					if (eReference.getEOpposite() == null) {
						EReferenceAnalysis eOppositeAnalysis = modelManager.getEReferenceAnalysis(eReference);
						if (eOppositeAnalysis != null) {
							assert !eReference.isTransient() && !eReference.isVolatile();
							if (eOppositeAnalyses == null) {
								eOppositeAnalyses = new ArrayList<>();
							}
							assert !eOppositeAnalyses.contains(eOppositeAnalysis);
							eOppositeAnalyses.add(eOppositeAnalysis);
						}
					}
				}
			}
			return eOppositeAnalyses != null ? eOppositeAnalyses.toArray(new @NonNull EReferenceAnalysis[eOppositeAnalyses.size()]) : null;
		}

		public abstract void analyzeEObject(@NonNull EObject eObject);

		public void analyzeEOpposites(@NonNull EObject eObject) {
			assert eObject.eClass() == eClass;
			@NonNull EReferenceAnalysis[] eOppositeAnalyses2 = eOppositeAnalyses;
			if (eOppositeAnalyses2 != null) {
				for (@NonNull EReferenceAnalysis eOppositeAnalysis : eOppositeAnalyses2) {
					eOppositeAnalysis.analyzeEObject(eObject);
				}
			}
		}

		/**
		 * Return the EClassAnalysisWithInstances that caches instances of thos EClass.
		 */
		public abstract @Nullable EClassAnalysisWithInstances basicGetEClassAnalysisWithInstances();

		public abstract @NonNull Iterable<@NonNull EObject> getInstances();
	}

	private static class EClassAnalysisWithInstances extends EClassAnalysis
	{
		/**
		 * The instances of this EClass or its subclasses that are not accumulated in one of the indirectInstances.
		 */
		protected final @NonNull List<@NonNull EObject> localInstances;
		/**
		 * The subclasses instance accumulators that are logically alos instances of the EClass.
		 * Includes localInstances when non-null.
		 */
		private @Nullable List<@NonNull List<@NonNull EObject>> indirectInstances;

		public EClassAnalysisWithInstances(@NonNull LazyAnalyzedModelManager modelManager, @NonNull EClass eClass) {
			super(modelManager, eClass);
			this.localInstances = new ArrayList<>();
			this.indirectInstances = null;;
		}

		protected void addLocalInstance(@NonNull EObject eObject) {
			assert !localInstances.contains(eObject);
			localInstances.add(eObject);
		}

		@Override
		public void analyzeEObject(@NonNull EObject eObject) {
			addLocalInstance(eObject);
			analyzeEOpposites(eObject);
		}

		public void analyzeInheritance() {
			for (EClass eSuperClass : eClass.getEAllSuperTypes()) {
				assert eSuperClass != null;
				EClassAnalysis eClassAnalysis = modelManager.basicGetEClassAnalysis(eSuperClass);
				if (eClassAnalysis instanceof EClassAnalysisWithInstances) {
					List<@NonNull List<@NonNull EObject>> indirectInstances2 = indirectInstances;
					if (indirectInstances2 == null) {
						indirectInstances2 = indirectInstances = new ArrayList<>();
						indirectInstances2.add(localInstances);
					}
					List<@NonNull EObject> localInstances2 = ((EClassAnalysisWithInstances)eClassAnalysis).localInstances;
					if (!indirectInstances2.contains(localInstances2)) {
						indirectInstances2.add(localInstances2);
					}
				}
			}
		}

		@Override
		public @NonNull EClassAnalysisWithInstances basicGetEClassAnalysisWithInstances() {
			return this;
		}

		@Override
		public @NonNull Iterable<@NonNull EObject> getInstances() {
			if (indirectInstances == null) {
				return localInstances;
			}
			else {
				return Iterables.concat(indirectInstances);
			}
		}
	}

	private static class EClassAnalysisWithoutInstances extends EClassAnalysis
	{
		protected final @Nullable EClassAnalysisWithInstances eClassAnalysisWithInstances;

		public EClassAnalysisWithoutInstances(@NonNull LazyAnalyzedModelManager modelManager, @NonNull EClass eClass,
				@Nullable EClassAnalysisWithInstances eClassAnalysisWithInstances) {
			super(modelManager, eClass);
			this.eClassAnalysisWithInstances = eClassAnalysisWithInstances;
		}

		@Override
		public void analyzeEObject(@NonNull EObject eObject) {
			if (eClassAnalysisWithInstances != null) {
				eClassAnalysisWithInstances.addLocalInstance(eObject);
			}
			analyzeEOpposites(eObject);
		}

	/*	public void analyzeInheritance() {
			EClassAnalysisWithInstances eClassAnalysisWithInstance = null;
			List<@NonNull EClassAnalysisWithInstances> eClassAnalysisWithInstances = null;
			for (@NonNull EClass eSuperClass : eClass.getEAllSuperTypes()) {
				EClassAnalysis eClassAnalysis = modelManager.basicGetEClassAnalysis(eSuperClass);
				if (eClassAnalysis instanceof EClassAnalysisWithInstances) {
					EClassAnalysisWithInstances eClassAnalysisWithInstance2 = (EClassAnalysisWithInstances)eClassAnalysis;
					if (eClassAnalysisWithInstances != null) {
						if (!eClassAnalysisWithInstances.contains(eClassAnalysisWithInstance2)) {
							eClassAnalysisWithInstances.add(eClassAnalysisWithInstance2);
						}
					}
					else if (eClassAnalysisWithInstance != null) {
						eClassAnalysisWithInstance = eClassAnalysisWithInstance2;
					}
					else {

					}

					List<@NonNull List<@NonNull EObject>> indirectInstances2 = indirectInstances;
					if (indirectInstances2 == null) {
						indirectInstances2 = indirectInstances = new ArrayList<>();
						indirectInstances2.add(localInstances);
					}
					List<@NonNull EObject> localInstances2 = ((EClassAnalysisWithInstances)eClassAnalysis).localInstances;
					if (!indirectInstances2.contains(localInstances2)) {
						indirectInstances2.add(localInstances2);
					}
				}
			}
		} */

		@Override
		public @Nullable EClassAnalysisWithInstances basicGetEClassAnalysisWithInstances() {
			return eClassAnalysisWithInstances;
		}

		@Override
		public @NonNull Iterable<@NonNull EObject> getInstances() {
			throw new IllegalStateException("No allInstances() support for a " + eClass.getName());
		}
	}

	private static class EReferenceAnalysis
	{
		protected final @NonNull EReference eReference;
		protected final @NonNull Map<@NonNull EObject, @NonNull Object> eTarget2eSourceOrSources = new HashMap<>();

		public EReferenceAnalysis(@NonNull EReference eReference) {
			this.eReference = eReference;
			assert eReference.getEOpposite() == null;
		}

		public void analyzeEObject(@NonNull EObject eObject) {
			Object objectOrObjects = eObject.eGet(eReference);
			if (eReference.isMany()) {
				@SuppressWarnings("unchecked")
				Iterable<@NonNull EObject> oppositeEObjects = (Iterable<@NonNull EObject>)objectOrObjects;
				for (EObject oppositeEObject : oppositeEObjects) {
					addOpposite(eObject, oppositeEObject);
				}
			}
			else if (objectOrObjects != null) {
				addOpposite(eObject, (EObject)objectOrObjects);
			}
		}

		private void addOpposite(@NonNull EObject eObject, @NonNull EObject oppositeEObject) {
			Object sourceOrSources = eTarget2eSourceOrSources.get(oppositeEObject);
			if (sourceOrSources == null) {
				eTarget2eSourceOrSources.put(oppositeEObject, eObject);
			}
			else if (sourceOrSources instanceof EObject) {
				List<@NonNull EObject> objects = new ArrayList<>();
				eTarget2eSourceOrSources.put(oppositeEObject, objects);
				objects.add((EObject)sourceOrSources);
				assert eObject != sourceOrSources;
				objects.add(eObject);
			}
			else {
				@SuppressWarnings("unchecked")
				List<@NonNull EObject> objects = (List<@NonNull EObject>)sourceOrSources;
				assert !objects.contains(eObject);
				objects.add(eObject);
			}
		}

	/*	public @Nullable EObject basicGetOpposite(@NonNull EObject oppositeEObject) {
			Object objectOrObjects = eTarget2eSourceOrSources.get(oppositeEObject);
			if (objectOrObjects == null) {
				return null;
			}
			else if (objectOrObjects instanceof EObject) {
				return (EObject)objectOrObjects;
			}
			else {
				throw new IllegalStateException("Too many " + eReference.getName() + " opposites");
			}
		} */

		public @Nullable List<@NonNull EObject> basicGetOpposites(@NonNull EObject oppositeEObject) {
			Object objectOrObjects = eTarget2eSourceOrSources.get(oppositeEObject);
			if (objectOrObjects == null) {
				return null;
			}
			else if (objectOrObjects instanceof EObject) {
				return Collections.singletonList((EObject)objectOrObjects);
			}
			else {
				@SuppressWarnings("unchecked")
				List<@NonNull EObject> objects = (List<@NonNull EObject>)objectOrObjects;
				return objects;
			}
		}
	}

	private static final @NonNull List<@NonNull Object> NO_OBJECTS = Collections.emptyList();

	/**
	 * The EObjects that together with their transitive containment descendants comprise the extent for allInstances() and imolucit opposites.
	 */
	private final @NonNull Collection<@NonNull EObject> extentRoots;

	/**
	 * The EClasses for which allInstances caches are maintained.
	 */
	private final @NonNull List<@NonNull EClass> allInstancesEClasses;

	/**
	 * The EReferences for which implicit opposites caches are maintained.
	 */
	private final @NonNull List<@NonNull EReference> implicitOppositeEReferences;

	/**
	 * Map from an EClass to its allInstances/implicitOpposites metadata.
	 */
	private @Nullable Map<@NonNull EClass, @NonNull EClassAnalysis> eClass2eClassAnalysis = null;

	/**
	 * Map from an EReference to its implicitOpposites cache.
	 */
	private @Nullable Map<@NonNull EReference, @NonNull EReferenceAnalysis> eReference2eReferenceAnalysis = null;

	public LazyAnalyzedModelManager(@NonNull Collection<@NonNull EObject> extentRoots, @Nullable Iterable<@NonNull EClass> allInstancesClasses, @Nullable Iterable<@NonNull EReference> implicitOpposites) {
		this.extentRoots = extentRoots;
		this.allInstancesEClasses = allInstancesClasses != null ? Lists.newArrayList(allInstancesClasses) : new ArrayList<>();
		this.implicitOppositeEReferences = implicitOpposites != null ? Lists.newArrayList(implicitOpposites) : new ArrayList<>();
	}

	@Override
	public void addAllInstancesEClass(@NonNull EClass allInstancesEClass) {
		if (!allInstancesEClasses.contains(allInstancesEClass)) {
			allInstancesEClasses.add(allInstancesEClass);
			resetAnalysis();
		}
	}

	@Override
	public void addImplicitOppositeEReference(@NonNull EReference implicitOppositeEReference) {
		if (!implicitOppositeEReferences.contains(implicitOppositeEReference)) {
			implicitOppositeEReferences.add(implicitOppositeEReference);
			resetAnalysis();
		}
	}

	/**
	 * Perform the lazy analyzis of the entire model to discover and cache all allInstances() and
	 * all implicit opposites.
	 */
	@Override
	public synchronized void analyze() {
		if ((allInstancesEClasses.size() + implicitOppositeEReferences.size()) > 0) {
			//
			//	Analyze the extent.
			//
			for (TreeIterator<EObject> tit = EcoreUtil.getAllContents(extentRoots); tit.hasNext(); ) {
				EObject eObject = tit.next();
				assert eObject != null;
				EClass eClass = eObject.eClass();
				assert eClass != null;
				EClassAnalysis eClassAnalysis = getEClassAnalysis(eClass);
				eClassAnalysis.analyzeEObject(eObject);
			}
		}
		if (eClass2eClassAnalysis == null) {
			eClass2eClassAnalysis = new HashMap<>();
		}
	}

	/**
	 * Return the cache support for eClass.
	 */
	protected @Nullable EClassAnalysis basicGetEClassAnalysis(@NonNull EClass eClass) {
		return eClass2eClassAnalysis != null ? eClass2eClassAnalysis.get(eClass) : null;
	}

	@Override
	public @NonNull Set<@NonNull EObject> get(org.eclipse.ocl.pivot.@NonNull Class asClass) {
		EClass eClass = (EClass)asClass.getESObject();
		assert eClass != null;
		Iterable<@NonNull EObject> list = getInstances(eClass);
		return list != null ? Sets.newHashSet(list) : Collections.emptySet();
	}

	/**
	 * Return the allInstances() cache for eClass.
	 */
	protected @NonNull EClassAnalysis getEClassAnalysis(@NonNull EClass eClass) {
		Map<@NonNull EClass, @NonNull EClassAnalysis> eClass2eClassAnalysis2 = eClass2eClassAnalysis;
		if (eClass2eClassAnalysis2 == null) {
			//
			//	Create the map eagerly seeded by the known allInstances() classes.
			//
			eClass2eClassAnalysis2 = eClass2eClassAnalysis = new HashMap<>();
			for (@NonNull EClass allInstancesEClass : allInstancesEClasses) {
				eClass2eClassAnalysis2.put(allInstancesEClass, new EClassAnalysisWithInstances(this, allInstancesEClass));
			}
			for (@NonNull EClass allInstancesEClass : allInstancesEClasses) {		// FIXME loop over values
				EClassAnalysisWithInstances eClassAnalysis = (EClassAnalysisWithInstances)eClass2eClassAnalysis2.get(allInstancesEClass);
				assert eClassAnalysis != null;
				eClassAnalysis.analyzeInheritance();
			}
		}
		EClassAnalysis eClassAnalysis = eClass2eClassAnalysis2.get(eClass);
		if (eClassAnalysis == null) {
			//
			//	Lazily add the required EClassAnalysis and all its super EClassAnalysis so that diamond
			//	inheritances avoid duplicates by caching allInstances at the join.
			//
			EClassAnalysisWithInstances theEClassAnalysisWithInstances = null;
			for (EClass eSuperClass : eClass.getESuperTypes()) {
				assert eSuperClass != null;
				EClassAnalysis eSuperClassAnalysis = getEClassAnalysis(eSuperClass);
				EClassAnalysisWithInstances eClassAnalysisWithInstances = eSuperClassAnalysis.basicGetEClassAnalysisWithInstances();
				if (eClassAnalysisWithInstances != null) {
					if (theEClassAnalysisWithInstances == null) {
						theEClassAnalysisWithInstances = eClassAnalysisWithInstances;
					}
					else {
						assert theEClassAnalysisWithInstances != eClassAnalysisWithInstances;
						eClassAnalysis = new EClassAnalysisWithInstances(this, eClass);
						break;
					}
				}
			}
			if (eClassAnalysis == null) {
				eClassAnalysis = new EClassAnalysisWithoutInstances(this, eClass, theEClassAnalysisWithInstances);
			}
			eClass2eClassAnalysis2.put(eClass, eClassAnalysis);
		}
		return eClassAnalysis;
	}

	/**
	 * Return the implicit opposite cache for eReference or null if no cache is required.
	 *
	 */
	protected @Nullable EReferenceAnalysis getEReferenceAnalysis(@NonNull EReference eReference) {
		Map<@NonNull EReference, @NonNull EReferenceAnalysis> eReference2eReferenceAnalysis2 = eReference2eReferenceAnalysis;
		if (eReference2eReferenceAnalysis2 == null) {
			eReference2eReferenceAnalysis2 = eReference2eReferenceAnalysis = new HashMap<>();
			for (@NonNull EReference implicitOppositeEReference : implicitOppositeEReferences) {
				eReference2eReferenceAnalysis2.put(implicitOppositeEReference, new EReferenceAnalysis(implicitOppositeEReference));
			}
		}
		return eReference2eReferenceAnalysis2.get(eReference);
	}

	/**
	 * Return the instances of eClass and its subtypes, returning null for none.
	 */
	@Override
	public @Nullable Iterable<@NonNull EObject> getInstances(@NonNull EClass eClass) {
		EClassAnalysis eClassAnalysis;
		Map<@NonNull EClass, @NonNull EClassAnalysis> eClass2eClassAnalysis2 = eClass2eClassAnalysis;
		if (eClass2eClassAnalysis2 == null) {
			addAllInstancesEClass(eClass);						// Precautionary re-insertion
			analyze();
			eClass2eClassAnalysis2 = eClass2eClassAnalysis;
			assert eClass2eClassAnalysis2 != null;
			eClassAnalysis = eClass2eClassAnalysis2.get(eClass);
			assert eClassAnalysis != null;
		}
		else {													// Legacy reanalysis for an unanticipated allInstances
			eClassAnalysis = eClass2eClassAnalysis2.get(eClass);
			if (eClassAnalysis == null) {
				addAllInstancesEClass(eClass);
				analyze();
				eClass2eClassAnalysis2 = eClass2eClassAnalysis;
				assert eClass2eClassAnalysis2 != null;
				eClassAnalysis = eClass2eClassAnalysis2.get(eClass);
				assert eClassAnalysis != null;
			}
		}
		return eClassAnalysis.getInstances();
	}

	/**
	 * Return the source EObjects for which the opposite of eReference navigates to eTarget, returning null if none.
	 */
	@Override
	public @Nullable Iterable<@NonNull EObject> getOpposites(@NonNull EReference eReference, @NonNull EObject eTarget) {
		Map<@NonNull EReference, @NonNull EReferenceAnalysis> eReference2eReferenceAnalysis2 = eReference2eReferenceAnalysis;
		if (eReference2eReferenceAnalysis2 == null) {
			addImplicitOppositeEReference(eReference);
			analyze();
			eReference2eReferenceAnalysis2 = eReference2eReferenceAnalysis;
			assert eReference2eReferenceAnalysis2 != null;
		}
		EReferenceAnalysis eReferenceAnalysis = eReference2eReferenceAnalysis2.get(eReference);
		if (eReferenceAnalysis == null) {
			addImplicitOppositeEReference(eReference);
			analyze();
			eReference2eReferenceAnalysis2 = eReference2eReferenceAnalysis;
			assert eReference2eReferenceAnalysis2 != null;
			eReferenceAnalysis = eReference2eReferenceAnalysis2.get(eReference);
			assert eReferenceAnalysis != null;
		}
		return eReferenceAnalysis.basicGetOpposites(eTarget);
	}


	@Override
	public @NonNull Iterable<@NonNull Object> getOpposite(@NonNull Property target2sourceProperty, @NonNull Object sourceObject) {
		EReference eReference = (EReference)target2sourceProperty.getESObject();
		assert eReference != null;
		Iterable<@NonNull ?> objects = getOpposites(eReference, (EObject)sourceObject);
		if (objects != null) {
			@SuppressWarnings("unchecked")
			Iterable<@NonNull Object> castObjects = (Iterable<@NonNull Object>)objects;
			return castObjects;
		}
		else {
			return NO_OBJECTS;
		}
	}

	@Override
	public void resetAnalysis() {
		eClass2eClassAnalysis = null;
		eReference2eReferenceAnalysis = null;
	}

//	@Override
//	public String toString() {
//		return eClass2eClassAnalysis.toString();
//	}
}
