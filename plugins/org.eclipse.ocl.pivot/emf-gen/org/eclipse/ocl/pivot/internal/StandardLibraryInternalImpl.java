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
package org.eclipse.ocl.pivot.internal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.AnyType;
import org.eclipse.ocl.pivot.BagType;
import org.eclipse.ocl.pivot.BooleanType;
import org.eclipse.ocl.pivot.CollectionType;
import org.eclipse.ocl.pivot.Comment;
import org.eclipse.ocl.pivot.CompleteClass;
import org.eclipse.ocl.pivot.CompleteEnvironment;
import org.eclipse.ocl.pivot.CompleteInheritance;
import org.eclipse.ocl.pivot.CompletePackage;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.ElementExtension;
import org.eclipse.ocl.pivot.InvalidType;
import org.eclipse.ocl.pivot.Iteration;
import org.eclipse.ocl.pivot.LambdaParameter;
import org.eclipse.ocl.pivot.LambdaType;
import org.eclipse.ocl.pivot.MapType;
import org.eclipse.ocl.pivot.NormalizedTemplateParameter;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.OrderedSetType;
import org.eclipse.ocl.pivot.Package;
import org.eclipse.ocl.pivot.Parameter;
import org.eclipse.ocl.pivot.PivotFactory;
import org.eclipse.ocl.pivot.PivotPackage;
import org.eclipse.ocl.pivot.PrimitiveType;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.SelfType;
import org.eclipse.ocl.pivot.SequenceType;
import org.eclipse.ocl.pivot.SetType;
import org.eclipse.ocl.pivot.StandardLibraryInternal;
import org.eclipse.ocl.pivot.TemplateParameter;
import org.eclipse.ocl.pivot.TemplateSignature;
import org.eclipse.ocl.pivot.TemplateableElement;
import org.eclipse.ocl.pivot.TupleType;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.TypedElement;
import org.eclipse.ocl.pivot.VoidType;
import org.eclipse.ocl.pivot.ids.CollectionTypeId;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.complete.CompleteModelInternal;
import org.eclipse.ocl.pivot.internal.manager.AbstractCollectionTypeManager;
import org.eclipse.ocl.pivot.internal.manager.AbstractMapTypeManager;
import org.eclipse.ocl.pivot.internal.manager.BasicTemplateSpecialization;
import org.eclipse.ocl.pivot.internal.manager.CollectionTypeManager;
import org.eclipse.ocl.pivot.internal.manager.LambdaTypeManager;
import org.eclipse.ocl.pivot.internal.manager.MapTypeManager;
import org.eclipse.ocl.pivot.internal.manager.Orphanage;
import org.eclipse.ocl.pivot.internal.manager.SpecializationManager;
import org.eclipse.ocl.pivot.internal.manager.SpecializationManagerInternal;
import org.eclipse.ocl.pivot.internal.manager.TemplateParameterization;
import org.eclipse.ocl.pivot.internal.manager.TupleTypeManager;
import org.eclipse.ocl.pivot.internal.manager.TupleTypeManagerInternal;
import org.eclipse.ocl.pivot.internal.messages.PivotMessagesInternal;
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal;
import org.eclipse.ocl.pivot.internal.utilities.IllegalLibraryException;
import org.eclipse.ocl.pivot.library.LibraryConstants;
import org.eclipse.ocl.pivot.library.oclany.OclAnyUnsupportedOperation;
import org.eclipse.ocl.pivot.util.Visitor;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.MetamodelManager;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.PivotConstants;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.values.CollectionTypeArguments;
import org.eclipse.ocl.pivot.values.IntegerValue;
import org.eclipse.ocl.pivot.values.MapTypeArguments;
import org.eclipse.ocl.pivot.values.TemplateParameterSubstitutions;
import org.eclipse.ocl.pivot.values.UnlimitedNaturalValue;
import org.eclipse.osgi.util.NLS;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Standard Library Internal</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.pivot.internal.StandardLibraryInternalImpl#getOwningCompleteEnvironment <em>Owning Complete Environment</em>}</li>
 * </ul>
 *
 * @generated
 */
public class StandardLibraryInternalImpl extends StandardLibraryImpl implements StandardLibraryInternal
{
	/**
	 * The number of structural features of the '<em>Standard Library Internal</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int STANDARD_LIBRARY_INTERNAL_FEATURE_COUNT = StandardLibraryImpl.STANDARD_LIBRARY_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Standard Library Internal</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int STANDARD_LIBRARY_INTERNAL_OPERATION_COUNT = StandardLibraryImpl.STANDARD_LIBRARY_OPERATION_COUNT + 0;


	/**
	 * The cached value of the '{@link #getOwningCompleteEnvironment() <em>Owning Complete Environment</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwningCompleteEnvironment()
	 * @generated
	 * @ordered
	 */
	protected CompleteEnvironment owningCompleteEnvironment;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected StandardLibraryInternalImpl()
	{
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass()
	{
		return PivotPackage.Literals.STANDARD_LIBRARY_INTERNAL;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public CompleteEnvironment getOwningCompleteEnvironment()
	{
		if (owningCompleteEnvironment != null && owningCompleteEnvironment.eIsProxy())
		{
			InternalEObject oldOwningCompleteEnvironment = (InternalEObject)owningCompleteEnvironment;
			owningCompleteEnvironment = (CompleteEnvironment)eResolveProxy(oldOwningCompleteEnvironment);
			if (owningCompleteEnvironment != oldOwningCompleteEnvironment)
			{
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, 4, oldOwningCompleteEnvironment, owningCompleteEnvironment));
			}
		}
		return owningCompleteEnvironment;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CompleteEnvironment basicGetOwningCompleteEnvironment()
	{
		return owningCompleteEnvironment;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setOwningCompleteEnvironment(CompleteEnvironment newOwningCompleteEnvironment)
	{
		CompleteEnvironment oldOwningCompleteEnvironment = owningCompleteEnvironment;
		owningCompleteEnvironment = newOwningCompleteEnvironment;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, 4, oldOwningCompleteEnvironment, owningCompleteEnvironment));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType)
	{
		switch (featureID)
		{
			case 0:
				return getAnnotatingComments();
			case 1:
				return getOwnedAnnotations();
			case 2:
				return getOwnedComments();
			case 3:
				return getOwnedExtensions();
			case 4:
				if (resolve) return getOwningCompleteEnvironment();
				return basicGetOwningCompleteEnvironment();
		}
		return eDynamicGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue)
	{
		switch (featureID)
		{
			case 0:
				getAnnotatingComments().clear();
				getAnnotatingComments().addAll((Collection<? extends Comment>)newValue);
				return;
			case 1:
				getOwnedAnnotations().clear();
				getOwnedAnnotations().addAll((Collection<? extends Element>)newValue);
				return;
			case 2:
				getOwnedComments().clear();
				getOwnedComments().addAll((Collection<? extends Comment>)newValue);
				return;
			case 3:
				getOwnedExtensions().clear();
				getOwnedExtensions().addAll((Collection<? extends ElementExtension>)newValue);
				return;
			case 4:
				setOwningCompleteEnvironment((CompleteEnvironment)newValue);
				return;
		}
		eDynamicSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID)
	{
		switch (featureID)
		{
			case 0:
				getAnnotatingComments().clear();
				return;
			case 1:
				getOwnedAnnotations().clear();
				return;
			case 2:
				getOwnedComments().clear();
				return;
			case 3:
				getOwnedExtensions().clear();
				return;
			case 4:
				setOwningCompleteEnvironment((CompleteEnvironment)null);
				return;
		}
		eDynamicUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID)
	{
		switch (featureID)
		{
			case 0:
				return annotatingComments != null && !annotatingComments.isEmpty();
			case 1:
				return ownedAnnotations != null && !ownedAnnotations.isEmpty();
			case 2:
				return ownedComments != null && !ownedComments.isEmpty();
			case 3:
				return ownedExtensions != null && !ownedExtensions.isEmpty();
			case 4:
				return owningCompleteEnvironment != null;
		}
		return eDynamicIsSet(featureID);
	}

