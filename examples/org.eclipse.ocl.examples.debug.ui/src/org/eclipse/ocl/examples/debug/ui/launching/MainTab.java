/*******************************************************************************
 * Copyright (c) 2014, 2018 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     R.Dvorak and others - QVTo debugger framework
 *     E.D.Willink - revised API for OCL debugger framework
 *******************************************************************************/
package org.eclipse.ocl.examples.debug.ui.launching;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.examples.debug.launching.OCLLaunchConstants;
import org.eclipse.ocl.examples.debug.ui.OCLDebugUIPlugin;
import org.eclipse.ocl.examples.debug.vm.ui.launching.LaunchingUtils;
import org.eclipse.ocl.pivot.Constraint;
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.LanguageExpression;
import org.eclipse.ocl.pivot.Model;
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal.EnvironmentFactoryInternalExtension;
import org.eclipse.ocl.pivot.internal.utilities.PivotUtilInternal;
import org.eclipse.ocl.pivot.resource.ASResource;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.LabelUtil;
import org.eclipse.ocl.pivot.utilities.ParserException;
import org.eclipse.ocl.xtext.base.utilities.BaseCSResource;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class MainTab extends AbstractMainTab implements OCLLaunchConstants
{
	protected class ContextModifyListener implements ModifyListener
	{
		@Override
		public void modifyText(ModifyEvent e) {
			if (elementCombo.isDisposed()) {
				return;
			}
			updateLaunchConfigurationDialog();
		}
	}

	protected class ContextPathModifyListener implements ModifyListener
	{
		@Override
		public void modifyText(ModifyEvent e) {
			if (modelPath.isDisposed()) {
				return;
			}
			String modelName = modelPath.getText();
			URI contextURI = URI.createURI(modelName, true);
			URI elementsURI = contextURI.trimFragment();
			try {
				Resource resource = getEnvironmentFactory().getResourceSet().getResource(elementsURI, true);
				if (resource == null) {
					throw new IOException("There was an error loading the model file. ");
				}
				List<String> elements = new ArrayList<String>();
				for (TreeIterator<EObject> tit = resource.getAllContents(); tit.hasNext(); ) {
					EObject eObject = tit.next();
					String displayString = LabelUtil.getLabel(eObject);
					URI uri = EcoreUtil.getURI(eObject);
					elements.add(displayString);
					element2uri.put(displayString, uri);
				}
				Collections.sort(elements);
				elementCombo.setItems(elements.toArray(new String[elements.size()]));
			}
			catch (Exception ex) {
				setErrorMessage("Failed to load '" + elementsURI + "': " + ex.toString());
			}
			updateLaunchConfigurationDialog();
		}
	}

	protected class ExpressionModifyListener implements ModifyListener
	{
		@Override
		public void modifyText(ModifyEvent e) {
			if (expressionCombo.isDisposed()) {
				return;
			}
			updateLaunchConfigurationDialog();
		}
	}

	protected class ExpressionPathModifyListener implements ModifyListener
	{
		@Override
		public void modifyText(ModifyEvent e) {
			if (oclPath.isDisposed()) {
				return;
			}
			String oclName = oclPath.getText();
			URI oclURI = URI.createURI(oclName, true);
			try {
				Model root = null;
				BaseCSResource xtextResource = null;
				xtextResource = (BaseCSResource) getEnvironmentFactory().getResourceSet().getResource(oclURI, true);
				if (xtextResource != null) {
					ASResource asResource = xtextResource.getASResource();
					for (EObject eContent : asResource.getContents()) {
						root = (Model)eContent;
						break;
					}
				} else {
					// TODO Can I get the parsing errors?
					//return null;
				}
				if (root == null) {
					throw new IOException("There was an error loading the OCL file. ");
				}
				List<String> expressions = new ArrayList<String>();
				for (TreeIterator<EObject> tit = root.eAllContents(); tit.hasNext(); ) {
					EObject eObject = tit.next();
					if (eObject instanceof ExpressionInOCL) {
						ExpressionInOCL expressionInOCL = (ExpressionInOCL)eObject;
						String displayString = getDisplayString(expressionInOCL);
						URI uri = EcoreUtil.getURI(expressionInOCL.eContainer());
						expressions.add(displayString);
						expression2uri.put(displayString, uri);
					}
				}
				Collections.sort(expressions);
				expressionCombo.setItems(expressions.toArray(new String[expressions.size()]));
			}
			catch (Exception ex) {
				setErrorMessage("Failed to load '" + oclName + "': " + ex.toString());
			}
			updateLaunchConfigurationDialog();
		}
	}

	protected Text oclPath;
	protected Button oclBrowseWS;
	protected Button oclBrowseFile;
	protected Button modelBrowseWS;
	protected Button modelBrowseFile;
	protected Group modelGroup;
	protected Group elementGroup;
	protected Combo expressionCombo;
	protected Combo elementCombo;
	private Group expressionGroup;
	private Text modelPath;
	private Map<String, URI> expression2uri = new HashMap<String, URI>();
	private Map<String, URI> element2uri = new HashMap<String, URI>();

	@Override
	public boolean canSave() {
		assert !initializing;
		ResourceSet resourceSet = getEnvironmentFactory().getResourceSet();
		URIConverter uriConverter = resourceSet.getURIConverter();
		String oclName = oclPath.getText();
		URI oclURI = URI.createURI(oclName, true);
		boolean oclExists = uriConverter.exists(oclURI, null);
		if (!oclExists){
			setErrorMessage("Selected OCL file '" + oclName + "' does not exist");
			return false;
		}
		String modelName = oclPath.getText();
		URI modelURI = URI.createURI(modelName, true);
		boolean modelExists = uriConverter.exists(modelURI, null);
		if (!modelExists){
			setErrorMessage("Selected Model file '" + modelName + "' does not exist");
			return false;
		}
		setErrorMessage(null);
		return true;
	}

	@Override
	@SuppressWarnings("null")
	public void createControl(Composite parent) {
		Composite control = createForm(parent);
		oclPath.addModifyListener(new ExpressionPathModifyListener());
		expressionCombo.addModifyListener(new ExpressionModifyListener());
		modelPath.addModifyListener(new ContextPathModifyListener());
		elementCombo.addModifyListener(new ContextModifyListener());
		LaunchingUtils.prepareBrowseWorkspaceButton(oclBrowseWS, oclPath, false);
		LaunchingUtils.prepareBrowseFileSystemButton(oclBrowseFile, oclPath, false);
		LaunchingUtils.prepareBrowseWorkspaceButton(modelBrowseWS, modelPath, false);
		LaunchingUtils.prepareBrowseFileSystemButton(modelBrowseFile, modelPath, false);
		control.setBounds(0, 0, 300, 300);
		control.layout();
		control.pack();
	}

	/**
	 * @wbp.parser.entryPoint
	 */
	public Composite createForm(Composite parent) {
		Composite control = new Composite(parent, SWT.NONE);
		setControl(control);
		control.setLayout(new GridLayout(1, false));
		control.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		Group oclGroup = new Group(control, SWT.NONE);
		oclGroup.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		oclGroup.setText("OCL");
		oclGroup.setLayout(new GridLayout(3, false));

		oclPath = new Text(oclGroup, SWT.BORDER);
		GridData gd_oclPath = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_oclPath.minimumWidth = 100;
		oclPath.setLayoutData(gd_oclPath);
		oclBrowseWS = new Button(oclGroup, SWT.NONE);
		oclBrowseWS.setText("Browse Workspace...");
		oclBrowseFile = new Button(oclGroup, SWT.NONE);
		oclBrowseFile.setText("Browse File...");

		expressionGroup = new Group(control, SWT.NONE);
		expressionGroup.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		GridLayout gl_expressionGroup = new GridLayout(2, false);
		gl_expressionGroup.marginWidth = 0;
		gl_expressionGroup.marginHeight = 0;
		expressionGroup.setLayout(gl_expressionGroup);

		Label expressionLabel = new Label(expressionGroup, SWT.READ_ONLY);
		expressionLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		expressionLabel.setText("Expression");

		expressionCombo = new Combo(expressionGroup, SWT.NONE);
		expressionCombo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(control, SWT.NONE);

		modelGroup = new Group(control, SWT.NONE);
		modelGroup.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		modelGroup.setText("Model");
		modelGroup.setLayout(new GridLayout(4, false));
		new Label(modelGroup, SWT.NONE);

		modelPath = new Text(modelGroup, SWT.BORDER);
		modelPath.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		modelBrowseWS = new Button(modelGroup, SWT.NONE);
		modelBrowseWS.setText("Browse Workspace...");

		modelBrowseFile = new Button(modelGroup, SWT.NONE);
		modelBrowseFile.setText("Browse File...");

		elementGroup = new Group(control, SWT.NONE);
		elementGroup.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		GridLayout gl_elementGroup = new GridLayout(2, false);
		gl_elementGroup.marginWidth = 0;
		gl_elementGroup.marginHeight = 0;
		elementGroup.setLayout(gl_elementGroup);

		Label elementLabel = new Label(elementGroup, SWT.NONE);
		elementLabel.setText("Element");

		elementCombo = new Combo(elementGroup, SWT.READ_ONLY);
		elementCombo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		return control;
	}

	protected String getDisplayString(@NonNull ExpressionInOCL expressionInOCL) {
		String typeString = expressionInOCL.getOwnedContext().getType().toString();
		String expressionString = expressionInOCL.toString();
		String displayString = typeString + ": " + expressionString;
		return displayString;
	}

	@Override
	public Image getImage() {
		return OCLDebugUIPlugin.getDefault().createImage("icons/OCLModelFile.gif");
	}

	@Override
	public void initializeFrom(ILaunchConfiguration configuration) {
		assert !initializing;
		try {
			initializing = true;
			String constraintUri = configuration.getAttribute(CONSTRAINT_URI, "");
			if (constraintUri.length() > 0) {
				URI constraintURI = URI.createURI(constraintUri);
				@NonNull URI oclASURI = constraintURI.trimFragment();
				URI oclNonASURI = PivotUtilInternal.getNonASURI(oclASURI);
				oclPath.setText(String.valueOf(oclNonASURI));
				EObject eObject = getEnvironmentFactory().getMetamodelManager().getASResourceSet().getEObject(constraintURI, true);
				if (eObject instanceof Constraint) {
					LanguageExpression specification = ((Constraint) eObject).getOwnedSpecification();
					if (specification != null) {
						try {
							ExpressionInOCL query = ((EnvironmentFactoryInternalExtension)getEnvironmentFactory()).parseSpecification(specification);
							String displayString = getDisplayString(query);
							int index = expressionCombo.indexOf(displayString);
							expressionCombo.select(index);
						} catch (ParserException e) {}
					}
				}
			}
			else {
				oclPath.setText(ClassUtil.nonNullState(configuration.getAttribute(OCL_KEY, "")));
			}
			String contextUri = configuration.getAttribute(CONTEXT_URI, "");
			if (contextUri.length() > 0) {
				URI contextURI = URI.createURI(contextUri);
				modelPath.setText(String.valueOf(contextURI.trimFragment()));
				EObject eObject = getEnvironmentFactory().getResourceSet().getEObject(contextURI, true);
				if (eObject  != null) {
					String displayString = LabelUtil.getLabel(eObject);
					int index = elementCombo.indexOf(displayString);
					elementCombo.select(index);
				}
			}
			else {
				modelPath.setText(ClassUtil.nonNullState(configuration.getAttribute(MODEL_URI, "")));
			}
		} catch (CoreException e) {
			//Ignore
		} finally {
			initializing = false;
			updateLaunchConfigurationDialog();
		}
	}

	@Override
	public void performApply(ILaunchConfigurationWorkingCopy configuration) {
		int expressionIndex = expressionCombo.getSelectionIndex();
		if ((0 <= expressionIndex) && (expressionIndex < expressionCombo.getItemCount())) {
			String expressionText = expressionCombo.getItem(expressionIndex);
			URI expressionURI = expression2uri.get(expressionText);
			configuration.setAttribute(CONSTRAINT_URI, String.valueOf(expressionURI));
			configuration.removeAttribute(OCL_KEY);
		}
		else {
			configuration.setAttribute(OCL_KEY, oclPath.getText());
			configuration.setAttribute(CONSTRAINT_URI, "");
		}
		int elementIndex = elementCombo.getSelectionIndex();
		if ((0 <= elementIndex) && (elementIndex < elementCombo.getItemCount())) {
			String contextText = elementCombo.getItem(elementIndex);
			URI contextURI = element2uri.get(contextText);
			configuration.setAttribute(CONTEXT_URI, String.valueOf(contextURI));
			configuration.removeAttribute(MODEL_URI);
		}
		else {
			configuration.setAttribute(MODEL_URI, modelPath.getText());
			configuration.setAttribute(CONTEXT_URI, "");
		}
	}

	@Override
	protected void setDefaults(@NonNull ILaunchConfigurationWorkingCopy configuration, @NonNull IFile iFile) {
		configuration.setAttribute(OCL_KEY, iFile.getFullPath().toString());
	}
}
