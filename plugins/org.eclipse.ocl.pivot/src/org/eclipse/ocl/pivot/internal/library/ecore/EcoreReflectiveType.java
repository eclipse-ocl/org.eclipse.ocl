/*******************************************************************************
 * Copyright (c) 2011, 2021 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.internal.library.ecore;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.CompleteInheritance;
import org.eclipse.ocl.pivot.Constraint;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.StandardLibrary;
import org.eclipse.ocl.pivot.TemplateParameter;
import org.eclipse.ocl.pivot.TemplateParameters;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.flat.FlatClass;
import org.eclipse.ocl.pivot.ids.IdResolver;
import org.eclipse.ocl.pivot.ids.OperationId;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.elements.AbstractExecutorClass;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.utilities.TypeUtil;
import org.eclipse.ocl.pivot.values.OCLValue;

public class EcoreReflectiveType extends AbstractExecutorClass
{
	public static final @NonNull List<CompleteInheritance> EMPTY_INHERITANCES = Collections.emptyList();
	protected final @NonNull EcoreReflectivePackage evaluationPackage;
	protected final @NonNull EClassifier eClassifier;
	protected final @NonNull TemplateParameters typeParameters;

	public EcoreReflectiveType(@NonNull EcoreReflectivePackage evaluationPackage, int flags, @NonNull EClassifier eClassifier, @NonNull TemplateParameter @NonNull ... typeParameters) {
		super(NameUtil.getName(eClassifier), flags);
		this.evaluationPackage = evaluationPackage;
		this.eClassifier = eClassifier;
		this.typeParameters = TypeUtil.createTemplateParameters(typeParameters);
	}

	@Override
	public boolean conformsTo(@NonNull StandardLibrary standardLibrary, @NonNull Type type) {
		Class<?> instanceClass = eClassifier.getInstanceClass();
		org.eclipse.ocl.pivot.@Nullable Class behavioralClass = instanceClass != null ? PivotUtil.getBehavioralClass(standardLibrary, instanceClass) : null;
		FlatClass behavioralFlatClass = behavioralClass != null ? behavioralClass.getFlatClass(standardLibrary) :  null;
		FlatClass thatFlatClass = type.getFlatClass(standardLibrary);
		if (behavioralFlatClass == thatFlatClass) {
			return true;
		}
		FlatClass thisFlatClass = this.getFlatClass(standardLibrary);
		return thatFlatClass.isSuperFlatClassOf(thisFlatClass);
	}

	@Override
	public @NonNull EObject createInstance() {
		if (eClassifier instanceof EClass) {
			EClass eClass = (EClass)eClassifier;
			EObject element = eClass.getEPackage().getEFactoryInstance().create(eClass);
			//			TypeId typeId = IdManager.INSTANCE.getTypeId(eClass);
			return /*ValuesUtil.createObjectValue(typeId,*/ ClassUtil.nonNullEMF(element); //);
		}
		throw new UnsupportedOperationException();
	}

	@Override
	public @NonNull Object createInstance(@NonNull String value) {
		if (eClassifier instanceof EDataType) {
			EDataType eDataType = (EDataType)eClassifier;
			Object element = eDataType.getEPackage().getEFactoryInstance().createFromString(eDataType, value);
			return ClassUtil.nonNullEMF(element);
		}
		throw new UnsupportedOperationException();
	}

	@Override
	public org.eclipse.ocl.pivot.Class flattenedType() {
		return this;
	}

	@Override
	public @NonNull Type getCommonType(@NonNull IdResolver idResolver, @NonNull Type type) {
		if (this == type) {
			return this.getPivotClass();
		}
		FlatClass firstFlatClass = this.getFlatClass();
		FlatClass secondFlatClass = type.getFlatClass(idResolver.getStandardLibrary());
		FlatClass commonFlatClass = firstFlatClass.getCommonFlatClass(secondFlatClass);
		return commonFlatClass.getPivotClass();
	}

	public final @NonNull EClassifier getEClassifier() {
		return eClassifier;
	}

	@Override
	public EObject getESObject() {
		return eClassifier;
	}

	@Override
	public org.eclipse.ocl.pivot.@NonNull Class getNormalizedType(@NonNull StandardLibrary standardLibrary) {
		return getPivotClass();
	}

	@Override
	public org.eclipse.ocl.pivot.@NonNull Package getOwningPackage() {
		return evaluationPackage;
	}

	@Override
	public @NonNull List<org.eclipse.ocl.pivot.Class> getSuperClasses() {
		throw new UnsupportedOperationException();		// FIXME
	}

	@Override
	public @Nullable Operation getMemberOperation(@NonNull OperationId operationId) {
		throw new UnsupportedOperationException();					// FIXME
	}

	@Override
	public @NonNull String getMetaTypeName() {
		return ClassUtil.nonNullPivot(eClassifier.getName());
	}

	@Override
	public @NonNull List<Constraint> getOwnedConstraints() {
		throw new UnsupportedOperationException();			// FIXME
	}

	@Override
	public @NonNull List<Constraint> getOwnedInvariants() {
		throw new UnsupportedOperationException();			// FIXME
	}

	@Override
	public org.eclipse.ocl.pivot.@NonNull Class getPivotClass() {
		return this;
	}

	@Override
	public @NonNull TypeId getTypeId() {
		return getOwningPackage().getPackageId().getClassId(name, getPivotClass().getTypeParameters().parametersSize());			// FIXME DataTypeId alternative
	}

	@Override
	public @NonNull TemplateParameters getTypeParameters() {
		return typeParameters;
	}

	@Override
	public org.eclipse.ocl.pivot.@NonNull Class isClass() {
		return getPivotClass();
	}

	@Override
	public boolean isEqualTo(@NonNull StandardLibrary standardLibrary, @NonNull Type type) {
		return getPivotClass() == type;
	}

	@Override
	public boolean isEqualToUnspecializedType(@NonNull StandardLibrary standardLibrary, @NonNull Type type) {
		return getPivotClass() == type;
	}

	@Override
	public boolean isOrdered() {
		return getFlatClass().isOrdered();
	}

	@Override
	public boolean isUnique() {
		return getFlatClass().isUnique();
	}

	@Override
	public @Nullable TemplateParameter isTemplateParameter() {
		return null;
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

	@Override
	public boolean validateNameIsNotNull(DiagnosticChain diagnostics, Map<Object, Object> context) {
		throw new UnsupportedOperationException();
	}
}
