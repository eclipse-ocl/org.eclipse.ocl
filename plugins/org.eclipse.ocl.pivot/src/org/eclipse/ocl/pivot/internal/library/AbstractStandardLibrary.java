/**
 * Copyright (c) 2010, 2025 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 */
package org.eclipse.ocl.pivot.internal.library;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.CollectionType;
import org.eclipse.ocl.pivot.DataType;
import org.eclipse.ocl.pivot.InvalidType;
import org.eclipse.ocl.pivot.LambdaType;
import org.eclipse.ocl.pivot.MapType;
import org.eclipse.ocl.pivot.StandardLibrary;
import org.eclipse.ocl.pivot.TemplateParameter;
import org.eclipse.ocl.pivot.TupleType;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.VoidType;
import org.eclipse.ocl.pivot.flat.FlatClass;
import org.eclipse.ocl.pivot.messages.StatusCodes;
import org.eclipse.ocl.pivot.options.PivotValidationOptions;
import org.eclipse.ocl.pivot.values.IntegerValue;
import org.eclipse.ocl.pivot.values.TemplateArguments;
import org.eclipse.ocl.pivot.values.UnlimitedNaturalValue;

/**
 * @since 7.0
 */
public abstract class AbstractStandardLibrary implements StandardLibrary
{
	protected AbstractStandardLibrary() {
	//	System.out.println("ctor " + NameUtil.debugSimpleName(this));
	}

	/**
	 * Configuration of validation preferences.
	 *
	 * The key used to be a magic String publicly exports from XXXTables polluting the API.
	 *
	 * Now it is the EOperation literal of the validation method.
	 */
	private /*LazyNonNull*/ Map<@Nullable Object, StatusCodes.@Nullable Severity> validationKey2severity = null;

	@Override
	public final boolean conformsTo(@NonNull Type leftType, @NonNull Type rightType) {
		return conformsTo(leftType, null, rightType, null, false);
	}

	@Override
	public final boolean conformsTo(@NonNull Type leftType, boolean leftIsRequired, @Nullable TemplateArguments leftTemplateArguments,
			@NonNull Type rightType, boolean rightIsRequired, @Nullable TemplateArguments rightTemplateArguments) {
		if (!leftIsRequired && rightIsRequired) {
			return false;
		}
		return conformsTo(leftType, leftTemplateArguments, rightType, rightTemplateArguments, true);
	}

	@Override
	public final boolean conformsTo(@NonNull Type leftType, @Nullable TemplateArguments leftTemplateArguments,
			@NonNull Type rightType, @Nullable TemplateArguments rightTemplateArguments) {
		return conformsTo(leftType, leftTemplateArguments, rightType, rightTemplateArguments, false);
	}

