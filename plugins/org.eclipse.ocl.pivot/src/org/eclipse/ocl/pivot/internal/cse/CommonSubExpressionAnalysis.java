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
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.LiteralExp;
import org.eclipse.ocl.pivot.OCLExpression;
import org.eclipse.ocl.pivot.TypeExp;
import org.eclipse.ocl.pivot.TypedElement;
import org.eclipse.ocl.pivot.Variable;
import org.eclipse.ocl.pivot.VariableDeclaration;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.symbolic.SymbolicUtils;
import org.eclipse.ocl.pivot.internal.symbolic.SymbolicUtils.TypedElementHeightComparator;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.StringUtil;

/**
 * @since 1.16
 */
public class CommonSubExpressionAnalysis
{
	protected final @NonNull CSEVisitor visitor;

	/**
	 * The CSEs for specific model elements.
	 */
	private final @NonNull Map<@NonNull Element, @NonNull CSEElement> element2cse = new HashMap<>();

	/**
	 * The CSEs for specific model elements.
	 */
	private @Nullable Map<@NonNull Class<?>, @NonNull Map<@NonNull List<@NonNull CSEElement>, @NonNull CSEAggregateElement>> namespaceClass2elements2cse = null;

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
		CSEElement cseElement = getElementCSE(expressionInOCL);
		assert SymbolicUtils.debugCheckCSEs(expressionInOCL, element2cse);		// XXX debugging
		return cseElement;
	}

	protected @NonNull CSEVisitor createCSEVisitor() {
		return new CSEVisitor(this);
	}

	public @NonNull CSEElement getElementCSE(@NonNull Element expression) {
		CSEElement cseElement = element2cse.get(expression);
		if (cseElement == null) {
			cseElement = visitor.visit(expression);
			element2cse.put(expression, cseElement);
		}
		return cseElement;
	}

	public @NonNull CSEMappedElement getMappedCSE(@NonNull Element element, @NonNull Map<@NonNull TypedElement, @NonNull CSEElement> property2element) {
		Map<@NonNull Map<@NonNull TypedElement, @NonNull CSEElement>, @NonNull CSEMappedElement> key2element2cse2 = key2element2cse;
		if (key2element2cse2 == null) {
			key2element2cse = key2element2cse2 = new HashMap<>();
		}
		CSEMappedElement cseElement = key2element2cse2.get(property2element);
		if (cseElement == null) {
			cseElement = new CSEMappedElement(this, element, property2element);
			key2element2cse2.put(property2element, cseElement);
		}
		return cseElement;
	}

	public @NonNull CSEAggregateElement getNamespaceCSE(@NonNull Element element, @NonNull List<@NonNull CSEElement> elements) {
		@NonNull Class<?> namespaceClass = element.getClass();
		Map<@NonNull Class<?>, @NonNull Map<@NonNull List<@NonNull CSEElement>, @NonNull CSEAggregateElement>> namespaceClass2elements2cse2 = namespaceClass2elements2cse;
		if (namespaceClass2elements2cse2 == null) {
			namespaceClass2elements2cse2 = namespaceClass2elements2cse = new HashMap<>();
		}
		Map<@NonNull List<@NonNull CSEElement>, @NonNull CSEAggregateElement> elements2cse = namespaceClass2elements2cse2.get(namespaceClass);
		if (elements2cse == null) {
			elements2cse = new HashMap<>();
			namespaceClass2elements2cse2.put(namespaceClass, elements2cse);
		}
		CSEAggregateElement cseElement = elements2cse.get(elements);
		if (cseElement == null) {
			cseElement = new CSEAggregateElement(this, element, elements);
			elements2cse.put(elements, cseElement);
		}
		return cseElement;
	}

	// XXX Make TypeExp a LiteralExp
	public @NonNull CSETypeElement getTypeCSE(@NonNull TypeExp typeExp) {
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
		return cseElement;
	}

	public @NonNull Comparator<@NonNull TypedElement> getTypedElementHeightComparator() {
		TypedElementHeightComparator typedElementHeightComparator2 = typedElementHeightComparator;
		if (typedElementHeightComparator2 == null) {
			typedElementHeightComparator = typedElementHeightComparator2 = new TypedElementHeightComparator(element2cse);
		}
		return typedElementHeightComparator2;
	}

	public @NonNull CSEValueElement getValueCSE(@NonNull LiteralExp literalExp, @NonNull Object value) {
		CSEValueElement cseElement = value2cse.get(value);
		if (cseElement == null) {
			cseElement = new CSEValueElement(this, literalExp, value);
			value2cse.put(value, cseElement);
		}
		cseElement.addLiteralExp(literalExp);
		return cseElement;
	}

//	public @NonNull CSEVariableElement getVariableCSE(@Nullable VariableExp variableExp, @NonNull VariableDeclaration variableDeclaration) {
//		CSEVariableElement cseElement = getVariableCSE(variableDeclaration);
//		if (variableExp != null) {
//			cseElement.addVariableExp(variableExp);
//		}
//		return cseElement;
//	}

	public @NonNull CSEVariableElement getVariableCSE(@NonNull VariableDeclaration variableDeclaration) {
		CSEVariableElement cseElement = (CSEVariableElement) element2cse.get(variableDeclaration);
		if (cseElement == null) {
			int height = 0;
			if (variableDeclaration instanceof Variable) {
				OCLExpression initExpression = ((Variable)variableDeclaration).getOwnedInit();
				if (initExpression != null) {
					CSEElement initCSE = getElementCSE(initExpression);
					height = initCSE.getHeight() + 1;
				}
			}
			cseElement = new CSEVariableElement(this, variableDeclaration, height);
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
			Element theElement = cseElement.getElement();
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
			Element element = cseElement.getElement();
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
