/*******************************************************************************
 * Copyright (c) 2010, 2017 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   E.D.Willink - initial API and implementation
 *   E.D.Willink (CEA List) - Bug 424057 - UML 2.5 CG
 *******************************************************************************/
package org.eclipse.ocl.pivot.internal.ecore.es2as;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
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
import org.eclipse.emf.ecore.xmi.impl.EMOFExtendedMetaData;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.Annotation;
import org.eclipse.ocl.pivot.CollectionType;
import org.eclipse.ocl.pivot.Constraint;
import org.eclipse.ocl.pivot.DataType;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.Model;
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
import org.eclipse.ocl.pivot.internal.utilities.PivotConstantsInternal;
import org.eclipse.ocl.pivot.library.LibraryConstants;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.utilities.ValueUtil;
import org.eclipse.ocl.pivot.values.IntegerValue;
import org.eclipse.ocl.pivot.values.UnlimitedNaturalValue;

public class Ecore2ASReferenceSwitch extends EcoreSwitch<Object>
{
	private static final Logger logger = Logger.getLogger(Ecore2ASReferenceSwitch.class);

	/**
	 * The key that identifies opposite role names in an annotation
	 */
	public static final String PROPERTY_OPPOSITE_ROLE_NAME_KEY = "Property.oppositeRoleName"; //$NON-NLS-1$
	public static final Object PROPERTY_OPPOSITE_ROLE_UNIQUE_KEY = "Property.oppositeUnique"; //$NON-NLS-1$
	public static final Object PROPERTY_OPPOSITE_ROLE_ORDERED_KEY = "Property.oppositeOrdered"; //$NON-NLS-1$
	public static final Object PROPERTY_OPPOSITE_ROLE_LOWER_KEY = "Property.oppositeLower"; //$NON-NLS-1$
	public static final Object PROPERTY_OPPOSITE_ROLE_UPPER_KEY = "Property.oppositeUpper"; //$NON-NLS-1$

	protected final @NonNull Ecore2AS converter;
	protected final @NonNull PivotMetamodelManager metamodelManager;
	protected final @NonNull StandardLibraryInternal standardLibrary;

	public Ecore2ASReferenceSwitch(@NonNull Ecore2AS converter) {
		this.converter = converter;
		this.metamodelManager = converter.getMetamodelManager();
		this.standardLibrary = metamodelManager.getStandardLibrary();
	}

	@Override
	public Object caseEAnnotation(EAnnotation eObject) {
		@SuppressWarnings("null") @NonNull EAnnotation eObject2 = eObject;
		Annotation pivotElement = converter.getCreated(Annotation.class, eObject2);
		if (pivotElement != null) {
			doSwitchAll(Element.class, ClassUtil.<Element>nullFree(pivotElement.getReferences()), eObject2.getReferences());
		}
		return null;
	}

	@Override
	public Object caseEClass(EClass eObject) {
		@SuppressWarnings("null") @NonNull EClass eObject2 = eObject;
		org.eclipse.ocl.pivot.Class pivotElement = converter.getCreated(org.eclipse.ocl.pivot.Class.class, eObject2);
		if (pivotElement != null) {
			doSwitchAll(org.eclipse.ocl.pivot.Class.class, ClassUtil.<org.eclipse.ocl.pivot.Class>nullFree(pivotElement.getSuperClasses()), eObject2.getEGenericSuperTypes());
			if (pivotElement.getSuperClasses().isEmpty()) {
				org.eclipse.ocl.pivot.Class oclElementType = standardLibrary.getOclElementType();
				pivotElement.getSuperClasses().add(oclElementType);
			}
		}
		return null;
	}

	@Override
	public Object caseEDataType(EDataType eObject) {
		@SuppressWarnings("null") @NonNull EDataType eObject2 = eObject;
		DataType pivotElement = converter.getCreated(DataType.class, eObject2);
		if (pivotElement != null) {
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
		}
		return null;
	}

	@Override
	public Object caseEEnum(EEnum eObject) {
		return null;
	}

