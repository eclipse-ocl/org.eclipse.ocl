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

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.internal.complete.CompleteModelInternal;
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal;
import org.eclipse.ocl.pivot.manager.LambdaTypeManager;
import org.eclipse.ocl.pivot.values.TemplateParameterSubstitutions;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Standard Library Internal</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.pivot.CompleteStandardLibrary#getOwningCompleteEnvironment <em>Owning Complete Environment</em>}</li>
 * </ul>
 *
 * @see org.eclipse.ocl.pivot.PivotPackage#getCompleteStandardLibrary()
 * @generated
 */
public interface CompleteStandardLibrary extends StandardLibrary
{
	/**
	 * Returns the value of the '<em><b>Owning Complete Environment</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owning Complete Environment</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Owning Complete Environment</em>' reference.
	 * @see #setOwningCompleteEnvironment(CompleteEnvironment)
	 * @see org.eclipse.ocl.pivot.PivotPackage#getCompleteStandardLibrary_OwningCompleteEnvironment()
	 * @generated
	 */
	CompleteEnvironment getOwningCompleteEnvironment();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.pivot.CompleteStandardLibrary#getOwningCompleteEnvironment <em>Owning Complete Environment</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Owning Complete Environment</em>' reference.
	 * @see #getOwningCompleteEnvironment()
	 * @generated
	 */
	void setOwningCompleteEnvironment(CompleteEnvironment value);
	/**
	 * @since 1.17
	 */
	@Nullable AnyType basicGetOclAnyType();
	@Nullable Operation basicGetOclInvalidOperation();
	@Nullable Property basicGetOclInvalidProperty();
	@Nullable InvalidType basicGetOclInvalidType();

	/**
	 * Return -ve if match1 is inferior to match2, +ve if match2 is inferior to match1, or
	 * zero if both matches are of equal validity.
	 *
	 * @since 7.0
	 */
	int compareOperationMatches(@NonNull Operation referenceOperation, @Nullable TemplateParameterSubstitutions referenceBindings,
			@NonNull Operation candidateOperation, @Nullable TemplateParameterSubstitutions candidateBindings);

	/**
	 * @since 1.17
	 */
	void defineLibraryTypes(@NonNull Iterable<org.eclipse.ocl.pivot.@NonNull Class> pivotTypes);
	void dispose();
	@Nullable PrimitiveType getBehavioralClass(java.lang.@NonNull Class<?> javaClass);
	@NonNull CompleteModelInternal getCompleteModel();
	@NonNull String getDefaultStandardLibraryURI();

	/**
	 * @since 7.0
	 */
	@NonNull EnvironmentFactoryInternal getEnvironmentFactory();

	/**
	 * @since 7.0
	 */
	@NonNull LambdaTypeManager getLambdaManager();

	/**
	 * @since 7.0
	 */
	@NonNull LambdaType getLambdaType(@NonNull TypedElement contextType, @NonNull List<@NonNull ? extends TypedElement> parameterTypes, @NonNull TypedElement resultType,
			@Nullable TemplateParameterSubstitutions bindings);
	@Override
	@NonNull Property getOclInvalidProperty();
	org.eclipse.ocl.pivot.@Nullable Class getASClass(@NonNull String className);

	org.eclipse.ocl.pivot.Package getRootPackage(@NonNull String name);
	/**
	 * @since 7.0
	 */
	org.eclipse.ocl.pivot.@NonNull Class getSpecializedType(org.eclipse.ocl.pivot.@NonNull Class genericClass,
			@NonNull List<@NonNull ? extends Type> superTemplateArgumentList);
	@NonNull CompleteStandardLibrary init(@NonNull EnvironmentFactoryInternal environmentFactory);
	boolean isExplicitDefaultStandardLibraryURI();
	@Nullable Resource loadDefaultLibrary(@Nullable String uri);
	void resolveSuperClasses(org.eclipse.ocl.pivot.@NonNull Class specializedClass, org.eclipse.ocl.pivot.@NonNull Class unspecializedClass);
	void setDefaultStandardLibraryURI(@NonNull String defaultStandardLibraryURI);

} // CompleteStandardLibrary
