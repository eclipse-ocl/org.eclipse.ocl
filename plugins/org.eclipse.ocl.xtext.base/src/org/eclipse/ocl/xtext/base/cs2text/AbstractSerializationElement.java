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
package org.eclipse.ocl.xtext.base.cs2text;

import org.eclipse.jdt.annotation.NonNull;

public abstract class AbstractSerializationElement implements SerializationElement
{
	@Override
	public @NonNull ListOfSerializationNode asList() {
		throw new IllegalStateException();
	}

	@Override
	public @NonNull ListOfListOfSerializationNode asListOfList() {
		throw new IllegalStateException();
	}

	@Override
	public @NonNull SerializationNode asNode() {
		throw new IllegalStateException();
	}


	@Override
	public boolean isList() {
		return false;
	}

	@Override
	public boolean isListOfList() {
		return false;
	}

	@Override
	public boolean isNode() {
		return false;
	}

	@Override
	public boolean isNull() {
		return false;
	}

//	@Override
	public void serialize(@NonNull Serializer serializer, @NonNull SerializationBuilder serializationBuilder) {
		serializationBuilder.append("«Unsupported serialize '" + getClass().getSimpleName() + "'»");
	}

	@Override
	public @NonNull String toString() {
		StringBuilder s = new StringBuilder();
		toString(s, 0);
		return s.toString();
	}
}