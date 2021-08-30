/*******************************************************************************
 * Copyright (c) 2020, 2021 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.internal.cse;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.BooleanLiteralExp;
import org.eclipse.ocl.pivot.CollectionItem;
import org.eclipse.ocl.pivot.CollectionLiteralExp;
import org.eclipse.ocl.pivot.CollectionLiteralPart;
import org.eclipse.ocl.pivot.CollectionRange;
import org.eclipse.ocl.pivot.CollectionType;
import org.eclipse.ocl.pivot.EnumLiteralExp;
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.IfExp;
import org.eclipse.ocl.pivot.IntegerLiteralExp;
import org.eclipse.ocl.pivot.InvalidLiteralExp;
import org.eclipse.ocl.pivot.IterateExp;
import org.eclipse.ocl.pivot.Iteration;
import org.eclipse.ocl.pivot.IteratorVariable;
import org.eclipse.ocl.pivot.LetExp;
import org.eclipse.ocl.pivot.LoopExp;
import org.eclipse.ocl.pivot.MapLiteralExp;
import org.eclipse.ocl.pivot.MapLiteralPart;
import org.eclipse.ocl.pivot.NavigationCallExp;
import org.eclipse.ocl.pivot.NullLiteralExp;
import org.eclipse.ocl.pivot.OCLExpression;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.OperationCallExp;
import org.eclipse.ocl.pivot.ParameterVariable;
import org.eclipse.ocl.pivot.PivotPackage;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.RealLiteralExp;
import org.eclipse.ocl.pivot.ShadowExp;
import org.eclipse.ocl.pivot.ShadowPart;
import org.eclipse.ocl.pivot.StringLiteralExp;
import org.eclipse.ocl.pivot.TupleLiteralExp;
import org.eclipse.ocl.pivot.TupleLiteralPart;
import org.eclipse.ocl.pivot.TypeExp;
import org.eclipse.ocl.pivot.TypedElement;
import org.eclipse.ocl.pivot.UnlimitedNaturalLiteralExp;
import org.eclipse.ocl.pivot.Variable;
import org.eclipse.ocl.pivot.VariableDeclaration;
import org.eclipse.ocl.pivot.VariableExp;
import org.eclipse.ocl.pivot.internal.cse.AbstractCSEElement.CSEVariableElement;
import org.eclipse.ocl.pivot.util.AbstractExtendingVisitor;
import org.eclipse.ocl.pivot.util.Visitable;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.utilities.ValueUtil;

/**
 * The CSEVisitor provides the algorithms to convert a Pivot Element to its CSEElement
 * under the caching and creation supervision of a CommonSubExpressionAnalysis.
 *
 * @since 1.17
 */
public class CSEVisitor extends AbstractExtendingVisitor<@NonNull CSEElement, @NonNull CommonSubExpressionAnalysis>
{
	public CSEVisitor(@NonNull CommonSubExpressionAnalysis cseAnalysis) {
		super(cseAnalysis);
	}

	public @NonNull CommonSubExpressionAnalysis getCommonSubExpressionAnalysis() {
		return context;
	}

	@Override
	public String toString() {
		return context.toString();
	}

	public @NonNull CSEElement visit(@NonNull OCLExpression expression) {
		CSEElement cseElement = expression.accept(this);
		assert cseElement != null;
		return cseElement;
	}

	@Override
	public @NonNull CSEElement visitBooleanLiteralExp(@NonNull BooleanLiteralExp booleanLiteralExp) {
		return context.getValueCSE(booleanLiteralExp.isBooleanSymbol());
	}

	@Override
	public @NonNull CSEElement visitCollectionItem(@NonNull CollectionItem collectionItem) {
		return context.getCSEElement(PivotUtil.getOwnedItem(collectionItem));
	}

	@Override
	public @NonNull CSEElement visitCollectionLiteralExp(@NonNull CollectionLiteralExp collectionLiteralExp) {
		List<@NonNull CSEElement> elements = new ArrayList<>();
		for (@NonNull CollectionLiteralPart part : PivotUtil.getOwnedParts(collectionLiteralExp)) {
			elements.add(context.getCSEElement(part));
		}
		CollectionType collectionType = (CollectionType)collectionLiteralExp.getType();
		if (!collectionType.isOrdered()) {
			Collections.sort(elements);
		}
		return context.getNamespaceCSE(collectionLiteralExp, elements);
	}

