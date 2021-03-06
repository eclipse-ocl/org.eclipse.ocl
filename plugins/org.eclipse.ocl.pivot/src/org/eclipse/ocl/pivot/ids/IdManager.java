/*******************************************************************************
 * Copyright (c) 2011, 2021 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.ids;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.ETypeParameter;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.Enumeration;
import org.eclipse.ocl.pivot.LambdaType;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.TemplateParameters;
import org.eclipse.ocl.pivot.TupleType;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.internal.ids.BindingsIdImpl;
import org.eclipse.ocl.pivot.internal.ids.GeneralizedCollectionTypeIdImpl;
import org.eclipse.ocl.pivot.internal.ids.GeneralizedLambdaTypeIdImpl;
import org.eclipse.ocl.pivot.internal.ids.GeneralizedMapTypeIdImpl;
import org.eclipse.ocl.pivot.internal.ids.GeneralizedTupleTypeIdImpl;
import org.eclipse.ocl.pivot.internal.ids.NsURIPackageIdImpl;
import org.eclipse.ocl.pivot.internal.ids.ParametersIdImpl;
import org.eclipse.ocl.pivot.internal.ids.PrimitiveTypeIdImpl;
import org.eclipse.ocl.pivot.internal.ids.RootPackageIdImpl;
import org.eclipse.ocl.pivot.internal.ids.TemplateParameterIdImpl;
import org.eclipse.ocl.pivot.internal.ids.TuplePartIdImpl;
import org.eclipse.ocl.pivot.internal.ids.UnspecifiedIdImpl;
import org.eclipse.ocl.pivot.internal.ids.WeakHashMapOfListOfWeakReference2;
import org.eclipse.ocl.pivot.internal.ids.WeakHashMapOfListOfWeakReference3;
import org.eclipse.ocl.pivot.internal.ids.WeakHashMapOfListOfWeakReference4;
import org.eclipse.ocl.pivot.internal.ids.WeakHashMapOfWeakReference;
import org.eclipse.ocl.pivot.util.DerivedConstants;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.PivotConstants;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.utilities.TypeUtil;

/**
 * IdManager supervises the thread-safe allocation of unique hierarchical identifier to each metamodel element.
 *
 * @see ElementId
 */
public final class IdManager
{
	/*
	 * IdManager is final and the sole instance of IdManager is private and ElementId implementations need an IdManager
	 * for construction so ElementId uniqueness is guaranteed.
	 */
	private static @NonNull IdManager PRIVATE_INSTANCE = new IdManager();

	public static final @NonNull RootPackageId METAMODEL = new RootPackageIdImpl(PRIVATE_INSTANCE, PivotConstants.METAMODEL_NAME);

	/**
	 * Map from the BindingsId hashCode to the elements with the same hash.
	 */
	private static @Nullable WeakHashMapOfListOfWeakReference2<Integer, @NonNull ElementId @NonNull [], BindingsIdImpl> bindingsIds;

	/**
	 * Map from a Collection type name to the corresponding CollectionTypeId.
	 */
	private static @NonNull WeakHashMapOfWeakReference<String, CollectionTypeId> collectionNames =
			new WeakHashMapOfWeakReference<String, CollectionTypeId>()
	{
		@Override
		protected @NonNull CollectionTypeId newId(@NonNull String name) {
			return new GeneralizedCollectionTypeIdImpl(PRIVATE_INSTANCE, name);
		}
	};

	/**
	 * Map from a Map type name to the corresponding MapTypeId.
	 */
	private static @NonNull WeakHashMapOfWeakReference<String, MapTypeId> mapNames =
			new WeakHashMapOfWeakReference<String, MapTypeId>()
	{
		@Override
		protected @NonNull MapTypeId newId(@NonNull String name) {
			return new GeneralizedMapTypeIdImpl(PRIVATE_INSTANCE, name);
		}
	};

	/**
	 * Map from an nsURI to the corresponding NsURITypeId.
	 */
	private static @NonNull WeakHashMapOfWeakReference<String, NsURIPackageId> nsURIs =
			new WeakHashMapOfWeakReference<String, NsURIPackageId>()
	{
		@Override
		protected @NonNull NsURIPackageId newId(@NonNull String nsURI) {
			return new NsURIPackageIdImpl(PRIVATE_INSTANCE, nsURI, null, null);
		}
	};

