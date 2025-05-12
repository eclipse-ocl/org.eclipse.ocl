/*******************************************************************************
 * Copyright (c) 2010, 2025 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/

package org.eclipse.ocl.pivot.internal.utilities;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.internal.complete.CompleteEnvironmentInternal;
import org.eclipse.ocl.pivot.internal.complete.CompleteModelInternal;
import org.eclipse.ocl.pivot.internal.complete.StandardLibraryInternal;
import org.eclipse.ocl.pivot.utilities.AbstractEnvironmentFactory;
import org.eclipse.ocl.pivot.utilities.EnvironmentFactory;

/**
 * A factory for creating OCL parser and evaluation artefacts.  Clients of the OCL
 * parser that wish to use OCL with their metamodels can provide the parser
 * a factory that creates the suitable environments.  The environment provides
 * mappings from the client's metamodel to the UML concepts required by the
 * parser (corresponding to the generic type parameters, below).  Many of these
 * mappings are optional (e.g., state machines, signals, and association
 * classes aren't supported by all metamodels).
 * <p>
 * This interface is <b>not</b> intended to be implemented to be implemented
 * "directly" by providers of metamodel bindings.
 * It is highly recommended to extend the {@link AbstractEnvironmentFactory}
 * class, instead.
 * </p>
 */
public interface EnvironmentFactoryInternal extends EnvironmentFactory
{
	@Override
	@NonNull OCLInternal createOCL();

	@Override
	@NonNull CompleteEnvironmentInternal getCompleteEnvironment();

	@Override
	@NonNull CompleteModelInternal getCompleteModel();

//	@Override
//	@NonNull PivotMetamodelManager getMetamodelManager();

	@Override
	@NonNull StandardLibraryInternal getStandardLibrary();
}
