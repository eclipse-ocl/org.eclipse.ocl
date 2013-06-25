/**
 * <copyright>
 *
 * Copyright (c) 2007, 2013 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: EvaluationVisitor.java,v 1.6 2011/05/07 16:41:08 ewillink Exp $
 */

package org.eclipse.ocl.examples.pivot.evaluation;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.domain.elements.DomainExpression;
import org.eclipse.ocl.examples.domain.elements.DomainStandardLibrary;
import org.eclipse.ocl.examples.domain.evaluation.DomainEvaluator;
import org.eclipse.ocl.examples.domain.evaluation.DomainModelManager;
import org.eclipse.ocl.examples.pivot.Environment;
import org.eclipse.ocl.examples.pivot.ExpressionInOCL;
import org.eclipse.ocl.examples.pivot.OCLExpression;
import org.eclipse.ocl.examples.pivot.manager.MetaModelManager;
import org.eclipse.ocl.examples.pivot.util.Visitor;

/**
 * A specialized visitor that is used for evaluation an
 * {@linkplain OCLExpression OCL expression} by walking its AST.
 * <p>
 * See the {@link Environment} class for a description of the
 * generic type parameters of this class. 
 * </p>
 * All instantiations of this evaluation visitor and all decorating or nested visitors are required to implement EV.
 */
public interface EvaluationVisitor<EV extends EvaluationVisitor<EV>> extends Visitor<Object>, DomainEvaluator
{
	@NonNull EV createNestedEvaluator();

	@Nullable Object evaluate(@NonNull DomainExpression body);

	@Nullable Object evaluate(@NonNull ExpressionInOCL expressionInOCL);
	
	/**
     * Obtains the environment that provides the metamodel semantics for the
     * expression to be evaluated.
     *  
	 * @return the environment
	 */
	Environment getEnvironment();

	/**
     * Obtains the evaluation environment that keeps track of variable values
     * and knows how to call operations, navigate properties, etc.
     * 
	 * @return the evaluation environment
	 */
	@NonNull EvaluationEnvironment getEvaluationEnvironment();

	@NonNull EV getEvaluator();
	
	/**
     * Obtains the mapping of model classes to their extents.
     * 
	 * @return the model manager
	 */
	@NonNull DomainModelManager getModelManager();

	@NonNull MetaModelManager getMetaModelManager();

	@NonNull DomainStandardLibrary getStandardLibrary();
    
    /**
     * Configures the specified decorated visitor to correctly handle the
     * invocation of recursive <code>visitXxx(...)</code> calls.  In particular,
     * the tail of a chain of decorators is informed (if it is an
     * {@link AbstractEvaluationVisitor} of the head decorator of the chain,
     * so that recursive visitation follows the entire decorator chain at
     * every step.
     * 
     * @param decorator the decorator that is not decorated/
     */
	void setUndecoratedVisitor(@NonNull EV evaluationVisitor);
}
