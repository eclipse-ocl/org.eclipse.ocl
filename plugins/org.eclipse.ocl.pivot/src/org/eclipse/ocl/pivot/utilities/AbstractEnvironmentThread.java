/*******************************************************************************
 * Copyright (c) 2021 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.utilities;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.PivotPackage;
import org.eclipse.ocl.pivot.internal.resource.StandaloneProjectMap;
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal;
import org.eclipse.ocl.pivot.internal.utilities.OCLInternal;
import org.eclipse.ocl.pivot.internal.utilities.PivotUtilInternal;
import org.eclipse.ocl.pivot.oclstdlib.OCLstdlibPackage;
import org.eclipse.ocl.pivot.resource.ProjectManager;
import org.eclipse.ocl.pivot.resource.ProjectManager.IPackageDescriptor;

/**
 * An OCLThread is a thread that has a unique OCL working state associated with the lifetime of the thread
 * which may be synchronsly executed from some control thread.
 *
 * @since 1.15
 */
public abstract class AbstractEnvironmentThread<R, @NonNull EF extends EnvironmentFactoryInternal, O extends OCLInternal> extends Thread
{
	/**
	 * EnvironmentThreadFactory transports the construction requirements from an invoking context to
	 * the EnvironmentThread on which the OCL-based processing is performed.
	 */
	public static interface EnvironmentThreadFactory<@NonNull EF extends EnvironmentFactoryInternal>
	{
		@NonNull OCLInternal createEnvironment(@NonNull EF environmentFactory);
		@NonNull EF createEnvironmentFactory();
		@NonNull ProjectManager getProjectManager();
	}

	public static abstract class AbstractEnvironmentThreadFactory<@NonNull EF extends EnvironmentFactoryInternal> implements EnvironmentThreadFactory<EF>
	{
		protected final @NonNull ProjectManager projectManager;

		protected AbstractEnvironmentThreadFactory(@NonNull ProjectManager projectManager) {
			this.projectManager = projectManager;
		}

		@Override
		public @NonNull ProjectManager getProjectManager() {
			return projectManager;
		}
	}

	public static interface EnvironmentThreadRunnable<R> extends Runnable
	{
		@NonNull EnvironmentFactoryInternal getEnvironmentFactory();
	//	R runWithModel(@NonNull ResourceSet resourceSet) throws Exception;
		R runWithThrowable() throws Exception;
		void syncSuspend(R result);
	}

	public static abstract class AbstractEnvironmentThreadRunnable<R> implements EnvironmentThreadRunnable<R>
	{
		protected @Nullable AbstractEnvironmentThread<R, ?, ?> environmentThread = null;

		@Override
		public @NonNull EnvironmentFactoryInternal getEnvironmentFactory() {
			assert environmentThread != null;
			return environmentThread.getEnvironmentFactory();
		}

		public @NonNull AbstractEnvironmentThread<R, ?, ?> getEnvironmentThread() {
			assert environmentThread != null;
			return environmentThread;
		}

		public @NonNull MetamodelManager getMetamodelManager() {
			return getEnvironmentFactory().getMetamodelManager();
		}

		public @NonNull ProjectManager getProjectManager() {
			assert environmentThread != null;
			return environmentThread.getProjectManager();
		}

		public @NonNull ResourceSet getResourceSet() {
			return getEnvironmentFactory().getResourceSet();
		}

		public void init(@NonNull AbstractEnvironmentThread<R, ?, ?> environmentThread) {
			this.environmentThread = environmentThread;
		}

		@Override
		public void run() {
			assert environmentThread != null;
			EnvironmentFactoryInternal environmentFactory = environmentThread.getEnvironmentFactory();
			environmentFactory.attach(this);
			try {
				environmentThread.result = runWithThrowable();
			}
			catch (Exception e) {
				environmentThread.exception = e;
			}
			catch (Throwable e) {
				environmentThread.exception = new RuntimeException(e);
			}
			synchronized (this) {
				environmentFactory.detach(this);
			//	unloadResourceSet(resourceSet);
				this.notify();			// Return control to the invoker.
			}
		}

		@Override
		public void syncSuspend(R result) {
			getEnvironmentThread().syncSuspend(result);
		}
	}