	/**
	 * Map from the Lambda hashCode to the lambda typeIds with the same hash.
	 */
	private static @Nullable WeakHashMapOfListOfWeakReference3<Integer, String, ParametersId, GeneralizedLambdaTypeIdImpl> lambdaTypes = null;

	/**
	 * Map from the TuplePart hashCode to the tuplePartIds with the same hash.
	 */
	private static @Nullable WeakHashMapOfListOfWeakReference4<Integer, Integer, String, TypeId, TuplePartIdImpl> tupleParts = null;

	/**
	 * Map from a name to the corresponding URI-less unnested RootPackageTypeId.
	 */
	private static @NonNull WeakHashMapOfWeakReference<String, RootPackageId> roots =
			new WeakHashMapOfWeakReference<String, RootPackageId>()
	{
		@Override
		protected @NonNull RootPackageId newId(@NonNull String name) {
			return new RootPackageIdImpl(PRIVATE_INSTANCE, name);
		}
	};

	/**
	 * List of template parameters; 0 index at least index ... up to most nested
	 */
	private static @NonNull List<TemplateParameterId> templateParameters = new ArrayList<TemplateParameterId>(10);

	/**
	 * Map from the Tuple hashCode to the tuple typeIds with the same hash.
	 */
	private static @Nullable WeakHashMapOfListOfWeakReference3<@NonNull Integer, @NonNull String, @NonNull TuplePartId @NonNull [], GeneralizedTupleTypeIdImpl> tupleTypes = null;

	/**
	 * Map from the ParametersId hashCode to the parametersId with the same hash.
	 */
	private static @Nullable WeakHashMapOfListOfWeakReference2<@NonNull Integer, @NonNull TypeId @NonNull [], ParametersIdImpl> parametersIds;

	/**
	 * Map from a Primitive type name to the corresponding PrimitiveTypeId.
	 */
	private static @NonNull WeakHashMapOfWeakReference<String, PrimitiveTypeId> primitiveTypes =
			new WeakHashMapOfWeakReference<String, PrimitiveTypeId>()
	{
		@Override
		protected @NonNull PrimitiveTypeId newId(@NonNull String name) {
			return new PrimitiveTypeIdImpl(PRIVATE_INSTANCE, name);
		}
	};

	private static @Nullable Map<String, String> metamodelURI2name = null;

	/**
	 * Define a metamodelNsURI as a contributor to the metamodelName. THis facility is used to enable
	 * UML2's duplicate Eclipse/OMG models to be treated as merged rather than conflicting.
	 */
	public static void addMetamodelEPackage(@NonNull String metamodelNsURI, @NonNull String metamodelName) {
		Map<String, String> metamodelURI2name2 = metamodelURI2name;
		if (metamodelURI2name2 == null) {
			metamodelURI2name = metamodelURI2name2 = new HashMap<String, String>();
		}
		metamodelURI2name2.put(metamodelNsURI, metamodelName);
	}

	public static @NonNull BindingsId getBindingsId(@NonNull Type... types) {
		@NonNull ElementId @NonNull [] elementIds = new @NonNull ElementId @NonNull [types.length];
		for (int i = 0; i < types.length; i++) {
			elementIds[i] = types[i].getTypeId();
		}
		return getBindingsId(elementIds);
	}

	/**
	 * Return the bindingsId for a given type list.
	 */
	public static @NonNull BindingsId getBindingsId(@NonNull ElementId @NonNull ... elementIds) {
		WeakHashMapOfListOfWeakReference2<Integer, @NonNull ElementId @NonNull [], BindingsIdImpl> bindingsIds2 = bindingsIds;
		if (bindingsIds2 == null) {
			synchronized (IdManager.class) {
				bindingsIds2 = bindingsIds;
				if (bindingsIds2 == null) {
					bindingsIds = bindingsIds2 = new WeakHashMapOfListOfWeakReference2<Integer, @NonNull ElementId @NonNull [], BindingsIdImpl>()
					{
						@Override
						protected @NonNull BindingsIdImpl newId(@NonNull Integer hashCode, @NonNull ElementId @NonNull [] elementIds) {
							return new BindingsIdImpl(PRIVATE_INSTANCE, hashCode, elementIds);
						}
					};
				}
			}
		}
		@NonNull Integer hashCode = IdHash.createParametersHash(BindingsId.class, elementIds);
		return bindingsIds2.getId(hashCode, elementIds);
	}

