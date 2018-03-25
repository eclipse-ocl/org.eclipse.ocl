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

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.resources.IStorage;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.emf.ecore.EValidator;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.xtext.base.ui.BaseUIActivator;
import org.eclipse.ocl.xtext.base.ui.builder.AbstractValidatingBuilder.BuildType;
import org.eclipse.ocl.xtext.base.ui.messages.BaseUIMessages;
import org.eclipse.osgi.util.NLS;

/**
 * An AbstractBuildSelector performs the selection of files to be built from within a project.
 * Selection is based on inclusion/exclusion of file-extensions/file-paths.
 */
public abstract class AbstractBuildSelector implements IResourceVisitor, IResourceDeltaVisitor
{
	protected final @NonNull IProject project;
	protected final @NonNull BuildType buildType;
	protected final @Nullable IProgressMonitor monitor;
	private final @NonNull SubMonitor progress;
	private final @NonNull Map<@NonNull String, @Nullable Boolean> extension2included = new HashMap<>();
	private final char[][] exclusionPatterns;
	private final char[][] inclusionPatterns;

	private final @NonNull Set<@NonNull IPath> removedPaths = new HashSet<>();
	private final @NonNull Set<@NonNull ValidationEntry> selectedEntries = new HashSet<>();

	protected AbstractBuildSelector(@NonNull IProject project, @NonNull BuildType buildType, @Nullable Map<String, String> args, @Nullable IProgressMonitor monitor) {
		this.project = project;
		this.buildType = buildType;
		this.monitor = monitor;
		String initializingMessage = NLS.bind(BaseUIMessages.MultiValidationJob_Initializing, getBuilderName(), project.getName());
		this.progress = SubMonitor.convert(monitor, initializingMessage, 100);
		System.out.println(Thread.currentThread().getName() + " " + NameUtil.debugSimpleName(progress) + " converted from: " + NameUtil.debugSimpleName(monitor));
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
		System.out.println(Thread.currentThread().getName() + " " + NameUtil.debugSimpleName(progress) + " worked 1");
		progress.worked(1);
	}

	public void buildResources() {
		System.out.println(Thread.currentThread().getName() + " " + NameUtil.debugSimpleName(progress) + " setWorkRemaining 1");
		progress.setWorkRemaining(1);
		MultiValidationJob multiValidationJob = BaseUIActivator.getMultiValidationJob();
		if (multiValidationJob != null) {
			multiValidationJob.addValidations(selectedEntries);
			String message = NLS.bind(BaseUIMessages.MultiValidationJob_Queuing, getBuilderName());
			System.out.println(Thread.currentThread().getName() + " " + NameUtil.debugSimpleName(progress) + " setTaskName: " + message);
			progress.setTaskName(message);
		}
		System.out.println(Thread.currentThread().getName() + " " + NameUtil.debugSimpleName(progress) + " worked: 1");
		progress.worked(1);
		System.out.println(Thread.currentThread().getName() + " " + NameUtil.debugSimpleName(progress) + " done");
		progress.done();
		if (monitor != null) {
			monitor.done();
			System.out.println(Thread.currentThread().getName() + " " + NameUtil.debugSimpleName(monitor) + " done");
		}
	}

	protected @NonNull ValidationEntry createValidationEntry(@NonNull IFile iFile) {
		return new ValidationEntry(iFile, getMarkerId(iFile));
	}

	protected abstract @NonNull String getBuilderName();

	/**
	 * Return the appropriate Problem Marked Id for the resuklts of validating iFile.
	 * Defaults to "org.eclipse.emf.ecore.diagnostic". Subclasses should override.
	 */
	@SuppressWarnings("null")
	protected @NonNull String getMarkerId(@NonNull IFile iFile) {
		return EValidator.MARKER;
	}

	public @Nullable Boolean isSelected(@NonNull IResource resource) {
		if (resource instanceof IFile) {
			String fileExtension = resource.getFileExtension();
			if (extension2included.get(fileExtension) != Boolean.TRUE) {
				return Boolean.FALSE;
			}
			String filePath = resource.getProjectRelativePath().toString();
			char[] path = filePath.toCharArray();
			boolean isExcluded = AbstractValidatingBuilder.isExcluded(path, inclusionPatterns, exclusionPatterns, false);
			//				System.out.println(filePath + " isExcluded " + isExcluded);
			return isExcluded ? Boolean.FALSE : Boolean.TRUE;
		}
		else if (resource instanceof IFolder) {
			String filePath = resource.getProjectRelativePath().toString();
			char[] path = filePath.toCharArray();
			boolean isExcluded = AbstractValidatingBuilder.isExcluded(path, inclusionPatterns, exclusionPatterns, true);
			//				System.out.println(filePath + " isExcluded " + isExcluded);
			return isExcluded ? Boolean.FALSE : null;
		}
		else {
			return null;
		}
	}

	public void selectResources(@Nullable IResourceDelta delta) throws CoreException {
		String selectingMessage = NLS.bind(BaseUIMessages.MultiValidationJob_Selecting, getBuilderName(), project.getName());
		//			this.progress = SubMonitor.convert(monitor, selectingMessage, 10);
		System.out.println(Thread.currentThread().getName() + " " + NameUtil.debugSimpleName(progress) + " subTask: " + selectingMessage);
		progress.subTask(selectingMessage);
		//			progress.subTask(selectingResourcesMessage);
		if (delta == null) {
			project.accept(this, IResource.DEPTH_INFINITE, IResource.NONE);
		}
		else {
			delta.accept(this);
		}
		System.out.println(Thread.currentThread().getName() + " " + NameUtil.debugSimpleName(progress) + " worked: 1");
		progress.worked(1);
	}

	@Override
	public boolean visit(IResource resource) throws CoreException {
		assert resource != null;
		//			System.out.println(NameUtil.debugSimpleName(this) + " visit " + resource);
		Boolean isSelected = isSelected(resource);
		if (isSelected == Boolean.TRUE) {
			IFile iFile = (IFile) resource;
			selectedEntries.add(createValidationEntry(iFile));
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
					selectedEntries.add(createValidationEntry(iFile));
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
		for (@NonNull ValidationEntry entry : selectedEntries) {
			try {
				entry.deleteMarkers();
			} catch (CoreException e) {
				// e.printStackTrace();  -- if deleteMarkers fails we probably don't want extra noise
			}
		}
	}
}