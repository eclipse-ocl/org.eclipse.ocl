/*******************************************************************************
 * Copyright (c) 2011, 2021 Willink Transformations and others.
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
import java.util.Collection;
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
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.AnyType;
import org.eclipse.ocl.pivot.BagType;
import org.eclipse.ocl.pivot.BooleanType;
import org.eclipse.ocl.pivot.Class;
import org.eclipse.ocl.pivot.CollectionType;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.Enumeration;
import org.eclipse.ocl.pivot.EnumerationLiteral;
import org.eclipse.ocl.pivot.InvalidType;
import org.eclipse.ocl.pivot.MapType;
import org.eclipse.ocl.pivot.Model;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.OrderedSetType;
import org.eclipse.ocl.pivot.Orphanage;
import org.eclipse.ocl.pivot.Parameter;
import org.eclipse.ocl.pivot.PivotFactory;
import org.eclipse.ocl.pivot.PrimitiveType;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.SequenceType;
import org.eclipse.ocl.pivot.SetType;
import org.eclipse.ocl.pivot.TemplateParameter;
import org.eclipse.ocl.pivot.TemplateSignature;
import org.eclipse.ocl.pivot.TemplateableElement;
import org.eclipse.ocl.pivot.TupleType;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.TypedElement;
import org.eclipse.ocl.pivot.VoidType;
import org.eclipse.ocl.pivot.flat.EcoreFlatModel;
import org.eclipse.ocl.pivot.flat.FlatClass;
import org.eclipse.ocl.pivot.flat.FlatFragment;
import org.eclipse.ocl.pivot.ids.IdManager;
import org.eclipse.ocl.pivot.ids.PackageId;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.ClassImpl;
import org.eclipse.ocl.pivot.internal.EnumerationImpl;
import org.eclipse.ocl.pivot.internal.EnumerationLiteralImpl;
import org.eclipse.ocl.pivot.internal.LambdaTypeImpl;
import org.eclipse.ocl.pivot.internal.OrphanageImpl;
import org.eclipse.ocl.pivot.internal.PackageImpl;
import org.eclipse.ocl.pivot.internal.PropertyImpl;
import org.eclipse.ocl.pivot.internal.StandardLibraryImpl;
import org.eclipse.ocl.pivot.internal.TemplateParameterImpl;
import org.eclipse.ocl.pivot.internal.TupleTypeImpl;
import org.eclipse.ocl.pivot.library.LibraryFeature;
import org.eclipse.ocl.pivot.library.LibraryProperty;
import org.eclipse.ocl.pivot.messages.StatusCodes;
import org.eclipse.ocl.pivot.oclstdlib.OCLstdlibTables;
import org.eclipse.ocl.pivot.options.PivotValidationOptions;
import org.eclipse.ocl.pivot.util.Visitor;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.utilities.TypeUtil;
import org.eclipse.ocl.pivot.values.IntegerValue;
import org.eclipse.ocl.pivot.values.TemplateParameterSubstitutions;
import org.eclipse.ocl.pivot.values.UnlimitedNaturalValue;

public class ExecutorStandardLibrary extends StandardLibraryImpl
{
	/**
	 * OperationParameterDecoder decodes the type 'stream' used to define an operation paramter list in the
	 * compact XXXTables formulation.
	 */
	protected static class OperationParameterDecoder
	{
		protected final @NonNull ExecutorStandardLibrary standardLibrary;
		protected final @NonNull Operation asOperation;
		protected final @NonNull Object @NonNull [] parameterTypes;
		private int parameterTypesIndex = 0;

		public OperationParameterDecoder(@NonNull ExecutorStandardLibrary standardLibrary, @NonNull Operation asOperation, @NonNull Object @NonNull [] parameterTypes) {
			this.standardLibrary = standardLibrary;
			this.asOperation = asOperation;
			this.parameterTypes = parameterTypes;
		}

		public void decode() {
			List<Parameter> asParameters = asOperation.getOwnedParameters();
			while (parameterTypesIndex < parameterTypes.length) {
				Type parameterType = decodeNextType();
				Parameter asParameter = PivotFactory.eINSTANCE.createParameter();
				asParameter.setName("_" + asParameters.size());
				asParameter.setType(parameterType);
				asParameters.add(asParameter);
			}
			assert parameterTypesIndex == parameterTypes.length;
		}

		protected org.eclipse.ocl.pivot.@NonNull Class decodeClassType(org.eclipse.ocl.pivot.@NonNull Class genericClass) {
			List<@NonNull TemplateParameter> templateParameters = PivotUtil.getTemplateParameters(genericClass);
			if (templateParameters == null) {
				return genericClass;
			}
			throw new UnsupportedOperationException();		// could be done, but not needed
		}

		protected org.eclipse.ocl.pivot.@NonNull Class decodeCollectionType(@NonNull CollectionType genericClass) {
			List<@NonNull TemplateParameter> templateParameters = PivotUtil.getTemplateParameters(genericClass);
			if (templateParameters == null) {
				return genericClass;
			}
			Type elementType = decodeNextType();
			return standardLibrary.getCollectionType(genericClass, elementType, null, null, null);
		}

		protected org.eclipse.ocl.pivot.@NonNull Class decodeLambdaType(@NonNull Operation asOperation, @NonNull String name) {
			int parameterIndex = (Integer)parameterTypes[parameterTypesIndex++];
			LambdaTypeImpl asClass = (LambdaTypeImpl)PivotFactory.eINSTANCE.createLambdaType();
			asClass.setName(name);
			TypeId typeId = decodeTemplateParameter(asOperation, parameterIndex).getTypeId();
			asClass.setTypeId(typeId);
			asClass.setNormalizedTypeId(typeId);
		//	initTemplateParameters(asClass, typeArguments);
		//	EcoreFlatModel flatModel = getFlatModel();
		//	FlatClass flatClass = flatModel.getEcoreFlatClass(asClass);
		//	asClass.setFlatClass(flatClass);
			return asClass;
		}

		protected org.eclipse.ocl.pivot.@NonNull Class decodeMapType(@NonNull MapType genericClass) {
			List<@NonNull TemplateParameter> templateParameters = PivotUtil.getTemplateParameters(genericClass);
			if (templateParameters == null) {
				return genericClass;
			}
			Type keyType = decodeNextType();
			Type valueType = decodeNextType();
			return standardLibrary.getMapType(keyType, null, valueType, null);
		}

		protected @NonNull Type decodeNextType() {
			@NonNull Object parameterKey = parameterTypes[parameterTypesIndex++];
			if (parameterKey instanceof Integer) {
				return decodeTemplateParameter(asOperation, (int)parameterKey);
			}
			else if (parameterKey instanceof CollectionType) {
				return decodeCollectionType((CollectionType)parameterKey);
			}
			else if (parameterKey instanceof MapType) {
				return decodeMapType((MapType)parameterKey);
			}
			else if (parameterKey instanceof org.eclipse.ocl.pivot.Class) {
				return decodeClassType((org.eclipse.ocl.pivot.Class)parameterKey);
			}
			else if ("Lambda".equals(parameterKey)) {
				return decodeLambdaType(asOperation, (String)parameterKey);
			}
			else {
				throw new UnsupportedOperationException();
			}
		}

		protected @NonNull TemplateParameter decodeTemplateParameter(@NonNull Operation asOperation, int parameterIndex) {
			List<@NonNull TemplateParameter> templateParameters = PivotUtil.getTemplateParameters(asOperation);
			assert templateParameters != null;
			TemplateParameter templateParameter = templateParameters.get(parameterIndex);
			assert templateParameter != null;
			return templateParameter;
		}
	}

