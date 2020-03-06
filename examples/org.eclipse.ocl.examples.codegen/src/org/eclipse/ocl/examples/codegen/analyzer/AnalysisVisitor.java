/*******************************************************************************
 * Copyright (c) 2013, 2019 CEA LIST and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink(CEA LIST) - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.codegen.analyzer;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.cgmodel.CGCollectionExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGElement;
import org.eclipse.ocl.examples.codegen.cgmodel.CGExecutorType;
import org.eclipse.ocl.examples.codegen.cgmodel.CGIfExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGInvalid;
import org.eclipse.ocl.examples.codegen.cgmodel.CGIsEqual2Exp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGIsEqualExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGIsInvalidExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGIsKindOfExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGIsUndefinedExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGIterationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGLetExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGMapExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGOperation;
import org.eclipse.ocl.examples.codegen.cgmodel.CGOperationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGOppositePropertyCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGParameter;
import org.eclipse.ocl.examples.codegen.cgmodel.CGPropertyCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGShadowExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGTemplateParameterExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGTupleExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGTypeExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.cgmodel.CGVariable;
import org.eclipse.ocl.examples.codegen.cgmodel.CGVariableExp;
import org.eclipse.ocl.examples.codegen.cgmodel.util.AbstractExtendingCGModelVisitor;
import org.eclipse.ocl.examples.codegen.generator.LocalContext;
import org.eclipse.ocl.examples.codegen.utilities.CGUtil;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.TypeExp;
import org.eclipse.ocl.pivot.ids.ElementId;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.messages.PivotMessages;
import org.eclipse.ocl.pivot.utilities.ValueUtil;

/**
 * A CGElementVisitor handles the Pivot AST visits on behalf of a CodeGenAnalyzer.
 * Derived visitors may support an extended AST.
 */
public class AnalysisVisitor extends AbstractExtendingCGModelVisitor<@Nullable Object, @NonNull CodeGenAnalyzer>
{
	public AnalysisVisitor(@NonNull CodeGenAnalyzer analyzer) {
		super(analyzer);
	}

	@Override
	public @Nullable Object visitCGCollectionExp(@NonNull CGCollectionExp cgCollectionExp) {
		super.visitCGCollectionExp(cgCollectionExp);
		CGInvalid cgInvalidValue = cgCollectionExp.getInvalidValue();
		if (cgInvalidValue != null) {
			context.setConstant(cgCollectionExp, cgInvalidValue);
		}
		return null;
	}

	@Override
	public @Nullable Object visitCGElement(@NonNull CGElement cgElement) {
		for (@NonNull CGElement cgChild : cgElement.getChildren()) {
			cgChild.accept(this);
		}
		return null;
	}

	/*	@Override
	public void visitEnumLiteralExp(@NonNull EnumLiteralExp element) {
		CGElement thisAnalysis = context.getCurrentAnalysis();
		thisAnalysis.initHashSource(ClassUtil.nonNullModel(element.getReferredEnumLiteral()));
		return thisAnalysis;
	}*/

	@Override
	public @Nullable Object visitCGIfExp(@NonNull CGIfExp cgIfExp) {
		super.visitCGIfExp(cgIfExp);
		CGValuedElement cgCondition = context.getExpression(cgIfExp.getCondition());
		CGInvalid cgInvalidValue = cgCondition.getInvalidValue();
		if (cgInvalidValue != null) {
			CGUtil.replace(cgIfExp, context.createCGConstantExp(cgInvalidValue));
		}
		else if (cgCondition.isNull()) {
			context.setConstant(cgIfExp, context.getInvalid("Null cgCondition"));
		}
		else if (cgCondition.isTrue()) {
			CGValuedElement cgThen = context.getExpression(cgIfExp.getThenExpression());
			context.replace(cgIfExp, cgThen, "Null then-expression");
		}
		else if (cgCondition.isFalse()) {
			CGValuedElement cgElse = context.getExpression(cgIfExp.getElseExpression());
			context.replace(cgIfExp, cgElse, "Null else-expression");
		}
		else if (cgCondition.isConstant()) {
			ElementId asTypeId = cgCondition.getTypeId().getElementId();
			context.setConstant(cgIfExp, context.getInvalid(PivotMessages.TypedValueRequired, "Boolean", asTypeId));
		}
		else {
			CGValuedElement cgThen = context.getExpression(cgIfExp.getThenExpression());
			CGValuedElement cgElse = context.getExpression(cgIfExp.getElseExpression());
			if (cgThen.isEquivalentTo(cgElse) == Boolean.TRUE) {
				context.replace(cgIfExp, cgThen, "Null then/else-expression");
			}
		}
		return null;
	}

