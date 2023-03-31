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
package org.eclipse.ocl.pivot.internal.library.executor;

import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.BagType;
import org.eclipse.ocl.pivot.CollectionType;
import org.eclipse.ocl.pivot.Comment;
import org.eclipse.ocl.pivot.CompleteEnvironment;
import org.eclipse.ocl.pivot.CompleteModel;
import org.eclipse.ocl.pivot.CompletePackage;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.ElementExtension;
import org.eclipse.ocl.pivot.Enumeration;
import org.eclipse.ocl.pivot.EnumerationLiteral;
import org.eclipse.ocl.pivot.LambdaType;
import org.eclipse.ocl.pivot.MapType;
import org.eclipse.ocl.pivot.Model;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.OrderedSetType;
import org.eclipse.ocl.pivot.ParameterTypes;
import org.eclipse.ocl.pivot.PivotFactory;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.SequenceType;
import org.eclipse.ocl.pivot.SetType;
import org.eclipse.ocl.pivot.StandardLibrary;
import org.eclipse.ocl.pivot.StandardLibrary.StandardLibraryExtension;
import org.eclipse.ocl.pivot.TemplateParameter;
import org.eclipse.ocl.pivot.TemplateParameters;
import org.eclipse.ocl.pivot.TemplateSignature;
import org.eclipse.ocl.pivot.TemplateableElement;
import org.eclipse.ocl.pivot.TupleType;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.TypedElement;
import org.eclipse.ocl.pivot.flat.EcoreFlatModel;
import org.eclipse.ocl.pivot.flat.FlatClass;
import org.eclipse.ocl.pivot.flat.FlatFragment;
import org.eclipse.ocl.pivot.flat.FlatModel;
import org.eclipse.ocl.pivot.ids.IdManager;
import org.eclipse.ocl.pivot.ids.IdResolver;
import org.eclipse.ocl.pivot.ids.PackageId;
import org.eclipse.ocl.pivot.ids.PrimitiveTypeId;
import org.eclipse.ocl.pivot.ids.TemplateParameterId;
import org.eclipse.ocl.pivot.ids.TuplePartId;
import org.eclipse.ocl.pivot.ids.TupleTypeId;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.ClassImpl;
import org.eclipse.ocl.pivot.internal.CollectionTypeImpl;
import org.eclipse.ocl.pivot.internal.EnumerationImpl;
import org.eclipse.ocl.pivot.internal.EnumerationLiteralImpl;
import org.eclipse.ocl.pivot.internal.LambdaTypeImpl;
import org.eclipse.ocl.pivot.internal.OperationImpl;
import org.eclipse.ocl.pivot.internal.PackageImpl;
import org.eclipse.ocl.pivot.internal.ParameterImpl;
import org.eclipse.ocl.pivot.internal.PropertyImpl;
import org.eclipse.ocl.pivot.internal.TemplateParameterImpl;
import org.eclipse.ocl.pivot.internal.TupleTypeImpl;
import org.eclipse.ocl.pivot.library.LibraryFeature;
import org.eclipse.ocl.pivot.library.LibraryProperty;
import org.eclipse.ocl.pivot.messages.StatusCodes;
import org.eclipse.ocl.pivot.oclstdlib.OCLstdlibTables;
import org.eclipse.ocl.pivot.options.PivotValidationOptions;
import org.eclipse.ocl.pivot.util.Visitor;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.utilities.TypeUtil;
import org.eclipse.ocl.pivot.utilities.ValueUtil;
import org.eclipse.ocl.pivot.values.CollectionTypeParameters;
import org.eclipse.ocl.pivot.values.IntegerValue;
import org.eclipse.ocl.pivot.values.MapTypeParameters;
import org.eclipse.ocl.pivot.values.TemplateParameterSubstitutions;
import org.eclipse.ocl.pivot.values.UnlimitedNaturalValue;

public class ExecutorStandardLibrary implements CompleteEnvironment, StandardLibraryExtension
{
	/**
	 * Shared cache of the lazily created lazily deleted specializations of each collection type.
	 */
	@Deprecated		// XXX Use an Orphanage
	private @NonNull Map<@NonNull Type, @NonNull Map<@NonNull CollectionTypeParameters<@NonNull Type>, @NonNull WeakReference<@Nullable CollectionType>>> collectionSpecializations = new /*Weak*/HashMap<>();	// Keys are not singletons

	/**
	 * Shared cache of the lazily created lazily deleted specializations of each map type.
	 */
	@Deprecated		// XXX Use an Orphanage
	private @NonNull Map<@NonNull Type, @NonNull Map<@NonNull MapTypeParameters<@NonNull Type, @NonNull Type>, @NonNull WeakReference<@Nullable MapType>>> mapSpecializations = new /*Weak*/HashMap<>();		// Keys are not singletons

	/**
	 * Shared cache of the lazily created lazily deleted tuples.
	 */
	@Deprecated		// XXX Use an Orphanage
	private @NonNull Map<@NonNull TupleTypeId, @NonNull WeakReference<@NonNull TupleType>> tupleTypeMap = new WeakHashMap<>();		// Keys are singletons

	/**
	 * Configuration of validation preferences.
	 *
	 * The key used to be a magic String publicly exports from XXXTables polluting the API.
	 *
	 * Now it is the EOperation literal of the validation method.
	 */
	private /*LazyNonNull*/ Map<@Nullable Object, StatusCodes.@Nullable Severity> validationKey2severity = null;

	private EcoreFlatModel ecoreFlatModel = null;

