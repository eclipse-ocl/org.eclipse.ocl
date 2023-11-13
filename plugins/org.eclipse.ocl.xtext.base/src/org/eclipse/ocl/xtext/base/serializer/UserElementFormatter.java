/*******************************************************************************
 * Copyright (c) 2020, 2023 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.xtext.base.serializer;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.xtext.AbstractElement;
import org.eclipse.xtext.GrammarUtil;
import org.eclipse.xtext.ParserRule;
import org.eclipse.xtext.nodemodel.INode;

/**
 * A Serializer supports the serialization of a user model element, automatically creating a hierarchy
 * of Serializers for the containment hierarchy of the user model element.
 */
public class UserElementFormatter
{
	protected final @NonNull INode node;
	protected final @NonNull AbstractElement compoundedGrammarElement;
	protected final @NonNull UserElementAnalysis elementAnalysis;
	protected final @NonNull SerializationMetaData serializationMetaData;

	public UserElementFormatter(@NonNull INode node, @NonNull AbstractElement compoundedGrammarElement, @NonNull UserModelAnalysis modelAnalysis, @NonNull EObject element) {
		this.node = node;
		this.compoundedGrammarElement = compoundedGrammarElement;
		this.elementAnalysis = modelAnalysis.getElementAnalysis(element);
		this.serializationMetaData = elementAnalysis.getSerializationMetaData();
	}

	public void addCommentSupport(@NonNull CommentSegmentSupport commentSegmentSupport) {
		elementAnalysis.getModelAnalysis().addCommentSupport(commentSegmentSupport);
	}

/*	public @NonNull AbstractRule getCalledRule() {
		for (@NonNull INode child = node, ancestor = child.getParent(); ancestor != null; child = ancestor, ancestor = child.getParent()) {
			EObject grammarElement = ancestor.getGrammarElement();
			if (ancestor.getGrammarElement() instanceof RuleCall) {
				AbstractRule calledRule = ((RuleCall)ancestor).getRule();
				assert calledRule != null;
				return calledRule;
			}
		}
		throw new IllegalStateException("No called rule");
	} */

/*	public @Nullable AbstractRule getCallingRule() {
		for (@NonNull INode ancestor = node.getParent(); ancestor != null; ancestor = ancestor.getParent()) {
			EObject grammarElement = ancestor.getGrammarElement();
			if (grammarElement instanceof RuleCall) {
				AbstractRule callingRule = GrammarUtil.containingRule(grammarElement);
				assert callingRule != null;
				return callingRule;
			}
		}
		/// assert RootNode
		return null;
	} */

	public @NonNull EObject getElement() {
		return elementAnalysis.getEObject();
	}

	public @NonNull SerializationSegment @NonNull [] getInnerFormattingSegments() {
		@NonNull SerializationSegment[] innerFormattingSegments = serializationMetaData.getInnerFormattingSegments(compoundedGrammarElement);
		return innerFormattingSegments;
	}

	public @NonNull INode getNode() {
		return node;
	}

	public @NonNull SerializationSegment @NonNull [] getOuterFormattingSegments() {
		@NonNull SerializationSegment[] outerFormattingSegments = serializationMetaData.getOuterFormattingSegments(compoundedGrammarElement);
		return outerFormattingSegments;
	}

	public @NonNull SerializationSegment @NonNull [] getRuleFormattingSegments() {
		ParserRule parserRule = GrammarUtil.containingParserRule(compoundedGrammarElement);
		AbstractElement rootGrammarElement = parserRule.getAlternatives();
		assert rootGrammarElement !=  null;
		return serializationMetaData.getInnerFormattingSegments(rootGrammarElement);
	}

	public @NonNull SerializationMetaData getSerializationMetaData() {
		return serializationMetaData;
	}
}