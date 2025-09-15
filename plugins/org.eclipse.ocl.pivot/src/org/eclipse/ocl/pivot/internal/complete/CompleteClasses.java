/*******************************************************************************
 * Copyright (c) 2014, 2022 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.internal.complete;

import java.util.HashMap;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Map;

import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.CompleteClass;
import org.eclipse.ocl.pivot.CompletePackage;
import org.eclipse.ocl.pivot.PivotPackage;
import org.eclipse.ocl.pivot.PrimitiveCompletePackage;
import org.eclipse.ocl.pivot.PrimitiveType;
import org.eclipse.ocl.pivot.TupleType;
import org.eclipse.ocl.pivot.internal.CompletePackageImpl;
import org.eclipse.ocl.pivot.util.PivotPlugin;
import org.eclipse.ocl.pivot.utilities.PivotConstants;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.utilities.TracingOption;

public class CompleteClasses extends EObjectContainmentWithInverseEList<CompleteClass>
{
	public static final @NonNull TracingOption COMPLETE_CLASSES = new TracingOption(PivotPlugin.PLUGIN_ID, "complete/classes");
	//	static { COMPLETE_CLASSES.setState(true); }
	private static final long serialVersionUID = 1L;

	protected @Nullable Map<@NonNull String, @NonNull CompleteClassInternal> name2completeClass = null;

	public CompleteClasses(@NonNull CompletePackageImpl owner) {
		super(CompleteClass.class, owner, PivotPackage.Literals.COMPLETE_PACKAGE__OWNED_COMPLETE_CLASSES.getFeatureID(), PivotPackage.Literals.COMPLETE_CLASS__OWNING_COMPLETE_PACKAGE.getFeatureID());
		if (COMPLETE_CLASSES.isActive()) {
			COMPLETE_CLASSES.println("Create " + this);
		}
	}

	@Override
	protected void didAdd(int index, CompleteClass completeClass) {
		assert completeClass != null;
		super.didAdd(index, completeClass);
		didAdd((CompleteClassInternal) completeClass);
	}

	public void didAdd(@NonNull CompleteClassInternal completeClass) {
		Map<@NonNull String, @NonNull CompleteClassInternal> name2completeClass2 = name2completeClass;
		if (name2completeClass2 != null) {
			String name = completeClass.getName();
			if (name != null) {
				CompleteClass oldCompleteClass = name2completeClass2.put(name, completeClass);
				assert oldCompleteClass == null;
			}
		}
	}

	public void didAddClass(org.eclipse.ocl.pivot.@NonNull Class partialClass) {
		if (name2completeClass != null) {
			CompleteClassInternal completeClass = name2completeClass.get(partialClass.getName());
			if (completeClass == null) {
				doRefreshPartialClass(partialClass);
			}
			else {
				completeClass.addClass(partialClass);
			}
		}
	}

	public void didAddPackage(org.eclipse.ocl.pivot.@NonNull Package partialPackage) {
		if (name2completeClass != null) {
			doRefreshPartialClasses(partialPackage);
		}
	}

	@Override
	protected void didRemove(int index, CompleteClass completeClass) {
		assert completeClass != null;
		didRemove(completeClass);
		super.didRemove(index, completeClass);
	}

	protected void didRemove(@NonNull CompleteClass completeClass) {
		Map<@NonNull String, @NonNull CompleteClassInternal> name2completeClass2 = name2completeClass;
		if (name2completeClass2 != null) {
			String name = completeClass.getName();
			if (name != null) {
				CompleteClassInternal oldCompleteClass = name2completeClass2.remove(name);
				assert oldCompleteClass == completeClass;
			}
		}
	}

	public void didRemoveClass(org.eclipse.ocl.pivot.@NonNull Class partialClass) {
		if (name2completeClass != null) {
			CompleteClassInternal completeClass = name2completeClass.get(partialClass.getName());
			if ((completeClass != null) && completeClass.didRemoveClass(partialClass)) {
				remove(completeClass);
				completeClass.dispose();
			}
		}
		if (partialClass.getUnspecializedElement() == null) {
			getCompleteModel().didRemoveClass(partialClass);
		}
	}

	public void didRemovePackage(org.eclipse.ocl.pivot.@NonNull Package partialPackage) {
		Map<@NonNull String, @NonNull CompleteClassInternal> name2completeClass2 = name2completeClass;
		if (name2completeClass2 != null) {
			for (org.eclipse.ocl.pivot.Class partialClass : partialPackage.getOwnedClasses()) {
				if (partialClass != null) {
					didRemoveClass(partialClass);
				}
			}
		}
	}

	protected void doRefreshPartialClass(org.eclipse.ocl.pivot.@NonNull Class partialClass) {
		if (partialClass instanceof TupleType) {
			getClass();
		}
		Map<@NonNull String, @NonNull CompleteClassInternal> name2completeClass2 = name2completeClass;
		assert name2completeClass2 != null;
		String name = partialClass.getName();
		if (name == null) {
			return;				// XXX ignore nameless classes
		}
		CompleteModelInternal completeModel = getCompleteModel();
		//
		//	If a OCLstdlib / Complete OCL has explicitly declared a PrimitveType.
		//
		if (partialClass instanceof PrimitiveType) {											// Primary OCLstdlib declaration
			CompletePackage completePackage = completeModel.getPrimitiveCompletePackage();
			CompleteClass completeClass = completePackage.getCompleteClass(partialClass);
			assert completeClass.getPartialClasses().contains(partialClass);			// XXX redundant
			return;
		}
		//
		//	Else if a Complete OCL has implicitly declared a PrimitiveType by overlaying a known PrimitiveType.
		//
		CompletePackage completePackage = getCompletePackage();
		if (PivotConstants.METAMODEL_ID == completePackage.getCompletePackageId()) {			// Secondary Complete OCL overlay
			PrimitiveCompletePackage primitiveCompletePackage = completeModel.getPrimitiveCompletePackage();
			CompleteClass completeClass = primitiveCompletePackage.getOwnedCompleteClass(name);
			if (completeClass != null) {														// If name is a primitive
				((CompleteClassInternal)completeClass).addClass(partialClass);
				return;
			}
		}
		//
		//	Else a regular partial Class contribution to a CompleteClass.
		//
		CompleteClass completeClass = completePackage.getCompleteClass(partialClass);
		assert completeClass.getPartialClasses().contains(partialClass);		// XXX redundant
	}

	protected @NonNull Map<@NonNull String, @NonNull CompleteClassInternal> doRefreshPartialClasses() {
		Map<@NonNull String, @NonNull CompleteClassInternal> name2completeClass2 = name2completeClass;
		if (name2completeClass2 == null) {
			name2completeClass2 = name2completeClass = new HashMap<>();
		}
		CompletePackage completePackage = getCompletePackage();
		for (org.eclipse.ocl.pivot.@NonNull Package partialPackage : PivotUtil.getPartialPackages(completePackage)) {
			doRefreshPartialClasses(partialPackage);
		}
		return name2completeClass2;
	}

	protected void doRefreshPartialClasses(org.eclipse.ocl.pivot.@NonNull Package partialPackage) {
		for (org.eclipse.ocl.pivot.@NonNull Class partialClass : PivotUtil.getOwnedClasses(partialPackage)) {
			doRefreshPartialClass(partialClass);
		}
	}

	public @NonNull CompleteModelInternal getCompleteModel() {
		return (CompleteModelInternal) getCompletePackage().getCompleteModel();
	}

	/**
	 * @since 7.0
	 */
	public @NonNull CompletePackage getCompletePackage() {
		assert owner != null;
		return (CompletePackage)owner;
	}

	public @Nullable CompleteClassInternal getOwnedCompleteClass(String name) {
		Map<@NonNull String, @NonNull CompleteClassInternal> name2completeClass2 = name2completeClass;
		if (name2completeClass2 == null) {
			name2completeClass2 = doRefreshPartialClasses();
		}
		return name2completeClass2.get(name);
	}

	@Override
	public @NonNull Iterator<CompleteClass> iterator() {
		if (name2completeClass == null) {
			doRefreshPartialClasses();
		}
		return super.iterator();
	}

	@Override
	public @NonNull ListIterator<CompleteClass> listIterator() {
		if (name2completeClass == null) {
			doRefreshPartialClasses();
		}
		return super.listIterator();
	}

	@Override
	public @NonNull ListIterator<CompleteClass> listIterator(int index) {
		if (name2completeClass == null) {
			doRefreshPartialClasses();
		}
		return super.listIterator(index);
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + ": " + owner.toString();
	}
}