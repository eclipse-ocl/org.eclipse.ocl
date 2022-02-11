/**
 * Copyright (c) 2020, 2021 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 */
package org.eclipse.ocl.pivot.internal.cse;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.MapLiteralPart;
import org.eclipse.ocl.pivot.TypedElement;
import org.eclipse.ocl.pivot.internal.cse.AbstractCSEElement.CSEMapLiteralPartElement;
import org.eclipse.ocl.pivot.internal.cse.AbstractCSEElement.CSEMappedElement;
import org.eclipse.ocl.pivot.internal.cse.AbstractCSEElement.CSESimpleElement;
import org.eclipse.ocl.pivot.internal.cse.AbstractCSEElement.CSEValueElement;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.utilities.StringUtil;
import org.eclipse.ocl.pivot.utilities.TreeIterable;

import com.google.common.collect.Iterables;

/**
 * CommonSubExpressionAnalysis supervises the construction of a CSEElement tree for an ExpresssionInOCL
 * using a CSEVisitor to handle each distinct child and referenced Pivot Element. Caches avoid reation
 * of duplicates.
 *
 * @since 1.17
 */
public class CommonSubExpressionAnalysis
{
	protected static int computeHeight(@NonNull Iterable<@NonNull CSEElement> elements) {
		if (Iterables.isEmpty(elements)) {
			return 0;
		}
		int maxHeight = 0;
		for (@NonNull CSEElement element : elements) {
			int height = element.getHeight();
			if (height > maxHeight) {
				maxHeight = height;
			}
		}
		return maxHeight + 1;
	}
	/**
	 * TypedElementHeightComparator sort ExpressionInOCL/OCLExpression to determine a (re-)evaluation order.
	 * Simplest (fewest shallowest children) CSE first, then fewest delegations to the CSE then earliest position
	 * in Ecore Tree..
	 */
	public static class TypedElementHeightComparator implements Comparator<@NonNull TypedElement>
	{
		private final @NonNull Map<@NonNull Element, @NonNull CSEElement> element2cse;		// FIXME Element -> TypedElement once MapLiteralPart fixed
		private @Nullable Map<@NonNull Element, @NonNull Iterable<@NonNull Integer>> element2position = null;

		private TypedElementHeightComparator(@NonNull Map<@NonNull Element, @NonNull CSEElement> element2cse) {
			this.element2cse = element2cse;
		}

		@Override
		public int compare(@NonNull TypedElement o1, @NonNull TypedElement o2) {
			assert o1 != o2;
			CSEElement cse1 = element2cse.get(o1);
			CSEElement cse2 = element2cse.get(o2);
			assert (cse1 != null) && (cse2 != null);
			int h1 = cse1.getHeight();
			int h2 = cse2.getHeight();
			int diff = h1 - h2;
			if (diff != 0) {
				return diff;
			}
			int d1 = getDelegations(o1);
			int d2 = getDelegations(o2);
			diff = d1 - d2;
			if (diff != 0) {
				return diff;
			}
			Iterator<@NonNull Integer> i1 = getPosition(o1);			// class name / nav name faster
			Iterator<@NonNull Integer> i2 = getPosition(o2);
			while (i1.hasNext() && i2.hasNext()) {
				int x1 = i1.next();
				int x2 = i2.next();
				diff = x1 - x2;
				if (diff != 0) {
					return diff;
				}
			}
			if (!i1.hasNext()) {		// Longest (deepest) first
				return 1;
			}
			if (!i2.hasNext()) {
				return -1;
			}
			assert false;
			return 0;
		}

		protected int getDelegations(@NonNull Element typedElement) {
			Element delegate = PivotUtil.getDelegate(typedElement);
			if (delegate != null) {
				final CSEElement cse1 = element2cse.get(typedElement);
				final CSEElement cse2 = element2cse.get(delegate);
				assert cse1 == cse2;		// XXX delete me
				return 1 + getDelegations(delegate);
			}
			return 0;
		}

		protected @NonNull Iterator<@NonNull Integer> getPosition(@NonNull TypedElement typedElement) {
			Map<@NonNull Element, @NonNull Iterable<@NonNull Integer>> element2position2 = element2position;
			if (element2position2 == null) {
				element2position = element2position2 = new HashMap<>();
			}
			Iterable<@NonNull Integer> position = element2position2.get(typedElement);
			if (position == null) {
				List<@NonNull Integer> position2 = new ArrayList<>();
				position = getPosition(position2, typedElement);
			//	assert !element2position2.values().contains(position);
				element2position2.put(typedElement, position);
			//	assert element2position2.values().contains(position);
			}
			return position.iterator();
		}

