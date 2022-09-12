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
import org.eclipse.ocl.examples.codegen.analyzer.BoxingAnalyzer;
import org.eclipse.ocl.examples.codegen.analyzer.CodeGenAnalyzer;
import org.eclipse.ocl.examples.codegen.cgmodel.CGBodiedProperty;
import org.eclipse.ocl.examples.codegen.cgmodel.CGConstrainedProperty;
import org.eclipse.ocl.examples.codegen.cgmodel.CGModelFactory;
import org.eclipse.ocl.examples.codegen.cgmodel.CGNavigationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGProperty;
import org.eclipse.ocl.examples.codegen.cgmodel.CGTypeId;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.java.CG2JavaVisitor;
import org.eclipse.ocl.examples.codegen.java.JavaStream;
import org.eclipse.ocl.examples.codegen.naming.FeatureNameManager;
import org.eclipse.ocl.examples.codegen.utilities.CGUtil;
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.LanguageExpression;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.TupleType;
import org.eclipse.ocl.pivot.TypedElement;
import org.eclipse.ocl.pivot.Variable;
import org.eclipse.ocl.pivot.ids.PropertyId;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal.EnvironmentFactoryInternalExtension;
import org.eclipse.ocl.pivot.utilities.ParserException;
import org.eclipse.ocl.pivot.utilities.ValueUtil;

/**
 *  AbstractPropertyCallingConvention defines the default support for a property declaration or call.
 */
public abstract class AbstractPropertyCallingConvention implements PropertyCallingConvention
{
	@Override
	public void createCGParameters(@NonNull FeatureNameManager propertyNameManager, @Nullable ExpressionInOCL initExpression) {}

	@Override
	public @NonNull CGProperty createCGProperty(@NonNull CodeGenAnalyzer analyzer, @NonNull TypedElement asTypedElement) {
		CGConstrainedProperty cgProperty = CGModelFactory.eINSTANCE.createCGConstrainedProperty();
		initProperty(analyzer, cgProperty, asTypedElement);
		analyzer.addCGProperty(cgProperty);
		return cgProperty;  // XXX Overrides may add state
	}

	@Override
	public void createImplementation(@NonNull CodeGenAnalyzer analyzer, @NonNull CGProperty cgProperty) {
	//	assert (this instanceof StereotypePropertyCallingConvention)
	//		|| (this instanceof ConstrainedPropertyCallingConvention)
	//		|| (this instanceof NativePropertyCallingConvention)
	//		|| (this instanceof ForeignPropertyCallingConvention);
		FeatureNameManager propertyNameManager = analyzer.usePropertyNameManager(cgProperty);
		Property asProperty = CGUtil.getAST(cgProperty);
		cgProperty.setRequired(asProperty.isIsRequired());
		LanguageExpression specification = asProperty.getOwnedExpression();
		if (specification != null) {
			try {
				EnvironmentFactoryInternalExtension environmentFactory = (EnvironmentFactoryInternalExtension)analyzer.getCodeGenerator().getEnvironmentFactory();
				ExpressionInOCL query = environmentFactory.parseSpecification(specification);
				Variable contextVariable = query.getOwnedContext();
				if (contextVariable != null) {
					analyzer.getSelfParameter(propertyNameManager, contextVariable);
				}
				((CGBodiedProperty)cgProperty).setBody(analyzer.createCGElement(CGValuedElement.class, query.getOwnedBody()));
			} catch (ParserException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();		// XXX
			}
		}
	}

	@Override
	public boolean generateJavaAssign(@NonNull CG2JavaVisitor cg2javaVisitor, @NonNull JavaStream js,
			@NonNull CGValuedElement slotValue, @NonNull CGProperty cgProperty, @NonNull CGValuedElement initValue) {
		js.appendReferenceTo(cgProperty);
		js.append(".initValue(");
		js.appendValueName(slotValue);
		js.append(", ");
		js.appendValueName(initValue);
		js.append(");\n");
		return false;
	}

	@Override
	public boolean generateJavaDeclaration(@NonNull CG2JavaVisitor cg2javaVisitor, @NonNull JavaStream js, @NonNull CGProperty cgProperty) {
		Property asProperty = CGUtil.getAST(cgProperty);
		js.append("«");
		js.append(getClass().getSimpleName());
		js.append(".generateJavaDeclaration ");
		js.append(asProperty.getOwningClass().getOwningPackage().getName());
		js.append("::");
		js.append(asProperty.getOwningClass().getName());
		js.append("::");
		js.append(asProperty.getName());
		js.append("»\n");
		return true;
	//	throw new UnsupportedOperationException("Missing/No support for " + getClass().getSimpleName() + ".generateJavaDeclaration");	// A number of Property Calling Conventions are call-only
	}

	@Override
	public @NonNull ClassCallingConvention getClassCallingConvention() {
		return ContextClassCallingConvention.INSTANCE;
	}

	protected void initProperty(@NonNull CodeGenAnalyzer analyzer, @NonNull CGProperty cgProperty, @NonNull TypedElement asProperty) {
 		TypeId asTypeId = asProperty.getTypeId();
		CGTypeId cgTypeId = analyzer.getCGTypeId(asTypeId);
		cgProperty.setAst(asProperty);
		cgProperty.setTypeId(cgTypeId);
		cgProperty.setRequired(asProperty.isIsRequired());
		cgProperty.setCallingConvention(this);
	}

	@Override
	public void rewriteWithBoxingAndGuards(@NonNull BoxingAnalyzer boxingAnalyzer, @NonNull CGProperty cgProperty) {
	}

	@Override
	public void rewriteWithBoxingAndGuards(@NonNull BoxingAnalyzer boxingAnalyzer, @NonNull CGNavigationCallExp cgNavigationCallExp) {
		Property referredProperty = cgNavigationCallExp.getAsProperty();
		String referredPropertyName;
		if (referredProperty == null) {
			referredPropertyName = "unknown";
		}
		else if (referredProperty.eContainer() instanceof TupleType) {
			referredPropertyName = referredProperty.getName();
		}
		else {
			PropertyId referredPropertyId = referredProperty.getPropertyId();
			referredPropertyName = ValueUtil.getElementIdName(referredPropertyId);
		}
		boxingAnalyzer.rewriteAsGuarded(cgNavigationCallExp.getSource(), boxingAnalyzer.isSafe(cgNavigationCallExp), "source for '" + referredPropertyName + "'");
		rewriteWithSourceBoxing(boxingAnalyzer, cgNavigationCallExp);
		rewriteWithResultBoxing(boxingAnalyzer, cgNavigationCallExp);
	}

	protected void rewriteWithResultBoxing(@NonNull BoxingAnalyzer boxingAnalyzer, @NonNull CGNavigationCallExp cgNavigationCallExp) {
		// XXX change to abstract to mandate handling
	}

	protected void rewriteWithSourceBoxing(@NonNull BoxingAnalyzer boxingAnalyzer, @NonNull CGNavigationCallExp cgNavigationCallExp) {
		// XXX change to abstract to mandate handling
	}

	@Override
	public @NonNull String toString() {
		return getClass().getSimpleName();
	}
}
