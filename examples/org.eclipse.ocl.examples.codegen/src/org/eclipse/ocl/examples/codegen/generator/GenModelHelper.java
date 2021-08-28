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
package org.eclipse.ocl.examples.codegen.generator;

import java.util.List;

import org.eclipse.emf.codegen.ecore.genmodel.GenClassifier;
import org.eclipse.emf.codegen.ecore.genmodel.GenFeature;
import org.eclipse.emf.codegen.ecore.genmodel.GenOperation;
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.codegen.ecore.genmodel.GenParameter;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.Parameter;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.TypedElement;
import org.eclipse.ocl.pivot.internal.manager.PivotMetamodelManager;
import org.eclipse.ocl.pivot.utilities.EnvironmentFactory;

public interface GenModelHelper
{
	@NonNull Class<?> getAbstractOperationClass(int parameterCount);
	@Nullable Class<?> getEcoreFactoryClass(@NonNull EPackage ePackage);
	@NonNull Class<?> getEcoreInterfaceClass(org.eclipse.ocl.pivot.@NonNull Class owningType) throws GenModelException;
	@Nullable String getEcoreInterfaceClassName(@NonNull EClass eClass);
	@NonNull Class<?> getEcoreInterfaceClassifier(@NonNull EClassifier eClassifier) throws GenModelException;
	@Nullable String getEcoreInterfaceClassifierName(@NonNull EClassifier eClassifier);
	@Nullable String getEcoreInterfaceName(org.eclipse.ocl.pivot.@NonNull Class type);
	@Nullable String getEcoreLiteralName(@NonNull EClassifier eClassifier);
	@Nullable String getEcoreLiteralName(@NonNull EStructuralFeature eStructuralFeature);
	@Nullable String getEcoreClassName(org.eclipse.ocl.pivot.@NonNull Class type);
	@NonNull EnvironmentFactory getEnvironmentFactory();
	@NonNull String getFullyQualifiedEcoreLiteralName(@NonNull EClassifier eClassifier);
	@Nullable GenClassifier getGenClassifier(org.eclipse.ocl.pivot.@NonNull Class type);
	@NonNull GenFeature getGenFeature(@NonNull EStructuralFeature eStructuralFeature) throws GenModelException;
	@NonNull GenFeature getGenFeature(@NonNull Property property) throws GenModelException;
	@Nullable GenOperation getGenOperation(@NonNull Operation operation);
	@Nullable GenPackage getGenPackage(@NonNull EPackage ePackage);
	@Nullable GenPackage getGenPackage(org.eclipse.ocl.pivot.@NonNull Package asPackage);
	@Nullable GenPackage getGenPackage(org.eclipse.ocl.pivot.@NonNull Class type);
	@Nullable GenParameter getGenParameter(@NonNull Parameter parameter);
	@NonNull String getGetAccessor(@NonNull Property aProperty) throws GenModelException;
	@NonNull String getGetAccessor(@NonNull EStructuralFeature eStructuralFeature) throws GenModelException;
	@Nullable String getImplementationClassName(@NonNull EClassifier eClassifier);
	@NonNull PivotMetamodelManager getMetamodelManager();
	@NonNull String getName(@Nullable ENamedElement eNamedElement);
	@NonNull String getOperationAccessor(@NonNull Operation anOperation) throws GenModelException;
	@NonNull Class<?> getOperationInterface(@NonNull List<? extends TypedElement> parameters);
	@NonNull String getOperationReturnType(@NonNull Operation operation) throws GenModelException;
	@NonNull String getPropertyResultType(@NonNull Property property) throws GenModelException;
	@NonNull String getQualifiedEcoreLiteralName(@NonNull EClassifier eClassifier);
	@NonNull String getQualifiedEcoreLiteralName(@NonNull EStructuralFeature eStructuralFeature);
	@Nullable String getQualifiedFactoryInterfaceName(@NonNull EPackage ePackage);
	@Nullable String getQualifiedFactoryInterfaceName(org.eclipse.ocl.pivot.@NonNull Class type);
	@Nullable String getQualifiedFactoryInstanceAccessor(@NonNull EPackage ePackage);
	@Nullable String getQualifiedFactoryInstanceAccessor(org.eclipse.ocl.pivot.@NonNull Class type);
	@Nullable String getQualifiedPackageInterfaceName(@NonNull EPackage ePackage);
	@NonNull String getQualifiedValidatorClassName(@NonNull GenPackage genPackage);
	@NonNull String getSetAccessor(@NonNull EStructuralFeature eStructuralFeature);
	@NonNull String getTablesClassName(@NonNull GenPackage genPackage);
}
