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
import org.eclipse.ocl.xtext.base.cs2text.elements.SerializationNode;

/**
 * An IdiomMatch is created after a first successful match of an idiom. It accumulates
 * subsequent matches for idoms with multiple subidioms.
 *
 * An Idiom such as {...} may be nested or cascaded to support {..{..]..} or {..}..{..}
 */
public class IdiomMatch
{
	protected final @NonNull Idiom idiom;
	private int subIdiomIndex = 0;
	private final @NonNull SerializationNode @NonNull [] matchNodes;
	private @Nullable IdiomMatch subMatch = null;

	public IdiomMatch(@NonNull Idiom idiom, @NonNull SerializationNode serializationNode) {
		this.idiom = idiom;
		this.matchNodes = new @NonNull SerializationNode[idiom.getSubIdioms().length];
		matchNodes[subIdiomIndex++] = serializationNode;
	}

	public @NonNull Idiom getIdiom() {
		return idiom;
	}

	public boolean installIn(@NonNull Map<@NonNull SerializationNode, @NonNull SubIdiom> serializationNode2subIdiom) {
		if (isMatched()) {
			for (@NonNull SerializationNode serializationNode : matchNodes) {
				if (serializationNode2subIdiom.get(serializationNode) != null) {
					return false;
				}
			}
		}
		if ((subMatch != null) && !subMatch.installIn(serializationNode2subIdiom)) {
			return false;
		}
		if (isMatched()) {
			for (int i = 0; i < matchNodes.length; i++) {
				SerializationNode serializationNode = matchNodes[i];
				serializationNode2subIdiom.put(serializationNode, idiom.getSubidiom(i));
			}
		}
		return true;
	}

	public boolean isMatched() {
		if (subIdiomIndex < matchNodes.length) {
			return false;
		}
		if (subMatch != null) {
			assert subMatch.isMatched();
		}
		return true;
	}

	public void nextMatch(@NonNull SerializationNode serializationNode) {
		if ((subMatch != null) && !subMatch.isMatched()) {											// Pass down to active sub-match
			assert subMatch != null;
			subMatch.nextMatch(serializationNode);
		}
		else if (idiom.getSubidiom(0).matches(serializationNode)) {									// Look to nest a new sub-match
			subMatch = new IdiomMatch(idiom, serializationNode);
		}
		else if (!isMatched() && idiom.getSubidiom(subIdiomIndex).matches(serializationNode)) {		// Continue current match
			matchNodes[subIdiomIndex++] = serializationNode;
		}
		return;
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
			StringUtil.appendIndentation(s, depth, "\t");
			s.append(matchNodes[i]);
		}
		if (subMatch != null) {
			subMatch.toString(s, depth+1);
		}
	}
}
