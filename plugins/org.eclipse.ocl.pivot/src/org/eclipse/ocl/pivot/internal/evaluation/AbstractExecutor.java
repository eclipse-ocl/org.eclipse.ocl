/*******************************************************************************
 * Copyright (c) 2015, 2016 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.internal.evaluation;

import java.util.Map;
import java.util.regex.Pattern;

import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.CompleteEnvironment;
import org.eclipse.ocl.pivot.NamedElement;
import org.eclipse.ocl.pivot.NavigationCallExp;
import org.eclipse.ocl.pivot.OCLExpression;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.StandardLibrary;
import org.eclipse.ocl.pivot.TypedElement;
import org.eclipse.ocl.pivot.evaluation.EvaluationEnvironment;
import org.eclipse.ocl.pivot.evaluation.EvaluationLogger;
import org.eclipse.ocl.pivot.evaluation.EvaluationVisitor;
import org.eclipse.ocl.pivot.evaluation.Evaluator;
import org.eclipse.ocl.pivot.evaluation.IndentingLogger;
import org.eclipse.ocl.pivot.evaluation.ModelManager;
import org.eclipse.ocl.pivot.ids.IdResolver;
import org.eclipse.ocl.pivot.ids.IdResolver.IdResolverExtension;
import org.eclipse.ocl.pivot.internal.manager.MetamodelManagerInternal;
import org.eclipse.ocl.pivot.internal.messages.PivotMessagesInternal;
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal;
import org.eclipse.ocl.pivot.library.LibraryProperty;
import org.eclipse.ocl.pivot.messages.StatusCodes;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.values.InvalidValueException;

/**
 * @since 1.1
 */
public abstract class AbstractExecutor implements ExecutorInternal
{	
	// This is the same as HashMap's default initial capacity
	private static final int DEFAULT_REGEX_CACHE_LIMIT = 16;

	// this is the same as HashMap's default load factor
	private static final float DEFAULT_REGEX_CACHE_LOAD_FACTOR = 0.75f;
	
	protected final EnvironmentFactoryInternal.@NonNull EnvironmentFactoryInternalExtension environmentFactory;
	/**
	 * @deprecated implement modelManager in derived class
	 */
	@Deprecated
	protected final ModelManager modelManager;
	private /*@LazyNonNull*/ EvaluationEnvironment.EvaluationEnvironmentExtension rootEvaluationEnvironment = null;
	private /*@LazyNonNull*/ EvaluationEnvironment.EvaluationEnvironmentExtension evaluationEnvironment = null;
	private /*@LazyNonNull*/ EvaluationVisitor.EvaluationVisitorExtension evaluationVisitor;

	/**
	 * Lazily-created cache of reusable regex patterns to avoid
	 * repeatedly parsing the same regexes.
	 */
	private /*@LazyNonNull*/ Map<String, Pattern> regexPatterns = null;

	private EvaluationLogger logger = IndentingLogger.OUT;

	protected AbstractExecutor(EnvironmentFactoryInternal.@NonNull EnvironmentFactoryInternalExtension environmentFactory) {
		this.environmentFactory = environmentFactory;
		this.modelManager = null;
	}

	/**
	 * @deprecated implement modelManager in derived class
	 */
	@Deprecated
	protected AbstractExecutor(EnvironmentFactoryInternal.@NonNull EnvironmentFactoryInternalExtension environmentFactory, @NonNull ModelManager modelManager) {
		this.environmentFactory = environmentFactory;
		this.modelManager = modelManager;
	}
	
	@Override
	public void add(@NonNull TypedElement referredVariable, @Nullable Object value) {
		evaluationEnvironment.add(referredVariable, value);
	}

	protected EvaluationVisitor.@NonNull EvaluationVisitorExtension createEvaluationVisitor() {
		EvaluationVisitor.EvaluationVisitorExtension result = new BasicEvaluationVisitor(this);
	    
	    if (environmentFactory.isEvaluationTracingEnabled()) {
	        // decorate the evaluation visitor with tracing support
	        result = new TracingEvaluationVisitor(result);
	    }
	    
	    return result;
	}

