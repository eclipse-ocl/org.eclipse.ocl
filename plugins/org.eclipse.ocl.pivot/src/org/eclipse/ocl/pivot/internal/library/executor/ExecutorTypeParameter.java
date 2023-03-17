/*******************************************************************************
 * Copyright (c) 2011, 2018 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.internal.library.executor;

import java.util.List;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.CallExp;
import org.eclipse.ocl.pivot.Class;
import org.eclipse.ocl.pivot.CompleteInheritance;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.StandardLibrary;
import org.eclipse.ocl.pivot.TemplateParameter;
import org.eclipse.ocl.pivot.TemplateSignature;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.flat.FlatClass;
import org.eclipse.ocl.pivot.ids.IdManager;
import org.eclipse.ocl.pivot.ids.IdResolver;
import org.eclipse.ocl.pivot.ids.OperationId;
import org.eclipse.ocl.pivot.ids.TemplateParameterId;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.elements.AbstractExecutorNamedElement;
import org.eclipse.ocl.pivot.library.LibraryFeature;
import org.eclipse.ocl.pivot.values.OCLValue;

public class ExecutorTypeParameter extends AbstractExecutorNamedElement implements CompleteInheritance, TemplateParameter
{
	private final @NonNull TemplateParameterId typeid;		// FIXME probably only need the index
	protected /*final*/ /*@NonNull*/ FlatClass flatClass = null;

	@Deprecated /* @deprecated use index */
	public ExecutorTypeParameter(@NonNull TemplateParameterId typeid, @NonNull String name) {
		super(name);
		this.typeid = typeid;
	}

	/**
	 * @since 1.18
	 */
	public ExecutorTypeParameter(int index, @NonNull String name) {
		super(name);
		this.typeid = IdManager.getTemplateParameterId(index);
	}

	@Override
	public boolean conformsTo(@NonNull StandardLibrary standardLibrary, @NonNull Type thatType) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Type flattenedType() {
		throw new UnsupportedOperationException();
	}

	@Override
	public @NonNull Type getCommonType(@NonNull IdResolver idResolver, @NonNull Type thatType) {
		throw new UnsupportedOperationException();
	}

	@Override
	public @NonNull List<Class> getConstrainingClasses() {
		throw new UnsupportedOperationException();
	}

	@Override
	public @NonNull FlatClass getFlatClass() {
		assert flatClass != null;
		return flatClass;
	}

	@Override
	public @NonNull FlatClass getFlatClass(@NonNull StandardLibrary standardLibrary) {
		assert flatClass != null;
		return flatClass;
	}

	@Override
	public @Nullable Operation getMemberOperation(@NonNull OperationId id) {
		throw new UnsupportedOperationException();
	}

	@Override
	public org.eclipse.ocl.pivot.@NonNull Class getNormalizedType( @NonNull StandardLibrary standardLibrary) {
		throw new UnsupportedOperationException();
	}

	@Override
	public TemplateSignature getOwningSignature() {
		throw new UnsupportedOperationException();
	}

	@Override
	public org.eclipse.ocl.pivot.@NonNull Class getPivotClass() {
		throw new UnsupportedOperationException();
	}

	@Override
	public @NonNull TemplateParameterId getTemplateParameterId() {
		return typeid;
	}

	@Override
	public @NonNull TemplateParameterId getTypeId() {
		return typeid;
	}

	@Override
	public org.eclipse.ocl.pivot.@Nullable Class isClass() {
		return null;
	}

	@Override
	public boolean isEqualTo(@NonNull StandardLibrary standardLibrary, @NonNull Type thatType) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isEqualToUnspecializedType(@NonNull StandardLibrary standardLibrary, @NonNull Type type) {
		throw new UnsupportedOperationException();
	}

	@Override
	public @NonNull TemplateParameter isTemplateParameter() {
		return this;
	}

	public @NonNull Operation lookupActualOperation(@NonNull StandardLibrary standardLibrary, @NonNull Operation apparentOperation) {
		return flatClass.lookupActualOperation(standardLibrary, apparentOperation);
	}

	public @NonNull LibraryFeature lookupImplementation(@NonNull StandardLibrary standardLibrary, @NonNull Operation apparentOperation) {
		return flatClass.lookupImplementation(standardLibrary, apparentOperation);
	}

	@Override
	public boolean oclEquals(@NonNull OCLValue thatValue) {
		if (!(thatValue instanceof Type)) {
			return false;
		}
		TypeId thisTypeId = getTypeId();
		TypeId thatTypeId = ((Type)thatValue).getTypeId();
		return thisTypeId.equals(thatTypeId);
	}

	@Override
	public int oclHashCode() {
		return getTypeId().hashCode();
	}

	public void setFlatClass(@NonNull FlatClass flatClass) {
		this.flatClass = flatClass;
	}

	@Override
	public void setOwningSignature(TemplateSignature value) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Type specializeIn(CallExp expr, Type selfType) {
		throw new UnsupportedOperationException();
	}
}