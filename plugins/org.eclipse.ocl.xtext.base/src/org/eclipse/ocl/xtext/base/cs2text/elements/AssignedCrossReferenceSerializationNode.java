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

import java.util.Collections;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.xtext.base.cs2text.SerializationBuilder;
import org.eclipse.ocl.xtext.base.cs2text.user.UserElementSerializer;
import org.eclipse.ocl.xtext.base.cs2text.xtext.AbstractRuleAnalysis;
import org.eclipse.ocl.xtext.base.cs2text.xtext.DirectAssignmentAnalysis;
import org.eclipse.ocl.xtext.base.cs2text.xtext.XtextGrammarUtil;
import org.eclipse.xtext.AbstractRule;
import org.eclipse.xtext.CrossReference;
import org.eclipse.xtext.RuleCall;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.serializer.tokens.ICrossReferenceSerializer;

public class AssignedCrossReferenceSerializationNode extends AbstractAssignedSerializationNode
{
	protected final @NonNull CrossReference crossReference;
	protected final @NonNull AbstractRuleAnalysis calledRuleAnalysis;
	private @NonNull ICrossReferenceSerializer crossReferenceSerializer;

	public AssignedCrossReferenceSerializationNode(@NonNull DirectAssignmentAnalysis assignmentAnalysis, @NonNull MultiplicativeCardinality multiplicativeCardinality, @NonNull CrossReference crossReference) {
		super(assignmentAnalysis, multiplicativeCardinality);
		RuleCall ruleCall = (RuleCall) XtextGrammarUtil.getTerminal(crossReference);
		AbstractRule calledRule = XtextGrammarUtil.getRule(ruleCall);
		this.calledRuleAnalysis = grammarAnalysis.getRuleAnalysis(calledRule);
		this.crossReference = crossReference;
		this.crossReferenceSerializer = grammarAnalysis.getCrossReferenceSerializer();
		assert !((EReference)eStructuralFeature).isContainment();
	}

	@Override
	public @Nullable Iterable<@NonNull AbstractRuleAnalysis> getAssignedRuleAnalyses() {
		return Collections.singletonList(calledRuleAnalysis);
	}

	@Override
	public @NonNull SerializationNode clone(@Nullable MultiplicativeCardinality multiplicativeCardinality) {
		if (multiplicativeCardinality == null) multiplicativeCardinality = this.multiplicativeCardinality;
		return new AssignedCrossReferenceSerializationNode((DirectAssignmentAnalysis)assignmentAnalysis, multiplicativeCardinality, crossReference);
	}

	@Override
	public void serialize(@NonNull UserElementSerializer serializer, @NonNull SerializationBuilder serializationBuilder) {
		EObject eGet = (EObject)serializer.consumeNext(eStructuralFeature);
		///	final String lexerRule = linkingHelper.getRuleNameFrom();

		EObject context = serializer.getElement();
		EObject target = eGet;
		INode node = null;
	/*	ISerializationDiagnostic.Acceptor errorAcceptor = new ISerializationDiagnostic.Acceptor()
		{
			@Override
			public void accept(ISerializationDiagnostic diagnostic) {
				// TODO Auto-generated method stub

			}
		}; */
		String val = crossReferenceSerializer.serializeCrossRef(context, crossReference, target, node, null);

	//	String val = valueConverterService.toString(eGet, ruleAnalysis.getRuleName());
		/*	if ("URI".equals(ruleName)) {
				if (semanticObject instanceof PathElementWithURICS) {
					PathElementWithURICS pathElementWithURICS = (PathElementWithURICS)semanticObject;
					String uri = pathElementWithURICS.getUri();
					if (uri != null) {
						String converted = helper.convert(uri, ruleName);
						if (converted != null) {
							return converted;
						}
					}
				} */
		serializationBuilder.append(val);
	}

	@Override
	public void toString(@NonNull StringBuilder s, int depth) {
		XtextGrammarUtil.appendEStructuralFeatureName(s, assignmentAnalysis);
		s.append(eStructuralFeature.isMany() ? "+=" : "=");
		s.append(calledRuleAnalysis.getRuleName());
		appendCardinality(s, depth);
	}
}