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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.CallExp;
import org.eclipse.ocl.pivot.IfExp;
import org.eclipse.ocl.pivot.LiteralExp;
import org.eclipse.ocl.pivot.MapLiteralPart;
import org.eclipse.ocl.pivot.NavigationCallExp;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.TypeExp;
import org.eclipse.ocl.pivot.TypedElement;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.PivotUtil;

/**
 * @since 1.16
 */
public abstract class AbstractCSEElement implements CSEElement
{
	/**
	 * A CSEMappedElement adds lookup from a CSEElement to its a TypedElement, typically Property, child,
	 */
	public static class CSEMappedElement extends AbstractCSEElement
	{
		private final @NonNull Map<@NonNull TypedElement, @NonNull CSEElement> key2element;

		public CSEMappedElement(@NonNull CommonSubExpressionAnalysis cseAnalysis, @NonNull TypedElement element, int height, @NonNull Map<@NonNull TypedElement, @NonNull CSEElement> key2element) {
			super(cseAnalysis, element, height);
			this.key2element = key2element;
		}

		public @NonNull CSEElement getElement(@NonNull TypedElement key) {
			return ClassUtil.nonNullState(key2element.get(key));
		}
	}

	/**
	 * A CSEMapLiteralPartElement is the cirregular non-TypedElement implementation for a MapLiteralPart.
	 */
	// XXX Delete me once MapLiteralPart is a TypedElement
	@Deprecated
	public static class CSEMapLiteralPartElement extends AbstractCSEElement
	{
		public CSEMapLiteralPartElement(@NonNull CommonSubExpressionAnalysis cseAnalysis, @NonNull MapLiteralPart element, int height) {
			super(cseAnalysis, height);
		}
	}

	/**
	 * A CSESimpleElement is the default concrete implementation of AbstractCSEElement.
	 */
	public static class CSESimpleElement extends AbstractCSEElement
	{
		public CSESimpleElement(@NonNull CommonSubExpressionAnalysis cseAnalysis, @NonNull TypedElement element, int height) {
			super(cseAnalysis, element, height);
		}
	}

	/**
	 * A CSETypeElement adds a redundant TypeId cache to aid debugging.
	 */
	public static class CSETypeElement extends CSESimpleElement
	{
		protected final @NonNull TypeId typeId;

		public CSETypeElement(@NonNull CommonSubExpressionAnalysis cseAnalysis, @NonNull TypeExp typeExp) {
			super(cseAnalysis, typeExp, 0);
			this.typeId = typeExp.getTypeId();
		}
	}

	/**
	 * A CSEValueElement adds a redundant value cache to aid debugging.
	 */
	public static class CSEValueElement extends CSESimpleElement
	{
		protected final @NonNull Object value;

		public CSEValueElement(@NonNull CommonSubExpressionAnalysis cseAnalysis, @NonNull LiteralExp literalExp, @NonNull Object value) {
			super(cseAnalysis, literalExp, 0);
			this.value = value;
		}
	}

	protected final @NonNull CommonSubExpressionAnalysis cseAnalysis;

	/**
	 * The peak depth of child CSE elements whose values contribute to the value of this CSE.
	 */
	private final int height;

	/**
	 * The common sub-expressions that are an if/iteration/operation navigation away from this common sub-expression.
	 * "if" is indexed by the null operation. uninitialized iterators have null argument CSEs.
	 */
	private /*@LazyNonNull*/ Map<@Nullable Operation, @NonNull Map<@NonNull List<@Nullable CSEElement>, @NonNull CSESimpleElement>> callable2arguments2cse = null;

	/**
	 * The common sub-expressions that are a property navigation away from this common sub-expression.
	 */
	private /*@LazyNonNull*/ Map<@NonNull Property, @NonNull CSESimpleElement> property2cse = null;

	/**
	 * The CSEs used to compute this CSE. The primary source first, rest in iterator, accumulator, argument, body order
	 */
	private @Nullable List<@NonNull CSEElement> inputs = null;

	/**
	 * The expressions whoe symbolic values make use of this CSE element.
	 */
//	private final @NonNull List<@NonNull TypedElement> outputs = new ArrayList<>();
	private final @NonNull List<@NonNull CSEElement> outputs = new ArrayList<>();

	/**
	 * The elements for which this is the CSE element.
	 *
	 * XXX FIXME this could be TypedElement if MapLiteralPart was a TypedElement.
	 */
	private final @NonNull List<@NonNull TypedElement> elements = new ArrayList<>();

	protected AbstractCSEElement(@NonNull CommonSubExpressionAnalysis cseAnalysis, @NonNull TypedElement element, int height) {
		this.cseAnalysis = cseAnalysis;
		this.height = height;
		addElement(element);
	}

	// XXX Delete me once MapLiteralPart is a TypedElement
	@Deprecated
	protected AbstractCSEElement(@NonNull CommonSubExpressionAnalysis cseAnalysis, int height) {
		this.cseAnalysis = cseAnalysis;
		this.height = height;
	}

	@Override
	public void addElement(@NonNull TypedElement element) {
		assert !elements.contains(element);
		elements.add(element);
	}

	protected void addInput(@NonNull CSEElement inputCSE) {
		List<@NonNull CSEElement> inputs2 = inputs;
		if (inputs2 == null) {
			inputs = inputs2 = new ArrayList<>();
		}
		inputs2.add(inputCSE);
		((AbstractCSEElement)inputCSE).outputs.add(this);
	}

