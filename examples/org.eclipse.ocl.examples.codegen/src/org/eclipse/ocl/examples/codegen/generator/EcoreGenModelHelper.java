/*******************************************************************************
 * Copyright (c) 2021 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.codegen.generator;

import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.internal.manager.PivotMetamodelManager;

public class EcoreGenModelHelper extends AbstractGenModelHelper
{
	public EcoreGenModelHelper(@NonNull PivotMetamodelManager metamodelManager) {
		super(metamodelManager);
	}

	@Override
	public @NonNull String getName(@Nullable ENamedElement eNamedElement) {
		if (eNamedElement == null) {
			return "";
		}
		String name = eNamedElement.getName();
		if (name == null) {
			name = "";
		}
		return name;
	}
}
