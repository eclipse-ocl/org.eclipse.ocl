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

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.xtext.base.cs2text.MultiplicativeCardinality;
import org.eclipse.ocl.xtext.base.cs2text.xtext.AssignmentAnalysis;
import org.eclipse.ocl.xtext.base.cs2text.xtext.XtextGrammarUtil;

public class AssignedCurrentSerializationNode extends AbstractAssignedSerializationNode
{
	public AssignedCurrentSerializationNode(@NonNull AssignmentAnalysis assignmentAnalysis, @NonNull MultiplicativeCardinality multiplicativeCardinality) {
		super(assignmentAnalysis, multiplicativeCardinality);
	}

	@Override
	public @NonNull SerializationNode clone(@NonNull MultiplicativeCardinality multiplicativeCardinality) {
		throw new UnsupportedOperationException();
	}

	//
	//		(... XX ... {YY.yy=current} ...)           => (... ... {YY} yy=XX ...)
	//		(... XX ... (... {YY.yy=current} ...)?)    => ((... XX ...) | (... ... ... {YY} yy=XX ...))



	//		RCG = (... RC ... ACG ...) => RCG = (... ... ACG' ...)
	//		RCG = (... RC ... ACG? ...) => RCG = (... ... ACG' ...) | (... ... ACG' ...)
	//		RC = UnassignedRuleCallSerializationNode
	//		ACG = (... {YY.yy=current} ...)
	//		ACG' = (... {YY} yy=RC ...)

	//
	//	Restriction for now:
	//	XX cannot be nested wrt common group
	//	YY can only be nested once wrt common group
	/*
	@Override
	public void resolveAssignedCurrentSerializationNodes(@NonNull Stack<@NonNull SequenceSerializationNode> parentStack) {
		SerializationNode childNode = this;
		for (int i = parentStack.size(); --i >= 0; ) {
			SequenceSerializationNode parentNode = parentStack.get(i);
			List<@NonNull SerializationNode> parentChildNodes = parentNode.getSerializationNodes();
			int index = parentChildNodes.indexOf(childNode);
			assert index >= 0;
			for (int j = 0; j < index; j++) {
				SerializationNode precedingNode = parentChildNodes.get(j);
				if (precedingNode instanceof UnassignedRuleCallSerializationNode) {
					SequenceSerializationNode commonGroup = parentNode;
					UnassignedRuleCallSerializationNode ruleCallNode = (UnassignedRuleCallSerializationNode) precedingNode;
					SerializationNode assignedCurrentGroup = childNode;
					rewriteAssignedCurrentSerializationNodes(commonGroup, ruleCallNode, assignedCurrentGroup);
					return;
				}
			}
			childNode = parentNode;
		}
	} */

/*	private void rewriteAssignedCurrentSerializationNodes(@NonNull SequenceSerializationNode commonGroup,
			@NonNull UnassignedRuleCallSerializationNode ruleCallNode, @NonNull SerializationNode assignedCurrentGroup) {
		if (assignedCurrentGroup.getMultiplicativeCardinality().mayBeZero()) {

		}
		else {
			commonGroup.getSerializationNodes().remove(ruleCallNode);
			assignedCurrentGroup.getSe
		}
	} */

	@Override
	public void toString(@NonNull StringBuilder s, int depth) {
//		XtextGrammarUtil.appendEStructuralFeatureName(s, eFeatureScope, eStructuralFeature);
		s.append(XtextGrammarUtil.getName(assignmentAnalysis.getEClass()));
		s.append("::");
		s.append(XtextGrammarUtil.getName(eStructuralFeature));
		s.append(eStructuralFeature.isMany() ? "+=" : "=");
		s.append("«current»");
		appendCardinality(s, depth);
	}
}