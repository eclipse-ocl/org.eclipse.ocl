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
import org.eclipse.ocl.pivot.util.PivotPlugin;

/**
 * The ThreadLocalExecutor enables a standard EMF operation such as getXXX() to locate its OCL Executor
 * despite the inability of the EMF API to pass it directly, provided only one OCL environment is active
 * on the prevailing thread. If the local thread access fails the caller should fall back to the
 * initial / legacy approach to discover an Executor via a ResourceSet adapter.
 *
 * See Bug 570995 for the design considerations.
 *
 * @since 1.14
 */
public class ThreadLocalExecutor
{
	public static final @NonNull TracingOption THREAD_LOCAL_ENVIRONMENT_FACTORY = new TracingOption(PivotPlugin.PLUGIN_ID, "environmentFactory/threadLocal");

	private static final @NonNull ThreadLocal<@Nullable ThreadLocalExecutor> INSTANCE = new ThreadLocal<>();
	static {
		INSTANCE.set(new ThreadLocalExecutor());
	}

	private static final Logger logger = Logger.getLogger(ThreadLocalExecutor.class);

	/**
	 * Return the prevailing thread-unique EnvironmentFactory or null if none/many.
	 */
	public static @Nullable EnvironmentFactory basicGetEnvironmentFactory() {
		ThreadLocalExecutor threadLocalExecutor = INSTANCE.get();
		return threadLocalExecutor != null ? threadLocalExecutor.localBasicGetEnvironmentFactory() : null;
	}

	/**
	 * Return the prevailing thread-unique Executor or null if none/many.
	 */
	public static @Nullable Executor basicGetExecutor() {
		ThreadLocalExecutor threadLocalExecutor = INSTANCE.get();
		return threadLocalExecutor != null ? threadLocalExecutor.localBasicGetExecutor() : null;
	}

	/**
	 * Reset to the initial no-EnvironmentFactory or Executor instances active state.
	 */
	public static void reset() {
		ThreadLocalExecutor threadLocalExecutor = INSTANCE.get();
		if (threadLocalExecutor != null) {
			threadLocalExecutor.localReset();
		}
	}

	/**
	 * Register the end of the current environmentFactory's activity. This may be used before
	 * environmentFactory is disposed to avoid invalidating its ResourceSet content.
	 *
	 * This must not be used during environmentFactory
	 * disposal by the GC since this would be on the wrong thread.
	 */
	public static void resetEnvironmentFactory() {
		ThreadLocalExecutor threadLocalExecutor = INSTANCE.get();
		if (threadLocalExecutor != null) {
			threadLocalExecutor.localResetEnvironmentFactory();
		}
	}

	public static void resetEnvironmentFactory(@NonNull EnvironmentFactory environmentFactory) {
		ThreadLocalExecutor threadLocalExecutor = INSTANCE.get();
		if (threadLocalExecutor != null) {
			threadLocalExecutor.localResetEnvironmentFactory(environmentFactory);
		}
	}

	/**
	 * Register the start of environmentFactory's activity. If another EnvironmentFactory is already
	 * registered concurrentEnvironmentFactories is set and basicGetExecutor() returns null until reset().
	 */
	public static void setEnvironmentFactory(@NonNull EnvironmentFactory environmentFactory) {
		ThreadLocalExecutor threadLocalExecutor = INSTANCE.get();
		if (threadLocalExecutor != null) {
			threadLocalExecutor.localSetEnvironmentFactory(environmentFactory);
		}
	}

	/**
	 * Set the Executor instance for the current thread. This is returned by basicGetExecutor() provided
	 * only a single EnvironmentFactory instance is active.
	 */
	public static void setExecutor(@NonNull Executor executor) {
		ThreadLocalExecutor threadLocalExecutor = INSTANCE.get();
		if (threadLocalExecutor != null) {
			threadLocalExecutor.localSetExecutor(executor);
		}
	}

	public static @NonNull String toDebugString() {
		ThreadLocalExecutor threadLocalExecutor = INSTANCE.get();
		return threadLocalExecutor != null ? threadLocalExecutor.toString() : "*** FINALIZED ***";
	}

