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
 * A RootObjectDescriptor describes the java.lang.Object type when that is precisely the type in use, as opposed to the usage of Object as the pragmatic
 * usage when a Java class cannot be determined.
 */
public class RootObjectDescriptor extends AbstractDescriptor implements SimpleDescriptor
{
	public RootObjectDescriptor(@NonNull ElementId elementId) {
		super(elementId);
	}

	@Override
	public void append(@NonNull JavaStream javaStream, @Nullable Boolean isRequired) {
		javaStream.appendClassReference(isRequired, Object.class);
	}

	@Override
	public @NonNull Boolean appendEcore(@NonNull JavaStream js, @NonNull JavaLocalContext<@NonNull ?> localContext, @NonNull CGEcoreExp cgEcoreExp, @NonNull CGValuedElement unboxedValue) {
		return appendEcoreLegacy(js, localContext, cgEcoreExp, unboxedValue);
	}

	@Override
	public @NonNull String getClassName() {
		return Object.class.getName();
	}

	@Override
	public @NonNull EcoreDescriptor getEcoreDescriptor(@NonNull CodeGenerator codeGenerator, @Nullable Class<?> instanceClass) {
		return this;
	}

	@Override
	public @NonNull Class<?> getJavaClass() {
		return Object.class;
	}

	@Override
	public @NonNull UnboxedDescriptor getUnboxedDescriptor(@NonNull CodeGenerator codeGenerator) {
		return this;
	}

	@Override
	public @Nullable Class<?> hasJavaClass() {
		return Object.class;
	}

	@Override
	public boolean isAssignableFrom(@NonNull TypeDescriptor typeDescriptor) {
		return true;
	}
}