	/**
	 * Return the classId for aType.
	 */
	public static @NonNull ClassId getClassId(org.eclipse.ocl.pivot.@NonNull Class aType) {
		if (aType.eIsProxy()) {
			return getUnspecifiedTypeId(aType);		// FIXME This occurs for underspecified/wildcard types
		}
		org.eclipse.ocl.pivot.Class unspecializedType = PivotUtil.getUnspecializedTemplateableElement(aType);
		String name = aType.getName();
		assert name != null;
		org.eclipse.ocl.pivot.Package parentPackage = unspecializedType.getOwningPackage();
		if (parentPackage != null) {
			TemplateParameters typeParameters = aType.getTypeParameters();
			PackageId packageId = parentPackage.getPackageId();
			return packageId.getClassId(name, typeParameters.parametersSize());
		}
		else {
			return getUnspecifiedTypeId(aType);		// FIXME This occurs for underspecified/wildcard types
		}
	}

	/**
	 * Return the classId for eClass.
	 */
	public static @NonNull ClassId getClassId(@NonNull EClass eClass) {
		EPackage ePackage = ClassUtil.nonNullEMF(eClass.getEPackage());
		PackageId packageId = IdManager.getPackageId(ePackage);
		String className = ClassUtil.nonNullEMF(NameUtil.getOriginalName(eClass));
		ClassId classId = packageId.getClassId(className, eClass.getETypeParameters().size());
		return classId;
	}

	/**
	 * Return the named collection typeId.
	 */
	public static @NonNull CollectionTypeId getCollectionTypeId(@NonNull String collectionTypeName) {
		return collectionNames.getId(collectionTypeName);
	}

	/**
	 * Return the dataTypeId for aType.
	 */
	public static @NonNull DataTypeId getDataTypeId(org.eclipse.ocl.pivot.@NonNull Class aType) {
		String name = aType.getName();
		assert name != null;
		org.eclipse.ocl.pivot.Package parentPackage = aType.getOwningPackage();
		if (parentPackage != null) {
			TemplateParameters typeParameters = aType.getTypeParameters();
			PackageId packageId = parentPackage.getPackageId();
			return packageId.getDataTypeId(name, typeParameters.parametersSize());
		}
		else {
			return new UnspecifiedIdImpl(PRIVATE_INSTANCE, aType);		// FIXME This occurs for underspecified/wildcard types
		}
	}

	/**
	 * Return the typeId for aType.
	 */
	public static @NonNull EnumerationId getEnumerationId(@NonNull Enumeration anEnumeration) {
		String name = anEnumeration.getName();
		assert name != null;
		org.eclipse.ocl.pivot.Package parentPackage = anEnumeration.getOwningPackage();
		assert parentPackage != null;
		return parentPackage.getPackageId().getEnumerationId(name);
	}

	/**
	 * Return the typeId for an EEnum.
	 *
	 * @Deprecated (UML-aware) caller should resolve the PackageId and then tunnel down.
	 * The UML-blind implementation here fails to resolve Ecore profiles.
	 */
	@Deprecated
	public static @NonNull EnumerationId getEnumerationId(@NonNull EEnum eEnum) {
		String name = eEnum.getName();
		assert name != null;
		EPackage parentPackage = eEnum.getEPackage();
		assert parentPackage != null;
		return getPackageId(parentPackage).getEnumerationId(name);
	}

	/**
	 * Return the typeId for an EEnumLiteral.
	 *
	 * @Deprecated (UML-aware) caller should resolve the PackageId and then tunnel down.
	 * The UML-blind implementation here fails to resolve Ecore profiles.
	 */
	@Deprecated
	public static @NonNull EnumerationLiteralId getEnumerationLiteralId(@NonNull EEnumLiteral eEnumLiteral) {
		EEnum eEnum = ClassUtil.nonNullModel(eEnumLiteral.getEEnum());
		String name = ClassUtil.nonNullModel(eEnumLiteral.getName());
		EnumerationId enumerationId = getEnumerationId(eEnum);
		EnumerationLiteralId enumerationLiteralId = enumerationId.getEnumerationLiteralId(name);
		return enumerationLiteralId;
	}

