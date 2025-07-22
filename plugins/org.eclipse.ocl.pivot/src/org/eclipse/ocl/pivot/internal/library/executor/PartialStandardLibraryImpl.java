/*******************************************************************************
 * Copyright (c) 2011, 2022 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.internal.library.executor;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.AnyType;
import org.eclipse.ocl.pivot.BagType;
import org.eclipse.ocl.pivot.BooleanType;
import org.eclipse.ocl.pivot.CollectionType;
import org.eclipse.ocl.pivot.CompletePackage;
import org.eclipse.ocl.pivot.CompleteStandardLibrary;
import org.eclipse.ocl.pivot.DataType;
import org.eclipse.ocl.pivot.Enumeration;
import org.eclipse.ocl.pivot.EnumerationLiteral;
import org.eclipse.ocl.pivot.InvalidType;
import org.eclipse.ocl.pivot.MapType;
import org.eclipse.ocl.pivot.Model;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.OrderedSetType;
import org.eclipse.ocl.pivot.ParameterTypes;
import org.eclipse.ocl.pivot.PivotFactory;
import org.eclipse.ocl.pivot.PivotTables;
import org.eclipse.ocl.pivot.PrimitiveType;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.SequenceType;
import org.eclipse.ocl.pivot.SetType;
import org.eclipse.ocl.pivot.StandardLibrary;
import org.eclipse.ocl.pivot.TemplateParameter;
import org.eclipse.ocl.pivot.TemplateSignature;
import org.eclipse.ocl.pivot.TemplateableElement;
import org.eclipse.ocl.pivot.TupleType;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.TypedElement;
import org.eclipse.ocl.pivot.VoidType;
import org.eclipse.ocl.pivot.evaluation.Executor;
import org.eclipse.ocl.pivot.flat.EcoreFlatModel;
import org.eclipse.ocl.pivot.flat.FlatClass;
import org.eclipse.ocl.pivot.flat.FlatFragment;
import org.eclipse.ocl.pivot.flat.FlatModel;
import org.eclipse.ocl.pivot.ids.IdManager;
import org.eclipse.ocl.pivot.ids.IdResolver;
import org.eclipse.ocl.pivot.ids.PackageId;
import org.eclipse.ocl.pivot.ids.PartId;
import org.eclipse.ocl.pivot.ids.TupleTypeId;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.ClassImpl;
import org.eclipse.ocl.pivot.internal.EnumerationImpl;
import org.eclipse.ocl.pivot.internal.EnumerationLiteralImpl;
import org.eclipse.ocl.pivot.internal.NormalizedTemplateParameterImpl;
import org.eclipse.ocl.pivot.internal.OperationImpl;
import org.eclipse.ocl.pivot.internal.PackageImpl;
import org.eclipse.ocl.pivot.internal.ParameterImpl;
import org.eclipse.ocl.pivot.internal.PropertyImpl;
import org.eclipse.ocl.pivot.internal.StandardLibraryImpl;
import org.eclipse.ocl.pivot.internal.TemplateParameterImpl;
import org.eclipse.ocl.pivot.internal.TupleTypeImpl;
import org.eclipse.ocl.pivot.internal.manager.AbstractCollectionTypeManager;
import org.eclipse.ocl.pivot.internal.manager.AbstractJavaTypeManager;
import org.eclipse.ocl.pivot.internal.manager.AbstractLambdaTypeManager;
import org.eclipse.ocl.pivot.internal.manager.AbstractMapTypeManager;
import org.eclipse.ocl.pivot.internal.manager.AbstractTupleTypeManager;
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal;
import org.eclipse.ocl.pivot.library.LibraryFeature;
import org.eclipse.ocl.pivot.library.LibraryProperty;
import org.eclipse.ocl.pivot.manager.CollectionTypeManager;
import org.eclipse.ocl.pivot.manager.JavaTypeManager;
import org.eclipse.ocl.pivot.manager.LambdaTypeManager;
import org.eclipse.ocl.pivot.manager.MapTypeManager;
import org.eclipse.ocl.pivot.manager.SpecializedTypeManager;
import org.eclipse.ocl.pivot.manager.TupleTypeManager;
import org.eclipse.ocl.pivot.messages.StatusCodes;
import org.eclipse.ocl.pivot.oclstdlib.OCLstdlibTables;
import org.eclipse.ocl.pivot.options.PivotValidationOptions;
import org.eclipse.ocl.pivot.types.TemplateParameters;
import org.eclipse.ocl.pivot.utilities.MetamodelManager;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.PivotConstants;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.utilities.ThreadLocalExecutor;
import org.eclipse.ocl.pivot.values.TemplateParameterSubstitutions;

import com.google.common.collect.Lists;

/**
 * @since 7.0
 */
