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
import org.eclipse.ocl.xtext.base.cs2text.AbstractIdiomsProvider;
import org.eclipse.ocl.xtext.base.cs2text.IdiomsProvider;
import org.eclipse.xtend2.lib.StringConcatenationClient;
import org.eclipse.xtext.Grammar;
import org.eclipse.xtext.GrammarUtil;
import org.eclipse.xtext.xtext.generator.IXtextGeneratorLanguage;
import org.eclipse.xtext.xtext.generator.XtextGeneratorNaming;
import org.eclipse.xtext.xtext.generator.formatting.Formatter2Fragment2;
import org.eclipse.xtext.xtext.generator.model.FileAccessFactory;
import org.eclipse.xtext.xtext.generator.model.GuiceModuleAccess;
import org.eclipse.xtext.xtext.generator.model.JavaFileAccess;
import org.eclipse.xtext.xtext.generator.model.TypeReference;
import org.eclipse.xtext.xtext.generator.model.XtendFileAccess;

import com.google.inject.Inject;

/**
 * The DeclarativeFormatterFragment collaborates with the DeclarativeSerializerFragment to replace the
 * backtracking serializer and the Xtend-dependent formatting specifiation approach of the 'new infrastructure'
 * by a largely statically determined serializer and declarative idiom-based formatter.
 */
@SuppressWarnings("restriction")
public abstract class DeclarativeFormatterFragment extends Formatter2Fragment2
{
	private static final Logger LOG = Logger.getLogger(DeclarativeFormatterFragment.class);

	@Inject
	private XtextGeneratorNaming xtextGeneratorNaming;

	@Inject
	private  FileAccessFactory fileAccessFactory;

	protected void doGenerateIdiomsStubFile() {
		JavaFileAccess javaFile = this.doGetIdiomsStubFile();
		if (javaFile != null) {
			javaFile.setMarkedAsGenerated(true);		// FIXME There must be a smarter way
			javaFile.writeTo(this.getProjectConfig().getRuntime().getSrcGen());
		}
	}

	protected JavaFileAccess doGetIdiomsStubFile() {
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
		TypeReference idiomsProviderStub = getIdiomsProviderClass(grammar);
		JavaFileAccess javaFile = fileAccessFactory.createJavaFile(idiomsProviderStub);
		javaFile.setResourceSet(language.getResourceSet());
		javaFile.setContent(doGetIdiomsProviderStubContent());
		return javaFile;
	}

	protected abstract StringConcatenationClient doGetIdiomsProviderStubContent();

	@Override
	public void generate() {
		Grammar grammar = this.getGrammar();
		GuiceModuleAccess.BindingFactory bindingFactory = new GuiceModuleAccess.BindingFactory();
		GuiceModuleAccess runtimeGenModule = this.getLanguage().getRuntimeGenModule();
		bindingFactory.addTypeToType(TypeReference.typeRef(IdiomsProvider.class), getIdiomsProviderClass(grammar)).contributeTo(runtimeGenModule);
		doGenerateIdiomsStubFile();
	}

	@Override
	protected void doGenerateStubFile() {
		throw new UnsupportedOperationException();
	}

	@Override
	protected XtendFileAccess doGetXtendStubFile() {
		throw new UnsupportedOperationException();
	}

	protected String getFormatterBasePackage(final Grammar grammar) {
		return xtextGeneratorNaming.getRuntimeBasePackage(grammar) + ".formatting";
	}

	@Override
	protected TypeReference getFormatter2Stub(final Grammar grammar) {
		throw new UnsupportedOperationException();
	}

	protected String getIdiomsPath(Grammar grammar) {
		return "/" + grammar.getName().replace('.', '/') + ".idioms";
	}

	protected TypeReference getIdiomsProviderClass(Grammar grammar) {
		return new TypeReference(getFormatterBasePackage(grammar), GrammarUtil.getSimpleName(grammar) + "IdiomsProvider");
	}

	protected TypeReference getIdiomsProviderSuperClass(Grammar grammar) {
		return new TypeReference(AbstractIdiomsProvider.class);
	}
}
