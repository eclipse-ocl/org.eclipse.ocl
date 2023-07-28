/*******************************************************************************
 * Copyright (c) 2023 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.codegen.oclinecore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.Class;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.Model;
import org.eclipse.ocl.pivot.NamedElement;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.PivotPackage;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.TemplateBinding;
import org.eclipse.ocl.pivot.TemplateParameter;
import org.eclipse.ocl.pivot.TemplateParameterSubstitution;
import org.eclipse.ocl.pivot.TemplateSignature;
import org.eclipse.ocl.pivot.TemplateableElement;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.TypedElement;
import org.eclipse.ocl.pivot.internal.utilities.AS2Moniker;
import org.eclipse.ocl.pivot.utilities.PivotUtil;

import com.google.common.collect.Iterables;

/**
 * The SytnthesisSchedule determines an initialization schedule for the metamodel synthesis.
 */
public class SynthesisSchedule
{
	private static final @NonNull Set<@NonNull Slot> CYCLIC_GUARD = new HashSet<>();

	/**
	 * A SytnthesisSchedule Slot represents the atomic element within the SynthesisSchedule.
	 * Each slot has predecessors and successors that constrain the potential schedules.
	 */
	public static class Slot //implements Nameable
	{
		public static final @NonNull String ROLE_ALL_OPERATIONS = "ALL_OPERATIONS";
		public static final @NonNull String ROLE_ALL_PROPERTIES = "ALL_PROPERTIES";
		public static final @NonNull String ROLE_COMMENTS = "CTOR";
		public static final @NonNull String ROLE_CTOR = "CTOR";
		public static final @NonNull String ROLE_ENUMERATION_LITERALS = "ENUMERATION_LITERALS";
		public static final @NonNull String ROLE_FRAGMENT_OPERATIONS = "FRAGMENT_OPERATIONS";
		public static final @NonNull String ROLE_FRAGMENT_PROPERTIES = "FRAGMENT_PROPERTIES";
		public static final @NonNull String ROLE_OPERATIONS = "OPERATIONS";
		public static final @NonNull String ROLE_PROPERTIES = "PROPERTIES";
		public static final @NonNull String ROLE_SUPER_CLASSES = "SUPER_CLASSES";
		public static final @NonNull String ROLE_TYPE = "TYPE";
		public static final @NonNull String ROLE_TYPE_FRAGMENTS = "TYPE_FRAGMENTS";
	//	protected final @NonNull String name;
		protected final @NonNull Element element;
		protected final @NonNull String role;
		protected final @NonNull List<@NonNull Slot> successors = new ArrayList<>();
		private final @NonNull List<@NonNull Slot> predecessors = new ArrayList<>();
		private int stage = -1;

		public Slot(/*@NonNull String name,*/ @NonNull EObject eInstance, @NonNull String role) {
			this.element = (Element)eInstance;
			this.role = role;
	//		this.name = name;
		}

		public @NonNull Element getElement() {
			return element;
		}

		//	@Override
		//	public @NonNull String getName() {
		//		return name;
		//	}

		public @NonNull List<@NonNull Slot> getPredecessors() {
			return predecessors;
		}

		public @NonNull String getRole() {
			return role;
		}

		public @NonNull List<@NonNull Slot> getSuccessors() {
			return successors;
		}

		@Override
		public @NonNull String toString() {
			StringBuilder s = new StringBuilder();
			if (stage >= 0) {
				s.append(stage);
				s.append(":");
			}
			s.append(role);
			s.append(":");
			s.append(element.eClass().getName());
			s.append("@");
			s.append(Integer.toHexString(System.identityHashCode(element)));
			s.append(": ");
			s.append(element);
			return s.toString();
		}
	}

	private static final class SlotComparator implements Comparator<@NonNull Slot>
	{

