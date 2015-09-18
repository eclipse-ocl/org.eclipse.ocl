/*******************************************************************************
 * Copyright (c) 2010, 2014 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.internal.attributes;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.LambdaLiteralExp;
import org.eclipse.ocl.pivot.Parameter;
import org.eclipse.ocl.pivot.internal.scoping.AbstractAttribution;
import org.eclipse.ocl.pivot.internal.scoping.EnvironmentView;
import org.eclipse.ocl.pivot.internal.scoping.ScopeView;

public class LambdaLiteralExpAttribution extends AbstractAttribution
{
	public static final LambdaLiteralExpAttribution INSTANCE = new LambdaLiteralExpAttribution();

	@Override
	public ScopeView computeLookup(@NonNull EObject target, @NonNull EnvironmentView environmentView, @NonNull ScopeView scopeView) {
		LambdaLiteralExp targetExpression = (LambdaLiteralExp)target;
		List<Parameter> parameters = targetExpression.getOwnedParameters();
		if (parameters != null) {
			environmentView.addNamedElements(parameters);
		}
		return scopeView.getParent();
	}
}
