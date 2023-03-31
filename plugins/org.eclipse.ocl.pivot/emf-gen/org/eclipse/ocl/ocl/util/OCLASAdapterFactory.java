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

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

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
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.ocl.ocl.OCLASPackage
 * @generated
 */
public class OCLASAdapterFactory extends AdapterFactoryImpl
{
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static OCLASPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OCLASAdapterFactory()
	{
		if (modelPackage == null)
		{
			modelPackage = OCLASPackage.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object object)
	{
		if (object == modelPackage)
		{
			return true;
		}
		if (object instanceof EObject)
		{
			return ((EObject)object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch that delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected OCLASSwitch<@Nullable Adapter> modelSwitch =
		new OCLASSwitch<@Nullable Adapter>()
		{
			@Override
			public Adapter caseAnnotation(Annotation object)
			{
				return createAnnotationAdapter();
			}
			@Override
			public Adapter caseAnyType(AnyType object)
			{
				return createAnyTypeAdapter();
			}
			@Override
			public Adapter caseAssociationClass(AssociationClass object)
			{
				return createAssociationClassAdapter();
			}
			@Override
			public Adapter caseAssociationClassCallExp(AssociationClassCallExp object)
			{
				return createAssociationClassCallExpAdapter();
			}
			@Override
			public <T> Adapter caseBag(Bag<T> object)
			{
				return createBagAdapter();
			}
			@Override
			public Adapter caseBagType(BagType object)
			{
				return createBagTypeAdapter();
			}
			@Override
			public Adapter caseBehavior(Behavior object)
			{
				return createBehaviorAdapter();
			}
			@Override
			public Adapter caseBooleanLiteralExp(BooleanLiteralExp object)
			{
				return createBooleanLiteralExpAdapter();
			}
			@Override
			public Adapter caseBooleanType(BooleanType object)
			{
				return createBooleanTypeAdapter();
			}
			@Override
			public Adapter caseCallExp(CallExp object)
			{
				return createCallExpAdapter();
			}
			@Override
			public Adapter caseCallOperationAction(CallOperationAction object)
			{
				return createCallOperationActionAdapter();
			}
			@Override
			public Adapter caseClass(org.eclipse.ocl.ocl.Class object)
			{
				return createClassAdapter();
			}
			@Override
			public <T> Adapter caseCollection(Collection<T> object)
			{
				return createCollectionAdapter();
			}
			@Override
			public Adapter caseCollectionItem(CollectionItem object)
			{
				return createCollectionItemAdapter();
			}
			@Override
			public Adapter caseCollectionLiteralExp(CollectionLiteralExp object)
			{
				return createCollectionLiteralExpAdapter();
			}
			@Override
			public Adapter caseCollectionLiteralPart(CollectionLiteralPart object)
			{
				return createCollectionLiteralPartAdapter();
			}
			@Override
			public Adapter caseCollectionRange(CollectionRange object)
			{
				return createCollectionRangeAdapter();
			}
			@Override
			public Adapter caseCollectionType(CollectionType object)
			{
				return createCollectionTypeAdapter();
			}
			@Override
			public Adapter caseComment(Comment object)
			{
				return createCommentAdapter();
			}
			@Override
			public Adapter caseCompleteClass(CompleteClass object)
			{
				return createCompleteClassAdapter();
			}
			@Override
			public Adapter caseCompleteEnvironment(CompleteEnvironment object)
			{
				return createCompleteEnvironmentAdapter();
			}
			@Override
			public Adapter caseCompleteModel(CompleteModel object)
			{
				return createCompleteModelAdapter();
			}
			@Override
			public Adapter caseCompletePackage(CompletePackage object)
			{
				return createCompletePackageAdapter();
			}
			@Override
			public Adapter caseConnectionPointReference(ConnectionPointReference object)
			{
				return createConnectionPointReferenceAdapter();
			}
			@Override
			public Adapter caseConstraint(Constraint object)
			{
				return createConstraintAdapter();
			}
			@Override
			public Adapter caseDataType(DataType object)
			{
				return createDataTypeAdapter();
			}
			@Override
			public Adapter caseDetail(Detail object)
			{
				return createDetailAdapter();
			}
			@Override
			public Adapter caseDynamicBehavior(DynamicBehavior object)
			{
				return createDynamicBehaviorAdapter();
			}
			@Override
			public Adapter caseDynamicElement(DynamicElement object)
			{
				return createDynamicElementAdapter();
			}
			@Override
			public Adapter caseDynamicProperty(DynamicProperty object)
			{
				return createDynamicPropertyAdapter();
			}
			@Override
			public Adapter caseDynamicType(DynamicType object)
			{
				return createDynamicTypeAdapter();
			}
			@Override
			public Adapter caseDynamicValueSpecification(DynamicValueSpecification object)
			{
				return createDynamicValueSpecificationAdapter();
			}
			@Override
			public Adapter caseElement(Element object)
			{
				return createElementAdapter();
			}
			@Override
			public Adapter caseElementExtension(ElementExtension object)
			{
				return createElementExtensionAdapter();
			}
			@Override
			public Adapter caseElementLiteralExp(ElementLiteralExp object)
			{
				return createElementLiteralExpAdapter();
			}
			@Override
			public Adapter caseEnumLiteralExp(EnumLiteralExp object)
			{
				return createEnumLiteralExpAdapter();
			}
			@Override
			public Adapter caseEnumeration(Enumeration object)
			{
				return createEnumerationAdapter();
			}
			@Override
			public Adapter caseEnumerationLiteral(EnumerationLiteral object)
			{
				return createEnumerationLiteralAdapter();
			}
			@Override
			public Adapter caseExpressionInOCL(ExpressionInOCL object)
			{
				return createExpressionInOCLAdapter();
			}
			@Override
			public Adapter caseFeature(Feature object)
			{
				return createFeatureAdapter();
			}
			@Override
			public Adapter caseFeatureCallExp(FeatureCallExp object)
			{
				return createFeatureCallExpAdapter();
			}
			@Override
			public Adapter caseFinalState(FinalState object)
			{
				return createFinalStateAdapter();
			}
			@Override
			public Adapter caseIfExp(IfExp object)
			{
				return createIfExpAdapter();
			}
			@Override
			public Adapter caseImport(Import object)
			{
				return createImportAdapter();
			}
			@Override
			public Adapter caseInstanceSpecification(InstanceSpecification object)
			{
				return createInstanceSpecificationAdapter();
			}
			@Override
			public Adapter caseIntegerLiteralExp(IntegerLiteralExp object)
			{
				return createIntegerLiteralExpAdapter();
			}
			@Override
			public Adapter caseInvalidLiteralExp(InvalidLiteralExp object)
			{
				return createInvalidLiteralExpAdapter();
			}
			@Override
			public Adapter caseInvalidType(InvalidType object)
			{
				return createInvalidTypeAdapter();
			}
			@Override
			public Adapter caseIterableType(IterableType object)
			{
				return createIterableTypeAdapter();
			}
			@Override
			public Adapter caseIterateExp(IterateExp object)
			{
				return createIterateExpAdapter();
			}
			@Override
			public Adapter caseIteration(Iteration object)
			{
				return createIterationAdapter();
			}
			@Override
			public Adapter caseIteratorExp(IteratorExp object)
			{
				return createIteratorExpAdapter();
			}
			@Override
			public Adapter caseIteratorVariable(IteratorVariable object)
			{
				return createIteratorVariableAdapter();
			}
			@Override
			public Adapter caseJavaType(JavaType object)
			{
				return createJavaTypeAdapter();
			}
			@Override
			public Adapter caseLambdaType(LambdaType object)
			{
				return createLambdaTypeAdapter();
			}
			@Override
			public Adapter caseLanguageExpression(LanguageExpression object)
			{
				return createLanguageExpressionAdapter();
			}
			@Override
			public Adapter caseLetExp(LetExp object)
			{
				return createLetExpAdapter();
			}
			@Override
			public Adapter caseLetVariable(LetVariable object)
			{
				return createLetVariableAdapter();
			}
			@Override
			public Adapter caseLibrary(Library object)
			{
				return createLibraryAdapter();
			}
			@Override
			public Adapter caseLiteralExp(LiteralExp object)
			{
				return createLiteralExpAdapter();
			}
			@Override
			public Adapter caseLoopExp(LoopExp object)
			{
				return createLoopExpAdapter();
			}
			@Override
			public <K, V> Adapter caseMap(Map<K, V> object)
			{
				return createMapAdapter();
			}
			@Override
			public Adapter caseMapLiteralExp(MapLiteralExp object)
			{
				return createMapLiteralExpAdapter();
			}
			@Override
			public Adapter caseMapLiteralPart(MapLiteralPart object)
			{
				return createMapLiteralPartAdapter();
			}
			@Override
			public Adapter caseMapType(MapType object)
			{
				return createMapTypeAdapter();
			}
			@Override
			public Adapter caseMessageExp(MessageExp object)
			{
				return createMessageExpAdapter();
			}
			@Override
			public Adapter caseMessageType(MessageType object)
			{
				return createMessageTypeAdapter();
			}
			@Override
			public Adapter caseModel(Model object)
			{
				return createModelAdapter();
			}
			@Override
			public Adapter caseMorePivotable(MorePivotable object)
			{
				return createMorePivotableAdapter();
			}
			@Override
			public Adapter caseNameable(Nameable object)
			{
				return createNameableAdapter();
			}
			@Override
			public Adapter caseNamedElement(NamedElement object)
			{
				return createNamedElementAdapter();
			}
			@Override
			public Adapter caseNamespace(Namespace object)
			{
				return createNamespaceAdapter();
			}
			@Override
			public Adapter caseNavigationCallExp(NavigationCallExp object)
			{
				return createNavigationCallExpAdapter();
			}
			@Override
			public Adapter caseNullLiteralExp(NullLiteralExp object)
			{
				return createNullLiteralExpAdapter();
			}
			@Override
			public Adapter caseNumericLiteralExp(NumericLiteralExp object)
			{
				return createNumericLiteralExpAdapter();
			}
			@Override
			public Adapter caseOCLExpression(OCLExpression object)
			{
				return createOCLExpressionAdapter();
			}
			@Override
			public Adapter caseOclAny(Object object)
			{
				return createOclAnyAdapter();
			}
			@Override
			public Adapter caseOclComparable(OclComparable object)
			{
				return createOclComparableAdapter();
			}
			@Override
			public Adapter caseOclElement(OclElement object)
			{
				return createOclElementAdapter();
			}
			@Override
			public Adapter caseOclEnumeration(OclEnumeration object)
			{
				return createOclEnumerationAdapter();
			}
			@Override
			public Adapter caseOclInvalid(org.eclipse.ocl.pivot.InvalidType object)
			{
				return createOclInvalidAdapter();
			}
			@Override
			public Adapter caseOclLambda(OclLambda object)
			{
				return createOclLambdaAdapter();
			}
			@Override
			public Adapter caseOclMessage(OclMessage object)
			{
				return createOclMessageAdapter();
			}
			@Override
			public Adapter caseOclSelf(SelfType object)
			{
				return createOclSelfAdapter();
			}
			@Override
			public Adapter caseOclState(OclState object)
			{
				return createOclStateAdapter();
			}
			@Override
			public Adapter caseOclStereotype(OclStereotype object)
			{
				return createOclStereotypeAdapter();
			}
			@Override
			public Adapter caseOclSummable(OclSummable object)
			{
				return createOclSummableAdapter();
			}
			@Override
			public Adapter caseOclTuple(OclTuple object)
			{
				return createOclTupleAdapter();
			}
			@Override
			public Adapter caseOclType(OclType object)
			{
				return createOclTypeAdapter();
			}
			@Override
			public Adapter caseOclVoid(VoidType object)
			{
				return createOclVoidAdapter();
			}
			@Override
			public Adapter caseOperation(Operation object)
			{
				return createOperationAdapter();
			}
			@Override
			public Adapter caseOperationCallExp(OperationCallExp object)
			{
				return createOperationCallExpAdapter();
			}
			@Override
			public Adapter caseOppositePropertyCallExp(OppositePropertyCallExp object)
			{
				return createOppositePropertyCallExpAdapter();
			}
			@Override
			public <T> Adapter caseOrderedCollection(Collection<T> object)
			{
				return createOrderedCollectionAdapter();
			}
			@Override
			public <T> Adapter caseOrderedSet(OrderedSet<T> object)
			{
				return createOrderedSetAdapter();
			}
			@Override
			public Adapter caseOrderedSetType(OrderedSetType object)
			{
				return createOrderedSetTypeAdapter();
			}
			@Override
			public Adapter caseOrphanCompletePackage(OrphanCompletePackage object)
			{
				return createOrphanCompletePackageAdapter();
			}
			@Override
			public Adapter casePackage(org.eclipse.ocl.ocl.Package object)
			{
				return createPackageAdapter();
			}
			@Override
			public Adapter caseParameter(Parameter object)
			{
				return createParameterAdapter();
			}
			@Override
			public Adapter caseParameterVariable(ParameterVariable object)
			{
				return createParameterVariableAdapter();
			}
			@Override
			public Adapter casePivotable(Pivotable object)
			{
				return createPivotableAdapter();
			}
			@Override
			public Adapter casePrecedence(Precedence object)
			{
				return createPrecedenceAdapter();
			}
			@Override
			public Adapter casePrimitiveCompletePackage(PrimitiveCompletePackage object)
			{
				return createPrimitiveCompletePackageAdapter();
			}
			@Override
			public Adapter casePrimitiveLiteralExp(PrimitiveLiteralExp object)
			{
				return createPrimitiveLiteralExpAdapter();
			}
			@Override
			public Adapter casePrimitiveType(PrimitiveType object)
			{
				return createPrimitiveTypeAdapter();
			}
			@Override
			public Adapter caseProfile(Profile object)
			{
				return createProfileAdapter();
			}
			@Override
			public Adapter caseProfileApplication(ProfileApplication object)
			{
				return createProfileApplicationAdapter();
			}
			@Override
			public Adapter caseProperty(Property object)
			{
				return createPropertyAdapter();
			}
			@Override
			public Adapter casePropertyCallExp(PropertyCallExp object)
			{
				return createPropertyCallExpAdapter();
			}
			@Override
			public Adapter casePseudostate(Pseudostate object)
			{
				return createPseudostateAdapter();
			}
			@Override
			public Adapter caseRealLiteralExp(RealLiteralExp object)
			{
				return createRealLiteralExpAdapter();
			}
			@Override
			public Adapter caseReferringElement(ReferringElement object)
			{
				return createReferringElementAdapter();
			}
			@Override
			public Adapter caseRegion(Region object)
			{
				return createRegionAdapter();
			}
			@Override
			public Adapter caseResultVariable(ResultVariable object)
			{
				return createResultVariableAdapter();
			}
			@Override
			public Adapter caseSelfType(org.eclipse.ocl.ocl.SelfType object)
			{
				return createSelfTypeAdapter();
			}
			@Override
			public Adapter caseSendSignalAction(SendSignalAction object)
			{
				return createSendSignalActionAdapter();
			}
			@Override
			public <T> Adapter caseSequence(List<T> object)
			{
				return createSequenceAdapter();
			}
			@Override
			public Adapter caseSequenceType(SequenceType object)
			{
				return createSequenceTypeAdapter();
			}
			@Override
			public <T> Adapter caseSet(Set<T> object)
			{
				return createSetAdapter();
			}
			@Override
			public Adapter caseSetType(SetType object)
			{
				return createSetTypeAdapter();
			}
			@Override
			public Adapter caseShadowExp(ShadowExp object)
			{
				return createShadowExpAdapter();
			}
			@Override
			public Adapter caseShadowPart(ShadowPart object)
			{
				return createShadowPartAdapter();
			}
			@Override
			public Adapter caseSignal(Signal object)
			{
				return createSignalAdapter();
			}
			@Override
			public Adapter caseSlot(Slot object)
			{
				return createSlotAdapter();
			}
			@Override
			public Adapter caseStandardLibrary(StandardLibrary object)
			{
				return createStandardLibraryAdapter();
			}
			@Override
			public Adapter caseState(State object)
			{
				return createStateAdapter();
			}
			@Override
			public Adapter caseStateExp(StateExp object)
			{
				return createStateExpAdapter();
			}
			@Override
			public Adapter caseStateMachine(StateMachine object)
			{
				return createStateMachineAdapter();
			}
			@Override
			public Adapter caseStereotype(Stereotype object)
			{
				return createStereotypeAdapter();
			}
			@Override
			public Adapter caseStereotypeExtender(StereotypeExtender object)
			{
				return createStereotypeExtenderAdapter();
			}
			@Override
			public Adapter caseStringLiteralExp(StringLiteralExp object)
			{
				return createStringLiteralExpAdapter();
			}
			@Override
			public Adapter caseTemplateBinding(TemplateBinding object)
			{
				return createTemplateBindingAdapter();
			}
			@Override
			public Adapter caseTemplateParameter(TemplateParameter object)
			{
				return createTemplateParameterAdapter();
			}
			@Override
			public Adapter caseTemplateParameterSubstitution(TemplateParameterSubstitution object)
			{
				return createTemplateParameterSubstitutionAdapter();
			}
			@Override
			public Adapter caseTemplateSignature(TemplateSignature object)
			{
				return createTemplateSignatureAdapter();
			}
			@Override
			public Adapter caseTemplateableElement(TemplateableElement object)
			{
				return createTemplateableElementAdapter();
			}
			@Override
			public Adapter caseTransition(Transition object)
			{
				return createTransitionAdapter();
			}
			@Override
			public Adapter caseTrigger(Trigger object)
			{
				return createTriggerAdapter();
			}
			@Override
			public Adapter caseTupleLiteralExp(TupleLiteralExp object)
			{
				return createTupleLiteralExpAdapter();
			}
			@Override
			public Adapter caseTupleLiteralPart(TupleLiteralPart object)
			{
				return createTupleLiteralPartAdapter();
			}
			@Override
			public Adapter caseTupleType(TupleType object)
			{
				return createTupleTypeAdapter();
			}
			@Override
			public Adapter caseType(Type object)
			{
				return createTypeAdapter();
			}
			@Override
			public Adapter caseTypeExp(TypeExp object)
			{
				return createTypeExpAdapter();
			}
			@Override
			public Adapter caseTypedElement(TypedElement object)
			{
				return createTypedElementAdapter();
			}
			@Override
			public <T> Adapter caseUniqueCollection(Collection<T> object)
			{
				return createUniqueCollectionAdapter();
			}
			@Override
			public Adapter caseUnlimitedNaturalLiteralExp(UnlimitedNaturalLiteralExp object)
			{
				return createUnlimitedNaturalLiteralExpAdapter();
			}
			@Override
			public Adapter caseUnspecifiedValueExp(UnspecifiedValueExp object)
			{
				return createUnspecifiedValueExpAdapter();
			}
			@Override
			public Adapter caseValueSpecification(ValueSpecification object)
			{
				return createValueSpecificationAdapter();
			}
			@Override
			public Adapter caseVariable(Variable object)
			{
				return createVariableAdapter();
			}
			@Override
			public Adapter caseVariableDeclaration(VariableDeclaration object)
			{
				return createVariableDeclarationAdapter();
			}
			@Override
			public Adapter caseVariableExp(VariableExp object)
			{
				return createVariableExpAdapter();
			}
			@Override
			public Adapter caseVertex(Vertex object)
			{
				return createVertexAdapter();
			}
			@Override
			public Adapter caseVisitable(Visitable object)
			{
				return createVisitableAdapter();
			}
			@Override
			public Adapter caseVoidType(org.eclipse.ocl.ocl.VoidType object)
			{
				return createVoidTypeAdapter();
			}
			@Override
			public Adapter caseWildcardType(WildcardType object)
			{
				return createWildcardTypeAdapter();
			}
			@Override
			public Adapter defaultCase(EObject object)
			{
				return createEObjectAdapter();
			}
		};

	/**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	@Override
	public Adapter createAdapter(Notifier target)
	{
		return modelSwitch.doSwitch((EObject)target);
	}


	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.ocl.Annotation <em>Annotation</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.ocl.Annotation
	 * @generated
	 */
	public Adapter createAnnotationAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.ocl.AnyType <em>Any Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.ocl.AnyType
	 * @generated
	 */
	public Adapter createAnyTypeAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.ocl.AssociationClass <em>Association Class</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.ocl.AssociationClass
	 * @generated
	 */
	public Adapter createAssociationClassAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.ocl.AssociationClassCallExp <em>Association Class Call Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.ocl.AssociationClassCallExp
	 * @generated
	 */
	public Adapter createAssociationClassCallExpAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.pivot.values.Bag <em>Bag</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.pivot.values.Bag
	 * @generated
	 */
	public Adapter createBagAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.ocl.BagType <em>Bag Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.ocl.BagType
	 * @generated
	 */
	public Adapter createBagTypeAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.ocl.Behavior <em>Behavior</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.ocl.Behavior
	 * @generated
	 */
	public Adapter createBehaviorAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.ocl.BooleanLiteralExp <em>Boolean Literal Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.ocl.BooleanLiteralExp
	 * @generated
	 */
	public Adapter createBooleanLiteralExpAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.ocl.BooleanType <em>Boolean Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.ocl.BooleanType
	 * @generated
	 */
	public Adapter createBooleanTypeAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.ocl.CallExp <em>Call Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.ocl.CallExp
	 * @generated
	 */
	public Adapter createCallExpAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.ocl.CallOperationAction <em>Call Operation Action</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.ocl.CallOperationAction
	 * @generated
	 */
	public Adapter createCallOperationActionAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.ocl.Class <em>Class</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.ocl.Class
	 * @generated
	 */
	public Adapter createClassAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link java.util.Collection <em>Collection</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see java.util.Collection
	 * @generated
	 */
	public Adapter createCollectionAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.ocl.CollectionItem <em>Collection Item</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.ocl.CollectionItem
	 * @generated
	 */
	public Adapter createCollectionItemAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.ocl.CollectionLiteralExp <em>Collection Literal Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.ocl.CollectionLiteralExp
	 * @generated
	 */
	public Adapter createCollectionLiteralExpAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.ocl.CollectionLiteralPart <em>Collection Literal Part</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.ocl.CollectionLiteralPart
	 * @generated
	 */
	public Adapter createCollectionLiteralPartAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.ocl.CollectionRange <em>Collection Range</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.ocl.CollectionRange
	 * @generated
	 */
	public Adapter createCollectionRangeAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.ocl.CollectionType <em>Collection Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.ocl.CollectionType
	 * @generated
	 */
	public Adapter createCollectionTypeAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.ocl.Comment <em>Comment</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.ocl.Comment
	 * @generated
	 */
	public Adapter createCommentAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.ocl.CompleteClass <em>Complete Class</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.ocl.CompleteClass
	 * @generated
	 */
	public Adapter createCompleteClassAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.ocl.CompleteEnvironment <em>Complete Environment</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.ocl.CompleteEnvironment
	 * @generated
	 */
	public Adapter createCompleteEnvironmentAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.ocl.CompleteModel <em>Complete Model</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.ocl.CompleteModel
	 * @generated
	 */
	public Adapter createCompleteModelAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.ocl.CompletePackage <em>Complete Package</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.ocl.CompletePackage
	 * @generated
	 */
	public Adapter createCompletePackageAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.ocl.ConnectionPointReference <em>Connection Point Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.ocl.ConnectionPointReference
	 * @generated
	 */
	public Adapter createConnectionPointReferenceAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.ocl.Constraint <em>Constraint</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.ocl.Constraint
	 * @generated
	 */
	public Adapter createConstraintAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.ocl.DataType <em>Data Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.ocl.DataType
	 * @generated
	 */
	public Adapter createDataTypeAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.ocl.Detail <em>Detail</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.ocl.Detail
	 * @generated
	 */
	public Adapter createDetailAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.ocl.DynamicBehavior <em>Dynamic Behavior</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.ocl.DynamicBehavior
	 * @generated
	 */
	public Adapter createDynamicBehaviorAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.ocl.DynamicElement <em>Dynamic Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.ocl.DynamicElement
	 * @generated
	 */
	public Adapter createDynamicElementAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.ocl.DynamicProperty <em>Dynamic Property</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.ocl.DynamicProperty
	 * @generated
	 */
	public Adapter createDynamicPropertyAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.ocl.DynamicType <em>Dynamic Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.ocl.DynamicType
	 * @generated
	 */
	public Adapter createDynamicTypeAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.ocl.DynamicValueSpecification <em>Dynamic Value Specification</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.ocl.DynamicValueSpecification
	 * @generated
	 */
	public Adapter createDynamicValueSpecificationAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.ocl.Element <em>Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.ocl.Element
	 * @generated
	 */
	public Adapter createElementAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.ocl.ElementExtension <em>Element Extension</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.ocl.ElementExtension
	 * @generated
	 */
	public Adapter createElementExtensionAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.ocl.ElementLiteralExp <em>Element Literal Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.ocl.ElementLiteralExp
	 * @generated
	 */
	public Adapter createElementLiteralExpAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.ocl.EnumLiteralExp <em>Enum Literal Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.ocl.EnumLiteralExp
	 * @generated
	 */
	public Adapter createEnumLiteralExpAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.ocl.Enumeration <em>Enumeration</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.ocl.Enumeration
	 * @generated
	 */
	public Adapter createEnumerationAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.ocl.EnumerationLiteral <em>Enumeration Literal</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.ocl.EnumerationLiteral
	 * @generated
	 */
	public Adapter createEnumerationLiteralAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.ocl.ExpressionInOCL <em>Expression In OCL</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.ocl.ExpressionInOCL
	 * @generated
	 */
	public Adapter createExpressionInOCLAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.ocl.Feature <em>Feature</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.ocl.Feature
	 * @generated
	 */
	public Adapter createFeatureAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.ocl.FeatureCallExp <em>Feature Call Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.ocl.FeatureCallExp
	 * @generated
	 */
	public Adapter createFeatureCallExpAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.ocl.FinalState <em>Final State</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.ocl.FinalState
	 * @generated
	 */
	public Adapter createFinalStateAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.ocl.IfExp <em>If Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.ocl.IfExp
	 * @generated
	 */
	public Adapter createIfExpAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.ocl.Import <em>Import</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.ocl.Import
	 * @generated
	 */
	public Adapter createImportAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.ocl.InstanceSpecification <em>Instance Specification</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.ocl.InstanceSpecification
	 * @generated
	 */
	public Adapter createInstanceSpecificationAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.ocl.IntegerLiteralExp <em>Integer Literal Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.ocl.IntegerLiteralExp
	 * @generated
	 */
	public Adapter createIntegerLiteralExpAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.ocl.InvalidLiteralExp <em>Invalid Literal Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.ocl.InvalidLiteralExp
	 * @generated
	 */
	public Adapter createInvalidLiteralExpAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.ocl.InvalidType <em>Invalid Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.ocl.InvalidType
	 * @generated
	 */
	public Adapter createInvalidTypeAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.ocl.IterableType <em>Iterable Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.ocl.IterableType
	 * @generated
	 */
	public Adapter createIterableTypeAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.ocl.IterateExp <em>Iterate Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.ocl.IterateExp
	 * @generated
	 */
	public Adapter createIterateExpAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.ocl.Iteration <em>Iteration</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.ocl.Iteration
	 * @generated
	 */
	public Adapter createIterationAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.ocl.IteratorExp <em>Iterator Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.ocl.IteratorExp
	 * @generated
	 */
	public Adapter createIteratorExpAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.ocl.IteratorVariable <em>Iterator Variable</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.ocl.IteratorVariable
	 * @generated
	 */
	public Adapter createIteratorVariableAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.ocl.JavaType <em>Java Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.ocl.JavaType
	 * @generated
	 */
	public Adapter createJavaTypeAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.ocl.LambdaType <em>Lambda Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.ocl.LambdaType
	 * @generated
	 */
	public Adapter createLambdaTypeAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.ocl.LanguageExpression <em>Language Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.ocl.LanguageExpression
	 * @generated
	 */
	public Adapter createLanguageExpressionAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.ocl.LetExp <em>Let Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.ocl.LetExp
	 * @generated
	 */
	public Adapter createLetExpAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.ocl.LetVariable <em>Let Variable</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.ocl.LetVariable
	 * @generated
	 */
	public Adapter createLetVariableAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.ocl.Library <em>Library</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.ocl.Library
	 * @generated
	 */
	public Adapter createLibraryAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.ocl.LiteralExp <em>Literal Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.ocl.LiteralExp
	 * @generated
	 */
	public Adapter createLiteralExpAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.ocl.LoopExp <em>Loop Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.ocl.LoopExp
	 * @generated
	 */
	public Adapter createLoopExpAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link java.util.Map <em>Map</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see java.util.Map
	 * @generated
	 */
	public Adapter createMapAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.ocl.MapLiteralExp <em>Map Literal Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.ocl.MapLiteralExp
	 * @generated
	 */
	public Adapter createMapLiteralExpAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.ocl.MapLiteralPart <em>Map Literal Part</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.ocl.MapLiteralPart
	 * @generated
	 */
	public Adapter createMapLiteralPartAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.ocl.MapType <em>Map Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.ocl.MapType
	 * @generated
	 */
	public Adapter createMapTypeAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.ocl.MessageExp <em>Message Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.ocl.MessageExp
	 * @generated
	 */
	public Adapter createMessageExpAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.ocl.MessageType <em>Message Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.ocl.MessageType
	 * @generated
	 */
	public Adapter createMessageTypeAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.ocl.Model <em>Model</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.ocl.Model
	 * @generated
	 */
	public Adapter createModelAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.pivot.utilities.MorePivotable <em>More Pivotable</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.pivot.utilities.MorePivotable
	 * @generated
	 */
	public Adapter createMorePivotableAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.pivot.utilities.Nameable <em>Nameable</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.pivot.utilities.Nameable
	 * @generated
	 */
	public Adapter createNameableAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.ocl.NamedElement <em>Named Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.ocl.NamedElement
	 * @generated
	 */
	public Adapter createNamedElementAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.ocl.Namespace <em>Namespace</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.ocl.Namespace
	 * @generated
	 */
	public Adapter createNamespaceAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.ocl.NavigationCallExp <em>Navigation Call Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.ocl.NavigationCallExp
	 * @generated
	 */
	public Adapter createNavigationCallExpAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.ocl.NullLiteralExp <em>Null Literal Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.ocl.NullLiteralExp
	 * @generated
	 */
	public Adapter createNullLiteralExpAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.ocl.NumericLiteralExp <em>Numeric Literal Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.ocl.NumericLiteralExp
	 * @generated
	 */
	public Adapter createNumericLiteralExpAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.ocl.OCLExpression <em>OCL Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.ocl.OCLExpression
	 * @generated
	 */
	public Adapter createOCLExpressionAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link java.lang.Object <em>Ocl Any</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see java.lang.Object
	 * @generated
	 */
	public Adapter createOclAnyAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.ocl.OclComparable <em>Ocl Comparable</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.ocl.OclComparable
	 * @generated
	 */
	public Adapter createOclComparableAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.ocl.OclElement <em>Ocl Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.ocl.OclElement
	 * @generated
	 */
	public Adapter createOclElementAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.ocl.OclEnumeration <em>Ocl Enumeration</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.ocl.OclEnumeration
	 * @generated
	 */
	public Adapter createOclEnumerationAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.pivot.InvalidType <em>Ocl Invalid</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.pivot.InvalidType
	 * @generated
	 */
	public Adapter createOclInvalidAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.ocl.OclLambda <em>Ocl Lambda</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.ocl.OclLambda
	 * @generated
	 */
	public Adapter createOclLambdaAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.ocl.OclMessage <em>Ocl Message</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.ocl.OclMessage
	 * @generated
	 */
	public Adapter createOclMessageAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.pivot.SelfType <em>Ocl Self</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.pivot.SelfType
	 * @generated
	 */
	public Adapter createOclSelfAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.ocl.OclState <em>Ocl State</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.ocl.OclState
	 * @generated
	 */
	public Adapter createOclStateAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.ocl.OclStereotype <em>Ocl Stereotype</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.ocl.OclStereotype
	 * @generated
	 */
	public Adapter createOclStereotypeAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.ocl.OclSummable <em>Ocl Summable</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.ocl.OclSummable
	 * @generated
	 */
	public Adapter createOclSummableAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.ocl.OclTuple <em>Ocl Tuple</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.ocl.OclTuple
	 * @generated
	 */
	public Adapter createOclTupleAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.ocl.OclType <em>Ocl Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.ocl.OclType
	 * @generated
	 */
	public Adapter createOclTypeAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.pivot.VoidType <em>Ocl Void</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.pivot.VoidType
	 * @generated
	 */
	public Adapter createOclVoidAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.ocl.Operation <em>Operation</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.ocl.Operation
	 * @generated
	 */
	public Adapter createOperationAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.ocl.OperationCallExp <em>Operation Call Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.ocl.OperationCallExp
	 * @generated
	 */
	public Adapter createOperationCallExpAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.ocl.OppositePropertyCallExp <em>Opposite Property Call Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.ocl.OppositePropertyCallExp
	 * @generated
	 */
	public Adapter createOppositePropertyCallExpAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link java.util.Collection <em>Ordered Collection</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see java.util.Collection
	 * @generated
	 */
	public Adapter createOrderedCollectionAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.pivot.values.OrderedSet <em>Ordered Set</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.pivot.values.OrderedSet
	 * @generated
	 */
	public Adapter createOrderedSetAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.ocl.OrderedSetType <em>Ordered Set Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.ocl.OrderedSetType
	 * @generated
	 */
	public Adapter createOrderedSetTypeAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.ocl.OrphanCompletePackage <em>Orphan Complete Package</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.ocl.OrphanCompletePackage
	 * @generated
	 */
	public Adapter createOrphanCompletePackageAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.ocl.Package <em>Package</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.ocl.Package
	 * @generated
	 */
	public Adapter createPackageAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.ocl.Parameter <em>Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.ocl.Parameter
	 * @generated
	 */
	public Adapter createParameterAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.ocl.ParameterVariable <em>Parameter Variable</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.ocl.ParameterVariable
	 * @generated
	 */
	public Adapter createParameterVariableAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.pivot.utilities.Pivotable <em>Pivotable</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.pivot.utilities.Pivotable
	 * @generated
	 */
	public Adapter createPivotableAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.ocl.Precedence <em>Precedence</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.ocl.Precedence
	 * @generated
	 */
	public Adapter createPrecedenceAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.ocl.PrimitiveCompletePackage <em>Primitive Complete Package</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.ocl.PrimitiveCompletePackage
	 * @generated
	 */
	public Adapter createPrimitiveCompletePackageAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.ocl.PrimitiveLiteralExp <em>Primitive Literal Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.ocl.PrimitiveLiteralExp
	 * @generated
	 */
	public Adapter createPrimitiveLiteralExpAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.ocl.PrimitiveType <em>Primitive Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.ocl.PrimitiveType
	 * @generated
	 */
	public Adapter createPrimitiveTypeAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.ocl.Profile <em>Profile</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.ocl.Profile
	 * @generated
	 */
	public Adapter createProfileAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.ocl.ProfileApplication <em>Profile Application</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.ocl.ProfileApplication
	 * @generated
	 */
	public Adapter createProfileApplicationAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.ocl.Property <em>Property</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.ocl.Property
	 * @generated
	 */
	public Adapter createPropertyAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.ocl.PropertyCallExp <em>Property Call Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.ocl.PropertyCallExp
	 * @generated
	 */
	public Adapter createPropertyCallExpAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.ocl.Pseudostate <em>Pseudostate</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.ocl.Pseudostate
	 * @generated
	 */
	public Adapter createPseudostateAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.ocl.RealLiteralExp <em>Real Literal Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.ocl.RealLiteralExp
	 * @generated
	 */
	public Adapter createRealLiteralExpAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.ocl.ReferringElement <em>Referring Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.ocl.ReferringElement
	 * @generated
	 */
	public Adapter createReferringElementAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.ocl.Region <em>Region</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.ocl.Region
	 * @generated
	 */
	public Adapter createRegionAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.ocl.ResultVariable <em>Result Variable</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.ocl.ResultVariable
	 * @generated
	 */
	public Adapter createResultVariableAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.ocl.SelfType <em>Self Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.ocl.SelfType
	 * @generated
	 */
	public Adapter createSelfTypeAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.ocl.SendSignalAction <em>Send Signal Action</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.ocl.SendSignalAction
	 * @generated
	 */
	public Adapter createSendSignalActionAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link java.util.List <em>Sequence</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see java.util.List
	 * @generated
	 */
	public Adapter createSequenceAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.ocl.SequenceType <em>Sequence Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.ocl.SequenceType
	 * @generated
	 */
	public Adapter createSequenceTypeAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link java.util.Set <em>Set</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see java.util.Set
	 * @generated
	 */
	public Adapter createSetAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.ocl.SetType <em>Set Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.ocl.SetType
	 * @generated
	 */
	public Adapter createSetTypeAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.ocl.ShadowExp <em>Shadow Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.ocl.ShadowExp
	 * @generated
	 */
	public Adapter createShadowExpAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.ocl.ShadowPart <em>Shadow Part</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.ocl.ShadowPart
	 * @generated
	 */
	public Adapter createShadowPartAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.ocl.Signal <em>Signal</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.ocl.Signal
	 * @generated
	 */
	public Adapter createSignalAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.ocl.Slot <em>Slot</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.ocl.Slot
	 * @generated
	 */
	public Adapter createSlotAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.ocl.StandardLibrary <em>Standard Library</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.ocl.StandardLibrary
	 * @generated
	 */
	public Adapter createStandardLibraryAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.ocl.State <em>State</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.ocl.State
	 * @generated
	 */
	public Adapter createStateAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.ocl.StateExp <em>State Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.ocl.StateExp
	 * @generated
	 */
	public Adapter createStateExpAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.ocl.StateMachine <em>State Machine</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.ocl.StateMachine
	 * @generated
	 */
	public Adapter createStateMachineAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.ocl.Stereotype <em>Stereotype</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.ocl.Stereotype
	 * @generated
	 */
	public Adapter createStereotypeAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.ocl.StereotypeExtender <em>Stereotype Extender</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.ocl.StereotypeExtender
	 * @generated
	 */
	public Adapter createStereotypeExtenderAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.ocl.StringLiteralExp <em>String Literal Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.ocl.StringLiteralExp
	 * @generated
	 */
	public Adapter createStringLiteralExpAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.ocl.TemplateBinding <em>Template Binding</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.ocl.TemplateBinding
	 * @generated
	 */
	public Adapter createTemplateBindingAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.ocl.TemplateParameter <em>Template Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.ocl.TemplateParameter
	 * @generated
	 */
	public Adapter createTemplateParameterAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.ocl.TemplateParameterSubstitution <em>Template Parameter Substitution</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.ocl.TemplateParameterSubstitution
	 * @generated
	 */
	public Adapter createTemplateParameterSubstitutionAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.ocl.TemplateSignature <em>Template Signature</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.ocl.TemplateSignature
	 * @generated
	 */
	public Adapter createTemplateSignatureAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.ocl.TemplateableElement <em>Templateable Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.ocl.TemplateableElement
	 * @generated
	 */
	public Adapter createTemplateableElementAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.ocl.Transition <em>Transition</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.ocl.Transition
	 * @generated
	 */
	public Adapter createTransitionAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.ocl.Trigger <em>Trigger</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.ocl.Trigger
	 * @generated
	 */
	public Adapter createTriggerAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.ocl.TupleLiteralExp <em>Tuple Literal Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.ocl.TupleLiteralExp
	 * @generated
	 */
	public Adapter createTupleLiteralExpAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.ocl.TupleLiteralPart <em>Tuple Literal Part</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.ocl.TupleLiteralPart
	 * @generated
	 */
	public Adapter createTupleLiteralPartAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.ocl.TupleType <em>Tuple Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.ocl.TupleType
	 * @generated
	 */
	public Adapter createTupleTypeAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.ocl.Type <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.ocl.Type
	 * @generated
	 */
	public Adapter createTypeAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.ocl.TypeExp <em>Type Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.ocl.TypeExp
	 * @generated
	 */
	public Adapter createTypeExpAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.ocl.TypedElement <em>Typed Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.ocl.TypedElement
	 * @generated
	 */
	public Adapter createTypedElementAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link java.util.Collection <em>Unique Collection</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see java.util.Collection
	 * @generated
	 */
	public Adapter createUniqueCollectionAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.ocl.UnlimitedNaturalLiteralExp <em>Unlimited Natural Literal Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.ocl.UnlimitedNaturalLiteralExp
	 * @generated
	 */
	public Adapter createUnlimitedNaturalLiteralExpAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.ocl.UnspecifiedValueExp <em>Unspecified Value Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.ocl.UnspecifiedValueExp
	 * @generated
	 */
	public Adapter createUnspecifiedValueExpAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.ocl.ValueSpecification <em>Value Specification</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.ocl.ValueSpecification
	 * @generated
	 */
	public Adapter createValueSpecificationAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.ocl.Variable <em>Variable</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.ocl.Variable
	 * @generated
	 */
	public Adapter createVariableAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.ocl.VariableDeclaration <em>Variable Declaration</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.ocl.VariableDeclaration
	 * @generated
	 */
	public Adapter createVariableDeclarationAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.ocl.VariableExp <em>Variable Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.ocl.VariableExp
	 * @generated
	 */
	public Adapter createVariableExpAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.ocl.Vertex <em>Vertex</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.ocl.Vertex
	 * @generated
	 */
	public Adapter createVertexAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.pivot.util.Visitable <em>Visitable</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.pivot.util.Visitable
	 * @generated
	 */
	public Adapter createVisitableAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.ocl.VoidType <em>Void Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.ocl.VoidType
	 * @generated
	 */
	public Adapter createVoidTypeAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.ocl.WildcardType <em>Wildcard Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.ocl.WildcardType
	 * @generated
	 */
	public Adapter createWildcardTypeAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter()
	{
		return null;
	}

} //OCLASAdapterFactory
