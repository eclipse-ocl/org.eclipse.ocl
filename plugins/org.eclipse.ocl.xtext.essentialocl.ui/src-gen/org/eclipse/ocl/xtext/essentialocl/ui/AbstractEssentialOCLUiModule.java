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
package org.eclipse.ocl.xtext.essentialocl.ui;

import com.google.inject.Binder;
import com.google.inject.Provider;
import com.google.inject.name.Names;
import org.eclipse.compare.IViewerCreator;
import org.eclipse.jface.text.ITextHover;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.ocl.xtext.base.ui.autoedit.BaseAutoEditStrategyProvider;
import org.eclipse.ocl.xtext.base.ui.model.BaseDocument;
import org.eclipse.ocl.xtext.base.ui.model.BaseDocumentationProvider;
import org.eclipse.ocl.xtext.base.ui.model.BaseEditorCallback;
import org.eclipse.ocl.xtext.base.ui.model.BaseTerminalsTokenTypeToPartitionMapper;
import org.eclipse.ocl.xtext.base.ui.model.BaseURIEditorOpener;
import org.eclipse.ocl.xtext.base.ui.outline.BaseOutlineNodeElementOpener;
import org.eclipse.ocl.xtext.base.ui.outline.BaseOutlineWithEditorLinker;
import org.eclipse.ocl.xtext.base.ui.syntaxcoloring.BaseAntlrTokenToAttributeIdMapper;
import org.eclipse.ocl.xtext.essentialocl.as2cs.EssentialOCLLocationInFileProvider;
import org.eclipse.ocl.xtext.essentialocl.ide.contentassist.antlr.EssentialOCLParser;
import org.eclipse.ocl.xtext.essentialocl.ide.contentassist.antlr.PartialEssentialOCLContentAssistParser;
import org.eclipse.ocl.xtext.essentialocl.ide.contentassist.antlr.internal.InternalEssentialOCLLexer;
import org.eclipse.ocl.xtext.essentialocl.ui.contentassist.EssentialOCLProposalProvider;
import org.eclipse.ocl.xtext.essentialocl.ui.labeling.EssentialOCLDescriptionLabelProvider;
import org.eclipse.ocl.xtext.essentialocl.ui.labeling.EssentialOCLLabelProvider;
import org.eclipse.ocl.xtext.essentialocl.ui.outline.EssentialOCLOutlineTreeProvider;
import org.eclipse.ocl.xtext.essentialocl.ui.quickfix.EssentialOCLQuickfixProvider;
import org.eclipse.ocl.xtext.essentialocl.ui.syntaxcoloring.EssentialOCLHighlightingConfiguration;
import org.eclipse.ocl.xtext.essentialocl.ui.syntaxcoloring.EssentialOCLSemanticHighlightingCalculator;
import org.eclipse.ocl.xtext.markup.ui.hover.MarkupCompositeHover;
import org.eclipse.ocl.xtext.markup.ui.hover.MarkupHover;
import org.eclipse.ocl.xtext.markup.ui.hover.MarkupHoverProvider;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipse.xtext.builder.EclipseOutputConfigurationProvider;
import org.eclipse.xtext.builder.builderState.IBuilderState;
import org.eclipse.xtext.builder.clustering.CurrentDescriptions;
import org.eclipse.xtext.builder.impl.PersistentDataAwareDirtyResource;
import org.eclipse.xtext.documentation.IEObjectDocumentationProvider;
import org.eclipse.xtext.generator.IContextualOutputConfigurationProvider;
import org.eclipse.xtext.ide.LexerIdeBindings;
import org.eclipse.xtext.ide.editor.contentassist.antlr.IContentAssistParser;
import org.eclipse.xtext.ide.editor.contentassist.antlr.internal.Lexer;
import org.eclipse.xtext.ide.editor.partialEditing.IPartialEditingContentAssistParser;
import org.eclipse.xtext.ide.editor.syntaxcoloring.ISemanticHighlightingCalculator;
import org.eclipse.xtext.parser.antlr.AntlrTokenDefProvider;
import org.eclipse.xtext.parser.antlr.ITokenDefProvider;
import org.eclipse.xtext.parser.antlr.LexerProvider;
import org.eclipse.xtext.resource.ILocationInFileProvider;
import org.eclipse.xtext.resource.IResourceDescriptions;
import org.eclipse.xtext.resource.containers.IAllContainersState;
import org.eclipse.xtext.resource.impl.ResourceDescriptionsProvider;
import org.eclipse.xtext.service.SingletonBinding;
import org.eclipse.xtext.ui.DefaultUiModule;
import org.eclipse.xtext.ui.UIBindings;
import org.eclipse.xtext.ui.codetemplates.ui.AccessibleCodetemplatesActivator;
import org.eclipse.xtext.ui.codetemplates.ui.partialEditing.IPartialEditingContentAssistContextFactory;
import org.eclipse.xtext.ui.codetemplates.ui.partialEditing.PartialEditingContentAssistContextFactory;
import org.eclipse.xtext.ui.codetemplates.ui.preferences.AdvancedTemplatesPreferencePage;
import org.eclipse.xtext.ui.codetemplates.ui.preferences.TemplatesLanguageConfiguration;
import org.eclipse.xtext.ui.codetemplates.ui.registry.LanguageRegistrar;
import org.eclipse.xtext.ui.codetemplates.ui.registry.LanguageRegistry;
import org.eclipse.xtext.ui.compare.DefaultViewerCreator;
import org.eclipse.xtext.ui.editor.DocumentBasedDirtyResource;
import org.eclipse.xtext.ui.editor.IURIEditorOpener;
import org.eclipse.xtext.ui.editor.IXtextEditorCallback;
import org.eclipse.xtext.ui.editor.XtextEditor;
import org.eclipse.xtext.ui.editor.autoedit.AbstractEditStrategyProvider;
import org.eclipse.xtext.ui.editor.contentassist.ContentAssistContext;
import org.eclipse.xtext.ui.editor.contentassist.FQNPrefixMatcher;
import org.eclipse.xtext.ui.editor.contentassist.IContentProposalProvider;
import org.eclipse.xtext.ui.editor.contentassist.IProposalConflictHelper;
import org.eclipse.xtext.ui.editor.contentassist.PrefixMatcher;
import org.eclipse.xtext.ui.editor.contentassist.antlr.AntlrProposalConflictHelper;
import org.eclipse.xtext.ui.editor.contentassist.antlr.DelegatingContentAssistContextFactory;
import org.eclipse.xtext.ui.editor.hover.IEObjectHover;
import org.eclipse.xtext.ui.editor.hover.IEObjectHoverProvider;
import org.eclipse.xtext.ui.editor.model.ITokenTypeToPartitionTypeMapper;
import org.eclipse.xtext.ui.editor.model.XtextDocument;
import org.eclipse.xtext.ui.editor.outline.IOutlineTreeProvider;
import org.eclipse.xtext.ui.editor.outline.actions.OutlineWithEditorLinker;
import org.eclipse.xtext.ui.editor.outline.impl.IOutlineTreeStructureProvider;
import org.eclipse.xtext.ui.editor.outline.impl.OutlineNodeElementOpener;
import org.eclipse.xtext.ui.editor.preferences.IPreferenceStoreInitializer;
import org.eclipse.xtext.ui.editor.quickfix.IssueResolutionProvider;
import org.eclipse.xtext.ui.editor.syntaxcoloring.AbstractAntlrTokenToAttributeIdMapper;
import org.eclipse.xtext.ui.editor.syntaxcoloring.IHighlightingConfiguration;
import org.eclipse.xtext.ui.editor.templates.XtextTemplatePreferencePage;
import org.eclipse.xtext.ui.refactoring.IDependentElementsCalculator;
import org.eclipse.xtext.ui.refactoring.IReferenceUpdater;
import org.eclipse.xtext.ui.refactoring.IRenameRefactoringProvider;
import org.eclipse.xtext.ui.refactoring.IRenameStrategy;
import org.eclipse.xtext.ui.refactoring.impl.DefaultDependentElementsCalculator;
import org.eclipse.xtext.ui.refactoring.impl.DefaultReferenceUpdater;
import org.eclipse.xtext.ui.refactoring.impl.DefaultRenameRefactoringProvider;
import org.eclipse.xtext.ui.refactoring.impl.DefaultRenameStrategy;
import org.eclipse.xtext.ui.refactoring.ui.DefaultRenameSupport;
import org.eclipse.xtext.ui.refactoring.ui.IRenameSupport;
import org.eclipse.xtext.ui.refactoring.ui.RefactoringPreferences;
import org.eclipse.xtext.ui.resource.ResourceServiceDescriptionLabelProvider;
import org.eclipse.xtext.ui.shared.Access;

