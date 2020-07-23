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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.Comment;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.utilities.Pivotable;
import org.eclipse.ocl.xtext.base.cs2text.SerializationBuilder;
import org.eclipse.ocl.xtext.base.cs2text.Serializer;
import org.eclipse.ocl.xtext.base.cs2text.elements.SerializationNode;

public class BaseCommentSegment implements Segment
{
	@Override
	public void serialize(@NonNull SerializationNode serializationNode, @NonNull Serializer serializer, @NonNull SerializationBuilder serializationBuilder) {
		EObject eObject = serializer.getElement();
		if (eObject instanceof Pivotable) {
			Element asElement = ((Pivotable)eObject).getPivot();
			if (asElement != null) {
				for (Comment asComment: asElement.getOwnedComments()) {
					serializationBuilder.append(SerializationBuilder.HALF_NEW_LINE);
					serializationBuilder.append("/**");
					serializationBuilder.append(SerializationBuilder.PUSH_NEXT);
					serializationBuilder.append(" * ");
					serializationBuilder.append(SerializationBuilder.NEW_LINE);
					serializationBuilder.append(String.valueOf(asComment.getBody()));
					serializationBuilder.append(SerializationBuilder.POP);
					serializationBuilder.append(SerializationBuilder.NEW_LINE);
					serializationBuilder.append(" */");
					serializationBuilder.append(SerializationBuilder.NEW_LINE);
				}
			}
		}
	}

	@Override
	public String toString() {
		return getClass().getSimpleName();
	}
}
