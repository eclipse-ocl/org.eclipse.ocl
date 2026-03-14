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
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.emf.ecore.plugin.RegistryReader;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.Model;
import org.eclipse.ocl.pivot.ids.CompletePackageId;
import org.eclipse.ocl.pivot.ids.IdManager;
import org.eclipse.ocl.pivot.internal.resource.BuiltInASResourceFactory;
import org.eclipse.ocl.pivot.util.PivotPlugin;
import org.eclipse.ocl.pivot.utilities.ClassUtil;

/**
 * A plugin extension reader that populates the CompletePackageId mappings.
 *
 * The registered classes populate the BuiltInASResourceFactory.
 *
 * @since 7.0
 */
public class CompletePackageIdRegistryReader extends RegistryReader
{
	private static final @NonNull String TAG_MAPPING = "mapping";
	private static final @NonNull String ATTRIBUTE_ASPECT = "aspect";
	private static final @NonNull String ATTRIBUTE_PACKAGE_URI = "packageURI";
	private static final @NonNull String ATTRIBUTE_COMPLETE_PACKAGE_ID = "completePackageId";
	private static final @NonNull String ATTRIBUTE_REGEX = "regex";
	private static final @NonNull String ATTRIBUTE_CLASS = "class";

	private static Map<@NonNull Pattern, @NonNull CompletePackageIdWithAspects> regexMappings = null;
	private static final @NonNull Map<@NonNull URI, @NonNull CompletePackageIdWithAspects> uriMappings = new HashMap<>();	// Defining plugin has two string mappings

	public static void addRegexMapping(@NonNull String packageRegex, @NonNull CompletePackageId completePackageID, @NonNull String aspect) {
		Map<@NonNull Pattern, @NonNull CompletePackageIdWithAspects> regexMappings2 = regexMappings;
		if (regexMappings2 == null) {
			regexMappings = regexMappings2 = new HashMap<>();
		}
		Pattern pattern = Pattern.compile(packageRegex);
		assert pattern != null;
		CompletePackageIdWithAspects completePackageIdWithAspects = regexMappings2.get(pattern);
		if (completePackageIdWithAspects == null) {
			completePackageIdWithAspects = new CompletePackageIdWithAspects(completePackageID);
			regexMappings2.put(pattern, completePackageIdWithAspects);
		}
		completePackageIdWithAspects.addAspect(aspect);
	}

	public static void addStringMapping(@NonNull URI packageURI, @NonNull CompletePackageId completePackageID, @NonNull String aspect) {
		CompletePackageIdWithAspects completePackageIdWithAspects = uriMappings.get(packageURI);
		if (completePackageIdWithAspects == null) {
			completePackageIdWithAspects = new CompletePackageIdWithAspects(completePackageID);
			uriMappings.put(packageURI, completePackageIdWithAspects);
		}
		completePackageIdWithAspects.addAspect(aspect);
	}

	/**
	 * Return the CompletePackageId registered to match the packageURI, or null if nothing registered.
	 */
	public static @Nullable CompletePackageId basicGetCompletePackageId(@Nullable String packageURI) {
		if (packageURI != null) {
			CompletePackageIdWithAspects completePackageIdWithAspects = basicGetCompletePackageIdWithAspects(packageURI);
			if (completePackageIdWithAspects != null) {
				return completePackageIdWithAspects.getCompletePackageId();
			}
		}
		return null;
	}

