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

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Template Parameter Class</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A NormalizedTemplateParameter is normalized to its position in the TemplateableElement TemplateParameter hierarchy. It has no lower bound (other than the implicit OclAny). It avoids specializing operations and iterations for each actual type.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.pivot.NormalizedTemplateParameter#getIndex <em>Index</em>}</li>
 * </ul>
 *
 * @see org.eclipse.ocl.pivot.PivotPackage#getNormalizedTemplateParameter()
 * @generated
 */
public interface NormalizedTemplateParameter extends TemplateParameter
{
	/**
	 * Returns the value of the '<em><b>Index</b></em>' attribute.
	 * The default value is <code>"-1"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Index</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Index</em>' attribute.
	 * @see org.eclipse.ocl.pivot.PivotPackage#getNormalizedTemplateParameter_Index()
	 * @generated NOT
	 */
	int getIndex();

} // NormalizedTemplateParameter
