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

import org.eclipse.jdt.annotation.NonNull;

public class DataTypeRuleValue extends AbstractRuleValue
{
	public DataTypeRuleValue(int index, @NonNull String name) {
		super(index, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof DataTypeRuleValue)) {
			return false;
		}
		DataTypeRuleValue that = (DataTypeRuleValue)obj;
		return this.name.equals(that.name);
	}
}