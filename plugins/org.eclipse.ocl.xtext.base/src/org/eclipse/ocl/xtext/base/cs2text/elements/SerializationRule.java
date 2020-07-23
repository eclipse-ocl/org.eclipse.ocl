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
package org.eclipse.ocl.xtext.base.cs2text.elements;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.utilities.Nameable;

public interface SerializationRule extends Nameable
{
	@NonNull BasicSerializationRule getBasicSerializationRule();
	@Override
	@NonNull String getName();
	@NonNull EClass getProducedEClass();
	@NonNull SerializationNode getRootSerializationNode();
	void toRuleString(@NonNull StringBuilder s);
	void toString(@NonNull StringBuilder s, int depth);
}