//	private @NonNull Orphanage orphanage = new Orphanage(PivotConstants.ORPHANAGE_NAME, PivotConstants.ORPHANAGE_URI);
	private final @NonNull Orphanage orphanage = OrphanageImpl.createSharedOrphanage(this, new ResourceSetImpl());

	/**
	 * Configuration of validation preferences.
	 *
	 * The key used to be a magic String publicly exports from XXXTables polluting the API.
	 *
	 * Now it is the EOperation literal of the validation method.
	 */
	private /*LazyNonNull*/ Map<@Nullable Object, StatusCodes.@Nullable Severity> validationKey2severity = null;

	private EcoreFlatModel ecoreFlatModel = null;

	private @NonNull Map<@NonNull String, @NonNull WeakReference<org.eclipse.ocl.pivot.@NonNull Package>> ePackageMap = new WeakHashMap<>();		// Keys are interned
	private /*@LazyNonNull*/ Map<org.eclipse.ocl.pivot.@NonNull Package, @NonNull List<org.eclipse.ocl.pivot.@NonNull Package>> extensions = null;
	private /*@LazyNonNull*/ org.eclipse.ocl.pivot.Class classType = null;
	private /*@LazyNonNull*/ org.eclipse.ocl.pivot.Class enumerationType = null;

	public ExecutorStandardLibrary(org.eclipse.ocl.pivot.@NonNull Package... execPackages) {
	//	OCLstdlibTables.PACKAGE.getClass();
		orphanage.eResource().getContents().add(this);
		for (org.eclipse.ocl.pivot.@NonNull Package execPackage : execPackages) {
			assert execPackage != null;
			addPackage(execPackage, null);
		}
	}

	@Override
	public <R> R accept(@NonNull Visitor<R> visitor) {
		throw new UnsupportedOperationException();
	}

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
	public @NonNull List<Element> allOwnedElements() {
		throw new UnsupportedOperationException();
	}

	@Override
	public @Nullable AnyType basicGetOclAnyType() {
		return (AnyType)OCLstdlibTables.Types._OclAny;
	}

	@Override
	public @Nullable InvalidType basicGetOclInvalidType() {
		return (InvalidType)OCLstdlibTables.Types._OclInvalid;
	}

	public org.eclipse.ocl.pivot.@NonNull Class createClass(/*@NonNull*/ EClass eMetaClass, /*@NonNull*/ EClassifier eClassifier,
			org.eclipse.ocl.pivot.@NonNull Package asPackage, @Nullable TypeId typeId, int flags, @NonNull TemplateParameter @Nullable ... typeParameters) {
		assert eMetaClass != null;
		assert eClassifier != null;
		ClassImpl asClass = (ClassImpl)PivotFactory.eINSTANCE.create(eMetaClass);
		initClass(asClass, eClassifier, typeId, flags, typeParameters);
		return asClass;
	}

	public @NonNull Enumeration createEnumeration(/*@NonNull*/ EEnum eEnum, org.eclipse.ocl.pivot.@NonNull Package asPackage) {
		assert eEnum != null;
		EnumerationImpl asClass = (EnumerationImpl)PivotFactory.eINSTANCE.createEnumeration();
		initClass(asClass, eEnum, null, 0);
		return asClass;
	}

	public @NonNull EnumerationLiteral createEnumerationLiteral(/*@NonNull*/ EEnumLiteral eEnumLiteral, @NonNull Enumeration asEnumeration, int ordinal) {
		assert eEnumLiteral != null;
		EnumerationLiteralImpl asEnumerationLiteral = (EnumerationLiteralImpl)PivotFactory.eINSTANCE.createEnumerationLiteral();
		asEnumerationLiteral.setName(eEnumLiteral.getName());
		asEnumerationLiteral.setESObject(eEnumLiteral);
	//	asEnumerationLiteral.setOrdinal(ordinal);
	//	asEnumeration.getOwnedLiterals().add(asEnumerationLiteral);
		return asEnumerationLiteral;
	}

	public @NonNull FlatFragment createFragment(org.eclipse.ocl.pivot.@NonNull Class cses, org.eclipse.ocl.pivot.@NonNull Class cses2) {
		return new FlatFragment(cses.getFlatClass(this), cses2.getFlatClass(this));
	}

	public @NonNull Type createLambdaType(@NonNull String name, @NonNull Type @NonNull ... typeArguments) {
		LambdaTypeImpl asClass = (LambdaTypeImpl)PivotFactory.eINSTANCE.createLambdaType();
		asClass.setName(name);
		TypeId typeId = IdManager.getLambdaTypeId(name, IdManager.getParametersId(typeArguments));
		asClass.setTypeId(typeId);
		asClass.setNormalizedTypeId(typeId);
	//	initTemplateParameters(asClass, typeArguments);
	//	EcoreFlatModel flatModel = getFlatModel();
	//	FlatClass flatClass = flatModel.getEcoreFlatClass(asClass);
	//	asClass.setFlatClass(flatClass);
		return asClass;
	}

	public @NonNull Operation createOperation(@NonNull String name, @NonNull Object @Nullable [] parameterTypes, org.eclipse.ocl.pivot.@NonNull Class asClass,
			int index, @Nullable Object templateParameterOrTemplateParameters, @Nullable LibraryFeature implementation) {
		Operation asOperation = PivotFactory.eINSTANCE.createOperation();
		asOperation.setName(name);
	//	asOperation.setESObject(eOperation);	-- doesn't exist
	//	asOperation.setIndex(index);			-- re-instate and exploit for faster virtual dispatch
		asOperation.setImplementation(implementation);
		if (templateParameterOrTemplateParameters != null) {
			TemplateSignature templateSignature = PivotFactory.eINSTANCE.createTemplateSignature();
			List<TemplateParameter> asTemplateParameters = templateSignature.getOwnedParameters();
			int iMax = templateParameterOrTemplateParameters instanceof TemplateParameter ? 1 : ((TemplateParameter[])templateParameterOrTemplateParameters).length;
			for (int i = 0; i < iMax; i++) {
				TemplateParameter templateParameter = iMax == 1 ? (TemplateParameter)templateParameterOrTemplateParameters : ((TemplateParameter[])templateParameterOrTemplateParameters)[i];
				TemplateParameterImpl asTemplateParameter = (TemplateParameterImpl)PivotFactory.eINSTANCE.createTemplateParameter();
			//	asTemplateParameter.setName("_" + i);
				asTemplateParameter.setName(templateParameter.getName());
				asTemplateParameter.setTemplateParameterId(templateParameter.getTemplateParameterId());
				asTemplateParameters.add(asTemplateParameter);
			}
			asOperation.setOwnedSignature(templateSignature);
		}
		asClass.getOwnedOperations().add(asOperation);
		if (parameterTypes != null) {
			OperationParameterDecoder operationParameterDecoder = new OperationParameterDecoder(this, asOperation, parameterTypes);
			operationParameterDecoder.decode();
		}
		return asOperation;
	}

	public @NonNull Property createOppositeProperty(@NonNull String name, org.eclipse.ocl.pivot.@NonNull Class asClass, int propertyIndex, /*@NonNull*/ EStructuralFeature eFeature) {
		assert eFeature != null;
		PropertyImpl asProperty = (PropertyImpl)PivotFactory.eINSTANCE.createProperty();
		asProperty.setName(eFeature.getName());
		asProperty.setESObject(eFeature);
	//	asProperty.setIndex(propertyIndex);
	//	asProperty.setImplementation(implementation);
		asClass.getOwnedProperties().add(asProperty);
		return asProperty;
	}

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

	public @NonNull Property createProperty(@NonNull String name, org.eclipse.ocl.pivot.@NonNull Class asClass, int propertyIndex, @NonNull LibraryProperty implementation) {
		PropertyImpl asProperty = (PropertyImpl)PivotFactory.eINSTANCE.createProperty();
		asProperty.setName(name);
	//	asProperty.setIndex(propertyIndex);
		asProperty.setImplementation(implementation);
		asClass.getOwnedProperties().add(asProperty);
		return asProperty;
	}

	public @NonNull Property createProperty(/*@NonNull*/ EStructuralFeature eFeature, org.eclipse.ocl.pivot.@NonNull Class asClass, int propertyIndex) {
		assert eFeature != null;
		PropertyImpl asProperty = (PropertyImpl)PivotFactory.eINSTANCE.createProperty();
		asProperty.setESObject(eFeature);
		asProperty.setName(eFeature.getName());
	//	asProperty.setIndex(propertyIndex);
	//	asProperty.setImplementation(implementation);
		asClass.getOwnedProperties().add(asProperty);
		return asProperty;
	}

	public @NonNull TemplateParameter createTemplateParameter(int index, @NonNull String name) {
		TemplateParameterImpl asTemplateParameter = (TemplateParameterImpl)PivotFactory.eINSTANCE.createTemplateParameter();
		asTemplateParameter.setName(name);
		asTemplateParameter.setTemplateParameterId(IdManager.getTemplateParameterId(index));
	//	asTemplateParameter.setIndex(index);
		return asTemplateParameter;
	}

	public @NonNull Property createTuplePart(@NonNull String name, @NonNull Type type) {
		PropertyImpl asTuplePart = (PropertyImpl)PivotFactory.eINSTANCE.createProperty();
		asTuplePart.setName(name);
		asTuplePart.setType(type);
		return asTuplePart;
	}

	public @NonNull Type createTupleType(@NonNull String name, @NonNull Property... parts) {
		TupleTypeImpl asTupleType = (TupleTypeImpl)PivotFactory.eINSTANCE.createTupleType();
		asTupleType.setName(name);
		for (@NonNull Property part : parts) {
			asTupleType.getOwnedProperties().add(part);
		}
		return asTupleType;
	}

	protected @NonNull HashMap<@Nullable Object, StatusCodes.@Nullable Severity> createValidationKey2severityMap() {
		return PivotValidationOptions.createValidationKey2severityMap();
	}

	@Override
	public @NonNull BagType getBagType() {
		return (BagType)OCLstdlibTables.Types._Bag;
	}

	@Override
	public @NonNull BooleanType getBooleanType() {
		return (BooleanType)OCLstdlibTables.Types._Boolean;
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
		classType = getPivotType(TypeId.CLASS_NAME);
		if (classType != null) {
			return classType;
		}
		throw new IllegalStateException("No extension package defines Class type"); //$NON-NLS-1$
	}

	@Override
	public @NonNull CollectionType getCollectionType() {
		return (CollectionType)OCLstdlibTables.Types._Collection;
	}

	@Deprecated // Short argument list with defaults used by auto-gen
	public org.eclipse.ocl.pivot.@NonNull Class getCollectionType(org.eclipse.ocl.pivot.@NonNull Class genericType, @NonNull Type elementType) {
		return getCollectionType((CollectionType)genericType, elementType, null, null, null);
	}

	@Override
	public @Nullable Type getCommonTupleType(@NonNull TupleType leftType, @NonNull TemplateParameterSubstitutions leftSubstitutions,
			@NonNull TupleType rightType, @NonNull TemplateParameterSubstitutions rightSubstitutions) {
		throw new UnsupportedOperationException();
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
		enumerationType = getPivotType(TypeId.ENUMERATION_NAME);
		if (enumerationType != null) {
			return enumerationType;
		}
		throw new IllegalStateException("No extension package defines Enumeration type"); //$NON-NLS-1$
	}

	@Override
	public @NonNull EcoreFlatModel getFlatModel() {
		EcoreFlatModel ecoreFlatModel2 = ecoreFlatModel;
		if (ecoreFlatModel2 == null) {
			ecoreFlatModel = ecoreFlatModel2 = new EcoreFlatModel(this);
		//	throw new UnsupportedOperationException();
		}
		return ecoreFlatModel2;
	}

	@Override
	public @NonNull PrimitiveType getIntegerType() {
		return (PrimitiveType)OCLstdlibTables.Types._Integer;
	}

	@Override
	public Class getLibraryType(@NonNull String typeName) {
		throw new UnsupportedOperationException();			// XXX do this
	}

	@Override
	public @NonNull MapType getMapOfEntryType(org.eclipse.ocl.pivot.@NonNull Class entryClass) {
		throw new UnsupportedOperationException();				// Only happens for Entry classes
	/*	MapTypeParameters<@NonNull Type, @NonNull Type> typeParameters = TypeUtil.createMapTypeParameters(entryClass);
		MapType specializedType = null;
		Map<@NonNull MapTypeParameters<@NonNull Type, @NonNull Type>, @NonNull WeakReference<@Nullable MapType>> map = mapSpecializations.get(genericType);
		if (map == null) {
			map = new WeakHashMap<>();
			mapSpecializations.put(genericType, map);
		}
		else {
			specializedType = weakGet(map, typeParameters);
		}
		if (specializedType == null) {
		//	specializedType = new ExecutorMapType(ClassUtil.nonNullModel(genericType.getName()), genericType, typeParameters.getKeyType(), typeParameters.getValueType());
			specializedType = PivotUtil.createMapType((MapType)genericType, typeParameters.getKeyType(), typeParameters.getValueType());
		//	specializedType.setKeysAreNullFree(keyValuesAreNullFree);
		//	specializedType.setValuesAreNullFree(valuesAreNullFree);
			map.put(typeParameters, new WeakReference<>(specializedType));
		}
		return specializedType; */
	}

	@Override
	public @NonNull MapType getMapType() {
		return (MapType)OCLstdlibTables.Types._Map;
	}

	@Override
	public org.eclipse.ocl.pivot.@NonNull Class getMetaclass(@NonNull Type asInstanceType) {
		String metaclassName = TypeUtil.getMetaclassName(asInstanceType);
		return ClassUtil.nonNullState(getPivotType(metaclassName));
	}

	@Override
	public org.eclipse.ocl.pivot.@Nullable Package getNestedPackage(org.eclipse.ocl.pivot.@NonNull Package parentPackage, @NonNull String name) {
		return NameUtil.getNameable(parentPackage.getOwnedPackages(), name);
	}

	@Override
	public org.eclipse.ocl.pivot.@Nullable Class getNestedType(org.eclipse.ocl.pivot.@NonNull Package parentPackage, @NonNull String name) {
		org.eclipse.ocl.pivot.Class nestedType = NameUtil.getNameable(parentPackage.getOwnedClasses(), name);
		if (nestedType != null) {
			return nestedType;
		}
		nestedType = getPivotType(name);
		return nestedType;
	}

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
		return (AnyType)OCLstdlibTables.Types._OclAny;
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
	public @NonNull InvalidType getOclInvalidType() {
		return (InvalidType)OCLstdlibTables.Types._OclInvalid;
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
	public synchronized org.eclipse.ocl.pivot.Class getOclType(@NonNull String typeName) {
		for (@NonNull WeakReference<org.eclipse.ocl.pivot.@NonNull Package> dPackage : ePackageMap.values()) {
			// FIXME			if (OCLstdlibTables.PACKAGE.getNsURI().equals(dPackage.getNsURI())) {
		//	if (dPackage != null) {
				org.eclipse.ocl.pivot.Package packageRef = dPackage.get();
				if (packageRef != null) {
					org.eclipse.ocl.pivot.Class type = packageRef.getOwnedClass(typeName);
					if (type != null) {
						return type;
					}
				}
		//	}
		}
		return null;
	}

	@Override
	public org.eclipse.ocl.pivot.@NonNull Class getOclTypeType() {
		throw new UnsupportedOperationException();				// XXX FIX this irregularity
	}

	@Override
	public @NonNull VoidType getOclVoidType() {
		return (VoidType)OCLstdlibTables.Types._OclVoid;
	}

	@Override
	public @NonNull Orphanage getOrphanage() {
		return orphanage;
	}

	@Override
	public @NonNull CollectionType getOrderedCollectionType() {
		return (CollectionType) OCLstdlibTables.Types._OrderedCollection;
	}

	@Override
	public @NonNull OrderedSetType getOrderedSetType() {
		return (OrderedSetType)OCLstdlibTables.Types._OrderedSet;
	}

	@Override
	public org.eclipse.ocl.pivot.@NonNull Package getPackage() {
		return OCLstdlibTables.PACKAGE;
	}

	public synchronized org.eclipse.ocl.pivot.@Nullable Package getPackage(@NonNull EPackage ePackage) {
		String nsURI = ePackage.getNsURI();
		return nsURI != null ? weakGet(ePackageMap, nsURI.intern()) : null;
	}

	public org.eclipse.ocl.pivot.@Nullable Class getPivotType(@NonNull String className) {
		Map<org.eclipse.ocl.pivot.@NonNull Package, List<org.eclipse.ocl.pivot.@NonNull Package>> extensions2 = extensions;
		if (extensions2 != null) {
			for (@SuppressWarnings("null")@NonNull List<org.eclipse.ocl.pivot.@NonNull Package> packages : extensions2.values()) {
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

	@Override
	public @NonNull PrimitiveType getRealType() {
		return (PrimitiveType)OCLstdlibTables.Types._Real;
	}

	@Override
	public org.eclipse.ocl.pivot.Package getRootPackage(@NonNull String name) {
		throw new UnsupportedOperationException();
	}

	@Override
	public @NonNull SequenceType getSequenceType() {
		return (SequenceType)OCLstdlibTables.Types._Sequence;
	}

	@Override
	public @NonNull SetType getSetType() {
		return (SetType)OCLstdlibTables.Types._Set;
	}

	public StatusCodes.@Nullable Severity getSeverity(@Nullable Object validationKey) {
		Map<@Nullable Object, StatusCodes.@Nullable Severity> validationKey2severity2 = validationKey2severity;
		if (validationKey2severity2 == null) {
			validationKey2severity = validationKey2severity2 = createValidationKey2severityMap();
		}
		return validationKey2severity2.get(validationKey);
	}

	@Override
	public @NonNull PrimitiveType getStringType() {
		return (PrimitiveType)OCLstdlibTables.Types._String;
	}

	@Override
	public @NonNull TupleType getTupleType(@NonNull String tupleName, @NonNull Map<@NonNull String, @NonNull ? extends Type> parts) {
		throw new UnsupportedOperationException();
	}

	@Override
	public @NonNull TupleType getTupleType(@NonNull TupleType type, @Nullable TemplateParameterSubstitutions usageBindings) {
		throw new UnsupportedOperationException();
	}

	@Override
	public @NonNull TupleType getTupleType(@NonNull String typeName, @NonNull Collection<@NonNull ? extends TypedElement> parts,
			@Nullable TemplateParameterSubstitutions bindings) {
		throw new UnsupportedOperationException();
	}

	@Override
	public @NonNull CollectionType getUniqueCollectionType() {
		return (CollectionType)OCLstdlibTables.Types._UniqueCollection;
	}

	@Override
	public @NonNull PrimitiveType getUnlimitedNaturalType() {
		return (PrimitiveType)OCLstdlibTables.Types._UnlimitedNatural;
	}

	private void initClass(@NonNull ClassImpl asClass, @NonNull EClassifier eClassifier, @Nullable TypeId typeId, int flags, @NonNull TemplateParameter @Nullable... typeParameters) {
		asClass.setESObject(eClassifier);
		asClass.setName(eClassifier.getName());
		if (typeId != null) {
			asClass.setTypeId(typeId);
			asClass.setNormalizedTypeId(typeId);
		}
		asClass.setIsAbstract((flags & FlatClass.ABSTRACT) != 0);
		initTemplateParameters(asClass, typeParameters);
		EcoreFlatModel flatModel = getFlatModel();
		FlatClass flatClass = flatModel.getEcoreFlatClass(asClass);
		asClass.setFlatClass(flatClass);
	}

	public void initLiterals(@NonNull Enumeration asEnumeration, @NonNull EnumerationLiteral @NonNull [] asEnumerationLiterals) {
		List<EnumerationLiteral> asLiterals = asEnumeration.getOwnedLiterals();
		for (@NonNull EnumerationLiteral asEnumerationLiteral : asEnumerationLiterals) {
			asLiterals.add(asEnumerationLiteral);
		}
	}

	public void initPackage(org.eclipse.ocl.pivot.@NonNull Package asPackage, org.eclipse.ocl.pivot.@NonNull Class @NonNull [] asClasses) {
		Object eContainer = asPackage.eContainer();
		assert eContainer == null;
		Model asModel = PivotFactory.eINSTANCE.createModel();
		asModel.setExternalURI(asPackage.getURI());
		asModel.getOwnedPackages().add(asPackage);
		orphanage.eResource().getContents().add(asModel);
		EcoreFlatModel flatModel = (EcoreFlatModel)asModel.initFlatModel(this);
		List<org.eclipse.ocl.pivot.Class> ownedClasses = asPackage.getOwnedClasses();
		for (org.eclipse.ocl.pivot.@NonNull Class asClass : asClasses) {
			ownedClasses.add(asClass);
		}
	}

	private <T extends CollectionType> void initTemplateParameters(@NonNull TemplateableElement pivotType, @NonNull TemplateParameter @Nullable... templateParameters) {
		if ((templateParameters != null) && (templateParameters.length > 0)) {
			TemplateSignature templateSignature = PivotFactory.eINSTANCE.createTemplateSignature();
			List<TemplateParameter> asTemplateParameters = templateSignature.getOwnedParameters();
			for (@NonNull TemplateParameter templateParameter : templateParameters) {
				TemplateParameterImpl asTemplateParameter = (TemplateParameterImpl)PivotFactory.eINSTANCE.createTemplateParameter();
				asTemplateParameter.setName(templateParameter.getName());
				asTemplateParameter.setTemplateParameterId(templateParameter.getTemplateParameterId());
				asTemplateParameters.add(asTemplateParameter);
			}
			pivotType.setOwnedSignature(templateSignature);
		}
	}

	@Override
	protected boolean isUnspecialized(@NonNull CollectionType genericType, @NonNull Type elementType,
			@Nullable Boolean isNullFree, @Nullable IntegerValue lower, @Nullable UnlimitedNaturalValue upper) {
		if (!PivotUtil.hasDefaultCollectionValueBindings(isNullFree, lower, upper)) {
			return false;
		}
		return elementType == genericType.getElementType();
	}

	@Override
	protected boolean isUnspecialized(@NonNull Type keyType, @Nullable Boolean keysAreNullFree, @NonNull Type valueType, @Nullable Boolean valuesAreNullFree) {
		if (!PivotUtil.hasDefaultMapValueBindings(keysAreNullFree, valuesAreNullFree)) {
			return false;
		}
		return (keyType == OCLstdlibTables.TypeParameters._0_K) && (valueType == OCLstdlibTables.TypeParameters._1_V);
	}

	public void resetSeverities() {
		validationKey2severity = null;
	}

	@Override
	public void resolveSuperClasses(org.eclipse.ocl.pivot.@NonNull Class specializedClass, org.eclipse.ocl.pivot.@NonNull Class unspecializedClass) {
		specializedClass.getSuperClasses().addAll(unspecializedClass.getSuperClasses());		// XXX
	}

	/**
	 * Return the map.get(key).get() entry if there is one or null if not, removing any stale
	 * entry that may be encountered.
	 */
	protected <K, V> @Nullable V weakGet(@NonNull Map<K, @NonNull WeakReference<V>> map, @NonNull K key) {
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
