/*******************************************************************************
 * Copyright (c) 2010, 2025 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.build.utilities;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.mwe.core.WorkflowContext;
import org.eclipse.emf.mwe.core.issues.Issues;
import org.eclipse.emf.mwe.core.monitor.ProgressMonitor;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.Annotation;
import org.eclipse.ocl.pivot.CompleteClass;
import org.eclipse.ocl.pivot.CompleteModel;
import org.eclipse.ocl.pivot.CompletePackage;
import org.eclipse.ocl.pivot.Constraint;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.Iteration;
import org.eclipse.ocl.pivot.Model;
import org.eclipse.ocl.pivot.NamedElement;
import org.eclipse.ocl.pivot.NormalizedTemplateParameter;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.Parameter;
import org.eclipse.ocl.pivot.PivotPackage;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.TemplateParameter;
import org.eclipse.ocl.pivot.TemplateableElement;
import org.eclipse.ocl.pivot.internal.ecore.as2es.AS2Ecore;
import org.eclipse.ocl.pivot.internal.ecore.es2as.Ecore2AS;
import org.eclipse.ocl.pivot.internal.manager.Orphanage;
import org.eclipse.ocl.pivot.internal.resource.StandaloneProjectMap;
import org.eclipse.ocl.pivot.internal.utilities.OCLInternal;
import org.eclipse.ocl.pivot.resource.ASResource;
import org.eclipse.ocl.pivot.resource.CSResource;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.EnvironmentFactory;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.PivotHelper;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.utilities.XMIUtil;
import org.eclipse.ocl.xtext.completeocl.CompleteOCLStandaloneSetup;
import org.eclipse.ocl.xtext.oclinecore.OCLinEcoreStandaloneSetup;
import org.eclipse.ocl.xtext.oclstdlib.OCLstdlibStandaloneSetup;

import com.google.common.base.Objects;
import com.google.common.collect.Iterables;
import com.google.common.collect.Sets;

/**
 * Loads an Complete AS Model from one or more Ecore <tt>uri</tt> and zero or more CompleteOCL/OCLstdlib/OCLinEcore <tt>uri</tt>.
 * THe result is output as a merged OCLAS and also Ecore model.
 */
public class ModelMerger extends AbstractProjectComponent
{
	public static abstract class AbstractSlotMerger
	{
		protected final @NonNull ModelMerger modelMerger;

		protected AbstractSlotMerger(@NonNull ModelMerger modelMerger) {
			this.modelMerger = modelMerger;
		}

		protected <T extends EObject> @Nullable T createMergedEObject(@NonNull Iterable<@NonNull T> eObjects) {
			EClass mergedEClass = getMergedEClass(eObjects);
			if (mergedEClass == null) {
				return null;
			}
			EObject mergedEObject = mergedEClass.getEPackage().getEFactoryInstance().create(mergedEClass);
			for (@NonNull T eObject : eObjects) {
				modelMerger.putMerged(mergedEObject, eObject);
			}
			return (T) mergedEObject;
		}

		protected EClass getMergedEClass(Iterable<@NonNull ? extends EObject> eObjects) {
			EClass mergedEClass = null;
			for (@NonNull EObject eObject : eObjects) {
				EClass eClass = eObject.eClass();
				if ((mergedEClass == null) || mergedEClass.isSuperTypeOf(eClass)) {
					mergedEClass = eClass;
				}
			}
			return mergedEClass;
		}

		protected @Nullable EObject mergeEObjects(@NonNull String path, @NonNull Iterable<@NonNull ? extends EObject> eObjects) {
			EObject mergedEObject = createMergedEObject(eObjects);
			if (mergedEObject != null) {
				EClass mergedEClass = mergedEObject.eClass();
				List<@NonNull EStructuralFeature> eAllStructuralFeatures = mergedEClass.getEAllStructuralFeatures();
				for (@NonNull EStructuralFeature eStructuralFeature : eAllStructuralFeatures) {
					if (eStructuralFeature.isDerived() || eStructuralFeature.isTransient() || eStructuralFeature.isVolatile()) {
						continue;
					}
					if (eStructuralFeature instanceof EReference) {
						EReference eReference = (EReference)eStructuralFeature;
						if (eReference.isContainer()) {
							continue;
						}
					}
					if (eStructuralFeature.isMany()) {
						mergeMultipleFeature(mergedEObject, path+ "." + eStructuralFeature.getName(), eStructuralFeature, eObjects);
					}
					else {
						mergeSingleFeature(mergedEObject, path+ "." + eStructuralFeature.getName(), eStructuralFeature, eObjects);
					}
				}
			}
			return mergedEObject;
		}

		protected void mergeSingleFeature(@NonNull EObject mergedEObject, @NonNull String path, @NonNull EStructuralFeature eFeature, @NonNull Iterable<@NonNull ? extends EObject> eObjects) {
			assert !eFeature.isMany();
			List<Object> slots = new ArrayList<>();
			for (@NonNull EObject eObject : eObjects) {
				if (eObject.eClass().getEAllStructuralFeatures().contains(eFeature)) {
					slots.add(eObject.eGet(eFeature));
				}
			}
			SingleSlotMerger featureMerger = modelMerger.getSingleSlotMerger(eFeature);
			Object mergedSlot = featureMerger.merge(path, slots);
			if ((mergedSlot != null) && (eFeature instanceof EReference) && ((EReference)eFeature).isContainment()) {
				if (slots.contains(mergedSlot)) {
					mergedSlot = EcoreUtil.copy((EObject) mergedSlot);
				}
			}
			mergedEObject.eSet(eFeature, mergedSlot);
		}

		protected void mergeMultipleFeature(@NonNull EObject mergedEObject, @NonNull String path, @NonNull EStructuralFeature eFeature, @NonNull Iterable<@NonNull ? extends EObject> eObjects) {
			assert eFeature.isMany();
			List<Iterable<Object>> slots = new ArrayList<>();
			for (@NonNull EObject eObject : eObjects) {
				if (eObject.eClass().getEAllStructuralFeatures().contains(eFeature)) {
					slots.add((List<Object>)eObject.eGet(eFeature));
				}
			}
			MultipleSlotMerger featureMerger = modelMerger.getMultipleSlotMerger(eFeature);
			Collection<Object> mergedSlots = featureMerger.merge(path, slots);
			if ((mergedSlots != null) && !mergedSlots.isEmpty()) {
				if ((eFeature instanceof EReference) && ((EReference)eFeature).isContainment()) {
					boolean hasContainer = false;
					for (@NonNull Object mergedSlot : mergedSlots) {
						if (mergedSlot instanceof EObject) {
							if (((EObject)mergedSlot).eContainer() != null) {
								hasContainer = true;
							}
						}
					}
					if (hasContainer) {
						System.out.println("Unexpected container for " + eFeature.getName() + " : " + mergedSlots);
						mergedSlots = EcoreUtil.copyAll(mergedSlots);
					}
				}
				((List<Object>)mergedEObject.eGet(eFeature)).addAll(mergedSlots);
			}
		}
	}

