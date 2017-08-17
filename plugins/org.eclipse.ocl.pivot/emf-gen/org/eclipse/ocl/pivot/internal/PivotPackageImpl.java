/*******************************************************************************
 * Copyright (c) 2010, 2017 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.internal;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EValidator;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.ocl.pivot.Annotation;
import org.eclipse.ocl.pivot.AnyType;
import org.eclipse.ocl.pivot.AssociationClass;
import org.eclipse.ocl.pivot.AssociationClassCallExp;
import org.eclipse.ocl.pivot.AssociativityKind;
import org.eclipse.ocl.pivot.BagType;
import org.eclipse.ocl.pivot.Behavior;
import org.eclipse.ocl.pivot.BooleanLiteralExp;
import org.eclipse.ocl.pivot.CallExp;
import org.eclipse.ocl.pivot.CallOperationAction;
import org.eclipse.ocl.pivot.CollectionItem;
import org.eclipse.ocl.pivot.CollectionKind;
import org.eclipse.ocl.pivot.CollectionLiteralExp;
import org.eclipse.ocl.pivot.CollectionLiteralPart;
import org.eclipse.ocl.pivot.CollectionRange;
import org.eclipse.ocl.pivot.CollectionType;
import org.eclipse.ocl.pivot.Comment;
import org.eclipse.ocl.pivot.CompleteClass;
import org.eclipse.ocl.pivot.CompleteEnvironment;
import org.eclipse.ocl.pivot.CompleteModel;
import org.eclipse.ocl.pivot.CompletePackage;
import org.eclipse.ocl.pivot.ConnectionPointReference;
import org.eclipse.ocl.pivot.Constraint;
import org.eclipse.ocl.pivot.DataType;
import org.eclipse.ocl.pivot.Detail;
import org.eclipse.ocl.pivot.DynamicBehavior;
import org.eclipse.ocl.pivot.DynamicElement;
import org.eclipse.ocl.pivot.DynamicProperty;
import org.eclipse.ocl.pivot.DynamicType;
import org.eclipse.ocl.pivot.DynamicValueSpecification;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.ElementExtension;
import org.eclipse.ocl.pivot.EnumLiteralExp;
import org.eclipse.ocl.pivot.Enumeration;
import org.eclipse.ocl.pivot.EnumerationLiteral;
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.Feature;
import org.eclipse.ocl.pivot.FeatureCallExp;
import org.eclipse.ocl.pivot.FinalState;
import org.eclipse.ocl.pivot.IfExp;
import org.eclipse.ocl.pivot.Import;
import org.eclipse.ocl.pivot.InstanceSpecification;
import org.eclipse.ocl.pivot.IntegerLiteralExp;
import org.eclipse.ocl.pivot.InvalidLiteralExp;
import org.eclipse.ocl.pivot.InvalidType;
import org.eclipse.ocl.pivot.IterateExp;
import org.eclipse.ocl.pivot.Iteration;
import org.eclipse.ocl.pivot.IteratorExp;
import org.eclipse.ocl.pivot.IteratorVariable;
import org.eclipse.ocl.pivot.LambdaType;
import org.eclipse.ocl.pivot.LanguageExpression;
import org.eclipse.ocl.pivot.LetExp;
import org.eclipse.ocl.pivot.LetVariable;
import org.eclipse.ocl.pivot.Library;
import org.eclipse.ocl.pivot.LiteralExp;
import org.eclipse.ocl.pivot.LoopExp;
import org.eclipse.ocl.pivot.MapLiteralExp;
import org.eclipse.ocl.pivot.MapLiteralPart;
import org.eclipse.ocl.pivot.MapType;
import org.eclipse.ocl.pivot.MessageExp;
import org.eclipse.ocl.pivot.MessageType;
import org.eclipse.ocl.pivot.Model;
import org.eclipse.ocl.pivot.NamedElement;
import org.eclipse.ocl.pivot.Namespace;
import org.eclipse.ocl.pivot.NavigationCallExp;
import org.eclipse.ocl.pivot.NullLiteralExp;
import org.eclipse.ocl.pivot.NumericLiteralExp;
import org.eclipse.ocl.pivot.OCLExpression;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.OperationCallExp;
import org.eclipse.ocl.pivot.OppositePropertyCallExp;
import org.eclipse.ocl.pivot.OrderedSetType;
import org.eclipse.ocl.pivot.OrphanCompletePackage;
import org.eclipse.ocl.pivot.Parameter;
import org.eclipse.ocl.pivot.ParameterVariable;
import org.eclipse.ocl.pivot.PivotFactory;
import org.eclipse.ocl.pivot.PivotPackage;
import org.eclipse.ocl.pivot.Precedence;
import org.eclipse.ocl.pivot.PrimitiveCompletePackage;
import org.eclipse.ocl.pivot.PrimitiveLiteralExp;
import org.eclipse.ocl.pivot.PrimitiveType;
import org.eclipse.ocl.pivot.Profile;
import org.eclipse.ocl.pivot.ProfileApplication;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.PropertyCallExp;
import org.eclipse.ocl.pivot.Pseudostate;
import org.eclipse.ocl.pivot.PseudostateKind;
import org.eclipse.ocl.pivot.RealLiteralExp;
import org.eclipse.ocl.pivot.ReferringElement;
import org.eclipse.ocl.pivot.Region;
import org.eclipse.ocl.pivot.ResultVariable;
import org.eclipse.ocl.pivot.SelfType;
import org.eclipse.ocl.pivot.SendSignalAction;
import org.eclipse.ocl.pivot.SequenceType;
import org.eclipse.ocl.pivot.SetType;
import org.eclipse.ocl.pivot.ShadowExp;
import org.eclipse.ocl.pivot.ShadowPart;
import org.eclipse.ocl.pivot.Signal;
import org.eclipse.ocl.pivot.Slot;
import org.eclipse.ocl.pivot.StandardLibrary;
import org.eclipse.ocl.pivot.State;
import org.eclipse.ocl.pivot.StateExp;
import org.eclipse.ocl.pivot.StateMachine;
import org.eclipse.ocl.pivot.Stereotype;
import org.eclipse.ocl.pivot.StereotypeExtender;
import org.eclipse.ocl.pivot.StringLiteralExp;
import org.eclipse.ocl.pivot.TemplateBinding;
import org.eclipse.ocl.pivot.TemplateParameter;
import org.eclipse.ocl.pivot.TemplateParameterSubstitution;
import org.eclipse.ocl.pivot.TemplateSignature;
import org.eclipse.ocl.pivot.TemplateableElement;
import org.eclipse.ocl.pivot.Transition;
import org.eclipse.ocl.pivot.TransitionKind;
import org.eclipse.ocl.pivot.Trigger;
import org.eclipse.ocl.pivot.TupleLiteralExp;
import org.eclipse.ocl.pivot.TupleLiteralPart;
import org.eclipse.ocl.pivot.TupleType;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.TypeExp;
import org.eclipse.ocl.pivot.TypedElement;
import org.eclipse.ocl.pivot.UnlimitedNaturalLiteralExp;
import org.eclipse.ocl.pivot.UnspecifiedValueExp;
import org.eclipse.ocl.pivot.ValueSpecification;
import org.eclipse.ocl.pivot.Variable;
import org.eclipse.ocl.pivot.VariableDeclaration;
import org.eclipse.ocl.pivot.VariableExp;
import org.eclipse.ocl.pivot.Vertex;
import org.eclipse.ocl.pivot.VoidType;
import org.eclipse.ocl.pivot.WildcardType;
import org.eclipse.ocl.pivot.library.LibraryFeature;
import org.eclipse.ocl.pivot.util.PivotValidator;
import org.eclipse.ocl.pivot.util.Visitable;
import org.eclipse.ocl.pivot.utilities.MorePivotable;
import org.eclipse.ocl.pivot.utilities.Nameable;
import org.eclipse.ocl.pivot.utilities.Pivotable;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class PivotPackageImpl
extends EPackageImpl
implements PivotPackage  {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass annotationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass namedElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass elementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass elementExtensionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass oclExpressionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass bagTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass behaviorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass collectionTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass dataTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass booleanLiteralExpEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass primitiveLiteralExpEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass literalExpEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass callOperationActionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass collectionItemEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass collectionLiteralPartEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass collectionLiteralExpEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass collectionRangeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass enumLiteralExpEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass enumerationLiteralEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass expressionInOCLEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass enumerationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass featureEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass variableEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass ifExpEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass importEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass instanceSpecificationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass integerLiteralExpEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass numericLiteralExpEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass invalidLiteralExpEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass invalidTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass iterateExpEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass iterationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass iteratorExpEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass iteratorVariableEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass lambdaTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass languageExpressionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass letExpEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass letVariableEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass libraryEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass loopExpEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass mapLiteralExpEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass mapLiteralPartEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass mapTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass messageExpEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass sendSignalActionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass signalEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass slotEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass standardLibraryEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass messageTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass modelEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass morePivotableEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass nullLiteralExpEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass operationCallExpEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass orderedSetTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass orphanCompletePackageEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass primitiveTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass profileEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass profileApplicationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass propertyCallExpEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass pseudostateEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass realLiteralExpEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass referringElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass regionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass resultVariableEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass selfTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass sequenceTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass setTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass shadowExpEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass shadowPartEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass stateEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass stateExpEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass stateMachineEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass stereotypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass stereotypeExtenderEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass stringLiteralExpEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tupleLiteralExpEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tupleLiteralPartEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tupleTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass typeExpEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass unlimitedNaturalLiteralExpEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass unspecifiedValueExpEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass variableExpEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass vertexEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass voidTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass wildcardTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass pivotableEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass visitableEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass classEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass typeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass templateableElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass transitionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass triggerEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass templateBindingEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass templateSignatureEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass templateParameterEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass templateParameterSubstitutionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass packageEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass namespaceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass precedenceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass primitiveCompletePackageEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass propertyEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass typedElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass associationClassEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass operationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass parameterEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass parameterVariableEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass variableDeclarationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass oppositePropertyCallExpEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass commentEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass completeClassEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass completeEnvironmentEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass completeModelEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass completePackageEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass connectionPointReferenceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass constraintEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass valueSpecificationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass nameableEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass detailEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass dynamicBehaviorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass dynamicElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass dynamicPropertyEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass dynamicTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass dynamicValueSpecificationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass anyTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass associationClassCallExpEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass navigationCallExpEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass featureCallExpEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass finalStateEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass callExpEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum associativityKindEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum collectionKindEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum pseudostateKindEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum transitionKindEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType booleanEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType integerEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType libraryFeatureEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType objectEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType realEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType stringEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType throwableEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType unlimitedNaturalEDataType = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see org.eclipse.ocl.pivot.PivotPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private PivotPackageImpl() {
		super(eNS_URI, PivotFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 *
	 * <p>This method is used to initialize {@link PivotPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static PivotPackage init() {
		if (isInited) return (PivotPackage)EPackage.Registry.INSTANCE.getEPackage(PivotPackage.eNS_URI);

		// Obtain or create and register package
		Object ePackage = EPackage.Registry.INSTANCE.get(eNS_URI);
		PivotPackageImpl thePivotPackage = (PivotPackageImpl)(ePackage instanceof PivotPackageImpl ? ePackage : new PivotPackageImpl());

		isInited = true;

		// Create package meta-data objects
		thePivotPackage.createPackageContents();

		// Initialize created meta-data
		thePivotPackage.initializePackageContents();

		// Register package validator
		EValidator.Registry.INSTANCE.put
		(thePivotPackage,
			new EValidator.Descriptor()
		{
			@Override
			public EValidator getEValidator()
			{
				return PivotValidator.INSTANCE;
			}
		});

		// Mark meta-data to indicate it can't be changed
		thePivotPackage.freeze();


		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(PivotPackage.eNS_URI, thePivotPackage);
		return thePivotPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getAnnotation() {
		return annotationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getAnnotation_OwnedContents()
	{
		return (EReference)annotationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getAnnotation_OwnedDetails()
	{
		return (EReference)annotationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getAnnotation_References()
	{
		return (EReference)annotationEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getNamedElement() {
		return namedElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getNamedElement_Name() {
		return (EAttribute)namedElementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getElement() {
		return elementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getElement_AnnotatingComments()
	{
		return (EReference)elementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getElement_OwnedAnnotations()
	{
		return (EReference)elementEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getElement_OwnedComments()
	{
		return (EReference)elementEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getElement_OwnedExtensions()
	{
		return (EReference)elementEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EOperation getElement__AllOwnedElements()
	{
		return elementEClass.getEOperations().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EOperation getElement__GetValue__Type_String()
	{
		return elementEClass.getEOperations().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getElementExtension()
	{
		return elementExtensionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getElementExtension_Stereotype()
	{
		return (EReference)elementExtensionEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getElementExtension_Base()
	{
		return (EReference)elementExtensionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getElementExtension_IsApplied()
	{
		return (EAttribute)elementExtensionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getElementExtension_IsRequired()
	{
		return (EAttribute)elementExtensionEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getBagType() {
		return bagTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getBehavior()
	{
		return behaviorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getBehavior_OwningTransition()
	{
		return (EReference)behaviorEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getCollectionType() {
		return collectionTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getCollectionType_ElementType() {
		return (EReference)collectionTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCollectionType_IsNullFree()
	{
		return (EAttribute)collectionTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCollectionType_Lower()
	{
		return (EAttribute)collectionTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCollectionType_Upper()
	{
		return (EAttribute)collectionTypeEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getDataType() {
		return dataTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getDataType_BehavioralClass()
	{
		return (EReference)dataTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getDataType_IsSerializable()
	{
		return (EAttribute)dataTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * @since 1.3
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getDataType_Value()
	{
		return (EAttribute)dataTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getBooleanLiteralExp() {
		return booleanLiteralExpEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getBooleanLiteralExp_BooleanSymbol() {
		return (EAttribute)booleanLiteralExpEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EOperation getBooleanLiteralExp__ValidateTypeIsBoolean__DiagnosticChain_Map()
	{
		return booleanLiteralExpEClass.getEOperations().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getPrimitiveLiteralExp() {
		return primitiveLiteralExpEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getLiteralExp() {
		return literalExpEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getCallOperationAction() {
		return callOperationActionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getCallOperationAction_Operation() {
		return (EReference)callOperationActionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getCollectionItem() {
		return collectionItemEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getCollectionItem_OwnedItem()
	{
		return (EReference)collectionItemEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EOperation getCollectionItem__ValidateTypeIsItemType__DiagnosticChain_Map()
	{
		return collectionItemEClass.getEOperations().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getCollectionLiteralPart() {
		return collectionLiteralPartEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EOperation getCollectionLiteralPart__ValidateTypeIsNotInvalid__DiagnosticChain_Map()
	{
		return collectionLiteralPartEClass.getEOperations().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getCollectionLiteralExp() {
		return collectionLiteralExpEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCollectionLiteralExp_Kind() {
		return (EAttribute)collectionLiteralExpEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getCollectionLiteralExp_OwnedParts()
	{
		return (EReference)collectionLiteralExpEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EOperation getCollectionLiteralExp__ValidateCollectionKindIsConcrete__DiagnosticChain_Map()
	{
		return collectionLiteralExpEClass.getEOperations().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EOperation getCollectionLiteralExp__ValidateSetKindIsSet__DiagnosticChain_Map()
	{
		return collectionLiteralExpEClass.getEOperations().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EOperation getCollectionLiteralExp__ValidateOrderedSetKindIsOrderedSet__DiagnosticChain_Map()
	{
		return collectionLiteralExpEClass.getEOperations().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EOperation getCollectionLiteralExp__ValidateSequenceKindIsSequence__DiagnosticChain_Map()
	{
		return collectionLiteralExpEClass.getEOperations().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EOperation getCollectionLiteralExp__ValidateBagKindIsBag__DiagnosticChain_Map()
	{
		return collectionLiteralExpEClass.getEOperations().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getCollectionRange() {
		return collectionRangeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getCollectionRange_OwnedFirst()
	{
		return (EReference)collectionRangeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getCollectionRange_OwnedLast()
	{
		return (EReference)collectionRangeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getEnumLiteralExp() {
		return enumLiteralExpEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getEnumLiteralExp_ReferredLiteral()
	{
		return (EReference)enumLiteralExpEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EOperation getEnumLiteralExp__ValidateTypeIsEnumerationType__DiagnosticChain_Map()
	{
		return enumLiteralExpEClass.getEOperations().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getEnumerationLiteral() {
		return enumerationLiteralEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getEnumerationLiteral_OwningEnumeration()
	{
		return (EReference)enumerationLiteralEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getEnumerationLiteral_Value()
	{
		return (EAttribute)enumerationLiteralEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getExpressionInOCL()
	{
		return expressionInOCLEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getExpressionInOCL_OwnedBody()
	{
		return (EReference)expressionInOCLEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getExpressionInOCL_OwnedContext()
	{
		return (EReference)expressionInOCLEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getExpressionInOCL_OwnedParameters()
	{
		return (EReference)expressionInOCLEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getExpressionInOCL_OwnedResult()
	{
		return (EReference)expressionInOCLEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getEnumeration() {
		return enumerationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getEnumeration_OwnedLiterals()
	{
		return (EReference)enumerationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getFeature() {
		return featureEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getFeature_ImplementationClass()
	{
		return (EAttribute)featureEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getFeature_IsStatic()
	{
		return (EAttribute)featureEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EOperation getFeature__ValidateNameIsNotNull__DiagnosticChain_Map()
	{
		return featureEClass.getEOperations().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EOperation getFeature__ValidateTypeIsNotInvalid__DiagnosticChain_Map()
	{
		return featureEClass.getEOperations().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EOperation getFeature__ValidateTypeIsNotNull__DiagnosticChain_Map()
	{
		return featureEClass.getEOperations().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getFeature_Implementation()
	{
		return (EAttribute)featureEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getVariable() {
		return variableEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getVariable_IsImplicit()
	{
		return (EAttribute)variableEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getVariable_RepresentedParameter() {
		return (EReference)variableEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EOperation getVariable__ValidateCompatibleInitialiserType__DiagnosticChain_Map()
	{
		return variableEClass.getEOperations().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getVariable_OwnedInit()
	{
		return (EReference)variableEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getIfExp() {
		return ifExpEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getIfExp_IsElseIf()
	{
		return (EAttribute)ifExpEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getIfExp_OwnedCondition()
	{
		return (EReference)ifExpEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getIfExp_OwnedElse()
	{
		return (EReference)ifExpEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getIfExp_OwnedThen()
	{
		return (EReference)ifExpEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EOperation getIfExp__ValidateConditionTypeIsBoolean__DiagnosticChain_Map()
	{
		return ifExpEClass.getEOperations().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EOperation getIfExp__ValidateTypeIsNotInvalid__DiagnosticChain_Map()
	{
		return ifExpEClass.getEOperations().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getImport()
	{
		return importEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getImport_ImportedNamespace()
	{
		return (EReference)importEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * @since 1.4
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getImport_XmiidVersion()
	{
		return (EAttribute)importEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getInstanceSpecification()
	{
		return instanceSpecificationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getInstanceSpecification_Classes()
	{
		return (EReference)instanceSpecificationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getInstanceSpecification_OwnedSlots()
	{
		return (EReference)instanceSpecificationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getInstanceSpecification_OwnedSpecification()
	{
		return (EReference)instanceSpecificationEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getInstanceSpecification_OwningPackage()
	{
		return (EReference)instanceSpecificationEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getIntegerLiteralExp() {
		return integerLiteralExpEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getIntegerLiteralExp_IntegerSymbol() {
		return (EAttribute)integerLiteralExpEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EOperation getIntegerLiteralExp__ValidateTypeIsInteger__DiagnosticChain_Map()
	{
		return integerLiteralExpEClass.getEOperations().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getNumericLiteralExp() {
		return numericLiteralExpEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getOCLExpression()
	{
		return oclExpressionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getOCLExpression_TypeValue()
	{
		return (EReference)oclExpressionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * @since 1.3
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EOperation getOCLExpression__IsNonNull()
	{
		return oclExpressionEClass.getEOperations().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * @since 1.3
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EOperation getOCLExpression__IsNull()
	{
		return oclExpressionEClass.getEOperations().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EOperation getOCLExpression__ValidateTypeIsNotNull__DiagnosticChain_Map()
	{
		return oclExpressionEClass.getEOperations().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getInvalidLiteralExp() {
		return invalidLiteralExpEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getInvalidType() {
		return invalidTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getIterateExp()
	{
		return iterateExpEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getIterateExp_OwnedResult()
	{
		return (EReference)iterateExpEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EOperation getIterateExp__ValidateTypeIsResultType__DiagnosticChain_Map()
	{
		return iterateExpEClass.getEOperations().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EOperation getIterateExp__ValidateUnsafeSourceCanNotBeNull__DiagnosticChain_Map()
	{
		return iterateExpEClass.getEOperations().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EOperation getIterateExp__ValidateBodyTypeConformsToResultType__DiagnosticChain_Map()
	{
		return iterateExpEClass.getEOperations().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EOperation getIterateExp__ValidateOneInitializer__DiagnosticChain_Map()
	{
		return iterateExpEClass.getEOperations().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EOperation getIterateExp__ValidateSafeIteratorIsRequired__DiagnosticChain_Map()
	{
		return iterateExpEClass.getEOperations().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EOperation getIterateExp__ValidateSafeSourceCanBeNull__DiagnosticChain_Map()
	{
		return iterateExpEClass.getEOperations().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getIteration()
	{
		return iterationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getIteration_OwnedAccumulators()
	{
		return (EReference)iterationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getIteration_OwnedIterators()
	{
		return (EReference)iterationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getIteratorExp()
	{
		return iteratorExpEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EOperation getIteratorExp__ValidateClosureBodyTypeIsConformanttoIteratorType__DiagnosticChain_Map()
	{
		return iteratorExpEClass.getEOperations().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EOperation getIteratorExp__ValidateSortedByIteratorTypeIsComparable__DiagnosticChain_Map()
	{
		return iteratorExpEClass.getEOperations().get(16);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EOperation getIteratorExp__ValidateUnsafeSourceCanNotBeNull__DiagnosticChain_Map()
	{
		return iteratorExpEClass.getEOperations().get(17);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getIteratorVariable()
	{
		return iteratorVariableEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EOperation getIteratorVariable__ValidateHasNoInitializer__DiagnosticChain_Map()
	{
		return iteratorVariableEClass.getEOperations().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EOperation getIteratorExp__ValidateAnyHasOneIterator__DiagnosticChain_Map()
	{
		return iteratorExpEClass.getEOperations().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EOperation getIteratorExp__ValidateAnyTypeIsSourceElementType__DiagnosticChain_Map()
	{
		return iteratorExpEClass.getEOperations().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * @since 1.3
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EOperation getIteratorExp__ValidateClosureBodyElementTypeIsIteratorType__DiagnosticChain_Map()
	{
		return iteratorExpEClass.getEOperations().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EOperation getIteratorExp__ValidateAnyBodyTypeIsBoolean__DiagnosticChain_Map()
	{
		return iteratorExpEClass.getEOperations().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EOperation getIteratorExp__ValidateClosureHasOneIterator__DiagnosticChain_Map()
	{
		return iteratorExpEClass.getEOperations().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EOperation getIteratorExp__ValidateClosureTypeIsUniqueCollection__DiagnosticChain_Map()
	{
		return iteratorExpEClass.getEOperations().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EOperation getIteratorExp__ValidateCollectElementTypeIsFlattenedBodyType__DiagnosticChain_Map()
	{
		return iteratorExpEClass.getEOperations().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EOperation getIteratorExp__ValidateClosureSourceElementTypeIsBodyElementType__DiagnosticChain_Map()
	{
		return iteratorExpEClass.getEOperations().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EOperation getIteratorExp__ValidateClosureElementTypeIsSourceElementType__DiagnosticChain_Map()
	{
		return iteratorExpEClass.getEOperations().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EOperation getIteratorExp__ValidateCollectTypeIsUnordered__DiagnosticChain_Map()
	{
		return iteratorExpEClass.getEOperations().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EOperation getIteratorExp__ValidateSortedByIsOrderedIfSourceIsOrdered__DiagnosticChain_Map()
	{
		return iteratorExpEClass.getEOperations().get(15);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EOperation getIteratorExp__ValidateSortedByElementTypeIsSourceElementType__DiagnosticChain_Map()
	{
		return iteratorExpEClass.getEOperations().get(14);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EOperation getIteratorExp__ValidateIteratorTypeIsSourceElementType__DiagnosticChain_Map()
	{
		return iteratorExpEClass.getEOperations().get(11);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EOperation getIteratorExp__ValidateSafeIteratorIsRequired__DiagnosticChain_Map()
	{
		return iteratorExpEClass.getEOperations().get(12);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EOperation getIteratorExp__ValidateSafeSourceCanBeNull__DiagnosticChain_Map()
	{
		return iteratorExpEClass.getEOperations().get(13);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getLambdaType()
	{
		return lambdaTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getLambdaType_ContextType()
	{
		return (EReference)lambdaTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getLambdaType_ParameterType()
	{
		return (EReference)lambdaTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getLambdaType_ResultType()
	{
		return (EReference)lambdaTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getLanguageExpression()
	{
		return languageExpressionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getLanguageExpression_Body()
	{
		return (EAttribute)languageExpressionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getLanguageExpression_Language()
	{
		return (EAttribute)languageExpressionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getLanguageExpression_OwningConstraint()
	{
		return (EReference)languageExpressionEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getLetExp() {
		return letExpEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getLetExp_OwnedIn()
	{
		return (EReference)letExpEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getLetExp_OwnedVariable()
	{
		return (EReference)letExpEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EOperation getLetExp__ValidateCompatibleNullityForIn__DiagnosticChain_Map()
	{
		return letExpEClass.getEOperations().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EOperation getLetExp__ValidateTypeIsInType__DiagnosticChain_Map()
	{
		return letExpEClass.getEOperations().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EOperation getLetExp__ValidateTypeIsNotInvalid__DiagnosticChain_Map()
	{
		return letExpEClass.getEOperations().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getLetVariable()
	{
		return letVariableEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EOperation getLetVariable__ValidateCompatibleNullityForInitializer__DiagnosticChain_Map()
	{
		return letVariableEClass.getEOperations().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EOperation getLetVariable__ValidateCompatibleTypeForInitializer__DiagnosticChain_Map()
	{
		return letVariableEClass.getEOperations().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EOperation getLetVariable__ValidateHasInitializer__DiagnosticChain_Map()
	{
		return letVariableEClass.getEOperations().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getLibrary()
	{
		return libraryEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getLibrary_OwnedPrecedences()
	{
		return (EReference)libraryEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getLoopExp() {
		return loopExpEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getLoopExp_OwnedBody()
	{
		return (EReference)loopExpEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getLoopExp_OwnedIterators()
	{
		return (EReference)loopExpEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getLoopExp_ReferredIteration()
	{
		return (EReference)loopExpEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EOperation getLoopExp__ValidateSourceIsCollection__DiagnosticChain_Map()
	{
		return loopExpEClass.getEOperations().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getMapLiteralExp()
	{
		return mapLiteralExpEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getMapLiteralExp_OwnedParts()
	{
		return (EReference)mapLiteralExpEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getMapLiteralPart()
	{
		return mapLiteralPartEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getMapLiteralPart_OwnedKey()
	{
		return (EReference)mapLiteralPartEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getMapLiteralPart_OwnedValue()
	{
		return (EReference)mapLiteralPartEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getMapType()
	{
		return mapTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getMapType_KeyType()
	{
		return (EReference)mapTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getMapType_ValueType()
	{
		return (EReference)mapTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EOperation getLoopExp__ValidateNoInitializers__DiagnosticChain_Map()
	{
		return loopExpEClass.getEOperations().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getMessageExp() {
		return messageExpEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getMessageExp_OwnedArguments()
	{
		return (EReference)messageExpEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getMessageExp_OwnedCalledOperation()
	{
		return (EReference)messageExpEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getMessageExp_OwnedSentSignal()
	{
		return (EReference)messageExpEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getMessageExp_OwnedTarget()
	{
		return (EReference)messageExpEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EOperation getMessageExp__ValidateOneCallOrOneSend__DiagnosticChain_Map()
	{
		return messageExpEClass.getEOperations().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EOperation getMessageExp__ValidateTargetIsNotACollection__DiagnosticChain_Map()
	{
		return messageExpEClass.getEOperations().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getSendSignalAction() {
		return sendSignalActionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getSendSignalAction_Signal() {
		return (EReference)sendSignalActionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getSignal() {
		return signalEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getSlot()
	{
		return slotEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getSlot_DefiningProperty()
	{
		return (EReference)slotEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getSlot_OwnedValues()
	{
		return (EReference)slotEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getSlot_OwningInstance()
	{
		return (EReference)slotEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getStandardLibrary()
	{
		return standardLibraryEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getStandardLibrary_OwningCompleteEnvironment()
	{
		return (EReference)standardLibraryEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getMessageType() {
		return messageTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getMessageType_ReferredSignal() {
		return (EReference)messageTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getModel()
	{
		return modelEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getModel_ExternalURI()
	{
		return (EAttribute)modelEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getModel_OwnedImports()
	{
		return (EReference)modelEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getModel_OwnedPackages()
	{
		return (EReference)modelEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * @since 1.4
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getModel_XmiidVersion()
	{
		return (EAttribute)modelEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getMessageType_ReferredOperation() {
		return (EReference)messageTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getMorePivotable()
	{
		return morePivotableEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getNullLiteralExp() {
		return nullLiteralExpEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getOperationCallExp() {
		return operationCallExpEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * @since 1.1
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getOperationCallExp_IsVirtual()
	{
		return (EAttribute)operationCallExpEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getOperationCallExp_OwnedArguments()
	{
		return (EReference)operationCallExpEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getOperationCallExp_ReferredOperation() {
		return (EReference)operationCallExpEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EOperation getOperationCallExp__ValidateArgumentTypeIsConformant__DiagnosticChain_Map()
	{
		return operationCallExpEClass.getEOperations().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EOperation getOperationCallExp__ValidateSafeSourceCanBeNull__DiagnosticChain_Map()
	{
		return operationCallExpEClass.getEOperations().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EOperation getOperationCallExp__ValidateArgumentCount__DiagnosticChain_Map()
	{
		return operationCallExpEClass.getEOperations().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getOrderedSetType() {
		return orderedSetTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getOrphanCompletePackage()
	{
		return orphanCompletePackageEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getPrimitiveType() {
		return primitiveTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getPrimitiveType_Coercions()
	{
		return (EReference)primitiveTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getProfile()
	{
		return profileEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getProfile_ProfileApplications()
	{
		return (EReference)profileEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getProfileApplication()
	{
		return profileApplicationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getProfileApplication_AppliedProfile()
	{
		return (EReference)profileApplicationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getProfileApplication_IsStrict()
	{
		return (EAttribute)profileApplicationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getProfileApplication_OwningPackage()
	{
		return (EReference)profileApplicationEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getPropertyCallExp() {
		return propertyCallExpEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getPropertyCallExp_ReferredProperty() {
		return (EReference)propertyCallExpEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EOperation getPropertyCallExp__GetSpecializedReferredPropertyOwningType()
	{
		return propertyCallExpEClass.getEOperations().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EOperation getPropertyCallExp__GetSpecializedReferredPropertyType()
	{
		return propertyCallExpEClass.getEOperations().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EOperation getPropertyCallExp__ValidateNonStaticSourceTypeIsConformant__DiagnosticChain_Map()
	{
		return propertyCallExpEClass.getEOperations().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EOperation getPropertyCallExp__ValidateSafeSourceCanBeNull__DiagnosticChain_Map()
	{
		return propertyCallExpEClass.getEOperations().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EOperation getPropertyCallExp__ValidateUnsafeSourceCanNotBeNull__DiagnosticChain_Map()
	{
		return propertyCallExpEClass.getEOperations().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EOperation getPropertyCallExp__ValidateCompatibleResultType__DiagnosticChain_Map()
	{
		return propertyCallExpEClass.getEOperations().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getPseudostate()
	{
		return pseudostateEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getPseudostate_Kind()
	{
		return (EAttribute)pseudostateEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getPseudostate_OwningState()
	{
		return (EReference)pseudostateEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getPseudostate_OwningStateMachine()
	{
		return (EReference)pseudostateEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getRealLiteralExp() {
		return realLiteralExpEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getRealLiteralExp_RealSymbol() {
		return (EAttribute)realLiteralExpEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getReferringElement()
	{
		return referringElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EOperation getReferringElement__GetReferredElement()
	{
		return referringElementEClass.getEOperations().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getRegion()
	{
		return regionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getRegion_ExtendedRegion()
	{
		return (EReference)regionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getRegion_OwnedSubvertexes()
	{
		return (EReference)regionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getRegion_OwnedTransitions()
	{
		return (EReference)regionEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getRegion_OwningState()
	{
		return (EReference)regionEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getRegion_OwningStateMachine()
	{
		return (EReference)regionEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getResultVariable()
	{
		return resultVariableEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EOperation getResultVariable__ValidateCompatibleNullityForInitializer__DiagnosticChain_Map()
	{
		return resultVariableEClass.getEOperations().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EOperation getResultVariable__ValidateCompatibleTypeForInitializer__DiagnosticChain_Map()
	{
		return resultVariableEClass.getEOperations().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EOperation getResultVariable__ValidateHasInitializer__DiagnosticChain_Map()
	{
		return resultVariableEClass.getEOperations().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getSelfType()
	{
		return selfTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EOperation getSelfType__SpecializeIn__CallExp_Type()
	{
		return selfTypeEClass.getEOperations().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getSequenceType() {
		return sequenceTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getSetType() {
		return setTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getShadowExp()
	{
		return shadowExpEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getShadowExp_OwnedParts()
	{
		return (EReference)shadowExpEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getShadowExp_Value()
	{
		return (EAttribute)shadowExpEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EOperation getShadowExp__ValidateClassHasNoStringValueInitializer__DiagnosticChain_Map()
	{
		return shadowExpEClass.getEOperations().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EOperation getShadowExp__ValidateDataTypeHasNoPartInitializers__DiagnosticChain_Map()
	{
		return shadowExpEClass.getEOperations().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * @since 1.3
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EOperation getShadowExp__ValidateDataTypeHasOnePartInitializer__DiagnosticChain_Map()
	{
		return shadowExpEClass.getEOperations().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EOperation getShadowExp__ValidateDataTypeHasStringValueInitializer__DiagnosticChain_Map()
	{
		return shadowExpEClass.getEOperations().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EOperation getShadowExp__ValidateInitializesAllClassProperties__DiagnosticChain_Map()
	{
		return shadowExpEClass.getEOperations().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EOperation getShadowExp__ValidateTypeIsNotInvalid__DiagnosticChain_Map()
	{
		return shadowExpEClass.getEOperations().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getShadowPart()
	{
		return shadowPartEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getShadowPart_OwnedInit()
	{
		return (EReference)shadowPartEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getShadowPart_ReferredProperty()
	{
		return (EReference)shadowPartEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EOperation getShadowPart__ValidateCompatibleInitialiserType__DiagnosticChain_Map()
	{
		return shadowPartEClass.getEOperations().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EOperation getShadowPart__ValidateTypeIsNotInvalid__DiagnosticChain_Map()
	{
		return shadowPartEClass.getEOperations().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EOperation getShadowPart__ValidateTypeIsNotNull__DiagnosticChain_Map()
	{
		return shadowPartEClass.getEOperations().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getState() {
		return stateEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getState_IsComposite()
	{
		return (EAttribute)stateEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getState_IsOrthogonal()
	{
		return (EAttribute)stateEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getState_IsSimple()
	{
		return (EAttribute)stateEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getState_IsSubmachineState()
	{
		return (EAttribute)stateEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getState_OwnedConnectionPoints()
	{
		return (EReference)stateEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getState_OwnedConnections()
	{
		return (EReference)stateEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getState_OwnedDeferrableTriggers()
	{
		return (EReference)stateEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getState_OwnedDoActivity()
	{
		return (EReference)stateEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getState_OwnedEntry()
	{
		return (EReference)stateEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getState_OwnedExit()
	{
		return (EReference)stateEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getState_OwnedRegions()
	{
		return (EReference)stateEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getState_OwnedStateInvariant()
	{
		return (EReference)stateEClass.getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getState_RedefinedState()
	{
		return (EReference)stateEClass.getEStructuralFeatures().get(12);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getState_Submachines()
	{
		return (EReference)stateEClass.getEStructuralFeatures().get(13);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getStateExp() {
		return stateExpEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getStateExp_ReferredState() {
		return (EReference)stateExpEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EOperation getStateExp__ValidateTypeIsNotInvalid__DiagnosticChain_Map()
	{
		return stateExpEClass.getEOperations().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getStateMachine()
	{
		return stateMachineEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getStateMachine_ExtendedStateMachines()
	{
		return (EReference)stateMachineEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getStateMachine_OwnedConnectionPoints()
	{
		return (EReference)stateMachineEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getStateMachine_OwnedRegions()
	{
		return (EReference)stateMachineEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getStateMachine_SubmachineStates()
	{
		return (EReference)stateMachineEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getStereotype()
	{
		return stereotypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getStereotype_OwnedExtenders()
	{
		return (EReference)stereotypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getStereotypeExtender()
	{
		return stereotypeExtenderEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getStereotypeExtender_Class()
	{
		return (EReference)stereotypeExtenderEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getStereotypeExtender_IsRequired()
	{
		return (EAttribute)stereotypeExtenderEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getStereotypeExtender_OwningStereotype()
	{
		return (EReference)stereotypeExtenderEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getStringLiteralExp() {
		return stringLiteralExpEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getStringLiteralExp_StringSymbol() {
		return (EAttribute)stringLiteralExpEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getTupleLiteralExp() {
		return tupleLiteralExpEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getTupleLiteralExp_OwnedParts()
	{
		return (EReference)tupleLiteralExpEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getTupleLiteralPart() {
		return tupleLiteralPartEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getTupleLiteralPart_OwnedInit()
	{
		return (EReference)tupleLiteralPartEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EOperation getTupleLiteralPart__ValidateCompatibleInitialiserType__DiagnosticChain_Map()
	{
		return tupleLiteralPartEClass.getEOperations().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EOperation getTupleLiteralPart__ValidateTypeIsNotInvalid__DiagnosticChain_Map()
	{
		return tupleLiteralPartEClass.getEOperations().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getTupleType() {
		return tupleTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getTypeExp() {
		return typeExpEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getTypeExp_ReferredType() {
		return (EReference)typeExpEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getUnlimitedNaturalLiteralExp() {
		return unlimitedNaturalLiteralExpEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getUnlimitedNaturalLiteralExp_UnlimitedNaturalSymbol()
	{
		return (EAttribute)unlimitedNaturalLiteralExpEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getUnspecifiedValueExp() {
		return unspecifiedValueExpEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getVariableExp() {
		return variableExpEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getVariableExp_IsImplicit()
	{
		return (EAttribute)variableExpEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getVariableExp_ReferredVariable() {
		return (EReference)variableExpEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EOperation getVariableExp__ValidateTypeIsNotInvalid__DiagnosticChain_Map()
	{
		return variableExpEClass.getEOperations().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getVertex()
	{
		return vertexEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getVertex_IncomingTransitions()
	{
		return (EReference)vertexEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getVertex_OutgoingTransitions()
	{
		return (EReference)vertexEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getVertex_OwningRegion()
	{
		return (EReference)vertexEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getVoidType() {
		return voidTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getWildcardType()
	{
		return wildcardTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getWildcardType_LowerBound()
	{
		return (EReference)wildcardTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getWildcardType_UpperBound()
	{
		return (EReference)wildcardTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getPivotable() {
		return pivotableEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getVisitable() {
		return visitableEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getClass_() {
		return classEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getClass_Extenders()
	{
		return (EReference)classEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getClass_InstanceClassName()
	{
		return (EAttribute)classEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getClass_IsAbstract() {
		return (EAttribute)classEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getClass_IsActive()
	{
		return (EAttribute)classEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getClass_OwnedInvariants()
	{
		return (EReference)classEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getClass_OwnedOperations()
	{
		return (EReference)classEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getClass_OwnedProperties()
	{
		return (EReference)classEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getClass_OwningPackage()
	{
		return (EReference)classEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getClass_SuperClasses()
	{
		return (EReference)classEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * @since 1.3
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EOperation getClass__ValidateNameIsNotNull__DiagnosticChain_Map()
	{
		return classEClass.getEOperations().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EOperation getClass__ValidateUniqueInvariantName__DiagnosticChain_Map()
	{
		return classEClass.getEOperations().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getClass_IsInterface()
	{
		return (EAttribute)classEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getClass_OwnedBehaviors()
	{
		return (EReference)classEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getType() {
		return typeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EOperation getType__FlattenedType()
	{
		return typeEClass.getEOperations().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EOperation getType__IsClass()
	{
		return typeEClass.getEOperations().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EOperation getType__IsTemplateParameter()
	{
		return typeEClass.getEOperations().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EOperation getType__SpecializeIn__CallExp_Type()
	{
		return typeEClass.getEOperations().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getTemplateableElement() {
		return templateableElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getTemplateableElement_OwnedBindings()
	{
		return (EReference)templateableElementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getTemplateableElement_OwnedSignature()
	{
		return (EReference)templateableElementEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getTemplateableElement_UnspecializedElement()
	{
		return (EReference)templateableElementEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getTransition()
	{
		return transitionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getTransition_Kind()
	{
		return (EAttribute)transitionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getTransition_OwnedEffect()
	{
		return (EReference)transitionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getTransition_OwnedGuard()
	{
		return (EReference)transitionEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getTransition_OwnedTriggers()
	{
		return (EReference)transitionEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getTransition_OwningRegion()
	{
		return (EReference)transitionEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getTransition_Source()
	{
		return (EReference)transitionEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getTransition_Target()
	{
		return (EReference)transitionEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getTrigger()
	{
		return triggerEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getTrigger_OwningState()
	{
		return (EReference)triggerEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getTrigger_OwningTransition()
	{
		return (EReference)triggerEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getTemplateBinding() {
		return templateBindingEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getTemplateBinding_OwnedSubstitutions()
	{
		return (EReference)templateBindingEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getTemplateBinding_OwningElement()
	{
		return (EReference)templateBindingEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getTemplateBinding_TemplateSignature()
	{
		return (EReference)templateBindingEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getTemplateSignature() {
		return templateSignatureEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getTemplateSignature_OwnedParameters()
	{
		return (EReference)templateSignatureEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getTemplateSignature_OwningElement()
	{
		return (EReference)templateSignatureEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getTemplateParameter() {
		return templateParameterEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getTemplateParameter_ConstrainingClasses()
	{
		return (EReference)templateParameterEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getTemplateParameter_OwningSignature()
	{
		return (EReference)templateParameterEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getTemplateParameterSubstitution() {
		return templateParameterSubstitutionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getTemplateParameterSubstitution_Formal() {
		return (EReference)templateParameterSubstitutionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getTemplateParameterSubstitution_OwnedWildcard()
	{
		return (EReference)templateParameterSubstitutionEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getTemplateParameterSubstitution_OwningBinding()
	{
		return (EReference)templateParameterSubstitutionEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getTemplateParameterSubstitution_Actual() {
		return (EReference)templateParameterSubstitutionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getPackage() {
		return packageEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getPackage_URI()
	{
		return (EAttribute)packageEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getPackage_ImportedPackages()
	{
		return (EReference)packageEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getPackage_NsPrefix() {
		return (EAttribute)packageEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getPackage_OwnedClasses()
	{
		return (EReference)packageEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getPackage_OwnedInstances()
	{
		return (EReference)packageEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getPackage_OwnedPackages()
	{
		return (EReference)packageEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getPackage_OwnedProfileApplications()
	{
		return (EReference)packageEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getPackage_OwningPackage()
	{
		return (EReference)packageEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getNamespace() {
		return namespaceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getNamespace_OwnedConstraints()
	{
		return (EReference)namespaceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getPrecedence() {
		return precedenceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getPrecedence_Associativity() {
		return (EAttribute)precedenceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getPrecedence_Order() {
		return (EAttribute)precedenceEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getPrimitiveCompletePackage()
	{
		return primitiveCompletePackageEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getProperty() {
		return propertyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getProperty_AssociationClass()
	{
		return (EReference)propertyEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getProperty_IsReadOnly() {
		return (EAttribute)propertyEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getProperty_IsComposite() {
		return (EAttribute)propertyEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getProperty_IsDerived() {
		return (EAttribute)propertyEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getProperty_Opposite() {
		return (EReference)propertyEClass.getEStructuralFeatures().get(13);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getProperty_OwnedExpression()
	{
		return (EReference)propertyEClass.getEStructuralFeatures().get(14);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getProperty_OwningClass()
	{
		return (EReference)propertyEClass.getEStructuralFeatures().get(15);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getProperty_RedefinedProperties()
	{
		return (EReference)propertyEClass.getEStructuralFeatures().get(16);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getProperty_DefaultValue()
	{
		return (EAttribute)propertyEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getProperty_DefaultValueString()
	{
		return (EAttribute)propertyEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getProperty_IsID()
	{
		return (EAttribute)propertyEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getProperty_IsImplicit()
	{
		return (EAttribute)propertyEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getProperty_Keys()
	{
		return (EReference)propertyEClass.getEStructuralFeatures().get(12);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getProperty_IsResolveProxies()
	{
		return (EAttribute)propertyEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getProperty_IsTransient()
	{
		return (EAttribute)propertyEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getProperty_IsUnsettable()
	{
		return (EAttribute)propertyEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getProperty_IsVolatile()
	{
		return (EAttribute)propertyEClass.getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getProperty_SubsettedProperty()
	{
		return (EReference)propertyEClass.getEStructuralFeatures().get(18);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getProperty_ReferredProperty()
	{
		return (EReference)propertyEClass.getEStructuralFeatures().get(17);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EOperation getProperty__IsAttribute__Property()
	{
		return propertyEClass.getEOperations().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EOperation getProperty__ValidateCompatibleDefaultExpression__DiagnosticChain_Map()
	{
		return propertyEClass.getEOperations().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getTypedElement() {
		return typedElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getTypedElement_IsMany()
	{
		return (EAttribute)typedElementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getTypedElement_Type() {
		return (EReference)typedElementEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EOperation getTypedElement__CompatibleBody__ValueSpecification()
	{
		return typedElementEClass.getEOperations().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getTypedElement_IsRequired()
	{
		return (EAttribute)typedElementEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getAssociationClass() {
		return associationClassEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getAssociationClass_UnownedAttributes()
	{
		return (EReference)associationClassEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getOperation() {
		return operationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getOperation_OwningClass()
	{
		return (EReference)operationEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getOperation_RaisedExceptions()
	{
		return (EReference)operationEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getOperation_RedefinedOperations()
	{
		return (EReference)operationEClass.getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getOperation_BodyExpression()
	{
		return (EReference)operationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getOperation_IsInvalidating()
	{
		return (EAttribute)operationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * @since 1.3
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getOperation_IsTransient()
	{
		return (EAttribute)operationEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getOperation_IsTypeof()
	{
		return (EAttribute)operationEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getOperation_IsValidating()
	{
		return (EAttribute)operationEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getOperation_OwnedParameters()
	{
		return (EReference)operationEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getOperation_OwnedPostconditions()
	{
		return (EReference)operationEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getOperation_OwnedPreconditions()
	{
		return (EReference)operationEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getOperation_Precedence() {
		return (EReference)operationEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EOperation getOperation__ValidateCompatibleReturn__DiagnosticChain_Map()
	{
		return operationEClass.getEOperations().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EOperation getOperation__ValidateLoadableImplementation__DiagnosticChain_Map()
	{
		return operationEClass.getEOperations().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EOperation getOperation__ValidateUniquePreconditionName__DiagnosticChain_Map()
	{
		return operationEClass.getEOperations().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EOperation getOperation__ValidateUniquePostconditionName__DiagnosticChain_Map()
	{
		return operationEClass.getEOperations().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getParameter() {
		return parameterEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getParameter_IsTypeof()
	{
		return (EAttribute)parameterEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getParameter_OwningOperation()
	{
		return (EReference)parameterEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getParameterVariable()
	{
		return parameterVariableEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EOperation getParameterVariable__ValidateHasNoInitializer__DiagnosticChain_Map()
	{
		return parameterVariableEClass.getEOperations().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getVariableDeclaration() {
		return variableDeclarationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getVariableDeclaration_TypeValue()
	{
		return (EReference)variableDeclarationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EOperation getVariableDeclaration__ValidateNameIsNotNull__DiagnosticChain_Map()
	{
		return variableDeclarationEClass.getEOperations().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EOperation getVariableDeclaration__ValidateTypeIsNotInvalid__DiagnosticChain_Map()
	{
		return variableDeclarationEClass.getEOperations().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EOperation getVariableDeclaration__ValidateTypeIsNotNull__DiagnosticChain_Map()
	{
		return variableDeclarationEClass.getEOperations().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getOppositePropertyCallExp()
	{
		return oppositePropertyCallExpEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getOppositePropertyCallExp_ReferredProperty()
	{
		return (EReference)oppositePropertyCallExpEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getComment() {
		return commentEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getComment_AnnotatedElements()
	{
		return (EReference)commentEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getComment_Body() {
		return (EAttribute)commentEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getComment_OwningElement()
	{
		return (EReference)commentEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getCompleteClass()
	{
		return completeClassEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getCompleteClass_OwningCompletePackage()
	{
		return (EReference)completeClassEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getCompleteClass_PartialClasses()
	{
		return (EReference)completeClassEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getCompleteEnvironment()
	{
		return completeEnvironmentEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getCompleteEnvironment_OwnedCompleteModel()
	{
		return (EReference)completeEnvironmentEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getCompleteEnvironment_OwnedStandardLibrary()
	{
		return (EReference)completeEnvironmentEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getCompleteModel()
	{
		return completeModelEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getCompleteModel_OrphanCompletePackage()
	{
		return (EReference)completeModelEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getCompleteModel_OwnedCompletePackages()
	{
		return (EReference)completeModelEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getCompleteModel_OwningCompleteEnvironment()
	{
		return (EReference)completeModelEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getCompleteModel_PartialModels()
	{
		return (EReference)completeModelEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getCompleteModel_PrimitiveCompletePackage()
	{
		return (EReference)completeModelEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EOperation getCompleteModel__GetOwnedCompletePackage__String()
	{
		return completeModelEClass.getEOperations().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getCompletePackage()
	{
		return completePackageEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getCompletePackage_OwnedCompleteClasses()
	{
		return (EReference)completePackageEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getCompletePackage_OwnedCompletePackages()
	{
		return (EReference)completePackageEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getCompletePackage_OwningCompleteModel()
	{
		return (EReference)completePackageEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getCompletePackage_OwningCompletePackage()
	{
		return (EReference)completePackageEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getCompletePackage_PartialPackages()
	{
		return (EReference)completePackageEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EOperation getCompletePackage__GetOwnedCompleteClass__String()
	{
		return completePackageEClass.getEOperations().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getConnectionPointReference()
	{
		return connectionPointReferenceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getConnectionPointReference_Entries()
	{
		return (EReference)connectionPointReferenceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getConnectionPointReference_Exits()
	{
		return (EReference)connectionPointReferenceEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getConnectionPointReference_OwningState()
	{
		return (EReference)connectionPointReferenceEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getConstraint() {
		return constraintEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getConstraint_ConstrainedElements()
	{
		return (EReference)constraintEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getConstraint_Context() {
		return (EReference)constraintEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getConstraint_IsCallable()
	{
		return (EAttribute)constraintEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getConstraint_OwnedSpecification()
	{
		return (EReference)constraintEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getConstraint_OwningPostContext()
	{
		return (EReference)constraintEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getConstraint_OwningPreContext()
	{
		return (EReference)constraintEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getConstraint_OwningState()
	{
		return (EReference)constraintEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getConstraint_OwningTransition()
	{
		return (EReference)constraintEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getConstraint_RedefinedConstraints()
	{
		return (EReference)constraintEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EOperation getConstraint__ValidateUniqueName__DiagnosticChain_Map()
	{
		return constraintEClass.getEOperations().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getValueSpecification() {
		return valueSpecificationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EOperation getValueSpecification__IsComputable() {
		return valueSpecificationEClass.getEOperations().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EOperation getValueSpecification__IntegerValue() {
		return valueSpecificationEClass.getEOperations().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EOperation getValueSpecification__BooleanValue() {
		return valueSpecificationEClass.getEOperations().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EOperation getValueSpecification__StringValue() {
		return valueSpecificationEClass.getEOperations().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EOperation getValueSpecification__UnlimitedValue() {
		return valueSpecificationEClass.getEOperations().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EOperation getValueSpecification__IsNull() {
		return valueSpecificationEClass.getEOperations().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getNameable() {
		return nameableEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getDetail() {
		return detailEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getDetail_Values()
	{
		return (EAttribute)detailEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getDynamicBehavior()
	{
		return dynamicBehaviorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getDynamicElement()
	{
		return dynamicElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getDynamicElement_MetaType()
	{
		return (EReference)dynamicElementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getDynamicProperty()
	{
		return dynamicPropertyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getDynamicProperty_ReferredProperty()
	{
		return (EReference)dynamicPropertyEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getDynamicProperty_Default()
	{
		return (EAttribute)dynamicPropertyEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getDynamicType()
	{
		return dynamicTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getDynamicType_OwnedDynamicProperties()
	{
		return (EReference)dynamicTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getDynamicValueSpecification()
	{
		return dynamicValueSpecificationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getAnyType() {
		return anyTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getAssociationClassCallExp() {
		return associationClassCallExpEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getAssociationClassCallExp_ReferredAssociationClass() {
		return (EReference)associationClassCallExpEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getNavigationCallExp() {
		return navigationCallExpEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getNavigationCallExp_NavigationSource() {
		return (EReference)navigationCallExpEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getNavigationCallExp_Qualifiers()
	{
		return (EReference)navigationCallExpEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getFeatureCallExp() {
		return featureCallExpEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getFeatureCallExp_IsPre() {
		return (EAttribute)featureCallExpEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getFinalState()
	{
		return finalStateEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getCallExp() {
		return callExpEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCallExp_IsImplicit()
	{
		return (EAttribute)callExpEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCallExp_IsSafe()
	{
		return (EAttribute)callExpEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getCallExp_OwnedSource()
	{
		return (EReference)callExpEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EOperation getCallExp__ValidateTypeIsNotInvalid__DiagnosticChain_Map()
	{
		return callExpEClass.getEOperations().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EEnum getAssociativityKind() {
		return associativityKindEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EEnum getCollectionKind() {
		return collectionKindEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EEnum getPseudostateKind()
	{
		return pseudostateKindEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EEnum getTransitionKind()
	{
		return transitionKindEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EDataType getBoolean() {
		return booleanEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EDataType getInteger() {
		return integerEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EDataType getLibraryFeature()
	{
		return libraryFeatureEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EDataType getObject()
	{
		return objectEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EDataType getReal() {
		return realEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EDataType getString() {
		return stringEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EDataType getThrowable() {
		return throwableEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EDataType getUnlimitedNatural() {
		return unlimitedNaturalEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public PivotFactory getPivotFactory() {
		return (PivotFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents()
	{
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		annotationEClass = createEClass(ANNOTATION);
		createEReference(annotationEClass, ANNOTATION__OWNED_CONTENTS);
		createEReference(annotationEClass, ANNOTATION__OWNED_DETAILS);
		createEReference(annotationEClass, ANNOTATION__REFERENCES);

		anyTypeEClass = createEClass(ANY_TYPE);

		associationClassEClass = createEClass(ASSOCIATION_CLASS);
		createEReference(associationClassEClass, ASSOCIATION_CLASS__UNOWNED_ATTRIBUTES);

		associationClassCallExpEClass = createEClass(ASSOCIATION_CLASS_CALL_EXP);
		createEReference(associationClassCallExpEClass, ASSOCIATION_CLASS_CALL_EXP__REFERRED_ASSOCIATION_CLASS);

		bagTypeEClass = createEClass(BAG_TYPE);

		behaviorEClass = createEClass(BEHAVIOR);
		createEReference(behaviorEClass, BEHAVIOR__OWNING_TRANSITION);

		booleanLiteralExpEClass = createEClass(BOOLEAN_LITERAL_EXP);
		createEAttribute(booleanLiteralExpEClass, BOOLEAN_LITERAL_EXP__BOOLEAN_SYMBOL);
		createEOperation(booleanLiteralExpEClass, BOOLEAN_LITERAL_EXP___VALIDATE_TYPE_IS_BOOLEAN__DIAGNOSTICCHAIN_MAP);

		callExpEClass = createEClass(CALL_EXP);
		createEAttribute(callExpEClass, CALL_EXP__IS_IMPLICIT);
		createEAttribute(callExpEClass, CALL_EXP__IS_SAFE);
		createEReference(callExpEClass, CALL_EXP__OWNED_SOURCE);
		createEOperation(callExpEClass, CALL_EXP___VALIDATE_TYPE_IS_NOT_INVALID__DIAGNOSTICCHAIN_MAP);

		callOperationActionEClass = createEClass(CALL_OPERATION_ACTION);
		createEReference(callOperationActionEClass, CALL_OPERATION_ACTION__OPERATION);

		classEClass = createEClass(CLASS);
		createEReference(classEClass, CLASS__EXTENDERS);
		createEAttribute(classEClass, CLASS__INSTANCE_CLASS_NAME);
		createEAttribute(classEClass, CLASS__IS_ABSTRACT);
		createEAttribute(classEClass, CLASS__IS_ACTIVE);
		createEAttribute(classEClass, CLASS__IS_INTERFACE);
		createEReference(classEClass, CLASS__OWNED_BEHAVIORS);
		createEReference(classEClass, CLASS__OWNED_INVARIANTS);
		createEReference(classEClass, CLASS__OWNED_OPERATIONS);
		createEReference(classEClass, CLASS__OWNED_PROPERTIES);
		createEReference(classEClass, CLASS__OWNING_PACKAGE);
		createEReference(classEClass, CLASS__SUPER_CLASSES);
		createEOperation(classEClass, CLASS___VALIDATE_NAME_IS_NOT_NULL__DIAGNOSTICCHAIN_MAP);
		createEOperation(classEClass, CLASS___VALIDATE_UNIQUE_INVARIANT_NAME__DIAGNOSTICCHAIN_MAP);

		collectionItemEClass = createEClass(COLLECTION_ITEM);
		createEReference(collectionItemEClass, COLLECTION_ITEM__OWNED_ITEM);
		createEOperation(collectionItemEClass, COLLECTION_ITEM___VALIDATE_TYPE_IS_ITEM_TYPE__DIAGNOSTICCHAIN_MAP);

		collectionLiteralExpEClass = createEClass(COLLECTION_LITERAL_EXP);
		createEAttribute(collectionLiteralExpEClass, COLLECTION_LITERAL_EXP__KIND);
		createEReference(collectionLiteralExpEClass, COLLECTION_LITERAL_EXP__OWNED_PARTS);
		createEOperation(collectionLiteralExpEClass, COLLECTION_LITERAL_EXP___VALIDATE_BAG_KIND_IS_BAG__DIAGNOSTICCHAIN_MAP);
		createEOperation(collectionLiteralExpEClass, COLLECTION_LITERAL_EXP___VALIDATE_COLLECTION_KIND_IS_CONCRETE__DIAGNOSTICCHAIN_MAP);
		createEOperation(collectionLiteralExpEClass, COLLECTION_LITERAL_EXP___VALIDATE_ORDERED_SET_KIND_IS_ORDERED_SET__DIAGNOSTICCHAIN_MAP);
		createEOperation(collectionLiteralExpEClass, COLLECTION_LITERAL_EXP___VALIDATE_SEQUENCE_KIND_IS_SEQUENCE__DIAGNOSTICCHAIN_MAP);
		createEOperation(collectionLiteralExpEClass, COLLECTION_LITERAL_EXP___VALIDATE_SET_KIND_IS_SET__DIAGNOSTICCHAIN_MAP);

		collectionLiteralPartEClass = createEClass(COLLECTION_LITERAL_PART);
		createEOperation(collectionLiteralPartEClass, COLLECTION_LITERAL_PART___VALIDATE_TYPE_IS_NOT_INVALID__DIAGNOSTICCHAIN_MAP);

		collectionRangeEClass = createEClass(COLLECTION_RANGE);
		createEReference(collectionRangeEClass, COLLECTION_RANGE__OWNED_FIRST);
		createEReference(collectionRangeEClass, COLLECTION_RANGE__OWNED_LAST);

		collectionTypeEClass = createEClass(COLLECTION_TYPE);
		createEReference(collectionTypeEClass, COLLECTION_TYPE__ELEMENT_TYPE);
		createEAttribute(collectionTypeEClass, COLLECTION_TYPE__IS_NULL_FREE);
		createEAttribute(collectionTypeEClass, COLLECTION_TYPE__LOWER);
		createEAttribute(collectionTypeEClass, COLLECTION_TYPE__UPPER);

		commentEClass = createEClass(COMMENT);
		createEReference(commentEClass, COMMENT__ANNOTATED_ELEMENTS);
		createEAttribute(commentEClass, COMMENT__BODY);
		createEReference(commentEClass, COMMENT__OWNING_ELEMENT);

		completeClassEClass = createEClass(COMPLETE_CLASS);
		createEReference(completeClassEClass, COMPLETE_CLASS__OWNING_COMPLETE_PACKAGE);
		createEReference(completeClassEClass, COMPLETE_CLASS__PARTIAL_CLASSES);

		completeEnvironmentEClass = createEClass(COMPLETE_ENVIRONMENT);
		createEReference(completeEnvironmentEClass, COMPLETE_ENVIRONMENT__OWNED_COMPLETE_MODEL);
		createEReference(completeEnvironmentEClass, COMPLETE_ENVIRONMENT__OWNED_STANDARD_LIBRARY);

		completeModelEClass = createEClass(COMPLETE_MODEL);
		createEReference(completeModelEClass, COMPLETE_MODEL__ORPHAN_COMPLETE_PACKAGE);
		createEReference(completeModelEClass, COMPLETE_MODEL__OWNED_COMPLETE_PACKAGES);
		createEReference(completeModelEClass, COMPLETE_MODEL__OWNING_COMPLETE_ENVIRONMENT);
		createEReference(completeModelEClass, COMPLETE_MODEL__PARTIAL_MODELS);
		createEReference(completeModelEClass, COMPLETE_MODEL__PRIMITIVE_COMPLETE_PACKAGE);
		createEOperation(completeModelEClass, COMPLETE_MODEL___GET_OWNED_COMPLETE_PACKAGE__STRING);

		completePackageEClass = createEClass(COMPLETE_PACKAGE);
		createEReference(completePackageEClass, COMPLETE_PACKAGE__OWNED_COMPLETE_CLASSES);
		createEReference(completePackageEClass, COMPLETE_PACKAGE__OWNED_COMPLETE_PACKAGES);
		createEReference(completePackageEClass, COMPLETE_PACKAGE__OWNING_COMPLETE_MODEL);
		createEReference(completePackageEClass, COMPLETE_PACKAGE__OWNING_COMPLETE_PACKAGE);
		createEReference(completePackageEClass, COMPLETE_PACKAGE__PARTIAL_PACKAGES);
		createEOperation(completePackageEClass, COMPLETE_PACKAGE___GET_OWNED_COMPLETE_CLASS__STRING);

		connectionPointReferenceEClass = createEClass(CONNECTION_POINT_REFERENCE);
		createEReference(connectionPointReferenceEClass, CONNECTION_POINT_REFERENCE__ENTRIES);
		createEReference(connectionPointReferenceEClass, CONNECTION_POINT_REFERENCE__EXITS);
		createEReference(connectionPointReferenceEClass, CONNECTION_POINT_REFERENCE__OWNING_STATE);

		constraintEClass = createEClass(CONSTRAINT);
		createEReference(constraintEClass, CONSTRAINT__CONSTRAINED_ELEMENTS);
		createEReference(constraintEClass, CONSTRAINT__CONTEXT);
		createEAttribute(constraintEClass, CONSTRAINT__IS_CALLABLE);
		createEReference(constraintEClass, CONSTRAINT__OWNED_SPECIFICATION);
		createEReference(constraintEClass, CONSTRAINT__OWNING_POST_CONTEXT);
		createEReference(constraintEClass, CONSTRAINT__OWNING_PRE_CONTEXT);
		createEReference(constraintEClass, CONSTRAINT__OWNING_STATE);
		createEReference(constraintEClass, CONSTRAINT__OWNING_TRANSITION);
		createEReference(constraintEClass, CONSTRAINT__REDEFINED_CONSTRAINTS);
		createEOperation(constraintEClass, CONSTRAINT___VALIDATE_UNIQUE_NAME__DIAGNOSTICCHAIN_MAP);

		dataTypeEClass = createEClass(DATA_TYPE);
		createEReference(dataTypeEClass, DATA_TYPE__BEHAVIORAL_CLASS);
		createEAttribute(dataTypeEClass, DATA_TYPE__IS_SERIALIZABLE);
		createEAttribute(dataTypeEClass, DATA_TYPE__VALUE);

		detailEClass = createEClass(DETAIL);
		createEAttribute(detailEClass, DETAIL__VALUES);

		dynamicBehaviorEClass = createEClass(DYNAMIC_BEHAVIOR);

		dynamicElementEClass = createEClass(DYNAMIC_ELEMENT);
		createEReference(dynamicElementEClass, DYNAMIC_ELEMENT__META_TYPE);

		dynamicPropertyEClass = createEClass(DYNAMIC_PROPERTY);
		createEAttribute(dynamicPropertyEClass, DYNAMIC_PROPERTY__DEFAULT);
		createEReference(dynamicPropertyEClass, DYNAMIC_PROPERTY__REFERRED_PROPERTY);

		dynamicTypeEClass = createEClass(DYNAMIC_TYPE);
		createEReference(dynamicTypeEClass, DYNAMIC_TYPE__OWNED_DYNAMIC_PROPERTIES);

		dynamicValueSpecificationEClass = createEClass(DYNAMIC_VALUE_SPECIFICATION);

		elementEClass = createEClass(ELEMENT);
		createEReference(elementEClass, ELEMENT__ANNOTATING_COMMENTS);
		createEReference(elementEClass, ELEMENT__OWNED_ANNOTATIONS);
		createEReference(elementEClass, ELEMENT__OWNED_COMMENTS);
		createEReference(elementEClass, ELEMENT__OWNED_EXTENSIONS);
		createEOperation(elementEClass, ELEMENT___ALL_OWNED_ELEMENTS);
		createEOperation(elementEClass, ELEMENT___GET_VALUE__TYPE_STRING);

		elementExtensionEClass = createEClass(ELEMENT_EXTENSION);
		createEReference(elementExtensionEClass, ELEMENT_EXTENSION__BASE);
		createEAttribute(elementExtensionEClass, ELEMENT_EXTENSION__IS_APPLIED);
		createEAttribute(elementExtensionEClass, ELEMENT_EXTENSION__IS_REQUIRED);
		createEReference(elementExtensionEClass, ELEMENT_EXTENSION__STEREOTYPE);

		enumLiteralExpEClass = createEClass(ENUM_LITERAL_EXP);
		createEReference(enumLiteralExpEClass, ENUM_LITERAL_EXP__REFERRED_LITERAL);
		createEOperation(enumLiteralExpEClass, ENUM_LITERAL_EXP___VALIDATE_TYPE_IS_ENUMERATION_TYPE__DIAGNOSTICCHAIN_MAP);

		enumerationEClass = createEClass(ENUMERATION);
		createEReference(enumerationEClass, ENUMERATION__OWNED_LITERALS);

		enumerationLiteralEClass = createEClass(ENUMERATION_LITERAL);
		createEReference(enumerationLiteralEClass, ENUMERATION_LITERAL__OWNING_ENUMERATION);
		createEAttribute(enumerationLiteralEClass, ENUMERATION_LITERAL__VALUE);

		expressionInOCLEClass = createEClass(EXPRESSION_IN_OCL);
		createEReference(expressionInOCLEClass, EXPRESSION_IN_OCL__OWNED_BODY);
		createEReference(expressionInOCLEClass, EXPRESSION_IN_OCL__OWNED_CONTEXT);
		createEReference(expressionInOCLEClass, EXPRESSION_IN_OCL__OWNED_PARAMETERS);
		createEReference(expressionInOCLEClass, EXPRESSION_IN_OCL__OWNED_RESULT);

		featureEClass = createEClass(FEATURE);
		createEAttribute(featureEClass, FEATURE__IMPLEMENTATION);
		createEAttribute(featureEClass, FEATURE__IMPLEMENTATION_CLASS);
		createEAttribute(featureEClass, FEATURE__IS_STATIC);
		createEOperation(featureEClass, FEATURE___VALIDATE_NAME_IS_NOT_NULL__DIAGNOSTICCHAIN_MAP);
		createEOperation(featureEClass, FEATURE___VALIDATE_TYPE_IS_NOT_INVALID__DIAGNOSTICCHAIN_MAP);
		createEOperation(featureEClass, FEATURE___VALIDATE_TYPE_IS_NOT_NULL__DIAGNOSTICCHAIN_MAP);

		featureCallExpEClass = createEClass(FEATURE_CALL_EXP);
		createEAttribute(featureCallExpEClass, FEATURE_CALL_EXP__IS_PRE);

		finalStateEClass = createEClass(FINAL_STATE);

		ifExpEClass = createEClass(IF_EXP);
		createEAttribute(ifExpEClass, IF_EXP__IS_ELSE_IF);
		createEReference(ifExpEClass, IF_EXP__OWNED_CONDITION);
		createEReference(ifExpEClass, IF_EXP__OWNED_ELSE);
		createEReference(ifExpEClass, IF_EXP__OWNED_THEN);
		createEOperation(ifExpEClass, IF_EXP___VALIDATE_CONDITION_TYPE_IS_BOOLEAN__DIAGNOSTICCHAIN_MAP);
		createEOperation(ifExpEClass, IF_EXP___VALIDATE_TYPE_IS_NOT_INVALID__DIAGNOSTICCHAIN_MAP);

		importEClass = createEClass(IMPORT);
		createEReference(importEClass, IMPORT__IMPORTED_NAMESPACE);
		createEAttribute(importEClass, IMPORT__XMIID_VERSION);

		instanceSpecificationEClass = createEClass(INSTANCE_SPECIFICATION);
		createEReference(instanceSpecificationEClass, INSTANCE_SPECIFICATION__CLASSES);
		createEReference(instanceSpecificationEClass, INSTANCE_SPECIFICATION__OWNED_SLOTS);
		createEReference(instanceSpecificationEClass, INSTANCE_SPECIFICATION__OWNED_SPECIFICATION);
		createEReference(instanceSpecificationEClass, INSTANCE_SPECIFICATION__OWNING_PACKAGE);

		integerLiteralExpEClass = createEClass(INTEGER_LITERAL_EXP);
		createEAttribute(integerLiteralExpEClass, INTEGER_LITERAL_EXP__INTEGER_SYMBOL);
		createEOperation(integerLiteralExpEClass, INTEGER_LITERAL_EXP___VALIDATE_TYPE_IS_INTEGER__DIAGNOSTICCHAIN_MAP);

		invalidLiteralExpEClass = createEClass(INVALID_LITERAL_EXP);

		invalidTypeEClass = createEClass(INVALID_TYPE);

		iterateExpEClass = createEClass(ITERATE_EXP);
		createEReference(iterateExpEClass, ITERATE_EXP__OWNED_RESULT);
		createEOperation(iterateExpEClass, ITERATE_EXP___VALIDATE_BODY_TYPE_CONFORMS_TO_RESULT_TYPE__DIAGNOSTICCHAIN_MAP);
		createEOperation(iterateExpEClass, ITERATE_EXP___VALIDATE_ONE_INITIALIZER__DIAGNOSTICCHAIN_MAP);
		createEOperation(iterateExpEClass, ITERATE_EXP___VALIDATE_SAFE_ITERATOR_IS_REQUIRED__DIAGNOSTICCHAIN_MAP);
		createEOperation(iterateExpEClass, ITERATE_EXP___VALIDATE_SAFE_SOURCE_CAN_BE_NULL__DIAGNOSTICCHAIN_MAP);
		createEOperation(iterateExpEClass, ITERATE_EXP___VALIDATE_TYPE_IS_RESULT_TYPE__DIAGNOSTICCHAIN_MAP);
		createEOperation(iterateExpEClass, ITERATE_EXP___VALIDATE_UNSAFE_SOURCE_CAN_NOT_BE_NULL__DIAGNOSTICCHAIN_MAP);

		iterationEClass = createEClass(ITERATION);
		createEReference(iterationEClass, ITERATION__OWNED_ACCUMULATORS);
		createEReference(iterationEClass, ITERATION__OWNED_ITERATORS);

		iteratorExpEClass = createEClass(ITERATOR_EXP);
		createEOperation(iteratorExpEClass, ITERATOR_EXP___VALIDATE_ANY_BODY_TYPE_IS_BOOLEAN__DIAGNOSTICCHAIN_MAP);
		createEOperation(iteratorExpEClass, ITERATOR_EXP___VALIDATE_ANY_HAS_ONE_ITERATOR__DIAGNOSTICCHAIN_MAP);
		createEOperation(iteratorExpEClass, ITERATOR_EXP___VALIDATE_ANY_TYPE_IS_SOURCE_ELEMENT_TYPE__DIAGNOSTICCHAIN_MAP);
		createEOperation(iteratorExpEClass, ITERATOR_EXP___VALIDATE_CLOSURE_BODY_ELEMENT_TYPE_IS_ITERATOR_TYPE__DIAGNOSTICCHAIN_MAP);
		createEOperation(iteratorExpEClass, ITERATOR_EXP___VALIDATE_CLOSURE_BODY_TYPE_IS_CONFORMANTTO_ITERATOR_TYPE__DIAGNOSTICCHAIN_MAP);
		createEOperation(iteratorExpEClass, ITERATOR_EXP___VALIDATE_CLOSURE_ELEMENT_TYPE_IS_SOURCE_ELEMENT_TYPE__DIAGNOSTICCHAIN_MAP);
		createEOperation(iteratorExpEClass, ITERATOR_EXP___VALIDATE_CLOSURE_HAS_ONE_ITERATOR__DIAGNOSTICCHAIN_MAP);
		createEOperation(iteratorExpEClass, ITERATOR_EXP___VALIDATE_CLOSURE_SOURCE_ELEMENT_TYPE_IS_BODY_ELEMENT_TYPE__DIAGNOSTICCHAIN_MAP);
		createEOperation(iteratorExpEClass, ITERATOR_EXP___VALIDATE_CLOSURE_TYPE_IS_UNIQUE_COLLECTION__DIAGNOSTICCHAIN_MAP);
		createEOperation(iteratorExpEClass, ITERATOR_EXP___VALIDATE_COLLECT_ELEMENT_TYPE_IS_FLATTENED_BODY_TYPE__DIAGNOSTICCHAIN_MAP);
		createEOperation(iteratorExpEClass, ITERATOR_EXP___VALIDATE_COLLECT_TYPE_IS_UNORDERED__DIAGNOSTICCHAIN_MAP);
		createEOperation(iteratorExpEClass, ITERATOR_EXP___VALIDATE_ITERATOR_TYPE_IS_SOURCE_ELEMENT_TYPE__DIAGNOSTICCHAIN_MAP);
		createEOperation(iteratorExpEClass, ITERATOR_EXP___VALIDATE_SAFE_ITERATOR_IS_REQUIRED__DIAGNOSTICCHAIN_MAP);
		createEOperation(iteratorExpEClass, ITERATOR_EXP___VALIDATE_SAFE_SOURCE_CAN_BE_NULL__DIAGNOSTICCHAIN_MAP);
		createEOperation(iteratorExpEClass, ITERATOR_EXP___VALIDATE_SORTED_BY_ELEMENT_TYPE_IS_SOURCE_ELEMENT_TYPE__DIAGNOSTICCHAIN_MAP);
		createEOperation(iteratorExpEClass, ITERATOR_EXP___VALIDATE_SORTED_BY_IS_ORDERED_IF_SOURCE_IS_ORDERED__DIAGNOSTICCHAIN_MAP);
		createEOperation(iteratorExpEClass, ITERATOR_EXP___VALIDATE_SORTED_BY_ITERATOR_TYPE_IS_COMPARABLE__DIAGNOSTICCHAIN_MAP);
		createEOperation(iteratorExpEClass, ITERATOR_EXP___VALIDATE_UNSAFE_SOURCE_CAN_NOT_BE_NULL__DIAGNOSTICCHAIN_MAP);

		iteratorVariableEClass = createEClass(ITERATOR_VARIABLE);
		createEOperation(iteratorVariableEClass, ITERATOR_VARIABLE___VALIDATE_HAS_NO_INITIALIZER__DIAGNOSTICCHAIN_MAP);

		lambdaTypeEClass = createEClass(LAMBDA_TYPE);
		createEReference(lambdaTypeEClass, LAMBDA_TYPE__CONTEXT_TYPE);
		createEReference(lambdaTypeEClass, LAMBDA_TYPE__PARAMETER_TYPE);
		createEReference(lambdaTypeEClass, LAMBDA_TYPE__RESULT_TYPE);

		languageExpressionEClass = createEClass(LANGUAGE_EXPRESSION);
		createEAttribute(languageExpressionEClass, LANGUAGE_EXPRESSION__BODY);
		createEAttribute(languageExpressionEClass, LANGUAGE_EXPRESSION__LANGUAGE);
		createEReference(languageExpressionEClass, LANGUAGE_EXPRESSION__OWNING_CONSTRAINT);

		letExpEClass = createEClass(LET_EXP);
		createEReference(letExpEClass, LET_EXP__OWNED_IN);
		createEReference(letExpEClass, LET_EXP__OWNED_VARIABLE);
		createEOperation(letExpEClass, LET_EXP___VALIDATE_COMPATIBLE_NULLITY_FOR_IN__DIAGNOSTICCHAIN_MAP);
		createEOperation(letExpEClass, LET_EXP___VALIDATE_TYPE_IS_IN_TYPE__DIAGNOSTICCHAIN_MAP);
		createEOperation(letExpEClass, LET_EXP___VALIDATE_TYPE_IS_NOT_INVALID__DIAGNOSTICCHAIN_MAP);

		letVariableEClass = createEClass(LET_VARIABLE);
		createEOperation(letVariableEClass, LET_VARIABLE___VALIDATE_COMPATIBLE_NULLITY_FOR_INITIALIZER__DIAGNOSTICCHAIN_MAP);
		createEOperation(letVariableEClass, LET_VARIABLE___VALIDATE_COMPATIBLE_TYPE_FOR_INITIALIZER__DIAGNOSTICCHAIN_MAP);
		createEOperation(letVariableEClass, LET_VARIABLE___VALIDATE_HAS_INITIALIZER__DIAGNOSTICCHAIN_MAP);

		libraryEClass = createEClass(LIBRARY);
		createEReference(libraryEClass, LIBRARY__OWNED_PRECEDENCES);

		literalExpEClass = createEClass(LITERAL_EXP);

		loopExpEClass = createEClass(LOOP_EXP);
		createEReference(loopExpEClass, LOOP_EXP__OWNED_BODY);
		createEReference(loopExpEClass, LOOP_EXP__OWNED_ITERATORS);
		createEReference(loopExpEClass, LOOP_EXP__REFERRED_ITERATION);
		createEOperation(loopExpEClass, LOOP_EXP___VALIDATE_NO_INITIALIZERS__DIAGNOSTICCHAIN_MAP);
		createEOperation(loopExpEClass, LOOP_EXP___VALIDATE_SOURCE_IS_COLLECTION__DIAGNOSTICCHAIN_MAP);

		mapLiteralExpEClass = createEClass(MAP_LITERAL_EXP);
		createEReference(mapLiteralExpEClass, MAP_LITERAL_EXP__OWNED_PARTS);

		mapLiteralPartEClass = createEClass(MAP_LITERAL_PART);
		createEReference(mapLiteralPartEClass, MAP_LITERAL_PART__OWNED_KEY);
		createEReference(mapLiteralPartEClass, MAP_LITERAL_PART__OWNED_VALUE);

		mapTypeEClass = createEClass(MAP_TYPE);
		createEReference(mapTypeEClass, MAP_TYPE__KEY_TYPE);
		createEReference(mapTypeEClass, MAP_TYPE__VALUE_TYPE);

		messageExpEClass = createEClass(MESSAGE_EXP);
		createEReference(messageExpEClass, MESSAGE_EXP__OWNED_ARGUMENTS);
		createEReference(messageExpEClass, MESSAGE_EXP__OWNED_CALLED_OPERATION);
		createEReference(messageExpEClass, MESSAGE_EXP__OWNED_SENT_SIGNAL);
		createEReference(messageExpEClass, MESSAGE_EXP__OWNED_TARGET);
		createEOperation(messageExpEClass, MESSAGE_EXP___VALIDATE_ONE_CALL_OR_ONE_SEND__DIAGNOSTICCHAIN_MAP);
		createEOperation(messageExpEClass, MESSAGE_EXP___VALIDATE_TARGET_IS_NOT_ACOLLECTION__DIAGNOSTICCHAIN_MAP);

		messageTypeEClass = createEClass(MESSAGE_TYPE);
		createEReference(messageTypeEClass, MESSAGE_TYPE__REFERRED_OPERATION);
		createEReference(messageTypeEClass, MESSAGE_TYPE__REFERRED_SIGNAL);

		modelEClass = createEClass(MODEL);
		createEAttribute(modelEClass, MODEL__EXTERNAL_URI);
		createEReference(modelEClass, MODEL__OWNED_IMPORTS);
		createEReference(modelEClass, MODEL__OWNED_PACKAGES);
		createEAttribute(modelEClass, MODEL__XMIID_VERSION);

		morePivotableEClass = createEClass(MORE_PIVOTABLE);

		nameableEClass = createEClass(NAMEABLE);

		namedElementEClass = createEClass(NAMED_ELEMENT);
		createEAttribute(namedElementEClass, NAMED_ELEMENT__NAME);

		namespaceEClass = createEClass(NAMESPACE);
		createEReference(namespaceEClass, NAMESPACE__OWNED_CONSTRAINTS);

		navigationCallExpEClass = createEClass(NAVIGATION_CALL_EXP);
		createEReference(navigationCallExpEClass, NAVIGATION_CALL_EXP__NAVIGATION_SOURCE);
		createEReference(navigationCallExpEClass, NAVIGATION_CALL_EXP__QUALIFIERS);

		nullLiteralExpEClass = createEClass(NULL_LITERAL_EXP);

		numericLiteralExpEClass = createEClass(NUMERIC_LITERAL_EXP);

		oclExpressionEClass = createEClass(OCL_EXPRESSION);
		createEReference(oclExpressionEClass, OCL_EXPRESSION__TYPE_VALUE);
		createEOperation(oclExpressionEClass, OCL_EXPRESSION___IS_NON_NULL);
		createEOperation(oclExpressionEClass, OCL_EXPRESSION___IS_NULL);
		createEOperation(oclExpressionEClass, OCL_EXPRESSION___VALIDATE_TYPE_IS_NOT_NULL__DIAGNOSTICCHAIN_MAP);

		operationEClass = createEClass(OPERATION);
		createEReference(operationEClass, OPERATION__BODY_EXPRESSION);
		createEAttribute(operationEClass, OPERATION__IS_INVALIDATING);
		createEAttribute(operationEClass, OPERATION__IS_TRANSIENT);
		createEAttribute(operationEClass, OPERATION__IS_TYPEOF);
		createEAttribute(operationEClass, OPERATION__IS_VALIDATING);
		createEReference(operationEClass, OPERATION__OWNED_PARAMETERS);
		createEReference(operationEClass, OPERATION__OWNED_POSTCONDITIONS);
		createEReference(operationEClass, OPERATION__OWNED_PRECONDITIONS);
		createEReference(operationEClass, OPERATION__OWNING_CLASS);
		createEReference(operationEClass, OPERATION__PRECEDENCE);
		createEReference(operationEClass, OPERATION__RAISED_EXCEPTIONS);
		createEReference(operationEClass, OPERATION__REDEFINED_OPERATIONS);
		createEOperation(operationEClass, OPERATION___VALIDATE_COMPATIBLE_RETURN__DIAGNOSTICCHAIN_MAP);
		createEOperation(operationEClass, OPERATION___VALIDATE_LOADABLE_IMPLEMENTATION__DIAGNOSTICCHAIN_MAP);
		createEOperation(operationEClass, OPERATION___VALIDATE_UNIQUE_POSTCONDITION_NAME__DIAGNOSTICCHAIN_MAP);
		createEOperation(operationEClass, OPERATION___VALIDATE_UNIQUE_PRECONDITION_NAME__DIAGNOSTICCHAIN_MAP);

		operationCallExpEClass = createEClass(OPERATION_CALL_EXP);
		createEAttribute(operationCallExpEClass, OPERATION_CALL_EXP__IS_VIRTUAL);
		createEReference(operationCallExpEClass, OPERATION_CALL_EXP__OWNED_ARGUMENTS);
		createEReference(operationCallExpEClass, OPERATION_CALL_EXP__REFERRED_OPERATION);
		createEOperation(operationCallExpEClass, OPERATION_CALL_EXP___VALIDATE_ARGUMENT_COUNT__DIAGNOSTICCHAIN_MAP);
		createEOperation(operationCallExpEClass, OPERATION_CALL_EXP___VALIDATE_ARGUMENT_TYPE_IS_CONFORMANT__DIAGNOSTICCHAIN_MAP);
		createEOperation(operationCallExpEClass, OPERATION_CALL_EXP___VALIDATE_SAFE_SOURCE_CAN_BE_NULL__DIAGNOSTICCHAIN_MAP);

		oppositePropertyCallExpEClass = createEClass(OPPOSITE_PROPERTY_CALL_EXP);
		createEReference(oppositePropertyCallExpEClass, OPPOSITE_PROPERTY_CALL_EXP__REFERRED_PROPERTY);

		orderedSetTypeEClass = createEClass(ORDERED_SET_TYPE);

		orphanCompletePackageEClass = createEClass(ORPHAN_COMPLETE_PACKAGE);

		packageEClass = createEClass(PACKAGE);
		createEAttribute(packageEClass, PACKAGE__URI);
		createEReference(packageEClass, PACKAGE__IMPORTED_PACKAGES);
		createEAttribute(packageEClass, PACKAGE__NS_PREFIX);
		createEReference(packageEClass, PACKAGE__OWNED_CLASSES);
		createEReference(packageEClass, PACKAGE__OWNED_INSTANCES);
		createEReference(packageEClass, PACKAGE__OWNED_PACKAGES);
		createEReference(packageEClass, PACKAGE__OWNED_PROFILE_APPLICATIONS);
		createEReference(packageEClass, PACKAGE__OWNING_PACKAGE);

		parameterEClass = createEClass(PARAMETER);
		createEAttribute(parameterEClass, PARAMETER__IS_TYPEOF);
		createEReference(parameterEClass, PARAMETER__OWNING_OPERATION);

		parameterVariableEClass = createEClass(PARAMETER_VARIABLE);
		createEOperation(parameterVariableEClass, PARAMETER_VARIABLE___VALIDATE_HAS_NO_INITIALIZER__DIAGNOSTICCHAIN_MAP);

		pivotableEClass = createEClass(PIVOTABLE);

		precedenceEClass = createEClass(PRECEDENCE);
		createEAttribute(precedenceEClass, PRECEDENCE__ASSOCIATIVITY);
		createEAttribute(precedenceEClass, PRECEDENCE__ORDER);

		primitiveCompletePackageEClass = createEClass(PRIMITIVE_COMPLETE_PACKAGE);

		primitiveLiteralExpEClass = createEClass(PRIMITIVE_LITERAL_EXP);

		primitiveTypeEClass = createEClass(PRIMITIVE_TYPE);
		createEReference(primitiveTypeEClass, PRIMITIVE_TYPE__COERCIONS);

		profileEClass = createEClass(PROFILE);
		createEReference(profileEClass, PROFILE__PROFILE_APPLICATIONS);

		profileApplicationEClass = createEClass(PROFILE_APPLICATION);
		createEReference(profileApplicationEClass, PROFILE_APPLICATION__APPLIED_PROFILE);
		createEAttribute(profileApplicationEClass, PROFILE_APPLICATION__IS_STRICT);
		createEReference(profileApplicationEClass, PROFILE_APPLICATION__OWNING_PACKAGE);

		propertyEClass = createEClass(PROPERTY);
		createEReference(propertyEClass, PROPERTY__ASSOCIATION_CLASS);
		createEAttribute(propertyEClass, PROPERTY__DEFAULT_VALUE);
		createEAttribute(propertyEClass, PROPERTY__DEFAULT_VALUE_STRING);
		createEAttribute(propertyEClass, PROPERTY__IS_COMPOSITE);
		createEAttribute(propertyEClass, PROPERTY__IS_DERIVED);
		createEAttribute(propertyEClass, PROPERTY__IS_ID);
		createEAttribute(propertyEClass, PROPERTY__IS_IMPLICIT);
		createEAttribute(propertyEClass, PROPERTY__IS_READ_ONLY);
		createEAttribute(propertyEClass, PROPERTY__IS_RESOLVE_PROXIES);
		createEAttribute(propertyEClass, PROPERTY__IS_TRANSIENT);
		createEAttribute(propertyEClass, PROPERTY__IS_UNSETTABLE);
		createEAttribute(propertyEClass, PROPERTY__IS_VOLATILE);
		createEReference(propertyEClass, PROPERTY__KEYS);
		createEReference(propertyEClass, PROPERTY__OPPOSITE);
		createEReference(propertyEClass, PROPERTY__OWNED_EXPRESSION);
		createEReference(propertyEClass, PROPERTY__OWNING_CLASS);
		createEReference(propertyEClass, PROPERTY__REDEFINED_PROPERTIES);
		createEReference(propertyEClass, PROPERTY__REFERRED_PROPERTY);
		createEReference(propertyEClass, PROPERTY__SUBSETTED_PROPERTY);
		createEOperation(propertyEClass, PROPERTY___IS_ATTRIBUTE__PROPERTY);
		createEOperation(propertyEClass, PROPERTY___VALIDATE_COMPATIBLE_DEFAULT_EXPRESSION__DIAGNOSTICCHAIN_MAP);

		propertyCallExpEClass = createEClass(PROPERTY_CALL_EXP);
		createEReference(propertyCallExpEClass, PROPERTY_CALL_EXP__REFERRED_PROPERTY);
		createEOperation(propertyCallExpEClass, PROPERTY_CALL_EXP___GET_SPECIALIZED_REFERRED_PROPERTY_OWNING_TYPE);
		createEOperation(propertyCallExpEClass, PROPERTY_CALL_EXP___GET_SPECIALIZED_REFERRED_PROPERTY_TYPE);
		createEOperation(propertyCallExpEClass, PROPERTY_CALL_EXP___VALIDATE_COMPATIBLE_RESULT_TYPE__DIAGNOSTICCHAIN_MAP);
		createEOperation(propertyCallExpEClass, PROPERTY_CALL_EXP___VALIDATE_NON_STATIC_SOURCE_TYPE_IS_CONFORMANT__DIAGNOSTICCHAIN_MAP);
		createEOperation(propertyCallExpEClass, PROPERTY_CALL_EXP___VALIDATE_SAFE_SOURCE_CAN_BE_NULL__DIAGNOSTICCHAIN_MAP);
		createEOperation(propertyCallExpEClass, PROPERTY_CALL_EXP___VALIDATE_UNSAFE_SOURCE_CAN_NOT_BE_NULL__DIAGNOSTICCHAIN_MAP);

		pseudostateEClass = createEClass(PSEUDOSTATE);
		createEAttribute(pseudostateEClass, PSEUDOSTATE__KIND);
		createEReference(pseudostateEClass, PSEUDOSTATE__OWNING_STATE);
		createEReference(pseudostateEClass, PSEUDOSTATE__OWNING_STATE_MACHINE);

		realLiteralExpEClass = createEClass(REAL_LITERAL_EXP);
		createEAttribute(realLiteralExpEClass, REAL_LITERAL_EXP__REAL_SYMBOL);

		referringElementEClass = createEClass(REFERRING_ELEMENT);
		createEOperation(referringElementEClass, REFERRING_ELEMENT___GET_REFERRED_ELEMENT);

		regionEClass = createEClass(REGION);
		createEReference(regionEClass, REGION__EXTENDED_REGION);
		createEReference(regionEClass, REGION__OWNED_SUBVERTEXES);
		createEReference(regionEClass, REGION__OWNED_TRANSITIONS);
		createEReference(regionEClass, REGION__OWNING_STATE);
		createEReference(regionEClass, REGION__OWNING_STATE_MACHINE);

		resultVariableEClass = createEClass(RESULT_VARIABLE);
		createEOperation(resultVariableEClass, RESULT_VARIABLE___VALIDATE_COMPATIBLE_NULLITY_FOR_INITIALIZER__DIAGNOSTICCHAIN_MAP);
		createEOperation(resultVariableEClass, RESULT_VARIABLE___VALIDATE_COMPATIBLE_TYPE_FOR_INITIALIZER__DIAGNOSTICCHAIN_MAP);
		createEOperation(resultVariableEClass, RESULT_VARIABLE___VALIDATE_HAS_INITIALIZER__DIAGNOSTICCHAIN_MAP);

		selfTypeEClass = createEClass(SELF_TYPE);
		createEOperation(selfTypeEClass, SELF_TYPE___SPECIALIZE_IN__CALLEXP_TYPE);

		sendSignalActionEClass = createEClass(SEND_SIGNAL_ACTION);
		createEReference(sendSignalActionEClass, SEND_SIGNAL_ACTION__SIGNAL);

		sequenceTypeEClass = createEClass(SEQUENCE_TYPE);

		setTypeEClass = createEClass(SET_TYPE);

		shadowExpEClass = createEClass(SHADOW_EXP);
		createEReference(shadowExpEClass, SHADOW_EXP__OWNED_PARTS);
		createEAttribute(shadowExpEClass, SHADOW_EXP__VALUE);
		createEOperation(shadowExpEClass, SHADOW_EXP___VALIDATE_CLASS_HAS_NO_STRING_VALUE_INITIALIZER__DIAGNOSTICCHAIN_MAP);
		createEOperation(shadowExpEClass, SHADOW_EXP___VALIDATE_DATA_TYPE_HAS_NO_PART_INITIALIZERS__DIAGNOSTICCHAIN_MAP);
		createEOperation(shadowExpEClass, SHADOW_EXP___VALIDATE_DATA_TYPE_HAS_ONE_PART_INITIALIZER__DIAGNOSTICCHAIN_MAP);
		createEOperation(shadowExpEClass, SHADOW_EXP___VALIDATE_DATA_TYPE_HAS_STRING_VALUE_INITIALIZER__DIAGNOSTICCHAIN_MAP);
		createEOperation(shadowExpEClass, SHADOW_EXP___VALIDATE_INITIALIZES_ALL_CLASS_PROPERTIES__DIAGNOSTICCHAIN_MAP);
		createEOperation(shadowExpEClass, SHADOW_EXP___VALIDATE_TYPE_IS_NOT_INVALID__DIAGNOSTICCHAIN_MAP);

		shadowPartEClass = createEClass(SHADOW_PART);
		createEReference(shadowPartEClass, SHADOW_PART__OWNED_INIT);
		createEReference(shadowPartEClass, SHADOW_PART__REFERRED_PROPERTY);
		createEOperation(shadowPartEClass, SHADOW_PART___VALIDATE_COMPATIBLE_INITIALISER_TYPE__DIAGNOSTICCHAIN_MAP);
		createEOperation(shadowPartEClass, SHADOW_PART___VALIDATE_TYPE_IS_NOT_INVALID__DIAGNOSTICCHAIN_MAP);
		createEOperation(shadowPartEClass, SHADOW_PART___VALIDATE_TYPE_IS_NOT_NULL__DIAGNOSTICCHAIN_MAP);

		signalEClass = createEClass(SIGNAL);

		slotEClass = createEClass(SLOT);
		createEReference(slotEClass, SLOT__DEFINING_PROPERTY);
		createEReference(slotEClass, SLOT__OWNED_VALUES);
		createEReference(slotEClass, SLOT__OWNING_INSTANCE);

		standardLibraryEClass = createEClass(STANDARD_LIBRARY);
		createEReference(standardLibraryEClass, STANDARD_LIBRARY__OWNING_COMPLETE_ENVIRONMENT);

		stateEClass = createEClass(STATE);
		createEAttribute(stateEClass, STATE__IS_COMPOSITE);
		createEAttribute(stateEClass, STATE__IS_ORTHOGONAL);
		createEAttribute(stateEClass, STATE__IS_SIMPLE);
		createEAttribute(stateEClass, STATE__IS_SUBMACHINE_STATE);
		createEReference(stateEClass, STATE__OWNED_CONNECTION_POINTS);
		createEReference(stateEClass, STATE__OWNED_CONNECTIONS);
		createEReference(stateEClass, STATE__OWNED_DEFERRABLE_TRIGGERS);
		createEReference(stateEClass, STATE__OWNED_DO_ACTIVITY);
		createEReference(stateEClass, STATE__OWNED_ENTRY);
		createEReference(stateEClass, STATE__OWNED_EXIT);
		createEReference(stateEClass, STATE__OWNED_REGIONS);
		createEReference(stateEClass, STATE__OWNED_STATE_INVARIANT);
		createEReference(stateEClass, STATE__REDEFINED_STATE);
		createEReference(stateEClass, STATE__SUBMACHINES);

		stateExpEClass = createEClass(STATE_EXP);
		createEReference(stateExpEClass, STATE_EXP__REFERRED_STATE);
		createEOperation(stateExpEClass, STATE_EXP___VALIDATE_TYPE_IS_NOT_INVALID__DIAGNOSTICCHAIN_MAP);

		stateMachineEClass = createEClass(STATE_MACHINE);
		createEReference(stateMachineEClass, STATE_MACHINE__EXTENDED_STATE_MACHINES);
		createEReference(stateMachineEClass, STATE_MACHINE__OWNED_CONNECTION_POINTS);
		createEReference(stateMachineEClass, STATE_MACHINE__OWNED_REGIONS);
		createEReference(stateMachineEClass, STATE_MACHINE__SUBMACHINE_STATES);

		stereotypeEClass = createEClass(STEREOTYPE);
		createEReference(stereotypeEClass, STEREOTYPE__OWNED_EXTENDERS);

		stereotypeExtenderEClass = createEClass(STEREOTYPE_EXTENDER);
		createEReference(stereotypeExtenderEClass, STEREOTYPE_EXTENDER__CLASS);
		createEAttribute(stereotypeExtenderEClass, STEREOTYPE_EXTENDER__IS_REQUIRED);
		createEReference(stereotypeExtenderEClass, STEREOTYPE_EXTENDER__OWNING_STEREOTYPE);

		stringLiteralExpEClass = createEClass(STRING_LITERAL_EXP);
		createEAttribute(stringLiteralExpEClass, STRING_LITERAL_EXP__STRING_SYMBOL);

		templateBindingEClass = createEClass(TEMPLATE_BINDING);
		createEReference(templateBindingEClass, TEMPLATE_BINDING__OWNED_SUBSTITUTIONS);
		createEReference(templateBindingEClass, TEMPLATE_BINDING__OWNING_ELEMENT);
		createEReference(templateBindingEClass, TEMPLATE_BINDING__TEMPLATE_SIGNATURE);

		templateParameterEClass = createEClass(TEMPLATE_PARAMETER);
		createEReference(templateParameterEClass, TEMPLATE_PARAMETER__CONSTRAINING_CLASSES);
		createEReference(templateParameterEClass, TEMPLATE_PARAMETER__OWNING_SIGNATURE);

		templateParameterSubstitutionEClass = createEClass(TEMPLATE_PARAMETER_SUBSTITUTION);
		createEReference(templateParameterSubstitutionEClass, TEMPLATE_PARAMETER_SUBSTITUTION__ACTUAL);
		createEReference(templateParameterSubstitutionEClass, TEMPLATE_PARAMETER_SUBSTITUTION__FORMAL);
		createEReference(templateParameterSubstitutionEClass, TEMPLATE_PARAMETER_SUBSTITUTION__OWNED_WILDCARD);
		createEReference(templateParameterSubstitutionEClass, TEMPLATE_PARAMETER_SUBSTITUTION__OWNING_BINDING);

		templateSignatureEClass = createEClass(TEMPLATE_SIGNATURE);
		createEReference(templateSignatureEClass, TEMPLATE_SIGNATURE__OWNED_PARAMETERS);
		createEReference(templateSignatureEClass, TEMPLATE_SIGNATURE__OWNING_ELEMENT);

		templateableElementEClass = createEClass(TEMPLATEABLE_ELEMENT);
		createEReference(templateableElementEClass, TEMPLATEABLE_ELEMENT__OWNED_BINDINGS);
		createEReference(templateableElementEClass, TEMPLATEABLE_ELEMENT__OWNED_SIGNATURE);
		createEReference(templateableElementEClass, TEMPLATEABLE_ELEMENT__UNSPECIALIZED_ELEMENT);

		transitionEClass = createEClass(TRANSITION);
		createEAttribute(transitionEClass, TRANSITION__KIND);
		createEReference(transitionEClass, TRANSITION__OWNED_EFFECT);
		createEReference(transitionEClass, TRANSITION__OWNED_GUARD);
		createEReference(transitionEClass, TRANSITION__OWNED_TRIGGERS);
		createEReference(transitionEClass, TRANSITION__OWNING_REGION);
		createEReference(transitionEClass, TRANSITION__SOURCE);
		createEReference(transitionEClass, TRANSITION__TARGET);

		triggerEClass = createEClass(TRIGGER);
		createEReference(triggerEClass, TRIGGER__OWNING_STATE);
		createEReference(triggerEClass, TRIGGER__OWNING_TRANSITION);

		tupleLiteralExpEClass = createEClass(TUPLE_LITERAL_EXP);
		createEReference(tupleLiteralExpEClass, TUPLE_LITERAL_EXP__OWNED_PARTS);

		tupleLiteralPartEClass = createEClass(TUPLE_LITERAL_PART);
		createEReference(tupleLiteralPartEClass, TUPLE_LITERAL_PART__OWNED_INIT);
		createEOperation(tupleLiteralPartEClass, TUPLE_LITERAL_PART___VALIDATE_COMPATIBLE_INITIALISER_TYPE__DIAGNOSTICCHAIN_MAP);
		createEOperation(tupleLiteralPartEClass, TUPLE_LITERAL_PART___VALIDATE_TYPE_IS_NOT_INVALID__DIAGNOSTICCHAIN_MAP);

		tupleTypeEClass = createEClass(TUPLE_TYPE);

		typeEClass = createEClass(TYPE);
		createEOperation(typeEClass, TYPE___FLATTENED_TYPE);
		createEOperation(typeEClass, TYPE___IS_CLASS);
		createEOperation(typeEClass, TYPE___IS_TEMPLATE_PARAMETER);
		createEOperation(typeEClass, TYPE___SPECIALIZE_IN__CALLEXP_TYPE);

		typeExpEClass = createEClass(TYPE_EXP);
		createEReference(typeExpEClass, TYPE_EXP__REFERRED_TYPE);

		typedElementEClass = createEClass(TYPED_ELEMENT);
		createEAttribute(typedElementEClass, TYPED_ELEMENT__IS_MANY);
		createEAttribute(typedElementEClass, TYPED_ELEMENT__IS_REQUIRED);
		createEReference(typedElementEClass, TYPED_ELEMENT__TYPE);
		createEOperation(typedElementEClass, TYPED_ELEMENT___COMPATIBLE_BODY__VALUESPECIFICATION);

		unlimitedNaturalLiteralExpEClass = createEClass(UNLIMITED_NATURAL_LITERAL_EXP);
		createEAttribute(unlimitedNaturalLiteralExpEClass, UNLIMITED_NATURAL_LITERAL_EXP__UNLIMITED_NATURAL_SYMBOL);

		unspecifiedValueExpEClass = createEClass(UNSPECIFIED_VALUE_EXP);

		valueSpecificationEClass = createEClass(VALUE_SPECIFICATION);
		createEOperation(valueSpecificationEClass, VALUE_SPECIFICATION___BOOLEAN_VALUE);
		createEOperation(valueSpecificationEClass, VALUE_SPECIFICATION___INTEGER_VALUE);
		createEOperation(valueSpecificationEClass, VALUE_SPECIFICATION___IS_COMPUTABLE);
		createEOperation(valueSpecificationEClass, VALUE_SPECIFICATION___IS_NULL);
		createEOperation(valueSpecificationEClass, VALUE_SPECIFICATION___STRING_VALUE);
		createEOperation(valueSpecificationEClass, VALUE_SPECIFICATION___UNLIMITED_VALUE);

		variableEClass = createEClass(VARIABLE);
		createEAttribute(variableEClass, VARIABLE__IS_IMPLICIT);
		createEReference(variableEClass, VARIABLE__OWNED_INIT);
		createEReference(variableEClass, VARIABLE__REPRESENTED_PARAMETER);
		createEOperation(variableEClass, VARIABLE___VALIDATE_COMPATIBLE_INITIALISER_TYPE__DIAGNOSTICCHAIN_MAP);

		variableDeclarationEClass = createEClass(VARIABLE_DECLARATION);
		createEReference(variableDeclarationEClass, VARIABLE_DECLARATION__TYPE_VALUE);
		createEOperation(variableDeclarationEClass, VARIABLE_DECLARATION___VALIDATE_NAME_IS_NOT_NULL__DIAGNOSTICCHAIN_MAP);
		createEOperation(variableDeclarationEClass, VARIABLE_DECLARATION___VALIDATE_TYPE_IS_NOT_INVALID__DIAGNOSTICCHAIN_MAP);
		createEOperation(variableDeclarationEClass, VARIABLE_DECLARATION___VALIDATE_TYPE_IS_NOT_NULL__DIAGNOSTICCHAIN_MAP);

		variableExpEClass = createEClass(VARIABLE_EXP);
		createEAttribute(variableExpEClass, VARIABLE_EXP__IS_IMPLICIT);
		createEReference(variableExpEClass, VARIABLE_EXP__REFERRED_VARIABLE);
		createEOperation(variableExpEClass, VARIABLE_EXP___VALIDATE_TYPE_IS_NOT_INVALID__DIAGNOSTICCHAIN_MAP);

		vertexEClass = createEClass(VERTEX);
		createEReference(vertexEClass, VERTEX__INCOMING_TRANSITIONS);
		createEReference(vertexEClass, VERTEX__OUTGOING_TRANSITIONS);
		createEReference(vertexEClass, VERTEX__OWNING_REGION);

		visitableEClass = createEClass(VISITABLE);

		voidTypeEClass = createEClass(VOID_TYPE);

		wildcardTypeEClass = createEClass(WILDCARD_TYPE);
		createEReference(wildcardTypeEClass, WILDCARD_TYPE__LOWER_BOUND);
		createEReference(wildcardTypeEClass, WILDCARD_TYPE__UPPER_BOUND);

		// Create enums
		associativityKindEEnum = createEEnum(ASSOCIATIVITY_KIND);
		collectionKindEEnum = createEEnum(COLLECTION_KIND);
		pseudostateKindEEnum = createEEnum(PSEUDOSTATE_KIND);
		transitionKindEEnum = createEEnum(TRANSITION_KIND);

		// Create data types
		booleanEDataType = createEDataType(BOOLEAN);
		integerEDataType = createEDataType(INTEGER);
		libraryFeatureEDataType = createEDataType(LIBRARY_FEATURE);
		objectEDataType = createEDataType(OBJECT);
		realEDataType = createEDataType(REAL);
		stringEDataType = createEDataType(STRING);
		throwableEDataType = createEDataType(THROWABLE);
		unlimitedNaturalEDataType = createEDataType(UNLIMITED_NATURAL);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents()
	{
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		annotationEClass.getESuperTypes().add(this.getNamedElement());
		anyTypeEClass.getESuperTypes().add(this.getClass_());
		associationClassEClass.getESuperTypes().add(this.getClass_());
		associationClassCallExpEClass.getESuperTypes().add(this.getNavigationCallExp());
		bagTypeEClass.getESuperTypes().add(this.getCollectionType());
		behaviorEClass.getESuperTypes().add(this.getClass_());
		booleanLiteralExpEClass.getESuperTypes().add(this.getPrimitiveLiteralExp());
		callExpEClass.getESuperTypes().add(this.getOCLExpression());
		callOperationActionEClass.getESuperTypes().add(this.getNamedElement());
		classEClass.getESuperTypes().add(this.getType());
		classEClass.getESuperTypes().add(this.getNamespace());
		classEClass.getESuperTypes().add(this.getTemplateableElement());
		collectionItemEClass.getESuperTypes().add(this.getCollectionLiteralPart());
		collectionLiteralExpEClass.getESuperTypes().add(this.getLiteralExp());
		collectionLiteralPartEClass.getESuperTypes().add(this.getTypedElement());
		collectionRangeEClass.getESuperTypes().add(this.getCollectionLiteralPart());
		collectionTypeEClass.getESuperTypes().add(this.getDataType());
		commentEClass.getESuperTypes().add(this.getElement());
		completeClassEClass.getESuperTypes().add(this.getNamedElement());
		completeEnvironmentEClass.getESuperTypes().add(this.getElement());
		completeModelEClass.getESuperTypes().add(this.getNamedElement());
		completePackageEClass.getESuperTypes().add(this.getNamedElement());
		connectionPointReferenceEClass.getESuperTypes().add(this.getVertex());
		constraintEClass.getESuperTypes().add(this.getNamedElement());
		dataTypeEClass.getESuperTypes().add(this.getClass_());
		detailEClass.getESuperTypes().add(this.getNamedElement());
		dynamicBehaviorEClass.getESuperTypes().add(this.getBehavior());
		dynamicBehaviorEClass.getESuperTypes().add(this.getDynamicType());
		dynamicElementEClass.getESuperTypes().add(this.getElement());
		dynamicPropertyEClass.getESuperTypes().add(this.getElement());
		dynamicTypeEClass.getESuperTypes().add(this.getClass_());
		dynamicTypeEClass.getESuperTypes().add(this.getDynamicElement());
		dynamicValueSpecificationEClass.getESuperTypes().add(this.getValueSpecification());
		elementEClass.getESuperTypes().add(this.getVisitable());
		elementExtensionEClass.getESuperTypes().add(this.getClass_());
		enumLiteralExpEClass.getESuperTypes().add(this.getLiteralExp());
		enumerationEClass.getESuperTypes().add(this.getDataType());
		enumerationLiteralEClass.getESuperTypes().add(this.getInstanceSpecification());
		expressionInOCLEClass.getESuperTypes().add(this.getLanguageExpression());
		featureEClass.getESuperTypes().add(this.getTypedElement());
		featureCallExpEClass.getESuperTypes().add(this.getCallExp());
		finalStateEClass.getESuperTypes().add(this.getState());
		ifExpEClass.getESuperTypes().add(this.getOCLExpression());
		importEClass.getESuperTypes().add(this.getNamedElement());
		instanceSpecificationEClass.getESuperTypes().add(this.getNamedElement());
		integerLiteralExpEClass.getESuperTypes().add(this.getNumericLiteralExp());
		invalidLiteralExpEClass.getESuperTypes().add(this.getLiteralExp());
		invalidTypeEClass.getESuperTypes().add(this.getClass_());
		iterateExpEClass.getESuperTypes().add(this.getLoopExp());
		iterateExpEClass.getESuperTypes().add(this.getReferringElement());
		iterationEClass.getESuperTypes().add(this.getOperation());
		iteratorExpEClass.getESuperTypes().add(this.getLoopExp());
		iteratorExpEClass.getESuperTypes().add(this.getReferringElement());
		iteratorVariableEClass.getESuperTypes().add(this.getVariable());
		lambdaTypeEClass.getESuperTypes().add(this.getDataType());
		languageExpressionEClass.getESuperTypes().add(this.getValueSpecification());
		letExpEClass.getESuperTypes().add(this.getOCLExpression());
		letVariableEClass.getESuperTypes().add(this.getVariable());
		libraryEClass.getESuperTypes().add(this.getPackage());
		literalExpEClass.getESuperTypes().add(this.getOCLExpression());
		loopExpEClass.getESuperTypes().add(this.getCallExp());
		mapLiteralExpEClass.getESuperTypes().add(this.getLiteralExp());
		mapLiteralPartEClass.getESuperTypes().add(this.getElement());
		mapTypeEClass.getESuperTypes().add(this.getDataType());
		messageExpEClass.getESuperTypes().add(this.getOCLExpression());
		messageTypeEClass.getESuperTypes().add(this.getClass_());
		modelEClass.getESuperTypes().add(this.getNamespace());
		namedElementEClass.getESuperTypes().add(this.getElement());
		namedElementEClass.getESuperTypes().add(this.getNameable());
		namespaceEClass.getESuperTypes().add(this.getNamedElement());
		navigationCallExpEClass.getESuperTypes().add(this.getFeatureCallExp());
		nullLiteralExpEClass.getESuperTypes().add(this.getPrimitiveLiteralExp());
		numericLiteralExpEClass.getESuperTypes().add(this.getPrimitiveLiteralExp());
		oclExpressionEClass.getESuperTypes().add(this.getTypedElement());
		operationEClass.getESuperTypes().add(this.getFeature());
		operationEClass.getESuperTypes().add(this.getNamespace());
		operationEClass.getESuperTypes().add(this.getTemplateableElement());
		operationCallExpEClass.getESuperTypes().add(this.getFeatureCallExp());
		operationCallExpEClass.getESuperTypes().add(this.getReferringElement());
		oppositePropertyCallExpEClass.getESuperTypes().add(this.getNavigationCallExp());
		orderedSetTypeEClass.getESuperTypes().add(this.getCollectionType());
		orphanCompletePackageEClass.getESuperTypes().add(this.getCompletePackage());
		packageEClass.getESuperTypes().add(this.getNamespace());
		parameterEClass.getESuperTypes().add(this.getVariableDeclaration());
		parameterVariableEClass.getESuperTypes().add(this.getVariable());
		precedenceEClass.getESuperTypes().add(this.getNamedElement());
		primitiveCompletePackageEClass.getESuperTypes().add(this.getCompletePackage());
		primitiveLiteralExpEClass.getESuperTypes().add(this.getLiteralExp());
		primitiveTypeEClass.getESuperTypes().add(this.getDataType());
		profileEClass.getESuperTypes().add(this.getPackage());
		profileApplicationEClass.getESuperTypes().add(this.getElement());
		propertyEClass.getESuperTypes().add(this.getFeature());
		propertyCallExpEClass.getESuperTypes().add(this.getNavigationCallExp());
		propertyCallExpEClass.getESuperTypes().add(this.getReferringElement());
		pseudostateEClass.getESuperTypes().add(this.getVertex());
		realLiteralExpEClass.getESuperTypes().add(this.getNumericLiteralExp());
		regionEClass.getESuperTypes().add(this.getNamespace());
		resultVariableEClass.getESuperTypes().add(this.getVariable());
		selfTypeEClass.getESuperTypes().add(this.getClass_());
		sendSignalActionEClass.getESuperTypes().add(this.getNamedElement());
		sequenceTypeEClass.getESuperTypes().add(this.getCollectionType());
		setTypeEClass.getESuperTypes().add(this.getCollectionType());
		shadowExpEClass.getESuperTypes().add(this.getOCLExpression());
		shadowPartEClass.getESuperTypes().add(this.getTypedElement());
		signalEClass.getESuperTypes().add(this.getClass_());
		slotEClass.getESuperTypes().add(this.getElement());
		standardLibraryEClass.getESuperTypes().add(this.getElement());
		stateEClass.getESuperTypes().add(this.getNamespace());
		stateEClass.getESuperTypes().add(this.getVertex());
		stateExpEClass.getESuperTypes().add(this.getOCLExpression());
		stateMachineEClass.getESuperTypes().add(this.getBehavior());
		stereotypeEClass.getESuperTypes().add(this.getClass_());
		stereotypeExtenderEClass.getESuperTypes().add(this.getElement());
		stringLiteralExpEClass.getESuperTypes().add(this.getPrimitiveLiteralExp());
		templateBindingEClass.getESuperTypes().add(this.getElement());
		templateParameterEClass.getESuperTypes().add(this.getType());
		templateParameterSubstitutionEClass.getESuperTypes().add(this.getElement());
		templateSignatureEClass.getESuperTypes().add(this.getElement());
		templateableElementEClass.getESuperTypes().add(this.getElement());
		transitionEClass.getESuperTypes().add(this.getNamespace());
		triggerEClass.getESuperTypes().add(this.getNamedElement());
		tupleLiteralExpEClass.getESuperTypes().add(this.getLiteralExp());
		tupleLiteralPartEClass.getESuperTypes().add(this.getVariableDeclaration());
		tupleTypeEClass.getESuperTypes().add(this.getDataType());
		typeEClass.getESuperTypes().add(this.getNamedElement());
		typeExpEClass.getESuperTypes().add(this.getOCLExpression());
		typeExpEClass.getESuperTypes().add(this.getReferringElement());
		typedElementEClass.getESuperTypes().add(this.getNamedElement());
		unlimitedNaturalLiteralExpEClass.getESuperTypes().add(this.getNumericLiteralExp());
		unspecifiedValueExpEClass.getESuperTypes().add(this.getOCLExpression());
		valueSpecificationEClass.getESuperTypes().add(this.getTypedElement());
		variableEClass.getESuperTypes().add(this.getVariableDeclaration());
		variableDeclarationEClass.getESuperTypes().add(this.getTypedElement());
		variableExpEClass.getESuperTypes().add(this.getOCLExpression());
		variableExpEClass.getESuperTypes().add(this.getReferringElement());
		vertexEClass.getESuperTypes().add(this.getNamedElement());
		voidTypeEClass.getESuperTypes().add(this.getClass_());
		wildcardTypeEClass.getESuperTypes().add(this.getClass_());

		// Initialize classes, features, and operations; add parameters
		initEClass(annotationEClass, Annotation.class, "Annotation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getAnnotation_OwnedContents(), this.getElement(), null, "ownedContents", null, 0, -1, Annotation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getAnnotation_OwnedDetails(), this.getDetail(), null, "ownedDetails", null, 0, -1, Annotation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getAnnotation_References(), this.getElement(), null, "references", null, 0, -1, Annotation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(anyTypeEClass, AnyType.class, "AnyType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(associationClassEClass, AssociationClass.class, "AssociationClass", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getAssociationClass_UnownedAttributes(), this.getProperty(), this.getProperty_AssociationClass(), "unownedAttributes", null, 0, -1, AssociationClass.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$

		initEClass(associationClassCallExpEClass, AssociationClassCallExp.class, "AssociationClassCallExp", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getAssociationClassCallExp_ReferredAssociationClass(), this.getAssociationClass(), null, "referredAssociationClass", null, 0, 1, AssociationClassCallExp.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(bagTypeEClass, BagType.class, "BagType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(behaviorEClass, Behavior.class, "Behavior", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getBehavior_OwningTransition(), this.getTransition(), this.getTransition_OwnedEffect(), "owningTransition", null, 0, 1, Behavior.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(booleanLiteralExpEClass, BooleanLiteralExp.class, "BooleanLiteralExp", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getBooleanLiteralExp_BooleanSymbol(), this.getBoolean(), "booleanSymbol", null, 1, 1, BooleanLiteralExp.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		EOperation op = initEOperation(getBooleanLiteralExp__ValidateTypeIsBoolean__DiagnosticChain_Map(), ecorePackage.getEBoolean(), "validateTypeIsBoolean", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, ecorePackage.getEDiagnosticChain(), "diagnostics", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		EGenericType g1 = createEGenericType(ecorePackage.getEMap());
		EGenericType g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		addEParameter(op, g1, "context", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		initEClass(callExpEClass, CallExp.class, "CallExp", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getCallExp_IsImplicit(), this.getBoolean(), "isImplicit", "false", 1, 1, CallExp.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(getCallExp_IsSafe(), this.getBoolean(), "isSafe", "false", 1, 1, CallExp.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEReference(getCallExp_OwnedSource(), this.getOCLExpression(), null, "ownedSource", null, 0, 1, CallExp.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		op = initEOperation(getCallExp__ValidateTypeIsNotInvalid__DiagnosticChain_Map(), ecorePackage.getEBoolean(), "validateTypeIsNotInvalid", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, ecorePackage.getEDiagnosticChain(), "diagnostics", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(ecorePackage.getEMap());
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		addEParameter(op, g1, "context", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		initEClass(callOperationActionEClass, CallOperationAction.class, "CallOperationAction", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getCallOperationAction_Operation(), this.getOperation(), null, "operation", null, 1, 1, CallOperationAction.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(classEClass, org.eclipse.ocl.pivot.Class.class, "Class", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getClass_Extenders(), this.getStereotypeExtender(), this.getStereotypeExtender_Class(), "extenders", null, 0, -1, org.eclipse.ocl.pivot.Class.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getClass_InstanceClassName(), this.getString(), "instanceClassName", null, 0, 1, org.eclipse.ocl.pivot.Class.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getClass_IsAbstract(), this.getBoolean(), "isAbstract", "false", 1, 1, org.eclipse.ocl.pivot.Class.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(getClass_IsActive(), this.getBoolean(), "isActive", "false", 1, 1, org.eclipse.ocl.pivot.Class.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(getClass_IsInterface(), this.getBoolean(), "isInterface", "false", 1, 1, org.eclipse.ocl.pivot.Class.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEReference(getClass_OwnedBehaviors(), this.getBehavior(), null, "ownedBehaviors", null, 0, -1, org.eclipse.ocl.pivot.Class.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(getClass_OwnedInvariants(), this.getConstraint(), null, "ownedInvariants", null, 0, -1, org.eclipse.ocl.pivot.Class.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(getClass_OwnedOperations(), this.getOperation(), this.getOperation_OwningClass(), "ownedOperations", null, 0, -1, org.eclipse.ocl.pivot.Class.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getClass_OwnedProperties(), this.getProperty(), this.getProperty_OwningClass(), "ownedProperties", null, 0, -1, org.eclipse.ocl.pivot.Class.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getClass_OwningPackage(), this.getPackage(), this.getPackage_OwnedClasses(), "owningPackage", null, 0, 1, org.eclipse.ocl.pivot.Class.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getClass_SuperClasses(), this.getClass_(), null, "superClasses", null, 0, -1, org.eclipse.ocl.pivot.Class.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$

		op = initEOperation(getClass__ValidateNameIsNotNull__DiagnosticChain_Map(), ecorePackage.getEBoolean(), "validateNameIsNotNull", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, ecorePackage.getEDiagnosticChain(), "diagnostics", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(ecorePackage.getEMap());
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		addEParameter(op, g1, "context", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		op = initEOperation(getClass__ValidateUniqueInvariantName__DiagnosticChain_Map(), ecorePackage.getEBoolean(), "validateUniqueInvariantName", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, ecorePackage.getEDiagnosticChain(), "diagnostics", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(ecorePackage.getEMap());
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		addEParameter(op, g1, "context", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		initEClass(collectionItemEClass, CollectionItem.class, "CollectionItem", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getCollectionItem_OwnedItem(), this.getOCLExpression(), null, "ownedItem", null, 1, 1, CollectionItem.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		op = initEOperation(getCollectionItem__ValidateTypeIsItemType__DiagnosticChain_Map(), ecorePackage.getEBoolean(), "validateTypeIsItemType", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, ecorePackage.getEDiagnosticChain(), "diagnostics", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(ecorePackage.getEMap());
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		addEParameter(op, g1, "context", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		initEClass(collectionLiteralExpEClass, CollectionLiteralExp.class, "CollectionLiteralExp", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getCollectionLiteralExp_Kind(), this.getCollectionKind(), "kind", null, 1, 1, CollectionLiteralExp.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getCollectionLiteralExp_OwnedParts(), this.getCollectionLiteralPart(), null, "ownedParts", null, 0, -1, CollectionLiteralExp.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		op = initEOperation(getCollectionLiteralExp__ValidateBagKindIsBag__DiagnosticChain_Map(), ecorePackage.getEBoolean(), "validateBagKindIsBag", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, ecorePackage.getEDiagnosticChain(), "diagnostics", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(ecorePackage.getEMap());
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		addEParameter(op, g1, "context", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		op = initEOperation(getCollectionLiteralExp__ValidateCollectionKindIsConcrete__DiagnosticChain_Map(), ecorePackage.getEBoolean(), "validateCollectionKindIsConcrete", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, ecorePackage.getEDiagnosticChain(), "diagnostics", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(ecorePackage.getEMap());
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		addEParameter(op, g1, "context", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		op = initEOperation(getCollectionLiteralExp__ValidateOrderedSetKindIsOrderedSet__DiagnosticChain_Map(), ecorePackage.getEBoolean(), "validateOrderedSetKindIsOrderedSet", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, ecorePackage.getEDiagnosticChain(), "diagnostics", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(ecorePackage.getEMap());
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		addEParameter(op, g1, "context", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		op = initEOperation(getCollectionLiteralExp__ValidateSequenceKindIsSequence__DiagnosticChain_Map(), ecorePackage.getEBoolean(), "validateSequenceKindIsSequence", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, ecorePackage.getEDiagnosticChain(), "diagnostics", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(ecorePackage.getEMap());
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		addEParameter(op, g1, "context", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		op = initEOperation(getCollectionLiteralExp__ValidateSetKindIsSet__DiagnosticChain_Map(), ecorePackage.getEBoolean(), "validateSetKindIsSet", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, ecorePackage.getEDiagnosticChain(), "diagnostics", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(ecorePackage.getEMap());
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		addEParameter(op, g1, "context", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		initEClass(collectionLiteralPartEClass, CollectionLiteralPart.class, "CollectionLiteralPart", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		op = initEOperation(getCollectionLiteralPart__ValidateTypeIsNotInvalid__DiagnosticChain_Map(), ecorePackage.getEBoolean(), "validateTypeIsNotInvalid", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, ecorePackage.getEDiagnosticChain(), "diagnostics", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(ecorePackage.getEMap());
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		addEParameter(op, g1, "context", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		initEClass(collectionRangeEClass, CollectionRange.class, "CollectionRange", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getCollectionRange_OwnedFirst(), this.getOCLExpression(), null, "ownedFirst", null, 1, 1, CollectionRange.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getCollectionRange_OwnedLast(), this.getOCLExpression(), null, "ownedLast", null, 1, 1, CollectionRange.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(collectionTypeEClass, CollectionType.class, "CollectionType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getCollectionType_ElementType(), this.getType(), null, "elementType", null, 1, 1, CollectionType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getCollectionType_IsNullFree(), this.getBoolean(), "isNullFree", "false", 1, 1, CollectionType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(getCollectionType_Lower(), this.getInteger(), "lower", "0", 1, 1, CollectionType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(getCollectionType_Upper(), this.getUnlimitedNatural(), "upper", "*", 1, 1, CollectionType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$

		initEClass(commentEClass, Comment.class, "Comment", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getComment_AnnotatedElements(), this.getElement(), this.getElement_AnnotatingComments(), "annotatedElements", null, 0, -1, Comment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getComment_Body(), this.getString(), "body", null, 0, 1, Comment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getComment_OwningElement(), this.getElement(), this.getElement_OwnedComments(), "owningElement", null, 0, 1, Comment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(completeClassEClass, CompleteClass.class, "CompleteClass", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getCompleteClass_OwningCompletePackage(), this.getCompletePackage(), this.getCompletePackage_OwnedCompleteClasses(), "owningCompletePackage", null, 0, 1, CompleteClass.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getCompleteClass_PartialClasses(), this.getClass_(), null, "partialClasses", null, 0, -1, CompleteClass.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$

		initEClass(completeEnvironmentEClass, CompleteEnvironment.class, "CompleteEnvironment", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getCompleteEnvironment_OwnedCompleteModel(), this.getCompleteModel(), this.getCompleteModel_OwningCompleteEnvironment(), "ownedCompleteModel", null, 1, 1, CompleteEnvironment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getCompleteEnvironment_OwnedStandardLibrary(), this.getStandardLibrary(), this.getStandardLibrary_OwningCompleteEnvironment(), "ownedStandardLibrary", null, 1, 1, CompleteEnvironment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(completeModelEClass, CompleteModel.class, "CompleteModel", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getCompleteModel_OrphanCompletePackage(), this.getOrphanCompletePackage(), null, "orphanCompletePackage", null, 0, 1, CompleteModel.class, IS_TRANSIENT, !IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getCompleteModel_OwnedCompletePackages(), this.getCompletePackage(), this.getCompletePackage_OwningCompleteModel(), "ownedCompletePackages", null, 0, -1, CompleteModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(getCompleteModel_OwningCompleteEnvironment(), this.getCompleteEnvironment(), this.getCompleteEnvironment_OwnedCompleteModel(), "owningCompleteEnvironment", null, 0, 1, CompleteModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getCompleteModel_PartialModels(), this.getModel(), null, "partialModels", null, 0, -1, CompleteModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(getCompleteModel_PrimitiveCompletePackage(), this.getPrimitiveCompletePackage(), null, "primitiveCompletePackage", null, 0, 1, CompleteModel.class, IS_TRANSIENT, !IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		op = initEOperation(getCompleteModel__GetOwnedCompletePackage__String(), this.getCompletePackage(), "getOwnedCompletePackage", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, this.getString(), "name", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		initEClass(completePackageEClass, CompletePackage.class, "CompletePackage", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getCompletePackage_OwnedCompleteClasses(), this.getCompleteClass(), this.getCompleteClass_OwningCompletePackage(), "ownedCompleteClasses", null, 0, -1, CompletePackage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(getCompletePackage_OwnedCompletePackages(), this.getCompletePackage(), this.getCompletePackage_OwningCompletePackage(), "ownedCompletePackages", null, 0, -1, CompletePackage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(getCompletePackage_OwningCompleteModel(), this.getCompleteModel(), this.getCompleteModel_OwnedCompletePackages(), "owningCompleteModel", null, 0, 1, CompletePackage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getCompletePackage_OwningCompletePackage(), this.getCompletePackage(), this.getCompletePackage_OwnedCompletePackages(), "owningCompletePackage", null, 0, 1, CompletePackage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getCompletePackage_PartialPackages(), this.getPackage(), null, "partialPackages", null, 0, -1, CompletePackage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$

		op = initEOperation(getCompletePackage__GetOwnedCompleteClass__String(), this.getCompleteClass(), "getOwnedCompleteClass", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, this.getString(), "name", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		initEClass(connectionPointReferenceEClass, ConnectionPointReference.class, "ConnectionPointReference", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getConnectionPointReference_Entries(), this.getPseudostate(), null, "entries", null, 0, -1, ConnectionPointReference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(getConnectionPointReference_Exits(), this.getPseudostate(), null, "exits", null, 0, -1, ConnectionPointReference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(getConnectionPointReference_OwningState(), this.getState(), this.getState_OwnedConnections(), "owningState", null, 0, 1, ConnectionPointReference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(constraintEClass, Constraint.class, "Constraint", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getConstraint_ConstrainedElements(), this.getElement(), null, "constrainedElements", null, 0, -1, Constraint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getConstraint_Context(), this.getNamespace(), null, "context", null, 0, 1, Constraint.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getConstraint_IsCallable(), this.getBoolean(), "isCallable", "false", 1, 1, Constraint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEReference(getConstraint_OwnedSpecification(), this.getLanguageExpression(), this.getLanguageExpression_OwningConstraint(), "ownedSpecification", null, 1, 1, Constraint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getConstraint_OwningPostContext(), this.getOperation(), this.getOperation_OwnedPostconditions(), "owningPostContext", null, 0, 1, Constraint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getConstraint_OwningPreContext(), this.getOperation(), this.getOperation_OwnedPreconditions(), "owningPreContext", null, 0, 1, Constraint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getConstraint_OwningState(), this.getState(), this.getState_OwnedStateInvariant(), "owningState", null, 0, 1, Constraint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getConstraint_OwningTransition(), this.getTransition(), this.getTransition_OwnedGuard(), "owningTransition", null, 0, 1, Constraint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getConstraint_RedefinedConstraints(), this.getConstraint(), null, "redefinedConstraints", null, 0, -1, Constraint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$

		op = initEOperation(getConstraint__ValidateUniqueName__DiagnosticChain_Map(), ecorePackage.getEBoolean(), "validateUniqueName", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, ecorePackage.getEDiagnosticChain(), "diagnostics", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(ecorePackage.getEMap());
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		addEParameter(op, g1, "context", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		initEClass(dataTypeEClass, DataType.class, "DataType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getDataType_BehavioralClass(), this.getClass_(), null, "behavioralClass", null, 0, 1, DataType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getDataType_IsSerializable(), this.getBoolean(), "isSerializable", "true", 1, 1, DataType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(getDataType_Value(), this.getString(), "value", "", 1, 1, DataType.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$

		initEClass(detailEClass, Detail.class, "Detail", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getDetail_Values(), this.getString(), "values", null, 1, -1, Detail.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$

		initEClass(dynamicBehaviorEClass, DynamicBehavior.class, "DynamicBehavior", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(dynamicElementEClass, DynamicElement.class, "DynamicElement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getDynamicElement_MetaType(), this.getType(), null, "metaType", null, 1, 1, DynamicElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(dynamicPropertyEClass, DynamicProperty.class, "DynamicProperty", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getDynamicProperty_Default(), this.getString(), "default", null, 0, 1, DynamicProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getDynamicProperty_ReferredProperty(), this.getProperty(), null, "referredProperty", null, 1, 1, DynamicProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(dynamicTypeEClass, DynamicType.class, "DynamicType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getDynamicType_OwnedDynamicProperties(), this.getDynamicProperty(), null, "ownedDynamicProperties", null, 0, -1, DynamicType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$

		initEClass(dynamicValueSpecificationEClass, DynamicValueSpecification.class, "DynamicValueSpecification", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(elementEClass, Element.class, "Element", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getElement_AnnotatingComments(), this.getComment(), this.getComment_AnnotatedElements(), "annotatingComments", null, 0, -1, Element.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(getElement_OwnedAnnotations(), this.getElement(), null, "ownedAnnotations", null, 0, -1, Element.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getElement_OwnedComments(), this.getComment(), this.getComment_OwningElement(), "ownedComments", null, 0, -1, Element.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(getElement_OwnedExtensions(), this.getElementExtension(), this.getElementExtension_Base(), "ownedExtensions", null, 0, -1, Element.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$

		initEOperation(getElement__AllOwnedElements(), this.getElement(), "allOwnedElements", 0, -1, IS_UNIQUE, !IS_ORDERED); //$NON-NLS-1$

		op = initEOperation(getElement__GetValue__Type_String(), this.getElement(), "getValue", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, this.getType(), "stereotype", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, this.getString(), "propertyName", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		initEClass(elementExtensionEClass, ElementExtension.class, "ElementExtension", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getElementExtension_Base(), this.getElement(), this.getElement_OwnedExtensions(), "base", null, 1, 1, ElementExtension.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getElementExtension_IsApplied(), this.getBoolean(), "isApplied", "false", 1, 1, ElementExtension.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(getElementExtension_IsRequired(), this.getBoolean(), "isRequired", "false", 1, 1, ElementExtension.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEReference(getElementExtension_Stereotype(), this.getStereotype(), null, "stereotype", null, 1, 1, ElementExtension.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(enumLiteralExpEClass, EnumLiteralExp.class, "EnumLiteralExp", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getEnumLiteralExp_ReferredLiteral(), this.getEnumerationLiteral(), null, "referredLiteral", null, 0, 1, EnumLiteralExp.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		op = initEOperation(getEnumLiteralExp__ValidateTypeIsEnumerationType__DiagnosticChain_Map(), ecorePackage.getEBoolean(), "validateTypeIsEnumerationType", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, ecorePackage.getEDiagnosticChain(), "diagnostics", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(ecorePackage.getEMap());
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		addEParameter(op, g1, "context", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		initEClass(enumerationEClass, Enumeration.class, "Enumeration", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getEnumeration_OwnedLiterals(), this.getEnumerationLiteral(), this.getEnumerationLiteral_OwningEnumeration(), "ownedLiterals", null, 0, -1, Enumeration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(enumerationLiteralEClass, EnumerationLiteral.class, "EnumerationLiteral", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getEnumerationLiteral_OwningEnumeration(), this.getEnumeration(), this.getEnumeration_OwnedLiterals(), "owningEnumeration", null, 1, 1, EnumerationLiteral.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getEnumerationLiteral_Value(), this.getInteger(), "value", "0", 0, 1, EnumerationLiteral.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$

		initEClass(expressionInOCLEClass, ExpressionInOCL.class, "ExpressionInOCL", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getExpressionInOCL_OwnedBody(), this.getOCLExpression(), null, "ownedBody", null, 0, 1, ExpressionInOCL.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getExpressionInOCL_OwnedContext(), this.getVariable(), null, "ownedContext", null, 0, 1, ExpressionInOCL.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getExpressionInOCL_OwnedParameters(), this.getVariable(), null, "ownedParameters", null, 0, -1, ExpressionInOCL.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getExpressionInOCL_OwnedResult(), this.getVariable(), null, "ownedResult", null, 0, 1, ExpressionInOCL.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(featureEClass, Feature.class, "Feature", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getFeature_Implementation(), this.getLibraryFeature(), "implementation", null, 0, 1, Feature.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getFeature_ImplementationClass(), this.getString(), "implementationClass", null, 0, 1, Feature.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getFeature_IsStatic(), this.getBoolean(), "isStatic", "false", 1, 1, Feature.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$

		op = initEOperation(getFeature__ValidateNameIsNotNull__DiagnosticChain_Map(), ecorePackage.getEBoolean(), "validateNameIsNotNull", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, ecorePackage.getEDiagnosticChain(), "diagnostics", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(ecorePackage.getEMap());
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		addEParameter(op, g1, "context", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		op = initEOperation(getFeature__ValidateTypeIsNotInvalid__DiagnosticChain_Map(), ecorePackage.getEBoolean(), "validateTypeIsNotInvalid", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, ecorePackage.getEDiagnosticChain(), "diagnostics", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(ecorePackage.getEMap());
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		addEParameter(op, g1, "context", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		op = initEOperation(getFeature__ValidateTypeIsNotNull__DiagnosticChain_Map(), ecorePackage.getEBoolean(), "validateTypeIsNotNull", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, ecorePackage.getEDiagnosticChain(), "diagnostics", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(ecorePackage.getEMap());
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		addEParameter(op, g1, "context", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		initEClass(featureCallExpEClass, FeatureCallExp.class, "FeatureCallExp", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getFeatureCallExp_IsPre(), this.getBoolean(), "isPre", "false", 1, 1, FeatureCallExp.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$

		initEClass(finalStateEClass, FinalState.class, "FinalState", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(ifExpEClass, IfExp.class, "IfExp", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getIfExp_IsElseIf(), this.getBoolean(), "isElseIf", "false", 1, 1, IfExp.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEReference(getIfExp_OwnedCondition(), this.getOCLExpression(), null, "ownedCondition", null, 1, 1, IfExp.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getIfExp_OwnedElse(), this.getOCLExpression(), null, "ownedElse", null, 1, 1, IfExp.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getIfExp_OwnedThen(), this.getOCLExpression(), null, "ownedThen", null, 1, 1, IfExp.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		op = initEOperation(getIfExp__ValidateConditionTypeIsBoolean__DiagnosticChain_Map(), ecorePackage.getEBoolean(), "validateConditionTypeIsBoolean", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, ecorePackage.getEDiagnosticChain(), "diagnostics", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(ecorePackage.getEMap());
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		addEParameter(op, g1, "context", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		op = initEOperation(getIfExp__ValidateTypeIsNotInvalid__DiagnosticChain_Map(), ecorePackage.getEBoolean(), "validateTypeIsNotInvalid", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, ecorePackage.getEDiagnosticChain(), "diagnostics", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(ecorePackage.getEMap());
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		addEParameter(op, g1, "context", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		initEClass(importEClass, Import.class, "Import", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getImport_ImportedNamespace(), this.getNamespace(), null, "importedNamespace", null, 1, 1, Import.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getImport_XmiidVersion(), this.getInteger(), "xmiidVersion", "0", 1, 1, Import.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$

		initEClass(instanceSpecificationEClass, InstanceSpecification.class, "InstanceSpecification", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getInstanceSpecification_Classes(), this.getClass_(), null, "classes", null, 0, -1, InstanceSpecification.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(getInstanceSpecification_OwnedSlots(), this.getSlot(), this.getSlot_OwningInstance(), "ownedSlots", null, 0, -1, InstanceSpecification.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(getInstanceSpecification_OwnedSpecification(), this.getLanguageExpression(), null, "ownedSpecification", null, 0, 1, InstanceSpecification.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getInstanceSpecification_OwningPackage(), this.getPackage(), this.getPackage_OwnedInstances(), "owningPackage", null, 0, 1, InstanceSpecification.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(integerLiteralExpEClass, IntegerLiteralExp.class, "IntegerLiteralExp", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getIntegerLiteralExp_IntegerSymbol(), this.getInteger(), "integerSymbol", null, 1, 1, IntegerLiteralExp.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		op = initEOperation(getIntegerLiteralExp__ValidateTypeIsInteger__DiagnosticChain_Map(), ecorePackage.getEBoolean(), "validateTypeIsInteger", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, ecorePackage.getEDiagnosticChain(), "diagnostics", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(ecorePackage.getEMap());
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		addEParameter(op, g1, "context", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		initEClass(invalidLiteralExpEClass, InvalidLiteralExp.class, "InvalidLiteralExp", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(invalidTypeEClass, InvalidType.class, "InvalidType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(iterateExpEClass, IterateExp.class, "IterateExp", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getIterateExp_OwnedResult(), this.getVariable(), null, "ownedResult", null, 0, 1, IterateExp.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		op = initEOperation(getIterateExp__ValidateBodyTypeConformsToResultType__DiagnosticChain_Map(), ecorePackage.getEBoolean(), "validateBodyTypeConformsToResultType", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, ecorePackage.getEDiagnosticChain(), "diagnostics", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(ecorePackage.getEMap());
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		addEParameter(op, g1, "context", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		op = initEOperation(getIterateExp__ValidateOneInitializer__DiagnosticChain_Map(), ecorePackage.getEBoolean(), "validateOneInitializer", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, ecorePackage.getEDiagnosticChain(), "diagnostics", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(ecorePackage.getEMap());
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		addEParameter(op, g1, "context", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		op = initEOperation(getIterateExp__ValidateSafeIteratorIsRequired__DiagnosticChain_Map(), ecorePackage.getEBoolean(), "validateSafeIteratorIsRequired", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, ecorePackage.getEDiagnosticChain(), "diagnostics", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(ecorePackage.getEMap());
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		addEParameter(op, g1, "context", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		op = initEOperation(getIterateExp__ValidateSafeSourceCanBeNull__DiagnosticChain_Map(), ecorePackage.getEBoolean(), "validateSafeSourceCanBeNull", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, ecorePackage.getEDiagnosticChain(), "diagnostics", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(ecorePackage.getEMap());
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		addEParameter(op, g1, "context", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		op = initEOperation(getIterateExp__ValidateTypeIsResultType__DiagnosticChain_Map(), ecorePackage.getEBoolean(), "validateTypeIsResultType", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, ecorePackage.getEDiagnosticChain(), "diagnostics", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(ecorePackage.getEMap());
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		addEParameter(op, g1, "context", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		op = initEOperation(getIterateExp__ValidateUnsafeSourceCanNotBeNull__DiagnosticChain_Map(), ecorePackage.getEBoolean(), "validateUnsafeSourceCanNotBeNull", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, ecorePackage.getEDiagnosticChain(), "diagnostics", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(ecorePackage.getEMap());
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		addEParameter(op, g1, "context", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		initEClass(iterationEClass, Iteration.class, "Iteration", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getIteration_OwnedAccumulators(), this.getParameter(), null, "ownedAccumulators", null, 0, -1, Iteration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getIteration_OwnedIterators(), this.getParameter(), null, "ownedIterators", null, 0, -1, Iteration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(iteratorExpEClass, IteratorExp.class, "IteratorExp", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		op = initEOperation(getIteratorExp__ValidateAnyBodyTypeIsBoolean__DiagnosticChain_Map(), ecorePackage.getEBoolean(), "validateAnyBodyTypeIsBoolean", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, ecorePackage.getEDiagnosticChain(), "diagnostics", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(ecorePackage.getEMap());
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		addEParameter(op, g1, "context", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		op = initEOperation(getIteratorExp__ValidateAnyHasOneIterator__DiagnosticChain_Map(), ecorePackage.getEBoolean(), "validateAnyHasOneIterator", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, ecorePackage.getEDiagnosticChain(), "diagnostics", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(ecorePackage.getEMap());
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		addEParameter(op, g1, "context", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		op = initEOperation(getIteratorExp__ValidateAnyTypeIsSourceElementType__DiagnosticChain_Map(), ecorePackage.getEBoolean(), "validateAnyTypeIsSourceElementType", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, ecorePackage.getEDiagnosticChain(), "diagnostics", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(ecorePackage.getEMap());
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		addEParameter(op, g1, "context", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		op = initEOperation(getIteratorExp__ValidateClosureBodyElementTypeIsIteratorType__DiagnosticChain_Map(), ecorePackage.getEBoolean(), "validateClosureBodyElementTypeIsIteratorType", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, ecorePackage.getEDiagnosticChain(), "diagnostics", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(ecorePackage.getEMap());
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		addEParameter(op, g1, "context", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		op = initEOperation(getIteratorExp__ValidateClosureBodyTypeIsConformanttoIteratorType__DiagnosticChain_Map(), ecorePackage.getEBoolean(), "validateClosureBodyTypeIsConformanttoIteratorType", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, ecorePackage.getEDiagnosticChain(), "diagnostics", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(ecorePackage.getEMap());
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		addEParameter(op, g1, "context", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		op = initEOperation(getIteratorExp__ValidateClosureElementTypeIsSourceElementType__DiagnosticChain_Map(), ecorePackage.getEBoolean(), "validateClosureElementTypeIsSourceElementType", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, ecorePackage.getEDiagnosticChain(), "diagnostics", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(ecorePackage.getEMap());
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		addEParameter(op, g1, "context", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		op = initEOperation(getIteratorExp__ValidateClosureHasOneIterator__DiagnosticChain_Map(), ecorePackage.getEBoolean(), "validateClosureHasOneIterator", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, ecorePackage.getEDiagnosticChain(), "diagnostics", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(ecorePackage.getEMap());
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		addEParameter(op, g1, "context", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		op = initEOperation(getIteratorExp__ValidateClosureSourceElementTypeIsBodyElementType__DiagnosticChain_Map(), ecorePackage.getEBoolean(), "validateClosureSourceElementTypeIsBodyElementType", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, ecorePackage.getEDiagnosticChain(), "diagnostics", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(ecorePackage.getEMap());
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		addEParameter(op, g1, "context", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		op = initEOperation(getIteratorExp__ValidateClosureTypeIsUniqueCollection__DiagnosticChain_Map(), ecorePackage.getEBoolean(), "validateClosureTypeIsUniqueCollection", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, ecorePackage.getEDiagnosticChain(), "diagnostics", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(ecorePackage.getEMap());
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		addEParameter(op, g1, "context", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		op = initEOperation(getIteratorExp__ValidateCollectElementTypeIsFlattenedBodyType__DiagnosticChain_Map(), ecorePackage.getEBoolean(), "validateCollectElementTypeIsFlattenedBodyType", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, ecorePackage.getEDiagnosticChain(), "diagnostics", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(ecorePackage.getEMap());
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		addEParameter(op, g1, "context", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		op = initEOperation(getIteratorExp__ValidateCollectTypeIsUnordered__DiagnosticChain_Map(), ecorePackage.getEBoolean(), "validateCollectTypeIsUnordered", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, ecorePackage.getEDiagnosticChain(), "diagnostics", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(ecorePackage.getEMap());
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		addEParameter(op, g1, "context", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		op = initEOperation(getIteratorExp__ValidateIteratorTypeIsSourceElementType__DiagnosticChain_Map(), ecorePackage.getEBoolean(), "validateIteratorTypeIsSourceElementType", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, ecorePackage.getEDiagnosticChain(), "diagnostics", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(ecorePackage.getEMap());
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		addEParameter(op, g1, "context", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		op = initEOperation(getIteratorExp__ValidateSafeIteratorIsRequired__DiagnosticChain_Map(), ecorePackage.getEBoolean(), "validateSafeIteratorIsRequired", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, ecorePackage.getEDiagnosticChain(), "diagnostics", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(ecorePackage.getEMap());
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		addEParameter(op, g1, "context", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		op = initEOperation(getIteratorExp__ValidateSafeSourceCanBeNull__DiagnosticChain_Map(), ecorePackage.getEBoolean(), "validateSafeSourceCanBeNull", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, ecorePackage.getEDiagnosticChain(), "diagnostics", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(ecorePackage.getEMap());
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		addEParameter(op, g1, "context", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		op = initEOperation(getIteratorExp__ValidateSortedByElementTypeIsSourceElementType__DiagnosticChain_Map(), ecorePackage.getEBoolean(), "validateSortedByElementTypeIsSourceElementType", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, ecorePackage.getEDiagnosticChain(), "diagnostics", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(ecorePackage.getEMap());
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		addEParameter(op, g1, "context", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		op = initEOperation(getIteratorExp__ValidateSortedByIsOrderedIfSourceIsOrdered__DiagnosticChain_Map(), ecorePackage.getEBoolean(), "validateSortedByIsOrderedIfSourceIsOrdered", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, ecorePackage.getEDiagnosticChain(), "diagnostics", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(ecorePackage.getEMap());
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		addEParameter(op, g1, "context", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		op = initEOperation(getIteratorExp__ValidateSortedByIteratorTypeIsComparable__DiagnosticChain_Map(), ecorePackage.getEBoolean(), "validateSortedByIteratorTypeIsComparable", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, ecorePackage.getEDiagnosticChain(), "diagnostics", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(ecorePackage.getEMap());
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		addEParameter(op, g1, "context", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		op = initEOperation(getIteratorExp__ValidateUnsafeSourceCanNotBeNull__DiagnosticChain_Map(), ecorePackage.getEBoolean(), "validateUnsafeSourceCanNotBeNull", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, ecorePackage.getEDiagnosticChain(), "diagnostics", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(ecorePackage.getEMap());
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		addEParameter(op, g1, "context", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		initEClass(iteratorVariableEClass, IteratorVariable.class, "IteratorVariable", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		op = initEOperation(getIteratorVariable__ValidateHasNoInitializer__DiagnosticChain_Map(), ecorePackage.getEBoolean(), "validateHasNoInitializer", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, ecorePackage.getEDiagnosticChain(), "diagnostics", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(ecorePackage.getEMap());
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		addEParameter(op, g1, "context", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		initEClass(lambdaTypeEClass, LambdaType.class, "LambdaType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getLambdaType_ContextType(), this.getType(), null, "contextType", null, 1, 1, LambdaType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getLambdaType_ParameterType(), this.getType(), null, "parameterType", null, 0, -1, LambdaType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getLambdaType_ResultType(), this.getType(), null, "resultType", null, 1, 1, LambdaType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(languageExpressionEClass, LanguageExpression.class, "LanguageExpression", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getLanguageExpression_Body(), this.getString(), "body", null, 0, 1, LanguageExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getLanguageExpression_Language(), this.getString(), "language", null, 1, 1, LanguageExpression.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getLanguageExpression_OwningConstraint(), this.getConstraint(), this.getConstraint_OwnedSpecification(), "owningConstraint", null, 0, 1, LanguageExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(letExpEClass, LetExp.class, "LetExp", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getLetExp_OwnedIn(), this.getOCLExpression(), null, "ownedIn", null, 1, 1, LetExp.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getLetExp_OwnedVariable(), this.getVariable(), null, "ownedVariable", null, 1, 1, LetExp.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		op = initEOperation(getLetExp__ValidateCompatibleNullityForIn__DiagnosticChain_Map(), ecorePackage.getEBoolean(), "validateCompatibleNullityForIn", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, ecorePackage.getEDiagnosticChain(), "diagnostics", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(ecorePackage.getEMap());
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		addEParameter(op, g1, "context", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		op = initEOperation(getLetExp__ValidateTypeIsInType__DiagnosticChain_Map(), ecorePackage.getEBoolean(), "validateTypeIsInType", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, ecorePackage.getEDiagnosticChain(), "diagnostics", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(ecorePackage.getEMap());
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		addEParameter(op, g1, "context", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		op = initEOperation(getLetExp__ValidateTypeIsNotInvalid__DiagnosticChain_Map(), ecorePackage.getEBoolean(), "validateTypeIsNotInvalid", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, ecorePackage.getEDiagnosticChain(), "diagnostics", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(ecorePackage.getEMap());
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		addEParameter(op, g1, "context", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		initEClass(letVariableEClass, LetVariable.class, "LetVariable", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		op = initEOperation(getLetVariable__ValidateCompatibleNullityForInitializer__DiagnosticChain_Map(), ecorePackage.getEBoolean(), "validateCompatibleNullityForInitializer", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, ecorePackage.getEDiagnosticChain(), "diagnostics", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(ecorePackage.getEMap());
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		addEParameter(op, g1, "context", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		op = initEOperation(getLetVariable__ValidateCompatibleTypeForInitializer__DiagnosticChain_Map(), ecorePackage.getEBoolean(), "validateCompatibleTypeForInitializer", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, ecorePackage.getEDiagnosticChain(), "diagnostics", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(ecorePackage.getEMap());
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		addEParameter(op, g1, "context", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		op = initEOperation(getLetVariable__ValidateHasInitializer__DiagnosticChain_Map(), ecorePackage.getEBoolean(), "validateHasInitializer", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, ecorePackage.getEDiagnosticChain(), "diagnostics", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(ecorePackage.getEMap());
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		addEParameter(op, g1, "context", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		initEClass(libraryEClass, Library.class, "Library", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getLibrary_OwnedPrecedences(), this.getPrecedence(), null, "ownedPrecedences", null, 0, -1, Library.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(literalExpEClass, LiteralExp.class, "LiteralExp", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(loopExpEClass, LoopExp.class, "LoopExp", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getLoopExp_OwnedBody(), this.getOCLExpression(), null, "ownedBody", null, 1, 1, LoopExp.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getLoopExp_OwnedIterators(), this.getVariable(), null, "ownedIterators", null, 0, -1, LoopExp.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getLoopExp_ReferredIteration(), this.getIteration(), null, "referredIteration", null, 0, 1, LoopExp.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		op = initEOperation(getLoopExp__ValidateNoInitializers__DiagnosticChain_Map(), ecorePackage.getEBoolean(), "validateNoInitializers", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, ecorePackage.getEDiagnosticChain(), "diagnostics", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(ecorePackage.getEMap());
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		addEParameter(op, g1, "context", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		op = initEOperation(getLoopExp__ValidateSourceIsCollection__DiagnosticChain_Map(), ecorePackage.getEBoolean(), "validateSourceIsCollection", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, ecorePackage.getEDiagnosticChain(), "diagnostics", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(ecorePackage.getEMap());
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		addEParameter(op, g1, "context", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		initEClass(mapLiteralExpEClass, MapLiteralExp.class, "MapLiteralExp", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getMapLiteralExp_OwnedParts(), this.getMapLiteralPart(), null, "ownedParts", null, 0, -1, MapLiteralExp.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(mapLiteralPartEClass, MapLiteralPart.class, "MapLiteralPart", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getMapLiteralPart_OwnedKey(), this.getOCLExpression(), null, "ownedKey", null, 1, 1, MapLiteralPart.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getMapLiteralPart_OwnedValue(), this.getOCLExpression(), null, "ownedValue", null, 1, 1, MapLiteralPart.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(mapTypeEClass, MapType.class, "MapType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getMapType_KeyType(), this.getType(), null, "keyType", null, 1, 1, MapType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getMapType_ValueType(), this.getType(), null, "valueType", null, 1, 1, MapType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(messageExpEClass, MessageExp.class, "MessageExp", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getMessageExp_OwnedArguments(), this.getOCLExpression(), null, "ownedArguments", null, 0, -1, MessageExp.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getMessageExp_OwnedCalledOperation(), this.getCallOperationAction(), null, "ownedCalledOperation", null, 0, 1, MessageExp.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getMessageExp_OwnedSentSignal(), this.getSendSignalAction(), null, "ownedSentSignal", null, 0, 1, MessageExp.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getMessageExp_OwnedTarget(), this.getOCLExpression(), null, "ownedTarget", null, 1, 1, MessageExp.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		op = initEOperation(getMessageExp__ValidateOneCallOrOneSend__DiagnosticChain_Map(), ecorePackage.getEBoolean(), "validateOneCallOrOneSend", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, ecorePackage.getEDiagnosticChain(), "diagnostics", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(ecorePackage.getEMap());
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		addEParameter(op, g1, "context", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		op = initEOperation(getMessageExp__ValidateTargetIsNotACollection__DiagnosticChain_Map(), ecorePackage.getEBoolean(), "validateTargetIsNotACollection", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, ecorePackage.getEDiagnosticChain(), "diagnostics", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(ecorePackage.getEMap());
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		addEParameter(op, g1, "context", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		initEClass(messageTypeEClass, MessageType.class, "MessageType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getMessageType_ReferredOperation(), this.getOperation(), null, "referredOperation", null, 0, 1, MessageType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getMessageType_ReferredSignal(), this.getSignal(), null, "referredSignal", null, 0, 1, MessageType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(modelEClass, Model.class, "Model", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getModel_ExternalURI(), this.getString(), "externalURI", null, 0, 1, Model.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getModel_OwnedImports(), this.getImport(), null, "ownedImports", null, 0, -1, Model.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getModel_OwnedPackages(), this.getPackage(), null, "ownedPackages", null, 0, -1, Model.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getModel_XmiidVersion(), this.getInteger(), "xmiidVersion", "0", 1, 1, Model.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$

		initEClass(morePivotableEClass, MorePivotable.class, "MorePivotable", IS_ABSTRACT, IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(nameableEClass, Nameable.class, "Nameable", IS_ABSTRACT, IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(namedElementEClass, NamedElement.class, "NamedElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getNamedElement_Name(), this.getString(), "name", null, 0, 1, NamedElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(namespaceEClass, Namespace.class, "Namespace", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getNamespace_OwnedConstraints(), this.getConstraint(), null, "ownedConstraints", null, 0, -1, Namespace.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$

		initEClass(navigationCallExpEClass, NavigationCallExp.class, "NavigationCallExp", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getNavigationCallExp_NavigationSource(), this.getProperty(), null, "navigationSource", null, 0, 1, NavigationCallExp.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getNavigationCallExp_Qualifiers(), this.getOCLExpression(), null, "qualifiers", null, 0, -1, NavigationCallExp.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(nullLiteralExpEClass, NullLiteralExp.class, "NullLiteralExp", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(numericLiteralExpEClass, NumericLiteralExp.class, "NumericLiteralExp", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(oclExpressionEClass, OCLExpression.class, "OCLExpression", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getOCLExpression_TypeValue(), this.getType(), null, "typeValue", null, 0, 1, OCLExpression.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEOperation(getOCLExpression__IsNonNull(), this.getBoolean(), "isNonNull", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		initEOperation(getOCLExpression__IsNull(), this.getBoolean(), "isNull", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		op = initEOperation(getOCLExpression__ValidateTypeIsNotNull__DiagnosticChain_Map(), ecorePackage.getEBoolean(), "validateTypeIsNotNull", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, ecorePackage.getEDiagnosticChain(), "diagnostics", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(ecorePackage.getEMap());
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		addEParameter(op, g1, "context", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		initEClass(operationEClass, Operation.class, "Operation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getOperation_BodyExpression(), this.getLanguageExpression(), null, "bodyExpression", null, 0, 1, Operation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getOperation_IsInvalidating(), this.getBoolean(), "isInvalidating", "false", 1, 1, Operation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(getOperation_IsTransient(), this.getBoolean(), "isTransient", "false", 1, 1, Operation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(getOperation_IsTypeof(), this.getBoolean(), "isTypeof", "false", 1, 1, Operation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(getOperation_IsValidating(), this.getBoolean(), "isValidating", "false", 1, 1, Operation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEReference(getOperation_OwnedParameters(), this.getParameter(), this.getParameter_OwningOperation(), "ownedParameters", null, 0, -1, Operation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getOperation_OwnedPostconditions(), this.getConstraint(), this.getConstraint_OwningPostContext(), "ownedPostconditions", null, 0, -1, Operation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(getOperation_OwnedPreconditions(), this.getConstraint(), this.getConstraint_OwningPreContext(), "ownedPreconditions", null, 0, -1, Operation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(getOperation_OwningClass(), this.getClass_(), this.getClass_OwnedOperations(), "owningClass", null, 0, 1, Operation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getOperation_Precedence(), this.getPrecedence(), null, "precedence", null, 0, 1, Operation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getOperation_RaisedExceptions(), this.getType(), null, "raisedExceptions", null, 0, -1, Operation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(getOperation_RedefinedOperations(), this.getOperation(), null, "redefinedOperations", null, 0, -1, Operation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$

		op = initEOperation(getOperation__ValidateCompatibleReturn__DiagnosticChain_Map(), ecorePackage.getEBoolean(), "validateCompatibleReturn", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, ecorePackage.getEDiagnosticChain(), "diagnostics", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(ecorePackage.getEMap());
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		addEParameter(op, g1, "context", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		op = initEOperation(getOperation__ValidateLoadableImplementation__DiagnosticChain_Map(), ecorePackage.getEBoolean(), "validateLoadableImplementation", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, ecorePackage.getEDiagnosticChain(), "diagnostics", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(ecorePackage.getEMap());
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		addEParameter(op, g1, "context", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		op = initEOperation(getOperation__ValidateUniquePostconditionName__DiagnosticChain_Map(), ecorePackage.getEBoolean(), "validateUniquePostconditionName", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, ecorePackage.getEDiagnosticChain(), "diagnostics", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(ecorePackage.getEMap());
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		addEParameter(op, g1, "context", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		op = initEOperation(getOperation__ValidateUniquePreconditionName__DiagnosticChain_Map(), ecorePackage.getEBoolean(), "validateUniquePreconditionName", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, ecorePackage.getEDiagnosticChain(), "diagnostics", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(ecorePackage.getEMap());
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		addEParameter(op, g1, "context", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		initEClass(operationCallExpEClass, OperationCallExp.class, "OperationCallExp", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getOperationCallExp_IsVirtual(), this.getBoolean(), "isVirtual", "true", 1, 1, OperationCallExp.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEReference(getOperationCallExp_OwnedArguments(), this.getOCLExpression(), null, "ownedArguments", null, 0, -1, OperationCallExp.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getOperationCallExp_ReferredOperation(), this.getOperation(), null, "referredOperation", null, 0, 1, OperationCallExp.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		op = initEOperation(getOperationCallExp__ValidateArgumentCount__DiagnosticChain_Map(), ecorePackage.getEBoolean(), "validateArgumentCount", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, ecorePackage.getEDiagnosticChain(), "diagnostics", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(ecorePackage.getEMap());
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		addEParameter(op, g1, "context", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		op = initEOperation(getOperationCallExp__ValidateArgumentTypeIsConformant__DiagnosticChain_Map(), ecorePackage.getEBoolean(), "validateArgumentTypeIsConformant", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, ecorePackage.getEDiagnosticChain(), "diagnostics", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(ecorePackage.getEMap());
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		addEParameter(op, g1, "context", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		op = initEOperation(getOperationCallExp__ValidateSafeSourceCanBeNull__DiagnosticChain_Map(), ecorePackage.getEBoolean(), "validateSafeSourceCanBeNull", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, ecorePackage.getEDiagnosticChain(), "diagnostics", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(ecorePackage.getEMap());
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		addEParameter(op, g1, "context", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		initEClass(oppositePropertyCallExpEClass, OppositePropertyCallExp.class, "OppositePropertyCallExp", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getOppositePropertyCallExp_ReferredProperty(), this.getProperty(), null, "referredProperty", null, 0, 1, OppositePropertyCallExp.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(orderedSetTypeEClass, OrderedSetType.class, "OrderedSetType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(orphanCompletePackageEClass, OrphanCompletePackage.class, "OrphanCompletePackage", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(packageEClass, org.eclipse.ocl.pivot.Package.class, "Package", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getPackage_URI(), this.getString(), "URI", null, 0, 1, org.eclipse.ocl.pivot.Package.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getPackage_ImportedPackages(), this.getPackage(), null, "importedPackages", null, 0, -1, org.eclipse.ocl.pivot.Package.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getPackage_NsPrefix(), this.getString(), "nsPrefix", null, 0, 1, org.eclipse.ocl.pivot.Package.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getPackage_OwnedClasses(), this.getClass_(), this.getClass_OwningPackage(), "ownedClasses", null, 0, -1, org.eclipse.ocl.pivot.Package.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(getPackage_OwnedInstances(), this.getInstanceSpecification(), this.getInstanceSpecification_OwningPackage(), "ownedInstances", null, 0, -1, org.eclipse.ocl.pivot.Package.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(getPackage_OwnedPackages(), this.getPackage(), this.getPackage_OwningPackage(), "ownedPackages", null, 0, -1, org.eclipse.ocl.pivot.Package.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(getPackage_OwnedProfileApplications(), this.getProfileApplication(), this.getProfileApplication_OwningPackage(), "ownedProfileApplications", null, 0, -1, org.eclipse.ocl.pivot.Package.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(getPackage_OwningPackage(), this.getPackage(), this.getPackage_OwnedPackages(), "owningPackage", null, 0, 1, org.eclipse.ocl.pivot.Package.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(parameterEClass, Parameter.class, "Parameter", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getParameter_IsTypeof(), this.getBoolean(), "isTypeof", "false", 1, 1, Parameter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEReference(getParameter_OwningOperation(), this.getOperation(), this.getOperation_OwnedParameters(), "owningOperation", null, 0, 1, Parameter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(parameterVariableEClass, ParameterVariable.class, "ParameterVariable", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		op = initEOperation(getParameterVariable__ValidateHasNoInitializer__DiagnosticChain_Map(), ecorePackage.getEBoolean(), "validateHasNoInitializer", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, ecorePackage.getEDiagnosticChain(), "diagnostics", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(ecorePackage.getEMap());
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		addEParameter(op, g1, "context", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		initEClass(pivotableEClass, Pivotable.class, "Pivotable", IS_ABSTRACT, IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(precedenceEClass, Precedence.class, "Precedence", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getPrecedence_Associativity(), this.getAssociativityKind(), "associativity", "left", 0, 1, Precedence.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(getPrecedence_Order(), this.getInteger(), "order", "0", 1, 1, Precedence.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$

		initEClass(primitiveCompletePackageEClass, PrimitiveCompletePackage.class, "PrimitiveCompletePackage", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(primitiveLiteralExpEClass, PrimitiveLiteralExp.class, "PrimitiveLiteralExp", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(primitiveTypeEClass, PrimitiveType.class, "PrimitiveType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getPrimitiveType_Coercions(), this.getOperation(), null, "coercions", null, 0, -1, PrimitiveType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(profileEClass, Profile.class, "Profile", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getProfile_ProfileApplications(), this.getProfileApplication(), this.getProfileApplication_AppliedProfile(), "profileApplications", null, 0, -1, Profile.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$

		initEClass(profileApplicationEClass, ProfileApplication.class, "ProfileApplication", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getProfileApplication_AppliedProfile(), this.getProfile(), this.getProfile_ProfileApplications(), "appliedProfile", null, 1, 1, ProfileApplication.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getProfileApplication_IsStrict(), this.getBoolean(), "isStrict", "false", 1, 1, ProfileApplication.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEReference(getProfileApplication_OwningPackage(), this.getPackage(), this.getPackage_OwnedProfileApplications(), "owningPackage", null, 1, 1, ProfileApplication.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(propertyEClass, Property.class, "Property", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getProperty_AssociationClass(), this.getAssociationClass(), this.getAssociationClass_UnownedAttributes(), "associationClass", null, 0, 1, Property.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getProperty_DefaultValue(), this.getObject(), "defaultValue", null, 0, 1, Property.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getProperty_DefaultValueString(), this.getString(), "defaultValueString", null, 0, 1, Property.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getProperty_IsComposite(), this.getBoolean(), "isComposite", "false", 1, 1, Property.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(getProperty_IsDerived(), this.getBoolean(), "isDerived", "false", 1, 1, Property.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(getProperty_IsID(), this.getBoolean(), "isID", "false", 1, 1, Property.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(getProperty_IsImplicit(), this.getBoolean(), "isImplicit", "false", 1, 1, Property.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(getProperty_IsReadOnly(), this.getBoolean(), "isReadOnly", "false", 1, 1, Property.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(getProperty_IsResolveProxies(), this.getBoolean(), "isResolveProxies", "true", 1, 1, Property.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(getProperty_IsTransient(), this.getBoolean(), "isTransient", "false", 1, 1, Property.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(getProperty_IsUnsettable(), this.getBoolean(), "isUnsettable", "false", 1, 1, Property.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(getProperty_IsVolatile(), this.getBoolean(), "isVolatile", "false", 1, 1, Property.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEReference(getProperty_Keys(), this.getProperty(), null, "keys", null, 0, -1, Property.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(getProperty_Opposite(), this.getProperty(), null, "opposite", null, 0, 1, Property.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getProperty_OwnedExpression(), this.getLanguageExpression(), null, "ownedExpression", null, 0, 1, Property.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getProperty_OwningClass(), this.getClass_(), this.getClass_OwnedProperties(), "owningClass", null, 0, 1, Property.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getProperty_RedefinedProperties(), this.getProperty(), null, "redefinedProperties", null, 0, -1, Property.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(getProperty_ReferredProperty(), this.getProperty(), null, "referredProperty", null, 0, 1, Property.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getProperty_SubsettedProperty(), this.getProperty(), null, "subsettedProperty", null, 0, -1, Property.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$

		op = initEOperation(getProperty__IsAttribute__Property(), this.getBoolean(), "isAttribute", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, this.getProperty(), "p", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		op = initEOperation(getProperty__ValidateCompatibleDefaultExpression__DiagnosticChain_Map(), ecorePackage.getEBoolean(), "validateCompatibleDefaultExpression", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, ecorePackage.getEDiagnosticChain(), "diagnostics", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(ecorePackage.getEMap());
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		addEParameter(op, g1, "context", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		initEClass(propertyCallExpEClass, PropertyCallExp.class, "PropertyCallExp", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getPropertyCallExp_ReferredProperty(), this.getProperty(), null, "referredProperty", null, 0, 1, PropertyCallExp.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEOperation(getPropertyCallExp__GetSpecializedReferredPropertyOwningType(), this.getClass_(), "getSpecializedReferredPropertyOwningType", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		initEOperation(getPropertyCallExp__GetSpecializedReferredPropertyType(), this.getClass_(), "getSpecializedReferredPropertyType", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		op = initEOperation(getPropertyCallExp__ValidateCompatibleResultType__DiagnosticChain_Map(), ecorePackage.getEBoolean(), "validateCompatibleResultType", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, ecorePackage.getEDiagnosticChain(), "diagnostics", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(ecorePackage.getEMap());
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		addEParameter(op, g1, "context", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		op = initEOperation(getPropertyCallExp__ValidateNonStaticSourceTypeIsConformant__DiagnosticChain_Map(), ecorePackage.getEBoolean(), "validateNonStaticSourceTypeIsConformant", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, ecorePackage.getEDiagnosticChain(), "diagnostics", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(ecorePackage.getEMap());
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		addEParameter(op, g1, "context", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		op = initEOperation(getPropertyCallExp__ValidateSafeSourceCanBeNull__DiagnosticChain_Map(), ecorePackage.getEBoolean(), "validateSafeSourceCanBeNull", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, ecorePackage.getEDiagnosticChain(), "diagnostics", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(ecorePackage.getEMap());
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		addEParameter(op, g1, "context", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		op = initEOperation(getPropertyCallExp__ValidateUnsafeSourceCanNotBeNull__DiagnosticChain_Map(), ecorePackage.getEBoolean(), "validateUnsafeSourceCanNotBeNull", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, ecorePackage.getEDiagnosticChain(), "diagnostics", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(ecorePackage.getEMap());
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		addEParameter(op, g1, "context", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		initEClass(pseudostateEClass, Pseudostate.class, "Pseudostate", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getPseudostate_Kind(), this.getPseudostateKind(), "kind", "initial", 1, 1, Pseudostate.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEReference(getPseudostate_OwningState(), this.getState(), this.getState_OwnedConnectionPoints(), "owningState", null, 0, 1, Pseudostate.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getPseudostate_OwningStateMachine(), this.getStateMachine(), this.getStateMachine_OwnedConnectionPoints(), "owningStateMachine", null, 0, 1, Pseudostate.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(realLiteralExpEClass, RealLiteralExp.class, "RealLiteralExp", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getRealLiteralExp_RealSymbol(), this.getReal(), "realSymbol", null, 1, 1, RealLiteralExp.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(referringElementEClass, ReferringElement.class, "ReferringElement", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEOperation(getReferringElement__GetReferredElement(), this.getElement(), "getReferredElement", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		initEClass(regionEClass, Region.class, "Region", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getRegion_ExtendedRegion(), this.getRegion(), null, "extendedRegion", null, 0, 1, Region.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getRegion_OwnedSubvertexes(), this.getVertex(), this.getVertex_OwningRegion(), "ownedSubvertexes", null, 0, -1, Region.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(getRegion_OwnedTransitions(), this.getTransition(), this.getTransition_OwningRegion(), "ownedTransitions", null, 0, -1, Region.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(getRegion_OwningState(), this.getState(), this.getState_OwnedRegions(), "owningState", null, 0, 1, Region.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getRegion_OwningStateMachine(), this.getStateMachine(), this.getStateMachine_OwnedRegions(), "owningStateMachine", null, 0, 1, Region.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(resultVariableEClass, ResultVariable.class, "ResultVariable", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		op = initEOperation(getResultVariable__ValidateCompatibleNullityForInitializer__DiagnosticChain_Map(), ecorePackage.getEBoolean(), "validateCompatibleNullityForInitializer", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, ecorePackage.getEDiagnosticChain(), "diagnostics", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(ecorePackage.getEMap());
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		addEParameter(op, g1, "context", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		op = initEOperation(getResultVariable__ValidateCompatibleTypeForInitializer__DiagnosticChain_Map(), ecorePackage.getEBoolean(), "validateCompatibleTypeForInitializer", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, ecorePackage.getEDiagnosticChain(), "diagnostics", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(ecorePackage.getEMap());
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		addEParameter(op, g1, "context", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		op = initEOperation(getResultVariable__ValidateHasInitializer__DiagnosticChain_Map(), ecorePackage.getEBoolean(), "validateHasInitializer", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, ecorePackage.getEDiagnosticChain(), "diagnostics", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(ecorePackage.getEMap());
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		addEParameter(op, g1, "context", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		initEClass(selfTypeEClass, SelfType.class, "SelfType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		op = initEOperation(getSelfType__SpecializeIn__CallExp_Type(), this.getType(), "specializeIn", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, this.getCallExp(), "expr", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, this.getType(), "selfType", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		initEClass(sendSignalActionEClass, SendSignalAction.class, "SendSignalAction", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getSendSignalAction_Signal(), this.getSignal(), null, "signal", null, 1, 1, SendSignalAction.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(sequenceTypeEClass, SequenceType.class, "SequenceType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(setTypeEClass, SetType.class, "SetType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(shadowExpEClass, ShadowExp.class, "ShadowExp", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getShadowExp_OwnedParts(), this.getShadowPart(), null, "ownedParts", null, 0, -1, ShadowExp.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getShadowExp_Value(), this.getString(), "value", null, 0, 1, ShadowExp.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		op = initEOperation(getShadowExp__ValidateClassHasNoStringValueInitializer__DiagnosticChain_Map(), ecorePackage.getEBoolean(), "validateClassHasNoStringValueInitializer", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, ecorePackage.getEDiagnosticChain(), "diagnostics", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(ecorePackage.getEMap());
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		addEParameter(op, g1, "context", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		op = initEOperation(getShadowExp__ValidateDataTypeHasNoPartInitializers__DiagnosticChain_Map(), ecorePackage.getEBoolean(), "validateDataTypeHasNoPartInitializers", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, ecorePackage.getEDiagnosticChain(), "diagnostics", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(ecorePackage.getEMap());
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		addEParameter(op, g1, "context", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		op = initEOperation(getShadowExp__ValidateDataTypeHasOnePartInitializer__DiagnosticChain_Map(), ecorePackage.getEBoolean(), "validateDataTypeHasOnePartInitializer", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, ecorePackage.getEDiagnosticChain(), "diagnostics", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(ecorePackage.getEMap());
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		addEParameter(op, g1, "context", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		op = initEOperation(getShadowExp__ValidateDataTypeHasStringValueInitializer__DiagnosticChain_Map(), ecorePackage.getEBoolean(), "validateDataTypeHasStringValueInitializer", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, ecorePackage.getEDiagnosticChain(), "diagnostics", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(ecorePackage.getEMap());
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		addEParameter(op, g1, "context", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		op = initEOperation(getShadowExp__ValidateInitializesAllClassProperties__DiagnosticChain_Map(), ecorePackage.getEBoolean(), "validateInitializesAllClassProperties", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, ecorePackage.getEDiagnosticChain(), "diagnostics", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(ecorePackage.getEMap());
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		addEParameter(op, g1, "context", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		op = initEOperation(getShadowExp__ValidateTypeIsNotInvalid__DiagnosticChain_Map(), ecorePackage.getEBoolean(), "validateTypeIsNotInvalid", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, ecorePackage.getEDiagnosticChain(), "diagnostics", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(ecorePackage.getEMap());
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		addEParameter(op, g1, "context", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		initEClass(shadowPartEClass, ShadowPart.class, "ShadowPart", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getShadowPart_OwnedInit(), this.getOCLExpression(), null, "ownedInit", null, 1, 1, ShadowPart.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getShadowPart_ReferredProperty(), this.getProperty(), null, "referredProperty", null, 1, 1, ShadowPart.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		op = initEOperation(getShadowPart__ValidateCompatibleInitialiserType__DiagnosticChain_Map(), ecorePackage.getEBoolean(), "validateCompatibleInitialiserType", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, ecorePackage.getEDiagnosticChain(), "diagnostics", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(ecorePackage.getEMap());
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		addEParameter(op, g1, "context", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		op = initEOperation(getShadowPart__ValidateTypeIsNotInvalid__DiagnosticChain_Map(), ecorePackage.getEBoolean(), "validateTypeIsNotInvalid", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, ecorePackage.getEDiagnosticChain(), "diagnostics", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(ecorePackage.getEMap());
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		addEParameter(op, g1, "context", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		op = initEOperation(getShadowPart__ValidateTypeIsNotNull__DiagnosticChain_Map(), ecorePackage.getEBoolean(), "validateTypeIsNotNull", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, ecorePackage.getEDiagnosticChain(), "diagnostics", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(ecorePackage.getEMap());
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		addEParameter(op, g1, "context", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		initEClass(signalEClass, Signal.class, "Signal", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(slotEClass, Slot.class, "Slot", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getSlot_DefiningProperty(), this.getProperty(), null, "definingProperty", null, 1, 1, Slot.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getSlot_OwnedValues(), this.getValueSpecification(), null, "ownedValues", null, 0, -1, Slot.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getSlot_OwningInstance(), this.getInstanceSpecification(), this.getInstanceSpecification_OwnedSlots(), "owningInstance", null, 1, 1, Slot.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(standardLibraryEClass, StandardLibrary.class, "StandardLibrary", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getStandardLibrary_OwningCompleteEnvironment(), this.getCompleteEnvironment(), this.getCompleteEnvironment_OwnedStandardLibrary(), "owningCompleteEnvironment", null, 0, 1, StandardLibrary.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(stateEClass, State.class, "State", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getState_IsComposite(), this.getBoolean(), "isComposite", null, 1, 1, State.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getState_IsOrthogonal(), this.getBoolean(), "isOrthogonal", null, 1, 1, State.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getState_IsSimple(), this.getBoolean(), "isSimple", null, 1, 1, State.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getState_IsSubmachineState(), this.getBoolean(), "isSubmachineState", null, 1, 1, State.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getState_OwnedConnectionPoints(), this.getPseudostate(), this.getPseudostate_OwningState(), "ownedConnectionPoints", null, 0, -1, State.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(getState_OwnedConnections(), this.getConnectionPointReference(), this.getConnectionPointReference_OwningState(), "ownedConnections", null, 0, -1, State.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(getState_OwnedDeferrableTriggers(), this.getTrigger(), this.getTrigger_OwningState(), "ownedDeferrableTriggers", null, 0, -1, State.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(getState_OwnedDoActivity(), this.getBehavior(), null, "ownedDoActivity", null, 0, 1, State.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getState_OwnedEntry(), this.getBehavior(), null, "ownedEntry", null, 0, 1, State.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getState_OwnedExit(), this.getBehavior(), null, "ownedExit", null, 0, 1, State.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getState_OwnedRegions(), this.getRegion(), this.getRegion_OwningState(), "ownedRegions", null, 0, -1, State.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(getState_OwnedStateInvariant(), this.getConstraint(), this.getConstraint_OwningState(), "ownedStateInvariant", null, 0, 1, State.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getState_RedefinedState(), this.getState(), null, "redefinedState", null, 0, 1, State.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getState_Submachines(), this.getStateMachine(), this.getStateMachine_SubmachineStates(), "submachines", null, 0, 1, State.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(stateExpEClass, StateExp.class, "StateExp", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getStateExp_ReferredState(), this.getState(), null, "referredState", null, 0, 1, StateExp.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		op = initEOperation(getStateExp__ValidateTypeIsNotInvalid__DiagnosticChain_Map(), ecorePackage.getEBoolean(), "validateTypeIsNotInvalid", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, ecorePackage.getEDiagnosticChain(), "diagnostics", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(ecorePackage.getEMap());
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		addEParameter(op, g1, "context", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		initEClass(stateMachineEClass, StateMachine.class, "StateMachine", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getStateMachine_ExtendedStateMachines(), this.getStateMachine(), null, "extendedStateMachines", null, 0, -1, StateMachine.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(getStateMachine_OwnedConnectionPoints(), this.getPseudostate(), this.getPseudostate_OwningStateMachine(), "ownedConnectionPoints", null, 0, -1, StateMachine.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(getStateMachine_OwnedRegions(), this.getRegion(), this.getRegion_OwningStateMachine(), "ownedRegions", null, 1, -1, StateMachine.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(getStateMachine_SubmachineStates(), this.getState(), this.getState_Submachines(), "submachineStates", null, 0, -1, StateMachine.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$

		initEClass(stereotypeEClass, Stereotype.class, "Stereotype", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getStereotype_OwnedExtenders(), this.getStereotypeExtender(), this.getStereotypeExtender_OwningStereotype(), "ownedExtenders", null, 0, -1, Stereotype.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$

		initEClass(stereotypeExtenderEClass, StereotypeExtender.class, "StereotypeExtender", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getStereotypeExtender_Class(), this.getClass_(), this.getClass_Extenders(), "class", null, 1, 1, StereotypeExtender.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getStereotypeExtender_IsRequired(), this.getBoolean(), "isRequired", "false", 1, 1, StereotypeExtender.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEReference(getStereotypeExtender_OwningStereotype(), this.getStereotype(), this.getStereotype_OwnedExtenders(), "owningStereotype", null, 1, 1, StereotypeExtender.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(stringLiteralExpEClass, StringLiteralExp.class, "StringLiteralExp", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getStringLiteralExp_StringSymbol(), this.getString(), "stringSymbol", null, 1, 1, StringLiteralExp.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(templateBindingEClass, TemplateBinding.class, "TemplateBinding", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getTemplateBinding_OwnedSubstitutions(), this.getTemplateParameterSubstitution(), this.getTemplateParameterSubstitution_OwningBinding(), "ownedSubstitutions", null, 1, -1, TemplateBinding.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(getTemplateBinding_OwningElement(), this.getTemplateableElement(), this.getTemplateableElement_OwnedBindings(), "owningElement", null, 1, 1, TemplateBinding.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getTemplateBinding_TemplateSignature(), this.getTemplateSignature(), null, "templateSignature", null, 1, 1, TemplateBinding.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(templateParameterEClass, TemplateParameter.class, "TemplateParameter", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getTemplateParameter_ConstrainingClasses(), this.getClass_(), null, "constrainingClasses", null, 0, -1, TemplateParameter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(getTemplateParameter_OwningSignature(), this.getTemplateSignature(), this.getTemplateSignature_OwnedParameters(), "owningSignature", null, 1, 1, TemplateParameter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(templateParameterSubstitutionEClass, TemplateParameterSubstitution.class, "TemplateParameterSubstitution", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getTemplateParameterSubstitution_Actual(), this.getType(), null, "actual", null, 1, 1, TemplateParameterSubstitution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getTemplateParameterSubstitution_Formal(), this.getTemplateParameter(), null, "formal", null, 1, 1, TemplateParameterSubstitution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getTemplateParameterSubstitution_OwnedWildcard(), this.getWildcardType(), null, "ownedWildcard", null, 0, 1, TemplateParameterSubstitution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getTemplateParameterSubstitution_OwningBinding(), this.getTemplateBinding(), this.getTemplateBinding_OwnedSubstitutions(), "owningBinding", null, 1, 1, TemplateParameterSubstitution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(templateSignatureEClass, TemplateSignature.class, "TemplateSignature", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getTemplateSignature_OwnedParameters(), this.getTemplateParameter(), this.getTemplateParameter_OwningSignature(), "ownedParameters", null, 1, -1, TemplateSignature.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getTemplateSignature_OwningElement(), this.getTemplateableElement(), this.getTemplateableElement_OwnedSignature(), "owningElement", null, 1, 1, TemplateSignature.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(templateableElementEClass, TemplateableElement.class, "TemplateableElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getTemplateableElement_OwnedBindings(), this.getTemplateBinding(), this.getTemplateBinding_OwningElement(), "ownedBindings", null, 0, -1, TemplateableElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(getTemplateableElement_OwnedSignature(), this.getTemplateSignature(), this.getTemplateSignature_OwningElement(), "ownedSignature", null, 0, 1, TemplateableElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getTemplateableElement_UnspecializedElement(), this.getTemplateableElement(), null, "unspecializedElement", null, 0, 1, TemplateableElement.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(transitionEClass, Transition.class, "Transition", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getTransition_Kind(), this.getTransitionKind(), "kind", "external", 1, 1, Transition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEReference(getTransition_OwnedEffect(), this.getBehavior(), this.getBehavior_OwningTransition(), "ownedEffect", null, 0, 1, Transition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getTransition_OwnedGuard(), this.getConstraint(), this.getConstraint_OwningTransition(), "ownedGuard", null, 0, 1, Transition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getTransition_OwnedTriggers(), this.getTrigger(), this.getTrigger_OwningTransition(), "ownedTriggers", null, 0, -1, Transition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(getTransition_OwningRegion(), this.getRegion(), this.getRegion_OwnedTransitions(), "owningRegion", null, 1, 1, Transition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getTransition_Source(), this.getVertex(), this.getVertex_OutgoingTransitions(), "source", null, 1, 1, Transition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getTransition_Target(), this.getVertex(), this.getVertex_IncomingTransitions(), "target", null, 1, 1, Transition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(triggerEClass, Trigger.class, "Trigger", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getTrigger_OwningState(), this.getState(), this.getState_OwnedDeferrableTriggers(), "owningState", null, 0, 1, Trigger.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getTrigger_OwningTransition(), this.getTransition(), this.getTransition_OwnedTriggers(), "owningTransition", null, 0, 1, Trigger.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(tupleLiteralExpEClass, TupleLiteralExp.class, "TupleLiteralExp", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getTupleLiteralExp_OwnedParts(), this.getTupleLiteralPart(), null, "ownedParts", null, 0, -1, TupleLiteralExp.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(tupleLiteralPartEClass, TupleLiteralPart.class, "TupleLiteralPart", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getTupleLiteralPart_OwnedInit(), this.getOCLExpression(), null, "ownedInit", null, 0, 1, TupleLiteralPart.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		op = initEOperation(getTupleLiteralPart__ValidateCompatibleInitialiserType__DiagnosticChain_Map(), ecorePackage.getEBoolean(), "validateCompatibleInitialiserType", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, ecorePackage.getEDiagnosticChain(), "diagnostics", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(ecorePackage.getEMap());
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		addEParameter(op, g1, "context", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		op = initEOperation(getTupleLiteralPart__ValidateTypeIsNotInvalid__DiagnosticChain_Map(), ecorePackage.getEBoolean(), "validateTypeIsNotInvalid", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, ecorePackage.getEDiagnosticChain(), "diagnostics", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(ecorePackage.getEMap());
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		addEParameter(op, g1, "context", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		initEClass(tupleTypeEClass, TupleType.class, "TupleType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(typeEClass, Type.class, "Type", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEOperation(getType__FlattenedType(), this.getType(), "flattenedType", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		initEOperation(getType__IsClass(), this.getClass_(), "isClass", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		initEOperation(getType__IsTemplateParameter(), this.getTemplateParameter(), "isTemplateParameter", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		op = initEOperation(getType__SpecializeIn__CallExp_Type(), this.getType(), "specializeIn", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, this.getCallExp(), "expr", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, this.getType(), "selfType", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		initEClass(typeExpEClass, TypeExp.class, "TypeExp", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getTypeExp_ReferredType(), this.getType(), null, "referredType", null, 0, 1, TypeExp.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(typedElementEClass, TypedElement.class, "TypedElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getTypedElement_IsMany(), this.getBoolean(), "isMany", null, 1, 1, TypedElement.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getTypedElement_IsRequired(), this.getBoolean(), "isRequired", "true", 1, 1, TypedElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEReference(getTypedElement_Type(), this.getType(), null, "type", null, 0, 1, TypedElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		op = initEOperation(getTypedElement__CompatibleBody__ValueSpecification(), this.getBoolean(), "CompatibleBody", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, this.getValueSpecification(), "bodySpecification", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		initEClass(unlimitedNaturalLiteralExpEClass, UnlimitedNaturalLiteralExp.class, "UnlimitedNaturalLiteralExp", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getUnlimitedNaturalLiteralExp_UnlimitedNaturalSymbol(), this.getUnlimitedNatural(), "unlimitedNaturalSymbol", null, 1, 1, UnlimitedNaturalLiteralExp.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(unspecifiedValueExpEClass, UnspecifiedValueExp.class, "UnspecifiedValueExp", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(valueSpecificationEClass, ValueSpecification.class, "ValueSpecification", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEOperation(getValueSpecification__BooleanValue(), this.getBoolean(), "booleanValue", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		initEOperation(getValueSpecification__IntegerValue(), this.getInteger(), "integerValue", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		initEOperation(getValueSpecification__IsComputable(), this.getBoolean(), "isComputable", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		initEOperation(getValueSpecification__IsNull(), this.getBoolean(), "isNull", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		initEOperation(getValueSpecification__StringValue(), this.getString(), "stringValue", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		initEOperation(getValueSpecification__UnlimitedValue(), this.getUnlimitedNatural(), "unlimitedValue", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		initEClass(variableEClass, Variable.class, "Variable", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getVariable_IsImplicit(), this.getBoolean(), "isImplicit", "false", 1, 1, Variable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEReference(getVariable_OwnedInit(), this.getOCLExpression(), null, "ownedInit", null, 0, 1, Variable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getVariable_RepresentedParameter(), this.getParameter(), null, "representedParameter", null, 0, 1, Variable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		op = initEOperation(getVariable__ValidateCompatibleInitialiserType__DiagnosticChain_Map(), ecorePackage.getEBoolean(), "validateCompatibleInitialiserType", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, ecorePackage.getEDiagnosticChain(), "diagnostics", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(ecorePackage.getEMap());
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		addEParameter(op, g1, "context", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		initEClass(variableDeclarationEClass, VariableDeclaration.class, "VariableDeclaration", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getVariableDeclaration_TypeValue(), this.getType(), null, "typeValue", null, 0, 1, VariableDeclaration.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		op = initEOperation(getVariableDeclaration__ValidateNameIsNotNull__DiagnosticChain_Map(), ecorePackage.getEBoolean(), "validateNameIsNotNull", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, ecorePackage.getEDiagnosticChain(), "diagnostics", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(ecorePackage.getEMap());
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		addEParameter(op, g1, "context", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		op = initEOperation(getVariableDeclaration__ValidateTypeIsNotInvalid__DiagnosticChain_Map(), ecorePackage.getEBoolean(), "validateTypeIsNotInvalid", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, ecorePackage.getEDiagnosticChain(), "diagnostics", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(ecorePackage.getEMap());
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		addEParameter(op, g1, "context", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		op = initEOperation(getVariableDeclaration__ValidateTypeIsNotNull__DiagnosticChain_Map(), ecorePackage.getEBoolean(), "validateTypeIsNotNull", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, ecorePackage.getEDiagnosticChain(), "diagnostics", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(ecorePackage.getEMap());
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		addEParameter(op, g1, "context", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		initEClass(variableExpEClass, VariableExp.class, "VariableExp", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getVariableExp_IsImplicit(), this.getBoolean(), "isImplicit", "false", 1, 1, VariableExp.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEReference(getVariableExp_ReferredVariable(), this.getVariableDeclaration(), null, "referredVariable", null, 0, 1, VariableExp.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		op = initEOperation(getVariableExp__ValidateTypeIsNotInvalid__DiagnosticChain_Map(), ecorePackage.getEBoolean(), "validateTypeIsNotInvalid", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, ecorePackage.getEDiagnosticChain(), "diagnostics", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(ecorePackage.getEMap());
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		addEParameter(op, g1, "context", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		initEClass(vertexEClass, Vertex.class, "Vertex", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getVertex_IncomingTransitions(), this.getTransition(), this.getTransition_Target(), "incomingTransitions", null, 0, -1, Vertex.class, !IS_TRANSIENT, !IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(getVertex_OutgoingTransitions(), this.getTransition(), this.getTransition_Source(), "outgoingTransitions", null, 0, -1, Vertex.class, !IS_TRANSIENT, !IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(getVertex_OwningRegion(), this.getRegion(), this.getRegion_OwnedSubvertexes(), "owningRegion", null, 0, 1, Vertex.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(visitableEClass, Visitable.class, "Visitable", IS_ABSTRACT, IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(voidTypeEClass, VoidType.class, "VoidType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(wildcardTypeEClass, WildcardType.class, "WildcardType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getWildcardType_LowerBound(), this.getType(), null, "lowerBound", null, 0, 1, WildcardType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getWildcardType_UpperBound(), this.getType(), null, "upperBound", null, 0, 1, WildcardType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		// Initialize enums and add enum literals
		initEEnum(associativityKindEEnum, AssociativityKind.class, "AssociativityKind"); //$NON-NLS-1$
		addEEnumLiteral(associativityKindEEnum, AssociativityKind.LEFT);
		addEEnumLiteral(associativityKindEEnum, AssociativityKind.RIGHT);

		initEEnum(collectionKindEEnum, CollectionKind.class, "CollectionKind"); //$NON-NLS-1$
		addEEnumLiteral(collectionKindEEnum, CollectionKind.COLLECTION);
		addEEnumLiteral(collectionKindEEnum, CollectionKind.SET);
		addEEnumLiteral(collectionKindEEnum, CollectionKind.ORDERED_SET);
		addEEnumLiteral(collectionKindEEnum, CollectionKind.BAG);
		addEEnumLiteral(collectionKindEEnum, CollectionKind.SEQUENCE);

		initEEnum(pseudostateKindEEnum, PseudostateKind.class, "PseudostateKind"); //$NON-NLS-1$
		addEEnumLiteral(pseudostateKindEEnum, PseudostateKind.INITIAL);
		addEEnumLiteral(pseudostateKindEEnum, PseudostateKind.DEEP_HISTORY);
		addEEnumLiteral(pseudostateKindEEnum, PseudostateKind.SHALLOW_HISTORY);
		addEEnumLiteral(pseudostateKindEEnum, PseudostateKind.JOIN);
		addEEnumLiteral(pseudostateKindEEnum, PseudostateKind.FORK);
		addEEnumLiteral(pseudostateKindEEnum, PseudostateKind.JUNCTION);
		addEEnumLiteral(pseudostateKindEEnum, PseudostateKind.CHOICE);
		addEEnumLiteral(pseudostateKindEEnum, PseudostateKind.ENTRY_POINT);
		addEEnumLiteral(pseudostateKindEEnum, PseudostateKind.EXIT_POINT);
		addEEnumLiteral(pseudostateKindEEnum, PseudostateKind.TERMINATE);

		initEEnum(transitionKindEEnum, TransitionKind.class, "TransitionKind"); //$NON-NLS-1$
		addEEnumLiteral(transitionKindEEnum, TransitionKind.INTERNAL);
		addEEnumLiteral(transitionKindEEnum, TransitionKind.LOCAL);
		addEEnumLiteral(transitionKindEEnum, TransitionKind.EXTERNAL);

		// Initialize data types
		initEDataType(booleanEDataType, boolean.class, "Boolean", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEDataType(integerEDataType, Number.class, "Integer", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEDataType(libraryFeatureEDataType, LibraryFeature.class, "LibraryFeature", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEDataType(objectEDataType, Object.class, "Object", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEDataType(realEDataType, Number.class, "Real", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEDataType(stringEDataType, String.class, "String", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEDataType(throwableEDataType, Throwable.class, "Throwable", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEDataType(unlimitedNaturalEDataType, Number.class, "UnlimitedNatural", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		// Create resource
		createResource(eNS_URI);

		// Create annotations
		// http://www.eclipse.org/OCL/ASMetamodel
		createASMetamodelAnnotations();
		// http://www.eclipse.org/emf/2002/Ecore
		createEcoreAnnotations();
		// http://www.eclipse.org/uml2/2.0.0/UML
		createUMLAnnotations();
	}

	/**
	 * Initializes the annotations for <b>http://www.eclipse.org/OCL/ASMetamodel</b>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void createASMetamodelAnnotations()
	{
		String source = "http://www.eclipse.org/OCL/ASMetamodel"; //$NON-NLS-1$
		addAnnotation
		(this,
			source,
			new String[]
					{
					});
	}

	/**
	 * Initializes the annotations for <b>http://www.eclipse.org/emf/2002/Ecore</b>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void createEcoreAnnotations()
	{
		String source = "http://www.eclipse.org/emf/2002/Ecore"; //$NON-NLS-1$
		addAnnotation
		(this,
			source,
			new String[]
					{
					});
	}

	/**
	 * Initializes the annotations for <b>http://www.eclipse.org/uml2/2.0.0/UML</b>.
	 * <!-- begin-user-doc -->
	 * @since 1.3
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void createUMLAnnotations()
	{
		String source = "http://www.eclipse.org/uml2/2.0.0/UML"; //$NON-NLS-1$
		addAnnotation
		(getBooleanLiteralExp__ValidateTypeIsBoolean__DiagnosticChain_Map(),
			source,
			new String[]
					{
						"originalName", "TypeIsBoolean" //$NON-NLS-1$ //$NON-NLS-2$
					});
		addAnnotation
		(getCallExp__ValidateTypeIsNotInvalid__DiagnosticChain_Map(),
			source,
			new String[]
					{
						"originalName", "TypeIsNotInvalid" //$NON-NLS-1$ //$NON-NLS-2$
					});
		addAnnotation
		(getClass__ValidateNameIsNotNull__DiagnosticChain_Map(),
			source,
			new String[]
					{
						"originalName", "NameIsNotNull" //$NON-NLS-1$ //$NON-NLS-2$
					});
		addAnnotation
		(getClass__ValidateUniqueInvariantName__DiagnosticChain_Map(),
			source,
			new String[]
					{
						"originalName", "UniqueInvariantName" //$NON-NLS-1$ //$NON-NLS-2$
					});
		addAnnotation
		(getCollectionItem__ValidateTypeIsItemType__DiagnosticChain_Map(),
			source,
			new String[]
					{
						"originalName", "TypeIsItemType" //$NON-NLS-1$ //$NON-NLS-2$
					});
		addAnnotation
		(getCollectionLiteralExp__ValidateBagKindIsBag__DiagnosticChain_Map(),
			source,
			new String[]
					{
						"originalName", "BagKindIsBag" //$NON-NLS-1$ //$NON-NLS-2$
					});
		addAnnotation
		(getCollectionLiteralExp__ValidateCollectionKindIsConcrete__DiagnosticChain_Map(),
			source,
			new String[]
					{
						"originalName", "CollectionKindIsConcrete" //$NON-NLS-1$ //$NON-NLS-2$
					});
		addAnnotation
		(getCollectionLiteralExp__ValidateOrderedSetKindIsOrderedSet__DiagnosticChain_Map(),
			source,
			new String[]
					{
						"originalName", "OrderedSetKindIsOrderedSet" //$NON-NLS-1$ //$NON-NLS-2$
					});
		addAnnotation
		(getCollectionLiteralExp__ValidateSequenceKindIsSequence__DiagnosticChain_Map(),
			source,
			new String[]
					{
						"originalName", "SequenceKindIsSequence" //$NON-NLS-1$ //$NON-NLS-2$
					});
		addAnnotation
		(getCollectionLiteralExp__ValidateSetKindIsSet__DiagnosticChain_Map(),
			source,
			new String[]
					{
						"originalName", "SetKindIsSet" //$NON-NLS-1$ //$NON-NLS-2$
					});
		addAnnotation
		(getCollectionLiteralPart__ValidateTypeIsNotInvalid__DiagnosticChain_Map(),
			source,
			new String[]
					{
						"originalName", "TypeIsNotInvalid" //$NON-NLS-1$ //$NON-NLS-2$
					});
		addAnnotation
		(getConstraint__ValidateUniqueName__DiagnosticChain_Map(),
			source,
			new String[]
					{
						"originalName", "UniqueName" //$NON-NLS-1$ //$NON-NLS-2$
					});
		addAnnotation
		(getEnumLiteralExp__ValidateTypeIsEnumerationType__DiagnosticChain_Map(),
			source,
			new String[]
					{
						"originalName", "TypeIsEnumerationType" //$NON-NLS-1$ //$NON-NLS-2$
					});
		addAnnotation
		(getFeature__ValidateNameIsNotNull__DiagnosticChain_Map(),
			source,
			new String[]
					{
						"originalName", "NameIsNotNull" //$NON-NLS-1$ //$NON-NLS-2$
					});
		addAnnotation
		(getFeature__ValidateTypeIsNotInvalid__DiagnosticChain_Map(),
			source,
			new String[]
					{
						"originalName", "TypeIsNotInvalid" //$NON-NLS-1$ //$NON-NLS-2$
					});
		addAnnotation
		(getFeature__ValidateTypeIsNotNull__DiagnosticChain_Map(),
			source,
			new String[]
					{
						"originalName", "TypeIsNotNull" //$NON-NLS-1$ //$NON-NLS-2$
					});
		addAnnotation
		(getIfExp__ValidateConditionTypeIsBoolean__DiagnosticChain_Map(),
			source,
			new String[]
					{
						"originalName", "ConditionTypeIsBoolean" //$NON-NLS-1$ //$NON-NLS-2$
					});
		addAnnotation
		(getIfExp__ValidateTypeIsNotInvalid__DiagnosticChain_Map(),
			source,
			new String[]
					{
						"originalName", "TypeIsNotInvalid" //$NON-NLS-1$ //$NON-NLS-2$
					});
		addAnnotation
		(getIntegerLiteralExp__ValidateTypeIsInteger__DiagnosticChain_Map(),
			source,
			new String[]
					{
						"originalName", "TypeIsInteger" //$NON-NLS-1$ //$NON-NLS-2$
					});
		addAnnotation
		(getIterateExp__ValidateBodyTypeConformsToResultType__DiagnosticChain_Map(),
			source,
			new String[]
					{
						"originalName", "BodyTypeConformsToResultType" //$NON-NLS-1$ //$NON-NLS-2$
					});
		addAnnotation
		(getIterateExp__ValidateOneInitializer__DiagnosticChain_Map(),
			source,
			new String[]
					{
						"originalName", "OneInitializer" //$NON-NLS-1$ //$NON-NLS-2$
					});
		addAnnotation
		(getIterateExp__ValidateSafeIteratorIsRequired__DiagnosticChain_Map(),
			source,
			new String[]
					{
						"originalName", "SafeIteratorIsRequired" //$NON-NLS-1$ //$NON-NLS-2$
					});
		addAnnotation
		(getIterateExp__ValidateSafeSourceCanBeNull__DiagnosticChain_Map(),
			source,
			new String[]
					{
						"originalName", "SafeSourceCanBeNull" //$NON-NLS-1$ //$NON-NLS-2$
					});
		addAnnotation
		(getIterateExp__ValidateTypeIsResultType__DiagnosticChain_Map(),
			source,
			new String[]
					{
						"originalName", "TypeIsResultType" //$NON-NLS-1$ //$NON-NLS-2$
					});
		addAnnotation
		(getIterateExp__ValidateUnsafeSourceCanNotBeNull__DiagnosticChain_Map(),
			source,
			new String[]
					{
						"originalName", "UnsafeSourceCanNotBeNull" //$NON-NLS-1$ //$NON-NLS-2$
					});
		addAnnotation
		(getIteratorExp__ValidateAnyBodyTypeIsBoolean__DiagnosticChain_Map(),
			source,
			new String[]
					{
						"originalName", "AnyBodyTypeIsBoolean" //$NON-NLS-1$ //$NON-NLS-2$
					});
		addAnnotation
		(getIteratorExp__ValidateAnyHasOneIterator__DiagnosticChain_Map(),
			source,
			new String[]
					{
						"originalName", "AnyHasOneIterator" //$NON-NLS-1$ //$NON-NLS-2$
					});
		addAnnotation
		(getIteratorExp__ValidateAnyTypeIsSourceElementType__DiagnosticChain_Map(),
			source,
			new String[]
					{
						"originalName", "AnyTypeIsSourceElementType" //$NON-NLS-1$ //$NON-NLS-2$
					});
		addAnnotation
		(getIteratorExp__ValidateClosureBodyElementTypeIsIteratorType__DiagnosticChain_Map(),
			source,
			new String[]
					{
						"originalName", "ClosureBodyElementTypeIsIteratorType" //$NON-NLS-1$ //$NON-NLS-2$
					});
		addAnnotation
		(getIteratorExp__ValidateClosureBodyTypeIsConformanttoIteratorType__DiagnosticChain_Map(),
			source,
			new String[]
					{
						"originalName", "ClosureBodyTypeIsConformanttoIteratorType" //$NON-NLS-1$ //$NON-NLS-2$
					});
		addAnnotation
		(getIteratorExp__ValidateClosureElementTypeIsSourceElementType__DiagnosticChain_Map(),
			source,
			new String[]
					{
						"originalName", "ClosureElementTypeIsSourceElementType" //$NON-NLS-1$ //$NON-NLS-2$
					});
		addAnnotation
		(getIteratorExp__ValidateClosureHasOneIterator__DiagnosticChain_Map(),
			source,
			new String[]
					{
						"originalName", "ClosureHasOneIterator" //$NON-NLS-1$ //$NON-NLS-2$
					});
		addAnnotation
		(getIteratorExp__ValidateClosureSourceElementTypeIsBodyElementType__DiagnosticChain_Map(),
			source,
			new String[]
					{
						"originalName", "ClosureSourceElementTypeIsBodyElementType" //$NON-NLS-1$ //$NON-NLS-2$
					});
		addAnnotation
		(getIteratorExp__ValidateClosureTypeIsUniqueCollection__DiagnosticChain_Map(),
			source,
			new String[]
					{
						"originalName", "ClosureTypeIsUniqueCollection" //$NON-NLS-1$ //$NON-NLS-2$
					});
		addAnnotation
		(getIteratorExp__ValidateCollectElementTypeIsFlattenedBodyType__DiagnosticChain_Map(),
			source,
			new String[]
					{
						"originalName", "CollectElementTypeIsFlattenedBodyType" //$NON-NLS-1$ //$NON-NLS-2$
					});
		addAnnotation
		(getIteratorExp__ValidateCollectTypeIsUnordered__DiagnosticChain_Map(),
			source,
			new String[]
					{
						"originalName", "CollectTypeIsUnordered" //$NON-NLS-1$ //$NON-NLS-2$
					});
		addAnnotation
		(getIteratorExp__ValidateIteratorTypeIsSourceElementType__DiagnosticChain_Map(),
			source,
			new String[]
					{
						"originalName", "IteratorTypeIsSourceElementType" //$NON-NLS-1$ //$NON-NLS-2$
					});
		addAnnotation
		(getIteratorExp__ValidateSafeIteratorIsRequired__DiagnosticChain_Map(),
			source,
			new String[]
					{
						"originalName", "SafeIteratorIsRequired" //$NON-NLS-1$ //$NON-NLS-2$
					});
		addAnnotation
		(getIteratorExp__ValidateSafeSourceCanBeNull__DiagnosticChain_Map(),
			source,
			new String[]
					{
						"originalName", "SafeSourceCanBeNull" //$NON-NLS-1$ //$NON-NLS-2$
					});
		addAnnotation
		(getIteratorExp__ValidateSortedByElementTypeIsSourceElementType__DiagnosticChain_Map(),
			source,
			new String[]
					{
						"originalName", "SortedByElementTypeIsSourceElementType" //$NON-NLS-1$ //$NON-NLS-2$
					});
		addAnnotation
		(getIteratorExp__ValidateSortedByIsOrderedIfSourceIsOrdered__DiagnosticChain_Map(),
			source,
			new String[]
					{
						"originalName", "SortedByIsOrderedIfSourceIsOrdered" //$NON-NLS-1$ //$NON-NLS-2$
					});
		addAnnotation
		(getIteratorExp__ValidateSortedByIteratorTypeIsComparable__DiagnosticChain_Map(),
			source,
			new String[]
					{
						"originalName", "SortedByIteratorTypeIsComparable" //$NON-NLS-1$ //$NON-NLS-2$
					});
		addAnnotation
		(getIteratorExp__ValidateUnsafeSourceCanNotBeNull__DiagnosticChain_Map(),
			source,
			new String[]
					{
						"originalName", "UnsafeSourceCanNotBeNull" //$NON-NLS-1$ //$NON-NLS-2$
					});
		addAnnotation
		(getIteratorVariable__ValidateHasNoInitializer__DiagnosticChain_Map(),
			source,
			new String[]
					{
						"originalName", "HasNoInitializer" //$NON-NLS-1$ //$NON-NLS-2$
					});
		addAnnotation
		(getLetExp__ValidateCompatibleNullityForIn__DiagnosticChain_Map(),
			source,
			new String[]
					{
						"originalName", "CompatibleNullityForIn" //$NON-NLS-1$ //$NON-NLS-2$
					});
		addAnnotation
		(getLetExp__ValidateTypeIsInType__DiagnosticChain_Map(),
			source,
			new String[]
					{
						"originalName", "TypeIsInType" //$NON-NLS-1$ //$NON-NLS-2$
					});
		addAnnotation
		(getLetExp__ValidateTypeIsNotInvalid__DiagnosticChain_Map(),
			source,
			new String[]
					{
						"originalName", "TypeIsNotInvalid" //$NON-NLS-1$ //$NON-NLS-2$
					});
		addAnnotation
		(getLetVariable__ValidateCompatibleNullityForInitializer__DiagnosticChain_Map(),
			source,
			new String[]
					{
						"originalName", "CompatibleNullityForInitializer" //$NON-NLS-1$ //$NON-NLS-2$
					});
		addAnnotation
		(getLetVariable__ValidateCompatibleTypeForInitializer__DiagnosticChain_Map(),
			source,
			new String[]
					{
						"originalName", "CompatibleTypeForInitializer" //$NON-NLS-1$ //$NON-NLS-2$
					});
		addAnnotation
		(getLetVariable__ValidateHasInitializer__DiagnosticChain_Map(),
			source,
			new String[]
					{
						"originalName", "HasInitializer" //$NON-NLS-1$ //$NON-NLS-2$
					});
		addAnnotation
		(getLoopExp__ValidateNoInitializers__DiagnosticChain_Map(),
			source,
			new String[]
					{
						"originalName", "NoInitializers" //$NON-NLS-1$ //$NON-NLS-2$
					});
		addAnnotation
		(getLoopExp__ValidateSourceIsCollection__DiagnosticChain_Map(),
			source,
			new String[]
					{
						"originalName", "SourceIsCollection" //$NON-NLS-1$ //$NON-NLS-2$
					});
		addAnnotation
		(getMessageExp__ValidateOneCallOrOneSend__DiagnosticChain_Map(),
			source,
			new String[]
					{
						"originalName", "OneCallOrOneSend" //$NON-NLS-1$ //$NON-NLS-2$
					});
		addAnnotation
		(getMessageExp__ValidateTargetIsNotACollection__DiagnosticChain_Map(),
			source,
			new String[]
					{
						"originalName", "TargetIsNotACollection" //$NON-NLS-1$ //$NON-NLS-2$
					});
		addAnnotation
		(getOCLExpression__ValidateTypeIsNotNull__DiagnosticChain_Map(),
			source,
			new String[]
					{
						"originalName", "TypeIsNotNull" //$NON-NLS-1$ //$NON-NLS-2$
					});
		addAnnotation
		(getOperation__ValidateCompatibleReturn__DiagnosticChain_Map(),
			source,
			new String[]
					{
						"originalName", "CompatibleReturn" //$NON-NLS-1$ //$NON-NLS-2$
					});
		addAnnotation
		(getOperation__ValidateLoadableImplementation__DiagnosticChain_Map(),
			source,
			new String[]
					{
						"originalName", "LoadableImplementation" //$NON-NLS-1$ //$NON-NLS-2$
					});
		addAnnotation
		(getOperation__ValidateUniquePostconditionName__DiagnosticChain_Map(),
			source,
			new String[]
					{
						"originalName", "UniquePostconditionName" //$NON-NLS-1$ //$NON-NLS-2$
					});
		addAnnotation
		(getOperation__ValidateUniquePreconditionName__DiagnosticChain_Map(),
			source,
			new String[]
					{
						"originalName", "UniquePreconditionName" //$NON-NLS-1$ //$NON-NLS-2$
					});
		addAnnotation
		(getOperationCallExp__ValidateArgumentCount__DiagnosticChain_Map(),
			source,
			new String[]
					{
						"originalName", "ArgumentCount" //$NON-NLS-1$ //$NON-NLS-2$
					});
		addAnnotation
		(getOperationCallExp__ValidateArgumentTypeIsConformant__DiagnosticChain_Map(),
			source,
			new String[]
					{
						"originalName", "ArgumentTypeIsConformant" //$NON-NLS-1$ //$NON-NLS-2$
					});
		addAnnotation
		(getOperationCallExp__ValidateSafeSourceCanBeNull__DiagnosticChain_Map(),
			source,
			new String[]
					{
						"originalName", "SafeSourceCanBeNull" //$NON-NLS-1$ //$NON-NLS-2$
					});
		addAnnotation
		(getParameterVariable__ValidateHasNoInitializer__DiagnosticChain_Map(),
			source,
			new String[]
					{
						"originalName", "HasNoInitializer" //$NON-NLS-1$ //$NON-NLS-2$
					});
		addAnnotation
		(getProperty__ValidateCompatibleDefaultExpression__DiagnosticChain_Map(),
			source,
			new String[]
					{
						"originalName", "CompatibleDefaultExpression" //$NON-NLS-1$ //$NON-NLS-2$
					});
		addAnnotation
		(getPropertyCallExp__ValidateCompatibleResultType__DiagnosticChain_Map(),
			source,
			new String[]
					{
						"originalName", "CompatibleResultType" //$NON-NLS-1$ //$NON-NLS-2$
					});
		addAnnotation
		(getPropertyCallExp__ValidateNonStaticSourceTypeIsConformant__DiagnosticChain_Map(),
			source,
			new String[]
					{
						"originalName", "NonStaticSourceTypeIsConformant" //$NON-NLS-1$ //$NON-NLS-2$
					});
		addAnnotation
		(getPropertyCallExp__ValidateSafeSourceCanBeNull__DiagnosticChain_Map(),
			source,
			new String[]
					{
						"originalName", "SafeSourceCanBeNull" //$NON-NLS-1$ //$NON-NLS-2$
					});
		addAnnotation
		(getPropertyCallExp__ValidateUnsafeSourceCanNotBeNull__DiagnosticChain_Map(),
			source,
			new String[]
					{
						"originalName", "UnsafeSourceCanNotBeNull" //$NON-NLS-1$ //$NON-NLS-2$
					});
		addAnnotation
		(getResultVariable__ValidateCompatibleNullityForInitializer__DiagnosticChain_Map(),
			source,
			new String[]
					{
						"originalName", "CompatibleNullityForInitializer" //$NON-NLS-1$ //$NON-NLS-2$
					});
		addAnnotation
		(getResultVariable__ValidateCompatibleTypeForInitializer__DiagnosticChain_Map(),
			source,
			new String[]
					{
						"originalName", "CompatibleTypeForInitializer" //$NON-NLS-1$ //$NON-NLS-2$
					});
		addAnnotation
		(getResultVariable__ValidateHasInitializer__DiagnosticChain_Map(),
			source,
			new String[]
					{
						"originalName", "HasInitializer" //$NON-NLS-1$ //$NON-NLS-2$
					});
		addAnnotation
		(getShadowExp__ValidateClassHasNoStringValueInitializer__DiagnosticChain_Map(),
			source,
			new String[]
					{
						"originalName", "ClassHasNoStringValueInitializer" //$NON-NLS-1$ //$NON-NLS-2$
					});
		addAnnotation
		(getShadowExp__ValidateDataTypeHasNoPartInitializers__DiagnosticChain_Map(),
			source,
			new String[]
					{
						"originalName", "DataTypeHasNoPartInitializers" //$NON-NLS-1$ //$NON-NLS-2$
					});
		addAnnotation
		(getShadowExp__ValidateDataTypeHasOnePartInitializer__DiagnosticChain_Map(),
			source,
			new String[]
					{
						"originalName", "DataTypeHasOnePartInitializer" //$NON-NLS-1$ //$NON-NLS-2$
					});
		addAnnotation
		(getShadowExp__ValidateDataTypeHasStringValueInitializer__DiagnosticChain_Map(),
			source,
			new String[]
					{
						"originalName", "DataTypeHasStringValueInitializer" //$NON-NLS-1$ //$NON-NLS-2$
					});
		addAnnotation
		(getShadowExp__ValidateInitializesAllClassProperties__DiagnosticChain_Map(),
			source,
			new String[]
					{
						"originalName", "InitializesAllClassProperties" //$NON-NLS-1$ //$NON-NLS-2$
					});
		addAnnotation
		(getShadowExp__ValidateTypeIsNotInvalid__DiagnosticChain_Map(),
			source,
			new String[]
					{
						"originalName", "TypeIsNotInvalid" //$NON-NLS-1$ //$NON-NLS-2$
					});
		addAnnotation
		(getShadowPart__ValidateCompatibleInitialiserType__DiagnosticChain_Map(),
			source,
			new String[]
					{
						"originalName", "CompatibleInitialiserType" //$NON-NLS-1$ //$NON-NLS-2$
					});
		addAnnotation
		(getShadowPart__ValidateTypeIsNotInvalid__DiagnosticChain_Map(),
			source,
			new String[]
					{
						"originalName", "TypeIsNotInvalid" //$NON-NLS-1$ //$NON-NLS-2$
					});
		addAnnotation
		(getShadowPart__ValidateTypeIsNotNull__DiagnosticChain_Map(),
			source,
			new String[]
					{
						"originalName", "TypeIsNotNull" //$NON-NLS-1$ //$NON-NLS-2$
					});
		addAnnotation
		(getStateExp__ValidateTypeIsNotInvalid__DiagnosticChain_Map(),
			source,
			new String[]
					{
						"originalName", "TypeIsNotInvalid" //$NON-NLS-1$ //$NON-NLS-2$
					});
		addAnnotation
		(getTupleLiteralPart__ValidateCompatibleInitialiserType__DiagnosticChain_Map(),
			source,
			new String[]
					{
						"originalName", "CompatibleInitialiserType" //$NON-NLS-1$ //$NON-NLS-2$
					});
		addAnnotation
		(getTupleLiteralPart__ValidateTypeIsNotInvalid__DiagnosticChain_Map(),
			source,
			new String[]
					{
						"originalName", "TypeIsNotInvalid" //$NON-NLS-1$ //$NON-NLS-2$
					});
		addAnnotation
		(getVariable__ValidateCompatibleInitialiserType__DiagnosticChain_Map(),
			source,
			new String[]
					{
						"originalName", "CompatibleInitialiserType" //$NON-NLS-1$ //$NON-NLS-2$
					});
		addAnnotation
		(getVariableDeclaration__ValidateNameIsNotNull__DiagnosticChain_Map(),
			source,
			new String[]
					{
						"originalName", "NameIsNotNull" //$NON-NLS-1$ //$NON-NLS-2$
					});
		addAnnotation
		(getVariableDeclaration__ValidateTypeIsNotInvalid__DiagnosticChain_Map(),
			source,
			new String[]
					{
						"originalName", "TypeIsNotInvalid" //$NON-NLS-1$ //$NON-NLS-2$
					});
		addAnnotation
		(getVariableDeclaration__ValidateTypeIsNotNull__DiagnosticChain_Map(),
			source,
			new String[]
					{
						"originalName", "TypeIsNotNull" //$NON-NLS-1$ //$NON-NLS-2$
					});
		addAnnotation
		(getVariableExp__ValidateTypeIsNotInvalid__DiagnosticChain_Map(),
			source,
			new String[]
					{
						"originalName", "TypeIsNotInvalid" //$NON-NLS-1$ //$NON-NLS-2$
					});
	}
} //PivotPackageImpl