/**
 * Manual modifications go to {@link EssentialOCLUiModule}.
 */
@SuppressWarnings("all")
public abstract class AbstractEssentialOCLUiModule extends DefaultUiModule {

	public AbstractEssentialOCLUiModule(AbstractUIPlugin plugin) {
		super(plugin);
	}

	// contributed by org.eclipse.xtext.xtext.generator.ImplicitFragment
	public Provider<? extends IAllContainersState> provideIAllContainersState() {
		return Access.getJavaProjectsState();
	}

	// contributed by org.eclipse.xtext.xtext.generator.parser.antlr.XtextAntlrGeneratorFragment2
	public Class<? extends IProposalConflictHelper> bindIProposalConflictHelper() {
		return AntlrProposalConflictHelper.class;
	}

	// contributed by org.eclipse.xtext.xtext.generator.parser.antlr.XtextAntlrGeneratorFragment2
	public void configureContentAssistLexer(Binder binder) {
		binder.bind(Lexer.class)
			.annotatedWith(Names.named(LexerIdeBindings.CONTENT_ASSIST))
			.to(InternalEssentialOCLLexer.class);
	}

	// contributed by org.eclipse.xtext.xtext.generator.parser.antlr.XtextAntlrGeneratorFragment2
	public void configureHighlightingLexer(Binder binder) {
		binder.bind(org.eclipse.xtext.parser.antlr.Lexer.class)
			.annotatedWith(Names.named(LexerIdeBindings.HIGHLIGHTING))
			.to(org.eclipse.ocl.xtext.essentialocl.parser.antlr.internal.InternalEssentialOCLLexer.class);
	}

