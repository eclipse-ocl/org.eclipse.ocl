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
import org.eclipse.ocl.examples.codegen.analyzer.GlobalNameManager;
import org.eclipse.ocl.examples.codegen.cgmodel.CGExecutorCompositionProperty;
import org.eclipse.ocl.examples.codegen.cgmodel.CGExecutorOppositePropertyCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGExecutorProperty;
import org.eclipse.ocl.examples.codegen.cgmodel.CGModelFactory;
import org.eclipse.ocl.examples.codegen.cgmodel.CGNavigationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGProperty;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.generator.CodeGenerator;
import org.eclipse.ocl.examples.codegen.java.CG2JavaVisitor;
import org.eclipse.ocl.examples.codegen.java.JavaStream;
import org.eclipse.ocl.examples.codegen.java.JavaStream.SubStream;
import org.eclipse.ocl.pivot.NavigationCallExp;
import org.eclipse.ocl.pivot.OppositePropertyCallExp;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.internal.library.CompositionProperty;
import org.eclipse.ocl.pivot.library.LibraryProperty;
import org.eclipse.ocl.pivot.utilities.ClassUtil;

/**
 *  ForeignPropertyCallingConvention defines the support for the call of a property realized by an
 *  implementation in the *Tables class.
 *   *  </br>
 *  e.g. as XXXTables.FOREIGN_qualified_class.FC_class.INSTANCE.evaluate(executor, arguments)
 */
public class ExecutorCompositionPropertyCallingConvention extends AbstractPropertyCallingConvention // cf ExecutorOppositePropertyCallingConvention
{
	public static final @NonNull ExecutorCompositionPropertyCallingConvention INSTANCE = new ExecutorCompositionPropertyCallingConvention();

	@Override
	public boolean generateJavaDeclaration(@NonNull CG2JavaVisitor cg2javaVisitor, @NonNull JavaStream js, @NonNull CGProperty cgProperty) {
		assert cgProperty instanceof CGExecutorCompositionProperty;
		CGExecutorCompositionProperty cgExecutorCompositionProperty = (CGExecutorCompositionProperty)cgProperty;
		js.appendDeclaration(cgExecutorCompositionProperty);
		js.append(" = new ");
		js.appendClassReference(null, cgExecutorCompositionProperty);
		js.append("(");
		js.appendIdReference(cgExecutorCompositionProperty.getUnderlyingPropertyId().getElementId());
		js.append(");\n");
		return true;
	}

	@Override
	public @NonNull CGValuedElement createCGNavigationCallExp(@NonNull AS2CGVisitor as2cgVisitor, @NonNull CGProperty cgProperty,
			@NonNull LibraryProperty libraryProperty, @Nullable CGValuedElement cgSource, @NonNull NavigationCallExp asNavigationCallExp) {
		CodeGenerator codeGenerator = as2cgVisitor.getCodeGenerator();
		OppositePropertyCallExp asOppositePropertyCallExp = (OppositePropertyCallExp)asNavigationCallExp;
		Property asOppositeProperty = ClassUtil.nonNullModel(asOppositePropertyCallExp.getReferredProperty());
		Property asProperty = ClassUtil.nonNullModel(asOppositeProperty.getOpposite());
		boolean isRequired = asProperty.isIsRequired();
		LibraryProperty libraryProperty2 = codeGenerator.getEnvironmentFactory().getMetamodelManager().getImplementation(asOppositePropertyCallExp, null, asProperty);
		assert libraryProperty2 == libraryProperty;				// XXX
		assert (libraryProperty instanceof CompositionProperty);// || (libraryProperty instanceof ImplicitNonCompositionProperty) || (libraryProperty instanceof ExtensionProperty);
		CGExecutorOppositePropertyCallExp cgPropertyCallExp = CGModelFactory.eINSTANCE.createCGExecutorOppositePropertyCallExp();
		CGExecutorProperty cgExecutorProperty = as2cgVisitor.getAnalyzer().createExecutorOppositeProperty(asProperty);
		cgExecutorProperty.setCallingConvention(this);
		cgPropertyCallExp.setExecutorProperty(cgExecutorProperty);
		cgPropertyCallExp.getOwns().add(cgExecutorProperty);
		cgPropertyCallExp.setReferredProperty(asProperty);
		as2cgVisitor.initAst(cgPropertyCallExp, asOppositePropertyCallExp);
		cgPropertyCallExp.setRequired(isRequired);
		cgPropertyCallExp.setSource(cgSource);
		cgPropertyCallExp.setCgProperty(cgProperty);
		return cgPropertyCallExp;
	}

	@Override
	public boolean generateJavaCall(@NonNull CG2JavaVisitor cg2javaVisitor, @NonNull JavaStream js, @NonNull CGNavigationCallExp cgPropertyCallExp) {
		GlobalNameManager globalNameManager = cg2javaVisitor.getCodeGenerator().getGlobalNameManager();
		CGExecutorOppositePropertyCallExp cgExecutorOppositePropertyCallExp = (CGExecutorOppositePropertyCallExp)cgPropertyCallExp;
		CGValuedElement source = cg2javaVisitor.getExpression(cgExecutorOppositePropertyCallExp.getSource());
		//
		if (!js.appendLocalStatements(source)) {
			return false;
		}
		//
		js.appendDeclaration(cgPropertyCallExp);
		js.append(" = ");
		SubStream castBody = new SubStream() {
			@Override
			public void append() {
				js.appendReferenceTo(cgExecutorOppositePropertyCallExp.getExecutorProperty());
				js.append(".");
				js.append(globalNameManager.getEvaluateName());
				js.append("(");
				//		js.append(getValueName(localContext.getEvaluatorParameter(cgPropertyCallExp)));
				js.append(globalNameManager.getExecutorName());
				js.append(", ");
				js.appendIdReference(cgPropertyCallExp.getASTypeId());
				js.append(", ");
				js.appendValueName(source);
				js.append(")");
			}
		};
		js.appendClassCast(cgPropertyCallExp, castBody);
		js.append(";\n");
		return true;
	}
}
