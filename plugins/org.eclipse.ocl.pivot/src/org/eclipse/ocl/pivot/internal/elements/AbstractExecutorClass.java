/*******************************************************************************
 * Copyright (c) 2014, 2020 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.internal.elements;

import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.Behavior;
import org.eclipse.ocl.pivot.Class;
import org.eclipse.ocl.pivot.Constraint;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.StandardLibrary;
import org.eclipse.ocl.pivot.StereotypeExtender;
import org.eclipse.ocl.pivot.TemplateBinding;
import org.eclipse.ocl.pivot.TemplateParameter;
import org.eclipse.ocl.pivot.TemplateParameters;
import org.eclipse.ocl.pivot.TemplateSignature;
import org.eclipse.ocl.pivot.TemplateableElement;
import org.eclipse.ocl.pivot.flat.FlatClass;
import org.eclipse.ocl.pivot.flat.FlatFragment;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.AbstractClassImpl;
import org.eclipse.ocl.pivot.library.LibraryFeature;

import com.google.common.collect.Lists;

public abstract class AbstractExecutorClass extends AbstractClassImpl
{
	public static final int ORDERED = FlatClass.ORDERED;
	public static final int UNIQUE = FlatClass.UNIQUE;
	public static final int OCL_ANY = FlatClass.OCL_ANY;
	public static final int OCL_VOID = FlatClass.OCL_VOID;
	public static final int OCL_INVALID = FlatClass.OCL_INVALID;
	public static final int ABSTRACT = FlatClass.ABSTRACT;

	private /*final*/ /*@NonNull*/ FlatClass flatClass = null;

	public AbstractExecutorClass(@NonNull String name, int flags) {
		setName(name);
	}

	@Override
	public @NonNull EObject createInstance() {
		throw new UnsupportedOperationException();
	}

	@Override
	public @Nullable Object createInstance(@NonNull String value) {
		throw new UnsupportedOperationException();
	}

	@Override
	protected final EClass eStaticClass() {
		return super.eStaticClass();		// FIXME Bug 577889 The direct CGed Executor has no eClass()
	}

	public org.eclipse.ocl.pivot.Class getBehavioralClass() {
		return null;
	}

	@Override
	public @NonNull List<StereotypeExtender> getExtenders() {
		throw new UnsupportedOperationException();
	}

//	@Override
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
	public String getInstanceClassName() {
		throw new UnsupportedOperationException();
	}

	@Override
	public @NonNull String getMetaTypeName() {
		return getTypeId().getMetaTypeName();
	}

	@Override
	public @NonNull Class getNormalizedType(@NonNull StandardLibrary standardLibrary) {
		throw new UnsupportedOperationException();
	}

	@Override
	@NonNull
	public List<Behavior> getOwnedBehaviors() {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<TemplateBinding> getOwnedBindings() {
		throw new UnsupportedOperationException();
	}

	@Override
	@NonNull
	public List<Constraint> getOwnedConstraints() {
		throw new UnsupportedOperationException();
	}

	@Override
	@NonNull
	public List<Constraint> getOwnedInvariants() {
		throw new UnsupportedOperationException();
	}

	@Override
	public final @NonNull List<Operation> getOwnedOperations() {
		@NonNull Operation @NonNull [] asOperations = flatClass.getSelfOperations();
		return Lists.newArrayList(asOperations);
	}

	@Override
	public final @NonNull List<Property> getOwnedProperties() {
		@NonNull Property @NonNull [] asProperties = flatClass.getSelfProperties();
		return Lists.newArrayList(asProperties);
	}

	@Override
	public TemplateSignature getOwnedSignature() {
		throw new UnsupportedOperationException();
	}

	@Override
	public org.eclipse.ocl.pivot.Package getOwningPackage() {
		throw new UnsupportedOperationException();
	}

	@Override
	@NonNull
	public List<org.eclipse.ocl.pivot.Class> getSuperClasses() {
		throw new UnsupportedOperationException();
	}

	@Override
	public @NonNull TypeId getTypeId() {
		throw new UnsupportedOperationException();
	}

	@Override
	@NonNull
	public TemplateParameters getTypeParameters() {
		return TemplateParameters.EMPTY_LIST;
		//		throw new UnsupportedOperationException();
	}

	@Override
	public TemplateableElement getUnspecializedElement() {
	//	throw new UnsupportedOperationException();
		return this;
	}

	@Override
	public void initFragments(@NonNull FlatFragment @NonNull [] fragments, int[] depthCounts) {
		throw new UnsupportedOperationException();
	}

	@Override
	public org.eclipse.ocl.pivot.@Nullable Class isClass() {
		return this;
	}

	@Override
	public boolean isIsAbstract() {
		return flatClass.isAbstract();
	}

	@Override
	public boolean isIsActive() {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isIsInterface() {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isOrdered() {
		assert !flatClass.isOrdered();
		return false;
	}

	@Override
	public @Nullable TemplateParameter isTemplateParameter() {
		return null;
	}

	@Override
	public boolean isUnique() {
		assert !flatClass.isUnique();
		return false;
	}

	//	@Override
	public boolean isIsSerializable() {
		throw new UnsupportedOperationException();
	}

	@Override
	public @NonNull Operation lookupActualOperation(@NonNull StandardLibrary standardLibrary, @NonNull Operation apparentOperation) {
		return flatClass.lookupActualOperation(standardLibrary, apparentOperation);
	}

	@Override
	public @NonNull LibraryFeature lookupImplementation(@NonNull StandardLibrary standardLibrary, @NonNull Operation apparentOperation) {
		return flatClass.lookupImplementation(standardLibrary, apparentOperation);
	}

	//	@Override
	public void setBehavioralClass(org.eclipse.ocl.pivot.Class value) {
		throw new UnsupportedOperationException();
	}

	public void setFlatClass(@NonNull FlatClass flatClass) {
		this.flatClass = flatClass;
	}

	@Override
	public void setInstanceClassName(String value) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setIsAbstract(boolean value) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setIsActive(boolean value) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setIsInterface(boolean value) {
		throw new UnsupportedOperationException();
	}

	//	@Override
	public void setIsSerializable(boolean value) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setOwnedSignature(TemplateSignature value) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setOwningPackage(org.eclipse.ocl.pivot.Package value) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setUnspecializedElement(TemplateableElement value) {
		throw new UnsupportedOperationException();
	}

	/**
	 * @since 1.18
	 */
	public boolean validateBehavioralClassHasDistinctName(DiagnosticChain diagnostics, Map<Object, Object> context) {
		throw new UnsupportedOperationException();
	}

	/**
	 * @since 1.18
	 */
	public boolean validateBehavioralClassIsPrimitiveType(DiagnosticChain diagnostics, Map<Object, Object> context) {
		throw new UnsupportedOperationException();
	}

	/**
	 * @since 1.18
	 */
	public boolean validateBehavioralClassIsSuperClass(DiagnosticChain diagnostics, Map<Object, Object> context) {
		throw new UnsupportedOperationException();
	}

	/**
	 * @since 1.3
	 */
	@Override
	public boolean validateNameIsNotNull(DiagnosticChain diagnostics, Map<Object, Object> context) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean validateUniqueInvariantName(DiagnosticChain diagnostics, Map<Object, Object> context) {
		throw new UnsupportedOperationException();
	}
}