/**
 * Copyright (c) 2010, 2019 Willink Transformations and others.
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
import org.eclipse.ocl.pivot.CompleteStandardLibrary;
import org.eclipse.ocl.pivot.OrphanCompletePackage;
import org.eclipse.ocl.pivot.PivotPackage;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.flat.FlatClass;
import org.eclipse.ocl.pivot.internal.complete.CompleteClassInternal;
import org.eclipse.ocl.pivot.internal.complete.CompletePackageInternal;
import org.eclipse.ocl.pivot.util.Visitor;
import org.eclipse.ocl.pivot.utilities.PivotConstants;
import org.eclipse.ocl.pivot.values.TemplateParameterSubstitutions;

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

	private class OrphanCompleteClassImpl extends CompleteClassImpl
	{
		protected final @NonNull FlatClass genericFlatClass;

		public OrphanCompleteClassImpl(@NonNull FlatClass genericFlatClass) {
			this.genericFlatClass = genericFlatClass;
		}

		@Override
		public boolean conformsTo(@NonNull CompleteClass thatCompleteClass) {
			final org.eclipse.ocl.pivot.@NonNull Class thisClass = getPrimaryClass();
			final org.eclipse.ocl.pivot.@NonNull Class thatClass = thatCompleteClass.getPrimaryClass();
			CompleteEnvironmentImpl completeEnvironmentImpl = new CompleteEnvironmentImpl()	{	// FIXME avoid this horrible fudge
				{
					this.ownedCompleteModel = getCompleteModel();
				}

				@Override
				public @NonNull CompleteClassInternal getCompleteClass(@NonNull Type asType) {
					if (asType == thisClass) {
						return OrphanCompleteClassImpl.this;
					}
					if (asType == thatClass) {
						return (@NonNull CompleteClassInternal) thatCompleteClass;
					}
					return super.getCompleteClass(asType);
				}
			};
			return completeEnvironmentImpl.getOwnedStandardLibrary().conformsTo(thisClass, TemplateParameterSubstitutions.EMPTY, thatClass, TemplateParameterSubstitutions.EMPTY);
		}

		@Override
		public @NonNull CompletePackageInternal getOwningCompletePackage() {
			return OrphanCompletePackageImpl.this;
		}
	}

	private @NonNull Map<org.eclipse.ocl.pivot.Class, WeakReference<OrphanCompleteClassImpl>> class2orphanCompleteClass
	= new WeakHashMap<org.eclipse.ocl.pivot.Class, WeakReference<OrphanCompleteClassImpl>>();

	protected OrphanCompletePackageImpl()
	{
		super();
		init("$orphans$", "orph", PivotConstants.ORPHANAGE_URI);
	}

	@Override
	public <R> R accept(@NonNull Visitor<R> visitor) {
		return visitor.visitOrphanCompletePackage(this);
	}

	@Override
	public void assertSamePackage(org.eclipse.ocl.pivot.@Nullable Package asPackage) {
		assert asPackage != null;
		org.eclipse.ocl.pivot.Package parentPackage = asPackage.getOwningPackage();
		assert parentPackage == null;
		assert OrphanageImpl.isOrphanage(asPackage);
	}

	@Override
	public @NonNull CompleteClassInternal getCompleteClass(org.eclipse.ocl.pivot.@NonNull Class type) {
		WeakReference<OrphanCompleteClassImpl> ref = class2orphanCompleteClass.get(type);
		if (ref != null) {
			OrphanCompleteClassImpl orphanCompleteClass = ref.get();
			if (orphanCompleteClass != null) {
				return orphanCompleteClass;
			}
		}
		Type generic = (Type)type.getGeneric();
		if (generic == null) generic = type;		// Tuples and Lambdas are not generic
		CompleteStandardLibrary standardLibrary = getCompleteModel().getEnvironmentFactory().getStandardLibrary();
		FlatClass genericFlatClass = generic.getFlatClass(standardLibrary);
		OrphanCompleteClassImpl completeClass = new OrphanCompleteClassImpl(genericFlatClass);
		completeClass.setName(type.getName());
		completeClass.getPartialClasses().add(type);
		class2orphanCompleteClass.put(type, new WeakReference<OrphanCompleteClassImpl>(completeClass));
		return completeClass;
	}

	@Override
	public @Nullable CompleteClassInternal getOwnedCompleteClass(String name) {
		return null;			// No orphan CompleteClasses
	}
} //OrphanCompletePackageImpl