	/**
	 * {@inheritDoc}
	 * @generated
	 */
	@Override
	public <R> R accept(@NonNull Visitor<R> visitor) {
		return visitor.visitStandardLibraryInternal(this);
	}

	/**
	 * CollectionTypeManagerInternal encapsulates the knowledge about known collection types.
	 *
	 * @since 7.0
	 */
	public static class CollectionTypeManagerInternal extends AbstractCollectionTypeManager
	{
		protected final @NonNull CompleteModelInternal completeModel;

		public CollectionTypeManagerInternal(@NonNull CompleteModelInternal completeModel) {
			super(completeModel.getStandardLibrary());
			this.completeModel = completeModel;
		}

		@Override
		protected @NonNull CollectionType createCollectionType(@NonNull CollectionTypeArguments typeArguments) {
			CollectionTypeId collectionTypeId = typeArguments.getCollectionTypeId();
			Type elementType = typeArguments.getElementType();
			boolean isNullFree = typeArguments.isNullFree();
			IntegerValue lower = typeArguments.getLower();
			UnlimitedNaturalValue upper = typeArguments.getUpper();
			CollectionType genericCollectionType = completeModel.getStandardLibrary().getCollectionType(collectionTypeId);
			CollectionType collectionType = PivotUtil.createCollectionType(genericCollectionType, elementType, isNullFree, lower, upper);
			completeModel.resolveSuperClasses(collectionType, genericCollectionType);
			Orphanage orphanage = completeModel.getOrphanage();
			collectionType.setOwningPackage(orphanage);
			return collectionType;
		}

		@Override
		protected boolean isValid(@Nullable Type type) {
			return (type != null) && (type.eResource() != null);
		}
	}

	/**
	 * MapTypeManagerInternal encapsulates the knowledge about known map types.
	 *
	 * @since 7.0
	 */
	public static class MapTypeManagerInternal extends AbstractMapTypeManager
	{
		protected final @NonNull CompleteModelInternal completeModel;

		public MapTypeManagerInternal(@NonNull CompleteModelInternal completeModel) {
			super(completeModel.getStandardLibrary().getMapType());
			this.completeModel = completeModel;
		}

		@Override
		protected @NonNull MapType createMapType(@NonNull MapTypeArguments typeArguments, org.eclipse.ocl.pivot.@Nullable Class entryClass) {
			Type keyType = typeArguments.getKeyType();
			boolean keysAreNullFree = typeArguments.isKeysAreNullFree();
			Type valueType = typeArguments.getValueType();
			boolean valuesAreNullFree = typeArguments.isValuesAreNullFree();
			MapType mapType;
			if (entryClass == null) {
				mapType = PivotUtil.createMapType(genericMapType, keyType, keysAreNullFree, valueType, valuesAreNullFree);
			}
			else {
				MapType specializedMapType = getMapType(typeArguments);
				mapType = PivotUtil.createMapEntryType(specializedMapType, entryClass);
			}
			completeModel.resolveSuperClasses(mapType, genericMapType);
			Orphanage orphanage = completeModel.getOrphanage();
			mapType.setOwningPackage(orphanage);
			return mapType;
		}

		@Override
		protected boolean isValid(@Nullable Type type) {
			return (type != null) && (type.eResource() != null);
		}
	}

	private static final Logger logger = Logger.getLogger(StandardLibraryInternal.class);

	/**
	 * The URI used by default for the OCL Standard Library. NB. This
	 * constant is repeated in GenerateOCLstdlibModel.mwe2 and in
	 * org.eclipse.ocl.pivot/plugin.xml.
	 */
	public static final @NonNull String DEFAULT_OCL_STDLIB_URI = LibraryConstants.STDLIB_URI;

	/**
	 * The URI to provide the default Standard Library. This value may be
	 * reassigned pior to any OCL analysis or evaluation to select a different
	 * default. Alternatively the need for default may be bypassed by explicitly
	 * invoking loadLibrary().
	 */
	protected @NonNull String defaultStandardLibraryURI = DEFAULT_OCL_STDLIB_URI;

	protected boolean explicitDefaultStandardLibraryURI = false;

