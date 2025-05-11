/*******************************************************************************
 * Copyright (c) 2011, 2025 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.xtext.essentialocl;

import com.google.inject.Binder;
import com.google.inject.Provider;
import com.google.inject.name.Names;
import java.util.Properties;
import org.eclipse.ocl.xtext.base.cs2as.BaseFragmentProvider;
import org.eclipse.ocl.xtext.base.serializer.BaseCrossReferenceSerializer;
import org.eclipse.ocl.xtext.base.serializer.BaseHiddenTokenSequencer;
import org.eclipse.ocl.xtext.base.serializer.DeclarativeFormatter;
import org.eclipse.ocl.xtext.base.serializer.DeclarativeSerializer;
import org.eclipse.ocl.xtext.base.serializer.SerializationMetaData;
import org.eclipse.ocl.xtext.base.services.BaseLinkingDiagnosticMessageProvider;
import org.eclipse.ocl.xtext.base.services.BaseLinkingService;
import org.eclipse.ocl.xtext.base.services.BaseQualifiedNameConverter;
import org.eclipse.ocl.xtext.base.services.BaseQualifiedNameProvider;
import org.eclipse.ocl.xtext.base.services.BaseValueConverterService;
import org.eclipse.ocl.xtext.base.services.PivotResourceServiceProvider;
import org.eclipse.ocl.xtext.base.utilities.CS2ASLinker;
import org.eclipse.ocl.xtext.base.utilities.PivotDiagnosticConverter;
import org.eclipse.ocl.xtext.base.utilities.PivotResourceValidator;
import org.eclipse.ocl.xtext.essentialocl.parser.antlr.EssentialOCLAntlrTokenFileProvider;
import org.eclipse.ocl.xtext.essentialocl.parser.antlr.EssentialOCLParser;
import org.eclipse.ocl.xtext.essentialocl.parser.antlr.internal.InternalEssentialOCLLexer;
import org.eclipse.ocl.xtext.essentialocl.scoping.EssentialOCLScopeProvider;
import org.eclipse.ocl.xtext.essentialocl.serializer.EssentialOCLSerializationMetaData;
import org.eclipse.ocl.xtext.essentialocl.services.EssentialOCLGrammarAccess;
import org.eclipse.ocl.xtext.essentialocl.utilities.EssentialOCLCSResource;
import org.eclipse.ocl.xtext.essentialocl.validation.EssentialOCLValidator;
import org.eclipse.xtext.Constants;
import org.eclipse.xtext.IGrammarAccess;
import org.eclipse.xtext.conversion.IValueConverterService;
import org.eclipse.xtext.formatting.INodeModelFormatter;
import org.eclipse.xtext.linking.ILinker;
import org.eclipse.xtext.linking.ILinkingDiagnosticMessageProvider;
import org.eclipse.xtext.linking.ILinkingService;
import org.eclipse.xtext.naming.IQualifiedNameConverter;
import org.eclipse.xtext.naming.IQualifiedNameProvider;
import org.eclipse.xtext.parser.IParser;
import org.eclipse.xtext.parser.ITokenToStringConverter;
import org.eclipse.xtext.parser.antlr.AntlrTokenDefProvider;
import org.eclipse.xtext.parser.antlr.AntlrTokenToStringConverter;
import org.eclipse.xtext.parser.antlr.IAntlrTokenFileProvider;
import org.eclipse.xtext.parser.antlr.ITokenDefProvider;
import org.eclipse.xtext.parser.antlr.Lexer;
import org.eclipse.xtext.parser.antlr.LexerBindings;
import org.eclipse.xtext.parser.antlr.LexerProvider;
import org.eclipse.xtext.resource.IContainer;
import org.eclipse.xtext.resource.IFragmentProvider;
import org.eclipse.xtext.resource.IResourceDescriptions;
import org.eclipse.xtext.resource.IResourceServiceProvider;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.resource.containers.IAllContainersState;
import org.eclipse.xtext.resource.containers.ResourceSetBasedAllContainersStateProvider;
import org.eclipse.xtext.resource.containers.StateBasedContainerManager;
import org.eclipse.xtext.resource.impl.ResourceDescriptionsProvider;
import org.eclipse.xtext.resource.impl.ResourceSetBasedResourceDescriptions;
import org.eclipse.xtext.scoping.IGlobalScopeProvider;
import org.eclipse.xtext.scoping.IScopeProvider;
import org.eclipse.xtext.scoping.IgnoreCaseLinking;
import org.eclipse.xtext.scoping.impl.AbstractDeclarativeScopeProvider;
import org.eclipse.xtext.scoping.impl.DefaultGlobalScopeProvider;
import org.eclipse.xtext.scoping.impl.ImportedNamespaceAwareLocalScopeProvider;
import org.eclipse.xtext.serializer.ISerializer;
import org.eclipse.xtext.serializer.sequencer.IHiddenTokenSequencer;
import org.eclipse.xtext.serializer.tokens.ICrossReferenceSerializer;
import org.eclipse.xtext.service.DefaultRuntimeModule;
import org.eclipse.xtext.service.GrammarProvider;
import org.eclipse.xtext.service.SingletonBinding;
import org.eclipse.xtext.validation.IDiagnosticConverter;
import org.eclipse.xtext.validation.IResourceValidator;

/**
 * Manual modifications go to {@link EssentialOCLRuntimeModule}.
 */
