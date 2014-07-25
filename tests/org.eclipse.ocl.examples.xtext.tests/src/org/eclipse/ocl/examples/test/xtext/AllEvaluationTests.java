/*******************************************************************************
 * Copyright (c) 2002, 2012 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 *   E.D.Willink - Bug 298634
 *******************************************************************************/

package org.eclipse.ocl.examples.test.xtext;

import org.eclipse.ocl.examples.pivot.tests.EvaluateBooleanOperationsTest4;
import org.eclipse.ocl.examples.pivot.tests.EvaluateClassifierOperationsTest;
import org.eclipse.ocl.examples.pivot.tests.EvaluateCollectionOperationsTest4;
import org.eclipse.ocl.examples.pivot.tests.EvaluateConstructsTest4;
import org.eclipse.ocl.examples.pivot.tests.EvaluateModelOperationsTest4;
import org.eclipse.ocl.examples.pivot.tests.EvaluateNameVisibilityTest4;
import org.eclipse.ocl.examples.pivot.tests.EvaluateNumericOperationsTest4;
import org.eclipse.ocl.examples.pivot.tests.EvaluateOclAnyOperationsTest4;
import org.eclipse.ocl.examples.pivot.tests.EvaluateStringOperationsTest4;
import org.eclipse.ocl.examples.pivot.tests.EvaluateTupleOperationsTest4;
import org.eclipse.ocl.examples.pivot.tests.EvaluateUMLTest4;
import org.eclipse.ocl.examples.pivot.tests.IteratorsTest4;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Tests for the Pivot Evaluation.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
	EvaluateBooleanOperationsTest4.class,
	EvaluateClassifierOperationsTest.class,
	EvaluateCollectionOperationsTest4.class,
	EvaluateConstructsTest4.class,
	EvaluateModelOperationsTest4.class,
	EvaluateNameVisibilityTest4.class,
	EvaluateNumericOperationsTest4.class,
	EvaluateOclAnyOperationsTest4.class,
	EvaluateStringOperationsTest4.class,
	EvaluateTupleOperationsTest4.class,
	EvaluateUMLTest4.class,
	IteratorsTest4.class})

public class AllEvaluationTests {}
