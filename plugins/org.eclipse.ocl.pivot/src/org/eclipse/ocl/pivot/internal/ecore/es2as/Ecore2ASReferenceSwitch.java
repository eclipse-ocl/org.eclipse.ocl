/*******************************************************************************
 * Copyright (c) 2010, 2019 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - initial API and implementation
 *   E.D.Willink (CEA List) - Bug 424057 - UML 2.5 CG
 *******************************************************************************/
package org.eclipse.ocl.pivot.internal.ecore.es2as;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.ETypeParameter;
import org.eclipse.emf.ecore.ETypedElement;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.util.EcoreSwitch;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.Annotation;
import org.eclipse.ocl.pivot.CollectionType;
import org.eclipse.ocl.pivot.Constraint;
import org.eclipse.ocl.pivot.DataType;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.NamedElement;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.Parameter;
import org.eclipse.ocl.pivot.PivotFactory;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.TemplateParameter;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.TypedElement;
import org.eclipse.ocl.pivot.internal.complete.StandardLibraryInternal;
import org.eclipse.ocl.pivot.internal.library.JavaCompareToOperation;
import org.eclipse.ocl.pivot.internal.manager.PivotMetamodelManager;
import org.eclipse.ocl.pivot.internal.utilities.OppositePropertyDetails;
import org.eclipse.ocl.pivot.internal.utilities.PivotConstantsInternal;
import org.eclipse.ocl.pivot.library.LibraryConstants;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.utilities.ValueUtil;
import org.eclipse.ocl.pivot.values.IntegerValue;
import org.eclipse.ocl.pivot.values.NumberValue;
import org.eclipse.ocl.pivot.values.UnlimitedNaturalValue;

/**
 * Resolve the references in the Pivot tree wrt the Ecore tree.
 *
 * caseXXXX returns:
 *  null to delegate to the super-caseXXX; never happens
 *  'this' or an invalid-type/property to fail requiring a retry later when some dependency might have been resolved
 *  non-null, typically the pivot element for success
 */
public class Ecore2ASReferenceSwitch extends EcoreSwitch<Object>
{
	@Deprecated /* @deprected moved to PropertyDetails */
	public static final String PROPERTY_OPPOSITE_ROLE_NAME_KEY = OppositePropertyDetails.PROPERTY_OPPOSITE_ROLE_NAME_KEY;
	@Deprecated /* @deprected moved to PropertyDetails */
	public static final Object PROPERTY_OPPOSITE_ROLE_UNIQUE_KEY = OppositePropertyDetails.PROPERTY_OPPOSITE_ROLE_UNIQUE_KEY;
	@Deprecated /* @deprected moved to PropertyDetails */
	public static final Object PROPERTY_OPPOSITE_ROLE_ORDERED_KEY = OppositePropertyDetails.PROPERTY_OPPOSITE_ROLE_ORDERED_KEY;
	@Deprecated /* @deprected moved to PropertyDetails */
	public static final Object PROPERTY_OPPOSITE_ROLE_LOWER_KEY = OppositePropertyDetails.PROPERTY_OPPOSITE_ROLE_LOWER_KEY;
	@Deprecated /* @deprected moved to PropertyDetails */
	public static final Object PROPERTY_OPPOSITE_ROLE_UPPER_KEY = OppositePropertyDetails.PROPERTY_OPPOSITE_ROLE_UPPER_KEY;

	protected final @NonNull Ecore2AS converter;
	protected final @NonNull PivotMetamodelManager metamodelManager;
	protected final @NonNull StandardLibraryInternal standardLibrary;
	private final @NonNull Property oclInvalidProperty;

	public Ecore2ASReferenceSwitch(@NonNull Ecore2AS converter) {
		this.converter = converter;
		this.metamodelManager = converter.getMetamodelManager();
		this.standardLibrary = metamodelManager.getStandardLibrary();
		this.oclInvalidProperty = standardLibrary.getOclInvalidProperty();
	}

