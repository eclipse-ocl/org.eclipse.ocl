/*******************************************************************************
 * Copyright (c) 2010, 2015 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *
 * This code is auto-generated
 * from: org.eclipse.ocl.pivot/model/Pivot.genmodel
 *
 * Only the copyright statement is editable.
 *******************************************************************************/
package	org.eclipse.ocl.pivot.util;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;

/**
 * An AbstractDelegatingVisitor delegates all visits.
 */
public abstract class AbstractDelegatingVisitor<R, C, D extends Visitor<R>>
	extends AbstractVisitor<R, C>
	implements Visitor<R>
{
	protected final D delegate;
	
	protected AbstractDelegatingVisitor(@NonNull D delegate, @NonNull C context) {
		super(context);
	//	assert delegate != null : "cannot decorate a null visitor"; //$NON-NLS-1$
		this.delegate = delegate;		
	//	delegate.setUndecoratedVisitor(this);
	}

	/**
	 * Delegates to my decorated visitor.
	 */
	//	public @NonNull DecorableVisitor<R> createNestedVisitor() {
	//		return delegate.createNestedVisitor();
	//	}

	/**
	 * Obtains the visitor that I decorate.
	 * 
	 * @return my decorated visitor
	 */
	@SuppressWarnings("null")
	protected final @NonNull D getDelegate() {
		return delegate;
	}

	@Override
	public @Nullable R visiting(org.eclipse.ocl.pivot.util.@NonNull Visitable visitable) {
		return delegate.visiting(visitable);
	}

	@Override
	public @Nullable R visitAnnotation(org.eclipse.ocl.pivot.@NonNull Annotation object) {
		return delegate.visitAnnotation(object);
	}

	@Override
	public @Nullable R visitAnyType(org.eclipse.ocl.pivot.@NonNull AnyType object) {
		return delegate.visitAnyType(object);
	}

	@Override
	public @Nullable R visitAssociationClass(org.eclipse.ocl.pivot.@NonNull AssociationClass object) {
		return delegate.visitAssociationClass(object);
	}

	@Override
	public @Nullable R visitAssociationClassCallExp(org.eclipse.ocl.pivot.@NonNull AssociationClassCallExp object) {
		return delegate.visitAssociationClassCallExp(object);
	}

	@Override
	public @Nullable R visitBagType(org.eclipse.ocl.pivot.@NonNull BagType object) {
		return delegate.visitBagType(object);
	}

	@Override
	public @Nullable R visitBehavior(org.eclipse.ocl.pivot.@NonNull Behavior object) {
		return delegate.visitBehavior(object);
	}

	@Override
	public @Nullable R visitBooleanLiteralExp(org.eclipse.ocl.pivot.@NonNull BooleanLiteralExp object) {
		return delegate.visitBooleanLiteralExp(object);
	}

	@Override
	public @Nullable R visitCallExp(org.eclipse.ocl.pivot.@NonNull CallExp object) {
		return delegate.visitCallExp(object);
	}

	@Override
	public @Nullable R visitCallOperationAction(org.eclipse.ocl.pivot.@NonNull CallOperationAction object) {
		return delegate.visitCallOperationAction(object);
	}

	@Override
	public @Nullable R visitClass(org.eclipse.ocl.pivot.@NonNull Class object) {
		return delegate.visitClass(object);
	}

	@Override
	public @Nullable R visitCollectionItem(org.eclipse.ocl.pivot.@NonNull CollectionItem object) {
		return delegate.visitCollectionItem(object);
	}

	@Override
	public @Nullable R visitCollectionLiteralExp(org.eclipse.ocl.pivot.@NonNull CollectionLiteralExp object) {
		return delegate.visitCollectionLiteralExp(object);
	}

	@Override
	public @Nullable R visitCollectionLiteralPart(org.eclipse.ocl.pivot.@NonNull CollectionLiteralPart object) {
		return delegate.visitCollectionLiteralPart(object);
	}

	@Override
	public @Nullable R visitCollectionRange(org.eclipse.ocl.pivot.@NonNull CollectionRange object) {
		return delegate.visitCollectionRange(object);
	}

	@Override
	public @Nullable R visitCollectionType(org.eclipse.ocl.pivot.@NonNull CollectionType object) {
		return delegate.visitCollectionType(object);
	}

	@Override
	public @Nullable R visitComment(org.eclipse.ocl.pivot.@NonNull Comment object) {
		return delegate.visitComment(object);
	}

	@Override
	public @Nullable R visitCompleteClass(org.eclipse.ocl.pivot.@NonNull CompleteClass object) {
		return delegate.visitCompleteClass(object);
	}

	@Override
	public @Nullable R visitCompleteEnvironment(org.eclipse.ocl.pivot.@NonNull CompleteEnvironment object) {
		return delegate.visitCompleteEnvironment(object);
	}

	@Override
	public @Nullable R visitCompleteModel(org.eclipse.ocl.pivot.@NonNull CompleteModel object) {
		return delegate.visitCompleteModel(object);
	}

	@Override
	public @Nullable R visitCompletePackage(org.eclipse.ocl.pivot.@NonNull CompletePackage object) {
		return delegate.visitCompletePackage(object);
	}

	@Override
	public @Nullable R visitConnectionPointReference(org.eclipse.ocl.pivot.@NonNull ConnectionPointReference object) {
		return delegate.visitConnectionPointReference(object);
	}

	@Override
	public @Nullable R visitConstraint(org.eclipse.ocl.pivot.@NonNull Constraint object) {
		return delegate.visitConstraint(object);
	}

	@Override
	public @Nullable R visitDataType(org.eclipse.ocl.pivot.@NonNull DataType object) {
		return delegate.visitDataType(object);
	}

	@Override
	public @Nullable R visitDetail(org.eclipse.ocl.pivot.@NonNull Detail object) {
		return delegate.visitDetail(object);
	}

	@Override
	public @Nullable R visitDynamicBehavior(org.eclipse.ocl.pivot.@NonNull DynamicBehavior object) {
		return delegate.visitDynamicBehavior(object);
	}

	@Override
	public @Nullable R visitDynamicElement(org.eclipse.ocl.pivot.@NonNull DynamicElement object) {
		return delegate.visitDynamicElement(object);
	}

	@Override
	public @Nullable R visitDynamicProperty(org.eclipse.ocl.pivot.@NonNull DynamicProperty object) {
		return delegate.visitDynamicProperty(object);
	}

	@Override
	public @Nullable R visitDynamicType(org.eclipse.ocl.pivot.@NonNull DynamicType object) {
		return delegate.visitDynamicType(object);
	}

	@Override
	public @Nullable R visitDynamicValueSpecification(org.eclipse.ocl.pivot.@NonNull DynamicValueSpecification object) {
		return delegate.visitDynamicValueSpecification(object);
	}

	@Override
	public @Nullable R visitElement(org.eclipse.ocl.pivot.@NonNull Element object) {
		return delegate.visitElement(object);
	}

	@Override
	public @Nullable R visitElementExtension(org.eclipse.ocl.pivot.@NonNull ElementExtension object) {
		return delegate.visitElementExtension(object);
	}

	@Override
	public @Nullable R visitEnumLiteralExp(org.eclipse.ocl.pivot.@NonNull EnumLiteralExp object) {
		return delegate.visitEnumLiteralExp(object);
	}

	@Override
	public @Nullable R visitEnumeration(org.eclipse.ocl.pivot.@NonNull Enumeration object) {
		return delegate.visitEnumeration(object);
	}

	@Override
	public @Nullable R visitEnumerationLiteral(org.eclipse.ocl.pivot.@NonNull EnumerationLiteral object) {
		return delegate.visitEnumerationLiteral(object);
	}

	@Override
	public @Nullable R visitExpressionInOCL(org.eclipse.ocl.pivot.@NonNull ExpressionInOCL object) {
		return delegate.visitExpressionInOCL(object);
	}

	@Override
	public @Nullable R visitFeature(org.eclipse.ocl.pivot.@NonNull Feature object) {
		return delegate.visitFeature(object);
	}

	@Override
	public @Nullable R visitFeatureCallExp(org.eclipse.ocl.pivot.@NonNull FeatureCallExp object) {
		return delegate.visitFeatureCallExp(object);
	}

	@Override
	public @Nullable R visitFinalState(org.eclipse.ocl.pivot.@NonNull FinalState object) {
		return delegate.visitFinalState(object);
	}

	@Override
	public @Nullable R visitIfExp(org.eclipse.ocl.pivot.@NonNull IfExp object) {
		return delegate.visitIfExp(object);
	}

	@Override
	public @Nullable R visitImport(org.eclipse.ocl.pivot.@NonNull Import object) {
		return delegate.visitImport(object);
	}

	@Override
	public @Nullable R visitInstanceSpecification(org.eclipse.ocl.pivot.@NonNull InstanceSpecification object) {
		return delegate.visitInstanceSpecification(object);
	}

	@Override
	public @Nullable R visitIntegerLiteralExp(org.eclipse.ocl.pivot.@NonNull IntegerLiteralExp object) {
		return delegate.visitIntegerLiteralExp(object);
	}

	@Override
	public @Nullable R visitInvalidLiteralExp(org.eclipse.ocl.pivot.@NonNull InvalidLiteralExp object) {
		return delegate.visitInvalidLiteralExp(object);
	}

	@Override
	public @Nullable R visitInvalidType(org.eclipse.ocl.pivot.@NonNull InvalidType object) {
		return delegate.visitInvalidType(object);
	}

	@Override
	public @Nullable R visitIterateExp(org.eclipse.ocl.pivot.@NonNull IterateExp object) {
		return delegate.visitIterateExp(object);
	}

	@Override
	public @Nullable R visitIteration(org.eclipse.ocl.pivot.@NonNull Iteration object) {
		return delegate.visitIteration(object);
	}

	@Override
	public @Nullable R visitIteratorExp(org.eclipse.ocl.pivot.@NonNull IteratorExp object) {
		return delegate.visitIteratorExp(object);
	}

	@Override
	public @Nullable R visitLambdaType(org.eclipse.ocl.pivot.@NonNull LambdaType object) {
		return delegate.visitLambdaType(object);
	}

	@Override
	public @Nullable R visitLanguageExpression(org.eclipse.ocl.pivot.@NonNull LanguageExpression object) {
		return delegate.visitLanguageExpression(object);
	}

	@Override
	public @Nullable R visitLetExp(org.eclipse.ocl.pivot.@NonNull LetExp object) {
		return delegate.visitLetExp(object);
	}

	@Override
	public @Nullable R visitLibrary(org.eclipse.ocl.pivot.@NonNull Library object) {
		return delegate.visitLibrary(object);
	}

	@Override
	public @Nullable R visitLiteralExp(org.eclipse.ocl.pivot.@NonNull LiteralExp object) {
		return delegate.visitLiteralExp(object);
	}

	@Override
	public @Nullable R visitLoopExp(org.eclipse.ocl.pivot.@NonNull LoopExp object) {
		return delegate.visitLoopExp(object);
	}

	@Override
	public @Nullable R visitMapLiteralExp(org.eclipse.ocl.pivot.@NonNull MapLiteralExp object) {
		return delegate.visitMapLiteralExp(object);
	}

	@Override
	public @Nullable R visitMapLiteralPart(org.eclipse.ocl.pivot.@NonNull MapLiteralPart object) {
		return delegate.visitMapLiteralPart(object);
	}

	@Override
	public @Nullable R visitMapType(org.eclipse.ocl.pivot.@NonNull MapType object) {
		return delegate.visitMapType(object);
	}

	@Override
	public @Nullable R visitMessageExp(org.eclipse.ocl.pivot.@NonNull MessageExp object) {
		return delegate.visitMessageExp(object);
	}

	@Override
	public @Nullable R visitMessageType(org.eclipse.ocl.pivot.@NonNull MessageType object) {
		return delegate.visitMessageType(object);
	}

	@Override
	public @Nullable R visitModel(org.eclipse.ocl.pivot.@NonNull Model object) {
		return delegate.visitModel(object);
	}

	@Override
	public @Nullable R visitNamedElement(org.eclipse.ocl.pivot.@NonNull NamedElement object) {
		return delegate.visitNamedElement(object);
	}

	@Override
	public @Nullable R visitNamespace(org.eclipse.ocl.pivot.@NonNull Namespace object) {
		return delegate.visitNamespace(object);
	}

	@Override
	public @Nullable R visitNavigationCallExp(org.eclipse.ocl.pivot.@NonNull NavigationCallExp object) {
		return delegate.visitNavigationCallExp(object);
	}

	@Override
	public @Nullable R visitNullLiteralExp(org.eclipse.ocl.pivot.@NonNull NullLiteralExp object) {
		return delegate.visitNullLiteralExp(object);
	}

	@Override
	public @Nullable R visitNumericLiteralExp(org.eclipse.ocl.pivot.@NonNull NumericLiteralExp object) {
		return delegate.visitNumericLiteralExp(object);
	}

	@Override
	public @Nullable R visitOCLExpression(org.eclipse.ocl.pivot.@NonNull OCLExpression object) {
		return delegate.visitOCLExpression(object);
	}

	@Override
	public @Nullable R visitOperation(org.eclipse.ocl.pivot.@NonNull Operation object) {
		return delegate.visitOperation(object);
	}

	@Override
	public @Nullable R visitOperationCallExp(org.eclipse.ocl.pivot.@NonNull OperationCallExp object) {
		return delegate.visitOperationCallExp(object);
	}

	@Override
	public @Nullable R visitOppositePropertyCallExp(org.eclipse.ocl.pivot.@NonNull OppositePropertyCallExp object) {
		return delegate.visitOppositePropertyCallExp(object);
	}

	@Override
	public @Nullable R visitOrderedSetType(org.eclipse.ocl.pivot.@NonNull OrderedSetType object) {
		return delegate.visitOrderedSetType(object);
	}

	@Override
	public @Nullable R visitOrphanCompletePackage(org.eclipse.ocl.pivot.@NonNull OrphanCompletePackage object) {
		return delegate.visitOrphanCompletePackage(object);
	}

	@Override
	public @Nullable R visitPackage(org.eclipse.ocl.pivot.@NonNull Package object) {
		return delegate.visitPackage(object);
	}

	@Override
	public @Nullable R visitParameter(org.eclipse.ocl.pivot.@NonNull Parameter object) {
		return delegate.visitParameter(object);
	}

	@Override
	public @Nullable R visitPrecedence(org.eclipse.ocl.pivot.@NonNull Precedence object) {
		return delegate.visitPrecedence(object);
	}

	@Override
	public @Nullable R visitPrimitiveCompletePackage(org.eclipse.ocl.pivot.@NonNull PrimitiveCompletePackage object) {
		return delegate.visitPrimitiveCompletePackage(object);
	}

	@Override
	public @Nullable R visitPrimitiveLiteralExp(org.eclipse.ocl.pivot.@NonNull PrimitiveLiteralExp object) {
		return delegate.visitPrimitiveLiteralExp(object);
	}

	@Override
	public @Nullable R visitPrimitiveType(org.eclipse.ocl.pivot.@NonNull PrimitiveType object) {
		return delegate.visitPrimitiveType(object);
	}

	@Override
	public @Nullable R visitProfile(org.eclipse.ocl.pivot.@NonNull Profile object) {
		return delegate.visitProfile(object);
	}

	@Override
	public @Nullable R visitProfileApplication(org.eclipse.ocl.pivot.@NonNull ProfileApplication object) {
		return delegate.visitProfileApplication(object);
	}

	@Override
	public @Nullable R visitProperty(org.eclipse.ocl.pivot.@NonNull Property object) {
		return delegate.visitProperty(object);
	}

	@Override
	public @Nullable R visitPropertyCallExp(org.eclipse.ocl.pivot.@NonNull PropertyCallExp object) {
		return delegate.visitPropertyCallExp(object);
	}

	@Override
	public @Nullable R visitPseudostate(org.eclipse.ocl.pivot.@NonNull Pseudostate object) {
		return delegate.visitPseudostate(object);
	}

	@Override
	public @Nullable R visitRealLiteralExp(org.eclipse.ocl.pivot.@NonNull RealLiteralExp object) {
		return delegate.visitRealLiteralExp(object);
	}

	@Override
	public @Nullable R visitRegion(org.eclipse.ocl.pivot.@NonNull Region object) {
		return delegate.visitRegion(object);
	}

	@Override
	public @Nullable R visitSelfType(org.eclipse.ocl.pivot.@NonNull SelfType object) {
		return delegate.visitSelfType(object);
	}

	@Override
	public @Nullable R visitSendSignalAction(org.eclipse.ocl.pivot.@NonNull SendSignalAction object) {
		return delegate.visitSendSignalAction(object);
	}

	@Override
	public @Nullable R visitSequenceType(org.eclipse.ocl.pivot.@NonNull SequenceType object) {
		return delegate.visitSequenceType(object);
	}

	@Override
	public @Nullable R visitSetType(org.eclipse.ocl.pivot.@NonNull SetType object) {
		return delegate.visitSetType(object);
	}

	@Override
	public @Nullable R visitShadowExp(org.eclipse.ocl.pivot.@NonNull ShadowExp object) {
		return delegate.visitShadowExp(object);
	}

	@Override
	public @Nullable R visitShadowPart(org.eclipse.ocl.pivot.@NonNull ShadowPart object) {
		return delegate.visitShadowPart(object);
	}

	@Override
	public @Nullable R visitSignal(org.eclipse.ocl.pivot.@NonNull Signal object) {
		return delegate.visitSignal(object);
	}

	@Override
	public @Nullable R visitSlot(org.eclipse.ocl.pivot.@NonNull Slot object) {
		return delegate.visitSlot(object);
	}

	@Override
	public @Nullable R visitStandardLibrary(org.eclipse.ocl.pivot.@NonNull StandardLibrary object) {
		return delegate.visitStandardLibrary(object);
	}

	@Override
	public @Nullable R visitState(org.eclipse.ocl.pivot.@NonNull State object) {
		return delegate.visitState(object);
	}

	@Override
	public @Nullable R visitStateExp(org.eclipse.ocl.pivot.@NonNull StateExp object) {
		return delegate.visitStateExp(object);
	}

	@Override
	public @Nullable R visitStateMachine(org.eclipse.ocl.pivot.@NonNull StateMachine object) {
		return delegate.visitStateMachine(object);
	}

	@Override
	public @Nullable R visitStereotype(org.eclipse.ocl.pivot.@NonNull Stereotype object) {
		return delegate.visitStereotype(object);
	}

	@Override
	public @Nullable R visitStereotypeExtender(org.eclipse.ocl.pivot.@NonNull StereotypeExtender object) {
		return delegate.visitStereotypeExtender(object);
	}

	@Override
	public @Nullable R visitStringLiteralExp(org.eclipse.ocl.pivot.@NonNull StringLiteralExp object) {
		return delegate.visitStringLiteralExp(object);
	}

	@Override
	public @Nullable R visitTemplateBinding(org.eclipse.ocl.pivot.@NonNull TemplateBinding object) {
		return delegate.visitTemplateBinding(object);
	}

	@Override
	public @Nullable R visitTemplateParameter(org.eclipse.ocl.pivot.@NonNull TemplateParameter object) {
		return delegate.visitTemplateParameter(object);
	}

	@Override
	public @Nullable R visitTemplateParameterSubstitution(org.eclipse.ocl.pivot.@NonNull TemplateParameterSubstitution object) {
		return delegate.visitTemplateParameterSubstitution(object);
	}

	@Override
	public @Nullable R visitTemplateSignature(org.eclipse.ocl.pivot.@NonNull TemplateSignature object) {
		return delegate.visitTemplateSignature(object);
	}

	@Override
	public @Nullable R visitTemplateableElement(org.eclipse.ocl.pivot.@NonNull TemplateableElement object) {
		return delegate.visitTemplateableElement(object);
	}

	@Override
	public @Nullable R visitTransition(org.eclipse.ocl.pivot.@NonNull Transition object) {
		return delegate.visitTransition(object);
	}

	@Override
	public @Nullable R visitTrigger(org.eclipse.ocl.pivot.@NonNull Trigger object) {
		return delegate.visitTrigger(object);
	}

	@Override
	public @Nullable R visitTupleLiteralExp(org.eclipse.ocl.pivot.@NonNull TupleLiteralExp object) {
		return delegate.visitTupleLiteralExp(object);
	}

	@Override
	public @Nullable R visitTupleLiteralPart(org.eclipse.ocl.pivot.@NonNull TupleLiteralPart object) {
		return delegate.visitTupleLiteralPart(object);
	}

	@Override
	public @Nullable R visitTupleType(org.eclipse.ocl.pivot.@NonNull TupleType object) {
		return delegate.visitTupleType(object);
	}

	@Override
	public @Nullable R visitType(org.eclipse.ocl.pivot.@NonNull Type object) {
		return delegate.visitType(object);
	}

	@Override
	public @Nullable R visitTypeExp(org.eclipse.ocl.pivot.@NonNull TypeExp object) {
		return delegate.visitTypeExp(object);
	}

	@Override
	public @Nullable R visitTypedElement(org.eclipse.ocl.pivot.@NonNull TypedElement object) {
		return delegate.visitTypedElement(object);
	}

	@Override
	public @Nullable R visitUnlimitedNaturalLiteralExp(org.eclipse.ocl.pivot.@NonNull UnlimitedNaturalLiteralExp object) {
		return delegate.visitUnlimitedNaturalLiteralExp(object);
	}

	@Override
	public @Nullable R visitUnspecifiedValueExp(org.eclipse.ocl.pivot.@NonNull UnspecifiedValueExp object) {
		return delegate.visitUnspecifiedValueExp(object);
	}

	@Override
	public @Nullable R visitValueSpecification(org.eclipse.ocl.pivot.@NonNull ValueSpecification object) {
		return delegate.visitValueSpecification(object);
	}

	@Override
	public @Nullable R visitVariable(org.eclipse.ocl.pivot.@NonNull Variable object) {
		return delegate.visitVariable(object);
	}

	@Override
	public @Nullable R visitVariableDeclaration(org.eclipse.ocl.pivot.@NonNull VariableDeclaration object) {
		return delegate.visitVariableDeclaration(object);
	}

	@Override
	public @Nullable R visitVariableExp(org.eclipse.ocl.pivot.@NonNull VariableExp object) {
		return delegate.visitVariableExp(object);
	}

	@Override
	public @Nullable R visitVertex(org.eclipse.ocl.pivot.@NonNull Vertex object) {
		return delegate.visitVertex(object);
	}

	@Override
	public @Nullable R visitVoidType(org.eclipse.ocl.pivot.@NonNull VoidType object) {
		return delegate.visitVoidType(object);
	}

	@Override
	public @Nullable R visitWildcardType(org.eclipse.ocl.pivot.@NonNull WildcardType object) {
		return delegate.visitWildcardType(object);
	}
}
