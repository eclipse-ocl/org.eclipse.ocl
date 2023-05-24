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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.CollectionType;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.LambdaType;
import org.eclipse.ocl.pivot.MapType;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.TemplateableElement;
import org.eclipse.ocl.pivot.TupleType;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.internal.utilities.Orphanage;
import org.eclipse.ocl.pivot.internal.utilities.PivotUtilInternal;
import org.eclipse.ocl.pivot.util.AbstractExtendingVisitor;
import org.eclipse.ocl.pivot.util.Visitable;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.utilities.TuplePart;
import org.eclipse.ocl.pivot.values.IntegerValue;
import org.eclipse.ocl.pivot.values.UnlimitedNaturalValue;

/**
 * The MergerResolveVisitor is used during the second merge pass to convert the target of a reference within the partial models
 * to the corresponding target with the merged model.
 */
public class MergerResolveVisitor extends AbstractExtendingVisitor<@NonNull Element, @NonNull Merger>
{
	protected final @NonNull Orphanage orphanage;

	public MergerResolveVisitor(@NonNull Merger context, @NonNull Orphanage orphanage) {
		super(context);
		this.orphanage = orphanage;
	}

	@Override
	public @NonNull Element visiting(@NonNull Visitable visitable) {
		throw new UnsupportedOperationException("Unsupported " + visitable.eClass().getName() + " for " + getClass().getSimpleName());
	//	System.out.println("Unsupported " + visitable.eClass().getName() + " for " + getClass().getSimpleName());
	//	return null;
	}

	@Override
	public @NonNull CollectionType visitCollectionType(@NonNull CollectionType asCollectionType) {
		String s = asCollectionType.toString();
	//	if ("Collection(OclElement)".equals(s) || "Collection(ocl::OclElement)".equals(s)) {
	//		getClass();		// XXX
	//	}
	//	assert orphanage.assertConsistent();
		TemplateableElement unspecializedElement = asCollectionType.getUnspecializedElement();
		if (unspecializedElement == null) {
			return (CollectionType)super.visitCollectionType(asCollectionType);
		}
		CollectionType mergedCollectionType = (CollectionType)unspecializedElement.accept(this);
		Type mergedElementType = (Type)asCollectionType.getElementType().accept(this);
		if (mergedElementType == null) {		// XXX
			mergedElementType = (Type)asCollectionType.getElementType().accept(this);		// XXX
			return null;
		}
		boolean isNullFree = asCollectionType.isIsNullFree();
		IntegerValue lowerValue = asCollectionType.getLowerValue();
		UnlimitedNaturalValue upperValue = asCollectionType.getUpperValue();
		CollectionType collectionType = orphanage.getCollectionType(mergedCollectionType, mergedElementType, isNullFree, lowerValue, upperValue);
	//	if (mergedElementType.toString().contains("OclElement")) {
	//		System.out.println("visitCollectionType " + NameUtil.debugSimpleName(collectionType) + " : " + collectionType);
	//		getClass();		// XXX
	//	}
	//	assert orphanage.assertConsistent();
		return collectionType;
	}

	@Override
	public @NonNull Element visitElement(@NonNull Element partialElement) {
		Element mergedElement = context.getMergedElement(partialElement);
		return ClassUtil.nonNullState(mergedElement);
	}

	@Override
	public @NonNull LambdaType visitLambdaType(@NonNull LambdaType asLambdaType) {
	//	TemplateableElement unspecializedElement = asLambdaType.getUnspecializedElement();
	//	if (unspecializedElement == null) {
	//		return (LambdaType) super.visitLambdaType(asLambdaType);
	//	}
	//	throw new UnsupportedOperationException();
	//	return super.visitLambdaType(object);
	//	return null;
		Type mergedContextType = (Type)asLambdaType.getContextType().accept(this);
		@NonNull List<@NonNull Type> mergedParameterTypes	= new ArrayList<>();
		for (@NonNull Type parameterType : PivotUtil.getParameterType(asLambdaType)) {
			mergedParameterTypes.add((Type)parameterType.accept(this));
		}
		Type mergedResultType = (Type)asLambdaType.getResultType().accept(this);
		return orphanage.getLambdaType(orphanage.getStandardLibrary().getOclLambdaType(), mergedContextType, mergedParameterTypes, mergedResultType);
	}

	@Override
	public @NonNull MapType visitMapType(@NonNull MapType asMapType) {
		TemplateableElement unspecializedElement = asMapType.getUnspecializedElement();
		if (unspecializedElement == null) {
			return (MapType)super.visitMapType(asMapType);
		}
		MapType mergedMapType = (MapType)unspecializedElement.accept(this);
		Type mergedKeyType = (Type)asMapType.getKeyType().accept(this);
		Type mergedValueType = (Type)asMapType.getValueType().accept(this);
		boolean isKeysAreNullFree = asMapType.isKeysAreNullFree();
		boolean isValuesAreNullFree = asMapType.isValuesAreNullFree();
		return orphanage.getMapType(mergedMapType, mergedKeyType, isKeysAreNullFree, mergedValueType, isValuesAreNullFree);
	}

	@Override
	public @NonNull Property visitProperty(@NonNull Property asProperty) {
		Property mergedProperty = (Property)context.getMergedElement(asProperty);
		if (mergedProperty == null) {
			if ("pivotas::Behavior::State".equals(asProperty.toString())) {
				getClass();		// XXX
			}
			Property asOpposite = asProperty.getOpposite();
			Property mergedOpposite = (Property)context.getMergedElement(asOpposite);
		/*	StringBuilder s = new StringBuilder();
			s.append("visitProperty " + NameUtil.debugSimpleName(mergedOpposite));
			s.append("\n\t" + NameUtil.debugSimpleName(asProperty) + " : " + asProperty);
			s.append("\n\t" + NameUtil.debugSimpleName(asOpposite) + " : " + asOpposite);
			System.out.println(s.toString()); */
		}
//		assert mergedProperty != null;
		return mergedProperty != null ? mergedProperty : asProperty;
	}

	@Override
	public @NonNull TupleType visitTupleType(@NonNull TupleType asTupleType) {
	//	TemplateableElement unspecializedElement = asTupleType.getUnspecializedElement();
	//	if (unspecializedElement == null) {
	//		return super.visitTupleType(asTupleType);
	//	}
	//	TupleType mergedTupleType = (TupleType)unspecializedElement.accept(this);
		List<@NonNull Property> tupleParts = PivotUtilInternal.getOwnedPropertiesList(asTupleType);
		@NonNull TuplePart[] mergedTupleParts = new @NonNull TuplePart[tupleParts.size()];
		int i = 0;
		for (@NonNull Property asProperty : tupleParts) {
			Type mergedType = (Type)asProperty.getType().accept(this);
			mergedTupleParts[i++] = new TuplePart.TuplePartImpl(NameUtil.getName(asProperty), mergedType);
		}
		return orphanage.getTupleType(orphanage.getStandardLibrary().getOclTupleType(), mergedTupleParts);
	}
}
