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

import java.util.List;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.analyzer.AS2CGVisitor;
import org.eclipse.ocl.examples.codegen.analyzer.CodeGenAnalyzer;
import org.eclipse.ocl.examples.codegen.analyzer.NestedNameManager;
import org.eclipse.ocl.examples.codegen.cgmodel.CGElementId;
import org.eclipse.ocl.examples.codegen.cgmodel.CGExecutorType;
import org.eclipse.ocl.examples.codegen.cgmodel.CGFinalVariable;
import org.eclipse.ocl.examples.codegen.cgmodel.CGForeignProperty;
import org.eclipse.ocl.examples.codegen.cgmodel.CGForeignPropertyCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGModelFactory;
import org.eclipse.ocl.examples.codegen.cgmodel.CGNativeOperationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGNavigationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGOperation;
import org.eclipse.ocl.examples.codegen.cgmodel.CGParameter;
import org.eclipse.ocl.examples.codegen.cgmodel.CGProperty;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.cgmodel.CGVariable;
import org.eclipse.ocl.examples.codegen.generator.CodeGenerator;
import org.eclipse.ocl.examples.codegen.java.CG2JavaVisitor;
import org.eclipse.ocl.examples.codegen.java.JavaConstants;
import org.eclipse.ocl.examples.codegen.java.JavaLocalContext;
import org.eclipse.ocl.examples.codegen.java.JavaStream;
import org.eclipse.ocl.examples.codegen.java.JavaStream.SubStream;
import org.eclipse.ocl.examples.codegen.utilities.CGUtil;
import org.eclipse.ocl.pivot.NavigationCallExp;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.internal.library.StaticProperty;
import org.eclipse.ocl.pivot.library.LibraryProperty;
import org.eclipse.ocl.pivot.utilities.PivotUtil;

/**
 *  ForeignPropertyCallingConvention defines the support for the call of a property realized by an
 *  implementation in the *Tables class.
 *   *  </br>
 *  e.g. as XXXTables.FOREIGN_qualified_class.FC_class.INSTANCE.evaluate(executor, arguments)
 */
public class ForeignPropertyCallingConvention extends AbstractPropertyCallingConvention
{
	public static final @NonNull ForeignPropertyCallingConvention INSTANCE = new ForeignPropertyCallingConvention();

	@Override
	public @NonNull CGValuedElement createCGNavigationCallExp(@NonNull AS2CGVisitor as2cgVisitor, @NonNull CGProperty cgProperty,
			@NonNull LibraryProperty libraryProperty, @Nullable CGValuedElement cgSource, @NonNull NavigationCallExp asPropertyCallExp) {
		CodeGenerator codeGenerator = as2cgVisitor.getCodeGenerator();
		CodeGenAnalyzer analyzer = as2cgVisitor.getAnalyzer();
		Property asProperty = CGUtil.getAST(cgProperty);
		boolean isRequired = asProperty.isIsRequired();
		assert libraryProperty instanceof StaticProperty;
		assert cgSource == null;
		CGForeignPropertyCallExp cgPropertyCallExp = CGModelFactory.eINSTANCE.createCGForeignPropertyCallExp();
		CGElementId cgPropertyId = analyzer.getElementId(asProperty.getPropertyId());
		cgPropertyCallExp.getOwns().add(cgPropertyId);
		cgPropertyCallExp.setCgProperty(cgProperty);
		cgPropertyCallExp.setReferredProperty(asProperty);
		as2cgVisitor.initAst(cgPropertyCallExp, asPropertyCallExp);
		cgPropertyCallExp.setRequired(isRequired || codeGenerator.isPrimitive(cgPropertyCallExp));
		cgPropertyCallExp.setSource(cgSource);
		return cgPropertyCallExp;
	}

	@Override
	public @NonNull CGProperty createCGProperty(@NonNull AS2CGVisitor as2cgVisitor, @NonNull Property asProperty) {
		CodeGenAnalyzer analyzer = as2cgVisitor.getAnalyzer();
		analyzer.addForeignFeature(asProperty);
		return CGModelFactory.eINSTANCE.createCGForeignProperty();
	}