	/**
	 * Creates (on demand) the regular-expression matcher cache. The default
	 * implementation creates an access-ordered LRU cache with a limit of 16
	 * entries. Subclasses may override to create a map with whatever different
	 * performance characteristics may be required.
	 * 
	 * @return the new regular-expression matcher cache
	 * 
	 * @see #getRegexPattern(String)
	 */
	protected @NonNull Map<String, Pattern> createRegexCache() {
		return new java.util.LinkedHashMap<String, Pattern>(
			DEFAULT_REGEX_CACHE_LIMIT, DEFAULT_REGEX_CACHE_LOAD_FACTOR, true) {

			private static final long serialVersionUID = 1L;

			@Override
			protected boolean removeEldestEntry(
					Map.Entry<String, Pattern> eldest) {
				return size() > DEFAULT_REGEX_CACHE_LIMIT;
			}
		};
	}
	
	protected EvaluationEnvironment.@NonNull EvaluationEnvironmentExtension createNestedEvaluationEnvironment(EvaluationEnvironment.@NonNull EvaluationEnvironmentExtension evaluationEnvironment, @NonNull NamedElement executableObject, @Nullable OCLExpression callingObject) {
		return new BasicEvaluationEnvironment(evaluationEnvironment, executableObject, callingObject);
	}
	
	/** @deprecated Evaluator no longer nests */
	@Deprecated
	@Override	
	public @NonNull Evaluator createNestedEvaluator() {
		return this;
	}

	protected EvaluationEnvironment.@NonNull EvaluationEnvironmentExtension createRootEvaluationEnvironment(@NonNull NamedElement executableObject) {
		return new BasicEvaluationEnvironment(this, executableObject);
	} 
	
	@Override	
	public void dispose() {}

	@Override
	public @Nullable Object evaluate(@NonNull OCLExpression body) {
		return evaluationVisitor.evaluate(body);
	}

