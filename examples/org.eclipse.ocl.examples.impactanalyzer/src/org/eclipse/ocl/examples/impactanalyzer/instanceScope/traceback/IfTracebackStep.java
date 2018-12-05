/*******************************************************************************
 * Copyright (c) 2009, 2018 SAP AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * 
 * Contributors:
 *     SAP AG - initial API and implementation
 ******************************************************************************/
package org.eclipse.ocl.examples.impactanalyzer.instanceScope.traceback;

import java.util.Stack;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.ocl.ecore.IfExp;
import org.eclipse.ocl.ecore.OCLExpression;
import org.eclipse.ocl.examples.impactanalyzer.impl.OperationBodyToCallMapper;
import org.eclipse.ocl.examples.impactanalyzer.instanceScope.unusedEvaluation.UnusedEvaluationRequestFactory;
import org.eclipse.ocl.examples.impactanalyzer.util.AnnotatedEObject;
import org.eclipse.ocl.examples.impactanalyzer.util.OCLFactory;


/**
 * When a {@link IfExp} is traced back, it calls the {@link TracebackStep#traceback(AnnotatedEObject, UnusedEvaluationRequestSet, TracebackCache, Notification)}
 * function for its then-expression and its else-expression, forwarding the <code>source</code> object, the (possibly
 * modified) <code>pendingUnusedEvalRequests</code> and the <code>tracebackCache</code>.
 * 
 * @see AbstractTracebackStep#performSubsequentTraceback(AnnotatedEObject, UnusedEvaluationRequestSet, TracebackCache, Notification)
 */
public class IfTracebackStep extends BranchingTracebackStep<IfExp> {
    public IfTracebackStep(IfExp sourceExpression, EClass context, OperationBodyToCallMapper operationBodyToCallMapper,
            Stack<String> tupleLiteralNamesToLookFor, TracebackStepCache tracebackStepCache, UnusedEvaluationRequestFactory unusedEvaluationRequestFactory, OCLFactory oclFactory) {
        super(sourceExpression, tupleLiteralNamesToLookFor, tracebackStepCache.getOppositeEndFinder(), operationBodyToCallMapper, unusedEvaluationRequestFactory, oclFactory);
        OCLExpression thenExpression = (OCLExpression) sourceExpression.getThenExpression();
        getSteps().add(createTracebackStepAndScopeChange(sourceExpression, thenExpression, context, operationBodyToCallMapper,
                tupleLiteralNamesToLookFor, tracebackStepCache));
        OCLExpression elseExpression = (OCLExpression) sourceExpression.getElseExpression();
        getSteps().add(createTracebackStepAndScopeChange(sourceExpression, elseExpression, context, operationBodyToCallMapper,
                tupleLiteralNamesToLookFor, tracebackStepCache));
    }
}