	private @Nullable BagType bagType = null;
	private @Nullable BooleanType booleanType = null;
	private org.eclipse.ocl.pivot.@Nullable Class classType = null;
	private @Nullable CollectionType collectionType = null;
	private org.eclipse.ocl.pivot.@Nullable Class enumerationType = null;
	private @Nullable PrimitiveType integerType = null;
	private @Nullable MapType mapType = null;
	private @Nullable AnyType oclAnyType = null;
	private org.eclipse.ocl.pivot.@Nullable Class oclComparableType = null;
	private org.eclipse.ocl.pivot.@Nullable Class oclElementType = null;
	private org.eclipse.ocl.pivot.@Nullable Class oclEnumerationType = null;
	private @Nullable Operation oclInvalidOperation = null;
	private @Nullable Property oclInvalidProperty = null;
	private @Nullable InvalidType oclInvalidType = null;
	private org.eclipse.ocl.pivot.@Nullable Class oclLambdaType = null;
	private @Nullable SelfType oclSelfType = null;
	private org.eclipse.ocl.pivot.@Nullable Class oclStereotypeType = null;
	private org.eclipse.ocl.pivot.@Nullable Class oclSummableType = null;
	private org.eclipse.ocl.pivot.@Nullable Class oclTupleType = null;
	private org.eclipse.ocl.pivot.@Nullable Class oclTypeType = null;
	private @Nullable VoidType oclVoidType = null;
	private @Nullable CollectionType orderedCollectionType = null;
	private @Nullable OrderedSetType orderedSetType = null;
	private @Nullable PrimitiveType realType = null;
	private @Nullable SequenceType sequenceType = null;
	private @Nullable SetType setType = null;
	private @Nullable PrimitiveType stringType = null;
	private @Nullable CollectionType uniqueCollectionType = null;
	private @Nullable PrimitiveType unlimitedNaturalType = null;

	private org.eclipse.ocl.pivot.@Nullable Package libraryPackage = null;

	/**
	 * The known lambda types.
	 */
	private @Nullable LambdaTypeManager lambdaManager = null;			// Lazily created

	/**
	 * The known tuple types.
	 */
//	private @Nullable TupleTypeManagerInternal tupleManager = null;			// Lazily created

	private @Nullable Map<@NonNull String, org.eclipse.ocl.pivot.@NonNull Class> nameToLibraryTypeMap = null;

	private /*final*/ /*@NonNull*/ CompleteModelInternal completeModel;
	private /*final*/ /*@NonNull*/ EnvironmentFactoryInternal environmentFactory;
	private @Nullable SpecializationManager specializationManager = null;

	@Override
	protected @Nullable Type basicGetBehavioralType(@NonNull Type type) {
		CompleteClass completeClass = completeModel.getCompleteClass(type);
		Type behavioralType = completeClass.getBehavioralClass();
		return behavioralType;
	}

	@Override
	public @Nullable AnyType basicGetOclAnyType() {
		return oclAnyType;
	}

	@Override
	public @Nullable Operation basicGetOclInvalidOperation() {
		return oclInvalidOperation;
	}

	@Override
	public @Nullable Property basicGetOclInvalidProperty() {
		return oclInvalidProperty;
	}

	@Override
	public @Nullable InvalidType basicGetOclInvalidType() {
		return oclInvalidType;
	}

	@Override
	public int compareOperationMatches(@NonNull Operation referenceOperation, @NonNull TemplateParameterSubstitutions referenceBindings,
			@NonNull Operation candidateOperation, @NonNull TemplateParameterSubstitutions candidateBindings) {
		if ((referenceOperation instanceof Iteration) && (candidateOperation instanceof Iteration)) {
			int iteratorCountDelta = ((Iteration)candidateOperation).getOwnedIterators().size() - ((Iteration)referenceOperation).getOwnedIterators().size();
			if (iteratorCountDelta != 0) {
				return iteratorCountDelta;
			}
			org.eclipse.ocl.pivot.Class referenceClass = referenceOperation.getOwningClass();
			org.eclipse.ocl.pivot.Class candidateClass = candidateOperation.getOwningClass();
			Type referenceType = referenceClass != null ? PivotUtil.getBehavioralType(referenceClass) : null;
			Type candidateType = candidateClass != null ? PivotUtil.getBehavioralType(candidateClass) : null;
			Type specializedReferenceType = referenceType != null ? getSpecializedType(referenceType, referenceBindings) : null;
			Type specializedCandidateType = candidateType != null ? getSpecializedType(candidateType, candidateBindings) : null;
			if ((referenceType != candidateType) && (specializedReferenceType != null) && (specializedCandidateType != null)) {
				if (conformsTo(specializedReferenceType, referenceBindings, specializedCandidateType, candidateBindings)) {
					return 1;
				}
				else if (conformsTo(specializedCandidateType, candidateBindings, specializedReferenceType, referenceBindings)) {
					return -1;
				}
			}
		}
		List<Parameter> candidateParameters = candidateOperation.getOwnedParameters();
		List<Parameter> referenceParameters = referenceOperation.getOwnedParameters();
		int parameterCountDelta = candidateParameters.size() - referenceParameters.size();
		if (parameterCountDelta != 0) {
			return parameterCountDelta;
		}
		boolean referenceConformsToCandidate = true;
		boolean candidateConformsToReference = true;
		for (int i = 0; i < candidateParameters.size(); i++) {
			Parameter referenceParameter = referenceParameters.get(i);
			Parameter candidateParameter = candidateParameters.get(i);
			if ((referenceParameter == null) || (candidateParameter == null)) {					// Doesn't happen (just a spurious NPE guard)
				referenceConformsToCandidate = false;
				candidateConformsToReference = false;
			}
			else {
				Type referenceType = PivotUtil.getType(referenceParameter);
				Type candidateType = PivotUtil.getType(candidateParameter);
				Type specializedReferenceType = getSpecializedType(referenceType, referenceBindings);
				Type specializedCandidateType = getSpecializedType(candidateType, candidateBindings);
				if (referenceType != candidateType) {
					if (!conformsTo(specializedReferenceType, referenceBindings, specializedCandidateType, candidateBindings)) {
						referenceConformsToCandidate = false;
					}
					if (!conformsTo(specializedCandidateType, candidateBindings, specializedReferenceType, referenceBindings)) {
						candidateConformsToReference = false;
					}
				}
			}
		}
		if (referenceConformsToCandidate != candidateConformsToReference) {
			return referenceConformsToCandidate ? 1 : -1;
		}
		Type referenceType = PivotUtil.getOwningClass(referenceOperation);
		Type candidateType = PivotUtil.getOwningClass(candidateOperation);
		Type specializedReferenceType = getSpecializedType(referenceType, referenceBindings);
		Type specializedCandidateType = getSpecializedType(candidateType, candidateBindings);
		if (referenceType != candidateType) {
			if (conformsTo(specializedReferenceType, referenceBindings, specializedCandidateType, candidateBindings)) {
				return 1;
			}
			else if (conformsTo(specializedCandidateType, candidateBindings, specializedReferenceType, referenceBindings)) {
				return -1;
			}
		}
		return 0;
	}

