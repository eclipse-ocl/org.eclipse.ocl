/*******************************************************************************
 * Copyright (c) 2011, 2022 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.internal.library.executor;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.AnyType;
import org.eclipse.ocl.pivot.BagType;
import org.eclipse.ocl.pivot.BooleanType;
import org.eclipse.ocl.pivot.CollectionType;
import org.eclipse.ocl.pivot.CompleteInheritance;
import org.eclipse.ocl.pivot.CompletePackage;
import org.eclipse.ocl.pivot.DataType;
import org.eclipse.ocl.pivot.InvalidType;
import org.eclipse.ocl.pivot.MapType;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.OrderedSetType;
import org.eclipse.ocl.pivot.PivotFactory;
import org.eclipse.ocl.pivot.PrimitiveType;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.SequenceType;
import org.eclipse.ocl.pivot.SetType;
import org.eclipse.ocl.pivot.StandardLibrary;
import org.eclipse.ocl.pivot.TemplateParameter;
import org.eclipse.ocl.pivot.TupleType;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.TypedElement;
import org.eclipse.ocl.pivot.VoidType;
import org.eclipse.ocl.pivot.evaluation.Executor;
import org.eclipse.ocl.pivot.ids.CollectionTypeId;
import org.eclipse.ocl.pivot.ids.IdResolver;
import org.eclipse.ocl.pivot.ids.PartId;
import org.eclipse.ocl.pivot.ids.TupleTypeId;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.StandardLibraryImpl;
import org.eclipse.ocl.pivot.internal.executor.ExecutorBagType;
import org.eclipse.ocl.pivot.internal.executor.ExecutorCollectionType;
import org.eclipse.ocl.pivot.internal.executor.ExecutorOrderedSetType;
import org.eclipse.ocl.pivot.internal.executor.ExecutorSequenceType;
import org.eclipse.ocl.pivot.internal.executor.ExecutorSetType;
import org.eclipse.ocl.pivot.internal.executor.ExecutorTupleType;
import org.eclipse.ocl.pivot.internal.library.ecore.EcoreExecutorPackage;
import org.eclipse.ocl.pivot.internal.library.ecore.EcoreReflectiveType;
import org.eclipse.ocl.pivot.internal.manager.AbstractCollectionTypeManager;
import org.eclipse.ocl.pivot.internal.manager.AbstractJavaTypeManager;
import org.eclipse.ocl.pivot.internal.manager.AbstractLambdaTypeManager;
import org.eclipse.ocl.pivot.internal.manager.AbstractMapTypeManager;
import org.eclipse.ocl.pivot.internal.manager.AbstractTupleTypeManager;
import org.eclipse.ocl.pivot.manager.CollectionTypeManager;
import org.eclipse.ocl.pivot.manager.JavaTypeManager;
import org.eclipse.ocl.pivot.manager.LambdaTypeManager;
import org.eclipse.ocl.pivot.manager.MapTypeManager;
import org.eclipse.ocl.pivot.manager.SpecializedTypeManager;
import org.eclipse.ocl.pivot.manager.TupleTypeManager;
import org.eclipse.ocl.pivot.messages.StatusCodes;
import org.eclipse.ocl.pivot.oclstdlib.OCLstdlibTables;
import org.eclipse.ocl.pivot.options.PivotValidationOptions;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.ThreadLocalExecutor;
import org.eclipse.ocl.pivot.values.CollectionTypeArguments;
import org.eclipse.ocl.pivot.values.IntegerValue;
import org.eclipse.ocl.pivot.values.TemplateParameterSubstitutions;
import org.eclipse.ocl.pivot.values.UnlimitedNaturalValue;

import com.google.common.collect.Lists;

/**
 * @since 7.0
 */
public abstract class PartialStandardLibraryImpl extends StandardLibraryImpl implements PartialStandardLibrary
{
	public static class ReadOnly extends PartialStandardLibraryImpl
	{
		public ReadOnly(EcoreExecutorPackage... execPackages) {
			super(execPackages);
		}
	}

	public static class Mutable extends PartialStandardLibraryImpl
	{
		public Mutable(@NonNull ReadOnly readonlyStandardLibrary) {
			super(readonlyStandardLibrary);
		}
	}

