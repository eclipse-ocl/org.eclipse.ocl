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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.NamedElement;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.Nameable;

/**
 * An AssociationHelper maintains a lazy cache of Property opposite pairs.
 */
@Deprecated
public class AssociationHelper
{
	public static class Association implements Nameable
	{
		protected final @NonNull Property first;
		protected final @NonNull Property second;
		private final @NonNull String name;

		public Association(@NonNull Property first, @NonNull String firstName, @NonNull Property second, @NonNull String secondName) {
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

	private final @NonNull Map<@NonNull Property, @NonNull Association> property2association = new HashMap<>();

	public @Nullable Association add(@NonNull Property asProperty) {
		if (asProperty.isIsComposite()) {
			return null;
		}
		Property asOpposite = asProperty.getOpposite();
		if (asOpposite == null) {
			return null;
		}
		if (asOpposite.isIsComposite()) {
			return null;
		}
		Association asAssociation;
		String n1 = getQualifiedName(new StringBuilder(), asProperty);
		String n2 = getQualifiedName(new StringBuilder(), asOpposite);
		int diff = n1.compareTo(n2);
		if (diff > 0) {			// 0 is eRef
			asAssociation = new Association(asOpposite, n2, asProperty, n1);
		}
		else if (diff == 0) {
			throw new UnsupportedOperationException("Unexpected self-reference " + n1);	// Ecore doesn't support this case
		}
		else {
			asAssociation = new Association(asProperty, n1, asOpposite, n2);
		}
		property2association.put(asProperty, asAssociation);
		property2association.put(asOpposite, asAssociation);
		return asAssociation;
	}

	public @Nullable Association get(@NonNull Property asProperty) {
		return property2association.get(asProperty);
	}

	private static @NonNull String getQualifiedName(@NonNull StringBuilder s, @NonNull NamedElement asNamedElement) {
		EObject eContainer = asNamedElement.eContainer();
		if (eContainer instanceof NamedElement) {
			getQualifiedName(s, (NamedElement)eContainer);
			s.append("::");
		}
		s.append(asNamedElement.getName());
		return s.toString();
	}

	@Override
	public @NonNull String toString() {
		List<@NonNull Association> asAssociations = new ArrayList<>(new HashSet<>(property2association.values()));
		StringBuilder s = new StringBuilder();
		boolean isFirst = true;
		Collections.sort(asAssociations, NameUtil.NAMEABLE_COMPARATOR);
		for (@NonNull Association asAssociation : asAssociations) {
			if (!isFirst) {
				s.append("\n");
			}
			s.append(asAssociation);
			isFirst = false;
		}
		return s.toString();
	}
}
