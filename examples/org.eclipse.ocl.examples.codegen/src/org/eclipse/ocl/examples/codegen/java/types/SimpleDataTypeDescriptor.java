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
import org.eclipse.ocl.examples.codegen.java.JavaStream;
import org.eclipse.ocl.examples.codegen.naming.ExecutableNameManager;
import org.eclipse.ocl.pivot.ids.ElementId;

/**
 * A SimpleDataTypeDescriptor describes a data type whose boxed and unboxed representations are the same. It has a pivot ElementId and a Java class name.
 * <p>
 * The Java class is only known by name; it is not yet loadable, since genmodel has not yet generated it.
 * <p>
 * There is no EClassifier available to perform type conformance checks since the Java class name was provided as an instanceClassName.
 */
public class SimpleDataTypeDescriptor extends AbstractDescriptor implements SimpleDescriptor
{
	private static class NamedFuture {}

	protected final @NonNull String fullyQualifiedClassName;

	public SimpleDataTypeDescriptor(@NonNull ElementId elementId, @NonNull String fullyQualifiedClassName) {
		super(elementId);
		this.fullyQualifiedClassName = fullyQualifiedClassName;
		assert fullyQualifiedClassName.contains(".");
	}

	@Override
	public void append(@NonNull JavaStream javaStream, @Nullable Boolean isRequired) {
		javaStream.appendClassReference(isRequired, fullyQualifiedClassName);
	}

	@Override
	public @NonNull Boolean appendEcore(@NonNull JavaStream js, @NonNull ExecutableNameManager localNameManager, @NonNull CGEcoreExp cgEcoreExp, @NonNull CGValuedElement unboxedValue) {
		return appendEcoreLegacy(js, localNameManager, cgEcoreExp, unboxedValue);
	}

	@Override
	@NonNull
	public String getClassName() {
		return fullyQualifiedClassName;
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
		return typeDescriptor == this;
	}
}
