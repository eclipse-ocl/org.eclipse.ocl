/*******************************************************************************
 * Copyright (c) 2015, 2020 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.xtext.tests;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStream;
import java.io.Reader;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.IJobManager;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.debug.core.model.IProcess;
import org.eclipse.debug.core.model.IThread;
import org.eclipse.debug.internal.ui.DebugUIPlugin;
import org.eclipse.debug.internal.ui.IInternalDebugUIConstants;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.jface.dialogs.MessageDialogWithToggle;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.intro.IIntroManager;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipse.xtext.ui.editor.validation.ValidationJob;
import org.osgi.framework.Bundle;

import junit.framework.TestCase;

@SuppressWarnings("restriction")
public class TestUIUtil
{
	private static boolean testedEgitUiBundle = false;

	public static void closeIntro() {
		IIntroManager introManager = PlatformUI.getWorkbench().getIntroManager();
		introManager.closeIntro(introManager.getIntro());
	}

	public static void cancelAndWaitForValidationJob() throws InterruptedException {
		IJobManager jobManager = Job.getJobManager();
		for (Job job : jobManager.find(null)) {
			if (job instanceof ValidationJob) {
				//				System.out.println(Thread.currentThread().getName() + " cancel " + NameUtil.debugSimpleName(job));
				if (!job.cancel()) {
					int i = 0;
					while ((job.getState() == Job.RUNNING) && (i++ < 10)) {
						//						System.out.println(Thread.currentThread().getName() + " waiting for " + NameUtil.debugSimpleName(job));
						flushEvents();
						Thread.sleep(100);
					}
				}
			}
		}
	}

	public static @NonNull IFile copyIFile(/*@NonNull*/ IFile outFile, @NonNull URI uri, String encoding) throws CoreException, FileNotFoundException {
		String string = uri.isFile() ? uri.toFileString() : uri.toString();
		Reader reader = new BufferedReader(new FileReader(string));
		if (encoding == null) {
			encoding = URIConverter.ReadableInputStream.getEncoding(reader);
		}
		InputStream inputStream = new URIConverter.ReadableInputStream(reader, encoding);
		outFile.create(inputStream, true, null);
		return outFile;
	}


	public static @NonNull FileEditorInput createFileEditorInput(@NonNull IContainer container, @NonNull String fileName, @NonNull InputStream inputStream) throws CoreException {
		IFile file1 = container.getFile(new Path(fileName));
		file1.create(inputStream, true, null);
		return new FileEditorInput(file1) {};	// Ensure classloader is here
	}

	public static @NonNull IProject createIProject(String projectName) throws CoreException {
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		IWorkspaceRoot root = workspace.getRoot();
		IProject project = root.getProject(projectName);
		if (!project.exists()) {
			project.create(null);
		}
		project.open(null);
		return project;
	}

	public static void deleteIProject(@NonNull String testProjectName) throws Exception {
		suppressGitPrefixPopUp();
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		IProject project = workspace.getRoot().getProject(testProjectName);
		project.delete(true, true, null);
	}

	public static void enableSwitchToDebugPerspectivePreference() {
		DebugUIPlugin.getDefault().getPreferenceStore().setValue(IInternalDebugUIConstants.PREF_SWITCH_TO_PERSPECTIVE, MessageDialogWithToggle.ALWAYS);
	}

	public static void flushEvents() {
		IWorkbench workbench = PlatformUI.getWorkbench();
		for (int i = 0; i < 10; i++) {
			while (workbench.getDisplay().readAndDispatch());
		}
		/*		for (int i = 0; i < 10; i++) {
			IWorkbench workbench = PlatformUI.getWorkbench();
			try {
				while (workbench.getDisplay().readAndDispatch())
					;
			}
//			catch (InterruptedException e) {
//				throw e;
//			}
			catch (Throwable e) {
				if (e instanceof InterruptedException) {
					throw (InterruptedException)e;
				}
				e.printStackTrace();
			}
		} */
	}

	public static void removeTerminatedLaunches(ILaunch[] elements) {
		List<ILaunch> removed = new ArrayList<ILaunch>();
		for (int i = 0; i < elements.length; i++) {
			ILaunch launch = elements[i];
			if (launch.isTerminated()) {
				removed.add(launch);
			}
		}
		if (!removed.isEmpty()) {
			ILaunchManager manager = DebugPlugin.getDefault().getLaunchManager();
			manager.removeLaunches(removed.toArray(new ILaunch[removed.size()]));
		}
	}

	/**
	 * Suppress diagnostics from EGIT
	 * <p>
	 * This was originally necessary to eliminate a model PopUp that locked up the tests (Bug 390479).
	 * <p>
	 * Now it just suppresses a Console Log entry.
	 */
	public static void suppressGitPrefixPopUp() {
		if (!testedEgitUiBundle) {
			testedEgitUiBundle = true;
			Bundle egitUiBundle = Platform.getBundle("org.eclipse.egit.ui");
			if (egitUiBundle != null) {
				try {
					Class<?> activatorClass = egitUiBundle.loadClass("org.eclipse.egit.ui.Activator");
					Class<?> preferencesClass = egitUiBundle.loadClass("org.eclipse.egit.ui.UIPreferences");
					Method getDefaultMethod = activatorClass.getMethod("getDefault");
					AbstractUIPlugin activator = (AbstractUIPlugin) getDefaultMethod.invoke(null);
					IPreferenceStore store = activator.getPreferenceStore();
					Field field = preferencesClass.getField("SHOW_GIT_PREFIX_WARNING");
					String name = (String)field.get(null);
					store.setValue(name, false);
					field = preferencesClass.getField("SHOW_HOME_DIR_WARNING");
					name = (String)field.get(null);
					store.setValue(name, false);
				}
				catch (Exception e) {}			// Ignore
			}
		}
	}

	public static void wait(int delayTimeInMilliseconds) {
		for (int i = 0; i < delayTimeInMilliseconds; i += 100) {
			flushEvents();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {}
		}
	}

	public static @Nullable List<@NonNull IStatus> waitForLaunchToTerminate(@NonNull ILaunch launch) throws InterruptedException, DebugException {
		while (true) {
			for (int i = 0; i < 10; i++){
				TestUIUtil.flushEvents();
				Thread.sleep(100);
			}
			boolean allTerminated = true;
			List<@NonNull IStatus> allResults = null;
			/*	for (IDebugTarget debugTarget : launch.getDebugTargets()) {
				IProcess process = debugTarget.getProcess();
				if (!process.isTerminated()) {
					allDead = false;
				}
				for (IThread debugThread : debugTarget.getThreads()) {
					if (!debugThread.isTerminated()) {
						allTerminated = false;
					}
				}
			} */
			for (IProcess process : launch.getProcesses()) {
				if (!process.isTerminated()) {
					allTerminated = false;
				}
				else {
					if (allResults == null) {
						allResults = new ArrayList<>();
					}
					if (process instanceof Job) {
						IStatus result = ((Job)process).getResult();
						assert result != null;		// isTerminated() => non-null result
						allResults.add(result);
					}
					else {
						allResults.add(new Status(process.getExitValue(), (String)null, (String)null));
					}
				}
			}
			if (allTerminated) {
				return allResults;
			}
		}
	}

	public static void waitForNotStepping(@NonNull IThread vmThread) throws InterruptedException, DebugException {
		for (int i = 0; i < 10; i++){
			flushEvents();
			Thread.sleep(100);
			if (!vmThread.isStepping()) {
				return;
			}
		}
		TestCase.fail("Failed to not-step");
	}

	public static void waitForSuspended(@NonNull IThread vmThread) throws InterruptedException, DebugException {
		for (int i = 0; i < 10; i++){
			flushEvents();
			Thread.sleep(100);
			if (vmThread.isSuspended()) {
				return;
			}
		}
		TestCase.fail("Failed to suspend");
	}

	public static void waitForTerminated(@NonNull IThread vmThread) throws InterruptedException, DebugException {
		for (int i = 0; i < 10; i++){
			flushEvents();
			Thread.sleep(100);
			if (vmThread.isTerminated()) {
				return;
			}
		}
		TestCase.fail("Failed to terminate");
	}
}
