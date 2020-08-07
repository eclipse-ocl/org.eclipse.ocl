/*******************************************************************************
 * Copyright (c) 2015, 2018 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.build.fragments;

import org.apache.log4j.Logger;
import org.eclipse.ocl.xtext.base.cs2text.DeclarativeSerializer;
import org.eclipse.xtext.Grammar;
import org.eclipse.xtext.GrammarUtil;
import org.eclipse.xtext.serializer.ISerializer;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xtext.generator.XtextGeneratorNaming;
import org.eclipse.xtext.xtext.generator.model.GuiceModuleAccess;
import org.eclipse.xtext.xtext.generator.model.TypeReference;
import org.eclipse.xtext.xtext.generator.serializer.SerializerFragment2;

import com.google.inject.Inject;

@SuppressWarnings("all")
public class DeclarativeSerializerFragment extends SerializerFragment2
{
	private static final Logger LOG = Logger.getLogger(DeclarativeSerializerFragment.class);

	@Inject
	@Extension
	private XtextGeneratorNaming _xtextGeneratorNaming;

	@Override
	public void generate() {
		new GuiceModuleAccess.BindingFactory().addTypeToType(TypeReference.typeRef(ISerializer.class), TypeReference.typeRef(DeclarativeSerializer.class)).contributeTo(this.getLanguage().getRuntimeGenModule());
	}

	@Override
	protected void generateAbstractSemanticSequencer() {
		throw new UnsupportedOperationException();
	}

	@Override
	protected void generateAbstractSyntacticSequencer() {
		throw new UnsupportedOperationException();
	}

	@Override
	protected void generateGrammarConstraints() {
		throw new UnsupportedOperationException();
	}

	@Override
	protected void generateSemanticSequencer() {
		throw new UnsupportedOperationException();
	}

	@Override
	protected void generateSyntacticSequencer() {
		throw new UnsupportedOperationException();
	}

	@Override
	protected TypeReference getAbstractSemanticSequencerClass(final Grammar grammar) {
		throw new UnsupportedOperationException();
	}

	@Override
	protected TypeReference getAbstractSyntacticSequencerClass(final Grammar grammar) {
		throw new UnsupportedOperationException();
	}

	@Override
	protected String getGrammarConstraintsPath(final Grammar grammar) {
		String _replace = this.getSerializerBasePackage(grammar).replace(".", "/");
		String _plus = (_replace + "/");
		String _simpleName = GrammarUtil.getSimpleName(grammar);
		String _plus_1 = (_plus + _simpleName);
		return (_plus_1 + "GrammarConstraints.txt");
	}

	@Override
	protected TypeReference getSemanticSequencerClass(final Grammar grammar) {
		throw new UnsupportedOperationException();
	}

	@Override
	protected String getSerializerBasePackage(final Grammar grammar) {
		String _runtimeBasePackage = this._xtextGeneratorNaming.getRuntimeBasePackage(grammar);
		return (_runtimeBasePackage + ".serializer");
	}

	@Override
	protected TypeReference getSyntacticSequencerClass(final Grammar grammar) {
		throw new UnsupportedOperationException();
	}
}