		private @NonNull Iterable<@NonNull Integer> getPosition(@NonNull List<@NonNull Integer> position, @NonNull EObject eObject) {
			EReference eContainmentFeature = eObject.eContainmentFeature();
			if (eContainmentFeature != null) {
				EObject eContainer = eObject.eContainer();
				assert eContainer != null;
				getPosition(position, eContainer);
				position.add(eContainer.eContents().indexOf(eObject));
			}
			return position;
		}
	}


	protected final @NonNull CSEVisitor visitor;

	/**
	 * The CSEs for specific model elements.
	 */
	private final @NonNull Map<@NonNull Element, @NonNull CSEElement> element2cse = new HashMap<>();

	/**
	 * The CSEs for specific model elements.
	 */
	private @Nullable Map<@NonNull Class<?>, @NonNull Map<@NonNull List<@NonNull CSEElement>, @NonNull CSESimpleElement>> namespaceClass2elements2cse = null;
	@Deprecated
	private @Nullable Map<@NonNull Class<?>, @NonNull Map<@NonNull List<@NonNull CSEElement>, @NonNull CSEMapLiteralPartElement>> namespaceClass2elements2mapcse = null;

	/**
	 * The CSEs for specific keyed model elements such as ShadowPart and TupleLiteralPart
	 */
	private @Nullable Map<@NonNull Map<@NonNull TypedElement, @NonNull CSEElement>, @NonNull CSEMappedElement> key2element2cse = null;

	/**
	 * The CSEs for specific values.
	 */
	private final @NonNull Map<@NonNull Object, @NonNull CSEValueElement> value2cse = new HashMap<>();

	/**
	 * The CSE for for the self paramater of the current class analysis.
	 */
	private final @NonNull CSEElement selfCSE;

	/**
	 * Comparator to sort ExpressionInOCL/OCLExpression into simplest CSE first, most direct TypedElement first.
	 */
	private @Nullable TypedElementHeightComparator typedElementHeightComparator = null;

	public CommonSubExpressionAnalysis() {
		this.visitor = createCSEVisitor();
		this.selfCSE = new CSESimpleElement(this, 0);
	}

	public @NonNull CSEElement analyze(@NonNull ExpressionInOCL expressionInOCL) {
		CSEElement cseElement = getCSEElement(expressionInOCL);
		assert debugCheckCSEs(expressionInOCL);		// XXX debugging
		return cseElement;
	}

	protected @NonNull CSEVisitor createCSEVisitor() {
		return new CSEVisitor(this);
	}

