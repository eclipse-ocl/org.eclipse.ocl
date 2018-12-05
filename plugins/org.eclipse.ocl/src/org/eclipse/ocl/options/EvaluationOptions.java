/*******************************************************************************
 * Copyright (c) 2007, 2018 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 *   Zeligsoft - Bug 253252
 *******************************************************************************/

package org.eclipse.ocl.options;

import org.eclipse.ocl.EvaluationEnvironment;
import org.eclipse.ocl.util.OCLUtil;


/**
 * Options applicable to {@link EvaluationEnvironment}s to
 * {@linkplain Customizable customize} their evaluation behaviour.
 * 
 * @author Christian W. Damus (cdamus)
 * 
 * @since 1.2
 */
public class EvaluationOptions {

    /**
     * <p>
     * Evaluation option indicating whether to implement lax handling of null
     * values in certain <tt>OclAny</tt>-defined operations.  When <tt>true</tt>,
     * the <tt>null.oclIsKindOf(OclType)</tt> and <tt>null.oclIsTypeOf(OclType)</tt>
     * operations will return <tt>true</tt> for any OCL type instead of
     * returning <tt>OclInvalid</tt>, as <tt>OclVoid</tt> is defined as
     * conforming to all other types.  Similarly, <tt>null.oclAsType(OclType)</tt>
     * will return <tt>null</tt> for any OCL type instead of <tt>OclInvalid</tt>
     * as prescribed by the OCL 2.0 Specification.
     * </p><p>
     * For backward compatibility with the 1.1 release behaviour, the default
     * value of this option is <tt>true</tt>.  For strict conformance to the
     * specification, use <tt>false</tt>.
     * </p>
     * 
     * @since 1.2
     */
    public static final Option<Boolean> LAX_NULL_HANDLING = new BooleanOption(
    		OCLUtil.PLUGIN_ID, "lax.null.handling", true); //$NON-NLS-1$

    /**
     * <p>
     * Evaluation option indicating whether to perform dynamic source-type dependent
     * dispatch of operation calls.
     * </p><p>
     * For backward compatibility, the default value of this option is <tt>false</tt>. 
     * For Object Oriented behaviour use <tt>true</tt>.
     * </p><p>
     * Prior to OCL 2.5, when Object Oriented behaviour may be specified explicitly, it is
     * debateable as to which behaviour is specification compliant.
     * </p><p>
     * To avoid a major performance degradation, the dynamic dispatcher uses a cache to
     * avoid repeated searches for operations in the inheritance tree. The consequent cost
     * of dynamic dispatch may therefore be a few percent, If the parsing option to use
     * type caches is enabled, the evaluator may reuse the analysis cache and avoid some
     * startup costs.
     * </p>
     * 
     * @since 3.2
     */
    public static final Option<Boolean> DYNAMIC_DISPATCH = new BooleanOption(
    		OCLUtil.PLUGIN_ID, "dynamic.dispatch", false); //$NON-NLS-1$

    /**
     * <p>
     * Evaluation option indicating whether an any iteration that selection no values
     * should return invalid (true) or null (false option, default option).
     * </p><p>
     * The OCL specification up until at least 2.3.1 has a contradiction with words specifying that
     * the return value should be null, while the equivalent OCL specifies that the return should
     * be invalid. Since null could be an intended value as in Set{null}->any(true), the invalid
     * return is clearly correct (OMG Issue 18504). However for compatibility null is the default.
     * </p><p>
     * Prior to the Kepler release: the Eclipse OCL 3.3 (Ecore), 4.1 (UML)  return value was null
     * </p>
     * 
     * @since 3.3
     */
    public static final Option<Boolean> ANY_LESS_IS_INVALID = new BooleanOption(
    		OCLUtil.PLUGIN_ID, "anyless.invalid", false); //$NON-NLS-1$

    /**
     * <p>
     * Evaluation option indicating whether a closure includes the sources (true) or excludes them (false, default).
     * should return invalid (true) or null (false option, default option).
     * </p><p>
     * The closure iteration was prototyped by the Classic OCL and omitted sources, however when
     * adopted by OCL 2.3 sources are included. So the default gives the legacy behavior. Setting
     * the option true gives the OCL standard behavior.
     * </p>
     * 
     * @since 3.4
     */
    public static final Option<Boolean> CLOSURE_INCLUDES_SOURCES = new BooleanOption(
    		OCLUtil.PLUGIN_ID, "closure.includes.sources", false); //$NON-NLS-1$

    /**
     * Not instantiable by clients.
     */
    private EvaluationOptions() {
        super();
    }

    /**
     * Add an option to apply to the specified environment, adapting it as
     * necessary to the {@link Customizable} API.
     * 
     * @param env an evaluation environment on which to set an option
     * @param option the option
     * @param value the option's value
     * 
     * @see Customizable#setOption(Option, Object)
     */
    public static <T> void setOption(EvaluationEnvironment<?, ?, ?, ?, ?> env,
            Option<T> option, T value) {
        
        Customizable custom = OCLUtil.getAdapter(env, Customizable.class);
        if (custom != null) {
            custom.setOption(option, value);
        }
    }

    /**
     * Obtains the value of the specified option's setting in the the given
     * environment's options map, adapting the environment as necessary to the
     * {@link Customizable} API.  If not already set, return the option's
     * {@linkplain Option#getDefaultValue() default value}.
     * 
     * @param env an evaluation environment on which to query an option
     * @param option an option to query
     * 
     * @return value of the option
     * 
     * @see Customizable#getValue(Option)
     */
    public static <T> T getValue(EvaluationEnvironment<?, ?, ?, ?, ?> env,
            Option<T> option) {
        
        Customizable custom = OCLUtil.getAdapter(env, Customizable.class);
        if (custom != null) {
            return custom.getValue(option);
        }
        
        return option.getDefaultValue();
    }

}
