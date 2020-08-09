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

import java.util.Set;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.ocl.xtext.base.cs2text.idioms.AbstractIdiomsProvider;
import org.eclipse.ocl.xtext.base.cs2text.idioms.IdiomsProvider;
import org.eclipse.xtend2.lib.StringConcatenationClient;
import org.eclipse.xtext.Grammar;
import org.eclipse.xtext.GrammarUtil;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xtext.generator.XtextGeneratorNaming;
import org.eclipse.xtext.xtext.generator.formatting.Formatter2Fragment2;
import org.eclipse.xtext.xtext.generator.model.FileAccessFactory;
import org.eclipse.xtext.xtext.generator.model.GuiceModuleAccess;
import org.eclipse.xtext.xtext.generator.model.JavaFileAccess;
import org.eclipse.xtext.xtext.generator.model.TypeReference;
import org.eclipse.xtext.xtext.generator.model.XtendFileAccess;

import com.google.common.collect.LinkedHashMultimap;
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
		final JavaFileAccess xtendFile = this.doGetIdiomsStubFile();
		if (xtendFile!=null) {
			xtendFile.writeTo(this.getProjectConfig().getRuntime().getSrc());
		}
	}

	protected JavaFileAccess doGetIdiomsStubFile() {
		Object _xblockexpression = null;
		{
			boolean _isGenerateStub = this.isGenerateStub();
			boolean _not = (!_isGenerateStub);
			if (_not) {
				return null;
			}
			boolean _isGenerateXtendStub = this.isGenerateXtendStub();
			if (_isGenerateXtendStub) {
				String _name = this.getClass().getName();
				String _plus = (_name + " has been configured to generate an Xtend stub, but that\'s not yet supported.");
				LOG.error(_plus);
			} else {
				TypeReference idiomsProviderStub = this.getIdiomsProviderClass(this.getGrammar());
				final JavaFileAccess javaFile = this.fileAccessFactory.createJavaFile(idiomsProviderStub);
				javaFile.setResourceSet(this.getLanguage().getResourceSet());
				final LinkedHashMultimap<EClass, EReference> type2ref = LinkedHashMultimap.<EClass, EReference>create();
				this.getLocallyAssignedContainmentReferences(this.getLanguage().getGrammar(), type2ref);
				final LinkedHashMultimap<EClass, EReference> inheritedTypes = LinkedHashMultimap.<EClass, EReference>create();
				this.getInheritedContainmentReferences(this.getLanguage().getGrammar(), inheritedTypes, CollectionLiterals.<Grammar>newHashSet());
				final Set<EClass> types = type2ref.keySet();
				StringConcatenationClient _client = doGetIdiomsProviderStubContent();;
				javaFile.setContent(_client);
				return javaFile;
			}
			_xblockexpression = null;
		}
		return ((JavaFileAccess)_xblockexpression);
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
		String _runtimeBasePackage = this.xtextGeneratorNaming.getRuntimeBasePackage(grammar);
		return (_runtimeBasePackage + ".formatting");
	}

	@Override
	protected TypeReference getFormatter2Stub(final Grammar grammar) {
		throw new UnsupportedOperationException();
	}

	protected TypeReference getIdiomsProviderClass(Grammar grammar) {
		return new TypeReference(getFormatterBasePackage(grammar), GrammarUtil.getSimpleName(grammar) + "IdiomsProvider");
	}

	protected TypeReference getIdiomsProviderSuperClass(Grammar grammar) {
		return new TypeReference(AbstractIdiomsProvider.class);
	}
}
