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
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.xmi.XMIException;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.Constraint;
import org.eclipse.ocl.pivot.Detail;
import org.eclipse.ocl.pivot.NamedElement;

/**
 * EAnnotationConverter defines the basic behaviour for EAnnotation-source-specific functionalities for use
 * during Ecore2AS and AS2Ecore conversions.
 * It also provides a global registry of known EAnnotation-source functionalities.
 *
 * @since 7.0
 */
public interface EAnnotationConverter
{
	/**
	 * Register a default copy-unchanged handler for the EAnnotation source. This suppresses the diagnostic message
	 * that would otherwise accompany the aut-registration of a default copy for an unknown source.
	 */
	public static void addDefaultEAnnotationConverter(String source) {
		EAnnotationConverter eAnnotationConverter = AbstractEAnnotationConverter.basicGetEAnnotationConverter(source);
		if (eAnnotationConverter == null) {
			eAnnotationConverter = new AbstractEAnnotationConverter.DefaultEAnnotationConverter(source);
			AbstractEAnnotationConverter.addEAnnotationConverter(source, eAnnotationConverter);
		}
		assert eAnnotationConverter instanceof AbstractEAnnotationConverter.DefaultEAnnotationConverter : "Conflicting DefaultEAnnotationConverter '" + source + "'";
	}

	/**
	 * Register a known handler for the eAnnotationConverter under its regular eAnnotationConverter.getSource().
	 */
	public static void addEAnnotationConverter(@NonNull EAnnotationConverter eAnnotationConverter) {
		AbstractEAnnotationConverter.addEAnnotationConverter(eAnnotationConverter.getSource(), eAnnotationConverter);
	}

	/**
	 * Register a known handler for the eAnnotationConverter under an explicit source,
	 * typically to re-use eAnnotationConverter for multiple sources..
	 */
	public static void addEAnnotationConverter(@NonNull String source, @NonNull EAnnotationConverter eAnnotationConverter) {
		AbstractEAnnotationConverter.addEAnnotationConverter(source, eAnnotationConverter);
	}

	/**
	 * Register the eAnnotationConverter under an explicit source. If eAnnotation's eContainer() is also an EAnnotation,
	 * the AbstractEAnnotationConverter.NESTED_SOURCE is returned to copy nested EAnnotations unchanged. Otherwise
	 * AbstractEAnnotationConverter.getEAnnotationConverter(source) is invoked for the eAnnotation's source.
	 */
	public static @NonNull EAnnotationConverter getEAnnotationConverter(@NonNull EAnnotation eAnnotation) {
		String source = eAnnotation.eContainer() instanceof EAnnotation ? AbstractEAnnotationConverter.NESTED_SOURCE : eAnnotation.getSource();
		return AbstractEAnnotationConverter.getEAnnotationConverter(source);
	}

	/**
	 * Return the EAnnotationConverter registered for a specific EAnnotation source. If the source is unknown,
	 * a default copy-unchanged EAnnotationConverter is registered and returned.
	 */
	public static @NonNull EAnnotationConverter getEAnnotationConverter(/*@NonNull*/ String source) {
		return AbstractEAnnotationConverter.getEAnnotationConverter(source);
	}

	public void convertDetail(@NonNull Detail asDetail, @NonNull EAnnotation eAnnotation);

	/**
	 * Convert the Pivot-supported details of eAnnotation to their Pivot counterparts in asElement.
	 * Returns true if default support is needed for further details.
	 */
	public boolean convertEAnnotationDetails(@NonNull EAnnotation eAnnotation, @NonNull NamedElement asElement) throws XMIException;

	/**
	 * Return a non-Pivot-supported detail to its default Pivot counterpart.
	 * Return null if the detail was converted by convertEAnnotationDetails().
	 */
	public @Nullable Detail convertEDetail(@NonNull Entry<String, String> eDetail);

	public @Nullable String getSource();

	public @Nullable List<@NonNull Constraint> refreshEAnnotation(@NonNull EAnnotation eAnnotation, @NonNull EMap<String, String> oclAnnotationDetails,
			Map<String, Constraint> newConstraintMap);
}