	public static class BooleanAndSingleSlotMerger extends SingleSlotMerger
	{
		public BooleanAndSingleSlotMerger(@NonNull ModelMerger modelMerger) {
			super(modelMerger);
		}

		@Override
		public Object merge(@NonNull String path, @NonNull Iterable<Object> slots) {
			Boolean mergedSlot = Boolean.TRUE;
			for (Object slot : slots) {
				if (slot == Boolean.FALSE) {
					mergedSlot = Boolean.FALSE;
				}
			}
			return mergedSlot;
		}
	}

	public static class BooleanOrSingleSlotMerger extends SingleSlotMerger
	{
		public BooleanOrSingleSlotMerger(@NonNull ModelMerger modelMerger) {
			super(modelMerger);
		}

		@Override
		public Object merge(@NonNull String path, @NonNull Iterable<Object> slots) {
			Boolean mergedSlot = Boolean.FALSE;
			for (Object slot : slots) {
				if (slot == Boolean.TRUE) {
					mergedSlot = Boolean.TRUE;
				}
			}
			return mergedSlot;
		}
	}

	public static class ClassMultipleSlotMerger extends MultipleSlotMerger
	{
		public ClassMultipleSlotMerger(@NonNull ModelMerger modelMerger) {
			super(modelMerger, PivotPackage.Literals.CLASS);
		}

		public String getKey(Object slot) {
			return ((org.eclipse.ocl.pivot.Class)slot).getName();
		}

	/*	@Override
		protected EClass getMergedEClass(Iterable<@NonNull ? extends EObject> eObjects) {
			EClass mergedEClass = super.getMergedEClass(eObjects);
			if (mergedEClass == PivotPackage.Literals.PRIMITIVE_TYPE) {
				mergedEClass = PivotPackage.Literals.CLASS;
			}
			return mergedEClass;
		} */

		@Override
		public @NonNull List<Object> merge(@NonNull String path, @NonNull Iterable<@NonNull Iterable<Object>> slotSlots) {
			Map<String, @NonNull List<org.eclipse.ocl.pivot.@NonNull Class>> key2slots = new HashMap<>();
			for (@NonNull Iterable<Object> slots : slotSlots) {
				for (Object slot : slots) {
					assert slot != null;
					org.eclipse.ocl.pivot.@NonNull Class asClass = (org.eclipse.ocl.pivot.Class)slot;
					String key = getKey(asClass);
					List<org.eclipse.ocl.pivot.@NonNull Class> keyedSlots = key2slots.get(key);
					if (keyedSlots == null) {
						keyedSlots = new ArrayList<>();
						key2slots.put(key, keyedSlots);
					}
					keyedSlots.add(asClass);
				}
			}
			List<Object> mergedSlots = new ArrayList<>();
			List<String> keys = new ArrayList<>(key2slots.keySet());
			Collections.sort(keys);
			for (String key : keys) {
				List<org.eclipse.ocl.pivot.@NonNull Class> keyedSlots = key2slots.get(key);
				assert keyedSlots != null;
				EObject mergedClass = mergeEObjects(path, keyedSlots);
				mergedSlots.add(mergedClass);
			}
			return mergedSlots;
		}
	}

	public static class CommentMultipleSlotMerger extends MultipleSlotMerger
	{
		public CommentMultipleSlotMerger(@NonNull ModelMerger modelMerger) {
			super(modelMerger, PivotPackage.Literals.COMMENT);
		}

		@Override
		public @Nullable List<Object> merge(@NonNull String path, @NonNull Iterable<@NonNull Iterable<Object>> slots) {
			List<Object> mergedSlots = null;
			for (@NonNull Iterable<Object> slot : slots) {
				if (!Iterables.isEmpty(slot)) {
					if (mergedSlots == null) {
						mergedSlots = new ArrayList<>();
					}
					Iterables.addAll(mergedSlots, slot);		// XXX duplicates
				}
			}
			if (mergedSlots != null) {
				mergedSlots = Collections.singletonList(mergeEObjects(path, (Iterable<@NonNull ? extends EObject>) mergedSlots));
			}
			return mergedSlots;
		}
	}

	public static class EObjectSingleSlotMerger extends SingleSlotMerger
	{
		public EObjectSingleSlotMerger(@NonNull ModelMerger modelMerger) {
			super(modelMerger);
		}

		@Override
		public Object merge(@NonNull String path, @NonNull Iterable<Object> slots) {
			List<@NonNull EObject> eObjects = null;
			for (Object slot : slots) {
				if (slot != null) {
					if (eObjects == null) {
						eObjects = new ArrayList<>();
					}
					eObjects.add((EObject)slot);
				}
			}
			return eObjects != null ? mergeEObjects(path, eObjects) : null;
		}
	}

	public static class FirstSingleSlotMerger extends SingleSlotMerger
	{
		public FirstSingleSlotMerger(@NonNull ModelMerger modelMerger) {
			super(modelMerger);
		}

		@Override
		public Object merge(@NonNull String path, @NonNull Iterable<Object> slots) {
			Object mergedSlot = this;
			for (Object slot : slots) {
				if (mergedSlot == this) {
					mergedSlot = slot;
				}
			}
			return mergedSlot;
		}
	}

	public static class IteratorsMultipleSlotMerger extends NamedElementMultipleSlotMerger
	{
		public IteratorsMultipleSlotMerger(@NonNull ModelMerger modelMerger) {
			super(modelMerger);
		}

		@Override
		public Integer getKey(Object slot) {
			Parameter iterator = (Parameter)slot;
			Iteration iteration = (Iteration)iterator.eContainer();			// XXX bad containeerID getOwningOperation();
			int index = iteration.getOwnedIterators().indexOf(iterator);
			return index;
		}

		@Override
		protected void sort(@NonNull List<Object> keys) {}
	}

	public static class ModelPackagesMultipleSlotMerger extends MultipleSlotMerger
	{
		public ModelPackagesMultipleSlotMerger(@NonNull ModelMerger modelMerger) {
			super(modelMerger);
		}

