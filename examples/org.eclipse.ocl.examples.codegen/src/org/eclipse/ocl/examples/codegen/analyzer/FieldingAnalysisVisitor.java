/*******************************************************************************
 * Copyright (c) 2022 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.codegen.analyzer;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.examples.codegen.analyzer.FieldingAnalyzer.ReturnState;
import org.eclipse.ocl.examples.codegen.cgmodel.CGCachedOperationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGCatchExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGConstantExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGElement;
import org.eclipse.ocl.examples.codegen.cgmodel.CGIsInvalidExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGIsUndefinedExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGIterationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGIterator;
import org.eclipse.ocl.examples.codegen.cgmodel.CGLetExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGModelFactory;
import org.eclipse.ocl.examples.codegen.cgmodel.CGNavigationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGOperation;
import org.eclipse.ocl.examples.codegen.cgmodel.CGOperationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGParameter;
import org.eclipse.ocl.examples.codegen.cgmodel.CGSourcedCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGThrowExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.cgmodel.CGVariable;
import org.eclipse.ocl.examples.codegen.cgmodel.CGVariableExp;
import org.eclipse.ocl.examples.codegen.cgmodel.util.AbstractExtendingCGModelVisitor;
import org.eclipse.ocl.examples.codegen.utilities.CGUtil;
import org.eclipse.ocl.pivot.Iteration;
import org.eclipse.ocl.pivot.Operation;

/*
 * An earlier version of this analysis determined variables accessed outside the prevailing containment tree
 * and assumed that only dodgy external accesses could cause invalid; dodgy internal accesses are handled internally.
 *
 * However invalid can arise despite all external variables being valid; divide-by-zero / index-out-of-bounds etc.
 *
 * An optimization attempted to propagate an invalid let variable init provided the corresponding let-in
 * would return it. This uses a lazy rather than mandatory catch on the let-init. Might be worth another go.
 */
/**
 * Perform the tree descent/ascent returning IS_CAUGHT / IS_THROWN / IS_VALID according to the mechanism by
 * which the result is passed and the potential invalidity of the result value.
 * <p>
 * Each node's isCaught is set according to whether an InvalidValueException may be passed mandating the use
 * of an Object to accommodate the invalid.
 */
public class FieldingAnalysisVisitor extends AbstractExtendingCGModelVisitor<@NonNull ReturnState, @NonNull FieldingAnalyzer>
{
	/**
	 * The required return state. Polymorphism is not used for the alternatives to allow derived AnalysisVisitor
	 * implementations to overload just once rather than three times.
	 */
	protected @NonNull ReturnState requiredReturn;

	public FieldingAnalysisVisitor(@NonNull FieldingAnalyzer context, @NonNull ReturnState requiredReturn) {
		super(context);
		this.requiredReturn = requiredReturn;
	}

	protected @NonNull FieldingAnalysisVisitor getMayBeThrownVisitor() {
		return context.mayBeThrown;
	}

	protected @NonNull FieldingAnalysisVisitor getMustBeCaughtVisitor() {
		return context.mustBeCaught;
	}

	protected @NonNull FieldingAnalysisVisitor getMustBeThrownVisitor() {
		return context.mustBeThrown;
	}

	protected @NonNull FieldingAnalysisVisitor getNestedVisitor(@NonNull CGOperation cgOperation) {
		ReturnState returnState = cgOperation.getCallingConvention().getRequiredReturn(cgOperation);
		switch (returnState) {
			case IS_CAUGHT: return context.mustBeCaught;
			case IS_THROWN: return context.mustBeThrown;
			default: return context.mayBeThrown;
		}
	}

	protected void insertCatch(@NonNull CGValuedElement cgChild) {
		assert !(cgChild instanceof CGCatchExp) : "double catch is redundant";
		assert !(cgChild instanceof CGVariableExp) : "must catch variable not its access";
		if (!cgChild.isNonInvalid()) {
			CGCatchExp cgCatchExp = CGModelFactory.eINSTANCE.createCGCatchExp();
			cgCatchExp.setCaught(true);
			CGUtil.wrap(cgCatchExp, cgChild);
		}
	}

	protected void insertThrow(@NonNull CGValuedElement cgChild) {
		assert !(cgChild instanceof CGThrowExp) : "double throw is redundant";
	//	assert cgChild.isCaught() : "cannot throw if not caught";
		if (!cgChild.isNonInvalid()) {
			CGThrowExp cgThrowExp = CGModelFactory.eINSTANCE.createCGThrowExp();
			cgThrowExp.setCaught(false);
			CGUtil.wrap(cgThrowExp, cgChild);
		}
	}

	protected @NonNull ReturnState requiredReturn() {
		return requiredReturn;
	}

	@SuppressWarnings("null")
	@Override
	public @NonNull String toString() {
		return requiredReturn.toString();
	}

	@Override
	public @NonNull ReturnState visiting(@NonNull CGElement visitable) {
		throw new UnsupportedOperationException(getClass().getSimpleName() + ": " + visitable.getClass().getSimpleName());
	}

