/*******************************************************************************
 * Copyright (c) 2012, 2025 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.internal.scoping;

import org.eclipse.ocl.pivot.PivotPackage;
import org.eclipse.ocl.pivot.internal.attributes.ClassAttribution;
import org.eclipse.ocl.pivot.internal.attributes.DataTypeAttribution;
import org.eclipse.ocl.pivot.internal.attributes.EnumerationAttribution;
import org.eclipse.ocl.pivot.internal.attributes.ExpressionInOCLAttribution;
import org.eclipse.ocl.pivot.internal.attributes.IterateExpAttribution;
import org.eclipse.ocl.pivot.internal.attributes.IteratorExpAttribution;
import org.eclipse.ocl.pivot.internal.attributes.LetExpAttribution;
import org.eclipse.ocl.pivot.internal.attributes.LibraryAttribution;
import org.eclipse.ocl.pivot.internal.attributes.ModelAttribution;
import org.eclipse.ocl.pivot.internal.attributes.OperationAttribution;
import org.eclipse.ocl.pivot.internal.attributes.OperationCallExpAttribution;
import org.eclipse.ocl.pivot.internal.attributes.PackageAttribution;
import org.eclipse.ocl.pivot.internal.attributes.PropertyAttribution;
import org.eclipse.ocl.pivot.internal.attributes.TemplateParameterAttribution;
import org.eclipse.ocl.pivot.internal.attributes.VariableAttribution;
import org.eclipse.ocl.pivot.internal.attributes.VoidTypeAttribution;
import org.eclipse.ocl.pivot.internal.scoping.Attribution.AttributionRegistryInstaller;

public class PivotScoping
{
	public static void init() {
		AttributionRegistryInstaller registryInstaller = Attribution.REGISTRY.getInstaller(PivotScoping.class);
		registryInstaller.install(PivotPackage.Literals.CLASS, ClassAttribution.INSTANCE);
		registryInstaller.install(PivotPackage.Literals.DATA_TYPE, DataTypeAttribution.INSTANCE);
		registryInstaller.install(PivotPackage.Literals.ELEMENT, EmptyAttribution.INSTANCE);
		registryInstaller.install(PivotPackage.Literals.ENUMERATION, EnumerationAttribution.INSTANCE);
		registryInstaller.install(PivotPackage.Literals.EXPRESSION_IN_OCL, ExpressionInOCLAttribution.INSTANCE);
		registryInstaller.install(PivotPackage.Literals.INVALID_TYPE, VoidTypeAttribution.INSTANCE);
		registryInstaller.install(PivotPackage.Literals.ITERATE_EXP, IterateExpAttribution.INSTANCE);
		registryInstaller.install(PivotPackage.Literals.ITERATOR_EXP, IteratorExpAttribution.INSTANCE);
		registryInstaller.install(PivotPackage.Literals.LAMBDA_TYPE, EmptyAttribution.INSTANCE);
		registryInstaller.install(PivotPackage.Literals.LET_EXP, LetExpAttribution.INSTANCE);
		registryInstaller.install(PivotPackage.Literals.LIBRARY, LibraryAttribution.INSTANCE);
		registryInstaller.install(PivotPackage.Literals.MODEL, ModelAttribution.INSTANCE);
		registryInstaller.install(PivotPackage.Literals.OPERATION, OperationAttribution.INSTANCE);
		registryInstaller.install(PivotPackage.Literals.OPERATION_CALL_EXP, OperationCallExpAttribution.INSTANCE);
		registryInstaller.install(PivotPackage.Literals.PACKAGE, PackageAttribution.INSTANCE);
		registryInstaller.install(PivotPackage.Literals.PROPERTY, PropertyAttribution.INSTANCE);
		registryInstaller.install(PivotPackage.Literals.TEMPLATE_PARAMETER, TemplateParameterAttribution.INSTANCE);
		registryInstaller.install(PivotPackage.Literals.VARIABLE, VariableAttribution.INSTANCE);
		registryInstaller.install(PivotPackage.Literals.VOID_TYPE, VoidTypeAttribution.INSTANCE);
	}
}
