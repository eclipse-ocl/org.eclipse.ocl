/**
 * Copyright (c) 2010, 2022 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 */
package org.eclipse.ocl.pivot;

import java.util.List;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.ids.CollectionTypeId;
import org.eclipse.ocl.pivot.ids.IdResolver;
import org.eclipse.ocl.pivot.ids.LambdaTypeId;
import org.eclipse.ocl.pivot.ids.MapTypeId;
import org.eclipse.ocl.pivot.ids.TupleTypeId;
import org.eclipse.ocl.pivot.internal.complete.PartialPackages;
import org.eclipse.ocl.pivot.values.IntegerValue;
import org.eclipse.ocl.pivot.values.MapTypeParameters;
import org.eclipse.ocl.pivot.values.TemplateParameterSubstitutions;
import org.eclipse.ocl.pivot.values.UnlimitedNaturalValue;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Orphanage</b></em>'.
 * <!-- end-user-doc -->
 *
 *
 * @see org.eclipse.ocl.pivot.PivotPackage#getOrphanage()
 * @generated
 */
public interface Orphanage extends org.eclipse.ocl.pivot.Package
{
	void addOrphanClass(org.eclipse.ocl.pivot.@NonNull Class orphanClass);
	void addPackageListener(@NonNull PartialPackages partialPackages);
	@Nullable CollectionType basicGetCollectionType(@NonNull CollectionTypeId collectionTypeId);
	@Nullable LambdaType basicGetLambdaType(@NonNull LambdaTypeId lambdaTypeId);
	@Nullable MapType basicGetMapType(@NonNull MapTypeId mapTypeId);
	@Nullable TupleType basicGetTupleType(@NonNull TupleTypeId tupleTypeId);
	void dispose();

	/**
	 * Return the specialized collection type for a collection type characteristics.
	 */
	@NonNull CollectionType getCollectionType(@NonNull CollectionType genericType, @NonNull Type elementType, @Nullable Boolean isNullFree, @Nullable IntegerValue lower, @Nullable UnlimitedNaturalValue upper);

	@NonNull LambdaType getLambdaType(@NonNull String typeName, @NonNull Type contextType, @NonNull List<@NonNull ? extends Type> parameterTypes, @NonNull Type resultType,
			@Nullable TemplateParameterSubstitutions bindings);

	/**
	 * Return the specialized map type for a map type descriptor.
	 */
	@NonNull MapType getMapType(@NonNull MapTypeParameters<@NonNull Type, @NonNull Type> typeParameters);

	@NonNull StandardLibrary getStandardLibrary();
	@NonNull TupleType getTupleType(@NonNull IdResolver idResolver, @NonNull TupleTypeId tupleTypeId);
	void removePackageListener(@NonNull PartialPackages partialPackages);
} // Orphanage
