/*******************************************************************************
 * Copyright (c) 2020, 2025 Willink Transformations and others.
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
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.utilities.Pivotable;

public class BaseCommentSegmentSupport implements CommentSegmentSupport
{
	@Override
	public void appendBody(@NonNull SerializationBuilder serializationBuilder, @Nullable String body) {
		List<@NonNull String> bodyLines = new ArrayList<>();
		if (body != null) {
			for (@NonNull String bodyLine : body.split("\n", -1)) {		// Trailing blank line too.
				bodyLines.add(bodyLine);
			}
		}
		int bodyLineCount = bodyLines.size();
		if (bodyLineCount <= 0) {
			serializationBuilder.append("/**/");
		}
		else if (bodyLineCount == 1) {
			serializationBuilder.append("/* ");
			serializationBuilder.append(bodyLines.get(0));
			serializationBuilder.append(" */");
		}
		else if (bodyLines.get(0).startsWith("*")) {
			serializationBuilder.append("/*");
			serializationBuilder.append(bodyLines.get(0));
			serializationBuilder.append(SerializationBuilder.PUSH_NEXT);
			serializationBuilder.append(" *");
			for (int i = 1; i < bodyLineCount-1; i++) {
				serializationBuilder.append(SerializationBuilder.NEW_LINE);
				String line = bodyLines.get(i);
				if (line.length() > 0) {
					serializationBuilder.append(" ");
					serializationBuilder.append(line);
				}
			}
			serializationBuilder.append(SerializationBuilder.NEW_LINE);
			String lastLine = bodyLines.get(bodyLineCount-1);
			if (lastLine.length() > 0) {
				serializationBuilder.append(lastLine);
				if (!lastLine.endsWith("*")) {
					serializationBuilder.append(" ");
				}
				serializationBuilder.append("*");
			}
			serializationBuilder.append("/");
			serializationBuilder.append(SerializationBuilder.POP);
		}
		else {
			serializationBuilder.append("/* ");
			serializationBuilder.append(bodyLines.get(0));
			serializationBuilder.append(SerializationBuilder.PUSH_NEXT);
			serializationBuilder.append(" ");
			for (int i = 1; i < bodyLineCount-1; i++) {
				serializationBuilder.append(SerializationBuilder.NEW_LINE);
				String line = bodyLines.get(i);
				if (line.length() > 0) {
					serializationBuilder.append(line);
				}
			}
			serializationBuilder.append(SerializationBuilder.NEW_LINE);
			String lastLine = bodyLines.get(bodyLineCount-1);
			if (lastLine.length() > 0) {
				serializationBuilder.append(lastLine);
				serializationBuilder.append(" ");
			}
			serializationBuilder.append("*/");
			serializationBuilder.append(SerializationBuilder.POP);
		}
		serializationBuilder.append(SerializationBuilder.NEW_LINE);
	}

	@Override
	public void format(@NonNull UserElementFormatter formatter, @NonNull SerializationBuilder serializationBuilder) {
		formatter.addCommentSupport(this);	// Comments are formatted from their INode not from their SerializationSegment
	}

	@Override
	public void serialize(int serializationStepIndex, @NonNull UserElementSerializer serializer, @NonNull SerializationBuilder serializationBuilder) {
		EObject eObject = serializer.getElement();
		if (eObject instanceof Pivotable) {
			Element asElement = ((Pivotable)eObject).getPivot();
			if (asElement != null) {
				for (Comment asComment: PivotUtil.getOwnedComments(asElement)) {
					String body = asComment.getBody();
					appendBody(serializationBuilder, body);
				}
			}
		}
	}
}