	@Override
	public Object caseEAnnotation(EAnnotation eObject) {
		@SuppressWarnings("null") @NonNull EAnnotation eObject2 = eObject;
		Annotation pivotElement = converter.getCreated(Annotation.class, eObject2);
		if (pivotElement == null) {
			return this;
		}
		doSwitchAll(Element.class, ClassUtil.<Element>nullFree(pivotElement.getReferences()), eObject2.getReferences());
		return pivotElement;
	}

	@Override
	public Object caseEClass(EClass eObject) {
		@SuppressWarnings("null") @NonNull EClass eObject2 = eObject;
		org.eclipse.ocl.pivot.Class pivotElement = converter.getCreated(org.eclipse.ocl.pivot.Class.class, eObject2);
		if (pivotElement == null) {
			return this;
		}
		doSwitchAll(org.eclipse.ocl.pivot.Class.class, ClassUtil.<org.eclipse.ocl.pivot.Class>nullFree(pivotElement.getSuperClasses()), eObject2.getEGenericSuperTypes());
		if (pivotElement.getSuperClasses().isEmpty()) {
			org.eclipse.ocl.pivot.Class oclElementType = standardLibrary.getOclElementType();
			pivotElement.getSuperClasses().add(oclElementType);
		}
		return pivotElement;
	}

	@Override
	public Object caseEDataType(EDataType eObject) {
		@SuppressWarnings("null") @NonNull EDataType eObject2 = eObject;
		DataType pivotElement = converter.getCreated(DataType.class, eObject2);
		if (pivotElement == null) {
			return this;
		}
		Class<?> instanceClass = eObject2.getInstanceClass();
		if (instanceClass != null) {
			try {
				Method declaredMethod = instanceClass.getDeclaredMethod("compareTo", instanceClass);
				if (declaredMethod != null) {
					Operation operation = PivotFactory.eINSTANCE.createOperation();
					operation.setName(LibraryConstants.COMPARE_TO);
					operation.setImplementation(new JavaCompareToOperation(declaredMethod));
					Parameter parameter = PivotFactory.eINSTANCE.createParameter();
					parameter.setName("that");
					parameter.setType(standardLibrary.getOclSelfType());
					operation.getOwnedParameters().add(parameter);
					operation.setType(standardLibrary.getIntegerType());
					pivotElement.getOwnedOperations().add(operation);
					pivotElement.getSuperClasses().add(standardLibrary.getOclComparableType());
				}
			} catch (Exception e) {
			}
		}
		return pivotElement;
	}

	@Override
	public Object caseEEnum(EEnum eObject) {
		return null;			// Drop through to caseEDataType
	}

	@Override
	public Object caseEOperation(EOperation eObject) {
		@SuppressWarnings("null") @NonNull EOperation eObject2 = eObject;
		if (converter.isInvariant(eObject2)) {
			Constraint asConstraint = converter.getCreated(Constraint.class, eObject2);
			if (asConstraint == null) {
				return this;
			}
			EAnnotation redefinesAnnotation = eObject2.getEAnnotation(PivotConstantsInternal.REDEFINES_ANNOTATION_SOURCE);
			if (redefinesAnnotation != null) {
				for (EObject eReference : redefinesAnnotation.getReferences()) {
					if ((eReference != null) && checkProxy(eReference)) {
						NamedElement redefinedConstraint = converter.getCreated(NamedElement.class, eReference);
						if (redefinedConstraint instanceof Constraint) {
							asConstraint.getRedefinedConstraints().add((Constraint)redefinedConstraint);
						}
					}
				}
			}
			return asConstraint;
		}
		else {
			TypedElement pivotElement = caseETypedElement(eObject2);
			//	if (pivotElement == oclInvalidProperty) {
			if (!(pivotElement instanceof Operation)) {
				return this;
			}
			Operation asOperation = (Operation)pivotElement;
			EAnnotation redefinesAnnotation = eObject2.getEAnnotation(PivotConstantsInternal.REDEFINES_ANNOTATION_SOURCE);
			if (redefinesAnnotation != null) {
				for (EObject eReference : redefinesAnnotation.getReferences()) {
					if ((eReference != null) && checkProxy(eReference)) {
						NamedElement redefinedOperation = converter.getCreated(NamedElement.class, eReference);
						if (redefinedOperation instanceof Operation) {
							asOperation.getRedefinedOperations().add((Operation)redefinedOperation);
						}
					}
				}
			}
			doSwitchAll(Type.class, ClassUtil.<Type>nullFree(asOperation.getRaisedExceptions()), eObject2.getEGenericExceptions());
			return asOperation;
		}
	}

