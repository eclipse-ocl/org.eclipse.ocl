/**
 * Copyright (c) 2010, 2020 Willink Transformations and others.
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
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.AnyType;
import org.eclipse.ocl.pivot.BagType;
import org.eclipse.ocl.pivot.BooleanType;
import org.eclipse.ocl.pivot.CollectionType;
import org.eclipse.ocl.pivot.Comment;
import org.eclipse.ocl.pivot.CompleteEnvironment;
import org.eclipse.ocl.pivot.CompletePackage;
import org.eclipse.ocl.pivot.CompleteStandardLibrary;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.ElementExtension;
import org.eclipse.ocl.pivot.InvalidType;
import org.eclipse.ocl.pivot.MapType;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.OrderedSetType;
import org.eclipse.ocl.pivot.Package;
import org.eclipse.ocl.pivot.PivotPackage;
import org.eclipse.ocl.pivot.PrimitiveType;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.SelfType;
import org.eclipse.ocl.pivot.SequenceType;
import org.eclipse.ocl.pivot.SetType;
import org.eclipse.ocl.pivot.TemplateParameter;
import org.eclipse.ocl.pivot.TemplateSignature;
import org.eclipse.ocl.pivot.TemplateableElement;
import org.eclipse.ocl.pivot.TupleType;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.TypedElement;
import org.eclipse.ocl.pivot.VoidType;
import org.eclipse.ocl.pivot.flat.CompleteFlatModel;
import org.eclipse.ocl.pivot.ids.CollectionTypeId;
import org.eclipse.ocl.pivot.ids.IdManager;
import org.eclipse.ocl.pivot.ids.TemplateParameterId;
import org.eclipse.ocl.pivot.ids.TuplePartId;
import org.eclipse.ocl.pivot.ids.TupleTypeId;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.complete.CompleteClassInternal;
import org.eclipse.ocl.pivot.internal.complete.CompleteModelInternal;
import org.eclipse.ocl.pivot.internal.manager.Orphanage;
import org.eclipse.ocl.pivot.internal.manager.PivotIdResolver;
import org.eclipse.ocl.pivot.internal.manager.PivotMetamodelManager;
import org.eclipse.ocl.pivot.internal.manager.TemplateParameterSubstitutionVisitor;
import org.eclipse.ocl.pivot.internal.messages.PivotMessagesInternal;
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal;
import org.eclipse.ocl.pivot.internal.utilities.IllegalLibraryException;
import org.eclipse.ocl.pivot.internal.utilities.PivotUtilInternal;
import org.eclipse.ocl.pivot.library.LibraryConstants;
import org.eclipse.ocl.pivot.util.Visitor;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.PivotConstants;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.utilities.TypeUtil;
import org.eclipse.ocl.pivot.values.IntegerValue;
import org.eclipse.ocl.pivot.values.MapTypeParameters;
import org.eclipse.ocl.pivot.values.TemplateParameterSubstitutions;
import org.eclipse.ocl.pivot.values.UnlimitedNaturalValue;
import org.eclipse.osgi.util.NLS;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Standard Library</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.pivot.internal.CompleteStandardLibraryImpl#getOwningCompleteEnvironment <em>Owning Complete Environment</em>}</li>
 * </ul>
 *
 * @generated
 */
