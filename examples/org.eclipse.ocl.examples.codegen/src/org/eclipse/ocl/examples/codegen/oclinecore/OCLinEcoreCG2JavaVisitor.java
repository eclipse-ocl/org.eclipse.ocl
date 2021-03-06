/*******************************************************************************
 * Copyright (c) 2013, 2021 CEA LIST and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink(CEA LIST) - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.codegen.oclinecore;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.codegen.ecore.genmodel.GenClass;
import org.eclipse.emf.codegen.ecore.genmodel.GenClassifier;
import org.eclipse.emf.codegen.ecore.genmodel.GenOperation;
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.cgmodel.CGClass;
import org.eclipse.ocl.examples.codegen.cgmodel.CGConstantExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGConstraint;
import org.eclipse.ocl.examples.codegen.cgmodel.CGExecutorType;
import org.eclipse.ocl.examples.codegen.cgmodel.CGInvalid;
import org.eclipse.ocl.examples.codegen.cgmodel.CGLibraryOperationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGOperation;
import org.eclipse.ocl.examples.codegen.cgmodel.CGPackage;
import org.eclipse.ocl.examples.codegen.cgmodel.CGParameter;
import org.eclipse.ocl.examples.codegen.cgmodel.CGProperty;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.generator.GenModelHelper;
import org.eclipse.ocl.examples.codegen.generator.TypeDescriptor;
import org.eclipse.ocl.examples.codegen.java.CG2JavaVisitor;
import org.eclipse.ocl.examples.codegen.java.JavaConstants;
import org.eclipse.ocl.examples.codegen.utilities.CGUtil;
import org.eclipse.ocl.pivot.CollectionType;
import org.eclipse.ocl.pivot.Constraint;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.Feature;
import org.eclipse.ocl.pivot.NamedElement;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.VariableDeclaration;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.library.LibraryOperation;
import org.eclipse.ocl.pivot.library.string.CGStringGetSeverityOperation;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.utilities.ValueUtil;

/**
 * An OCLinEcoreCG2JavaVisitor supports generation of the OCL embedded in an Ecore model
 * into the Java bodies of the code producxed by GenModel.
 */
public class OCLinEcoreCG2JavaVisitor extends CG2JavaVisitor<@NonNull OCLinEcoreCodeGenerator>
{
	protected final @NonNull GenPackage genPackage;
	protected final @NonNull CGPackage cgPackage;
	protected ExpressionInOCL expInOcl;
	protected Feature feature;

	public OCLinEcoreCG2JavaVisitor(@NonNull OCLinEcoreCodeGenerator codeGenerator,
			@NonNull GenPackage genPackage, @NonNull CGPackage cgPackage) {
		super(codeGenerator);
		this.genPackage = genPackage;
		this.cgPackage = cgPackage;
	}

	@Override
	protected void appendGlobalPrefix() {
		js.append(getGlobalContext().getTablesClassName());
		js.append(".");
	}

	protected @Nullable Boolean doGetSeverity(@NonNull CGLibraryOperationCallExp cgOperationCallExp) {
		// OCL, OCL-CG has no support for EOperation litetals so the original validation check severity predicate used a magic String.
		// Now we recognise the old-style call and re-implement as a check on the EOperation litetal that we can compute anyway.
		CGConstraint cgConstraint = CGUtil.getContainingConstraint(cgOperationCallExp);
		if (cgConstraint != null) {
			Constraint asConstraint = CGUtil.getAST(cgConstraint);
			EObject eOperation = asConstraint.getESObject();
			CGClass cgClass = CGUtil.getContainingClass(cgConstraint);
			if (cgClass != null) {
				org.eclipse.ocl.pivot.Class asClass = CGUtil.getAST(cgClass);
				GenModelHelper genModelHelper = context.getGenModelHelper();
				GenClassifier genClassifier = genModelHelper.getGenClassifier(asClass);
				if (genClassifier instanceof GenClass) {
					GenClass genClass = (GenClass)genClassifier;
					for (GenOperation genOperation : genClass.getGenOperations()) {
						if (genOperation.getEcoreOperation() == eOperation) {
							js.appendDeclaration(cgOperationCallExp);
							js.append(" = ");
							js.appendClassReference(null, CGStringGetSeverityOperation.class);
							js.append("."+ globalContext.getInstanceName() + "."+ globalContext.getEvaluateName() + "(");
							js.append(JavaConstants.EXECUTOR_NAME);
							js.append(", ");
							js.appendClassReference(null, genClassifier.getGenPackage().getQualifiedPackageInterfaceName());
							js.append(".Literals.");
							js.append(genClass.getOperationID(genOperation, false));
							js.append(");\n");
							return Boolean.TRUE;
						}
					}
				}
			}
		}
		return null;
	}