	@Override
	public @NonNull CompleteEnvironment getCompleteEnvironment() {
		return environmentFactory.getCompleteEnvironment();
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

	@Override
	public @NonNull EnvironmentFactoryInternal getEnvironmentFactory() {
		return environmentFactory;
	}

	@Override
	public @NonNull EvaluationEnvironment getEvaluationEnvironment() {
		return ClassUtil.nonNullState(evaluationEnvironment);
	}

	@Override
	public EvaluationVisitor.@NonNull EvaluationVisitorExtension getEvaluationVisitor() {
		EvaluationVisitor.EvaluationVisitorExtension evaluationVisitor2 = evaluationVisitor;
		if (evaluationVisitor2 == null) {
			evaluationVisitor = evaluationVisitor2 = createEvaluationVisitor();
		}
		return evaluationVisitor2;
	}

//	@Override
//	public @NonNull ExecutorInternal getExecutor() {
//		return this;
//	}

	@Override
	public IdResolver.@NonNull IdResolverExtension getIdResolver() {
		return (IdResolverExtension) environmentFactory.getIdResolver();
	}

	@Override
	public @Nullable EvaluationLogger getLogger() {
		return logger;
	}

	@Override
	public @NonNull MetamodelManagerInternal getMetamodelManager() {
		return environmentFactory.getMetamodelManager();
	}

	/**
	 * Return a cached matcher for a give regular expression.
	 */
	@Override
	public @NonNull Pattern getRegexPattern(@NonNull String regex) {
		if (regexPatterns == null) {
			synchronized (this) {
				if (regexPatterns == null) {
					regexPatterns = createRegexCache();
				}
			}
		}
		synchronized (regexPatterns) {
			Pattern pattern = regexPatterns.get(regex);
			if (pattern == null) {
//				System.out.println("Compile " + regex);
				pattern = Pattern.compile(regex);
				assert pattern != null;
				regexPatterns.put(regex, pattern);
			}
//			else {
//				System.out.println("Re-use " + regex);
//			}
			return pattern;
		}
	}

	@Override
	public @NonNull EvaluationEnvironment getRootEvaluationEnvironment() {
		return ClassUtil.nonNullState(rootEvaluationEnvironment);
	}

	@Override
	public int getSeverity(@Nullable Object validationKey) {
		StatusCodes.Severity severity = environmentFactory.getSeverity(validationKey);
		return severity != null ? severity.getStatusCode() : StatusCodes.WARNING;
	}

	@Override
	public @NonNull StandardLibrary getStandardLibrary() {
		return environmentFactory.getStandardLibrary();
	}

	@Override
	public org.eclipse.ocl.pivot.@NonNull Class getStaticTypeOf(@Nullable Object value) {
		return environmentFactory.getIdResolver().getStaticTypeOf(value);
	}

	@Override
	public org.eclipse.ocl.pivot.@NonNull Class getStaticTypeOf(@Nullable Object value, @NonNull Object... values) {
		return environmentFactory.getIdResolver().getStaticTypeOf(value, values);
	}
 
	@Override
	public org.eclipse.ocl.pivot.@NonNull Class getStaticTypeOf(@Nullable Object value, @NonNull Iterable<?> values) {
		return environmentFactory.getIdResolver().getStaticTypeOf(value, values);
	}
    
	@Override
	public @Nullable Object getValueOf(@NonNull TypedElement referredVariable) {
        return evaluationEnvironment.getValueOf(referredVariable);
	}

	@Override
	public @NonNull EvaluationEnvironment initializeEvaluationEnvironment(@NonNull NamedElement executableObject) {
		EvaluationEnvironment.EvaluationEnvironmentExtension rootEvaluationEnvironment = createRootEvaluationEnvironment(executableObject);
		setRootEvaluationEnvironment(rootEvaluationEnvironment);
		return rootEvaluationEnvironment;
	}

	@Override
	public @Nullable Object internalExecuteNavigationCallExp(@NonNull NavigationCallExp navigationCallExp, @NonNull Property referredProperty, @Nullable Object sourceValue) {
		if (navigationCallExp.isIsSafe() && (sourceValue == null)) {
			return null;
		}
		MetamodelManagerInternal.MetamodelManagerInternalExtension metamodelManager = environmentFactory.getMetamodelManager();
		LibraryProperty.LibraryPropertyExtension implementation = (LibraryProperty.LibraryPropertyExtension)metamodelManager.getImplementation(navigationCallExp, sourceValue, referredProperty);
		try {
			return implementation.evaluate(this, navigationCallExp.getTypeId(), sourceValue);
		}
		catch (InvalidValueException e) {
			throw e;
		}
		catch (Exception e) {
			// This is a backstop. Library operations should catch their own exceptions
			//  and produce a better reason as a result.
			throw new InvalidValueException(e, PivotMessagesInternal.FailedToEvaluate_ERROR_, referredProperty, sourceValue, navigationCallExp);
		}
	}

	@Override
	public boolean isCanceled() {
		return evaluationVisitor.isCanceled();
	}

	@Override
	public void popEvaluationEnvironment() {
		evaluationEnvironment = ClassUtil.nonNullState(evaluationEnvironment.getParentEvaluationEnvironment());
	}

	@Override
	public @NonNull EvaluationEnvironment pushEvaluationEnvironment(@NonNull NamedElement executableObject, @Nullable OCLExpression callingObject) {
		EvaluationEnvironment.EvaluationEnvironmentExtension evaluationEnvironment2 = ClassUtil.nonNullState(evaluationEnvironment);
		EvaluationEnvironment.EvaluationEnvironmentExtension nestedEvaluationEnvironment = createNestedEvaluationEnvironment(evaluationEnvironment2, executableObject, callingObject);
		evaluationEnvironment = nestedEvaluationEnvironment;
		return nestedEvaluationEnvironment;
	}

	@Override
	public void replace(@NonNull TypedElement referredVariable, @Nullable Object value) {
		evaluationEnvironment.replace(referredVariable, value);
	}

	@Override
	public void setCanceled(boolean isCanceled) {
		evaluationVisitor.setCanceled(isCanceled);
	}

	@Override
	public void setLogger(@Nullable EvaluationLogger logger) {
		this.logger = logger;
	}

	protected void setRootEvaluationEnvironment(EvaluationEnvironment.@NonNull EvaluationEnvironmentExtension evaluationEnvironment) {
		assert this.rootEvaluationEnvironment == null;
		this.rootEvaluationEnvironment = evaluationEnvironment;
		this.evaluationEnvironment = evaluationEnvironment;
	}
}