	/**
	 * @since 7.0
	 */
	@Override
	public final boolean conformsTo(@NonNull Type leftType, @Nullable TemplateArguments leftTemplateArguments,
			@NonNull Type rightType, @Nullable TemplateArguments rightTemplateArguments, boolean enforceNullity) {
		if (leftType == rightType) {
			return true;
		}
		if (leftType instanceof InvalidType) {
			return true;
		}
		else if (leftType instanceof VoidType) {
			if (rightType instanceof InvalidType) {
				return false;
			}
			else {
				return true;
			}
		}
		//
		//	Resolve left template parameters to its TemplateArgument
		//
		if ((leftType instanceof TemplateParameter) && (leftTemplateArguments != null)) {
			TemplateParameter leftTemplateParameter = (TemplateParameter)leftType;
			Type leftTemplateArgument = leftTemplateArguments.get(leftTemplateParameter);
			if (leftTemplateArgument != null) {
				leftType = leftTemplateArgument;
			}
		}
		//
		//	Accrue solution to the right template parameter
		//
		if ((rightType instanceof TemplateParameter) && (rightTemplateArguments != null)) {
			TemplateParameter rightTemplateParameter = (TemplateParameter)rightType;
			rightTemplateArguments.put(rightTemplateParameter, leftType);
			return true;
		}
		if (leftType == rightType) {
			return true;
		}
		//
		//	Normalize types to their behavioral class
		//
//		CompleteClass leftCompleteClass = getCompleteClass(leftType);
//		CompleteClass rightCompleteClass = getCompleteClass(rightType);
//		if (leftCompleteClass == rightCompleteClass) {
//			return true;
//		}
	//	leftType = leftCompleteClass.getPrimaryClass();
//		Type behavioralClass = rightCompleteClass.getBehavioralClass();
//		if ((behavioralClass != null) && (behavioralClass != rightType)) {
//			rightCompleteClass = getCompleteClass(behavioralClass);		// See Bug 574431 / Issue 2190 for discussion of this dodgy downcast
//			rightType = behavioralClass;
//		}
		leftType = getPrimaryType(leftType);
		rightType = getPrimaryType(rightType);
		if (leftType == rightType) {
			return true;
		}
	//	Type behavioralSecondType = basicGetBehavioralType(rightType);
	//	if (behavioralSecondType != null) {
	//		rightType = behavioralSecondType;
	//	}
		//
		//	Use specialized conformance for compound types, inheritance tree intersection for simple types
		//
	//	if (leftType == rightType) {
	//		return true;
	//	}
		if (leftType instanceof DataType) {
			if (leftType instanceof CollectionType) {
				if (rightType instanceof CollectionType) {
					return getCollectionTypeManager().conformsToCollectionType(this, (CollectionType)leftType, leftTemplateArguments, (CollectionType)rightType, rightTemplateArguments, enforceNullity);
				}
				// Drop through to simple inheritance for e.g. OclAny
			}
			else if (leftType instanceof MapType) {
				if (rightType instanceof MapType) {
					return getMapTypeManager().conformsToMapType(this, (MapType)leftType, leftTemplateArguments, (MapType)rightType, rightTemplateArguments, enforceNullity);
				}
				// Drop through to simple inheritance for e.g. OclAny
			}
			else if (leftType instanceof LambdaType) {
				if (rightType instanceof LambdaType) {
					return getLambdaTypeManager().conformsToLambdaType(this, (LambdaType)leftType, leftTemplateArguments, (LambdaType)rightType, rightTemplateArguments, enforceNullity);
				}
				// Drop through to simple inheritance for e.g. OclAny
			}
			else if (leftType instanceof TupleType) {
				if (rightType instanceof TupleType) {
					return getTupleTypeManager().conformsToTupleType(this, (TupleType)leftType, leftTemplateArguments, (TupleType)rightType, rightTemplateArguments, enforceNullity);
				}
				// Drop through to simple inheritance for e.g. OclAny
			}
			else {
				if (rightType instanceof DataType) {
					Type behavioralRightType = basicGetBehavioralType(rightType);
					if (behavioralRightType != null) {
						rightType = behavioralRightType;
					}
				}
			}
		}
		return conformsToSimpleType(leftType, rightType);
	}

	@Override
	public final boolean conformsToSimpleType(@NonNull Type leftType, @NonNull Type rightType) {	// After compound types handled
	//	assert leftType instanceof org.eclipse.ocl.pivot.Class;// && !(leftType instanceof DataType);
	//	assert rightType instanceof org.eclipse.ocl.pivot.Class;// && !(rightType instanceof DataType);
		if (leftType == rightType) {		// XXX specializations
			return true;
		}
		Type leftPrimaryType = getPrimaryType(leftType);
		Type rightPrimaryType = getPrimaryType(rightType);
		FlatClass leftFlatClass = leftPrimaryType.getFlatClass(this);
		FlatClass rightFlatClass = rightPrimaryType.getFlatClass(this);
		return leftFlatClass.isSubFlatClassOf(rightFlatClass);
	}

	protected @NonNull HashMap<@Nullable Object, StatusCodes.@Nullable Severity> createValidationKey2severityMap() {
		return PivotValidationOptions.createValidationKey2severityMap();
	}

	@Override
	public final @NonNull CollectionType getBagType(@NonNull Type elementType, boolean isNullFree, @Nullable IntegerValue lower, @Nullable UnlimitedNaturalValue upper) {
		return getCollectionType(getBagType(), elementType, isNullFree, lower, upper);
	}

	@Override
	public final @NonNull CollectionType getOrderedSetType(@NonNull Type elementType, boolean isNullFree, @Nullable IntegerValue lower, @Nullable UnlimitedNaturalValue upper) {
		return getCollectionType(getOrderedSetType(), elementType, isNullFree, lower, upper);
	}

	@Override
	public final @NonNull CollectionType getSequenceType(@NonNull Type elementType, boolean isNullFree, @Nullable IntegerValue lower, @Nullable UnlimitedNaturalValue upper) {
		return getCollectionType(getSequenceType(), elementType, isNullFree, lower, upper);
	}

	@Override
	public final @NonNull CollectionType getSetType(@NonNull Type elementType, boolean isNullFree, @Nullable IntegerValue lower, @Nullable UnlimitedNaturalValue upper) {
		return getCollectionType(getSetType(), elementType, isNullFree, lower, upper);
	}

	@Override
	public StatusCodes.@Nullable Severity getSeverity(@Nullable Object validationKey) {
		Map<@Nullable Object, StatusCodes.@Nullable Severity> validationKey2severity2 = validationKey2severity;
		if (validationKey2severity2 == null) {
			validationKey2severity = validationKey2severity2 = createValidationKey2severityMap();
		}
		return validationKey2severity2.get(validationKey);
	}

	public void resetSeverities() {
		validationKey2severity = null;
	}
}
