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
package org.eclipse.ocl.xtext.base.ui.builder;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.resources.IStorage;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.jdt.core.compiler.CharOperation;
import org.eclipse.ocl.xtext.base.ui.BaseUIActivator;
import org.eclipse.ocl.xtext.base.ui.messages.BaseUIMessages;
import org.eclipse.osgi.util.NLS;

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
	// FIXME it would be nice to use use a derived OCL_PROBLEM_MARKER
	private static final @NonNull String EMF_PROBLEM_MARKER = "org.eclipse.emf.ecore.diagnostic";

	/**
	 * This is a copy of org.eclipse.jdt.internal.compiler.util.Util.isExcluded
	 *
	 * FIXME BUG 529789 requests its availability in CharOperation.
	 */
	private final static boolean isExcluded(char[] path, char[][] inclusionPatterns, char[][] exclusionPatterns, boolean isFolderPath) {
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

	protected static class BuildSelector implements IResourceVisitor, IResourceDeltaVisitor
	{
		protected final @NonNull String builderName;
		protected final @NonNull IProject project;
		protected final @NonNull BuildType buildType;
		private SubMonitor progress;
		private final @NonNull Map<@NonNull String, @Nullable Boolean> extension2included = new HashMap<>();
		private final char[][] exclusionPatterns;
		private final char[][] inclusionPatterns;

		private final @NonNull Set<@NonNull IPath> removedPaths = new HashSet<>();
		private final @NonNull Set<@NonNull IFile> selectedFiles = new HashSet<>();

		public BuildSelector(@NonNull String builderName, @NonNull IProject project, @NonNull BuildType buildType, Map<String, String> args, IProgressMonitor monitor) {
			this.builderName = builderName;
			this.project = project;
			this.buildType = buildType;
			String initializingMessage = NLS.bind(BaseUIMessages.MultiValidationJob_Initializing, builderName, project.getName());
			this.progress = SubMonitor.convert(monitor, initializingMessage, 100);
			String[] disabledPathArray = null;
			String[] enabledPathArray = null;
			if (args != null) {
				String includedExtensions = args.get("enabledExtensions");
				if (includedExtensions != null) {
					for (@NonNull String includedExtension : includedExtensions.split(",")) {
						@SuppressWarnings("unused") Boolean oldIncludes = extension2included.put(includedExtension, Boolean.TRUE);
						// assert oldIncludes != null; double true is not an issue
					}
				}
				String excludedExtensions = args.get("disabledExtensions");
				if (excludedExtensions != null) {
					for (@NonNull String excludedExtension : excludedExtensions.split(",")) {
						@SuppressWarnings("unused") Boolean oldExcludes = extension2included.put(excludedExtension, Boolean.FALSE);
						// assert oldExcludes != null; double false / conflicting false is not really an issue
					}
				}
				String enabledPaths = args.get("enabledPaths");
				if (enabledPaths != null) {
					enabledPathArray = enabledPaths.split(",");
				}
				String disabledPaths = args.get("disabledPaths");
				if (disabledPaths != null) {
					disabledPathArray = disabledPaths.split(",");
				}
			}
			if (enabledPathArray != null) {
				inclusionPatterns = new char[enabledPathArray.length][];
				for (int i = 0; i < enabledPathArray.length; i++) {
					String enabledPath = enabledPathArray[i];
					inclusionPatterns[i] = (enabledPath.length() > 0 ? enabledPath : "**").toCharArray();
				}
			}
			else {
				inclusionPatterns = null;
			}
			if (disabledPathArray != null) {
				exclusionPatterns = new char[disabledPathArray.length][];
				for (int i = 0; i < disabledPathArray.length; i++) {
					String disabledPath = disabledPathArray[i];
					exclusionPatterns[i] = (disabledPath.length() > 0 ? disabledPath : "**").toCharArray();
				}
			}
			else {
				exclusionPatterns = null;
			}
			progress.worked(1);
		}

		public void buildResources() {
			progress.setWorkRemaining(1);
			MultiValidationJob multiValidationJob = BaseUIActivator.getMultiValidationJob();
			if (multiValidationJob != null) {
				multiValidationJob.addValidations(selectedFiles);
				progress.setTaskName(NLS.bind(BaseUIMessages.MultiValidationJob_Queuing, builderName));
			}
			progress.worked(1);
			progress.done();
		}

		public @Nullable Boolean isSelected(@NonNull IResource resource) {
			if (resource instanceof IFile) {
				String fileExtension = resource.getFileExtension();
				if (extension2included.get(fileExtension) != Boolean.TRUE) {
					return Boolean.FALSE;
				}
				String filePath = resource.getProjectRelativePath().toString();
				char[] path = filePath.toCharArray();
				boolean isExcluded = isExcluded(path, inclusionPatterns, exclusionPatterns, false);
				//				System.out.println(filePath + " isExcluded " + isExcluded);
				return isExcluded ? Boolean.FALSE : Boolean.TRUE;
			}
			else if (resource instanceof IFolder) {
				String filePath = resource.getProjectRelativePath().toString();
				char[] path = filePath.toCharArray();
				boolean isExcluded = isExcluded(path, inclusionPatterns, exclusionPatterns, true);
				//				System.out.println(filePath + " isExcluded " + isExcluded);
				return isExcluded ? Boolean.FALSE : null;
			}
			else {
				return null;
			}
		}

		public void selectResources(@Nullable IResourceDelta delta) throws CoreException {
			String selectingMessage = NLS.bind(BaseUIMessages.MultiValidationJob_Selecting, builderName, project.getName());
			//			this.progress = SubMonitor.convert(monitor, selectingMessage, 10);
			progress.subTask(selectingMessage);
			//			progress.subTask(selectingResourcesMessage);
			if (delta == null) {
				project.accept(this, IResource.DEPTH_INFINITE, IResource.NONE);
			}
			else {
				delta.accept(this);
			}
			progress.worked(1);
		}

		@Override
		public boolean visit(IResource resource) throws CoreException {
			assert resource != null;
			//			System.out.println(NameUtil.debugSimpleName(this) + " visit " + resource);
			Boolean isSelected = isSelected(resource);
			if (isSelected == Boolean.TRUE) {
				IFile iFile = (IFile) resource;
				selectedFiles.add(iFile);
				return true;
			}
			else if (isSelected == null) {
				return true;
			}
			else {
				return false;
			}
		}

		@Override
		public boolean visit(IResourceDelta delta) throws CoreException {
			if (progress.isCanceled())
				throw new OperationCanceledException();
			IResource resource = delta.getResource();
			if (resource instanceof IProject) {
				return resource == project;
			}
			if (resource instanceof IStorage) {
				if (delta.getKind() == IResourceDelta.REMOVED) {
					//					System.out.println(NameUtil.debugSimpleName(this) + " remove " + resource);
					Boolean isSelected = isSelected(resource);
					if (isSelected == Boolean.TRUE) {
						IPath fullPath = ((IFile) resource).getFullPath();
						assert fullPath != null;
						removedPaths.add(fullPath);
						return true;
					}
					else if (isSelected == null) {
						return true;
					}
					else {
						return false;
					}
				} else if (delta.getKind() == IResourceDelta.ADDED || delta.getKind() == IResourceDelta.CHANGED) {
					//					System.out.println(NameUtil.debugSimpleName(this) + " update " + resource);
					Boolean isSelected = isSelected(resource);
					if (isSelected == Boolean.TRUE) {
						IFile iFile = (IFile) resource;
						selectedFiles.add(iFile);
						return true;
					}
					else if (isSelected == null) {
						return true;
					}
					else {
						return false;
					}
				}
			}
			return true;
		}

		public void deleteMarkers() {
			for (@NonNull IFile selectedFile : selectedFiles) {
				try {
					selectedFile.deleteMarkers(EMF_PROBLEM_MARKER, true, IResource.DEPTH_ZERO);
				} catch (CoreException e) {
					// e.printStackTrace();  -- if deleteMarkers fails we probably don't want extra noise
				}
			}
		}
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
			BuildSelector buildSelector;
			IResourceDelta delta;
			if (kind == FULL_BUILD) {
				buildSelector = new BuildSelector(getBuilderName(), project, BuildType.FULL, args, monitor);
				delta = null;
			} else {
				delta = getDelta(getProject());
				buildSelector = new BuildSelector(getBuilderName(), project, BuildType.INCREMENTAL, args, monitor);
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

	protected abstract @NonNull String getBuilderName();

	protected abstract Logger getLog();

	private void handleCanceled(Throwable t) {
		BaseUIActivator.cancelMultiValidationJob();
	}
}
