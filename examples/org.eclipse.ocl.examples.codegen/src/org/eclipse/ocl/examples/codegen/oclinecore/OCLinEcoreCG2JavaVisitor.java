/*******************************************************************************
 * Copyright (c) 2013, 2016 CEA LIST and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   E.D.Willink(CEA LIST) - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.codegen.oclinecore;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.codegen.ecore.genmodel.GenClassifier;
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.examples.codegen.cgmodel.CGClass;
import org.eclipse.ocl.examples.codegen.cgmodel.CGConstantExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGConstraint;
import org.eclipse.ocl.examples.codegen.cgmodel.CGExecutorType;
import org.eclipse.ocl.examples.codegen.cgmodel.CGInvalid;
import org.eclipse.ocl.examples.codegen.cgmodel.CGOperation;
import org.eclipse.ocl.examples.codegen.cgmodel.CGPackage;
import org.eclipse.ocl.examples.codegen.cgmodel.CGProperty;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.generator.TypeDescriptor;
import org.eclipse.ocl.examples.codegen.java.CG2JavaVisitor;
import org.eclipse.ocl.pivot.Constraint;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.Feature;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.utilities.ClassUtil;

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
					String bodyText = generateBody(cgBody, returnClassName);
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
					String bodyText = generateBody(cgBody, returnClassName);
					String fragmentURI = getFragmentURI(asProperty);
					bodies.put(fragmentURI, bodyText);
				}
			}
		}
		localContext = null;
		return bodies;
	}

	protected @NonNull String generateBody(@NonNull CGValuedElement cgBody, @NonNull String returnClassName) {
		definition2references = new HashMap<>();
		js.resetStream();
		js.appendCommentWithOCL(null, cgBody.getAst());
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
		js.append(";");
		return toString();
	}

	protected @NonNull String getFragmentURI(@NonNull Element element) {
		return String.valueOf(EcoreUtil.getURI(element).fragment());
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
	public @NonNull Boolean visitCGOperation(@NonNull CGOperation cgOperation) {
		return true;
	}

	@Override
	public @NonNull Boolean visitCGPackage(@NonNull CGPackage cgPackage) {
		return true;
	}
}
