/*******************************************************************************
 * Copyright (c) 2010, 2014 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.xtext.oclstdlib.cs2as;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.xtext.base.cs2as.CS2ASConversion;
import org.eclipse.ocl.xtext.base.cs2as.Continuation;
import org.eclipse.ocl.xtext.oclstdlibcs.LibPropertyCS;
import org.eclipse.ocl.xtext.oclstdlibcs.PrecedenceCS;
import org.eclipse.ocl.xtext.oclstdlibcs.util.AbstractOCLstdlibCSPostOrderVisitor;

public class OCLstdlibCSPostOrderVisitor extends AbstractOCLstdlibCSPostOrderVisitor
{		
	public OCLstdlibCSPostOrderVisitor(@NonNull CS2ASConversion context) {
		super(context);
	}

	@Override
	public @Nullable
	Continuation<?> visitLibPropertyCS(@NonNull LibPropertyCS csElement) {
		Continuation<?> continuation = super.visitLibPropertyCS(csElement);
		Property pivotElement = PivotUtil.getPivot(Property.class, csElement);
		if (pivotElement != null) {
			pivotElement.setOpposite(null);
			metamodelManager.installPropertyDeclaration(pivotElement);
		}
		return continuation;
	}

	@Override
	public Continuation<?> visitPrecedenceCS(@NonNull PrecedenceCS csPrecedence) {
		return null;
	}
}