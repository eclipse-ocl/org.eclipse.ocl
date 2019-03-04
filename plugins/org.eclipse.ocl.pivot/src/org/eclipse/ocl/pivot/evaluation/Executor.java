/*******************************************************************************
 * Copyright (c) 2015, 2019 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.evaluation;

import java.util.regex.Pattern;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.CompleteEnvironment;
import org.eclipse.ocl.pivot.NamedElement;
import org.eclipse.ocl.pivot.OCLExpression;
import org.eclipse.ocl.pivot.StandardLibrary;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.TypedElement;
import org.eclipse.ocl.pivot.ids.IdResolver;
import org.eclipse.ocl.pivot.utilities.EnvironmentFactory;
import org.eclipse.ocl.pivot.utilities.MetamodelManager;

/**
 * @since 1.1
 */
public interface Executor extends Evaluator
{
	/**
	 * @since 1.3
	 */
	public interface ExecutorExtension extends Executor
	{
		@NonNull EvaluationEnvironment pushEvaluationEnvironment(@NonNull NamedElement executableObject, @Nullable Object caller);
		void resetCaches();
	}
	void add(@NonNull TypedElement referredVariable, @Nullable Object value);
	@Override
	@Nullable Object evaluate(@NonNull OCLExpression body);
	@Override
	@NonNull CompleteEnvironment getCompleteEnvironment();
	//	@Override
	@NonNull EnvironmentFactory getEnvironmentFactory();
	@Override
	@NonNull EvaluationEnvironment getEvaluationEnvironment();
	@Override
	@NonNull IdResolver getIdResolver();
	@Override
	@Nullable EvaluationLogger getLogger();
	@NonNull MetamodelManager getMetamodelManager();
	@Override
	@NonNull ModelManager getModelManager();
	@Override
	@NonNull Pattern getRegexPattern(@NonNull String regex);
	@Override
	int getSeverity(@Nullable Object validationKey);
	@Override
	@NonNull StandardLibrary getStandardLibrary();
	@Override
	@Deprecated /* @deprecated getStaticTypeOfValue to enable TemplateParameters to be resolved */
	org.eclipse.ocl.pivot.@NonNull Class getStaticTypeOf(@Nullable Object value);
	@Override
	org.eclipse.ocl.pivot.@NonNull Class getStaticTypeOf(@Nullable Object value, @Nullable Object @NonNull ... values);
	@Override
	org.eclipse.ocl.pivot.@NonNull Class getStaticTypeOf(@Nullable Object value, @NonNull Iterable<?> values);
	/**
	 * @since 1.7
	 */
	default org.eclipse.ocl.pivot.@NonNull Class getStaticTypeOfValue(@Nullable Type staticType, @Nullable Object value) {
		return getStaticTypeOf(value);
	}
	void popEvaluationEnvironment();
	/**
	 * @deprecated use Object argument in ExecutorInternalExtension
	 */
	@Deprecated
	@NonNull EvaluationEnvironment pushEvaluationEnvironment(@NonNull NamedElement executableObject, @Nullable OCLExpression callingObject);
	void replace(@NonNull TypedElement referredVariable, @Nullable Object value);
	@Override
	void setLogger(@Nullable EvaluationLogger logger);
}
