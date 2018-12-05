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
package org.eclipse.ocl.uml.ui.internal.preferences;

import java.util.List;

import org.eclipse.ocl.common.preferences.PreferenceableOption;
import org.eclipse.ocl.common.ui.internal.preferences.AbstractProjectPreferencePage;
import org.eclipse.ocl.uml.options.EvaluationMode;
import org.eclipse.ocl.uml.options.UMLEvaluationOptions;
import org.eclipse.ocl.uml.options.UMLParsingOptions;
import org.eclipse.ocl.uml.ui.internal.messages.UMLUIMessages;
import org.eclipse.ocl.uml.util.OCLUMLUtil;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.AssociationClass;

/**
 * The Project/Property preference page for the UML Bindings.
 */
public class UMLProjectPreferencePage extends AbstractProjectPreferencePage
{
	protected static final String[][] EVALUATION_MODES = new String[][] {
		{ UMLUIMessages.UML_EvaluationMode_InstanceModel, EvaluationMode.INSTANCE_MODEL.toString() },
		{ UMLUIMessages.UML_EvaluationMode_RuntimeObjects, EvaluationMode.RUNTIME_OBJECTS.toString()  },
		{ UMLUIMessages.UML_EvaluationMode_Adaptive, EvaluationMode.ADAPTIVE.toString()  }
	};
	
	public UMLProjectPreferencePage() {
		super(OCLUMLUtil.PLUGIN_ID, UMLUIMessages.UML_PageTitle);
	}

	@Override
	protected AbstractProjectPreferencePage createClonePage() {
		return new UMLProjectPreferencePage();
	}
	
	/**
	 * Creates the field editors. Field editors are abstractions of
	 * the common GUI blocks needed to manipulate various types
	 * of preferences. Each field editor knows how to save and
	 * restore itself.
	 */
	@Override
	protected void createFieldEditors(Composite fieldEditorParent, List<IFieldEditor> fields) {
		Label horizontalLine= new Label(fieldEditorParent, SWT.SEPARATOR | SWT.HORIZONTAL);
		horizontalLine.setLayoutData(new GridData(GridData.FILL, GridData.FILL, false, false, 2, 1));
		horizontalLine.setFont(fieldEditorParent.getFont());
		String[][] Associations = new String[][] {
			{ Association.class.getSimpleName(), Association.class.getName() },
			{ AssociationClass.class.getSimpleName(), AssociationClass.class.getName() }
		};
		fields.add(new MyComboFieldEditor((PreferenceableOption<?>) UMLParsingOptions.ASSOCIATION_CLASS_TYPE,
				UMLUIMessages.UML_AssociationClassType, Associations, fieldEditorParent));
		fields.add(new MyComboFieldEditor((PreferenceableOption<?>) UMLEvaluationOptions.EVALUATION_MODE,
				UMLUIMessages.UML_EvaluationMode, EVALUATION_MODES, fieldEditorParent));
	}
}