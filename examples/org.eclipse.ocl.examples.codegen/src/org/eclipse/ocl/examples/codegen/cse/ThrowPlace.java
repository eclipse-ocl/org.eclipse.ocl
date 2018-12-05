/*******************************************************************************
 * Copyright (c) 2013, 2018 CEA LIST and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink(CEA LIST) - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.codegen.cse;

import java.util.Map;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.cgmodel.CGElement;
import org.eclipse.ocl.examples.codegen.cgmodel.CGThrowExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;

/**
 * A ThrowPlace describes either the body forest of CG trees for a throw expression.
 */
public class ThrowPlace extends ControlPlace
{
	public static @NonNull LocalPlace createThrowPlace(@NonNull Map<@Nullable CGElement, @NonNull AbstractPlace> element2place, @NonNull CGThrowExp cgThrowExp) {
		ControlPlace throwPlace = ControlPlace.getControlPlace(element2place, cgThrowExp);
		CGValuedElement cgThrownExp = cgThrowExp.getSource();
		if (cgThrownExp != null) {
			ThrowPlace thrownPlace = new ThrowPlace(throwPlace, cgThrownExp);
			element2place.put(cgThrownExp, thrownPlace);
		}
		return throwPlace;
	}

	private ThrowPlace(@NonNull LocalPlace throwPlace, @NonNull CGValuedElement cgThrownExp) {
		super(throwPlace, cgThrownExp);
	}
}