/*******************************************************************************
 * Copyright (c) 2010, 2018 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *  C.Damus, K.Hussey, E.D.Willink - Initial API and implementation
 * 	E.D.Willink - Bug 306079, 322159
 *  K.Hussey - Bug 331143
 *******************************************************************************/
package org.eclipse.ocl.examples.pivot.tests;

import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.jdt.annotation.NonNull;

/**
 * Tests for the OCL delegate implementations.
 */
public class MoreDelegatesTest extends DelegatesTest
{
	// Passes in isolation; probably an adapter clean-up problem
	public void test_eAttributeDerivation_registered() throws Exception {
		EagerDelegatesOCLTestThread delegatesThread = new EagerDelegatesOCLTestThread(getTestName())
		{
			@Override
			public void runWithModel(@NonNull ResourceSet resourceSet) throws Exception {
				initPackageRegistrations(resourceSet);
				doTest_eAttributeDerivation(resourceSet, COMPANY_XMI);
			}
		};
		delegatesThread.syncExec();
	}

	// Passes in isolation; probably an adapter clean-up problem
	public void test_invariantValidation_withoutReflection_registered() throws Exception {
		EagerDelegatesOCLTestThread delegatesThread = new EagerDelegatesOCLTestThread(getTestName())
		{
			@Override
			public void runWithModel(@NonNull ResourceSet resourceSet) throws Exception {
				initPackageRegistrations(resourceSet);
				doTest_invariantValidation(resourceSet, NO_REFLECTION_COMPANY_XMI, true, Diagnostic.ERROR);
			}
		};
		delegatesThread.syncExec();
	}

	// Passes in isolation; probably an adapter clean-up problem
	public void test_operationInvocation_registered() throws Exception {
		EagerDelegatesOCLTestThread delegatesThread = new EagerDelegatesOCLTestThread(getTestName())
		{
			@Override
			public void runWithModel(@NonNull ResourceSet resourceSet) throws Exception {
				initPackageRegistrations(resourceSet);
				doTest_operationInvocation(resourceSet, COMPANY_XMI);
				assertFalse(usedLocalRegistry);
			}
		};
		delegatesThread.syncExec();
	}
}
