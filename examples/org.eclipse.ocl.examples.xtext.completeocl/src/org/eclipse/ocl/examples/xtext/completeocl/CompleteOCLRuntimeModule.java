/**
 * <copyright>
 *
 * Copyright (c) 2010, 2012 E.D.Willink and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.ocl.examples.xtext.completeocl;

import org.antlr.runtime.TokenSource;
import org.eclipse.ocl.examples.xtext.base.InjectorProvider;
import org.eclipse.ocl.examples.xtext.completeocl.parser.antlr.CompleteOCLParser;
import org.eclipse.ocl.examples.xtext.completeocl.scoping.CompleteOCLScopeProvider;
import org.eclipse.ocl.examples.xtext.completeocl.services.CompleteOCLHiddenTokenSequencer;
import org.eclipse.ocl.examples.xtext.completeocl.utilities.CompleteOCLCSResource;
import org.eclipse.ocl.examples.xtext.essentialocl.services.RetokenizingTokenSource;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.scoping.IScopeProvider;

import com.google.inject.Binder;
import com.google.inject.name.Names;

/**
 * Use this class to register components to be used within the IDE.
 */
public class CompleteOCLRuntimeModule extends org.eclipse.ocl.examples.xtext.completeocl.AbstractCompleteOCLRuntimeModule
{
	
	@SuppressWarnings("restriction")
	@Override
	public Class<? extends org.eclipse.xtext.serializer.sequencer.IHiddenTokenSequencer> bindIHiddenTokenSequencer() {
		return CompleteOCLHiddenTokenSequencer.class;
	}
	
	@Override
	public Class<? extends org.eclipse.xtext.parser.IParser> bindIParser() {
		return RetokenizingCompleteOCLParser.class;
	}

	public static class RetokenizingCompleteOCLParser extends CompleteOCLParser
	{
		@Override
		protected XtextTokenStream createTokenStream(TokenSource tokenSource) {
			return super.createTokenStream(new RetokenizingTokenSource(tokenSource, getTokenDefProvider().getTokenDefMap()));
		}
	}

	@Override
	public Class<? extends IScopeProvider> bindIScopeProvider() {
		return CompleteOCLScopeProvider.class;
	}
	
	@Override
	public Class<? extends XtextResource> bindXtextResource() {
		return CompleteOCLCSResource.class;
	}
	
	/**
	 * @return The language injector provider
	 */
	public Class<? extends InjectorProvider> bindInjectorProvider() {
		return CompleteOCLInjectorProvider.class;
	}
	
	public void configureEObjectValidation(Binder binder) {
		binder.bindConstant().annotatedWith(Names.named(org.eclipse.xtext.validation.CompositeEValidator.USE_EOBJECT_VALIDATOR)).to(false);
	}
}
