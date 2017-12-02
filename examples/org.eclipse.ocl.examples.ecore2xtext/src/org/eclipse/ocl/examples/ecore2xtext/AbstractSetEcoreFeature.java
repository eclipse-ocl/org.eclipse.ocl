package org.eclipse.ocl.examples.ecore2xtext;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jdt.annotation.NonNull;

public class AbstractSetEcoreFeature<ESF extends EStructuralFeature> extends SetAttribute
{
	protected final @NonNull ESF eFeature;

	protected AbstractSetEcoreFeature(@NonNull ESF eFeature) {
		this.eFeature = eFeature;
	}

	protected void setFeature(@NonNull EObject eInstance, Object value) {
		if (!eFeature.isMany()) {
			eInstance.eSet(eFeature, value);
		}
		else {
			((List<Object>)eInstance.eGet(eFeature)).add(value);
		}
	}
}