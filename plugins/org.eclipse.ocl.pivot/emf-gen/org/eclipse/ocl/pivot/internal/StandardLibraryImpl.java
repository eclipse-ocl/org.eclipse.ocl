/**
 * Copyright (c) 2010, 2022 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 */
package org.eclipse.ocl.pivot.internal;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.CollectionType;
import org.eclipse.ocl.pivot.DataType;
import org.eclipse.ocl.pivot.InvalidType;
import org.eclipse.ocl.pivot.LambdaType;
import org.eclipse.ocl.pivot.MapType;
import org.eclipse.ocl.pivot.PivotPackage;
import org.eclipse.ocl.pivot.StandardLibrary;
import org.eclipse.ocl.pivot.TemplateParameter;
import org.eclipse.ocl.pivot.TupleType;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.TypedElement;
import org.eclipse.ocl.pivot.VoidType;
import org.eclipse.ocl.pivot.flat.FlatClass;
import org.eclipse.ocl.pivot.ids.CollectionTypeId;
import org.eclipse.ocl.pivot.ids.IdResolver;
import org.eclipse.ocl.pivot.ids.PartId;
import org.eclipse.ocl.pivot.ids.PrimitiveTypeId;
import org.eclipse.ocl.pivot.ids.TupleTypeId;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.library.ecore.EcoreExecutorType;
import org.eclipse.ocl.pivot.internal.library.executor.AbstractReflectiveInheritanceType;
import org.eclipse.ocl.pivot.internal.library.executor.ReflectiveInheritance;
import org.eclipse.ocl.pivot.library.LibraryConstants;
import org.eclipse.ocl.pivot.manager.CollectionTypeManager;
import org.eclipse.ocl.pivot.manager.JavaTypeManager;
import org.eclipse.ocl.pivot.manager.LambdaTypeManager;
import org.eclipse.ocl.pivot.manager.MapTypeManager;
import org.eclipse.ocl.pivot.manager.SpecializedTypeManager;
import org.eclipse.ocl.pivot.manager.TupleTypeManager;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.values.CollectionTypeArguments;
import org.eclipse.ocl.pivot.values.IntegerValue;
import org.eclipse.ocl.pivot.values.MapTypeArguments;
import org.eclipse.ocl.pivot.values.TemplateParameterSubstitutions;
import org.eclipse.ocl.pivot.values.UnlimitedNaturalValue;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Standard Library</b></em>'.
 * <!-- end-user-doc -->
 *
 * @generated
 */
public abstract class StandardLibraryImpl extends ElementImpl implements StandardLibrary
{
	/**
	 * The number of structural features of the '<em>Standard Library</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int STANDARD_LIBRARY_FEATURE_COUNT = ElementImpl.ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Standard Library</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int STANDARD_LIBRARY_OPERATION_COUNT = ElementImpl.ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The URI used by default for the OCL Standard Library. NB. This
	 * constant is repeated in GenerateOCLstdlibModel.mwe2 and in
	 * org.eclipse.ocl.pivot/plugin.xml.
	 */
	public static final @NonNull String DEFAULT_OCL_STDLIB_URI = LibraryConstants.STDLIB_URI;