	public static class PartialCollectionTypeManager extends AbstractCollectionTypeManager
	{
		public PartialCollectionTypeManager(@NonNull StandardLibrary standardLibrary) {
			super(standardLibrary);
		}

		@Override
		protected @NonNull CollectionType createCollectionType(@NonNull CollectionTypeArguments typeArguments) {
			CollectionTypeId collectionTypeId = typeArguments.getCollectionTypeId();
			Type elementType = typeArguments.getElementType();
			boolean isNullFree = typeArguments.isNullFree();
			IntegerValue lower = typeArguments.getLower();
			UnlimitedNaturalValue upper = typeArguments.getUpper();
			if (collectionTypeId == TypeId.BAG) {
				return new ExecutorBagType(TypeId.BAG_NAME, standardLibrary.getBagType(), elementType, isNullFree, lower, upper);
			}
			else if (collectionTypeId == TypeId.ORDERED_SET) {
				return new ExecutorOrderedSetType(TypeId.ORDERED_SET_NAME, standardLibrary.getOrderedSetType(), elementType, isNullFree, lower, upper);
			}
			else if (collectionTypeId == TypeId.SEQUENCE) {
				return new ExecutorSequenceType(TypeId.SEQUENCE_NAME, standardLibrary.getSequenceType(), elementType, isNullFree, lower, upper);
			}
			else if (collectionTypeId == TypeId.SET) {
				return new ExecutorSetType(TypeId.SET_NAME, standardLibrary.getSetType(), elementType, isNullFree, lower, upper);
			}
			else {
				return new ExecutorCollectionType(TypeId.COLLECTION_NAME, standardLibrary.getCollectionType(), elementType, isNullFree, lower, upper);
			}
		}

		@Override
		protected boolean isValid(@Nullable Type type) {
			return type != null;
		}
	}

	/**
	 * @since 7.0
	 */
	public static class PartialJavaTypeManager extends AbstractJavaTypeManager
	{
		public PartialJavaTypeManager(@NonNull StandardLibrary standardLibrary) {
			super(standardLibrary);
		}
	}

	/**
	 * PartialLambdaTypeManager encapsulates the knowledge about known lambda types.
	 *
	 * @since 7.0
	 */
	public static class PartialLambdaTypeManager extends AbstractLambdaTypeManager
	{
		public PartialLambdaTypeManager(@NonNull StandardLibrary standardLibrary) {
			super(standardLibrary);
		}
	}

	/**
	 * @since 7.0
	 */
	public static class PartialMapTypeManager extends AbstractMapTypeManager
	{
		public PartialMapTypeManager(@NonNull StandardLibrary standardLibrary) {
			super(standardLibrary);
		}
	}

	public static class PartialTupleTypeManager extends AbstractTupleTypeManager
	{
		public PartialTupleTypeManager(@NonNull StandardLibrary standardLibrary) {
			super(standardLibrary);
		}

		@Override
		protected @NonNull TupleType createTupleType(@NonNull TupleTypeId tupleTypeId) {
			IdResolver idResolver = standardLibrary.getIdResolver();
			@NonNull PartId[] partIds = tupleTypeId.getPartIds();
			List<@NonNull Property> parts = new ArrayList<>(partIds.length);
			for (@NonNull PartId partId : partIds) {
				Type partType = idResolver.getType(partId.getTypeId());
				Property property = PivotFactory.eINSTANCE.createProperty();
				property.setName(NameUtil.getSafeName(partId));
				property.setIsRequired(partId.isRequired());
				property.setType(partType);
				parts.add(property);
			}
			return new ExecutorTupleType(tupleTypeId, parts);
		}
	}

	/**
	 * Configuration of validation preferences.
	 *
	 * The key used to be a magic String publicly exports from XXXTables polluting the API.
	 *
	 * Now it is the EOperation literal of the validation method.
	 */
	private /*LazyNonNull*/ Map<@Nullable Object, StatusCodes.@Nullable Severity> validationKey2severity = null;

