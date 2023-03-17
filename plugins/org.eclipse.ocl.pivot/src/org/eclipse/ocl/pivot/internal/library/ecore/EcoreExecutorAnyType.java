/*******************************************************************************
 * Copyright (c) 2021 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.internal.library.ecore;

import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.AnyType;
import org.eclipse.ocl.pivot.ids.BuiltInTypeId;
import org.eclipse.ocl.pivot.internal.library.executor.ExecutorType;

/**
 * @since 1.18
 */
public class EcoreExecutorAnyType extends ExecutorType implements AnyType
// Initialization of OCLstdlibTables gives a NoSuchFieldError if this is a nested class.
{
	public EcoreExecutorAnyType(@NonNull EClassifier eClassifier, org.eclipse.ocl.pivot.@NonNull Package asPackage, @NonNull BuiltInTypeId typeId, int flags) {
		super(eClassifier, asPackage, typeId, flags);
	}
}