public class CompleteStandardLibraryImpl extends StandardLibraryImpl implements CompleteStandardLibrary
{
	/**
	 * The number of structural features of the '<em>Complete Standard Library</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int COMPLETE_STANDARD_LIBRARY_FEATURE_COUNT = StandardLibraryImpl.STANDARD_LIBRARY_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Complete Standard Library</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int COMPLETE_STANDARD_LIBRARY_OPERATION_COUNT = StandardLibraryImpl.STANDARD_LIBRARY_OPERATION_COUNT + 0;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CompleteStandardLibraryImpl()
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
		return PivotPackage.Literals.COMPLETE_STANDARD_LIBRARY;
	}
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public CompleteEnvironment getOwningCompleteEnvironment()
	{
		if (eContainerFeatureID() != (4)) return null;
		return (CompleteEnvironment)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOwningCompleteEnvironment(CompleteEnvironment newOwningCompleteEnvironment, NotificationChain msgs)
	{
		msgs = eBasicSetContainer((InternalEObject)newOwningCompleteEnvironment, 4, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setOwningCompleteEnvironment(CompleteEnvironment newOwningCompleteEnvironment)
	{
		if (newOwningCompleteEnvironment != eInternalContainer() || (eContainerFeatureID() != (4) && newOwningCompleteEnvironment != null))
		{
			if (EcoreUtil.isAncestor(this, newOwningCompleteEnvironment))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString()); //$NON-NLS-1$
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newOwningCompleteEnvironment != null)
				msgs = ((InternalEObject)newOwningCompleteEnvironment).eInverseAdd(this, 5, CompleteEnvironment.class, msgs);
			msgs = basicSetOwningCompleteEnvironment(newOwningCompleteEnvironment, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, 4, newOwningCompleteEnvironment, newOwningCompleteEnvironment));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs)
	{
		switch (featureID)
		{
			case 0:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getAnnotatingComments()).basicAdd(otherEnd, msgs);
			case 2:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOwnedComments()).basicAdd(otherEnd, msgs);
			case 3:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOwnedExtensions()).basicAdd(otherEnd, msgs);
			case 4:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetOwningCompleteEnvironment((CompleteEnvironment)otherEnd, msgs);
		}
		return eDynamicInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs)
	{
		switch (featureID)
		{
			case 0:
				return ((InternalEList<?>)getAnnotatingComments()).basicRemove(otherEnd, msgs);
			case 1:
				return ((InternalEList<?>)getOwnedAnnotations()).basicRemove(otherEnd, msgs);
			case 2:
				return ((InternalEList<?>)getOwnedComments()).basicRemove(otherEnd, msgs);
			case 3:
				return ((InternalEList<?>)getOwnedExtensions()).basicRemove(otherEnd, msgs);
			case 4:
				return basicSetOwningCompleteEnvironment(null, msgs);
		}
		return eDynamicInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs)
	{
		switch (eContainerFeatureID())
		{
			case 4:
				return eInternalContainer().eInverseRemove(this, 5, CompleteEnvironment.class, msgs);
		}
		return eDynamicBasicRemoveFromContainer(msgs);
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
				return getOwningCompleteEnvironment();
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
				return getOwningCompleteEnvironment() != null;
		}
		return eDynamicIsSet(featureID);
	}
	/**
	 * {@inheritDoc}
	 * @generated
	 */
	@Override
	public <R> R accept(@NonNull Visitor<R> visitor) {
		return visitor.visitCompleteStandardLibrary(this);
	}

	private static final Logger logger = Logger.getLogger(CompleteStandardLibrary.class);
	private static final @NonNull List<TemplateParameter> EMPTY_TEMPLATE_PARAMETER_LIST2 = Collections.emptyList();

	/**
	 * The URI used by default for the OCL Standard Library. NB. This
	 * constant is repeated in GenerateOCLstdlibModel.mwe2 and in
	 * org.eclipse.ocl.pivot/plugin.xml.
	 */
	public static final @NonNull String DEFAULT_OCL_STDLIB_URI = LibraryConstants.STDLIB_URI;
	protected static class TupleIdResolver extends PivotIdResolver
	{
		private final TemplateParameterReferencesVisitor referencesVisitor;

		private TupleIdResolver(@NonNull EnvironmentFactoryInternal environmentFactory,
				TemplateParameterReferencesVisitor referencesVisitor) {
			super(environmentFactory);
			this.referencesVisitor = referencesVisitor;
		}

		@Override
		public @NonNull Element visitTemplateParameterId(@NonNull TemplateParameterId id) {
			int index = id.getIndex();
			TemplateParameter templateParameter = referencesVisitor.templateParameters.get(index);
			if (templateParameter != null) {
				return templateParameter;
			}
			return super.visitTemplateParameterId(id);
		}
	}

	/**
	 * TuplePart provides a convenient descriptor for a tuple part complying with the full EMF model protocols.
	 */
	public static class TuplePart extends TypedElementImpl
	{
		protected final @NonNull TuplePartId partId;

		public TuplePart(@NonNull TuplePartId partId) {
			this.partId = partId;
			setName(partId.getName());
		}

		@Override
		public @NonNull TypeId getTypeId() {
			return partId.getTypeId();
		}

		@Override
		public String toString() {
			return String.valueOf(name) + " : " + String.valueOf(type);
		}
	}

	/**
	 * The TemplateParameterReferencesVisitor remembers the formal TemplateParameter for re-uyse during Tuple instantiation.
	 */
	protected static class TemplateParameterReferencesVisitor extends TemplateParameterSubstitutionVisitor
	{
		protected final @NonNull Map<@NonNull Integer, @NonNull TemplateParameter> templateParameters = new HashMap<>();

