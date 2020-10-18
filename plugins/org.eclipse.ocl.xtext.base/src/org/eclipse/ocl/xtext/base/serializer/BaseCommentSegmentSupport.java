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
package org.eclipse.ocl.xtext.base.serializer;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.xtext.serializer.CommentSegmentSupport;
import org.eclipse.ocl.examples.xtext.serializer.SerializationBuilder;
import org.eclipse.ocl.pivot.Comment;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.utilities.Pivotable;

public class BaseCommentSegmentSupport extends CommentSegmentSupport
{
	/**
	 * To preserve legacy testing functionality, empty comments use an abbreviated one line form. The following
	 * reserved string serves to distinguish the empty case from the no-comment case.
	 */
	private static final @NonNull String EMPTY_COMMENT = "/**/";

	public BaseCommentSegmentSupport() {
		super("/**", " * ", "*/");
	}

	@Override
	protected void appendComment(@NonNull SerializationBuilder serializationBuilder, @NonNull String comment) {
		if (comment == EMPTY_COMMENT) {		// NB == rather than equals() for private instance
			serializationBuilder.append(SerializationBuilder.HALF_NEW_LINE);
			serializationBuilder.append(EMPTY_COMMENT);
			serializationBuilder.append(SerializationBuilder.NEW_LINE);
		}
		else {
			super.appendComment(serializationBuilder, comment);
		}
	}

	@Override
	public @Nullable Iterable<@NonNull String> getComments(@NonNull EObject eObject) {
		if (eObject instanceof Pivotable) {
			Element asElement = ((Pivotable)eObject).getPivot();
			if (asElement != null) {
				List<Comment> ownedComments = asElement.getOwnedComments();
				if (!ownedComments.isEmpty()) {
					List<@NonNull String> comments = new ArrayList<>();
					for (Comment asComment: ownedComments) {
						String body = asComment.getBody();
						comments.add(body != null ? body : EMPTY_COMMENT);
					}
					return comments;
				}
			}
		}
		return null;
	}
}
