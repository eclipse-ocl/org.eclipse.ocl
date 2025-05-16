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
package org.eclipse.ocl.build.analysis;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.xtext.base.serializer.DataTypeRuleValue;
import org.eclipse.ocl.xtext.base.serializer.GrammarCardinality;
import org.eclipse.ocl.xtext.base.serializer.SerializationSegment;
import org.eclipse.ocl.xtext.base.serializer.SerializationUtils;
import org.eclipse.ocl.xtext.base.serializer.SubstringStep;
import org.eclipse.ocl.xtext.idioms.FinalLocator;
import org.eclipse.ocl.xtext.idioms.IdiomsUtils;
import org.eclipse.ocl.xtext.idioms.Locator;
import org.eclipse.ocl.xtext.idioms.Segment;
import org.eclipse.ocl.xtext.idioms.SubIdiom;
import org.eclipse.xtext.AbstractElement;
import org.eclipse.xtext.AbstractRule;
import org.eclipse.xtext.Alternatives;
import org.eclipse.xtext.CharacterRange;
import org.eclipse.xtext.Group;
import org.eclipse.xtext.Keyword;
import org.eclipse.xtext.NegatedToken;
import org.eclipse.xtext.ParserRule;
import org.eclipse.xtext.RuleCall;
import org.eclipse.xtext.util.XtextSwitch;

/**
 * A DataTypeRuleAnalysis provides the extended analysis of a ParserRule for a DataType
 */
public class DataTypeRuleAnalysis extends AbstractNonTerminalRuleAnalysis
{
	/**
	 * The LocatorAnalysisSwitch analyzes the data type locators that may occur preciusely once and so
	 * represent cancxdidates for a distinct surrounding formatting.
	 */
	private static class LocatorAnalysisSwitch extends XtextSwitch<@NonNull LocatorAnalysisSwitch>
	{
		protected final @NonNull DataTypeRuleAnalysis dataTypeRuleAnalysis;
	//	protected final @NonNull GrammarAnalysis grammarAnalysis;

		public LocatorAnalysisSwitch(@NonNull DataTypeRuleAnalysis dataTypeRuleAnalysis) {
			this.dataTypeRuleAnalysis = dataTypeRuleAnalysis;
	//		this.grammarAnalysis = dataTypeRuleAnalysis.getGrammarAnalysis();
		}

		public void analyze() {
			AbstractElement rootElement = SerializationUtils.getAlternatives(dataTypeRuleAnalysis.getRule());
			analyze(rootElement);
		}

		private @NonNull LocatorAnalysisSwitch analyze(@NonNull AbstractElement nestedElement) {
			int classifierID = nestedElement.eClass().getClassifierID();
			doSwitch(classifierID, nestedElement);
			return this;
		}

		@Override
		public @NonNull LocatorAnalysisSwitch caseAlternatives(Alternatives alternatives) {
			assert alternatives != null;
			if (prefixMayBeZero(alternatives) && mayBeOne(alternatives) && suffixMayBeZero(alternatives)) {
				for (@NonNull AbstractElement element : SerializationUtils.getElements(alternatives)) {
					analyze(element);
				}
			}
			return this;
		}

		@Override
		public @NonNull LocatorAnalysisSwitch caseCharacterRange(CharacterRange characterRange) {
			assert characterRange != null;
			return this;
		}

		@Override
		public @NonNull LocatorAnalysisSwitch caseGroup(Group group) {
			assert group != null;
			if (prefixMayBeZero(group) && mayBeOne(group) && suffixMayBeZero(group)) {
				for (@NonNull AbstractElement element : SerializationUtils.getElements(group)) {
					analyze(element);
				}
			}
			return this;
		}

		@Override
		public @NonNull LocatorAnalysisSwitch caseKeyword(Keyword keyword) {
			assert keyword != null;
			if (prefixMayBeZero(keyword) && mayBeOne(keyword) && suffixMayBeZero(keyword)) {
				dataTypeRuleAnalysis.addUnitKeyword(keyword);
			}
			return this;
		}

