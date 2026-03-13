
/** * Copyright (c) 2010, 2025 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 */
package org.eclipse.ocl.pivot.internal.library;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.AnyType;
import org.eclipse.ocl.pivot.BagType;
import org.eclipse.ocl.pivot.BooleanType;
import org.eclipse.ocl.pivot.CollectionType;
import org.eclipse.ocl.pivot.CompleteClass;
import org.eclipse.ocl.pivot.CompleteModel;
import org.eclipse.ocl.pivot.CompletePackage;
import org.eclipse.ocl.pivot.InvalidType;
import org.eclipse.ocl.pivot.Iteration;
import org.eclipse.ocl.pivot.LambdaType;
import org.eclipse.ocl.pivot.Library;
import org.eclipse.ocl.pivot.MapType;
import org.eclipse.ocl.pivot.Model;
import org.eclipse.ocl.pivot.NormalizedTemplateParameter;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.OrderedSetType;
import org.eclipse.ocl.pivot.Parameter;
import org.eclipse.ocl.pivot.PivotFactory;
import org.eclipse.ocl.pivot.PrimitiveType;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.SelfType;
import org.eclipse.ocl.pivot.SequenceType;
import org.eclipse.ocl.pivot.SetType;
import org.eclipse.ocl.pivot.TemplateArgument;
import org.eclipse.ocl.pivot.TemplateParameter;
import org.eclipse.ocl.pivot.TemplateableElement;
import org.eclipse.ocl.pivot.TupleType;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.VoidType;
import org.eclipse.ocl.pivot.flat.CompleteFlatModel;
import org.eclipse.ocl.pivot.flat.FlatClass;
import org.eclipse.ocl.pivot.ids.CollectionTypeId;
import org.eclipse.ocl.pivot.ids.CompletePackageId;
import org.eclipse.ocl.pivot.ids.IdResolver;
import org.eclipse.ocl.pivot.ids.TemplateParameterId;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.complete.PartialPackages;
import org.eclipse.ocl.pivot.internal.manager.AbstractCollectionTypeManager;
import org.eclipse.ocl.pivot.internal.manager.AbstractJavaTypeManager;
import org.eclipse.ocl.pivot.internal.manager.AbstractLambdaTypeManager;
import org.eclipse.ocl.pivot.internal.manager.AbstractMapTypeManager;
import org.eclipse.ocl.pivot.internal.manager.AbstractSpecializedTypeManager;
import org.eclipse.ocl.pivot.internal.manager.AbstractTupleTypeManager;
import org.eclipse.ocl.pivot.internal.manager.Orphanage;
import org.eclipse.ocl.pivot.internal.manager.PivotMetamodelManager;
import org.eclipse.ocl.pivot.internal.messages.PivotMessagesInternal;
import org.eclipse.ocl.pivot.internal.plugin.CompletePackageIdRegistryReader;
import org.eclipse.ocl.pivot.internal.utilities.IllegalLibraryException;
import org.eclipse.ocl.pivot.library.LibraryConstants;
import org.eclipse.ocl.pivot.library.oclany.OclAnyUnsupportedOperation;
import org.eclipse.ocl.pivot.manager.CollectionTypeManager;
import org.eclipse.ocl.pivot.manager.JavaTypeManager;
import org.eclipse.ocl.pivot.manager.LambdaTypeManager;
import org.eclipse.ocl.pivot.manager.MapTypeManager;
import org.eclipse.ocl.pivot.manager.SpecializedTypeManager;
import org.eclipse.ocl.pivot.manager.TupleTypeManager;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.EnvironmentFactory;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.PivotConstants;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.values.CollectionTypeArguments;
import org.eclipse.ocl.pivot.values.IntegerValue;
import org.eclipse.ocl.pivot.values.MapTypeArguments;
import org.eclipse.ocl.pivot.values.TemplateArguments;
import org.eclipse.ocl.pivot.values.UnlimitedNaturalValue;
import org.eclipse.osgi.util.NLS;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Standard Library Internal</b></em>'.
 * <!-- end-user-doc -->
 *
 * @since 7.0
 */
public class CompleteStandardLibrary extends ConcreteStandardLibrary
{
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CompleteStandardLibrary()
	{
		super();
	}