	@Override
	public Object caseEReference(EReference eReference) {
		//		Property pivotElement = converter.getCreated(Property.class, eObject);
		Property asProperty = caseEStructuralFeature(eReference);
		if (asProperty == oclInvalidProperty) {
			return this;
		}
		doSwitchAll(Property.class, ClassUtil.<Property>nullFree(asProperty.getKeys()), eReference.getEKeys());
		Property oppositeProperty = null;
		EReference eOpposite = eReference.getEOpposite();
		if (eOpposite != null) {
			oppositeProperty = converter.getCreated(Property.class, eOpposite);
			asProperty.setOpposite(oppositeProperty);
		}
		else {
			OppositePropertyDetails oppositePropertyDetails = OppositePropertyDetails.createFromEReference(eReference);
			if (oppositePropertyDetails != null) {
				metamodelManager.createImplicitOppositeProperty(asProperty, oppositePropertyDetails.getName(),
					oppositePropertyDetails.isOrdered(), oppositePropertyDetails.isUnique(),
					oppositePropertyDetails.getLower(), oppositePropertyDetails.getUpper());
			}
			else {
				asProperty.setOpposite(null);
			}
		}
		//		else if (eObject.eContainer() instanceof EClass) {		// Skip annotation references
		//			metamodelManager.installPropertyDeclaration(pivotElement);
		//		}
		return asProperty;
	}

