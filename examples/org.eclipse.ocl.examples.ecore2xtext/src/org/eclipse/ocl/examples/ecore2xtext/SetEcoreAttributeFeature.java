package org.eclipse.ocl.examples.ecore2xtext;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.jdt.annotation.NonNull;

public class SetEcoreAttributeFeature extends AbstractSetEcoreFeature<@NonNull EAttribute>
{
	protected final Object value;

	public SetEcoreAttributeFeature(@NonNull EAttribute eFeature, Object value) {
		super(eFeature);
		EDataType eAttributeType = eFeature.getEAttributeType();
		if ((eAttributeType == EcorePackage.Literals.EBOOLEAN) || (eAttributeType == EcorePackage.Literals.EBOOLEAN_OBJECT)) {
			this.value = Boolean.parseBoolean(AbstractEcore2XtextParser.unquote(value));
		}
		else if ((eAttributeType == EcorePackage.Literals.EBYTE) || (eAttributeType == EcorePackage.Literals.EBYTE_OBJECT)) {
			this.value = Byte.parseByte(AbstractEcore2XtextParser.unquote(value));
		}
		else if ((eAttributeType == EcorePackage.Literals.EDOUBLE) || (eAttributeType == EcorePackage.Literals.EDOUBLE_OBJECT)) {
			this.value = Double.parseDouble(AbstractEcore2XtextParser.unquote(value));
		}
		else if ((eAttributeType == EcorePackage.Literals.EFLOAT) || (eAttributeType == EcorePackage.Literals.EFLOAT_OBJECT)) {
			this.value = Float.parseFloat(AbstractEcore2XtextParser.unquote(value));
		}
		else if ((eAttributeType == EcorePackage.Literals.EINT) || (eAttributeType == EcorePackage.Literals.EINTEGER_OBJECT)) {
			this.value = Integer.parseInt(AbstractEcore2XtextParser.unquote(value));
		}
		else if ((eAttributeType == EcorePackage.Literals.ELONG) || (eAttributeType == EcorePackage.Literals.ELONG_OBJECT)) {
			this.value = Long.parseLong(AbstractEcore2XtextParser.unquote(value));
		}
		else if ((eAttributeType == EcorePackage.Literals.ESHORT) || (eAttributeType == EcorePackage.Literals.ESHORT_OBJECT)) {
			this.value = Short.parseShort(AbstractEcore2XtextParser.unquote(value));
		}
		else if (eAttributeType == EcorePackage.Literals.ESTRING) {
			this.value = AbstractEcore2XtextParser.unquote(value);
		}
		else {
			this.value = value;
		}
	}

	@Override
	public void setAttribute(@NonNull AbstractEcore2XtextParser parser, @NonNull EObject eInstance) {
		setFeature(eInstance, value);
	}

	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append(eFeature.getEContainingClass().getEPackage().getName() + "::" + eFeature.getEContainingClass().getName() + "::" + eFeature.getName() + "=");
		if (value instanceof SetAttributes) {
			((SetAttributes)value).toString(s, 1);
		}
		else {
			s.append(value);
		}
		return s.toString();
	}
}