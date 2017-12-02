package org.eclipse.ocl.examples.ecore2xtext;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;

public class SetXSITypeAttribute extends SetAttribute
{
	protected final EClass eClass;

	public SetXSITypeAttribute(@NonNull EClass eClass) {
		this.eClass = eClass;
	}

	@Override
	public @Nullable EClass getXSIType() {
		return eClass;
	}

	@Override
	public String toString() {
		return "xsi:type='" + eClass.getEPackage().getNsURI() + "'::" + eClass.getName();
	}
}