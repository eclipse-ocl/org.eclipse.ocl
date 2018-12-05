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
package org.eclipse.ocl.ui.internal.preferences;

import java.util.List;

import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.ocl.common.preferences.PreferenceableOption;
import org.eclipse.ocl.common.ui.internal.preferences.AbstractProjectPreferencePage;
import org.eclipse.ocl.lpg.ProblemHandler;
import org.eclipse.ocl.options.EvaluationOptions;
import org.eclipse.ocl.options.ParsingOptions;
import org.eclipse.ocl.options.ProblemOption;
import org.eclipse.ocl.ui.internal.messages.EcoreAndUMLUIMessages;
import org.eclipse.ocl.util.OCLUtil;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

/**
 * The Project/Property preference page for the Ecore and UML Bindings.
 */
public class EcoreAndUmlProjectPreferencePage extends AbstractProjectPreferencePage
{
	protected static final String[][] LOOKUP_STRATEGIES = new String[][] {
		{ EcoreAndUMLUIMessages.Preference_Severity_ByName, ParsingOptions.PACKAGE_LOOKUP_STRATEGIES.LOOKUP_PACKAGE_BY_NAME.toString() },
		{ EcoreAndUMLUIMessages.Preference_Severity_ByAlias, ParsingOptions.PACKAGE_LOOKUP_STRATEGIES.LOOKUP_PACKAGE_BY_ALIAS.toString()  },
		{ EcoreAndUMLUIMessages.Preference_Severity_ByAliasThenName, ParsingOptions.PACKAGE_LOOKUP_STRATEGIES.LOOKUP_PACKAGE_BY_ALIAS_THEN_NAME.toString()  }
	};
	protected static final String[][] SEVERITY_NAMES_AND_IDS = new String[][] {
		{ EcoreAndUMLUIMessages.Preference_Severity_Ok, ProblemHandler.Severity.OK.name() },
		{ EcoreAndUMLUIMessages.Preference_Severity_Info, ProblemHandler.Severity.INFO.name() },
		{ EcoreAndUMLUIMessages.Preference_Severity_Warning, ProblemHandler.Severity.WARNING.name() },
		{ EcoreAndUMLUIMessages.Preference_Severity_Error, ProblemHandler.Severity.ERROR.name() }
	};

	public EcoreAndUmlProjectPreferencePage() {
		super(OCLUtil.PLUGIN_ID, EcoreAndUMLUIMessages.PageTitle);
	}