public abstract class PartialStandardLibraryImpl extends StandardLibraryImpl implements PartialStandardLibrary
{
	public static class ReadOnly extends PartialStandardLibraryImpl
	{
		public ReadOnly(org.eclipse.ocl.pivot.@NonNull Package... execPackages) {
			super(execPackages);
		}
	}

	public static class Mutable extends PartialStandardLibraryImpl
	{
		public Mutable(@NonNull ReadOnly readonlyStandardLibrary) {
			super(readonlyStandardLibrary);
		}

		@Override
		public @NonNull FlatClass getFlatClass(org.eclipse.ocl.pivot.@NonNull Class type) {
			ClassImpl asClass = (ClassImpl)type;
			FlatClass flatClass = asClass.basicGetFlatClass();
			if (flatClass == null) {
//				flatClass = type.getFlatClass(this);
				String name = asClass.getName();
				org.eclipse.ocl.pivot.Class asImmutableClass = NameUtil.getNameable(OCLstdlibTables.PACKAGE.getOwnedClasses(), name);
				if (asImmutableClass == null) {
					asImmutableClass = NameUtil.getNameable(PivotTables.PACKAGE.getOwnedClasses(), name);			// XXX generalize / promote / use extensions
				}
				assert asImmutableClass != null;
				asClass.setESObject(asImmutableClass.getESObject());
			//	asClass.setTypeId(asImmutableClass.getTypeId());
			//	asClass.setIsAbstract(asImmutableClass.isIsAbstract());
			//	initTemplateParameters(asClass, typeParameters);
				EcoreFlatModel flatModel = getFlatModel();
				flatClass = flatModel.getEcoreFlatClass(asClass);
				asClass.setFlatClass(flatClass);
			}
			else if (flatClass.getStandardLibrary() != this) {		// e.g populating mutable partial library wrt immutable
//				flatClass = type.getFlatClass(this);
				String name = asClass.getName();
				org.eclipse.ocl.pivot.Class asImmutableClass = NameUtil.getNameable(OCLstdlibTables.PACKAGE.getOwnedClasses(), name);
				if (asImmutableClass == null) {
					asImmutableClass = NameUtil.getNameable(PivotTables.PACKAGE.getOwnedClasses(), name);
				}
				assert asImmutableClass != null;
				asClass.setESObject(asImmutableClass.getESObject());
			//	asClass.setTypeId(asImmutableClass.getTypeId());
			//	asClass.setIsAbstract(asImmutableClass.isIsAbstract());
			//	initTemplateParameters(asClass, typeParameters);
				EcoreFlatModel flatModel = getFlatModel();
				flatClass = flatModel.getEcoreFlatClass(asClass);
			}
			return flatClass;
		}
	}

	public static class PartialCollectionTypeManager extends AbstractCollectionTypeManager
	{
		public PartialCollectionTypeManager(@NonNull StandardLibrary standardLibrary) {
			super(standardLibrary);
		}
	}

	/**
	 * @since 7.0
	 */
	public static class PartialJavaTypeManager extends AbstractJavaTypeManager
	{
		public PartialJavaTypeManager(@NonNull StandardLibrary standardLibrary) {
			super(standardLibrary);
		}
	}

	/**
	 * PartialLambdaTypeManager encapsulates the knowledge about known lambda types.
	 *
	 * @since 7.0
	 */
	public static class PartialLambdaTypeManager extends AbstractLambdaTypeManager
	{
		public PartialLambdaTypeManager(@NonNull StandardLibrary standardLibrary) {
			super(standardLibrary);
		}
	}

	/**
	 * @since 7.0
	 */
	public static class PartialMapTypeManager extends AbstractMapTypeManager
	{
		public PartialMapTypeManager(@NonNull StandardLibrary standardLibrary) {
			super(standardLibrary);
		}
	}

	public static class PartialTupleTypeManager extends AbstractTupleTypeManager
	{
		public PartialTupleTypeManager(@NonNull StandardLibrary standardLibrary) {
			super(standardLibrary);
		}

		@Override
		protected @NonNull TupleType createTupleType(@NonNull TupleTypeId tupleTypeId) {
			CompleteStandardLibrary completeStandardLibrary = (CompleteStandardLibrary)standardLibrary;
			EnvironmentFactoryInternal environmentFactory = completeStandardLibrary.getEnvironmentFactory();
			MetamodelManager metamodelManager = environmentFactory.getMetamodelManager();
			IdResolver idResolver = environmentFactory.getIdResolver();
			TupleType tupleType = new TupleTypeImpl(tupleTypeId);
			tupleType.setName(TypeId.TUPLE_NAME);
			@NonNull PartId[] partIds = tupleTypeId.getPartIds();
			List<Property> ownedAttributes = tupleType.getOwnedProperties();
			for (@NonNull PartId partId : partIds) {
				Type partType = idResolver.getType(partId.getTypeId());
				Type partType2 = metamodelManager.getPrimaryType(partType);
				Property property = PivotFactory.eINSTANCE.createProperty();
				property.setName(NameUtil.getSafeName(partId));
				property.setIsRequired(partId.isRequired());
				ownedAttributes.add(property);
				property.setType(partType2);			// After container to satisfy Property.setType assertIsNormalizedType
			}
			tupleType.getSuperClasses().add(standardLibrary.getOclTupleType());
			environmentFactory.addOrphanClass(tupleType);
			return tupleType;
		}
	}

