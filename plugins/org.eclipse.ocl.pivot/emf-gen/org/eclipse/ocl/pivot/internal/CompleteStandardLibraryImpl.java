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
import org.eclipse.ocl.pivot.Class;
import org.eclipse.ocl.pivot.CollectionType;
import org.eclipse.ocl.pivot.Comment;
import org.eclipse.ocl.pivot.CompleteEnvironment;
import org.eclipse.ocl.pivot.CompletePackage;
import org.eclipse.ocl.pivot.CompleteStandardLibrary;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.ElementExtension;
import org.eclipse.ocl.pivot.PivotPackage;
import org.eclipse.ocl.pivot.PrimitiveType;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.TemplateParameter;
import org.eclipse.ocl.pivot.TemplateSignature;
import org.eclipse.ocl.pivot.TupleType;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.flat.CompleteFlatModel;
import org.eclipse.ocl.pivot.ids.IdManager;
import org.eclipse.ocl.pivot.ids.TemplateParameterId;
import org.eclipse.ocl.pivot.ids.TuplePartId;
import org.eclipse.ocl.pivot.ids.TupleTypeId;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.complete.CompleteClassInternal;
import org.eclipse.ocl.pivot.internal.complete.CompleteModelInternal;
import org.eclipse.ocl.pivot.internal.manager.PivotIdResolver;
import org.eclipse.ocl.pivot.internal.manager.PivotMetamodelManager;
import org.eclipse.ocl.pivot.internal.manager.TemplateParameterSubstitutionVisitor;
import org.eclipse.ocl.pivot.internal.messages.PivotMessagesInternal;
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal;
import org.eclipse.ocl.pivot.internal.utilities.IllegalLibraryException;
import org.eclipse.ocl.pivot.internal.utilities.Orphanage;
import org.eclipse.ocl.pivot.internal.utilities.PivotUtilInternal;
import org.eclipse.ocl.pivot.library.LibraryConstants;
import org.eclipse.ocl.pivot.util.Visitor;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.PivotConstants;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.values.IntegerValue;
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
public class CompleteStandardLibraryImpl extends ReflectiveStandardLibraryImpl implements CompleteStandardLibrary
{
	/**
	 * The number of structural features of the '<em>Complete Standard Library</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int COMPLETE_STANDARD_LIBRARY_FEATURE_COUNT = ReflectiveStandardLibraryImpl.REFLECTIVE_STANDARD_LIBRARY_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Complete Standard Library</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int COMPLETE_STANDARD_LIBRARY_OPERATION_COUNT = ReflectiveStandardLibraryImpl.REFLECTIVE_STANDARD_LIBRARY_OPERATION_COUNT + 0;

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


	protected /*final*/ /*@NonNull*/ CompleteModelInternal completeModel;
	protected /*final*/ /*@NonNull*/ EnvironmentFactoryInternal environmentFactory;
	private org.eclipse.ocl.pivot.@Nullable Package libraryPackage = null;
	private @Nullable Map<@NonNull String, org.eclipse.ocl.pivot.@NonNull Class> nameToLibraryTypeMap = null;

