package org.eclipse.ocl.examples.ecore2xtext;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;

public abstract class SetAttribute
{
	public @Nullable EClass getXSIType() {
		return null;
	}

	public void setAttribute(@NonNull AbstractEcore2XtextParser parser, @NonNull EObject eInstance) {
		return;
	}

	public void toString(@NonNull StringBuilder s, int depth) {
		for (int i = 0; i < depth; i++) {
			s.append("  ");
		}
		s.append(toString());
	}
}