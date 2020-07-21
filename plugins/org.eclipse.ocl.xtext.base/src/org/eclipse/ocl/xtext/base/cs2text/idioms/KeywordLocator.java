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

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.xtext.base.cs2text.elements.AssignedKeywordSerializationNode;
import org.eclipse.ocl.xtext.base.cs2text.elements.SerializationNode;
import org.eclipse.ocl.xtext.base.cs2text.elements.UnassignedKeywordSerializationNode;
import org.eclipse.xtext.util.Strings;

public class KeywordLocator implements Locator
{
	protected final @NonNull String string;

	public KeywordLocator(@NonNull String string) {
		this.string = string;
	}

	@Override
	public boolean matches(@NonNull SerializationNode serializationNode) {
		if (serializationNode instanceof AssignedKeywordSerializationNode) {
			return string.equals(((AssignedKeywordSerializationNode)serializationNode).getValue());
		}
		else if (serializationNode instanceof UnassignedKeywordSerializationNode) {
			return string.equals(((UnassignedKeywordSerializationNode)serializationNode).getValue());
		}
		return false;
	}

	@Override
	public String toString() {
		return "\"" + Strings.convertToJavaString(string) + "\"";
	}
}
