/*******************************************************************************
 * Copyright (c) 2011, 2023 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.build.fragments;

import org.eclipse.ocl.xtext.base.cs2as.BaseFragmentProvider;
import org.eclipse.ocl.xtext.base.serializer.BaseCrossReferenceSerializer;
import org.eclipse.ocl.xtext.base.serializer.BaseHiddenTokenSequencer;
import org.eclipse.ocl.xtext.base.services.BaseLinkingDiagnosticMessageProvider;
import org.eclipse.ocl.xtext.base.services.BaseLinkingService;
import org.eclipse.ocl.xtext.base.services.BaseQualifiedNameConverter;
import org.eclipse.ocl.xtext.base.services.BaseQualifiedNameProvider;
import org.eclipse.ocl.xtext.base.services.BaseValueConverterService;
import org.eclipse.ocl.xtext.base.services.PivotResourceServiceProvider;
import org.eclipse.ocl.xtext.base.ui.autoedit.BaseAutoEditStrategyProvider;
import org.eclipse.ocl.xtext.base.ui.model.BaseDocument;
import org.eclipse.ocl.xtext.base.ui.model.BaseEditorCallback;
import org.eclipse.ocl.xtext.base.ui.model.BaseTerminalsTokenTypeToPartitionMapper;
import org.eclipse.ocl.xtext.base.ui.model.BaseURIEditorOpener;
import org.eclipse.ocl.xtext.base.ui.outline.BaseOutlineNodeElementOpener;
import org.eclipse.ocl.xtext.base.ui.outline.BaseOutlineWithEditorLinker;
import org.eclipse.ocl.xtext.base.ui.syntaxcoloring.BaseAntlrTokenToAttributeIdMapper;
import org.eclipse.ocl.xtext.base.utilities.CS2ASLinker;
import org.eclipse.ocl.xtext.base.utilities.PivotDiagnosticConverter;
import org.eclipse.ocl.xtext.base.utilities.PivotResourceValidator;
import org.eclipse.ocl.xtext.essentialocl.as2cs.EssentialOCLLocationInFileProvider;
import org.eclipse.ocl.xtext.essentialocl.ui.syntaxcoloring.EssentialOCLHighlightingConfiguration;
import org.eclipse.ocl.xtext.essentialocl.ui.syntaxcoloring.EssentialOCLSemanticHighlightingCalculator;
import org.eclipse.ocl.xtext.essentialocl.utilities.EssentialOCLCSResource;
import org.eclipse.xtend2.lib.StringConcatenationClient;
import org.eclipse.xtext.Grammar;
import org.eclipse.xtext.conversion.IValueConverterService;
import org.eclipse.xtext.ide.editor.syntaxcoloring.ISemanticHighlightingCalculator;
import org.eclipse.xtext.linking.ILinker;
import org.eclipse.xtext.linking.ILinkingDiagnosticMessageProvider;
import org.eclipse.xtext.linking.ILinkingService;
import org.eclipse.xtext.naming.IQualifiedNameConverter;
import org.eclipse.xtext.naming.IQualifiedNameProvider;
import org.eclipse.xtext.resource.IFragmentProvider;
import org.eclipse.xtext.resource.ILocationInFileProvider;
import org.eclipse.xtext.resource.IResourceServiceProvider;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.ui.editor.IURIEditorOpener;
import org.eclipse.xtext.ui.editor.IXtextEditorCallback;
import org.eclipse.xtext.ui.editor.XtextEditor;
import org.eclipse.xtext.ui.editor.autoedit.AbstractEditStrategyProvider;
import org.eclipse.xtext.ui.editor.model.ITokenTypeToPartitionTypeMapper;
import org.eclipse.xtext.ui.editor.model.XtextDocument;
import org.eclipse.xtext.ui.editor.outline.actions.OutlineWithEditorLinker;
import org.eclipse.xtext.ui.editor.outline.impl.OutlineNodeElementOpener;
import org.eclipse.xtext.ui.editor.syntaxcoloring.AbstractAntlrTokenToAttributeIdMapper;
import org.eclipse.xtext.ui.editor.syntaxcoloring.IHighlightingConfiguration;
import org.eclipse.xtext.validation.IDiagnosticConverter;
import org.eclipse.xtext.validation.IResourceValidator;
import org.eclipse.xtext.xtext.generator.AbstractXtextGeneratorFragment;
import org.eclipse.xtext.xtext.generator.IXtextGeneratorLanguage;
import org.eclipse.xtext.xtext.generator.model.GuiceModuleAccess;
import org.eclipse.xtext.xtext.generator.model.TypeReference;

import com.google.inject.Inject;

/**
 * Provide the standard EssentialOCL bindings as Abstract defaults
 */
public class EssentialOCLFragment extends AbstractXtextGeneratorFragment //implements NamingAware
{
	@Inject
	private Grammar grammar;
	@Inject
	private IXtextGeneratorLanguage language;

//	public static String getQualifiedName(Grammar grammar, Naming n) {
//		return n.basePackageUi(grammar) + "." + GrammarUtil.getSimpleName(grammar) + "Editor";
//	}

//	private Naming naming;

	protected void addTypeToType(GuiceModuleAccess.BindingFactory bindFactory, Class<?> keyClass, Class<?> valueClass) {
		bindFactory.addTypeToType(new TypeReference(keyClass.getName()), new TypeReference(valueClass.getName()));
	}

