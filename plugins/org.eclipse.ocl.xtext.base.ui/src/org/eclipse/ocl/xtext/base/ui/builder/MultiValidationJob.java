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

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EValidator;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.eclipse.emf.ecore.util.EObjectValidator;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.internal.resource.ProjectMap;
import org.eclipse.ocl.pivot.internal.utilities.PivotDiagnostician;
import org.eclipse.ocl.pivot.internal.utilities.PivotDiagnostician.BasicDiagnosticWithRemove;
import org.eclipse.ocl.pivot.resource.CSResource;
import org.eclipse.ocl.pivot.resource.ProjectManager;
import org.eclipse.ocl.pivot.utilities.LabelUtil;
import org.eclipse.ocl.pivot.utilities.NameUtil.ToStringComparator;
import org.eclipse.ocl.pivot.utilities.OCL;
import org.eclipse.ocl.xtext.base.ui.messages.BaseUIMessages;
import org.eclipse.ocl.xtext.base.utilities.PivotDiagnosticConverter;
import org.eclipse.osgi.util.NLS;
import org.eclipse.xtext.diagnostics.Severity;
import org.eclipse.xtext.ui.editor.validation.IValidationIssueProcessor;
import org.eclipse.xtext.ui.editor.validation.MarkerCreator;
import org.eclipse.xtext.ui.editor.validation.MarkerIssueProcessor;
import org.eclipse.xtext.ui.validation.MarkerTypeProvider;
import org.eclipse.xtext.util.IAcceptor;
import org.eclipse.xtext.validation.IDiagnosticConverter;
import org.eclipse.xtext.validation.Issue;

/**
 * A MultiValidationJob maintains a queue of workspaceRelativeFileNames in need of validation.
 *
 * Validations are performed in a separate job from the builder since the success/failure of an OCL validation
 * does not need to force users to wait as occurs for builder active.
 */
public class MultiValidationJob extends Job
{
	/**
	 * IssueListAcceptor provides a simple implementation of the Issue Acceptor protocol that accumulates all
	 * issues in a list.
	 */
	protected static class IssueListAcceptor implements IAcceptor<@NonNull Issue>
	{
		protected final @NonNull List<@NonNull Issue> issues = new ArrayList<>();

		@Override
		public void accept(@NonNull Issue issue) {
			issues.add(issue);
		}

		public @NonNull List<@NonNull Issue> getIssues() {
			return issues;
		}
	}

	private static final Logger log = Logger.getLogger(MultiValidationJob.class);
	private static final @NonNull IDiagnosticConverter converter = new PivotDiagnosticConverter();
	private static final @NonNull MarkerCreator markerCreator = new MarkerCreator();

	private final @NonNull Set<@NonNull ValidationEntry> validationQueue = new HashSet<>();
	private @Nullable ProjectManager projectManager = null;

	public MultiValidationJob() {
		super(BaseUIMessages.MultiValidationJob_Name);
	}

	/**
	 * Add the files to the queue of validations. If the validation job is not
	 * already running, it is scheduled to run.
	 */
	public synchronized void addValidations(@NonNull Iterable<@NonNull ValidationEntry> entries) {
		boolean added = false;
		for (@NonNull ValidationEntry entry : entries) {
			if (validationQueue.add(entry)) {
				added = true;
			}
		}
		if (added) {
			int state = getState();
			if (state == Job.NONE) {
				schedule();
			}
		}
	}

	@Override
	protected synchronized void canceling() {
		validationQueue.clear();
		projectManager = null;		// FIXME track project open/closes
		super.canceling();
	}

	protected boolean checkResourceErrors(@NonNull Resource resource, @NonNull IAcceptor<@NonNull Issue> acceptor, @NonNull IProgressMonitor monitor) throws CoreException {
		for (int i = 0; i < resource.getErrors().size(); i++) {
			if (monitor.isCanceled())
				return false;
			converter.convertResourceDiagnostic(resource.getErrors().get(i), Severity.ERROR, acceptor);
		}
		for (int i = 0; i < resource.getWarnings().size(); i++) {
			if (monitor.isCanceled())
				return false;
			converter.convertResourceDiagnostic(resource.getWarnings().get(i), Severity.WARNING, acceptor);
		}
		return true;
	}