	/**
	 * Configuration of validation preferences.
	 *
	 * The key used to be a magic String publicly exports from XXXTables polluting the API.
	 *
	 * Now it is the EOperation literal of the validation method.
	 */
	private /*LazyNonNull*/ Map<@Nullable Object, StatusCodes.@Nullable Severity> validationKey2severity = null;

	private @NonNull Map<@NonNull String, @NonNull WeakReference<org.eclipse.ocl.pivot.@NonNull Package>> ePackageMap = new WeakHashMap<>();		// Keys are interned
	private Map<org.eclipse.ocl.pivot.@NonNull Package, @NonNull WeakReference<org.eclipse.ocl.pivot.@NonNull Package>> asPackageMap = null;
	private /*@LazyNonNull*/ Map<org.eclipse.ocl.pivot.@NonNull Package, @NonNull List<org.eclipse.ocl.pivot.@NonNull Package>> extensions = null;
	private /*@LazyNonNull*/ org.eclipse.ocl.pivot.Class classType = null;
	private /*@LazyNonNull*/ org.eclipse.ocl.pivot.Class enumerationType = null;
	private final boolean mutable;			//XXX split into two classes

	protected PartialStandardLibraryImpl(org.eclipse.ocl.pivot.@NonNull Package ... execPackages) {
	//	OCLstdlibTables.PACKAGE.getClass();
		this.mutable = false;
		for (org.eclipse.ocl.pivot.@NonNull Package execPackage : execPackages) {
			assert execPackage != null;
			addPackage(execPackage, null);
		}
	}

	protected PartialStandardLibraryImpl(@NonNull PartialStandardLibraryImpl immutableStandardLibrary) {
		assert !immutableStandardLibrary.mutable;
		this.mutable = true;
		for (WeakReference<org.eclipse.ocl.pivot.@NonNull Package> execPackageRef : immutableStandardLibrary.ePackageMap.values()) {
			assert execPackageRef != null;
			org.eclipse.ocl.pivot.Package execPackage = execPackageRef.get();
			if (execPackage != null) {
				addPackage(execPackage, null);
			}
		}
		for (Map.Entry<org.eclipse.ocl.pivot.@NonNull Package, @NonNull List<org.eclipse.ocl.pivot.@NonNull Package>> entry : immutableStandardLibrary.extensions.entrySet()) {
			org.eclipse.ocl.pivot.@NonNull Package basePackage = entry.getKey();
			for (org.eclipse.ocl.pivot.@NonNull Package extensionPackage : entry.getValue()) {
				addExtension(basePackage, extensionPackage);
			}
		}
	}

//	@Override
//	public <R> R accept(@NonNull Visitor<R> visitor) {
//		throw new UnsupportedOperationException();
//	}

	/**
	 * @since 7.0
	 */
	public void addExtension(org.eclipse.ocl.pivot.@NonNull Package basePackage, org.eclipse.ocl.pivot.@NonNull Package extensionPackage) {
		Map<org.eclipse.ocl.pivot.@NonNull Package, @NonNull List<org.eclipse.ocl.pivot.@NonNull Package>> extensions2 = extensions;
		if (extensions2 == null) {
			extensions = extensions2 = new HashMap<>();
		}
		List<org.eclipse.ocl.pivot.@NonNull Package> list = extensions2.get(basePackage);
		if (list == null) {
			list = new ArrayList<>();
			extensions2.put(basePackage, list);
		}
		list.add(extensionPackage);
	}

	/**
	 * @since 7.0
	 */
	public synchronized void addPackage(org.eclipse.ocl.pivot.@NonNull Package execPackage, org.eclipse.ocl.pivot.@Nullable Package extendedPackage) {
		String uri = execPackage.getURI();
		assert uri != null;
		String internedURI = uri.intern();
		@SuppressWarnings("unused")
		WeakReference<org.eclipse.ocl.pivot.@NonNull Package> oldExecPackage = ePackageMap.put(internedURI, new WeakReference<>(execPackage));
		//		if ((oldExecPackage != null) && (oldExecPackage != execPackage)) {
		//			Iterable<ExecutorType> newTypes = execPackage.getOwnedType();
		//			for (DomainType oldType : oldExecPackage.getOwnedType()) {
		//				-- check for type compatibility
		//			}
		//		}
	}

