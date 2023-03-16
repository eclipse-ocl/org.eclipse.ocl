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
import org.eclipse.ocl.pivot.flat.FlatFragment;
import org.eclipse.ocl.pivot.ids.IdManager;
import org.eclipse.ocl.pivot.ids.IdResolver;
import org.eclipse.ocl.pivot.ids.OperationId;
import org.eclipse.ocl.pivot.ids.PackageId;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.elements.AbstractExecutorClass;
import org.eclipse.ocl.pivot.oclstdlib.OCLstdlibTables;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.TypeUtil;
import org.eclipse.ocl.pivot.values.OCLValue;

/**
 * An ExecutorType defines a Type using a compact representation suitable for efficient
 * execution and static construction.
 */
public class ExecutorType extends AbstractExecutorClass
{
	protected final org.eclipse.ocl.pivot.@NonNull Package evaluationPackage;
	private final @NonNull TemplateParameters typeParameters;
	protected @Nullable EClassifier eClassifier;
	private @Nullable TypeId typeId;

	/**
	 * Construct an executable type descriptor for a known EClassifier and TypeId.
	 */
	public ExecutorType(@NonNull EClassifier eClassifier, @NonNull ExecutorPackage evaluationPackage, @Nullable TypeId typeId, int flags, @NonNull TemplateParameter @NonNull ... typeParameters) {
		super(NameUtil.getName(eClassifier), flags);
		this.evaluationPackage = evaluationPackage;
		this.typeParameters = TypeUtil.createTemplateParameters(typeParameters);
		this.eClassifier = eClassifier;
		this.typeId = typeId;
	}

	@Override
	public boolean conformsTo(@NonNull StandardLibrary standardLibrary, @NonNull Type type) {
		if (this == type) {
			return true;
		}
		FlatClass thatFlatClass = type.getFlatClass(standardLibrary);
		FlatClass thisFlatClass = this.getFlatClass(standardLibrary);
		if (thisFlatClass == thatFlatClass) {
			return true;
		}
		return thatFlatClass.isSuperFlatClassOf(thisFlatClass);
	}

	@Override
	public @NonNull EObject createInstance() {
		EClassifier eClassifier2 = eClassifier;
		if (eClassifier2 instanceof EClass) {
			EClass eClass = (EClass)eClassifier2;
			EObject element = eClass.getEPackage().getEFactoryInstance().create(eClass);
//			TypeId typeId = IdManager.getTypeId(eClass);
			return /*ValuesUtil.createObjectValue(typeId, */ClassUtil.nonNullEMF(element); //);
		}
		throw new UnsupportedOperationException();
	}

