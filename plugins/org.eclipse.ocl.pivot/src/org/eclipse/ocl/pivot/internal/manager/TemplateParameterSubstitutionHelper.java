/*******************************************************************************
 * Copyright (c) 2014, 2019 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D. Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.internal.manager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.CallExp;
import org.eclipse.ocl.pivot.CollectionType;
import org.eclipse.ocl.pivot.LoopExp;
import org.eclipse.ocl.pivot.OCLExpression;
import org.eclipse.ocl.pivot.OperationCallExp;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.internal.utilities.PivotUtilInternal;
import org.eclipse.ocl.pivot.library.LibraryFeature;
import org.eclipse.ocl.pivot.library.collection.CollectionAsBagOperation;
import org.eclipse.ocl.pivot.library.collection.CollectionAsOrderedSetOperation;
import org.eclipse.ocl.pivot.library.collection.CollectionAsSequenceOperation;
import org.eclipse.ocl.pivot.library.collection.CollectionAsSetOperation;
import org.eclipse.ocl.pivot.library.collection.CollectionExcludingAllOperation;
import org.eclipse.ocl.pivot.library.collection.CollectionExcludingOperation;
import org.eclipse.ocl.pivot.library.collection.CollectionFlattenOperation;
import org.eclipse.ocl.pivot.library.collection.CollectionIncludingAllOperation;
import org.eclipse.ocl.pivot.library.collection.CollectionIncludingOperation;
import org.eclipse.ocl.pivot.library.collection.CollectionIntersectionOperation;
import org.eclipse.ocl.pivot.library.collection.CollectionMinOperation;
import org.eclipse.ocl.pivot.library.collection.OrderedCollectionAtOperation;
import org.eclipse.ocl.pivot.library.collection.OrderedCollectionFirstOperation;
import org.eclipse.ocl.pivot.library.collection.OrderedCollectionLastOperation;
import org.eclipse.ocl.pivot.library.iterator.AnyIteration;
import org.eclipse.ocl.pivot.library.iterator.ClosureIteration;
import org.eclipse.ocl.pivot.library.iterator.CollectIteration;
import org.eclipse.ocl.pivot.library.iterator.RejectIteration;
import org.eclipse.ocl.pivot.library.iterator.SelectIteration;
import org.eclipse.ocl.pivot.library.iterator.SortedByIteration;
import org.eclipse.ocl.pivot.library.oclany.OclAnyOclAsSetOperation;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.utilities.ValueUtil;
import org.eclipse.ocl.pivot.values.IntegerValue;
import org.eclipse.ocl.pivot.values.UnlimitedNaturalValue;

/**
 * TemplateParameterSubstitutionHelper instances support irregular TemplateParameterSubstitution deduction for difficult to
 * model operations such as flatten().
 * <p>
 * The TemplateParameterSubstitutionHelper maintains a registry of helpers indexed by their implementation class.
 */
public abstract class TemplateParameterSubstitutionHelper
{
	/**
	 * Add any templateParameter substitutions to templateParameterSubstitutions that the regular library modeling omits.
	 *
	 * THe default implementation adds nothing. The intended usage for flatten where the input/output types are irregular.
	 */
	public void resolveUnmodeledTemplateParameterSubstitutions(@NonNull TemplateParameterSubstitutionVisitor templateParameterSubstitutions, @NonNull CallExp callExp) {}

	/**
	 * Return the actual type of te body of callExp for which the regular library modeling suggests bodyType.
	 *
	 * The default implementation just returns bodyType.
	 */
	public @Nullable Type resolveBodyType(@NonNull PivotMetamodelManager metamodelManager, @NonNull CallExp callExp, @Nullable Type bodyType) {
		return bodyType;
	}

	/**
	 * Return the actual return nullity of callExp for which the regular library modeling suggests returnIsRequired.
	 *
	 * The default implementation just returns returnIsRequired.
	 * @since 1.3
	 */
	public boolean resolveReturnNullity(@NonNull PivotMetamodelManager metamodelManager, @NonNull CallExp callExp, boolean returnIsRequired) {
		return returnIsRequired;
	}

