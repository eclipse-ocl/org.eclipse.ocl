/*******************************************************************************
 * Copyright (c) 2013, 2018 CEA LIST and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink(CEA LIST) - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.codegen.analyzer;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.examples.codegen.cgmodel.CGCatchExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGConstantExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGElement;
import org.eclipse.ocl.examples.codegen.cgmodel.CGFinalVariable;
import org.eclipse.ocl.examples.codegen.cgmodel.CGIsInvalidExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGIsUndefinedExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGIterationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGIterator;
import org.eclipse.ocl.examples.codegen.cgmodel.CGLetExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGModelFactory;
import org.eclipse.ocl.examples.codegen.cgmodel.CGModelPackage;
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
import org.eclipse.ocl.pivot.internal.utilities.PivotUtilInternal;

/**
 * A FieldingAnalyzer identifies the necessary catches and throws to accommodate the alternative mechanisms
 * for 'returning' an invalid value. For which there are three possibilities and two representations.
 * <h4>Thrown Representation</h4>
 * Using the Java exception mechanism enables the invalid to pass through many intermediate AST nodes
 * without individual handling at each node. This form of 'return' is preferred but does not support passing
 * an invalid value into a validating AST node that needs to 'see' the invalid. e.g. oclIsInvalid() or
 * OCL 2.x and/or/implies.
 * <h4>Caught Representation</h4>
 * Passing an OCL invalid value directly emulates the OCL semantics and enables a receiving AST node to process
 * the invalid value.
 * <h4>Valid</h4>
 * A 'return' that cannot be invalid is always valid and needs no conversion to caught or thrown since there
 * is no invalid to 'return'.
 * <h3>Invalid Value</h3>
 * Whether caught or thrown the 'invalid' OCL return is an instanceof InvalidValueException making conversion
 * between the two representations straightforward. Really bad execution may result in arbitrary Java exceptions
 * that are may be left to propgate when we are confidemnt that they are not needed yet. Eventually these raw Java
 * exceptions are wrapped in an InvalidValueException.
 * <p>
 * <h2>Caught or Thrown Consumer Requirements</h2>
 * <p>
 * Most AST nodes just propagate invalid and so use of Java exception is convenient and efficient.
 * <p>
 * A thrown value must be converted to caught when a recipient requires the caught representation.
 * <h4>Final result</h4>
 * Outer environments such as EMF's eGet() do not expect Java exceptions to occur, but if they do then there is a
 * problem for which an exception is consistent. Conversely EMF's validate() used to fail globally is a single
 * constraint crashes. Here OCL must ensure that all exceptions are caught and reported as constraint failures.
 * The fragility of EMF's validate
 * has improved recently.
 * <br>
 * Outer environments such as a QVT mapping may pursue the policy that any failure is catastrophic and so require
 * that all invalid values are thrown out as exceptions.
 * <h4>Validating operations</h4>
 * Operations that may convert an invalid input into a not-invalid output, such as oclIsInvalid() or and()/or()/implies()
 * require that invalid inputs use the caught representation.
 * <h4>Null values</h4>
 * Whether a null value is acceptable depends on the recipent. Property calls treat a null source as an invalid value.
 * Operation calls that lack an OclVoid overload treat a null source as an invalid value.
 * Operation calls with required parameters treat corresponding null arguments as invalid.
 * <h4>Let variables</h4>
 * The result of one computation my be passed to further computations by caching the result in the let variable.
 * The cached value must satisfy the requirements of each recipient, thus if any recipient is a validating operation
 * the let variable must use the caught representation, since a thrown value would bypass the caching.
 * <h3>Optimization</h3>
 * The init input of a let variable does not need to be caught if an invalid value is guaranteed to make the in input
 * invalid as well; a crash on the in input can be propagated without further ado.
 * <p>
 * <h2>Algorithm</h2>
 * <p>
 * <h3>Descent</h3>
 * The descent notifies each child of the parent's expectation by visting with the mustBeCaught / canBeInvalid visitor.
 * <br>
 * A mustBeCaught visit of a mayBeInvalid CGVariable wraps the CGVariable in a CGCatchExp at source.
 * The caught CGVariable is cached for re-use by multiple expectations.
 * <h3>Ascent</h3>
 * The ascent returns true if the execution value isCaught / notInvalid or false if mayBeThrown.
 * <br>
 * A mayBeThrown return for a mustBeCaught value is wrapped in a CGCatchExp.
 * <br>
 * A mustBeThrown return for an invalid isCaught value is wrapped in a CGThrowExp.
 */
