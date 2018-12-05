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
package org.eclipse.ocl.examples.impactanalyzer.benchmark.preparation.ocl;

import java.util.Collection;


/**
 * A {@link OCLExpressionPicker} provides a bunch of parsed OCL expressions
 * which can be used for benchmarking or testing.
 *
 * @author Manuel Holzleitner (D049667)
 */
public interface OCLExpressionPicker {
    public Collection<OCLExpressionWithContext> pickUpExpressions();
}