	@Override
	public void defineLibraryTypes(@NonNull Iterable<@NonNull Class> asTypes) {
		Map<@NonNull String, org.eclipse.ocl.pivot.@NonNull Class> nameToLibraryTypeMap2 = nameToLibraryTypeMap;
		if (nameToLibraryTypeMap2 == null) {
			nameToLibraryTypeMap = nameToLibraryTypeMap2 = new HashMap<>();
		}
		for (org.eclipse.ocl.pivot.@NonNull Class asType : asTypes) {
			String name = NameUtil.getName(asType);
			org.eclipse.ocl.pivot.Class oldType = nameToLibraryTypeMap2.put(name, asType);
			if ((oldType != null) && (oldType != asType)) {
				if (!(oldType instanceof PrimitiveType) || !(asType instanceof PrimitiveType)) {		// User primitives may only be DataType e.g. testQVTrLoad_ATL2QVTr_qvtre
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
	public @NonNull Class getCollectionType(@NonNull CollectionType genericType, @NonNull Type elementType,
			@Nullable Boolean isNullFree, @Nullable IntegerValue lower, @Nullable UnlimitedNaturalValue upper) {
		CompleteClassInternal completeClass = environmentFactory.getCompleteModel().getCompleteClass(elementType);
	//	elementType = completeClass.getPrimaryClass();
		return super.getCollectionType(genericType, elementType, isNullFree, lower, upper);
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
	public @NonNull CompleteFlatModel getFlatModel() {
		return getCompleteModel().getFlatModel();
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
	 * @since 1.7
	 */
	@Override
	public org.eclipse.ocl.pivot.@NonNull Class getMapOfEntryType(org.eclipse.ocl.pivot.@NonNull Class entryClass) {
		if (entryClass.eIsProxy()) {
			return getOclInvalidType();
		}
		PivotMetamodelManager metamodelManager = environmentFactory.getMetamodelManager();
		org.eclipse.ocl.pivot.@NonNull Class entryType = metamodelManager.getPrimaryClass(entryClass);
		return getOrphanage().getMapOfEntryType(getMapType(), entryType);
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
	public @NonNull Orphanage getOrphanage() {
		return getCompleteModel().getSharedOrphanage();
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
	public org.eclipse.ocl.pivot.@NonNull Class getRequiredLibraryType(@NonNull String typeName) {
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
		org.eclipse.ocl.pivot.Package rootPackage = completeModel.getRootPackage(completeURIorName);
		if (rootPackage == null) {
			if (PivotConstants.METAMODEL_NAME.equals(completeURIorName)) {
				environmentFactory.getMetamodelManager().getASmetamodel();
				rootPackage = completeModel.getRootPackage(completeURIorName);
			}
		}
		return rootPackage;
	}

	@Override
	public @NonNull <T extends org.eclipse.ocl.pivot.Class> T getSpecializedType(@NonNull T genericClass, @Nullable List<@NonNull TemplateParameter> partialTemplateParameters, @NonNull List<@NonNull Type> templateArguments) {
		//		assert !(libraryType instanceof CollectionType);
		assert genericClass == PivotUtil.getUnspecializedTemplateableElement(genericClass);
		TemplateSignature templateSignature = genericClass.getOwnedSignature();
		List<TemplateParameter> templateParameters = templateSignature != null ? templateSignature.getOwnedParameters() : EMPTY_TEMPLATE_PARAMETER_LIST2;
		if (templateParameters.isEmpty()) {
			return genericClass;
		}
		if (templateArguments.size() != templateParameters.size()) {
			throw new IllegalArgumentException("Incorrect template bindings for template type " + genericClass.getName());
		}
		boolean isUnspecialized = isUnspecialized(templateParameters, templateArguments);
		if (isUnspecialized) {
			return genericClass;
		}
		CompleteClassInternal libraryCompleteClass = getCompleteModel().getMetamodelManager().getCompleteClass(genericClass);
		@SuppressWarnings("unchecked")
		T pivotClass = (T) libraryCompleteClass.getPrimaryClass();
		return super.getSpecializedType(pivotClass, partialTemplateParameters, templateArguments);
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

/*	@Override
	public @NonNull TupleType getTupleType(@NonNull Map<@NonNull String, @NonNull Type> parts) {
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
		TupleType tupleType = getTupleType(pivotIdResolver /*metamodelManager.getIdResolver()* /, tupleTypeId);
		return tupleType;
	} */

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
			@Nullable Boolean isNullFree, @Nullable IntegerValue lower, @Nullable UnlimitedNaturalValue upper) {
		if (!PivotUtil.hasDefaultCollectionValueBindings(isNullFree, lower, upper)) {
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
	protected boolean isUnspecialized(@NonNull Type keyType, @Nullable Boolean keysAreNullFree,
			@NonNull Type valueType, @Nullable Boolean valuesAreNullFree) {
		if (!PivotUtil.hasDefaultMapValueBindings(keysAreNullFree, valuesAreNullFree)) {
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
		try {
			return environmentFactory.getMetamodelManager().loadDefaultLibrary(uri);
		}
		finally {			// XXX phase out redundant check
			assert ((CompleteModelImpl)environmentFactory.getCompleteModel()).getSharedOrphanage().getStandardLibrary() == this;
		}
	}

	@Override
	public Type getOclType(@NonNull String typeName) {
		return environmentFactory.getMetamodelManager().getOclType(typeName);
	}

	@Override
	public void resetLibrary() {
		super.resetLibrary();
		libraryPackage = null;
		nameToLibraryTypeMap = null;
	}

	@Override
	public void setDefaultStandardLibraryURI(@NonNull String defaultStandardLibraryURI) {
		assert !PivotUtilInternal.isASURI(defaultStandardLibraryURI);
		this.defaultStandardLibraryURI = defaultStandardLibraryURI;
		this.explicitDefaultStandardLibraryURI = true;
	}
} //CompleteStandardLibraryImpl