	@Override
	protected boolean conformsToType(@NonNull Type firstType, @NonNull TemplateParameterSubstitutions firstSubstitutions,
			@NonNull Type secondType, @NonNull TemplateParameterSubstitutions secondSubstitutions) {	// XXX substitutions not used
		if (firstType != secondType) {
			CompleteClass firstContainerCompleteClass = completeModel.getCompleteClass(firstType);
			CompleteClass secondContainerCompleteClass = completeModel.getCompleteClass(secondType);
			CompleteInheritance firstContainerInheritance = firstContainerCompleteClass.getCompleteInheritance();
			CompleteInheritance secondContainerInheritance = secondContainerCompleteClass.getCompleteInheritance();
			if (!firstContainerInheritance.isSubInheritanceOf(secondContainerInheritance)) {
				return false;
			}
		}
		return true;
	}

	@Override
	protected @NonNull CollectionTypeManager createCollectionTypeManager() {
		assert completeModel != null;
		return new CollectionTypeManagerInternal(completeModel);
	}

	@Override
	protected @NonNull MapTypeManager createMapTypeManager() {
		assert completeModel != null;
		return new MapTypeManagerInternal(completeModel);
	}

	@Override
	protected @NonNull TupleTypeManager createTupleTypeManager() {
		assert environmentFactory != null;
		return new TupleTypeManagerInternal(environmentFactory);
	}

	@Override
	public void defineLibraryTypes(@NonNull Iterable<org.eclipse.ocl.pivot.@NonNull Class> pivotTypes) {
		Map<@NonNull String, org.eclipse.ocl.pivot.@NonNull Class> nameToLibraryTypeMap2 = nameToLibraryTypeMap;
		if (nameToLibraryTypeMap2 == null) {
			nameToLibraryTypeMap = nameToLibraryTypeMap2 = new HashMap<>();
		}
		for (org.eclipse.ocl.pivot.@NonNull Class pivotType : pivotTypes) {
			String name = PivotUtil.getName(pivotType);
			org.eclipse.ocl.pivot.Class oldType = nameToLibraryTypeMap2.put(name, pivotType);
			if ((oldType != null) && (oldType != pivotType)) {
				if (!(oldType instanceof PrimitiveType) || !(pivotType instanceof PrimitiveType)) {		// User primitives may only be DataType e.g. testQVTrLoad_ATL2QVTr_qvtre
					logger.warn("Conflicting pivot type '" + name + "'");
				}
			}
		}
	}

	@Override
	public void dispose() {
		resetLibrary();
	}

	/**
	 * Return the pivot model class for className with the Pivot Model.
	 */
	@Override
	public org.eclipse.ocl.pivot.@Nullable Class getASClass(@NonNull String className) {
		assert environmentFactory != null;
		return environmentFactory.getMetamodelManager().getASClass(className);
	}

	@Override
	public @NonNull Iterable<@NonNull ? extends CompletePackage> getAllCompletePackages() {
		assert environmentFactory != null;
		return environmentFactory.getMetamodelManager().getAllCompletePackages();
	}

	@Override
	public @NonNull BagType getBagType() {
		BagType bagType2 = bagType;
		if (bagType2 == null) {
			bagType2 = bagType = resolveRequiredTemplateableType(BagType.class, TypeId.BAG_NAME, 1);
		}
		return bagType2;
	}

	@Override
	public @Nullable PrimitiveType getBehavioralClass(@NonNull Class<?> instanceClass) {
		return (PrimitiveType)PivotUtil.getBehavioralClass(this, instanceClass);
	}

	@Override
	public @NonNull BooleanType getBooleanType() {
		BooleanType booleanType2 = booleanType;
		if (booleanType2 == null) {
			booleanType2 = booleanType = resolveRequiredSimpleType(BooleanType.class, TypeId.BOOLEAN_NAME);
		}
		return booleanType2;
	}

	@Override
	public org.eclipse.ocl.pivot.@NonNull Class getClassType() {
		org.eclipse.ocl.pivot.Class classType2 = classType;
		if (classType2 == null) {
			classType2 = classType = resolveRequiredSimpleType(org.eclipse.ocl.pivot.Class.class, TypeId.CLASS_NAME);
		}
		return classType2;
	}

	@Override
	public @NonNull CollectionType getCollectionType() {
		CollectionType collectionType2 = collectionType;
		if (collectionType2 == null) {
			collectionType2 = collectionType = resolveRequiredTemplateableType(CollectionType.class, TypeId.COLLECTION_NAME, 1);
		}
		return collectionType2;
	}

	@Override
	public @NonNull CollectionType getCollectionType(@NonNull CollectionType genericType,
			@NonNull Type elementType, boolean isNullFree, @Nullable IntegerValue lower, @Nullable UnlimitedNaturalValue upper) {
		assert environmentFactory != null;
		MetamodelManager metamodelManager = environmentFactory.getMetamodelManager();
		genericType = (CollectionType) metamodelManager.getPrimaryClass(genericType);
		elementType = metamodelManager.getPrimaryType(elementType);
		assert genericType == PivotUtil.getUnspecializedTemplateableElement(genericType);
	//	CompleteClassInternal completeClass = completeModel.getCompleteClass(genericType);
	//	if (isUnspecializedType(completeClass, elementType)) {
	//		return genericType;
	//	}
		return super.getCollectionType(genericType, elementType, isNullFree, lower, upper);
	}

	@Override
	public @NonNull CompleteModelInternal getCompleteModel() {
		assert completeModel != null;
		return completeModel;
	}

	@Override
	public @NonNull String getDefaultStandardLibraryURI() {
		return defaultStandardLibraryURI;
	}

	@Override
	public org.eclipse.ocl.pivot.@NonNull Class getEnumerationType() {
		org.eclipse.ocl.pivot.Class enumerationType2 = enumerationType;
		if (enumerationType2 == null) {
			enumerationType2 = enumerationType = resolveRequiredSimpleType(org.eclipse.ocl.pivot.Class.class, TypeId.ENUMERATION_NAME);
		}
		return enumerationType2;
	}

	/**
	 * @since 7.0
	 */
	@Override
	public @NonNull EnvironmentFactoryInternal getEnvironmentFactory() {
		return ClassUtil.requireNonNull(environmentFactory);
	}