	@Override
	protected @Nullable Type basicGetBehavioralType(@NonNull Type type) {
		if (type instanceof DataType) {
			return ((DataType)type).getBehavioralClass();
		}
		return null;
	}

	@Override
	public org.eclipse.ocl.pivot.@Nullable Class basicGetLibraryClass(@NonNull String className) {
		Map<org.eclipse.ocl.pivot.@NonNull Package, @NonNull List<org.eclipse.ocl.pivot.@NonNull Package>> extensions2 = extensions;
		if (extensions2 != null) {
			for (@NonNull List<org.eclipse.ocl.pivot.@NonNull Package> packages : extensions2.values()) {
				for (org.eclipse.ocl.pivot.@NonNull Package extensionPackage : packages) {
					org.eclipse.ocl.pivot.Class executorType = extensionPackage.getOwnedClass(className);
					if (executorType != null) {
						return executorType;
					}
				}
			}
		}
		return null;
	}

	/**
	 * @since 7.0
	 */
	public org.eclipse.ocl.pivot.@NonNull Class createClass(/*@NonNull*/ EClass eMetaClass, /*@NonNull*/ EClassifier eClassifier,
			org.eclipse.ocl.pivot.@NonNull Package asPackage, @Nullable TypeId typeId, int flags, @NonNull TemplateParameter @Nullable ... typeParameters) {
		assert eMetaClass != null;
		assert eClassifier != null;
		ClassImpl asClass = (ClassImpl)PivotFactory.eINSTANCE.create(eMetaClass);
		initClass(asClass, eClassifier, typeId, flags, typeParameters);
		return asClass;
	}

	@Override
	protected @NonNull CollectionTypeManager createCollectionTypeManager() {
		return new PartialCollectionTypeManager(this);
	}

	/**
	 * @since 7.0
	 */
	public @NonNull Enumeration createEnumeration(/*@NonNull*/ EEnum eEnum, org.eclipse.ocl.pivot.@NonNull Package asPackage) {
		assert eEnum != null;
		EnumerationImpl asClass = (EnumerationImpl)PivotFactory.eINSTANCE.createEnumeration();
		initClass(asClass, eEnum, null, 0);
		return asClass;
	}

	/**
	 * @since 7.0
	 */
	public @NonNull EnumerationLiteral createEnumerationLiteral(/*@NonNull*/ EEnumLiteral eEnumLiteral, @NonNull Enumeration asEnumeration, int ordinal) {
		assert eEnumLiteral != null;
		EnumerationLiteralImpl asEnumerationLiteral = (EnumerationLiteralImpl)PivotFactory.eINSTANCE.createEnumerationLiteral();
		asEnumerationLiteral.setName(eEnumLiteral.getName());
		asEnumerationLiteral.setESObject(eEnumLiteral);
	//	asEnumerationLiteral.setOrdinal(ordinal);
	//	asEnumeration.getOwnedLiterals().add(asEnumerationLiteral);
		return asEnumerationLiteral;
	}

	@Override
	protected @NonNull EcoreFlatModel createFlatModel() {
		return new EcoreFlatModel(this);
	}

	/**
	 * @since 7.0
	 */
	public @NonNull FlatFragment createFragment(org.eclipse.ocl.pivot.@NonNull Class cses, org.eclipse.ocl.pivot.@NonNull Class cses2) {
		return new FlatFragment(cses.getFlatClass(this), cses2.getFlatClass(this));
	}

	@Override
	protected @NonNull IdResolver createIdResolver() {
		Executor executor = ThreadLocalExecutor.basicGetExecutor();
		assert executor != null;
		return executor.getIdResolver();
	}

	@Override
	protected @NonNull JavaTypeManager createJavaTypeManager() {
		return new PartialJavaTypeManager(this);
	}

	/**
	 * @since 7.0
	 */
	public @NonNull TypedElement createLambdaParameter(String name, @NonNull Type type, boolean isRequired) {
		return LambdaTypeManager.createCandidateLambdaParameter(name, type, isRequired);
	}

	@Override
	protected @NonNull LambdaTypeManager createLambdaTypeManager() {
		return new PartialLambdaTypeManager(this);
	}

	@Override
	protected @NonNull MapTypeManager createMapTypeManager() {
		return new PartialMapTypeManager(this);
	}