		@Override
		public @NonNull LocatorAnalysisSwitch caseNegatedToken(NegatedToken negatedToken) {
			assert negatedToken != null;
			return this;
		}

		@Override
		public @NonNull LocatorAnalysisSwitch caseRuleCall(RuleCall ruleCall) {
			assert ruleCall != null;
			if (prefixMayBeZero(ruleCall) && mayBeOne(ruleCall) && suffixMayBeZero(ruleCall)) {
				dataTypeRuleAnalysis.addUnitRule(SerializationUtils.getRule(ruleCall));
			}
			return this;
		}

		@Override
		public @NonNull LocatorAnalysisSwitch defaultCase(EObject object) {
			throw new UnsupportedOperationException("Unsupported '" + object.eClass().getName() + "' in LocatorAnalysisSwitch");
		}

		private boolean mayBeOne(@NonNull AbstractElement element) {
			return true;
		}

		private boolean mayBeZero(@NonNull AbstractElement element) {
			GrammarCardinality cardinality = GrammarCardinality.toEnum(element);
			if (cardinality.mayBeZero()) {
				return true;
			}
			if (element instanceof Alternatives) {
				for (@NonNull AbstractElement subElement : SerializationUtils.getElements((Alternatives)element)) {
					if (mayBeZero(subElement)) {
						return true;
					}
				}
				return false;
			}
			else if (element instanceof Group) {
				for (@NonNull AbstractElement subElement : SerializationUtils.getElements(((Group)element))) {
					if (!mayBeZero(subElement)) {
						return false;
					}
				}
				return true;
			}
			return false;
		}

		private boolean prefixMayBeZero(@NonNull AbstractElement element) {
			for (EObject eObject = element, eContainer; (eContainer = eObject.eContainer()) != null; eObject = eContainer) {
				if (eContainer instanceof Group) {
					List<@NonNull AbstractElement> elements = SerializationUtils.getElements((Group)eContainer);
					for (@NonNull AbstractElement preElement : elements) {
						if (preElement == eObject) {
							break;
						}
						if (!mayBeZero(element)) {
							return false;
						}
					}
				}
				else if (eContainer instanceof AbstractRule) {
					break;
				}
			}
			return true;
		}

		private boolean suffixMayBeZero(@NonNull AbstractElement element) {
			for (EObject eObject = element, eContainer; (eContainer = eObject.eContainer()) != null; eObject = eContainer) {
				if (eContainer instanceof Group) {
					List<@NonNull AbstractElement> elements = SerializationUtils.getElements((Group)eContainer);
					for (int i = elements.size(); --i >= 0; ) {
						AbstractElement postElement = elements.get(i);
						if (postElement == eObject) {
							break;
						}
						if (!mayBeZero(postElement)) {
							return false;
						}
					}
				}
				else if (eContainer instanceof AbstractRule) {
					break;
				}
			}
			return true;
		}

		@Override
		public @NonNull String toString() {
			return dataTypeRuleAnalysis.toString();
		}
	}

	protected final @NonNull EDataType eDataType;
	private @Nullable DataTypeRuleValue dataTypeRuleValue = null;
	private @Nullable List<@NonNull Keyword> unitKeywords = null;
	private @Nullable List<@NonNull AbstractRule> unitRules = null;
	private @Nullable List<@NonNull Segment> rule2segments = null;
	private @Nullable Map<@NonNull String, @NonNull List<@NonNull Segment>> string2segments = null;

	public DataTypeRuleAnalysis(@NonNull GrammarAnalysis grammarAnalysis, int index, @NonNull ParserRule parserRule, @NonNull EDataType eDataType) {
		super(grammarAnalysis, index, parserRule);
		this.eDataType = eDataType;
	}