	/**
	 * Return the CompletePackageId and aspects registered to match the packageURI, or null if nothing registered.
	 */
	public static @Nullable CompletePackageIdWithAspects basicGetCompletePackageIdWithAspects(@Nullable String packageURI) {
		if (packageURI != null) {
			initialize();
			CompletePackageIdWithAspects completePackageIdWithAspects = uriMappings.get(URI.createURI(packageURI));
			if (completePackageIdWithAspects != null) {
				return completePackageIdWithAspects;
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

	/**
	 * Process the complete_package_id extension point, returning true if this was necessary, false if a redundant repetition.
	 */
	public static boolean initialize() {
		if (!uriMappings.isEmpty()) {			// Defining plugin has two URI mappings
			return false;
		}
		new CompletePackageIdRegistryReader().readRegistry();
		return true;
	}

	public static void removeRegexMapping(@NonNull String packageRegex, @NonNull String aspect) {
		if (regexMappings != null) {
			for (Pattern pattern : new ArrayList<>(regexMappings.keySet())) {
				if (packageRegex.equals(pattern.toString())) {
					CompletePackageIdWithAspects completePackageIdWithAspects = regexMappings.get(pattern);
					assert completePackageIdWithAspects != null;
					if (completePackageIdWithAspects.removeAspect(aspect)) {
						regexMappings.remove(pattern);
					}
				}
			}
		}
	}

	public static void removeStringMapping(@NonNull URI packageURI, @NonNull String aspect) {
		CompletePackageIdWithAspects completePackageIdWithAspects = uriMappings.get(packageURI);
		if (completePackageIdWithAspects != null) {
			if (completePackageIdWithAspects.removeAspect(aspect)) {
				uriMappings.remove(packageURI);
			}
		}
	}

	public static class CompletePackageIdWithAspects
	{
		protected final @NonNull CompletePackageId completePackageId;
		private @Nullable List<@NonNull String> aspects = null;

		public CompletePackageIdWithAspects(@NonNull CompletePackageId completePackageId) {
			this.completePackageId = completePackageId;
		}

		public void addAspect(@NonNull String aspect) {
			List<@NonNull String> aspects2 = aspects;
			if (aspects2 == null) {
				aspects2 = aspects = new ArrayList<>();
			}
			if (!aspects2.contains(aspect)) {
				aspects2.add(aspect);
			}
		}

		public @NonNull CompletePackageId getCompletePackageId() {
			return completePackageId;
		}

		public boolean hasAspect(@NonNull String aspect) {
			return (aspects != null) && aspects.contains(aspect);
		}

		public boolean removeAspect(@NonNull String aspect) {
			List<@NonNull String> aspects2 = aspects;
			if (aspects2 != null) {
				aspects2.remove(aspect);
				if (aspects2.isEmpty()) {
					aspects2 = aspects = null;
					return true;
				}
			}
			return false;
		}

		@Override
		public @NonNull String toString() {
			StringBuilder s = new StringBuilder();
			s.append(completePackageId.toString());
			List<@NonNull String> aspects2 = aspects;
			if (aspects2 != null) {
				for (@NonNull String aspect : aspects2) {
					s.append(" ");
					s.append(aspect);
				}
			}
			return s.toString();
		}
	}

    /**
     * A <code>Descriptor</code> may be used by the {@link BuiltInASResourceFactory.Registry}
     * to defer loading of a built-in model until needed.
     */
    public static interface Descriptor
    {
		/*@NonNull*/ Model getModel();		// -- nullity annotations are optional for derived classes
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
		final String packageURIstring = element.getAttribute(ATTRIBUTE_PACKAGE_URI);
		String aspect = element.getAttribute(ATTRIBUTE_ASPECT);
		String completePackageID = element.getAttribute(ATTRIBUTE_COMPLETE_PACKAGE_ID);
		final boolean regex = Boolean.parseBoolean(element.getAttribute(ATTRIBUTE_REGEX));
		final String tablesClass = element.getAttribute(ATTRIBUTE_CLASS);
		if (packageURIstring == null) {
			logMissingAttribute(element, ATTRIBUTE_PACKAGE_URI);
			return false;
		}
		final URI packageURI = URI.createURI(packageURIstring);
		if (completePackageID == null) {
			completePackageID = packageURIstring;
		}
		if (aspect == null) {
			aspect = "";
		}
		if (add) {
			CompletePackageId completePackageId2 = IdManager.getCompletePackageId(completePackageID);
			if (regex) {
				addRegexMapping(packageURIstring, completePackageId2, aspect);
			}
			else {
				addStringMapping(packageURI, completePackageId2, aspect);
				if (tablesClass != null) {
					try {
						Descriptor descriptor = (Descriptor)element.createExecutableExtension(ATTRIBUTE_CLASS);
						BuiltInASResourceFactory.addModelDescriptor(packageURI, descriptor);
					} catch (CoreException e) {
						EcorePlugin.INSTANCE.log(e);
						return false;
					}
				}
			}
		} else {
			if (regex) {
				removeRegexMapping(packageURIstring, aspect);
			}
			else {
				removeStringMapping(packageURI, aspect);
				if (tablesClass != null) {
					BuiltInASResourceFactory.removeModelDescriptor(packageURI);
				}
			}
		}
		return true;
	}
}