	@Override
	public @Nullable Object visitCGIsEqualExp(@NonNull CGIsEqualExp cgIsEqualExp) {
		super.visitCGIsEqualExp(cgIsEqualExp);
		CGValuedElement cgSource = cgIsEqualExp.getSource();
		if (cgSource == null) {
			return null;
		}
		CGValuedElement cgArgument = cgIsEqualExp.getArgument();
		if (cgArgument == null) {
			return null;
		}
		CGInvalid cgInvalidValue = cgSource.getInvalidValue();
		if (cgInvalidValue == null) {
			cgInvalidValue = cgArgument.getInvalidValue();
		}
		if (cgInvalidValue != null) {
			context.setConstant(cgIsEqualExp, cgInvalidValue);
		}
		else {
			CGValuedElement cgSourceValue = cgSource.getNamedValue();
			CGValuedElement cgArgumentValue = cgArgument.getNamedValue();
			Boolean isEqual = cgSourceValue.isEquivalentTo(cgArgumentValue);
			if (isEqual == Boolean.TRUE) {
				context.setConstant(cgIsEqualExp, context.getBoolean(!cgIsEqualExp.isNotEquals()));
			}
			else if (isEqual == Boolean.FALSE) {
				context.setConstant(cgIsEqualExp, context.getBoolean(cgIsEqualExp.isNotEquals()));
			}
			else if (cgSource.isTrue() && cgArgument.isNonNull() && (cgArgument.getASTypeId() == TypeId.BOOLEAN)) {
				context.replace(cgIsEqualExp, cgArgument, "Null term");
			}
			else if (cgArgument.isTrue() && cgSource.isNonNull() && (cgSource.getASTypeId() == TypeId.BOOLEAN)) {
				context.replace(cgIsEqualExp, cgSource, "Null term");
			}
		}
		return null;
	}

	@Override
	public @Nullable Object visitCGIsEqual2Exp(@NonNull CGIsEqual2Exp cgIsEqual2Exp) {
		super.visitCGIsEqual2Exp(cgIsEqual2Exp);
		CGValuedElement cgSource = cgIsEqual2Exp.getSource();
		if (cgSource == null) {
			return null;
		}
		CGValuedElement cgArgument = cgIsEqual2Exp.getArgument();
		if (cgArgument == null) {
			return null;
		}
		CGInvalid cgInvalidValue1 = cgSource.getInvalidValue();
		CGInvalid cgInvalidValue2 = cgArgument.getInvalidValue();
		if ((cgInvalidValue1 != null) && (cgInvalidValue2 != null)) {
			context.setConstant(cgIsEqual2Exp, context.getBoolean(true));
			return null;
		}
		if ((cgInvalidValue1 != null) && cgArgument.isNonInvalid()) {
			context.setConstant(cgIsEqual2Exp, context.getBoolean(false));
			return null;
		}
		if ((cgInvalidValue2 != null) && cgSource.isNonInvalid()) {
			context.setConstant(cgIsEqual2Exp, context.getBoolean(false));
			return null;
		}
		boolean isNull1 = cgSource.isNull();
		boolean isNull2 = cgArgument.isNull();
		if (isNull1 && isNull2) {
			context.setConstant(cgIsEqual2Exp, context.getBoolean(true));
			return null;
		}
		if (isNull1 && cgArgument.isNonNull()) {
			context.setConstant(cgIsEqual2Exp, context.getBoolean(false));
			return null;
		}
		if (isNull2 && cgSource.isNonNull()) {
			context.setConstant(cgIsEqual2Exp, context.getBoolean(false));
			return null;
		}
		CGValuedElement cgSourceValue = cgSource.getNamedValue();
		CGValuedElement cgArgumentValue = cgArgument.getNamedValue();
		Boolean isEqual = cgSourceValue.isEquivalentTo(cgArgumentValue);
		if (isEqual == Boolean.TRUE) {
			context.setConstant(cgIsEqual2Exp, context.getBoolean(true));
		}
		else if (isEqual == Boolean.FALSE) {
			context.setConstant(cgIsEqual2Exp, context.getBoolean(false));
		}
		else if (cgSource.isTrue() && cgArgument.isNonNull() && (cgArgument.getASTypeId() == TypeId.BOOLEAN)) {
			context.replace(cgIsEqual2Exp, cgArgument, "Null term");
		}
		else if (cgArgument.isTrue() && cgSource.isNonNull() && (cgSource.getASTypeId() == TypeId.BOOLEAN)) {
			context.replace(cgIsEqual2Exp, cgSource, "Null term");
		}
		return null;
	}

