package org.eclipse.ocl.examples.ecore2xtext;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;

public class SetEcoreReferenceFeature extends AbstractSetEcoreFeature<@NonNull EReference>
{
	private @Nullable EObject eInstance;
	protected final String value;

	public SetEcoreReferenceFeature(@NonNull EReference eFeature, Object value) {
		super(eFeature);
		this.value = AbstractEcore2XtextParser.unquote(value);
	}

	public void resolveReference(@NonNull AbstractEcore2XtextParser parser) {
		String typeString = null;				// FIXME use as proxy type
		int endIndex = value.length();
		for (int startIndex = 0; startIndex < endIndex; ) {
			String thisString;
			int spaceIndex = value.indexOf(" ", startIndex);
			if (spaceIndex >= startIndex) {
				thisString = value.substring(startIndex, spaceIndex);
				startIndex = spaceIndex+1;
			}
			else {
				thisString = value.substring(startIndex);
				startIndex = endIndex;
			}
			int hashIndex = thisString.indexOf("#");
			if (hashIndex < 0) {
				typeString = thisString;
			}
			else {
				EObject eObject = parser.getEObject(hashIndex, thisString);
				setFeature(eInstance, eObject);
			}
		}
	}

	@Override
	public void setAttribute(@NonNull AbstractEcore2XtextParser parser, @NonNull EObject eInstance) {
		this.eInstance = eInstance;
		parser.addReference(this);
	}

	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append(eFeature.getEContainingClass().getEPackage().getName() + "::" + eFeature.getEContainingClass().getName() + "::" + eFeature.getName() + "=");
		//		if (value instanceof SetAttributes) {
		//			((SetAttributes)value).toString(s, 1);
		//		}
		//		else {
		s.append(value);
		//		}
		return s.toString();
	}
}