	@Override
	@NonNull
	public CompleteInheritance getInheritance(org.eclipse.ocl.pivot.@NonNull Class type) {
		assert environmentFactory != null;
		return environmentFactory.getMetamodelManager().getInheritance(type);
	}

	@Override
	public @NonNull PrimitiveType getIntegerType() {
		PrimitiveType integerType2 = integerType;
		if (integerType2 == null) {
			integerType2 = integerType = resolveRequiredSimpleType(PrimitiveType.class, TypeId.INTEGER_NAME);
		}
		return integerType2;
	}

	@Override
	public @NonNull LambdaTypeManager getLambdaManager() {
		LambdaTypeManager lambdaManager2 = lambdaManager;
		if (lambdaManager2 == null) {
			assert environmentFactory != null;
			lambdaManager2 = lambdaManager = new LambdaTypeManager(environmentFactory);
		}
		return lambdaManager2;
	}

	@Override
	public @NonNull LambdaType getLambdaType(@NonNull String typeName, @NonNull TypedElement contextType, @NonNull List<@NonNull ? extends TypedElement> parameterTypes, @NonNull TypedElement resultType,
			@Nullable TemplateParameterSubstitutions bindings) {
		return getLambdaManager().getLambdaType(typeName, contextType, parameterTypes, resultType, bindings);
	}

	@Override
	public org.eclipse.ocl.pivot.Class getLibraryType(@NonNull String typeName) {
		Map<@NonNull String, org.eclipse.ocl.pivot.@NonNull Class> nameToLibraryTypeMap2 = nameToLibraryTypeMap;
		if (nameToLibraryTypeMap2 == null) {
			nameToLibraryTypeMap = nameToLibraryTypeMap2 = new HashMap<>();
			loadDefaultLibrary(defaultStandardLibraryURI);
		}
		return nameToLibraryTypeMap2.get(typeName);
	}

	/**
	 * @since 7.0
	 */
	@Override
	public @NonNull MapType getMapEntryType(org.eclipse.ocl.pivot.@NonNull Class entryClass) {
		assert !entryClass.eIsProxy();
		return super.getMapEntryType(entryClass);
	}

	@Override
	public @NonNull MapType getMapType() {
		MapType mapType2 = mapType;
		if (mapType2 == null) {
			mapType2 = mapType = resolveRequiredTemplateableType(MapType.class, TypeId.MAP_NAME, 2);
		}
		return mapType2;
	}

	/**
	 * @since 7.0
	 */
	@Override
	public @NonNull MapType getMapType(@NonNull Type keyType, boolean keysAreNullFree, @NonNull Type valueType, boolean valuesAreNullFree) {
		MapType mapType = getMapType();
		assert mapType == PivotUtil.getUnspecializedTemplateableElement(mapType);
		if ((keyType == mapType.getKeyType()) && (valueType == mapType.getValueType())) {
			return mapType;		// XXX ??? never happens now that NormalizedTemplatedParameter in use
		}
		return super.getMapType(keyType, keysAreNullFree, valueType, valuesAreNullFree);
	}

	@Override
	public org.eclipse.ocl.pivot.Package getNsURIPackage(@NonNull String nsURI) {
		CompletePackage completePackage = completeModel.getCompletePackageByURI(nsURI);
		return completePackage != null ? completePackage.getPrimaryPackage() : null;
	}

	@Override
	public @NonNull AnyType getOclAnyType() {
		AnyType oclAnyType2 = oclAnyType;
		if (oclAnyType2 == null) {
			oclAnyType2 = oclAnyType = resolveRequiredSimpleType(AnyType.class, TypeId.OCL_ANY_NAME);
		}
		return oclAnyType2;
	}

	@Override
	public org.eclipse.ocl.pivot.@NonNull Class getOclComparableType() {
		org.eclipse.ocl.pivot.Class oclComparableType2 = oclComparableType;
		if (oclComparableType2 == null) {
			oclComparableType2 = oclComparableType = resolveRequiredSimpleType(org.eclipse.ocl.pivot.Class.class, TypeId.OCL_COMPARABLE_NAME);
		}
		return oclComparableType2;
	}

	@Override
	public org.eclipse.ocl.pivot.@NonNull Class getOclElementType() {
		org.eclipse.ocl.pivot.Class oclElementType2 = oclElementType;
		if (oclElementType2 == null) {
			oclElementType2 = oclElementType = resolveRequiredSimpleType(org.eclipse.ocl.pivot.Class.class, TypeId.OCL_ELEMENT_NAME);
		}
		return oclElementType2;
	}

	/**
	 * @since 1.1
	 */
	@Override
	public org.eclipse.ocl.pivot.@NonNull Class getOclEnumerationType() {
		org.eclipse.ocl.pivot.Class oclEnumerationType2 = oclEnumerationType;
		if (oclEnumerationType2 == null) {
			oclEnumerationType2 = oclEnumerationType = resolveRequiredSimpleType(org.eclipse.ocl.pivot.Class.class, TypeId.OCL_ENUMERATION_NAME);
		}
		return oclEnumerationType2;
	}

	@Override
	public @NonNull Operation getOclInvalidOperation() {
		Operation oclInvalidOperation2 = oclInvalidOperation;
		if (oclInvalidOperation2 == null) {
			AnyType anyType = getOclAnyType();
			InvalidType invalidType = getOclInvalidType();
			List<Operation> invalidOperations = invalidType.getOwnedOperations();
			String invalidName = "oclBadOperation";
			oclInvalidOperation2 = NameUtil.getNameable(invalidOperations, invalidName);
			if (oclInvalidOperation2 == null) {
				oclInvalidOperation2 = PivotFactory.eINSTANCE.createOperation();
				oclInvalidOperation2.setName(invalidName);
				oclInvalidOperation2.setType(anyType);
				oclInvalidOperation2.setImplementation(OclAnyUnsupportedOperation.INSTANCE);
				invalidOperations.add(oclInvalidOperation2);
			}
			oclInvalidOperation = oclInvalidOperation2;
		}
		return oclInvalidOperation2;
	}