	@Override
	public Object caseEOperation(EOperation eObject) {
		@SuppressWarnings("null") @NonNull EOperation eObject2 = eObject;
		if (converter.isInvariant(eObject2)) {
			Constraint pivotElement = converter.getCreated(Constraint.class, eObject2);
			if (pivotElement != null) {
				EAnnotation redefinesAnnotation = eObject2.getEAnnotation(PivotConstantsInternal.REDEFINES_ANNOTATION_SOURCE);
				if (redefinesAnnotation != null) {
					for (EObject eReference : redefinesAnnotation.getReferences()) {
						if ((eReference != null) && checkProxy(eReference)) {
							NamedElement redefinedConstraint = converter.getCreated(NamedElement.class, eReference);
							if (redefinedConstraint instanceof Constraint) {
								pivotElement.getRedefinedConstraints().add((Constraint)redefinedConstraint);
							}
						}
					}
				}
			}
			return pivotElement;
		}
		else {
			Operation pivotElement = (Operation) caseETypedElement(eObject2);
			if (pivotElement != null) {
				EAnnotation redefinesAnnotation = eObject2.getEAnnotation(PivotConstantsInternal.REDEFINES_ANNOTATION_SOURCE);
				if (redefinesAnnotation != null) {
					for (EObject eReference : redefinesAnnotation.getReferences()) {
						if ((eReference != null) && checkProxy(eReference)) {
							NamedElement redefinedOperation = converter.getCreated(NamedElement.class, eReference);
							if (redefinedOperation instanceof Operation) {
								pivotElement.getRedefinedOperations().add((Operation)redefinedOperation);
							}
						}
					}
				}
				doSwitchAll(Type.class, ClassUtil.<Type>nullFree(pivotElement.getRaisedExceptions()), eObject2.getEGenericExceptions());
			}
			return pivotElement;
		}
	}

