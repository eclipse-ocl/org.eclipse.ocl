/**
 * Copyright (c) 2021 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 */
package org.eclipse.ocl.pivot.internal.cse;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.LiteralExp;
import org.eclipse.ocl.pivot.OCLExpression;
import org.eclipse.ocl.pivot.TypedElement;
import org.eclipse.ocl.pivot.VariableDeclaration;
import org.eclipse.ocl.pivot.VariableExp;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.utilities.StringUtil;

/**
 * @since 1.15
 */
public class CommonSubExpressionAnalysis
{
	protected final @NonNull CSEVisitor visitor;
	private final @NonNull Map<@NonNull TypedElement, @NonNull CSEElement> element2cse = new HashMap<>();
//	private final @NonNull Map<@NonNull OCLExpression, @NonNull CSEElement> expression2cse = new HashMap<>();
	private final @NonNull Map<@NonNull Object, @NonNull CSEValueElement> value2cse = new HashMap<>();
//	private final @NonNull Map<@NonNull VariableDeclaration, @NonNull CSEVariableElement> variable2cse = new HashMap<>();

	public CommonSubExpressionAnalysis() {
		this.visitor = createCSEVisitor();
	}

	public CommonSubExpressionAnalysis(@NonNull CSEVisitor visitor) {
		this.visitor = visitor;
	}

	public @NonNull CSEElement analyze(@NonNull ExpressionInOCL expressionInOCL) {
		OCLExpression bodyExp = PivotUtil.getOwnedBody(expressionInOCL);
		return getExpressionCSE(bodyExp);
	}

	protected @NonNull CSEVisitor createCSEVisitor() {
		return new CSEVisitor(this);
	}

	public @NonNull CSEElement getElementCSE(@NonNull TypedElement expression) {
		CSEElement cseElement = element2cse.get(expression);
		if (cseElement == null) {
			cseElement = visitor.visit(expression);
			element2cse.put(expression, cseElement);
		}
		return cseElement;
	}

	public @NonNull CSEElement getExpressionCSE(@NonNull OCLExpression expression) {
		CSEElement cseElement = element2cse.get(expression);
		if (cseElement == null) {
			cseElement = visitor.visit(expression);
			element2cse.put(expression, cseElement);
		}
		return cseElement;
	}

	public @NonNull CSEValueElement getValueCSE(@NonNull LiteralExp literalExp, @NonNull Object value) {
		CSEValueElement cseElement = value2cse.get(value);
		if (cseElement == null) {
			cseElement = new CSEValueElement(this, literalExp, value);
			value2cse.put(value, cseElement);
		}
		cseElement.addLiteralExp(literalExp);
		return cseElement;
	}

	public @NonNull CSEVariableElement getVariableCSE(@Nullable VariableExp variableExp, @NonNull VariableDeclaration variableDeclaration) {
		CSEVariableElement cseElement = (CSEVariableElement) element2cse.get(variableDeclaration);
		if (cseElement == null) {
			cseElement = new CSEVariableElement(this, variableDeclaration);
			element2cse.put(variableDeclaration, cseElement);
		}
		if (variableExp != null) {
			cseElement.addVariableExp(variableExp);
		}
		return cseElement;
	}

	@Override
	public @NonNull String toString() {
		StringBuilder s = new StringBuilder();
		toString(s, 100);
		return s.toString();
	}

	public void toString(@NonNull StringBuilder s, int lengthLimit) {
		s.append("variables");
		StringUtil.appendIndentation(s, lengthLimit+1);
	//	Set<@NonNull VariableDeclaration> variables = element2cse.keySet();
	//	for (@NonNull VariableDeclaration> variable : Collections.sort(variables))
	//	s.append(mayBeNull ? "?" : "1");
	//	if (mayBeInvalid) {
	//		s.append("!");
	//	}
	//	s.append("]");
	}
}
