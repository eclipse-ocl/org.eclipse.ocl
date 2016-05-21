/*******************************************************************************
 * Copyright (c) 2010, 2016 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.xtext.base.as2cs;

import java.util.Map;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.Namespace;
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal;
import org.eclipse.ocl.pivot.resource.ASResource;
import org.eclipse.ocl.xtext.base.utilities.BaseCSResource;

public class BaseAS2CS extends AS2CS
{	
	private static final class Factory implements AS2CS.Factory
	{
		private static AS2CS.@NonNull Factory INSTANCE = new Factory();

		@Override
		public @NonNull BaseDeclarationVisitor createDeclarationVisitor(@NonNull AS2CSConversion converter) {
			return new BaseDeclarationVisitor(converter);
		}

		@Override
		public @NonNull BaseReferenceVisitor createReferenceVisitor(@NonNull AS2CSConversion converter, @Nullable Namespace scope) {
			return new BaseReferenceVisitor(converter);
		}

		@Override
		public @NonNull EClass @NonNull [] getEClasses() {
			return new @NonNull EClass @NonNull [] {};
		}
	}

	public BaseAS2CS(@NonNull Map<? extends BaseCSResource, ? extends ASResource> cs2asResourceMap,
			@NonNull EnvironmentFactoryInternal environmentFactory) {
		super(cs2asResourceMap, environmentFactory);
		addFactory(Factory.INSTANCE);
	}
}