/*******************************************************************************
 * Copyright (c) 2021, 2025 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.utilities;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.IntUnaryOperator;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.evaluation.Executor;
import org.eclipse.ocl.pivot.internal.evaluation.ExecutorInternal;
import org.eclipse.ocl.pivot.internal.manager.PivotExecutorManager;
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal;
import org.eclipse.ocl.pivot.util.PivotPlugin;

/**
 * The ThreadLocalExecutor enables a standard EMF operation such as getXXX() to locate its OCL Executor
 * despite the inability of the EMF API to pass it directly, provided only one OCL environment is active
 * on the prevailing thread. If the local thread access fails the caller should fall back to the
 * initial / legacy approach to discover an Executor via a ResourceSet adapter.
 *
 * The derived ThreadLocalExecutorUI supports one OCL environment per IWorkbenchPart on the main UI thread.
 *
 * See Bug 570995 and Bug 571721 for the design considerations.
 *
 * @since 1.14
 */
public class ThreadLocalExecutor implements Nameable
{
	private static final @NonNull String TAG_MANAGER = "manager";
	private static final @NonNull String ATT_CLASS = "class";

	public static final @NonNull TracingOption THREAD_LOCAL_ENVIRONMENT_FACTORY = new TracingOption(PivotPlugin.PLUGIN_ID, "environmentFactory/threadLocal");

	/**
	 * NeedsInit identifies wrapping required to establish and revert OCL functionality around an
	 * OCL activity.
	 *
	 * @since 1.20
	 */
	public enum NeedsInit {
		AS_IS,							// No action needed, EnvironmentFactory already available
		ATTACH_FROM_PART_THREAD,		// EnvironmentFactory must be established from the activePart
		WRAP_WITH_PART_THREAD			// EnvironmentFactory and activiePart must be established/override another partThread
	}

	/**
	 * The InitWrapperCallBack defines the callback for use when an initialization neds to attach prefix and corresponding mpostfix functionality such
	 * as attach/detach to OCL-based functionality on a IWorkbenchPart/Thread. THe API support result/exception returns if the derivation requires them.
	 *
	 * @since 1.20
	 */
	public static interface InitWrapperCallBack<R, T>
	{
		R getResult();
		default @Nullable T getThrowable() { return null; }
		void run();
	}

	/**
	 * The ThreadLocal value of a ThreadLocalExecutor.
	 */
	private static final @NonNull ThreadLocal<@Nullable ThreadLocalExecutor> INSTANCE = new ThreadLocal<>();

	/**
	 * The ThreadLocalExecutor instance that creates thread-specific instances.
	 */
	private static @Nullable ThreadLocalExecutor CREATOR = null;

	private static final Logger logger = Logger.getLogger(ThreadLocalExecutor.class);

	/**
	 * Count (non-zero) of finalize() executions that released resources and so may require further GC and finalize().
	 * This is primarily intended to allow test code to loop intelligently until GC has completed
	 * and so localize leaks to a particular test.
	 */
	private static final @NonNull AtomicInteger finalizerReleases = new AtomicInteger(0);

