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
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.analyzer.CodeGenAnalyzer;
import org.eclipse.ocl.examples.codegen.cgmodel.CGModelFactory;
import org.eclipse.ocl.examples.codegen.cgmodel.CGProperty;
import org.eclipse.ocl.examples.codegen.cgmodel.CGPropertyCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.java.CG2JavaVisitor;
import org.eclipse.ocl.examples.codegen.java.JavaStream;
import org.eclipse.ocl.examples.codegen.utilities.CGUtil;
import org.eclipse.ocl.pivot.NavigationCallExp;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.library.LibraryProperty;

/**
 *  ImmutableCachePropertyCallingConvention defines the support for an immutable input property of a chahe realizing an operation call.
 */
public class ImmutableCachePropertyCallingConvention extends AbstractCachePropertyCallingConvention
{
	private static final @NonNull ImmutableCachePropertyCallingConvention INSTANCE = new ImmutableCachePropertyCallingConvention();

	public static @NonNull ImmutableCachePropertyCallingConvention getInstance(@NonNull Property asProperty) {
		INSTANCE.logInstance(asProperty);
		return INSTANCE;
	}

	@Override
	public @NonNull CGValuedElement createCGNavigationCallExp(@NonNull CodeGenAnalyzer analyzer, @NonNull CGProperty cgProperty,
			@NonNull LibraryProperty libraryProperty, @Nullable CGValuedElement cgSource, @NonNull NavigationCallExp asPropertyCallExp) {
	//	CodeGenerator codeGenerator = as2cgVisitor.getCodeGenerator();
		Property asProperty = CGUtil.getAST(cgProperty);
	//	boolean isRequired = asProperty.isIsRequired();
	//	assert libraryProperty instanceof CacheProperty;
		CGPropertyCallExp cgPropertyCallExp = CGModelFactory.eINSTANCE.createCGLibraryPropertyCallExp();
		cgPropertyCallExp.setSource(cgSource);
		cgPropertyCallExp.setReferredProperty(cgProperty);
		cgPropertyCallExp.setTypeId(cgProperty.getTypeId());
		cgPropertyCallExp.setRequired(cgProperty.isRequired());
	//	CGTuplePartCallExp cgPropertyCallExp = CGModelFactory.eINSTANCE.createCGNaCallExp();
	//	cgPropertyCallExp.setAstTuplePartId(IdManager.getTuplePartId(asProperty));
	//	cgPropertyCallExp.setReferredProperty(cgProperty);
		cgPropertyCallExp.setAsProperty(asProperty);
		analyzer.initAst(cgPropertyCallExp, asPropertyCallExp, true);
	//	cgPropertyCallExp.setRequired(isRequired || codeGenerator.isPrimitive(cgPropertyCallExp));
	//	cgPropertyCallExp.setSource(cgSource);
		return cgPropertyCallExp;
	}


	@Override
	public boolean generateJavaDeclaration(@NonNull CG2JavaVisitor cg2javaVisitor, @NonNull JavaStream js, @NonNull CGProperty cgProperty) {
		js.append("protected final");
		return super.generateJavaDeclaration(cg2javaVisitor, js, cgProperty);
	}

	@Override
	public boolean isInlined() {
		return true;
	}
}
