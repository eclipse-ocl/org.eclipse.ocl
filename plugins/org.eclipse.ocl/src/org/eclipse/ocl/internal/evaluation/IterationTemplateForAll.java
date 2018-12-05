/*******************************************************************************
 * Copyright (c) 2005, 2018 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 *******************************************************************************/

package org.eclipse.ocl.internal.evaluation;

import java.util.List;

import org.eclipse.ocl.EvaluationEnvironment;
import org.eclipse.ocl.EvaluationVisitor;
import org.eclipse.ocl.expressions.Variable;


/**
 *
 */
public final class IterationTemplateForAll<PK, C, O, P, EL, PM, S, COA, SSA, CT, CLS, E>
		extends IterationTemplate<PK, C, O, P, EL, PM, S, COA, SSA, CT, CLS, E> {
	
	private IterationTemplateForAll(
			EvaluationVisitor<PK, C, O, P, EL, PM, S, COA, SSA, CT, CLS, E> v) {
		super(v);
	}
	
	public static<PK, C, O, P, EL, PM, S, COA, SSA, CT, CLS, E>
	IterationTemplate<PK, C, O, P, EL, PM, S, COA, SSA, CT, CLS, E>
	getInstance(EvaluationVisitor<PK, C, O, P, EL, PM, S, COA, SSA, CT, CLS, E> v) {
		return new IterationTemplateForAll<PK, C, O, P, EL, PM, S, COA, SSA, CT, CLS, E>(v);
	}
	
	@Override
    protected Object evaluateResult(List<Variable<C, PM>> iterators, String resultName, Object body) {
		EvaluationEnvironment<C, O, P, CLS, E> env = getEvalEnvironment();
		
		// check for undefined result:
		// the current result value cannot be false since the short-circuit
		// "isDone" mechanism below would have caused the evaluation to stop.
		// If the body result is undefined then the entire expression's value
		// is invalid
		if ((body == null) || (body == getInvalid())) {
			setDone(true);
			return getInvalid();
		}
		
		Boolean currVal = (Boolean)env.getValueOf(resultName);
		Boolean bodyVal = (Boolean)body;
		
		boolean resultVal = currVal.booleanValue() && bodyVal.booleanValue();
		if (!resultVal)
			setDone(true);
		return resultVal ? Boolean.TRUE : Boolean.FALSE;
	}
}
