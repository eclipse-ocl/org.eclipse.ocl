/*******************************************************************************
 * Copyright (c) 2010, 2025 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *     Adolfo Sanchez-Barbudo Herrera (University of York) - Bug 397429
 *******************************************************************************/
package org.eclipse.ocl.xtext.base.cs2as;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.resource.ASResource;
import org.eclipse.ocl.pivot.resource.CSResource;
import org.eclipse.ocl.pivot.utilities.EnvironmentFactory;
import org.eclipse.ocl.xtext.base.scoping.JavaClassScope;
import org.eclipse.ocl.xtext.basecs.JavaClassCS;
import org.eclipse.ocl.xtext.basecs.util.BaseCSVisitor;

/**
 * BaseCS2AS extends the CS to AS conversion managed by CS2AS to support creation of the CS by Xtext parsing.
 */
public class BaseCS2AS extends CS2AS
{
	private @NonNull JavaClassScope javaClassScope;

	public BaseCS2AS(@NonNull EnvironmentFactory environmentFactory, @NonNull CSResource csResource, @NonNull ASResource asResource) {
		super(environmentFactory, csResource, asResource);
		Iterable<@NonNull ClassLoader> classLoaders = environmentFactory.getMetamodelManager().getImplementationManager().getClassLoaders();
		javaClassScope = new JavaClassScope(classLoaders);
	}

	public BaseCS2AS(@NonNull BaseCS2AS cs2as) {
		super(cs2as);
		javaClassScope = cs2as.javaClassScope;
	}

	@Override
	protected @NonNull BaseCSVisitor<Continuation<?>> createContainmentVisitor(@NonNull CS2ASConversion converter) {
		return new BaseCSContainmentVisitor(converter);
	}

	@Override
	protected @NonNull BaseCSVisitor<Element> createLeft2RightVisitor(@NonNull CS2ASConversion converter) {
		return new BaseCSLeft2RightVisitor(converter);
	}

	@Override
	protected @NonNull BaseCSVisitor<Continuation<?>> createPostOrderVisitor(@NonNull CS2ASConversion converter) {
		return new BaseCSPostOrderVisitor(converter);
	}

	@Override
	protected @NonNull BaseCSVisitor<Continuation<?>> createPreOrderVisitor(@NonNull CS2ASConversion converter) {
		return new BaseCSPreOrderVisitor(converter);
	}

	public @NonNull JavaClassCS getJavaClassCS(@NonNull String name) {
		return javaClassScope.getJavaClassCS(name);
	}

	public @NonNull JavaClassScope getJavaClassScope() {
		return javaClassScope;
	}
}
