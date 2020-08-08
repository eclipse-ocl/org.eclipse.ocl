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
package org.eclipse.ocl.xtext.base.cs2text.idioms;

import java.util.Map;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.utilities.StringUtil;
import org.eclipse.ocl.xtext.base.cs2text.elements.BasicSerializationRule;
import org.eclipse.ocl.xtext.base.cs2text.elements.SerializationNode;

/**
 * An IdiomMatch is created after a first successful match of an idiom. It accumulates
 * subsequent matches for idioms with multiple subidioms.
 *
 * An Idiom such as {...} may be nested or cascaded to support {..{..]..} or {..}..{..}
 */
public class IdiomMatch
{
	protected final @NonNull Idiom idiom;
	private int subIdiomIndex = 0;
	private final @NonNull SerializationNode @NonNull [] matchNodes;
	private @Nullable IdiomMatch nestedMatch = null;
	private @Nullable IdiomMatch additionalMatch = null;

	public IdiomMatch(@NonNull Idiom idiom, @NonNull SerializationNode serializationNode) {
		this.idiom = idiom;
		this.matchNodes = new @NonNull SerializationNode[idiom.getOwnedSubIdioms().size()];
		matchNodes[subIdiomIndex++] = serializationNode;
	}

	public @NonNull Idiom getIdiom() {
		return idiom;
	}

	public boolean installIn(@NonNull Map<@NonNull SerializationNode, @NonNull SubIdiom> serializationNode2subIdiom) {
		if (additionalMatch != null) {
			additionalMatch.installIn(serializationNode2subIdiom);
		}
		if (isMatchedLocal()) {
			for (@NonNull SerializationNode serializationNode : matchNodes) {
				if (serializationNode2subIdiom.get(serializationNode) != null) {
					return false;
				}
			}
		}
		if ((nestedMatch != null) && !nestedMatch.installIn(serializationNode2subIdiom)) {
			return false;
		}
		if (isMatchedLocal()) {
			for (int i = 0; i < matchNodes.length; i++) {
				SerializationNode serializationNode = matchNodes[i];
				serializationNode2subIdiom.put(serializationNode, idiom.getOwnedSubIdioms().get(i));
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

	private boolean isMatchedLocal() {
		if (subIdiomIndex < matchNodes.length) {
			return false;
		}
		return true;
	}

	public boolean nextMatch(@NonNull SerializationNode serializationNode, @NonNull BasicSerializationRule serializationRule) {
		if (isMatchedLocal()) {
			if (additionalMatch != null) {
				additionalMatch.nextMatch(serializationNode, serializationRule);
			}
			else if (idiom.getOwnedSubIdioms().get(0).matches(serializationNode, serializationRule)) {								// Look to chain a new sub-match
				additionalMatch = new IdiomMatch(idiom, serializationNode);
			}
			return true;		// Handled by additional match
		}
		if (nestedMatch != null) {															// Pass down to active sub-match
			if (nestedMatch.nextMatch(serializationNode, serializationRule)) {
				return true;
			}
		}
		if (idiom.getOwnedSubIdioms().get(0).matches(serializationNode, serializationRule)) {								// Look to nest a new sub-match
			nestedMatch = new IdiomMatch(idiom, serializationNode);
			return true;
		}
		else if (idiom.getOwnedSubIdioms().get(subIdiomIndex).matches(serializationNode, serializationRule)) {	// Continue current match
			matchNodes[subIdiomIndex++] = serializationNode;
			return true;
		}
		else {
			return false;
		}
//			assert nestedMatch != null;
//		}
//		else if ((idiom.getSubIdioms().length > 1) &&  idiom.getSubidiom(0).matches(serializationNode, serializationRule)) {								// Look to nest a new sub-match
//			nestedMatch = new IdiomMatch(idiom, serializationNode);
//		}
//		else if (!isMatchedLocal() && idiom.getSubidiom(subIdiomIndex).matches(serializationNode, serializationRule)) {	// Continue current match
//			matchNodes[subIdiomIndex++] = serializationNode;
//		}
//		return true;
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
			StringUtil.appendIndentation(s, depth);
			s.append(matchNodes[i]);
		}
		if (nestedMatch != null) {
			nestedMatch.toString(s, depth+1);
		}
		if (additionalMatch != null) {
			additionalMatch.toString(s, depth);
		}
	}
}