	@Override
	public @Nullable Object visitCGIsInvalidExp(@NonNull CGIsInvalidExp cgIsInvalidExp) {
		super.visitCGIsInvalidExp(cgIsInvalidExp);
		CGValuedElement cgSource = context.getExpression(cgIsInvalidExp.getSource());
		if (cgSource.isInvalid()) {
			context.setConstant(cgIsInvalidExp, context.getBoolean(true));
		}
		else if (cgSource.isNonInvalid()) {
			context.setConstant(cgIsInvalidExp, context.getBoolean(false));
		}
		return null;
	}

	@Override
	public Object visitCGIsKindOfExp(@NonNull CGIsKindOfExp object) {
		super.visitCGIsKindOfExp(object);
		// FIXME constant type conformance
		return null;
	}

	@Override
	public @Nullable Object visitCGIsUndefinedExp(@NonNull CGIsUndefinedExp cgIsUndefinedExp) {
		super.visitCGIsUndefinedExp(cgIsUndefinedExp);
		CGValuedElement cgSource = context.getExpression(cgIsUndefinedExp.getSource());
		if (cgSource.isInvalid() || cgSource.isNull()) {
			context.setConstant(cgIsUndefinedExp, context.getBoolean(true));
		}
		else if (cgSource.isNonInvalid() && cgSource.isNonNull()) {
			context.setConstant(cgIsUndefinedExp, context.getBoolean(false));
		}
		return null;
	}

	@Override
	public @Nullable Object visitCGLetExp(@NonNull CGLetExp cgLetExp) {
		super.visitCGLetExp(cgLetExp);
		CGValuedElement cgIn = context.getExpression(cgLetExp.getIn());
		//		if (cgLetExp.getInit().isGlobal()) { //Constant()) {
		//			CGUtils.replace(cgLetExp, in);
		//		}
		//		else {
		if (cgIn.isConstant()) {
			context.replace(cgLetExp, cgIn, "Null let-expression");
		}
		//		}
		return null;
	}

	@Override
	public @Nullable Object visitCGIterationCallExp(@NonNull CGIterationCallExp cgIterationCallExp) {
		super.visitCGIterationCallExp(cgIterationCallExp);
		CGValuedElement cgSource = context.getExpression(cgIterationCallExp.getSource());
		//		if (!cgIterationCallExp.isValidating()) {
		CGInvalid cgInvalidValue = cgSource.getInvalidValue();
		if (cgInvalidValue != null) {
			context.setConstant(cgIterationCallExp, cgInvalidValue);
			return null;
		}
		else if (cgSource.isNull()) {
			context.setConstant(cgIterationCallExp, context.getInvalid(PivotMessages.TypedValueRequired, TypeId.ITERABLE_NAME, ValueUtil.getTypeName(null)));
			return null;
		}
		//			for (@SuppressWarnings("null")@NonNull CGValuedElement cgArgument : cgIterationCallExp.getArguments()) {
		//				CGConstant constantArgument = cgArgument.getConstantValue();
		//				if ((constantArgument != null) && constantArgument.isInvalid()) {
		//					context.setConstant(cgIterationCallExp, constantArgument);
		//					return null;
		//				}
		//			}
		//		}
		return null;
	}

