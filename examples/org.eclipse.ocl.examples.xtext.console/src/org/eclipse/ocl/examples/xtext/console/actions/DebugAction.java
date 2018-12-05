/*******************************************************************************
 * Copyright (c) 2014, 2018 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *	E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.xtext.console.actions;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfigurationType;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ocl.examples.debug.launching.OCLLaunchConstants;
import org.eclipse.ocl.examples.debug.vm.ui.launching.LaunchingUtils;
import org.eclipse.ocl.examples.debug.vm.ui.utils.DebugUtil;
import org.eclipse.ocl.examples.xtext.console.OCLConsolePage;
import org.eclipse.ocl.examples.xtext.console.XtextConsolePlugin;
import org.eclipse.ocl.examples.xtext.console.messages.ConsoleMessages;
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.EnvironmentFactory;
import org.eclipse.ocl.pivot.utilities.ParserException;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.xtext.base.ui.model.BaseDocument;
import org.eclipse.ocl.xtext.base.utilities.BaseCSResource;
import org.eclipse.ocl.xtext.base.utilities.ElementUtil;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.progress.IProgressService;

/**
 * DebugAction launches the OCL debugger using the currently selected object as self abd the text in the Cosle Input as the OCL to execute..
 */
public final class DebugAction extends Action
{
	/**
	 * The DebugStarter sequences the start up of the debugger off the thread.
	 */
	protected static class DebugStarter implements IRunnableWithProgress
	{
		protected final @NonNull Shell shell;
		protected final @NonNull EnvironmentFactoryInternal environmentFactory;
		protected final @Nullable EObject contextObject;
		protected final @NonNull String expression;
		private @Nullable ILaunch launch = null;

		public DebugStarter(@NonNull Shell shell, @NonNull EnvironmentFactory environmentFactory, @Nullable EObject contextObject, @NonNull String expression) {
			this.shell = shell;
			this.environmentFactory = (EnvironmentFactoryInternal) environmentFactory;
			this.contextObject = contextObject;
			this.expression = expression;
		}

		/**
		 * Create a test Complete OCL document that wraps the required OCL text up as the body of a test operation.
		 * Returns its URI.
		 */
		protected @NonNull URI createDocument(IProgressMonitor monitor) throws IOException, CoreException {
			return DebugUtil.createDebugDocument(environmentFactory, contextObject, expression, monitor);
		}

		public ILaunch getLaunch() {
			return launch;
		}

		/**
		 * Create and launch an internal launch configuration to debug expressionInOCL applied to contextObject.
		 */
		protected ILaunch launchDebugger(IProgressMonitor monitor, @Nullable EObject contextObject, @NonNull ExpressionInOCL expressionInOCL) throws CoreException {
			ILaunchManager launchManager = DebugPlugin.getDefault().getLaunchManager();
			ILaunchConfigurationType launchConfigurationType = launchManager.getLaunchConfigurationType(OCLLaunchConstants.LAUNCH_CONFIGURATION_TYPE_ID);
			ILaunchConfigurationWorkingCopy launchConfiguration = launchConfigurationType.newInstance(null, "test" /*constraint.getName()*/);
			Map<String,Object> attributes = new HashMap<String,Object>();
			attributes.put(OCLLaunchConstants.EXPRESSION_OBJECT, expressionInOCL);
			attributes.put(OCLLaunchConstants.CONTEXT_OBJECT, contextObject);
			launchConfiguration.setAttributes(attributes);
			return launchConfiguration.launch(ILaunchManager.DEBUG_MODE, monitor);
		}

		/**
		 * Load and parse the test document.
		 * @throws IOException
		 */
		protected @Nullable BaseCSResource loadDocument(IProgressMonitor monitor, @NonNull URI documentURI) throws Exception {
			ResourceSet externalResourceSet = environmentFactory.getResourceSet();
			if (contextObject != null) {
				Resource contextResource = contextObject.eResource();
				if (contextResource != null) {
					ResourceSet contextResourceSet = contextResource.getResourceSet();
					if (contextResourceSet != null) {
						environmentFactory.addExternalResources(contextResourceSet);
					}
					else {
						if (externalResourceSet instanceof ResourceSetImpl) {
							Map<URI, Resource> uriResourceMap = ((ResourceSetImpl)externalResourceSet).getURIResourceMap();
							if (uriResourceMap != null) {
								uriResourceMap.put(contextResource.getURI(), contextResource);
							}
						}
					}
				}
			}
			Resource resource = externalResourceSet.getResource(documentURI, true);
			if (resource instanceof BaseCSResource) {
				return (BaseCSResource)resource;
			}
			return null;
		}

