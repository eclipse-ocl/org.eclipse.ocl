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
package org.eclipse.ocl.examples.xtext.build.elements;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.examples.xtext.serializer.DiagnosticStringBuilder;
import org.eclipse.ocl.examples.xtext.serializer.GrammarCardinality;
import org.eclipse.xtext.Alternatives;
import org.eclipse.xtext.CompoundElement;

/**
 * A NullSerializationNode is used to indicate nothing to serialize avoiding the need for a genuinely
 * null value that confuses the XtextSwitch.
 */
public class NullSerializationNode extends AbstractSerializationElement
{
	public static final @NonNull NullSerializationNode INSTANCE = new NullSerializationNode();

	private NullSerializationNode() {}

	@Override
	public @NonNull SerializationElement addConcatenation(@NonNull SerializationElement additionalSerializationElement) {
		return additionalSerializationElement;
	}

	@Override
	public @NonNull SerializationNode freezeAlternatives(@NonNull Alternatives alternatives) {
		throw new IllegalStateException();
	}

	@Override
	public @NonNull SerializationElement freezeSequences(@NonNull CompoundElement compoundElement, @NonNull GrammarCardinality grammarCardinality, boolean isRootAlternative) {
		return this;
	}

	@Override
	public boolean isNull() {
		return true;
	}

	@Override
	public @NonNull SerializationNode setGrammarCardinality(@NonNull CompoundElement compoundElement, @NonNull GrammarCardinality grammarCardinality) {
		throw new UnsupportedOperationException();		// Surely never happens; maybe just return this
	}

	@Override
	public void toString(@NonNull DiagnosticStringBuilder s, int depth) {
		s.append("«null»");
	}
}