	/**
	 * CompleteCollectionTypeManager encapsulates the knowledge about known collection types.
	 *
	 * @since 7.0
	 */
	public static class CompleteCollectionTypeManager extends AbstractCollectionTypeManager
	{
		@Override
		protected boolean isValid(@Nullable Type type) {
			return (type != null) && (type.eResource() != null);		// XXX Built-in have null Resource
		}
	}

	/**
	 * CompleteJavaTypeManager encapsulates the knowledge about known java types.
	 *
	 * @since 7.0
	 */
	protected static class CompleteJavaTypeManager extends AbstractJavaTypeManager
	{
		protected CompleteJavaTypeManager(@NonNull CompleteStandardLibrary standardLibrary) {
			super(standardLibrary);
		}
	}

	/**
	 * CompleteLambdaTypeManager encapsulates the knowledge about known lambda types.
	 *
	 * @since 7.0
	 */
	public static class CompleteLambdaTypeManager extends AbstractLambdaTypeManager
	{
		public CompleteLambdaTypeManager(@NonNull CompleteStandardLibrary standardLibrary) {
			super(standardLibrary);
		}
	}

	/**
	 * CompleteMapTypeManager encapsulates the knowledge about known map types.
	 *
	 * @since 7.0
	 */
	public static class CompleteMapTypeManager extends AbstractMapTypeManager
	{
		public CompleteMapTypeManager(@NonNull CompleteStandardLibrary standardLibrary) {
			super(standardLibrary);
		}

		@Override
		protected boolean isValid(@Nullable Type type) {
			return (type != null) && (type.eResource() != null);
		}
	}

	/**
	 * CompleteSpecializedTypeManager encapsulates the knowledge about known specialized types.
	 *
	 * @since 7.0
	 */
	public static class CompleteSpecializedTypeManager extends AbstractSpecializedTypeManager
	{
		public CompleteSpecializedTypeManager(@NonNull CompleteStandardLibrary standardLibrary) {
			super(standardLibrary);
		}
	}

	/**
	 * CompleteTupleTypeManager encapsulates the knowledge about known tuple types.
	 * @since 7.0
	 */
	public static class CompleteTupleTypeManager extends AbstractTupleTypeManager
	{
		public CompleteTupleTypeManager(@NonNull CompleteStandardLibrary standardLibrary) {
			super(standardLibrary);
		}
	}

//	private static final Logger logger = Logger.getLogger(CompleteStandardLibrary.class);

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

	private @Nullable Map<@NonNull String, org.eclipse.ocl.pivot.@NonNull Class> nameToLibraryTypeMap = null;

	/**
	 * All Library packages imported into the current type managed domain.
	 */
	protected final @NonNull List<@NonNull Library> asLibraries = new ArrayList<>();

	private boolean libraryLoadInProgress = false;

	private /*final*/ /*@NonNull*/ CompleteModel completeModel;
	private /*final*/ /*@NonNull*/ EnvironmentFactory environmentFactory;

	private @Nullable Orphanage orphanage = null;

	@Override
	public void addOrphanClass(org.eclipse.ocl.pivot.@NonNull Class pivotElement) {
		assert pivotElement.eContainer() == null;
		if (pivotElement.getGeneric() != null) {
			assert pivotElement.getGeneric().getGeneric() == null;
		}
		else {
			assert (pivotElement instanceof LambdaType)
			|| (pivotElement instanceof TupleType);
		}
		assert environmentFactory != null;
		pivotElement.setOwningPackage(getOrphanage());
	}

	@Override
	public @Nullable PrimitiveType basicGetBehavioralClass(java.lang.@NonNull Class<?> instanceClass) {
		return (PrimitiveType)PivotUtil.basicGetBehavioralClass(this, instanceClass);
	}

	@Override
	public @Nullable Type basicGetBehavioralType(@NonNull Type type) {
		CompleteClass completeClass = completeModel.getCompleteClass(type);
		Type behavioralType = completeClass.getBehavioralClass();
		return behavioralType;
	}

	public @Nullable CompletePackage basicGetCompletePackage(@NonNull CompletePackageId completePackageId) {
		return completeModel.basicGetCompletePackage(completePackageId);
	}

