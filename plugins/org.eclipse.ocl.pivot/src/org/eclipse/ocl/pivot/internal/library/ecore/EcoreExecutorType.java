/*******************************************************************************
 * Copyright (c) 2012, 2021 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.internal.library.ecore;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.Constraint;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.StandardLibrary;
import org.eclipse.ocl.pivot.TemplateParameter;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.flat.FlatClass;
import org.eclipse.ocl.pivot.flat.FlatFragment;
import org.eclipse.ocl.pivot.ids.BuiltInTypeId;
import org.eclipse.ocl.pivot.ids.IdManager;
import org.eclipse.ocl.pivot.ids.OperationId;
import org.eclipse.ocl.pivot.ids.PackageId;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.elements.AbstractExecutorClass;
import org.eclipse.ocl.pivot.internal.library.executor.ExecutorFragment;
import org.eclipse.ocl.pivot.internal.library.executor.ExecutorPackage;
import org.eclipse.ocl.pivot.internal.library.executor.ExecutorProperties;
import org.eclipse.ocl.pivot.internal.library.executor.ExecutorTypeArgument;
import org.eclipse.ocl.pivot.internal.library.executor.ExecutorTypeParameter;
import org.eclipse.ocl.pivot.oclstdlib.OCLstdlibTables;
import org.eclipse.ocl.pivot.types.TemplateParameters;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.values.OCLValue;

public class EcoreExecutorType extends AbstractExecutorClass implements ExecutorTypeArgument
{
	/**
	 * Depth ordered inheritance fragments. OclAny at depth 0, OclSelf at depth size-1.
	 */
	private @NonNull ExecutorFragment @Nullable [] fragments = null;

	/**
	 * The index in fragments at which inheritance fragments at a given depth start.
	 * depthIndexes[0] is always zero since OclAny is always at depth 0.
	 * depthIndexes[depthIndexes.length-2] is always depthIndexes.length-1 since OclSelf is always at depth depthIndexes.length-2.
	 * depthIndexes[depthIndexes.length-1] is always depthIndexes.length to provide an easy end stop.
	 */
	private int[] indexes = null;

	/**
	 * @since 7.0
	 */
	protected final org.eclipse.ocl.pivot.@NonNull Package evaluationPackage;
	private final @NonNull TemplateParameters templateParameters;
	private /*@LazyNonNull*/ ExecutorProperties allProperties;

	protected @Nullable EClassifier eClassifier;
	private @Nullable TypeId typeId = null;

	/**
	 * Construct an executable type descriptor in the absence of a known EClassifier. A subsequent
	 * call of {@link #initFragments(ExecutorFragment[], int[], EClassifier)} may define an EClassifier.
	 */
	public EcoreExecutorType(@NonNull String name, @NonNull ExecutorPackage evaluationPackage, int flags, @NonNull ExecutorTypeParameter @NonNull ... templateParameters) {
		super(name, flags);
		this.evaluationPackage = evaluationPackage;
		this.templateParameters = new TemplateParameters(templateParameters);
		this.eClassifier = null;
	}

	/**
	 * Construct an executable type descriptor in the absence of a known EClassifier. A subsequent
	 * call of {@link #initFragments(ExecutorFragment[], int[], EClassifier)} may define an EClassifier.
	 */
	public EcoreExecutorType(@NonNull BuiltInTypeId typeId, @NonNull ExecutorPackage evaluationPackage, int flags, @NonNull ExecutorTypeParameter @NonNull ... templateParameters) {
		super(typeId.getName(), flags);
		this.evaluationPackage = evaluationPackage;
		this.templateParameters = new TemplateParameters(templateParameters);
		this.eClassifier = null;
		this.typeId = typeId;
	}

	/**
	 * Construct an executable type descriptor for a known EClassifier.
	 */
	public EcoreExecutorType(/*@NonNull*/ EClassifier eClassifier, @NonNull EcoreExecutorPackage evaluationPackage, int flags, @NonNull ExecutorTypeParameter @NonNull ... templateParameters) {
		super(ClassUtil.requireNonNull(eClassifier.getName()), flags);
		this.evaluationPackage = evaluationPackage;
		this.templateParameters = new TemplateParameters(templateParameters);
		this.eClassifier = eClassifier;
	}

	@Override
	public @NonNull EObject createInstance() {
		EClassifier eClassifier2 = eClassifier;
		if (eClassifier2 instanceof EClass) {
			EClass eClass = (EClass)eClassifier2;
			EObject element = eClass.getEPackage().getEFactoryInstance().create(eClass);
//			TypeId typeId = IdManager.getTypeId(eClass);
			return /*ValuesUtil.createObjectValue(typeId, */ClassUtil.requireNonNull(element); //);
		}
		throw new UnsupportedOperationException();
	}

	@Override
	public @Nullable Object createInstance(@NonNull String value) {
		EClassifier eClassifier2 = eClassifier;
		if (eClassifier2 instanceof EDataType) {
			EDataType eDataType = (EDataType) eClassifier2;
			Object element = eDataType.getEPackage().getEFactoryInstance().createFromString(eDataType, value);
			return /*ValuesUtil.valueOf(*/ClassUtil.requireNonNull(element); //);
		}
		throw new UnsupportedOperationException();
	}

	@Override
	public org.eclipse.ocl.pivot.Class flattenedType() {
		return this;
	}

	/**
	 * @since 7.0
	 */
	@Override
	public final @NonNull FragmentIterable getAllProperSuperFragments() {
		@NonNull FlatFragment @NonNull [] fragments2 = ClassUtil.requireNonNull(fragments);
		return new FragmentIterable(fragments2, 0, fragments2.length-1);
	}

	/**
	 * @since 7.0
	 */
	@Override
	public @NonNull FragmentIterable getAllSuperFragments() {
		return new FragmentIterable(ClassUtil.requireNonNull(fragments));
	}

	@Override
	public int getDepth() {
		return indexes.length-2;
	}

	public final EClassifier getEClassifier() {
		return eClassifier;
	}

	@Override
	public EObject getESObject() {
		return eClassifier;
	}

	/**
	 * @since 7.0
	 */
	@Override
	public @NonNull FragmentIterable getFragments() {
		return new FragmentIterable(fragments != null ? fragments : new @NonNull FlatFragment[0]);
	}

	@Override
	public @NonNull ExecutorFragment getFragment(int fragmentNumber) {
		return ClassUtil.requireNonNull(fragments)[fragmentNumber];
	}

	@Override
	public int getIndex(int fragmentNumber) {
		return indexes[fragmentNumber];
	}

	@Override
	public int getIndexes(){
		return indexes.length;
	}

	@Override
	public @NonNull FlatClass getFlatClass(@NonNull StandardLibrary standardLibrary) {
		return this;
	}

	/**
	 * @since 7.0
	 */
	@Override
	public @Nullable Operation basicGetOperation(@NonNull OperationId operationId) {
		throw new UnsupportedOperationException();					// FIXME
	}

	/**
	 * @since 7.0
	 */
	@Override
	public @Nullable Property basicGetProperty(@NonNull String name) {
		ExecutorProperties allProperties2 = allProperties;
		if (allProperties2 == null) {
			allProperties = allProperties2 = new ExecutorProperties(this);
		}
		return allProperties2.getMemberProperty(name);
	}

	@Override
	public @NonNull String getMetaclassName() {
		if (eClassifier != null) {					// FIXME Enforce @NonNull
			return ClassUtil.requireNonNull(ClassUtil.requireNonNull(eClassifier).getName());
		}
		else {
			return getTypeId().getMetaclassName();
		}
	}

	@Override
	public @NonNull List<Constraint> getOwnedInvariants() {
		throw new UnsupportedOperationException();			// FIXME
	}

	@Override
	public @NonNull List<Property> getOwnedProperties() {
		return getSelfFragment().getLocalProperties();
	}

	@Override
	public @NonNull List<Operation> getOwnedOperations() {
		return getSelfFragment().getLocalOperations();
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

	@Override
	public @NonNull ExecutorFragment getSelfFragment() {
		return getFragment(ClassUtil.requireNonNull(fragments).length-1);
	}

	public @NonNull StandardLibrary getStandardLibrary() {
		return OCLstdlibTables.LIBRARY;
	}

	@Override
	public @NonNull List<org.eclipse.ocl.pivot.Class> getSuperClasses() {
		return getSelfFragment().getSuperClasses();
	}

	/**
	 * @since 7.0
	 */
	@Override
	public final @NonNull FragmentIterable getSuperFragments(int depth) {
		return new FragmentIterable(ClassUtil.requireNonNull(fragments), indexes[depth], indexes[depth+1]);
	}

//	public @NonNull TypeId getTypeId() {
//		throw new UnsupportedOperationException();					// FIXME
//	}

	@Override
	public @NonNull TemplateParameters getTemplateParameters() {
		return templateParameters;
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
						TemplateParameters templateParameters = getTemplateParameters();
						if (eClassifier instanceof EDataType) {
							typeId2 = packageTypeId.getDataTypeId(name, templateParameters.parametersSize());
						}
						else {
							typeId2 = packageTypeId.getClassId(name, templateParameters.parametersSize());
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
	public @NonNull EcoreExecutorType initFragments(@NonNull ExecutorFragment @NonNull [] fragments, int[] depthCounts, /*@NonNull*/ EClassifier eClassifier) {
		assert eClassifier != null;
		assert this.eClassifier == null;
		assert name.equals(eClassifier.getName());
		this.eClassifier = ClassUtil.requireNonNull(eClassifier);
		initFragments(fragments, depthCounts);
		return this;
	}

	public void initFragments(@NonNull ExecutorFragment @NonNull [] fragments, int[] depthCounts) {
		int[] indexes = new int[depthCounts.length+1];
		indexes[0] = 0;
		for (int i = 0; i <  depthCounts.length; i++) {
			indexes[i+1] = indexes[i] + depthCounts[i];
		}
		this.fragments = fragments;
		this.indexes = indexes;
	}

	@Override
	public org.eclipse.ocl.pivot.@NonNull Class isClass() {
		return this;
	}

	@Override
	public boolean isEqualToUnspecializedType(@NonNull StandardLibrary standardLibrary, @NonNull Type type) {
		return this == type;
	}

	@Override
	public boolean isOrdered() {
		return (flags & ORDERED) != 0;
	}

	@Override
	public @Nullable TemplateParameter isTemplateParameter() {
		return null;
	}

	@Override
	public boolean isUnique() {
		return (flags & UNIQUE) != 0;
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