		@Override
		public int compare(@NonNull Slot o1, @NonNull Slot o2) {
			String s1 = o1.getRole();
			String s2 = o2.getRole();
			int diff = s1.compareTo(s2);
			if (diff != 0) {
				return diff;
			}
			Element e1 = o1.getElement();
			Element e2 = o2.getElement();
			EClass c1 = e1.eClass();
			EClass c2 = e2.eClass();
			assert c1 != null;
			assert c2 != null;
			int x1 = sortIndex(c1);
			int x2 = sortIndex(c2);
			diff = x1 - x2;
			if (diff != 0) {
				return diff;
			}
			s1 = c1.getName();
			s2 = c2.getName();
			diff = s1.compareTo(s2);
			if (diff != 0) {
				return diff;
			}
			s1 = AS2Moniker.toString(e1);
			s2 = AS2Moniker.toString(e2);
			return s1.compareTo(s2);
		}

		private int sortIndex(@NonNull EClass eClass) {
			if (eClass == PivotPackage.Literals.LIBRARY) {
				return 0;
			}
			if (eClass == PivotPackage.Literals.PACKAGE) {
				return 1;
			}
			if (eClass == PivotPackage.Literals.PRIMITIVE_TYPE) {
				return 2;
			}
			if ((eClass == PivotPackage.Literals.ANY_TYPE) || (eClass == PivotPackage.Literals.INVALID_TYPE) || (eClass == PivotPackage.Literals.SELF_TYPE) || (eClass == PivotPackage.Literals.VOID_TYPE)) {
				return 3;
			}
			if (eClass == PivotPackage.Literals.CLASS) {
				return 3;
			}
			if (eClass == PivotPackage.Literals.TEMPLATE_PARAMETER) {
				return 4;
			}
			if (eClass == PivotPackage.Literals.COLLECTION_TYPE) {
				return 5;
			}
			if (eClass == PivotPackage.Literals.MAP_TYPE) {
				return 6;
			}
			if ((eClass == PivotPackage.Literals.BAG_TYPE) || (eClass == PivotPackage.Literals.ORDERED_SET_TYPE) || (eClass == PivotPackage.Literals.SEQUENCE_TYPE) || (eClass == PivotPackage.Literals.SET_TYPE)) {
				return 7;
			}
			if (eClass == PivotPackage.Literals.TUPLE_TYPE) {
				return 8;
			}
			if (eClass == PivotPackage.Literals.LAMBDA_TYPE) {
				return 9;
			}
			if ((eClass == PivotPackage.Literals.ITERATION) || (eClass == PivotPackage.Literals.OPERATION)) {
				return 10;
			}
			return -1;
		}
	}

	public static class Stage
	{
		private @NonNull String name;
		private @NonNull List<@NonNull Slot> slots;

		public Stage(@NonNull String name, @NonNull List<@NonNull Slot> slots) {
			this.name = name;
			this.slots = slots;
		}

		public @NonNull String getName() {
			return name;
		}

		public @NonNull List<@NonNull Slot> getSlots() {
			return slots;
		}
	}

	private final @NonNull Map<@NonNull EObject, @NonNull Map<@NonNull String, @NonNull Slot>> eInstance2role2slot = new HashMap<>();
	private /*LazyNonNull*/ List<@NonNull Stage> stages = null;
	private /*LazyNonNull*/ List<@NonNull Slot> slots = null;
	private final @NonNull SlotComparator slotComparator = new SlotComparator();
	private int maxSlotsPerStage = 100;

	public @NonNull Slot addDependency(@NonNull Slot successorSlot, /*@NonNull*/ EObject predecessor) {
		assert predecessor != null;
		Slot predecessorSlot = getSlot(predecessor);
		return addDependency(successorSlot, predecessorSlot);
	}

	private @NonNull Slot addDependency(@NonNull Slot successorSlot, @NonNull Slot predecessorSlot) {
		for (EObject eContainer = predecessorSlot.element; (eContainer = eContainer.eContainer()) != null; ) {	// Debugging - a cheap variant of all predessors
			assert (eContainer != successorSlot.element) || (successorSlot.role != Slot.ROLE_CTOR);
		}
		//	if (successorSlot != predecessorSlot) {							// Operation may write and read a TemplateParameter
		if (!successorSlot.predecessors.contains(predecessorSlot)) {		// Operation can depend on same type many times
			successorSlot.predecessors.add(predecessorSlot);
			predecessorSlot.successors.add(successorSlot);
		//	assert predecessorSlot.element != successorSlot.element;
		}
		//	}
		return successorSlot;
	}

