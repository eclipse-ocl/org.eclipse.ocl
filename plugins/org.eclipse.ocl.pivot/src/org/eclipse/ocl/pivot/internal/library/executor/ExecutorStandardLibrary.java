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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.Class;
import org.eclipse.ocl.pivot.CollectionType;
import org.eclipse.ocl.pivot.Enumeration;
import org.eclipse.ocl.pivot.EnumerationLiteral;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.Package;
import org.eclipse.ocl.pivot.ParameterTypes;
import org.eclipse.ocl.pivot.PivotFactory;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.TemplateParameter;
import org.eclipse.ocl.pivot.TemplateParameters;
import org.eclipse.ocl.pivot.TemplateSignature;
import org.eclipse.ocl.pivot.TemplateableElement;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.flat.EcoreFlatModel;
import org.eclipse.ocl.pivot.flat.FlatClass;
import org.eclipse.ocl.pivot.flat.FlatFragment;
import org.eclipse.ocl.pivot.ids.IdManager;
import org.eclipse.ocl.pivot.ids.PackageId;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.ClassImpl;
import org.eclipse.ocl.pivot.internal.EnumerationImpl;
import org.eclipse.ocl.pivot.internal.EnumerationLiteralImpl;
import org.eclipse.ocl.pivot.internal.LambdaTypeImpl;
import org.eclipse.ocl.pivot.internal.OperationImpl;
import org.eclipse.ocl.pivot.internal.PackageImpl;
import org.eclipse.ocl.pivot.internal.ParameterImpl;
import org.eclipse.ocl.pivot.internal.PropertyImpl;
import org.eclipse.ocl.pivot.internal.TemplateParameterImpl;
import org.eclipse.ocl.pivot.library.LibraryFeature;
import org.eclipse.ocl.pivot.library.LibraryProperty;
import org.eclipse.ocl.pivot.utilities.NameUtil;

public class ExecutorStandardLibrary extends ExecutableStandardLibrary
{
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

	public @NonNull Type createSpecializedType(@NonNull TypeId unspecializedTypeId, @NonNull Type... typeArguments) {
		return new ExecutorSpecializedType(unspecializedTypeId, typeArguments);
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

	public synchronized org.eclipse.ocl.pivot.@Nullable Package getPackage(@NonNull EPackage ePackage) {
		String nsURI = ePackage.getNsURI();
		return nsURI != null ? weakGet(ePackageMap, nsURI.intern()) : null;
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

	public void initPackage(@NonNull Package asPackage, org.eclipse.ocl.pivot.@NonNull Class @NonNull [] asClasses) {
		List<Class> ownedClasses = asPackage.getOwnedClasses();
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
}
