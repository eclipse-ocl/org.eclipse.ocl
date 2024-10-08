/*******************************************************************************
 * Copyright (c) 2006, 2024 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.internal.l10n;

import java.text.MessageFormat;

/**
 * Abstraction of the OSGi runtime's NLS class, to abstract the dependency on
 * Eclipse platform.
 *
 * @author Christian W. Damus (cdamus)
 */
interface IMessages {
	String bind(String message, Object arg);
	String bind(String message, Object arg1, Object arg2);
	String bind(String message, Object[] args);
	
	class Default implements IMessages {
		@Override
		public String bind(String message, Object arg) {
			return MessageFormat.format(message, new Object[] {arg});
		}

		@Override
		public String bind(String message, Object arg1, Object arg2) {
			return MessageFormat.format(message, new Object[] {arg1, arg2});
		}

		@Override
		public String bind(String message, Object[] args) {
			return MessageFormat.format(message, args);
		}
	}
	
	class NLS implements IMessages {
		public NLS() {
			org.eclipse.osgi.util.NLS.initializeMessages(
					OCLMessages.BUNDLE_NAME, OCLMessages.class);
		}

		@Override
		public String bind(String message, Object arg) {
			return org.eclipse.osgi.util.NLS.bind(message, arg);
		}

		@Override
		public String bind(String message, Object arg1, Object arg2) {
			return org.eclipse.osgi.util.NLS.bind(message, arg1, arg2);
		}

		@Override
		public String bind(String message, Object[] args) {
			return org.eclipse.osgi.util.NLS.bind(message, args);
		}
	}
}
