/*******************************************************************************
 * Copyright (c) 2026 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.emf.validation.validity.export;

import java.io.IOException;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.emf.validation.validity.ConstrainingNode;
import org.eclipse.ocl.examples.emf.validation.validity.LeafConstrainingNode;
import org.eclipse.ocl.examples.emf.validation.validity.Result;
import org.eclipse.ocl.examples.emf.validation.validity.ResultSet;
import org.eclipse.ocl.examples.emf.validation.validity.RootConstrainingNode;
import org.eclipse.ocl.examples.emf.validation.validity.RootNode;
import org.eclipse.ocl.examples.emf.validation.validity.Severity;
import org.eclipse.ocl.examples.emf.validation.validity.ValidatableNode;
import org.eclipse.ocl.examples.emf.validation.validity.locator.ConstraintLocator;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.Model;
import org.eclipse.ocl.pivot.NamedElement;
import org.eclipse.ocl.pivot.utilities.Pivotable;

/**
 * Exports validation results as a model.
 */
public class CSVExporter extends AbstractExporter
{
	public static final @NonNull String EXPORTER_TYPE = "csv";
	public static final @NonNull CSVExporter INSTANCE = new CSVExporter();

	private void appendQuoted(Appendable text, final String string) throws IOException {
		text.append("\"");
		final int iMax = string.length();
		for (int i = 0; i < iMax; i++) {
			char c = string.charAt(i);
			if (c == '"') {
				text.append("\"");
			}
			text.append(c);
		}
		text.append("\"");
	}

	@Override
	public void createContents(@NonNull Appendable text, @NonNull RootNode rootNode, @Nullable String exportedFileName) throws IOException {
		appendQuoted(text, "Model Element URI");
		text.append(",");
		appendQuoted(text, "Model Element EClass");
		text.append(",");
		appendQuoted(text, "Severity");
		text.append(",");
		appendQuoted(text, "Diagnosis");
		text.append(",");
		appendQuoted(text, "Constraint Locator");
		text.append(",");
		appendQuoted(text, "Constraint Resource");
		text.append(",");
		appendQuoted(text, "Constraint EClass");
		text.append(",");
		appendQuoted(text, "Constraint Name");
		text.append(",");
		appendQuoted(text, "Constraint Body");
		text.append(",");
//		appendQuoted(text, "Diagnostic");
//		text.append(",");
		appendQuoted(text, "Exception");
		text.append("\n");
		for (ResultSet resultSet : rootNode.getResultSets()) {
			for (Result result : resultSet.getResults()) {
				ValidatableNode validatableNode = result.getValidatableNode();
				EObject constrainedObject = validatableNode.getConstrainedObject();
				appendQuoted(text, EcoreUtil.getURI(constrainedObject).toString());
				text.append(",");
				if (constrainedObject != null) {
					EClass constrainingEClass = constrainedObject.eClass();
					assert constrainingEClass != null;
					appendQuoted(text, getEcorePath(null, constrainingEClass).toString());
				}
				text.append(",");
				Severity severity = result.getSeverity();
				appendQuoted(text, severity.getName());
				text.append(",");
				LeafConstrainingNode leafConstrainingNode = result.getLeafConstrainingNode();
				String message = getMessage(leafConstrainingNode.getWorstResult());
				appendQuoted(text, message != null ? message.toString() : "---");
				text.append(",");
				ConstraintLocator constraintLocator = leafConstrainingNode.getConstraintLocator();
				appendQuoted(text, constraintLocator != null ? constraintLocator.getName() : "---");
				text.append(",");
				Resource constraintResource = leafConstrainingNode.getConstraintResource();
				ConstrainingNode constraintParentNode = leafConstrainingNode.getParent();
				if (constraintResource == null) {
					Object constrainingObject = constraintParentNode.getConstrainingObject();
					if (constrainingObject instanceof EObject) {
						constraintResource = ((EObject)constrainingObject).eResource();
					}
				}
				appendQuoted(text, constraintResource != null ? String.valueOf(constraintResource.getURI()) : "---");
				text.append(",");
				if (constraintParentNode != null) {
					Object constrainingObject = constraintParentNode.getConstrainingObject();
					if (constrainingObject instanceof Pivotable) {
						Element pivotElement = ((Pivotable)constrainingObject).getPivot();
						if (pivotElement instanceof NamedElement) {
							appendQuoted(text, getPivotPath(null, (NamedElement)pivotElement).toString());
						}
						else {
							appendQuoted(text, getLabelPath(null, constraintParentNode).toString());
						}
					}
					else if (constrainingObject instanceof ENamedElement) {
						appendQuoted(text, getEcorePath(null, (ENamedElement)constrainingObject).toString());
					}
					else {
						appendQuoted(text, getLabelPath(null, constraintParentNode).toString());
					}
				}
				text.append(",");
				appendQuoted(text, leafConstrainingNode.getLabel());
				text.append(",");
				if (severity.compareTo(Severity.INFO) > 0) {
					String expression = leafConstrainingNode.getConstraintString();
					appendQuoted(text, expression != null ? expression : "---");
				}
				text.append(",");
/*				Object diagnostic = result.getDiagnostic();
				appendQuoted(text, diagnostic != null ? diagnostic.toString() : "---");
				text.append(","); */
				Throwable exception = result.getException();
				appendQuoted(text, exception != null ? exception.toString() : "---");
				text.append("\n");
			}
		}
	}

	private @NonNull StringBuilder getEcorePath(@Nullable StringBuilder s, @NonNull ENamedElement constrainingObject) {
		if (s == null) {
			s = new StringBuilder();
		}
		EObject constrainingParent = constrainingObject.eContainer();
		if (constrainingParent instanceof ENamedElement) {
			s = getEcorePath(s, (ENamedElement) constrainingParent);
			s.append("::");
		}
		s.append(constrainingObject.getName());
		return s;
	}

	@Override
	public @NonNull String getExporterType() { return EXPORTER_TYPE; }

	private @NonNull StringBuilder getLabelPath(@Nullable StringBuilder s, @NonNull ConstrainingNode constrainingNode) {
		if (s == null) {
			s = new StringBuilder();
		}
		Object constrainingObject = constrainingNode.getConstrainingObject();
		if (constrainingObject instanceof ENamedElement) {
			s = getEcorePath(s, (ENamedElement)constrainingObject);
			s.append("::");
		}
		else {
			ConstrainingNode constrainingParent = constrainingNode.getParent();
			if ((constrainingParent != null) && !(constrainingParent instanceof RootConstrainingNode)){
				s = getLabelPath(s, constrainingParent);
				s.append("::");
			}
		}
		if (constrainingObject instanceof ENamedElement) {
			s.append(((ENamedElement)constrainingObject).getName());
		}
		else {
			s.append(constrainingNode.toString());
		}
		return s;
	}

	private @NonNull StringBuilder getPivotPath(@Nullable StringBuilder s, @NonNull NamedElement constrainingObject) {
		if (s == null) {
			s = new StringBuilder();
		}
		EObject constrainingParent = constrainingObject.eContainer();
		if ((constrainingParent instanceof NamedElement) && !(constrainingParent instanceof Model)) {
			s = getPivotPath(s, (NamedElement) constrainingParent);
			s.append("::");
		}
		s.append(constrainingObject.getName());
		return s;
	}

	@Override
	public @NonNull String getPreferredExtension() { return "csv"; }
}
