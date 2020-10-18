/**
 * Copyright (c) 2020 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 */
package org.eclipse.ocl.examples.xtext.serializer;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;


/**
 * CommentSegmentSupport defines the framework for a user-defined class that supports
 * comment insertion. The derived class must implement getComment to locate the comment.
 */
public abstract class CommentSegmentSupport implements CustomSegmentSupport
{
	protected @Nullable String epilogue = null;
	protected @Nullable String indentation = null;
	protected @Nullable String prologue = null;

	public CommentSegmentSupport(@Nullable String prologue, @Nullable String indentation, @Nullable String epilogue) {
		this.prologue = prologue;
		this.indentation = indentation;
		this.epilogue = epilogue;
	}

	protected void appendComment(@NonNull SerializationBuilder serializationBuilder, @NonNull String comment) {
		serializationBuilder.append(SerializationBuilder.HALF_NEW_LINE);
		serializationBuilder.append(prologue);
		serializationBuilder.append(SerializationBuilder.PUSH_NEXT);
		serializationBuilder.append(indentation);
		serializationBuilder.append(SerializationBuilder.NEW_LINE);
		serializationBuilder.append(comment);
		serializationBuilder.append(SerializationBuilder.POP);
		serializationBuilder.append(SerializationBuilder.NEW_LINE);
		serializationBuilder.append(epilogue);
		serializationBuilder.append(SerializationBuilder.NEW_LINE);
	}

	@Override
	public void format(@NonNull UserElementFormatter fomatter, @NonNull SerializationBuilder serializationBuilder) {
		EObject eObject = fomatter.getElement();
		String comment = getComment(eObject);
		if (comment != null) {
			appendComment(serializationBuilder, comment);
		}
	}

	protected abstract String getComment(@NonNull EObject eObject);

	public @Nullable String getEpilogue() {
		return epilogue;
	}

	public @Nullable String getIndentation() {
		return indentation;
	}

	public @Nullable String getPrologue() {
		return prologue;
	}

	@Override
	public void serialize(int serializationStepIndex, @NonNull UserElementSerializer serializer, @NonNull SerializationBuilder serializationBuilder) {
		EObject eObject = serializer.getElement();
		String comment = getComment(eObject);
		if (comment != null) {
			appendComment(serializationBuilder, comment);
		}
	}

	public void setEpilogue(@Nullable String epilogue) {
		this.epilogue = epilogue;
	}

	public void setIndentation(@Nullable String indentation) {
		this.indentation = indentation;
	}

	public void setPrologue(@Nullable String prologue) {
		this.prologue = prologue;
	}
}
