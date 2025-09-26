/*******************************************************************************
 * Copyright (c) 2010, 2025 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *     E.D.Willink (CEA LIST) - Bug 388493, 399252, 399378
 *******************************************************************************/
package org.eclipse.ocl.pivot.uml.internal.es2as;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.FeatureMap.Entry;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.AssociationClass;
import org.eclipse.ocl.pivot.BooleanType;
import org.eclipse.ocl.pivot.Comment;
import org.eclipse.ocl.pivot.CompleteStandardLibrary;
import org.eclipse.ocl.pivot.ConnectionPointReference;
import org.eclipse.ocl.pivot.Constraint;
import org.eclipse.ocl.pivot.DataType;
import org.eclipse.ocl.pivot.DynamicBehavior;
import org.eclipse.ocl.pivot.DynamicElement;
import org.eclipse.ocl.pivot.DynamicType;
import org.eclipse.ocl.pivot.DynamicValueSpecification;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.Enumeration;
import org.eclipse.ocl.pivot.EnumerationLiteral;
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.FinalState;
import org.eclipse.ocl.pivot.InstanceSpecification;
import org.eclipse.ocl.pivot.NamedElement;
import org.eclipse.ocl.pivot.Namespace;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.Parameter;
import org.eclipse.ocl.pivot.PivotFactory;
import org.eclipse.ocl.pivot.PivotPackage;
import org.eclipse.ocl.pivot.PrimitiveType;
import org.eclipse.ocl.pivot.Profile;
import org.eclipse.ocl.pivot.ProfileApplication;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.Pseudostate;
import org.eclipse.ocl.pivot.Region;
import org.eclipse.ocl.pivot.Signal;
import org.eclipse.ocl.pivot.Slot;
import org.eclipse.ocl.pivot.State;
import org.eclipse.ocl.pivot.StateMachine;
import org.eclipse.ocl.pivot.Stereotype;
import org.eclipse.ocl.pivot.StereotypeExtender;
import org.eclipse.ocl.pivot.TemplateParameter;
import org.eclipse.ocl.pivot.TemplateSignature;
import org.eclipse.ocl.pivot.TemplateableElement;
import org.eclipse.ocl.pivot.Transition;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.TypedElement;
import org.eclipse.ocl.pivot.internal.ecore.es2as.Ecore2ASDeclarationSwitch;
import org.eclipse.ocl.pivot.utilities.MetamodelManager;
import org.eclipse.ocl.pivot.values.IntegerValue;
import org.eclipse.ocl.pivot.values.RealValue;
import org.eclipse.uml2.common.util.UML2Util;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.resource.UMLResource;
import org.eclipse.uml2.uml.util.UMLSwitch;
import org.eclipse.uml2.uml.util.UMLUtil;

public class UML2ASDeclarationSwitch extends UMLSwitch<Object>
{
	@SuppressWarnings("null")
	private static final @NonNull Logger logger = Logger.getLogger(UML2ASDeclarationSwitch.class);

	protected final @NonNull Ecore2ASDeclarationSwitch ecoreSwitch;
	protected final @NonNull UML2AS converter;
	protected final @NonNull MetamodelManager metamodelManager;
	protected final @NonNull CompleteStandardLibrary standardLibrary;

	public UML2ASDeclarationSwitch(@NonNull UML2AS converter) {
		this.converter = converter;
		this.ecoreSwitch = new Ecore2ASDeclarationSwitch(converter);
		this.metamodelManager = converter.getMetamodelManager();
		this.standardLibrary = converter.getStandardLibrary();
	}

	@Override
	public Object caseAssociation(org.eclipse.uml2.uml.Association umlAssociation) {
		assert umlAssociation != null;
		//
		//	It would be nice to create Pivot AssociationClass instances lazily, but UML defines these objects and it is hard
		//	to be lazy; the proxies could be nearly as costly as their truths.
		//
		AssociationClass asAssociationClass = converter.refreshElement(org.eclipse.ocl.pivot.AssociationClass.class, PivotPackage.Literals.ASSOCIATION_CLASS, umlAssociation);
		//		System.out.println("Association " + umlAssociation.getName() + ", " + NameUtil.debugSimpleName(umlAssociation) + " => " + NameUtil.debugSimpleName(asAssociationClass));
		copyNamedElement(asAssociationClass, umlAssociation);
		converter.queueReference(umlAssociation);			// properties, superClasses
		converter.queueUse(umlAssociation);					// redefinitions, constraints
		return asAssociationClass;
	}

	@Override
	public Object caseAssociationClass(org.eclipse.uml2.uml.AssociationClass umlAssociationClass) {
		assert umlAssociationClass != null;
		AssociationClass asAssociationClass = converter.refreshElement(AssociationClass.class, PivotPackage.Literals.ASSOCIATION_CLASS, umlAssociationClass);
		//		System.out.println("AssociationClass " + umlAssociationClass.getName() + " => " + NameUtil.debugSimpleName(asAssociationClass));
		copyClass(asAssociationClass, umlAssociationClass);
		converter.queueReference(umlAssociationClass);		// properties, superClasses
		converter.queueUse(umlAssociationClass);			// redefinitions, constraints
		return asAssociationClass;
	}

	@Override
	public Object caseBehavior(org.eclipse.uml2.uml.Behavior umlBehavior) {
		assert umlBehavior != null;
		DynamicBehavior pivotElement = converter.refreshElement(DynamicBehavior.class, PivotPackage.Literals.DYNAMIC_BEHAVIOR, umlBehavior);
		pivotElement.setName(umlBehavior.getName());
		converter.setOriginalMapping(pivotElement, umlBehavior);
		doSwitchAll(pivotElement.getOwnedAnnotations(), umlBehavior.getOwnedElements(), null);
		EClass umlMetaClass = umlBehavior.eClass();
		Type metaType = metamodelManager.getASOfEcore(Type.class, umlMetaClass);
		pivotElement.setMetaType(metaType);
		return pivotElement;
	}

	@Override
	public org.eclipse.ocl.pivot.Class caseClass(org.eclipse.uml2.uml.Class umlClass) {
		assert umlClass != null;
		org.eclipse.ocl.pivot.Class pivotElement = converter.refreshElement(org.eclipse.ocl.pivot.Class.class, PivotPackage.Literals.CLASS, umlClass);
		//		System.out.println("Class " + umlClass.getName() + " => " + ClassUtil.debugSimpleName(pivotElement));
		copyClass(pivotElement, umlClass);
		converter.queueReference(umlClass);		// superClasses
		return pivotElement;
	}

