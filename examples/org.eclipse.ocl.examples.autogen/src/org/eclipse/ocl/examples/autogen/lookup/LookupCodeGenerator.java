/*******************************************************************************
 * Copyright (c) 2014, 2021 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *   Adolfo Sanchez-Barbudo Herrera (University of York) - Lookup Environment/Visitor
 *******************************************************************************/
package org.eclipse.ocl.examples.autogen.lookup;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.Class;
import org.eclipse.ocl.pivot.Model;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.Package;
import org.eclipse.ocl.pivot.PivotPackage;
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal;
import org.eclipse.ocl.pivot.internal.utilities.PivotUtilInternal;
import org.eclipse.ocl.pivot.oclstdlib.OCLstdlibPackage;

/**
 * LookupCodeGenerator supports generation of the content of a JavaClassFile for the Lookup visitor.
 */
public class LookupCodeGenerator
{
	public static void generate(@NonNull GenPackage genPackage,
			@Nullable GenPackage superGenPackage,
			@Nullable GenPackage baseGenPackage,
			@NonNull String projectName,
			@NonNull String lookupFilePath,
			@NonNull String lookupPackageName,
			@Nullable String superLookupPackageName,
			@Nullable String baseLookupPackage) {
		EPackage ePackage = genPackage.getEcorePackage();
		assert ePackage != null;

		EnvironmentFactoryInternal environmentFactory = PivotUtilInternal.getEnvironmentFactory(ePackage);

		List<org.eclipse.ocl.pivot.@NonNull Package> targetPackages = LookupCGUtil.getTargetPackages(genPackage,environmentFactory, lookupFilePath, projectName);
		for (org.eclipse.ocl.pivot.Package oclDocPackage : targetPackages){
			org.eclipse.ocl.pivot.Package asSuperPackage = null;
			if (superGenPackage != null) {
				String superProjectPrefix = superGenPackage.getPrefix();
				asSuperPackage = LookupCGUtil.getPackage(genPackage, superProjectPrefix, environmentFactory);
				if (asSuperPackage == null) {
					throw new IllegalStateException("No super-GenPackage found in UsedGenPackages for " + superProjectPrefix);
				}
			}
			org.eclipse.ocl.pivot.Package basePackage = asSuperPackage == null ? oclDocPackage : asSuperPackage;
			if (baseGenPackage != null) {
				String baseProjectPrefix = baseGenPackage.getPrefix();
				basePackage = LookupCGUtil.getPackage(genPackage, baseProjectPrefix, environmentFactory);
				if (basePackage == null) {
					throw new IllegalStateException("No super-GenPackage found in UsedGenPackages for " + baseProjectPrefix);
				}
			}

			for (String unqualifiedOpName : gatherEnvOpNames(oclDocPackage, LookupVisitorsClassContext.UNQUALIFIED_ENV_NAME)) {
				new LookupUnqualifiedCodeGenerator(environmentFactory, oclDocPackage, asSuperPackage, basePackage,
					genPackage,superGenPackage, baseGenPackage, unqualifiedOpName).saveSourceFile();
			}

			for (String exportedEnvOpName : gatherEnvOpNames(oclDocPackage, LookupVisitorsClassContext.EXPORTED_ENV_NAME)) {
				new LookupExportedVisitorCodeGenerator(environmentFactory, oclDocPackage, asSuperPackage, basePackage,
					genPackage,superGenPackage, baseGenPackage, exportedEnvOpName).saveSourceFile();
			}

			for (String qualifiedEnvOpName : gatherEnvOpNames(oclDocPackage, LookupVisitorsClassContext.QUALIFIED_ENV_NAME)) {
				new LookupQualifiedCodeGenerator(environmentFactory, oclDocPackage, asSuperPackage, basePackage,
					genPackage,superGenPackage, baseGenPackage, qualifiedEnvOpName).saveSourceFile();
			}

			new LookupFilterGenerator(environmentFactory, oclDocPackage, asSuperPackage, basePackage,
				genPackage,	superGenPackage, baseGenPackage,
				lookupPackageName, superLookupPackageName, baseLookupPackage).saveSourceFile();
		}
	}


	private static Set<@NonNull String> gatherEnvOpNames(@NonNull Package oclDocPackage, @NonNull String envOpNamePrefix) {

		Set<@NonNull String> result = new LinkedHashSet<@NonNull String>();

		Model model = (Model) oclDocPackage.eContainer();
		for (Package pPackage : model.getOwnedPackages()) {
			String uri = pPackage.getURI();
			if (uri.equals(OCLstdlibPackage.eNS_URI)
				|| uri.equals(PivotPackage.eNS_URI)) { // FIXME
				for (Class pClass : pPackage.getOwnedClasses()) {
					for (Operation op : pClass.getOwnedOperations()) {
						String opName = op.getName();
						if (opName != null && opName.startsWith(envOpNamePrefix)) {
							result.add(opName);
						}
					}
				}
			}
		}
		return result;
	}
}
