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

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.utilities.Nameable;
import org.eclipse.ocl.xtext.base.cs2text.enumerations.EnumerationValue;
import org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleAnalysis;

public interface SerializationRule extends Nameable
{
	@Nullable Iterable<@NonNull AssignedSerializationNode> getAssignedSerializationNodes(@NonNull EReference eReference);
	@Nullable Set<@NonNull ParserRuleAnalysis> getAssignedRuleAnalyses(@NonNull EReference eReference);
	@NonNull BasicSerializationRule getBasicSerializationRule();
	@Nullable Set<@NonNull EnumerationValue> getEnumerationValues(@NonNull EAttribute eAttribute);
	@Override
	@NonNull String getName();
	@NonNull EClass getProducedEClass();
	@NonNull SerializationNode getRootSerializationNode();
	@NonNull ParserRuleAnalysis getRuleAnalysis();
	void toRuleString(@NonNull StringBuilder s);
	void toSolutionString(@NonNull StringBuilder s, int depth);
	void toString(@NonNull StringBuilder s, int depth);
}
