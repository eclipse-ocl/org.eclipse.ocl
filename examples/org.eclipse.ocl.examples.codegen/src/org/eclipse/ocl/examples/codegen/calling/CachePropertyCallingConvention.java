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
import org.eclipse.ocl.examples.codegen.analyzer.AS2CGVisitor;
import org.eclipse.ocl.examples.codegen.analyzer.CodeGenAnalyzer;
import org.eclipse.ocl.examples.codegen.cgmodel.CGModelFactory;
import org.eclipse.ocl.examples.codegen.cgmodel.CGNavigationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGProperty;
import org.eclipse.ocl.examples.codegen.cgmodel.CGTuplePartCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.generator.CodeGenerator;
import org.eclipse.ocl.examples.codegen.java.CG2JavaVisitor;
import org.eclipse.ocl.examples.codegen.java.JavaStream;
import org.eclipse.ocl.examples.codegen.utilities.CGUtil;
import org.eclipse.ocl.pivot.NavigationCallExp;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.TypedElement;
import org.eclipse.ocl.pivot.ids.IdManager;
import org.eclipse.ocl.pivot.internal.library.TuplePartProperty;
import org.eclipse.ocl.pivot.library.LibraryProperty;

/**
 *  CachePropertyCallingConvention defines the support for the property realizing a cahed operation call.
 *   *  </br>
 *  e.g. as XXXTables.FOREIGN_qualified_class.FC_class.INSTANCE.evaluate(executor, arguments)
 */
public class CachePropertyCallingConvention extends AbstractPropertyCallingConvention
{
	public static final @NonNull CachePropertyCallingConvention INSTANCE = new CachePropertyCallingConvention();

	@Override
	public @NonNull CGValuedElement createCGNavigationCallExp(@NonNull AS2CGVisitor as2cgVisitor, @NonNull CGProperty cgProperty,
			@NonNull LibraryProperty libraryProperty, @Nullable CGValuedElement cgSource, @NonNull NavigationCallExp asPropertyCallExp) {
		CodeGenerator codeGenerator = as2cgVisitor.getCodeGenerator();
		Property asProperty = CGUtil.getAST(cgProperty);
		boolean isRequired = asProperty.isIsRequired();
		assert libraryProperty instanceof TuplePartProperty;
		CGTuplePartCallExp cgPropertyCallExp = CGModelFactory.eINSTANCE.createCGTuplePartCallExp();
		cgPropertyCallExp.setAstTuplePartId(IdManager.getTuplePartId(asProperty));
		cgPropertyCallExp.setReferredProperty(cgProperty);
		cgPropertyCallExp.setAsProperty(asProperty);
		as2cgVisitor.initAst(cgPropertyCallExp, asPropertyCallExp);
		cgPropertyCallExp.setRequired(isRequired || codeGenerator.isPrimitive(cgPropertyCallExp));
		cgPropertyCallExp.setSource(cgSource);
		return cgPropertyCallExp;
	}

	@Override
	public @NonNull CGProperty createCGProperty(@NonNull CodeGenAnalyzer analyzer, @NonNull TypedElement asTypedElement) {
		// TODO Auto-generated method stub
		return super.createCGProperty(analyzer, asTypedElement);
	}

	@Override
	public boolean generateJavaAssign(@NonNull CG2JavaVisitor cg2javaVisitor, @NonNull JavaStream js,
			@NonNull CGValuedElement slotValue, @NonNull CGProperty cgProperty, @NonNull CGValuedElement initValue) {
		js.appendValueName(slotValue);			// Always "this"
		js.append(".");
		js.appendReferenceTo(cgProperty);
		js.append(" = ");
		js.appendValueName(initValue);
		js.append(";\n");
		return false;
	}

	@Override
	public boolean generateJavaDeclaration(@NonNull CG2JavaVisitor cg2javaVisitor, @NonNull JavaStream js, @NonNull CGProperty cgProperty) {
		js.append("private ");
		js.appendDeclaration(cgProperty);
		js.append(";\n");
		return true;
	}

	@Override
	public boolean generateJavaCall(@NonNull CG2JavaVisitor cg2javaVisitor, @NonNull JavaStream js, @NonNull CGNavigationCallExp cgPropertyCallExp) {
		throw new UnsupportedOperationException();
	}
}