	public static abstract class AbstractEagerEnvironmentThreadRunnable<R> extends AbstractEnvironmentThreadRunnable<R>
	{
		@Override
		public @NonNull EnvironmentFactoryInternal getEnvironmentFactory() {
			assert environmentThread != null;
			return environmentThread.getEnvironmentFactory();
		}

		@Override
		public void run() {
			assert environmentThread != null;
			EnvironmentFactoryInternal environmentFactory = environmentThread.getEnvironmentFactory();
			environmentFactory.attach(this);
			try {
				environmentThread.result = runWithThrowable();
			}
			catch (Exception e) {
				environmentThread.exception = e;
			}
			catch (Throwable e) {
				environmentThread.exception = new RuntimeException(e);
			}
			synchronized (this) {
				environmentFactory.detach(this);
			//	unloadResourceSet(resourceSet);
				this.notify();			// Return control to the invoker.
			}
		}
	}

	public static abstract class AbstractLazyEnvironmentThreadRunnable<R> extends AbstractEnvironmentThreadRunnable<R>
	{
		@Override
		public @NonNull EnvironmentFactoryInternal getEnvironmentFactory() {
			return PivotUtilInternal.getEnvironmentFactory(null);
		}

		@Override
		public void run() {
			assert environmentThread != null;
			try {
				environmentThread.result = runWithThrowable();
			}
			catch (Exception e) {
				environmentThread.exception = e;
			}
			catch (Throwable e) {
				environmentThread.exception = new RuntimeException(e);
			}
			synchronized (this) {
			//	environmentFactory.detach(this);
			//	unloadResourceSet(resourceSet);
				this.notify();			// Return control to the invoker.
			}
		}
	}

	public static class EnvironmentThreadResult<R, @NonNull EF extends EnvironmentFactoryInternal>
	{
		protected final @NonNull AbstractEnvironmentThread<R, EF, ?> environmentThread;
		protected final R result;
		private boolean resumed = false;

		protected EnvironmentThreadResult(@NonNull AbstractEnvironmentThread<R, EF, ?> environmentThread, R result) {
			this.environmentThread = environmentThread;
			this.result = result;
		}

		/**
		 * Resume execution ebling the Environment Thread to release its resurces
		 */
		public void dispose() {
			resumed = true;
			synchronized (environmentThread) {
				environmentThread.notify();			// Return control to the thread.
			}
		}

		@Override
		protected void finalize() {
			if (!resumed) {
				System.err.println("Thread '" + environmentThread.getName() + "' was not resumed");
			}
		}

		public @NonNull EF getEnvironmentFactory() {
			return environmentThread.getEnvironmentFactory();
		}

		public R getResult() {
			return result;
		}

		public @NonNull AbstractEnvironmentThread<R, EF, ?> getThread() {
			return environmentThread;
		}
	}

	protected final @NonNull EnvironmentThreadFactory<EF> environmentThreadFactory;
	protected @Nullable AbstractEnvironmentThreadRunnable<R> runnable = null;
	protected /*@NonNull*/ @Nullable EF environmentFactory = null;
	protected /*@NonNull*/ O ocl;
	protected Exception exception = null;
	protected R result;
	private boolean started = false;
	private boolean suspended = false;

	@SuppressWarnings("null")
	protected AbstractEnvironmentThread(@NonNull EnvironmentThreadFactory<@NonNull EF> environmentThreadFactory, @NonNull String threadName) {
		super(threadName);
		this.environmentThreadFactory = environmentThreadFactory;
	}

	protected AbstractEnvironmentThread(@NonNull AbstractEnvironmentThreadRunnable<R> runnable, @NonNull String threadName, @NonNull EnvironmentThreadFactory<EF> environmentThreadFactory) {
		this(environmentThreadFactory, threadName);
		this.runnable = runnable;
		runnable.init(this);
	}

	protected void configureGeneratedPackage(@NonNull ResourceSet resourceSet, /*@NonNull*/ String uriString) {
		URI nsURI = URI.createURI(uriString);
		IPackageDescriptor packageDescriptor = getProjectManager().getPackageDescriptor(nsURI);
		if (packageDescriptor != null) {
			packageDescriptor.configure(resourceSet, StandaloneProjectMap.LoadGeneratedPackageStrategy.INSTANCE, null);
		}
	}

