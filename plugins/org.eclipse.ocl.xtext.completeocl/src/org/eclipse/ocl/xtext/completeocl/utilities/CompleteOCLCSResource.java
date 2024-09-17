/*******************************************************************************
 * Copyright (c) 2011, 2018 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.xtext.completeocl.utilities;

import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceFactoryImpl;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.internal.resource.ASResourceFactory;
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal;
import org.eclipse.ocl.pivot.resource.ASResource;
import org.eclipse.ocl.xtext.base.as2cs.AS2CS;
import org.eclipse.ocl.xtext.base.cs2as.CS2AS;
import org.eclipse.ocl.xtext.base.utilities.BaseCSResource;
import org.eclipse.ocl.xtext.completeocl.as2cs.CompleteOCLAS2CS;
import org.eclipse.ocl.xtext.completeocl.cs2as.CompleteOCLCS2AS;
import org.eclipse.ocl.xtext.essentialocl.utilities.EssentialOCLCSResource;

public class CompleteOCLCSResource extends EssentialOCLCSResource
{
	/**
	 * The CompleteOCLCSResourceLoad implementation of BaseCSResource that ensures that loading resolves references to CS/ES elements
	 * to equivalent AS references and conversely ensures that saving replaces AS references by CS/ES references.
	 */
	public static class CompleteOCLCSResourceLoad extends OCLCSResourceLoad
	{
		/**
		 * Creates an instance of the resource.
		 */
		public CompleteOCLCSResourceLoad(@NonNull URI uri) {
			super(uri, CompleteOCLASResourceFactory.getInstance());
		}

		@Override
		public @NonNull CS2AS createCS2AS(@NonNull EnvironmentFactoryInternal environmentFactory, @NonNull ASResource asResource) {
			return new CompleteOCLCS2AS(environmentFactory, this, asResource);
		}
	}

	/**
	 * A CompleteOCLCSResourceLoadFactory supports creation of an CompleteOCLCSXMIResourceImpl that supports persistence of the CS model directly as XMI
	 * rather than exploiting Xtext to serialize to / parse from a text file.
	 */
	public static class CompleteOCLCSResourceLoadFactory extends ResourceFactoryImpl
	{
		/**
		 * Creates an instance of the resource factory.
		 */
		public CompleteOCLCSResourceLoadFactory() {}

		@Override
		public Resource createResource(URI uri) {
			assert uri != null;
			return new CompleteOCLCSResourceLoad(uri);
		}
	}

	@Override
	public @NonNull AS2CS createAS2CS(@NonNull Map<@NonNull ? extends BaseCSResource, @NonNull ? extends ASResource> cs2asResourceMap,
			@NonNull EnvironmentFactoryInternal environmentFactory) {
		return new CompleteOCLAS2CS(cs2asResourceMap, environmentFactory);
	}

	@Override
	public @NonNull CS2AS createCS2AS(@NonNull EnvironmentFactoryInternal environmentFactory, @NonNull ASResource asResource) {
		return new CompleteOCLCS2AS(environmentFactory, this, asResource);
	}

	@Override
	public @NonNull String getASContentType() {
		return ASResource.COMPLETE_OCL_CONTENT_TYPE;
	}

	@Override
	public @NonNull ASResourceFactory getASResourceFactory() {
		return CompleteOCLASResourceFactory.getInstance();
	}

	@Override
	public @NonNull String getEditorName() {
		return "Complete OCL";
	}
}