	@Override
	public void createImplementation(@NonNull AS2CGVisitor as2cgVisitor, @NonNull JavaLocalContext<?> localContext, @NonNull CGProperty cgProperty) {
		CodeGenAnalyzer analyzer = as2cgVisitor.getAnalyzer();
		CGForeignProperty cgForeignProperty = (CGForeignProperty)cgProperty;
		Property asProperty = CGUtil.getAST(cgProperty);
		NestedNameManager nameManager = as2cgVisitor.getNameManager();
		CGParameter cgParameter = asProperty.isIsStatic() ? localContext.getAnyParameter() : localContext.getSelfParameter();
		cgForeignProperty.getParameters().add(localContext.getExecutorParameter());
		cgForeignProperty.getParameters().add(cgParameter);

	//	PropertyId propertyId = asProperty.getPropertyId();

		cgForeignProperty.setRequired(asProperty.isIsRequired());
		CGValuedElement cgInitValue = as2cgVisitor.getInitExpression(/*cgParameter,*/ asProperty);
		assert cgInitValue != null;
	//	Operation nativeOperation = context.getNativeOperation(JavaConstants.EXECUTOR_GET_MODEL_MANAGER_METHOD);
	//	CGOperation cgOperation = generateOperation(nativeOperation);
		CGVariable modelManagerVariable = localContext.getModelManagerVariable();


		CGElementId cgPropertyId = analyzer.getElementId(asProperty.getPropertyId());
	//	CGTypeId cacheTypeId = context.getTypeId(asProperty.getTypeId());
		CGExecutorType cgCastType = analyzer.createExecutorType(PivotUtil.getType(asProperty));
		CGNativeOperationCallExp basicGetValueInit = as2cgVisitor.createCGBoxedNativeOperationCallExp(analyzer.createCGVariableExp(modelManagerVariable), JavaConstants.MODEL_MANAGER_BASIC_GET_FOREIGN_PROPERTY_VALUE_METHOD,
			asProperty.isIsStatic() ? analyzer.createCGNull() : analyzer.createCGVariableExp(cgParameter), analyzer.createCGConstantExp(cgPropertyId));
	//	basicGetValueInit.setTypeId(cacheTypeId);
		basicGetValueInit.setValueIsBoxed(true);
		CGValuedElement castBasicGetValueInit = as2cgVisitor.createCGCastExp(cgCastType, basicGetValueInit);
		CGFinalVariable basicGetValueVariable = as2cgVisitor.createCGFinalVariable(castBasicGetValueInit);
		nameManager.declareLazyName(basicGetValueVariable);
		CGValuedElement cgCondition = as2cgVisitor.createCGIsEqual(analyzer.createCGVariableExp(basicGetValueVariable), analyzer.createCGNull());
		CGNativeOperationCallExp getValue = as2cgVisitor.createCGBoxedNativeOperationCallExp(analyzer.createCGVariableExp(modelManagerVariable), JavaConstants.MODEL_MANAGER_GET_FOREIGN_PROPERTY_VALUE_METHOD,
			analyzer.createCGVariableExp(cgParameter), analyzer.createCGConstantExp(cgPropertyId), cgInitValue);
	//	getValue.setTypeId(cacheTypeId);
		getValue.setValueIsBoxed(true);
		CGValuedElement castGetValue = as2cgVisitor.createCGCastExp(cgCastType, getValue);
		if (asProperty.isIsRequired()) {
			getValue.setRequired(true);
		}
		CGValuedElement ifValue = as2cgVisitor.createCGIfExp(cgCondition, castGetValue, analyzer.createCGVariableExp(basicGetValueVariable));
		if (asProperty.isIsRequired()) {
			ifValue.setRequired(true);
		}
		CGValuedElement withBasicGetValue = as2cgVisitor.createCGLetExp(basicGetValueVariable, ifValue);
		cgForeignProperty.setBody(withBasicGetValue);
	//	if (!element.isIsReadOnly()) {
	//		cgNativeProperty.setSettable();
	//	}
	//	else {
	//		cgNativeProperty.setNonNull();
	//	}
	}

