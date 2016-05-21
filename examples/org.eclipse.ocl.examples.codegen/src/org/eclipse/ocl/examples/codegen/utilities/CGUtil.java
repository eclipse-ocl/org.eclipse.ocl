/*******************************************************************************
 * Copyright (c) 2013, 2016 CEA LIST and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *   E.D.Willink(CEA LIST) - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.codegen.utilities;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.cgmodel.CGCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGClass;
import org.eclipse.ocl.examples.codegen.cgmodel.CGElement;
import org.eclipse.ocl.examples.codegen.cgmodel.CGExecutorType;
import org.eclipse.ocl.examples.codegen.cgmodel.CGModelFactory;
import org.eclipse.ocl.examples.codegen.cgmodel.CGParameter;
import org.eclipse.ocl.examples.codegen.cgmodel.CGTypeId;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.pivot.ids.ElementId;
import org.eclipse.ocl.pivot.ids.OclVoidTypeId;
import org.eclipse.ocl.pivot.ids.PrimitiveTypeId;
import org.eclipse.ocl.pivot.ids.TemplateParameterId;

public class CGUtil
{
	public static @NonNull CGParameter createCGParameter(@NonNull String name, @NonNull CGTypeId typeId) {
		CGParameter cgParameter = CGModelFactory.eINSTANCE.createCGParameter();
		cgParameter.setName(name);
		cgParameter.setTypeId(typeId);
		return cgParameter;
	}

	public static @Nullable CGClass getContainingClass(@NonNull CGElement cgExpression) {
		for (CGElement cgElement = cgExpression; cgElement != null; cgElement = cgElement.getParent()) {
			if (cgElement instanceof CGClass) {
				return (CGClass) cgElement;
			}
		}
		return null;
	}

/*	public static @Nullable CGOperation getContainingOperation(@NonNull CGValuedElement cgExpression) {
		for (CGElement cgElement = cgExpression; cgElement != null; cgElement = cgElement.getParent()) {
			if (cgElement instanceof CGOperation) {
				return (CGOperation) cgElement;
			}
		}
		return null;
	} */

	public static boolean isInlinedId(@NonNull ElementId elementId) {
		return (elementId instanceof PrimitiveTypeId)
			|| (elementId instanceof OclVoidTypeId)
			|| (elementId instanceof TemplateParameterId);
	}

	public static @Nullable Boolean isKindOf(@NonNull CGValuedElement cgValue, @NonNull CGExecutorType executorType) {
		CGTypeId referenceTypeId = executorType.getUnderlyingTypeId();
		CGTypeId actualTypeId = cgValue.getTypeId();
		return referenceTypeId == actualTypeId ? Boolean.TRUE : null;			// FIXME support conformance somehow
	}


	/**
	 * Replace oldElement by newElement and return oldElement which is orphaned by the replacement.
	 */
	public static @NonNull CGValuedElement replace(@NonNull CGValuedElement oldElement, @NonNull CGValuedElement newElement) {
		assert !oldElement.isRequired() || !newElement.isNull();
		EObject oldContainer = oldElement.eContainer();
//		EObject newContainer = newElement.eContainer();
//		assert (oldContainer != null) && (newContainer == null);
		EcoreUtil.replace(oldElement, newElement);
		assert oldElement.eContainer() == null;
		assert newElement.eContainer() == oldContainer;
		return oldElement;
	}

	/**
	 * Use wrapExp to wrap wrappedExp. 
	 */
	public static void wrap(@NonNull CGCallExp wrapExp, @NonNull CGValuedElement wrappedExp) {
		wrapExp.setTypeId(wrappedExp.getTypeId());
		wrapExp.setAst(wrappedExp.getAst());
		replace(wrappedExp, wrapExp);
		wrapExp.setSource(wrappedExp);
	}
}