		public TemplateParameterReferencesVisitor(@NonNull EnvironmentFactoryInternal environmentFactory, Collection<? extends Type> partValues) {
			super(environmentFactory, null, null);
			for (Type partValue : partValues) {
				analyzeType(partValue, partValue);
			}
		}

		@Override
		public @NonNull Type put(@NonNull TemplateParameter formalTemplateParameter, @NonNull Type actualType) {
			templateParameters.put(formalTemplateParameter.getTemplateParameterId().getIndex(), formalTemplateParameter);
			return super.put(formalTemplateParameter, actualType);
		}
	}

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
	private org.eclipse.ocl.pivot.@Nullable Package libraryPackage = null;
	private @Nullable MapType mapType = null;
	private @Nullable AnyType oclAnyType = null;
	private org.eclipse.ocl.pivot.@Nullable Class oclComparableType = null;
	private org.eclipse.ocl.pivot.@Nullable Class oclElementType = null;
	private org.eclipse.ocl.pivot.@Nullable Class oclEnumerationType = null;
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

	private @Nullable Map<String, org.eclipse.ocl.pivot.Class> nameToLibraryTypeMap = null;

	protected /*final*/ /*@NonNull*/ CompleteModelInternal completeModel;
	protected /*final*/ /*@NonNull*/ EnvironmentFactoryInternal environmentFactory;

	@Override
	public void addOrphanClass(org.eclipse.ocl.pivot.@NonNull Class orphanClass) {
		Orphanage orphanage = getCompleteModel().getOrphanage();
		orphanClass.setOwningPackage(orphanage);
	}

	@Override
	public @Nullable AnyType basicGetOclAnyType() {
		return oclAnyType;
	}

	@Override
	public @Nullable InvalidType basicGetOclInvalidType() {
		return oclInvalidType;
	}

	@Override
	public void defineLibraryTypes(@NonNull Iterable<org.eclipse.ocl.pivot.@NonNull Class> pivotTypes) {
		Map<String, org.eclipse.ocl.pivot.Class> nameToLibraryTypeMap2 = nameToLibraryTypeMap;
		if (nameToLibraryTypeMap2 == null) {
			nameToLibraryTypeMap = nameToLibraryTypeMap2 = new HashMap<>();
		}
		for (org.eclipse.ocl.pivot.@NonNull Class pivotType : pivotTypes) {
			String name = pivotType.getName();
			org.eclipse.ocl.pivot.Class oldType = nameToLibraryTypeMap2.put(name, pivotType);
			if ((oldType != null) && (oldType != pivotType)) {
				if (!(oldType instanceof PrimitiveType) || !(pivotType instanceof PrimitiveType)) {		// User primitives may only be DataType e.g. testQVTrLoad_ATL2QVTr_qvtre
					logger.warn("Conflicting pivot type '" + name + "'");
				}
			}
		}
	}

	/**
	 * Return the pivot model class for className with the Pivot Model.
	 */
	@Override
	public org.eclipse.ocl.pivot.@Nullable Class getASClass(@NonNull String className) {
		return environmentFactory.getMetamodelManager().getASClass(className);
	}

