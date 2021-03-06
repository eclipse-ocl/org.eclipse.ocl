/*******************************************************************************
 * Copyright (c) 2015, 2021 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.codegen.java.types;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.examples.codegen.cgmodel.CGEcoreExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.generator.TypeDescriptor;
import org.eclipse.ocl.examples.codegen.java.JavaLocalContext;
import org.eclipse.ocl.examples.codegen.java.JavaStream;

/**
 * An EcoreDescriptor defines the description of a type that may be used where an Ecore representation is required.
 */
public interface EcoreDescriptor extends TypeDescriptor
{
	/**
	 * Append a statement to create the cgEcoreExp value by converting unboxedValue as specified by this EcoreDescriptor.
	 */
	@NonNull Boolean appendEcore(@NonNull JavaStream js, @NonNull JavaLocalContext<@NonNull ?> localContext,
			@NonNull CGEcoreExp cgEcoreExp, @NonNull CGValuedElement unboxedValue);

	/**
	 * Return the basic Java class for this descriptor. e.g. Integer for a primitive int.
	 */
	@NonNull Class<?> getNonPrimitiveJavaClass();
}