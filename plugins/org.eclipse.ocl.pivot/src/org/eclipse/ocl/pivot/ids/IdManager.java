/*******************************************************************************
 * Copyright (c) 2011, 2022 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *	 E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.ids;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.ETypeParameter;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.Enumeration;
import org.eclipse.ocl.pivot.LambdaParameter;
import org.eclipse.ocl.pivot.LambdaType;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.TemplateBinding;
import org.eclipse.ocl.pivot.TemplateParameter;
import org.eclipse.ocl.pivot.TemplateParameterSubstitution;
import org.eclipse.ocl.pivot.TemplateSignature;
import org.eclipse.ocl.pivot.TupleType;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.internal.ids.BindingsIdImpl.BindingsIdSingletonScope;
import org.eclipse.ocl.pivot.internal.ids.GeneralizedCollectionTypeIdImpl.CollectionTypeIdSingletonScope;
import org.eclipse.ocl.pivot.internal.ids.GeneralizedLambdaTypeIdImpl.LambdaTypeIdSingletonScope;
import org.eclipse.ocl.pivot.internal.ids.GeneralizedMapTypeIdImpl.MapTypeIdSingletonScope;
import org.eclipse.ocl.pivot.internal.ids.GeneralizedTupleTypeIdImpl.TupleTypeIdSingletonScope;
import org.eclipse.ocl.pivot.internal.ids.NsURIPackageIdImpl.NsURIPackageIdSingletonScope;
import org.eclipse.ocl.pivot.internal.ids.ParametersIdImpl.ParametersIdSingletonScope;
import org.eclipse.ocl.pivot.internal.ids.PartIdImpl.PartIdSingletonScope;
import org.eclipse.ocl.pivot.internal.ids.PrimitiveTypeIdImpl.PrimitiveTypeIdSingletonScope;
import org.eclipse.ocl.pivot.internal.ids.RootPackageIdImpl.RootPackageIdSingletonScope;
import org.eclipse.ocl.pivot.internal.ids.TemplateParameterIdImpl;
import org.eclipse.ocl.pivot.internal.ids.UnspecifiedIdImpl;
import org.eclipse.ocl.pivot.internal.ids.WildcardIdImpl;
import org.eclipse.ocl.pivot.internal.manager.TemplateParameterization;
import org.eclipse.ocl.pivot.types.TemplateParameters;
import org.eclipse.ocl.pivot.util.DerivedConstants;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.PivotConstants;
import org.eclipse.ocl.pivot.utilities.PivotUtil;

import com.google.common.collect.Iterables;

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
	private static final @NonNull IdManager PRIVATE_INSTANCE = new IdManager();

	/**
	 * Map from the BindingsId hashCode to the elements with the same hash.
	 */
	private static final @NonNull BindingsIdSingletonScope bindingsIds = new BindingsIdSingletonScope();

	/**
	 * Map from a Collection type name to the corresponding CollectionTypeId.
	 */
	private static final @NonNull CollectionTypeIdSingletonScope collectionNames = new CollectionTypeIdSingletonScope();

	/**
	 * Map from a Map type name to the corresponding MapTypeId.
	 */
	private static final @NonNull MapTypeIdSingletonScope mapNames = new MapTypeIdSingletonScope();

	/**
	 * Map from an nsURI to the corresponding NsURITypeId.
	 */
	private static final @NonNull NsURIPackageIdSingletonScope nsURIs = new NsURIPackageIdSingletonScope();

	/**
	 * Map from the Lambda hashCode to the lambda typeIds with the same hash.
	 */
	private static final @NonNull LambdaTypeIdSingletonScope lambdaTypes = new LambdaTypeIdSingletonScope();

	/**
	 * Map from the Part hashCode to the PartIds with the same hash.
	 */
	private static final @NonNull PartIdSingletonScope tupleParts = new PartIdSingletonScope();

	/**
	 * Map from a name to the corresponding URI-less unnested RootPackageTypeId.
	 */
	private static final @NonNull RootPackageIdSingletonScope roots = new RootPackageIdSingletonScope();

	/**
	 * Map from the Tuple hashCode to the tuple typeIds with the same hash.
	 */
	private static final @NonNull TupleTypeIdSingletonScope tupleTypes = new TupleTypeIdSingletonScope();

	/**
	 * Map from the ParametersId hashCode to the parametersId with the same hash.
	 */
	private static final @NonNull ParametersIdSingletonScope parametersIds = new ParametersIdSingletonScope();

	/**
	 * Map from a Primitive type name to the corresponding PrimitiveTypeId.
	 */
	private static final @NonNull PrimitiveTypeIdSingletonScope primitiveTypes = new PrimitiveTypeIdSingletonScope();

	private static @Nullable Map<@NonNull String, @NonNull String> ePackageURI2completeName = null;

	private static @Nullable WildcardId wildcardId = null;

	/**
	 * @since 7.0
	 */
	public static final @NonNull RootPackageId METAMODEL_ID = getRootPackageId(PivotConstants.METAMODEL_NAME);

	/**
	 * Define a metamodelNsURI as a contributor to the metamodelName. This facility is used to enable
	 * UML2's duplicate Eclipse/OMG models to be treated as merged rather than conflicting.
	 * @since 7.0
	 */
	public static void addMetamodelEPackage(@NonNull String metamodelName, /*@NonNull*/ EPackage ePackage) {
		assert ePackage != null;
		Map<@NonNull String, @NonNull String> ePackageURI2completeName2 = ePackageURI2completeName;
		if (ePackageURI2completeName2 == null) {
			ePackageURI2completeName = ePackageURI2completeName2 = new HashMap<>();
		}
		String ePackageURI = ePackage.getNsURI();
		assert ePackageURI != null;
		ePackageURI2completeName2.put(ePackageURI, metamodelName);
	}

	/**
	 * Return the bindingsId for a given list of bindings.
	 *
	 * @since 1.18
	 */
	public static @Nullable BindingsId basicGetBindingsId(@Nullable Iterable<@NonNull TemplateBinding> templateBindings) {
		if (templateBindings == null) {
			return null;
		}
		List<@NonNull ElementId> elementIdList = new ArrayList<>();
		for (@NonNull TemplateBinding templateBinding : templateBindings) {
			for (@NonNull TemplateParameterSubstitution templateParameterSubstitution : PivotUtil.getOwnedSubstitutions(templateBinding)) {
				Type actual = templateParameterSubstitution.getActual();
				elementIdList.add(actual.getTypeId());
			}
		}
		int size = elementIdList.size();
		if (size <= 0) {
			return null;
		}
		@NonNull ElementId[] elementIds = elementIdList.toArray(new @NonNull ElementId[size]);
		return bindingsIds.getSingleton(PRIVATE_INSTANCE, elementIds, null);
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
		return bindingsIds.getSingleton(PRIVATE_INSTANCE, elementIds, null);
	}

	/**
	 * Return the bindingsId for a given types followed by values list.
	 *
	 * @since 1.18
	 */
	public static @NonNull BindingsId getBindingsId(@NonNull Object @NonNull ... elementIdAndValues) {
		int elementIdCount = 0;
		for (@NonNull Object elementId : elementIdAndValues) {
			if (elementId instanceof ElementId) {
				elementIdCount++;
			}
		}
		int valuesCount = elementIdAndValues.length - elementIdCount;
		@NonNull ElementId[] elementIds = new @NonNull ElementId[elementIdCount];
		@NonNull Object[] values = new @NonNull Object[valuesCount];
		int index = 0;
		for (int i = 0; i < elementIdCount; i++) {
			elementIds[i] = (ElementId)elementIdAndValues[index++];
		}
		for (int i = 0; i < valuesCount; i++) {
			values[i] = elementIdAndValues[index++];
		}
		return bindingsIds.getSingleton(PRIVATE_INSTANCE, elementIds, values);
	}

	/**
	 * Return the bindingsId for a given type list of element and value parameters.
	 *
	 * @since 1.18
	 */
	public static @NonNull BindingsId getBindingsId(@NonNull ElementId @NonNull [] elementIds, @NonNull Object @Nullable [] values) {
		return bindingsIds.getSingleton(PRIVATE_INSTANCE, elementIds, values);
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
			TemplateParameters templateParameters = unspecializedType.getTemplateParameters();
			PackageId packageId = parentPackage.getPackageId();
			ClassId unspecializedClassId = packageId.getClassId(name, templateParameters.parametersSize());
			BindingsId bindingsId = basicGetBindingsId(PivotUtil.getOwnedBindings(aType));
			return bindingsId != null ? (ClassId)unspecializedClassId.getSpecializedId(bindingsId) : unspecializedClassId;
		}
		else {
			return getUnspecifiedTypeId(aType);		// FIXME This occurs for underspecified/wildcard types
		}
	}

	/**
	 * Return the classId for eClass.
	 */
	public static @NonNull ClassId getClassId(@NonNull EClass eClass) {
		EPackage ePackage = ClassUtil.requireNonNull(eClass.getEPackage());
		PackageId packageId = IdManager.getPackageId(ePackage);
		String className = ClassUtil.requireNonNull(NameUtil.getOriginalName(eClass));
		ClassId classId = packageId.getClassId(className, eClass.getETypeParameters().size());
		return classId;
	}

	/**
	 * Return the named collection typeId.
	 */
	public static @NonNull CollectionTypeId getCollectionTypeId(@NonNull String collectionTypeName) {
		return collectionNames.getSingleton(PRIVATE_INSTANCE, collectionTypeName);
	}

	/**
	 * @since 7.0
	 */
	public static @NonNull CollectionTypeId getCollectionTypeId(boolean isOrdered, boolean isUnique) {
		if (isOrdered) {
			if (isUnique) {
				return TypeId.ORDERED_SET;
			}
			else {
				return TypeId.SEQUENCE;
			}
		}
		else {
			if (isUnique) {
				return TypeId.SET;
			}
			else {
				return TypeId.BAG;
			}
		}
	}

	/**
	 * Return the dataTypeId for aType.
	 */
	public static @NonNull DataTypeId getDataTypeId(org.eclipse.ocl.pivot.@NonNull Class aType) {
		if (aType.eIsProxy()) {
			return getUnspecifiedTypeId(aType);		// FIXME This occurs for underspecified/wildcard types
		}
		org.eclipse.ocl.pivot.Class unspecializedType = PivotUtil.getUnspecializedTemplateableElement(aType);
		String name = aType.getName();
		assert name != null;
		org.eclipse.ocl.pivot.Package parentPackage = unspecializedType.getOwningPackage();
		if (parentPackage != null) {
			TemplateParameters templateParameters = unspecializedType.getTemplateParameters();
			PackageId packageId = parentPackage.getPackageId();
			DataTypeId unspecializedDataTypeId = packageId.getDataTypeId(name, templateParameters.parametersSize());
			BindingsId bindingsId = basicGetBindingsId(PivotUtil.getOwnedBindings(aType));
			return bindingsId != null ? (DataTypeId)unspecializedDataTypeId.getSpecializedId(bindingsId) : unspecializedDataTypeId;
		}
		else {
			return getUnspecifiedTypeId(aType);		// FIXME This occurs for underspecified/wildcard types
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
	 * Return the typeId for aLambdaType.
	 */
	public static @NonNull LambdaTypeId getLambdaTypeId(@NonNull LambdaType lambdaType) {
		String name = NameUtil.getSafeName(lambdaType);
		return getLambdaTypeId(name, PivotUtil.getOwnedContext(lambdaType), PivotUtil.getOwnedParametersList(lambdaType), PivotUtil.getOwnedResult(lambdaType));
	}

	/**
	 * Return the named lambda typeId with the defined type parameters.
	 * @since 7.0
	 */
	public static @NonNull LambdaTypeId getLambdaTypeId(@NonNull String name, @NonNull LambdaParameter context,
			@Nullable List<@NonNull LambdaParameter> parameters, @NonNull LambdaParameter result) {
		return lambdaTypes.getSingleton(PRIVATE_INSTANCE, name, context, parameters, result);
	}

	/**
	 * Return the named collection typeId.
	 */
	public static @NonNull MapTypeId getMapTypeId(@NonNull String mapTypeName) {
		return mapNames.getSingleton(PRIVATE_INSTANCE, mapTypeName);
	}

	/**
	 * Return the URIed package typeId.
	 */
	public static @NonNull NsURIPackageId getNsURIPackageId(@NonNull String nsURI, @Nullable String nsPrefix, @Nullable EPackage ePackage) {
		NsURIPackageId nsURIPackageId = nsURIs.getSingleton(PRIVATE_INSTANCE, nsURI, nsPrefix, ePackage);
		if ((ePackage != null) && (nsURIPackageId.getEPackage() == null)) {		// Late ePackage may occur if early lifecycle is OCLinEcore then AS
			nsURIPackageId.setEPackage(ePackage);
		}
		return nsURIPackageId;
	}

	/**
	 * Return the OperationId for anOperation.
	 */
	public static @NonNull OperationId getOperationId(@NonNull Operation anOperation) {
		String name = NameUtil.getSafeName(anOperation);
		org.eclipse.ocl.pivot.Class owningClass = PivotUtil.getOwningClass(anOperation);
		TypeId parentTypeId = owningClass.getTypeId();
		TemplateParameters templateParameters = anOperation.getTemplateParameters();
		int typeParametersSize = templateParameters.parametersSize();
		ParametersId parametersId;
		if ((typeParametersSize <= 0) || (anOperation.getUnspecializedElement() != null)) {	// If unspecializeable or specialized
			@NonNull Type @NonNull [] parameterTypes = PivotUtil.getOperationParameterTypes(anOperation);
			parametersId = getParametersId(parameterTypes);
		}
		else {																				// If unspecialized
			TemplateParameterization contextTemplateParameterization = TemplateParameterization.basicGetTemplateParameterization(owningClass);
			TemplateSignature operationTemplateSignature = anOperation.getOwnedSignature();
			if (operationTemplateSignature == null) {										// Never happens
				parametersId = ParametersId.EMPTY;
			}
			else {
				int operationTemplateParameterSize = Iterables.size(PivotUtil.getOwnedParameters(operationTemplateSignature));
				if (operationTemplateParameterSize <= 0) {									// Never happens
					parametersId = ParametersId.EMPTY;
				}
				else {		// Templated operations cannot be overloaded so use the normalized template parameter ids only
					@NonNull TypeId typeIds[] = new @NonNull TypeId[operationTemplateParameterSize];
					int contextTemplateParameterSize = contextTemplateParameterization != null ? contextTemplateParameterization.size() : 0;
					for (int i = 0; i < operationTemplateParameterSize; i++) {
						typeIds[i] = IdManager.getTemplateParameterId(contextTemplateParameterSize + i);
					}
					parametersId = getParametersId(typeIds);
				}
			}
		}
		return parentTypeId.getOperationId(typeParametersSize, name, parametersId);
	}

	/**
	 * Return the named tuple typeId with the defined parts (which have not been alphabetically ordered by part name).
	 * @since 7.0
	 */
	public static @NonNull TupleTypeId getOrderedTupleTypeId(@NonNull Collection<@NonNull PartId> unOrderedPartIds) {
		@NonNull PartId @NonNull [] orderedPartIds = unOrderedPartIds.toArray(new @NonNull PartId [unOrderedPartIds.size()]);
		Arrays.sort(orderedPartIds);
		return tupleTypes.getSingleton(PRIVATE_INSTANCE, orderedPartIds);
	}

	/**
	 * Return the named tuple typeId with the defined parts (which are alphabetically ordered by part name).
	 *
	 * @since 7.0
	 */
	public static @NonNull TupleTypeId getOrderedTupleTypeId(@NonNull PartId @NonNull [] orderedPartIds) {
		return tupleTypes.getSingleton(PRIVATE_INSTANCE, orderedPartIds);
	}

	/**
	 * Return the typeId for aPackage.
	 */
	public static @NonNull PackageId getPackageId(org.eclipse.ocl.pivot.@NonNull Package asPackage) {
		String nsURI;
		URI semantics = PivotUtil.basicGetPackageSemantics(asPackage);
		if (semantics != null) {
			URI trimFragment = semantics.trimFragment();
			if (trimFragment == PivotConstants.METAMODEL_URI) {
				return IdManager.METAMODEL_ID;
			}
			nsURI = trimFragment.toString();
		}
		else {
			nsURI = asPackage.getURI();
		}
		if (nsURI != null) {
			return getNsURIPackageId(nsURI, asPackage.getNsPrefix(), asPackage.getEPackage());
		}
		String name = asPackage.getName();
		//		assert name != null;
		if (name == null) name = "";
		org.eclipse.ocl.pivot.Package parentPackage = asPackage.getOwningPackage();
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
	public static @NonNull PackageId getPackageId(@NonNull EPackage ePackage) {
	//	if (ClassUtil.basicGetMetamodelAnnotation(ePackage) != null) {
	//		return METAMODEL;
	//	}
		String ePackageURI = ePackage.getNsURI();
		if (ePackageURI != null) {
			if (ePackageURI2completeName != null) {										// XXX review
				String metamodelName = ePackageURI2completeName.get(ePackageURI);
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
			EObject eContainer1 = ePackage.eContainer();
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
									return getNsURIPackageId(String.valueOf(uri), ePackage.getNsPrefix(), ePackage);
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
			return getNsURIPackageId(ePackageURI, ePackage.getNsPrefix(), ePackage);
		}
		String name = ePackage.getName();
		assert name != null;
		EPackage parentPackage = ePackage.getESuperPackage();
		if (parentPackage != null) {
			return getPackageId(parentPackage).getNestedPackageId(name);
		}
		return getNsURIPackageId(name, ePackage.getNsPrefix(), null);
	}

	public static @NonNull ParametersId getParametersId(@NonNull Type @NonNull [] parameterTypes) {
		int iSize = parameterTypes.length;
		@NonNull TypeId @NonNull [] typeIds = new @NonNull TypeId[iSize];
		for (int i = 0; i < iSize; i++) {
			typeIds[i] = parameterTypes[i].getTypeId();
		}
		return getParametersId(typeIds);
	}

	/**
	 * Return the parametersId for a given type list.
	 */
	public static @NonNull ParametersId getParametersId(@NonNull TypeId @NonNull ... typeIds) {
		return parametersIds.getSingleton(PRIVATE_INSTANCE, typeIds);
	}

	/**
	 * Return the named lambda/tuple PartId with the defined name and type and nullity.
	 * @since 7.0
	 */
	public static @NonNull PartId getPartId(int index, @NonNull String name, @NonNull TypeId typeId, boolean isRequired) {
		return tupleParts.getSingleton(PRIVATE_INSTANCE, index, name, typeId, isRequired);
	}

	/**
	 * Return the named tuple PartId for the given property of a TupleType.
	 *
	 * @since 7.0
	 */
	public static @NonNull PartId getPartId(@NonNull Property asProperty) {
		TupleType tupleType = (TupleType) PivotUtil.getOwningClass(asProperty);
		String name = NameUtil.getSafeName(asProperty);
		int index = tupleType.getOwnedProperties().indexOf(asProperty);
		return getPartId(index, name, asProperty.getTypeId(), asProperty.isIsRequired());
	}

	/**
	 * Return the named primitive typeId.
	 */
	public static @NonNull PrimitiveTypeId getPrimitiveTypeId(@NonNull String name) {
		return primitiveTypes.getSingleton(PRIVATE_INSTANCE, name);
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
		//	if (PivotConstants.METAMODEL_NAME.equals(name)) {
		//		return METAMODEL;
		//	}
		return roots.getSingleton(PRIVATE_INSTANCE, name);
	}

	/**
	 * @since 1.18
	 */
	public static @NonNull TemplateParameterId getTemplateParameterIndexId(@NonNull TemplateParameter templateParameter) {
		TemplateParameterization templateParameterization = TemplateParameterization.getTemplateParameterization(templateParameter);
		int index = templateParameterization.indexOf(templateParameter);
		return TemplateParameterIdImpl.getTemplateParameterId(index);
	}

	public static @NonNull TemplateParameterId getTemplateParameterId(int index) {
		assert index >= 0;
		return TemplateParameterIdImpl.getTemplateParameterId(index);
	}

	/**
	 * Return the named tuple typeId with the defined collection of parts (which need not be alphabetically ordered).
	 * @since 7.0
	 */
	public static @NonNull TupleTypeId getTupleTypeId(@NonNull Collection<@NonNull ? extends PartId> unOrderedPartIds) {
		@NonNull PartId @NonNull [] unOrderedPartIds2 = new @NonNull PartId[unOrderedPartIds.size()];
		int i = 0;
		for (PartId part : unOrderedPartIds) {
			unOrderedPartIds2[i++] = part;
		}
		return getTupleTypeId(unOrderedPartIds2);
	}

	/**
	 * Return the named tuple typeId with the defined array of parts (which need not be alphabetically ordered).
	 * @since 7.0
	 */
	public static @NonNull TupleTypeId getTupleTypeId(@NonNull PartId @NonNull ... unOrderedPartIds) {
		@NonNull PartId @NonNull [] orderedParts = new @NonNull PartId[unOrderedPartIds.length];
		int i = 0;
		for (PartId part : unOrderedPartIds) {
			orderedParts[i++] = part;
		}
		Arrays.sort(orderedParts);
		int index = 0;
		for (PartId part : unOrderedPartIds) {
			if (part.getIndex() != index) {
				orderedParts[index] = getPartId(index, part.getName(), part.getTypeId(), part.isRequired());
			}
			index++;
		}
		return getOrderedTupleTypeId(orderedParts);
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

	/**
	 * @since 1.18
	 */
	public static @NonNull WildcardId getWildcardId() {
		WildcardId wildcardId2 = wildcardId ;
		if (wildcardId2 == null) {
			wildcardId = wildcardId2 = new WildcardIdImpl(PRIVATE_INSTANCE);
		}
		return wildcardId2;
	}

	private IdManager() {}		// private to guarantee ElementId uniqueness
}