	/**
	 * @since 7.0
	 */
	public @NonNull Operation createOperation(@NonNull String name, @NonNull ParameterTypes parameterTypes, org.eclipse.ocl.pivot.@NonNull Class asClass,
			int index, @NonNull TemplateParameters typeParameters, @Nullable LibraryFeature implementation) {
	//	return new ExecutorOperation(name, parameterTypes, asClass, index, typeParameters, implementation);
		OperationImpl asOperation = (OperationImpl)PivotFactory.eINSTANCE.createOperation();
		asOperation.setName(name);
	//	asOperation.setESObject(eOperation);
	//	asOperation.setIndex(index);
		asOperation.setImplementation(implementation);
		for (int i = 0; i < parameterTypes.size(); i++) {
			@NonNull Type parameterType = parameterTypes.get(i);
			ParameterImpl asParameter = (ParameterImpl)PivotFactory.eINSTANCE.createParameter();
			asParameter.setName("_" + i);
			asParameter.setType(parameterType);
			asOperation.getOwnedParameters().add(asParameter);
		}
		if (typeParameters.parametersSize() > 0) {
			TemplateSignature templateSignature = PivotFactory.eINSTANCE.createTemplateSignature();
			List<TemplateParameter> asTemplateParameters = templateSignature.getOwnedParameters();
			for (int i = 0; i < typeParameters.parametersSize(); i++) {
				Type type = typeParameters.get(i);		// XXX
				TemplateParameterImpl asTemplateParameter = (TemplateParameterImpl)PivotFactory.eINSTANCE.createTemplateParameter();
				asTemplateParameter.setName("_" + i);
			//	asTemplateParameter.setType(type);
			//	asTemplateParameter.setTemplateParameterId(templateParameter.getTemplateParameterId());
				asTemplateParameters.add(asTemplateParameter);
			}
			asOperation.setOwnedSignature(templateSignature);
		}
		asClass.getOwnedOperations().add(asOperation);
		return asOperation;
	}

	/**
	 * @since 7.0
	 */
	public @NonNull Property createOppositeProperty(@NonNull String name, org.eclipse.ocl.pivot.@NonNull Class asClass, int propertyIndex, /*@NonNull*/ EStructuralFeature eFeature) {
		assert eFeature != null;
	//	EcoreLibraryOppositeProperty oppositeProperty = new EcoreLibraryOppositeProperty(eFeature);
	//	return new ExecutorPropertyWithImplementation(name, executorType, propertyIndex, oppositeProperty);
		PropertyImpl asProperty = (PropertyImpl)PivotFactory.eINSTANCE.createProperty();
		asProperty.setName(eFeature.getName());
		asProperty.setESObject(eFeature);
	//	asProperty.setIndex(propertyIndex);
	//	asProperty.setImplementation(implementation);
		asClass.getOwnedProperties().add(asProperty);
		return asProperty;
	}

	/**
	 * @since 7.0
	 */
	public org.eclipse.ocl.pivot.@NonNull Package createPackage(/*@NonNull*/ EPackage ePackage, @Nullable PackageId packageId) {
		assert ePackage != null;
		PackageImpl asPackage = (PackageImpl)PivotFactory.eINSTANCE.createPackage();
		asPackage.setName(ePackage.getName());
		asPackage.setNsPrefix(ePackage.getNsPrefix());
		asPackage.setURI(ePackage.getNsURI());
		asPackage.setPackageId(packageId != null ? packageId : IdManager.getPackageId(ePackage));
		asPackage.setESObject(ePackage);
		return asPackage;
	}

	/**
	 * @since 7.0
	 */
	public @NonNull Property createProperty(@NonNull String name, org.eclipse.ocl.pivot.@NonNull Class asClass, int propertyIndex, @NonNull LibraryProperty implementation) {
	//	return new ExecutorPropertyWithImplementation(name, asClass, propertyIndex, implementation);
		PropertyImpl asProperty = (PropertyImpl)PivotFactory.eINSTANCE.createProperty();
		asProperty.setName(name);
	//	asProperty.setIndex(propertyIndex);
		asProperty.setImplementation(implementation);
		asClass.getOwnedProperties().add(asProperty);
		return asProperty;
	}

	/**
	 * @since 7.0
	 */
	public @NonNull Property createProperty(/*@NonNull*/ EStructuralFeature eFeature, org.eclipse.ocl.pivot.@NonNull Class asClass, int propertyIndex) {
		assert eFeature != null;
	//	return new EcoreExecutorProperty(eFeature, asClass, propertyIndex);
		PropertyImpl asProperty = (PropertyImpl)PivotFactory.eINSTANCE.createProperty();
		asProperty.setESObject(eFeature);
		asProperty.setName(eFeature.getName());
	//	asProperty.setIndex(propertyIndex);
	//	asProperty.setImplementation(implementation);
		asClass.getOwnedProperties().add(asProperty);
		return asProperty;
	}

	@Override
	protected @Nullable SpecializedTypeManager createSpecializedTypeManager() {
		return null;
	}