	/*	@Override
		public @Nullable List<Object> merge(@NonNull String path, @NonNull Iterable<@NonNull Iterable<Object>> slots) {
			List<Object> mergedSlots = null;
			for (@NonNull Iterable<Object> slot : slots) {
				if (!Iterables.isEmpty(slot)) {
					if (mergedSlots == null) {
						mergedSlots = new ArrayList<>();
					}
					Iterables.addAll(mergedSlots, slot);		// XXX duplicates
				}
			}
			if (mergedSlots != null) {
				mergedSlots = Collections.singletonList(mergeEObjects(path, (Iterable<@NonNull ? extends EObject>) mergedSlots));
			}
			return mergedSlots;
		} */

		public org.eclipse.ocl.pivot.@Nullable Package mergePackage(@NonNull PivotHelper helper, @NonNull CompletePackage completePackage) {
			String packageName = completePackage.getName();
			String packagePrefix = completePackage.getNsPrefix();
			String packageURI = completePackage.getURI();
		//	org.eclipse.ocl.pivot.@NonNull Package mergedPackage = helper.createPackage(packageName, packagePrefix, packageURI);

			EObject mergedEObject = mergeEObjects("partialPackages", completePackage.getPartialPackages());
			org.eclipse.ocl.pivot.@NonNull Package mergedPackage = (org.eclipse.ocl.pivot.Package) mergedEObject;
			return mergedPackage;
		}
	}

	public static class MultipleSlotMerger extends AbstractSlotMerger
	{
		protected final @Nullable EStructuralFeature eFeature;
		protected final @Nullable EClassifier eClassifier;

		protected MultipleSlotMerger(@NonNull ModelMerger modelMerger) {
			super(modelMerger);
			this.eFeature = null;
			this.eClassifier = null;
		}

		protected MultipleSlotMerger(@NonNull ModelMerger modelMerger, @NonNull EStructuralFeature eFeature) {
			super(modelMerger);
			this.eFeature = eFeature;
			this.eClassifier = eFeature.getEType();
		}

		protected MultipleSlotMerger(@NonNull ModelMerger modelMerger, @NonNull EClassifier eClassifier) {
			super(modelMerger);
			this.eFeature = null;
			this.eClassifier = eClassifier;
		}

		public @Nullable List<Object> merge(@NonNull String path, @NonNull Iterable<@NonNull Iterable<Object>> slots) {
			List<Object> mergedSlots = new ArrayList<>();
			for (@NonNull Iterable<Object> slot : slots) {
				Iterables.addAll(mergedSlots, slot);		// XXX duplicates
			}
			return mergedSlots;
		}

	/*	@Override
		public @NonNull String toString() {
			EStructuralFeature eFeature2 = eFeature;
			if (eFeature2 != null) {
				return "Merge-" + eFeature2.eClass().getName() + "::" + eFeature2.getName();
			}
			else {
				return "Merge-" + eClassifier.getName();
			}
		} */
	}

	public static class NamedElementMultipleSlotMerger extends MultipleSlotMerger
	{
		public NamedElementMultipleSlotMerger(@NonNull ModelMerger modelMerger) {
			super(modelMerger);
		}

		public Object getKey(Object slot) {
			return ((NamedElement)slot).getName();
		}

		@Override
		public @Nullable List<Object> merge(@NonNull String path, @NonNull Iterable<@NonNull Iterable<Object>> slotSlots) {
			Map<Object, @NonNull List<@NonNull NamedElement>> key2slots = new HashMap<>();
			for (@NonNull Iterable<Object> slots : slotSlots) {
				for (Object slot : slots) {
					assert slot != null;
					@NonNull NamedElement asNamedElement = (NamedElement)slot;
					Object key = getKey(asNamedElement);
					List<@NonNull NamedElement> keyedSlots = key2slots.get(key);
					if (keyedSlots == null) {
						keyedSlots = new ArrayList<>();
						key2slots.put(key, keyedSlots);
					}
					keyedSlots.add(asNamedElement);
				}
			}
			List<Object> mergedSlots = new ArrayList<>();
			List<Object> keys = new ArrayList<>(key2slots.keySet());
			sort(keys);
			for (Object key : keys) {
				List<@NonNull NamedElement> keyedSlots = key2slots.get(key);
				assert keyedSlots != null;
				EObject mergedNamedElement = mergeEObjects(path, keyedSlots);
				mergedSlots.add(mergedNamedElement);
			}
			return mergedSlots;
		}

		protected void sort(@NonNull List<Object> keys) {
			Collections.sort((List<String>)(Object)keys);
		}
	}

	public static class OperationMultipleSlotMerger extends NamedElementMultipleSlotMerger
	{
		public OperationMultipleSlotMerger(@NonNull ModelMerger modelMerger) {
			super(modelMerger);
		}

		@Override
		public Object getKey(Object slot) {
			return ((Operation)slot).getOperationId();
		}

		@Override
		protected void sort(@NonNull List<Object> keys) {
			Collections.sort(keys, NameUtil.TO_STRING_COMPARATOR);
		}
	}

	public static class OppositeSingleSlotMerger extends SingleSlotMerger
	{
		protected OppositeSingleSlotMerger(@NonNull ModelMerger modelMerger) {
			super(modelMerger);
		}

		@Override
		public Object merge(@NonNull String path, @NonNull Iterable<Object> slots) {
			Object mergedSlot = null;
			String mergedPropertyName = null;
			for (Object slot : slots) {
				if (slot != null) {
					String propertyName = ((Property)slot).getName();
					if (mergedPropertyName == null) {
						mergedSlot = slot;
						mergedPropertyName = propertyName;
					}
					else {
						if (!Objects.equal(propertyName, mergedPropertyName)) {
							StringBuilder s = new StringBuilder();
							s.append("Conflicting " + path);
							for (Object slot2 : slots) {
								s.append("\n\t" + slot2);
							}
							System.out.println(s.toString());
							break;
						}
					}
				}
			}
			return mergedSlot;
		}
	}

	public static class PropertyMultipleSlotMerger extends NamedElementMultipleSlotMerger
	{
		public PropertyMultipleSlotMerger(@NonNull ModelMerger modelMerger) {
			super(modelMerger);
		}

		@Override
		public Object getKey(Object slot) {
			return ((Property)slot).getPropertyId();
		}

		@Override
		protected void sort(@NonNull List<Object> keys) {
			Collections.sort(keys, NameUtil.TO_STRING_COMPARATOR);
		}
	}

