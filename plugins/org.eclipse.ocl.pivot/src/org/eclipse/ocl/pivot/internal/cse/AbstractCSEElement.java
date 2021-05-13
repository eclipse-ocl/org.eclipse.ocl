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
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.CallExp;
import org.eclipse.ocl.pivot.IfExp;
import org.eclipse.ocl.pivot.NavigationCallExp;
import org.eclipse.ocl.pivot.OCLExpression;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.TypedElement;
import org.eclipse.ocl.pivot.utilities.PivotUtil;

/**
 * @since 1.15
 */
public abstract class AbstractCSEElement<E extends TypedElement, C extends OCLExpression> implements CSEElement
{
	protected final @NonNull CommonSubExpressionAnalysis cseAnalysis;
	protected final @Nullable AbstractCSEElement<?, ?> parent;
	protected final @NonNull E exemplar;

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
	 * The common sub-expressions that are a property navigation away from this common sub-expression.
	 */
	private /*@LazyNonNull*/ List<@NonNull CSEElement> children = null;

	private final @NonNull List<@NonNull C> clients = new ArrayList<>();


	protected AbstractCSEElement(@NonNull CommonSubExpressionAnalysis cseAnalysis, @NonNull E exemplar) {
		this.cseAnalysis = cseAnalysis;
		this.parent = null;
		this.exemplar = exemplar;
	}

	protected AbstractCSEElement(@NonNull AbstractCSEElement<?, ?> parent, @NonNull E exemplar) {
		this.cseAnalysis = parent.cseAnalysis;
		this.parent = parent;
		this.exemplar = exemplar;
	}

	private void addChild(@NonNull CSEElement cseElement) {
		List<@NonNull CSEElement> children2 = children;
		if (children2 == null) {
			children2 = children = new ArrayList<>();
		}
		children2.add(cseElement);
	}

	protected void addClient(@NonNull C client) {
		assert !clients.contains(client);
		clients.add(client);
	}

	@Override
	public @NonNull Iterable<@NonNull CSEElement> getChildren() {
		if (children != null) {
			return children;
		}
		else {
			return Collections.emptyList();
		}
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
			cseElement = new CSEExpressionElement(this, ifExp);
			arguments2cse.put(argumentCSEs, cseElement);
		}
		return cseElement;
	}

	@Override
	public @NonNull E getObject() {
		return exemplar;
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
			cseElement = new CSEExpressionElement(this, callExp);
			arguments2cse.put(argumentCSEs, cseElement);
			addChild(cseElement);
		}
		return cseElement;
	}

	@Override
	public @NonNull CommonSubExpressionAnalysis getCommonSubExpressionAnalysis() {
		return cseAnalysis;
	}

	@Override
	public @Nullable CSEElement getParent() {
		return parent;
	}

	@Override
	public @NonNull CSEElement getPropertyCSE(@NonNull NavigationCallExp navigationCallExp) {
		Map<@NonNull Property, @NonNull CSEElement> property2cse2 = property2cse;
		if (property2cse2 == null) {
			property2cse2 = property2cse = new HashMap<>();
		}
		Property property = PivotUtil.getReferredProperty(navigationCallExp);
		CSEElement cseElement = property2cse2.get(property);
		if (cseElement == null) {
			cseElement = new CSEExpressionElement(this, navigationCallExp);
			property2cse2.put(property, cseElement);
			addChild(cseElement);
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
		s.append(exemplar);
	}
}