	/**
	 * Perform up to 10 garbage collects followed by 10 millisecond sleep until basicGetEnvironmentFactory
	 * returns false indicating that GC of an EnvironmentFactory held by a WeakOCLReference has succeeded.
	 * Retirn false if an EnvironmentFactory remains active.
	 */
	public static boolean waitForGC() throws InterruptedException {
		ThreadLocalExecutor threadLocalExecutor = INSTANCE.get();
		return (threadLocalExecutor != null) && threadLocalExecutor.localWaitForGC() ;
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

	private @Nullable EnvironmentFactory localBasicGetEnvironmentFactory() {
		if (concurrentEnvironmentFactories) {
			assert environmentFactory == null;
		}
		return (environmentFactory != null) && !environmentFactory.isDisposed() ? environmentFactory : null;
	}

	private @Nullable Executor localBasicGetExecutor() {
		if (concurrentEnvironmentFactories) {
			assert executor == null;
		}
		EnvironmentFactory environmentFactory2 = environmentFactory;
		return (environmentFactory2 == null) || !environmentFactory2.isDisposed() ? executor : null;
	}

	private void localReset() {
		environmentFactory = null;
		executor = null;
		concurrentEnvironmentFactories = false;
		if (THREAD_LOCAL_ENVIRONMENT_FACTORY.isActive()) {
			THREAD_LOCAL_ENVIRONMENT_FACTORY.println("[" + Thread.currentThread().getName() + "] " + toString());
		}
	}

	private void localResetEnvironmentFactory() {
		if (!concurrentEnvironmentFactories) {
			this.environmentFactory = null;
			this.executor = null;
		}
		if (THREAD_LOCAL_ENVIRONMENT_FACTORY.isActive()) {
			THREAD_LOCAL_ENVIRONMENT_FACTORY.println("[" + Thread.currentThread().getName() + "] " + toString());
		}
	}

	private void localResetEnvironmentFactory(@NonNull EnvironmentFactory environmentFactory) {
		if (this.environmentFactory == environmentFactory) {
			localResetEnvironmentFactory();
		}
	}

	private void localSetEnvironmentFactory(@NonNull EnvironmentFactory environmentFactory) {
		if (!concurrentEnvironmentFactories && !environmentFactory.isDisposed()) {
			EnvironmentFactory environmentFactory2 = this.environmentFactory;
			if (environmentFactory2 == null) {
				assert this.executor == null;		// ?? lightweight Executor promoted to non-lightweight ??
				this.environmentFactory = environmentFactory;
			}
			else if (environmentFactory2.isDisposed()) {
				this.environmentFactory = environmentFactory;
				this.executor = null;
			}
			else if (environmentFactory2 != environmentFactory) {
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
		if (THREAD_LOCAL_ENVIRONMENT_FACTORY.isActive()) {
			THREAD_LOCAL_ENVIRONMENT_FACTORY.println("[" + Thread.currentThread().getName() + "] " + toString());
		}
	}

	private void localSetExecutor(@NonNull Executor executor) {
		if (!concurrentEnvironmentFactories) {
			this.executor = executor;
		}
		if (THREAD_LOCAL_ENVIRONMENT_FACTORY.isActive()) {
			THREAD_LOCAL_ENVIRONMENT_FACTORY.println("[" + Thread.currentThread().getName() + "] " + toString());
		}
	}

	@Override
	public @NonNull String toString() {
		if (!concurrentEnvironmentFactories) {
			return (environmentFactory != null ? NameUtil.debugSimpleName(environmentFactory) : "no-environmentFactory")
					+ " " + (executor != null ? NameUtil.debugSimpleName(executor) : "no-executor");
		}
		else {
			return "**** CONCURRENT ENVIRONMENT FACTORIES ****";
		}
	}

	public boolean localWaitForGC() throws InterruptedException {
		if (concurrentEnvironmentFactories) {
			return false;
		}
		EnvironmentFactory environmentFactory2 = environmentFactory;
		if (environmentFactory2 == null) {
			return true;
		}
		for (int i = 0; i < 10; i++) {
			System.gc();
			System.runFinalization();
			if (environmentFactory2.isDisposed()) {
				return true;
			}
			System.out.println("Waiting for EnvironmentFactory GC");
			Thread.sleep(10);
		}
		return false;
	}
}