	private @NonNull Map<@NonNull String, @NonNull WeakReference<@NonNull EcoreExecutorPackage>> ePackageMap = new WeakHashMap<>();		// Keys are interned
	private Map<org.eclipse.ocl.pivot.@NonNull Package, @NonNull WeakReference<@NonNull ExecutorReflectivePackage>> asPackageMap = null;
	private /*@LazyNonNull*/ Map<@NonNull EcoreExecutorPackage, @NonNull List<@NonNull EcoreExecutorPackage>> extensions = null;
	private /*@LazyNonNull*/ org.eclipse.ocl.pivot.Class classType = null;
	private /*@LazyNonNull*/ org.eclipse.ocl.pivot.Class enumerationType = null;
	private final boolean mutable;			//XXX split into two classes

	protected PartialStandardLibraryImpl(EcoreExecutorPackage... execPackages) {
		OCLstdlibTables.PACKAGE.getClass();
		this.mutable = false;
		for (EcoreExecutorPackage execPackage : execPackages) {
			assert execPackage != null;
			addPackage(execPackage, null);
		}
	}

	protected PartialStandardLibraryImpl(@NonNull PartialStandardLibraryImpl immutableStandardLibrary) {
		assert !immutableStandardLibrary.mutable;
		this.mutable = true;
		for (WeakReference<@NonNull EcoreExecutorPackage> execPackageRef : immutableStandardLibrary.ePackageMap.values()) {
			assert execPackageRef != null;
			EcoreExecutorPackage execPackage = execPackageRef.get();
			if (execPackage != null) {
				addPackage(execPackage, null);
			}
		}
		for (Map.Entry<@NonNull EcoreExecutorPackage, @NonNull List<@NonNull EcoreExecutorPackage>> entry : immutableStandardLibrary.extensions.entrySet()) {
			EcoreExecutorPackage basePackage = entry.getKey();
			for (@NonNull EcoreExecutorPackage extensionPackage : entry.getValue()) {
				addExtension(basePackage, extensionPackage);
			}
		}
	}

//	@Override
//	public <R> R accept(@NonNull Visitor<R> visitor) {
//		throw new UnsupportedOperationException();
//	}

	public void addExtension(@NonNull EcoreExecutorPackage basePackage, @NonNull EcoreExecutorPackage extensionPackage) {
		Map<@NonNull EcoreExecutorPackage, @NonNull List<@NonNull EcoreExecutorPackage>> extensions2 = extensions;
		if (extensions2 == null) {
			extensions = extensions2 = new HashMap<>();
		}
		List<@NonNull EcoreExecutorPackage> list = extensions2.get(basePackage);
		if (list == null) {
			list = new ArrayList<>();
			extensions2.put(basePackage, list);
		}
		list.add(extensionPackage);
	}

	public synchronized void addPackage(@NonNull EcoreExecutorPackage execPackage, @Nullable EcoreExecutorPackage extendedPackage) {
		String uri = execPackage.getURI();
		assert uri != null;
		String internedURI = uri.intern();
		@SuppressWarnings("unused")
		WeakReference<@NonNull EcoreExecutorPackage> oldExecPackage = ePackageMap.put(internedURI, new WeakReference<>(execPackage));
		//		if ((oldExecPackage != null) && (oldExecPackage != execPackage)) {
		//			Iterable<ExecutorType> newTypes = execPackage.getOwnedType();
		//			for (DomainType oldType : oldExecPackage.getOwnedType()) {
		//				-- check for type compatibility
		//			}
		//		}
	}

	@Override
	protected @Nullable Type basicGetBehavioralType(@NonNull Type type) {
		if (type instanceof DataType) {
			return ((DataType)type).getBehavioralClass();
		}
		return null;
	}

	@Override
	public org.eclipse.ocl.pivot.@Nullable Class basicGetLibraryClass(@NonNull String className) {
		Map<@NonNull EcoreExecutorPackage, @NonNull List<@NonNull EcoreExecutorPackage>> extensions2 = extensions;
		if (extensions2 != null) {
			for (@NonNull List<@NonNull EcoreExecutorPackage> packages : extensions2.values()) {
				for (@NonNull EcoreExecutorPackage extensionPackage : packages) {
					org.eclipse.ocl.pivot.Class executorType = extensionPackage.getOwnedClass(className);
					if (executorType != null) {
						return executorType;
					}
				}
			}
		}
		return null;
	}

