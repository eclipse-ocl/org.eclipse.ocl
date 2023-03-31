/**
 * Copyright (c) 2010, 2022 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 */
package org.eclipse.ocl.ocl.util;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.Switch;

import org.eclipse.jdt.annotation.Nullable;

import org.eclipse.ocl.ocl.Annotation;
import org.eclipse.ocl.ocl.AnyType;
import org.eclipse.ocl.ocl.AssociationClass;
import org.eclipse.ocl.ocl.AssociationClassCallExp;
import org.eclipse.ocl.ocl.BagType;
import org.eclipse.ocl.ocl.Behavior;
import org.eclipse.ocl.ocl.BooleanLiteralExp;
import org.eclipse.ocl.ocl.BooleanType;
import org.eclipse.ocl.ocl.CallExp;
import org.eclipse.ocl.ocl.CallOperationAction;
import org.eclipse.ocl.ocl.CollectionItem;
import org.eclipse.ocl.ocl.CollectionLiteralExp;
import org.eclipse.ocl.ocl.CollectionLiteralPart;
import org.eclipse.ocl.ocl.CollectionRange;
import org.eclipse.ocl.ocl.CollectionType;
import org.eclipse.ocl.ocl.Comment;
import org.eclipse.ocl.ocl.CompleteClass;
import org.eclipse.ocl.ocl.CompleteEnvironment;
import org.eclipse.ocl.ocl.CompleteModel;
import org.eclipse.ocl.ocl.CompletePackage;
import org.eclipse.ocl.ocl.ConnectionPointReference;
import org.eclipse.ocl.ocl.Constraint;
import org.eclipse.ocl.ocl.DataType;
import org.eclipse.ocl.ocl.Detail;
import org.eclipse.ocl.ocl.DynamicBehavior;
import org.eclipse.ocl.ocl.DynamicElement;
import org.eclipse.ocl.ocl.DynamicProperty;
import org.eclipse.ocl.ocl.DynamicType;
import org.eclipse.ocl.ocl.DynamicValueSpecification;
import org.eclipse.ocl.ocl.Element;
import org.eclipse.ocl.ocl.ElementExtension;
import org.eclipse.ocl.ocl.ElementLiteralExp;
import org.eclipse.ocl.ocl.EnumLiteralExp;
import org.eclipse.ocl.ocl.Enumeration;
import org.eclipse.ocl.ocl.EnumerationLiteral;
import org.eclipse.ocl.ocl.ExpressionInOCL;
import org.eclipse.ocl.ocl.Feature;
import org.eclipse.ocl.ocl.FeatureCallExp;
import org.eclipse.ocl.ocl.FinalState;
import org.eclipse.ocl.ocl.IfExp;
import org.eclipse.ocl.ocl.Import;
import org.eclipse.ocl.ocl.InstanceSpecification;
import org.eclipse.ocl.ocl.IntegerLiteralExp;
import org.eclipse.ocl.ocl.InvalidLiteralExp;
import org.eclipse.ocl.ocl.InvalidType;
import org.eclipse.ocl.ocl.IterableType;
import org.eclipse.ocl.ocl.IterateExp;
import org.eclipse.ocl.ocl.Iteration;
import org.eclipse.ocl.ocl.IteratorExp;
import org.eclipse.ocl.ocl.IteratorVariable;
import org.eclipse.ocl.ocl.JavaType;
import org.eclipse.ocl.ocl.LambdaType;
import org.eclipse.ocl.ocl.LanguageExpression;
import org.eclipse.ocl.ocl.LetExp;
import org.eclipse.ocl.ocl.LetVariable;
import org.eclipse.ocl.ocl.Library;
import org.eclipse.ocl.ocl.LiteralExp;
import org.eclipse.ocl.ocl.LoopExp;
import org.eclipse.ocl.ocl.MapLiteralExp;
import org.eclipse.ocl.ocl.MapLiteralPart;
import org.eclipse.ocl.ocl.MapType;
import org.eclipse.ocl.ocl.MessageExp;
import org.eclipse.ocl.ocl.MessageType;
import org.eclipse.ocl.ocl.Model;
import org.eclipse.ocl.ocl.NamedElement;
import org.eclipse.ocl.ocl.Namespace;
import org.eclipse.ocl.ocl.NavigationCallExp;
import org.eclipse.ocl.ocl.NullLiteralExp;
import org.eclipse.ocl.ocl.NumericLiteralExp;
import org.eclipse.ocl.ocl.OCLASPackage;
import org.eclipse.ocl.ocl.OCLExpression;
import org.eclipse.ocl.ocl.OclComparable;
import org.eclipse.ocl.ocl.OclElement;
import org.eclipse.ocl.ocl.OclEnumeration;
import org.eclipse.ocl.ocl.OclLambda;
import org.eclipse.ocl.ocl.OclMessage;
import org.eclipse.ocl.ocl.OclState;
import org.eclipse.ocl.ocl.OclStereotype;
import org.eclipse.ocl.ocl.OclSummable;
import org.eclipse.ocl.ocl.OclTuple;
import org.eclipse.ocl.ocl.OclType;
import org.eclipse.ocl.ocl.Operation;
import org.eclipse.ocl.ocl.OperationCallExp;
import org.eclipse.ocl.ocl.OppositePropertyCallExp;
import org.eclipse.ocl.ocl.OrderedSetType;
import org.eclipse.ocl.ocl.OrphanCompletePackage;
import org.eclipse.ocl.ocl.Parameter;
import org.eclipse.ocl.ocl.ParameterVariable;
import org.eclipse.ocl.ocl.Precedence;
import org.eclipse.ocl.ocl.PrimitiveCompletePackage;
import org.eclipse.ocl.ocl.PrimitiveLiteralExp;
import org.eclipse.ocl.ocl.PrimitiveType;
import org.eclipse.ocl.ocl.Profile;
import org.eclipse.ocl.ocl.ProfileApplication;
import org.eclipse.ocl.ocl.Property;
import org.eclipse.ocl.ocl.PropertyCallExp;
import org.eclipse.ocl.ocl.Pseudostate;
import org.eclipse.ocl.ocl.RealLiteralExp;
import org.eclipse.ocl.ocl.ReferringElement;
import org.eclipse.ocl.ocl.Region;
import org.eclipse.ocl.ocl.ResultVariable;
import org.eclipse.ocl.ocl.SendSignalAction;
import org.eclipse.ocl.ocl.SequenceType;
import org.eclipse.ocl.ocl.SetType;
import org.eclipse.ocl.ocl.ShadowExp;
import org.eclipse.ocl.ocl.ShadowPart;
import org.eclipse.ocl.ocl.Signal;
import org.eclipse.ocl.ocl.Slot;
import org.eclipse.ocl.ocl.StandardLibrary;
import org.eclipse.ocl.ocl.State;
import org.eclipse.ocl.ocl.StateExp;
import org.eclipse.ocl.ocl.StateMachine;
import org.eclipse.ocl.ocl.Stereotype;
import org.eclipse.ocl.ocl.StereotypeExtender;
import org.eclipse.ocl.ocl.StringLiteralExp;
import org.eclipse.ocl.ocl.TemplateBinding;
import org.eclipse.ocl.ocl.TemplateParameter;
import org.eclipse.ocl.ocl.TemplateParameterSubstitution;
import org.eclipse.ocl.ocl.TemplateSignature;
import org.eclipse.ocl.ocl.TemplateableElement;
import org.eclipse.ocl.ocl.Transition;
import org.eclipse.ocl.ocl.Trigger;
import org.eclipse.ocl.ocl.TupleLiteralExp;
import org.eclipse.ocl.ocl.TupleLiteralPart;
import org.eclipse.ocl.ocl.TupleType;
import org.eclipse.ocl.ocl.Type;

import org.eclipse.ocl.ocl.TypeExp;
import org.eclipse.ocl.ocl.TypedElement;
import org.eclipse.ocl.ocl.UnlimitedNaturalLiteralExp;
import org.eclipse.ocl.ocl.UnspecifiedValueExp;
import org.eclipse.ocl.ocl.ValueSpecification;
import org.eclipse.ocl.ocl.Variable;
import org.eclipse.ocl.ocl.VariableDeclaration;
import org.eclipse.ocl.ocl.VariableExp;
import org.eclipse.ocl.ocl.Vertex;
import org.eclipse.ocl.ocl.WildcardType;
import org.eclipse.ocl.pivot.SelfType;
import org.eclipse.ocl.pivot.VoidType;

import org.eclipse.ocl.pivot.util.Visitable;
import org.eclipse.ocl.pivot.utilities.MorePivotable;
import org.eclipse.ocl.pivot.utilities.Nameable;
import org.eclipse.ocl.pivot.utilities.Pivotable;
import org.eclipse.ocl.pivot.values.Bag;
import org.eclipse.ocl.pivot.values.OrderedSet;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see org.eclipse.ocl.ocl.OCLASPackage
 * @generated
 */
public class OCLASSwitch<@Nullable T1> extends Switch<T1>
{
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static OCLASPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OCLASSwitch()
	{
		if (modelPackage == null)
		{
			modelPackage = OCLASPackage.eINSTANCE;
		}
	}

