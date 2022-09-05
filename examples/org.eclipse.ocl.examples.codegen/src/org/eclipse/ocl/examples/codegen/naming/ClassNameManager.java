/*******************************************************************************
 * Copyright (c) 2013, 2022 CEA LIST and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink(CEA LIST) - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.codegen.naming;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.cgmodel.CGClass;
import org.eclipse.ocl.examples.codegen.cgmodel.CGNamedElement;
import org.eclipse.ocl.examples.codegen.java.JavaCodeGenerator;
import org.eclipse.ocl.examples.codegen.utilities.CGUtil;

/**
 * A ClassNameManager provides suggestions for names and maintains caches of used names so that model elements are consistently
 * named without collisions at some node in the name nesting hierarchy..
 */
public class ClassNameManager extends NestedNameManager implements ClassableNameManager
{
	protected final @NonNull CGClass cgClass;
	protected final org.eclipse.ocl.pivot.@NonNull Class asClass;

	public ClassNameManager(@NonNull JavaCodeGenerator codeGenerator, @NonNull ClassableNameManager parent, @NonNull CGClass cgClass) {
		super(codeGenerator, (AbstractNameManager)parent, cgClass);
		this.cgClass = cgClass;
		this.asClass = CGUtil.getAST(cgClass);
	}

	public org.eclipse.ocl.pivot.@NonNull Class getASClass() {
		return asClass;
	}

//	@Override
	public @NonNull CGClass getCGClass() {
		return cgClass;
	}

//	@Override
//	public @NonNull CGClass getCGScope() {
//		return cgClass;
//	}

//	@Override
//	public @NonNull ClassNameManager getClassNameManager() {
//		return this;
//	}

	/**
	 * Return the NestedNameManager that can be the parent of another CGClass. Returns null for global.
	 */
	@Override
	public @Nullable ClassNameManager getClassParentNameManager() {
		for (ClassNameManager nameManager = this; nameManager != null; nameManager = nameManager.parent instanceof ClassNameManager ? (ClassNameManager)nameManager.parent : null) {
			CGNamedElement cgClass = nameManager.cgClass;
			if (cgClass instanceof CGClass) {
				return nameManager.parent instanceof ClassNameManager ? (ClassNameManager)nameManager.parent : null;
			}
		}
		return null;
	}

	@Override
	public @NonNull String toString() {
		return "locals-" + cgClass.eClass().getName() + "-" + CGUtil.getAST(cgClass).getName();
	}
}
