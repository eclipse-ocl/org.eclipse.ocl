/*******************************************************************************
 * Copyright (c) 2014, 2017 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   E.D. Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.internal.manager;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.CallExp;
import org.eclipse.ocl.pivot.CollectionType;
import org.eclipse.ocl.pivot.LoopExp;
import org.eclipse.ocl.pivot.OCLExpression;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.VoidType;
import org.eclipse.ocl.pivot.internal.utilities.PivotUtilInternal;
import org.eclipse.ocl.pivot.library.LibraryFeature;
import org.eclipse.ocl.pivot.library.collection.CollectionAsBagOperation;
import org.eclipse.ocl.pivot.library.collection.CollectionAsOrderedSetOperation;
import org.eclipse.ocl.pivot.library.collection.CollectionAsSequenceOperation;
import org.eclipse.ocl.pivot.library.collection.CollectionAsSetOperation;
import org.eclipse.ocl.pivot.library.collection.CollectionExcludingAllOperation;
import org.eclipse.ocl.pivot.library.collection.CollectionExcludingOperation;
import org.eclipse.ocl.pivot.library.collection.CollectionFlattenOperation;
import org.eclipse.ocl.pivot.library.collection.CollectionIntersectionOperation;
import org.eclipse.ocl.pivot.library.collection.CollectionMaxOperation;
import org.eclipse.ocl.pivot.library.collection.CollectionMinOperation;
import org.eclipse.ocl.pivot.library.collection.OrderedCollectionAtOperation;
import org.eclipse.ocl.pivot.library.collection.OrderedCollectionFirstOperation;
import org.eclipse.ocl.pivot.library.collection.OrderedCollectionLastOperation;
import org.eclipse.ocl.pivot.library.iterator.AnyIteration;
import org.eclipse.ocl.pivot.library.iterator.CollectIteration;
import org.eclipse.ocl.pivot.library.iterator.RejectIteration;
import org.eclipse.ocl.pivot.library.iterator.SelectIteration;
import org.eclipse.ocl.pivot.library.iterator.SortedByIteration;
import org.eclipse.ocl.pivot.library.oclany.OclAnyOclAsSetOperation;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.utilities.TypeUtil;
import org.eclipse.ocl.pivot.utilities.ValueUtil;
import org.eclipse.ocl.pivot.values.IntegerValue;
import org.eclipse.ocl.pivot.values.UnlimitedNaturalValue;

/**
 * TemplateParameterSubstitutionHelper instances support irregular TemplateParameterSubstitution deduction for difficult to
 * model operations such as flatten().
 * <p>
 * The TemplateParameterSubstitutionHelper maintains a registry of helpers indexed by their implementatin class.
 */
public abstract class TemplateParameterSubstitutionHelper
{
	public void resolveUnmodeledTemplateParameterSubstitutions(@NonNull TemplateParameterSubstitutionVisitor templateParameterSubstitutions, @NonNull CallExp callExp) {}

	public @Nullable Type resolveBodyType(@NonNull PivotMetamodelManager metamodelManager, @NonNull CallExp callExp, @Nullable Type bodyType) {
		return bodyType;
	}

	/**
	 * Return true/false according to whetyher the return type isRequired. Return null if the nullity was encoded in the resolveReturnType return.
	 *
	 * @since 1.3
	 */
	public @Nullable Boolean resolveReturnNullity(@NonNull PivotMetamodelManager metamodelManager, @NonNull CallExp callExp, boolean returnIsRequired) {
		return returnIsRequired;
	}

	public @Nullable Type resolveReturnType(@NonNull PivotMetamodelManager metamodelManager, @NonNull CallExp callExp, @Nullable Type returnType) {
		return returnType;
	}

	private static @NonNull Map<Class<? extends LibraryFeature>, TemplateParameterSubstitutionHelper> className2helper = new HashMap<Class<? extends LibraryFeature>, TemplateParameterSubstitutionHelper>();

	public static void addHelper(@NonNull Class<? extends LibraryFeature> className, @NonNull TemplateParameterSubstitutionHelper helper) {
		className2helper.put(className,  helper);
	}

	public static @Nullable TemplateParameterSubstitutionHelper getHelper(@NonNull Class<? extends LibraryFeature> className) {
		return className2helper.get(className);
	}

