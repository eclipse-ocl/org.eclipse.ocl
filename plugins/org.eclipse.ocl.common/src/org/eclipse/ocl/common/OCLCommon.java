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
package org.eclipse.ocl.common;

import java.util.List;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.preferences.IEclipsePreferences.IPreferenceChangeListener;
import org.eclipse.core.runtime.preferences.IEclipsePreferences.PreferenceChangeEvent;
import org.eclipse.core.runtime.preferences.IPreferencesService;
import org.eclipse.core.runtime.preferences.IScopeContext;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.ocl.common.preferences.PreferenceableOption;
import org.eclipse.ocl.common.preferences.PreferenceableOption.PreferenceableOption2;

/**
 * The Facade for common Eclipse OCL facilities.
 */
public class OCLCommon implements OCLConstants
{
	/**
	 * A DefaultDefaultDelegationMode.run() scans the validationDelegates extension points
	 * to determine whether Pivot functionality is available and so returns the relevant delegate
	 * URI for use as the default.degate.mode..
	 *
	 * This code is factored into a separate static class to ensure that classes that are not
	 * available standalone are not loaded before EMFPlugin.IS_ECLIPSE_RUNNING is checked.
	 */
	private static final class DefaultDefaultDelegationMode
	{
		public String run() {
			IExtensionRegistry pluginRegistry = Platform.getExtensionRegistry();
			String pluginID = EcorePlugin.getPlugin().getBundle().getSymbolicName();
			IExtensionPoint point = pluginRegistry.getExtensionPoint(pluginID, EcorePlugin.VALIDATION_DELEGATE_PPID);
			if (point != null) {
				String pivotURI = OCLConstants.OCL_DELEGATE_URI_PIVOT;
				IConfigurationElement[] elements = point.getConfigurationElements();
				for (int i = 0; i < elements.length; i++) {
					String uri = elements[i].getAttribute("uri");						//$NON-NLS-1$
					if (pivotURI.equals(uri)) {
						return pivotURI;
					}
				}
			}
			return null;
		}
	}

	/**
	 * A PreferenceListenerInstaller installs itself as a IPreferenceChangeListener on an option
	 * so that PreferenceableOption2.fireChanged is invoked for any change.
	 *
	 * This code is factored into a separate static class to ensure that classes that are not
	 * available standalone are not loaded before EMFPlugin.IS_ECLIPSE_RUNNING is checked.
	 */
	private static final class PreferenceListenerInstaller implements IPreferenceChangeListener
	{
		@SuppressWarnings("deprecation")
		private static final InstanceScope INSTANCE_SCOPE_INSTANCE = new InstanceScope();	// InstanceScope.INSTANCE not available for Galileo
		private final PreferenceableOption2<?> option;

		private PreferenceListenerInstaller(PreferenceableOption2<?> option) {
			this.option = option;
			String qualifier = option.getPluginId();
			INSTANCE_SCOPE_INSTANCE.getNode(qualifier).addPreferenceChangeListener(this);
		}

		public void preferenceChange(PreferenceChangeEvent event) {
			String key = event.getKey();
			if (key != null){
				option.fireChanged(key, event.getOldValue(), event.getNewValue());
			}
		}
	}

	public static final String PLUGIN_ID = "org.eclipse.ocl.common"; //$NON-NLS-1$

	/**
	 * Return the default value of the "default.delegation.mode" preference, returning the LPG value for now.
	 * .
	 * @since 1.1
	 */
	public static String getDefaultDefaultDelegationMode() {
		if (EMFPlugin.IS_ECLIPSE_RUNNING) {
			//
			// org.eclipse.ocl.pivot.utilities.PivotConstants.OCL_DELEGATE_URI_PIVOT is not
			// on the classPath so search for it using the validationDelegates extension point.
			//
			String pivotURI = new DefaultDefaultDelegationMode().run();
			if (pivotURI != null) {
				return pivotURI;
			}
		}
		else {
			//
			// org.eclipse.ocl.pivot.utilities.PivotConstants might be on the classPath.
			//
			try {
				ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
				Class<?> pivotClass = contextClassLoader.loadClass("org.eclipse.ocl.pivot.utilities.PivotConstants"); //$NON-NLS-1$
				return (String) pivotClass.getField("OCL_DELEGATE_URI_PIVOT").get(null); //$NON-NLS-1$
			} catch (Exception e) { /* Can't find it - no need to report as error */ }
		}
		return OCLConstants.OCL_DELEGATE_URI_LPG;
	}

