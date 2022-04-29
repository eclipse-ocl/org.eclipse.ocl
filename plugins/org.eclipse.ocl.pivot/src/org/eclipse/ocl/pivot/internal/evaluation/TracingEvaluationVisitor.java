/*******************************************************************************
 * Copyright (c) 2010, 2018 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/

package org.eclipse.ocl.pivot.internal.evaluation;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.AssociationClassCallExp;
import org.eclipse.ocl.pivot.BooleanLiteralExp;
import org.eclipse.ocl.pivot.CollectionItem;
import org.eclipse.ocl.pivot.CollectionLiteralExp;
import org.eclipse.ocl.pivot.CollectionRange;
import org.eclipse.ocl.pivot.Constraint;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.EnumLiteralExp;
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.IfExp;
import org.eclipse.ocl.pivot.IntegerLiteralExp;
import org.eclipse.ocl.pivot.InvalidLiteralExp;
import org.eclipse.ocl.pivot.IterateExp;
import org.eclipse.ocl.pivot.IteratorExp;
import org.eclipse.ocl.pivot.LetExp;
import org.eclipse.ocl.pivot.MapLiteralExp;
import org.eclipse.ocl.pivot.MapLiteralPart;
import org.eclipse.ocl.pivot.MessageExp;
import org.eclipse.ocl.pivot.NullLiteralExp;
import org.eclipse.ocl.pivot.OperationCallExp;
import org.eclipse.ocl.pivot.OppositePropertyCallExp;
import org.eclipse.ocl.pivot.PropertyCallExp;
import org.eclipse.ocl.pivot.RealLiteralExp;
import org.eclipse.ocl.pivot.ShadowExp;
import org.eclipse.ocl.pivot.StateExp;
import org.eclipse.ocl.pivot.StringLiteralExp;
import org.eclipse.ocl.pivot.TupleLiteralExp;
import org.eclipse.ocl.pivot.TupleLiteralPart;
import org.eclipse.ocl.pivot.TypeExp;
import org.eclipse.ocl.pivot.UnlimitedNaturalLiteralExp;
import org.eclipse.ocl.pivot.UnspecifiedValueExp;
import org.eclipse.ocl.pivot.Variable;
import org.eclipse.ocl.pivot.VariableExp;
import org.eclipse.ocl.pivot.evaluation.EvaluationVisitor;
import org.eclipse.ocl.pivot.evaluation.Executor;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.helper.HelperUtil;
import org.eclipse.ocl.pivot.utilities.MetamodelManager;

/**
 * A decorator for evaluation visitors that is installed when evaluation tracing
 * is enabled, to trace interim evaluation results to the console.
 */
public class TracingEvaluationVisitor extends EvaluationVisitorDecorator implements EvaluationVisitor.EvaluationVisitorExtension
{
	private int indentDepth = 0;

	/**
	 * Initializes me with the visitor whose evaluation I trace to the console.
	 *
	 * @param decorated a real evaluation visitor
	 */
	public TracingEvaluationVisitor(@NonNull EvaluationVisitor decorated) {
		super(decorated);
	}

	/** @deprecated moved to Executor
	 * @since 1.1*/
	@Override
	@Deprecated
	public @NonNull Executor getExecutor() {
		return ((EvaluationVisitor.EvaluationVisitorExtension)delegate).getExecutor();
	}

	/** @deprecated moved to Executor */
	@Override
	@Deprecated
	public @NonNull MetamodelManager getMetamodelManager() {
		return delegate.getMetamodelManager();
	}

	/**
	 * @since 1.18
	 */
	protected void trace(@NonNull String indent, @NonNull String message) {
		HelperUtil.trace(indent + message);
	}

	/**
	 * @since 1.18
	 */
	protected @Nullable Object trace(@NonNull Element element) {
		int savedIndent = indentDepth++;
		try {
			StringBuilder s = new StringBuilder();
			for (int i = 0; i < savedIndent; i++) {
				s.append("  ");
			}
			String indent = s.toString();
			trace(indent, element.eClass().getName() + ": " + element);
			try {
				Object value = element.accept(delegate);
				if (value == null) {
					value = TypeId.OCL_INVALID_NAME;
				}
				trace(indent, "=> " + String.valueOf(value));
				return value;
			} catch (Throwable e) {
				try {
					trace(indent, "!! " + e);
				} catch (Throwable e2) {}
				throw e;
			}
		} catch (Throwable e) {
			throw e;
		} finally {
			indentDepth = savedIndent;
		}
	}

	@Deprecated
	/* @deprecated drop argument to guarantee indented evaluation and exception detection */
	protected @Nullable Object trace(@NonNull Element element, @Nullable Object value) {
		try {
			HelperUtil.trace(element.eClass().getName() + ": " + element); //$NON-NLS-1$
			if (value == null) {
				value = TypeId.OCL_INVALID_NAME;
			}
			HelperUtil.trace("=> " + String.valueOf(value));
			return value;
		} catch (Throwable e) {
			throw e;
		}
	}

