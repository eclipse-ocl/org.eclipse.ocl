/*******************************************************************************
 * Copyright (c) 2010, 2022 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.library.collection;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.CallExp;
import org.eclipse.ocl.pivot.CollectionType;
import org.eclipse.ocl.pivot.OCLExpression;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.StandardLibrary;
import org.eclipse.ocl.pivot.TemplateParameter;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.ids.CollectionTypeId;
import org.eclipse.ocl.pivot.internal.manager.TemplateParameterSubstitutionVisitor;
import org.eclipse.ocl.pivot.library.AbstractSimpleUnaryOperation;
import org.eclipse.ocl.pivot.utilities.EnvironmentFactory;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.utilities.ValueUtil;
import org.eclipse.ocl.pivot.values.CollectionTypeArguments;
import org.eclipse.ocl.pivot.values.CollectionValue;
import org.eclipse.ocl.pivot.values.IntegerValue;
import org.eclipse.ocl.pivot.values.UnlimitedNaturalValue;

/**
 * CollectionFlattenOperation realises the Collection::flatten() library operation.
 */
public class CollectionFlattenOperation extends AbstractSimpleUnaryOperation
{
	public static final @NonNull CollectionFlattenOperation INSTANCE = new CollectionFlattenOperation();

	@Override
	public @NonNull CollectionValue evaluate(@Nullable Object argument) {
		CollectionValue collectionValue = asCollectionValue(argument);
		return collectionValue.flatten();
	}

	/**
	 *	Special case processing for return collection types based on the source collection types and multiplicities.
	 *
	 * @since 1.18
	 */
	@Override
	public @Nullable Type resolveReturnType(@NonNull EnvironmentFactory environmentFactory, @NonNull CallExp callExp, @Nullable Type returnType) {
		if (returnType instanceof CollectionType) {
			OCLExpression ownedSource = callExp.getOwnedSource();
			if (ownedSource != null) {
				Type sourceType = ownedSource.getType();
				CollectionType returnCollectionType = (CollectionType)returnType;
				if (sourceType instanceof CollectionType) {
					CollectionType sourceCollectionType = (CollectionType)sourceType;
					boolean isNullFree = sourceCollectionType.isIsNullFree();
					IntegerValue lowerValue = sourceCollectionType.getLowerValue();
					UnlimitedNaturalValue upperValue = sourceCollectionType.getUpperValue();
					Type elementType = PivotUtil.getElementType(sourceCollectionType);
					while (elementType instanceof CollectionType) {
						CollectionType nestedCollectionType = (CollectionType)elementType;
						boolean nestedIisNullFree = nestedCollectionType.isIsNullFree();
						if (!nestedIisNullFree) {
							isNullFree = false;
						}
						IntegerValue nestedLowerValue = nestedCollectionType.getLowerValue();
						if (nestedLowerValue.signum() > 0) {
							lowerValue = lowerValue.addInteger(nestedLowerValue.subtractInteger(ValueUtil.ONE_VALUE));
						}
						UnlimitedNaturalValue nestedUpperValue = nestedCollectionType.getUpperValue();
						if (nestedUpperValue.isUnlimited() || upperValue.isUnlimited()) {
							upperValue = ValueUtil.UNLIMITED_VALUE;
						}
						else {
							upperValue = upperValue.asIntegerValue().addInteger(nestedUpperValue.asIntegerValue()).asUnlimitedNaturalValue();
						}
						elementType = PivotUtil.getElementType(nestedCollectionType);
					}
					StandardLibrary standardLibrary = environmentFactory.getStandardLibrary();
					CollectionTypeId genericCollectionTypeId = ((CollectionType)returnCollectionType.getUnspecializedElement()).getTypeId();// IdManager.getCollectionTypeId(returnCollectionType.isOrdered(), returnCollectionType.isUnique());
					CollectionTypeArguments typeArguments = new CollectionTypeArguments(genericCollectionTypeId, elementType, isNullFree, lowerValue, upperValue);
					returnType = standardLibrary.getCollectionType(typeArguments);
				}
			}
		}
		return returnType;
	}

	/**
	 *	Special case processing for flatten() that flattens nested types.
	 *
	 * @since 1.18
	 */
	@Override
	public void resolveUnmodeledTemplateParameterSubstitutions(@NonNull TemplateParameterSubstitutionVisitor templateParameterSubstitutions, @NonNull CallExp callExp) {
		Type elementType = PivotUtil.getType(PivotUtil.getOwnedSource(callExp));
		while (elementType instanceof CollectionType) {
			elementType = PivotUtil.getElementType((CollectionType)elementType);
		}
		Operation flattenOperation = PivotUtil.getReferredOperation(callExp);
		assert flattenOperation.getImplementation() == INSTANCE;
		TemplateParameter templateParameter = flattenOperation.getOwnedSignature().getOwnedParameters().get(0);
		assert templateParameterSubstitutions.getTemplateParameterization().get(1) == templateParameter;
		templateParameterSubstitutions.put(templateParameter, elementType);
	}
}