	/**
	 * Return the actual return type of callExp for which the regular library modeling suggests returnType.
	 *
	 * The default implementation just returns returnType.
	 */
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
	//	Special case processing for closure() that deduces nullFree both source and argument.
	//
	private static class CollectionClosureHelper extends TemplateParameterSubstitutionHelper
	{
		@Override
		public @Nullable Type resolveReturnType(@NonNull PivotMetamodelManager metamodelManager, @NonNull CallExp callExp, @Nullable Type returnType) {
			if (returnType instanceof CollectionType) {
				OCLExpression ownedSource = callExp.getOwnedSource();
				if (ownedSource != null) {
					Type sourceType = ownedSource.getType();
					if (sourceType instanceof CollectionType) {
						CollectionType sourceCollectionType = (CollectionType)sourceType;
						OCLExpression body = ((LoopExp)callExp).getOwnedBody();
						Type bodyType = body.getType();
						if (bodyType instanceof CollectionType) {
							CollectionType argumentCollectionType = (CollectionType)bodyType;
							boolean isNullFree = sourceCollectionType.isIsNullFree() && argumentCollectionType.isIsNullFree();
							CollectionType returnCollectionType = (CollectionType)returnType;
							if (returnCollectionType.isIsNullFree() != isNullFree) {
								@SuppressWarnings("null")@NonNull Type elementType = returnCollectionType.getElementType();
								returnType = metamodelManager.getCollectionType(returnCollectionType.isOrdered(), returnCollectionType.isUnique(),
									elementType, isNullFree, returnCollectionType.getLowerValue(), returnCollectionType.getUpperValue());
							}
						}
					}
				}
			}
			return returnType;
		}
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
			Type asType = body != null ? body.getType() : null;
			Type bodyType = asType != null ? PivotUtilInternal.getNonLambdaType(asType) : null;
			if (bodyType != null) {
				@NonNull Type elementType = bodyType;
				//				if (bodyType instanceof CollectionType) {
				while (elementType instanceof CollectionType) {
					Type elementType2 = ((CollectionType)elementType).getElementType();
					if (elementType2 != null) {
						elementType = elementType2;
					}
				}
				//				}
				return elementType;
			}
			return returnType;
		}

