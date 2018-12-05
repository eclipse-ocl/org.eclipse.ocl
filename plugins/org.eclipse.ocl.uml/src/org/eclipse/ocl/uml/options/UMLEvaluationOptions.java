/*******************************************************************************
 * Copyright (c) 2007, 2018 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 *******************************************************************************/

package org.eclipse.ocl.uml.options;

import org.eclipse.ocl.options.Customizable;
import org.eclipse.ocl.options.EnumeratedOption;
import org.eclipse.ocl.options.Option;
import org.eclipse.ocl.uml.UMLEvaluationEnvironment;
import org.eclipse.ocl.uml.util.OCLUMLUtil;


/**
 * Options applicable to the {@link UMLEvaluationEnvironment} to
 * {@linkplain Customizable customize} its evaluation behaviour.
 * 
 * @author Christian W. Damus (cdamus)
 * 
 * @since 1.2
 */
public class UMLEvaluationOptions {

    /**
     * <p>
     * Evaluation mode option determining whether we are working with M1
     * instance models ({@link EvaluationMode#INSTANCE_MODEL}, M0 objects
     * ({@link EvaluationMode#RUNTIME_OBJECTS}), or indeterminate, in which
     * last case we guess on-the-fly ({@link EvaluationMode#ADAPTIVE}).
     * </p><p>
     * For compatibility with the 1.1 release behaviour, the default value of
     * this option is {@link EvaluationMode#ADAPTIVE}.
     * </p>
     */
    public static final Option<EvaluationMode> EVALUATION_MODE = new EnumeratedOption<EvaluationMode>(
        OCLUMLUtil.PLUGIN_ID, "uml.evaluation.mode", EvaluationMode.ADAPTIVE, EvaluationMode.class); //$NON-NLS-1$

    /**
     * Not instantiable by clients.
     */
    private UMLEvaluationOptions() {
        super();
    }

}
