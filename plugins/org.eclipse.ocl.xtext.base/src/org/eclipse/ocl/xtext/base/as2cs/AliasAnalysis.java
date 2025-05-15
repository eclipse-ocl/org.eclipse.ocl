/*******************************************************************************
 * Copyright (c) 2011, 2025 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.xtext.base.as2cs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.CompletePackage;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.Import;
import org.eclipse.ocl.pivot.NamedElement;
import org.eclipse.ocl.pivot.Namespace;
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal;
import org.eclipse.ocl.pivot.internal.utilities.PathElement;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.MetamodelManager;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.utilities.Pivotable;
import org.eclipse.ocl.pivot.utilities.ThreadLocalExecutor;
import org.eclipse.ocl.pivot.utilities.TreeIterable;
import org.eclipse.ocl.xtext.base.utilities.BaseCSResource;
import org.eclipse.ocl.xtext.basecs.ImportCS;
import org.eclipse.ocl.xtext.basecs.NamedElementCS;
import org.eclipse.ocl.xtext.basecs.RootPackageCS;

/**
 * An AliasAnalysis is dynamically created to support the serialization
 * of cross-references following a Pivot to CS conversion. It ensures the
 * resource-wide uniqueness of aliases for package names.
 *
 * Uniqueness is achieved with respect to all names to avoid the complexity
 * of considering which name usages are not actually conflicting.
 */
public class AliasAnalysis extends AdapterImpl
{
	public static void dispose(@NonNull Resource resource) {
		List<Adapter> eAdapters = ClassUtil.requireNonNull(resource.eAdapters());
		AliasAnalysis adapter = ClassUtil.getAdapter(AliasAnalysis.class, eAdapters);
		if (adapter != null) {
			adapter.dispose();
		}
	}

	public static @NonNull AliasAnalysis getAdapter(@NonNull Resource resource) {
		EnvironmentFactoryInternal environmentFactory = ThreadLocalExecutor.basicGetEnvironmentFactory();
		if (environmentFactory == null) {
			throw new IllegalStateException("No EnvironmentFactory");
		}
		return getAdapter(resource, environmentFactory);
	}

	public static @NonNull AliasAnalysis getAdapter(@NonNull Resource resource, @NonNull EnvironmentFactoryInternal environmentFactory) {
		List<Adapter> eAdapters = resource.eAdapters();
		for (Adapter adapter : eAdapters) {
			if (adapter instanceof AliasAnalysis) {
				AliasAnalysis aliasAnalysis = (AliasAnalysis)adapter;
				if (aliasAnalysis.environmentFactory == environmentFactory) {
					return aliasAnalysis;
				}
			}
		}
		AliasAnalysis aliasAnalysis = new AliasAnalysis(resource, environmentFactory);
		Set<@NonNull CompletePackage> localCompletePackages = new HashSet<>();
		Set<@NonNull CompletePackage> otherCompletePackages = new HashSet<>();
		aliasAnalysis.computePackages(localCompletePackages, otherCompletePackages);
		aliasAnalysis.computeAliases(localCompletePackages, otherCompletePackages);
		return aliasAnalysis;
	}

	protected final @NonNull EnvironmentFactoryInternal environmentFactory;

	/**
	 * Mapping of all named elements from the name to the name usage,
	 * which is non-null for a uniquely named element, or
	 * null for a name shared between multiple elements.
	 */
	private @NonNull Map<@NonNull String, @Nullable Object> allNames = new HashMap<>();

	/**
	 * The known package aliases assigned by an import declaration.
	 */
	private @NonNull Map<@NonNull CompletePackage, @Nullable String> allKnownAliases = new HashMap<>();

	/**
	 * The candidate package aliases computed to determine distinct names for code generation
	 */
	private @NonNull Map<@NonNull CompletePackage, @Nullable String> allAliases = new HashMap<>();

	public AliasAnalysis(@NonNull Resource resource, @NonNull EnvironmentFactoryInternal environmentFactory) {
		resource.eAdapters().add(this);
		this.environmentFactory = environmentFactory;
	}

	/**
	 * Assign a unique alias to each localPackage then to each otherPackage.
	 */
	private void computeAliases(@NonNull Set<@NonNull CompletePackage> localCompletePackages,
			@NonNull Set<@NonNull CompletePackage> otherCompletePackages) {
		for (@NonNull CompletePackage localCompletePackage : localCompletePackages) {
			if ((localCompletePackage.getNsPrefix() != null) || (localCompletePackage.getOwningCompletePackage() == null)) {
				String alias = computeCandidateAlias(localCompletePackage);
				String old = allAliases.put(localCompletePackage, alias);
				assert old == null;
			}
		}
		for (@NonNull CompletePackage otherCompletePackage : otherCompletePackages) {
			String alias = computeCandidateAlias(otherCompletePackage);
			String old = allAliases.put(otherCompletePackage, alias);
			assert old == null;
		}
	}

