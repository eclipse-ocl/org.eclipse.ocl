/**
 * Copyright (c) 2010, 2020 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 */
package org.eclipse.ocl.pivot.internal.values;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.manager.SymbolicExecutor;
import org.eclipse.ocl.pivot.library.InvertibleLibraryOperation;
import org.eclipse.ocl.pivot.library.LibraryOperation;
import org.eclipse.ocl.pivot.values.SymbolicConstraint;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Symbolic Value</b></em>'.
 * @since 1.12
 * <!-- end-user-doc -->
 *
 * @generated
 */
public class SymbolicResultValueImpl extends SymbolicUnknownValueImpl {
	private static final long serialVersionUID = 1L;

	protected final @NonNull LibraryOperation operation;

	protected final @Nullable Object sourceValue;
	protected final @Nullable Object argumentValue;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public SymbolicResultValueImpl(@NonNull TypeId typeId, boolean mayBeNull, boolean mayBeInvalid,
			@NonNull LibraryOperation operation, @Nullable Object sourceValue, @Nullable Object argumentValue) {
		super(typeId, mayBeNull, mayBeInvalid);
		this.operation = operation;
		this.sourceValue = sourceValue;
		this.argumentValue = argumentValue;
	}

	@Override
	public void deduceFrom(@NonNull SymbolicExecutor symbolicExecutor, @NonNull SymbolicConstraint symbolicConstraint) {
		if (operation instanceof InvertibleLibraryOperation) {
			((InvertibleLibraryOperation)operation).deduceFrom(symbolicExecutor, this, symbolicConstraint);
		}
	}

	public @Nullable Object getArgumentValue() {
		return argumentValue;
	}

	public @Nullable Object getSourceValue() {
		return sourceValue;
	}

	@Override
	public void toString(@NonNull StringBuilder s, int lengthLimit) {
		s.append(operation.getClass().getSimpleName());
		s.append("(");
		s.append(sourceValue);
		s.append(",");
		s.append(argumentValue);
		s.append(")");
		s.append(":");
		super.toString(s, lengthLimit);
	}
}