	/**
	 * @since 7.0
	 */
	public @NonNull TemplateParameter createTemplateParameter(int index, @NonNull String name) {
	//	ExecutorTypeParameter executorTypeParameter = new ExecutorTypeParameter(index, name);
		NormalizedTemplateParameterImpl asTemplateParameter = (NormalizedTemplateParameterImpl)PivotFactory.eINSTANCE.createNormalizedTemplateParameter();
		asTemplateParameter.setName(name);
	//	asTemplateParameter.setIndex(index);
//XXX		asTemplateParameter.setTemplateParameterId(IdManager.getTemplateParameterId(index));
	//	asTemplateParameter.setIndex(index);
	//	EcoreFlatModel flatModel = getFlatModel();
	//	FlatClass flatClass = flatModel.getFlatClass(executorTypeParameter);
	//	executorTypeParameter.setFlatClass(flatClass);
		return asTemplateParameter;
	}

	@Override
	protected @NonNull TupleTypeManager createTupleTypeManager() {
		return new PartialTupleTypeManager(this);
	}

	protected @NonNull HashMap<@Nullable Object, StatusCodes.@Nullable Severity> createValidationKey2severityMap() {
		return PivotValidationOptions.createValidationKey2severityMap();
	}

	@Override
	public @NonNull Iterable<@NonNull ? extends CompletePackage> getAllCompletePackages() {
		throw new UnsupportedOperationException();
	}

	@Override
	public @NonNull BagType getBagType() {
		return (BagType) OCLstdlibTables.Types._Bag;
	}

	@Override
	public @NonNull BooleanType getBooleanType() {
		return (BooleanType) OCLstdlibTables.Types._Boolean;
	}

	@Override
	public org.eclipse.ocl.pivot.@NonNull Class getClassType() {
		Map<org.eclipse.ocl.pivot.@NonNull Package, @NonNull List<org.eclipse.ocl.pivot.@NonNull Package>> extensions2 = extensions;
		if (extensions2 == null) {
			throw new IllegalStateException("No extension package registered to define Class type"); //$NON-NLS-1$
		}
		if (classType != null) {
			return classType;
		}
		classType = basicGetLibraryClass(TypeId.CLASS_NAME);
		if (classType != null) {
			return classType;
		}
		throw new IllegalStateException("No extension package defines Class type"); //$NON-NLS-1$
	}

	/**
	 * @since 7.0
	 */
	@Override
	public @NonNull CollectionType getCollectionType() {
		return (CollectionType) OCLstdlibTables.Types._Collection;
	}

	/**
	 * @since 7.0
	 */
	public @NonNull CollectionType getCollectionType(org.eclipse.ocl.pivot.@NonNull Class genericType, @NonNull Type elementType) {
		return getCollectionType((CollectionType)genericType, elementType, PivotConstants.DEFAULT_IS_NULL_FREE, PivotConstants.DEFAULT_LOWER_BOUND, PivotConstants.DEFAULT_UPPER_BOUND);
	}

	protected org.eclipse.ocl.pivot.@NonNull Class getCommonTupleType(@NonNull TupleType thisType, @NonNull Type thatType) {
		if (thisType != thatType) {
			return getOclAnyType();
		}
		return thisType;				// XXX missing code
	}

	@Override
	public org.eclipse.ocl.pivot.@NonNull Class getEnumerationType() {
		Map<org.eclipse.ocl.pivot.@NonNull Package, @NonNull List<org.eclipse.ocl.pivot.@NonNull Package>> extensions2 = extensions;
		if (extensions2 == null) {
			throw new IllegalStateException("No extension package registered to define Enumeration type"); //$NON-NLS-1$
		}
		if (enumerationType != null) {
			return enumerationType;
		}
		enumerationType = basicGetLibraryClass(TypeId.ENUMERATION_NAME);
		if (enumerationType != null) {
			return enumerationType;
		}
		throw new IllegalStateException("No extension package defines Enumeration type"); //$NON-NLS-1$
	}

	@Override
	public @NonNull FlatClass getFlatClass(org.eclipse.ocl.pivot.@NonNull Class type) {
		return ((ClassImpl)type).getFlatClass();
	}

	@Override
	public @NonNull EcoreFlatModel getFlatModel() {
		return (EcoreFlatModel)super.getFlatModel();
	}

	@Override
	public @NonNull PrimitiveType getIntegerType() {
		return (PrimitiveType) OCLstdlibTables.Types._Integer;
	}

	@Override
	public @NonNull LambdaTypeManager getLambdaManager() {
		assert lambdaTypeManager != null;
		return lambdaTypeManager;
	}

	/**
	 * @since 7.0
	 */
	public @NonNull Type getLambdaType(@NonNull TypedElement context, @NonNull TypedElement result, @NonNull TypedElement ... parameters) {
		List<@NonNull TypedElement> parameterList = parameters != null ? Lists.newArrayList(parameters) : Collections.emptyList();
		return getLambdaManager().getLambdaType(context, parameterList, result, null);
	}

	@Override
	public @NonNull MapType getMapType() {
		return (MapType) OCLstdlibTables.Types._Map;
	}

