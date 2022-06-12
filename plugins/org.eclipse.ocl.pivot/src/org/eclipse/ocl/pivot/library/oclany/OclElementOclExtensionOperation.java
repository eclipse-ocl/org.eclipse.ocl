/*******************************************************************************
 * Copyright (c) 2010, 2018 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.library.oclany;

import java.util.List;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.ElementExtension;
import org.eclipse.ocl.pivot.Stereotype;
import org.eclipse.ocl.pivot.evaluation.Executor;
import org.eclipse.ocl.pivot.ids.CollectionTypeId;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.library.ExtensionProperty;
import org.eclipse.ocl.pivot.library.AbstractBinaryOperation;
import org.eclipse.ocl.pivot.utilities.ValueUtil;
import org.eclipse.ocl.pivot.values.SetValue;

/**
 * OclElementOclExtensionOperation realises the OclElement::oclExtension() library operation.
 *
 * @since 1.17
 */
public class OclElementOclExtensionOperation extends AbstractBinaryOperation
{
	public static final @NonNull OclElementOclExtensionOperation INSTANCE = new OclElementOclExtensionOperation();

	@Override
	public @NonNull SetValue evaluate(@NonNull Executor executor, @NonNull TypeId returnTypeId, @Nullable Object sourceValue, @Nullable Object argumentValue) {
		assert sourceValue != null;
		Stereotype stereotype = ValueUtil.asStereotype(argumentValue);
		List<@NonNull ElementExtension> selectedExtensions = ExtensionProperty.selectExtensions(executor, stereotype, sourceValue);
		CollectionTypeId collectionTypeId = (CollectionTypeId)returnTypeId;
		return selectedExtensions != null ? ValueUtil.createSetValue(collectionTypeId, selectedExtensions) : ValueUtil.createSetOfEach(collectionTypeId);
	}
}
