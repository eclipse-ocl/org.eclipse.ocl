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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.build.analysis.LocatorHelper.LocatorSwitch;
import org.eclipse.ocl.build.elements.SerializationNode;
import org.eclipse.ocl.xtext.base.serializer.Indexed;
import org.eclipse.ocl.xtext.base.serializer.SerializationUtils;
import org.eclipse.ocl.xtext.idioms.Idiom;
import org.eclipse.ocl.xtext.idioms.IdiomsUtils;
import org.eclipse.ocl.xtext.idioms.Locator;
import org.eclipse.ocl.xtext.idioms.ReferredLocator;
import org.eclipse.ocl.xtext.idioms.SubIdiom;
import org.eclipse.ocl.xtext.idioms.impl.LocatorImpl;
import org.eclipse.xtext.AbstractElement;
import org.eclipse.xtext.Action;
import org.eclipse.xtext.CompoundElement;
import org.eclipse.xtext.ParserRule;

import com.google.common.collect.Iterables;

/**
 * A ParserRuleAnalysis provides the extended analysis of an Xtext ParserRule
 */
public abstract class AbstractNonTerminalRuleAnalysis extends AbstractRuleAnalysis implements Indexed
{
	/**
	 * The subidioms to decorate each node during formatting.
	 */
	private @Nullable Map<@NonNull AbstractElement, @NonNull List<@NonNull SubIdiom>> grammarElement2subIdioms = null;

	protected AbstractNonTerminalRuleAnalysis(@NonNull GrammarAnalysis grammarAnalysis, int index, @NonNull ParserRule parserRule) {
		super(grammarAnalysis, index, parserRule);
	}

	protected void gatherFormattingIdioms(@NonNull AbstractElement abstractElement, @NonNull List<@Nullable List<@NonNull SubIdiom>> serializationIdiomsList) {
		serializationIdiomsList.add(getSubIdioms(abstractElement));
		if (abstractElement instanceof CompoundElement) {
			for (AbstractElement childElement : ((CompoundElement)abstractElement).getElements()) {
				assert childElement != null;
				gatherFormattingIdioms(childElement, serializationIdiomsList);
			}
		}
	}

	public @NonNull Map<@NonNull AbstractElement, @NonNull List<@NonNull SubIdiom>> getGrammarElement2subIdioms() {
		Map<@NonNull AbstractElement, @NonNull List<@NonNull SubIdiom>> grammarElement2subIdioms2 = grammarElement2subIdioms;
		if (grammarElement2subIdioms2 == null) {
		//	StringBuilder s = new StringBuilder();
		//	new SerializationUtils.XtextToString(s, false).doSwitch(abstractRule.getAlternatives());
		//	String ss = s.toString();
		//	if (ss.contains("BinaryOperatorName")) {
		//		getClass();
		//	}
			EClassifier producedEClassifier = abstractRule.getType().getClassifier();
			assert producedEClassifier != null;
			List<@NonNull Idiom> idioms = new ArrayList<>();
			for (@NonNull Idiom idiom : grammarAnalysis.getIdioms()) {
				boolean isOk = true;
			// ParserRule has alternatives with distinct producedEClasses, so must defer to subidiom
			//	EClass inEClass = idiom.getForEClass();
			//	if ((inEClass != null) && !AnalysisUtils.isSuperTypeOf(inEClass, producedEClass)) {
			//		isOk = false;
			//	}
				Pattern pattern = idiom.getRegexPattern();
				if (pattern != null) {
					Matcher matcher = pattern.matcher(getName());
					if (!matcher.matches()) {
						isOk = false;
					}
				}
				if (isOk) {
					idioms.add(idiom);
				}
			}
			//
			//	Locate the matches for each idiom.
			//
			@Nullable IdiomGrammarMatch @NonNull [] idiomMatches = new @Nullable IdiomGrammarMatch[Iterables.size(idioms)];
			AbstractElement alternatives = abstractRule.getAlternatives();
			assert alternatives != null;
			getIdiomMatches(producedEClassifier, alternatives, idioms, idiomMatches);
			//
			//	Install the subidioms for each first/mixin full idiom match.
			//
			grammarElement2subIdioms2 = new HashMap<>();
			for (@Nullable IdiomGrammarMatch idiomMatch : idiomMatches) {
				if (idiomMatch != null) {
					idiomMatch.installIn(grammarElement2subIdioms2);
				}
			}
			grammarElement2subIdioms = grammarElement2subIdioms2;
		}
		return grammarElement2subIdioms2;
	}

