/*******************************************************************************
 * Copyright (c) 2021 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.utilities;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.internal.resource.ASResourceFactoryRegistry;
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal;
import org.eclipse.ocl.pivot.internal.utilities.OCLInternal;
import org.eclipse.ocl.pivot.resource.ProjectManager;
import org.eclipse.ocl.pivot.utilities.AbstractEnvironmentThread.AbstractEnvironmentThreadFactory;

/**
 * PivotEnvironmentThreadFactory transports the construction requirements from an invoking context to
 * the PivotEnvironmentThread on which the OCL processing is performed.
 *
 * @since 1.15
 */
public class PivotEnvironmentThreadFactory extends AbstractEnvironmentThreadFactory<@NonNull EnvironmentFactoryInternal>
	{
		public PivotEnvironmentThreadFactory(@NonNull ProjectManager projectManager) {
			super(projectManager);
		}

		@Override
		public @NonNull EnvironmentFactoryInternal createEnvironmentFactory() {
			return ASResourceFactoryRegistry.INSTANCE.createEnvironmentFactory(projectManager, null);	// XXX cast
		}

		@Override
		public @NonNull OCLInternal createEnvironment(@NonNull EnvironmentFactoryInternal environmentFactory) {
			return environmentFactory.createOCL();
		}
	}