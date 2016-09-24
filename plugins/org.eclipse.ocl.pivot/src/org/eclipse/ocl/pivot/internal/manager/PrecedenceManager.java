/*******************************************************************************
 * Copyright (c) 2011, 2016 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.internal.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.Library;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.Parameter;
import org.eclipse.ocl.pivot.PivotFactory;
import org.eclipse.ocl.pivot.Precedence;
import org.eclipse.ocl.pivot.internal.utilities.PivotUtilInternal;
import org.eclipse.ocl.pivot.utilities.ClassUtil;

/**
 * PrecedenceManager encapsulates the knowledge about known precedences.
 */
public class PrecedenceManager
{

	public static @NonNull Precedence NULL_PRECEDENCE = PivotFactory.eINSTANCE.createPrecedence();
	public static @NonNull Precedence NAVIGATION_PRECEDENCE = PivotFactory.eINSTANCE.createPrecedence();
	public static @NonNull Precedence LEAF_PRECEDENCE = PivotFactory.eINSTANCE.createPrecedence();
	static {
		NULL_PRECEDENCE.setName("NULL");
		NULL_PRECEDENCE.setOrder(Integer.MAX_VALUE/2);			// Small enough to avoid wrap around during comparison
		NAVIGATION_PRECEDENCE.setName("NAVIGATION");
		NAVIGATION_PRECEDENCE.setOrder(Integer.valueOf(-1));
		LEAF_PRECEDENCE.setName("LEAF");
		LEAF_PRECEDENCE.setOrder(Integer.valueOf(-2));
	}

	/**
	 * Map of precedence name to precedence objects. Multiple precedence objects
	 * may be associated with a single name since alternate contributions
	 * provide independent lists that must be successfully interleaved so that
	 * all same-named precedence objects get the same compiled ordering.
	 * <p>
	 * e.g. <tt> precedence A B D</tt> and <tt>precedence B C D</tt> merge to
	 * <tt>A B C D</tt> with duplicate precedence objects for B and D.
	 */
	private Map<@NonNull String, @NonNull List<Precedence>> nameToPrecedencesMap = null;

	private Map<@NonNull String, String> infixToPrecedenceNameMap = null;

	private Map<@NonNull String, @NonNull String> prefixToPrecedenceNameMap = null;

	/**
	 * Interleave the ownedPrecedences of the rootPackages to establish a merged
	 * ordering and assign the index in that ordering to each
	 * rootPackages.ownedPrecedences. Any inconsistent ordering and
	 * associativity is diagnosed.
	 */
	public @NonNull List<@NonNull String> compilePrecedences(@NonNull Iterable<@NonNull ? extends Library> libraries) {
		List<@NonNull String> errors = new ArrayList<>();
		List<@NonNull String> orderedPrecedences = new ArrayList<>();
		nameToPrecedencesMap = new HashMap<>();
		infixToPrecedenceNameMap = new HashMap<>();
		prefixToPrecedenceNameMap = new HashMap<>();
		for (@NonNull Library library : libraries) {
			List<@NonNull Precedence> precedences = ClassUtil.nullFree(library.getOwnedPrecedences());
			if (precedences.size() > 0) {
				compilePrecedencePackage(errors, library);
				int prevIndex = -1;
				List<Precedence> list = null;
				String name = null;
				for (@NonNull Precedence precedence : precedences) {
					name = precedence.getName();
					assert name != null;
					int index = orderedPrecedences.indexOf(name);
					if (index < 0) {
						index = prevIndex < 0 ? orderedPrecedences.size() : prevIndex + 1;
						orderedPrecedences.add(index, name);
						list = new ArrayList<>();
						nameToPrecedencesMap.put(name, list);
					} else {
						list = nameToPrecedencesMap.get(name);
						assert list != null;
						if (index <= prevIndex) {
							errors.add("Inconsistent precedence ordering for '" + name + "'");
						} else if ((prevIndex >= 0) && (index != prevIndex + 1)) {
							errors.add("Ambiguous precedence ordering for '" + name + "'");
						}
						if (precedence.getAssociativity() != list.get(0).getAssociativity()) {
							errors.add("Inconsistent precedence associativity for '" + name + "'");
						}
					}
					prevIndex = index;
					list.add(precedence);
				}
				if ((list != null) && (list.size() == 1) && (prevIndex != orderedPrecedences.size() - 1)) {
					errors.add("Ambiguous precedence ordering for '" + name + "' at tail");
				}
			}
		}
		for (int i = 0; i < orderedPrecedences.size(); i++) {
			String name = orderedPrecedences.get(i);
			List<Precedence> precedences = nameToPrecedencesMap.get(name);
			assert precedences != null;
			for (Precedence precedence : precedences) {
				precedence.setOrder(i);
			}
		}
		return errors;
	}

	protected void compilePrecedenceOperation(@NonNull List<@NonNull String> errors, @NonNull Operation operation) {
		Precedence precedence = operation.getPrecedence();
		if (precedence != null) {
			List<@NonNull Parameter> parameters = ClassUtil.nullFree(operation.getOwnedParameters());
			if (parameters.size() == 0) {
				String newName = precedence.getName();
				String operatorName = operation.getName();
				assert (newName != null) && (operatorName != null);
				String oldName = prefixToPrecedenceNameMap.put(operatorName, newName);
				if ((oldName != null) && !oldName.equals(newName)) {
					errors.add("Conflicting precedences for prefix operation '" + operatorName + "'");
				}
			} else if (parameters.size() == 1) {
				String newName = precedence.getName();
				String operatorName = operation.getName();
				assert (newName != null) && (operatorName != null);
				String oldName = infixToPrecedenceNameMap.put(operatorName, newName);
				if ((oldName != null) && !oldName.equals(newName)) {
					errors.add("Conflicting precedences for infix operation '" + operatorName + "'");
				}
			}
		}
	}

	protected void compilePrecedencePackage(@NonNull List<@NonNull String> errors, @NonNull Library library) {
		//		for (org.eclipse.ocl.pivot.Package nestedPackage : pivotPackage.getNestedPackage()) {
		//			compilePrecedencePackage(errors, nestedPackage);
		//		}
		for (org.eclipse.ocl.pivot.Class type : library.getOwnedClasses()) {
			if ((type != null) && PivotUtilInternal.isLibraryType(type)) {
				compilePrecedenceType(errors, type);
			}
		}
	}

	protected void compilePrecedenceType(@NonNull List<String> errors, org.eclipse.ocl.pivot.@NonNull Class pivotType) {
		for (Operation operation : pivotType.getOwnedOperations()) {
			if (operation != null) {
				compilePrecedenceOperation(errors, operation);
			}
		}
	}

	public void dispose() {
		nameToPrecedencesMap = null;
		infixToPrecedenceNameMap = null;
		prefixToPrecedenceNameMap = null;
	}

	public @Nullable Precedence getInfixPrecedence(@NonNull String operatorName) {
		String precedenceName = infixToPrecedenceNameMap.get(operatorName);
		if (precedenceName == null) {
			return null;
		}
		List<Precedence> precedences = nameToPrecedencesMap.get(precedenceName);
		if (precedences == null) {
			return null;
		}
		return precedences.get(0);
	}

	public @Nullable Precedence getPrefixPrecedence(@NonNull String operatorName) {
		String precedenceName = prefixToPrecedenceNameMap.get(operatorName);
		if (precedenceName == null) {
			return null;
		}
		List<Precedence> precedences = nameToPrecedencesMap.get(precedenceName);
		if (precedences == null) {
			return null;
		}
		return precedences.get(0);
	}
}