	private @NonNull Map<@NonNull String, @NonNull WeakReference<org.eclipse.ocl.pivot.@NonNull Package>> ePackageMap = new WeakHashMap<>();		// Keys are interned
	private /*@LazyNonNull*/ Map<org.eclipse.ocl.pivot.@NonNull Package, @NonNull List<org.eclipse.ocl.pivot.@NonNull Package>> extensions = null;
	private /*@LazyNonNull*/ org.eclipse.ocl.pivot.Class classType = null;
	private /*@LazyNonNull*/ org.eclipse.ocl.pivot.Class enumerationType = null;

	public ExecutorStandardLibrary(org.eclipse.ocl.pivot.@NonNull Package... execPackages) {
	//	OCLstdlibTables.PACKAGE.getClass();
		for (org.eclipse.ocl.pivot.@NonNull Package execPackage : execPackages) {
			assert execPackage != null;
			addPackage(execPackage, null);
		}
	}
	@Override
	public <R> R accept(@NonNull Visitor<R> visitor) {
		throw new UnsupportedOperationException();
	}

	public void addExtension(org.eclipse.ocl.pivot.@NonNull Package basePackage, org.eclipse.ocl.pivot.@NonNull Package extensionPackage) {
		Map<org.eclipse.ocl.pivot.@NonNull Package, @NonNull List<org.eclipse.ocl.pivot.@NonNull Package>> extensions2 = extensions;
		if (extensions2 == null) {
			extensions = extensions2 = new HashMap<>();
		}
		List<org.eclipse.ocl.pivot.@NonNull Package> list = extensions2.get(basePackage);
		if (list == null) {
			list = new ArrayList<>();
			extensions2.put(basePackage, list);
		}
		list.add(extensionPackage);
	}

	public synchronized void addPackage(org.eclipse.ocl.pivot.@NonNull Package execPackage, org.eclipse.ocl.pivot.@Nullable Package extendedPackage) {
		String uri = execPackage.getURI();
		assert uri != null;
		String internedURI = uri.intern();
		@SuppressWarnings("unused")
		WeakReference<org.eclipse.ocl.pivot.@NonNull Package> oldExecPackage = ePackageMap.put(internedURI, new WeakReference<>(execPackage));
		//		if ((oldExecPackage != null) && (oldExecPackage != execPackage)) {
		//			Iterable<ExecutorType> newTypes = execPackage.getOwnedType();
		//			for (DomainType oldType : oldExecPackage.getOwnedType()) {
		//				-- check for type compatibility
		//			}
		//		}
	}

	@Override
	@NonNull
	public List<Element> allOwnedElements() {
		throw new UnsupportedOperationException();
	}

	public org.eclipse.ocl.pivot.@NonNull Class createClass(/*@NonNull*/ EClass eMetaClass, /*@NonNull*/ EClassifier eClassifier,
			org.eclipse.ocl.pivot.@NonNull Package asPackage, @Nullable TypeId typeId, int flags, @NonNull TemplateParameter @Nullable ... typeParameters) {
		assert eMetaClass != null;
		assert eClassifier != null;
		ClassImpl asClass = (ClassImpl)PivotFactory.eINSTANCE.create(eMetaClass);
		initClass(asClass, eClassifier, typeId, flags, typeParameters);
		return asClass;
	}

	public @NonNull Enumeration createEnumeration(/*@NonNull*/ EEnum eEnum, org.eclipse.ocl.pivot.@NonNull Package asPackage) {
		assert eEnum != null;
		EnumerationImpl asClass = (EnumerationImpl)PivotFactory.eINSTANCE.createEnumeration();
		initClass(asClass, eEnum, null, 0);
		return asClass;
	}

	public @NonNull EnumerationLiteral createEnumerationLiteral(/*@NonNull*/ EEnumLiteral eEnumLiteral, @NonNull Enumeration asEnumeration, int ordinal) {
		assert eEnumLiteral != null;
		EnumerationLiteralImpl asEnumerationLiteral = (EnumerationLiteralImpl)PivotFactory.eINSTANCE.createEnumerationLiteral();
		asEnumerationLiteral.setName(eEnumLiteral.getName());
		asEnumerationLiteral.setESObject(eEnumLiteral);
	//	asEnumerationLiteral.setOrdinal(ordinal);
	//	asEnumeration.getOwnedLiterals().add(asEnumerationLiteral);
		return asEnumerationLiteral;
	}

	public @NonNull FlatFragment createFragment(org.eclipse.ocl.pivot.@NonNull Class cses, org.eclipse.ocl.pivot.@NonNull Class cses2) {
		return new FlatFragment(cses.getFlatClass(this), cses2.getFlatClass(this));
	}

	public @NonNull Type createLambdaType(@NonNull String name, @NonNull Type @NonNull ... typeArguments) {
	//	return new ExecutorLambdaType(name, typeArguments);
		LambdaTypeImpl asClass = (LambdaTypeImpl)PivotFactory.eINSTANCE.createLambdaType();
		asClass.setName(name);
		TypeId typeId = IdManager.getLambdaTypeId(name, IdManager.getParametersId(typeArguments));
		asClass.setTypeId(typeId);
		asClass.setNormalizedTypeId(typeId);
	//	initTemplateParameters(asClass, typeArguments);
	//	EcoreFlatModel flatModel = getFlatModel();
	//	FlatClass flatClass = flatModel.getEcoreFlatClass(asClass);
	//	asClass.setFlatClass(flatClass);
		return asClass;
	}

