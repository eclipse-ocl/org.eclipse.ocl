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
package org.eclipse.ocl.xtext.base.cs2text.idioms;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.xtext.base.cs2text.elements.BasicSerializationRule;
import org.eclipse.ocl.xtext.base.cs2text.elements.SerializationNode;

public class ProducedEClassLocator implements Locator
{
	protected final @NonNull EClass eClass;

	public ProducedEClassLocator(@NonNull EClass eClass) {
		this.eClass = eClass;
	}

	@Override
	public boolean matches(@NonNull SerializationNode serializationNode, @NonNull BasicSerializationRule serializationRule) {
		if ((serializationNode == serializationRule.getRootSerializationNode()) && (eClass.isSuperTypeOf(serializationRule.getProducedEClass()))) {
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return eClass.getEPackage().getName() + "::" + eClass.getName();
	}
}