/*******************************************************************************
 * Copyright (c) 2016, 2019 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   Adolfo Sanchez-Barbudo Herrera
 *******************************************************************************/
package org.eclipse.ocl.examples.build.utilities;

import org.eclipse.emf.codegen.ecore.genmodel.GenAnnotation;
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.examples.codegen.genmodel.OCLGenModelUtil;
import org.eclipse.ocl.pivot.utilities.ClassUtil;

/**
 * @author asanchez
 *
 */
public class GenPackageHelper {

	private static class VisitorGenModelAnnnotationManager {

		public static final VisitorGenModelAnnnotationManager INSTANCE = new VisitorGenModelAnnnotationManager();

		public @NonNull String getVisitorPackageName(GenPackage genPackage) {

			String visitorClass = getVisitorClass(genPackage);
			return ClassUtil.nonNullState(visitorClass.substring(0,visitorClass.lastIndexOf('.')));
		}

		public @NonNull String getVisitorClassName(GenPackage genPackage) {

			String visitorClass = getVisitorClass(genPackage);
			return ClassUtil.nonNullState(visitorClass.substring(visitorClass.lastIndexOf('.')+1,visitorClass.length()));
		}

		public @NonNull String getVisitablePackageName(GenPackage genPackage) {
			String visitableClass = getVisitableClass(genPackage);
			return ClassUtil.nonNullState(visitableClass.substring(0,visitableClass.lastIndexOf('.')));
		}

		public @NonNull String getVisitableClassName(GenPackage genPackage) {
			String visitableClass = getVisitableClass(genPackage);
			return ClassUtil.nonNullState(visitableClass.substring(visitableClass.lastIndexOf('.')+1,visitableClass.length()));
		}

		protected @NonNull String getVisitorClass(GenPackage genPackage) {
			GenModel genModel = genPackage.getGenModel();

			GenAnnotation ann = genModel.getGenAnnotation(OCLGenModelUtil.OCL_GENMODEL_VISITOR_URI);
			EMap<String, String> details = ann.getDetails();
			String visitorClass = details.get(OCLGenModelUtil.DERIVED_VISITOR_CLASS);
			if (visitorClass == null) {
				visitorClass = details.get(OCLGenModelUtil.ROOT_VISITOR_CLASS);
				if (visitorClass == null) {
					throw new IllegalStateException("Visitor Class not found as genAnnotation of " + genPackage.getPrefix() +  " genModel.");
				}
			}
			return visitorClass;
		}

		protected @NonNull String getVisitableClass(GenPackage genPackage) {
			GenModel genModel = genPackage.getGenModel();

			GenAnnotation ann = genModel.getGenAnnotation(OCLGenModelUtil.OCL_GENMODEL_VISITOR_URI);
			EMap<String, String> details = ann.getDetails();
			String visitableItf = details.get(OCLGenModelUtil.VISITABLE_INTERFACE);
			if (visitableItf == null) {
				throw new IllegalStateException("Visitable Interface not found as genAnnotation of " + genPackage.getPrefix() +  " genModel.");
			}
			return visitableItf;
		}
	}

	private GenPackage genPackage;

	public GenPackageHelper(GenPackage genPackage) {
		this.genPackage = genPackage;
	}

	/**
	 * @return the fully qualified name of the visitor interface.
	 * @throws IllegalStateException if the containing genModel doesn't have the appropriate genAnnontations
	 */
	public @NonNull String getVisitor() {
		return VisitorGenModelAnnnotationManager.INSTANCE.getVisitorClass(genPackage);
	}

	/**
	 * @return the fully qualified name of the visitable interface.
	 * @throws IllegalStateException if the containing genModel doesn't have the appropriate genAnnontations
	 */
	public @NonNull String getVisitable() {
		return VisitorGenModelAnnnotationManager.INSTANCE.getVisitableClass(genPackage);
	}

	public @NonNull String getVisitorPackageName() {
		return VisitorGenModelAnnnotationManager.INSTANCE.getVisitorPackageName(genPackage);
	}

	public @NonNull String getVisitorClassName() {
		return VisitorGenModelAnnnotationManager.INSTANCE.getVisitorClassName(genPackage);
	}

	public @NonNull String getVisitablePackageName() {
		return VisitorGenModelAnnnotationManager.INSTANCE.getVisitablePackageName(genPackage);
	}

	public @NonNull String getVisitableClassName() {
		return VisitorGenModelAnnnotationManager.INSTANCE.getVisitableClassName(genPackage);
	}

	public @NonNull String getProjectPrefix() {
		String pPrefix = genPackage.getPrefix();
		return pPrefix == null ? "" : pPrefix;
	}

	public @NonNull String getModelPackageName() {
		String packageName = genPackage.getReflectionPackageName();
		return packageName == null ? "" : packageName;
	}

	public @NonNull String getSrcJavaFolder() {
		// We assume we have one, or we will generate in the first one
		return ClassUtil.nonNullState(genPackage.getGenModel().getModelSourceFolders().get(0));
	}
}