	@Override
	public @Nullable Object visitCGMapExp(@NonNull CGMapExp cgMapExp) {
		super.visitCGMapExp(cgMapExp);
		CGInvalid cgInvalidValue = cgMapExp.getInvalidValue();
		if (cgInvalidValue != null) {
			context.setConstant(cgMapExp, cgInvalidValue);
		}
		return null;
	}

	@Override
	public @Nullable Object visitCGOperation(@NonNull CGOperation cgOperation) {
		super.visitCGOperation(cgOperation);
		CGValuedElement cgBody = context.getExpression(cgOperation.getBody());
		for (@SuppressWarnings("null")@NonNull CGVariable cgParameter : cgOperation.getParameters()) {
			CGInvalid cgInvalidValue = cgParameter.getInvalidValue();
			if (cgInvalidValue != null) {
				context.setConstant(cgBody, cgInvalidValue);
				return null;
			}
		}
		return null;
	}

	@Override
	public @Nullable Object visitCGOperationCallExp(@NonNull CGOperationCallExp cgOperationCallExp) {
		super.visitCGOperationCallExp(cgOperationCallExp);
		CGValuedElement cgSource = context.getExpression(cgOperationCallExp.getSource());
		if (!cgOperationCallExp.isValidating()) {
			CGInvalid cgInvalidValue = cgSource.getInvalidValue();
			if (cgInvalidValue == null) {
				for (@SuppressWarnings("null")@NonNull CGValuedElement cgArgument : cgOperationCallExp.getArguments()) {
					cgInvalidValue = cgArgument.getInvalidValue();
					if (cgInvalidValue != null) {
						break;
					}
				}
			}
			if (cgInvalidValue != null) {
				context.setConstant(cgOperationCallExp, cgInvalidValue);
				return null;
			}
		}
		return null;
	}

	@Override
	public @Nullable Object visitCGOppositePropertyCallExp(@NonNull CGOppositePropertyCallExp cgPropertyCallExp) {
		super.visitCGOppositePropertyCallExp(cgPropertyCallExp);
		//		Property referredProperty = ClassUtil.nonNullModel(element.getReferredProperty());
		//		thisAnalysis.initHashSource(referredProperty);
		//		context.addNamedElement(referredProperty);
		CGValuedElement cgSource = context.getExpression(cgPropertyCallExp.getSource());
		CGInvalid cgInvalidValue = cgSource.getInvalidValue();
		if (cgInvalidValue != null) {
			context.setConstant(cgPropertyCallExp, cgInvalidValue);
		}
		else if (cgSource.isNull()) {
			context.setConstant(cgPropertyCallExp, context.getInvalid());
		}
		return null;
	}

	@Override
	public @Nullable Object visitCGParameter(@NonNull CGParameter object) {
		return null;
	}

	@Override
	public @Nullable Object visitCGPropertyCallExp(@NonNull CGPropertyCallExp cgPropertyCallExp) {
		super.visitCGPropertyCallExp(cgPropertyCallExp);
		//		Property referredProperty = ClassUtil.nonNullModel(element.getReferredProperty());
		//		thisAnalysis.initHashSource(referredProperty);
		//		context.addNamedElement(referredProperty);
		CGValuedElement cgSource = context.getExpression(cgPropertyCallExp.getSource());
		CGInvalid cgInvalidValue = cgSource.getInvalidValue();
		if (cgInvalidValue != null) {
			context.setConstant(cgPropertyCallExp, cgInvalidValue);
		}
		else if (cgSource.isNull()) {
			context.setConstant(cgPropertyCallExp, context.getInvalid());
		}
		return null;
	}

