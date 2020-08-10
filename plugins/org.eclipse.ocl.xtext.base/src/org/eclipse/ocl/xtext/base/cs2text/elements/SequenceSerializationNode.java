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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.utilities.StringUtil;
import org.eclipse.ocl.xtext.base.cs2text.SerializationBuilder;
import org.eclipse.ocl.xtext.base.cs2text.user.UserElementSerializer;
import org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleAnalysis;
import org.eclipse.xtext.CompoundElement;

public class SequenceSerializationNode extends CompositeSerializationNode //implements List<@NonNull SerializationNode>
{
	protected final @NonNull CompoundElement compoundElement;
	protected final @NonNull List<@NonNull SerializationNode> serializationNodes;

	public SequenceSerializationNode(@NonNull CompoundElement compoundElement, @NonNull MultiplicativeCardinality multiplicativeCardinality, @NonNull List<@NonNull SerializationNode> groupSerializationNodes) {
		super(multiplicativeCardinality);
		this.compoundElement = compoundElement;
		this.serializationNodes = groupSerializationNodes;
		assert !groupSerializationNodes.isEmpty();
		assert multiplicativeCardinality.isOne() || noAssignedCurrent(this);
		assert noUnassignedParserRuleCall(this);
		assert groupSerializationNodes.size() == new HashSet<>(groupSerializationNodes).size();
	}

	public SequenceSerializationNode(@NonNull SequenceSerializationNode sequenceSerializationNode, @NonNull List<@NonNull SerializationNode> groupSerializationNodes) {
		super(sequenceSerializationNode.multiplicativeCardinality);
		this.compoundElement = sequenceSerializationNode.compoundElement;
		this.serializationNodes = groupSerializationNodes;
	//	assert !groupSerializationNodes.isEmpty();
	}

	/*	@Override
	public boolean add(@NonNull SerializationNode e) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void add(int index, @NonNull SerializationNode element) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean addAll(int index, Collection<? extends @NonNull SerializationNode> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean addAll(Collection<? extends @NonNull SerializationNode> c) {
		throw new UnsupportedOperationException();
	} */

/*	@Override
	public boolean add(@NonNull SerializationNode e) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void add(int index, @NonNull SerializationNode element) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean addAll(int index, Collection<? extends @NonNull SerializationNode> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean addAll(Collection<? extends @NonNull SerializationNode> c) {
		throw new UnsupportedOperationException();
	} */



/*	@Override
	public void clear() {
		throw new UnsupportedOperationException();
	} */

	@Override
	public @NonNull SerializationNode clone(@Nullable MultiplicativeCardinality multiplicativeCardinality) {
		List<@NonNull SerializationNode> newList = new ArrayList<>(serializationNodes.size());
		for (@NonNull SerializationNode serializationNode : serializationNodes) {
			newList.add(serializationNode.clone(null));
		}
		if (multiplicativeCardinality == null) multiplicativeCardinality = this.multiplicativeCardinality;
		return new SequenceSerializationNode(compoundElement, multiplicativeCardinality, newList);
	}

/*	@Override
	public boolean contains(Object o) {
		return serializationNodes.contains(o);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return serializationNodes.containsAll(c);
	} */

/*	@Override
	public @NonNull SerializationNode get(int index) {
		return serializationNodes.get(index);
	} */

	public @NonNull List<@NonNull SerializationNode> getSerializationNodes() {
		return serializationNodes;
	}

/*	@Override
	public int indexOf(Object o) {
		return serializationNodes.indexOf(o);
	}

	@Override
	public boolean isEmpty() {
		return serializationNodes.isEmpty();
	}

	@Override
	public @NonNull Iterator<@NonNull SerializationNode> iterator() {
		return serializationNodes.iterator();
	}

	@Override
	public int lastIndexOf(Object o) {
		return serializationNodes.lastIndexOf(o);
	}

	@Override
	public ListIterator<@NonNull SerializationNode> listIterator() {
		return serializationNodes.listIterator();
	}

	@Override
	public ListIterator<@NonNull SerializationNode> listIterator(int index) {
		return serializationNodes.listIterator(index);
	}

	@Override
	public boolean remove(Object o) {
		throw new UnsupportedOperationException();
	}

	@Override
	public @NonNull SerializationNode remove(int index) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		throw new UnsupportedOperationException();
	} */

	private boolean noAssignedCurrent(@NonNull SerializationNode serializationNode) {
		if (serializationNode instanceof AssignedCurrentSerializationNode) {
			return false;
		}
		else if (serializationNode instanceof SequenceSerializationNode) {
			for (@NonNull SerializationNode nestedSerializationNode : ((SequenceSerializationNode)serializationNode).getSerializationNodes()) {
				if (!noAssignedCurrent(nestedSerializationNode)) {
					return false;
				}
			}
		}
		else if (serializationNode.isList() || serializationNode.isListOfList() || serializationNode.isNull() || (serializationNode instanceof AlternativesSerializationNode)) {
			throw new UnsupportedOperationException();
		}
		return true;
	}

	private boolean noUnassignedParserRuleCall(@NonNull SerializationNode serializationNode) {
		if (serializationNode instanceof UnassignedRuleCallSerializationNode) {
			return !(((UnassignedRuleCallSerializationNode)serializationNode).getCalledRuleAnalysis() instanceof ParserRuleAnalysis);
		}
		else if (serializationNode instanceof SequenceSerializationNode) {
			for (@NonNull SerializationNode nestedSerializationNode : ((SequenceSerializationNode)serializationNode).getSerializationNodes()) {
				if (!noUnassignedParserRuleCall(nestedSerializationNode)) {
					return false;
				}
			}
		}
		else if (serializationNode.isList() || serializationNode.isListOfList() || serializationNode.isNull() || (serializationNode instanceof AlternativesSerializationNode)) {
			throw new UnsupportedOperationException();
		}
		return true;
	}

	@Override
	public void serialize(@NonNull UserElementSerializer serializer, @NonNull SerializationBuilder serializationBuilder) {
		for (@NonNull SerializationNode serializationNode : serializationNodes) {
			serializer.serializeNode(serializationBuilder, serializationNode);
		}
	}

/*	@Override
	public @NonNull SerializationNode set(int index, @NonNull SerializationNode element) {
		throw new UnsupportedOperationException();
	}

	@Override
	public int size() {
		return serializationNodes.size();
	}

	@Override
	public List<@NonNull SerializationNode> subList(int fromIndex, int toIndex) {
		return serializationNodes.subList(fromIndex, toIndex);
	}

	@Override
	public Object[] toArray() {
		return serializationNodes.toArray();
	}

	@Override
	public <T> T @NonNull [] toArray(T @NonNull [] a) {
		return serializationNodes.toArray(a);
	} */

	@Override
	public void toString(@NonNull StringBuilder s, int depth) {
		s.append("{");
		for (@NonNull SerializationNode serializationNode : serializationNodes) {
			StringUtil.appendIndentation(s, depth);
			s.append(depth >= 0 ? "+\t" : " ");
			serializationNode.toString(s, depth >= 0 ? depth+1 : depth);
		}
		StringUtil.appendIndentation(s, depth);
		s.append(" }");
		appendCardinality(s, depth);
	}
}