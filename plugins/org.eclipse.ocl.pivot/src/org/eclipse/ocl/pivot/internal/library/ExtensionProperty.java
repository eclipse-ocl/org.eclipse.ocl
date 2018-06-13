/*******************************************************************************
 * Copyright (c) 2011, 2018 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.internal.library;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.ElementExtension;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.Stereotype;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.evaluation.Executor;
import org.eclipse.ocl.pivot.ids.CollectionTypeId;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal.EnvironmentFactoryInternalExtension;
import org.eclipse.ocl.pivot.library.AbstractProperty;
import org.eclipse.ocl.pivot.utilities.ParserException;
import org.eclipse.ocl.pivot.utilities.ValueUtil;
import org.eclipse.ocl.pivot.values.InvalidValueException;

/**
 * The static instance of ExplicitNavigationProperty supports evaluation of
 * a property call that navigates a relationship.
 */
public class ExtensionProperty extends AbstractProperty
{
	protected final @NonNull Property property;

	public ExtensionProperty(@NonNull Property property) {
		this.property = property;
	}

	@Override
	public @Nullable Object evaluate(@NonNull Executor executor, @NonNull TypeId returnTypeId, @Nullable Object sourceValue) {
		Type staticType = property.getType();
		if (staticType == null) {
			return null;
		}
		Element element = null;
		if (sourceValue instanceof Element) {
			element = (Element)sourceValue;
		}
		else {
			try {
				element = ((EnvironmentFactoryInternalExtension)executor.getEnvironmentFactory()).getASOf(Element.class, (EObject)sourceValue);
			} catch (ParserException e) {
				return new InvalidValueException(e, "Failed to parse " + property);
			}
		}
		if (element != null) {
			List<ElementExtension> selectedExtensions = null;
			for (ElementExtension elementExtension : element.getOwnedExtensions()) {
				Stereotype dynamicStereotype = elementExtension.getStereotype();
				if (dynamicStereotype.conformsTo(executor.getStandardLibrary(), staticType)) {
					if (selectedExtensions == null) {
						selectedExtensions = new ArrayList<ElementExtension>();
					}
					selectedExtensions.add(elementExtension);
				}
			}
			if (selectedExtensions == null) {
				return null;
			}
			TypeId typeId = property.getTypeId();
			if (typeId instanceof CollectionTypeId) {
				return ValueUtil.createSetValue((CollectionTypeId) typeId, selectedExtensions);
			}
			else if (selectedExtensions.size() == 1) {
				return selectedExtensions.get(0);
			}
			else {
				return new InvalidValueException("Multiple applied stereotypes for " + property);
			}
		}
		return staticType;
	}
}