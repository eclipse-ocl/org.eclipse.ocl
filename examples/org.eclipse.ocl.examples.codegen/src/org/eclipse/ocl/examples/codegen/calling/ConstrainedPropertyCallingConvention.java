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
import org.eclipse.ocl.examples.codegen.cgmodel.CGConstrainedProperty;
import org.eclipse.ocl.examples.codegen.cgmodel.CGElementId;
import org.eclipse.ocl.examples.codegen.cgmodel.CGForeignPropertyCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGModelFactory;
import org.eclipse.ocl.examples.codegen.cgmodel.CGNavigationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGProperty;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.generator.CodeGenerator;
import org.eclipse.ocl.examples.codegen.java.CG2JavaVisitor;
import org.eclipse.ocl.examples.codegen.java.JavaStream;
import org.eclipse.ocl.examples.codegen.utilities.CGUtil;
import org.eclipse.ocl.pivot.NavigationCallExp;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.TypedElement;
import org.eclipse.ocl.pivot.internal.library.ConstrainedProperty;
import org.eclipse.ocl.pivot.library.LibraryProperty;

/**
 *  ForeignPropertyCallingConvention defines the support for the call of a property realized by an
 *  implementation in the *Tables class.
 *   *  </br>
 *  e.g. as XXXTables.FOREIGN_qualified_class.FC_class.INSTANCE.evaluate(executor, arguments)
 */
public class ConstrainedPropertyCallingConvention extends AbstractPropertyCallingConvention		// XXX cf ForeignPropertyCallingConvention
{
	private static final @NonNull ConstrainedPropertyCallingConvention INSTANCE = new ConstrainedPropertyCallingConvention();

	public static @NonNull PropertyCallingConvention getInstance(@NonNull Property asProperty) {
		INSTANCE.logInstance(asProperty);
		return INSTANCE;
	}

/*	@Override
	public @NonNull CGValuedElement createCGOppositePropertyCallExp(@NonNull AS2CGVisitor as2cgVisitor, @NonNull CGProperty cgProperty,
			@NonNull LibraryProperty libraryProperty, @NonNull CGValuedElement cgSource, @NonNull OppositePropertyCallExp asOppositePropertyCallExp) {
		CodeGenerator codeGenerator = as2cgVisitor.getCodeGenerator();
		Property asOppositeProperty = ClassUtil.nonNullModel(asOppositePropertyCallExp.getReferredProperty());
		Property asProperty = ClassUtil.nonNullModel(asOppositeProperty.getOpposite());
		boolean isRequired = asProperty.isIsRequired();
		LibraryProperty libraryProperty2 = codeGenerator.getEnvironmentFactory().getMetamodelManager().getImplementation(asOppositePropertyCallExp, null, asProperty);
		assert libraryProperty2 == libraryProperty;
		assert libraryProperty instanceof ExtensionProperty;
		CGExecutorOppositePropertyCallExp cgPropertyCallExp = CGModelFactory.eINSTANCE.createCGExecutorOppositePropertyCallExp();
		CGExecutorProperty cgExecutorProperty = as2cgVisitor.getAnalyzer().createExecutorOppositeProperty(asProperty);
		cgPropertyCallExp.setExecutorProperty(cgExecutorProperty);
		cgPropertyCallExp.getOwns().add(cgExecutorProperty);
		cgPropertyCallExp.setReferredProperty(asProperty);
		as2cgVisitor.initAst(cgPropertyCallExp, asOppositePropertyCallExp);
		cgPropertyCallExp.setRequired(isRequired);
		cgPropertyCallExp.setSource(cgSource);
		cgPropertyCallExp.setReferredProperty(cgProperty);
		return cgPropertyCallExp;
	} */

