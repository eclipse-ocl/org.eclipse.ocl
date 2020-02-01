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
import org.eclipse.ocl.pivot.library.LibraryOperation;
import org.eclipse.ocl.pivot.library.logical.BooleanXorOperation;

/**
 * XorOperationHandler generates inline code for the xor Boolean operation.
 */
public class XorOperationHandler extends AbstractLibraryOperationHandler
	{
		public XorOperationHandler( @NonNull JavaStream js) {
			super(js);
		}

		@Override
		public @NonNull Boolean generate(@NonNull CGLibraryOperationCallExp cgOperationCallExp) {
			assert !cgOperationCallExp.getReferredOperation().isIsValidating();
			boolean hasDeclaration = false;
			//
			//	Trivial source cases
			//
			final CGValuedElement cgSource = cgOperationCallExp.getSource();
			if (appendThrowIfInvalid(cgSource, "xor source")) {
				return false;
			}
			//
			//	Trivial argument cases
			//
			CGValuedElement cgArgument = cgOperationCallExp.getArguments().get(0);
			assert cgArgument != null;
			if (appendThrowIfInvalid(cgArgument, "xor argument")) {
				return false;
			}
			//
			//	Trivial source+argument cases
			//
			if (cgSource.isFalse() && cgArgument.isFalse()) {
				appendAssignBooleanLiteral(hasDeclaration, cgOperationCallExp, false);
				return true;
			}
			if (cgSource.isFalse() && cgArgument.isTrue()) {
				appendAssignBooleanLiteral(hasDeclaration, cgOperationCallExp, true);
				return true;
			}
			if (cgSource.isTrue() && cgArgument.isFalse()) {
				appendAssignBooleanLiteral(hasDeclaration, cgOperationCallExp, true);
				return true;
			}
			if (cgSource.isTrue() && cgArgument.isTrue()) {
				appendAssignBooleanLiteral(hasDeclaration, cgOperationCallExp, false);
				return true;
			}
			//
			//	Real case
			//
			if (!js.appendLocalStatements(cgSource)) {
				return false;
			}
		//	appendThrowIfMayBeInvalid(cgSource);	-- xor is not validating so an InvalidValueException propagates
			if (!js.appendLocalStatements(cgArgument)) {
				return false;
			}
		//	appendThrowIfMayBeInvalid(cgArgument);	-- xor is not validating so an InvalidValueException propagates
			if (cgSource.isNull() || cgArgument.isNull()) {
				appendAssignNullLiteral(hasDeclaration, cgOperationCallExp);
				return true;
			}

			if (cgSource.isNonNull()) {
				if (cgArgument.isNonNull()) {
					appendAssignXor(hasDeclaration, cgOperationCallExp, cgSource, cgArgument);
				}
				else {
					hasDeclaration = appendDeclaration(hasDeclaration, cgOperationCallExp);
					appendIfEqualsNull(cgArgument);
					appendAssignNullLiteral(hasDeclaration, cgOperationCallExp);
					appendElse();
					appendAssignXor(hasDeclaration, cgOperationCallExp, cgSource, cgArgument);
					appendEndIf();
				}
			}
			else {
				hasDeclaration = appendDeclaration(hasDeclaration, cgOperationCallExp);
				appendIfEqualsNull(cgSource);
				appendAssignNullLiteral(hasDeclaration, cgOperationCallExp);
				appendElse();
				if (cgArgument.isNonNull()) {
					appendAssignXor(hasDeclaration, cgOperationCallExp, cgSource, cgArgument);
				}
				else {
					appendIfEqualsNull(cgArgument);
					appendAssignNullLiteral(hasDeclaration, cgOperationCallExp);
					appendElse();
					appendAssignXor(hasDeclaration, cgOperationCallExp, cgSource, cgArgument);
					appendEndIf();
				}
				appendEndIf();
			}
			return true;
		}

		@Override
		public@NonNull Class<? extends LibraryOperation> getLibraryOperationClass() {
			return BooleanXorOperation.class;
		}
	}