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
import org.eclipse.ocl.pivot.MapType;
import org.eclipse.ocl.pivot.PivotFactory;
import org.eclipse.ocl.pivot.PivotPackage;
import org.eclipse.ocl.pivot.PrimitiveType;
import org.eclipse.ocl.pivot.internal.CompletePackageImpl;
import org.eclipse.ocl.pivot.util.PivotPlugin;
import org.eclipse.ocl.pivot.utilities.PivotConstants;
import org.eclipse.ocl.pivot.utilities.TracingOption;

public class CompleteClasses extends EObjectContainmentWithInverseEList<CompleteClass>
{
	public static final @NonNull TracingOption COMPLETE_CLASSES = new TracingOption(PivotPlugin.PLUGIN_ID, "completeClasses");
	//	static { COMPLETE_CLASSES.setState(true); }
	private static final long serialVersionUID = 1L;

	protected @Nullable Map<String, CompleteClassInternal> name2completeClass = null;

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
		didAdd((CompleteClassInternal) completeClass);			// XXX uses preceding overload
	}

	public void didAdd(@NonNull CompleteClassInternal completeClass) {
		Map<String, CompleteClassInternal> name2completeClass2 = name2completeClass;
		if (name2completeClass2 != null) {
			String name = completeClass.getName();
			if (name != null) {
				if ("Real".equals(name)) {
					getClass();			// XXX
				}
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
		Map<String, CompleteClassInternal> name2completeClass2 = name2completeClass;
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
		if (partialClass.getGeneric() == null) {
			getCompleteModel().didRemoveClass(partialClass);
		}
	}

	public void didRemovePackage(org.eclipse.ocl.pivot.@NonNull Package partialPackage) {
		Map<String, CompleteClassInternal> name2completeClass2 = name2completeClass;
		if (name2completeClass2 != null) {
			for (org.eclipse.ocl.pivot.Class partialClass : partialPackage.getOwnedClasses()) {
				if (partialClass != null) {
					didRemoveClass(partialClass);
				}
			}
		}
	}

	protected void doRefreshPartialClass(org.eclipse.ocl.pivot.@NonNull Class partialClass) {
		Map<String, CompleteClassInternal> name2completeClass2 = name2completeClass;
		assert name2completeClass2 != null;
		CompleteModelInternal completeModel = getCompleteModel();
		String name = partialClass.getName();
		if (name != null) {
			CompleteClassInternal completeClass = null;
			if (partialClass instanceof PrimitiveType) {
				CompletePackageInternal primitiveCompletePackage = completeModel.getPrimitiveCompletePackage();
				completeClass = primitiveCompletePackage.getCompleteClass(partialClass);
			}
			else if ((partialClass instanceof MapType) && (partialClass.getGeneric() != null)) {
				CompletePackageInternal orphanCompletePackage = completeModel.getOrphanCompletePackage();
				completeClass = orphanCompletePackage.getCompleteClass(partialClass);
			}
			else if (PivotConstants.METAMODEL_NAME.equals(getCompletePackage().getURI())) {
				CompletePackageInternal primitiveCompletePackage = completeModel.getPrimitiveCompletePackage();
				completeClass = primitiveCompletePackage.getOwnedCompleteClass(name);
			}
			if (completeClass == null) {
				completeClass = name2completeClass2.get(name);
				if (completeClass == null) {
					completeClass = (CompleteClassInternal) PivotFactory.eINSTANCE.createCompleteClass();
					completeClass.setName(name);
					add(completeClass);
				}
			}
			completeClass.addClass(partialClass);
		}
	}

	protected @NonNull Map<String, CompleteClassInternal> doRefreshPartialClasses() {
		Map<String, CompleteClassInternal> name2completeClass2 = name2completeClass;
		if (name2completeClass2 == null) {
			name2completeClass2 = name2completeClass = new HashMap<String, CompleteClassInternal>();
		}
		for (org.eclipse.ocl.pivot.Package partialPackage : getCompletePackage().getPartialPackages()) {
			if (partialPackage != null) {
				doRefreshPartialClasses(partialPackage);
			}
		}
		return name2completeClass2;
	}

	protected void doRefreshPartialClasses(org.eclipse.ocl.pivot.@NonNull Package partialPackage) {
		for (org.eclipse.ocl.pivot.Class partialClass : partialPackage.getOwnedClasses()) {
			if (partialClass != null) {
				doRefreshPartialClass(partialClass);
			}
		}
	}

	public @NonNull CompleteModelInternal getCompleteModel() {
		return getCompletePackage().getCompleteModel();
	}

	@SuppressWarnings("null")
	public @NonNull CompletePackageInternal getCompletePackage() {
		return (@NonNull CompletePackageInternal) owner;
	}

	public @Nullable CompleteClassInternal getOwnedCompleteClass(String name) {
		Map<String, CompleteClassInternal> name2completeClass2 = name2completeClass;
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