	private void getIdiomMatches(@NonNull EClassifier producedEClassifier, @NonNull AbstractElement abstractElement, @NonNull Iterable<@NonNull Idiom> idioms,
			@Nullable IdiomGrammarMatch @NonNull [] idiomMatches) {
		int idiomIndex = 0;
		for (@NonNull Idiom idiom : idioms) {
			IdiomGrammarMatch idiomMatch = idiomMatches[idiomIndex];
			if (idiomMatch == null) {
				EClass inEClass = idiom.getForEClass();
				if ((inEClass == null) || (inEClass == producedEClassifier) || ((producedEClassifier instanceof EClass) && AnalysisUtils.isSuperTypeOf(inEClass, producedEClassifier))) {
					SubIdiom firstSubIdiom = idiom.getOwnedSubIdioms().get(0);
					assert firstSubIdiom != null;
					boolean firstSubIdiomMatches = matches(firstSubIdiom, abstractElement);
					idiomMatches[idiomIndex] = firstSubIdiomMatches ? grammarAnalysis.createIdiomMatch(idiom, abstractElement) : null;
				}
			}
			else {
				idiomMatch.nextMatch(abstractElement, this);
			}
			idiomIndex++;
		}
		if (abstractElement instanceof CompoundElement) {				// FIXME Alternatives need permutation or maybe just a constraint check
			for (AbstractElement nestedElement : ((CompoundElement)abstractElement).getElements()) {
				assert nestedElement != null;
				if (producedEClassifier instanceof EClass) {
					producedEClassifier = refineProducedEClass(nestedElement, (EClass)producedEClassifier);
				}
				getIdiomMatches(producedEClassifier, nestedElement, idioms, idiomMatches);
			}
		}
	}

	@Override
	public int getIndex() {
		return index;
	}

	protected abstract @NonNull EClassifier getReturnedEClassifier();

	public @Nullable List<@NonNull SubIdiom> getSubIdioms(@NonNull AbstractElement grammarElement) {
		return getGrammarElement2subIdioms().get(grammarElement);
	}

	public boolean matches(@NonNull SubIdiom subIdiom, @NonNull AbstractElement grammarElement) {
		Locator locator = IdiomsUtils.getLocator(subIdiom);
		return matches(locator, grammarElement);
	}

	public boolean matches(@NonNull Locator locator, @NonNull AbstractElement grammarElement) {
		assert !(locator instanceof ReferredLocator);
	//	if (locator instanceof ReferredLocator) {
	//		locator = IdiomsUtils.getOwnedLocator(IdiomsUtils.getLocatorDeclaration((ReferredLocator)locator));
	//	}
		LocatorImpl locatorImpl = (LocatorImpl)locator;
		LocatorHelper locatorHelper = (LocatorHelper)locatorImpl.basicGetHelper();
		if (locatorHelper == null) {
			LocatorSwitch subIdiomLocatorSwitch = grammarAnalysis.getLocatorSwitch();
			locatorHelper = subIdiomLocatorSwitch.doSwitch(locator);
			locatorImpl.setHelper(locatorHelper);
		}
		if (locatorHelper == null) {	// Only actually null after an UnsupportedOperationException
			return false;
		}
		return locatorHelper.matches(locator, grammarElement, this);
	}

	public boolean matches(@NonNull Locator locator, @NonNull SerializationNode serializationNode) {
		assert !(locator instanceof ReferredLocator);
	//	if (locator instanceof ReferredLocator) {
	//		locator = IdiomsUtils.getOwnedLocator(IdiomsUtils.getLocatorDeclaration((ReferredLocator)locator));
	//	}
		LocatorImpl locatorImpl = (LocatorImpl)locator;
		LocatorHelper locatorHelper = (LocatorHelper)locatorImpl.basicGetHelper();
		if (locatorHelper == null) {
			LocatorSwitch subIdiomLocatorSwitch = grammarAnalysis.getLocatorSwitch();
			locatorHelper = subIdiomLocatorSwitch.doSwitch(locator);
			locatorImpl.setHelper(locatorHelper);
		}
		if (locatorHelper == null) {	// Only actually null after an UnsupportedOperationException
			return false;
		}
		return locatorHelper.matches(locator, serializationNode);
	}

	private @NonNull EClass refineProducedEClass(@NonNull AbstractElement grammarElement, @NonNull EClass producedEClass) {
		if (grammarElement instanceof Action) {
			EClass assignedEClass = (EClass)((Action)grammarElement).getType().getClassifier();
			assert assignedEClass != null;
			producedEClass = SerializationUtils.getSubTypeOf(producedEClass, assignedEClass);
		}
		return producedEClass;
	}
}