	@Override
	public Object caseClassifierTemplateParameter(org.eclipse.uml2.uml.ClassifierTemplateParameter umlTemplateParameter) {
		assert umlTemplateParameter != null;
		@SuppressWarnings("null") org.eclipse.uml2.uml.@NonNull Type umlParameterType = (org.eclipse.uml2.uml.Type)umlTemplateParameter.getParameteredElement();
		TemplateParameter pivotElement = converter.refreshElement(TemplateParameter.class, PivotPackage.Literals.TEMPLATE_PARAMETER, umlTemplateParameter);
		//		TemplateParameter pivotTemplateParameter = converter.refreshNamedElement(org.eclipse.ocl.pivot.Class.class, PivotPackage.Literals.CLASS, umlTemplateParameter);
		//		setOriginalMapping(pivotElement, umlTemplateParameter);
		//		String name = umlTemplateParameter.getName();
		pivotElement.setName(umlParameterType.getName());
		//		TemplateParameter templateParameter = pivotElement.isTemplateParameter();
		//		if (templateParameter == null) {
		//			templateParameter = PivotFactory.eINSTANCE.createTemplateParameter();
		//			templateParameter.setOwnedParameteredElement(pivotElement);
		//			converter.setOriginalMapping(templateParameter, umlTemplateParameter);
		//		}
		converter.setOriginalMapping(pivotElement, umlTemplateParameter);
		//		List<EGenericType> eBounds = umlTemplateParameter.getEBounds();
		//		if (!eBounds.isEmpty()) {
		//			doSwitchAll(eBounds);
		//			converter.queueReference(umlTemplateParameter);
		//		}
		if (!umlTemplateParameter.getConstrainingClassifiers().isEmpty()) {
			converter.queueReference(umlTemplateParameter);
		}
		return pivotElement;
	}

	@Override
	public Comment caseComment(org.eclipse.uml2.uml.Comment umlComment) {
		assert umlComment != null;
		Comment pivotElement = converter.refreshElement(Comment.class, PivotPackage.Literals.COMMENT, umlComment);
		pivotElement.setBody(umlComment.getBody());
		copyComments(pivotElement, umlComment);
		return pivotElement;
	}

	@Override
	public ConnectionPointReference caseConnectionPointReference(org.eclipse.uml2.uml.ConnectionPointReference umlConnectionPointReference) {
		assert umlConnectionPointReference != null;
		ConnectionPointReference pivotElement = converter.refreshElement(ConnectionPointReference.class, PivotPackage.Literals.CONNECTION_POINT_REFERENCE, umlConnectionPointReference);
		copyNamedElement(pivotElement, umlConnectionPointReference);
		return pivotElement;
	}

	@Override
	public Constraint caseConstraint(org.eclipse.uml2.uml.Constraint umlConstraint) {
		assert umlConstraint != null;
		Constraint pivotElement = converter.refreshElement(Constraint.class, PivotPackage.Literals.CONSTRAINT, umlConstraint);
		copyNamedElement(pivotElement, umlConstraint);
		converter.queueUse(umlConstraint);
		return pivotElement;
	}

	@Override
	public DataType caseDataType(org.eclipse.uml2.uml.DataType umlDataType) {
		assert umlDataType != null;
		DataType pivotElement = converter.refreshElement(DataType.class, PivotPackage.Literals.DATA_TYPE, umlDataType);
		copyDataTypeOrEnum(pivotElement, umlDataType);
		@SuppressWarnings("null") @NonNull List<org.eclipse.uml2.uml.Property> umlAttributes = umlDataType.getAttributes();
		doSwitchAll(umlAttributes);
		return pivotElement;
	}

	@Override
	public Enumeration caseEnumeration(org.eclipse.uml2.uml.Enumeration umlEnumeration) {
		assert umlEnumeration != null;
		Enumeration pivotElement = converter.refreshElement(Enumeration.class, PivotPackage.Literals.ENUMERATION, umlEnumeration);
		copyDataTypeOrEnum(pivotElement, umlEnumeration);
		doSwitchAll(pivotElement.getOwnedLiterals(), umlEnumeration.getOwnedLiterals(), null);
		return pivotElement;
	}

	@Override
	public EnumerationLiteral caseEnumerationLiteral(org.eclipse.uml2.uml.EnumerationLiteral umlEnumLiteral) {
		assert umlEnumLiteral != null;
		EnumerationLiteral pivotElement = converter.refreshElement(EnumerationLiteral.class,
			PivotPackage.Literals.ENUMERATION_LITERAL, umlEnumLiteral);
		copyNamedElement(pivotElement, umlEnumLiteral);
		//		if (eEnumLiteral.eIsSet(EcorePackage.Literals.EENUM_LITERAL__VALUE)) {
		//			pivotElement.setValue(BigInteger.valueOf(eEnumLiteral.getValue()));
		//		}
		//		else {
		//			pivotElement.eUnset(PivotPackage.Literals.ENUMERATION_LITERAL__VALUE);
		//		}
		//			String literal = basicGet(eObject, EcorePackage.Literals.EENUM_LITERAL__LITERAL, String.class);
		//			Enumerator instance = eEnumLiteral.getInstance();
		//			if (literal != null) {
		/*				AnnotationCS csAnnotation = PivotFactory.eINSTANCE.createAnnotationCS();
				csAnnotation.setIdSource(EcorePackage.eNS_URI);
				DetailCS csDetail = PivotFactory.eINSTANCE.createDetailCS();
				csDetail.setIdName("literal");
				copyDetailLines(csDetail.getValue(), literal);
				csAnnotation.getDetails().add(csDetail);
				pivotElement.getAnnotations().add(csAnnotation); */
		//			}
		return pivotElement;
	}

	@Override
	public Object caseExtension(org.eclipse.uml2.uml.Extension umlExtension) {
		assert umlExtension != null;
		/* Redundant while NsURI set at start of convert
		org.eclipse.uml2.uml.Class metaclass = umlExtension.getMetaclass();
		if (metaclass != null) {
			org.eclipse.uml2.uml.Package metapackage = metaclass.getPackage();
			if (metapackage != null) {
				String metaNsURI = metapackage.getURI();
				if (metaNsURI != null) {
					metamodelManager.setMetamodelNsURI(metaNsURI);
				}
			}
		} */
		StereotypeExtender asTypeExtension = converter.refreshElement(StereotypeExtender.class, PivotPackage.Literals.STEREOTYPE_EXTENDER, umlExtension);
		converter.copyModelElement(asTypeExtension, umlExtension);
		//		converter.queueReference(umlProfileApplication);
		//		converter.addProfileApplication(umlProfileApplication);
		boolean isRequired = false;
		for (org.eclipse.uml2.uml.Property umlProperty : umlExtension.getOwnedEnds()) {
			@SuppressWarnings("unused")Object eObject = doSwitch(umlProperty);
			int lower = umlProperty.getLower();
			if (lower == 1) {
				isRequired = false;
			}
		}
		asTypeExtension.setIsRequired(isRequired);
		org.eclipse.uml2.uml.Class umlMetaclass = umlExtension.getMetaclass();
		if (umlMetaclass != null) {
			org.eclipse.uml2.uml.Package umlMetapackage = umlMetaclass.getPackage();
			if (umlMetapackage != null) {
				converter.addImportedPackage(umlMetapackage);
			}
		}
		converter.queueReference(umlExtension);		// stereotype, type, opposites
		return asTypeExtension;
	}

