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
import org.eclipse.ocl.pivot.library.logical.BooleanAndOperation;

/**
 * AndOperationHandler generates inline code for the and Boolean operation.
 */
public class AndOperationHandler extends AbstractLibraryOperationHandler
	{
		public AndOperationHandler( @NonNull JavaStream js) {
			super(js);
		}

		@Override
		public @NonNull Boolean generate(@NonNull CGLibraryOperationCallExp cgOperationCallExp) {
			assert cgOperationCallExp.getReferredOperation().isIsValidating();
			boolean hasDeclaration = false;
			//
			//	Short-circuit cases
			//
			final CGValuedElement cgSource = cgOperationCallExp.getSource();
			CGValuedElement cgArgument = cgOperationCallExp.getArguments().get(0);
			assert cgArgument != null;
			if (cgSource.isFalse() || cgArgument.isFalse()) {
				appendAssignBooleanLiteral(hasDeclaration, cgOperationCallExp, false);
				return true;
			}
			//
			//	Constant cases
			//
			if (cgSource.isConstant() && cgArgument.isConstant()) {
				if (appendThrowIfInvalid(cgSource, "and source")) {
					return false;
				}
				if (appendThrowIfInvalid(cgArgument, "and argument")) {
					return false;
				}
				if (cgSource.isNull() || cgArgument.isNull()) {
					appendAssignNullLiteral(hasDeclaration, cgOperationCallExp);
					return true;
				}
				assert cgSource.isTrue() && cgArgument.isTrue();
				appendAssignBooleanLiteral(hasDeclaration, cgOperationCallExp, true);
				return true;
			}
			//
			//	Real cases that require first term evaluation
			//
			if (!js.appendLocalStatements(cgSource)) {
				return false;
			}
			hasDeclaration = appendDeclaration(hasDeclaration, cgOperationCallExp);
			boolean mayBeFalse1 = appendIfEqualsBoolean1(cgSource, false);
			if (mayBeFalse1) {
				appendAssignBooleanLiteral(hasDeclaration, cgOperationCallExp, false);
				appendElse();
			}
			try {
				//
				//	Real cases that require second term evaluation too
				//
				if (!js.appendLocalStatements(cgArgument)) {
					return false;
				}
				if (cgSource.isTrue()) {
					appendAssignValue(hasDeclaration, cgOperationCallExp, cgArgument);
					return true;
				}
				if (cgArgument.isTrue()) {
					appendAssignValue(hasDeclaration, cgOperationCallExp, cgSource);
					return true;
				}
				boolean mayBeFalse2 = appendIfEqualsBoolean1(cgArgument, false);
				if (mayBeFalse2) {
					appendAssignBooleanLiteral(hasDeclaration, cgOperationCallExp, false);
					appendElse();
				}
				if (!appendThrowIfInvalid(cgSource, "and source") && !appendThrowIfInvalid(cgSource, "and argument")) {
					appendThrowIfMayBeInvalid(cgSource);
					appendThrowIfMayBeInvalid(cgArgument);
					if (cgSource.isNull() || cgArgument.isNull()) {
						appendAssignNullLiteral(hasDeclaration, cgOperationCallExp);
					}
					else if (cgSource.isNonNull()){
						if (cgArgument.isNonNull()){
							appendAssignBooleanLiteral(hasDeclaration, cgOperationCallExp, true);
						}
						else {
							appendIfEqualsNull(cgArgument);
							appendAssignNullLiteral(hasDeclaration, cgOperationCallExp);
							appendElse();
							appendAssignBooleanLiteral(hasDeclaration, cgOperationCallExp, true);
							appendEndIf();
						}
					}
					else {
						if (cgArgument.isNonNull()){
							appendIfEqualsNull(cgSource);
							appendAssignNullLiteral(hasDeclaration, cgOperationCallExp);
							appendElse();
							appendAssignBooleanLiteral(hasDeclaration, cgOperationCallExp, true);
							appendEndIf();
						}
						else {
							appendIfEqualsNull(cgSource, cgArgument);
							appendAssignNullLiteral(hasDeclaration, cgOperationCallExp);
							appendElse();
							appendAssignBooleanLiteral(hasDeclaration, cgOperationCallExp, true);
							appendEndIf();
						}
					}
				}
				if (mayBeFalse2) {
					appendEndIf();
				}
			}
			finally {
				if (mayBeFalse1) {
					appendEndIf();
				}
			}
			return true;
		}

		@Override
		public@NonNull Class<? extends LibraryOperation> getLibraryOperationClass() {
			return BooleanAndOperation.class;
		}
	}