	@Override
	public @NonNull CSEElement visitCollectionRange(@NonNull CollectionRange collectionRange) {
		List<@NonNull CSEElement> elements = new ArrayList<>();
		elements.add(context.getCSEElement(PivotUtil.getOwnedFirst(collectionRange)));
		elements.add(context.getCSEElement(PivotUtil.getOwnedLast(collectionRange)));
		return context.getNamespaceCSE(collectionRange, elements);
	}

	@Override
	public @NonNull CSEElement visitEnumLiteralExp(@NonNull EnumLiteralExp enumLiteralExp) {
		return context.getValueCSE(PivotUtil.getReferredLiteral(enumLiteralExp));
	}

	@Override
	public @NonNull CSEElement visitExpressionInOCL(@NonNull ExpressionInOCL expressionInOCL) {
		List<@NonNull CSEElement> elements = new ArrayList<>();
		elements.add(context.getCSEElement(PivotUtil.getOwnedContext(expressionInOCL)));
		for (@NonNull VariableDeclaration parameter : PivotUtil.getOwnedParameters(expressionInOCL)) {
			elements.add(context.getCSEElement(parameter));
		}
		Variable ownedResult = expressionInOCL.getOwnedResult();
		if (ownedResult != null) {
			elements.add(context.getCSEElement(ownedResult));
		}
		elements.add(context.getCSEElement(PivotUtil.getOwnedBody(expressionInOCL)));
		return context.getNamespaceCSE(expressionInOCL, elements);
	}

	@Override
	public @NonNull CSEElement visitIfExp(@NonNull IfExp ifExp) {
		OCLExpression conditionExp = PivotUtil.getOwnedCondition(ifExp);
		OCLExpression thenExp = PivotUtil.getOwnedThen(ifExp);
		OCLExpression elseExp = PivotUtil.getOwnedElse(ifExp);
		CSEElement conditionCSE = context.getCSEElement(conditionExp);
		CSEElement thenCSE = context.getCSEElement(thenExp);
		CSEElement elseCSE = context.getCSEElement(elseExp);
		return conditionCSE.getIfCSE(ifExp, thenCSE, elseCSE);
	}

	@Override
	public @NonNull CSEElement visitIntegerLiteralExp(@NonNull IntegerLiteralExp integerLiteralExp) {
		return context.getValueCSE(ValueUtil.integerValueOf(integerLiteralExp.getIntegerSymbol()));
	}

	@Override
	public @NonNull CSEElement visitInvalidLiteralExp(@NonNull InvalidLiteralExp invalidLiteralExp) {
		return context.getValueCSE(ValueUtil.INVALID_VALUE);
	}

	@Override
	public @NonNull CSEElement visitIteratorVariable(@NonNull IteratorVariable iteratorVariable) {
		LoopExp loopExp = (LoopExp)iteratorVariable.eContainer();
		assert loopExp != null;
		OCLExpression ownedSource = PivotUtil.getOwnedSource(loopExp);
		CSEElement sourceCSE = context.getCSEElement(ownedSource);
		CSEVariableElement iteratorCSE = new CSEVariableElement(context, iteratorVariable, sourceCSE.getHeight() + 1);
		iteratorCSE.addInput(sourceCSE);
		return iteratorCSE;
	}

	@Override
	public @NonNull CSEElement visitLetExp(@NonNull LetExp letExp) {
		List<@NonNull CSEElement> elements = new ArrayList<>();
		elements.add(context.getCSEElement(PivotUtil.getOwnedVariable(letExp)));
		elements.add(context.getCSEElement(PivotUtil.getOwnedIn(letExp)));
		return context.getNamespaceCSE(letExp, elements);
	}

