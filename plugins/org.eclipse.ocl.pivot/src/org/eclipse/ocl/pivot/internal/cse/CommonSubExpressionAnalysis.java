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
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.LiteralExp;
import org.eclipse.ocl.pivot.MapLiteralPart;
import org.eclipse.ocl.pivot.OCLExpression;
import org.eclipse.ocl.pivot.TypeExp;
import org.eclipse.ocl.pivot.TypedElement;
import org.eclipse.ocl.pivot.Variable;
import org.eclipse.ocl.pivot.VariableDeclaration;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.cse.AbstractCSEElement.CSEMapLiteralPartElement;
import org.eclipse.ocl.pivot.internal.cse.AbstractCSEElement.CSEMappedElement;
import org.eclipse.ocl.pivot.internal.cse.AbstractCSEElement.CSESimpleElement;
import org.eclipse.ocl.pivot.internal.cse.AbstractCSEElement.CSETypeElement;
import org.eclipse.ocl.pivot.internal.cse.AbstractCSEElement.CSEValueElement;
import org.eclipse.ocl.pivot.internal.symbolic.SymbolicUtil;
import org.eclipse.ocl.pivot.internal.symbolic.SymbolicUtil.TypedElementHeightComparator;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.StringUtil;
import org.eclipse.ocl.pivot.utilities.TreeIterable;

import com.google.common.collect.Iterables;

/**
 * CommonSubExpressionAnalysis supervises the construction of a CSEElement tree for an ExpresssionInOCL
 * using a CSEVisitor to handle each distinct child and referenced Pivot Element. Caches avoid reation
 * of duplicates.
 *
 * @since 1.16
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
	 * The CSEs for specific typeids.
	 *
	 * XXX make TypeExp a Literal
	 */
	private @Nullable Map<@NonNull TypeId, @NonNull CSETypeElement> typeid2cse = null;

	/**
	 * The CSEs for specific values.
	 */
	private final @NonNull Map<@NonNull Object, @NonNull CSEValueElement> value2cse = new HashMap<>();

	/**
	 * Comparator to sort ExpressionInOCL/OCLExpression into simplest CSE first, most direct TYpedE;ement first.
	 */
	private @Nullable TypedElementHeightComparator typedElementHeightComparator;

	public CommonSubExpressionAnalysis() {
		this.visitor = createCSEVisitor();
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
				for (Element aDelegate = element; (aDelegate = SymbolicUtil.getDelegate(aDelegate)) != null; ) {
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
			cseElement = new CSEMappedElement(this, element, height, property2element);
			for (@NonNull CSEElement inputCSE : property2element.values()) {
				cseElement.addInput(inputCSE);
			}
			key2element2cse2.put(property2element, cseElement);
		}
		else {
			cseElement.addElement(element);
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
			cseElement = new CSESimpleElement(this, element, height);
			for (@NonNull CSEElement cseElement2 : cseElements) {
				cseElement.addInput(cseElement2);
			}
			elements2cse.put(cseElements, cseElement);
		}
		else {
			cseElement.addElement(element);
		}
		element2cse.put(element, cseElement);
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
	//	else {
	//		cseElement.addElement(element);
	//	}
		element2cse.put(element, cseElement);
		return cseElement;
	}

	// XXX Make TypeExp a LiteralExp
	public @NonNull CSEElement getTypeCSE(@NonNull TypeExp typeExp) {
		Map<@NonNull TypeId, @NonNull CSETypeElement> typeid2cse2 = typeid2cse;
		if (typeid2cse2 == null) {
			typeid2cse2 = typeid2cse = new HashMap<>();
		}
		TypeId typeId = typeExp.getTypeId();
		CSETypeElement cseElement = typeid2cse2.get(typeId);
		if (cseElement == null) {
			cseElement = new CSETypeElement(this, typeExp);
			typeid2cse2.put(typeId, cseElement);
		}
		else {
			cseElement.addElement(typeExp);
		}
		return cseElement;
	}

	public @NonNull Comparator<@NonNull TypedElement> getTypedElementHeightComparator() {
		TypedElementHeightComparator typedElementHeightComparator2 = typedElementHeightComparator;
		if (typedElementHeightComparator2 == null) {
			typedElementHeightComparator = typedElementHeightComparator2 = new TypedElementHeightComparator(element2cse);
		}
		return typedElementHeightComparator2;
	}

	public @NonNull CSEElement getValueCSE(@NonNull LiteralExp literalExp, @NonNull Object value) {
		CSEValueElement cseElement = value2cse.get(value);
		if (cseElement == null) {
			cseElement = new CSEValueElement(this, literalExp, value);
			value2cse.put(value, cseElement);
		}
		else {
			cseElement.addElement(literalExp);
		}
		cseElement.addOutput(literalExp);
		return cseElement;
	}

//	public @NonNull CSEVariableElement getVariableCSE(@Nullable VariableExp variableExp, @NonNull VariableDeclaration variableDeclaration) {
//		CSEVariableElement cseElement = getVariableCSE(variableDeclaration);
//		if (variableExp != null) {
//			cseElement.addVariableExp(variableExp);
//		}
//		return cseElement;
//	}

	public @NonNull CSEElement getVariableCSE(@NonNull VariableDeclaration variableDeclaration) {  // Fold single call
		CSESimpleElement cseElement = (CSESimpleElement)element2cse.get(variableDeclaration);
		if (cseElement == null) {
			CSEElement initCSE = null;
			int height = 0;
			if (variableDeclaration instanceof Variable) {
				OCLExpression initExpression = ((Variable)variableDeclaration).getOwnedInit();
				if (initExpression != null) {
					initCSE = getCSEElement(initExpression);
					height = initCSE.getHeight() + 1;
				}
			}
			cseElement = new CSESimpleElement(this, variableDeclaration, height);
			if (initCSE != null) {
				cseElement.addInput(initCSE);
			}
			element2cse.put(variableDeclaration, cseElement);
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
			Element theElement = cseElement.getElements().iterator().next();
			s.append(element.eClass().getName());
			s.append("@");
			s.append(Integer.toHexString(System.identityHashCode(element)));
			s.append(" | ");
			s.append(cseElement.getHeight());
			s.append("-");
			s.append(Integer.toHexString(System.identityHashCode(cseElement)));
			s.append("-");
			s.append(theElement.eClass().getName());
			s.append(": ");
			s.append(theElement);
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