		protected void openError(final String message) {
			shell.getDisplay().asyncExec(new Runnable()
			{
				@Override
				public void run() {
					MessageDialog.openError(shell, ConsoleMessages.Debug_Starter, message);
				}
			});
		}

		protected void openError(final String message, final @NonNull Exception e) {
			shell.getDisplay().asyncExec(new Runnable()
			{
				@Override
				public void run() {
					IStatus status = new Status(IStatus.ERROR, XtextConsolePlugin.PLUGIN_ID, e.getLocalizedMessage(), e);
					ErrorDialog.openError(shell, ConsoleMessages.Debug_Starter, message, status);
				}
			});
		}

		@Override
		public void run(IProgressMonitor monitor) {
			monitor.beginTask(NLS.bind(ConsoleMessages.Debug_Starter, expression), 3);
			try {
				monitor.subTask(ConsoleMessages.Debug_ProgressCreate);
				URI documentURI;
				try {
					documentURI = createDocument(monitor);
				} catch (Exception e) {
					openError(ConsoleMessages.Debug_FailCreate, e);
					return;
				}
				monitor.worked(1);
				monitor.subTask(ConsoleMessages.Debug_ProgressLoad);
				BaseCSResource csResource;
				@SuppressWarnings("null")@NonNull String debug_FailLoad = ConsoleMessages.Debug_FailLoad;
				try {
					csResource = loadDocument(monitor, documentURI);
				} catch (Exception e) {
					openError(debug_FailLoad, e);
					return;
				}
				if (csResource == null) {
					openError(debug_FailLoad);
					return;
				}
				String message = PivotUtil.formatResourceDiagnostics(ClassUtil.nonNullEMF(csResource.getErrors()), debug_FailLoad, "\n\t");
				if (message != null) {
					openError(message);
					return;
				}
				ExpressionInOCL query;
				try {
					query = ElementUtil.getFirstQuery(environmentFactory.getMetamodelManager(), csResource);
				} catch (ParserException e) {
					openError(debug_FailLoad, e);
					return;
				}
				if (query == null) {
					openError(debug_FailLoad);
					return;
				}
				monitor.worked(1);
				monitor.subTask(ConsoleMessages.Debug_ProgressLoad);
				try {
					LaunchingUtils.loadPerspectiveManager();
					launch = launchDebugger(monitor, contextObject, query);
				} catch (CoreException e) {
					openError(ConsoleMessages.Debug_FailLaunch, e);
				}
				monitor.worked(1);
			}
			finally {
				monitor.done();
			}
		}
	}

	protected final @NonNull OCLConsolePage oclConsolePage;

	public DebugAction(@NonNull OCLConsolePage oclConsolePage) {
		super(ConsoleMessages.Debug_Title, ImageDescriptor.createFromURL(
			FileLocator.find(XtextConsolePlugin.getInstance().getBundle(),
				new Path("$nl$/icons/elcl16/debug.gif"), null)));
		this.oclConsolePage = oclConsolePage;
		setToolTipText(ConsoleMessages.Debug_ToolTip);
	}

	public ILaunch launch() {
		Control control = oclConsolePage.getControl();
		Shell shell = control != null ? control.getShell() : null;
		if (shell == null) {
			MessageDialog.openError(shell, ConsoleMessages.Debug_Starter, ConsoleMessages.Debug_FailStart_NoShell);
			return null;
		}
		EObject contextObject = oclConsolePage.getContextObject();
		BaseDocument editorDocument = oclConsolePage.getEditorDocument();
		String text = editorDocument.get();
		String expression = text.trim();
		if (expression.length() <= 0) {
			MessageDialog.openError(shell, ConsoleMessages.Debug_Starter, ConsoleMessages.Debug_FailStart_NoOCL);
			return null;
		}
		IProgressService progressService = PlatformUI.getWorkbench().getProgressService();
		EnvironmentFactory environmentFactory = oclConsolePage.getEnvironmentFactory(contextObject);
		DebugStarter runnable = new DebugStarter(shell, environmentFactory, contextObject, expression);
		try {
			progressService.run(true, true, runnable);
		} catch (InvocationTargetException e) {
			Throwable targetException = e.getTargetException();
			IStatus status = new Status(IStatus.ERROR, XtextConsolePlugin.PLUGIN_ID, targetException.getLocalizedMessage(), targetException);
			ErrorDialog.openError(shell, ConsoleMessages.Debug_Starter, ConsoleMessages.Debug_FailStart, status);
		} catch (InterruptedException e) {
			/* Cancel is not a problem. */
		}
		return runnable.getLaunch();
	}

	@Override
	public void run() {
		launch();
	}
}