	@Override
	public Object caseEReference(EReference eObject) {
		//		Property pivotElement = converter.getCreated(Property.class, eObject);
		Property pivotElement = caseEStructuralFeature(eObject);
		doSwitchAll(Property.class, ClassUtil.<Property>nullFree(pivotElement.getKeys()), eObject.getEKeys());
		Property oppositeProperty = null;
		EReference eOpposite = eObject.getEOpposite();
		if (eOpposite != null) {
			oppositeProperty = converter.getCreated(Property.class, eOpposite);
		}
		else {
			EAnnotation oppositeRole = eObject.getEAnnotation(EMOFExtendedMetaData.EMOF_PACKAGE_NS_URI_2_0);
			if (oppositeRole != null) {
				EMap<String, String> details = oppositeRole.getDetails();
				String oppositeName = details.get(PROPERTY_OPPOSITE_ROLE_NAME_KEY);
				if (oppositeName != null) {
					oppositeProperty = PivotFactory.eINSTANCE.createProperty();
					oppositeProperty.setName(oppositeName);
					oppositeProperty.setIsImplicit(true);
					org.eclipse.ocl.pivot.Class remoteType = (org.eclipse.ocl.pivot.Class)pivotElement.getType();	// FIXME cast
					org.eclipse.ocl.pivot.Class localType = pivotElement.getOwningClass();
					oppositeProperty.setType(localType);
					String uniqueValue = details.get(PROPERTY_OPPOSITE_ROLE_UNIQUE_KEY);
					String orderedValue = details.get(PROPERTY_OPPOSITE_ROLE_ORDERED_KEY);
					String lowerValue = details.get(PROPERTY_OPPOSITE_ROLE_LOWER_KEY);
					String upperValue = details.get(PROPERTY_OPPOSITE_ROLE_UPPER_KEY);
					boolean isOrdered = orderedValue != null ? Boolean.valueOf(orderedValue) : false;
					boolean isUnique = uniqueValue != null ? Boolean.valueOf(uniqueValue) : true;
					IntegerValue one = ValueUtil.ONE_VALUE;
					IntegerValue lower = lowerValue != null ? ValueUtil.integerValueOf(lowerValue) : one;
					if (lower.isInvalid()) {
						logger.error("Invalid " + PROPERTY_OPPOSITE_ROLE_LOWER_KEY + " " + lower);
						lower = one;
					}
					UnlimitedNaturalValue unlimitedOne = ValueUtil.UNLIMITED_ONE_VALUE;
					UnlimitedNaturalValue upper = upperValue != null ? ValueUtil.unlimitedNaturalValueOf(upperValue) : unlimitedOne;
					if (upper.isInvalid()) {
						logger.error("Invalid " + PROPERTY_OPPOSITE_ROLE_UPPER_KEY + " " + upper);
						upper = unlimitedOne;
					}
					if (localType == null) {
						localType = metamodelManager.getStandardLibrary().getOclInvalidType();
					}
					if (upper != one) {
						oppositeProperty.setType(metamodelManager.getCollectionType(isOrdered, isUnique, localType, false, lower, upper));
						oppositeProperty.setIsRequired(true);
					}
					else {
						oppositeProperty.setType(localType);
						oppositeProperty.setIsRequired(lower == one);
					}
					remoteType.getOwnedProperties().add(oppositeProperty);
					oppositeProperty.setOpposite(pivotElement);
				}
			}
			else {
				oppositeRole = eObject.getEAnnotation(EMOFExtendedMetaData.EMOF_PROPERTY_OPPOSITE_ROLE_NAME_ANNOTATION_SOURCE);
				if (oppositeRole != null) {
					EMap<String, String> details = oppositeRole.getDetails();
					String oppositeName = details.get(EMOFExtendedMetaData.EMOF_COMMENT_BODY);
					if (oppositeName != null) {
						EObject eContainer = pivotElement.eContainer();
						if (eContainer instanceof Type) {
							Type localType = (Type)eContainer;
							org.eclipse.ocl.pivot.Class remoteType = (org.eclipse.ocl.pivot.Class)pivotElement.getType();	// FIXME cast
							while (remoteType instanceof CollectionType) {
								remoteType = (org.eclipse.ocl.pivot.Class)((CollectionType)remoteType).getElementType();	// FIXME cast
							}
							if (remoteType != null) {
								oppositeProperty = PivotFactory.eINSTANCE.createProperty();
								oppositeProperty.setName(oppositeName);
								oppositeProperty.setIsImplicit(true);
								Model thisModel = PivotUtil.getContainingModel(localType);
								assert thisModel != null;
								org.eclipse.ocl.pivot.Class thisRemoteType = metamodelManager.getEquivalentClass(thisModel, remoteType);
								//
								String lowerValue = details.get("lower");
								IntegerValue lower = lowerValue != null ? ValueUtil.integerValueOf(lowerValue) :  PivotConstantsInternal.ANNOTATED_IMPLICIT_OPPOSITE_LOWER_VALUE;
								if (lower.isInvalid()) {
									logger.error("Invalid " + PROPERTY_OPPOSITE_ROLE_LOWER_KEY + " " + lower);
									lower = PivotConstantsInternal.ANNOTATED_IMPLICIT_OPPOSITE_LOWER_VALUE;
								}
								String upperValue = details.get("upper");
								UnlimitedNaturalValue upper = upperValue != null ? ValueUtil.unlimitedNaturalValueOf(upperValue) : PivotConstantsInternal.ANNOTATED_IMPLICIT_OPPOSITE_UPPER_VALUE;
								if (upper.isInvalid()) {
									logger.error("Invalid " + PROPERTY_OPPOSITE_ROLE_UPPER_KEY + " " + upper);
									upper = PivotConstantsInternal.ANNOTATED_IMPLICIT_OPPOSITE_UPPER_VALUE;
								}
								if (!upper.equals(ValueUtil.ONE_VALUE)) {
									String uniqueValue = details.get("unique");
									boolean isUnique = uniqueValue != null ? Boolean.valueOf(uniqueValue) : PivotConstantsInternal.ANNOTATED_IMPLICIT_OPPOSITE_UNIQUE;
									String orderedValue = details.get("ordered");
									boolean isOrdered = orderedValue != null ? Boolean.valueOf(orderedValue) : PivotConstantsInternal.ANNOTATED_IMPLICIT_OPPOSITE_ORDERED;
									oppositeProperty.setType(metamodelManager.getCollectionType(isOrdered, isUnique, localType, false, lower, upper));
									oppositeProperty.setIsRequired(true);
								}
								else {
									oppositeProperty.setType(localType);
									oppositeProperty.setIsRequired(lower.equals(ValueUtil.ONE_VALUE));
								}
								thisRemoteType.getOwnedProperties().add(oppositeProperty);
								oppositeProperty.setOpposite(pivotElement);
							}
						}
					}
				}
			}
		}
		if (oppositeProperty != null) {
			pivotElement.setOpposite(oppositeProperty);
		}
		//		else if (eObject.eContainer() instanceof EClass) {		// Skip annotation references
		//			metamodelManager.installPropertyDeclaration(pivotElement);
		//		}
		return null;
	}

