/*******************************************************************************
 * Copyright (c) 2014, 2018 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * 
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.debug.ui.delegate;

import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.Variable;
import org.eclipse.ocl.pivot.evaluation.EvaluationEnvironment;
import org.eclipse.ocl.pivot.ids.IdResolver;
import org.eclipse.ocl.pivot.internal.delegate.OCLDelegateDomain;
import org.eclipse.ocl.pivot.internal.delegate.OCLInvocationDelegate;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.OCL;
import org.eclipse.ocl.pivot.utilities.Query;

/**
 * An implementation of an operation-invocation delegate for OCL body expressions.
 */
public class OCLDebugInvocationDelegate extends OCLInvocationDelegate
{
	public OCLDebugInvocationDelegate(@NonNull OCLDelegateDomain delegateDomain, @NonNull EOperation operation) {
		super(delegateDomain, operation);
	}

	protected @Nullable Object evaluate(@NonNull OCL ocl, @NonNull ExpressionInOCL query, InternalEObject target, EList<?> arguments) {
		IdResolver idResolver = ocl.getIdResolver();
		Query query2 = ocl.createQuery(query);
		EvaluationEnvironment env = query2.getEvaluationEnvironment(target);
		Object object = target;
		Object value = idResolver.boxedValueOf(target);
		env.add(ClassUtil.nonNullModel(query.getOwnedContext()), value);
		List<Variable> parms = query.getOwnedParameters();
		if (!parms.isEmpty()) {
			// bind arguments to parameter names
			for (int i = 0; i < parms.size(); i++) {
				object = arguments.get(i);
				value = idResolver.boxedValueOf(object);
				env.add(ClassUtil.nonNullModel(parms.get(i)), value);
			}
		}
		Object ecoreResult = query2.evaluateEcore(eOperation.getEType().getInstanceClass(), target);
		return ecoreResult;
	}
}