	protected void addTypeToType(GuiceModuleAccess.BindingFactory bindFactory, Class<?> keyClass, String valueClassName) {
		bindFactory.addTypeToType(new TypeReference(keyClass.getName()), new TypeReference(valueClassName));
	}

	@Override
	public void generate() {
		generateRT();
		generateUI();
	}

	protected void generateRT() {
		GuiceModuleAccess.BindingFactory bindFactory = new GuiceModuleAccess.BindingFactory();
		addTypeToType(bindFactory, org.eclipse.xtext.serializer.tokens.ICrossReferenceSerializer.class, BaseCrossReferenceSerializer.class);
		// Potential resolution of Pivot fragments for CS resource
		addTypeToType(bindFactory, IDiagnosticConverter.class, PivotDiagnosticConverter.class);
		addTypeToType(bindFactory, IFragmentProvider.class, BaseFragmentProvider.class);
		addTypeToType(bindFactory, org.eclipse.xtext.serializer.sequencer.IHiddenTokenSequencer.class, BaseHiddenTokenSequencer.class);
		addTypeToType(bindFactory, ILinker.class, CS2ASLinker.class);
		addTypeToType(bindFactory, ILinkingDiagnosticMessageProvider.class, BaseLinkingDiagnosticMessageProvider.class);
		// External reference loading and resolution.
		addTypeToType(bindFactory, ILinkingService.class, BaseLinkingService.class);
		// :: as scope separator
		addTypeToType(bindFactory, IQualifiedNameConverter.class, BaseQualifiedNameConverter.class);
		// Name value to text serialisation.
		addTypeToType(bindFactory, IQualifiedNameProvider.class, BaseQualifiedNameProvider.class);
		// pivot: scheme support
		addTypeToType(bindFactory, IResourceServiceProvider.class, PivotResourceServiceProvider.class);
		// pivot AST validation support
		//		addTypeToType(bindFactory, Diagnostician.class, PivotCancelableDiagnostician.class);
		addTypeToType(bindFactory, IResourceValidator.class, PivotResourceValidator.class);
		// DataType text to value parsing.
		addTypeToType(bindFactory, IValueConverterService.class, BaseValueConverterService.class);
		addTypeToType(bindFactory, XtextResource.class, EssentialOCLCSResource.class);
		bindFactory.contributeTo(language.getRuntimeGenModule());
	}

	protected void generateUI() {
		GuiceModuleAccess.BindingFactory bindFactory = new GuiceModuleAccess.BindingFactory();
	      StringConcatenationClient _client = new StringConcatenationClient() {
	          @Override
	          protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
	            _builder.append("binder.bind(String.class).annotatedWith(com.google.inject.name.Names.named((org.eclipse.xtext.ui.editor.contentassist.XtextContentAssistProcessor.COMPLETION_AUTO_ACTIVATION_CHARS))).toInstance(\".,:>\");");
	            _builder.newLine();
	          }
	        };
		bindFactory.addConfiguredBinding(String.class.getName(), _client);
		addTypeToType(bindFactory, AbstractAntlrTokenToAttributeIdMapper.class, BaseAntlrTokenToAttributeIdMapper.class);
		addTypeToType(bindFactory, AbstractEditStrategyProvider.class, BaseAutoEditStrategyProvider.class);
		addTypeToType(bindFactory, IHighlightingConfiguration.class, EssentialOCLHighlightingConfiguration.class);
		addTypeToType(bindFactory, ILocationInFileProvider.class, EssentialOCLLocationInFileProvider.class);
		//		addTypeToType(bindFactory, org.eclipse.xtext.ui.editor.findrefs.IReferenceFinder.class, BaseReferenceFinder.class);
		addTypeToType(bindFactory, ISemanticHighlightingCalculator.class, EssentialOCLSemanticHighlightingCalculator.class);
		addTypeToType(bindFactory, ITokenTypeToPartitionTypeMapper.class, BaseTerminalsTokenTypeToPartitionMapper.class);
		addTypeToType(bindFactory, IURIEditorOpener.class, BaseURIEditorOpener.class);
		addTypeToType(bindFactory, IXtextEditorCallback.class, BaseEditorCallback.class);
		addTypeToType(bindFactory, OutlineWithEditorLinker.class, BaseOutlineWithEditorLinker.class);
		addTypeToType(bindFactory, OutlineNodeElementOpener.class, BaseOutlineNodeElementOpener.class);
		addTypeToType(bindFactory, XtextDocument.class, BaseDocument.class);
//		return n.basePackageUi(grammar) + "." + GrammarUtil.getSimpleName(grammar) + "Editor";
		String grammarName = grammar.getName();
		int i = grammarName.lastIndexOf('.');
		String valuePackageName = grammarName.substring(0, i);
		String valueClassName = grammarName.substring(i+1) + "Editor";
		addTypeToType(bindFactory, XtextEditor.class, valuePackageName + ".ui." + valueClassName);
		bindFactory.contributeTo(language.getEclipsePluginGenModule());
	}

//	public Naming getNaming() {
//		return naming;
//	}

//	@Override
//	public void registerNaming(Naming naming) {
//		this.naming = naming;
//	}
}