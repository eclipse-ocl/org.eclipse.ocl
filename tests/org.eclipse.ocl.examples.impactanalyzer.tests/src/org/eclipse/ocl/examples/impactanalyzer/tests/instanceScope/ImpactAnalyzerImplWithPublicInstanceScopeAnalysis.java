/*******************************************************************************
 * Copyright (c) 2010, 2018 SAP AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     SAP AG - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.impactanalyzer.tests.instanceScope;

import org.eclipse.ocl.ecore.OCLExpression;
import org.eclipse.ocl.examples.impactanalyzer.configuration.ActivationOption;
import org.eclipse.ocl.examples.impactanalyzer.impl.ImpactAnalyzerImpl;
import org.eclipse.ocl.examples.impactanalyzer.instanceScope.InstanceScopeAnalysis;
import org.eclipse.ocl.examples.impactanalyzer.util.OCLFactory;


public class ImpactAnalyzerImplWithPublicInstanceScopeAnalysis extends ImpactAnalyzerImpl {

    public ImpactAnalyzerImplWithPublicInstanceScopeAnalysis(OCLExpression expression,
            boolean notifyOnNewContextElements, ActivationOption configuration, OCLFactory oclFactory) {
        super(expression, notifyOnNewContextElements, configuration, oclFactory);
    }

    @Override
    public InstanceScopeAnalysis createInstanceScopeAnalysis() {
        return super.createInstanceScopeAnalysis();
    }
}
