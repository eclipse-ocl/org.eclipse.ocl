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

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.xtext.base.cs2text.MultiplicativeCardinality;
import org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleAnalysis;
import org.eclipse.xtext.Alternatives;
import org.eclipse.xtext.CompoundElement;

/**
 * A SerializationElement identifies the result of the conversion of an AbstractElement ParserRule sub-tree to
 * its SerializationRule sub-tree.
 *
 * Derived classes support:
 *
 * - NullSerializationNode - no content#
 * - SerializationNode a frozen tree element
 * - ListOfSerializationNode a SequenceSerializationNode future
 * - ListListOfSerializationNode a disjunction of SequenceSerializationNode futures
 */
public interface SerializationElement
{
	@NonNull SerializationElement addConcatenation(@NonNull SerializationElement additionalSerializationElement);
	@NonNull ListOfSerializationNode asList();
	@NonNull ListOfListOfSerializationNode asListOfList();
	@NonNull SerializationNode asNode();
	@NonNull SerializationNode freezeAlternatives(@NonNull ParserRuleAnalysis ruleAnalysis, @NonNull Alternatives alternatives);
	@NonNull SerializationElement freezeSequences(@NonNull ParserRuleAnalysis ruleAnalysis, @NonNull CompoundElement compoundElement);
	@NonNull MultiplicativeCardinality getMultiplicativeCardinality();
	boolean isList();
	boolean isListOfList();
	boolean isNode();
	boolean isNull();
	@NonNull SerializationElement setMultiplicativeCardinality(@NonNull MultiplicativeCardinality multiplicativeCardinality);
	void toString(@NonNull StringBuilder s, int depth);
}