/*******************************************************************************
 * Copyright (c) 2014, 2020 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.build.fragments;

import java.util.Set;

import org.eclipse.xtext.Grammar;
import org.eclipse.xtext.generator.BindFactory;
import org.eclipse.xtext.generator.Binding;
import org.eclipse.xtext.generator.DefaultGeneratorFragment;
import org.eclipse.xtext.service.GrammarProvider;

/**
 * Provide the Xtext 2.4+ *.xtextbin support for Xtext 2.3
 */
public class CompatibilityFragment extends DefaultGeneratorFragment
{
	@Override
	public Set<Binding> getGuiceBindingsRt(Grammar grammar) {
		BindFactory bindFactory = new BindFactory();
		bindFactory.addTypeToType(GrammarProvider.class.getName(), grammar.getName() + "GrammarResource.GrammarProvider");
		return bindFactory.getBindings();
	}
}