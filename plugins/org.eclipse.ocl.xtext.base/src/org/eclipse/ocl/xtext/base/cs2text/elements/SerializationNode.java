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

import java.util.Stack;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.xtext.base.cs2text.SerializationBuilder;
import org.eclipse.ocl.xtext.base.cs2text.Serializer;

public interface SerializationNode extends SerializationElement
{
	/**
	 * Ceate a shallow copy of this node with a changed multiplicativeCardinality.
	 */
	@NonNull SerializationNode clone(@NonNull MultiplicativeCardinality multiplicativeCardinality);
	void preSerialize(@NonNull BasicSerializationRule serializationRule, @NonNull Stack<@NonNull SerializationNode> parentStack);
	void serialize(@NonNull Serializer serializer, @NonNull SerializationBuilder serializationBuilder);
}