	public @NonNull Map<String, String> generateBodies() {
		Map<String, String> bodies = new HashMap<String, String>();
		for (CGClass cgClass : cgPackage.getClasses()) {
			for (CGConstraint cgConstraint : cgClass.getInvariants()) {
				CGValuedElement cgBody = cgConstraint.getBody();
				Element pivotClass = cgClass.getAst();
				Element asElement = cgConstraint.getAst();
				if ((cgBody != null) && (pivotClass instanceof org.eclipse.ocl.pivot.Class) && (asElement instanceof Constraint)) {
					Constraint asConstraint = (Constraint) asElement;
					localContext = globalContext.getLocalContext(cgConstraint);
					String bodyText = generateValidatorBody(cgBody, asConstraint, (org.eclipse.ocl.pivot.Class)pivotClass);
					String fragmentURI = getFragmentURI(pivotClass) + "==" + getRuleName(asConstraint);
					bodies.put(fragmentURI, bodyText);
				}
			}
			for (CGOperation cgOperation : cgClass.getOperations()) {
				CGValuedElement cgBody = cgOperation.getBody();
				Element asOperation = cgOperation.getAst();
				if ((cgBody != null) && (asOperation instanceof Operation)) {
					String returnClassName = genModelHelper.getOperationReturnType((Operation)asOperation);
					localContext = globalContext.getLocalContext(cgOperation);
					String bodyText = generateBody(cgOperation.getParameters(), cgBody, returnClassName);
					String fragmentURI = getFragmentURI(asOperation);
					bodies.put(fragmentURI, bodyText);
				}
			}
			for (CGProperty cgProperty : cgClass.getProperties()) {
				CGValuedElement cgBody = cgProperty.getBody();
				Element asProperty = cgProperty.getAst();
				if ((cgBody != null) && (asProperty instanceof Property)) {
					String returnClassName = genModelHelper.getPropertyResultType((Property)asProperty);
					localContext = globalContext.getLocalContext(cgProperty);
					String bodyText = generateBody(null, cgBody, returnClassName);
					String fragmentURI = getFragmentURI(asProperty);
					bodies.put(fragmentURI, bodyText);
				}
			}
		}
		localContext = null;
		return bodies;
	}

	protected @NonNull String generateBody(@Nullable List<CGParameter> cgParameters, @NonNull CGValuedElement cgBody, @NonNull String returnClassName) {
		js.resetStream();
		js.appendCommentWithOCL(null, cgBody.getAst());
		if (cgParameters != null) {
			for (@SuppressWarnings("null")@NonNull CGParameter cgParameter : cgParameters) {
				VariableDeclaration asParameter = CGUtil.getAST(cgParameter);
				Type asType = PivotUtil.getType(asParameter);
				if (asType instanceof CollectionType) {
					js.append("assert ");
					js.appendValueName(cgParameter);
					js.append(" != null;\n");
				}
			}
		}
		js.appendLocalStatements(cgBody);
		CGInvalid cgInvalidValue = cgBody.getInvalidValue();
		if (cgInvalidValue  != null) {
			js.append("throw ");
			js.appendValueName(cgInvalidValue);
		}
		else {
			TypeDescriptor typeDescriptor = context.getTypeDescriptor(cgBody);
			//			String className = typeDescriptor.getClassName();
			//			Class<?> javaClass = typeDescriptor.getJavaClass();
			js.append("return ");
			//			if (returnClassName.contains("<")) {
			//				js.append("(" + returnClassName + ")");
			//			}
			//			js.appendValueName(cgBody);
			typeDescriptor.appendEcoreValue(js, returnClassName, cgBody);
		}
		js.append(";");
		return toString();
	}

	public @NonNull String generateConstants(Iterable<@NonNull CGValuedElement> sortedGlobals) {
		js.resetStream();
		js.pushIndentation(null);
		if (sortedGlobals != null) {
			generateGlobals(sortedGlobals);
		}
		return toString();
	}