	/**
	 * The URI to provide the default Standard Library. This value may be
	 * reassigned prior to any OCL analysis or evaluation to select a different
	 * default. Alternatively the need for default may be bypassed by explicitly
	 * invoking loadLibrary().
	 */
	protected @NonNull String defaultStandardLibraryURI = DEFAULT_OCL_STDLIB_URI;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	protected StandardLibraryImpl()
	{
		this.collectionTypeManager = createCollectionTypeManager();
		this.javaTypeManager = createJavaTypeManager();
		this.lambdaTypeManager = createLambdaTypeManager();
		this.mapTypeManager = createMapTypeManager();
		this.specializedTypeManager = createSpecializedTypeManager();
		this.tupleTypeManager = createTupleTypeManager();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass()
	{
		return PivotPackage.Literals.STANDARD_LIBRARY;
	}

	/**
	 * Shared cache of the lazily created lazily deleted specializations of each collection type.
	 * @since 7.0
	 */
	protected /*@NonNull*/ CollectionTypeManager collectionTypeManager = null;

	/**
	 * Resolver for facilities using ElementIds.
	 * </br>
	 * This is lazily cached since it it is rarely used and has timing challenges if cached eagerly.
	 * </br>
	 * Since the idResolver is lazily crreated, its creation is fully aware of the context and so can create an
	 * appropriate derived IdResolver for e.g. UML avoiding the need for a UML-derivation of the StandardLibrary.
	 */
	private /*@LazyNonNull*/ IdResolver idResolver = null;

	/**
	 * Shared cache of the lazily created lazily deleted representations of each lambda type.
	 * @since 7.0
	 */
	protected /*@NonNull*/ JavaTypeManager javaTypeManager = null;

	/**
	 * Shared cache of the lazily created lazily deleted representations of each type as a Java type.
	 * @since 7.0
	 */
	protected /*@NonNull*/ LambdaTypeManager lambdaTypeManager = null;

	/**
	 * Shared cache of the lazily created lazily deleted specializations of each map type.
	 * @since 7.0
	 */
	protected /*@NonNull*/ MapTypeManager mapTypeManager = null;

	/**
	 * Shared cache of the lazily created lazily deleted representations of each specialized class.
	 * @since 7.0
	 */
	protected @Nullable SpecializedTypeManager specializedTypeManager = null;

	/**
	 * Shared cache of the lazily created lazily deleted specializations of each tuple type.
	 * @since 7.0
	 */
	protected /*@NonNull*/ TupleTypeManager tupleTypeManager = null;

	/**
	 * @since 7.0
	 */
	protected abstract @Nullable Type basicGetBehavioralType(@NonNull Type type);

	@Override
	public @Nullable CollectionType basicGetCollectionType(@NonNull CollectionTypeArguments typeArguments) {
		assert collectionTypeManager != null;
		return collectionTypeManager.basicGetCollectionType(typeArguments);
	}

	@Override
	public boolean conformsTo(@NonNull Type leftType, @NonNull Type rightType) {
		return conformsTo(leftType, null, rightType, null, false);
	}

/*	@Override
	private boolean conformsTo1(@NonNull Type leftType, @NonNull Type rightType) {
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
		else if (leftType instanceof CollectionType) {
			CollectionType leftCollectionType = (CollectionType)leftType;
			if (rightType instanceof CollectionType) {
				assert collectionTypeManager != null;
				return collectionTypeManager.conformsToCollectionType(leftCollectionType, null, (CollectionType)rightType, null, false);		/// XXX nullity
			}
			CollectionType leftGenericType = (CollectionType)leftCollectionType.getUnspecializedElement();
			if (leftGenericType != null) {
				return conformsTo(leftGenericType, rightType);
			}
			// Drop through to inheritance for e.g. OclAny
		}
		else if (leftType instanceof JavaType) {
			throw new UnsupportedOperationException();		// WIP
		}
		else if (leftType instanceof LambdaType) {
			LambdaType leftLambdaType = (LambdaType)leftType;
			if (rightType instanceof LambdaType) {
				if (lambdaTypeManager != null) {
					return lambdaTypeManager.conformsToLambdaType(leftLambdaType, null, (LambdaType)rightType, null, false);		/// XXX nullity
				}
			}
			// Drop through to inheritance for e.g. OclAny
		}
		else if (leftType instanceof MapType) {
			MapType leftMapType = (MapType)leftType;
			if (rightType instanceof MapType) {
				assert mapTypeManager != null;
				return mapTypeManager.conformsToMapType(leftMapType, null, (MapType)rightType, null, false);		/// XXX nullity
			}
			MapType leftGenericType = (MapType)leftMapType.getUnspecializedElement();
			if (leftGenericType != null) {
				return conformsTo(leftGenericType, rightType);
			}
			// Drop through to inheritance for e.g. OclAny
		}
		else if (leftType instanceof SelfType) {
			throw new UnsupportedOperationException();		// WIP
		}
		else if (leftType instanceof TupleType) {
			TupleType leftTupleType = (TupleType)leftType;
			if (rightType instanceof TupleType) {
				assert tupleTypeManager != null;
				return tupleTypeManager.conformsToTupleType(leftTupleType, null, (TupleType)rightType, null, false);		/// XXX nullity
			}
			// Drop through to inheritance for e.g. OclAny
		}
		else if (leftType instanceof DataType) {			// XXX rewrite
			if (rightType instanceof DataType) {
				org.eclipse.ocl.pivot.Class rightBehavioralClass = ((DataType)rightType).getBehavioralClass();
				if (rightBehavioralClass != null) {
					rightType = rightBehavioralClass;		// See Bug 574431 for discussion of this dodgy downcast
				}
			}
			// Drop through to inheritance
		}
		else if (leftType instanceof EcoreReflectiveType) {			// XXX
			EcoreReflectiveType leftEcoreReflectiveType = (EcoreReflectiveType)leftType;
			Class<?> leftInstanceClass = leftEcoreReflectiveType.getEClassifier().getInstanceClass();
			org.eclipse.ocl.pivot.@Nullable Class leftBehavioralClass = leftInstanceClass != null ? PivotUtil.getBehavioralClass(this, leftInstanceClass) : null;
			CompleteInheritance rightInheritance = rightType.getInheritance(this);
			if (leftBehavioralClass == rightInheritance) {
				return true;
			}
			return rightInheritance.isSuperInheritanceOf(leftEcoreReflectiveType);
		}
 		CompleteInheritance leftInheritance = leftType.getInheritance(this);
		CompleteInheritance rightInheritance = rightType.getInheritance(this);
		return leftInheritance.isSubInheritanceOf(rightInheritance);
	} */

	@Override
	public boolean conformsTo(@NonNull Type leftType, boolean leftIsRequired, @Nullable TemplateParameterSubstitutions leftSubstitutions,
			@NonNull Type rightType, boolean rightIsRequired, @Nullable TemplateParameterSubstitutions rightSubstitutions) {
		if (!leftIsRequired && rightIsRequired) {
			return false;
		}
		return conformsTo(leftType, leftSubstitutions, rightType, rightSubstitutions, true);
	}

	@Override
	public boolean conformsTo(@NonNull Type leftType, @Nullable TemplateParameterSubstitutions leftSubstitutions,
			@NonNull Type rightType, @Nullable TemplateParameterSubstitutions rightSubstitutions) {
		return conformsTo(leftType, leftSubstitutions, rightType, rightSubstitutions, false);
	}

	/**
	 * @since 7.0
	 */
	@Override
	public boolean conformsTo(@NonNull Type leftType, @Nullable TemplateParameterSubstitutions leftSubstitutions,
			@NonNull Type rightType, @Nullable TemplateParameterSubstitutions rightSubstitutions, boolean enforceNullity) {
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
		//	Resolve left template parameters to its substitution
		//
		if ((leftType instanceof TemplateParameter) && (leftSubstitutions != null)) {
			TemplateParameter leftTemplateParameter = (TemplateParameter)leftType;
			Type leftSubstitution = leftSubstitutions.get(leftTemplateParameter);
			if (leftSubstitution != null) {
				leftType = leftSubstitution;
			}
		}
		//
		//	Accrue solution to the right template parameter
		//
		if ((rightType instanceof TemplateParameter) && (rightSubstitutions != null)) {
			TemplateParameter rightTemplateParameter = (TemplateParameter)rightType;
			rightSubstitutions.put(rightTemplateParameter, leftType);
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
					assert collectionTypeManager != null;
					return collectionTypeManager.conformsToCollectionType((CollectionType)leftType, leftSubstitutions, (CollectionType)rightType, rightSubstitutions, enforceNullity);
				}
				// Drop through to simple inheritance for e.g. OclAny
			}
			else if (leftType instanceof MapType) {
				if (rightType instanceof MapType) {
					assert mapTypeManager != null;
					return mapTypeManager.conformsToMapType((MapType)leftType, leftSubstitutions, (MapType)rightType, rightSubstitutions, enforceNullity);
				}
				// Drop through to simple inheritance for e.g. OclAny
			}
			else if (leftType instanceof LambdaType) {
				if (rightType instanceof LambdaType) {
					assert lambdaTypeManager != null;
					return lambdaTypeManager.conformsToLambdaType((LambdaType)leftType, leftSubstitutions, (LambdaType)rightType, rightSubstitutions, enforceNullity);
				}
				// Drop through to simple inheritance for e.g. OclAny
			}
			else if (leftType instanceof TupleType) {
				if (rightType instanceof TupleType) {
					assert tupleTypeManager != null;
					return tupleTypeManager.conformsToTupleType((TupleType)leftType, leftSubstitutions, (TupleType)rightType, rightSubstitutions, enforceNullity);
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
	public boolean conformsToSimpleType(@NonNull Type leftType, @NonNull Type rightType) {	// After compound types handled
	//	assert leftType instanceof org.eclipse.ocl.pivot.Class;// && !(leftType instanceof DataType);
	//	assert rightType instanceof org.eclipse.ocl.pivot.Class;// && !(rightType instanceof DataType);
		if (leftType == rightType) {		// XXX specializations
			return true;
		}
		Type leftPrimaryType = getPrimaryType(leftType);
		Type rightPrimaryType = getPrimaryType(rightType);
		FlatClass leftInheritance = leftPrimaryType.getFlatClass(this);
		FlatClass rightInheritance = rightPrimaryType.getFlatClass(this);
		return leftInheritance.isSubInheritanceOf(rightInheritance);
	}

	/**
	 * @since 7.0
	 */
	protected abstract @NonNull CollectionTypeManager createCollectionTypeManager();

	/**
	 * @since 7.0
	 */
	protected abstract @NonNull IdResolver createIdResolver();

	/**
	 * @since 7.0
	 */
	protected abstract @NonNull JavaTypeManager createJavaTypeManager();

	/**
	 * @since 7.0
	 */
	protected abstract @NonNull LambdaTypeManager createLambdaTypeManager();

	/**
	 * @since 7.0
	 */
	protected abstract @NonNull MapTypeManager createMapTypeManager();

	/**
	 * @since 7.0
	 */
	protected abstract @Nullable SpecializedTypeManager createSpecializedTypeManager();

	/**
	 * @since 7.0
	 */
	protected abstract @NonNull TupleTypeManager createTupleTypeManager();

	@Override
	public @NonNull CollectionType getBagType(@NonNull Type elementType, boolean isNullFree, @Nullable IntegerValue lower, @Nullable UnlimitedNaturalValue upper) {
		return getCollectionType(getBagType(), elementType, isNullFree, lower, upper);
	}

	@Override
	public org.eclipse.ocl.pivot.@Nullable Class getBehavioralClass(java.lang.@NonNull Class<?> javaClass) {
		assert javaTypeManager != null;
		return javaTypeManager.getBehavioralClass(javaClass);
	}

	/**
	 * @since 7.0
	 */
	@Override
	public @NonNull CollectionType getCollectionType(@NonNull CollectionTypeArguments typeArguments) {
		assert collectionTypeManager != null;
		return collectionTypeManager.getCollectionType(typeArguments);
	}

	@Override
	public @NonNull CollectionType getCollectionType(@NonNull CollectionTypeId collectionTypeId) {
		assert collectionTypeManager != null;
		return collectionTypeManager.getCollectionType(collectionTypeId);
	}

	@Override
	public @NonNull CollectionType getCollectionType(@NonNull CollectionType genericType, @NonNull Type elementType, boolean isNullFree, @Nullable IntegerValue lower, @Nullable UnlimitedNaturalValue upper) {
		CollectionTypeArguments typeArguments = new CollectionTypeArguments(genericType.getTypeId(), elementType, isNullFree, lower, upper);
		assert collectionTypeManager != null;
		return collectionTypeManager.getCollectionType(typeArguments);
	}

	@Override
	public @NonNull CollectionTypeManager getCollectionTypeManager() {
		assert collectionTypeManager != null;
		return collectionTypeManager;
	}

	@Override
	public org.eclipse.ocl.pivot.@NonNull Class getCommonType(org.eclipse.ocl.pivot.@NonNull Class leftType, org.eclipse.ocl.pivot.@NonNull Class rightType) {
		return (org.eclipse.ocl.pivot.@NonNull Class)getCommonType(leftType, null, rightType, null);
	}

	@Override
	public @NonNull Type getCommonType(@NonNull Type leftType, @NonNull Type rightType) {
		return getCommonType(leftType, null, rightType, null);
	}

	@Override
	public @NonNull Type getCommonType(@NonNull Type leftType, @Nullable TemplateParameterSubstitutions leftSubstitutions,
			@NonNull Type rightType, @Nullable TemplateParameterSubstitutions rightSubstitutions) {
		if (leftType instanceof CollectionType) {
			if (rightType instanceof CollectionType) {
				assert collectionTypeManager != null;
				return collectionTypeManager.getCommonCollectionType((CollectionType)leftType, leftSubstitutions, (CollectionType)rightType, rightSubstitutions);
			}
			return getOclAnyType();
		}
		else if (leftType instanceof LambdaType) {
			if (rightType instanceof LambdaType) {
				throw new UnsupportedOperationException();			// XXX TODO FIXME
			}
			return getOclAnyType();
		}
		else if (leftType instanceof MapType) {
			if (rightType instanceof MapType) {
				assert mapTypeManager != null;
				return mapTypeManager.getCommonMapType((MapType)leftType, leftSubstitutions, (MapType)rightType, rightSubstitutions);
			}
			return getOclAnyType();
		}
		else if (leftType instanceof TupleType) {
			if (rightType instanceof TupleType) {
				assert tupleTypeManager != null;
				TupleType commonTupleType = tupleTypeManager.getCommonTupleType((TupleType)leftType, leftSubstitutions, (TupleType)rightType, rightSubstitutions);
				if (commonTupleType != null) {
					return commonTupleType;
				}
			}
			return getOclAnyType();
		}
		else if (leftType instanceof DataType) {
		//	if (rightType instanceof DataType) {			// XXX Avoid getBehavioralClass problem with conformsTo
				FlatClass leftInheritance = leftType.getFlatClass(this);
				FlatClass rightInheritance = rightType.getFlatClass(this);
				FlatClass commonInheritance = leftInheritance.getCommonInheritance(rightInheritance);
				return getPrimaryType(commonInheritance.getPivotClass());
		//	}
		//	return getOclAnyType();
		}
	/*	if (conformsTo(leftType, leftSubstitutions, rightType, rightSubstitutions, false)) {		// malfunctions in testOperationDependencyAnalysis_Companies for a TemplateParameter as right
			return rightType;
		}
		if (conformsTo(rightType, rightSubstitutions, leftType, leftSubstitutions, false)) {
			return leftType;
		} */
		FlatClass leftInheritance = leftType.getFlatClass(this);
		FlatClass rightInheritance = rightType.getFlatClass(this);
		FlatClass commonInheritance = leftInheritance.getCommonInheritance(rightInheritance);
		return getPrimaryType(commonInheritance.getPivotClass());
	}

	@Override
	public boolean getCommonIsRequired(boolean leftIsRequired, boolean rightIsRequired) {
		return leftIsRequired && rightIsRequired;
	}

	@Override
	public @NonNull IdResolver getIdResolver() {
		IdResolver idResolver2 = idResolver;
		if (idResolver2 == null) {
			idResolver = idResolver2 = createIdResolver();
		}
		return idResolver2;
	}

	@Override
	public org.eclipse.ocl.pivot.@NonNull Class getJavaType(@NonNull Object object) {
		assert javaTypeManager != null;
		return javaTypeManager.getJavaType(object);
	}

	/**
	 * @since 7.0
	 */
	@Override
	public @NonNull JavaTypeManager getJavaTypeManager() {
		assert javaTypeManager != null;
		return javaTypeManager;
	}

	@Override
	public org.eclipse.ocl.pivot.@NonNull Class getLibraryClass(@NonNull String className) {
		return ClassUtil.requireNonNull(basicGetLibraryClass(className));
	}

	/**
	 * @since 7.0
	 */
	@Override
	public @NonNull MapType getMapEntryType(org.eclipse.ocl.pivot.@NonNull Class entryClass) {
		assert mapTypeManager != null;
		return mapTypeManager.getMapEntryType(entryClass);
	}

	/**
	 * @since 7.0
	 */
	@Override
	public @NonNull MapType getMapType(@NonNull Type keyType, boolean keyValuesAreNullFree, @NonNull Type valueType, boolean valuesAreNullFree) {
		MapTypeArguments typeArguments = new MapTypeArguments(keyType, keyValuesAreNullFree, valueType, valuesAreNullFree);
		return getMapType(typeArguments);
	}

	/**
	 * @since 7.0
	 */
	@Override
	public @NonNull MapType getMapType(@NonNull MapTypeArguments typeArguments) {
		assert mapTypeManager != null;
		return mapTypeManager.getMapType(typeArguments);
	}

	/**
	 * @since 7.0
	 */
	@Override
	public @NonNull MapTypeManager getMapTypeManager() {
		assert mapTypeManager != null;
		return mapTypeManager;
	}

//	@Override
	private org.eclipse.ocl.pivot.@NonNull Class getNormalizedType(@NonNull Type type) {
		if (type instanceof ReflectiveInheritance) {
			return ((ReflectiveInheritance)type).getPivotClass();
		}
		else if (type instanceof EcoreExecutorType) {
			return (EcoreExecutorType)type;
		}
		else {
			try {
				return type.getFlatClass(this).getPivotClass();
			}
			catch (Throwable e) {}
		}
		return getOclAnyType();			// FIXME should never happen;
	}

	@Override
	public @NonNull CollectionType getOrderedSetType(@NonNull Type elementType, boolean isNullFree, @Nullable IntegerValue lower, @Nullable UnlimitedNaturalValue upper) {
		return getCollectionType(getOrderedSetType(), elementType, isNullFree, lower, upper);
	}

	@Override
	public @Nullable Type getPrimitiveType(@NonNull PrimitiveTypeId typeId) {
		if (typeId == TypeId.BOOLEAN) {
			return getBooleanType();
		}
		else if (typeId == TypeId.INTEGER) {
			return getIntegerType();
		}
		else if (typeId == TypeId.REAL) {
			return getRealType();
		}
		else if (typeId == TypeId.STRING) {
			return getStringType();
		}
		else if (typeId == TypeId.UNLIMITED_NATURAL) {
			return getUnlimitedNaturalType();
		}
		else if (typeId == TypeId.OCL_ANY) {
			return getOclAnyType();
		}
		else if (typeId == TypeId.OCL_COMPARABLE) {
			return getOclComparableType();
		}
		else if (typeId == TypeId.OCL_ENUMERATION) {
			return getOclEnumerationType();
		}
		else if (typeId == TypeId.OCL_SELF) {
			return getOclSelfType();
		}
		else if (typeId == TypeId.OCL_SUMMABLE) {
			return getOclSummableType();
		}
		throw new UnsupportedOperationException();
	}

	@Override
	public @NonNull CollectionType getSequenceType(@NonNull Type elementType, boolean isNullFree, @Nullable IntegerValue lower, @Nullable UnlimitedNaturalValue upper) {
		return getCollectionType(getSequenceType(), elementType, isNullFree, lower, upper);
	}

	@Override
	public @NonNull CollectionType getSetType(@NonNull Type elementType, boolean isNullFree, @Nullable IntegerValue lower, @Nullable UnlimitedNaturalValue upper) {
		return getCollectionType(getSetType(), elementType, isNullFree, lower, upper);
	}

	@Override
	public @NonNull TupleType getTupleType(@NonNull Collection<@NonNull ? extends TypedElement> parts, @Nullable TemplateParameterSubstitutions bindings) {
		assert tupleTypeManager != null;
		return tupleTypeManager.getTupleType(parts, bindings);
	}

	@Override
	public @NonNull TupleType getTupleType(@NonNull List<@NonNull PartId> partIds) {
		assert tupleTypeManager != null;
		return tupleTypeManager.getTupleType(partIds);
	}

	@Override
	public @NonNull TupleType getTupleType(@NonNull TupleTypeId typeId) {
		assert tupleTypeManager != null;
		return tupleTypeManager.getTupleType(typeId);
	}

	/**
	 * @since 7.0
	 */
	@Override
	public @NonNull TupleTypeManager getTupleTypeManager() {
		assert tupleTypeManager != null;
		return tupleTypeManager;
	}


	@Override
	public boolean isEqualTo(@NonNull Type leftType, @NonNull Type rightType) {
		if (leftType == rightType) {
			return true;
		}
		else if (leftType instanceof CollectionType) {
			if (rightType instanceof CollectionType) {
				assert collectionTypeManager != null;
				return collectionTypeManager.isEqualToCollectionType((CollectionType)leftType, (CollectionType)rightType);
			}
			return false;
		}
/*		else if (leftType instanceof LambdaType) {
			if (rightType instanceof LambdaType) {
				throw new UnsupportedOperationException();			// XXX TODO FIXME
			}
			return getOclAnyType();
		} */
		else if (leftType instanceof MapType) {
			if (rightType instanceof MapType) {
				assert mapTypeManager != null;
				return mapTypeManager.isEqualToMapType((MapType)leftType, (MapType)rightType);
			}
			return false;
		}
		else if (leftType instanceof TupleType) {
			if (rightType instanceof TupleType) {
				assert tupleTypeManager != null;
				return tupleTypeManager.isEqualToTupleType((TupleType)leftType, (TupleType)rightType);
			}
			return false;
		}
		else if (leftType instanceof AbstractReflectiveInheritanceType) {
			AbstractReflectiveInheritanceType leftReflectiveType = (AbstractReflectiveInheritanceType)leftType;
			return leftReflectiveType.getPivotClass() == rightType;
		}
		Type thisType = getNormalizedType(leftType);
		Type thatType = getNormalizedType(rightType);
		return thisType == thatType;
	}

	protected void resetLibrary() {
	//	System.out.println("resetLibrary " + NameUtil.debugSimpleName(this));
		if (collectionTypeManager != null) {
			collectionTypeManager.dispose();
			collectionTypeManager = null;
		}
		if (idResolver != null) {
			idResolver.dispose();
			idResolver = null;
		}
		if (javaTypeManager != null) {
			javaTypeManager.dispose();
			javaTypeManager = null;
		}
		if (lambdaTypeManager != null) {
			lambdaTypeManager.dispose();
			lambdaTypeManager = null;
		}
		if (mapTypeManager != null) {
			mapTypeManager.dispose();
			mapTypeManager = null;
		}
		if (specializedTypeManager != null) {
			specializedTypeManager.dispose();
			specializedTypeManager = null;
		}
		if (tupleTypeManager != null) {
			tupleTypeManager.dispose();
			tupleTypeManager = null;
		}
	}
} //StandardLibraryImpl
