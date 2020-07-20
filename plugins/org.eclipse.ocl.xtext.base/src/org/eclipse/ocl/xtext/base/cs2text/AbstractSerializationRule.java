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

import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.xtext.base.cs2text.elements.AssignedSerializationNode;
import org.eclipse.ocl.xtext.base.cs2text.elements.SequenceSerializationNode;
import org.eclipse.ocl.xtext.base.cs2text.elements.SerializationNode;
import org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleAnalysis;

public abstract class AbstractSerializationRule implements SerializationRule
{
	protected final @NonNull ParserRuleAnalysis ruleAnalysis;
	protected final @NonNull SerializationNode rootSerializationNode;
	private @Nullable EClass producedEClass = null;

	protected AbstractSerializationRule(@NonNull ParserRuleAnalysis ruleAnalysis, @NonNull SerializationNode rootSerializationNode) {
		this.ruleAnalysis = ruleAnalysis;
		this.rootSerializationNode = rootSerializationNode;
	}

	@Override
	public @NonNull String getName() {
		return ruleAnalysis.getName();
	}

	@Override
	public @NonNull EClass getProducedEClass() {
		EClass producedEClass2 = producedEClass;
		if (producedEClass2  == null) {
			producedEClass2 = getProducedEClass(Collections.singletonList(rootSerializationNode));
			if (producedEClass2 == null) {
				producedEClass2 = ruleAnalysis.getReturnedEClass();
			}
			producedEClass = producedEClass2;
			if ("EnumerationCS".equals(producedEClass2.getName()) ) {		// XXX debugging
				getProducedEClass(Collections.singletonList(rootSerializationNode));
				getClass();
			}
		}
		return producedEClass2;
	}

	private @Nullable EClass getProducedEClass(@NonNull List<@NonNull SerializationNode> serializationNodes) {
		EClass producedEClass = null;
		for (@NonNull SerializationNode serializationNode : serializationNodes) {
			EClass nestedEClass = null;
			if (serializationNode instanceof AssignedSerializationNode) {
				nestedEClass = ((AssignedSerializationNode)serializationNode).getEFeatureScope();
			}
			else if (serializationNode instanceof SequenceSerializationNode) {
				nestedEClass = getProducedEClass(((SequenceSerializationNode)serializationNode).getSerializationNodes());
			}
			if (nestedEClass != null) {
				if ((producedEClass == null) || producedEClass.isSuperTypeOf(nestedEClass)) {
					producedEClass = nestedEClass;
				}
				else {
					assert nestedEClass.isSuperTypeOf(producedEClass);
				}
			}
		}
		return producedEClass;
	}

	@Override
	public @NonNull SerializationNode getRootSerializationNode() {
		return rootSerializationNode;
	}

	@Override
	public void toRuleString(@NonNull StringBuilder s) {
		rootSerializationNode.toString(s, -1);
	}

	@Override
	public final @NonNull String toString() {
		StringBuilder s = new StringBuilder();
		toString(s, 0);
		return String.valueOf(s);
	}
}