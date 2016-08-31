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
package org.eclipse.ocl.examples.codegen.completeocl;

import java.util.List;
import java.util.Set;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.cgmodel.CGClass;
import org.eclipse.ocl.examples.codegen.cgmodel.CGPackage;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.java.CG2JavaVisitor;

/**
 * A CompleteOCLCG2JavaVisitor supports generation of an OCL expression as the LibraryOperation INSTANCE of a Java Class.
 */
public class CompleteOCLCG2JavaVisitor extends CG2JavaVisitor<@NonNull CompleteOCLCodeGenerator>
{
	protected final @NonNull CGPackage cgPackage;
	protected final @Nullable List<CGValuedElement> sortedGlobals;

	public CompleteOCLCG2JavaVisitor(@NonNull CompleteOCLCodeGenerator codeGenerator,
			@NonNull CGPackage cgPackage, @Nullable List<CGValuedElement> sortedGlobals) {
		super(codeGenerator);
		this.cgPackage = cgPackage;
		this.sortedGlobals = sortedGlobals;
	}

	@Override
	protected boolean doClassStatics(@NonNull CGClass cgClass, boolean needsBlankLine) {
		if (cgClass.getContainingPackage() != null) {
			@Nullable
			List<CGValuedElement> sortedGlobals2 = sortedGlobals;
			if (sortedGlobals2 != null) {
				if (needsBlankLine) {
					js.append("\n");
				}
				boolean gotOne = false;
				for (CGValuedElement cgElement : sortedGlobals2) {
					assert cgElement.isGlobal();
					cgElement.accept(this);
					gotOne = true;
				}
				if (gotOne) {
					js.append("\n");
					needsBlankLine = false;
				}
			}
		}
		return super.doClassStatics(cgClass, needsBlankLine);
	}

	@Override
	public @NonNull Set<String> getAllImports() {
		return globalContext.getImports();
	}
}
