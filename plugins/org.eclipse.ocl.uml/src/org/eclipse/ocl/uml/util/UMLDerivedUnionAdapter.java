/*******************************************************************************
 * Copyright (c) 2009, 2018 Eclipse Modeling Project and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.uml.util;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.notify.impl.AdapterImpl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import org.eclipse.ocl.uml.AnyType;
import org.eclipse.ocl.uml.AssociationClassCallExp;
import org.eclipse.ocl.uml.BagType;
import org.eclipse.ocl.uml.BooleanLiteralExp;
import org.eclipse.ocl.uml.CollectionItem;
import org.eclipse.ocl.uml.CollectionLiteralExp;
import org.eclipse.ocl.uml.CollectionRange;
import org.eclipse.ocl.uml.CollectionType;
import org.eclipse.ocl.uml.ElementType;
import org.eclipse.ocl.uml.EnumLiteralExp;
import org.eclipse.ocl.uml.ExpressionInOCL;
import org.eclipse.ocl.uml.IfExp;
import org.eclipse.ocl.uml.IntegerLiteralExp;
import org.eclipse.ocl.uml.InvalidLiteralExp;
import org.eclipse.ocl.uml.InvalidType;
import org.eclipse.ocl.uml.IterateExp;
import org.eclipse.ocl.uml.IteratorExp;
import org.eclipse.ocl.uml.LetExp;
import org.eclipse.ocl.uml.MessageExp;
import org.eclipse.ocl.uml.MessageType;
import org.eclipse.ocl.uml.NullLiteralExp;
import org.eclipse.ocl.uml.OperationCallExp;
import org.eclipse.ocl.uml.OrderedSetType;
import org.eclipse.ocl.uml.PrimitiveType;
import org.eclipse.ocl.uml.PropertyCallExp;
import org.eclipse.ocl.uml.RealLiteralExp;
import org.eclipse.ocl.uml.SequenceType;
import org.eclipse.ocl.uml.SetType;
import org.eclipse.ocl.uml.StateExp;
import org.eclipse.ocl.uml.StringLiteralExp;
import org.eclipse.ocl.uml.TemplateParameterType;
import org.eclipse.ocl.uml.TupleLiteralExp;
import org.eclipse.ocl.uml.TupleLiteralPart;
import org.eclipse.ocl.uml.TupleType;
import org.eclipse.ocl.uml.TypeExp;
import org.eclipse.ocl.uml.TypeType;
import org.eclipse.ocl.uml.UMLPackage;
import org.eclipse.ocl.uml.UnlimitedNaturalLiteralExp;
import org.eclipse.ocl.uml.UnspecifiedValueExp;
import org.eclipse.ocl.uml.Variable;
import org.eclipse.ocl.uml.VariableExp;
import org.eclipse.ocl.uml.VoidType;

/**
 * <!-- begin-user-doc -->
 * An adapter that propagates notifications for derived unions.
 * @since 5.2
 * <!-- end-user-doc -->
 * @see org.eclipse.ocl.uml.UMLPackage
 * @generated
 */