	public @NonNull Slot addTypeDependency(@NonNull TypedElement asTypedElement, @NonNull Type type) {
		Slot typeSlot = getSlot(asTypedElement, Slot.ROLE_TYPE);
		addDependency(typeSlot, type);
		return typeSlot;
	}

	public void analyze() {
		Map<@NonNull Slot, @NonNull Set<@NonNull Slot>> directSlotPredecessors = computeDirectSlotPredecessors();
		Map<@NonNull Slot, @NonNull Set<@NonNull Slot>> transitiveSlotPredecessors = computeTransitiveSlotPredecessors(directSlotPredecessors);
		stages = computeStagedSlots(transitiveSlotPredecessors);
		slots = new ArrayList<>();
		StringBuilder s = new StringBuilder();
		for (@NonNull Stage stage : stages) {
			s.append("\nStage " + stage.getName());
			for (@NonNull Slot slot : stage.getSlots()) {
				s.append("\n  " + slot);
				for (@NonNull Slot predecessor : slot.predecessors) {
					s.append("\n    requires: " + predecessor);
				}
				slots.add(slot);
			}
		}
		System.out.println(s.toString());
		Collections.sort(slots, slotComparator);
	}

	private @NonNull Map<@NonNull Slot, @NonNull Set<@NonNull Slot>> computeDirectSlotPredecessors() {
		Map<@NonNull Slot, @NonNull Set<@NonNull Slot>> slot2predecessors = new HashMap<>();
		for (Map.Entry<@NonNull EObject, @NonNull Map<@NonNull String, @NonNull Slot>> entry1 : eInstance2role2slot.entrySet()) {
		//	EObject eInstnace = entry1.getKey();
			for (Map.Entry<@NonNull String, @NonNull Slot> entry2 : entry1.getValue().entrySet()) {
				Slot slot = entry2.getValue();
				Set<@NonNull Slot> predecessors = slot2predecessors.get(slot);
				if (predecessors == null) {
					predecessors = new HashSet<>();
					slot2predecessors.put(slot, predecessors);
				}
				predecessors.addAll(slot.predecessors);
			}
		}
		return slot2predecessors;
	}

	private @NonNull List<@NonNull Stage> computeStagedSlots(@NonNull Map<@NonNull Slot, @NonNull Set<@NonNull Slot>> slot2transitivePredecessors) {
		List<@NonNull Stage> stages = new ArrayList<>();
		Map<@NonNull Slot, @NonNull Set<@NonNull Slot>> toDoSlots2residualTransitivePredecessors = new HashMap<>(slot2transitivePredecessors);
		List<@NonNull Slot> newReadySlots = new ArrayList<>();
		int stagesCount = 0;
		while (toDoSlots2residualTransitivePredecessors.size() > 0) {
			List<@NonNull Slot> oldReadySlots = newReadySlots;
			newReadySlots = new ArrayList<>();
			for (@NonNull Slot slot : toDoSlots2residualTransitivePredecessors.keySet()) {
				Set<@NonNull Slot> residualTransitivePredecessors = toDoSlots2residualTransitivePredecessors.get(slot);
				assert residualTransitivePredecessors != null;
				for (@NonNull Slot oldReadySlot : oldReadySlots) {
					residualTransitivePredecessors.remove(oldReadySlot);
				}
				if (residualTransitivePredecessors.size() <= 0) {
					newReadySlots.add(slot);
				}
			}
			for (@NonNull Slot slot : newReadySlots) {
				toDoSlots2residualTransitivePredecessors.remove(slot);
				slot.stage = stagesCount;
			}
			if (newReadySlots.size() <= 0) {
				StringBuilder s = new StringBuilder();
				s.append("STUCK");
				for (Entry<@NonNull Slot, @NonNull Set<@NonNull Slot>> entry : toDoSlots2residualTransitivePredecessors.entrySet()) {
					s.append("\n" + entry.getKey());
					for (@NonNull Slot predecessor : entry.getValue()) {
						s.append("\n    requires: " + predecessor);
					}
				}
				System.out.println(s.toString());
				throw new UnsupportedOperationException();
			}
			Collections.sort(newReadySlots, slotComparator);
			int readySlotCount = newReadySlots.size();
			if (readySlotCount <= maxSlotsPerStage) {
				stages.add(new Stage("" + stagesCount, newReadySlots));
			}
			else {
				char suffix = 'a';
				for (int i = 0; i < readySlotCount; i += maxSlotsPerStage) {
					stages.add(new Stage("" + stagesCount + suffix, newReadySlots.subList(i, Math.min(readySlotCount, i + maxSlotsPerStage))));
					suffix++;
				}
			}
			stagesCount++;
		}
		return stages;
	}