	@Override
	public @NonNull CSEElement visitLoopExp(@NonNull LoopExp loopExp) {
		OCLExpression sourceExp = PivotUtil.getOwnedSource(loopExp);
		OCLExpression bodyExp = PivotUtil.getOwnedBody(loopExp);
		CSEElement sourceCSE = context.getCSEElement(sourceExp);
		List<@Nullable CSEElement> argumentCSEs = new ArrayList<>();
		for (@NonNull Variable iterator : PivotUtil.getOwnedIterators(loopExp)) {
			@SuppressWarnings("unused")
			CSEElement variableCSE = context.getCSEElement(iterator);
			OCLExpression initExp = iterator.getOwnedInit();
			CSEElement initCSE = initExp != null ? context.getCSEElement(initExp) : variableCSE;
			argumentCSEs.add(initCSE);
		}
		for (@NonNull Variable coiterator : PivotUtil.getOwnedCoIterators(loopExp)) {
			argumentCSEs.add(context.getCSEElement(coiterator));
		}
		if (loopExp instanceof IterateExp) {
			Variable result = PivotUtil.getOwnedResult((IterateExp)loopExp);
			argumentCSEs.add(context.getCSEElement(result));
			OCLExpression resultExp = result.getOwnedInit();
			@SuppressWarnings("unused")
			CSEElement resultCSE = resultExp != null ? context.getCSEElement(resultExp) : null;
		//	argumentCSEs.add(resultCSE);
		}
		argumentCSEs.add(context.getCSEElement(bodyExp));
		Iteration iteration = PivotUtil.getReferredIteration(loopExp);
		AbstractCSEElement iterationCSE = (AbstractCSEElement) sourceCSE.getOperationCSE(loopExp, iteration, argumentCSEs);
	//	for (@NonNull Variable iterator : PivotUtil.getOwnedIterators(loopExp)) {
	//		iterationCSE.addInput(context.getCSEElement(iterator));
		//	((AbstractCSEElement)context.getCSEElement(iterator)).addInput(iterationCSE);
	//	}
		return iterationCSE;
	}

	@Override
	public @NonNull CSEElement visitMapLiteralExp(@NonNull MapLiteralExp mapLiteralExp) {
		List<@NonNull CSEElement> elements = new ArrayList<>();
		for (@NonNull MapLiteralPart part : PivotUtil.getOwnedParts(mapLiteralExp)) {
			elements.add(context.getCSEElement(part));
		}
		Collections.sort(elements);
		return context.getNamespaceCSE(mapLiteralExp, elements);
	}

	@Override
	public @NonNull CSEElement visitMapLiteralPart(@NonNull MapLiteralPart mapLiteralPart) {
		List<@NonNull CSEElement> elements = new ArrayList<>();
		elements.add(context.getCSEElement(PivotUtil.getOwnedKey(mapLiteralPart)));
		elements.add(context.getCSEElement(PivotUtil.getOwnedValue(mapLiteralPart)));
		return context.getNamespaceCSE(mapLiteralPart, elements);
	}

	@Override
	public @NonNull CSEElement visitNavigationCallExp(@NonNull NavigationCallExp navigationCallExp) {
		OCLExpression sourceExp = PivotUtil.getOwnedSource(navigationCallExp);
		CSEElement sourceCSE = context.getCSEElement(sourceExp);
		return sourceCSE.getPropertyCSE(navigationCallExp);
	}

	@Override
	public @NonNull CSEElement visitNullLiteralExp(@NonNull NullLiteralExp nullLiteralExp) {
		return context.getValueCSE(ValueUtil.NULL_VALUE);
	}

	@Override
	public @NonNull CSEElement visitOperationCallExp(@NonNull OperationCallExp operationCallExp) {
		OCLExpression sourceExp = PivotUtil.getOwnedSource(operationCallExp);
		CSEElement sourceCSE = context.getCSEElement(sourceExp);
		List<@Nullable CSEElement> argumentCSEs = new ArrayList<>();
		for (@NonNull OCLExpression argumentExp : PivotUtil.getOwnedArguments(operationCallExp)) {
			CSEElement argumentCSE = context.getCSEElement(argumentExp);
			argumentCSEs.add(argumentCSE);
		}
		Operation operation = PivotUtil.getReferredOperation(operationCallExp);
		return sourceCSE.getOperationCSE(operationCallExp, operation, argumentCSEs);
	}