	@Override
	public @NonNull CGValuedElement createCGNavigationCallExp(@NonNull CodeGenAnalyzer analyzer, @NonNull CGProperty cgProperty,
			@NonNull LibraryProperty libraryProperty, @Nullable CGValuedElement cgSource, @NonNull NavigationCallExp asPropertyCallExp) {
		CodeGenerator codeGenerator = analyzer.getCodeGenerator();
		Property asProperty = CGUtil.getAST(cgProperty);
		boolean isRequired = asProperty.isIsRequired();
		assert libraryProperty instanceof ConstrainedProperty;
	//	assert cgSource == null;
		CGForeignPropertyCallExp cgPropertyCallExp = CGModelFactory.eINSTANCE.createCGForeignPropertyCallExp();
		CGElementId cgPropertyId = analyzer.getCGElementId(asProperty.getPropertyId());
		cgPropertyCallExp.getOwns().add(cgPropertyId);
		cgPropertyCallExp.setReferredProperty(cgProperty);
		cgPropertyCallExp.setAsProperty(asProperty);
		analyzer.initAst(cgPropertyCallExp, asPropertyCallExp, true);
		cgPropertyCallExp.setRequired(isRequired || codeGenerator.isPrimitive(cgPropertyCallExp));
		cgPropertyCallExp.setSource(cgSource);
		return cgPropertyCallExp;
	}

/*	@Override
	public @NonNull CGValuedElement createCGNavigationCallExp(@NonNull AS2CGVisitor as2cgVisitor, @NonNull CGProperty cgProperty,
			@NonNull LibraryProperty libraryProperty, @Nullable CGValuedElement cgSource, @NonNull NavigationCallExp asPropertyCallExp) {
		CodeGenerator codeGenerator = as2cgVisitor.getCodeGenerator();
		CodeGenAnalyzer analyzer = as2cgVisitor.getAnalyzer();
		Property asProperty = CGUtil.getAST(cgProperty);
		boolean isRequired = asProperty.isIsRequired();
		CGExecutorPropertyCallExp cgExecutorPropertyCallExp = CGModelFactory.eINSTANCE.createCGExecutorPropertyCallExp();
		CGExecutorProperty cgExecutorProperty = analyzer.createExecutorProperty(asProperty);
		cgExecutorProperty.setCallingConvention(this);
		cgExecutorPropertyCallExp.setExecutorProperty(cgExecutorProperty);
		cgExecutorPropertyCallExp.getOwns().add(cgExecutorProperty);
		cgExecutorPropertyCallExp.setReferredProperty(cgProperty);
		cgExecutorPropertyCallExp.setReferredProperty(asProperty);
		cgExecutorPropertyCallExp.setAst(asPropertyCallExp);
		cgExecutorPropertyCallExp.setTypeId(analyzer.getTypeId(asPropertyCallExp.getTypeId()));
		cgExecutorPropertyCallExp.setRequired(isRequired || codeGenerator.isPrimitive(cgExecutorPropertyCallExp));
		cgExecutorPropertyCallExp.setSource(cgSource);
		return cgExecutorPropertyCallExp;
	} */

	@Override
	public @NonNull CGProperty createCGProperty(@NonNull CodeGenAnalyzer analyzer, @NonNull TypedElement asTypedElement) {
		return CGModelFactory.eINSTANCE.createCGConstrainedProperty();
	}

/*	@Override
	public void createImplementation(@NonNull AS2CGVisitor as2cgVisitor, @NonNull JavaLocalContext<?> localContext, @NonNull CGProperty cgProperty) {
	//	assert false;
		super.createImplementation(as2cgVisitor, localContext, cgProperty);
	} */

	@Override
	public boolean generateJavaCall(@NonNull CG2JavaVisitor cg2javaVisitor, @NonNull JavaStream js, @NonNull CGNavigationCallExp cgPropertyCallExp) {
		throw new UnsupportedOperationException();		// XXX
	/*	CGForeignPropertyCallExp cgForeignPropertyCallExp = (CGForeignPropertyCallExp) cgPropertyCallExp;		// XXX never happens
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
		return true; */
	}

