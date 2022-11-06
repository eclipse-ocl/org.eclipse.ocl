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
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.Feature;
import org.eclipse.ocl.pivot.Model;
import org.eclipse.ocl.pivot.PivotFactory;
import org.eclipse.ocl.pivot.internal.utilities.PivotUtilInternal;
import org.eclipse.ocl.pivot.util.PivotPlugin;

/**
 * AbstractLanguageSupport defines the abstract support for invocation of native language facilities from OCL.
 * @since 1.18
 */
public abstract class AbstractLanguageSupport implements LanguageSupport
{
	public static interface Factory {
		@NonNull AbstractLanguageSupport createLanguageSupport(@NonNull EnvironmentFactory environmentFactory);

		void install();

		@NonNull String getName();
	}
	private static final @NonNull String TAG_LANGUAGE = "language";
	private static final @NonNull String ATT_CLASS = "class";
	private static final @NonNull String ATT_NAME = "name";

	private static @Nullable Map<@NonNull String, AbstractLanguageSupport.@NonNull Factory> supportRegistry = null;

	public static void addLanguageSupport(AbstractLanguageSupport.@NonNull Factory support) {
		Map<@NonNull String, AbstractLanguageSupport.@NonNull Factory> supportRegistry2 = supportRegistry;
		if (supportRegistry2 == null) {
			supportRegistry = supportRegistry2 = new HashMap<>();
			readExtension();
		}
		supportRegistry2.put(support.getName(), support);
	}

	/**
	 * Return the package in which a cache class to support asFeature may be created.
	 *
	 * Since The Pivot does not support nested classes, they are simulated by nesting within a Package
	 * that has the same name as the owning class of asFeature.
	 */
	public static org.eclipse.ocl.pivot.@NonNull Package getCachePackage(@NonNull Feature asFeature) {
		org.eclipse.ocl.pivot.@NonNull Class asClass = PivotUtil.getOwningClass(asFeature);
		org.eclipse.ocl.pivot.@NonNull Package asPackage = PivotUtil.getOwningPackage(asClass);
		return getPackage(asPackage, PivotUtil.getName(asClass));
	}

	public static org.eclipse.ocl.pivot.@NonNull Class getClass(org.eclipse.ocl.pivot.@NonNull Package asParentPackage, @NonNull String name) {
		List<org.eclipse.ocl.pivot.@NonNull Class> asSiblingClasses = PivotUtilInternal.getOwnedClassesList(asParentPackage);
		org.eclipse.ocl.pivot.Class asClass = NameUtil.getNameable(asSiblingClasses, name);
		if (asClass == null) {
			asClass = PivotFactory.eINSTANCE.createClass();
			asClass.setName(name);
			asSiblingClasses.add(asClass);
		}
		return asClass;
	}

	public static @Nullable AbstractLanguageSupport getLanguageSupport(@NonNull String name, @NonNull EnvironmentFactory environmentFactory) {
		Map<@NonNull String, AbstractLanguageSupport.@NonNull Factory> supportRegistry2 = supportRegistry;
		if (supportRegistry2 == null) {
			supportRegistry = supportRegistry2 = new HashMap<>();
			readExtension();
		}
		Factory factory = supportRegistry2.get(name);
		return factory != null ? factory.createLanguageSupport(environmentFactory) : null;
	}

	public static org.eclipse.ocl.pivot.@NonNull Package getPackage(@NonNull Model asModel, @NonNull String name) {
		List<org.eclipse.ocl.pivot.@NonNull Package> asSiblingPackages = PivotUtilInternal.getOwnedPackagesList(asModel);
		return getPackage(asSiblingPackages, name);
	}

	public static org.eclipse.ocl.pivot.@NonNull Package getPackage(org.eclipse.ocl.pivot.@NonNull Package asParentPackage, @NonNull String name) {
		List<org.eclipse.ocl.pivot.@NonNull Package> asSiblingPackages = PivotUtilInternal.getOwnedPackagesList(asParentPackage);
		return getPackage(asSiblingPackages, name);
	}

	protected static org.eclipse.ocl.pivot.@NonNull Package getPackage(@NonNull List<org.eclipse.ocl.pivot.@NonNull Package> asSiblingPackages, @NonNull String name) {
		org.eclipse.ocl.pivot.Package asPackage = NameUtil.getNameable(asSiblingPackages, name);
		if (asPackage == null) {
			asPackage = PivotFactory.eINSTANCE.createPackage();
			asPackage.setName(name);
			asSiblingPackages.add(asPackage);
		}
		return asPackage;
	}

	/**
	 * Return true if asPackage has a saem-named sibling Class; asPackage therefore provides its nested classes.
	 */
	public static boolean isNestingClass(org.eclipse.ocl.pivot.@NonNull Package asPackage) {
		org.eclipse.ocl.pivot.Package asParentPackage = asPackage.getOwningPackage();
		if (asParentPackage ==null) {
			return false;
		}
		List<org.eclipse.ocl.pivot.@NonNull Class> asSiblingClasses = PivotUtilInternal.getOwnedClassesList(asParentPackage);
		org.eclipse.ocl.pivot.Class asNestingClass = NameUtil.getNameable(asSiblingClasses, PivotUtil.getName(asPackage));
		return asNestingClass != null;
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
						AbstractLanguageSupport.@NonNull Factory languageSupport = (AbstractLanguageSupport.@NonNull Factory)element.createExecutableExtension(ATT_CLASS);
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
}