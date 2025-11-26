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
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.CollectionType;
import org.eclipse.ocl.pivot.DataType;
import org.eclipse.ocl.pivot.InvalidType;
import org.eclipse.ocl.pivot.LambdaType;
import org.eclipse.ocl.pivot.MapType;
import org.eclipse.ocl.pivot.Model;
import org.eclipse.ocl.pivot.PivotFactory;
import org.eclipse.ocl.pivot.PivotPackage;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.StandardLibrary;
import org.eclipse.ocl.pivot.TemplateParameter;
import org.eclipse.ocl.pivot.TemplateSignature;
import org.eclipse.ocl.pivot.TupleType;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.TypedElement;
import org.eclipse.ocl.pivot.VoidType;
import org.eclipse.ocl.pivot.flat.FlatClass;
import org.eclipse.ocl.pivot.flat.FlatModel;
import org.eclipse.ocl.pivot.ids.CollectionTypeId;
import org.eclipse.ocl.pivot.ids.IdManager;
import org.eclipse.ocl.pivot.ids.IdResolver;
import org.eclipse.ocl.pivot.ids.PartId;
import org.eclipse.ocl.pivot.ids.PrimitiveTypeId;
import org.eclipse.ocl.pivot.ids.TupleTypeId;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.manager.Orphanage;
import org.eclipse.ocl.pivot.internal.utilities.PivotConstantsInternal;
import org.eclipse.ocl.pivot.manager.CollectionTypeManager;
import org.eclipse.ocl.pivot.manager.JavaTypeManager;
import org.eclipse.ocl.pivot.manager.LambdaTypeManager;
import org.eclipse.ocl.pivot.manager.MapTypeManager;
import org.eclipse.ocl.pivot.manager.SpecializedTypeManager;
import org.eclipse.ocl.pivot.manager.TupleTypeManager;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.utilities.ValueUtil;
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
	//	System.out.println("ctor " + NameUtil.debugSimpleName(this));
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
	 * @since 7.0
	 */
	protected /*@NonNull*/ FlatModel flatModel = null;

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
		FlatClass leftFlatClass = leftPrimaryType.getFlatClass(this);
		FlatClass rightFlatClass = rightPrimaryType.getFlatClass(this);
		return leftFlatClass.isSubFlatClassOf(rightFlatClass);
	}

	/**
	 * @since 7.0
	 */
	protected abstract @NonNull CollectionTypeManager createCollectionTypeManager();

	/**
	 * @since 7.0
	 */
	protected abstract @NonNull FlatModel createFlatModel();

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
	public org.eclipse.ocl.pivot.@Nullable Class basicGetBehavioralClass(java.lang.@NonNull Class<?> javaClass) {
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
		assert genericType.getUnspecializedElement() == null;
		assert genericType.getOwnedSignature() != null;
		assert genericType.getOwnedSignature().getOwnedParameters().size() == 1;
		assert genericType.getOwnedSignature().getOwnedParameters().get(0).eClass() == PivotPackage.Literals.TEMPLATE_PARAMETER;
		assert PivotUtil.getUnspecializedTemplateableElement(genericType) == genericType;
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
				FlatClass leftFlatClass = leftType.getFlatClass(this);
				FlatClass rightFlatClass = rightType.getFlatClass(this);
				FlatClass commonFlatClass = leftFlatClass.getCommonFlatClass(rightFlatClass);
				return getPrimaryType(commonFlatClass.getPivotClass());
		//	}
		//	return getOclAnyType();
		}
	/*	if (conformsTo(leftType, leftSubstitutions, rightType, rightSubstitutions, false)) {		// malfunctions in testOperationDependencyAnalysis_Companies for a TemplateParameter as right
			return rightType;
		}
		if (conformsTo(rightType, rightSubstitutions, leftType, leftSubstitutions, false)) {
			return leftType;
		} */
		FlatClass leftFlatClass = leftType.getFlatClass(this);
		FlatClass rightFlatClass = rightType.getFlatClass(this);
		FlatClass commonFlatClass = leftFlatClass.getCommonFlatClass(rightFlatClass);
		return getPrimaryType(commonFlatClass.getPivotClass());
	}

	@Override
	public boolean getCommonIsRequired(boolean leftIsRequired, boolean rightIsRequired) {
		return leftIsRequired && rightIsRequired;
	}

	@Override
	public org.eclipse.ocl.pivot.@NonNull Class getEquivalentClass(@NonNull Model thisModel, org.eclipse.ocl.pivot.@NonNull Class thatClass) {
		// NB This may be called for an isolated xxxTables and so there are no CompleteClasses.
	//	CompleteClass completeClass = getCompleteClass(thatClass);					// Ensure thatPackage has a complete representation -- BUG 477342 once gave intermittent dispose() ISEs
		Model thatModel = PivotUtil.getContainingModel(thatClass);
		if ((thisModel == thatModel) || Orphanage.isOrphanage(thatModel)) {
			return thatClass;
		}
		org.eclipse.ocl.pivot.Package thatPackage = PivotUtil.getOwningPackage(thatClass);
		org.eclipse.ocl.pivot.Package thisPackage = getEquivalentPackage(thisModel, thatPackage);
		List<org.eclipse.ocl.pivot.Class> theseClasses = thisPackage.getOwnedClasses();
		String className = thatClass.getName();
	//	assert className != null;							// XXX Nameless classes such as UML Association cannot be opposites
		org.eclipse.ocl.pivot.Class thisClass = NameUtil.getNameable(theseClasses, className);
		if (thisClass != null) {
			return thisClass;
		}
		org.eclipse.ocl.pivot.Class asClass = thatClass; //completeClass.getPrimaryClass();
		thisClass = PivotUtil.createNamedElement(asClass);			// XXX what about template parameter??
		TemplateSignature thatSignature = asClass.getOwnedSignature();
		if (thatSignature != null) {
			TemplateSignature thisSignature = EcoreUtil.copy(thatSignature);
			thisClass.setOwnedSignature(thisSignature);
		}
		theseClasses.add(thisClass);
		//	System.out.println("getEquivalentClass " + NameUtil.debugSimpleName(thatClass) +  " => " + NameUtil.debugSimpleName(thisClass) +  " " + thisClass.getName());
		return thisClass;
	}

	@Override
	public org.eclipse.ocl.pivot.@NonNull Package getEquivalentPackage(@NonNull Model thisModel, org.eclipse.ocl.pivot.@NonNull Package thatPackage) {
		// NB This may be called for an isolated xxxTables and so there are no CompletePackages.
		Model thatModel = PivotUtil.basicGetContainingModel(thatPackage);
		if (thisModel == thatModel) {
			return thatPackage;
		}
		org.eclipse.ocl.pivot.Package thisParentPackage;
		org.eclipse.ocl.pivot.Package thatParentPackage = thatPackage.getOwningPackage();
		if (thatParentPackage == null) {
			thisParentPackage = Orphanage.getLocalOrphanPackage(thisModel);
		//	assert thisParentPackage.eResource().getResourceSet() != null; // xxxTables models have no Resource and so no ResourceSet
		}
		else {
			thisParentPackage = getEquivalentPackage(thisModel, thatParentPackage);
			assert thisParentPackage.eResource().getResourceSet() != null;
		}
		List<org.eclipse.ocl.pivot.@NonNull Package> thesePackages = PivotUtil.getOwnedPackagesList(thisParentPackage);
		String packageName = PivotUtil.getName(thatPackage);
		String packageURI = thatPackage.getURI();
		if (packageURI != null) {
			for (org.eclipse.ocl.pivot.@NonNull Package thisPackage : thesePackages) {
				if (packageURI.equals(thisPackage.getURI())) {
					return thisPackage;
				}
			}
		}
		for (org.eclipse.ocl.pivot.@NonNull Package thisPackage : thesePackages) {
			if (packageName.equals(thisPackage.getName())) {
				return thisPackage;
			}
		}
		if (packageURI == null) {
			packageURI = "";
		}
		org.eclipse.ocl.pivot.Package thisPackage = PivotUtil.createPackage(packageName, thatPackage.getNsPrefix(), packageURI, thatPackage.getPackageId());		// XXX
		thesePackages.add(thisPackage);
		return thisPackage;
	}

	/**
	 * @since 7.0
	 */
	@Override
	public @NonNull FlatModel getFlatModel() {
		FlatModel flatModel2 = flatModel;
		if (flatModel2 == null) {
			flatModel = flatModel2 = createFlatModel();
		}
		return flatModel2;
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
		try {
			return type.getFlatClass(this).getPivotClass();
		}
		catch (Throwable e) {}
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
	public @NonNull TupleType getTupleType(@NonNull Collection<@NonNull ? extends TypedElement> asParts, @Nullable TemplateParameterSubstitutions bindings) {
		assert tupleTypeManager != null;
		return tupleTypeManager.getTupleType(asParts, bindings);
	}

	@Override
	public @NonNull TupleType getTupleType(@Nullable List<@NonNull Property> asParts, @NonNull List<@NonNull PartId> partIds) {
		assert tupleTypeManager != null;
		return tupleTypeManager.getTupleType(asParts, partIds);
	}

	@Override
	public @NonNull TupleType getTupleType(@NonNull TupleTypeId typeId) {
		assert tupleTypeManager != null;
		return tupleTypeManager.getTupleType(null, typeId);
	}

	/**
	 * @since 7.0
	 */
	@Override
	public @NonNull TupleTypeManager getTupleTypeManager() {
		assert tupleTypeManager != null;
		return tupleTypeManager;
	}

	/**
	 * @since 7.0
	 */
	@Override
	public void installImplicitOppositeProperty(@NonNull Property thisProperty, @NonNull String oppositeName) {
		boolean isOrdered = PivotConstantsInternal.DEFAULT_IMPLICIT_OPPOSITE_ORDERED;
		boolean isUnique = PivotConstantsInternal.DEFAULT_IMPLICIT_OPPOSITE_UNIQUE;
		IntegerValue lower;
		UnlimitedNaturalValue upper;
		if (thisProperty.isIsComposite()) {
			lower = ValueUtil.ZERO_VALUE;
			upper = ValueUtil.UNLIMITED_ONE_VALUE;
		}
		else {
			lower = PivotConstantsInternal.DEFAULT_IMPLICIT_OPPOSITE_LOWER_VALUE;
			upper = PivotConstantsInternal.DEFAULT_IMPLICIT_OPPOSITE_UPPER_VALUE;
		}
		installOppositeProperty(thisProperty, oppositeName, isOrdered, isUnique, lower, upper);
	}

	@Override
	public void installOppositeProperty(@NonNull Property thisProperty, @NonNull String oppositeName,
			boolean isOrdered, boolean isUnique, @NonNull IntegerValue lower, @NonNull UnlimitedNaturalValue upper) {
		// A new CollectionType may be synthesized for the opposite so this method must access a StandardLibrary.
		//	It might as well be a StandardLibrary method. getEquivalentClass/Package sensibly are here too.
		assert thisProperty.getOpposite() == null;
		Type thatType = PivotUtil.getType(thisProperty);
		if (thatType instanceof CollectionType) {				// opposite can only be one collection deep
			thatType = PivotUtil.getElementType((CollectionType)thatType);
		}
		org.eclipse.ocl.pivot.Class thatClass = PivotUtil.getClass(thatType, this);
		if (thatClass instanceof DataType) {
			return;
		}
		org.eclipse.ocl.pivot.Class thisClass = PivotUtil.getOwningClass(thisProperty);
		assert thisClass == PivotUtil.getUnspecializedTemplateableElement(thisClass); //	thisClass = TemplateParameterSubstitutionVisitor.specializeTypeToLowerBound(thisClass, environmentFactory);
		Model thisModel = PivotUtil.getContainingModel(thisClass);
		org.eclipse.ocl.pivot.Class mutableThatClass = getEquivalentClass(thisModel, thatClass);
		Property newOpposite = PivotFactory.eINSTANCE.createProperty();
		newOpposite.setName(oppositeName);
		newOpposite.setIsImplicit(true);
		Type oppositeType;
		boolean isRequired;
		if (upper.equals(ValueUtil.UNLIMITED_ONE_VALUE)) {
			oppositeType = thisClass;
			isRequired = lower.equals(ValueUtil.ONE_VALUE);
		}
		else {
			CollectionTypeId genericCollectionTypeId = IdManager.getCollectionTypeId(isOrdered, isUnique);
			CollectionTypeArguments typeArguments = new CollectionTypeArguments(genericCollectionTypeId, thisClass, false, lower, upper);
			oppositeType = getCollectionType(typeArguments);
			isRequired = true;
		}
		newOpposite.setType(oppositeType);
		newOpposite.setIsRequired(isRequired);
		mutableThatClass.getOwnedProperties().add(newOpposite);
		newOpposite.setOpposite(thisProperty);
		thisProperty.setOpposite(newOpposite);
	//	System.out.println("installOppositeProperty: " + thisProperty + " # " + newOpposite);
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
