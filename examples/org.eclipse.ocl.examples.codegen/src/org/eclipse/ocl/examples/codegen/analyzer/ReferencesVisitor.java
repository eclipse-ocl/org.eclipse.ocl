/*******************************************************************************
 * Copyright (c) 2013, 2019 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.codegen.analyzer;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.cgmodel.CGCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGCastExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGConstant;
import org.eclipse.ocl.examples.codegen.cgmodel.CGConstantExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGEcoreOperation;
import org.eclipse.ocl.examples.codegen.cgmodel.CGElement;
import org.eclipse.ocl.examples.codegen.cgmodel.CGElementId;
import org.eclipse.ocl.examples.codegen.cgmodel.CGExecutorOperation;
import org.eclipse.ocl.examples.codegen.cgmodel.CGExecutorProperty;
import org.eclipse.ocl.examples.codegen.cgmodel.CGExecutorType;
import org.eclipse.ocl.examples.codegen.cgmodel.CGIsEqual2Exp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGIsEqualExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGIsInvalidExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGIsKindOfExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGIterationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGNamedElement;
import org.eclipse.ocl.examples.codegen.cgmodel.CGOperationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGOppositePropertyCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGPropertyCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGTemplateParameterExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGTypeExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGTypedElement;
import org.eclipse.ocl.examples.codegen.cgmodel.CGVariableExp;
import org.eclipse.ocl.examples.codegen.cgmodel.util.AbstractExtendingCGModelVisitor;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.OperationCallExp;
import org.eclipse.ocl.pivot.TypeExp;

/**
 * The ReferencesVisitor compute a list of objects referenced by (but not contained by or containing) the visited object
 * that contribute to that objects identity. The computed list may contain null elements to ensure that the returned lists
 * by two different objects exhibit positional equivalence.
 */
public class ReferencesVisitor extends AbstractExtendingCGModelVisitor<@NonNull List<@Nullable Object>, @Nullable Object>
{
	public static final @NonNull ReferencesVisitor INSTANCE = new ReferencesVisitor(null);

	protected ReferencesVisitor(@Nullable Object context) {
		super(context);
	}

	protected @NonNull List<@Nullable Object> append(@NonNull List<@Nullable Object> iterables, @Nullable Object... objects) {
		if (objects != null) {
			for (@Nullable Object object : objects) {
				iterables.add(object);			// Nulls too to preserve positional equivalence between alternate lists
			}
		}
		return iterables;
	}

	@Override
	public @NonNull List<@Nullable Object> visiting(@NonNull CGElement visitable) {
		throw new UnsupportedOperationException("Unsupported " + getClass().getName() + " visit");
	}

	@Override
	public @NonNull List<@Nullable Object> visitCGCallExp(@NonNull CGCallExp cgElement) {
		return append(super.visitCGCallExp(cgElement), cgElement.getSource());
	}

	@Override
	public @NonNull List<@Nullable Object> visitCGCastExp(@NonNull CGCastExp cgElement) {
		return append(super.visitCGCastExp(cgElement), cgElement.getExecutorType());
	}

	@Override
	public @NonNull List<@Nullable Object> visitCGConstant(@NonNull CGConstant cgElement) {
		return append(super.visitCGConstant(cgElement), cgElement.getConstantValue());
	}

	@Override
	public @NonNull List<@Nullable Object> visitCGConstantExp(@NonNull CGConstantExp cgElement) {
		return append(super.visitCGConstantExp(cgElement), cgElement.getReferredConstant());
	}

	@Override
	public @NonNull List<@Nullable Object> visitCGEcoreOperation(@NonNull CGEcoreOperation cgElement) {
		return append(super.visitCGEcoreOperation(cgElement), cgElement.getEOperation());
	}

	@Override
	public @NonNull List<@Nullable Object> visitCGElement(@NonNull CGElement cgElement) {
		return new ArrayList<@Nullable Object>();
	}

	@Override
	public @NonNull List<@Nullable Object> visitCGElementId(@NonNull CGElementId cgElement) {
		return append(super.visitCGElementId(cgElement), cgElement.getASTypeId());
	}

	@Override
	public @NonNull List<@Nullable Object> visitCGExecutorOperation(@NonNull CGExecutorOperation cgElement) {
		return append(super.visitCGExecutorOperation(cgElement), cgElement.getUnderlyingOperationId());
	}

