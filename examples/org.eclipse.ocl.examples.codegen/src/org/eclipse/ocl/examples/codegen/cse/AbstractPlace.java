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

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;

/**
 * The Place hierarchy is a sub-hierarchy of the CG hierarchy comprising only those nodes at which declarations may be introduced,
 * typically be a chain of LetExp.
 * <p>
 * An AbstractPlace describes a place where forests of CG trees may be placed for code generation. 
 * <p>A GlobalPlace describes the place for global constants and all outer stack places.
 * <p>A StackPlace describes a callable forest such as an Operation of Iteration.
 * <p>A ControlPlace describes a control dependency such as the Else place of an If.
 */
public abstract class AbstractPlace
{
	public static @NonNull ControlPlace getControlPlace(@NonNull AbstractPlace abstractPlace) {
		if (abstractPlace instanceof ControlPlace) {
			return (ControlPlace) abstractPlace;
		}
		else {
			throw new IllegalStateException("No ControlPlace for " + abstractPlace);
		}
	}

	/**
	 * Return the GlobalPlace.
	 */
	public abstract @NonNull GlobalPlace getGlobalPlace();

	/**
	 * Return the parent of this place, which is self for the GlobalPlace.
	 */
	public abstract @NonNull AbstractPlace getParentPlace();
	
	/**
	 * Return the stack place hosting this place. Returns for the GlobalPlace..
	 */
	public abstract @Nullable StackPlace getStackPlace();
	
	public abstract void printHierarchy(@NonNull Appendable appendable, @NonNull String indentation);
}