	// contributed by org.eclipse.xtext.xtext.generator.parser.antlr.XtextAntlrGeneratorFragment2
	public void configureHighlightingTokenDefProvider(Binder binder) {
		binder.bind(ITokenDefProvider.class)
			.annotatedWith(Names.named(LexerIdeBindings.HIGHLIGHTING))
			.to(AntlrTokenDefProvider.class);
	}

	// contributed by org.eclipse.xtext.xtext.generator.parser.antlr.XtextAntlrGeneratorFragment2
	public Class<? extends ContentAssistContext.Factory> bindContentAssistContext$Factory() {
		return DelegatingContentAssistContextFactory.class;
	}

	// contributed by org.eclipse.xtext.xtext.generator.parser.antlr.XtextAntlrGeneratorFragment2
	public Class<? extends IContentAssistParser> bindIContentAssistParser() {
		return EssentialOCLParser.class;
	}

	// contributed by org.eclipse.xtext.xtext.generator.parser.antlr.XtextAntlrGeneratorFragment2
	public void configureContentAssistLexerProvider(Binder binder) {
		binder.bind(InternalEssentialOCLLexer.class).toProvider(LexerProvider.create(InternalEssentialOCLLexer.class));
	}

	// contributed by org.eclipse.xtext.xtext.generator.exporting.QualifiedNamesFragment2
	public Class<? extends PrefixMatcher> bindPrefixMatcher() {
		return FQNPrefixMatcher.class;
	}

	// contributed by org.eclipse.xtext.xtext.generator.exporting.QualifiedNamesFragment2
	public Class<? extends IDependentElementsCalculator> bindIDependentElementsCalculator() {
		return DefaultDependentElementsCalculator.class;
	}

	// contributed by org.eclipse.xtext.xtext.generator.builder.BuilderIntegrationFragment2
	public void configureIResourceDescriptionsBuilderScope(Binder binder) {
		binder.bind(IResourceDescriptions.class).annotatedWith(Names.named(ResourceDescriptionsProvider.NAMED_BUILDER_SCOPE)).to(CurrentDescriptions.ResourceSetAware.class);
	}

	// contributed by org.eclipse.xtext.xtext.generator.builder.BuilderIntegrationFragment2
	public Class<? extends IContextualOutputConfigurationProvider> bindIContextualOutputConfigurationProvider() {
		return EclipseOutputConfigurationProvider.class;
	}

	// contributed by org.eclipse.xtext.xtext.generator.builder.BuilderIntegrationFragment2
	public void configureIResourceDescriptionsPersisted(Binder binder) {
		binder.bind(IResourceDescriptions.class).annotatedWith(Names.named(ResourceDescriptionsProvider.PERSISTED_DESCRIPTIONS)).to(IBuilderState.class);
	}

	// contributed by org.eclipse.xtext.xtext.generator.builder.BuilderIntegrationFragment2
	public Class<? extends DocumentBasedDirtyResource> bindDocumentBasedDirtyResource() {
		return PersistentDataAwareDirtyResource.class;
	}

	// contributed by org.eclipse.xtext.xtext.generator.ui.labeling.LabelProviderFragment2
	public Class<? extends ILabelProvider> bindILabelProvider() {
		return EssentialOCLLabelProvider.class;
	}