	//
	//	Special case processing for collect() return and body types.
	//
	private static class CollectionCollectHelper extends TemplateParameterSubstitutionHelper
	{
		@Override
		public @Nullable Type resolveBodyType(@NonNull PivotMetamodelManager metamodelManager, @NonNull CallExp callExp, @Nullable Type returnType) {
			LoopExp loopExp = (LoopExp)callExp;
			OCLExpression body = loopExp.getOwnedBody();
			Type asType = body != null ? body.getRawType() : null;
			Type bodyType = asType != null ? PivotUtilInternal.getNonLambdaType(asType) : null;
			if (bodyType != null) {
				@NonNull Type decodedElementType = TypeUtil.decodeNullableType(bodyType);
				int nullity = TypeUtil.decodeNullity(bodyType);
				while (decodedElementType instanceof CollectionType) {
					Type elementType2 = ((CollectionType)decodedElementType).getElementType();
					if (elementType2 != null) {
						decodedElementType = TypeUtil.decodeNullableType(elementType2);
						nullity = Math.max(nullity, TypeUtil.decodeNullity(elementType2));
					}
					else {
						break;
					}
				}
				return TypeUtil.encodeNullity(metamodelManager.getEnvironmentFactory(), decodedElementType, nullity);
			}
			return returnType;
		}

		@Override
		public @Nullable Boolean resolveReturnNullity(@NonNull PivotMetamodelManager metamodelManager, @NonNull CallExp callExp, boolean returnIsRequired) {
			return null;
		}

		@Override
		public @Nullable Type resolveReturnType(@NonNull PivotMetamodelManager metamodelManager, @NonNull CallExp callExp, @Nullable Type returnType) {
			if (returnType != null) {
				OCLExpression ownedBody = ((LoopExp)callExp).getOwnedBody();
				if (ownedBody != null) {
					Type decodedReturnCollectionType = TypeUtil.decodeNullableType(returnType);
					Type decodedBodyType = TypeUtil.decodeNullableType(ownedBody);
					int bodyNullity = TypeUtil.decodeNullity(ownedBody);
					while (decodedBodyType instanceof CollectionType) {
						decodedBodyType = ((CollectionType)decodedBodyType).getElementType();
						if (decodedBodyType != null) {
							bodyNullity = Math.max(bodyNullity, TypeUtil.decodeNullity(decodedBodyType));
						}
					}
					assert decodedReturnCollectionType instanceof CollectionType;
					CollectionType collectionType = (CollectionType)decodedReturnCollectionType;
					Type returnElementType = collectionType.getElementType();
					if (returnElementType != null) {
						Type decodedReturnElementType = TypeUtil.decodeNullableType(returnElementType);
						int elementNullity = Math.min(TypeUtil.decodeNullity(returnElementType), bodyNullity);
						Type elementType = TypeUtil.encodeNullity(metamodelManager.getEnvironmentFactory(), decodedReturnElementType, elementNullity);
						returnType = metamodelManager.getCollectionType(collectionType.isOrdered(), collectionType.isUnique(),
							elementType, true, collectionType.getLowerValue(), collectionType.getUpperValue());
						returnType = TypeUtil.encodeNullity(metamodelManager.getEnvironmentFactory(), returnType, TypeUtil.decodeNullity(returnType));
					}
				}
			}
			return returnType;
		}
	}

	//
	//	Special case processing for flatten() return type.
	//
	private static class CollectionFlattenHelper extends TemplateParameterSubstitutionHelper
	{
		@Override
		public void resolveUnmodeledTemplateParameterSubstitutions(@NonNull TemplateParameterSubstitutionVisitor templateParameterSubstitutions, @NonNull CallExp callExp) {
			Type elementType = callExp.getOwnedSource().getType();
			while (elementType instanceof CollectionType) {
				elementType = ((CollectionType)elementType).getElementType();
			}
			templateParameterSubstitutions.put(1, elementType);
		}
	}