public class FieldingAnalyzer
{
	public enum ReturnState {
		IS_CAUGHT,			// Any invalid value is returned by value as an InvalidValueException
		IS_THROWN,			// Any invalid value is returned by throwing an InvalidValueException
		IS_VALID,			// There is no invalid value to return
		MAYBE_THROWN;		// Any invalid value may be returned/thrown as convenient as an InvalidValueException

		/**
		 * Return true if a value of this ReturnState can be returned to requiredState without throwing/catching.
		 */
		boolean isSuitableFor(@NonNull ReturnState requiredState) {
			switch (this) {
				case IS_CAUGHT: return (requiredState == IS_CAUGHT) || (requiredState == MAYBE_THROWN);
				case IS_THROWN: return (requiredState == IS_THROWN) || (requiredState == MAYBE_THROWN);
				case IS_VALID: return true;
				case MAYBE_THROWN: return  (requiredState == IS_CAUGHT) || (requiredState == IS_VALID) || (requiredState == MAYBE_THROWN);
			}
			return false;
		}
	};

	/*
	 * An earlier version of this analysis determined variables accessed outside the prevailing containment tree
	 * and assumed that only dodgy external accesses could cause invalid; dodgy internal accesses are handled internally.
	 *
	 * However invalid can arise despite all external variables being valid; divide-by-zero / index-out-of-bounds etc.
	 */
	/**
	 * Perform the tree descent/ascent returning IS_CAUGHT / IS_THROWN / IS_VALID according to the mechanism by
	 * which the result is passed and the potential invalidity of the result value.
	 */
	public static class AnalysisVisitor extends AbstractExtendingCGModelVisitor<@NonNull ReturnState, @NonNull FieldingAnalyzer>
	{
		/**
		 * The required return state. Polymorphism is not used for the alternatives to allow derived AnalysisVisitor
		 * implementations to overload just once rather than three times.
		 */
		protected @NonNull ReturnState requiredReturn;

		public AnalysisVisitor(@NonNull FieldingAnalyzer context, @NonNull ReturnState requiredReturn) {
			super(context);
			this.requiredReturn = requiredReturn;
		}

		protected @NonNull ReturnState requiredReturn() {
			return requiredReturn;
		}

		@Override
		public @NonNull ReturnState visiting(@NonNull CGElement visitable) {
			throw new UnsupportedOperationException(getClass().getSimpleName() + ": " + visitable.getClass().getSimpleName());
		}

