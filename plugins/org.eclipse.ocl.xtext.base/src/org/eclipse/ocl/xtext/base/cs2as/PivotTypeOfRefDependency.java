/*******************************************************************************
 * Copyright (c) 2010, 2014 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.xtext.base.cs2as;

import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.TypedElement;
import org.eclipse.ocl.xtext.basecs.TypedRefCS;
import org.eclipse.ocl.xtext.basecs.PathTypeCS;

public class PivotTypeOfRefDependency extends AbstractDependency<TypedRefCS>
{
	public PivotTypeOfRefDependency(TypedRefCS csElement) {
		super(csElement);
	}

	@Override
	public boolean canExecute() {
		if (element instanceof PathTypeCS) {
			Element type = ((PathTypeCS)element).getPivot();
			return type != null;
		}
		Element pivot = element.getPivot();
		if (pivot == null) {
			return false;
		}
		Type type = ((TypedElement)pivot).getType();
		return type != null;
	}
}