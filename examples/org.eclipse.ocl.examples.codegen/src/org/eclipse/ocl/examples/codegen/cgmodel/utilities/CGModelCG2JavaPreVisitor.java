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
package org.eclipse.ocl.examples.codegen.cgmodel.utilities;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.examples.codegen.java.CG2JavaPreVisitor;
import org.eclipse.ocl.examples.codegen.java.JavaCodeGenerator;

/**
 * @noextend This class is not intended to be subclassed by clients. It is part of the hierarchy for auto-generated visitors.
 */
public class CGModelCG2JavaPreVisitor extends CG2JavaPreVisitor
{
	public CGModelCG2JavaPreVisitor(@NonNull JavaCodeGenerator codeGenerator) {
		super(codeGenerator);
	}

}
