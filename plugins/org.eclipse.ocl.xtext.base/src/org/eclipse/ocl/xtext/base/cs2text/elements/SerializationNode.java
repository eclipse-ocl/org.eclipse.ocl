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
import org.eclipse.ocl.xtext.base.cs2text.PreSerializer;
import org.eclipse.ocl.xtext.base.cs2text.SerializationBuilder;
import org.eclipse.ocl.xtext.base.cs2text.Serializer;
import org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleAnalysis;

public interface SerializationNode extends SerializationElement
{
	/**
	 * Ceate a shallow copy of this node with a changed multiplicativeCardinality.
	 */
	@NonNull SerializationNode clone(@NonNull MultiplicativeCardinality multiplicativeCardinality);
	@NonNull ParserRuleAnalysis getRuleAnalysis();
	void preSerialize(@NonNull PreSerializer preSerialize);
	void serialize(@NonNull Serializer serializer, @NonNull SerializationBuilder serializationBuilder);
//	@NonNull String toRuleString(@NonNull StringBuilder s);
}