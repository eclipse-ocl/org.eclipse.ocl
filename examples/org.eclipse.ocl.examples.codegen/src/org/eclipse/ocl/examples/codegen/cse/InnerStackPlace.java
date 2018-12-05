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
import org.eclipse.ocl.examples.codegen.cgmodel.CGIterationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;

/**
 * An InnerStackPlace describes either the body forest of CG trees for a loop expression.
 */
public class InnerStackPlace extends StackPlace
{
	public static @NonNull LocalPlace createInnerStackPlace(@NonNull Map<@Nullable CGElement, @NonNull AbstractPlace> element2place, @NonNull CGIterationCallExp cgLoopExp) {
		ControlPlace loopPlace = ControlPlace.getControlPlace(element2place, cgLoopExp);
		CGValuedElement cgBodyExp = cgLoopExp.getBody();
		if (cgBodyExp != null) {
			InnerStackPlace innerPlace = new InnerStackPlace(loopPlace, cgBodyExp);
			ControlPlace bodyPlace = new ControlPlace(innerPlace, cgBodyExp);
			element2place.put(cgBodyExp, bodyPlace);
		}
		return loopPlace;
	}

	protected final @NonNull ControlPlace parentPlace;

	private InnerStackPlace(@NonNull ControlPlace parentPlace, @NonNull CGValuedElement cgBodyExp) {
		super(parentPlace.getGlobalPlace(), cgBodyExp);
		this.parentPlace = parentPlace;
		parentPlace.getStackPlace().addStackPlace(this);
	}

	@Override
	public @NonNull ControlPlace getParentPlace() {
		return parentPlace;
	}
}