	@Override
	public @NonNull ReturnState visit(@NonNull CGElement cgElement) {
		EObject oldEContainer = cgElement.eContainer();
		if (cgElement instanceof CGCachedOperationCallExp) {
			getClass();		// XXX
		}
		ReturnState returnState = cgElement.accept(this);
		if ((cgElement instanceof CGValuedElement) && (cgElement.eContainer() == oldEContainer)) {	// skip if already wrapped
			CGValuedElement cgValuedElement = (CGValuedElement)cgElement;
			if (!cgValuedElement.isNonInvalid()) {
				if (requiredReturn == ReturnState.IS_CAUGHT) {
					if (!returnState.isSuitableFor(ReturnState.IS_CAUGHT)) {
						insertCatch(cgValuedElement);
						cgValuedElement.setCaught(true);
						return ReturnState.IS_CAUGHT;
					}
				}
				else if (requiredReturn == ReturnState.IS_THROWN) {
					if (!returnState.isSuitableFor(ReturnState.IS_THROWN)) {
						insertThrow(cgValuedElement);
						cgValuedElement.setCaught(false);
						return ReturnState.IS_THROWN;
					}
				}
			}
			cgValuedElement.setCaught(returnState.isCaught());
		}
		return returnState;
	}

	/**
	 * By default the result IS_THROWN if any child IS_THROWN else IS_CAUGHT if any child IS_CAUGHT else IS_VALID.
	 */
	public @NonNull ReturnState visitAll(@NonNull Iterable<@NonNull ? extends CGElement> cgElements) {
		boolean aChildMayBeCaught = false;
		boolean aChildMayBeThrown = false;
		for (@NonNull CGElement cgElement : cgElements) {
			ReturnState returnState = visit(cgElement);
			if (!(cgElement instanceof CGValuedElement) || !((CGValuedElement)cgElement).isNonInvalid()) {
				switch (returnState) {
					case IS_CAUGHT: aChildMayBeCaught = true; break;
					case IS_THROWN: aChildMayBeThrown = true; break;
					default: break;
				}
			}
		}
		return aChildMayBeThrown ? ReturnState.IS_THROWN : aChildMayBeCaught ? ReturnState.IS_CAUGHT : ReturnState.IS_VALID;
	}

	@Override
	public @NonNull ReturnState visitCGConstantExp(@NonNull CGConstantExp object) {
		return object.isNonInvalid() ? ReturnState.IS_VALID : ReturnState.IS_CAUGHT;
	}

	// The mapping of exception to false is handled by OCLinEcoreCG2JavaVisitor.generateValidatorBody
	// once there is less CG2Java magic a mustBeCaught should enforced here.
/*	public @NonNull ReturnState visitCGConstraint(@NonNull CGConstraint cgConstraint) {
		return super.visitCGConstraint(object);
	}  */

	/**
	 * By default the result IS_THROWN if any child IS_THROWN else IS_CAUGHT if any child IS_CAUGHT else IS_VALID.
	 */
	@Override
	public @NonNull ReturnState visitCGElement(@NonNull CGElement cgElement) {
		ReturnState returnState = visitAll(cgElement.getChildren());
		if (cgElement instanceof CGValuedElement) {
			((CGValuedElement)cgElement).setCaught(returnState.isCaught());
		}
		return returnState;
	}

	/**
	 * All children of a validating operation must be caught.
	 */
	@Override
	public @NonNull ReturnState visitCGIsInvalidExp(@NonNull CGIsInvalidExp cgIsInvalidExp) {
		CGValuedElement cgSource = CGUtil.getSource(cgIsInvalidExp);
		context.mustBeCaught.visit(cgSource);
		cgIsInvalidExp.setCaught(false);
		return ReturnState.IS_VALID;
	}

	/**
	 * All children of a validating operation must be caught.
	 */
	@Override
	public @NonNull ReturnState visitCGIsUndefinedExp(@NonNull CGIsUndefinedExp cgIsUndefinedExp) {
		CGValuedElement cgSource = CGUtil.getSource(cgIsUndefinedExp);
		context.mustBeCaught.visit(cgSource);
		cgIsUndefinedExp.setCaught(false);
		return ReturnState.IS_VALID;
	}

	@Override
	public @NonNull ReturnState visitCGIterationCallExp(@NonNull CGIterationCallExp cgIterationCallExp) {
		Iteration asIteration = cgIterationCallExp.getAsIteration();
		context.mustBeThrown.visit(CGUtil.getSource(cgIterationCallExp));
		for (CGIterator cgIterator : CGUtil.getIterators(cgIterationCallExp)) {
			context.mustBeThrown.visit(cgIterator);
		}
		for (CGIterator cgCoIterator : CGUtil.getCoIterators(cgIterationCallExp)) {
			context.mustBeThrown.visit(cgCoIterator);
		}
		FieldingAnalysisVisitor bodyAnalysisVisitor = getNestedVisitor(CGUtil.getReferredIteration(cgIterationCallExp));
		ReturnState returnState = bodyAnalysisVisitor.visit(CGUtil.getBody(cgIterationCallExp));
		// Although individual body evaluations may be caught and accumulated, the accumularedc result is thrown.
		cgIterationCallExp.setCaught(false);
		return returnState == ReturnState.IS_VALID ? ReturnState.IS_VALID : ReturnState.IS_THROWN;
	}

