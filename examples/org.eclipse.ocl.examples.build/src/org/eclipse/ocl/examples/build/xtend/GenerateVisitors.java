/*******************************************************************************
 * Copyright (c) 2014, 2019 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.build.xtend;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.ETypeParameter;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.examples.build.genmodel.SplitGenModelGeneratorAdapterFactory;
import org.eclipse.ocl.examples.codegen.genmodel.OCLGenModelUtil;
import org.eclipse.ocl.pivot.utilities.NameUtil;

public abstract class GenerateVisitors extends GenerateVisitorsWorkflowComponent
{
	protected static EClass firstSuperClass(EClass eClass, EClass nullClass) {
		if (eClass.getESuperTypes().size() == 0) {
			return nullClass;
		} else {
			EClass eSuperClass = eClass.getESuperTypes().get(0);
			if (eSuperClass.isInterface()) {
				return firstSuperClass(eSuperClass, nullClass);
			} else {
				return eSuperClass;
			}
		}
	}

	protected static @NonNull List<EClass> getSortedEClasses(@NonNull EPackage ePackage) {
		List<EClass> sortedEClasses = new ArrayList<EClass>();
		for (EClassifier eClassifier : ePackage.getEClassifiers()) {
			if ((eClassifier instanceof EClass) && !((EClass) eClassifier).isInterface()) {
				sortedEClasses.add((EClass) eClassifier);
			}
		}
		Collections.sort(sortedEClasses, NameUtil.ENamedElementComparator.INSTANCE);
		return sortedEClasses;
	}

	protected static @NonNull String getTemplatedName(@NonNull EClass eClass) {
		StringBuilder s = new StringBuilder();
		s.append(eClass.getName());
		List<ETypeParameter> eTypeParameters = eClass.getETypeParameters();
		if (eTypeParameters.size() > 0) {
			s.append("<");
			int i = 0;
			while (i < eTypeParameters.size()) {
				if (i > 0) {
					s.append(",");
				}
				s.append("?");
				i = i + 1;
			}
			s.append(">");
		}
		return s.toString();
	}

	protected @NonNull String emitNonNull(@NonNull String qualifiedTypeName) {
		int index = qualifiedTypeName.lastIndexOf(".");
		if (index < 0) {
			return "@NonNull " + qualifiedTypeName;
		}
		else {
			return qualifiedTypeName.substring(0, index+1) + "@NonNull " + qualifiedTypeName.substring(index+1);
		}
	}

	protected @NonNull String emitNullable(@NonNull String qualifiedTypeName) {
		int index = qualifiedTypeName.lastIndexOf(".");
		if (index < 0) {
			return "@Nullable " + qualifiedTypeName;
		}
		else {
			return qualifiedTypeName.substring(0, index+1) + "@Nullable " + qualifiedTypeName.substring(index+1);
		}
	}

	protected String getInterfaceModelDirectory(@NonNull GenModel genModel) {
		String interfaceModelDirectory = SplitGenModelGeneratorAdapterFactory.getInterfaceModelDirectory(genModel);
		if (interfaceModelDirectory == null) {
			interfaceModelDirectory = genModel.getModelDirectory();
		}
		String projectName = interfaceModelDirectory;
		if (projectName.startsWith("/")) {
			projectName = projectName.substring(1);
		}
		String directory = "";
		int firstSlash = projectName.indexOf("/");
		if (firstSlash >= 0) {
			directory = projectName.substring(firstSlash+1);
			projectName = projectName.substring(0, firstSlash);
		}
		URI projectFileURI = EcorePlugin.getPlatformResourceMap().get(projectName);
		URI interfaceResourceURI = URI.createURI(directory, true).resolve(projectFileURI);
		return (interfaceResourceURI.isFile() ? interfaceResourceURI.toFileString() : interfaceResourceURI.toString()) + "/";
	}

	protected String getVisitableClassName(@NonNull GenModel genModel) {
		String visitableClass = OCLGenModelUtil.getVisitableClass(genModel);
		if (visitableClass != null) {
			int lastDot = visitableClass.lastIndexOf(".");
			if (lastDot >= 0) {
				return visitableClass.substring(lastDot+1);
			}
		}
		return visitableClassName;
	}

	protected String getVisitablePackageName(@NonNull GenModel genModel) {
		String visitableClass = OCLGenModelUtil.getVisitableClass(genModel);
		if (visitableClass != null) {
			int lastDot = visitableClass.lastIndexOf(".");
			if (lastDot >= 0) {
				return visitableClass.substring(0, lastDot);
			}
		}
		return visitablePackageName;
	}

/*
		var genModel = genPackage.getGenModel();
		var interfaceModelDirectory = SplitGenClassGeneratorAdapter.getInterfaceModelDirectory(genModel);
		System.out.println("javaFolder " + javaFolder);
		System.out.println("modelFolder " + modelFolder);
		System.out.println("outputFolder " + outputFolder);
		System.out.println("interfaceModelDirectory " + interfaceModelDirectory);
		System.out.println("visitablePackageName " + visitablePackageName);
		System.out.println("visitableClassName " + visitableClassName);
		System.out.println("visitableClass " + visitableClass);
		if (visitableClass != null) {


			var URI projectFileURI = EcorePlugin.getPlatformResourceMap().get(projectName);
			var URI projectResourceURI = URI.createPlatformResourceURI("/" + projectName + "/", true);
			var URI genModelURI = URI.createURI(genModelFile).resolve(projectResourceURI);
			var directory = if (interfaceModelDirectory != null) { interfaceModelDirectory } else { genModel.getModelDirectory(); }
			var project = if (interfaceModelDirectory != null) { interfaceModelDirectory } else { genModel.getModelDirectory(); }
			var URI directoryURI = URI.createPlatformResourceURI(directory, true);
			System.out.println("directoryURI " + directoryURI);
			var URI genModelURI = URI.createURI(genModelFile).resolve(projectResourceURI);

			var lastDot = visitableClass.lastIndexOf(".");
			var visitableClassName2 = if (lastDot >= 0) { visitableClass.substring(lastDot+1) } else { visitableClass };
			var visitablePackageName2 = if (lastDot >= 0) { visitableClass.substring(0, lastDot) } else { "" };
			System.out.println("visitablePackageName2 " + visitablePackageName2);
			System.out.println("visitableClassName2 " + visitableClassName2);
		}
		var EPackage ePackage = genPackage.getEcorePackage();
		var MergeWriter writer = new MergeWriter(outputFolder + visitableClassName + ".java");

	} */
}
