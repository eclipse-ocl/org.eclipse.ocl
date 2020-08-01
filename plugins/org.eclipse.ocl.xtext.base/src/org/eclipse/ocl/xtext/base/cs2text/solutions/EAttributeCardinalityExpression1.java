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
package org.eclipse.ocl.xtext.base.cs2text.solutions;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.xtext.base.cs2text.enumerations.EnumerationValue;

/**
 * A CardinalityExpression eqates the sum of CardinailtyVariable products to the number of elemets in an eStrucuralFeature slot.
 *
 * Multiple CardinalityExpressions provide a set of simultaneous equations for which an integer solution mmust be found to
 * select a potential serialization option.
 */
public class EAttributeCardinalityExpression1 extends EAttributeCardinalityExpression
{
	public EAttributeCardinalityExpression1(@NonNull String name, @NonNull EAttribute eAttribute, @NonNull EnumerationValue enumerationValue) {
		super(name, eAttribute, enumerationValue);
		assert !enumerationValue.isNull();
	}
}