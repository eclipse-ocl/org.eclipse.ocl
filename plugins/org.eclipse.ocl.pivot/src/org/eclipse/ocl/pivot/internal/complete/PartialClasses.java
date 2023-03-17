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

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.Class;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.internal.CompleteClassImpl;
import org.eclipse.ocl.pivot.util.PivotPlugin;
import org.eclipse.ocl.pivot.utilities.TracingOption;

public class PartialClasses implements ClassListeners.IClassListener
{
	public static final @NonNull TracingOption PARTIAL_CLASSES = new TracingOption(PivotPlugin.PLUGIN_ID, "partialClasses");

	public static final @NonNull TracingOption ADD_BASE_PROPERTY = new TracingOption(PivotPlugin.PLUGIN_ID, "partialClasses/addBaseProperty");
	public static final @NonNull TracingOption ADD_EXTENSION_PROPERTY = new TracingOption(PivotPlugin.PLUGIN_ID, "partialClasses/addExtensionProperty");
	public static final @NonNull TracingOption INIT_MEMBER_OPERATIONS = new TracingOption(PivotPlugin.PLUGIN_ID, "partialClasses/initMemberOperations");
	public static final @NonNull TracingOption INIT_MEMBER_PROPERTIES = new TracingOption(PivotPlugin.PLUGIN_ID, "partialClasses/initMemberProperties");

	protected final @NonNull CompleteClassImpl owner;

	protected /*@NonNull*/ CompleteInheritanceImpl completeInheritance;

	public PartialClasses(@NonNull CompleteClassImpl completeClass) {
		this.owner = completeClass;
	//	owner.addClassListener(this);
	}

	@Override
	public void didAddOperation(@NonNull Operation pivotOperation) {
		owner.resetOperations();
	}

	@Override
	public void didAddPartialClass(int index, @NonNull Class partialClass) {
	}

	@Override
	public void didAddProperty(@NonNull Property pivotProperty) {
		owner.resetProperties();
	}

	@Override
	public void didAddSuperClass(org.eclipse.ocl.pivot.@NonNull Class partialClass) {
		if (completeInheritance != null) {
			completeInheritance.uninstall();
		}
		owner.resetFragments();
	}

	@Override
	public void didRemoveOperation(@NonNull Operation pivotOperation) {
		owner.resetOperations();
	}

	@Override
	public void didRemovePartialClass(int index, @NonNull Class partialClass) {
	}

	@Override
	public void didRemoveProperty(@NonNull Property pivotProperty) {
		owner.resetProperties();
	}

	@Override
	public void didRemoveSuperClass(org.eclipse.ocl.pivot.@NonNull Class partialClass) {
		if (completeInheritance != null) {
			completeInheritance.uninstall();
		}
		owner.resetFragments();
	}

	public void dispose() {
		CompleteClassInternal completeClass = owner;
		CompletePackageInternal owningCompletePackage = completeClass.getOwningCompletePackage();
		if (owningCompletePackage != null) {
			owningCompletePackage.getPartialPackages().uninstalled(completeClass);
		}
		completeInheritance = null;
	}

	public final @NonNull CompleteInheritanceImpl getCompleteInheritance() {
		CompleteInheritanceImpl completeInheritance2 = completeInheritance;
		if (completeInheritance2 == null) {
			CompleteClassInternal completeClass = owner;
			CompletePackageInternal completePackage = completeClass.getOwningCompletePackage();
			completeInheritance2 = completePackage.getCompleteInheritance(completeClass);
			completeInheritance = completeInheritance2;
		}
		return completeInheritance2;
	}
}