	@Override
	public Property caseEStructuralFeature(EStructuralFeature eObject) {
		@SuppressWarnings("null")@NonNull EStructuralFeature eObject2 = eObject;
		Property pivotElement = (Property) caseETypedElement(eObject2);
		if (pivotElement != null) {
			EAnnotation redefinesAnnotation = eObject2.getEAnnotation(PivotConstantsInternal.REDEFINES_ANNOTATION_SOURCE);
			if (redefinesAnnotation != null) {
				for (EObject eReference : redefinesAnnotation.getReferences()) {
					if ((eReference != null) && checkProxy(eReference)) {
						Property redefinedProperty = converter.getCreated(Property.class, eReference);
						pivotElement.getRedefinedProperties().add(redefinedProperty);
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
							Type oldType = pivotElement.getType();
							if (oldType instanceof CollectionType) {
								CollectionType oldCollectionType = (CollectionType)oldType;
								oldType = oldCollectionType.getElementType();
								oldLowerValue = oldCollectionType.getLowerValue();
								oldOrdered = oldCollectionType.isOrdered();
								oldUnique = oldCollectionType.isUnique();
								oldUpperValue = oldCollectionType.getUpperValue();
							}
							else {
								oldLowerValue = pivotElement.isIsRequired() ? ValueUtil.ONE_VALUE : ValueUtil.ZERO_VALUE;
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
								if (upperValue.equals(ValueUtil.ONE_VALUE)) {
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
							pivotElement.setType(pivotType);
							pivotElement.setIsRequired(isRequired);
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
			pivotElement.setDefaultValueString(defaultValueLiteral);
		}
		return pivotElement;
	}

	@Override
	public TypedElement caseETypedElement(ETypedElement eObject) {
		@SuppressWarnings("null") @NonNull ETypedElement eObject2 = eObject;
		TypedElement pivotElement = converter.getCreated(TypedElement.class, eObject2);
		if (pivotElement != null) {
			boolean isRequired;
			Type pivotType;
			EGenericType eType = eObject2.getEGenericType();
			if (eType != null) {
				pivotType = converter.getASType(eType);
				int lower = eObject.getLowerBound();
				int upper = eObject.getUpperBound();
				if (upper == 1) {
					if (lower == 0) {
						if (converter.cannotBeOptional(eObject)) {
							lower = 1;
							Ecore2AS.NOT_OPTIONAL.println(NameUtil.qualifiedNameFor(eObject) + " converted to not-optional");
						}
						else {
							EClassifier eClassifier = eType.getEClassifier();
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
					boolean isNullFree = Ecore2AS.isNullFree(eObject);
					boolean isOrdered = eObject.isOrdered();
					boolean isUnique = eObject.isUnique();
					if (pivotType != null) {
						IntegerValue lowerValue = ValueUtil.integerValueOf(lower);
						UnlimitedNaturalValue upperValue = upper != -1 ? ValueUtil.unlimitedNaturalValueOf(upper) : ValueUtil.UNLIMITED_VALUE;
						pivotType = metamodelManager.getCollectionType(isOrdered, isUnique, pivotType, isNullFree, lowerValue, upperValue);
					}
				}
			}
			else {
				isRequired = false;
				pivotType = standardLibrary.getOclVoidType();
			}
			pivotElement.setType(pivotType);
			pivotElement.setIsRequired(isRequired);
		}
		return pivotElement;
	}

	@Override
	public Object caseETypeParameter(ETypeParameter eObject) {
		@SuppressWarnings("null") @NonNull ETypeParameter eObject2 = eObject;
		TemplateParameter pivotElement = converter.getCreated(TemplateParameter.class, eObject2);
		if (pivotElement != null) {
			doSwitchAll(org.eclipse.ocl.pivot.Class.class, ClassUtil.<org.eclipse.ocl.pivot.Class>nullFree(pivotElement.getConstrainingClasses()), eObject2.getEBounds());
		}
		return null;
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
}