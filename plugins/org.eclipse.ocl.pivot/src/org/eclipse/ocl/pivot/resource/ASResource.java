/*******************************************************************************
 * Copyright (c) 2010, 2015 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.resource;

import java.util.Map;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.Model;
import org.eclipse.ocl.pivot.PivotPackage;
import org.eclipse.ocl.pivot.internal.resource.ASResourceFactory;
import org.eclipse.ocl.pivot.utilities.PivotConstants;

/**
 * A resource for an OCL Abstract Syntax (Pivot) Model
 */
public interface ASResource extends XMIResource
{
	/**
	 * {@link Resource#save(Map)} option.
	 * <p>
	 * Use UUIDs as the xmi:id of each element, such as a specialization, that cannot be referenced externally.
	 * The default behavior is to leave such elements with a shorter simple sequential xmi:id.
	 * <p>
	 * The XMI file may be safely edited manually without disrupting references, but
	 * regeneration will produce a new set of UUIDs resulting in unstable content for the purposes
	 * of configuration management.
	 * <p>
	 * More pragmatically UUIDs may be specified to assist in debugging the correct generation of stable
	 * xmi:ids for externally referenceable elements: no @'s should remain.
	 */
	String OPTION_INTERNAL_UUIDS = "INTERNAL_UUIDS";

	/**
	 * {@link Resource#save(Map)} option.
	 * <p>
	 * Rearrange the contents to enforce alphabetic ordering and so ensure stable contents after
	 * regeneration.
	 */
	String OPTION_NORMALIZE_CONTENTS = "NORMALIZE_CONTENTS";

	/**
	 * The file extension for OCL Abstract Syntax resources.
	 */
	@NonNull String FILE_EXTENSION = PivotConstants.OCL_AS_FILE_EXTENSION;

	/**
	 * The default encoding for OCL Abstract Syntax resources.
	 */
	@NonNull String DEFAULT_ENCODING = "UTF-8"; //$NON-NLS-1$

	@NonNull String CONTENT_TYPE = PivotPackage.eCONTENT_TYPE;

	@NonNull String COMPLETE_OCL_CONTENT_TYPE = CONTENT_TYPE + "." + PivotConstants.OCL_FILE_EXTENSION;
	@NonNull String ECORE_CONTENT_TYPE = CONTENT_TYPE + ".ecore";
	@NonNull String ESSENTIALOCL_CONTENT_TYPE = CONTENT_TYPE + "." + PivotConstants.ESSENTIAL_OCL_FILE_EXTENSION;;
	//	@NonNull String LIBRARY_CONTENT_TYPE = CONTENT_TYPE + ".library";
	@NonNull String OCLINECORE_CONTENT_TYPE = CONTENT_TYPE + "." + PivotConstants.OCLINECORE_FILE_EXTENSION;
	@NonNull String OCLSTDLIB_CONTENT_TYPE = CONTENT_TYPE + "." + PivotConstants.OCLSTDLIB_FILE_EXTENSION;
	@NonNull String UML_CONTENT_TYPE = CONTENT_TYPE + ".uml";

	// FIXME add API	@Nullable EObject basicGetEObjectByID(@Nullable String id);

	/**
	 * Return the ASResourceFactory that created this ASResource and which may be used
	 * to create further artefacts.
	 */
	@NonNull ASResourceFactory getASResourceFactory();

	/**
	 * Return the Model that provides the sole root content. Throws an IllegalStateException if there is none.
	 */
	@NonNull Model getModel();
}