	@Override
	public org.eclipse.ocl.pivot.@Nullable Class basicGetLibraryClass(@NonNull String typeName) {
		Map<@NonNull String, org.eclipse.ocl.pivot.@NonNull Class> nameToLibraryTypeMap2 = nameToLibraryTypeMap;
		if (nameToLibraryTypeMap2 == null) {
			nameToLibraryTypeMap = nameToLibraryTypeMap2 = new HashMap<>();
			boolean gotLibrary = false;
			for (Library asLibrary : asLibraries) {
				defineLibraryTypes(asLibrary);				// XXX ?? use installRootContents instead
				String packageURI = asLibrary.getURI();
				if (packageURI != null) {			// QVT roots may be blank
					CompletePackageId completePackageId = CompletePackageIdRegistryReader.basicGetCompletePackageId(packageURI);
					if (completePackageId == PivotConstants.METAMODEL_ID) {
						if (asLibrary.getOwnedClass("Collection") != null) {
							gotLibrary = true;
							//	break;
						}
					}
				}
			}
			if (!gotLibrary) {
				loadDefaultLibrary(defaultStandardLibraryURI);
			}
		}
		return nameToLibraryTypeMap2.get(typeName);
	}

	public @Nullable AnyType basicGetOclAnyType() {
		return oclAnyType;
	}

	public @Nullable Operation basicGetOclInvalidOperation() {
		return oclInvalidOperation;
	}

	public @Nullable Property basicGetOclInvalidProperty() {
		return oclInvalidProperty;
	}

	public @Nullable InvalidType basicGetOclInvalidType() {
		return oclInvalidType;
	}

	@Override
	public org.eclipse.ocl.pivot.@Nullable Package basicGetOrphanage() {
		return orphanage;
	}