	/**
	 * Return the typeId for aLambdaType.
	 */
	public static @NonNull LambdaTypeId getLambdaTypeId(@NonNull LambdaType lambdaType) {
		String name = NameUtil.getSafeName(lambdaType);
		return getLambdaTypeId(name, lambdaType.getParametersId());
	}

	/**
	 * Return the named lambda typeId with the defined type parameters.
	 */
	public static @NonNull LambdaTypeId getLambdaTypeId(@NonNull String name, @NonNull TypeId @NonNull ... typeIds) {
		return getLambdaTypeId(name, getParametersId(typeIds));
	}

	/**
	 * Return the named lambda typeId with the defined type parameters.
	 */
	public static @NonNull LambdaTypeId getLambdaTypeId(@NonNull String name, @NonNull ParametersId parametersId) {
		WeakHashMapOfListOfWeakReference3<Integer, String, ParametersId, GeneralizedLambdaTypeIdImpl> lambdaTypes2 = lambdaTypes;
		if (lambdaTypes2 == null) {
			synchronized (IdManager.class) {
				lambdaTypes2 = lambdaTypes;
				if (lambdaTypes2 == null) {
					lambdaTypes = lambdaTypes2 = new WeakHashMapOfListOfWeakReference3<Integer, String, ParametersId, GeneralizedLambdaTypeIdImpl>()
					{
						@Override
						protected @NonNull GeneralizedLambdaTypeIdImpl newId(@NonNull Integer hashCode, @NonNull String name, @NonNull ParametersId parametersId) {
							return new GeneralizedLambdaTypeIdImpl(hashCode, name, parametersId);
						}
					};
				}
			}
		}
		int childHash = IdHash.createGlobalHash(LambdaTypeId.class, name);
		Integer hashCode = childHash + parametersId.hashCode();
		return lambdaTypes2.getId(hashCode, name, parametersId);
	}

	/**
	 * Return the named collection typeId.
	 */
	public static @NonNull MapTypeId getMapTypeId(@NonNull String mapTypeName) {
		return mapNames.getId(mapTypeName);
	}

	/**
	 * Return the URIed package typeId.
	 */
	public static @NonNull NsURIPackageId getNsURIPackageId(@NonNull String nsURI, @Nullable String nsPrefix, @Nullable EPackage ePackage) {
		WeakReference<NsURIPackageId> ref = nsURIs.get(nsURI);
		if (ref != null) {
			NsURIPackageId oldTypeId = ref.get();
			if (oldTypeId != null) {
				if ((ePackage != null) && (oldTypeId.getEPackage() == null)) {
					oldTypeId.setEPackage(ePackage);
				}
				return oldTypeId;
			}
		}
		synchronized (nsURIs) {
			ref = nsURIs.get(nsURI);
			if (ref != null) {
				NsURIPackageId oldTypeId = ref.get();
				if (oldTypeId != null) {
					return oldTypeId;
				}
			}
			NsURIPackageId newTypeId = new NsURIPackageIdImpl(PRIVATE_INSTANCE, nsURI, nsPrefix, ePackage);
			nsURIs.put(nsURI, new WeakReference<NsURIPackageId>(newTypeId));
			return newTypeId;
		}
	}

	/**
	 * Return the OperationId for anOperation.
	 */
	public static @NonNull OperationId getOperationId(@NonNull Operation anOperation) {
		String name = NameUtil.getSafeName(anOperation);
		org.eclipse.ocl.pivot.Class parentType = anOperation.getOwningClass();
		TypeId parentTypeId = parentType.getTypeId();
		@NonNull Type @NonNull [] parameterTypes = TypeUtil.getOperationParameterTypes(anOperation);
		TemplateParameters typeParameters = anOperation.getTypeParameters();
		ParametersId parametersId = getParametersId(parameterTypes);
		return parentTypeId.getOperationId(typeParameters.parametersSize(), name, parametersId);
	}

