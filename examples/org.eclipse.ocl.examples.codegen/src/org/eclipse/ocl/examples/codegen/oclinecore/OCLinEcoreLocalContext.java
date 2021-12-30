/*******************************************************************************
 * Copyright (c) 2013, 2019 CEA LIST and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink(CEA LIST) - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.codegen.oclinecore;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.cgmodel.CGConstraint;
import org.eclipse.ocl.examples.codegen.cgmodel.CGElement;
import org.eclipse.ocl.examples.codegen.cgmodel.CGModelFactory;
import org.eclipse.ocl.examples.codegen.cgmodel.CGParameter;
import org.eclipse.ocl.examples.codegen.cgmodel.CGText;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.java.ImportUtils;
import org.eclipse.ocl.examples.codegen.java.JavaConstants;
import org.eclipse.ocl.examples.codegen.java.JavaLocalContext;
import org.eclipse.ocl.pivot.utilities.PivotUtil;

/**
 * A JavaLocalContext maintains the Java-specific context for generation of code from a CGOperation.
 */
public class OCLinEcoreLocalContext extends JavaLocalContext<@NonNull OCLinEcoreCodeGenerator>
{
	protected final @Nullable String contextName;
	protected final @Nullable String diagnosticsName;
	protected final @Nullable String messageName;
	protected final @Nullable String severityName;

	public OCLinEcoreLocalContext(@NonNull OCLinEcoreGlobalContext globalContext, @NonNull CGElement cgScope) {
		super(globalContext, cgScope);
		if (cgScope instanceof CGConstraint) {
			this.contextName = nameManagerContext.getSymbolName(null, "context");
			this.diagnosticsName = nameManagerContext.getSymbolName(null, "diagnostics");
			this.messageName = nameManagerContext.getSymbolName(null, "message");
			this.severityName = nameManagerContext.getSymbolName(null, "severity");
		}
		else {
			this.contextName = null;
			this.diagnosticsName = null;
			this.messageName = null;
			this.severityName = null;
		}
	}

	@Override
	public @Nullable CGParameter createExecutorParameter() {
		return null;
	}

	@Override
	public @Nullable CGValuedElement createExecutorVariable(@Nullable String contextName) {
		CGText evaluator = CGModelFactory.eINSTANCE.createCGText();
		setNames2(evaluator, JavaConstants.EXECUTOR_NAME, JavaConstants.EXECUTOR_TYPE_ID);
		String utilClassName = ImportUtils.getAffixedName(PivotUtil.class);
		StringBuilder s = new StringBuilder();
		s.append(utilClassName);
		s.append(".getExecutor(this)");
		evaluator.setTextValue(s.toString());
		return evaluator;
	}

	@Override
	public @NonNull CGValuedElement createIdResolverVariable(@Nullable String contextName) {
		CGValuedElement evaluator = createExecutorVariable(contextName);
		CGValuedElement idResolverVariable = super.createIdResolverVariable(contextName);
		idResolverVariable.getOwns().add(evaluator);
		return idResolverVariable;
	}

	@Override
	public @Nullable CGParameter createTypeIdParameter() {
		return null;
	}

	public @Nullable String getContextName() {
		return contextName;
	}

	public @Nullable String getDiagnosticsName() {
		return diagnosticsName;
	}

	@Override
	public @NonNull OCLinEcoreGlobalContext getGlobalContext() {
		return (OCLinEcoreGlobalContext) globalContext;
	}

	public @Nullable String getMessageName() {
		return messageName;
	}

	public @Nullable String getSeverityName() {
		return severityName;
	}
}
