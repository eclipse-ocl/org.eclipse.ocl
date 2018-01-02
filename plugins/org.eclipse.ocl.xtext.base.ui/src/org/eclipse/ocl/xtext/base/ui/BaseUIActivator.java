/*******************************************************************************
 * Copyright (c) 2018 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.xtext.base.ui;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.xtext.base.ui.builder.MultiValidationJob;
import org.eclipse.ocl.xtext.base.ui.internal.BaseActivator;
import org.osgi.framework.BundleContext;

public class BaseUIActivator extends BaseActivator
{
	private static @Nullable MultiValidationJob multiValidationJob = null;

	public static synchronized @NonNull MultiValidationJob getMultiValidationJob() {
		MultiValidationJob multiValidationJob2 = multiValidationJob;
		if (multiValidationJob2 == null) {
			multiValidationJob = multiValidationJob2 = new MultiValidationJob();
		}
		return multiValidationJob2;
	}

	public BaseUIActivator() {
		super();
	}


	@Override
	public void stop(BundleContext context) throws Exception {
		super.stop(context);
		MultiValidationJob multiValidationJob2 = multiValidationJob;
		if (multiValidationJob2 != null) {
			multiValidationJob = null;
			multiValidationJob2.cancel();
		}
	}
}
