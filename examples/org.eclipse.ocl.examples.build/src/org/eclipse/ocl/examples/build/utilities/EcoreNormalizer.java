/**
 * <copyright>
 *
 * Copyright (c) 2013, 2018 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *
 * </copyright>
 */
package org.eclipse.ocl.examples.build.utilities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.mwe.core.WorkflowContext;
import org.eclipse.emf.mwe.core.issues.Issues;
import org.eclipse.emf.mwe.core.lib.WorkflowComponentWithModelSlot;
import org.eclipse.emf.mwe.core.monitor.ProgressMonitor;
import org.eclipse.jdt.annotation.NonNull;

/**
 * Normalizes an Ecore <tt>modelSlot</tt> by
 * <br> alphabeticizing packages/classifiers/attributes/operations/constraints
 * <br> eliminating comments.
 */
public class EcoreNormalizer extends WorkflowComponentWithModelSlot
{
	private Logger log = Logger.getLogger(getClass());	
	private ResourceSet resourceSet = null;	

	public @NonNull ResourceSet getResourceSet() {
		ResourceSet resourceSet2 = resourceSet;
		if (resourceSet2 == null) {
			resourceSet = resourceSet2 = new ResourceSetImpl();
		}
		return resourceSet2;
	}

	@Override
	public void invokeInternal(WorkflowContext ctx, ProgressMonitor arg1, Issues arg2) {
		Resource oldResource = (Resource) ctx.get(getModelSlot());
		log.info("Normalizing '" + oldResource.getURI() + "'");
		List<List<? extends ENamedElement>> listOfLists = new ArrayList<List<? extends ENamedElement>>();
		Resource newResource = getResourceSet().createResource(URI.createURI("temp.ecore"));
		newResource.getContents().addAll(EcoreUtil.copyAll(oldResource.getContents()));
		List<EObject> removals = new ArrayList<EObject>();
		for (Iterator<EObject> it = newResource.getAllContents(); it.hasNext(); ) {
			EObject eObject = it.next();
			if (eObject instanceof EPackage) {
				EPackage ePackage = (EPackage) eObject;
				listOfLists.add(ePackage.getESubpackages());
				listOfLists.add(ePackage.getEClassifiers());
			}
			else if (eObject instanceof EEnum) {
				EEnum eEnum = (EEnum) eObject;
				listOfLists.add(eEnum.getELiterals());
			}
//			else if (eObject instanceof EEnumLiteral) {
//				EEnumLiteral eEnumLiteral = (EEnumLiteral) eObject;
//				eEnumLiteral.setValue(0);
//			}
			else if (eObject instanceof EClass) {
				EClass eClass = (EClass) eObject;
				listOfLists.add(eClass.getESuperTypes());
				listOfLists.add(eClass.getEStructuralFeatures());
				listOfLists.add(eClass.getEOperations());
			}
			else if (eObject instanceof EOperation) {
				EOperation eOperation = (EOperation) eObject;
//				if (EcoreUtil.isInvariant(eOperation) && eOperation.getName().startsWith("validate")) {
//					eOperation.setName(eOperation.getName().substring(8));
//				}
				if (EcoreUtil.isInvariant(eOperation)) {
					removals.add(eOperation);
				}
			}
			else if (eObject instanceof EReference) {
				@SuppressWarnings("unused") EReference eReference = (EReference) eObject;
//				eReference.setResolveProxies(true);
			}
			if (eObject instanceof EModelElement) {
				EModelElement eModelElement = (EModelElement) eObject;
				EAnnotation eAnnotation = eModelElement.getEAnnotation(GenModelPackage.eNS_URI); // "http://www.eclipse.org/emf/2002/GenModel");
				if (eAnnotation != null) {
					removals.add(eAnnotation);
				}
				eAnnotation = eModelElement.getEAnnotation("http://www.eclipse.org/emf/2002/Ecore");
				if (eAnnotation != null) {
					removals.add(eAnnotation);
				}
				eAnnotation = eModelElement.getEAnnotation("http://schema.omg.org/spec/MOF/2.0/emof.xml#Property.oppositeRoleName");
				if (eAnnotation != null) {
					removals.add(eAnnotation);
				}
				eAnnotation = eModelElement.getEAnnotation("http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot");
				if (eAnnotation != null) {
					removals.add(eAnnotation);
				}
				eAnnotation = eModelElement.getEAnnotation("duplicates");
				if (eAnnotation != null) {
					removals.add(eAnnotation);
				}
				eAnnotation = eModelElement.getEAnnotation("redefines");
				if (eAnnotation != null) {
					removals.add(eAnnotation);
				}
				eAnnotation = eModelElement.getEAnnotation("subsets");
				if (eAnnotation != null) {
					removals.add(eAnnotation);
				}
			}
		}
		for (EObject removal : removals) {
			((List<?>)removal.eContainer().eGet(removal.eContainingFeature())).remove(removal);
		}
		for (List<? extends ENamedElement> list : listOfLists) {
			sortList(list);
		}
		ctx.set(getModelSlot(), newResource);
	}

	protected <T extends ENamedElement> void sortList(List<T> list) {
		List<T> newList = new ArrayList<T>(list);
		Collections.sort(newList, new Comparator<@NonNull T>()
		{
			public int compare(@NonNull T o1, @NonNull T o2) {
				EClass e1 = o1.eClass();
				EClass e2 = o2.eClass();
				if (e1 != e2) {
					if (EcorePackage.Literals.EATTRIBUTE.isSuperTypeOf(e1)) {
						return -1;
					}
					else if (EcorePackage.Literals.EATTRIBUTE.isSuperTypeOf(e2)) {
						return 1;
					}
					if (EcorePackage.Literals.EDATA_TYPE.isSuperTypeOf(e1)) {
						return -1;
					}
					else if (EcorePackage.Literals.EDATA_TYPE.isSuperTypeOf(e2)) {
						return 1;
					}
					if (EcorePackage.Literals.EENUM.isSuperTypeOf(e1)) {
						return -1;
					}
					else if (EcorePackage.Literals.EENUM.isSuperTypeOf(e2)) {
						return 1;
					}
					if (EcorePackage.Literals.ECLASS.isSuperTypeOf(e1)) {
						return -1;
					}
					else if (EcorePackage.Literals.ECLASS.isSuperTypeOf(e2)) {
						return 1;
					}
				}
				String n1 = o1.getName();
				String n2 = o2.getName();
				return n1.compareTo(n2);
			}
		});
		list.clear();
		list.addAll(newList);
	}
	
	public void setResourceSet(@NonNull ResourceSet resourceSet) {
		this.resourceSet = resourceSet;
	}
}