	/*	@Override
	public Object caseExtensionEnd(ExtensionEnd umlExtensionEnd) {
		int lower = umlExtensionEnd.getLower();
		if (lower == 1) {
//			converter.addRequiredExtensionEnd(umlExtensionEnd);
		}
		return this;
	} */

	@Override
	public FinalState caseFinalState(org.eclipse.uml2.uml.FinalState umlState) {
		assert umlState != null;
		FinalState pivotElement = converter.refreshElement(FinalState.class, PivotPackage.Literals.FINAL_STATE, umlState);
		copyState(pivotElement, umlState);
		return pivotElement;
	}

	@Override
	public InstanceSpecification caseInstanceSpecification(org.eclipse.uml2.uml.InstanceSpecification umlInstanceSpecification) {
		assert umlInstanceSpecification != null;
		InstanceSpecification pivotElement = converter.refreshElement(InstanceSpecification.class, PivotPackage.Literals.INSTANCE_SPECIFICATION, umlInstanceSpecification);
		doSwitchAll(pivotElement.getOwnedSlots(), umlInstanceSpecification.getSlots(), null);
		//		converter.queueReference(umlInstanceSpecification);		// classifiers
		return pivotElement;
	}

	@Override
	public org.eclipse.ocl.pivot.Class caseInterface(org.eclipse.uml2.uml.Interface umlInterface) {
		assert umlInterface != null;
		org.eclipse.ocl.pivot.Class pivotElement = converter.refreshElement(org.eclipse.ocl.pivot.Class.class, PivotPackage.Literals.CLASS, umlInterface);
		pivotElement.setIsInterface(true);
		copyClassOrInterface(pivotElement, umlInterface);
		return pivotElement;
	}

	@Override
	public Object caseOpaqueExpression(org.eclipse.uml2.uml.OpaqueExpression umlExpression) {
		assert umlExpression != null;
		if (umlExpression.eContainer() instanceof org.eclipse.uml2.uml.Constraint) {
			converter.queueUse(umlExpression);
			return this;
		}
		else {
			ExpressionInOCL pivotElement = converter.refreshOpaqueExpression(umlExpression);
			return pivotElement;
		}
	}

	@Override
	public Operation caseOperation(org.eclipse.uml2.uml.Operation umlOperation) {
		assert umlOperation != null;
		Operation pivotElement = converter.refreshElement(Operation.class, PivotPackage.Literals.OPERATION, umlOperation);
		copyNamedElement(pivotElement, umlOperation);
		pivotElement.setIsStatic(umlOperation.isStatic());
		for (org.eclipse.uml2.uml.Parameter umlParameter : umlOperation.getOwnedParameters()) {
			org.eclipse.uml2.uml.ParameterDirectionKind direction = umlParameter.getDirection();
			if (direction == org.eclipse.uml2.uml.ParameterDirectionKind.IN_LITERAL) {
				Parameter pivotObject = (Parameter) doSwitch(umlParameter);
				if (pivotObject != null) {
					pivotElement.getOwnedParameters().add(pivotObject);
				}
			}
		}
		copyTemplateSignature(pivotElement, umlOperation.getOwnedTemplateSignature());
		//		doSwitchAll(umlOperation.getEGenericExceptions());
		converter.queueUse(umlOperation);				// For exceptions
		return pivotElement;
	}

	@Override
	public org.eclipse.ocl.pivot.Package casePackage(org.eclipse.uml2.uml.Package umlPackage) {
		assert umlPackage != null;
		org.eclipse.ocl.pivot.Package pivotElement = converter.refreshElement(org.eclipse.ocl.pivot.Package.class, PivotPackage.Literals.PACKAGE, umlPackage);
		copyPackage(pivotElement, umlPackage);
		//		if (pivotElement.getName() == null) {
		//			pivotElement.setName("anon_" + Integer.toHexString(System.identityHashCode(umlPackage)));
		//			logger.error("Anonymous package named as '" + pivotElement.getName() + "'");
		//		}
		return pivotElement;
	}

	@Override
	public Parameter caseParameter(org.eclipse.uml2.uml.Parameter eObject) {
		assert eObject != null;
		Parameter pivotElement = converter.refreshElement(Parameter.class, PivotPackage.Literals.PARAMETER, eObject);
		copyTypedElement(pivotElement, eObject, null);
		return pivotElement;
	}

	@Override
	public DataType casePrimitiveType(org.eclipse.uml2.uml.PrimitiveType umlPrimitiveType) {
		assert umlPrimitiveType != null;
		PrimitiveType asPrimitiveType = converter.getPrimitiveTypeByName(umlPrimitiveType);
		if (asPrimitiveType == null) {
			asPrimitiveType = converter.getPrimitiveTypeByOCLStereotype(umlPrimitiveType);
		}
		org.eclipse.uml2.uml.Stereotype ecoreStereotype = null;
		DataType pivotElement;
		if (asPrimitiveType == standardLibrary.getBooleanType()) {
			pivotElement = converter.refreshElement(BooleanType.class, PivotPackage.Literals.BOOLEAN_TYPE, umlPrimitiveType);
		}
		else if (asPrimitiveType == standardLibrary.getIntegerType()) {
			pivotElement = converter.refreshElement(PrimitiveType.class, PivotPackage.Literals.PRIMITIVE_TYPE, umlPrimitiveType);
		}
		else if (asPrimitiveType == standardLibrary.getRealType()) {
			pivotElement = converter.refreshElement(PrimitiveType.class, PivotPackage.Literals.PRIMITIVE_TYPE, umlPrimitiveType);
		}
		else if (asPrimitiveType == standardLibrary.getStringType()) {
			pivotElement = converter.refreshElement(PrimitiveType.class, PivotPackage.Literals.PRIMITIVE_TYPE, umlPrimitiveType);
		}
		else if (asPrimitiveType == standardLibrary.getUnlimitedNaturalType()) {
			pivotElement = converter.refreshElement(PrimitiveType.class, PivotPackage.Literals.PRIMITIVE_TYPE, umlPrimitiveType);
		}
		else {
			ecoreStereotype = umlPrimitiveType.getAppliedStereotype("Ecore::EDataType");
			if (ecoreStereotype == null) {
				ecoreStereotype = umlPrimitiveType.getAppliedStereotype("Ecore::EClassifier");
			}
			if (ecoreStereotype != null) {
				pivotElement = converter.refreshElement(DataType.class, PivotPackage.Literals.DATA_TYPE, umlPrimitiveType);
			}
			else {
				pivotElement = converter.refreshElement(PrimitiveType.class, PivotPackage.Literals.PRIMITIVE_TYPE, umlPrimitiveType);
			}
		}
		copyClassifier(pivotElement, umlPrimitiveType);
		String instanceClassName = null;
		if (ecoreStereotype != null) {
			Object object = umlPrimitiveType.getValue(ecoreStereotype, "instanceClassName");
			if (object instanceof String) {
				instanceClassName = (String) object;
			}
		}
		pivotElement.setInstanceClassName(instanceClassName);
		converter.queueReference(umlPrimitiveType);				// behavioralClss /  superClasses
		return pivotElement;
	}