	/**
	 * Register the usage of name by primaryElement, and if name is already in use
	 * register the ambiguity as a usage by null.
	 */
	private void addName(@NonNull String name, @NonNull Object primaryElement) {
		if (!allNames.containsKey(name)) {
			allNames.put(name, primaryElement);
		}
		else if (allNames.get(name) != primaryElement) {
			allNames.put(name, null);
		}
	}

	/**
	 * Determine a unique alias for primaryPackage/
	 */
	private @NonNull String computeCandidateAlias(@NonNull CompletePackage primaryPackage) {
		String nsPrefix = primaryPackage.getNsPrefix();
		String aliasBase = nsPrefix != null ? nsPrefix : getDefaultAlias(primaryPackage.getName());
		int index = 0;
		String alias = aliasBase;
		while (allNames.containsKey(alias) && (allNames.get(alias) != primaryPackage)) {
			@SuppressWarnings("unused")
			Object debugObject = allNames.get(alias);
			alias = aliasBase + "_" + index++;
		}
		addName(alias, primaryPackage);
		return alias;
	}

	public void computeCandidates(@NonNull BaseCSResource csResource) {
		List<EObject> contents = csResource.getContents();
		if (contents.size() > 0) {
			EObject root = contents.get(0);
			if (root instanceof RootPackageCS) {
				for (ImportCS csImport : ((RootPackageCS)root).getOwnedImports()) {
					Element pivot = csImport.getPivot();
					if (pivot instanceof Import) {
						Import asImport = (Import)pivot;
						String alias = asImport.getName();
						Namespace asNamespace = asImport.getImportedNamespace();
						if ((asNamespace != null) && (alias != null)) {
							getAlias(asNamespace, alias);
						}
					}
				}
			}
		}
	}

	/**
	 * Scan the target resource to identify allNames of any form that appear,
	 * allAliases assigned by explicit imports, all localPackages whose name is
	 * defined within the target resource all all otherPackages. Nested packages
	 * of localPackages are excluded from localPackages.
	 */
	private void computePackages(@NonNull Set<@NonNull CompletePackage> localCompletePackages,
			@NonNull Set<@NonNull CompletePackage> otherCompletePackages) {
		MetamodelManager metamodelManager = environmentFactory.getMetamodelManager();
		assert target != null;
		for (EObject eObject : new TreeIterable((Resource)target)) {
			if (eObject instanceof ImportCS) {
				String name = ((ImportCS)eObject).getName();
				Namespace namespace = ((ImportCS)eObject).getReferredNamespace();
				if (namespace instanceof org.eclipse.ocl.pivot.Package) {						// XXX Model can be alias too
					CompletePackage completePackage = metamodelManager.getCompletePackage((org.eclipse.ocl.pivot.Package)namespace);
					allKnownAliases.put(completePackage, name);
					allAliases.put(completePackage, name);
				}
			}
			EObject csObject = eObject;
			if (eObject instanceof Pivotable) {
				eObject = ((Pivotable)eObject).getPivot();
			}
			if (eObject instanceof NamedElement) {
				NamedElement domainNamedElement = (NamedElement) eObject;
				if (!(eObject instanceof CompletePackage)) {
					if (eObject instanceof CompletePackage) {
						;
					}
					else if (eObject instanceof org.eclipse.ocl.pivot.Package) {
						domainNamedElement = metamodelManager.getCompletePackage((org.eclipse.ocl.pivot.Package)eObject);
					}
					else {
						//						domainNamedElement = metamodelManager.getPrimaryElement((NamedElement)eObject);
					}
				}
				String name = domainNamedElement.getName();
				if (name != null) {
					addName(name, domainNamedElement);
				}
				if ((eObject instanceof org.eclipse.ocl.pivot.Package) && (csObject instanceof RootPackageCS)) {			// FIXME
					org.eclipse.ocl.pivot.Package pivotPackage = (org.eclipse.ocl.pivot.Package)eObject;
					String nsPrefix = pivotPackage.getNsPrefix();
					if (nsPrefix != null) {
						addName(nsPrefix, domainNamedElement);
					}
					localCompletePackages.add(metamodelManager.getCompletePackage(pivotPackage));
				}
				else {
					for (EObject eContainer = eObject; eContainer != null; eContainer = eContainer.eContainer()) {
						if (eContainer instanceof org.eclipse.ocl.pivot.Package) {
							otherCompletePackages.add(metamodelManager.getCompletePackage((org.eclipse.ocl.pivot.Package)eContainer));
							break;
						}
						if (eContainer instanceof org.eclipse.ocl.pivot.Class) {
							eContainer = PivotUtil.getUnspecializedTemplateableElement((org.eclipse.ocl.pivot.Class)eContainer);
						}
					}
				}
			}
		}
		otherCompletePackages.removeAll(localCompletePackages);
		otherCompletePackages.removeAll(allKnownAliases.keySet());
		Set<@NonNull CompletePackage> nestedCompletePackages = new HashSet<>();
		for (@NonNull CompletePackage localCompletePackage : localCompletePackages) {
			EObject eContainer = localCompletePackage.eContainer();
			if (eContainer instanceof CompletePackage) {
				EObject eContainerContainer = eContainer.eContainer();
				if (eContainerContainer instanceof CompletePackage) {
					nestedCompletePackages.add(localCompletePackage);
				}
			}
		}
		localCompletePackages.removeAll(nestedCompletePackages);
		localCompletePackages.removeAll(allKnownAliases.keySet());
	}

