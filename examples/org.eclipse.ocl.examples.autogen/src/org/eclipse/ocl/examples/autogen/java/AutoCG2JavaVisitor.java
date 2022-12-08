/*******************************************************************************
 * Copyright (c) 2013, 2018 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *   Adolfo Sanchez-Barbudo Herrera (University of York) - Lookup Environment/Visitor
 *******************************************************************************/
package org.eclipse.ocl.examples.autogen.java;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.examples.codegen.analyzer.CodeGenAnalyzer;
import org.eclipse.ocl.examples.codegen.cgmodel.CGClass;
import org.eclipse.ocl.examples.codegen.java.CG2JavaVisitor;

/**
 * AutoCG2JavaVisitor refines the regular generation of Java code from an optimized Auto CG transformation tree
 * to add contributions that are inadequately represented by the CG model.
 */
public abstract class AutoCG2JavaVisitor extends CG2JavaVisitor
{
	protected final @NonNull CodeGenAnalyzer analyzer;

	public AutoCG2JavaVisitor(@NonNull AutoCodeGenerator codeGenerator) {
		super(codeGenerator);
		this.analyzer = codeGenerator.getAnalyzer();
	}

	@Override
	final protected boolean doClassMethods(@NonNull CGClass cgClass, boolean needsBlankLine) {
		if (needsBlankLine) {
			js.appendOptionalBlankLine();
		}
		doConstructor(cgClass);
		doMoreClassMethods(cgClass);
		return super.doClassMethods(cgClass, needsBlankLine);
	}

	protected void doMoreClassMethods(@NonNull CGClass cgClass) {
		// doNothing
	}

	protected abstract void doConstructor(@NonNull CGClass cgClass);
}
