/*******************************************************************************
 * Copyright (c) 2010, 2024 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.xtext.base.ui.model;

import java.io.IOException;
import java.io.StringWriter;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.jface.text.DocumentEvent;
import org.eclipse.ocl.pivot.internal.context.EInvocationContext;
import org.eclipse.ocl.pivot.internal.context.EObjectContext;
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal;
import org.eclipse.ocl.pivot.resource.ASResource;
import org.eclipse.ocl.pivot.utilities.ThreadLocalExecutor;
import org.eclipse.ocl.pivot.utilities.ThreadLocalExecutor.InitWrapperCallBack;
import org.eclipse.ocl.pivot.utilities.XMIUtil;
import org.eclipse.ocl.xtext.base.attributes.RootCSAttribution;
import org.eclipse.ocl.xtext.base.cs2as.CS2AS;
import org.eclipse.ocl.xtext.base.ui.BaseUiModule;
import org.eclipse.ocl.xtext.base.ui.utilities.ThreadLocalExecutorUI;
import org.eclipse.ocl.xtext.base.utilities.BaseCSResource;
import org.eclipse.ocl.xtext.base.utilities.ElementUtil;
import org.eclipse.ocl.xtext.basecs.ElementCS;
import org.eclipse.swt.widgets.Display;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.resource.XtextSyntaxDiagnostic;
import org.eclipse.xtext.ui.editor.model.DocumentTokenSource;
import org.eclipse.xtext.ui.editor.model.XtextDocument;
import org.eclipse.xtext.ui.editor.model.edit.ITextEditComposer;
import org.eclipse.xtext.util.concurrent.IUnitOfWork;

import com.google.inject.Inject;

public class BaseDocument extends XtextDocument implements ConsoleContext
{
	/**
	 * The derived BaseDocumentLocker assigns the EnvironmentFactory from the prevailing part thread
	 * to the current worker thread.
	 */
	protected class BaseDocumentLocker extends XtextDocumentLocker
	{
		/**
		 * The part thread on which the BaseEditor created the BaseDocument.
		 */
		private @Nullable ThreadLocalExecutor partThread = null;
		@Deprecated
		private EnvironmentFactoryInternal environmentFactory = null;

		protected BaseDocumentLocker() {
			super();
		}

		public @Nullable EnvironmentFactoryInternal basicGetEnvironmentFactory() {
			return environmentFactory != null ? environmentFactory : partThread != null ? partThread.localBasicGetEnvironmentFactory() : null;
		}

		@Deprecated
		public void initEnvironmentFactory(@Nullable EnvironmentFactoryInternal environmentFactory) {
			assert this.environmentFactory == environmentFactory;
			this.environmentFactory = environmentFactory;
		}

		public void initPartThread(@NonNull ThreadLocalExecutor partThread) {
			this.partThread = partThread;
			this.environmentFactory = null;
		}

		@Override
		public <T> T modify(IUnitOfWork<T, XtextResource> work) {
		//	System.out.println("[" + Thread.currentThread().getName() + "] modify " + NameUtil.debugSimpleName(this));
			ThreadLocalExecutor partThread2 = partThread;
			ThreadLocalExecutorUI.NeedsInit needsInit;
			if ((partThread2 != null) && ((needsInit = ThreadLocalExecutorUI.needsInit(partThread2)) != ThreadLocalExecutorUI.NeedsInit.AS_IS)) {
				InitWrapperCallBack<T, Object> callBack = new InitWrapperCallBack<T, Object>()
				{
					protected @Nullable T result = null;

					@SuppressWarnings("null")
					@Override
					public T getResult() {
						return result;
					}

					@Override
					public void run() {
						result = BaseDocumentLocker.super.modify(work);
					}
				};
				ThreadLocalExecutorUI.init(partThread2, callBack, needsInit);
				return callBack.getResult();
			}
			else {
				return super.modify(work);
			}
		}

		@Override
		public <T> T readOnly(IUnitOfWork<T, XtextResource> work) {
		//	System.out.println("[" + Thread.currentThread().getName() + "] readOnly " + NameUtil.debugSimpleName(this));
			ThreadLocalExecutor partThread2 = partThread;
			ThreadLocalExecutorUI.NeedsInit needsInit;
			if ((partThread2 != null) && ((needsInit = ThreadLocalExecutorUI.needsInit(partThread2)) != ThreadLocalExecutorUI.NeedsInit.AS_IS)) {
				InitWrapperCallBack<T, Object> callBack = new InitWrapperCallBack<T, Object>()
				{
					protected @Nullable T result = null;

					@SuppressWarnings("null")
					@Override
					public T getResult() {
						return result;
					}

					@Override
					public void run() {
						result = BaseDocumentLocker.super.readOnly(work);
					}
				};
				ThreadLocalExecutorUI.init(partThread2, callBack, needsInit);
				return callBack.getResult();
			}
			else {
				return super.readOnly(work);
			}
		}

		public void resetPartThread() {
			this.partThread = null;
			this.environmentFactory = null;
		}
	}

	private BaseDocumentLocker baseStateAccess;
	private @Nullable EObject context;
	private @Nullable Map<String, EClassifier> parameters;

	@Inject
	public BaseDocument(DocumentTokenSource tokenSource, ITextEditComposer composer) {
		super(tokenSource, composer);
	}

	public @Nullable EnvironmentFactoryInternal basicGetEnvironmentFactory() {
		return baseStateAccess != null ? baseStateAccess.basicGetEnvironmentFactory() : null;
	}

	protected void checkForErrors(Resource resource) throws CoreException {
		List<Resource.Diagnostic> errors = resource.getErrors();
		if (errors.size() > 0) {
			StringBuilder s = new StringBuilder();
			for (Resource.Diagnostic diagnostic : errors) {
				s.append("\n");
				if (diagnostic instanceof XtextSyntaxDiagnostic) {
					s.append("Syntax error: ");
					String location = diagnostic.getLocation();
					if (location != null) {
						s.append(location);
						s.append(":");
					}
					s.append(diagnostic.getLine());
					s.append(" ");
					s.append(diagnostic.getMessage());
				}
				else {
					s.append(diagnostic.toString());
				}
			}
			throw new CoreException(new Status(IStatus.ERROR, BaseUiModule.PLUGIN_ID, s.toString()));
		}
	}

	@Override
	protected XtextDocumentLocker createDocumentLocker() {
		baseStateAccess = new BaseDocumentLocker();
		return baseStateAccess;
	}

	@Override
	public void disposeInput() {
		if (baseStateAccess != null) {
			baseStateAccess.resetPartThread();
			baseStateAccess = null;
		}
		super.disposeInput();
	}

	public @Nullable ASResource getASResource() throws CoreException {
		return readOnly(new IUnitOfWork<@Nullable ASResource, @Nullable XtextResource>()
		{
			@Override
			public ASResource exec(@Nullable XtextResource resource) throws Exception {
				if (!(resource instanceof BaseCSResource)) {
					return null;
				}
				BaseCSResource csResource = (BaseCSResource)resource;
				CS2AS cs2as = csResource.findCS2AS();
				if (cs2as  == null) {
					return null;
				}
				ASResource asResource = cs2as.getASResource();
				checkForErrors(asResource);
				return asResource;
			}
		});
	}

	@Override
	protected void fireDocumentChanged(DocumentEvent event) {
		if (Thread.currentThread() == Display.getDefault().getThread()) {
			super.fireDocumentChanged(event);
		}
	}

	@Override
	protected void fireDocumentAboutToBeChanged(DocumentEvent event) {
		if (Thread.currentThread() == Display.getDefault().getThread()) {
			super.fireDocumentAboutToBeChanged(event);
		}
	}

	protected RootCSAttribution getDocumentAttribution() {
		return readOnly(new IUnitOfWork<RootCSAttribution, XtextResource>()
		{
			@Override
			public RootCSAttribution exec(@Nullable XtextResource resource) throws Exception {
				if ((resource != null) && !resource.getContents().isEmpty()) {
					ElementCS csElement = (ElementCS) resource.getContents().get(0);
					return ElementUtil.getDocumentAttribution(csElement);
				}
				return null;
			}
		});
	}

	@Override
	public @Nullable EObject getOCLContext() {
		return context;
	}

	@Override
	public @Nullable Map<String, EClassifier> getOCLParameters() {
		return parameters;
	}

	public @Nullable ResourceSet getResourceSet() {
		return readOnly(new IUnitOfWork<@Nullable ResourceSet, @Nullable XtextResource>()
		{
			@Override
			public ResourceSet exec(@Nullable XtextResource resource) throws Exception {
				return resource != null ? resource.getResourceSet() : null;
			}
		});
	}

	/**
	 * Write the XMI representation of the Pivot to be saved.
	 */
	public void saveAsPivot(@NonNull StringWriter writer) throws CoreException, IOException {
		XMLResource asResource = getASResource();
		if (asResource != null) {
			asResource.save(writer, XMIUtil.createPivotSaveOptions());
		}
	}

	@Override
	public void setContext(final @NonNull EClassifier ecoreContext, final @Nullable Map<String, EClassifier> ecoreParameters) {
		modify(new IUnitOfWork<Object, XtextResource>()
		{
			@Override
			public Object exec(@Nullable XtextResource resource) throws Exception {
				if (resource instanceof BaseCSResource) {
					BaseCSResource csResource = (BaseCSResource)resource;
					csResource.setParserContext(new EInvocationContext(csResource.getEnvironmentFactory(), resource.getURI(), ecoreContext, ecoreParameters));
				}
				return null;
			}
		});

		this.context = ecoreContext;
		this.parameters = ecoreParameters;
	}

	public @Nullable Object setContext(@NonNull BaseCSResource csResource, @Nullable EObject eObject) {
		csResource.setParserContext(new EObjectContext(csResource.getEnvironmentFactory(), csResource.getURI(), eObject));
		return null;
	}

	@Override
	public void setInput(XtextResource resource) {
		assert baseStateAccess != null;
		EnvironmentFactoryInternal environmentFactory = baseStateAccess.basicGetEnvironmentFactory();
		if (environmentFactory == null) {
			ThreadLocalExecutor partThread = ThreadLocalExecutorUI.basicGetPartThread();
			if (partThread != null) {
				baseStateAccess.initPartThread(partThread);
			}
		}
		super.setInput(resource);
	}
}
