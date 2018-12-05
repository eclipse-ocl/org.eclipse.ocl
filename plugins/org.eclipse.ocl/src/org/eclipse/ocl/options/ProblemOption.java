/*******************************************************************************
 * Copyright (c) 2007, 2018 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.options;

import org.eclipse.ocl.common.preferences.PreferenceableOption;
import org.eclipse.ocl.lpg.ProblemHandler.Severity;
import org.eclipse.ocl.util.OCLUtil;


/**
 * Options for problem-reporting in parsing OCL constraints.  These options
 * determine the severity of usage of constructs that deviate from the OCL
 * Specification.
 * 
 * @author Christian W. Damus (cdamus)
 */
public enum ProblemOption implements PreferenceableOption<Severity>, Option<Severity> {
	/**
	 * Severity of using the non-spec <tt>closure</tt> iterator.
	 * The default severity is <tt>WARNING</tt>.
	 */
	CLOSURE_ITERATOR(OCLUtil.PLUGIN_ID, "iterators.closure", Severity.OK), //$NON-NLS-1$
	
	/**
	 * Severity of using the non-spec <tt>toUpper()</tt> and <tt>toLower()</tt>
	 * operations on <tt>String</tt>s.
	 * The default severity is <tt>WARNING</tt>.
	 */
	STRING_CASE_CONVERSION(OCLUtil.PLUGIN_ID, "string.caseconv", Severity.WARNING), //$NON-NLS-1$
	
	/**
	 * Severity of using the non-spec <tt>''</tt> escape sequence for single-quotes
	 * in string literals.
	 * The default severity is <tt>WARNING</tt>.
	 */
	STRING_SINGLE_QUOTE_ESCAPE(OCLUtil.PLUGIN_ID, "string.escapes.squote", Severity.WARNING), //$NON-NLS-1$
	
	/**
	 * Severity of using the non-spec <tt>"..."</tt> escape sequence for
	 * element names consisting of multiple OCL tokens.
	 * The default severity is <tt>WARNING</tt>.
	 */
	ELEMENT_NAME_QUOTE_ESCAPE(OCLUtil.PLUGIN_ID, "string.escapes.element", Severity.WARNING), //$NON-NLS-1$
    
    /**
     * Severity of the ambiguity when an unnavigable but named associend has the
     * same name as the implicit name of an unnamed association end.
     * The default severity is <tt>ERROR</tt>.
     */
    AMBIGUOUS_ASSOCIATION_ENDS(OCLUtil.PLUGIN_ID, "ambiguous.association.ends", Severity.ERROR), //$NON-NLS-1$
    
    /**
     * Severity of the problem to report when declaring (using the concrete
     * syntax) an operation or property context in a classifier that inherits
     * the feature (rather than declaring a redefinition of the feature as
     * the context).  This is allowed by the MDT OCL implementation but is not,
     * strictly speaking, well-formed OCL.
     * The default severity is <tt>WARNING</tt>.
     */
    INHERITED_FEATURE_CONTEXT(OCLUtil.PLUGIN_ID, "inherited.feature.context", Severity.WARNING), //$NON-NLS-1$
	
	/**
	 * Severity of making use of an operation name conceptually as
	 * in 7.4.8 .
	 * The default severity is <tt>OK</tt>.
	 * @since 3.0
	 */
	CONCEPTUAL_OPERATION_NAME(OCLUtil.PLUGIN_ID, "conceptual.operation.name", Severity.ERROR); //$NON-NLS-1$
	
	private final String pluginId;
	private final String key;
	private final Severity defaultValue;
	
	ProblemOption(String key, Severity defaultValue) {
		this(null, key, defaultValue);
	}
	
	ProblemOption(String pluginId, String key, Severity defaultValue) {
		this.pluginId = pluginId;
		this.key = key;
		this.defaultValue = defaultValue;
	}
	
	public String getKey() {
		return key;
	}
	
	public Severity getDefaultValue() {
		return defaultValue;
	}

	/**
	 * @since 3.2
	 */
	public String getPluginId() {
		return pluginId;
	}

	/**
	 * @since 3.2
	 */
	public Severity getValueOf(String string) {
		if (string != null) {
			try {
				return Severity.valueOf(string);
			} catch (IllegalArgumentException e) {
			}
		}
		return defaultValue;
	}

	@Override
	public String toString() {
		return String.valueOf(key) + "[" + String.valueOf(defaultValue) + "]"; //$NON-NLS-1$ //$NON-NLS-2$
	}
}
