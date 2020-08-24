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

import java.util.Set;

import org.eclipse.emf.ecore.EReference;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.xtext.base.cs2text.SerializationBuilder;
import org.eclipse.ocl.xtext.base.cs2text.user.DynamicRuleMatch;
import org.eclipse.ocl.xtext.base.cs2text.user.UserElementSerializer;
import org.eclipse.ocl.xtext.base.cs2text.user.UserSlotsAnalysis;
import org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue;

public interface SerializationRule
{
	@Nullable Set<@NonNull ParserRuleValue> getAssignedRuleValues(@NonNull EReference eReference);
	@NonNull BasicSerializationRule getBasicSerializationRule();
	int getRuleValueIndex();
	@Nullable DynamicRuleMatch match(@NonNull UserSlotsAnalysis slotsAnalysis);
	void serializeRule(@NonNull UserElementSerializer serializer, @NonNull SerializationBuilder serializationBuilder);
	void serializeSubRule(int startIndex, int endIndex, @NonNull UserElementSerializer serializer, @NonNull SerializationBuilder serializationBuilder);
	void toRuleString(@NonNull StringBuilder s);
	void toSolutionString(@NonNull StringBuilder s, int depth);
	void toString(@NonNull StringBuilder s, int depth);
}
