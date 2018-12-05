/*******************************************************************************
 * Copyright (c) 2010, 2018 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.build.utilities;

import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.Resource.Diagnostic;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.mwe.core.ConfigurationException;
import org.eclipse.xtext.diagnostics.ExceptionDiagnostic;

public class ResourceUtils
{
	private static Logger log = Logger.getLogger(ResourceUtils.class);	

	/**
	 * Checks a resource for any errors or warnings.
	 * @param resourceSet
	 * @throws ConfigurationException if any error present
	 */
	public static void checkResource(Resource resource) throws ConfigurationException {
		int errorCount = 0;
		List<Diagnostic> errors = resource.getErrors();
		if (errors.size() > 0) {
			for (Diagnostic error : errors) {
				if (error instanceof ExceptionDiagnostic) {
					log.error("Error for '" + resource.getURI() + "'", ((ExceptionDiagnostic)error).getException());
				}
				else {
					log.error(error + " for '" + resource.getURI() + "'");
				}
				errorCount++;
			}
		}
		List<Diagnostic> warnings = resource.getWarnings();
		if (warnings.size() > 0) {
			for (Diagnostic warning : warnings) {
				if (warning instanceof ExceptionDiagnostic) {
					log.warn("Warning for '" + resource.getURI() + "'", ((ExceptionDiagnostic)warning).getException());
				}
				else {
					log.warn(warning + " for '" + resource.getURI() + "'");
				}
			}
		}
		if (errorCount > 0) {
			throw new RuntimeException("Errors in Resource");
		}
	}

	/**
	 * Checks all resources in a resource set for any errors or warnings.
	 * @param resourceSet
	 * @throws ConfigurationException if any error present
	 */
	public static void checkResourceSet(ResourceSet resourceSet) throws ConfigurationException {
		int errorCount = 0;
		for (Resource aResource : resourceSet.getResources()) {
			List<Diagnostic> errors = aResource.getErrors();
			if (errors.size() > 0) {
				for (Diagnostic error : errors) {
					if (error instanceof ExceptionDiagnostic) {
						log.error("Error for '" + aResource.getURI() + "'", ((ExceptionDiagnostic)error).getException());
					}
					else {
						log.error(error + " for '" + aResource.getURI() + "'");
					}
					errorCount++;
				}
			}
			List<Diagnostic> warnings = aResource.getWarnings();
			if (warnings.size() > 0) {
				for (Diagnostic warning : warnings) {
					if (warning instanceof ExceptionDiagnostic) {
						log.warn("Warning for '" + aResource.getURI() + "'", ((ExceptionDiagnostic)warning).getException());
					}
					else {
						log.warn(warning + " for '" + aResource.getURI() + "'");
					}
				}
			}
		}
		if (errorCount > 0) {
			throw new RuntimeException("Errors in ResourceSet");
		}
	}
}
