/*******************************************************************************
 * Copyright (c) 2012, 2015 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.xtext.base.utilities;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.internal.scoping.EnvironmentView;
import org.eclipse.ocl.pivot.internal.scoping.ScopeView;
import org.eclipse.ocl.pivot.resource.ASResource;
import org.eclipse.ocl.pivot.utilities.ParserContext;
import org.eclipse.ocl.xtext.base.cs2as.CS2AS;

/**
 * An ExtendedParserContext captures the context in which source text is parsed with knowledge of the Concrete SYntax classes
 * that are in use.
 *
 * A derived context is constructed with the relevant context, then createBaseResource
 * creates a Concrete Syntax resource for a Concrete Syntax expression string. Then parse creates
 * a corresponding Abstract Syntax ExpressionInOCL. initialize is invoked during the parse to
 * install the derived context into the ExpressionInOCL.
 *
 * @noimplement This interface is not intended to be implemented by clients.
 */
public interface ExtendedParserContext extends ParserContext
{
	/**
	 * Add the local lookup contributions to a view of an Environment.
	 * <p>
	 * The EnvironmentView contains the lookup matching criteria such as a specific name and
	 * accumulates candidate results.
	 * <p>
	 * The input ScopeView identifies the CS node and the containment of the CS child from which
	 * the lookup is made allowing derived implementations to present the alternative environments
	 * specified as the <i>Inherited Attributes</i> in the OCL Specification.
	 * <p>
	 * The returned ScopeView identifies an outer scope in which the lookup may be continued if the
	 * local candidate results are not suitable.
	 *
	 * @param environmentView the EnvironmentView to compute
	 * @param scopeView the access selectivity to be applied by the lookup
	 * @return an outer ScopeView in which to continue the lookup, or null if none
	 */
	@Nullable ScopeView computeLookup(@NonNull EObject target, @NonNull EnvironmentView environmentView, @NonNull ScopeView scopeView);

	/**
	 * Create and return a CS2AS to orchestrate the conversion of csResource to asResource.
	 * If a null CS2AS is returned, the default CS2AS appropriate to the csResource should be created by the caller.
	 */
	@Nullable CS2AS createCS2AS(@NonNull BaseCSResource csResource, @NonNull ASResource asResource);
}