	@Override
	protected @NonNull CollectionTypeManager createCollectionTypeManager() {
		return new PartialCollectionTypeManager(this);
	}

	@Override
	protected @NonNull IdResolver createIdResolver() {
		Executor executor = ThreadLocalExecutor.basicGetExecutor();
		assert executor != null;
		return executor.getIdResolver();
	}

	@Override
	protected @NonNull JavaTypeManager createJavaTypeManager() {
		return new PartialJavaTypeManager(this);
	}

	@Override
	protected @NonNull LambdaTypeManager createLambdaTypeManager() {
		return new PartialLambdaTypeManager(this);
	}

	@Override
	protected @NonNull MapTypeManager createMapTypeManager() {
		return new PartialMapTypeManager(this);
	}

	@Override
	protected @Nullable SpecializedTypeManager createSpecializedTypeManager() {
		return null;
	}

	@Override
	protected @NonNull TupleTypeManager createTupleTypeManager() {
		return new PartialTupleTypeManager(this);
	}

	protected @NonNull HashMap<@Nullable Object, StatusCodes.@Nullable Severity> createValidationKey2severityMap() {
		return PivotValidationOptions.createValidationKey2severityMap();
	}

	@Override
	public @NonNull Iterable<@NonNull ? extends CompletePackage> getAllCompletePackages() {
		throw new UnsupportedOperationException();
	}

	@Override
	public @NonNull BagType getBagType() {
		return OCLstdlibTables.Types._Bag;
	}

	@Override
	public @NonNull BooleanType getBooleanType() {
		return OCLstdlibTables.Types._Boolean;
	}

	@Override
	public org.eclipse.ocl.pivot.@NonNull Class getClassType() {
		Map<@NonNull EcoreExecutorPackage, @NonNull List<@NonNull EcoreExecutorPackage>> extensions2 = extensions;
		if (extensions2 == null) {
			throw new IllegalStateException("No extension package registered to define Class type"); //$NON-NLS-1$
		}
		if (classType != null) {
			return classType;
		}
		classType = basicGetLibraryClass(TypeId.CLASS_NAME);
		if (classType != null) {
			return classType;
		}
		throw new IllegalStateException("No extension package defines Class type"); //$NON-NLS-1$
	}

	@Override
	public @NonNull CollectionType getCollectionType() {
		return OCLstdlibTables.Types._Collection;
	}

	@Override
	public @NonNull Type getCommonType(@NonNull Type leftType, @Nullable TemplateParameterSubstitutions leftSubstitutions,
				@NonNull Type rightType, @Nullable TemplateParameterSubstitutions rightSubstitutions) {
		if (leftType == rightType) {
			return getPrimaryType(leftType);
		}
		if (leftType instanceof EcoreReflectiveType) {
			CompleteInheritance firstInheritance = leftType.getInheritance(this);
			CompleteInheritance secondInheritance = rightType.getInheritance(this);
			CompleteInheritance commonInheritance = firstInheritance.getCommonInheritance(secondInheritance);
			return commonInheritance.getPivotClass();
		}
		if (leftType instanceof ExecutorSpecializedType) {
			throw new UnsupportedOperationException();			// WIP fixme
		}

		if (leftType instanceof JavaType) {
			return getCommonJavaType((JavaType)leftType, rightType);
		}
		else if (leftType instanceof TemplateParameter) {
			throw new UnsupportedOperationException();			// WIP fixme
		}
		return super.getCommonType(leftType, leftSubstitutions, rightType, rightSubstitutions);
	}

