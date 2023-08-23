/*******************************************************************************
 * Copyright (c) 2010, 2020 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 * E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.utilities;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.internal.utilities.PivotConstantsInternal;
import org.eclipse.ocl.pivot.labels.ILabelGenerator;
import org.eclipse.ocl.pivot.util.DerivedConstants;

public class NameUtil
{
	public static final class NameableComparator implements Comparator<Nameable>
	{
		public static final @NonNull NameableComparator INSTANCE = new NameableComparator();

		@Override
		public int compare(Nameable o1, Nameable o2) {
			String n1 = NameUtil.getSafeName(o1);
			String n2 = NameUtil.getSafeName(o2);
			return ClassUtil.safeCompareTo(n1, n2);
		}
	}

	public static final class ENamedElementComparator implements Comparator<ENamedElement>
	{
		public static final @NonNull ENamedElementComparator INSTANCE = new ENamedElementComparator();

		@Override
		public int compare(ENamedElement o1, ENamedElement o2) {
			String n1 = o1.getName();
			String n2 = o2.getName();
			return ClassUtil.safeCompareTo(n1, n2);
		}
	}

	/**
	 * @since 1.3
	 */
	public static final class ToStringComparator implements Comparator<@NonNull Object>
	{
		/**
		 * Provide a simple shared INSTANCE for comparison based on toString().
		 * If toString() is more expensive than a Map.get() a toString() cache can be
		 * activated by constructing a new ToStringComparator instance.
		 */
		public static final @NonNull ToStringComparator INSTANCE = new ToStringComparator(null);

		/*
		 * toString can be expensive so avoid repeated evaluations.
		 */
		private final Map<@NonNull Object, String> object2string;

		public ToStringComparator() {
			this(new HashMap<>());
		}

		protected ToStringComparator(@Nullable Map<@NonNull Object, String> object2string) {
			this.object2string = object2string;
		}

		@Override
		public int compare(@NonNull Object o1, @NonNull Object o2) {
			if (object2string == null) {
				String s1 = o1.toString();
				String s2 = o2.toString();
				return ClassUtil.safeCompareTo(s1, s2);
			}
			else {
				String s1 = getString(o1);
				String s2 = getString(o2);
				return ClassUtil.safeCompareTo(s1, s2);
			}
		}

		private String getString(@NonNull Object o) {
			String string = object2string.get(o);
			if (string == null) {
				string = o.toString();
				object2string.put(o, string);
			}
			return string;
		}
	}

	public static final class URIComparator implements Comparator<@NonNull EObject>
	{
		/*
		 * toString can be expensive so avoid repeated evaluations.
		 */
		private final Map<@NonNull EObject, @NonNull URI> eObject2uri = new HashMap<>();

		@Override
		public int compare(@NonNull EObject o1, @NonNull EObject o2) {
			String s1 = getURI(o1).toString();
			String s2 = getURI(o2).toString();
			return ClassUtil.safeCompareTo(s1, s2);
		}

		private @NonNull URI getURI(@NonNull EObject o) {
			URI uri = eObject2uri.get(o);
			if (uri == null) {
				uri = EcoreUtil.getURI(o);
				eObject2uri.put(o, uri);
			}
			return uri;
		}
	}

	/**
	 * @since 1.13
	 */
	public static final @NonNull ENamedElementComparator ENAMED_ELEMENT_COMPARATOR = ENamedElementComparator.INSTANCE;

	public static final @NonNull NameableComparator NAMEABLE_COMPARATOR = NameableComparator.INSTANCE;

	/**
	 * @since 1.3
	 */
	public static final @NonNull ToStringComparator TO_STRING_COMPARATOR = ToStringComparator.INSTANCE;

	public static final Comparator URI_COMPARATOR = null;

	public static String debugFullName(Object object) {
		if (object == null) {
			return "null";
		}
		else {
			return object.getClass().getName() + "@" + Integer.toHexString(System.identityHashCode(object));
		}
	}

	public static String debugSimpleName(Object object) {
		if (object == null) {
			return "null";
		}
		else {
			String name = object.getClass().getName();
			int lastIndex = name.lastIndexOf(".");
			return (lastIndex >= 0 ? name.substring(lastIndex+1) : name) + "@" + Integer.toHexString(System.identityHashCode(object));
		}
	}

	/**
	 * Optional context for subsequent errPrintln messages. Test harnesses may set the test name.
	 *
	 * @since 1.18
	 */
	public static @Nullable String contextLine = null;

	/**
	 * Print string on System.err preceded by a contextLine if one has been defined. The contextLine is undefined after the call.
	 *
	 * @since 1.18
	 */
	public static void errPrintln(@Nullable String string) {
		if (contextLine != null) {
			System.err.println(contextLine);
			contextLine = null;
		}
		System.err.println(String.valueOf(string));
	}

	public static @Nullable <T extends ENamedElement> T getENamedElement(@Nullable Iterable<T> elements, @Nullable String name) {
		if (elements == null)
			return null;
		for (T element : elements)
			if ((element != null) && ClassUtil.safeEquals(name, element.getName()))
				return element;
		return null;
	}

	@SuppressWarnings("unchecked")
	public static @Nullable <T extends ENamedElement, R extends T> R getENamedElement(@Nullable Iterable<T> elements, @Nullable String name, @Nullable Class<R> returnClass) {
		if (elements == null)
			return null;
		if (name == null)
			return null;
		if (returnClass == null)
			return null;
		for (T element : elements)
			if ((element != null) && returnClass.isAssignableFrom(element.getClass()) && ClassUtil.safeEquals(name, element.getName()))
				return (R) element;
		return null;
	}

	public static @NonNull String getName(@NonNull ENamedElement eNamedElement) {
		return ClassUtil.nonNullState(eNamedElement.getName());
	}

	public static @NonNull String getName(@NonNull Nameable nameable) {
		return ClassUtil.nonNullState(nameable.getName());
	}

	public static <T extends Nameable> @Nullable T getNameable(@Nullable Iterable<T> elements, @Nullable String name) {
		if (elements == null)
			return null;
		for (T element : elements)
			if ((element != null) && ClassUtil.safeEquals(name, element.getName()))
				return element;
		return null;
	}

	public static @Nullable String getOriginalName(@NonNull ENamedElement eNamedElement) {
		EAnnotation eAnnotation = eNamedElement.getEAnnotation(DerivedConstants.UML2_UML_PACKAGE_2_0_NS_URI);
		if (eAnnotation != null) {
			String originalName = eAnnotation.getDetails().get(DerivedConstants.ANNOTATION_DETAIL__ORIGINAL_NAME);
			if (originalName != null) {
				return originalName;
			}
		}
		return eNamedElement.getName();
	}

	public static @NonNull String getSafeName(@Nullable Nameable aNameable) {
		if (aNameable == null) {
			return "";
		}
		String name = aNameable.getName();
		if (name == null) {
			name = "";
		}
		return name;
	}

	/**
	 * Return a valid Java identifier based on nameHint. hasPrefix may be true to indicate that the
	 * caller will supply an additional valid prefix relieving this routine of the need to avoid
	 * leading numeric characters.
	 * <p>
	 * This is not intended to be a reversible algorithm; just to provide something reasonably readable.
	 */
	public static @NonNull String getValidJavaIdentifier(@NonNull String nameHint, boolean hasPrefix, @Nullable Object anObject) {
		if (nameHint.equals("<")) {
			return("lt");
		}
		else if (nameHint.equals("<=")) {
			return("le");
		}
		else if (nameHint.equals("=")) {
			return("eq");
		}
		else if (nameHint.equals("<>")) {
			return("ne");
		}
		else if (nameHint.equals(">=")) {
			return("ge");
		}
		else if (nameHint.equals(">")) {
			return("gt");
		}
		else if (nameHint.equals("+")) {
			return("sum");
		}
		else if (nameHint.equals("-")) {
			return((anObject instanceof Operation) && ((Operation)anObject).getOwnedParameters().size() <= 0 ? "neg" : "diff");
		}
		else if (nameHint.equals("*")) {
			return("prod");
		}
		else if (nameHint.equals("/")) {
			return("quot");
		}
		StringBuilder s = new StringBuilder();
		Character prefix = null;
		int length = nameHint.length();
		for (int i = 0; i < length; i++) {
			char c = nameHint.charAt(i);
			if (((i == 0) && !hasPrefix) ? Character.isJavaIdentifierStart(c) : Character.isJavaIdentifierPart(c)) {
				if (prefix != null) {
					s.append(prefix);
					prefix = null;
				}
				s.append(c);
			}
			else {
				if (c == '*') {
					s.append("_a");
				}
				else if (c == ':') {
					s.append("_c");
				}
				else if (c == '.') {
					if (prefix != null) {
						s.append(prefix);
						prefix = null;
					}
				}
				else if (c == ')') {
					s.append("_e");
				}
				else if (c == '>') {
					s.append("_g");
				}
				else if (c == '<') {
					s.append("_l");
				}
				else if (c == '-') {
					s.append("_m");
				}
				else if (c == '(') {
					s.append("_o");
				}
				else if (c == '+') {
					s.append("_p");
				}
				else if (c == '=') {
					s.append("_q");
				}
				else if (c == '/') {
					s.append("_s");
				}
				else {
					s.append('_' + Integer.toString(c));
				}
				prefix = '_';
			}
		}
		return s.toString();
	}

	/**
	 * Return a qualified name for object using the label generators registered
	 * in the QUALIFIED_NAME_REGISTRY.
	 *
	 * @param object to be named
	 * @return qualified name
	 */
	public static @NonNull String qualifiedNameFor(@Nullable Object object) {
		if (object == null) {
			return PivotConstantsInternal.NULL_MARKER;
		}
		Map<ILabelGenerator.Option<?>, Object> options = new HashMap<ILabelGenerator.Option<?>, Object>();
		options.put(ILabelGenerator.Builder.SHOW_QUALIFIER, "::");
		return LabelUtil.QUALIFIED_NAME_REGISTRY.labelFor(object, options);
	}

	/**
	 * @since 1.3
	 */
	public static void setOriginalName(@NonNull ENamedElement eNamedElement, @NonNull String originalName) {
		EAnnotation eAnnotation = eNamedElement.getEAnnotation(DerivedConstants.UML2_UML_PACKAGE_2_0_NS_URI);
		if (eAnnotation == null) {
			eAnnotation = EcoreFactory.eINSTANCE.createEAnnotation();
			eAnnotation.setSource(DerivedConstants.UML2_UML_PACKAGE_2_0_NS_URI);
			eNamedElement.getEAnnotations().add(eAnnotation);
		}
		eAnnotation.getDetails().put(DerivedConstants.ANNOTATION_DETAIL__ORIGINAL_NAME, originalName);
	}

	/**
	 * Return a simple name for object using the label generators registered
	 * in the SIMPLE_NAME_REGISTRY.
	 *
	 * @param object to be named
	 * @return simple name
	 */
	public static @NonNull String simpleNameFor(@Nullable Object object) {
		if (object == null) {
			return PivotConstantsInternal.NULL_MARKER;
		}
		return LabelUtil.SIMPLE_NAME_REGISTRY.labelFor(object);
	}
}