	public static class ParametersMultipleSlotMerger extends NamedElementMultipleSlotMerger
	{
		public ParametersMultipleSlotMerger(@NonNull ModelMerger modelMerger) {
			super(modelMerger);
		}

		@Override
		public Integer getKey(Object slot) {
			Parameter parameter = (Parameter)slot;
			Operation operation = parameter.getOwningOperation();
			int index = operation.getOwnedParameters().indexOf(parameter);
			return index;
		}

		@Override
		protected void sort(@NonNull List<Object> keys) {}
	}

	public static class SingleSlotMerger extends AbstractSlotMerger
	{
		protected SingleSlotMerger(@NonNull ModelMerger modelMerger) {
			super(modelMerger);
		}

		public Object merge(@NonNull String path, @NonNull Iterable<Object> slots) {
			Object mergedSlot = this;
			for (Object slot : slots) {
				if (mergedSlot == this) {
					mergedSlot = slot;
				}
				else {
					if (!Objects.equal(slot, mergedSlot)) {
						StringBuilder s = new StringBuilder();
						s.append("Conflicting " + path);
						for (Object slot2 : slots) {
							s.append("\n\t" + slot2);
						}
						System.out.println(s.toString());
						break;
					}
				}
			}
			return mergedSlot;
		}
	}

	public static class StringSingleSlotMerger extends SingleSlotMerger
	{
		public StringSingleSlotMerger(@NonNull ModelMerger modelMerger) {
			super(modelMerger);
		}

		@Override
		public String merge(@NonNull String path, @NonNull Iterable<Object> slots) {
			String mergedSlot = null;
			for (Object slot : slots) {
				if (mergedSlot == null) {
					mergedSlot = (String) slot;
				}
			}
			return mergedSlot;
		}
	}

	public static class TemplateParametersMultipleSlotMerger extends NamedElementMultipleSlotMerger
	{
		public TemplateParametersMultipleSlotMerger(@NonNull ModelMerger modelMerger) {
			super(modelMerger);
		}

		@Override
		public Integer getKey(Object slot) {
			TemplateParameter templateParameter = (TemplateParameter)slot;
			TemplateableElement templateableElement = templateParameter.getOwningTemplateableElement();
			int index = templateableElement.getOwnedTemplateParameters().indexOf(templateParameter);
			return index;
		}

		@Override
		protected void sort(@NonNull List<Object> keys) {}
	}

	public static class TypeSingleSlotMerger extends SingleSlotMerger
	{
		protected final @NonNull CompleteModel completeModel;

		public TypeSingleSlotMerger(@NonNull ModelMerger modelMerger, @NonNull CompleteModel completeModel) {
			super(modelMerger);
			this.completeModel = completeModel;
		}

		@Override
		public Object merge(@NonNull String path, @NonNull Iterable<Object> slots) {
			CompleteClass mergedType = null;
			NormalizedTemplateParameter mergedNormalizedTemplateParameter = null;
			for (Object slot : slots) {
				if (slot instanceof org.eclipse.ocl.pivot.Class) {
					CompleteClass type = completeModel.getCompleteClass((org.eclipse.ocl.pivot.Class)slot);
					if (mergedType == null) {
						mergedType = type;
					}
					else if (mergedType != type) {
						StringBuilder s = new StringBuilder();
						s.append("Conflicting " + path);
						for (Object slot2 : slots) {
							s.append("\n\t" + slot2);
						}
						System.out.println(s.toString());
					}
				}
				else {
					NormalizedTemplateParameter templateParameter = (NormalizedTemplateParameter)slot;
					if (mergedNormalizedTemplateParameter == null) {
						mergedNormalizedTemplateParameter = templateParameter;
					}
					else if (mergedNormalizedTemplateParameter != templateParameter) {
						StringBuilder s = new StringBuilder();
						s.append("Conflicting " + path);
						for (Object slot2 : slots) {
							s.append("\n\t" + slot2);
						}
						System.out.println(s.toString());
					}
				}
			}
			if (mergedType != null) {
				assert mergedNormalizedTemplateParameter == null;
				return mergedType.getPrimaryClass();
			}
			else {
				assert mergedType == null;
				return mergedNormalizedTemplateParameter;
			}
		}
	}

/*	public static abstract class FeatureMerger
	{
		protected final @NonNull EStructuralFeature eFeature;

		protected FeatureMerger(@NonNull EStructuralFeature eFeature) {
			this.eFeature = eFeature;
		}

		public abstract Object merge(@NonNull Iterable<Object> slots);

		@Override
		public @NonNull String toString() {
			return "Merge-" + eFeature.eClass().getName() + "::" + eFeature.getName();
		}
	}

	public class DefaultFeatureMerger extends FeatureMerger
	{
		protected DefaultFeatureMerger(@NonNull EStructuralFeature eFeature) {
			super(eFeature);
		}

		@Override
		public Object merge(@NonNull Iterable<Object> slots) {
			if (eFeature.isMany()) {
				return mergeSlots(eFeature, slots);
			}
			else {
				return mergeSlot(eFeature, slots);
			}
		}
	} */

/*	public class DefaultSlotMerger extends SlotMerger
	{
		protected DefaultSlotMerger(@NonNull Class<?> slotClass) {
			super(slotClass);
		}

		@Override
		public Object merge(@NonNull EStructuralFeature eFeature, @NonNull Iterable<Object> slots) {
			if (eFeature.isMany()) {
				return mergeSlots(eFeature, slots);
			}
			else {
				return mergeSlot(eFeature, slots);
			}
		}
	} */

	private Logger log = Logger.getLogger(getClass());
	protected @NonNull List<@NonNull String> ecoreURIs = new ArrayList<>();
//	protected String libraryURI = null;
	protected @NonNull List<@NonNull String> oclURIs = new ArrayList<>();
	private String mergedURI;
	protected String invariantPrefix;
	protected final @NonNull Map<@NonNull EClassifier, @NonNull MultipleSlotMerger> classifier2multipleSlotMerger = new HashMap<>();
	protected final @NonNull Map<@NonNull EClassifier, @NonNull SingleSlotMerger> classifier2singleSlotMerger = new HashMap<>();
	protected final @NonNull Map<@NonNull EStructuralFeature, @NonNull MultipleSlotMerger> feature2multipleSlotMerger = new HashMap<>();
	protected final @NonNull Map<@NonNull EStructuralFeature, @NonNull SingleSlotMerger> feature2singleSlotMerger = new HashMap<>();
	protected final @NonNull Map<@NonNull EObject, @NonNull EObject> eObject2mergedEObject = new HashMap<>();

