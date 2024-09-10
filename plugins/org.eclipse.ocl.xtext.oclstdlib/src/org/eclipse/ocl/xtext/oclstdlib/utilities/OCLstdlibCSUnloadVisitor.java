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
package org.eclipse.ocl.xtext.oclstdlib.utilities;

import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.Precedence;
import org.eclipse.ocl.pivot.resource.CSResource;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.xtext.oclstdlibcs.LibOperationCS;
import org.eclipse.ocl.xtext.oclstdlibcs.util.AbstractOCLstdlibCSUnloadVisitor;

public class OCLstdlibCSUnloadVisitor extends AbstractOCLstdlibCSUnloadVisitor
{
	public OCLstdlibCSUnloadVisitor(@NonNull CSResource context) {
		super(context);
	}

	@Override
	public @Nullable Object visitLibOperationCS(@NonNull LibOperationCS csLibOperation) {
		Precedence asPrecedence = csLibOperation.getPrecedence();
		if (asPrecedence != null) {
			Precedence asProxy = getProxy(asPrecedence);
			System.out.println(NameUtil.debugSimpleName(csLibOperation) + ".referredElement => " + NameUtil.debugSimpleName(asProxy) + " " + EcoreUtil.getURI(asProxy));
			csLibOperation.setPrecedence(asProxy);
		}
		return super.visitLibOperationCS(csLibOperation);
	}
}