	//
	//	Confirm that the element2cse has a consistently delegated entry for the expressionInOCL tree.
	//
	public boolean debugCheckCSEs(@NonNull ExpressionInOCL expressionInOCL) {
		//	Map<@NonNull CSEElement, @NonNull Set<@NonNull Element>> cse2elements = new HashMap<>();
		Set<@NonNull CSEElement> cseElements = new HashSet<>();
		for (EObject eObject : new TreeIterable(expressionInOCL, true)) {
			if (eObject instanceof Element) {		// MapLiteralPart
				Element element = (Element)eObject;
				CSEElement cseElement = element2cse.get(element);
				assert cseElement != null : "Missing CSE for " + element.eClass().getName() + ": " + element;
	//			Set<@NonNull Element> elements = cse2elements.get(cseElement);
	//			if (elements == null) {
	//				elements = new HashSet<>();
	//				cse2elements.put(cseElement, elements);
	//			}
	//			elements.add(element);
				cseElements.add(cseElement);
			}
		}
		CSEElement cseRoot = getCSEElement(expressionInOCL);
		int maxHeight = cseRoot.getHeight();
		for (@NonNull CSEElement cseElement : cseElements) {
			Iterable<@NonNull TypedElement> elements = cseElement.getElements();
			for (@NonNull Element element : elements) {
				for (Element aDelegate = element; (aDelegate = PivotUtil.getDelegate(aDelegate)) != null; ) {
					assert Iterables.contains(elements, aDelegate) : "Inconsistent CSE delegation for " + element.eClass().getName() + ": " + element;
				}
			}
			int height = cseElement.getHeight();
			Iterable<@NonNull CSEElement> inputs = cseElement.getInputs();
			if (inputs == null) {
				assert height == 0 : "inconsistent height " + height + " for " + cseElement;
			}
			else {
				assert height > 0 : "inconsistent height " + height + " for " + cseElement;
				for (@NonNull CSEElement input : inputs) {
					assert input.getHeight() < height : "inconsistent " + input.getHeight() + " input at " + height + " for " + cseElement;
					assert Iterables.contains(input.getOutputs(), cseElement) : "missing input " + input + " for " + cseElement;
				}
			}
			Iterable<@NonNull CSEElement> outputs = cseElement.getOutputs();
			if (Iterables.isEmpty(outputs)) {
				assert height == maxHeight : "inconsistent height " + height + " for " + cseElement;
			}
			else {
				assert height < maxHeight : "inconsistent height " + height + " for " + cseElement;
				for (@NonNull CSEElement output : outputs) {
					assert output.getHeight() > height : "inconsistent height " + output.getHeight() + " output at " + height + " for " + cseElement;
					assert Iterables.contains(output.getInputs(), cseElement) : "missing output " + output + " for " + cseElement;
				}
			}
		}
	//	for (Map.Entry<@NonNull CSEElement, @NonNull Set<@NonNull Element>> entry : cse2elements.entrySet()) {
	//		Set<@NonNull Element> localElements = entry.getValue();
	//		Set<@NonNull Element> cachedElements = Sets.newHashSet(entry.getKey().getElements());
	//		assert localElements.equals(cachedElements);
	//	}
		return true;
	}

	// XXX Change to TypedElement once MapLiteralPart is a TypedElement
	public @NonNull CSEElement getCSEElement(@NonNull Element element) {
		CSEElement cseElement = element2cse.get(element);
		if (cseElement == null) {
			cseElement = visitor.visit(element);
			element2cse.put(element, cseElement);
			if (!(element instanceof MapLiteralPart)) {
				cseElement.addElement((TypedElement)element);
				assert Iterables.contains(cseElement.getElements(), element) : "No CSE registration for a " + element.eClass().getName();
			}
		}
		return cseElement;
	}

	public @NonNull CSEElement getMappedCSE(@NonNull TypedElement element, @NonNull Map<@NonNull TypedElement, @NonNull CSEElement> property2element) {
		Map<@NonNull Map<@NonNull TypedElement, @NonNull CSEElement>, @NonNull CSEMappedElement> key2element2cse2 = key2element2cse;
		if (key2element2cse2 == null) {
			key2element2cse = key2element2cse2 = new HashMap<>();
		}
		CSEMappedElement cseElement = key2element2cse2.get(property2element);
		if (cseElement == null) {
			int height = computeHeight(property2element.values());
			cseElement = new CSEMappedElement(this, height, property2element);
			for (@NonNull CSEElement inputCSE : property2element.values()) {
				cseElement.addInput(inputCSE);
			}
			key2element2cse2.put(property2element, cseElement);
		}
		return cseElement;
	}

	public @NonNull CSEElement getNamespaceCSE(@NonNull TypedElement element, @NonNull List<@NonNull CSEElement> cseElements) {
		assert !element2cse.containsKey(element);
		@NonNull Class<?> namespaceClass = element.getClass();
		Map<@NonNull Class<?>, @NonNull Map<@NonNull List<@NonNull CSEElement>, @NonNull CSESimpleElement>> namespaceClass2elements2cse2 = namespaceClass2elements2cse;
		if (namespaceClass2elements2cse2 == null) {
			namespaceClass2elements2cse2 = namespaceClass2elements2cse = new HashMap<>();
		}
		Map<@NonNull List<@NonNull CSEElement>, @NonNull CSESimpleElement> elements2cse = namespaceClass2elements2cse2.get(namespaceClass);
		if (elements2cse == null) {
			elements2cse = new HashMap<>();
			namespaceClass2elements2cse2.put(namespaceClass, elements2cse);
		}
		CSESimpleElement cseElement = elements2cse.get(cseElements);
		if (cseElement == null) {
			int height = computeHeight(cseElements);
			cseElement = new CSESimpleElement(this, height);
			for (@NonNull CSEElement cseElement2 : cseElements) {
				cseElement.addInput(cseElement2);
			}
			elements2cse.put(cseElements, cseElement);
		}
		return cseElement;
	}