		@Override
		public @Nullable Type resolveReturnType(@NonNull PivotMetamodelManager metamodelManager, @NonNull CallExp callExp, @Nullable Type returnType) {
			LoopExp loopExp = (LoopExp)callExp;
			OCLExpression body = loopExp.getOwnedBody();
			Type asType = body != null ? body.getType() : null;
			Type bodyType = asType != null ? PivotUtilInternal.getNonLambdaType(asType) : null;
			if (bodyType != null) {
				@NonNull Type elementType = bodyType;
				//				if (bodyType instanceof CollectionType) {
				while (elementType instanceof CollectionType) {
					Type elementType2 = ((CollectionType)elementType).getElementType();
					if (elementType2 != null) {
						elementType = elementType2;
					}
				}
				//				}
				boolean isOrdered = (returnType instanceof CollectionType) && ((CollectionType)returnType).isOrdered();
				boolean isNullFree = asType instanceof CollectionType && ((CollectionType)asType).isIsNullFree();
				boolean isRequired = !(asType instanceof CollectionType) && (body != null) && body.isIsRequired();
				returnType = metamodelManager.getCollectionType(isOrdered, false, elementType, isNullFree || isRequired, null, null);	// FIXME null, null
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
	//	Special case processing for return types based on the source collection element typess.
	//
	private static class CollectionSourceElementHelper extends TemplateParameterSubstitutionHelper
	{
		@Override
		public boolean resolveReturnNullity(@NonNull PivotMetamodelManager metamodelManager, @NonNull CallExp callExp, boolean returnIsRequired) {
			OCLExpression ownedSource = callExp.getOwnedSource();
			if (ownedSource != null) {
				Type sourceType = ownedSource.getType();
				if (sourceType instanceof CollectionType) {
					returnIsRequired = ((CollectionType)sourceType).isIsNullFree();
				}
			}
			return returnIsRequired;
		}
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

	/*
	//	Special case processing for an any(true) return from a null-free collection.
	//
	private static class CollectionAnyHelper extends CollectionSourceHelper
	{
		@Override
		public boolean resolveReturnNullity(@NonNull PivotMetamodelManager metamodelManager, @NonNull CallExp callExp, boolean returnIsRequired) {
			OCLExpression ownedBody = ((LoopExp)callExp).getOwnedBody();
			if ((ownedBody instanceof BooleanLiteralExp) && ((BooleanLiteralExp)ownedBody).isBooleanSymbol()) {
				OCLExpression ownedSource = callExp.getOwnedSource();
				if (ownedSource != null) {
					Type sourceType = ownedSource.getType();
					if (sourceType instanceof CollectionType) {
						CollectionType collectionType = (CollectionType)sourceType;
						if (collectionType.isIsNullFree()) {
							return true;
						}
					}
				}
			}
			return returnIsRequired;
		}
	} */

	//
	//	Special case processing for including() that deduces nullFree both source and argument.
	//
	private static class CollectionIncludingHelper extends TemplateParameterSubstitutionHelper
	{
		@Override
		public @Nullable Type resolveReturnType(@NonNull PivotMetamodelManager metamodelManager, @NonNull CallExp callExp, @Nullable Type returnType) {
			if (returnType instanceof CollectionType) {
				OCLExpression ownedSource = callExp.getOwnedSource();
				if (ownedSource != null) {
					Type sourceType = ownedSource.getType();
					if (sourceType instanceof CollectionType) {
						CollectionType sourceCollectionType = (CollectionType)sourceType;
						List<OCLExpression> arguments = ((OperationCallExp)callExp).getOwnedArguments();
						if (arguments.size() > 0) {
							OCLExpression ownedArgument = arguments.get(0);
							if (ownedArgument != null) {
								boolean isNullFree = sourceCollectionType.isIsNullFree() && ownedArgument.isIsRequired();
								CollectionType returnCollectionType = (CollectionType)returnType;
								if (returnCollectionType.isIsNullFree() != isNullFree) {
									@SuppressWarnings("null")@NonNull Type elementType = returnCollectionType.getElementType();
									returnType = metamodelManager.getCollectionType(returnCollectionType.isOrdered(), returnCollectionType.isUnique(),
										elementType, isNullFree, returnCollectionType.getLowerValue(), returnCollectionType.getUpperValue());
								}
							}
						}
					}
				}
			}
			return returnType;
		}
	}

	//
	//	Special case processing for includingAll() that deduces nullFree both source and argument.
	//
	private static class CollectionIncludingAllHelper extends TemplateParameterSubstitutionHelper
	{
		@Override
		public @Nullable Type resolveReturnType(@NonNull PivotMetamodelManager metamodelManager, @NonNull CallExp callExp, @Nullable Type returnType) {
			if (returnType instanceof CollectionType) {
				OCLExpression ownedSource = callExp.getOwnedSource();
				if (ownedSource != null) {
					Type sourceType = ownedSource.getType();
					if (sourceType instanceof CollectionType) {
						CollectionType sourceCollectionType = (CollectionType)sourceType;
						List<OCLExpression> arguments = ((OperationCallExp)callExp).getOwnedArguments();
						if (arguments.size() > 0) {
							OCLExpression ownedArgument = arguments.get(0);
							if (ownedArgument != null) {
								Type argumentType = ownedArgument.getType();
								if (argumentType instanceof CollectionType) {
									CollectionType argumentCollectionType = (CollectionType)argumentType;
									boolean isNullFree = sourceCollectionType.isIsNullFree() && argumentCollectionType.isIsNullFree();
									CollectionType returnCollectionType = (CollectionType)returnType;
									if (returnCollectionType.isIsNullFree() != isNullFree) {
										@SuppressWarnings("null")@NonNull Type elementType = returnCollectionType.getElementType();
										returnType = metamodelManager.getCollectionType(returnCollectionType.isOrdered(), returnCollectionType.isUnique(),
											elementType, isNullFree, returnCollectionType.getLowerValue(), returnCollectionType.getUpperValue());
									}
								}
							}
						}
					}
				}
			}
			return returnType;
		}
	}

	//
	//	Special case processing for return collection types based on the source collection types.
	//
	private static class CollectionSourceHelper extends TemplateParameterSubstitutionHelper
	{
		@Override
		public @Nullable Type resolveReturnType(@NonNull PivotMetamodelManager metamodelManager, @NonNull CallExp callExp, @Nullable Type returnType) {
			if (returnType instanceof CollectionType) {
				OCLExpression ownedSource = callExp.getOwnedSource();
				if (ownedSource != null) {
					Type sourceType = ownedSource.getType();
					CollectionType collectionType = (CollectionType)returnType;
					if ((sourceType instanceof CollectionType) && ((CollectionType)sourceType).isIsNullFree() && !collectionType.isIsNullFree()) {
						@SuppressWarnings("null")@NonNull Type elementType = collectionType.getElementType();
						returnType = metamodelManager.getCollectionType(collectionType.isOrdered(), collectionType.isUnique(),
							elementType, true, collectionType.getLowerValue(), collectionType.getUpperValue());
					}
				}
			}
			return returnType;
		}
	}

	private static class OclAnyOclAsSetHelper extends TemplateParameterSubstitutionHelper  // Working around Bug 512758
	{
		@Override
		public @Nullable Type resolveReturnType(@NonNull PivotMetamodelManager metamodelManager, @NonNull CallExp callExp, @Nullable Type returnType) {
			if (returnType instanceof CollectionType) {
				OCLExpression ownedSource = callExp.getOwnedSource();
				if (ownedSource != null) {
					CollectionType collectionType = (CollectionType)returnType;
					int collectionBound = ownedSource.isIsRequired() ? 1 : 0;
					IntegerValue lowerBound = ValueUtil.integerValueOf(collectionBound);
					UnlimitedNaturalValue upperBound = ValueUtil.unlimitedNaturalValueOf(collectionBound);
					Type elementType = PivotUtil.getElementType(collectionType);
					returnType = metamodelManager.getCollectionType(collectionType.isOrdered(), collectionType.isUnique(),
						elementType, true, lowerBound, upperBound);
				}
			}
			return returnType;
		}
	}

	static
	{
		addHelper(AnyIteration.class, new CollectionSourceElementHelper());
		addHelper(ClosureIteration.class, new CollectionClosureHelper());
		addHelper(CollectIteration.class, new CollectionCollectHelper());
		addHelper(CollectionAsBagOperation.class, new CollectionAsCollectionHelper());
		addHelper(CollectionAsOrderedSetOperation.class, new CollectionAsCollectionHelper());
		addHelper(CollectionAsSequenceOperation.class, new CollectionAsCollectionHelper());
		addHelper(CollectionAsSetOperation.class, new CollectionAsCollectionHelper());
		addHelper(CollectionExcludingOperation.class, new CollectionSourceHelper());
		addHelper(CollectionExcludingAllOperation.class, new CollectionSourceHelper());
		addHelper(CollectionIncludingOperation.class, new CollectionIncludingHelper());
		addHelper(CollectionIncludingAllOperation.class, new CollectionIncludingAllHelper());
		addHelper(CollectionIntersectionOperation.class, new CollectionSourceHelper()/*OrArgument*/);
		addHelper(CollectionMinOperation.class, new CollectionSourceHelper());
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