	@Override
	public @NonNull Iterable<@NonNull ? extends CompletePackage> getAllCompletePackages() {
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
	public @Nullable Type getCommonTupleType(@NonNull TupleType leftType, @NonNull TemplateParameterSubstitutions leftSubstitutions,
			@NonNull TupleType rightType, @NonNull TemplateParameterSubstitutions rightSubstitutions) {
		List<Property> leftProperties = leftType.getOwnedProperties();
		List<Property> rightProperties = rightType.getOwnedProperties();
		int iSize = leftProperties.size();
		if (iSize != rightProperties.size()) {
			return null;
		}
		List<@NonNull TuplePartId> commonPartIds = new ArrayList<>(iSize);
		for (int i = 0; i < iSize; i++) {
			Property leftProperty = leftProperties.get(i);
			if (leftProperty == null) {
				return null;				// Never happens
			}
			String name = leftProperty.getName();
			if (name == null) {
				return null;				// Never happens
			}
			Property rightProperty = NameUtil.getNameable(rightProperties, name);
			if (rightProperty == null) {
				return null;				// Happens for inconsistent tuples
			}
			Type leftPropertyType = leftProperty.getType();
			if (leftPropertyType == null) {
				return null;				// Never happens
			}
			Type rightPropertyType = rightProperty.getType();
			if (rightPropertyType == null) {
				return null;				// Never happens
			}
			Type commonType = environmentFactory.getMetamodelManager().getCommonType(leftPropertyType, leftSubstitutions, rightPropertyType, rightSubstitutions);
			TuplePartId commonPartId = IdManager.getTuplePartId(i, name, commonType.getTypeId());
			commonPartIds.add(commonPartId);
		}
		TupleTypeId commonTupleTypeId = IdManager.getTupleTypeId(TypeId.TUPLE_NAME, commonPartIds);
		return getTupleType(environmentFactory.getIdResolver(), commonTupleTypeId);
	}

	@Override
	public @NonNull CompleteModelInternal getCompleteModel() {
		return ClassUtil.nonNullState(completeModel);
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

	@Override
	public @NonNull CompleteFlatModel getFlatModel() {
		return getCompleteModel().getFlatModel();
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
	public org.eclipse.ocl.pivot.Class getLibraryType(@NonNull String typeName) {
		Map<String, org.eclipse.ocl.pivot.Class> nameToLibraryTypeMap2 = nameToLibraryTypeMap;
		if (nameToLibraryTypeMap2 == null) {
			nameToLibraryTypeMap = nameToLibraryTypeMap2 = new HashMap<>();
			loadDefaultLibrary(defaultStandardLibraryURI);
		}
		return nameToLibraryTypeMap2.get(typeName);
	}

	@Override
	public org.eclipse.ocl.pivot.@Nullable Class getLibraryType(@NonNull String string, @NonNull List<@NonNull ? extends Type> templateArguments) {
		org.eclipse.ocl.pivot.Class libraryType = getRequiredLibraryType(string);
		return getLibraryType(libraryType, templateArguments);
	}

	@Override
	public @NonNull <T extends org.eclipse.ocl.pivot.Class> T getLibraryType(@NonNull T libraryType, @NonNull List<@NonNull ? extends Type> templateArguments) {
		//		assert !(libraryType instanceof CollectionType);
		assert libraryType == PivotUtil.getUnspecializedTemplateableElement(libraryType);
		TemplateSignature templateSignature = libraryType.getOwnedSignature();
		List<TemplateParameter> templateParameters = templateSignature != null ? templateSignature.getOwnedParameters() : EMPTY_TEMPLATE_PARAMETER_LIST2;
		if (templateParameters.isEmpty()) {
			return libraryType;
		}
		if (templateArguments.size() != templateParameters.size()) {
			throw new IllegalArgumentException("Incorrect template bindings for template type " + libraryType.getName());
		}
		boolean isUnspecialized = isUnspecialized(templateParameters, templateArguments);
		if (isUnspecialized) {
			return libraryType;
		}
		CompleteClassInternal libraryCompleteClass = getCompleteModel().getMetamodelManager().getCompleteClass(libraryType);
		org.eclipse.ocl.pivot.Class pivotClass = libraryCompleteClass.getPrimaryClass();
		if (pivotClass instanceof CollectionType) {
			assert pivotClass instanceof CollectionType;
			assert templateArguments.size() == 1;
			@NonNull Type templateArgument = templateArguments.get(0);
			@SuppressWarnings("unchecked") T specializedType = (T) getCollectionType(TypeUtil.createCollectionTypeParameters((CollectionTypeId) libraryType.getTypeId(), templateArgument, PivotConstants.DEFAULT_COLLECTIONS_ARE_NULL_FREE, null, null));
			return specializedType;
		}
		else if (pivotClass instanceof MapType) {
			assert pivotClass instanceof MapType;
			assert templateArguments.size() == 2;
			@NonNull Type keyTemplateArgument = templateArguments.get(0);
			@NonNull Type valueTemplateArgument = templateArguments.get(1);
			@SuppressWarnings("unchecked") T specializedType = (T) getMapType(TypeUtil.createMapTypeParameters(keyTemplateArgument, PivotConstants.DEFAULT_MAP_KEYS_ARE_NULL_FREE, valueTemplateArgument, PivotConstants.DEFAULT_MAP_VALUES_ARE_NULL_FREE));
			return specializedType;
		}
		else {
			@SuppressWarnings("unchecked")
			T specializedType = (T) libraryCompleteClass.getSpecializedType(templateArguments);
			return specializedType;
		}
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
	 * @since 1.7
	 */
	@Override
	public org.eclipse.ocl.pivot.@NonNull Class getMapOfEntryType(org.eclipse.ocl.pivot.@NonNull Class entryClass) {
		if (entryClass.eIsProxy()) {
			return getOclInvalidType();
		}
		PivotMetamodelManager metamodelManager = environmentFactory.getMetamodelManager();
		org.eclipse.ocl.pivot.@NonNull Class entryType = metamodelManager.getPrimaryClass(entryClass);
		MapTypeParameters<@NonNull Type, @NonNull Type> typeParameters = TypeUtil.createMapTypeParameters(entryType);
		return getMapType(typeParameters);
	}

	@Override
	public org.eclipse.ocl.pivot.@NonNull Class getMetaclass(@NonNull Type classType) {
		return environmentFactory.getMetamodelManager().getMetaclass(classType);
	}

	@Override
	public org.eclipse.ocl.pivot.Package getNestedPackage(org.eclipse.ocl.pivot.@NonNull Package domainPackage, @NonNull String name) {
		PivotMetamodelManager metamodelManager = environmentFactory.getMetamodelManager();
		CompletePackage completePackage = metamodelManager.getCompletePackage(domainPackage);
		CompletePackage memberPackage = completePackage.getOwnedCompletePackage(name);
		return memberPackage != null ? memberPackage.getPrimaryPackage() : null;
	}

	@Override
	public org.eclipse.ocl.pivot.Class getNestedType(org.eclipse.ocl.pivot.@NonNull Package domainPackage, @NonNull String name) {
		PivotMetamodelManager metamodelManager = environmentFactory.getMetamodelManager();
		CompletePackage completePackage = metamodelManager.getCompletePackage(domainPackage);
		return completePackage.getMemberType(name);
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
	public Type getOclType(@NonNull String typeName) {
		return environmentFactory.getMetamodelManager().getOclType(typeName);
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
	public @Nullable Element getOperationTemplateParameter(@NonNull Operation anOperation, int index) {
		anOperation = PivotUtil.getUnspecializedTemplateableElement(anOperation);
		return anOperation.getTypeParameters().get(index);
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

	@Override
	public org.eclipse.ocl.pivot.@NonNull Class getRequiredLibraryType(@NonNull String typeName) {
		org.eclipse.ocl.pivot.Class type = getLibraryType(typeName);
		if (type == null) {
			//			nameToLibraryTypeMap = null;
			type = getLibraryType(typeName);	// FIXME just a debug retry
			Map<String, org.eclipse.ocl.pivot.Class> nameToLibraryTypeMap2 = nameToLibraryTypeMap;
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
	public @NonNull PrimitiveType getStringType() {
		PrimitiveType stringType2 = stringType;
		if (stringType2 == null) {
			stringType2 = stringType = resolveRequiredSimpleType(PrimitiveType.class, TypeId.STRING_NAME);
		}
		return stringType2;
	}

	@Override
	public @NonNull TupleType getTupleType(@NonNull String tupleName, @NonNull Collection<@NonNull ? extends TypedElement> parts,
			@Nullable TemplateParameterSubstitutions bindings) {
		Map<@NonNull String, @NonNull Type> partMap = new HashMap<>();
		for (@NonNull TypedElement part : parts) {
			Type type1 = part.getType();
			if (type1 != null) {
				Type type2 = getPrimaryType(type1);
				Type type3 = getSpecializedType(type2, bindings);
				partMap.put(PivotUtil.getName(part), type3);
			}
		}
		return getTupleType(tupleName, partMap);
	}

	@Override
	public @NonNull TupleType getTupleType(@NonNull TupleType type, @Nullable TemplateParameterSubstitutions usageBindings) {	// FIXME Remove duplication, unify type/multiplicity
		TupleType specializedTupleType = type;
		Map<String, Type> resolutions =  null;
		List<Property> parts = specializedTupleType.getOwnedProperties();
		for (Property part : parts) {
			if (part != null) {
				Type propertyType = PivotUtilInternal.getType(part);
				Type resolvedPropertyType = getSpecializedType(propertyType, usageBindings);
				if (resolvedPropertyType != propertyType) {
					if (resolutions == null) {
						resolutions = new HashMap<>();
					}
					resolutions.put(NameUtil.getSafeName(part), resolvedPropertyType);
				}
			}
		}
		if (resolutions != null) {
			List<@NonNull TuplePartId> partIds = new ArrayList<>(parts.size());
			for (int i = 0; i < parts.size(); i++) {
				@SuppressWarnings("null") @NonNull Property part = parts.get(i);
				String partName = NameUtil.getSafeName(part);
				Type resolvedPropertyType = resolutions.get(partName);
				TypeId partTypeId = resolvedPropertyType != null ? resolvedPropertyType.getTypeId() : part.getTypeId();
				TuplePartId tuplePartId = IdManager.getTuplePartId(i, partName, partTypeId);
				partIds.add(tuplePartId);
			}
			TupleTypeId tupleTypeId = IdManager.getTupleTypeId(ClassUtil.nonNullModel(type.getName()), partIds);
			specializedTupleType = getTupleType(environmentFactory.getIdResolver(), tupleTypeId);
			return specializedTupleType;
		}
		else {
			return getTupleType(NameUtil.getSafeName(type), ClassUtil.nullFree(type.getOwnedProperties()), usageBindings);
		}
	}

	@Override
	public @NonNull TupleType getTupleType(@NonNull String tupleName, @NonNull Map<@NonNull String, @NonNull ? extends Type> parts) {
		//
		//	Find the outgoing template parameter references
		// FIXME this should be more readily and reliably computed in the caller
		@NonNull Collection<? extends Type> partValues = parts.values();
		final TemplateParameterReferencesVisitor referencesVisitor = new TemplateParameterReferencesVisitor(environmentFactory, partValues);	// FIXME this isn't realistically extensible
		TupleTypeId tupleTypeId = IdManager.getOrderedTupleTypeId(tupleName, parts);
		PivotIdResolver pivotIdResolver = new TupleIdResolver(environmentFactory, referencesVisitor);
		//
		//	Finally create the (specialize) tuple type
		//
		TupleType tupleType = getTupleType(pivotIdResolver /*metamodelManager.getIdResolver()*/, tupleTypeId);
		return tupleType;
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
	public @NonNull CompleteStandardLibrary init(@NonNull CompleteModelInternal completeModel) {
		this.completeModel = completeModel;
		this.environmentFactory = completeModel.getEnvironmentFactory();
		return this;
	}

	@Override
	public boolean isExplicitDefaultStandardLibraryURI() {
		return explicitDefaultStandardLibraryURI;
	}

	/**
	 * Return true if elementTypes are the TemplateParameters of one of the unspecialized type of one of the
	 * partial types of completeClass.
	 */
	@Override
	protected boolean isUnspecialized(@NonNull CollectionType genericType, @NonNull Type elementType,
			boolean isNullFree, @Nullable IntegerValue lower, @Nullable UnlimitedNaturalValue upper) {
		if (!isUnspecialized(isNullFree, lower, upper)) {
			return false;
		}
		CompleteClassInternal completeClass = getCompleteModel().getCompleteClass(genericType);
		return isUnspecializedType(completeClass, elementType);
	}

	/**
	 * Return true if elementTypes are the TemplateParameters of one of the unspecialized type of one of the
	 * partial types of completeClass.
	 */
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
	}

	protected boolean isUnspecialized(@NonNull List<TemplateParameter> templateParameters, @NonNull List<? extends Type> templateArguments) {
		int iMax = templateParameters.size();
		assert templateArguments.size() == iMax;
		for (int i = 0; i < iMax; i++) {
			if (templateArguments.get(i) != templateParameters.get(i)) {
				return false;
			}
		}
		return true;
	}

	@Override
	protected boolean isUnspecialized(@NonNull Type keyType, boolean keysAreNullFree,
			@NonNull Type valueType, boolean valuesAreNullFree) {
		if (!isUnspecialized(keysAreNullFree, valuesAreNullFree)) {
			return false;
		}
		CompleteClassInternal completeClass = getCompleteModel().getCompleteClass(getMapType());
		if (isUnspecializedType(completeClass, keyType, valueType)) {
			return true;
		}
		return false;
	}

	@Override
	public @Nullable Resource loadDefaultLibrary(@Nullable String uri) {
		return environmentFactory.getMetamodelManager().loadDefaultLibrary(uri);
	}

	@Override
	public void resetLibrary() {
		super.resetLibrary();
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
	public void resolveSuperClasses(org.eclipse.ocl.pivot.@NonNull Class specializedClass, org.eclipse.ocl.pivot.@NonNull Class unspecializedClass) {
		getCompleteModel().resolveSuperClasses(specializedClass, unspecializedClass);
	}

	@Override
	public void setDefaultStandardLibraryURI(@NonNull String defaultStandardLibraryURI) {
		assert !PivotUtilInternal.isASURI(defaultStandardLibraryURI);
		this.defaultStandardLibraryURI = defaultStandardLibraryURI;
		this.explicitDefaultStandardLibraryURI = true;
	}

} //StandardLibraryImpl