	@Override
	public @Nullable Object visitAssociationClassCallExp(@NonNull AssociationClassCallExp callExp) {
		return trace(callExp);
	}

	@Override
	public @Nullable Object visitBooleanLiteralExp(@NonNull BooleanLiteralExp literalExp) {
		return trace(literalExp);
	}

	@Override
	public @Nullable Object visitCollectionItem(@NonNull CollectionItem item) {
		return trace(item);
	}

	@Override
	public @Nullable Object visitCollectionLiteralExp(@NonNull CollectionLiteralExp literalExp) {
		return trace(literalExp);
	}

	@Override
	public @Nullable Object visitCollectionRange(@NonNull CollectionRange range) {
		return trace(range);
	}

	@Override
	public @Nullable Object visitConstraint(@NonNull Constraint constraint) {
		return trace(constraint);
	}

	@Override
	public Object visitElement(@NonNull Element element) {
		return trace(element);		// THis handles all derived cases - derivation are just an 'optimization'
	}

	@Override
	public @Nullable Object visitEnumLiteralExp(@NonNull EnumLiteralExp literalExp) {
		return trace(literalExp);
	}

	@Override
	public @Nullable Object visitExpressionInOCL(@NonNull ExpressionInOCL expression) {
		return trace(expression);
	}

	@Override
	public @Nullable Object visitIfExp(@NonNull IfExp ifExp) {
		return trace(ifExp);
	}

	@Override
	public @Nullable Object visitIntegerLiteralExp(@NonNull IntegerLiteralExp literalExp) {
		return trace(literalExp);
	}

	@Override
	public @Nullable Object visitInvalidLiteralExp(@NonNull InvalidLiteralExp literalExp) {
		return trace(literalExp);
	}

	@Override
	public Object visitIterateExp(@NonNull IterateExp iterateExp) {
		return trace(iterateExp);
	}

	@Override
	public Object visitIteratorExp(@NonNull IteratorExp iteratorExp) {
		return trace(iteratorExp);
	}

	@Override
	public @Nullable Object visitLetExp(@NonNull LetExp letExp) {
		return trace(letExp);
	}

	@Override
	public @Nullable Object visitMapLiteralExp(@NonNull MapLiteralExp literalExp) {
		return trace(literalExp);
	}

	@Override
	public @Nullable Object visitMapLiteralPart(@NonNull MapLiteralPart range) {
		return trace(range);
	}

	@Override
	public @Nullable Object visitMessageExp(@NonNull MessageExp messageExp) {
		return trace(messageExp);
	}

	@Override
	public @Nullable Object visitNullLiteralExp(@NonNull NullLiteralExp literalExp) {
		return trace(literalExp);
	}

	@Override
	public @Nullable Object visitOperationCallExp(@NonNull OperationCallExp callExp) {
		return trace(callExp);
	}

	@Override
	public @Nullable Object visitOppositePropertyCallExp(@NonNull OppositePropertyCallExp callExp) {
		return trace(callExp);
	}

	@Override
	public @Nullable Object visitPropertyCallExp(@NonNull PropertyCallExp callExp) {
		return trace(callExp);
	}

	@Override
	public @Nullable Object visitRealLiteralExp(@NonNull RealLiteralExp literalExp) {
		return trace(literalExp);
	}

	@Override
	public @Nullable Object visitShadowExp(@NonNull ShadowExp shadowExp) {
		return trace(shadowExp);
	}

	@Override
	public @Nullable Object visitStateExp(@NonNull StateExp stateExp) {
		return trace(stateExp);
	}

	@Override
	public @Nullable Object visitStringLiteralExp(@NonNull StringLiteralExp literalExp) {
		return trace(literalExp);
	}

	@Override
	public @Nullable Object visitTupleLiteralExp(@NonNull TupleLiteralExp literalExp) {
		return trace(literalExp);
	}

	@Override
	public @Nullable Object visitTupleLiteralPart(@NonNull TupleLiteralPart part) {
		return trace(part);
	}

	@Override
	public @Nullable Object visitTypeExp(@NonNull TypeExp typeExp) {
		return trace(typeExp);
	}

	@Override
	public @Nullable Object visitUnlimitedNaturalLiteralExp(@NonNull UnlimitedNaturalLiteralExp literalExp) {
		return trace(literalExp);
	}

	@Override
	public @Nullable Object visitUnspecifiedValueExp(@NonNull UnspecifiedValueExp unspecExp) {
		return trace(unspecExp);
	}

	@Override
	public @Nullable Object visitVariable(@NonNull Variable variable) {
		return trace(variable);
	}

	@Override
	public @Nullable Object visitVariableExp(@NonNull VariableExp variableExp) {
		return trace(variableExp);
	}
}
