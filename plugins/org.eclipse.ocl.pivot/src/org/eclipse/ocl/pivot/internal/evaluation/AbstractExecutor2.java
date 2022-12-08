/*******************************************************************************
 * Copyright (c) 2015, 2021 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.internal.evaluation;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Pattern;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.evaluation.ExecutionSupport;
import org.eclipse.ocl.pivot.evaluation.Executor;

/**
 * AbstractExecutor2 unifies the divergence between the code-generated ExecutorManager and the interpreted AbsyractExecutor.
 * @since 1.1
 */
public abstract class AbstractExecutor2 implements Executor.ExecutorExtension
{
	// This is the same as HashMap's default initial capacity
	private static final int DEFAULT_REGEX_CACHE_LIMIT = 16;

	// this is the same as HashMap's default load factor
	private static final float DEFAULT_REGEX_CACHE_LOAD_FACTOR = 0.75f;

	/**
	 * The executor-specific additional execution support.
	 */
	private @Nullable Map<@NonNull Class<? extends ExecutionSupport>, @NonNull ExecutionSupport> executionSupports = null;

	/**
	 * Lazily-created cache of reusable regex patterns to avoid
	 * repeatedly parsing the same regexes.
	 */
	private @Nullable Map<@NonNull String, @NonNull Pattern> regexPatterns = null;

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
	protected @NonNull Map<@NonNull String, @NonNull Pattern> createRegexCache() {
		return new LinkedHashMap<@NonNull String, @NonNull Pattern>(
				DEFAULT_REGEX_CACHE_LIMIT, DEFAULT_REGEX_CACHE_LOAD_FACTOR, true)
		{
			private static final long serialVersionUID = 1L;

			@Override
			protected boolean removeEldestEntry(Map.Entry<@NonNull String, @NonNull Pattern> eldest) {
				return size() > DEFAULT_REGEX_CACHE_LIMIT;
			}
		};
	}

	@Override
	public <T extends ExecutionSupport> @NonNull T getExecutionSupport(@NonNull Class<T> executionSupportClass) throws RuntimeException {
		Map<@NonNull Class<? extends ExecutionSupport>, @NonNull ExecutionSupport> executionSupports2 = executionSupports;
		if (executionSupports2 == null) {
			synchronized (this) {
				executionSupports2 = executionSupports;
				if (executionSupports2 == null) {
					executionSupports2 = executionSupports = new HashMap<>(4);		// Very unlikely to ever be more than 1 ?? use a mini list-based Map
				}
			}
		}
		synchronized (executionSupports2) {
			@SuppressWarnings("unchecked")
			T executionSupport = (T)executionSupports2.get(executionSupportClass);
			if (executionSupport == null) {
				try {
					Constructor<T> constructor = executionSupportClass.getConstructor(Executor.class);
					executionSupport = constructor.newInstance(this);
					executionSupports2.put(executionSupportClass, executionSupport);
				} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
							| InvocationTargetException | NoSuchMethodException | SecurityException e) {
					throw new RuntimeException("Inadequate " + executionSupportClass.getName() + " implementation", e);
				}
			}
			return executionSupport;
		}
	}

	/**
	 * Return a cached matcher for a given regular expression.
	 */
	@Override
	public @NonNull Pattern getRegexPattern(@NonNull String regex) {
		Map<@NonNull String, @NonNull Pattern> regexPatterns2 = regexPatterns;
		if (regexPatterns2 == null) {
			synchronized (this) {
				regexPatterns2 = regexPatterns;
				if (regexPatterns2 == null) {
					regexPatterns2 = regexPatterns = createRegexCache();
				}
			}
		}
		synchronized (regexPatterns2) {
			Pattern pattern = regexPatterns2.get(regex);
			if (pattern == null) {
				//				System.out.println("Compile " + regex);
				pattern = Pattern.compile(regex);
				assert pattern != null;
				regexPatterns2.put(regex, pattern);
			}
			//			else {
			//				System.out.println("Re-use " + regex);
			//			}
			return pattern;
		}
	}

	/**
	 * @since 1.3
	 */
	@Override
	public void resetCaches() {
		executionSupports = null;
		regexPatterns = null;
	}
}
