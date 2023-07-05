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
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
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
import org.eclipse.ocl.pivot.AnyType;
import org.eclipse.ocl.pivot.CollectionType;
import org.eclipse.ocl.pivot.CompleteStandardLibrary;
import org.eclipse.ocl.pivot.Constraint;
import org.eclipse.ocl.pivot.DataType;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.LambdaType;
import org.eclipse.ocl.pivot.MapType;
import org.eclipse.ocl.pivot.NamedElement;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.Parameter;
import org.eclipse.ocl.pivot.PivotFactory;
import org.eclipse.ocl.pivot.PrimitiveType;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.Stereotype;
import org.eclipse.ocl.pivot.TemplateParameter;
import org.eclipse.ocl.pivot.TemplateSignature;
import org.eclipse.ocl.pivot.TemplateableElement;
import org.eclipse.ocl.pivot.TupleType;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.TypedElement;
import org.eclipse.ocl.pivot.internal.library.JavaCompareToOperation;
import org.eclipse.ocl.pivot.internal.manager.PivotMetamodelManager;
import org.eclipse.ocl.pivot.internal.utilities.OppositePropertyDetails;
import org.eclipse.ocl.pivot.internal.utilities.PivotConstantsInternal;
import org.eclipse.ocl.pivot.internal.utilities.PivotUtilInternal;
import org.eclipse.ocl.pivot.library.LibraryConstants;
import org.eclipse.ocl.pivot.utilities.AnnotationUtil;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.utilities.ValueUtil;
import org.eclipse.ocl.pivot.values.IntegerValue;
import org.eclipse.ocl.pivot.values.NumberValue;
import org.eclipse.ocl.pivot.values.TemplateParameterSubstitutions;
import org.eclipse.ocl.pivot.values.TemplateParameterSubstitutions.SimpleTemplateParameterSubstitutions;
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
	protected final @NonNull CompleteStandardLibrary standardLibrary;
	private final @NonNull Property oclInvalidProperty;

	public Ecore2ASReferenceSwitch(@NonNull Ecore2AS converter) {
		this.converter = converter;
		this.metamodelManager = converter.getMetamodelManager();
		this.standardLibrary = metamodelManager.getStandardLibrary();
		this.oclInvalidProperty = standardLibrary.getOclInvalidProperty();
	}

	@Override
	public Object caseEAnnotation(EAnnotation eAnnotation) {
		assert eAnnotation != null;
		Annotation pivotElement = converter.getCreated(Annotation.class, eAnnotation);
		if (pivotElement == null) {
			return this;
		}
		doSwitchAll(Element.class, ClassUtil.<Element>nullFree(pivotElement.getReferences()), eAnnotation.getReferences());
		return pivotElement;
	}

	@Override
	public Object caseEClass(EClass eClass) {
		assert eClass != null;
		org.eclipse.ocl.pivot.Class pivotElement = converter.getCreated(org.eclipse.ocl.pivot.Class.class, eClass);
		if (pivotElement == null) {
			return this;
		}
		doSwitchAll(org.eclipse.ocl.pivot.Class.class, ClassUtil.<org.eclipse.ocl.pivot.Class>nullFree(pivotElement.getSuperClasses()), eClass.getEGenericSuperTypes());
		if (pivotElement.getSuperClasses().isEmpty() && !(pivotElement instanceof AnyType) /*&& !pivotElement .isIsInterface()*/) {	// CompaniesCS2AS.qvtc's Visiatbale needs OclElement
			org.eclipse.ocl.pivot.Class superType = pivotElement instanceof Stereotype ? standardLibrary.getOclStereotypeType() : standardLibrary.getOclElementType();
			pivotElement.getSuperClasses().add(superType);
		}
		return pivotElement;
	}

	@Override
	public Object caseEDataType(EDataType eDataType) {
		assert eDataType != null;
		DataType pivotElement = converter.getCreated(DataType.class, eDataType);
		if (pivotElement == null) {
			return this;
		}
		if (!(pivotElement instanceof PrimitiveType)) {
			doCompareToOperation(pivotElement, eDataType);
		}
		return pivotElement;
	}

	@Override
	public Object caseEEnum(EEnum eEnum) {
		return null;			// Drop through to caseEDataType
	}

	@Override
	public Object caseEGenericType(EGenericType eGenericType) {		// XXX cf doEGenericType
		assert eGenericType != null;
		Type asType = doEGenericType(eGenericType);
		return asType;
	/*	ETypeParameter eTypeParameter = eGenericType.getETypeParameter();
		if (eTypeParameter != null) {
			return doInPackageSwitch(eTypeParameter);
		}
		EClassifier eClassifier = eGenericType.getEClassifier();
		if (eClassifier != null) {
			Type asType = (Type)doInPackageSwitch(eClassifier);
			assert asType != null;
			List<EGenericType> eTypeArguments = eGenericType.getETypeArguments();
			final int iSize = eTypeArguments.size();
			if (iSize > 0) {
				TemplateableElement asTemplateableElement = (TemplateableElement)asType;
				TemplateSignature asSignature = asTemplateableElement.getOwnedSignature();
				assert asSignature != null;
				List<@NonNull TemplateParameter> asFormalParameters = PivotUtilInternal.getOwnedParametersList(asSignature);
				TemplateParameterSubstitutions bindings = new SimpleTemplateParameterSubstitutions();
				for (int i = 0; i < iSize; i++) {
					EGenericType eTypeArgument = eTypeArguments.get(i);
					Type asActualType = (Type)doInPackageSwitch(eTypeArgument);
					assert asActualType != null;
					TemplateParameter asFormalParameter = asFormalParameters.get(i);
					bindings.put(asFormalParameter, asActualType);
				}
				asType = standardLibrary.getSpecializedType(asType, bindings);
			}
			return asType;  //xxx typeArguments
		} */
	//	throw new UnsupportedOperationException();
	//	boolean ok = doEGenericType(???, eGenericType, null);
	//	assert ok;
	}

	@Override
	public Object caseEOperation(EOperation eOperation) {
		assert eOperation != null;
		if (converter.isInvariant(eOperation)) {
			Constraint asConstraint = converter.getCreated(Constraint.class, eOperation);
			if (asConstraint == null) {
				return this;
			}
			EAnnotation redefinesAnnotation = eOperation.getEAnnotation(PivotConstantsInternal.REDEFINES_ANNOTATION_SOURCE);
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
			TypedElement pivotElement = caseETypedElement(eOperation);
			//	if (pivotElement == oclInvalidProperty) {
			if (!(pivotElement instanceof Operation)) {
				return this;
			}
			Operation asOperation = (Operation)pivotElement;
			EAnnotation redefinesAnnotation = eOperation.getEAnnotation(PivotConstantsInternal.REDEFINES_ANNOTATION_SOURCE);
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
			doSwitchAll(Type.class, ClassUtil.<Type>nullFree(asOperation.getRaisedExceptions()), eOperation.getEGenericExceptions());
			return asOperation;
		}
	}

	@Override
	public Object caseEReference(EReference eReference) {
		assert eReference != null;
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
		else if (Boolean.valueOf(AnnotationUtil.getEAnnotationValue(eReference, AnnotationUtil.PROPERTY_ANNOTATION_SOURCE, AnnotationUtil.PROPERTY_SELF))) {
			asProperty.setOpposite(asProperty);
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
	public @NonNull Property caseEStructuralFeature(EStructuralFeature eStructuralFeature) {
		assert eStructuralFeature != null;
		TypedElement pivotElement = caseETypedElement(eStructuralFeature);
		if (pivotElement == oclInvalidProperty) {
			return oclInvalidProperty;
		}
		Property asProperty = (Property)pivotElement;
		EAnnotation redefinesAnnotation = eStructuralFeature.getEAnnotation(PivotConstantsInternal.REDEFINES_ANNOTATION_SOURCE);
		if (redefinesAnnotation != null) {
			for (EObject eReference : redefinesAnnotation.getReferences()) {
				if ((eReference != null) && checkProxy(eReference)) {
					Property redefinedProperty = converter.getCreated(Property.class, eReference);
					asProperty.getRedefinedProperties().add(redefinedProperty);
				}
			}
		}
		EObject eContainer = eStructuralFeature.eContainer();
		if (eContainer instanceof EAnnotation) {
			EAnnotation duplicatesAnnotation = (EAnnotation) eContainer;
			if (PivotConstantsInternal.DUPLICATES_ANNOTATION_SOURCE.equals(duplicatesAnnotation.getSource())) {
				EAnnotation eAnnotation = duplicatesAnnotation.getEAnnotation(eStructuralFeature.getName());
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
								EObject eRoot = EcoreUtil.getRootContainer(eStructuralFeature);
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
								CollectionType genericCollectionType = standardLibrary.getCollectionType(isOrdered, isUnique);
								pivotType = standardLibrary.getCollectionType(genericCollectionType, pivotType, false, lowerValue, upperValue);
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
		if (eStructuralFeature.eIsSet(EcorePackage.Literals.ESTRUCTURAL_FEATURE__DEFAULT_VALUE_LITERAL)) {
			defaultValueLiteral = eStructuralFeature.getDefaultValueLiteral();
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
	public @NonNull TypedElement caseETypedElement(ETypedElement eTypedElement) {
		assert eTypedElement != null;
		TypedElement pivotElement = converter.getCreated(TypedElement.class, eTypedElement);
		if (pivotElement == null) {
			return oclInvalidProperty;
		}
		EGenericType eGenericType = eTypedElement.getEGenericType();
		if (eGenericType == null) {					// Null for Operation returning void
			pivotElement.setType(standardLibrary.getOclVoidType());
			pivotElement.setIsRequired(false);
			return pivotElement;
		}
		else if (doEGenericType(pivotElement, eGenericType, null)) {
			return pivotElement;
		}
		else {
			return oclInvalidProperty;
		}
	}

	@Override
	public Object caseETypeParameter(ETypeParameter eTypeParameter) {
		assert eTypeParameter != null;
		TemplateParameter pivotElement = converter.getCreated(TemplateParameter.class, eTypeParameter);
		if (pivotElement == null) {
			return this;
		}
		doSwitchAll(org.eclipse.ocl.pivot.Class.class, ClassUtil.<org.eclipse.ocl.pivot.Class>nullFree(pivotElement.getConstrainingClasses()), eTypeParameter.getEBounds());
		return pivotElement;
	}

	protected boolean checkProxy(@NonNull EObject eReference) {		// BUG 457206 MARTE has unresolveable proxies
		if (!eReference.eIsProxy()) {
			return true;
		}
		converter.error("Unresolved proxy: " + EcoreUtil.getURI(eReference));
		return false;
	}

	/**
	 * @since 1.17
	 */
	protected void doCompareToOperation(@NonNull DataType pivotElement, @NonNull EDataType eDataType) {
		Class<?> instanceClass = eDataType.getInstanceClass();
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
	}

	protected boolean doEGenericType(@NonNull TypedElement asTypedElement, @NonNull EGenericType eGenericType, @Nullable Map<@NonNull ETypeParameter, @NonNull EGenericType> parameter2argument) {
		// Null eGenericType for Operation returning void handled by caller;
		ETypedElement eTypedElement = (ETypedElement)eGenericType.eContainer();
		assert eTypedElement != null;
		//
		// Traverse known Lambda parameter/argument bindings
		//
		if (parameter2argument != null) {
			for (ETypeParameter eTypeParameter; (eTypeParameter = eGenericType.getETypeParameter()) != null; ) {
				assert eGenericType.getEClassifier() == null;
				EGenericType eTypeArgument = parameter2argument.get(eTypeParameter);
				if (eTypeArgument != null) {
					eGenericType = eTypeArgument;
				}
				else {
					break;
				}
			}
		}
		assert eGenericType != null;
		EClassifier eClassifier = eGenericType.getEClassifier();
		//
		//	Resolve an ETypeParameter to its already created element.
		//
		ETypeParameter eTypeParameter = eGenericType.getETypeParameter();
		if (eTypeParameter != null) {
			assert eClassifier == null;
			TemplateParameter asTemplateParameter = converter.getCreated(TemplateParameter.class, eTypeParameter);
			assert asTemplateParameter != null;
			setTypeAndMultiplicity(asTypedElement, asTemplateParameter, eTypedElement);
			return true;
		}
		//
		//	Compute the mapping from EClassifier parameters to invocation arguments.
		//
		assert eClassifier != null;
		List<@NonNull EGenericType> eTypeArguments = ClassUtil.nullFree(eGenericType.getETypeArguments());
		List<@NonNull ETypeParameter> eTypeParameters = ClassUtil.nullFree(eClassifier.getETypeParameters());
		int iSize = eTypeArguments.size();
		assert iSize == eTypeParameters.size();
		if (iSize > 0) {
			if (parameter2argument == null) {
				parameter2argument = new HashMap<>();
			}
			for (int i = 0; i < iSize; i++) {
				parameter2argument.put(eTypeParameters.get(i), eTypeArguments.get(i));
			}
		}
		int lower = eTypedElement.getLowerBound();
		int upper = eTypedElement.getUpperBound();
		String role = AnnotationUtil.getEAnnotationValue(eClassifier, AnnotationUtil.CLASSIFIER_ANNOTATION_SOURCE, AnnotationUtil.CLASSIFIER_ROLE);
		boolean isEntry = AnnotationUtil.CLASSIFIER_ROLE_ENTRY.equals(role);
		boolean isLambda = AnnotationUtil.CLASSIFIER_ROLE_LAMBDA.equals(role);
		boolean isTuple = AnnotationUtil.CLASSIFIER_ROLE_TUPLE.equals(role);
		if ((lower == 0) && (upper == -1) && isEntry) {		// Collection of Entry is a Map
			Type asType = doImplicitEntryClassMapType(eGenericType);
			asTypedElement.setType(asType);
			asTypedElement.setIsRequired(asType != null);
		}
		else if (/*(lower == 0) && (upper == -1) &&*/ converter.isEntryClass(eClassifier)) {
			assert lower == 0;
			assert upper < 0;
			Type asType = doExplicitEntryClassMapType(eGenericType);
			asTypedElement.setType(asType);
			asTypedElement.setIsRequired(asType != null);
		}
		else if (/*(lower == 0) && (upper == 1) &&*/ isLambda) {
			LambdaType asLambdaType = doLambdaType(eGenericType);
			setTypeAndMultiplicity(asTypedElement, asLambdaType, eTypedElement);
		}
		else if (isTuple) {
			TupleType asTupleType = doTupleType(eGenericType, parameter2argument);
			setTypeAndMultiplicity(asTypedElement, asTupleType, eTypedElement);
		}
	/*	else if (converter.isEntryClass(eClassifier)) {
			org.eclipse.ocl.pivot.Class asEntryClass = (org.eclipse.ocl.pivot.Class)converter.getASType(eGenericType);
			assert asEntryClass != null;
			Iterable<@NonNull Property> ownedProperties = PivotUtil.getOwnedProperties(asEntryClass);
			Property keyProperty = ClassUtil.nonNullState(NameUtil.getNameable(ownedProperties, "key"));
			Property valueProperty = ClassUtil.nonNullState(NameUtil.getNameable(ownedProperties, "value"));
			if (keyProperty.getType() == null) {
				return false;			// Retry later once type defined
			}
			if (valueProperty.getType() == null) {
				return false;			// Retry later once type defined
			}
			Type asType = standardLibrary.getMapOfEntryType(asEntryClass);
			asTypedElement.setType(asType);
			asTypedElement.setIsRequired(true);
		} */
		else {
			Type asElementType = converter.getASType(eGenericType);
			assert asElementType != null;
			setTypeAndMultiplicity(asTypedElement, asElementType, eTypedElement);
		}
		return true;
	}

	protected @NonNull Type doEGenericType(@NonNull EGenericType eGenericType) {
		// Null eGenericType for Operation returning void handled by caller;
	//	ETypedElement eTypedElement = (ETypedElement)eGenericType.eContainer();
	//	assert eTypedElement != null;
		assert eGenericType != null;
		EClassifier eClassifier = eGenericType.getEClassifier();
		//
		//	Resolve an ETypeParameter to its already created element.
		//
		ETypeParameter eTypeParameter = eGenericType.getETypeParameter();
		if (eTypeParameter != null) {
			assert eClassifier == null;
			TemplateParameter asTemplateParameter = converter.getCreated(TemplateParameter.class, eTypeParameter);
			assert asTemplateParameter != null;
			return asTemplateParameter;
		}
		//
		//	Compute the mapping from EClassifier parameters to invocation arguments.
		//
		assert eClassifier != null;
		List<@NonNull EGenericType> eTypeArguments = ClassUtil.nullFree(eGenericType.getETypeArguments());
		List<@NonNull ETypeParameter> eTypeParameters = ClassUtil.nullFree(eClassifier.getETypeParameters());
		int iSize = eTypeArguments.size();
		assert iSize == eTypeParameters.size();
	//	int lower = eTypedElement.getLowerBound();
	//	int upper = eTypedElement.getUpperBound();
		String role = AnnotationUtil.getEAnnotationValue(eClassifier, AnnotationUtil.CLASSIFIER_ANNOTATION_SOURCE, AnnotationUtil.CLASSIFIER_ROLE);
		boolean isEntry = AnnotationUtil.CLASSIFIER_ROLE_ENTRY.equals(role);
		boolean isLambda = AnnotationUtil.CLASSIFIER_ROLE_LAMBDA.equals(role);
		boolean isTuple = AnnotationUtil.CLASSIFIER_ROLE_TUPLE.equals(role);
		if (/*(lower == 0) && (upper == -1) &&*/ isEntry) {		// Collection of Entry is a Map
			Type asType = doImplicitEntryClassMapType(eGenericType);
			return asType;
		}
		else if (/*(lower == 0) && (upper == -1) &&*/ converter.isEntryClass(eClassifier)) {
		//	assert lower == 0;
		//	assert upper < 0;
			Type asType = doExplicitEntryClassMapType(eGenericType);
			return asType;
		}
		else if (/*(lower == 0) && (upper == 1) &&*/ isLambda) {
			LambdaType asLambdaType = doLambdaType(eGenericType);
			return asLambdaType;
		}
		else if (isTuple) {
			TupleType asTupleType = doTupleType(eGenericType, null);
			return asTupleType;
		}
	/*	else if (converter.isEntryClass(eClassifier)) {
			org.eclipse.ocl.pivot.Class asEntryClass = (org.eclipse.ocl.pivot.Class)converter.getASType(eGenericType);
			assert asEntryClass != null;
			Iterable<@NonNull Property> ownedProperties = PivotUtil.getOwnedProperties(asEntryClass);
			Property keyProperty = ClassUtil.nonNullState(NameUtil.getNameable(ownedProperties, "key"));
			Property valueProperty = ClassUtil.nonNullState(NameUtil.getNameable(ownedProperties, "value"));
			if (keyProperty.getType() == null) {
				return false;			// Retry later once type defined
			}
			if (valueProperty.getType() == null) {
				return false;			// Retry later once type defined
			}
			Type asType = standardLibrary.getMapOfEntryType(asEntryClass);
			asTypedElement.setType(asType);
			asTypedElement.setIsRequired(true);
		} */
		else {

			assert eClassifier != null;
			Type asType = (Type)doInPackageSwitch(eClassifier);
			assert asType != null;
		//	List<EGenericType> eTypeArguments = eGenericType.getETypeArguments();
		//	final int iSize = eTypeArguments.size();
			if (iSize > 0) {
				TemplateableElement asTemplateableElement = (TemplateableElement)asType;
				TemplateSignature asSignature = asTemplateableElement.getOwnedSignature();
				assert asSignature != null;
				List<@NonNull TemplateParameter> asFormalParameters = PivotUtilInternal.getOwnedParametersList(asSignature);
				TemplateParameterSubstitutions bindings = new SimpleTemplateParameterSubstitutions();
				for (int i = 0; i < iSize; i++) {
					EGenericType eTypeArgument = eTypeArguments.get(i);
					Type asActualType = (Type)doInPackageSwitch(eTypeArgument);
					assert asActualType != null;
					TemplateParameter asFormalParameter = asFormalParameters.get(i);
					bindings.put(asFormalParameter, asActualType);
				}
				asType = standardLibrary.getSpecializedType(asType, bindings);
			}
			return asType;  //xxx typeArguments





		//	Type asElementType = converter.getASType(eGenericType);
		//	assert asElementType != null;
		//	return asElementType;
		}
	}

	private @Nullable Type doExplicitEntryClassMapType(@NonNull EGenericType eGenericType) {
		EClass eClass = (EClass) eGenericType.getEClassifier();
		assert eClass != null;
		assert converter.isEntryClass(eClass);
		org.eclipse.ocl.pivot.Class asEntryClass = converter.getCreated(org.eclipse.ocl.pivot.Class.class, eGenericType);
		assert asEntryClass != null;
		Type asType = null;
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
					asType = standardLibrary.getMapType(keyType, keysAreNullFree, valueType, valuesAreNullFree);
					if (asType instanceof MapType) {
						((MapType)asType).setEntryClass(asEntryClass);
					}
				}
			}
		}
		return asType;
	}

	private @Nullable Type doImplicitEntryClassMapType(@NonNull EGenericType eGenericType) {
		EClassifier eClassifier = eGenericType.getEClassifier();
		assert eClassifier != null;
		Type asType = converter.getCreated(Type.class, eGenericType);
		assert converter.isEntryClass(eClassifier);
		assert asType == null;
		EClass eClass = (EClass)eGenericType.getEClassifier();
		EList<EGenericType> eTypeArguments = eGenericType.getETypeArguments();
		EStructuralFeature keyFeature = eClass.getEStructuralFeature("key");
		EStructuralFeature valueFeature = eClass.getEStructuralFeature("value");
		if (keyFeature == null) {
			converter.error("Missing 'key' feature for map '" + eClass.getName() + "");
		}
		else if (valueFeature == null) {
			converter.error("Missing 'value' feature for map '" + eClass.getName() + "");
		}
		else if (eTypeArguments.size() != 2) {
			converter.error("Inconsistent template arguments for map of '" + eClass.getName() + "");
		}
		else {
			EGenericType keyGenericType = eTypeArguments.get(0);
			EGenericType valueGenericType = eTypeArguments.get(1);
			assert keyGenericType != null;
			assert valueGenericType != null;
			Map<@NonNull String, @NonNull Type> resolvedSpecializations = new HashMap<>();
			Type keyType = converter.resolveType(resolvedSpecializations, keyGenericType);
			Type valueType = converter.resolveType(resolvedSpecializations, valueGenericType);
			if ((keyType != null) && (valueType != null)) {
				boolean keysAreNullFree = keyFeature.isRequired();
				boolean valuesAreNullFree = valueFeature.isRequired();
				asType = standardLibrary.getMapType(keyType, keysAreNullFree, valueType, valuesAreNullFree);
			}
		}
		return asType;
	}

	public Object doInPackageSwitch(EObject eObject) {
		int classifierID = eObject.eClass().getClassifierID();
		return doSwitch(classifierID, eObject);
	}

	private @NonNull LambdaType doLambdaType(@NonNull EGenericType eGenericType) {
		ETypedElement eTypedElement = (ETypedElement)eGenericType.eContainer();
		assert eTypedElement != null;
		EClassifier eClassifier = eGenericType.getEClassifier();
		assert eClassifier != null;
		assert !converter.isEntryClass(eClassifier);
		Type asType = converter.getCreated(Type.class, eGenericType);
		assert asType == null;
		List<EGenericType> eTypeArguments = eGenericType.getETypeArguments();
		final int size = eTypeArguments.size();
		assert size >= 2;
		Type contextType = null;
		List<@NonNull Type> parameterTypes = new ArrayList<>(size-2);
		Type resultType = null;
		for (int i = 0; i < size; i++) {
			EGenericType eTypeArgument = eTypeArguments.get(i);
			Type argumentType = (Type)doInPackageSwitch(eTypeArgument);
			assert argumentType != null;
			if (i == 0) {
				contextType = argumentType;
			}
			else if (i < size-1) {
				parameterTypes.add(argumentType);
			}
			else {
				resultType = argumentType;
			//	if ((resultType instanceof TemplateableElement) && (((TemplateableElement)resultType).getOwnedSignature() != null)) {		// XXX debugging
			//		argumentType = (Type)doInPackageSwitch(eTypeArgument);				// XXX debugging
			//	}
			}
		}
		assert contextType != null;
		assert resultType != null;
		return standardLibrary.getLambdaType(contextType, parameterTypes, resultType, null);
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

	private @NonNull TupleType doTupleType(@NonNull EGenericType eGenericType, @Nullable Map<@NonNull ETypeParameter, @NonNull EGenericType> parameter2argument) {
		EClassifier eClassifier = eGenericType.getEClassifier();
		assert eClassifier != null;
		ETypedElement eTypedElement = (ETypedElement)eGenericType.eContainer();
		assert eTypedElement != null;
		Collection<@NonNull Property> parts = new ArrayList<>();
		for (EStructuralFeature eFeature : ((EClass)eClassifier).getEStructuralFeatures()) {
			String partName = eFeature.getName();
			assert partName != null;
			Property property = PivotUtil.createProperty(partName, null);
			EGenericType eGenericPartType = eFeature.getEGenericType();
			assert eGenericPartType != null;
			boolean ok = doEGenericType(property, eGenericPartType, parameter2argument);
			assert ok;
			parts.add(property);
		}
		return standardLibrary.getTupleType("Tuple", parts , null);
	}

	private @NonNull Type setTypeAndMultiplicity(@NonNull TypedElement asTypedElement, @NonNull Type asElementType, @NonNull ETypedElement eTypedElement) {
		assert eTypedElement != null;
		int lower = eTypedElement.getLowerBound();
		int upper = eTypedElement.getUpperBound();
		if (upper == 1) {
			if (lower == 0) {
				if (converter.cannotBeOptional(eTypedElement)) {
					lower = 1;
					Ecore2AS.NOT_OPTIONAL.println(NameUtil.qualifiedNameFor(eTypedElement) + " converted to not-optional");
				}
				else {
					EClassifier eClassifier = eTypedElement.getEGenericType().getEClassifier();
					if (eClassifier instanceof EDataType) {
						Class<?> instanceClass = eClassifier.getInstanceClass();
						if ((instanceClass == Boolean.class) && (asElementType.getESObject() == EcorePackage.Literals.EBOOLEAN_OBJECT)) {
							asElementType = standardLibrary.getBooleanType();		// Correct Ecore's BooleanObject but not UML's BooleanObject
						}
					}
				}
			}
			asTypedElement.setType(asElementType);
			asTypedElement.setIsRequired(lower == 1);
			return asElementType;
		}
		else {
			boolean isNullFree = Ecore2AS.isNullFree(eTypedElement);
			boolean isOrdered = eTypedElement.isOrdered();
			boolean isUnique = eTypedElement.isUnique();
			IntegerValue lowerValue = ValueUtil.integerValueOf(lower);
			UnlimitedNaturalValue upperValue = upper != -1 ? ValueUtil.unlimitedNaturalValueOf(upper) : ValueUtil.UNLIMITED_VALUE;
			String kind = AnnotationUtil.getEAnnotationValue(eTypedElement, AnnotationUtil.COLLECTION_ANNOTATION_SOURCE, AnnotationUtil.COLLECTION_KIND);
			CollectionType genericCollectionType = null;
			if (kind != null) {
				if ("Collection".equals(kind)) {
					genericCollectionType = standardLibrary.getCollectionType();
				}
				else if ("OrderedCollection".equals(kind)) {
					genericCollectionType = standardLibrary.getOrderedCollectionType();
				}
				else if ("UniqueCollection".equals(kind)) {
					genericCollectionType = standardLibrary.getUniqueCollectionType();
				}
				else {
					converter.error("Unsupported " + AnnotationUtil.COLLECTION_ANNOTATION_SOURCE + "." + AnnotationUtil.COLLECTION_KIND + "\"" + kind + "\"");
				}
			}
			if (genericCollectionType == null) {
				genericCollectionType = standardLibrary.getCollectionType(isOrdered, isUnique);
			}
			Type asType = standardLibrary.getCollectionType(genericCollectionType, asElementType, isNullFree, lowerValue, upperValue);
			asTypedElement.setType(asType);
			asTypedElement.setIsRequired(true);
			return asType;
		}
	}
}