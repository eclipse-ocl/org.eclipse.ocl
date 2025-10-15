/*******************************************************************************
 * Copyright (c) 2010, 2025 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - initial API and implementation
 *   E.D.Willink (CEA List) - Bug 424057 - UML 2.5 CG *******************************************************************************/
package org.eclipse.ocl.pivot.internal.ecore.es2as;

import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.ETypedElement;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.XMIException;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.Model;
import org.eclipse.ocl.pivot.internal.utilities.AbstractConversion;
import org.eclipse.ocl.pivot.internal.utilities.External2AS;
import org.eclipse.ocl.pivot.internal.utilities.PivotConstantsInternal;
import org.eclipse.ocl.pivot.utilities.EnvironmentFactory;
import org.eclipse.ocl.pivot.utilities.PivotConstants;

public abstract class AbstractExternal2AS extends AbstractConversion implements External2AS, PivotConstantsInternal
{
	/**
	 * @since 7.0
	 */
	protected AbstractExternal2AS(@NonNull EnvironmentFactory environmentFactory) {
		super(environmentFactory);
	}

	public abstract void addGenericType(@NonNull EGenericType eObject);

	public abstract void addMapping(@NonNull EObject eObject, @NonNull Element pivotElement);

	protected abstract Model basicGetPivotModel();

	/**
	 * @since 1.3
	 */
	public boolean cannotBeOptional(@NonNull ETypedElement eTypedElement) {	// Fixes Bug 510180, Ecore does not prohibit optional primitive types
		EClassifier eType = eTypedElement.getEType();
		if (eType != null) {
			Class<?> instanceClass = eType.getInstanceClass();
			if ((instanceClass != null) && ((instanceClass == boolean.class) || (instanceClass == byte.class)
					|| (instanceClass == double.class) || (instanceClass == float.class)
					|| (instanceClass == int.class) || (instanceClass == long.class) || (instanceClass == short.class))) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void dispose() {
		Model pivotModel2 = basicGetPivotModel();
		if (pivotModel2 != null) {
			if (pivotModel2.eIsProxy()) {
				getClass();		// XXX
			}
			if (!pivotModel2.eIsProxy()) {		// XXX already unloaded/unloading
				Resource asResource = pivotModel2.eResource();
				if ((asResource != null) && (asResource.getResourceSet() != null)) {
					asResource.unload();
					environmentFactory.getCompleteModel().getPartialModels().remove(pivotModel2);		// XXX
					metamodelManager.getASResourceSet().getResources().remove(asResource);
				}
			}
		}
		metamodelManager.removeExternalResource(this);
	}

	public void error(@NonNull String message) {
		error(new XMIException(message));
	}

	/**
	 * @since 7.0
	 */
	public abstract void error(@NonNull XMIException e);

	/**
	 * Return true if eClassifier is annotated to indicate that it is a Map Entry class that is required in
	 * Ecore but is not required by the Pivot; i.e. it is only ever used to represent the Entry(K,V) underlying
	 * a Map(K,V).
	 *
	 * @since 1.7
	 */
	public boolean isEcoreOnlyEntryClass(@Nullable EClassifier eClassifier) {
		return (eClassifier != null) && (eClassifier.getEAnnotation(PivotConstants.ENTRY_CLASS_ANNOTATION_SOURCE) != null);
	}

	/**
	 * Return true if eClassifier is an EClass whose instanceClass is set to java.util.Map.Entry.class
	 * identifying its use as the Entry(K,V) for the Ecore Collection(Entry(K,V)) == Map(K,V) idiom.
	 *
	 * @since 1.7
	 */
	public boolean isEntryClass(@Nullable EClassifier eClassifier) {
		return (eClassifier instanceof EClass) && (eClassifier.getInstanceClass() == java.util.Map.Entry.class);
	}

	/**
	 * Return true if eOperation can be handled as an OCL invariant. In addition to the EcoreUtil.isInvariant()
	 * checks we also require either no GenModel documentation or a GenModel documentation with no additional
	 * content such as a Java body.
	 */
	public boolean isInvariant(@NonNull EOperation eOperation) {
		if (!EcoreUtil.isInvariant(eOperation)) {
			return false;
		}
		EAnnotation eAnnotation = eOperation.getEAnnotation(PivotConstantsInternal.DOCUMENTATION_ANNOTATION_SOURCE);
		if (eAnnotation != null) {
			@SuppressWarnings("null")@NonNull EMap<String, String> details = eAnnotation.getDetails();
			if ((details.size() != 1) || details.containsKey("body")) {
				return false;
			}
		}
		return true;
	}

	/**
	 * @since 1.17
	 */
	public boolean isLibrary(@NonNull EPackage ePackage) {
		EAnnotation asLibraryAnnotation = ePackage.getEAnnotation(PivotConstants.AS_LIBRARY_ANNOTATION_SOURCE);
		return asLibraryAnnotation != null;
	}

	public abstract void queueReference(@NonNull EObject eObject);

	/**
	 * @since 7.0
	 */
	public <@NonNull T extends Element> T refreshElement(@NonNull Class<@NonNull T> pivotClass, /*@NonNull*/ EClass pivotEClass, @NonNull EModelElement eModelElement) {
		assert pivotEClass != null;
		EFactory eFactoryInstance = pivotEClass.getEPackage().getEFactoryInstance();
		EObject pivotElement = eFactoryInstance.create(pivotEClass);
		return pivotClass.cast(pivotElement);
	}
}