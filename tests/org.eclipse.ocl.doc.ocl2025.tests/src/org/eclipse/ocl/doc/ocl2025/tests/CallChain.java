/*******************************************************************************
 * Copyright (c) 2025 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.doc.ocl2025.tests;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.CallExp;
import org.eclipse.ocl.pivot.ids.OperationId;
import org.eclipse.ocl.pivot.utilities.IndexableIterable;


/**
 * ParametersId provides a hashable list of operation
 * parameter ids suitable for use when indexing operation overloads.
 */
public interface CallChain extends IndexableIterable<@NonNull OperationId>	//  ?? ElementId
{
	void addCallExp(@NonNull CallExp asCallExp);
	public void printOut();
}