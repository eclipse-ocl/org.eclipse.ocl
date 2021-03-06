/*******************************************************************************
 * Copyright (c) 2011, 2020 Willink Transformations and others.
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
import org.eclipse.xtext.Grammar;
import org.eclipse.xtext.GrammarUtil;
import org.eclipse.xtext.conversion.IValueConverterService;
import org.eclipse.xtext.generator.BindFactory;
import org.eclipse.xtext.generator.Binding;
import org.eclipse.xtext.generator.DefaultGeneratorFragment;
import org.eclipse.xtext.generator.Naming;
import org.eclipse.xtext.generator.NamingAware;
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

/**
 * Provide the standard EssentialOCL bindings as Abstract defaults
 */
public class EssentialOCLFragment extends DefaultGeneratorFragment implements NamingAware
{
	public static String getQualifiedName(Grammar grammar, Naming n) {
		return n.basePackageUi(grammar) + "." + GrammarUtil.getSimpleName(grammar) + "Editor";
	}

	private Naming naming;

	@SuppressWarnings("restriction")
	@Override
	public Set<Binding> getGuiceBindingsRt(Grammar grammar) {
		BindFactory bindFactory = new BindFactory();
		bindFactory.addTypeToType(org.eclipse.xtext.serializer.tokens.ICrossReferenceSerializer.class.getName(), BaseCrossReferenceSerializer.class.getName());
		// Potential resolution of Pivot fragments for CS resource
		bindFactory.addTypeToType(IDiagnosticConverter.class.getName(), PivotDiagnosticConverter.class.getName());
		bindFactory.addTypeToType(IFragmentProvider.class.getName(), BaseFragmentProvider.class.getName());
		bindFactory.addTypeToType(org.eclipse.xtext.serializer.sequencer.IHiddenTokenSequencer.class.getName(), BaseHiddenTokenSequencer.class.getName());
		bindFactory.addTypeToType(ILinker.class.getName(), CS2ASLinker.class.getName());
		bindFactory.addTypeToType(ILinkingDiagnosticMessageProvider.class.getName(), BaseLinkingDiagnosticMessageProvider.class.getName());
		// External reference loading and resolution.
		bindFactory.addTypeToType(ILinkingService.class.getName(), BaseLinkingService.class.getName());
		// :: as scope separator
		bindFactory.addTypeToType(IQualifiedNameConverter.class.getName(), BaseQualifiedNameConverter.class.getName());
		// Name value to text serialisation.
		bindFactory.addTypeToType(IQualifiedNameProvider.class.getName(), BaseQualifiedNameProvider.class.getName());
		// pivot: scheme support
		bindFactory.addTypeToType(IResourceServiceProvider.class.getName(), PivotResourceServiceProvider.class.getName());
		// pivot AST validation support
		//		bindFactory.addTypeToType(Diagnostician.class.getName(), PivotCancelableDiagnostician.class.getName());
		bindFactory.addTypeToType(IResourceValidator.class.getName(), PivotResourceValidator.class.getName());
		// DataType text to value parsing.
		bindFactory.addTypeToType(IValueConverterService.class.getName(), BaseValueConverterService.class.getName());
		bindFactory.addTypeToType(XtextResource.class.getName(), EssentialOCLCSResource.class.getName());
		return bindFactory.getBindings();
	}

	@Override
	public Set<Binding> getGuiceBindingsUi(Grammar grammar) {
		BindFactory bindFactory = new BindFactory();
		bindFactory.addConfiguredBinding(String.class.getName(), "binder.bind(String.class).annotatedWith(com.google.inject.name.Names.named((org.eclipse.xtext.ui.editor.contentassist.XtextContentAssistProcessor.COMPLETION_AUTO_ACTIVATION_CHARS))).toInstance(\".,:>\");");
		bindFactory.addTypeToType(AbstractAntlrTokenToAttributeIdMapper.class.getName(), BaseAntlrTokenToAttributeIdMapper.class.getName());
		bindFactory.addTypeToType(AbstractEditStrategyProvider.class.getName(), BaseAutoEditStrategyProvider.class.getName());
		bindFactory.addTypeToType(IHighlightingConfiguration.class.getName(), EssentialOCLHighlightingConfiguration.class.getName());
		bindFactory.addTypeToType(ILocationInFileProvider.class.getName(), EssentialOCLLocationInFileProvider.class.getName());
		//		bindFactory.addTypeToType(org.eclipse.xtext.ui.editor.findrefs.IReferenceFinder.class.getName(), BaseReferenceFinder.class.getName());
		bindFactory.addTypeToType(ISemanticHighlightingCalculator.class.getName(), EssentialOCLSemanticHighlightingCalculator.class.getName());
		bindFactory.addTypeToType(ITokenTypeToPartitionTypeMapper.class.getName(), BaseTerminalsTokenTypeToPartitionMapper.class.getName());
		bindFactory.addTypeToType(IURIEditorOpener.class.getName(), BaseURIEditorOpener.class.getName());
		bindFactory.addTypeToType(IXtextEditorCallback.class.getName(), BaseEditorCallback.class.getName());
		bindFactory.addTypeToType(OutlineWithEditorLinker.class.getName(), BaseOutlineWithEditorLinker.class.getName());
		bindFactory.addTypeToType(OutlineNodeElementOpener.class.getName(), BaseOutlineNodeElementOpener.class.getName());
		bindFactory.addTypeToType(XtextDocument.class.getName(), BaseDocument.class.getName());
		bindFactory.addTypeToType(XtextEditor.class.getName(), getQualifiedName(grammar, getNaming()));
		return bindFactory.getBindings();
	}

	public Naming getNaming() {
		return naming;
	}

	@Override
	public void registerNaming(Naming naming) {
		this.naming = naming;
	}
}