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
import org.eclipse.ocl.pivot.TypeExp;
import org.eclipse.ocl.pivot.ids.TypeId;

/**
 * @since 1.15
 */
public class CSETypeElement extends AbstractCSEElement<@NonNull TypeExp>
{
	protected final @NonNull TypeId typeId;

	public CSETypeElement(@NonNull CommonSubExpressionAnalysis cseAnalysis, @NonNull TypeExp typeExp) {
		super(cseAnalysis, typeExp, 0);
		this.typeId = typeExp.getTypeId();
	}
}
