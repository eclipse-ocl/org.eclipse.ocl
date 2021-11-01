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
package org.eclipse.ocl.examples.xtext.build.analysis;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.xtext.idioms.Idiom;
import org.eclipse.ocl.examples.xtext.idioms.IdiomsUtils;
import org.eclipse.ocl.examples.xtext.idioms.SubIdiom;
import org.eclipse.ocl.examples.xtext.serializer.SerializationUtils;
import org.eclipse.xtext.AbstractElement;

/**
 * An IdiomMatch is created after a first successful match of an idiom. It accumulates
 * subsequent matches for idioms with multiple subidioms.
 *
 * An Idiom such as {...} may be nested or cascaded to support {..{..]..} or {..}..{..}
 */
public class IdiomGrammarMatch implements IdiomMatch
{
	/**
	 * The matched Idiom.
	 */
	protected final @NonNull Idiom idiom;

	/**
	 * Counter for the subidiom locations, first location in constructor, more in nextMatch().
	 */
	private int subIdiomIndex = 0;

	/**
	 * The located node for each subidiom.
	 */
	private final @NonNull AbstractElement @NonNull [] locatedElements;

	/**
	 * A nested match if a first subidiom re-occurs before a last subidiom.
	 */
	private @Nullable IdiomGrammarMatch nestedMatch = null;

	/**
	 * An additional match if a first subidiom re-occurs after a last subidiom.
	 */
	private @Nullable IdiomGrammarMatch additionalMatch = null;

	public IdiomGrammarMatch(@NonNull Idiom idiom, @NonNull AbstractElement grammarElement) {
		this.idiom = idiom;
		this.locatedElements = new @NonNull AbstractElement[idiom.getOwnedSubIdioms().size()];
		locatedElements[subIdiomIndex++] = grammarElement;
	}

	public @NonNull Idiom getIdiom() {
		return idiom;
	}

	public boolean installIn(@NonNull Map<@NonNull AbstractElement, @NonNull List<@NonNull SubIdiom>> grammarElement2subIdioms) {
		if (additionalMatch != null) {
			additionalMatch.installIn(grammarElement2subIdioms);
		}
		if (subIdiomIndex >= locatedElements.length) {
			for (@NonNull AbstractElement grammarElement : locatedElements) {
				List<@NonNull SubIdiom> subIdioms = SerializationUtils.maybeNull(grammarElement2subIdioms.get(grammarElement));
				if ((subIdioms != null) && !isAllMixIn(subIdioms)) {
					return false;
				}
			}
		}
		if ((nestedMatch != null) && !nestedMatch.installIn(grammarElement2subIdioms)) {
			return false;
		}
		if (subIdiomIndex >= locatedElements.length) {
			for (int i = 0; i < locatedElements.length; i++) {
				AbstractElement grammarElement = locatedElements[i];
				SubIdiom subIdiom = SerializationUtils.nonNullState(IdiomsUtils.getOwnedSubIdioms(idiom).get(i));
				if (subIdiom.getOwnedSegments().size() > 0) {		// Locator-only subidioms are trivislly 'mixed-in'
					List<@NonNull SubIdiom> subIdioms = SerializationUtils.maybeNull(grammarElement2subIdioms.get(grammarElement));
					if (subIdioms == null) {
						subIdioms = new ArrayList<>();
						grammarElement2subIdioms.put(grammarElement, subIdioms);
					}
					if (isAllMixIn(subIdioms)) {
						subIdioms.add(subIdiom);
					}
					else {
						// FIXME ERROR MESSAGE
					}
				}
			}
		}
		return true;
	}

	protected boolean isAllMixIn(@NonNull Iterable<@NonNull SubIdiom> subIdioms) {
		for (@NonNull SubIdiom subIdiom : subIdioms) {
			if (!IdiomsUtils.getOwningIdiom(subIdiom).isMixin()) {
				return false;
			}
		}
		return true;
	}

/*	public boolean isMatched() {
		if (subIdiomIndex < matchNodes.length) {
			return false;
		}
		if (nestedMatch != null) {
			assert nestedMatch.isMatched();
		}
		if (additionalMatch != null) {
			return additionalMatch.isMatched();
		}
		return true;
	} */

	/**
	 * Return true if grammarElement is a match for the next subidiom,
	 * updating locatedNodes,additionalMatch, nextMatch accordingly.
	 */
	public boolean nextMatch(@NonNull AbstractElement grammarElement, @NonNull ParserRuleAnalysis parserRuleAnalysis) {
		List<@NonNull SubIdiom> subIdioms = IdiomsUtils.getOwnedSubIdioms(idiom);
		if (subIdiomIndex >= locatedElements.length) {
			if (additionalMatch != null) {
				additionalMatch.nextMatch(grammarElement, parserRuleAnalysis);
			}
			else if (parserRuleAnalysis.matches(subIdioms.get(0), grammarElement)) {		// Look to chain a new sub-match
				additionalMatch = new IdiomGrammarMatch(idiom, grammarElement);
			}
			return true;																	// Handled by additional match
		}
		if (nestedMatch != null) {															// Pass down to active sub-match
			if (nestedMatch.nextMatch(grammarElement, parserRuleAnalysis)) {
				return true;
			}
		}
		if (parserRuleAnalysis.matches(subIdioms.get(subIdiomIndex), grammarElement)) {	// Continue current match
			locatedElements[subIdiomIndex++] = grammarElement;
			return true;
		}
		else if (parserRuleAnalysis.matches(subIdioms.get(0), grammarElement)) {			// Look to nest a recursive sub-match
			nestedMatch = new IdiomGrammarMatch(idiom, grammarElement);
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		toString(s, 0);
		return s.toString();
	}

	public void toString(@NonNull StringBuilder s, int depth) {
		if (depth == 0) {
			s.append(idiom);
		}
		for (int i = 0; i < subIdiomIndex; i++) {
			SerializationUtils.appendIndentation(s, depth);
			s.append(locatedElements[i]);
		}
		if (nestedMatch != null) {
			nestedMatch.toString(s, depth+1);
		}
		if (additionalMatch != null) {
			additionalMatch.toString(s, depth);
		}
	}
}
