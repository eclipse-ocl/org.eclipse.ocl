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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.xtext.AbstractRule;
import org.eclipse.xtext.CrossReference;
import org.eclipse.xtext.RuleCall;
import org.eclipse.xtext.conversion.IValueConverterService;
import org.eclipse.xtext.linking.impl.LinkingHelper;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.serializer.tokens.ICrossReferenceSerializer;

public class AssignedCrossReferenceSerializationNode extends AbstractAssignedSerializationNode
{
	protected final @NonNull CrossReference crossReference;
	protected final @NonNull XtextAbstractRuleAnalysis calledRuleAnalysis;
//	@Inject
	private IValueConverterService valueConverterService;
//	@Inject
	private LinkingHelper linkingHelper;
//	@Inject
	private @NonNull ICrossReferenceSerializer crossReferenceSerializer;

	public AssignedCrossReferenceSerializationNode(@NonNull XtextParserRuleAnalysis ruleAnalysis, @NonNull XtextAssignmentAnalysis assignmentAnalysis, @NonNull MultiplicativeCardinality multiplicativeCardinality, @NonNull CrossReference crossReference) {
		super(ruleAnalysis, assignmentAnalysis, multiplicativeCardinality);
		RuleCall ruleCall = (RuleCall) XtextGrammarUtil.getTerminal(crossReference);
		AbstractRule calledRule = XtextGrammarUtil.getRule(ruleCall);
		XtextGrammarAnalysis grammarAnalysis = ruleAnalysis.getGrammarAnalysis();
		this.calledRuleAnalysis = grammarAnalysis.getRuleAnalysis(calledRule);
		this.crossReference = crossReference;
		this.crossReferenceSerializer = grammarAnalysis.getCrossReferenceSerializer();
		this.valueConverterService = grammarAnalysis.getValueConverterService();
		this.linkingHelper = grammarAnalysis.getLinkingHelper();
		assert !((EReference)eStructuralFeature).isContainment();
	}

	@Override
	public @NonNull SerializationNode clone(@NonNull MultiplicativeCardinality multiplicativeCardinality) {
		return new AssignedCrossReferenceSerializationNode(ruleAnalysis, assignmentAnalysis, multiplicativeCardinality, crossReference);
	}

	@Override
	public void serialize(@NonNull Serializer serializer, @NonNull SerializationBuilder serializationBuilder) {
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
		s.append(ruleAnalysis.getRuleName());
		appendCardinality(s, depth);
	}
}