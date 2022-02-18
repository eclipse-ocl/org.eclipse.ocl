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
import org.eclipse.ocl.examples.codegen.cgmodel.CGModelFactory;
import org.eclipse.ocl.examples.codegen.cgmodel.CGNavigationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGProperty;
import org.eclipse.ocl.examples.codegen.cgmodel.CGTuplePartCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.generator.CodeGenerator;
import org.eclipse.ocl.examples.codegen.java.CG2JavaVisitor;
import org.eclipse.ocl.examples.codegen.java.JavaStream;
import org.eclipse.ocl.examples.codegen.java.JavaStream.SubStream;
import org.eclipse.ocl.examples.codegen.utilities.CGUtil;
import org.eclipse.ocl.pivot.NavigationCallExp;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.ids.IdManager;
import org.eclipse.ocl.pivot.ids.TuplePartId;
import org.eclipse.ocl.pivot.internal.library.TuplePartProperty;
import org.eclipse.ocl.pivot.library.LibraryProperty;
import org.eclipse.ocl.pivot.values.TupleValue;

/**
 *  ForeignPropertyCallingConvention defines the support for the call of a property realized by an
 *  implementation in the *Tables class.
 *   *  </br>
 *  e.g. as XXXTables.FOREIGN_qualified_class.FC_class.INSTANCE.evaluate(executor, arguments)
 */
public class TuplePropertyCallingConvention extends AbstractPropertyCallingConvention
{
	public static final @NonNull TuplePropertyCallingConvention INSTANCE = new TuplePropertyCallingConvention();

	@Override
	public @NonNull CGValuedElement createCGNavigationCallExp(@NonNull AS2CGVisitor as2cgVisitor, @NonNull CGProperty cgProperty,
			@NonNull LibraryProperty libraryProperty, @Nullable CGValuedElement cgSource, @NonNull NavigationCallExp asPropertyCallExp) {
		CodeGenerator codeGenerator = as2cgVisitor.getCodeGenerator();
		Property asProperty = CGUtil.getAST(cgProperty);
		boolean isRequired = asProperty.isIsRequired();
		assert libraryProperty instanceof TuplePartProperty;
		CGTuplePartCallExp cgPropertyCallExp = CGModelFactory.eINSTANCE.createCGTuplePartCallExp();
		cgPropertyCallExp.setAstTuplePartId(IdManager.getTuplePartId(asProperty));
		cgPropertyCallExp.setCgProperty(cgProperty);
		cgPropertyCallExp.setReferredProperty(asProperty);
		as2cgVisitor.initAst(cgPropertyCallExp, asPropertyCallExp);
		cgPropertyCallExp.setRequired(isRequired || codeGenerator.isPrimitive(cgPropertyCallExp));
		cgPropertyCallExp.setSource(cgSource);
		return cgPropertyCallExp;
	}

	@Override
	public boolean generateJavaCall(@NonNull CG2JavaVisitor<?> cg2javaVisitor, @NonNull JavaStream js, @NonNull CGNavigationCallExp cgPropertyCallExp) {
		CGTuplePartCallExp cgTuplePartCallExp = (CGTuplePartCallExp) cgPropertyCallExp;
		CGValuedElement source = cg2javaVisitor.getExpression(cgTuplePartCallExp.getSource());
		//		CGTypeId resultType = cgTuplePartCallExp.getTypeId();
		//		Class<?> requiredBoxedReturnClass = context.getBoxedClass(resultType.getElementId());
		TuplePartId partId = cgTuplePartCallExp.getAstTuplePartId();
		//
		if (!js.appendLocalStatements(source)) {
			return false;
		}
		//
		boolean isRequired = cgTuplePartCallExp.isNonNull();
		boolean isPrimitive = js.isPrimitive(cgTuplePartCallExp);
		if (!isPrimitive && isRequired /*&& (ecoreIsRequired == Boolean.FALSE)*/) {
			js.appendSuppressWarningsNull(true);
		}
		js.appendDeclaration(cgTuplePartCallExp);
		js.append(" = ");
		//		js.appendClassReference(null, ClassUtil.class);
		//		js.append(".nonNullState(");
		SubStream castBody = new SubStream() {
			@Override
			public void append() {
				js.appendAtomicReferenceTo(TupleValue.class, source);
				js.append(".getValue(" + partId.getIndex() + "/*" + partId.getName() + "*/)");
			}
		};
		js.appendClassCast(cgTuplePartCallExp, castBody);
		//		js.append(")");
		js.append(";\n");
		return true;
	}
}
