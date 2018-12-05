/*******************************************************************************
 * Copyright (c) 2015, 2018 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.common.internal.delegate;

import org.eclipse.emf.common.util.WrappedException;

/**
 * An OCLDelegateException wraps an exception that explains a problem that occurred during delegated evaluation.
 * <p>
 * For simple problems this class was once used directly rather than as a wrapper requiring the handler to
 * determine whether a direct or wrapped exception was involved. It is intended that this should only be a wrapper
 * consequently constructors permitting a non-null message are deprecated.
 * <p>
 * Code supporting the Classic LPG evaluator must be prepared to handle unwrapped exceptions.
 */
public class OCLDelegateException extends WrappedException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

//	@Deprecated but still in use by Classic LPG delegation
	public OCLDelegateException(String message) {
		super(message, null);
	}

//	@Deprecated but still in use by Classic LPG delegation
	public OCLDelegateException(String message, Exception cause) {
		super(message, cause);
	}

	public OCLDelegateException(/*@NonNull*/ Exception cause) {
		super(null, cause);
	}
}
