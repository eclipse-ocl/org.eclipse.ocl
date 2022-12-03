/*******************************************************************************
 * Copyright (c) 2022 Willink Transformation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.codegen.naming;

import java.util.List;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.cgmodel.CGNamedElement;
import org.eclipse.ocl.examples.codegen.cgmodel.CGPackage;
import org.eclipse.ocl.examples.codegen.java.JavaCodeGenerator;
import org.eclipse.ocl.examples.codegen.utilities.CGUtil;
import org.eclipse.ocl.pivot.NamedElement;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.PivotUtil;

/**
 * A PackageNameManager provides suggestions for names and maintains caches of used names so that model elements are consistently
 * named without collisions at some node in the name nesting hierarchy.
 */
public class PackageNameManager extends NestedNameManager implements ClassableNameManager
{
	protected final @NonNull CGPackage cgPackage;
	protected final org.eclipse.ocl.pivot.@NonNull Package asPackage;

	public PackageNameManager(@NonNull JavaCodeGenerator codeGenerator, @NonNull GlobalNameManager parent, @NonNull CGPackage cgPackage) {
		this(codeGenerator, (AbstractNameManager)parent, cgPackage);
		parent.addSelfNameManager(cgPackage, parent);
	}

	public PackageNameManager(@NonNull JavaCodeGenerator codeGenerator, @NonNull PackageNameManager parent, @NonNull CGPackage cgPackage) {
		this(codeGenerator, (AbstractNameManager)parent, cgPackage);
	}

	private PackageNameManager(@NonNull JavaCodeGenerator codeGenerator, @NonNull AbstractNameManager parent, @NonNull CGPackage cgPackage) {
		super(codeGenerator, parent, cgPackage);
		this.cgPackage = cgPackage;
		this.asPackage = CGUtil.getAST(cgPackage);
	}

	@Override
	public @NonNull NamedElement getASScope() {
		return asPackage;
	}

	public @NonNull CGPackage getCGPackage() {
		return cgPackage;
	}

	@Override
	public @NonNull CGNamedElement getCGScope() {
		return cgPackage;
	}

	/**
	 * Return the NestedNameManager that can be the parent of another CGClass. Returns null for global.
	 */
	@Override
	public @Nullable PackageNameManager getClassParentNameManager() {
		return null;
	}

	public @NonNull String getUniqueClassName(@NonNull String namePrefix, org.eclipse.ocl.pivot.@NonNull Class asClass) {
		String name = namePrefix + PivotUtil.getName(asClass);
		List<org.eclipse.ocl.pivot.Class> ownedClasses = asPackage.getOwnedClasses();
		if (NameUtil.getNameable(ownedClasses, name) == null) {
			return name;
		}
		for (int i = 1; true; i++) {
			String name2 = name + "_" + i;
			if (NameUtil.getNameable(ownedClasses, name2) == null) {
				return name2;
			}
		}
	}

	public @NonNull String getUniqueClassName(@NonNull String namePrefix, @NonNull Operation asOperation) {
		String name = namePrefix + PivotUtil.getName(PivotUtil.getOwningClass(asOperation)) + "_" + PivotUtil.getName(asOperation);
		List<org.eclipse.ocl.pivot.Class> ownedClasses = asPackage.getOwnedClasses();
		if (NameUtil.getNameable(ownedClasses, name) == null) {
			return name;
		}
		for (int i = 1; true; i++) {
			String name2 = name + "_" + i;
			if (NameUtil.getNameable(ownedClasses, name2) == null) {
				return name2;
			}
		}
	}

	@Override
	public boolean isGlobal() {
		return true;
	}
}
