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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.util.ExtendedMetaData;
import org.eclipse.emf.ecore.xmi.XMIException;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.common.OCLConstants;
import org.eclipse.ocl.pivot.Constraint;
import org.eclipse.ocl.pivot.Detail;
import org.eclipse.ocl.pivot.NamedElement;
import org.eclipse.ocl.pivot.PivotFactory;
import org.eclipse.ocl.pivot.util.DerivedConstants;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.utilities.StringUtil;

/**
 * AbstractEAnnotationConverter defines the basic behaviour for EAnnotation-source-specific functionalities.
 * It also provides a global registry of known EAnnotation-source functionalities.
 *
 * @since 7.0
 */
public abstract class AbstractEAnnotationConverter implements EAnnotationConverter		// XXX add extension point support
{
	static final Logger logger = Logger.getLogger(AbstractEAnnotationConverter.class);

	private static final @NonNull Map<@Nullable String, @NonNull EAnnotationConverter> source2handler = new HashMap<>();

	/*package*/ static final String NESTED_SOURCE = "«nested»";

	/*package*/ static void addEAnnotationConverter(@Nullable String source, @NonNull EAnnotationConverter eAnnotationConverter) {
		EAnnotationConverter old = source2handler.put(source, eAnnotationConverter);
		assert (old == null) || (old == eAnnotationConverter) : "Conflicting EAnnotationConverter for '" + source + "'";
	}

	/*package*/ static EAnnotationConverter basicGetEAnnotationConverter(/*@NonNull*/ String source) {
		return source2handler.get(source);
	}

	@Override
	public @Nullable Detail convertEDetail(@NonNull Entry<String, String> eDetail) {
		Detail asDetail = PivotFactory.eINSTANCE.createDetail();
		asDetail.setName(eDetail.getKey());
		asDetail.getValues().add(eDetail.getValue());
		return asDetail;
	}

	/*package*/ static @NonNull EAnnotationConverter getEAnnotationConverter(/*@NonNull*/ String source) {
	//	assert source != null;		-- testSerialize_Bug354336has a null (missing) source
		EAnnotationConverter eAnnotationConverter = source2handler.get(source);
		if (eAnnotationConverter == null) {
			int index = source.indexOf("#");
			if (index > 0) {
				source = source.substring(0, index);
				eAnnotationConverter = source2handler.get(source);
			}
			if (eAnnotationConverter == null) {
				eAnnotationConverter = new DefaultEAnnotationConverter(source);
				PivotUtil.errPrintln("Registering DefaultEAnnotationConverter for " + source);
				addEAnnotationConverter(source, eAnnotationConverter);
			}
		}
		return eAnnotationConverter;
	}

	public static class DefaultEAnnotationConverter extends AbstractEAnnotationConverter
	{
		public DefaultEAnnotationConverter(@Nullable String source) {
			super(source);
		}

		@Override
		public boolean convertEAnnotationDetails(@NonNull EAnnotation eAnnotation, @NonNull NamedElement asElement) {
			return true;				// normal copy
		}
	}

	/**
	 * The EAnnotation.source for which this AbstractEAnnotationConverter provides support.
	 */
	protected final @Nullable String source;		// The EAnnotation source

	protected AbstractEAnnotationConverter(@Nullable String source) {
		this.source = source;
		addEAnnotationConverter(source, this);
	}

	@Override
	public void convertDetail(@NonNull Detail asDetail, @NonNull EAnnotation eAnnotation) {
		String value = StringUtil.splice(asDetail.getValues(), "");
		eAnnotation.getDetails().put(asDetail.getName(), value);
	}

	@Override
	public boolean convertEAnnotationDetails(@NonNull EAnnotation eAnnotation, @NonNull NamedElement asElement) throws XMIException {
		throw new UnsupportedOperationException("Unsupported " + getClass().getName() + ".convertEAnnotationDetails() for " + this);
	}

	protected @NonNull EAnnotation getEAnnotation(@NonNull EModelElement eModelElement) {
		EAnnotation eAnnotation = eModelElement.getEAnnotation(source);
		if (eAnnotation == null) {
			eAnnotation = EcoreFactory.eINSTANCE.createEAnnotation();
			eAnnotation.setSource(source);
			eModelElement.getEAnnotations().add(eAnnotation);
		}
		return eAnnotation;
	}

	@Override
	public final @Nullable String getSource() {
		return source;
	}

	@Override
	public @Nullable List<@NonNull Constraint> refreshEAnnotation(@NonNull EAnnotation eAnnotation, @NonNull EMap<String, String> oclAnnotationDetails,
			Map<String, Constraint> newConstraintMap) {
		throw new UnsupportedOperationException("Unsupported " + getClass().getName() + ".refreshEAnnotation() for " + this);
	}

	@Override
	public @NonNull String toString() {
		return String.valueOf(source);
	}

	static {
		EMF_Ecore_EAnnotationConverter.getInstance();
		OCL_Pivot_EAnnotationConverter pivotInstance = OCL_Pivot_EAnnotationConverter.getInstance();
		EAnnotationConverter.addEAnnotationConverter(OCLConstants.OCL_DELEGATE_URI, pivotInstance);
		EAnnotationConverter.addEAnnotationConverter(OCLConstants.OCL_DELEGATE_URI_DEBUG, pivotInstance);
		EAnnotationConverter.addEAnnotationConverter(OCLConstants.OCL_DELEGATE_URI_LPG, pivotInstance);
		EAnnotationConverter.addEAnnotationConverter(DerivedConstants.UML2_GEN_MODEL_PACKAGE_2_0_NS_URI, pivotInstance);		// XXX why handled -- historical typo ??
		EAnnotationConverter.addEAnnotationConverter(DerivedConstants.UML2_GEN_MODEL_PACKAGE_1_1_NS_URI, pivotInstance);		// XXX why handled -- historical typo ??
		OCL_Pivot_Dynamic_EAnnotationConverter.getInstance();
		EMF_GenModel_EAnnotationConverter.getInstance();
		MOF_XML_EAnnotationConverter.getInstance();
		OCL_Collection_EAnnotationConverter.getInstance();
		OCL_Import_EAnnotationConverter.getInstance();
		OCL_ASLibrary_EAnnotationConverter.getInstance();
		OCL_ASMetamodel_EAnnotationConverter.getInstance();
		OCL_Pivot_Operation_EAnnotationConverter.getInstance();
		UML_EAnnotationConverter.getInstance();					// validateXXX has XXX as an originalName

		EAnnotationConverter.addDefaultEAnnotationConverter(NESTED_SOURCE);
		//
		// Declare some common EAnnotations to suppress a diagnostic from auto-copy registration.
		//
		EAnnotationConverter.addDefaultEAnnotationConverter(ExtendedMetaData.ANNOTATION_URI);
		EAnnotationConverter.addDefaultEAnnotationConverter("http://www.eclipse.org/emf/CDO");
		EAnnotationConverter.addDefaultEAnnotationConverter("http://www.w3.org/XML/1998/namespace");
	}
}
