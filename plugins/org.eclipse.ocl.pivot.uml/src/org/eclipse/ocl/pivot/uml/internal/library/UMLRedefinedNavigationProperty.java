/*******************************************************************************
 * Copyright (c) 2016 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.uml.internal.library;

import java.util.Collections;
import java.util.List;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.CollectionType;
import org.eclipse.ocl.pivot.CompleteClass;
import org.eclipse.ocl.pivot.CompleteModel;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.evaluation.Executor;
import org.eclipse.ocl.pivot.ids.CollectionTypeId;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.library.ExplicitNavigationProperty;
import org.eclipse.ocl.pivot.uml.internal.messages.UMLPivotMessages;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.utilities.ValueUtil;
import org.eclipse.ocl.pivot.values.CollectionValue;
import org.eclipse.ocl.pivot.values.InvalidValueException;

/**
 * The static instance of UMLRedefinedNavigationProperty supports evaluation of
 * a property call that navigates a relationship that uses a UML redefinition.
 * @since 1.3
 */
public class UMLRedefinedNavigationProperty extends ExplicitNavigationProperty
{
	protected final @NonNull Property baseProperty;
	
	public UMLRedefinedNavigationProperty(@NonNull CompleteModel completeModel, @NonNull Property property) {
		super(property);
		CompleteClass baseCompleteClass = completeModel.getCompleteClass(PivotUtil.getOwningClass(property));
		Property baseProperty = property;
		for (@NonNull Property aProperty : PivotUtil.getRedefinedProperties(property)) {
			CompleteClass aCompleteClass = completeModel.getCompleteClass(PivotUtil.getOwningClass(aProperty));
			if (baseCompleteClass.conformsTo(aCompleteClass)) {
				baseCompleteClass = aCompleteClass;
				baseProperty = aProperty;
			}
		}
		this.baseProperty = baseProperty;
	}
	
	@Override
	public @Nullable Object evaluate(@NonNull Executor executor, @NonNull TypeId returnTypeId, @Nullable Object sourceValue) {
		Object unredefinedResult = super.evaluate(executor, returnTypeId, sourceValue);
		Type unredefinedType = baseProperty.getType();
		Type redefinedType = property.getType();
		if (unredefinedType instanceof CollectionType) {
			if (!(redefinedType instanceof CollectionType)) {
				CollectionValue asCollectionValue = ValueUtil.asCollectionValue(unredefinedResult);
				int intSize = asCollectionValue.intSize();
				switch (intSize) {
					case 0: {
						return null;
					}
					case 1: {
						return asCollectionValue.getElements().iterator().next();
					}
					default: {
						throw new InvalidValueException(UMLPivotMessages.TooManyValuesForRedefinedProperty, property);
					}
				}
			}
			else {
				return unredefinedResult;
			}
		}
		else {
			if (redefinedType instanceof CollectionType) {
				CollectionType collectionType = (CollectionType) redefinedType;
				CollectionTypeId typeId = collectionType.getTypeId();
				List<Object> valueAsList = Collections.singletonList(unredefinedResult);
				return executor.getIdResolver().createCollectionOfAll(typeId, valueAsList);
			}
			else {
				return unredefinedResult;
			}
		}
	}
}