	protected org.eclipse.ocl.pivot.@NonNull Class getCommonJavaType(@NonNull JavaType thisType, @NonNull Type thatType) {
		if (thisType == thatType) {
			return thisType;
		}
		if (!(thatType instanceof JavaType)) {
			return getOclAnyType();
		}
		JavaType thisJavaType = thisType;
		JavaType thatJavaType = (JavaType)thatType;
		java.lang.Class<?> commonClass = getCommonClass1(thisJavaType.javaClass, thatJavaType.javaClass);
		if (commonClass != null) {
			return getJavaType(commonClass);
		}
		else {
			return getOclAnyType();
		}
	}
	private static java.lang.@Nullable Class<?> getCommonClass1(java.lang.@NonNull Class<?> thisClass, java.lang.@NonNull Class<?> thatClass) {
		java.lang.Class<?> commonClass = getCommonClass2(thisClass, thatClass);
		if (commonClass != null) {
			return commonClass;
		}
		java.lang.Class<?> superclass = thisClass.getSuperclass();
		if (superclass != null) {
			commonClass = getCommonClass1(superclass, thatClass);
			if (commonClass != null) {
				return commonClass;
			}
		}
		for (java.lang.Class<?> superInterface : thisClass.getInterfaces()) {
			if (superInterface != null) {
				commonClass = getCommonClass1(superInterface, thatClass);
				if (commonClass != null) {
					return commonClass;
				}
			}
		}
		return null;
	}
	private static java.lang.@Nullable Class<?> getCommonClass2(java.lang.@NonNull Class<?> thisClass, java.lang.@NonNull Class<?> thatClass) {
		if (thisClass == thatClass) {
			return thisClass;
		}
		java.lang.Class<?> superclass = thatClass.getSuperclass();
		if (superclass != null) {
			java.lang.Class<?> commonClass = getCommonClass2(thisClass, superclass);
			if (commonClass != null) {
				return commonClass;
			}
		}
		for (java.lang.Class<?> superInterface : thatClass.getInterfaces()) {
			if (superInterface != null) {
				java.lang.Class<?> commonClass = getCommonClass2(thisClass, superInterface);
				if (commonClass != null) {
					return commonClass;
				}
			}
		}
		return null;
	}

	protected org.eclipse.ocl.pivot.@NonNull Class getCommonTupleType(@NonNull TupleType thisType, @NonNull Type thatType) {
		if (thisType != thatType) {
			return getOclAnyType();
		}
		return thisType;				// XXX missing code
	}

	@Override
	public org.eclipse.ocl.pivot.@NonNull Class getEnumerationType() {
		Map<@NonNull EcoreExecutorPackage, @NonNull List<@NonNull EcoreExecutorPackage>> extensions2 = extensions;
		if (extensions2 == null) {
			throw new IllegalStateException("No extension package registered to define Enumeration type"); //$NON-NLS-1$
		}
		if (enumerationType != null) {
			return enumerationType;
		}
		enumerationType = basicGetLibraryClass(TypeId.ENUMERATION_NAME);
		if (enumerationType != null) {
			return enumerationType;
		}
		throw new IllegalStateException("No extension package defines Enumeration type"); //$NON-NLS-1$
	}

