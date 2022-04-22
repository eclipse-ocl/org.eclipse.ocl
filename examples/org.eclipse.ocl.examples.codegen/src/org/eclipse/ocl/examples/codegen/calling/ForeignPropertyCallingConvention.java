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
import org.eclipse.ocl.examples.codegen.cgmodel.CGForeignProperty;
import org.eclipse.ocl.examples.codegen.cgmodel.CGForeignPropertyCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGNavigationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGOperation;
import org.eclipse.ocl.examples.codegen.cgmodel.CGParameter;
import org.eclipse.ocl.examples.codegen.cgmodel.CGProperty;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.java.CG2JavaVisitor;
import org.eclipse.ocl.examples.codegen.java.JavaConstants;
import org.eclipse.ocl.examples.codegen.java.JavaStream;
import org.eclipse.ocl.examples.codegen.java.JavaStream.SubStream;
import org.eclipse.ocl.examples.codegen.utilities.CGUtil;
import org.eclipse.ocl.pivot.Property;
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
