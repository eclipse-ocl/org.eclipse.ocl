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

import java.util.HashSet;
import java.util.Set;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.examples.codegen.cgmodel.CGCatchExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGModelFactory;
import org.eclipse.ocl.examples.codegen.cgmodel.CGPackage;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.cgmodel.CGVariable;

/**
 * A FieldingAnalyzer identifies the necessary catches and throws to accommodate the alternative mechanisms
 * for 'returning' an invalid value, for which there are three possibilities and two representations.
 * <h4>Thrown Representation</h4>
 * Using the Java exception mechanism enables the invalid to pass through many intermediate AST nodes
 * without individual handling at each node. This form of 'return' is preferred but does not support passing
 * an invalid value into a validating AST node that needs to 'see' the invalid. e.g. oclIsInvalid() or
 * OCL 2.x and/or/implies.
 * <h4>Caught Representation</h4>
 * Passing an OCL invalid value directly emulates the OCL semantics and enables a receiving AST node to process
 * the invalid value.
 * <h4>Valid</h4>
 * A 'return' that cannot be invalid is always valid and needs no conversion to caught or thrown representations since there
 * is no invalid to 'return'.
 * <h3>Invalid Value</h3>
 * Whether caught or thrown the 'invalid' OCL return is an instance of InvalidValueException making conversion
 * between the two representations straightforward. Really bad execution may result in arbitrary Java exceptions
 * that may be left to propagate when we are confident that they are not needed yet. Eventually these raw Java
 * exceptions are wrapped in an InvalidValueException.
 * <p>
 * <h2>Caught or Thrown Consumer Requirements</h2>
 * <p>
 * Most AST nodes just propagate invalid and so use of Java exception is convenient and efficient.
 * <p>
 * A thrown value must be converted to caught when a recipient requires the caught representation.
 * <h4>Final result</h4>
 * Outer environments such as EMF's eGet() do not expect Java exceptions to occur, but if they do then there is a
 * problem for which an exception is consistent. Conversely EMF's validate() used to fail globally if a single
 * constraint crashes. Here OCL must ensure that all exceptions are caught and converted to constraint failures.
 * The fragility of EMF's validate has improved recently.
 * <br>
 * Outer environments such as a QVT mapping may pursue the policy that any failure is catastrophic and so require
 * that all invalid values are thrown out as exceptions.
 * <h4>Validating operations</h4>
 * Operations that may convert an invalid input into a not-invalid output, such as oclIsInvalid() or and()/or()/implies()
 * require that invalid inputs use the caught representation.
 * <h4>Invalidating operations</h4>
 * Operations that may return an invalid output even though all inputs are valid, such as at() or and()/or()/implies()
 * throw their invalid result.
 * <h4>Null values</h4>
 * Whether a null value is acceptable depends on the recipent. Property calls treat a null source as an invalid value.
 * Operation calls that lack an OclVoid overload treat a null source as an invalid value.
 * Operation calls with required parameters treat corresponding null arguments as invalid.
 * <h4>Let variables</h4>
 * The result of one computation my be passed to further computations by caching the result in the let variable.
 * The cached value must satisfy the requirements of each recipient, thus if any recipient is a validating operation
 * the let variable must use the caught representation, since a thrown value would bypass the caching.
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

		boolean isCaught() {
			return this == IS_CAUGHT;
		}

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

	protected final @NonNull CodeGenAnalyzer analyzer;

	/**
	 * The isValidating ensures that variable accesses notify their source let-variables that they need to catch invalid.
	 */
	private final @NonNull FieldingValidationVisitor isValidating;

	/**
	 * The notValidating allows source let-variables to be uncaught.
	 */
	private final @NonNull FieldingValidationVisitor notValidating;

	/**
	 * The MayBeThrown enforces the container's requirement that an invalid received value
	 * may be thrown but may also be caught, so no wrapping is required.
	 */
	private final @NonNull FieldingAnalysisVisitor mayBeThrown;

	/**
	 * The MustBeCaught visitor enforces the container's requirement that an invalid received value
	 * must be caught by a CGCatchExp. CGVariableExp are redirected to a cached CGCatchExp. Other
	 * terms are wrapped in a CGCatchExp.
	 */
	private final @NonNull FieldingAnalysisVisitor mustBeCaught;

	/**
	 * The MustBeThrown enforces the container's requirement that an invalid received value
	 * may be thrown by a CGThrowExp.
	 */
	private final @NonNull FieldingAnalysisVisitor mustBeThrown;

	/**
	 * All variables that are transitively consumed by an isValidating operation. This includes
	 * paramters and other variables that cannot actually be invalid.
	 */
	private final @NonNull Set<@NonNull CGVariable> validatedVariables = new HashSet<>();

	public FieldingAnalyzer(@NonNull CodeGenAnalyzer analyzer) {
		this.analyzer = analyzer;
		this.isValidating = createValidatedVisitor(true);
		this.notValidating = createValidatedVisitor(false);
		this.mayBeThrown = createAnalysisVisitor(ReturnState.MAYBE_THROWN);
		this.mustBeCaught = createAnalysisVisitor(ReturnState.IS_CAUGHT);
		this.mustBeThrown = createAnalysisVisitor(ReturnState.IS_THROWN);
	}

	public void addValidated(@NonNull CGVariable cgVariable) {
		validatedVariables.add(cgVariable);
	}

	public void analyze(@NonNull Iterable<@NonNull CGPackage> cgPackages) {
		for (@NonNull CGPackage cgPackage : cgPackages) {
			notValidating.visit(cgPackage);
		}
		for (@NonNull CGPackage cgPackage : cgPackages) {
			mustBeThrown.visit(cgPackage);
		}
	}

	protected @NonNull FieldingAnalysisVisitor createAnalysisVisitor(@NonNull ReturnState returnState) {
		return new FieldingAnalysisVisitor(this, returnState);
	}

	protected @NonNull CGCatchExp createCGCatchExp(@NonNull CGValuedElement cgValuedElement) {
		CGCatchExp cgCatchExp = CGModelFactory.eINSTANCE.createCGCatchExp();
		cgCatchExp.setSource(cgValuedElement);
		cgCatchExp.setAst(cgValuedElement.getAst());
		cgCatchExp.setTypeId(cgValuedElement.getTypeId());
		cgCatchExp.setCaught(true);
		return cgCatchExp;
	}

	protected @NonNull FieldingValidationVisitor createValidatedVisitor(boolean isValidating) {
		return new FieldingValidationVisitor(this, isValidating);
	}

	public @NonNull CodeGenAnalyzer getAnalyzer() {
		return analyzer;
	}

/*	public @NonNull CGVariable getCaughtVariable(@NonNull CGVariable cgVariable) {
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
	} */

	public @NonNull FieldingValidationVisitor getIsValidatingVisitor() {
		return isValidating;
	}

	public @NonNull FieldingAnalysisVisitor getMayBeThrownVisitor() {
		return mayBeThrown;
	}

	public @NonNull FieldingAnalysisVisitor getMustBeCaughtVisitor() {
		return mustBeCaught;
	}

	public @NonNull FieldingAnalysisVisitor getMustBeThrownVisitor() {
		return mustBeThrown;
	}

	public @NonNull FieldingValidationVisitor getNotValidatingVisitor() {
		return notValidating;
	}

	public boolean isValidated(@NonNull CGVariable cgVariable) {
		return validatedVariables.contains(cgVariable);
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
