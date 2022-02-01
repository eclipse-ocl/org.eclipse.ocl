/*******************************************************************************
 * Copyright (c) 2013, 2018 CEA LIST and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink(CEA LIST) - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.codegen.oclinecore;

import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.analyzer.NameResolution;
import org.eclipse.ocl.examples.codegen.cgmodel.CGNamedElement;
import org.eclipse.ocl.examples.codegen.java.JavaGlobalContext;
import org.eclipse.ocl.examples.codegen.java.JavaLocalContext;
import org.eclipse.ocl.pivot.NamedElement;

/**
 * A JavaGlobalContext maintains the Java-specific global context for generation of code.
 */
public class OCLinEcoreGlobalContext extends JavaGlobalContext<@NonNull OCLinEcoreCodeGenerator>
{
	protected final @NonNull GenPackage genPackage;
	protected final @NonNull NameResolution contextName;
	protected final @NonNull NameResolution diagnosticsName;
	protected final @NonNull NameResolution messageName;
	protected final @NonNull NameResolution severityName;
//	protected final @NonNull CGParameter contextParameter;
//	protected final @NonNull CGParameter diagnosticsParameter;
//	protected final @NonNull CGParameter messageParameter;
//	protected final @NonNull CGParameter severityParameter;

	public OCLinEcoreGlobalContext(@NonNull OCLinEcoreCodeGenerator codeGenerator, @NonNull GenPackage genPackage) {
		super(codeGenerator);
		this.genPackage = genPackage;
		this.contextName = globalNameManager.declareGlobalName(null, "context");
		this.diagnosticsName = globalNameManager.declareGlobalName(null, "diagnostics");
		this.messageName = globalNameManager.declareGlobalName(null, "message");
		this.severityName = globalNameManager.declareGlobalName(null, "severity");
//		this.contextParameter = analyzer.createCGParameter(contextName, analyzer.getTypeId(JavaConstants.getJavaTypeId(Map.class)), false);
//		this.diagnosticsParameter = analyzer.createCGParameter(diagnosticsName, analyzer.getTypeId(JavaConstants.getJavaTypeId(Diagnostic.class)), false);
//		this.messageParameter = analyzer.createCGParameter(messageName, analyzer.getTypeId(JavaConstants.getJavaTypeId(String.class)), false);
//		this.severityParameter = analyzer.createCGParameter(severityName, analyzer.getTypeId(JavaConstants.getJavaTypeId(Integer.class)), false);
	}

	@Override
	public @NonNull OCLinEcoreLocalContext createLocalContext(@Nullable JavaLocalContext<@NonNull ?> outerContext, @NonNull CGNamedElement cgNamedElement, @NonNull NamedElement asNamedElement) {
		return new OCLinEcoreLocalContext(this, (OCLinEcoreLocalContext)outerContext, cgNamedElement, asNamedElement);
	}

	/*	public @NonNull CGParameter createSelfParameter(@NonNull Variable contextVariable) {
		CGTextParameter cgTextParameter = CGModelFactory.eINSTANCE.createCGTextParameter();
		cgTextParameter.setName(contextVariable.getName());
		cgTextParameter.setValueName(getSelfName());
		cgTextParameter.setAst(contextVariable);
		cgTextParameter.setTextValue(JavaConstants.THIS_NAME);
		cgTextParameter.setNonInvalid();
		cgTextParameter.setNonNull();
		cgTextParameter.setTypeId(analyzer.getTypeId(contextVariable.getTypeId()));
		return cgTextParameter;
	} */

	public @NonNull NameResolution getContextName() {
		return contextName;
	}

//	public @NonNull CGParameter getContextParameter() {
//		return contextParameter;
//	}

	public @NonNull NameResolution getDiagnosticsName() {
		return diagnosticsName;
	}

//	public @NonNull CGParameter getDiagnosticsParameter() {
//		return diagnosticsParameter;
//	}

	public @NonNull NameResolution getMessageName() {
		return messageName;
	}

//	public @NonNull CGParameter getMessageParameter() {
//		return messageParameter;
//	}

	public @NonNull NameResolution getSeverityName() {
		return severityName;
	}

//	public @NonNull CGParameter getSeverityParameter() {
//		return severityParameter;
//	}

	public @NonNull String getTablesClassName() {
		return codeGenerator.getGenModelHelper().getTablesClassName(genPackage);
	}

/*	protected @NonNull String queueValueName(@NonNull CGParameter cgParameter) {
		String name = cgParameter.getName();
		assert name != null;
		globalNameManager.queueValueName(cgParameter, name);
		return name;
	} */
}