/*******************************************************************************
 * Copyright (c) 2014, 2019 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.debug.ui.delegate;

import java.util.HashMap;
import java.util.Map;

//import org.eclipse.core.filesystem.EFS;
//import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfigurationType;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.debug.core.model.IDebugTarget;
import org.eclipse.debug.core.model.IProcess;
import org.eclipse.debug.core.model.IThread;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.ocl.examples.debug.launching.OCLLaunchConstants;
import org.eclipse.ocl.examples.debug.ui.OCLDebugUIPlugin;
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.evaluation.EvaluationException;
import org.eclipse.ocl.pivot.internal.delegate.OCLDelegateDomain;
import org.eclipse.ocl.pivot.internal.delegate.OCLDelegateException;
import org.eclipse.ocl.pivot.internal.delegate.OCLSettingDelegate;
import org.eclipse.ocl.pivot.internal.messages.PivotMessagesInternal;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;

/**
 * An implementation of a setting delegate that computes OCL derived features.
 */
public class OCLDebugSettingDelegate extends OCLSettingDelegate
{
	/**
	 * The DebugStarter sequences the start up of the debugger off the thread.
	 */
	protected static class DebugStarter implements IRunnableWithProgress
	{
		protected final @NonNull Display display;
		//	protected final @NonNull MetamodelManager metamodelManager;
		protected final @Nullable Object contextObject;
		protected final @NonNull ExpressionInOCL constraint;
		private @Nullable ILaunch launch = null;

		public DebugStarter(@NonNull Display display, /*@NonNull MetamodelManager metamodelManager,*/ @Nullable Object contextObject, @NonNull ExpressionInOCL constraint) {
			this.display = display;
			//		this.metamodelManager = metamodelManager;
			this.contextObject = contextObject;
			this.constraint = constraint;
		}

		public ILaunch getLaunch() {
			return launch;
		}

		/**
		 * Create and launch an internal launch configuration to debug expressionInOCL applied to contextObject.
		 */
		protected ILaunch launchDebugger(IProgressMonitor monitor, @Nullable Object contextObject, @NonNull ExpressionInOCL expressionInOCL) throws CoreException {
			ILaunchManager launchManager = DebugPlugin.getDefault().getLaunchManager();
			ILaunchConfigurationType launchConfigurationType = launchManager.getLaunchConfigurationType(OCLLaunchConstants.LAUNCH_CONFIGURATION_TYPE_ID);
			ILaunchConfigurationWorkingCopy launchConfiguration = launchConfigurationType.newInstance(null, "test" /*constraint.getName()*/);
			Map<String,Object> attributes = new HashMap<String,Object>();
			attributes.put(OCLLaunchConstants.EXPRESSION_OBJECT, expressionInOCL);
			attributes.put(OCLLaunchConstants.CONTEXT_OBJECT, contextObject);
			launchConfiguration.setAttributes(attributes);
			return launchConfiguration.launch(ILaunchManager.DEBUG_MODE, monitor);
		}

		protected void openError(final String message, final @NonNull Exception e) {
			display.asyncExec(new Runnable()
			{
				@Override
				public void run() {
					IStatus status = new Status(IStatus.ERROR, OCLDebugUIPlugin.PLUGIN_ID, e.getLocalizedMessage(), e);
					ErrorDialog.openError(display.getActiveShell(), "ConsoleMessages.Debug_Starter", message, status);
				}
			});
		}

		@Override
		public void run(IProgressMonitor monitor) {
			String expression = constraint.toString();
			monitor.beginTask(NLS.bind("ConsoleMessages.Debug_Starter", expression), 1);
			try {
				monitor.subTask("ConsoleMessages.Debug_ProgressLoad");
				try {
					launch = launchDebugger(monitor, contextObject, constraint);
				} catch (CoreException e) {
					openError("ConsoleMessages.Debug_FailLaunch", e);
				}
				monitor.worked(1);
			}
			finally {
				monitor.done();
			}
		}
	}

	public OCLDebugSettingDelegate(@NonNull OCLDelegateDomain delegateDomain, @NonNull EStructuralFeature structuralFeature) {
		super(delegateDomain, structuralFeature);
	}

	//	@Override
	//	protected @Nullable Object evaluateEcore(@NonNull OCL ocl, @NonNull ExpressionInOCL query, @Nullable Object contextObject) {
	//		return evaluateEcore(query, contextObject);
	//	}

	//	@Override
	protected @Nullable Object evaluateEcore(/*@NonNull OCL ocl,*/ @NonNull ExpressionInOCL query, @Nullable Object contextObject) {
		//	MetamodelManager metamodelManager = ocl.getMetamodelManager();
		@SuppressWarnings("null")@NonNull Display display = Display.getCurrent();
		DebugStarter runnable = new DebugStarter(display, /*metamodelManager,*/ contextObject, query);
		runnable.run(new NullProgressMonitor());
		ILaunch launch = runnable.getLaunch();
		if (launch != null) {
			try {
				waitForLaunchToTerminate(launch);
				//				launch.
			} catch (DebugException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return launch != null;
	}

	public static void flushEvents() {
		for (int i = 0; i < 10; i++) {
			IWorkbench workbench = PlatformUI.getWorkbench();
			while (workbench.getDisplay().readAndDispatch());
		}
	}

	@Override
	protected Object get(InternalEObject owner, boolean resolve, boolean coreType) {
		try {
			ExpressionInOCL query2 = getQuery();



			//	MetamodelManager metamodelManager = ocl.getMetamodelManager();
			@SuppressWarnings("null")@NonNull Display display = Display.getCurrent();
			DebugStarter runnable = new DebugStarter(display, /*metamodelManager,*/ owner, query2);
			runnable.run(new NullProgressMonitor());
			ILaunch launch = runnable.getLaunch();
			if (launch != null) {
				try {
					waitForLaunchToTerminate(launch);
					//					launch.
				} catch (DebugException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return launch != null;






			//	OCL ocl = delegateDomain.getOCL();
			//	QueryImpl2 query3 = new QueryImpl2(ocl.getEnvironmentFactory(), ocl.getModelManager(), query2);
			//	return query3.evaluateEcore(eStructuralFeature.getEType().getInstanceClass(), owner);
		}
		catch (EvaluationException e) {
			throw new OCLDelegateException(new EvaluationException(e, PivotMessagesInternal.EvaluationResultIsInvalid_ERROR_, basicGetProperty()));
		}
	}

	protected void waitForLaunchToTerminate(@NonNull ILaunch launch) throws InterruptedException, DebugException {
		while (true) {
			for (int i = 0; i < 10; i++){
				flushEvents();
				Thread.sleep(100);
			}
			boolean allDead = true;
			for (IDebugTarget debugTarget : launch.getDebugTargets()) {
				IProcess process = debugTarget.getProcess();
				if (!process.isTerminated()) {
					allDead = false;
				}
				for (IThread debugThread : debugTarget.getThreads()) {
					if (!debugThread.isTerminated()) {
						allDead = false;
					}
				}
			}
			if (allDead) {
				break;
			}
		}
	}
}
