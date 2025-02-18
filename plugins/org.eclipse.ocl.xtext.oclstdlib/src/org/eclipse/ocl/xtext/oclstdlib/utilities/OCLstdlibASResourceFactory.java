/*******************************************************************************
 * Copyright (c) 2014, 2025 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.xtext.oclstdlib.utilities;

import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.ecore.resource.ContentHandler;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.internal.resource.ASResourceFactory;
import org.eclipse.ocl.pivot.internal.resource.AbstractASResourceFactory;
import org.eclipse.ocl.pivot.internal.resource.ICS2AS;
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal;
import org.eclipse.ocl.pivot.resource.ASResource;
import org.eclipse.ocl.pivot.resource.CSResource;
import org.eclipse.ocl.pivot.resource.NotXMLContentHandlerImpl;
import org.eclipse.ocl.pivot.utilities.PivotConstants;
import org.eclipse.ocl.xtext.oclstdlib.cs2as.OCLstdlibCS2AS;

public final class OCLstdlibASResourceFactory extends AbstractASResourceFactory
{
	private static @Nullable OCLstdlibASResourceFactory INSTANCE = null;

	public static synchronized @NonNull OCLstdlibASResourceFactory getInstance() {
		if (INSTANCE == null) {
			//			ASResourceFactoryContribution asResourceRegistry = ASResourceFactoryRegistry.INSTANCE.get(ASResource.OCLSTDLIB_CONTENT_TYPE);
			//			if (asResourceRegistry != null) {
			//				INSTANCE = (OCLstdlibASResourceFactory) asResourceRegistry.getASResourceFactory();	// Create the registered singleton
			//			}
			//			else {
			INSTANCE = new OCLstdlibASResourceFactory();										// Create our own singleton
			//			}
			assert INSTANCE != null;
			INSTANCE.install(PivotConstants.OCLSTDLIB_FILE_EXTENSION, null);
		}
		assert INSTANCE != null;
		return INSTANCE;
	}

	static {
		if (!EMFPlugin.IS_ECLIPSE_RUNNING) {
			installContentHandler(ContentHandler.Registry.NORMAL_PRIORITY,
				new NotXMLContentHandlerImpl(new @NonNull String[]{PivotConstants.OCLSTDLIB_FILE_EXTENSION}));
		}
	}

	public OCLstdlibASResourceFactory() {
		super(ASResource.OCLSTDLIB_CONTENT_TYPE, null);
	}

	@Override
	public @NonNull ICS2AS createCS2AS(@NonNull EnvironmentFactoryInternal environmentFactory, @NonNull CSResource csResource, @NonNull ASResource asResource) {
		return new OCLstdlibCS2AS(environmentFactory, csResource, asResource);
	}

	@Override
	public @NonNull ASResourceFactory getASResourceFactory() {
		return getInstance();
	}
}
