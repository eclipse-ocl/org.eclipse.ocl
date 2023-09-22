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
import org.eclipse.ocl.pivot.ids.LambdaTypeId;
import org.eclipse.ocl.pivot.ids.MapTypeId;
import org.eclipse.ocl.pivot.ids.TupleTypeId;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.ids.WildcardId;
import org.eclipse.ocl.pivot.internal.complete.PartialPackages;
import org.eclipse.ocl.pivot.types.TuplePart;
import org.eclipse.ocl.pivot.values.IntegerValue;
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
//	void addOrphanClass(org.eclipse.ocl.pivot.@NonNull Class orphanClass);
	void addPackageListener(@NonNull PartialPackages partialPackages);
	void addReference(@NonNull Type type, @NonNull Element asElement);
	@Nullable CollectionType basicGetCollectionType(@NonNull CollectionTypeId collectionTypeId);
	@Nullable LambdaType basicGetLambdaType(@NonNull LambdaTypeId lambdaTypeId);
	@Nullable MapType basicGetMapType(@NonNull MapTypeId mapTypeId);
	@Nullable TupleType basicGetTupleType(@NonNull TupleTypeId tupleTypeId);
	@Nullable Type basicGetType(@NonNull TypeId typeId, boolean retainStaleEntry);
	@Nullable WildcardType basicGetWildcardType(@NonNull WildcardId wildcardId);
	void dispose();

	/**
	 * Traverse the orphange to prune all entres for types that are no longer well contained
	 * (all transitively referenced types have a non-null eResource()).
	 * <br>
	 * This is an expensive operation that is only needed in long running heavily mutating applications.
	 * Use sparingly.
	 */
	void gc();

	/**
	 * Return the templateArguments specialization of genericClass, which should not be a built-in generic such as Collection/Lambda/Map.
	 */
	org.eclipse.ocl.pivot.@NonNull Class getClassType(org.eclipse.ocl.pivot.@NonNull Class genericClass, @NonNull List<@NonNull ? extends Type> templateArguments);

	/**
	 * Return the specialized collection type for a collection type characteristics.
	 */
	@NonNull CollectionType getCollectionType(@NonNull CollectionType genericType, @NonNull Type elementType, @Nullable Boolean isNullFree, @Nullable IntegerValue lower, @Nullable UnlimitedNaturalValue upper);

	@NonNull LambdaType getLambdaType(org.eclipse.ocl.pivot.@NonNull Class oclLambdaType, @NonNull Type contextType, @NonNull List<@NonNull ? extends Type> parameterTypes, @NonNull Type resultType);

	@NonNull MapType getMapOfEntryType(@NonNull MapType genericType, org.eclipse.ocl.pivot.@NonNull Class entryType);
	/**
	 * Return the specialized map type for a map type descriptor.
	 */
	@NonNull MapType getMapType(@NonNull MapType genericType, @NonNull Type keyType, boolean keysAreNullFree, @NonNull Type valueType, boolean valuesAreNullFree);

	@NonNull StandardLibrary getStandardLibrary();
	@NonNull TupleType getTupleType(org.eclipse.ocl.pivot.@NonNull Class oclTupleType, @NonNull TuplePart @NonNull ... parts);
	@NonNull WildcardType getWildcardType(@NonNull TemplateParameter templateParameter);
	void removePackageListener(@NonNull PartialPackages partialPackages);
	void removeReference(@NonNull Type type, @NonNull Element asElement);
} // Orphanage
