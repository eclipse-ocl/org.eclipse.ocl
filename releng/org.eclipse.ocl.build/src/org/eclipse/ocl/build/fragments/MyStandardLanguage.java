/*******************************************************************************
 * Copyright (c) 2023 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.build.fragments;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.xtext.resource.IClasspathUriResolver;
import org.eclipse.xtext.resource.XtextResourceSet;
import org.eclipse.xtext.xtext.generator.Issues;
import org.eclipse.xtext.xtext.generator.StandardLanguage;

/**
 * This override installs MyClassloaderClasspathUriResolver to invert the classpath: URI conversion
 * of XtextLinkingService.getUsedGrammar()
 */
public class MyStandardLanguage extends StandardLanguage
{
	private boolean initialized = false;

	public MyStandardLanguage() {
		super();
	}

	@Override
	public void checkConfiguration(Issues issues) {
		super.checkConfiguration(issues);
		String grammarUri = getGrammarUri();
		URI uri = URI.createURI(grammarUri);
		if (!uri.isPlatformResource()) {
			issues.addWarning("grammarUri should be a 'platform:' URI to support use as a rule locator");
		}
	}

	@Override
	public String getGrammarUri() {
		if (!initialized) {
			initialized = true;
			ResourceSet resourceSet = getResourceSet();
			if (resourceSet instanceof XtextResourceSet) {
				XtextResourceSet xtextResourceSet = (XtextResourceSet)resourceSet;
				IClasspathUriResolver resolver = new MyClassloaderClasspathUriResolver(xtextResourceSet);
				xtextResourceSet.setClasspathUriResolver(resolver);
			}
		}
		return super.getGrammarUri();
	}
}
