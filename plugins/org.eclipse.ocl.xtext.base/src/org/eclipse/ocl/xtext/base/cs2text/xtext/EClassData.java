/*******************************************************************************
 * Copyright (c) 2020 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.xtext.base.cs2text.xtext;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.utilities.Nameable;
import org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule;

public class EClassData implements Nameable
{
	protected final @NonNull String name;
	protected final @NonNull EClass eClass;
	protected final @NonNull RTSerializationRule @NonNull[] rtSerializationRules;

	public EClassData(@NonNull String name, /*@NonNull*/ EClass eClass, @NonNull RTSerializationRule @NonNull[] rtSerializationRules) {
		assert eClass != null;
		this.name = name;
		this.eClass = eClass;
		this.rtSerializationRules = rtSerializationRules;
	}

	@Override
	public @NonNull String getName() {
		return name;
	}

	@Override
	public @NonNull String toString() {
		return name;
	}
}