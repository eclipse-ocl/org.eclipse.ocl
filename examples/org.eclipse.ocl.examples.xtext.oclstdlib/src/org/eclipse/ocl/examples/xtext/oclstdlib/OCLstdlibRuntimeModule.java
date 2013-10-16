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

package org.eclipse.ocl.examples.xtext.oclstdlib;

import org.antlr.runtime.TokenSource;
import org.eclipse.ocl.examples.xtext.base.InjectorProvider;
import org.eclipse.ocl.examples.xtext.essentialocl.services.RetokenizingTokenSource;
import org.eclipse.ocl.examples.xtext.oclstdlib.parser.antlr.OCLstdlibParser;
import org.eclipse.ocl.examples.xtext.oclstdlib.scoping.OCLstdlibScopeProvider;
import org.eclipse.ocl.examples.xtext.oclstdlib.services.OCLstdlibValueConverterService;
import org.eclipse.ocl.examples.xtext.oclstdlib.utilities.OCLstdlibCSResource;
import org.eclipse.ocl.examples.xtext.oclstdlib.validation.OCLstdlibCompositeEValidator;
import org.eclipse.xtext.common.types.access.ClasspathTypeProviderFactory;
import org.eclipse.xtext.conversion.IValueConverterService;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.scoping.IScopeProvider;
import org.eclipse.xtext.validation.CompositeEValidator;

import com.google.inject.Binder;
import com.google.inject.name.Names;

/**
 * Use this class to register components to be used within the IDE.
 */
@SuppressWarnings("restriction")
public class OCLstdlibRuntimeModule extends org.eclipse.ocl.examples.xtext.oclstdlib.AbstractOCLstdlibRuntimeModule
{

	public Class<? extends CompositeEValidator> bindCompositeEValidator() {
		return OCLstdlibCompositeEValidator.class;
	}
	
	@Override
	public Class<? extends org.eclipse.xtext.parser.IParser> bindIParser() {
		return RetokenizingOCLstdlibParser.class;
	}

	public static class RetokenizingOCLstdlibParser extends OCLstdlibParser
	{
		@Override
		protected XtextTokenStream createTokenStream(TokenSource tokenSource) {
			return super.createTokenStream(new RetokenizingTokenSource(tokenSource, getTokenDefProvider().getTokenDefMap()));
		}
	}

	@Override
	public Class<? extends IValueConverterService> bindIValueConverterService() {
	  return OCLstdlibValueConverterService.class;
	}

	@Override
	public Class<? extends IScopeProvider> bindIScopeProvider() {
		return OCLstdlibScopeProvider.class;
	}
	
	public Class<? extends ClasspathTypeProviderFactory> bindClasspathTypeProviderFactory() {
		return ResourceSetClasspathTypeProviderFactory.class;
	}
	
	@Override
	public Class<? extends XtextResource> bindXtextResource() {
		return OCLstdlibCSResource.class;
	}
	
	/**
	 * @return The language injector provider
	 */
	public Class<? extends InjectorProvider> bindInjectorProvider() {
		return OCLstdlibInjectorProvider.class;
	}
	
	
	public void configureEObjectValidation(Binder binder) {
		binder.bindConstant().annotatedWith(Names.named(org.eclipse.xtext.validation.CompositeEValidator.USE_EOBJECT_VALIDATOR)).to(false);
	}
}
