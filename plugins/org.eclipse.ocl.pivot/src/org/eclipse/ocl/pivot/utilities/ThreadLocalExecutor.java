/*******************************************************************************
 * Copyright (c) 2010, 2021 Willink Transformations and others.
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
import org.eclipse.ocl.pivot.internal.manager.PivotExecutorManager;
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal;
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

	private static final Logger logger = Logger.getLogger(ThreadLocalExecutor.class);

	/**
	 * Register the start of environmentFactory's activity. If another EnvironmentFactory is already
	 * registered concurrentEnvironmentFactories is set and basicGetExecutor() returns null until reset().
	 */
	public static void attachEnvironmentFactory(@NonNull EnvironmentFactoryInternal environmentFactory) {
		ThreadLocalExecutor threadLocalExecutor = INSTANCE.get();
		if (threadLocalExecutor == null) {
			threadLocalExecutor = new ThreadLocalExecutor();
			INSTANCE.set(threadLocalExecutor);
		}
		threadLocalExecutor.localAttachEnvironmentFactory(environmentFactory);
	}

	/**
	 * Return the prevailing thread-unique EnvironmentFactory or null if none/many.
	 */
	public static @Nullable EnvironmentFactoryInternal basicGetEnvironmentFactory() {
		ThreadLocalExecutor threadLocalExecutor = INSTANCE.get();
		if (threadLocalExecutor == null) {
			return null;
		}
		return threadLocalExecutor.localBasicGetEnvironmentFactory();
	}

	/**
	 * Return the prevailing thread-unique Executor or null if none/many.
	 */
	public static @Nullable Executor basicGetExecutor() {
		ThreadLocalExecutor threadLocalExecutor = INSTANCE.get();
		if (threadLocalExecutor == null) {
			return null;
		}
		return threadLocalExecutor.localBasicGetExecutor();
	}

	public static void detachEnvironmentFactory(@NonNull EnvironmentFactory environmentFactory) {
		ThreadLocalExecutor threadLocalExecutor = INSTANCE.get();
		if (threadLocalExecutor != null) {
			threadLocalExecutor.localDetachEnvironmentFactory(environmentFactory);
		}
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

	/**
	 * Set the Executor instance for the current thread. This is returned by basicGetExecutor() provided
	 * only a single EnvironmentFactory instance is active.
	 */
	public static void setExecutor(@NonNull Executor executor) {
		ThreadLocalExecutor threadLocalExecutor = INSTANCE.get();
		if (threadLocalExecutor == null) {
			threadLocalExecutor = new ThreadLocalExecutor();
			INSTANCE.set(threadLocalExecutor);
		}
		threadLocalExecutor.localSetExecutor(executor);
	}

	public static @NonNull String toDebugString() {
		ThreadLocalExecutor threadLocalExecutor = INSTANCE.get();
		return threadLocalExecutor != null ? threadLocalExecutor.toString() : "*** FINALIZED ***";
	}

	/**
	 * Perform up to 10 garbage collects followed by 10 millisecond sleep until basicGetEnvironmentFactory
	 * returns false indicating that GC of an EnvironmentFactory held by a WeakOCLReference has succeeded.
	 * Return false if an EnvironmentFactory remains active.
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
	private @Nullable EnvironmentFactoryInternal environmentFactory = null;

	/**
	 * The Executor for the only active EnvironmentFactory on this thread, null if no unique Executor.
	 */
	private @Nullable Executor executor = null;

	private ThreadLocalExecutor() {
//		if (THREAD_LOCAL_ENVIRONMENT_FACTORY.isActive()) {
//			THREAD_LOCAL_ENVIRONMENT_FACTORY.println("[" + Thread.currentThread().getName() + "] Create " + toString());
//		}
	}

	@Override
	protected void finalize() throws Throwable {
		if (THREAD_LOCAL_ENVIRONMENT_FACTORY.isActive()) {
			THREAD_LOCAL_ENVIRONMENT_FACTORY.println("[" + Thread.currentThread().getName() + "] Finalize " + toString());
		}
		localReset();
	}

	private void localAttachEnvironmentFactory(@NonNull EnvironmentFactoryInternal newEnvironmentFactory) {
		if (!concurrentEnvironmentFactories && !newEnvironmentFactory.isDisposed()) {
			EnvironmentFactory oldEnvironmentFactory = this.environmentFactory;
			if (oldEnvironmentFactory == null) {
			//	assert this.executor == null;		// ?? lightweight Executor promoted to non-lightweight ??
				setEnvironmentFactory(newEnvironmentFactory);
			}
			else if (oldEnvironmentFactory.isDisposed()) {
				setEnvironmentFactory(newEnvironmentFactory);
			}
			else if (oldEnvironmentFactory != newEnvironmentFactory) {	// FIXME we could help caller by doing a localWaitForGC
				setEnvironmentFactory(null);
				this.executor = null;
				this.concurrentEnvironmentFactories = true;
				String message = "Concurrent EnvironmentFactory instances inhibit local thread Executor passing.\n" +
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

	private @Nullable EnvironmentFactoryInternal localBasicGetEnvironmentFactory() {
		if (concurrentEnvironmentFactories) {
			assert environmentFactory == null;
		}
		return (environmentFactory != null) && !environmentFactory.isDisposed() ? environmentFactory : null;
	}

	private @Nullable Executor localBasicGetExecutor() {
		if (concurrentEnvironmentFactories) {
			assert executor == null;
		}
		EnvironmentFactoryInternal environmentFactory2 = environmentFactory;
		return (environmentFactory2 == null) || !environmentFactory2.isDisposed() ? executor : null;
	}

	private void localDetachEnvironmentFactory(@NonNull EnvironmentFactory environmentFactory) {
		if (this.environmentFactory == environmentFactory) {
			localResetEnvironmentFactory();
		}
	}

	private void localReset() {
		setEnvironmentFactory(null);
		executor = null;
		concurrentEnvironmentFactories = false;
		if (THREAD_LOCAL_ENVIRONMENT_FACTORY.isActive()) {
			THREAD_LOCAL_ENVIRONMENT_FACTORY.println("[" + Thread.currentThread().getName() + "] " + toString());
		}
	}

	private void localResetEnvironmentFactory() {
		if (!concurrentEnvironmentFactories) {
			setEnvironmentFactory(null);
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
		if (executor instanceof PivotExecutorManager) {
			localAttachEnvironmentFactory((EnvironmentFactoryInternal) ((PivotExecutorManager)executor).getEnvironmentFactory());
		}
	}

	private void setEnvironmentFactory(@Nullable EnvironmentFactoryInternal newEnvironmentFactory) {
		EnvironmentFactoryInternal oldEnvironmentFactory = this.environmentFactory;
		if (newEnvironmentFactory != oldEnvironmentFactory) {
			if ((oldEnvironmentFactory != null) && !oldEnvironmentFactory.isDisposed()) {
				oldEnvironmentFactory.detach(this);
				this.environmentFactory = null;
			}
			if ((newEnvironmentFactory != null) && !newEnvironmentFactory.isDisposed()) {
				this.environmentFactory = newEnvironmentFactory;
				newEnvironmentFactory.attach(this);
			}
			this.executor = null;
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
		EnvironmentFactoryInternal environmentFactory2 = environmentFactory;
		if (environmentFactory2 == null) {
			return true;
		}
		for (int i = 0; i < 10; i++) {
			environmentFactory2.detachRedundantThreadLocal();
			System.gc();
			System.runFinalization();
			if (environmentFactory2.isDisposed()) {
				return true;
			}
		//	System.out.println("Waiting for EnvironmentFactory GC");
			Thread.sleep(10);
		}
		return false;
	}
}