	/**
	 * Return the named tuple typeId with the defined parts (which are alphabetically ordered by part name).
	 */
	public static @NonNull TupleTypeId getOrderedTupleTypeId(@NonNull String name, @NonNull TuplePartId @NonNull [] parts) {
		WeakHashMapOfListOfWeakReference3<@NonNull Integer, @NonNull String, @NonNull TuplePartId @NonNull [], GeneralizedTupleTypeIdImpl> tupleTypes2 = tupleTypes;
		if (tupleTypes2 == null) {
			synchronized (IdManager.class) {
				tupleTypes2 = tupleTypes;
				if (tupleTypes2 == null) {
					tupleTypes = tupleTypes2 = new WeakHashMapOfListOfWeakReference3<@NonNull Integer, @NonNull String, @NonNull TuplePartId @NonNull [], GeneralizedTupleTypeIdImpl>()
					{
						@Override
						protected @NonNull GeneralizedTupleTypeIdImpl newId(@NonNull Integer hashCode, @NonNull String name, @NonNull TuplePartId @NonNull [] parts) {
							return new GeneralizedTupleTypeIdImpl(PRIVATE_INSTANCE, hashCode, name, parts);
						}
					};
				}
			}
		}
		int hash = IdHash.createTupleHash(name, parts);
		return tupleTypes2.getId(hash, name, parts);
	}

	/**
	 * Return the typeId for aPackage.
	 */
	public static @NonNull PackageId getPackageId(org.eclipse.ocl.pivot.@NonNull Package aPackage) {
		String nsURI = aPackage.getURI();
		if (nsURI != null) {
			return getNsURIPackageId(nsURI, aPackage.getNsPrefix(), aPackage.getEPackage());
		}
		String name = aPackage.getName();
		//		assert name != null;
		if (name == null) name = "";
		org.eclipse.ocl.pivot.Package parentPackage = aPackage.getOwningPackage();
		if (parentPackage != null) {
			return parentPackage.getPackageId().getNestedPackageId(name);
		}
		else {
			return getRootPackageId(name);
		}
	}

	/**
	 * Return the typeId for ePackage.
	 */
	public static @NonNull PackageId getPackageId(@NonNull EPackage aPackage) {
		if (ClassUtil.basicGetMetamodelAnnotation(aPackage) != null) {
			return METAMODEL;
		}
		String nsURI = aPackage.getNsURI();
		if (nsURI != null) {
			if (metamodelURI2name != null) {
				String metamodelName = metamodelURI2name.get(nsURI);
				if (metamodelName != null) {
					return getRootPackageId(metamodelName);
				}
			}
			//			if (nsURI.equals(UMLPackage.eNS_URI)) {		// FIXME use extension point
			//				return getRootPackageId(PivotConstants.UML_METAMODEL_NAME);
			//			}
			//			else if (nsURI.equals(TypesPackage.eNS_URI)) {		// FIXME use extension point
			//				return getRootPackageId(PivotConstants.TYPES_METAMODEL_NAME);
			//			}
			EObject eContainer1 = aPackage.eContainer();
			if (eContainer1 instanceof EAnnotation) {
				EAnnotation eAnnotation = (EAnnotation)eContainer1;
				if (DerivedConstants.UML2_UML_PACKAGE_2_0_NS_URI.equals(eAnnotation.getSource())) {
					EObject eContainer2 = eAnnotation.eContainer();
					if (eContainer2 != null) {
						EClass eClass2 = eContainer2.eClass();
						if ("Profile".equals(eClass2.getName())) {
							EStructuralFeature eStructuralFeature = eClass2.getEStructuralFeature("URI");
							if (eStructuralFeature != null) {
								Object uri = eContainer2.eGet(eStructuralFeature);
								if (uri != null) {
									return getNsURIPackageId(String.valueOf(uri), aPackage.getNsPrefix(), aPackage);
								}
								eStructuralFeature = eClass2.getEStructuralFeature("name");
								if (eStructuralFeature != null) {
									Object name = eContainer2.eGet(eStructuralFeature);
									if (name != null) {
										return getRootPackageId(String.valueOf(name));
									}
								}
							}
						}
					}
				}
			//	System.out.println("Looks like a UML Profile has not been used in place of its EPackage for " + nsURI);
			}
			return getNsURIPackageId(nsURI, aPackage.getNsPrefix(), aPackage);
		}
		String name = aPackage.getName();
		assert name != null;
		EPackage parentPackage = aPackage.getESuperPackage();
		if (parentPackage != null) {
			return getPackageId(parentPackage).getNestedPackageId(name);
		}
		return getNsURIPackageId(name, aPackage.getNsPrefix(), null);
	}

