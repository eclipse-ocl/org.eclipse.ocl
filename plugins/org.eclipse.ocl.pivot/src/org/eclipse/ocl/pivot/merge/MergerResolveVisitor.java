/*******************************************************************************
 * Copyright (c) 2023 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.merge;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.CollectionType;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.LambdaType;
import org.eclipse.ocl.pivot.MapType;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.StandardLibrary;
import org.eclipse.ocl.pivot.TemplateableElement;
import org.eclipse.ocl.pivot.TupleType;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.util.AbstractExtendingVisitor;
import org.eclipse.ocl.pivot.util.Visitable;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.values.IntegerValue;
import org.eclipse.ocl.pivot.values.UnlimitedNaturalValue;

/**
 * The MergerResolveVisitor is used during the second merge pass to convert the target of a reference within the partial models
 * to the corresponding target with the merged model.
 */
public class MergerResolveVisitor extends AbstractExtendingVisitor<@Nullable EObject, @NonNull Merger>
{
	protected final @NonNull StandardLibrary standardLibrary;

	public MergerResolveVisitor(@NonNull Merger context, @NonNull StandardLibrary standardLibrary) {
		super(context);
		this.standardLibrary = standardLibrary;
	}

	@Override
	public @Nullable EObject visiting(@NonNull Visitable visitable) {
		System.out.println("Unsupported " + visitable.eClass().getName() + " for " + getClass().getSimpleName());
		return null;
	}

	@Override
	public @Nullable EObject visitCollectionType(@NonNull CollectionType asCollectionType) {
		TemplateableElement unspecializedElement = asCollectionType.getUnspecializedElement();
		if (unspecializedElement == null) {
			return super.visitCollectionType(asCollectionType);
		}
		CollectionType mergedCollectionType = (CollectionType)unspecializedElement.accept(this);
		Type mergedElementType = (Type)asCollectionType.getElementType().accept(this);
		boolean isNullFree = asCollectionType.isIsNullFree();
		IntegerValue lowerValue = asCollectionType.getLowerValue();
		UnlimitedNaturalValue upperValue = asCollectionType.getUpperValue();
		return standardLibrary.getCollectionType(mergedCollectionType, mergedElementType, isNullFree, lowerValue, upperValue);
	}

	@Override
	public @Nullable EObject visitElement(@NonNull Element partialElement) {
		return context.getMergedElement(partialElement);
	}

	@Override
	public @Nullable EObject visitLambdaType(@NonNull LambdaType asLambdaType) {
		TemplateableElement unspecializedElement = asLambdaType.getUnspecializedElement();
		if (unspecializedElement == null) {
			return super.visitLambdaType(asLambdaType);
		}
	//	throw new UnsupportedOperationException();
	//	return super.visitLambdaType(object);
		return null;
	}

	@Override
	public @Nullable EObject visitMapType(@NonNull MapType asMapType) {
		TemplateableElement unspecializedElement = asMapType.getUnspecializedElement();
		if (unspecializedElement == null) {
			return super.visitMapType(asMapType);
		}
		Type mergedKeyType = (Type)asMapType.getKeyType().accept(this);
		Type mergedValueType = (Type)asMapType.getValueType().accept(this);
		boolean isKeysAreNullFree = asMapType.isKeysAreNullFree();
		boolean isValuesAreNullFree = asMapType.isValuesAreNullFree();
		return standardLibrary.getMapType(mergedKeyType, isKeysAreNullFree, mergedValueType, isValuesAreNullFree);
	}

	@Override
	public @Nullable EObject visitTupleType(@NonNull TupleType asTupleType) {
		Map<@NonNull String, @NonNull Type> tupleParts = new HashMap<>();
		for (@NonNull Property asProperty : PivotUtil.getOwnedProperties(asTupleType)) {
			Type mergedType = (Type)asProperty.getType().accept(this);
			assert mergedType != null;
			tupleParts.put(NameUtil.getName(asProperty), mergedType);
		}
		return standardLibrary.getTupleType(tupleParts);
	}
}