	/**
	 * Register the start of environmentFactory's activity. If another EnvironmentFactory is already
	 * registered concurrentEnvironmentFactories is set and basicGetExecutor() returns null until reset().
	 */
	public static void attachEnvironmentFactory(@NonNull EnvironmentFactoryInternal environmentFactory) {
		ThreadLocalExecutor threadLocalExecutor = get();
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

	/**
	 * @since 1.15
	 */
	protected static synchronized @NonNull ThreadLocalExecutor createThreadLocalExecutor() {
		ThreadLocalExecutor CREATOR2 = CREATOR;
		if (CREATOR2 == null) {
			ThreadLocalExecutor readExtension = readExtension();
			CREATOR = CREATOR2 = readExtension != null ? readExtension : new ThreadLocalExecutor();
		}
		return CREATOR2.createInstance();
	}

	public static void detachEnvironmentFactory(@NonNull EnvironmentFactory environmentFactory) {
		ThreadLocalExecutor threadLocalExecutor = INSTANCE.get();
		if (threadLocalExecutor != null) {
			threadLocalExecutor.localDetachEnvironmentFactory(environmentFactory);
		}
	}

	/**
	 * Return the current-thread-specific instance of ThreadLocalExecutor creating it if necessary.
	 *
	 * @since 1.15
	 */
	protected static @NonNull ThreadLocalExecutor get() {
		ThreadLocalExecutor threadLocalExecutor = INSTANCE.get();
		if (threadLocalExecutor == null) {
			threadLocalExecutor = createThreadLocalExecutor();
			INSTANCE.set(threadLocalExecutor);
		}
	//	System.out.println(getBracketedThreadName() + " get " + NameUtil.debugSimpleName(threadLocalExecutor));
		return threadLocalExecutor;
	}

	/**
	 * Return the prevailing thread-unique name surrounded by square brackets.
	 *
	 * @since 1.20
	 */
	public static @NonNull String getBracketedThreadName() {
		ThreadLocalExecutor threadLocalExecutor = INSTANCE.get();
		if (threadLocalExecutor == null) {
			return "[" + Thread.currentThread().getName() + "]";
		}
		return threadLocalExecutor.getThreadName();
	}

	/**
	 * Return the prevailing thread-unique EnvironmentFactory or throw an IllegalStateException none/many.
	 *
	 * @since 1.18
	 */
	public static @NonNull EnvironmentFactoryInternal getEnvironmentFactory() {
		ThreadLocalExecutor threadLocalExecutor = get();
		return ClassUtil.requireNonNull(threadLocalExecutor.localBasicGetEnvironmentFactory());
	}

	/**
	 * Return the prevailing thread-unique Executor or null if none/many.
	 *
	public static @NonNull Executor getExecutor() {
		ThreadLocalExecutor threadLocalExecutor = get();
		return ClassUtil.requireNonNull(threadLocalExecutor.localBasicGetExecutor());
	} */

	/**
	 * Register execution of a finalizer that may release some resource that may also need finalization.
	 *
	 * @since 7.0
	 */
	public static int incrementFinalizerReleases() {
		int count = finalizerReleases.incrementAndGet();
	//	System.out.println(ThreadLocalExecutor.getBracketedThreadName() + " incrementFinalizerReleases " + count);
		return count;
	}

	private static @Nullable ThreadLocalExecutor readExtension() {
		IExtensionRegistry extensionRegistry = Platform.getExtensionRegistry();
		if (extensionRegistry == null) {
			return null;
		}
		String maxClassName = null;
		IConfigurationElement maxElement = null;
		IExtensionPoint point = extensionRegistry.getExtensionPoint(PivotPlugin.PLUGIN_ID, PivotPlugin.THREAD_LOCAL_PID);
		if (point != null) {
			for (IConfigurationElement element : point.getConfigurationElements()) {
				String className = null;
				String tagName = element.getName();
				if (TAG_MANAGER.equals(tagName)) {
					className = element.getAttribute(ATT_CLASS);
				}
				if (className != null) {
					if (maxClassName == null) {
						maxClassName = className;
						maxElement = element;
					}
					else if (maxClassName.length() < className.length()) {
						maxClassName = className;
						maxElement = element;
					}
					else if ((maxClassName.length() == className.length()) && (className.compareTo(maxClassName) < 0)) {
						maxClassName = className;
						maxElement = element;
					}
				}
			}
			if (maxElement != null) {
				try {
					return (ThreadLocalExecutor)maxElement.createExecutableExtension(ATT_CLASS);
				} catch (CoreException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
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
	 * Restart the finalizer-has-released count. Returns the pre-rest value.
	 *
	 * @since 7.0
	 */
	public static synchronized int resetFinalizerReleases() {
		int count = finalizerReleases.get();
		finalizerReleases.updateAndGet(new IntUnaryOperator()
		{
			@Override
			public int applyAsInt(int operand) {
				return 0;
			}
		});
	//	System.out.println(ThreadLocalExecutor.getBracketedThreadName() + " resetFinalizerReleases " + count);
		return count;
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
			threadLocalExecutor.localRemoveEnvironmentFactory();
		}
	}

	/**
	 * Set or reset the Executor instance for the current thread. This is returned by basicGetExecutor() provided
	 * only a single EnvironmentFactory instance is active.
	 */
	public static void setExecutor(@Nullable Executor executor) {
		ThreadLocalExecutor threadLocalExecutor = get();
		threadLocalExecutor.localSetExecutor(executor);
	}

	/**
	 * Specify that this (worker) thread uses a finalizer to release its resources.
	 *
	 * @since 1.15
	 */
	public static void setUsesFinalizer() {
		ThreadLocalExecutor threadLocalExecutor = INSTANCE.get();
		if (threadLocalExecutor != null) {
			threadLocalExecutor.usesFinalizer = true;
		}
	}

	public static @NonNull String toDebugString() {
		ThreadLocalExecutor threadLocalExecutor = INSTANCE.get();
		return threadLocalExecutor != null ? threadLocalExecutor.toString() : "*** FINALIZED ***";
	}

	/**
	 * Return true if this (worker) thread uses a finalizer to release its resources.
	 *
	 * @since 1.15
	 */
	public static boolean usesFinalizer() {
		ThreadLocalExecutor threadLocalExecutor = INSTANCE.get();
		return (threadLocalExecutor != null) && threadLocalExecutor.usesFinalizer;
	}

	/**
	 * Perform up to 10 garbage collects followed by 10 millisecond sleep until basicGetEnvironmentFactory
	 * returns false indicating that GC of an EnvironmentFactory held by a WeakOCLReference has succeeded.
	 * Return false if an EnvironmentFactory remains active.
	 */
	@Deprecated /* @deprecated no longer used */
	public static boolean waitForGC() throws InterruptedException {
		ThreadLocalExecutor threadLocalExecutor = INSTANCE.get();
		return (threadLocalExecutor != null) && threadLocalExecutor.localWaitForGC() ;
	}

	/**
	 * Distinctive name for debug purposes, avoiding a GC-hazard.
	 *
	 */
	private final @NonNull String name;

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

	/**
	 * True if the thread application code uses a finalizer to release its resources.
	 * e.g. EMF validation worker thread lazily discovers that it needs OCL and so must resort to
	 * a finalizer to release it.
	 */
	private boolean usesFinalizer = false;

//	protected final @NonNull Thread debugThread;

	public ThreadLocalExecutor() {
		this("[" + Thread.currentThread().getName() + "]");
	}

	/**
	 * @since 1.20
	 */
	protected ThreadLocalExecutor(@NonNull String name) {
		this.name = name;
	//	this.debugThread = Thread.currentThread();
		assert name.contains(Thread.currentThread().getName());
	}

	/**
	 * @since 1.15
	 */
	protected @NonNull ThreadLocalExecutor createInstance() {
		return new ThreadLocalExecutor();
	}

	/**
	 * @since 1.20
	 *
	protected void debugState() {
		StringBuilder s = new StringBuilder();
		debugState(s);
		System.out.println(s.toString());
	} */

	/**
	 * @since 1.20
	 *
	protected void debugState(@NonNull StringBuilder s) {
		s.append("\tthis : " + toString());
	} */

	@Override
	protected void finalize() throws Throwable {
		if (usesFinalizer) {
			if (THREAD_LOCAL_ENVIRONMENT_FACTORY.isActive()) {
				THREAD_LOCAL_ENVIRONMENT_FACTORY.println(getThreadName() + " Finalize " + toString());
			}
			assert environmentFactory != null;
			incrementFinalizerReleases();
			localReset();
		}
		else {
			if (environmentFactory != null) {		// Reset may have set environmentFactory null before finalization
				localReset();
			}
		}
	}

	@Override
	public @NonNull String getName() {
		return name;
	}

	/**
	 * @since 1.15
	 */
	protected @NonNull String getThreadName() {
		return "[" + Thread.currentThread().getName() + "]";
	}

	/**
	 * @since 1.20
	 */
	public void localAttachEnvironmentFactory(@NonNull EnvironmentFactoryInternal newEnvironmentFactory) {
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
			THREAD_LOCAL_ENVIRONMENT_FACTORY.println(getThreadName() + " Attach " + toString());
		}
	//	debugState();
	}

	/**
	 * @since 1.20
	 */
	public @Nullable EnvironmentFactoryInternal localBasicGetEnvironmentFactory() {
		EnvironmentFactoryInternal environmentFactory2 = environmentFactory;
		if (concurrentEnvironmentFactories) {
			assert environmentFactory2 == null;
		}
		return (environmentFactory2 != null) && (environmentFactory2.isDisposing() || !environmentFactory2.isDisposed()) ? environmentFactory2 : null;
	}

	/**
	 * @since 1.20
	 */
	public @Nullable Executor localBasicGetExecutor() {
		if (concurrentEnvironmentFactories) {
			assert executor == null;
		}
		EnvironmentFactoryInternal environmentFactory2 = environmentFactory;
		return (environmentFactory2 == null) || !environmentFactory2.isDisposed() ? executor : null;
	}

	/**
	 * @since 1.20
	 */
	public void localDetachEnvironmentFactory(@NonNull EnvironmentFactory environmentFactory) {
		if (this.environmentFactory == environmentFactory) {
//			localResetEnvironmentFactory();
			if (!concurrentEnvironmentFactories) {
				setEnvironmentFactory(null);
			}
			if (THREAD_LOCAL_ENVIRONMENT_FACTORY.isActive()) {
				THREAD_LOCAL_ENVIRONMENT_FACTORY.println(getThreadName() + " Detach " + toString());
			}
		//	debugState();
		}
	}

	/**
	 * @since 1.20
	 */
	public void localInit(@NonNull ThreadLocalExecutor initPartThread, @NonNull InitWrapperCallBack<?,?> callBack, @NonNull NeedsInit needsInit) {
	//	assert activePart != NOT_A_PART_THREAD;			// First init is from NOT_A_PART_THREAD
		if (needsInit == NeedsInit.ATTACH_FROM_PART_THREAD) {
		//	assert this.activePartThread == this.activatedPartThread;
			EnvironmentFactoryInternal environmentFactory = initPartThread.environmentFactory;
			if (environmentFactory != null) {
				localAttachEnvironmentFactory(environmentFactory);
				try {
					//	System.out.println(getBracketedThreadName() + " activePartThread " + NameUtil.debugSimpleName(activePartThread));
					callBack.run();
				}
				finally {
					localDetachEnvironmentFactory(environmentFactory);
					//	System.out.println(getBracketedThreadName() + " activePartThread " + NameUtil.debugSimpleName(activePartThread));
				}
				return;
			}
		}
	//	else {
		//	assert this.activePartThread == this.activatedPartThread;
			//	System.out.println(getBracketedThreadName() + " activePartThread " + NameUtil.debugSimpleName(activePartThread));
			callBack.run();
		//	System.out.println(getBracketedThreadName() + " activePartThread " + NameUtil.debugSimpleName(activePartThread));
	//	}
	}

	/**
	 * @since 1.20
	 */
	public void localRemoveEnvironmentFactory() {
		if (!concurrentEnvironmentFactories) {
			setEnvironmentFactory(null);
		}
		if (THREAD_LOCAL_ENVIRONMENT_FACTORY.isActive()) {
			THREAD_LOCAL_ENVIRONMENT_FACTORY.println(getThreadName() + " Remove " + toString());
		}
	//	debugState();
	}

	/**
	 * @since 1.20
	 */
	public synchronized void localReset() {
		setEnvironmentFactory(null);
		executor = null;
		concurrentEnvironmentFactories = false;
		usesFinalizer = false;
		if (THREAD_LOCAL_ENVIRONMENT_FACTORY.isActive()) {
			THREAD_LOCAL_ENVIRONMENT_FACTORY.println(getThreadName() + " Reset " + toString());
		}
	//	debugState();
	}

	/**
	 * @since 1.20
	 */
	public void localSetExecutor(@Nullable Executor executor) {
		if (executor != null) {
			if (!concurrentEnvironmentFactories) {
				this.executor = executor;
			}
			if (executor instanceof PivotExecutorManager) {
				localAttachEnvironmentFactory((EnvironmentFactoryInternal) ((PivotExecutorManager)executor).getEnvironmentFactory());
			}
			if (THREAD_LOCAL_ENVIRONMENT_FACTORY.isActive()) {
				THREAD_LOCAL_ENVIRONMENT_FACTORY.println(getThreadName() + " Set " + toString());
			}
		}
		else {
			this.executor = null;
			if (THREAD_LOCAL_ENVIRONMENT_FACTORY.isActive()) {
				THREAD_LOCAL_ENVIRONMENT_FACTORY.println(getThreadName() + " Reset " + toString());
			}
		}
	//	debugState();
	}

	/**
	 * @since 1.20
	 */
	public void setEnvironmentFactory(@Nullable EnvironmentFactoryInternal newEnvironmentFactory) {
		EnvironmentFactoryInternal oldEnvironmentFactory = this.environmentFactory;
		if (newEnvironmentFactory != oldEnvironmentFactory) {
			if (oldEnvironmentFactory != null) {
				if (!oldEnvironmentFactory.isDisposed()) {
					oldEnvironmentFactory.detach(this);
					this.environmentFactory = null;
					if (usesFinalizer) {
					//	System.out.println(getThreadName() + " setEnvironmentFactory() gc()");
						this.executor = null;
						System.gc();
						usesFinalizer = false;
					}
				}
				else {
					this.environmentFactory = null;
				}
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
			StringBuilder s = new StringBuilder();
			s.append(name);
			s.append(" ");
			if (environmentFactory != null) {
				s.append(((AbstractEnvironmentFactory)environmentFactory).toDebugString());
			}
			else {
				s.append("no-environmentFactory");
			}
			s.append(" ");
			Executor executor = this.executor;
			if (executor != null) {
				s.append(NameUtil.debugSimpleName(executor));
				ExecutorInternal interpretedExecutor = executor.basicGetInterpretedExecutor();
				if ((interpretedExecutor != null) && (interpretedExecutor != executor)) {
					s.append("+");
					s.append(NameUtil.debugSimpleName(interpretedExecutor));
				}
			}
			else {
				s.append("no-executor");
			}
			return s.toString();
		}
		else {
			return "**** CONCURRENT ENVIRONMENT FACTORIES ****";
		}
	}

	@Deprecated /* @deprecated no longer used */
	public boolean localWaitForGC() throws InterruptedException {
		if (concurrentEnvironmentFactories) {
			return false;
		}
		EnvironmentFactoryInternal environmentFactory2 = environmentFactory;
		if (environmentFactory2 == null) {
			return true;
		}
		for (int i = 0; i < 10; i++) {
			ThreadLocalExecutor.detachEnvironmentFactory(environmentFactory2);
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