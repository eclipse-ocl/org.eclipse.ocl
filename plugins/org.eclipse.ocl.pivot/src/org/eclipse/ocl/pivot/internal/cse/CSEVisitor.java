/*******************************************************************************
 * Copyright (c) 2010, 2020 Willink Transformations and others.
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
import java.util.List;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.BooleanLiteralExp;
import org.eclipse.ocl.pivot.EnumLiteralExp;
import org.eclipse.ocl.pivot.IfExp;
import org.eclipse.ocl.pivot.IntegerLiteralExp;
import org.eclipse.ocl.pivot.InvalidLiteralExp;
import org.eclipse.ocl.pivot.IterateExp;
import org.eclipse.ocl.pivot.Iteration;
import org.eclipse.ocl.pivot.LetExp;
import org.eclipse.ocl.pivot.LoopExp;
import org.eclipse.ocl.pivot.NavigationCallExp;
import org.eclipse.ocl.pivot.NullLiteralExp;
import org.eclipse.ocl.pivot.OCLExpression;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.OperationCallExp;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.RealLiteralExp;
import org.eclipse.ocl.pivot.StringLiteralExp;
import org.eclipse.ocl.pivot.UnlimitedNaturalLiteralExp;
import org.eclipse.ocl.pivot.Variable;
import org.eclipse.ocl.pivot.VariableDeclaration;
import org.eclipse.ocl.pivot.VariableExp;
import org.eclipse.ocl.pivot.util.AbstractExtendingVisitor;
import org.eclipse.ocl.pivot.util.Visitable;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.utilities.ValueUtil;

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
	public @NonNull CSEElement visitEnumLiteralExp(@NonNull EnumLiteralExp enumLiteralExp) {
		return context.getValueCSE(PivotUtil.getReferredLiteral(enumLiteralExp));
	}

	@Override
	public @NonNull CSEElement visitIfExp(@NonNull IfExp ifExp) {
		OCLExpression conditionExp = PivotUtil.getOwnedCondition(ifExp);
		OCLExpression thenExp = PivotUtil.getOwnedThen(ifExp);
		OCLExpression elseExp = PivotUtil.getOwnedElse(ifExp);
		CSEElement conditionCSE = context.getExpressionCSE(conditionExp);
		CSEElement thenCSE = context.getExpressionCSE(thenExp);
		CSEElement elseCSE = context.getExpressionCSE(elseExp);
		return conditionCSE.getIfCSE(thenCSE, elseCSE);
	}

	@Override
	public @NonNull CSEElement visitIntegerLiteralExp(@NonNull IntegerLiteralExp integerLiteralExp) {
		return context.getValueCSE(ValueUtil.realValueOf(integerLiteralExp.getIntegerSymbol()));
	}

	@Override
	public @NonNull CSEElement visitInvalidLiteralExp(@NonNull InvalidLiteralExp object) {
		return context.getValueCSE(ValueUtil.INVALID_VALUE);
	}

	@Override
	public @NonNull CSEElement visitLetExp(@NonNull LetExp letExp) {
		OCLExpression inExp = PivotUtil.getOwnedIn(letExp);
		CSEElement inCSE = context.getExpressionCSE(inExp);
		return inCSE;		// init is separate as referenced, in is indeed unchanged.
	}

	@Override
	public @NonNull CSEElement visitLoopExp(@NonNull LoopExp loopExp) {
		OCLExpression sourceExp = PivotUtil.getOwnedSource(loopExp);
		OCLExpression bodyExp = PivotUtil.getOwnedBody(loopExp);
		CSEElement sourceCSE = context.getExpressionCSE(sourceExp);
		List<@Nullable CSEElement> argumentCSEs = new ArrayList<>();
		for (@NonNull Variable iterator : PivotUtil.getOwnedIterators(loopExp)) {
			OCLExpression initExp = iterator.getOwnedInit();
			CSEElement initCSE = initExp != null ? context.getExpressionCSE(initExp) : null;
			argumentCSEs.add(initCSE);
		}
		if (loopExp instanceof IterateExp) {
			Variable result = PivotUtil.getOwnedResult((IterateExp)loopExp);
			OCLExpression resultExp = result.getOwnedInit();
			CSEElement resultCSE = resultExp != null ? context.getExpressionCSE(resultExp) : null;
			argumentCSEs.add(resultCSE);
		}
		CSEElement bodyCSE = context.getExpressionCSE(bodyExp);
		argumentCSEs.add(bodyCSE);
		Iteration iteration = PivotUtil.getReferredIteration(loopExp);
		return sourceCSE.getOperationCSE(iteration, argumentCSEs);
	}

	@Override
	public @NonNull CSEElement visitNavigationCallExp(@NonNull NavigationCallExp navigationCallExp) {
		OCLExpression source = PivotUtil.getOwnedSource(navigationCallExp);
		CSEElement sourceCSE = context.getExpressionCSE(source);
		Property property = PivotUtil.getReferredProperty(navigationCallExp);
		return sourceCSE.getPropertyCSE(property);
	}

	@Override
	public @NonNull CSEElement visitNullLiteralExp(@NonNull NullLiteralExp nullLiteralExp) {
		return context.getValueCSE(ValueUtil.NULL_VALUE);
	}

	@Override
	public @NonNull CSEElement visitOperationCallExp(@NonNull OperationCallExp operationCallExp) {
		OCLExpression sourceExp = PivotUtil.getOwnedSource(operationCallExp);
		CSEElement sourceCSE = context.getExpressionCSE(sourceExp);
		List<@Nullable CSEElement> argumentCSEs = new ArrayList<>();
		for (@NonNull OCLExpression argumentExp : PivotUtil.getOwnedArguments(operationCallExp)) {
			CSEElement argumentCSE = context.getExpressionCSE(argumentExp);
			argumentCSEs.add(argumentCSE);
		}
		Operation operation = PivotUtil.getReferredOperation(operationCallExp);
		return sourceCSE.getOperationCSE(operation, argumentCSEs);
	}

	@Override
	public @NonNull CSEElement visitRealLiteralExp(@NonNull RealLiteralExp realLiteralExp) {
		return context.getValueCSE(ValueUtil.realValueOf(realLiteralExp.getRealSymbol()));
	}

	@Override
	public @NonNull CSEElement visitStringLiteralExp(@NonNull StringLiteralExp stringLiteralExp) {
		return context.getValueCSE(PivotUtil.getStringSymbol(stringLiteralExp));
	}

	@Override
	public @NonNull CSEElement visitUnlimitedNaturalLiteralExp(@NonNull UnlimitedNaturalLiteralExp unlimitedNaturalLiteralExp) {
		return context.getValueCSE(ValueUtil.realValueOf(unlimitedNaturalLiteralExp.getUnlimitedNaturalSymbol()));
	}

	@Override
	public @NonNull CSEElement visitVariableDeclaration(@NonNull VariableDeclaration variableDeclaration) {
		return context.getVariableCSE(variableDeclaration);
	}

	@Override
	public @NonNull CSEElement visitVariableExp(@NonNull VariableExp variableExp) {
		VariableDeclaration variable = PivotUtil.getReferredVariable(variableExp);
		if (variableExp.eContainer() instanceof LetExp) {
			OCLExpression initExp = ((Variable)variable).getOwnedInit();
			if (initExp != null) {
				return context.getExpressionCSE(initExp);
			}
		}
		CSEVariableElement variableCSE = context.getVariableCSE(variable);
		variableCSE.addVariableExp(variableExp);
		return variableCSE;
	}

	@Override
	public @NonNull CSEElement visiting(@NonNull Visitable visitable) {
		throw new IllegalArgumentException("Unsupported " + visitable.eClass().getName() + " for " + getClass().getSimpleName());
	}
}
