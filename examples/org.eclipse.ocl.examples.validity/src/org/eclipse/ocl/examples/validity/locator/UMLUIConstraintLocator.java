/*******************************************************************************
 * Copyright (c) 2014 E.D.Willink and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *	E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.validity.locator;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.filesystem.EFS;
import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
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
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.ocl.examples.debug.launching.OCLLaunchConstants;
import org.eclipse.ocl.examples.domain.elements.DomainClass;
import org.eclipse.ocl.examples.domain.utilities.DomainUtil;
import org.eclipse.ocl.examples.emf.validation.validity.LeafConstrainingNode;
import org.eclipse.ocl.examples.emf.validation.validity.ResultConstrainingNode;
import org.eclipse.ocl.examples.emf.validation.validity.ValidatableNode;
import org.eclipse.ocl.examples.emf.validation.validity.ui.locator.ConstraintUILocator;
import org.eclipse.ocl.examples.emf.validation.validity.ui.view.IDEValidityManager;
import org.eclipse.ocl.examples.emf.validation.validity.ui.view.ValidityView;
import org.eclipse.ocl.examples.pivot.Constraint;
import org.eclipse.ocl.examples.pivot.ExpressionInOCL;
import org.eclipse.ocl.examples.pivot.LanguageExpression;
import org.eclipse.ocl.examples.pivot.ParserException;
import org.eclipse.ocl.examples.pivot.Model;
import org.eclipse.ocl.examples.pivot.manager.MetaModelManager;
import org.eclipse.ocl.examples.pivot.manager.PivotIdResolver;
import org.eclipse.ocl.examples.pivot.prettyprint.PrettyPrintOptions;
import org.eclipse.ocl.examples.pivot.prettyprint.PrettyPrinter;
import org.eclipse.ocl.examples.pivot.utilities.PivotUtil;
import org.eclipse.ocl.examples.xtext.base.utilities.BaseCSResource;
import org.eclipse.ocl.examples.xtext.base.utilities.ElementUtil;
import org.eclipse.ocl.examples.xtext.console.XtextConsolePlugin;
import org.eclipse.ocl.examples.xtext.console.messages.ConsoleMessages;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.widgets.Shell;

public class UMLUIConstraintLocator extends UMLConstraintLocator implements ConstraintUILocator
{
    /**
     * The DebugStarter sequences the start up of the debugger off the thread.
     */
    protected static class DebugStarter implements IRunnableWithProgress
	{
		protected final @NonNull Shell shell;
    	protected final @NonNull MetaModelManager metaModelManager;
    	protected final @Nullable EObject contextObject;
    	protected final @NonNull String expression;
    	private @Nullable ILaunch launch = null;

		public DebugStarter(@NonNull Shell shell, @NonNull MetaModelManager metaModelManager, @Nullable EObject contextObject, @NonNull String expression) {
			this.shell = shell;
			this.metaModelManager = metaModelManager;
			this.contextObject = contextObject;
			this.expression = expression;
		}

		/**
		 * Create a test Complete OCL document that wraps the required OCL text up as the body of a test operation.
		 * Returns its URI.
		 */
		protected @NonNull URI createDocument(IProgressMonitor monitor) throws IOException, CoreException {
			PivotIdResolver idResolver = metaModelManager.getIdResolver();
			DomainClass staticType = idResolver.getStaticTypeOf(contextObject);
			org.eclipse.ocl.examples.pivot.Class contextType = metaModelManager.getType(staticType);
//			if (contextType instanceof Metaclass) {
//				contextType = (org.eclipse.ocl.examples.pivot.Class)((Metaclass<?>)contextType).getInstanceType();	// FIXME cast
//			}
			org.eclipse.ocl.examples.pivot.Package contextPackage = contextType.getOwningPackage();
			IPath documentPath = XtextConsolePlugin.getInstance().getStateLocation().append("debug" + EcoreUtil.generateUUID() + ".ocl");
			IFileStore documentStore = EFS.getLocalFileSystem().getStore(documentPath);
			OutputStream documentStream = documentStore.openOutputStream(0, monitor);
			PrettyPrintOptions.Global printOptions = PrettyPrinter.createOptions(null);
			printOptions.addReservedNames(PrettyPrinter.restrictedNameList);
			Writer s = new OutputStreamWriter(documentStream);
			String externalURI = null;
			if (contextPackage != null) {
				Model containingRoot = PivotUtil.getContainingRoot(contextPackage);
				if (containingRoot == null) {
					externalURI = contextPackage.getURI();
				}
				else if (containingRoot != PivotUtil.getContainingRoot(metaModelManager.getStandardLibrary().getOclAnyType())) {
					externalURI = containingRoot.getExternalURI();
					if (PivotUtil.isASURI(externalURI)) {
						@SuppressWarnings("null")
						@NonNull URI uri = URI.createURI(externalURI);
						externalURI = PivotUtil.getNonASURI(uri).toString();
					}
				}
				if (externalURI != null) {
					s.append("import '" + externalURI + "'\n\n");
				}
//				s.append("package " + PrettyPrinter.printName(contextPackage, printOptions) + "\n\n");
			}
			s.append("context ");
			if (externalURI == null) {
				s.append("ocl::");			// FIXME use printOptions, FIXME support UML non-OCL classes
			}
			s.append(PrettyPrinter.printName(contextType, printOptions) + "\n");
			s.append("def: oclDebuggerExpression() : OclAny = \n\t");
			s.append(expression.replace("\n", "\n\t"));
			s.append("\n");
//			if (contextPackage != null) {
//				s.append("\n\nendpackage\n");
//			}
			s.close();
			java.net.URI documentURI1 = documentStore.toURI();
			@SuppressWarnings("null")@NonNull URI documentURI2 = URI.createURI(documentURI1.toString());
			return documentURI2;
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
			Resource contextResource = contextObject != null ? contextObject.eResource()  : null;
			ResourceSet resourceSet = contextResource != null ? contextResource.getResourceSet() : null;
			if (resourceSet == null) {
				resourceSet = new ResourceSetImpl();
			}
			Resource resource = resourceSet.getResource(documentURI, true);
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
				String message = PivotUtil.formatResourceDiagnostics(DomainUtil.nonNullEMF(csResource.getErrors()), debug_FailLoad, "\n\t");
				if (message != null) {
					openError(message);
					return;
				}
				ExpressionInOCL query;
				try {
					query = ElementUtil.getFirstQuery(metaModelManager, csResource);
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

    public static @NonNull UMLUIConstraintLocator INSTANCE = new UMLUIConstraintLocator();

	@Override
	public boolean debug(@NonNull ResultConstrainingNode resultConstrainingNode, final @NonNull ValidityView validityView, @NonNull IProgressMonitor monitor) throws CoreException {
		final Object object = resultConstrainingNode.getParent().getConstrainingObject();
		if (!(object instanceof org.eclipse.uml2.uml.Constraint)) {
			throw new IllegalStateException("non-UML Constraint " + object);
//			return false;
		}
		org.eclipse.uml2.uml.Constraint umlConstraint = (org.eclipse.uml2.uml.Constraint)object;
		MetaModelManager metaModelManager = PivotUtil.getMetaModelManager(umlConstraint.eResource());
		Constraint constraint = null;
		try {
			constraint = metaModelManager.getPivotOf(Constraint.class, umlConstraint);
		} catch (ParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (constraint == null) {
			throw new IllegalStateException("non-UML Constraint " + object);
//			return false;
		}
		

		
		
		
//		URI constraintURI = EcoreUtil.getURI(constraint);
//        Path path = new Path(constraintURI.toPlatformString(true));
		LanguageExpression specification = constraint.getSpecification();
		String expression = specification != null ? PrettyPrinter.print(specification) : "";
		
		ValidatableNode parent = resultConstrainingNode.getResultValidatableNode().getParent();
		if (parent == null) {
			return false;
		}
		EObject eObject = parent.getConstrainedObject();
		
		Shell shell = validityView.getSite().getShell();
		DebugStarter runnable = new DebugStarter(shell, metaModelManager, eObject, expression);
		
		
//		IProgressService progressService = PlatformUI.getWorkbench().getProgressService();
//		try {
//			progressService.run(true, true, runnable);
			runnable.run(monitor);
//		} catch (InvocationTargetException e) {
//			Throwable targetException = e.getTargetException();
//			IStatus status = new Status(IStatus.ERROR, XtextConsolePlugin.PLUGIN_ID, targetException.getLocalizedMessage(), targetException);
//			ErrorDialog.openError(shell, ConsoleMessages.Debug_Starter, ConsoleMessages.Debug_FailStart, status);
//		} catch (InterruptedException e) {
			/* Cancel is not a problem. */
//		}
		return runnable.getLaunch() != null;
/*		
		
		IPath trimmedPath = path.removeLastSegments(1);
		IContainer folder = (IContainer) ResourcesPlugin.getWorkspace().getRoot().findMember(trimmedPath);
		Path tailPath = new Path(constraint.getName() + ".essentialocl");
		final IFile file = folder.getFile(tailPath);
		file.create(new URIConverter.ReadableInputStream(string, "UTF-8"), false, null);
		
//		URI contextURI = EcoreUtil.getURI(eObject);

//		ILaunchManager launchManager = DebugPlugin.getDefault().getLaunchManager();
//		ILaunchConfigurationType launchConfigurationType = launchManager.getLaunchConfigurationType(OCLLaunchConstants.LAUNCH_CONFIGURATION_TYPE_ID);
//		ILaunchConfigurationWorkingCopy launchConfiguration = launchConfigurationType.newInstance(folder, constraint.getName());
//		launchConfiguration.setAttribute(OCLLaunchConstants.CONSTRAINT_URI, constraintURI.toString());
//		launchConfiguration.setAttribute(OCLLaunchConstants.CONTEXT_URI, contextURI.toString());
//		launchConfiguration.doSave();
//		launchConfiguration.launch(ILaunchManager.DEBUG_MODE, monitor);
		launchDebugger(monitor, eObject, expressionInOCL);
		return true; */
	}

	@Override
	public boolean openEditor(@NonNull LeafConstrainingNode leafConstrainingNode, @NonNull IDEValidityManager validityManager, @NonNull IProgressMonitor monitor) throws CoreException {
		// TODO Auto-generated method stub
		return false;
	}
}