	private @NonNull Map<@NonNull Slot, @NonNull Set<@NonNull Slot>> computeTransitiveSlotPredecessors(@NonNull Map<@NonNull Slot, @NonNull Set<@NonNull Slot>> slot2directPredecessors) {
		@NonNull Map<@NonNull Slot, @NonNull Set<@NonNull Slot>> slot2transitivePredecessors = new HashMap<>();
		for (@NonNull Slot slot : slot2directPredecessors.keySet()) {
			computeTransitiveSlotPredecessors(slot, slot2directPredecessors, slot2transitivePredecessors);
		}
		return slot2transitivePredecessors;
	}

	private @NonNull Set<@NonNull Slot> computeTransitiveSlotPredecessors(@NonNull Slot slot, @NonNull Map<@NonNull Slot, Set<@NonNull Slot>> slot2directPredecessors, @NonNull Map<@NonNull Slot, @NonNull Set<@NonNull Slot>> slot2transitivePredecessors) {
		Set<@NonNull Slot> transitivePredecessors = slot2transitivePredecessors.get(slot);
		if (transitivePredecessors == null) { //|| (transitivePredecessors == CYCLIC_GUARD)) {
			slot2transitivePredecessors.put(slot, CYCLIC_GUARD);		// Infinite loop guard
			Set<@NonNull Slot> directPredecessors = slot2directPredecessors.get(slot);
			assert directPredecessors != null;
			transitivePredecessors = new HashSet<>(directPredecessors);
			for (@NonNull Slot directPredecessor : directPredecessors) {
				Set<@NonNull Slot> precedingTransitivePredecessors = computeTransitiveSlotPredecessors(directPredecessor, slot2directPredecessors, slot2transitivePredecessors);
				transitivePredecessors.addAll(precedingTransitivePredecessors);
			}
			slot2transitivePredecessors.put(slot, transitivePredecessors);
		}
		return transitivePredecessors;
	}

	public void doModel(@NonNull Model asModel) {
		Slot allOperationsSlot = getSlot(asModel, Slot.ROLE_ALL_OPERATIONS);
		Slot allPropertiesSlot = getSlot(asModel, Slot.ROLE_ALL_PROPERTIES);
		Slot typeFragmentsSlot = getSlot(asModel, Slot.ROLE_TYPE_FRAGMENTS);
		Slot fragmentOperationsSlot = getSlot(asModel, Slot.ROLE_FRAGMENT_OPERATIONS);
		Slot fragmentPropertiesSlot = getSlot(asModel, Slot.ROLE_FRAGMENT_PROPERTIES);
		Slot enumerationLiteralsSlot = getSlot(asModel, Slot.ROLE_ENUMERATION_LITERALS);
	//	addDependency(allOperationsSlot, typeFragmentsSlot);
		addDependency(allPropertiesSlot, allOperationsSlot);
		addDependency(typeFragmentsSlot, allPropertiesSlot);
		addDependency(fragmentOperationsSlot, typeFragmentsSlot);
		addDependency(fragmentPropertiesSlot, fragmentOperationsSlot);
		addDependency(enumerationLiteralsSlot, fragmentPropertiesSlot);
	}

	public void doOperations(org.eclipse.ocl.pivot.@NonNull Class asClass) {
		Iterable<@NonNull Operation> asOperations = PivotUtil.getOwnedOperations(asClass);
		Slot slot = getSlot(asClass, Slot.ROLE_OPERATIONS);
		Slot fragmentOperationsSlot = getSlot(PivotUtil.getContainingModel(asClass), Slot.ROLE_FRAGMENT_OPERATIONS);
		for (Operation asOperation : asOperations) {
			addDependency(slot, asOperation);
			addDependency(fragmentOperationsSlot, slot);
		}
	}

