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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.xtext.base.cs2text.MultiplicativeCardinality;
import org.eclipse.ocl.xtext.base.cs2text.SerializationBuilder;
import org.eclipse.ocl.xtext.base.cs2text.Serializer;
import org.eclipse.ocl.xtext.base.cs2text.xtext.AbstractRuleAnalysis;
import org.eclipse.ocl.xtext.base.cs2text.xtext.AssignmentAnalysis;
import org.eclipse.ocl.xtext.base.cs2text.xtext.XtextGrammarUtil;
import org.eclipse.xtext.conversion.IValueConverterService;
import org.eclipse.xtext.linking.impl.LinkingHelper;

public class AssignedRuleCallSerializationNode extends AbstractAssignedSerializationNode
{
	protected final @NonNull AbstractRuleAnalysis calledRuleAnalysis;
//	@Inject
	private @NonNull IValueConverterService valueConverterService;
//	@Inject
	private @NonNull LinkingHelper linkingHelper;

	public AssignedRuleCallSerializationNode(@NonNull AssignmentAnalysis assignmentAnalysis, @NonNull MultiplicativeCardinality multiplicativeCardinality, @NonNull AbstractRuleAnalysis calledRuleAnalysis) {
		super(assignmentAnalysis, multiplicativeCardinality);
		this.calledRuleAnalysis = calledRuleAnalysis;
		this.valueConverterService = grammarAnalysis.getValueConverterService();
		this.linkingHelper = grammarAnalysis.getLinkingHelper();
	}

	@Override
	public @NonNull SerializationNode clone(@NonNull MultiplicativeCardinality multiplicativeCardinality) {
		return new AssignedRuleCallSerializationNode(assignmentAnalysis, multiplicativeCardinality, calledRuleAnalysis);
	}

	@Override
	public void serialize(@NonNull Serializer serializer, @NonNull SerializationBuilder serializationBuilder) {
		Object eGet = serializer.consumeNext(eStructuralFeature);
		if (eStructuralFeature instanceof EReference) {
			assert ((EReference)eStructuralFeature).isContainment();
			assert eGet != null;
			serializer.serializeElement(serializationBuilder, (EObject)eGet);
		}
		else {
//			serializationBuilder.append("«attribute-call»");
		///	final String lexerRule = linkingHelper.getRuleNameFrom();


			String val = valueConverterService.toString(eGet, calledRuleAnalysis.getRuleName());
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
	}

	@Override
	public void toString(@NonNull StringBuilder s, int depth) {
		XtextGrammarUtil.appendEStructuralFeatureName(s, assignmentAnalysis);
		s.append(eStructuralFeature.isMany() ? "+=" : "=");
		s.append(calledRuleAnalysis.getRuleName());
		appendCardinality(s, depth);
	}
}