	// contributed by org.eclipse.xtext.xtext.generator.ui.labeling.LabelProviderFragment2
	public void configureResourceUIServiceLabelProvider(Binder binder) {
		binder.bind(ILabelProvider.class).annotatedWith(ResourceServiceDescriptionLabelProvider.class).to(EssentialOCLDescriptionLabelProvider.class);
	}

	// contributed by org.eclipse.xtext.xtext.generator.ui.outline.OutlineTreeProviderFragment2
	public Class<? extends IOutlineTreeProvider> bindIOutlineTreeProvider() {
		return EssentialOCLOutlineTreeProvider.class;
	}

	// contributed by org.eclipse.xtext.xtext.generator.ui.outline.OutlineTreeProviderFragment2
	public Class<? extends IOutlineTreeStructureProvider> bindIOutlineTreeStructureProvider() {
		return EssentialOCLOutlineTreeProvider.class;
	}

	// contributed by org.eclipse.xtext.xtext.generator.ui.quickfix.QuickfixProviderFragment2
	public Class<? extends IssueResolutionProvider> bindIssueResolutionProvider() {
		return EssentialOCLQuickfixProvider.class;
	}

	// contributed by org.eclipse.xtext.xtext.generator.ui.contentAssist.ContentAssistFragment2
	public Class<? extends IContentProposalProvider> bindIContentProposalProvider() {
		return EssentialOCLProposalProvider.class;
	}

	// contributed by org.eclipse.xtext.xtext.generator.ui.refactoring.RefactorElementNameFragment2
	public void configureIPreferenceStoreInitializer(Binder binder) {
		binder.bind(IPreferenceStoreInitializer.class)
			.annotatedWith(Names.named("RefactoringPreferences"))
			.to(RefactoringPreferences.Initializer.class);
	}

	// contributed by org.eclipse.xtext.xtext.generator.ui.refactoring.RefactorElementNameFragment2
	public Class<? extends IRenameStrategy> bindIRenameStrategy() {
		return DefaultRenameStrategy.class;
	}

	// contributed by org.eclipse.xtext.xtext.generator.ui.refactoring.RefactorElementNameFragment2
	public Class<? extends IReferenceUpdater> bindIReferenceUpdater() {
		return DefaultReferenceUpdater.class;
	}

	// contributed by org.eclipse.xtext.xtext.generator.ui.refactoring.RefactorElementNameFragment2
	public Class<? extends IRenameRefactoringProvider> bindIRenameRefactoringProvider() {
		return DefaultRenameRefactoringProvider.class;
	}

	// contributed by org.eclipse.xtext.xtext.generator.ui.refactoring.RefactorElementNameFragment2
	public Class<? extends IRenameSupport.Factory> bindIRenameSupport$Factory() {
		return DefaultRenameSupport.Factory.class;
	}

	// contributed by org.eclipse.xtext.xtext.generator.ui.templates.CodetemplatesGeneratorFragment2
	public Provider<? extends TemplatesLanguageConfiguration> provideTemplatesLanguageConfiguration() {
		return AccessibleCodetemplatesActivator.getTemplatesLanguageConfigurationProvider();
	}

	// contributed by org.eclipse.xtext.xtext.generator.ui.templates.CodetemplatesGeneratorFragment2
	public Provider<? extends LanguageRegistry> provideLanguageRegistry() {
		return AccessibleCodetemplatesActivator.getLanguageRegistry();
	}

	// contributed by org.eclipse.xtext.xtext.generator.ui.templates.CodetemplatesGeneratorFragment2
	@SingletonBinding(eager=true)
	public Class<? extends LanguageRegistrar> bindLanguageRegistrar() {
		return LanguageRegistrar.class;
	}

	// contributed by org.eclipse.xtext.xtext.generator.ui.templates.CodetemplatesGeneratorFragment2
	public Class<? extends XtextTemplatePreferencePage> bindXtextTemplatePreferencePage() {
		return AdvancedTemplatesPreferencePage.class;
	}

	// contributed by org.eclipse.xtext.xtext.generator.ui.templates.CodetemplatesGeneratorFragment2
	public Class<? extends IPartialEditingContentAssistParser> bindIPartialEditingContentAssistParser() {
		return PartialEssentialOCLContentAssistParser.class;
	}

	// contributed by org.eclipse.xtext.xtext.generator.ui.templates.CodetemplatesGeneratorFragment2
	public Class<? extends IPartialEditingContentAssistContextFactory> bindIPartialEditingContentAssistContextFactory() {
		return PartialEditingContentAssistContextFactory.class;
	}

	// contributed by org.eclipse.xtext.xtext.generator.ui.compare.CompareFragment2
	public Class<? extends IViewerCreator> bindIViewerCreator() {
		return DefaultViewerCreator.class;
	}