	//
	//	Special case processing to unify the nullity of return types and source collection element types.
	//
	//	e.g. for any(), at(), first(), last(), max(), min()
	//
	private static class CollectionSourceElementHelper extends TemplateParameterSubstitutionHelper
	{
		@Override
		public @Nullable Type resolveReturnType(@NonNull PivotMetamodelManager metamodelManager, @NonNull CallExp callExp, @Nullable Type returnType) {
			if (returnType != null) {
				OCLExpression ownedSource = callExp.getOwnedSource();
				if (ownedSource != null) {
					Type decodedReturnType = TypeUtil.decodeNullableType(returnType);
					int returnNullity = Math.min(TypeUtil.decodeNullity(returnType), TypeUtil.decodeNullity(ownedSource));
					returnType = TypeUtil.encodeNullity(metamodelManager.getEnvironmentFactory(), decodedReturnType, returnNullity);
				}
			}
			return returnType;
		}

		@Override
		public @Nullable Boolean resolveReturnNullity(@NonNull PivotMetamodelManager metamodelManager, @NonNull CallExp callExp, boolean returnIsRequired) {
			return null;
		}

		/*		@Override
		public @Nullable Boolean resolveReturnNullity(@NonNull PivotMetamodelManager metamodelManager, @NonNull CallExp callExp, boolean returnIsRequired) {
			OCLExpression ownedSource = callExp.getOwnedSource();
			if (ownedSource != null) {
				Type sourceType = ownedSource.getType();
				if (sourceType instanceof CollectionType) {
					returnIsRequired = ((CollectionType)sourceType).isIsNullFree();
				}
			}
			return returnIsRequired;
		} */
	}

	//
	//	Special case processing for return collection types based on the source collection types and multiplicities.
	//
	private static class CollectionAsCollectionHelper extends TemplateParameterSubstitutionHelper
	{
		@Override
		public @Nullable Type resolveReturnType(@NonNull PivotMetamodelManager metamodelManager, @NonNull CallExp callExp, @Nullable Type returnType) {
			if (returnType instanceof CollectionType) {
				OCLExpression ownedSource = callExp.getOwnedSource();
				if (ownedSource != null) {
					Type sourceType = ownedSource.getType();
					CollectionType returnCollectionType = (CollectionType)returnType;
					if (sourceType instanceof CollectionType) {
						CollectionType sourceCollectionType = (CollectionType)sourceType;
						Type elementType = PivotUtil.getElementType(sourceCollectionType);
						returnType = metamodelManager.getCollectionType(returnCollectionType.isOrdered(), returnCollectionType.isUnique(),
							elementType, sourceCollectionType.isIsNullFree(), sourceCollectionType.getLowerValue(), sourceCollectionType.getUpperValue());
					}
				}
			}
			return returnType;
		}
	}

	//
	//	Special case processing to unify the nullity of return collection types and source collection types.
	//
	//	e.g. excluding, excludingAll, intersection, reject, select, sortedBy
	//
	private static class CollectionSourceHelper extends TemplateParameterSubstitutionHelper
	{
		@Override
		public @Nullable Type resolveReturnType(@NonNull PivotMetamodelManager metamodelManager, @NonNull CallExp callExp, @Nullable Type returnType) {
			if (returnType != null) {
				OCLExpression ownedSource = callExp.getOwnedSource();
				if (ownedSource != null) {
					Type decodedReturnCollectionType = TypeUtil.decodeNullableType(returnType);
					Type decodedSourceCollectionType = TypeUtil.decodeNullableType(ownedSource);
					assert decodedReturnCollectionType instanceof CollectionType;
					assert decodedSourceCollectionType instanceof CollectionType;
					CollectionType collectionType = (CollectionType)decodedReturnCollectionType;
					Type returnElementType = collectionType.getElementType();
					Type sourceElementType = ((CollectionType)decodedSourceCollectionType).getElementType();
					if ((returnElementType != null) && (sourceElementType !=null)) {
						Type decodedReturnElementType = TypeUtil.decodeNullableType(returnElementType);
						//						Type decodedSourceElementType = TypeUtil.decodeNullableType(sourceElementType);
						int elementNullity = Math.min(TypeUtil.decodeNullity(returnElementType), TypeUtil.decodeNullity(sourceElementType));
						Type elementType = TypeUtil.encodeNullity(metamodelManager.getEnvironmentFactory(), decodedReturnElementType, elementNullity);
						returnType = metamodelManager.getCollectionType(collectionType.isOrdered(), collectionType.isUnique(),
							elementType, true, collectionType.getLowerValue(), collectionType.getUpperValue());
						returnType = TypeUtil.encodeNullity(metamodelManager.getEnvironmentFactory(), returnType, TypeUtil.decodeNullity(returnType));
					}
				}
			}
			return returnType;
		}

