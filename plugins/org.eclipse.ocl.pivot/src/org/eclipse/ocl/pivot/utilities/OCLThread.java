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
import org.eclipse.ocl.pivot.internal.utilities.OCLInternal;

/**
 * An OCLThread is a thread that has a unique OCL working state associated with the lifetime of the thread
 * which may be synchronsly executed from some control thread.
 *
 * @since 1.14
 */
public abstract class OCLThread<R> extends Thread
{
	protected /*@NonNull*/ OCLInternal ocl;
	protected Throwable throwable = null;
	protected R result;

	@SuppressWarnings("null")
	public OCLThread(@NonNull String oclThreadName) {
		super(oclThreadName);
	}

	/**
	 * Create the OCL for use on the OCL thread.
	 */
	protected abstract @NonNull OCLInternal createOCL() throws ParserException;

	@Override
	public final void run() {
		try {
			ocl = createOCL();				// Create the OCL working state on the OCL thread.
											// - side effect: ThreadLocalExecutor attaches the EnvironmentFactory
			result = runWithThrowable();	// Use the OCL on the OCL thread
		}
		catch (Throwable t) {
			throwable = t;
		}
		finally {
			try {
				ocl.dispose();				// Release the no longer required OCL working state.
											// - side effect: ThreadLocalExecutor detaches the EnvironmentFactory
				ocl = null;
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
	protected abstract R runWithThrowable() throws Throwable;

	/**
	 * Execute the OCL thread work load on its distinct thread, returning a possible value or
	 * thriwing a possible failure.
	 */
	public R syncExec() throws Throwable {
		start();
		synchronized (this) {
			try {
				wait(1000000);				// Needlessly long wait to avoid confusing debug session
			} catch (InterruptedException e) {
				// Don't care -- e.printStackTrace();
			}
		//	if (DEBUG_GC) {
		//		System.gc();
		//		System.runFinalization();
		//	}
		}
		if (throwable != null) {
			throw throwable;
		}
		return result;
	}
}