	@Override
	public void rewriteWithBoxingAndGuards(@NonNull BoxingAnalyzer boxingAnalyzer, @NonNull CGProperty cgProperty) {
		super.rewriteWithBoxingAndGuards(boxingAnalyzer, cgProperty);
		CGConstrainedProperty cgConstrainedProperty = (CGConstrainedProperty)cgProperty;
		if (cgConstrainedProperty.isRequired()) {
			CGValuedElement body = cgConstrainedProperty.getBody();
			if (body != null) {
				boxingAnalyzer.rewriteAsGuarded(body, false, "body for '" + cgConstrainedProperty.getAst() + "'");
			}
		}
	}

/*	protected boolean generateForwardJavaCall(@NonNull CG2JavaVisitor cg2javaVisitor, @NonNull JavaStream js, @NonNull CGExecutorPropertyCallExp cgPropertyCallExp) {
		CGValuedElement asSource = cgPropertyCallExp.getSource();
		CGValuedElement cgSource = asSource != null ? cg2javaVisitor.getExpression(asSource) : null;
		if ((cgSource != null) && !js.appendLocalStatements(cgSource)) {
			return false;
		}
		//
		//	CGExecutorProperty cgExecutorProperty = ClassUtil.nonNullState(cgPropertyCallExp.getExecutorProperty());
		Boolean ecoreIsRequired = Boolean.FALSE;						// CP properties evaluate is nullable -- FIXME compute rather than assume
		//	boolean isPrimitive = js.isPrimitive(cgPropertyCallExp);
		JavaCodeGenerator codeGenerator = cg2javaVisitor.getCodeGenerator();
		Boolean isRequired = codeGenerator.isRequired(cgPropertyCallExp);
		if ((isRequired == Boolean.TRUE) && (ecoreIsRequired != Boolean.TRUE)) {
			js.appendSuppressWarningsNull(true);
		}
		js.appendDeclaration(cgPropertyCallExp);
		js.append(" = ");
		final JavaGlobalContext<@NonNull ? extends JavaCodeGenerator> globalContext = codeGenerator.getGlobalContext();
		TypeDescriptor typeDescriptor = codeGenerator.getTypeDescriptor(cgPropertyCallExp);
		JavaStream.SubStream castBody = new JavaStream.SubStream() {
			@Override
			public void append() {
				js.appendReferenceTo(cgPropertyCallExp.getExecutorProperty());
				js.append(".");
				js.append(globalContext.getEvaluateName());
				js.append("(");
				//		js.append(getValueName(localContext.getEvaluatorParameter(cgPropertyCallExp)));
				js.append(globalContext.getExecutorName());
				js.append(", ");
				js.appendIdReference(cgPropertyCallExp.getASTypeId());
				js.append(", ");
				js.appendValueName(cgSource);
				js.append(")");
			}
		};
		typeDescriptor.appendCast(js, isRequired, null, castBody);
		js.append(";\n");
		return true;
	} */

/*	private boolean generateForwardJavaDeclaration(@NonNull CG2JavaVisitor cg2javaVisitor, @NonNull JavaStream js, @NonNull CGExecutorNavigationProperty cgProperty) {
		js.appendDeclaration(cgProperty);
		js.append(" = new ");
		js.appendClassReference(null, cgProperty);
		js.append("(");
		js.appendIdReference(cgProperty.getUnderlyingPropertyId().getElementId());
		js.append(");\n");
		return true;
	} */

/*	@Override
	public boolean generateJavaDeclaration(	@NonNull CG2JavaVisitor cg2javaVisitor, @NonNull JavaStream js, @NonNull CGProperty cgProperty) {
		assert cgProperty instanceof CGExecutorNavigationProperty;
	//	if (cgProperty instanceof CGExecutorNavigationProperty) {
			return generateForwardJavaDeclaration(cg2javaVisitor, js, (CGExecutorNavigationProperty)cgProperty);
	//	}
	//	else {
	//		return generateOppositeJavaDeclaration(cg2javaVisitor, js, (CGExecutorOppositeProperty)cgProperty);
	//	}
	} */

/*	@Override
	public boolean generateJavaCall(@NonNull CG2JavaVisitor cg2javaVisitor, @NonNull JavaStream js, @NonNull CGNavigationCallExp cgPropertyCallExp) {
		if (cgPropertyCallExp instanceof CGExecutorPropertyCallExp) {
			return generateForwardJavaCall(cg2javaVisitor, js, (CGExecutorPropertyCallExp)cgPropertyCallExp);
		}
		else {
			return generateOppositeJavaCall(cg2javaVisitor, js, (CGExecutorOppositePropertyCallExp)cgPropertyCallExp);
		}
	} */

/*	protected boolean generateOppositeJavaCall(@NonNull CG2JavaVisitor cg2javaVisitor, @NonNull JavaStream js, @NonNull CGExecutorOppositePropertyCallExp cgPropertyCallExp) {
		CGValuedElement source = cg2javaVisitor.getExpression(cgPropertyCallExp.getSource());
		//
		if (!js.appendLocalStatements(source)) {
			return false;
		}
		//
		final JavaGlobalContext<@NonNull ? extends JavaCodeGenerator> globalContext = cg2javaVisitor.getCodeGenerator().getGlobalContext();
		js.appendDeclaration(cgPropertyCallExp);
		js.append(" = ");
		SubStream castBody = new SubStream() {
			@Override
			public void append() {
				js.appendReferenceTo(cgPropertyCallExp.getExecutorProperty());
				js.append(".");
				js.append(globalContext.getEvaluateName());
				js.append("(");
				//		js.append(getValueName(localContext.getEvaluatorParameter(cgPropertyCallExp)));
				js.append(globalContext.getExecutorName());
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
	} */

/*	private boolean generateOppositeJavaDeclaration(@NonNull CG2JavaVisitor cg2javaVisitor, @NonNull JavaStream js, @NonNull CGExecutorOppositeProperty cgProperty) {
		Property asProperty = (Property) cgProperty.getAst();
		Property asOppositeProperty = asProperty.getOpposite();
		js.appendDeclaration(cgProperty);
		js.append(" = new ");
		js.appendClassReference(null, cgProperty);
		js.append("(");
		js.appendIdReference(asOppositeProperty.getPropertyId());
		js.append(");\n");
		return true;
	} */
}
