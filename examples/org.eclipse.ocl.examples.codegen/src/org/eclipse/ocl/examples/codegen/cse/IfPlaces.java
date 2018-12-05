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
import org.eclipse.ocl.examples.codegen.cgmodel.CGIfExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;

/**
 * IfPlaces manages a ThenPlace and an ElsePlace for the then and else forests of CG trees
 * for an IF expression.
 * <p>
 * They support hoisting a CSE that appears on both then and else arms of an expression.
 */
public class IfPlaces
{
	public static @NonNull LocalPlace createIfPlaces(@NonNull Map<@Nullable CGElement, @NonNull AbstractPlace> element2place, @NonNull CGIfExp cgIfExp) {
		ControlPlace ifPlace = ControlPlace.getControlPlace(element2place, cgIfExp);
		CGValuedElement cgThenExp = cgIfExp.getThenExpression();
		CGValuedElement cgElseExp = cgIfExp.getElseExpression();
		if ((cgThenExp != null) && (cgElseExp != null)) {
			ThenPlace thenPlace = new ThenPlace(ifPlace, cgThenExp, cgElseExp);
			ElsePlace elsePlace = thenPlace.elsePlace;
			element2place.put(cgThenExp, thenPlace);
			element2place.put(cgElseExp, elsePlace);
		}
		return ifPlace;
	}

	/**
	 * A ThenPlace describes the then forest of CG trees for an IF expression.
	 */
	public static class ThenPlace extends ControlPlace
	{
		protected final @NonNull ElsePlace elsePlace;

		private ThenPlace(@NonNull LocalPlace ifPlace, @NonNull CGValuedElement cgThenExp, @NonNull CGValuedElement cgElseExp) {
			super(ifPlace, cgThenExp);
			elsePlace = new ElsePlace(ifPlace, this, cgElseExp);
		}
	}

	/**
	 * An ElsePlace describes the else forest of CG trees for an IF expression.
	 */
	public static class ElsePlace extends ControlPlace
	{
		protected final @NonNull ThenPlace thenPlace;

		private ElsePlace(@NonNull LocalPlace ifPlace, @NonNull ThenPlace thenPlace, @NonNull CGValuedElement cgElseExp) {
			super(ifPlace, cgElseExp);
			this.thenPlace = thenPlace;
		}

		@Override
		public void pushUp() {
			super.pushUp();
			thenPlace.pushUp();
			HashedAnalyses thenSet = thenPlace.getHashedAnalyses();
			HashedAnalyses elseSet = getHashedAnalyses();
			HashedAnalyses intersection = HashedAnalyses.intersection(thenSet, elseSet);
			if (intersection != null) {
				ControlPlace parentPlace = getControlPlace(getParentPlace());
				for (@NonNull AbstractAnalysis commonAnalysis : intersection) {
					AbstractAnalysis thenAnalysis = thenSet.remove(commonAnalysis);
					AbstractAnalysis elseAnalysis = elseSet.remove(commonAnalysis);
					if ((thenAnalysis != null) &&  (elseAnalysis != null)) {
						parentPlace.addAnalysis(thenAnalysis);
						parentPlace.addAnalysis(elseAnalysis);
					}
					else {
						assert (thenAnalysis == null) && (elseAnalysis == null);	// Repeated for other half of intersection
					}
				}
			}
		}
	}
}