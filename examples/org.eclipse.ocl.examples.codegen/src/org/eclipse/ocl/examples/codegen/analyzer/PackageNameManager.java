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
package org.eclipse.ocl.examples.codegen.analyzer;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.cgmodel.CGPackage;
import org.eclipse.ocl.examples.codegen.java.JavaCodeGenerator;
import org.eclipse.ocl.examples.codegen.utilities.CGUtil;

/**
 * A PackageNameManager provides suggestions for names and maintains caches of used names so that model elements are consistently
 * named without collisions at some node in the name nesting hierarchy..
 */
public class PackageNameManager extends NestedNameManager
{
	protected final @NonNull CGPackage cgPackage;
	protected final org.eclipse.ocl.pivot.@NonNull Package asPackage;

	public PackageNameManager(@NonNull JavaCodeGenerator codeGenerator, @NonNull NameManager parent, @NonNull CGPackage cgPackage) {
		super(codeGenerator, parent, cgPackage);
		this.cgPackage = cgPackage;
		this.asPackage = CGUtil.getAST(cgPackage);
		assert (parent instanceof GlobalNameManager) || (parent instanceof PackageNameManager);
	}

	public @NonNull CGPackage getCCGPackage() {
		return cgPackage;
	}

//	@Override
//	public @NonNull PackageNameManager getClassNameManager() {
//		return this;
//	}

	/**
	 * Return the NestedNameManager that can be the parent of another CGClass. Returns null for global.
	 */
	@Override
	public @Nullable PackageNameManager getClassParentNameManager() {
	/*	for (PackageNameManager nameManager = this; nameManager != null; nameManager = nameManager.parent instanceof PackageNameManager ? (PackageNameManager)nameManager.parent : null) {
			CGNamedElement cgClass = nameManager.cgClass;
			if (cgClass instanceof CGClass) {
				return nameManager.parent instanceof PackageNameManager ? (PackageNameManager)nameManager.parent : null;
			}
		} */
		return null;
	}

	@Override
	public @NonNull String toString() {
		return "locals-" + cgPackage.eClass().getName() + "-" + CGUtil.getAST(cgPackage).getName();
	}
}