	@Override
	public @NonNull Property caseEStructuralFeature(EStructuralFeature eObject) {
		@SuppressWarnings("null")@NonNull EStructuralFeature eObject2 = eObject;
		TypedElement pivotElement = caseETypedElement(eObject2);
		if (pivotElement == oclInvalidProperty) {
			return oclInvalidProperty;
		}
		Property asProperty = (Property)pivotElement;
		EAnnotation redefinesAnnotation = eObject2.getEAnnotation(PivotConstantsInternal.REDEFINES_ANNOTATION_SOURCE);
		if (redefinesAnnotation != null) {
			for (EObject eReference : redefinesAnnotation.getReferences()) {
				if ((eReference != null) && checkProxy(eReference)) {
					Property redefinedProperty = converter.getCreated(Property.class, eReference);
					asProperty.getRedefinedProperties().add(redefinedProperty);
				}
			}
		}
		EObject eContainer = eObject2.eContainer();
		if (eContainer instanceof EAnnotation) {
			EAnnotation duplicatesAnnotation = (EAnnotation) eContainer;
			if (PivotConstantsInternal.DUPLICATES_ANNOTATION_SOURCE.equals(duplicatesAnnotation.getSource())) {
				EAnnotation eAnnotation = duplicatesAnnotation.getEAnnotation(eObject.getName());
				if (eAnnotation != null) {
					String newLowerBound = null;
					Boolean newOrdered = null;
					Boolean newUnique = null;
					String newUpperBound = null;
					Type newType = null;
					boolean changedType = false;
					EMap<String, String> details = eAnnotation.getDetails();
					for (String key : details.keySet()) {
						Object value = details.get(key);
						if (value != null) {
							if ("lowerBound".equals(key)) {
								newLowerBound = value.toString();
								changedType = true;
							}
							else if ("ordered".equals(key)) {
								newOrdered = Boolean.valueOf(value.toString());
								changedType = true;
							}
							else  if ("unique".equals(key)) {
								newUnique = Boolean.valueOf(value.toString());
								changedType = true;
							}
							else if ("upperBound".equals(key)) {
								newUpperBound = value.toString();
								changedType = true;
							}
							else if ("eType".equals(key)) {
								String[] path = value.toString().split("::");
								EObject eRoot = EcoreUtil.getRootContainer(eObject);
								int iSize = path.length;
								if ((iSize >= 2) && (eRoot instanceof EPackage)) {
									EPackage ePackage = (EPackage)eRoot;
									if (path[0].equals(ePackage.getName())) {
										for (int i = 1; (ePackage != null) && (i < iSize-1); i++) {
											ePackage = NameUtil.getENamedElement(ePackage.getESubpackages(), path[i]);
										}
										if (ePackage != null) {
											EClassifier eClassifier = NameUtil.getENamedElement(ePackage.getEClassifiers(), path[iSize-1]);
											if (eClassifier != null) {
												newType = converter.getASType(eClassifier);
												changedType = true;
											}
										}
									}
								}
							}
						}
					}
					if (changedType) {
						IntegerValue oldLowerValue;
						boolean oldOrdered;
						boolean oldUnique;
						UnlimitedNaturalValue oldUpperValue;
						Type oldType = asProperty.getType();
						if (oldType instanceof CollectionType) {
							CollectionType oldCollectionType = (CollectionType)oldType;
							oldType = oldCollectionType.getElementType();
							oldLowerValue = oldCollectionType.getLowerValue();
							oldOrdered = oldCollectionType.isOrdered();
							oldUnique = oldCollectionType.isUnique();
							oldUpperValue = oldCollectionType.getUpperValue();
						}
						else {
							oldLowerValue = asProperty.isIsRequired() ? ValueUtil.ONE_VALUE : ValueUtil.ZERO_VALUE;
							oldOrdered = false;
							oldUnique = false;
							oldUpperValue = ValueUtil.UNLIMITED_ONE_VALUE;
						}
						boolean isOrdered = newOrdered != null ? newOrdered.booleanValue() : oldOrdered;
						IntegerValue lowerValue = newLowerBound != null ? ValueUtil.integerValueOf(newLowerBound) : oldLowerValue;
						boolean isUnique = newUnique != null ? newUnique.booleanValue() : oldUnique;
						UnlimitedNaturalValue upperValue = newUpperBound != null ? ValueUtil.unlimitedNaturalValueOf(newUpperBound) : oldUpperValue;
						Type type = newType != null ? newType : oldType;
						boolean isRequired;
						Type pivotType;
						if (type != null) {
							pivotType = type;
							if (((NumberValue)upperValue).equals(ValueUtil.ONE_VALUE)) {
								isRequired = lowerValue.equals(ValueUtil.ONE_VALUE);
							}
							else {
								isRequired = true;
								pivotType = metamodelManager.getCollectionType(isOrdered, isUnique, pivotType, false, lowerValue, upperValue);
							}
						}
						else {
							isRequired = false;
							pivotType = standardLibrary.getOclVoidType();
						}
						asProperty.setType(pivotType);
						asProperty.setIsRequired(isRequired);
					}
				}
			}
		}
		//			Object boxedDefaultValue = null;
		String defaultValueLiteral = null;
		if (eObject.eIsSet(EcorePackage.Literals.ESTRUCTURAL_FEATURE__DEFAULT_VALUE_LITERAL)) {
			defaultValueLiteral = eObject.getDefaultValueLiteral();
			/*				EClassifier eType = eObject.getEType();
				if (eType instanceof EEnum) {
					EEnum eEnum = (EEnum)eType;
					EEnumLiteral unboxedValue = eEnum.getEEnumLiteral(defaultValueLiteral);
					if (unboxedValue != null) {
						boxedDefaultValue = metamodelManager.getPivotOfEcore(EnumerationLiteral.class, unboxedValue);
					}
					else {
//						converter.addError("Unknown enumeration literal");
					}
				}
				else if (eType instanceof EDataType) {
					EDataType eDataType = (EDataType)eType;
					EPackage ePackage = eDataType.getEPackage();
					if (PivotPackage.eNS_URI.equals(ePackage.getNsURI()) && !(ePackage instanceof PivotPackage)) {	// Occurs when ConstraintMerger using dynamic Pivot
						ePackage = PivotPackage.eINSTANCE;
						eDataType = (EDataType) ePackage.getEClassifier(eDataType.getName());
					}
					EFactory eFactoryInstance = ePackage.getEFactoryInstance();
					boxedDefaultValue = eFactoryInstance.createFromString(eDataType, defaultValueLiteral);
				}
				else {
					URI uri = URI.createURI(defaultValueLiteral);
					EObject defaultEObject = metamodelManager.getExternalResourceSet().getEObject(uri, false);
					if (defaultEObject instanceof Element) {
						boxedDefaultValue = defaultEObject;
					}
					else {
						boxedDefaultValue = defaultEObject;
					}
				} */
		}
		asProperty.setDefaultValueString(defaultValueLiteral);
		return asProperty;
	}

