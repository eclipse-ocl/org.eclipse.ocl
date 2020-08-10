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

import org.apache.log4j.Logger;
import org.eclipse.ocl.xtext.base.cs2text.AbstractAnalysisProvider;
import org.eclipse.ocl.xtext.base.cs2text.DeclarativeSerializer;
import org.eclipse.xtend2.lib.StringConcatenationClient;
import org.eclipse.xtext.Grammar;
import org.eclipse.xtext.GrammarUtil;
import org.eclipse.xtext.serializer.ISerializer;
import org.eclipse.xtext.xtext.generator.IXtextGeneratorLanguage;
import org.eclipse.xtext.xtext.generator.XtextGeneratorNaming;
import org.eclipse.xtext.xtext.generator.model.FileAccessFactory;
import org.eclipse.xtext.xtext.generator.model.GuiceModuleAccess;
import org.eclipse.xtext.xtext.generator.model.JavaFileAccess;
import org.eclipse.xtext.xtext.generator.model.TypeReference;
import org.eclipse.xtext.xtext.generator.serializer.SerializerFragment2;

import com.google.inject.Inject;

/**
 * The DeclarativeSerializerFragment collaborates with the DeclarativeFormatterFragment to replace the
 * backtracking serializer and the Xtend-dependent formatting specifiation approach of the 'new infrastructure'
 * by a largely statically determined serializer and declarative idiom-based formatter.
 */
@SuppressWarnings("restriction")
public abstract class DeclarativeSerializerFragment extends SerializerFragment2
{
	private static final Logger LOG = Logger.getLogger(DeclarativeSerializerFragment.class);

	@Inject
	private XtextGeneratorNaming xtextGeneratorNaming;

	@Inject
	private  FileAccessFactory fileAccessFactory;

	protected abstract StringConcatenationClient doGetAnalysisProviderContent();

	protected void doGenerateAnalysisStubFile() {
		JavaFileAccess javaFile = this.doGetAnalysisStubFile();
		if (javaFile != null) {
			javaFile.setMarkedAsGenerated(true);		// FIXME There must be a smarter way
			javaFile.writeTo(this.getProjectConfig().getRuntime().getSrcGen());
		}
	}

	protected JavaFileAccess doGetAnalysisStubFile() {
		if (!isGenerateStub()) {
			return null;
		}
		if (isGenerateXtendStub()) {
			String name = getClass().getName();
			LOG.error(name + " has been configured to generate an Xtend stub, but that\'s not yet supported.");
			return null;
		}
		IXtextGeneratorLanguage language = getLanguage();
		Grammar grammar = getGrammar();
		TypeReference analysisProviderStub = getAnalysisProviderClass(grammar);
		JavaFileAccess javaFile = fileAccessFactory.createJavaFile(analysisProviderStub);
		javaFile.setResourceSet(language.getResourceSet());
		javaFile.setContent(doGetAnalysisProviderContent());
		return javaFile;
	}

	@Override
	public void generate() {
		Grammar grammar = this.getGrammar();
		GuiceModuleAccess.BindingFactory bindingFactory = new GuiceModuleAccess.BindingFactory();
		GuiceModuleAccess runtimeGenModule = this.getLanguage().getRuntimeGenModule();
		bindingFactory.addTypeToType(TypeReference.typeRef(ISerializer.class), TypeReference.typeRef(DeclarativeSerializer.class)).contributeTo(runtimeGenModule);
		bindingFactory.addTypeToType(TypeReference.typeRef(AbstractAnalysisProvider.class), getAnalysisProviderClass(grammar)).contributeTo(runtimeGenModule);
		doGenerateAnalysisStubFile();
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

	protected TypeReference getAnalysisProviderClass(Grammar grammar) {
		return new TypeReference(getSerializerBasePackage(grammar), GrammarUtil.getSimpleName(grammar) + "AnalysisProvider");
	}

	protected TypeReference getAnalysisProviderSuperClass(Grammar grammar) {
		return new TypeReference(AbstractAnalysisProvider.class);
	}

	@Override
	protected String getGrammarConstraintsPath(final Grammar grammar) {
		throw new UnsupportedOperationException();
	}

	@Override
	protected TypeReference getSemanticSequencerClass(final Grammar grammar) {
		throw new UnsupportedOperationException();
	}

	@Override
	protected String getSerializerBasePackage(final Grammar grammar) {
		return xtextGeneratorNaming.getRuntimeBasePackage(grammar) + ".serializer";
	}

	@Override
	protected TypeReference getSyntacticSequencerClass(final Grammar grammar) {
		throw new UnsupportedOperationException();
	}
}