@SuppressWarnings("all")
public abstract class AbstractEssentialOCLRuntimeModule extends DefaultRuntimeModule {

	protected Properties properties = null;

	@Override
	public void configure(Binder binder) {
		properties = tryBindProperties(binder, "org/eclipse/ocl/xtext/essentialocl/EssentialOCL.properties");
		super.configure(binder);
	}

	public void configureLanguageName(Binder binder) {
		binder.bind(String.class).annotatedWith(Names.named(Constants.LANGUAGE_NAME)).toInstance("org.eclipse.ocl.xtext.essentialocl.EssentialOCL");
	}

	public void configureFileExtensions(Binder binder) {
		if (properties == null || properties.getProperty(Constants.FILE_EXTENSIONS) == null)
			binder.bind(String.class).annotatedWith(Names.named(Constants.FILE_EXTENSIONS)).toInstance("essentialocl");
	}

	// contributed by org.eclipse.xtext.xtext.generator.grammarAccess.GrammarAccessFragment2
	public ClassLoader bindClassLoaderToInstance() {
		return getClass().getClassLoader();
	}

	// contributed by org.eclipse.xtext.xtext.generator.grammarAccess.GrammarAccessFragment2
	public Class<? extends IGrammarAccess> bindIGrammarAccess() {
		return EssentialOCLGrammarAccess.class;
	}

	// contributed by org.eclipse.ocl.build.fragments.DeclarativeSerializerFragment
	public Class<? extends INodeModelFormatter> bindINodeModelFormatter() {
		return DeclarativeFormatter.class;
	}

	// contributed by org.eclipse.ocl.build.fragments.DeclarativeSerializerFragment
	public Class<? extends ISerializer> bindISerializer() {
		return DeclarativeSerializer.class;
	}

	// contributed by org.eclipse.ocl.build.fragments.DeclarativeSerializerFragment
	public Class<? extends SerializationMetaData.Provider> bindSerializationMetaData$Provider() {
		return EssentialOCLSerializationMetaData.Provider.class;
	}

	// contributed by org.eclipse.xtext.xtext.generator.parser.antlr.XtextAntlrGeneratorFragment2
	public Class<? extends IParser> bindIParser() {
		return EssentialOCLParser.class;
	}

	// contributed by org.eclipse.xtext.xtext.generator.parser.antlr.XtextAntlrGeneratorFragment2
	public Class<? extends ITokenToStringConverter> bindITokenToStringConverter() {
		return AntlrTokenToStringConverter.class;
	}

	// contributed by org.eclipse.xtext.xtext.generator.parser.antlr.XtextAntlrGeneratorFragment2
	public Class<? extends IAntlrTokenFileProvider> bindIAntlrTokenFileProvider() {
		return EssentialOCLAntlrTokenFileProvider.class;
	}

	// contributed by org.eclipse.xtext.xtext.generator.parser.antlr.XtextAntlrGeneratorFragment2
	public Class<? extends Lexer> bindLexer() {
		return InternalEssentialOCLLexer.class;
	}

	// contributed by org.eclipse.xtext.xtext.generator.parser.antlr.XtextAntlrGeneratorFragment2
	public Class<? extends ITokenDefProvider> bindITokenDefProvider() {
		return AntlrTokenDefProvider.class;
	}

	// contributed by org.eclipse.xtext.xtext.generator.parser.antlr.XtextAntlrGeneratorFragment2
	public Provider<? extends InternalEssentialOCLLexer> provideInternalEssentialOCLLexer() {
		return LexerProvider.create(InternalEssentialOCLLexer.class);
	}

	// contributed by org.eclipse.xtext.xtext.generator.parser.antlr.XtextAntlrGeneratorFragment2
	public void configureRuntimeLexer(Binder binder) {
		binder.bind(Lexer.class)
			.annotatedWith(Names.named(LexerBindings.RUNTIME))
			.to(InternalEssentialOCLLexer.class);
	}

	// contributed by org.eclipse.xtext.xtext.generator.validation.ValidatorFragment2
	@SingletonBinding(eager=true)
	public Class<? extends EssentialOCLValidator> bindEssentialOCLValidator() {
		return EssentialOCLValidator.class;
	}

	// contributed by org.eclipse.xtext.xtext.generator.scoping.ImportNamespacesScopingFragment2
	public Class<? extends IScopeProvider> bindIScopeProvider() {
		return EssentialOCLScopeProvider.class;
	}

