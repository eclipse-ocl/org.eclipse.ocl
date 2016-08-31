package org.eclipse.ocl.examples.codegen.completeocl;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.examples.codegen.cgmodel.CGElement;
import org.eclipse.ocl.examples.codegen.java.JavaLocalContext;

/**
 * A JavaLocalContext maintains the Java-specific context for generation of code from a CGOperation.
 */
public class CompleteOCLLocalContext extends JavaLocalContext<@NonNull CompleteOCLCodeGenerator>
{
	public CompleteOCLLocalContext(@NonNull CompleteOCLGlobalContext globalContext, @NonNull CGElement cgScope) {
		super(globalContext, cgScope);
	}

	/*	@Override
	public @NonNull CGValuedElement createIdResolverVariable() {
		CGValuedElement evaluator = createEvaluatorVariable();
		CGValuedElement idResolverVariable = super.createIdResolverVariable();
		idResolverVariable.getOwns().add(evaluator);
		return idResolverVariable;
	} */

	@Override
	public @NonNull CompleteOCLGlobalContext getGlobalContext() {
		return (CompleteOCLGlobalContext) globalContext;
	}
}
