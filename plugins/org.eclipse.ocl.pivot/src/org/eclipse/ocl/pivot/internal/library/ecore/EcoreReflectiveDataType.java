/*******************************************************************************
 * Copyright (c) 2011, 2018 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.internal.library.ecore;

import org.eclipse.emf.ecore.EDataType;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.DataType;
import org.eclipse.ocl.pivot.TemplateParameter;

/**
 * @since 7.0
 */
public class EcoreReflectiveDataType extends EcoreReflectiveType implements DataType
{
	public EcoreReflectiveDataType(@NonNull EcoreReflectivePackage evaluationPackage, int flags, @NonNull EDataType eEnum, @NonNull TemplateParameter @NonNull ... typeParameters) {
		super(evaluationPackage, flags, eEnum, typeParameters);
	}

	/**
	 * @since 1.3
	 */
	@Override
	public String getValue() {
		throw new UnsupportedOperationException();
	}
}
