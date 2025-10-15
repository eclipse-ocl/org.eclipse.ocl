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
package org.eclipse.ocl.pivot;

import java.util.List;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.ids.CompletePackageId;
import org.eclipse.ocl.pivot.internal.complete.CompleteClassInternal;
import org.eclipse.ocl.pivot.internal.manager.Orphanage;
import org.eclipse.ocl.pivot.utilities.EnvironmentFactory;
import org.eclipse.ocl.pivot.utilities.MetamodelManager;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Complete Model</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.pivot.CompleteModel#getOrphanCompletePackage <em>Orphan Complete Package</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.CompleteModel#getOwnedCompletePackages <em>Owned Complete Packages</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.CompleteModel#getPartialModels <em>Partial Models</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.CompleteModel#getPrimitiveCompletePackage <em>Primitive Complete Package</em>}</li>
 * </ul>
 *
 * @see org.eclipse.ocl.pivot.PivotPackage#getCompleteModel()
 * @generated
 */
public interface CompleteModel extends NamedElement
{
	/**
	 * Returns the value of the '<em><b>Orphan Complete Package</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Orphan Complete Package</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Orphan Complete Package</em>' reference.
	 * @see org.eclipse.ocl.pivot.PivotPackage#getCompleteModel_OrphanCompletePackage()
	 * @generated
	 */
	@NonNull OrphanCompletePackage getOrphanCompletePackage();

	/**
	 * Returns the value of the '<em><b>Owned Complete Packages</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.ocl.pivot.CompletePackage}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.ocl.pivot.CompletePackage#getOwningCompleteModel <em>Owning Complete Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Complete Packages</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Owned Complete Packages</em>' containment reference list.
	 * @see org.eclipse.ocl.pivot.PivotPackage#getCompleteModel_OwnedCompletePackages()
	 * @see org.eclipse.ocl.pivot.CompletePackage#getOwningCompleteModel
	 * @generated
	 */
	@NonNull List<CompletePackage> getOwnedCompletePackages();

	/**
	 * Returns the value of the '<em><b>Partial Models</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.ocl.pivot.Model}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Partial Roots</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Partial Models</em>' reference list.
	 * @see org.eclipse.ocl.pivot.PivotPackage#getCompleteModel_PartialModels()
	 * @generated
	 */
	@NonNull List<Model> getPartialModels();

	/**
	 * Returns the value of the '<em><b>Primitive Complete Package</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Primitive Complete Package</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Primitive Complete Package</em>' reference.
	 * @see org.eclipse.ocl.pivot.PivotPackage#getCompleteModel_PrimitiveCompletePackage()
	 * @generated
	 */
	@NonNull PrimitiveCompletePackage getPrimitiveCompletePackage();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Nullable CompletePackage getOwnedCompletePackage(/*@NonNull*/ String packageName);

	/**
	 * Return the already created CompleteClass for asClass within this CompleteModel, or null if not yet created.
	 * <br>
	 * This is for test purposes only since a CompleteClass is normally created lazily on demand.
	 * @since 7.0
	 */
	@Nullable CompleteClassInternal basicGetCompleteClass(org.eclipse.ocl.pivot.@NonNull Class asClass);

	/**
	 * @since 7.0
	 */
	@Nullable CompletePackage basicGetCompletePackage(@NonNull CompletePackageId completePackageId);

	/**
	 * @since 7.0
	 */
	@Nullable CompletePackage basicGetCompletePackage(org.eclipse.ocl.pivot.@NonNull Package asPackage);

	/**
	 * @since 7.0
	 */
	@Nullable CompletePackage basicGetCompletePackageForURI(@NonNull String packageURI);

	/**
	 * @since 7.0
	 */
	void dispose();

	/**
	 * Return all constraints applicable to asType and its superclasses. In superclass first then alphabetical order.
	 * Multiple same-named invariants for the same CompleteClass are return as a List<Constraint> rather than just a Constraint.
	 * The multiples are most-executable first. Returns null for none.
	 *
	 * @since 7.0
	 */
	default @Nullable Iterable<@NonNull Object> getAllCompleteInvariants(@NonNull Type asType) { return null; }
	@NonNull Iterable<@NonNull CompletePackage> getAllCompletePackages();
	@Deprecated /* Use Class */
	@NonNull CompleteClass getCompleteClass(@NonNull Type partialClass);
	/**
	 * @since 7.0
	 */
	@NonNull CompleteClassInternal getCompleteClass(org.eclipse.ocl.pivot.@NonNull Class asClass);

	/**
	 * @since 7.0
	 */
	@NonNull CompletePackage getCompletePackage(@NonNull CompletePackageId completePackageId, @Nullable String prefix, @NonNull String uri);
	@NonNull CompletePackage getCompletePackage(org.eclipse.ocl.pivot.@NonNull Package asPackage);

	/**
	 * @since 7.0
	 */
	@Deprecated @Nullable CompletePackage getCompletePackage2(org.eclipse.ocl.pivot.@NonNull Package asPackage);

	/**
	 * @since 7.0
	 */
	@NonNull EnvironmentFactory getEnvironmentFactory();
	/**
	 * Return the equivalent class to thatClass in thisModel, where equivalent is the same class/package name
	 * hierarchy wrt the orphan package in thisModel. This is typically used to create a merge contribution
	 * for thatClass in thisModel avoiding the need to modify thatClass.
	 * <br>
	 * i.e the equivalent of A::B::thatClass in thatModel is $$::A::B::thatClass in thisModel.
	 *
	 * @since 7.0
	 */
	org.eclipse.ocl.pivot.@NonNull Class getEquivalentClass(@NonNull Model thisModel, org.eclipse.ocl.pivot.@NonNull Class thatClass);
	/**
	 * @since 7.0
	 */
	@NonNull MetamodelManager getMetamodelManager();

	/**
	 * @since 7.0
	 */
	@NonNull Orphanage getOrphanage();

	@Deprecated org.eclipse.ocl.pivot.@Nullable Package getRootPackage(@NonNull String completeURIorName);

	/**
	 * @since 7.0
	 */
	@NonNull CompleteStandardLibrary getStandardLibrary();

	/**
	 * @since 7.0
	 */
	@NonNull CompletePackageId registerCompletePackageContribution(@NonNull String metamodelName, /*@NonNull*/ EPackage ePackage);
} // CompleteModel