	@Override
	public @NonNull List<@Nullable Object> visitCGExecutorProperty(@NonNull CGExecutorProperty cgElement) {
		return append(super.visitCGExecutorProperty(cgElement), cgElement.getUnderlyingPropertyId());
	}

	@Override
	public @NonNull List<@Nullable Object> visitCGExecutorType(@NonNull CGExecutorType cgElement) {
		return append(super.visitCGExecutorType(cgElement), cgElement.getUnderlyingTypeId());
	}

	@Override
	public @NonNull List<@Nullable Object> visitCGIsEqual2Exp(@NonNull CGIsEqual2Exp cgElement) {
		List<@Nullable Object> elements = super.visitCGIsEqual2Exp(cgElement);
		Element ast = cgElement.getAst();
		if (ast instanceof OperationCallExp) {
			elements.add(((OperationCallExp)ast).getReferredOperation());
		}
		return elements;
	}

	@Override
	public @NonNull List<@Nullable Object> visitCGIsEqualExp(@NonNull CGIsEqualExp cgElement) {
		List<@Nullable Object> elements = super.visitCGIsEqualExp(cgElement);
		Element ast = cgElement.getAst();
		if (ast instanceof OperationCallExp) {
			elements.add(((OperationCallExp)ast).getReferredOperation());
		}
		return elements;
	}

	@Override
	public @NonNull List<@Nullable Object> visitCGIsInvalidExp(@NonNull CGIsInvalidExp cgElement) {
		List<@Nullable Object> elements = super.visitCGIsInvalidExp(cgElement);
		Element ast = cgElement.getAst();
		if (ast instanceof OperationCallExp) {
			elements.add(((OperationCallExp)ast).getReferredOperation());
		}
		return elements;
	}

	@Override
	public @NonNull List<@Nullable Object> visitCGIsKindOfExp(@NonNull CGIsKindOfExp cgElement) {
		List<@Nullable Object> elements = super.visitCGIsKindOfExp(cgElement);
		Element ast = cgElement.getAst();
		if (ast instanceof OperationCallExp) {		// FIXME on the fly CG trees have no AS
			elements.add(((OperationCallExp)ast).getReferredOperation());
		}
		return elements;
	}

	@Override
	public @NonNull List<@Nullable Object> visitCGIterationCallExp(@NonNull CGIterationCallExp cgElement) {
		return append(super.visitCGIterationCallExp(cgElement), cgElement.getReferredIteration(), cgElement.getBody());
	}

	@Override
	public @NonNull List<@Nullable Object> visitCGNamedElement(@NonNull CGNamedElement cgElement) {
		return append(super.visitCGNamedElement(cgElement), cgElement.getName());
	}

	@Override
	public @NonNull List<@Nullable Object> visitCGOperationCallExp(@NonNull CGOperationCallExp cgElement) {
		List<@Nullable Object> elements = append(super.visitCGOperationCallExp(cgElement), cgElement.getReferredOperation());
		elements.addAll(cgElement.getArguments());
		return elements;
	}

	@Override
	public @NonNull List<@Nullable Object> visitCGOppositePropertyCallExp(@NonNull CGOppositePropertyCallExp cgElement) {
		return append(super.visitCGOppositePropertyCallExp(cgElement), cgElement.getReferredProperty());
	}

	@Override
	public @NonNull List<@Nullable Object> visitCGPropertyCallExp(@NonNull CGPropertyCallExp cgElement) {
		return append(super.visitCGPropertyCallExp(cgElement), cgElement.getReferredProperty());
	}

	@Override
	public @NonNull List<@Nullable Object> visitCGTemplateParameterExp(@NonNull CGTemplateParameterExp cgTemplateParameterExp) {
		return append(super.visitCGTemplateParameterExp(cgTemplateParameterExp), ((TypeExp)cgTemplateParameterExp.getAst()).getReferredType().getTypeId());
	}

	@Override
	public @NonNull List<@Nullable Object> visitCGTypeExp(@NonNull CGTypeExp cgElement) {
		return append(super.visitCGTypeExp(cgElement), ((TypeExp)cgElement.getAst()).getReferredType().getTypeId());
	}

	@Override
	public @NonNull List<@Nullable Object> visitCGTypedElement(@NonNull CGTypedElement cgElement) {
		return append(super.visitCGTypedElement(cgElement), cgElement.getASTypeId());
	}

	@Override
	public @NonNull List<@Nullable Object> visitCGVariableExp(@NonNull CGVariableExp cgElement) {
		return append(super.visitCGVariableExp(cgElement), cgElement.getReferredVariable());
	}
}