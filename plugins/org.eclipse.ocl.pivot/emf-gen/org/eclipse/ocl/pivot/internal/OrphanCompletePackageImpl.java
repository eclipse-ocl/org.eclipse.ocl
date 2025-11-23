/**
 * Copyright (c) 2010, 2025 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 */
package org.eclipse.ocl.pivot.internal;

import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.WeakHashMap;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.CompleteClass;
import org.eclipse.ocl.pivot.CompletePackage;
import org.eclipse.ocl.pivot.OrphanCompletePackage;
import org.eclipse.ocl.pivot.PivotPackage;
import org.eclipse.ocl.pivot.StandardLibrary;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.internal.manager.Orphanage;
import org.eclipse.ocl.pivot.util.Visitor;
import org.eclipse.ocl.pivot.utilities.PivotConstants;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Orphan Complete Package</b></em>'.
 * <!-- end-user-doc -->
 *
 * @generated
 */
public class OrphanCompletePackageImpl extends CompletePackageImpl implements OrphanCompletePackage
{
	/**
	 * The number of structural features of the '<em>Orphan Complete Package</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ORPHAN_COMPLETE_PACKAGE_FEATURE_COUNT = CompletePackageImpl.COMPLETE_PACKAGE_FEATURE_COUNT + 0;
	/**
	 * The number of operations of the '<em>Orphan Complete Package</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ORPHAN_COMPLETE_PACKAGE_OPERATION_COUNT = CompletePackageImpl.COMPLETE_PACKAGE_OPERATION_COUNT + 0;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass()
	{
		return PivotPackage.Literals.ORPHAN_COMPLETE_PACKAGE;
	}

	@Deprecated
	private class OrphanCompleteClassImpl extends CompleteClassImpl
	{
		@Override
		public boolean conformsTo(@NonNull StandardLibrary standardLibrary, @NonNull CompleteClass rightCompleteClass) {
			CompleteClass leftCompleteClass = this;
			if (rightCompleteClass instanceof OrphanCompleteClassImpl) {
				org.eclipse.ocl.pivot.Class leftPrimaryClass = leftCompleteClass.getPrimaryClass();
				org.eclipse.ocl.pivot.Class rightPrimaryClass = rightCompleteClass.getPrimaryClass();
				return standardLibrary.conformsTo(leftPrimaryClass, rightPrimaryClass);
			}
			else {
				return super.conformsTo(standardLibrary, rightCompleteClass);
			}
		}

		@Override
		public boolean conformsTo(@NonNull StandardLibrary standardLibrary, @NonNull Type rightType) {
			CompleteClass leftCompleteClass = this;
			org.eclipse.ocl.pivot.Class leftPrimaryClass = leftCompleteClass.getPrimaryClass();
			Type rightPrimaryClass = rightType;
			return standardLibrary.conformsTo(leftPrimaryClass, rightPrimaryClass);
		}

		@Override
		public @NonNull CompletePackage getOwningCompletePackage() {
			return OrphanCompletePackageImpl.this;
		}
	}

	private @NonNull Map<org.eclipse.ocl.pivot.@NonNull Class, WeakReference<@NonNull CompleteClass>> class2orphanCompleteClass
				= new WeakHashMap<org.eclipse.ocl.pivot.@NonNull Class, WeakReference<@NonNull CompleteClass>>();

	protected OrphanCompletePackageImpl()
	{
		super();
		init(PivotConstants.ORPHANAGE_ID, PivotConstants.ORPHANAGE_PREFIX, PivotConstants.ORPHANAGE_URI);
	}

	@Override
	public <R> R accept(@NonNull Visitor<R> visitor) {
		return visitor.visitOrphanCompletePackage(this);
	}

	@Override
	public void assertSamePackage(org.eclipse.ocl.pivot.@Nullable Package domainPackage) {
		assert domainPackage != null;
		org.eclipse.ocl.pivot.Package parentPackage = domainPackage.getOwningPackage();
		assert parentPackage == null;
		assert Orphanage.isOrphanage(domainPackage);
	}

	/**
	 * @since 7.0
	 */
	@Override
	protected @NonNull CompleteClass createCompleteClass() {
		return new OrphanCompleteClassImpl();
	}

	/**
	 * @since 7.0
	 */
	@Override
	public @NonNull CompleteClass createCompleteClass(org.eclipse.ocl.pivot.@NonNull Class asClass, @NonNull String name) {
		CompleteClass completeClass = createCompleteClass();
		completeClass.setName(name);
	//	getOwnedCompleteClasses().add(completeClass);		// orphans are not 'owned'
		return completeClass;
	}

	@Override
	public void dispose() {
		super.dispose();
		class2orphanCompleteClass.clear();
	}

	@Override
	public @NonNull CompleteClass getCompleteClass(org.eclipse.ocl.pivot.@NonNull Class asClass) {
		WeakReference<@NonNull CompleteClass> ref = class2orphanCompleteClass.get(asClass);
		CompleteClass completeClass = ref != null ? ref.get() : null;
		if (completeClass == null) {
			String name = asClass.getName();
			assert name != null;
			completeClass = createCompleteClass(asClass, name);
			class2orphanCompleteClass.put(asClass, new WeakReference<>(completeClass));
		}
		completeClass.getPartialClasses().add(asClass);
		return completeClass;
	}

	@Override
	public @Nullable CompleteClass getOwnedCompleteClass(String name) {
		return null;			// No orphan CompleteClasses
	}
} //OrphanCompletePackageImpl