		@Override
		public @Nullable Boolean resolveReturnNullity(@NonNull PivotMetamodelManager metamodelManager, @NonNull CallExp callExp, boolean returnIsRequired) {
			return null;
		}
	}

	//
	//	Special case processing to unify the nullity of return collection element type and source element type with appropriate bounds.
	//
	private static class OclAnyOclAsSetHelper extends TemplateParameterSubstitutionHelper  // Working around Bug 512758
	{
		@Override
		public @Nullable Type resolveReturnType(@NonNull PivotMetamodelManager metamodelManager, @NonNull CallExp callExp, @Nullable Type returnType) {
			assert (returnType instanceof CollectionType);
			OCLExpression ownedSource = callExp.getOwnedSource();
			if (ownedSource != null) {
				int sourceNullity = TypeUtil.decodeNullity(ownedSource);
				Type decodedSourceType = TypeUtil.decodeNullableType(ownedSource);
				IntegerValue lowerBound;
				UnlimitedNaturalValue upperBound;
				if (decodedSourceType instanceof VoidType) {
					lowerBound = ValueUtil.ZERO_VALUE;
					upperBound = ValueUtil.unlimitedNaturalValueOf(0);
				}
				else {
					lowerBound = sourceNullity > 0 ? ValueUtil.ZERO_VALUE : ValueUtil.ONE_VALUE;
					upperBound = ValueUtil.unlimitedNaturalValueOf(1);
				}
				returnType = metamodelManager.getCollectionType(false, true, decodedSourceType, true, lowerBound, upperBound);
				if (sourceNullity >= 2) {
					returnType = metamodelManager.getCompleteModel().getInvalidableType(returnType);
				}
			}
			return returnType;
		}

		@Override
		public @Nullable Boolean resolveReturnNullity(@NonNull PivotMetamodelManager metamodelManager, @NonNull CallExp callExp, boolean returnIsRequired) {
			return null;
		}
	}

	static
	{
		addHelper(AnyIteration.class, new CollectionSourceElementHelper());
		addHelper(CollectIteration.class, new CollectionCollectHelper());
		addHelper(CollectionAsBagOperation.class, new CollectionAsCollectionHelper());
		addHelper(CollectionAsOrderedSetOperation.class, new CollectionAsCollectionHelper());
		addHelper(CollectionAsSequenceOperation.class, new CollectionAsCollectionHelper());
		addHelper(CollectionAsSetOperation.class, new CollectionAsCollectionHelper());
		addHelper(CollectionExcludingOperation.class, new CollectionSourceHelper());
		addHelper(CollectionExcludingAllOperation.class, new CollectionSourceHelper());
		//		addHelper(CollectionIncludingOperation.class, new CollectionSourceAndArgumentHelper());
		//		addHelper(CollectionIncludingAllOperation.class, new CollectionSourceAndArgumentHelper());
		addHelper(CollectionIntersectionOperation.class, new CollectionSourceHelper()/*OrArgument*/);
		addHelper(CollectionMaxOperation.class, new CollectionSourceElementHelper());
		addHelper(CollectionMinOperation.class, new CollectionSourceElementHelper());
		addHelper(OrderedCollectionAtOperation.class, new CollectionSourceElementHelper());
		addHelper(OrderedCollectionFirstOperation.class, new CollectionSourceElementHelper());
		addHelper(OrderedCollectionLastOperation.class, new CollectionSourceElementHelper());
		addHelper(CollectionFlattenOperation.class, new CollectionFlattenHelper());
		addHelper(OclAnyOclAsSetOperation.class, new OclAnyOclAsSetHelper());
		addHelper(RejectIteration.class, new CollectionSourceHelper());
		addHelper(SelectIteration.class, new CollectionSourceHelper());
		addHelper(SortedByIteration.class, new CollectionSourceHelper());
	}
}