	public @NonNull MapType getMapType(org.eclipse.ocl.pivot.@NonNull Class genericType, @NonNull Type keyType, @NonNull Type valueType) {
		return getMapType(keyType, PivotConstants.DEFAULT_IS_NULL_FREE, valueType, PivotConstants.DEFAULT_IS_NULL_FREE);
	}

/*	@Override
	public org.eclipse.ocl.pivot.@Nullable Class getNestedType(org.eclipse.ocl.pivot.@NonNull Package parentPackage, @NonNull String name) {
		org.eclipse.ocl.pivot.Class nestedType = NameUtil.getNameable(parentPackage.getOwnedClasses(), name);
		if (nestedType != null) {
			return nestedType;
		}
		nestedType = getPivotType(name);
		return nestedType;
	} */

	@Override
	public org.eclipse.ocl.pivot.Package getNsURIPackage(@NonNull String nsURI) {
		WeakReference<org.eclipse.ocl.pivot.@NonNull Package> weakReference = ePackageMap.get(nsURI.intern());
		if (weakReference == null) {
			return null;
		}
		return weakReference.get();
	}

	/**
	 * @since 1.14
	 */
	@Override
	public @NonNull Set<@NonNull String> getNsURIs() {
		return ePackageMap.keySet();
	}

	@Override
	public @NonNull AnyType getOclAnyType() {
		return (AnyType) OCLstdlibTables.Types._OclAny;
	}

	@Override
	public org.eclipse.ocl.pivot.@NonNull Class getOclComparableType() {
		return OCLstdlibTables.Types._OclComparable;
	}

	@Override
	public org.eclipse.ocl.pivot.@NonNull Class getOclElementType() {
		return OCLstdlibTables.Types._OclElement;
	}

	/**
	 * @since 1.1
	 */
	@Override
	public org.eclipse.ocl.pivot.@NonNull Class getOclEnumerationType() {
		return OCLstdlibTables.Types._OclEnumeration;
	}

	@Override
	public @NonNull Operation getOclInvalidOperation() {
		throw new UnsupportedOperationException();
	}

	/**
	 * @since 1.4
	 */
	@Override
	public @NonNull Property getOclInvalidProperty() {
		throw new UnsupportedOperationException();
	}

	@Override
	public @NonNull InvalidType getOclInvalidType() {
		return (InvalidType) OCLstdlibTables.Types._OclInvalid;
	}

	@Override
	public org.eclipse.ocl.pivot.@NonNull Class getOclLambdaType() {
		return OCLstdlibTables.Types._OclLambda;
	}

	@Override
	public org.eclipse.ocl.pivot.@NonNull Class getOclMessageType() {
		return OCLstdlibTables.Types._OclMessage;
	}

	@Override
	public org.eclipse.ocl.pivot.@NonNull Class getOclSelfType() {
		return OCLstdlibTables.Types._OclSelf;
	}

	/**
	 * @since 1.1
	 */
	@Override
	public org.eclipse.ocl.pivot.@NonNull Class getOclStereotypeType() {
		return OCLstdlibTables.Types._OclStereotype;
	}

	@Override
	public org.eclipse.ocl.pivot.@NonNull Class getOclSummableType() {
		return OCLstdlibTables.Types._OclSummable;
	}

	@Override
	public org.eclipse.ocl.pivot.@NonNull Class getOclTupleType() {
		return OCLstdlibTables.Types._OclTuple;
	}

	@Override
	public org.eclipse.ocl.pivot.@NonNull Class getOclTypeType() {
		return OCLstdlibTables.Types._OclType;
	}

	@Override
	public @NonNull VoidType getOclVoidType() {
		return (VoidType) OCLstdlibTables.Types._OclVoid;
	}

	@Override
	public @NonNull CollectionType getOrderedCollectionType() {
		return (CollectionType) OCLstdlibTables.Types._OrderedCollection;
	}

	@Override
	public @NonNull OrderedSetType getOrderedSetType() {
		return (OrderedSetType) OCLstdlibTables.Types._OrderedSet;
	}

	@Override
	public org.eclipse.ocl.pivot.@NonNull Package getPackage() {
		return OCLstdlibTables.PACKAGE;
	}

	@Override
	public synchronized org.eclipse.ocl.pivot.@Nullable Package getPackage(@NonNull EPackage ePackage) {
		String nsURI = ePackage.getNsURI();
		return nsURI != null ? weakGet(ePackageMap, nsURI.intern()) : null;
	}

	@Override
	public @NonNull Type getPrimaryType(@NonNull Type asType) {
		return asType;
	}

	@Override
	public @NonNull PrimitiveType getRealType() {
		return (PrimitiveType) OCLstdlibTables.Types._Real;
	}

	@Override
	public @NonNull SequenceType getSequenceType() {
		return (SequenceType) OCLstdlibTables.Types._Sequence;
	}

