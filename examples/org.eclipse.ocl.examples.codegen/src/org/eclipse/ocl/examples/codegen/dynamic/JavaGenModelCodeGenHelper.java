/*******************************************************************************
 * Copyright (c) 2012, 2018 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.codegen.dynamic;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.codegen.ecore.genmodel.GenClass;
import org.eclipse.emf.codegen.ecore.genmodel.GenClassifier;
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.common.CodeGenHelper;
import org.eclipse.ocl.examples.codegen.oclinjunit.JUnitCodeGenerator;
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.PivotPackage;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal;
import org.eclipse.ocl.pivot.library.LibraryConstants;
import org.eclipse.ocl.pivot.library.LibraryOperation;
import org.eclipse.ocl.pivot.oclstdlib.OCLstdlibTables;

public class JavaGenModelCodeGenHelper implements CodeGenHelper
{	// FIXME Isn't all this functionality available elsewhere?
	protected final @NonNull EnvironmentFactoryInternal environmentFactory;
	private @NonNull Map<EPackage, GenPackage> ePackageMap = new HashMap<EPackage, GenPackage>();
	private @NonNull Map<String, GenPackage> uriMap = new HashMap<String, GenPackage>();
	private @NonNull Map<EClassifier, GenClassifier> eClassifierMap = new HashMap<EClassifier, GenClassifier>();

	public JavaGenModelCodeGenHelper(@NonNull GenModel genModel, @NonNull EnvironmentFactoryInternal environmentFactory) throws IOException {
		this.environmentFactory = environmentFactory;
		for (GenPackage genPackage : genModel.getGenPackages()) {
			assert genPackage != null;
			install(genPackage);
		}
		for (GenPackage genPackage : genModel.getUsedGenPackages()) {
			assert genPackage != null;
			install(genPackage);
		}
	}

	protected void install(@NonNull GenPackage genPackage) {
		EPackage ePackage = genPackage.getEcorePackage();
		if ((ePackage != null) && !ePackageMap.containsKey(ePackage)) {
			ePackageMap.put(ePackage, genPackage);
			String nsURI = ePackage.getNsURI();
			if (nsURI != null) {
				uriMap.put(nsURI, genPackage);
			}
			for (GenClassifier genClassifier : genPackage.getGenClassifiers()) {
				assert genClassifier != null;
				EClassifier eClassifier = genClassifier.getEcoreClassifier();
				eClassifierMap.put(eClassifier, genClassifier);
			}
		}
		for (GenPackage nestedGenPackage : genPackage.getNestedGenPackages()) {
			assert nestedGenPackage != null;
			install(nestedGenPackage);
		}
	}

	@Override
	public @NonNull String getCopyright(@NonNull String indentation) {
		return "";
	}

	public @Nullable GenClass getGenClass(@NonNull GenPackage genPackage, @NonNull Type type) {
		String name = type.getName();
		for (GenClass genClass : genPackage.getGenClasses()) {
			String clsName = genClass.getEcoreClass().getName();
			if (name.equals(clsName)) {
				return genClass;
			}
		}
		return null;
	}

	@Override
	public @NonNull GenPackage getGenPackage(org.eclipse.ocl.pivot.@NonNull Class type) {
		org.eclipse.ocl.pivot.Package pPackage = type.getOwningPackage();
		String nsURI = pPackage.getURI();
		GenPackage genPackage = uriMap.get(nsURI);
		if (nsURI != null) {
			if (genPackage != null) {
				return genPackage;
			}
			if (LibraryConstants.STDLIB_URI.equals(nsURI)) {		// FIXME regularize
				genPackage = uriMap.get(PivotPackage.eNS_URI);
				if (genPackage != null) {
					return genPackage;
				}
			}
			genPackage = environmentFactory.getMetamodelManager().getGenPackage(nsURI);
			if (genPackage != null) {
				install(genPackage);
				return genPackage;
			}
		}
		/*		ResourceSet externalResourceSet = metamodelManager.getExternalResourceSet();
		projectMap = ProjectMap.getAdapter(externalResourceSet);
		if (projectMap == null) {
			projectMap = new ProjectMap();
			projectMap.initializeGenModelLocationMap(false);
		}
		URI uri = EcorePlugin.getEPackageNsURIToGenModelLocationMap().get(nsURI);
		if (uri != null) {
			Resource resource = externalResourceSet.getResource(uri, true);
			for (EObject eObject : resource.getContents()) {
				if (eObject instanceof GenModel) {
					for (GenPackage genPackage2 : ((GenModel)eObject).getGenPackages()) {
						assert genPackage2 != null;
						install(genPackage2);
					}
				}
			}
		}
		genPackage = uriMap.get(nsURI);
		if (nsURI != null) {
			if (genPackage != null) {
				return genPackage;
			}
		} */
		throw new IllegalArgumentException("Unknown package '" + nsURI + "'");
	}

	@Override
	public @Nullable LibraryOperation loadClass(@NonNull ExpressionInOCL query, @NonNull File targetFolder,
			@NonNull String packageName, @NonNull String className, boolean saveSource) throws Exception {
		String qualifiedClassName = packageName + "." + className;
		String javaCodeSource = JUnitCodeGenerator.generateClassFile(environmentFactory, query, packageName, className);
		if (saveSource) {
			String fileName = targetFolder + "/" + qualifiedClassName.replace('.', '/') + ".java";
			Writer writer = new FileWriter(fileName);
			writer.append(javaCodeSource);
			writer.close();
		}
		OCLstdlibTables.LIBRARY.getClass();		// Ensure coherent initialization
		File explicitClassPath = new File(targetFolder.getParentFile(), "test-bin");
		String problems = OCL2JavaFileObject.saveClass(String.valueOf(explicitClassPath), qualifiedClassName, javaCodeSource);
		if (problems != null) {
			throw new IOException("Failed to compile " + qualifiedClassName + "\n" + problems);
		}
		Class<?> testClass = OCL2JavaFileObject.loadExplicitClass(explicitClassPath, qualifiedClassName);
		return (LibraryOperation) testClass.newInstance();
	}
}
