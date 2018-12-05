/*******************************************************************************
 * Copyright (c) 2012, 2018 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.internal.ids;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.ids.BindingsId;
import org.eclipse.ocl.pivot.ids.IdVisitor;
import org.eclipse.ocl.pivot.ids.OperationId;
import org.eclipse.ocl.pivot.ids.ParametersId;
import org.eclipse.ocl.pivot.ids.TypeId;

public class GeneralizedOperationIdImpl extends AbstractGeneralizedIdImpl<OperationId> implements OperationId, WeakHashMapOfListOfWeakReference4.MatchableId<Integer, String, ParametersId>
{
	protected final @NonNull TypeId parentId;
	protected final @NonNull ParametersId parametersId;
	
	public GeneralizedOperationIdImpl(@NonNull Integer hashCode, @NonNull TypeId parentId, int templateParameters, @NonNull String name, @NonNull ParametersId parametersId) {
		super(hashCode, templateParameters, name);
		this.parentId = parentId;
		this.parametersId = parametersId;
	}

	@Override
	public <R> R accept(@NonNull IdVisitor<R> visitor) {
		return visitor.visitOperationId(this);
	}

	@Override
	protected @NonNull OperationId createSpecializedId(@NonNull BindingsId templateBindings) {
		return new SpecializedOperationIdImpl(this, templateBindings);
	}

	@Override
	public @NonNull String getDisplayName() {
		StringBuilder s = new StringBuilder();
//		if (templateParameters > 0) {
//			s.append("<" + templateParameters + ">");
//		}
		s.append(parentId);
		s.append("::");
		s.append(name);
		s.append(parametersId);
		return s.toString();
	}

	@Override
	public @NonNull OperationId getGeneralizedId() {
		return this;
	}

	@Override
	public @NonNull String getMetaTypeName() {
		return TypeId.OPERATION_NAME;
	}

	@Override
	public @NonNull ParametersId getParametersId() {
		return parametersId;
	}

	@Override
	public @NonNull TypeId getParent() {
		return parentId;
	}

	@Override
	public boolean matches(@NonNull Integer thoseTemplateParameters, @NonNull String thatName, @NonNull ParametersId thatParametersId) {
		if (this.parametersId != thatParametersId) {
			return false;
		}
		if (this.templateParameters != thoseTemplateParameters) {
			return false;
		}
		if (!this.name.equals(thatName)) {
			return false;
		}
		return true;
	}
}