	public @NonNull Operation createOperation(@NonNull String name, @NonNull ParameterTypes parameterTypes, org.eclipse.ocl.pivot.@NonNull Class asClass,
			int index, @NonNull TemplateParameters typeParameters, @Nullable LibraryFeature implementation) {
	//	return new ExecutorOperation(name, parameterTypes, asClass, index, typeParameters, implementation);
		OperationImpl asOperation = (OperationImpl)PivotFactory.eINSTANCE.createOperation();
		asOperation.setName(name);
	//	asOperation.setESObject(eOperation);
	//	asOperation.setIndex(index);
		asOperation.setImplementation(implementation);
		for (int i = 0; i < parameterTypes.size(); i++) {
			@NonNull Type parameterType = parameterTypes.get(i);
			ParameterImpl asParameter = (ParameterImpl)PivotFactory.eINSTANCE.createParameter();
			asParameter.setName("_" + i);
			asParameter.setType(parameterType);
			asOperation.getOwnedParameters().add(asParameter);
		}
		if (typeParameters.parametersSize() > 0) {
			TemplateSignature templateSignature = PivotFactory.eINSTANCE.createTemplateSignature();
			List<TemplateParameter> asTemplateParameters = templateSignature.getOwnedParameters();
			for (int i = 0; i < typeParameters.parametersSize(); i++) {
				Type type = typeParameters.get(i);		// XXX
				TemplateParameterImpl asTemplateParameter = (TemplateParameterImpl)PivotFactory.eINSTANCE.createTemplateParameter();
				asTemplateParameter.setName("_" + i);
			//	asTemplateParameter.setType(type);
			//	asTemplateParameter.setTemplateParameterId(templateParameter.getTemplateParameterId());
				asTemplateParameters.add(asTemplateParameter);
			}
			asOperation.setOwnedSignature(templateSignature);
		}
		asClass.getOwnedOperations().add(asOperation);
		return asOperation;
	}

	public @NonNull Property createOppositeProperty(@NonNull String name, org.eclipse.ocl.pivot.@NonNull Class asClass, int propertyIndex, /*@NonNull*/ EStructuralFeature eFeature) {
		assert eFeature != null;
	//	EcoreLibraryOppositeProperty oppositeProperty = new EcoreLibraryOppositeProperty(eFeature);
	//	return new ExecutorPropertyWithImplementation(name, executorType, propertyIndex, oppositeProperty);
		PropertyImpl asProperty = (PropertyImpl)PivotFactory.eINSTANCE.createProperty();
		asProperty.setName(eFeature.getName());
		asProperty.setESObject(eFeature);
	//	asProperty.setIndex(propertyIndex);
	//	asProperty.setImplementation(implementation);
		asClass.getOwnedProperties().add(asProperty);
		return asProperty;
	}

	public org.eclipse.ocl.pivot.@NonNull Package createPackage(/*@NonNull*/ EPackage ePackage, @Nullable PackageId packageId) {
		assert ePackage != null;
		PackageImpl asPackage = (PackageImpl)PivotFactory.eINSTANCE.createPackage();
		asPackage.setName(ePackage.getName());
		asPackage.setNsPrefix(ePackage.getNsPrefix());
		asPackage.setURI(ePackage.getNsURI());
		asPackage.setPackageId(packageId != null ? packageId : IdManager.getPackageId(ePackage));
		asPackage.setESObject(ePackage);
		return asPackage;
	}

	public @NonNull Property createProperty(@NonNull String name, org.eclipse.ocl.pivot.@NonNull Class asClass, int propertyIndex, @NonNull LibraryProperty implementation) {
	//	return new ExecutorPropertyWithImplementation(name, asClass, propertyIndex, implementation);
		PropertyImpl asProperty = (PropertyImpl)PivotFactory.eINSTANCE.createProperty();
		asProperty.setName(name);
	//	asProperty.setIndex(propertyIndex);
		asProperty.setImplementation(implementation);
		asClass.getOwnedProperties().add(asProperty);
		return asProperty;
	}

	public @NonNull Property createProperty(/*@NonNull*/ EStructuralFeature eFeature, org.eclipse.ocl.pivot.@NonNull Class asClass, int propertyIndex) {
		assert eFeature != null;
	//	return new EcoreExecutorProperty(eFeature, asClass, propertyIndex);
		PropertyImpl asProperty = (PropertyImpl)PivotFactory.eINSTANCE.createProperty();
		asProperty.setESObject(eFeature);
		asProperty.setName(eFeature.getName());
	//	asProperty.setIndex(propertyIndex);
	//	asProperty.setImplementation(implementation);
		asClass.getOwnedProperties().add(asProperty);
		return asProperty;
	}

	public @NonNull TemplateParameter createTemplateParameter(int index, @NonNull String name) {
	//	ExecutorTypeParameter executorTypeParameter = new ExecutorTypeParameter(index, name);
		TemplateParameterImpl asTemplateParameter = (TemplateParameterImpl)PivotFactory.eINSTANCE.createTemplateParameter();
		asTemplateParameter.setName(name);
		asTemplateParameter.setTemplateParameterId(IdManager.getTemplateParameterId(index));
	//	asTemplateParameter.setIndex(index);
	//	EcoreFlatModel flatModel = getFlatModel();
	//	FlatClass flatClass = flatModel.getFlatClass(executorTypeParameter);
	//	executorTypeParameter.setFlatClass(flatClass);
		return asTemplateParameter;
	}

	public @NonNull Property createTuplePart(@NonNull String name, @NonNull Type type) {
		PropertyImpl asTuplePart = (PropertyImpl)PivotFactory.eINSTANCE.createProperty();
		asTuplePart.setName(name);
		asTuplePart.setType(type);
		return asTuplePart;
	}

	public @NonNull Type createTupleType(@NonNull String name, @NonNull Property... parts) {
		TupleTypeImpl asTupleType = (TupleTypeImpl)PivotFactory.eINSTANCE.createTupleType();
		asTupleType.setName(name);
		for (@NonNull Property part : parts) {
			asTupleType.getOwnedProperties().add(part);
		}
		return asTupleType;
	}

