/*******************************************************************************
 * Copyright (c) 2010, 2018 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.xtext.oclstdlib.utilities;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.XMLSave;
import org.eclipse.emf.ecore.xmi.impl.XMIHelperImpl;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.Precedence;
import org.eclipse.ocl.pivot.internal.resource.ASResourceFactory;
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal;
import org.eclipse.ocl.pivot.resource.ASResource;
import org.eclipse.ocl.pivot.resource.CSResource;
import org.eclipse.ocl.xtext.base.cs2as.CS2AS;
import org.eclipse.ocl.xtext.basecs.JavaClassCS;
import org.eclipse.ocl.xtext.essentialocl.utilities.EssentialOCLCSResource;
import org.eclipse.ocl.xtext.oclstdlib.cs2as.OCLstdlibCS2AS;
import org.eclipse.ocl.xtext.oclstdlibcs.MetaclassNameCS;

public class OCLstdlibCSResource extends EssentialOCLCSResource
{
	/**
	 * An OCLstdlibCSResourceLoadFactory supports creation of a BaseCSXMIResource that supports persistence of the CS model directly as XMI
	 * rather than exploiting Xtext to serialize to / parse from a text file.
	 */
	public static class OCLstdlibCSResourceLoadFactory extends OCLCSResourceLoadFactory
	{
		public OCLstdlibCSResourceLoadFactory() {
			super(OCLstdlibASResourceFactory.getInstance());
		}
	}

	/**
	 * OCLstdlibCSResourceSave refines saving the contents of a CS Resource using regular XMI serialization
	 * to support proprietary URIs for the Java class and OCL metaclassl references.
	 */
	protected static class OCLstdlibCSResourceSave extends OCLCSResourceSave
	{
		/**
		 * CSXMISaveHelper extends the overload getHREF to provide simple proprietary URIs for the Java class and OCL metaclassl references.
		 */
		protected static final class OCLstdlibCSXMISaveHelper extends CSXMISaveHelper
		{
			protected OCLstdlibCSXMISaveHelper(@NonNull XMLResource xmiResource, @NonNull CSResource csResource) {
				super(xmiResource, csResource);
			}

			@Override
			public String getHREF(EObject obj) {
				if (obj instanceof JavaClassCS) {
					return "ocl-j#" + ((JavaClassCS)obj).getName();
				}
				if (obj instanceof MetaclassNameCS) {
					return "ocl-m#" + ((MetaclassNameCS)obj).getName();
				}
				if (obj instanceof Precedence) {
					return "ocl-p#" + ((Precedence)obj).getName();		// XXX CS element should be useable
				}
				return super.getHREF(obj);
			}
		}

		public OCLstdlibCSResourceSave(@NonNull URI uri, @NonNull ASResourceFactory asResourceFactory, @NonNull CSResource csResource) {
			super(uri, asResourceFactory, csResource);
		}

	//	@Override
	//	public @NonNull CS2AS createCS2AS(@NonNull EnvironmentFactoryInternal environmentFactory, @NonNull ASResource asResource) {
	//		return (CS2AS)csResource.createCS2AS(environmentFactory, asResource);
	//	}

		@Override
		protected @NonNull XMLSave createXMLSave() {
			XMIHelperImpl xmlHelper = new OCLstdlibCSXMISaveHelper(this, this.csResource);
			return new CSXMISave(xmlHelper);
		}
	}

	@Override
	public @NonNull CS2AS createCS2AS(@NonNull EnvironmentFactoryInternal environmentFactory, @NonNull ASResource asResource) {
		return new OCLstdlibCS2AS(environmentFactory, this, asResource);
	}

	@Override
	protected @NonNull OCLCSResourceSave createCSResourceSave(@NonNull URI uri) {
		return new OCLstdlibCSResourceSave(uri, getASResourceFactory(), this);
	}

	@Override
	public @NonNull String getASContentType() {
		return ASResource.OCLSTDLIB_CONTENT_TYPE;
	}

	@Override
	public @NonNull ASResourceFactory getASResourceFactory() {
		return OCLstdlibASResourceFactory.getInstance();
	}

	@Override
	public @NonNull String getEditorName() {
		return "OCL Standard Library";
	}
}