	public void dispose() {
		target.eAdapters().remove(this);
	}

	/**
	 * Return the alias for eObject, using a non-null hint as a stem for auto-generation, or null if there is none.
	 */
	public @Nullable String getAlias(@NonNull EObject eObject, @Nullable String hint) {
		EObject eObject2 = eObject;
		if (eObject2 instanceof Pivotable) {
			eObject2 = ((Pivotable)eObject2).getPivot();
		}
		if (eObject2 instanceof org.eclipse.ocl.pivot.Package) {
			CompletePackage completePackage = environmentFactory.getMetamodelManager().getCompletePackage((org.eclipse.ocl.pivot.Package)eObject2);
			String alias = allKnownAliases.get(completePackage);
			if (alias != null) {
				return alias;
			}
			alias = allAliases.get(completePackage);
			if (alias != null) {
				return alias;
			}
			/*			MetamodelManager metamodelManager = ElementUtil.findMetamodelManager((Resource)getTarget());
			if (metamodelManager != null) {
				eObject = metamodelManager.getPrimaryElement(eObject);
				return allAliases.get(eObject);
			} */
			if (hint != null) {
				if (allNames.get(hint) != null) {
					int counter = 0;
					while (allNames.get(hint + "_" + counter) != null) {
						counter++;
					}
					hint = hint + "_" + counter;
				}
				allNames.put(hint, completePackage);
				allAliases.put(completePackage, hint);
				return hint;
			}
		}
		return null;
	}

	/**
	 * Return the CompletePackages with known aliases.
	 */
	public @NonNull Iterable<@NonNull CompletePackage> getKnownAliases() {
		return allKnownAliases.keySet();
	}

	protected @NonNull String getDefaultAlias(@Nullable String name) {
		if (name == null) {
			return "anon";			// Never happens
		}
		int iMax = name.length();
		if (iMax <= 0) {
			return "anon";			// Never happens
		}
		if (Character.isLowerCase(name.charAt(0))) {
			return name;
		}
		StringBuilder s = new StringBuilder();
		for (int i = 0; i < iMax; i++) {
			char c = name.charAt(i);
			if (Character.isUpperCase(c)) {
				s.append(Character.toLowerCase(c));
			}
			else {
				s.append(name.substring(i));
				break;
			}
		}
		return s.toString();
	}

	/**
	 * Return the known alias for eObject.
	 */
	public @Nullable String getKnownAlias(@NonNull EObject eObject) {
		if (eObject instanceof org.eclipse.ocl.pivot.Package) {
			CompletePackage completePackage = environmentFactory.getMetamodelManager().getCompletePackage((org.eclipse.ocl.pivot.Package)eObject);
			return allKnownAliases.get(completePackage);
		}
		return null;
	}

	public @NonNull List<@NonNull PathElement> getPath(@NonNull Element eObject) {
		EObject eContainer = eObject.eContainer();
		if (eContainer == null) {
			return new ArrayList<>();
		}
		List<@NonNull PathElement> result = getPath((Element) eContainer);
		if (eObject instanceof NamedElement) {
			result.add(new PathElement(((NamedElement)eObject).getName(), eObject));
		}
		else if (eObject instanceof ENamedElement) {
			result.add(new PathElement(((ENamedElement)eObject).getName(), eObject));
		}
		else if (eObject instanceof NamedElementCS) {
			result.add(new PathElement(((NamedElementCS)eObject).getName(), eObject));
		}
		return result;
	}

	@Override
	public boolean isAdapterForType(Object type) {
		return type == AliasAnalysis.class;
	}
}
