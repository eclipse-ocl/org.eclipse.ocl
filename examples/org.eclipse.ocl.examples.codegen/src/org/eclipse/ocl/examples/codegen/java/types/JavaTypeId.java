/*******************************************************************************
 * Copyright (c) 2013, 2018 CEA LIST and others.
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
import org.eclipse.ocl.pivot.ids.IdVisitor;
import org.eclipse.ocl.pivot.ids.PrimitiveTypeId;
import org.eclipse.ocl.pivot.internal.ids.UnscopedId;

/**
 * A JavaTypeId enables a Java class to be represented as a typeid singleton.
 */
public class JavaTypeId extends UnscopedId implements PrimitiveTypeId
{
	protected final @NonNull Class<?> javaClass;

	public JavaTypeId(@NonNull Class<?> javaClass) {
		super(javaClass.getName());
		this.javaClass = javaClass;
	}

	@Override
	public <R> R accept(@NonNull IdVisitor<R> visitor) {
		return visitor.visitPrimitiveTypeId(this);
	}

	public @NonNull Class<?> getJavaClass() {
		return javaClass;
	}

	@Override
	public @NonNull String getMetaTypeName() {
		return "JavaClass";
	}
}