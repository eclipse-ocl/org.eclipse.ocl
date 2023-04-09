/*******************************************************************************
 * Copyright (c) 2023 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - initial API and implementation
*******************************************************************************/
package org.eclipse.ocl.pivot.merge;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.Nameable;

/**
 * An EAssociationHelper maintains a lazy cache of EReference opposite pairs.
 */
public class EAssociationHelper
{
	public static class EAssociation implements Nameable
	{
		protected final @NonNull EReference first;
		protected final @Nullable EReference second;
		private final @NonNull String name;

		public EAssociation(@NonNull EReference first, @NonNull String firstName, @Nullable EReference second, @NonNull String secondName) {
			this.first = first;
			this.second = second;
			this.name = firstName + " <> " + secondName;
		}

		@Override
		public @NonNull String getName() {
			return name;
		}

		public boolean isFirst(@NonNull EReference eReference) {
			return eReference == first;
		}

		@Override
		public @NonNull String toString() {
			return name;
		}
	}

	private final @NonNull Map<@NonNull EReference, @NonNull EAssociation> eReference2eAssociation = new HashMap<>();

	public @Nullable EAssociation add(@NonNull EReference eReference) {
		// XXX detect/avoid multiple invocation
		if (eReference.isContainer() || eReference.isContainment()) {
			return null;
		}
		EReference eOpposite = eReference.getEOpposite();
		if (eOpposite == null) {
			if (eReference.isMany()) {
				return null;
			}
			if (eReference.getEContainingClass() != eReference.getEReferenceType()) {
				return null;
			}
			getClass();			// XXX
		//	return null;
		}
		EAssociation eAssociation = eReference2eAssociation.get(eReference);
		if (eAssociation != null) {
			return eAssociation;
		}
		String n1 = getQualifiedName(new StringBuilder(), eReference);
		String n2;
		if (eOpposite != null) {
			n2 = getQualifiedName(new StringBuilder(), eOpposite);
			int diff = n1.compareTo(n2);
			if (diff > 0) {			// 0 is eRef
				eAssociation = new EAssociation(eOpposite, n2, eReference, n1);
			}
			else if (diff == 0) {
				throw new UnsupportedOperationException("Unexpected self-reference " + n1);	// Ecore doesn't support this case
			}
		}
		else {
			n2 = "";
		}
		if (eAssociation == null) {
			eAssociation = new EAssociation(eReference, n1, eOpposite, n2);
		}
		eReference2eAssociation.put(eReference, eAssociation);
		if (eOpposite != null) {
			eReference2eAssociation.put(eOpposite, eAssociation);
		}
		return eAssociation;
	}

	public @Nullable EAssociation get(@NonNull EReference eReference) {
		return eReference2eAssociation.get(eReference);
	}

	private static @NonNull String getQualifiedName(@NonNull StringBuilder s, @NonNull ENamedElement eNamedElement) {
		EObject eContainer = eNamedElement.eContainer();
		if (eContainer instanceof ENamedElement) {
			getQualifiedName(s, (ENamedElement)eContainer);
			s.append("::");
		}
		s.append(eNamedElement.getName());
		return s.toString();
	}

	@Override
	public @NonNull String toString() {
		List<@NonNull EAssociation> eAssociations = new ArrayList<>(new HashSet<>(eReference2eAssociation.values()));
		StringBuilder s = new StringBuilder();
		boolean isFirst = true;
		Collections.sort(eAssociations, NameUtil.NAMEABLE_COMPARATOR);
		for (@NonNull EAssociation eAssociation : eAssociations) {
			if (!isFirst) {
				s.append("\n");
			}
			s.append(eAssociation);
			isFirst = false;
		}
		return s.toString();
	}
}
