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
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.xtext.resource.IClasspathUriResolver;
import org.eclipse.xtext.resource.XtextResourceSet;

/**
 * This override inverts the classpath: URI conversion of XtextLinkingService.getUsedGrammar().
 */
public class MyClassloaderClasspathUriResolver implements IClasspathUriResolver
{
	protected final @NonNull XtextResourceSet xtextResourceSet;

	public MyClassloaderClasspathUriResolver(@NonNull XtextResourceSet xtextResourceSet) {
		this.xtextResourceSet = xtextResourceSet;
	}

	@Override
	public URI resolve(Object context, URI classpathUri) {
		StringBuilder s1 = new StringBuilder();
		StringBuilder s2 = new StringBuilder();
		@NonNull String[] segments = classpathUri.segments();
		int iMax = segments.length;
		for (int i = 0; i < iMax; i++) {
			String segment = segments[i];
			s1.append("/");
			s1.append(segment);
			if (i+1 < iMax) {
				s2.append(i == 0 ? "/" : ".");
				s2.append(segment);
			}
		}
		String name = s2.toString() + "/src" + s1.toString();
		return URI.createPlatformResourceURI(name, true);
	}
}
