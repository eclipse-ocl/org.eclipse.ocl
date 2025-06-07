/*******************************************************************************
 * Copyright (c) 2011, 2018 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.manager;

import java.util.Collection;
import java.util.List;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.TupleType;
import org.eclipse.ocl.pivot.TypedElement;
import org.eclipse.ocl.pivot.ids.PartId;
import org.eclipse.ocl.pivot.ids.TupleTypeId;
import org.eclipse.ocl.pivot.values.TemplateParameterSubstitutions;

/**
 * TupleTypeManager encapsulates the knowledge about known tuple type creation and access.
 *
 * @since 7.0
 */
public interface TupleTypeManager
{
	boolean conformsToTupleType(@NonNull TupleType actualType, @Nullable TemplateParameterSubstitutions actualSubstitutions,
			@NonNull TupleType requiredType, @Nullable TemplateParameterSubstitutions requiredSubstitutions, boolean enforceNullity);

	void dispose();

	@Nullable TupleType getCommonTupleType(@NonNull TupleType leftType, @Nullable TemplateParameterSubstitutions leftSubstitutions,
			@NonNull TupleType rightType, @Nullable TemplateParameterSubstitutions rightSubstitutions);

	/**
	 * Return the named tuple typeId with the defined parts (which need not be alphabetically ordered).
	 */
	@NonNull TupleType getTupleType(@NonNull List<@NonNull PartId> partIds);

	@NonNull TupleType getTupleType(@NonNull TupleTypeId tupleTypeId);

	@NonNull TupleType getTupleType(@NonNull Collection<@NonNull? extends TypedElement> parts, @Nullable TemplateParameterSubstitutions usageBindings);
	@NonNull TupleType getTupleType(@NonNull TupleType type, @Nullable TemplateParameterSubstitutions usageBindings);	// FIXME Remove duplication, unify type/multiplicity

	boolean isEqualToTupleType(@NonNull TupleType leftTupleType, @NonNull TupleType rightTupleType);
}