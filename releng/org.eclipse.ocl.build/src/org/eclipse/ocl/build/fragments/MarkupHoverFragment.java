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

import org.eclipse.jface.text.ITextHover;
import org.eclipse.ocl.xtext.base.ui.model.BaseDocumentationProvider;
import org.eclipse.ocl.xtext.markup.ui.hover.MarkupCompositeHover;
import org.eclipse.ocl.xtext.markup.ui.hover.MarkupHover;
import org.eclipse.ocl.xtext.markup.ui.hover.MarkupHoverProvider;
import org.eclipse.xtext.documentation.IEObjectDocumentationProvider;
import org.eclipse.xtext.ui.editor.hover.IEObjectHover;
import org.eclipse.xtext.ui.editor.hover.IEObjectHoverProvider;
import org.eclipse.xtext.xtext.generator.AbstractXtextGeneratorFragment;
import org.eclipse.xtext.xtext.generator.IXtextGeneratorLanguage;
import org.eclipse.xtext.xtext.generator.model.GuiceModuleAccess;
import org.eclipse.xtext.xtext.generator.model.TypeReference;

import com.google.inject.Inject;

/**
 * Support Markup in hover text.
 */
public class MarkupHoverFragment  extends AbstractXtextGeneratorFragment
{
	@Inject
	private IXtextGeneratorLanguage language;

	protected void addTypeToType(GuiceModuleAccess.BindingFactory bindFactory, Class<?> keyClass, Class<?> valueClass) {
		bindFactory.addTypeToType(new TypeReference(keyClass.getName()), new TypeReference(valueClass.getName()));
	}

	@Override
	public void generate() {
		GuiceModuleAccess.BindingFactory bindFactory = new GuiceModuleAccess.BindingFactory();
		addTypeToType(bindFactory, IEObjectHover.class, MarkupHover.class);
		addTypeToType(bindFactory, IEObjectHoverProvider.class, MarkupHoverProvider.class);
		addTypeToType(bindFactory, IEObjectDocumentationProvider.class, BaseDocumentationProvider.class);
		addTypeToType(bindFactory, ITextHover.class, MarkupCompositeHover.class);
		bindFactory.contributeTo(language.getEclipsePluginGenModule());
	}
}