	protected void addOutput(@NonNull TypedElement outputExp) {
	//	assert !outputs.contains(outputExp);
	//	outputs.add(outputExp);
	}

	@Override
	public int compareTo(@NonNull CSEElement that) {
		int i1 = System.identityHashCode(this);
		int i2 = System.identityHashCode(that);
		return i1 - i2;
	}

	@Override
	public @NonNull CommonSubExpressionAnalysis getCommonSubExpressionAnalysis() {
		return cseAnalysis;
	}

	@Override
	public @NonNull Iterable<@NonNull TypedElement> getElements() {
		return elements;
	}

	@Override
	public int getHeight() {
		return height;
	}

	@Override
	public @NonNull CSEElement getIfCSE(@NonNull IfExp ifExp, @NonNull CSEElement thenCSE, @NonNull CSEElement elseCSE) {
		Map<@Nullable Operation, @NonNull Map<@NonNull List<@Nullable CSEElement>, @NonNull CSESimpleElement>> callable2arguments2cse2 = callable2arguments2cse;
		if (callable2arguments2cse2 == null) {
			callable2arguments2cse2 = callable2arguments2cse = new HashMap<>();
		}
		Map<@NonNull List<@Nullable CSEElement>, @NonNull CSESimpleElement> arguments2cse = callable2arguments2cse2.get(null);
		if (arguments2cse == null) {
			arguments2cse = new HashMap<>();
			callable2arguments2cse2.put(null, arguments2cse);
		}
		List<@Nullable CSEElement> argumentCSEs = new ArrayList<>();
		argumentCSEs.add(thenCSE);
		argumentCSEs.add(elseCSE);
		CSESimpleElement cseElement = arguments2cse.get(argumentCSEs);
		if (cseElement == null) {
			int maxHeight = this.height;
			int thenHeight = thenCSE.getHeight();
			if (thenHeight > maxHeight) {
				maxHeight = thenHeight;
			}
			int elseHeight = elseCSE.getHeight();
			if (elseHeight > maxHeight) {
				maxHeight = elseHeight;
			}
			cseElement = new CSESimpleElement(cseAnalysis, ifExp, maxHeight+1);
			cseElement.addInput(this);
			cseElement.addInput(thenCSE);
			cseElement.addInput(elseCSE);
			arguments2cse.put(argumentCSEs, cseElement);
		}
		else {
			cseElement.addElement(ifExp);
		}
		return cseElement;
	}

	@Override
	public @Nullable Iterable<@NonNull CSEElement> getInputs() {
		return inputs;
	}

	@Override
	public @NonNull CSEElement getOperationCSE(@NonNull CallExp callExp, @NonNull Operation operation, @NonNull List<@Nullable CSEElement> argumentCSEs) {
		Map<@Nullable Operation, @NonNull Map<@NonNull List<@Nullable CSEElement>, @NonNull CSESimpleElement>> callable2arguments2cse2 = callable2arguments2cse;
		if (callable2arguments2cse2 == null) {
			callable2arguments2cse2 = callable2arguments2cse = new HashMap<>();
		}
		Map<@NonNull List<@Nullable CSEElement>, @NonNull CSESimpleElement> arguments2cse = callable2arguments2cse2.get(operation);
		if (arguments2cse == null) {
			arguments2cse = new HashMap<>();
			callable2arguments2cse2.put(operation, arguments2cse);
		}
		CSESimpleElement cseElement = arguments2cse.get(argumentCSEs);
		if (cseElement == null) {
			int maxHeight = this.height;
			for (@Nullable CSEElement argumentCSE : argumentCSEs) {
				if (argumentCSE != null) {				// null for auto-initialized iterator variables
					int argHeight = argumentCSE.getHeight();
					if (argHeight > maxHeight) {
						maxHeight = argHeight;
					}
				}
			}
			cseElement = new CSESimpleElement(cseAnalysis, callExp, maxHeight+1);
			cseElement.addInput(this);
			for (@Nullable CSEElement argumentCSE : argumentCSEs) {
				if (argumentCSE != null) {				// null for auto-initialized iterator variables
					cseElement.addInput(argumentCSE);
				}
			}
			arguments2cse.put(argumentCSEs, cseElement);
			addOutput(callExp);
		}
		else {
			cseElement.addElement(callExp);
		}
		return cseElement;
	}

	@Override
	public @NonNull Iterable<@NonNull CSEElement> getOutputs() {
		return outputs;
	}

	@Override
	public @NonNull CSEElement getPropertyCSE(@NonNull NavigationCallExp navigationCallExp) {
		Map<@NonNull Property, @NonNull CSESimpleElement> property2cse2 = property2cse;
		if (property2cse2 == null) {
			property2cse2 = property2cse = new HashMap<>();
		}
		Property property = PivotUtil.getReferredProperty(navigationCallExp);
		CSESimpleElement cseElement = property2cse2.get(property);
		if (cseElement == null) {
			cseElement = new CSESimpleElement(cseAnalysis, navigationCallExp, height+1);
			cseElement.addInput(this);
			property2cse2.put(property, cseElement);
			addOutput(navigationCallExp);
		}
		else {
			cseElement.addElement(navigationCallExp);
		}
		return cseElement;
	}

	@Override
	public @NonNull String toString() {
		StringBuilder s = new StringBuilder();
		toString(s, 100);
		return s.toString();
	}

	public void toString(@NonNull StringBuilder s, int lengthLimit) {
		s.append(height);
		s.append("#");
		s.append(elements.get(0));
	}
}