	@Override
	public Object caseProfile(org.eclipse.uml2.uml.Profile umlProfile) {
		assert umlProfile != null;
		Profile pivotElement = converter.refreshElement(Profile.class, PivotPackage.Literals.PROFILE, umlProfile);
		copyPackage(pivotElement, umlProfile);
		return pivotElement;
	}

	@Override
	public Object caseProfileApplication(org.eclipse.uml2.uml.ProfileApplication umlProfileApplication) {
		assert umlProfileApplication != null;
		ProfileApplication pivotElement = converter.refreshElement(ProfileApplication.class, PivotPackage.Literals.PROFILE_APPLICATION, umlProfileApplication);
		converter.copyModelElement(pivotElement, umlProfileApplication);
		converter.queueReference(umlProfileApplication);		// Resolve Profile reference
		return pivotElement;
	}

	@Override
	public Object caseProperty(org.eclipse.uml2.uml.Property umlProperty) {
		assert umlProperty != null;
		org.eclipse.uml2.uml.Association umlAssociation = umlProperty.getAssociation();
		if (umlAssociation != null) {
			if (umlAssociation instanceof org.eclipse.uml2.uml.Extension) {
				// FIXME regularize by handling Extension properties as part of the Extension
			}
			else {
				return this;		// Association Properties are handled by the Association
			}
		}
		//		if ((umlProperty.getName() != null) && umlProperty.getName().startsWith(UML2AS.STEREOTYPE_BASE_PREFIX)) {
		//			System.out.println("Got it");
		//		}
		Property pivotElement = converter.refreshElement(Property.class, PivotPackage.Literals.PROPERTY, umlProperty);
		//		System.out.println("Property " + ((org.eclipse.uml2.uml.NamedElement)umlProperty.eContainer()).getName() + "::" + umlProperty.getName() + " => " + ClassUtil.debugSimpleName(pivotElement));
		copyProperty(pivotElement, umlProperty, null);
		// NB MDT/UML2's base_XXX/extension_YYY are spurious composites
		@SuppressWarnings("unused")org.eclipse.uml2.uml.Element owner = umlProperty.getOwner();
		boolean isComposer = true; //(owner instanceof org.eclipse.uml2.uml.Classifier) && !(owner instanceof org.eclipse.uml2.uml.Association);
		pivotElement.setIsComposite(isComposer && umlProperty.isComposite());
		pivotElement.setIsImplicit(!isComposer);
		//		pivotElement.setIsID(umlProperty.isID());
		//		pivotElement.setIsResolveProxies(umlProperty.isResolveProxies());
		pivotElement.setIsStatic(umlProperty.isStatic());
		//		converter.addProperty(umlProperty);
		converter.queueReference(umlProperty);	// type
		converter.queueUse(umlProperty);	// Defer
		return pivotElement;
	}

	@Override
	public Pseudostate casePseudostate(org.eclipse.uml2.uml.Pseudostate umlPseudostate) {
		assert umlPseudostate != null;
		Pseudostate pivotElement = converter.refreshElement(Pseudostate.class, PivotPackage.Literals.PSEUDOSTATE, umlPseudostate);
		copyNamedElement(pivotElement, umlPseudostate);
		return pivotElement;
	}

	@Override
	public Region caseRegion(org.eclipse.uml2.uml.Region umlRegion) {
		assert umlRegion != null;
		Region pivotElement = converter.refreshElement(Region.class, PivotPackage.Literals.REGION, umlRegion);
		copyNamespace(pivotElement, umlRegion);
		doSwitchAll(pivotElement.getOwnedSubvertexes(), umlRegion.getSubvertices(), null);
		doSwitchAll(pivotElement.getOwnedTransitions(), umlRegion.getTransitions(), null);
		return pivotElement;
	}

	/**
	 * @since 1.1
	 */
	@Override
	public Signal caseSignal(org.eclipse.uml2.uml.Signal umlSignal) {
		assert umlSignal != null;
		Signal pivotElement = converter.refreshElement(Signal.class, PivotPackage.Literals.SIGNAL, umlSignal);
		copyClassOrInterface(pivotElement, umlSignal);
		converter.queueReference(umlSignal);		// superClasses
		return pivotElement;
	}

	@Override
	public Slot caseSlot(org.eclipse.uml2.uml.Slot umlSlot) {
		assert umlSlot != null;
		Slot pivotElement = converter.refreshElement(Slot.class, PivotPackage.Literals.SLOT, umlSlot);
		doSwitchAll(pivotElement.getOwnedValues(), umlSlot.getValues(), null);
		converter.setOriginalMapping(pivotElement, umlSlot);
		converter.queueReference(umlSlot);		// definingFeature
		return pivotElement;
	}

	@Override
	public State caseState(org.eclipse.uml2.uml.State umlState) {
		assert umlState != null;
		State pivotElement = converter.refreshElement(State.class, PivotPackage.Literals.STATE, umlState);
		copyState(pivotElement, umlState);
		return pivotElement;
	}

	@Override
	public StateMachine caseStateMachine(org.eclipse.uml2.uml.StateMachine umlStateMachine) {
		assert umlStateMachine != null;
		StateMachine pivotElement = converter.refreshElement(StateMachine.class, PivotPackage.Literals.STATE_MACHINE, umlStateMachine);
		copyClass(pivotElement, umlStateMachine);
		doSwitchAll(pivotElement.getOwnedRegions(), umlStateMachine.getRegions(), null);
		return pivotElement;
	}

	@Override
	public Stereotype caseStereotype(org.eclipse.uml2.uml.Stereotype umlStereotype) {
		assert umlStereotype != null;
		Stereotype pivotElement = converter.refreshElement(Stereotype.class, PivotPackage.Literals.STEREOTYPE, umlStereotype);
		copyClass(pivotElement, umlStereotype);
		converter.queueReference(umlStereotype);		// superClasses
		return pivotElement;
	}

