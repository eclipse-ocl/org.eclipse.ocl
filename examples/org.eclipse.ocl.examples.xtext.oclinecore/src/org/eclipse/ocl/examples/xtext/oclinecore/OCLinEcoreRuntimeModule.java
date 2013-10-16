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
 * $Id: OCLinEcoreRuntimeModule.java,v 1.12 2011/05/15 20:22:19 ewillink Exp $
 */
package org.eclipse.ocl.examples.xtext.oclinecore;

import org.antlr.runtime.TokenSource;
import org.eclipse.ocl.examples.xtext.base.InjectorProvider;
import org.eclipse.ocl.examples.xtext.essentialocl.services.RetokenizingTokenSource;
import org.eclipse.ocl.examples.xtext.oclinecore.parser.antlr.OCLinEcoreParser;
import org.eclipse.ocl.examples.xtext.oclinecore.services.OCLinEcoreValueConverterService;
import org.eclipse.ocl.examples.xtext.oclinecore.utilities.OCLinEcoreCSResource;
import org.eclipse.xtext.conversion.IValueConverterService;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.resource.XtextResource;

import com.google.inject.Binder;
import com.google.inject.name.Names;

/**
 * Use this class to register components to be used within the IDE.
 */
public class OCLinEcoreRuntimeModule extends AbstractOCLinEcoreRuntimeModule
{
	public static class RetokenizingOCLinEcoreParser extends OCLinEcoreParser
	{
		@Override
		protected XtextTokenStream createTokenStream(TokenSource tokenSource) {
			return super.createTokenStream(new RetokenizingTokenSource(tokenSource, getTokenDefProvider().getTokenDefMap()));
		}
	}
	
	@Override
	public Class<? extends org.eclipse.xtext.parser.IParser> bindIParser() {
		return RetokenizingOCLinEcoreParser.class;
	}
	
	@Override
	public Class<? extends IValueConverterService> bindIValueConverterService() {
		return OCLinEcoreValueConverterService.class;
	}

	@Override
	public Class<? extends XtextResource> bindXtextResource() {
		return OCLinEcoreCSResource.class;
	}
	
	/**
	 * @return The language injector provider
	 */
	public Class<? extends InjectorProvider> bindInjectorProvider() {
		return OCLinEcoreInjectorProvider.class;
	}
	
	public void configureEObjectValidation(Binder binder) {
		binder.bindConstant().annotatedWith(Names.named(org.eclipse.xtext.validation.CompositeEValidator.USE_EOBJECT_VALIDATOR)).to(false);
	}
}
