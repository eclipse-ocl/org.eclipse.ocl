/*******************************************************************************
 * Copyright (c) 2017 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.internal.iterators;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.NamedElement;
import org.eclipse.ocl.pivot.TypedElement;
import org.eclipse.ocl.pivot.evaluation.EvaluationEnvironment;
import org.eclipse.ocl.pivot.evaluation.Executor.ExecutorExtension;
import org.eclipse.ocl.pivot.values.LazyCollectionValue;
import org.eclipse.ocl.pivot.values.LazyIterator;

/**
 * ContextIterator wraps a lazy collection evaluation with the prevailing context so that the evaluation uses the correct valyes of variables.
 *
 * @since 1.4
 */
public class ContextIterator extends AbstractLazyIterator
{
	protected final @NonNull ExecutorExtension executor;
	protected final @NonNull NamedElement executableObject;
	protected final /*@NonNull*/ TypedElement caller;
	protected final @NonNull LazyCollectionValue lazyValue;
	protected final @NonNull LazyIterator sourceIterator;
	protected final @NonNull Map<@NonNull TypedElement, @Nullable Object> variable2value = new HashMap<>();

	public ContextIterator(@NonNull ExecutorExtension executor, @NonNull NamedElement executableObject, /*@NonNull*/ TypedElement caller, @NonNull LazyCollectionValue lazyValue) {
		this.executor = executor;
		this.executableObject = executableObject;
		this.caller = caller;
		this.lazyValue = lazyValue;
		this.sourceIterator = lazyValue.lazyIterator();
		EvaluationEnvironment evaluationEnvironment = executor.getEvaluationEnvironment();
		for (@NonNull TypedElement variable : evaluationEnvironment.getVariables()) {
			variable2value.put(variable, evaluationEnvironment.getValueOf(variable));
		}
	}

	@Override
	public int getNextCount() {
		executor.pushEvaluationEnvironment(executableObject, caller);
		try {
			EvaluationEnvironment evaluationEnvironment = executor.getEvaluationEnvironment();
			for (@NonNull TypedElement variable : variable2value.keySet()) {
				evaluationEnvironment.add(variable, variable2value.get(variable));
			}
			int hasNextCount = sourceIterator.hasNextCount();
			if (hasNextCount > 0) {
				Object element = sourceIterator.next();
				return setNext(element, hasNextCount);
			}
			return 0;
		}
		finally {
			executor.popEvaluationEnvironment();
		}
	}

	@Override
	public @NonNull LazyIterator reIterator() {
		return new ContextIterator(executor, executableObject, caller, lazyValue);
	}

	@Override
	public void toString(@NonNull StringBuilder s, int sizeLimit) {
		s.append("Context{");
		s.append(sourceIterator);
		s.append("}");
	}
}