	@Override
	public Transition caseTransition(org.eclipse.uml2.uml.Transition umlTransition) {
		assert umlTransition != null;
		Transition pivotElement = converter.refreshElement(Transition.class, PivotPackage.Literals.TRANSITION, umlTransition);
		copyNamespace(pivotElement, umlTransition);
		return pivotElement;
	}

	@Override
	public DynamicValueSpecification caseValueSpecification(org.eclipse.uml2.uml.ValueSpecification umlValueSpecification) {
		assert umlValueSpecification != null;
		DynamicValueSpecification pivotElement = converter.refreshElement(DynamicValueSpecification.class, PivotPackage.Literals.DYNAMIC_VALUE_SPECIFICATION, umlValueSpecification);
		converter.setOriginalMapping(pivotElement, umlValueSpecification);
		return pivotElement;
	}

	protected void copyAnnotatedElement(@NonNull NamedElement pivotElement,
			@NonNull EModelElement umlElement, @Nullable List<EAnnotation> excludedAnnotations) {
		List<Element> pivotAnnotations = pivotElement.getOwnedAnnotations();
		for (EAnnotation eAnnotation : umlElement.getEAnnotations()) {
			if ((excludedAnnotations == null) || !excludedAnnotations.contains(eAnnotation)) {
				Element pivotAnnotation = (Element) doSwitch(eAnnotation);
				if (pivotAnnotation != null) {
					pivotAnnotations.add(pivotAnnotation);
				}
			}
		}
	}

	protected void copyClassOrInterface(org.eclipse.ocl.pivot.@NonNull Class pivotElement, org.eclipse.uml2.uml.@NonNull Classifier umlClassifier) {
		copyClassifier(pivotElement, umlClassifier);
		pivotElement.setIsAbstract(umlClassifier.isAbstract());
		String instanceClassName = null;
		org.eclipse.uml2.uml.Stereotype ecoreStereotype = umlClassifier.getAppliedStereotype("Ecore::EClass");
		if (ecoreStereotype != null) {
			Object object = umlClassifier.getValue(ecoreStereotype, "instanceClassName");
			if (object instanceof String) {
				instanceClassName = (String) object;
			}
		}
		pivotElement.setInstanceClassName(instanceClassName);
		//		doSwitchAll(umlClass.getSuperClasses());
		@SuppressWarnings("null") @NonNull List<org.eclipse.uml2.uml.Property> umlAttributes = umlClassifier.getAttributes();
		/*		converter.addProperties(umlAttributes, new UML2AS.Predicate<>()
		{
			public boolean filter(org.eclipse.uml2.uml.@NonNull Property element) {
				if (element.getAssociation() == null) {
					doSwitch(element);
				}
				return element.getAssociation() == null;
			}
		}); */
		//		doSwitchAll(pivotElement.getOwnedAttribute(), umlClassifier.getAttributes());
		doSwitchAll(umlAttributes);
		converter.queueUse(umlClassifier);				// For superclasses
	}

	protected void copyClass(org.eclipse.ocl.pivot.@NonNull Class pivotElement, org.eclipse.uml2.uml.@NonNull Class umlClass) {
		pivotElement.setIsInterface(false);
		copyClassOrInterface(pivotElement, umlClass);
		/*		for (org.eclipse.uml2.uml.Classifier umlType : umlClass.getNestedClassifiers()) {
//			doSwitch(umlType);
			Type pivotObject = (Type) doSwitch(umlType);
			if (pivotObject != null) {
				metamodelManager.addOrphanClass(pivotObject);
			}
		} */
		//		doSwitchAll(pivotElement.getNestedClassifier(), umlClass.getNestedClassifiers(), null);
		doSwitchAll(pivotElement.getOwnedBehaviors(), umlClass.getOwnedBehaviors(), null);
		for (org.eclipse.uml2.uml.Classifier umlNestedClassifier : umlClass.getNestedClassifiers()) {
			if (umlNestedClassifier instanceof org.eclipse.uml2.uml.Association) {
				doSwitch(umlNestedClassifier);
			}
			/*				else if (umlNestedClassifier instanceof org.eclipse.uml2.uml.Behavior) {}		// Handled above
				else { // FIXME Bug 514353 Class::nestedClassifiers
					converter.error("Unsupported Class::nestedClassifiers for \"" + umlClass.getPackage().getName() + "::" + umlClass.getName() + "::" + umlNestedClassifier.getName() + "\"");
				} */
		}
	}

	protected void copyClassifier(org.eclipse.ocl.pivot.@NonNull Class pivotElement, org.eclipse.uml2.uml.@NonNull Classifier umlClassifier) {
		copyNamespace(pivotElement, umlClassifier);
		copyTemplateSignature(pivotElement, umlClassifier.getOwnedTemplateSignature());
		doSwitchAll(pivotElement.getOwnedOperations(), umlClassifier.getOperations(), null);
	}

	protected void copyComments(@NonNull Element pivotElement, org.eclipse.uml2.uml.@NonNull Element umlElement) {
		doSwitchAll(pivotElement.getOwnedComments(), umlElement.getOwnedComments(), null);
	}

	/*	protected void copyConstraints(@NonNull Namespace pivotElement, org.eclipse.uml2.uml.@NonNull Namespace umlNamespace,
			@Nullable List<org.eclipse.uml2.uml.Constraint> exclusions) {
		List<org.eclipse.uml2.uml.Constraint> ownedRules = umlNamespace.getOwnedRules();
		if ((exclusions != null) && (exclusions.size() > 0)) {
			ownedRules = new ArrayList<>(ownedRules);
			ownedRules.removeAll(exclusions);
		}
		doSwitchAll(pivotElement.getOwnedRule(), ownedRules, null);
	} */

	protected void copyDataTypeOrEnum(@NonNull DataType pivotElement, org.eclipse.uml2.uml.@NonNull DataType umlDataType) {
		copyClassifier(pivotElement, umlDataType);
		String instanceClassName = null;
		org.eclipse.uml2.uml.Stereotype ecoreStereotype = umlDataType.getAppliedStereotype("Ecore::EClass");	// Bug 453090 : UML2 does not support generalization
		if (ecoreStereotype == null) {
			ecoreStereotype = umlDataType.getAppliedStereotype("Ecore::EDataType");
		}
		if (ecoreStereotype == null) {
			ecoreStereotype = umlDataType.getAppliedStereotype("Ecore::EClassifier");
		}
		if (ecoreStereotype != null) {
			Object object = umlDataType.getValue(ecoreStereotype, "instanceClassName");
			if (object instanceof String) {
				instanceClassName = (String) object;
			}
		}
		PrimitiveType asPrimitiveType = converter.getPrimitiveTypeByOCLStereotype(umlDataType);
		if (asPrimitiveType != null) {
			if (instanceClassName == null) {
				if (asPrimitiveType == standardLibrary.getIntegerType()) {
					instanceClassName = IntegerValue.class.getName();
				}
				else {
					instanceClassName = RealValue.class.getName();
				}
			}
		}
		pivotElement.setInstanceClassName(instanceClassName);
		converter.queueReference(umlDataType);				// behavioralClss /  superClasses
	}

