/*******************************************************************************
 * Copyright (c) 2013, 2014 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.xtext.essentialocl.cs2as;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.Variable;
import org.eclipse.ocl.xtext.basecs.ElementCS;

/**
 * An Iterator over the implicit source variables (most nested first).
 */
public class SourceVariableIterator extends AbstractSourceNamedElementIterator<Variable>
{
	public SourceVariableIterator(@NonNull ElementCS csElement) {
		super(csElement);
	}

	@Override
	protected void setNext(@NonNull Variable asVariable) {
		addNext(asVariable);
	}
}