	// XXX Delete me once MapLiteralPart is a TypedElement
	@Deprecated
	public @NonNull CSEElement getNamespaceCSE(@NonNull MapLiteralPart element, @NonNull List<@NonNull CSEElement> cseElements) {
		assert !element2cse.containsKey(element);
		@NonNull Class<?> namespaceClass = element.getClass();
		Map<@NonNull Class<?>, @NonNull Map<@NonNull List<@NonNull CSEElement>, @NonNull CSEMapLiteralPartElement>> namespaceClass2elements2cse2 = namespaceClass2elements2mapcse;
		if (namespaceClass2elements2cse2 == null) {
			namespaceClass2elements2cse2 = namespaceClass2elements2mapcse = new HashMap<>();
		}
		Map<@NonNull List<@NonNull CSEElement>, @NonNull CSEMapLiteralPartElement> elements2cse = namespaceClass2elements2cse2.get(namespaceClass);
		if (elements2cse == null) {
			elements2cse = new HashMap<>();
			namespaceClass2elements2cse2.put(namespaceClass, elements2cse);
		}
		CSEMapLiteralPartElement cseElement = elements2cse.get(cseElements);
		if (cseElement == null) {
			int height = computeHeight(cseElements);
			cseElement = new CSEMapLiteralPartElement(this, element, height);
			for (@NonNull CSEElement cseElement2 : cseElements) {
				cseElement.addInput(cseElement2);
			}
			elements2cse.put(cseElements, cseElement);
		}
		return cseElement;
	}

	public @NonNull CSEElement getSelfCSE() {
		return selfCSE;
	}

	public @NonNull Comparator<@NonNull TypedElement> getTypedElementHeightComparator() {
		TypedElementHeightComparator typedElementHeightComparator2 = typedElementHeightComparator;
		if (typedElementHeightComparator2 == null) {
			typedElementHeightComparator = typedElementHeightComparator2 = new TypedElementHeightComparator(element2cse);
		}
		return typedElementHeightComparator2;
	}

	public @NonNull CSEElement getValueCSE(@NonNull Object value) {
		CSEValueElement cseElement = value2cse.get(value);
		if (cseElement == null) {
			cseElement = new CSEValueElement(this, value);
			value2cse.put(value, cseElement);
		}
		return cseElement;
	}

	@Override
	public @NonNull String toString() {
		StringBuilder s = new StringBuilder();
		toString(s, 0);
		return s.toString();
	}

	public void toString(@NonNull StringBuilder s, int depth) {
		s.append("elements");
		List<@NonNull Element> elements = new ArrayList<>(element2cse.keySet());
		Collections.sort(elements, NameUtil.TO_STRING_COMPARATOR);
		for (@NonNull Element element : elements) {
			StringUtil.appendIndentation(s, depth+1);
			CSEElement cseElement = element2cse.get(element);
			assert cseElement != null;
			Iterable<@NonNull TypedElement> elements2 = cseElement.getElements();
			s.append(element.eClass().getName());
			s.append("@");
			s.append(Integer.toHexString(System.identityHashCode(element)));
			s.append(" | ");
			s.append(cseElement.getHeight());
			s.append("-");
			s.append(Integer.toHexString(System.identityHashCode(cseElement)));
			if (!Iterables.isEmpty(elements2)) {
				Element theElement = elements2.iterator().next();
				s.append("-");
				s.append(theElement.eClass().getName());
				s.append(": ");
				s.append(theElement);
			}
		}
		StringUtil.appendIndentation(s, depth);
		s.append("values");
		List<@NonNull Object> values = new ArrayList<>(value2cse.keySet());
		Collections.sort(values, NameUtil.TO_STRING_COMPARATOR);
		for (@NonNull Object value : values) {
			StringUtil.appendIndentation(s, depth+1);
			CSEElement cseElement = value2cse.get(value);
			assert cseElement != null;
			Element element = cseElement.getElements().iterator().next();
			if (value instanceof EObject) {
				s.append(((EObject)value).eClass().getName());
			}
			else {
				s.append(value.getClass().getSimpleName());
			}
			s.append("@");
			s.append(cseElement.getHeight());
			s.append("#");
			s.append(element);
		}
	}
}