	@Override
	public @Nullable Object visitCGShadowExp(@NonNull CGShadowExp cgShadowExp) {
		//		ShadowExp pShadowExp = (ShadowExp) cgShadowExp.getPivot();
		//		Type pType = pShadowExp.getType();
		//		if (pType != null) {
		//			EObject eTarget = pType.getETarget();
		//			if (eTarget instanceof EClass) {
		//				LocalContext localContext = context.getCodeGenerator().getGlobalContext().getLocalContext(cgShadowExp);
		//				if (localContext != null) {
		//					CGExecutorType cgExecutorType = localContext.getExecutorType(pType);
		//					cgShadowExp.setReferredType(cgExecutorType);
		//					cgShadowExp.getDependsOn().add(cgExecutorType);
		//				}
		//			}
		//		}
		CGInvalid cgInvalidValue = cgShadowExp.getInvalidValue();
		if (cgInvalidValue != null) {
			context.setConstant(cgShadowExp, cgInvalidValue);
		}
		return null;
	}

	@Override
	public @Nullable Object visitCGTemplateParameterExp(@NonNull CGTemplateParameterExp cgTemplateParameterExp) {
		TypeExp pTypeExp = (TypeExp) cgTemplateParameterExp.getAst();
		Type referredType = pTypeExp.getReferredType();
		if (referredType != null) {
			LocalContext localContext = context.getCodeGenerator().getGlobalContext().getLocalContext(cgTemplateParameterExp);
			if (localContext != null) {
				CGValuedElement cgTemplateableElement = cgTemplateParameterExp.getTemplateableElement();
				//				cgTypeExp.setTypeId(cgExecutorType.getUnderlyingTypeId());
				cgTemplateParameterExp.getDependsOn().add(cgTemplateableElement);
			}
		}
		return super.visitCGTemplateParameterExp(cgTemplateParameterExp);
	}

	@Override
	public @Nullable Object visitCGTupleExp(@NonNull CGTupleExp cgTupleExp) {
		CGInvalid cgInvalidValue = cgTupleExp.getInvalidValue();
		if (cgInvalidValue != null) {
			context.setConstant(cgTupleExp, cgInvalidValue);
		}
		return null;
	}

	@Override
	public @Nullable Object visitCGTypeExp(@NonNull CGTypeExp cgTypeExp) {
		TypeExp pTypeExp = (TypeExp) cgTypeExp.getAst();
		Type referredType = pTypeExp.getReferredType();
		if (referredType != null) {
			LocalContext localContext = context.getCodeGenerator().getGlobalContext().getLocalContext(cgTypeExp);
			if (localContext != null) {
				CGExecutorType cgExecutorType = cgTypeExp.getExecutorType();
				//				cgTypeExp.setTypeId(cgExecutorType.getUnderlyingTypeId());
				cgTypeExp.getDependsOn().add(cgExecutorType);
			}
		}
		return super.visitCGTypeExp(cgTypeExp);
	}

	@Override
	public @Nullable Object visitCGVariable(@NonNull CGVariable cgVariable) {
		CGValuedElement cgInit = cgVariable.getInit();
		if (cgInit != null) {
			cgInit.accept(this);
		}
		return null;
	}

	@Override
	public @Nullable Object visitCGVariableExp(@NonNull CGVariableExp cgVariableExp) {
		super.visitCGVariableExp(cgVariableExp);
		if (cgVariableExp.isConstant() && !(cgVariableExp.getReferredVariable() instanceof CGParameter)) {
			context.setConstant(cgVariableExp, cgVariableExp.getNamedValue());
		}
		//		else if (cgVariableExp.isConstant()) {
		//			context.replace(cgVariableExp, cgVariableExp.getReferredVariable().getInit());
		//		}
		return null;
	}

	@Override
	public @Nullable CGElement visiting(@NonNull CGElement visitable) {
		throw new UnsupportedOperationException(getClass().getSimpleName() + ": " + visitable.getClass().getSimpleName());
	}
}
