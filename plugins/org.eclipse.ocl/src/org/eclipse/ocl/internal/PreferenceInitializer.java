/*******************************************************************************
 * Copyright (c) 2011, 2018 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.internal;

import org.eclipse.ocl.common.internal.preferences.AnnotatedPreferenceInitializer;
import org.eclipse.ocl.common.preferences.PreferenceableOption;
import org.eclipse.ocl.options.EvaluationOptions;
import org.eclipse.ocl.options.ParsingOptions;
import org.eclipse.ocl.options.ProblemOption;

/**
 * Class used to initialize default preference values.
 * 
 * @since 3.2
 */
public class PreferenceInitializer extends AnnotatedPreferenceInitializer
{
	@Override
	public void initializeDefaultPreferences() {
		putPreference(ProblemOption.CLOSURE_ITERATOR);
		putPreference(ProblemOption.STRING_CASE_CONVERSION);
		putPreference(ProblemOption.STRING_SINGLE_QUOTE_ESCAPE);
		putPreference(ProblemOption.ELEMENT_NAME_QUOTE_ESCAPE);
		putPreference(ProblemOption.AMBIGUOUS_ASSOCIATION_ENDS);
		putPreference(ProblemOption.INHERITED_FEATURE_CONTEXT);
		putPreference(ProblemOption.CONCEPTUAL_OPERATION_NAME);

		//
		//	The following options cannot be declared to implement PreferenceableOption<?>
		//	without an API change. Not worth double definitions for initialization only usage.
		//
		putPreference((PreferenceableOption<?>) EvaluationOptions.DYNAMIC_DISPATCH);
		putPreference((PreferenceableOption<?>) EvaluationOptions.LAX_NULL_HANDLING);

		putPreference((PreferenceableOption<?>) ParsingOptions.OVERLOAD_AMBIGUITY_IS_INVALID);
//		TypeCaches have significant consequences. Only allow enabling programmatically.
//		putPreference((PreferenceableOption<?>) ParsingOptions.USE_TYPE_CACHES);
		putPreference((PreferenceableOption<?>) ParsingOptions.DEFINITION_CONSTRAINS_FEATURE);
		putPreference((PreferenceableOption<?>) ParsingOptions.USE_COMPARE_TO_OPERATION);
		putPreference((PreferenceableOption<?>) ParsingOptions.USE_LONG_INTEGERS);
		putPreference((PreferenceableOption<?>) ParsingOptions.WARN_OF_XOR_OR_AND_PRECEDENCE_CHANGE);
		putPreference((PreferenceableOption<?>) ParsingOptions.IMPLICIT_ROOT_CLASS);
		putPreference((PreferenceableOption<?>) ParsingOptions.PACKAGE_LOOKUP_STRATEGY);
		putPreference((PreferenceableOption<?>) ParsingOptions.USE_BACKSLASH_ESCAPE_PROCESSING);
		putPreference((PreferenceableOption<?>) ParsingOptions.SUPPORT_STATIC_FEATURES);
	}
}
