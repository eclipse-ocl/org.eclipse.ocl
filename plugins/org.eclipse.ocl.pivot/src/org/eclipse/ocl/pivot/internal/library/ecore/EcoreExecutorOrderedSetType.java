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
import org.eclipse.ocl.pivot.OrderedSetType;
import org.eclipse.ocl.pivot.TemplateParameter;
import org.eclipse.ocl.pivot.ids.BuiltInTypeId;
import org.eclipse.ocl.pivot.internal.library.executor.ExecutorPackage;

/**
 * @since 1.18
 */
public class EcoreExecutorOrderedSetType extends EcoreExecutorCollectionType implements OrderedSetType
//Initialization of OCLstdlibTables gives a NoSuchFieldError if EcoreExecutorAnyType is a nested class.
{
//	public EcoreExecutorOrderedSetType(@NonNull BuiltInTypeId typeId, @NonNull ExecutorPackage evaluationPackage, int flags, @NonNull TemplateParameter typeParameter) {
//		super(typeId, evaluationPackage, flags, typeParameter);
//	}

	public EcoreExecutorOrderedSetType(@NonNull EClassifier eClassifier, @NonNull ExecutorPackage evaluationPackage, @NonNull BuiltInTypeId typeId, int flags, @NonNull TemplateParameter typeParameter) {
		super(eClassifier, evaluationPackage, typeId, flags, typeParameter);
	}
}