	public static @NonNull ParametersId getParametersId(@NonNull Type @NonNull [] parameterTypes) {
		int iSize = parameterTypes.length;
		@NonNull TypeId @NonNull [] typeIds = new @NonNull TypeId[iSize];
		for (int i = 0; i < iSize; i++) {
			Type parameterType = parameterTypes[i];
			@SuppressWarnings("null")boolean isNonNull = parameterType != null;
			typeIds[i] = isNonNull ? parameterType.getTypeId() : TypeId.OCL_INVALID;		// Never happens NPE guard
		}
		return getParametersId(typeIds);
	}

	/**
	 * Return the parametersId for a given type list.
	 */
	public static @NonNull ParametersId getParametersId(@NonNull TypeId @NonNull ... typeIds) {
		WeakHashMapOfListOfWeakReference2<@NonNull Integer, @NonNull TypeId @NonNull [], ParametersIdImpl> parametersIds2 = parametersIds;
		if (parametersIds2 == null) {
			synchronized (IdManager.class) {
				parametersIds2 = parametersIds;
				if (parametersIds2 == null) {
					parametersIds = parametersIds2 = new WeakHashMapOfListOfWeakReference2<@NonNull Integer, @NonNull TypeId @NonNull [], ParametersIdImpl>()
					{
						@Override
						protected @NonNull ParametersIdImpl newId(@NonNull Integer hashCode, @NonNull TypeId @NonNull [] typeIds) {
							return new ParametersIdImpl(PRIVATE_INSTANCE, hashCode, typeIds);
						}
					};
				}
			}
		}
		@NonNull Integer hashCode = IdHash.createParametersHash(ParametersId.class, typeIds);
		return parametersIds2.getId(hashCode, typeIds);
	}

	/**
	 * Return the named primitive typeId.
	 */
	public static @NonNull PrimitiveTypeId getPrimitiveTypeId(@NonNull String name) {
		return primitiveTypes.getId(name);
	}

	/**
	 * Return the propertyId for an EStructuralFeature.
	 */
	public static @NonNull PropertyId getPropertyId(@NonNull EStructuralFeature eFeature) {
		String name = NameUtil.getOriginalName(eFeature);
		assert name != null;
		EClass parentClass = eFeature.getEContainingClass();
		assert parentClass != null;
		ClassId classId = getClassId(parentClass);
		return classId.getPropertyId(name);
	}

	/**
	 * Return the URI-less unnested package typeId.
	 */
	public static @NonNull RootPackageId getRootPackageId(@NonNull String name) {
		if (PivotConstants.METAMODEL_NAME.equals(name)) {
			return METAMODEL;
		}
		WeakReference<RootPackageId> ref = roots.get(name);
		if (ref != null) {
			RootPackageId oldTypeId = ref.get();
			if (oldTypeId != null) {
				return oldTypeId;
			}
		}
		synchronized (roots) {
			ref = roots.get(name);
			if (ref != null) {
				RootPackageId oldTypeId = ref.get();
				if (oldTypeId != null) {
					return oldTypeId;
				}
			}
			RootPackageId newTypeId = new RootPackageIdImpl(PRIVATE_INSTANCE, name);
			roots.put(name, new WeakReference<RootPackageId>(newTypeId));
			return newTypeId;
		}
	}

	public static @NonNull TemplateParameterId getTemplateParameterId(int index) {
		if (index >= templateParameters.size()) {
			synchronized (templateParameters) {
				while (index >= templateParameters.size()) {
					templateParameters.add(new TemplateParameterIdImpl(PRIVATE_INSTANCE, templateParameters.size()));
				}
			}
		}
		TemplateParameterId templateParameterId = templateParameters.get(index);
		assert templateParameterId != null;
		return templateParameterId;
	}