	protected void configureGeneratedPackages(@NonNull EnvironmentFactory environmentFactory) {
		ResourceSet resourceSet = environmentFactory.getResourceSet();
		configureGeneratedPackage(resourceSet, EcorePackage.eNS_URI);
		configureGeneratedPackage(resourceSet, PivotPackage.eNS_URI);
		configureGeneratedPackage(resourceSet, OCLstdlibPackage.eNS_URI);
	}

	/**
	 * Create the EnvironmentFactory for use on the OCL Environment thread.
	 */
	protected /*abstract*/ EF createEnvironmentFactory() {
		return environmentThreadFactory.createEnvironmentFactory();
	}

	/**
	 * Create the OCL for use on the OCL thread.
	 */
	protected /*abstract*/ O createOCL(@NonNull EF environmentFactory) {
		return (O) environmentThreadFactory.createEnvironment(environmentFactory);
	//	throw new UnsupportedOperationException();		// XXX
	}

	/**
	 * Create the OCL for use on the OCL thread.
	 */
	protected @Nullable O createOCL() {
		EF environmentFactory = getEnvironmentFactory();
		return createOCL(environmentFactory);
	}

	@Override
	protected void finalize() {
		if (!started) {
			System.err.println("Thread '" + getName() + "' was not started");
		}
	}

	public @NonNull EF getEnvironmentFactory() {
		@Nullable EF environmentFactory2 = environmentFactory;
		if (environmentFactory2 == null) {
			environmentFactory = environmentFactory2 = createEnvironmentFactory();
		}
		return environmentFactory2;
	}

	public @NonNull EnvironmentThreadFactory<@NonNull EF> getEnvironmentThreadFactory() {
		return environmentThreadFactory;
	}

	protected @NonNull /*Pivot*/MetamodelManager getMetamodelManager() {
		return getEnvironmentFactory().getMetamodelManager();
	}

	public @NonNull ProjectManager getProjectManager() {
		return environmentThreadFactory.getProjectManager();
	}

	/**
	 * Execute the Environment thread work load on its distinct thread, returning a possible value or
	 * throwing a possible failure.
	 */
	public R invoke() throws Exception {
		started = true;
		start();
		synchronized (this) {
			try {
				wait(1000000);				// Needlessly long wait to avoid confusing debug session
			} catch (InterruptedException e) { /* Don't care */ }
		}
		if (exception != null) {
			throw exception;
		}
		return result;
	}

	public boolean isSuspended() {
		return suspended;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void run() {
		if (runnable != null) {
			runnable.run();
			synchronized (this) {
			//	unloadResourceSet(resourceSet);
				this.notify();			// Return control to the invoker.
			}
		}
		else {
			try {
				ocl = createOCL();				// Create the OCL working state on the OCL thread.
				if (ocl != null) {
					environmentFactory = (EF)ocl.getEnvironmentFactory();
				}
				// - side effect: ThreadLocalExecutor attaches the EnvironmentFactory
				result = runWithThrowable();	// Use the OCL on the OCL thread
			}
			catch (Exception e) {
				exception = e;
			}
			catch (Throwable e) {
				exception = new RuntimeException(e);
			}
			finally {
				try {
					assert ocl != null;
					ocl.dispose();				// Release the no longer required OCL working state.
												// - side effect: ThreadLocalExecutor detaches the EnvironmentFactory
					//	ocl = null;
				}
				finally {
					synchronized (this) {
						this.notify();			// Return control to the invoker.
					}
				}
			}
		}
	}

	/**
	 * Do the work using the created OCL.
	 */
	protected abstract R runWithThrowable() throws Exception;

	/**
	 * Start the first phase of execution of the OCLthread. Returns a Resummable that may be used to
	 * obtain the first phase result and resume the second phase.
	 */
	public @NonNull EnvironmentThreadResult<R, EF> syncStart() throws Exception {
		R result = invoke();
		return new EnvironmentThreadResult<>(this, result);
	}

	/**
	 * Assign the result value and suspend execution allowing the caller to continue without working resources
	 * being released. Normal execution continues when the caller invoks syncResume().
	 */
	public void syncSuspend(R result) {
		this.result = result;
		this.suspended  = true;
		synchronized (this) {
			try {
				this.notify();			// Return control to the invoker.
				wait(1000000);			// Needlessly long wait to avoid confusing debug session
			} catch (InterruptedException e) { /* Don't care */ }
		}
		this.suspended = false;
	}
}