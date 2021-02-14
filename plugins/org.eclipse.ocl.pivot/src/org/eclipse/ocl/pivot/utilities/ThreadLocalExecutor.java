/*******************************************************************************
 * Copyright (c) 2010, 2020 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - initial API and implementation
 * 	 E.D.Willink (Obeo) - Bug 416287 - tuple-valued constraints
 * 	 E.D.Willink (CEA LIST) - Bug 425799 - validity view
 *******************************************************************************/
package org.eclipse.ocl.pivot.utilities;

import org.apache.log4j.Logger;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.evaluation.Executor;

/**
 * The ThreadLocalExecutor enables a standard EMF operation such as getXXX() to locate its OCL Executor
 * despite the inability of the EMF API to pass it directly, provided only one OCL environment is active
 * on the prevailing thread. If the LOcalThread access fails the caller should fall back to the
 * initial / legacy approach to discover an Executor via a ResourceSet adapter.
 *
 * See Bug 570995 for the design considerations.
 *
 * @since 1.14
 */
public class ThreadLocalExecutor
{
	private static final @NonNull ThreadLocal</*@NonNull*/ ThreadLocalExecutor> INSTANCE = new ThreadLocal<>();
	static {
		INSTANCE.set(new ThreadLocalExecutor());
	}

	private static final Logger logger = Logger.getLogger(ThreadLocalExecutor.class);

	/**
	 * Register the start of environmentFactory's activity. If another EnvironmentFactory is already
	 * registered concurrentEnvironmentFactories is set and basicGetExecutor() returns null until reset().
	 */
	public static void addEnvironmentFactory(@NonNull EnvironmentFactory environmentFactory) {
		ThreadLocalExecutor threadLocalExecutor = INSTANCE.get();
		threadLocalExecutor.localAddEnvironmentFactory(environmentFactory);
	}

	/**
	 * Return the prevailing thread-unique EnvironmentFactory or null if none/many.
	 */
	public static @Nullable EnvironmentFactory basicGetEnvironmentFactory() {
		ThreadLocalExecutor threadLocalExecutor = INSTANCE.get();
		return threadLocalExecutor.localBasicGetEnvironmentFactory();
	}

	/**
	 * Return the prevailing thread-unique Executor or null if none/many.
	 */
	public static @Nullable Executor basicGetExecutor() {
		ThreadLocalExecutor threadLocalExecutor = INSTANCE.get();
		return threadLocalExecutor.localBasicGetExecutor();
	}

	/**
	 * Register the end of environmentFactory's activity.
	 */
	public static void removeEnvironmentFactory(@NonNull EnvironmentFactory environmentFactory) {
		ThreadLocalExecutor threadLocalExecutor = INSTANCE.get();
		threadLocalExecutor.localRemoveEnvironmentFactory(environmentFactory);
	}

	/**
	 * Reset to the initial no-EnvironmentFactory or Executor instances active state.
	 */
	public static void reset() {
		ThreadLocalExecutor threadLocalExecutor = INSTANCE.get();
		threadLocalExecutor.localReset();
	}

	/**
	 * Set the Executor instance for the current thread. This is returned by basicGetExecutor() provided
	 * only a single EnvironmentFactory instance is active.
	 */
	public static void setExecutor(@NonNull Executor executor) {
		ThreadLocalExecutor threadLocalExecutor = INSTANCE.get();
		threadLocalExecutor.localSetExecutor(executor);
	}

	public static @NonNull String toDebugString() {
		ThreadLocalExecutor threadLocalExecutor = INSTANCE.get();
		return threadLocalExecutor.toString();
	}

	/**
	 * Set true once multiple EnvironmentFactory instances are constructed on this thread. Reset by reset().
	 */
	private boolean concurrentEnvironmentFactories = false;

	/**
	 * The only active EnvironmentFactory on th thread, null if none or many.
	 */
	private @Nullable EnvironmentFactory environmentFactory = null;

	/**
	 * The Executor for the only active EnvironmentFactory on this thread, null if no unique Executor.
	 */
	private @Nullable Executor executor = null;

	private ThreadLocalExecutor() {}

	private void localAddEnvironmentFactory(@NonNull EnvironmentFactory environmentFactory) {
		if (!concurrentEnvironmentFactories) {
			if (this.environmentFactory == null) {
				assert this.executor == null;		// ?? lightweight Executor promoted to non-lightweight ??
				this.environmentFactory = environmentFactory;
			}
			else if (this.environmentFactory != environmentFactory) {
				this.environmentFactory = null;
				this.executor = null;
				this.concurrentEnvironmentFactories = true;
				String message = "Concurrent EnvironmentFactory instances inhibit LocalThread Executor passing.\n" +
						"\tSee https://wiki.eclipse.org/OCL/FAQ#Concurrent_EnvironmentFactory_instances";
//				System.out.println(message);
				logger.warn(message);
			}
		}
		else {
			assert this.executor == null;
		}
	}

	private @Nullable EnvironmentFactory localBasicGetEnvironmentFactory() {
		if (concurrentEnvironmentFactories) {
			assert environmentFactory == null;
		}
		return environmentFactory;
	}

	private @Nullable Executor localBasicGetExecutor() {
		if (concurrentEnvironmentFactories) {
			assert executor == null;
		}
		return executor;
	}

	private void localRemoveEnvironmentFactory(@NonNull EnvironmentFactory environmentFactory) {
		if (!concurrentEnvironmentFactories) {
			if (this.environmentFactory == environmentFactory) {
				this.environmentFactory = null;
				this.executor = null;
			}
		}
	}

	private void localReset() {
		environmentFactory = null;
		executor = null;
		concurrentEnvironmentFactories = false;
	}

	private void localSetExecutor(@NonNull Executor executor) {
		if (!concurrentEnvironmentFactories) {
			this.executor = executor;
		}
	}

	@Override
	public @NonNull String toString() {
		return "environmentFactory=" + String.valueOf(environmentFactory) + " executor=" + String.valueOf(executor) + " concurrentEnvironmentFactories=" + concurrentEnvironmentFactories;
	}
}