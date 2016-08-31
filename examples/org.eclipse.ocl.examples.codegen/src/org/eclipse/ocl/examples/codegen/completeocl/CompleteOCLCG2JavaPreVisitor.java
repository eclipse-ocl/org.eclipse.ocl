/*******************************************************************************
 * Copyright (c) 2013, 2016 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.codegen.completeocl;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.examples.codegen.java.CG2JavaPreVisitor;

public class CompleteOCLCG2JavaPreVisitor extends CG2JavaPreVisitor
{
	public CompleteOCLCG2JavaPreVisitor(@NonNull CompleteOCLGlobalContext javaContext) {
		super(javaContext);
	}

	//	@Override
	//	protected @Nullable CGValuedElement installIdResolverVariable(@NonNull CGValuedElement cgValuedElement) {
	//		return localContext.getIdResolverVariable(cgValuedElement);
	//	}
}