	@Override
	public @NonNull CompleteInheritance getInheritance(org.eclipse.ocl.pivot.@NonNull Class asClass) {
		if (asClass instanceof CompleteInheritance) {
			return (CompleteInheritance) asClass;
		}
		/*		if (type instanceof DomainMetaclass) {
			DomainType instanceType = ClassUtil.nonNullPivot(((DomainMetaclass)type).getInstanceType());
			org.eclipse.ocl.pivot.Class metaclass = getMetaclass(instanceType);
			DomainType containerType = metaclass;//.getContainerType();
			return containerType.getInheritance(this);
		} */
		if (asClass instanceof CollectionType) {
			Type containerType = ((CollectionType)asClass).getContainerType();
			if (containerType != asClass) {
				return containerType.getInheritance(this);
			}
		}
		if (asClass instanceof MapType) {
			Type containerType = ((MapType)asClass).getContainerType();
			if (containerType != asClass) {
				return containerType.getInheritance(this);
			}
		}
		org.eclipse.ocl.pivot.Package asPackage = asClass.getOwningPackage();
		Map<org.eclipse.ocl.pivot.@NonNull Package, @NonNull WeakReference<@NonNull ExecutorReflectivePackage>> asPackageMap2;
		synchronized (this) {
			String nsURI = asPackage.getURI();
			EcoreExecutorPackage ecoreExecutorPackage = nsURI != null ? weakGet(ePackageMap, nsURI.intern()) : null;
			if (ecoreExecutorPackage != null) {
				String name = asClass.getName();
				CompleteInheritance executorType = ecoreExecutorPackage.getOwnedClass(name);
				if (executorType != null) {
					return executorType;
				}
				Map<@NonNull EcoreExecutorPackage, @NonNull List<@NonNull EcoreExecutorPackage>> extensions2 = extensions;
				if (extensions2 != null) {
					List<@NonNull EcoreExecutorPackage> packages = extensions2.get(ecoreExecutorPackage);
					if (packages != null) {
						for (@NonNull EcoreExecutorPackage extensionPackage : packages) {
							executorType = extensionPackage.getOwnedClass(name);
							if (executorType != null) {
								break;
							}
						}
					}
				}
				if (executorType != null) {
					return executorType;
				}
			}
			asPackageMap2 = asPackageMap;
			if (asPackageMap2 == null) {
				asPackageMap2 = asPackageMap = new WeakHashMap<>();
			}
		}
		synchronized (asPackageMap2) {
			ExecutorReflectivePackage executorPackage = weakGet(asPackageMap2, asPackage);
			if (executorPackage == null) {
				executorPackage = new ExecutorReflectivePackage(this, asPackage);
				asPackageMap2.put(asPackage, new WeakReference<>(executorPackage));
			}
			return executorPackage.getInheritance(asClass);
		}
	}

	@Override
	public @NonNull PrimitiveType getIntegerType() {
		return OCLstdlibTables.Types._Integer;
	}

	@Override
	public @NonNull LambdaTypeManager getLambdaManager() {
		assert lambdaTypeManager != null;
		return lambdaTypeManager;
	}

	/**
	 * @since 7.0
	 */
	public @NonNull Type getLambdaType(@NonNull TypedElement context, @NonNull TypedElement result, @NonNull TypedElement ... parameters) {
		List<@NonNull TypedElement> parameterList = parameters != null ? Lists.newArrayList(parameters) : Collections.emptyList();
		return getLambdaManager().getLambdaType(context, parameterList, result, null);
	}

	@Override
	public @NonNull MapType getMapType() {
		return OCLstdlibTables.Types._Map;
	}

/*	@Override
	public org.eclipse.ocl.pivot.@Nullable Class getNestedType(org.eclipse.ocl.pivot.@NonNull Package parentPackage, @NonNull String name) {
		org.eclipse.ocl.pivot.Class nestedType = NameUtil.getNameable(parentPackage.getOwnedClasses(), name);
		if (nestedType != null) {
			return nestedType;
		}
		nestedType = getPivotType(name);
		return nestedType;
	} */

	@Override
	public org.eclipse.ocl.pivot.Package getNsURIPackage(@NonNull String nsURI) {
		WeakReference<EcoreExecutorPackage> weakReference = ePackageMap.get(nsURI.intern());
		if (weakReference == null) {
			return null;
		}
		return weakReference.get();
	}

	/**
	 * @since 1.14
	 */
	@Override
	public @NonNull Set<@NonNull String> getNsURIs() {
		return ePackageMap.keySet();
	}

	@Override
	public @NonNull AnyType getOclAnyType() {
		return OCLstdlibTables.Types._OclAny;
	}

	@Override
	public org.eclipse.ocl.pivot.@NonNull Class getOclComparableType() {
		return OCLstdlibTables.Types._OclComparable;
	}

	@Override
	public org.eclipse.ocl.pivot.@NonNull Class getOclElementType() {
		return OCLstdlibTables.Types._OclElement;
	}

	/**
	 * @since 1.1
	 */
	@Override
	public org.eclipse.ocl.pivot.@NonNull Class getOclEnumerationType() {
		return OCLstdlibTables.Types._OclEnumeration;
	}

	@Override
	public @NonNull Operation getOclInvalidOperation() {
		throw new UnsupportedOperationException();
	}

	/**
	 * @since 1.4
	 */
	@Override
	public @NonNull Property getOclInvalidProperty() {
		throw new UnsupportedOperationException();
	}

	@Override
	public @NonNull InvalidType getOclInvalidType() {
		return OCLstdlibTables.Types._OclInvalid;
	}