	@Override
	public @NonNull Property getOclInvalidProperty() {
		Property oclInvalidProperty2 = oclInvalidProperty;
		if (oclInvalidProperty2 == null) {
			AnyType anyType = getOclAnyType();
			InvalidType invalidType = getOclInvalidType();
			List<Property> invalidProperties = invalidType.getOwnedProperties();
			String invalidName = "oclBadProperty";
			oclInvalidProperty2 = NameUtil.getNameable(invalidProperties, invalidName);
			if (oclInvalidProperty2 == null) {
				oclInvalidProperty2 = PivotFactory.eINSTANCE.createProperty();
				oclInvalidProperty2.setName(invalidName);
				oclInvalidProperty2.setType(anyType);
				oclInvalidProperty2.setImplementation(OclAnyUnsupportedOperation.INSTANCE);
				invalidProperties.add(oclInvalidProperty2);
			}
			oclInvalidProperty = oclInvalidProperty2;
		}
		return oclInvalidProperty2;
	}

	@Override
	public @NonNull InvalidType getOclInvalidType() {
		InvalidType oclInvalidType2 = oclInvalidType;
		if (oclInvalidType2 == null) {
			oclInvalidType2 = oclInvalidType = resolveRequiredSimpleType(InvalidType.class, TypeId.OCL_INVALID_NAME);
		}
		return oclInvalidType2;
	}

	@Override
	public org.eclipse.ocl.pivot.@NonNull Class getOclLambdaType() {
		org.eclipse.ocl.pivot.Class oclLambdaType2 = oclLambdaType;
		if (oclLambdaType2 == null) {
			oclLambdaType2 = oclLambdaType = resolveRequiredSimpleType(org.eclipse.ocl.pivot.Class.class, "OclLambda");
		}
		return oclLambdaType2;
	}

	@Override
	public org.eclipse.ocl.pivot.@NonNull Class getOclMessageType() {
		return getRequiredLibraryType("OclMessage");
	}

	@Override
	public @NonNull SelfType getOclSelfType() {
		SelfType oclSelfType2 = oclSelfType;
		if (oclSelfType2 == null) {
			oclSelfType2 = oclSelfType = resolveRequiredSimpleType(SelfType.class, TypeId.OCL_SELF_NAME);
		}
		return oclSelfType2;
	}

	/**
	 * @since 1.1
	 */
	@Override
	public org.eclipse.ocl.pivot.@NonNull Class getOclStereotypeType() {
		org.eclipse.ocl.pivot.Class oclStereotypeType2 = oclStereotypeType;
		if (oclStereotypeType2 == null) {
			oclStereotypeType2 = oclStereotypeType = resolveRequiredSimpleType(org.eclipse.ocl.pivot.Class.class, TypeId.OCL_STEREOTYPE_NAME);
		}
		return oclStereotypeType2;
	}

	@Override
	public org.eclipse.ocl.pivot.@NonNull Class getOclSummableType() {
		org.eclipse.ocl.pivot.Class oclSummableType2 = oclSummableType;
		if (oclSummableType2 == null) {
			oclSummableType2 = oclSummableType = resolveRequiredSimpleType(org.eclipse.ocl.pivot.Class.class, TypeId.OCL_SUMMABLE_NAME);
		}
		return oclSummableType2;
	}

	@Override
	public org.eclipse.ocl.pivot.@NonNull Class getOclTupleType() {
		org.eclipse.ocl.pivot.Class oclTupleType2 = oclTupleType;
		if (oclTupleType2 == null) {
			oclTupleType2 = oclTupleType = resolveRequiredSimpleType(org.eclipse.ocl.pivot.Class.class, TypeId.OCL_TUPLE_NAME);
		}
		return oclTupleType2;
	}

	@Override
	public org.eclipse.ocl.pivot.@NonNull Class getOclTypeType() {
		org.eclipse.ocl.pivot.Class oclTypeType2 = oclTypeType;
		if (oclTypeType2 == null) {
			oclTypeType2 = oclTypeType = resolveRequiredSimpleType(org.eclipse.ocl.pivot.Class.class, TypeId.OCL_TYPE_NAME);
		}
		return oclTypeType2;
	}

	@Override
	public @NonNull VoidType getOclVoidType() {
		VoidType oclVoidType2 = oclVoidType;
		if (oclVoidType2 == null) {
			oclVoidType2 = oclVoidType = resolveRequiredSimpleType(VoidType.class, TypeId.OCL_VOID_NAME);
		}
		return oclVoidType2;
	}

	@Override
	public @NonNull CollectionType getOrderedCollectionType() {
		CollectionType orderedCollectionType2 = orderedCollectionType;
		if (orderedCollectionType2 == null) {
			orderedCollectionType2 = orderedCollectionType = resolveRequiredTemplateableType(CollectionType.class, TypeId.ORDERED_COLLECTION_NAME, 1);
		}
		return orderedCollectionType2;
	}

	@Override
	public @NonNull OrderedSetType getOrderedSetType() {
		OrderedSetType orderedSetType2 = orderedSetType;
		if (orderedSetType2 == null) {
			orderedSetType2 = orderedSetType = resolveRequiredTemplateableType(OrderedSetType.class, TypeId.ORDERED_SET_NAME, 1);
		}
		return orderedSetType2;
	}

	@Override
	public org.eclipse.ocl.pivot.@NonNull Package getPackage() {
		org.eclipse.ocl.pivot.Package libraryPackage2 = libraryPackage;
		if (libraryPackage2 == null) {
			libraryPackage2 = libraryPackage = getOclAnyType().getOwningPackage();
			assert libraryPackage2 != null;
		}
		return libraryPackage2;
	}

	@Override
	public @NonNull Type getPrimaryType(@NonNull Type asType) {
		return environmentFactory.getMetamodelManager().getPrimaryType(asType);
	}

	@Override
	public @NonNull PrimitiveType getRealType() {
		PrimitiveType realType2 = realType;
		if (realType2 == null) {
			realType2 = realType = resolveRequiredSimpleType(PrimitiveType.class, TypeId.REAL_NAME);
		}
		return realType2;
	}

	protected org.eclipse.ocl.pivot.@NonNull Class getRequiredLibraryType(@NonNull String typeName) {
		org.eclipse.ocl.pivot.Class type = getLibraryType(typeName);
		if (type == null) {
			//			nameToLibraryTypeMap = null;
			type = getLibraryType(typeName);	// FIXME just a debug retry
			Map<@NonNull String, org.eclipse.ocl.pivot.@NonNull Class> nameToLibraryTypeMap2 = nameToLibraryTypeMap;
			if ((nameToLibraryTypeMap2 == null) || nameToLibraryTypeMap2.isEmpty()) {
				throw new IllegalLibraryException(PivotMessagesInternal.EmptyLibrary_ERROR_);
			}
			else {
				throw new IllegalLibraryException(NLS.bind(PivotMessagesInternal.MissingLibraryType_ERROR_, typeName));
			}
		}
		return type;
	}

