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

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.analyzer.CodeGenAnalyzer;
import org.eclipse.ocl.examples.codegen.cgmodel.CGLibraryPropertyCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGModelFactory;
import org.eclipse.ocl.examples.codegen.cgmodel.CGNavigationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGProperty;
import org.eclipse.ocl.examples.codegen.cgmodel.CGTypeId;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.generator.CodeGenerator;
import org.eclipse.ocl.examples.codegen.java.CG2JavaVisitor;
import org.eclipse.ocl.examples.codegen.java.JavaCodeGenerator;
import org.eclipse.ocl.examples.codegen.java.JavaStream;
import org.eclipse.ocl.examples.codegen.java.JavaStream.SubStream;
import org.eclipse.ocl.examples.codegen.naming.GlobalNameManager;
import org.eclipse.ocl.examples.codegen.utilities.CGUtil;
import org.eclipse.ocl.pivot.NavigationCallExp;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.library.LibraryProperty;
import org.eclipse.ocl.pivot.library.collection.CollectionElementTypeProperty;
import org.eclipse.ocl.pivot.library.collection.CollectionLowerProperty;
import org.eclipse.ocl.pivot.library.collection.CollectionUpperProperty;
import org.eclipse.ocl.pivot.library.map.MapKeyTypeProperty;
import org.eclipse.ocl.pivot.library.map.MapValueTypeProperty;
import org.eclipse.ocl.pivot.library.oclany.OclElementOclContentsProperty;
import org.eclipse.ocl.pivot.utilities.ClassUtil;

/**
 *  NativePropertyCallingConvention defines the support for the call of a property realized by native code.
 *   *  </br>
 *  e.g. as XXXTables.FOREIGN_qualified_class.FC_class.INSTANCE.evaluate(executor, arguments)
 */
public class LibraryPropertyCallingConvention extends AbstractPropertyCallingConvention
{
	private static final @NonNull LibraryPropertyCallingConvention INSTANCE = new LibraryPropertyCallingConvention();

