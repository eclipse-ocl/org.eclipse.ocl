/*******************************************************************************
 * Copyright (c) 2012, 2019 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.internal.ids;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.ids.OperationId;
import org.eclipse.ocl.pivot.ids.ParametersId;
import org.eclipse.ocl.pivot.ids.PropertyId;
import org.eclipse.ocl.pivot.ids.TemplateableId;
import org.eclipse.ocl.pivot.ids.TemplateableTypeId;

public abstract class GeneralizedTypeIdImpl<T extends TemplateableId> extends AbstractGeneralizedIdImpl<T> implements TemplateableTypeId
{
	/**
	 * Map from the operation hashCode to the operationIds with the same hash.
	 */
	private @Nullable OperationIdsMap memberOperations = null;

	/**
	 * Map from the property name to the propertyIds.
	 */
	private @Nullable PropertyIdsMap memberProperties = null;

	protected GeneralizedTypeIdImpl(int hashCode, int templateParameters, @NonNull String name) {
		super(hashCode, templateParameters, name);
	}

	@Override
	public @NonNull OperationId getOperationId(int templateParameters, @NonNull String name, @NonNull ParametersId parametersId) {
		//		System.out.println("getOperationId " + name + " " + ClassUtil.debugFullName(parametersId) + " with " + ClassUtil.debugFullName(templateParameters));
		OperationIdsMap memberOperations2 = memberOperations;
		if (memberOperations2 == null) {
			synchronized (this) {
				memberOperations2 = memberOperations;
				if (memberOperations2 == null) {
					memberOperations = memberOperations2 = new OperationIdsMap(this);
				}
			}
		}
		return memberOperations2.getId(templateParameters, name, parametersId);
	}

	@Override
	public @NonNull PropertyId getPropertyId(@NonNull String name) {
		PropertyIdsMap memberProperties2 = memberProperties;
		if (memberProperties2 == null) {
			synchronized (this) {
				memberProperties2 = memberProperties;
				if (memberProperties2 == null) {
					memberProperties = memberProperties2 = new PropertyIdsMap(this);
				}
			}
		}
		return memberProperties2.getId(name);
	}

	/**
	 * @since 1.7
	 */
	@Override
	public boolean isTemplated() {
		return true;
	}
}