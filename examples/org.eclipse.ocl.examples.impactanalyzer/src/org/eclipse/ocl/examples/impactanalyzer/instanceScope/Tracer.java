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
package org.eclipse.ocl.examples.impactanalyzer.instanceScope;

import java.util.Set;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.ocl.ecore.OCLExpression;
import org.eclipse.ocl.examples.impactanalyzer.impl.OperationBodyToCallMapper;




/**
 * All implementations must offer a constructor that takes a {@link OCLFactory} and a {@link OCLExpression} impl
 * subclass of the type handled by them as argument. Furthermore, the implementing classes underly a naming convention.
 * If they handle an {@link OCLExpression} type by the MOF name of <tt>X</tt> then the tracer implementation class name
 * must be <tt>XTracer</tt>.
 * 
 */
public interface Tracer {
    /**
     * Computes a navigation step that when executed, computes a set of elements containing at least all context elements such
     * that when the overall expression of which the expression represented by this tracer is a part, is evaluated for such an
     * element, the sub-expression represented by this tracer evaluates to the element passed to the navigation step's
     * {@link NavigationStep#navigate(Set, TracebackCache, Notification)} operation in the set parameter. The result may also contain elements for which
     * this does not hold. It hence represents a conservative estimate.
     * 
     * @param context
     *            the context type that defines the type of any <tt>self</tt> occurrence outside of operation bodies
     * @param pathCache
     *            a global cache that remembers the navigation steps already computed for some OCL expressions
     * @param filterSynthesizer
     *            the filter synthesizer that analyzed an overall expression that contains the expression to be handled by this
     *            tracer
     */
    NavigationStep traceback(EClass context, PathCache pathCache, OperationBodyToCallMapper filterSynthesizer);
}
