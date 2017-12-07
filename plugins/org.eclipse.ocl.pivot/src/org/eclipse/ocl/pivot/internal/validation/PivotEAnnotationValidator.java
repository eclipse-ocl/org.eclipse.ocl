/*******************************************************************************
 * Copyright (c) 2017 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.internal.validation;

import java.util.Map;

import org.eclipse.emf.ecore.EAnnotationValidator;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;

/**
 * PivotEAnnotationValidator exploits the custom EAnnotationValidator support added by EMF 2.14.
 * It provides null functionality prior to EMF 2.14M2.
 *
 * @since 1.4
 */
public class PivotEAnnotationValidator {

	/**
	 * Set true if EMF has EAnnotationValidator support, else false and there is no EAnnotationValidator checking.
	 */
	private static @Nullable Map<String, Object> eAnnotationValidatorRegistry = null;
	static {
		try {
			//			Class<?> eAnnotationValidatorClass = Class.forName("org.eclipse.emf.ecore.EAnnotationValidator");
			Class<?> eAnnotationValidatorRegistryClass = Class.forName("org.eclipse.emf.ecore.EAnnotationValidator$Registry");
			@SuppressWarnings("unchecked")
			Map<String, Object> EAnnotationValidatorRegistryInstance = (Map<String, Object>)eAnnotationValidatorRegistryClass.getField("INSTANCE").get(null);
			eAnnotationValidatorRegistry = EAnnotationValidatorRegistryInstance;
		}
		catch (Exception e) {}
	}

	/**
	 * Return true if there is EAnnotationValidator support.
	 */
	public static boolean hasEcoreEAnnotationValidators() {
		return eAnnotationValidatorRegistry != null;
	}

	public static boolean initializeEcoreEAnnotationValidators() {
		Map<String, Object> eAnnotationValidatorRegistry2 = eAnnotationValidatorRegistry;
		if (eAnnotationValidatorRegistry2 != null) {
			try {
				installAnnotationValidator(eAnnotationValidatorRegistry2, "org.eclipse.emf.ecore.util.EcoreAnnotationValidator");
				installAnnotationValidator(eAnnotationValidatorRegistry2, "org.eclipse.emf.ecore.util.ExtendedMetaDataAnnotationValidator");
				installAnnotationValidator(eAnnotationValidatorRegistry2, "org.eclipse.emf.codegen.ecore.genmodel.util.GenModelAnnotatonValidator");
				installAnnotationValidator(eAnnotationValidatorRegistry2, "org.eclipse.ocl.pivot.internal.validation.Ecore_OCL_AnnotationValidator$Blank");
				installAnnotationValidator(eAnnotationValidatorRegistry2, "org.eclipse.ocl.pivot.internal.validation.Ecore_OCL_AnnotationValidator$Debug");
				installAnnotationValidator(eAnnotationValidatorRegistry2, "org.eclipse.ocl.pivot.internal.validation.Ecore_OCL_AnnotationValidator$Pivot");
				installAnnotationValidator(eAnnotationValidatorRegistry2, "org.eclipse.ocl.pivot.internal.validation.OCL_ASLibrary_AnnotationValidator");
				installAnnotationValidator(eAnnotationValidatorRegistry2, "org.eclipse.ocl.pivot.internal.validation.OCL_ASMetamodel_AnnotationValidator");
				installAnnotationValidator(eAnnotationValidatorRegistry2, "org.eclipse.ocl.pivot.internal.validation.OCL_Collection_AnnotationValidator");
				installAnnotationValidator(eAnnotationValidatorRegistry2, "org.eclipse.ocl.pivot.internal.validation.OCL_Import_AnnotationValidator");
				return true;
			}
			catch (Exception e) {}
		}
		return false;
	}

	/**
	 * Install the annotationValidatorClassName in the eAnnotationValidatorRegistry using reflection
	 * to load the class in case it does not exist in EMF, or it inherits from an non-existent EMF class.
	 */
	protected static void installAnnotationValidator(@NonNull Map<String, Object> eAnnotationValidatorRegistry, @NonNull String annotationValidatorClassName) {
		try {
			Class<?> annotationValidatorClass = Class.forName(annotationValidatorClassName);
			Object annotationValidatorInstance = annotationValidatorClass.getField("INSTANCE").get(null);
			if (annotationValidatorInstance instanceof EAnnotationValidator) {
				String annotationSource = ((EAnnotationValidator)annotationValidatorInstance).getAnnotationSource();
				if (!eAnnotationValidatorRegistry.containsKey(annotationSource)) {
					eAnnotationValidatorRegistry.put(annotationSource, annotationValidatorInstance);
				}
			}
		}
		catch (Exception e) {}
	}
}
