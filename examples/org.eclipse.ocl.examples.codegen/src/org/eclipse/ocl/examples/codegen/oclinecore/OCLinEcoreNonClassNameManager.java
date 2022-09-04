/*******************************************************************************
 * Copyright (c) 2022 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.codegen.oclinecore;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.examples.codegen.analyzer.NameManager;
import org.eclipse.ocl.examples.codegen.analyzer.NonClassNameManager;
import org.eclipse.ocl.examples.codegen.cgmodel.CGNamedElement;
import org.eclipse.ocl.examples.codegen.cgmodel.CGParameter;
import org.eclipse.ocl.examples.codegen.java.JavaCodeGenerator;
import org.eclipse.ocl.pivot.VariableDeclaration;

/**
 * OCLinEcoreNestedNameManager provides OCLinEcore-specific overrides for nested contexts.
 */
public class OCLinEcoreNonClassNameManager extends NonClassNameManager
{
	public OCLinEcoreNonClassNameManager(@NonNull JavaCodeGenerator codeGenerator, @NonNull NameManager parent, @NonNull CGNamedElement cgScope) {
		super(codeGenerator, parent, cgScope);
	}

	@Override
	public @NonNull CGParameter getSelfParameter(@NonNull VariableDeclaration aParameter) {
		CGParameter cgParameter = super.getThisParameter(aParameter);
	//	assert (PivotConstants.SELF_NAME.equals(aParameter.getName())) {
	//	globalContext.getThisNameResolution().addSecondaryElement(cgParameter);
	//	globalContext.getSelfNameResolution().addSecondaryElement(cgParameter);
		//	cgParameter.setValueName(JavaConstants.THIS_NAME);
	//	}
		return cgParameter;
	}
}