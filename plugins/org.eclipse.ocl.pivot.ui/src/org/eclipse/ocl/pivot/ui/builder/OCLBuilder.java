/*******************************************************************************
 * Copyright (c) 2017 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation based on org.eclipse.xtext.builder.nature.XtextNature
 *******************************************************************************/
package org.eclipse.ocl.pivot.ui.builder;

import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.xtext.base.ui.OCLProjectHelper;
import org.eclipse.ocl.xtext.base.ui.builder.AbstractValidatingBuilder;
import org.eclipse.ocl.xtext.base.ui.builder.AbstractBuildSelector;

/**
 * Builder for OCL contributions. Currently this involves identifying OCL files subject to
 * extension filtering defined by the excludeExtension/includeExtensions comma-separated file extensionlist
 * and path filtering defined by excludePaths/includepaths comma-separated classpath-style regexes.
 * Default values are supplied as part of the .project buildCommand when the OCL nature is added.
 *
 * The identified files are passed to a separate MultiValidationJob for concurrent non-blocking validation.
 */
public class OCLBuilder extends AbstractValidatingBuilder
{
	protected static class OCLBuildSelector extends AbstractBuildSelector
	{
		public OCLBuildSelector(@NonNull IProject project, @NonNull BuildType buildType, @Nullable Map<String, String> args, @Nullable IProgressMonitor monitor) {
			super(project, buildType, args, monitor);
		}

		@Override
		protected @NonNull String getBuilderName() {
			return "OCL";
		}
	}

	private static final Logger log = Logger.getLogger(OCLBuilder.class);
	public static final String BUILDER_ID = OCLProjectHelper.BUILDER_ID;

	public static void deleteMarkers(@NonNull IProject project, Map<String, String> arguments) throws CoreException {
		AbstractBuildSelector buildSelector = new OCLBuildSelector(project, BuildType.CLEAN, arguments, null);
		buildSelector.selectResources(null);
		buildSelector.deleteMarkers();
	}
	public OCLBuilder() {
		//		System.out.println(NameUtil.debugSimpleName(this) + " init");
	}

	@Override
	protected @NonNull AbstractBuildSelector createBuildSelector(@NonNull IProject project, @NonNull BuildType buildType,
			@Nullable Map<String, String> args, @Nullable IProgressMonitor monitor) {
		return new OCLBuildSelector(project, buildType, args, monitor);
	}

	@Override
	protected Logger getLog() {
		return log;
	}
}