	protected boolean checkValidationErrors(@NonNull Resource resource, @NonNull IAcceptor<@NonNull Issue> acceptor, @NonNull IProgressMonitor monitor) throws CoreException {
		Map<Object, Object> validationContext = LabelUtil.createDefaultContext(Diagnostician.INSTANCE);
		BasicDiagnostic diagnostics = new BasicDiagnosticWithRemove(EObjectValidator.DIAGNOSTIC_SOURCE, 0, EcorePlugin.INSTANCE.getString("_UI_DiagnosticRoot_diagnostic", new Object[] { resource.getURI() }), new Object [] { resource });
		ResourceSet resourceSet = resource.getResourceSet();
		assert resourceSet != null;
		Diagnostician instance = PivotDiagnostician.createDiagnostician(resourceSet, EValidator.Registry.INSTANCE, null/*adapterFactory*/, monitor);
		for (EObject eObject : resource.getContents()) {
			if (monitor.isCanceled()) {
				return false;
			}
			instance.validate(eObject, diagnostics, validationContext);
		}
		return true;
	}

	protected void doValidate(final @NonNull ValidationEntry entry, @NonNull IProgressMonitor monitor) throws CoreException {
		IFile file = entry.getFile();
		URI uri = URI.createPlatformResourceURI(file.getFullPath().toString(), true);
		//		System.out.println("OCL:Validating " + uri.toString());
		ProjectManager projectManager2 = projectManager;
		if (projectManager2 == null) {
			projectManager = projectManager2 = new ProjectMap(false);
		}
		OCL ocl = OCL.newInstance(projectManager2);
		Resource resource = ocl.getResourceSet().getResource(uri, true);
		MarkerTypeProvider markerTypeProvider = entry;	// ValidationEntry pragmatically extends MarkerTypeProvider
		IValidationIssueProcessor validationIssueProcessor = new MarkerIssueProcessor(file, markerCreator, markerTypeProvider);
		IssueListAcceptor acceptor = new IssueListAcceptor();
		if (resource != null) {
			if (!checkResourceErrors(resource, acceptor, monitor)) {
				return;
			}
			if (resource instanceof CSResource) {
				Resource asResource = ((CSResource)resource).getASResource();
				if (!checkResourceErrors(asResource, acceptor, monitor)) {
					return;
				}
				if (!checkValidationErrors(asResource, acceptor, monitor)) {
					return;
				}
				// FIXME accumulate/cache dependencies
			}
			else {
				if (!checkValidationErrors(resource, acceptor, monitor)) {
					return;
				}
			}
		}
		validationIssueProcessor.processIssues(acceptor.getIssues(), monitor);
	}

	private synchronized @NonNull List<@NonNull ValidationEntry> getValidationList() {
		return new ArrayList<>(validationQueue);
	}

	@Override
	protected IStatus run(IProgressMonitor monitor) {
		if (monitor == null) {
			monitor = new NullProgressMonitor();
		}
		List<@NonNull ValidationEntry> validationList;
		while (!(validationList = getValidationList()).isEmpty()) {
			SubMonitor progress = SubMonitor.convert(monitor, validationList.size());
			Collections.sort(validationList, ToStringComparator.INSTANCE);
			for (@NonNull ValidationEntry entry : validationList) {
				if (monitor.isCanceled()) {
					return Status.CANCEL_STATUS;
				}
				try {
					progress.setTaskName(NLS.bind(BaseUIMessages.MultiValidationJob_Validating, entry.getFile().getFullPath().toString()));
					doValidate(entry, monitor);
				} catch (OperationCanceledException canceled) {
					return Status.CANCEL_STATUS;
				} catch (Exception e) {
					log.error("Error running " + getName(), e);
					//					return Status.OK_STATUS;
				}
				validationQueue.remove(entry);		// Remove so that failure does not repeat
				progress.worked(1);
			}
			progress.done();
		}
		return Status.OK_STATUS;
	}
}