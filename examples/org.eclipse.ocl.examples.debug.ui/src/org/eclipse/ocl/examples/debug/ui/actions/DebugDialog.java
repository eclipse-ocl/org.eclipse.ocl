/*******************************************************************************
 * Copyright (c) 2014, 2021 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     R.Dvorak and others - QVTo debugger framework
 *     E.D.Willink - revised API for OCL debugger framework
 *******************************************************************************/
package org.eclipse.ocl.examples.debug.ui.actions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.ocl.pivot.Constraint;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.internal.manager.MetamodelManagerInternal;
import org.eclipse.ocl.pivot.internal.prettyprint.PrettyPrinter;
import org.eclipse.ocl.pivot.internal.registry.CompleteOCLRegistry;
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal;
import org.eclipse.ocl.pivot.internal.utilities.PivotUtilInternal;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.ParserException;
import org.eclipse.ocl.xtext.base.ui.utilities.PDEUtils;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class DebugDialog extends Dialog
{
	protected class ConstraintsModifyListener implements ModifyListener
	{
		@Override
		public void modifyText(ModifyEvent e) {
			constraintText2constraint.clear();
			List<String> constraints = new ArrayList<String>();
			int selectedIndex = constraintsCombo.getSelectionIndex();
			if (selectedIndex >= 0) {
				String selectedText = constraintsCombo.getItem(selectedIndex);
				URI selectedURI = constraintsText2resourceURI.get(selectedText);
				if (selectedURI != null) {
					EnvironmentFactoryInternal environmentFactory = PivotUtilInternal.getEnvironmentFactory(selectedObject);
					MetamodelManagerInternal metamodelManager = environmentFactory.getMetamodelManager();
					Element resource = null;
					try {
						resource = metamodelManager.loadResource(selectedURI, null, null);
					} catch (ParserException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					if (resource != null) {
						for (TreeIterator<EObject> tit = resource.eAllContents(); tit.hasNext(); ) {
							EObject eObject = tit.next();
							if (eObject instanceof Constraint) {
								String constraintText = eObject.toString();
								constraints.add(constraintText);
								constraintText2constraint.put(constraintText, (Constraint)eObject);
							}
						}
						Collections.sort(constraints);
					}
				}
			}
			constraintCombo.setItems(constraints.toArray(new String[constraints.size()]));
			if (constraints.size() > 0) {
				constraintCombo.select(0);
			}
		}
	}

	protected class ConstraintModifyListener implements ModifyListener
	{
		@Override
		public void modifyText(ModifyEvent e) {
			Constraint selectedConstraint2 = null;
			String text = null;
			int selectedIndex = constraintCombo.getSelectionIndex();
			if (selectedIndex >= 0) {
				String selectedText = constraintCombo.getItem(selectedIndex);
				selectedConstraint2 = constraintText2constraint.get(selectedText);
				if (selectedConstraint2 != null) {
					text = PrettyPrinter.print(selectedConstraint2);
				}
			}
			selectedConstraint = selectedConstraint2;
			constraintText.setText(text != null ? text : "<no-constraint-text>");
		}
	}

	protected final @NonNull CompleteOCLRegistry completeOCLRegistry;
	protected final @NonNull EObject selectedObject;
	protected @Nullable Constraint selectedConstraint;
	private Text elementName;
	private Text elementNsURI;
	private Text elementClass;
	private Text constraintText;
	private Combo constraintsCombo;
	private Combo constraintCombo;
	private final @NonNull Map<String, URI> constraintsText2resourceURI = new HashMap<String, URI>();
	private final @NonNull Map<String, Constraint> constraintText2constraint = new HashMap<String, Constraint>();

	/**
	 * Create the dialog.
	 * @param parentShell
	 * @param selectedObject
	 */
	public DebugDialog(Shell parentShell, @NonNull EObject selectedObject) {
		super(parentShell);
		setShellStyle(SWT.SHELL_TRIM | SWT.BORDER);
		completeOCLRegistry = PDEUtils.createCompleteOCLRegistry();
		this.selectedObject = selectedObject;
	}

	/**
	 * Create contents of the dialog.
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite container = (Composite) super.createDialogArea(parent);
		GridLayout gridLayout = (GridLayout) container.getLayout();
		gridLayout.numColumns = 2;

//		Composite elementRow = new Composite(container, SWT.NONE);
//		GridData gd_elementRow = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
//		gd_elementRow.heightHint = 80;
//		elementRow.setLayoutData(gd_elementRow);
//		elementRow.setLayout(new GridLayout(2, false));

		Label elementLabel = new Label(container, SWT.NONE);
		elementLabel.setSize(43, 15);
		elementLabel.setText("Element");

		elementName = new Text(container, SWT.BORDER);
		elementName.setEditable(false);
		elementName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		elementName.setText("");

		Label eClassLabel = new Label(container, SWT.NONE);
		eClassLabel.setBounds(0, 0, 55, 15);
		eClassLabel.setText("EClass");

		elementClass = new Text(container, SWT.BORDER);
		elementClass.setEditable(false);
		elementClass.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		elementClass.setBounds(0, 0, 76, 21);

		Label nsURILabel = new Label(container, SWT.NONE);
		nsURILabel.setLayoutData(new GridData(SWT.LEFT, SWT.BOTTOM, false, false, 1, 1));
		nsURILabel.setBounds(0, 0, 55, 15);
		nsURILabel.setText("Ns URI");

		elementNsURI = new Text(container, SWT.BORDER);
		elementNsURI.setEditable(false);
		elementNsURI.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		elementNsURI.setBounds(0, 0, 76, 21);

		Label constraintsLabel = new Label(container, SWT.NONE);
		constraintsLabel.setBounds(0, 0, 55, 15);
		constraintsLabel.setText("Constraints");

		constraintsCombo = new Combo(container, SWT.NONE);
		constraintsCombo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		constraintsCombo.setBounds(0, 0, 91, 23);

		Label constraintLabel = new Label(container, SWT.NONE);
		constraintLabel.setBounds(0, 0, 55, 15);
		constraintLabel.setText("Constraint");

		constraintCombo = new Combo(container, SWT.NONE);
		constraintCombo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		constraintText = new Text(container, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL | SWT.CANCEL | SWT.MULTI);
		constraintText.setEditable(false);
		GridData gd_constraintText = new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1);
		gd_constraintText.minimumHeight = 30;
		constraintText.setLayoutData(gd_constraintText);
		//
		constraintsCombo.addModifyListener(new ConstraintsModifyListener());
		constraintCombo.addModifyListener(new ConstraintModifyListener());
		setSelection(selectedObject);
		return container;
	}

	/**
	 * Create contents of the button bar.
	 * @param parent
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL,
				true);
		createButton(parent, IDialogConstants.CANCEL_ID,
				IDialogConstants.CANCEL_LABEL, false);
	}

	/**
	 * Return the initial size of the dialog.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(450, 300);
	}

	public void setSelection(@NonNull EObject eObject) {
		elementName.setText(NameUtil.qualifiedNameFor(eObject));
		EClass eClass = eObject.eClass();
		elementClass.setText(NameUtil.qualifiedNameFor(eClass));
		EPackage ePackage = eClass != null ? eClass.getEPackage() : null;
		String nsURI = ePackage != null ? ePackage.getNsURI() : null;
		elementNsURI.setText(String.valueOf(nsURI));
		@NonNull List<String> elementNsURIs = Collections.singletonList(nsURI);
		Set<URI> resourceURIs = completeOCLRegistry.getResourceURIs(elementNsURIs);
		List<String> constraintNsURIs = new ArrayList<String>();
		constraintsText2resourceURI.clear();
		for (URI resourceURI : resourceURIs) {
			URI leadingSegments = resourceURI.trimSegments(1);
			String lastSegment = resourceURI.lastSegment();
			String constraintsText = lastSegment + " - " + leadingSegments.toString();
			constraintNsURIs.add(constraintsText);
			constraintsText2resourceURI.put(constraintsText, resourceURI);
		}
		Collections.sort(constraintNsURIs);
		constraintsCombo.setItems(constraintNsURIs.toArray(new String[constraintNsURIs.size()]));
		constraintsCombo.select(0);
	}

	public @NonNull EObject getContext() {
		return selectedObject;
	}

	public @Nullable Constraint getConstraint() {
		return selectedConstraint;
	}
}