	@Override
	protected AbstractProjectPreferencePage createClonePage() {
		return new EcoreAndUmlProjectPreferencePage();
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
		fields.add(new MyComboFieldEditor((PreferenceableOption<?>) ParsingOptions.DEFINITION_CONSTRAINS_FEATURE,
			EcoreAndUMLUIMessages.LPG_DefinitionConstrainsFeature, BOOLEANS, fieldEditorParent));
		fields.add(new MyComboFieldEditor((PreferenceableOption<?>) ParsingOptions.USE_COMPARE_TO_OPERATION,
			EcoreAndUMLUIMessages.LPG_UseCompareToOperation, BOOLEANS, fieldEditorParent));
		fields.add(new MyComboFieldEditor((PreferenceableOption<?>) ParsingOptions.USE_LONG_INTEGERS,
			EcoreAndUMLUIMessages.LPG_UseLongIntegers, BOOLEANS, fieldEditorParent));
		fields.add(new MyComboFieldEditor((PreferenceableOption<?>) ParsingOptions.WARN_OF_XOR_OR_AND_PRECEDENCE_CHANGE,
			EcoreAndUMLUIMessages.LPG_WarnOfXorOrAndPrecedenceChange, BOOLEANS, fieldEditorParent));
		fields.add(new MyComboFieldEditor((PreferenceableOption<?>) ParsingOptions.PACKAGE_LOOKUP_STRATEGY,
			EcoreAndUMLUIMessages.LPG_PackageLookupStrategy, LOOKUP_STRATEGIES, fieldEditorParent));
		fields.add(new MyComboFieldEditor((PreferenceableOption<?>) ParsingOptions.USE_BACKSLASH_ESCAPE_PROCESSING,
			EcoreAndUMLUIMessages.LPG_UseBackslashEscapeProcessing, BOOLEANS, fieldEditorParent));
		fields.add(new MyComboFieldEditor((PreferenceableOption<?>) ParsingOptions.SUPPORT_STATIC_FEATURES,
			EcoreAndUMLUIMessages.LPG_SupportStaticFeatures, BOOLEANS, fieldEditorParent));
//		TypeCaches have significant consequences. Only allow enabling programmatically.
//		fields.add(new MyComboFieldEditor((PreferenceableOption<?>) ParsingOptions.USE_TYPE_CACHES,
//			EcoreAndUMLUIMessages.LPG_UseTypeCaches, BOOLEANS, fieldEditorParent));
		fields.add(new MyComboFieldEditor((PreferenceableOption<?>) ParsingOptions.OVERLOAD_AMBIGUITY_IS_INVALID,
			EcoreAndUMLUIMessages.LPG_OverloadAmbiguityIsInvalid, BOOLEANS, fieldEditorParent));
		horizontalLine = new Label(fieldEditorParent, SWT.SEPARATOR | SWT.HORIZONTAL);
		horizontalLine.setLayoutData(new GridData(GridData.FILL, GridData.FILL, false, false, 2, 1));
		horizontalLine.setFont(fieldEditorParent.getFont());
		fields.add(new MyComboFieldEditor(ProblemOption.CLOSURE_ITERATOR,
			EcoreAndUMLUIMessages.LPG_ClosureIterator, SEVERITY_NAMES_AND_IDS, fieldEditorParent));
		fields.add(new MyComboFieldEditor(ProblemOption.STRING_CASE_CONVERSION,
			EcoreAndUMLUIMessages.LPG_StringCaseConversion, SEVERITY_NAMES_AND_IDS, fieldEditorParent));
		fields.add(new MyComboFieldEditor(ProblemOption.STRING_SINGLE_QUOTE_ESCAPE,
			EcoreAndUMLUIMessages.LPG_StringSingleQuoteEscape, SEVERITY_NAMES_AND_IDS, fieldEditorParent));
		fields.add(new MyComboFieldEditor(ProblemOption.ELEMENT_NAME_QUOTE_ESCAPE,
			EcoreAndUMLUIMessages.LPG_ElementNameQuoteEscape, SEVERITY_NAMES_AND_IDS, fieldEditorParent));
		fields.add(new MyComboFieldEditor(ProblemOption.AMBIGUOUS_ASSOCIATION_ENDS,
			EcoreAndUMLUIMessages.LPG_AmbiguousAssociationEnds, SEVERITY_NAMES_AND_IDS, fieldEditorParent));
		fields.add(new MyComboFieldEditor(ProblemOption.INHERITED_FEATURE_CONTEXT,
			EcoreAndUMLUIMessages.LPG_InheritedFeatureContext, SEVERITY_NAMES_AND_IDS, fieldEditorParent));
		fields.add(new MyComboFieldEditor(ProblemOption.CONCEPTUAL_OPERATION_NAME,
			EcoreAndUMLUIMessages.LPG_ConceptualOperationName, SEVERITY_NAMES_AND_IDS, fieldEditorParent));
		horizontalLine = new Label(fieldEditorParent, SWT.SEPARATOR | SWT.HORIZONTAL);
		horizontalLine.setLayoutData(new GridData(GridData.FILL, GridData.FILL, false, false, 2, 1));
		horizontalLine.setFont(fieldEditorParent.getFont());
		fields.add(new MyComboFieldEditor((PreferenceableOption<?>) EvaluationOptions.ANY_LESS_IS_INVALID,
			EcoreAndUMLUIMessages.LPG_AnyLessIteratorReturnsInvalid, ANY_LESS_VALUES, fieldEditorParent));
		fields.add(new MyComboFieldEditor((PreferenceableOption<?>) EvaluationOptions.CLOSURE_INCLUDES_SOURCES,
			EcoreAndUMLUIMessages.LPG_ClosureIncludesSources, BOOLEANS, fieldEditorParent));
		fields.add(new MyComboFieldEditor((PreferenceableOption<?>) EvaluationOptions.LAX_NULL_HANDLING,
			EcoreAndUMLUIMessages.LPG_LaxNullHandling, BOOLEANS, fieldEditorParent));
		String[][] Objects = new String[][] {
			{ "null", null }, //$NON-NLS-1$
			{ "EObject", EcorePackage.Literals.class.getName() + ".EOBJECT" } //$NON-NLS-1$ //$NON-NLS-2$
		};
		fields.add(new MyComboFieldEditor((PreferenceableOption<?>) EvaluationOptions.DYNAMIC_DISPATCH,
			EcoreAndUMLUIMessages.LPG_DynamicDispatch, BOOLEANS, fieldEditorParent));
		fields.add(new MyComboFieldEditor((PreferenceableOption<?>) ParsingOptions.IMPLICIT_ROOT_CLASS,
				EcoreAndUMLUIMessages.LPG_ImplicitRootClass, Objects, fieldEditorParent));
	}
}