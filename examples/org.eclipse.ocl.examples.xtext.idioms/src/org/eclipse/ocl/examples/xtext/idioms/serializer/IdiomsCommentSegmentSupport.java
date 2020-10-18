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
package org.eclipse.ocl.examples.xtext.idioms.serializer;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.xtext.serializer.CustomSegmentSupport;
import org.eclipse.ocl.examples.xtext.serializer.SerializationBuilder;
import org.eclipse.ocl.examples.xtext.serializer.UserElementFormatter;
import org.eclipse.ocl.examples.xtext.serializer.UserElementSerializer;
import org.eclipse.xtext.TerminalRule;
import org.eclipse.xtext.nodemodel.ICompositeNode;
import org.eclipse.xtext.nodemodel.ILeafNode;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;

public class IdiomsCommentSegmentSupport implements CustomSegmentSupport
{
	/**
	 * To preserve legacy testing functionality, empty comments use an abbreviated one line form. The following
	 * reserved string serves to distinguish the empty case from the no-comment case.
	 */
//	private static final @NonNull String EMPTY_COMMENT = "/**/";

	protected void appendComment(@NonNull SerializationBuilder serializationBuilder, @NonNull String comment) {
	//	if (comment == EMPTY_COMMENT) {		// NB == rather than equals() for private instance
	//		serializationBuilder.append(SerializationBuilder.HALF_NEW_LINE);
	//		serializationBuilder.append(EMPTY_COMMENT);
	//		serializationBuilder.append(SerializationBuilder.NEW_LINE);
	//	}
	//	else {
			serializationBuilder.append(SerializationBuilder.HALF_NEW_LINE);
			serializationBuilder.append("/*");
			serializationBuilder.append(SerializationBuilder.PUSH_NEXT);
			serializationBuilder.append(" *");
		//	if (!comment.startsWith("*")) {
		//		serializationBuilder.append(SerializationBuilder.NEW_LINE);
		//	}
//			serializationBuilder.append(comment);
			for (int start = 0; true; ) {
				int index = comment.indexOf('\n', start);
				String line = comment.substring(start, index >= 0 ? index : comment.length());
				assert line != null;
				serializationBuilder.append(line);
				if (index >= 0) {
					serializationBuilder.append(SerializationBuilder.NEW_LINE);
					start = index+1;
				}
				else {
					break;
				}
			}
			serializationBuilder.append(SerializationBuilder.POP);
		//	if (!comment.endsWith("*")) {
		//		serializationBuilder.append(SerializationBuilder.NEW_LINE);
		//		serializationBuilder.append(" */");
		//	}
		//	else {
				serializationBuilder.append("*/");
		//	}
			serializationBuilder.append(SerializationBuilder.NEW_LINE);
	//	}
	}

	protected @Nullable String getComment(@NonNull EObject eObject) {
		ICompositeNode node = NodeModelUtils.getNode(eObject);
		if (node == null) {
			return null;
		}
		List<ILeafNode> documentationNodes = getDocumentationNodes(node);
		if (documentationNodes == null) {
			return null;
		}
	//	else if ((documentationNodes.size() == 1) && "/**/".equals(documentationNodes.get(0).getText())) {
	//		return "";
	//	}
		else {
			StringBuilder s = new StringBuilder();
		//	List<String> documentationStrings = new ArrayList<String>();
			for (ILeafNode documentationNode : documentationNodes) {
				String text = documentationNode.getText().replace("\r", "");
				if ((text.length() >= 4) && text.startsWith("/*") && text.endsWith("*/")) {
					String contentString = text.substring(2, text.length()-2);//.trim();
					boolean firstLine = true;
					for (String string : contentString.split("\n")) {
					//	boolean startsWithStar = false;
						if (s.length() > 0) {
						//	startsWithStar = string.startsWith("*");
							s.append("\n");
						}
						s.append((!firstLine && string.startsWith(" *")) ? string.substring(2) : string);
						firstLine = false;
					}
				//	documentationStrings.add(s.toString());
				}
				else {
				//	documentationStrings.add(text.trim());
				}
			}
			return s.toString();
		}
	}

	protected @Nullable List<@NonNull ILeafNode> getDocumentationNodes(@NonNull ICompositeNode node) {
		List<@NonNull ILeafNode> documentationNodes = null;
		for (ILeafNode leafNode : node.getLeafNodes()) {
			EObject grammarElement = leafNode.getGrammarElement();
			if (!(grammarElement instanceof TerminalRule)) {
				break;
			}
			TerminalRule terminalRule = (TerminalRule) grammarElement;
			String name = terminalRule.getName();
			if ("WS".equals(name)) {
			}
			else if ("SL_COMMENT".equals(name)) {
			}
			else if ("ML_COMMENT".equals(name)) {
				if (documentationNodes == null) {
					documentationNodes = new ArrayList<>();
				}
				documentationNodes.add(leafNode);
			}
			else {
				break;
			}
		}
		return documentationNodes;
	}

	@Override
	public void format(@NonNull UserElementFormatter fomatter, @NonNull SerializationBuilder serializationBuilder) {
		EObject eObject = fomatter.getElement();
		String comment = getComment(eObject);
		if (comment != null) {
			appendComment(serializationBuilder, comment);
		}
	}

	@Override
	public void serialize(int thisSserializationStepIndex, @NonNull UserElementSerializer serializer, @NonNull SerializationBuilder serializationBuilder) {
		EObject eObject = serializer.getElement();
		String comment = getComment(eObject);
		if (comment != null) {
			appendComment(serializationBuilder, comment);
		}
	}
}
