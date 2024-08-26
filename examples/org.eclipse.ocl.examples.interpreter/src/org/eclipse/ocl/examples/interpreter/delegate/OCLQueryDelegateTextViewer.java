/*******************************************************************************
 * Copyright (c) 2010, 2024 Kenn Hussey and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 *
 * Contributors:
 *   Kenn Hussey - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.interpreter.delegate;

import java.util.Map;

import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.ui.util.QueryDelegateTextViewer;
import org.eclipse.jface.text.IDocument;
import org.eclipse.ocl.OCL;
import org.eclipse.ocl.ecore.EcoreEnvironmentFactory;
import org.eclipse.ocl.examples.interpreter.console.IOCLFactory;
import org.eclipse.ocl.examples.interpreter.console.ModelingLevel;
import org.eclipse.ocl.examples.interpreter.console.TargetMetamodel;
import org.eclipse.ocl.examples.interpreter.console.text.ColorManager;
import org.eclipse.ocl.examples.interpreter.console.text.OCLDocument;
import org.eclipse.ocl.examples.interpreter.console.text.OCLSourceViewer;
import org.eclipse.swt.widgets.Composite;

/**
 * @since 3.1
 */
public class OCLQueryDelegateTextViewer
		extends OCLSourceViewer
		implements QueryDelegateTextViewer {

	protected EClassifier context = null;

	protected Map<String, EClassifier> parameters = null;

	private class EcoreOCLFactory
			implements IOCLFactory<Object> {

		@Override
		public TargetMetamodel getTargetMetamodel() {
			return TargetMetamodel.Ecore;
		}

		@Override
		@SuppressWarnings("unchecked")
		public OCL<?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?> createOCL(
				ModelingLevel level) {
			return OCL.newInstanceAbstract(new EcoreEnvironmentFactory(
				EPackage.Registry.INSTANCE));
		}

		@Override
		@SuppressWarnings("unchecked")
		public OCL<?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?> createOCL(
				ModelingLevel level, Resource res) {

			return OCL.newInstanceAbstract(new EcoreEnvironmentFactory(
				EPackage.Registry.INSTANCE), res);
		}

		@Override
		public Object getContextClassifier(EObject object) {
			return context.eClass();
		}

		@Override
		public String getName(Object modelElement) {
			return ((ENamedElement) modelElement).getName();
		}
	}

	public OCLQueryDelegateTextViewer(Composite parent, int styles) {
		super(parent, new ColorManager(), styles);
	}

	@Override
	public void setContext(EClassifier context) {
		((OCLDocument) getDocument()).setOCLContext(this.context = context);
	}

	@Override
	public void setParameters(Map<String, EClassifier> parameters) {
		((OCLDocument) getDocument())
			.setOCLParameters(this.parameters = parameters);
	}

	@Override
	public void setDocument(IDocument document) {

		if (document != null) {
			OCLDocument oclDocument = new OCLDocument(document.get());
			oclDocument.setOCLFactory(new EcoreOCLFactory());
			oclDocument.setOCLContext(context);
			oclDocument.setOCLParameters(parameters);
			oclDocument.setModelingLevel(ModelingLevel.M1);

			document = oclDocument;
		}

		super.setDocument(document);
	}

}
