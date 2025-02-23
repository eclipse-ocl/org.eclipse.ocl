/*******************************************************************************
 * Copyright (c) 2025 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.internal.ecore.annotations;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.Import;
import org.eclipse.ocl.pivot.Model;
import org.eclipse.ocl.pivot.NamedElement;
import org.eclipse.ocl.pivot.Namespace;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.PivotConstants;
import org.eclipse.ocl.pivot.utilities.URIUtil;

/**
 * The OCL_Import_EAnnotationConverter maps the Element ownedComments field to the
 * http://www.eclipse.org/OCL/Import documentation detail of an EModelElement.
 * Other http://www.eclipse.org/OCL/Import details are copied.
 *
 * @since 1.23
 */
public class OCL_Import_EAnnotationConverter extends AbstractEAnnotationConverter
{
	private static final @NonNull OCL_Import_EAnnotationConverter INSTANCE = new OCL_Import_EAnnotationConverter();

	public static @NonNull OCL_Import_EAnnotationConverter getInstance() {
		return INSTANCE;
	}

	private OCL_Import_EAnnotationConverter() {
		super(PivotConstants.IMPORT_ANNOTATION_SOURCE);
	}

	public void convertAnnotations(@NonNull Import anImport, URI ecoreURI, int noNames, @NonNull EModelElement eModelElement) {
		Namespace importedNamespace = anImport.getImportedNamespace();
		if (importedNamespace != null) {
			EAnnotation importAnnotation = getEAnnotation(eModelElement);
			String value;
			if (importedNamespace instanceof Model) {
				value = ((Model)importedNamespace).getExternalURI();
			}
			else {
				EObject eTarget = importedNamespace.getESObject();
				if (eTarget != null) {
					URI uri = null;
					Resource eResource = eTarget.eResource();
					if ((eTarget instanceof EPackage) && ClassUtil.isRegistered(eResource)) {
						uri = eResource.getURI();
					}
					if (uri == null) {
						uri = EcoreUtil.getURI(eTarget);
					}
					URI uri2 = URIUtil.deresolve(uri, ecoreURI, true, true, true);
					value = uri2.toString();
				}
				else if (importedNamespace instanceof org.eclipse.ocl.pivot.Package) {
					value = ((org.eclipse.ocl.pivot.Package)importedNamespace).getURI();
				}
				else {
					value = importedNamespace.toString();
				}
			}
			String key = anImport.getName();
			if ((noNames > 1) && ((key == null) || "".equals(key))) {
				key = value;
				value = null;
			}
			String oldValue = importAnnotation.getDetails().put(key, value);
			if (oldValue != null) {
				System.out.println("Conflicting " + PivotConstants.IMPORT_ANNOTATION_SOURCE + " for \"" + key + "\" => \"" + oldValue + "\" / \"" + value + "\"");
			}
		}
	}

	@Override
	public boolean convertEAnnotationDetails(@NonNull EAnnotation eAnnotation, @NonNull NamedElement asElement) {
		return false;		// Suppressed - imports are explicitly processed in Ecore2AS.loadImports before regular conversions.
	}
}