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

import org.eclipse.jdt.annotation.NonNull;
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

/**
 * An Id2JavaClassVisitor return the Java Interface for an Id.
 */
public class Id2JavaInterfaceVisitor implements IdVisitor<@NonNull Class<? extends ElementId>>
{
	@Override
	public @NonNull Class<? extends ElementId> visitClassId(@NonNull ClassId id) {
		return ClassId.class;
	}

	@Override
	public @NonNull Class<? extends ElementId> visitCollectionTypeId(@NonNull CollectionTypeId id) {
		return CollectionTypeId.class;
	}

	@Override
	public @NonNull Class<? extends ElementId> visitDataTypeId(@NonNull DataTypeId id) {
		return DataTypeId.class;
	}

	@Override
	public @NonNull Class<? extends ElementId> visitEnumerationId(@NonNull EnumerationId id) {
		return EnumerationId.class;
	}

	@Override
	public @NonNull Class<? extends ElementId> visitEnumerationLiteralId(@NonNull EnumerationLiteralId id) {
		return EnumerationLiteralId.class;
	}

	@Override
	public @NonNull Class<? extends ElementId> visitInvalidId(@NonNull OclInvalidTypeId id) {
		return OclInvalidTypeId.class;
	}

	@Override
	public @NonNull Class<? extends ElementId> visitLambdaTypeId(@NonNull LambdaTypeId id) {
		return LambdaTypeId.class;
	}

	@Override
	public @NonNull Class<? extends ElementId> visitMapTypeId(@NonNull MapTypeId id) {
		return MapTypeId.class;
	}

	@Override
	public @NonNull Class<? extends ElementId> visitNestedPackageId(@NonNull NestedPackageId id) {
		return NestedPackageId.class;
	}

	@Override
	public @NonNull Class<? extends ElementId> visitNsURIPackageId(@NonNull NsURIPackageId id) {
		return NsURIPackageId.class;
	}

	@Override
	public @NonNull Class<? extends ElementId> visitNullId(@NonNull OclVoidTypeId id) {
		return OclVoidTypeId.class;
	}

	@Override
	public @NonNull Class<? extends ElementId> visitOperationId(@NonNull OperationId id) {
		return OperationId.class;
	}

	@Override
	public @NonNull Class<? extends ElementId> visitPrimitiveTypeId(@NonNull PrimitiveTypeId id) {
		return PrimitiveTypeId.class;
	}

	@Override
	public @NonNull Class<? extends ElementId> visitPropertyId(@NonNull PropertyId id) {
		return PropertyId.class;
	}

	@Override
	public @NonNull Class<? extends ElementId> visitRootPackageId(@NonNull RootPackageId id) {
		return RootPackageId.class;
	}

	@Override
	public @NonNull Class<? extends ElementId> visitTemplateBinding(@NonNull TemplateBinding id) {
		return TemplateBinding.class;
	}

	@Override
	public @NonNull Class<? extends ElementId> visitTemplateParameterId(@NonNull TemplateParameterId id) {
		return TemplateParameterId.class;
	}

	@Override
	public @NonNull Class<? extends ElementId> visitTemplateableTypeId(@NonNull TemplateableTypeId id) {
		return TemplateableTypeId.class;
	}

	@Override
	public @NonNull Class<? extends ElementId> visitTuplePartId(@NonNull TuplePartId id) {
		return TuplePartId.class;
	}

	@Override
	public @NonNull Class<? extends ElementId> visitTupleTypeId(@NonNull TupleTypeId id) {
		return TupleTypeId.class;
	}

	@Override
	public @NonNull Class<? extends ElementId> visitUnspecifiedId(@NonNull UnspecifiedId id) {
		return UnspecifiedId.class;
	}

	@Override
	public @NonNull Class<? extends ElementId> visitWildcardId(@NonNull WildcardId id) {
		return WildcardId.class;
	}
}