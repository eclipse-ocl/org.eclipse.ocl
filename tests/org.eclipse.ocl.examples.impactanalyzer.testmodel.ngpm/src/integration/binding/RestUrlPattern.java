/*******************************************************************************
 * Copyright (c) 2009, 2018 SAP AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * 
 * Contributors:
 *     SAP AG - initial API and implementation
 ******************************************************************************
 */
package integration.binding;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Rest Url Pattern</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Allows for the mapping of parameter values to parts of the base URL (preceding any query part, before the ? character, if any). This can help construct very short, easy to use URLs but comes at the price that special URL characters such as "/", "?" or '&' need to be URL-encoded correspondingly.
 * <!-- end-model-doc -->
 *
 *
 * @see integration.binding.BindingPackage#getRestUrlPattern()
 * @model
 * @generated
 */
public interface RestUrlPattern extends UrlPattern {
} // RestUrlPattern