	@Override
	public org.eclipse.ocl.pivot.@Nullable Package getRootPackage(@NonNull String completeURIorName) {
		Package rootPackage = completeModel.getRootPackage(completeURIorName);
		if (rootPackage == null) {
			if (PivotConstants.METAMODEL_NAME.equals(completeURIorName)) {
				assert environmentFactory != null;
				environmentFactory.getMetamodelManager().getASmetamodel();
				rootPackage = completeModel.getRootPackage(completeURIorName);
			}
		}
		return rootPackage;
	}

	@Override
	public @NonNull SequenceType getSequenceType() {
		SequenceType sequenceType2 = sequenceType;
		if (sequenceType2 == null) {
			sequenceType2 = sequenceType = resolveRequiredTemplateableType(SequenceType.class, TypeId.SEQUENCE_NAME, 1);
		}
		return sequenceType2;
	}

	@Override
	public @NonNull SetType getSetType() {
		SetType setType2 = setType;
		if (setType2 == null) {
			setType2 = setType = resolveRequiredTemplateableType(SetType.class, TypeId.SET_NAME, 1);
		}
		return setType2;
	}

	@Override
	public @NonNull Type getSpecializedType(@NonNull Type type, @Nullable TemplateParameterSubstitutions substitutions) {
		if ((substitutions == null) || substitutions.isEmpty()) {
			return type;
		}
		TemplateParameter asTemplateParameter = type.isTemplateParameter();
		if ((asTemplateParameter instanceof NormalizedTemplateParameter) && (substitutions instanceof BasicTemplateSpecialization)) {
			int index = ((NormalizedTemplateParameter)asTemplateParameter).getIndex();
			BasicTemplateSpecialization templateSpecialization = (BasicTemplateSpecialization)substitutions;
			Type boundType = templateSpecialization.basicGet(index);
			if (boundType == null) {
				TemplateParameterization templateParameterization = templateSpecialization.getTemplateParameterization();
				boundType = templateParameterization.get(index);
			}
			return boundType;
		}
		else if (asTemplateParameter != null) {
			Type boundType = substitutions.get(asTemplateParameter);
			org.eclipse.ocl.pivot.Class asClass = boundType != null ? boundType.isClass() : null;
			return asClass != null ? asClass : type;
		}
		else if (type instanceof CollectionType) {
			CollectionType collectionType = (CollectionType)type;
			CollectionType unspecializedType = PivotUtil.getUnspecializedTemplateableElement(collectionType);
			Type elementType = getSpecializedType(ClassUtil.requireNonNull(collectionType.getElementType()), substitutions);
			return getCollectionType(unspecializedType, elementType, collectionType.isIsNullFree(), collectionType.getLowerValue(), collectionType.getUpperValue());
		}
		else if (type instanceof MapType) {
			MapType mapType = (MapType)type;
			Type keyType = getSpecializedType(ClassUtil.requireNonNull(mapType.getKeyType()), substitutions);
			Type valueType = getSpecializedType(ClassUtil.requireNonNull(mapType.getValueType()), substitutions);
			return getMapType(keyType, mapType.isKeysAreNullFree(), valueType, mapType.isValuesAreNullFree());
		}
		else if (type instanceof TupleType) {
			return getTupleTypeManager().getTupleType((TupleType) type, substitutions);
		}
		else if (type instanceof LambdaType) {
			LambdaType lambdaType = (LambdaType)type;
			String typeName = PivotUtil.getName(lambdaType);
			LambdaParameter context = PivotUtil.getOwnedContext(lambdaType);
			List<@NonNull LambdaParameter> parameters = PivotUtil.getOwnedParametersList(lambdaType);
			LambdaParameter result = PivotUtil.getOwnedResult(lambdaType);
			return getLambdaType(typeName, context, parameters, result, substitutions);
		}
		else if (type instanceof org.eclipse.ocl.pivot.Class) {
			//
			//	Get the bindings of the type.
			//
			org.eclipse.ocl.pivot.Class unspecializedType = PivotUtil.getUnspecializedTemplateableElement((org.eclipse.ocl.pivot.Class)type);
			//
			//	Prepare the template argument list, one template argument per template parameter.
			//
			TemplateSignature templateSignature = unspecializedType.getOwnedSignature();
			if (templateSignature != null) {
				List<@NonNull TemplateParameter> templateParameters = ClassUtil.nullFree(templateSignature.getOwnedParameters());
				List<@NonNull Type> templateArguments = new ArrayList<@NonNull Type>(templateParameters.size());
				for (@NonNull TemplateParameter templateParameter : templateParameters) {
					Type templateArgument = substitutions.get(templateParameter);
					templateArguments.add(templateArgument != null ? templateArgument : templateParameter);
				}
				assert environmentFactory != null;
				MetamodelManager metamodelManager = environmentFactory.getMetamodelManager();
				return metamodelManager.getLibraryType(unspecializedType, templateArguments);
			}
		}
		return type;
	}

	/**
	 * @since 7.0
	 */
	@Override
	public org.eclipse.ocl.pivot.@NonNull Class getSpecializedType(org.eclipse.ocl.pivot.@NonNull Class genericClass,
			@NonNull List<@NonNull ? extends Type> templateArguments) {
		return getSpecializationManager().getSpecializedType(genericClass, templateArguments);
	}

	private @NonNull SpecializationManager getSpecializationManager() {
		SpecializationManager specializationManager2 = specializationManager;
		if (specializationManager2 == null) {
			assert environmentFactory != null;
			specializationManager = specializationManager2 = new SpecializationManagerInternal(environmentFactory.getCompleteModel());
		}
		return specializationManager2;
	}

	@Override
	public @NonNull PrimitiveType getStringType() {
		PrimitiveType stringType2 = stringType;
		if (stringType2 == null) {
			stringType2 = stringType = resolveRequiredSimpleType(PrimitiveType.class, TypeId.STRING_NAME);
		}
		return stringType2;
	}

/*	@Override
	public @NonNull TupleTypeManagerInternal getTupleManager() {
		TupleTypeManagerInternal tupleManager2 = tupleManager;
		if (tupleManager2 == null) {
			assert environmentFactory != null;
			tupleManager = tupleManager2 = new TupleTypeManagerInternal(environmentFactory);
		}
		return tupleManager2;
	} */

