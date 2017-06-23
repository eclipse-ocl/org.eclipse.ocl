/*******************************************************************************
 * Copyright (c) 2014, 2015 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   E.D.Willink - initial API and implementation
 *   E.D.Willink (CEA LIST) - Bug 388529
 *******************************************************************************/
package org.eclipse.ocl.xtext.essentialocl.attributes;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.OCLExpression;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.xtext.essentialoclcs.NavigatingArgCS;
import org.eclipse.ocl.xtext.essentialoclcs.NavigationRole;
import org.eclipse.ocl.xtext.essentialoclcs.RoundBracketedClauseCS;

public class OperationMatcher extends AbstractOperationMatcher
{
	protected final @NonNull List<@NonNull OCLExpression> asArguments = new ArrayList<>();

	public OperationMatcher(@NonNull EnvironmentFactoryInternal environmentFactory, @Nullable Type sourceType, @Nullable Type sourceTypeValue, @NonNull RoundBracketedClauseCS csRoundBracketedClause) {
		super(environmentFactory, sourceType, sourceTypeValue);
		for (NavigatingArgCS csNavigatingArg : csRoundBracketedClause.getOwnedArguments()) {
			if (csNavigatingArg.getRole() == NavigationRole.EXPRESSION) {
				OCLExpression asArgument = PivotUtil.getPivot(OCLExpression.class, csNavigatingArg);
				asArguments.add(ClassUtil.nonNullState(asArgument));
			}
		}
	}

	@Override
	public @NonNull OCLExpression getArgument(int i) {
		return asArguments.get(i);
	}

	@Override
	public int getArgumentCount() {
		return asArguments.size();
	}
}