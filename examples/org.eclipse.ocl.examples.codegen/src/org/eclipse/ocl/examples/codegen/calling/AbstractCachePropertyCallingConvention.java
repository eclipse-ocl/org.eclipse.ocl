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
import org.eclipse.ocl.examples.codegen.cgmodel.CGNavigationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGProperty;
import org.eclipse.ocl.examples.codegen.cgmodel.CGPropertyCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.java.CG2JavaVisitor;
import org.eclipse.ocl.examples.codegen.java.JavaCodeGenerator;
import org.eclipse.ocl.examples.codegen.java.JavaStream;
import org.eclipse.ocl.examples.codegen.java.JavaStream.TypeRepresentation;
import org.eclipse.ocl.examples.codegen.utilities.CGUtil;
import org.eclipse.ocl.pivot.NavigationCallExp;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.library.LibraryProperty;

/**
 *  CachePropertyCallingConvention defines the support for the property realizing a cahed operation call.
 *   *  </br>
 *  e.g. as XXXTables.FOREIGN_qualified_class.FC_class.INSTANCE.evaluate(executor, arguments)
 */
public abstract class AbstractCachePropertyCallingConvention extends AbstractPropertyCallingConvention
{
	public static class DefaultInstancePropertyCallingConvention extends AbstractCachePropertyCallingConvention
	{
		private static final @NonNull DefaultInstancePropertyCallingConvention INSTANCE = new DefaultInstancePropertyCallingConvention();

		public static @NonNull DefaultInstancePropertyCallingConvention getInstance(@NonNull Property asProperty) {
			INSTANCE.logInstance(asProperty);
			return INSTANCE;
		}

		@Override
		public boolean generateJavaDeclaration(@NonNull CG2JavaVisitor cg2javaVisitor, @NonNull CGProperty cgProperty) {
			JavaStream js = cg2javaVisitor.getJavaStream();
			TypeRepresentation boxedTypeRepresentation = js.getBoxedTypeRepresentation();
			if (JavaCodeGenerator.CALLING_CONVENTION_COMMENTS.isActive()) {
				js.append("// " + cgProperty.getCallingConvention() + "\n");
			}
			js.append("protected final");
			js.append(" /*@NonInvalid*/ ");
			boxedTypeRepresentation.appendClassReference(cgProperty.isRequired(), cgProperty);
			js.append(" ");
			js.appendValueName(cgProperty);
			js.append(";\n");
			return true;
		}

		@Override
		public boolean generateJavaInitialization(@NonNull CG2JavaVisitor cg2javaVisitor, @NonNull CGProperty cgProperty) {
			JavaStream js = cg2javaVisitor.getJavaStream();
			TypeRepresentation boxedTypeRepresentation = js.getBoxedTypeRepresentation();
			if (JavaCodeGenerator.CALLING_CONVENTION_COMMENTS.isActive()) {
				js.append("// " + cgProperty.getCallingConvention() + "\n");
			}
			js.append("this.");
			js.appendValueName(cgProperty);
			js.append(" = new ");
			boxedTypeRepresentation.appendClassReference(null, cgProperty);
			js.append("();\n");
			return true;
		}
	}

	/**
	 *  ImmutableCachePropertyCallingConvention defines the support for an immutable input property of a cache realizing an operation call.
	 */
	public static class ImmutableCachePropertyCallingConvention extends AbstractCachePropertyCallingConvention
	{
		private static final @NonNull ImmutableCachePropertyCallingConvention INSTANCE = new ImmutableCachePropertyCallingConvention();

		public static @NonNull ImmutableCachePropertyCallingConvention getInstance(@NonNull Property asProperty) {
			INSTANCE.logInstance(asProperty);
			return INSTANCE;
		}

		@Override
		public boolean generateJavaDeclaration(@NonNull CG2JavaVisitor cg2javaVisitor, @NonNull CGProperty cgProperty) {
			JavaStream js = cg2javaVisitor.getJavaStream();
			if (JavaCodeGenerator.CALLING_CONVENTION_COMMENTS.isActive()) {
				js.append("// " + cgProperty.getCallingConvention() + "\n");
			}
			js.append("protected final");
			js.append(" /*@NonInvalid*/ ");
			js.getBoxedTypeRepresentation().appendClassReference(cgProperty.isRequired(), cgProperty);
			js.append(" ");
			js.appendValueName(cgProperty);
			js.append(";\n");
			return true;
		}
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
		analyzer.initAst(cgPropertyCallExp, asPropertyCallExp, true);
	//	cgPropertyCallExp.setRequired(isRequired || codeGenerator.isPrimitive(cgPropertyCallExp));
	//	cgPropertyCallExp.setSource(cgSource);
		return cgPropertyCallExp;
	}

	@Override
	public boolean generateJavaAssign(@NonNull CG2JavaVisitor cg2javaVisitor,
			@NonNull CGValuedElement slotValue, @NonNull CGProperty cgProperty, @NonNull CGValuedElement initValue) {
		assert !initValue.isInvalid();
		JavaStream js = cg2javaVisitor.getJavaStream();
		js.appendValueName(slotValue);			// Always "this"
		js.append(".");
		js.appendReferenceTo(cgProperty);
		js.append(" = ");
		js.appendValueName(initValue);
		js.append(";\n");
		return true;
	}

	@Override
	public boolean generateJavaCall(@NonNull CG2JavaVisitor cg2javaVisitor, @NonNull CGNavigationCallExp cgPropertyCallExp) {
		JavaStream js = cg2javaVisitor.getJavaStream();
	//	js.appendDeclaration(cgPropertyCallExp);
	//	js.append(" = ");
		js.appendValueName(cgPropertyCallExp.getSource());			// Always "this"
		js.append(".");
		js.appendReferenceTo(cgPropertyCallExp.getReferredProperty());
	//	js.append(";\n");
		return true;
	}

	@Override
	public boolean isInlined() {
		return true;
	}
}
