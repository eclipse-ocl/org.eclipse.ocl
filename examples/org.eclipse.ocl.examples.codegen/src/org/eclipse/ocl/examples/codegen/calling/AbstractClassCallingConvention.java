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
package org.eclipse.ocl.examples.codegen.calling;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.examples.codegen.analyzer.CodeGenAnalyzer;
import org.eclipse.ocl.examples.codegen.cgmodel.CGClass;
import org.eclipse.ocl.examples.codegen.cgmodel.CGModelFactory;
import org.eclipse.ocl.examples.codegen.cgmodel.CGOperation;
import org.eclipse.ocl.examples.codegen.cgmodel.CGProperty;
import org.eclipse.ocl.examples.codegen.java.CG2JavaVisitor;
import org.eclipse.ocl.examples.codegen.java.JavaStream;
import org.eclipse.ocl.examples.codegen.utilities.CGUtil;
import org.eclipse.ocl.pivot.NamedElement;
import org.eclipse.ocl.pivot.utilities.PivotUtil;

/**
 *  ClassCallingConvention defines a particular style of Class declaration.
 */
public abstract class AbstractClassCallingConvention implements ClassCallingConvention
{
	protected void appendSuperTypes(@NonNull JavaStream js, @NonNull CGClass cgClass) {
		boolean isFirst = true;
		for (@NonNull CGClass cgSuperClass : CGUtil.getSuperTypes(cgClass)) {
			if (isFirst) {
				js.append(" extends ");
			}
			else {
				js.append(", ");
			}
			js.append(cgSuperClass.getName());
			isFirst = false;
		}
//			}
	}

	@Override
	public @NonNull CGClass createCGClass(@NonNull NamedElement asNamedElement) {
		return CGModelFactory.eINSTANCE.createCGClass();
	}

	protected void generateClasses(@NonNull CG2JavaVisitor cg2javaVisitor, @NonNull JavaStream js, @NonNull CGClass cgClass) {
	//	boolean first = true;
		for (CGClass cgNestedClass : cgClass.getClasses()) {
		//	boolean first = true;
		//	if (!first) {
				js.append("\n");
		//	}
				cgNestedClass.accept(cg2javaVisitor);
		//	first = false;
		}
	}

	protected void generateOperations(@NonNull CG2JavaVisitor cg2javaVisitor, @NonNull JavaStream js, @NonNull CGClass cgClass) {
	//	boolean first = true;
		for (CGOperation cgOperation : cgClass.getOperations()) {
	//		if (!first) {
				js.append("\n");
	//		}
			cgOperation.accept(cg2javaVisitor);
	//		first = false;
		}
	}

	protected void generateProperties(@NonNull CG2JavaVisitor cg2javaVisitor, @NonNull JavaStream js, @NonNull CGClass cgClass) {
	//	boolean first = true;
		for (CGProperty cgProperty : cgClass.getProperties()) {
	//		if (!first) {
	//			js.append("\n");
	//		}
			cgProperty.accept(cg2javaVisitor);
	//		first = false;
		}
	}

	@Override
	public @NonNull ClassCallingConvention getClassCallingConvention() {
		return this;
	}

	@Override
	public @NonNull String getName(@NonNull CodeGenAnalyzer analyzer, @NonNull NamedElement asNamedElement) {
		return PivotUtil.getName(asNamedElement);
	}
}