	private void addUnitKeyword(@NonNull Keyword keyword) {
		List<@NonNull Keyword> unitKeywords2 = unitKeywords;
		if (unitKeywords2 == null) {
			unitKeywords = unitKeywords2 = new ArrayList<>();
		}
		unitKeywords2.add(keyword);
	}

	private void addUnitRule(@NonNull AbstractRule rule) {
		List<@NonNull AbstractRule> unitRules2 = unitRules;
		if (unitRules2 == null) {
			unitRules = unitRules2 = new ArrayList<>();
		}
		unitRules2.add(rule);
	}

	public void analyzeSpacing(@NonNull List<@NonNull Locator> dataTypeLocators) {
		List<@NonNull Segment> finalSegments = null;
		if (string2segments == null) {
			for (@NonNull Locator locator : dataTypeLocators) {
				if (locator.covers(abstractRule)) {
					SubIdiom owningSubIdiom = locator.getOwningSubIdiom();
					if ((owningSubIdiom != null) && (rule2segments == null)) {
						rule2segments = IdiomsUtils.getOwnedSegments(owningSubIdiom);
					}
				}
				else if (locator instanceof FinalLocator) {
					SubIdiom owningSubIdiom = locator.getOwningSubIdiom();
					if (owningSubIdiom != null) {
						finalSegments = IdiomsUtils.getOwnedSegments(owningSubIdiom);
					}
				}
			}
			Map<@NonNull String, @NonNull List<@NonNull Segment>> string2segments2 = string2segments = new HashMap<>();
			analyzeSpacing(dataTypeLocators, string2segments2, rule2segments, finalSegments);
			if (rule2segments == null) {
				rule2segments = finalSegments;
			}
		}
	}

	protected void analyzeSpacing(@NonNull List<@NonNull Locator> dataTypeLocators, @NonNull Map<@NonNull String, @NonNull List<@NonNull Segment>> string2segments, @Nullable List<@NonNull Segment> parentSegments, @Nullable List<@NonNull Segment> finalSegments) {
		if (unitKeywords != null) {
			for (@NonNull Keyword keyword : unitKeywords) {
				String string = SerializationUtils.getValue(keyword);
				@Nullable List<@NonNull Segment> childSegments = parentSegments;
				for (@NonNull Locator locator : dataTypeLocators) {
					if (locator.covers(string)) {
						SubIdiom owningSubIdiom = locator.getOwningSubIdiom();
						if (owningSubIdiom != null) {
							childSegments = IdiomsUtils.getOwnedSegments(owningSubIdiom);
						}
					}
				}
				if ((childSegments != null) && !childSegments.equals(finalSegments)) {
					string2segments.put(string, childSegments);
				}
			}
		}
		if (unitRules != null) {
			for (@NonNull AbstractRule rule : unitRules) {
				AbstractRuleAnalysis ruleAnalysis = grammarAnalysis.getRuleAnalysis(rule);
				if (ruleAnalysis instanceof DataTypeRuleAnalysis) {
					DataTypeRuleAnalysis dataTypeRuleAnalysis = (DataTypeRuleAnalysis)ruleAnalysis;
					Map<@NonNull String, @NonNull List<@NonNull Segment>> string2segments2 = dataTypeRuleAnalysis.string2segments;
					if (string2segments2 == null) {
						dataTypeRuleAnalysis.analyzeSpacing(dataTypeLocators);
						string2segments2 = dataTypeRuleAnalysis.string2segments;
						assert string2segments2 != null;
					}
					for (Map.@NonNull Entry<@NonNull String, @NonNull List<@NonNull Segment>> entry : string2segments2.entrySet()) {
						List<@NonNull Segment> childSegments = entry.getValue();
						if (!childSegments.equals(finalSegments)) {
							string2segments.put(entry.getKey(), childSegments);
						}
					}
				}
			}
		}
	}