	public ModelMerger() {
	//	OCLstdlib.install();
		CompleteOCLStandaloneSetup.doSetup();
		OCLinEcoreStandaloneSetup.doSetup();
		OCLstdlibStandaloneSetup.doSetup();
	}

	public void addEcoreURI(String ecoreURI) {
		assert ecoreURI != null;
		ecoreURIs.add(ecoreURI);
	}

	public void addOclURI(String oclURI) {
		assert oclURI != null;
		oclURIs.add(oclURI);
	}

	@Override
	public void checkConfiguration(Issues issues) {
		super.checkConfiguration(issues);
	//	if (ecoreURIs.size() <= 0) {
	//		issues.addError(this, "no ecoreURI specified.");
	//	}
		if (oclURIs.size() <= 0) {
			issues.addError(this, "no oclURI specified.");
		}
	}

/*	protected @NonNull SlotMerger getSlotMerger(@NonNull Class<?> slotClass) {
		SlotMerger slotMerger = slotClass2merger.get(slotClass);
		if (slotMerger == null) {
			slotMerger = new DefaultSlotMerger(slotClass);
			slotClass2merger.put(slotClass, slotMerger);
		}
		return slotMerger;
	} */

/*	protected @NonNull FeatureMerger getFeatureMerger(@NonNull EStructuralFeature eFeature) {
		FeatureMerger featureMerger = feature2merger.get(eFeature);
		if (featureMerger == null) {
			featureMerger = new DefaultFeatureMerger(eFeature);
			feature2merger.put(eFeature, featureMerger);
		}
		return featureMerger;
	} */

	protected @NonNull MultipleSlotMerger getMultipleSlotMerger(@NonNull EStructuralFeature eFeature) {
		assert eFeature.isMany();
		MultipleSlotMerger slotMerger = feature2multipleSlotMerger.get(eFeature);
		if (slotMerger == null) {
			EClassifier eType = eFeature.getEType();
			slotMerger = classifier2multipleSlotMerger.get(eType);
			if (slotMerger == null) {
				slotMerger = new MultipleSlotMerger(this, eFeature);
			}
			feature2multipleSlotMerger.put(eFeature, slotMerger);
		}
		return slotMerger;
	}

	protected @NonNull SingleSlotMerger getSingleSlotMerger(@NonNull EStructuralFeature eFeature) {
		assert !eFeature.isMany();
		SingleSlotMerger slotMerger = feature2singleSlotMerger.get(eFeature);
		if (slotMerger == null) {
			EClassifier eType = eFeature.getEType();
			slotMerger = classifier2singleSlotMerger.get(eType);
			if (slotMerger == null) {
				slotMerger = new SingleSlotMerger(this);
			}
			feature2singleSlotMerger.put(eFeature, slotMerger);
		}
		return slotMerger;
	}

	protected Map<Object, Object> getSaveOptions(@NonNull XMLResource resource) {
		Map<Object, Object> result = XMIUtil.createSaveOptions(resource);
		result.put(Resource.OPTION_SAVE_ONLY_IF_CHANGED, Resource.OPTION_SAVE_ONLY_IF_CHANGED_MEMORY_BUFFER);
		result.put(ASResource.OPTION_NORMALIZE_CONTENTS, Boolean.TRUE);
		return result;
	}

	public void installSlotMergers(EnvironmentFactory environmentFactory) {
		feature2multipleSlotMerger.put(PivotPackage.Literals.ANNOTATION__OWNED_DETAILS, new NamedElementMultipleSlotMerger(this));
		feature2multipleSlotMerger.put(PivotPackage.Literals.CLASS__OWNED_INVARIANTS, new NamedElementMultipleSlotMerger(this));
		feature2multipleSlotMerger.put(PivotPackage.Literals.CLASS__OWNED_OPERATIONS, new OperationMultipleSlotMerger(this));
		feature2multipleSlotMerger.put(PivotPackage.Literals.CLASS__OWNED_PROPERTIES, new PropertyMultipleSlotMerger(this));
		feature2multipleSlotMerger.put(PivotPackage.Literals.ELEMENT__OWNED_ANNOTATIONS, new NamedElementMultipleSlotMerger(this));
		feature2multipleSlotMerger.put(PivotPackage.Literals.ELEMENT__OWNED_COMMENTS, new CommentMultipleSlotMerger(this));
		feature2multipleSlotMerger.put(PivotPackage.Literals.ENUMERATION__OWNED_LITERALS, new NamedElementMultipleSlotMerger(this));
		feature2multipleSlotMerger.put(PivotPackage.Literals.ITERATION__OWNED_ITERATORS, new IteratorsMultipleSlotMerger(this));
		feature2multipleSlotMerger.put(PivotPackage.Literals.LIBRARY__OWNED_PRECEDENCES, new NamedElementMultipleSlotMerger(this));
		feature2multipleSlotMerger.put(PivotPackage.Literals.OPERATION__OWNED_PARAMETERS, new ParametersMultipleSlotMerger(this));
		feature2multipleSlotMerger.put(PivotPackage.Literals.OPERATION__OWNED_POSTCONDITIONS, new NamedElementMultipleSlotMerger(this));
		feature2multipleSlotMerger.put(PivotPackage.Literals.OPERATION__OWNED_PRECONDITIONS, new NamedElementMultipleSlotMerger(this));
		feature2multipleSlotMerger.put(PivotPackage.Literals.PACKAGE__OWNED_CLASSES, new ClassMultipleSlotMerger(this));
		feature2multipleSlotMerger.put(PivotPackage.Literals.TEMPLATEABLE_ELEMENT__OWNED_TEMPLATE_PARAMETERS, new TemplateParametersMultipleSlotMerger(this));
		feature2singleSlotMerger.put(PivotPackage.Literals.CLASS__INSTANCE_CLASS_NAME, new FirstSingleSlotMerger(this));
		feature2singleSlotMerger.put(PivotPackage.Literals.CLASS__IS_ABSTRACT, new BooleanOrSingleSlotMerger(this));
		feature2singleSlotMerger.put(PivotPackage.Literals.CLASS__IS_INTERFACE, new BooleanOrSingleSlotMerger(this));
		feature2singleSlotMerger.put(PivotPackage.Literals.COMMENT__BODY, new FirstSingleSlotMerger(this));
		feature2singleSlotMerger.put(PivotPackage.Literals.CONSTRAINT__IS_CALLABLE, new BooleanOrSingleSlotMerger(this));
		feature2singleSlotMerger.put(PivotPackage.Literals.CONSTRAINT__OWNED_SPECIFICATION, new FirstSingleSlotMerger(this));
		feature2singleSlotMerger.put(PivotPackage.Literals.FEATURE__IMPLEMENTATION_CLASS, new StringSingleSlotMerger(this));
		feature2singleSlotMerger.put(PivotPackage.Literals.ITERATION__OWNED_ACCUMULATOR, new EObjectSingleSlotMerger(this));
		//	feature2singleSlotMerger.put(PivotPackage.Literals.NAMED_ELEMENT__NAME, new PivotNameSingleSlotMerger(this));		// XXX Fudge irregularity
		feature2singleSlotMerger.put(PivotPackage.Literals.OPERATION__BODY_EXPRESSION, new FirstSingleSlotMerger(this));
		feature2singleSlotMerger.put(PivotPackage.Literals.PACKAGE__NS_PREFIX, new FirstSingleSlotMerger(this));
		feature2singleSlotMerger.put(PivotPackage.Literals.PACKAGE__URI, new FirstSingleSlotMerger(this));
		feature2singleSlotMerger.put(PivotPackage.Literals.PROPERTY__DEFAULT_VALUE_STRING, new StringSingleSlotMerger(this));
		feature2singleSlotMerger.put(PivotPackage.Literals.PROPERTY__IS_DERIVED, new BooleanOrSingleSlotMerger(this));
		feature2singleSlotMerger.put(PivotPackage.Literals.PROPERTY__IS_RESOLVE_PROXIES, new BooleanAndSingleSlotMerger(this));
		feature2singleSlotMerger.put(PivotPackage.Literals.PROPERTY__IS_TRANSIENT, new BooleanOrSingleSlotMerger(this));
		feature2singleSlotMerger.put(PivotPackage.Literals.PROPERTY__IS_VOLATILE, new BooleanOrSingleSlotMerger(this));
		feature2singleSlotMerger.put(PivotPackage.Literals.PROPERTY__OPPOSITE, new OppositeSingleSlotMerger(this));
		feature2singleSlotMerger.put(PivotPackage.Literals.TYPED_ELEMENT__IS_REQUIRED, new FirstSingleSlotMerger(this));
		feature2singleSlotMerger.put(PivotPackage.Literals.TYPED_ELEMENT__TYPE, new TypeSingleSlotMerger(this, environmentFactory.getCompleteModel()));
	}

