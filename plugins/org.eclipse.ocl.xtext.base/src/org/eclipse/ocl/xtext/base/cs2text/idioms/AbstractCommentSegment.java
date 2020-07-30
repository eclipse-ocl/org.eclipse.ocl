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
import org.eclipse.ocl.xtext.base.cs2text.SerializationBuilder;
import org.eclipse.ocl.xtext.base.cs2text.Serializer;
import org.eclipse.ocl.xtext.base.cs2text.elements.SerializationNode;

public abstract class AbstractCommentSegment implements Segment
{
	protected final @NonNull String prologue;
	protected final @NonNull String indentation;
	protected final @NonNull String epilogue;

	protected AbstractCommentSegment(@NonNull String prologue, @NonNull String indentation, @NonNull String epilogue) {
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

	protected abstract @Nullable String getComment(@NonNull EObject eObject);

	@Override
	public void serialize(@NonNull SerializationNode serializationNode, @NonNull Serializer serializer, @NonNull SerializationBuilder serializationBuilder) {
		EObject eObject = serializer.getElement();
		String comment = getComment(eObject);
		if (comment != null) {
			appendComment(serializationBuilder, comment);
		}
	}

	@Override
	public String toString() {
		return getClass().getSimpleName();
	}
}
