/*******************************************************************************
 * Copyright (c) 2015, 2021 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.test.xtext;

import org.eclipse.ocl.examples.xtext.tests.TestUtil;
import org.eclipse.ocl.xtext.base.BaseGrammarResource;
import org.eclipse.ocl.xtext.completeocl.CompleteOCLGrammarResource;
import org.eclipse.ocl.xtext.essentialocl.EssentialOCLGrammarResource;
import org.eclipse.ocl.xtext.markup.MarkupGrammarResource;
import org.eclipse.ocl.xtext.oclinecore.OCLinEcoreGrammarResource;
import org.eclipse.ocl.xtext.oclstdlib.OCLstdlibGrammarResource;

/**
 * Tests.
 */
public class GrammarTests extends AbstractGrammarTests
{
	@Override
	protected void setUp() throws Exception {
		TestUtil.doMarkupSetup();
		super.setUp();
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	/**
	 * Checks that the local *.xtextbin is the same as the pre-compiled Java implementation.
	 *
	 * FIXME check the library/model version instead.
	 */
	public void testGrammar_Base() throws Exception {
		doTestGrammar(BaseGrammarResource.class, "Base.xtextbin", BaseGrammarResource.INSTANCE);
	}

	public void testGrammar_EssentialOCL() throws Exception {
		doTestGrammar(EssentialOCLGrammarResource.class, "EssentialOCL.xtextbin", EssentialOCLGrammarResource.INSTANCE);
	}

	public void testGrammar_Markup() throws Exception {
		doTestGrammar(MarkupGrammarResource.class, "Markup.xtextbin", MarkupGrammarResource.INSTANCE);
	}

	public void testGrammar_OCLinEcore() throws Exception {
		doTestGrammar(OCLinEcoreGrammarResource.class, "OCLinEcore.xtextbin", OCLinEcoreGrammarResource.INSTANCE);
	}

	public void testGrammar_CompleteOCL() throws Exception {
		doTestGrammar(CompleteOCLGrammarResource.class, "CompleteOCL.xtextbin", CompleteOCLGrammarResource.INSTANCE);
	}

	public void testGrammar_OCLstdlib() throws Exception {
		doTestGrammar(OCLstdlibGrammarResource.class, "OCLstdlib.xtextbin", OCLstdlibGrammarResource.INSTANCE);
	}
}
