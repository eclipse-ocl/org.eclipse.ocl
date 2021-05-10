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

import java.util.List;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.CallExp;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.IfExp;
import org.eclipse.ocl.pivot.NavigationCallExp;
import org.eclipse.ocl.pivot.Operation;

/**
 * @since 1.15
 */
public interface CSEElement
{
	@NonNull Iterable<@NonNull CSEElement> getChildren();
	@NonNull CommonSubExpressionAnalysis getCommonSubExpressionAnalysis();
	@NonNull Element getElement();
	@NonNull CSEElement getIfCSE(@NonNull IfExp ifExp, @NonNull CSEElement thenCSE, @NonNull CSEElement elseCSE);
	@NonNull CSEElement getOperationCSE(@NonNull CallExp callExp, @NonNull Operation operation, @NonNull List<@Nullable CSEElement> argumentCSEs);
	@Nullable CSEElement getParent();
	@NonNull CSEElement getPropertyCSE(@NonNull NavigationCallExp navigationCallExp);
}
