/*******************************************************************************
 * Copyright (c) 2013, 2021 CEA LIST and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink(CEA LIST) - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.codegen.generator;

import java.util.List;

import org.eclipse.emf.codegen.ecore.genmodel.GenClassifier;
import org.eclipse.emf.codegen.ecore.genmodel.GenFeature;
import org.eclipse.emf.codegen.ecore.genmodel.GenOperation;
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.codegen.ecore.genmodel.GenParameter;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.Constraint;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.Parameter;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.TypedElement;
import org.eclipse.ocl.pivot.utilities.EnvironmentFactory;

public interface GenModelHelper
{
	@Nullable Class<?> basicGetEcoreFactoryClass(@NonNull EPackage ePackage);
	@Nullable String basicGetEcoreInterfaceClassName(@NonNull EClass eClass);
	@Nullable String basicGetEcoreInterfaceClassifierName(@NonNull EClassifier eClassifier);
	@Nullable String basicGetEcoreInterfaceName(org.eclipse.ocl.pivot.@NonNull Class type);
	@Nullable String basicGetEcoreLiteralName(@NonNull EClassifier eClassifier);
	@Nullable String basicGetEcoreLiteralName(@NonNull EOperation eOperation);
	@Nullable String basicGetEcoreLiteralName(@NonNull EStructuralFeature eStructuralFeature);
	@Nullable String basicGetEcoreClassName(org.eclipse.ocl.pivot.@NonNull Class type);
	@Nullable GenClassifier basicGetGenClassifier(org.eclipse.ocl.pivot.@NonNull Class type);
	@Nullable GenFeature basicGetGenFeature(@NonNull Property property);
	@Nullable GenOperation basicGetGenOperation(@NonNull Constraint constraint);
	@Nullable GenOperation basicGetGenOperation(@NonNull EOperation eOperation);
	@Nullable GenOperation basicGetGenOperation(@NonNull Operation operation);
	@Nullable GenPackage basicGetGenPackage(@NonNull EPackage ePackage);
	@Nullable GenPackage basicGetGenPackage(org.eclipse.ocl.pivot.@NonNull Package asPackage);
	@Nullable GenParameter basicGetGenParameter(@NonNull Parameter parameter);
	@Nullable GenPackage basicGetGenPackage(org.eclipse.ocl.pivot.@NonNull Class type);
	@Nullable String basicGetImplementationClassName(@NonNull EClassifier eClassifier);
	@Nullable String basicGetQualifiedEcoreLiteralName(@Nullable EOperation eOperation);
	@Nullable String basicGetQualifiedFactoryInterfaceName(@NonNull EPackage ePackage);
	@Nullable String basicGetQualifiedFactoryInterfaceName(org.eclipse.ocl.pivot.@NonNull Class type);
	@Nullable String basicGetQualifiedFactoryInstanceAccessor(@NonNull EPackage ePackage);
	@Nullable String basicGetQualifiedFactoryInstanceAccessor(org.eclipse.ocl.pivot.@NonNull Class type);
	@Nullable String basicGetQualifiedPackageInterfaceName(@NonNull EPackage ePackage);
	@NonNull Class<?> getAbstractOperationClass(int parameterCount);
	@NonNull Class<?> getEcoreInterfaceClass(org.eclipse.ocl.pivot.@NonNull Class owningType) throws GenModelException;
	@NonNull Class<?> getEcoreInterfaceClassifier(@NonNull EClassifier eClassifier) throws GenModelException;
	@NonNull EnvironmentFactory getEnvironmentFactory();
	@NonNull String getFullyQualifiedEcoreLiteralName(@NonNull EClassifier eClassifier);
	@NonNull GenFeature getGenFeature(@NonNull EStructuralFeature eStructuralFeature) throws GenModelException;
	@NonNull GenFeature getGenFeature(@NonNull Property property) throws GenModelException;
	@NonNull String getGetAccessor(@NonNull Property aProperty) throws GenModelException;
	@NonNull String getGetAccessor(@NonNull EStructuralFeature eStructuralFeature) throws GenModelException;
	@NonNull String getName(@Nullable ENamedElement eNamedElement);
	@NonNull String getOperationAccessor(@NonNull Operation anOperation) throws GenModelException;
	@NonNull Class<?> getOperationInterface(@NonNull List<? extends TypedElement> parameters);
	@NonNull String getOperationReturnType(@NonNull Operation operation) throws GenModelException;
	@NonNull String getPropertyResultType(@NonNull Property property) throws GenModelException;
	@NonNull String getQualifiedEcoreLiteralName(@NonNull EClassifier eClassifier);
	@NonNull String getQualifiedEcoreLiteralName(@NonNull EStructuralFeature eStructuralFeature);
	@NonNull String getQualifiedEcorePackage(@NonNull EClassifier eClassifier);
	@NonNull String getQualifiedValidatorClassName(@NonNull GenPackage genPackage);
	@NonNull String getSetAccessor(@NonNull EStructuralFeature eStructuralFeature);
	@NonNull String getTablesClassName(@NonNull GenPackage genPackage);
}
