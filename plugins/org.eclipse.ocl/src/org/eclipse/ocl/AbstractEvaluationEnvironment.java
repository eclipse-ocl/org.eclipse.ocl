/*******************************************************************************
 * Copyright (c) 2007, 2019 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 *******************************************************************************/

package org.eclipse.ocl;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.common.util.EList;
import org.eclipse.ocl.common.OCLCommon;
import org.eclipse.ocl.common.preferences.PreferenceableOption;
import org.eclipse.ocl.internal.OCLPlugin;
import org.eclipse.ocl.internal.OCLStatusCodes;
import org.eclipse.ocl.internal.evaluation.NumberUtil;
import org.eclipse.ocl.internal.l10n.OCLMessages;
import org.eclipse.ocl.options.Customizable;
import org.eclipse.ocl.options.Option;
import org.eclipse.ocl.types.TupleType;
import org.eclipse.ocl.util.Adaptable;
import org.eclipse.ocl.util.CollectionUtil;
import org.eclipse.ocl.util.OCLUtil;
import org.eclipse.ocl.util.Tuple;
import org.eclipse.ocl.utilities.PredefinedType;

/**
 * A partial implementation of the {@link EvaluationEnvironment} interface,
 * providing some useful common behaviors.  Implementors of metamodel-specific
 * environments are encourage to extend this class rather than implement
 * an evaluation environment "from scratch."
 * <p>
 * See the {@link Environment} class for a description of the
 * generic type parameters of this class.
 * </p><p>
 * Since the 1.2 release, this interface is {@link Adaptable} to support the
 * optional adapter protocols such as {@link EvaluationEnvironment.Enumerations}
 * and {@link Customizable}.
 * </p>
 *
 * @author Christian W. Damus (cdamus)
 */