	@Override
	public @NonNull SetType getSetType() {
		return (SetType) OCLstdlibTables.Types._Set;
	}

	public StatusCodes.@Nullable Severity getSeverity(@Nullable Object validationKey) {
		Map<@Nullable Object, StatusCodes.@Nullable Severity> validationKey2severity2 = validationKey2severity;
		if (validationKey2severity2 == null) {
			validationKey2severity = validationKey2severity2 = createValidationKey2severityMap();
		}
		return validationKey2severity2.get(validationKey);
	}

	@Override
	public @NonNull Type getSpecializedType(@NonNull Type type, @Nullable TemplateParameterSubstitutions substitutions) {
		throw new UnsupportedOperationException();
	}

	@Override
	public @NonNull PrimitiveType getStringType() {
		return (PrimitiveType) OCLstdlibTables.Types._String;
	}

	@Override
	public @NonNull CollectionType getUniqueCollectionType() {
		return (CollectionType) OCLstdlibTables.Types._UniqueCollection;
	}

	@Override
	public @NonNull PrimitiveType getUnlimitedNaturalType() {
		return (PrimitiveType) OCLstdlibTables.Types._UnlimitedNatural;
	}

	private void initClass(@NonNull ClassImpl asClass, @NonNull EClassifier eClassifier, @Nullable TypeId typeId, int flags, @NonNull TemplateParameter @Nullable... typeParameters) {
		asClass.setESObject(eClassifier);
		asClass.setName(eClassifier.getName());
		if (typeId != null) {
			asClass.setTypeId(typeId);
//XXX			asClass.setNormalizedTypeId(typeId);
		}
		asClass.setIsAbstract((flags & FlatClass.ABSTRACT) != 0);
		initTemplateParameters(asClass, typeParameters);
		/*Ecore*/FlatModel flatModel = getFlatModel();
		FlatClass flatClass = flatModel./*Ecore*/getFlatClass(asClass);
		asClass.setFlatClass(flatClass);
	}

	/**
	 * @since 7.0
	 */
	public void initLiterals(@NonNull Enumeration asEnumeration, @NonNull EnumerationLiteral @NonNull [] asEnumerationLiterals) {
		List<EnumerationLiteral> asLiterals = asEnumeration.getOwnedLiterals();
		for (@NonNull EnumerationLiteral asEnumerationLiteral : asEnumerationLiterals) {
			asLiterals.add(asEnumerationLiteral);
		}
	}

	/**
	 * @since 7.0
	 */
	public void initPackage(org.eclipse.ocl.pivot.@NonNull Package asPackage, org.eclipse.ocl.pivot.@NonNull Class @NonNull [] asClasses) {
		Object eContainer = asPackage.eContainer();
		assert eContainer == null;
		Model asModel = PivotFactory.eINSTANCE.createModel();
		asModel.setExternalURI(asPackage.getURI());
		asModel.getOwnedPackages().add(asPackage);
	//	EcoreFlatModel flatModel = (EcoreFlatModel)asModel.initFlatModel(this);
		List<org.eclipse.ocl.pivot.Class> ownedClasses = asPackage.getOwnedClasses();
		for (org.eclipse.ocl.pivot.@NonNull Class asClass : asClasses) {
			ownedClasses.add(asClass);
		}
		addPackage(asPackage, null);
	}

	private <T extends CollectionType> void initTemplateParameters(@NonNull TemplateableElement pivotType, @NonNull TemplateParameter @Nullable... templateParameters) {
		if ((templateParameters != null) && (templateParameters.length > 0)) {
			TemplateSignature templateSignature = PivotFactory.eINSTANCE.createTemplateSignature();
			List<TemplateParameter> asTemplateParameters = templateSignature.getOwnedParameters();
			for (@NonNull TemplateParameter templateParameter : templateParameters) {
				TemplateParameterImpl asTemplateParameter = (TemplateParameterImpl)PivotFactory.eINSTANCE.createTemplateParameter();
				asTemplateParameter.setName(templateParameter.getName());
// XXX				asTemplateParameter.setTemplateParameterId(templateParameter.getTemplateParameterId());
				asTemplateParameters.add(asTemplateParameter);
			}
			pivotType.setOwnedSignature(templateSignature);
		}
	}

	public boolean isMutable() {
		return mutable;
	}

	public void resetSeverities() {
		validationKey2severity = null;
	}

	/**
	 * Return the map.get(key).get() entry if there is one or null if not, removing any stale
	 * entry that may be encountered.
	 */
	private <K, V> @Nullable V weakGet(@NonNull Map<K, @NonNull WeakReference<V>> map, @NonNull K key) {
		WeakReference<V> ref = map.get(key);
		if (ref == null) {
			return null;
		}
		@Nullable V value = ref.get();
		if (value == null) {
			map.remove(key);
		}
		return value;
	}
}
