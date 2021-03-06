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

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.ETypeParameter;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.cgmodel.CGEcoreExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.generator.CodeGenerator;
import org.eclipse.ocl.examples.codegen.generator.TypeDescriptor;
import org.eclipse.ocl.examples.codegen.java.JavaLocalContext;
import org.eclipse.ocl.examples.codegen.java.JavaStream;
import org.eclipse.ocl.pivot.ids.ElementId;

/**
 * A FutureEObjectDescriptor describes a yet-to-be-created type whose boxed and unboxed representations are the same. It has a pivot ElementId, and EClassifier and a Java class name.
 * <p>
 * The Java class is only known by name; it is not yet loadable, since genmodel has not yet generated it.
 * <p>
 * There is no EClassifier available to perform type conformance checks since thie Java class name was provided as an instanceClassName.
 */
public class FutureEObjectDescriptor extends AbstractDescriptor implements SimpleDescriptor
{
	protected final @NonNull EClassifier eClassifier;
	protected final @NonNull String className;

	public FutureEObjectDescriptor(@NonNull ElementId elementId, @NonNull EClassifier eClassifier, @NonNull String className) {
		super(elementId);
		this.eClassifier = eClassifier;
		this.className = className;
	}

	@Override
	public void append(@NonNull JavaStream js, @Nullable Boolean isRequired) {
		js.appendClassReference(isRequired, className);
		List<ETypeParameter> eTypeParameters = eClassifier.getETypeParameters();
		if ((eTypeParameters != null) && (eTypeParameters.size() > 0)) {
			js.append("<");
			for (int i = 0; i < eTypeParameters.size(); i++) {
				if (i != 0) {
					js.append(",");
				}
				js.append("?");
			}
			js.append(">");
		}
	}

	@Override
	public @NonNull Boolean appendEcore(@NonNull JavaStream js, @NonNull JavaLocalContext<@NonNull ?> localContext, @NonNull CGEcoreExp cgEcoreExp, @NonNull CGValuedElement unboxedValue) {
		return appendEcoreLegacy(js, localContext, cgEcoreExp, unboxedValue);
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

	@Override
	public @NonNull EcoreDescriptor getEcoreDescriptor(@NonNull CodeGenerator codeGenerator, @Nullable Class<?> instanceClass) {
		return this;
	}

	@Override
	public @NonNull Class<?> getJavaClass() {
		return NamedFuture.class;
	}

	@Override
	public @NonNull UnboxedDescriptor getUnboxedDescriptor(@NonNull CodeGenerator codeGenerator) {
		return this;
	}

	@Override
	public @Nullable Class<?> hasJavaClass() {
		return null;
	}

	@Override
	public final boolean isAssignableFrom(@NonNull TypeDescriptor typeDescriptor) {
		if (typeDescriptor == this) {
			return true;
		}
		EClassifier thisEClassifier = this.getEClassifier();
		EClassifier thatEClassifier = typeDescriptor.getEClassifier();
		if ((thisEClassifier instanceof EClass) && (thatEClassifier instanceof EClass)) {
			return ((EClass)thisEClassifier).isSuperTypeOf((EClass)thatEClassifier);
		}
		return false;
	}
}
