/*******************************************************************************
 * Copyright (c) 2010, 2024 Willink Transformations and others.
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

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.internal.utilities.PivotConstantsInternal;
import org.eclipse.ocl.pivot.labels.ILabelGenerator;
import org.eclipse.ocl.pivot.util.DerivedConstants;

public class NameUtil
{
	public static final class EAnnotationComparator implements Comparator<EAnnotation>
	{
		public static final @NonNull EAnnotationComparator INSTANCE = new EAnnotationComparator();

		@Override
		public int compare(EAnnotation o1, EAnnotation o2) {
			String n1 = o1.getSource();
			String n2 = o2.getSource();
			return ClassUtil.safeCompareTo(n1, n2);
		}
	}

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

	public static final class ENamedElementComparator implements Comparator<@NonNull ENamedElement>
	{
		public static final @NonNull ENamedElementComparator INSTANCE = new ENamedElementComparator();

		@Override
		public int compare(@NonNull ENamedElement o1, @NonNull ENamedElement o2) {
			if (o1 == o2) {
				return 0;		// Short circuit containment compare / independent searches
			}
			String n1 = o1.getName();
			String n2 = o2.getName();
			int comparison = ClassUtil.safeCompareTo(n1, n2);
			if (comparison != 0) {
				return comparison;
			}
			if ((o1 instanceof EPackage) && (o2 instanceof EPackage)) {
				n1 = ((EPackage)o1).getNsURI();
				n2 = ((EPackage)o2).getNsURI();
				comparison = ClassUtil.safeCompareTo(n1, n2);
				if (comparison != 0) {
					return comparison;
				}
			}
			EObject p1 = o1.eContainer();
			EObject p2 = o2.eContainer();
			if ((p1 instanceof ENamedElement) && (p2 instanceof ENamedElement)) {
				return compare((ENamedElement)p1, (ENamedElement)p2);
			}
			return comparison;
		}
	}

	/**
	 * @since 1.3
	 */
	public static class ToStringComparator<T> implements Comparator<@NonNull T>
	{
		/**
		 * Provide a simple shared INSTANCE for comparison based on toString().
		 * If toString() is more expensive than a Map.get() a toString() cache can be
		 * activated by constructing a new ToStringComparator instance.
		 */
		public static final @NonNull ToStringComparator<@NonNull Object> INSTANCE = new ToStringComparator<@NonNull Object>(null);

		/*
		 * toString can be expensive so avoid repeated evaluations.
		 */
		private final Map<@NonNull T, @NonNull String> object2string;

		public ToStringComparator() {
			this(new HashMap<>());
		}

		protected ToStringComparator(@Nullable Map<@NonNull T, @NonNull String> object2string) {
			this.object2string = object2string;
		}

		@Override
		public int compare(@NonNull T o1, @NonNull T o2) {
			String s1;
			String s2;
			if (object2string == null) {
				s1 = o1.toString();
				s2 = o2.toString();
			}
			else {
				s1 = getString(o1);
				s2 = getString(o2);
			}
			return ClassUtil.safeCompareTo(s1, s2);
		}

		/**
		 * @since 1.20
		 */
		protected @NonNull String getString(@NonNull T o) {
			String string = object2string.get(o);
			if (string == null) {
				string = o.toString();
				if (string == null) {
					string = "";
				}
				object2string.put(o, string);
			}
			return string;
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
	public static final @NonNull ToStringComparator<@NonNull Object> TO_STRING_COMPARATOR = ToStringComparator.INSTANCE;

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