	/**
	 * Return the OCL Delegate EAnnotation, which is an EAnnotation with {@link #OCL_DELEGATE_URI}
	 * as its source, or if no such EAnnotation is present, then the first EAnnotation with a source
	 * whose URI starts with {@link #OCL_DELEGATE_URI} and a / character/
	 */
	public static EAnnotation getDelegateAnnotation(EModelElement eModelElement) {
		List<EAnnotation> eAnnotations = eModelElement.getEAnnotations();
		for (EAnnotation eAnnotation : eAnnotations) {
			String source = eAnnotation.getSource();
			if ((source != null) && source.equals(OCL_DELEGATE_URI)) {
				return eAnnotation;
			}
		}
		for (EAnnotation eAnnotation : eAnnotations) {
			String source = eAnnotation.getSource();
			if ((source != null) && source.startsWith(OCL_DELEGATE_URI_SLASH)) {
				return eAnnotation;
			}
		}
		return null;
	}

	/**
	 * Return the keyed detail of an OCL Delegate EAnnotation, which is an EAnnotation with {@link #OCL_DELEGATE_URI}
	 * as its source, or if no such EAnnotation is present, then the first EAnnotation with a source
	 * whose URI starts with {@link #OCL_DELEGATE_URI} and a / character/
	 */
	public static String getDelegateAnnotation(EModelElement eModelElement, String key) {
	    EAnnotation eAnnotation = getDelegateAnnotation(eModelElement);
	    return eAnnotation == null ? null : (String)eAnnotation.getDetails().get(key);
	}

	/**
	 * Return the preference value for
	 * @param option
	 * @param contexts
	 */
	public static <T> T getPreference(PreferenceableOption<T> option, IScopeContext[] contexts) {
		if (EMFPlugin.IS_ECLIPSE_RUNNING) {
			IPreferencesService preferencesService = Platform.getPreferencesService();
			if (preferencesService != null) {
				String qualifier = option.getPluginId();
				String key = option.getKey();
				T defaultValue = option.getDefaultValue();
				String string = preferencesService.getString(qualifier, key, defaultValue != null ? defaultValue.toString() : "", contexts); //$NON-NLS-1$
				return option.getValueOf(string);
			}
		}
		return option.getDefaultValue(); // Standalone or Eclipse not running
	}

	/**
	 * Install an IPreferenceChangeListener so that option.fireChanged() is notified of any change in the Eclipse preference.
	 * @since 1.1
	 */
	public static <T> void installListener(PreferenceableOption<T> option) {
		if (EMFPlugin.IS_ECLIPSE_RUNNING) {
			IPreferencesService preferencesService = Platform.getPreferencesService();
			if ((preferencesService != null) && (option instanceof PreferenceableOption.PreferenceableOption2)) {
				new PreferenceListenerInstaller((PreferenceableOption.PreferenceableOption2<?>)option);
			}
		}
	}

	/**
	 * Return true if string denotes an OCL Delegate, which is the string {@link #OCL_DELEGATE_URI},
	 * or a string starting with {@link #OCL_DELEGATE_URI} and a / character.
	 */
	public static boolean isDelegateURI(String string) {
		if (string != null) {
			if (string.equals(OCL_DELEGATE_URI)) {
				return true;
			}
			if (string.startsWith(OCL_DELEGATE_URI_SLASH)) {
				return true;
			}
		}
		return false;
	}
}
