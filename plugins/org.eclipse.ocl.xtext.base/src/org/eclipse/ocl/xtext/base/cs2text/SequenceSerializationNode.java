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

import java.util.List;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.utilities.StringUtil;
import org.eclipse.xtext.CompoundElement;

public class SequenceSerializationNode extends CompositeSerializationNode
{
	protected final @NonNull CompoundElement compoundElement;
	protected final @NonNull List<@NonNull SerializationNode> serializationNodes;

	public SequenceSerializationNode(@NonNull XtextParserRuleAnalysis ruleAnalysis, @NonNull CompoundElement compoundElement, @NonNull MultiplicativeCardinality multiplicativeCardinality, @NonNull List<@NonNull SerializationNode> groupSerializationNodes) {
		super(ruleAnalysis, multiplicativeCardinality);
		this.compoundElement = compoundElement;
		this.serializationNodes = groupSerializationNodes;
	}

	@Override
	public @NonNull SerializationNode clone(@NonNull MultiplicativeCardinality multiplicativeCardinality) {
		return new SequenceSerializationNode(ruleAnalysis, compoundElement, multiplicativeCardinality, serializationNodes);
	}

	public @NonNull List<@NonNull SerializationNode> getSerializationNodes() {
		return serializationNodes;
	}

	@Override
	public void preSerialize(@NonNull PreSerializer preSerializer) {
//		super.preSerialize(preSerializer);
		PreSerializer nestedPreSerializer = preSerializer.createNestedPreSerializer(this);
		for (@NonNull SerializationNode serializationNode : serializationNodes) {
			serializationNode.preSerialize(nestedPreSerializer);
		}
	/*	List<@NonNull SerializationNode> nestedSerializedNodes = nestedPreSerializer.getSerializedNodes();
		SequenceSerializationNode nestedSequenceSerializationNode = new SequenceSerializationNode(grammarAnalysis, group, nestedSerializedNodes)
		{
			@Override
			public void toString(@NonNull StringBuilder s, int depth) {
			//	StringUtil.appendIndentation(s, depth, "\t");
				s.append("{");
			//	boolean isFirst = true;
				for (@NonNull SerializationNode serializationNode : serializationNodes) {
				//	if (!isFirst) {
						s.append(" ");
				//	}
					serializationNode.toString(s, depth+1);
				//	isFirst = false;
				}
				s.append(" }");
				appendCardinality(s);
			}
		};
		preSerializer.addSerializedNode(nestedSequenceSerializationNode);			// XXX parent counted list */
	}

	@Override
	public void serialize(@NonNull Serializer serializer, @NonNull SerializationBuilder serializationBuilder) {
		serializer.serializeNodes(serializationBuilder, serializationNodes);
	//	for (@NonNull SerializationNode serializationNode : serializationNodes) {
	//		serializationNode.serialize(serializationBuilder);
	//	}
	}

	@Override
	public void toString(@NonNull StringBuilder s, int depth) {
		s.append("{");
		for (@NonNull SerializationNode serializationNode : serializationNodes) {
			s.append("\n");
			StringUtil.appendIndentation(s, depth, "\t");
			s.append("+ ");
			serializationNode.toString(s, depth+1);
		}
		s.append("\n");
		StringUtil.appendIndentation(s, depth, "\t");
		s.append("}");
		appendCardinality(s, depth);
	}
}