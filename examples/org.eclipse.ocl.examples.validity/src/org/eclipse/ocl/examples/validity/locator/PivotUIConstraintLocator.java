/*******************************************************************************
 * Copyright (c) 2014, 2021 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *	E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.validity.locator;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfigurationType;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.ocl.examples.debug.launching.OCLLaunchConstants;
import org.eclipse.ocl.examples.emf.validation.validity.ResultConstrainingNode;
import org.eclipse.ocl.examples.emf.validation.validity.ValidatableNode;
import org.eclipse.ocl.examples.emf.validation.validity.locator.ConstraintLocator;
import org.eclipse.ocl.examples.emf.validation.validity.ui.locator.ConstraintUILocator;
import org.eclipse.ocl.examples.emf.validation.validity.ui.view.ValidityView;
import org.eclipse.ocl.examples.validity.plugin.OCLValidityPlugin;
import org.eclipse.ocl.examples.xtext.console.XtextConsolePlugin;
import org.eclipse.ocl.examples.xtext.console.messages.ConsoleMessages;
import org.eclipse.ocl.pivot.Constraint;
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.LanguageExpression;
import org.eclipse.ocl.pivot.internal.messages.PivotMessagesInternal;
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal;
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal.EnvironmentFactoryInternalExtension;
import org.eclipse.ocl.pivot.internal.utilities.PivotConstantsInternal;
import org.eclipse.ocl.pivot.internal.utilities.PivotUtilInternal;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.ParserException;
import org.eclipse.ocl.pivot.utilities.StringUtil;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.widgets.Shell;

public class PivotUIConstraintLocator extends PivotConstraintLocator implements ConstraintUILocator
{
	/**
	 * The DebugStarter sequences the start up of the debugger off the thread.
	 */
	protected static class DebugStarter implements IRunnableWithProgress
	{
		protected final @NonNull Shell shell;
		protected final @NonNull EnvironmentFactoryInternal environmentFactory;
		protected final @Nullable EObject contextObject;
		protected final @NonNull ExpressionInOCL constraint;
		private @Nullable ILaunch launch = null;

		public DebugStarter(@NonNull Shell shell, @NonNull EnvironmentFactoryInternal environmentFactory, @Nullable EObject contextObject, @NonNull ExpressionInOCL constraint) {
			this.shell = shell;
			this.environmentFactory = environmentFactory;
			this.contextObject = contextObject;
			this.constraint = constraint;
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
			String expression = constraint.toString();
			monitor.beginTask(NLS.bind(ConsoleMessages.Debug_Starter, expression), 1);
			try {
				monitor.subTask(ConsoleMessages.Debug_ProgressLoad);
				try {
					launch = launchDebugger(monitor, contextObject, constraint);
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

	public static @NonNull PivotUIConstraintLocator INSTANCE = new PivotUIConstraintLocator();

	public static @NonNull IStatus createStatus(Throwable e, String messageTemplate, Object... bindings) {
		String message = StringUtil.bind(messageTemplate, bindings);
		return new Status(IStatus.ERROR, OCLValidityPlugin.PLUGIN_ID, 0, message, e);
	}

	@Override
	public boolean debug(@NonNull ResultConstrainingNode resultConstrainingNode, final @NonNull ValidityView validityView, @NonNull IProgressMonitor monitor) throws CoreException {
		ValidatableNode validatableNode = resultConstrainingNode.getResultValidatableNode().getParent();
		assert validatableNode != null;
		EObject constrainedObject = validatableNode.getConstrainedObject();
		EnvironmentFactoryInternalExtension environmentFactory = (EnvironmentFactoryInternalExtension) PivotUtilInternal.getEnvironmentFactory(constrainedObject);
		Constraint asConstraint = null;
		Object constrainingObject = resultConstrainingNode.getParent().getConstrainingObject();
		if (constrainingObject instanceof Constraint) {
			asConstraint = (Constraint)constrainingObject;
		}
		if (asConstraint == null) {
			IStatus status = createStatus(null, PivotMessagesInternal.MissingSpecification_ERROR_, NameUtil.qualifiedNameFor(asConstraint), PivotConstantsInternal.CONSTRAINT_ROLE);
			throw new CoreException(status);
		}
		LanguageExpression specification = asConstraint.getOwnedSpecification();
		if (specification == null) {
			IStatus status = createStatus(null, PivotMessagesInternal.MissingSpecificationBody_ERROR_, NameUtil.qualifiedNameFor(asConstraint), PivotConstantsInternal.CONSTRAINT_ROLE);
			throw new CoreException(status);
		}
		ExpressionInOCL query;
		try {
			query = environmentFactory.parseSpecification(specification);
		} catch (ParserException e) {
			IStatus status = createStatus(e, PivotMessagesInternal.InvalidSpecificationBody_ERROR_, NameUtil.qualifiedNameFor(asConstraint), PivotConstantsInternal.CONSTRAINT_ROLE);
			throw new CoreException(status);
		}
		ValidatableNode parent = resultConstrainingNode.getResultValidatableNode().getParent();
		if (parent == null) {
			return false;
		}
		EObject eObject = parent.getConstrainedObject();

		Shell shell = validityView.getSite().getShell();
		if (shell == null) {
			return false;
		}
		DebugStarter runnable = new DebugStarter(shell, environmentFactory, eObject, query);
		runnable.run(monitor);
		return runnable.getLaunch() != null;
	}

	@Override
	public @NonNull ConstraintLocator getInstance() {
		return INSTANCE;
	}
}
