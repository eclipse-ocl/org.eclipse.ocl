/**
 * Copyright (c) 2014, 2018 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 */
package org.eclipse.ocl.pivot.internal.complete;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.CompleteClass;
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal;
import org.eclipse.ocl.pivot.utilities.MetamodelManager;

public interface CompleteClassInternal extends CompleteClass
{
	void addClass(org.eclipse.ocl.pivot.@NonNull Class partialClass);
	void didAddClass(org.eclipse.ocl.pivot.@NonNull Class partialClass);
	boolean didRemoveClass(org.eclipse.ocl.pivot.@NonNull Class partialClass);
	void dispose();
	void uninstall();
	@NonNull CompleteModelInternal getCompleteModel();
	@NonNull EnvironmentFactoryInternal getEnvironmentFactory();
	/**
	 * @since 7.0
	 */
	@NonNull MetamodelManager getMetamodelManager();
	@Override
	CompletePackageInternal getOwningCompletePackage();
	@NonNull Iterable<org.eclipse.ocl.pivot.@NonNull Class> getProperSuperClasses();
} // CompleteClass