	@Override
	public @Nullable Object createInstance(@NonNull String value) {
		EClassifier eClassifier2 = eClassifier;
		if (eClassifier2 instanceof EDataType) {
			EDataType eDataType = (EDataType) eClassifier2;
			Object element = eDataType.getEPackage().getEFactoryInstance().createFromString(eDataType, value);
			return /*ValuesUtil.valueOf(*/ClassUtil.nonNullEMF(element); //);
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

	public final EClassifier getEClassifier() {
		return eClassifier;
	}

	@Override
	public EObject getESObject() {
		return eClassifier;
	}

	@Override
	public @NonNull CompleteInheritance getInheritance(@NonNull StandardLibrary standardLibrary) {
		return this;
	}

	@Override
	public @Nullable Operation getMemberOperation(@NonNull OperationId operationId) {
		throw new UnsupportedOperationException();					// FIXME
	}

//	@Override
//	public @Nullable Property getMemberProperty(@NonNull String name) {
//		DomainProperties allProperties2 = allProperties;
//		if (allProperties2 == null) {
//			allProperties = allProperties2 = new DomainProperties(getFlatClass());
//		}
//		return allProperties2.getMemberProperty(name);
//	}

	@Override
	public @NonNull String getMetaTypeName() {
		if (eClassifier != null) {					// FIXME Enforce @NonNull
			return ClassUtil.nonNullModel(ClassUtil.nonNullState(eClassifier).getName());
		}
		else {
			return getTypeId().getMetaTypeName();
		}
	}

	@Override
	public org.eclipse.ocl.pivot.@NonNull Class getNormalizedType(@NonNull StandardLibrary standardLibrary) {
		return this;
	}

	@Override
	public @NonNull List<Constraint> getOwnedInvariants() {
		throw new UnsupportedOperationException();			// FIXME
	}

	@Override
	public org.eclipse.ocl.pivot.@NonNull Package getOwningPackage() {
		return evaluationPackage;
	}

	@Override
	public @NonNull List<Constraint> getOwnedConstraints() {
		throw new UnsupportedOperationException();			// FIXME
	}

	@Override
	public org.eclipse.ocl.pivot.@NonNull Class getPivotClass() {
		return this;
	}

	public @NonNull StandardLibrary getStandardLibrary() {
		return OCLstdlibTables.LIBRARY;
	}

	@Override
	public @NonNull List<org.eclipse.ocl.pivot.Class> getSuperClasses() {
		throw new UnsupportedOperationException();					// FIXME
//		return IterableAsImmutableList.asList(getFlatClass().getSelfFragment().getSuperClasses());
	}

	@Override
	public @NonNull TemplateParameters getTypeParameters() {
		return typeParameters;
	}

	@Override
	public @NonNull TypeId getTypeId() {
		TypeId typeId2 = typeId;
		if (typeId2 == null) {
			synchronized (this) {
				typeId2 = typeId;
				if (typeId2 == null) {
					EClassifier eClassifier2 = eClassifier;
					if (eClassifier2 != null) {
						typeId2 = IdManager.getTypeId(eClassifier2);
					}
					else {
						PackageId packageTypeId = evaluationPackage.getPackageId(); //IdManager.getPackageId(evaluationPackage);
						TemplateParameters typeParameters = getTypeParameters();
						if (eClassifier instanceof EDataType) {
							typeId2 = packageTypeId.getDataTypeId(name, typeParameters.parametersSize());
						}
						else {
							typeId2 = packageTypeId.getClassId(name, typeParameters.parametersSize());
						}
					}
					typeId = typeId2;
				}
			}
		}
		return typeId2;
	}

	/**
	 * Define the EClassifier associated with an executable type. This initialization may
	 * be performed once to allow an Ecore-aware package of type descriptors to re-use and
	 * enhance an Ecore-unaware package. This occurs for the PivotTables that enhance the
	 * OCLstdlibTables.
	 */
	public @NonNull ExecutorType initFragments(@NonNull FlatFragment @NonNull [] fragments, int[] depthCounts, /*@NonNull*/ EClassifier eClassifier) {
		assert eClassifier != null;
		assert this.eClassifier == null;
		assert name.equals(eClassifier.getName());
		this.eClassifier = ClassUtil.nonNullState(eClassifier);
		initFragments(fragments, depthCounts);
		return this;
	}

	@Override
	public void initFragments(@NonNull FlatFragment @NonNull [] fragments, int[] depthCounts) {
		getFlatClass().initFragments(fragments, depthCounts);;
	}

	@Override
	public org.eclipse.ocl.pivot.@NonNull Class isClass() {
		return this;
	}

	@Override
	public boolean isEqualTo(@NonNull StandardLibrary standardLibrary, @NonNull Type type) {
		return this == type;
	}

	@Override
	public boolean isEqualToUnspecializedType(@NonNull StandardLibrary standardLibrary, @NonNull Type type) {
		return this == type;
	}

	@Override
	public boolean isOrdered() {
		return getFlatClass().isOrdered();
	}

	@Override
	public @Nullable TemplateParameter isTemplateParameter() {
		return null;
	}

	@Override
	public boolean isUnique() {
		return getFlatClass().isUnique();
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
	public String toString() {
		if (evaluationPackage.getPackageId() != IdManager.METAMODEL) {
			return String.valueOf(evaluationPackage) + "::" + String.valueOf(name); //$NON-NLS-1$
		}
		else {
			return String.valueOf(name);
		}
	}
}