	/*		public void copyDetailLines(List<String> lines, String value) {
			String[] splitLines = value.split("\n");
			for (int i = 0; i < splitLines.length-1; i++) {
				lines.add(splitLines[i] + '\n');
			}
			if (splitLines.length > 0) {
				lines.add(splitLines[splitLines.length-1]);
			}
		} */

	protected void copyNamedElement(@NonNull NamedElement pivotElement, org.eclipse.uml2.uml.@NonNull NamedElement umlNamedElement) {
		converter.copyModelElement(pivotElement, umlNamedElement);
		pivotElement.setName(getName(umlNamedElement));
		copyAnnotatedElement(pivotElement, umlNamedElement, null);
		copyComments(pivotElement, umlNamedElement);
	}

	protected void copyNamespace(@NonNull Namespace pivotElement, org.eclipse.uml2.uml.@NonNull Namespace umlNamespace) {
		copyNamedElement(pivotElement, umlNamespace);
		converter.queueUse(umlNamespace);				// Defer for constraints
	}

	protected void copyPackage(org.eclipse.ocl.pivot.@NonNull Package pivotElement, org.eclipse.uml2.uml.@NonNull Package umlPackage) {
		//		EAnnotation eAnnotation = umlPackage.getEAnnotation(EcorePackage.eNS_URI);
		//		List<EAnnotation> exclusions = eAnnotation == null ? Collections.<EAnnotation>emptyList() : Collections.singletonList(eAnnotation);
		copyNamespace(pivotElement, umlPackage);
		String nsURI = umlPackage.getURI();
		Object nsPrefix = null;
		Object asURI = null;
		String packageName = umlPackage.getName();
		for (org.eclipse.uml2.uml.Package uriPackage = umlPackage; uriPackage != null; uriPackage = uriPackage.getNestingPackage()) {
			asURI = uriPackage.getURI();
			org.eclipse.uml2.uml.Stereotype ecoreStereotype = getEcoreStereotype(uriPackage, UMLUtil.STEREOTYPE__E_PACKAGE);
			if ((ecoreStereotype != null) && uriPackage.isStereotypeApplied(ecoreStereotype)) {
				nsPrefix = uriPackage.getValue(ecoreStereotype, UMLUtil.TAG_DEFINITION__NS_PREFIX);
				if (asURI == null) {
					asURI = uriPackage.getValue(ecoreStereotype, UMLUtil.TAG_DEFINITION__NS_URI);
				}
			}
			if (asURI != null) {
				packageName = uriPackage.getName();
				break;
			}
		}
		pivotElement.setNsPrefix(nsPrefix != null ? nsPrefix.toString() : null);
		pivotElement.setURI(nsURI != null ? nsURI.toString() : null);
		@Nullable List<org.eclipse.uml2.uml.Constraint> umlConstraints = null;
		@Nullable List<org.eclipse.uml2.uml.Element> umlOtherElements = null;
		@Nullable List<org.eclipse.uml2.uml.InstanceSpecification> umlInstanceSpecifications = null;
		@Nullable List<org.eclipse.uml2.uml.Package> umlNestedPackages = null;
		@Nullable List<org.eclipse.uml2.uml.Package> umlImportedPackages = null;
		@Nullable List<org.eclipse.uml2.uml.ProfileApplication> umlProfileApplications = null;
		@Nullable List<org.eclipse.uml2.uml.Type> umlTypes = null;
		@Nullable List<org.eclipse.uml2.uml.Association> umlAssociations = null;
		for (org.eclipse.uml2.uml.Element ownedElement : umlPackage.getOwnedElements()) {
			if (ownedElement instanceof org.eclipse.uml2.uml.Package) {
				if (umlNestedPackages == null) {
					umlNestedPackages = new ArrayList<>();
				}
				umlNestedPackages.add((org.eclipse.uml2.uml.Package)ownedElement);
			}
			else if (ownedElement instanceof org.eclipse.uml2.uml.PackageImport) {
				if (umlImportedPackages == null) {
					umlImportedPackages = new ArrayList<>();
				}
				umlImportedPackages.add(((org.eclipse.uml2.uml.PackageImport)ownedElement).getImportedPackage());
			}
			else if (ownedElement instanceof org.eclipse.uml2.uml.Association) {
				if (umlAssociations == null) {
					umlAssociations = new ArrayList<>();
				}
				umlAssociations.add((org.eclipse.uml2.uml.Association)ownedElement);
			}
			else if (ownedElement instanceof org.eclipse.uml2.uml.Constraint) {
				if (umlConstraints == null) {
					umlConstraints = new ArrayList<>();
				}
				umlConstraints.add((org.eclipse.uml2.uml.Constraint)ownedElement);
			}
			else if (ownedElement instanceof org.eclipse.uml2.uml.InstanceSpecification) {
				if (umlInstanceSpecifications == null) {
					umlInstanceSpecifications = new ArrayList<>();
				}
				umlInstanceSpecifications.add((org.eclipse.uml2.uml.InstanceSpecification)ownedElement);
			}
			else if (ownedElement instanceof org.eclipse.uml2.uml.Type) {
				if (umlTypes == null) {
					umlTypes = new ArrayList<>();
				}
				umlTypes.add((org.eclipse.uml2.uml.Type)ownedElement);
			}
			else if (ownedElement instanceof org.eclipse.uml2.uml.ProfileApplication) {
				if (umlProfileApplications == null) {
					umlProfileApplications = new ArrayList<>();
				}
				umlProfileApplications.add((org.eclipse.uml2.uml.ProfileApplication)ownedElement);
			}
			else if (ownedElement instanceof org.eclipse.uml2.uml.Comment) {
				//				umlComments.add((org.eclipse.uml2.uml.Comment)ownedElement);
			}
			else {
				if (umlOtherElements == null) {
					umlOtherElements = new ArrayList<>();
				}
				umlOtherElements.add(ownedElement);
			}
		}
		if (umlInstanceSpecifications != null) {
			doSwitchAll(pivotElement.getOwnedInstances(), umlInstanceSpecifications, null);
		}
		else {
			pivotElement.getOwnedInstances().clear();
		}
		if (umlNestedPackages != null) {
			doSwitchAll(pivotElement.getOwnedPackages(), umlNestedPackages, null);
		}
		else {
			pivotElement.getOwnedPackages().clear();
		}
		if (umlProfileApplications != null) {
			doSwitchAll(pivotElement.getOwnedProfileApplications(), umlProfileApplications, null);
			for (org.eclipse.uml2.uml.ProfileApplication umlProfileApplication : umlProfileApplications) {
				if (umlProfileApplication != null) {
					org.eclipse.uml2.uml.Profile appliedProfile = umlProfileApplication.getAppliedProfile();
					if (appliedProfile != null) {
						if (umlImportedPackages == null) {
							umlImportedPackages = new ArrayList<>();
						}
						umlImportedPackages.add(appliedProfile);
					}
				}
			}
		}
		else {
			pivotElement.getOwnedProfileApplications().clear();
		}
		List<org.eclipse.ocl.pivot.@NonNull Class> asClasses = new ArrayList<>();
		if (umlTypes != null) {
			doSwitchAll(asClasses, umlTypes, null);
		}
		else {
			asClasses.clear();
		}
		if (umlAssociations != null) {
			for (org.eclipse.uml2.uml.Association umlAssociation : umlAssociations) {
				Object asAssociation = doSwitch(umlAssociation);
				if (asAssociation instanceof AssociationClass) {
					asClasses.add((AssociationClass)asAssociation);
				}
			}
		}
		converter.refreshList(pivotElement.getOwnedClasses(), asClasses);
		if (umlConstraints != null) {
			doSwitchAll(pivotElement.getOwnedConstraints(), umlConstraints, null);
		}
		if (umlOtherElements != null) {
			doSwitchAll(pivotElement.getOwnedAnnotations(), umlOtherElements, null);
		}
		else {
			pivotElement.getOwnedAnnotations().clear();
		}
		if (umlImportedPackages != null) {
			converter.addImportedPackages(umlImportedPackages);
			converter.queueUse(umlPackage);	// Defer
		}
		else {
			pivotElement.getImportedPackages().clear();
		}
	}