	public static @NonNull LibraryPropertyCallingConvention getInstance(@NonNull Property asProperty) {
		INSTANCE.logInstance(asProperty);
		return INSTANCE;
	}

/*	@Override
	public @NonNull CGValuedElement createCGOppositePropertyCallExp(@NonNull AS2CGVisitor as2cgVisitor, @NonNull CGProperty cgProperty,
			@NonNull LibraryProperty libraryProperty, @NonNull CGValuedElement cgSource, @NonNull OppositePropertyCallExp asOppositePropertyCallExp) {
//		throw new UnsupportedOperationException();
		CodeGenerator codeGenerator = as2cgVisitor.getCodeGenerator();
		Property asOppositeProperty = ClassUtil.nonNullModel(asOppositePropertyCallExp.getReferredProperty());
		Property asProperty = ClassUtil.nonNullModel(asOppositeProperty.getOpposite());
		boolean isRequired = asProperty.isIsRequired();
		LibraryProperty libraryProperty2 = codeGenerator.getEnvironmentFactory().getMetamodelManager().getImplementation(asOppositePropertyCallExp, null, asProperty);
		assert libraryProperty2 == libraryProperty;
		CGOppositePropertyCallExp cgPropertyCallExp = null;
		if ((libraryProperty instanceof CompositionProperty) || (libraryProperty instanceof ImplicitNonCompositionProperty)) {
			EStructuralFeature eStructuralFeature = (EStructuralFeature) asProperty.getESObject();
			if (eStructuralFeature != null) {
				try {
					codeGenerator.getGenModelHelper().getGetAccessor(eStructuralFeature);
					CGEcoreOppositePropertyCallExp cgEcorePropertyCallExp = CGModelFactory.eINSTANCE.createCGEcoreOppositePropertyCallExp();
					cgEcorePropertyCallExp.setEStructuralFeature(eStructuralFeature);
					Boolean ecoreIsRequired = codeGenerator.isNonNull(asProperty);
					if (ecoreIsRequired != null) {
						isRequired = ecoreIsRequired;
					}
					cgPropertyCallExp = cgEcorePropertyCallExp;
				} catch (GenModelException e) {
					codeGenerator.addProblem(e);
				}
			}
		}
		else if (libraryProperty instanceof ExtensionProperty){
			CGExecutorOppositePropertyCallExp cgExecutorPropertyCallExp = CGModelFactory.eINSTANCE.createCGExecutorOppositePropertyCallExp();
			CGExecutorProperty cgExecutorProperty = as2cgVisitor.getAnalyzer().createExecutorOppositeProperty(asProperty);
			cgExecutorPropertyCallExp.setExecutorProperty(cgExecutorProperty);
			cgExecutorPropertyCallExp.getOwns().add(cgExecutorProperty);
			cgPropertyCallExp = cgExecutorPropertyCallExp;
		}
		else {
			// throw new UnsupportedOperationException("AS2CGVisitor.generateOppositePropertyCallExp for " + libraryProperty.getClass().getSimpleName());
			assert false : "Unsupported AS2CGVisitor.generateOppositePropertyCallExp for " + libraryProperty.getClass().getSimpleName();
		}
		if (cgPropertyCallExp == null) {
			CGExecutorOppositePropertyCallExp cgExecutorPropertyCallExp = CGModelFactory.eINSTANCE.createCGExecutorOppositePropertyCallExp();
			CGExecutorProperty cgExecutorProperty = as2cgVisitor.getAnalyzer().createExecutorOppositeProperty(asProperty);
			cgExecutorPropertyCallExp.setExecutorProperty(cgExecutorProperty);
			cgExecutorPropertyCallExp.getOwns().add(cgExecutorProperty);
			cgPropertyCallExp = cgExecutorPropertyCallExp;
		}
		cgPropertyCallExp.setAsProperty(asProperty);
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
		assert ((libraryProperty instanceof OclElementOclContentsProperty)
			 || (libraryProperty instanceof CollectionElementTypeProperty)
			 || (libraryProperty instanceof CollectionLowerProperty)
			 || (libraryProperty instanceof CollectionUpperProperty)
			 || (libraryProperty instanceof MapKeyTypeProperty)
			 || (libraryProperty instanceof MapValueTypeProperty));
		CGLibraryPropertyCallExp cgPropertyCallExp = CGModelFactory.eINSTANCE.createCGLibraryPropertyCallExp();
		cgPropertyCallExp.setReferredProperty(cgProperty);
		cgPropertyCallExp.setLibraryProperty(libraryProperty);
		cgPropertyCallExp.setAsProperty(asProperty);
		analyzer.initAst(cgPropertyCallExp, asPropertyCallExp, true);
		cgPropertyCallExp.setRequired(isRequired || codeGenerator.isPrimitive(cgPropertyCallExp));
		cgPropertyCallExp.setSource(cgSource);
		return cgPropertyCallExp;
	}

	public @NonNull CGValuedElement createCGNavigationCallExp2(@NonNull CodeGenAnalyzer analyzer, @NonNull CGProperty cgProperty,
			@Nullable CGValuedElement cgSource) {
		CodeGenerator codeGenerator = analyzer.getCodeGenerator();
		Property asProperty = CGUtil.getAST(cgProperty);
		LibraryProperty libraryProperty = (LibraryProperty) asProperty.getImplementation();
		boolean isRequired = asProperty.isIsRequired();
		CGLibraryPropertyCallExp cgPropertyCallExp = CGModelFactory.eINSTANCE.createCGLibraryPropertyCallExp();
		cgPropertyCallExp.setReferredProperty(cgProperty);
		cgPropertyCallExp.setLibraryProperty(libraryProperty);
		cgPropertyCallExp.setAsProperty(asProperty);
		analyzer.initAst(cgPropertyCallExp, asProperty, false);
		cgPropertyCallExp.setRequired(isRequired || codeGenerator.isPrimitive(cgPropertyCallExp));
		cgPropertyCallExp.setSource(cgSource);
		return cgPropertyCallExp;
	}

	@Override
	public boolean generateJavaCall(@NonNull CG2JavaVisitor cg2javaVisitor, @NonNull CGNavigationCallExp cgPropertyCallExp) {
		JavaStream js = cg2javaVisitor.getJavaStream();
		JavaCodeGenerator codeGenerator = cg2javaVisitor.getCodeGenerator();
		GlobalNameManager globalNameManager = codeGenerator.getGlobalNameManager();
		CGLibraryPropertyCallExp cgLibraryPropertyCallExp = (CGLibraryPropertyCallExp) cgPropertyCallExp;
		CGValuedElement source = cg2javaVisitor.getExpression(cgPropertyCallExp.getSource());
		LibraryProperty libraryProperty = ClassUtil.nonNullState(cgLibraryPropertyCallExp.getLibraryProperty());
		Method actualMethod = libraryProperty.getEvaluateMethod(CGUtil.getAsProperty(cgPropertyCallExp));
		Class<?> actualReturnClass = actualMethod.getReturnType();
		boolean actualIsNonNull = codeGenerator.getIsNonNull(actualMethod) == Boolean.TRUE;
		boolean expectedIsNonNull = cgPropertyCallExp.isNonNullChecked();
		//		Class<?> actualBoxedReturnClass = getBoxedReturnClass(libraryProperty);
		//		CGValuedElement resultVariable = cgOperationCallExp; //.getValue();
		CGTypeId resultType = cgPropertyCallExp.getTypeId();
		//		Class<?> requiredBoxedReturnClass = context.getBoxedClass(resultType.getElementId());
		if (!js.appendLocalStatements(source)) {
			return false;
		}
		js.appendDeclaration(cgPropertyCallExp);
		js.append(" = ");
		boolean isRequiredNullCast = expectedIsNonNull && !actualIsNonNull;
		//		if (expectedIsNonNull && !actualIsNonNull) {
		//			js.appendClassReference(null, ClassUtil.class);
		//			js.append(".nonNullState(");
		//		}
		SubStream castBody = new SubStream() {
			@Override
			public void append() {
				js.appendClassReference(null, libraryProperty.getClass());
				//		CGOperation cgOperation = ClassUtil.nonNullState(CGUtils.getContainingOperation(cgPropertyCallExp));
				js.append(".");
				js.append(globalNameManager.getInstanceName());
				js.append(".");
				js.append(globalNameManager.getEvaluateName());
				js.append("(");
				//		if (!(libraryOperation instanceof LibrarySimpleOperation)) {
				//			js.append(getValueName(localContext.getEvaluatorParameter(cgPropertyCallExp)));
				js.append(globalNameManager.getExecutorName());
				js.append(", ");
				//			if (!(libraryProperty instanceof LibraryUntypedOperation)) {
				//				CGTypeVariable typeVariable = localContext.getTypeVariable(resultType);
				js.appendValueName(resultType);
				js.append(", ");
				//			}
				//		}
				js.appendValueName(source);
				//				if (expectedIsNonNull && !actualIsNonNull) {
				//					js.append(")");
				//				}
				js.append(")");
			}
		};
		js.appendClassCast(cgPropertyCallExp, isRequiredNullCast, actualReturnClass, castBody);
		js.append(";\n");
		return true;
	}

	@Override
	public boolean generateJavaDeclaration(@NonNull CG2JavaVisitor cg2javaVisitor, @NonNull CGProperty cgProperty) {
		return generateJavaDeclarationUnimplemented(cg2javaVisitor, cgProperty);		// XXX
	}
}
