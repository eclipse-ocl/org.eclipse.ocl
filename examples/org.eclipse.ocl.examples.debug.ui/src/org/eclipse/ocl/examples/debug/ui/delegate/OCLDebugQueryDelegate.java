/*******************************************************************************
 * Copyright (c) 2014, 2018 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * 
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.debug.ui.delegate;

import java.util.Map;

import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.internal.delegate.OCLDelegateDomain;
import org.eclipse.ocl.pivot.internal.delegate.OCLQueryDelegate;
import org.eclipse.ocl.pivot.utilities.Query;

/**
 * An implementation of a query delegate for OCL expressions.
 */
public class OCLDebugQueryDelegate extends OCLQueryDelegate
{
	public OCLDebugQueryDelegate(@NonNull OCLDelegateDomain delegateDomain, @NonNull EClassifier context, @Nullable Map<String, EClassifier> parameters, @NonNull String expression) {
		super(delegateDomain, context, parameters, expression);
	}

	protected@Nullable Object evaluateEcore(@NonNull Query query, @Nullable Object target) {
		return query.evaluateEcore(target);
	}
}
