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
package org.eclipse.ocl.examples.codegen.java.types;

import org.eclipse.emf.common.util.Enumerator;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.cgmodel.CGEcoreExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.java.JavaLocalContext;
import org.eclipse.ocl.examples.codegen.java.JavaStream;
import org.eclipse.ocl.pivot.ids.ElementId;
import org.eclipse.ocl.pivot.ids.EnumerationLiteralId;

/**
 * A FutureEnumerationValueDescriptor describes a yet-to-be-created Ecore enumeration type. It has a pivot ElementId, and EClassifier and a Java class name.
 * <p>
 * The Java class is only known by name; it is not yet loadable, since genmodel has not yet generated it.
 * <p>
 * There is no EClassifier available to perform type conformance checks since thie Java class name was provided as an instanceClassName.
 */
public class FutureEnumerationValueDescriptor extends BoxedValueDescriptor implements EcoreDescriptor
{
	protected final @NonNull EClassifier eClassifier;
	protected final @NonNull String className;

	public FutureEnumerationValueDescriptor(@NonNull ElementId elementId, @NonNull EClassifier eClassifier, @NonNull String className) {
		super(elementId, EnumerationLiteralId.class);
		this.eClassifier = eClassifier;
		this.className = className;
	}

	//	@Override
	//	public void append(@NonNull JavaStream js) {
	//		js.appendClassReference(null, className);
	//	}

	@Override
	public @NonNull Boolean appendEcoreStatements(@NonNull JavaStream js, @NonNull JavaLocalContext<@NonNull ?> localContext,
			@NonNull CGEcoreExp cgEcoreExp, @NonNull CGValuedElement boxedValue) {
		EClassifier eClassifier = cgEcoreExp.getEClassifier();
		Class<?> ecoreClass = eClassifier != null ? eClassifier.getInstanceClass() : null;
		EcoreDescriptor ecoreDescriptor = getEcoreDescriptor(js.getCodeGenerator(), ecoreClass);
	//	return ecoreDescriptor.appendEcore(js, localContext, cgEcoreExp, boxedValue);




		js.appendDeclaration(cgEcoreExp);
		js.append(" = (");
		js.appendClassReference(null, className);
		js.append(")");
		js.appendReferenceTo(localContext.getIdResolverVariable(cgEcoreExp));
		js.append(".ecoreValueOf(");
		js.appendClassReference(null, Enumerator.class);
		js.append(".class, ");
		js.appendValueName(boxedValue);
		js.append(");\n");
		return true;
	}

	@Override
	public @NonNull Boolean appendEcore(@NonNull JavaStream js, @NonNull JavaLocalContext<@NonNull ?> localContext,
			@NonNull CGEcoreExp cgEcoreExp, @NonNull CGValuedElement unboxedValue) {
//		return super.appendEcore(js, localContext, cgEcoreExp, unboxedValue);
		js.appendDeclaration(cgEcoreExp);
		js.append(" = (");
		js.appendClassReference(null, className);
		js.append(")");
		js.appendReferenceTo(localContext.getIdResolverVariable(cgEcoreExp));
		js.append(".ecoreValueOf(");
		js.appendClassReference(null, Enumerator.class);
		js.append(".class, ");
		js.appendValueName(unboxedValue);
		js.append(");\n");
		return true;
	}

	@Override
	public void appendEqualsValue(@NonNull JavaStream js, @NonNull CGValuedElement thisValue,
			@NonNull CGValuedElement thatValue, boolean notEquals) {
		js.appendValueName(thisValue);
		js.append(notEquals ? " != " : " == ");
		js.appendValueName(thatValue);
	}

	@Override
	protected @NonNull EcoreDescriptor createEcoreDescriptor() {
		return new FutureEnumerationObjectDescriptor(elementId, className);
	}

	@Override
	protected @NonNull UnboxedDescriptor createUnboxedDescriptor() {
		return new FutureEnumerationObjectDescriptor(elementId, className);
	}

	@Override
	@NonNull
	public String getClassName() {
		return className;
	}

	@Override
	public @Nullable EClassifier getEClassifier() {
		return eClassifier;
	}

	//	@Override
	//	public @NonNull EcoreDescriptor getEcoreDescriptor(@NonNull CodeGenerator codeGenerator, @Nullable Class<?> instanceClass) {
	//		return this;
	//	}

	@Override
	public @NonNull Class<?> getJavaClass() {
		return NamedFuture.class;
	}

	//	@Override
	//	public @NonNull UnboxedDescriptor getUnboxedDescriptor(@NonNull CodeGenerator codeGenerator) {
	//		return this;
	//	}

	@Override
	public @Nullable Class<?> hasJavaClass() {
		return null;
	}

	//	@Override
	//	public final boolean isAssignableFrom(@NonNull TypeDescriptor typeDescriptor) {
	//		return typeDescriptor == this;
	//	}
}
