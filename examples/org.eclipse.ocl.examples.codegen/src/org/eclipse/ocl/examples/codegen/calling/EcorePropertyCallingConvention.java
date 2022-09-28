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

import java.lang.reflect.Method;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.analyzer.BoxingAnalyzer;
import org.eclipse.ocl.examples.codegen.analyzer.CodeGenAnalyzer;
import org.eclipse.ocl.examples.codegen.cgmodel.CGEcorePropertyCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGModelFactory;
import org.eclipse.ocl.examples.codegen.cgmodel.CGNavigationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGProperty;
import org.eclipse.ocl.examples.codegen.cgmodel.CGPropertyCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGTypeId;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.generator.CodeGenerator;
import org.eclipse.ocl.examples.codegen.generator.TypeDescriptor;
import org.eclipse.ocl.examples.codegen.java.CG2JavaVisitor;
import org.eclipse.ocl.examples.codegen.java.JavaCodeGenerator;
import org.eclipse.ocl.examples.codegen.java.JavaConstants;
import org.eclipse.ocl.examples.codegen.java.JavaStream;
import org.eclipse.ocl.examples.codegen.naming.ClassNameManager;
import org.eclipse.ocl.examples.codegen.naming.ExecutableNameManager;
import org.eclipse.ocl.examples.codegen.utilities.CGUtil;
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.NavigationCallExp;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.ids.ElementId;
import org.eclipse.ocl.pivot.internal.library.ConstrainedProperty;
import org.eclipse.ocl.pivot.internal.library.ExplicitNavigationProperty;
import org.eclipse.ocl.pivot.internal.library.ForeignProperty;
import org.eclipse.ocl.pivot.internal.library.StereotypeProperty;
import org.eclipse.ocl.pivot.library.LibraryProperty;
import org.eclipse.ocl.pivot.library.oclany.OclElementOclContainerProperty;
import org.eclipse.ocl.pivot.oclstdlib.OCLstdlibPackage;
import org.eclipse.ocl.pivot.utilities.ClassUtil;

/**
 *  NativePropertyCallingConvention defines the support for the call of a property realized by native code.
 *   *  </br>
 *  e.g. as XXXTables.FOREIGN_qualified_class.FC_class.INSTANCE.evaluate(executor, arguments)
 */
public class EcorePropertyCallingConvention extends AbstractPropertyCallingConvention
{
	public static final @NonNull EcorePropertyCallingConvention INSTANCE = new EcorePropertyCallingConvention();

	protected void appendEcoreGet(@NonNull CG2JavaVisitor cg2javaVisitor, @NonNull JavaStream js, @NonNull CGValuedElement cgSource, @NonNull Property asProperty) {
		CGTypeId cgTypeId = cg2javaVisitor.getAnalyzer().getCGTypeId(asProperty.getOwningClass().getTypeId());
		ElementId elementId = ClassUtil.nonNullState(cgTypeId.getElementId());
		JavaCodeGenerator codeGenerator = cg2javaVisitor.getCodeGenerator();
		TypeDescriptor requiredTypeDescriptor = codeGenerator.getUnboxedDescriptor(elementId);
		//		EStructuralFeature eStructuralFeature = ClassUtil.nonNullState(cgPropertyCallExp.getEStructuralFeature());
		EStructuralFeature eStructuralFeature = ClassUtil.nonNullState(cg2javaVisitor.getESObject(asProperty));
		String getAccessor;
		if (eStructuralFeature == OCLstdlibPackage.Literals.OCL_ELEMENT__OCL_CONTAINER) {
			getAccessor = JavaConstants.E_CONTAINER_NAME;
		}
		else {
			getAccessor = codeGenerator.getGenModelHelper().getGetAccessor(eStructuralFeature);
		}
		Class<?> requiredJavaClass = requiredTypeDescriptor.hasJavaClass();
		Method leastDerivedMethod = requiredJavaClass != null ? codeGenerator.getLeastDerivedMethod(requiredJavaClass, getAccessor) : null;
		Class<?> unboxedSourceClass;
		if (leastDerivedMethod != null) {
			unboxedSourceClass = leastDerivedMethod.getDeclaringClass();
		}
		else {
			unboxedSourceClass = requiredJavaClass;
		}
		if ((unboxedSourceClass != null) && (unboxedSourceClass != Object.class)) {
			js.appendAtomicReferenceTo(unboxedSourceClass, cgSource);
		}
		else {
			js.appendAtomicReferenceTo(cgSource);
		}
		js.append(".");
		js.append(getAccessor);
		js.append("()");
	}

