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
import org.eclipse.ocl.examples.codegen.cgmodel.CGConstraint;
import org.eclipse.ocl.examples.codegen.cgmodel.CGIterationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGOperation;
import org.eclipse.ocl.examples.codegen.cgmodel.CGParameter;
import org.eclipse.ocl.examples.codegen.cgmodel.CGProperty;
import org.eclipse.ocl.examples.codegen.naming.ClassNameManager;
import org.eclipse.ocl.examples.codegen.naming.FeatureNameManager;
import org.eclipse.ocl.pivot.VariableDeclaration;

/**
 * OCLinEcoreFeatureNameManager provides OCLinEcore-specific overrides for nested contexts.
 */
public class OCLinEcoreFeatureNameManager extends FeatureNameManager
{
	public OCLinEcoreFeatureNameManager(@NonNull ClassNameManager classNameManager, @NonNull CGConstraint cgConstraint) {
		super(classNameManager, cgConstraint);
	}

	public OCLinEcoreFeatureNameManager(@NonNull ClassNameManager classNameManager, @NonNull FeatureNameManager parent, @NonNull CGIterationCallExp cgIterationCallExp) {
		super(classNameManager, parent, cgIterationCallExp);
	}

	public OCLinEcoreFeatureNameManager(@NonNull ClassNameManager classNameManager, @NonNull CGOperation cgOperation) {
		super(classNameManager, cgOperation);
	}

	public OCLinEcoreFeatureNameManager(@NonNull ClassNameManager classNameManager, @NonNull CGProperty cgProperty) {
		super(classNameManager, cgProperty);
	}

	@Override
	public @NonNull CGParameter getSelfParameter(@NonNull VariableDeclaration aParameter) {			// XXX Avoid need for override - redirect via CodeGenerator polymorphism
		CGParameter cgParameter = super.getThisParameter(aParameter);
	//	assert (PivotConstants.SELF_NAME.equals(aParameter.getName())) {
	//	globalContext.getThisNameResolution().addSecondaryElement(cgParameter);
	//	globalContext.getSelfNameResolution().addSecondaryElement(cgParameter);
		//	cgParameter.setValueName(JavaConstants.THIS_NAME);
	//	}
		return cgParameter;
	}
}