		@Override
		public @NonNull ReturnState visit(@NonNull CGElement cgElement) {
			ReturnState returnState = cgElement.accept(this);
			if (requiredReturn == ReturnState.IS_CAUGHT) {
				if (!returnState.isSuitableFor(ReturnState.IS_CAUGHT) && (cgElement instanceof CGValuedElement)) {
					CGValuedElement cgValuedElement = (CGValuedElement)cgElement;
					if (!cgValuedElement.isNonInvalid()) {
						context.insertCatch(cgValuedElement);
						cgValuedElement.setCaught(false);
					}
				}
				return ReturnState.IS_CAUGHT;
			}
			else if (requiredReturn == ReturnState.IS_THROWN) {
				if (!returnState.isSuitableFor(ReturnState.IS_THROWN) && (cgElement instanceof CGValuedElement)) {
					CGValuedElement cgValuedElement = (CGValuedElement)cgElement;
					if (!cgValuedElement.isNonInvalid()) {
						context.insertThrow(cgValuedElement);
						cgValuedElement.setCaught(false);
					}
				}
				return ReturnState.IS_THROWN;
			}
			else {
				return returnState;
			}
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

		/**
		 * By default the result IS_THROWN if any child IS_THROWN else IS_CAUGHT if any child IS_CAUGHT else IS_VALID.
		 */
		@Override
		public @NonNull ReturnState visitCGElement(@NonNull CGElement cgElement) {
			return visitAll(cgElement.getChildren());
		}

		/**
		 * All children of a validating operation must be caught.
		 */
		@Override
		public @NonNull ReturnState visitCGIsInvalidExp(@NonNull CGIsInvalidExp cgIsInvalidExp) {
			CGValuedElement cgSource = CGUtil.getSource(cgIsInvalidExp);
			context.mustBeCaught.visit(cgSource);
			return ReturnState.IS_VALID;
		}

		/**
		 * All children of a validating operation must be caught.
		 */
		@Override
		public @NonNull ReturnState visitCGIsUndefinedExp(@NonNull CGIsUndefinedExp cgIsUndefinedExp) {
			CGValuedElement cgSource = CGUtil.getSource(cgIsUndefinedExp);
			context.mustBeCaught.visit(cgSource);
			return ReturnState.IS_VALID;
		}

		@Override
		public @NonNull ReturnState visitCGIterationCallExp(@NonNull CGIterationCallExp cgElement) {
			Iteration asIteration = cgElement.getReferredIteration();
			AnalysisVisitor mayBeThrown = context.mayBeThrown;
			AnalysisVisitor mustBeCaught = context.mustBeCaught;
			AnalysisVisitor mustBeThrown = context.mustBeThrown;
			mustBeThrown.visit(CGUtil.getSource(cgElement));
			for (CGIterator cgIterator : CGUtil.getIterators(cgElement)) {
				mustBeThrown.visit(cgIterator);
			}
			for (CGIterator cgCoIterator : CGUtil.getCoIterators(cgElement)) {
				mustBeThrown.visit(cgCoIterator);
			}
			AnalysisVisitor bodyAnalysisVisitor = asIteration.isIsValidating() ? mustBeCaught : mayBeThrown;
			bodyAnalysisVisitor.visit(CGUtil.getBody(cgElement));
		/*	if (asIteration.isIsInvalidating()) {			// Explicitly may-be-invalid result
				return IS_THROWN;
			}
			else */ if (asIteration.isIsValidating()) {		// Explicitly must be caught input
				return requiredReturn();
			}
			else {											// Default could be accidentally bad Java
				return ReturnState.IS_THROWN;
			}
		}

		@Override
		public @NonNull ReturnState visitCGLetExp(@NonNull CGLetExp cgLetExp) {
			context.mayBeThrown.visit(CGUtil.getInit(cgLetExp));		// let variable does not have to be caught yet.
			return visit(CGUtil.getIn(cgLetExp));
		}

		@Override
		public @NonNull ReturnState visitCGOperation(@NonNull CGOperation cgOperation) {
			for (CGParameter cgParameter : CGUtil.getParameters(cgOperation)) {		// XXX use callingConvention
				visit(cgParameter);
			}
			CGValuedElement cgBody = cgOperation.getBody();
			if (cgBody != null) {
				ReturnState returnState = visit(cgBody);
				ReturnState requiredReturn = requiredReturn();
				assert returnState.isSuitableFor(requiredReturn);
			}
			return requiredReturn();
		}

		/**
		 * All children of a validating operation must be caught.
		 * The result of an invalidating operation is thrown.
		 */
		@Override
		public @NonNull ReturnState visitCGOperationCallExp(@NonNull CGOperationCallExp cgOperationCallExp) {
			CGOperation cgOperation = CGUtil.getOperation(cgOperationCallExp);
			Operation asOperation = CGUtil.getAST(cgOperation);
			AnalysisVisitor childVisitor = asOperation.isIsValidating() ? context.mustBeCaught : context.mustBeThrown;
			ReturnState requiredChildReturn = childVisitor.requiredReturn();
			for (CGValuedElement cgArgument : CGUtil.getArguments(cgOperationCallExp)) {
				ReturnState returnState = childVisitor.visit(cgArgument);
				assert returnState.isSuitableFor(requiredChildReturn) || cgArgument.isNonInvalid();
				// ?? required / guarded - rewriteAsThrown
			}
			if (asOperation.isIsInvalidating()) {			// Explicitly may-be-invalid result
				return ReturnState.IS_THROWN;
			}
			else if (asOperation.isIsValidating()) {		// Explicitly must be caught input
				return requiredReturn();
			}
			else {											// Default could be accidentally bad Java
				return ReturnState.IS_THROWN;
			}
		}

		@Override
		public @NonNull ReturnState visitCGParameter(@NonNull CGParameter cgParameter) {
			assert cgParameter.getInit() == null;
			assert cgParameter.isNonInvalid();
			return requiredReturn();
		}

		@Override
		public @NonNull ReturnState visitCGSourcedCallExp(@NonNull CGSourcedCallExp cgSourcedCallExp) {
			return context.mustBeThrown.visitAll(cgSourcedCallExp.getChildren());
		}

		@Override
		public @NonNull ReturnState visitCGVariable(@NonNull CGVariable cgVariable) {
			return context.mayBeThrown.visit(CGUtil.getInit(cgVariable));
		}

		@Override
		public @NonNull ReturnState visitCGVariableExp(@NonNull CGVariableExp cgVariableExp) {
			if (requiredReturn == ReturnState.IS_CAUGHT) {
				CGVariable cgVariable = CGUtil.getReferredVariable(cgVariableExp);
				if (!cgVariable.isCaught() && !cgVariable.isNonInvalid()) {
					CGVariable cgCaughtVariable = context.getCaughtVariable(cgVariable);
					cgVariableExp.setReferredVariable(cgCaughtVariable);
				}
				cgVariableExp.setCaught(true);
				return ReturnState.IS_CAUGHT;
			}
			else {
				return super.visitCGVariableExp(cgVariableExp);
			}
		}
	}

