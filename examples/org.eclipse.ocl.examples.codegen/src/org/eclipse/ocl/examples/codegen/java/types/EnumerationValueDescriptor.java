/*******************************************************************************
 * Copyright (c) 2014, 2019 CEA LIST and others.
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
import org.eclipse.ocl.examples.codegen.cgmodel.CGUnboxExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.generator.TypeDescriptor;
import org.eclipse.ocl.examples.codegen.java.JavaLocalContext;
import org.eclipse.ocl.examples.codegen.java.JavaStream;
import org.eclipse.ocl.pivot.ids.ElementId;
import org.eclipse.ocl.pivot.ids.EnumerationLiteralId;

/**
 * EnumerationValueDescriptor describes the boxed representation of the instances of an Enumeration, these are EnumerationLiteraIds.
 */
public class EnumerationValueDescriptor extends BoxedValueDescriptor //implements EcoreDescriptor
{
	protected final @NonNull EClassifier eClassifier;
	protected final @NonNull Class<?> ecoreJavaClass;

	public EnumerationValueDescriptor(@NonNull ElementId elementId, @NonNull EClassifier eClassifier, @NonNull Class<?> ecoreJavaClass) {
		super(elementId, EnumerationLiteralId.class);
		this.eClassifier = eClassifier;
		this.ecoreJavaClass = ecoreJavaClass;
	}

	@Override
	public @NonNull Boolean appendEcoreStatements(@NonNull JavaStream js, @NonNull JavaLocalContext<@NonNull ?> localContext,
			@NonNull CGEcoreExp cgEcoreExp, @NonNull CGValuedElement boxedValue) {
		js.appendSuppressWarningsNull(true);
		js.appendDeclaration(cgEcoreExp);
		js.append(" = (");
		js.appendClassReference(true, ecoreJavaClass);
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
	public void appendEqualsValue(@NonNull JavaStream js, @NonNull CGValuedElement thisValue,
			@NonNull CGValuedElement thatValue, boolean notEquals) {
		TypeDescriptor thatTypeDescriptor = js.getCodeGenerator().getTypeDescriptor(thatValue);
		if (thatTypeDescriptor.getEClassifier() != eClassifier) {
			js.appendBooleanString(false);
		}
		else {
			js.appendValueName(thisValue);
			js.append(notEquals ? " != " : " == ");
			js.appendValueName(thatValue);
		}
	}

	@Override
	public @NonNull Boolean appendUnboxStatements(@NonNull JavaStream js, @NonNull JavaLocalContext<@NonNull ?> localContext,
			@NonNull CGUnboxExp cgUnboxExp, @NonNull CGValuedElement boxedValue) {
		js.appendDeclaration(cgUnboxExp);
		js.append(" = ");
		js.appendReferenceTo(localContext.getIdResolverVariable(cgUnboxExp));
		js.append(".unboxedValueOf(");
		js.appendValueName(boxedValue);
		js.append(");\n");
		return true;
	}

	@Override
	protected @NonNull EcoreDescriptor createEcoreDescriptor() {
		return new EnumerationObjectDescriptor(elementId, ecoreJavaClass);
	}

	@Override
	protected @NonNull UnboxedDescriptor createUnboxedDescriptor() {
		return new EnumerationObjectDescriptor(elementId, ecoreJavaClass);
	}

	@Override
	public @Nullable EClassifier getEClassifier() {
		return eClassifier;
	}
}
