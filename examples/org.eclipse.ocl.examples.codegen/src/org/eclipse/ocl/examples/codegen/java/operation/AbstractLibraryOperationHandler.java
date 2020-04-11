/*******************************************************************************
 * Copyright (c) 2020 Willink Transformations Ltd and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.codegen.java.operation;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.examples.codegen.cgmodel.CGLibraryOperationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.java.JavaStream;
import org.eclipse.ocl.pivot.values.InvalidValueException;

/**
 * AbstractLibraryOperationHandler provides the mandatory implementation of the LibraryOperationHandler
 * API for a custom code generator for a library operation. It also provides many helper methods for generating
 * code for 2-valued / 4-valued Boolean sub-expressions.
 */
public abstract class AbstractLibraryOperationHandler implements LibraryOperationHandler
{
	protected final @NonNull JavaStream js;

	protected AbstractLibraryOperationHandler(@NonNull JavaStream js) {
		this.js = js;
	}

	protected void appendAssignBooleanLiteral(boolean hasDeclaration, @NonNull CGLibraryOperationCallExp cgOperationCallExp, boolean value) {
		js.appendAssignBooleanLiteral(hasDeclaration, cgOperationCallExp, value);
	}

/*	protected void appendAssignNotValue(boolean hasDeclaration, @NonNull CGLibraryOperationCallExp cgOperationCallExp, @NonNull CGValuedElement value) {
		if (!cgOperationCallExp.isNonInvalid()) {
			appendThrowIfMayBeInvalid(value);
		}
		if (!hasDeclaration) {
			js.appendDeclaration(cgOperationCallExp);
		}
		else {
			js.appendValueName(cgOperationCallExp);
		}
		js.append(" = ");
		if (value.isNull()) {
			js.append("null");
		}
		else if (value.isNonNull() && value.isNonInvalid()) {
			js.append("!");
			js.appendValueName(value);
		}
		else {
			js.appendValueName(value);
			js.append(" instanceof ");
			js.appendClassReference(null, Boolean.class);
			js.append(" ? !(");
			js.appendClassReference(null, Boolean.class);
			js.append(")");
			js.appendValueName(value);
			js.append(" : null ");			// true/false cast to Boolean, invalid already thrown
//			js.appendValueName(value);
		}
		js.append(";\n");
	} */

	protected void appendAssignNullLiteral(boolean hasDeclaration, @NonNull CGLibraryOperationCallExp cgOperationCallExp) {
		assert !cgOperationCallExp.isNonNull();
		if (!hasDeclaration) {
			js.appendDeclaration(cgOperationCallExp);
		}
		else {
			js.appendValueName(cgOperationCallExp);
		}
		js.append(" = null;\n");
	}

	protected void appendAssignValue(boolean hasDeclaration, @NonNull CGLibraryOperationCallExp cgOperationCallExp, @NonNull CGValuedElement value) {
		if (!cgOperationCallExp.isNonInvalid()) {
			appendThrowIfMayBeInvalid(value);
		}
		if (!hasDeclaration) {
			js.appendDeclaration(cgOperationCallExp);
		}
		else {
			js.appendValueName(cgOperationCallExp);
		}
		js.append(" = ");
		if (value.isNull()) {
			js.append("null");
		}
		else if (value.isNonNull() && value.isNonInvalid()) {
			js.appendValueName(value);
		}
		else {
			js.append("(");
			js.appendClassReference(null, Boolean.class);
			js.append(")");
			js.appendValueName(value);
		}
		js.append(";\n");
	}

	protected void appendAssignXor(boolean hasDeclaration, @NonNull CGLibraryOperationCallExp cgOperationCallExp,
			@NonNull CGValuedElement cgSource, @NonNull CGValuedElement cgArgument) {
		if (!hasDeclaration) {
			js.appendDeclaration(cgOperationCallExp);
		}
		else {
			js.appendValueName(cgOperationCallExp);
		}
		js.append(" = ");
		if (cgSource.isFalse() && cgArgument.isFalse()) {
			js.appendBooleanString(false);
		}
		else if (cgSource.isFalse() && cgArgument.isTrue()) {
			js.appendBooleanString(true);
		}
		else if (cgSource.isTrue() && cgArgument.isFalse()) {
			js.appendBooleanString(true);
		}
		else if (cgSource.isTrue() && cgArgument.isTrue()) {
			js.appendBooleanString(false);
		}
		else {
			js.appendValueName(cgSource);
			js.append(" != ");
			js.appendValueName(cgArgument);
		}
		js.append(";\n");
	}

	protected boolean appendDeclaration(boolean hasDeclaration, @NonNull CGLibraryOperationCallExp cgOperationCallExp) {
		if (!hasDeclaration) {
			js.appendDeclaration(cgOperationCallExp);
			js.append(";\n");
			hasDeclaration = true;
		}
		return hasDeclaration;
	}