	protected final @NonNull CodeGenAnalyzer analyzer;

	/**
	 * The MayBeThrown enforces the container's requirement that an invalid received value
	 * may be thrown but may also be caught, so no wrapping is required.
	 */
	protected final @NonNull AnalysisVisitor mayBeThrown = createAnalysisVisitor(ReturnState.MAYBE_THROWN);

	/**
	 * The MustBeCaught visitor enforces the container's requirement that an invalid received value
	 * must be caught by a CGCatchExp. CGVariableExp are redirected to a cached CGCatchExp. Other
	 * terms are wrapped in a CGCatchExp.
	 */
	protected final @NonNull AnalysisVisitor mustBeCaught = createAnalysisVisitor(ReturnState.IS_CAUGHT);

	/**
	 * The MustBeThrown enforces the container's requirement that an invalid received value
	 * may be thrown by a CGThrowExp.
	 */
	protected final @NonNull AnalysisVisitor mustBeThrown = createAnalysisVisitor(ReturnState.IS_THROWN);

	/**
	 * The CGVariable with a CGCatchExp iitializer for each possibly invalid CGVariable that is accessed in a mustBeCaught fashion.
	 */
	private final @NonNull Map<@NonNull CGVariable, @NonNull CGFinalVariable> variable2caughtVariable = new HashMap<>();

	public FieldingAnalyzer(@NonNull CodeGenAnalyzer analyzer) {
		this.analyzer = analyzer;
	}

	public void analyze(@NonNull CGElement cgTree, boolean mustBeCaught) {
		mayBeThrown.visit(cgTree);
	}

	protected @NonNull AnalysisVisitor createAnalysisVisitor(@NonNull ReturnState returnState) {
		return new AnalysisVisitor(this, returnState);
	}

	protected @NonNull CGCatchExp createCGCatchExp(@NonNull CGValuedElement cgValuedElement) {
		CGCatchExp cgCatchExp = CGModelFactory.eINSTANCE.createCGCatchExp();
		cgCatchExp.setSource(cgValuedElement);
		cgCatchExp.setAst(cgValuedElement.getAst());
		cgCatchExp.setTypeId(cgValuedElement.getTypeId());
		cgCatchExp.setCaught(true);
		return cgCatchExp;
	}

	public @NonNull CGVariable getCaughtVariable(@NonNull CGVariable cgVariable) {
		CGFinalVariable cgCaughtVariable = variable2caughtVariable.get(cgVariable);
		if (cgCaughtVariable == null) {
			if (cgVariable.eContainingFeature() == CGModelPackage.Literals.CG_LET_EXP__INIT) {
				NestedNameManager nameManager = analyzer.getGlobalNameManager().findNestedNameManager(cgVariable);
				CGVariableExp cgVariableExp = analyzer.createCGVariableExp(cgVariable);
				CGLetExp cgLetExp = (CGLetExp)cgVariable.eContainer();
				assert cgLetExp != null;
				CGCatchExp cgCatchExp = createCGCatchExp(cgVariableExp);
				cgCaughtVariable = nameManager.createCGVariable(cgCatchExp);
				CGValuedElement cgIn = CGUtil.getIn(cgLetExp);
				PivotUtilInternal.resetContainer(cgIn);
				CGLetExp cgCaughtLetExp = analyzer.createCGLetExp(cgCaughtVariable, cgIn);
				cgLetExp.setIn(cgCaughtLetExp);
			}
			else {
				// ?? sibling if a multi-child
				throw new UnsupportedOperationException();
			}
			variable2caughtVariable.put(cgVariable, cgCaughtVariable);
		}
		return cgCaughtVariable;
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
		if (!cgChild.isNonInvalid()) {
			CGThrowExp cgThrowExp = CGModelFactory.eINSTANCE.createCGThrowExp();
			CGUtil.wrap(cgThrowExp, cgChild);
		}
	}

/*	protected boolean isValidating(EObject eObject) {
		if (eObject instanceof OperationCallExp) {
			OperationCallExp operationCall = (OperationCallExp)eObject;
			Operation operation = operationCall.getReferredOperation();
			if (operation.isIsValidating()) {
				return true;
			}
		}
		return false;
	} */
}
