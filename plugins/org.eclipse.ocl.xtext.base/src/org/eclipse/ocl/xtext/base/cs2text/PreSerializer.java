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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;

public class PreSerializer
{
//	protected final @NonNull SerializationNode serializationNode;
	private final @Nullable Map<@NonNull AlternativesSerializationNode, @Nullable SerializationNode> alternatives2choice;
	private final @NonNull List<@NonNull SerializationNode> serializedNodes = new ArrayList<>();

	public PreSerializer(/*@NonNull SerializationNode serializationNode,*/ @Nullable Map<@NonNull AlternativesSerializationNode, @Nullable SerializationNode> alternatives2choice) {
//		this.serializationNode = serializationNode;
		this.alternatives2choice = alternatives2choice;
	}

	public void addSerializedNode(@NonNull SerializationNode serializationNode) {
		serializedNodes.add(serializationNode);
	}

	public @NonNull PreSerializer createNestedPreSerializer(/*@NonNull SequenceSerializationNode sequenceSerializationNode*/) {
		return new PreSerializer(/*sequenceSerializationNode,*/ alternatives2choice);
	}

	public @Nullable SerializationNode getChosenNode(@NonNull AlternativesSerializationNode alternativesSerializationNode) {
		assert alternatives2choice != null;
		return alternatives2choice.get(alternativesSerializationNode);
	}

	public @NonNull List<@NonNull SerializationNode> getSerializedNodes() {
		return serializedNodes;
	}
}
