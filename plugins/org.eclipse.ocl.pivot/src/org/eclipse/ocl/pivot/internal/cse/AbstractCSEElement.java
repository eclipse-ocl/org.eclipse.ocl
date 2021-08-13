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
import org.eclipse.ocl.pivot.MapLiteralPart;
import org.eclipse.ocl.pivot.NavigationCallExp;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.TypedElement;
import org.eclipse.ocl.pivot.VariableDeclaration;
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

		public CSEMappedElement(@NonNull CommonSubExpressionAnalysis cseAnalysis, int height, @NonNull Map<@NonNull TypedElement, @NonNull CSEElement> key2element) {
			super(cseAnalysis, height);
			this.key2element = key2element;
		}

		public @NonNull CSEElement getElement(@NonNull TypedElement key) {
			return ClassUtil.nonNullState(key2element.get(key));
		}
	}

	/**
	 * A CSEMapLiteralPartElement is the irregular non-TypedElement implementation for a MapLiteralPart.
	 */
	// XXX Delete me once MapLiteralPart is a TypedElement
	@Deprecated
	public static class CSEMapLiteralPartElement extends AbstractCSEElement
	{
		protected final @NonNull MapLiteralPart part;

		public CSEMapLiteralPartElement(@NonNull CommonSubExpressionAnalysis cseAnalysis, @NonNull MapLiteralPart element, int height) {
			super(cseAnalysis, height);
			this.part = element;
		}

		@Override
		public void toString(@NonNull StringBuilder s, int lengthLimit) {
			s.append(getHeight());
			s.append("#");
			s.append(part);
		}
	}

	/**
	 * A CSESafeElement is a safe navigation wrapper. It has a single input which is rge unsafe CSE.
	 */
	public static class CSESafeElement extends AbstractCSEElement
	{
		public CSESafeElement(@NonNull AbstractCSEElement cseElement) {
			super(cseElement.cseAnalysis, cseElement.height+1);
		}

		@Override
		public boolean isSafe() {
			return true;
		}
	}

	/**
	 * A CSESimpleElement is the default concrete implementation of AbstractCSEElement.
	 */
	public static class CSESimpleElement extends AbstractCSEElement
	{
		public CSESimpleElement(@NonNull CommonSubExpressionAnalysis cseAnalysis, int height) {
			super(cseAnalysis, height);
		}
	}

	/**
	 * A CSEValueElement adds a redundant value cache to aid debugging.
	 */
	public static class CSEValueElement extends CSESimpleElement
	{
		protected final @NonNull Object value;

		public CSEValueElement(@NonNull CommonSubExpressionAnalysis cseAnalysis, @NonNull Object value) {
			super(cseAnalysis, 0);
			this.value = value;
		}
	}

	/**
	 * A CSEVariableElement adds a redundant variable cache to aid debugging.
	 */
	public static class CSEVariableElement extends CSESimpleElement
	{
		protected final @NonNull VariableDeclaration variable;

		public CSEVariableElement(@NonNull CommonSubExpressionAnalysis cseAnalysis, @NonNull VariableDeclaration variable) {
			super(cseAnalysis, 0);
			this.variable = variable;
		}
	}

	protected final @NonNull CommonSubExpressionAnalysis cseAnalysis;

	/**
	 * The peak depth of child CSE elements whose values contribute to the value of this CSE.
	 */
	private final int height;

	/**
	 * The common sub-expression that has a safe navigation prefix wrt this one.
	 */
	private @Nullable CSESafeElement safe = null;

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
	private final @NonNull List<@NonNull CSEElement> outputs = new ArrayList<>();

	/**
	 * The elements for which this is the CSE element.
	 *
	 * XXX FIXME this could be TypedElement if MapLiteralPart was a TypedElement.
	 */
	private final @NonNull List<@NonNull TypedElement> elements = new ArrayList<>();

	protected AbstractCSEElement(@NonNull CommonSubExpressionAnalysis cseAnalysis, int height) {
		this.cseAnalysis = cseAnalysis;
		this.height = height;
	}

	@Override
	public void addElement(@NonNull TypedElement element) {
		if ((element instanceof CallExp) && ((CallExp)element).isIsSafe()) {
			if (!isSafe()) {
				getSafeCSE().addElement(element);
			}
		}
		else {
		//	assert !isSafe();		-- LetVariable my be initialized with a safe CSE
		}
		assert !elements.contains(element);
		elements.add(element);
	}

	protected void addInput(@NonNull CSEElement inputCSE) {
	//	assert !isSafe();
		List<@NonNull CSEElement> inputs2 = inputs;
		if (inputs2 == null) {
			inputs = inputs2 = new ArrayList<>();
		}
		inputs2.add(inputCSE);		// No prohibition on duplicates e.g. f(1,1) or Sequence{1,1}
		((AbstractCSEElement)inputCSE).outputs.add(this);
	}

//	private void addOutput(@NonNull CSEElement outputCSE) {
	//	assert !isSafe();
	//	assert !outputs.contains(outputExp);
//		outputs.add(outputCSE);
//	}

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
			cseElement = new CSESimpleElement(cseAnalysis, maxHeight+1);
			cseElement.addInput(this);
			cseElement.addInput(thenCSE);
			cseElement.addInput(elseCSE);
			arguments2cse.put(argumentCSEs, cseElement);
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
		CSESimpleElement cseSimpleElement = arguments2cse.get(argumentCSEs);
		if (cseSimpleElement == null) {
			int maxHeight = this.height;
			for (@Nullable CSEElement argumentCSE : argumentCSEs) {
				if (argumentCSE != null) {				// null for auto-initialized iterator variables
					int argHeight = argumentCSE.getHeight();
					if (argHeight > maxHeight) {
						maxHeight = argHeight;
					}
				}
			}
			cseSimpleElement = new CSESimpleElement(cseAnalysis, maxHeight+1);
			cseSimpleElement.addInput(this);
			for (@Nullable CSEElement argumentCSE : argumentCSEs) {
				if (argumentCSE != null) {				// null for auto-initialized iterator variables
					cseSimpleElement.addInput(argumentCSE);
				}
			}
			arguments2cse.put(argumentCSEs, cseSimpleElement);
		}
		CSEElement cseElement = callExp.isIsSafe() ? cseSimpleElement.getSafeCSE() : cseSimpleElement;
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
		CSESimpleElement cseSimpleElement = property2cse2.get(property);
		if (cseSimpleElement == null) {
			cseSimpleElement = new CSESimpleElement(cseAnalysis, height+1);
			cseSimpleElement.addInput(this);
			property2cse2.put(property, cseSimpleElement);
		}
		CSEElement cseElement = navigationCallExp.isIsSafe() ? cseSimpleElement.getSafeCSE() : cseSimpleElement;
		return cseElement;
	}

	@Override
	public @NonNull CSESafeElement getSafeCSE() {
		assert !isSafe();
		CSESafeElement safe2 = safe;
		if (safe2 == null) {
			safe = safe2 = new CSESafeElement(this);
			safe2.addInput(this);
		}
		return safe2;
	}

	@Override
	public boolean isSafe() {
		return false;
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
		if (elements.size() > 0) {
			s.append(elements.get(0));
		}
		else {
			s.append("empty");
		}
	}
}
