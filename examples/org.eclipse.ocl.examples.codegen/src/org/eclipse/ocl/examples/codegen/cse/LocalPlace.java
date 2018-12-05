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

import java.util.HashSet;
import java.util.Map;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.cgmodel.CGElement;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.pivot.utilities.ClassUtil;

/**
 * A LocalPlace describes a forest of CG trees that cannot be resolved as global constants.
 */
public abstract class LocalPlace extends AbstractPlace
{
	public static @NonNull AbstractPlace createLocalPlace(@NonNull Map<@Nullable CGElement, @NonNull AbstractPlace> element2place, @NonNull CGValuedElement cgElement) {
		boolean isGlobal = cgElement.isGlobal();
		if (isGlobal) {
			return ClassUtil.nonNullState(element2place.get(null));
		}
		else {
			return ControlPlace.createControlPlace(element2place, cgElement);
		}
	}

	protected static @NonNull GlobalPlace getGlobalPlace(@NonNull Map<@Nullable CGElement, @NonNull AbstractPlace> element2place) {
		AbstractPlace abstractPlace = element2place.get(null);
		if (abstractPlace instanceof GlobalPlace) {
			return (GlobalPlace) abstractPlace;
		}
		else {
			throw new IllegalStateException("No GlobalPlace");
		}
	}

	protected static @NonNull LocalPlace getLocalPlace(@NonNull Map<@Nullable CGElement, @NonNull AbstractPlace> element2place, @Nullable CGElement cgElement) {
		if (cgElement == null) {
			throw new IllegalStateException("No LocalPlace for null element");
		}
		AbstractPlace abstractPlace = element2place.get(cgElement);
		return getLocalPlace(abstractPlace, cgElement);
	}

	protected static @NonNull LocalPlace getLocalPlace(@Nullable AbstractPlace abstractPlace, @NonNull CGElement cgElement) {
		if (abstractPlace instanceof LocalPlace) {
			return (LocalPlace) abstractPlace;
		}
		else {
			throw new IllegalStateException("No LocalPlace for " + cgElement);
		}
	}

	protected final @NonNull GlobalPlace globalPlace;
	private /*@LazyNonNull*/ HashSet<@NonNull ControlPlace> controlPlaces = null;

	protected LocalPlace(@NonNull GlobalPlace globalPlace) {
		this.globalPlace = globalPlace;
	}

	public void addControlPlace(@NonNull ControlPlace controlPlace) {
		if (controlPlaces == null) {
			controlPlaces = new HashSet<>();
		}
		controlPlaces.add(controlPlace);
	}

	@Override
	public @NonNull GlobalPlace getGlobalPlace() {
		return globalPlace;
	}

	@Override
	public abstract @NonNull StackPlace getStackPlace();

	@Override
	public void printHierarchy(@NonNull Appendable appendable, @NonNull String indentation) {
		if (controlPlaces != null) {
			for (@NonNull ControlPlace controlPlace : controlPlaces) {
				controlPlace.printHierarchy(appendable, indentation);
			}
		}
	}

	/**
	 * Eliminate CSE candidates that are not shared and do not need to be CSEs. For the retained CSEs
	 * select the shallowest candidate as the actaul CSE.
	 */
	public void prune() {
		if (controlPlaces != null) {
			for (@NonNull ControlPlace controlPlace : controlPlaces) {
				controlPlace.prune();
			}
		}
	}

	/**
	 * Pull up all redundant child analyses that are visible in a parent to the parent.
	 */
	public void pullUp() {
		if (controlPlaces != null) {
			for (@NonNull ControlPlace controlPlace : controlPlaces) {
				controlPlace.pullUp();
			}
		}
	}

	/**
	 * Push shareable analyses up the place tree. e.g. something on both then and else arms of an if can be pushed up.
	 */
	public void pushUp() {
		if (controlPlaces != null) {
			for (@NonNull ControlPlace controlPlace : controlPlaces) {
				controlPlace.pushUp();
			}
		}
	}

	/**
	 * Rewrite the expression trees to exploit the CSEs.
	 */
	public void rewrite() {
		if (controlPlaces != null) {
			for (@NonNull ControlPlace controlPlace : controlPlaces) {
				controlPlace.rewrite();
			}
		}
	}
}