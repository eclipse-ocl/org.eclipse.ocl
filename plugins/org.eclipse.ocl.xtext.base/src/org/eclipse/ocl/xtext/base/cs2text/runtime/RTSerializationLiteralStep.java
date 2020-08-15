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
package org.eclipse.ocl.xtext.base.cs2text.runtime;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.xtext.base.cs2text.SerializationBuilder;
import org.eclipse.ocl.xtext.base.cs2text.user.UserElementSerializer;
import org.eclipse.xtext.util.Strings;

public class RTSerializationLiteralStep extends RTAbstractSerializationStep
{
	protected final @NonNull String string;

	public RTSerializationLiteralStep(int variableIndex,  @NonNull String string) {
		super(variableIndex);
		this.string = string;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof RTSerializationLiteralStep)) {
			return false;
		}
		return equalTo((RTSerializationLiteralStep)obj);
	}

	protected boolean equalTo(@NonNull RTSerializationLiteralStep that) {
		return super.equalTo(that) && this.string.equals(that.string);
	}

	public @NonNull String getString() {
		return string;
	}

	@Override
	public int hashCode() {
		return super.hashCode() + 5 * string.hashCode();
	}

	@Override
	public void serialize(@NonNull UserElementSerializer serializer, @NonNull SerializationBuilder serializationBuilder) {
		serializationBuilder.append(string);
	}

	@Override
	public void toString(@NonNull StringBuilder s, int depth) {
		super.toString(s, depth);
		s.append("'");
		s.append(Strings.convertToJavaString(string));
		s.append("'");
	}
}