/**
 * Copyright (c) 2021 Willink Transformations and others.
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
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.IfExp;
import org.eclipse.ocl.pivot.NavigationCallExp;
import org.eclipse.ocl.pivot.OCLExpression;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.utilities.PivotUtil;

/**
 * @since 1.15
 */
public abstract class AbstractCSEElement<E extends Element> implements CSEElement
{
	protected static int computeHeight(@NonNull Iterable<@NonNull CSEElement> elements) {
		int maxHeight = 0;
		for (@NonNull CSEElement element : elements) {
			int height = element.getHeight();
			if (height > maxHeight) {
				maxHeight = height;
			}
		}
		return maxHeight + 1;
	}

	protected final @NonNull CommonSubExpressionAnalysis cseAnalysis;
	protected final @NonNull E exemplar;
	private final int height;

	/**
	 * The common sub-expressions that are an if/iteration/operation navigation away from this common sub-expression.
	 * "if" is indexed by the null operation. uninitialized iterators have null argument CSEs.
	 */
	private /*@LazyNonNull*/ Map<@Nullable Operation, @NonNull Map<@NonNull List<@Nullable CSEElement>, @NonNull CSEExpressionElement>> callable2arguments2cse = null;

	/**
	 * The common sub-expressions that are a property navigation away from this common sub-expression.
	 */
	private /*@LazyNonNull*/ Map<@NonNull Property, @NonNull CSEElement> property2cse = null;

	/**
	 * The CSEs used to compute this CSE. The primary source first, rest in iteratir, accumulator, argument, body order
	 */
	private @Nullable List<@NonNull CSEElement> inputs = null;

	/**
	 * The expressions that compute from this CSE.
	 */
	private final @NonNull List<@NonNull OCLExpression> outputs = new ArrayList<>();


	protected AbstractCSEElement(@NonNull CommonSubExpressionAnalysis cseAnalysis, @NonNull E exemplar, int height) {
		this.cseAnalysis = cseAnalysis;
		this.exemplar = exemplar;
		this.height = height;
	}

	protected AbstractCSEElement(@NonNull AbstractCSEElement<?> parent, @NonNull E exemplar, int height) {
		this.cseAnalysis = parent.cseAnalysis;
		addInput(parent);
		this.exemplar = exemplar;
		this.height = height;
	}

	protected void addInput(@NonNull CSEElement inputCSE) {
		List<@NonNull CSEElement> inputs2 = inputs;
		if (inputs2 == null) {
			inputs = inputs2 = new ArrayList<>();
		}
		inputs2.add(inputCSE);
	}

	protected void addOutput(@NonNull OCLExpression outputExp) {
		assert !outputs.contains(outputExp);
		outputs.add(outputExp);
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
	public @Nullable Iterable<@NonNull CSEElement> getInputs() {
		return inputs;
	}

	@Override
	public @NonNull E getElement() {
		return exemplar;
	}

	@Override
	public int getHeight() {
		return height;
	}

	@Override
	public @NonNull CSEElement getIfCSE(@NonNull IfExp ifExp, @NonNull CSEElement thenCSE, @NonNull CSEElement elseCSE) {
		Map<@Nullable Operation, @NonNull Map<@NonNull List<@Nullable CSEElement>, @NonNull CSEExpressionElement>> callable2arguments2cse2 = callable2arguments2cse;
		if (callable2arguments2cse2 == null) {
			callable2arguments2cse2 = callable2arguments2cse = new HashMap<>();
		}
		Map<@NonNull List<@Nullable CSEElement>, @NonNull CSEExpressionElement> arguments2cse = callable2arguments2cse2.get(null);
		if (arguments2cse == null) {
			arguments2cse = new HashMap<>();
			callable2arguments2cse2.put(null, arguments2cse);
		}
		List<@Nullable CSEElement> argumentCSEs = new ArrayList<>();
		argumentCSEs.add(thenCSE);
		argumentCSEs.add(elseCSE);
		CSEExpressionElement cseElement = arguments2cse.get(argumentCSEs);
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
			cseElement = new CSEExpressionElement(this, ifExp, maxHeight+1);
			cseElement.addInput(thenCSE);
			cseElement.addInput(elseCSE);
			arguments2cse.put(argumentCSEs, cseElement);
		}
		return cseElement;
	}

	@Override
	public @NonNull CSEElement getOperationCSE(@NonNull CallExp callExp, @NonNull Operation operation, @NonNull List<@Nullable CSEElement> argumentCSEs) {
		Map<@Nullable Operation, @NonNull Map<@NonNull List<@Nullable CSEElement>, @NonNull CSEExpressionElement>> callable2arguments2cse2 = callable2arguments2cse;
		if (callable2arguments2cse2 == null) {
			callable2arguments2cse2 = callable2arguments2cse = new HashMap<>();
		}
		Map<@NonNull List<@Nullable CSEElement>, @NonNull CSEExpressionElement> arguments2cse = callable2arguments2cse2.get(operation);
		if (arguments2cse == null) {
			arguments2cse = new HashMap<>();
			callable2arguments2cse2.put(operation, arguments2cse);
		}
		CSEExpressionElement cseElement = arguments2cse.get(argumentCSEs);
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
			cseElement = new CSEExpressionElement(this, callExp, maxHeight+1);
			for (@Nullable CSEElement argumentCSE : argumentCSEs) {
				if (argumentCSE != null) {				// null for auto-initialized iterator variables
					cseElement.addInput(argumentCSE);
				}
			}
			arguments2cse.put(argumentCSEs, cseElement);
			addOutput(callExp);
		}
		return cseElement;
	}

	@Override
	public @NonNull Iterable<@NonNull OCLExpression> getOutputs() {
		return outputs;
	}

//	@Override
//	public @Nullable CSEElement getParent() {
//		return parent;
//	}

	@Override
	public @NonNull CSEElement getPropertyCSE(@NonNull NavigationCallExp navigationCallExp) {
		Map<@NonNull Property, @NonNull CSEElement> property2cse2 = property2cse;
		if (property2cse2 == null) {
			property2cse2 = property2cse = new HashMap<>();
		}
		Property property = PivotUtil.getReferredProperty(navigationCallExp);
		CSEElement cseElement = property2cse2.get(property);
		if (cseElement == null) {
			cseElement = new CSEExpressionElement(this, navigationCallExp, height+1);
			property2cse2.put(property, cseElement);
			addOutput(navigationCallExp);
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
		s.append(exemplar);
	}
}
