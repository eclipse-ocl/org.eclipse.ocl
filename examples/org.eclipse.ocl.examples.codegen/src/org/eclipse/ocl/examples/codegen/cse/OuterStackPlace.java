/*******************************************************************************
 * Copyright (c) 2012, 2018 CEA LIST and others.
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

public class OuterStackPlace extends StackPlace
{
	public static @Nullable OuterStackPlace createOuterStackPlace(@NonNull Map<@Nullable CGElement, @NonNull AbstractPlace> element2place, @NonNull CGElement cgStackElement) {
		GlobalPlace globalPlace = getGlobalPlace(element2place);
		OuterStackPlace stackPlace = new OuterStackPlace(globalPlace, cgStackElement);
		return stackPlace;
	}

	protected OuterStackPlace(@NonNull GlobalPlace globalPlace, @NonNull CGElement stackElement) {
		super(globalPlace, stackElement);
		globalPlace.addStackPlace(this);
	}

	@Override
	public @NonNull GlobalPlace getParentPlace() {
		return globalPlace;
	}

	public void optimize() {
		//
		//	Push common SEs upwards (e.g. terms on both then and else of an IF).
		//
		pushUp();
		if (CommonSubexpressionEliminator.CSE_PUSH_UP.isActive()) {
			CommonSubexpressionEliminator.CSE_PUSH_UP.println("Places after push up");
			printHierarchy(CommonSubexpressionEliminator.CSE_PUSH_UP, "");
		}
		//
		//	Pull common SEs upwards (e.g. terms on just else of an IF already available in a parent).
		//
		pullUp();
		if (CommonSubexpressionEliminator.CSE_PULL_UP.isActive()) {
			CommonSubexpressionEliminator.CSE_PULL_UP.println("Places after pull up");
			printHierarchy(CommonSubexpressionEliminator.CSE_PULL_UP, "");
		}
		//
		//	Prune to eliminate single use CSEs, the secondary terms of selected CSEs.
		//
		prune();
		if (CommonSubexpressionEliminator.CSE_PRUNE.isActive()) {
			CommonSubexpressionEliminator.CSE_PRUNE.println("Places after prune");
			printHierarchy(CommonSubexpressionEliminator.CSE_PRUNE, "");
		}
		//
		//	Rewrite shared analyses as LetExps, VarExps.
		//
		rewrite();
		if (CommonSubexpressionEliminator.CSE_REWRITE.isActive()) {
			CommonSubexpressionEliminator.CSE_REWRITE.println("Places after rewrite");
			printHierarchy(CommonSubexpressionEliminator.CSE_REWRITE, "");
		}
	}
}