package org.eclipse.ocl.examples.ecore2xtext;

public class SetXMLNSAttribute extends SetAttribute
{
	protected final Object name;
	protected final Object value;

	public SetXMLNSAttribute(Object name, Object value) {
		this.name = name;
		this.value = value;
	}

	@Override
	public String toString() {
		return "xmlns:" + name + "=" + value;
	}
}