	@Override
	public @NonNull CSEElement visitParameterVariable(@NonNull ParameterVariable parameterVariable) {
		if (parameterVariable.eContainmentFeature() == PivotPackage.Literals.EXPRESSION_IN_OCL__OWNED_CONTEXT) {
			return context.getSelfCSE();	// same CSE for many root elements
		}
		else {
			return super.visitParameterVariable(parameterVariable);
		}
	}

	@Override
	public @NonNull CSEElement visitRealLiteralExp(@NonNull RealLiteralExp realLiteralExp) {
		return context.getValueCSE(ValueUtil.realValueOf(realLiteralExp.getRealSymbol()));
	}

	@Override
	public @NonNull CSEElement visitShadowExp(@NonNull ShadowExp shadowExp) {
		Map<@NonNull TypedElement, @NonNull CSEElement> property2element = new HashMap<>();
		for (@NonNull ShadowPart shadowPart : PivotUtil.getOwnedParts(shadowExp)) {
			Property shadowProperty = PivotUtil.getReferredProperty(shadowPart);
			CSEElement partCSE = context.getCSEElement(shadowPart);
			property2element.put(shadowProperty, partCSE);
		}
		return context.getMappedCSE(shadowExp, property2element);
	}

	@Override
	public @NonNull CSEElement visitShadowPart(@NonNull ShadowPart shadowPart) {
		CSEElement initCSE = context.getCSEElement(PivotUtil.getOwnedInit(shadowPart));
		return context.getNamespaceCSE(shadowPart, Collections.singletonList(initCSE));
	}

	@Override
	public @NonNull CSEElement visitStringLiteralExp(@NonNull StringLiteralExp stringLiteralExp) {
		return context.getValueCSE(PivotUtil.getStringSymbol(stringLiteralExp));
	}

	@Override
	public @NonNull CSEElement visitTupleLiteralExp(@NonNull TupleLiteralExp tupleLiteralExp) {
		Map<@NonNull TypedElement, @NonNull CSEElement> property2element = new HashMap<>();
		for (@NonNull TupleLiteralPart tuplePart : PivotUtil.getOwnedParts(tupleLiteralExp)) {
			CSEElement partCSE = context.getCSEElement(tuplePart);
			property2element.put(tuplePart, partCSE);
		}
		return context.getMappedCSE(tupleLiteralExp, property2element);
	}

	@Override
	public @NonNull CSEElement visitTupleLiteralPart(@NonNull TupleLiteralPart tupleLiteralPart) {
		OCLExpression partInit = PivotUtil.getOwnedInit(tupleLiteralPart);
		CSEElement initCSE = context.getCSEElement(partInit);
		return context.getNamespaceCSE(tupleLiteralPart, Collections.singletonList(initCSE));
	}

	@Override
	public @NonNull CSEElement visitTypeExp(@NonNull TypeExp typeExp) {
		return context.getValueCSE(typeExp.getReferredType().getTypeId());
	}

	@Override
	public @NonNull CSEElement visitUnlimitedNaturalLiteralExp(@NonNull UnlimitedNaturalLiteralExp unlimitedNaturalLiteralExp) {
		return context.getValueCSE(ValueUtil.unlimitedNaturalValueOf(unlimitedNaturalLiteralExp.getUnlimitedNaturalSymbol()));
	}

	@Override
	public @NonNull CSEElement visitVariable(@NonNull Variable variable) {
		OCLExpression initExpression = variable.getOwnedInit();
		if (initExpression != null) {
			return context.getCSEElement(initExpression);
		}
		else {
			return super.visitVariable(variable);
		}
	}

	@Override
	public @NonNull CSEElement visitVariableDeclaration(@NonNull VariableDeclaration variableDeclaration) {
		return new CSEVariableElement(context, variableDeclaration, 0);
	}

	@Override
	public @NonNull CSEElement visitVariableExp(@NonNull VariableExp variableExp) {
		VariableDeclaration variable = PivotUtil.getReferredVariable(variableExp);
		return context.getCSEElement(variable);
	}

	@Override
	public @NonNull CSEElement visiting(@NonNull Visitable visitable) {
		throw new IllegalArgumentException("Unsupported " + visitable.eClass().getName() + " for " + getClass().getSimpleName());
	}
}
