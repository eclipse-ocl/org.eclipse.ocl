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
package org.eclipse.ocl.common.preferences;

/**
 * A PreferenceableOption identifies a configurable behavior suitable for use in a Preference
 * or Property Page.
 * <p>
 * The behavior is identified by a <i>key</i> and has a value with a type <i>T</i> and a <i>defaultValue</i>.
 * <p>
 * The value may be persisted as a string and reconstructed by {@link #getValueOf(String)}.
 * 
 * @param <T> the type of the option's value
 */
public interface PreferenceableOption<T>
{
	/**
	 * A Listener may be added to a PreferenceableOption2 and notified of changes in the option.
	 * 
	 * @since 1.1
	 */
	public interface Listener
	{
		/**
		 * Call-back notification that the Eclipse preference identified by a key, has changed from
		 * oldValue to new Value.
		 * 
		 * @param key
		 * @param oldValue
		 * @param newValue
		 */
		void changed(/*@NonNull*/ String key, /*@Nullable*/ Object oldValue, /*@Nullable*/ Object newValue);
	}
	
	/**
	 * The extended PreferenceableOption identifies a configurable behavior suitable for use in a Preference
	 * or Property Page. The extended behavior supports access to the Eclipse preference and listening for
	 * changes in the Eclipse setting.
	 * 
	 * @since 1.1
	 */
	public interface PreferenceableOption2<T> extends PreferenceableOption<T>
	{
		/**
		 * Add a listener to be notified of changes.
		 * 
		 * @param listener
		 */
		void addListener(/*@NonNull*/ Listener listener);

		/**
		 * Call-back notification that the Eclipse preference identified by a key, has changed from
		 * oldValue to new Value.
		 * 
		 * @param key
		 * @param oldValue
		 * @param newValue
		 */
		void fireChanged(/*@NonNull*/ String key, /*@Nullable*/ Object oldValue, /*@Nullable*/ Object newValue);
		
		/**
		 * Get the preferred value of this option. When running standalone this is the built-in default.
		 * When running in Eclipse, the built-in default may be overridden by a user preference.
		 */
		/*@Nullable*/ T getPreferredValue();
		
		/**
		 * Remove a listener to be notified of changes.
		 * 
		 * @param listener
		 */
		void removeListener(/*@NonNull*/ Listener listener);
	}
	/**
	 * Obtains the option's default value.
	 * 
	 * @return my default value, which default-default is <code>null</code>
	 */
	/*@Nullable*/ T getDefaultValue();

	/**
	 * Obtains my string key, which may be used for persistence in a
	 * preference store.
	 * 
	 * @return my key.  Is never <code>null</code>
	 */
	/*@NonNull*/ String getKey();

	/**
	 * Returns the plugin qualifier for the key.
	 */
	/*@NonNull*/ String getPluginId();

	/**
	 * Get the preferred value of this option. When running standalone this is the built-in default.
	 * When running in Eclipse, the built-in default may be overridden by a user preference.
	 *
	 * @since Next major version
	 */
//	@Nullable T getPreferredValue();

	/**
	 * Returns an option value from a String typically obtained from an Eclipse Preference file.
	 */
	/*@Nullable*/ T getValueOf(/*@Nullable*/ String string);
}
