package org.eclipse.ocl.examples.ecore2xtext;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;

public class SetAttributes extends SetAttribute
{
	public static Object concatenate(Object firstElement, Object secondElement) {
		if (firstElement == null) {
			return secondElement;
		}
		else if (secondElement == null) {
			return firstElement;
		}
		else if (firstElement instanceof SetAttributes) {
			if (secondElement instanceof SetAttributes) {
				return ((SetAttributes)firstElement).appendAll((SetAttributes)secondElement);
			}
			else {
				return ((SetAttributes)firstElement).append((SetAttribute)secondElement);
			}
		}
		else {
			if (secondElement instanceof SetAttributes) {
				return ((SetAttributes)secondElement).prepend((SetAttribute)secondElement);
			}
			else {
				return new SetAttributes((SetAttribute)firstElement).append((SetAttribute)secondElement);
			}
		}
	}

	public static Object create(Object element) {
		return element != null ? new SetAttributes((SetAttribute)element) : null;
	}

	protected @NonNull List<@NonNull SetAttribute> elements = new ArrayList<>();

	public SetAttributes(@NonNull SetAttribute element) {
		append(element);
	}

	private SetAttributes append(SetAttribute element) {
		elements.add(element);
		return this;
	}

	private SetAttributes appendAll(SetAttributes sequence) {
		elements.addAll(sequence.elements);
		return this;
	}

	@Override
	public @Nullable EClass getXSIType() {
		for (@NonNull SetAttribute element : elements) {
			EClass xsiType = element.getXSIType();
			if (xsiType != null) {
				return xsiType;
			}
		}
		return null;
	}

	private SetAttributes prepend(SetAttribute element) {
		elements.add(0, element);
		return this;
	}

	@Override
	public void setAttribute(@NonNull AbstractEcore2XtextParser parser, @NonNull EObject eObject) {
		for (@NonNull SetAttribute element : elements) {
			element.setAttribute(parser, eObject);
		}
	}

	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		toString(s, 0);
		return s.toString();
	}

	@Override
	public void toString(@NonNull StringBuilder s, int depth) {
		s.append("{ ");
		for (@NonNull SetAttribute element : elements) {
			s.append("\n");
			for (int i = 0; i < depth; i++) {
				s.append("  ");
			}
			if (element instanceof SetAttributes) {
				((SetAttributes)element).toString(s, depth);
			}
			else {
				element.toString(s, depth+1);
			}
		}
		s.append("\n");
		for (int i = 0; i < depth; i++) {
			s.append("  ");
		}
		s.append("}");
	}
}
