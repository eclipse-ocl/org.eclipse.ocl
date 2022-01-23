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
 * A StaticProperty instance supports evaluation of a static property by lookup in the ModelManager cache.
 * (A static property has a single instance for the owning class, rather than one per instance of the owning class.
 */
public class StaticProperty extends AbstractProperty
{
	/**
	 * @since 1.18
	 */
	public static @Nullable StaticProperty createStaticProperty(@NonNull EnvironmentFactory environmentFactory, @NonNull Property property) {
		try {
			OCLExpression bodyExpression = null;
			LanguageExpression asLanguageExpression = property.getOwnedExpression();
			if (asLanguageExpression != null) {
				ExpressionInOCL expression = ((AbstractEnvironmentFactory) environmentFactory).parseSpecification(asLanguageExpression);
				bodyExpression = expression.getOwnedBody();
				assert bodyExpression != null;
				return new StaticProperty(property.getPropertyId(), bodyExpression, null);
			}
			else {
				Object defaultValue = property.getDefaultValue();
				return new StaticProperty(property.getPropertyId(), null, defaultValue != null ? defaultValue : ValueUtil.NULL_VALUE);
			}
		} catch (ParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Deprecated		/* @deprecated not used */
	protected @Nullable Property property;
	private final @NonNull PropertyId propertyId;
	private final @Nullable OCLExpression initExpression;
	private final @Nullable Object defaultValue;

	@Deprecated		/* deprecated not used */
	public StaticProperty(@NonNull Property property) {
		this.property = property;
		this.propertyId = property.getPropertyId();
		this.initExpression = (OCLExpression) property.getOwnedExpression();
		this.defaultValue = property.getDefaultValue();
	}

	/**
	 * @since 1.18
	 */
	private StaticProperty(@NonNull PropertyId propertyId, @Nullable OCLExpression initExpression, @Nullable Object defaultValue) {
		this.propertyId = propertyId;
		this.initExpression = initExpression;
		this.defaultValue = defaultValue;
		this.property = null;
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
		ModelManager modelManager = executor.getModelManager();
		Object value = modelManager.getStaticPropertyValue(propertyId, initExpression, defaultValue);
		return value == ValueUtil.NULL_VALUE ? null : value;
	}
}