/*******************************************************************************
 * Copyright (c) 2012, 2018 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.ui;

import java.util.Collection;

import org.eclipse.core.expressions.IEvaluationContext;
import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jface.text.TextSelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.ISources;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.services.IEvaluationService;
import org.eclipse.xtext.ui.editor.XtextEditor;

public class OCLPropertyTester extends PropertyTester
{
	private static final String RESOURCE_SET_AVAILABLE = "resourceSetAvailable"; //$NON-NLS-1$
	
	@Deprecated // No longer used
	public static XtextEditor getActiveXtextEditor(IEvaluationContext evaluationContext) {
		Object o = HandlerUtil.getVariable(evaluationContext, ISources.ACTIVE_EDITOR_NAME);
		if (!(o instanceof IEditorPart)) {
			return null;
		}
		XtextEditor xtextEditor = (XtextEditor) ((IEditorPart)o).getAdapter(XtextEditor.class);
		return xtextEditor;
	}

	public OCLPropertyTester() {
		super();
	}

	private boolean hasResourceSet(Object receiver, @NonNull IEditorPart editorPart) {
		if (receiver instanceof IStructuredSelection) {
			receiver = ((IStructuredSelection)receiver).getFirstElement();
		}
    	if (receiver instanceof IAdaptable) {
    		receiver = ((IAdaptable) receiver).getAdapter(EObject.class);
        }
		if (receiver instanceof EObject) {
			Resource resource = ((EObject)receiver).eResource();
			return (resource != null) && (resource.getResourceSet() != null);
		}
		else if (receiver instanceof Resource) {
			return ((Resource)receiver).getResourceSet() != null;
		}
		else if (receiver instanceof ResourceSet) {
			return true;
		}
		else if (receiver instanceof TextSelection) {
			Object xtextEditor = editorPart.getAdapter(XtextEditor.class);
			return xtextEditor != null;
		}
		return false;
	}

	/**
	 * @return an evaluation context
	 */
	public static IEvaluationContext getApplicationContext() {
		IEvaluationService es = (IEvaluationService) PlatformUI.getWorkbench().getService(IEvaluationService.class);
		return es == null ? null : es.getCurrentState();
	}

	public boolean test(Object receiver, String property, Object[] args, Object expectedValue) {
		if (RESOURCE_SET_AVAILABLE.equals(property)) {
			IEvaluationContext evaluationContext = getApplicationContext();
			Object o = HandlerUtil.getVariable(evaluationContext, ISources.ACTIVE_PART_NAME);
			if (!(o instanceof IEditorPart)) {
//				System.out.println(property + " false for " + o);
				return false;
			}
			IEditorPart editorPart = (IEditorPart)o;
			if (receiver instanceof Collection) {
				for (Object aReceiver : (Collection<?>)receiver) {
					if (hasResourceSet(aReceiver, editorPart)) {
//						System.out.println(property + " *true for " + aReceiver);
						return true;
					}
				}
//				System.out.println(property + " *false for " + receiver);
				return false;
			}
			else {
				if (hasResourceSet(receiver, editorPart)) {
//					System.out.println(property + " true for " + receiver);
					return true;
				}
//				System.out.println(property + " false for " + receiver);
				return false;
			}
		}
//		System.out.println(property + " for " + receiver);
		return false;
	}
}