	protected void appendElse() {
		js.popIndentation();
		js.append("}\n");
		js.append("else {\n");
		js.pushIndentation(null);
	}

	protected void appendEndIf() {
		js.popIndentation();
		js.append("}\n");
	}

	private void appendEqualsBoolean(@NonNull CGValuedElement cgValue, boolean value) {
		js.appendEqualsBoolean(cgValue, value);
	}

	/**
	 * Append an if cgSource -- value prolog.
	 *
	 * Append the start of an if test that cgValue matches refValue.
	 *
	 * Asserts that the if test is necessary.
	 */
	protected void appendIfEqualsBoolean0(@NonNull CGValuedElement cgValue, boolean refValue) {
		assert !cgValue.isConstant();
		assert canMatch(cgValue, refValue);
		js.append("if (");
		appendEqualsBoolean(cgValue, refValue);
		js.append(") {\n");
		js.pushIndentation(null);
	}

	/**
	 * Return true if the start of an if test that cgValue matches refValue has been generated.
	 * Return false if no test is needed since neither can match.
	 */
	protected boolean appendIfEqualsBoolean1(@NonNull CGValuedElement cgValue, boolean refValue) {
		boolean canMatch = canMatch(cgValue, refValue);
		if (!canMatch) {
			return false;
		}
		js.append("if (");
			appendEqualsBoolean(cgValue, refValue);
		js.append(") {\n");
		js.pushIndentation(null);
		return true;
	}

	/**
	 * Append an if cgValue == null prolog.
	 */
	protected void appendIfEqualsNull(@NonNull CGValuedElement cgValue) {
		assert !cgValue.isConstant();
		js.append("if (");
		js.appendValueName(cgValue);
		js.append(" == null) {\n");
		js.pushIndentation(null);
	}
	/**
	 * Append an if cgValue1 == null or cgValue2 == null prolog.
	 */
	protected void appendIfEqualsNull(@NonNull CGValuedElement cgValue1, @NonNull CGValuedElement cgValue2) {
		assert !cgValue1.isConstant();
		assert !cgValue2.isConstant();
		js.append("if ((");
		js.appendValueName(cgValue1);
		js.append(" == null) || (");
		js.appendValueName(cgValue2);
		js.append(" == null)) {\n");
		js.pushIndentation(null);
	}

	/**
	 * Append a mandatory throw if cgElement is unconditionally invalid.
	 *
	 * Returns true if throw appended.
	 */
	protected boolean appendThrowIfInvalid(final CGValuedElement cgElement, @NonNull String context) {
		if (cgElement.isInvalid()) {
			js.append("throw new ");
			js.appendClassReference(null, InvalidValueException.class);
			js.append("(\"Null " + context + "\");\n");
			return true;
		}
		return false;
	}

	/**
	 * Append a conditionsal throw if cgElement may be invalid.
	 */
	protected void appendThrowIfMayBeInvalid(final CGValuedElement cgElement) {
		if (!cgElement.isNonInvalid()) {
			js.append("if (");
			js.appendValueName(cgElement);
			js.append(" instanceof ");
			js.appendClassReference(null, InvalidValueException.class);
			js.append(") {\n");
			js.pushIndentation(null);
			js.append("throw (");
			js.appendClassReference(null, InvalidValueException.class);
			js.append(")");
			js.appendValueName(cgElement);
			js.append(";\n");
			js.popIndentation();
			js.append("}\n");
		}
	}

	/**
	 * Append a conditionsal throw if cgElement may be null.
	 */
	protected void appendThrowIfMayBeNull(final CGValuedElement cgElement, @NonNull String context) {
		if (!cgElement.isNonNull()) {
			js.append("if (");
			js.appendValueName(cgElement);
			js.append(" == null) {\n");
			js.pushIndentation(null);
			js.append("throw new ");
			js.appendClassReference(null, InvalidValueException.class);
			js.append("(\"Null " + context + "\");\n");
			js.popIndentation();
			js.append("}\n");
		}
	}

	/**
	 * Append a mandatory throw if cgElement is unconditionally null.
	 *
	 * Returns true of throw appended.
	 */
	protected boolean appendThrowIfNull(final CGValuedElement cgElement, @NonNull String context) {
		if (cgElement.isNull()) {
			js.append("throw new ");
			js.appendClassReference(null, InvalidValueException.class);
			js.append("(\"Null " + context + "\");\n");
			return true;
		}
		return false;
	}

	private boolean canMatch(@NonNull CGValuedElement cgValue, boolean value) {
		if (value) assert !cgValue.isTrue(); else assert !cgValue.isFalse();
		if (cgValue.isNull() || cgValue.isInvalid()) {
			return false;
		}
		else if (value) {
			return cgValue.isFalse() ? false : true;
		}
		else {
			return cgValue.isTrue() ? false : true;
		}
	}

	@Override
	public @NonNull String toString() {
		return js.toString();
	}
}