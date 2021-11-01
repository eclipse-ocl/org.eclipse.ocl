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
import org.eclipse.ocl.examples.xtext.build.elements.SerializationNode;
import org.eclipse.ocl.examples.xtext.idioms.Idiom;
import org.eclipse.ocl.examples.xtext.idioms.IdiomsUtils;
import org.eclipse.ocl.examples.xtext.idioms.SubIdiom;
import org.eclipse.ocl.examples.xtext.serializer.SerializationUtils;

/**
 * An IdiomMatch is created after a first successful match of an idiom. It accumulates
 * subsequent matches for idioms with multiple subidioms.
 *
 * An Idiom such as {...} may be nested or cascaded to support {..{..]..} or {..}..{..}
 */
public class IdiomSerializationMatch implements IdiomMatch
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
	private final @NonNull SerializationNode @NonNull [] locatedNodes;

	/**
	 * A nested match if a first subidiom re-occurs before a last subidiom.
	 */
	private @Nullable IdiomSerializationMatch nestedMatch = null;

	/**
	 * An additional match if a first subidiom re-occurs after a last subidiom.
	 */
	private @Nullable IdiomSerializationMatch additionalMatch = null;

	public IdiomSerializationMatch(@NonNull Idiom idiom, @NonNull SerializationNode serializationNode) {
		this.idiom = idiom;
		this.locatedNodes = new @NonNull SerializationNode[idiom.getOwnedSubIdioms().size()];
		locatedNodes[subIdiomIndex++] = serializationNode;
	}

	public @NonNull Idiom getIdiom() {
		return idiom;
	}

	public boolean installIn(@NonNull Map<@NonNull SerializationNode, @NonNull List<@NonNull SubIdiom>> serializationNode2subIdioms) {
		if (additionalMatch != null) {
			additionalMatch.installIn(serializationNode2subIdioms);
		}
		if (subIdiomIndex >= locatedNodes.length) {
			for (@NonNull SerializationNode serializationNode : locatedNodes) {
				List<@NonNull SubIdiom> subIdioms = SerializationUtils.maybeNull(serializationNode2subIdioms.get(serializationNode));
				if ((subIdioms != null) && !isAllMixIn(subIdioms)) {
					return false;
				}
			}
		}
		if ((nestedMatch != null) && !nestedMatch.installIn(serializationNode2subIdioms)) {
			return false;
		}
		if (subIdiomIndex >= locatedNodes.length) {
			for (int i = 0; i < locatedNodes.length; i++) {
				SerializationNode serializationNode = locatedNodes[i];
				SubIdiom subIdiom = SerializationUtils.nonNullState(IdiomsUtils.getOwnedSubIdioms(idiom).get(i));
				if (subIdiom.getOwnedSegments().size() > 0) {		// Locator-only subidioms are trivislly 'mixed-in'
					List<@NonNull SubIdiom> subIdioms = SerializationUtils.maybeNull(serializationNode2subIdioms.get(serializationNode));
					if (subIdioms == null) {
						subIdioms = new ArrayList<>();
						serializationNode2subIdioms.put(serializationNode, subIdioms);
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

	/**
	 * Return true if serializationNode is a match for the next subidiom,
	 * updating locatedNodes,additionalMatch, nextMatch accordingly.
	 */
	public boolean nextMatch(@NonNull SerializationNode serializationNode, @NonNull SerializationRuleAnalysis serializationRule) {
		List<@NonNull SubIdiom> subIdioms = IdiomsUtils.getOwnedSubIdioms(idiom);
		if (subIdiomIndex >= locatedNodes.length) {
			if (additionalMatch != null) {
				additionalMatch.nextMatch(serializationNode, serializationRule);
			}
			else if (serializationRule.matches(subIdioms.get(0), serializationNode)) {		// Look to chain a new sub-match
				additionalMatch = new IdiomSerializationMatch(idiom, serializationNode);
			}
			return true;																	// Handled by additional match
		}
		if (nestedMatch != null) {															// Pass down to active sub-match
			if (nestedMatch.nextMatch(serializationNode, serializationRule)) {
				return true;
			}
		}
		if (serializationRule.matches(subIdioms.get(subIdiomIndex), serializationNode)) {	// Continue current match
			locatedNodes[subIdiomIndex++] = serializationNode;
			return true;
		}
		else if (serializationRule.matches(subIdioms.get(0), serializationNode)) {			// Look to nest a recursive sub-match
			nestedMatch = new IdiomSerializationMatch(idiom, serializationNode);
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
			s.append(locatedNodes[i]);
		}
		if (nestedMatch != null) {
			nestedMatch.toString(s, depth+1);
		}
		if (additionalMatch != null) {
			additionalMatch.toString(s, depth);
		}
	}
}