	@Override
	public boolean generateJavaCall(@NonNull CG2JavaVisitor<?> cg2javaVisitor, @NonNull JavaStream js, @NonNull CGNavigationCallExp cgPropertyCallExp) {
		CGForeignPropertyCallExp cgForeignPropertyCallExp = (CGForeignPropertyCallExp) cgPropertyCallExp;
		Property asProperty = CGUtil.getReferredProperty(cgPropertyCallExp);
		org.eclipse.ocl.pivot.Class asClass = PivotUtil.getOwningClass(asProperty);
		String foreignClassName = cg2javaVisitor.getCodeGenerator().getQualifiedForeignClassName(asClass);
		String propertyName = PivotUtil.getName(asProperty);
		CGValuedElement cgSource = cgForeignPropertyCallExp.getSource();
		if (cgSource != null) {
			CGValuedElement source = cg2javaVisitor.getExpression(cgSource);
			//
			if (!js.appendLocalStatements(source)) {
				return false;
			}
		}
		//
		js.appendDeclaration(cgForeignPropertyCallExp);
		js.append(" = ");
		SubStream castBody = new SubStream() {
			@Override
			public void append() {
				js.appendClassReference(null, foreignClassName);
				js.append("." + JavaConstants.FOREIGN_PROPERTY_PREFIX);
				js.append(propertyName);
				js.append("(");
				if (cgSource != null) {
					js.appendValueName(cgSource);
				}
				else if (asProperty.isIsStatic()) {
					CGOperation cgOperation = CGUtil.basicGetContainingOperation(cgForeignPropertyCallExp);
					if (cgOperation != null) {
						List<CGParameter> cgParameters = cgOperation.getParameters();
						js.appendValueName(cgParameters.get(0));		// executor
						js.append(", ");
						js.appendValueName(cgParameters.get(2));		// self
					}
					else {
						CGProperty cgProperty = CGUtil.basicGetContainingProperty(cgForeignPropertyCallExp);
						if (cgProperty instanceof CGForeignProperty) {
							Iterable<@NonNull CGParameter> cgParameters = CGUtil.getParameters((CGForeignProperty)cgProperty);
							boolean isFirst = true;
							for (@NonNull CGParameter cgParameter : cgParameters) {
								if (!isFirst) {
									js.append(", ");
								}
								js.appendValueName(cgParameter);
								isFirst = false;
							}
						}
					}
				}
				js.append(")");
			}
		};
		js.appendClassCast(cgForeignPropertyCallExp, castBody);
		js.append(";\n");
		return true;
	}

	@Override
	public boolean generateJavaDeclaration(@NonNull CG2JavaVisitor<?> cg2javaVisitor, @NonNull JavaStream js, @NonNull CGProperty cgProperty) {
		Property asProperty = CGUtil.getAST(cgProperty);
		Iterable<@NonNull CGParameter> cgParameters = CGUtil.getParameters((CGForeignProperty)cgProperty);
		CGValuedElement cgInitExpression = cg2javaVisitor.getExpression(cgProperty.getBody());
		js.append("public static ");
		js.appendTypeDeclaration(cgProperty);
		js.append(" " + JavaConstants.FOREIGN_PROPERTY_PREFIX);
		js.append(asProperty.getName());		// FIXME valid Java name
		js.append("(");
		boolean isFirst = true;
		for (@NonNull CGParameter cgParameter : cgParameters) {
			if (!isFirst) {
				js.append(", ");
			}
			js.appendDeclaration(cgParameter);
			isFirst = false;
		}
		js.append(") {\n");
		js.pushIndentation(null);
		if (!js.appendLocalStatements(cgInitExpression)) {
			return false;
		}
		js.append("return ");
		js.appendValueName(cgInitExpression);
		js.append(";\n");
		js.popIndentation();
		js.append("}\n");
		return true;
	}

	@Override
	public boolean isBoxed() {
		return true;
	}
}
