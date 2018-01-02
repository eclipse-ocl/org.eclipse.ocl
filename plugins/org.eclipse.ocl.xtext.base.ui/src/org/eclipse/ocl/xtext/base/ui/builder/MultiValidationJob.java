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
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.ui.MarkerHelper;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EValidator;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.eclipse.emf.ecore.util.EObjectValidator;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.ui.util.EditUIMarkerHelper;
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
import org.eclipse.osgi.util.NLS;

/**
 * A MultiValidationJob maintains a queue of workspaceRelativeFileNames in need of validation.
 *
 * Validations are performed in a separate job from the builder since the success/failure of an OCL validation
 * does not need to force users to wait as occurs for builder active.
 */
public class MultiValidationJob extends Job
{
	/**
	 * The ValidationMarkerHelper replicates those parts of org.eclipse.emf.edit.ui.actionValidateAction.EclipseResourcesUtil
	 * that ensure that Problems View markers can navigate back to an EMF editor.
	 */
	protected static class ValidationMarkerHelper extends EditUIMarkerHelper
	{
		@Override
		protected void adjustMarker(IMarker marker, Diagnostic diagnostic, Diagnostic parentDiagnostic) throws CoreException
		{
			List<?> data = diagnostic.getData();
			StringBuilder relatedURIs = new StringBuilder();
			boolean first = true;
			for (Object object : data) {
				if (object instanceof EObject) {
					EObject eObject = (EObject)object;
					if (first) {
						first = false;
						marker.setAttribute(EValidator.URI_ATTRIBUTE, EcoreUtil.getURI(eObject).toString());
					}
					else {
						if (relatedURIs.length() != 0) {
							relatedURIs.append(' ');
						}
						relatedURIs.append(URI.encodeFragment(EcoreUtil.getURI(eObject).toString(), false));
					}
				}
			}
			if (relatedURIs.length() > 0) {
				marker.setAttribute(EValidator.RELATED_URIS_ATTRIBUTE, relatedURIs.toString());
			}
			super.adjustMarker(marker, diagnostic, parentDiagnostic);
		}

		@Override
		protected String getMarkerID() {
			return EValidator.MARKER;
		}
	}

	private static final Logger log = Logger.getLogger(MultiValidationJob.class);
	private static final @NonNull MarkerHelper markerHelper = new ValidationMarkerHelper();

	private final @NonNull Set<@NonNull IFile> validationQueue = new HashSet<>();
	private @Nullable ProjectManager projectManager = null;

	public MultiValidationJob() {
		super(BaseUIMessages.MultiValidationJob_Name);
	}

	/**
	 * Add the files to the queue of validations. If the validation job is not
	 * already running, it is scheduled to run.
	 */
	public synchronized void addValidations(@NonNull Iterable<@NonNull IFile> files) {
		boolean added = false;
		for (@NonNull IFile file : files) {
			if (validationQueue.add(file)) {
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

	protected void checkResourceErrors(@NonNull Resource resource) throws CoreException {
		boolean wrap = true;
		for (Diagnostic diagnostic : markerHelper.getInstrinciDiagnostics(resource, wrap)) {
			markerHelper.updateMarkers(diagnostic);
		}
	}

	protected void checkValidationErrors(@NonNull Resource resource, IProgressMonitor monitor) throws CoreException {
		Map<Object, Object> validationContext = LabelUtil.createDefaultContext(Diagnostician.INSTANCE);
		BasicDiagnostic diagnostics = new BasicDiagnosticWithRemove(EObjectValidator.DIAGNOSTIC_SOURCE, 0, EcorePlugin.INSTANCE.getString("_UI_DiagnosticRoot_diagnostic", new Object[] { resource.getURI() }), new Object [] { resource });
		ResourceSet resourceSet = resource.getResourceSet();
		assert resourceSet != null;
		Diagnostician instance = PivotDiagnostician.createDiagnostician(resourceSet, EValidator.Registry.INSTANCE, null/*adapterFactory*/, monitor);
		for (EObject eObject : resource.getContents()) {
			instance.validate(eObject, diagnostics, validationContext);
		}
		markerHelper.updateMarkers(diagnostics);
	}

	protected void doValidate(@NonNull IFile file, IProgressMonitor monitor) throws CoreException {
		URI uri = URI.createPlatformResourceURI(file.getFullPath().toString(), true);
		//		System.out.println("OCL:Validating " + uri.toString());
		ProjectManager projectManager2 = projectManager;
		if (projectManager2 == null) {
			projectManager = projectManager2 = new ProjectMap(false);
		}
		OCL ocl = OCL.newInstance(projectManager2);
		Resource resource = ocl.getResourceSet().getResource(uri, true);
		markerHelper.deleteMarkers(resource);
		if (resource != null) {
			checkResourceErrors(resource);
			if (resource instanceof CSResource) {
				Resource asResource = ((CSResource)resource).getASResource();
				markerHelper.deleteMarkers(asResource);
				checkResourceErrors(asResource);
				checkValidationErrors(asResource, monitor);
				// FIXME accumulate/cache dependencies
			}
			else {
				checkValidationErrors(resource, monitor);
			}
		}
	}

	private synchronized @NonNull List<@NonNull IFile> getValidationList() {
		return new ArrayList<>(validationQueue);
	}

	@Override
	protected IStatus run(final IProgressMonitor monitor) {
		List<@NonNull IFile> validationList;
		while (!(validationList = getValidationList()).isEmpty()) {
			SubMonitor progress = SubMonitor.convert(monitor, validationList.size());
			Collections.sort(validationList, ToStringComparator.INSTANCE);
			for (@NonNull IFile file : validationList) {
				if (monitor.isCanceled()) {
					return Status.CANCEL_STATUS;
				}
				try {
					progress.setTaskName(NLS.bind(BaseUIMessages.MultiValidationJob_Validating, file.getFullPath().toString()));
					doValidate(file, monitor);
				} catch (OperationCanceledException canceled) {
					return Status.CANCEL_STATUS;
				} catch (Exception e) {
					log.error("Error running " + getName(), e);
					//					return Status.OK_STATUS;
				}
				validationQueue.remove(file);		// Remove so that failure does not repeat
				progress.worked(1);
			}
			progress.done();
		}
		return Status.OK_STATUS;
	}
}