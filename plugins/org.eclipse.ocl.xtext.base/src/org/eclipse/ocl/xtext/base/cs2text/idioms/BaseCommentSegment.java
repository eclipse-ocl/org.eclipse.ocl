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
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.Comment;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.utilities.Pivotable;
import org.eclipse.ocl.xtext.base.cs2text.SerializationBuilder;

public class BaseCommentSegment extends AbstractCommentSegment
{
	private static final @NonNull String EMPTY_COMMENT = "£$%^&*";

	public BaseCommentSegment() {
		super("/**", " * ", " */");
	}

	@Override
	protected void appendComment(@NonNull SerializationBuilder serializationBuilder, @NonNull String comment) {
		if (comment == EMPTY_COMMENT) {		// NB == rather than equals() for private instance
			serializationBuilder.append(SerializationBuilder.HALF_NEW_LINE);
			serializationBuilder.append("/**/");
			serializationBuilder.append(SerializationBuilder.NEW_LINE);
		}
		else {
			super.appendComment(serializationBuilder, comment);
		}
	}

	@Override
	protected @Nullable String getComment(@NonNull EObject eObject) {
		if (eObject instanceof Pivotable) {
			Element asElement = ((Pivotable)eObject).getPivot();
			if (asElement != null) {
				for (Comment asComment: asElement.getOwnedComments()) {
					String body = asComment.getBody();
					return body != null ? body : EMPTY_COMMENT;
				}
			}
		}
		return null;
	}
}