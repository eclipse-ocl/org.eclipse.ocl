/*******************************************************************************
 * Copyright (c) 2017 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.xtext.essentialocl.attributes;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.internal.scoping.EnvironmentView;
import org.eclipse.ocl.pivot.internal.scoping.ScopeView;
import org.eclipse.ocl.pivot.utilities.ParserContext;
import org.eclipse.ocl.xtext.base.attributes.PivotableElementCSAttribution;
import org.eclipse.ocl.xtext.base.utilities.BaseCSResource;

public class ContextCSAttribution extends PivotableElementCSAttribution
{
	public static final ContextCSAttribution INSTANCE = new ContextCSAttribution();

	@Override
	public @Nullable ScopeView computeLookup(@NonNull EObject target, @NonNull EnvironmentView environmentView, @NonNull ScopeView scopeView) {
		ScopeView computeLookup = super.computeLookup(target, environmentView, scopeView);
		//
		//	Last, last alternative. If nothing found give the ParserContext an opportunity to help out.
		//
		//	This may be used by for instance UMLX2QVTr to parse an OCL text snippet within its local ContextCS and then
		//	search further to th ancestry of the model in which the OCL text snippet belongs.
		//
		if (!environmentView.hasFinalResult() && ((computeLookup == null) || (computeLookup.getTarget() == null))) {
			Resource eResource = target.eResource();
			if (eResource instanceof BaseCSResource) {
				ParserContext parserContext = ((BaseCSResource)eResource).getParserContext();
				if (parserContext instanceof ParserContext.ParserContextExtension) {
					return ((ParserContext.ParserContextExtension)parserContext).computeLookup(target, environmentView, scopeView);
				}
			}
		}
		return computeLookup;
	}
}