	protected void copyProperty(@NonNull Property pivotElement, org.eclipse.uml2.uml.@NonNull Property umlProperty, List<EAnnotation> excludedAnnotations) {
		copyTypedElement(pivotElement, umlProperty, excludedAnnotations);		pivotElement.setIsReadOnly(umlProperty.isReadOnly());
		pivotElement.setIsDerived(umlProperty.isDerived());
		//		pivotElement.setIsTransient(umlProperty.isTransient());
		//		pivotElement.setIsUnsettable(umlProperty.isUnsettable());
		//		pivotElement.setIsVolatile(umlProperty.isVolatile());
		//		if (umlProperty.eIsSet(EcorePackage.Literals.ESTRUCTURAL_FEATURE__DEFAULT_VALUE_LITERAL)) {
		//			pivotElement.setDefault(eObject.getDefaultValueLiteral());
		//		}
		//		else {
		//			pivotElement.eUnset(PivotPackage.Literals.PROPERTY__DEFAULT);
		//		}
	}

	protected void copyState(@NonNull State pivotElement, org.eclipse.uml2.uml.@NonNull State umlState) {
		copyNamespace(pivotElement, umlState);
		doSwitchAll(pivotElement.getOwnedRegions(), umlState.getRegions(), null);
	}

	protected void copyTemplateSignature(TemplateableElement pivotElement, org.eclipse.uml2.uml.TemplateSignature umlTemplateSignature) {
		if (umlTemplateSignature != null) {
			List<org.eclipse.uml2.uml.TemplateParameter> umlTemplateParameters = umlTemplateSignature.getOwnedParameters();
			if (!umlTemplateParameters.isEmpty()) {
				TemplateSignature pivotTemplateSignature = PivotFactory.eINSTANCE.createTemplateSignature();
				pivotElement.setOwnedSignature(pivotTemplateSignature);
				doSwitchAll(pivotTemplateSignature.getOwnedParameters(), umlTemplateParameters, null);
			}
		}
	}

	protected void copyTypedElement(@NonNull TypedElement pivotElement, org.eclipse.uml2.uml.@NonNull TypedElement umlTypedElement, List<EAnnotation> excludedAnnotations) {
		copyNamedElement(pivotElement, umlTypedElement);
		int lower = ((org.eclipse.uml2.uml.MultiplicityElement)umlTypedElement).getLower();
		int upper = ((org.eclipse.uml2.uml.MultiplicityElement)umlTypedElement).getUpper();
		pivotElement.setIsRequired((upper == 1) && (lower == 1));
		org.eclipse.uml2.uml.Type umlType = umlTypedElement.getType();
		if (umlType != null) {
			converter.queueReference(umlTypedElement);
			Resource umlResource = umlType.eResource();
			if (umlResource != null) {
				converter.addImportedResource(umlResource);
			}
		}
	}

	@Override
	public Element defaultCase(EObject umlObject) {
		DynamicElement pivotElement;
		if (umlObject instanceof org.eclipse.uml2.uml.Type) {
			pivotElement = converter.refreshElement(DynamicType.class, PivotPackage.Literals.DYNAMIC_TYPE, (org.eclipse.uml2.uml.Type)umlObject);
			((DynamicType)pivotElement).setName(((org.eclipse.uml2.uml.Type)umlObject).getName());
		}
		else if (umlObject instanceof org.eclipse.uml2.uml.Element) {
			pivotElement = converter.refreshElement(DynamicElement.class, PivotPackage.Literals.DYNAMIC_ELEMENT, (org.eclipse.uml2.uml.Element)umlObject);
		}
		else {
			converter.error("Unsupported " + umlObject.eClass().getName() + " for UML2ASDeclarationSwitch");
			return null;
		}
		converter.setOriginalMapping(pivotElement, umlObject);
		doSwitchAll(pivotElement.getOwnedAnnotations(), ((org.eclipse.uml2.uml.Element)umlObject).getOwnedElements(), null);
		EClass umlMetaClass = umlObject.eClass();
		Type metaType = metamodelManager.getASOfEcore(Type.class, umlMetaClass);
		pivotElement.setMetaType(metaType);
		if (umlObject instanceof org.eclipse.uml2.uml.Slot) {
			converter.queueUse(umlObject);
		}
		return pivotElement;
	}

	public Object doInPackageSwitch(@NonNull EObject eObject) {
		int classifierID = eObject.eClass().getClassifierID();
		return doSwitch(classifierID, eObject);
	}