	@Override
	public @NonNull TypedElement caseETypedElement(ETypedElement eObject) {
		@SuppressWarnings("null") @NonNull ETypedElement eObject2 = eObject;
		TypedElement pivotElement = converter.getCreated(TypedElement.class, eObject2);
		if (pivotElement == null) {
			return oclInvalidProperty;
		}
		boolean isRequired;
		Type pivotType;
		EGenericType eType = eObject2.getEGenericType();
		if (eType != null) {
			EClassifier eClassifier = eType.getEClassifier();
			int lower = eObject.getLowerBound();
			int upper = eObject.getUpperBound();
			if ((lower == 0) && (upper == -1) && converter.isEcoreOnlyEntryClass(eClassifier)) {
				pivotType = converter.getCreated(Type.class, eType);
				assert converter.isEntryClass(eClassifier);
				assert pivotType == null;
				assert eClassifier != null;
				isRequired = true;
				pivotType = getEcoreOnlyEntryClassMapType((EClass)eClassifier);
			}
			else {
				pivotType = converter.getASType(eType);
				assert pivotType != null;
				if (upper == 1) {
					if (lower == 0) {
						if (converter.cannotBeOptional(eObject)) {
							lower = 1;
							Ecore2AS.NOT_OPTIONAL.println(NameUtil.qualifiedNameFor(eObject) + " converted to not-optional");
						}
						else {
							if (eClassifier instanceof EDataType) {
								Class<?> instanceClass = ((EDataType)eClassifier).getInstanceClass();
								if ((instanceClass == Boolean.class) && (pivotType.getESObject() == EcorePackage.Literals.EBOOLEAN_OBJECT)) {
									pivotType = standardLibrary.getBooleanType();		// Correct Ecore's BooleanObject but not UML's BooleanObject
								}
							}
						}
					}
					isRequired = lower == 1;
				}
				else {
					isRequired = true;
					if (converter.isEntryClass(eClassifier)) {
						Iterable<@NonNull Property> ownedProperties = PivotUtil.getOwnedProperties((org.eclipse.ocl.pivot.Class)pivotType);
						Property keyProperty = ClassUtil.nonNullState(NameUtil.getNameable(ownedProperties, "key"));
						Property valueProperty = ClassUtil.nonNullState(NameUtil.getNameable(ownedProperties, "value"));
						if (keyProperty.getType() == null) {
							return oclInvalidProperty;			// Retry later once type defined
						}
						if (valueProperty.getType() == null) {
							return oclInvalidProperty;			// Retry later once type defined
						}
						pivotType = metamodelManager.getMapType((org.eclipse.ocl.pivot.Class)pivotType);
					}
					else {
						boolean isNullFree = Ecore2AS.isNullFree(eObject);
						boolean isOrdered = eObject.isOrdered();
						boolean isUnique = eObject.isUnique();
						IntegerValue lowerValue = ValueUtil.integerValueOf(lower);
						UnlimitedNaturalValue upperValue = upper != -1 ? ValueUtil.unlimitedNaturalValueOf(upper) : ValueUtil.UNLIMITED_VALUE;
						pivotType = metamodelManager.getCollectionType(isOrdered, isUnique, pivotType, isNullFree, lowerValue, upperValue);
					}
				}
			}
		}
		else {
			isRequired = false;
			pivotType = standardLibrary.getOclVoidType();
		}
		pivotElement.setType(pivotType);
		pivotElement.setIsRequired(isRequired);
		return pivotElement;
	}

