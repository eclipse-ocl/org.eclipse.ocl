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
import org.eclipse.ocl.pivot.LetExp;
import org.eclipse.ocl.pivot.LoopExp;
import org.eclipse.ocl.pivot.MapLiteralExp;
import org.eclipse.ocl.pivot.MapLiteralPart;
import org.eclipse.ocl.pivot.NavigationCallExp;
import org.eclipse.ocl.pivot.NullLiteralExp;
import org.eclipse.ocl.pivot.OCLExpression;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.OperationCallExp;
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
import org.eclipse.ocl.pivot.internal.cse.AbstractCSEElement.CSEMappedElement;
import org.eclipse.ocl.pivot.util.AbstractExtendingVisitor;
import org.eclipse.ocl.pivot.util.Visitable;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.utilities.ValueUtil;

/**
 * The CSEVisitor provides the algorithms to convert a Pivot Element to its CSEElement
 * under the caching and creation supervision of a CommonSubExpressionAnalysis.
 *
 * @since 1.16
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
		return context.getValueCSE(booleanLiteralExp, booleanLiteralExp.isBooleanSymbol());
	}

	@Override
	public @NonNull CSEElement visitCollectionItem(@NonNull CollectionItem collectionItem) {
		CSEElement elementCSE = context.getCSEElement(PivotUtil.getOwnedItem(collectionItem));
		elementCSE.addElement(collectionItem);
		return elementCSE;
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
		return context.getValueCSE(enumLiteralExp, PivotUtil.getReferredLiteral(enumLiteralExp));
	}

	@Override
	public @NonNull CSEElement visitExpressionInOCL(@NonNull ExpressionInOCL expressionInOCL) {
		@SuppressWarnings("unused")
		CSEElement selfCSE = context.getVariableCSE(PivotUtil.getOwnedContext(expressionInOCL));
		for (@NonNull VariableDeclaration parameter : PivotUtil.getOwnedParameters(expressionInOCL)) {
			@SuppressWarnings("unused")
			CSEElement parameterCSE = context.getVariableCSE(parameter);
		}
		Variable ownedResult = expressionInOCL.getOwnedResult();
		if (ownedResult != null) {
			@SuppressWarnings("unused")
			CSEElement resultCSE = context.getVariableCSE(ownedResult);
		}
		OCLExpression bodyExp = PivotUtil.getOwnedBody(expressionInOCL);
		CSEElement bodyCSE = context.getCSEElement(bodyExp);
		bodyCSE.addElement(expressionInOCL);
		return bodyCSE;
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
		return context.getValueCSE(integerLiteralExp, ValueUtil.integerValueOf(integerLiteralExp.getIntegerSymbol()));
	}

	@Override
	public @NonNull CSEElement visitInvalidLiteralExp(@NonNull InvalidLiteralExp invalidLiteralExp) {
		return context.getValueCSE(invalidLiteralExp, ValueUtil.INVALID_VALUE);
	}

	@Override
	public @NonNull CSEElement visitLetExp(@NonNull LetExp letExp) {
		@SuppressWarnings("unused")
		CSEElement variableCSE = context.getVariableCSE(PivotUtil.getOwnedVariable(letExp));
		OCLExpression inExp = PivotUtil.getOwnedIn(letExp);
		CSEElement inCSE = context.getCSEElement(inExp);
		inCSE.addElement(letExp);
		return inCSE;		// init is separate as referenced, in is indeed unchanged.
	}

	@Override
	public @NonNull CSEElement visitLoopExp(@NonNull LoopExp loopExp) {
		OCLExpression sourceExp = PivotUtil.getOwnedSource(loopExp);
		OCLExpression bodyExp = PivotUtil.getOwnedBody(loopExp);
		CSEElement sourceCSE = context.getCSEElement(sourceExp);
		List<@Nullable CSEElement> argumentCSEs = new ArrayList<>();
		for (@NonNull Variable iterator : PivotUtil.getOwnedIterators(loopExp)) {
			@SuppressWarnings("unused")
			CSEElement variableCSE = context.getVariableCSE(iterator);
			OCLExpression initExp = iterator.getOwnedInit();
			CSEElement initCSE = initExp != null ? context.getCSEElement(initExp) : null;
			argumentCSEs.add(initCSE);
		}
		for (@NonNull Variable coiterator : PivotUtil.getOwnedCoIterators(loopExp)) {
			@SuppressWarnings("unused")
			CSEElement variableCSE = context.getVariableCSE(coiterator);
		}
		if (loopExp instanceof IterateExp) {
			Variable result = PivotUtil.getOwnedResult((IterateExp)loopExp);
			@SuppressWarnings("unused")
			CSEElement variableCSE = context.getVariableCSE(result);
			OCLExpression resultExp = result.getOwnedInit();
			CSEElement resultCSE = resultExp != null ? context.getCSEElement(resultExp) : null;
			argumentCSEs.add(resultCSE);
		}
		CSEElement bodyCSE = context.getCSEElement(bodyExp);
		argumentCSEs.add(bodyCSE);
		Iteration iteration = PivotUtil.getReferredIteration(loopExp);
		return sourceCSE.getOperationCSE(loopExp, iteration, argumentCSEs);
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
		return context.getValueCSE(nullLiteralExp, ValueUtil.NULL_VALUE);
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
	public @NonNull CSEElement visitRealLiteralExp(@NonNull RealLiteralExp realLiteralExp) {
		return context.getValueCSE(realLiteralExp, ValueUtil.realValueOf(realLiteralExp.getRealSymbol()));
	}

	@Override
	public @NonNull CSEElement visitShadowExp(@NonNull ShadowExp shadowExp) {
		Map<@NonNull TypedElement, @NonNull CSEElement> property2element = new HashMap<>();
		for (@NonNull ShadowPart shadowPart : PivotUtil.getOwnedParts(shadowExp)) {
			Property shadowProperty = PivotUtil.getReferredProperty(shadowPart);
			OCLExpression shadowInit = PivotUtil.getOwnedInit(shadowPart);
			CSEElement initCSE = context.getCSEElement(shadowInit);
			CSEElement partCSE = context.getNamespaceCSE(shadowPart, Collections.singletonList(initCSE));
			property2element.put(shadowProperty, partCSE);
		}
		return context.getMappedCSE(shadowExp, property2element);
	}

	@Override
	public @NonNull CSEElement visitShadowPart(@NonNull ShadowPart shadowPart) {
		ShadowExp shadowExp = PivotUtil.getOwningShadowExp(shadowPart);
		Property shadowProperty = PivotUtil.getReferredProperty(shadowPart);
		CSEMappedElement shadowCSE = (CSEMappedElement)context.getCSEElement(shadowExp);
		return shadowCSE.getElement(shadowProperty);
	}

	@Override
	public @NonNull CSEElement visitStringLiteralExp(@NonNull StringLiteralExp stringLiteralExp) {
		return context.getValueCSE(stringLiteralExp, PivotUtil.getStringSymbol(stringLiteralExp));
	}

	@Override
	public @NonNull CSEElement visitTupleLiteralExp(@NonNull TupleLiteralExp tupleLiteralExp) {
		Map<@NonNull TypedElement, @NonNull CSEElement> property2element = new HashMap<>();
		for (@NonNull TupleLiteralPart tuplePart : PivotUtil.getOwnedParts(tupleLiteralExp)) {
			OCLExpression shadowInit = PivotUtil.getOwnedInit(tuplePart);
			CSEElement initCSE = context.getCSEElement(shadowInit);
			CSEElement partCSE = context.getNamespaceCSE(tuplePart, Collections.singletonList(initCSE));
			property2element.put(tuplePart, partCSE);
		}
		return context.getMappedCSE(tupleLiteralExp, property2element);
	}

	@Override
	public @NonNull CSEElement visitTupleLiteralPart(@NonNull TupleLiteralPart tupleLiteralPart) {
		TupleLiteralExp tupleLiteralExp = PivotUtil.getOwningTupleLiteralExp(tupleLiteralPart);
		CSEMappedElement tupleCSE = (CSEMappedElement)context.getCSEElement(tupleLiteralExp);
		return tupleCSE.getElement(tupleLiteralPart);
	}

	@Override
	public @NonNull CSEElement visitTypeExp(@NonNull TypeExp typeExp) {
		return context.getTypeCSE(typeExp);
	}

	@Override
	public @NonNull CSEElement visitUnlimitedNaturalLiteralExp(@NonNull UnlimitedNaturalLiteralExp unlimitedNaturalLiteralExp) {
		return context.getValueCSE(unlimitedNaturalLiteralExp, ValueUtil.unlimitedNaturalValueOf(unlimitedNaturalLiteralExp.getUnlimitedNaturalSymbol()));
	}

	@Override
	public @NonNull CSEElement visitVariableDeclaration(@NonNull VariableDeclaration variableDeclaration) {
		return context.getVariableCSE(variableDeclaration);
	}

	@Override
	public @NonNull CSEElement visitVariableExp(@NonNull VariableExp variableExp) {
		VariableDeclaration variable = PivotUtil.getReferredVariable(variableExp);
		CSEElement variableCSE = context.getVariableCSE(variable);
		variableCSE.addElement(variableExp);
		return variableCSE;
	}

	@Override
	public @NonNull CSEElement visiting(@NonNull Visitable visitable) {
		throw new IllegalArgumentException("Unsupported " + visitable.eClass().getName() + " for " + getClass().getSimpleName());
	}
}
