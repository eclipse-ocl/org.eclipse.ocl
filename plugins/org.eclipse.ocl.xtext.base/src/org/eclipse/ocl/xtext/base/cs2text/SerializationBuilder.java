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

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;

public class SerializationBuilder
{
	private static final @NonNull Character SOFT_SPACE = new Character(Character.highSurrogate(' '));

	protected final @NonNull UserModelAnalysis modelAnalysis;
	protected final @NonNull StringBuilder s;
	private final int startIndex;
	protected final @NonNull EObject element;
	private @Nullable Map<@NonNull EStructuralFeature, @NonNull Integer> feature2consumptions = null;

	public SerializationBuilder(@NonNull UserModelAnalysis modelAnalysis, @NonNull StringBuilder s, @NonNull EObject element) {
		this.modelAnalysis = modelAnalysis;
		this.s = s;
		this.startIndex = s.length();
		this.element = element;
	}

	public void append(@NonNull String string) {
		s.append(string);
	}

	public void appendSoftSpace() {
		s.append(SOFT_SPACE);
	}

	/**
	 * Return the consumption index of the next feature slot.
	 */
	public int consume(@NonNull EStructuralFeature feature) {
		Map<@NonNull EStructuralFeature, @NonNull Integer> feature2consumptions = this.feature2consumptions;
		if (feature2consumptions == null) {
			this.feature2consumptions = feature2consumptions = new HashMap<>();
		}
		Integer count = feature2consumptions.get(feature);
		if (count == null) {
			feature2consumptions.put(feature, Integer.valueOf(1));
			return 0;
		}
		else {
			int intValue = count.intValue();
			feature2consumptions.put(feature, Integer.valueOf(intValue+1));
			return intValue;
		}
	}

	public @Nullable SerializationNode getAlternative(@NonNull AlternativesSerializationNode alternativesSerializationNode) {
		// TODO Auto-generated method stub
		return null;
	}

	public @NonNull EObject getElement() {
		return element;
	}

	public void serialize() {
		modelAnalysis.serialize(this, element);
	}

	public void serialize(@NonNull EObject element) {
		new SerializationBuilder(modelAnalysis, s, element).serialize();
	}

	public @NonNull String toRenderedString() {
		StringBuilder s = new StringBuilder();
		for (int i = 0; i < this.s.length(); i++) {
			char ch = this.s.charAt(i);
			int length = s.length();
			char prevCh = length <= 0 ? ' ' : s.charAt(length-1);
			switch (prevCh) {
			/*	case -1: {
					if (ch == SOFT_SPACE) {}
					else {
						s.append(ch);
					}
					break;
				} */
				case ' ': {
					if (ch == SOFT_SPACE) {}
					else {
						s.append(ch);
					}
					break;
				}
				case '\n': {
					if (ch == SOFT_SPACE) {}
					else {
						s.append(ch);
					}
					break;
				}
				default: {
					if (ch == SOFT_SPACE) {
						s.append(' ');
					}
					else {
						s.append(ch);
					}
					break;
				}
			}
		}
		return String.valueOf(s);
	}

	@Override
	public @NonNull String toString() {
		return String.valueOf(s.substring(startIndex));
	}

}