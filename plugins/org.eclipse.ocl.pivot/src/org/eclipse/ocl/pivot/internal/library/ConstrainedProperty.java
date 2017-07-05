/*******************************************************************************
 * Copyright (c) 2011, 2015 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   E.D.Willink - initial API and implementation
 * 	 E.D.Willink (CEA LIST) - Bug 425799 - validity view
 *******************************************************************************/
package org.eclipse.ocl.pivot.internal.library;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.LanguageExpression;
import org.eclipse.ocl.pivot.OCLExpression;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.Variable;
import org.eclipse.ocl.pivot.evaluation.EvaluationEnvironment;
import org.eclipse.ocl.pivot.evaluation.Executor;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.library.AbstractProperty;
import org.eclipse.ocl.pivot.utilities.MetamodelManager;
import org.eclipse.ocl.pivot.utilities.ParserException;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.values.InvalidValueException;

/**
 * An instance of ConstrainedProperty supports evaluation of
 * a relationship defined by constraints.
 */
public class ConstrainedProperty extends AbstractProperty
{
	protected final @NonNull Property property;
	protected /*@LazyNonNull*/ ExpressionInOCL expression = null;

	public ConstrainedProperty(@NonNull Property property) {
		this.property = property;
	}

	@Override
	public @Nullable Object evaluate(@NonNull Executor executor, @NonNull TypeId returnTypeId, @Nullable Object sourceValue) {
		ExpressionInOCL expression2 = expression;
		if (expression2 == null) {
			LanguageExpression defaultSpecification = property.getOwnedExpression();
			if (defaultSpecification == null) {
				throw new InvalidValueException("No defaultExpression for '{0}'", property);
			}
			try {
				MetamodelManager metamodelManager = executor.getMetamodelManager();
				expression = expression2 = metamodelManager.parseSpecification(defaultSpecification);
			} catch (ParserException e) {
				throw new InvalidValueException(e, "Bad defaultExpression for '{0}'", property);
			}
		}
		PivotUtil.checkExpression(expression2);
		EvaluationEnvironment nestedEvaluationEnvironment = executor.pushEvaluationEnvironment(expression2, null);
		Variable contextVariable = expression2.getOwnedContext();
		if (contextVariable != null) {
			nestedEvaluationEnvironment.add(contextVariable, sourceValue);
		}
		try {
			OCLExpression bodyExpression = expression2.getOwnedBody();
			assert bodyExpression != null;
			Object result = executor.evaluate(bodyExpression, expression2, null);
			return result;
		}
		finally {
			executor.popEvaluationEnvironment();
		}
	}
}