	@Override
	public @NonNull CGValuedElement createCGNavigationCallExp(@NonNull CodeGenAnalyzer analyzer, @NonNull CGProperty cgProperty,
			@NonNull LibraryProperty libraryProperty, @Nullable CGValuedElement cgSource, @NonNull NavigationCallExp asPropertyCallExp) {
		CodeGenerator codeGenerator = analyzer.getCodeGenerator();
		Property asProperty = CGUtil.getAST(cgProperty);
		boolean isRequired = asProperty.isIsRequired();
		CGPropertyCallExp cgPropertyCallExp = null;
		EStructuralFeature eStructuralFeature = null;
		if (libraryProperty instanceof OclElementOclContainerProperty) {
			eStructuralFeature = OCLstdlibPackage.Literals.OCL_ELEMENT__OCL_CONTAINER;
		}
		else if (libraryProperty instanceof StereotypeProperty) {
			eStructuralFeature = (EStructuralFeature) asProperty.getESObject();
			if (eStructuralFeature != null) {
				isRequired = asProperty.isIsRequired();
			}
		}
		else if (libraryProperty instanceof ForeignProperty) {
			eStructuralFeature = (EStructuralFeature) asProperty.getESObject();
			assert eStructuralFeature == null;
			isRequired = asProperty.isIsRequired();
		}
		else if (libraryProperty instanceof ConstrainedProperty) {
			eStructuralFeature = (EStructuralFeature) asProperty.getESObject();
		//	assert eStructuralFeature != null;
			isRequired = asProperty.isIsRequired();
		}
		else if (libraryProperty instanceof ExplicitNavigationProperty) {
				//	|| (libraryProperty instanceof CompositionProperty)
				//	|| (libraryProperty instanceof ImplicitNonCompositionProperty)		// FIXME surely this isn't Ecore
				//	|| (libraryProperty instanceof StaticProperty)
				//	|| (libraryProperty instanceof StereotypeProperty)) {
			eStructuralFeature = (EStructuralFeature) asProperty.getESObject();
		}
		assert eStructuralFeature != null;
		CGEcorePropertyCallExp cgEcorePropertyCallExp = CGModelFactory.eINSTANCE.createCGEcorePropertyCallExp();
		cgEcorePropertyCallExp.setEStructuralFeature(eStructuralFeature);
		cgPropertyCallExp = cgEcorePropertyCallExp;
	/*	}
		else {
			CGExecutorPropertyCallExp cgExecutorPropertyCallExp = CGModelFactory.eINSTANCE.createCGExecutorPropertyCallExp();
			CGExecutorProperty cgExecutorProperty = analyzer.createExecutorProperty(asProperty);
			cgExecutorPropertyCallExp.setExecutorProperty(cgExecutorProperty);
			cgExecutorPropertyCallExp.getOwns().add(cgExecutorProperty);
			cgPropertyCallExp = cgExecutorPropertyCallExp;
		} */
		cgPropertyCallExp.setReferredProperty(cgProperty);
		cgPropertyCallExp.setAsProperty(asProperty);
		analyzer.initAst(cgPropertyCallExp, asPropertyCallExp, true);
		cgPropertyCallExp.setRequired(isRequired || codeGenerator.isPrimitive(cgPropertyCallExp));
		cgPropertyCallExp.setSource(cgSource);
		return cgPropertyCallExp;
	}

	@Override
	public void createCGParameters(@NonNull ExecutableNameManager propertyNameManager, @Nullable ExpressionInOCL initExpression) {
		Property asProperty = (Property)propertyNameManager.getASScope();
		CGProperty cgProperty = (CGProperty)propertyNameManager.getCGScope();
		assert !asProperty.isIsImplicit();
		ClassNameManager classNameManager = propertyNameManager.getClassNameManager();
		classNameManager.declareEagerName(cgProperty);
		super.createCGParameters(propertyNameManager, initExpression);
	}

	@Override
	public boolean generateJavaCall(@NonNull CG2JavaVisitor cg2javaVisitor, @NonNull JavaStream js, @NonNull CGNavigationCallExp cgPropertyCallExp) {
		CGEcorePropertyCallExp cgEcorePropertyCallExp = (CGEcorePropertyCallExp) cgPropertyCallExp;
		Property asProperty = ClassUtil.nonNullState(cgPropertyCallExp.getAsProperty());
		assert cg2javaVisitor.getESObject(asProperty) == ClassUtil.nonNullState(cgEcorePropertyCallExp.getEStructuralFeature());
		//
		CGValuedElement source = cg2javaVisitor.getExpression(cgPropertyCallExp.getSource());
		if (!js.appendLocalStatements(source)) {
			return false;
		}
		//
		Boolean ecoreIsRequired = cg2javaVisitor.getCodeGenerator().isNonNull(asProperty);
		boolean isPrimitive = js.isPrimitive(cgPropertyCallExp);
		if (!isPrimitive) cg2javaVisitor.appendSuppressWarningsNull(cgPropertyCallExp, ecoreIsRequired);
		//		js.append("/* " + ecoreIsRequired + " " + isRequired + " */\n");
		js.appendDeclaration(cgPropertyCallExp);
		js.append(" = ");
		appendEcoreGet(cg2javaVisitor, js, source, asProperty);
		js.append(";\n");
		return true;
	}

	@Override
	public boolean generateJavaDeclaration(
			@NonNull CG2JavaVisitor cg2javaVisitor, @NonNull JavaStream js,
			@NonNull CGProperty cgProperty) {
		// TODO Auto-generated method stub
		return super.generateJavaDeclaration(cg2javaVisitor, js, cgProperty);
	}

	@Override
	public boolean needsGeneration() {
		return false;
	}

	@Override
	protected void rewriteWithResultBoxing(@NonNull BoxingAnalyzer boxingAnalyzer, @NonNull CGNavigationCallExp cgNavigationCallExp) {
		CGEcorePropertyCallExp cgEcorePropertyCallExp = (CGEcorePropertyCallExp) cgNavigationCallExp;
		if (cgEcorePropertyCallExp.getEStructuralFeature().isMany()) {
			boxingAnalyzer.rewriteAsAssertNonNulled(cgEcorePropertyCallExp);
		}
	}

	@Override
	protected void rewriteWithSourceBoxing(@NonNull BoxingAnalyzer boxingAnalyzer, @NonNull CGNavigationCallExp cgNavigationCallExp) {
		CGEcorePropertyCallExp cgEcorePropertyCallExp = (CGEcorePropertyCallExp) cgNavigationCallExp;
		boxingAnalyzer.rewriteAsEcore(cgEcorePropertyCallExp.getSource(), cgEcorePropertyCallExp.getEStructuralFeature().getEContainingClass());
	}
}
