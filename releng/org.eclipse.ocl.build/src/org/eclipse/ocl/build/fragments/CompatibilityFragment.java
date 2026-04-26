/*******************************************************************************
 * Copyright (c) 2014, 2023 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.build.fragments;

import org.eclipse.xtext.Grammar;
import org.eclipse.xtext.service.GrammarProvider;
import org.eclipse.xtext.xtext.generator.AbstractXtextGeneratorFragment;
import org.eclipse.xtext.xtext.generator.IXtextGeneratorLanguage;
import org.eclipse.xtext.xtext.generator.model.GuiceModuleAccess;
import org.eclipse.xtext.xtext.generator.model.TypeReference;

import com.google.inject.Inject;

/**
 * Provide the Xtext 2.4+ *.xtextbin support for Xtext 2.3
 */
public class CompatibilityFragment extends AbstractXtextGeneratorFragment
{
	@Inject
	private Grammar grammar;
	@Inject
	private IXtextGeneratorLanguage language;

	@Override
	public void generate() {
		GuiceModuleAccess.BindingFactory bindFactory = new GuiceModuleAccess.BindingFactory();
		String qualifiedKeyName = GrammarProvider.class.getName();
		String grammarName = grammar.getName();
		int i = grammarName.lastIndexOf('.');
		String valuePackageName = grammarName.substring(0, i);
		String valueClassName = grammarName.substring(i+1) + "GrammarResource.GrammarProvider";
		TypeReference keyType = new TypeReference(qualifiedKeyName);
		TypeReference valueType = new TypeReference(valuePackageName, valueClassName);
		bindFactory.addTypeToType(keyType, valueType);
		bindFactory.contributeTo(language.getRuntimeGenModule());
	}
}