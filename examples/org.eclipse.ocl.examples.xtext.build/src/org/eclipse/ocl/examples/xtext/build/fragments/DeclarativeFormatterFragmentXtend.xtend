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
package org.eclipse.ocl.examples.xtext.build.fragments;

import org.eclipse.xtext.xtext.generator.model.TypeReference
import org.eclipse.emf.ecore.resource.ResourceSet
import org.eclipse.jdt.annotation.NonNull
import org.eclipse.jdt.annotation.Nullable
import org.eclipse.ocl.examples.xtext.idioms.Idiom
import org.eclipse.ocl.examples.xtext.idioms.IdiomModel

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
			// «new TypeReference(NonNull)»
			// «new TypeReference(Nullable)»
					
			public class «getIdiomsProviderClass(grammar).simpleName» extends «getIdiomsProviderSuperClass(grammar)»
			{
				private static @Nullable Iterable<@NonNull «new TypeReference(Idiom)»> idioms = null;
			
				@Override
				public @NonNull Iterable<@NonNull «new TypeReference(Idiom)»> getIdioms(@NonNull «new TypeReference(ResourceSet)» resourceSet) {
					Iterable<@NonNull «new TypeReference(Idiom)»> idioms2 = idioms;
					if (idioms2 == null) {
						«new TypeReference(IdiomModel)» idiomModel = getIdiomModel(getClass(), resourceSet, "«getIdiomsPath(grammar)»");
						idioms = idioms2 = getIdioms(idiomModel);
					}
					return idioms2;
				}
			}
		'''
	}
}
