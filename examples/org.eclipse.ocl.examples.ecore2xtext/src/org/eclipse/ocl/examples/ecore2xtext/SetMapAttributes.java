package org.eclipse.ocl.examples.ecore2xtext;

import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.jdt.annotation.NonNull;

public class SetMapAttributes extends SetAttributes
{
	public static SetMapAttributes create(Object element) {
		return new SetMapAttributes((SetAttribute) element);
	}

	public SetMapAttributes(@NonNull SetAttribute element) {
		super(element);
	}

	public Object getKey() {
		for (@NonNull SetAttribute element : elements) {
			if (element instanceof SetEcoreAttributeFeature) {
				SetEcoreAttributeFeature setEcoreFeature = (SetEcoreAttributeFeature)element;
				if (setEcoreFeature.eFeature == EcorePackage.Literals.ESTRING_TO_STRING_MAP_ENTRY__KEY) {
					return setEcoreFeature.value;
				}
			}
		}
		return null;
	}

	public Object getValue() {
		for (@NonNull SetAttribute element : elements) {
			if (element instanceof SetEcoreAttributeFeature) {
				SetEcoreAttributeFeature setEcoreFeature = (SetEcoreAttributeFeature)element;
				if (setEcoreFeature.eFeature == EcorePackage.Literals.ESTRING_TO_STRING_MAP_ENTRY__VALUE) {
					return setEcoreFeature.value;
				}
			}
		}
		return null;
	}
}
