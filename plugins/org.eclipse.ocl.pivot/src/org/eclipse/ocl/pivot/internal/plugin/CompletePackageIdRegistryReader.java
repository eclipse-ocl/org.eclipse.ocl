/**
 * Copyright (c) 2014, 2018 Obeo and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   Obeo - initial API and implementation
 */
package org.eclipse.ocl.pivot.internal.plugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.emf.ecore.plugin.RegistryReader;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.ids.CompletePackageId;
import org.eclipse.ocl.pivot.ids.IdManager;
import org.eclipse.ocl.pivot.util.PivotPlugin;
import org.eclipse.ocl.pivot.utilities.ClassUtil;

/**
 * A plugin extension reader that populates the CompletePackageId mappings.
 *
 * @since 7.0
 */
public class CompletePackageIdRegistryReader extends RegistryReader
{
	private static final @NonNull String TAG_MAPPING = "mapping";
	private static final @NonNull String ATTRIBUTE_PACKAGE_URI = "packageURI";
	private static final @NonNull String ATTRIBUTE_COMPLETE_PACKAGE_ID = "completePackageId";
	private static final @NonNull String ATTRIBUTE_REGEX = "regex";

	private static Map<@NonNull Pattern, @NonNull CompletePackageId> regexMappings = null;
	private static Map<@NonNull String, @NonNull CompletePackageId> stringMappings = null;

	public static void addRegexMapping(@NonNull String packageURI, @NonNull CompletePackageId completePackageID) {
		Map<@NonNull Pattern, @NonNull CompletePackageId> regexMappings2 = regexMappings;
		if (regexMappings2 == null) {
			regexMappings = regexMappings2 = new HashMap<>();
		}
		Pattern pattern = Pattern.compile(packageURI);
		assert pattern != null;
		CompletePackageId old = regexMappings2.put(pattern, completePackageID);
		assert (old == null) || (old == completePackageID);
	}

	public static void addStringMapping(@NonNull String packageURI, @NonNull CompletePackageId completePackageID) {
		Map<@NonNull String, @NonNull CompletePackageId> stringMappings2 = stringMappings;
		if (stringMappings2 == null) {
			stringMappings = stringMappings2 = new HashMap<>();
		}
		CompletePackageId old = stringMappings2.put(packageURI, completePackageID);
		assert (old == null) || (old == completePackageID);
	}

	/**
	 * Return the CompletePackageId registered to match the packageURI, or null if nothing registered.
	 */
	public static @Nullable CompletePackageId basicGetCompletePackageId(@Nullable String packageURI) {
		if (stringMappings == null) {
			new CompletePackageIdRegistryReader().readRegistry();
			assert stringMappings != null;
		}
		if (packageURI != null) {
			if (stringMappings != null) {
				CompletePackageId completePackageId = stringMappings.get(packageURI);
				if (completePackageId != null) {
					return completePackageId;
				}
			}
			if (regexMappings != null) {
				for (Pattern pattern : regexMappings.keySet()) {
					Matcher matcher = pattern.matcher(packageURI);
					if (matcher.matches()) {
						return regexMappings.get(pattern);
					}
				}
			}
		}
		return null;
	}

	public static boolean removeRegexMapping(@NonNull String packageURI) {
		boolean gotOne = false;
		if (regexMappings != null) {
			for (Pattern pattern : new ArrayList<>(regexMappings.keySet())) {
				if (packageURI.equals(pattern.toString())) {
					regexMappings.remove(pattern);
					gotOne = true;
				}
			}
		}
		return gotOne;
	}

	public static boolean removeStringMapping(@NonNull String packageURI) {
		return (stringMappings != null) && (stringMappings.remove(packageURI) != null);
	}

	public CompletePackageIdRegistryReader() {
		super(ClassUtil.getExtensionRegistry(), PivotPlugin.PLUGIN_ID, PivotPlugin.COMPLETE_PACKAGE_ID_PID);
	}

	@Override
	protected boolean readElement(IConfigurationElement element, boolean add) {
		String tagName = element.getName();
		if (!TAG_MAPPING.equals(tagName)) {
			return false;
		}
		final String packageURI = element.getAttribute(ATTRIBUTE_PACKAGE_URI);
		final String completePackageID = element.getAttribute(ATTRIBUTE_COMPLETE_PACKAGE_ID);
		final boolean regex = Boolean.parseBoolean(element.getAttribute(ATTRIBUTE_REGEX));
		if (packageURI == null) {
			logMissingAttribute(element, ATTRIBUTE_PACKAGE_URI);
			return false;
		}
		else if (completePackageID == null) {
			logMissingAttribute(element, ATTRIBUTE_COMPLETE_PACKAGE_ID);
			return false;
		}
		if (add) {
			CompletePackageId completePackageId2 = IdManager.getCompletePackageId(completePackageID);
			if (regex) {
				addRegexMapping(packageURI, completePackageId2);
			}
			else {
				addStringMapping(packageURI, completePackageId2);
			}
		} else {
			if (regex) {
				removeRegexMapping(packageURI);
			}
			else {
				removeStringMapping(packageURI);
			}
		}
		return true;
	}
}