	@Override
	public org.eclipse.ocl.pivot.@NonNull Class getOclLambdaType() {
		return OCLstdlibTables.Types._OclLambda;
	}

	@Override
	public org.eclipse.ocl.pivot.@NonNull Class getOclMessageType() {
		return OCLstdlibTables.Types._OclMessage;
	}

	@Override
	public org.eclipse.ocl.pivot.@NonNull Class getOclSelfType() {
		return OCLstdlibTables.Types._OclSelf;
	}

	/**
	 * @since 1.1
	 */
	@Override
	public org.eclipse.ocl.pivot.@NonNull Class getOclStereotypeType() {
		return OCLstdlibTables.Types._OclStereotype;
	}

	@Override
	public org.eclipse.ocl.pivot.@NonNull Class getOclSummableType() {
		return OCLstdlibTables.Types._OclSummable;
	}

	@Override
	public org.eclipse.ocl.pivot.@NonNull Class getOclTupleType() {
		return OCLstdlibTables.Types._OclTuple;
	}

	@Override
	public org.eclipse.ocl.pivot.@NonNull Class getOclTypeType() {
		return OCLstdlibTables.Types._OclType;
	}

	@Override
	public @NonNull VoidType getOclVoidType() {
		return OCLstdlibTables.Types._OclVoid;
	}

	@Override
	public @NonNull CollectionType getOrderedCollectionType() {
		return OCLstdlibTables.Types._OrderedCollection;
	}

	@Override
	public @NonNull OrderedSetType getOrderedSetType() {
		return OCLstdlibTables.Types._OrderedSet;
	}

	@Override
	public org.eclipse.ocl.pivot.@NonNull Package getPackage() {
		return OCLstdlibTables.PACKAGE;
	}

	@Override
	public synchronized @Nullable EcoreExecutorPackage getPackage(@NonNull EPackage ePackage) {
		String nsURI = ePackage.getNsURI();
		return nsURI != null ? weakGet(ePackageMap, nsURI.intern()) : null;
	}

	@Override
	public @NonNull Type getPrimaryType(@NonNull Type asType) {
		return asType;
	}

	@Override
	public @NonNull PrimitiveType getRealType() {
		return OCLstdlibTables.Types._Real;
	}

	@Override
	public @NonNull SequenceType getSequenceType() {
		return OCLstdlibTables.Types._Sequence;
	}

	@Override
	public @NonNull SetType getSetType() {
		return OCLstdlibTables.Types._Set;
	}

	public StatusCodes.@Nullable Severity getSeverity(@Nullable Object validationKey) {
		Map<@Nullable Object, StatusCodes.@Nullable Severity> validationKey2severity2 = validationKey2severity;
		if (validationKey2severity2 == null) {
			validationKey2severity = validationKey2severity2 = createValidationKey2severityMap();
		}
		return validationKey2severity2.get(validationKey);
	}

	@Override
	public @NonNull Type getSpecializedType(@NonNull Type type, @Nullable TemplateParameterSubstitutions substitutions) {
		throw new UnsupportedOperationException();
	}

	@Override
	public @NonNull PrimitiveType getStringType() {
		return OCLstdlibTables.Types._String;
	}

	@Override
	public @NonNull CollectionType getUniqueCollectionType() {
		return OCLstdlibTables.Types._UniqueCollection;
	}

	@Override
	public @NonNull PrimitiveType getUnlimitedNaturalType() {
		return OCLstdlibTables.Types._UnlimitedNatural;
	}

	public boolean isMutable() {
		return mutable;
	}

	public void resetSeverities() {
		validationKey2severity = null;
	}

	/**
	 * Return the map.get(key).get() entry if there is one or null if not, removing any stale
	 * entry that may be encountered.
	 */
	private <K, V> @Nullable V weakGet(@NonNull Map<K, @NonNull WeakReference<V>> map, @NonNull K key) {
		WeakReference<V> ref = map.get(key);
		if (ref == null) {
			return null;
		}
		@Nullable V value = ref.get();
		if (value == null) {
			map.remove(key);
		}
		return value;
	}
}
