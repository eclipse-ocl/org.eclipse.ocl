/*******************************************************************************
 * Copyright (c) 2017,2018 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation based on org.eclipse.xtext.builder.nature.XtextNature
 *******************************************************************************/
package org.eclipse.ocl.xtext.base.ui.builder;

import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.jdt.core.compiler.CharOperation;
import org.eclipse.ocl.xtext.base.ui.BaseUIActivator;

/**
 * Abstract Builder for OCL or QVTd contributions. Currently this involves identifying relevant files subject to
 * extension filtering defined by the excludeExtension/includeExtensions comma-separated file extensionlist
 * and path filtering defined by excludePaths/includepaths comma-separated classpath-style regexes.
 * Default values are supplied as part of the .project buildCommand when the OCL nature is added.
 *
 * The identified files are passed to a separate MultiValidationJob for concurrent non-blocking validation.
 */
public abstract class AbstractValidatingBuilder extends IncrementalProjectBuilder
{
	// FIXME delete after Photon M7 (once QVTd has caught up)
	@Deprecated
	public static class BuildSelector extends AbstractBuildSelector
	{
		protected final @NonNull String builderName;

		public BuildSelector(@NonNull String builderName, @NonNull IProject project, @NonNull BuildType buildType,
				Map<String, String> args, IProgressMonitor monitor) {
			super(project, buildType, args, monitor);
			this.builderName = builderName;
		}

		@Override
		protected @NonNull String getBuilderName() {
			return builderName;
		}

	}
	/**
	 * This is a copy of org.eclipse.jdt.internal.compiler.util.Util.isExcluded
	 *
	 * FIXME BUG 529789 requests its availability in CharOperation.
	 */
	final static boolean isExcluded(char[] path, char[][] inclusionPatterns, char[][] exclusionPatterns, boolean isFolderPath) {
		if (inclusionPatterns == null && exclusionPatterns == null) return false;

		inclusionCheck: if (inclusionPatterns != null) {
			for (int i = 0, length = inclusionPatterns.length; i < length; i++) {
				char[] pattern = inclusionPatterns[i];
				char[] folderPattern = pattern;
				if (isFolderPath) {
					int lastSlash = CharOperation.lastIndexOf('/', pattern);
					if (lastSlash != -1 && lastSlash != pattern.length-1){ // trailing slash -> adds '**' for free (see http://ant.apache.org/manual/dirtasks.html)
						int star = CharOperation.indexOf('*', pattern, lastSlash);
						if ((star == -1
								|| star >= pattern.length-1
								|| pattern[star+1] != '*')) {
							folderPattern = CharOperation.subarray(pattern, 0, lastSlash);
						}
					}
				}
				if (CharOperation.pathMatch(folderPattern, path, true, '/')) {
					break inclusionCheck;
				}
			}
			return true; // never included
		}
		if (isFolderPath) {
			path = CharOperation.concat(path, new char[] {'*'}, '/');
		}
		if (exclusionPatterns != null) {
			for (int i = 0, length = exclusionPatterns.length; i < length; i++) {
				if (CharOperation.pathMatch(exclusionPatterns[i], path, true, '/')) {
					return true;
				}
			}
		}
		return false;
	}

	protected static enum BuildType
	{
		CLEAN, FULL, INCREMENTAL, RECOVERY
	}

	@Override
	protected IProject[] build(final int kind, Map<String, String> args, IProgressMonitor monitor) throws CoreException {
		//		System.out.println(NameUtil.debugSimpleName(this) + " build " + getKindAsString(kind));
		long startTime = System.currentTimeMillis();
		IProject project = getProject();
		assert project != null;
		try {
			AbstractBuildSelector buildSelector;
			IResourceDelta delta;
			if (kind == FULL_BUILD) {
				buildSelector = createBuildSelector(project, BuildType.FULL, args, monitor);
				delta = null;
			} else {
				delta = getDelta(getProject());
				buildSelector = createBuildSelector(project, BuildType.INCREMENTAL, args, monitor);
			}
			buildSelector.selectResources(delta);
			buildSelector.buildResources();
		} catch (CoreException e) {
			getLog().error(e.getMessage(), e);
			throw e;
		} catch (OperationCanceledException e) {
			handleCanceled(e);
		} catch (Exception e) {
			getLog().error(e.getMessage(), e);
			forgetLastBuiltState();
		} finally {
			if (monitor != null)
				monitor.done();
			String message = "Build " + getProject().getName() + " in " + (System.currentTimeMillis() - startTime) + " ms";
			getLog().info(message);
		}
		return null;
	}

	@Override
	protected void clean(IProgressMonitor monitor) throws CoreException {
		//		System.out.println(NameUtil.debugSimpleName(this) + " clean");
	}

	// FIXME change to abstract after Photon M7 (once QVTd has caught up)
	protected /*abstract*/ @NonNull AbstractBuildSelector createBuildSelector(@NonNull IProject project, @NonNull BuildType buildType,
			@Nullable Map<String, String> args, @Nullable IProgressMonitor monitor) {
		return new BuildSelector(getBuilderName(), project, buildType, args, monitor);
	}

	// FIXME delete after Photon M7 (once QVTd has caught up)
	@Deprecated
	protected @NonNull String getBuilderName() {
		return "FIXME";
	}

	protected abstract Logger getLog();

	private void handleCanceled(Throwable t) {
		BaseUIActivator.cancelMultiValidationJob();
	}
}
