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
package org.eclipse.ocl.pivot.internal.resource;

import java.util.Map;

import org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.util.ExtendedMetaData;
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
				Class<?> EcoreAnnotationValidatorClass = Class.forName("org.eclipse.emf.ecore.util.EcoreAnnotationValidator");
				Class<?> ExtendedMetaDataAnnotationValidatorClass = Class.forName("org.eclipse.emf.ecore.util.ExtendedMetaDataAnnotationValidator");
				Class<?> GenModelAnnotatonValidatorClass = Class.forName("org.eclipse.emf.codegen.ecore.genmodel.util.GenModelAnnotatonValidator");
				Object EcoreAnnotationValidatorInstance = EcoreAnnotationValidatorClass.getField("INSTANCE").get(null);
				Object ExtendedMetaDataAnnotationValidatorInstance = ExtendedMetaDataAnnotationValidatorClass.getField("INSTANCE").get(null);
				Object GenModelAnnotatonValidatorInstance = GenModelAnnotatonValidatorClass.getField("INSTANCE").get(null);
				if (!eAnnotationValidatorRegistry2.containsKey(EcorePackage.eNS_URI)) {
					eAnnotationValidatorRegistry2.put(EcorePackage.eNS_URI, EcoreAnnotationValidatorInstance);
				}
				if (!eAnnotationValidatorRegistry2.containsKey(ExtendedMetaData.ANNOTATION_URI)) {
					eAnnotationValidatorRegistry2.put(ExtendedMetaData.ANNOTATION_URI, ExtendedMetaDataAnnotationValidatorInstance);
				}
				if (!eAnnotationValidatorRegistry2.containsKey(GenModelPackage.eNS_URI)) {
					eAnnotationValidatorRegistry2.put(GenModelPackage.eNS_URI, GenModelAnnotatonValidatorInstance);
				}
				return true;
			}
			catch (Exception e) {}
		}
		return false;
	}
}