	@Override
	public void invokeInternal(WorkflowContext ctx, ProgressMonitor arg1, Issues arg2) {
	//	StandardLibraryContribution savedContribution = StandardLibraryContribution.REGISTRY.remove(OCLstdlib.STDLIB_URI);	// XXX
		ResourceSet resourceSet = getResourceSet();
		StandaloneProjectMap.IProjectDescriptor projectDescriptor = ClassUtil.requireNonNull(getProjectDescriptor());
		//		Resource ecoreResource = (Resource) ctx.get(getModelSlot());
		//		EPackage ecorePivotPackage = (EPackage) ecoreResource.getContents().get(0);
		//		final String pivotNsURI = ClassUtil.requireNonNull(ecorePivotPackage.getNsURI());
		OCLInternal ocl = OCLInternal.newInstance(resourceSet);
		EnvironmentFactory environmentFactory = ocl.getEnvironmentFactory();
		PivotHelper helper = new PivotHelper(environmentFactory);
		CompleteModel completeModel = environmentFactory.getCompleteModel();
		ResourceSet asResourceSet = environmentFactory.getASResourceSet();
		//		ocl.getResourceSet().getResources().add(ecoreResource);		// Don't load another copy
	//	String libraryURI2 = libraryURI;
	//	if (libraryURI2 != null) {
	//		ocl.getStandardLibrary().setDefaultStandardLibraryURI(libraryURI2);
	//		StandardLibraryContribution.REGISTRY.put(libraryURI2, new OCLstdlib.Loader());
	//	}


		//
		try {
			for (@NonNull String ecoreURI : ecoreURIs) {
				URI uri = projectDescriptor.getPlatformResourceURI(ecoreURI);
				log.info("Loading " + uri);
				Resource ecoreResource = resourceSet.getResource(uri, true);
				assert ecoreResource != null;
				EcoreUtil.resolveAll(ecoreResource);
				ResourceUtils.checkResource(ecoreResource);
			//	for (EObject eObject : ecoreResource.getContents()) {
			//		if (eObject instanceof EPackage) {
			//			EPackage ePackage = (EPackage) eObject;
			//		//	ClassUtil.getMetamodelAnnotation(ePackage); // Install EAnnotation
			//		}
			//	}
				Ecore2AS ecore2as = Ecore2AS.getAdapter(ecoreResource, environmentFactory);
				Model pivotModel = ecore2as.getASModel();
				ASResource asResource = ClassUtil.requireNonNull((ASResource)pivotModel.eResource());
			//	((CompleteModelImpl)environmentFactory.getCompleteModel()).suppressAutoloading(false);
				ResourceUtils.checkResource(asResource);
			}
			EcoreUtil.resolveAll(resourceSet);
			ResourceUtils.checkResourceSet(resourceSet);
			Set<@NonNull Resource> primaryASResources = Sets.newHashSet(ClassUtil.nullFree(asResourceSet.getResources()));
			//
			for (@NonNull String oclURI : oclURIs) {
				URI uri = projectDescriptor.getPlatformResourceURI(oclURI);
				log.info("Merging " + uri);
				CSResource csResource = ocl.getCSResource(uri);
				ResourceUtils.checkResourceSet(ocl.getResourceSet());
				@SuppressWarnings("unused") Resource oclResource = csResource.getCS2AS(ocl.getEnvironmentFactory()).getASResource();
				ResourceUtils.checkResourceSet(asResourceSet);
			}
			ResourceUtils.checkResourceSet(asResourceSet);
			//

			installSlotMergers(environmentFactory);
			URI mergedFileURI = projectDescriptor.getPlatformResourceURI(mergedURI);
			URI mergedASFileURI = mergedFileURI.trimFileExtension().appendFileExtension("oclas");
			ASResource mergedResource = (ASResource) resourceSet.createResource(mergedASFileURI, null);
			Model asModel = helper.createModel(mergedFileURI.toString());
			ModelPackagesMultipleSlotMerger rootSlotMerger = new ModelPackagesMultipleSlotMerger(this);	// XXX regularize
			for (CompletePackage completePackage : completeModel.getOwnedCompletePackages()) {
				if (completePackage != completeModel.getOrphanCompletePackage()) {
					org.eclipse.ocl.pivot.Package mergedPackage = rootSlotMerger.mergePackage(helper, completePackage);
					if (mergedPackage != null) {
						asModel.getOwnedPackages().add(mergedPackage);
					}
				}
			}
			mergedResource.getContents().add(asModel);

			for (Map.Entry<EObject, Collection<Setting>> entry : EcoreUtil.CrossReferencer.find(mergedResource.getContents()).entrySet()) {
				EObject eTarget = entry.getKey();
				if (!Orphanage.isOrphan((Element)eTarget)) {
					for (EStructuralFeature.Setting setting : entry.getValue()) {
						EObject mergedEObject = eObject2mergedEObject.get(eTarget);
						if (mergedEObject != null) {
							EObject eSource = setting.getEObject();
							EStructuralFeature eStructuralFeature = setting.getEStructuralFeature();
							if (eStructuralFeature.isMany()) {
								@SuppressWarnings("unchecked")
								List<EObject> siblings = (List<EObject>) eSource.eGet(eStructuralFeature);
								int index = siblings.indexOf(eTarget);
								assert index >= 0;
								siblings.set(index, mergedEObject);
							}
							else {
								eSource.eSet(eStructuralFeature, mergedEObject);
							}
						}
					}
				}
			}


			log.info("Saving " + mergedASFileURI);
			Map<Object, Object> saveOptions = getSaveOptions(mergedResource);
			mergedResource.save(saveOptions);

		/*	Set<@NonNull Resource> modifiedPrimaryASResources = new HashSet<>();
			Map<@NonNull CompleteClass, @NonNull List<org.eclipse.ocl.pivot.@NonNull Class>> completeClass2mergeTypes = new HashMap<>();
			for (Resource secondaryASResource : asResourceSet.getResources()) {
				if (!primaryASResources.contains(secondaryASResource)) {
					for (TreeIterator<EObject> tit = secondaryASResource.getAllContents(); tit.hasNext(); ) {
						EObject eObject = tit.next();
						if (eObject instanceof Library) {
						//	tit.prune();		// XXX precedences
						}
						else if (eObject instanceof Orphanage) {
							tit.prune();
						}
						else if (eObject instanceof org.eclipse.ocl.pivot.Class) {
							org.eclipse.ocl.pivot.Class mergeType = (org.eclipse.ocl.pivot.Class)eObject;
							CompleteClass completeClass = completeModel.getCompleteClass(mergeType);
							List<org.eclipse.ocl.pivot.@NonNull Class> mergeTypes = completeClass2mergeTypes.get(completeClass);
							if (mergeTypes == null) {
								mergeTypes = new ArrayList<>();
								completeClass2mergeTypes.put(completeClass, mergeTypes);
							}
							mergeTypes.add(mergeType);
							tit.prune();
						}
					}
				}
			}
			for (@NonNull Entry<@NonNull CompleteClass, @NonNull List<@NonNull Class>> entry : completeClass2mergeTypes.entrySet()) {
				@NonNull CompleteClass completeClass = entry.getKey();
				List<org.eclipse.ocl.pivot.@NonNull Class> mergeTypes = entry.getValue();
				boolean merged = false;
				for (org.eclipse.ocl.pivot.@NonNull Class partialClass : PivotUtil.getPartialClasses(completeClass)) {
					Resource primaryASResource = PivotUtil.getResource(partialClass);
					if (primaryASResources.contains(primaryASResource)) {
						modifiedPrimaryASResources.add(primaryASResource);
						for (org.eclipse.ocl.pivot.@NonNull Class mergeType : mergeTypes) {
							mergeType(completeModel, partialClass, mergeType);
						}
						merged = true;
						break;
					}
				}
				if (!merged) {
					// FIXME migrate class
					getClass();		// XXX
				}
			} */
			//
			irregularPivotConditioning(mergedResource);
			Map<@NonNull String, @Nullable Object> options = new HashMap<>();
			options.put(AS2Ecore.OPTION_SUPPRESS_DUPLICATES, true);
			options.put(AS2Ecore.OPTION_INVARIANT_PREFIX, invariantPrefix);
			Model mergedModel = PivotUtil.getModel(mergedResource);
			String externalURI = mergedModel.getExternalURI();
			URI ecoreURI = URI.createURI(externalURI);
		/*	ProjectManager projectManager = StandaloneProjectMap.findAdapter(resourceSet);
			resourceSet.getResources().clear();
			for (@NonNull Resource modifiedPrimaryASResource : modifiedPrimaryASResources) {
				Model asModel = PivotUtil.getModel(modifiedPrimaryASResource);
				String externalURI = asModel.getExternalURI();
				URI ecoreURI = URI.createURI(externalURI);
				//				Resource oldEcoreResource = resourceSet.getResource(ecoreURI, false);
				//				if (oldEcoreResource != null) {
				//
				//				}
				if (projectManager != null) {
					IResourceDescriptor resourceDescriptor = null;
					resourceDescriptor = projectManager.getResourceDescriptor(ecoreURI);
					if (resourceDescriptor != null) {
						resourceDescriptor.unload(resourceSet);
						resourceDescriptor.configure(resourceSet,
							StandaloneProjectMap.CreateStrategy.INSTANCE,
							StandaloneProjectMap.MapToFirstConflictHandlerWithLog.INSTANCE);
					}
				} */
				Resource newEcoreResource = AS2Ecore.createResource(environmentFactory, mergedResource, ecoreURI, options);
				//				projectDescriptor.configure(ecoreResource2.getResourceSet(), StandaloneProjectMap.LoadBothStrategy.INSTANCE, null);
				XMIUtil.assignIds(newEcoreResource, new XMIUtil.StructuralENamedElementIdCreator(), null);
				Map<Object, Object> saveOptions2 = XMIUtil.createSaveOptions(newEcoreResource);
				XMIUtil.retainLineWidth(saveOptions2, newEcoreResource);
				log.info("Saving " + mergedFileURI);
				newEcoreResource.save(saveOptions); //getSaveOptions());
				//				resourceSet.getResources().remove(ecoreResource2);
		//	} */
			ocl.dispose(true);
		} catch (IOException e) {
			throw new RuntimeException("Problems running " + getClass().getSimpleName(), e);
		}
	}

