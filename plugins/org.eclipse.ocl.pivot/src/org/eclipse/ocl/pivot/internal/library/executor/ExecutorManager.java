/*******************************************************************************
 * Copyright (c) 2011, 2021 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.internal.library.executor;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.CompleteEnvironment;
import org.eclipse.ocl.pivot.NamedElement;
import org.eclipse.ocl.pivot.OCLExpression;
import org.eclipse.ocl.pivot.StandardLibrary;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.TypedElement;
import org.eclipse.ocl.pivot.evaluation.EvaluationEnvironment;
import org.eclipse.ocl.pivot.evaluation.EvaluationLogger;
import org.eclipse.ocl.pivot.evaluation.Evaluator;
import org.eclipse.ocl.pivot.evaluation.ModelManager;
import org.eclipse.ocl.pivot.internal.evaluation.AbstractExecutor2;
import org.eclipse.ocl.pivot.internal.evaluation.ExecutorInternal;
import org.eclipse.ocl.pivot.messages.StatusCodes;
import org.eclipse.ocl.pivot.utilities.EnvironmentFactory;
import org.eclipse.ocl.pivot.utilities.MetamodelManager;
import org.eclipse.ocl.pivot.utilities.Option;
import org.eclipse.ocl.pivot.values.CollectionValue;
import org.eclipse.ocl.pivot.values.InvalidValueException;
import org.eclipse.ocl.pivot.values.Value;

public abstract class ExecutorManager extends AbstractExecutor2
{
	private static final @NonNull EvaluationEnvironment nullEvaluationEnvironment = new EvaluationEnvironment()
	{
		@Override
		public void add(@NonNull TypedElement referredVariable, @Nullable Object value) {}

		@Override
		public void clear() {}

		@Override
		public @NonNull Map<Option<?>, Object> clearOptions() {
			return Collections.emptyMap();
		}

		@Override
		public void dispose() {}

		@Override
		public <T> @Nullable T getAdapter(Class<T> adapterType) {
			return null;
		}

		@Override
		public @NonNull EnvironmentFactory getEnvironmentFactory() {
			throw new UnsupportedOperationException();
		}

		@Override
		public @NonNull NamedElement getExecutableObject() {
			throw new UnsupportedOperationException();
		}

		//		@Override
		//		public @NonNull ExecutorInternal getExecutor() {
		//			throw new UnsupportedOperationException();
		//		}

		@Override
		public @NonNull ModelManager getModelManager() {
			throw new UnsupportedOperationException();
		}

		@Override
		public Map<Option<?>, Object> getOptions() {
			return null;
		}

		//		@Override
		//		public EvaluationEnvironment.@Nullable EvaluationEnvironmentExtension getParent() {
		//			return null;
		//		}

		@Override
		public <@Nullable T> T getValue(@NonNull Option<T> option) {
			return null;
		}

		@Override
		public @Nullable Object getValueOf(@NonNull TypedElement referredVariable) {
			return null;
		}

		@Override
		public @NonNull Set<TypedElement> getVariables() {
			return Collections.emptySet();
		}

		@Override
		public boolean isEnabled(@NonNull Option<@Nullable Boolean> option) {
			return false;
		}

		@Override
		public <@Nullable T> void putOptions(@NonNull Map<? extends Option<T>, ? extends T> options) {
		}

		@Override
		public @Nullable Object remove(@NonNull TypedElement referredVariable) {
			return null;
		}

		@Override
		public <@Nullable T> @Nullable T removeOption(@NonNull Option<T> option) {
			return null;
		}

		@Override
		public <@Nullable T> @NonNull Map<Option<T>, T> removeOptions(@NonNull Collection<Option<T>> options) {
			return Collections.emptyMap();
		}

		@Override
		public void replace(@NonNull TypedElement referredVariable, @Nullable Object value) {}

		@Override
		public <T> void setOption(@NonNull Option<T> option, @Nullable T value) {
		}
	};

	protected final @NonNull CompleteEnvironment environment;
	protected final @NonNull StandardLibrary standardLibrary;

	/**
	 * Set true by {@link #setCanceled} to terminate execution at next call to {@link #getValuefactory()}.
	 */
	private boolean isCanceled = false;

	private @Nullable ExecutorInternal interpretedExecutor = null;

	/**
	 * @since 1.7
	 */
	public static int CONSTRUCTION_COUNT = 0;

	public ExecutorManager(@NonNull CompleteEnvironment environment) {
		CONSTRUCTION_COUNT++;
		this.environment = environment;
		this.standardLibrary = environment.getOwnedStandardLibrary();
	//	NameUtil.errPrintln("Create " + NameUtil.debugSimpleName(this));
	}

	/**
	 * @since 1.1
	 */
	@Override
	public void add(@NonNull TypedElement referredVariable, @Nullable Object value) {}

	@Override
	public @Nullable ExecutorInternal basicGetInterpretedExecutor() {
		return interpretedExecutor;
	}

	/** @deprecated Evaluator no longer nests
	 * @since 1.1*/
	@Deprecated
	@Override
	public @NonNull Evaluator createNestedEvaluator() {
		return this;
	}

	@Override
	public void dispose() {}

	@Override
	public @NonNull Value evaluate(@NonNull OCLExpression body) {
		throw new UnsupportedOperationException();
		//		try {
		//			return ((LibraryUnaryOperation)body).evaluate(this, null, null);		// WIP
		//		} catch (InvalidValueException e) {
		//			return throwInvalidEvaluation(e);
		//		}
	}

	public Value evaluateIteration(Type returnType, CollectionValue sourceVal, TypedElement accumulator,
			OCLExpression body, TypedElement[] iterators) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

