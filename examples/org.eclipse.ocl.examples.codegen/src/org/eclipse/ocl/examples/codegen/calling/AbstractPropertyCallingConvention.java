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
import org.eclipse.ocl.examples.codegen.analyzer.AS2CGVisitor;
import org.eclipse.ocl.examples.codegen.analyzer.BoxingAnalyzer;
import org.eclipse.ocl.examples.codegen.cgmodel.CGBodiedProperty;
import org.eclipse.ocl.examples.codegen.cgmodel.CGModelFactory;
import org.eclipse.ocl.examples.codegen.cgmodel.CGNavigationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGProperty;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.java.CG2JavaVisitor;
import org.eclipse.ocl.examples.codegen.java.JavaLocalContext;
import org.eclipse.ocl.examples.codegen.java.JavaStream;
import org.eclipse.ocl.examples.codegen.utilities.CGUtil;
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.LanguageExpression;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.TupleType;
import org.eclipse.ocl.pivot.Variable;
import org.eclipse.ocl.pivot.ids.PropertyId;
import org.eclipse.ocl.pivot.utilities.ParserException;
import org.eclipse.ocl.pivot.utilities.ValueUtil;

/**
 *  AbstractPropertyCallingConvention defines the default support for a property declaration or call.
 */
public abstract class AbstractPropertyCallingConvention implements PropertyCallingConvention
{
	@Override
	public @NonNull CGProperty createCGProperty(@NonNull AS2CGVisitor as2cgVisitor, @NonNull Property asProperty) {
		return CGModelFactory.eINSTANCE.createCGConstrainedProperty();  // XXX Overrides may add state
	}

	@Override
	public void createImplementation(@NonNull AS2CGVisitor as2cgVisitor, @NonNull JavaLocalContext<?> localContext, @NonNull CGProperty cgProperty) {
	//	assert (this instanceof StereotypePropertyCallingConvention)
	//		|| (this instanceof ConstrainedPropertyCallingConvention)
	//		|| (this instanceof NativePropertyCallingConvention)
	//		|| (this instanceof ForeignPropertyCallingConvention);
		Property asProperty = CGUtil.getAST(cgProperty);
		cgProperty.setRequired(asProperty.isIsRequired());
		LanguageExpression specification = asProperty.getOwnedExpression();
		if (specification != null) {
			try {
				ExpressionInOCL query = as2cgVisitor.getEnvironmentFactory().parseSpecification(specification);
				Variable contextVariable = query.getOwnedContext();
				if (contextVariable != null) {
					as2cgVisitor.getSelfParameter(contextVariable);
				}
				((CGBodiedProperty)cgProperty).setBody(as2cgVisitor.doVisit(CGValuedElement.class, query.getOwnedBody()));
			} catch (ParserException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();		// XXX
			}
		}
	}

	@Override
	public boolean generateJavaDeclaration(@NonNull CG2JavaVisitor<?> cg2javaVisitor, @NonNull JavaStream js, @NonNull CGProperty cgProperty) {
		throw new UnsupportedOperationException("Missing/No support for " + getClass().getSimpleName() + ".generateJavaDeclaration");	// A number of Property Calling Conventions are call-only
	}

	@Override
	public void rewriteWithBoxingAndGuards(@NonNull BoxingAnalyzer boxingAnalyzer, @NonNull CGProperty cgProperty) {
	}

	@Override
	public void rewriteWithBoxingAndGuards(@NonNull BoxingAnalyzer boxingAnalyzer, @NonNull CGNavigationCallExp cgNavigationCallExp) {
		Property referredProperty = cgNavigationCallExp.getReferredProperty();
		String referredPropertyName;
		if (referredProperty == null) {
			referredPropertyName = "unknown";
		}
		else if (referredProperty.eContainer() instanceof TupleType) {
			referredPropertyName = referredProperty.getName();
		}
		else {
			PropertyId referredPropertyId = referredProperty.getPropertyId();
			referredPropertyName = ValueUtil.getElementIdName(referredPropertyId);
		}
		boxingAnalyzer.rewriteAsGuarded(cgNavigationCallExp.getSource(), boxingAnalyzer.isSafe(cgNavigationCallExp), "source for '" + referredPropertyName + "'");
	}

	@Override
	public @NonNull String toString() {
		return getClass().getSimpleName();
	}
}
