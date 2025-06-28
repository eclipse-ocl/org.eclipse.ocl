/*******************************************************************************
 * Copyright (c) 2015, 2025 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.xtext.base.ui;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.utilities.EnvironmentFactory;
import org.eclipse.ocl.pivot.utilities.PivotConstants;
import org.eclipse.ocl.pivot.utilities.ThreadLocalExecutor.InitWrapperCallBack;
import org.eclipse.ocl.xtext.base.ui.model.BaseDocumentProvider;
import org.eclipse.ocl.xtext.base.ui.utilities.ThreadLocalExecutorUI;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.texteditor.IDocumentProvider;
import org.eclipse.xtext.ui.editor.XtextEditor;

public class BaseEditor extends XtextEditor
{
	public BaseEditor() {
		super();
	}

	@Override
	public void createPartControl(Composite parent) {
		InitWrapperCallBack<?, ?> callBack = new InitWrapperCallBack<Object, Object>()
		{
			@Override
			public Object getResult() {
				return null;
			}

			@Override
			public void run() {
				BaseEditor.super.createPartControl(parent);
			}
		};
		ThreadLocalExecutorUI.init(this, callBack);
	}

	@Override
	public <T> T getAdapter(Class<T> adapter) {
		if (EnvironmentFactory.class.isAssignableFrom(adapter)) {
			return adapter.cast(getEnvironmentFactory());
		}
		return super.getAdapter(adapter);
	}

	public @NonNull String getCSXMIfileExtension() {			// Should override
		return PivotConstants.OCL_CS_FILE_EXTENSION;
	}

	public @NonNull EnvironmentFactory getEnvironmentFactory() {
		BaseDocumentProvider documentProvider = (BaseDocumentProvider) getDocumentProvider();
		return documentProvider.getEnvironmentFactory();
	}

	public @NonNull String getMarkerId() {
		return BaseUiModule.MARKER_ID;
	}

	@Override
	public void init(IEditorSite site, IEditorInput input) throws PartInitException {
		InitWrapperCallBack<Object, PartInitException> callBack = new InitWrapperCallBack<Object, PartInitException>()
		{
			protected @Nullable PartInitException throwable = null;

			@Override
			public Object getResult() {
				return null;
			}

			@Override
			public @Nullable PartInitException getThrowable() { return throwable; }

			@Override
			public void run() {
				try {
					BaseEditor.super.init(site, input);
				} catch (PartInitException e) {
					throwable = e;
				}
			}
		};
		ThreadLocalExecutorUI.init(this, callBack);
		PartInitException throwable = callBack.getThrowable();
		if (throwable != null) {
			throw throwable;
		}
	}

	@Override
	protected void setDocumentProvider(IDocumentProvider provider) {
		super.setDocumentProvider(provider);
		((BaseDocumentProvider)provider).initOCL(this);
	}
}