	// contributed by org.eclipse.xtext.xtext.generator.ui.compare.CompareFragment2
	public void configureCompareViewerTitle(Binder binder) {
		binder.bind(String.class).annotatedWith(Names.named(UIBindings.COMPARE_VIEWER_TITLE)).toInstance("EssentialOCL Compare");
	}

	// contributed by org.eclipse.ocl.examples.build.fragments.EssentialOCLFragment
	public void configurejava$lang$String(Binder binder) {
		binder.bind(String.class).annotatedWith(com.google.inject.name.Names.named((org.eclipse.xtext.ui.editor.contentassist.XtextContentAssistProcessor.COMPLETION_AUTO_ACTIVATION_CHARS))).toInstance(".,:>");
	}

	// contributed by org.eclipse.ocl.examples.build.fragments.EssentialOCLFragment
	public Class<? extends AbstractAntlrTokenToAttributeIdMapper> bindAbstractAntlrTokenToAttributeIdMapper() {
		return BaseAntlrTokenToAttributeIdMapper.class;
	}

	// contributed by org.eclipse.ocl.examples.build.fragments.EssentialOCLFragment
	public Class<? extends AbstractEditStrategyProvider> bindAbstractEditStrategyProvider() {
		return BaseAutoEditStrategyProvider.class;
	}

	// contributed by org.eclipse.ocl.examples.build.fragments.EssentialOCLFragment
	public Class<? extends IHighlightingConfiguration> bindIHighlightingConfiguration() {
		return EssentialOCLHighlightingConfiguration.class;
	}

	// contributed by org.eclipse.ocl.examples.build.fragments.EssentialOCLFragment
	public Class<? extends ILocationInFileProvider> bindILocationInFileProvider() {
		return EssentialOCLLocationInFileProvider.class;
	}

	// contributed by org.eclipse.ocl.examples.build.fragments.EssentialOCLFragment
	public Class<? extends ISemanticHighlightingCalculator> bindISemanticHighlightingCalculator() {
		return EssentialOCLSemanticHighlightingCalculator.class;
	}

	// contributed by org.eclipse.ocl.examples.build.fragments.EssentialOCLFragment
	public Class<? extends ITokenTypeToPartitionTypeMapper> bindITokenTypeToPartitionTypeMapper() {
		return BaseTerminalsTokenTypeToPartitionMapper.class;
	}

	// contributed by org.eclipse.ocl.examples.build.fragments.EssentialOCLFragment
	public Class<? extends IURIEditorOpener> bindIURIEditorOpener() {
		return BaseURIEditorOpener.class;
	}

	// contributed by org.eclipse.ocl.examples.build.fragments.EssentialOCLFragment
	public Class<? extends IXtextEditorCallback> bindIXtextEditorCallback() {
		return BaseEditorCallback.class;
	}

	// contributed by org.eclipse.ocl.examples.build.fragments.EssentialOCLFragment
	public Class<? extends OutlineWithEditorLinker> bindOutlineWithEditorLinker() {
		return BaseOutlineWithEditorLinker.class;
	}

	// contributed by org.eclipse.ocl.examples.build.fragments.EssentialOCLFragment
	public Class<? extends OutlineNodeElementOpener> bindOutlineNodeElementOpener() {
		return BaseOutlineNodeElementOpener.class;
	}

	// contributed by org.eclipse.ocl.examples.build.fragments.EssentialOCLFragment
	public Class<? extends XtextDocument> bindXtextDocument() {
		return BaseDocument.class;
	}

	// contributed by org.eclipse.ocl.examples.build.fragments.EssentialOCLFragment
	public Class<? extends XtextEditor> bindXtextEditor() {
		return EssentialOCLEditor.class;
	}

	// contributed by org.eclipse.ocl.examples.build.fragments.MarkupHoverFragment
	public Class<? extends IEObjectHover> bindIEObjectHover() {
		return MarkupHover.class;
	}

	// contributed by org.eclipse.ocl.examples.build.fragments.MarkupHoverFragment
	public Class<? extends IEObjectHoverProvider> bindIEObjectHoverProvider() {
		return MarkupHoverProvider.class;
	}

	// contributed by org.eclipse.ocl.examples.build.fragments.MarkupHoverFragment
	public Class<? extends IEObjectDocumentationProvider> bindIEObjectDocumentationProvider() {
		return BaseDocumentationProvider.class;
	}

	// contributed by org.eclipse.ocl.examples.build.fragments.MarkupHoverFragment
	public Class<? extends ITextHover> bindITextHover() {
		return MarkupCompositeHover.class;
	}

}