	/**
	 * Return the named tuplePartId for the givem property of a TupleType.
	 * @since 1.3
	 */
	public static @NonNull TuplePartId getTuplePartId(@NonNull Property asProperty) {
		TupleType tupleType = (TupleType) PivotUtil.getOwningClass(asProperty);
		String name = NameUtil.getSafeName(asProperty);
		int index = tupleType.getOwnedProperties().indexOf(asProperty);
		return IdManager.getTuplePartId(index, name, asProperty.getTypeId());
	}

	/**
	 * Return the named tuplePartId with the defined name and type.
	 */
	public static @NonNull TuplePartId getTuplePartId(int index, @NonNull String name, @NonNull TypeId typeId) {
		WeakHashMapOfListOfWeakReference4<Integer, Integer, String, TypeId, TuplePartIdImpl> tupleParts2 = tupleParts;
		if (tupleParts2 == null) {
			synchronized (IdManager.class) {
				tupleParts2 = tupleParts;
				if (tupleParts2 == null) {
					tupleParts = tupleParts2 = new WeakHashMapOfListOfWeakReference4<Integer, Integer, String, TypeId, TuplePartIdImpl>()
					{
						@Override
						protected @NonNull TuplePartIdImpl newId(@NonNull Integer hashCode, @NonNull Integer index, @NonNull String name, @NonNull TypeId typeId) {
							return new TuplePartIdImpl(PRIVATE_INSTANCE, hashCode, index, name, typeId);
						}
					};
				}
			}
		}
		Integer hashCode = name.hashCode() + 7 * typeId.hashCode() + 989 * index;
		return tupleParts2.getId(hashCode, index, name, typeId);
	}

	/**
	 * Return the named tuple typeId with the defined parts (which need not be alphabetically ordered).
	 */
	public static @NonNull TupleTypeId getTupleTypeId(@NonNull String name, @NonNull Collection<@NonNull ? extends TuplePartId> parts) {
		@NonNull TuplePartId @NonNull [] orderedParts = new @NonNull TuplePartId[parts.size()];
		int i = 0;
		for (TuplePartId part : parts) {
			orderedParts[i++] = part;
		}
		Arrays.sort(orderedParts);
		return getOrderedTupleTypeId(name, orderedParts);
	}

	public static @NonNull TupleTypeId getTupleTypeId(@NonNull String name, @NonNull TuplePartId @NonNull ... parts) {
		@NonNull TuplePartId @NonNull [] orderedParts = new @NonNull TuplePartId[parts.length];
		int i = 0;
		for (TuplePartId part : parts) {
			orderedParts[i++] = part;
		}
		Arrays.sort(orderedParts);
		return getOrderedTupleTypeId(name, orderedParts);
	}

	/**
	 * Return the typeId for an EClassifier.
	 */
	public static @NonNull TypeId getTypeId(@NonNull EClassifier eClassifier) {
		String name = NameUtil.getOriginalName(eClassifier);
		assert name != null;
		EPackage parentPackage = eClassifier.getEPackage();
		assert parentPackage != null;
		List<ETypeParameter> eTypeParameters = eClassifier.getETypeParameters();
		assert eTypeParameters != null;
		PackageId packageId = getPackageId(parentPackage);
		int eTypeParameterCount = eTypeParameters.size();
		if (eClassifier instanceof EEnum) {
			return packageId.getEnumerationId(name);
		}
		else if (eClassifier instanceof EDataType) {
			return packageId.getDataTypeId(name, eTypeParameterCount);
		}
		else {
			return packageId.getClassId(name, eTypeParameterCount);
		}
	}

	/**
	 * Return the typeId for aType.
	 */
	public static @NonNull UnspecifiedIdImpl getUnspecifiedTypeId(@NonNull Type aType) {
		UnspecifiedIdImpl newId = new UnspecifiedIdImpl(PRIVATE_INSTANCE, aType);
		//		System.out.println("Create " + newId.getClass().getSimpleName() + " " + newId + " => @" + Integer.toHexString(newId.hashCode()));
		return newId;
	}

	private IdManager() {}
}