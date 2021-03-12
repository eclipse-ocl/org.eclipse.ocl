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

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal;
import org.eclipse.ocl.pivot.internal.utilities.OCLInternal;

/**
 * An OCLThread is a thread that has a unique OCL working state associated with the lifetime of the thread
 * which may be synchronsly executed from some control thread.
 *
 * @since 1.14
 */
public abstract class OCLThread<R, O extends OCLInternal> extends Thread
{
	public static interface EnvironmentThreadFactory
	{
		public abstract @NonNull OCLInternal createEnvironment();
	}

	public static class Resumable<R>
	{
		protected final @NonNull OCLThread<R, ?> oclThread;
		protected final R result;
		private boolean resumed = false;

		private Resumable(@NonNull OCLThread<R, ?> oclThread, R result) {
			this.oclThread = oclThread;
			this.result = result;
		}

		@Override
		protected void finalize() {
			if (!resumed) {
				System.err.println("Thread '" + oclThread.getName() + "' was not resumed");
			}
		}

		public @NonNull EnvironmentFactoryInternal getEnvironmentFactory() {
			return oclThread.getOCL().getEnvironmentFactory();
		}

		public R getResult() {
			return result;
		}

		/**
		 * Resume execution of the OCL thread.
		 */
		public void syncResume() {
			resumed = true;
			synchronized (oclThread) {
				oclThread.notify();			// Return control to the thread.
			}
		}
	}

	protected /*@NonNull*/ O ocl;
	protected Exception exception = null;
	protected R result;
	private boolean started = false;

	@SuppressWarnings("null")
	public OCLThread(@NonNull String oclThreadName) {
		super(oclThreadName);
	}

	/**
	 * Create the OCL for use on the OCL thread.
	 */
	protected abstract O createOCL() throws ParserException;

	@Override
	protected void finalize() {
		if (!started) {
			System.err.println("Thread '" + getName() + "' was not started");
		}
	}

	protected @NonNull O getOCL() {
		assert ocl != null;
		return ocl;
	}

	@Override
	public final void run() {
		try {
			ocl = createOCL();				// Create the OCL working state on the OCL thread.
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

	/**
	 * Do the work using the created OCL.
	 */
	protected abstract R runWithThrowable() throws Exception;

	/**
	 * Execute the OCL thread work load on its distinct thread, returning a possible value or
	 * throwing a possible failure.
	 */
	public R syncExec() throws Exception {
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

	/**
	 * Start the first phase of execution of the OCLthread. Returns a Resummable that may be used to
	 * obtain the first phase result and resume the second phase.
	 */
	public @NonNull Resumable<R> syncStart() throws Exception {
		R result = syncExec();
		return new Resumable<>(this, result);
	}

	/**
	 * Assign the result value and suspend execution allowing the caller to continue without working resources
	 * being released. Normal execution continues when the caller invoks syncResume().
	 */
	protected void syncSuspend(R result) {
		this.result = result;
		synchronized (this) {
			try {
				this.notify();			// Return control to the invoker.
				wait(1000000);			// Needlessly long wait to avoid confusing debug session
			} catch (InterruptedException e) { /* Don't care */ }
		}
	}
}