public class UMLDerivedUnionAdapter
		extends AdapterImpl {

	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static UMLPackage modelPackage;

	/**
	 * Creates an instance of the adapter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UMLDerivedUnionAdapter() {
		if (modelPackage == null) {
			modelPackage = UMLPackage.eINSTANCE;
		}
	}

	/**
	 * Calls <code>notifyChanged</code> with the appropriate model class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param notification a description of the change.
	 * @generated
	 */
	public void notifyChanged(Notification notification) {
		Object notifier = notification.getNotifier();
		if (notifier instanceof EObject) {
			EClass eClass = ((EObject) notifier).eClass();
			if (eClass.eContainer() == modelPackage) {
				notifyChanged(notification, eClass);
			}
		}
	}

	/**
	 * Calls <code>notifyXXXChanged</code> for the corresponding class of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param notification a description of the change.
	 * @param eClass the Ecore class of the notifier.
	 * @generated
	 */
	protected void notifyChanged(Notification notification, EClass eClass) {
		switch (eClass.getClassifierID()) {
			case UMLPackage.ANY_TYPE :
				notifyAnyTypeChanged(notification, eClass);
				break;
			case UMLPackage.VOID_TYPE :
				notifyVoidTypeChanged(notification, eClass);
				break;
			case UMLPackage.INVALID_TYPE :
				notifyInvalidTypeChanged(notification, eClass);
				break;
			case UMLPackage.ELEMENT_TYPE :
				notifyElementTypeChanged(notification, eClass);
				break;
			case UMLPackage.TYPE_TYPE :
				notifyTypeTypeChanged(notification, eClass);
				break;
			case UMLPackage.MESSAGE_TYPE :
				notifyMessageTypeChanged(notification, eClass);
				break;
			case UMLPackage.PRIMITIVE_TYPE :
				notifyPrimitiveTypeChanged(notification, eClass);
				break;
			case UMLPackage.COLLECTION_TYPE :
				notifyCollectionTypeChanged(notification, eClass);
				break;
			case UMLPackage.TUPLE_TYPE :
				notifyTupleTypeChanged(notification, eClass);
				break;
			case UMLPackage.BAG_TYPE :
				notifyBagTypeChanged(notification, eClass);
				break;
			case UMLPackage.SET_TYPE :
				notifySetTypeChanged(notification, eClass);
				break;
			case UMLPackage.ORDERED_SET_TYPE :
				notifyOrderedSetTypeChanged(notification, eClass);
				break;
			case UMLPackage.SEQUENCE_TYPE :
				notifySequenceTypeChanged(notification, eClass);
				break;
			case UMLPackage.EXPRESSION_IN_OCL :
				notifyExpressionInOCLChanged(notification, eClass);
				break;
			case UMLPackage.ASSOCIATION_CLASS_CALL_EXP :
				notifyAssociationClassCallExpChanged(notification, eClass);
				break;
			case UMLPackage.BOOLEAN_LITERAL_EXP :
				notifyBooleanLiteralExpChanged(notification, eClass);
				break;
			case UMLPackage.COLLECTION_ITEM :
				notifyCollectionItemChanged(notification, eClass);
				break;
			case UMLPackage.COLLECTION_LITERAL_EXP :
				notifyCollectionLiteralExpChanged(notification, eClass);
				break;
			case UMLPackage.COLLECTION_RANGE :
				notifyCollectionRangeChanged(notification, eClass);
				break;
			case UMLPackage.ENUM_LITERAL_EXP :
				notifyEnumLiteralExpChanged(notification, eClass);
				break;
			case UMLPackage.IF_EXP :
				notifyIfExpChanged(notification, eClass);
				break;
			case UMLPackage.INTEGER_LITERAL_EXP :
				notifyIntegerLiteralExpChanged(notification, eClass);
				break;
			case UMLPackage.UNLIMITED_NATURAL_LITERAL_EXP :
				notifyUnlimitedNaturalLiteralExpChanged(notification, eClass);
				break;
			case UMLPackage.INVALID_LITERAL_EXP :
				notifyInvalidLiteralExpChanged(notification, eClass);
				break;
			case UMLPackage.ITERATE_EXP :
				notifyIterateExpChanged(notification, eClass);
				break;
			case UMLPackage.ITERATOR_EXP :
				notifyIteratorExpChanged(notification, eClass);
				break;
			case UMLPackage.LET_EXP :
				notifyLetExpChanged(notification, eClass);
				break;
			case UMLPackage.MESSAGE_EXP :
				notifyMessageExpChanged(notification, eClass);
				break;
			case UMLPackage.NULL_LITERAL_EXP :
				notifyNullLiteralExpChanged(notification, eClass);
				break;
			case UMLPackage.OPERATION_CALL_EXP :
				notifyOperationCallExpChanged(notification, eClass);
				break;
			case UMLPackage.PROPERTY_CALL_EXP :
				notifyPropertyCallExpChanged(notification, eClass);
				break;
			case UMLPackage.REAL_LITERAL_EXP :
				notifyRealLiteralExpChanged(notification, eClass);
				break;
			case UMLPackage.STATE_EXP :
				notifyStateExpChanged(notification, eClass);
				break;
			case UMLPackage.STRING_LITERAL_EXP :
				notifyStringLiteralExpChanged(notification, eClass);
				break;
			case UMLPackage.TUPLE_LITERAL_EXP :
				notifyTupleLiteralExpChanged(notification, eClass);
				break;
			case UMLPackage.TUPLE_LITERAL_PART :
				notifyTupleLiteralPartChanged(notification, eClass);
				break;
			case UMLPackage.TYPE_EXP :
				notifyTypeExpChanged(notification, eClass);
				break;
			case UMLPackage.UNSPECIFIED_VALUE_EXP :
				notifyUnspecifiedValueExpChanged(notification, eClass);
				break;
			case UMLPackage.VARIABLE :
				notifyVariableChanged(notification, eClass);
				break;
			case UMLPackage.VARIABLE_EXP :
				notifyVariableExpChanged(notification, eClass);
				break;
			case UMLPackage.TEMPLATE_PARAMETER_TYPE :
				notifyTemplateParameterTypeChanged(notification, eClass);
				break;
		}
	}

	/**
	 * Does nothing; clients may override so that it does something.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param notification a description of the change.
	 * @param eClass the Ecore class of the notifier.
	 * @param derivedUnion the derived union affected by the change.
	 * @generated
	 */
	public void notifyChanged(Notification notification, EClass eClass,
			EStructuralFeature derivedUnion) {
		// Do nothing.
	}

	/**
	 * Calls <code>notifyChanged</code> for each affected derived union.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param notification a description of the change.
	 * @param eClass the Ecore class of the notifier.
	 * @generated
	 */
	protected void notifyAnyTypeChanged(Notification notification,
			EClass eClass) {
		switch (notification.getFeatureID(AnyType.class)) {
			case UMLPackage.ANY_TYPE__OWNED_COMMENT :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.ANY_TYPE__NAME_EXPRESSION :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.ANY_TYPE__NAMESPACE :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNER);
				break;
			case UMLPackage.ANY_TYPE__OWNED_RULE :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.NAMESPACE__OWNED_MEMBER);
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.NAMESPACE__MEMBER);
				break;
			case UMLPackage.ANY_TYPE__ELEMENT_IMPORT :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.ANY_TYPE__PACKAGE_IMPORT :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.ANY_TYPE__OWNED_MEMBER :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.NAMESPACE__MEMBER);
				break;
			case UMLPackage.ANY_TYPE__IMPORTED_MEMBER :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.NAMESPACE__MEMBER);
				break;
			case UMLPackage.ANY_TYPE__OWNING_TEMPLATE_PARAMETER :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNER);
				break;
			case UMLPackage.ANY_TYPE__PACKAGE :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.NAMED_ELEMENT__NAMESPACE);
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNER);
				break;
			case UMLPackage.ANY_TYPE__TEMPLATE_BINDING :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.ANY_TYPE__OWNED_TEMPLATE_SIGNATURE :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.ANY_TYPE__FEATURE :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.NAMESPACE__MEMBER);
				break;
			case UMLPackage.ANY_TYPE__ATTRIBUTE :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.CLASSIFIER__FEATURE);
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.NAMESPACE__MEMBER);
				break;
			case UMLPackage.ANY_TYPE__COLLABORATION_USE :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.ANY_TYPE__GENERALIZATION :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.ANY_TYPE__INHERITED_MEMBER :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.NAMESPACE__MEMBER);
				break;
			case UMLPackage.ANY_TYPE__OWNED_USE_CASE :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.NAMESPACE__OWNED_MEMBER);
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.NAMESPACE__MEMBER);
				break;
			case UMLPackage.ANY_TYPE__REDEFINED_CLASSIFIER :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.REDEFINABLE_ELEMENT__REDEFINED_ELEMENT);
				break;
			case UMLPackage.ANY_TYPE__REPRESENTATION :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.ANY_TYPE__SUBSTITUTION :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.ANY_TYPE__OWNED_OPERATION :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.CLASSIFIER__FEATURE);
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.NAMESPACE__MEMBER);
				break;
		}
	}

	/**
	 * Calls <code>notifyChanged</code> for each affected derived union.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param notification a description of the change.
	 * @param eClass the Ecore class of the notifier.
	 * @generated
	 */
	protected void notifyVoidTypeChanged(Notification notification,
			EClass eClass) {
		switch (notification.getFeatureID(VoidType.class)) {
			case UMLPackage.VOID_TYPE__OWNED_COMMENT :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.VOID_TYPE__NAME_EXPRESSION :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.VOID_TYPE__NAMESPACE :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNER);
				break;
			case UMLPackage.VOID_TYPE__OWNED_RULE :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.NAMESPACE__OWNED_MEMBER);
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.NAMESPACE__MEMBER);
				break;
			case UMLPackage.VOID_TYPE__ELEMENT_IMPORT :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.VOID_TYPE__PACKAGE_IMPORT :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.VOID_TYPE__OWNED_MEMBER :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.NAMESPACE__MEMBER);
				break;
			case UMLPackage.VOID_TYPE__IMPORTED_MEMBER :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.NAMESPACE__MEMBER);
				break;
			case UMLPackage.VOID_TYPE__OWNING_TEMPLATE_PARAMETER :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNER);
				break;
			case UMLPackage.VOID_TYPE__PACKAGE :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.NAMED_ELEMENT__NAMESPACE);
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNER);
				break;
			case UMLPackage.VOID_TYPE__TEMPLATE_BINDING :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.VOID_TYPE__OWNED_TEMPLATE_SIGNATURE :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.VOID_TYPE__FEATURE :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.NAMESPACE__MEMBER);
				break;
			case UMLPackage.VOID_TYPE__ATTRIBUTE :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.CLASSIFIER__FEATURE);
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.NAMESPACE__MEMBER);
				break;
			case UMLPackage.VOID_TYPE__COLLABORATION_USE :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.VOID_TYPE__GENERALIZATION :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.VOID_TYPE__INHERITED_MEMBER :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.NAMESPACE__MEMBER);
				break;
			case UMLPackage.VOID_TYPE__OWNED_USE_CASE :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.NAMESPACE__OWNED_MEMBER);
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.NAMESPACE__MEMBER);
				break;
			case UMLPackage.VOID_TYPE__REDEFINED_CLASSIFIER :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.REDEFINABLE_ELEMENT__REDEFINED_ELEMENT);
				break;
			case UMLPackage.VOID_TYPE__REPRESENTATION :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.VOID_TYPE__SUBSTITUTION :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.VOID_TYPE__OWNED_OPERATION :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.CLASSIFIER__FEATURE);
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.NAMESPACE__MEMBER);
				break;
		}
	}

	/**
	 * Calls <code>notifyChanged</code> for each affected derived union.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param notification a description of the change.
	 * @param eClass the Ecore class of the notifier.
	 * @generated
	 */
	protected void notifyInvalidTypeChanged(Notification notification,
			EClass eClass) {
		switch (notification.getFeatureID(InvalidType.class)) {
			case UMLPackage.INVALID_TYPE__OWNED_COMMENT :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.INVALID_TYPE__NAME_EXPRESSION :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.INVALID_TYPE__NAMESPACE :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNER);
				break;
			case UMLPackage.INVALID_TYPE__OWNED_RULE :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.NAMESPACE__OWNED_MEMBER);
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.NAMESPACE__MEMBER);
				break;
			case UMLPackage.INVALID_TYPE__ELEMENT_IMPORT :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.INVALID_TYPE__PACKAGE_IMPORT :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.INVALID_TYPE__OWNED_MEMBER :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.NAMESPACE__MEMBER);
				break;
			case UMLPackage.INVALID_TYPE__IMPORTED_MEMBER :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.NAMESPACE__MEMBER);
				break;
			case UMLPackage.INVALID_TYPE__OWNING_TEMPLATE_PARAMETER :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNER);
				break;
			case UMLPackage.INVALID_TYPE__PACKAGE :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.NAMED_ELEMENT__NAMESPACE);
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNER);
				break;
			case UMLPackage.INVALID_TYPE__TEMPLATE_BINDING :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.INVALID_TYPE__OWNED_TEMPLATE_SIGNATURE :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.INVALID_TYPE__FEATURE :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.NAMESPACE__MEMBER);
				break;
			case UMLPackage.INVALID_TYPE__ATTRIBUTE :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.CLASSIFIER__FEATURE);
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.NAMESPACE__MEMBER);
				break;
			case UMLPackage.INVALID_TYPE__COLLABORATION_USE :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.INVALID_TYPE__GENERALIZATION :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.INVALID_TYPE__INHERITED_MEMBER :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.NAMESPACE__MEMBER);
				break;
			case UMLPackage.INVALID_TYPE__OWNED_USE_CASE :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.NAMESPACE__OWNED_MEMBER);
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.NAMESPACE__MEMBER);
				break;
			case UMLPackage.INVALID_TYPE__REDEFINED_CLASSIFIER :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.REDEFINABLE_ELEMENT__REDEFINED_ELEMENT);
				break;
			case UMLPackage.INVALID_TYPE__REPRESENTATION :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.INVALID_TYPE__SUBSTITUTION :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.INVALID_TYPE__OWNED_OPERATION :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.CLASSIFIER__FEATURE);
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.NAMESPACE__MEMBER);
				break;
		}
	}

	/**
	 * Calls <code>notifyChanged</code> for each affected derived union.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param notification a description of the change.
	 * @param eClass the Ecore class of the notifier.
	 * @generated
	 */
	protected void notifyElementTypeChanged(Notification notification,
			EClass eClass) {
		switch (notification.getFeatureID(ElementType.class)) {
			case UMLPackage.ELEMENT_TYPE__OWNED_COMMENT :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.ELEMENT_TYPE__NAME_EXPRESSION :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.ELEMENT_TYPE__NAMESPACE :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNER);
				break;
			case UMLPackage.ELEMENT_TYPE__OWNED_RULE :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.NAMESPACE__OWNED_MEMBER);
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.NAMESPACE__MEMBER);
				break;
			case UMLPackage.ELEMENT_TYPE__ELEMENT_IMPORT :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.ELEMENT_TYPE__PACKAGE_IMPORT :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.ELEMENT_TYPE__OWNED_MEMBER :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.NAMESPACE__MEMBER);
				break;
			case UMLPackage.ELEMENT_TYPE__IMPORTED_MEMBER :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.NAMESPACE__MEMBER);
				break;
			case UMLPackage.ELEMENT_TYPE__OWNING_TEMPLATE_PARAMETER :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNER);
				break;
			case UMLPackage.ELEMENT_TYPE__PACKAGE :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.NAMED_ELEMENT__NAMESPACE);
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNER);
				break;
			case UMLPackage.ELEMENT_TYPE__TEMPLATE_BINDING :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.ELEMENT_TYPE__OWNED_TEMPLATE_SIGNATURE :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.ELEMENT_TYPE__FEATURE :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.NAMESPACE__MEMBER);
				break;
			case UMLPackage.ELEMENT_TYPE__ATTRIBUTE :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.CLASSIFIER__FEATURE);
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.NAMESPACE__MEMBER);
				break;
			case UMLPackage.ELEMENT_TYPE__COLLABORATION_USE :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.ELEMENT_TYPE__GENERALIZATION :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.ELEMENT_TYPE__INHERITED_MEMBER :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.NAMESPACE__MEMBER);
				break;
			case UMLPackage.ELEMENT_TYPE__OWNED_USE_CASE :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.NAMESPACE__OWNED_MEMBER);
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.NAMESPACE__MEMBER);
				break;
			case UMLPackage.ELEMENT_TYPE__REDEFINED_CLASSIFIER :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.REDEFINABLE_ELEMENT__REDEFINED_ELEMENT);
				break;
			case UMLPackage.ELEMENT_TYPE__REPRESENTATION :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.ELEMENT_TYPE__SUBSTITUTION :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
		}
	}

	/**
	 * Calls <code>notifyChanged</code> for each affected derived union.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param notification a description of the change.
	 * @param eClass the Ecore class of the notifier.
	 * @generated
	 */
	protected void notifyTypeTypeChanged(Notification notification,
			EClass eClass) {
		switch (notification.getFeatureID(TypeType.class)) {
			case UMLPackage.TYPE_TYPE__OWNED_COMMENT :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.TYPE_TYPE__NAME_EXPRESSION :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.TYPE_TYPE__NAMESPACE :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNER);
				break;
			case UMLPackage.TYPE_TYPE__OWNED_RULE :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.NAMESPACE__OWNED_MEMBER);
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.NAMESPACE__MEMBER);
				break;
			case UMLPackage.TYPE_TYPE__ELEMENT_IMPORT :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.TYPE_TYPE__PACKAGE_IMPORT :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.TYPE_TYPE__OWNED_MEMBER :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.NAMESPACE__MEMBER);
				break;
			case UMLPackage.TYPE_TYPE__IMPORTED_MEMBER :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.NAMESPACE__MEMBER);
				break;
			case UMLPackage.TYPE_TYPE__OWNING_TEMPLATE_PARAMETER :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNER);
				break;
			case UMLPackage.TYPE_TYPE__PACKAGE :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.NAMED_ELEMENT__NAMESPACE);
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNER);
				break;
			case UMLPackage.TYPE_TYPE__TEMPLATE_BINDING :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.TYPE_TYPE__OWNED_TEMPLATE_SIGNATURE :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.TYPE_TYPE__FEATURE :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.NAMESPACE__MEMBER);
				break;
			case UMLPackage.TYPE_TYPE__ATTRIBUTE :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.CLASSIFIER__FEATURE);
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.NAMESPACE__MEMBER);
				break;
			case UMLPackage.TYPE_TYPE__COLLABORATION_USE :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.TYPE_TYPE__GENERALIZATION :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.TYPE_TYPE__INHERITED_MEMBER :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.NAMESPACE__MEMBER);
				break;
			case UMLPackage.TYPE_TYPE__OWNED_USE_CASE :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.NAMESPACE__OWNED_MEMBER);
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.NAMESPACE__MEMBER);
				break;
			case UMLPackage.TYPE_TYPE__REDEFINED_CLASSIFIER :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.REDEFINABLE_ELEMENT__REDEFINED_ELEMENT);
				break;
			case UMLPackage.TYPE_TYPE__REPRESENTATION :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.TYPE_TYPE__SUBSTITUTION :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.TYPE_TYPE__OWNED_OPERATION :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.CLASSIFIER__FEATURE);
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.NAMESPACE__MEMBER);
				break;
		}
	}

	/**
	 * Calls <code>notifyChanged</code> for each affected derived union.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param notification a description of the change.
	 * @param eClass the Ecore class of the notifier.
	 * @generated
	 */
	protected void notifyMessageTypeChanged(Notification notification,
			EClass eClass) {
		switch (notification.getFeatureID(MessageType.class)) {
			case UMLPackage.MESSAGE_TYPE__OWNED_COMMENT :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.MESSAGE_TYPE__NAME_EXPRESSION :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.MESSAGE_TYPE__NAMESPACE :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNER);
				break;
			case UMLPackage.MESSAGE_TYPE__OWNED_RULE :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.NAMESPACE__OWNED_MEMBER);
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.NAMESPACE__MEMBER);
				break;
			case UMLPackage.MESSAGE_TYPE__ELEMENT_IMPORT :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.MESSAGE_TYPE__PACKAGE_IMPORT :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.MESSAGE_TYPE__OWNED_MEMBER :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.NAMESPACE__MEMBER);
				break;
			case UMLPackage.MESSAGE_TYPE__IMPORTED_MEMBER :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.NAMESPACE__MEMBER);
				break;
			case UMLPackage.MESSAGE_TYPE__OWNING_TEMPLATE_PARAMETER :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNER);
				break;
			case UMLPackage.MESSAGE_TYPE__PACKAGE :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.NAMED_ELEMENT__NAMESPACE);
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNER);
				break;
			case UMLPackage.MESSAGE_TYPE__TEMPLATE_BINDING :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.MESSAGE_TYPE__OWNED_TEMPLATE_SIGNATURE :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.MESSAGE_TYPE__FEATURE :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.NAMESPACE__MEMBER);
				break;
			case UMLPackage.MESSAGE_TYPE__ATTRIBUTE :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.CLASSIFIER__FEATURE);
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.NAMESPACE__MEMBER);
				break;
			case UMLPackage.MESSAGE_TYPE__COLLABORATION_USE :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.MESSAGE_TYPE__GENERALIZATION :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.MESSAGE_TYPE__INHERITED_MEMBER :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.NAMESPACE__MEMBER);
				break;
			case UMLPackage.MESSAGE_TYPE__OWNED_USE_CASE :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.NAMESPACE__OWNED_MEMBER);
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.NAMESPACE__MEMBER);
				break;
			case UMLPackage.MESSAGE_TYPE__REDEFINED_CLASSIFIER :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.REDEFINABLE_ELEMENT__REDEFINED_ELEMENT);
				break;
			case UMLPackage.MESSAGE_TYPE__REPRESENTATION :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.MESSAGE_TYPE__SUBSTITUTION :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.MESSAGE_TYPE__OWNED_OPERATION :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.CLASSIFIER__FEATURE);
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.NAMESPACE__MEMBER);
				break;
			case UMLPackage.MESSAGE_TYPE__OWNED_ATTRIBUTE :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.CLASSIFIER__ATTRIBUTE);
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.CLASSIFIER__FEATURE);
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.NAMESPACE__MEMBER);
				break;
		}
	}

	/**
	 * Calls <code>notifyChanged</code> for each affected derived union.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param notification a description of the change.
	 * @param eClass the Ecore class of the notifier.
	 * @generated
	 */
	protected void notifyPrimitiveTypeChanged(Notification notification,
			EClass eClass) {
		switch (notification.getFeatureID(PrimitiveType.class)) {
			case UMLPackage.PRIMITIVE_TYPE__OWNED_COMMENT :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.PRIMITIVE_TYPE__NAME_EXPRESSION :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.PRIMITIVE_TYPE__NAMESPACE :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNER);
				break;
			case UMLPackage.PRIMITIVE_TYPE__OWNED_RULE :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.NAMESPACE__OWNED_MEMBER);
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.NAMESPACE__MEMBER);
				break;
			case UMLPackage.PRIMITIVE_TYPE__ELEMENT_IMPORT :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.PRIMITIVE_TYPE__PACKAGE_IMPORT :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.PRIMITIVE_TYPE__OWNED_MEMBER :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.NAMESPACE__MEMBER);
				break;
			case UMLPackage.PRIMITIVE_TYPE__IMPORTED_MEMBER :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.NAMESPACE__MEMBER);
				break;
			case UMLPackage.PRIMITIVE_TYPE__OWNING_TEMPLATE_PARAMETER :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNER);
				break;
			case UMLPackage.PRIMITIVE_TYPE__PACKAGE :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.NAMED_ELEMENT__NAMESPACE);
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNER);
				break;
			case UMLPackage.PRIMITIVE_TYPE__TEMPLATE_BINDING :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.PRIMITIVE_TYPE__OWNED_TEMPLATE_SIGNATURE :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.PRIMITIVE_TYPE__FEATURE :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.NAMESPACE__MEMBER);
				break;
			case UMLPackage.PRIMITIVE_TYPE__ATTRIBUTE :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.CLASSIFIER__FEATURE);
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.NAMESPACE__MEMBER);
				break;
			case UMLPackage.PRIMITIVE_TYPE__COLLABORATION_USE :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.PRIMITIVE_TYPE__GENERALIZATION :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.PRIMITIVE_TYPE__INHERITED_MEMBER :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.NAMESPACE__MEMBER);
				break;
			case UMLPackage.PRIMITIVE_TYPE__OWNED_USE_CASE :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.NAMESPACE__OWNED_MEMBER);
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.NAMESPACE__MEMBER);
				break;
			case UMLPackage.PRIMITIVE_TYPE__REDEFINED_CLASSIFIER :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.REDEFINABLE_ELEMENT__REDEFINED_ELEMENT);
				break;
			case UMLPackage.PRIMITIVE_TYPE__REPRESENTATION :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.PRIMITIVE_TYPE__SUBSTITUTION :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.PRIMITIVE_TYPE__OWNED_ATTRIBUTE :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.CLASSIFIER__ATTRIBUTE);
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.CLASSIFIER__FEATURE);
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.NAMESPACE__MEMBER);
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.NAMESPACE__OWNED_MEMBER);
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.PRIMITIVE_TYPE__OWNED_OPERATION :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.CLASSIFIER__FEATURE);
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.NAMESPACE__MEMBER);
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.NAMESPACE__OWNED_MEMBER);
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
		}
	}

	/**
	 * Calls <code>notifyChanged</code> for each affected derived union.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param notification a description of the change.
	 * @param eClass the Ecore class of the notifier.
	 * @generated
	 */
	protected void notifyCollectionTypeChanged(Notification notification,
			EClass eClass) {
		switch (notification.getFeatureID(CollectionType.class)) {
			case UMLPackage.COLLECTION_TYPE__OWNED_COMMENT :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.COLLECTION_TYPE__NAME_EXPRESSION :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.COLLECTION_TYPE__NAMESPACE :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNER);
				break;
			case UMLPackage.COLLECTION_TYPE__OWNED_RULE :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.NAMESPACE__OWNED_MEMBER);
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.NAMESPACE__MEMBER);
				break;
			case UMLPackage.COLLECTION_TYPE__ELEMENT_IMPORT :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.COLLECTION_TYPE__PACKAGE_IMPORT :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.COLLECTION_TYPE__OWNED_MEMBER :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.NAMESPACE__MEMBER);
				break;
			case UMLPackage.COLLECTION_TYPE__IMPORTED_MEMBER :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.NAMESPACE__MEMBER);
				break;
			case UMLPackage.COLLECTION_TYPE__OWNING_TEMPLATE_PARAMETER :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNER);
				break;
			case UMLPackage.COLLECTION_TYPE__PACKAGE :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.NAMED_ELEMENT__NAMESPACE);
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNER);
				break;
			case UMLPackage.COLLECTION_TYPE__TEMPLATE_BINDING :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.COLLECTION_TYPE__OWNED_TEMPLATE_SIGNATURE :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.COLLECTION_TYPE__FEATURE :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.NAMESPACE__MEMBER);
				break;
			case UMLPackage.COLLECTION_TYPE__ATTRIBUTE :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.CLASSIFIER__FEATURE);
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.NAMESPACE__MEMBER);
				break;
			case UMLPackage.COLLECTION_TYPE__COLLABORATION_USE :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.COLLECTION_TYPE__GENERALIZATION :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.COLLECTION_TYPE__INHERITED_MEMBER :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.NAMESPACE__MEMBER);
				break;
			case UMLPackage.COLLECTION_TYPE__OWNED_USE_CASE :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.NAMESPACE__OWNED_MEMBER);
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.NAMESPACE__MEMBER);
				break;
			case UMLPackage.COLLECTION_TYPE__REDEFINED_CLASSIFIER :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.REDEFINABLE_ELEMENT__REDEFINED_ELEMENT);
				break;
			case UMLPackage.COLLECTION_TYPE__REPRESENTATION :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.COLLECTION_TYPE__SUBSTITUTION :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.COLLECTION_TYPE__OWNED_ATTRIBUTE :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.CLASSIFIER__ATTRIBUTE);
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.CLASSIFIER__FEATURE);
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.NAMESPACE__MEMBER);
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.NAMESPACE__OWNED_MEMBER);
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.COLLECTION_TYPE__OWNED_OPERATION :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.CLASSIFIER__FEATURE);
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.NAMESPACE__MEMBER);
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.NAMESPACE__OWNED_MEMBER);
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
		}
	}

	/**
	 * Calls <code>notifyChanged</code> for each affected derived union.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param notification a description of the change.
	 * @param eClass the Ecore class of the notifier.
	 * @generated
	 */
	protected void notifyTupleTypeChanged(Notification notification,
			EClass eClass) {
		switch (notification.getFeatureID(TupleType.class)) {
			case UMLPackage.TUPLE_TYPE__OWNED_COMMENT :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.TUPLE_TYPE__NAME_EXPRESSION :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.TUPLE_TYPE__NAMESPACE :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNER);
				break;
			case UMLPackage.TUPLE_TYPE__OWNED_RULE :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.NAMESPACE__OWNED_MEMBER);
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.NAMESPACE__MEMBER);
				break;
			case UMLPackage.TUPLE_TYPE__ELEMENT_IMPORT :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.TUPLE_TYPE__PACKAGE_IMPORT :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.TUPLE_TYPE__OWNED_MEMBER :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.NAMESPACE__MEMBER);
				break;
			case UMLPackage.TUPLE_TYPE__IMPORTED_MEMBER :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.NAMESPACE__MEMBER);
				break;
			case UMLPackage.TUPLE_TYPE__OWNING_TEMPLATE_PARAMETER :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNER);
				break;
			case UMLPackage.TUPLE_TYPE__PACKAGE :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.NAMED_ELEMENT__NAMESPACE);
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNER);
				break;
			case UMLPackage.TUPLE_TYPE__TEMPLATE_BINDING :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.TUPLE_TYPE__OWNED_TEMPLATE_SIGNATURE :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.TUPLE_TYPE__FEATURE :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.NAMESPACE__MEMBER);
				break;
			case UMLPackage.TUPLE_TYPE__ATTRIBUTE :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.CLASSIFIER__FEATURE);
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.NAMESPACE__MEMBER);
				break;
			case UMLPackage.TUPLE_TYPE__COLLABORATION_USE :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.TUPLE_TYPE__GENERALIZATION :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.TUPLE_TYPE__INHERITED_MEMBER :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.NAMESPACE__MEMBER);
				break;
			case UMLPackage.TUPLE_TYPE__OWNED_USE_CASE :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.NAMESPACE__OWNED_MEMBER);
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.NAMESPACE__MEMBER);
				break;
			case UMLPackage.TUPLE_TYPE__REDEFINED_CLASSIFIER :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.REDEFINABLE_ELEMENT__REDEFINED_ELEMENT);
				break;
			case UMLPackage.TUPLE_TYPE__REPRESENTATION :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.TUPLE_TYPE__SUBSTITUTION :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.TUPLE_TYPE__OWNED_ATTRIBUTE :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.CLASSIFIER__ATTRIBUTE);
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.CLASSIFIER__FEATURE);
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.NAMESPACE__MEMBER);
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.NAMESPACE__OWNED_MEMBER);
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.TUPLE_TYPE__OWNED_OPERATION :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.CLASSIFIER__FEATURE);
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.NAMESPACE__MEMBER);
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.NAMESPACE__OWNED_MEMBER);
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
		}
	}

	/**
	 * Calls <code>notifyChanged</code> for each affected derived union.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param notification a description of the change.
	 * @param eClass the Ecore class of the notifier.
	 * @generated
	 */
	protected void notifyBagTypeChanged(Notification notification,
			EClass eClass) {
		switch (notification.getFeatureID(BagType.class)) {
			case UMLPackage.BAG_TYPE__OWNED_COMMENT :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.BAG_TYPE__NAME_EXPRESSION :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.BAG_TYPE__NAMESPACE :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNER);
				break;
			case UMLPackage.BAG_TYPE__OWNED_RULE :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.NAMESPACE__OWNED_MEMBER);
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.NAMESPACE__MEMBER);
				break;
			case UMLPackage.BAG_TYPE__ELEMENT_IMPORT :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.BAG_TYPE__PACKAGE_IMPORT :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.BAG_TYPE__OWNED_MEMBER :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.NAMESPACE__MEMBER);
				break;
			case UMLPackage.BAG_TYPE__IMPORTED_MEMBER :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.NAMESPACE__MEMBER);
				break;
			case UMLPackage.BAG_TYPE__OWNING_TEMPLATE_PARAMETER :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNER);
				break;
			case UMLPackage.BAG_TYPE__PACKAGE :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.NAMED_ELEMENT__NAMESPACE);
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNER);
				break;
			case UMLPackage.BAG_TYPE__TEMPLATE_BINDING :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.BAG_TYPE__OWNED_TEMPLATE_SIGNATURE :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.BAG_TYPE__FEATURE :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.NAMESPACE__MEMBER);
				break;
			case UMLPackage.BAG_TYPE__ATTRIBUTE :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.CLASSIFIER__FEATURE);
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.NAMESPACE__MEMBER);
				break;
			case UMLPackage.BAG_TYPE__COLLABORATION_USE :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.BAG_TYPE__GENERALIZATION :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.BAG_TYPE__INHERITED_MEMBER :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.NAMESPACE__MEMBER);
				break;
			case UMLPackage.BAG_TYPE__OWNED_USE_CASE :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.NAMESPACE__OWNED_MEMBER);
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.NAMESPACE__MEMBER);
				break;
			case UMLPackage.BAG_TYPE__REDEFINED_CLASSIFIER :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.REDEFINABLE_ELEMENT__REDEFINED_ELEMENT);
				break;
			case UMLPackage.BAG_TYPE__REPRESENTATION :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.BAG_TYPE__SUBSTITUTION :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.BAG_TYPE__OWNED_ATTRIBUTE :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.CLASSIFIER__ATTRIBUTE);
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.CLASSIFIER__FEATURE);
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.NAMESPACE__MEMBER);
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.NAMESPACE__OWNED_MEMBER);
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.BAG_TYPE__OWNED_OPERATION :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.CLASSIFIER__FEATURE);
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.NAMESPACE__MEMBER);
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.NAMESPACE__OWNED_MEMBER);
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
		}
	}

	/**
	 * Calls <code>notifyChanged</code> for each affected derived union.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param notification a description of the change.
	 * @param eClass the Ecore class of the notifier.
	 * @generated
	 */
	protected void notifySetTypeChanged(Notification notification,
			EClass eClass) {
		switch (notification.getFeatureID(SetType.class)) {
			case UMLPackage.SET_TYPE__OWNED_COMMENT :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.SET_TYPE__NAME_EXPRESSION :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.SET_TYPE__NAMESPACE :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNER);
				break;
			case UMLPackage.SET_TYPE__OWNED_RULE :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.NAMESPACE__OWNED_MEMBER);
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.NAMESPACE__MEMBER);
				break;
			case UMLPackage.SET_TYPE__ELEMENT_IMPORT :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.SET_TYPE__PACKAGE_IMPORT :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.SET_TYPE__OWNED_MEMBER :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.NAMESPACE__MEMBER);
				break;
			case UMLPackage.SET_TYPE__IMPORTED_MEMBER :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.NAMESPACE__MEMBER);
				break;
			case UMLPackage.SET_TYPE__OWNING_TEMPLATE_PARAMETER :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNER);
				break;
			case UMLPackage.SET_TYPE__PACKAGE :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.NAMED_ELEMENT__NAMESPACE);
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNER);
				break;
			case UMLPackage.SET_TYPE__TEMPLATE_BINDING :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.SET_TYPE__OWNED_TEMPLATE_SIGNATURE :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.SET_TYPE__FEATURE :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.NAMESPACE__MEMBER);
				break;
			case UMLPackage.SET_TYPE__ATTRIBUTE :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.CLASSIFIER__FEATURE);
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.NAMESPACE__MEMBER);
				break;
			case UMLPackage.SET_TYPE__COLLABORATION_USE :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.SET_TYPE__GENERALIZATION :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.SET_TYPE__INHERITED_MEMBER :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.NAMESPACE__MEMBER);
				break;
			case UMLPackage.SET_TYPE__OWNED_USE_CASE :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.NAMESPACE__OWNED_MEMBER);
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.NAMESPACE__MEMBER);
				break;
			case UMLPackage.SET_TYPE__REDEFINED_CLASSIFIER :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.REDEFINABLE_ELEMENT__REDEFINED_ELEMENT);
				break;
			case UMLPackage.SET_TYPE__REPRESENTATION :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.SET_TYPE__SUBSTITUTION :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.SET_TYPE__OWNED_ATTRIBUTE :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.CLASSIFIER__ATTRIBUTE);
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.CLASSIFIER__FEATURE);
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.NAMESPACE__MEMBER);
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.NAMESPACE__OWNED_MEMBER);
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.SET_TYPE__OWNED_OPERATION :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.CLASSIFIER__FEATURE);
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.NAMESPACE__MEMBER);
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.NAMESPACE__OWNED_MEMBER);
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
		}
	}

	/**
	 * Calls <code>notifyChanged</code> for each affected derived union.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param notification a description of the change.
	 * @param eClass the Ecore class of the notifier.
	 * @generated
	 */
	protected void notifyOrderedSetTypeChanged(Notification notification,
			EClass eClass) {
		switch (notification.getFeatureID(OrderedSetType.class)) {
			case UMLPackage.ORDERED_SET_TYPE__OWNED_COMMENT :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.ORDERED_SET_TYPE__NAME_EXPRESSION :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.ORDERED_SET_TYPE__NAMESPACE :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNER);
				break;
			case UMLPackage.ORDERED_SET_TYPE__OWNED_RULE :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.NAMESPACE__OWNED_MEMBER);
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.NAMESPACE__MEMBER);
				break;
			case UMLPackage.ORDERED_SET_TYPE__ELEMENT_IMPORT :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.ORDERED_SET_TYPE__PACKAGE_IMPORT :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.ORDERED_SET_TYPE__OWNED_MEMBER :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.NAMESPACE__MEMBER);
				break;
			case UMLPackage.ORDERED_SET_TYPE__IMPORTED_MEMBER :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.NAMESPACE__MEMBER);
				break;
			case UMLPackage.ORDERED_SET_TYPE__OWNING_TEMPLATE_PARAMETER :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNER);
				break;
			case UMLPackage.ORDERED_SET_TYPE__PACKAGE :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.NAMED_ELEMENT__NAMESPACE);
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNER);
				break;
			case UMLPackage.ORDERED_SET_TYPE__TEMPLATE_BINDING :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.ORDERED_SET_TYPE__OWNED_TEMPLATE_SIGNATURE :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.ORDERED_SET_TYPE__FEATURE :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.NAMESPACE__MEMBER);
				break;
			case UMLPackage.ORDERED_SET_TYPE__ATTRIBUTE :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.CLASSIFIER__FEATURE);
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.NAMESPACE__MEMBER);
				break;
			case UMLPackage.ORDERED_SET_TYPE__COLLABORATION_USE :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.ORDERED_SET_TYPE__GENERALIZATION :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.ORDERED_SET_TYPE__INHERITED_MEMBER :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.NAMESPACE__MEMBER);
				break;
			case UMLPackage.ORDERED_SET_TYPE__OWNED_USE_CASE :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.NAMESPACE__OWNED_MEMBER);
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.NAMESPACE__MEMBER);
				break;
			case UMLPackage.ORDERED_SET_TYPE__REDEFINED_CLASSIFIER :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.REDEFINABLE_ELEMENT__REDEFINED_ELEMENT);
				break;
			case UMLPackage.ORDERED_SET_TYPE__REPRESENTATION :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.ORDERED_SET_TYPE__SUBSTITUTION :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.ORDERED_SET_TYPE__OWNED_ATTRIBUTE :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.CLASSIFIER__ATTRIBUTE);
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.CLASSIFIER__FEATURE);
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.NAMESPACE__MEMBER);
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.NAMESPACE__OWNED_MEMBER);
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.ORDERED_SET_TYPE__OWNED_OPERATION :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.CLASSIFIER__FEATURE);
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.NAMESPACE__MEMBER);
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.NAMESPACE__OWNED_MEMBER);
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
		}
	}

	/**
	 * Calls <code>notifyChanged</code> for each affected derived union.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param notification a description of the change.
	 * @param eClass the Ecore class of the notifier.
	 * @generated
	 */
	protected void notifySequenceTypeChanged(Notification notification,
			EClass eClass) {
		switch (notification.getFeatureID(SequenceType.class)) {
			case UMLPackage.SEQUENCE_TYPE__OWNED_COMMENT :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.SEQUENCE_TYPE__NAME_EXPRESSION :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.SEQUENCE_TYPE__NAMESPACE :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNER);
				break;
			case UMLPackage.SEQUENCE_TYPE__OWNED_RULE :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.NAMESPACE__OWNED_MEMBER);
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.NAMESPACE__MEMBER);
				break;
			case UMLPackage.SEQUENCE_TYPE__ELEMENT_IMPORT :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.SEQUENCE_TYPE__PACKAGE_IMPORT :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.SEQUENCE_TYPE__OWNED_MEMBER :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.NAMESPACE__MEMBER);
				break;
			case UMLPackage.SEQUENCE_TYPE__IMPORTED_MEMBER :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.NAMESPACE__MEMBER);
				break;
			case UMLPackage.SEQUENCE_TYPE__OWNING_TEMPLATE_PARAMETER :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNER);
				break;
			case UMLPackage.SEQUENCE_TYPE__PACKAGE :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.NAMED_ELEMENT__NAMESPACE);
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNER);
				break;
			case UMLPackage.SEQUENCE_TYPE__TEMPLATE_BINDING :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.SEQUENCE_TYPE__OWNED_TEMPLATE_SIGNATURE :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.SEQUENCE_TYPE__FEATURE :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.NAMESPACE__MEMBER);
				break;
			case UMLPackage.SEQUENCE_TYPE__ATTRIBUTE :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.CLASSIFIER__FEATURE);
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.NAMESPACE__MEMBER);
				break;
			case UMLPackage.SEQUENCE_TYPE__COLLABORATION_USE :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.SEQUENCE_TYPE__GENERALIZATION :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.SEQUENCE_TYPE__INHERITED_MEMBER :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.NAMESPACE__MEMBER);
				break;
			case UMLPackage.SEQUENCE_TYPE__OWNED_USE_CASE :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.NAMESPACE__OWNED_MEMBER);
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.NAMESPACE__MEMBER);
				break;
			case UMLPackage.SEQUENCE_TYPE__REDEFINED_CLASSIFIER :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.REDEFINABLE_ELEMENT__REDEFINED_ELEMENT);
				break;
			case UMLPackage.SEQUENCE_TYPE__REPRESENTATION :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.SEQUENCE_TYPE__SUBSTITUTION :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.SEQUENCE_TYPE__OWNED_ATTRIBUTE :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.CLASSIFIER__ATTRIBUTE);
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.CLASSIFIER__FEATURE);
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.NAMESPACE__MEMBER);
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.NAMESPACE__OWNED_MEMBER);
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.SEQUENCE_TYPE__OWNED_OPERATION :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.CLASSIFIER__FEATURE);
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.NAMESPACE__MEMBER);
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.NAMESPACE__OWNED_MEMBER);
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
		}
	}

	/**
	 * Calls <code>notifyChanged</code> for each affected derived union.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param notification a description of the change.
	 * @param eClass the Ecore class of the notifier.
	 * @generated
	 */
	protected void notifyExpressionInOCLChanged(Notification notification,
			EClass eClass) {
		switch (notification.getFeatureID(ExpressionInOCL.class)) {
			case UMLPackage.EXPRESSION_IN_OCL__OWNED_COMMENT :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.EXPRESSION_IN_OCL__NAME_EXPRESSION :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.EXPRESSION_IN_OCL__NAMESPACE :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNER);
				break;
			case UMLPackage.EXPRESSION_IN_OCL__OWNING_TEMPLATE_PARAMETER :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNER);
				break;
		}
	}

	/**
	 * Calls <code>notifyChanged</code> for each affected derived union.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param notification a description of the change.
	 * @param eClass the Ecore class of the notifier.
	 * @generated
	 */
	protected void notifyAssociationClassCallExpChanged(
			Notification notification, EClass eClass) {
		switch (notification.getFeatureID(AssociationClassCallExp.class)) {
			case UMLPackage.ASSOCIATION_CLASS_CALL_EXP__OWNED_COMMENT :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.ASSOCIATION_CLASS_CALL_EXP__NAME_EXPRESSION :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.ASSOCIATION_CLASS_CALL_EXP__NAMESPACE :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNER);
				break;
		}
	}

	/**
	 * Calls <code>notifyChanged</code> for each affected derived union.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param notification a description of the change.
	 * @param eClass the Ecore class of the notifier.
	 * @generated
	 */
	protected void notifyBooleanLiteralExpChanged(Notification notification,
			EClass eClass) {
		switch (notification.getFeatureID(BooleanLiteralExp.class)) {
			case UMLPackage.BOOLEAN_LITERAL_EXP__OWNED_COMMENT :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.BOOLEAN_LITERAL_EXP__NAME_EXPRESSION :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.BOOLEAN_LITERAL_EXP__NAMESPACE :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNER);
				break;
		}
	}

	/**
	 * Calls <code>notifyChanged</code> for each affected derived union.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param notification a description of the change.
	 * @param eClass the Ecore class of the notifier.
	 * @generated
	 */
	protected void notifyCollectionItemChanged(Notification notification,
			EClass eClass) {
		switch (notification.getFeatureID(CollectionItem.class)) {
			case UMLPackage.COLLECTION_ITEM__OWNED_COMMENT :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.COLLECTION_ITEM__NAME_EXPRESSION :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.COLLECTION_ITEM__NAMESPACE :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNER);
				break;
		}
	}

	/**
	 * Calls <code>notifyChanged</code> for each affected derived union.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param notification a description of the change.
	 * @param eClass the Ecore class of the notifier.
	 * @generated
	 */
	protected void notifyCollectionLiteralExpChanged(Notification notification,
			EClass eClass) {
		switch (notification.getFeatureID(CollectionLiteralExp.class)) {
			case UMLPackage.COLLECTION_LITERAL_EXP__OWNED_COMMENT :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.COLLECTION_LITERAL_EXP__NAME_EXPRESSION :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.COLLECTION_LITERAL_EXP__NAMESPACE :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNER);
				break;
		}
	}

	/**
	 * Calls <code>notifyChanged</code> for each affected derived union.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param notification a description of the change.
	 * @param eClass the Ecore class of the notifier.
	 * @generated
	 */
	protected void notifyCollectionRangeChanged(Notification notification,
			EClass eClass) {
		switch (notification.getFeatureID(CollectionRange.class)) {
			case UMLPackage.COLLECTION_RANGE__OWNED_COMMENT :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.COLLECTION_RANGE__NAME_EXPRESSION :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.COLLECTION_RANGE__NAMESPACE :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNER);
				break;
		}
	}

	/**
	 * Calls <code>notifyChanged</code> for each affected derived union.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param notification a description of the change.
	 * @param eClass the Ecore class of the notifier.
	 * @generated
	 */
	protected void notifyEnumLiteralExpChanged(Notification notification,
			EClass eClass) {
		switch (notification.getFeatureID(EnumLiteralExp.class)) {
			case UMLPackage.ENUM_LITERAL_EXP__OWNED_COMMENT :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.ENUM_LITERAL_EXP__NAME_EXPRESSION :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.ENUM_LITERAL_EXP__NAMESPACE :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNER);
				break;
		}
	}

	/**
	 * Calls <code>notifyChanged</code> for each affected derived union.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param notification a description of the change.
	 * @param eClass the Ecore class of the notifier.
	 * @generated
	 */
	protected void notifyIfExpChanged(Notification notification,
			EClass eClass) {
		switch (notification.getFeatureID(IfExp.class)) {
			case UMLPackage.IF_EXP__OWNED_COMMENT :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.IF_EXP__NAME_EXPRESSION :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.IF_EXP__NAMESPACE :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNER);
				break;
		}
	}

	/**
	 * Calls <code>notifyChanged</code> for each affected derived union.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param notification a description of the change.
	 * @param eClass the Ecore class of the notifier.
	 * @generated
	 */
	protected void notifyIntegerLiteralExpChanged(Notification notification,
			EClass eClass) {
		switch (notification.getFeatureID(IntegerLiteralExp.class)) {
			case UMLPackage.INTEGER_LITERAL_EXP__OWNED_COMMENT :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.INTEGER_LITERAL_EXP__NAME_EXPRESSION :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.INTEGER_LITERAL_EXP__NAMESPACE :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNER);
				break;
		}
	}

	/**
	 * Calls <code>notifyChanged</code> for each affected derived union.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param notification a description of the change.
	 * @param eClass the Ecore class of the notifier.
	 * @generated
	 */
	protected void notifyUnlimitedNaturalLiteralExpChanged(
			Notification notification, EClass eClass) {
		switch (notification.getFeatureID(UnlimitedNaturalLiteralExp.class)) {
			case UMLPackage.UNLIMITED_NATURAL_LITERAL_EXP__OWNED_COMMENT :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.UNLIMITED_NATURAL_LITERAL_EXP__NAME_EXPRESSION :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.UNLIMITED_NATURAL_LITERAL_EXP__NAMESPACE :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNER);
				break;
		}
	}

	/**
	 * Calls <code>notifyChanged</code> for each affected derived union.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param notification a description of the change.
	 * @param eClass the Ecore class of the notifier.
	 * @generated
	 */
	protected void notifyInvalidLiteralExpChanged(Notification notification,
			EClass eClass) {
		switch (notification.getFeatureID(InvalidLiteralExp.class)) {
			case UMLPackage.INVALID_LITERAL_EXP__OWNED_COMMENT :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.INVALID_LITERAL_EXP__NAME_EXPRESSION :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.INVALID_LITERAL_EXP__NAMESPACE :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNER);
				break;
		}
	}

	/**
	 * Calls <code>notifyChanged</code> for each affected derived union.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param notification a description of the change.
	 * @param eClass the Ecore class of the notifier.
	 * @generated
	 */
	protected void notifyIterateExpChanged(Notification notification,
			EClass eClass) {
		switch (notification.getFeatureID(IterateExp.class)) {
			case UMLPackage.ITERATE_EXP__OWNED_COMMENT :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.ITERATE_EXP__NAME_EXPRESSION :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.ITERATE_EXP__NAMESPACE :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNER);
				break;
		}
	}

	/**
	 * Calls <code>notifyChanged</code> for each affected derived union.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param notification a description of the change.
	 * @param eClass the Ecore class of the notifier.
	 * @generated
	 */
	protected void notifyIteratorExpChanged(Notification notification,
			EClass eClass) {
		switch (notification.getFeatureID(IteratorExp.class)) {
			case UMLPackage.ITERATOR_EXP__OWNED_COMMENT :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.ITERATOR_EXP__NAME_EXPRESSION :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.ITERATOR_EXP__NAMESPACE :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNER);
				break;
		}
	}

	/**
	 * Calls <code>notifyChanged</code> for each affected derived union.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param notification a description of the change.
	 * @param eClass the Ecore class of the notifier.
	 * @generated
	 */
	protected void notifyLetExpChanged(Notification notification,
			EClass eClass) {
		switch (notification.getFeatureID(LetExp.class)) {
			case UMLPackage.LET_EXP__OWNED_COMMENT :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.LET_EXP__NAME_EXPRESSION :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.LET_EXP__NAMESPACE :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNER);
				break;
		}
	}

	/**
	 * Calls <code>notifyChanged</code> for each affected derived union.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param notification a description of the change.
	 * @param eClass the Ecore class of the notifier.
	 * @generated
	 */
	protected void notifyMessageExpChanged(Notification notification,
			EClass eClass) {
		switch (notification.getFeatureID(MessageExp.class)) {
			case UMLPackage.MESSAGE_EXP__OWNED_COMMENT :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.MESSAGE_EXP__NAME_EXPRESSION :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.MESSAGE_EXP__NAMESPACE :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNER);
				break;
		}
	}

	/**
	 * Calls <code>notifyChanged</code> for each affected derived union.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param notification a description of the change.
	 * @param eClass the Ecore class of the notifier.
	 * @generated
	 */
	protected void notifyNullLiteralExpChanged(Notification notification,
			EClass eClass) {
		switch (notification.getFeatureID(NullLiteralExp.class)) {
			case UMLPackage.NULL_LITERAL_EXP__OWNED_COMMENT :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.NULL_LITERAL_EXP__NAME_EXPRESSION :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.NULL_LITERAL_EXP__NAMESPACE :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNER);
				break;
		}
	}

	/**
	 * Calls <code>notifyChanged</code> for each affected derived union.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param notification a description of the change.
	 * @param eClass the Ecore class of the notifier.
	 * @generated
	 */
	protected void notifyOperationCallExpChanged(Notification notification,
			EClass eClass) {
		switch (notification.getFeatureID(OperationCallExp.class)) {
			case UMLPackage.OPERATION_CALL_EXP__OWNED_COMMENT :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.OPERATION_CALL_EXP__NAME_EXPRESSION :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.OPERATION_CALL_EXP__NAMESPACE :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNER);
				break;
		}
	}

	/**
	 * Calls <code>notifyChanged</code> for each affected derived union.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param notification a description of the change.
	 * @param eClass the Ecore class of the notifier.
	 * @generated
	 */
	protected void notifyPropertyCallExpChanged(Notification notification,
			EClass eClass) {
		switch (notification.getFeatureID(PropertyCallExp.class)) {
			case UMLPackage.PROPERTY_CALL_EXP__OWNED_COMMENT :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.PROPERTY_CALL_EXP__NAME_EXPRESSION :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.PROPERTY_CALL_EXP__NAMESPACE :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNER);
				break;
		}
	}

	/**
	 * Calls <code>notifyChanged</code> for each affected derived union.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param notification a description of the change.
	 * @param eClass the Ecore class of the notifier.
	 * @generated
	 */
	protected void notifyRealLiteralExpChanged(Notification notification,
			EClass eClass) {
		switch (notification.getFeatureID(RealLiteralExp.class)) {
			case UMLPackage.REAL_LITERAL_EXP__OWNED_COMMENT :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.REAL_LITERAL_EXP__NAME_EXPRESSION :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.REAL_LITERAL_EXP__NAMESPACE :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNER);
				break;
		}
	}

	/**
	 * Calls <code>notifyChanged</code> for each affected derived union.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param notification a description of the change.
	 * @param eClass the Ecore class of the notifier.
	 * @generated
	 */
	protected void notifyStateExpChanged(Notification notification,
			EClass eClass) {
		switch (notification.getFeatureID(StateExp.class)) {
			case UMLPackage.STATE_EXP__OWNED_COMMENT :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.STATE_EXP__NAME_EXPRESSION :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.STATE_EXP__NAMESPACE :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNER);
				break;
		}
	}

	/**
	 * Calls <code>notifyChanged</code> for each affected derived union.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param notification a description of the change.
	 * @param eClass the Ecore class of the notifier.
	 * @generated
	 */
	protected void notifyStringLiteralExpChanged(Notification notification,
			EClass eClass) {
		switch (notification.getFeatureID(StringLiteralExp.class)) {
			case UMLPackage.STRING_LITERAL_EXP__OWNED_COMMENT :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.STRING_LITERAL_EXP__NAME_EXPRESSION :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.STRING_LITERAL_EXP__NAMESPACE :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNER);
				break;
		}
	}

	/**
	 * Calls <code>notifyChanged</code> for each affected derived union.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param notification a description of the change.
	 * @param eClass the Ecore class of the notifier.
	 * @generated
	 */
	protected void notifyTupleLiteralExpChanged(Notification notification,
			EClass eClass) {
		switch (notification.getFeatureID(TupleLiteralExp.class)) {
			case UMLPackage.TUPLE_LITERAL_EXP__OWNED_COMMENT :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.TUPLE_LITERAL_EXP__NAME_EXPRESSION :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.TUPLE_LITERAL_EXP__NAMESPACE :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNER);
				break;
		}
	}

	/**
	 * Calls <code>notifyChanged</code> for each affected derived union.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param notification a description of the change.
	 * @param eClass the Ecore class of the notifier.
	 * @generated
	 */
	protected void notifyTupleLiteralPartChanged(Notification notification,
			EClass eClass) {
		switch (notification.getFeatureID(TupleLiteralPart.class)) {
			case UMLPackage.TUPLE_LITERAL_PART__OWNED_COMMENT :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.TUPLE_LITERAL_PART__NAME_EXPRESSION :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.TUPLE_LITERAL_PART__NAMESPACE :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNER);
				break;
		}
	}

	/**
	 * Calls <code>notifyChanged</code> for each affected derived union.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param notification a description of the change.
	 * @param eClass the Ecore class of the notifier.
	 * @generated
	 */
	protected void notifyTypeExpChanged(Notification notification,
			EClass eClass) {
		switch (notification.getFeatureID(TypeExp.class)) {
			case UMLPackage.TYPE_EXP__OWNED_COMMENT :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.TYPE_EXP__NAME_EXPRESSION :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.TYPE_EXP__NAMESPACE :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNER);
				break;
		}
	}

	/**
	 * Calls <code>notifyChanged</code> for each affected derived union.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param notification a description of the change.
	 * @param eClass the Ecore class of the notifier.
	 * @generated
	 */
	protected void notifyUnspecifiedValueExpChanged(Notification notification,
			EClass eClass) {
		switch (notification.getFeatureID(UnspecifiedValueExp.class)) {
			case UMLPackage.UNSPECIFIED_VALUE_EXP__OWNED_COMMENT :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.UNSPECIFIED_VALUE_EXP__NAME_EXPRESSION :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.UNSPECIFIED_VALUE_EXP__NAMESPACE :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNER);
				break;
		}
	}

	/**
	 * Calls <code>notifyChanged</code> for each affected derived union.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param notification a description of the change.
	 * @param eClass the Ecore class of the notifier.
	 * @generated
	 */
	protected void notifyVariableChanged(Notification notification,
			EClass eClass) {
		switch (notification.getFeatureID(Variable.class)) {
			case UMLPackage.VARIABLE__OWNED_COMMENT :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.VARIABLE__NAME_EXPRESSION :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.VARIABLE__NAMESPACE :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNER);
				break;
		}
	}

	/**
	 * Calls <code>notifyChanged</code> for each affected derived union.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param notification a description of the change.
	 * @param eClass the Ecore class of the notifier.
	 * @generated
	 */
	protected void notifyVariableExpChanged(Notification notification,
			EClass eClass) {
		switch (notification.getFeatureID(VariableExp.class)) {
			case UMLPackage.VARIABLE_EXP__OWNED_COMMENT :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.VARIABLE_EXP__NAME_EXPRESSION :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.VARIABLE_EXP__NAMESPACE :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNER);
				break;
		}
	}

	/**
	 * Calls <code>notifyChanged</code> for each affected derived union.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param notification a description of the change.
	 * @param eClass the Ecore class of the notifier.
	 * @generated
	 */
	protected void notifyTemplateParameterTypeChanged(Notification notification,
			EClass eClass) {
		switch (notification.getFeatureID(TemplateParameterType.class)) {
			case UMLPackage.TEMPLATE_PARAMETER_TYPE__OWNED_COMMENT :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.TEMPLATE_PARAMETER_TYPE__NAME_EXPRESSION :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.TEMPLATE_PARAMETER_TYPE__NAMESPACE :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNER);
				break;
			case UMLPackage.TEMPLATE_PARAMETER_TYPE__OWNED_RULE :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.NAMESPACE__OWNED_MEMBER);
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.NAMESPACE__MEMBER);
				break;
			case UMLPackage.TEMPLATE_PARAMETER_TYPE__ELEMENT_IMPORT :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.TEMPLATE_PARAMETER_TYPE__PACKAGE_IMPORT :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.TEMPLATE_PARAMETER_TYPE__OWNED_MEMBER :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.NAMESPACE__MEMBER);
				break;
			case UMLPackage.TEMPLATE_PARAMETER_TYPE__IMPORTED_MEMBER :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.NAMESPACE__MEMBER);
				break;
			case UMLPackage.TEMPLATE_PARAMETER_TYPE__OWNING_TEMPLATE_PARAMETER :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNER);
				break;
			case UMLPackage.TEMPLATE_PARAMETER_TYPE__PACKAGE :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.NAMED_ELEMENT__NAMESPACE);
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNER);
				break;
			case UMLPackage.TEMPLATE_PARAMETER_TYPE__TEMPLATE_BINDING :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.TEMPLATE_PARAMETER_TYPE__OWNED_TEMPLATE_SIGNATURE :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.TEMPLATE_PARAMETER_TYPE__FEATURE :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.NAMESPACE__MEMBER);
				break;
			case UMLPackage.TEMPLATE_PARAMETER_TYPE__ATTRIBUTE :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.CLASSIFIER__FEATURE);
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.NAMESPACE__MEMBER);
				break;
			case UMLPackage.TEMPLATE_PARAMETER_TYPE__COLLABORATION_USE :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.TEMPLATE_PARAMETER_TYPE__GENERALIZATION :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.TEMPLATE_PARAMETER_TYPE__INHERITED_MEMBER :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.NAMESPACE__MEMBER);
				break;
			case UMLPackage.TEMPLATE_PARAMETER_TYPE__OWNED_USE_CASE :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.NAMESPACE__OWNED_MEMBER);
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.NAMESPACE__MEMBER);
				break;
			case UMLPackage.TEMPLATE_PARAMETER_TYPE__REDEFINED_CLASSIFIER :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.REDEFINABLE_ELEMENT__REDEFINED_ELEMENT);
				break;
			case UMLPackage.TEMPLATE_PARAMETER_TYPE__REPRESENTATION :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.TEMPLATE_PARAMETER_TYPE__SUBSTITUTION :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.ELEMENT__OWNED_ELEMENT);
				break;
			case UMLPackage.TEMPLATE_PARAMETER_TYPE__OWNED_OPERATION :
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.CLASSIFIER__FEATURE);
				notifyChanged(notification, eClass,
					org.eclipse.uml2.uml.UMLPackage.Literals.NAMESPACE__MEMBER);
				break;
		}
	}

} //UMLDerivedUnionAdapter
