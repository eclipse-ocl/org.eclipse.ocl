/*******************************************************************************
 * Copyright (c) 2014, 2018 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.debug.stepper;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.debug.vm.evaluator.VMEvaluationStepper;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.IterateExp;
import org.eclipse.ocl.pivot.IteratorExp;
import org.eclipse.ocl.pivot.LoopExp;
import org.eclipse.ocl.pivot.OCLExpression;
import org.eclipse.ocl.pivot.Variable;

public class LoopExpStepper extends CallExpStepper
{
	public static @NonNull LoopExpStepper INSTANCE = new LoopExpStepper();

	@Override
	public @Nullable Element isPostStoppable(@NonNull VMEvaluationStepper vmEvaluationVisitor, @NonNull Element childElement, @Nullable Object result) {
		EObject parentElement = childElement.eContainer();
		if (parentElement instanceof Variable) {
			parentElement = parentElement.eContainer();
		}
		if (parentElement instanceof IteratorExp) {
			IteratorExp iteratorExp = (IteratorExp)parentElement;
			vmEvaluationVisitor.postIterate(iteratorExp);
			OCLExpression body = iteratorExp.getOwnedBody();
			if (body != null) {
				return getFirstElement(vmEvaluationVisitor, body);
			}
		}
		else if (parentElement instanceof IterateExp) {
			IterateExp iterateExp = (IterateExp)parentElement;
			vmEvaluationVisitor.postIterate(iterateExp);
			OCLExpression body = iterateExp.getOwnedBodies().get(0);			// XXX sequence bodies
			if (body != null) {
				return getFirstElement(vmEvaluationVisitor, body);
			}
		}
		return super.isPostStoppable(vmEvaluationVisitor, childElement, result);
	}

	@Override
	public boolean isPreStoppable(@NonNull VMEvaluationStepper rootVMEvaluationVisitor, @NonNull Element element) {
		rootVMEvaluationVisitor.preIterate((LoopExp)element);
		return super.isPreStoppable(rootVMEvaluationVisitor, element);
	}
}
