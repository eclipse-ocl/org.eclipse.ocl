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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.mwe.core.WorkflowContext;
import org.eclipse.emf.mwe.core.issues.Issues;
import org.eclipse.emf.mwe.core.lib.WorkflowComponentWithModelSlot;
import org.eclipse.emf.mwe.core.monitor.ProgressMonitor;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.UMLPackage;

/**
 * Alphabeticizes a designated <tt>modelSlot</tt> so that primitive types
 * appear before enumerations before classes before associations, each
 * in alphabetical order.
 */
public class PackageAlphabetizer extends WorkflowComponentWithModelSlot
{
	private Logger log = Logger.getLogger(getClass());	

	@Override
	public void invokeInternal(WorkflowContext ctx, ProgressMonitor arg1, Issues arg2) {
		Resource resource = (Resource) ctx.get(getModelSlot());
		log.info("Alphabeticizing '" + resource.getURI() + "'");
		alphabeticize(resource);
	}

	public void alphabeticize(@NonNull Resource resource) {
		List<@NonNull List<@NonNull ? extends NamedElement>> listOfLists = new ArrayList<@NonNull List<@NonNull ? extends NamedElement>>();
		for (Iterator<EObject> it = resource.getAllContents(); it.hasNext(); ) {
			EObject eObject = it.next();
			if (eObject instanceof org.eclipse.uml2.uml.Package) {
				org.eclipse.uml2.uml.Package package_ = (org.eclipse.uml2.uml.Package) eObject;
				listOfLists.add(ClassUtil.nullFree(package_.getNestedPackages()));
				listOfLists.add(ClassUtil.nullFree(package_.getOwnedTypes()));
			}
		}
		for (List<@NonNull ? extends NamedElement> list : listOfLists) {
			sortList(list);
		}
	}

	protected <@NonNull T extends NamedElement> void sortList(List< T> list) {
		List<T> newList = new ArrayList<T>(list);
		Collections.sort(newList, new Comparator<T>()
		{
			@Override
			public int compare(T o1, T o2) {
				EClass e1 = o1.eClass();
				EClass e2 = o2.eClass();
				if (e1 != e2) {
					if (UMLPackage.Literals.PRIMITIVE_TYPE.isSuperTypeOf(e1)) {
						return -1;
					}
					else if (UMLPackage.Literals.PRIMITIVE_TYPE.isSuperTypeOf(e2)) {
						return 1;
					}
					if (UMLPackage.Literals.ENUMERATION.isSuperTypeOf(e1)) {
						return -1;
					}
					else if (UMLPackage.Literals.ENUMERATION.isSuperTypeOf(e2)) {
						return 1;
					}
					if (UMLPackage.Literals.CLASS.isSuperTypeOf(e1)) {
						return -1;
					}
					else if (UMLPackage.Literals.CLASS.isSuperTypeOf(e2)) {
						return 1;
					}
				}
				String n1 = o1.getName();
				String n2 = o2.getName();
				if (n1 == null) n1 = "";
				if (n2 == null) n2 = "";
				return n1.compareTo(n2);
			}
		});
		list.clear();
		list.addAll(newList);
	}
}