	public void analyzeUnitLocators() {
		LocatorAnalysisSwitch locatorAnalysisSwitch = new LocatorAnalysisSwitch(this);
		locatorAnalysisSwitch.analyze();
	}

	@Override
	public @Nullable DataTypeRuleValue basicGetRuleValue() {
		return dataTypeRuleValue;
	}

	@Override
	protected @NonNull EClassifier getReturnedEClassifier() {
		return eDataType;
	}

	@Override
	public @NonNull DataTypeRuleValue getRuleValue() {
		DataTypeRuleValue dataTypeRuleValue2 = dataTypeRuleValue;
		if (dataTypeRuleValue2 == null) {
			assert dataTypeRuleValue == null;
			Map<@NonNull String, @NonNull List<@NonNull Segment>> string2segments2 = string2segments;
			@NonNull SubstringStep [] substringSteps;
			if (string2segments2 != null) {
				substringSteps = new @NonNull SubstringStep[string2segments2.size()];
				int j = 0;
				List<@NonNull String> keys = new ArrayList<>(string2segments2.keySet());
				Collections.sort(keys);
				for (@NonNull String key : keys) {
					@NonNull SerializationSegment[] serializationSegments = grammarAnalysis.getSerializationSegments(string2segments2.get(key));
					substringSteps[j++] = new SubstringStep(key, serializationSegments);

				}
				dataTypeRuleValue2 = new DataTypeRuleValue(index, getName(), getSerializationSegments(), substringSteps);
			}
			else {
				substringSteps = null;
			}
			dataTypeRuleValue2 = dataTypeRuleValue = new DataTypeRuleValue(index, getName(), getSerializationSegments(), substringSteps);
		}
		return dataTypeRuleValue2;
	}

	public @NonNull SerializationSegment @NonNull [] getSerializationSegments() {
		return grammarAnalysis.getSerializationSegments(rule2segments);
	}

	@Override
	public void toDebugString(@NonNull StringBuilder s, int depth) {
		s.append(getQualifiedName());
	/*	List<@NonNull RuleCall> delegatingRuleCalls2 = delegatingRuleCalls;
		if (delegatingRuleCalls2 != null) {
			s.append(" <= ");
			boolean isFirst1 = true;
			for (@NonNull RuleCall ruleCall : delegatingRuleCalls2) {
				if (!isFirst1) {
					s.append(", ");
				}
				AbstractRule calledRule = XtextGrammarUtil.getRule(ruleCall);
				ParserRuleAnalysis callingRuleAnalysis = (ParserRuleAnalysis) grammarAnalysis.getRuleAnalysis(calledRule);
				s.append(callingRuleAnalysis.getName());
				isFirst1 = false;
			}
		} */
	/*	List<@NonNull AbstractNonTerminalRuleAnalysis> superRuleAnalysesClosure2 = getSuperRuleAnalysesClosure();
		if (superRuleAnalysesClosure2 != null) {
			s.append(" -> ");
			boolean isFirst1 = true;
			for (@NonNull AbstractNonTerminalRuleAnalysis superRuleAnalysis : superRuleAnalysesClosure2) {
				if (!isFirst1) {
					s.append(", ");
				}
				s.append(superRuleAnalysis.getQualifiedName());
				isFirst1 = false;
			}
		} */
/*		List<@NonNull SerializationRuleAnalysis> serializationRuleAnalyses = basicGetSerializationRuleAnalyses();
		if (serializationRuleAnalyses != null) {
			for (@NonNull SerializationRuleAnalysis serializationRuleAnalysis : serializationRuleAnalyses) {
				SerializationUtils.appendIndentation(s, depth+1);
				s.append(serializationRuleAnalysis);
			}
		} */
/*		s.append(" <=> ");
		boolean isFirst2 = true;
		for (@NonNull EClassifier eClassifier : eClassifiers) {
			if (!isFirst2) {
				s.append(",");
			}
			s.append(eClassifier.getName());
			isFirst2 = false;
		} */
	}
}