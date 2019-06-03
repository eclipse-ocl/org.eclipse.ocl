/*******************************************************************************
 * Copyright (c) 2013, 2019 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * 
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *   Adolfo Sanchez-Barbudo Herrera (University of York) - Lookup Environment/Visitor
 *******************************************************************************/
package org.eclipse.ocl.examples.autogen.java;

import org.eclipse.emf.codegen.ecore.genmodel.GenClass;
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.build.utilities.GenPackageHelper;
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal;

/**
 * AutoCodeGenerator supports generation of the content of a JavaClassFile to
 * execute a Auto transformation.
 */
public abstract class AutoVisitorsCodeGenerator extends AutoCodeGenerator
{
	public AutoVisitorsCodeGenerator(@NonNull EnvironmentFactoryInternal environmentFactory,
			org.eclipse.ocl.pivot.@NonNull Package asPackage,
			org.eclipse.ocl.pivot.@Nullable Package asSuperPackage,
			@NonNull GenPackage genPackage, 
			@Nullable GenPackage superGenPackage, 
			@Nullable GenPackage baseGenPackage) {
		super(environmentFactory, asPackage, asSuperPackage, genPackage, superGenPackage, baseGenPackage);
	}

	@SuppressWarnings("null")
	public @NonNull Class<?> getVisitableClass() {
		try {
			return genModel.getClass().getClassLoader().loadClass(getVisitablePackageName() + '.' + getVisitableClassName());
		} catch (ClassNotFoundException e) {
			return Object.class;
		}
	}
	
	protected org.eclipse.ocl.pivot.@NonNull Class getVisitablePivotClass() {
		
//		return ClassUtil.nonNullState(metamodelManager.getASClass(visitableClass)); 
		for (GenPackage genPackage : genModel.getAllGenAndUsedGenPackagesWithClassifiers()) {
			if (getBasePrefix().equals(genPackage.getPrefix()) &&
				getBaseVisitorPackageName().startsWith(genPackage.getQualifiedPackageName())) {
				for (GenClass genClass : genPackage.getGenClasses()) {
					if (getVisitableClassName().equals(genClass.getName())) {
						EClass visitableEClass = genClass.getEcoreClass();
						org.eclipse.ocl.pivot.Class visitableClass = metamodelManager.getASOfEcore(org.eclipse.ocl.pivot.Class.class, visitableEClass); 
						if (visitableClass != null) {
							return visitableClass;	
						}
					}
				}
			}
		}
		throw new IllegalStateException("Visitable Class not found from the provided GenModel");		
	}
	
	private GenPackageHelper genPackageHelper;
	private GenPackageHelper superGenPackageHelper;
	private GenPackageHelper baseGenPackageHelper;
	
	private GenPackageHelper getGenPackageHelper() {
		if (genPackageHelper == null) {
			genPackageHelper = new GenPackageHelper(genPackage);
		}
		return genPackageHelper;
	}
	
	private GenPackageHelper getSuperGenPackageHelper() {
		if (superGenPackageHelper == null) {
			superGenPackageHelper = new GenPackageHelper(superGenPackage);
		}
		return superGenPackageHelper;
	}
	
	private GenPackageHelper getBaseGenPackageHelper() {
		if (baseGenPackageHelper == null) {
			baseGenPackageHelper = new GenPackageHelper(baseGenPackage);
		}
		return baseGenPackageHelper;
	}
	
	
	public @NonNull Class<?> getVisitorResultClass() {
		return Object.class;
	}

	/**
	 * @return <code>true</code> if the generation is for a base language, otherwise is for a derived one
	 */
	public boolean isBaseVisitorsGeneration() {
		return getSuperProjectPrefix() == null;
	}
	
	protected @NonNull String getVisitorPackageName() {
		return getGenPackageHelper().getVisitorPackageName();
	}
	
	protected @NonNull String getSuperVisitorPackageName() {
		return getSuperGenPackageHelper().getVisitorPackageName();
	}
	
	protected @NonNull String getBaseVisitorPackageName() {
		return getBaseGenPackageHelper().getVisitorPackageName();
	}
	
	protected @NonNull String getVisitorClassName() {
		return getGenPackageHelper().getVisitorClassName();
	}
	
	protected @NonNull String getSuperVisitorClassName() {
		return getSuperGenPackageHelper().getVisitorClassName();
	}
	
	protected @NonNull String getBaseVisitorClassName() {
		return getBaseGenPackageHelper().getVisitorClassName();
	}

	protected @NonNull String getVisitablePackageName() {
		// Visitable will always be taken from the base genPackage
		return getBaseGenPackageHelper().getVisitablePackageName();
	}
	
	protected @NonNull String getVisitableClassName() {
		// Visitable will always be taken from the base genPackage
		return getBaseGenPackageHelper().getVisitableClassName();
	}
}
	
	
	

