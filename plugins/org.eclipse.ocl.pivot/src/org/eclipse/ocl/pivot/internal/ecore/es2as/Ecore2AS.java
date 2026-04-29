/*******************************************************************************
 * Copyright (c) 2010, 2026 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - initial API and implementation
 *   E.D.Willink (CEA List) - Bug 424057 - UML 2.5 CG *******************************************************************************/
package org.eclipse.ocl.pivot.internal.ecore.es2as;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.ETypeParameter;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.XMIException;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.Model;
import org.eclipse.ocl.pivot.internal.resource.StandaloneProjectMap;
import org.eclipse.ocl.pivot.internal.utilities.External2AS;
import org.eclipse.ocl.pivot.resource.ProjectManager;
import org.eclipse.ocl.pivot.util.PivotPlugin;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.EnvironmentFactory;
import org.eclipse.ocl.pivot.utilities.PivotConstants;
import org.eclipse.ocl.pivot.utilities.TracingOption;
import org.eclipse.ocl.pivot.utilities.ValueUtil;
import org.eclipse.ocl.pivot.values.IntegerValue;
import org.eclipse.ocl.pivot.values.UnlimitedNaturalValue;

/**
 * Manage conversion/mapping of a *.ecore model to a *.ecore.oclas.
 *
 * @since 7.0
 */
public abstract class Ecore2AS extends AbstractExternal2AS
{
	/**
	 * @since 1.3
	 */
	public static final @NonNull TracingOption NOT_OPTIONAL = new TracingOption(PivotPlugin.PLUGIN_ID, "ecore2as/notOptional");

	/**
	 * @since 7.0
	 */
	public static @Nullable EMap<String, String> basicGetCollectionDetails(@NonNull EGenericType eGenericType) {
		for (EObject eObject = eGenericType; (eObject != null); eObject = eObject.eContainer()) {
			if (eObject instanceof EModelElement) {
				for (EAnnotation eAnnotation : ((EModelElement)eObject).getEAnnotations()) {
					if (PivotConstants.COLLECTION_ANNOTATION_SOURCE.equals(eAnnotation.getSource())) {
						List<EObject> references = eAnnotation.getReferences();
						if (!references.isEmpty() && (references.get(0) == eGenericType)) {
							return eAnnotation.getDetails();
						}
					}
				}
				break;
			}
		}
		return null;
	}

	/**
	 * @since 7.0
	 */
	public static @NonNull ExternalEcore2AS getAdapter(@NonNull Resource resource, @NonNull EnvironmentFactory environmentFactory) {
	//	return ExternalEcore2AS.getAdapter(resource, environmentFactory);
		return (ExternalEcore2AS)External2AS.getAdapter(resource, environmentFactory);
	}

	/**
	 * @since 7.0
	 */
	public static boolean getCollectionIsNullFreeDetail(@Nullable EMap<String, String> eDetails) {
		if (eDetails != null) {
			String isNullFreeText = eDetails.get(PivotConstants.COLLECTION_IS_NULL_FREE);
			if (isNullFreeText != null) {
				return Boolean.parseBoolean(isNullFreeText);
			}
		}
		return PivotConstants.DEFAULT_IS_NULL_FREE;
	}

	/**
	 * @since 7.0
	 */
	public static @NonNull IntegerValue getCollectionLowerValueDetail(@Nullable EMap<String, String> eDetails) {
		if (eDetails != null) {
			String lowerText = eDetails.get(PivotConstants.COLLECTION_LOWER);
			if (lowerText != null) {
				return ValueUtil.integerValueOf(lowerText);
			}
		}
		return PivotConstants.DEFAULT_LOWER_BOUND;
	}

