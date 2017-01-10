/*******************************************************************************
 * Copyright (c) 2010, 2016 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.ETypedElement;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.Model;
import org.eclipse.ocl.pivot.NamedElement;
import org.eclipse.ocl.pivot.internal.utilities.AbstractConversion;
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal;
import org.eclipse.ocl.pivot.internal.utilities.External2AS;
import org.eclipse.ocl.pivot.internal.utilities.PivotConstantsInternal;

public abstract class AbstractExternal2AS extends AbstractConversion implements External2AS, PivotConstantsInternal
{
	public static @Nullable External2AS findAdapter(@NonNull Resource resource, @NonNull EnvironmentFactoryInternal environmentFactory) {
		External2AS es2as = environmentFactory.getMetamodelManager().getES2AS(resource);
		return es2as;
	}

	protected AbstractExternal2AS(@NonNull EnvironmentFactoryInternal environmentFactory) {
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
			Resource asResource = pivotModel2.eResource();
			if (asResource != null) {
				asResource.unload();
			}
			environmentFactory.getCompleteModel().getPartialModels().remove(pivotModel2);
			metamodelManager.getASResourceSet().getResources().remove(asResource);
		}
		metamodelManager.removeExternalResource(this);
	}

	public abstract void error(@NonNull String message);

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
			if ((details.size() != 1) ||  details.containsKey(PivotConstantsInternal.DOCUMENTATION_ANNOTATION_KEY)) {
				return false;
			}
		}
		return true;
	}

	public abstract void queueReference(@NonNull EObject eObject);

	public <@NonNull T extends NamedElement> T refreshElement(@NonNull Class<T> pivotClass, /*@NonNull*/ EClass pivotEClass, @NonNull EModelElement eModelElement) {
		assert pivotEClass != null;
		EFactory eFactoryInstance = pivotEClass.getEPackage().getEFactoryInstance();
		EObject pivotElement = eFactoryInstance.create(pivotEClass);
		if (!pivotClass.isAssignableFrom(pivotElement.getClass())) {
			throw new ClassCastException();
		}
		@SuppressWarnings("unchecked")
		T castElement = (T) pivotElement;
		return castElement;
	}

	public <@NonNull T extends NamedElement> T refreshNamedElement(@NonNull Class<T> pivotClass, /*@NonNull*/ EClass pivotEClass, @NonNull ENamedElement eNamedElement) {
		T castElement = refreshElement(pivotClass, pivotEClass, eNamedElement);
		castElement.setName(environmentFactory.getTechnology().getOriginalName(eNamedElement));
		return castElement;
	}
}