//	@Override
//	protected void finalize() throws Throwable {
//		System.out.println("Finalize " + NameUtil.debugSimpleName(this));
//		super.finalize();
//	}

	@Override
	public @NonNull CompleteEnvironment getCompleteEnvironment() {
		return environment;
	}

	@Override
	public int getDiagnosticSeverity(int severityPreference, @Nullable Object resultValue) {
		if (resultValue == null) {
			return Diagnostic.ERROR;
		}
		else if (resultValue instanceof InvalidValueException) {
			return Diagnostic.CANCEL;
		}
		else {
			return severityPreference;
		}
	}

	public @NonNull Type getDynamicTypeOf(@Nullable Object value) {
		return getIdResolver().getDynamicTypeOf(value);
	}

	/**
	 * @since 1.1
	 */
	@Override
	public @NonNull EnvironmentFactory getEnvironmentFactory() {
		throw new UnsupportedOperationException();
	}

	@Override
	public @NonNull EvaluationEnvironment getEvaluationEnvironment() {
		throw new UnsupportedOperationException();
	}

	//	@Override
	//	public @NonNull Executor getExecutor() {
	//		return this;
	//	}

	//	public @NonNull IdResolver getIdResolver() {
	//		return standardLibrary.getIdResolver();
	//	}

	@Override
	public @Nullable EvaluationLogger getLogger() {
		return null;
	}

	/**
	 * @since 1.1
	 */
	@Override
	public @NonNull MetamodelManager getMetamodelManager() {
		throw new UnsupportedOperationException();
	}

	//	@Override
	//	public @NonNull EvaluationEnvironment getRootEvaluationEnvironment() {
	//		return nullEvaluationEnvironment;
	//	}

	@Override
	public int getSeverity(@Nullable Object validationKey) {
		return StatusCodes.WARNING;
	}

	@Override
	public @NonNull StandardLibrary getStandardLibrary() {
		return standardLibrary;
	}

	@Override
	@Deprecated /* @deprecated getStaticTypeOfValue to enable TemplateParameters to be resolved */
	public org.eclipse.ocl.pivot.@NonNull Class getStaticTypeOf(@Nullable Object value) {
		return getIdResolver().getStaticTypeOf(value);
	}

	@Override
	public org.eclipse.ocl.pivot.@NonNull Class getStaticTypeOf(@Nullable Object value, @Nullable Object @NonNull ... values) {
		return getIdResolver().getStaticTypeOf(value, values);
	}

	@Override
	public org.eclipse.ocl.pivot.@NonNull Class getStaticTypeOf(@Nullable Object value, @NonNull Iterable<?> values) {
		return getIdResolver().getStaticTypeOf(value, values);
	}

	/**
	 * @since 1.7
	 */
	@Override
	public org.eclipse.ocl.pivot.@NonNull Class getStaticTypeOfValue(@Nullable Type staticType, @Nullable Object value) {
		return getIdResolver().getStaticTypeOfValue(staticType, value);
	}

	//	public @NonNull ValueFactory getValueFactory() {
	//		if (isCanceled) {
	//			throw new EvaluationHaltedException("Canceled"); //$NON-NLS-1$
	//		}
	//		return valueFactory;
	//	}

	//	@Override
	//	public @Nullable Object getValueOf(@NonNull TypedElement referredVariable) {
	//		return null;
	//	}

	@Override
	public boolean isCanceled() {
		return isCanceled;
	}

	@Override
	public void setCanceled(boolean isCanceled) {
		this.isCanceled = isCanceled;
	}

	@Override
	public void setLogger(@Nullable EvaluationLogger logger) {
		/* ignored */;
	}

	/**
	 * @since 1.1
	 */
	@Override
	public void popEvaluationEnvironment() {}

	/**
	 * @since 1.1
	 * @deprecated use TypedElement argument
	 */
	@Deprecated
	@Override
	public @NonNull EvaluationEnvironment pushEvaluationEnvironment(@NonNull NamedElement executableObject, @Nullable OCLExpression callingObject) {
		return nullEvaluationEnvironment;
	}

	/**
	 * @since 1.3
	 */
	@Override
	public @NonNull EvaluationEnvironment pushEvaluationEnvironment(@NonNull NamedElement executableObject, @Nullable Object caller) {
		return nullEvaluationEnvironment;
	}

	/**
	 * @since 1.1
	 */
	@Override
	public void replace(@NonNull TypedElement referredVariable, @Nullable Object value) {}

	@Override
	public void setInterpretedExecutor(@Nullable ExecutorInternal interpretedExecutor) {
		this.interpretedExecutor = interpretedExecutor;;
	}

	/*	public DomainType typeOf(Value value, Value... values) {
		DomainStandardLibrary standardLibrary = valueFactory.getStandardLibrary();
		DomainType type = value.getType(standardLibrary);
		for (Value anotherValue : values) {
			DomainType anotherType = anotherValue.getType(standardLibrary);
			type = type.getCommonType(standardLibrary, anotherType);
		}
		return type;
	} */

	//	public @NonNull NullValue throwInvalidEvaluation(InvalidValueException e) {
	//		throw new InvalidEvaluationException(null, e);
	//	}

	//	public @NonNull NullValue throwInvalidEvaluation(Throwable e, DomainExpression expression, Object context,
	//			String message, Object... bindings) {
	//		throw new InvalidEvaluationException(null, NLS.bind(message, bindings), e, expression, context);
	//	}
}
