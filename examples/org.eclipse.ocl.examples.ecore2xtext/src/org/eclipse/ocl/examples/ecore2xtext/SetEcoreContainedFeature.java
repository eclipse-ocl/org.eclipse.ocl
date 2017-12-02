package org.eclipse.ocl.examples.ecore2xtext;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.jdt.annotation.NonNull;

public class SetEcoreContainedFeature extends AbstractSetEcoreFeature<@NonNull EReference>
{
	protected final @NonNull EObject eObject;

	public SetEcoreContainedFeature(@NonNull EReference eFeature, @NonNull EObject eObject) {
		super(eFeature);
		this.eObject = eObject;
	}

	@Override
	public void setAttribute(@NonNull AbstractEcore2XtextParser parser, @NonNull EObject eInstance) {
		setFeature(eInstance, eObject);
	}

	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append(eFeature.getEContainingClass().getEPackage().getName() + "::" + eFeature.getEContainingClass().getName() + "::" + eFeature.getName() + "=an ");
		s.append(eObject.eClass().getName());
		return s.toString();
	}
}