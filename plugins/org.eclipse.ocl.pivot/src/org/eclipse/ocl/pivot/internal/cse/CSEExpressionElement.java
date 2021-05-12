/**
 * Copyright (c) 2021 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 */
package org.eclipse.ocl.pivot.internal.cse;

import org.eclipse.jdt.annotation.NonNull;

public class CSEExpressionElement extends AbstractCSEElement
{
	public CSEExpressionElement(@NonNull AbstractCSEElement parent) {
		super(parent);
	}

	@Override
	public void toString(@NonNull StringBuilder s, int lengthLimit) {
/*		s.append(typeId);
		s.append("[");
		s.append(mayBeNull ? "?" : "1");
		if (mayBeInvalid) {
			s.append("!");
		}
		s.append("]"); */
	}
}