	private void irregularPivotConditioning(@NonNull ASResource asResource) {
		Model asModel = PivotUtil.getModel(asResource);
		for (org.eclipse.ocl.pivot.Package asPackage : PivotUtil.getOwnedPackages(asModel)) {
			if ("ocl".equals(asPackage.getName())) {
				asPackage.setName("pivot");
			}
			if ("ocl".equals(asPackage.getNsPrefix())) {
				asPackage.setNsPrefix("pivot");
			}
			if ("http://www.eclipse.org/ocl/2015/Library".equals(asPackage.getURI())) {
				asPackage.setURI("http://www.eclipse.org/ocl/2015/Pivot");
			}
			for (org.eclipse.ocl.pivot.Class asClass : PivotUtil.getOwnedClasses(asPackage)) {
				List<Element> ownedAnnotations = asClass.getOwnedAnnotations();
				for (Element asAnnotation : ownedAnnotations) {
					if ((asAnnotation instanceof Annotation) && "http://www.omg.org/ocl".equals(((Annotation)asAnnotation).getName())) {
						ownedAnnotations.remove(asAnnotation);
						break;
					}
				}
				//DelegateInstaller.setConstraintNames(null, ecoreURIs);
				for (Constraint asConstraint : PivotUtil.getOwnedInvariants(asClass)) {
					asConstraint.setIsCallable(true);
				}
			}
		}

	}

/*	protected Object mergeSlot(@NonNull EStructuralFeature eFeature, @NonNull Iterable<Object> slots) {
		Object mergedSlot = this;
		for (Object slot : slots) {
			if (mergedSlot == this) {
				mergedSlot = slot;
			}
			else {
				if (!Objects.equal(slot, mergedSlot)) {
					System.out.println("Conflicting " + eFeature);
				}
			}
		}
		return mergedSlot;
	} */

/*	protected List<? extends Object> mergeSlots(@NonNull EStructuralFeature eFeature, @NonNull Iterable<? extends Object> slots) {
		List<Object> mergedSlots = new ArrayList<>();
		for (Object slot : slots) {
			Iterables.addAll(mergedSlots, slots);		// XXX duplicates
		}
		return null;
	} */

/*	protected List<? extends Object> mergeSlots2(@NonNull EStructuralFeature eFeature, @NonNull Iterable<@NonNull Iterable<Object>> slots) {
		List<Object> mergedSlots = new ArrayList<>();
		for (@NonNull Iterable<Object> slot : slots) {
			Iterables.addAll(mergedSlots, slot);		// XXX duplicates
		}
		return mergedSlots;
	} */

/*	protected void mergeType(@NonNull CompleteModel completeModel, org.eclipse.ocl.pivot.@NonNull Class primaryType, org.eclipse.ocl.pivot.@NonNull Class mergeType) {
		List<Constraint> mergeInvariants = mergeType.getOwnedInvariants();
		List<Constraint> primaryInvariants = primaryType.getOwnedInvariants();
		for (Constraint mergeInvariant : new ArrayList<>(mergeInvariants)) {
			mergeInvariant.setIsCallable(true);
			PivotUtil.resetContainer(mergeInvariant);
			primaryInvariants.add(mergeInvariant);
		}
		List<Property> mergeProperties = mergeType.getOwnedProperties();
		if (mergeProperties.size() > 0) {
			List<Property> primaryProperties = primaryType.getOwnedProperties();
			for (@SuppressWarnings("null")@NonNull Property mergeProperty : new ArrayList<>(mergeProperties)) {
				Property primaryProperty = completeModel.getPrimaryProperty(mergeProperty);
				if (primaryProperty != mergeProperty) {			// If merge needed
					LanguageExpression pivotDefaultExpression = mergeProperty.getOwnedExpression();
					LanguageExpression primaryDefaultExpression = primaryProperty.getOwnedExpression();
					if ((primaryDefaultExpression == null) && (pivotDefaultExpression != null)) {
						primaryProperty.setOwnedExpression(pivotDefaultExpression);
					}
					mergeComments(primaryProperty, mergeProperty);
				}
				else											// Else simple promotion
				{
					//					boolean b1 = primaryProperty.isIsImplicit();
					//					boolean b2 = mergeProperty.isIsImplicit();
					PivotUtil.resetContainer(mergeProperty);
					primaryProperties.add(mergeProperty);
				}
			}
		}
		List<Operation> mergeOperations = mergeType.getOwnedOperations();
		if (mergeOperations.size() > 0) {
			List<Operation> primaryOperations = primaryType.getOwnedOperations();
			for (@SuppressWarnings("null")@NonNull Operation mergeOperation : new ArrayList<>(mergeOperations)) {
				Operation primaryOperation = completeModel.getPrimaryOperation(mergeOperation);
				if (primaryOperation != mergeOperation) {		// If merge needed
					LanguageExpression pivotBodyExpression = mergeOperation.getBodyExpression();
					LanguageExpression primaryBodyExpression = primaryOperation.getBodyExpression();
					if ((primaryBodyExpression == null) && (pivotBodyExpression != null)) {
						PivotUtil.resetContainer(pivotBodyExpression);
						primaryOperation.setBodyExpression(pivotBodyExpression);
					}
					mergeComments(primaryOperation, mergeOperation);
				}
				else											// Else simple promotion
				{
					PivotUtil.resetContainer(mergeOperation);
					primaryOperations.add(mergeOperation);
				}
			}
		}
	} */

/*	public void mergeComments(@NonNull Element primaryElement, @NonNull Element mergeElement) {
		if (primaryElement.getOwnedComments().isEmpty()) {
			Iterable<@NonNull Comment> mergeComments = PivotUtil.getOwnedComments(mergeElement);
			if (!Iterables.isEmpty(mergeComments)) {
				for (Comment mergeComment : Lists.newArrayList(mergeComments)) {
					PivotUtil.resetContainer(mergeComment);
					primaryElement.getOwnedComments().add(mergeComment);
				}
			}
		}
	} */

	//	public void setUri(String uri) {
	//		this.uri = uri;
	//	}

	/**
	 * Define a prefix such as "validate" for all invariant operation names.
	 */
	public void setInvariantPrefix(String invariantPrefix) {
		this.invariantPrefix = invariantPrefix;
	}

	public void putMerged(@NonNull EObject mergedEObject, @NonNull EObject eObject) {
		eObject2mergedEObject.put(eObject, mergedEObject);
	}

	/**
	 * Define a URI to be loaded as the OCL Standard Library.
	 */
//	public void setLibraryURI(String libraryURI) {
//		this.libraryURI = libraryURI;
//	}

	public void setMergedURI(String mergedURI) {
		this.mergedURI = mergedURI;
	}
}