	/**
	 * Return -ve if match1 is inferior to match2, +ve if match2 is inferior to match1, or
	 * zero if both matches are of equal validity.
	 *
	 * @since 7.0
	 */
	public int compareOperationMatches(@NonNull Operation referenceOperation, @Nullable TemplateArguments referenceBindings,
			@NonNull Operation candidateOperation, @Nullable TemplateArguments candidateBindings) {
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
	protected @NonNull CollectionTypeManager createCollectionTypeManager() {
		return new CompleteCollectionTypeManager();
	}

	@Override
	protected @NonNull CompleteFlatModel createFlatModel() {
		assert completeModel != null;
		return new CompleteFlatModel(this, completeModel);
	}

	@Override
	protected @NonNull IdResolver createIdResolver() {
		return environmentFactory.getIdResolver();
	}

	@Override
	protected @NonNull JavaTypeManager createJavaTypeManager() {
		return new CompleteJavaTypeManager(this);
	}

	@Override
	protected @NonNull LambdaTypeManager createLambdaTypeManager() {
		return new CompleteLambdaTypeManager(this);
	}

	@Override
	protected @NonNull MapTypeManager createMapTypeManager() {
		return new CompleteMapTypeManager(this);
	}

	@Override
	protected @NonNull SpecializedTypeManager createSpecializedTypeManager() {
		return new CompleteSpecializedTypeManager(this);
	}

	@Override
	protected @NonNull TupleTypeManager createTupleTypeManager() {
		return new CompleteTupleTypeManager(this);
	}

	/**
	 * Merge all types in asLibrary into the overall Standard Library.
	 */
	private void defineLibraryTypes(@NonNull Library asLibrary) {
		List<org.eclipse.ocl.pivot.@NonNull Class> asClasses = null;
		for (org.eclipse.ocl.pivot.Class asClass : PivotUtil.getOwnedClasses(asLibrary)) {
			Type asPrimaryType = asClass; // XXX getPrimaryType(asClass);
			if ((asClass == asPrimaryType) && !PivotUtil.isOrphanType(asClass)) {
				if (asClasses == null) {
					asClasses = new ArrayList<>();
				}
				asClasses.add(asClass);
			}
		}
		if (asClasses != null) {
			defineLibraryTypes(asClasses);
		}
	}

	public void defineLibraryTypes(@NonNull Iterable<org.eclipse.ocl.pivot.@NonNull Class> pivotTypes) {
		Map<@NonNull String, org.eclipse.ocl.pivot.@NonNull Class> nameToLibraryTypeMap2 = nameToLibraryTypeMap;
		if (nameToLibraryTypeMap2 == null) {
			nameToLibraryTypeMap = nameToLibraryTypeMap2 = new HashMap<>();
		}
		for (org.eclipse.ocl.pivot.@NonNull Class pivotType : pivotTypes) {
			String name = pivotType.getName();
			if (name != null) {
				if ("Model".equals(name) || "UnlimitedNatural".equals(name)) {
					getClass();		// XXX
				}
				//
				// Multiple libraries may exploit CompleteClasses, so nameToLibraryTypeMap caches just the first.
				if (!nameToLibraryTypeMap2.containsKey(name)) {
					@SuppressWarnings("unused")
					org.eclipse.ocl.pivot.Class oldType = nameToLibraryTypeMap2.put(name, pivotType);
				/*	if ((oldType != null) && (oldType != pivotType)) {
						if (!(oldType instanceof PrimitiveType) || !(pivotType instanceof PrimitiveType)) {		// User primitives may only be DataType e.g. testQVTrLoad_ATL2QVTr_qvtre
						//	logger.warn("Conflicting pivot type '" + name + "'");
							System.err.println("Conflicting pivot type '" + name + "'");
							// qvtruntimelibrary has two an extra definition of Model from QVTimperativeLibrary
						}
					} */
				}
			}
		}
	}

	public void dispose() {
		resetLibrary();
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
	public @NonNull CollectionType getCollectionType(@NonNull CollectionType genericType,
			@NonNull Type elementType, boolean isNullFree, @Nullable IntegerValue lower, @Nullable UnlimitedNaturalValue upper) {
		assert completeModel != null;
		genericType = (CollectionType) completeModel.getPrimaryClass(genericType);
		elementType = completeModel.getPrimaryType(elementType);
		assert genericType == PivotUtil.getGenericElement(genericType);
		return super.getCollectionType(genericType, elementType, isNullFree, lower, upper);
	}

	public @NonNull CompleteModel getCompleteModel() {
		assert completeModel != null;
		return completeModel;
	}

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
	public @NonNull EnvironmentFactory getEnvironmentFactory() {
		return ClassUtil.requireNonNull(environmentFactory);
	}

	@Override
	@NonNull
	public FlatClass getFlatClass(org.eclipse.ocl.pivot.@NonNull Class type) {
		assert completeModel != null;
		return completeModel.getFlatClass(type);
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
	public @NonNull CompleteFlatModel getFlatModel() {
		return (CompleteFlatModel)super.getFlatModel();
	}

	public @Nullable Resource getLibraryResource() {
		return asLibraries.isEmpty() ? null : asLibraries.get(0).eResource();
	}

	public @NonNull List<@NonNull Library> getLibraries() { return asLibraries; }

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
		assert mapType == PivotUtil.getGenericElement(mapType);
		if ((keyType == mapType.getKeyType()) && (valueType == mapType.getValueType())) {
			return mapType;		// XXX ??? never happens now that NormalizedTemplatedParameter in use
		}
		return super.getMapType(keyType, keysAreNullFree, valueType, valuesAreNullFree);
	}

	@Override
	public org.eclipse.ocl.pivot.Package getNsURIPackage(@NonNull String nsURI) {
		CompletePackage completePackage = completeModel.basicGetCompletePackageForURI(nsURI);
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
			oclLambdaType2 = oclLambdaType = resolveRequiredSimpleType(org.eclipse.ocl.pivot.Class.class, TypeId.OCL_LAMBDA_NAME);
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
	public @NonNull Orphanage getOrphanage() {
		Orphanage orphanage2 = orphanage;
		if (orphanage2 == null) {
			orphanage2 = orphanage = Orphanage.getOrphanage(environmentFactory.getASResourceSet());
			@SuppressWarnings("unused") CompletePackage completePackage = completeModel.getCompletePackage(orphanage2);
		//	OrphanCompletePackageImpl orphanCompletePackage2 = getOrphanCompletePackage();
		//	package2completePackage.put(orphanage2, orphanCompletePackage2);
		//	packageURI2completePackage.put(PivotConstants.ORPHANAGE_URI, orphanCompletePackage2);
			Model orphanModel = PivotUtil.getContainingModel(orphanage2);
			//	didAddPartialModel(orphanModel);
			assert completeModel.getPartialModels().contains(orphanModel);
		//	PartialPackages partialPackages = getOrphanCompletePackage().getPartialPackages();
		//	orphanage2.addPackageListener(partialPackages);
		//	for (org.eclipse.ocl.pivot.@NonNull Package asPackage : PivotUtil.getOwnedPackages(orphanage2)) {
		//		didAddPackage(asPackage);
		//	}
		}
		return orphanage2;
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
		assert completeModel != null;
		return completeModel.getPrimaryType(asType);
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
		org.eclipse.ocl.pivot.Class type = basicGetLibraryClass(typeName);
		if (type == null) {
			//			nameToLibraryTypeMap = null;
			type = basicGetLibraryClass(typeName);	// FIXME just a debug retry
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

	public org.eclipse.ocl.pivot.@Nullable Package getRootPackage(@NonNull String completeURIorName) {
		throw new UnsupportedOperationException();
/*		Package rootPackage = completeModel.getRootPackage(completeURIorName);
		if (rootPackage == null) {
			if (PivotConstants.METAMODEL_NAME.equals(completeURIorName)) {
				assert environmentFactory != null;
				environmentFactory.getMetamodelManager().getASmetamodel();
				rootPackage = completeModel.getRootPackage(completeURIorName);
			}
		}
		return rootPackage; */
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

	public @NonNull CompleteStandardLibrary init(@NonNull EnvironmentFactory environmentFactory) {
		this.environmentFactory = environmentFactory;
		this.completeModel = environmentFactory.getCompleteModel();
		return this;
	}

	/**
	 * Create and install the implicit opposite of asProperty with the default name.
	 * @since 7.0
	 */
	public void installImplicitOppositeProperty(@NonNull Property asProperty) {
		org.eclipse.ocl.pivot.Class thisClass = PivotUtil.getOwningClass(asProperty);
		String oppositeName = thisClass.getName();
		if (oppositeName == null) {
			return;
		}
		installImplicitOppositeProperty(asProperty, oppositeName);
	}

	public void installLibrary() {
		if (!libraryLoadInProgress && (getLibraryResource() == null) && (asLibraries.size() > 0)) {
			getOclAnyType();
		}
	}

	/**
	 * @since 7.0
	 */
	public void installLibrary(@NonNull Library asLibrary) {
		if (!asLibraries.contains(asLibrary)) {
			String uri = asLibrary.getURI();
			if (asLibraries.isEmpty()) {
				if (uri == null) {
					throw new IllegalLibraryException(PivotMessagesInternal.MissingLibraryURI_ERROR_);
				}
				if (!explicitDefaultStandardLibraryURI) {
					for (org.eclipse.ocl.pivot.@NonNull Class asClass : PivotUtil.getOwnedClasses(asLibrary)) {
						if (TypeId.OCL_ANY_NAME.equals(asClass.getName())) {
							setDefaultStandardLibraryURI(uri);
							break;
						}
					}
				}
			}
			asLibraries.add(asLibrary);
			if (getLibraryResource() != null) {
				defineLibraryTypes(asLibrary);
			}
		}
	}

	public boolean isLibraryLoadInProgress() {
		return libraryLoadInProgress;
	}

/*	public boolean isOrdered(Type sourceType) {
		if (sourceType instanceof OrderedSetType) {
			return true;
		}
		if (sourceType instanceof SequenceType) {
			return true;
		}
		return false;
	} */

/*	public boolean isUnique(Type sourceType) {
		if (sourceType instanceof OrderedSetType) {
			return true;
		}
		if (sourceType instanceof SetType) {
			return true;
		}
		return false;
	} */

	/**
	 * Load the Standard Library for a given uri. If the uri corresponds to a registered library, that library
	 * is installed, otherwise the already loaded asLibraries are examined and the first library with a matching
	 * URI is used. Return the resource of the library, and merges all types of all libraries into the overall
	 * standard library.
	 */
	private @Nullable Resource loadDefaultLibrary(@Nullable String uri) {
		if (uri == null) {
			return null;
		}
		if ((nameToLibraryTypeMap != null) && nameToLibraryTypeMap.containsKey("Collection")) {
			return getLibraryResource();
		}
		boolean savedLibraryLoadInProgress = libraryLoadInProgress;
		libraryLoadInProgress = true;
		Resource asLibraryResource = null;
		try {
			StandardLibraryContribution contribution = StandardLibraryContribution.REGISTRY.get(uri);
			if (contribution != null) {
				asLibraryResource = contribution.getResource();
			}
			else {
				for (@NonNull Library asLibrary : asLibraries) {
					if (uri.equals(asLibrary.getURI())) {
						asLibraryResource = asLibrary.eResource();
						break;
					}
				}
				if (asLibraryResource == null) {
					return null;
				}
			}
			int size = asLibraries.size();
			PivotMetamodelManager metamodelManager = (PivotMetamodelManager)getEnvironmentFactory().getMetamodelManager();
			metamodelManager.installResource(asLibraryResource);
			for (int i = 0; i < size; i++) {
				defineLibraryTypes(asLibraries.get(i));
			}
			return asLibraryResource;
		}
		finally {
			libraryLoadInProgress = savedLibraryLoadInProgress;
		}
	}

	public @Nullable Resource loadLibraryResource(@NonNull String uri) {
		if (uri.equals(getDefaultStandardLibraryURI())) {
			Resource asLibraryResource = getLibraryResource();
			if (asLibraryResource != null) {
				return asLibraryResource;
			}
			else {
				return loadDefaultLibrary(uri);
			}
		}
		else {
			StandardLibraryContribution contribution = StandardLibraryContribution.REGISTRY.get(uri);
			if (contribution != null) {
				return contribution.getResource();
			}
			else {
				return null;
			}
		}
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
		asLibraries.clear();
		Orphanage orphanage2 = orphanage;
		if (orphanage2 != null) {
			orphanage2.removePackageListener((PartialPackages)completeModel.getOrphanCompletePackage().getPartialPackages());
			orphanage = null;
		}
		super.resetLibrary();
	}

	protected @NonNull <T extends TemplateableElement> T resolveRequiredSimpleType(java.lang.@NonNull Class<@NonNull T> requiredClassType, @NonNull String name) {
		org.eclipse.ocl.pivot.Class type = getRequiredLibraryType(name);
		return requiredClassType.cast(type);
	}

	protected @NonNull <T extends TemplateableElement> T resolveRequiredTemplateableType(java.lang.@NonNull Class<T> requiredClassType, @NonNull String name, int parameterCount) {
		org.eclipse.ocl.pivot.Class type = getRequiredLibraryType(name);
		if (requiredClassType.isAssignableFrom(type.getClass())) {
			List<@NonNull TemplateParameter> asTemplateParameters = type.basicGetOwnedTemplateParameters();
			if (asTemplateParameters == null) {
				throw new IllegalLibraryException(name + " is not a templated type");
			}
			else if (asTemplateParameters.size() != parameterCount) {
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
		List<@NonNull TemplateArgument> specializedTemplateArguments = specializedClass.basicGetOwnedTemplateArguments();
		for (org.eclipse.ocl.pivot.@NonNull Class superClass : PivotUtil.getSuperClasses(unspecializedClass)) {
			List<@NonNull TemplateArgument> superTemplateArguments = superClass.basicGetOwnedTemplateArguments();
			if (superTemplateArguments != null) {
				List<@NonNull TemplateArgument> superSpecializedTemplateArguments = new ArrayList<>();
				for (@NonNull TemplateArgument superTemplateArgument : superTemplateArguments) {
					TemplateArgument superSpecializedTemplateArgument = null;
					Type superActual = PivotUtil.getActual(superTemplateArgument);
					if (specializedTemplateArguments != null) {
						for (@NonNull TemplateArgument specializedTemplateArgument : specializedTemplateArguments) {
							TemplateParameter specializedFormal = PivotUtil.getFormal(specializedTemplateArgument);
							TemplateParameterId specializedTemplateParameterId = specializedFormal.getTemplateParameterId();
							int specializedIndex = specializedTemplateParameterId.getIndex();
							if (specializedFormal == superActual) {
								Type specializedActual = PivotUtil.getActual(specializedTemplateArgument);
								superSpecializedTemplateArgument = PivotUtil.createTemplateArgument(specializedActual);
								break;
							}
							else if (superActual instanceof NormalizedTemplateParameter) {
								int superIndex = ((NormalizedTemplateParameter)superActual).getIndex();
								if (specializedIndex == superIndex) {
									Type specializedActual = PivotUtil.getActual(specializedTemplateArgument);
									superSpecializedTemplateArgument = PivotUtil.createTemplateArgument(specializedActual);
									break;
								}
							}
						}
					}
					if (superSpecializedTemplateArgument != null) {
						superSpecializedTemplateArguments.add(superSpecializedTemplateArgument);
					}
				}
				org.eclipse.ocl.pivot.@NonNull Class genericSuperClass = PivotUtil.getGenericElement(superClass);
				CompleteClass superCompleteClass = completeModel.getCompleteClass(genericSuperClass);
				org.eclipse.ocl.pivot.Class superPivotClass = superCompleteClass.getPrimaryClass();
				if (superPivotClass instanceof CollectionType) {
					CollectionType specializedCollection = (CollectionType)specializedClass;
					if (superSpecializedTemplateArguments.size() == 1) {
						Type templateArgument = superSpecializedTemplateArguments.get(0).getActual();
						if (templateArgument != null) {
							CollectionTypeArguments typeArguments = new CollectionTypeArguments((CollectionTypeId) superPivotClass.getTypeId(), templateArgument,
								specializedCollection.isIsNullFree(), specializedCollection.getLowerValue(), specializedCollection.getUpperValue());
							org.eclipse.ocl.pivot.Class specializedSuperClass = getCollectionType(typeArguments);
							specializedClass.getSuperClasses().add(specializedSuperClass);
						}
					}
				}
				else if (superPivotClass instanceof MapType) {
					MapType specializedMap = (MapType)specializedClass;
					if (superSpecializedTemplateArguments.size() == 2) {
						Type keyArgument = superSpecializedTemplateArguments.get(0).getActual();
						Type valueArgument = superSpecializedTemplateArguments.get(1).getActual();
						if ((keyArgument != null) && (valueArgument != null)) {
							MapTypeArguments typeArguments = new MapTypeArguments(keyArgument, specializedMap.isKeysAreNullFree(), valueArgument, specializedMap.isValuesAreNullFree());
							org.eclipse.ocl.pivot.Class specializedSuperClass = getMapType(typeArguments);
							specializedClass.getSuperClasses().add(specializedSuperClass);
						}
					}
				}
				else {
					List<@NonNull Type> superTemplateArgumentList = new ArrayList<>(superSpecializedTemplateArguments.size());
					for (TemplateArgument superSpecializedTemplateArgument : superSpecializedTemplateArguments) {
						Type actual = superSpecializedTemplateArgument.getActual();
						if (actual != null) {
							superTemplateArgumentList.add(actual);
						}
					}
					FlatClass superFlatClass = superCompleteClass.getFlatClass();
					org.eclipse.ocl.pivot.Class genericSuperType = superFlatClass.getPivotClass(); //getCompleteClass().getPrimaryClass();
					org.eclipse.ocl.pivot.Class specializedSuperType = getSpecializedType(genericSuperType, superTemplateArgumentList);
					specializedClass.getSuperClasses().add(specializedSuperType);
				}
			}
			else {
				specializedClass.getSuperClasses().add(superClass);
			}
		}
	}

	public void setDefaultStandardLibraryURI(@NonNull String defaultStandardLibraryURI) {
		assert !PivotUtil.isASURI(URI.createURI(defaultStandardLibraryURI));
		this.defaultStandardLibraryURI = defaultStandardLibraryURI;
		this.explicitDefaultStandardLibraryURI = true;
	}

	public void setLibraryLoadInProgress(boolean libraryLoadInProgress) {
		this.libraryLoadInProgress = libraryLoadInProgress;
	}
} //CompleteStandardLibraryImpl