	public void doProperties(org.eclipse.ocl.pivot.@NonNull Class asClass) {
		Iterable<@NonNull Property> asProperties = PivotUtil.getOwnedProperties(asClass);
		Slot slot = getSlot(asClass, Slot.ROLE_PROPERTIES);
		Slot fragmentPropertiesSlot = getSlot(PivotUtil.getContainingModel(asClass), Slot.ROLE_FRAGMENT_PROPERTIES);
		for (Property asProperty : asProperties) {
			addDependency(slot, asProperty);
			addDependency(fragmentPropertiesSlot, slot);
		}
	}

	public void doSuperClasses(org.eclipse.ocl.pivot.@NonNull Class asClass) {
		Iterable<@NonNull Class> asSuperClasses = PivotUtil.getSuperClasses(asClass);
		if (!Iterables.isEmpty(asSuperClasses)) {
			Slot slot = getSlot(asClass, Slot.ROLE_SUPER_CLASSES);
			for (org.eclipse.ocl.pivot.Class superClass : asSuperClasses) {
				addDependency(slot, superClass);
			}
		}
	}

	public void doTemplateableElement(@NonNull TemplateableElement asTemplateableElement) {
		Slot slot = getSlot(asTemplateableElement);
		TemplateableElement generic = asTemplateableElement.getGeneric();
		if (generic instanceof NamedElement) {
			Slot superSlot = getSlot(generic, Slot.ROLE_SUPER_CLASSES);
			addDependency(slot, superSlot);
			for (TemplateParameter asTemplateParameter : generic.getOwnedSignature().getOwnedParameters()) {
				addDependency(slot, asTemplateParameter);
			}
		}
		for (TemplateBinding asTemplateBinding : asTemplateableElement.getOwnedBindings()) {
			for (TemplateParameterSubstitution asTemplateParameterSubstitution : asTemplateBinding.getOwnedSubstitutions()) {
				addDependency(slot, asTemplateParameterSubstitution.getActual());
			}
		}
	}

	public void doTypedElement(@NonNull TypedElement asTypedElement) {
		Type asType = asTypedElement.getType();
		if (asType != null) {
			addTypeDependency(asTypedElement, asType);
		}
	}

	public int getMaxSlotsPerStage() {
		return maxSlotsPerStage;
	}

	public @NonNull Slot getSlot(/*@NonNull*/ EObject eInstance) {
		return getSlot(eInstance, Slot.ROLE_CTOR);
	}

	public @NonNull Slot getSlot(/*@NonNull*/ EObject eInstance, @NonNull String role) {
		assert eInstance != null;
		Map<@NonNull String, @NonNull Slot> role2slot = eInstance2role2slot.get(eInstance);
		if (role2slot == null) {
			role2slot = new HashMap<>();
			eInstance2role2slot.put(eInstance, role2slot);
		}
		Slot slot = role2slot.get(role);
		if (slot == null) {
			slot = new Slot(eInstance, role);
			role2slot.put(role, slot);
			if (role != Slot.ROLE_CTOR) {
				addDependency(slot, eInstance);
			}
			else {
				EObject eContainer = eInstance.eContainer();
				if (eContainer instanceof TemplateSignature) {
					eContainer = eContainer.eContainer();
				}
				if (eContainer != null) {
					addDependency(slot, eContainer);
				}
			}
		}
		return slot;
	}

	public @NonNull List<@NonNull Slot> getSlots() {
		assert slots != null;
		return slots;
	}

	public int getStage(@NonNull Element asElement) {
		Slot slot = getSlot(asElement);
		int stage = slot.stage;
		assert stage >= 0;
		return stage;
	}

	public @NonNull List<@NonNull Stage> getStages() {
		assert stages != null;
		return stages;
	}

	public void setMaxSlotsPerStage(int maxSlotsPerStage) {
		this.maxSlotsPerStage = maxSlotsPerStage;
	}
}