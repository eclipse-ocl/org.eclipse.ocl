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
import java.util.List;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.analyzer.AS2CGVisitor;
import org.eclipse.ocl.examples.codegen.analyzer.BoxingAnalyzer;
import org.eclipse.ocl.examples.codegen.analyzer.CodeGenAnalyzer;
import org.eclipse.ocl.examples.codegen.analyzer.GlobalNameManager;
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
import org.eclipse.ocl.examples.codegen.cgmodel.CGTypeId;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.cgmodel.CGVariable;
import org.eclipse.ocl.examples.codegen.generator.CodeGenerator;
import org.eclipse.ocl.examples.codegen.java.CG2JavaVisitor;
import org.eclipse.ocl.examples.codegen.java.JavaConstants;
import org.eclipse.ocl.examples.codegen.java.JavaStream;
import org.eclipse.ocl.examples.codegen.java.JavaStream.SubStream;
import org.eclipse.ocl.examples.codegen.java.types.JavaTypeId;
import org.eclipse.ocl.examples.codegen.utilities.CGUtil;
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.NavigationCallExp;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.TypedElement;
import org.eclipse.ocl.pivot.Variable;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.library.ForeignProperty;
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
		assert (libraryProperty instanceof StaticProperty)			// test_static_property
			|| (libraryProperty instanceof ForeignProperty);		// test_static_id
	//	assert cgSource == null;
		CGForeignPropertyCallExp cgPropertyCallExp = CGModelFactory.eINSTANCE.createCGForeignPropertyCallExp();
		CGElementId cgPropertyId = analyzer.getCGElementId(asProperty.getPropertyId());
		cgPropertyCallExp.getOwns().add(cgPropertyId);
		cgPropertyCallExp.setReferredProperty(cgProperty);
		cgPropertyCallExp.setAsProperty(asProperty);
		as2cgVisitor.initAst(cgPropertyCallExp, asPropertyCallExp);
		cgPropertyCallExp.setRequired(isRequired || codeGenerator.isPrimitive(cgPropertyCallExp));
		cgPropertyCallExp.setSource(cgSource);
		return cgPropertyCallExp;
	}

	@Override
	public void createCGParameters(@NonNull NestedNameManager nameManager, @NonNull CGProperty cgProperty, @Nullable ExpressionInOCL initExpression) {
		CGForeignProperty cgForeignProperty = (CGForeignProperty)cgProperty;
		List<CGParameter> cgParameters = cgForeignProperty.getParameters();
		cgParameters.add(nameManager.getExecutorParameter());
		if (initExpression != null) {
			Variable contextVariable = initExpression.getOwnedContext();
			if (contextVariable != null) {
				cgParameters.add(nameManager.getSelfParameter(contextVariable));
			}
			else {
				cgParameters.add(nameManager.getAnyParameter());
			}
		}
		else {	// default value
			Property asProperty = CGUtil.getAST(cgProperty);
			cgParameters.add(asProperty.isIsStatic() ?  nameManager.getAnyParameter() : nameManager.getSelfParameter());
		}
	}

	@Override
	public @NonNull CGProperty createCGProperty(@NonNull CodeGenAnalyzer analyzer, @NonNull TypedElement asTypedElement) {
		Property asProperty = (Property)asTypedElement;
		analyzer.addExternalFeature(asProperty);
		return CGModelFactory.eINSTANCE.createCGForeignProperty();
	}

	@Override
	public void createImplementation(@NonNull AS2CGVisitor as2cgVisitor, @NonNull CGProperty cgProperty) {
		NestedNameManager nameManager = as2cgVisitor.getNameManager();
		CGForeignProperty cgForeignProperty = (CGForeignProperty)cgProperty;
		CodeGenAnalyzer analyzer = as2cgVisitor.getAnalyzer();
		Property asProperty = CGUtil.getAST(cgForeignProperty);
//		CGParameter cgParameter = asProperty.isIsStatic() ? localContext.getAnyParameter() : localContext.getSelfParameter();
//		cgForeignProperty.getParameters().add(localContext.getExecutorParameter());
//		cgForeignProperty.getParameters().add(cgParameter);
		CGParameter cgSelfParameter = CGUtil.getParametersList(cgForeignProperty).get(1);
		CGValuedElement cgInitValue = as2cgVisitor.getInitExpression(/*cgParameter,*/ asProperty);
		assert cgInitValue != null;
		CGVariable modelManagerVariable = nameManager.getModelManagerVariable();
		CGElementId cgPropertyId = analyzer.getCGElementId(asProperty.getPropertyId());
	//	CGTypeId cacheTypeId = context.getTypeId(asProperty.getTypeId());
		CGExecutorType cgCastType = analyzer.createExecutorType(PivotUtil.getType(asProperty));
		CGNativeOperationCallExp basicGetValueInit = createCGBoxedNativeOperationCallExp(as2cgVisitor, analyzer.createCGVariableExp(modelManagerVariable), JavaConstants.MODEL_MANAGER_BASIC_GET_FOREIGN_PROPERTY_VALUE_METHOD,
			asProperty.isIsStatic() ? analyzer.createCGConstantExp(analyzer.createCGNull()) : analyzer.createCGVariableExp(cgSelfParameter), analyzer.createCGConstantExp(cgPropertyId));
	//	basicGetValueInit.setTypeId(cacheTypeId);
		basicGetValueInit.setValueIsBoxed(true);
		CGValuedElement castBasicGetValueInit = analyzer.createCGCastExp(cgCastType, basicGetValueInit);
		CGFinalVariable basicGetValueVariable = as2cgVisitor.getNameManager().createCGVariable(castBasicGetValueInit);
//		nameManager.declareLazyName(basicGetValueVariable);
		CGValuedElement cgCondition = analyzer.createCGIsEqual(analyzer.createCGVariableExp(basicGetValueVariable), analyzer.createCGNull());
		CGNativeOperationCallExp getValue = createCGBoxedNativeOperationCallExp(as2cgVisitor, analyzer.createCGVariableExp(modelManagerVariable), JavaConstants.MODEL_MANAGER_GET_FOREIGN_PROPERTY_VALUE_METHOD,
			asProperty.isIsStatic() ? analyzer.createCGConstantExp(analyzer.createCGNull()) : analyzer.createCGVariableExp(cgSelfParameter), analyzer.createCGConstantExp(cgPropertyId), cgInitValue);
	//	getValue.setTypeId(cacheTypeId);
		getValue.setValueIsBoxed(true);
		CGValuedElement castGetValue = analyzer.createCGCastExp(cgCastType, getValue);
		if (asProperty.isIsRequired()) {
			getValue.setRequired(true);
		}
		CGValuedElement ifValue = analyzer.createCGIfExp(cgCondition, castGetValue, analyzer.createCGVariableExp(basicGetValueVariable));
		if (asProperty.isIsRequired()) {
			ifValue.setRequired(true);
		}
		CGValuedElement withBasicGetValue = analyzer.createCGLetExp(basicGetValueVariable, ifValue);
		cgForeignProperty.setBody(withBasicGetValue);
	}

	private @NonNull CGNativeOperationCallExp createCGBoxedNativeOperationCallExp(@NonNull AS2CGVisitor as2cgVisitor, @Nullable CGValuedElement cgThis, @NonNull Method jMethod, @NonNull CGValuedElement... cgArguments) {
		CGNativeOperationCallExp cgCallExp = as2cgVisitor.getAnalyzer().createCGNativeOperationCallExp(jMethod, SupportOperationCallingConvention.INSTANCE);
		cgCallExp.setCgThis(cgThis);
		if (cgArguments != null) {
			List<CGValuedElement> cgArguments2 = cgCallExp.getArguments();
			for (@NonNull CGValuedElement cgArgument : cgArguments) {
				cgArguments2.add(cgArgument);
			}
		}
		cgCallExp.setRequired(as2cgVisitor.getCodeGenerator().getIsNonNull(jMethod) == Boolean.TRUE);
	//	cgCallExp.setInvalidating(false));
		Class<?> jReturnType = jMethod.getReturnType();
		assert jReturnType != null;
		cgCallExp.setTypeId(as2cgVisitor.getAnalyzer().getCGTypeId(new JavaTypeId(jReturnType)));		// XXX cache
		return cgCallExp;
	}

	@Override
	public boolean generateJavaCall(@NonNull CG2JavaVisitor cg2javaVisitor, @NonNull JavaStream js, @NonNull CGNavigationCallExp cgPropertyCallExp) {
		GlobalNameManager globalNameManager = cg2javaVisitor.getCodeGenerator().getGlobalNameManager();
		CGForeignPropertyCallExp cgForeignPropertyCallExp = (CGForeignPropertyCallExp) cgPropertyCallExp;		// XXX never happens
		CGForeignProperty cgProperty = (CGForeignProperty)CGUtil.getProperty(cgPropertyCallExp);
		Property asProperty = CGUtil.getAsProperty(cgPropertyCallExp);
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
				CGOperation cgContainingOperation = CGUtil.basicGetContainingOperation(cgPropertyCallExp);
				Iterable<@NonNull CGParameter> cgContainingParameters = null;
				if (cgContainingOperation != null) {
					cgContainingParameters = CGUtil.getParameters(cgContainingOperation);
				}
				else {
					CGProperty cgContainingProperty = CGUtil.basicGetContainingProperty(cgPropertyCallExp);
					if (cgContainingProperty instanceof CGForeignProperty) {
						cgContainingParameters = CGUtil.getParameters((CGForeignProperty)cgContainingProperty);
					}
				}
				js.appendClassReference(null, foreignClassName);
				js.append("." + JavaConstants.EXTERNAL_PROPERTY_PREFIX);
				js.append(propertyName);
				js.append("(");
				boolean isFirst = true;
				for (@NonNull CGParameter cgParameter : CGUtil.getParameters(cgProperty)) {
					if (!isFirst) {
						js.append(", ");
					}
					CGTypeId cgTypeId = cgParameter.getTypeId();
					TypeId asTypeId = cgTypeId.getASTypeId();
					if (asTypeId == JavaConstants.EXECUTOR_TYPE_ID) {
						CGParameter cgContainingParameter = getContainingParameter(cgContainingParameters, JavaConstants.EXECUTOR_TYPE_ID);
						if (cgContainingParameter != null) {
							js.appendValueName(cgContainingParameter);
						}
						else {
							js.append(globalNameManager.getExecutorName());
						}
					}
					else if (asTypeId == TypeId.OCL_ANY) {
						CGParameter cgContainingParameter = getContainingParameter(cgContainingParameters, TypeId.OCL_ANY);
						if (cgContainingParameter == null) {
							cgContainingParameter = getContainingParameter(cgContainingParameters, TypeId.OCL_VOID);
						}
						if (cgContainingParameter != null) {
							js.appendValueName(cgContainingParameter);
						}
						else {
							js.append(globalNameManager.getSelfName());
						}
					}
					else {
						js.appendValueName(cgParameter);
					}
					isFirst = false;
				}
				js.append(")");
			}
		};
		js.appendClassCast(cgForeignPropertyCallExp, castBody);
		js.append(";\n");
		return true;
	}

	@Override
	public boolean generateJavaDeclaration(@NonNull CG2JavaVisitor cg2javaVisitor, @NonNull JavaStream js, @NonNull CGProperty cgProperty) {
		Property asProperty = CGUtil.getAST(cgProperty);
		Iterable<@NonNull CGParameter> cgParameters = CGUtil.getParameters((CGForeignProperty)cgProperty);
		CGValuedElement cgInitExpression = cg2javaVisitor.getExpression(((CGForeignProperty)cgProperty).getBody());
		js.append("public static ");
		js.appendTypeDeclaration(cgProperty);
		js.append(" " + JavaConstants.EXTERNAL_PROPERTY_PREFIX);
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

	private @Nullable CGParameter getContainingParameter(@Nullable Iterable<@NonNull CGParameter> cgParameters, @NonNull TypeId requiredTypeId) {
		if (cgParameters != null) {
			for (@NonNull CGParameter cgParameter : cgParameters) {
				CGTypeId cgTypeId = cgParameter.getTypeId();
				TypeId asTypeId = cgTypeId.getASTypeId();
				if (asTypeId == requiredTypeId) {
					return cgParameter;
				}
			}
		}
		return null;
	}

	@Override
	public void rewriteWithBoxingAndGuards(@NonNull BoxingAnalyzer boxingAnalyzer, @NonNull CGProperty cgProperty) {
		super.rewriteWithBoxingAndGuards(boxingAnalyzer, cgProperty);
		CGForeignProperty cgForeignProperty = (CGForeignProperty)cgProperty;
		boxingAnalyzer.rewriteAsBoxed(cgForeignProperty.getBody());
		if (cgForeignProperty.isRequired()) {
			CGValuedElement body = cgForeignProperty.getBody();
			if (body != null) {
				boxingAnalyzer.rewriteAsGuarded(body, false, "body for '" + cgForeignProperty.getAst() + "'");
			}
		}
	}
}
