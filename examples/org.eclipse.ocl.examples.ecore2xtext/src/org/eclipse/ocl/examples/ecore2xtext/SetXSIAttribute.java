package org.eclipse.ocl.examples.ecore2xtext;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.jdt.annotation.Nullable;

public class SetXSIAttribute extends SetAttribute
{
	protected final Object name;
	protected final Object value;

	public SetXSIAttribute(Object name, Object value) {
		this.name = name;
		this.value = value;
	}

	@Override
	public @Nullable EClass getXSIType() {
		return (EClass) value;
	}

	@Override
	public String toString() {
		return "xsi:" + name + "=" + value;
	}
}