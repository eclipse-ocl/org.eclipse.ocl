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
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.flat.FlatClass;
import org.eclipse.ocl.pivot.ids.CompletePackageId;
import org.eclipse.ocl.pivot.internal.manager.Orphanage;
import org.eclipse.ocl.pivot.utilities.EnvironmentFactory;
import org.eclipse.ocl.pivot.utilities.FeatureFilter;
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
	 * @since 7.0
	 */
	@Nullable Namespace addGlobalNamespace(@NonNull String name, @NonNull Namespace namespace);

	/**
	 * @since 7.0
	 */
	boolean addPartialModel(@NonNull Model model);

	/**
	 * @since 7.0
	 */
	org.eclipse.ocl.pivot.@Nullable Package basicGetASmetamodel();

	/**
	 * Return the already created CompleteClass for asClass within this CompleteModel, or null if not yet created.
	 * <br>
	 * This is for test purposes only since a CompleteClass is normally created lazily on demand.
	 * @since 7.0
	 */
	@Nullable CompleteClass basicGetCompleteClass(org.eclipse.ocl.pivot.@NonNull Class asClass);

	/**
	 * Return the CompletePackage known to be associated with completePackageId, or null if not known.
	 *
	 * @since 7.0
	 */
	@Nullable CompletePackage basicGetCompletePackage(@NonNull CompletePackageId completePackageId);

	/**
	 * Return the CompletePackage known for the partial asPackage, or null if not known.
	 *
	 * @since 7.0
	 */
	@Nullable CompletePackage basicGetCompletePackage(org.eclipse.ocl.pivot.@NonNull Package asPackage);

	/**
	 * Return the CompletePackage known to support packageURI, or null if not known.
	 * Any fragment in packageURI is ignored.
	 *
	 * @since 7.0
	 */
	@Nullable CompletePackage basicGetCompletePackageForURI(@NonNull String packageURI);

	/**
	 * Return the Orphan or Primitive CompletePackage for the Package containing the irregular asClass. Return null for a regular Class.
	 *
	 * @since 7.0
	 */
	@Nullable CompletePackage basicGetSharedCompletePackage(org.eclipse.ocl.pivot.@NonNull Class asClass);

	/**
	 * @since 7.0
	 */
	void dispose();

	/**
	 * @since 7.0
	 */
	org.eclipse.ocl.pivot.@Nullable Class getASClass(@NonNull String className);

	/**
	 * @since 7.0
	 */
	org.eclipse.ocl.pivot.@Nullable Package getASmetamodel();

	/**
	 * Return all constraints applicable to asType and its superclasses. In superclass first then alphabetical order.
	 * Multiple same-named invariants for the same CompleteClass are return as a List<Constraint> rather than just a Constraint.
	 * The multiples are most-executable first. Returns null for none.
	 *
	 * @since 7.0
	 */
	default @Nullable Iterable<@NonNull Object> getAllCompleteInvariants(@NonNull Type asType) { return null; }
	@NonNull Iterable<@NonNull CompletePackage> getAllCompletePackages();

	/**
	 * @since 7.0
	 */
	@Deprecated
	@NonNull Iterable<Constraint> getAllInvariants(@NonNull Type pivotType);

	/**
	 * @since 7.0
	 */
	@NonNull Iterable<@NonNull Operation> getAllOperations(@NonNull Type type, @Nullable FeatureFilter featureFilter);

	/**
	 * @since 7.0
	 */
	@NonNull Iterable<@NonNull Operation> getAllOperations(@NonNull Type type, @Nullable FeatureFilter featureFilter, @NonNull String name);

	/**
	 * @since 7.0
	 */
	@NonNull Iterable<@NonNull CompleteClass> getAllSuperCompleteClasses(@NonNull Type type);

	@Deprecated /* Use Class */
	@NonNull CompleteClass getCompleteClass(@NonNull Type partialClass);
	/**
	 * @since 7.0
	 */
	@NonNull CompleteClass getCompleteClass(org.eclipse.ocl.pivot.@NonNull Class asClass);

	/**
	 * @since 7.0
	 */
	@NonNull CompletePackage getCompletePackage(org.eclipse.ocl.pivot.@NonNull Package asPackage);

	/**
	 * @since 7.0
	 */
	@Nullable ExpressionInOCL getDefaultExpression(@NonNull Property property);

	/**
	 * @since 7.0
	 */
	@NonNull EnvironmentFactory getEnvironmentFactory();

	/**
	 * @since 7.0
	 */
	@NonNull FlatClass getFlatClass(org.eclipse.ocl.pivot.@NonNull Class type);

	/**
	 * @since 7.0
	 */
	@NonNull Set<Map.@NonNull Entry<@NonNull String, @NonNull Namespace>> getGlobalNamespaces();

	/**
	 * @since 7.0
	 */
	@NonNull Iterable<@NonNull Type> getGlobalTypes();

	/**
	 * @since 7.0
	 */
	@NonNull Iterable<@NonNull Constraint> getMemberInvariants(org.eclipse.ocl.pivot.@NonNull Class type);

	/**
	 * @since 7.0
	 */
	@NonNull Iterable<@NonNull Operation> getMemberOperations(org.eclipse.ocl.pivot.@NonNull Class type, boolean selectStatic);

	/**
	 * @since 7.0
	 */
	@NonNull Iterable<@NonNull Property> getMemberProperties(org.eclipse.ocl.pivot.@NonNull Class type, boolean selectStatic);

	/**
	 * @since 7.0
	 */
	@NonNull MetamodelManager getMetamodelManager();

	/**
	 * @since 7.0
	 */
	@NonNull Iterable<? extends Operation> getOperationOverloads(@NonNull Operation pivotOperation);

	/**
	 * @since 7.0
	 */
	@NonNull Orphanage getOrphanage();

	/**
	 * @since 7.0
	 */
	@NonNull Iterable<org.eclipse.ocl.pivot.Class> getPartialClasses(@NonNull Type pivotType);

	/**
	 * @since 7.0
	 */
	@NonNull Iterable<? extends org.eclipse.ocl.pivot.@NonNull Package> getPartialPackages(org.eclipse.ocl.pivot.@NonNull Package pkg);

	/**
	 * @since 7.0
	 */
	org.eclipse.ocl.pivot.@NonNull Class getPrimaryClass(org.eclipse.ocl.pivot.@NonNull Class pivotClass);

	/**
	 * @since 7.0
	 */
	@NonNull <T extends EObject> T getPrimaryElement(@NonNull T element);

	/**
	 * @since 7.0
	 */
	@NonNull Operation getPrimaryOperation(@NonNull Operation pivotOperation);

	/**
	 * @since 7.0
	 */
	org.eclipse.ocl.pivot.@NonNull Package getPrimaryPackage(org.eclipse.ocl.pivot.@NonNull Package eObject);

	/**
	 * @since 7.0
	 */
	org.eclipse.ocl.pivot.@Nullable Package getPrimaryPackage(@NonNull String nsURI, String... subPackagePath);

	/**
	 * @since 7.0
	 */
	@NonNull Property getPrimaryProperty(@NonNull Property pivotProperty);

	/**
	 * @since 7.0
	 */
	org.eclipse.ocl.pivot.@Nullable Class getPrimaryType(@NonNull String nsURI, @NonNull String path, String... extraPath);

	// FIXME ASBH This should probably disappear
	/**
	 * @since 7.0
	 */
	@NonNull Type getPrimaryType(@NonNull Type type);

	@Deprecated org.eclipse.ocl.pivot.@Nullable Package getRootPackage(@NonNull String completeURIorName);

	/**
	 * @since 7.0
	 */
	@NonNull CompleteStandardLibrary getStandardLibrary();

	/**
	 * Create and install the CompletePackage placeholder for completePackageId with uri and optional prefix.
	 * The placeholder initially has no partyial packages.
	 *
	 * @since 7.0
	 */
	@NonNull CompletePackage initCompletePackage(@NonNull CompletePackageId completePackageId, @Nullable String prefix, @NonNull String uri);

	/**
	 * @since 7.0
	 */
	boolean isSuperClassOf(org.eclipse.ocl.pivot.@NonNull Class unspecializedFirstType, org.eclipse.ocl.pivot.@NonNull Class secondType);

	/**
	 * @since 7.0
	 */
	boolean isTypeServeable(@NonNull Type type);

	/**
	 * @since 7.0
	 */
	@NonNull CompletePackageId registerCompletePackageContribution(@NonNull String metamodelName, /*@NonNull*/ EPackage ePackage);

	/**
	 * @since 7.0
	 */
	void removePartialModel(@NonNull Model model);
} // CompleteModel
