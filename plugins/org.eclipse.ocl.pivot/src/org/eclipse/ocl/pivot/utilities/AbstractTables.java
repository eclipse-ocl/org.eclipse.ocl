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

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.Property;

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
	public static final int IndexMask = IsComposite - 1;

	public static final @NonNull Map<@NonNull String, @NonNull AbstractTables> nsURI2tables = new HashMap<>();

	public static final @Nullable AbstractTables basicGet(@NonNull String nsURI) {
		return nsURI2tables.get(nsURI);
	}

	/**
	 * @since 7.0
	 */
	protected static void createOpposite(/*@NonNull*/ EClass eClass, @NonNull String name, @NonNull Property asProperty) {
		EnvironmentFactory environmentFactory = PivotUtil.getEnvironmentFactory(eClass);
		environmentFactory.installImplicitOppositePropertyDeclaration(asProperty, name);
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

	protected AbstractTables(@NonNull String nsURI) {
		nsURI2tables.put(nsURI, this);
	}

	/**
	 * Return the EClasses for which there are known invocations of the OCL allInstances() library operation.
	 */
	public @NonNull EClass @Nullable [] basicGetAllInstancesClasses() {
		return null;
	}

	/**
	 * Return the EReferences whose implicit opposite is used in an OCL Expression.
	 */
	public @NonNull EReference @Nullable [] basicGetImplicitOpposites() {
		return null;
	}
}