/*******************************************************************************
 * Copyright (c) 2022 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.utilities;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.Feature;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.util.PivotPlugin;

/**
 * LanguageSupport defines the abstract support for invocation of native language facilities from OCL.
 * @since 1.18
 */
public abstract class LanguageSupport
{
	public static interface Factory {
		@NonNull LanguageSupport createLanguageSupport(@NonNull EnvironmentFactory environmentFactory);

		void install();

		@NonNull String getName();
	}
	private static final @NonNull String TAG_LANGUAGE = "language";
	private static final @NonNull String ATT_CLASS = "class";
	private static final @NonNull String ATT_NAME = "name";

	private static @Nullable Map<@NonNull String, LanguageSupport.@NonNull Factory> supportRegistry = null;

	public static void addLanguageSupport(LanguageSupport.@NonNull Factory support) {
		Map<@NonNull String, LanguageSupport.@NonNull Factory> supportRegistry2 = supportRegistry;
		if (supportRegistry2 == null) {
			supportRegistry = supportRegistry2 = new HashMap<>();
			readExtension();
		}
		supportRegistry2.put(support.getName(), support);
	}

	public static @Nullable LanguageSupport getLanguageSupport(@NonNull String name, @NonNull EnvironmentFactory environmentFactory) {
		Map<@NonNull String, LanguageSupport.@NonNull Factory> supportRegistry2 = supportRegistry;
		if (supportRegistry2 == null) {
			supportRegistry = supportRegistry2 = new HashMap<>();
			readExtension();
		}
		Factory factory = supportRegistry2.get(name);
		return factory != null ? factory.createLanguageSupport(environmentFactory) : null;
	}

	private static @Nullable ThreadLocalExecutor readExtension() {
		IExtensionRegistry extensionRegistry = Platform.getExtensionRegistry();
		if (extensionRegistry == null) {
			return null;
		}
		IExtensionPoint point = extensionRegistry.getExtensionPoint(PivotPlugin.PLUGIN_ID, PivotPlugin.LANGUAGE_SUPPORT_PPID);
		if (point != null) {
			for (IConfigurationElement element : point.getConfigurationElements()) {
				String tagName = element.getName();
				if (TAG_LANGUAGE.equals(tagName)) {
				//	String className = element.getAttribute(ATT_CLASS);
					String language = element.getAttribute(ATT_NAME);
					assert language != null;
					try {
						@SuppressWarnings("null")
						LanguageSupport.@NonNull Factory languageSupport = (LanguageSupport.@NonNull Factory)element.createExecutableExtension(ATT_CLASS);
						assert languageSupport != null;
						addLanguageSupport(languageSupport);
					} catch (CoreException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return null;
	}

	/**
	 * Return the AS cache class for asClass.
	 */
	public abstract org.eclipse.ocl.pivot.@NonNull Class getCacheClass(org.eclipse.ocl.pivot.@NonNull Class asClass, @NonNull String name);

	/**
	 * Return the AS cache class for asFeature.
	 */
	public abstract org.eclipse.ocl.pivot.@NonNull Class getCacheClass(@NonNull Feature asFeature);

	/**
	 * Return the AS cache constructor class for asFeature.
	 */
	public abstract org.eclipse.ocl.pivot.@NonNull Class getConstructorClass(@NonNull Feature asFeature);

	/**
	 * Return the AS class for a Java Class.
	 */
	public abstract org.eclipse.ocl.pivot.@NonNull Class getNativeClass(/*@NonNull */Class<?> jClass);
	public abstract @Nullable Invocations resolveInvocations(@NonNull Type requiredSourceType, boolean hasExplicitSourceExp, @NonNull String qualifiedOperationName);
}