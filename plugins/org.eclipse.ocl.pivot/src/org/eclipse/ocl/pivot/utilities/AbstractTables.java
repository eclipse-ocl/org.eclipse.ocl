/*******************************************************************************
 * Copyright (c) 2021 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.utilities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.CollectionType;
import org.eclipse.ocl.pivot.LambdaType;
import org.eclipse.ocl.pivot.MapType;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.TemplateParameter;
import org.eclipse.ocl.pivot.TupleType;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.TypedElement;
import org.eclipse.ocl.pivot.ids.IdManager;
import org.eclipse.ocl.pivot.ids.PartId;
import org.eclipse.ocl.pivot.internal.ModelImpl;
import org.eclipse.ocl.pivot.internal.library.executor.PartialStandardLibraryImpl;
import org.eclipse.ocl.pivot.internal.manager.Orphanage;

import com.google.common.collect.Lists;

/**
 * @since 1.14
 */
public abstract class AbstractTables
{
	/**
	 * @since 7.0
	 */
	public static final int IsComposite = 1 << 8;
	/**
	 * @since 7.0
	 */
	public static final int IsDerived = IsComposite << 1;
	/**
	 * @since 7.0
	 */
	public static final int IsID = IsDerived << 1;
	/**
	 * @since 7.0
	 */
	public static final int IsImplicit = IsID << 1;
	/**
	 * @since 7.0
	 */
	public static final int IsInvalidating = IsImplicit << 1;
	/**
	 * @since 7.0
	 */
	public static final int IsReadOnly = IsInvalidating << 1;
	/**
	 * @since 7.0
	 */
	public static final int IsRequired = IsReadOnly << 1;
	/**
	 * @since 7.0
	 */
	public static final int IsResolveProxies = IsRequired << 1;
	/**
	 * @since 7.0
	 */
	public static final int IsStatic = IsResolveProxies << 1;
	/**
	 * @since 7.0
	 */
	public static final int IsTransient = IsStatic << 1;
	/**
	 * @since 7.0
	 */
	public static final int IsTypeof = IsTransient << 1;
	/**
	 * @since 7.0
	 */
	public static final int IsUnsettable = IsTypeof << 1;
	/**
	 * @since 7.0
	 */
	public static final int IsValidating = IsUnsettable << 1;
	/**
	 * @since 7.0
	 */
	public static final int IsVolatile = IsValidating << 1;
	/**
	 * @since 7.0
	 */
	public static final int HasAccumulator = IsVolatile << 1;
	/**
	 * @since 7.0
	 */
	public static final int IndexMask = IsComposite - 1;

	public static final @NonNull Map<@NonNull String, @NonNull AbstractTables> nsURI2tables = new HashMap<>();

	public static final @Nullable AbstractTables basicGet(@NonNull String nsURI) {
		return nsURI2tables.get(nsURI);
	}

	/**
	 * @since 7.0
	 */
	protected static org.eclipse.ocl.pivot.@NonNull Class getASClass(@NonNull EClassifier eClassifier) {
		EnvironmentFactory environmentFactory = PivotUtil.getEnvironmentFactory(eClassifier);
		org.eclipse.ocl.pivot.Class asClass = environmentFactory.getMetamodelManager().getASOfEcore(org.eclipse.ocl.pivot.Class.class, eClassifier);
		assert asClass != null;
		return asClass;
	}

	/**
	 * The BuiltInModel is a Model with support for obtaining orphan types. Creation is managed by the static PartialStandardLibraryImpl.
	 * Ownership is provided by the first BuiltInModel that obtains the orphan type.
	 *
	 * @since 7.0
	 */
	public static class BuiltInModel extends ModelImpl
	{
		private final @NonNull PartialStandardLibraryImpl library;
		private final @NonNull List<org.eclipse.ocl.pivot.@NonNull Class> orphanClasses;

		public BuiltInModel(@NonNull PartialStandardLibraryImpl library, org.eclipse.ocl.pivot.@NonNull Package asPackage) {
			this.library = library;
			setExternalURI(asPackage.getURI());
			getOwnedPackages().add(asPackage);
			org.eclipse.ocl.pivot.Package localOrphanPackage = Orphanage.getLocalOrphanPackage(this);
			this.orphanClasses = PivotUtil.getOwnedClassesList(localOrphanPackage);
		}

		private void addOrphanClass(org.eclipse.ocl.pivot.@NonNull Class orphanClass) {
			if (orphanClass.eContainer() == null) {
				orphanClasses.add(orphanClass);
			}
		}

		public @NonNull CollectionType getCollectionType(org.eclipse.ocl.pivot.@NonNull Class genericType, @NonNull Type elementType) {
			CollectionType collectionType = library.getCollectionType((CollectionType)genericType, elementType, PivotConstants.DEFAULT_IS_NULL_FREE, PivotConstants.DEFAULT_LOWER_BOUND, PivotConstants.DEFAULT_UPPER_BOUND);
			addOrphanClass(collectionType);
			return collectionType;
		}

		public @NonNull Type getLambdaType(@NonNull TypedElement context, @NonNull TypedElement result, @NonNull TypedElement ... parameters) {
			List<@NonNull TypedElement> parameterList = parameters != null ? Lists.newArrayList(parameters) : Collections.emptyList();
			assert parameterList != null;
			LambdaType lambdaType = library.getLambdaManager().getLambdaType(context, parameterList, result, null);
			addOrphanClass(lambdaType);
			return lambdaType;
		}

		public @NonNull MapType getMapType(org.eclipse.ocl.pivot.@NonNull Class genericType, @NonNull Type keyType, @NonNull Type valueType) {
			MapType mapType = library.getMapType(keyType, PivotConstants.DEFAULT_IS_NULL_FREE, valueType, PivotConstants.DEFAULT_IS_NULL_FREE);
			addOrphanClass(mapType);
			return mapType;
		}

		public @NonNull TemplateParameter getTemplateParameter(int i) {
			return Orphanage.getNormalizedTemplateParameter(Orphanage.getLocalOrphanPackage(this), i);
		}

		public @NonNull Type getTupleType(@NonNull Property @NonNull... asParts) {
			List<@NonNull Property> asPartList = new ArrayList<>();
			List<@NonNull PartId> partIds = new ArrayList<>();
			for (@NonNull Property asPart : asParts) {
				asPartList.add(asPart);
				partIds.add(IdManager.getPartId(partIds.size(), PivotUtil.getName(asPart), asPart.getTypeId(), asPart.isIsRequired()));
			}
			TupleType tupleType = library.getTupleType(asPartList, partIds);
			addOrphanClass(tupleType);
			return tupleType;
		}
	}

	protected AbstractTables(@NonNull String nsURI) {
		nsURI2tables.put(nsURI, this);
	}

	/**
	 * Return the EClasses for which there are known invocations of the OCL allInstances() library operation.
	 */
	@Deprecated		// XXX is this really necessary - seems to be just CodegencompanyTables -- NB not QVTd
	public @NonNull EClass @Nullable [] basicGetAllInstancesClasses() {
		return null;
	}

	/**
	 * Return the EReferences whose implicit opposite is used in an OCL Expression.
	 */
	@Deprecated		// XXX is this really necessary - seems to be just CodegencompanyTables -- NB not QVTd
	public @NonNull EReference @Nullable [] basicGetImplicitOpposites() {
		return null;
	}
}