	protected @NonNull String generateValidatorBody(@NonNull CGValuedElement cgBody, @NonNull Constraint asConstraint, org.eclipse.ocl.pivot.@NonNull Class asType) {
		js.resetStream();
		boolean isBombproof = false;
		if (cgBody.isTrue() || cgBody.isFalse()) {				// FIXME Deeper bombproof analysis
			isBombproof = true;
		}
		String constraintNameName = JavaConstants.CONSTRAINT_NAME_NAME; //context.getGlobalContext().getConstraintNameName();
		String constraintName = PivotUtil.getName(asConstraint);
		EObject eContainer = asConstraint.eContainer();
		if (eContainer instanceof NamedElement) {
			String containerName = ((NamedElement)eContainer).getName();
			if (containerName != null) {
				constraintName = containerName + "::" + constraintName;
			}
		}
		if (!isBombproof) {
			js.append("final ");
			js.appendClassReference(true, String.class);
			js.append(" ");
			js.append(constraintNameName);
			js.append(" = ");
			js.appendString(constraintName);
			js.append(";\n");
			js.append("try {\n");  								// See Bug 543178 for design rationale
			js.pushIndentation(null);
		}
		GenClassifier genClassifier = genModelHelper.getGenClassifier(asType);
		String genClassifierName = genClassifier != null ? genClassifier.getName() : null;
		if (genClassifierName == null) {
			genClassifierName = "";
		}
		js.appendCommentWithOCL(null, asConstraint);
		js.appendLocalStatements(cgBody);
		js.append("return ");
		if (!cgBody.isNonNull() || (cgBody.getASTypeId() != TypeId.BOOLEAN)) {
			js.append("Boolean.TRUE == ");
		}
		js.appendValueName(cgBody);
		js.append(";\n");
		if (!isBombproof) {
			js.popIndentation();
			js.append("}\n");
			js.append("catch (");
			js.appendClassReference(null, Throwable.class);
			js.append(" e) {\n");
			js.pushIndentation(null);
			js.append("return ");
			js.appendClassReference(null, ValueUtil.class);
			js.append(".validationFailedDiagnostic(");
			js.append(constraintNameName);
			js.append(", this, diagnostics, context, e);\n");
			js.popIndentation();
			js.append("}\n");
		}
		return toString();
	}

	protected @NonNull String getFragmentURI(@NonNull Element element) {
		return String.valueOf(EcoreUtil.getURI(element).fragment());
	}

	@Override
	public @Nullable GenPackage getGenPackage() {
		return genPackage;
	}

	protected @NonNull OCLinEcoreGlobalContext getGlobalContext() {
		return (OCLinEcoreGlobalContext) globalContext;
	}

	protected @NonNull OCLinEcoreLocalContext getLocalContext() {
		return ClassUtil.nonNullState((OCLinEcoreLocalContext) localContext);
	}

	protected String getRuleName(@NonNull Constraint constraint) {
		String name = constraint.getName();
		return name != null ? name : "";
	}

	@Override
	public @NonNull Boolean visitCGClass(@NonNull CGClass cgClass) {
		return true;
	}

	@Override
	public @NonNull Boolean visitCGConstantExp(@NonNull CGConstantExp cgConstantExp) {
		CGValuedElement globalConstant = cgConstantExp.getReferredConstant();
		if (globalConstant instanceof CGExecutorType) {		// FIXME Why is this necessary to avoid a CG failure in CodeGenCompany
			return true;
		}
		if (globalConstant != null) {
			if (!cgConstantExp.isInlined()) {
				appendGlobalPrefix();
			}
			js.appendValueName(globalConstant);
		}
		return true;
	}

	@Override
	public @NonNull Boolean visitCGLibraryOperationCallExp(@NonNull CGLibraryOperationCallExp cgOperationCallExp) {
		LibraryOperation libraryOperation = cgOperationCallExp.getLibraryOperation();
		if (libraryOperation == CGStringGetSeverityOperation.INSTANCE) {
			Boolean flowContinues = doGetSeverity(cgOperationCallExp);
			if (flowContinues != null) {
				return flowContinues;
			}
		}
		return super.visitCGLibraryOperationCallExp(cgOperationCallExp);
	}

	@Override
	public @NonNull Boolean visitCGOperation(@NonNull CGOperation cgOperation) {
		return true;
	}

	@Override
	public @NonNull Boolean visitCGPackage(@NonNull CGPackage cgPackage) {
		return true;
	}
}
