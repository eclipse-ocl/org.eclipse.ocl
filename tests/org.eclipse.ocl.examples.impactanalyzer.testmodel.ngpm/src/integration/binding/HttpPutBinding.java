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
 * A representation of the model object '<em><b>Http Put Binding</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Allows for invoking a function through an HTTP POST request. Parameters to the function may either be encoded as part of the URL or may be transmitted in the request body, e.g., as a multipart message. The UrlPattern specifies how the URL is to be matched and if/which parameters are to be extracted from the URL. Additional parameters are expected in the request body in "default form."
 * <!-- end-model-doc -->
 *
 *
 * @see integration.binding.BindingPackage#getHttpPutBinding()
 * @model
 * @generated
 */
public interface HttpPutBinding extends HttpBinding {
} // HttpPutBinding
