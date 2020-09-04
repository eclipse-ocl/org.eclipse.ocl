/**
 * Copyright (c) 2020 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 */
package org.eclipse.ocl.xtext.base.cs2text.runtime;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.TreeIterable;
import org.eclipse.xtext.AbstractRule;
import org.eclipse.xtext.CrossReference;
import org.eclipse.xtext.Grammar;
import org.eclipse.xtext.GrammarUtil;
import org.eclipse.xtext.RuleCall;
import org.eclipse.xtext.service.GrammarProvider;

import com.google.inject.Inject;

public abstract class AbstractSerializationMetaData implements SerializationMetaData
{
	@Inject
	private GrammarProvider grammarProvider;

	/**
	 * The cache of all distinct CrossReferences in the grammar, indexed by the traversed ERefence and the Rule name for the referenced value.
	 *
	 * This cache is typically rather small and only used transiently during construction. Lists might be better.
	 */
	private @Nullable Map<@NonNull EReference, @NonNull Map<@NonNull String, @NonNull CrossReference>> eReference2ruleName2crossReference = null;

	/**
	 * Serialization of references uses ICrossReferenceSerializer.serializeCrossRef(EObject, CrossReference, EObject, INode, ISerializationDiagnostic.Acceptor)
	 * which needs a plausible CrossReference.
	 *
	 * This is most reliably obtained by finding the CrossReference in the grammar using name context.
	 *
	 * (More efficient would be to use URIs, but that would fail if the grammar evolves without regenerating the serilaizer.)
	 *
	 * (Better if an alternative API didn't need the CrossReference.)
	 */
	public @NonNull CrossReference getCrossReference(/*@NonNull*/ EReference assignedEReference, @NonNull String assignedRuleName) {
		Map<@NonNull EReference, @NonNull Map<@NonNull String, @NonNull CrossReference>> eReference2ruleName2crossReference2 = eReference2ruleName2crossReference;
		if (eReference2ruleName2crossReference2 == null) {
			eReference2ruleName2crossReference = eReference2ruleName2crossReference2 = new HashMap<>();
			Grammar grammar = grammarProvider.getGrammar(this);
			assert grammar != null;
			analyzeGrammar(grammar, eReference2ruleName2crossReference2);
		}
		Map<@NonNull String, CrossReference> ruleName2crossReference = ClassUtil.maybeNull(eReference2ruleName2crossReference2.get(assignedEReference));
		if (ruleName2crossReference != null) {
			CrossReference crossReference = ruleName2crossReference.get(assignedRuleName);
			if (crossReference != null) {
				return crossReference;
			}
		}
		throw new IllegalStateException();
	/*	Assignment assignment = XtextFactory.eINSTANCE.createAssignment();
		CrossReference crossReference = XtextFactory.eINSTANCE.createCrossReference();
		RuleCall ruleCall = XtextFactory.eINSTANCE.createRuleCall();
		ParserRule calledRule = XtextFactory.eINSTANCE.createParserRule();
		assignment.setFeature(assignedEReference.getName());
		assignment.setTerminal(crossReference);
		crossReference.setTerminal(ruleCall);
		ruleCall.setRule(calledRule);
		calledRule.setName(assignedRuleName);
		return crossReference; */
	}

	/**
	 * Traverse the grammar(s) to discover all distinct CrossREferences.
	 */
	private void analyzeGrammar(@NonNull Grammar grammar, @NonNull Map<@NonNull EReference, @NonNull Map<@NonNull String, @NonNull CrossReference>> eReference2ruleName2crossReference) {
		for (@NonNull EObject eObject : new TreeIterable(grammar, false)) {
			if (eObject instanceof CrossReference) {
				CrossReference crossReference = (CrossReference)eObject;
				EReference eReference = GrammarUtil.getReference(crossReference);
				assert eReference != null;
				RuleCall ruleCall = (RuleCall)crossReference.getTerminal();
				AbstractRule calledRule = ruleCall.getRule();
				String calledRuleName = calledRule.getName();
				assert calledRuleName != null;
				Map<@NonNull String, @NonNull CrossReference> ruleName2crossReference = ClassUtil.maybeNull(eReference2ruleName2crossReference.get(eReference));
				if (ruleName2crossReference == null) {
					ruleName2crossReference = new HashMap<>();
					eReference2ruleName2crossReference.put(eReference, ruleName2crossReference);
				}
				CrossReference oldCrossReference = ClassUtil.maybeNull(ruleName2crossReference.get(calledRuleName));
				if (oldCrossReference == null) {
					ruleName2crossReference.put(calledRuleName, crossReference);
				}
			}
		}
		for (Grammar usedGrammar : grammar.getUsedGrammars()) {
			assert usedGrammar != null;
			analyzeGrammar(usedGrammar, eReference2ruleName2crossReference);
		}
	}
}