	protected @NonNull HashMap<@Nullable Object, StatusCodes.@Nullable Severity> createValidationKey2severityMap() {
		return PivotValidationOptions.createValidationKey2severityMap();
	}

	@Override
	public EList<Adapter> eAdapters() {
		throw new UnsupportedOperationException();
	}

	@Override
	public @NonNull TreeIterator<@NonNull EObject> eAllContents() {
		throw new UnsupportedOperationException();
	}

	@Override
	public EClass eClass() {
		throw new UnsupportedOperationException();
	}

	@Override
	public EObject eContainer() {
		throw new UnsupportedOperationException();
	}

	@Override
	public EStructuralFeature eContainingFeature() {
		throw new UnsupportedOperationException();
	}

	@Override
	public EReference eContainmentFeature() {
		throw new UnsupportedOperationException();
	}

	@Override
	public EList<EObject> eContents() {
		throw new UnsupportedOperationException();
	}

	@Override
	public EList<EObject> eCrossReferences() {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean eDeliver() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Object eGet(EStructuralFeature feature) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Object eGet(EStructuralFeature feature, boolean resolve) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Object eInvoke(EOperation operation, EList<?> arguments) throws InvocationTargetException {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean eIsProxy() {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean eIsSet(EStructuralFeature feature) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void eNotify(Notification notification) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Resource eResource() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void eSet(EStructuralFeature feature, Object newValue) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void eSetDeliver(boolean deliver) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void eUnset(EStructuralFeature feature) {
		throw new UnsupportedOperationException();
	}

	@Override
	public @NonNull Iterable<@NonNull ? extends CompletePackage> getAllCompletePackages() {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<Comment> getAnnotatingComments() {
		throw new UnsupportedOperationException();
	}

	@Override
	public org.eclipse.ocl.pivot.@NonNull Class getBagType() {
		return OCLstdlibTables.Types._Bag;
	}

	@Override
	public @NonNull CollectionType getBagType(@NonNull Type elementType, @Nullable IntegerValue lower, @Nullable UnlimitedNaturalValue upper) {
		return getCollectionType(getBagType(), elementType, false, lower, upper);
	}

	@Override
	public @NonNull CollectionType getBagType(@NonNull Type elementType, boolean isNullFree, @Nullable IntegerValue lower, @Nullable UnlimitedNaturalValue upper) {
		return getCollectionType(getBagType(), elementType, isNullFree, lower, upper);
	}

	@Override
	public org.eclipse.ocl.pivot.@NonNull Class getBooleanType() {
		return OCLstdlibTables.Types._Boolean;
	}

	@Override
	public org.eclipse.ocl.pivot.@NonNull Class getClassType() {
		Map<org.eclipse.ocl.pivot.@NonNull Package, @NonNull List<org.eclipse.ocl.pivot.@NonNull Package>> extensions2 = extensions;
		if (extensions2 == null) {
			throw new IllegalStateException("No extension package registered to define Class type"); //$NON-NLS-1$
		}
		if (classType != null) {
			return classType;
		}
		classType = getPivotType(TypeId.CLASS_NAME);
		if (classType != null) {
			return classType;
		}
		throw new IllegalStateException("No extension package defines Class type"); //$NON-NLS-1$
	}
	@Override
	public org.eclipse.ocl.pivot.@NonNull Class getCollectionType() {
		return OCLstdlibTables.Types._Collection;
	}

	public @NonNull CollectionType getCollectionType(org.eclipse.ocl.pivot.@NonNull Class genericType, @NonNull Type elementType) {
		return getCollectionType(genericType, elementType, false, null, null);
	}

	@Override
	public @NonNull CollectionType getCollectionType(org.eclipse.ocl.pivot.@NonNull Class genericType, @NonNull Type elementType, @Nullable IntegerValue lower, @Nullable UnlimitedNaturalValue upper) {
		return getCollectionType(genericType, elementType, false, lower, upper);
	}

	@Override
	public synchronized @NonNull CollectionType getCollectionType(org.eclipse.ocl.pivot.@NonNull Class genericType, @NonNull Type elementType, boolean isNullFree, @Nullable IntegerValue lower, @Nullable UnlimitedNaturalValue upper) {
	//	assert false;
		IntegerValue lower2 = lower;
		UnlimitedNaturalValue upper2 = upper;
		if (lower2 == null) {
			lower2 = ValueUtil.ZERO_VALUE;
		}
		if (upper2 == null) {
			upper2 = ValueUtil.UNLIMITED_VALUE;
		}
		CollectionTypeParameters<@NonNull Type> typeParameters = TypeUtil.createCollectionTypeParameters(elementType, isNullFree, lower2, upper2);
		CollectionType specializedType = null;
		Map<@NonNull CollectionTypeParameters<@NonNull Type>, @NonNull WeakReference<@Nullable CollectionType>> map = collectionSpecializations.get(genericType);
		if (map == null) {
			map = new WeakHashMap<>();
			collectionSpecializations.put(genericType, map);
		}
		else {
			specializedType = weakGet(map, typeParameters);
		}
		if (specializedType == null) {
		//	String name = ClassUtil.nonNullModel(genericType.getName());
			if (genericType instanceof BagType) {
			//	specializedType = new ExecutorBagType(name, genericType, elementType, isNullFree, lower, upper);
				specializedType = PivotUtil.createBagType((BagType)genericType, elementType);
			}
			else if (genericType instanceof OrderedSetType) {
			//	specializedType = new ExecutorOrderedSetType(name, genericType, elementType, isNullFree, lower, upper);
				specializedType = PivotUtil.createOrderedSetType((OrderedSetType)genericType, elementType);
			}
			else if (genericType instanceof SequenceType) {
			//	specializedType = new ExecutorSequenceType(name, genericType, elementType, isNullFree, lower, upper);
				specializedType = PivotUtil.createSequenceType((SequenceType)genericType, elementType);
			}
			else if (genericType instanceof SetType) {
			//	specializedType = new ExecutorSetType(name, genericType, elementType, isNullFree, lower, upper);
				specializedType = PivotUtil.createSetType((SetType)genericType, elementType);
			}
			else {
			//	specializedType = new ExecutorCollectionType(name, genericType, elementType, isNullFree, lower, upper);
				specializedType = PivotUtil.createCollectionType((CollectionType)genericType, elementType);
			}
			specializedType.setIsNullFree(isNullFree);
			if (lower != null) {
				specializedType.setLowerValue(lower);
			}
			if (upper != null) {
				specializedType.setUpperValue(upper);
			}
			map.put(typeParameters, new WeakReference<>(specializedType));
			if (genericType.getESObject() != null) {			// XXX Temporary fudge till Library properly initialized
				/*Partial*/FlatModel flatModel = getFlatModel();
				FlatClass flatClass = flatModel.getFlatClass(genericType);
				((CollectionTypeImpl)specializedType).setFlatClass(flatClass);
			}
		}
		return specializedType;
	}

	@Override
	public EObject getESObject() {
		throw new UnsupportedOperationException();
	}

	@Override
	public EObject getETarget() {
		throw new UnsupportedOperationException();
	}

	@Override
	public org.eclipse.ocl.pivot.@NonNull Class getEnumerationType() {
		Map<org.eclipse.ocl.pivot.@NonNull Package, @NonNull List<org.eclipse.ocl.pivot.@NonNull Package>> extensions2 = extensions;
		if (extensions2 == null) {
			throw new IllegalStateException("No extension package registered to define Enumeration type"); //$NON-NLS-1$
		}
		if (enumerationType != null) {
			return enumerationType;
		}
		enumerationType = getPivotType(TypeId.ENUMERATION_NAME);
		if (enumerationType != null) {
			return enumerationType;
		}
		throw new IllegalStateException("No extension package defines Enumeration type"); //$NON-NLS-1$
	}

	@Override
	public @NonNull FlatClass getFlatClass(org.eclipse.ocl.pivot.@NonNull Class asClass) {	// XXX review duplication
		return getFlatModel().getFlatClass(asClass);
	//	if (asClass instanceof CompleteInheritance) {
	//		return (CompleteInheritance) asClass;
	//	}
		/*		if (type instanceof DomainMetaclass) {
			DomainType instanceType = ClassUtil.nonNullPivot(((DomainMetaclass)type).getInstanceType());
			org.eclipse.ocl.pivot.Class metaclass = getMetaclass(instanceType);
			DomainType containerType = metaclass;//.getContainerType();
			return containerType.getInheritance(this);
		} */
/*		if (asClass instanceof CollectionType) {
			Type containerType = ((CollectionType)asClass).getContainerType();
			if (containerType != asClass) {
				return containerType.getFlatClass(this);
			}
		}
		if (asClass instanceof MapType) {
			Type containerType = ((MapType)asClass).getContainerType();
			if (containerType != asClass) {
				return containerType.getFlatClass(this);
			}
		}
		org.eclipse.ocl.pivot.Package asPackage = asClass.getOwningPackage();
		Map<org.eclipse.ocl.pivot.@NonNull Package, @NonNull WeakReference<@NonNull DomainReflectivePackage>> asPackageMap2;
		synchronized (this) {
			String nsURI = asPackage.getURI();
			EcoreExecutorPackage ecoreExecutorPackage = nsURI != null ? weakGet(ePackageMap, nsURI.intern()) : null;
			if (ecoreExecutorPackage != null) {
				String name = asClass.getName();
				org.eclipse.ocl.pivot.Class executorType = ecoreExecutorPackage.getOwnedClass(name);
				if (executorType != null) {
					return executorType.getFlatClass(this);
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
					return executorType.getFlatClass(this);
				}
			}
			asPackageMap2 = asPackageMap;
			if (asPackageMap2 == null) {
				asPackageMap2 = asPackageMap = new WeakHashMap<>();
			}
		}
		synchronized (asPackageMap2) {
			DomainReflectivePackage executorPackage = weakGet(asPackageMap2, asPackage);
			if (executorPackage == null) {
				executorPackage = new DomainReflectivePackage(this, asPackage);
				asPackageMap2.put(asPackage, new WeakReference<>(executorPackage));
			}
			return executorPackage.getFlatClass(asClass);
		} */
	}

	@Override
	public @NonNull EcoreFlatModel getFlatModel() {
		EcoreFlatModel ecoreFlatModel2 = ecoreFlatModel;
		if (ecoreFlatModel2 == null) {
			ecoreFlatModel = ecoreFlatModel2 = new EcoreFlatModel(this);
		//	throw new UnsupportedOperationException();
		}
		return ecoreFlatModel2;
	}

	@Override
	public org.eclipse.ocl.pivot.@NonNull Class getIntegerType() {
		return OCLstdlibTables.Types._Integer;
	}

	@Override
	public @NonNull LambdaType getLambdaType(@NonNull String typeName, @NonNull Type contextType,
			@NonNull List<@NonNull ? extends Type> parameterTypes, @NonNull Type resultType, @Nullable TemplateParameterSubstitutions bindings) {
		throw new UnsupportedOperationException();
	}

	@Override
	public org.eclipse.ocl.pivot.@NonNull Class getMapType() {
		return OCLstdlibTables.Types._Map;
	}

	@Override
	public @NonNull MapType getMapType(org.eclipse.ocl.pivot.@NonNull Class genericType, @NonNull Type keyType, @NonNull Type valueType) {
		return getMapType(genericType, keyType, true, valueType, true);
	}

	@Override
	public synchronized @NonNull MapType getMapType(org.eclipse.ocl.pivot.@NonNull Class genericType, @NonNull Type keyType, boolean keyValuesAreNullFree, @NonNull Type valueType, boolean valuesAreNullFree) {
		MapTypeParameters<@NonNull Type, @NonNull Type> typeParameters = TypeUtil.createMapTypeParameters(keyType, keyValuesAreNullFree, valueType, valuesAreNullFree);
		MapType specializedType = null;
		Map<@NonNull MapTypeParameters<@NonNull Type, @NonNull Type>, @NonNull WeakReference<@Nullable MapType>> map = mapSpecializations.get(genericType);
		if (map == null) {
			map = new WeakHashMap<>();
			mapSpecializations.put(genericType, map);
		}
		else {
			specializedType = weakGet(map, typeParameters);
		}
		if (specializedType == null) {
		//	specializedType = new ExecutorMapType(PivotUtil.getName(genericType), genericType, keyType, keyValuesAreNullFree, valueType, valuesAreNullFree);
			specializedType = PivotUtil.createMapType((MapType)genericType, keyType, valueType);
			specializedType.setKeysAreNullFree(keyValuesAreNullFree);
			specializedType.setValuesAreNullFree(valuesAreNullFree);
			map.put(typeParameters, new WeakReference<>(specializedType));
		}
		return specializedType;
	}

	@Override
	public synchronized @NonNull MapType getMapType(org.eclipse.ocl.pivot.@NonNull Class genericType, org.eclipse.ocl.pivot.@NonNull Class entryClass) {
		MapTypeParameters<@NonNull Type, @NonNull Type> typeParameters = TypeUtil.createMapTypeParameters(entryClass);
		MapType specializedType = null;
		Map<@NonNull MapTypeParameters<@NonNull Type, @NonNull Type>, @NonNull WeakReference<@Nullable MapType>> map = mapSpecializations.get(genericType);
		if (map == null) {
			map = new WeakHashMap<>();
			mapSpecializations.put(genericType, map);
		}
		else {
			specializedType = weakGet(map, typeParameters);
		}
		if (specializedType == null) {
		//	specializedType = new ExecutorMapType(ClassUtil.nonNullModel(genericType.getName()), genericType, typeParameters.getKeyType(), typeParameters.getValueType());
			specializedType = PivotUtil.createMapType((MapType)genericType, typeParameters.getKeyType(), typeParameters.getValueType());
		//	specializedType.setKeysAreNullFree(keyValuesAreNullFree);
		//	specializedType.setValuesAreNullFree(valuesAreNullFree);
			map.put(typeParameters, new WeakReference<>(specializedType));
		}
		return specializedType;
	}

	@Override
	public org.eclipse.ocl.pivot.@NonNull Class getMetaclass(@NonNull Type asInstanceType) {
		String metaclassName = TypeUtil.getMetaclassName(asInstanceType);
		return ClassUtil.nonNullState(getPivotType(metaclassName));
	}

	@Override
	public @NonNull Type getMetaType(@NonNull Type instanceType) {
		throw new UnsupportedOperationException();
	}

	@Override
	public org.eclipse.ocl.pivot.@Nullable Package getNestedPackage(org.eclipse.ocl.pivot.@NonNull Package parentPackage, @NonNull String name) {
		return NameUtil.getNameable(parentPackage.getOwnedPackages(), name);
	}
	@Override
	public org.eclipse.ocl.pivot.@Nullable Class getNestedType(org.eclipse.ocl.pivot.@NonNull Package parentPackage, @NonNull String name) {
		org.eclipse.ocl.pivot.Class nestedType = NameUtil.getNameable(parentPackage.getOwnedClasses(), name);
		if (nestedType != null) {
			return nestedType;
		}
		nestedType = getPivotType(name);
		return nestedType;
	}
	@Override
	public org.eclipse.ocl.pivot.Package getNsURIPackage(@NonNull String nsURI) {
		WeakReference<org.eclipse.ocl.pivot.@NonNull Package> weakReference = ePackageMap.get(nsURI.intern());
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
	public org.eclipse.ocl.pivot.@NonNull Class getOclAnyType() {
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
	public org.eclipse.ocl.pivot.@NonNull Class getOclInvalidType() {
		return OCLstdlibTables.Types._OclInvalid;
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
	public synchronized org.eclipse.ocl.pivot.Class getOclType(@NonNull String typeName) {
		for (@NonNull WeakReference<org.eclipse.ocl.pivot.@NonNull Package> dPackage : ePackageMap.values()) {
			// FIXME			if (OCLstdlibTables.PACKAGE.getNsURI().equals(dPackage.getNsURI())) {
		//	if (dPackage != null) {
				org.eclipse.ocl.pivot.Package packageRef = dPackage.get();
				if (packageRef != null) {
					org.eclipse.ocl.pivot.Class type = packageRef.getOwnedClass(typeName);
					if (type != null) {
						return type;
					}
				}
		//	}
		}
		return null;
	}
	@Override
	public org.eclipse.ocl.pivot.@NonNull Class getOclVoidType() {
		return OCLstdlibTables.Types._OclVoid;
	}

	@Override
	public @Nullable Element getOperationTemplateParameter(@NonNull Operation anOperation, int index) {
		return anOperation.getTypeParameters().get(index);
	}

	@Override
	public org.eclipse.ocl.pivot.@NonNull Class getOrderedCollectionType() {
		return OCLstdlibTables.Types._OrderedCollection;
	}

	@Override
	public org.eclipse.ocl.pivot.@NonNull Class getOrderedSetType() {
		return OCLstdlibTables.Types._OrderedSet;
	}

	@Override
	public @NonNull CollectionType getOrderedSetType(@NonNull Type elementType, @Nullable IntegerValue lower, @Nullable UnlimitedNaturalValue upper) {
		return getCollectionType(getOrderedSetType(), elementType, false, lower, upper);
	}

	@Override
	public @NonNull CollectionType getOrderedSetType(@NonNull Type elementType, boolean isNullFree, @Nullable IntegerValue lower, @Nullable UnlimitedNaturalValue upper) {
		return getCollectionType(getOrderedSetType(), elementType, isNullFree, lower, upper);
	}

	@Override
	@NonNull
	public List<Element> getOwnedAnnotations() {
		throw new UnsupportedOperationException();
	}

	@Override
	@NonNull
	public List<Comment> getOwnedComments() {
		throw new UnsupportedOperationException();
	}

	@Override
	public @NonNull CompleteModel getOwnedCompleteModel() {
		throw new UnsupportedOperationException();
	}

	@Override
	@NonNull
	public List<ElementExtension> getOwnedExtensions() {
		throw new UnsupportedOperationException();
	}

	@Override
	public @NonNull StandardLibrary getOwnedStandardLibrary() {
		return this;
	}

	@Override
	public CompleteEnvironment getOwningCompleteEnvironment() {
		throw new UnsupportedOperationException();
	}

	@Override
	public org.eclipse.ocl.pivot.@NonNull Package getPackage() {
		return OCLstdlibTables.PACKAGE;
	}

	public synchronized org.eclipse.ocl.pivot.@Nullable Package getPackage(@NonNull EPackage ePackage) {
		String nsURI = ePackage.getNsURI();
		return nsURI != null ? weakGet(ePackageMap, nsURI.intern()) : null;
	}
	public org.eclipse.ocl.pivot.@Nullable Class getPivotType(@NonNull String className) {
		Map<org.eclipse.ocl.pivot.@NonNull Package, List<org.eclipse.ocl.pivot.@NonNull Package>> extensions2 = extensions;
		if (extensions2 != null) {
			for (@SuppressWarnings("null")@NonNull List<org.eclipse.ocl.pivot.@NonNull Package> packages : extensions2.values()) {
				for (org.eclipse.ocl.pivot.@NonNull Package extensionPackage : packages) {
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
	public @Nullable Type getPrimitiveType(@NonNull PrimitiveTypeId typeId) {
		return TypeUtil.getPrimitiveType(this, typeId);
	}

	@Override
	public org.eclipse.ocl.pivot.@NonNull Class getRealType() {
		return OCLstdlibTables.Types._Real;
	}

	@Override
	public org.eclipse.ocl.pivot.Package getRootPackage(@NonNull String name) {
		throw new UnsupportedOperationException();
	}

	@Override
	public org.eclipse.ocl.pivot.@NonNull Class getSequenceType() {
		return OCLstdlibTables.Types._Sequence;
	}

	@Override
	public @NonNull CollectionType getSequenceType(@NonNull Type elementType, @Nullable IntegerValue lower, @Nullable UnlimitedNaturalValue upper) {
		return getCollectionType(getSequenceType(), elementType, false, lower, upper);
	}

	@Override
	public @NonNull CollectionType getSequenceType(@NonNull Type elementType, boolean isNullFree, @Nullable IntegerValue lower, @Nullable UnlimitedNaturalValue upper) {
		return getCollectionType(getSequenceType(), elementType, isNullFree, lower, upper);
	}

	@Override
	public org.eclipse.ocl.pivot.@NonNull Class getSetType() {
		return OCLstdlibTables.Types._Set;
	}

	@Override
	public @NonNull CollectionType getSetType(@NonNull Type elementType, @Nullable IntegerValue lower, @Nullable UnlimitedNaturalValue upper) {
		return getCollectionType(getSetType(), elementType, false, lower, upper);
	}

	@Override
	public @NonNull CollectionType getSetType(@NonNull Type elementType, boolean isNullFree, @Nullable IntegerValue lower, @Nullable UnlimitedNaturalValue upper) {
		return getCollectionType(getSetType(), elementType, isNullFree, lower, upper);
	}

	//	@Override
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
	public org.eclipse.ocl.pivot.@NonNull Class getStringType() {
		return OCLstdlibTables.Types._String;
	}

	/*	public @NonNull DomainTupleType getTupleType(@NonNull List<? extends DomainTypedElement> parts) {
		StringBuilder s = new StringBuilder();
		for (DomainTypedElement part : parts) {
			s.append(part.getName());
			s.append("\n"); //$NON-NLS-1$
		}
		String key = s.toString();
		synchronized (this) {
			List<WeakReference<DomainTupleType>> tupleTypes = tupleTypeMap.get(key);
			if (tupleTypes != null) {
				for (int j = tupleTypes.size(); --j >= 0; ) {
					WeakReference<DomainTupleType> tupleTypeRef = tupleTypes.get(j);
					DomainTupleType tupleType = tupleTypeRef.get();
					if (tupleType == null) {
						tupleTypes.remove(j);		// Trim stale list entry.
					}
					else {
						int i = 0;
						for (; i < parts.size(); i++) {
							List<? extends DomainTypedElement> ownedAttributes = tupleType.getOwnedAttribute();
							if (ownedAttributes.get(i).getType() != parts.get(i).getType()) {
								break;
							}
						}
						if (i >= parts.size()) {
							return tupleType;
						}
					}
				}
			}
			else {
				tupleTypes = new ArrayList<>();
				tupleTypeMap.put(key, tupleTypes);
			}
			DomainTupleType tupleType = new AbstractTupleType(this, parts);
			tupleTypes.add(new WeakReference<>(tupleType));
			return tupleType;
		}
	} */

	public @NonNull Element getTemplateParameter(@NonNull TemplateParameterId id, Element context) {
		throw new UnsupportedOperationException();
	}

	public synchronized @NonNull TupleType getTupleType(@NonNull TupleTypeId typeId, @NonNull IdResolver idResolver) {
		WeakReference<@NonNull TupleType> ref = tupleTypeMap.get(typeId);
		if (ref != null) {
			TupleType domainTupleType = ref.get();
			if (domainTupleType != null) {
				return domainTupleType;
			}
		}
		TupleType tupleType = new TupleTypeImpl(typeId);
		@NonNull TuplePartId[] partIds = typeId.getPartIds();
		List<Property> ownedAttributes = tupleType.getOwnedProperties();
		for (@NonNull TuplePartId partId : partIds) {
			Type partType = idResolver.getType(partId.getTypeId());
		//	Type partType2 = metamodelManager.getPrimaryType(partType);
			Property property = PivotUtil.createProperty(NameUtil.getSafeName(partId), partType);
			ownedAttributes.add(property);
		}
	//	TupleType domainTupleType = new ExecutorTupleType(typeId);
	//	tupleTypeMap.put(typeId, new WeakReference<>(domainTupleType));
		return tupleType;
	}

	@Override
	public @NonNull TupleType getTupleType(@NonNull String typeName, @NonNull Collection<@NonNull ? extends TypedElement> parts,
			@Nullable TemplateParameterSubstitutions bindings) {
		throw new UnsupportedOperationException();
	}

	@Override
	public org.eclipse.ocl.pivot.@NonNull Class getUniqueCollectionType() {
		return OCLstdlibTables.Types._UniqueCollection;
	}

	@Override
	public org.eclipse.ocl.pivot.@NonNull Class getUnlimitedNaturalType() {
		return OCLstdlibTables.Types._UnlimitedNatural;
	}

	@Override
	public Element getValue(Type stereotype, String propertyName) {
		throw new UnsupportedOperationException();
	}

	private void initClass(@NonNull ClassImpl asClass, @NonNull EClassifier eClassifier, @Nullable TypeId typeId, int flags, @NonNull TemplateParameter @Nullable... typeParameters) {
		asClass.setESObject(eClassifier);
		asClass.setName(eClassifier.getName());
		if (typeId != null) {
			asClass.setTypeId(typeId);
			asClass.setNormalizedTypeId(typeId);
		}
		asClass.setIsAbstract((flags & FlatClass.ABSTRACT) != 0);
		initTemplateParameters(asClass, typeParameters);
		EcoreFlatModel flatModel = getFlatModel();
		FlatClass flatClass = flatModel.getEcoreFlatClass(asClass);
		asClass.setFlatClass(flatClass);
	}

	public void initLiterals(@NonNull Enumeration asEnumeration, @NonNull EnumerationLiteral @NonNull [] asEnumerationLiterals) {
		List<EnumerationLiteral> asLiterals = asEnumeration.getOwnedLiterals();
		for (@NonNull EnumerationLiteral asEnumerationLiteral : asEnumerationLiterals) {
			asLiterals.add(asEnumerationLiteral);
		}
	}

	public void initPackage(org.eclipse.ocl.pivot.@NonNull Package asPackage, org.eclipse.ocl.pivot.@NonNull Class @NonNull [] asClasses) {
		Object eContainer = asPackage.eContainer();
		assert eContainer == null;
		Model asModel = PivotFactory.eINSTANCE.createModel();
		asModel.setExternalURI(asPackage.getURI());
		asModel.getOwnedPackages().add(asPackage);
		EcoreFlatModel flatModel = (EcoreFlatModel)asModel.initFlatModel(this);
		List<org.eclipse.ocl.pivot.Class> ownedClasses = asPackage.getOwnedClasses();
		for (org.eclipse.ocl.pivot.@NonNull Class asClass : asClasses) {
			ownedClasses.add(asClass);
		}
	}

	private <T extends CollectionType> void initTemplateParameters(@NonNull TemplateableElement pivotType, @NonNull TemplateParameter @Nullable... templateParameters) {
		if ((templateParameters != null) && (templateParameters.length > 0)) {
			TemplateSignature templateSignature = PivotFactory.eINSTANCE.createTemplateSignature();
			List<TemplateParameter> asTemplateParameters = templateSignature.getOwnedParameters();
			for (@NonNull TemplateParameter templateParameter : templateParameters) {
				TemplateParameterImpl asTemplateParameter = (TemplateParameterImpl)PivotFactory.eINSTANCE.createTemplateParameter();
				asTemplateParameter.setName(templateParameter.getName());
				asTemplateParameter.setTemplateParameterId(templateParameter.getTemplateParameterId());
				asTemplateParameters.add(asTemplateParameter);
			}
			pivotType.setOwnedSignature(templateSignature);
		}
	}

	public void resetSeverities() {
		validationKey2severity = null;
	}

	@Override
	public void setOwnedCompleteModel(CompleteModel value) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setOwnedStandardLibrary(StandardLibrary value) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setOwningCompleteEnvironment(CompleteEnvironment value) {
		throw new UnsupportedOperationException();
	}

	/**
	 * Return the map.get(key).get() entry if there is one or null if not, removing any stale
	 * entry that may be encountered.
	 */
	protected <K, V> @Nullable V weakGet(@NonNull Map<K, @NonNull WeakReference<V>> map, @NonNull K key) {
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
