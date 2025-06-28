/*******************************************************************************
 * Copyright (c) 2010, 2025 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.xtext.base.scoping;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.xtext.scoping.IScope;
import org.eclipse.xtext.scoping.impl.AbstractScope;

/**
 * An AbstractJavaClassScope provides the abstract support for ClassName lookup in the base plugin. The real
 * lookup is only appropriate for OCLstdlib where the derived JavaClassScope resides.
 */
public abstract class AbstractJavaClassScope extends AbstractScope
{
	protected AbstractJavaClassScope() {
		super(IScope.NULLSCOPE, false);
	}

	public abstract void addClassLoaders(@NonNull Iterable<@NonNull ClassLoader> classLoaders);

	@Override
	public String toString() {
		return getClass().getSimpleName();
	}
}