	/**
	 * @since 7.0
	 */
	public static @NonNull UnlimitedNaturalValue getCollectionUpperValueDetail(@Nullable EMap<String, String> eDetails) {
		if (eDetails != null) {
			String upperText = eDetails.get(PivotConstants.COLLECTION_UPPER);
			if (upperText != null) {
				return ValueUtil.unlimitedNaturalValueOf(upperText);
			}
		}
		return PivotConstants.DEFAULT_UPPER_BOUND;
	}

	/**
	 * @since 1.18
	 */
	public static boolean isNullFree(@NonNull ENamedElement eObject) {
		boolean isNullFree;
		EAnnotation eAnnotation = eObject.getEAnnotation(PivotConstants.COLLECTION_ANNOTATION_SOURCE);
		if (eAnnotation != null) {
			isNullFree = Boolean.parseBoolean(eAnnotation.getDetails().get(PivotConstants.COLLECTION_IS_NULL_FREE));
		}
		else {
			EObject eContainer = eObject.eContainer();
			if (eContainer instanceof ENamedElement) {
				isNullFree = isNullFree((ENamedElement)eContainer);
			}
			else {
				isNullFree = PivotConstants.DEFAULT_IS_NULL_FREE;
			}
		}
		return isNullFree;
	}

	protected final @NonNull Resource ecoreResource;

	/**
	 * @since 7.0
	 */
	protected Ecore2AS(@NonNull Resource ecoreResource, @NonNull EnvironmentFactory environmentFactory) {
		super(environmentFactory);
		this.ecoreResource = ecoreResource;
		this.environmentFactory.addExternal2AS(this);
	}

	@Override
	public void addGenericType(@NonNull EGenericType eObject) {
		throw new IllegalStateException();
	}

	@Override
	public void addMapping(@NonNull EObject eObject, @NonNull Element pivotElement) {
		throw new IllegalStateException();
	}

	/**
	 * @since 7.0
	 */
	public boolean checkProxy(@NonNull EObject eReference) {		// BUG 457206 MARTE has unresolveable proxies
		if (!eReference.eIsProxy()) {
			return true;
		}
		error("Unresolved proxy: " + EcoreUtil.getURI(eReference));
		return false;
	}

	@Override
	public void error(@NonNull XMIException e) {
		throw new IllegalStateException();
	}

	@Override
	public abstract @NonNull Model getASModel();

	/**
	 * Return the baseURI of ecoreResource against which its imports should be resolved.
	 */
	protected @Nullable URI getBaseURI(@NonNull Resource ecoreResource) {
		URI ecoreURI = ecoreResource.getURI();
		if (ecoreURI == null) {
			return null;
		}
		if (ClassUtil.isRegistered(ecoreResource)) {
			ProjectManager projectManager = environmentFactory.getProjectManager();
			StandaloneProjectMap.IPackageDescriptor packageDescriptor = projectManager.getPackageDescriptor(ecoreURI);
			if (packageDescriptor == null) {
				return null;
			}
			return packageDescriptor.getResourceDescriptor().getPlatformPluginURI();
		}
		else {
			if (!ecoreURI.isHierarchical() || ecoreURI.isRelative()) {
				return null;
			}
			return ecoreURI;
		}
	}

	public @Nullable Resource getEcoreResource() {
		return ecoreResource;
	}

	@Override
	public @NonNull Resource getResource() {
		return ecoreResource;
	}

	@Override
	public @NonNull URI getURI() {
		return ClassUtil.requireNonNull(ecoreResource.getURI());
	}

	@Override
	public void queueETypeParameter(@NonNull ETypeParameter eTypeParameter) {
		throw new IllegalStateException();
	}

	@Override
	public void queueReference(@NonNull EObject eObject) {
		throw new IllegalStateException();
	}

	@SuppressWarnings("null")
	@Override
	public String toString() {
		return String.valueOf(ecoreResource != null ? ecoreResource.getURI() : null);
	}

	public void update(@NonNull Resource resource, @NonNull Collection<@NonNull EObject> ecoreContents) {
		throw new IllegalStateException();
	}
}
