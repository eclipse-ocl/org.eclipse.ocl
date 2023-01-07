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
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.Feature;
import org.eclipse.ocl.pivot.Model;
import org.eclipse.ocl.pivot.NamedElement;
import org.eclipse.ocl.pivot.PivotFactory;
import org.eclipse.ocl.pivot.internal.utilities.PivotUtilInternal;
import org.eclipse.ocl.pivot.util.PivotPlugin;

/**
 * AbstractLanguageSupport defines the abstract support for invocation of native language facilities from OCL.
 * @since 1.18
 */
public abstract class AbstractLanguageSupport implements LanguageSupport
{
	public static interface Factory
	{
		@NonNull AbstractLanguageSupport createLanguageSupport(@NonNull EnvironmentFactory environmentFactory);
		@NonNull String getName();
		void install();
	}
	private static final @NonNull String TAG_LANGUAGE = "language";
	private static final @NonNull String ATT_CLASS = "class";
	private static final @NonNull String ATT_NAME = "name";

	private static @Nullable Map<@NonNull String, AbstractLanguageSupport.@NonNull Factory> supportRegistry = null;

	public static void addLanguageSupport(AbstractLanguageSupport.@NonNull Factory support) {
		Map<@NonNull String, AbstractLanguageSupport.@NonNull Factory> supportRegistry2 = supportRegistry;
		if (supportRegistry2 == null) {
			supportRegistry2 = createSupportRegistry();
		}
		supportRegistry2.put(support.getName(), support);
	}

	/**
	 * Append name normally as is, but if name has weird characters encode to normal.
	 */
	private static void appendAndEncodeName(@NonNull StringBuilder s, @NonNull String name) {
		for (int i = 0; i < name.length(); i++) {
			char ch = name.charAt(i);
			if (!Character.isJavaIdentifierPart(ch)) {
				s.append("_" + Integer.toString(ch) + "_");
			}
			else {
				s.append(ch);
			}
		}
	}

	public static void appendQualification(@NonNull StringBuilder s, @Nullable EObject asNamedElement) {
		if ((asNamedElement instanceof NamedElement) && !(asNamedElement instanceof Model)) {
			appendQualification(s, asNamedElement.eContainer());
			String name = ((NamedElement)asNamedElement).getName();
			if (name.length() > 0) {			// Hide the default Java package
				appendAndEncodeName(s, name);
				s.append(".");
			}
		}
	}

	public static org.eclipse.ocl.pivot.@Nullable Package basicGetCachePackage(org.eclipse.ocl.pivot.@NonNull Class asClass) {
		org.eclipse.ocl.pivot.@NonNull Package asPackage = PivotUtil.getOwningPackage(asClass);
		List<org.eclipse.ocl.pivot.@NonNull Package> asSiblingPackages = PivotUtilInternal.getOwnedPackagesList(asPackage);
		return NameUtil.getNameable(asSiblingPackages, PivotUtil.getName(asClass));
	}

	protected static @NonNull Map<@NonNull String, AbstractLanguageSupport.@NonNull Factory> createSupportRegistry() {
		Map<@NonNull String, AbstractLanguageSupport.@NonNull Factory> supportRegistry2 = supportRegistry = new HashMap<>(4);
		readExtension();
		return supportRegistry2;
	}

	/**
	 * Return the package in which a cache class to support asFeature may be created.
	 *
	 * Since The Pivot does not support nested classes, they are simulated by nesting within a Package
	 * that has the same name as the owning class of asFeature.
	 */
	public static org.eclipse.ocl.pivot.@NonNull Package getCachePackage(@NonNull Feature asFeature) {
		org.eclipse.ocl.pivot.@NonNull Class asClass = PivotUtil.getOwningClass(asFeature);
		return getCachePackage(asClass);
	}
	public static org.eclipse.ocl.pivot.@NonNull Package getCachePackage(org.eclipse.ocl.pivot.@NonNull Class asClass) {
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
			supportRegistry2 = createSupportRegistry();
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

	public static @NonNull String getQualifiedName(@NonNull NamedElement asNamedElement) {
		StringBuilder s = new StringBuilder();
		appendQualification(s, asNamedElement.eContainer());
		appendAndEncodeName(s, PivotUtil.getName(asNamedElement));
		return s.toString();
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