	/**
	 * Checks whether this is a switch for the given package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param ePackage the package in question.
	 * @return whether this is a switch for the given package.
	 * @generated
	 */
	@Override
	protected boolean isSwitchFor(EPackage ePackage)
	{
		return ePackage == modelPackage;
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	@Override
	protected T1 doSwitch(int classifierID, EObject theEObject)
	{
		switch (classifierID)
		{
			case 0:
			{
				Annotation annotation = (Annotation)theEObject;
				T1 result = caseAnnotation(annotation);
				if (result == null) result = caseNamedElement(annotation);
				if (result == null) result = caseElement(annotation);
				if (result == null) result = caseOclElement(annotation);
				if (result == null) result = caseOclAny(annotation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 1:
			{
				AnyType anyType = (AnyType)theEObject;
				T1 result = caseAnyType(anyType);
				if (result == null) result = caseClass(anyType);
				if (result == null) result = caseType(anyType);
				if (result == null) result = caseNamespace(anyType);
				if (result == null) result = caseTemplateableElement(anyType);
				if (result == null) result = caseOclAny(anyType);
				if (result == null) result = caseOclType(anyType);
				if (result == null) result = caseNamedElement(anyType);
				if (result == null) result = caseElement(anyType);
				if (result == null) result = caseOclElement(anyType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 2:
			{
				AssociationClass associationClass = (AssociationClass)theEObject;
				T1 result = caseAssociationClass(associationClass);
				if (result == null) result = caseClass(associationClass);
				if (result == null) result = caseType(associationClass);
				if (result == null) result = caseNamespace(associationClass);
				if (result == null) result = caseTemplateableElement(associationClass);
				if (result == null) result = caseOclAny(associationClass);
				if (result == null) result = caseOclType(associationClass);
				if (result == null) result = caseNamedElement(associationClass);
				if (result == null) result = caseElement(associationClass);
				if (result == null) result = caseOclElement(associationClass);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 3:
			{
				AssociationClassCallExp associationClassCallExp = (AssociationClassCallExp)theEObject;
				T1 result = caseAssociationClassCallExp(associationClassCallExp);
				if (result == null) result = caseNavigationCallExp(associationClassCallExp);
				if (result == null) result = caseFeatureCallExp(associationClassCallExp);
				if (result == null) result = caseCallExp(associationClassCallExp);
				if (result == null) result = caseOCLExpression(associationClassCallExp);
				if (result == null) result = caseTypedElement(associationClassCallExp);
				if (result == null) result = caseNamedElement(associationClassCallExp);
				if (result == null) result = caseElement(associationClassCallExp);
				if (result == null) result = caseOclElement(associationClassCallExp);
				if (result == null) result = caseOclAny(associationClassCallExp);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 4:
			{
				Bag<?> bag = (Bag<?>)theEObject;
				T1 result = caseBag(bag);
				if (result == null) result = caseCollection(bag);
				if (result == null) result = caseOclAny(bag);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 5:
			{
				BagType bagType = (BagType)theEObject;
				T1 result = caseBagType(bagType);
				if (result == null) result = caseCollectionType(bagType);
				if (result == null) result = caseIterableType(bagType);
				if (result == null) result = caseOclAny(bagType);
				if (result == null) result = caseDataType(bagType);
				if (result == null) result = caseClass(bagType);
				if (result == null) result = caseType(bagType);
				if (result == null) result = caseNamespace(bagType);
				if (result == null) result = caseTemplateableElement(bagType);
				if (result == null) result = caseOclType(bagType);
				if (result == null) result = caseNamedElement(bagType);
				if (result == null) result = caseElement(bagType);
				if (result == null) result = caseOclElement(bagType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 6:
			{
				Behavior behavior = (Behavior)theEObject;
				T1 result = caseBehavior(behavior);
				if (result == null) result = caseClass(behavior);
				if (result == null) result = caseType(behavior);
				if (result == null) result = caseNamespace(behavior);
				if (result == null) result = caseTemplateableElement(behavior);
				if (result == null) result = caseOclAny(behavior);
				if (result == null) result = caseOclType(behavior);
				if (result == null) result = caseNamedElement(behavior);
				if (result == null) result = caseElement(behavior);
				if (result == null) result = caseOclElement(behavior);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 7:
			{
				BooleanLiteralExp booleanLiteralExp = (BooleanLiteralExp)theEObject;
				T1 result = caseBooleanLiteralExp(booleanLiteralExp);
				if (result == null) result = casePrimitiveLiteralExp(booleanLiteralExp);
				if (result == null) result = caseLiteralExp(booleanLiteralExp);
				if (result == null) result = caseOCLExpression(booleanLiteralExp);
				if (result == null) result = caseTypedElement(booleanLiteralExp);
				if (result == null) result = caseNamedElement(booleanLiteralExp);
				if (result == null) result = caseElement(booleanLiteralExp);
				if (result == null) result = caseOclElement(booleanLiteralExp);
				if (result == null) result = caseOclAny(booleanLiteralExp);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 8:
			{
				BooleanType booleanType = (BooleanType)theEObject;
				T1 result = caseBooleanType(booleanType);
				if (result == null) result = casePrimitiveType(booleanType);
				if (result == null) result = caseOclAny(booleanType);
				if (result == null) result = caseDataType(booleanType);
				if (result == null) result = caseClass(booleanType);
				if (result == null) result = caseType(booleanType);
				if (result == null) result = caseNamespace(booleanType);
				if (result == null) result = caseTemplateableElement(booleanType);
				if (result == null) result = caseOclType(booleanType);
				if (result == null) result = caseNamedElement(booleanType);
				if (result == null) result = caseElement(booleanType);
				if (result == null) result = caseOclElement(booleanType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 9:
			{
				CallExp callExp = (CallExp)theEObject;
				T1 result = caseCallExp(callExp);
				if (result == null) result = caseOCLExpression(callExp);
				if (result == null) result = caseTypedElement(callExp);
				if (result == null) result = caseNamedElement(callExp);
				if (result == null) result = caseElement(callExp);
				if (result == null) result = caseOclElement(callExp);
				if (result == null) result = caseOclAny(callExp);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 10:
			{
				CallOperationAction callOperationAction = (CallOperationAction)theEObject;
				T1 result = caseCallOperationAction(callOperationAction);
				if (result == null) result = caseNamedElement(callOperationAction);
				if (result == null) result = caseElement(callOperationAction);
				if (result == null) result = caseOclElement(callOperationAction);
				if (result == null) result = caseOclAny(callOperationAction);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 11:
			{
				org.eclipse.ocl.ocl.Class class_ = (org.eclipse.ocl.ocl.Class)theEObject;
				T1 result = caseClass(class_);
				if (result == null) result = caseType(class_);
				if (result == null) result = caseNamespace(class_);
				if (result == null) result = caseTemplateableElement(class_);
				if (result == null) result = caseOclAny(class_);
				if (result == null) result = caseOclType(class_);
				if (result == null) result = caseNamedElement(class_);
				if (result == null) result = caseElement(class_);
				if (result == null) result = caseOclElement(class_);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 12:
			{
				Collection<?> collection = (Collection<?>)theEObject;
				T1 result = caseCollection(collection);
				if (result == null) result = caseOclAny(collection);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 13:
			{
				CollectionItem collectionItem = (CollectionItem)theEObject;
				T1 result = caseCollectionItem(collectionItem);
				if (result == null) result = caseCollectionLiteralPart(collectionItem);
				if (result == null) result = caseTypedElement(collectionItem);
				if (result == null) result = caseNamedElement(collectionItem);
				if (result == null) result = caseElement(collectionItem);
				if (result == null) result = caseOclElement(collectionItem);
				if (result == null) result = caseOclAny(collectionItem);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 14:
			{
				CollectionLiteralExp collectionLiteralExp = (CollectionLiteralExp)theEObject;
				T1 result = caseCollectionLiteralExp(collectionLiteralExp);
				if (result == null) result = caseLiteralExp(collectionLiteralExp);
				if (result == null) result = caseOCLExpression(collectionLiteralExp);
				if (result == null) result = caseTypedElement(collectionLiteralExp);
				if (result == null) result = caseNamedElement(collectionLiteralExp);
				if (result == null) result = caseElement(collectionLiteralExp);
				if (result == null) result = caseOclElement(collectionLiteralExp);
				if (result == null) result = caseOclAny(collectionLiteralExp);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 15:
			{
				CollectionLiteralPart collectionLiteralPart = (CollectionLiteralPart)theEObject;
				T1 result = caseCollectionLiteralPart(collectionLiteralPart);
				if (result == null) result = caseTypedElement(collectionLiteralPart);
				if (result == null) result = caseNamedElement(collectionLiteralPart);
				if (result == null) result = caseElement(collectionLiteralPart);
				if (result == null) result = caseOclElement(collectionLiteralPart);
				if (result == null) result = caseOclAny(collectionLiteralPart);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 16:
			{
				CollectionRange collectionRange = (CollectionRange)theEObject;
				T1 result = caseCollectionRange(collectionRange);
				if (result == null) result = caseCollectionLiteralPart(collectionRange);
				if (result == null) result = caseTypedElement(collectionRange);
				if (result == null) result = caseNamedElement(collectionRange);
				if (result == null) result = caseElement(collectionRange);
				if (result == null) result = caseOclElement(collectionRange);
				if (result == null) result = caseOclAny(collectionRange);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 17:
			{
				CollectionType collectionType = (CollectionType)theEObject;
				T1 result = caseCollectionType(collectionType);
				if (result == null) result = caseIterableType(collectionType);
				if (result == null) result = caseOclAny(collectionType);
				if (result == null) result = caseDataType(collectionType);
				if (result == null) result = caseClass(collectionType);
				if (result == null) result = caseType(collectionType);
				if (result == null) result = caseNamespace(collectionType);
				if (result == null) result = caseTemplateableElement(collectionType);
				if (result == null) result = caseOclType(collectionType);
				if (result == null) result = caseNamedElement(collectionType);
				if (result == null) result = caseElement(collectionType);
				if (result == null) result = caseOclElement(collectionType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 18:
			{
				Comment comment = (Comment)theEObject;
				T1 result = caseComment(comment);
				if (result == null) result = caseElement(comment);
				if (result == null) result = caseOclElement(comment);
				if (result == null) result = caseOclAny(comment);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 19:
			{
				CompleteClass completeClass = (CompleteClass)theEObject;
				T1 result = caseCompleteClass(completeClass);
				if (result == null) result = caseNamedElement(completeClass);
				if (result == null) result = caseElement(completeClass);
				if (result == null) result = caseOclElement(completeClass);
				if (result == null) result = caseOclAny(completeClass);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 20:
			{
				CompleteEnvironment completeEnvironment = (CompleteEnvironment)theEObject;
				T1 result = caseCompleteEnvironment(completeEnvironment);
				if (result == null) result = caseElement(completeEnvironment);
				if (result == null) result = caseOclElement(completeEnvironment);
				if (result == null) result = caseOclAny(completeEnvironment);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 21:
			{
				CompleteModel completeModel = (CompleteModel)theEObject;
				T1 result = caseCompleteModel(completeModel);
				if (result == null) result = caseNamedElement(completeModel);
				if (result == null) result = caseElement(completeModel);
				if (result == null) result = caseOclElement(completeModel);
				if (result == null) result = caseOclAny(completeModel);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 22:
			{
				CompletePackage completePackage = (CompletePackage)theEObject;
				T1 result = caseCompletePackage(completePackage);
				if (result == null) result = caseNamedElement(completePackage);
				if (result == null) result = caseElement(completePackage);
				if (result == null) result = caseOclElement(completePackage);
				if (result == null) result = caseOclAny(completePackage);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 23:
			{
				ConnectionPointReference connectionPointReference = (ConnectionPointReference)theEObject;
				T1 result = caseConnectionPointReference(connectionPointReference);
				if (result == null) result = caseVertex(connectionPointReference);
				if (result == null) result = caseNamedElement(connectionPointReference);
				if (result == null) result = caseElement(connectionPointReference);
				if (result == null) result = caseOclElement(connectionPointReference);
				if (result == null) result = caseOclAny(connectionPointReference);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 24:
			{
				Constraint constraint = (Constraint)theEObject;
				T1 result = caseConstraint(constraint);
				if (result == null) result = caseNamedElement(constraint);
				if (result == null) result = caseElement(constraint);
				if (result == null) result = caseOclElement(constraint);
				if (result == null) result = caseOclAny(constraint);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 25:
			{
				DataType dataType = (DataType)theEObject;
				T1 result = caseDataType(dataType);
				if (result == null) result = caseClass(dataType);
				if (result == null) result = caseType(dataType);
				if (result == null) result = caseNamespace(dataType);
				if (result == null) result = caseTemplateableElement(dataType);
				if (result == null) result = caseOclAny(dataType);
				if (result == null) result = caseOclType(dataType);
				if (result == null) result = caseNamedElement(dataType);
				if (result == null) result = caseElement(dataType);
				if (result == null) result = caseOclElement(dataType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 26:
			{
				Detail detail = (Detail)theEObject;
				T1 result = caseDetail(detail);
				if (result == null) result = caseNamedElement(detail);
				if (result == null) result = caseElement(detail);
				if (result == null) result = caseOclElement(detail);
				if (result == null) result = caseOclAny(detail);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 27:
			{
				DynamicBehavior dynamicBehavior = (DynamicBehavior)theEObject;
				T1 result = caseDynamicBehavior(dynamicBehavior);
				if (result == null) result = caseBehavior(dynamicBehavior);
				if (result == null) result = caseDynamicType(dynamicBehavior);
				if (result == null) result = caseClass(dynamicBehavior);
				if (result == null) result = caseDynamicElement(dynamicBehavior);
				if (result == null) result = caseType(dynamicBehavior);
				if (result == null) result = caseNamespace(dynamicBehavior);
				if (result == null) result = caseTemplateableElement(dynamicBehavior);
				if (result == null) result = caseOclAny(dynamicBehavior);
				if (result == null) result = caseOclType(dynamicBehavior);
				if (result == null) result = caseNamedElement(dynamicBehavior);
				if (result == null) result = caseElement(dynamicBehavior);
				if (result == null) result = caseOclElement(dynamicBehavior);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 28:
			{
				DynamicElement dynamicElement = (DynamicElement)theEObject;
				T1 result = caseDynamicElement(dynamicElement);
				if (result == null) result = caseElement(dynamicElement);
				if (result == null) result = caseOclElement(dynamicElement);
				if (result == null) result = caseOclAny(dynamicElement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 29:
			{
				DynamicProperty dynamicProperty = (DynamicProperty)theEObject;
				T1 result = caseDynamicProperty(dynamicProperty);
				if (result == null) result = caseElement(dynamicProperty);
				if (result == null) result = caseOclElement(dynamicProperty);
				if (result == null) result = caseOclAny(dynamicProperty);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 30:
			{
				DynamicType dynamicType = (DynamicType)theEObject;
				T1 result = caseDynamicType(dynamicType);
				if (result == null) result = caseClass(dynamicType);
				if (result == null) result = caseDynamicElement(dynamicType);
				if (result == null) result = caseType(dynamicType);
				if (result == null) result = caseNamespace(dynamicType);
				if (result == null) result = caseTemplateableElement(dynamicType);
				if (result == null) result = caseOclAny(dynamicType);
				if (result == null) result = caseOclType(dynamicType);
				if (result == null) result = caseNamedElement(dynamicType);
				if (result == null) result = caseElement(dynamicType);
				if (result == null) result = caseOclElement(dynamicType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 31:
			{
				DynamicValueSpecification dynamicValueSpecification = (DynamicValueSpecification)theEObject;
				T1 result = caseDynamicValueSpecification(dynamicValueSpecification);
				if (result == null) result = caseValueSpecification(dynamicValueSpecification);
				if (result == null) result = caseTypedElement(dynamicValueSpecification);
				if (result == null) result = caseNamedElement(dynamicValueSpecification);
				if (result == null) result = caseElement(dynamicValueSpecification);
				if (result == null) result = caseOclElement(dynamicValueSpecification);
				if (result == null) result = caseOclAny(dynamicValueSpecification);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 32:
			{
				Element element = (Element)theEObject;
				T1 result = caseElement(element);
				if (result == null) result = caseOclElement(element);
				if (result == null) result = caseOclAny(element);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 33:
			{
				ElementExtension elementExtension = (ElementExtension)theEObject;
				T1 result = caseElementExtension(elementExtension);
				if (result == null) result = caseClass(elementExtension);
				if (result == null) result = caseType(elementExtension);
				if (result == null) result = caseNamespace(elementExtension);
				if (result == null) result = caseTemplateableElement(elementExtension);
				if (result == null) result = caseOclAny(elementExtension);
				if (result == null) result = caseOclType(elementExtension);
				if (result == null) result = caseNamedElement(elementExtension);
				if (result == null) result = caseElement(elementExtension);
				if (result == null) result = caseOclElement(elementExtension);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 34:
			{
				ElementLiteralExp elementLiteralExp = (ElementLiteralExp)theEObject;
				T1 result = caseElementLiteralExp(elementLiteralExp);
				if (result == null) result = caseLiteralExp(elementLiteralExp);
				if (result == null) result = caseOCLExpression(elementLiteralExp);
				if (result == null) result = caseTypedElement(elementLiteralExp);
				if (result == null) result = caseNamedElement(elementLiteralExp);
				if (result == null) result = caseElement(elementLiteralExp);
				if (result == null) result = caseOclElement(elementLiteralExp);
				if (result == null) result = caseOclAny(elementLiteralExp);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 35:
			{
				EnumLiteralExp enumLiteralExp = (EnumLiteralExp)theEObject;
				T1 result = caseEnumLiteralExp(enumLiteralExp);
				if (result == null) result = caseLiteralExp(enumLiteralExp);
				if (result == null) result = caseOCLExpression(enumLiteralExp);
				if (result == null) result = caseTypedElement(enumLiteralExp);
				if (result == null) result = caseNamedElement(enumLiteralExp);
				if (result == null) result = caseElement(enumLiteralExp);
				if (result == null) result = caseOclElement(enumLiteralExp);
				if (result == null) result = caseOclAny(enumLiteralExp);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 36:
			{
				Enumeration enumeration = (Enumeration)theEObject;
				T1 result = caseEnumeration(enumeration);
				if (result == null) result = caseDataType(enumeration);
				if (result == null) result = caseClass(enumeration);
				if (result == null) result = caseOclAny(enumeration);
				if (result == null) result = caseType(enumeration);
				if (result == null) result = caseNamespace(enumeration);
				if (result == null) result = caseTemplateableElement(enumeration);
				if (result == null) result = caseOclType(enumeration);
				if (result == null) result = caseNamedElement(enumeration);
				if (result == null) result = caseElement(enumeration);
				if (result == null) result = caseOclElement(enumeration);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 37:
			{
				EnumerationLiteral enumerationLiteral = (EnumerationLiteral)theEObject;
				T1 result = caseEnumerationLiteral(enumerationLiteral);
				if (result == null) result = caseInstanceSpecification(enumerationLiteral);
				if (result == null) result = caseNamedElement(enumerationLiteral);
				if (result == null) result = caseElement(enumerationLiteral);
				if (result == null) result = caseOclElement(enumerationLiteral);
				if (result == null) result = caseOclAny(enumerationLiteral);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 38:
			{
				ExpressionInOCL expressionInOCL = (ExpressionInOCL)theEObject;
				T1 result = caseExpressionInOCL(expressionInOCL);
				if (result == null) result = caseLanguageExpression(expressionInOCL);
				if (result == null) result = caseValueSpecification(expressionInOCL);
				if (result == null) result = caseTypedElement(expressionInOCL);
				if (result == null) result = caseNamedElement(expressionInOCL);
				if (result == null) result = caseElement(expressionInOCL);
				if (result == null) result = caseOclElement(expressionInOCL);
				if (result == null) result = caseOclAny(expressionInOCL);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 39:
			{
				Feature feature = (Feature)theEObject;
				T1 result = caseFeature(feature);
				if (result == null) result = caseTypedElement(feature);
				if (result == null) result = caseNamedElement(feature);
				if (result == null) result = caseElement(feature);
				if (result == null) result = caseOclElement(feature);
				if (result == null) result = caseOclAny(feature);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 40:
			{
				FeatureCallExp featureCallExp = (FeatureCallExp)theEObject;
				T1 result = caseFeatureCallExp(featureCallExp);
				if (result == null) result = caseCallExp(featureCallExp);
				if (result == null) result = caseOCLExpression(featureCallExp);
				if (result == null) result = caseTypedElement(featureCallExp);
				if (result == null) result = caseNamedElement(featureCallExp);
				if (result == null) result = caseElement(featureCallExp);
				if (result == null) result = caseOclElement(featureCallExp);
				if (result == null) result = caseOclAny(featureCallExp);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 41:
			{
				FinalState finalState = (FinalState)theEObject;
				T1 result = caseFinalState(finalState);
				if (result == null) result = caseState(finalState);
				if (result == null) result = caseOclState(finalState);
				if (result == null) result = caseNamespace(finalState);
				if (result == null) result = caseVertex(finalState);
				if (result == null) result = caseNamedElement(finalState);
				if (result == null) result = caseElement(finalState);
				if (result == null) result = caseOclElement(finalState);
				if (result == null) result = caseOclAny(finalState);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 42:
			{
				IfExp ifExp = (IfExp)theEObject;
				T1 result = caseIfExp(ifExp);
				if (result == null) result = caseOCLExpression(ifExp);
				if (result == null) result = caseTypedElement(ifExp);
				if (result == null) result = caseNamedElement(ifExp);
				if (result == null) result = caseElement(ifExp);
				if (result == null) result = caseOclElement(ifExp);
				if (result == null) result = caseOclAny(ifExp);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 43:
			{
				Import import_ = (Import)theEObject;
				T1 result = caseImport(import_);
				if (result == null) result = caseNamedElement(import_);
				if (result == null) result = caseElement(import_);
				if (result == null) result = caseOclElement(import_);
				if (result == null) result = caseOclAny(import_);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 44:
			{
				InstanceSpecification instanceSpecification = (InstanceSpecification)theEObject;
				T1 result = caseInstanceSpecification(instanceSpecification);
				if (result == null) result = caseNamedElement(instanceSpecification);
				if (result == null) result = caseElement(instanceSpecification);
				if (result == null) result = caseOclElement(instanceSpecification);
				if (result == null) result = caseOclAny(instanceSpecification);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 45:
			{
				IntegerLiteralExp integerLiteralExp = (IntegerLiteralExp)theEObject;
				T1 result = caseIntegerLiteralExp(integerLiteralExp);
				if (result == null) result = caseNumericLiteralExp(integerLiteralExp);
				if (result == null) result = casePrimitiveLiteralExp(integerLiteralExp);
				if (result == null) result = caseLiteralExp(integerLiteralExp);
				if (result == null) result = caseOCLExpression(integerLiteralExp);
				if (result == null) result = caseTypedElement(integerLiteralExp);
				if (result == null) result = caseNamedElement(integerLiteralExp);
				if (result == null) result = caseElement(integerLiteralExp);
				if (result == null) result = caseOclElement(integerLiteralExp);
				if (result == null) result = caseOclAny(integerLiteralExp);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 46:
			{
				InvalidLiteralExp invalidLiteralExp = (InvalidLiteralExp)theEObject;
				T1 result = caseInvalidLiteralExp(invalidLiteralExp);
				if (result == null) result = caseLiteralExp(invalidLiteralExp);
				if (result == null) result = caseOCLExpression(invalidLiteralExp);
				if (result == null) result = caseTypedElement(invalidLiteralExp);
				if (result == null) result = caseNamedElement(invalidLiteralExp);
				if (result == null) result = caseElement(invalidLiteralExp);
				if (result == null) result = caseOclElement(invalidLiteralExp);
				if (result == null) result = caseOclAny(invalidLiteralExp);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 47:
			{
				InvalidType invalidType = (InvalidType)theEObject;
				T1 result = caseInvalidType(invalidType);
				if (result == null) result = caseClass(invalidType);
				if (result == null) result = caseOclAny(invalidType);
				if (result == null) result = caseType(invalidType);
				if (result == null) result = caseNamespace(invalidType);
				if (result == null) result = caseTemplateableElement(invalidType);
				if (result == null) result = caseOclType(invalidType);
				if (result == null) result = caseNamedElement(invalidType);
				if (result == null) result = caseElement(invalidType);
				if (result == null) result = caseOclElement(invalidType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 48:
			{
				IterableType iterableType = (IterableType)theEObject;
				T1 result = caseIterableType(iterableType);
				if (result == null) result = caseDataType(iterableType);
				if (result == null) result = caseClass(iterableType);
				if (result == null) result = caseType(iterableType);
				if (result == null) result = caseNamespace(iterableType);
				if (result == null) result = caseTemplateableElement(iterableType);
				if (result == null) result = caseOclAny(iterableType);
				if (result == null) result = caseOclType(iterableType);
				if (result == null) result = caseNamedElement(iterableType);
				if (result == null) result = caseElement(iterableType);
				if (result == null) result = caseOclElement(iterableType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 49:
			{
				IterateExp iterateExp = (IterateExp)theEObject;
				T1 result = caseIterateExp(iterateExp);
				if (result == null) result = caseLoopExp(iterateExp);
				if (result == null) result = caseReferringElement(iterateExp);
				if (result == null) result = caseCallExp(iterateExp);
				if (result == null) result = caseOCLExpression(iterateExp);
				if (result == null) result = caseTypedElement(iterateExp);
				if (result == null) result = caseNamedElement(iterateExp);
				if (result == null) result = caseElement(iterateExp);
				if (result == null) result = caseOclElement(iterateExp);
				if (result == null) result = caseOclAny(iterateExp);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 50:
			{
				Iteration iteration = (Iteration)theEObject;
				T1 result = caseIteration(iteration);
				if (result == null) result = caseOperation(iteration);
				if (result == null) result = caseFeature(iteration);
				if (result == null) result = caseNamespace(iteration);
				if (result == null) result = caseTemplateableElement(iteration);
				if (result == null) result = caseTypedElement(iteration);
				if (result == null) result = caseNamedElement(iteration);
				if (result == null) result = caseElement(iteration);
				if (result == null) result = caseOclElement(iteration);
				if (result == null) result = caseOclAny(iteration);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 51:
			{
				IteratorExp iteratorExp = (IteratorExp)theEObject;
				T1 result = caseIteratorExp(iteratorExp);
				if (result == null) result = caseLoopExp(iteratorExp);
				if (result == null) result = caseReferringElement(iteratorExp);
				if (result == null) result = caseCallExp(iteratorExp);
				if (result == null) result = caseOCLExpression(iteratorExp);
				if (result == null) result = caseTypedElement(iteratorExp);
				if (result == null) result = caseNamedElement(iteratorExp);
				if (result == null) result = caseElement(iteratorExp);
				if (result == null) result = caseOclElement(iteratorExp);
				if (result == null) result = caseOclAny(iteratorExp);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 52:
			{
				IteratorVariable iteratorVariable = (IteratorVariable)theEObject;
				T1 result = caseIteratorVariable(iteratorVariable);
				if (result == null) result = caseVariable(iteratorVariable);
				if (result == null) result = caseVariableDeclaration(iteratorVariable);
				if (result == null) result = caseTypedElement(iteratorVariable);
				if (result == null) result = caseNamedElement(iteratorVariable);
				if (result == null) result = caseElement(iteratorVariable);
				if (result == null) result = caseOclElement(iteratorVariable);
				if (result == null) result = caseOclAny(iteratorVariable);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 53:
			{
				JavaType javaType = (JavaType)theEObject;
				T1 result = caseJavaType(javaType);
				if (result == null) result = caseClass(javaType);
				if (result == null) result = caseType(javaType);
				if (result == null) result = caseNamespace(javaType);
				if (result == null) result = caseTemplateableElement(javaType);
				if (result == null) result = caseOclAny(javaType);
				if (result == null) result = caseOclType(javaType);
				if (result == null) result = caseNamedElement(javaType);
				if (result == null) result = caseElement(javaType);
				if (result == null) result = caseOclElement(javaType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 54:
			{
				LambdaType lambdaType = (LambdaType)theEObject;
				T1 result = caseLambdaType(lambdaType);
				if (result == null) result = caseDataType(lambdaType);
				if (result == null) result = caseClass(lambdaType);
				if (result == null) result = caseType(lambdaType);
				if (result == null) result = caseNamespace(lambdaType);
				if (result == null) result = caseTemplateableElement(lambdaType);
				if (result == null) result = caseOclAny(lambdaType);
				if (result == null) result = caseOclType(lambdaType);
				if (result == null) result = caseNamedElement(lambdaType);
				if (result == null) result = caseElement(lambdaType);
				if (result == null) result = caseOclElement(lambdaType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 55:
			{
				LanguageExpression languageExpression = (LanguageExpression)theEObject;
				T1 result = caseLanguageExpression(languageExpression);
				if (result == null) result = caseValueSpecification(languageExpression);
				if (result == null) result = caseTypedElement(languageExpression);
				if (result == null) result = caseNamedElement(languageExpression);
				if (result == null) result = caseElement(languageExpression);
				if (result == null) result = caseOclElement(languageExpression);
				if (result == null) result = caseOclAny(languageExpression);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 56:
			{
				LetExp letExp = (LetExp)theEObject;
				T1 result = caseLetExp(letExp);
				if (result == null) result = caseOCLExpression(letExp);
				if (result == null) result = caseTypedElement(letExp);
				if (result == null) result = caseNamedElement(letExp);
				if (result == null) result = caseElement(letExp);
				if (result == null) result = caseOclElement(letExp);
				if (result == null) result = caseOclAny(letExp);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 57:
			{
				LetVariable letVariable = (LetVariable)theEObject;
				T1 result = caseLetVariable(letVariable);
				if (result == null) result = caseVariable(letVariable);
				if (result == null) result = caseVariableDeclaration(letVariable);
				if (result == null) result = caseTypedElement(letVariable);
				if (result == null) result = caseNamedElement(letVariable);
				if (result == null) result = caseElement(letVariable);
				if (result == null) result = caseOclElement(letVariable);
				if (result == null) result = caseOclAny(letVariable);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 58:
			{
				Library library = (Library)theEObject;
				T1 result = caseLibrary(library);
				if (result == null) result = casePackage(library);
				if (result == null) result = caseNamespace(library);
				if (result == null) result = caseNamedElement(library);
				if (result == null) result = caseElement(library);
				if (result == null) result = caseOclElement(library);
				if (result == null) result = caseOclAny(library);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 59:
			{
				LiteralExp literalExp = (LiteralExp)theEObject;
				T1 result = caseLiteralExp(literalExp);
				if (result == null) result = caseOCLExpression(literalExp);
				if (result == null) result = caseTypedElement(literalExp);
				if (result == null) result = caseNamedElement(literalExp);
				if (result == null) result = caseElement(literalExp);
				if (result == null) result = caseOclElement(literalExp);
				if (result == null) result = caseOclAny(literalExp);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 60:
			{
				LoopExp loopExp = (LoopExp)theEObject;
				T1 result = caseLoopExp(loopExp);
				if (result == null) result = caseCallExp(loopExp);
				if (result == null) result = caseOCLExpression(loopExp);
				if (result == null) result = caseTypedElement(loopExp);
				if (result == null) result = caseNamedElement(loopExp);
				if (result == null) result = caseElement(loopExp);
				if (result == null) result = caseOclElement(loopExp);
				if (result == null) result = caseOclAny(loopExp);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 61:
			{
				Map<?, ?> map = (Map<?, ?>)theEObject;
				T1 result = caseMap(map);
				if (result == null) result = caseOclAny(map);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 62:
			{
				MapLiteralExp mapLiteralExp = (MapLiteralExp)theEObject;
				T1 result = caseMapLiteralExp(mapLiteralExp);
				if (result == null) result = caseLiteralExp(mapLiteralExp);
				if (result == null) result = caseOCLExpression(mapLiteralExp);
				if (result == null) result = caseTypedElement(mapLiteralExp);
				if (result == null) result = caseNamedElement(mapLiteralExp);
				if (result == null) result = caseElement(mapLiteralExp);
				if (result == null) result = caseOclElement(mapLiteralExp);
				if (result == null) result = caseOclAny(mapLiteralExp);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 63:
			{
				MapLiteralPart mapLiteralPart = (MapLiteralPart)theEObject;
				T1 result = caseMapLiteralPart(mapLiteralPart);
				if (result == null) result = caseElement(mapLiteralPart);
				if (result == null) result = caseOclElement(mapLiteralPart);
				if (result == null) result = caseOclAny(mapLiteralPart);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 64:
			{
				MapType mapType = (MapType)theEObject;
				T1 result = caseMapType(mapType);
				if (result == null) result = caseIterableType(mapType);
				if (result == null) result = caseOclAny(mapType);
				if (result == null) result = caseDataType(mapType);
				if (result == null) result = caseClass(mapType);
				if (result == null) result = caseType(mapType);
				if (result == null) result = caseNamespace(mapType);
				if (result == null) result = caseTemplateableElement(mapType);
				if (result == null) result = caseOclType(mapType);
				if (result == null) result = caseNamedElement(mapType);
				if (result == null) result = caseElement(mapType);
				if (result == null) result = caseOclElement(mapType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 65:
			{
				MessageExp messageExp = (MessageExp)theEObject;
				T1 result = caseMessageExp(messageExp);
				if (result == null) result = caseOCLExpression(messageExp);
				if (result == null) result = caseTypedElement(messageExp);
				if (result == null) result = caseNamedElement(messageExp);
				if (result == null) result = caseElement(messageExp);
				if (result == null) result = caseOclElement(messageExp);
				if (result == null) result = caseOclAny(messageExp);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 66:
			{
				MessageType messageType = (MessageType)theEObject;
				T1 result = caseMessageType(messageType);
				if (result == null) result = caseClass(messageType);
				if (result == null) result = caseType(messageType);
				if (result == null) result = caseNamespace(messageType);
				if (result == null) result = caseTemplateableElement(messageType);
				if (result == null) result = caseOclAny(messageType);
				if (result == null) result = caseOclType(messageType);
				if (result == null) result = caseNamedElement(messageType);
				if (result == null) result = caseElement(messageType);
				if (result == null) result = caseOclElement(messageType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 67:
			{
				Model model = (Model)theEObject;
				T1 result = caseModel(model);
				if (result == null) result = caseNamespace(model);
				if (result == null) result = caseNamedElement(model);
				if (result == null) result = caseElement(model);
				if (result == null) result = caseOclElement(model);
				if (result == null) result = caseOclAny(model);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 68:
			{
				MorePivotable morePivotable = (MorePivotable)theEObject;
				T1 result = caseMorePivotable(morePivotable);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 69:
			{
				Nameable nameable = (Nameable)theEObject;
				T1 result = caseNameable(nameable);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 70:
			{
				NamedElement namedElement = (NamedElement)theEObject;
				T1 result = caseNamedElement(namedElement);
				if (result == null) result = caseElement(namedElement);
				if (result == null) result = caseOclElement(namedElement);
				if (result == null) result = caseOclAny(namedElement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 71:
			{
				Namespace namespace = (Namespace)theEObject;
				T1 result = caseNamespace(namespace);
				if (result == null) result = caseNamedElement(namespace);
				if (result == null) result = caseElement(namespace);
				if (result == null) result = caseOclElement(namespace);
				if (result == null) result = caseOclAny(namespace);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 72:
			{
				NavigationCallExp navigationCallExp = (NavigationCallExp)theEObject;
				T1 result = caseNavigationCallExp(navigationCallExp);
				if (result == null) result = caseFeatureCallExp(navigationCallExp);
				if (result == null) result = caseCallExp(navigationCallExp);
				if (result == null) result = caseOCLExpression(navigationCallExp);
				if (result == null) result = caseTypedElement(navigationCallExp);
				if (result == null) result = caseNamedElement(navigationCallExp);
				if (result == null) result = caseElement(navigationCallExp);
				if (result == null) result = caseOclElement(navigationCallExp);
				if (result == null) result = caseOclAny(navigationCallExp);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 73:
			{
				NullLiteralExp nullLiteralExp = (NullLiteralExp)theEObject;
				T1 result = caseNullLiteralExp(nullLiteralExp);
				if (result == null) result = casePrimitiveLiteralExp(nullLiteralExp);
				if (result == null) result = caseLiteralExp(nullLiteralExp);
				if (result == null) result = caseOCLExpression(nullLiteralExp);
				if (result == null) result = caseTypedElement(nullLiteralExp);
				if (result == null) result = caseNamedElement(nullLiteralExp);
				if (result == null) result = caseElement(nullLiteralExp);
				if (result == null) result = caseOclElement(nullLiteralExp);
				if (result == null) result = caseOclAny(nullLiteralExp);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 74:
			{
				NumericLiteralExp numericLiteralExp = (NumericLiteralExp)theEObject;
				T1 result = caseNumericLiteralExp(numericLiteralExp);
				if (result == null) result = casePrimitiveLiteralExp(numericLiteralExp);
				if (result == null) result = caseLiteralExp(numericLiteralExp);
				if (result == null) result = caseOCLExpression(numericLiteralExp);
				if (result == null) result = caseTypedElement(numericLiteralExp);
				if (result == null) result = caseNamedElement(numericLiteralExp);
				if (result == null) result = caseElement(numericLiteralExp);
				if (result == null) result = caseOclElement(numericLiteralExp);
				if (result == null) result = caseOclAny(numericLiteralExp);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 75:
			{
				OCLExpression oclExpression = (OCLExpression)theEObject;
				T1 result = caseOCLExpression(oclExpression);
				if (result == null) result = caseTypedElement(oclExpression);
				if (result == null) result = caseNamedElement(oclExpression);
				if (result == null) result = caseElement(oclExpression);
				if (result == null) result = caseOclElement(oclExpression);
				if (result == null) result = caseOclAny(oclExpression);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 76:
			{
				Object oclAny = (Object)theEObject;
				T1 result = caseOclAny(oclAny);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 77:
			{
				OclComparable oclComparable = (OclComparable)theEObject;
				T1 result = caseOclComparable(oclComparable);
				if (result == null) result = caseOclAny(oclComparable);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 78:
			{
				OclElement oclElement = (OclElement)theEObject;
				T1 result = caseOclElement(oclElement);
				if (result == null) result = caseOclAny(oclElement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 79:
			{
				OclEnumeration oclEnumeration = (OclEnumeration)theEObject;
				T1 result = caseOclEnumeration(oclEnumeration);
				if (result == null) result = caseOclType(oclEnumeration);
				if (result == null) result = caseOclElement(oclEnumeration);
				if (result == null) result = caseOclAny(oclEnumeration);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 80:
			{
				org.eclipse.ocl.pivot.InvalidType oclInvalid = (org.eclipse.ocl.pivot.InvalidType)theEObject;
				T1 result = caseOclInvalid(oclInvalid);
				if (result == null) result = caseOclAny(oclInvalid);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 81:
			{
				OclLambda oclLambda = (OclLambda)theEObject;
				T1 result = caseOclLambda(oclLambda);
				if (result == null) result = caseOclAny(oclLambda);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 82:
			{
				OclMessage oclMessage = (OclMessage)theEObject;
				T1 result = caseOclMessage(oclMessage);
				if (result == null) result = caseOclAny(oclMessage);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 83:
			{
				SelfType oclSelf = (SelfType)theEObject;
				T1 result = caseOclSelf(oclSelf);
				if (result == null) result = caseOclAny(oclSelf);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 84:
			{
				OclState oclState = (OclState)theEObject;
				T1 result = caseOclState(oclState);
				if (result == null) result = caseOclAny(oclState);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 85:
			{
				OclStereotype oclStereotype = (OclStereotype)theEObject;
				T1 result = caseOclStereotype(oclStereotype);
				if (result == null) result = caseOclType(oclStereotype);
				if (result == null) result = caseOclElement(oclStereotype);
				if (result == null) result = caseOclAny(oclStereotype);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 86:
			{
				OclSummable oclSummable = (OclSummable)theEObject;
				T1 result = caseOclSummable(oclSummable);
				if (result == null) result = caseOclAny(oclSummable);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 87:
			{
				OclTuple oclTuple = (OclTuple)theEObject;
				T1 result = caseOclTuple(oclTuple);
				if (result == null) result = caseOclAny(oclTuple);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 88:
			{
				OclType oclType = (OclType)theEObject;
				T1 result = caseOclType(oclType);
				if (result == null) result = caseOclElement(oclType);
				if (result == null) result = caseOclAny(oclType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 89:
			{
				VoidType oclVoid = (VoidType)theEObject;
				T1 result = caseOclVoid(oclVoid);
				if (result == null) result = caseOclAny(oclVoid);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 90:
			{
				Operation operation = (Operation)theEObject;
				T1 result = caseOperation(operation);
				if (result == null) result = caseFeature(operation);
				if (result == null) result = caseNamespace(operation);
				if (result == null) result = caseTemplateableElement(operation);
				if (result == null) result = caseTypedElement(operation);
				if (result == null) result = caseNamedElement(operation);
				if (result == null) result = caseElement(operation);
				if (result == null) result = caseOclElement(operation);
				if (result == null) result = caseOclAny(operation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 91:
			{
				OperationCallExp operationCallExp = (OperationCallExp)theEObject;
				T1 result = caseOperationCallExp(operationCallExp);
				if (result == null) result = caseFeatureCallExp(operationCallExp);
				if (result == null) result = caseReferringElement(operationCallExp);
				if (result == null) result = caseCallExp(operationCallExp);
				if (result == null) result = caseOCLExpression(operationCallExp);
				if (result == null) result = caseTypedElement(operationCallExp);
				if (result == null) result = caseNamedElement(operationCallExp);
				if (result == null) result = caseElement(operationCallExp);
				if (result == null) result = caseOclElement(operationCallExp);
				if (result == null) result = caseOclAny(operationCallExp);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 92:
			{
				OppositePropertyCallExp oppositePropertyCallExp = (OppositePropertyCallExp)theEObject;
				T1 result = caseOppositePropertyCallExp(oppositePropertyCallExp);
				if (result == null) result = caseNavigationCallExp(oppositePropertyCallExp);
				if (result == null) result = caseFeatureCallExp(oppositePropertyCallExp);
				if (result == null) result = caseCallExp(oppositePropertyCallExp);
				if (result == null) result = caseOCLExpression(oppositePropertyCallExp);
				if (result == null) result = caseTypedElement(oppositePropertyCallExp);
				if (result == null) result = caseNamedElement(oppositePropertyCallExp);
				if (result == null) result = caseElement(oppositePropertyCallExp);
				if (result == null) result = caseOclElement(oppositePropertyCallExp);
				if (result == null) result = caseOclAny(oppositePropertyCallExp);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 93:
			{
				Collection<?> orderedCollection = (Collection<?>)theEObject;
				T1 result = caseOrderedCollection(orderedCollection);
				if (result == null) result = caseCollection(orderedCollection);
				if (result == null) result = caseOclAny(orderedCollection);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 94:
			{
				OrderedSet<?> orderedSet = (OrderedSet<?>)theEObject;
				T1 result = caseOrderedSet(orderedSet);
				if (result == null) result = caseOrderedCollection(orderedSet);
				if (result == null) result = caseUniqueCollection(orderedSet);
				if (result == null) result = caseCollection(orderedSet);
				if (result == null) result = caseOclAny(orderedSet);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 95:
			{
				OrderedSetType orderedSetType = (OrderedSetType)theEObject;
				T1 result = caseOrderedSetType(orderedSetType);
				if (result == null) result = caseCollectionType(orderedSetType);
				if (result == null) result = caseIterableType(orderedSetType);
				if (result == null) result = caseOclAny(orderedSetType);
				if (result == null) result = caseDataType(orderedSetType);
				if (result == null) result = caseClass(orderedSetType);
				if (result == null) result = caseType(orderedSetType);
				if (result == null) result = caseNamespace(orderedSetType);
				if (result == null) result = caseTemplateableElement(orderedSetType);
				if (result == null) result = caseOclType(orderedSetType);
				if (result == null) result = caseNamedElement(orderedSetType);
				if (result == null) result = caseElement(orderedSetType);
				if (result == null) result = caseOclElement(orderedSetType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 96:
			{
				OrphanCompletePackage orphanCompletePackage = (OrphanCompletePackage)theEObject;
				T1 result = caseOrphanCompletePackage(orphanCompletePackage);
				if (result == null) result = caseCompletePackage(orphanCompletePackage);
				if (result == null) result = caseNamedElement(orphanCompletePackage);
				if (result == null) result = caseElement(orphanCompletePackage);
				if (result == null) result = caseOclElement(orphanCompletePackage);
				if (result == null) result = caseOclAny(orphanCompletePackage);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 97:
			{
				org.eclipse.ocl.ocl.Package package_ = (org.eclipse.ocl.ocl.Package)theEObject;
				T1 result = casePackage(package_);
				if (result == null) result = caseNamespace(package_);
				if (result == null) result = caseNamedElement(package_);
				if (result == null) result = caseElement(package_);
				if (result == null) result = caseOclElement(package_);
				if (result == null) result = caseOclAny(package_);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 98:
			{
				Parameter parameter = (Parameter)theEObject;
				T1 result = caseParameter(parameter);
				if (result == null) result = caseVariableDeclaration(parameter);
				if (result == null) result = caseTypedElement(parameter);
				if (result == null) result = caseNamedElement(parameter);
				if (result == null) result = caseElement(parameter);
				if (result == null) result = caseOclElement(parameter);
				if (result == null) result = caseOclAny(parameter);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 99:
			{
				ParameterVariable parameterVariable = (ParameterVariable)theEObject;
				T1 result = caseParameterVariable(parameterVariable);
				if (result == null) result = caseVariable(parameterVariable);
				if (result == null) result = caseVariableDeclaration(parameterVariable);
				if (result == null) result = caseTypedElement(parameterVariable);
				if (result == null) result = caseNamedElement(parameterVariable);
				if (result == null) result = caseElement(parameterVariable);
				if (result == null) result = caseOclElement(parameterVariable);
				if (result == null) result = caseOclAny(parameterVariable);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 100:
			{
				Pivotable pivotable = (Pivotable)theEObject;
				T1 result = casePivotable(pivotable);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 101:
			{
				Precedence precedence = (Precedence)theEObject;
				T1 result = casePrecedence(precedence);
				if (result == null) result = caseNamedElement(precedence);
				if (result == null) result = caseElement(precedence);
				if (result == null) result = caseOclElement(precedence);
				if (result == null) result = caseOclAny(precedence);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 102:
			{
				PrimitiveCompletePackage primitiveCompletePackage = (PrimitiveCompletePackage)theEObject;
				T1 result = casePrimitiveCompletePackage(primitiveCompletePackage);
				if (result == null) result = caseCompletePackage(primitiveCompletePackage);
				if (result == null) result = caseNamedElement(primitiveCompletePackage);
				if (result == null) result = caseElement(primitiveCompletePackage);
				if (result == null) result = caseOclElement(primitiveCompletePackage);
				if (result == null) result = caseOclAny(primitiveCompletePackage);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 103:
			{
				PrimitiveLiteralExp primitiveLiteralExp = (PrimitiveLiteralExp)theEObject;
				T1 result = casePrimitiveLiteralExp(primitiveLiteralExp);
				if (result == null) result = caseLiteralExp(primitiveLiteralExp);
				if (result == null) result = caseOCLExpression(primitiveLiteralExp);
				if (result == null) result = caseTypedElement(primitiveLiteralExp);
				if (result == null) result = caseNamedElement(primitiveLiteralExp);
				if (result == null) result = caseElement(primitiveLiteralExp);
				if (result == null) result = caseOclElement(primitiveLiteralExp);
				if (result == null) result = caseOclAny(primitiveLiteralExp);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 104:
			{
				PrimitiveType primitiveType = (PrimitiveType)theEObject;
				T1 result = casePrimitiveType(primitiveType);
				if (result == null) result = caseDataType(primitiveType);
				if (result == null) result = caseClass(primitiveType);
				if (result == null) result = caseType(primitiveType);
				if (result == null) result = caseNamespace(primitiveType);
				if (result == null) result = caseTemplateableElement(primitiveType);
				if (result == null) result = caseOclAny(primitiveType);
				if (result == null) result = caseOclType(primitiveType);
				if (result == null) result = caseNamedElement(primitiveType);
				if (result == null) result = caseElement(primitiveType);
				if (result == null) result = caseOclElement(primitiveType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 105:
			{
				Profile profile = (Profile)theEObject;
				T1 result = caseProfile(profile);
				if (result == null) result = casePackage(profile);
				if (result == null) result = caseNamespace(profile);
				if (result == null) result = caseNamedElement(profile);
				if (result == null) result = caseElement(profile);
				if (result == null) result = caseOclElement(profile);
				if (result == null) result = caseOclAny(profile);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 106:
			{
				ProfileApplication profileApplication = (ProfileApplication)theEObject;
				T1 result = caseProfileApplication(profileApplication);
				if (result == null) result = caseElement(profileApplication);
				if (result == null) result = caseOclElement(profileApplication);
				if (result == null) result = caseOclAny(profileApplication);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 107:
			{
				Property property = (Property)theEObject;
				T1 result = caseProperty(property);
				if (result == null) result = caseFeature(property);
				if (result == null) result = caseTypedElement(property);
				if (result == null) result = caseNamedElement(property);
				if (result == null) result = caseElement(property);
				if (result == null) result = caseOclElement(property);
				if (result == null) result = caseOclAny(property);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 108:
			{
				PropertyCallExp propertyCallExp = (PropertyCallExp)theEObject;
				T1 result = casePropertyCallExp(propertyCallExp);
				if (result == null) result = caseNavigationCallExp(propertyCallExp);
				if (result == null) result = caseReferringElement(propertyCallExp);
				if (result == null) result = caseFeatureCallExp(propertyCallExp);
				if (result == null) result = caseCallExp(propertyCallExp);
				if (result == null) result = caseOCLExpression(propertyCallExp);
				if (result == null) result = caseTypedElement(propertyCallExp);
				if (result == null) result = caseNamedElement(propertyCallExp);
				if (result == null) result = caseElement(propertyCallExp);
				if (result == null) result = caseOclElement(propertyCallExp);
				if (result == null) result = caseOclAny(propertyCallExp);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 109:
			{
				Pseudostate pseudostate = (Pseudostate)theEObject;
				T1 result = casePseudostate(pseudostate);
				if (result == null) result = caseVertex(pseudostate);
				if (result == null) result = caseNamedElement(pseudostate);
				if (result == null) result = caseElement(pseudostate);
				if (result == null) result = caseOclElement(pseudostate);
				if (result == null) result = caseOclAny(pseudostate);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 110:
			{
				RealLiteralExp realLiteralExp = (RealLiteralExp)theEObject;
				T1 result = caseRealLiteralExp(realLiteralExp);
				if (result == null) result = caseNumericLiteralExp(realLiteralExp);
				if (result == null) result = casePrimitiveLiteralExp(realLiteralExp);
				if (result == null) result = caseLiteralExp(realLiteralExp);
				if (result == null) result = caseOCLExpression(realLiteralExp);
				if (result == null) result = caseTypedElement(realLiteralExp);
				if (result == null) result = caseNamedElement(realLiteralExp);
				if (result == null) result = caseElement(realLiteralExp);
				if (result == null) result = caseOclElement(realLiteralExp);
				if (result == null) result = caseOclAny(realLiteralExp);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 111:
			{
				ReferringElement referringElement = (ReferringElement)theEObject;
				T1 result = caseReferringElement(referringElement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 112:
			{
				Region region = (Region)theEObject;
				T1 result = caseRegion(region);
				if (result == null) result = caseNamespace(region);
				if (result == null) result = caseNamedElement(region);
				if (result == null) result = caseElement(region);
				if (result == null) result = caseOclElement(region);
				if (result == null) result = caseOclAny(region);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 113:
			{
				ResultVariable resultVariable = (ResultVariable)theEObject;
				T1 result = caseResultVariable(resultVariable);
				if (result == null) result = caseVariable(resultVariable);
				if (result == null) result = caseVariableDeclaration(resultVariable);
				if (result == null) result = caseTypedElement(resultVariable);
				if (result == null) result = caseNamedElement(resultVariable);
				if (result == null) result = caseElement(resultVariable);
				if (result == null) result = caseOclElement(resultVariable);
				if (result == null) result = caseOclAny(resultVariable);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 114:
			{
				org.eclipse.ocl.ocl.SelfType selfType = (org.eclipse.ocl.ocl.SelfType)theEObject;
				T1 result = caseSelfType(selfType);
				if (result == null) result = caseClass(selfType);
				if (result == null) result = caseType(selfType);
				if (result == null) result = caseNamespace(selfType);
				if (result == null) result = caseTemplateableElement(selfType);
				if (result == null) result = caseOclAny(selfType);
				if (result == null) result = caseOclType(selfType);
				if (result == null) result = caseNamedElement(selfType);
				if (result == null) result = caseElement(selfType);
				if (result == null) result = caseOclElement(selfType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 115:
			{
				SendSignalAction sendSignalAction = (SendSignalAction)theEObject;
				T1 result = caseSendSignalAction(sendSignalAction);
				if (result == null) result = caseNamedElement(sendSignalAction);
				if (result == null) result = caseElement(sendSignalAction);
				if (result == null) result = caseOclElement(sendSignalAction);
				if (result == null) result = caseOclAny(sendSignalAction);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 116:
			{
				List<?> sequence = (List<?>)theEObject;
				T1 result = caseSequence(sequence);
				if (result == null) result = caseOrderedCollection(sequence);
				if (result == null) result = caseCollection(sequence);
				if (result == null) result = caseOclAny(sequence);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 117:
			{
				SequenceType sequenceType = (SequenceType)theEObject;
				T1 result = caseSequenceType(sequenceType);
				if (result == null) result = caseCollectionType(sequenceType);
				if (result == null) result = caseIterableType(sequenceType);
				if (result == null) result = caseOclAny(sequenceType);
				if (result == null) result = caseDataType(sequenceType);
				if (result == null) result = caseClass(sequenceType);
				if (result == null) result = caseType(sequenceType);
				if (result == null) result = caseNamespace(sequenceType);
				if (result == null) result = caseTemplateableElement(sequenceType);
				if (result == null) result = caseOclType(sequenceType);
				if (result == null) result = caseNamedElement(sequenceType);
				if (result == null) result = caseElement(sequenceType);
				if (result == null) result = caseOclElement(sequenceType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 118:
			{
				Set<?> set = (Set<?>)theEObject;
				T1 result = caseSet(set);
				if (result == null) result = caseUniqueCollection(set);
				if (result == null) result = caseCollection(set);
				if (result == null) result = caseOclAny(set);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 119:
			{
				SetType setType = (SetType)theEObject;
				T1 result = caseSetType(setType);
				if (result == null) result = caseCollectionType(setType);
				if (result == null) result = caseIterableType(setType);
				if (result == null) result = caseOclAny(setType);
				if (result == null) result = caseDataType(setType);
				if (result == null) result = caseClass(setType);
				if (result == null) result = caseType(setType);
				if (result == null) result = caseNamespace(setType);
				if (result == null) result = caseTemplateableElement(setType);
				if (result == null) result = caseOclType(setType);
				if (result == null) result = caseNamedElement(setType);
				if (result == null) result = caseElement(setType);
				if (result == null) result = caseOclElement(setType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 120:
			{
				ShadowExp shadowExp = (ShadowExp)theEObject;
				T1 result = caseShadowExp(shadowExp);
				if (result == null) result = caseOCLExpression(shadowExp);
				if (result == null) result = caseTypedElement(shadowExp);
				if (result == null) result = caseNamedElement(shadowExp);
				if (result == null) result = caseElement(shadowExp);
				if (result == null) result = caseOclElement(shadowExp);
				if (result == null) result = caseOclAny(shadowExp);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 121:
			{
				ShadowPart shadowPart = (ShadowPart)theEObject;
				T1 result = caseShadowPart(shadowPart);
				if (result == null) result = caseTypedElement(shadowPart);
				if (result == null) result = caseNamedElement(shadowPart);
				if (result == null) result = caseElement(shadowPart);
				if (result == null) result = caseOclElement(shadowPart);
				if (result == null) result = caseOclAny(shadowPart);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 122:
			{
				Signal signal = (Signal)theEObject;
				T1 result = caseSignal(signal);
				if (result == null) result = caseClass(signal);
				if (result == null) result = caseType(signal);
				if (result == null) result = caseNamespace(signal);
				if (result == null) result = caseTemplateableElement(signal);
				if (result == null) result = caseOclAny(signal);
				if (result == null) result = caseOclType(signal);
				if (result == null) result = caseNamedElement(signal);
				if (result == null) result = caseElement(signal);
				if (result == null) result = caseOclElement(signal);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 123:
			{
				Slot slot = (Slot)theEObject;
				T1 result = caseSlot(slot);
				if (result == null) result = caseElement(slot);
				if (result == null) result = caseOclElement(slot);
				if (result == null) result = caseOclAny(slot);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 124:
			{
				StandardLibrary standardLibrary = (StandardLibrary)theEObject;
				T1 result = caseStandardLibrary(standardLibrary);
				if (result == null) result = caseElement(standardLibrary);
				if (result == null) result = caseOclElement(standardLibrary);
				if (result == null) result = caseOclAny(standardLibrary);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 125:
			{
				State state = (State)theEObject;
				T1 result = caseState(state);
				if (result == null) result = caseOclState(state);
				if (result == null) result = caseNamespace(state);
				if (result == null) result = caseVertex(state);
				if (result == null) result = caseNamedElement(state);
				if (result == null) result = caseElement(state);
				if (result == null) result = caseOclElement(state);
				if (result == null) result = caseOclAny(state);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 126:
			{
				StateExp stateExp = (StateExp)theEObject;
				T1 result = caseStateExp(stateExp);
				if (result == null) result = caseOCLExpression(stateExp);
				if (result == null) result = caseTypedElement(stateExp);
				if (result == null) result = caseNamedElement(stateExp);
				if (result == null) result = caseElement(stateExp);
				if (result == null) result = caseOclElement(stateExp);
				if (result == null) result = caseOclAny(stateExp);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 127:
			{
				StateMachine stateMachine = (StateMachine)theEObject;
				T1 result = caseStateMachine(stateMachine);
				if (result == null) result = caseBehavior(stateMachine);
				if (result == null) result = caseClass(stateMachine);
				if (result == null) result = caseType(stateMachine);
				if (result == null) result = caseNamespace(stateMachine);
				if (result == null) result = caseTemplateableElement(stateMachine);
				if (result == null) result = caseOclAny(stateMachine);
				if (result == null) result = caseOclType(stateMachine);
				if (result == null) result = caseNamedElement(stateMachine);
				if (result == null) result = caseElement(stateMachine);
				if (result == null) result = caseOclElement(stateMachine);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 128:
			{
				Stereotype stereotype = (Stereotype)theEObject;
				T1 result = caseStereotype(stereotype);
				if (result == null) result = caseOclStereotype(stereotype);
				if (result == null) result = caseClass(stereotype);
				if (result == null) result = caseType(stereotype);
				if (result == null) result = caseNamespace(stereotype);
				if (result == null) result = caseTemplateableElement(stereotype);
				if (result == null) result = caseOclType(stereotype);
				if (result == null) result = caseNamedElement(stereotype);
				if (result == null) result = caseOclAny(stereotype);
				if (result == null) result = caseElement(stereotype);
				if (result == null) result = caseOclElement(stereotype);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 129:
			{
				StereotypeExtender stereotypeExtender = (StereotypeExtender)theEObject;
				T1 result = caseStereotypeExtender(stereotypeExtender);
				if (result == null) result = caseElement(stereotypeExtender);
				if (result == null) result = caseOclElement(stereotypeExtender);
				if (result == null) result = caseOclAny(stereotypeExtender);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 130:
			{
				StringLiteralExp stringLiteralExp = (StringLiteralExp)theEObject;
				T1 result = caseStringLiteralExp(stringLiteralExp);
				if (result == null) result = casePrimitiveLiteralExp(stringLiteralExp);
				if (result == null) result = caseLiteralExp(stringLiteralExp);
				if (result == null) result = caseOCLExpression(stringLiteralExp);
				if (result == null) result = caseTypedElement(stringLiteralExp);
				if (result == null) result = caseNamedElement(stringLiteralExp);
				if (result == null) result = caseElement(stringLiteralExp);
				if (result == null) result = caseOclElement(stringLiteralExp);
				if (result == null) result = caseOclAny(stringLiteralExp);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 131:
			{
				TemplateBinding templateBinding = (TemplateBinding)theEObject;
				T1 result = caseTemplateBinding(templateBinding);
				if (result == null) result = caseElement(templateBinding);
				if (result == null) result = caseOclElement(templateBinding);
				if (result == null) result = caseOclAny(templateBinding);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 132:
			{
				TemplateParameter templateParameter = (TemplateParameter)theEObject;
				T1 result = caseTemplateParameter(templateParameter);
				if (result == null) result = caseType(templateParameter);
				if (result == null) result = caseOclType(templateParameter);
				if (result == null) result = caseNamedElement(templateParameter);
				if (result == null) result = caseElement(templateParameter);
				if (result == null) result = caseOclElement(templateParameter);
				if (result == null) result = caseOclAny(templateParameter);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 133:
			{
				TemplateParameterSubstitution templateParameterSubstitution = (TemplateParameterSubstitution)theEObject;
				T1 result = caseTemplateParameterSubstitution(templateParameterSubstitution);
				if (result == null) result = caseElement(templateParameterSubstitution);
				if (result == null) result = caseOclElement(templateParameterSubstitution);
				if (result == null) result = caseOclAny(templateParameterSubstitution);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 134:
			{
				TemplateSignature templateSignature = (TemplateSignature)theEObject;
				T1 result = caseTemplateSignature(templateSignature);
				if (result == null) result = caseElement(templateSignature);
				if (result == null) result = caseOclElement(templateSignature);
				if (result == null) result = caseOclAny(templateSignature);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 135:
			{
				TemplateableElement templateableElement = (TemplateableElement)theEObject;
				T1 result = caseTemplateableElement(templateableElement);
				if (result == null) result = caseElement(templateableElement);
				if (result == null) result = caseOclElement(templateableElement);
				if (result == null) result = caseOclAny(templateableElement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 136:
			{
				Transition transition = (Transition)theEObject;
				T1 result = caseTransition(transition);
				if (result == null) result = caseNamespace(transition);
				if (result == null) result = caseNamedElement(transition);
				if (result == null) result = caseElement(transition);
				if (result == null) result = caseOclElement(transition);
				if (result == null) result = caseOclAny(transition);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 137:
			{
				Trigger trigger = (Trigger)theEObject;
				T1 result = caseTrigger(trigger);
				if (result == null) result = caseNamedElement(trigger);
				if (result == null) result = caseElement(trigger);
				if (result == null) result = caseOclElement(trigger);
				if (result == null) result = caseOclAny(trigger);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 138:
			{
				TupleLiteralExp tupleLiteralExp = (TupleLiteralExp)theEObject;
				T1 result = caseTupleLiteralExp(tupleLiteralExp);
				if (result == null) result = caseLiteralExp(tupleLiteralExp);
				if (result == null) result = caseOCLExpression(tupleLiteralExp);
				if (result == null) result = caseTypedElement(tupleLiteralExp);
				if (result == null) result = caseNamedElement(tupleLiteralExp);
				if (result == null) result = caseElement(tupleLiteralExp);
				if (result == null) result = caseOclElement(tupleLiteralExp);
				if (result == null) result = caseOclAny(tupleLiteralExp);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 139:
			{
				TupleLiteralPart tupleLiteralPart = (TupleLiteralPart)theEObject;
				T1 result = caseTupleLiteralPart(tupleLiteralPart);
				if (result == null) result = caseVariableDeclaration(tupleLiteralPart);
				if (result == null) result = caseTypedElement(tupleLiteralPart);
				if (result == null) result = caseNamedElement(tupleLiteralPart);
				if (result == null) result = caseElement(tupleLiteralPart);
				if (result == null) result = caseOclElement(tupleLiteralPart);
				if (result == null) result = caseOclAny(tupleLiteralPart);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 140:
			{
				TupleType tupleType = (TupleType)theEObject;
				T1 result = caseTupleType(tupleType);
				if (result == null) result = caseDataType(tupleType);
				if (result == null) result = caseClass(tupleType);
				if (result == null) result = caseType(tupleType);
				if (result == null) result = caseNamespace(tupleType);
				if (result == null) result = caseTemplateableElement(tupleType);
				if (result == null) result = caseOclAny(tupleType);
				if (result == null) result = caseOclType(tupleType);
				if (result == null) result = caseNamedElement(tupleType);
				if (result == null) result = caseElement(tupleType);
				if (result == null) result = caseOclElement(tupleType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 141:
			{
				Type type = (Type)theEObject;
				T1 result = caseType(type);
				if (result == null) result = caseOclType(type);
				if (result == null) result = caseNamedElement(type);
				if (result == null) result = caseElement(type);
				if (result == null) result = caseOclElement(type);
				if (result == null) result = caseOclAny(type);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 142:
			{
				TypeExp typeExp = (TypeExp)theEObject;
				T1 result = caseTypeExp(typeExp);
				if (result == null) result = caseOCLExpression(typeExp);
				if (result == null) result = caseReferringElement(typeExp);
				if (result == null) result = caseTypedElement(typeExp);
				if (result == null) result = caseNamedElement(typeExp);
				if (result == null) result = caseElement(typeExp);
				if (result == null) result = caseOclElement(typeExp);
				if (result == null) result = caseOclAny(typeExp);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 143:
			{
				TypedElement typedElement = (TypedElement)theEObject;
				T1 result = caseTypedElement(typedElement);
				if (result == null) result = caseNamedElement(typedElement);
				if (result == null) result = caseElement(typedElement);
				if (result == null) result = caseOclElement(typedElement);
				if (result == null) result = caseOclAny(typedElement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 144:
			{
				Collection<?> uniqueCollection = (Collection<?>)theEObject;
				T1 result = caseUniqueCollection(uniqueCollection);
				if (result == null) result = caseCollection(uniqueCollection);
				if (result == null) result = caseOclAny(uniqueCollection);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 145:
			{
				UnlimitedNaturalLiteralExp unlimitedNaturalLiteralExp = (UnlimitedNaturalLiteralExp)theEObject;
				T1 result = caseUnlimitedNaturalLiteralExp(unlimitedNaturalLiteralExp);
				if (result == null) result = caseNumericLiteralExp(unlimitedNaturalLiteralExp);
				if (result == null) result = casePrimitiveLiteralExp(unlimitedNaturalLiteralExp);
				if (result == null) result = caseLiteralExp(unlimitedNaturalLiteralExp);
				if (result == null) result = caseOCLExpression(unlimitedNaturalLiteralExp);
				if (result == null) result = caseTypedElement(unlimitedNaturalLiteralExp);
				if (result == null) result = caseNamedElement(unlimitedNaturalLiteralExp);
				if (result == null) result = caseElement(unlimitedNaturalLiteralExp);
				if (result == null) result = caseOclElement(unlimitedNaturalLiteralExp);
				if (result == null) result = caseOclAny(unlimitedNaturalLiteralExp);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 146:
			{
				UnspecifiedValueExp unspecifiedValueExp = (UnspecifiedValueExp)theEObject;
				T1 result = caseUnspecifiedValueExp(unspecifiedValueExp);
				if (result == null) result = caseOCLExpression(unspecifiedValueExp);
				if (result == null) result = caseTypedElement(unspecifiedValueExp);
				if (result == null) result = caseNamedElement(unspecifiedValueExp);
				if (result == null) result = caseElement(unspecifiedValueExp);
				if (result == null) result = caseOclElement(unspecifiedValueExp);
				if (result == null) result = caseOclAny(unspecifiedValueExp);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 147:
			{
				ValueSpecification valueSpecification = (ValueSpecification)theEObject;
				T1 result = caseValueSpecification(valueSpecification);
				if (result == null) result = caseTypedElement(valueSpecification);
				if (result == null) result = caseNamedElement(valueSpecification);
				if (result == null) result = caseElement(valueSpecification);
				if (result == null) result = caseOclElement(valueSpecification);
				if (result == null) result = caseOclAny(valueSpecification);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 148:
			{
				Variable variable = (Variable)theEObject;
				T1 result = caseVariable(variable);
				if (result == null) result = caseVariableDeclaration(variable);
				if (result == null) result = caseTypedElement(variable);
				if (result == null) result = caseNamedElement(variable);
				if (result == null) result = caseElement(variable);
				if (result == null) result = caseOclElement(variable);
				if (result == null) result = caseOclAny(variable);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 149:
			{
				VariableDeclaration variableDeclaration = (VariableDeclaration)theEObject;
				T1 result = caseVariableDeclaration(variableDeclaration);
				if (result == null) result = caseTypedElement(variableDeclaration);
				if (result == null) result = caseNamedElement(variableDeclaration);
				if (result == null) result = caseElement(variableDeclaration);
				if (result == null) result = caseOclElement(variableDeclaration);
				if (result == null) result = caseOclAny(variableDeclaration);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 150:
			{
				VariableExp variableExp = (VariableExp)theEObject;
				T1 result = caseVariableExp(variableExp);
				if (result == null) result = caseOCLExpression(variableExp);
				if (result == null) result = caseReferringElement(variableExp);
				if (result == null) result = caseTypedElement(variableExp);
				if (result == null) result = caseNamedElement(variableExp);
				if (result == null) result = caseElement(variableExp);
				if (result == null) result = caseOclElement(variableExp);
				if (result == null) result = caseOclAny(variableExp);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 151:
			{
				Vertex vertex = (Vertex)theEObject;
				T1 result = caseVertex(vertex);
				if (result == null) result = caseNamedElement(vertex);
				if (result == null) result = caseElement(vertex);
				if (result == null) result = caseOclElement(vertex);
				if (result == null) result = caseOclAny(vertex);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 152:
			{
				Visitable visitable = (Visitable)theEObject;
				T1 result = caseVisitable(visitable);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 153:
			{
				org.eclipse.ocl.ocl.VoidType voidType = (org.eclipse.ocl.ocl.VoidType)theEObject;
				T1 result = caseVoidType(voidType);
				if (result == null) result = caseClass(voidType);
				if (result == null) result = caseOclAny(voidType);
				if (result == null) result = caseType(voidType);
				if (result == null) result = caseNamespace(voidType);
				if (result == null) result = caseTemplateableElement(voidType);
				if (result == null) result = caseOclType(voidType);
				if (result == null) result = caseNamedElement(voidType);
				if (result == null) result = caseElement(voidType);
				if (result == null) result = caseOclElement(voidType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 154:
			{
				WildcardType wildcardType = (WildcardType)theEObject;
				T1 result = caseWildcardType(wildcardType);
				if (result == null) result = caseClass(wildcardType);
				if (result == null) result = caseType(wildcardType);
				if (result == null) result = caseNamespace(wildcardType);
				if (result == null) result = caseTemplateableElement(wildcardType);
				if (result == null) result = caseOclAny(wildcardType);
				if (result == null) result = caseOclType(wildcardType);
				if (result == null) result = caseNamedElement(wildcardType);
				if (result == null) result = caseElement(wildcardType);
				if (result == null) result = caseOclElement(wildcardType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Annotation</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Annotation</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseAnnotation(Annotation object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Any Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Any Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseAnyType(AnyType object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Association Class</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Association Class</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseAssociationClass(AssociationClass object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Association Class Call Exp</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Association Class Call Exp</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseAssociationClassCallExp(AssociationClassCallExp object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Bag</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Bag</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public <T> T1 caseBag(Bag<T> object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Bag Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Bag Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseBagType(BagType object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Behavior</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Behavior</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseBehavior(Behavior object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Boolean Literal Exp</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Boolean Literal Exp</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseBooleanLiteralExp(BooleanLiteralExp object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Boolean Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Boolean Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseBooleanType(BooleanType object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Call Exp</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Call Exp</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseCallExp(CallExp object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Call Operation Action</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Call Operation Action</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseCallOperationAction(CallOperationAction object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Class</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Class</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseClass(org.eclipse.ocl.ocl.Class object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Collection</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Collection</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public <T> T1 caseCollection(Collection<T> object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Collection Item</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Collection Item</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseCollectionItem(CollectionItem object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Collection Literal Exp</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Collection Literal Exp</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseCollectionLiteralExp(CollectionLiteralExp object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Collection Literal Part</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Collection Literal Part</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseCollectionLiteralPart(CollectionLiteralPart object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Collection Range</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Collection Range</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseCollectionRange(CollectionRange object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Collection Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Collection Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseCollectionType(CollectionType object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Comment</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Comment</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseComment(Comment object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Complete Class</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Complete Class</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseCompleteClass(CompleteClass object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Complete Environment</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Complete Environment</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseCompleteEnvironment(CompleteEnvironment object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Complete Model</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Complete Model</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseCompleteModel(CompleteModel object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Complete Package</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Complete Package</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseCompletePackage(CompletePackage object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Connection Point Reference</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Connection Point Reference</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseConnectionPointReference(ConnectionPointReference object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Constraint</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Constraint</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseConstraint(Constraint object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Data Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Data Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseDataType(DataType object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Detail</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Detail</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseDetail(Detail object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Dynamic Behavior</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Dynamic Behavior</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseDynamicBehavior(DynamicBehavior object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Dynamic Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Dynamic Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseDynamicElement(DynamicElement object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Dynamic Property</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Dynamic Property</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseDynamicProperty(DynamicProperty object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Dynamic Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Dynamic Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseDynamicType(DynamicType object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Dynamic Value Specification</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Dynamic Value Specification</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseDynamicValueSpecification(DynamicValueSpecification object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseElement(Element object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Element Extension</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Element Extension</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseElementExtension(ElementExtension object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Element Literal Exp</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Element Literal Exp</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseElementLiteralExp(ElementLiteralExp object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Enum Literal Exp</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Enum Literal Exp</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseEnumLiteralExp(EnumLiteralExp object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Enumeration</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Enumeration</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseEnumeration(Enumeration object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Enumeration Literal</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Enumeration Literal</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseEnumerationLiteral(EnumerationLiteral object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Expression In OCL</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Expression In OCL</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseExpressionInOCL(ExpressionInOCL object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Feature</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Feature</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseFeature(Feature object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Feature Call Exp</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Feature Call Exp</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseFeatureCallExp(FeatureCallExp object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Final State</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Final State</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseFinalState(FinalState object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>If Exp</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>If Exp</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseIfExp(IfExp object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Import</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Import</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseImport(Import object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Instance Specification</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Instance Specification</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseInstanceSpecification(InstanceSpecification object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Integer Literal Exp</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Integer Literal Exp</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseIntegerLiteralExp(IntegerLiteralExp object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Invalid Literal Exp</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Invalid Literal Exp</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseInvalidLiteralExp(InvalidLiteralExp object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Invalid Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Invalid Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseInvalidType(InvalidType object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Iterable Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Iterable Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseIterableType(IterableType object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Iterate Exp</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Iterate Exp</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseIterateExp(IterateExp object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Iteration</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Iteration</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseIteration(Iteration object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Iterator Exp</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Iterator Exp</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseIteratorExp(IteratorExp object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Iterator Variable</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Iterator Variable</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseIteratorVariable(IteratorVariable object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Java Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Java Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseJavaType(JavaType object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Lambda Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Lambda Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseLambdaType(LambdaType object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Language Expression</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Language Expression</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseLanguageExpression(LanguageExpression object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Let Exp</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Let Exp</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseLetExp(LetExp object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Let Variable</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Let Variable</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseLetVariable(LetVariable object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Library</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Library</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseLibrary(Library object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Literal Exp</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Literal Exp</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseLiteralExp(LiteralExp object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Loop Exp</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Loop Exp</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseLoopExp(LoopExp object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Map</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Map</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public <K, V> T1 caseMap(Map<K, V> object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Map Literal Exp</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Map Literal Exp</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseMapLiteralExp(MapLiteralExp object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Map Literal Part</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Map Literal Part</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseMapLiteralPart(MapLiteralPart object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Map Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Map Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseMapType(MapType object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Message Exp</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Message Exp</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseMessageExp(MessageExp object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Message Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Message Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseMessageType(MessageType object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Model</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Model</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseModel(Model object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>More Pivotable</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>More Pivotable</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseMorePivotable(MorePivotable object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Nameable</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Nameable</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseNameable(Nameable object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Named Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Named Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseNamedElement(NamedElement object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Namespace</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Namespace</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseNamespace(Namespace object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Navigation Call Exp</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Navigation Call Exp</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseNavigationCallExp(NavigationCallExp object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Null Literal Exp</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Null Literal Exp</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseNullLiteralExp(NullLiteralExp object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Numeric Literal Exp</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Numeric Literal Exp</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseNumericLiteralExp(NumericLiteralExp object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>OCL Expression</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>OCL Expression</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseOCLExpression(OCLExpression object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Ocl Any</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Ocl Any</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseOclAny(Object object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Ocl Comparable</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Ocl Comparable</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseOclComparable(OclComparable object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Ocl Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Ocl Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseOclElement(OclElement object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Ocl Enumeration</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Ocl Enumeration</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseOclEnumeration(OclEnumeration object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Ocl Invalid</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Ocl Invalid</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseOclInvalid(org.eclipse.ocl.pivot.InvalidType object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Ocl Lambda</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Ocl Lambda</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseOclLambda(OclLambda object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Ocl Message</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Ocl Message</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseOclMessage(OclMessage object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Ocl Self</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Ocl Self</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseOclSelf(SelfType object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Ocl State</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Ocl State</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseOclState(OclState object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Ocl Stereotype</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Ocl Stereotype</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseOclStereotype(OclStereotype object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Ocl Summable</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Ocl Summable</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseOclSummable(OclSummable object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Ocl Tuple</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Ocl Tuple</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseOclTuple(OclTuple object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Ocl Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Ocl Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseOclType(OclType object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Ocl Void</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Ocl Void</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseOclVoid(VoidType object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Operation</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Operation</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseOperation(Operation object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Operation Call Exp</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Operation Call Exp</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseOperationCallExp(OperationCallExp object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Opposite Property Call Exp</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Opposite Property Call Exp</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseOppositePropertyCallExp(OppositePropertyCallExp object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Ordered Collection</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Ordered Collection</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public <T> T1 caseOrderedCollection(Collection<T> object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Ordered Set</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Ordered Set</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public <T> T1 caseOrderedSet(OrderedSet<T> object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Ordered Set Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Ordered Set Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseOrderedSetType(OrderedSetType object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Orphan Complete Package</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Orphan Complete Package</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseOrphanCompletePackage(OrphanCompletePackage object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Package</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Package</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 casePackage(org.eclipse.ocl.ocl.Package object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Parameter</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Parameter</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseParameter(Parameter object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Parameter Variable</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Parameter Variable</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseParameterVariable(ParameterVariable object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Pivotable</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Pivotable</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 casePivotable(Pivotable object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Precedence</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Precedence</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 casePrecedence(Precedence object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Primitive Complete Package</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Primitive Complete Package</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 casePrimitiveCompletePackage(PrimitiveCompletePackage object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Primitive Literal Exp</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Primitive Literal Exp</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 casePrimitiveLiteralExp(PrimitiveLiteralExp object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Primitive Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Primitive Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 casePrimitiveType(PrimitiveType object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Profile</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Profile</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseProfile(Profile object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Profile Application</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Profile Application</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseProfileApplication(ProfileApplication object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Property</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Property</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseProperty(Property object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Property Call Exp</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Property Call Exp</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 casePropertyCallExp(PropertyCallExp object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Pseudostate</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Pseudostate</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 casePseudostate(Pseudostate object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Real Literal Exp</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Real Literal Exp</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseRealLiteralExp(RealLiteralExp object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Referring Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Referring Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseReferringElement(ReferringElement object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Region</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Region</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseRegion(Region object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Result Variable</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Result Variable</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseResultVariable(ResultVariable object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Self Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Self Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseSelfType(org.eclipse.ocl.ocl.SelfType object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Send Signal Action</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Send Signal Action</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseSendSignalAction(SendSignalAction object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Sequence</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Sequence</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public <T> T1 caseSequence(List<T> object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Sequence Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Sequence Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseSequenceType(SequenceType object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Set</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Set</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public <T> T1 caseSet(Set<T> object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Set Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Set Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseSetType(SetType object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Shadow Exp</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Shadow Exp</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseShadowExp(ShadowExp object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Shadow Part</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Shadow Part</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseShadowPart(ShadowPart object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Signal</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Signal</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseSignal(Signal object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Slot</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Slot</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseSlot(Slot object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Standard Library</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Standard Library</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseStandardLibrary(StandardLibrary object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>State</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>State</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseState(State object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>State Exp</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>State Exp</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseStateExp(StateExp object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>State Machine</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>State Machine</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseStateMachine(StateMachine object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Stereotype</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Stereotype</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseStereotype(Stereotype object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Stereotype Extender</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Stereotype Extender</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseStereotypeExtender(StereotypeExtender object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>String Literal Exp</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>String Literal Exp</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseStringLiteralExp(StringLiteralExp object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Template Binding</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Template Binding</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseTemplateBinding(TemplateBinding object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Template Parameter</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Template Parameter</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseTemplateParameter(TemplateParameter object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Template Parameter Substitution</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Template Parameter Substitution</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseTemplateParameterSubstitution(TemplateParameterSubstitution object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Template Signature</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Template Signature</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseTemplateSignature(TemplateSignature object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Templateable Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Templateable Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseTemplateableElement(TemplateableElement object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Transition</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Transition</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseTransition(Transition object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Trigger</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Trigger</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseTrigger(Trigger object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Tuple Literal Exp</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Tuple Literal Exp</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseTupleLiteralExp(TupleLiteralExp object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Tuple Literal Part</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Tuple Literal Part</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseTupleLiteralPart(TupleLiteralPart object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Tuple Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Tuple Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseTupleType(TupleType object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseType(Type object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Type Exp</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Type Exp</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseTypeExp(TypeExp object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Typed Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Typed Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseTypedElement(TypedElement object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Unique Collection</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Unique Collection</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public <T> T1 caseUniqueCollection(Collection<T> object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Unlimited Natural Literal Exp</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Unlimited Natural Literal Exp</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseUnlimitedNaturalLiteralExp(UnlimitedNaturalLiteralExp object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Unspecified Value Exp</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Unspecified Value Exp</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseUnspecifiedValueExp(UnspecifiedValueExp object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Value Specification</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Value Specification</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseValueSpecification(ValueSpecification object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Variable</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Variable</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseVariable(Variable object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Variable Declaration</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Variable Declaration</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseVariableDeclaration(VariableDeclaration object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Variable Exp</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Variable Exp</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseVariableExp(VariableExp object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Vertex</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Vertex</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseVertex(Vertex object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Visitable</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Visitable</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseVisitable(Visitable object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Void Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Void Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseVoidType(org.eclipse.ocl.ocl.VoidType object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Wildcard Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Wildcard Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseWildcardType(WildcardType object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch, but this is the last case anyway.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
	@Override
	public T1 defaultCase(EObject object)
	{
		return null;
	}

} //OCLASSwitch
