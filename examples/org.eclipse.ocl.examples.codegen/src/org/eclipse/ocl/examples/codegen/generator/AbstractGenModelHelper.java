/*******************************************************************************
 * Copyright (c) 2013, 2019 CEA LIST and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink(CEA LIST) - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.codegen.generator;

import java.util.List;

import org.eclipse.emf.codegen.ecore.genmodel.GenClass;
import org.eclipse.emf.codegen.ecore.genmodel.GenClassifier;
import org.eclipse.emf.codegen.ecore.genmodel.GenDataType;
import org.eclipse.emf.codegen.ecore.genmodel.GenFeature;
import org.eclipse.emf.codegen.ecore.genmodel.GenOperation;
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.codegen.ecore.genmodel.GenParameter;
import org.eclipse.emf.codegen.util.CodeGenUtil;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.java.ImportUtils;
import org.eclipse.ocl.examples.codegen.java.JavaCodeGenerator;
import org.eclipse.ocl.pivot.Model;
import org.eclipse.ocl.pivot.NamedElement;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.Parameter;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.TypedElement;
import org.eclipse.ocl.pivot.internal.manager.PivotMetamodelManager;
import org.eclipse.ocl.pivot.library.AbstractBinaryOperation;
import org.eclipse.ocl.pivot.library.AbstractOperation;
import org.eclipse.ocl.pivot.library.AbstractTernaryOperation;
import org.eclipse.ocl.pivot.library.AbstractUnaryOperation;
import org.eclipse.ocl.pivot.library.LibraryBinaryOperation;
import org.eclipse.ocl.pivot.library.LibraryOperation;
import org.eclipse.ocl.pivot.library.LibraryTernaryOperation;
import org.eclipse.ocl.pivot.library.LibraryUnaryOperation;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.EnvironmentFactory;

public class AbstractGenModelHelper implements GenModelHelper
{
	public static final @NonNull String TABLES_CLASS_SUFFIX = "Tables";
	public static final @NonNull String TABLES_PACKAGE_NAME = "";

	public static @NonNull String encodeName(@NonNull NamedElement element) {
		int arity = element instanceof Operation ? ((Operation)element).getOwnedParameters().size() : 0;
		String rawEncodeName = rawEncodeName(ClassUtil.nonNullModel(element.getName()), arity);
		if (element instanceof Operation) {
			int sameNames = 0;
			int myIndex = 0;
			for (Operation operation : ((Operation)element).getOwningClass().getOwnedOperations()) {
				String rawName = rawEncodeName(ClassUtil.nonNullModel(operation.getName()), ClassUtil.nonNullModel(operation.getOwnedParameters().size()));
				if (rawName.equals(rawEncodeName)) {
					if (operation == element) {
						myIndex = sameNames;
					}
					sameNames++;
				}
			}
			if (sameNames > 1) {
				return myIndex + "_" + rawEncodeName;
			}
		}
		return rawEncodeName;
	}

	public static @NonNull String rawEncodeName(@NonNull String name, @NonNull Integer arity) {
		StringBuilder s = new StringBuilder();
		//		boolean prevCharIsLower = true;
		for (int i = 0; i < name.length(); i++) {
			char ch = name.charAt(i);
			//			boolean charIsLowerCase = Character.isLowerCase(ch);
			/*if (charIsLowerCase) {
				s.append(Character.toUpperCase(ch));
			}
			else if (Character.isUpperCase(ch)) {
				if (prevCharIsLower) {
					s.append('_');
				}
				s.append(ch);
			}
			else if (Character.isJavaIdentifierPart(ch)) {
				s.append(ch);
			}
			else*/ if (ch == '<') {
				s.append("_lt_");
			}
			else if (ch == '>') {
				s.append("_gt_");
			}
			else if (ch == '=') {
				s.append("_eq_");
			}
			else if (ch == '+') {
				s.append("_add_");
			}
			else if (ch == '-') {
				if (arity == 0) {
					s.append("_neg_");
				}
				else {
					s.append("_sub_");
				}
			}
			else if (ch == '*') {
				s.append("_mul_");
			}
			else if (ch == '/') {
				s.append("_div_");
			}
			else if (!Character.isJavaIdentifierPart(ch)) {
				s.append("_" + Integer.toString(ch) + "_");
			}
			else {
				s.append(ch);
			}
			//			if ((''))
			//			prevCharIsLower = charIsLowerCase;
		}
		return s.toString();
	}

	protected final @NonNull PivotMetamodelManager metamodelManager;

	public AbstractGenModelHelper(@NonNull PivotMetamodelManager metamodelManager) {
		this.metamodelManager = metamodelManager;
	}

	@Override
	public @NonNull Class<?> getAbstractOperationClass(@NonNull List<?> parameters) {
		return getAbstractOperationClass(parameters.size()) ;
	}

	@Override
	public @NonNull Class<?> getAbstractOperationClass(int parameterCount) {
		switch (parameterCount) {
			case 0: return AbstractUnaryOperation.class;
			case 1: return AbstractBinaryOperation.class;
			case 2: return AbstractTernaryOperation.class;
			default: return AbstractOperation.class;
		}
	}

	@Override
	public @Nullable Class<?> getEcoreFactoryClass(@NonNull EPackage ePackage) {
		GenPackage genPackage = getGenPackage(ePackage);
		if (genPackage == null) {
			return null;
		}
		String qualifiedFactoryName = genPackage.getQualifiedFactoryInterfaceName();
		try {
			Thread currentThread = Thread.currentThread();
			@SuppressWarnings("null") @NonNull ClassLoader contextClassLoader = currentThread.getContextClassLoader();
			@SuppressWarnings("null") @NonNull Class<?> loadedClass = contextClassLoader.loadClass(qualifiedFactoryName);
			return loadedClass;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public @NonNull Class<?> getEcoreInterfaceClass(org.eclipse.ocl.pivot.@NonNull Class type) throws GenModelException {
		GenClassifier genClassifier = getGenClassifier(type);
		String qualifiedInterfaceName;
		if (genClassifier instanceof GenDataType) {
			qualifiedInterfaceName = ((GenDataType)genClassifier).getQualifiedInstanceClassName();
			Class<?> primitiveClass = JavaCodeGenerator.javaPrimitiveNames.get(qualifiedInterfaceName);
			if (primitiveClass != null) {
				return primitiveClass;
			}
		}
		else {
			qualifiedInterfaceName = ((GenClass)genClassifier).getQualifiedInterfaceName();
		}
		try {
			Thread currentThread = Thread.currentThread();
			@SuppressWarnings("null") @NonNull ClassLoader contextClassLoader = currentThread.getContextClassLoader();
			@SuppressWarnings("null") @NonNull Class<?> loadedClass = contextClassLoader.loadClass(qualifiedInterfaceName);
			return loadedClass;
		} catch (Exception e) {
			throw new GenModelException("Failed to load class for " + type);
		}
	}

	@Override
	public @Nullable String getEcoreInterfaceClassName(@NonNull EClass eClass) throws GenModelException {
		try {
			GenClassifier genClassifier = getGenClass(eClass);
			String qualifiedInterfaceName;
			if (genClassifier instanceof GenDataType) {
				qualifiedInterfaceName = ((GenDataType)genClassifier).getQualifiedInstanceClassName();
				//				Class<?> primitiveClass = JavaCodeGenerator.javaPrimitiveNames.get(qualifiedInterfaceName);
				//				if (primitiveClass != null) {
				//					return primitiveClass;
				//				}
			}
			else {
				qualifiedInterfaceName = ((GenClass)genClassifier).getQualifiedInterfaceName();
			}
			return qualifiedInterfaceName;
		}
		catch (GenModelException e) {
			return null;
		}
	}

	@Override
	public @NonNull Class<?> getEcoreInterfaceClassifier(@NonNull EClassifier eClassifier) throws GenModelException {
		GenClassifier genClassifier = getGenClassifier(eClassifier);
		String qualifiedInterfaceName;
		if (genClassifier instanceof GenDataType) {
			qualifiedInterfaceName = ((GenDataType)genClassifier).getQualifiedInstanceClassName();
			Class<?> primitiveClass = JavaCodeGenerator.javaPrimitiveNames.get(qualifiedInterfaceName);
			if (primitiveClass != null) {
				return primitiveClass;
			}
		}
		else {
			qualifiedInterfaceName = ((GenClass)genClassifier).getQualifiedInterfaceName();
		}
		try {
			Thread currentThread = Thread.currentThread();
			@SuppressWarnings("null") @NonNull ClassLoader contextClassLoader = currentThread.getContextClassLoader();
			@SuppressWarnings("null") @NonNull Class<?> loadedClass = contextClassLoader.loadClass(qualifiedInterfaceName);
			return loadedClass;
		} catch (Exception e) {		// FIXME this is a normal path for dynamic models
			throw new GenModelException("Failed to load class for " + eClassifier);
		}
	}

	@Override
	public @Nullable String getEcoreInterfaceClassifierName(@NonNull EClassifier eClassifier) throws GenModelException {
		try {
			GenClassifier genClassifier = getGenClassifier(eClassifier);
			String qualifiedInterfaceName;
			if (genClassifier instanceof GenDataType) {
				qualifiedInterfaceName = ((GenDataType)genClassifier).getQualifiedInstanceClassName();
				//				Class<?> primitiveClass = JavaCodeGenerator.javaPrimitiveNames.get(qualifiedInterfaceName);
				//				if (primitiveClass != null) {
				//					return primitiveClass;
				//				}
			}
			else {
				qualifiedInterfaceName = ((GenClass)genClassifier).getQualifiedInterfaceName();
			}
			return qualifiedInterfaceName;
		}
		catch (GenModelException e) {
			return null;
		}
	}

	@Override
	public @Nullable String getEcoreInterfaceName(org.eclipse.ocl.pivot.@NonNull Class type) {
		try {
			GenClassifier genClassifier = getGenClassifier(type);
			return genClassifier instanceof GenDataType
					? ((GenDataType) genClassifier).getQualifiedInstanceClassName()
						: ((GenClass) genClassifier).getQualifiedInterfaceName();
		} catch (GenModelException e) {
			return null;
		}
	}

	@Override
	public @Nullable String getEcoreLiteralName(@NonNull EStructuralFeature eFeature) {
		try {
			GenFeature genFeature = getGenFeature(eFeature);
			return genFeature.getGenClass().getFeatureID(genFeature);
		} catch (GenModelException e) {
			return null;
		}
	}

	@Override
	public @Nullable String getEcoreClassName(org.eclipse.ocl.pivot.@NonNull Class type) {
		try {
			GenClassifier genClassifier = getGenClassifier(type);
			return genClassifier instanceof GenDataType
					? ((GenDataType) genClassifier).getQualifiedInstanceClassName()
						: ((GenClass) genClassifier).getQualifiedClassName();
		} catch (GenModelException e) {
			return null;
		}
	}

	@Override
	public @NonNull EnvironmentFactory getEnvironmentFactory() {
		return metamodelManager.getEnvironmentFactory();
	}

	protected @NonNull GenClass getGenClass(org.eclipse.ocl.pivot.@NonNull Class type) throws GenModelException {
		GenPackage genPackage = getGenPackage(type);
		if (genPackage != null) {
			String name = type.getName();
			for (GenClass genClass : genPackage.getGenClasses()) {
				String clsName = genClass.getEcoreClass().getName();
				if (name.equals(clsName)) {
					return genClass;
				}
			}
		}
		throw new GenModelException("No GenClass for " + type);
	}

	protected @NonNull GenClass getGenClass(@NonNull EClass eClass) throws GenModelException {
		GenPackage genPackage = getGenPackage(eClass);
		if (genPackage != null) {
			String name = eClass.getName();
			for (GenClass genClass : genPackage.getGenClasses()) {
				String clsName = genClass.getEcoreClass().getName();
				if (name.equals(clsName)) {
					return genClass;
				}
			}
		}
		throw new GenModelException("No GenClass for " + eClass);
	}

	protected @NonNull GenClassifier getGenClassifier(@NonNull EClassifier eClassifier) throws GenModelException {
		GenPackage genPackage = getGenPackage(eClassifier);
		if (genPackage != null) {
			String name = eClassifier.getName();
			for (GenClassifier genClassifier : genPackage.getGenClassifiers()) {
				String clsName = genClassifier.getEcoreClassifier().getName();
				if (name.equals(clsName)) {
					return genClassifier;
				}
			}
		}
		throw new GenModelException("No GenClassifier for " + eClassifier);
	}

	/*	public @Nullable GenClass getGenClass(@NonNull GenPackage genPackage, @NonNull Type type) {
		String name = type.getName();
		for (GenClass genClass : genPackage.getGenClasses()) {
			String clsName = genClass.getEcoreClass().getName();
			if (name.equals(clsName)) {
				return genClass;
			}
		}
		return null;
	} */

	@Override
	public @NonNull GenClassifier getGenClassifier(org.eclipse.ocl.pivot.@NonNull Class type) throws GenModelException {
		GenPackage genPackage = getGenPackage(type);
		if (genPackage != null) {
			String name = type.getName();
			for (GenClassifier genClassifier : genPackage.getGenClassifiers()) {
				String clsName = genClassifier.getEcoreClassifier().getName();
				if (name.equals(clsName)) {
					return genClassifier;
				}
			}
		}
		for (@SuppressWarnings("null")org.eclipse.ocl.pivot.@NonNull Class partialType : metamodelManager.getPartialClasses(type)) {
			genPackage = getGenPackage(partialType);
			if (genPackage != null) {
				String name = partialType.getName();
				for (GenClassifier genClassifier : genPackage.getGenClassifiers()) {
					String clsName = genClassifier.getEcoreClassifier().getName();
					if (name.equals(clsName)) {
						return genClassifier;
					}
				}
			}
		}
		throw new GenModelException("No GenClassifier for " + type);
	}

	public @NonNull GenFeature getGenFeature(@NonNull Property property) throws GenModelException {
		org.eclipse.ocl.pivot.Class owningType = property.getOwningClass();
		if (owningType != null) {
			GenClass genClass = getGenClass(owningType);
			String name = property.getName();
			for (GenFeature genFeature : genClass.getGenFeatures()) {
				String featureName = genFeature.getEcoreFeature().getName();
				if (name.equals(featureName)) {
					return genFeature;
				}
			}
		}
		throw new GenModelException("No GenFeature for " + property);
	}

	@Override
	public @NonNull GenFeature getGenFeature(@NonNull EStructuralFeature eStructuralFeature) throws GenModelException {
		EClass eClass = eStructuralFeature.getEContainingClass();
		if (eClass != null) {
			GenClass genClass = getGenClass(eClass);
			String name = eStructuralFeature.getName();
			for (GenFeature genFeature : genClass.getGenFeatures()) {
				String featureName = genFeature.getEcoreFeature().getName();
				if (name.equals(featureName)) {
					return genFeature;
				}
			}
		}
		throw new GenModelException("No GenFeature for " + eStructuralFeature);
	}

	/*	public @Nullable GenFeature getGenFeature(@NonNull GenPackage genPackage, @NonNull GenClass genClass, @NonNull Property property) {
		String name = property.getName();
		for (GenFeature genFeature : genClass.getGenFeatures()) {
			String featureName = genFeature.getEcoreFeature().getName();
			if (name.equals(featureName)) {
				return genFeature;
			}
		}
		return null;
	} */

	/*	public @Nullable GenOperation getGenOperation(@NonNull GenPackage genPackage, @NonNull GenClass genClass, @NonNull Operation operation) {
		String name = operation.getName();
		for (GenOperation genOperation : genClass.getGenOperations()) {
			if (name.equals(genOperation.getName())) {
				return genOperation;		// FIXME signatures
			}
		}
		return null;
	} */

	@Override
	public @NonNull GenOperation getGenOperation(@NonNull Operation operation) throws GenModelException {
		Operation baseOperation = operation;
		for ( ; baseOperation.getRedefinedOperations().size() > 0; baseOperation = baseOperation.getRedefinedOperations().get(0)) {
			;
		}
		org.eclipse.ocl.pivot.Class owningType = baseOperation.getOwningClass();
		if (owningType != null) {
			GenClass genClass = getGenClass(owningType);
			String name = operation.getName();
			for (GenOperation genOperation : genClass.getGenOperations()) {
				String operationName = genOperation.getEcoreOperation().getName();
				if (name.equals(operationName)) {
					// FIXME parameters
					return genOperation;
				}
			}
		}
		Operation baseOperation2 = operation;
		for ( ; baseOperation2.getRedefinedOperations().size() > 0; baseOperation2 = baseOperation2.getRedefinedOperations().get(0)) {
			;
		}
		throw new GenModelException("No GenFeature for " + baseOperation);
	}

	@Override
	public @Nullable GenPackage getGenPackage(org.eclipse.ocl.pivot.@NonNull Package asPackage) {
		EObject eContainer = asPackage.eContainer();
		if (eContainer instanceof Model) {
			String nsURI = ((Model)eContainer).getExternalURI();
			if (nsURI != null) {
				GenPackage genPackage = metamodelManager.getGenPackage(nsURI);
				if (genPackage != null) {
					return genPackage;
				}
			}
		}
		String nsURI = asPackage.getURI();
		if (nsURI == null) {
			return null;
		}
		return metamodelManager.getGenPackage(nsURI);
	}

	@Override
	public @Nullable GenPackage getGenPackage(org.eclipse.ocl.pivot.@NonNull Class type) {
		org.eclipse.ocl.pivot.Package asPackage = type.getOwningPackage();
		if (asPackage == null) {
			return null;
		}
		return getGenPackage(asPackage);
	}

	public @Nullable GenPackage getGenPackage(@NonNull EClassifier eClassifier) {
		EPackage ePackage = eClassifier.getEPackage();
		if (ePackage == null) {
			return null;
		}
		return getGenPackage(ePackage);
	}

	public @Nullable GenPackage getGenPackage(@NonNull EPackage ePackage) {
		String nsURI = ePackage.getNsURI();
		if (nsURI == null) {
			return null;
		}
		return metamodelManager.getGenPackage(nsURI);
	}

	@Override
	public @Nullable GenParameter getGenParameter(@NonNull Parameter parameter) throws GenModelException {
		Operation operation = parameter.getOwningOperation();
		if (operation != null) {
			int index = operation.getOwnedParameters().indexOf(parameter);
			GenOperation genOperation = getGenOperation(operation);
			List<GenParameter> genParameters = genOperation.getGenParameters();
			if ((0 <= index) && (index < genParameters.size())) {
				return genParameters.get(index);
			}
		}
		throw new GenModelException("No GenParameter for " + parameter);
	}

	@Override
	public @NonNull String getGetAccessor(@NonNull Property aProperty) throws GenModelException {
		GenFeature genFeature = getGenFeature(aProperty);
		String getAccessor = genFeature.getGetAccessor();
		if (getAccessor != null) {
			return getAccessor;
		}
		throw new GenModelException("No GenFeature for " + aProperty);
	}

	@Override
	public @NonNull String getGetAccessor(@NonNull EStructuralFeature eStructuralFeature) throws GenModelException {
		GenFeature genFeature = getGenFeature(eStructuralFeature);
		String getAccessor = genFeature.getGetAccessor();
		if (getAccessor != null) {
			return getAccessor;
		}
		throw new GenModelException("No GenFeature for " + eStructuralFeature);
	}

	@Override
	public @Nullable String getImplementationClassName(@NonNull EClassifier eClassifier) throws GenModelException {
		try {
			GenClassifier genClassifier = getGenClassifier(eClassifier);
			if (genClassifier instanceof GenClass) {
				return ((GenClass)genClassifier).getClassName();
			}
			else {
				return genClassifier.getName();
			}
		}
		catch (GenModelException e) {
			return null;
		}
	}

	@Override
	@SuppressWarnings("null")
	public @NonNull String getLiteralName(@NonNull EClassifier eClassifier) {
		String name = eClassifier.getName();
		return CodeGenUtil.upperName(name != null ? name : "");
	}

	@Override
	public @NonNull PivotMetamodelManager getMetamodelManager() {
		return metamodelManager;
	}

	@Override
	public @NonNull String getOperationAccessor(@NonNull Operation anOperation) throws GenModelException {
		GenOperation genOperation = getGenOperation(anOperation);
		String operationAccessor = genOperation.getName();
		if (operationAccessor != null) {
			return operationAccessor;
		}
		throw new GenModelException("No GenOperation for " + anOperation);
	}

	@Override
	public @NonNull Class<?> getOperationInterface(@NonNull List<? extends TypedElement> parameters) {
		switch (parameters.size()) {
			case 0: return LibraryUnaryOperation.class;
			case 1: return LibraryBinaryOperation.class;
			case 2: return LibraryTernaryOperation.class;
			default: return LibraryOperation.class;
		}
	}

	@Override
	public @NonNull String getOperationReturnType(@NonNull Operation operation) throws GenModelException {
		org.eclipse.ocl.pivot.Class owningType = operation.getOwningClass();
		if (owningType == null) {
			throw new GenModelException("No owningType for " + operation);
		}
		GenClass genClass = getGenClass(owningType);
		GenOperation genOperation = getGenOperation(operation);
		String returnType = genOperation.getType(genClass);
		if (returnType == null) {
			throw new GenModelException("No returnType for " + operation);
		}
		return returnType;
	}

	@Override
	public @NonNull String getPropertyResultType(@NonNull Property property) throws GenModelException {
		org.eclipse.ocl.pivot.Class owningType = property.getOwningClass();
		if (owningType == null) {
			throw new GenModelException("No owningType for " + property);
		}
		GenClass genClass = getGenClass(owningType);
		GenFeature genFeature = getGenFeature(property);
		String resultType = genFeature.getQualifiedObjectType(genClass);
		if (resultType == null) {
			throw new GenModelException("No resultType for " + property);
		}
		return resultType;
	}

	@Override
	public @Nullable String getQualifiedFactoryInterfaceName(@NonNull EPackage ePackage) {
		GenPackage genPackage = getGenPackage(ePackage);
		if (genPackage == null) {
			return null;
		}
		return genPackage.getQualifiedFactoryInterfaceName();
	}

	@Override
	public @Nullable String getQualifiedFactoryInterfaceName(org.eclipse.ocl.pivot.@NonNull Class type) {
		GenPackage genPackage = getGenPackage(type);
		if (genPackage == null) {
			return null;
		}
		return genPackage.getQualifiedFactoryInterfaceName();
	}

	@Override
	public @Nullable String getQualifiedFactoryInstanceAccessor(@NonNull EPackage ePackage) {
		GenPackage genPackage = getGenPackage(ePackage);
		if (genPackage == null) {
			return null;
		}
		return genPackage.getQualifiedFactoryInstanceAccessor();
	}

	@Override
	public @Nullable String getQualifiedFactoryInstanceAccessor(org.eclipse.ocl.pivot.@NonNull Class type) {
		GenPackage genPackage = getGenPackage(type);
		if (genPackage == null) {
			return null;
		}
		return genPackage.getQualifiedFactoryInstanceAccessor();
	}

	@Override
	public @Nullable String getQualifiedPackageInterfaceName(@NonNull EPackage ePackage) {
		GenPackage genPackage = getGenPackage(ePackage);
		if (genPackage == null) {
			return null;
		}
		return genPackage.getQualifiedPackageInterfaceName();
	}

	@Override
	public @NonNull String getQualifiedValidatorClassName(@NonNull GenPackage genPackage) {
		return ClassUtil.nonNullEMF(genPackage.getQualifiedValidatorClassName());
	}

	@Override
	public @NonNull String getSetAccessor(@NonNull EStructuralFeature eStructuralFeature) throws GenModelException {
		GenFeature genFeature = getGenFeature(eStructuralFeature);
		String setAccessor = genFeature.getAccessorName();
		if (setAccessor != null) {
			return "set" + setAccessor;
		}
		throw new GenModelException("No GenFeature for " + eStructuralFeature);
	}

	@Override
	public @NonNull String getTablesClassName(@NonNull GenPackage genPackage) {
		return ImportUtils.getAffixedName(genPackage.getReflectionPackageName() + "." + genPackage.getPrefix() + TABLES_CLASS_SUFFIX);
	}
}