	@Override
	public @NonNull ReturnState visitCGIterator(@NonNull CGIterator cgIterator) {
		assert cgIterator.getInit() == null;
		assert cgIterator.isNonInvalid();
		cgIterator.setCaught(false);
		return ReturnState.IS_VALID;
	}

	@Override
	public @NonNull ReturnState visitCGLetExp(@NonNull CGLetExp cgLetExp) {
		CGVariable cgVariable = CGUtil.getInit(cgLetExp);
		ReturnState initReturnState = context.mustBeCaught.visit(CGUtil.getInit(cgVariable));		// let will have to be caught anyway.
		cgVariable.setCaught(initReturnState.isCaught());
		ReturnState inReturnState = visit(CGUtil.getIn(cgLetExp));
		cgLetExp.setCaught(inReturnState.isCaught());
		return inReturnState;
	}

	@Override
	public @NonNull ReturnState visitCGNavigationCallExp(@NonNull CGNavigationCallExp object) {
		super.visitCGNavigationCallExp(object);
		return ReturnState.IS_THROWN;			// XXX non-derived attributes need not throw ???
	}

	@Override
	public @NonNull ReturnState visitCGOperation(@NonNull CGOperation cgOperation) {
		for (CGParameter cgParameter : CGUtil.getParameters(cgOperation)) {		// XXX use callingConvention
			visit(cgParameter);
		}
		CGValuedElement cgBody = cgOperation.getBody();
		if (cgBody == null) {
			return requiredReturn;
		}
		ReturnState returnState = visit(cgBody);
	//XXX	assert returnState.isSuitableFor(requiredReturn) || !cgBody.isNonInvalid();
		cgOperation.setCaught(returnState.isCaught());
		return returnState;
	}

	/**
	 * All children of a validating operation must be caught.
	 * The result of an invalidating operation is thrown.
	 */
	@Override
	public @NonNull ReturnState visitCGOperationCallExp(@NonNull CGOperationCallExp cgOperationCallExp) {
		CGOperation cgOperation = CGUtil.getOperation(cgOperationCallExp);
		Operation asOperation = CGUtil.getAST(cgOperation);
		FieldingAnalysisVisitor childVisitor = getNestedVisitor(cgOperation);// asOperation.isIsValidating() ? context.mustBeCaught : context.mustBeThrown;
		ReturnState requiredChildReturn = childVisitor.requiredReturn();
		for (CGValuedElement cgArgument : CGUtil.getArguments(cgOperationCallExp)) {
			ReturnState returnState = childVisitor.visit(cgArgument);
			assert returnState.isSuitableFor(requiredChildReturn) || cgArgument.isNonInvalid();
			// ?? required / guarded - rewriteAsThrown
		}
		ReturnState returnState;
		if (asOperation.isIsInvalidating()) {			// Explicitly may-be-invalid result
			returnState = ReturnState.IS_THROWN;
		}
		else if (asOperation.isIsValidating()) {		// Explicitly must be caught input
			returnState = requiredReturn;
		}
		else {											// Default could be accidentally bad Java
			returnState = ReturnState.IS_THROWN;
		}
		cgOperationCallExp.setCaught(returnState.isCaught());
		return returnState;								// ??? simplify like IterateExp
	}

	@Override
	public @NonNull ReturnState visitCGParameter(@NonNull CGParameter cgParameter) {
		assert cgParameter.getInit() == null;
		assert cgParameter.isNonInvalid();			// But the pass-as-Object then cast calling convention can give a CCE
		cgParameter.setCaught(false);
		return ReturnState.IS_THROWN;
	}

	@Override
	public @NonNull ReturnState visitCGSourcedCallExp(@NonNull CGSourcedCallExp cgSourcedCallExp) {
		ReturnState returnState = context.mustBeThrown.visitAll(cgSourcedCallExp.getChildren());
		cgSourcedCallExp.setCaught(returnState.isCaught());
		return returnState;
	}

	@Override
	public @NonNull ReturnState visitCGVariable(@NonNull CGVariable cgVariable) {
		ReturnState returnState = context.mayBeThrown.visit(CGUtil.getInit(cgVariable));
		cgVariable.setCaught(returnState.isCaught());
		return returnState;
	}

	@Override
	public @NonNull ReturnState visitCGVariableExp(@NonNull CGVariableExp cgVariableExp) {
		CGVariable cgVariable = CGUtil.getReferredVariable(cgVariableExp);
		if (!cgVariable.isNonInvalid()) {			// If the CGVariable could be invalid
			if (!cgVariable.isCaught()) {
				CGValuedElement cgInit = cgVariable.getInit();
				if (cgInit != null) {
					insertCatch(cgInit);
					cgVariable.setCaught(true);
				}
				else {
					assert false;
				}
			}
			if (requiredReturn == ReturnState.IS_THROWN) {
				insertThrow(cgVariableExp);
			}
		}
		cgVariableExp.setCaught(cgVariable.isCaught());
		return requiredReturn;
	}
}