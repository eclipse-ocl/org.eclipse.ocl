/**
 * <copyright>
 * 
 * Copyright (c) 2013 CEA LIST and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *   E.D.Willink(CEA LIST) - Initial API and implementation
 * 
 * </copyright>
 */
package org.eclipse.ocl.examples.codegen.cse;

import java.util.Map;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.examples.codegen.cgmodel.CGElement;
import org.eclipse.ocl.examples.codegen.cgmodel.CGLetExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;

/**
 * LetPlaces manages an InPlace and an InitPlace for the in and init forests of CG trees
 * for a Let expression.
 * <p>
 * They do not yet support hoisting.
 */
public class LetPlaces
{
	public static @NonNull LocalPlace createPlaces(@NonNull Map<CGElement, AbstractPlace> element2place, @NonNull CGLetExp cgLetExp) {
		ControlPlace letPlace = ControlPlace.createPlace(element2place, cgLetExp);
		CGValuedElement cgInExp = cgLetExp.getIn();
		CGValuedElement cgInitExp = cgLetExp.getInit();
		if ((cgInExp != null) && (cgInitExp != null)) {
			InPlace inPlace = new InPlace(letPlace, cgInExp, cgInitExp);
			InitPlace initPlace = inPlace.initPlace;
			element2place.put(cgInExp, inPlace);
			element2place.put(cgInitExp, initPlace);
		}
		return letPlace;
	}
	
	/**
	 * A InPlace describes the in forest of CG trees for a Let expression.
	 */
	public static class InPlace extends ControlPlace
	{
		protected final @NonNull InitPlace initPlace;
		
		private InPlace(@NonNull LocalPlace letPlace, @NonNull CGValuedElement cgThenExp, @NonNull CGValuedElement cgInitExp) {
			super(letPlace, cgThenExp);
			initPlace = new InitPlace(letPlace, this, cgInitExp);
		}
	}
	
	/**
	 * An InitPlace describes the init forest of CG trees for a Let expression.
	 */
	public static class InitPlace extends ControlPlace
	{
		protected final @NonNull InPlace inPlace;
		
		private InitPlace(@NonNull LocalPlace letPlace, @NonNull InPlace inPlace, @NonNull CGValuedElement cgInitExp) {
			super(letPlace, cgInitExp);
			this.inPlace = inPlace;
		}
	}
}