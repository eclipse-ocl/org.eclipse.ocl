/*******************************************************************************
 * Copyright (c) 2020 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.build.fragments;

/**
 * DeclarativeFormatterFragmentXtend augments DeclarativeFormatterFragment with M2T functionality
 * exploiting Xtend'sstring template capabilities.
 */
class DeclarativeFormatterFragmentXtend extends DeclarativeFormatterFragment
{
//	static val Logger LOG = Logger.getLogger(DeclarativeFormatterFragmentXtend)
	
//	@Inject FileAccessFactory fileAccessFactory
//	@Inject extension XtextGeneratorNaming
//	@Inject extension GrammarAccessExtensions

	protected override doGetIdiomsProviderStubContent() {
		'''
				public class «getIdiomsProviderClass(grammar).simpleName» extends «getIdiomsProviderSuperClass(grammar)» {
				}
			'''
	}
}