	// contributed by org.eclipse.xtext.xtext.generator.scoping.ImportNamespacesScopingFragment2
	public void configureIScopeProviderDelegate(Binder binder) {
		binder.bind(IScopeProvider.class).annotatedWith(Names.named(AbstractDeclarativeScopeProvider.NAMED_DELEGATE)).to(ImportedNamespaceAwareLocalScopeProvider.class);
	}

	// contributed by org.eclipse.xtext.xtext.generator.scoping.ImportNamespacesScopingFragment2
	public Class<? extends IGlobalScopeProvider> bindIGlobalScopeProvider() {
		return DefaultGlobalScopeProvider.class;
	}

	// contributed by org.eclipse.xtext.xtext.generator.scoping.ImportNamespacesScopingFragment2
	public void configureIgnoreCaseLinking(Binder binder) {
		binder.bindConstant().annotatedWith(IgnoreCaseLinking.class).to(false);
	}

	// contributed by org.eclipse.xtext.xtext.generator.builder.BuilderIntegrationFragment2
	public Class<? extends IContainer.Manager> bindIContainer$Manager() {
		return StateBasedContainerManager.class;
	}

	// contributed by org.eclipse.xtext.xtext.generator.builder.BuilderIntegrationFragment2
	public Class<? extends IAllContainersState.Provider> bindIAllContainersState$Provider() {
		return ResourceSetBasedAllContainersStateProvider.class;
	}

	// contributed by org.eclipse.xtext.xtext.generator.builder.BuilderIntegrationFragment2
	public void configureIResourceDescriptions(Binder binder) {
		binder.bind(IResourceDescriptions.class).to(ResourceSetBasedResourceDescriptions.class);
	}

	// contributed by org.eclipse.xtext.xtext.generator.builder.BuilderIntegrationFragment2
	public void configureIResourceDescriptionsPersisted(Binder binder) {
		binder.bind(IResourceDescriptions.class).annotatedWith(Names.named(ResourceDescriptionsProvider.PERSISTED_DESCRIPTIONS)).to(ResourceSetBasedResourceDescriptions.class);
	}

	// contributed by org.eclipse.ocl.build.fragments.EssentialOCLFragment
	public Class<? extends ICrossReferenceSerializer> bindICrossReferenceSerializer() {
		return BaseCrossReferenceSerializer.class;
	}

	// contributed by org.eclipse.ocl.build.fragments.EssentialOCLFragment
	public Class<? extends IDiagnosticConverter> bindIDiagnosticConverter() {
		return PivotDiagnosticConverter.class;
	}

	// contributed by org.eclipse.ocl.build.fragments.EssentialOCLFragment
	public Class<? extends IFragmentProvider> bindIFragmentProvider() {
		return BaseFragmentProvider.class;
	}

	// contributed by org.eclipse.ocl.build.fragments.EssentialOCLFragment
	public Class<? extends IHiddenTokenSequencer> bindIHiddenTokenSequencer() {
		return BaseHiddenTokenSequencer.class;
	}

	// contributed by org.eclipse.ocl.build.fragments.EssentialOCLFragment
	public Class<? extends ILinker> bindILinker() {
		return CS2ASLinker.class;
	}

	// contributed by org.eclipse.ocl.build.fragments.EssentialOCLFragment
	public Class<? extends ILinkingDiagnosticMessageProvider> bindILinkingDiagnosticMessageProvider() {
		return BaseLinkingDiagnosticMessageProvider.class;
	}

	// contributed by org.eclipse.ocl.build.fragments.EssentialOCLFragment
	public Class<? extends ILinkingService> bindILinkingService() {
		return BaseLinkingService.class;
	}

	// contributed by org.eclipse.ocl.build.fragments.EssentialOCLFragment
	public Class<? extends IQualifiedNameConverter> bindIQualifiedNameConverter() {
		return BaseQualifiedNameConverter.class;
	}

	// contributed by org.eclipse.ocl.build.fragments.EssentialOCLFragment
	public Class<? extends IQualifiedNameProvider> bindIQualifiedNameProvider() {
		return BaseQualifiedNameProvider.class;
	}

	// contributed by org.eclipse.ocl.build.fragments.EssentialOCLFragment
	public Class<? extends IResourceServiceProvider> bindIResourceServiceProvider() {
		return PivotResourceServiceProvider.class;
	}

	// contributed by org.eclipse.ocl.build.fragments.EssentialOCLFragment
	public Class<? extends IResourceValidator> bindIResourceValidator() {
		return PivotResourceValidator.class;
	}

	// contributed by org.eclipse.ocl.build.fragments.EssentialOCLFragment
	public Class<? extends IValueConverterService> bindIValueConverterService() {
		return BaseValueConverterService.class;
	}

	// contributed by org.eclipse.ocl.build.fragments.EssentialOCLFragment
	public Class<? extends XtextResource> bindXtextResource() {
		return EssentialOCLCSResource.class;
	}

	// contributed by org.eclipse.ocl.build.fragments.CompatibilityFragment
	public Class<? extends GrammarProvider> bindGrammarProvider() {
		return EssentialOCLGrammarResource.GrammarProvider.class;
	}

}