	@Override
	public Object caseETypeParameter(ETypeParameter eObject) {
		@SuppressWarnings("null") @NonNull ETypeParameter eObject2 = eObject;
		TemplateParameter pivotElement = converter.getCreated(TemplateParameter.class, eObject2);
		if (pivotElement == null) {
			return this;
		}
		doSwitchAll(org.eclipse.ocl.pivot.Class.class, ClassUtil.<org.eclipse.ocl.pivot.Class>nullFree(pivotElement.getConstrainingClasses()), eObject2.getEBounds());
		return pivotElement;
	}

	protected boolean checkProxy(@NonNull EObject eReference) {		// BUG 457206 MARTE has unresolveable proxies
		if (!eReference.eIsProxy()) {
			return true;
		}
		converter.error("Unresolved proxy: " + EcoreUtil.getURI(eReference));
		return false;
	}

	public Object doInPackageSwitch(EObject eObject) {
		int classifierID = eObject.eClass().getClassifierID();
		return doSwitch(classifierID, eObject);
	}

	public <T extends Element> void doSwitchAll(Class<T> pivotClass, Collection<T> pivotElements, List<? extends EObject> eObjects) {
		@SuppressWarnings("null") @NonNull Class<T> pivotClass2 = pivotClass;
		for (EObject eObject : eObjects) {
			if ((eObject != null) && checkProxy(eObject)) {
				T pivotElement = converter.getASElement(pivotClass2, eObject);
				if (pivotElement != null) {
					pivotElements.add(pivotElement);
				}
			}
		}
	}

	/**
	 * @since 1.7
	 */
	protected @Nullable Type getEcoreOnlyEntryClassMapType(@NonNull EClass eClass) {
		EStructuralFeature keyFeature = eClass.getEStructuralFeature("key");
		EStructuralFeature valueFeature = eClass.getEStructuralFeature("value");
		if (keyFeature == null) {
			converter.error("Missing 'key' feature for map '" + eClass.getName() + "");
		}
		else if (valueFeature == null) {
			converter.error("Missing 'value' feature for map '" + eClass.getName() + "");
		}
		else {
			EGenericType keyGenericType = keyFeature.getEGenericType();
			EGenericType valueGenericType = valueFeature.getEGenericType();
			if (keyGenericType == null) {
				converter.error("No 'key' EGenericType for map '" + eClass.getName() + "");
			}
			else if (valueGenericType == null) {
				converter.error("No 'value' EGenericType for map '" + eClass.getName() + "");
			}
			else {
				Map<@NonNull String, @NonNull Type> resolvedSpecializations = new HashMap<>();
				Type keyType = converter.resolveType(resolvedSpecializations, keyGenericType);
				Type valueType = converter.resolveType(resolvedSpecializations, valueGenericType);
				if ((keyType != null) && (valueType != null)) {
					boolean keysAreNullFree = keyFeature.isRequired();
					boolean valuesAreNullFree = valueFeature.isRequired();
					org.eclipse.ocl.pivot.Class mapMetatype = standardLibrary.getMapType();
					return metamodelManager.getCompleteEnvironment().getMapType(mapMetatype, keyType, keysAreNullFree, valueType, valuesAreNullFree);
				}
			}
		}
		return null;
	}
}