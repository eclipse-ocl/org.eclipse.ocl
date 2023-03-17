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
import org.eclipse.ocl.pivot.SequenceType;
import org.eclipse.ocl.pivot.TemplateParameter;
import org.eclipse.ocl.pivot.ids.BuiltInTypeId;

/**
 * @since 1.18
 */
public class EcoreExecutorSequenceType extends EcoreExecutorCollectionType implements SequenceType
//Initialization of OCLstdlibTables gives a NoSuchFieldError if EcoreExecutorAnyType is a nested class.
{
	public EcoreExecutorSequenceType(@NonNull EClassifier eClassifier, org.eclipse.ocl.pivot.@NonNull Package asPackage, @NonNull BuiltInTypeId typeId, int flags, @NonNull TemplateParameter typeParameter) {
		super(eClassifier, asPackage, typeId, flags, typeParameter);
	}
}
