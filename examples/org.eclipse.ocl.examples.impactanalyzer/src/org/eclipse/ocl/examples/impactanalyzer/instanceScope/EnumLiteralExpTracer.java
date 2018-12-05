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

import java.util.Stack;

import org.eclipse.ocl.ecore.EnumLiteralExp;
import org.eclipse.ocl.examples.impactanalyzer.util.OCLFactory;


public class EnumLiteralExpTracer extends AbstractTracer<EnumLiteralExp> {
    public EnumLiteralExpTracer(EnumLiteralExp expression, Stack<String> tuplePartNames, OCLFactory oclFactory) {
	super(expression, tuplePartNames, oclFactory);
    }

}