	@Override
	public @NonNull TupleTypeManagerInternal getTupleTypeManager() {
		return (TupleTypeManagerInternal)super.getTupleTypeManager();
	}

	@Override
	public @NonNull CollectionType getUniqueCollectionType() {
		CollectionType uniqueCollectionType2 = uniqueCollectionType;
		if (uniqueCollectionType2 == null) {
			uniqueCollectionType2 = uniqueCollectionType = resolveRequiredTemplateableType(CollectionType.class, TypeId.UNIQUE_COLLECTION_NAME, 1);
		}
		return uniqueCollectionType2;
	}

	@Override
	public @NonNull PrimitiveType getUnlimitedNaturalType() {
		PrimitiveType unlimitedNaturalType2 = unlimitedNaturalType;
		if (unlimitedNaturalType2 == null) {
			unlimitedNaturalType2 = unlimitedNaturalType = resolveRequiredSimpleType(PrimitiveType.class, TypeId.UNLIMITED_NATURAL_NAME);
		}
		return unlimitedNaturalType2;
	}

	@Override
	public @NonNull StandardLibraryInternal init(@NonNull CompleteModelInternal completeModel) {
		this.completeModel = completeModel;
		this.environmentFactory = completeModel.getEnvironmentFactory();
		return this;
	}

	@Override
	public boolean isExplicitDefaultStandardLibraryURI() {
		return explicitDefaultStandardLibraryURI;
	}

	public boolean isOrdered(Type sourceType) {
		if (sourceType instanceof OrderedSetType) {
			return true;
		}
		if (sourceType instanceof SequenceType) {
			return true;
		}
		return false;
	}

	/**
	 * Return true if elementTypes are the TemplateParameters of one of the unspecialized type of one of the
	 * partial types of completeClass.
	 *
	private boolean isUnspecializedType(@NonNull CompleteClassInternal completeClass, @NonNull Type @NonNull ... elementTypes) {
		Iterable<org.eclipse.ocl.pivot.@NonNull Class> partialClasses = PivotUtil.getPartialClasses(completeClass);
		for (int i = 0; i < elementTypes.length; i++) {
			@NonNull Type elementType = elementTypes[i];
			boolean isUnspecializedElement = false;
			for (org.eclipse.ocl.pivot.@NonNull Class partialClass : partialClasses) {
				TemplateSignature templateSignature = partialClass.getOwnedSignature();
				if (templateSignature == null) {
					throw new IllegalArgumentException(completeClass.getName() + " type must have a template signature");
				}
				List<TemplateParameter> templateParameters = templateSignature.getOwnedParameters();
				if (templateParameters.size() != elementTypes.length) {
					throw new IllegalArgumentException(completeClass.getName() + " type must have exactly " + elementTypes.length + " template parameter");
				}
				if (elementType == templateParameters.get(i)) {
					isUnspecializedElement = true;
					break;
				}
			}
			if (!isUnspecializedElement) {
				return false;
			}
		}
		return true;
	} */

	public boolean isUnique(Type sourceType) {
		if (sourceType instanceof OrderedSetType) {
			return true;
		}
		if (sourceType instanceof SetType) {
			return true;
		}
		return false;
	}

	@Override
	public @Nullable Resource loadDefaultLibrary(@Nullable String uri) {
		assert environmentFactory != null;
		return environmentFactory.getMetamodelManager().loadDefaultLibrary(uri);
	}

	@Override
	public void resetLibrary() {
		bagType = null;
		booleanType = null;
		classType = null;
		collectionType = null;
		enumerationType = null;
		integerType = null;
		libraryPackage = null;
		mapType = null;
		oclAnyType = null;
		oclComparableType = null;
		oclElementType = null;
		oclEnumerationType = null;
		oclInvalidOperation = null;
		oclInvalidProperty = null;
		oclInvalidType = null;
		oclLambdaType = null;
		oclSelfType = null;
		oclSummableType = null;
		oclTupleType = null;
		oclTypeType = null;
		oclVoidType = null;
		orderedCollectionType = null;
		orderedSetType = null;
		realType = null;
		sequenceType = null;
		setType = null;
		stringType = null;
		uniqueCollectionType = null;
		unlimitedNaturalType = null;
		nameToLibraryTypeMap = null;
		if (lambdaManager != null) {
			lambdaManager.dispose();
			lambdaManager = null;
		}
		super.resetLibrary();
	}

	protected @NonNull <T extends TemplateableElement> T resolveRequiredSimpleType(@NonNull Class<T> requiredClassType, @NonNull String name) {
		org.eclipse.ocl.pivot.Class type = getRequiredLibraryType(name);
		if (requiredClassType.isAssignableFrom(type.getClass())) {
			@SuppressWarnings("unchecked")
			T type2 = (T) type;
			return type2;
		}
		else {
			throw new IllegalLibraryException(name + " is not a " + requiredClassType.getSimpleName());
		}
	}

	protected @NonNull <T extends TemplateableElement> T resolveRequiredTemplateableType(@NonNull Class<T> requiredClassType, @NonNull String name, int parameterCount) {
		org.eclipse.ocl.pivot.Class type = getRequiredLibraryType(name);
		if (requiredClassType.isAssignableFrom(type.getClass())) {
			if (type.getOwnedSignature() == null) {
				throw new IllegalLibraryException(name + " is not a templated type");
			}
			else if (type.getOwnedSignature().getOwnedParameters().size() != parameterCount) {
				throw new IllegalLibraryException(name + " is not a templated type with " + parameterCount + " argument" + (parameterCount != 1 ? "s" : ""));
			}
			@SuppressWarnings("unchecked")
			T type2 = (T) type;
			return type2;
		}
		else {
			throw new IllegalLibraryException(name + " is not a " + requiredClassType.getSimpleName());
		}
	}

	@Override
	public void setDefaultStandardLibraryURI(@NonNull String defaultStandardLibraryURI) {
		assert !PivotUtil.isASURI(URI.createURI(defaultStandardLibraryURI));
		this.defaultStandardLibraryURI = defaultStandardLibraryURI;
		this.explicitDefaultStandardLibraryURI = true;
	}
} //StandardLibraryInternalImpl
