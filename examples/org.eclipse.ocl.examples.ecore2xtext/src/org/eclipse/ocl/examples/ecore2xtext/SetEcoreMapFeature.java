package org.eclipse.ocl.examples.ecore2xtext;

import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jdt.annotation.NonNull;

public class SetEcoreMapFeature extends AbstractSetEcoreFeature<@NonNull EStructuralFeature>
{
	protected final Object key;
	protected final Object value;

	public SetEcoreMapFeature(@NonNull EStructuralFeature eFeature, Object value) {
		super(eFeature);
		SetMapAttributes setMapAttributes = (SetMapAttributes)value;
		this.key = setMapAttributes.getKey();
		this.value = setMapAttributes.getValue();
	}

	@Override
	public void setAttribute(@NonNull AbstractEcore2XtextParser parser, @NonNull EObject eInstance) {
		EMap<Object, Object> map = (EMap<Object,Object>)eInstance.eGet(eFeature);
		map.put(key, value);
	}

	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append(eFeature.getEContainingClass().getEPackage().getName() + "::" + eFeature.getEContainingClass().getName() + "::" + eFeature.getName() + "=");
		s.append(key  + "=>" + value);
		return s.toString();
	}
}