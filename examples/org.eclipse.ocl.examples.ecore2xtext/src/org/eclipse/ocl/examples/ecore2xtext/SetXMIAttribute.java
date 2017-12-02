package org.eclipse.ocl.examples.ecore2xtext;

public class SetXMIAttribute extends SetAttribute
{
	protected final Object name;
	protected final Object value;

	public SetXMIAttribute(Object name, Object value) {
		this.name = name;
		this.value = value;
	}

	@Override
	public String toString() {
		return "xmi:" + name + "=" + value;
	}
}