	@Override
	public Object doSwitch(EObject eObject) {
		EClass eClass = eObject.eClass();
		EPackage ePackage = eClass.getEPackage();
		if (ePackage == UMLPackage.eINSTANCE) {
			return doInPackageSwitch(eObject);
		}
		else if (ePackage == EcorePackage.eINSTANCE) {
			if (eObject.eContainer() instanceof org.eclipse.uml2.uml.Profile) {
				return null;
			}
			return ecoreSwitch.doInPackageSwitch(eObject);
		}
		else if (ePackage.getNsURI().startsWith("http://www.omg.org/spec/MOF")) {		// Should never happen (removed by CMOF2UMLResourceHandler
			if ((eObject instanceof org.eclipse.emf.ecore.xml.type.AnyType) && "Tag".equals(eClass.getName())) {
				FeatureMap anyAttribute = ((org.eclipse.emf.ecore.xml.type.AnyType)eObject).getAnyAttribute();
				Object name = null;
				Object value = null;
				Object element = null;
				for (Entry entry : anyAttribute) {
					EStructuralFeature eFeature = entry.getEStructuralFeature();
					if ("name".equals(eFeature.getName())) {
						name = anyAttribute.get(eFeature, false);
					}
					else if ("value".equals(eFeature.getName())) {
						value = anyAttribute.get(eFeature, false);
					}
					else if ("element".equals(eFeature.getName())) {
						element = anyAttribute.get(eFeature, false);
					}
				}
				boolean gotIt = false;
				EObject taggedObject = eObject.eResource().getEObject(String.valueOf(element));
				if ("org.omg.xmi.nsPrefix".equals(name) && (taggedObject instanceof org.eclipse.uml2.uml.Package)) {
					org.eclipse.ocl.pivot.Package asPackage = converter.getCreated(org.eclipse.ocl.pivot.Package.class, taggedObject);
					if (asPackage != null) {
						asPackage.setNsPrefix(String.valueOf(value));
						gotIt = true;
					}
				}
				else if ("org.omg.xmi.nsURI".equals(name) && (taggedObject instanceof org.eclipse.uml2.uml.Package)) {
					org.eclipse.ocl.pivot.Package asPackage = converter.getCreated(org.eclipse.ocl.pivot.Package.class, taggedObject);
					if (asPackage != null) {
						asPackage.setURI(String.valueOf(value));
						gotIt = true;
					}
				}
				else if ("org.omg.xmi.schemaType".equals(name) && (taggedObject instanceof org.eclipse.uml2.uml.DataType)) {
					DataType asPackage = converter.getCreated(DataType.class, taggedObject);
					if (asPackage != null) {
						// FIXME						asPackage.setNsURI(String.valueOf(value));
						System.out.println("Unknown " + ePackage.getNsURI() + "::" + eObject.eClass().getName() + "::" + name + " ignored");
						gotIt = true;
					}
				}
				if (!gotIt) {
					logger.warn("Unknown " + ePackage.getNsURI() + "::" + eObject.eClass().getName() + "::" + name + " ignored");
				}
			}
			return null;
		}
		else if (ePackage.getNsURI().startsWith("http://www.eclipse.org/uml2/schemas/Ecore")) {
			// FIXME
			return null;
		}
		else {
			converter.addStereotypeApplication(eObject);
			return null;
		}
	}

	public <T extends Element, V extends EObject> void doSwitchAll(/*@NonNull*/ Collection<T> pivotObjects, /*@NonNull*/ List<V> eObjects, UML2AS.@Nullable Predicate<V> predicate) {
		assert pivotObjects != null;
		assert eObjects != null;
		eObjects.size();
		for (V eObject : eObjects) {
			if ((eObject != null) && ((predicate == null) || predicate.filter(eObject))) {
				Object switchObject = doSwitch(eObject);
				if ((switchObject != null) && (switchObject != this)) {
					@SuppressWarnings("unchecked")
					T pivotObject = (T)switchObject;
					pivotObjects.add(pivotObject);
				}
			}
		}
	}

	public <T extends Element> void doSwitchAll(@NonNull List<? extends EObject> eObjects) {
		for (EObject eObject : eObjects) {
			doSwitch(eObject);
		}
	}

	protected org.eclipse.uml2.uml.Profile getEcoreProfile(EObject eObject) {
		Resource eResource = eObject.eResource();

		if (eResource != null) {
			ResourceSet resourceSet = eResource.getResourceSet();

			if (resourceSet != null) {
				return UML2Util.load(resourceSet, URI
					.createURI(UMLResource.ECORE_PROFILE_URI),
					UMLPackage.Literals.PROFILE);
			}
		}

		return null;
	}

	protected org.eclipse.uml2.uml.Stereotype getEcoreStereotype(EObject eObject, String name) {
		org.eclipse.uml2.uml.Profile ecoreProfile = getEcoreProfile(eObject);

		return ecoreProfile != null
				? ecoreProfile.getOwnedStereotype(name)
					: null;
	}

	/**
	 * Return the rational UML name for umlNamedElement.
	 */
	private String getName(org.eclipse.uml2.uml.@NonNull NamedElement umlNamedElement) {
		//
		//	UML models with that correspond to an Ecore model cannot import originalName annotations and so must use the
		//	Ecore model spelling. OOPS Ecore.metamodel.uml has a capitalized package name. So we must correct.
		//
		//	The code below correct the specific case of a http://www.eclipse.org/emf/2002/Ecore URI.
		//
		//	(Alternatively related cases might be detected if the Ecore::EPackage::nsURI stereotype value is null.)
		//
		if (umlNamedElement instanceof org.eclipse.uml2.uml.Model) {		// Model not Profile
			org.eclipse.uml2.uml.Model umlModel = (org.eclipse.uml2.uml.Model)umlNamedElement;
			if (EcorePackage.eNS_URI.equals(umlModel.getURI())) {
				org.eclipse.uml2.uml.Stereotype ecoreEPackageStereotype = umlNamedElement.getAppliedStereotype("Ecore::EPackage");
				if (ecoreEPackageStereotype != null) {
				//	Object nsURI = umlNamedElement.getValue(ecoreEPackageStereotype, "nsURI");
				//	if (nsURI == null) {			// Ecore.metamodel.uml
						Object packageName = umlNamedElement.getValue(ecoreEPackageStereotype, "packageName");
						if (packageName != null) {
							return (String)packageName;
						}
				//	}
				}
			}
		}
		return umlNamedElement.getName();
	}

	protected @Nullable PrimitiveType getPrimitiveTypeByEcoreStereotype(org.eclipse.uml2.uml.@NonNull Stereotype ecoreStereotype, @NonNull String instanceClassName) {
		return converter.getPrimitiveTypeByEcoreStereotype(ecoreStereotype, instanceClassName);
	}
}
