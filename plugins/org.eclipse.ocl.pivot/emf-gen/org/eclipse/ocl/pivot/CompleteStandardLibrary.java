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

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.internal.complete.CompleteModelInternal;
import org.eclipse.ocl.pivot.values.TemplateParameterSubstitutions;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Standard Library</b></em>'.
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
	 * Returns the value of the '<em><b>Owning Complete Environment</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.ocl.pivot.CompleteEnvironment#getOwnedStandardLibrary <em>Owned Standard Library</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owning Complete Environment</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Owning Complete Environment</em>' container reference.
	 * @see #setOwningCompleteEnvironment(CompleteEnvironment)
	 * @see org.eclipse.ocl.pivot.PivotPackage#getCompleteStandardLibrary_OwningCompleteEnvironment()
	 * @see org.eclipse.ocl.pivot.CompleteEnvironment#getOwnedStandardLibrary
	 * @generated
	 */
	CompleteEnvironment getOwningCompleteEnvironment();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.pivot.CompleteStandardLibrary#getOwningCompleteEnvironment <em>Owning Complete Environment</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Owning Complete Environment</em>' container reference.
	 * @see #getOwningCompleteEnvironment()
	 * @generated
	 */
	void setOwningCompleteEnvironment(CompleteEnvironment value);

	int compareOperationMatches(@NonNull Operation reference, @NonNull TemplateParameterSubstitutions referenceBindings,
			@NonNull Operation candidate, @NonNull TemplateParameterSubstitutions candidateBindings);
	/**
	 * @since 1.17
	 */
	void defineLibraryTypes(@NonNull Iterable<org.eclipse.ocl.pivot.@NonNull Class> pivotTypes);
	org.eclipse.ocl.pivot.@Nullable Class getASClass(@NonNull String className);
	@NonNull Iterable<@NonNull ? extends CompletePackage> getAllCompletePackages();
	@NonNull CompleteModelInternal getCompleteModel();
	@NonNull String getDefaultStandardLibraryURI();
	org.eclipse.ocl.pivot.@NonNull Class getRequiredLibraryType(@NonNull String typeName);
	@NonNull CompleteStandardLibrary init(@NonNull CompleteModelInternal completeModel);
	boolean isExplicitDefaultStandardLibraryURI();
	@Nullable Resource loadDefaultLibrary(@Nullable String uri);
	/**
	 * Define the behavioralClass of asDataType preferring and returning a partial class in the saem model as asDataType.
	 */
	@Nullable PrimitiveType setBehavioralClass(@NonNull DataType asDataType, @Nullable PrimitiveType behavioralClass);
	void setDefaultStandardLibraryURI(@NonNull String defaultStandardLibraryURI);
} // StandardLibrary
