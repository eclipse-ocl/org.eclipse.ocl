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
package dataaccess.expressions.collectionexpressions;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Including At</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Like Including, produced a new value based on the *source* value that includes the *argument* object. In addition, *IncludingAt* specifies the position where to include the *argument* object. The multiplicity of source and resulting value have ordered=true. If *source* already includes an object equal to *argument* and the *source* multiplicity has unique=true, that element will afterwards be at the requested position or at the end of the resulting value if the *at* position is greater or equal the *source*'s cardinality.
 * <!-- end-model-doc -->
 *
 *
 * @see dataaccess.expressions.collectionexpressions.CollectionexpressionsPackage#getIncludingAt()
 * @model
 * @generated
 */
public interface IncludingAt extends Including, WithPosition {
} // IncludingAt
