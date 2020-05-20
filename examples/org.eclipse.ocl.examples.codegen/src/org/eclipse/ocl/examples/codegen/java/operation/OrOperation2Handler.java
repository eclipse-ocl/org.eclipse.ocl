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
import org.eclipse.ocl.pivot.library.logical.BooleanOrOperation2;

/**
 * OrOperation2Handler generates inline code for the or2 Boolean operation.
 */
public class OrOperation2Handler extends AbstractLibraryOperationHandler
	{
		public OrOperation2Handler( @NonNull JavaStream js) {
			super(js);
		}

		@Override
		public @NonNull Boolean generate(@NonNull CGLibraryOperationCallExp cgOperationCallExp) {
			assert !cgOperationCallExp.getReferredOperation().isIsInvalidating();
			assert cgOperationCallExp.getReferredOperation().isIsValidating();
			boolean hasDeclaration = false;
			//
			//	Trivial source cases
			//
			final CGValuedElement cgSource = cgOperationCallExp.getSource();
			if (appendThrowIfNull(cgSource, "or2 source")) {
				return false;
			}
			if (appendThrowIfInvalid(cgSource, "or2 source")) {
				return false;
			}
			if (cgSource.isTrue()) {
				appendAssignBooleanLiteral(hasDeclaration, cgOperationCallExp, true);
				return true;
			}
			//
			//	Trivial argument cases
			//
			CGValuedElement cgArgument = cgOperationCallExp.getArguments().get(0);
			assert cgArgument != null;
			if (cgArgument.isTrue()) {
				appendAssignBooleanLiteral(hasDeclaration, cgOperationCallExp, true);
				return true;
			}
			//
			//	Trivial source+argument case
			//
			if (cgSource.isFalse() && cgArgument.isFalse()) {
				appendAssignBooleanLiteral(hasDeclaration, cgOperationCallExp, false);
				return true;
			}
			//
			//	Real case
			//
			boolean hasConstantSource = cgSource.isFalse();
			if (!hasConstantSource) {
				if (!js.appendLocalStatements(cgSource)) {
					return false;
				}
				appendThrowIfMayBeNull(cgSource, "or2 source");
				appendThrowIfMayBeInvalid(cgSource);
				hasDeclaration = appendDeclaration(hasDeclaration, cgOperationCallExp);
				appendIfEqualsBoolean0(cgSource, true);
				appendAssignBooleanLiteral(hasDeclaration, cgOperationCallExp, true);
				appendElse();
			}
			try {
				if (!js.appendLocalStatements(cgArgument)) {
					return false;
				}
				if (appendThrowIfNull(cgArgument, "or2 argument")) {
					return !hasConstantSource;
				}
				if (appendThrowIfInvalid(cgArgument, "or2 argument")) {
					return !hasConstantSource;
				}
				if (cgArgument.isFalse()) {
					appendAssignBooleanLiteral(hasDeclaration, cgOperationCallExp, false);
					return true;
				}
				appendThrowIfMayBeNull(cgArgument, "or2 argument");
				appendThrowIfMayBeInvalid(cgArgument);
				appendAssignValue(hasDeclaration, cgOperationCallExp, cgArgument);
				return true;
			}
			finally {
				if (!hasConstantSource) {
					appendEndIf();
				}
			}
		}

		@Override
		public@NonNull Class<? extends LibraryOperation> getLibraryOperationClass() {
			return BooleanOrOperation2.class;
		}
	}