/**
 * Copyright (c) 2010, 2021 Willink Transformations and others.
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
import org.eclipse.ocl.pivot.flat.FlatClass;
import org.eclipse.ocl.pivot.ids.OperationId;
import org.eclipse.ocl.pivot.utilities.EnvironmentFactory;
import org.eclipse.ocl.pivot.utilities.FeatureFilter;
import org.eclipse.ocl.pivot.utilities.MetamodelManager;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Complete Class</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.pivot.CompleteClass#getOwningCompletePackage <em>Owning Complete Package</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.CompleteClass#getPartialClasses <em>Partial Classes</em>}</li>
 * </ul>
 *
 * @see org.eclipse.ocl.pivot.PivotPackage#getCompleteClass()
 * @generated
 */
public interface CompleteClass extends NamedElement
{
	/**
	 * Returns the value of the '<em><b>Owning Complete Package</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.ocl.pivot.CompletePackage#getOwnedCompleteClasses <em>Owned Complete Classes</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owning Complete Package</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Owning Complete Package</em>' container reference.
	 * @see #setOwningCompletePackage(CompletePackage)
	 * @see org.eclipse.ocl.pivot.PivotPackage#getCompleteClass_OwningCompletePackage()
	 * @see org.eclipse.ocl.pivot.CompletePackage#getOwnedCompleteClasses
	 * @generated
	 */
	CompletePackage getOwningCompletePackage();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.pivot.CompleteClass#getOwningCompletePackage <em>Owning Complete Package</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Owning Complete Package</em>' container reference.
	 * @see #getOwningCompletePackage()
	 * @generated
	 */
	void setOwningCompletePackage(CompletePackage value);

	/**
	 * Returns the value of the '<em><b>Partial Classes</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.ocl.pivot.Class}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Partial Classes</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Partial Classes</em>' reference list.
	 * @see org.eclipse.ocl.pivot.PivotPackage#getCompleteClass_PartialClasses()
	 * @generated
	 */
	@NonNull List<org.eclipse.ocl.pivot.Class> getPartialClasses();

	/**
	 * @since 7.0
	 */
	void addClass(org.eclipse.ocl.pivot.@NonNull Class partialClass);

	/**
	 * Return the primary Property for the propertyName property or null if none.
	 * Conflicting static and non-static properties throw a SemanticException.
	 *
	 * @since 7.0
	 */
	@Nullable Property basicGetPrimaryProperty(@Nullable FeatureFilter featureFilter, @NonNull String propertyName);

	/**
	 * @since 7.0
	 */
	boolean conformsTo(@NonNull StandardLibrary standardLibrary, @NonNull CompleteClass thatCompleteClass);
	/**
	 * @since 7.0
	 */
	boolean conformsTo(@NonNull StandardLibrary standardLibrary, @NonNull Type thatType);
	/**
	 * Track the addition of a partialClass to a CompleteClass.
	 */
//	void didAddClass(org.eclipse.ocl.pivot.@NonNull Class partialClass);	// XXX didRemoveClass asymmetry
	/**
	 * Track the removal of a partialClass from a CompleteClass returning true if the CompleteClass is empty.
	 * @since 7.0
	 */
	boolean didRemoveClass(org.eclipse.ocl.pivot.@NonNull Class partialClass);
	/**
	 * @since 7.0
	 */
	void dispose();
	org.eclipse.ocl.pivot.@Nullable Class getBehavioralClass();
	/**
	 * @since 7.0
	 */
	@NonNull CompleteModel getCompleteModel();
	/**
	 * @since 7.0
	 */
	@NonNull EnvironmentFactory getEnvironmentFactory();
	/**
	 * @since 7.0
	 */
	@NonNull FlatClass getFlatClass();
	/**
	 * @since 7.0
	 */
	@NonNull MetamodelManager getMetamodelManager();
	@Nullable Operation getOperation(@NonNull OperationId operationId);
	@Nullable Operation getOperation(@NonNull Operation operationId);
	@Nullable Iterable<@NonNull Operation> getOperationOverloads(@NonNull Operation pivotOperation);
	@NonNull Iterable<@NonNull Operation> getOperations(@Nullable FeatureFilter featureFilter);
	@NonNull Iterable<@NonNull Operation> getOperations(@Nullable FeatureFilter featureFilter, @Nullable String name);
	org.eclipse.ocl.pivot.@NonNull Class getPrimaryClass();
	/**
	 * Return an iterable over each primary Property for each property that satisfies featureFilter.
	 * Conflicting static and non-static properties are both returned.
	 *
	 * @since 7.0
	 */
	@NonNull Iterable<@NonNull Property> getPrimaryProperties(@Nullable FeatureFilter featureFilter);
	/**
	 * Return a singleton iterable comprising the primary Property for the name property if it satisfies featureFilter.
	 * Conflicting static and non-static properties are returned as a two element list.
	 *
	 * @since 7.0
	 */
	@NonNull Iterable<@NonNull Property> getPrimaryProperties(@Nullable FeatureFilter featureFilter, @NonNull String name);
	/**
	 * Return the primary Property for the name property that satisfies featureFilter.
	 *
	 * @since 7.0
	 */
	@NonNull Property getPrimaryProperty(@Nullable FeatureFilter featureFilter, @NonNull String name);
	@NonNull Iterable<@NonNull CompleteClass> getProperSuperCompleteClasses();
//	@NonNull Iterable<org.eclipse.ocl.pivot.@NonNull Class> getProperSuperClasses();
	/**
	 * Return all partial Property's with the same name and isStatic as asProperty leaving the caller to resolve ambiguities.
	 *
	 * @since 7.0
	 */
	@Nullable Iterable<@NonNull Property> getProperties(@NonNull Property asProperty);
	/**
	 * Return all partial Property's satisfying featureFilter leaving the caller to resolve ambiguities.
	 *
	 * @since 7.0
	 */
	@NonNull Iterable<@NonNull Property> getProperties(@Nullable FeatureFilter featureFilter);
	/**
	 * Return all partial Property's for name property leaving the caller to resolve ambiguities..
	 *
	 * @since 7.0
	 */
	@NonNull Iterable<@NonNull Property> getProperties(@Nullable FeatureFilter featureFilter, @NonNull String name);
	@NonNull Iterable<@NonNull State>  getStates();
	@NonNull Iterable<@NonNull State>  getStates(@Nullable String name);
	@NonNull Iterable<@NonNull CompleteClass> getSuperCompleteClasses();
	/**
	 * @since 7.0
	 */
	boolean isElementType(@NonNull StandardLibrary standardLibrary, @NonNull Type elementType, @NonNull VoidType oclVoidType);
	// void uninstall();
} // CompleteClass
