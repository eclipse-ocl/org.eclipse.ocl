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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.cgmodel.CGCatchExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGElement;
import org.eclipse.ocl.examples.codegen.cgmodel.CGText;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;

/**
 * A CatchPlace describes either the body forest of CG trees for a catch expression.
 */
public class CatchPlace extends ControlPlace
{
	public static @NonNull LocalPlace createCatchPlace(@NonNull Map<@Nullable CGElement, @NonNull AbstractPlace> element2place, @NonNull CGCatchExp cgCatchExp) {
		ControlPlace catchPlace = ControlPlace.getControlPlace(element2place, cgCatchExp);
		CGValuedElement cgCaughtExp = cgCatchExp.getSource();
		if (cgCaughtExp != null) {
			CatchPlace caughtPlace = new CatchPlace(catchPlace, cgCaughtExp);
			element2place.put(cgCaughtExp, caughtPlace);
		}
		return catchPlace;
	}

	private CatchPlace(@NonNull LocalPlace catchPlace, @NonNull CGValuedElement cgCaughtExp) {
		super(catchPlace, cgCaughtExp);
	}

	@Override
	public void pushUp() {
		super.pushUp();
		HashedAnalyses mySet = getHashedAnalyses();
		ControlPlace parentPlace = getControlPlace(getParentPlace());
		List<@NonNull AbstractAnalysis> pushUps = null;
		for (@NonNull AbstractAnalysis commonAnalysis : mySet) {
			if (commonAnalysis.getPrimaryElement() instanceof CGText) {
				if (pushUps == null) {
					pushUps = new ArrayList<>();
				}
				pushUps.add(commonAnalysis);
			}
		}
		if (pushUps != null) {
			for (@NonNull AbstractAnalysis commonAnalysis : pushUps) {
				mySet.remove(commonAnalysis);
				parentPlace.addAnalysis(commonAnalysis);
			}
		}
	}
}