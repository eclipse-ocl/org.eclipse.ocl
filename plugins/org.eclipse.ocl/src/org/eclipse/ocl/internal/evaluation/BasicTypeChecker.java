/*******************************************************************************
 * Copyright (c) 2006, 2018 IBM and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.internal.evaluation;

import org.eclipse.emf.common.util.EList;
import org.eclipse.ocl.AbstractTypeChecker;
import org.eclipse.ocl.Environment;
import org.eclipse.ocl.expressions.CollectionKind;
import org.eclipse.ocl.types.CollectionType;
import org.eclipse.ocl.types.TupleType;
import org.eclipse.ocl.utilities.TypedElement;

public class BasicTypeChecker<C, O, P, PM> extends AbstractTypeChecker<C, O, P, PM>
{
	public BasicTypeChecker(Environment<?, C, O, P, ?, PM, ?, ?, ?, ?, ?, ?> env) {
		super(env);
	}

	@Override
	protected C resolve(C type) {
		return getEnvironment().getTypeResolver().resolve(type);
	}

	@Override
	protected CollectionType<C, O> resolveCollectionType(CollectionKind kind, C elementType) {	
		return getEnvironment().getTypeResolver().resolveCollectionType(kind, elementType);
	}

	@Override
	protected TupleType<O, P> resolveTupleType(EList<? extends TypedElement<C>> parts) {		
		return getEnvironment().getTypeResolver().resolveTupleType(parts);
	}
}