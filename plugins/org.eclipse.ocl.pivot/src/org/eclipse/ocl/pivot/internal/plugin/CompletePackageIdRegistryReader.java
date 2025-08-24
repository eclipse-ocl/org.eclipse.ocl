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
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.emf.ecore.plugin.RegistryReader;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.ids.CompletePackageId;
import org.eclipse.ocl.pivot.ids.IdManager;
import org.eclipse.ocl.pivot.util.PivotPlugin;

/**
 * A plugin extension reader that populates the CompletePackageId mappings.
 *
 * @since 7.0
 */
public class CompletePackageIdRegistryReader extends RegistryReader
{
	private static final @NonNull String TAG_MAPPING = "mapping";
	private static final @NonNull String ATTRIBUTE_PACKAGE_URI = "packageURI";
	private static final @NonNull String ATTRIBUTE_COMPLETE_PACKAGE_ID = "completePackageID";

	private static final @NonNull Map<@NonNull Pattern, @NonNull CompletePackageId> mappings = new HashMap<>();

	public static void addMapping(@NonNull String packageURI, @NonNull CompletePackageId completePackageID) {
		Pattern pattern = Pattern.compile(packageURI);
		assert pattern != null;
		CompletePackageId old = mappings.put(pattern, completePackageID);
		assert (old == null) || (old == completePackageID);
	}

	public static @Nullable CompletePackageId basicGetCompletePackageId(@NonNull String packageURI) {
		if (mappings.isEmpty()) {
			if (EcorePlugin.IS_ECLIPSE_RUNNING) {
				new CompletePackageIdRegistryReader().readRegistry();
			}
			else {
				throw new IllegalStateException("Missing " + PivotPlugin.COMPLETE_PACKAGE_ID_PID + " registrations");
			}
		}
		for (Pattern pattern : mappings.keySet()) {
			Matcher matcher = pattern.matcher(packageURI);
			if (matcher.matches()) {
				return mappings.get(pattern);
			}
		}
		return null;
	}

	public static boolean removeMapping(@NonNull String packageURI) {
		boolean gotOne = false;
		for (Pattern pattern : new ArrayList<>(mappings.keySet())) {
			if (packageURI.equals(pattern.toString())) {
				mappings.remove(pattern);
				gotOne = true;
			}
		}
		return gotOne;
	}

	public CompletePackageIdRegistryReader() {
		super(Platform.getExtensionRegistry(), PivotPlugin.PLUGIN_ID, PivotPlugin.COMPLETE_PACKAGE_ID_PID);
	}

	@Override
	protected boolean readElement(IConfigurationElement element, boolean add) {
		String tagName = element.getName();
		if (!TAG_MAPPING.equals(tagName)) {
			return false;
		}
		final String packageURI = element.getAttribute(ATTRIBUTE_PACKAGE_URI);
		final String completePackageID = element.getAttribute(ATTRIBUTE_COMPLETE_PACKAGE_ID);
		if (packageURI == null) {
			logMissingAttribute(element, ATTRIBUTE_PACKAGE_URI);
			return false;
		}
		else if (completePackageID == null) {
			logMissingAttribute(element, ATTRIBUTE_COMPLETE_PACKAGE_ID);
			return false;
		}
		if (add) {
			addMapping(packageURI, IdManager.getCompletePackageId(completePackageID));
		} else {
			removeMapping(packageURI);
		}
		return true;
	}
}
