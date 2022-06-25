/*******************************************************************************
 * Copyright (c) 2022 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.codegen.java.types;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.ids.IdVisitor;

/**
 * A CGIdVisitor supports visting the additional CG ElementId to react according to the derived Element type.
 */
public interface CGIdVisitor<R> extends IdVisitor<R>
{
	R visitJavaTypeId(@NonNull JavaTypeId id);
}
