/*******************************************************************************
 * Copyright (c) 2024 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.xtext.completeocl.utilities;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.resource.CSResource;
import org.eclipse.ocl.xtext.completeoclcs.util.AbstractCompleteOCLCSUnloadVisitor;

public class CompleteOCLCSUnloadVisitor extends AbstractCompleteOCLCSUnloadVisitor
{
	public CompleteOCLCSUnloadVisitor(@NonNull CSResource context) {
		super(context);
	}
}