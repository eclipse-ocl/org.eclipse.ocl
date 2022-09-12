/*******************************************************************************
 * Copyright (c) 2022 Willink Transformation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.codegen.naming;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.examples.codegen.cgmodel.CGProperty;
import org.eclipse.ocl.pivot.Property;

/**
 * A PropertyNameManager supervises the variable names allocated within the scope of a Property.
 */
public class PropertyNameManager extends FeatureNameManager
{
	public PropertyNameManager(@NonNull ClassNameManager classNameManager, @NonNull CGProperty cgProperty) {
		super(classNameManager, classNameManager, cgProperty);
	}

	public @NonNull Property getASProperty() {
		return (Property)asScope;
	}

	public @NonNull CGProperty getCGProperty() {
		return (CGProperty)cgScope;
	}
}
