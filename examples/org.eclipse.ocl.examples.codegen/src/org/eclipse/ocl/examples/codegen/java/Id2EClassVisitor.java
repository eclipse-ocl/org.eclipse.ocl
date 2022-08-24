/*******************************************************************************
 * Copyright (c) 2013, 2018 CEA LIST and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink(CEA LIST) - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.codegen.java;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.ids.ClassId;
import org.eclipse.ocl.pivot.ids.CollectionTypeId;
import org.eclipse.ocl.pivot.ids.DataTypeId;
import org.eclipse.ocl.pivot.ids.ElementId;
import org.eclipse.ocl.pivot.ids.EnumerationId;
import org.eclipse.ocl.pivot.ids.EnumerationLiteralId;
import org.eclipse.ocl.pivot.ids.IdVisitor;
import org.eclipse.ocl.pivot.ids.LambdaTypeId;
import org.eclipse.ocl.pivot.ids.MapTypeId;
import org.eclipse.ocl.pivot.ids.NestedPackageId;
import org.eclipse.ocl.pivot.ids.NsURIPackageId;
import org.eclipse.ocl.pivot.ids.OclInvalidTypeId;
import org.eclipse.ocl.pivot.ids.OclVoidTypeId;
import org.eclipse.ocl.pivot.ids.OperationId;
import org.eclipse.ocl.pivot.ids.PrimitiveTypeId;
import org.eclipse.ocl.pivot.ids.PropertyId;
import org.eclipse.ocl.pivot.ids.RootPackageId;
import org.eclipse.ocl.pivot.ids.TemplateBinding;
import org.eclipse.ocl.pivot.ids.TemplateParameterId;
import org.eclipse.ocl.pivot.ids.TemplateableTypeId;
import org.eclipse.ocl.pivot.ids.TuplePartId;
import org.eclipse.ocl.pivot.ids.TupleTypeId;
import org.eclipse.ocl.pivot.ids.UnspecifiedId;
import org.eclipse.ocl.pivot.ids.WildcardId;
import org.eclipse.ocl.pivot.internal.manager.PivotMetamodelManager;

public class Id2EClassVisitor implements IdVisitor<@Nullable EClass>
{
	protected final @NonNull PivotMetamodelManager metamodelManager;

	protected Id2EClassVisitor(@NonNull PivotMetamodelManager metamodelManager) {
		this.metamodelManager = metamodelManager;
	}

	@Override
	public @Nullable EClass visitClassId(@NonNull ClassId id) {
		Type type = metamodelManager.getEnvironmentFactory().getIdResolver().getType(id);
		return (EClass) type.getESObject();
	}

	@Override
	public @Nullable EClass visitCollectionTypeId(@NonNull CollectionTypeId id) {
		return null;
	}

	@Override
	public @Nullable EClass visitDataTypeId(@NonNull DataTypeId id) {
		return null;
	}

	@Override
	public @Nullable EClass visitEnumerationId(@NonNull EnumerationId id) {
		return null;
	}

	@Override
	public @Nullable EClass visitEnumerationLiteralId(@NonNull EnumerationLiteralId id) {
		return null;
	}

	@Override
	public @Nullable EClass visitInvalidId(@NonNull OclInvalidTypeId id) {
		return null;
	}

	@Override
	public @Nullable EClass visitLambdaTypeId(@NonNull LambdaTypeId id) {
		return null;
	}

	@Override
	public @Nullable EClass visitMapTypeId(@NonNull MapTypeId id) {
		return null;
	}

	@Override
	public @Nullable EClass visitNestedPackageId(@NonNull NestedPackageId id) {
		return null;
	}

	@Override
	public @Nullable EClass visitNsURIPackageId(@NonNull NsURIPackageId id) {
		return null;
	}

	@Override
	public @Nullable EClass visitNullId(@NonNull OclVoidTypeId id) {
		return null;
	}

	@Override
	public @Nullable EClass visitOperationId(@NonNull OperationId id) {
		return null;
	}

	@Override
	public @Nullable EClass visitPrimitiveTypeId(@NonNull PrimitiveTypeId id) {
		return null;
	}

	@Override
	public @Nullable EClass visitPropertyId(@NonNull PropertyId id) {
		return null;
	}

	@Override
	public @Nullable EClass visitRootPackageId(@NonNull RootPackageId id) {
		return null;
	}

	@Override
	public @Nullable EClass visitTemplateBinding(@NonNull TemplateBinding id) {
		return visiting(id);
	}

	@Override
	public @Nullable EClass visitTemplateParameterId(@NonNull TemplateParameterId id) {
		return null;
	}

	@Override
	public @Nullable EClass visitTemplateableTypeId(@NonNull TemplateableTypeId id) {
		return null;
	}

	@Override
	public @Nullable EClass visitTuplePartId(@NonNull TuplePartId id) {
		return null;
	}

	@Override
	public @Nullable EClass visitTupleTypeId(@NonNull TupleTypeId id) {
		return null;
	}

	@Override
	public @Nullable EClass visitUnspecifiedId(@NonNull UnspecifiedId id) {
		return visiting(id);
	}

	@Override
	public @Nullable EClass visitWildcardId(@NonNull WildcardId id) {
		return visiting(id);
	}

	public @Nullable EClass visiting(@NonNull ElementId id) {
		throw new UnsupportedOperationException(getClass().getSimpleName() + ": " + id.getClass().getName());
	}
}
