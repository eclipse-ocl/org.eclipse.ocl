package org.eclipse.ocl.examples.codegen.completeocl;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.cgmodel.CGElement;
import org.eclipse.ocl.examples.codegen.java.JavaGlobalContext;

/**
 * A JavaGlobalContext maintains the Java-specific global context for generation of code.
 */
public class CompleteOCLGlobalContext extends JavaGlobalContext<@NonNull CompleteOCLCodeGenerator>
{
	public CompleteOCLGlobalContext(@NonNull CompleteOCLCodeGenerator codeGenerator) {
		super(codeGenerator);
	}

	@Override
	public @NonNull CompleteOCLLocalContext createNestedContext(@NonNull CGElement cgScope) {
		return new CompleteOCLLocalContext(this, cgScope);
	}

	@Override
	public @Nullable CompleteOCLLocalContext getLocalContext(@NonNull CGElement cgElement) {
		return (CompleteOCLLocalContext)super.getLocalContext(cgElement);
	}

	/*	public @NonNull CGParameter createSelfParameter(@NonNull Variable contextVariable) {
		CGTextParameter cgTextParameter = CGModelFactory.eINSTANCE.createCGTextParameter();
		cgTextParameter.setName(contextVariable.getName());
		cgTextParameter.setValueName(getSelfName());
		cgTextParameter.setAst(contextVariable);
		cgTextParameter.setTextValue("this");
		cgTextParameter.setNonInvalid();
		cgTextParameter.setNonNull();
		cgTextParameter.setTypeId(analyzer.getTypeId(contextVariable.getTypeId()));
		return cgTextParameter;
	} */
}