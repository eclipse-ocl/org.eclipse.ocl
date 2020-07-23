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

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.xtext.base.cs2text.elements.AlternativeAssignedKeywordsSerializationNode;
import org.eclipse.ocl.xtext.base.cs2text.elements.AssignedCrossReferenceSerializationNode;
import org.eclipse.ocl.xtext.base.cs2text.elements.AssignedKeywordSerializationNode;
import org.eclipse.ocl.xtext.base.cs2text.elements.AssignedRuleCallSerializationNode;
import org.eclipse.ocl.xtext.base.cs2text.elements.BasicSerializationRule;
import org.eclipse.ocl.xtext.base.cs2text.elements.SerializationNode;
import org.eclipse.ocl.xtext.base.cs2text.elements.UnassignedKeywordSerializationNode;
import org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleAnalysis;

public class DefaultLocator implements Locator
{
	public static final @NonNull DefaultLocator INSTANCE = new DefaultLocator();

	private DefaultLocator() {}

	@Override
	public boolean matches(@NonNull SerializationNode serializationNode, @NonNull BasicSerializationRule serializationRule) {
		if (serializationNode instanceof AlternativeAssignedKeywordsSerializationNode) {
			return true;
		}
		else if (serializationNode instanceof AssignedCrossReferenceSerializationNode) {
			return true;
		}
		else if (serializationNode instanceof AssignedRuleCallSerializationNode) {
			return !(((AssignedRuleCallSerializationNode)serializationNode).getCalledRuleAnalysis() instanceof ParserRuleAnalysis);
		}
		else if (serializationNode instanceof AssignedKeywordSerializationNode) {
			return true;
		}
		else if (serializationNode instanceof UnassignedKeywordSerializationNode) {
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return "«default»";
	}
}
