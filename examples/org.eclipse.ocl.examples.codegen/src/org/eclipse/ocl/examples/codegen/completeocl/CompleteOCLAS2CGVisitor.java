/*******************************************************************************
 * Copyright (c) 2014, 2015 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.codegen.completeocl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.analyzer.AS2CGVisitor;
import org.eclipse.ocl.examples.codegen.analyzer.CodeGenAnalyzer;
import org.eclipse.ocl.examples.codegen.cgmodel.CGClass;
import org.eclipse.ocl.examples.codegen.cgmodel.CGModelFactory;
import org.eclipse.ocl.examples.codegen.cgmodel.CGNamedElement;
import org.eclipse.ocl.examples.codegen.cgmodel.CGOperation;
import org.eclipse.ocl.examples.codegen.cgmodel.CGPackage;
import org.eclipse.ocl.examples.codegen.cgmodel.CGParameter;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.LanguageExpression;
import org.eclipse.ocl.pivot.Model;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.ParserException;

/**
 * @since 1.3
 */
public final class CompleteOCLAS2CGVisitor extends AS2CGVisitor
{
	private @NonNull Map<@NonNull Operation, @NonNull CGOperation> deferredOperations = new HashMap<>();

	public CompleteOCLAS2CGVisitor(@NonNull CodeGenAnalyzer analyzer) {
		super(analyzer);
	}

	public @NonNull CGPackage createCGDocument(@NonNull Model document, @NonNull String rootPackageNames, @NonNull String rootClassName) {
		CGClass cgClass = (CGClass) ClassUtil.nonNullState(document.accept(this));
		cgClass.setName(rootClassName);
		CGPackage cgDocument = CGModelFactory.eINSTANCE.createCGPackage();
		//		setAst(cgDocument, asModel);
		cgDocument.setName(rootPackageNames);
		cgDocument.getClasses().add(cgClass);
		createOperationBodies();
		return cgDocument;
	}

	protected void createOperationBodies() {
		for (@NonNull Operation asOperation : deferredOperations.keySet()) {
			CGOperation cgOperation = deferredOperations.get(asOperation);
			assert cgOperation != null;
			LanguageExpression specification = asOperation.getBodyExpression();
			assert specification != null;
			try {
				ExpressionInOCL query = metamodelManager.parseSpecification(specification);
				createParameters(cgOperation, query);
				cgOperation.setBody(doVisit(CGValuedElement.class, query.getOwnedBody()));
			} catch (ParserException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public @NonNull CompleteOCLCodeGenerator getCodeGenerator() {
		return (@NonNull CompleteOCLCodeGenerator) context.getCodeGenerator();
	}

	/*	@Override
	public @NonNull CGValuedElement visitExpressionInOCL(@NonNull ExpressionInOCL element) {
		Variable contextVariable = element.getOwnedContext();
		if (contextVariable != null) {
			CGVariable cgContext = getParameter(contextVariable, null);
			cgContext.setTypeId(context.getTypeId(TypeId.OCL_VOID));			// FIXME Java-specific
			cgContext.setNonInvalid();
//			cgContext.setNonNull();
		}
		for (@SuppressWarnings("null")@NonNull Variable parameterVariable : element.getOwnedParameters()) {
			@SuppressWarnings("unused") CGVariable cgParameter = getParameter(parameterVariable, null);
		}
		CGValuedElement cgBody = doVisit(CGValuedElement.class, element.getOwnedBody());
//		cgOperation.getDependsOn().add(cgBody);
		return cgBody;
	} */

	@Override
	public @Nullable CGNamedElement visitModel(@NonNull Model asModel) {
		CGClass cgRootClass = CGModelFactory.eINSTANCE.createCGClass();
		setAst(cgRootClass, asModel);
		for (org.eclipse.ocl.pivot.Package asPackage : asModel.getOwnedPackages()) {
			CGClass cgClass = doVisit(CGClass.class, asPackage);
			cgRootClass.getClasses().add(cgClass);
		}
		return cgRootClass;
	}

	@Override
	public @Nullable CGOperation visitOperation(@NonNull Operation element) {
		CGOperation cgOperation = CGModelFactory.eINSTANCE.createCGLibraryOperation();
		setAst(cgOperation, element);
		cgOperation.setRequired(element.isIsRequired());
		CompleteOCLLocalContext localContext = getCodeGenerator().getGlobalContext().getLocalContext(cgOperation);
		if (localContext != null) {
			List<CGParameter> cgParameters = cgOperation.getParameters();
			CGParameter executorParameter = localContext.createExecutorParameter();
			if (executorParameter != null) {
				cgParameters.add(executorParameter);
			}
			CGParameter typeIdParameter = localContext.createTypeIdParameter();
			if (typeIdParameter != null) {
				cgParameters.add(typeIdParameter);
			}
		}
		LanguageExpression specification = element.getBodyExpression();
		if (specification != null) {
			deferredOperations.put(element, cgOperation);
		}
		return cgOperation;
	}

	@Override
	public @Nullable CGClass visitPackage(org.eclipse.ocl.pivot.@NonNull Package asPackage) {
		CGClass cgClass = CGModelFactory.eINSTANCE.createCGClass();
		setAst(cgClass, asPackage);
		for (org.eclipse.ocl.pivot.Package asNestedPackage : asPackage.getOwnedPackages()) {
			CGClass cgNestedClass = doVisit(CGClass.class, asNestedPackage);
			cgClass.getClasses().add(cgNestedClass);
		}
		for (org.eclipse.ocl.pivot.Class asNestedClass : asPackage.getOwnedClasses()) {
			CGClass cgNestedClass = doVisit(CGClass.class, asNestedClass);
			cgClass.getClasses().add(cgNestedClass);
		}
		return cgClass;
	}
}
