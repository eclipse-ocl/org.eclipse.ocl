/*******************************************************************************
 * Copyright (c) 2023 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.internal.TypeImpl;
import org.eclipse.ocl.pivot.internal.complete.ClassListeners;

public abstract class AbstractClass extends TypeImpl implements org.eclipse.ocl.pivot.Class
{
	protected @Nullable ClassListeners<ClassListeners.IClassListener> classListeners = null;

	@Override
	public synchronized void addClassListener(ClassListeners.@NonNull IClassListener classListener) {
		ClassListeners<ClassListeners.IClassListener> classListeners2 = classListeners;
		if (classListeners2 == null) {
			classListeners2 = classListeners = new ClassListeners<>();
		}
		classListeners2.addListener(classListener);
	}

	@Override
	public synchronized void removeClassListener(ClassListeners.@NonNull IClassListener classListener) {
		ClassListeners<ClassListeners.IClassListener> classListeners2 = classListeners;
		if ((classListeners2 != null) && classListeners2.removeListener(classListener)) {
			classListeners = null;
		}
	}
}
