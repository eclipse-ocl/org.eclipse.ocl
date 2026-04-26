/*******************************************************************************
 * Copyright (c) 2010, 2025 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.internal.scoping;

import java.util.HashMap;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.internal.utilities.PivotUtilInternal;
import org.eclipse.ocl.pivot.utilities.ClassUtil;

/**
 * An Attribution provides a helper to assist in evaluating the inheriteed or synthesized attributes
 * of a type. All helpers are static and stateless, although some may maintain state using an Adapter on the
 * target element.
 *
 * The REGISTRY maintains the helpers for each type. Missing entries may be lazily populated by following the
 * primary superclasses until an entry is found.
 */
public interface Attribution
{
	/**
	 * @since 7.0
	 */
	public static class AttributionRegistry extends HashMap<@NonNull EClassifier, @NonNull Attribution>
	{
		private static final long serialVersionUID = 1L;
		private static final Logger logger = Logger.getLogger(AttributionRegistry.class);

		@Override @Deprecated /* @deprecated use new AttributionRegistryInstaller().install() for better debugging */
		public @NonNull Attribution put(@NonNull EClassifier key, @NonNull Attribution newValue) {
			assert PivotUtilInternal.debugDeprecation("AttributionRegistry.put");
			return install(key,  newValue, null);
		}

		// Bug 583509 - the GlobalState save and restore should include this registry.
		@SuppressWarnings("null")
		private @NonNull Attribution install(/*@NonNull*/ EClassifier key, @NonNull Attribution newValue, @Nullable Class<?> scopingClass) {
			assert key != null;
			Attribution oldValue = super.put(key, newValue);
			if (oldValue != null) {
				assert oldValue.getClass().isAssignableFrom(newValue.getClass()) : newValue.getClass().getName() + " for " + key.getName() + " key does not refine " + oldValue.getClass().getName();
			}
		//	System.out.println("AttributionRegistry[" + size() + "] «" + (scopingClass != null ? scopingClass.getSimpleName() : "inferred") + "» "
		//			+ /*NameUtil.debugSimpleName(key) + ":" +*/ key.getEPackage().getName() + "::" + key.getName() + " "
		//			+ newValue.getClass().getName() + (oldValue != null ? " / " + oldValue.getClass().getName() : ""));
			return oldValue;
		}

		public @NonNull Attribution getAttribution(@NonNull EObject eObject) {
			if (eObject.eIsProxy()) {			// Shouldn't happen, but certainly does during development
				logger.warn("getAttribution for proxy " + eObject);
				return NullAttribution.INSTANCE;
			}
			else {
				EClass eClass = eObject.eClass();
				assert eClass != null;
				Attribution attribution = ClassUtil.maybeNull(get(eClass));
				if (attribution == null) {
					for (EClass superClass = eClass; superClass.getESuperTypes().size() > 0;) {
						superClass = superClass.getESuperTypes().get(0);
						attribution = ClassUtil.maybeNull(get(superClass));
						if (attribution != null) {
							break;
						}
					}
					if (attribution == null) {
						attribution = NullAttribution.INSTANCE;
					}
					install(eClass, attribution, null);
				}
				return attribution;
			}
		}

		public @NonNull AttributionRegistryInstaller getInstaller(@NonNull Class<?> scopingClass) {
			return new AttributionRegistryInstaller(scopingClass);
		}
	}

	/**
	 * @since 7.0
	 */
	public static class AttributionRegistryInstaller
	{
		protected final @NonNull Class<?> scopingClass;

		public AttributionRegistryInstaller(@NonNull Class<?> scopingClass) {
			this.scopingClass = scopingClass;
		}

		public void install(/*@NonNull*/ EClassifier key, @NonNull Attribution newValue) {
			REGISTRY.install(key,  newValue, scopingClass);
		}
	}

	/**
	 * The per-classifier registry of attributions.
	 */
	public static @NonNull AttributionRegistry REGISTRY = new AttributionRegistry();

	/**
	 * Add the local lookup contributions to a view of an Environment.
	 * <p>
	 * The EnvironmentView contains the lookup matching criteria such as a specific name and
	 * accumulates candidate results.
	 * <p>
	 * The input ScopeView identifies the CS node and the containment of the CS child from which
	 * the lookup is made allowing derived implementations to present the alternative environments
	 * specified as the <i>Inherited Attributes</i> in the OCL Specification.
	 * <p>
	 * The returned ScopeView identifies an outer scope in which the lookup may be continued if the
	 * local candidate results are not suitable.
	 *
	 * @param environmentView the EnvironmentView to compute
	 * @param scopeView the access selectivity to be applied by the lookup
	 * @return an outer ScopeView in which to continue the lookup, or null if none
	 */
	@Nullable ScopeView computeLookup(@NonNull EObject target, @NonNull EnvironmentView environmentView, @NonNull ScopeView scopeView);
}
