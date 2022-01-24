/*******************************************************************************
 * Copyright (c) 2011, 2018 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.internal.library;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.LanguageExpression;
import org.eclipse.ocl.pivot.OCLExpression;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.evaluation.Evaluator;
import org.eclipse.ocl.pivot.evaluation.Executor;
import org.eclipse.ocl.pivot.evaluation.ModelManager;
import org.eclipse.ocl.pivot.ids.PropertyId;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.library.AbstractProperty;
import org.eclipse.ocl.pivot.utilities.AbstractEnvironmentFactory;
import org.eclipse.ocl.pivot.utilities.EnvironmentFactory;
import org.eclipse.ocl.pivot.utilities.ParserException;
import org.eclipse.ocl.pivot.utilities.ValueUtil;

/**
 * A ForeignProperty instance supports evaluation of a foreign property by lookup in the ModelManager cache.
 * (A static property has an instance of the owning class, but not defined by the owning class.)
 *
 * @since 1.18
 */
public class ForeignProperty extends AbstractProperty
{
	/**
	 * @since 1.18
	 */
	public static @Nullable ForeignProperty createForeignProperty(@NonNull EnvironmentFactory environmentFactory, @NonNull Property property) {
		try {
			OCLExpression bodyExpression = null;
			LanguageExpression asLanguageExpression = property.getOwnedExpression();
			if (asLanguageExpression != null) {
				ExpressionInOCL expression = ((AbstractEnvironmentFactory) environmentFactory).parseSpecification(asLanguageExpression);
				bodyExpression = expression.getOwnedBody();
				assert bodyExpression != null;
				return new ForeignProperty(property.getPropertyId(), bodyExpression, null);
			}
			else {
				Object defaultValue = property.getDefaultValue();
				return new ForeignProperty(property.getPropertyId(), null, defaultValue != null ? defaultValue : ValueUtil.NULL_VALUE);
			}
		} catch (ParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	private final @NonNull PropertyId propertyId;
	private final @Nullable OCLExpression initExpression;
	private final @Nullable Object defaultValue;

	private ForeignProperty(@NonNull PropertyId propertyId, @Nullable OCLExpression initExpression, @Nullable Object defaultValue) {
		this.propertyId = propertyId;
		this.initExpression = initExpression;
		this.defaultValue = defaultValue;
	}

	/** @deprecated use Executor */
	@Deprecated
	@Override
	public @Nullable Object evaluate(@NonNull Evaluator evaluator, @NonNull TypeId returnTypeId, @Nullable Object sourceValue) {
		return evaluate(getExecutor(evaluator), returnTypeId, sourceValue);
	}

	/**
	 * @since 1.1
	 */
	@Override
	public @Nullable Object evaluate(@NonNull Executor executor, @NonNull TypeId returnTypeId, @Nullable Object sourceValue) {
		assert sourceValue != null;
		ModelManager modelManager = executor.getModelManager();
		Object value = modelManager.getForeignPropertyValue(sourceValue, propertyId, initExpression, defaultValue);
		return value == ValueUtil.NULL_VALUE ? null : value;
	}
}