public abstract class AbstractEvaluationEnvironment<C, O, P, CLS, E>
		implements EvaluationEnvironment<C, O, P, CLS, E>, Adaptable, Customizable {

    private final EvaluationEnvironment<C, O, P, CLS, E> parent;
    private final Map<String, Object> map = new HashMap<String, Object>();

    private final Map<Option<?>, Object> options =
        new java.util.HashMap<Option<?>, Object>();

    protected AbstractEvaluationEnvironment() {
    	this(null);
    }

    protected AbstractEvaluationEnvironment(
    		EvaluationEnvironment<C, O, P, CLS, E> parent) {

    	this.parent = parent;
    }

    /**
     * Obtains my parent (nesting) environment.
     *
     * @return my parent environment, or <code>null</code> if none
     */
    protected EvaluationEnvironment<C, O, P, CLS, E> getParent() {
    	return parent;
    }

    /**
     * Returns the value associated with the supplied name
     *
     * @param name
     *            the name whose value is to be returned
     * @return the value associated with the name
     */
    public Object getValueOf(String name) {
        return map.get(name);
    }

    /**
     * Replaces the current value of the supplied name with the supplied value.
     *
     * @param name
     *            the name
     * @param value
     *            the new value
     */
    public void replace(String name, Object value) {
        map.put(name, value);
    }

    /**
     * Adds the supplied name and value binding to the environment
     *
     * @param name
     *            the name to add
     * @param value
     *            the associated binding
     */
    public void add(String name, Object value) {
        if (map.containsKey(name)) {
            String message = OCLMessages.bind(
            		OCLMessages.BindingExist_ERROR_,
                    name,
                    map.get(name));
            throw new IllegalArgumentException(message);
        }
        map.put(name, value);
    }

    /**
     * Removes the supplied name and binding from the environment (if it exists)
     * and returns it.
     *
     * @param name
     *            the name to remove
     * @return the value associated with the removed name
     */
    public Object remove(String name) {
        return map.remove(name);
    }

    /**
     * Clears the environment of variables.
     */
    public void clear() {
        map.clear();
    }

    /**
     * Returns a string representation of the bindings
     */
    @Override
    public String toString() {
        return map.toString();
    }

    /**
     * By default, a subclass will not support overriding the operations defined
     * by the OCL Standard Library.  This implementation delegates to the
     * parent environment (if any), otherwise returns <code>false</code>.
     */
    public boolean overrides(O operation, int opcode) {
    	return (getParent() != null)? getParent().overrides(operation, opcode) : false;
    }

    /**
     * Implements the inherited method by attempting to find an appropriate
     * Java method in the actual type of the <tt>source</tt> object and invoking
     * it.  On failure to find or invoke the method (e.g., an exception), the
     * <tt>OclInvalid</tt> result is returned.
     *
     * @return the result of the Java method invocation, or <tt>OclInvalid</tt>
     *    on failure of the method invocation
     */
    public Object callOperation(O operation, int opcode, Object source, Object[] args)

		throws IllegalArgumentException {

    	if (getParent() != null) {
    		return getParent().callOperation(operation, opcode, source, args);
    	}

    	Method method = getJavaMethodFor(operation, source);

    	if (method != null) {
    		try {
    		    // coerce any collection arguments to EList as necessary
    		    Class<?>[] parmTypes = method.getParameterTypes();
    		    for (int i = 0; i < parmTypes.length; i++) {
    		        if (EList.class.isAssignableFrom(parmTypes[i])) {
    		            if (args[i] == null) {
    		                args[i] = ECollections.EMPTY_ELIST;
    		            } else if (!(args[i] instanceof Collection<?>)) {
    		                EList<Object> list = new BasicEList.FastCompare<Object>(1);
    		                list.add(args[i]);
    		                args[i] = list;
    		            } else if (!(args[i] instanceof EList<?>)) {
    		                args[i] = new BasicEList.FastCompare<Object>((Collection<?>) args[i]);
    		            }
    		        }
    		    }

				return method.invoke(source, args);
			} catch (Exception e) {
				String context = "callOperation - " + method.toString(); //$NON-NLS-1$
				OCLPlugin.catching(getClass(), context, e);
				OCLPlugin.log(
					Diagnostic.WARNING,
					OCLStatusCodes.IGNORED_EXCEPTION_WARNING,
					OCLMessages.bind(
						OCLMessages.ErrorMessage_ERROR_,
						context,
						e.getLocalizedMessage()),
					e);
				return getInvalidResult();
			}
    	}

    	// maybe it's a comparison operation that is implemented implicitly
    	// via the Comparable interface?
    	switch (opcode) {
    	    case PredefinedType.LESS_THAN:
    	    case PredefinedType.GREATER_THAN:
    	    case PredefinedType.LESS_THAN_EQUAL:
    	    case PredefinedType.GREATER_THAN_EQUAL:
            if ((source instanceof Comparable<?>) && (args.length == 1)) {
                @SuppressWarnings("unchecked")
                Comparable<Object> comparable = (Comparable<Object>) source;
                Object other = args[0];

            	switch (opcode) {
                    case PredefinedType.LESS_THAN:
                        return comparable.compareTo(other) < 0;
                    case PredefinedType.GREATER_THAN:
                        return comparable.compareTo(other) > 0;
                    case PredefinedType.LESS_THAN_EQUAL:
                        return comparable.compareTo(other) <= 0;
                    case PredefinedType.GREATER_THAN_EQUAL:
                        return comparable.compareTo(other) >= 0;
            	}
            }
            break;
    	}

    	throw new IllegalArgumentException();
    }

	/**
	 * Returns the java method that corresponds to the supplied
	 * <code>EOperation</code>
	 *
	 * @param operation
	 *            the operation
	 * @return a java method
	 */
	protected abstract Method getJavaMethodFor(O operation, Object receiver);

	/**
	 * Obtains the language-binding-specific representation of the predefined
	 * <tt>OclInvalid</tt> object.
	 *
	 * @return <tt>OclInvalid</tt>
	 */
	protected abstract Object getInvalidResult();

	/**
	 * Implements the interface method by testing whether I am an instance of
	 * the requested adapter type.
	 */
	@SuppressWarnings("unchecked")
	public <T> T getAdapter(Class<T> adapterType) {
		T result;

		if (adapterType.isInstance(this)) {
			result = (T) this;
		} else {
			result = null;
		}

		return result;
	}

    protected Map<Option<?>, Object> basicGetOptions() {
        return options;
    }

    public Map<Option<?>, Object> getOptions() {
        Customizable parent = (getParent() != null)?
            OCLUtil.getAdapter(getParent(), Customizable.class) : null;

        Map<Option<?>, Object> result = (parent != null)
        	? new java.util.HashMap<Option<?>, Object>(parent.getOptions())
            : new java.util.HashMap<Option<?>, Object>();

        result.putAll(basicGetOptions());

        return result;
    }

    public <T> void setOption(Option<T> option, T value) {
        basicGetOptions().put(option, value);
    }

    public <T> void putOptions(Map<? extends Option<T>, ? extends T> options) {
        Map<Option<?>, Object> myOptions = basicGetOptions();

        myOptions.clear();
        myOptions.putAll(options);
    }

    public <T> T removeOption(Option<T> option) {
        T result = getValue(option);

        basicGetOptions().remove(option);

        return result;
    }

    public <T> Map<Option<T>, T> removeOptions(Collection<Option<T>> options) {
        Map<Option<T>, T> result = new java.util.HashMap<Option<T>, T>();

        Map<Option<?>, Object> myOptions = basicGetOptions();

        for (Option<T> next : options) {
            result.put(next, getValue(next));
            myOptions.remove(next);
        }

        return result;
    }

    public Map<Option<?>, Object> clearOptions() {
        Map<Option<?>, Object> myOptions = basicGetOptions();

        Map<Option<?>, Object> result = new java.util.HashMap<Option<?>, Object>(
                myOptions);

        myOptions.clear();

        return result;
    }

    public boolean isEnabled(Option<Boolean> option) {
        Boolean result = getValue(option);
        return (result == null)? false : result.booleanValue();
    }

    public <T> T getValue(Option<T> option) {
		Map<Option<?>, Object> options = basicGetOptions();
        @SuppressWarnings("unchecked")
        T result = (T) options.get(option);
		if ((result == null) && !options.containsKey(option)) {
            Customizable parent = (getParent() != null)?
                OCLUtil.getAdapter(getParent(), Customizable.class) : null;

            if (parent != null) {
            	result = parent.getValue(option);
            }
			else {
				if (option instanceof PreferenceableOption<?>) {
				    @SuppressWarnings("unchecked")
					PreferenceableOption<T> preferenceOption = (PreferenceableOption<T>)option;
					result = OCLCommon.getPreference(preferenceOption, null);
				}
	            else {
	            	result = option.getDefaultValue();
	            }
				options.put(option, result);
			}
        }

        return result;
    }


    /**
     * UML implementation of a tuple value.
     *
     * @author Christian W. Damus (cdamus)
     * @since 3.2
     */
    protected static abstract class AbstractTuple<O, P>
        implements Tuple<O, P> {

        private final TupleType<O, P> type;
        private final Map<String, Object> parts = new java.util.HashMap<String, Object>();
        private Integer hashCode = null;

        /**
         * Initializes me with a map of part values.
         *
         * @param type
         *            my type
         * @param values
         *            my parts
         */
        protected AbstractTuple(TupleType<O, P> type,
                Map<P, Object> values) {
            this.type = type;

            for (Map.Entry<P, Object> entry : values.entrySet()) {
                parts.put(getName(entry.getKey()), entry.getValue());
            }
        }

        protected abstract String getName(P part);

        // implements the inherited specification
        public TupleType<O, P> getTupleType() {
            return type;
        }

        // implements the inherited specification
        public Object getValue(String partName) {
            Object partValue = parts.get(partName);
			if (partValue instanceof Number) {
				partValue = NumberUtil.coerceNumber((Number)partValue);
			}
			return partValue;
        }

        // implements the inherited specification
        public Object getValue(P part) {
            return getValue(getName(part));
        }

        // overrides the inherited implementation
        @Override
        public boolean equals(Object o) {
            boolean result = o instanceof AbstractTuple<?,?>;

            if (result) {
            	AbstractTuple<?,?> other = (AbstractTuple<?,?>) o;

                result &= other.type.equals(type);
                result &= other.parts.equals(parts);
            }

            return result;
        }

        // overrides the inherited implementation
        @Override
        public int hashCode() {
        	if (hashCode == null) {
                int typeHashCode = type.hashCode();
        		int partsHashCode = parts.hashCode();
        		hashCode = 37 * typeHashCode + 17 * partsHashCode;
        	}
    		return hashCode;
         }

        @Override
        public String toString() {
            StringBuilder result = new StringBuilder();
            result.append("Tuple{"); //$NON-NLS-1$

            for (Iterator<P> iter =  getTupleType().oclProperties().iterator();
                    iter.hasNext();) {

            	P p = iter.next();

                result.append(getName(p));
                result.append(" = "); //$NON-NLS-1$
                result.append(toString(getValue(p)));

                if (iter.hasNext()) {
                    result.append(", "); //$NON-NLS-1$
                }
            }

            result.append("}"); //$NON-NLS-1$
            return result.toString();
        }

        private String toString(Object o) {
            if (o instanceof String) {
                return "'" + (String) o + "'"; //$NON-NLS-1$ //$NON-NLS-2$
            } else if (o instanceof Collection<?>) {
                return CollectionUtil.toString((Collection<?>) o);
            } else if (o == null) {
                return "null"; //$NON-NLS-1$
            } else {
                return o.toString();
            }
        }
    }

}
