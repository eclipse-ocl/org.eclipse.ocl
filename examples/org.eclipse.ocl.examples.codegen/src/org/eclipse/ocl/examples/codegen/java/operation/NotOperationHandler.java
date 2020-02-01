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
import org.eclipse.ocl.pivot.library.logical.BooleanNotOperation;

/**
 * NotOperationHandler generates inline code for the not Boolean operation.
 */
public class NotOperationHandler extends AbstractLibraryOperationHandler
	{
		public NotOperationHandler( @NonNull JavaStream js) {
			super(js);
		}

		@Override
		public @NonNull Boolean generate(@NonNull CGLibraryOperationCallExp cgOperationCallExp) {
			assert cgOperationCallExp.getReferredOperation().isIsValidating();
			boolean hasDeclaration = false;
			//
			//	Trivial source cases
			//
			final CGValuedElement cgSource = cgOperationCallExp.getSource();
			if (appendThrowIfInvalid(cgSource, "not source")) {
				return false;
			}
			if (cgSource.isFalse()) {
				appendAssignBooleanLiteral(hasDeclaration, cgOperationCallExp, true);
				return true;
			}
			if (cgSource.isTrue()) {
				appendAssignBooleanLiteral(hasDeclaration, cgOperationCallExp, false);
				return true;
			}
			if (cgSource.isNull()) {
				appendAssignNullLiteral(hasDeclaration, cgOperationCallExp);
				return true;
			}
			//
			//	Real case
			//
			if (!js.appendLocalStatements(cgSource)) {
				return false;
			}
			appendThrowIfMayBeInvalid(cgSource);
			hasDeclaration = appendDeclaration(hasDeclaration, cgOperationCallExp);
			appendIfEqualsBoolean0(cgSource, false);
			appendAssignBooleanLiteral(hasDeclaration, cgOperationCallExp, true);
			appendElse();
			appendIfEqualsBoolean0(cgSource, true);
			appendAssignBooleanLiteral(hasDeclaration, cgOperationCallExp, false);
			appendElse();
			appendAssignNullLiteral(hasDeclaration, cgOperationCallExp);
			appendEndIf();
			appendEndIf();
			return true;
		}

		@Override
		public@NonNull Class<? extends LibraryOperation> getLibraryOperationClass() {
			return BooleanNotOperation.class;
		}
	}