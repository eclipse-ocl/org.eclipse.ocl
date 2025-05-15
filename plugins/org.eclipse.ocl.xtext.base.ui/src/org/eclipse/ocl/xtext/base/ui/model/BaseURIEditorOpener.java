/*******************************************************************************
 * Copyright (c) 2011, 2018 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.xtext.base.ui.model;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ui.IEditorPart;
import org.eclipse.xtext.ui.editor.GlobalURIEditorOpener;

public class BaseURIEditorOpener extends GlobalURIEditorOpener
{
	@Override
	public IEditorPart open(URI uri, boolean select) {
		if (uri != null) {
			URI trimFragment = ClassUtil.requireNonNull(uri.trimFragment());
			if (PivotUtil.isASURI(trimFragment)) {
				uri = PivotUtil.getNonASURI(trimFragment);		// FIXME map AST to CST URI too
			}
		}
		return super.open(uri, select);
	}

	@Override
	public IEditorPart open(URI referenceOwnerURI, EReference reference, int indexInList, boolean select) {
		if (referenceOwnerURI != null) {
			referenceOwnerURI = PivotUtil.isASURI(referenceOwnerURI) ? PivotUtil.getNonASURI(referenceOwnerURI) : referenceOwnerURI;
		}
		return super.open(referenceOwnerURI, reference, indexInList, select);
	}
}
