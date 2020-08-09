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

import org.eclipse.xtext.xtext.generator.model.TypeReference
import org.eclipse.ocl.xtext.base.cs2text.idioms.IdiomModel
import org.eclipse.ocl.xtext.base.cs2text.idioms.Idiom

/**
 * DeclarativeFormatterFragmentXtend augments DeclarativeFormatterFragment with M2T functionality
 * exploiting Xtend's string template capabilities.
 */
class DeclarativeFormatterFragmentXtend extends DeclarativeFormatterFragment
{
//	static val Logger LOG = Logger.getLogger(DeclarativeFormatterFragmentXtend)
	
//	@Inject FileAccessFactory fileAccessFactory
//	@Inject extension XtextGeneratorNaming
//	@Inject extension GrammarAccessExtensions

	protected override doGetIdiomsProviderStubContent() {
		'''
		public class «getIdiomsProviderClass(grammar).simpleName» extends «getIdiomsProviderSuperClass(grammar)»
		{
			private static Iterable<«new TypeReference(Idiom)»> idioms = null;
		
			@Override
			public Iterable<«new TypeReference(Idiom)»> getIdioms() {
				if (idioms == null) {
					«new TypeReference(IdiomModel)» idiomModel = getIdiomModel(getClass(), "«getIdiomsPath(grammar)»");
					idioms = getIdioms(idiomModel);
				}
				return idioms;
			}
		}
		'''
	}
}
