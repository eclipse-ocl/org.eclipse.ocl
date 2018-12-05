/*******************************************************************************
 * Copyright (c) 2010, 2018 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.build.utilities;

import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.ETypedElement;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.mwe.core.WorkflowContext;
import org.eclipse.emf.mwe.core.issues.Issues;
import org.eclipse.emf.mwe.core.lib.WorkflowComponentWithModelSlot;
import org.eclipse.emf.mwe.core.monitor.ProgressMonitor;

/**
 * Alphabeticizes a designated <tt>modelSlot</tt> so that primitive types
 * appear before enumerations before classes before associations, each
 * in alphabetical order.
 */
public class UML25Fixes extends WorkflowComponentWithModelSlot
{

	private Logger log = Logger.getLogger(getClass());	

	@Override
	public void invokeInternal(WorkflowContext ctx, ProgressMonitor arg1, Issues arg2) {
		Resource resource = (Resource) ctx.get(getModelSlot());
		log.info("Fixing '" + resource.getURI() + "'");
		EPackage ePackage = (EPackage) resource.getContents().get(0);
		List<EClassifier> eClassifiers = ePackage.getEClassifiers();
		EClassifier booleanType = ePackage.getEClassifier("Boolean");
		EClassifier integerType = ePackage.getEClassifier("Integer");
		EClassifier realType = ePackage.getEClassifier("Real");
		EClassifier unlimitedNaturalType = ePackage.getEClassifier("UnlimitedNatural");
		EClassifier BooleanType = EcoreUtil.copy(booleanType);
		BooleanType.setName("BooleanObject");
		BooleanType.setInstanceClassName(Boolean.class.getName());
		EClassifier IntegerType = EcoreUtil.copy(integerType);
		IntegerType.setName("IntegerObject");
		IntegerType.setInstanceClassName(Integer.class.getName());
		EClassifier RealType = EcoreUtil.copy(realType);
		RealType.setName("RealObject");
		RealType.setInstanceClassName(Double.class.getName());
		EClassifier UnlimitedNaturalType = EcoreUtil.copy(unlimitedNaturalType);
		UnlimitedNaturalType.setName("UnlimitedNaturalObject");
		UnlimitedNaturalType.setInstanceClassName(Integer.class.getName());
		eClassifiers.add(BooleanType);
		eClassifiers.add(IntegerType);
		eClassifiers.add(RealType);
		eClassifiers.add(UnlimitedNaturalType);
		for (Iterator<EObject> it = resource.getAllContents(); it.hasNext(); ) {
			EObject eObject = it.next();
			if (eObject instanceof ETypedElement) {
				ETypedElement eTypedElement = (ETypedElement) eObject;
				if (!eTypedElement.isRequired()) {
					EGenericType eGenericType = eTypedElement.getEGenericType();
					EClassifier eClassifier = eGenericType.getEClassifier();
					if (eClassifier == booleanType) {
						eGenericType.setEClassifier(BooleanType);
					}
					else if (eClassifier == integerType) {
						eGenericType.setEClassifier(IntegerType);
					}
					else if (eClassifier == realType) {
						eGenericType.setEClassifier(RealType);
					}
					else if (eClassifier == unlimitedNaturalType) {
						eGenericType.setEClassifier(UnlimitedNaturalType);
					}
				}
			}
		}
	}
}
