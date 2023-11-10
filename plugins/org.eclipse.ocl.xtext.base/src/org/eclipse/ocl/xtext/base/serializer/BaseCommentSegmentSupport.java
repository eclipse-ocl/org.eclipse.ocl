/*******************************************************************************
 * Copyright (c) 2020, 2023 Willink Transformations and others.
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
import org.eclipse.ocl.pivot.Comment;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.utilities.Pivotable;
import org.eclipse.ocl.xtext.base.cs2as.CS2AS;
import org.eclipse.xtext.nodemodel.ICompositeNode;
import org.eclipse.xtext.nodemodel.ILeafNode;
import org.eclipse.xtext.nodemodel.INode;

public class BaseCommentSegmentSupport implements CustomSegmentSupport
{
	protected @Nullable String prologue = "/**";
	protected @Nullable String indentation = " * ";
	protected @Nullable String epilogue = " */";

	/**
	 * To preserve legacy testing functionality, empty comments use an abbreviated one line form. The following
	 * reserved string serves to distinguish the empty case from the no-comment case.
	 */
	private static final @NonNull String EMPTY_COMMENT = "/**/";

	protected void appendComment(@NonNull SerializationBuilder serializationBuilder, @NonNull String comment) {
		serializationBuilder.append(SerializationBuilder.HALF_NEW_LINE);
		if (comment == EMPTY_COMMENT) {		// NB == rather than equals() for private instance
			serializationBuilder.append(EMPTY_COMMENT);
		}
		else {
			serializationBuilder.append(prologue);
			serializationBuilder.append(SerializationBuilder.PUSH_NEXT);
			serializationBuilder.append(indentation);
			boolean hasLeadingNewLine = false;
			for (int start = 0; true; ) {
				int index = comment.indexOf('\n', start);
				if (index >= 0) {
					if (!hasLeadingNewLine) {
						hasLeadingNewLine = true;
						serializationBuilder.append(SerializationBuilder.NEW_LINE);
					}
					String line = comment.substring(start, index);
					assert line != null;
					serializationBuilder.append(line);
					serializationBuilder.append(SerializationBuilder.NEW_LINE);
					start = index+1;
				}
				else {
					if (!hasLeadingNewLine) {
						serializationBuilder.append(" ");
					}
					String line = comment.substring(start, comment.length());
					assert line != null;
					serializationBuilder.append(line);
					break;
				}
			}
			serializationBuilder.append(SerializationBuilder.POP);
			if (hasLeadingNewLine) {
				serializationBuilder.append(SerializationBuilder.NEW_LINE);
			}
			serializationBuilder.append(epilogue);
		}
		serializationBuilder.append(SerializationBuilder.NEW_LINE);
	}

	@Override
	public void format(@NonNull UserElementFormatter fomatter, @NonNull SerializationBuilder serializationBuilder) {
		EObject eObject = fomatter.getElement();
		INode node = fomatter.getNode();
		Iterable<@NonNull String> comments = getComments(eObject);
		assert comments == null;
		if (comments == null) {
			comments = getComments(node);
		}
		if (comments != null) {
			for (String comment : comments) {
				appendComment(serializationBuilder, comment);
			}
		}
	}

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

	public @Nullable Iterable<@NonNull String> getComments(@NonNull INode node) {
		if (node instanceof ICompositeNode) {
			List<ILeafNode> documentationNodes = CS2AS.getDocumentationNodes((ICompositeNode)node);
			if (documentationNodes != null) {
				int i = 0;
			/*	if ((documentationNodes.size() == 1) && "/** /".equals(documentationNodes.get(0).getText())) {
					Comment comment = PivotFactory.eINSTANCE.createComment();
					comment.setBody(null);
					ownedComments.add(comment);
					i = 1;
				}
				else { */
					List<String> documentationStrings = new ArrayList< >();
					for (ILeafNode documentationNode : documentationNodes) {
						String text = documentationNode.getText().replace("\r", "");
						if (text.startsWith("/*") && text.endsWith("*/")) {
							StringBuilder s = new StringBuilder();
							String contentString = text.substring(2, text.length()-2).trim();
							for (String string : contentString.split("\n")) {
								String trimmedString = string.trim();
								if (s.length() > 0) {
									s.append("\n");
								}
								s.append(trimmedString.startsWith("*") ? trimmedString.substring(1).trim() : trimmedString);
							}
							documentationStrings.add(s.toString());
						}
						else {
							documentationStrings.add(text.trim());
						}
					}
				/*	int iMax = Math.min(documentationStrings.size(), ownedComments.size());
					for (; i < iMax; i++) {
						String string = documentationStrings.get(i);
						if (string != null) {
							String trimmedString = string; //trimComments(string);
							Comment comment = ownedComments.get(i);
							if (!trimmedString.equals(comment.getBody())) {
								comment.setBody(trimmedString);
							}
						}
					}
					for ( ; i < documentationStrings.size(); i++) {
						String string = documentationStrings.get(i);
						if (string != null) {
							String trimmedString = string; //trimComments(string);
							Comment comment = PivotFactory.eINSTANCE.createComment();
							comment.setBody(trimmedString);
							ownedComments.add(comment);
						}
					}
				} */
				return documentationStrings;
			}
		}
		return null;
	}

	@Override
	public void serialize(int serializationStepIndex, @NonNull UserElementSerializer serializer, @NonNull SerializationBuilder serializationBuilder) {
		EObject eObject = serializer.getElement();
		Iterable<@NonNull String> comments = getComments(eObject);
		if (comments != null) {
			for (String comment : comments) {
				appendComment(serializationBuilder, comment);
			}
		}
	}
}
