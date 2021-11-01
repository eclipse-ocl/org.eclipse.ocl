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
	/**
	 * Return a, possibly this SerializationElement, that corresponds to this SerializationElement
	 * with an additionalSerializationElement concatenated to each its disjunct conjunctions.
	 *
	 * This is expected to be invoked as part of a one pass tree refiement and so this SerializationElement
	 * may be modified and re-used for the enhanced result.
	 */
	@NonNull SerializationElement addConcatenation(@NonNull SerializationElement additionalSerializationElement);

	/**
	 * Return this ListOfSerializationNode or throw an IllegalStateException.
	 */
	@NonNull ListOfSerializationNode asList();

	/**
	 * Return this ListListOfSerializationNode or throw an IllegalStateException.
	 */
	@NonNull ListOfListOfSerializationNode asListOfList();

	/**
	 * Return this SerializationNode or throw an IllegalStateException.
	 */
	@NonNull SerializationNode asNode();

	/**
	 * Eliminate all ListListOfSerializationNode (and ListOfSerializationNode) content returning an
	 * AlternativesSerializationNode if one is required to represent the disjunction of flattened conjunctions.
	 * Else return a SequenceSerializationNOde for the one alternative.
	 */
	@NonNull SerializationNode freezeAlternatives(@NonNull Alternatives alternatives);

	/**
	 * Promote all ListOfSerializationNode future sequences into frozen SequenceSerializationNOde with
	 * current action calls and unassigned rule calls rewritten. The return may be a ListListOfSerializationNode.
	 */
	@NonNull SerializationElement freezeSequences(@NonNull CompoundElement compoundElement, @NonNull GrammarCardinality grammarCardinality, boolean isRootAlternative);

	/**
	 * Return true if this is a ListOfSerializationNode
	 */
	boolean isList();

	/**
	 * Return true if this is a ListListOfSerializationNode
	 */
	boolean isListOfList();

	/**
	 * Return true if this is a SerializationNode
	 */
	boolean isNode();

	/**
	 * Return true if this is a NullSerializationNode
	 */
	boolean isNull();

	/**
	 * Return true if the node tree is free of UnassignedParserRuleCalls.
	 * @return
	 */
	boolean noUnassignedParserRuleCall();

	boolean onlyRootUnassignedSerializationRuleCall(boolean isRootAlternative);	// FIXME delete me

	/**
	 * Append a multi-line debug serialization to a StringBuilder with the specified ndentation depth.
	 */
	void toString(@NonNull DiagnosticStringBuilder s, int depth);

	/**
	 * Return an equivalent SerializationElement to this that supports a grammarCardinality or greater as part of a compoundElement.
	 * Returns this if existing cardinality is adequate, or a SequenceSerializationNode, clone with adjusted cardinality otherwise.
	 */
	@NonNull SerializationElement setGrammarCardinality(@NonNull CompoundElement compoundElement, @NonNull GrammarCardinality grammarCardinality);
}